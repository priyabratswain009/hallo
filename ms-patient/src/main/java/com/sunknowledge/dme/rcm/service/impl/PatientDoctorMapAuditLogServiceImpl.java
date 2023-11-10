package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.PatientDoctorMapAuditLog;
import com.sunknowledge.dme.rcm.repository.PatientDoctorMapAuditLogRepository;
import com.sunknowledge.dme.rcm.service.PatientDoctorMapAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.PatientDoctorMapAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.PatientDoctorMapAuditLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link PatientDoctorMapAuditLog}.
 */
@Service
@Transactional
public class PatientDoctorMapAuditLogServiceImpl implements PatientDoctorMapAuditLogService {

    private final Logger log = LoggerFactory.getLogger(PatientDoctorMapAuditLogServiceImpl.class);

    private final PatientDoctorMapAuditLogRepository patientDoctorMapAuditLogRepository;

    private final PatientDoctorMapAuditLogMapper patientDoctorMapAuditLogMapper;

    public PatientDoctorMapAuditLogServiceImpl(
        PatientDoctorMapAuditLogRepository patientDoctorMapAuditLogRepository,
        PatientDoctorMapAuditLogMapper patientDoctorMapAuditLogMapper
    ) {
        this.patientDoctorMapAuditLogRepository = patientDoctorMapAuditLogRepository;
        this.patientDoctorMapAuditLogMapper = patientDoctorMapAuditLogMapper;
    }

    @Override
    public Mono<PatientDoctorMapAuditLogDTO> save(PatientDoctorMapAuditLogDTO patientDoctorMapAuditLogDTO) {
        log.debug("Request to save PatientDoctorMapAuditLog : {}", patientDoctorMapAuditLogDTO);
        return patientDoctorMapAuditLogRepository
            .save(patientDoctorMapAuditLogMapper.toEntity(patientDoctorMapAuditLogDTO))
            .map(patientDoctorMapAuditLogMapper::toDto);
    }

    @Override
    public Mono<PatientDoctorMapAuditLogDTO> update(PatientDoctorMapAuditLogDTO patientDoctorMapAuditLogDTO) {
        log.debug("Request to update PatientDoctorMapAuditLog : {}", patientDoctorMapAuditLogDTO);
        return patientDoctorMapAuditLogRepository
            .save(patientDoctorMapAuditLogMapper.toEntity(patientDoctorMapAuditLogDTO))
            .map(patientDoctorMapAuditLogMapper::toDto);
    }

    @Override
    public Mono<PatientDoctorMapAuditLogDTO> partialUpdate(PatientDoctorMapAuditLogDTO patientDoctorMapAuditLogDTO) {
        log.debug("Request to partially update PatientDoctorMapAuditLog : {}", patientDoctorMapAuditLogDTO);

        return patientDoctorMapAuditLogRepository
            .findById(patientDoctorMapAuditLogDTO.getId())
            .map(existingPatientDoctorMapAuditLog -> {
                patientDoctorMapAuditLogMapper.partialUpdate(existingPatientDoctorMapAuditLog, patientDoctorMapAuditLogDTO);

                return existingPatientDoctorMapAuditLog;
            })
            .flatMap(patientDoctorMapAuditLogRepository::save)
            .map(patientDoctorMapAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<PatientDoctorMapAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PatientDoctorMapAuditLogs");
        return patientDoctorMapAuditLogRepository.findAllBy(pageable).map(patientDoctorMapAuditLogMapper::toDto);
    }

    public Mono<Long> countAll() {
        return patientDoctorMapAuditLogRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<PatientDoctorMapAuditLogDTO> findOne(Long id) {
        log.debug("Request to get PatientDoctorMapAuditLog : {}", id);
        return patientDoctorMapAuditLogRepository.findById(id).map(patientDoctorMapAuditLogMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete PatientDoctorMapAuditLog : {}", id);
        return patientDoctorMapAuditLogRepository.deleteById(id);
    }
}
