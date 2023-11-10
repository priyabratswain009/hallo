package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.DeliveryAbnData;
import com.sunknowledge.dme.rcm.repository.DeliveryAbnDataRepository;
import com.sunknowledge.dme.rcm.service.DeliveryAbnDataService;
import com.sunknowledge.dme.rcm.service.dto.DeliveryAbnDataDTO;
import com.sunknowledge.dme.rcm.service.mapper.DeliveryAbnDataMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link DeliveryAbnData}.
 */
@Service
@Transactional
public class DeliveryAbnDataServiceImpl implements DeliveryAbnDataService {

    private final Logger log = LoggerFactory.getLogger(DeliveryAbnDataServiceImpl.class);

    private final DeliveryAbnDataRepository deliveryAbnDataRepository;

    private final DeliveryAbnDataMapper deliveryAbnDataMapper;

    public DeliveryAbnDataServiceImpl(DeliveryAbnDataRepository deliveryAbnDataRepository, DeliveryAbnDataMapper deliveryAbnDataMapper) {
        this.deliveryAbnDataRepository = deliveryAbnDataRepository;
        this.deliveryAbnDataMapper = deliveryAbnDataMapper;
    }

    @Override
    public DeliveryAbnDataDTO save(DeliveryAbnDataDTO deliveryAbnDataDTO) {
        log.debug("Request to save DeliveryAbnData : {}", deliveryAbnDataDTO);
        DeliveryAbnData deliveryAbnData = deliveryAbnDataMapper.toEntity(deliveryAbnDataDTO);
        deliveryAbnData = deliveryAbnDataRepository.save(deliveryAbnData);
        return deliveryAbnDataMapper.toDto(deliveryAbnData);
    }

    @Override
    public DeliveryAbnDataDTO update(DeliveryAbnDataDTO deliveryAbnDataDTO) {
        log.debug("Request to save DeliveryAbnData : {}", deliveryAbnDataDTO);
        DeliveryAbnData deliveryAbnData = deliveryAbnDataMapper.toEntity(deliveryAbnDataDTO);
        deliveryAbnData = deliveryAbnDataRepository.save(deliveryAbnData);
        return deliveryAbnDataMapper.toDto(deliveryAbnData);
    }

    @Override
    public Optional<DeliveryAbnDataDTO> partialUpdate(DeliveryAbnDataDTO deliveryAbnDataDTO) {
        log.debug("Request to partially update DeliveryAbnData : {}", deliveryAbnDataDTO);

        return deliveryAbnDataRepository
            .findById(deliveryAbnDataDTO.getDeliveryAbnDataId())
            .map(existingDeliveryAbnData -> {
                deliveryAbnDataMapper.partialUpdate(existingDeliveryAbnData, deliveryAbnDataDTO);

                return existingDeliveryAbnData;
            })
            .map(deliveryAbnDataRepository::save)
            .map(deliveryAbnDataMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DeliveryAbnDataDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DeliveryAbnData");
        return deliveryAbnDataRepository.findAll(pageable).map(deliveryAbnDataMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DeliveryAbnDataDTO> findOne(Long id) {
        log.debug("Request to get DeliveryAbnData : {}", id);
        return deliveryAbnDataRepository.findById(id).map(deliveryAbnDataMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DeliveryAbnData : {}", id);
        deliveryAbnDataRepository.deleteById(id);
    }
}
