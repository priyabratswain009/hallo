package com.sunknowledge.dme.rcm.service.soentryandsearch;

import com.dropbox.core.InvalidAccessTokenException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.SalesOrderClinicalDetails;
import com.sunknowledge.dme.rcm.repository.SalesOrderClinicalDetailsRepositoryExtended;
import com.sunknowledge.dme.rcm.securityutil.InternalAccessTokenUtilities;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderClinicalDetailsDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderClinicalEntryParameterDTO;
import com.sunknowledge.dme.rcm.service.mapper.SalesOrderClinicalDetailsMapper;
import liquibase.pro.packaged.T;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Slf4j
@Primary
@Service
public class SalesOrderClinicalDetailsServiceExtendedImpl implements SalesOrderClinicalDetailsServiceExtended{

    @Autowired
    SalesOrderClinicalDetailsRepositoryExtended salesOrderClinicalDetailsRepositoryExtended;
    @Autowired
    SalesOrderMasterServiceExtented salesOrderMasterServiceExtented;
    @Autowired
    SalesOrderClinicalDetailsMapper salesOrderClinicalDetailsMapper;

    private static final DecimalFormat decfor = new DecimalFormat("0.00");

    @Override
    public Mono<SalesOrderClinicalDetailsDTO> save(SalesOrderClinicalDetailsDTO salesOrderClinicalDetailsDTO) {
        return null;
    }

    @Override
    public Mono<SalesOrderClinicalDetailsDTO> update(SalesOrderClinicalDetailsDTO salesOrderClinicalDetailsDTO) {
        return null;
    }

    @Override
    public Mono<SalesOrderClinicalDetailsDTO> partialUpdate(SalesOrderClinicalDetailsDTO salesOrderClinicalDetailsDTO) {
        return null;
    }

    @Override
    public Flux<SalesOrderClinicalDetailsDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Mono<Long> countAll() {
        return null;
    }

    @Override
    public Mono<SalesOrderClinicalDetailsDTO> findOne(Long id) {
        return null;
    }

    @Override
    public Mono<Void> delete(Long id) {
        return null;
    }

    @Override
    public Flux<SalesOrderClinicalDetails> findBySalesOrderId(Long SOID) {
        return salesOrderClinicalDetailsRepositoryExtended.findBySalesOrderId(SOID);
    }

    @Override
    public Flux<SalesOrderClinicalDetails> getSOClinicalByUUID(UUID sOClinicalUUID) {
        return salesOrderClinicalDetailsRepositoryExtended.getSOClinicalByUUID(sOClinicalUUID);
    }

