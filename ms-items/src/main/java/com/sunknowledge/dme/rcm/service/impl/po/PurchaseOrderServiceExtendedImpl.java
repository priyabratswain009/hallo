package com.sunknowledge.dme.rcm.service.impl.po;

import com.sunknowledge.dme.rcm.domain.PurchaseOrder;
import com.sunknowledge.dme.rcm.repository.po.PurchaseOrderRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.ItemItemlocationMapDTO;
import com.sunknowledge.dme.rcm.service.dto.ItemMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.ItemVendorMappingDTO;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderDTO;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderItemsDTO;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderItemsReceivedDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.po.CancelPartialPurchaseOrderParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.po.DropshipPurchaseOrderParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.po.PurchaseOrderParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.po.ReceiptDropshipPurchaseOrderParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.po.ReceivePurchaseOrderParameterDTO;
import com.sunknowledge.dme.rcm.service.items.ItemItemlocationMapServiceExtended;
import com.sunknowledge.dme.rcm.service.items.ItemMasterServiceExtended;
import com.sunknowledge.dme.rcm.service.items.ItemVendorMappingServiceExtended;
import com.sunknowledge.dme.rcm.service.mapper.PurchaseOrderMapper;
import com.sunknowledge.dme.rcm.service.po.PurchaseOrderItemsReceivedServiceExtended;
import com.sunknowledge.dme.rcm.service.po.PurchaseOrderItemsServiceExtended;
import com.sunknowledge.dme.rcm.service.po.PurchaseOrderServiceExtended;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Primary
@Service
public class PurchaseOrderServiceExtendedImpl<T> implements PurchaseOrderServiceExtended {

    private final Logger log = LoggerFactory.getLogger(PurchaseOrderServiceExtendedImpl.class);

    @Autowired
    PurchaseOrderRepositoryExtended purchaseOrderRepositoryExtended;
    @Autowired
    PurchaseOrderItemsServiceExtended purchaseOrderItemsServiceExtended;
    @Autowired
    PurchaseOrderItemsReceivedServiceExtended purchaseOrderItemsReceivedServiceExtended;
    @Autowired
    PurchaseOrderMapper purchaseOrderMapper;
    @Autowired
    ItemItemlocationMapServiceExtended itemItemlocationMapServiceExtended;
    @Autowired
    ItemVendorMappingServiceExtended itemVendorMappingServiceExtended;
    @Autowired
    ItemMasterServiceExtended itemMasterServiceExtended;
    @Override
    public PurchaseOrderDTO save(PurchaseOrderDTO purchaseOrderDTO) {
        return null;
    }

    @Override
    public PurchaseOrderDTO update(PurchaseOrderDTO purchaseOrderDTO) {
        return null;
    }

    @Override
    public Optional<PurchaseOrderDTO> partialUpdate(PurchaseOrderDTO purchaseOrderDTO) {
        return Optional.empty();
    }

    @Override
    public Page<PurchaseOrderDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<PurchaseOrderDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<PurchaseOrderDTO> getPOByPONumber(String poNumber) {
        return purchaseOrderMapper.toDto(purchaseOrderRepositoryExtended.findByPoNumberAndStatusIgnoreCase(poNumber, "active"));
    }

    public List<Map> getVendorWiseItems(String itemNo, String itemName, Long vendorId) {
        return purchaseOrderRepositoryExtended.findByItemNoOrItemNameOrVendorId(itemNo, itemName, vendorId);
    }

