package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ItemGroupAuditLogRepository;
import com.sunknowledge.dme.rcm.service.ItemGroupAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.ItemGroupAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ItemGroupAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class ItemGroupAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(ItemGroupAuditLogResource.class);

    private static final String ENTITY_NAME = "itemsItemGroupAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ItemGroupAuditLogService itemGroupAuditLogService;

    private final ItemGroupAuditLogRepository itemGroupAuditLogRepository;

    public ItemGroupAuditLogResource(
        ItemGroupAuditLogService itemGroupAuditLogService,
        ItemGroupAuditLogRepository itemGroupAuditLogRepository
    ) {
        this.itemGroupAuditLogService = itemGroupAuditLogService;
        this.itemGroupAuditLogRepository = itemGroupAuditLogRepository;
    }

    /**
     * {@code POST  /item-group-audit-logs} : Create a new itemGroupAuditLog.
     *
     * @param itemGroupAuditLogDTO the itemGroupAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new itemGroupAuditLogDTO, or with status {@code 400 (Bad Request)} if the itemGroupAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/item-group-audit-logs")
    public ResponseEntity<ItemGroupAuditLogDTO> createItemGroupAuditLog(@RequestBody ItemGroupAuditLogDTO itemGroupAuditLogDTO)
        throws URISyntaxException {
        log.debug("REST request to save ItemGroupAuditLog : {}", itemGroupAuditLogDTO);
        if (itemGroupAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new itemGroupAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemGroupAuditLogDTO result = itemGroupAuditLogService.save(itemGroupAuditLogDTO);
        return ResponseEntity
            .created(new URI("/api/item-group-audit-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /item-group-audit-logs/:id} : Updates an existing itemGroupAuditLog.
     *
     * @param id the id of the itemGroupAuditLogDTO to save.
     * @param itemGroupAuditLogDTO the itemGroupAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemGroupAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the itemGroupAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the itemGroupAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/item-group-audit-logs/{id}")
    public ResponseEntity<ItemGroupAuditLogDTO> updateItemGroupAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ItemGroupAuditLogDTO itemGroupAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ItemGroupAuditLog : {}, {}", id, itemGroupAuditLogDTO);
        if (itemGroupAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, itemGroupAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!itemGroupAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ItemGroupAuditLogDTO result = itemGroupAuditLogService.update(itemGroupAuditLogDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, itemGroupAuditLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /item-group-audit-logs/:id} : Partial updates given fields of an existing itemGroupAuditLog, field will ignore if it is null
     *
     * @param id the id of the itemGroupAuditLogDTO to save.
     * @param itemGroupAuditLogDTO the itemGroupAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemGroupAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the itemGroupAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the itemGroupAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the itemGroupAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/item-group-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ItemGroupAuditLogDTO> partialUpdateItemGroupAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ItemGroupAuditLogDTO itemGroupAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ItemGroupAuditLog partially : {}, {}", id, itemGroupAuditLogDTO);
        if (itemGroupAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, itemGroupAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!itemGroupAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ItemGroupAuditLogDTO> result = itemGroupAuditLogService.partialUpdate(itemGroupAuditLogDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, itemGroupAuditLogDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /item-group-audit-logs} : get all the itemGroupAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of itemGroupAuditLogs in body.
     */
    @GetMapping("/item-group-audit-logs")
    public ResponseEntity<List<ItemGroupAuditLogDTO>> getAllItemGroupAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ItemGroupAuditLogs");
        Page<ItemGroupAuditLogDTO> page = itemGroupAuditLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /item-group-audit-logs/:id} : get the "id" itemGroupAuditLog.
     *
     * @param id the id of the itemGroupAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the itemGroupAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/item-group-audit-logs/{id}")
    public ResponseEntity<ItemGroupAuditLogDTO> getItemGroupAuditLog(@PathVariable Long id) {
        log.debug("REST request to get ItemGroupAuditLog : {}", id);
        Optional<ItemGroupAuditLogDTO> itemGroupAuditLogDTO = itemGroupAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(itemGroupAuditLogDTO);
    }

    /**
     * {@code DELETE  /item-group-audit-logs/:id} : delete the "id" itemGroupAuditLog.
     *
     * @param id the id of the itemGroupAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/item-group-audit-logs/{id}")
    public ResponseEntity<Void> deleteItemGroupAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete ItemGroupAuditLog : {}", id);
        itemGroupAuditLogService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
