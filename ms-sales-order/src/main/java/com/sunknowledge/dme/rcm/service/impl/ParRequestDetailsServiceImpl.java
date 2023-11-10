package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ParRequestDetails;
import com.sunknowledge.dme.rcm.repository.ParRequestDetailsRepository;
import com.sunknowledge.dme.rcm.service.ParRequestDetailsService;
import com.sunknowledge.dme.rcm.service.dto.ParRequestDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.ParRequestDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link ParRequestDetails}.
 */
@Service
@Transactional
public class ParRequestDetailsServiceImpl implements ParRequestDetailsService {

    private final Logger log = LoggerFactory.getLogger(ParRequestDetailsServiceImpl.class);

    private final ParRequestDetailsRepository parRequestDetailsRepository;

    private final ParRequestDetailsMapper parRequestDetailsMapper;

    public ParRequestDetailsServiceImpl(
        ParRequestDetailsRepository parRequestDetailsRepository,
        ParRequestDetailsMapper parRequestDetailsMapper
    ) {
        this.parRequestDetailsRepository = parRequestDetailsRepository;
        this.parRequestDetailsMapper = parRequestDetailsMapper;
    }

    @Override
    public Mono<ParRequestDetailsDTO> save(ParRequestDetailsDTO parRequestDetailsDTO) {
        log.debug("Request to save ParRequestDetails : {}", parRequestDetailsDTO);
        return parRequestDetailsRepository.save(parRequestDetailsMapper.toEntity(parRequestDetailsDTO)).map(parRequestDetailsMapper::toDto);
    }

    @Override
    public Mono<ParRequestDetailsDTO> update(ParRequestDetailsDTO parRequestDetailsDTO) {
        log.debug("Request to save ParRequestDetails : {}", parRequestDetailsDTO);
        return parRequestDetailsRepository.save(parRequestDetailsMapper.toEntity(parRequestDetailsDTO)).map(parRequestDetailsMapper::toDto);
    }

    @Override
    public Mono<ParRequestDetailsDTO> partialUpdate(ParRequestDetailsDTO parRequestDetailsDTO) {
        log.debug("Request to partially update ParRequestDetails : {}", parRequestDetailsDTO);

        return parRequestDetailsRepository
            .findById(parRequestDetailsDTO.getParRequestDetailsId())
            .map(existingParRequestDetails -> {
                parRequestDetailsMapper.partialUpdate(existingParRequestDetails, parRequestDetailsDTO);

                return existingParRequestDetails;
            })
            .flatMap(parRequestDetailsRepository::save)
            .map(parRequestDetailsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<ParRequestDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ParRequestDetails");
        return parRequestDetailsRepository.findAllBy(pageable).map(parRequestDetailsMapper::toDto);
    }

    public Mono<Long> countAll() {
        return parRequestDetailsRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<ParRequestDetailsDTO> findOne(Long id) {
        log.debug("Request to get ParRequestDetails : {}", id);
        return parRequestDetailsRepository.findById(id).map(parRequestDetailsMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete ParRequestDetails : {}", id);
        return parRequestDetailsRepository.deleteById(id);
    }
}
