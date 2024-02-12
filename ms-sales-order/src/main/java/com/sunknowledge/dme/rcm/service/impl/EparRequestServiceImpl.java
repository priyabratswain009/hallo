package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.EparRequest;
import com.sunknowledge.dme.rcm.repository.EparRequestRepository;
import com.sunknowledge.dme.rcm.service.EparRequestService;
import com.sunknowledge.dme.rcm.service.dto.EparRequestDTO;
import com.sunknowledge.dme.rcm.service.mapper.EparRequestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link EparRequest}.
 */
@Service
@Transactional
public class EparRequestServiceImpl implements EparRequestService {

    private final Logger log = LoggerFactory.getLogger(EparRequestServiceImpl.class);

    private final EparRequestRepository eparRequestRepository;

    private final EparRequestMapper eparRequestMapper;

    public EparRequestServiceImpl(EparRequestRepository eparRequestRepository, EparRequestMapper eparRequestMapper) {
        this.eparRequestRepository = eparRequestRepository;
        this.eparRequestMapper = eparRequestMapper;
    }

    @Override
    public Mono<EparRequestDTO> save(EparRequestDTO eparRequestDTO) {
        log.debug("Request to save EparRequest : {}", eparRequestDTO);
        return eparRequestRepository.save(eparRequestMapper.toEntity(eparRequestDTO)).map(eparRequestMapper::toDto);
    }

    @Override
    public Mono<EparRequestDTO> update(EparRequestDTO eparRequestDTO) {
        log.debug("Request to update EparRequest : {}", eparRequestDTO);
        return eparRequestRepository.save(eparRequestMapper.toEntity(eparRequestDTO)).map(eparRequestMapper::toDto);
    }

    @Override
    public Mono<EparRequestDTO> partialUpdate(EparRequestDTO eparRequestDTO) {
        log.debug("Request to partially update EparRequest : {}", eparRequestDTO);

        return eparRequestRepository
            .findById(eparRequestDTO.getEparRequestId())
            .map(existingEparRequest -> {
                eparRequestMapper.partialUpdate(existingEparRequest, eparRequestDTO);

                return existingEparRequest;
            })
            .flatMap(eparRequestRepository::save)
            .map(eparRequestMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<EparRequestDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EparRequests");
        return eparRequestRepository.findAllBy(pageable).map(eparRequestMapper::toDto);
    }

    public Mono<Long> countAll() {
        return eparRequestRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<EparRequestDTO> findOne(Long id) {
        log.debug("Request to get EparRequest : {}", id);
        return eparRequestRepository.findById(id).map(eparRequestMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete EparRequest : {}", id);
        return eparRequestRepository.deleteById(id);
    }
}
