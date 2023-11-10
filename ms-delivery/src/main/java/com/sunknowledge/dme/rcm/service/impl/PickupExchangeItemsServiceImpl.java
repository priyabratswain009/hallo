package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.PickupExchangeItems;
import com.sunknowledge.dme.rcm.repository.PickupExchangeItemsRepository;
import com.sunknowledge.dme.rcm.service.PickupExchangeItemsService;
import com.sunknowledge.dme.rcm.service.dto.PickupExchangeItemsDTO;
import com.sunknowledge.dme.rcm.service.mapper.PickupExchangeItemsMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link PickupExchangeItems}.
 */
@Service
@Transactional
public class PickupExchangeItemsServiceImpl implements PickupExchangeItemsService {

    private final Logger log = LoggerFactory.getLogger(PickupExchangeItemsServiceImpl.class);

    private final PickupExchangeItemsRepository pickupExchangeItemsRepository;

    private final PickupExchangeItemsMapper pickupExchangeItemsMapper;

    public PickupExchangeItemsServiceImpl(
        PickupExchangeItemsRepository pickupExchangeItemsRepository,
        PickupExchangeItemsMapper pickupExchangeItemsMapper
    ) {
        this.pickupExchangeItemsRepository = pickupExchangeItemsRepository;
        this.pickupExchangeItemsMapper = pickupExchangeItemsMapper;
    }

    @Override
    public PickupExchangeItemsDTO save(PickupExchangeItemsDTO pickupExchangeItemsDTO) {
        log.debug("Request to save PickupExchangeItems : {}", pickupExchangeItemsDTO);
        PickupExchangeItems pickupExchangeItems = pickupExchangeItemsMapper.toEntity(pickupExchangeItemsDTO);
        pickupExchangeItems = pickupExchangeItemsRepository.save(pickupExchangeItems);
        return pickupExchangeItemsMapper.toDto(pickupExchangeItems);
    }

    @Override
    public PickupExchangeItemsDTO update(PickupExchangeItemsDTO pickupExchangeItemsDTO) {
        log.debug("Request to save PickupExchangeItems : {}", pickupExchangeItemsDTO);
        PickupExchangeItems pickupExchangeItems = pickupExchangeItemsMapper.toEntity(pickupExchangeItemsDTO);
        pickupExchangeItems = pickupExchangeItemsRepository.save(pickupExchangeItems);
        return pickupExchangeItemsMapper.toDto(pickupExchangeItems);
    }

    @Override
    public Optional<PickupExchangeItemsDTO> partialUpdate(PickupExchangeItemsDTO pickupExchangeItemsDTO) {
        log.debug("Request to partially update PickupExchangeItems : {}", pickupExchangeItemsDTO);

        return pickupExchangeItemsRepository
            .findById(pickupExchangeItemsDTO.getPickupExchangeItemId())
            .map(existingPickupExchangeItems -> {
                pickupExchangeItemsMapper.partialUpdate(existingPickupExchangeItems, pickupExchangeItemsDTO);

                return existingPickupExchangeItems;
            })
            .map(pickupExchangeItemsRepository::save)
            .map(pickupExchangeItemsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PickupExchangeItemsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PickupExchangeItems");
        return pickupExchangeItemsRepository.findAll(pageable).map(pickupExchangeItemsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PickupExchangeItemsDTO> findOne(Long id) {
        log.debug("Request to get PickupExchangeItems : {}", id);
        return pickupExchangeItemsRepository.findById(id).map(pickupExchangeItemsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PickupExchangeItems : {}", id);
        pickupExchangeItemsRepository.deleteById(id);
    }
}
