package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.PickupExchange;
import com.sunknowledge.dme.rcm.repository.PickupExchangeRepository;
import com.sunknowledge.dme.rcm.service.PickupExchangeService;
import com.sunknowledge.dme.rcm.service.dto.PickupExchangeDTO;
import com.sunknowledge.dme.rcm.service.mapper.PickupExchangeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link PickupExchange}.
 */
@Service
@Transactional
public class PickupExchangeServiceImpl implements PickupExchangeService {

    private final Logger log = LoggerFactory.getLogger(PickupExchangeServiceImpl.class);

    private final PickupExchangeRepository pickupExchangeRepository;

    private final PickupExchangeMapper pickupExchangeMapper;

    public PickupExchangeServiceImpl(PickupExchangeRepository pickupExchangeRepository, PickupExchangeMapper pickupExchangeMapper) {
        this.pickupExchangeRepository = pickupExchangeRepository;
        this.pickupExchangeMapper = pickupExchangeMapper;
    }

    @Override
    public Mono<PickupExchangeDTO> save(PickupExchangeDTO pickupExchangeDTO) {
        log.debug("Request to save PickupExchange : {}", pickupExchangeDTO);
        return pickupExchangeRepository.save(pickupExchangeMapper.toEntity(pickupExchangeDTO)).map(pickupExchangeMapper::toDto);
    }

    @Override
    public Mono<PickupExchangeDTO> update(PickupExchangeDTO pickupExchangeDTO) {
        log.debug("Request to save PickupExchange : {}", pickupExchangeDTO);
        return pickupExchangeRepository.save(pickupExchangeMapper.toEntity(pickupExchangeDTO)).map(pickupExchangeMapper::toDto);
    }

    @Override
    public Mono<PickupExchangeDTO> partialUpdate(PickupExchangeDTO pickupExchangeDTO) {
        log.debug("Request to partially update PickupExchange : {}", pickupExchangeDTO);

        return pickupExchangeRepository
            .findById(pickupExchangeDTO.getPickupExchangeId())
            .map(existingPickupExchange -> {
                pickupExchangeMapper.partialUpdate(existingPickupExchange, pickupExchangeDTO);

                return existingPickupExchange;
            })
            .flatMap(pickupExchangeRepository::save)
            .map(pickupExchangeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<PickupExchangeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PickupExchanges");
        return pickupExchangeRepository.findAllBy(pageable).map(pickupExchangeMapper::toDto);
    }

    public Mono<Long> countAll() {
        return pickupExchangeRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<PickupExchangeDTO> findOne(Long id) {
        log.debug("Request to get PickupExchange : {}", id);
        return pickupExchangeRepository.findById(id).map(pickupExchangeMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete PickupExchange : {}", id);
        return pickupExchangeRepository.deleteById(id);
    }
}
