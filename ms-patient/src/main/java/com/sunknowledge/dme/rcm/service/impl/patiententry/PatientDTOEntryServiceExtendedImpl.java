package com.sunknowledge.dme.rcm.service.impl.patiententry;

import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.PatientMasterParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SavePatientCommand;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SavePatientDTOCommand;
import com.sunknowledge.dme.rcm.service.patiententry.PatientDTOEntryServiceExtended;
import com.sunknowledge.dme.rcm.service.patiententry.PatientMasterEntryServiceExtended;
import com.sunknowledge.dme.rcm.service.patiententry.aggregate.PatientDTOEntryAggregate;
import com.sunknowledge.dme.rcm.service.patiententry.aggregate.PatientMasterEntryAggregate;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientMasterSearchServiceExtended;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Transactional
@Service("patientDTOEntryServiceExtendedImpl")
public class PatientDTOEntryServiceExtendedImpl implements PatientDTOEntryServiceExtended {
    @Autowired
    PatientDTOEntryAggregate patientDTOEntryAggregate;

    @Override
    public Mono<ResponseDTO> generatePatientBucket(UUID patientUUID)
    {
        SavePatientDTOCommand savePatientDTOCommand = new SavePatientDTOCommand();
        savePatientDTOCommand.setPatientUUID(patientUUID);
        return patientDTOEntryAggregate.handleGeneratePatientBucket(savePatientDTOCommand);
    }
}
