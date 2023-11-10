package com.sunknowledge.dme.rcm.service.soentryandsearch;

import com.dropbox.core.InvalidAccessTokenException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunknowledge.dme.rcm.amazon.BucketName;
import com.sunknowledge.dme.rcm.amazon.FileStore;
import com.sunknowledge.dme.rcm.application.properties.FileUploadConfigProperties;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.PatientDocumentSoMap;
import com.sunknowledge.dme.rcm.repository.PatientDocumentSoMapRepositoryExtended;
import com.sunknowledge.dme.rcm.repository.SalesOrderMasterSearchRepositoryExtended;
import com.sunknowledge.dme.rcm.securityutil.InternalAccessTokenUtilities;
import com.sunknowledge.dme.rcm.service.dto.PatientDocumentSoMapDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.DocumentsBySoIdOutputDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.FileDetailsOutputExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.PatientDocumentCopyDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.PatientDocumentDetailsInputExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.PatientSoIdAndSoNoOutputExtendedDTO;
import com.sunknowledge.dme.rcm.service.impl.patientdocuments.PatientDocumentsManagementServiceImpl;
import com.sunknowledge.dme.rcm.service.mapper.PatientDocumentSoMapMapper;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Primary
@Service
public class PatientDocumentSoMapServiceExtendedImpl implements PatientDocumentSoMapServiceExtended {

    private final Logger log = LoggerFactory.getLogger(PatientDocumentSoMapServiceExtendedImpl.class);

    @Autowired
    PatientDocumentSoMapRepositoryExtended patientDocumentSoMapRepositoryExtended;
    @Autowired
    PatientDocumentSoMapMapper patientDocumentSoMapMapper;
    @Autowired
    FileUploadConfigProperties fileUploadConfigProperties;

    @Autowired
    PatientDocumentsManagementServiceImpl patientDocumentsManagementServiceImpl;

    @Autowired
    SalesOrderMasterSearchRepositoryExtended salesOrderMasterSearchRepositoryExtended;
    @Autowired
    FileStore fileStore;

    @Override
    public Mono<PatientDocumentSoMapDTO> save(PatientDocumentSoMapDTO patientDocumentSoMapDTO) {
        return null;
    }

    @Override
    public Mono<PatientDocumentSoMapDTO> update(PatientDocumentSoMapDTO patientDocumentSoMapDTO) {
        return null;
    }

    @Override
    public Mono<PatientDocumentSoMapDTO> partialUpdate(PatientDocumentSoMapDTO patientDocumentSoMapDTO) {
        return null;
    }

    @Override
    public Flux<PatientDocumentSoMapDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Mono<Long> countAll() {
        return null;
    }

    @Override
    public Mono<PatientDocumentSoMapDTO> findOne(Long id) {
        return null;
    }

    @Override
    public Mono<Void> delete(Long id) {
        return null;
    }

    @Override
    public Flux<PatientDocumentSoMap> getPatientIdNoBySoNo(String soNo) {
        System.out.println("======soNo======"+soNo);
        return patientDocumentSoMapRepositoryExtended.findBySoNo(soNo);
    }

    @Override
    public Flux<ResponseDTO> attachPatientDocumentsSoDataBySoNo(String parameterValue, String soNo, String patientIdNumber,
                                                                List<PatientDocumentSoMap> patientDocumentSoMapAllDataList,
                                                                PatientSoIdAndSoNoOutputExtendedDTO patientSoIdAndSoNoOutputExtendedDTO) throws ExecutionException, InterruptedException, IOException, ParseException {
        String patientIdNo = patientIdNumber!= null ? patientIdNumber : null;
        if(patientIdNo==null || patientIdNo.equalsIgnoreCase("")){
            return Flux.just(new ResponseDTO<List<PatientDocumentSoMap>>(false,"Patient Id No Does Not exist for soNo: "+soNo,null));
        }

        List<String> duplicate = new ArrayList<>();
        if(!patientIdNo.equals("")) {
            duplicate = patientDocumentSoMapAllDataList.stream().map(x -> x.getPatientDocumentId() + "_" + x.getSoNo()).collect(Collectors.toList());
        }
        log.info("patientIdNo======>"+patientIdNo);
        //System.out.println("duplicate======>"+duplicate);
        ResponseDTO responseBody = getPatientDocumentsDataByPatientIdNo(patientIdNo, "patientIdNo");
        List<LinkedHashMap<String, Object>> patientDocumentsList = (List<LinkedHashMap<String, Object>>) responseBody.getData();

        return attachPatientDocumentsSoData(patientIdNo, patientDocumentsList, duplicate, Long.valueOf(patientSoIdAndSoNoOutputExtendedDTO.getSoId()), soNo);
    }

