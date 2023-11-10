package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.PatientInsuranceAuditLog;
import com.sunknowledge.dme.rcm.repository.PatientInsuranceAuditLogRepository;
import com.sunknowledge.dme.rcm.service.PatientInsuranceAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.PatientInsuranceAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.PatientInsuranceAuditLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link PatientInsuranceAuditLog}.
 */
@Service
@Transactional
public class PatientInsuranceAuditLogServiceImpl implements PatientInsuranceAuditLogService {

    private final Logger log = LoggerFactory.getLogger(PatientInsuranceAuditLogServiceImpl.class);

    private final PatientInsuranceAuditLogRepository patientInsuranceAuditLogRepository;

    private final PatientInsuranceAuditLogMapper patientInsuranceAuditLogMapper;

    public PatientInsuranceAuditLogServiceImpl(
        PatientInsuranceAuditLogRepository patientInsuranceAuditLogRepository,
        PatientInsuranceAuditLogMapper patientInsuranceAuditLogMapper
    ) {
        this.patientInsuranceAuditLogRepository = patientInsuranceAuditLogRepository;
        this.patientInsuranceAuditLogMapper = patientInsuranceAuditLogMapper;
    }

    @Override
    public Mono<PatientInsuranceAuditLogDTO> save(PatientInsuranceAuditLogDTO patientInsuranceAuditLogDTO) {
        log.debug("Request to save PatientInsuranceAuditLog : {}", patientInsuranceAuditLogDTO);
        return patientInsuranceAuditLogRepository
            .save(patientInsuranceAuditLogMapper.toEntity(patientInsuranceAuditLogDTO))
            .map(patientInsuranceAuditLogMapper::toDto);
    }

    @Override
    public Mono<PatientInsuranceAuditLogDTO> update(PatientInsuranceAuditLogDTO patientInsuranceAuditLogDTO) {
        log.debug("Request to update PatientInsuranceAuditLog : {}", patientInsuranceAuditLogDTO);
        return patientInsuranceAuditLogRepository
            .save(patientInsuranceAuditLogMapper.toEntity(patientInsuranceAuditLogDTO))
            .map(patientInsuranceAuditLogMapper::toDto);
    }

    @Override
    public Mono<PatientInsuranceAuditLogDTO> partialUpdate(PatientInsuranceAuditLogDTO patientInsuranceAuditLogDTO) {
        log.debug("Request to partially update PatientInsuranceAuditLog : {}", patientInsuranceAuditLogDTO);

        return patientInsuranceAuditLogRepository
            .findById(patientInsuranceAuditLogDTO.getId())
            .map(existingPatientInsuranceAuditLog -> {
                patientInsuranceAuditLogMapper.partialUpdate(existingPatientInsuranceAuditLog, patientInsuranceAuditLogDTO);

                return existingPatientInsuranceAuditLog;
            })
            .flatMap(patientInsuranceAuditLogRepository::save)
            .map(patientInsuranceAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<PatientInsuranceAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PatientInsuranceAuditLogs");
        return patientInsuranceAuditLogRepository.findAllBy(pageable).map(patientInsuranceAuditLogMapper::toDto);
    }

    public Mono<Long> countAll() {
        return patientInsuranceAuditLogRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<PatientInsuranceAuditLogDTO> findOne(Long id) {
        log.debug("Request to get PatientInsuranceAuditLog : {}", id);
        return patientInsuranceAuditLogRepository.findById(id).map(patientInsuranceAuditLogMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete PatientInsuranceAuditLog : {}", id);
        return patientInsuranceAuditLogRepository.deleteById(id);
    }
}
