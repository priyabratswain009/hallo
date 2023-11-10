package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ItemVendorMappingAuditLogDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.ItemVendorMappingAuditLog}.
 */
public interface ItemVendorMappingAuditLogService {
    /**
     * Save a itemVendorMappingAuditLog.
     *
     * @param itemVendorMappingAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    ItemVendorMappingAuditLogDTO save(ItemVendorMappingAuditLogDTO itemVendorMappingAuditLogDTO);

    /**
     * Updates a itemVendorMappingAuditLog.
     *
     * @param itemVendorMappingAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    ItemVendorMappingAuditLogDTO update(ItemVendorMappingAuditLogDTO itemVendorMappingAuditLogDTO);

    /**
     * Partially updates a itemVendorMappingAuditLog.
     *
     * @param itemVendorMappingAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ItemVendorMappingAuditLogDTO> partialUpdate(ItemVendorMappingAuditLogDTO itemVendorMappingAuditLogDTO);

    /**
     * Get all the itemVendorMappingAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ItemVendorMappingAuditLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" itemVendorMappingAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ItemVendorMappingAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" itemVendorMappingAuditLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
