package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ItemInventoryStatusAuditLogRepository;
import com.sunknowledge.dme.rcm.service.ItemInventoryStatusAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.ItemInventoryStatusAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ItemInventoryStatusAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class ItemInventoryStatusAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(ItemInventoryStatusAuditLogResource.class);

    private static final String ENTITY_NAME = "itemsItemInventoryStatusAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ItemInventoryStatusAuditLogService itemInventoryStatusAuditLogService;

    private final ItemInventoryStatusAuditLogRepository itemInventoryStatusAuditLogRepository;

    public ItemInventoryStatusAuditLogResource(
        ItemInventoryStatusAuditLogService itemInventoryStatusAuditLogService,
        ItemInventoryStatusAuditLogRepository itemInventoryStatusAuditLogRepository
    ) {
        this.itemInventoryStatusAuditLogService = itemInventoryStatusAuditLogService;
        this.itemInventoryStatusAuditLogRepository = itemInventoryStatusAuditLogRepository;
    }

    /**
     * {@code POST  /item-inventory-status-audit-logs} : Create a new itemInventoryStatusAuditLog.
     *
     * @param itemInventoryStatusAuditLogDTO the itemInventoryStatusAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new itemInventoryStatusAuditLogDTO, or with status {@code 400 (Bad Request)} if the itemInventoryStatusAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/item-inventory-status-audit-logs")
    public ResponseEntity<ItemInventoryStatusAuditLogDTO> createItemInventoryStatusAuditLog(
        @RequestBody ItemInventoryStatusAuditLogDTO itemInventoryStatusAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to save ItemInventoryStatusAuditLog : {}", itemInventoryStatusAuditLogDTO);
        if (itemInventoryStatusAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new itemInventoryStatusAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemInventoryStatusAuditLogDTO result = itemInventoryStatusAuditLogService.save(itemInventoryStatusAuditLogDTO);
        return ResponseEntity
            .created(new URI("/api/item-inventory-status-audit-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /item-inventory-status-audit-logs/:id} : Updates an existing itemInventoryStatusAuditLog.
     *
     * @param id the id of the itemInventoryStatusAuditLogDTO to save.
     * @param itemInventoryStatusAuditLogDTO the itemInventoryStatusAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemInventoryStatusAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the itemInventoryStatusAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the itemInventoryStatusAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/item-inventory-status-audit-logs/{id}")
    public ResponseEntity<ItemInventoryStatusAuditLogDTO> updateItemInventoryStatusAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ItemInventoryStatusAuditLogDTO itemInventoryStatusAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ItemInventoryStatusAuditLog : {}, {}", id, itemInventoryStatusAuditLogDTO);
        if (itemInventoryStatusAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, itemInventoryStatusAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!itemInventoryStatusAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ItemInventoryStatusAuditLogDTO result = itemInventoryStatusAuditLogService.update(itemInventoryStatusAuditLogDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, itemInventoryStatusAuditLogDTO.getId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /item-inventory-status-audit-logs/:id} : Partial updates given fields of an existing itemInventoryStatusAuditLog, field will ignore if it is null
     *
     * @param id the id of the itemInventoryStatusAuditLogDTO to save.
     * @param itemInventoryStatusAuditLogDTO the itemInventoryStatusAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemInventoryStatusAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the itemInventoryStatusAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the itemInventoryStatusAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the itemInventoryStatusAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/item-inventory-status-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ItemInventoryStatusAuditLogDTO> partialUpdateItemInventoryStatusAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ItemInventoryStatusAuditLogDTO itemInventoryStatusAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ItemInventoryStatusAuditLog partially : {}, {}", id, itemInventoryStatusAuditLogDTO);
        if (itemInventoryStatusAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, itemInventoryStatusAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!itemInventoryStatusAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ItemInventoryStatusAuditLogDTO> result = itemInventoryStatusAuditLogService.partialUpdate(itemInventoryStatusAuditLogDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, itemInventoryStatusAuditLogDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /item-inventory-status-audit-logs} : get all the itemInventoryStatusAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of itemInventoryStatusAuditLogs in body.
     */
    @GetMapping("/item-inventory-status-audit-logs")
    public ResponseEntity<List<ItemInventoryStatusAuditLogDTO>> getAllItemInventoryStatusAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ItemInventoryStatusAuditLogs");
        Page<ItemInventoryStatusAuditLogDTO> page = itemInventoryStatusAuditLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /item-inventory-status-audit-logs/:id} : get the "id" itemInventoryStatusAuditLog.
     *
     * @param id the id of the itemInventoryStatusAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the itemInventoryStatusAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/item-inventory-status-audit-logs/{id}")
    public ResponseEntity<ItemInventoryStatusAuditLogDTO> getItemInventoryStatusAuditLog(@PathVariable Long id) {
        log.debug("REST request to get ItemInventoryStatusAuditLog : {}", id);
        Optional<ItemInventoryStatusAuditLogDTO> itemInventoryStatusAuditLogDTO = itemInventoryStatusAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(itemInventoryStatusAuditLogDTO);
    }

    /**
     * {@code DELETE  /item-inventory-status-audit-logs/:id} : delete the "id" itemInventoryStatusAuditLog.
     *
     * @param id the id of the itemInventoryStatusAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/item-inventory-status-audit-logs/{id}")
    public ResponseEntity<Void> deleteItemInventoryStatusAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete ItemInventoryStatusAuditLog : {}", id);
        itemInventoryStatusAuditLogService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
