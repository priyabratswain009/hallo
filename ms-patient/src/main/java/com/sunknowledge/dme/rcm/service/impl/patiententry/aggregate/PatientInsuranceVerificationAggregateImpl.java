package com.sunknowledge.dme.rcm.service.impl.patiententry.aggregate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.patientelligibility.PatientElligibilityInput;
import com.sunknowledge.dme.rcm.domain.patientelligibility.PatientElligibilityOutput;
import com.sunknowledge.dme.rcm.domain.patientelligibility.Subscriber;
import com.sunknowledge.dme.rcm.repository.PatientInsuranceVerificationRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.PatientInsVerifStatDTO;
import com.sunknowledge.dme.rcm.service.dto.PatientInsuranceDTO;
import com.sunknowledge.dme.rcm.service.dto.PatientMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patientelligibility.ElligibilityHealthCheck;
import com.sunknowledge.dme.rcm.service.dto.patiententry.PatientInsuranceVerificationParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SavePatientInsuranceVerificationCommand;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientSearchByBasicInfoOrAddressOrBranch;
import com.sunknowledge.dme.rcm.service.mapper.PatientInsVerifStatMapper;
import com.sunknowledge.dme.rcm.service.patiententry.aggregate.PatientInsuranceVerificationAggregate;
import com.sunknowledge.dme.rcm.utils.application.ApplicationConstant;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

@Service
@Transactional
public class PatientInsuranceVerificationAggregateImpl implements PatientInsuranceVerificationAggregate {
    private final PatientInsVerifStatMapper patientInsVerifStatMapper;
    @Autowired
    PatientInsuranceVerificationRepositoryExtended patientInsuranceVerificationRepositoryExtended;
    @Autowired
    private RestTemplate restTemplate;

    public PatientInsuranceVerificationAggregateImpl(PatientInsVerifStatMapper patientInsVerifStatMapper) {
        this.patientInsVerifStatMapper = patientInsVerifStatMapper;
    }

