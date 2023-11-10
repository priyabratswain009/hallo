package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.PatientClinicalInformationAuditLog;
import com.sunknowledge.dme.rcm.repository.PatientClinicalInformationAuditLogRepository;
import com.sunknowledge.dme.rcm.service.PatientClinicalInformationAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.PatientClinicalInformationAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.PatientClinicalInformationAuditLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link PatientClinicalInformationAuditLog}.
 */
@Service
@Transactional
public class PatientClinicalInformationAuditLogServiceImpl implements PatientClinicalInformationAuditLogService {

    private final Logger log = LoggerFactory.getLogger(PatientClinicalInformationAuditLogServiceImpl.class);

    private final PatientClinicalInformationAuditLogRepository patientClinicalInformationAuditLogRepository;

    private final PatientClinicalInformationAuditLogMapper patientClinicalInformationAuditLogMapper;

    public PatientClinicalInformationAuditLogServiceImpl(
        PatientClinicalInformationAuditLogRepository patientClinicalInformationAuditLogRepository,
        PatientClinicalInformationAuditLogMapper patientClinicalInformationAuditLogMapper
    ) {
        this.patientClinicalInformationAuditLogRepository = patientClinicalInformationAuditLogRepository;
        this.patientClinicalInformationAuditLogMapper = patientClinicalInformationAuditLogMapper;
    }

    @Override
    public Mono<PatientClinicalInformationAuditLogDTO> save(PatientClinicalInformationAuditLogDTO patientClinicalInformationAuditLogDTO) {
        log.debug("Request to save PatientClinicalInformationAuditLog : {}", patientClinicalInformationAuditLogDTO);
        return patientClinicalInformationAuditLogRepository
            .save(patientClinicalInformationAuditLogMapper.toEntity(patientClinicalInformationAuditLogDTO))
            .map(patientClinicalInformationAuditLogMapper::toDto);
    }

    @Override
    public Mono<PatientClinicalInformationAuditLogDTO> update(PatientClinicalInformationAuditLogDTO patientClinicalInformationAuditLogDTO) {
        log.debug("Request to update PatientClinicalInformationAuditLog : {}", patientClinicalInformationAuditLogDTO);
        return patientClinicalInformationAuditLogRepository
            .save(patientClinicalInformationAuditLogMapper.toEntity(patientClinicalInformationAuditLogDTO))
            .map(patientClinicalInformationAuditLogMapper::toDto);
    }

    @Override
    public Mono<PatientClinicalInformationAuditLogDTO> partialUpdate(
        PatientClinicalInformationAuditLogDTO patientClinicalInformationAuditLogDTO
    ) {
        log.debug("Request to partially update PatientClinicalInformationAuditLog : {}", patientClinicalInformationAuditLogDTO);

        return patientClinicalInformationAuditLogRepository
            .findById(patientClinicalInformationAuditLogDTO.getId())
            .map(existingPatientClinicalInformationAuditLog -> {
                patientClinicalInformationAuditLogMapper.partialUpdate(
                    existingPatientClinicalInformationAuditLog,
                    patientClinicalInformationAuditLogDTO
                );

                return existingPatientClinicalInformationAuditLog;
            })
            .flatMap(patientClinicalInformationAuditLogRepository::save)
            .map(patientClinicalInformationAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<PatientClinicalInformationAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PatientClinicalInformationAuditLogs");
        return patientClinicalInformationAuditLogRepository.findAllBy(pageable).map(patientClinicalInformationAuditLogMapper::toDto);
    }

    public Mono<Long> countAll() {
        return patientClinicalInformationAuditLogRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<PatientClinicalInformationAuditLogDTO> findOne(Long id) {
        log.debug("Request to get PatientClinicalInformationAuditLog : {}", id);
        return patientClinicalInformationAuditLogRepository.findById(id).map(patientClinicalInformationAuditLogMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete PatientClinicalInformationAuditLog : {}", id);
        return patientClinicalInformationAuditLogRepository.deleteById(id);
    }
}
