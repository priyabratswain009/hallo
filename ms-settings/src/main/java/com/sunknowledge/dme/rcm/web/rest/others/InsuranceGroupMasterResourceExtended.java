package com.sunknowledge.dme.rcm.web.rest.others;

import com.sunknowledge.dme.rcm.service.dto.InsuranceGroupMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ServiceOutcome;
import com.sunknowledge.dme.rcm.service.dto.others.InsuranceGroupMasterExtendedDTO;
import com.sunknowledge.dme.rcm.service.others.InsuranceGroupMasterServiceExtended;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.InvalidAttributeValueException;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class InsuranceGroupMasterResourceExtended {

    private final Logger log = LoggerFactory.getLogger(InsuranceGroupMasterResourceExtended.class);
    @Autowired
    InsuranceGroupMasterServiceExtended insuranceGroupMasterServiceExtended;

    /********To save insuranceGroupMasterName is mandatory and insuranceGroupMasterUuid should be null*********/
    /********for update insuranceGroupMasterUuid value is required, status should be active/inactive which is not case-sensitive*********/
    @PatchMapping(value = "/saveInsuranceGroupMaster",consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO saveInsuranceGroupMaster(@Valid @RequestBody InsuranceGroupMasterExtendedDTO insuranceGroupMasterExtendedDTO) throws InvalidAttributeValueException {
        return insuranceGroupMasterServiceExtended.saveInsuranceGroupMaster(insuranceGroupMasterExtendedDTO);
    }

    @GetMapping("/getAllInsuranceGroupMasterInfo")
    public ResponseDTO getAllInsuranceGroupMasterInfo(){
        List<InsuranceGroupMasterDTO> obj = insuranceGroupMasterServiceExtended.getAllInsuranceGroupMasterInfo();
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "": "Data Not Found.", obj,200));
    }

    @GetMapping("/getInsuranceGroupMasterByUUID")
    public ResponseDTO getInsuranceGroupMasterByUUID(
        @NotBlank(message = "Insurance Group Master UUID must be provided")
        @RequestParam("uuid") UUID uuid){

        InsuranceGroupMasterDTO obj = insuranceGroupMasterServiceExtended.getInsuranceGroupMasterByUUID(uuid);
        return (new ResponseDTO(obj!=null?true:false, obj!=null? "": "Data Not Found.", obj,200));
    }

    @PutMapping(value = "/setInsuranceGroupMasterStatusByUuid")
    public ResponseDTO setInsuranceGroupMasterStatusByUuid(@RequestParam("uuid") UUID uuid,
                                                        @RequestParam("status") String status){
        return insuranceGroupMasterServiceExtended.setInsuranceGroupMasterStatusByUuid(uuid,status);
    }

    @GetMapping(value = "/getInsuranceGroupMasterForDropdown")
    public ServiceOutcome getInsuranceGroupMasterForDropdown(){
        List<Map<String, Object>> list =  insuranceGroupMasterServiceExtended.getInsuranceGroupMasterForDropdown();
        return (new ServiceOutcome(list, list.size() > 0 ? true : false, list.size() > 0 ? "" : "Data Not Found.",200));
    }

}
