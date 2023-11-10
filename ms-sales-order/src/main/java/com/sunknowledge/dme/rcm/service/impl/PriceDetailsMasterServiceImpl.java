package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.PriceDetailsMaster;
import com.sunknowledge.dme.rcm.repository.PriceDetailsMasterRepository;
import com.sunknowledge.dme.rcm.service.PriceDetailsMasterService;
import com.sunknowledge.dme.rcm.service.dto.PriceDetailsMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.PriceDetailsMasterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link PriceDetailsMaster}.
 */
@Service
@Transactional
public class PriceDetailsMasterServiceImpl implements PriceDetailsMasterService {

    private final Logger log = LoggerFactory.getLogger(PriceDetailsMasterServiceImpl.class);

    private final PriceDetailsMasterRepository priceDetailsMasterRepository;

    private final PriceDetailsMasterMapper priceDetailsMasterMapper;

    public PriceDetailsMasterServiceImpl(
        PriceDetailsMasterRepository priceDetailsMasterRepository,
        PriceDetailsMasterMapper priceDetailsMasterMapper
    ) {
        this.priceDetailsMasterRepository = priceDetailsMasterRepository;
        this.priceDetailsMasterMapper = priceDetailsMasterMapper;
    }

    @Override
    public Mono<PriceDetailsMasterDTO> save(PriceDetailsMasterDTO priceDetailsMasterDTO) {
        log.debug("Request to save PriceDetailsMaster : {}", priceDetailsMasterDTO);
        return priceDetailsMasterRepository
            .save(priceDetailsMasterMapper.toEntity(priceDetailsMasterDTO))
            .map(priceDetailsMasterMapper::toDto);
    }

    @Override
    public Mono<PriceDetailsMasterDTO> update(PriceDetailsMasterDTO priceDetailsMasterDTO) {
        log.debug("Request to save PriceDetailsMaster : {}", priceDetailsMasterDTO);
        return priceDetailsMasterRepository
            .save(priceDetailsMasterMapper.toEntity(priceDetailsMasterDTO))
            .map(priceDetailsMasterMapper::toDto);
    }

    @Override
    public Mono<PriceDetailsMasterDTO> partialUpdate(PriceDetailsMasterDTO priceDetailsMasterDTO) {
        log.debug("Request to partially update PriceDetailsMaster : {}", priceDetailsMasterDTO);

        return priceDetailsMasterRepository
            .findById(priceDetailsMasterDTO.getPriceDetailsId())
            .map(existingPriceDetailsMaster -> {
                priceDetailsMasterMapper.partialUpdate(existingPriceDetailsMaster, priceDetailsMasterDTO);

                return existingPriceDetailsMaster;
            })
            .flatMap(priceDetailsMasterRepository::save)
            .map(priceDetailsMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<PriceDetailsMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PriceDetailsMasters");
        return priceDetailsMasterRepository.findAllBy(pageable).map(priceDetailsMasterMapper::toDto);
    }

    public Mono<Long> countAll() {
        return priceDetailsMasterRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<PriceDetailsMasterDTO> findOne(Long id) {
        log.debug("Request to get PriceDetailsMaster : {}", id);
        return priceDetailsMasterRepository.findById(id).map(priceDetailsMasterMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete PriceDetailsMaster : {}", id);
        return priceDetailsMasterRepository.deleteById(id);
    }
}
