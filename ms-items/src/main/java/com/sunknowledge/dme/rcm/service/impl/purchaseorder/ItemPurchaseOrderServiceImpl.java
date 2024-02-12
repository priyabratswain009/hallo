package com.sunknowledge.dme.rcm.service.impl.purchaseorder;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.application.properties.AsyncConfigurationSetup;
import com.sunknowledge.dme.rcm.application.properties.FileConfigUtility;
import com.sunknowledge.dme.rcm.application.properties.FileUploadConfigProperties;
import com.sunknowledge.dme.rcm.commonutil.AccessTokenUtilities;
import com.sunknowledge.dme.rcm.commonutil.CommonPDFStubs;
import com.sunknowledge.dme.rcm.documentutil.PurchaseOrderTableBuilder;
import com.sunknowledge.dme.rcm.domain.PurchaseOrder;
import com.sunknowledge.dme.rcm.domain.PurchaseOrderItems;
import com.sunknowledge.dme.rcm.domain.VendorMaster;
import com.sunknowledge.dme.rcm.repository.itemothers.VendorMasterRepositoryExtended;
import com.sunknowledge.dme.rcm.repository.purchaseorder.PurchaseOrderItemsRepo;
import com.sunknowledge.dme.rcm.repository.purchaseorder.PurchaseOrderRepo;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderDTO;
import com.sunknowledge.dme.rcm.service.dto.salesorder.DeliveryAddress;
import com.sunknowledge.dme.rcm.service.mapper.PurchaseOrderMapper;
import com.sunknowledge.dme.rcm.service.purchaseorder.ItemPurchaseOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.FileOutputStream;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
public class ItemPurchaseOrderServiceImpl implements ItemPurchaseOrderService {
    @Autowired
    private PurchaseOrderRepo purchaseOrderRepository;
    @Autowired
    private PurchaseOrderItemsRepo purchaseOrderItemsRepository;
    @Autowired
    private VendorMasterRepositoryExtended vendorMasterRepository;
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private FileConfigUtility fileConfigUtility;
    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;
    @Autowired
    private FileUploadConfigProperties fileUploadConfigProperties;

    @Override
    @Async(AsyncConfigurationSetup.TASK_EXECUTOR_SERVICE)
    public CompletableFuture<ServiceOutcome<DeliveryAddress>> generatePurchaseOrderOnPoNumber(String poNumber) {
        log.info("==========================PO Number======================="+poNumber);
        return CompletableFuture.supplyAsync(() -> {
            try {
                PurchaseOrder purchaseOrder = purchaseOrderRepository.getPurchaseOrderDetailsOnPoNumber(poNumber);
                if (purchaseOrder == null) {
                    return new ServiceOutcome<>(null, false, "Purchase order not found.");
                }
                VendorMaster vendorMaster = vendorMasterRepository.findByVendorIdAndStatusIgnoreCase(purchaseOrder.getVendorId(), "active");
                List<PurchaseOrderItems> purchaseOrderItemsList = purchaseOrderItemsRepository.getPurchaseOrderDetailsOnPo(purchaseOrder.getPoId());
                return getDeliveryAddressAsync(purchaseOrder)
                    .thenComposeAsync(deliveryAddressOutcome ->
                        generatePdfDocument(poNumber, purchaseOrder, vendorMaster, purchaseOrderItemsList, deliveryAddressOutcome))
                    .thenComposeAsync(saveDocument -> savePORequestDocumentName(purchaseOrder, saveDocument))
                    .exceptionally(ex -> new ServiceOutcome<>(null, false, "Failed to generate purchase order: " + ex.getMessage()))
                    .join();
            } catch (Exception e) {
                e.printStackTrace();
                return new ServiceOutcome<>(null, false, "Failed to generate purchase order: " + e.getMessage());
            }
        });
    }

