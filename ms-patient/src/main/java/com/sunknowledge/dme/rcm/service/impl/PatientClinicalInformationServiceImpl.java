package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.PatientClinicalInformation;
import com.sunknowledge.dme.rcm.repository.PatientClinicalInformationRepository;
import com.sunknowledge.dme.rcm.service.PatientClinicalInformationService;
import com.sunknowledge.dme.rcm.service.dto.PatientClinicalInformationDTO;
import com.sunknowledge.dme.rcm.service.mapper.PatientClinicalInformationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link PatientClinicalInformation}.
 */
@Service
@Transactional
public class PatientClinicalInformationServiceImpl implements PatientClinicalInformationService {

    private final Logger log = LoggerFactory.getLogger(PatientClinicalInformationServiceImpl.class);

    private final PatientClinicalInformationRepository patientClinicalInformationRepository;

    private final PatientClinicalInformationMapper patientClinicalInformationMapper;

    public PatientClinicalInformationServiceImpl(
        PatientClinicalInformationRepository patientClinicalInformationRepository,
        PatientClinicalInformationMapper patientClinicalInformationMapper
    ) {
        this.patientClinicalInformationRepository = patientClinicalInformationRepository;
        this.patientClinicalInformationMapper = patientClinicalInformationMapper;
    }

    @Override
    public Mono<PatientClinicalInformationDTO> save(PatientClinicalInformationDTO patientClinicalInformationDTO) {
        log.debug("Request to save PatientClinicalInformation : {}", patientClinicalInformationDTO);
        return patientClinicalInformationRepository
            .save(patientClinicalInformationMapper.toEntity(patientClinicalInformationDTO))
            .map(patientClinicalInformationMapper::toDto);
    }

    @Override
    public Mono<PatientClinicalInformationDTO> update(PatientClinicalInformationDTO patientClinicalInformationDTO) {
        log.debug("Request to update PatientClinicalInformation : {}", patientClinicalInformationDTO);
        return patientClinicalInformationRepository
            .save(patientClinicalInformationMapper.toEntity(patientClinicalInformationDTO))
            .map(patientClinicalInformationMapper::toDto);
    }

    @Override
    public Mono<PatientClinicalInformationDTO> partialUpdate(PatientClinicalInformationDTO patientClinicalInformationDTO) {
        log.debug("Request to partially update PatientClinicalInformation : {}", patientClinicalInformationDTO);

        return patientClinicalInformationRepository
            .findById(patientClinicalInformationDTO.getPatientClinicalInformationId())
            .map(existingPatientClinicalInformation -> {
                patientClinicalInformationMapper.partialUpdate(existingPatientClinicalInformation, patientClinicalInformationDTO);

                return existingPatientClinicalInformation;
            })
            .flatMap(patientClinicalInformationRepository::save)
            .map(patientClinicalInformationMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<PatientClinicalInformationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PatientClinicalInformations");
        return patientClinicalInformationRepository.findAllBy(pageable).map(patientClinicalInformationMapper::toDto);
    }

    public Mono<Long> countAll() {
        return patientClinicalInformationRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<PatientClinicalInformationDTO> findOne(Long id) {
        log.debug("Request to get PatientClinicalInformation : {}", id);
        return patientClinicalInformationRepository.findById(id).map(patientClinicalInformationMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete PatientClinicalInformation : {}", id);
        return patientClinicalInformationRepository.deleteById(id);
    }
}
