package com.sunknowledge.dme.rcm.web.rest.others;

import com.sunknowledge.dme.rcm.service.dto.ClaimProgramMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.HoldReasonMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.ClaimProgramMasterExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.others.HoldReasonMasterExtendedDTO;
import com.sunknowledge.dme.rcm.service.others.ClaimProgramMasterServiceExtended;
import com.sunknowledge.dme.rcm.service.others.HoldReasonMasterServiceExtended;
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
public class ClaimProgramMasterResourceExtended {
    private final Logger log = LoggerFactory.getLogger(ClaimProgramMasterResourceExtended.class);
    @Autowired
    ClaimProgramMasterServiceExtended claimProgramMasterServiceExtended;

    /********To save saveClaimProgramMaster, put claimProgramMasterUuid value as null, for update claimProgramMasterUuid value is required*********/
    @PatchMapping(value = "/saveClaimProgramMaster",consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO saveClaimProgramMaster(@Valid @RequestBody ClaimProgramMasterExtendedDTO claimProgramMasterExtendedDTO) throws InvalidAttributeValueException {
        return claimProgramMasterServiceExtended.saveClaimProgramMaster(claimProgramMasterExtendedDTO);
    }

    @GetMapping("/getAllClaimProgramMasterInfo")
    public ResponseDTO getAllClaimProgramMasterInfo(){
        List<ClaimProgramMasterDTO> obj = claimProgramMasterServiceExtended.getAllClaimProgramMasterInfo();
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj));
    }

    @GetMapping("/getClaimProgramMasterInfoByUUID")
    public ResponseDTO getClaimProgramMasterInfoByUUID(
        @NotBlank(message = "Claim Reason UUID must be provided")
        @RequestParam("uuid") UUID uuid){

        List<ClaimProgramMasterDTO> obj = claimProgramMasterServiceExtended.getClaimProgramMasterInfoByUUID(uuid);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj));
    }
}
