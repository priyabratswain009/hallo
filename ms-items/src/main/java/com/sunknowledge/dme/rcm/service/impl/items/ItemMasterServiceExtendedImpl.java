package com.sunknowledge.dme.rcm.service.impl.items;

import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.ItemMaster;
import com.sunknowledge.dme.rcm.repository.items.ItemMasterRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.ItemGroupDTO;
import com.sunknowledge.dme.rcm.service.dto.ItemMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.items.ItemMasterParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.items.ItemMasterOutputDTO;
import com.sunknowledge.dme.rcm.service.dto.items.ItemMasterRejectedDTO;
import com.sunknowledge.dme.rcm.service.dto.items.ItemMasterResponseDTO;
import com.sunknowledge.dme.rcm.service.helper.items.ItemMasterServiceImplHelper;
import com.sunknowledge.dme.rcm.service.items.ItemMasterServiceExtended;
import com.sunknowledge.dme.rcm.service.items.ItemProcedureCodeMapServiceExtended;
import com.sunknowledge.dme.rcm.service.items.ItemVendorMappingServiceExtended;
import com.sunknowledge.dme.rcm.service.mapper.ItemMasterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Primary
@Service
public class ItemMasterServiceExtendedImpl implements ItemMasterServiceExtended {
    private final Logger log = LoggerFactory.getLogger(ItemMasterServiceExtendedImpl.class);
    @Autowired
    ItemMasterServiceImplHelper itemMasterServiceImplHelper;
    @Autowired
    ItemMasterRepositoryExtended itemMasterRepositoryExtended;
    @Autowired
    ItemMasterMapper itemMasterMapper;
    @Autowired
    ItemProcedureCodeMapServiceExtended itemProcedureCodeMapServiceExtended;



    @Override
    public ItemMasterDTO save(ItemMasterDTO itemMasterDTO) {
        return null;
    }

    @Override
    public ItemMasterDTO update(ItemMasterDTO itemMasterDTO) {
        return null;
    }

    @Override
    public Optional<ItemMasterDTO> partialUpdate(ItemMasterDTO itemMasterDTO) {
        return Optional.empty();
    }

    @Override
    public Page<ItemMasterDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<ItemMasterDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ResponseDTO bulkUploadForItemMaster(MultipartFile documentFile) {
        Map<String, Object> itemMasterBothData;
        ItemMasterResponseDTO itemMasterResponseDTO = new ItemMasterResponseDTO();
        String message = "";
        try {
            itemMasterBothData = itemMasterServiceImplHelper.csvToItemMasterDTOMapper(documentFile.getInputStream());
            List<ItemMasterDTO> itemMasterDTOS = (List<ItemMasterDTO>) itemMasterBothData.get("itemMasterDTOS");
            itemMasterRepositoryExtended.saveAll(itemMasterMapper.toEntity(itemMasterDTOS));
            Integer successfullyUploaded = ((List<ItemMasterDTO>) itemMasterBothData.get("itemMasterDTOS")).size();
            Integer skipped = ((Map<String, ItemMasterRejectedDTO>) itemMasterBothData.get("SkippedItemMasterDTO")).size();
            message = "Successfully Uploaded (" + successfullyUploaded + ")";
            if (skipped > 0) {
                message += " and Skipped " + skipped + " Rows";
            }
            return (new ResponseDTO(Boolean.TRUE,message,List.of(itemMasterBothData.get("SkippedManufacturerDTO"))));
        } catch (IOException e) {
            log.error("=====>> Error : "+e);
            throw new RuntimeException("Fail to store csv data: " + e.getMessage());
        }
    }

