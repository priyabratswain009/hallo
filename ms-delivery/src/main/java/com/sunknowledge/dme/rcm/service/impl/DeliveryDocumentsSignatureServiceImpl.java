package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.DeliveryDocumentsSignature;
import com.sunknowledge.dme.rcm.repository.DeliveryDocumentsSignatureRepository;
import com.sunknowledge.dme.rcm.service.DeliveryDocumentsSignatureService;
import com.sunknowledge.dme.rcm.service.dto.DeliveryDocumentsSignatureDTO;
import com.sunknowledge.dme.rcm.service.mapper.DeliveryDocumentsSignatureMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link DeliveryDocumentsSignature}.
 */
@Service
@Transactional
public class DeliveryDocumentsSignatureServiceImpl implements DeliveryDocumentsSignatureService {

    private final Logger log = LoggerFactory.getLogger(DeliveryDocumentsSignatureServiceImpl.class);

    private final DeliveryDocumentsSignatureRepository deliveryDocumentsSignatureRepository;

    private final DeliveryDocumentsSignatureMapper deliveryDocumentsSignatureMapper;

    public DeliveryDocumentsSignatureServiceImpl(
        DeliveryDocumentsSignatureRepository deliveryDocumentsSignatureRepository,
        DeliveryDocumentsSignatureMapper deliveryDocumentsSignatureMapper
    ) {
        this.deliveryDocumentsSignatureRepository = deliveryDocumentsSignatureRepository;
        this.deliveryDocumentsSignatureMapper = deliveryDocumentsSignatureMapper;
    }

    @Override
    public DeliveryDocumentsSignatureDTO save(DeliveryDocumentsSignatureDTO deliveryDocumentsSignatureDTO) {
        log.debug("Request to save DeliveryDocumentsSignature : {}", deliveryDocumentsSignatureDTO);
        DeliveryDocumentsSignature deliveryDocumentsSignature = deliveryDocumentsSignatureMapper.toEntity(deliveryDocumentsSignatureDTO);
        deliveryDocumentsSignature = deliveryDocumentsSignatureRepository.save(deliveryDocumentsSignature);
        return deliveryDocumentsSignatureMapper.toDto(deliveryDocumentsSignature);
    }

    @Override
    public DeliveryDocumentsSignatureDTO update(DeliveryDocumentsSignatureDTO deliveryDocumentsSignatureDTO) {
        log.debug("Request to save DeliveryDocumentsSignature : {}", deliveryDocumentsSignatureDTO);
        DeliveryDocumentsSignature deliveryDocumentsSignature = deliveryDocumentsSignatureMapper.toEntity(deliveryDocumentsSignatureDTO);
        deliveryDocumentsSignature = deliveryDocumentsSignatureRepository.save(deliveryDocumentsSignature);
        return deliveryDocumentsSignatureMapper.toDto(deliveryDocumentsSignature);
    }

    @Override
    public Optional<DeliveryDocumentsSignatureDTO> partialUpdate(DeliveryDocumentsSignatureDTO deliveryDocumentsSignatureDTO) {
        log.debug("Request to partially update DeliveryDocumentsSignature : {}", deliveryDocumentsSignatureDTO);

        return deliveryDocumentsSignatureRepository
            .findById(deliveryDocumentsSignatureDTO.getDeliveryDocumentSignatureId())
            .map(existingDeliveryDocumentsSignature -> {
                deliveryDocumentsSignatureMapper.partialUpdate(existingDeliveryDocumentsSignature, deliveryDocumentsSignatureDTO);

                return existingDeliveryDocumentsSignature;
            })
            .map(deliveryDocumentsSignatureRepository::save)
            .map(deliveryDocumentsSignatureMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DeliveryDocumentsSignatureDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DeliveryDocumentsSignatures");
        return deliveryDocumentsSignatureRepository.findAll(pageable).map(deliveryDocumentsSignatureMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DeliveryDocumentsSignatureDTO> findOne(Long id) {
        log.debug("Request to get DeliveryDocumentsSignature : {}", id);
        return deliveryDocumentsSignatureRepository.findById(id).map(deliveryDocumentsSignatureMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DeliveryDocumentsSignature : {}", id);
        deliveryDocumentsSignatureRepository.deleteById(id);
    }
}
