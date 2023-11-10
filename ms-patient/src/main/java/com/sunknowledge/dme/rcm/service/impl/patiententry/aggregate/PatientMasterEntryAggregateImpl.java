package com.sunknowledge.dme.rcm.service.impl.patiententry.aggregate;

import com.dropbox.core.InvalidAccessTokenException;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.repository.PatientMasterEntryRepositoryExtended;
import com.sunknowledge.dme.rcm.securityutil.InternalAccessTokenUtilities;
import com.sunknowledge.dme.rcm.service.dto.PatientMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SavePatientCommand;
import com.sunknowledge.dme.rcm.service.mapper.PatientMasterMapper;
import com.sunknowledge.dme.rcm.service.patiententry.aggregate.PatientMasterEntryAggregate;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Properties;
import java.util.UUID;

@Service
public class PatientMasterEntryAggregateImpl implements PatientMasterEntryAggregate {
    private final PatientMasterMapper patientMasterMapper;
    @Autowired
    PatientMasterEntryRepositoryExtended patientMasterEntryRepositoryExtended;

    public PatientMasterEntryAggregateImpl(PatientMasterMapper patientMasterMapper) {
        this.patientMasterMapper = patientMasterMapper;
    }

    @Override
    public Mono<ResponseDTO> handleSavePatientCommand(SavePatientCommand savePatientCommand) {
        PatientMasterDTO patientMasterDTO = new PatientMasterDTO();
        try {
            if (savePatientCommand.getPatientId() == null || savePatientCommand.getPatientId() == 0) {
                if (savePatientCommand.getPatientDod() != null) {
                    return Mono.just(new ResponseDTO(false, "Dead Patient not allowed.", null));
                }
                CommonUtilities.dtoTrimmer(savePatientCommand);
                BeanUtils.copyProperties(savePatientCommand, patientMasterDTO, "createdDate", "updatedDate");
                patientMasterDTO.setCreatedDate(LocalDate.now());
                patientMasterDTO.setCreatedById(1L);
                patientMasterDTO.setCreatedByName("Test");
                patientMasterDTO.setPatientId(null);
                patientMasterDTO.setPatientMasterUUID(UUID.randomUUID());
                patientMasterDTO.setStatus("active");
                return patientMasterEntryRepositoryExtended
                    .save(patientMasterMapper.toEntity(patientMasterDTO))
                    .map(patientMasterMapper::toDto).map(
                        i -> new ResponseDTO(true, "Successfully Saved", i));
            } else {
                return patientMasterEntryRepositoryExtended.findById(savePatientCommand.getPatientId())
                    .map(existingPatientMaster -> {
                        String patientIdNumber = existingPatientMaster.getPatientIdNumber();
                        CommonUtilities.dtoTrimmer(savePatientCommand);
                        BeanUtils.copyProperties(savePatientCommand, existingPatientMaster, "createdDate", "createdById", "createdByName");
                        existingPatientMaster.setPatientIdNumber(patientIdNumber);
                        existingPatientMaster.setUpdatedDate(LocalDate.now());
                        existingPatientMaster.setUpdatedByName("Abhijit");
                        existingPatientMaster.setUpdatedById(1L);

                        return existingPatientMaster;
                    })
                    .flatMap(patientMasterEntryRepositoryExtended::save)
                    .map(updatedObj -> {
                        if (savePatientCommand.getPatientDod() != null) {
                            ServiceOutcome serviceOutcome = patientDateOfDeathIncorporation(savePatientCommand.getPatientId(), savePatientCommand.getPatientDod());
                            //System.out.println("serviceOutcome " + serviceOutcome);
                        }
                        return new ResponseDTO(true, "Successfully Saved", updatedObj);
                    });
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Mono.just(new ResponseDTO(false, "Data Error! Failed to Save.", null));
        }
    }

    private ServiceOutcome patientDateOfDeathIncorporation(Long patientId, LocalDate patientDod) {
        ServiceOutcome responseBody = new ServiceOutcome<>();
        try {
            //==================== Get the access token ====================
            String accessToken = InternalAccessTokenUtilities.getAccessToken();
            JSONParser parser = new JSONParser();
            JSONObject accessTokenJson = (JSONObject) parser.parse(accessToken);
            String token = accessTokenJson.get("access_token").toString();

            if (!token.equalsIgnoreCase("NOT_AVAILABLE")) {
                //===================== Call the MS API with Token =============
                CommonUtilities commonUtilitiesObj = new CommonUtilities();
                Properties propData = commonUtilitiesObj.readPropertiesFile("/project-properties-files/url_config.properties");

                String url = propData.getProperty("salesOrder_url");
                String param = "?patientId={patientId}&patientDod={patientDod}";
                String completeUrl = url + param;
                RestTemplate restTemplateData = new RestTemplate();
                HttpHeaders headersData = new HttpHeaders();
                headersData.add("Authorization", "Bearer " + token);
                HttpEntity entityData = new HttpEntity<>(headersData);
                ResponseEntity responseData = restTemplateData.exchange(
                    completeUrl,
                    HttpMethod.POST,
                    entityData,
                    ServiceOutcome.class,
                    patientId,
                    patientDod);
                if (responseData.getStatusCode() == HttpStatus.OK) {
                    responseBody = (ServiceOutcome) responseData.getBody();
                } else {
                    responseBody.setData(null);
                    responseBody.setMessage("API Error: No Response Data Available");
                    responseBody.setOutcome(false);
                }
            } else {
                responseBody.setData(null);
                responseBody.setMessage("Missing Access Token");
                responseBody.setOutcome(false);
            }
        } catch (HttpClientErrorException e) {
            // Client-side errors (4xx)
            if (e.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
                try {
                    throw new InvalidAccessTokenException("REQUEST_CODE_498", "Invalid Access Token - " + e.getMessage());
                } catch (InvalidAccessTokenException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "API/Page Not Found");
            } else if (e.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error: Error in API Service");
            } else if (e.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request: Request Datatype Mismatch/Error in Request Body");
            } else {
                throw new RuntimeException(e);
            }
        } catch (HttpServerErrorException e) {
            // Server-side errors (5xx)
            if (e.getCause() instanceof ConnectTimeoutException || e.getStatusCode().equals(HttpStatus.REQUEST_TIMEOUT)) {
                throw new ResponseStatusException(HttpStatus.REQUEST_TIMEOUT, "Connection/Request Timeout Exception");
            } else if (e.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error: Error in API Service");
            } else if (e.getStatusCode().equals(HttpStatus.SERVICE_UNAVAILABLE)) {
                throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "API Service Unavailable");
            } else {
                throw new RuntimeException(e);
            }

        } catch (ResourceAccessException e) {
            // Timeouts, connection refused, etc.
            throw new RuntimeException(e);
        } catch (Exception e) {
            // Any other exception
            throw new RuntimeException(e);
        }

        return responseBody;

    }

}