    @Override
    public ResponseDTO saveItemMaster(ItemMasterParameterDTO itemMasterParameterDTO) {
        if (itemMasterParameterDTO.getPrimaryProcedureCodeValueList().contains(itemMasterParameterDTO.getPrimaryProcedureCodeValue())) {
            Set uniqueItemMasterNoSet = itemMasterRepositoryExtended.findAll().stream().map(x -> x.getItemNumber()).collect(Collectors.toSet());
            Set uniqueItemMasterIdSet = itemMasterRepositoryExtended.findAll().stream().map(x -> x.getItemId()).collect(Collectors.toSet());
            try {
                if (itemMasterParameterDTO.getItemName() == null) {
                    throw new InvalidAttributeValueException("Invalid Attribute (item_name)");
                } else if (itemMasterParameterDTO.getItemName().trim() == "") {
                    throw new InputMismatchException("Item_name must be provided");
                }
//                else if (itemMasterExtendedDTO.getItemId() == 0 && uniqueItemMasterNoSet.contains(itemMasterExtendedDTO.getItemNumber())) {
//                    throw new InputMismatchException("(" + itemMasterExtendedDTO.getItemNumber() + ") " + "Item_number already exist");
//                } else if (!uniqueItemMasterIdSet.contains(itemMasterExtendedDTO.getItemId()) &&
//                    uniqueItemMasterNoSet.contains(itemMasterExtendedDTO.getItemNumber())) {
//                    throw new InputMismatchException("Item_number already exist");
//                }
                CommonUtilities.dtoTrimmer(itemMasterParameterDTO);
                ItemMasterDTO itemMasterData = (itemMasterParameterDTO.getItemId()==null || itemMasterParameterDTO.getItemId()==0)? new ItemMasterDTO():
                    (itemMasterRepositoryExtended.findById(itemMasterParameterDTO.getItemId()).isEmpty()?
                        new ItemMasterDTO() : itemMasterMapper.toDto(
                        itemMasterRepositoryExtended.findById(itemMasterParameterDTO.getItemId()).get()));
                BeanUtils.copyProperties(itemMasterParameterDTO, itemMasterData);
                // Long itemId = Long.valueOf(0);
                if (itemMasterData.getItemId() == null || itemMasterData.getItemId() == 0) {
                    itemMasterData.setItemId(null);
                    itemMasterData.setItemMasterUuid(UUID.randomUUID());
                    itemMasterData.setCreatedDate(LocalDate.now());
                    itemMasterData.setCreatedById(1L);
                    itemMasterData.setCreatedByName("Abhijit");
                    itemMasterData.setItemNumber(itemMasterRepositoryExtended.getItemNumber());
                } else {
                    itemMasterData.setUpdatedDate(LocalDate.now());
                    itemMasterData.setUpdatedById(1L);
                    itemMasterData.setUpdatedByName("Abhijit");
                }
                ItemMaster itemMasterSaved = itemMasterRepositoryExtended.save(itemMasterMapper.toEntity(itemMasterData));
                itemMasterParameterDTO.setItemId(itemMasterSaved.getItemId());
                itemMasterData.setItemId(itemMasterSaved.getItemId());
                itemProcedureCodeMapServiceExtended.saveItemProcedureCodeMap(itemMasterParameterDTO, itemMasterData);
                List list = new ArrayList<>();
                list.add(itemMasterSaved);
                return new ResponseDTO(true, "Successfully Saved", list);
            } catch (InvalidAttributeValueException e) {
                log.error("=====>> Error : "+e);
                throw new RuntimeException(e);
            }
        } else {
            return new ResponseDTO(false,
                "PrimaryProcedureCode is not exist at ProcedureCode List",
                new ArrayList<>());
        }
    }

