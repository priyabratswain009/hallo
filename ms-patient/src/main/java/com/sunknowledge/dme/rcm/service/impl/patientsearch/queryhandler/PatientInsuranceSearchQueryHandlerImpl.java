package com.sunknowledge.dme.rcm.service.impl.patientsearch.queryhandler;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.repository.PatientInsuranceSearchRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.PatientInsuranceDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.PatientInsuranceAuditLogByPatientNoAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientInsuranceByPatientUUIDAndPayerLevel;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientInsuranceSearchByPatientIdAndInsuranceId;
import com.sunknowledge.dme.rcm.service.mapper.PatientInsuranceMapper;
import com.sunknowledge.dme.rcm.service.patientsearch.queryhandler.PatientInsuranceSearchQueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class PatientInsuranceSearchQueryHandlerImpl implements PatientInsuranceSearchQueryHandler {

    private final Logger log = LoggerFactory.getLogger(PatientInsuranceSearchQueryHandlerImpl.class);
    @Autowired
    PatientInsuranceSearchRepositoryExtended patientInsuranceSearchRepositoryExtended;

    private final PatientInsuranceMapper patientInsuranceMapper;

    public PatientInsuranceSearchQueryHandlerImpl(PatientInsuranceMapper patientInsuranceMapper) {
        this.patientInsuranceMapper = patientInsuranceMapper;
    }

    @Override
    public Flux<PatientInsuranceDTO> getPatientInsuranceBySearchParametersQueryHandler(PatientInsuranceSearchByPatientIdAndInsuranceId obj) {
        try {
            return patientInsuranceSearchRepositoryExtended.findAll()
                .map(patientInsuranceMapper::toDto)
                .filter(x ->
                    (
                        x.getPatientId().equals(obj.getPatientID())
                    )
                        && x.getStatus().trim().equalsIgnoreCase("active")
                );
        } catch (Exception e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Flux<PatientInsuranceDTO> getPatientInsuranceByPatientIdQueryHandler(PatientInsuranceAuditLogByPatientNoAndUserIdAndDateQuery obj) {
        try {
            return patientInsuranceSearchRepositoryExtended.findAll()
                .map(patientInsuranceMapper::toDto)
                .filter(x ->
                    (
                        x.getPatientId() == obj.getPatientId()
                    )
                        && x.getStatus().trim().equalsIgnoreCase("active")
                );
        } catch (Exception e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Mono<Long> getIDByUUID(UUID patientUuid) {
        return patientInsuranceSearchRepositoryExtended.getIDByUUID(patientUuid);
    }

    @Override
    public Mono<Object> getPatientInsuranceByPatInsuranceId(Long patInsuranceId) {
        try {
            return patientInsuranceSearchRepositoryExtended.findById(patInsuranceId)
                .map(patientInsuranceMapper::toDto);
        } catch (Exception e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Mono<PatientInsuranceDTO> getPatientInsuranceByPatientUUIDAndPayerLevel(PatientInsuranceByPatientUUIDAndPayerLevel obj) {
        try {
            return patientInsuranceSearchRepositoryExtended.findAll()
                .map(patientInsuranceMapper::toDto)
                .filter(x ->
                    (
                        x.getPatientId() == obj.getPatientId()
                    )
                        && x.getPayerLevel().equalsIgnoreCase(obj.getPayerLevel())
                        && x.getStatus().trim().equalsIgnoreCase("active")
                ).reduce((max, current) -> current.getPatientInsuranceId() > max.getPatientInsuranceId() ? current : max);
        } catch (Exception e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Mono<ServiceOutcome> getExixtingPayerLevelsByPatientUUID(Long patientId) throws ExecutionException, InterruptedException {
        List<String> payerLevelList =  patientInsuranceSearchRepositoryExtended.getExixtingPayerLevelsByPatientUUID(patientId).collectList().toFuture().get();
        if(payerLevelList.size() > 0){
            return Mono.just(new ServiceOutcome<>(payerLevelList, true, "Data Successfully fetched."));
        }else{
            return Mono.just(new ServiceOutcome<>(new ArrayList<>(), false, "No Data Found."));
        }
    }

    @Override
    public Mono<PatientInsuranceDTO> getPatientInsuranceInfoByPatientInsId(Long patientInsId) {
        return patientInsuranceSearchRepositoryExtended.getPatientInsuranceInfoByPatientInsId(patientInsId);
    }

    @Override
    public Mono<Long> getPatientIdByPatientInsuranceUUID(UUID patientInsuranceUUID) {
        return patientInsuranceSearchRepositoryExtended.getPatientIdByPatientInsuranceUUID(patientInsuranceUUID);
    }
}