    private CompletableFuture<ServiceOutcome<DeliveryAddress>> savePORequestDocumentName(PurchaseOrder purchaseOrder, ServiceOutcome<DeliveryAddress> deliveryAddress){
        return CompletableFuture.supplyAsync(() -> {
            purchaseOrder.setPoRequestDocumentName(deliveryAddress.getData().getPoRequestDocumentName());
            purchaseOrderRepository.save(purchaseOrder);
            return new ServiceOutcome<>(deliveryAddress.getData(), true, "Purchase Order Generated Successfully!!!");
        });
    }
    private CompletableFuture<ServiceOutcome<DeliveryAddress>> getDeliveryAddressAsync(PurchaseOrder purchaseOrder) {
        log.info("==========================getDeliveryAddressAsync=======================");
        try {
            if (purchaseOrder.getSalesOrderId() != null && purchaseOrder.getVendorDelivery()) {
                String token = AccessTokenUtilities.getOtherwaytoFindAccessToken();
                String url = fileConfigUtility.getProperty("api-gateway.ip") + "/salesorder/api/salesorder/getSalesOrderDeliveryAddressOnSalesOrder?salesOrderId={salesOrderId}";
                Mono<ServiceOutcome<DeliveryAddress>> monoResult = webClientBuilder.build()
                    .get()
                    .uri(url, purchaseOrder.getSalesOrderId())
                    .header("Authorization", "Bearer " + token)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<>() {});
                // Convert Mono to CompletableFuture
                return CompletableFuture.supplyAsync(() -> {
                    try {
                        return monoResult.toFuture().get();
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to get delivery address: " + e.getMessage());
                    }
                });
            } else {
                return CompletableFuture.completedFuture(new ServiceOutcome<>(null, true, ""));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return CompletableFuture.completedFuture(new ServiceOutcome<>(null, false, "Failed to get delivery address: " + e.getMessage()));
        }
    }
    private CompletableFuture<ServiceOutcome<DeliveryAddress>> generatePdfDocument(String poNumber, PurchaseOrder purchaseOrder,
                                                                                   VendorMaster vendorMaster, List<PurchaseOrderItems> purchaseOrderItemsList,
                                                                                   ServiceOutcome<DeliveryAddress> deliveryAddressOutcome) {
        log.info("==========================generatePdfDocument=======================");
        return CompletableFuture.supplyAsync(() -> {
            try {
                log.info("======================Within try===============================");
                if (deliveryAddressOutcome.getOutcome()) {
                    log.info("==================Outcome is true========================");
                    String fileName = "PO_" + poNumber + ".pdf";
                    log.info("==================fileName========================"+fileName);

                    String qrCodeName = poNumber;
                    byte[] qrCodeBytes = new CommonPDFStubs().generateQRCodeInAmazon(qrCodeName);
                    // Add QR code to the document
                    Image qrCodeImage = Image.getInstance(qrCodeBytes);

                    Document document = new Document(PageSize.A4);
                    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileUploadConfigProperties.getPurchaseOrderDocuments().getLocation()+"/" + fileName));
                    document.open();
                    new CommonPDFStubs().addQrCodeByteArrayOnFooter(qrCodeBytes, writer);
                    new CommonPDFStubs().addPageNumber(writer);
                    document.add(PurchaseOrderTableBuilder.createPurchaseOrderMainBodyTitle());
                    document.add(PurchaseOrderTableBuilder.createPurchaseOrderMainBodyPurchaseDetails(purchaseOrder));
                    document.add(new Paragraph("\n\n\n"));
                    document.add(PurchaseOrderTableBuilder.createPurchaseOrderMainBodyVendorDetails(vendorMaster));
                    if (purchaseOrder.getSalesOrderId() != null)
                        document.add(PurchaseOrderTableBuilder.createPurchaseOrderMainBodyVendorDetailsShipto(deliveryAddressOutcome.getData()));
                    else
                        document.add(PurchaseOrderTableBuilder.createPurchaseOrderMainBodyVendorDetailsShipto(deliveryAddressOutcome.getData()));
                    document.add(PurchaseOrderTableBuilder.createPurchaseOrderMainBodyItemOrdered());
                    document.add(PurchaseOrderTableBuilder.createPurchaseOrderMainHeaderItemOrderedDetails());
                    document.add(PurchaseOrderTableBuilder.createPurchaseOrderMainBodyItemOrderedDetails(purchaseOrderItemsList));

                    document.add(new Paragraph("\n"));
                    document.add(PurchaseOrderTableBuilder.createPurchaseOrderMainHeaderNote());

                    AtomicReference<Double> total = new AtomicReference<>(0.0);
                    purchaseOrderItemsList.stream().forEach(item -> {
                        total.set(total.get() + item.getTotalAmount());
                    });
                    document.add(PurchaseOrderTableBuilder.createPurchaseOrderMainBodyTotalExtendedAmt(total));
                    document.add(PurchaseOrderTableBuilder.createPurchaseOrderMainBodyFreight(purchaseOrder));
                    document.add(PurchaseOrderTableBuilder.createPurchaseOrderMainBodyNotes());

                    document.add(new Paragraph("\n\n\n\n\n"));
                    document.add(PurchaseOrderTableBuilder.createPurchaseOrderMainBodySignatureDate());
                    document.close();
                    deliveryAddressOutcome.getData().setPoRequestDocumentName(fileName);
                    log.info("========================Purchase Order Generated Successfully!==========================");
                    return new ServiceOutcome<>(deliveryAddressOutcome.getData(), true, "Purchase Order Generated Successfully!");
                } else {
                    log.info("========================Failed to get delivery address==========================");
                    return new ServiceOutcome<>(null, false, "Failed to get delivery address: " + deliveryAddressOutcome.getMessage());
                }
            } catch (Exception e) {
                e.printStackTrace();
                return new ServiceOutcome<>(null, false, "Failed to generate PDF: " + e.getMessage());
            }
        });
    }

    @Override
    @Async(AsyncConfigurationSetup.TASK_EXECUTOR_SERVICE)
    public CompletableFuture<ServiceOutcome<PurchaseOrderDTO>> getPurchaseOrderDetailsOnPONumber(String poNumber){
        return CompletableFuture.supplyAsync(() -> {
            try {
                PurchaseOrder purchaseOrder = purchaseOrderRepository.getPurchaseOrderDetailsOnPoNumber(poNumber);
                if(purchaseOrder != null)
                    return new ServiceOutcome<>(purchaseOrderMapper.toDto(purchaseOrder), true, "Purchase order details fetched successfully!");
                else
                    return new ServiceOutcome<>(null, false, "Purchase order not exists!");
            }
            catch (Exception e){
                e.printStackTrace();
                return new ServiceOutcome<>(null, false, "Failed to fetch purchase order details!!!");
            }
        });
    }
}
