package com.sunknowledge.dme.rcm.web.rest.patiententry;


import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SaveFunctionalAbilityCommand;
import com.sunknowledge.dme.rcm.service.patientsearch.FunctionalAbilitySearchServiceExtended;
import org.springframework.beans.BeanUtils;
import com.sunknowledge.dme.rcm.service.dto.patiententry.FunctionalAbilityParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.patiententry.FunctionalAbilityEntryServiceExtended;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * REST controller for PatientDiagnosisEntryResource.
 */
@Validated
@RestController
@RequestMapping("/api")
public class FunctionalAbilityEntryResourceExtended {
    private final Logger log = LoggerFactory.getLogger(FunctionalAbilityEntryResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    @Qualifier("functionalAbilityEntryServiceExtended")
    FunctionalAbilityEntryServiceExtended functionalAbilityEntryServiceExtended;
    @Autowired
    FunctionalAbilitySearchServiceExtended functionalAbilitySearchServiceExtended;

    /**
     * Save Functional Ability
     */
    @PatchMapping(value = "saveFunctionalAbility", consumes = {"application/json", "application/merge-patch+json"})
    public Mono<ResponseDTO> saveFunctionalAbility(
        @RequestBody @Valid FunctionalAbilityParameterDTO functionalAbilityParameterDTO
    ) {
        SaveFunctionalAbilityCommand obj = new SaveFunctionalAbilityCommand();
        BeanUtils.copyProperties(functionalAbilityParameterDTO,obj);
        //----- Implementing UUID_To_ID Bridge Method ----------
        Long id = 0L;
        if (functionalAbilityParameterDTO.getFunctionalAbilityUuid() != null) {
            id = functionalAbilitySearchServiceExtended.getIDByUUID(functionalAbilityParameterDTO.getFunctionalAbilityUuid());
            id = id == null ? 0L : id;
            obj.setFunctionalAbilityUuid(functionalAbilityParameterDTO.getFunctionalAbilityUuid());
        }
        obj.setFunctionalAbilityId(id);
        return functionalAbilityEntryServiceExtended.saveFunctionalAbility(obj);
    }


//    @SneakyThrows
//    @GetMapping(value = "getAccessTokenRest")
//    public Mono<String> getAccessTokenRest() {
//        JSONArray jArr = new JSONArray();
//        JSONObject jObj = new JSONObject();
//        String responseBody = new String("[]");
//        try {
//            //==================== Get the access token ====================
//            String accessToken = InternalAccessTokenUtilities.getAccessToken();
//            JSONParser parser = new JSONParser();
//            JSONObject accessTokenJson = (JSONObject) parser.parse(accessToken);
//            String token = accessTokenJson.get("access_token").toString();
//
//            if (!token.equalsIgnoreCase("NOT_AVAILABLE")) {
//                //===================== Call the MS API with Token =============
//                String itemNumber = "ITM1000000004";
//                RestTemplate restTemplateData = new RestTemplate();
//                HttpHeaders headersData = new HttpHeaders();
//                headersData.add("Authorization", "Bearer " + token);
//                HttpEntity entityData = new HttpEntity<>(headersData);
//                ResponseEntity responseData = restTemplateData.exchange(
//                    "http://localhost:8080/services/items/api/getItemByItemNo?itemNumber={itemNumber}",
//                    HttpMethod.GET,
//                    entityData,
//                    String.class,
//                    itemNumber);
//                if (responseData.getStatusCode() == HttpStatus.OK) {
//                    responseBody = (String) responseData.getBody();
//                } else {
//                    jObj = new JSONObject();
//                    jObj.put("status", false);
//                    jObj.put("message", "API Error: No Response Data Available");
//                    jObj.put("data", new JSONArray());
//                    jArr.add(jObj);
//                    responseBody = jArr.toString();
//                }
//            } else {
//                jObj = new JSONObject();
//                jObj.put("status", false);
//                jObj.put("message", "Missing Access Token");
//                jObj.put("data", new JSONArray());
//                jArr.add(jObj);
//                responseBody = jArr.toString();
//            }
//        } catch (HttpClientErrorException e) {
//            // Client-side errors (4xx)
//            if (e.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
//                throw new InvalidAccessTokenException("REQUEST_CODE_498", "Invalid Access Token - " + e.getMessage());
//            } else if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
//                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "API/Page Not Found");
//            } else if (e.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
//                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error: Error in API Service");
//            } else if (e.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
//                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request: Request Datatype Mismatch/Error in Request Body");
//            } else {
//                throw new RuntimeException(e);
//            }
//        } catch (HttpServerErrorException e) {
//            // Server-side errors (5xx)
//            if (e.getCause() instanceof ConnectTimeoutException || e.getStatusCode().equals(HttpStatus.REQUEST_TIMEOUT)) {
//                throw new ResponseStatusException(HttpStatus.REQUEST_TIMEOUT, "Connection/Request Timeout Exception");
//            } else if (e.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
//                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error: Error in API Service");
//            } else if (e.getStatusCode().equals(HttpStatus.SERVICE_UNAVAILABLE)) {
//                throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "API Service Unavailable");
//            } else {
//                throw new RuntimeException(e);
//            }
//
//        } catch (ResourceAccessException e) {
//            // Timeouts, connection refused, etc.
//            throw new RuntimeException(e);
//        } catch (Exception e) {
//            // Any other exception
//            throw new RuntimeException(e);
//        }
//
//        return Mono.just(responseBody);
//    }
}
