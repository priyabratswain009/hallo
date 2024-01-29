package com.sunknowledge.dme.rcm.service.coverage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.application.utils.ApplicationConstants;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.BenefitCoverageRequest;
import com.sunknowledge.dme.rcm.domain.BenefitCoverageResponse;
import com.sunknowledge.dme.rcm.domain.coverage.CoverageInput;
import com.sunknowledge.dme.rcm.domain.coverage.CoverageOutput;
import com.sunknowledge.dme.rcm.domain.elligibility.TokenOutCome;
import com.sunknowledge.dme.rcm.domain.newcoverage.BenefitCoverage;
import com.sunknowledge.dme.rcm.repository.Availity.BenefitCoverageRequestRepo;
import com.sunknowledge.dme.rcm.repository.Availity.BenefitCoverageResponseRepo;
import com.sunknowledge.dme.rcm.service.claimssubmissiondata.TokenGenerationService;
import com.sunknowledge.dme.rcm.service.dto.BenefitCoverageRequestDTO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

@Primary
@Service
public class BenefitCoverageRequestServiceExtendedImpl implements BenefitCoverageRequestServiceExtended{


    @Autowired
    private TokenGenerationService tokenGenerationService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    BenefitCoverageRequestRepo benefitCoverageRequestRepository;
    @Autowired
    BenefitCoverageResponseRepo benefitCoverageResponseRepository;

    @Override
    public Mono<BenefitCoverageRequestDTO> save(BenefitCoverageRequestDTO benefitCoverageRequestDTO) {
        return null;
    }

    @Override
    public Mono<BenefitCoverageRequestDTO> update(BenefitCoverageRequestDTO benefitCoverageRequestDTO) {
        return null;
    }

    @Override
    public Mono<BenefitCoverageRequestDTO> partialUpdate(BenefitCoverageRequestDTO benefitCoverageRequestDTO) {
        return null;
    }

    @Override
    public Flux<BenefitCoverageRequestDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Mono<Long> countAll() {
        return null;
    }

    @Override
    public Mono<BenefitCoverageRequestDTO> findOne(Long id) {
        return null;
    }

    @Override
    public Mono<Void> delete(Long id) {
        return null;
    }

