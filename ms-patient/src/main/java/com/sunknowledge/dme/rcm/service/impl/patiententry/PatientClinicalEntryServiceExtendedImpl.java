package com.sunknowledge.dme.rcm.service.impl.patiententry;

import com.sunknowledge.dme.rcm.service.dto.patiententry.PatientClinicalParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SavePatientClinicalDetailsCommand;
import com.sunknowledge.dme.rcm.service.patiententry.PatientClinicalEntryServiceExtended;
import com.sunknowledge.dme.rcm.service.patiententry.aggregate.PatientClinicalEntryAggregate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Transactional
@Service("patientClinicalEntryServiceExtendedImpl")
public class PatientClinicalEntryServiceExtendedImpl implements PatientClinicalEntryServiceExtended {
    @Autowired
    PatientClinicalEntryAggregate patientClinicalEntryAggregate;

    @Override
    public Mono<ResponseDTO> savePatientClinicalDetails(SavePatientClinicalDetailsCommand obj) {
        return patientClinicalEntryAggregate.handleSavePatientClinicalInfoCommand(obj);
    }
}
