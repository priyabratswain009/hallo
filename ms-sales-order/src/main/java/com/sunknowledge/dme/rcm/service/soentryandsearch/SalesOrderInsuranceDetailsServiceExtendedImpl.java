package com.sunknowledge.dme.rcm.service.soentryandsearch;

import com.dropbox.core.InvalidAccessTokenException;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.SalesOrderInsuranceDetails;
import com.sunknowledge.dme.rcm.repository.SalesOrderInsuranceDetailsRepositoryExtended;
import com.sunknowledge.dme.rcm.securityutil.InternalAccessTokenUtilities;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderInsuranceDetailsDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderInsuranceEntryParameterDTO;
import com.sunknowledge.dme.rcm.service.mapper.SalesOrderInsuranceDetailsMapper;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

@Primary
@Service
@Transactional
public class SalesOrderInsuranceDetailsServiceExtendedImpl implements SalesOrderInsuranceDetailsServiceExtended {

    @Autowired
    SalesOrderInsuranceDetailsRepositoryExtended salesOrderInsuranceDetailsRepositoryExtended;

    @Autowired
    SalesOrderInsuranceDetailsMapper salesOrderInsuranceDetailsMapper;

    @Override
    public Mono<SalesOrderInsuranceDetailsDTO> save(SalesOrderInsuranceDetailsDTO salesOrderInsuranceDetailsDTO) {
        return null;
    }

    @Override
    public Mono<SalesOrderInsuranceDetailsDTO> update(SalesOrderInsuranceDetailsDTO salesOrderInsuranceDetailsDTO) {
        return null;
    }

    @Override
    public Mono<SalesOrderInsuranceDetailsDTO> partialUpdate(SalesOrderInsuranceDetailsDTO salesOrderInsuranceDetailsDTO) {
        return null;
    }

    @Override
    public Flux<SalesOrderInsuranceDetailsDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Mono<Long> countAll() {
        return null;
    }

    @Override
    public Mono<SalesOrderInsuranceDetailsDTO> findOne(Long id) {
        return null;
    }

