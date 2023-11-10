package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.PatientMasterAuditLog;
import com.sunknowledge.dme.rcm.repository.PatientMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.PatientMasterAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.PatientMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.PatientMasterAuditLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link PatientMasterAuditLog}.
 */
@Service
@Transactional
public class PatientMasterAuditLogServiceImpl implements PatientMasterAuditLogService {

    private final Logger log = LoggerFactory.getLogger(PatientMasterAuditLogServiceImpl.class);

    private final PatientMasterAuditLogRepository patientMasterAuditLogRepository;

    private final PatientMasterAuditLogMapper patientMasterAuditLogMapper;

    public PatientMasterAuditLogServiceImpl(
        PatientMasterAuditLogRepository patientMasterAuditLogRepository,
        PatientMasterAuditLogMapper patientMasterAuditLogMapper
    ) {
        this.patientMasterAuditLogRepository = patientMasterAuditLogRepository;
        this.patientMasterAuditLogMapper = patientMasterAuditLogMapper;
    }

    @Override
    public Mono<PatientMasterAuditLogDTO> save(PatientMasterAuditLogDTO patientMasterAuditLogDTO) {
        log.debug("Request to save PatientMasterAuditLog : {}", patientMasterAuditLogDTO);
        return patientMasterAuditLogRepository
            .save(patientMasterAuditLogMapper.toEntity(patientMasterAuditLogDTO))
            .map(patientMasterAuditLogMapper::toDto);
    }

    @Override
    public Mono<PatientMasterAuditLogDTO> update(PatientMasterAuditLogDTO patientMasterAuditLogDTO) {
        log.debug("Request to update PatientMasterAuditLog : {}", patientMasterAuditLogDTO);
        return patientMasterAuditLogRepository
            .save(patientMasterAuditLogMapper.toEntity(patientMasterAuditLogDTO))
            .map(patientMasterAuditLogMapper::toDto);
    }

    @Override
    public Mono<PatientMasterAuditLogDTO> partialUpdate(PatientMasterAuditLogDTO patientMasterAuditLogDTO) {
        log.debug("Request to partially update PatientMasterAuditLog : {}", patientMasterAuditLogDTO);

        return patientMasterAuditLogRepository
            .findById(patientMasterAuditLogDTO.getId())
            .map(existingPatientMasterAuditLog -> {
                patientMasterAuditLogMapper.partialUpdate(existingPatientMasterAuditLog, patientMasterAuditLogDTO);

                return existingPatientMasterAuditLog;
            })
            .flatMap(patientMasterAuditLogRepository::save)
            .map(patientMasterAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<PatientMasterAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PatientMasterAuditLogs");
        return patientMasterAuditLogRepository.findAllBy(pageable).map(patientMasterAuditLogMapper::toDto);
    }

    public Mono<Long> countAll() {
        return patientMasterAuditLogRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<PatientMasterAuditLogDTO> findOne(Long id) {
        log.debug("Request to get PatientMasterAuditLog : {}", id);
        return patientMasterAuditLogRepository.findById(id).map(patientMasterAuditLogMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete PatientMasterAuditLog : {}", id);
        return patientMasterAuditLogRepository.deleteById(id);
    }
}
