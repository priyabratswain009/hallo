package com.sunknowledge.dme.rcm.service.impl.items;

import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.ProcedureCodeMaster;
import com.sunknowledge.dme.rcm.repository.items.ProcedureCodeRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.ProcedureCodeMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.items.ProcedureCodeParameterDTO;
import com.sunknowledge.dme.rcm.service.items.ProcedureCodeMasterServiceExtended;
import com.sunknowledge.dme.rcm.service.mapper.ProcedureCodeMasterMapper;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service("procedureCodeServiceExtendedImpl")
public class ProcedureCodeServiceExtendedImpl implements ProcedureCodeMasterServiceExtended {
    @Autowired
    ProcedureCodeRepositoryExtended procedureCodeRepositoryExtended;
    @Autowired
    ProcedureCodeMasterMapper procedureCodeMasterMapper;

    @Override
    public ResponseDTO saveProcedureCode(ProcedureCodeParameterDTO procedureCodeParameterDTO) {
        try {
            List responseList = new ArrayList<ProcedureCodeMasterDTO>();
            ProcedureCodeMasterDTO procedureCodeMasterDTO = (procedureCodeParameterDTO.getProcedureCodeId() == null ||
                procedureCodeParameterDTO.getProcedureCodeId() == 0) ? new ProcedureCodeMasterDTO() :
                (procedureCodeRepositoryExtended.findById(procedureCodeParameterDTO.getProcedureCodeId()).isPresent() ?
                    procedureCodeMasterMapper.toDto(procedureCodeRepositoryExtended.findById(procedureCodeParameterDTO.getProcedureCodeId()).get()) :
                    new ProcedureCodeMasterDTO());

            BeanUtils.copyProperties(procedureCodeParameterDTO, procedureCodeMasterDTO);
            if (procedureCodeMasterDTO.getProcedureCodeId() == null || procedureCodeMasterDTO.getProcedureCodeId() == 0) {
                procedureCodeMasterDTO.setProcedureCodeId(null);  //procedureCodeRepositoryExtended.findNextId()
                procedureCodeMasterDTO.setCreatedDate(LocalDate.now());
                procedureCodeMasterDTO.setCreatedById(1L);
                procedureCodeMasterDTO.setCreatedByName("Abhijit");
                procedureCodeMasterDTO.setItemProcedureCodeUuid(UUID.randomUUID());
            } else {
                procedureCodeMasterDTO.setUpdatedDate(LocalDate.now());
                procedureCodeMasterDTO.setUpdatedById(1L);
                procedureCodeMasterDTO.setUpdatedByName("Abhijit");
            }
            ProcedureCodeMasterDTO savedProcedureCodeMasterDTO = procedureCodeMasterMapper.toDto(
                procedureCodeRepositoryExtended.save(procedureCodeMasterMapper.toEntity(procedureCodeMasterDTO))
            );
            responseList.add(savedProcedureCodeMasterDTO);
            return new ResponseDTO(true, "Successfully Saved.", responseList, 200);
        } catch (Exception e) {
            return new ResponseDTO(false, "Failed to Save! Data Error.", new ArrayList(), 200);
        }
    }

