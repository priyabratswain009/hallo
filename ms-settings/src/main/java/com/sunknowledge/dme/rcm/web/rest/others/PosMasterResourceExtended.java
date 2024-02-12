package com.sunknowledge.dme.rcm.web.rest.others;

import com.sunknowledge.dme.rcm.service.dto.CompanyDTO;
import com.sunknowledge.dme.rcm.service.dto.PosMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ServiceOutcome;
import com.sunknowledge.dme.rcm.service.dto.others.CompanyExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.others.PosMasterExtendedDTO;
import com.sunknowledge.dme.rcm.service.others.PosMasterServiceExtended;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.InvalidAttributeValueException;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class PosMasterResourceExtended {
    private final Logger log = LoggerFactory.getLogger(PosMasterResourceExtended.class);

    @Autowired
    PosMasterServiceExtended posMasterServiceExtended;

   /* @GetMapping("/getAllPOSInfo")
    public ResponseDTO getAllPOSInfo() {
        List<PosMasterDTO> obj = posMasterServiceExtended.getAllPOSInfo();
        return (new ResponseDTO(obj.size() > 0 ? true : false, obj.size() > 0 ? "Successfully Data Fetched." : "Data Not Found.", obj));
    }*/

    /********To save savePOSInfo, put posMasterUuid value as null, for update posMasterUuid value is required*********/
    @PatchMapping(value = "/savePOSInfo", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO savePOSInfo(@Valid @RequestBody PosMasterExtendedDTO posMasterExtendedDTO) throws InvalidAttributeValueException {
        return posMasterServiceExtended.savePOSInfo(posMasterExtendedDTO);
    }

    /**
     * This API will help to find All ID as ID, Pos_Name as title for all Active Status.
     * @return ServiceOutcome
     */
    @GetMapping("getPlaceOfServiceForDropdown")
    public ServiceOutcome getPlaceOfServiceForDropdown() {
        List<Map<String, Object>> list = posMasterServiceExtended.getPlaceOfServiceForDropdown();
        return (new ServiceOutcome(list, list.size() > 0 ? true : false, list.size() > 0 ? "" : "Data Not Found.",200));
    }

    @GetMapping("/getAllPlaceOfServiceData")
    public ResponseDTO getAllPlaceOfServiceData(){
        List<PosMasterDTO> list = posMasterServiceExtended.getAllPlaceOfServiceData();
        return (new ResponseDTO(list.size()>0?true:false, list.size()>0? "": "Data Not Found.", list,200));
    }

    @GetMapping("/getPlaceOfServiceDataById")
    public ResponseDTO getPlaceOfServiceDataById(@RequestParam("posId") Long posId){
        PosMasterDTO obj = posMasterServiceExtended.getPlaceOfServiceDataById(posId);
        return (new ResponseDTO(obj!=null?true:false, obj!=null? "": "Data Not Found.", obj,200));
    }

    @PutMapping(value = "/setPlaceOfServiceStatusByUuid")
    public ResponseDTO setPlaceOfServiceStatusByUuid(@RequestParam("uuid") UUID uuid,
                                            @RequestParam("status") String status){
        return posMasterServiceExtended.setPlaceOfServiceStatusByUuid(uuid,status);
    }
}