    //private ItemMaster convertToItemMasterDTO(ItemMasterParameterDTO itemMasterParameterDTO) {
//        ItemMaster itemMasterData = new ItemMaster();
//        //itemMasterData.setItemIds(itemMasterExtendedDTO.getPrimaryProcedureCodeValueList());
//        itemMasterData.setItemId(itemMasterParameterDTO.getItemId());
//        itemMasterData.setItemName(itemMasterParameterDTO.getItemName());
//        itemMasterData.setItemDescription(itemMasterParameterDTO.getItemDescription());
//        itemMasterData.setItemTypeId(itemMasterParameterDTO.getItemTypeId());
//        itemMasterData.setItemGroupId(itemMasterParameterDTO.getItemGroupId());
//        itemMasterData.setSaleType(itemMasterParameterDTO.getSaleType());
//        itemMasterData.setCoverageType(itemMasterParameterDTO.getCoverageType());
//        itemMasterData.setWeight(itemMasterParameterDTO.getWeight());
//        itemMasterData.setLotStatus(itemMasterParameterDTO.getLotStatus());
//        itemMasterData.setKitStatus(itemMasterParameterDTO.getKitStatus());
//        itemMasterData.setKitType(itemMasterParameterDTO.getKitType());
//        itemMasterData.setItemPricingDefaultRentalAmt(itemMasterParameterDTO.getItemPricingDefaultRentalAmt());
//        itemMasterData.setItemPricingDefaultPurchaseAmt(itemMasterParameterDTO.getItemPricingDefaultPurchaseAmt());
//        itemMasterData.setAutoReorderStatus(itemMasterParameterDTO.getAutoReorderStatus());
//        itemMasterData.setExcludePoStatus(itemMasterParameterDTO.getExcludePoStatus());
//        itemMasterData.setExcludeSoStatus(itemMasterParameterDTO.getExcludeSoStatus());
//        itemMasterData.setNdc(itemMasterParameterDTO.getNdc());
//        itemMasterData.setNdcUnitOfMeasurement(itemMasterParameterDTO.getNdcUnitOfMeasurement());
//        itemMasterData.setManufacturerName(itemMasterParameterDTO.getManufacturerName());
//        itemMasterData.setManufacturerId(itemMasterParameterDTO.getManufacturerId());
//        itemMasterData.setManufacturerBarcode(itemMasterParameterDTO.getManufacturerBarcode());
//        itemMasterData.setDefaultVendorId(itemMasterParameterDTO.getDefaultVendorId());
//        itemMasterData.setExcludeStandardPriceingStat(itemMasterParameterDTO.getExcludeStandardPriceingStat());
//        itemMasterData.setUserField1(itemMasterParameterDTO.getUserField1());
//        itemMasterData.setUserField2(itemMasterParameterDTO.getUserField2());
//        itemMasterData.setUserField3(itemMasterParameterDTO.getUserField3());
//        itemMasterData.setBillingMultiplier(itemMasterParameterDTO.getBillingMultiplier());
//        itemMasterData.setClaimNote(itemMasterParameterDTO.getClaimNote());
//        itemMasterData.setItemUom(itemMasterParameterDTO.getItemUom());
//        itemMasterData.setStatus("active");
//        itemMasterData.setCreatedById(itemMasterParameterDTO.getCreatedById());
//        itemMasterData.setCreatedByName(itemMasterParameterDTO.getCreatedByName());
//        itemMasterData.setUpdatedById(itemMasterParameterDTO.getUpdatedById());
//        itemMasterData.setUpdatedByName(itemMasterParameterDTO.getUpdatedByName());
//        itemMasterData.setItemNumber(itemMasterParameterDTO.getItemNumber());
//        itemMasterData.setItemGroupName(itemMasterParameterDTO.getItemGroupName());
//        itemMasterData.setItemTypeName(itemMasterParameterDTO.getItemTypeName());
//        itemMasterData.setPrimaryProcedureCodeValue(itemMasterParameterDTO.getPrimaryProcedureCodeValue());
//        itemMasterData.setDefaultVendorName(itemMasterParameterDTO.getDefaultVendorName());
//        itemMasterData.setPrimaryProcedureCodeId(itemMasterParameterDTO.getPrimaryProcedureCodeId());

//        return itemMasterData;
   // }

    @Override
    public ItemMasterOutputDTO getItemMasterById(Long itemId) {
        ItemMasterOutputDTO itemMasterOutputDTO = new ItemMasterOutputDTO();
        ItemMaster data = itemMasterRepositoryExtended.findByItemId(itemId);
        if (data != null) {
            BeanUtils.copyProperties(data, itemMasterOutputDTO);
        }
        return itemMasterOutputDTO;
    }

    @Override
    public List<ItemMasterOutputDTO> getItemByItemName(String itemName) {
        List<ItemMasterOutputDTO> itemMasterOutputDTOList = new ArrayList<ItemMasterOutputDTO>();
        List<ItemMaster> data = itemMasterRepositoryExtended.findByItemNameLikeIgnoreCaseAndStatusIgnoreCase("%"+itemName+"%", "active");
        if (data != null) {
            data.stream().forEach(x -> {
                ItemMasterOutputDTO itemMasterOutputDTO = new ItemMasterOutputDTO();
                BeanUtils.copyProperties(x, itemMasterOutputDTO);
                itemMasterOutputDTOList.add(itemMasterOutputDTO);
            });
        }
        return itemMasterOutputDTOList;
    }

    @Override
    public List<ItemMasterOutputDTO> getItemByItemNo(String itemNumber) {
        List<ItemMasterOutputDTO> itemMasterOutputDTOList = new ArrayList<ItemMasterOutputDTO>();
        ItemMaster data = itemMasterRepositoryExtended.findByItemNumberAndStatusIgnoreCase(itemNumber, "active");
        if (data != null) {
            ItemMasterOutputDTO itemMasterOutputDTO = new ItemMasterOutputDTO();
            BeanUtils.copyProperties(data, itemMasterOutputDTO);
            itemMasterOutputDTOList.add(itemMasterOutputDTO);
        }
        return itemMasterOutputDTOList;
    }