    @Override
    public ResponseDTO saveProcedureCodeByExternalAPI(String procedureCode) {
        try {
            List responseList = new ArrayList<ProcedureCodeMasterDTO>();
            ProcedureCodeMasterDTO procedureCodeMasterDTO = procedureCodeRepositoryExtended.findByProcedureCode(procedureCode) == null ?
                new ProcedureCodeMasterDTO() : procedureCodeMasterMapper.
                toDto(procedureCodeRepositoryExtended.findByProcedureCode(procedureCode));
            //=========================Call HCPCS Service============================================
            String responseBody = "[]";
            JSONParser parser = new JSONParser();

            CommonUtilities commonUtilitiesObj = new CommonUtilities();
            Properties propData = commonUtilitiesObj.readPropertiesFile("/project-properties-files/url_config.properties");

            String url = propData.getProperty("clinical_url");
            String param = "?terms={procedureCode}";
            String completeUrl = url + param;

            RestTemplate restTemplateData = new RestTemplate();
            HttpHeaders headersData = new HttpHeaders();
            headersData.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity entityData = new HttpEntity<>(headersData);
            ResponseEntity responseData = restTemplateData.exchange(
                completeUrl,
                HttpMethod.GET,
                entityData,
                String.class,
                procedureCode);

            if (responseData.getStatusCode() == HttpStatus.OK) {
                responseBody = (String) responseData.getBody();
            } else {
                String message = "API Error: API Not Available";
                return new ResponseDTO(false, message, responseList, 200);
            }
            JSONArray hcpcsJson = (JSONArray) parser.parse(responseBody == null ? "[]" : responseBody);
            if (hcpcsJson.size() > 0) {
                JSONArray hcpcsJsonList = (JSONArray) hcpcsJson.get(3);
                if (hcpcsJsonList.size() > 0) {
                    JSONArray hcpcsJsonSubList = (JSONArray) ((JSONArray) hcpcsJson.get(3)).get(0);
                    procedureCodeMasterDTO.setProcedureCode((String) hcpcsJsonSubList.get(0));
                    procedureCodeMasterDTO.setItemProcedureCodeDesc((String) hcpcsJsonSubList.get(1));
                    procedureCodeMasterDTO.setStatus("active");
                    if (procedureCodeMasterDTO.getProcedureCodeId() == null || procedureCodeMasterDTO.getProcedureCodeId() == 0) {
                        procedureCodeMasterDTO.setProcedureCodeId(null);  //procedureCodeRepositoryExtended.findNextId()
                        procedureCodeMasterDTO.setCreatedDate(LocalDate.now());
                        procedureCodeMasterDTO.setItemProcedureCodeUuid(UUID.randomUUID());
                    } else {
                        procedureCodeMasterDTO.setUpdatedDate(LocalDate.now());
                    }
                    ProcedureCodeMasterDTO savedProcedureCodeMasterDTO = procedureCodeMasterMapper.toDto(
                        procedureCodeRepositoryExtended.save(procedureCodeMasterMapper.toEntity(procedureCodeMasterDTO))
                    );
                    responseList.add(savedProcedureCodeMasterDTO);
                    return new ResponseDTO(true, "Successfully Saved.", responseList, 200);
                } else {
                    String message = "HCPCS Code Not Available";
                    return new ResponseDTO(false, message, responseList, 200);
                }
            } else {
                String message = "API Error: No Response Data Available";
                return new ResponseDTO(false, message, responseList, 200);
            }
            //=======================================================================================


        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (HttpClientErrorException e) {
            // Client-side errors (4xx)
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
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
    }

    @Override
    public ProcedureCodeMasterDTO getProcedureCodeByCode(String procedureCode) {
        ProcedureCodeMaster obj = procedureCodeRepositoryExtended.findByProcedureCode(procedureCode);
        return obj != null ? procedureCodeMasterMapper.
            toDto(obj) : null;
    }

    @Override
    public ProcedureCodeMasterDTO getProcedureCodeById(Long procedureId) {
        Optional<ProcedureCodeMaster> procedureCodeListOptional = procedureCodeRepositoryExtended.findById(procedureId);
        if (procedureCodeListOptional.isPresent()) {
            return procedureCodeMasterMapper.toDto(procedureCodeListOptional.get());
        } else {
            return new ProcedureCodeMasterDTO();
        }
    }

    @Override
    public List<ProcedureCodeMasterDTO> getProcedureCodeByName(String procedureName) {
        List<ProcedureCodeMaster> procedureCodeList = procedureCodeRepositoryExtended.findByItemProcedureCodeDescLikeIgnoreCase("%" + procedureName + "%");
        if (procedureCodeList != null) {
            return procedureCodeList.stream().filter(x -> x.getStatus().equalsIgnoreCase("active"))
                .map(x -> procedureCodeMasterMapper.toDto(x)).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<ProcedureCodeMasterDTO> getAllProcedureCode() {
        List<ProcedureCodeMaster> procedureCodeList = procedureCodeRepositoryExtended.findAll();
        if (procedureCodeList != null) {
            return procedureCodeList.stream().filter(x -> x.getStatus().equalsIgnoreCase("active"))
                .map(x -> procedureCodeMasterMapper.toDto(x)).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

}
