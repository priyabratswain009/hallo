package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.DeliveryAbnData;
import com.sunknowledge.dme.rcm.repository.DeliveryAbnDataRepository;
import com.sunknowledge.dme.rcm.service.DeliveryAbnDataService;
import com.sunknowledge.dme.rcm.service.dto.DeliveryAbnDataDTO;
import com.sunknowledge.dme.rcm.service.mapper.DeliveryAbnDataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Mono<DeliveryAbnDataDTO> save(DeliveryAbnDataDTO deliveryAbnDataDTO) {
        log.debug("Request to save DeliveryAbnData : {}", deliveryAbnDataDTO);
        return deliveryAbnDataRepository.save(deliveryAbnDataMapper.toEntity(deliveryAbnDataDTO)).map(deliveryAbnDataMapper::toDto);
    }

    @Override
    public Mono<DeliveryAbnDataDTO> update(DeliveryAbnDataDTO deliveryAbnDataDTO) {
        log.debug("Request to save DeliveryAbnData : {}", deliveryAbnDataDTO);
        return deliveryAbnDataRepository.save(deliveryAbnDataMapper.toEntity(deliveryAbnDataDTO)).map(deliveryAbnDataMapper::toDto);
    }

    @Override
    public Mono<DeliveryAbnDataDTO> partialUpdate(DeliveryAbnDataDTO deliveryAbnDataDTO) {
        log.debug("Request to partially update DeliveryAbnData : {}", deliveryAbnDataDTO);

        return deliveryAbnDataRepository
            .findById(deliveryAbnDataDTO.getDeliveryAbnDataId())
            .map(existingDeliveryAbnData -> {
                deliveryAbnDataMapper.partialUpdate(existingDeliveryAbnData, deliveryAbnDataDTO);

                return existingDeliveryAbnData;
            })
            .flatMap(deliveryAbnDataRepository::save)
            .map(deliveryAbnDataMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<DeliveryAbnDataDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DeliveryAbnData");
        return deliveryAbnDataRepository.findAllBy(pageable).map(deliveryAbnDataMapper::toDto);
    }

    public Mono<Long> countAll() {
        return deliveryAbnDataRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<DeliveryAbnDataDTO> findOne(Long id) {
        log.debug("Request to get DeliveryAbnData : {}", id);
        return deliveryAbnDataRepository.findById(id).map(deliveryAbnDataMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete DeliveryAbnData : {}", id);
        return deliveryAbnDataRepository.deleteById(id);
    }
}