    public ResponseDTO savePurchaseOrder(PurchaseOrderParameterDTO purchaseOrderParameterDTO) {
        try {
            List<String> itemList = new ArrayList<String>(Arrays.asList(
                purchaseOrderParameterDTO.getItemIds().split(",")));
            List<ItemMasterDTO> itemMasterDTOS = itemMasterServiceExtended.findByItemIdIn(itemList.stream()
                .map(Long::parseLong)
                .collect(Collectors.toList()));
            List<ItemItemlocationMapDTO> itemItemlocationMapDTO = itemItemlocationMapServiceExtended.getAll().stream()
                .filter(x -> x.getStatus().equalsIgnoreCase("active")
                    && itemList.contains(x.getItemId().toString())
                    && x.getItemLocationId().equals(purchaseOrderParameterDTO.getItemLocationId())).collect(Collectors.toList());

            List<ItemVendorMappingDTO> itemVendorMappingDTO = itemVendorMappingServiceExtended.getItemVendorMapByStatus("active")
                .stream()
                .filter(x -> itemList.contains(x.getItemId().toString())
                    && x.getVendorId().equals(purchaseOrderParameterDTO.getVendorId())).collect(Collectors.toList());
            if(itemList.size() == itemMasterDTOS.size()) {
                if (itemVendorMappingDTO.size() > 0) {
                    if (itemItemlocationMapDTO.size() > 0) {
                        Long itemLocationId = purchaseOrderParameterDTO.getItemLocationId();
                        Long soId = purchaseOrderParameterDTO.getSoId();
                        Long vendorId = purchaseOrderParameterDTO.getVendorId();
                        String billingAddressLine1 = purchaseOrderParameterDTO.getBillingAddressLine1();
                        String billingAddressLine2 = purchaseOrderParameterDTO.getBillingAddressLine2();
                        String billingAddressCity = purchaseOrderParameterDTO.getBillingAddressCity();
                        String billingAddressState = purchaseOrderParameterDTO.getBillingAddressState();
                        String billingAddressZip = purchaseOrderParameterDTO.getBillingAddressZip();
                        String billingContactNo = purchaseOrderParameterDTO.getBillingContactNo();
                        String billingContactName = purchaseOrderParameterDTO.getBillingContactName();
                        String billingContactEmail = purchaseOrderParameterDTO.getBillingContactEmail();
                        String deliveryAddressLine1 = purchaseOrderParameterDTO.getDeliveryAddressLine1();
                        String deliveryAddressLine2 = purchaseOrderParameterDTO.getDeliveryAddressLine2();
                        String deliveryAddressCity = purchaseOrderParameterDTO.getDeliveryAddressCity();
                        String deliveryAddressState = purchaseOrderParameterDTO.getDeliveryAddressState();
                        String deliveryAddressZip = purchaseOrderParameterDTO.getDeliveryAddressZip();
                        String deliveryContactNo = purchaseOrderParameterDTO.getDeliveryContactNo();
                        String deliveryContactName = purchaseOrderParameterDTO.getDeliveryContactName();
                        String deliveryContactEmail = purchaseOrderParameterDTO.getDeliveryContactEmail();
                        String itemIds = purchaseOrderParameterDTO.getItemIds();
                        String itemQtys = purchaseOrderParameterDTO.getItemQtys();
                        String itemprices = purchaseOrderParameterDTO.getItemprices();
                        String whetherSerialised = purchaseOrderParameterDTO.getWhetherSerialised();
                        //Boolean vendorDelivery = purchaseOrderParameterDTO.getVendorDelivery();
                        String notes = purchaseOrderParameterDTO.getNotes();
                        String branchName = purchaseOrderParameterDTO.getBranchName();
                        Long branchId = purchaseOrderParameterDTO.getBranchId();
                        Long userId = 1L;  // Get Login User ID
                        String userName = "Abhijit Chowdhury"; // Get Login User Name
                        purchaseOrderRepositoryExtended.savePurchaseOrder(itemLocationId, soId, vendorId, branchId, branchName, billingAddressLine1,
                            billingAddressLine2, billingAddressCity, billingAddressState, billingAddressZip, billingContactNo,
                            billingContactName, billingContactEmail, deliveryAddressLine1, deliveryAddressLine2, deliveryAddressCity,
                            deliveryAddressState, deliveryAddressZip, deliveryContactNo, deliveryContactName, deliveryContactEmail,
                            itemIds, itemQtys, itemprices, whetherSerialised, userId, userName, notes);
                        return new ResponseDTO(true, "Successfully Saved", new ArrayList(), 200);
                    } else {
                        return new ResponseDTO(false,
                            "This location/branch does not have the given item(s)! Kindly add given item(s) to this location/branch.",
                            new ArrayList(), 200);
                    }
                } else {
                    return new ResponseDTO(false,
                        "This vendor does not have the given item(s)! Kindly add given item(s) to this vendor.",
                        new ArrayList(), 200);
                }
            }else{
                return new ResponseDTO(false,
                    "This item(s) does not exist!",
                    new ArrayList(), 200);
            }
        } catch (Exception e) {
            log.error("==========> Exception=" + e);
            return new ResponseDTO(false, "Failed to Save :: Data Error", new ArrayList(), 200);
        }
    }

