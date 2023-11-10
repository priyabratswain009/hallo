package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.PatientDiagnosisAuditLog;
import com.sunknowledge.dme.rcm.repository.PatientDiagnosisAuditLogRepository;
import com.sunknowledge.dme.rcm.service.PatientDiagnosisAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.PatientDiagnosisAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.PatientDiagnosisAuditLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link PatientDiagnosisAuditLog}.
 */
@Service
@Transactional
public class PatientDiagnosisAuditLogServiceImpl implements PatientDiagnosisAuditLogService {

    private final Logger log = LoggerFactory.getLogger(PatientDiagnosisAuditLogServiceImpl.class);

    private final PatientDiagnosisAuditLogRepository patientDiagnosisAuditLogRepository;

    private final PatientDiagnosisAuditLogMapper patientDiagnosisAuditLogMapper;

    public PatientDiagnosisAuditLogServiceImpl(
        PatientDiagnosisAuditLogRepository patientDiagnosisAuditLogRepository,
        PatientDiagnosisAuditLogMapper patientDiagnosisAuditLogMapper
    ) {
        this.patientDiagnosisAuditLogRepository = patientDiagnosisAuditLogRepository;
        this.patientDiagnosisAuditLogMapper = patientDiagnosisAuditLogMapper;
    }

    @Override
    public Mono<PatientDiagnosisAuditLogDTO> save(PatientDiagnosisAuditLogDTO patientDiagnosisAuditLogDTO) {
        log.debug("Request to save PatientDiagnosisAuditLog : {}", patientDiagnosisAuditLogDTO);
        return patientDiagnosisAuditLogRepository
            .save(patientDiagnosisAuditLogMapper.toEntity(patientDiagnosisAuditLogDTO))
            .map(patientDiagnosisAuditLogMapper::toDto);
    }

    @Override
    public Mono<PatientDiagnosisAuditLogDTO> update(PatientDiagnosisAuditLogDTO patientDiagnosisAuditLogDTO) {
        log.debug("Request to update PatientDiagnosisAuditLog : {}", patientDiagnosisAuditLogDTO);
        return patientDiagnosisAuditLogRepository
            .save(patientDiagnosisAuditLogMapper.toEntity(patientDiagnosisAuditLogDTO))
            .map(patientDiagnosisAuditLogMapper::toDto);
    }

    @Override
    public Mono<PatientDiagnosisAuditLogDTO> partialUpdate(PatientDiagnosisAuditLogDTO patientDiagnosisAuditLogDTO) {
        log.debug("Request to partially update PatientDiagnosisAuditLog : {}", patientDiagnosisAuditLogDTO);

        return patientDiagnosisAuditLogRepository
            .findById(patientDiagnosisAuditLogDTO.getId())
            .map(existingPatientDiagnosisAuditLog -> {
                patientDiagnosisAuditLogMapper.partialUpdate(existingPatientDiagnosisAuditLog, patientDiagnosisAuditLogDTO);

                return existingPatientDiagnosisAuditLog;
            })
            .flatMap(patientDiagnosisAuditLogRepository::save)
            .map(patientDiagnosisAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<PatientDiagnosisAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PatientDiagnosisAuditLogs");
        return patientDiagnosisAuditLogRepository.findAllBy(pageable).map(patientDiagnosisAuditLogMapper::toDto);
    }

    public Mono<Long> countAll() {
        return patientDiagnosisAuditLogRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<PatientDiagnosisAuditLogDTO> findOne(Long id) {
        log.debug("Request to get PatientDiagnosisAuditLog : {}", id);
        return patientDiagnosisAuditLogRepository.findById(id).map(patientDiagnosisAuditLogMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete PatientDiagnosisAuditLog : {}", id);
        return patientDiagnosisAuditLogRepository.deleteById(id);
    }
}
