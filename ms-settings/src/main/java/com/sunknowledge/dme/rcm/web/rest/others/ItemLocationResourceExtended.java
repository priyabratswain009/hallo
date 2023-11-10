package com.sunknowledge.dme.rcm.web.rest.others;

import com.sunknowledge.dme.rcm.exception.BranchNotFoundException;
import com.sunknowledge.dme.rcm.service.dto.ItemLocationDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.ItemLocationParameterDTO;
import com.sunknowledge.dme.rcm.service.others.ItemLocationServiceExtended;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api")
public class ItemLocationResourceExtended {
    private final Logger log = LoggerFactory.getLogger(ItemLocationResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public static String TYPE = "text/csv";

    @Autowired
    ItemLocationServiceExtended itemLocationServiceExtended;

    @PostMapping(value = "/bulkUploadForItemLocation", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO bulkUploadForItemLocation(@RequestPart MultipartFile documentFile) throws IOException {
        try {
            if (!TYPE.equals(documentFile.getContentType())) {
                throw new BranchNotFoundException("File type should be CSV!");
            }
            else if(documentFile == null || documentFile.getOriginalFilename().equals("")){
                throw new BranchNotFoundException("You must select the document file for uploading");
            }
            else{
                return itemLocationServiceExtended.bulkUploadForItemLocation(documentFile);
            }
        } catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    @PatchMapping(value = "saveItemLocation", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO saveItemLocation(@RequestBody ItemLocationParameterDTO itemLocationParameterDTO){
        return itemLocationServiceExtended.saveItemLocation(itemLocationParameterDTO);
    }

    @GetMapping("/getItemLocationById")
    public ResponseDTO getItemLocationById(
        @Min(value=1, message="Item_Location_Id must be greater than or equal to 1")
        @RequestParam("itemLocationId") Long itemLocationId){
        List<ItemLocationDTO> obj = itemLocationServiceExtended.getItemLocationById(itemLocationId);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj));
    }

    @GetMapping("/getItemLocationByItemLocationName")
    public ResponseDTO getItemLocationByItemLocationName(
        @NotBlank(message = "Item_Location_Name must be provided")
        @RequestParam("itemLocationName") String itemLocationName){
        List<ItemLocationDTO> obj = itemLocationName.trim()!=""?itemLocationServiceExtended.getItemLocationByItemLocationName(itemLocationName.trim()):new ArrayList<>();
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj));
    }

    @GetMapping("/getItemLocationByDescription")
    public ResponseDTO getItemLocationByDescription(
        @NotBlank(message = "NPI must be provided")
        @RequestParam("description") String description){
        List<ItemLocationDTO> obj = description.trim()!=""?itemLocationServiceExtended.getItemLocationByDescription(description.trim()):new ArrayList<>();
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj));
    }

    @GetMapping("/getAllItemLocationData")
    public ResponseDTO getAllItemLocationData(){
        List<ItemLocationDTO> obj = itemLocationServiceExtended.getAllItemLocationData();
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj));
    }

    @GetMapping("/getItemLocationByStatus")
    public ResponseDTO getItemLocationByStatus(
        @NotBlank(message = "Status must be provided")
        @RequestParam("status") String status){

        List<ItemLocationDTO> obj = itemLocationServiceExtended.getItemLocationByStatus(status);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj));
    }

    @PutMapping("/setItemLocationStatusById/{itemLocationId}/{status}")
    public ResponseDTO setItemLocationStatusById(
        @PathVariable("itemLocationId") Long itemLocationId,
        @NotBlank(message = "Status must be provided")
        @PathVariable("status") String status){

        return itemLocationServiceExtended.setItemLocationStatusById(itemLocationId,status);
    }
}
