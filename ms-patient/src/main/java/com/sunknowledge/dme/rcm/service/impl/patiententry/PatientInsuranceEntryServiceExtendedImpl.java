package com.sunknowledge.dme.rcm.service.impl.patiententry;

import com.sunknowledge.dme.rcm.service.dto.patiententry.PatientInsuranceParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SavePatientInsuranceCommand;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.UpdatePatientInsuranceStatusCommand;
import com.sunknowledge.dme.rcm.service.patiententry.PatientInsuranceEntryServiceExtended;
import com.sunknowledge.dme.rcm.service.patiententry.aggregate.PatientInsuranceEntryAggregate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Transactional
@Service("patientInsuranceEntryServiceExtendedImpl")
public class PatientInsuranceEntryServiceExtendedImpl implements PatientInsuranceEntryServiceExtended {
    @Autowired
    PatientInsuranceEntryAggregate patientInsuranceEntryAggregate;

    @Override
    public Mono<ResponseDTO> savePatientInsurance(SavePatientInsuranceCommand obj) {
        return patientInsuranceEntryAggregate.handleSavePatientInsuranceCommand(obj);
    }

    @Override
    public Mono<ResponseDTO> updatePatientInsuranceStatus(Long patientInsuranceId, String status) {
        UpdatePatientInsuranceStatusCommand updatePatientInsuranceStatusCommand = new UpdatePatientInsuranceStatusCommand();
        updatePatientInsuranceStatusCommand.setPatientInsuranceId(patientInsuranceId);
        updatePatientInsuranceStatusCommand.setStatus(status);
        return patientInsuranceEntryAggregate.handleUpdatePatientInsuranceStatusCommand(updatePatientInsuranceStatusCommand);
    }
}
