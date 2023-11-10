package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.DeliveryAssignmentDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.DeliveryAssignment}.
 */
public interface DeliveryAssignmentService {
    /**
     * Save a deliveryAssignment.
     *
     * @param deliveryAssignmentDTO the entity to save.
     * @return the persisted entity.
     */
    DeliveryAssignmentDTO save(DeliveryAssignmentDTO deliveryAssignmentDTO);

    /**
     * Updates a deliveryAssignment.
     *
     * @param deliveryAssignmentDTO the entity to update.
     * @return the persisted entity.
     */
    DeliveryAssignmentDTO update(DeliveryAssignmentDTO deliveryAssignmentDTO);

    /**
     * Partially updates a deliveryAssignment.
     *
     * @param deliveryAssignmentDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DeliveryAssignmentDTO> partialUpdate(DeliveryAssignmentDTO deliveryAssignmentDTO);

    /**
     * Get all the deliveryAssignments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DeliveryAssignmentDTO> findAll(Pageable pageable);

    /**
     * Get the "id" deliveryAssignment.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DeliveryAssignmentDTO> findOne(Long id);

    /**
     * Delete the "id" deliveryAssignment.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
