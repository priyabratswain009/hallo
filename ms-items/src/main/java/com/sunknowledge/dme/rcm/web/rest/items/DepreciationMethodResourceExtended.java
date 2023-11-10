package com.sunknowledge.dme.rcm.web.rest.items;

import com.sunknowledge.dme.rcm.service.dto.DepreciationMethodDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.items.DepreciationMethodExtendedDTO;
import com.sunknowledge.dme.rcm.service.items.DepreciationMethodServiceExtended;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.InvalidAttributeValueException;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class DepreciationMethodResourceExtended {
    private final Logger log = LoggerFactory.getLogger(DepreciationMethodResourceExtended.class);
    @Autowired
    DepreciationMethodServiceExtended depreciationMethodServiceExtended;

    /********To save depreciationMethodName is mandatory and depreciationMethodUuid should be null*********/
    /********for update depreciationMethodUuid value is required, status should be active/inactive which is not case-sensitive*********/
    @PatchMapping(value = "/saveDepreciationMethod",consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO saveDepreciationMethod(@Valid @RequestBody DepreciationMethodExtendedDTO depreciationMethodExtendedDTO) throws InvalidAttributeValueException {
        return depreciationMethodServiceExtended.saveDepreciationMethod(depreciationMethodExtendedDTO);
    }

    @GetMapping("/getAllDepreciationMethodInfo")
    public ResponseDTO getAllDepreciationMethodInfo(){
        List<DepreciationMethodDTO> obj = depreciationMethodServiceExtended.getAllDepreciationMethodInfo();
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj));
    }

    @GetMapping("/getDepreciationMethodByUUID")
    public ResponseDTO getDepreciationMethodByUUID(
        @NotBlank(message = "Depreciation Method UUID must be provided")
        @RequestParam("uuid") UUID uuid){

        List<DepreciationMethodDTO> obj = depreciationMethodServiceExtended.getDepreciationMethodByUUID(uuid);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj));
    }
}
