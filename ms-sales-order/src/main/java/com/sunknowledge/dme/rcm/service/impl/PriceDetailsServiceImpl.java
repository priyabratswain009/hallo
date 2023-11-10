package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.PriceDetails;
import com.sunknowledge.dme.rcm.repository.PriceDetailsRepository;
import com.sunknowledge.dme.rcm.service.PriceDetailsService;
import com.sunknowledge.dme.rcm.service.dto.PriceDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.PriceDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link PriceDetails}.
 */
@Service
@Transactional
public class PriceDetailsServiceImpl implements PriceDetailsService {

    private final Logger log = LoggerFactory.getLogger(PriceDetailsServiceImpl.class);

    private final PriceDetailsRepository priceDetailsRepository;

    private final PriceDetailsMapper priceDetailsMapper;

    public PriceDetailsServiceImpl(PriceDetailsRepository priceDetailsRepository, PriceDetailsMapper priceDetailsMapper) {
        this.priceDetailsRepository = priceDetailsRepository;
        this.priceDetailsMapper = priceDetailsMapper;
    }

    @Override
    public Mono<PriceDetailsDTO> save(PriceDetailsDTO priceDetailsDTO) {
        log.debug("Request to save PriceDetails : {}", priceDetailsDTO);
        return priceDetailsRepository.save(priceDetailsMapper.toEntity(priceDetailsDTO)).map(priceDetailsMapper::toDto);
    }

    @Override
    public Mono<PriceDetailsDTO> update(PriceDetailsDTO priceDetailsDTO) {
        log.debug("Request to save PriceDetails : {}", priceDetailsDTO);
        return priceDetailsRepository.save(priceDetailsMapper.toEntity(priceDetailsDTO)).map(priceDetailsMapper::toDto);
    }

    @Override
    public Mono<PriceDetailsDTO> partialUpdate(PriceDetailsDTO priceDetailsDTO) {
        log.debug("Request to partially update PriceDetails : {}", priceDetailsDTO);

        return priceDetailsRepository
            .findById(priceDetailsDTO.getPriceDetailsId())
            .map(existingPriceDetails -> {
                priceDetailsMapper.partialUpdate(existingPriceDetails, priceDetailsDTO);

                return existingPriceDetails;
            })
            .flatMap(priceDetailsRepository::save)
            .map(priceDetailsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<PriceDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PriceDetails");
        return priceDetailsRepository.findAllBy(pageable).map(priceDetailsMapper::toDto);
    }

    public Mono<Long> countAll() {
        return priceDetailsRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<PriceDetailsDTO> findOne(Long id) {
        log.debug("Request to get PriceDetails : {}", id);
        return priceDetailsRepository.findById(id).map(priceDetailsMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete PriceDetails : {}", id);
        return priceDetailsRepository.deleteById(id);
    }
}
