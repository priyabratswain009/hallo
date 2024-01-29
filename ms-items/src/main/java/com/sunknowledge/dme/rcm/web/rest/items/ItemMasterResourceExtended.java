package com.sunknowledge.dme.rcm.web.rest.items;

import com.sunknowledge.dme.rcm.service.dto.common.ServiceOutcome;
import com.sunknowledge.dme.rcm.exception.ItemNotFoundException;
import com.sunknowledge.dme.rcm.service.dto.ItemMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.items.ItemMasterCombinedSearchInputDTO;
import com.sunknowledge.dme.rcm.service.dto.items.ItemMasterOutputDTO;
import com.sunknowledge.dme.rcm.service.dto.items.ItemMasterParameterDTO;
import com.sunknowledge.dme.rcm.service.items.ItemMasterServiceExtended;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.management.InvalidAttributeValueException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/api")
public class ItemMasterResourceExtended {
    private final Logger log = LoggerFactory.getLogger(ItemMasterResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public static String TYPE = "text/csv";

    @Autowired
    ItemMasterServiceExtended itemMasterServiceExtended;

    @PostMapping(value = "/bulkUploadForItemMaster", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO bulkUploadForItemMaster(@RequestPart MultipartFile documentFile) throws IOException {
        try {
            if (!TYPE.equals(documentFile.getContentType())) {
                throw new ItemNotFoundException("File type should be CSV!");
            }
            else if(documentFile == null || documentFile.getOriginalFilename().equals("")){
                throw new ItemNotFoundException("You must select the document file for uploading");
            }
            else{
                return itemMasterServiceExtended.bulkUploadForItemMaster(documentFile);
            }
        } catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    @PatchMapping(value = "saveItemMaster", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO saveItemMaster(@RequestBody ItemMasterParameterDTO itemMasterParameterDTO){
        return itemMasterServiceExtended.saveItemMaster(itemMasterParameterDTO);
    }

    @GetMapping("/getItemMasterById")
    public ResponseDTO getItemMasterById(
        @Min(value=1, message="Item_Id must be greater than or equal to 1")
        @RequestParam("itemId") Long itemId){

        ItemMasterOutputDTO obj = itemMasterServiceExtended.getItemMasterById(itemId);
        return (new ResponseDTO((obj!=null && obj.getItemId()!= null) ?true:false, obj!=null? "": "Data Not Found.", obj, 200));
    }

    @GetMapping("/getItemByItemName")
    public  ResponseDTO getItemByItemName(
        @NotBlank(message = "Item_Name must be provided")
        @RequestParam("itemName") String itemName){

        List<ItemMasterOutputDTO> obj = itemName.trim()!=""? itemMasterServiceExtended.getItemByItemName(itemName.trim()):new ArrayList<>();
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj, 200));
    }

    @GetMapping("/getItemByItemNo")
    public  ResponseDTO getItemByItemNo(
        @NotBlank(message = "Item_number must be provided")
        @RequestParam("itemNumber") String itemNumber){

        List<ItemMasterOutputDTO> obj = itemMasterServiceExtended.getItemByItemNo(itemNumber);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj, 200));
    }

