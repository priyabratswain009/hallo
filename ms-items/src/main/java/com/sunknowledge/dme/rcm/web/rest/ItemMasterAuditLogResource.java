package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ItemMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.ItemMasterAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.ItemMasterAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ItemMasterAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class ItemMasterAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(ItemMasterAuditLogResource.class);

    private static final String ENTITY_NAME = "itemsItemMasterAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ItemMasterAuditLogService itemMasterAuditLogService;

    private final ItemMasterAuditLogRepository itemMasterAuditLogRepository;

    public ItemMasterAuditLogResource(
        ItemMasterAuditLogService itemMasterAuditLogService,
        ItemMasterAuditLogRepository itemMasterAuditLogRepository
    ) {
        this.itemMasterAuditLogService = itemMasterAuditLogService;
        this.itemMasterAuditLogRepository = itemMasterAuditLogRepository;
    }

    /**
     * {@code POST  /item-master-audit-logs} : Create a new itemMasterAuditLog.
     *
     * @param itemMasterAuditLogDTO the itemMasterAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new itemMasterAuditLogDTO, or with status {@code 400 (Bad Request)} if the itemMasterAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/item-master-audit-logs")
    public ResponseEntity<ItemMasterAuditLogDTO> createItemMasterAuditLog(@RequestBody ItemMasterAuditLogDTO itemMasterAuditLogDTO)
        throws URISyntaxException {
        log.debug("REST request to save ItemMasterAuditLog : {}", itemMasterAuditLogDTO);
        if (itemMasterAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new itemMasterAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemMasterAuditLogDTO result = itemMasterAuditLogService.save(itemMasterAuditLogDTO);
        return ResponseEntity
            .created(new URI("/api/item-master-audit-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /item-master-audit-logs/:id} : Updates an existing itemMasterAuditLog.
     *
     * @param id the id of the itemMasterAuditLogDTO to save.
     * @param itemMasterAuditLogDTO the itemMasterAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemMasterAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the itemMasterAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the itemMasterAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/item-master-audit-logs/{id}")
    public ResponseEntity<ItemMasterAuditLogDTO> updateItemMasterAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ItemMasterAuditLogDTO itemMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ItemMasterAuditLog : {}, {}", id, itemMasterAuditLogDTO);
        if (itemMasterAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, itemMasterAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!itemMasterAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ItemMasterAuditLogDTO result = itemMasterAuditLogService.update(itemMasterAuditLogDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, itemMasterAuditLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /item-master-audit-logs/:id} : Partial updates given fields of an existing itemMasterAuditLog, field will ignore if it is null
     *
     * @param id the id of the itemMasterAuditLogDTO to save.
     * @param itemMasterAuditLogDTO the itemMasterAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemMasterAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the itemMasterAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the itemMasterAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the itemMasterAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/item-master-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ItemMasterAuditLogDTO> partialUpdateItemMasterAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ItemMasterAuditLogDTO itemMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ItemMasterAuditLog partially : {}, {}", id, itemMasterAuditLogDTO);
        if (itemMasterAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, itemMasterAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!itemMasterAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ItemMasterAuditLogDTO> result = itemMasterAuditLogService.partialUpdate(itemMasterAuditLogDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, itemMasterAuditLogDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /item-master-audit-logs} : get all the itemMasterAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of itemMasterAuditLogs in body.
     */
    @GetMapping("/item-master-audit-logs")
    public ResponseEntity<List<ItemMasterAuditLogDTO>> getAllItemMasterAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ItemMasterAuditLogs");
        Page<ItemMasterAuditLogDTO> page = itemMasterAuditLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /item-master-audit-logs/:id} : get the "id" itemMasterAuditLog.
     *
     * @param id the id of the itemMasterAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the itemMasterAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/item-master-audit-logs/{id}")
    public ResponseEntity<ItemMasterAuditLogDTO> getItemMasterAuditLog(@PathVariable Long id) {
        log.debug("REST request to get ItemMasterAuditLog : {}", id);
        Optional<ItemMasterAuditLogDTO> itemMasterAuditLogDTO = itemMasterAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(itemMasterAuditLogDTO);
    }

    /**
     * {@code DELETE  /item-master-audit-logs/:id} : delete the "id" itemMasterAuditLog.
     *
     * @param id the id of the itemMasterAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/item-master-audit-logs/{id}")
    public ResponseEntity<Void> deleteItemMasterAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete ItemMasterAuditLog : {}", id);
        itemMasterAuditLogService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
