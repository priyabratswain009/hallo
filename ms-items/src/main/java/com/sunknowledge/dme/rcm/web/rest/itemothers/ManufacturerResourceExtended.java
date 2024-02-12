package com.sunknowledge.dme.rcm.web.rest.itemothers;

import com.sunknowledge.dme.rcm.exception.ItemNotFoundException;
import com.sunknowledge.dme.rcm.service.dto.ManufacturerDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.itemothersdto.ManufacturerParameterDTO;
import com.sunknowledge.dme.rcm.service.helper.itemothers.ManufacturerServiceImplHelper;
import com.sunknowledge.dme.rcm.service.itemothers.ManufacturerServiceExtended;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
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

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ManufacturerResourceExtended {
    private final Logger log = LoggerFactory.getLogger(ManufacturerResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public static String TYPE = "text/csv";
    @Autowired
    ManufacturerServiceImplHelper manufacturerServiceImplHelper;
    private final ManufacturerServiceExtended manufacturerServiceExtended;

    public ManufacturerResourceExtended(ManufacturerServiceExtended manufacturerServiceExtended) {
        this.manufacturerServiceExtended = manufacturerServiceExtended;
    }
    @PostMapping(value = "/bulkUploadForManufacturer", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO bulkUploadForManufacturer(@RequestPart MultipartFile documentFile) throws IOException {
        try {
            if (!TYPE.equals(documentFile.getContentType())) {
                throw new ItemNotFoundException("File type should be CSV!");
            }
            else if(documentFile == null || documentFile.getOriginalFilename().equals("")){
                throw new ItemNotFoundException("You must select the document file for uploading");
            }
            else{
                return manufacturerServiceExtended.bulkUploadForManufacturer(documentFile);
            }
        } catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    @PatchMapping(value = "/saveManufacturer", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO saveManufacturer(@RequestBody ManufacturerParameterDTO manufacturerParameterDTO){
        return manufacturerServiceExtended.saveManufacturer(manufacturerParameterDTO);
    }

    @GetMapping("/getManufactureById")
    public ResponseDTO getManufactureById(
        @Min(value=1, message="Manufacture_Id must be greater than or equal to 1")
        @RequestParam("manufactureId") Long manufactureId){

        List<ManufacturerDTO> obj =manufacturerServiceExtended.getManufacturerById(manufactureId);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj, 200));
    }

    @GetMapping("/getManufactureByManufacturerName")
    public ResponseDTO getManufactureByManufacturerName(
        @NotBlank(message = "Manufacture_Name must be provided")
                                @RequestParam("manufactureName") String manufactureName){

        List<ManufacturerDTO> obj = manufactureName.trim() != "" ? manufacturerServiceExtended.getManufacturerByManufacturerName(manufactureName.trim()) : new ArrayList<>();
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj, 200));
    }

    @GetMapping("/getManufactureByManufacturerNo")
    public ResponseDTO getManufactureByManufacturerNo(
        @NotBlank(message = "Manufacture_No must be provided")
        @RequestParam("manufacturerNo") String manufacturerNo){

        List<ManufacturerDTO> obj = manufacturerServiceExtended.getManufacturerByManufacturerNo(manufacturerNo);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj, 200));
    }

    @GetMapping("/getManufactureByStatus")
    public ResponseDTO getManufactureByStatus(
        @NotBlank(message = "Status must be provided")
        @RequestParam("status") String status){

        List<ManufacturerDTO> obj = manufacturerServiceExtended.getManufactureByStatus(status);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj, 200));
    }

    @GetMapping("/getAllManufacturerData")
    public ResponseDTO getAllManufacturerData(){
        List<ManufacturerDTO> obj = manufacturerServiceExtended.getAllManufacturerData();
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj, 200));
    }

    @PutMapping("/setManufacturerStatusById/{manufactureById}/{status}")
    public ResponseDTO setManufacturerStatusById(
        @PathVariable("manufactureById") Long manufactureById,
        @NotBlank(message = "Status must be provided")
        @PathVariable("status") String status){

        return manufacturerServiceExtended.setManufacturerStatusById(manufactureById,status);
    }
}
