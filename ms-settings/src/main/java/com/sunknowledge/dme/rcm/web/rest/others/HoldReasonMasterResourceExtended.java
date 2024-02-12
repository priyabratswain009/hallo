package com.sunknowledge.dme.rcm.web.rest.others;

import com.sunknowledge.dme.rcm.service.dto.HoldReasonMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.HoldReasonMasterExtendedDTO;
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
public class HoldReasonMasterResourceExtended {

    private final Logger log = LoggerFactory.getLogger(HoldReasonMasterResourceExtended.class);
    @Autowired
    HoldReasonMasterServiceExtended holdReasonMasterServiceExtended;

    /********To save holdReasonName, put holdReasonMasterUuid value as null, for update holdReasonMasterUuid value is required*********/
    @PatchMapping(value = "/saveHoldReason",consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO saveHoldReasonMaster(@Valid @RequestBody HoldReasonMasterExtendedDTO holdReasonMasterExtendedDTO) throws InvalidAttributeValueException {
        return holdReasonMasterServiceExtended.saveHoldReasonMaster(holdReasonMasterExtendedDTO);
    }

    @GetMapping("/getAllHoldReasonDetails")
    public ResponseDTO getAllHoldReasonDetails(){
        List<HoldReasonMasterDTO> obj = holdReasonMasterServiceExtended.getAllHoldReasonDetails();
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "": "Data Not Found.", obj,200));
    }

    @GetMapping("/getHoldReasonDetailsByUUID")
    public ResponseDTO getHoldReasonDetailsByUUID(
        @NotBlank(message = "Hold Reason UUID must be provided")
        @RequestParam("uuid") UUID uuid){

        HoldReasonMasterDTO obj = holdReasonMasterServiceExtended.getHoldReasonDetailsByUUID(uuid);
        return (new ResponseDTO(obj!=null?true:false, obj!=null? "": "Data Not Found.", obj,200));
    }

    @PutMapping(value = "/setHoldReasonDetailsStatusByUuid")
    public ResponseDTO setHoldReasonDetailsStatusByUuid(@RequestParam("uuid") UUID uuid,
                                                        @RequestParam("status") String status){
        return holdReasonMasterServiceExtended.setHoldReasonDetailsStatusByUuid(uuid,status);
    }
}