    public Flux<ResponseDTO> attachPatientDocumentsSoDataByPatientUuid(String parameterValue, String soNo, List<PatientDocumentSoMap> patientDocumentSoMapList,
                                                                       List<PatientDocumentSoMap> patientDocumentSoMapAllDataList,
                                                                       PatientSoIdAndSoNoOutputExtendedDTO patientSoIdAndSoNoOutputExtendedDTO) throws ExecutionException, InterruptedException, IOException, ParseException {
        log.info("=====attachPatientDocumentsSoDataByPatientUuid=====");
        String patientIdNo = null;

        ResponseDTO responseBody = getPatientDocumentsDataByPatientIdNo(parameterValue, "patientUuid");
        log.info("=========responseBody========="+responseBody);
        List<LinkedHashMap<String, Object>> patientDocumentsList = (List<LinkedHashMap<String, Object>>) responseBody.getData();
        for (LinkedHashMap<String, Object> obj : patientDocumentsList) {
            patientIdNo = (String)  obj.get("patientIdNo");
            break;
        }

        patientIdNo = patientDocumentSoMapList.size() > 0 ? patientDocumentSoMapList.get(0).getPatientIdNo() : patientIdNo;
        List<String> duplicate = new ArrayList<>();
        if(!patientIdNo.equals("")){
            duplicate = patientDocumentSoMapAllDataList.stream().map(x->x.getPatientDocumentId()+"_"+x.getSoNo()).collect(Collectors.toList());
        }

        return attachPatientDocumentsSoData(patientIdNo, patientDocumentsList, duplicate, Long.valueOf(patientSoIdAndSoNoOutputExtendedDTO.getSoId()), soNo);
    }

    public Flux<ResponseDTO> attachPatientDocumentsSoDataByPatientIdNo(String parameterValue, String soNo, List<PatientDocumentSoMap> patientDocumentSoMapAllDataList,
                                                                       PatientSoIdAndSoNoOutputExtendedDTO patientSoIdAndSoNoOutputExtendedDTO) throws ExecutionException, InterruptedException, IOException, ParseException {
        log.info("=====attachPatientDocumentsSoDataByPatientIdNo=====");
        String patientIdNo = parameterValue;
        //patientDocumentSoMapObj.subscribe(System.out::println);
        List<String> duplicate = new ArrayList<>();
        if(!patientIdNo.equals("")){
            duplicate = patientDocumentSoMapAllDataList.stream().map(x->x.getPatientDocumentId()+"_"+x.getSoNo()).collect(Collectors.toList());
        }

        ResponseDTO responseBody = getPatientDocumentsDataByPatientIdNo(parameterValue, "patientIdNo");
        List<LinkedHashMap<String, Object>> patientDocumentsList = (List<LinkedHashMap<String, Object>>) responseBody.getData();

        return attachPatientDocumentsSoData(patientIdNo, patientDocumentsList, duplicate, Long.valueOf(patientSoIdAndSoNoOutputExtendedDTO.getSoId()), soNo);
    }

