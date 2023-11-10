package com.sunknowledge.dme.rcm.service.impl.patiententry.aggregate;

import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.repository.PatientInsuranceEntryRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.PatientInsuranceDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SavePatientInsuranceCommand;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.UpdatePatientInsuranceStatusCommand;
import com.sunknowledge.dme.rcm.service.mapper.PatientInsuranceMapper;
import com.sunknowledge.dme.rcm.service.patiententry.aggregate.PatientInsuranceEntryAggregate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

@Service
@Transactional
public class PatientInsuranceEntryAggregateImpl implements PatientInsuranceEntryAggregate {
    private final PatientInsuranceMapper patientInsuranceMapper;
    @Autowired
    PatientInsuranceEntryRepositoryExtended patientInsuranceEntryRepositoryExtended;

    public PatientInsuranceEntryAggregateImpl(PatientInsuranceMapper patientInsuranceMapper) {
        this.patientInsuranceMapper = patientInsuranceMapper;
    }

    @Override
    public Mono<ResponseDTO> handleSavePatientInsuranceCommand(SavePatientInsuranceCommand savePatientInsuranceCommand) {
        PatientInsuranceDTO patientInsuranceDTO = new PatientInsuranceDTO();
        try {
            if (savePatientInsuranceCommand.getPatientInsuranceId() == null || savePatientInsuranceCommand.getPatientInsuranceId() == 0) {
                CommonUtilities.dtoTrimmer(savePatientInsuranceCommand);
                BeanUtils.copyProperties(savePatientInsuranceCommand, patientInsuranceDTO, "createdDate", "updatedDate");
                patientInsuranceDTO.setCreatedDate(LocalDate.now());
                patientInsuranceDTO.setCreatedById(1L);
                patientInsuranceDTO.setCreatedByName("Abhijit Chowdhury");
                patientInsuranceDTO.setStatus("active");
                patientInsuranceDTO.setPatientInsuranceUuid(UUID.randomUUID());
                patientInsuranceDTO.setPatientInsuranceId(null);
                return patientInsuranceEntryRepositoryExtended
                    .save(patientInsuranceMapper.toEntity(patientInsuranceDTO))
                    .map(patientInsuranceMapper::toDto).map(
                        i -> new ResponseDTO(true, "Successfully Saved", i));
            } else {
                return patientInsuranceEntryRepositoryExtended
                    .findById(savePatientInsuranceCommand.getPatientInsuranceId())
                    .map(existingObj -> {
                        existingObj.setUpdatedDate(LocalDate.now());
                        existingObj.setUpdatedByName("Abhijit Chowdhury");
                        existingObj.setUpdatedById(1L);
                        existingObj.setStatus("inactive");
                        return existingObj;
                    })
                    .flatMap(patientInsuranceEntryRepositoryExtended::save)
                    .map(savedObj -> {
                        CommonUtilities.dtoTrimmer(savePatientInsuranceCommand);
                        BeanUtils.copyProperties(savePatientInsuranceCommand, savedObj, "createdDate", "updatedDate");
                        savedObj.setCreatedDate(LocalDate.now());
                        savedObj.setCreatedById(1L);
                        savedObj.setCreatedByName("Abhijit Chowdhury");
                        savedObj.setStatus("active");
                        savedObj.setPatientInsuranceUuid(UUID.randomUUID());
                        savedObj.setPatientInsuranceId(null);
                        return savedObj;
                    }).flatMap(patientInsuranceEntryRepositoryExtended::save)
                    .map(patientInsuranceMapper::toDto)
                    .map(updatedObj -> new ResponseDTO(true, "Successfully Saved", updatedObj));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Mono.just(new ResponseDTO(false, "Data Error! Failed to Save.", null));
        }
    }

    @Override
    public Mono<ResponseDTO> handleUpdatePatientInsuranceStatusCommand(UpdatePatientInsuranceStatusCommand updatePatientInsuranceStatusCommand) {
        try {
            Mono<PatientInsuranceDTO> patientInsuranceDTO = patientInsuranceEntryRepositoryExtended.findById(updatePatientInsuranceStatusCommand.getPatientInsuranceId())
                .map(patientInsuranceMapper::toDto);
            CommonUtilities.dtoTrimmer(updatePatientInsuranceStatusCommand);
            if (patientInsuranceDTO != null) {
                if (updatePatientInsuranceStatusCommand.getStatus().equalsIgnoreCase("active")) {

                    return patientInsuranceEntryRepositoryExtended
                        .findById(updatePatientInsuranceStatusCommand.getPatientInsuranceId())
                        .map(existingObj -> {
                            if (existingObj.getStatus().equalsIgnoreCase(
                                "active")) {
                                existingObj.setUpdatedDate(LocalDate.now());
                            } else {
                                existingObj.setCreatedDate(LocalDate.now());
                                existingObj.setUpdatedDate(null);
                                existingObj.setPatientInsuranceId(null);
                            }
                            existingObj.setStatus(updatePatientInsuranceStatusCommand.getStatus());
                            return existingObj;
                        })
                        .flatMap(patientInsuranceEntryRepositoryExtended::save)
                        .map(patientInsuranceMapper::toDto).map(
                            i -> new ResponseDTO(true, "Successfully Saved", i));
                } else {

                    return patientInsuranceEntryRepositoryExtended
                        .findById(updatePatientInsuranceStatusCommand.getPatientInsuranceId())
                        .map(existingObj -> {
                            existingObj.setUpdatedDate(LocalDate.now());
                            existingObj.setStatus(updatePatientInsuranceStatusCommand.getStatus());
                            return existingObj;
                        })
                        .flatMap(patientInsuranceEntryRepositoryExtended::save)
                        .map(patientInsuranceMapper::toDto).map(
                            i -> new ResponseDTO(true, "Successfully Saved", i));
                }
            } else {
                return Mono.just(new ResponseDTO(false, "Patient Insurance Not Exist.", null));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Mono.just(new ResponseDTO(false, "Data Error! Failed to Save.", null));
        }
    }

}
