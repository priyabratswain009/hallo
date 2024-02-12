package com.sunknowledge.dme.rcm.web.rest.items;

import com.sunknowledge.dme.rcm.service.dto.ItemGroupDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.items.ItemGroupParameterDTO;
import com.sunknowledge.dme.rcm.service.items.ItemGroupServiceExtended;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api")
public class ItemGroupResourceExtended {
    private final Logger log = LoggerFactory.getLogger(ItemGroupResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public static String TYPE = "text/csv";

    @Qualifier("itemGroupServiceExtendedImpl")
    @Autowired
    ItemGroupServiceExtended itemGroupServiceExtended;

    @PatchMapping(value = "/saveItemGroup", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO saveItemGroup(@Valid @RequestBody ItemGroupParameterDTO itemGroupParameterDTO) {
        return itemGroupServiceExtended.saveItemGroup(itemGroupParameterDTO);
    }

    @GetMapping("/getItemGroupById")
    public ResponseDTO getItemGroupById(
        @RequestParam("itemGroupId") Long itemGroupId){
        ItemGroupDTO obj = itemGroupServiceExtended.getItemGroupById(itemGroupId);
        return (new ResponseDTO(obj!=null?true:false, obj!=null? "Successfully Data Fetched.": "Data Not Found.", List.of(obj), 200));
    }

    @GetMapping("/getItemGroupByName")
    public ResponseDTO getItemGroupByName(
        @RequestParam("itemGroupName") String itemGroupName){
        List<ItemGroupDTO> obj = itemGroupName.trim()!=""?itemGroupServiceExtended.getItemGroupByName(itemGroupName.trim()):new ArrayList<>();
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj, 200));
    }

    @GetMapping("/getAllItemGroup")
    public ResponseDTO getAllItemGroup(){
        List<ItemGroupDTO> obj = itemGroupServiceExtended.getAllItemGroup();
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj, 200));
    }

    @PutMapping("/setItemGroupStatusById/{itemGroupId}/{status}")
    public ResponseDTO setItemGroupStatusById(
        @PathVariable("itemGroupId") Long id,
        @NotBlank(message = "Status must be provided")
        @PathVariable("status") String status){

        return itemGroupServiceExtended.setItemGroupStatusById(id,status);
    }
}
