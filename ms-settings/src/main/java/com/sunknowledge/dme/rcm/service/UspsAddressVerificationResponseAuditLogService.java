package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.UspsAddressVerificationResponseAuditLogDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.UspsAddressVerificationResponseAuditLog}.
 */
public interface UspsAddressVerificationResponseAuditLogService {
    /**
     * Save a uspsAddressVerificationResponseAuditLog.
     *
     * @param uspsAddressVerificationResponseAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    UspsAddressVerificationResponseAuditLogDTO save(UspsAddressVerificationResponseAuditLogDTO uspsAddressVerificationResponseAuditLogDTO);

    /**
     * Updates a uspsAddressVerificationResponseAuditLog.
     *
     * @param uspsAddressVerificationResponseAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    UspsAddressVerificationResponseAuditLogDTO update(
        UspsAddressVerificationResponseAuditLogDTO uspsAddressVerificationResponseAuditLogDTO
    );

    /**
     * Partially updates a uspsAddressVerificationResponseAuditLog.
     *
     * @param uspsAddressVerificationResponseAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<UspsAddressVerificationResponseAuditLogDTO> partialUpdate(
        UspsAddressVerificationResponseAuditLogDTO uspsAddressVerificationResponseAuditLogDTO
    );

    /**
     * Get all the uspsAddressVerificationResponseAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UspsAddressVerificationResponseAuditLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" uspsAddressVerificationResponseAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UspsAddressVerificationResponseAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" uspsAddressVerificationResponseAuditLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
