package com.sunknowledge.dme.rcm.web.rest.items;

import com.sunknowledge.dme.rcm.service.dto.ItemProcedureCodeMapDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.items.ItemProcedureCodeMapServiceExtended;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api")
public class ItemProcedureCodeMapResourceExtended {
    private final Logger log = LoggerFactory.getLogger(ItemProcedureCodeMapResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public static String TYPE = "text/csv";

    @Autowired
    ItemProcedureCodeMapServiceExtended itemProcedureCodeMapServiceExtended;

    @GetMapping("/getItemProcedureCodeMapByItemId")
    public ResponseDTO getItemProcedureCodeMapByItemId(
        @Min(value=1, message="Item_master_item_id must be greater than or equal to 1")
        @RequestParam("itemMasterItemId") Long itemMasterItemId){

        List<ItemProcedureCodeMapDTO> obj = itemProcedureCodeMapServiceExtended.getItemProcedureCodeMapById(itemMasterItemId);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj, 200));
    }

    @GetMapping("/getItemProcedureCodeMapByItemName")
    public ResponseDTO getItemProcedureCodeMapByItemName(
        @NotBlank(message = "Item_Name must be provided")
        @RequestParam("itemName") String itemName){

        List<ItemProcedureCodeMapDTO> obj = itemName.trim()!=""?itemProcedureCodeMapServiceExtended.getItemProcedureCodeMapByItemName(itemName.trim()):new ArrayList<>();
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj, 200));
    }

    @GetMapping("/getItemProcedureCodeMapByItemNo")
    public  ResponseDTO getItemProcedureCodeMapByItemNo(
        @NotBlank(message = "Item_number must be provided")
        @RequestParam("itemNumber") String itemNumber){

        List<ItemProcedureCodeMapDTO> obj = itemProcedureCodeMapServiceExtended.getItemProcedureCodeMapByItemNo(itemNumber);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj, 200));
    }

    @GetMapping("/getItemProcedureCodeMapByProcedureCode")
    public ResponseDTO getItemProcedureCodeMapByProcedureCode(
        @NotBlank(message = "Procedure_Code must be provided")
        @RequestParam("procedureCode") String procedureCode){

        List<ItemProcedureCodeMapDTO> obj = itemProcedureCodeMapServiceExtended.getItemProcedureCodeMapByProcedureCode(procedureCode);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj, 200));
    }

    @GetMapping("/getItemProcedureCodeMapByItemDescription")
    public ResponseDTO getItemProcedureCodeMapByItemDescription(
        @NotBlank(message = "Item_Description must be provided")
        @RequestParam("itemDescription") String itemDescription){

        List<ItemProcedureCodeMapDTO> obj = itemDescription.trim()!=""?itemProcedureCodeMapServiceExtended.getItemProcedureCodeMapByItemDescription(itemDescription.trim()):new ArrayList<>();
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj, 200));
    }

    @GetMapping("/getAllItemProcedureCodeMapData")
    public ResponseDTO getAllItemProcedureCodeMapData(){
        List<ItemProcedureCodeMapDTO> obj = itemProcedureCodeMapServiceExtended.getAllItemProcedureCodeMapData();
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj, 200));
    }

    @GetMapping("/getItemProcedureCodeMapByStatus")
    public ResponseDTO getItemProcedureCodeMapByStatus(
        @NotBlank(message = "Status must be provided")
        @RequestParam("status") String status){

        List<ItemProcedureCodeMapDTO> obj = itemProcedureCodeMapServiceExtended.getItemProcedureCodeMapByStatus(status);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj, 200));
    }

    @PutMapping("/setItemProcedureCodeMapStatusById/{itemProcedureCodeMapId}/{status}")
    public ResponseDTO setItemProcedureCodeMapStatusById(
        @PathVariable("itemProcedureCodeMapId") Long itemProcedureCodeMapId,
        @NotBlank(message = "Status must be provided")
        @PathVariable("status") String status){

        return itemProcedureCodeMapServiceExtended.setItemProcedureCodeMapStatusById(itemProcedureCodeMapId,status);
    }
}
