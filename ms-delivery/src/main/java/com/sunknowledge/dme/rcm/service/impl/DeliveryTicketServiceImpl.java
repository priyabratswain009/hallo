package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.DeliveryTicket;
import com.sunknowledge.dme.rcm.repository.DeliveryTicketRepository;
import com.sunknowledge.dme.rcm.service.DeliveryTicketService;
import com.sunknowledge.dme.rcm.service.dto.DeliveryTicketDTO;
import com.sunknowledge.dme.rcm.service.mapper.DeliveryTicketMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public DeliveryTicketDTO save(DeliveryTicketDTO deliveryTicketDTO) {
        log.debug("Request to save DeliveryTicket : {}", deliveryTicketDTO);
        DeliveryTicket deliveryTicket = deliveryTicketMapper.toEntity(deliveryTicketDTO);
        deliveryTicket = deliveryTicketRepository.save(deliveryTicket);
        return deliveryTicketMapper.toDto(deliveryTicket);
    }

    @Override
    public DeliveryTicketDTO update(DeliveryTicketDTO deliveryTicketDTO) {
        log.debug("Request to save DeliveryTicket : {}", deliveryTicketDTO);
        DeliveryTicket deliveryTicket = deliveryTicketMapper.toEntity(deliveryTicketDTO);
        deliveryTicket = deliveryTicketRepository.save(deliveryTicket);
        return deliveryTicketMapper.toDto(deliveryTicket);
    }

    @Override
    public Optional<DeliveryTicketDTO> partialUpdate(DeliveryTicketDTO deliveryTicketDTO) {
        log.debug("Request to partially update DeliveryTicket : {}", deliveryTicketDTO);

        return deliveryTicketRepository
            .findById(deliveryTicketDTO.getDeliveryTicketId())
            .map(existingDeliveryTicket -> {
                deliveryTicketMapper.partialUpdate(existingDeliveryTicket, deliveryTicketDTO);

                return existingDeliveryTicket;
            })
            .map(deliveryTicketRepository::save)
            .map(deliveryTicketMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DeliveryTicketDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DeliveryTickets");
        return deliveryTicketRepository.findAll(pageable).map(deliveryTicketMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DeliveryTicketDTO> findOne(Long id) {
        log.debug("Request to get DeliveryTicket : {}", id);
        return deliveryTicketRepository.findById(id).map(deliveryTicketMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DeliveryTicket : {}", id);
        deliveryTicketRepository.deleteById(id);
    }
}