    public ResponseDTO cancelFullPurchaseOrder(Long poId) {
        try {
            Long userId = 1L;  // Get Login User ID
            String userName = "Abhijit Chowdhury"; // Get Login User Name
            purchaseOrderRepositoryExtended.cancelFullPurchaseOrder(poId, userId, userName);
            return new ResponseDTO(true, "Successfully Cancelled", new ArrayList(), 200);
        } catch (Exception e) {
            log.error("==========> Exception=" + e);
            return new ResponseDTO(false, "Failed to Cancel :: Data Error", new ArrayList(), 200);
        }
    }

    public ResponseDTO cancelPartialPurchaseOrder(CancelPartialPurchaseOrderParameterDTO cancelPartialPurchaseOrderParameterDTO) {
        try {
            Long poId = cancelPartialPurchaseOrderParameterDTO.getPoid();
            String itemIds = cancelPartialPurchaseOrderParameterDTO.getItemids();
            List<Long> itemList = Arrays.asList(itemIds.split(",")).stream()
                .map(x -> Long.valueOf(x)).collect(Collectors.toList());
            String itemListArr[] = itemIds.split(",");
            String itemOrderQtys = cancelPartialPurchaseOrderParameterDTO.getItemorderqtys();
            String itemCancelQtys = cancelPartialPurchaseOrderParameterDTO.getItemcancelqtys();
            String itemCancelList[] = itemCancelQtys.split(",");
            List<PurchaseOrderItemsDTO> purchaseOrderItemsList = purchaseOrderItemsServiceExtended.findByPoIdAndItemIdIn(poId, itemList);

            int count = 0;
            for (int i = 0; i < itemListArr.length; i++) {
                for (int j = 0; j < purchaseOrderItemsList.size(); j++) {

                    if (Long.valueOf(itemListArr[i])
                        .compareTo(purchaseOrderItemsList.get(j).getItemId()) == 0
                        && Long.valueOf(itemCancelList[i])
                        .compareTo(purchaseOrderItemsList.get(j).getOrderedQty() -
                            purchaseOrderItemsList.get(j).getReceivedQty()) <= 0) {

                        count++;
                    }
                }
            }

            Long userId = 1L;  // Get Login User ID
            String userName = "Abhijit Chowdhury"; // Get Login User Name
            if (count == itemList.size()) {
                purchaseOrderRepositoryExtended.cancelPartialPurchaseOrder(poId, itemIds, itemOrderQtys,
                    itemCancelQtys, userId, userName);
                return new ResponseDTO(true, "Successfully Cancelled", new ArrayList(), 200);
            } else {
                return new ResponseDTO(false, "Cancel_Quantity must be lesser than and equals to (Ordered_Qty - Received_Qty).", new ArrayList(), 200);
            }
        } catch (Exception e) {
            log.error("==========> Exception=" + e);
            return new ResponseDTO(false, "Failed to Cancel :: Data Error", new ArrayList(), 200);
        }
    }

