package com.sunknowledge.dme.rcm.web.rest.items;

import com.sunknowledge.dme.rcm.service.dto.ItemItemlocationMapDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.items.ItemItemlocationMapInputDTO;
import com.sunknowledge.dme.rcm.service.items.ItemItemlocationMapServiceExtended;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api")
public class ItemItemlocationMapResourceExtended {
    private final Logger log = LoggerFactory.getLogger(ItemItemlocationMapResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    ItemItemlocationMapServiceExtended itemItemlocationMapServiceExtended;

    @PostMapping("/saveItemItemlocationMap")
    public ResponseDTO saveItemItemlocationMap(@RequestBody ItemItemlocationMapInputDTO itemItemlocationMapInputDTO){
        System.out.println("test" + itemItemlocationMapInputDTO.getItemIdList().contains(0L));
        if(itemItemlocationMapInputDTO.getItemIdList().contains(0L) || itemItemlocationMapInputDTO.getItemIdList().contains(null) ||
            itemItemlocationMapInputDTO.getItemLocationId() == null || itemItemlocationMapInputDTO.getItemLocationId() == 0L){
            return new ResponseDTO(Boolean.FALSE,"Ids Not Valid.",new ArrayList<>());
        }
        return itemItemlocationMapServiceExtended.saveItemItemlocationMap(itemItemlocationMapInputDTO);
    }

    @PostMapping("/deactiveItemItemLocationMap")
    public ResponseDTO deactiveItemItemLocationMap(@RequestBody ItemItemlocationMapInputDTO itemItemlocationMapInputDTO){
        return itemItemlocationMapServiceExtended.deactiveItemItemLocationMap(itemItemlocationMapInputDTO);
    }

    @GetMapping("/getItemItemlocationMapByItemId")
    public ResponseDTO getItemItemlocationMapByItemId(Long itemId){
        List<ItemItemlocationMapDTO> obj = itemItemlocationMapServiceExtended.getItemItemlocationMapByItemId(itemId);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj));
    }

    @GetMapping("/getItemItemlocationMapByItemLocationId")
    public ResponseDTO getItemItemlocationMapByItemLocationId(Long itemLocationId){
        List<ItemItemlocationMapDTO> obj = itemItemlocationMapServiceExtended.getItemItemlocationMapByItemLocationId(itemLocationId);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj));
    }

    @GetMapping("/getItemItemlocationMapByItemLocationName")
    public ResponseDTO getItemItemlocationMapByItemLocationName(String itemLocationName){
        List<ItemItemlocationMapDTO> obj = itemItemlocationMapServiceExtended.getItemItemlocationMapByItemLocationName(itemLocationName);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj));
    }

    @GetMapping("/getItemItemlocationMapByItemName")
    public ResponseDTO getItemItemlocationMapByItemName(String itemName){
        List<ItemItemlocationMapDTO> obj = itemItemlocationMapServiceExtended.getItemItemlocationMapByItemName(itemName);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj));
    }

    @GetMapping("/getItemItemlocationMapByStatus")
    public ResponseDTO getItemItemlocationMapByStatus(String status){
        List<ItemItemlocationMapDTO> obj = itemItemlocationMapServiceExtended.getItemItemlocationMapByStatus(status);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj));
    }

    @PutMapping("/setItemItemlocationMapStatusById/{itemItemlocationMapId}/{status}")
    public ResponseDTO setItemItemlocationMapStatusById(
        @PathVariable("itemItemlocationMapId") Long itemItemlocationMapId,
        @NotBlank(message = "Status must be provided")
        @PathVariable("status") String status){

        return itemItemlocationMapServiceExtended.setItemItemlocationMapStatusById(itemItemlocationMapId,status);
    }
}
