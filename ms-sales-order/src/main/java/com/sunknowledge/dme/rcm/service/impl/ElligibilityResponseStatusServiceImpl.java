package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ElligibilityResponseStatus;
import com.sunknowledge.dme.rcm.repository.ElligibilityResponseStatusRepository;
import com.sunknowledge.dme.rcm.service.ElligibilityResponseStatusService;
import com.sunknowledge.dme.rcm.service.dto.ElligibilityResponseStatusDTO;
import com.sunknowledge.dme.rcm.service.mapper.ElligibilityResponseStatusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link ElligibilityResponseStatus}.
 */
@Service
@Transactional
public class ElligibilityResponseStatusServiceImpl implements ElligibilityResponseStatusService {

    private final Logger log = LoggerFactory.getLogger(ElligibilityResponseStatusServiceImpl.class);

    private final ElligibilityResponseStatusRepository elligibilityResponseStatusRepository;

    private final ElligibilityResponseStatusMapper elligibilityResponseStatusMapper;

    public ElligibilityResponseStatusServiceImpl(
        ElligibilityResponseStatusRepository elligibilityResponseStatusRepository,
        ElligibilityResponseStatusMapper elligibilityResponseStatusMapper
    ) {
        this.elligibilityResponseStatusRepository = elligibilityResponseStatusRepository;
        this.elligibilityResponseStatusMapper = elligibilityResponseStatusMapper;
    }

    @Override
    public Mono<ElligibilityResponseStatusDTO> save(ElligibilityResponseStatusDTO elligibilityResponseStatusDTO) {
        log.debug("Request to save ElligibilityResponseStatus : {}", elligibilityResponseStatusDTO);
        return elligibilityResponseStatusRepository
            .save(elligibilityResponseStatusMapper.toEntity(elligibilityResponseStatusDTO))
            .map(elligibilityResponseStatusMapper::toDto);
    }

    @Override
    public Mono<ElligibilityResponseStatusDTO> update(ElligibilityResponseStatusDTO elligibilityResponseStatusDTO) {
        log.debug("Request to save ElligibilityResponseStatus : {}", elligibilityResponseStatusDTO);
        return elligibilityResponseStatusRepository
            .save(elligibilityResponseStatusMapper.toEntity(elligibilityResponseStatusDTO))
            .map(elligibilityResponseStatusMapper::toDto);
    }

    @Override
    public Mono<ElligibilityResponseStatusDTO> partialUpdate(ElligibilityResponseStatusDTO elligibilityResponseStatusDTO) {
        log.debug("Request to partially update ElligibilityResponseStatus : {}", elligibilityResponseStatusDTO);

        return elligibilityResponseStatusRepository
            .findById(elligibilityResponseStatusDTO.getElligibilityResponseStatusId())
            .map(existingElligibilityResponseStatus -> {
                elligibilityResponseStatusMapper.partialUpdate(existingElligibilityResponseStatus, elligibilityResponseStatusDTO);

                return existingElligibilityResponseStatus;
            })
            .flatMap(elligibilityResponseStatusRepository::save)
            .map(elligibilityResponseStatusMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<ElligibilityResponseStatusDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ElligibilityResponseStatuses");
        return elligibilityResponseStatusRepository.findAllBy(pageable).map(elligibilityResponseStatusMapper::toDto);
    }

    public Mono<Long> countAll() {
        return elligibilityResponseStatusRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<ElligibilityResponseStatusDTO> findOne(Long id) {
        log.debug("Request to get ElligibilityResponseStatus : {}", id);
        return elligibilityResponseStatusRepository.findById(id).map(elligibilityResponseStatusMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete ElligibilityResponseStatus : {}", id);
        return elligibilityResponseStatusRepository.deleteById(id);
    }
}
