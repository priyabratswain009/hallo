package com.sunknowledge.dme.rcm.service.impl.patiententry.aggregate;

import com.sunknowledge.dme.rcm.repository.PatientDtoEntryRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.PatientMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SavePatientDTOCommand;
import com.sunknowledge.dme.rcm.service.mapper.PatientDtoMapper;
import com.sunknowledge.dme.rcm.service.patiententry.aggregate.PatientDTOEntryAggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PatientDTOEntryAggregateImpl implements PatientDTOEntryAggregate {
    private final PatientDtoMapper patientDtoMapper;
    @Autowired
    PatientDtoEntryRepositoryExtended patientDTOEntryRepositoryExtended;

    public PatientDTOEntryAggregateImpl(PatientDtoMapper patientDtoMapper) {
        this.patientDtoMapper = patientDtoMapper;
    }

    @Override
    public Mono<ResponseDTO> handleGeneratePatientBucket(SavePatientDTOCommand savePatientDTOCommand) {
        PatientMasterDTO patientMasterDTO = new PatientMasterDTO();
        try {
            System.out.println("Patient Bucket Entry Aggregate Called :: Before Repository");
            Mono<Void> ptBktProcedureCall = patientDTOEntryRepositoryExtended.
                generatePatientBucket(savePatientDTOCommand.getPatientUUID());
            ptBktProcedureCall.subscribe(
                error -> System.err.println("Error calling procedure: " + error)
            );

//            System.out.println("Patient Bucket Entry Aggregate Called :: After Repository");
            return Mono.just(new ResponseDTO(true, "Successfully Generated.", null));
        } catch (Exception e) {
            e.printStackTrace();
            return Mono.just(new ResponseDTO(false, "Data Error! Failed to Generate.", null));
        }
    }

}
