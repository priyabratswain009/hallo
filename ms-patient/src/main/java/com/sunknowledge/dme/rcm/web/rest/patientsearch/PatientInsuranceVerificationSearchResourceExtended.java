package com.sunknowledge.dme.rcm.web.rest.patientsearch;

import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.service.dto.PatientInsVerifStatDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.PatientInsuranceVerificationSearchParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientInsVerifSearchByInsVerifIdOrPatientInsId;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientInsuranceSearchServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientInsuranceVerificationSearchServiceExtended;
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
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * REST controller for SalesOrderInsuranceDetailsSearchResource.
 */
//@Validated
@RestController
@RequestMapping("/api")
public class PatientInsuranceVerificationSearchResourceExtended {
    private final Logger log = LoggerFactory.getLogger(PatientInsuranceVerificationSearchResourceExtended.class);
    @Autowired
    @Qualifier("patientInsuranceVerificationSearchServiceExtendedImpl")
    PatientInsuranceVerificationSearchServiceExtended patientInsuranceVerificationSearchServiceExtended;
    @Autowired
    PatientInsuranceSearchServiceExtended patientInsuranceSearchServiceExtended;
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    /**
     * Get Patient Insurance Verification By Search Parameters
     */
    @GetMapping("getPatientInsuranceVerificationBySearchParameters")
    public Mono<ResponseDTO> getPatientInsuranceVerificationBySearchParameters(
        @ModelAttribute PatientInsuranceVerificationSearchParameterDTO patientInsuranceVerificationSearchParameterDTO
    ) {
        try {
            //----- Implementing UUID_To_ID Bridge Method ----------
            // ----If UUID not required, then pass UUID as null ----------
            Long pInid = 0L;
            if (patientInsuranceVerificationSearchParameterDTO.getPatientInsuranceUuid() != null) {
                pInid = patientInsuranceSearchServiceExtended.getIDByUUID(patientInsuranceVerificationSearchParameterDTO.getPatientInsuranceUuid()).toFuture().get();
            }
            Long pInVeSid = 0L;
            if (patientInsuranceVerificationSearchParameterDTO.getPatientInsVerifStatUuid() != null) {
                pInVeSid = patientInsuranceVerificationSearchServiceExtended.getIDByUUID(patientInsuranceVerificationSearchParameterDTO.getPatientInsVerifStatUuid());
            }

            PatientInsVerifSearchByInsVerifIdOrPatientInsId obj = new PatientInsVerifSearchByInsVerifIdOrPatientInsId();
            obj.setInsuranceVerifId(pInid);
            obj.setPatientInsuranceId(pInVeSid);
            List<PatientInsVerifStatDTO> list = patientInsuranceVerificationSearchServiceExtended.getPatientInsuranceVerificationBySearchParameters(obj).collectList().toFuture().get();
            if (list == null || list.size() == 0) {
                return Mono.just(new ResponseDTO<PatientInsVerifStatDTO>(false, "Data Not Found.", null));
            } else {
                //======== Convert List To Json Removing Specific Value =============
                String keyToRemove = "insuranceVerifId";
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

    @GetMapping("getCurrentPatientInsuranceVerificationByPatientInsuranceUUID")
    public Mono<ResponseDTO> getCurrentPatientInsuranceVerificationByPatientInsuranceUUID(
        @RequestParam("patientInsuranceUUID") UUID patientInsuranceUUID
    ) throws ExecutionException, InterruptedException {
        Long patientInsId = 0L;
        if(patientInsuranceUUID!=null){
            patientInsId = patientInsuranceSearchServiceExtended.getIDByUUID(patientInsuranceUUID).toFuture().get();
            if(patientInsId!=null && patientInsId > 0){
                PatientInsVerifStatDTO patientInsVerifStatDTO = patientInsuranceVerificationSearchServiceExtended.getPatientInsuranceVerificationByPatientInsId(patientInsId).toFuture().get();
                if(patientInsVerifStatDTO.getInsuranceVerifId()!= null){
                    return Mono.just(new ResponseDTO(true, "Data successfully fetched.", patientInsVerifStatDTO));
                }else{
                    return Mono.just(new ResponseDTO(false, "No Data found.", null));
                }
            }
            else{
                return Mono.just(new ResponseDTO(false, "Patient Insurance UUID does not exist.", null));
            }
        }else {
            return Mono.just(new ResponseDTO(false, "Patient Insurance UUID must be provided.", null));
        }
    }

    @GetMapping("getPatientInsuranceVerificationByPatientInsuranceVerificationUUID")
    public Mono<ResponseDTO> getPatientInsuranceVerificationByPatientInsuranceVerificationUUID(
        @RequestParam("patientInsuranceVerificationUUID") UUID patientInsuranceVerificationUUID
    ) throws ExecutionException, InterruptedException {
        Long pInVeSid = 0L;
        if(patientInsuranceVerificationUUID!=null){
            pInVeSid = patientInsuranceVerificationSearchServiceExtended.getIDByUUID(patientInsuranceVerificationUUID);
            if(pInVeSid!=null && pInVeSid > 0){
                PatientInsVerifStatDTO patientInsVerifStatDTO = patientInsuranceVerificationSearchServiceExtended.getPatientInsuranceVerificationDetail(pInVeSid).toFuture().get();
                if(patientInsVerifStatDTO.getInsuranceVerifId()!= null){
                    return Mono.just(new ResponseDTO(true, "Data successfully fetched.", patientInsVerifStatDTO));
                }else{
                    return Mono.just(new ResponseDTO(false, "No Data found.", null));
                }
            }else{
                return Mono.just(new ResponseDTO(false, "Patient Insurance Verification UUID does not exist.", null));
            }
        }else {
            return Mono.just(new ResponseDTO(false, "Patient Insurance Verification UUID must be provided.", null));
        }
    }

    @GetMapping("getPatientInsuranceVerificationByPatientInsuranceUUID")
    public Mono<ResponseDTO> getPatientInsuranceVerificationByPatientInsuranceUUID(
        @RequestParam("patientInsuranceUUID") UUID patientInsuranceUUID
    ) throws ExecutionException, InterruptedException {
        if(patientInsuranceUUID!=null){
            Long patientInsuranceId = 0L;
            patientInsuranceId = patientInsuranceSearchServiceExtended.getIDByUUID(patientInsuranceUUID).toFuture().get();
            if(patientInsuranceId!=null && patientInsuranceId > 0){
                List<PatientInsVerifStatDTO> patientInsVerifStatList = patientInsuranceVerificationSearchServiceExtended.getPatientInsuranceVerificationByPatientInsuranceUUID(patientInsuranceId).collectList().toFuture().get();
                if(patientInsVerifStatList.size() > 0){
                    return Mono.just(new ResponseDTO(true, "Data successfully fetched.", patientInsVerifStatList));
                }else{
                    return Mono.just(new ResponseDTO(false, "No Data found.", null));
                }
            }else{
                return Mono.just(new ResponseDTO(false, "Patient Insurance UUID does not exist.", null));
            }
        }else {
            return Mono.just(new ResponseDTO(false, "Patient Insurance UUID must be provided.", null));
        }
    }
}
