package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.PatientMaster;
import com.sunknowledge.dme.rcm.repository.PatientMasterRepository;
import com.sunknowledge.dme.rcm.service.PatientMasterService;
import com.sunknowledge.dme.rcm.service.dto.PatientMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.PatientMasterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link PatientMaster}.
 */
@Service
@Transactional
public class PatientMasterServiceImpl implements PatientMasterService {

    private final Logger log = LoggerFactory.getLogger(PatientMasterServiceImpl.class);

    private final PatientMasterRepository patientMasterRepository;

    private final PatientMasterMapper patientMasterMapper;

    public PatientMasterServiceImpl(PatientMasterRepository patientMasterRepository, PatientMasterMapper patientMasterMapper) {
        this.patientMasterRepository = patientMasterRepository;
        this.patientMasterMapper = patientMasterMapper;
    }

    @Override
    public Mono<PatientMasterDTO> save(PatientMasterDTO patientMasterDTO) {
        log.debug("Request to save PatientMaster : {}", patientMasterDTO);
        return patientMasterRepository.save(patientMasterMapper.toEntity(patientMasterDTO)).map(patientMasterMapper::toDto);
    }

    @Override
    public Mono<PatientMasterDTO> update(PatientMasterDTO patientMasterDTO) {
        log.debug("Request to update PatientMaster : {}", patientMasterDTO);
        return patientMasterRepository.save(patientMasterMapper.toEntity(patientMasterDTO)).map(patientMasterMapper::toDto);
    }

    @Override
    public Mono<PatientMasterDTO> partialUpdate(PatientMasterDTO patientMasterDTO) {
        log.debug("Request to partially update PatientMaster : {}", patientMasterDTO);

        return patientMasterRepository
            .findById(patientMasterDTO.getPatientId())
            .map(existingPatientMaster -> {
                patientMasterMapper.partialUpdate(existingPatientMaster, patientMasterDTO);

                return existingPatientMaster;
            })
            .flatMap(patientMasterRepository::save)
            .map(patientMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<PatientMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PatientMasters");
        return patientMasterRepository.findAllBy(pageable).map(patientMasterMapper::toDto);
    }

    public Mono<Long> countAll() {
        return patientMasterRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<PatientMasterDTO> findOne(Long id) {
        log.debug("Request to get PatientMaster : {}", id);
        return patientMasterRepository.findById(id).map(patientMasterMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete PatientMaster : {}", id);
        return patientMasterRepository.deleteById(id);
    }
}