    @Override
    public Mono<ServiceOutcome<BenefitCoverageResponse>> getSOBenefitCoverage(CoverageInput coverageInput) {
        CoverageOutput coverageOutput = null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String resultOutcomeJson = "";

        try {
            String token = tokenGenerationService.getCoverageToken();
            //String token = tokenOutCome.getTokenResponseOutput().getAccessToken();
            String url =  ApplicationConstants.VERIFY_BENEFITCOVERAGE_NEW_URL;
            String inputData = setData(coverageInput);
            url = url + "?" + inputData;

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            headers.add("Accept", "application/json");
            headers.set("Authorization", "Bearer " + token);
            System.out.println("url "+url);
            HttpEntity<String> inputCriteriaEntity = new HttpEntity<String>(null, headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, inputCriteriaEntity,
                String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                ObjectMapper mapper1 = new ObjectMapper();
                LocalDate reqdate = LocalDate.now();
                coverageOutput = mapper1.readValue(response.getBody(), CoverageOutput.class);
                resultOutcomeJson = ow.writeValueAsString(response.getBody());
                System.out.println("resultOutcomeJson "+ response);
                System.out.println("coverageOutput 1 "+coverageOutput);
                return getSOBenefitCoverageById(coverageOutput.getCoverages().get(0).getId(),coverageInput);
            }else{
                return Mono.just(new ServiceOutcome<>(null,false,""));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //return coverageOutput.getCoverages().get(0).getLinks().getSelf().getHref();
        return Mono.just(new ServiceOutcome<>(null,false,""));
    }

    /*private Mono<String> getSOBenefitCoverageResponse(String id, CoverageInput coverageInput) {
        CoverageOutput coverageOutput = null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String resultOutcomeJson = "";

        try {
            String token = tokenGenerationService.getCoverageToken();
            //String token = tokenOutCome.getTokenResponseOutput().getAccessToken();
            String url = ApplicationConstants.AVAILITY_SECONDARY_RESPONSE_URL;
            System.out.println("id "+id);
            url = url + "/" + id;

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            headers.add("Accept", "application/json");
            headers.set("Authorization", "Bearer " + token);
            System.out.println("url " + url);
            HttpEntity<String> inputCriteriaEntity = new HttpEntity<String>(null, headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, inputCriteriaEntity,
                String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                ObjectMapper mapper1 = new ObjectMapper();
                LocalDate reqdate = LocalDate.now();
                JSONParser jsonParser = new JSONParser();
                JSONObject coverages = (JSONObject) jsonParser.parse(response.getBody());

//                coverageOutput = mapper1.readValue(response.getBody(), JSON.class);
//                resultOutcomeJson = ow.writeValueAsString(response.getBody());
                System.out.println("jsonObject " + coverages);
                System.out.println("jsonObject class" + coverages.getClass());

                LocalDate respdate = LocalDate.now();
                BenefitCoverageRequest benefitCoverageRequest = setDataInRequest(coverageInput);
                BenefitCoverageResponse benefitCoverageResponse = setDataInResponse(coverageOutput, coverages);
                benefitCoverageRequestRepository.save(benefitCoverageRequest);
                benefitCoverageResponseRepository.save(benefitCoverageResponse);
                return Mono.just(resultOutcomeJson);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/

    String setData(CoverageInput coverageInput) {

        String data = "payerId=" + coverageInput.getPayerId() + "&providerLastName="
            + coverageInput.getProviderLastName() + "&providerType=" + coverageInput.getProviderType()
            + "&providerNpi=" + coverageInput.getProviderNpi() + "&providerCity=" + coverageInput.getProviderCity()
            + "&providerState=" + coverageInput.getPatientState() + "&providerZipCode="
            + coverageInput.getProviderZipCode() + "&asOfDate=" + coverageInput.getAsOfDate() + "&serviceType="
            + coverageInput.getServiceType() + "&memberId=" + coverageInput.getMemberId() + "&patientLastName="
            + coverageInput.getPatientLastName() + "&patientFirstName=" + coverageInput.getPatientFirstName()
            + "&patientBirthDate=" + coverageInput.getPatientBirthDate() + "&patientGender="
            + coverageInput.getPatientGender() + "&patientState=" + coverageInput.getPatientState()
            + "&subscriberRelationship=" + coverageInput.getSubscriberRelationship();

        return data;
    }

    public Mono<ServiceOutcome<BenefitCoverageResponse>> getSOBenefitCoverageById(String id,CoverageInput coverageInput) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String resultOutcomeJson = "";
        BenefitCoverage benefitCoverage = new BenefitCoverage();

        try {
            String token = tokenGenerationService.getCoverageToken();
            //String token = tokenOutCome.getTokenResponseOutput().getAccessToken();

            String url = ApplicationConstants.VERIFY_BENEFITCOVERAGE_NEW_URL + "/" + id;

            System.out.println(url);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            headers.add("Accept", "application/json");
            headers.set("Authorization", "Bearer " + token);

            HttpEntity<String> inputCriteriaEntity = new HttpEntity<String>(null, headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, inputCriteriaEntity,
                String.class);
            benefitCoverage = mapper.readValue(response.getBody(), BenefitCoverage.class);

            resultOutcomeJson = ow.writeValueAsString(response.getBody());
            System.out.println("resultOutcomeJson response "+ response);
//            JSONParser jsonParser = new JSONParser();
//            JSONObject coverages = (JSONObject) jsonParser.parse(response.getBody());
            BenefitCoverageRequest benefitCoverageRequest = setDataInRequest(coverageInput);
            System.out.println("benefitCoverage "+ benefitCoverage);
            BenefitCoverageResponse benefitCoverageResponse = setDataInResponse(benefitCoverage,response);
            return benefitCoverageRequestRepository.save(benefitCoverageRequest)
                .map(data->{
                    return benefitCoverageResponseRepository.save(benefitCoverageResponse)
                        .map(x->new ServiceOutcome<BenefitCoverageResponse>(x,true,""));
                }).flatMap(x->x);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return Mono.just(new ServiceOutcome<>(null,false,""));
    }

    BenefitCoverageRequest setDataInRequest(CoverageInput coverageInput) {
        BenefitCoverageRequest benefitCoverageRequest = new BenefitCoverageRequest();

        benefitCoverageRequest.setPayerId(coverageInput.getPayerId());
        benefitCoverageRequest.setProviderLastName(coverageInput.getProviderLastName());
        benefitCoverageRequest.setProviderFirstName(coverageInput.getProviderFirstName());
        benefitCoverageRequest.setProviderType(coverageInput.getProviderType());
        benefitCoverageRequest.setProviderNpi(coverageInput.getProviderNpi());
        benefitCoverageRequest.setProviderCity(coverageInput.getProviderCity());
        benefitCoverageRequest.setPatientState(coverageInput.getPatientState());
        benefitCoverageRequest.setProviderZipcode(coverageInput.getProviderZipCode());
        System.out.println(coverageInput.getAsOfDate());
        benefitCoverageRequest
            .setAsOfDate(CommonUtilities.stringwithhyphentodateConverter(coverageInput.getAsOfDate()));
        benefitCoverageRequest.setServiceType(coverageInput.getServiceType());
        benefitCoverageRequest.setMemberId(coverageInput.getMemberId());
        benefitCoverageRequest.setPatientLastName(coverageInput.getPatientLastName());
        benefitCoverageRequest.setPatientFirstName(coverageInput.getPatientFirstName());
        //benefitCoverageRequest.setPatientDob(CommonUtilities.stringtodateConverter(coverageInput.getPatientBirthDate()));
        benefitCoverageRequest.setPatientGender(coverageInput.getPatientGender());
        benefitCoverageRequest.setPatientState(coverageInput.getPatientState());
        benefitCoverageRequest.setSubscriberRelationship(coverageInput.getSubscriberRelationship());
        benefitCoverageRequest.setCreatedById(Long.valueOf("6165"));
        benefitCoverageRequest.setCreatedByName("Abhay Test");
        //benefitCoverageRequest.setUpdatedById(Long.valueOf("5501"));
        //benefitCoverageRequest.setUpdatedByName("Arijit");
        LocalDate zone = LocalDate.now();
        //benefitCoverageRequest.setUpdatedDate(zone);
        benefitCoverageRequest.setCreatedDate(zone);

        return benefitCoverageRequest;
    }

    BenefitCoverageResponse setDataInResponse(BenefitCoverage benefitCoverage, ResponseEntity response) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
        BenefitCoverageResponse objBenefitCoverageResponse = new BenefitCoverageResponse();
        try {
            objBenefitCoverageResponse.setBenefitCoverageRequestId(Long.parseLong(benefitCoverageRequestRepository.getlastentry().toFuture().get()));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        objBenefitCoverageResponse.setRequestControlNumberExt(benefitCoverage.getControlNumber());
        //objBenefitCoverageResponse.setAsOnDate(LocalDate.parse(benefitCoverage.getAsOfDate(),inputFormatter));
        //ZonedDateTime zonedDateTime = ZonedDateTime.parse(benefitCoverage.getAsOfDate(), formatter);
        objBenefitCoverageResponse.setAsOnDate(LocalDate.now());
        objBenefitCoverageResponse.setServiceType(benefitCoverage.getRequestedServiceType().get(0).getCode());
        objBenefitCoverageResponse.setMemberFirstName(benefitCoverage.getSubscriber().getFirstName());
        objBenefitCoverageResponse.setMemberLastName(benefitCoverage.getSubscriber().getLastName());
        objBenefitCoverageResponse.setSubscriberMemberId(benefitCoverage.getSubscriber().getMemberId());
        objBenefitCoverageResponse.setMemberGender(benefitCoverage.getSubscriber().getGender());
        objBenefitCoverageResponse.setPatientFirstName(benefitCoverage.getPatient().getFirstName());
        objBenefitCoverageResponse.setPatientLastName(benefitCoverage.getPatient().getLastName());
        objBenefitCoverageResponse.setPatientGender(benefitCoverage.getPatient().getGender());
        objBenefitCoverageResponse.setPayerName(benefitCoverage.getPayer().getName());
        objBenefitCoverageResponse.setPatientRelationshipCode(benefitCoverage.getPatient().getSubscriberRelationshipCode());
        objBenefitCoverageResponse.setPatientState(benefitCoverage.getPatient().getAddress().getState());
        objBenefitCoverageResponse.setCoverageStatus(benefitCoverage.getPlans().get(0).getStatus());
        objBenefitCoverageResponse.setPayerGroupNumber(benefitCoverage.getPlans().get(0).getGroupNumber());
        //objBenefitCoverageResponse.setServiceDate(LocalDate.parse(benefitCoverage.getPlans().get(0).getServiceDate()));
        objBenefitCoverageResponse.setServiceDate(LocalDate.now());
        //objBenefitCoverageResponse.setServiceDate(LocalDate.parse(benefitCoverage.getPlans().get(0).getServiceDate()));
        objBenefitCoverageResponse.setServiceDate(LocalDate.now());
        //objBenefitCoverageResponse.setPlanStartDate(LocalDate.parse(benefitCoverage.getPlans().get(0).getPlanStartDate()));
        objBenefitCoverageResponse.setPlanStartDate(LocalDate.now());
        objBenefitCoverageResponse.setResponseJsonText(response.getBody().toString());

        System.out.println("objBenefitCoverageResponse "+ objBenefitCoverageResponse);
        objBenefitCoverageResponse.setCreatedById(6165l);
        objBenefitCoverageResponse.setCreatedByName("Abhay Test");
        objBenefitCoverageResponse.setCreatedDate(LocalDate.now());
        objBenefitCoverageResponse.setStatus("Active");
        return objBenefitCoverageResponse;
    }
}
