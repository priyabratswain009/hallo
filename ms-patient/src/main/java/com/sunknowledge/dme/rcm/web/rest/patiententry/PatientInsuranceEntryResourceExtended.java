package com.sunknowledge.dme.rcm.web.rest.patiententry;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.service.dto.PatientInsuranceDTO;
import com.sunknowledge.dme.rcm.service.dto.PatientMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.PatientInsuranceParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.PatientInsuranceParameterMicroserviceDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SavePatientInsuranceCommand;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientInsuranceByPatientUUIDAndPayerLevel;
import com.sunknowledge.dme.rcm.service.patiententry.PatientInsuranceEntryServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientInsuranceSearchServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientMasterSearchServiceExtended;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * REST controller for SalesOrderInsuranceDetailsSearchResource.
 */

@Validated
@RestController
@RequestMapping("/api")
public class PatientInsuranceEntryResourceExtended {
    private final Logger log = LoggerFactory.getLogger(PatientInsuranceEntryResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    @Qualifier("patientInsuranceEntryServiceExtendedImpl")
    PatientInsuranceEntryServiceExtended patientInsuranceEntryServiceExtended;
    @Autowired
    PatientInsuranceSearchServiceExtended patientInsuranceSearchServiceExtended;
    @Autowired
    PatientMasterSearchServiceExtended patientMasterSearchServiceExtended;

    /**
     * Save Patient Insurance Information
     */
    @PatchMapping(value = "savePatientInsurance", consumes = {"application/json", "application/merge-patch+json"})
    public Mono<ServiceOutcome<PatientInsuranceDTO>> savePatientInsurance(
        @RequestBody @Valid PatientInsuranceParameterDTO patientInsuranceParameterDTO
    ) throws ExecutionException, InterruptedException {
        //----- Implementing UUID_To_ID Bridge Method ----------
        // ----If UUID not required, then pass UUID as null ----------
        Long pInId = 0L;
        if (patientInsuranceParameterDTO.getPatientInsuranceUuid() != null) {
            pInId = patientInsuranceSearchServiceExtended.getIDByUUID(patientInsuranceParameterDTO.getPatientInsuranceUuid()).toFuture().get();
            pInId = pInId == null ? 0L : pInId;
        }
        Long pId = 0L;
        if (patientInsuranceParameterDTO.getPatientUUID() != null) {
            pId = patientMasterSearchServiceExtended.getIDByUUID(patientInsuranceParameterDTO.getPatientUUID()).toFuture().get();
            pId = pId == null ? 0L : pId;
        }
        SavePatientInsuranceCommand obj = new SavePatientInsuranceCommand();
        BeanUtils.copyProperties(patientInsuranceParameterDTO, obj);
        obj.setPatientId(pId);
        obj.setPatientInsuranceId(pInId);
        obj.setPatientRelationshipInsured(patientInsuranceParameterDTO.getRelationship());
        if (patientInsuranceParameterDTO.getRelationship().equalsIgnoreCase("self")) {
            try {
                PatientMasterDTO patientMasterDTO = patientMasterSearchServiceExtended.getPatientDetailsByPatientId(pId).toFuture().get();
                obj.setInsuredFirstName(patientMasterDTO.getPatientFirstName());
                obj.setInsuredMiddleName(patientMasterDTO.getPatientMiddleName());
                //obj.setInsuredSuffix(patientMasterDTO.get());
                obj.setInsuredDob(patientMasterDTO.getDob());
                obj.setInsuredSsn(patientMasterDTO.getSsn());
                obj.setInsuredGender(patientMasterDTO.getGender());
                obj.setInsuredLastName(patientMasterDTO.getPatientLastName());
                obj.setInsurerAddressLine1(patientMasterDTO.getAddressLine1());
                obj.setInsurerAddressLine2(patientMasterDTO.getAddressLine2());
                obj.setInsurerCity(patientMasterDTO.getCity());
                obj.setInsurerState(patientMasterDTO.getState());
                obj.setInsurerZip(patientMasterDTO.getZip());
                obj.setInsurerContact1(patientMasterDTO.getContactNo1());
                obj.setInsurerFax(patientMasterDTO.getFax());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        return patientInsuranceEntryServiceExtended.savePatientInsurance(obj).map(x -> {
            return new ServiceOutcome<PatientInsuranceDTO>((PatientInsuranceDTO) x.getData(), x.getStatus(), x.getMessage());
        });
    }

    /**
     * Save Patient Insurance Information For Microservice
     */
    @PatchMapping(value = "savePatientInsuranceForMicroservice", consumes = {"application/json", "application/merge-patch+json"})
    public Mono<ServiceOutcome<PatientInsuranceDTO>> savePatientInsuranceForMicroservice(
        @RequestBody @Valid PatientInsuranceParameterMicroserviceDTO patientInsuranceParameterDTO
    ) throws ExecutionException, InterruptedException {

        Long pId = patientInsuranceParameterDTO.getPatientID();

        PatientInsuranceByPatientUUIDAndPayerLevel patientInsuranceByPatientUUIDAndPayerLevel = new PatientInsuranceByPatientUUIDAndPayerLevel();
        patientInsuranceByPatientUUIDAndPayerLevel.setPatientId(pId);
        patientInsuranceByPatientUUIDAndPayerLevel.setPayerLevel(patientInsuranceParameterDTO.getPayerLevel());
        PatientInsuranceDTO patientInsuranceDTO = null;

        try {
            patientInsuranceDTO = patientInsuranceSearchServiceExtended.getPatientInsuranceByPatientUUIDAndPayerLevel(patientInsuranceByPatientUUIDAndPayerLevel).toFuture().get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        if (patientInsuranceDTO != null
            && patientInsuranceDTO.getInsuranceId() != null
            && patientInsuranceDTO.getInsuranceId() != 0) {
            return Mono.just(new ServiceOutcome<PatientInsuranceDTO>(patientInsuranceDTO, true, "Successfully Fetched."));
        } else {
            //----- Implementing UUID_To_ID Bridge Method ----------
            // ----If UUID not required, then pass UUID as null ----------
            Long pInId = 0L;
            if (patientInsuranceParameterDTO.getPatientInsuranceUuid() != null) {
                pInId = patientInsuranceSearchServiceExtended.getIDByUUID(patientInsuranceParameterDTO.getPatientInsuranceUuid()).toFuture().get();
                pInId = pInId == null ? 0L : pInId;
            }
            pId = patientInsuranceParameterDTO.getPatientID();

            SavePatientInsuranceCommand obj = new SavePatientInsuranceCommand();
            BeanUtils.copyProperties(patientInsuranceParameterDTO, obj);
            obj.setPatientId(pId);
            obj.setPatientInsuranceId(pInId);
            obj.setPatientRelationshipInsured(patientInsuranceParameterDTO.getRelationship());
            if (patientInsuranceParameterDTO.getRelationship().equalsIgnoreCase("self")) {
                try {
                    PatientMasterDTO patientMasterDTO = patientMasterSearchServiceExtended.getPatientDetailsByPatientId(pId).toFuture().get();
                    obj.setInsuredFirstName(patientMasterDTO.getPatientFirstName());
                    obj.setInsuredMiddleName(patientMasterDTO.getPatientMiddleName());
                    //obj.setInsuredSuffix(patientMasterDTO.get());
                    obj.setInsuredDob(patientMasterDTO.getDob());
                    obj.setInsuredSsn(patientMasterDTO.getSsn());
                    obj.setInsuredGender(patientMasterDTO.getGender());
                    obj.setInsuredLastName(patientMasterDTO.getPatientLastName());
                    obj.setInsurerAddressLine1(patientMasterDTO.getAddressLine1());
                    obj.setInsurerAddressLine2(patientMasterDTO.getAddressLine2());
                    obj.setInsurerCity(patientMasterDTO.getCity());
                    obj.setInsurerState(patientMasterDTO.getState());
                    obj.setInsurerZip(patientMasterDTO.getZip());
                    obj.setInsurerContact1(patientMasterDTO.getContactNo1());
                    obj.setInsurerFax(patientMasterDTO.getFax());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
            return patientInsuranceEntryServiceExtended.savePatientInsurance(obj).map(x -> {
                return new ServiceOutcome<PatientInsuranceDTO>((PatientInsuranceDTO) x.getData(), x.getStatus(), x.getMessage());
            });
        }
    }

    /**
     * Update Patient Insurance Status
     */
    @PutMapping(value = "updatePatientInsuranceStatus")
    public Mono<ResponseDTO> updatePatientInsuranceStatus(
        @NotNull(message = "Patient_Insurance_UUID must be provided.")
        @RequestParam(value = "patientInsuranceUUID") final UUID patientInsuranceUUID,
        @NotNull(message = "Status must be provided.")
        @NotBlank(message = "Status must be provided.")
        @Pattern(regexp = "active|inactive", message = "Kindly provide status as active/inactive only.")
        @RequestParam(value = "status") final String status
    ) throws ExecutionException, InterruptedException {
        Long patientInsuranceId = 0L;
        if (patientInsuranceUUID != null) {
            patientInsuranceId = patientInsuranceSearchServiceExtended.getIDByUUID(patientInsuranceUUID).toFuture().get();
            patientInsuranceId = patientInsuranceId == null ? 0L : patientInsuranceId;
        }
        return patientInsuranceEntryServiceExtended.updatePatientInsuranceStatus(patientInsuranceId, status);
    }
}
