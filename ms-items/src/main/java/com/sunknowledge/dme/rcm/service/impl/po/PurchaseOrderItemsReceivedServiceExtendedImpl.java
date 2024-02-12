package com.sunknowledge.dme.rcm.service.impl.po;

import com.sunknowledge.dme.rcm.repository.po.PurchaseOrderItemsReceivedRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.ItemMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderItemsReceivedDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.po.ReceiptDropshipPurchaseOrderParameterDTO;
import com.sunknowledge.dme.rcm.service.items.ItemMasterServiceExtended;
import com.sunknowledge.dme.rcm.service.mapper.PurchaseOrderItemsReceivedMapper;
import com.sunknowledge.dme.rcm.service.po.PurchaseOrderItemsReceivedServiceExtended;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Primary
@Service
@Slf4j
public class PurchaseOrderItemsReceivedServiceExtendedImpl implements PurchaseOrderItemsReceivedServiceExtended {

    @Autowired
    PurchaseOrderItemsReceivedRepositoryExtended purchaseOrderItemsReceivedRepositoryExtended;
    @Autowired
    PurchaseOrderItemsReceivedMapper purchaseOrderItemsReceivedMapper;
    @Autowired
    ItemMasterServiceExtended itemMasterServiceExtended;

    @Override
    public PurchaseOrderItemsReceivedDTO save(PurchaseOrderItemsReceivedDTO purchaseOrderItemsReceivedDTO) {
        return null;
    }

    @Override
    public PurchaseOrderItemsReceivedDTO update(PurchaseOrderItemsReceivedDTO purchaseOrderItemsReceivedDTO) {
        return null;
    }

    @Override
    public Optional<PurchaseOrderItemsReceivedDTO> partialUpdate(PurchaseOrderItemsReceivedDTO purchaseOrderItemsReceivedDTO) {
        return Optional.empty();
    }

    @Override
    public Page<PurchaseOrderItemsReceivedDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<PurchaseOrderItemsReceivedDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<PurchaseOrderItemsReceivedDTO> getPurchaseOrderItemsReceivedByItemId(Long itemId) {
        return purchaseOrderItemsReceivedMapper.toDto(purchaseOrderItemsReceivedRepositoryExtended.findByItemIdAndStatusIgnoreCase(itemId, "active"));
    }

    @Override
    public List<PurchaseOrderItemsReceivedDTO> getReceivedPOItemsByPOIdAndStatus(Long poId, String status) {
        return purchaseOrderItemsReceivedMapper.toDto(purchaseOrderItemsReceivedRepositoryExtended.
            findByPoIdAndStatus(poId, status));
    }

    @Override
    public List<PurchaseOrderItemsReceivedDTO> getReceivedPOItemsByPONumberAndStatus(String poNumber, String status){
        return purchaseOrderItemsReceivedMapper.toDto(purchaseOrderItemsReceivedRepositoryExtended.
            findByPoNumberAndStatus(poNumber, status));
    }

    @Override
    public List<PurchaseOrderItemsReceivedDTO> getReceivedPOItemsByItemReceivedIDAndStatus(Long itemReceivedID, String status)
    {
        return purchaseOrderItemsReceivedMapper.toDto(purchaseOrderItemsReceivedRepositoryExtended.
            findByPoItemReceivedIdAndStatus(itemReceivedID, status));
    }

