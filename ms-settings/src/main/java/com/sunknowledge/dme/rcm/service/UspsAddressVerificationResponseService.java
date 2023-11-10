package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.UspsAddressVerificationResponseDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.UspsAddressVerificationResponse}.
 */
public interface UspsAddressVerificationResponseService {
    /**
     * Save a uspsAddressVerificationResponse.
     *
     * @param uspsAddressVerificationResponseDTO the entity to save.
     * @return the persisted entity.
     */
    UspsAddressVerificationResponseDTO save(UspsAddressVerificationResponseDTO uspsAddressVerificationResponseDTO);

    /**
     * Updates a uspsAddressVerificationResponse.
     *
     * @param uspsAddressVerificationResponseDTO the entity to update.
     * @return the persisted entity.
     */
    UspsAddressVerificationResponseDTO update(UspsAddressVerificationResponseDTO uspsAddressVerificationResponseDTO);

    /**
     * Partially updates a uspsAddressVerificationResponse.
     *
     * @param uspsAddressVerificationResponseDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<UspsAddressVerificationResponseDTO> partialUpdate(UspsAddressVerificationResponseDTO uspsAddressVerificationResponseDTO);

    /**
     * Get all the uspsAddressVerificationResponses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UspsAddressVerificationResponseDTO> findAll(Pageable pageable);

    /**
     * Get the "id" uspsAddressVerificationResponse.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UspsAddressVerificationResponseDTO> findOne(Long id);

    /**
     * Delete the "id" uspsAddressVerificationResponse.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
