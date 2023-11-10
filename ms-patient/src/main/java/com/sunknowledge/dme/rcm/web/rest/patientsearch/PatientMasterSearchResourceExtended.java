package com.sunknowledge.dme.rcm.web.rest.patientsearch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.service.dto.FunctionalAbilityDTO;
import com.sunknowledge.dme.rcm.service.dto.PatientCombinedSearchOutputDTO;
import com.sunknowledge.dme.rcm.service.dto.PatientDtoDTO;
import com.sunknowledge.dme.rcm.service.dto.PatientMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.PatientBucketParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.PatientExistCheckingParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.PatientSearchCombinedParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientBucketUserParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientSearchByCombinedInfo;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientSearchByPatientIdNoAndBranchIdInputDTO;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientMasterSearchServiceExtended;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * REST controller for SalesOrderInsuranceDetailsSearchResource.
 */
//@Validated
@RestController
@RequestMapping("/api")
public class PatientMasterSearchResourceExtended {
    private final Logger log = LoggerFactory.getLogger(PatientMasterSearchResourceExtended.class);
    @Autowired
    @Qualifier("patientMasterSearchServiceExtendedImpl")
    PatientMasterSearchServiceExtended patientMasterSearchServiceExtended;
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    /**
     * Get Patient By Search Parameters
     */
//    @GetMapping("getPatientBySearchParameters")
//    public Mono<ResponseDTO> getPatientBySearchParameters(
//        @ModelAttribute PatientSearchBasicParameterDTO patientSearchBasicParameterDTO
//    ) {
//        try {
//            //----- Implementing UUID_To_ID Bridge Method ----------
//            // ----If UUID not required, then pass UUID as null ----------
//            Long id = 0L;
//            if (patientSearchBasicParameterDTO.getPatientMasterUuid() != null) {
//                id = patientMasterSearchServiceExtended.getIDByUUID(patientSearchBasicParameterDTO.getPatientMasterUuid());
//            }
//
//            PatientSearchByBasicInfoOrAddressOrBranch obj = new PatientSearchByBasicInfoOrAddressOrBranch();
//            obj.setPatientID(id);
//            BeanUtils.copyProperties(patientSearchBasicParameterDTO, obj);
//            List<PatientMasterDTO> list = patientMasterSearchServiceExtended.getPatientBySearchParameters(obj)
//                .collectList().toFuture().get();
//
//            if (list == null || list.size() == 0) {
//                return Mono.just(new ResponseDTO<FunctionalAbilityDTO>(false, "Data Not Found.", new ArrayList<>()));
//            } else {
//                //======== Convert List To Json Removing Specific Value =============
//                String keyToRemove = "patientId";
//                JSONArray jarr = CommonUtilities.convertListToJsonRemovingSpecificValue(list, keyToRemove);
//                //======== Convert List To Json Removing Specific Value =============
//
//                return Mono.just(new ResponseDTO(true, "Successfully Fetched.", jarr));
//            }
//        } catch (InterruptedException e) {
//            log.error("==========> Exception=" + e);
//            throw new RuntimeException(e);
//        } catch (ExecutionException e) {
//            log.error("==========> Exception=" + e);
//            throw new RuntimeException(e);
//        }
//    }