    public Flux<ResponseDTO> attachPatientDocumentsSoDataByPatientDocumentNo(String parameterValue, String soNo, List<PatientDocumentSoMap> patientDocumentSoMapAllDataList,
                                                                             PatientSoIdAndSoNoOutputExtendedDTO patientSoIdAndSoNoOutputExtendedDTO) throws ExecutionException, InterruptedException, IOException, ParseException {
        log.info("=====attachPatientDocumentsSoDataByPatientDocumentId=====");
        List<String> duplicate = new ArrayList<>();

        String patientDocumentNo = parameterValue;
        String patientIdNo = null;
        List<LinkedHashMap<String, Object>> patientDocumentsList = new ArrayList<>();
        if(!patientDocumentNo.equals("")){
            ResponseDTO responseBody = getPatientDocumentsDataByPatientIdNo(patientDocumentNo, "patientDocumentNo");
            patientDocumentsList = (List<LinkedHashMap<String, Object>>) responseBody.getData();
            for (LinkedHashMap<String, Object> obj : patientDocumentsList) {
                patientIdNo = (String)  obj.get("patientIdNo");
                break;
            }
            duplicate = patientDocumentSoMapAllDataList.stream().map(x->x.getPatientDocumentId()+"_"+x.getSoNo()).collect(Collectors.toList());

        }
        return attachPatientDocumentsSoData(patientIdNo, patientDocumentsList, duplicate, Long.valueOf(patientSoIdAndSoNoOutputExtendedDTO.getSoId()), soNo);
    }
    @Override
    public Flux<ResponseDTO> attachPatientDocumentsSoData(String patientIdNo, List<LinkedHashMap<String, Object>> patientDocumentsList,List<String> duplicate, Long soId,  String soNo) throws ExecutionException, InterruptedException, IOException, ParseException {
        log.info("==========Start attachPatientDocumentsSoData============");
        List<PatientDocumentSoMapDTO> savedPatientDocumentSoMapDTO = new ArrayList<>();
        log.info("=======duplicate======="+duplicate);
        for (LinkedHashMap<String, Object> obj : patientDocumentsList) {
            log.info("=======Pair======="+obj.get("patientDocumentId")+"_"+soNo);
            if(!duplicate.contains(obj.get("patientDocumentId")+"_"+soNo)){
                PatientDocumentSoMapDTO patientDocumentSoMapDTO = new PatientDocumentSoMapDTO();

                patientDocumentSoMapDTO.setPatientId(Long.valueOf((Integer) obj.get("patientId")));
                patientDocumentSoMapDTO.setPatientIdNo((String) obj.get("patientIdNo"));
                patientDocumentSoMapDTO.setPatientDocumentId(Long.valueOf((Integer) obj.get("patientDocumentId")));
                patientDocumentSoMapDTO.setPatientDocumentNo((String) obj.get("patientDocumentNo"));
                patientDocumentSoMapDTO.setSoId(soId);
                patientDocumentSoMapDTO.setSoNo(soNo);
                patientDocumentSoMapDTO.setStatus("Active");
                patientDocumentSoMapDTO.setUploadedById(31L);
                patientDocumentSoMapDTO.setUploadedByName("Falguni");
                patientDocumentSoMapDTO.setUploadedDate(LocalDate.now());
                /*return patientDocumentSoMapRepositoryExtended
                    .save(patientDocumentSoMapMapper.toEntity(patientDocumentSoMapDTO))
                    .flux()
                    .map(patientDocumentSoMapMapper::toDto).map(
                        i -> {
                            if(i.getPatientDocumentSoMapId()!=null){
                                return new ResponseDTO(true, "Successfully Saved.", i);
                            }else{
                                return new ResponseDTO(false, "Error Occurred.", null);
                            }
                        });*/
                Mono<PatientDocumentSoMap> patientDocumentSoMap = patientDocumentSoMapRepositoryExtended.save(patientDocumentSoMapMapper.toEntity(patientDocumentSoMapDTO));
                try {
                    savedPatientDocumentSoMapDTO.add(patientDocumentSoMapMapper.toDto(patientDocumentSoMap.toFuture().get()));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return Flux.just(new ResponseDTO<>(
            savedPatientDocumentSoMapDTO != null ? true : false, savedPatientDocumentSoMapDTO.size() >0 ? "Data Saved Sucessfully" : "Data Not Saved", savedPatientDocumentSoMapDTO
        ));
    }
    @Override
    public PatientDocumentSoMapDTO savePatientDocumentSoMap(PatientDocumentSoMapDTO patientDocumentSoMapDTO) {
        Mono<PatientDocumentSoMap> patientDocumentSoMap = patientDocumentSoMapRepositoryExtended.save(patientDocumentSoMapMapper.toEntity(patientDocumentSoMapDTO));
        try {
            return patientDocumentSoMapMapper.toDto(patientDocumentSoMap.toFuture().get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseDTO getPatientDocumentsDataByPatientIdNo(String patientIdNo, String operationType) throws IOException, ParseException {
        ObjectMapper mapper = new ObjectMapper();
        ResponseDTO responseBody = new ResponseDTO();
        try {
            String accessToken = InternalAccessTokenUtilities.getAccessToken();
            JSONParser parser = new JSONParser();
            JSONObject accessTokenJson = (JSONObject) parser.parse(accessToken);
            String token = accessTokenJson.get("access_token").toString();
            if (!token.equalsIgnoreCase("NOT_AVAILABLE")) {
                CommonUtilities commonUtilitiesObj = new CommonUtilities();
                Properties propData = commonUtilitiesObj.readPropertiesFile("/project-properties-files/url_config.properties");

                String url = propData.getProperty("patient_documents_url");
                String paramValue = "?parameterValue=" + patientIdNo + "&operationType=" + operationType;
                String completeUrl = url + paramValue;

                System.out.println("completeUrl " + completeUrl);

                RestTemplate restTemplateData = new RestTemplate();
                HttpHeaders headersData = new HttpHeaders();
                headersData.add("Authorization", "Bearer " + token);
                HttpEntity entityData = new HttpEntity<>(headersData);
                ResponseEntity responseData = restTemplateData.exchange(
                    completeUrl,
                    HttpMethod.GET,
                    entityData,
                    Object.class
                );

                if (responseData.getStatusCode() == HttpStatus.OK) {
                    responseBody = mapper.convertValue(responseData.getBody(), ResponseDTO.class);
                    responseBody.setStatus(true);
                    responseBody.setMessage("Successfully Fetched.");
                    responseBody.setData(responseBody.getData());

                } else {
                    responseBody.setStatus(false);
                    responseBody.setMessage("API Error: No Response Data Available");
                    responseBody.setData(new JSONArray());
                }
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

    @Override
    public Flux<PatientDocumentSoMap> uploadPatientDocument(Flux<FilePart> patientFilePartFlux, Long soId, String soNo, UUID patientUUID
        , String patientDocumentStatus, String description, String documentType) {
        String documentName = UUID.randomUUID().toString();
        String patientDocumentFile = fileUploadConfigProperties.getPatientDocumentsPDDorPIDorPMRProperties().getLocation();

        Path patientDocumentFilePath = Path.of(patientDocumentFile);
        System.out.println("patientDocumentStatus Service Impl " + patientDocumentStatus);
        CommonUtilities commonUtilitiesObj = new CommonUtilities();
        Properties propData = null;
        try {
            propData = commonUtilitiesObj.readPropertiesFile("/project-properties-files/document_management.properties");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        boolean isCloudStorage = Boolean.parseBoolean(propData.getProperty("is_cloud_storage"));
        System.out.println("isCloudStorage "+isCloudStorage);

        List<String> documentNameList = new ArrayList<>();
        return patientFilePartFlux.flatMapSequential(filePart -> {
                return DataBufferUtils.join(filePart.content())
                    .map(dataBuffer -> {
                        UUID uuid = UUID.randomUUID();
                        documentNameList.add(uuid.toString());
                        InputStream targetStream = new ByteArrayInputStream(dataBuffer.asByteBuffer().array());
                        if(isCloudStorage) {
                            Map<String, String> metadata = new HashMap<>();
                            String path = String.format("%s/%s", BucketName.BUCKET_NAME.getPatientServiceBucket(), "qrCode");
                            return fileStore.upload(path, uuid + ".pdf", Optional.of(metadata), targetStream);
                        }else {
                            String tempPatientDocumentFile = patientDocumentFile + "/" + uuid.toString() + ".pdf";
                            CountDownLatch countDownLatch = new CountDownLatch(1);
                            countDownLatch.countDown();
                            try {
                                Files.copy(targetStream, Path.of(tempPatientDocumentFile));
                                targetStream.close();
                                countDownLatch.await();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            return uuid;
                        }
                    });
            }).buffer()
            .flatMap(dataBufferStrList->{
                System.out.println("documentNameList "+documentNameList);
                ResponseDTO<List<PatientDocumentCopyDTO>> obj = (uploadPatientDocumentFromSO(documentNameList.toString(), patientUUID,
                    patientDocumentStatus, description, documentType,isCloudStorage, "Upload"));
                System.out.println(" Returned value of patient ==== "+ obj.getData());

                PatientDocumentSoMapDTO savedPatientDocumentSoMapDTO = new PatientDocumentSoMapDTO();
                if (obj != null) {
                    Flux patientDocumentCopyDTOFlux = Mono.just(obj.getData()).flatMapMany(Flux::fromIterable);
                    //Flux fluxData = Flux.fromIterable(obj.getData());
                    return patientDocumentCopyDTOFlux.flatMapSequential(data ->{
                        LinkedHashMap patientDocumentData = (LinkedHashMap) data;
                        //PatientDocumentCopyDTO patientDocumentData = data;
                        if (patientDocumentData != null) {
                            PatientDocumentSoMapDTO patientDocumentSoMapDTO = new PatientDocumentSoMapDTO();
                            patientDocumentSoMapDTO.setPatientDocumentId(Long.valueOf((Integer) patientDocumentData.get("patientDocumentId")));
                            patientDocumentSoMapDTO.setPatientId(Long.valueOf((Integer) patientDocumentData.get("patientId")));
                            patientDocumentSoMapDTO.setPatientIdNo((String) patientDocumentData.get("patientIdNo"));
                            patientDocumentSoMapDTO.setPatientDocumentNo((String) patientDocumentData.get("patientDocumentNo"));
                            patientDocumentSoMapDTO.setSoId(soId);
                            patientDocumentSoMapDTO.setSoNo(soNo);

                            patientDocumentSoMapDTO.setStatus("Active");
                            patientDocumentSoMapDTO.setUploadedById(1L);
                            patientDocumentSoMapDTO.setUploadedByName("Abhay Api Test");
                            patientDocumentSoMapDTO.setUploadedDate(LocalDate.now());
                            return patientDocumentSoMapRepositoryExtended.save(patientDocumentSoMapMapper.toEntity(patientDocumentSoMapDTO))
                                .map(patientDocument -> patientDocument)
                                .switchIfEmpty(Mono.just(new PatientDocumentSoMap()));
                        }else{
                            return null;
                        }
                    });
                }else{
                    return null;
                }
            });
    }
    @Override
    public Flux<String> getFileWithPathByPatientDocumentUuid(List<UUID> patientDocumentUuid) {
        return patientDocumentSoMapRepositoryExtended.getFileWithPathByPatientDocumentUuid(patientDocumentUuid);
    }

    @Override
    public Mono<ResponseEntity<ByteArrayResource>> downloadPatientDocument(List<Path> filePaths) throws IOException {
        return Mono.fromSupplier(() -> {
            try {
                // Create a ZIP output stream
                ByteArrayOutputStream zipStream = new ByteArrayOutputStream();
                ZipOutputStream zipOutputStream = new ZipOutputStream(zipStream);

                for (Path pdfFile : filePaths) {
                    byte[] decodedBytes = Files.readAllBytes(pdfFile);
                    ByteArrayResource pdfFileResource = new ByteArrayResource(decodedBytes);
                    // Add each PDF to the ZIP archive with a unique entry name

                    zipOutputStream.putNextEntry(new ZipEntry(pdfFile.getFileName().toString()));
                    pdfFileResource.getInputStream().transferTo(zipOutputStream);
                    zipOutputStream.closeEntry();
                }

                // Close the ZIP output stream
                zipOutputStream.close();

                // Create a ByteArrayResource from the ZIP data
                byte[] zipData = zipStream.toByteArray();
                ByteArrayResource resource = new ByteArrayResource(zipData);

                // Create the HTTP response
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=PatientDocuments.zip");

                return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
            } catch (IOException e) {
                // Handle the exception here
                e.printStackTrace();
                return ResponseEntity.badRequest().build();
            }
        });

    }

    private ResponseDTO uploadPatientDocumentFromSO(String documentNameList, UUID patientUUID, String patientDocumentStatus,
                                                    String description, String documentType,boolean isCloudStorage, String operationType) {
        ObjectMapper mapper = new ObjectMapper();
        ResponseDTO responseBody = new ResponseDTO();
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

                String url = propData.getProperty("patient_url");
                String param = "/{documentNames}/{patientUUID}/{patientDocumentStatus}/{description}/{documentType}/{isCloudStorage}/{operationType}";
                String paramValue = "?documentNames=" + documentNameList + "&patientUUID=" + patientUUID + "&patientDocumentStatus="
                    + patientDocumentStatus + "&description=" + description + "&documentType=" + documentType + "&isCloudStorage=" + isCloudStorage+ "&operationType=" + operationType;
                String completeUrl = url + param + paramValue;
                System.out.println("completeUrl " + completeUrl);

                RestTemplate restTemplateData = new RestTemplate();
                HttpHeaders headersData = new HttpHeaders();
                headersData.add("Authorization", "Bearer " + token);
                HttpEntity entityData = new HttpEntity<>(headersData);
                ResponseEntity responseData = restTemplateData.exchange(
                    completeUrl,
                    HttpMethod.POST,
                    entityData,
                    Object.class,
                    documentNameList,
                    patientUUID,
                    patientDocumentStatus,
                    description,
                    documentType,
                    isCloudStorage,
                    operationType);
                if (responseData.getStatusCode() == HttpStatus.OK) {
                    responseBody = mapper.convertValue(responseData.getBody(), ResponseDTO.class);
                } else {
                    responseBody.setStatus(false);
                    responseBody.setMessage("API Error: No Response Data Available");
                    responseBody.setData(new JSONArray());
                }
            } else {
                responseBody.setStatus(false);
                responseBody.setMessage("Missing Access Token");
                responseBody.setData(new JSONArray());
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
        if (responseBody.getStatus()) {
            return responseBody;
        } else {
            return null;
        }
    }

    @Override
    public Mono<ResponseDTO>getAllPatientFiles() throws IOException {
        ResponseDTO responseDTO = new ResponseDTO();

        log.info("==================getAllPatientFiles() :: STARTED=======================");
        File directoryPath = new File(fileUploadConfigProperties.getDmePatientDocuments().getLocation());
        FileFilter textFileFilter = new FileFilter(){
            public boolean accept(File file) {
                boolean isFile = file.isFile();
                if (isFile) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        File filesList[] = directoryPath.listFiles(textFileFilter);

        List<FileDetailsOutputExtendedDTO> fileDetailsList = new ArrayList<>();
        for(File file : filesList) {
            String fileName = file.getName();
            String filePath = file.getAbsolutePath();
            long fileSize = file.length();
            String fileType = Files.probeContentType(file.toPath());

            FileDetailsOutputExtendedDTO fileDetails = new FileDetailsOutputExtendedDTO();
            fileDetails.setFileName(fileName);
            fileDetails.setFilePath(filePath);
            fileDetails.setFileSize(fileSize);
            fileDetails.setFileType(fileType);

            fileDetailsList.add(fileDetails);

            File source = new File(fileUploadConfigProperties.getDmePatientDocuments().getLocation() + "/" + file.getName());
            System.out.println("===========source==========="+source);

            /*
            File source = new File(fileUploadConfigProperties.getPatientAllDocumentsProperties().getLocation() + "/" + file.getName());
            File destination = null;
            PDDocument document = PDDocument.load(file);
            ServiceOutcome<String> outcome = new CommonPDFStubs().checkOwnOrOutsideDocument(document, tempPath); //No Need to check as its already external
            */
        }
        if(fileDetailsList!= null && fileDetailsList.size() > 0){
            responseDTO.setStatus(true);
            responseDTO.setMessage("Successfully Fetched.");
            responseDTO.setData(fileDetailsList);
        }else{
            responseDTO.setStatus(false);
            responseDTO.setMessage("Failed to Attach.");
            responseDTO.setData(fileDetailsList);
        }
        return Mono.just(responseDTO);
    }

    @Override
    public Mono<ResponseDTO> movePatientDocumentsPddAndPidAndPmrToSpecializedDirectory(PatientDocumentDetailsInputExtendedDTO patientDocumentDetailsInputExtendedDTO,
                                                                                       PatientSoIdAndSoNoOutputExtendedDTO patientSoIdAndSoNoOutputExtendedDTO) throws IOException, ExecutionException, InterruptedException {
        ResponseDTO responseDTO = new ResponseDTO();
        String documentNames = patientDocumentDetailsInputExtendedDTO.getDocumentNames().replaceAll("\\[|\\]|\\s", "");
        // Split by commas and trim each UUID
        String[] documentNameListArr = documentNames.split(",");

        System.out.println("==========patientSoIdAndSoNoOutputExtendedDTO==========="+patientSoIdAndSoNoOutputExtendedDTO);
        if(patientSoIdAndSoNoOutputExtendedDTO.getSoNo()!=null){
            if(!patientDocumentDetailsInputExtendedDTO.getSoNo().equals(patientSoIdAndSoNoOutputExtendedDTO.getSoNo())){
                responseDTO.setStatus(false);
                responseDTO.setMessage("soNo mismatch with respected soUuid.");
                responseDTO.setData(null);
                return Mono.just(responseDTO);
            }
        }

        //String documentName = patientDocumentDetailsInputExtendedDTO.getDocumentName();
        UUID patientUUID = patientDocumentDetailsInputExtendedDTO.getPatientUUID();
        String patientDocumentStatus = "active";
        String documentType = patientDocumentDetailsInputExtendedDTO.getDocType();
        String description = documentType+ " documents description";
        boolean isCloudStorage = patientDocumentDetailsInputExtendedDTO.getIsCloudStorage() ? true : false;

        if(patientDocumentDetailsInputExtendedDTO.getIsCloudStorage()){
            ArrayList<String> documentNameList = new ArrayList<>();
            for(int i = 0; i< documentNameListArr.length; i++){
                documentNameList.add(documentNameListArr[i]);
            }
        }else{
            for(int i = 0; i< documentNameListArr.length; i++){
                String documentName = documentNameListArr[i];
                File source = new File(fileUploadConfigProperties.getDmePatientDocuments().getLocation()+ "/" + documentName+".pdf");
                File destination = new File(fileUploadConfigProperties.getPatientDocumentsPDDorPIDorPMRProperties().getLocation()+ "/" + documentName+".pdf");
                if (!source.exists()) {
                    responseDTO.setStatus(false);
                    responseDTO.setMessage("The file does not exist at the source location.");
                    responseDTO.setData(null);
                    return Mono.just(responseDTO);
                }else{
                    copyFileUsingChannel(source, destination);
                    Files.deleteIfExists(Paths.get(fileUploadConfigProperties.getDmePatientDocuments().getLocation() + "/" + documentName+".pdf"));  //To delete file from source location
                }
            }
        }
        System.out.println("=====patientDocumentDetailsInputExtendedDTO======"+patientDocumentDetailsInputExtendedDTO);

        //ResponseDTO obj = uploadPatientDocumentFromSO(patientDocumentDetailsInputExtendedDTO.getDocumentNames(), patientUUID, patientDocumentStatus, description, documentType,isCloudStorage, patientDocumentDetailsInputExtendedDTO.getOperationType());

        ResponseDTO<List<PatientDocumentCopyDTO>> obj = (uploadPatientDocumentFromSO(patientDocumentDetailsInputExtendedDTO.getDocumentNames(), patientUUID,
            patientDocumentStatus, description, documentType,isCloudStorage, patientDocumentDetailsInputExtendedDTO.getOperationType()));

        System.out.println("======obj======"+obj);
        System.out.println(" Returned value of patient ==== "+ obj.getData());

        if (obj != null) {
            log.info("***File moved, QR code added, file renamed according to document type and data save into t_patient_document***");
            if(patientDocumentDetailsInputExtendedDTO.getPatientUUID() != null || !patientDocumentDetailsInputExtendedDTO.getPatientUUID().equals("")){
                log.info("***When soUUID is there data must be saved into t_patient_document_so_map***");
                if(patientSoIdAndSoNoOutputExtendedDTO.getSoNo()!=null && patientSoIdAndSoNoOutputExtendedDTO.getSoId()!=null){
                    return savePatientDocumentSoMapData(obj, patientSoIdAndSoNoOutputExtendedDTO).collectList()
                        .map(x->{
                            System.out.println("=======I am here to Update======"+x);
                            if(x.size() > 0 && x.get(0).getPatientDocumentSoMapId()!= null){
                                return new ResponseDTO<List<PatientDocumentSoMap>>(true,"Document Successfully moved.",x);
                            }else{
                                return new ResponseDTO<List<PatientDocumentSoMap>>(false,"Error Occurred.",null);
                            }
                        });
                }else{
                    log.info("***File moved, QR code added, file renamed according to document type and data is saved into t_patient_document and soUUID is not there" +
                            "which is not mandatory. Therefore, return successful msg with data***");
                    responseDTO.setStatus(true);
                    responseDTO.setMessage("Document Successfully moved.");
                    responseDTO.setData(obj.getData());
                }
            }
        }else{
            responseDTO.setStatus(false);
            responseDTO.setMessage("Error Occurred.");
            responseDTO.setData(null);
        }
        return Mono.just(responseDTO);
    }
    private Flux<PatientDocumentSoMap> savePatientDocumentSoMapData(ResponseDTO<List<PatientDocumentCopyDTO>> obj, PatientSoIdAndSoNoOutputExtendedDTO patientSoIdAndSoNoOutputExtendedDTO){
        //PatientDocumentSoMapDTO savedPatientDocumentSoMapDTO = new PatientDocumentSoMapDTO();

        if (obj != null) {
            Flux patientDocumentCopyDTOFlux = Mono.just(obj.getData()).flatMapMany(Flux::fromIterable);
            //Flux fluxData = Flux.fromIterable(obj.getData());
            return patientDocumentCopyDTOFlux.flatMapSequential(data ->{
                LinkedHashMap patientDocumentData = (LinkedHashMap) data;
                //PatientDocumentCopyDTO patientDocumentData = data;
                if (patientDocumentData != null) {
                    PatientDocumentSoMapDTO patientDocumentSoMapDTO = new PatientDocumentSoMapDTO();
                    patientDocumentSoMapDTO.setPatientDocumentId(Long.valueOf((Integer) patientDocumentData.get("patientDocumentId")));
                    patientDocumentSoMapDTO.setPatientId(Long.valueOf((Integer) patientDocumentData.get("patientId")));
                    patientDocumentSoMapDTO.setPatientIdNo((String) patientDocumentData.get("patientIdNo"));
                    patientDocumentSoMapDTO.setPatientDocumentNo((String) patientDocumentData.get("patientDocumentNo"));
                    patientDocumentSoMapDTO.setSoId(Long.valueOf(patientSoIdAndSoNoOutputExtendedDTO.getSoId()));
                    patientDocumentSoMapDTO.setSoNo(patientSoIdAndSoNoOutputExtendedDTO.getSoNo());

                    patientDocumentSoMapDTO.setStatus("active");
                    patientDocumentSoMapDTO.setUploadedById(31L);
                    patientDocumentSoMapDTO.setUploadedByName("Falguni");
                    patientDocumentSoMapDTO.setUploadedDate(LocalDate.now());
                    return patientDocumentSoMapRepositoryExtended.save(patientDocumentSoMapMapper.toEntity(patientDocumentSoMapDTO))
                        .map(patientDocument -> patientDocument)
                        .switchIfEmpty(Mono.just(new PatientDocumentSoMap()));
                }else{
                    return null;
                }
            });
        }else{
            return null;
        }
    }

    private static void copyFileUsingChannel(File source, File dest) throws IOException {
        FileChannel sourceChannel = null;
        FileChannel destChannel = null;
        System.out.println("===============CALL to FILE COPY===================");
        try {
            sourceChannel = new FileInputStream(source).getChannel();
            System.out.println("=========sourceChannel========="+sourceChannel);
            destChannel = new FileOutputStream(dest).getChannel();
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        }finally{
            sourceChannel.close();
            destChannel.close();
        }
    }

    @Override
    public Mono<PatientSoIdAndSoNoOutputExtendedDTO> getSoIdAndSoNoBySoUUID(String salesOrderMasterUuid) {
        return salesOrderMasterSearchRepositoryExtended.findSoIdAndSoNoBySoUUID(salesOrderMasterUuid);
    }

    @Override
    public Flux<DocumentsBySoIdOutputDTO> getAllDocumentsBySoID(Long soId) {
        return patientDocumentSoMapRepositoryExtended.findBySoId(soId);
    }
    //getPatientIdNoBySoNo
    @Override
    public Mono<Long> getPatientIdBySoNo(String soNo) {
        return patientDocumentSoMapRepositoryExtended.getPatientIdBySoNo(soNo);
    }

    //getPatientIdNoByPatientId
    @Override
    public Mono<String> getPatientIdNoByPatientId(Long patientId) {
        return patientDocumentSoMapRepositoryExtended.getPatientIdNoByPatientId(patientId);
    }

    @Override
    public Flux<PatientDocumentSoMap> findPatientDocumentsSoDataByPatientIdNo(String patientIdNo) {
        return patientDocumentSoMapRepositoryExtended.findPatientDocumentsSoDataByPatientIdNo(patientIdNo);
    }
}
