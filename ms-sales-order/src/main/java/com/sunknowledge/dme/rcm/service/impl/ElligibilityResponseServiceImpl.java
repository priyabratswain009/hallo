package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ElligibilityResponse;
import com.sunknowledge.dme.rcm.repository.ElligibilityResponseRepository;
import com.sunknowledge.dme.rcm.service.ElligibilityResponseService;
import com.sunknowledge.dme.rcm.service.dto.ElligibilityResponseDTO;
import com.sunknowledge.dme.rcm.service.mapper.ElligibilityResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link ElligibilityResponse}.
 */
@Service
@Transactional
public class ElligibilityResponseServiceImpl implements ElligibilityResponseService {

    private final Logger log = LoggerFactory.getLogger(ElligibilityResponseServiceImpl.class);

    private final ElligibilityResponseRepository elligibilityResponseRepository;

    private final ElligibilityResponseMapper elligibilityResponseMapper;

    public ElligibilityResponseServiceImpl(
        ElligibilityResponseRepository elligibilityResponseRepository,
        ElligibilityResponseMapper elligibilityResponseMapper
    ) {
        this.elligibilityResponseRepository = elligibilityResponseRepository;
        this.elligibilityResponseMapper = elligibilityResponseMapper;
    }

    @Override
    public Mono<ElligibilityResponseDTO> save(ElligibilityResponseDTO elligibilityResponseDTO) {
        log.debug("Request to save ElligibilityResponse : {}", elligibilityResponseDTO);
        return elligibilityResponseRepository
            .save(elligibilityResponseMapper.toEntity(elligibilityResponseDTO))
            .map(elligibilityResponseMapper::toDto);
    }

    @Override
    public Mono<ElligibilityResponseDTO> update(ElligibilityResponseDTO elligibilityResponseDTO) {
        log.debug("Request to save ElligibilityResponse : {}", elligibilityResponseDTO);
        return elligibilityResponseRepository
            .save(elligibilityResponseMapper.toEntity(elligibilityResponseDTO))
            .map(elligibilityResponseMapper::toDto);
    }

    @Override
    public Mono<ElligibilityResponseDTO> partialUpdate(ElligibilityResponseDTO elligibilityResponseDTO) {
        log.debug("Request to partially update ElligibilityResponse : {}", elligibilityResponseDTO);

        return elligibilityResponseRepository
            .findById(elligibilityResponseDTO.getElligibilityResponseStatusId())
            .map(existingElligibilityResponse -> {
                elligibilityResponseMapper.partialUpdate(existingElligibilityResponse, elligibilityResponseDTO);

                return existingElligibilityResponse;
            })
            .flatMap(elligibilityResponseRepository::save)
            .map(elligibilityResponseMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<ElligibilityResponseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ElligibilityResponses");
        return elligibilityResponseRepository.findAllBy(pageable).map(elligibilityResponseMapper::toDto);
    }

    public Mono<Long> countAll() {
        return elligibilityResponseRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<ElligibilityResponseDTO> findOne(Long id) {
        log.debug("Request to get ElligibilityResponse : {}", id);
        return elligibilityResponseRepository.findById(id).map(elligibilityResponseMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete ElligibilityResponse : {}", id);
        return elligibilityResponseRepository.deleteById(id);
    }
}
