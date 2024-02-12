package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.DeliveryTicket;
import com.sunknowledge.dme.rcm.repository.DeliveryTicketRepository;
import com.sunknowledge.dme.rcm.service.DeliveryTicketService;
import com.sunknowledge.dme.rcm.service.dto.DeliveryTicketDTO;
import com.sunknowledge.dme.rcm.service.mapper.DeliveryTicketMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link DeliveryTicket}.
 */
@Service
@Transactional
public class DeliveryTicketServiceImpl implements DeliveryTicketService {

    private final Logger log = LoggerFactory.getLogger(DeliveryTicketServiceImpl.class);

    private final DeliveryTicketRepository deliveryTicketRepository;

    private final DeliveryTicketMapper deliveryTicketMapper;

    public DeliveryTicketServiceImpl(DeliveryTicketRepository deliveryTicketRepository, DeliveryTicketMapper deliveryTicketMapper) {
        this.deliveryTicketRepository = deliveryTicketRepository;
        this.deliveryTicketMapper = deliveryTicketMapper;
    }

    @Override
    public Mono<DeliveryTicketDTO> save(DeliveryTicketDTO deliveryTicketDTO) {
        log.debug("Request to save DeliveryTicket : {}", deliveryTicketDTO);
        return deliveryTicketRepository.save(deliveryTicketMapper.toEntity(deliveryTicketDTO)).map(deliveryTicketMapper::toDto);
    }

    @Override
    public Mono<DeliveryTicketDTO> update(DeliveryTicketDTO deliveryTicketDTO) {
        log.debug("Request to update DeliveryTicket : {}", deliveryTicketDTO);
        return deliveryTicketRepository.save(deliveryTicketMapper.toEntity(deliveryTicketDTO)).map(deliveryTicketMapper::toDto);
    }

    @Override
    public Mono<DeliveryTicketDTO> partialUpdate(DeliveryTicketDTO deliveryTicketDTO) {
        log.debug("Request to partially update DeliveryTicket : {}", deliveryTicketDTO);

        return deliveryTicketRepository
            .findById(deliveryTicketDTO.getDeliveryTicketId())
            .map(existingDeliveryTicket -> {
                deliveryTicketMapper.partialUpdate(existingDeliveryTicket, deliveryTicketDTO);

                return existingDeliveryTicket;
            })
            .flatMap(deliveryTicketRepository::save)
            .map(deliveryTicketMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<DeliveryTicketDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DeliveryTickets");
        return deliveryTicketRepository.findAllBy(pageable).map(deliveryTicketMapper::toDto);
    }

    public Mono<Long> countAll() {
        return deliveryTicketRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<DeliveryTicketDTO> findOne(Long id) {
        log.debug("Request to get DeliveryTicket : {}", id);
        return deliveryTicketRepository.findById(id).map(deliveryTicketMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete DeliveryTicket : {}", id);
        return deliveryTicketRepository.deleteById(id);
    }
}
