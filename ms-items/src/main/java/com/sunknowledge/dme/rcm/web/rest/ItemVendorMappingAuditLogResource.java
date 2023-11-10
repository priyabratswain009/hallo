package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ItemVendorMappingAuditLogRepository;
import com.sunknowledge.dme.rcm.service.ItemVendorMappingAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.ItemVendorMappingAuditLogDTO;
import com.sunknowledge.dme.rcm.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ItemVendorMappingAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class ItemVendorMappingAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(ItemVendorMappingAuditLogResource.class);

    private static final String ENTITY_NAME = "itemsItemVendorMappingAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ItemVendorMappingAuditLogService itemVendorMappingAuditLogService;

    private final ItemVendorMappingAuditLogRepository itemVendorMappingAuditLogRepository;

    public ItemVendorMappingAuditLogResource(
        ItemVendorMappingAuditLogService itemVendorMappingAuditLogService,
        ItemVendorMappingAuditLogRepository itemVendorMappingAuditLogRepository
    ) {
        this.itemVendorMappingAuditLogService = itemVendorMappingAuditLogService;
        this.itemVendorMappingAuditLogRepository = itemVendorMappingAuditLogRepository;
    }

    /**
     * {@code POST  /item-vendor-mapping-audit-logs} : Create a new itemVendorMappingAuditLog.
     *
     * @param itemVendorMappingAuditLogDTO the itemVendorMappingAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new itemVendorMappingAuditLogDTO, or with status {@code 400 (Bad Request)} if the itemVendorMappingAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/item-vendor-mapping-audit-logs")
    public ResponseEntity<ItemVendorMappingAuditLogDTO> createItemVendorMappingAuditLog(
        @RequestBody ItemVendorMappingAuditLogDTO itemVendorMappingAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to save ItemVendorMappingAuditLog : {}", itemVendorMappingAuditLogDTO);
        if (itemVendorMappingAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new itemVendorMappingAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemVendorMappingAuditLogDTO result = itemVendorMappingAuditLogService.save(itemVendorMappingAuditLogDTO);
        return ResponseEntity
            .created(new URI("/api/item-vendor-mapping-audit-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /item-vendor-mapping-audit-logs/:id} : Updates an existing itemVendorMappingAuditLog.
     *
     * @param id the id of the itemVendorMappingAuditLogDTO to save.
     * @param itemVendorMappingAuditLogDTO the itemVendorMappingAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemVendorMappingAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the itemVendorMappingAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the itemVendorMappingAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/item-vendor-mapping-audit-logs/{id}")
    public ResponseEntity<ItemVendorMappingAuditLogDTO> updateItemVendorMappingAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ItemVendorMappingAuditLogDTO itemVendorMappingAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ItemVendorMappingAuditLog : {}, {}", id, itemVendorMappingAuditLogDTO);
        if (itemVendorMappingAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, itemVendorMappingAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!itemVendorMappingAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ItemVendorMappingAuditLogDTO result = itemVendorMappingAuditLogService.update(itemVendorMappingAuditLogDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, itemVendorMappingAuditLogDTO.getId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /item-vendor-mapping-audit-logs/:id} : Partial updates given fields of an existing itemVendorMappingAuditLog, field will ignore if it is null
     *
     * @param id the id of the itemVendorMappingAuditLogDTO to save.
     * @param itemVendorMappingAuditLogDTO the itemVendorMappingAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemVendorMappingAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the itemVendorMappingAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the itemVendorMappingAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the itemVendorMappingAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/item-vendor-mapping-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ItemVendorMappingAuditLogDTO> partialUpdateItemVendorMappingAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ItemVendorMappingAuditLogDTO itemVendorMappingAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ItemVendorMappingAuditLog partially : {}, {}", id, itemVendorMappingAuditLogDTO);
        if (itemVendorMappingAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, itemVendorMappingAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!itemVendorMappingAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ItemVendorMappingAuditLogDTO> result = itemVendorMappingAuditLogService.partialUpdate(itemVendorMappingAuditLogDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, itemVendorMappingAuditLogDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /item-vendor-mapping-audit-logs} : get all the itemVendorMappingAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of itemVendorMappingAuditLogs in body.
     */
    @GetMapping("/item-vendor-mapping-audit-logs")
    public ResponseEntity<List<ItemVendorMappingAuditLogDTO>> getAllItemVendorMappingAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ItemVendorMappingAuditLogs");
        Page<ItemVendorMappingAuditLogDTO> page = itemVendorMappingAuditLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /item-vendor-mapping-audit-logs/:id} : get the "id" itemVendorMappingAuditLog.
     *
     * @param id the id of the itemVendorMappingAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the itemVendorMappingAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/item-vendor-mapping-audit-logs/{id}")
    public ResponseEntity<ItemVendorMappingAuditLogDTO> getItemVendorMappingAuditLog(@PathVariable Long id) {
        log.debug("REST request to get ItemVendorMappingAuditLog : {}", id);
        Optional<ItemVendorMappingAuditLogDTO> itemVendorMappingAuditLogDTO = itemVendorMappingAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(itemVendorMappingAuditLogDTO);
    }

    /**
     * {@code DELETE  /item-vendor-mapping-audit-logs/:id} : delete the "id" itemVendorMappingAuditLog.
     *
     * @param id the id of the itemVendorMappingAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/item-vendor-mapping-audit-logs/{id}")
    public ResponseEntity<Void> deleteItemVendorMappingAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete ItemVendorMappingAuditLog : {}", id);
        itemVendorMappingAuditLogService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
