package com.sunknowledge.dme.rcm.service.impl.patiententry.aggregate;

import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.PatientDoctorMap;
import com.sunknowledge.dme.rcm.repository.PatientDoctorMappingRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.PatientDoctorMapDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SavePatientDoctorMappingCommand;
import com.sunknowledge.dme.rcm.service.mapper.PatientDoctorMapMapper;
import com.sunknowledge.dme.rcm.service.patiententry.aggregate.PatientDoctorMappingAggregate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class PatientDoctorMappingAggregateImpl implements PatientDoctorMappingAggregate {
    @Autowired
    PatientDoctorMappingRepositoryExtended patientDoctorMappingRepositoryExtended;

    private final PatientDoctorMapMapper patientDoctorMapMapper;

    public PatientDoctorMappingAggregateImpl(PatientDoctorMapMapper patientDoctorMapMapper) {
        this.patientDoctorMapMapper = patientDoctorMapMapper;
    }

    @Override
    public Mono<ResponseDTO> handleSavePatientDoctorMapping(SavePatientDoctorMappingCommand savePatientDoctorMappingCommand) {
        PatientDoctorMapDTO patientDoctorMapDTO = new PatientDoctorMapDTO();
        //Mono<PatientDoctorMap> patientDoctorMap = patientDoctorMappingRepositoryExtended.findByPatientIdAndNpi(savePatientDoctorMappingCommand.getPatientId(),savePatientDoctorMappingCommand.getDoctorNpiNumber());
        //List<Object> responseList = new ArrayList<>();
        System.out.println("==========SavePatientDoctorMapping function called==========");
        System.out.println("savePatientDoctorMappingCommand else" +savePatientDoctorMappingCommand.getPatientDoctorMapId());

        try {
            if (savePatientDoctorMappingCommand.getPatientDoctorMapId() == null || savePatientDoctorMappingCommand.getPatientDoctorMapId() == 0) {
                CommonUtilities.dtoTrimmer(savePatientDoctorMappingCommand);
                BeanUtils.copyProperties(savePatientDoctorMappingCommand, patientDoctorMapDTO, "createdDate", "updatedDate");
                patientDoctorMapDTO.setCreatedDate(LocalDate.now());
                patientDoctorMapDTO.setCreatedById(1L);  //-- Should be taken from Login Service
                patientDoctorMapDTO.setCreatedByName("Abhijit Chowdhury");  //-- Should be taken from Login Service
                patientDoctorMapDTO.setPatientDoctorMapId(null);
                patientDoctorMapDTO.setPatientDoctorMapUuid(UUID.randomUUID());
                patientDoctorMapDTO.setStatus("Active");
                patientDoctorMapDTO.setUpdatedDate(null);
                patientDoctorMapDTO.setUpdatedById(null);  //-- Should be taken from Login Service
                patientDoctorMapDTO.setUpdatedByName(null);  //-- Should be taken from Login Service
                return patientDoctorMappingRepositoryExtended
                    .save(patientDoctorMapMapper.toEntity(patientDoctorMapDTO))
                    .map(patientDoctorMapMapper::toDto).map(
                        i -> new ResponseDTO(true, "Successfully Saved", i));
            } else {
                System.out.println("savePatientDoctorMappingCommand else" +savePatientDoctorMappingCommand.getPatientDoctorMapId());
                return patientDoctorMappingRepositoryExtended
                    .findById(savePatientDoctorMappingCommand.getPatientDoctorMapId())
                    .map(existingObj -> {
                        existingObj.setUpdatedDate(LocalDate.now());
                        existingObj.setUpdatedById(1L);  //-- Should be taken from Login Service
                        existingObj.setUpdatedByName("Abhijit Chowdhury");  //-- Should be taken from Login Service
                        existingObj.setStatus("inactive");
                        return existingObj;
                    })
                    .flatMap(patientDoctorMappingRepositoryExtended::save)
                    .map(savedObj -> {
                        CommonUtilities.dtoTrimmer(savePatientDoctorMappingCommand);
                        BeanUtils.copyProperties(savePatientDoctorMappingCommand, savedObj, "createdDate", "updatedDate");
                        savedObj.setCreatedDate(LocalDate.now());
                        savedObj.setCreatedById(1L);  //-- Should be taken from Login Service
                        savedObj.setCreatedByName("Abhijit Chowdhury");  //-- Should be taken from Login Service
                        savedObj.setPatientDoctorMapId(null);
                        savedObj.setUpdatedDate(null);
                        savedObj.setUpdatedById(null);  //-- Should be taken from Login Service
                        savedObj.setUpdatedByName(null);  //-- Should be taken from Login Service
                        savedObj.setPatientDoctorMapUuid(UUID.randomUUID());
                        savedObj.setStatus("Active");
                        return savedObj;
                    }).flatMap(patientDoctorMappingRepositoryExtended::save)
                    .map(updatedObj -> new ResponseDTO(true, "Successfully Saved", updatedObj));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Mono.just(new ResponseDTO(false, "Data Error! Failed to Save.", null));
        }
    }

}
