package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ParDetails;
import com.sunknowledge.dme.rcm.repository.ParDetailsRepository;
import com.sunknowledge.dme.rcm.service.ParDetailsService;
import com.sunknowledge.dme.rcm.service.dto.ParDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.ParDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link ParDetails}.
 */
@Service
@Transactional
public class ParDetailsServiceImpl implements ParDetailsService {

    private final Logger log = LoggerFactory.getLogger(ParDetailsServiceImpl.class);

    private final ParDetailsRepository parDetailsRepository;

    private final ParDetailsMapper parDetailsMapper;

    public ParDetailsServiceImpl(ParDetailsRepository parDetailsRepository, ParDetailsMapper parDetailsMapper) {
        this.parDetailsRepository = parDetailsRepository;
        this.parDetailsMapper = parDetailsMapper;
    }

    @Override
    public Mono<ParDetailsDTO> save(ParDetailsDTO parDetailsDTO) {
        log.debug("Request to save ParDetails : {}", parDetailsDTO);
        return parDetailsRepository.save(parDetailsMapper.toEntity(parDetailsDTO)).map(parDetailsMapper::toDto);
    }

    @Override
    public Mono<ParDetailsDTO> update(ParDetailsDTO parDetailsDTO) {
        log.debug("Request to save ParDetails : {}", parDetailsDTO);
        return parDetailsRepository.save(parDetailsMapper.toEntity(parDetailsDTO)).map(parDetailsMapper::toDto);
    }

    @Override
    public Mono<ParDetailsDTO> partialUpdate(ParDetailsDTO parDetailsDTO) {
        log.debug("Request to partially update ParDetails : {}", parDetailsDTO);

        return parDetailsRepository
            .findById(parDetailsDTO.getParDetailsId())
            .map(existingParDetails -> {
                parDetailsMapper.partialUpdate(existingParDetails, parDetailsDTO);

                return existingParDetails;
            })
            .flatMap(parDetailsRepository::save)
            .map(parDetailsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<ParDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ParDetails");
        return parDetailsRepository.findAllBy(pageable).map(parDetailsMapper::toDto);
    }

    public Mono<Long> countAll() {
        return parDetailsRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<ParDetailsDTO> findOne(Long id) {
        log.debug("Request to get ParDetails : {}", id);
        return parDetailsRepository.findById(id).map(parDetailsMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete ParDetails : {}", id);
        return parDetailsRepository.deleteById(id);
    }
}
