package com.sunknowledge.dme.rcm.service.impl.patiententry.aggregate;

import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.repository.PatientClinicalEntryRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.PatientClinicalInformationDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SavePatientClinicalDetailsCommand;
import com.sunknowledge.dme.rcm.service.mapper.PatientClinicalInformationMapper;
import com.sunknowledge.dme.rcm.service.patiententry.aggregate.PatientClinicalEntryAggregate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PatientClinicalEntryAggregateImpl implements PatientClinicalEntryAggregate {
    @Autowired
    PatientClinicalEntryRepositoryExtended patientClinicalEntryRepositoryExtended;

    private final PatientClinicalInformationMapper patientClinicalInformationMapper;

    public PatientClinicalEntryAggregateImpl(PatientClinicalInformationMapper patientClinicalInformationMapper) {
        this.patientClinicalInformationMapper = patientClinicalInformationMapper;
    }

    @Override
    public Mono<ResponseDTO> handleSavePatientClinicalInfoCommand(SavePatientClinicalDetailsCommand savePatientClinicalDetailsCommand) {
        PatientClinicalInformationDTO patientClinicalInformationDTO = new PatientClinicalInformationDTO();
        List<Object> responseList = new ArrayList<>();
        try {
            if (savePatientClinicalDetailsCommand.getPatientClinicalInformationId() == null || savePatientClinicalDetailsCommand.getPatientClinicalInformationId() == 0) {
                CommonUtilities.dtoTrimmer(savePatientClinicalDetailsCommand);
                BeanUtils.copyProperties(savePatientClinicalDetailsCommand, patientClinicalInformationDTO, "createdDate", "updatedDate");
                patientClinicalInformationDTO.setCreatedDate(LocalDate.now());
                patientClinicalInformationDTO.setCreatedByName("Abhijit Chowdhury");
                patientClinicalInformationDTO.setCreatedById(1L);
                patientClinicalInformationDTO.setPatientClinicalInformationId(null);
                patientClinicalInformationDTO.setStatus("active");
                patientClinicalInformationDTO.setPatientClinicalInformationUuid(UUID.randomUUID());
                return patientClinicalEntryRepositoryExtended
                    .save(patientClinicalInformationMapper.toEntity(patientClinicalInformationDTO))
                    .map(patientClinicalInformationMapper::toDto).map(
                        i -> new ResponseDTO(true, "Successfully Saved", i));
            } else {
                return patientClinicalEntryRepositoryExtended
                    .findById(savePatientClinicalDetailsCommand.getPatientClinicalInformationId())
                    .map(existingObj -> {
                        existingObj.setUpdatedDate(LocalDate.now());
                        existingObj.setUpdatedByName("Abhijit Chowdhury");
                        existingObj.setUpdatedById(1L);
                        existingObj.setStatus("inactive");
                        return existingObj;
                    })
                    .flatMap(patientClinicalEntryRepositoryExtended::save)
                    .map(savedObj -> {
                        CommonUtilities.dtoTrimmer(savePatientClinicalDetailsCommand);
                        BeanUtils.copyProperties(savePatientClinicalDetailsCommand, savedObj, "createdDate", "updatedDate");
                        savedObj.setCreatedDate(LocalDate.now());
                        savedObj.setCreatedByName("Abhijit Chowdhury");
                        savedObj.setCreatedById(1L);
                        savedObj.setPatientClinicalInformationId(null);
                        savedObj.setStatus("active");
                        savedObj.setPatientClinicalInformationUuid(UUID.randomUUID());
                        return savedObj;
                    }).flatMap(patientClinicalEntryRepositoryExtended::save)
                    .map(updatedObj -> new ResponseDTO(true, "Successfully Saved", updatedObj));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Mono.just(new ResponseDTO(false, "Data Error! Failed to Save.", responseList));
        }
    }

}
