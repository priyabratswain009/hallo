package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.DeliveryDocumentsDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.DeliveryDocuments}.
 */
public interface DeliveryDocumentsService {
    /**
     * Save a deliveryDocuments.
     *
     * @param deliveryDocumentsDTO the entity to save.
     * @return the persisted entity.
     */
    DeliveryDocumentsDTO save(DeliveryDocumentsDTO deliveryDocumentsDTO);

    /**
     * Updates a deliveryDocuments.
     *
     * @param deliveryDocumentsDTO the entity to update.
     * @return the persisted entity.
     */
    DeliveryDocumentsDTO update(DeliveryDocumentsDTO deliveryDocumentsDTO);

    /**
     * Partially updates a deliveryDocuments.
     *
     * @param deliveryDocumentsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DeliveryDocumentsDTO> partialUpdate(DeliveryDocumentsDTO deliveryDocumentsDTO);

    /**
     * Get all the deliveryDocuments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DeliveryDocumentsDTO> findAll(Pageable pageable);

    /**
     * Get the "id" deliveryDocuments.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DeliveryDocumentsDTO> findOne(Long id);

    /**
     * Delete the "id" deliveryDocuments.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
