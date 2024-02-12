package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.EparResponse;
import com.sunknowledge.dme.rcm.repository.EparResponseRepository;
import com.sunknowledge.dme.rcm.service.EparResponseService;
import com.sunknowledge.dme.rcm.service.dto.EparResponseDTO;
import com.sunknowledge.dme.rcm.service.mapper.EparResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link EparResponse}.
 */
@Service
@Transactional
public class EparResponseServiceImpl implements EparResponseService {

    private final Logger log = LoggerFactory.getLogger(EparResponseServiceImpl.class);

    private final EparResponseRepository eparResponseRepository;

    private final EparResponseMapper eparResponseMapper;

    public EparResponseServiceImpl(EparResponseRepository eparResponseRepository, EparResponseMapper eparResponseMapper) {
        this.eparResponseRepository = eparResponseRepository;
        this.eparResponseMapper = eparResponseMapper;
    }

    @Override
    public Mono<EparResponseDTO> save(EparResponseDTO eparResponseDTO) {
        log.debug("Request to save EparResponse : {}", eparResponseDTO);
        return eparResponseRepository.save(eparResponseMapper.toEntity(eparResponseDTO)).map(eparResponseMapper::toDto);
    }

    @Override
    public Mono<EparResponseDTO> update(EparResponseDTO eparResponseDTO) {
        log.debug("Request to update EparResponse : {}", eparResponseDTO);
        return eparResponseRepository.save(eparResponseMapper.toEntity(eparResponseDTO)).map(eparResponseMapper::toDto);
    }

    @Override
    public Mono<EparResponseDTO> partialUpdate(EparResponseDTO eparResponseDTO) {
        log.debug("Request to partially update EparResponse : {}", eparResponseDTO);

        return eparResponseRepository
            .findById(eparResponseDTO.getEparResponseId())
            .map(existingEparResponse -> {
                eparResponseMapper.partialUpdate(existingEparResponse, eparResponseDTO);

                return existingEparResponse;
            })
            .flatMap(eparResponseRepository::save)
            .map(eparResponseMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<EparResponseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EparResponses");
        return eparResponseRepository.findAllBy(pageable).map(eparResponseMapper::toDto);
    }

    public Mono<Long> countAll() {
        return eparResponseRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<EparResponseDTO> findOne(Long id) {
        log.debug("Request to get EparResponse : {}", id);
        return eparResponseRepository.findById(id).map(eparResponseMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete EparResponse : {}", id);
        return eparResponseRepository.deleteById(id);
    }
}
