package com.sunknowledge.dme.rcm.service.impl.patientsearch.queryhandler;

import com.sunknowledge.dme.rcm.repository.PatientInsuranceVerifSearchRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.PatientInsVerifStatDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.PatientInsVerifStatAuditLogByPatientNoAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientInsVerifSearchByInsVerifIdOrPatientInsId;
import com.sunknowledge.dme.rcm.service.mapper.PatientInsVerifStatMapper;
import com.sunknowledge.dme.rcm.service.patientsearch.queryhandler.PatientInsuranceVerificationSearchQueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class PatientInsuranceVerificationSearchQueryHandlerImpl implements PatientInsuranceVerificationSearchQueryHandler {
    private final Logger log = LoggerFactory.getLogger(PatientInsuranceVerificationSearchQueryHandlerImpl.class);
    @Autowired
    PatientInsuranceVerifSearchRepositoryExtended patientInsuranceVerifSearchRepositoryExtended;

    private final PatientInsVerifStatMapper patientInsVerifStatMapper;

    public PatientInsuranceVerificationSearchQueryHandlerImpl(PatientInsVerifStatMapper patientInsVerifStatMapper) {
        this.patientInsVerifStatMapper = patientInsVerifStatMapper;
    }

    @Override
    public Flux<PatientInsVerifStatDTO> getPatientInsuranceVerificationBySearchParametersQueryHandler(PatientInsVerifSearchByInsVerifIdOrPatientInsId obj) {
        try {
            return patientInsuranceVerifSearchRepositoryExtended.findAll()
                .map(patientInsVerifStatMapper::toDto)
                .filter(x ->
                    (
                        (x.getInsuranceVerifId() == obj.getInsuranceVerifId() && obj.getPatientInsuranceId() == 0) ||
                            (x.getPatientInsuranceId() == obj.getPatientInsuranceId() && obj.getInsuranceVerifId() == 0)
                    )
                        && x.getStatus().trim().equalsIgnoreCase("active")
                );
        }catch (Exception e){
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Flux<PatientInsVerifStatDTO> getPatientInsuranceVerificationByInsuranceIdsQueryHandler(PatientInsVerifStatAuditLogByPatientNoAndUserIdAndDateQuery obj) {
        try {
            return patientInsuranceVerifSearchRepositoryExtended.findAll()
                .map(patientInsVerifStatMapper::toDto)
                .filter(x ->
                    (
                        (obj.getPatientInsuranceIdList().contains(x.getPatientInsuranceId()))
                    )
                        && x.getStatus().trim().equalsIgnoreCase("active")
                );
        }catch (Exception e){
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long getIDByUUID(UUID patientInsVerifStatUuid) {
        try {
            return patientInsuranceVerifSearchRepositoryExtended.getIDByUUID(patientInsVerifStatUuid).toFuture().get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Mono<PatientInsVerifStatDTO> getPatientInsuranceVerificationDetail(Long pInVeSid){
        return patientInsuranceVerifSearchRepositoryExtended.findById(pInVeSid)
            .map(patientInsVerifStatMapper::toDto)
            .filter(x ->
                (
                    x.getStatus().trim().equalsIgnoreCase("active")
                )
            );
    }

    @Override
    public Mono<PatientInsVerifStatDTO> getPatientInsuranceVerificationByPatientInsId(Long patientInsId){
        return patientInsuranceVerifSearchRepositoryExtended.findByPatientInsIdForPatientInsuranceVerificationId(patientInsId);
    }

    @Override
    public Flux<PatientInsVerifStatDTO> getPatientInsuranceVerificationByPatientInsuranceUUID(Long patientInsuranceId){
        return patientInsuranceVerifSearchRepositoryExtended.findByPatientInsId(patientInsuranceId);
    }

    @Override
    public Mono<PatientInsVerifStatDTO> getLastetPatientInsuranceEligibilityStatusByPatientInsId(Long patientInsId){
        return patientInsuranceVerifSearchRepositoryExtended.findLatestByPatientInsId(patientInsId);
    }
}
