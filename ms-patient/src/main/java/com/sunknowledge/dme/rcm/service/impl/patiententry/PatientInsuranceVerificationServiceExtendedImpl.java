package com.sunknowledge.dme.rcm.service.impl.patiententry;

import com.sunknowledge.dme.rcm.service.dto.PatientInsuranceDTO;
import com.sunknowledge.dme.rcm.service.dto.PatientMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.PatientInsuranceVerificationParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SavePatientInsuranceVerificationCommand;
import com.sunknowledge.dme.rcm.service.patiententry.CheckPatientElligibility;
import com.sunknowledge.dme.rcm.service.patiententry.PatientInsuranceVerificationEntryServiceExtended;
import com.sunknowledge.dme.rcm.service.patiententry.aggregate.PatientInsuranceVerificationAggregate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Transactional
@Service("patientInsuranceVerificationServiceExtended")
public class PatientInsuranceVerificationServiceExtendedImpl
		implements PatientInsuranceVerificationEntryServiceExtended {
	@Autowired
	PatientInsuranceVerificationAggregate patientInsuranceVerificationAggregate;


	  @Autowired
	  CheckPatientElligibility checkPatientElligibility;


	@Override
	public Mono<ResponseDTO> savePatientInsuranceVerification(SavePatientInsuranceVerificationCommand obj) {
		return patientInsuranceVerificationAggregate
				.handleSavePatientInsuranceVerificationCommand(obj);
	}

	@Override
	public Mono<ResponseDTO> checkPatientElligibility(String patientId) {
		SavePatientInsuranceVerificationCommand savePatientInsuranceVerificationCommand = new SavePatientInsuranceVerificationCommand();
		PatientInsuranceVerificationParameterDTO patientInsuranceVerificationParameterDTO = new PatientInsuranceVerificationParameterDTO();
        System.out.println("=========Before checkElligibility========");
		patientInsuranceVerificationParameterDTO=checkPatientElligibility.checkElligibility(patientId);
        System.out.println("=========After checkElligibility========");
		BeanUtils.copyProperties(patientInsuranceVerificationParameterDTO, savePatientInsuranceVerificationCommand);
		// TODO Auto-generated method stub
		return patientInsuranceVerificationAggregate
				.handleSavePatientElligibility(savePatientInsuranceVerificationCommand);
	}

    @Override
    public Mono<ResponseDTO> checkPatientElligibilityReactive(Long patientId, PatientMasterDTO objpatient, PatientInsuranceDTO patientInsuranceDTO, String accessToken){
        return patientInsuranceVerificationAggregate.checkAndHandleSavePatientElligibility(patientId, objpatient, patientInsuranceDTO, accessToken);
    }
}
