package com.sunknowledge.dme.rcm.web.rest.others;

import com.sunknowledge.dme.rcm.service.dto.ClaimFormMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.ClaimFormMasterExtendedDTO;
import com.sunknowledge.dme.rcm.service.others.ClaimFormMasterServiceExtended;
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
public class ClaimFormMasterResourceExtended {
    private final Logger log = LoggerFactory.getLogger(ClaimFormMasterResourceExtended.class);
    @Autowired
    ClaimFormMasterServiceExtended claimFormMasterServiceExtended;

    /********To save saveClaimFormMaster, put claimFormMasterUuid value as null, for update claimFormMasterUuid value is required*********/
    @PatchMapping(value = "/saveClaimFormMaster",consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO saveClaimFormMaster(@Valid @RequestBody ClaimFormMasterExtendedDTO claimFormMasterExtendedDTO) throws InvalidAttributeValueException {
        return claimFormMasterServiceExtended.saveClaimFormMaster(claimFormMasterExtendedDTO);
    }

    @GetMapping("/getAllClaimFormMasterInfo")
    public ResponseDTO getAllClaimFormMasterInfo(){
        List<ClaimFormMasterDTO> obj = claimFormMasterServiceExtended.getAllClaimFormMasterInfo();
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "": "Data Not Found.", obj,200));
    }

    @GetMapping("/getClaimFormMasterInfoByUUID")
    public ResponseDTO getClaimFormMasterInfoByUUID(
        @NotBlank(message = "Claim Form Master UUID must be provided")
        @RequestParam("uuid") UUID uuid){

        ClaimFormMasterDTO obj = claimFormMasterServiceExtended.getClaimFormMasterInfoByUUID(uuid);
        return (new ResponseDTO(obj!=null?true:false, obj!=null? "": "Data Not Found.", obj,200));
    }

    @PutMapping(value = "/setClaimFormMasterStatusByUuid")
    public ResponseDTO setClaimFormMasterStatusByUuid(@RequestParam("uuid") UUID uuid,
                                            @RequestParam("status") String status){
        return claimFormMasterServiceExtended.setClaimFormMasterStatusByUuid(uuid,status);
    }
}
