package com.sunknowledge.dme.rcm.service.impl.purchaseorder;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.sunknowledge.dme.rcm.commonutil.AccessTokenUtilities;
import com.sunknowledge.dme.rcm.documentutil.PurchaseOrderTableBuilder;
import com.sunknowledge.dme.rcm.domain.PurchaseOrder;
import com.sunknowledge.dme.rcm.domain.PurchaseOrderItems;
import com.sunknowledge.dme.rcm.domain.VendorMaster;
import com.sunknowledge.dme.rcm.repository.itemothers.VendorMasterRepositoryExtended;
import com.sunknowledge.dme.rcm.repository.purchaseorder.PurchaseOrderItemsRepo;
import com.sunknowledge.dme.rcm.repository.purchaseorder.PurchaseOrderRepo;
import com.sunknowledge.dme.rcm.service.dto.salesorder.DeliveryAddress;
import com.sunknowledge.dme.rcm.service.purchaseorder.ItemPurchaseOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import javax.persistence.EntityManager;
import java.io.FileOutputStream;
import java.util.List;
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
    private EntityManager entityManager;
    private final static String PROCEDURE_TEST = "purchaseorder1";

    @Override
    public String generatePurchaseOrderOnPoNumber(String poNumber){
        String result = "";
        try{
            DeliveryAddress deliveryAddress = new DeliveryAddress();
            Document document = new Document(PageSize.A4);
            String fileName = poNumber+".pdf";
            PurchaseOrder purchaseOrder = purchaseOrderRepository.getPurchaseOrderDetailsOnPoNumber(poNumber);
            VendorMaster vendorMaster = vendorMasterRepository.findByVendorIdAndStatusIgnoreCase(purchaseOrder.getVendorId(), "active");
            List<PurchaseOrderItems> purchaseOrderItemsList = purchaseOrderItemsRepository.getPurchaseOrderDetailsOnPo(purchaseOrder.getPoId());
            if(purchaseOrder.getSalesOrderId() != null && purchaseOrder.getVendorDelivery()){
                RestTemplate restTemplate = new RestTemplate();
                String token = AccessTokenUtilities.getOtherwaytoFindAccessToken();
//                HttpHeaders headers = new HttpHeaders();
//                headers.add("Content-Type","application/json");
//                headers.add("Accept","application/json");
//                headers.set("Authorization", "Bearer "+token);
//                HttpEntity<String> requestEntity = new HttpEntity<>(headers);
//                Map<String, Long> param = new HashMap<>();
//                param.put("salesOrderId", purchaseOrder.getSalesOrderId());
//                String url = "http://SALESORDER/api/salesorder/getSalesOrderDeliveryAddressOnSalesOrder?salesOrderId={salesOrderId}";
//                ResponseEntity<DeliveryAddress> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, DeliveryAddress.class, param);
//
//                deliveryAddress = responseEntity.getBody();

                String url = "http://localhost:8083/api/salesorder/getSalesOrderDeliveryAddressOnSalesOrder?salesOrderId={salesOrderId}";
                deliveryAddress = webClientBuilder.build()
                    .get()
                    .uri(url, purchaseOrder.getSalesOrderId())
                    .header("Authorization", "Bearer "+token)
                    .retrieve()
                    .bodyToMono(DeliveryAddress.class)
                    .toFuture().get();
            }

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:\\Project Data\\Purchase Order\\" + fileName));
            document.open();
            document.add(PurchaseOrderTableBuilder.createPurchaseOrderMainBodyTitle());
            document.add(PurchaseOrderTableBuilder.createPurchaseOrderMainBodyPurchaseDetails(purchaseOrder));
            document.add(new Paragraph("\n\n\n"));

            document.add(PurchaseOrderTableBuilder.createPurchaseOrderMainBodyVendorDetails(vendorMaster));
            if(purchaseOrder.getSalesOrderId() != null)
                document.add(PurchaseOrderTableBuilder.createPurchaseOrderMainBodyVendorDetailsShipto(deliveryAddress));
            else
                document.add(PurchaseOrderTableBuilder.createPurchaseOrderMainBodyVendorDetailsShipto(purchaseOrder));

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
            result = "Purchase Order Generated Successfully!";
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
