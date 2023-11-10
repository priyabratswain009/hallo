package com.sunknowledge.dme.rcm.service.impl.patiententry.aggregate;

import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.repository.PatientDiagnosisEntryRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.PatientDiagnosisDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SavePatientDiagnosisDetailsCommand;
import com.sunknowledge.dme.rcm.service.mapper.PatientDiagnosisMapper;
import com.sunknowledge.dme.rcm.service.patiententry.aggregate.PatientDiagnosisEntryAggregate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PatientDiagnosisEntryAggregateImpl implements PatientDiagnosisEntryAggregate {
    @Autowired
    PatientDiagnosisEntryRepositoryExtended patientDiagnosisEntryRepositoryExtended;

    private final PatientDiagnosisMapper patientDiagnosisMapper;

    public PatientDiagnosisEntryAggregateImpl(PatientDiagnosisMapper patientDiagnosisMapper) {
        this.patientDiagnosisMapper = patientDiagnosisMapper;
    }

    @Override
    public Mono<ResponseDTO> handleSavePatientDiagnosisCommand(SavePatientDiagnosisDetailsCommand savePatientDiagnosisDetailsCommand) {
        PatientDiagnosisDTO patientDiagnosisDTO = new PatientDiagnosisDTO();
        List<Object> responseList = new ArrayList<>();
        try {
            if (savePatientDiagnosisDetailsCommand.getPatientDiagnosisId() == null || savePatientDiagnosisDetailsCommand.getPatientDiagnosisId() == 0) {
                CommonUtilities.dtoTrimmer(savePatientDiagnosisDetailsCommand);
                BeanUtils.copyProperties(savePatientDiagnosisDetailsCommand, patientDiagnosisDTO, "createdDate", "updatedDate");
                patientDiagnosisDTO.setCreatedDate(LocalDate.now());
                patientDiagnosisDTO.setCreatedById(1L);
                patientDiagnosisDTO.setCreatedByName("Abhijit Chowdhury");
                patientDiagnosisDTO.setPatientDiagnosisId(null);
                patientDiagnosisDTO.setStatus("active");
                patientDiagnosisDTO.setPatientDiagnosisUuid(UUID.randomUUID());
                return patientDiagnosisEntryRepositoryExtended
                    .save(patientDiagnosisMapper.toEntity(patientDiagnosisDTO))
                    .map(patientDiagnosisMapper::toDto).map(
                        i -> new ResponseDTO(true, "Successfully Saved", i));
            } else {
                return patientDiagnosisEntryRepositoryExtended
                    .findById(savePatientDiagnosisDetailsCommand.getPatientDiagnosisId())
                    .map(existingObj -> {
                        existingObj.setUpdatedDate(LocalDate.now());
                        existingObj.setUpdatedById(1L);
                        existingObj.setUpdatedByName("Abhijit Chowdhury");
                        existingObj.setStatus("inactive");
                        return existingObj;
                    })
                    .flatMap(patientDiagnosisEntryRepositoryExtended::save)
                    .map(savedObj -> {
                        CommonUtilities.dtoTrimmer(savePatientDiagnosisDetailsCommand);
                        BeanUtils.copyProperties(savePatientDiagnosisDetailsCommand, savedObj, "createdDate", "updatedDate");
                        savedObj.setCreatedDate(LocalDate.now());
                        savedObj.setCreatedById(1L);
                        savedObj.setCreatedByName("Abhijit Chowdhury");
                        savedObj.setPatientDiagnosisId(null);
                        savedObj.setStatus("active");
                        savedObj.setPatientDiagnosisUuid(UUID.randomUUID());
                        return savedObj;
                    }).flatMap(patientDiagnosisEntryRepositoryExtended::save)
                    .map(updatedObj -> new ResponseDTO(true, "Successfully Saved", updatedObj));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Mono.just(new ResponseDTO(false, "Data Error! Failed to Save.", responseList));
        }
    }

}
