package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ItemLocationAuditLogRepository;
import com.sunknowledge.dme.rcm.service.ItemLocationAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.ItemLocationAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ItemLocationAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class ItemLocationAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(ItemLocationAuditLogResource.class);

    private static final String ENTITY_NAME = "settingsItemLocationAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ItemLocationAuditLogService itemLocationAuditLogService;

    private final ItemLocationAuditLogRepository itemLocationAuditLogRepository;

    public ItemLocationAuditLogResource(
        ItemLocationAuditLogService itemLocationAuditLogService,
        ItemLocationAuditLogRepository itemLocationAuditLogRepository
    ) {
        this.itemLocationAuditLogService = itemLocationAuditLogService;
        this.itemLocationAuditLogRepository = itemLocationAuditLogRepository;
    }

    /**
     * {@code POST  /item-location-audit-logs} : Create a new itemLocationAuditLog.
     *
     * @param itemLocationAuditLogDTO the itemLocationAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new itemLocationAuditLogDTO, or with status {@code 400 (Bad Request)} if the itemLocationAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/item-location-audit-logs")
    public ResponseEntity<ItemLocationAuditLogDTO> createItemLocationAuditLog(@RequestBody ItemLocationAuditLogDTO itemLocationAuditLogDTO)
        throws URISyntaxException {
        log.debug("REST request to save ItemLocationAuditLog : {}", itemLocationAuditLogDTO);
        if (itemLocationAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new itemLocationAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemLocationAuditLogDTO result = itemLocationAuditLogService.save(itemLocationAuditLogDTO);
        return ResponseEntity
            .created(new URI("/api/item-location-audit-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /item-location-audit-logs/:id} : Updates an existing itemLocationAuditLog.
     *
     * @param id the id of the itemLocationAuditLogDTO to save.
     * @param itemLocationAuditLogDTO the itemLocationAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemLocationAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the itemLocationAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the itemLocationAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/item-location-audit-logs/{id}")
    public ResponseEntity<ItemLocationAuditLogDTO> updateItemLocationAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ItemLocationAuditLogDTO itemLocationAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ItemLocationAuditLog : {}, {}", id, itemLocationAuditLogDTO);
        if (itemLocationAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, itemLocationAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!itemLocationAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ItemLocationAuditLogDTO result = itemLocationAuditLogService.update(itemLocationAuditLogDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, itemLocationAuditLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /item-location-audit-logs/:id} : Partial updates given fields of an existing itemLocationAuditLog, field will ignore if it is null
     *
     * @param id the id of the itemLocationAuditLogDTO to save.
     * @param itemLocationAuditLogDTO the itemLocationAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemLocationAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the itemLocationAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the itemLocationAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the itemLocationAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/item-location-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ItemLocationAuditLogDTO> partialUpdateItemLocationAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ItemLocationAuditLogDTO itemLocationAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ItemLocationAuditLog partially : {}, {}", id, itemLocationAuditLogDTO);
        if (itemLocationAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, itemLocationAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!itemLocationAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ItemLocationAuditLogDTO> result = itemLocationAuditLogService.partialUpdate(itemLocationAuditLogDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, itemLocationAuditLogDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /item-location-audit-logs} : get all the itemLocationAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of itemLocationAuditLogs in body.
     */
    @GetMapping("/item-location-audit-logs")
    public ResponseEntity<List<ItemLocationAuditLogDTO>> getAllItemLocationAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ItemLocationAuditLogs");
        Page<ItemLocationAuditLogDTO> page = itemLocationAuditLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /item-location-audit-logs/:id} : get the "id" itemLocationAuditLog.
     *
     * @param id the id of the itemLocationAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the itemLocationAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/item-location-audit-logs/{id}")
    public ResponseEntity<ItemLocationAuditLogDTO> getItemLocationAuditLog(@PathVariable Long id) {
        log.debug("REST request to get ItemLocationAuditLog : {}", id);
        Optional<ItemLocationAuditLogDTO> itemLocationAuditLogDTO = itemLocationAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(itemLocationAuditLogDTO);
    }

    /**
     * {@code DELETE  /item-location-audit-logs/:id} : delete the "id" itemLocationAuditLog.
     *
     * @param id the id of the itemLocationAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/item-location-audit-logs/{id}")
    public ResponseEntity<Void> deleteItemLocationAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete ItemLocationAuditLog : {}", id);
        itemLocationAuditLogService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
