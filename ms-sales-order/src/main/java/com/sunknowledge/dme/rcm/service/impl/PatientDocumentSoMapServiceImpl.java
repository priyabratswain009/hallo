package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.PatientDocumentSoMap;
import com.sunknowledge.dme.rcm.repository.PatientDocumentSoMapRepository;
import com.sunknowledge.dme.rcm.service.PatientDocumentSoMapService;
import com.sunknowledge.dme.rcm.service.dto.PatientDocumentSoMapDTO;
import com.sunknowledge.dme.rcm.service.mapper.PatientDocumentSoMapMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link PatientDocumentSoMap}.
 */
@Service
@Transactional
public class PatientDocumentSoMapServiceImpl implements PatientDocumentSoMapService {

    private final Logger log = LoggerFactory.getLogger(PatientDocumentSoMapServiceImpl.class);

    private final PatientDocumentSoMapRepository patientDocumentSoMapRepository;

    private final PatientDocumentSoMapMapper patientDocumentSoMapMapper;

    public PatientDocumentSoMapServiceImpl(
        PatientDocumentSoMapRepository patientDocumentSoMapRepository,
        PatientDocumentSoMapMapper patientDocumentSoMapMapper
    ) {
        this.patientDocumentSoMapRepository = patientDocumentSoMapRepository;
        this.patientDocumentSoMapMapper = patientDocumentSoMapMapper;
    }

    @Override
    public Mono<PatientDocumentSoMapDTO> save(PatientDocumentSoMapDTO patientDocumentSoMapDTO) {
        log.debug("Request to save PatientDocumentSoMap : {}", patientDocumentSoMapDTO);
        return patientDocumentSoMapRepository
            .save(patientDocumentSoMapMapper.toEntity(patientDocumentSoMapDTO))
            .map(patientDocumentSoMapMapper::toDto);
    }

    @Override
    public Mono<PatientDocumentSoMapDTO> update(PatientDocumentSoMapDTO patientDocumentSoMapDTO) {
        log.debug("Request to save PatientDocumentSoMap : {}", patientDocumentSoMapDTO);
        return patientDocumentSoMapRepository
            .save(patientDocumentSoMapMapper.toEntity(patientDocumentSoMapDTO))
            .map(patientDocumentSoMapMapper::toDto);
    }

    @Override
    public Mono<PatientDocumentSoMapDTO> partialUpdate(PatientDocumentSoMapDTO patientDocumentSoMapDTO) {
        log.debug("Request to partially update PatientDocumentSoMap : {}", patientDocumentSoMapDTO);

        return patientDocumentSoMapRepository
            .findById(patientDocumentSoMapDTO.getPatientDocumentSoMapId())
            .map(existingPatientDocumentSoMap -> {
                patientDocumentSoMapMapper.partialUpdate(existingPatientDocumentSoMap, patientDocumentSoMapDTO);

                return existingPatientDocumentSoMap;
            })
            .flatMap(patientDocumentSoMapRepository::save)
            .map(patientDocumentSoMapMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<PatientDocumentSoMapDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PatientDocumentSoMaps");
        return patientDocumentSoMapRepository.findAllBy(pageable).map(patientDocumentSoMapMapper::toDto);
    }

    public Mono<Long> countAll() {
        return patientDocumentSoMapRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<PatientDocumentSoMapDTO> findOne(Long id) {
        log.debug("Request to get PatientDocumentSoMap : {}", id);
        return patientDocumentSoMapRepository.findById(id).map(patientDocumentSoMapMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete PatientDocumentSoMap : {}", id);
        return patientDocumentSoMapRepository.deleteById(id);
    }
}
