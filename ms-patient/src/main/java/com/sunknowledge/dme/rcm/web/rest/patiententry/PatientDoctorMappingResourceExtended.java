package com.sunknowledge.dme.rcm.web.rest.patiententry;

import com.sunknowledge.dme.rcm.service.dto.PatientMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.PatientDoctorMappingParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.PatientDoctorMappingParameterV2DTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SavePatientDoctorMappingCommand;
import com.sunknowledge.dme.rcm.service.patiententry.PatientDoctorMappingServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientDoctorMappingSearchServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientMasterSearchServiceExtended;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.concurrent.ExecutionException;

/**
 * REST controller for PatientDoctorMappingResource.
 */
//@Validated
@RestController
@RequestMapping("/api")
public class PatientDoctorMappingResourceExtended {
    private final Logger log = LoggerFactory.getLogger(PatientDoctorMappingResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    //@Qualifier("patientDoctorMappingServiceExtended")
    PatientDoctorMappingServiceExtended patientDoctorMappingServiceExtended;
    @Autowired
    PatientDoctorMappingSearchServiceExtended patientDoctorMappingSearchServiceExtended;
    @Autowired
    PatientMasterSearchServiceExtended patientMasterSearchServiceExtended;

    /**
     * Save Patient Doctor Mapping
     */
    @PatchMapping(value = "savePatientDoctorMappingV2", consumes = {"application/json", "application/merge-patch+json"})
    public Mono<ResponseDTO> savePatientDoctorMapping(
        @RequestBody @Valid PatientDoctorMappingParameterDTO patientDoctorMappingParameterDTO
    ) throws ExecutionException, InterruptedException {
        //----- Implementing UUID_To_ID Bridge Method ----------
        Long id = 0L;
        if (patientDoctorMappingParameterDTO.getPatientDoctorMapUuid() != null) {
            id = patientDoctorMappingSearchServiceExtended.getIDByUUID(patientDoctorMappingParameterDTO.getPatientDoctorMapUuid()).toFuture().get();
            id = id == null ? 0L : id;
        }
        log.info("=======id======="+id);
        Long pMasId = 0L;
        if (patientDoctorMappingParameterDTO.getPatientUUID() != null) {
            pMasId = patientMasterSearchServiceExtended.getIDByUUID(patientDoctorMappingParameterDTO.getPatientUUID()).toFuture().get();
            pMasId = pMasId == null ? 0L : pMasId;
        }
        SavePatientDoctorMappingCommand obj = new SavePatientDoctorMappingCommand();
        BeanUtils.copyProperties(patientDoctorMappingParameterDTO,obj);
        obj.setPatientDoctorMapId(id);
        obj.setPatientId(pMasId);
        log.info("=======pMasId========"+pMasId);
        //----- Get Details to check patient is exist or not -----
        /*PatientSearchByBasicInfoOrAddressOrBranch obj1 =
            new PatientSearchByBasicInfoOrAddressOrBranch();
        obj1.setPatientID(obj.getPatientId());
        Flux<PatientMasterDTO> list = patientMasterSearchServiceExtended.getPatientBySearchParameters(obj1);*/
        log.info("====obj.getPatientId()===="+obj.getPatientId());
        PatientMasterDTO patientMasterDTO = null;
        if(obj.getPatientId()!=null){
            Mono<PatientMasterDTO> objMono = patientMasterSearchServiceExtended.getPatientByPatientId(obj.getPatientId());
            patientMasterDTO = objMono.toFuture().get();
        }
        log.info("===========patientMasterDTO==========="+patientMasterDTO);
        //----- Get Details to check patient is exist or not -----
        Long countPats = 0l;
        //countPats = list.count().toFuture().get();
        countPats = patientMasterDTO.getPatientId()!=null ? 1L : 0L;

        return patientDoctorMappingServiceExtended.savePatientDoctorMapping(obj, countPats);
    }

    @PatchMapping(value = "savePatientDoctorMapping", consumes = {"application/json", "application/merge-patch+json"})
    public Mono<ResponseDTO> savePatientDoctorMappingV2(
        @RequestBody @Valid PatientDoctorMappingParameterV2DTO patientDoctorMappingParameterV2DTO
    ) throws ExecutionException, InterruptedException {
        Long pdMapId = 0L;
        if (patientDoctorMappingParameterV2DTO.getPatientDoctorMapUuid() != null) {
            pdMapId = patientDoctorMappingSearchServiceExtended.getIDByUUID(patientDoctorMappingParameterV2DTO.getPatientDoctorMapUuid()).toFuture().get();
            pdMapId = pdMapId == null ? 0L : pdMapId;
        }
        log.info("=======pdMapId======="+pdMapId);
        Long pMasId = 0L;
        if (patientDoctorMappingParameterV2DTO.getPatientUUID() != null) {
            pMasId = patientMasterSearchServiceExtended.getIDByUUID(patientDoctorMappingParameterV2DTO.getPatientUUID()).toFuture().get();
            pMasId = pMasId == null ? 0L : pMasId;
        }
        SavePatientDoctorMappingCommand obj = new SavePatientDoctorMappingCommand();
        BeanUtils.copyProperties(patientDoctorMappingParameterV2DTO,obj);
        obj.setPatientDoctorMapId(pdMapId);
        obj.setPatientId(pMasId);
        obj.setDoctorAddressLine1(patientDoctorMappingParameterV2DTO.getDoctorAddressLineI());
        obj.setDoctorAddressLine2(patientDoctorMappingParameterV2DTO.getDoctorAddressLineIi());
        obj.setDoctorContact1(patientDoctorMappingParameterV2DTO.getDoctorContactI());
        obj.setDoctorContact2(patientDoctorMappingParameterV2DTO.getDoctorContactIi());
        return patientDoctorMappingServiceExtended.savePatientDoctorMapping_V2(obj);
    }
}
