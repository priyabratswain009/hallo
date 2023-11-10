package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.DeliveryPatientCommunications;
import com.sunknowledge.dme.rcm.repository.DeliveryPatientCommunicationsRepository;
import com.sunknowledge.dme.rcm.service.DeliveryPatientCommunicationsService;
import com.sunknowledge.dme.rcm.service.dto.DeliveryPatientCommunicationsDTO;
import com.sunknowledge.dme.rcm.service.mapper.DeliveryPatientCommunicationsMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link DeliveryPatientCommunications}.
 */
@Service
@Transactional
public class DeliveryPatientCommunicationsServiceImpl implements DeliveryPatientCommunicationsService {

    private final Logger log = LoggerFactory.getLogger(DeliveryPatientCommunicationsServiceImpl.class);

    private final DeliveryPatientCommunicationsRepository deliveryPatientCommunicationsRepository;

    private final DeliveryPatientCommunicationsMapper deliveryPatientCommunicationsMapper;

    public DeliveryPatientCommunicationsServiceImpl(
        DeliveryPatientCommunicationsRepository deliveryPatientCommunicationsRepository,
        DeliveryPatientCommunicationsMapper deliveryPatientCommunicationsMapper
    ) {
        this.deliveryPatientCommunicationsRepository = deliveryPatientCommunicationsRepository;
        this.deliveryPatientCommunicationsMapper = deliveryPatientCommunicationsMapper;
    }

    @Override
    public DeliveryPatientCommunicationsDTO save(DeliveryPatientCommunicationsDTO deliveryPatientCommunicationsDTO) {
        log.debug("Request to save DeliveryPatientCommunications : {}", deliveryPatientCommunicationsDTO);
        DeliveryPatientCommunications deliveryPatientCommunications = deliveryPatientCommunicationsMapper.toEntity(
            deliveryPatientCommunicationsDTO
        );
        deliveryPatientCommunications = deliveryPatientCommunicationsRepository.save(deliveryPatientCommunications);
        return deliveryPatientCommunicationsMapper.toDto(deliveryPatientCommunications);
    }

    @Override
    public DeliveryPatientCommunicationsDTO update(DeliveryPatientCommunicationsDTO deliveryPatientCommunicationsDTO) {
        log.debug("Request to save DeliveryPatientCommunications : {}", deliveryPatientCommunicationsDTO);
        DeliveryPatientCommunications deliveryPatientCommunications = deliveryPatientCommunicationsMapper.toEntity(
            deliveryPatientCommunicationsDTO
        );
        deliveryPatientCommunications = deliveryPatientCommunicationsRepository.save(deliveryPatientCommunications);
        return deliveryPatientCommunicationsMapper.toDto(deliveryPatientCommunications);
    }

    @Override
    public Optional<DeliveryPatientCommunicationsDTO> partialUpdate(DeliveryPatientCommunicationsDTO deliveryPatientCommunicationsDTO) {
        log.debug("Request to partially update DeliveryPatientCommunications : {}", deliveryPatientCommunicationsDTO);

        return deliveryPatientCommunicationsRepository
            .findById(deliveryPatientCommunicationsDTO.getDeliveryPatientCommunicationsId())
            .map(existingDeliveryPatientCommunications -> {
                deliveryPatientCommunicationsMapper.partialUpdate(existingDeliveryPatientCommunications, deliveryPatientCommunicationsDTO);

                return existingDeliveryPatientCommunications;
            })
            .map(deliveryPatientCommunicationsRepository::save)
            .map(deliveryPatientCommunicationsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DeliveryPatientCommunicationsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DeliveryPatientCommunications");
        return deliveryPatientCommunicationsRepository.findAll(pageable).map(deliveryPatientCommunicationsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DeliveryPatientCommunicationsDTO> findOne(Long id) {
        log.debug("Request to get DeliveryPatientCommunications : {}", id);
        return deliveryPatientCommunicationsRepository.findById(id).map(deliveryPatientCommunicationsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DeliveryPatientCommunications : {}", id);
        deliveryPatientCommunicationsRepository.deleteById(id);
    }
}
