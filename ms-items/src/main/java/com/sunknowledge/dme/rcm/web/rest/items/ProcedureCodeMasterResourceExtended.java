package com.sunknowledge.dme.rcm.web.rest.items;

import com.sunknowledge.dme.rcm.service.dto.ProcedureCodeMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.items.ProcedureCodeParameterDTO;
import com.sunknowledge.dme.rcm.service.items.ProcedureCodeMasterServiceExtended;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api")
public class ProcedureCodeMasterResourceExtended {
    private final Logger log = LoggerFactory.getLogger(ProcedureCodeMasterResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public static String TYPE = "text/csv";

    @Qualifier("procedureCodeServiceExtendedImpl")
    @Autowired
    ProcedureCodeMasterServiceExtended procedureCodeMasterServiceExtended;

    @PatchMapping(value = "/saveProcedureCode", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO saveProcedureCode(@Valid @RequestBody ProcedureCodeParameterDTO procedureCodeParameterDTO) {
        return procedureCodeMasterServiceExtended.saveProcedureCode(procedureCodeParameterDTO);
    }

    @PostMapping(value = "/saveProcedureCodeByExternalAPI/{procedureCode}")
    public ResponseDTO saveProcedureCodeByExternalAPI(
        @PathVariable(value = "procedureCode", required = false) final String procedureCode
    ) {
        return procedureCodeMasterServiceExtended.saveProcedureCodeByExternalAPI(procedureCode);
    }

    @GetMapping("/getProcedureCodeByCode")
    public ResponseDTO getProcedureCodeByCode(
        @RequestParam("procedureCode") String procedureCode){
        ProcedureCodeMasterDTO obj = procedureCodeMasterServiceExtended.getProcedureCodeByCode(procedureCode);
        return (new ResponseDTO(obj!=null?true:false, obj!=null? "Successfully Data Fetched.": "Data Not Found.", List.of(obj), 200));
    }

    @GetMapping("/getProcedureCodeById")
    public ResponseDTO getProcedureCodeById(
        @RequestParam("procedureId") Long procedureId){
        ProcedureCodeMasterDTO obj = procedureCodeMasterServiceExtended.getProcedureCodeById(procedureId);
        return (new ResponseDTO(obj!=null?true:false, obj!=null? "Successfully Data Fetched.": "Data Not Found.", List.of(obj), 200));
    }

    @GetMapping("/getProcedureCodeByName")
    public ResponseDTO getProcedureCodeByName(
        @RequestParam("procedureName") String procedureName){
        List<ProcedureCodeMasterDTO> obj =procedureName.trim()!=""?
            procedureCodeMasterServiceExtended.getProcedureCodeByName(procedureName.trim()):new ArrayList<>();
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj, 200));
    }

    @GetMapping("/getAllProcedureCode")
    public ResponseDTO getAllProcedureCode(){
        List<ProcedureCodeMasterDTO> obj = procedureCodeMasterServiceExtended.getAllProcedureCode();
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "": "Data Not Found.", obj, 200));
    }
}
