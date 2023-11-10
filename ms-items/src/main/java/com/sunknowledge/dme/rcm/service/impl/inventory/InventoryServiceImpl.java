package com.sunknowledge.dme.rcm.service.impl.inventory;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.ItemInventoryStatus;
import com.sunknowledge.dme.rcm.domain.ItemSerialNumber;
import com.sunknowledge.dme.rcm.domain.SoItemTransactionDetails;
import com.sunknowledge.dme.rcm.domain.StockAdjustment;
import com.sunknowledge.dme.rcm.repository.inventory.ItemInventoryStatusReposito;
import com.sunknowledge.dme.rcm.repository.inventory.ItemSerialNumberRepo;
import com.sunknowledge.dme.rcm.repository.inventory.SoItemTransactionDetailsReposi;
import com.sunknowledge.dme.rcm.repository.inventory.StockAdjustmentRepo;
import com.sunknowledge.dme.rcm.service.dto.StockAdjustmentDTO;
import com.sunknowledge.dme.rcm.service.dto.inventory.*;
import com.sunknowledge.dme.rcm.service.inventory.InventoryService;
import com.sunknowledge.dme.rcm.service.mapper.StockAdjustmentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private StockAdjustmentRepo stockAdjustmentRepository;
    @Autowired
    private StockAdjustmentMapper stockAdjustmentMapper;
    @Autowired
    private ItemInventoryStatusReposito itemInventoryStatusRepository;
    @Autowired
    private ItemSerialNumberRepo itemSerialNumberRepository;
    @Autowired
    private SoItemTransactionDetailsReposi soItemTransactionDetailsRepository;

    @Override
    public ServiceOutcome<StockAdjustmentDTO> inventoryStockAdjustment(StockAdjustmentRequestParams stockAdjustmentRequestParams){
        StockAdjustmentDTO stockAdjustmentDTO = new StockAdjustmentDTO();
        try{
//            stockAdjustmentRepository.processStockAdjustmentOnRequestedParams(stockAdjustmentRequestParams.getAdjustmentType(), stockAdjustmentRequestParams.getItemId(),
//                stockAdjustmentRequestParams.getLocationId(), stockAdjustmentRequestParams.getItemQty(),
//                stockAdjustmentRequestParams.getWhetherSerialised(), stockAdjustmentRequestParams.getSerialNos(),
//                stockAdjustmentRequestParams.getLotNos(), stockAdjustmentRequestParams.getMfgDates(),
//                stockAdjustmentRequestParams.getAvgPrice(), stockAdjustmentRequestParams.getUid(), stockAdjustmentRequestParams.getUname());
            StockAdjustment stockAdjustment = stockAdjustmentRepository.getStockAdjustmentOnLocationNitem(stockAdjustmentRequestParams.getLocationId(), stockAdjustmentRequestParams.getItemId());
            if(stockAdjustment != null){
                stockAdjustmentDTO = stockAdjustmentMapper.toDto(stockAdjustment);
                return new ServiceOutcome<>(stockAdjustmentDTO, true, "Stock gets positively adjusted successfully!");
            }
            else{
                return new ServiceOutcome<>(stockAdjustmentDTO, false, "Failure to adjust the stock!");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ServiceOutcome<>(stockAdjustmentDTO, false, "Failure to adjust the stock!");
    }

    @Override
    public StockAdjustment inventoryStockAdjustment1(StockAdjustmentRequestParams stockAdjustmentRequestParams){
        StockAdjustmentDTO stockAdjustmentDTO = new StockAdjustmentDTO();
        StockAdjustment stockAdjustment = null;
        try{
            stockAdjustmentRepository.processStockAdjustmentOnRequestedParams(stockAdjustmentRequestParams.getAdjustmentType(), stockAdjustmentRequestParams.getItemId(),
                stockAdjustmentRequestParams.getLocationId(), stockAdjustmentRequestParams.getBranchId(), stockAdjustmentRequestParams.getBranchName(),
                stockAdjustmentRequestParams.getItemQty(),
                stockAdjustmentRequestParams.getWhetherSerialised(), stockAdjustmentRequestParams.getSerialNos(),
                stockAdjustmentRequestParams.getLotNos(), stockAdjustmentRequestParams.getMfgDates(),
                stockAdjustmentRequestParams.getAvgPrice(), stockAdjustmentRequestParams.getUid(), stockAdjustmentRequestParams.getUname());
            stockAdjustment = stockAdjustmentRepository.getStockAdjustmentOnLocationNitem(stockAdjustmentRequestParams.getLocationId(), stockAdjustmentRequestParams.getItemId());
            if(stockAdjustment != null){
                log.info("===============>"+stockAdjustment.getStockAdjustmentId());
                //BeanUtils.copyProperties(stockAdjustmentDTO, stockAdjustment);
                log.info("Stock gets positively adjusted successfully!");
            }
            else{
                log.info("Failure to adjust the stock!");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("=====================END Service============================");
        return stockAdjustment;
    }

    @Override
    public ServiceOutcome<ItemInventoryStatusResponse> updateItemInventoryStatusByItemAndLocation(ItemInventoryStatusInputRequest itemInventoryStatusInputRequest){
        log.info("=====================updateItemInventoryStatusByItemAndLocation Service============================>");
        ItemInventoryStatusResponse itemInventoryStatusResponse = new ItemInventoryStatusResponse();
        AtomicReference<Boolean> status = new AtomicReference<>(false);
        ServiceOutcome<ItemInventoryStatusResponse> serviceOutcome = new ServiceOutcome<>();
        if(itemInventoryStatusInputRequest.getItemInventoryStatusInputParamsList().size() > 0) {
            itemInventoryStatusInputRequest.getItemInventoryStatusInputParamsList().stream().forEach(itemInventoryInputParams -> {
                log.info("=====================Outer============================>");
                log.info("=====>" + itemInventoryInputParams.getItemLocationId());
                log.info("=====>" + itemInventoryInputParams.getDeliveryTicketNo());
                log.info("=====>" + itemInventoryInputParams.getDeliveryTicketId());
                itemInventoryInputParams.getDeliveryItemData().stream().forEach(deliveryItem -> {
                    log.info("=====================Inner============================>");
                    log.info("=====>" + deliveryItem.getItemNumber());
                    ItemInventoryStatus itemInventoryStatus = itemInventoryStatusRepository.getItemInventoryStatusByItemLocationId(deliveryItem.getItemId(), itemInventoryInputParams.getItemLocationId());
                    if (itemInventoryStatus != null) {
                        if(itemInventoryInputParams.getServiceType().equalsIgnoreCase("SALESORDER")) {
                            itemInventoryStatus.setInorderQty(itemInventoryStatus.getInorderQty() - 1);
                            itemInventoryStatus.setComittedQty(itemInventoryStatus.getComittedQty() + 1);
                            if (itemInventoryInputParams.getDeliveryType().equalsIgnoreCase("Dropship"))
                                itemInventoryStatus.setOnBackorder(itemInventoryStatus.getOnBackorder() - deliveryItem.getItemQuantity());
                            itemInventoryStatusRepository.save(itemInventoryStatus);
                            status.set(true);
                        }
                        else if(itemInventoryInputParams.getServiceType().equalsIgnoreCase("DELIVERY")) {
                            ItemSerialNumber itemSerialNumber;
                            itemInventoryStatus.setComittedQty(itemInventoryStatus.getComittedQty() - 1);
                            itemInventoryStatus.setOnhandQty(itemInventoryStatus.getOnhandQty() - 1);
                            if(deliveryItem.getItemSaleType().equalsIgnoreCase("Rental"))
                                itemInventoryStatus.setOnrentQty(itemInventoryStatus.getOnrentQty() + 1);
                            if (itemInventoryInputParams.getDeliveryType().equalsIgnoreCase("Dropship")) {
                                itemInventoryStatus.setOnBackorder(itemInventoryStatus.getOnBackorder() - deliveryItem.getItemQuantity());
                                itemSerialNumber = saveUpdateItemSerialNumber(itemInventoryStatus, deliveryItem);
                            }
                            itemInventoryStatusRepository.save(itemInventoryStatus);
                            saveUpdateSoItemTransactionDetails(itemInventoryStatus, itemInventoryInputParams, deliveryItem, itemInventoryInputParams.getSoNumber(), deliveryItem.getItemSerialNumber());
                            status.set(true);
                        }
                    }
                });
            });
        }
        if(status.get()){
            itemInventoryStatusResponse.setItemInventoryStatusInputParamsList(itemInventoryStatusInputRequest.getItemInventoryStatusInputParamsList());
            serviceOutcome.setOutcome(true);
            serviceOutcome.setMessage("Successfully created the delivery ticket and updated item inventory status!!!");
            serviceOutcome.setData(itemInventoryStatusResponse);
        }
        else{
            serviceOutcome.setOutcome(false);
            serviceOutcome.setMessage("Failed to create the delivery ticket!!!");
            serviceOutcome.setData(itemInventoryStatusResponse);
        }
        return serviceOutcome;
    }

    public ItemSerialNumber saveUpdateItemSerialNumber(ItemInventoryStatus itemInventoryStatus, DeliveryItemData deliveryItem){
        ItemSerialNumber itemSerialNumber = itemSerialNumberRepository.getItemSerialNumberOnItemNSerialNo(itemInventoryStatus.getItemId(), deliveryItem.getItemSerialNumber());
        if(itemSerialNumber != null){
            itemSerialNumber.setSerialNumber(deliveryItem.getItemSerialNumber());
            itemSerialNumber.setOnHandStatus("Unavailable");
            itemSerialNumber.setUpdatedById(0L);
            itemSerialNumber.setUpdatedByName("Bimal");
            itemSerialNumber.setUpdatedDate(LocalDate.now());
            itemSerialNumber = itemSerialNumberRepository.save(itemSerialNumber);
        }
        else{
            ItemSerialNumber saveItemSerialNumber = new ItemSerialNumber();
            saveItemSerialNumber.setItemId(itemInventoryStatus.getItemId());
            saveItemSerialNumber.setLocationId(itemInventoryStatus.getItemLocationId());
            saveItemSerialNumber.serialNumber(deliveryItem.getItemSerialNumber());
            saveItemSerialNumber.setAssetNumber("");
            saveItemSerialNumber.setOnHandStatus("Unavailable");
            saveItemSerialNumber.setStatus("active");
            saveItemSerialNumber.setCreatedById(0L);
            saveItemSerialNumber.setCreatedByName("Bimal");
            saveItemSerialNumber.setCreatedDate(LocalDate.now());
            saveItemSerialNumber.setItemNo(itemInventoryStatus.getItemNo());
            saveItemSerialNumber.setItemName(itemInventoryStatus.getItemName());
            saveItemSerialNumber.setLocationName(itemInventoryStatus.getItemLocationName());
            saveItemSerialNumber.setPoNo(deliveryItem.getPoNumber());
            saveItemSerialNumber.setIsDropship(deliveryItem.getIsDropship());
            saveItemSerialNumber = itemSerialNumberRepository.save(saveItemSerialNumber);
            itemSerialNumber = saveItemSerialNumber;
        }
        return itemSerialNumber;
    }

    public void saveUpdateSoItemTransactionDetails(ItemInventoryStatus itemInventoryStatus, ItemInventoryStatusInputParams itemInventoryInputParams, DeliveryItemData deliveryItem, String salesOrderNo, String serialNumber){
        SoItemTransactionDetails soItemTransactionDetails = soItemTransactionDetailsRepository.getItemTransactionDetailsOnSalesOrderNItemNSerialNumber(salesOrderNo, itemInventoryStatus.getItemId(), serialNumber);
        if(soItemTransactionDetails != null){
            soItemTransactionDetails.setUpdatedById(0L);
            soItemTransactionDetails.setUpdatedByName("Bimal");
            soItemTransactionDetails.setUpdatedDate(LocalDate.now());
            soItemTransactionDetailsRepository.save(soItemTransactionDetails);
        }
        else{
            SoItemTransactionDetails saveSoItemTransactionDetails = new SoItemTransactionDetails();
            saveSoItemTransactionDetails.setSalesOrderNo(salesOrderNo);
            saveSoItemTransactionDetails.setSaleType(deliveryItem.getItemSaleType());
            saveSoItemTransactionDetails.setItemId(deliveryItem.getItemId());
            saveSoItemTransactionDetails.setItemNo(deliveryItem.getItemNumber());
            saveSoItemTransactionDetails.setItemName(deliveryItem.getItemName());
            saveSoItemTransactionDetails.setItemUom("");
            saveSoItemTransactionDetails.setItemQty(Long.valueOf(deliveryItem.getItemQuantity()));
            if(itemInventoryStatus != null)
                saveSoItemTransactionDetails.setWheatherSerialized(itemInventoryStatus.getWhetherSerialised());
            else
                saveSoItemTransactionDetails.setWheatherSerialized("");
            saveSoItemTransactionDetails.setSerialNo(serialNumber);
            if(itemInventoryStatus != null)
                saveSoItemTransactionDetails.setWheatherAssetTagged(itemInventoryStatus.getWhetherAssetTagged());
            else
                saveSoItemTransactionDetails.setWheatherAssetTagged("");
//            saveSoItemTransactionDetails.setAssetNo();
//            saveSoItemTransactionDetails.setOriginalDos();
//            saveSoItemTransactionDetails.setBranchId();
            saveSoItemTransactionDetails.setLocationId(itemInventoryInputParams.getItemLocationId());
            if(itemInventoryStatus != null)
                saveSoItemTransactionDetails.setLocationName(itemInventoryStatus.getItemLocationName());
            else
                saveSoItemTransactionDetails.setLocationName("");
            saveSoItemTransactionDetails.setStatus("active");
            saveSoItemTransactionDetails.setTransactionDate(LocalDate.now());
            saveSoItemTransactionDetails.setTransactionNo(soItemTransactionDetailsRepository.getTransactionNumber());
            saveSoItemTransactionDetails.setCreatedById(0L);
            saveSoItemTransactionDetails.setCreatedByName("Bimal");
            saveSoItemTransactionDetails.setCreatedDate(LocalDate.now());
            saveSoItemTransactionDetails.isDropship(deliveryItem.getIsDropship());
            saveSoItemTransactionDetails.setPoNo(deliveryItem.getPoNumber());;
            saveSoItemTransactionDetails.setItemTransactionStatus("Delivered");
            soItemTransactionDetailsRepository.save(saveSoItemTransactionDetails);
        }
    }
}