    @GetMapping("/getItemByItemDescription")
    public ResponseDTO getItemByItemDescription(
        @NotBlank(message = "Item_Description must be provided")
        @RequestParam("itemDescription") String itemDescription){

        List<ItemMasterOutputDTO> obj = itemDescription.trim() != "" ?itemMasterServiceExtended.getItemByItemDescription(itemDescription.trim()):new ArrayList<>();
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj, 200));
    }

    @GetMapping("/getAllItemMasterData")
    public ResponseDTO getAllItemMasterData(){
        List<ItemMasterOutputDTO> obj = itemMasterServiceExtended.getAllItemMasterData();
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj, 200));
    }

    @GetMapping("/getItemByStatus")
    public  ResponseDTO getItemByStatus(
        @NotBlank(message = "Status must be provided")
        @RequestParam("status") String status){

        List<ItemMasterOutputDTO> obj = itemMasterServiceExtended.getItemByStatus(status);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj, 200));
    }

    @GetMapping(value = "getItemByItemNoOrItemNameAndLocationIdAndPricetableId")
    public ResponseDTO getItemByItemNoOrItemNameAndLocationIdAndPricetableId(
        @NotNull(message = "ItemNo must be provided")
        @RequestParam("itemNo") String itemNo,
        @NotNull(message = "ItemName must be provided")
        @RequestParam("itemName") String itemName,
        @NotNull(message = "LocationId must be provided")
        @RequestParam("locationId") Long locationId,
        @NotNull(message = "PricetableId must be provided")
        @RequestParam("pricetableId") Long pricetableId){

        List<Map> obj = itemMasterServiceExtended.getItemByItemNoOrItemNameAndLocationIdAndPricetableId(itemNo,itemName,locationId,pricetableId);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj, 200));
    }

    @GetMapping(value = "getItemByItemNoOrItemNameAndPricetableId")
    public ResponseDTO getItemByItemNoOrItemNameAndPricetableId(
        @NotNull(message = "ItemNo must be provided")
        @RequestParam("itemNo") String itemNo,
        @NotNull(message = "ItemName must be provided")
        @RequestParam("itemName") String itemName,
        @NotNull(message = "PricetableId must be provided")
        @RequestParam("pricetableId") Long pricetableId){

        List<Map> obj = itemMasterServiceExtended.getItemByItemNoOrItemNameAndPricetableId(itemNo,itemName,pricetableId);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj, 200));
    }

    @PutMapping("/setItemStatusById/{itemId}/{status}")
    public ResponseDTO setItemStatusById(
        @PathVariable("itemId") Long itemId,
        @NotBlank(message = "Status must be provided")
        @PathVariable("status") String status){

        return itemMasterServiceExtended.setItemStatusById(itemId,status);
    }

    /**********Checking Condition 1:: itemlocationid &&  (itemno || proccode) , Condition 2::  (itemlocationid && itemname) where [groupid is Not mandatory ]*****************/
    @GetMapping(value = "/getItemsForSo")
    public ResponseDTO getItemsForSo(
        @NotNull(message = "Item Location Id must be provided")
        @NotBlank(message = "Item Location Id can not be blank")
        @RequestParam("itemLocationId") String itemLocationId,
        @RequestParam("itemNo") String itemNo,
        @RequestParam("procCode") String procCode,
        @RequestParam("itemName") String itemName,
        @RequestParam("groupId") String groupId) throws InvalidAttributeValueException {

        List<Map> obj = obj = itemMasterServiceExtended.getItemsForSoByItemLocationIdAndItemNoAndProcCodeAndItemNameAndGroupId(itemLocationId,itemNo,procCode, itemName, groupId);

        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj, 200));
    }

    /**********Checking Condition :: (itemno || itemname) && (lid && ptid) is mandatory*****************/
    @GetMapping(value = "/getItemsForPricing")
    public ResponseDTO getItemsForPricing(
        @RequestParam("itemNo") String itemNo,
        @RequestParam("itemName") String itemName,
        @NotNull(message = "lid must be provided")
        @RequestParam("lid") Long lid,
        @NotNull(message = "ptid must be provided")
        @RequestParam("ptid") Long ptid) throws InvalidAttributeValueException {

        List<Map> obj = itemMasterServiceExtended.getItemsForPricingByItemNoAndItemNameAndLidAndPtid(itemNo,itemName,lid, ptid);

        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj, 200));
    }

    @GetMapping(value = "/itemPriceSearchByPriceTableId")
    public ResponseDTO itemPriceSearchByPriceTableId(
        @RequestParam(name = "itemNo", required = false) String itemNo,
        @RequestParam(name = "procCode", required = false) String procCode,
        @NotNull(message = "priceTableId must be provided")
        @RequestParam("priceTableId") String priceTableId,
        @NotNull(message = "purchaseType must be provided")
        @RequestParam("purchaseType") String purchaseType,
        @NotNull(message = "Date of Service must be provided")
        @RequestParam("dos") String dos) throws InvalidAttributeValueException {

        List<Map> obj = itemMasterServiceExtended.itemPriceSearchByPriceTableId(itemNo,procCode,priceTableId, purchaseType, dos);

        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj, 200));
    }

    @GetMapping("getItemMasterDataByCombinedSearchParameters")
    public ServiceOutcome getItemMasterDataByCombinedSearchParameters(
        @Valid @ModelAttribute ItemMasterCombinedSearchInputDTO itemMasterCombinedSearchInputDTO
    ) {

        //BeanUtils.copyProperties(patientSearchByPatientIdNoAndBranchIdInputDTO, obj);
        if((itemMasterCombinedSearchInputDTO.getItemNumber()!=null && !itemMasterCombinedSearchInputDTO.getItemNumber().trim().equals("")) ||
            (itemMasterCombinedSearchInputDTO.getItemName()!=null && !itemMasterCombinedSearchInputDTO.getItemName().trim().equals("")) ||
            (itemMasterCombinedSearchInputDTO.getManufacturerName()!=null && !itemMasterCombinedSearchInputDTO.getManufacturerName().trim().equals("")) ||
            (itemMasterCombinedSearchInputDTO.getItemGroupName()!=null && !itemMasterCombinedSearchInputDTO.getItemGroupName().trim().equals("")) ||
            (itemMasterCombinedSearchInputDTO.getItemTypeName()!=null && !itemMasterCombinedSearchInputDTO.getItemTypeName().trim().equals(""))
        ){
            ServiceOutcome outputData = itemMasterServiceExtended.getItemMasterDataByCombinedSearchParameters(itemMasterCombinedSearchInputDTO);
            //List<ItemMasterDTO> itemMasterList = new ArrayList<>();
            log.info("=========outputData========"+outputData);
            if(outputData.getOutcome() && outputData.getData()!=null){
                List<ItemMasterDTO> itemMasterList = (List<ItemMasterDTO>) outputData.getData();
                return new ServiceOutcome(itemMasterList.size()> 0 ? itemMasterList : "", itemMasterList.size()> 0 ? true : false, itemMasterList.size()> 0 ? "" : "Data Not Found", 200);
            }else{
                return outputData;
            }
        }else{
            return new ServiceOutcome(null, false, "Invalid Input.", 200);
        }
    }

}
