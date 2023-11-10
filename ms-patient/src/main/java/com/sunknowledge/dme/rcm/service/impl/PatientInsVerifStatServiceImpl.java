package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.PatientInsVerifStat;
import com.sunknowledge.dme.rcm.repository.PatientInsVerifStatRepository;
import com.sunknowledge.dme.rcm.service.PatientInsVerifStatService;
import com.sunknowledge.dme.rcm.service.dto.PatientInsVerifStatDTO;
import com.sunknowledge.dme.rcm.service.mapper.PatientInsVerifStatMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link PatientInsVerifStat}.
 */
@Service
@Transactional
public class PatientInsVerifStatServiceImpl implements PatientInsVerifStatService {

    private final Logger log = LoggerFactory.getLogger(PatientInsVerifStatServiceImpl.class);

    private final PatientInsVerifStatRepository patientInsVerifStatRepository;

    private final PatientInsVerifStatMapper patientInsVerifStatMapper;

    public PatientInsVerifStatServiceImpl(
        PatientInsVerifStatRepository patientInsVerifStatRepository,
        PatientInsVerifStatMapper patientInsVerifStatMapper
    ) {
        this.patientInsVerifStatRepository = patientInsVerifStatRepository;
        this.patientInsVerifStatMapper = patientInsVerifStatMapper;
    }

    @Override
    public Mono<PatientInsVerifStatDTO> save(PatientInsVerifStatDTO patientInsVerifStatDTO) {
        log.debug("Request to save PatientInsVerifStat : {}", patientInsVerifStatDTO);
        return patientInsVerifStatRepository
            .save(patientInsVerifStatMapper.toEntity(patientInsVerifStatDTO))
            .map(patientInsVerifStatMapper::toDto);
    }

    @Override
    public Mono<PatientInsVerifStatDTO> update(PatientInsVerifStatDTO patientInsVerifStatDTO) {
        log.debug("Request to update PatientInsVerifStat : {}", patientInsVerifStatDTO);
        return patientInsVerifStatRepository
            .save(patientInsVerifStatMapper.toEntity(patientInsVerifStatDTO))
            .map(patientInsVerifStatMapper::toDto);
    }

    @Override
    public Mono<PatientInsVerifStatDTO> partialUpdate(PatientInsVerifStatDTO patientInsVerifStatDTO) {
        log.debug("Request to partially update PatientInsVerifStat : {}", patientInsVerifStatDTO);

        return patientInsVerifStatRepository
            .findById(patientInsVerifStatDTO.getInsuranceVerifId())
            .map(existingPatientInsVerifStat -> {
                patientInsVerifStatMapper.partialUpdate(existingPatientInsVerifStat, patientInsVerifStatDTO);

                return existingPatientInsVerifStat;
            })
            .flatMap(patientInsVerifStatRepository::save)
            .map(patientInsVerifStatMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<PatientInsVerifStatDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PatientInsVerifStats");
        return patientInsVerifStatRepository.findAllBy(pageable).map(patientInsVerifStatMapper::toDto);
    }

    public Mono<Long> countAll() {
        return patientInsVerifStatRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<PatientInsVerifStatDTO> findOne(Long id) {
        log.debug("Request to get PatientInsVerifStat : {}", id);
        return patientInsVerifStatRepository.findById(id).map(patientInsVerifStatMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete PatientInsVerifStat : {}", id);
        return patientInsVerifStatRepository.deleteById(id);
    }
}
