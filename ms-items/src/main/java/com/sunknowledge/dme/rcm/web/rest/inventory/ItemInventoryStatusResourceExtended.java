package com.sunknowledge.dme.rcm.web.rest.inventory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunknowledge.dme.rcm.service.dto.common.ServiceOutcome;
import com.sunknowledge.dme.rcm.service.dto.ItemInventoryStatusDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.inventory.InventoryInputDTO;
import com.sunknowledge.dme.rcm.service.dto.inventory.InventoryStatusByItemIdAndItemLocationIdInputDTO;
import com.sunknowledge.dme.rcm.service.dto.inventory.ItemInventoryStatusParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.inventory.ItemInventoryStatusZeroQtyParameterDTO;
import com.sunknowledge.dme.rcm.service.items.ItemInventoryStatusServiceExtended;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api")
public class ItemInventoryStatusResourceExtended {
    private final Logger log = LoggerFactory.getLogger(ItemInventoryStatusResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public static String TYPE = "text/csv";

    @Autowired
    ItemInventoryStatusServiceExtended itemInventoryStatusServiceExtended;

    @PatchMapping(value = "saveInventoryStatusManualQtyUpdate", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO saveInventoryStatusManualQtyUpdate(@Valid @RequestBody ItemInventoryStatusParameterDTO itemInventoryStatusParameterDTO){
        return itemInventoryStatusServiceExtended.saveInventoryStatusManualQtyUpdate(itemInventoryStatusParameterDTO);
    }

    @PatchMapping(value = "saveInventoryStatusByZeroQtyUpdate", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO saveInventoryStatusByZeroQtyUpdate(@Valid @RequestBody ItemInventoryStatusZeroQtyParameterDTO inventoryStatusZeroQtyParameterDTO){
        return itemInventoryStatusServiceExtended.saveInventoryStatusByZeroQtyUpdate(inventoryStatusZeroQtyParameterDTO);
    }

    @GetMapping("/getInventoryStatusByItemInventoryStatusId")
    public ResponseDTO getInventoryStatusByItemInventoryStatusId(
        @Min(value=1, message="Item_inventory_status_id must be greater than or equal to 1")
        @RequestParam("itemInventoryStatusId") Long itemInventoryStatusId){
        ItemInventoryStatusDTO obj = itemInventoryStatusServiceExtended.getInventoryStatusByItemInventoryStatusId(itemInventoryStatusId);
        return (obj!=null?
            (new ResponseDTO(true, "Successfully Data Fetched.", List.of(obj), 200)):
            (new ResponseDTO(false, "Data Not Found.", new ArrayList<>(), 200))
        );
    }

//    @GetMapping("/getInventoryStatusByLocationId")
//    public ResponseDTO getInventoryStatusByLocationId(
//        @Min(value=1, message="Location_Id must be greater than or equal to 1")
//        @RequestParam("locationId") Long locationId){
//        List<ItemInventoryStatusDTO> obj = itemInventoryStatusServiceExtended.getInventoryStatusByLocationId(locationId);
//        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj));
//    }

    @GetMapping("/getInventoryStatusByItemId")
    public ResponseDTO getInventoryStatusByItemId(
        @Min(value=1, message="Item_Id must be greater than or equal to 1")
        @RequestParam("itemId") Long itemId){

        List<ItemInventoryStatusDTO> obj = itemInventoryStatusServiceExtended.getItemInventoryStatusByItemId(itemId);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj, 200));
    }

    @GetMapping("/getAllInventoryStatusData")
    public ResponseDTO getAllInventoryStatusData(){
        List<ItemInventoryStatusDTO> obj = itemInventoryStatusServiceExtended.getAllInventoryStatusData();
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj, 200));
    }

    @GetMapping("/getInventoryStatusByStatus")
    public ResponseDTO getInventoryStatusByStatus(
        @NotBlank(message = "Status must be provided")
        @RequestParam("status") String status){

        List<ItemInventoryStatusDTO> obj = itemInventoryStatusServiceExtended.getInventoryStatusByStatus(status);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj, 200));
    }

    @PutMapping("/setInventoryStatusById/{itemInventoryStatusId}/{status}")
    public ResponseDTO setInventoryStatusById(
        @PathVariable("itemInventoryStatusId") Long itemInventoryStatusId,
        @NotBlank(message = "Status must be provided")
        @PathVariable("status") String status){

        return itemInventoryStatusServiceExtended.setInventoryStatusById(itemInventoryStatusId,status);
    }

    @PutMapping(value = "saveInventoryStatusByItemIdAndItemLocationId") //, consumes = "application/json", produces = "application/json"
    public ServiceOutcome saveInventoryStatusByItemIdAndItemLocationId(
        @RequestParam(value = "inputParams") String inputParams) throws Exception {
        System.out.println("==========inputParams========="+inputParams);
        //String json = "[{\"itemId\":4000,\"itemLocationId\":12,\"itemQty\":2,\"operationType\":\"Committed\"},{\"itemId\":4001,\"itemLocationId\":12,\"itemQty\":2,\"operationType\":\"Committed\"},{\"itemId\":4002,\"itemLocationId\":12,\"itemQty\":2,\"operationType\":\"Committed\"}]";
        //http://localhost:8080/process?json={%22key1%22:%22value1%22,%22key2%22:42}
        //, defaultValue = "[]"
        String decodedText = URLDecoder.decode(inputParams, "UTF-8");
        System.out.println("Decoded Text: " + decodedText);
        ObjectMapper mapper = new ObjectMapper();
        String operationType = null;
        List<InventoryStatusByItemIdAndItemLocationIdInputDTO> inventoryStatusByItemIdAndItemLocationIdInputDTO = new ArrayList<>();
        InventoryInputDTO inputDTOs = new InventoryInputDTO();
        try{
            InventoryStatusByItemIdAndItemLocationIdInputDTO[] myObjects = mapper.readValue(decodedText, InventoryStatusByItemIdAndItemLocationIdInputDTO[].class);
            for (InventoryStatusByItemIdAndItemLocationIdInputDTO eachObject : myObjects) {
                operationType = eachObject.getOperationType();
                log.info("=====eachObject====="+eachObject.getOperationType());
            }

            for (InventoryStatusByItemIdAndItemLocationIdInputDTO eachObject : myObjects) {
                inventoryStatusByItemIdAndItemLocationIdInputDTO.add(eachObject);
                log.info("=====eachObject====="+eachObject.getItemId());
                log.info("=====eachObject====="+eachObject.getItemLocationId());
                log.info("=====eachObject====="+eachObject.getItemQty());
            }
            inputDTOs.setInventoryStatusByItemIdAndItemLocationIdInputDTO(inventoryStatusByItemIdAndItemLocationIdInputDTO);
        }catch (JsonMappingException e){
            e.printStackTrace();
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }

        if(operationType.equalsIgnoreCase("Committed")){
            return itemInventoryStatusServiceExtended.saveInventoryStatusByItemIdAndItemLocationId(inputDTOs);
        }else if(operationType.equalsIgnoreCase("Delivered")){
            return itemInventoryStatusServiceExtended.saveInventoryStatusByItemIdAndItemLocationIdForDelivered(inputDTOs);
        }else{
            return new ServiceOutcome<>(null, false, "Wrong Operation.", 200);
        }
    }

    @GetMapping(value = "checkJsonString") //, consumes = "application/json", produces = "application/json"
    public ServiceOutcome checkJsonString() throws Exception {
        List<InventoryStatusByItemIdAndItemLocationIdInputDTO> items = new ArrayList<>();

        // Populate the list with your data
        /*items.add(new InventoryStatusByItemIdAndItemLocationIdInputDTO(4000, 12, 2, "Committed",null,null));
        items.add(new InventoryStatusByItemIdAndItemLocationIdInputDTO(4001, 12, 2, "Committed",null,null));
        items.add(new InventoryStatusByItemIdAndItemLocationIdInputDTO(4002, 12, 2, "Committed",null,null));*/

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(items);
            System.out.println("=====json====="+json);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
