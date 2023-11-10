package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.WorkersCompensation;
import com.sunknowledge.dme.rcm.repository.WorkersCompensationRepository;
import com.sunknowledge.dme.rcm.service.WorkersCompensationService;
import com.sunknowledge.dme.rcm.service.dto.WorkersCompensationDTO;
import com.sunknowledge.dme.rcm.service.mapper.WorkersCompensationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link WorkersCompensation}.
 */
@Service
@Transactional
public class WorkersCompensationServiceImpl implements WorkersCompensationService {

    private final Logger log = LoggerFactory.getLogger(WorkersCompensationServiceImpl.class);

    private final WorkersCompensationRepository workersCompensationRepository;

    private final WorkersCompensationMapper workersCompensationMapper;

    public WorkersCompensationServiceImpl(
        WorkersCompensationRepository workersCompensationRepository,
        WorkersCompensationMapper workersCompensationMapper
    ) {
        this.workersCompensationRepository = workersCompensationRepository;
        this.workersCompensationMapper = workersCompensationMapper;
    }

    @Override
    public Mono<WorkersCompensationDTO> save(WorkersCompensationDTO workersCompensationDTO) {
        log.debug("Request to save WorkersCompensation : {}", workersCompensationDTO);
        return workersCompensationRepository
            .save(workersCompensationMapper.toEntity(workersCompensationDTO))
            .map(workersCompensationMapper::toDto);
    }

    @Override
    public Mono<WorkersCompensationDTO> update(WorkersCompensationDTO workersCompensationDTO) {
        log.debug("Request to update WorkersCompensation : {}", workersCompensationDTO);
        return workersCompensationRepository
            .save(workersCompensationMapper.toEntity(workersCompensationDTO))
            .map(workersCompensationMapper::toDto);
    }

    @Override
    public Mono<WorkersCompensationDTO> partialUpdate(WorkersCompensationDTO workersCompensationDTO) {
        log.debug("Request to partially update WorkersCompensation : {}", workersCompensationDTO);

        return workersCompensationRepository
            .findById(workersCompensationDTO.getWorkersCompensationId())
            .map(existingWorkersCompensation -> {
                workersCompensationMapper.partialUpdate(existingWorkersCompensation, workersCompensationDTO);

                return existingWorkersCompensation;
            })
            .flatMap(workersCompensationRepository::save)
            .map(workersCompensationMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<WorkersCompensationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WorkersCompensations");
        return workersCompensationRepository.findAllBy(pageable).map(workersCompensationMapper::toDto);
    }

    public Mono<Long> countAll() {
        return workersCompensationRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<WorkersCompensationDTO> findOne(Long id) {
        log.debug("Request to get WorkersCompensation : {}", id);
        return workersCompensationRepository.findById(id).map(workersCompensationMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete WorkersCompensation : {}", id);
        return workersCompensationRepository.deleteById(id);
    }
}
