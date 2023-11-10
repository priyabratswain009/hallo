package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.AbnDelivery;
import com.sunknowledge.dme.rcm.repository.AbnDeliveryRepository;
import com.sunknowledge.dme.rcm.service.AbnDeliveryService;
import com.sunknowledge.dme.rcm.service.dto.AbnDeliveryDTO;
import com.sunknowledge.dme.rcm.service.mapper.AbnDeliveryMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AbnDelivery}.
 */
@Service
@Transactional
public class AbnDeliveryServiceImpl implements AbnDeliveryService {

    private final Logger log = LoggerFactory.getLogger(AbnDeliveryServiceImpl.class);

    private final AbnDeliveryRepository abnDeliveryRepository;

    private final AbnDeliveryMapper abnDeliveryMapper;

    public AbnDeliveryServiceImpl(AbnDeliveryRepository abnDeliveryRepository, AbnDeliveryMapper abnDeliveryMapper) {
        this.abnDeliveryRepository = abnDeliveryRepository;
        this.abnDeliveryMapper = abnDeliveryMapper;
    }

    @Override
    public AbnDeliveryDTO save(AbnDeliveryDTO abnDeliveryDTO) {
        log.debug("Request to save AbnDelivery : {}", abnDeliveryDTO);
        AbnDelivery abnDelivery = abnDeliveryMapper.toEntity(abnDeliveryDTO);
        abnDelivery = abnDeliveryRepository.save(abnDelivery);
        return abnDeliveryMapper.toDto(abnDelivery);
    }

    @Override
    public AbnDeliveryDTO update(AbnDeliveryDTO abnDeliveryDTO) {
        log.debug("Request to save AbnDelivery : {}", abnDeliveryDTO);
        AbnDelivery abnDelivery = abnDeliveryMapper.toEntity(abnDeliveryDTO);
        abnDelivery = abnDeliveryRepository.save(abnDelivery);
        return abnDeliveryMapper.toDto(abnDelivery);
    }

    @Override
    public Optional<AbnDeliveryDTO> partialUpdate(AbnDeliveryDTO abnDeliveryDTO) {
        log.debug("Request to partially update AbnDelivery : {}", abnDeliveryDTO);

        return abnDeliveryRepository
            .findById(abnDeliveryDTO.getAbnDeliveryId())
            .map(existingAbnDelivery -> {
                abnDeliveryMapper.partialUpdate(existingAbnDelivery, abnDeliveryDTO);

                return existingAbnDelivery;
            })
            .map(abnDeliveryRepository::save)
            .map(abnDeliveryMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AbnDeliveryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AbnDeliveries");
        return abnDeliveryRepository.findAll(pageable).map(abnDeliveryMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AbnDeliveryDTO> findOne(Long id) {
        log.debug("Request to get AbnDelivery : {}", id);
        return abnDeliveryRepository.findById(id).map(abnDeliveryMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AbnDelivery : {}", id);
        abnDeliveryRepository.deleteById(id);
    }
}
