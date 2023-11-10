package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.DeliveryDocuments;
import com.sunknowledge.dme.rcm.repository.DeliveryDocumentsRepository;
import com.sunknowledge.dme.rcm.service.DeliveryDocumentsService;
import com.sunknowledge.dme.rcm.service.dto.DeliveryDocumentsDTO;
import com.sunknowledge.dme.rcm.service.mapper.DeliveryDocumentsMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link DeliveryDocuments}.
 */
@Service
@Transactional
public class DeliveryDocumentsServiceImpl implements DeliveryDocumentsService {

    private final Logger log = LoggerFactory.getLogger(DeliveryDocumentsServiceImpl.class);

    private final DeliveryDocumentsRepository deliveryDocumentsRepository;

    private final DeliveryDocumentsMapper deliveryDocumentsMapper;

    public DeliveryDocumentsServiceImpl(
        DeliveryDocumentsRepository deliveryDocumentsRepository,
        DeliveryDocumentsMapper deliveryDocumentsMapper
    ) {
        this.deliveryDocumentsRepository = deliveryDocumentsRepository;
        this.deliveryDocumentsMapper = deliveryDocumentsMapper;
    }

    @Override
    public DeliveryDocumentsDTO save(DeliveryDocumentsDTO deliveryDocumentsDTO) {
        log.debug("Request to save DeliveryDocuments : {}", deliveryDocumentsDTO);
        DeliveryDocuments deliveryDocuments = deliveryDocumentsMapper.toEntity(deliveryDocumentsDTO);
        deliveryDocuments = deliveryDocumentsRepository.save(deliveryDocuments);
        return deliveryDocumentsMapper.toDto(deliveryDocuments);
    }

    @Override
    public DeliveryDocumentsDTO update(DeliveryDocumentsDTO deliveryDocumentsDTO) {
        log.debug("Request to save DeliveryDocuments : {}", deliveryDocumentsDTO);
        DeliveryDocuments deliveryDocuments = deliveryDocumentsMapper.toEntity(deliveryDocumentsDTO);
        deliveryDocuments = deliveryDocumentsRepository.save(deliveryDocuments);
        return deliveryDocumentsMapper.toDto(deliveryDocuments);
    }

    @Override
    public Optional<DeliveryDocumentsDTO> partialUpdate(DeliveryDocumentsDTO deliveryDocumentsDTO) {
        log.debug("Request to partially update DeliveryDocuments : {}", deliveryDocumentsDTO);

        return deliveryDocumentsRepository
            .findById(deliveryDocumentsDTO.getDeliveryDocId())
            .map(existingDeliveryDocuments -> {
                deliveryDocumentsMapper.partialUpdate(existingDeliveryDocuments, deliveryDocumentsDTO);

                return existingDeliveryDocuments;
            })
            .map(deliveryDocumentsRepository::save)
            .map(deliveryDocumentsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DeliveryDocumentsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DeliveryDocuments");
        return deliveryDocumentsRepository.findAll(pageable).map(deliveryDocumentsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DeliveryDocumentsDTO> findOne(Long id) {
        log.debug("Request to get DeliveryDocuments : {}", id);
        return deliveryDocumentsRepository.findById(id).map(deliveryDocumentsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DeliveryDocuments : {}", id);
        deliveryDocumentsRepository.deleteById(id);
    }
}
