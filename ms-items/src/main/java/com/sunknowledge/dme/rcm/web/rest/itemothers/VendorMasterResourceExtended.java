package com.sunknowledge.dme.rcm.web.rest.itemothers;

import com.sunknowledge.dme.rcm.exception.ItemNotFoundException;
import com.sunknowledge.dme.rcm.service.dto.VendorMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.itemothersdto.VendorMasterParameterDTO;
import com.sunknowledge.dme.rcm.service.itemothers.VendorMasterServiceExtended;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class VendorMasterResourceExtended {
    private final Logger log = LoggerFactory.getLogger(VendorMasterResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public static String TYPE = "text/csv";
    private final VendorMasterServiceExtended vendorMasterServiceExtended;

    public VendorMasterResourceExtended(VendorMasterServiceExtended vendorMasterServiceExtended) {
        this.vendorMasterServiceExtended = vendorMasterServiceExtended;
    }
    @PostMapping(value = "/bulkUploadForVendor", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO bulkUploadForVendor(@RequestPart MultipartFile documentFile) throws IOException {
        try {
            if (!TYPE.equals(documentFile.getContentType())) {
                throw new ItemNotFoundException("File type should be CSV!");
            }
            else if(documentFile == null || documentFile.getOriginalFilename().equals("")){
                throw new ItemNotFoundException("You must select the document file for uploading");
            }
            else{
                return vendorMasterServiceExtended.bulkUploadForVendor(documentFile);
            }
        } catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    @PatchMapping(value = "saveVendor", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO saveVendor(@RequestBody VendorMasterParameterDTO vendorMasterParameterDTO){
        return vendorMasterServiceExtended.saveVendor(vendorMasterParameterDTO);
    }

    @GetMapping("/getVendorById")
    public  ResponseDTO getVendorById(
        @Min(value=1, message="Vendor_Id must be greater than or equal to 1")
        @RequestParam("vendorId") Long vendorId){

        List<VendorMasterDTO> obj = vendorMasterServiceExtended.getVendorById(vendorId);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj));
    }

    @GetMapping("/getVendorByVendorName")
    public  ResponseDTO getVendorByVendorName(
        @NotBlank(message = "Vendor_Name must be provided")
        @RequestParam("vendorName") String vendorName){

        List<VendorMasterDTO> obj = vendorName.trim()!=""?vendorMasterServiceExtended.getVendorByVendorName(vendorName.trim()):new ArrayList<>();
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj));
    }

    @GetMapping("/getVendorByVendorNo")
    public  ResponseDTO getVendorByVendorNo(
        @NotBlank(message = "Vendor_No must be provided")
        @RequestParam("vendorNo") String vendorNo){

        List<VendorMasterDTO> obj = vendorMasterServiceExtended.getVendorByVendorNo(vendorNo);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj));
    }

    @GetMapping("/getVendorByVendorDescription")
    public  ResponseDTO getVendorByVendorDescription(
        @NotBlank(message = "Vendor_Description must be provided")
        @RequestParam("vendorDescription") String vendorDescription){

        List<VendorMasterDTO> obj = vendorDescription.trim()!= ""? vendorMasterServiceExtended.getVendorByVendorDescription(vendorDescription):new ArrayList<>();
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj));
    }

    @GetMapping("/getAllVendorMasterData")
    public ResponseDTO getAllVendorMasterData(){
        List<VendorMasterDTO> obj =  vendorMasterServiceExtended.getAllVendorMasterData();
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj));
    }

    @GetMapping("/getVendorByStatus")
    public  ResponseDTO getVendorByStatus(
        @NotBlank(message = "Status must be provided")
        @RequestParam("status") String status){

        List<VendorMasterDTO> obj =  vendorMasterServiceExtended.getVendorByStatus(status);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj));
    }

    @PutMapping("/setVendorMasterStatusById/{vendorId}/{status}")
    public ResponseDTO setVendorMasterStatusById(
        @PathVariable("vendorId") Long id,
        @NotBlank(message = "Status must be provided")
        @PathVariable("status") String status){

        return vendorMasterServiceExtended.setVendorMasterStatusById(id,status);
    }
}