    public ResponseDTO receivePurchaseOrder(ReceivePurchaseOrderParameterDTO receivePurchaseOrderParameterDTO) {
        try {
            Long poId = receivePurchaseOrderParameterDTO.getPoid();
            Long locationId = receivePurchaseOrderParameterDTO.getLocationid();
            String itemIds = receivePurchaseOrderParameterDTO.getItemids();
            String itemIdsArr[] = itemIds.split(",");
            String receivedQtys = receivePurchaseOrderParameterDTO.getReceivedqtys();
            String receivedQtysArr[] = receivedQtys.split(",");
            String whetherSerialised = receivePurchaseOrderParameterDTO.getWhetherserialised();
            String whetherSerialisedArr[] = whetherSerialised.split(",");
            String serialNos = receivePurchaseOrderParameterDTO.getSerialnos();
            String serialNosArr[] = serialNos.split("\\|");
            String lotNos = receivePurchaseOrderParameterDTO.getLotnos();
            String lotNosArr[] = lotNos.split("\\|");
            String mfgDates = receivePurchaseOrderParameterDTO.getMfgdates();
            String mfgDatesArr[] = mfgDates.split("\\|");
            LocalDate receivedDate = receivePurchaseOrderParameterDTO.getReceiveddate();

            List<PurchaseOrderItemsDTO> purchaseOrderItemsList = purchaseOrderItemsServiceExtended
                .findByPoIdAndItemIdIn(poId, Arrays.asList(itemIdsArr).stream()
                    .map(x -> Long.valueOf(x)).collect(Collectors.toList()));

            int count = 0;
            for (int i = 0; i < itemIdsArr.length; i++) {
                for (int j = 0; j < purchaseOrderItemsList.size(); j++) {

                    if (Long.valueOf(itemIdsArr[i])
                        .compareTo(purchaseOrderItemsList.get(j).getItemId()) == 0
                        && Long.valueOf(receivedQtysArr[i])
                        .compareTo(purchaseOrderItemsList.get(j).getOrderedQty() -
                            purchaseOrderItemsList.get(j).getCancelledQty()) <= 0) {
                        count++;
                    }
                }
            }

            Long userId = 1L;  // Get Login User ID
            String userName = "Abhijit Chowdhury"; // Get Login User Name
            if (itemIdsArr.length == receivedQtysArr.length &&
                itemIdsArr.length == whetherSerialisedArr.length &&
                itemIdsArr.length == serialNosArr.length &&
                itemIdsArr.length == lotNosArr.length &&
                itemIdsArr.length == mfgDatesArr.length) {
                if (count == itemIdsArr.length) {
                    purchaseOrderRepositoryExtended.receivePurchaseOrder(poId, locationId, itemIds,
                        receivedQtys, whetherSerialised, serialNos, lotNos, mfgDates,
                        receivedDate, userId, userName);
                    return new ResponseDTO(true, "Successfully Received", new ArrayList(), 200);
                } else {
                    return new ResponseDTO(false, "Received_Quantity must be lesser than and equals to (Ordered_Qty - Canceled_Qty).", new ArrayList(), 200);
                }
            } else {
                return new ResponseDTO(false, "Count_Of_Items, Count_Of_Whether_Serialised, Count_Of_Serial_Nos, Count_Of_Lot_Nos and Count_Of_Mfg_Dates must be same.", new ArrayList(), 200);
            }
        } catch (Exception e) {
            log.error("==========> Exception=" + e);
            return new ResponseDTO(false, "Failed to Receive :: Data Error", new ArrayList(), 200);
        }
    }

