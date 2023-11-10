package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.DeliveryPatientCommunicationsDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.DeliveryPatientCommunications}.
 */
public interface DeliveryPatientCommunicationsService {
    /**
     * Save a deliveryPatientCommunications.
     *
     * @param deliveryPatientCommunicationsDTO the entity to save.
     * @return the persisted entity.
     */
    DeliveryPatientCommunicationsDTO save(DeliveryPatientCommunicationsDTO deliveryPatientCommunicationsDTO);

    /**
     * Updates a deliveryPatientCommunications.
     *
     * @param deliveryPatientCommunicationsDTO the entity to update.
     * @return the persisted entity.
     */
    DeliveryPatientCommunicationsDTO update(DeliveryPatientCommunicationsDTO deliveryPatientCommunicationsDTO);

    /**
     * Partially updates a deliveryPatientCommunications.
     *
     * @param deliveryPatientCommunicationsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DeliveryPatientCommunicationsDTO> partialUpdate(DeliveryPatientCommunicationsDTO deliveryPatientCommunicationsDTO);

    /**
     * Get all the deliveryPatientCommunications.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DeliveryPatientCommunicationsDTO> findAll(Pageable pageable);

    /**
     * Get the "id" deliveryPatientCommunications.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DeliveryPatientCommunicationsDTO> findOne(Long id);

    /**
     * Delete the "id" deliveryPatientCommunications.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
