package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.PatientDoctorMap;
import com.sunknowledge.dme.rcm.repository.PatientDoctorMapRepository;
import com.sunknowledge.dme.rcm.service.PatientDoctorMapService;
import com.sunknowledge.dme.rcm.service.dto.PatientDoctorMapDTO;
import com.sunknowledge.dme.rcm.service.mapper.PatientDoctorMapMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link PatientDoctorMap}.
 */
@Service
@Transactional
public class PatientDoctorMapServiceImpl implements PatientDoctorMapService {

    private final Logger log = LoggerFactory.getLogger(PatientDoctorMapServiceImpl.class);

    private final PatientDoctorMapRepository patientDoctorMapRepository;

    private final PatientDoctorMapMapper patientDoctorMapMapper;

    public PatientDoctorMapServiceImpl(
        PatientDoctorMapRepository patientDoctorMapRepository,
        PatientDoctorMapMapper patientDoctorMapMapper
    ) {
        this.patientDoctorMapRepository = patientDoctorMapRepository;
        this.patientDoctorMapMapper = patientDoctorMapMapper;
    }

    @Override
    public Mono<PatientDoctorMapDTO> save(PatientDoctorMapDTO patientDoctorMapDTO) {
        log.debug("Request to save PatientDoctorMap : {}", patientDoctorMapDTO);
        return patientDoctorMapRepository.save(patientDoctorMapMapper.toEntity(patientDoctorMapDTO)).map(patientDoctorMapMapper::toDto);
    }

    @Override
    public Mono<PatientDoctorMapDTO> update(PatientDoctorMapDTO patientDoctorMapDTO) {
        log.debug("Request to update PatientDoctorMap : {}", patientDoctorMapDTO);
        return patientDoctorMapRepository.save(patientDoctorMapMapper.toEntity(patientDoctorMapDTO)).map(patientDoctorMapMapper::toDto);
    }

    @Override
    public Mono<PatientDoctorMapDTO> partialUpdate(PatientDoctorMapDTO patientDoctorMapDTO) {
        log.debug("Request to partially update PatientDoctorMap : {}", patientDoctorMapDTO);

        return patientDoctorMapRepository
            .findById(patientDoctorMapDTO.getPatientDoctorMapId())
            .map(existingPatientDoctorMap -> {
                patientDoctorMapMapper.partialUpdate(existingPatientDoctorMap, patientDoctorMapDTO);

                return existingPatientDoctorMap;
            })
            .flatMap(patientDoctorMapRepository::save)
            .map(patientDoctorMapMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<PatientDoctorMapDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PatientDoctorMaps");
        return patientDoctorMapRepository.findAllBy(pageable).map(patientDoctorMapMapper::toDto);
    }

    public Mono<Long> countAll() {
        return patientDoctorMapRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<PatientDoctorMapDTO> findOne(Long id) {
        log.debug("Request to get PatientDoctorMap : {}", id);
        return patientDoctorMapRepository.findById(id).map(patientDoctorMapMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete PatientDoctorMap : {}", id);
        return patientDoctorMapRepository.deleteById(id);
    }
}
