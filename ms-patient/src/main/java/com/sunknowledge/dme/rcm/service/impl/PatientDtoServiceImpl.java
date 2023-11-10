package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.PatientDto;
import com.sunknowledge.dme.rcm.repository.PatientDtoRepository;
import com.sunknowledge.dme.rcm.service.PatientDtoService;
import com.sunknowledge.dme.rcm.service.dto.PatientDtoDTO;
import com.sunknowledge.dme.rcm.service.mapper.PatientDtoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link PatientDto}.
 */
@Service
@Transactional
public class PatientDtoServiceImpl implements PatientDtoService {

    private final Logger log = LoggerFactory.getLogger(PatientDtoServiceImpl.class);

    private final PatientDtoRepository patientDtoRepository;

    private final PatientDtoMapper patientDtoMapper;

    public PatientDtoServiceImpl(PatientDtoRepository patientDtoRepository, PatientDtoMapper patientDtoMapper) {
        this.patientDtoRepository = patientDtoRepository;
        this.patientDtoMapper = patientDtoMapper;
    }

    @Override
    public Mono<PatientDtoDTO> save(PatientDtoDTO patientDtoDTO) {
        log.debug("Request to save PatientDto : {}", patientDtoDTO);
        return patientDtoRepository.save(patientDtoMapper.toEntity(patientDtoDTO)).map(patientDtoMapper::toDto);
    }

    @Override
    public Mono<PatientDtoDTO> update(PatientDtoDTO patientDtoDTO) {
        log.debug("Request to update PatientDto : {}", patientDtoDTO);
        return patientDtoRepository.save(patientDtoMapper.toEntity(patientDtoDTO)).map(patientDtoMapper::toDto);
    }

    @Override
    public Mono<PatientDtoDTO> partialUpdate(PatientDtoDTO patientDtoDTO) {
        log.debug("Request to partially update PatientDto : {}", patientDtoDTO);

        return patientDtoRepository
            .findById(patientDtoDTO.getPatientDtoId())
            .map(existingPatientDto -> {
                patientDtoMapper.partialUpdate(existingPatientDto, patientDtoDTO);

                return existingPatientDto;
            })
            .flatMap(patientDtoRepository::save)
            .map(patientDtoMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<PatientDtoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PatientDtos");
        return patientDtoRepository.findAllBy(pageable).map(patientDtoMapper::toDto);
    }

    public Mono<Long> countAll() {
        return patientDtoRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<PatientDtoDTO> findOne(Long id) {
        log.debug("Request to get PatientDto : {}", id);
        return patientDtoRepository.findById(id).map(patientDtoMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete PatientDto : {}", id);
        return patientDtoRepository.deleteById(id);
    }
}
