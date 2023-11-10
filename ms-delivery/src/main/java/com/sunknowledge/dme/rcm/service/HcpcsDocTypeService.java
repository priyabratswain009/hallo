package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.HcpcsDocTypeDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.HcpcsDocType}.
 */
public interface HcpcsDocTypeService {
    /**
     * Save a hcpcsDocType.
     *
     * @param hcpcsDocTypeDTO the entity to save.
     * @return the persisted entity.
     */
    HcpcsDocTypeDTO save(HcpcsDocTypeDTO hcpcsDocTypeDTO);

    /**
     * Updates a hcpcsDocType.
     *
     * @param hcpcsDocTypeDTO the entity to update.
     * @return the persisted entity.
     */
    HcpcsDocTypeDTO update(HcpcsDocTypeDTO hcpcsDocTypeDTO);

    /**
     * Partially updates a hcpcsDocType.
     *
     * @param hcpcsDocTypeDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<HcpcsDocTypeDTO> partialUpdate(HcpcsDocTypeDTO hcpcsDocTypeDTO);

    /**
     * Get all the hcpcsDocTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<HcpcsDocTypeDTO> findAll(Pageable pageable);

    /**
     * Get the "id" hcpcsDocType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<HcpcsDocTypeDTO> findOne(Long id);

    /**
     * Delete the "id" hcpcsDocType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
