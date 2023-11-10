package com.sunknowledge.dme.rcm.web.rest.items;

import com.sunknowledge.dme.rcm.service.dto.ItemAssetNumberMapDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.items.ItemAssetNumberMapExtendedDTO;
import com.sunknowledge.dme.rcm.service.items.ItemAssetNumberMapServiceExtended;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.InvalidAttributeValueException;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ItemAssetNumberMapResourceExtended {

    private final Logger log = LoggerFactory.getLogger(ItemAssetNumberMapResourceExtended.class);
    @Autowired
    ItemAssetNumberMapServiceExtended itemAssetNumberMapServiceExtended;

    /********To save itemAssetNumberUuid should be null and itemId, itemNo, itemName, locationId, locationName,
     * assetNumber, onHandStatus, onRentStatus, purchaseDate, saleDate, status are mandatory fields*********/
    /********for update itemAssetNumberUuid value is required, status should be active/inactive which is not case-sensitive*********/
    @PatchMapping(value = "/saveItemAssetNumberMap",consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO saveItemAssetNumberMap(@Valid @RequestBody ItemAssetNumberMapExtendedDTO itemAssetNumberMapExtendedDTO) throws InvalidAttributeValueException {
        return itemAssetNumberMapServiceExtended.saveItemAssetNumberMap(itemAssetNumberMapExtendedDTO);
    }

    @GetMapping("/getAllItemAssetNumberMapInfo")
    public ResponseDTO getAllItemAssetNumberMapInfo(){
        List<ItemAssetNumberMapDTO> obj = itemAssetNumberMapServiceExtended.getAllItemAssetNumberMapInfo();
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj));
    }

    @GetMapping("/getItemAssetNumberMapByUUID")
    public ResponseDTO getItemAssetNumberMapByUUID(
        @NotBlank(message = "Depreciation Method UUID must be provided")
        @RequestParam("uuid") UUID uuid){

        List<ItemAssetNumberMapDTO> obj = itemAssetNumberMapServiceExtended.getItemAssetNumberMapByUUID(uuid);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj));
    }

    @GetMapping("/getItemAssetNumberMapByItemIdAndAssetNo")
    public ResponseDTO getItemAssetNumberMapByItemIdAndAssetNo(
        @NotNull(message = "Item Id must be provided")
        @RequestParam("itemId") Long itemId,
        @NotBlank(message = "Asset No must be provided")
        @RequestParam("assetNo") String assetNo){

        List<ItemAssetNumberMapDTO> obj = itemAssetNumberMapServiceExtended.getItemAssetNumberMapByItemIdAndAssetNo(itemId, assetNo);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj));
    }

    @GetMapping("/getAssetNumberByItemId")
    public ResponseDTO getAssetNumberByItemId(
        @NotNull(message = "Item Id must be provided")
        @RequestParam("itemId") Long itemId){

        List<ItemAssetNumberMapDTO> assetNumberList = itemAssetNumberMapServiceExtended.getAssetNumberByItemId(itemId);
        return (new ResponseDTO(assetNumberList.size()>0?true:false, assetNumberList.size()>0? "Successfully Data Fetched.": "Data Not Found.", assetNumberList));
    }
}
