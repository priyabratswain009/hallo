package com.sunknowledge.dme.rcm.service.impl.patientsearch;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.service.dto.PatientInsuranceDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientInsuranceByPatientUUIDAndPayerLevel;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientInsuranceSearchByPatientIdAndInsuranceId;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientInsuranceSearchServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.queryhandler.PatientInsuranceSearchQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service("patientInsuranceSearchServiceExtendedImpl")
public class PatientInsuranceSearchServiceExtendedImpl implements PatientInsuranceSearchServiceExtended {
    @Autowired
    PatientInsuranceSearchQueryHandler patientInsuranceSearchQueryHandler;

	@Override
	public Flux<PatientInsuranceDTO> getPatientInsuranceBySearchParameters(PatientInsuranceSearchByPatientIdAndInsuranceId obj) {
		return patientInsuranceSearchQueryHandler.getPatientInsuranceBySearchParametersQueryHandler(obj);
	}

	@Override
	public Flux<PatientInsuranceDTO> getPatientInsuranceByPatientId(Long patientId) {
		// TODO Auto-generated method stub
		PatientInsuranceSearchByPatientIdAndInsuranceId obj = new PatientInsuranceSearchByPatientIdAndInsuranceId();
		obj.setPatientID(patientId);
		return patientInsuranceSearchQueryHandler.getPatientInsuranceBySearchParametersQueryHandler(obj);
	}

    @Override
    public Mono<Long> getIDByUUID(UUID patientUuid) {
        return patientInsuranceSearchQueryHandler.getIDByUUID(patientUuid);
    }

    @Override
    public Mono<Object> getPatientInsuranceByPatInsuranceId(PatientInsuranceSearchByPatientIdAndInsuranceId obj)
    {
        return patientInsuranceSearchQueryHandler.getPatientInsuranceByPatInsuranceId(obj.getPatInsuranceId());
    }

    @Override
    public Mono<PatientInsuranceDTO> getPatientInsuranceByPatientUUIDAndPayerLevel(PatientInsuranceByPatientUUIDAndPayerLevel patientInsuranceByPatientUUIDAndPayerLevel) {
        return patientInsuranceSearchQueryHandler.getPatientInsuranceByPatientUUIDAndPayerLevel(patientInsuranceByPatientUUIDAndPayerLevel);
    }

    @Override
    public Mono<ServiceOutcome> getExixtingPayerLevelsByPatientUUID(Long patientId) throws ExecutionException, InterruptedException {
        return patientInsuranceSearchQueryHandler.getExixtingPayerLevelsByPatientUUID(patientId);
    }

    @Override
    public Mono<PatientInsuranceDTO> getPatientInsuranceInfoByPatientInsId(Long patientInsId) {
        return patientInsuranceSearchQueryHandler.getPatientInsuranceInfoByPatientInsId(patientInsId);
    }

    @Override
    public Mono<Long> getPatientIdByPatientInsuranceUUID(UUID patientInsuranceUUID) {
        return patientInsuranceSearchQueryHandler.getPatientIdByPatientInsuranceUUID(patientInsuranceUUID);
    }
}
