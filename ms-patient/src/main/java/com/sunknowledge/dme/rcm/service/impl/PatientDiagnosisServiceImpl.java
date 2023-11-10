package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.PatientDiagnosis;
import com.sunknowledge.dme.rcm.repository.PatientDiagnosisRepository;
import com.sunknowledge.dme.rcm.service.PatientDiagnosisService;
import com.sunknowledge.dme.rcm.service.dto.PatientDiagnosisDTO;
import com.sunknowledge.dme.rcm.service.mapper.PatientDiagnosisMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link PatientDiagnosis}.
 */
@Service
@Transactional
public class PatientDiagnosisServiceImpl implements PatientDiagnosisService {

    private final Logger log = LoggerFactory.getLogger(PatientDiagnosisServiceImpl.class);

    private final PatientDiagnosisRepository patientDiagnosisRepository;

    private final PatientDiagnosisMapper patientDiagnosisMapper;

    public PatientDiagnosisServiceImpl(
        PatientDiagnosisRepository patientDiagnosisRepository,
        PatientDiagnosisMapper patientDiagnosisMapper
    ) {
        this.patientDiagnosisRepository = patientDiagnosisRepository;
        this.patientDiagnosisMapper = patientDiagnosisMapper;
    }

    @Override
    public Mono<PatientDiagnosisDTO> save(PatientDiagnosisDTO patientDiagnosisDTO) {
        log.debug("Request to save PatientDiagnosis : {}", patientDiagnosisDTO);
        return patientDiagnosisRepository.save(patientDiagnosisMapper.toEntity(patientDiagnosisDTO)).map(patientDiagnosisMapper::toDto);
    }

    @Override
    public Mono<PatientDiagnosisDTO> update(PatientDiagnosisDTO patientDiagnosisDTO) {
        log.debug("Request to update PatientDiagnosis : {}", patientDiagnosisDTO);
        return patientDiagnosisRepository.save(patientDiagnosisMapper.toEntity(patientDiagnosisDTO)).map(patientDiagnosisMapper::toDto);
    }

    @Override
    public Mono<PatientDiagnosisDTO> partialUpdate(PatientDiagnosisDTO patientDiagnosisDTO) {
        log.debug("Request to partially update PatientDiagnosis : {}", patientDiagnosisDTO);

        return patientDiagnosisRepository
            .findById(patientDiagnosisDTO.getPatientDiagnosisId())
            .map(existingPatientDiagnosis -> {
                patientDiagnosisMapper.partialUpdate(existingPatientDiagnosis, patientDiagnosisDTO);

                return existingPatientDiagnosis;
            })
            .flatMap(patientDiagnosisRepository::save)
            .map(patientDiagnosisMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<PatientDiagnosisDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PatientDiagnoses");
        return patientDiagnosisRepository.findAllBy(pageable).map(patientDiagnosisMapper::toDto);
    }

    public Mono<Long> countAll() {
        return patientDiagnosisRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<PatientDiagnosisDTO> findOne(Long id) {
        log.debug("Request to get PatientDiagnosis : {}", id);
        return patientDiagnosisRepository.findById(id).map(patientDiagnosisMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete PatientDiagnosis : {}", id);
        return patientDiagnosisRepository.deleteById(id);
    }
}