    @Override
    public Long getIDByUUID(UUID sOClinicalUUID) {
        try {
            return salesOrderClinicalDetailsRepositoryExtended.getIDByUUID(sOClinicalUUID).toFuture().get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Mono<SalesOrderClinicalDetails> findByClinicalId(Long id) {
        return salesOrderClinicalDetailsRepositoryExtended.findById(id);
    }

    @Override
    public Mono<ServiceOutcome> saveSOClinicalDetails(SalesOrderClinicalEntryParameterDTO salesOrderClinicalEntryParameterDTO) {
        SalesOrderClinicalDetailsDTO salesOrderClinicalDetailsDTO = new SalesOrderClinicalDetailsDTO();
        ServiceOutcome serviceOutcome = new ServiceOutcome<>();
        //----- Implementing UUID_To_ID Bridge Method ----------
        Long soId = 0L;
        if (salesOrderClinicalEntryParameterDTO.getSalesOrderUUID() != null) {
            try {
                soId = salesOrderMasterServiceExtented.getIDByUUID(salesOrderClinicalEntryParameterDTO.getSalesOrderUUID()).toFuture().get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
            soId = soId != null ? soId : 0L;
        }
        Long soClinicalId = 0L;
        if (salesOrderClinicalEntryParameterDTO.getSalesOrderClinicalDetailsUuid() != null) {
            soClinicalId = getIDByUUID(salesOrderClinicalEntryParameterDTO.getSalesOrderClinicalDetailsUuid());
            soClinicalId = soClinicalId != null ? soClinicalId : 0L;
        }
        salesOrderClinicalDetailsDTO.setSalesOrderId(soId);
        salesOrderClinicalDetailsDTO.setPatientId(salesOrderClinicalEntryParameterDTO.getPatientId());
        salesOrderClinicalDetailsDTO.setPatientWeightInLbs(salesOrderClinicalEntryParameterDTO.getPatientWeightInLbs());
        salesOrderClinicalDetailsDTO.setPatientWeightInKg(Double.parseDouble(decfor.format(salesOrderClinicalEntryParameterDTO.getPatientWeightInLbs()*0.45359237)));
        salesOrderClinicalDetailsDTO.setHeightInInches(salesOrderClinicalEntryParameterDTO.getHeightInInches());
        salesOrderClinicalDetailsDTO.setHeightInCm(Double.parseDouble(decfor.format(salesOrderClinicalEntryParameterDTO.getHeightInInches()*2.54)));
        salesOrderClinicalDetailsDTO.setSalesRepId(salesOrderClinicalEntryParameterDTO.getSalesRepId());
        salesOrderClinicalDetailsDTO.setSalesRepName(salesOrderClinicalEntryParameterDTO.getSalesRepName());
        salesOrderClinicalDetailsDTO.setDiagnosisCodeType(salesOrderClinicalEntryParameterDTO.getDiagnosisCodeType());

        salesOrderClinicalDetailsDTO.setRenderingProviderFacilityId(salesOrderClinicalEntryParameterDTO.getRenderingProviderFacilityId());
        salesOrderClinicalDetailsDTO.setRenderingProviderFacilityName(salesOrderClinicalEntryParameterDTO.getRenderingProviderFacilityName());
        salesOrderClinicalDetailsDTO.setReferringProviderFacilityId(salesOrderClinicalEntryParameterDTO.getReferringProviderFacilityId());
        salesOrderClinicalDetailsDTO.setReferringProviderFacilityName(salesOrderClinicalEntryParameterDTO.getReferringProviderFacilityName());
        salesOrderClinicalDetailsDTO.setOrderingProviderFacilityId(salesOrderClinicalEntryParameterDTO.getOrderingProviderFacilityId());
        salesOrderClinicalDetailsDTO.setOrderingProviderFacilityName(salesOrderClinicalEntryParameterDTO.getOrderingProviderFacilityName());

        salesOrderClinicalDetailsDTO.setMarketingReferralId(salesOrderClinicalEntryParameterDTO.getMarketingReferralId());
        salesOrderClinicalDetailsDTO.setMarketingReferralName(salesOrderClinicalEntryParameterDTO.getMarketingReferralName());
        salesOrderClinicalDetailsDTO.setMarketingReferralTypeId(salesOrderClinicalEntryParameterDTO.getMarketingReferralTypeId());
        salesOrderClinicalDetailsDTO.setMarketingReferralTypeDescription(salesOrderClinicalEntryParameterDTO.getMarketingReferralTypeDescription());
        salesOrderClinicalDetailsDTO.setEpsdtCertificationConditionIndicator(salesOrderClinicalEntryParameterDTO.getEpsdtCertificationConditionIndicator());
        salesOrderClinicalDetailsDTO.setEpsdtCertificationCode(salesOrderClinicalEntryParameterDTO.getEpsdtCertificationCode());
        salesOrderClinicalDetailsDTO.setRelationship(salesOrderClinicalEntryParameterDTO.getRelationship());
        salesOrderClinicalDetailsDTO.setModeOfContact(salesOrderClinicalEntryParameterDTO.getModeOfContact());


        String providerFacilityNPIsComaSeparated = "";
        List<String> npiCodeList = new ArrayList<>();
        if(salesOrderClinicalEntryParameterDTO.getOrderingProviderFacilityNPI() > 0){
            providerFacilityNPIsComaSeparated += salesOrderClinicalEntryParameterDTO.getOrderingProviderFacilityNPI().toString();
            npiCodeList.add(salesOrderClinicalEntryParameterDTO.getOrderingProviderFacilityNPI().toString());
        }
        if(salesOrderClinicalEntryParameterDTO.getRenderingProviderFacilityNPI() > 0){
            providerFacilityNPIsComaSeparated += ","+salesOrderClinicalEntryParameterDTO.getRenderingProviderFacilityNPI().toString();
            npiCodeList.add(salesOrderClinicalEntryParameterDTO.getRenderingProviderFacilityNPI().toString());
        }
        if(salesOrderClinicalEntryParameterDTO.getReferringProviderFacilityNPI() > 0){
            providerFacilityNPIsComaSeparated += ","+salesOrderClinicalEntryParameterDTO.getReferringProviderFacilityNPI().toString();
            npiCodeList.add(salesOrderClinicalEntryParameterDTO.getReferringProviderFacilityNPI().toString());
        }

        String orderingProviderFacilityNPI = salesOrderClinicalEntryParameterDTO.getOrderingProviderFacilityNPI().toString();
        String renderingProviderFacilityNPI = salesOrderClinicalEntryParameterDTO.getRenderingProviderFacilityNPI().toString();
        String referringProviderFacilityNPI = salesOrderClinicalEntryParameterDTO.getReferringProviderFacilityNPI().toString();

        if(providerFacilityNPIsComaSeparated!="") {

            ResponseDTO responseDTO_NPI = getDoctorMasterDataFromPatient(salesOrderClinicalEntryParameterDTO.getPatientId(), providerFacilityNPIsComaSeparated);
            log.info("responseDTO_NPI " + responseDTO_NPI.getData());
            //System.out.println("npiCodeList "+ npiCodeList);
            List doctorDataList = (List) responseDTO_NPI.getData();
            if (responseDTO_NPI.getStatus()) {
                for (Object doctorObj : doctorDataList) {
                    LinkedHashMap doctorMap = (LinkedHashMap) doctorObj;
                    System.out.println("doctorMap " + doctorMap);
                    System.out.println("doctorNpiNumber " + doctorMap.get("doctorNpiNumber"));

                    if (salesOrderClinicalEntryParameterDTO.getOrderingProviderFacilityNPI() > 0 &&
                        orderingProviderFacilityNPI.equals(doctorMap.get("doctorNpiNumber"))) {

                        salesOrderClinicalDetailsDTO.setOrderingProviderNpi(orderingProviderFacilityNPI);
                        salesOrderClinicalDetailsDTO.setOrderingProviderDea(salesOrderClinicalEntryParameterDTO.getOrderingProviderDea());
                        salesOrderClinicalDetailsDTO.setOrderingProviderType((String) doctorMap.get("doctorTaxonomyDescription"));
                        salesOrderClinicalDetailsDTO.setOrderingProviderId(doctorMap.get("doctorId")!=null?Long.parseLong(((Integer) doctorMap.get("doctorId")).toString()):0l);
                        salesOrderClinicalDetailsDTO.setOrderingProviderFirstName((String) doctorMap.get("doctorFirstName"));
                        salesOrderClinicalDetailsDTO.setOrderingProviderMiddleName((String) doctorMap.get("doctorMiddleName"));
                        salesOrderClinicalDetailsDTO.setOrderingProviderLastName((String) doctorMap.get("doctorLastName"));
                        salesOrderClinicalDetailsDTO.setOrderingProviderAddressLine1((String) doctorMap.get("doctorAddressLineI"));
                        salesOrderClinicalDetailsDTO.setOrderingProviderAddressLine2((String) doctorMap.get("doctorAddressLineIi"));
                        salesOrderClinicalDetailsDTO.setOrderingProviderEmail((String) doctorMap.get("doctorEmail"));
                        salesOrderClinicalDetailsDTO.setOrderingProviderZip((String) doctorMap.get("doctorAddressZip"));
                        salesOrderClinicalDetailsDTO.setOrderingProviderFax((String) doctorMap.get("doctorFax"));
                        salesOrderClinicalDetailsDTO.setOrderingProviderCity((String) doctorMap.get("doctorAddressCity"));
                        salesOrderClinicalDetailsDTO.setOrderingProviderState((String) doctorMap.get("doctorAddressState"));
                        salesOrderClinicalDetailsDTO.setOrderingProviderCountry((String) doctorMap.get("doctorAddressCountry"));
                        salesOrderClinicalDetailsDTO.setOrderingProviderContactNo1((String) doctorMap.get("doctorContactI"));
                        salesOrderClinicalDetailsDTO.setOrderingProviderContactNo2((String) doctorMap.get("doctorContactIi"));
                        salesOrderClinicalDetailsDTO.setOrderingProviderEfax((String) doctorMap.get("doctorEfax"));
                        npiCodeList.remove(doctorMap.get("doctorNpiNumber"));
                    }
                    if (salesOrderClinicalEntryParameterDTO.getRenderingProviderFacilityNPI() > 0 &&
                        renderingProviderFacilityNPI.equals(doctorMap.get("doctorNpiNumber"))) {

                        salesOrderClinicalDetailsDTO.setRenderingProviderDea(salesOrderClinicalEntryParameterDTO.getRenderingProviderDea());
                        salesOrderClinicalDetailsDTO.setRenderingProviderNpi(salesOrderClinicalEntryParameterDTO.getRenderingProviderFacilityNPI().toString());
                        //salesOrderClinicalDetailsDTO.setRenderingProviderFacilityId();
                        //salesOrderClinicalDetailsDTO.setRenderingProviderFacilityName();
                        salesOrderClinicalDetailsDTO.setRenderingProviderId(Long.parseLong(((Integer) doctorMap.get("doctorId")).toString()));
                        salesOrderClinicalDetailsDTO.setRenderingProviderType((String) doctorMap.get("doctorTaxonomyDescription"));
                        salesOrderClinicalDetailsDTO.setRenderingProviderFirstName((String) doctorMap.get("doctorFirstName"));
                        salesOrderClinicalDetailsDTO.setRenderingProviderMiddleName((String) doctorMap.get("doctorMiddleName"));
                        salesOrderClinicalDetailsDTO.setRenderingProviderLastName((String) doctorMap.get("doctorLastName"));
                        salesOrderClinicalDetailsDTO.setRenderingProviderAddressLine1((String) doctorMap.get("doctorAddressLineI"));
                        salesOrderClinicalDetailsDTO.setRenderingProviderAddressLine2((String) doctorMap.get("doctorAddressLineIi"));
                        salesOrderClinicalDetailsDTO.setRenderingProviderEmail((String) doctorMap.get("doctorEmail"));
                        salesOrderClinicalDetailsDTO.setRenderingProviderFax((String) doctorMap.get("doctorFax"));
                        salesOrderClinicalDetailsDTO.setRenderingProviderZip((String) doctorMap.get("doctorAddressZip"));
                        salesOrderClinicalDetailsDTO.setRenderingProviderCity((String) doctorMap.get("doctorAddressCity"));
                        salesOrderClinicalDetailsDTO.setRenderingProviderState((String) doctorMap.get("doctorAddressState"));
                        salesOrderClinicalDetailsDTO.setRenderingProviderCountry((String) doctorMap.get("doctorAddressCountry"));
                        salesOrderClinicalDetailsDTO.setRenderingProviderContactNo1((String) doctorMap.get("doctorContactI"));
                        salesOrderClinicalDetailsDTO.setRenderingProviderContactNo2((String) doctorMap.get("doctorContactIi"));
                        salesOrderClinicalDetailsDTO.setRenderingProviderEfax((String) doctorMap.get("doctorEfax"));
                        npiCodeList.remove(doctorMap.get("doctorNpiNumber"));
                    }
                    if (salesOrderClinicalEntryParameterDTO.getReferringProviderFacilityNPI() > 0 &&
                        referringProviderFacilityNPI.equals(doctorMap.get("doctorNpiNumber"))) {

                        salesOrderClinicalDetailsDTO.setReferringProviderDea(salesOrderClinicalEntryParameterDTO.getReferringProviderDea());
                        salesOrderClinicalDetailsDTO.setReferringProviderNpi(salesOrderClinicalEntryParameterDTO.getReferringProviderFacilityNPI().toString());
                        salesOrderClinicalDetailsDTO.setReferringProviderId(Long.parseLong(((Integer) doctorMap.get("doctorId")).toString()));
                        salesOrderClinicalDetailsDTO.setReferringProviderType((String) doctorMap.get("doctorTaxonomyDescription"));
                        salesOrderClinicalDetailsDTO.setReferringProviderFirstName((String) doctorMap.get("doctorFirstName"));
                        salesOrderClinicalDetailsDTO.setReferringProviderMiddleName((String) doctorMap.get("doctorMiddleName"));
                        salesOrderClinicalDetailsDTO.setReferringProviderLastName((String) doctorMap.get("doctorLastName"));
                        salesOrderClinicalDetailsDTO.setReferringProviderAddressLine1((String) doctorMap.get("doctorAddressLineI"));
                        salesOrderClinicalDetailsDTO.setReferringProviderAddressLine2((String) doctorMap.get("doctorAddressLineIi"));
                        salesOrderClinicalDetailsDTO.setReferringProviderEmail((String) doctorMap.get("doctorEmail"));
                        salesOrderClinicalDetailsDTO.setReferringProviderFax((String) doctorMap.get("doctorFax"));
                        salesOrderClinicalDetailsDTO.setReferringProviderZip((String) doctorMap.get("doctorAddressZip"));
                        salesOrderClinicalDetailsDTO.setReferringProviderCity((String) doctorMap.get("doctorAddressCity"));
                        salesOrderClinicalDetailsDTO.setReferringProviderState((String) doctorMap.get("doctorAddressState"));
                        salesOrderClinicalDetailsDTO.setReferringProviderCountry((String) doctorMap.get("doctorAddressCountry"));
                        salesOrderClinicalDetailsDTO.setReferringProviderContactNo1((String) doctorMap.get("doctorContactI"));
                        salesOrderClinicalDetailsDTO.setReferringProviderContactNo2((String) doctorMap.get("doctorContactIi"));
                        salesOrderClinicalDetailsDTO.setReferringProviderEfax((String) doctorMap.get("doctorEfax"));
                        npiCodeList.remove(doctorMap.get("doctorNpiNumber"));
                    }
                }
            }
            //System.out.println("npiCodeList 2"+ npiCodeList);
            if (npiCodeList.size() > 0) {
                String message = "";
                System.out.println("wrong Npi " + npiCodeList.toString());
                message = "(" + npiCodeList.toString() + ") NPI/s are invalid.";
                serviceOutcome.setOutcome(false);
                serviceOutcome.setMessage(message);
                serviceOutcome.setData(null);
                return Mono.just(serviceOutcome);
            }
        }
        else{
            log.info("NPI is not given.");
        }
        String icdCodes = getICDCodes(salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode1()+","+
            StringUtils.defaultString(salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode2())+","+
            StringUtils.defaultString(salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode3())+","+
            StringUtils.defaultString(salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode4())+","+
            StringUtils.defaultString(salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode5())+","+
            StringUtils.defaultString(salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode6())+","+
            StringUtils.defaultString(salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode7())+","+
            StringUtils.defaultString(salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode8())+","+
            StringUtils.defaultString(salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode9())+","+
            StringUtils.defaultString(salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode10())+","+
            StringUtils.defaultString(salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode11())+","+
            StringUtils.defaultString(salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode12())+","+
            StringUtils.defaultString(salesOrderClinicalEntryParameterDTO.getPrimaryDiagnosis()));

        if(icdCodes!=""){
            String icdCodeType = salesOrderClinicalEntryParameterDTO.getDiagnosisCodeType();
            System.out.println("icdCodes "+icdCodes);
            ResponseDTO responseDTO_ICD = getICDCodeMasterDataFromUtility(icdCodes,icdCodeType);
            log.info("responseDTO_ICD "+ responseDTO_ICD.getData());
            List arrayListICDs = (List) responseDTO_ICD.getData();
            List<String> icdCodeList = Arrays.stream(icdCodes.split(","))
                .collect(Collectors.toList());//"A01.01,A01.02,A01,A02"
    //        System.out.println("calling Icd Length" +icdCodes.split(",").length);
    //        System.out.println("Result Icd Length" +arrayListICDs.size());

            for(Object icdData : arrayListICDs) {
                //System.out.println("Inside");
                LinkedHashMap icdObj = (LinkedHashMap) icdData;
                if (icdCodes.split(",").length == arrayListICDs.size()) {
                    if(salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode1()!=null &&
                        salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode1().trim()!="" &&
                        salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode1().trim().equals(((String) icdObj.get("icdCode")))){
                        salesOrderClinicalDetailsDTO.setIcd10DiagnosisCode1((String) icdObj.get("icdCode"));
                    }
                    else if(salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode2()!=null &&
                        salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode2().trim()!="" &&
                        salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode2().trim().equals(((String) icdObj.get("icdCode")))){
                        salesOrderClinicalDetailsDTO.setIcd10DiagnosisCode2((String) icdObj.get("icdCode"));
                    }
                    else if(salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode3()!=null &&
                        salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode3().trim()!="" &&
                        salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode3().trim().equals(((String) icdObj.get("icdCode")))){
                        salesOrderClinicalDetailsDTO.setIcd10DiagnosisCode3((String) icdObj.get("icdCode"));
                    }
                    else if(salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode4()!=null &&
                        salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode4().trim()!="" &&
                        salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode4().trim().equals(((String) icdObj.get("icdCode")))){
                        salesOrderClinicalDetailsDTO.setIcd10DiagnosisCode4((String) icdObj.get("icdCode"));
                    }
                    else if(salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode5()!=null &&
                        salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode5().trim()!=""&&
                        salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode5().trim().equals(((String) icdObj.get("icdCode")))){
                        salesOrderClinicalDetailsDTO.setIcd10DiagnosisCode5((String) icdObj.get("icdCode"));
                    }
                    else if(salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode6()!=null &&
                        salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode6().trim()!=""&&
                        salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode6().trim().equals(((String) icdObj.get("icdCode")))){
                        salesOrderClinicalDetailsDTO.setIcd10DiagnosisCode6((String) icdObj.get("icdCode"));
                    }
                    else if(salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode7()!=null &&
                        salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode7().trim()!=""&&
                        salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode7().trim().equals(((String) icdObj.get("icdCode")))){
                        salesOrderClinicalDetailsDTO.setIcd10DiagnosisCode7((String) icdObj.get("icdCode"));
                    }
                    else if(salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode8()!=null &&
                        salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode8().trim()!=""&&
                        salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode8().trim().equals(((String) icdObj.get("icdCode")))){
                        salesOrderClinicalDetailsDTO.setIcd10DiagnosisCode8((String) icdObj.get("icdCode"));
                    }
                    else if(salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode9()!=null &&
                        salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode9().trim()!=""&&
                        salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode9().trim().equals(((String) icdObj.get("icdCode")))){
                        salesOrderClinicalDetailsDTO.setIcd10DiagnosisCode9((String) icdObj.get("icdCode"));
                    }
                    else if(salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode10()!=null &&
                        salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode10().trim()!=""&&
                        salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode10().trim().equals(((String) icdObj.get("icdCode")))){
                        salesOrderClinicalDetailsDTO.setIcd10DiagnosisCode10((String) icdObj.get("icdCode"));
                    }
                    else if(salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode11()!=null &&
                        salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode11().trim()!=""&&
                        salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode11().trim().equals(((String) icdObj.get("icdCode")))){
                        salesOrderClinicalDetailsDTO.setIcd10DiagnosisCode11((String) icdObj.get("icdCode"));
                    }
                    else if(salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode12()!=null &&
                        salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode12().trim()!=""&&
                        salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode12().trim().equals(((String) icdObj.get("icdCode")))){
                        salesOrderClinicalDetailsDTO.setIcd10DiagnosisCode12((String) icdObj.get("icdCode"));
                    }

                    if(salesOrderClinicalEntryParameterDTO.getPrimaryDiagnosis()!=null &&
                        salesOrderClinicalEntryParameterDTO.getPrimaryDiagnosis().trim()!=""&&
                        salesOrderClinicalEntryParameterDTO.getPrimaryDiagnosis().trim().equals(((String) icdObj.get("icdCode")))){
                        salesOrderClinicalDetailsDTO.setPrimaryDiagnosis((String) icdObj.get("icdCode"));
                    }

                    //System.out.println("Icd Master Id " + icdObj.get("icdCode"));
                    icdCodeList.remove((String) icdObj.get("icdCode"));
                }
                else{
                    System.out.println("icdCodeList For "+icdCodeList);
                    System.out.println("icdObj.get(icdCode) For "+icdObj.get("icdCode"));
                    if(icdCodeList.contains((String) icdObj.get("icdCode"))){
                        icdCodeList.remove((String) icdObj.get("icdCode"));
                    }
                    System.out.println("icdCodeList For "+icdCodeList);
                }
            }

            if(icdCodeList.size()>0){
                //return wrong ICD codes and data will not save.
                String message="";
                //System.out.println("wrongICDCode "+ icdCodeList.toString());
                message = "("+icdCodeList.toString()+") ICD_Code/s are invalid.";
                serviceOutcome.setOutcome(false);
                serviceOutcome.setMessage(message);
                serviceOutcome.setData(null);
                return Mono.just(serviceOutcome);
            }
        }
        //save the soclinical object
        if(salesOrderClinicalEntryParameterDTO.getSalesOrderClinicalDetailsUuid() == null) {
            salesOrderClinicalDetailsDTO.setStatus("Active");
            salesOrderClinicalDetailsDTO.setCreatedById(1l);
            salesOrderClinicalDetailsDTO.setCreatedByName("Abhay Testing");
            salesOrderClinicalDetailsDTO.setCreatedDate(LocalDate.now());
        }else if(soClinicalId>0){
            try {
                System.out.println("soClinicalId "+ soClinicalId);
                SalesOrderClinicalDetails salesOrderClinicalDetails = salesOrderClinicalDetailsRepositoryExtended.findById(soClinicalId).toFuture().get();
                //BeanUtils.copyProperties(salesOrderClinicalDetails,salesOrderClinicalDetailsDTO);
                salesOrderClinicalDetailsDTO.setCreatedDate(salesOrderClinicalDetails.getCreatedDate());
                salesOrderClinicalDetailsDTO.setStatus(salesOrderClinicalDetails.getStatus());
                salesOrderClinicalDetailsDTO.setCreatedById(salesOrderClinicalDetails.getCreatedById());
                salesOrderClinicalDetailsDTO.setCreatedByName(salesOrderClinicalDetails.getCreatedByName());
                salesOrderClinicalDetailsDTO.setSalesOrderClinicalDetailsUuid(salesOrderClinicalDetails.getSalesOrderClinicalDetailsUuid());
                salesOrderClinicalDetailsDTO.setSalesOrderClinicalDetailsId(soClinicalId);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
            salesOrderClinicalDetailsDTO.setUpdatedById(1l);
            salesOrderClinicalDetailsDTO.setUpdatedByName("Active");
            salesOrderClinicalDetailsDTO.setUpdatedDate(LocalDate.now());
        }else{
            serviceOutcome.setOutcome(false);
            serviceOutcome.setMessage("SOClinicalDetailsUUID should be null or give correct UUID");
            return Mono.just(serviceOutcome);
        }
        System.out.println("salesOrderClinicalDetailsDTO "+ salesOrderClinicalDetailsDTO);
        Mono<SalesOrderClinicalDetails> salesOrderClinicalDetails = salesOrderClinicalDetailsRepositoryExtended.save(salesOrderClinicalDetailsMapper.toEntity(salesOrderClinicalDetailsDTO));
        serviceOutcome.setOutcome(true);
        serviceOutcome.setMessage("Data Saved Successfully");
        try {
            serviceOutcome.setData(salesOrderClinicalDetails.toFuture().get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        return Mono.just(serviceOutcome);
    }

    private String getICDCodes(String icdCodes) {
        String[] icdArr = icdCodes.split(",");
        String icdCodesResult = "";
        for(String icd : icdArr){
            if(icd!=null && icd.trim()!="") {
                if(icdCodesResult.equals("")){
                    icdCodesResult = icd;
                }else {
                    icdCodesResult += "," + icd;
                }
            }
        }
        return icdCodesResult;
    }

    public ResponseDTO getICDCodeMasterDataFromUtility(String requestICDInputs, String icdType) {
        ObjectMapper mapper = new ObjectMapper();
        ServiceOutcome serviceOutcome = new ServiceOutcome();
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

                String url = propData.getProperty("utility_ICD_url");
                //String param = "/{icdCodes}/{npiId}";
                String paramValue = "?requestICDInputs=" + requestICDInputs + "&icdType=" + icdType;
                String completeUrl = url + paramValue ;
                System.out.println("completeUrl " + completeUrl);

                RestTemplate restTemplateData = new RestTemplate();
                HttpHeaders headersData = new HttpHeaders();
                headersData.add("Authorization", "Bearer " + token);
                HttpEntity entityData = new HttpEntity<>(headersData);
                ResponseEntity responseData = restTemplateData.exchange(
                    completeUrl,
                    HttpMethod.GET,
                    entityData,
                    Object.class,
                    requestICDInputs,
                    icdType
                );
                if (responseData.getStatusCode() == HttpStatus.OK) {
                    serviceOutcome = mapper.convertValue(responseData.getBody(), ServiceOutcome.class);
                    System.out.println("responseBody " +serviceOutcome);
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
        if (serviceOutcome.getOutcome()) {
            System.out.println("ICD Data "+ serviceOutcome.getData());
            responseBody.setData((ArrayList) serviceOutcome.getData());
            responseBody.setStatus(true);
            return responseBody;
        } else {
            responseBody.setStatus(false);
            responseBody.setMessage("Data not fetched.");
            responseBody.setData(null);
            return responseBody;
        }
    }

    @Override
    public Map<String, Object> validateSOClinicalParameterDTO(SalesOrderClinicalEntryParameterDTO salesOrderClinicalEntryParameterDTO) {
        Map<String, Object> stringTMap = new HashMap<>();
        stringTMap.put("status",true);
        String icdCodes = getICDCodes(salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode1()+","+
            StringUtils.defaultString(salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode2())+","+
            StringUtils.defaultString(salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode3())+","+
            StringUtils.defaultString(salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode4())+","+
            StringUtils.defaultString(salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode5())+","+
            StringUtils.defaultString(salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode6())+","+
            StringUtils.defaultString(salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode7())+","+
            StringUtils.defaultString(salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode8())+","+
            StringUtils.defaultString(salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode9())+","+
            StringUtils.defaultString(salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode10())+","+
            StringUtils.defaultString(salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode11())+","+
            StringUtils.defaultString(salesOrderClinicalEntryParameterDTO.getIcd10DiagnosisCode12()));
        List<String> icdCodeList = Arrays.stream(icdCodes.split(","))
            .collect(Collectors.toList());
        if(!icdCodeList.contains(salesOrderClinicalEntryParameterDTO.getPrimaryDiagnosis())){
            //"PrimaryDiagnosis must be one of ICD Codes"
            stringTMap.put("status",false);
            stringTMap.put("message","PrimaryDiagnosis must be one of ICD Codes");
            return stringTMap;
        }
        if(salesOrderClinicalEntryParameterDTO.getOrderingProviderFacilityNPI()==0 &&
            (salesOrderClinicalEntryParameterDTO.getRenderingProviderFacilityNPI()>0 || salesOrderClinicalEntryParameterDTO.getReferringProviderFacilityNPI() >0)){
            // OrderingProviderFacilityNPI should be first field for fill up
            stringTMap.put("status",false);
            stringTMap.put("message","OrderingProviderFacilityNPI should be first priority for fill up");
            return stringTMap;
        }

        return stringTMap;
    }

    @Override
    public ResponseDTO getDoctorMasterDataFromPatient(Long patientId, String npiIds) {
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

                String url = propData.getProperty("patient_doctor_url");
                String param = "/{patientId}/{npiIds}";
                //String paramValue = "?documentName=" + documentName + "&patientUUID=" + patientUUID + "&patientDocumentStatus=" + patientDocumentStatus + "&description=" + description + "&documentType=" + documentType;
                String completeUrl = url + param ;
                System.out.println("completeUrl " + completeUrl);

                RestTemplate restTemplateData = new RestTemplate();
                HttpHeaders headersData = new HttpHeaders();
                headersData.add("Authorization", "Bearer " + token);
                HttpEntity entityData = new HttpEntity<>(headersData);
                ResponseEntity responseData = restTemplateData.exchange(
                    completeUrl,
                    HttpMethod.GET,
                    entityData,
                    Object.class,
                    patientId,
                    npiIds
                );
                if (responseData.getStatusCode() == HttpStatus.OK) {
                    responseBody = mapper.convertValue(responseData.getBody(), ResponseDTO.class);
                    System.out.println("responseBody " +responseBody);
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
            System.out.println("Npi Data Received from Utility " +responseBody.getData());
            responseBody.setData(responseBody.getData());
            return responseBody;
        } else {
            responseBody.setStatus(false);
            responseBody.setMessage("Data not fetched.");
            responseBody.setData(null);
            return responseBody;
        }
    }

}