    @Override
    public List<ItemMasterOutputDTO> getItemByItemDescription(String itemDescription) {
        List<ItemMasterOutputDTO> itemMasterOutputDTOList = new ArrayList<ItemMasterOutputDTO>();
        List<ItemMaster> data = itemMasterRepositoryExtended.findByItemDescriptionLikeIgnoreCaseAndStatusIgnoreCase("%" + itemDescription + "%", "active");
        if (data != null) {
            data.stream().forEach(x -> {
                ItemMasterOutputDTO itemMasterOutputDTO = new ItemMasterOutputDTO();
                BeanUtils.copyProperties(x, itemMasterOutputDTO);
                itemMasterOutputDTOList.add(itemMasterOutputDTO);
            });
        }
        return itemMasterOutputDTOList;
    }

    @Override
    public List<ItemMasterOutputDTO> getAllItemMasterData() {
        List<ItemMasterOutputDTO> itemMasterOutputDTOList = new ArrayList<ItemMasterOutputDTO>();
        List<ItemMaster> data = itemMasterRepositoryExtended.findByStatusIgnoreCase("active");
        if (data != null) {
            data.stream().forEach(x -> {
                ItemMasterOutputDTO itemMasterOutputDTO = new ItemMasterOutputDTO();
                BeanUtils.copyProperties(x, itemMasterOutputDTO);
                itemMasterOutputDTOList.add(itemMasterOutputDTO);
            });
        }
        return itemMasterOutputDTOList;
    }

    @Override
    public List<ItemMasterOutputDTO> getItemByStatus(String status) {
        List<ItemMasterOutputDTO> itemMasterOutputDTOList = new ArrayList<ItemMasterOutputDTO>();
        List<ItemMaster> data = itemMasterRepositoryExtended.findByStatusIgnoreCase("active");
        if (data != null) {
            data.stream().forEach(x -> {
                ItemMasterOutputDTO itemMasterOutputDTO = new ItemMasterOutputDTO();
                BeanUtils.copyProperties(x, itemMasterOutputDTO);
                itemMasterOutputDTOList.add(itemMasterOutputDTO);
            });
        }
        return itemMasterOutputDTOList;
    }

    @Override
    public List<ItemMasterDTO> findByItemIdIn(List<Long> itemIdList) {
        List<ItemMaster> itemMasterS = itemMasterRepositoryExtended.findByItemIdInAndStatusIgnoreCase(itemIdList, "active");
        return itemMasterMapper.toDto(itemMasterS);
    }

