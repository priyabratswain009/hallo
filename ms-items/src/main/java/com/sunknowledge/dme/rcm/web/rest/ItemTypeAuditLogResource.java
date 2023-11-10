package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ItemTypeAuditLogRepository;
import com.sunknowledge.dme.rcm.service.ItemTypeAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.ItemTypeAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ItemTypeAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class ItemTypeAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(ItemTypeAuditLogResource.class);

    private static final String ENTITY_NAME = "itemsItemTypeAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ItemTypeAuditLogService itemTypeAuditLogService;

    private final ItemTypeAuditLogRepository itemTypeAuditLogRepository;

    public ItemTypeAuditLogResource(
        ItemTypeAuditLogService itemTypeAuditLogService,
        ItemTypeAuditLogRepository itemTypeAuditLogRepository
    ) {
        this.itemTypeAuditLogService = itemTypeAuditLogService;
        this.itemTypeAuditLogRepository = itemTypeAuditLogRepository;
    }

    /**
     * {@code POST  /item-type-audit-logs} : Create a new itemTypeAuditLog.
     *
     * @param itemTypeAuditLogDTO the itemTypeAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new itemTypeAuditLogDTO, or with status {@code 400 (Bad Request)} if the itemTypeAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/item-type-audit-logs")
    public ResponseEntity<ItemTypeAuditLogDTO> createItemTypeAuditLog(@RequestBody ItemTypeAuditLogDTO itemTypeAuditLogDTO)
        throws URISyntaxException {
        log.debug("REST request to save ItemTypeAuditLog : {}", itemTypeAuditLogDTO);
        if (itemTypeAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new itemTypeAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemTypeAuditLogDTO result = itemTypeAuditLogService.save(itemTypeAuditLogDTO);
        return ResponseEntity
            .created(new URI("/api/item-type-audit-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /item-type-audit-logs/:id} : Updates an existing itemTypeAuditLog.
     *
     * @param id the id of the itemTypeAuditLogDTO to save.
     * @param itemTypeAuditLogDTO the itemTypeAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemTypeAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the itemTypeAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the itemTypeAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/item-type-audit-logs/{id}")
    public ResponseEntity<ItemTypeAuditLogDTO> updateItemTypeAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ItemTypeAuditLogDTO itemTypeAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ItemTypeAuditLog : {}, {}", id, itemTypeAuditLogDTO);
        if (itemTypeAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, itemTypeAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!itemTypeAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ItemTypeAuditLogDTO result = itemTypeAuditLogService.update(itemTypeAuditLogDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, itemTypeAuditLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /item-type-audit-logs/:id} : Partial updates given fields of an existing itemTypeAuditLog, field will ignore if it is null
     *
     * @param id the id of the itemTypeAuditLogDTO to save.
     * @param itemTypeAuditLogDTO the itemTypeAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemTypeAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the itemTypeAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the itemTypeAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the itemTypeAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/item-type-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ItemTypeAuditLogDTO> partialUpdateItemTypeAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ItemTypeAuditLogDTO itemTypeAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ItemTypeAuditLog partially : {}, {}", id, itemTypeAuditLogDTO);
        if (itemTypeAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, itemTypeAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!itemTypeAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ItemTypeAuditLogDTO> result = itemTypeAuditLogService.partialUpdate(itemTypeAuditLogDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, itemTypeAuditLogDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /item-type-audit-logs} : get all the itemTypeAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of itemTypeAuditLogs in body.
     */
    @GetMapping("/item-type-audit-logs")
    public ResponseEntity<List<ItemTypeAuditLogDTO>> getAllItemTypeAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ItemTypeAuditLogs");
        Page<ItemTypeAuditLogDTO> page = itemTypeAuditLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /item-type-audit-logs/:id} : get the "id" itemTypeAuditLog.
     *
     * @param id the id of the itemTypeAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the itemTypeAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/item-type-audit-logs/{id}")
    public ResponseEntity<ItemTypeAuditLogDTO> getItemTypeAuditLog(@PathVariable Long id) {
        log.debug("REST request to get ItemTypeAuditLog : {}", id);
        Optional<ItemTypeAuditLogDTO> itemTypeAuditLogDTO = itemTypeAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(itemTypeAuditLogDTO);
    }

    /**
     * {@code DELETE  /item-type-audit-logs/:id} : delete the "id" itemTypeAuditLog.
     *
     * @param id the id of the itemTypeAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/item-type-audit-logs/{id}")
    public ResponseEntity<Void> deleteItemTypeAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete ItemTypeAuditLog : {}", id);
        itemTypeAuditLogService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
