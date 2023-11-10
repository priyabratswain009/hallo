package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.PatientInsurance;
import com.sunknowledge.dme.rcm.repository.PatientInsuranceRepository;
import com.sunknowledge.dme.rcm.service.PatientInsuranceService;
import com.sunknowledge.dme.rcm.service.dto.PatientInsuranceDTO;
import com.sunknowledge.dme.rcm.service.mapper.PatientInsuranceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link PatientInsurance}.
 */
@Service
@Transactional
public class PatientInsuranceServiceImpl implements PatientInsuranceService {

    private final Logger log = LoggerFactory.getLogger(PatientInsuranceServiceImpl.class);

    private final PatientInsuranceRepository patientInsuranceRepository;

    private final PatientInsuranceMapper patientInsuranceMapper;

    public PatientInsuranceServiceImpl(
        PatientInsuranceRepository patientInsuranceRepository,
        PatientInsuranceMapper patientInsuranceMapper
    ) {
        this.patientInsuranceRepository = patientInsuranceRepository;
        this.patientInsuranceMapper = patientInsuranceMapper;
    }

    @Override
    public Mono<PatientInsuranceDTO> save(PatientInsuranceDTO patientInsuranceDTO) {
        log.debug("Request to save PatientInsurance : {}", patientInsuranceDTO);
        return patientInsuranceRepository.save(patientInsuranceMapper.toEntity(patientInsuranceDTO)).map(patientInsuranceMapper::toDto);
    }

    @Override
    public Mono<PatientInsuranceDTO> update(PatientInsuranceDTO patientInsuranceDTO) {
        log.debug("Request to update PatientInsurance : {}", patientInsuranceDTO);
        return patientInsuranceRepository.save(patientInsuranceMapper.toEntity(patientInsuranceDTO)).map(patientInsuranceMapper::toDto);
    }

    @Override
    public Mono<PatientInsuranceDTO> partialUpdate(PatientInsuranceDTO patientInsuranceDTO) {
        log.debug("Request to partially update PatientInsurance : {}", patientInsuranceDTO);

        return patientInsuranceRepository
            .findById(patientInsuranceDTO.getPatientInsuranceId())
            .map(existingPatientInsurance -> {
                patientInsuranceMapper.partialUpdate(existingPatientInsurance, patientInsuranceDTO);

                return existingPatientInsurance;
            })
            .flatMap(patientInsuranceRepository::save)
            .map(patientInsuranceMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<PatientInsuranceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PatientInsurances");
        return patientInsuranceRepository.findAllBy(pageable).map(patientInsuranceMapper::toDto);
    }

    public Mono<Long> countAll() {
        return patientInsuranceRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<PatientInsuranceDTO> findOne(Long id) {
        log.debug("Request to get PatientInsurance : {}", id);
        return patientInsuranceRepository.findById(id).map(patientInsuranceMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete PatientInsurance : {}", id);
        return patientInsuranceRepository.deleteById(id);
    }
}
