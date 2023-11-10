package com.sunknowledge.dme.rcm.service.impl.patiententry.aggregate;

import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.repository.PatientDocumentEntryRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.PatientDocumentDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SavePatientDocumentDetailsCommand;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.UploadPatientDocumentByPatientIdCommand;
import com.sunknowledge.dme.rcm.service.mapper.PatientDocumentMapper;
import com.sunknowledge.dme.rcm.service.patiententry.aggregate.PatientDocumentEntryAggregate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientDocumentEntryAggregateImpl implements PatientDocumentEntryAggregate {
    @Autowired
    PatientDocumentEntryRepositoryExtended patientDocumentEntryRepositoryExtended;

    private final PatientDocumentMapper patientDocumentMapper;

    public PatientDocumentEntryAggregateImpl(PatientDocumentMapper patientDocumentMapper) {
        this.patientDocumentMapper = patientDocumentMapper;
    }

    /*@Override
    public Mono<ResponseDTO> handleSavePatientDocumentInfoCommand(SavePatientDocumentDetailsCommand savePatientDocumentDetailsCommand) {
        PatientDocumentDTO patientDocumentDTO = new PatientDocumentDTO();
        List<Object> responseList = new ArrayList<>();
        try {
            if (savePatientDocumentDetailsCommand.getPatientDocumentId() == null || savePatientDocumentDetailsCommand.getPatientDocumentId() == 0) {
                CommonUtilities.dtoTrimmer(savePatientDocumentDetailsCommand);
                BeanUtils.copyProperties(savePatientDocumentDetailsCommand, patientDocumentDTO, "createdDate", "updatedDate");
                patientDocumentDTO.setCreatedDate(LocalDate.now());
                patientDocumentDTO.setPatientDocumentId(null);
                return patientDocumentEntryRepositoryExtended
                    .save(patientDocumentMapper.toEntity(patientDocumentDTO))
                    .map(patientDocumentMapper::toDto).map(
                        i -> new ResponseDTO(true, "Successfully Saved", List.of(i)));
            } else {
                return patientDocumentEntryRepositoryExtended
                    .findById(savePatientDocumentDetailsCommand.getPatientDocumentId())
                    .map(existingObj -> {
                        existingObj.setUpdatedDate(LocalDate.now());
                        existingObj.setStatus("inactive");
                        return existingObj;
                    })
                    .flatMap(patientDocumentEntryRepositoryExtended::save)
                    .map(savedObj -> {
                        CommonUtilities.dtoTrimmer(savePatientDocumentDetailsCommand);
                        BeanUtils.copyProperties(savePatientDocumentDetailsCommand, savedObj, "createdDate", "updatedDate");
                        savedObj.setCreatedDate(LocalDate.now());
                        savedObj.setPatientDocumentId(null);
                        savedObj.setUpdatedDate(null);
                        return savedObj;
                    }).flatMap(patientDocumentEntryRepositoryExtended::save)
                    .map(updatedObj -> new ResponseDTO(true, "Successfully Saved", List.of(updatedObj)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Mono.just(new ResponseDTO(false, "Data Error! Failed to Save.", responseList));
        }
    }*/


    /*@Override
    public Mono<ResponseDTO> handleUploadPatientDocumentInfoCommand(UploadPatientDocumentByPatientIdCommand uploadPatientDocumentByPatientIdCommand) {
        PatientDocumentDTO patientDocumentDTO = null;
        String message = new String("");
        Boolean status = false;
        List<Object> responseList = new ArrayList<>();
        try {
            patientDocumentDTO = patientDocumentEntryRepositoryExtended.findById(uploadPatientDocumentByPatientIdCommand.getPatientDocumentId())
                .map(patientDocumentMapper::toDto).toFuture().get();
            patientDocumentDTO.setFileUploadPath(uploadPatientDocumentByPatientIdCommand.getBasePath());
            patientDocumentDTO.setUpdatedDate(LocalDate.now());
            patientDocumentDTO.setUploadDate(LocalDate.now());
            CommonUtilities.dtoTrimmer(patientDocumentDTO);
            Mono<PatientDocumentDTO> responsePatient = patientDocumentEntryRepositoryExtended
                .save(patientDocumentMapper.toEntity(patientDocumentDTO))
                .map(patientDocumentMapper::toDto);
            message = "Successfully Uploaded.";
            status = true;
            responseList.add(responsePatient.toFuture().get());
        } catch (Exception e) {
            e.printStackTrace();
            message = "Failed to Upload.";
            status = false;
        }
        return Mono.just(new ResponseDTO(status, message, responseList));
    }*/
}