    public ResponseDTO getPurchaseOrderData(String param, String opType) {
        try {
            if (opType.equals("POByPoId")) {
                PurchaseOrderDTO dataObj = purchaseOrderRepositoryExtended.findById(Long.valueOf(param)).isPresent() ?
                    purchaseOrderMapper.toDto(purchaseOrderRepositoryExtended.findById(Long.valueOf(param)).get())
                    : new PurchaseOrderDTO();
                List<PurchaseOrderDTO> listPO = new ArrayList<>();
                listPO.add(dataObj);
                List<T> list = (List<T>) listPO;
                return new ResponseDTO((list.size() > 0) ? true : false,
                    (list.size() > 0) ? "Data Successfully Fetched" : "No Data Available",
                    list, 200);
            } else if (opType.equals("POByPoNumber")) {
                List<PurchaseOrderDTO> dataObj = purchaseOrderMapper.toDto(
                    purchaseOrderRepositoryExtended.findByPoNumberAndStatusIgnoreCase(param, "active"));
                List<T> list = (List<T>) dataObj;
                return new ResponseDTO((list.size() > 0) ? true : false,
                    (list.size() > 0) ? "Data Successfully Fetched" : "No Data Available",
                    list, 200);
            } else if (opType.equals("POByFromDateAndToDateOrPOStatus")) {
                String params[] = param.split(",");

                if (params.length == 3) {
                    LocalDate fromDate = LocalDate.parse(params[0]);
                    LocalDate toDate = LocalDate.parse(params[1]);
                    String poStatus = params[2];
                    List<Map> dataObj = purchaseOrderRepositoryExtended.findByDateAndPOStatus(fromDate, toDate, poStatus);
                    System.out.println("===>> dataObj :: " + dataObj);
                    List<T> list = (List<T>) dataObj;
                    return new ResponseDTO((list.size() > 0) ? true : false,
                        (list.size() > 0) ? "Data Successfully Fetched" : "No Data Available",
                        list, 200);
                } else {
                    return new ResponseDTO(false, "Proper From_Date(yyyy-mm-dd), To_Date(yyyy-mm-dd) and PO_Status must be provided.",
                        new ArrayList(), 200);
                }
            } else if (opType.equals("POByVendorId")) {
                List<PurchaseOrderDTO> dataObj = purchaseOrderMapper.toDto(
                    purchaseOrderRepositoryExtended.findByVendorIdAndStatusIgnoreCase(Long.valueOf(param),
                        "active"));
                List<T> list = (List<T>) dataObj;
                return new ResponseDTO((list.size() > 0) ? true : false,
                    (list.size() > 0) ? "Data Successfully Fetched" : "No Data Available",
                    list, 200);
            } else if (opType.equals("POItemsByPOId")) {
                List<PurchaseOrderItemsDTO> dataObj = purchaseOrderItemsServiceExtended
                    .getItemsByPOIdAndStatus(Long.valueOf(param), "active");
                List<T> list = (List<T>) dataObj;
                return new ResponseDTO((list.size() > 0) ? true : false,
                    (list.size() > 0) ? "Data Successfully Fetched" : "No Data Available",
                    list, 200);
            } else if (opType.equals("POItemsByPONumber")) {
                List<PurchaseOrderItemsDTO> dataObj = purchaseOrderItemsServiceExtended
                    .getItemsByPoNumberAndStatus(param, "active");
                List<T> list = (List<T>) dataObj;
                return new ResponseDTO((list.size() > 0) ? true : false,
                    (list.size() > 0) ? "Data Successfully Fetched" : "No Data Available",
                    list, 200);
            } else if (opType.equals("POItemsByPOItemID")) {
                List<PurchaseOrderItemsDTO> dataObj = purchaseOrderItemsServiceExtended
                    .getItemsByPOItemsIDAndStatus(Long.valueOf(param), "active");
                List<T> list = (List<T>) dataObj;
                return new ResponseDTO((list.size() > 0) ? true : false,
                    (list.size() > 0) ? "Data Successfully Fetched" : "No Data Available",
                    list, 200);
            } else if (opType.equals("ReceivedPOItemsByPOId")) {
                List<PurchaseOrderItemsReceivedDTO> dataObj = purchaseOrderItemsReceivedServiceExtended
                    .getReceivedPOItemsByPOIdAndStatus(Long.valueOf(param), "active");
                List<T> list = (List<T>) dataObj;
                return new ResponseDTO((list.size() > 0) ? true : false,
                    (list.size() > 0) ? "Data Successfully Fetched" : "No Data Available",
                    list, 200);
            } else if (opType.equals("ReceivedPOItemsByPONumber")) {
                List<PurchaseOrderItemsReceivedDTO> dataObj = purchaseOrderItemsReceivedServiceExtended
                    .getReceivedPOItemsByPONumberAndStatus(param, "active");
                List<T> list = (List<T>) dataObj;
                return new ResponseDTO((list.size() > 0) ? true : false,
                    (list.size() > 0) ? "Data Successfully Fetched" : "No Data Available",
                    list, 200);
            } else if (opType.equals("ReceivedPOItemsByItemReceivedID")) {
                List<PurchaseOrderItemsReceivedDTO> dataObj = purchaseOrderItemsReceivedServiceExtended
                    .getReceivedPOItemsByItemReceivedIDAndStatus(Long.valueOf(param), "active");
                System.out.println("dataObj=" + dataObj);
                List<T> list = (List<T>) dataObj;
                return new ResponseDTO((list.size() > 0) ? true : false,
                    (list.size() > 0) ? "Data Successfully Fetched" : "No Data Available",
                    list, 200);
            } else {
                return new ResponseDTO(false,
                    "No Data Available",
                    new ArrayList<>(), 200);
            }
        } catch (Exception e) {
            log.error("==========> Exception=" + e);
            return new ResponseDTO(false,
                "Data Not Found Data :: Data Error",
                new ArrayList<>(), 200);
        }
    }

