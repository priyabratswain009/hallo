package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.AddressVerificationResponseDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.AddressVerificationResponse}.
 */
public interface AddressVerificationResponseService {
    /**
     * Save a addressVerificationResponse.
     *
     * @param addressVerificationResponseDTO the entity to save.
     * @return the persisted entity.
     */
    AddressVerificationResponseDTO save(AddressVerificationResponseDTO addressVerificationResponseDTO);

    /**
     * Updates a addressVerificationResponse.
     *
     * @param addressVerificationResponseDTO the entity to update.
     * @return the persisted entity.
     */
    AddressVerificationResponseDTO update(AddressVerificationResponseDTO addressVerificationResponseDTO);

    /**
     * Partially updates a addressVerificationResponse.
     *
     * @param addressVerificationResponseDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<AddressVerificationResponseDTO> partialUpdate(AddressVerificationResponseDTO addressVerificationResponseDTO);

    /**
     * Get all the addressVerificationResponses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AddressVerificationResponseDTO> findAll(Pageable pageable);

    /**
     * Get the "id" addressVerificationResponse.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AddressVerificationResponseDTO> findOne(Long id);

    /**
     * Delete the "id" addressVerificationResponse.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
