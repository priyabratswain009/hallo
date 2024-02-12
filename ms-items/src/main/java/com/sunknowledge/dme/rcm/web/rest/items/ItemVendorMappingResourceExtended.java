package com.sunknowledge.dme.rcm.web.rest.items;

import com.sunknowledge.dme.rcm.service.dto.ItemVendorMappingDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.items.ItemVendorMapExtendedDTO;
import com.sunknowledge.dme.rcm.service.items.ItemVendorMappingServiceExtended;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/api")
public class ItemVendorMappingResourceExtended {
    private final Logger log = LoggerFactory.getLogger(ItemVendorMappingResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    ItemVendorMappingServiceExtended itemVendorMappingServiceExtended;

    @PostMapping("/saveItemVendorMap")
    public ResponseDTO saveItemVendorMap(@RequestBody ItemVendorMapExtendedDTO itemVendorMapExtendedDTO){
        if(itemVendorMapExtendedDTO.getItemIdList().contains(0L) || itemVendorMapExtendedDTO.getItemIdList().contains(null) ||
            itemVendorMapExtendedDTO.getVendorId() == null || itemVendorMapExtendedDTO.getVendorId() == 0L){
            return new ResponseDTO(Boolean.FALSE,"Ids Not Valid.",new ArrayList<>(), 200);
        }
        return itemVendorMappingServiceExtended.saveItemVendorMap(itemVendorMapExtendedDTO);
    }

    @PostMapping("/deactiveItemVendorMap")
    public ResponseDTO deactiveItemVendorMap(@RequestBody ItemVendorMapExtendedDTO itemVendorMapExtendedDTO){
        return itemVendorMappingServiceExtended.deactiveItemVendorMap(itemVendorMapExtendedDTO);
    }

    @GetMapping("/getItemVendorMapByItemVendorId")
    public ResponseDTO getItemVendorMapByItemVendorId(Long itemVendorId){
        List<ItemVendorMappingDTO> obj = itemVendorMappingServiceExtended.getItemVendorMapByItemVendorId(itemVendorId);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj, 200));
    }

    @GetMapping("/getItemVendorMapByItemId")
    public ResponseDTO getItemVendorMapByItemId(Long itemId){
        List<ItemVendorMappingDTO> obj = itemVendorMappingServiceExtended.getItemVendorMapByItemId(itemId);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj, 200));
    }

    @GetMapping("/getItemVendorMapByVendorId")
    public ResponseDTO getItemVendorMapByVendorId(Long vendorId){
        List<ItemVendorMappingDTO> obj = itemVendorMappingServiceExtended.getItemVendorMapByVendorId(vendorId);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj, 200));
    }

    @GetMapping("/getItemVendorMapByItemName")
    public ResponseDTO getItemVendorMapByItemName(String itemName){
        List<ItemVendorMappingDTO> obj = itemName.trim()!=""?itemVendorMappingServiceExtended.getItemVendorMapByItemName(itemName.trim()):new ArrayList<>();
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj, 200));
    }

    @GetMapping("/getItemVendorMapByVendorName")
    public ResponseDTO getItemVendorMapByVendorName(String vendorName){
        List<ItemVendorMappingDTO> obj = vendorName.trim()!=""? itemVendorMappingServiceExtended.getItemVendorMapByVendorName(vendorName.trim()):new ArrayList<>();
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj, 200));
    }

    @GetMapping("/getItemVendorMapByStatus")
    public ResponseDTO getItemVendorMapByStatus(String status){
        List<ItemVendorMappingDTO> obj = itemVendorMappingServiceExtended.getItemVendorMapByStatus(status);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj, 200));
    }

    @PutMapping("/setItemVendorStatusById/{itemVendorId}/{status}")
    public ResponseDTO setItemVendorStatusById(
        @PathVariable("itemVendorId") Long itemVendorId,
        @NotBlank(message = "Status must be provided")
        @PathVariable("status") String status){

        return itemVendorMappingServiceExtended.setItemVendorStatusById(itemVendorId,status);
    }

    @GetMapping(value = "/getVendorsByItemIdDropdown")
    public ResponseDTO getVendorsByItemIdDropdown(@RequestParam(name = "itemId", required = false) Long itemId){
        List<Map> maps = itemVendorMappingServiceExtended.getVendorsByItemIdDropdown(itemId);
        return (new ResponseDTO(maps.size()>0?true:false, maps.size()>0? "Successfully Data Fetched.": "Data Not Found.", maps, 200));
    }
}
