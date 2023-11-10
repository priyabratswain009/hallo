package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.PickupExchange;
import com.sunknowledge.dme.rcm.repository.PickupExchangeRepository;
import com.sunknowledge.dme.rcm.service.PickupExchangeService;
import com.sunknowledge.dme.rcm.service.dto.PickupExchangeDTO;
import com.sunknowledge.dme.rcm.service.mapper.PickupExchangeMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public PickupExchangeDTO save(PickupExchangeDTO pickupExchangeDTO) {
        log.debug("Request to save PickupExchange : {}", pickupExchangeDTO);
        PickupExchange pickupExchange = pickupExchangeMapper.toEntity(pickupExchangeDTO);
        pickupExchange = pickupExchangeRepository.save(pickupExchange);
        return pickupExchangeMapper.toDto(pickupExchange);
    }

    @Override
    public PickupExchangeDTO update(PickupExchangeDTO pickupExchangeDTO) {
        log.debug("Request to save PickupExchange : {}", pickupExchangeDTO);
        PickupExchange pickupExchange = pickupExchangeMapper.toEntity(pickupExchangeDTO);
        pickupExchange = pickupExchangeRepository.save(pickupExchange);
        return pickupExchangeMapper.toDto(pickupExchange);
    }

    @Override
    public Optional<PickupExchangeDTO> partialUpdate(PickupExchangeDTO pickupExchangeDTO) {
        log.debug("Request to partially update PickupExchange : {}", pickupExchangeDTO);

        return pickupExchangeRepository
            .findById(pickupExchangeDTO.getPickupExchangeId())
            .map(existingPickupExchange -> {
                pickupExchangeMapper.partialUpdate(existingPickupExchange, pickupExchangeDTO);

                return existingPickupExchange;
            })
            .map(pickupExchangeRepository::save)
            .map(pickupExchangeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PickupExchangeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PickupExchanges");
        return pickupExchangeRepository.findAll(pageable).map(pickupExchangeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PickupExchangeDTO> findOne(Long id) {
        log.debug("Request to get PickupExchange : {}", id);
        return pickupExchangeRepository.findById(id).map(pickupExchangeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PickupExchange : {}", id);
        pickupExchangeRepository.deleteById(id);
    }
}
