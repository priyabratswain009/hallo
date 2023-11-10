package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.PatientInsVerifStatAuditLog;
import com.sunknowledge.dme.rcm.repository.PatientInsVerifStatAuditLogRepository;
import com.sunknowledge.dme.rcm.service.PatientInsVerifStatAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.PatientInsVerifStatAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.PatientInsVerifStatAuditLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link PatientInsVerifStatAuditLog}.
 */
@Service
@Transactional
public class PatientInsVerifStatAuditLogServiceImpl implements PatientInsVerifStatAuditLogService {

    private final Logger log = LoggerFactory.getLogger(PatientInsVerifStatAuditLogServiceImpl.class);

    private final PatientInsVerifStatAuditLogRepository patientInsVerifStatAuditLogRepository;

    private final PatientInsVerifStatAuditLogMapper patientInsVerifStatAuditLogMapper;

    public PatientInsVerifStatAuditLogServiceImpl(
        PatientInsVerifStatAuditLogRepository patientInsVerifStatAuditLogRepository,
        PatientInsVerifStatAuditLogMapper patientInsVerifStatAuditLogMapper
    ) {
        this.patientInsVerifStatAuditLogRepository = patientInsVerifStatAuditLogRepository;
        this.patientInsVerifStatAuditLogMapper = patientInsVerifStatAuditLogMapper;
    }

    @Override
    public Mono<PatientInsVerifStatAuditLogDTO> save(PatientInsVerifStatAuditLogDTO patientInsVerifStatAuditLogDTO) {
        log.debug("Request to save PatientInsVerifStatAuditLog : {}", patientInsVerifStatAuditLogDTO);
        return patientInsVerifStatAuditLogRepository
            .save(patientInsVerifStatAuditLogMapper.toEntity(patientInsVerifStatAuditLogDTO))
            .map(patientInsVerifStatAuditLogMapper::toDto);
    }

    @Override
    public Mono<PatientInsVerifStatAuditLogDTO> update(PatientInsVerifStatAuditLogDTO patientInsVerifStatAuditLogDTO) {
        log.debug("Request to update PatientInsVerifStatAuditLog : {}", patientInsVerifStatAuditLogDTO);
        return patientInsVerifStatAuditLogRepository
            .save(patientInsVerifStatAuditLogMapper.toEntity(patientInsVerifStatAuditLogDTO))
            .map(patientInsVerifStatAuditLogMapper::toDto);
    }

    @Override
    public Mono<PatientInsVerifStatAuditLogDTO> partialUpdate(PatientInsVerifStatAuditLogDTO patientInsVerifStatAuditLogDTO) {
        log.debug("Request to partially update PatientInsVerifStatAuditLog : {}", patientInsVerifStatAuditLogDTO);

        return patientInsVerifStatAuditLogRepository
            .findById(patientInsVerifStatAuditLogDTO.getId())
            .map(existingPatientInsVerifStatAuditLog -> {
                patientInsVerifStatAuditLogMapper.partialUpdate(existingPatientInsVerifStatAuditLog, patientInsVerifStatAuditLogDTO);

                return existingPatientInsVerifStatAuditLog;
            })
            .flatMap(patientInsVerifStatAuditLogRepository::save)
            .map(patientInsVerifStatAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<PatientInsVerifStatAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PatientInsVerifStatAuditLogs");
        return patientInsVerifStatAuditLogRepository.findAllBy(pageable).map(patientInsVerifStatAuditLogMapper::toDto);
    }

    public Mono<Long> countAll() {
        return patientInsVerifStatAuditLogRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<PatientInsVerifStatAuditLogDTO> findOne(Long id) {
        log.debug("Request to get PatientInsVerifStatAuditLog : {}", id);
        return patientInsVerifStatAuditLogRepository.findById(id).map(patientInsVerifStatAuditLogMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete PatientInsVerifStatAuditLog : {}", id);
        return patientInsVerifStatAuditLogRepository.deleteById(id);
    }
}
