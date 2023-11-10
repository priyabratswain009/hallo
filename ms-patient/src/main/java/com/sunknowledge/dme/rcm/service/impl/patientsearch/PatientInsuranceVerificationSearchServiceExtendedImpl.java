package com.sunknowledge.dme.rcm.service.impl.patientsearch;

import com.sunknowledge.dme.rcm.service.dto.PatientInsVerifStatDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.PatientInsVerifStatAuditLogByPatientNoAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientInsVerifSearchByInsVerifIdOrPatientInsId;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientInsuranceVerificationSearchServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.queryhandler.PatientInsuranceVerificationSearchQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service("patientInsuranceVerificationSearchServiceExtendedImpl")
public class PatientInsuranceVerificationSearchServiceExtendedImpl implements PatientInsuranceVerificationSearchServiceExtended {
    @Autowired
    PatientInsuranceVerificationSearchQueryHandler patientInsuranceVerificationSearchQueryHandler;

    @Override
    public Flux<PatientInsVerifStatDTO> getPatientInsuranceVerificationBySearchParameters(PatientInsVerifSearchByInsVerifIdOrPatientInsId obj) {
        return patientInsuranceVerificationSearchQueryHandler.getPatientInsuranceVerificationBySearchParametersQueryHandler(obj);
    }

    @Override
    public Flux<PatientInsVerifStatDTO> getPatientInsuranceVerificationByInsuranceIds(List<Long> patientInsuranceIdList) {
        PatientInsVerifStatAuditLogByPatientNoAndUserIdAndDateQuery obj =
            new PatientInsVerifStatAuditLogByPatientNoAndUserIdAndDateQuery();
        obj.setPatientInsuranceIdList(patientInsuranceIdList);

        return patientInsuranceVerificationSearchQueryHandler.getPatientInsuranceVerificationByInsuranceIdsQueryHandler(obj);
    }

    @Override
    public Long getIDByUUID(UUID patientInsVerifStatUuid) {
        return patientInsuranceVerificationSearchQueryHandler.getIDByUUID(patientInsVerifStatUuid);
    }

    public Mono<PatientInsVerifStatDTO> getPatientInsuranceVerificationDetail(Long pInVeSid) throws ExecutionException, InterruptedException {
        return patientInsuranceVerificationSearchQueryHandler.getPatientInsuranceVerificationDetail(pInVeSid);
    }

    public Mono<PatientInsVerifStatDTO> getPatientInsuranceVerificationByPatientInsId(Long patientInsId) throws ExecutionException, InterruptedException {
        return patientInsuranceVerificationSearchQueryHandler.getPatientInsuranceVerificationByPatientInsId(patientInsId);

    }

    public Flux<PatientInsVerifStatDTO> getPatientInsuranceVerificationByPatientInsuranceUUID(Long patientInsuranceId) throws ExecutionException, InterruptedException {
        return patientInsuranceVerificationSearchQueryHandler.getPatientInsuranceVerificationByPatientInsuranceUUID(patientInsuranceId);
    }

    public Mono<PatientInsVerifStatDTO> getLastetPatientInsuranceEligibilityStatusByPatientInsId(Long patientInsId){
        return patientInsuranceVerificationSearchQueryHandler.getLastetPatientInsuranceEligibilityStatusByPatientInsId(patientInsId);
    }
}
