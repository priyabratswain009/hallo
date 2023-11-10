package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.PatientDocumentAuditLog;
import com.sunknowledge.dme.rcm.repository.PatientDocumentAuditLogRepository;
import com.sunknowledge.dme.rcm.service.PatientDocumentAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.PatientDocumentAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.PatientDocumentAuditLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link PatientDocumentAuditLog}.
 */
@Service
@Transactional
public class PatientDocumentAuditLogServiceImpl implements PatientDocumentAuditLogService {

    private final Logger log = LoggerFactory.getLogger(PatientDocumentAuditLogServiceImpl.class);

    private final PatientDocumentAuditLogRepository patientDocumentAuditLogRepository;

    private final PatientDocumentAuditLogMapper patientDocumentAuditLogMapper;

    public PatientDocumentAuditLogServiceImpl(
        PatientDocumentAuditLogRepository patientDocumentAuditLogRepository,
        PatientDocumentAuditLogMapper patientDocumentAuditLogMapper
    ) {
        this.patientDocumentAuditLogRepository = patientDocumentAuditLogRepository;
        this.patientDocumentAuditLogMapper = patientDocumentAuditLogMapper;
    }

    @Override
    public Mono<PatientDocumentAuditLogDTO> save(PatientDocumentAuditLogDTO patientDocumentAuditLogDTO) {
        log.debug("Request to save PatientDocumentAuditLog : {}", patientDocumentAuditLogDTO);
        return patientDocumentAuditLogRepository
            .save(patientDocumentAuditLogMapper.toEntity(patientDocumentAuditLogDTO))
            .map(patientDocumentAuditLogMapper::toDto);
    }

    @Override
    public Mono<PatientDocumentAuditLogDTO> update(PatientDocumentAuditLogDTO patientDocumentAuditLogDTO) {
        log.debug("Request to update PatientDocumentAuditLog : {}", patientDocumentAuditLogDTO);
        return patientDocumentAuditLogRepository
            .save(patientDocumentAuditLogMapper.toEntity(patientDocumentAuditLogDTO))
            .map(patientDocumentAuditLogMapper::toDto);
    }

    @Override
    public Mono<PatientDocumentAuditLogDTO> partialUpdate(PatientDocumentAuditLogDTO patientDocumentAuditLogDTO) {
        log.debug("Request to partially update PatientDocumentAuditLog : {}", patientDocumentAuditLogDTO);

        return patientDocumentAuditLogRepository
            .findById(patientDocumentAuditLogDTO.getId())
            .map(existingPatientDocumentAuditLog -> {
                patientDocumentAuditLogMapper.partialUpdate(existingPatientDocumentAuditLog, patientDocumentAuditLogDTO);

                return existingPatientDocumentAuditLog;
            })
            .flatMap(patientDocumentAuditLogRepository::save)
            .map(patientDocumentAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<PatientDocumentAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PatientDocumentAuditLogs");
        return patientDocumentAuditLogRepository.findAllBy(pageable).map(patientDocumentAuditLogMapper::toDto);
    }

    public Mono<Long> countAll() {
        return patientDocumentAuditLogRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<PatientDocumentAuditLogDTO> findOne(Long id) {
        log.debug("Request to get PatientDocumentAuditLog : {}", id);
        return patientDocumentAuditLogRepository.findById(id).map(patientDocumentAuditLogMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete PatientDocumentAuditLog : {}", id);
        return patientDocumentAuditLogRepository.deleteById(id);
    }
}
