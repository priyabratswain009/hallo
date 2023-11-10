package com.sunknowledge.dme.rcm.web.rest.patientsearch;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.service.dto.FunctionalAbilityDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.FunctionalAbilitySearchParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.FunctionalAbilitySearchByFuncAbIdOrFuncAbCodeOrFuncAbName;
import com.sunknowledge.dme.rcm.service.patientsearch.FunctionalAbilitySearchServiceExtended;
import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * REST controller for FunctionalAbilitySearchResourceExtended.
 */
//@Validated
@RestController
@RequestMapping("/api")
public class FunctionalAbilitySearchResourceExtended {
    private final Logger log = LoggerFactory.getLogger(FunctionalAbilitySearchResourceExtended.class);
    @Autowired
    @Qualifier("functionalAbilitySearchServiceExtended")
    FunctionalAbilitySearchServiceExtended functionalAbilitySearchServiceExtended;
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    /**
     * Get Functional Ability By Search Parameters
     */
    @GetMapping(value = "getFunctionalAbilityBySearchParameters", produces = {"application/json"})
    public Mono<ResponseDTO> getFunctionalAbilityBySearchParameters(
        @ModelAttribute FunctionalAbilitySearchParameterDTO functionalAbilitySearchParameterDTO
    ) {
        try {
            //----- Implementing UUID_To_ID Bridge Method ----------
            Long id = 0L;
            if (functionalAbilitySearchParameterDTO.getFunctionalAbilityUUID() != null) {
                id = functionalAbilitySearchServiceExtended.getIDByUUID(functionalAbilitySearchParameterDTO.getFunctionalAbilityUUID());
            }

            FunctionalAbilitySearchByFuncAbIdOrFuncAbCodeOrFuncAbName obj =
                new FunctionalAbilitySearchByFuncAbIdOrFuncAbCodeOrFuncAbName();
            obj.setFunctionalAbilityId(id);
            obj.setFunctionalAbilityCode(functionalAbilitySearchParameterDTO.getFunctionalAbilityCode());
            obj.setFunctionalAbilityName(functionalAbilitySearchParameterDTO.getFunctionalAbilityName());

            List<FunctionalAbilityDTO> list = functionalAbilitySearchServiceExtended.getFunctionalAbilityBySearchParameters(obj).collectList().toFuture().get();
            if (list == null || list.size() == 0) {
                return Mono.just(new ResponseDTO<FunctionalAbilityDTO>(false, "Data Not Found.", null));
            } else {
                //======== Convert List To Json Removing Specific Value =============
                String keyToRemove = "functionalAbilityId";
                JSONArray jarr = CommonUtilities.convertListToJsonRemovingSpecificValue(list, keyToRemove);
                //======== Convert List To Json Removing Specific Value =============
                return Mono.just(new ResponseDTO<Object>(true, "Successfully Fetched.", jarr.size() > 0 ? jarr.get(0) : null));
            }
        } catch (InterruptedException e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }

    //getAllFunctionalAbility
    @GetMapping("getAllFunctionalAbility")
    public Mono<ResponseDTO> getAllFunctionalAbility() {
        try {
            List<FunctionalAbilityDTO> list = functionalAbilitySearchServiceExtended.getAllFunctionalAbility().collectList().toFuture().get();
            if (list == null || list.size() == 0) {
                return Mono.just(new ResponseDTO<FunctionalAbilityDTO>(false, "Data Not Available.", null));
            } else {
                //======== Convert List To Json Removing Specific Value =============
//                String keyToRemove = "patientClinicalInformationId";
//                JSONArray jarr = CommonUtilities.convertListToJsonRemovingSpecificValue(list, keyToRemove);
                //======== Convert List To Json Removing Specific Value =============
                return Mono.just(new ResponseDTO<Object>(true, "Successfully Fetched.", list.size() > 0 ? list : null));
            }
        } catch (InterruptedException e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }

    //Mono<ServiceOutcome<PatientClinicalInformationDTO>
    @GetMapping("getAllFunctionalAbilityByUUID")
    public Mono<ServiceOutcome<FunctionalAbilityDTO>> getAllFunctionalAbilityByUUID(
        @RequestParam("functionalAbilityUUID") UUID functionalAbilityUUID
    ) {
        try {
            Long functionalAbilityId = 0L;
            if (functionalAbilityUUID != null) {
                functionalAbilityId = functionalAbilitySearchServiceExtended.getIDByUUID(functionalAbilityUUID);
            }

            FunctionalAbilityDTO functionalAbilityDTO = (FunctionalAbilityDTO) functionalAbilitySearchServiceExtended.getAllFunctionalAbilityByUUID(functionalAbilityId).toFuture().get();
            if (functionalAbilityDTO == null || functionalAbilityDTO.getFunctionalAbilityId() == null) {
                return Mono.just(new ServiceOutcome<FunctionalAbilityDTO>(null, false, "No Data Available."));
            } else {
                //======== Convert List To Json Removing Specific Value =============
//                String keyToRemove = "patientClinicalInformationId";
//                JSONArray jarr = CommonUtilities.convertListToJsonRemovingSpecificValue(list, keyToRemove);
                //======== Convert List To Json Removing Specific Value =============
                return Mono.just(new ServiceOutcome<FunctionalAbilityDTO>(functionalAbilityDTO, true, "Successfully Fetched."));
            }
        } catch (InterruptedException e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }

    /**
     * This API will help to find All UUID as ID, Name as title for all Active Status.
     */
    @GetMapping("/getAllFunctionalAbilityForDropdown")
    public ServiceOutcome getAllFunctionalAbilityForDropdown() throws ExecutionException, InterruptedException {
        List<Map<String, Object>> list = functionalAbilitySearchServiceExtended.getAllFunctionalAbilityForDropdown();
        return (new ServiceOutcome(list, list.size() > 0 ? true : false, list.size() > 0 ? "Successfully Data Fetched." : "Data Not Found."));

    }
}