    @Override
    public ResponseDTO saveDropshipPurchaseOrder(DropshipPurchaseOrderParameterDTO dropshipPurchaseOrderParameterDTO) {
        try {
            List<ItemVendorMappingDTO> itemVendorMappingDTO = itemVendorMappingServiceExtended
                .getItemVendorMapByItemIdAndVendorId(dropshipPurchaseOrderParameterDTO.getItemId(),dropshipPurchaseOrderParameterDTO.getVendorId());
            Long countSO = purchaseOrderRepositoryExtended.checkSOExistorNot(dropshipPurchaseOrderParameterDTO.getSoId(),dropshipPurchaseOrderParameterDTO.getVendorId(),
                dropshipPurchaseOrderParameterDTO.getItemId(),dropshipPurchaseOrderParameterDTO.getItemQty());
            System.out.println("countSO "+countSO);
            System.out.println("itemVendorMappingDTO "+itemVendorMappingDTO);
            if (itemVendorMappingDTO.size() > 0) {
                if (countSO < 1) {
                    Long soId = dropshipPurchaseOrderParameterDTO.getSoId();
                    String soNo = dropshipPurchaseOrderParameterDTO.getSoNo();
                    Long vendorId = dropshipPurchaseOrderParameterDTO.getVendorId();
                    String billingAddressLine1 = dropshipPurchaseOrderParameterDTO.getBillingAddressLine1();
                    String billingAddressLine2 = dropshipPurchaseOrderParameterDTO.getBillingAddressLine2();
                    String billingAddressCity = dropshipPurchaseOrderParameterDTO.getBillingAddressCity();
                    String billingAddressState = dropshipPurchaseOrderParameterDTO.getBillingAddressState();
                    String billingAddressZip = dropshipPurchaseOrderParameterDTO.getBillingAddressZip();
                    String billingContactNo = dropshipPurchaseOrderParameterDTO.getBillingContactNo();
                    String billingContactName = dropshipPurchaseOrderParameterDTO.getBillingContactName();
                    String billingContactEmail = dropshipPurchaseOrderParameterDTO.getBillingContactEmail();
                    String deliveryAddressLine1 = dropshipPurchaseOrderParameterDTO.getDeliveryAddressLine1();
                    String deliveryAddressLine2 = dropshipPurchaseOrderParameterDTO.getDeliveryAddressLine2();
                    String deliveryAddressCity = dropshipPurchaseOrderParameterDTO.getDeliveryAddressCity();
                    String deliveryAddressState = dropshipPurchaseOrderParameterDTO.getDeliveryAddressState();
                    String deliveryAddressZip = dropshipPurchaseOrderParameterDTO.getDeliveryAddressZip();
                    String deliveryContactNo = dropshipPurchaseOrderParameterDTO.getDeliveryContactNo();
                    String deliveryContactName = dropshipPurchaseOrderParameterDTO.getDeliveryContactName();
                    String deliveryContactEmail = dropshipPurchaseOrderParameterDTO.getDeliveryContactEmail();
                    Long itemId = dropshipPurchaseOrderParameterDTO.getItemId();
                    Long itemQty = dropshipPurchaseOrderParameterDTO.getItemQty();
                    String itemprices = dropshipPurchaseOrderParameterDTO.getItemprices();
                    String whetherSerialised = dropshipPurchaseOrderParameterDTO.getWhetherSerialised();
                    String notes = dropshipPurchaseOrderParameterDTO.getNotes();
                    String branchName = dropshipPurchaseOrderParameterDTO.getBranchName();
                    Long branchId = dropshipPurchaseOrderParameterDTO.getBranchId();
                    Long locationId = dropshipPurchaseOrderParameterDTO.getLocationId();
                    Long userId = 1L;  // Get Login User ID
                    String userName = "Abhijit Chowdhury"; // Get Login User Name
                    Object obj = purchaseOrderRepositoryExtended.saveDropshipPurchaseOrder(soId,soNo, vendorId,branchId,branchName, billingAddressLine1,
                        billingAddressLine2, billingAddressCity, billingAddressState, billingAddressZip, billingContactNo,
                        billingContactName, billingContactEmail, deliveryAddressLine1, deliveryAddressLine2, deliveryAddressCity,
                        deliveryAddressState, deliveryAddressZip, deliveryContactNo, deliveryContactName, deliveryContactEmail,
                        itemId.toString(), itemQty.toString(), itemprices, whetherSerialised,userId, userName, notes,locationId);
                    return new ResponseDTO(true, "Successfully Saved", List.of(obj), 200);
                } else {
                    return new ResponseDTO(false,
                        "This SO_Id already have a Purchase_Order!.",
                        new ArrayList(), 200);
                }
            } else {
                return new ResponseDTO(false,
                    "This vendor does not have the given item(s)! Kindly add given item(s) to this vendor.",
                    new ArrayList(), 200);
            }
        } catch (Exception e) {
            log.error("==========> Exception=" + e);
            return new ResponseDTO(false, "Failed to Save :: Data Error", new ArrayList(), 200);
        }
    }