    @Override
    public ResponseDTO saveReceiptDropshipPurchaseOrder(ReceiptDropshipPurchaseOrderParameterDTO receiptDropshipPurchaseOrderParameterDTO) {
        try {
            List<String> itemList = new ArrayList<String>(Arrays.asList(
                receiptDropshipPurchaseOrderParameterDTO.getItemId().split(",")));
            List<String> recQtemQtyList = new ArrayList<String>(Arrays.asList(
                receiptDropshipPurchaseOrderParameterDTO.getReceivedqty().split(",")));
            List<String> whetherSerialisedList = new ArrayList<String>(Arrays.asList(
                receiptDropshipPurchaseOrderParameterDTO.getWhetherSerialised().split(",")));
            List<String> serialNosPipeLineList = new ArrayList<String>(Arrays.asList(
                receiptDropshipPurchaseOrderParameterDTO.getSerialnos().split("\\|")));
            List<String> lotNosPipeLineList = new ArrayList<String>(Arrays.asList(
                receiptDropshipPurchaseOrderParameterDTO.getLotnos().split("\\|")));
            List<String> mfgdatesPipeLineList = new ArrayList<String>(Arrays.asList(
                receiptDropshipPurchaseOrderParameterDTO.getMfgdates().split("\\|")));

            List<ItemMasterDTO> itemMasterDTOS = itemMasterServiceExtended.findByItemIdIn(itemList.stream()
                .map(Long::parseLong)
                .collect(Collectors.toList()));
            boolean isCorrectDataSet = true;
            if(itemList.size() == itemMasterDTOS.size()) {
                if (itemList.size() == recQtemQtyList.size()) {
                    if(recQtemQtyList.size() == whetherSerialisedList.size()){
                        for(int i=0; i<itemList.size(); i++){
                            if(whetherSerialisedList.get(i).equalsIgnoreCase("Y")){
                                List<String> serialNosList = new ArrayList<String>(Arrays.asList(
                                    serialNosPipeLineList.get(i).split(",")));
                                List<String> lotNosList = new ArrayList<String>(Arrays.asList(
                                    lotNosPipeLineList.get(i).split(",")));
                                List<String> mfgdatesList = new ArrayList<String>(Arrays.asList(
                                    mfgdatesPipeLineList.get(i).split(",")));
                                if(Long.valueOf(recQtemQtyList.get(i)) != serialNosList.size() ||
                                    Long.valueOf(recQtemQtyList.get(i)) != lotNosList.size() ||
                                    Long.valueOf(recQtemQtyList.get(i)) != mfgdatesList.size()){
                                    isCorrectDataSet = false;
                                    break;
                                }else{
                                    log.info("Wrong Data set.");
                                }
                            }else{
                                log.info("Skipped Data set.");
                            }
                        }
                        if(isCorrectDataSet){
                            String pono = receiptDropshipPurchaseOrderParameterDTO.getPono();
                            String itemid = receiptDropshipPurchaseOrderParameterDTO.getItemId();
                            String receivedqty = receiptDropshipPurchaseOrderParameterDTO.getReceivedqty();
                            String whetherserialised = receiptDropshipPurchaseOrderParameterDTO.getWhetherSerialised();
                            String serialnos = receiptDropshipPurchaseOrderParameterDTO.getSerialnos();
                            String lotnos = receiptDropshipPurchaseOrderParameterDTO.getLotnos();
                            String mfgdates = receiptDropshipPurchaseOrderParameterDTO.getMfgdates();
                            LocalDate receiveddate = receiptDropshipPurchaseOrderParameterDTO.getReceiveddate();
                            String note = receiptDropshipPurchaseOrderParameterDTO.getNote();
                            Long uid = 1l;
                            String uname = "Abhijit Chowdhury";
                            Object obj = purchaseOrderItemsReceivedRepositoryExtended.saveReceiptDropshipPurchaseOrder(pono,itemid,receivedqty,whetherserialised,
                                serialnos,lotnos,mfgdates,receiveddate,note,uid,uname);
                            System.out.println("Correct Data set.");
                            return new ResponseDTO(true,
                                "Data Saved Successfully.",
                                List.of(obj), 200);
                        }else{
                            return new ResponseDTO(false,
                                "Data Not Saved.",
                                 new ArrayList<>(), 200);
                        }
                    }else{
                        log.info("Wrong Data set.");
                        return new ResponseDTO(false,
                            "Data Not Saved.",
                            new ArrayList<>(), 200);
                    }
                } else {
                    return new ResponseDTO(false,
                        "Item_Ids and Received_Item_Quantity should be same no of count.",
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
        //return null;
    }
}
