package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.DeliveryDocumentsSignatureDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.DeliveryDocumentsSignature}.
 */
public interface DeliveryDocumentsSignatureService {
    /**
     * Save a deliveryDocumentsSignature.
     *
     * @param deliveryDocumentsSignatureDTO the entity to save.
     * @return the persisted entity.
     */
    DeliveryDocumentsSignatureDTO save(DeliveryDocumentsSignatureDTO deliveryDocumentsSignatureDTO);

    /**
     * Updates a deliveryDocumentsSignature.
     *
     * @param deliveryDocumentsSignatureDTO the entity to update.
     * @return the persisted entity.
     */
    DeliveryDocumentsSignatureDTO update(DeliveryDocumentsSignatureDTO deliveryDocumentsSignatureDTO);

    /**
     * Partially updates a deliveryDocumentsSignature.
     *
     * @param deliveryDocumentsSignatureDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DeliveryDocumentsSignatureDTO> partialUpdate(DeliveryDocumentsSignatureDTO deliveryDocumentsSignatureDTO);

    /**
     * Get all the deliveryDocumentsSignatures.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DeliveryDocumentsSignatureDTO> findAll(Pageable pageable);

    /**
     * Get the "id" deliveryDocumentsSignature.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DeliveryDocumentsSignatureDTO> findOne(Long id);

    /**
     * Delete the "id" deliveryDocumentsSignature.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