    @Override
    public Mono<Void> delete(Long id) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<SalesOrderInsuranceDetails> findBySalesOrderId(Long SOID) {
        return salesOrderInsuranceDetailsRepositoryExtended.findBySalesOrderId(SOID);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<SalesOrderInsuranceDetails> getSOInsuranceDetailsByInsuranceUUID(UUID sOInsuranceDetailsUUID) {
        return salesOrderInsuranceDetailsRepositoryExtended.getSOInsuranceDetailsByInsuranceUUID(sOInsuranceDetailsUUID);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getIDByUUID(UUID sOInsuranceDetailsUUID) {
        try {
            return salesOrderInsuranceDetailsRepositoryExtended.getIDByUUID(sOInsuranceDetailsUUID).toFuture().get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<SalesOrderInsuranceDetails> findById(Long id) {
        return salesOrderInsuranceDetailsRepositoryExtended.findById(id);
    }

    @Override
    public Mono<ResponseDTO> saveSOInsuranceDetails(SalesOrderInsuranceDetailsDTO obj, SalesOrderInsuranceEntryParameterDTO salesOrderInsuranceEntryParameterDTO, Long branchId) {
        String message = "";
        Boolean status = false;
        List<Object> responseList = new ArrayList<>();

        try {

            if (salesOrderInsuranceEntryParameterDTO.getPrimaryInsurerId() != null &&
                !salesOrderInsuranceEntryParameterDTO.getPrimaryInsurerId().equals(0L)) {
                //----- Get details for check doctor is exist or not -----
                String accessToken = InternalAccessTokenUtilities.getAccessToken();
                JSONParser parser = new JSONParser();
                JSONObject accessTokenJson = (JSONObject) parser.parse(accessToken);
                String token = accessTokenJson.get("access_token").toString();

                String responseBody = "[]";
                if (!token.equalsIgnoreCase("NOT_AVAILABLE")) {
                    branchId = branchId == null ? 0L : branchId;
                    Long insuranceId = obj.getPrimaryInsurerId() == null ? 0L : obj.getPrimaryInsurerId();
                    RestTemplate restTemplateData = new RestTemplate();
                    HttpHeaders headersData = new HttpHeaders();
                    headersData.add("Authorization", "Bearer " + token);
                    HttpEntity entityData = new HttpEntity<>(headersData);
                    ResponseEntity responseData = restTemplateData.exchange(
                        "http://localhost:8080/services/settings/api/getBranchInsuranceMapByBranchIdAndInsuranceId" +
                            "?branchId={branchId}&insuranceId={insuranceId}",
                        HttpMethod.GET,
                        entityData,
                        String.class,
                        branchId, insuranceId);
                    if (responseData.getStatusCode() == HttpStatus.OK) {
                        responseBody = (String) responseData.getBody();
                    } else {
                        message = "API Error: No Response Data Available";
                        return Mono.just(new ResponseDTO(status, message, responseList));
                    }
                } else {
                    message = "Missing Access Token";
                    return Mono.just(new ResponseDTO(status, message, responseList));
                }
                JSONObject branchInsMapJson = (JSONObject) parser.parse(responseBody == null ? "{}" : responseBody);
//            //System.out.println("branchInsMapJson=" + branchInsMapJson);

                //----------- Get branch details ------------
                if (!branchInsMapJson.isEmpty() && branchInsMapJson.get("data") != null
                    && ((JSONArray) branchInsMapJson.get("data")).size() > 0) {
                    JSONObject branchInfoRow = (JSONObject) ((JSONArray) branchInsMapJson.get("data")).get(0);

//                //System.out.println("branchInfoRow=>" + branchInfoRow);

                    Long insuranceId = branchInfoRow.get("insuranceId") == null ?
                        0 : (Long) branchInfoRow.get("insuranceId");
                    obj.setPrimaryInsurerId(insuranceId);

                    //----------- Get Insurance Master (Claim Program, Insurance Group)-------------
                    String responseInsuranceBody = "[]";
                    Long primaryInsID = obj.getPrimaryInsurerId() == null ? 0L : obj.getPrimaryInsurerId();
                    Long secondaryInsID = obj.getSecondaryInsurerId() == null ? 0L : obj.getSecondaryInsurerId();
                    Long tertiaryInsID = obj.getTertiaryInsurerId() == null ? 0L : obj.getTertiaryInsurerId();
                    if (!token.equalsIgnoreCase("NOT_AVAILABLE")) {
                        String insuranceIds = primaryInsID + "," + secondaryInsID + "," + tertiaryInsID;

                        RestTemplate restTemplateData = new RestTemplate();
                        HttpHeaders headersData = new HttpHeaders();
                        headersData.add("Authorization", "Bearer " + token);
                        HttpEntity entityData = new HttpEntity<>(headersData);
                        ResponseEntity responseData = restTemplateData.exchange(
                            "http://localhost:8080/services/settings/api/getInsuranceMasterByInsuranceIdList" +
                                "?insuranceIds={insuranceIds}",
                            HttpMethod.GET,
                            entityData,
                            String.class,
                            insuranceIds);
                        if (responseData.getStatusCode() == HttpStatus.OK) {
                            responseInsuranceBody = (String) responseData.getBody();
                        } else {
                            message = "API Error: No Response Data Available";
                            return Mono.just(new ResponseDTO(status, message, responseList));
                        }
                    } else {
                        message = "Missing Access Token";
                        return Mono.just(new ResponseDTO(status, message, responseList));
                    }
                    JSONObject insMaster = (JSONObject) parser.parse(responseInsuranceBody == null ? "{}" : responseInsuranceBody);
                    //System.out.println("insMaster=" + insMaster);

                    //----------- Get branch details ------------
                    if (!insMaster.isEmpty() && insMaster.get("data") != null
                        && ((JSONArray) insMaster.get("data")).size() > 0) {
                        //System.out.println("--------------------- In insMaster IF");
                        for (int i = 0; i < ((JSONArray) insMaster.get("data")).size(); i++) {
                            //System.out.println("--------------------- In insMaster Loop");
                            JSONObject insMstRow = (JSONObject) ((JSONArray) insMaster.get("data")).get(i);
                            //System.out.println("--------- In insMaster Loop ---- Fetch Row:: " + insMstRow);
                            if (!insMstRow.isEmpty() && ((Long) insMstRow.get("insuranceId")).equals(primaryInsID)) {    //!insMstRow.isEmpty() &&
                                //System.out.println("--------------------- In get(\"insurance_id\").equals(primaryInsID) condition ----");
                                obj.setPrimaryInsuranceGroupId((Long) insMstRow.get("insuranceGroupId"));
                                obj.setPrimaryInsuranceGroupName((String) insMstRow.get("insuranceGroupName"));
                                obj.setPrimaryClaimProgram((String) insMstRow.get("claimProgramName"));
                            }
                            if (!insMstRow.isEmpty() && ((Long) insMstRow.get("insuranceId")).equals(secondaryInsID)) {
                                //System.out.println("--------------------- In get(\"insurance_id\").equals(secondaryInsID) condition ----");
                                obj.setSecondaryInsuranceGroupId((Long) insMstRow.get("insuranceGroupId"));
                                obj.setSecondaryInsuranceGroupName((String) insMstRow.get("insuranceGroupName"));
                                obj.setSecondaryClaimProgram((String) insMstRow.get("claimProgramName"));
                            }
                            if (!insMstRow.isEmpty() && ((Long) insMstRow.get("insuranceId")).equals(tertiaryInsID)) {
                                //System.out.println("--------------------- In get(\"insurance_id\").equals(tertiaryInsID) condition ----");
                                obj.setTertiaryInsuranceGroupId((Long) insMstRow.get("insuranceGroupId"));
                                obj.setTertiaryInsuranceGroupName((String) insMstRow.get("insuranceGroupName"));
                                obj.setTertiaryClaimProgram((String) insMstRow.get("claimProgramName"));
                            }

//                        //System.out.println("--------- insMstRow.get(\"insuranceId\") =" + insMstRow.get("insuranceId"));

                            //System.out.println("----- Fetch Row Last :: insMstRow=" + insMstRow);
                        }

                    } else {
                        message = "Given insurances are not available.";
                        return Mono.just(new ResponseDTO(status, message, responseList));
                    }
                    //----------- Get Insurance Master (Claim Program, Insurance Group) -------------


                } else {
                    message = "Branch is not map with given Insurance.";
                    return Mono.just(new ResponseDTO(status, message, responseList));
                }
            }

            //System.out.println("SOInsuranceObj=" + obj);
            //----------- Get branch details ------------

            //------------Saving Data-----------------------------------------------------
            if (obj.getSalesOrderInsuranceDetailsId() == null || obj.getSalesOrderInsuranceDetailsId() == 0) {
                obj.setCreatedDate(LocalDate.now());
                obj.setCreatedById(1L);   //----- Data taken from login user service
                obj.setCreatedByName("Abhijit Chowdhury"); //----- Data taken from login user service
                obj.setSalesOrderInsuranceDetailsId(null);
                obj.setSalesOrderInsuranceDetailsUuid(UUID.randomUUID());
                obj.setStatus("Active");
                return salesOrderInsuranceDetailsRepositoryExtended
                    .save(salesOrderInsuranceDetailsMapper.toEntity(obj))
                    .map(salesOrderInsuranceDetailsMapper::toDto).map(
                        i -> new ResponseDTO(true, "Successfully Saved", List.of(i)));
            } else {
                List<SalesOrderInsuranceDetails> salesOrderInsuranceDetailsForUpdate = salesOrderInsuranceDetailsRepositoryExtended.
                    getSOInsuranceDetailsByInsuranceUUID(obj.getSalesOrderInsuranceDetailsUuid()).collectList().toFuture().get();
                if (salesOrderInsuranceDetailsForUpdate.size() > 0) {
                    SalesOrderInsuranceDetails updateObj = salesOrderInsuranceDetailsForUpdate.get(0);
                    BeanUtils.copyProperties(salesOrderInsuranceEntryParameterDTO, updateObj);
                    updateObj.setUpdatedDate(LocalDate.now());
                    updateObj.setUpdatedById(1L);      //----- Data taken from login user service
                    updateObj.setUpdatedByName("Abhijit Chowdhury");   //----- Data taken from login user service
                    return salesOrderInsuranceDetailsRepositoryExtended
                        .save(salesOrderInsuranceDetailsMapper.toEntity(obj))
                        .map(salesOrderInsuranceDetailsMapper::toDto).map(
                            i -> new ResponseDTO(true, "Successfully Saved", List.of(i)));
                } else {
                    message = "Update failed! Sales_Order_Insurance_Details does not exist.";
                    return Mono.just(new ResponseDTO(status, message, responseList));
                }
            }

            //-----------------------------Saving Data----------------------------------------------

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
//        return Mono.just(new ResponseDTO(status, "Test Operation", responseList));
    }

    @Override
    public Mono<ServiceOutcome<SalesOrderInsuranceDetailsDTO>> updateSOSecondaryInsuranceInfo(SalesOrderInsuranceDetailsDTO salesOrderInsuranceDetailsDTO) {
        if (salesOrderInsuranceDetailsDTO.getSalesOrderId() != null && salesOrderInsuranceDetailsDTO.getSalesOrderId() != 0) {
            return salesOrderInsuranceDetailsRepositoryExtended.save(salesOrderInsuranceDetailsMapper.toEntity(salesOrderInsuranceDetailsDTO))
                .map(salesOrderInsuranceDetailsMapper::toDto)
                .map(updatedObj -> new ServiceOutcome<>(updatedObj, true, "Successfully Saved."));
        } else {
            return Mono.just(new ServiceOutcome(null, false, "Sales Order Id should not be null."));
        }
    }

    @Override
    public Mono<ServiceOutcome> updateSOInsuranceStatus(Long soInsId) {
        AtomicInteger count = new AtomicInteger(0);

        return salesOrderInsuranceDetailsRepositoryExtended.findById(soInsId)
            .map(existingSoInsuranceObj ->{
                System.out.println("====existingSoInsuranceObj.existingSoInsuranceObj===="+existingSoInsuranceObj.getSalesOrderInsuranceDetailsId());
                existingSoInsuranceObj.setInsuranceVerificationStatus("Y");
                existingSoInsuranceObj.setUpdatedDate(LocalDate.now());
                existingSoInsuranceObj.setUpdatedById(31L);
                existingSoInsuranceObj.setUpdatedByName("Falguni"+ count.getAndIncrement());
                return existingSoInsuranceObj;
            }).flatMap(salesOrderInsuranceDetailsRepositoryExtended::save)
            .map(updatedObj -> {
                System.out.println("======Final Updated soInsuranceDetails data::===="+updatedObj);
                return new ServiceOutcome(updatedObj, true ,"Sales Order Insurance Details Updated.");
            }).switchIfEmpty(Mono.just(new ServiceOutcome(null, false ,"Unable to Update.")));
    }
}
