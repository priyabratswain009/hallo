package com.sunknowledge.dme.rcm.service.impl.patiententry;

import com.sunknowledge.dme.rcm.service.dto.patiententry.PatientMasterParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SavePatientCommand;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SavePatientDTOCommand;
import com.sunknowledge.dme.rcm.service.patiententry.PatientDTOEntryServiceExtended;
import com.sunknowledge.dme.rcm.service.patiententry.PatientMasterEntryServiceExtended;
import com.sunknowledge.dme.rcm.service.patiententry.aggregate.PatientMasterEntryAggregate;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientMasterSearchServiceExtended;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Transactional
@Service("patientMasterEntryServiceExtendedImpl")
public class PatientMasterEntryServiceExtendedImpl implements PatientMasterEntryServiceExtended {
    @Autowired
    PatientMasterEntryAggregate patientMasterEntryAggregate;

    @Autowired
    @Qualifier("patientMasterSearchServiceExtendedImpl")
    PatientMasterSearchServiceExtended patientMasterSearchServiceExtended;

    @Autowired
    @Qualifier("patientDTOEntryServiceExtendedImpl")
    PatientDTOEntryServiceExtended patientDTOEntryServiceExtended;

    @Override
    public Mono<ResponseDTO> savePatient(SavePatientCommand obj) {
        return patientMasterEntryAggregate.handleSavePatientCommand(obj);
    }

    @Override
    public Mono<ResponseDTO> generatePatientBucket(UUID patientUUID) {
        return patientDTOEntryServiceExtended.generatePatientBucket(patientUUID);
    }
}
