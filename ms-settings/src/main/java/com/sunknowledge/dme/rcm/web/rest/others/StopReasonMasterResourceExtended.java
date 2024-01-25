package com.sunknowledge.dme.rcm.web.rest.others;

import com.sunknowledge.dme.rcm.service.dto.StopReasonMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.StopReasonMasterExtendedDTO;
import com.sunknowledge.dme.rcm.service.others.StopReasonMasterServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.InvalidAttributeValueException;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class StopReasonMasterResourceExtended {
    private final Logger log = LoggerFactory.getLogger(StopReasonMasterResourceExtended.class);

    @Autowired
    StopReasonMasterServiceExtended stopReasonMasterServiceExtended;

    /********To save stopReasonName, put stopReasonMasterUuid value as null, for update stopReasonMasterUuid value is required*********/
    @PatchMapping(value = "/saveStopReason",consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO saveStopReason(@Valid @RequestBody StopReasonMasterExtendedDTO stopReasonMasterExtendedDTO) throws InvalidAttributeValueException {
        return stopReasonMasterServiceExtended.saveStopReasonMaster(stopReasonMasterExtendedDTO);
    }

    @GetMapping("/getAllStopReasonDetails")
    public ResponseDTO getAllStopReasonDetails(){
        List<StopReasonMasterDTO> obj = stopReasonMasterServiceExtended.getAllStopReasonDetails();
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj,200));
    }

    @GetMapping("/getStopReasonDetailsByUUID")
    public ResponseDTO getStopReasonDetailsByUUID(
        @NotBlank(message = "Stop Reason UUID must be provided")
        @RequestParam("uuid") UUID uuid){

        StopReasonMasterDTO obj = stopReasonMasterServiceExtended.getStopReasonDetailsByUUID(uuid);
        return (new ResponseDTO(obj!=null?true:false, obj!=null? "": "Data Not Found.", obj,200));
    }

    @PutMapping(value = "/setStopReasonDetailsStatusByUuid")
    public ResponseDTO setStopReasonDetailsStatusByUuid(@RequestParam("uuid") UUID uuid,
                                                   @RequestParam("status") String status){
        return stopReasonMasterServiceExtended.setStopReasonDetailsStatusByUuid(uuid,status);
    }
}