    @Override
    public Mono<ResponseDTO> handleSavePatientInsuranceVerificationCommand(SavePatientInsuranceVerificationCommand savePatientInsuranceVerificationCommand) {
        PatientInsVerifStatDTO patientInsVerifStatDTO = new PatientInsVerifStatDTO();
        try {
            if (savePatientInsuranceVerificationCommand.getInsuranceVerifId() == null || savePatientInsuranceVerificationCommand.getInsuranceVerifId() == 0) {
                CommonUtilities.dtoTrimmer(savePatientInsuranceVerificationCommand);
                BeanUtils.copyProperties(savePatientInsuranceVerificationCommand, patientInsVerifStatDTO, "createdDate", "updatedDate");
                patientInsVerifStatDTO.setCreatedDate(LocalDate.now());
                patientInsVerifStatDTO.setCreatedById(1L);
                patientInsVerifStatDTO.setCreatedByName("Abhijit Chowdhury"); // --- From User Login Service
                patientInsVerifStatDTO.setStatus("Active");
                patientInsVerifStatDTO.setPatientInsVerifStatUuid(UUID.randomUUID());
                patientInsVerifStatDTO.setInsuranceVerifId(null);
                return patientInsuranceVerificationRepositoryExtended
                    .save(patientInsVerifStatMapper.toEntity(patientInsVerifStatDTO))
                    .map(patientInsVerifStatMapper::toDto).map(
                        i -> new ResponseDTO(true, "Successfully Saved", i));
            } else {
                return patientInsuranceVerificationRepositoryExtended
                    .findById(savePatientInsuranceVerificationCommand.getInsuranceVerifId())
                    .map(existingPatientInsVerifStat -> {
                        existingPatientInsVerifStat.setUpdatedDate(LocalDate.now());
                        existingPatientInsVerifStat.setUpdatedByName("Abhijit Chowdhury"); // --- From User Login Service
                        existingPatientInsVerifStat.setUpdatedById(1L);
                        existingPatientInsVerifStat.setStatus("inactive");
                        return existingPatientInsVerifStat;
                    })
                    .flatMap(patientInsuranceVerificationRepositoryExtended::save)
                    .map(savedPatientInsVerifStat -> {
                        CommonUtilities.dtoTrimmer(savePatientInsuranceVerificationCommand);
                        BeanUtils.copyProperties(savePatientInsuranceVerificationCommand, savedPatientInsVerifStat, "createdDate", "updatedDate");
                        savedPatientInsVerifStat.setCreatedDate(LocalDate.now());
                        savedPatientInsVerifStat.setCreatedById(1L);
                        savedPatientInsVerifStat.setCreatedByName("Abhijit Chowdhury"); // --- From User Login Service
                        savedPatientInsVerifStat.setStatus("Active");
                        savedPatientInsVerifStat.setPatientInsVerifStatUuid(UUID.randomUUID());
                        savedPatientInsVerifStat.setInsuranceVerifId(null);
                        savedPatientInsVerifStat.setUpdatedDate(null);

                        return savedPatientInsVerifStat;
                    }).flatMap(patientInsuranceVerificationRepositoryExtended::save)
                    .map(updatedPatientInsVerifStat -> new ResponseDTO(true, "Successfully Saved", updatedPatientInsVerifStat));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Mono.just(new ResponseDTO(false, "Data Error! Failed to Save.", null));
        }
    }

    @Override
    public Mono<ResponseDTO> handleSavePatientElligibility(
        SavePatientInsuranceVerificationCommand savePatientInsuranceVerificationCommand) {
        System.out.println("TESTING==========> " + savePatientInsuranceVerificationCommand);
        try {
            if (savePatientInsuranceVerificationCommand.getInsuranceVerifId() == null || savePatientInsuranceVerificationCommand.getInsuranceVerifId() == 0) {
                return Mono.just(new ResponseDTO(false, "Error in Response, Kindly check logs for Detail", null));
            } else {
                return patientInsuranceVerificationRepositoryExtended
                    .findById(savePatientInsuranceVerificationCommand.getInsuranceVerifId())
                    .map(existingPatientInsVerifStat -> {
                        existingPatientInsVerifStat.setUpdatedDate(LocalDate.now());
                        existingPatientInsVerifStat.setStatus("inactive");
                        return existingPatientInsVerifStat;
                    })
                    .flatMap(patientInsuranceVerificationRepositoryExtended::save)
                    .map(savedPatientInsVerifStat -> {
                        CommonUtilities.dtoTrimmer(savePatientInsuranceVerificationCommand);
                        BeanUtils.copyProperties(savePatientInsuranceVerificationCommand, savedPatientInsVerifStat, "createdDate", "updatedDate");
                        savedPatientInsVerifStat.setCreatedDate(LocalDate.now());
                        savedPatientInsVerifStat.setInsuranceVerifId(null);
                        savedPatientInsVerifStat.setUpdatedDate(null);

                        return savedPatientInsVerifStat;
                    }).flatMap(patientInsuranceVerificationRepositoryExtended::save)
                    .map(updatedPatientInsVerifStat -> new ResponseDTO(true, "Successfully Saved", updatedPatientInsVerifStat));
            }


        } catch (Exception e) {
            e.printStackTrace();
            return Mono.just(new ResponseDTO(false, "Data Error! Failed to Save.", null));
        }
    }

    /*************New Code****************/
    @Override
    public Mono<ResponseDTO> checkAndHandleSavePatientElligibility(Long patientId, PatientMasterDTO objpatient, PatientInsuranceDTO patientInsuranceDTO, String accessToken){
        System.out.println("============::Start checkAndHandleSavePatientElligibility::============");
        System.out.println("======================::patientId::======================"+patientId);
        System.out.println("======================::accessToken::======================"+accessToken);
        System.out.println("======================::objpatient::======================"+objpatient);
        System.out.println("======================::patientInsuranceDTO::======================"+patientInsuranceDTO);

        SavePatientInsuranceVerificationCommand savePatientInsuranceVerificationCommand = new SavePatientInsuranceVerificationCommand();
        PatientInsuranceVerificationParameterDTO patientInsuranceVerificationParameterDTO = new PatientInsuranceVerificationParameterDTO();

        String inputEntity = "";
        PatientSearchByBasicInfoOrAddressOrBranch obj = new PatientSearchByBasicInfoOrAddressOrBranch();
        PatientElligibilityOutput patientElligibilityOutput = null;
        String url = ApplicationConstant.ELLIGIBILITY_URL;
        try {

            PatientElligibilityInput objPatientElligibilityInput;

            if (objpatient.getPatientId() == null || patientInsuranceDTO.getPatientInsuranceId() == null) {
                System.out.println("No Data available in Patient");
            } else {
                if (checkElligibilityHealth(accessToken)) {
                    objPatientElligibilityInput = getInputData(objpatient, patientInsuranceDTO);

                    System.out.println("===========::objPatientElligibilityInput::=========="+objPatientElligibilityInput);

                    HttpHeaders headers = new HttpHeaders();
                    headers.add("Content-Type", "application/json");
                    headers.add("Accept", "application/json");
                    headers.set("Authorization", "Bearer " + accessToken);
                    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                    inputEntity = ow.writeValueAsString(objPatientElligibilityInput);

                    HttpEntity<String> inputCriteriaEntity = new HttpEntity<String>(inputEntity, headers);
                    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, inputCriteriaEntity,
                        String.class);

                    System.out.println("========response========"+response);

                    if (response.getStatusCode() == HttpStatus.OK) {
                        ObjectMapper mapper = new ObjectMapper();
                        patientElligibilityOutput = mapper.readValue(response.getBody(), PatientElligibilityOutput.class);

                        System.out.println("============::patientElligibilityOutput::==========="+patientElligibilityOutput);

                        if(patientElligibilityOutput.getErrors()==null) {
                            patientInsuranceVerificationParameterDTO = setDTOData(patientElligibilityOutput,
                                String.valueOf(patientInsuranceDTO.getPatientInsuranceId()),
                                ow.writeValueAsString(patientElligibilityOutput));

                            System.out.println("==============================::Successful::====================================");
                        }else {
                            patientInsuranceVerificationParameterDTO = new PatientInsuranceVerificationParameterDTO();
                            patientInsuranceVerificationParameterDTO.setElligibilityStatus("N");
                            System.out.println("===================Errors======================"+response.getBody());
                            return Mono.just(new ResponseDTO(false, "Data Error.", patientInsuranceVerificationParameterDTO));
                        }
                    } else {
                        System.out.println("===================Failed======================"+response.getBody());
                    }
                } else {
                    System.out.println("URL you're Trying to access is currently  Unavailable");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("==============::Final patientInsuranceVerificationParameterDTO::=============="+patientInsuranceVerificationParameterDTO);

        BeanUtils.copyProperties(patientInsuranceVerificationParameterDTO, savePatientInsuranceVerificationCommand);

        System.out.println("=======savePatientInsuranceVerificationCommand======="+savePatientInsuranceVerificationCommand);

        if(patientInsuranceVerificationParameterDTO.getPatientInsuranceUuid()!=null && patientElligibilityOutput.getMeta().getTraceId()!=null){
            savePatientInsuranceVerificationCommand.setPatientInsuranceId(patientInsuranceVerificationParameterDTO.getPatientInsuranceUuid());
            PatientInsVerifStatDTO patientInsVerifStatDTO = new PatientInsVerifStatDTO();
            patientInsVerifStatDTO.setPatientInsuranceId(savePatientInsuranceVerificationCommand.getPatientInsuranceId());
            if(savePatientInsuranceVerificationCommand.getElligibilityStatus().trim().equals("Active Coverage")){
                patientInsVerifStatDTO.setElligibilityStatus("Y");
            }else{
                patientInsVerifStatDTO.setElligibilityStatus("N");
            }
            patientInsVerifStatDTO.setElligibilityCheckType(savePatientInsuranceVerificationCommand.getElligibilityCheckType());
            patientInsVerifStatDTO.setPeriodYear(savePatientInsuranceVerificationCommand.getPeriodYear());
            patientInsVerifStatDTO.setCoverageInfo(savePatientInsuranceVerificationCommand.getCoverageInfo());
            patientInsVerifStatDTO.setNetworkInfo(savePatientInsuranceVerificationCommand.getNetworkInfo());
            patientInsVerifStatDTO.setDeductableAmt(savePatientInsuranceVerificationCommand.getDeductableAmt());
            patientInsVerifStatDTO.setRemainingAmt(savePatientInsuranceVerificationCommand.getRemainingAmt());
            patientInsVerifStatDTO.setCoinsuranceOrCopay(savePatientInsuranceVerificationCommand.getCoinsuranceOrCopay());
            patientInsVerifStatDTO.setCreatedDate(LocalDate.now());
            patientInsVerifStatDTO.setCreatedById(31L);
            patientInsVerifStatDTO.setCreatedByName("Falguni"); // --- From User Login Service
            patientInsVerifStatDTO.setStatus("active");
            patientInsVerifStatDTO.setPatientInsVerifStatUuid(UUID.randomUUID());
            patientInsVerifStatDTO.setInsuranceVerifId(null);
            return patientInsuranceVerificationRepositoryExtended
                .save(patientInsVerifStatMapper.toEntity(patientInsVerifStatDTO))
                .map(patientInsVerifStatMapper::toDto).map(
                    i -> new ResponseDTO(true, "Successfully Saved", i));
        }
        else{
            return Mono.just(new ResponseDTO(false, "Error Occurred.", null));
        }
    }

    public boolean checkElligibilityHealth(String token) {
        // TODO Auto-generated method stub
        ElligibilityHealthCheck healthCheckOutcome = new ElligibilityHealthCheck();
        boolean status = false;

        try {
            String URL = ApplicationConstant.ELLIGIBILITY_HEALTHCHECK_URL;
            HttpHeaders header = new HttpHeaders();
            token = "Bearer " + token;

            header.add("Content-Type", "application/json");
            header.add("Accept", "application/json");
            header.add("Authorization", token);

            HttpEntity<String> request = new HttpEntity<String>(null, header);
            ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, request, String.class);
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                ObjectMapper mapper = new ObjectMapper();
                healthCheckOutcome = mapper.readValue(responseEntity.getBody(), ElligibilityHealthCheck.class);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (healthCheckOutcome.getVersion().equalsIgnoreCase("v3")
            && healthCheckOutcome.getStatus().equalsIgnoreCase("ok")) {
            status = true;
        } else {
            status = false;
        }

        return status;
    }

    public static PatientElligibilityInput getInputData(PatientMasterDTO objpatient,
                                                        PatientInsuranceDTO objpatientInsurance) {

        PatientElligibilityInput objPatientElligibilityInput = new PatientElligibilityInput();
        Subscriber objSubscriber = new Subscriber();
        String fname = "", lname = "";

        if (CommonUtilities.isStringNullOrBlank(objpatientInsurance.getPolicyNum())) {
            objSubscriber.setMemberId(objpatientInsurance.getPolicyNum());
        }
        if (CommonUtilities.isStringNullOrBlank(
            CommonUtilities.mergeName(objpatient.getPatientFirstName(), objpatient.getPatientMiddleName(), objpatient.getPatientLastName()))) {
            String[] arr = CommonUtilities.mergeName(objpatient.getPatientFirstName(), objpatient.getPatientMiddleName(), objpatient.getPatientLastName()).split(" ");
            fname = arr[0];
            lname = arr[arr.length - 1];
        }
        if (CommonUtilities.isStringNullOrBlank(fname)) {
            objSubscriber.setFirstName(fname);
        }
        if (CommonUtilities.isStringNullOrBlank(lname)) {
            objSubscriber.setLastName(lname);
        }
        if (CommonUtilities.isStringNullOrBlank(objpatient.getGender())) {
            objSubscriber.setGender(objpatient.getGender());
        }
        if (CommonUtilities.isStringNullOrBlank(String.valueOf(objpatient.getDob()))) {
            objSubscriber.setDateOfBirth(CommonUtilities.datetoStringConverter(String.valueOf(objpatient.getDob())));
        }
        if (CommonUtilities.isStringNullOrBlank(String.valueOf(objpatientInsurance.getPolicyNum()))) {
            // No control number is available in patient
            objPatientElligibilityInput.setControlNumber("123456789");
        }
        if (CommonUtilities.isStringNullOrBlank(String.valueOf(objpatientInsurance.getInsurancePayerIdNo()))) {
            // for sandbox using hard coded value
            objPatientElligibilityInput.setTradingPartnerServiceId(objpatientInsurance.getInsurancePayerIdNo());
        }

        /****Test Data Set Start******/
        /*objSubscriber.setMemberId("0000000000");
        objSubscriber.setFirstName("johnOne");
        objSubscriber.setLastName("doeOne");
        objSubscriber.setGender("M");
        objSubscriber.setDateOfBirth("18800102");
        objPatientElligibilityInput.setControlNumber("123456789");*/
        /****Test Data Set End******/
        objPatientElligibilityInput.setSubscriber(objSubscriber);

        System.out.println("========Input Data========"+objPatientElligibilityInput);
        System.out.println("========Input Data:: Subscriber:: MemberId========"+objPatientElligibilityInput.getSubscriber().getMemberId());
        System.out.println("========Input Data:: Subscriber:: FirstName========"+objPatientElligibilityInput.getSubscriber().getFirstName());
        System.out.println("========Input Data:: Subscriber:: LastName========"+objPatientElligibilityInput.getSubscriber().getLastName());
        System.out.println("========Input Data:: Subscriber:: Gender========"+objPatientElligibilityInput.getSubscriber().getGender());
        System.out.println("========Input Data:: DateOfBirth========"+objPatientElligibilityInput.getSubscriber().getDateOfBirth());
        System.out.println("========Input Data:: ControlNumber========"+objPatientElligibilityInput.getControlNumber());
        System.out.println("========Input Data:: Trading Partner Service Id========"+objPatientElligibilityInput.getTradingPartnerServiceId());



        return objPatientElligibilityInput;
    }

    public static PatientInsuranceVerificationParameterDTO setDTOData(
        PatientElligibilityOutput patientElligibilityOutput, String insuranceId, String response) {

        PatientInsuranceVerificationParameterDTO patientInsuranceVerificationParameterDTO = new PatientInsuranceVerificationParameterDTO();

        if (CommonUtilities.isStringNullOrBlank(insuranceId)) {
            patientInsuranceVerificationParameterDTO.setPatientInsuranceUuid(Long.valueOf(insuranceId));
        }
        if (CommonUtilities.isStringNullOrBlank(patientElligibilityOutput.getPlanStatus().get(0).getStatus())) {
            patientInsuranceVerificationParameterDTO
                .setElligibilityStatus(patientElligibilityOutput.getPlanStatus().get(0).getStatus());
        }
        patientInsuranceVerificationParameterDTO.setElligibilityCheckType("Automatic");
        if (CommonUtilities.isStringNullOrBlank(patientElligibilityOutput.getPlanDateInformation().getPlan())) {
            patientInsuranceVerificationParameterDTO
                .setPeriodYear(patientElligibilityOutput.getPlanDateInformation().getPlan());
        }
        if (CommonUtilities.isStringNullOrBlank(response)) {
            patientInsuranceVerificationParameterDTO.setCoverageInfo(response);
        }
        if (CommonUtilities.isStringNullOrBlank(patientElligibilityOutput.getMeta().getTraceId())) {
            patientInsuranceVerificationParameterDTO.setNetworkInfo(patientElligibilityOutput.getMeta().getTraceId());
        }
        if (CommonUtilities.isStringNullOrBlank(patientElligibilityOutput.getPlanStatus().get(0).getStatus())) {
            patientInsuranceVerificationParameterDTO
                .setNetworkInfo(patientElligibilityOutput.getPlanStatus().get(0).getStatus());
        }
        return patientInsuranceVerificationParameterDTO;
    }
}