    @Override
    public ResponseDTO cancelDropshipPurchaseOrder(String poNumber) {
        try {
            List<PurchaseOrder> purchaseOrder = purchaseOrderRepositoryExtended.findByPoNumberAndStatusIgnoreCase(poNumber,"active");
            if(purchaseOrder != null && purchaseOrder.size()>0) {
                Long userId = 1L;  // Get Login User ID
                String userName = "Abhijit Chowdhury"; // Get Login User Name
                purchaseOrderRepositoryExtended.cancelDropshipPurchaseOrder(poNumber, userId, userName);
                return new ResponseDTO(true, "Successfully Cancelled", new ArrayList(), 200);
            }else {
                return new ResponseDTO(false, "Purchase_Order_Id is not exist.", new ArrayList(), 200);
            }
        } catch (Exception e) {
            log.error("==========> Exception=" + e);
            return new ResponseDTO(false, "Failed to Cancel :: Data Error", new ArrayList(), 200);
        }
    }

    @Override
    public ResponseDTO saveReceiptDropshipPurchaseOrder(ReceiptDropshipPurchaseOrderParameterDTO receiptDropshipPurchaseOrderParameterDTO) {
        List<PurchaseOrderItemsReceivedDTO> purchaseOrderItemsReceivedDTOS = purchaseOrderItemsReceivedServiceExtended
            .getReceivedPOItemsByPONumberAndStatus(receiptDropshipPurchaseOrderParameterDTO.getPono(),"active");
        if(purchaseOrderItemsReceivedDTOS.size() == 0){
            return purchaseOrderItemsReceivedServiceExtended.saveReceiptDropshipPurchaseOrder(receiptDropshipPurchaseOrderParameterDTO);
        }else{
            List<PurchaseOrder> purchaseOrders = purchaseOrderRepositoryExtended.findByPoNumberAndStatusIgnoreCase(receiptDropshipPurchaseOrderParameterDTO.getPono(),"active");
            if(!purchaseOrders.get(0).getPoStatus().equalsIgnoreCase("Completed")){
                return purchaseOrderItemsReceivedServiceExtended.saveReceiptDropshipPurchaseOrder(receiptDropshipPurchaseOrderParameterDTO);
            }else{
                log.info("PO Already Completed.");
                return new ResponseDTO<>(false,"Data not saved.",new ArrayList<>(), 200);
            }
        }


    }
}