    @Override
    public List<Map> getItemByItemNoOrItemNameAndLocationIdAndPricetableId(String itemNo, String itemName, Long locationId, Long pricetableId) {
        try {
            itemName = itemNo == "" ? itemName : "";
            if (itemNo == "" && itemName == "") {
                throw new InvalidAttributeValueException("Item_No and Item_Name should not be blank at the same time");
            } else if (locationId == null) {
                throw new InvalidAttributeValueException("Location_Id should not be blank");
            } else if (pricetableId == null) {
                throw new InvalidAttributeValueException("Pricetable_Id should not be blank");
            }
            List<Map> itemsearchforpricingDTOS = itemMasterRepositoryExtended.findItemByItemNoOrItemNameAndLocationIdAndPricetableId(itemNo, itemName, locationId, pricetableId);
            return itemsearchforpricingDTOS;
        } catch (InvalidAttributeValueException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Map> getItemByItemNoOrItemNameAndPricetableId(String itemNo, String itemName, Long pricetableId) {
        try {
            itemName = itemNo == "" ? itemName : "";
            if (itemNo == "" && itemName == "") {
                throw new InvalidAttributeValueException("Item_No and Item_Name should not be blank at the same time");
            } else if (pricetableId == null) {
                throw new InvalidAttributeValueException("Pricetable_Id should not be blank");
            }
            List<Map> itemsearchallforpricingDTOS = itemMasterRepositoryExtended.findItemByItemNoOrItemNameAndPricetableId(itemNo, itemName, pricetableId);
            return itemsearchallforpricingDTOS;
        } catch (InvalidAttributeValueException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseDTO setItemStatusById(Long itemId, String status) {
        if(status.toLowerCase().equals("active") || status.toLowerCase().equals("inactive")) {
            try{
                ItemMaster itemMaster = itemMasterRepositoryExtended.findByItemId(itemId);
                itemMaster.setStatus(status);
                itemMasterRepositoryExtended.save(itemMaster);
                return (new ResponseDTO(Boolean.TRUE, "Successfully Saved", List.of(itemMaster)));
            }catch (Exception e){
                log.error("=====>> Error : "+e);
                return (new ResponseDTO(Boolean.FALSE, "Failed to Save :: Data Error",new ArrayList<>()));
            }
        }else{
            return (new ResponseDTO(Boolean.FALSE, "Status must be active or inactive ", new ArrayList<>()));
        }
    }

    @Override
    public List<Map> getItemsForSoByItemLocationIdAndItemNoAndProcCodeAndItemNameAndGroupId(String itemLocationId, String itemNo, String procCode, String itemName, String groupId) throws InvalidAttributeValueException {
        List<Map> itemSearchForSO = null;
        if(itemLocationId!=null && (itemNo!=null || procCode!=null) && (!itemNo.equals("") || !procCode.equals("")) && !itemLocationId.equals("") && (!itemNo.equals("") || !procCode.equals(""))){
            if(!itemNo.equals("") && !procCode.equals("")){
                procCode="";
            }
            groupId="";
            itemSearchForSO = itemMasterRepositoryExtended.findItemsForSoByItemLocationIdAndItemNoAndProcCodeAndItemNameAndGroupId(itemLocationId,itemNo,procCode, itemName, groupId);
        } else if ((itemLocationId!=null && itemName!=null) && (!itemLocationId.equals("") && !itemName.equals(""))) {
            itemNo="";
            procCode="";
            if (groupId == null || groupId.equals("")) {
                groupId = "";
            }
            itemSearchForSO = itemMasterRepositoryExtended.findItemsForSoByItemLocationIdAndItemNoAndProcCodeAndItemNameAndGroupId(itemLocationId,itemNo,procCode, itemName, groupId);
        }else{
            if(((itemNo==null && procCode==null) || (itemNo.equals("") && procCode.equals(""))) || (itemName==null || itemName.equals(""))){
                throw new InvalidAttributeValueException("Item No and Proc Code should not be blank at the same time or Item Name should not be blank");
            }
            itemSearchForSO = itemMasterRepositoryExtended.findItemsForSoByItemLocationIdAndItemNoAndProcCodeAndItemNameAndGroupId(itemLocationId,itemNo,procCode, itemName, groupId);
        }
        return itemSearchForSO;
    }

    @Override
    public List<Map> getItemsForPricingByItemNoAndItemNameAndLidAndPtid(String itemNo, String itemName, Long lid, Long ptid) throws InvalidAttributeValueException {
        List<Map> itemSearchForPricing = null;
        if((itemNo!=null || itemName!=null) && (lid!=null && ptid!=null) && (!itemNo.equals("") || !itemName.equals(""))){
            if(!itemNo.equals("")){
                itemName="";
            }
            itemSearchForPricing = itemMasterRepositoryExtended.findItemsForSoByItemNoAndItemNameAndLidAndPtid(itemNo, itemName, lid, ptid);
        }else{
            if((itemNo==null && itemName==null) || (itemNo.equals("") && itemName.equals(""))){
                throw new InvalidAttributeValueException("Item No and Item Name should not be blank at the same time");
            }

            if(lid ==  null && ptid == null){
                throw new InvalidAttributeValueException("Item Location Id and Price Table Id should not be blank");
            }
        }
        return itemSearchForPricing;
    }

    @Override
    public List<Map> itemPriceSearchByPriceTableId(String itemNo, String procCode, String priceTableId, String purchaseType, String dos)throws InvalidAttributeValueException {
        List<Map> itemPriceSearchByPriceTableId = null;
        if((itemNo!=null || procCode!=null) && (priceTableId!=null && purchaseType!=null && dos!=null)){
            itemPriceSearchByPriceTableId = itemMasterRepositoryExtended.findItemPriceByPriceTableId(itemNo, procCode, priceTableId, purchaseType, dos);
        }
        else{
            if((itemNo==null && procCode==null) || (itemNo.equals("") && procCode.equals(""))){
                throw new InvalidAttributeValueException("Item No and Proc Code should not be blank at the same time");
            }
        }
        return itemPriceSearchByPriceTableId;
    }


}
