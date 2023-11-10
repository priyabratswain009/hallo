package com.sunknowledge.dme.rcm.service.impl.patiententry;

import com.sunknowledge.dme.rcm.service.dto.patiententry.PatientDiagnosisParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SavePatientDiagnosisDetailsCommand;
import com.sunknowledge.dme.rcm.service.patiententry.PatientDiagnosisEntryServiceExtended;
import com.sunknowledge.dme.rcm.service.patiententry.aggregate.PatientDiagnosisEntryAggregate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Transactional
@Service("patientDiagnosisEntryServiceExtended")
public class PatientDiagnosisEntryServiceExtendedImpl implements PatientDiagnosisEntryServiceExtended {
    @Autowired
    PatientDiagnosisEntryAggregate patientDiagnosisEntryAggregate;

    @Override
    public Mono<ResponseDTO> savePatientDiagnosis(SavePatientDiagnosisDetailsCommand obj) {
          //"createdDate", "updatedDate"
        return patientDiagnosisEntryAggregate.handleSavePatientDiagnosisCommand(obj);
    }
}
