package com.sunknowledge.dme.rcm.web.rest.patientsearch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.service.dto.PatientDoctorMapDTO;
import com.sunknowledge.dme.rcm.service.dto.PatientMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.PatientDoctorMappingOutputDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.PatientDoctorMapPatientMasterExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.PatientDoctorMappingSearchParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientDoctorMappingSearchByPatIdOrMapIdOrDocId;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientSearchByBasicInfoOrAddressOrBranch;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientDoctorMappingSearchServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientMasterSearchServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.queryhandler.PatientDoctorMappingSearchQueryHandler;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * REST controller for PatientDoctorMappingSearchResourceExtended.
 */
//@Validated
@RestController
@RequestMapping("/api")
public class PatientDoctorMappingSearchResourceExtended {
    private final Logger log = LoggerFactory.getLogger(PatientDoctorMappingSearchResourceExtended.class);
    @Autowired
    @Qualifier("patientDoctorMappingSearchServiceExtended")
    PatientDoctorMappingSearchServiceExtended patientDoctorMappingSearchServiceExtended;
    @Autowired
    PatientDoctorMappingSearchQueryHandler patientDoctorMappingSearchQueryHandler;
    @Autowired
    PatientMasterSearchServiceExtended patientMasterSearchServiceExtended;
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    /**
     * Get Patient Doctor Mapping By Search Parameters
     */
    @GetMapping("getPatientDoctorMappingBySearchParameters")
    public Mono<ResponseDTO> getPatientDoctorMappingBySearchParameters(
        @ModelAttribute PatientDoctorMappingSearchParameterDTO patientDoctorMappingSearchParameterDTO
    ) {
        try {
            //----- Implementing UUID_To_ID Bridge Method ----------
            // ----If UUID not required, then pass UUID as null ----------
            Long id = 0L;
            if (patientDoctorMappingSearchParameterDTO.getPatientDoctorMapUuid() != null) {
                id = patientDoctorMappingSearchServiceExtended.getIDByUUID(patientDoctorMappingSearchParameterDTO.getPatientDoctorMapUuid()).toFuture().get();
            }
            PatientDoctorMappingSearchByPatIdOrMapIdOrDocId obj = new PatientDoctorMappingSearchByPatIdOrMapIdOrDocId();
            obj.setPatientId(patientDoctorMappingSearchParameterDTO.getPatientId());
            obj.setPatientDoctorMapId(id);
            obj.setDoctorNpiNumber(patientDoctorMappingSearchParameterDTO.getDoctorNpiNumber());

            PatientDoctorMappingOutputDTO patientDoctorMappingOutputDTO = patientDoctorMappingSearchServiceExtended.getPatientDoctorMappingBySearchParameters(obj).toFuture().get();
            if (patientDoctorMappingOutputDTO == null || patientDoctorMappingOutputDTO.getDoctorNpiNumber() == null) {
                return Mono.just(new ResponseDTO<PatientDoctorMappingOutputDTO>(false, "Data Not Found.", null));
            } else {
                //======== Convert List To Json Removing Specific Value =============
//                String keyToRemove = "patientDoctorMapId";
//                JSONArray jarr = CommonUtilities.convertListToJsonRemovingSpecificValue(list, keyToRemove);
                patientDoctorMappingOutputDTO.setPatientDoctorMapId(null);
                //======== Convert List To Json Removing Specific Value =============
                return Mono.just(new ResponseDTO<Object>(true, "Successfully Fetched.", patientDoctorMappingOutputDTO != null ? patientDoctorMappingOutputDTO : null));
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
     * Get Doctor Details From Doctor API for Single NpiNumber
     */
//    @GetMapping("getDoctorMapDetails/{patientId}/{npiId}") + @PathVariable("patientId") Long patientId,@PathVariable("npiId") String npiId
    @GetMapping("getDoctorMapDetails")
    public Mono<ResponseDTO> getDoctorMapDetails(@RequestParam("patientUuid") UUID patientUuid, @RequestParam("npiId") String npiId) {
        String type = "Search";
        if(npiId==null && npiId.equals("")){
            return Mono.just(new ResponseDTO(false,"Npi Number must be required.",null));
        }if(patientUuid==null){
            return Mono.just(new ResponseDTO(false,"Patient must be required.",null));
        }else {
            Long pMasId = 0L;
            if (patientUuid != null) {
                try {
                    pMasId = patientMasterSearchServiceExtended.getIDByUUID(patientUuid).toFuture().get();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
                pMasId = pMasId == null ? 0L : pMasId;
            }
            PatientDoctorMappingOutputDTO patientDoctorMappingOutputDTO = null;
            try {
                if(pMasId!=null && pMasId!=0) {
                    Mono<PatientDoctorMappingOutputDTO> patientDoctorMapDTOFlux = patientDoctorMappingSearchQueryHandler.getPatientDoctorsByNpi(pMasId, npiId);
                    patientDoctorMappingOutputDTO = patientDoctorMapDTOFlux.toFuture().get();
                }
                if (patientDoctorMappingOutputDTO != null && patientDoctorMappingOutputDTO.getDoctorNpiNumber() != null ) {
                    return Mono.just(new ResponseDTO(true, "Data Fetched Successfully.", patientDoctorMappingOutputDTO));
                } else {
                    return patientDoctorMappingSearchServiceExtended.getDoctorDetails(npiId, type);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Get Doctor Details From Doctor API for Multiple NpiNumber
     */
    @GetMapping("getDoctorDetails/{patientId}/{npiIds}")
    public Mono<ResponseDTO> getPatientDoctor(@PathVariable("patientId") Long patientId, @PathVariable("npiIds") String npiIds) {
        ResponseDTO responseResultDTO = new ResponseDTO();
        List<Object> objectList = new ArrayList<>();
        JSONParser parser = new JSONParser();
        Long countPats = 0l;
        try {
            String[] npiIdsArr = npiIds.split(",");
            for (String npiId : npiIdsArr) {
                Mono<ResponseDTO> responseDTO = null;
                PatientDoctorMappingSearchByPatIdOrMapIdOrDocId patientDoctorMappingSearchByPatIdOrMapIdOrDocId =
                    new PatientDoctorMappingSearchByPatIdOrMapIdOrDocId();
                patientDoctorMappingSearchByPatIdOrMapIdOrDocId.setDoctorNpiNumber(npiId);
                patientDoctorMappingSearchByPatIdOrMapIdOrDocId.setPatientId(patientId);
                Mono<PatientDoctorMappingOutputDTO> patientDoctorMapDTOMono = patientDoctorMappingSearchQueryHandler.getPatientDoctorMappingBySearchParameters(patientDoctorMappingSearchByPatIdOrMapIdOrDocId);
                PatientDoctorMappingOutputDTO patientDoctorMappingOutputDTO = patientDoctorMapDTOMono.toFuture().get();

                ResponseDTO doctorDeatils = new ResponseDTO();
                if(patientDoctorMappingOutputDTO==null || patientDoctorMappingOutputDTO.getDoctorNpiNumber() == null){
                    Mono<ResponseDTO> doctorDeatilsObj = patientDoctorMappingSearchServiceExtended.getDoctorDetails(npiId, "Persist");
                    doctorDeatils = (ResponseDTO) doctorDeatilsObj.toFuture().get();
                }
                //----- Get Details to check patient is exist or not -----
                PatientSearchByBasicInfoOrAddressOrBranch obj1 =
                    new PatientSearchByBasicInfoOrAddressOrBranch();
                obj1.setPatientID(patientId);
                Flux<PatientMasterDTO> list = patientMasterSearchServiceExtended.getPatientBySearchParameters(obj1);
                //----- Get Details to check patient is exist or not -----
                countPats = list.count().toFuture().get();
                responseDTO = patientDoctorMappingSearchServiceExtended.getPatientDoctor(doctorDeatils, patientDoctorMappingOutputDTO, countPats, patientId, npiId);

                ////System.out.println("DAta "+ responseDTO.toFuture().get().getData().get(0));
                ResponseDTO responseDTO1 = responseDTO.toFuture().get();
                if (responseDTO1.getStatus()) {
                    System.out.println("type = " + responseDTO1.getData().getClass().getName());
                    System.out.println("data = " + responseDTO1.getData());

                    if (responseDTO1.getData().getClass().getName().equals("java.util.ArrayList")) {
                        ArrayList arrayList = (ArrayList) responseDTO1.getData();
                        PatientDoctorMappingOutputDTO patientDoctorMapDTO = (PatientDoctorMappingOutputDTO) arrayList.get(0);
                        //System.out.println("Npi Data " + jsonObject);
                        objectList.add(patientDoctorMapDTO);
                    } else {
                        PatientDoctorMappingOutputDTO patientDoctorMapDTO = (PatientDoctorMappingOutputDTO) responseDTO1.getData();
                        //System.out.println("Npi Data " + arrayList.get(0));
                        objectList.add(patientDoctorMapDTO);
                    }

                }
                ////System.out.println("objectList " + objectList);
            }
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (objectList.size() > 0) {
            responseResultDTO.setData(objectList);
            responseResultDTO.setStatus(true);
            responseResultDTO.setMessage("Data Fetched Successfully");
        } else {
            responseResultDTO.setData(new ArrayList());
            responseResultDTO.setStatus(false);
            responseResultDTO.setMessage("Data Not Found");
        }
        return Mono.just(responseResultDTO);
    }

    //getPatientDoctorsByPatientUuid
    @GetMapping("getPatientDoctorsByPatientUuid")
    public Mono<ResponseDTO> getPatientDoctorsByPatientUuid(
        @RequestParam("patientUuid") UUID patientUuid
    ) {
        try {
            //----- Implementing UUID_To_ID Bridge Method ----------
            // ----If UUID not required, then pass UUID as null ----------
            Long patientId = 0L;
            if (patientUuid != null) {
                patientId = patientMasterSearchServiceExtended.getIDByUUID(patientUuid).toFuture().get();
            }

            List<PatientDoctorMapPatientMasterExtendedDTO> list = patientDoctorMappingSearchServiceExtended.getPatientDoctorsByPatientId(patientId).collectList().toFuture().get();
            if (list == null || list.size() == 0) {
                return Mono.just(new ResponseDTO<PatientDoctorMapPatientMasterExtendedDTO>(false, "Data Not Available.", null));
            } else {
                //======== Convert List To Json Removing Specific Value =============
//                String keyToRemove = "patientClinicalInformationId";
//                JSONArray jarr = CommonUtilities.convertListToJsonRemovingSpecificValue(list, keyToRemove);
                //======== Convert List To Json Removing Specific Value =============
                return Mono.just(new ResponseDTO<Object>(true, "Successfully Fetched.",
                    list.size() > 0 ? list : null));
            }
        } catch (InterruptedException e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }

    //getPatientDoctorsByPatientDoctorUuid
    @GetMapping("getPatientDoctorsByPatientDoctorUuid")
    public Mono<ServiceOutcome<PatientDoctorMapDTO>> getPatientDoctorsByPatientDoctorUuid(
        @RequestParam("patientDoctorMapUuid") UUID patientDoctorMapUuid
    ) {
        try {
            //----- Implementing UUID_To_ID Bridge Method ----------
            // ----If UUID not required, then pass UUID as null ----------
            Long patientDoctorMapId = 0L;
            if (patientDoctorMapUuid != null) {
                patientDoctorMapId = patientDoctorMappingSearchServiceExtended.getIDByUUID(patientDoctorMapUuid).toFuture().get();
            }

            //PatientInsuranceSearchByPatientIdAndInsuranceId obj = new PatientInsuranceSearchByPatientIdAndInsuranceId();
            //obj.setPatInsuranceId(patInsId);

            PatientDoctorMapDTO patientDoctorMapDTO = (PatientDoctorMapDTO) patientDoctorMappingSearchServiceExtended.getPatientDoctorsByPatientDoctorId(patientDoctorMapId).toFuture().get();
            if (patientDoctorMapDTO == null || patientDoctorMapDTO.getPatientDoctorMapId() == null) {
                return Mono.just(new ServiceOutcome<PatientDoctorMapDTO>(null, false, "No Data Available."));
            } else {
                return Mono.just(new ServiceOutcome<PatientDoctorMapDTO>(patientDoctorMapDTO, true, "Successfully Fetched."));
            }
        } catch (InterruptedException e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }

    @GetMapping("getCurrentPatientDoctorsByMaxId")
    public Mono<ServiceOutcome> getCurrentPatientDoctorsByMaxId(@RequestParam("patientUuid") UUID patientUuid) {
        try {
            Long patientId = 0L;
            if (patientUuid != null) {
                patientId = patientMasterSearchServiceExtended.getIDByUUID(patientUuid).toFuture().get();
            }

            PatientDoctorMapDTO patientDoctorMapDTO = (PatientDoctorMapDTO) patientDoctorMappingSearchServiceExtended.getCurrentPatientDoctorsByMaxId(patientId).toFuture().get();
            if (patientDoctorMapDTO == null || patientDoctorMapDTO.getPatientDoctorMapId() == null) {
                return Mono.just(new ServiceOutcome<PatientDoctorMapDTO>(null, false, "No Data Available."));
            } else {
                JSONObject parsedObj;
                ObjectMapper Obj = new ObjectMapper();
                Obj.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
                Obj.registerModule(new JavaTimeModule());
                try {
                    String jsonStr = Obj.writeValueAsString(patientDoctorMapDTO);
                    //System.out.println(jsonStr);
                    JSONParser jsonParser = new JSONParser();
                    parsedObj = (JSONObject) jsonParser.parse(jsonStr);
                    parsedObj.put("fullName", CommonUtilities.mergeName(
                        parsedObj.get("doctorFirstName") != null ? parsedObj.get("doctorFirstName").toString() : "",
                        parsedObj.get("doctorMiddleName") != null ? parsedObj.get("doctorMiddleName").toString() : "",
                        parsedObj.get("doctorLastName") != null ? parsedObj.get("doctorLastName").toString() : ""));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
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
}
