package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.DeliveryAbnDataDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.DeliveryAbnData}.
 */
public interface DeliveryAbnDataService {
    /**
     * Save a deliveryAbnData.
     *
     * @param deliveryAbnDataDTO the entity to save.
     * @return the persisted entity.
     */
    DeliveryAbnDataDTO save(DeliveryAbnDataDTO deliveryAbnDataDTO);

    /**
     * Updates a deliveryAbnData.
     *
     * @param deliveryAbnDataDTO the entity to update.
     * @return the persisted entity.
     */
    DeliveryAbnDataDTO update(DeliveryAbnDataDTO deliveryAbnDataDTO);

    /**
     * Partially updates a deliveryAbnData.
     *
     * @param deliveryAbnDataDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DeliveryAbnDataDTO> partialUpdate(DeliveryAbnDataDTO deliveryAbnDataDTO);

    /**
     * Get all the deliveryAbnData.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DeliveryAbnDataDTO> findAll(Pageable pageable);

    /**
     * Get the "id" deliveryAbnData.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DeliveryAbnDataDTO> findOne(Long id);

    /**
     * Delete the "id" deliveryAbnData.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