    /**
     * checkPatientIsAlreadyExistOrNot
     */
    @GetMapping("checkPatientIsAlreadyExistOrNot")
    public Mono<ResponseDTO> checkPatientIsAlreadyExistOrNot(
        @ModelAttribute PatientExistCheckingParameterDTO patientExistCheckingParameterDTO
    ) {
        try {
            List<PatientMasterDTO> list = patientMasterSearchServiceExtended.checkPatientIsAlreadyExistOrNot(patientExistCheckingParameterDTO)
                .collectList().toFuture().get();

            //======== Convert List To Json Removing Specific Value =============
            String keyToRemove = "patientId";
            JSONArray jarr = CommonUtilities.convertListToJsonRemovingSpecificValue(list, keyToRemove);
            //======== Convert List To Json Removing Specific Value =============

            if (list == null || list.size() == 0) {
                return Mono.just(new ResponseDTO<FunctionalAbilityDTO>(false, "Data Not Found.", null));
            } else {
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

    /**
     * Get_Patient_By_UUID
     */
    @GetMapping("getPatientByUUID")
    public Mono<ServiceOutcome> getPatientByUUID(
        @RequestParam("patientUUID") UUID patientUUID
    ) {
        try {
            Long id = patientMasterSearchServiceExtended.getIDByUUID(patientUUID).toFuture().get();
            JSONObject parsedObj;

            PatientMasterDTO dto = patientMasterSearchServiceExtended.getPatientDetailsByPatientId(id)
                .toFuture().get();
            String posName = patientMasterSearchServiceExtended.getPosNameById(dto.getPosId());
            boolean sameAsDeliveryAddress = patientMasterSearchServiceExtended.checkSameAsDeliveryAddress(dto).toFuture().get();

            //======== Convert List To Json Removing Specific Value =============
//            String keyToRemove = "patientId";
//            JSONArray jarr = CommonUtilities.convertListToJsonRemovingSpecificValue(list, keyToRemove);
            //======== Convert List To Json Removing Specific Value =============
            ObjectMapper Obj = new ObjectMapper();
            Obj.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            Obj.registerModule(new JavaTimeModule());
            try {

                String jsonStr = Obj.writeValueAsString(dto);

                System.out.println(jsonStr);
                JSONParser jsonParser = new JSONParser();
                parsedObj = (JSONObject) jsonParser.parse(jsonStr);
                parsedObj.put("fullName", CommonUtilities.mergeName(
                    parsedObj.get("patientFirstName") != null ? parsedObj.get("patientFirstName").toString() : "",
                    parsedObj.get("patientMiddleName") != null ? parsedObj.get("patientMiddleName").toString() : "",
                    parsedObj.get("patientLastName") != null ? parsedObj.get("patientLastName").toString() : ""));
                parsedObj.put("posName", posName);

                if(sameAsDeliveryAddress)
                    parsedObj.put("sameAsDeliveryAddressStatus", "Y");
                else
                    parsedObj.put("sameAsDeliveryAddressStatus", "N");

            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            if (dto == null || dto.getPatientId() == null) {
                return Mono.just(new ServiceOutcome(new PatientMasterDTO(), false, "No Data Available."));
            } else {
                return Mono.just(new ServiceOutcome(parsedObj, true, "Successfully Fetched."));
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
     * getPatientIDNumber
     */
    @GetMapping("generatePatientIDNumber")
    public String generatePatientIDNumber() {
        return patientMasterSearchServiceExtended.generatePatientIDNumber();
    }

    /**
     * getPatientBucketData --> Used for migrating data between microservices
     */
    @GetMapping("getPatientBucketData")
    public Mono<ResponseDTO> getPatientBucketData(
        @ModelAttribute PatientBucketUserParameterDTO patientBucketUserParameterDTO
    ) {
        List<PatientDtoDTO> list = null;
        try {
            UUID patUUID = patientBucketUserParameterDTO.getPatientUUID();
            Long id = 0L;
            if (patUUID != null) {
                id = patientMasterSearchServiceExtended.getIDByUUID(patUUID).toFuture().get();
            }
            PatientBucketParameterDTO parameter = new PatientBucketParameterDTO();
            parameter.setPatientID(id);
            list = patientMasterSearchServiceExtended.getPatientBucketData(parameter)
                .collectList().toFuture().get();

            //======== Convert List To Json Removing Specific Value =============
//            String keyToRemove = "patientId";
//            JSONArray jarr = CommonUtilities.convertListToJsonRemovingSpecificValue(list, keyToRemove);
            //======== Convert List To Json Removing Specific Value =============

            if (list == null || list.size() == 0) {
                return Mono.just(new ResponseDTO<FunctionalAbilityDTO>(false, "Data Not Found.", null));
            } else {
                return Mono.just(new ResponseDTO<Object>(true, "Successfully Fetched.", list.size() > 0 ? list.get(0) : null));
            }
        } catch (InterruptedException e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }

    @GetMapping("getPatientByCombinedParameters")
    public Mono<ResponseDTO> getPatientByCombinedParameters(
        @Valid @ModelAttribute PatientSearchCombinedParameterDTO patientSearchCombinedParameterDTO
    ) {
        try {
            String patientIdNo = patientSearchCombinedParameterDTO.getPatientIdNo().trim();
            String patientFirstName = patientSearchCombinedParameterDTO.getPatientFirstName().trim();
            String patientLastName = patientSearchCombinedParameterDTO.getPatientLastName().trim();
            String pdob = patientSearchCombinedParameterDTO.getPdob().trim();
            String ssnNo = patientSearchCombinedParameterDTO.getSsnNo().trim();
            String addressLine1 = patientSearchCombinedParameterDTO.getAddressLine1().trim();
            String addressLine2 = patientSearchCombinedParameterDTO.getAddressLine2().trim();
            String pcity = patientSearchCombinedParameterDTO.getPcity().trim();
            String patientState = patientSearchCombinedParameterDTO.getPatientState().trim();
            String pzip = patientSearchCombinedParameterDTO.getPzip().trim();
            String phone = patientSearchCombinedParameterDTO.getPhone().trim();
            Long branchId = patientSearchCombinedParameterDTO.getBranchId();
            if (!patientIdNo.equals("") || !ssnNo.equals("")) {
                patientSearchCombinedParameterDTO.setPatientFirstName("");
                patientSearchCombinedParameterDTO.setPatientMiddleName("");
                patientSearchCombinedParameterDTO.setPatientLastName("");
                patientSearchCombinedParameterDTO.setPdob("");
                patientSearchCombinedParameterDTO.setAddressLine1("");
                patientSearchCombinedParameterDTO.setAddressLine2("");
                patientSearchCombinedParameterDTO.setPcity("");
                patientSearchCombinedParameterDTO.setPatientState("");
                patientSearchCombinedParameterDTO.setPzip("");
                patientSearchCombinedParameterDTO.setPhone("");
            } else if (!patientFirstName.equals("") && !patientLastName.equals("") && (!pdob.equals("") || !addressLine1.equals("") || !addressLine2.equals("") || !pcity.equals("") || !patientState.equals("") || !pzip.equals("") || !phone.equals(""))) {
                System.out.println("----- Entering into the else_if_(!patientFirstName.equals(\"\") ");
                patientSearchCombinedParameterDTO.setPatientIdNo("");
//                patientSearchCombinedParameterDTO.setPatientMiddleName("");
                patientSearchCombinedParameterDTO.setSsnNo("");
            } else {
                return Mono.just(new ResponseDTO<FunctionalAbilityDTO>(false, "Please provide proper search criterion (combined/single).", null));
            }


            PatientSearchByCombinedInfo obj = new PatientSearchByCombinedInfo();
            BeanUtils.copyProperties(patientSearchCombinedParameterDTO, obj);
            System.out.println("------------> " + obj);
            List<PatientCombinedSearchOutputDTO> list = patientMasterSearchServiceExtended.getPatientByCombinedSearchParameters(obj)
                .collectList().toFuture().get();
            if (list == null || list.size() == 0) {
                return Mono.just(new ResponseDTO<Object>(false, "Data Not Found.", list.size() > 0 ? list.get(0) : null));
            } else {
                //======== Convert List To Json Removing Specific Value =============
                String keyToRemove = "patientId";
                JSONArray jarr = CommonUtilities.convertListToJsonRemovingSpecificValue(list, keyToRemove);
                //======== Convert List To Json Removing Specific Value =============

                return Mono.just(new ResponseDTO(true, "Successfully Fetched.", jarr));
            }
        } catch (InterruptedException e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }

    @GetMapping("getPatientByPatientIdNoAndBranchId")
    public Mono<ServiceOutcome> getPatientByPatientIdNoAndBranchId(
        @Valid @ModelAttribute PatientSearchByPatientIdNoAndBranchIdInputDTO patientSearchByPatientIdNoAndBranchIdInputDTO
    ) {
        try {
            String patientIdNo = patientSearchByPatientIdNoAndBranchIdInputDTO.getPatientIdNo().trim();
            Long branchId = patientSearchByPatientIdNoAndBranchIdInputDTO.getBranchId();
            PatientSearchByCombinedInfo obj = new PatientSearchByCombinedInfo();
            if (patientIdNo != null && branchId != null) {
                obj.setPatientFirstName("");
                obj.setPatientMiddleName("");
                obj.setPatientLastName("");
                obj.setPdob("");
                obj.setSsnNo("");
                obj.setAddressLine1("");
                obj.setAddressLine2("");
                obj.setPcity("");
                obj.setPatientState("");
                obj.setPzip("");
                obj.setPhone("");
            }
            BeanUtils.copyProperties(patientSearchByPatientIdNoAndBranchIdInputDTO, obj);
            List<PatientCombinedSearchOutputDTO> list = patientMasterSearchServiceExtended.getPatientByCombinedSearchParameters(obj)
                .collectList().toFuture().get();
            if (!list.isEmpty()) {
                PatientCombinedSearchOutputDTO patient = list.get(0);
                System.out.println(patient);
                return Mono.just(new ServiceOutcome(patient, true, "Successfully Fetched."));
            } else {
                return Mono.just(new ServiceOutcome(null, false, "Data Not Found."));
            }
        } catch (InterruptedException e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }

    //patientAddressVerification API
    @GetMapping("verifyPatientBillingAddress")
    public Mono<ServiceOutcome> verifyPatientBillingAddress(
        @RequestParam("patientUUID") UUID patientUUID
    ) throws ExecutionException, InterruptedException {
        Long id = patientMasterSearchServiceExtended.getIDByUUID(patientUUID).toFuture().get();

        PatientMasterDTO patientData = patientMasterSearchServiceExtended.getPatientDetailsByPatientId(id)
            .toFuture().get();

        System.out.println("========patientData========"+patientData);

        return patientMasterSearchServiceExtended.checkPatientBillingAddressVerifiableOrNot(patientData);
    }
}
