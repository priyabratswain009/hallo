package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.DeliveryAssignment;
import com.sunknowledge.dme.rcm.repository.DeliveryAssignmentRepository;
import com.sunknowledge.dme.rcm.service.DeliveryAssignmentService;
import com.sunknowledge.dme.rcm.service.dto.DeliveryAssignmentDTO;
import com.sunknowledge.dme.rcm.service.mapper.DeliveryAssignmentMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link DeliveryAssignment}.
 */
@Service
@Transactional
public class DeliveryAssignmentServiceImpl implements DeliveryAssignmentService {

    private final Logger log = LoggerFactory.getLogger(DeliveryAssignmentServiceImpl.class);

    private final DeliveryAssignmentRepository deliveryAssignmentRepository;

    private final DeliveryAssignmentMapper deliveryAssignmentMapper;

    public DeliveryAssignmentServiceImpl(
        DeliveryAssignmentRepository deliveryAssignmentRepository,
        DeliveryAssignmentMapper deliveryAssignmentMapper
    ) {
        this.deliveryAssignmentRepository = deliveryAssignmentRepository;
        this.deliveryAssignmentMapper = deliveryAssignmentMapper;
    }

    @Override
    public DeliveryAssignmentDTO save(DeliveryAssignmentDTO deliveryAssignmentDTO) {
        log.debug("Request to save DeliveryAssignment : {}", deliveryAssignmentDTO);
        DeliveryAssignment deliveryAssignment = deliveryAssignmentMapper.toEntity(deliveryAssignmentDTO);
        deliveryAssignment = deliveryAssignmentRepository.save(deliveryAssignment);
        return deliveryAssignmentMapper.toDto(deliveryAssignment);
    }

    @Override
    public DeliveryAssignmentDTO update(DeliveryAssignmentDTO deliveryAssignmentDTO) {
        log.debug("Request to save DeliveryAssignment : {}", deliveryAssignmentDTO);
        DeliveryAssignment deliveryAssignment = deliveryAssignmentMapper.toEntity(deliveryAssignmentDTO);
        deliveryAssignment = deliveryAssignmentRepository.save(deliveryAssignment);
        return deliveryAssignmentMapper.toDto(deliveryAssignment);
    }

    @Override
    public Optional<DeliveryAssignmentDTO> partialUpdate(DeliveryAssignmentDTO deliveryAssignmentDTO) {
        log.debug("Request to partially update DeliveryAssignment : {}", deliveryAssignmentDTO);

        return deliveryAssignmentRepository
            .findById(deliveryAssignmentDTO.getDeliveryAssignmentId())
            .map(existingDeliveryAssignment -> {
                deliveryAssignmentMapper.partialUpdate(existingDeliveryAssignment, deliveryAssignmentDTO);

                return existingDeliveryAssignment;
            })
            .map(deliveryAssignmentRepository::save)
            .map(deliveryAssignmentMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DeliveryAssignmentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DeliveryAssignments");
        return deliveryAssignmentRepository.findAll(pageable).map(deliveryAssignmentMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DeliveryAssignmentDTO> findOne(Long id) {
        log.debug("Request to get DeliveryAssignment : {}", id);
        return deliveryAssignmentRepository.findById(id).map(deliveryAssignmentMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DeliveryAssignment : {}", id);
        deliveryAssignmentRepository.deleteById(id);
    }
}
