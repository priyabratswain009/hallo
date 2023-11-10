package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.DeliveryItems;
import com.sunknowledge.dme.rcm.repository.DeliveryItemsRepository;
import com.sunknowledge.dme.rcm.service.DeliveryItemsService;
import com.sunknowledge.dme.rcm.service.dto.DeliveryItemsDTO;
import com.sunknowledge.dme.rcm.service.mapper.DeliveryItemsMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link DeliveryItems}.
 */
@Service
@Transactional
public class DeliveryItemsServiceImpl implements DeliveryItemsService {

    private final Logger log = LoggerFactory.getLogger(DeliveryItemsServiceImpl.class);

    private final DeliveryItemsRepository deliveryItemsRepository;

    private final DeliveryItemsMapper deliveryItemsMapper;

    public DeliveryItemsServiceImpl(DeliveryItemsRepository deliveryItemsRepository, DeliveryItemsMapper deliveryItemsMapper) {
        this.deliveryItemsRepository = deliveryItemsRepository;
        this.deliveryItemsMapper = deliveryItemsMapper;
    }

    @Override
    public DeliveryItemsDTO save(DeliveryItemsDTO deliveryItemsDTO) {
        log.debug("Request to save DeliveryItems : {}", deliveryItemsDTO);
        DeliveryItems deliveryItems = deliveryItemsMapper.toEntity(deliveryItemsDTO);
        deliveryItems = deliveryItemsRepository.save(deliveryItems);
        return deliveryItemsMapper.toDto(deliveryItems);
    }

    @Override
    public DeliveryItemsDTO update(DeliveryItemsDTO deliveryItemsDTO) {
        log.debug("Request to save DeliveryItems : {}", deliveryItemsDTO);
        DeliveryItems deliveryItems = deliveryItemsMapper.toEntity(deliveryItemsDTO);
        deliveryItems = deliveryItemsRepository.save(deliveryItems);
        return deliveryItemsMapper.toDto(deliveryItems);
    }

    @Override
    public Optional<DeliveryItemsDTO> partialUpdate(DeliveryItemsDTO deliveryItemsDTO) {
        log.debug("Request to partially update DeliveryItems : {}", deliveryItemsDTO);

        return deliveryItemsRepository
            .findById(deliveryItemsDTO.getDeliveryItemId())
            .map(existingDeliveryItems -> {
                deliveryItemsMapper.partialUpdate(existingDeliveryItems, deliveryItemsDTO);

                return existingDeliveryItems;
            })
            .map(deliveryItemsRepository::save)
            .map(deliveryItemsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DeliveryItemsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DeliveryItems");
        return deliveryItemsRepository.findAll(pageable).map(deliveryItemsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DeliveryItemsDTO> findOne(Long id) {
        log.debug("Request to get DeliveryItems : {}", id);
        return deliveryItemsRepository.findById(id).map(deliveryItemsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DeliveryItems : {}", id);
        deliveryItemsRepository.deleteById(id);
    }
}
