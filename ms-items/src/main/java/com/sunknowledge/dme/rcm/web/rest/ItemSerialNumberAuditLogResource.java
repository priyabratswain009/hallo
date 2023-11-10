package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ItemSerialNumberAuditLogRepository;
import com.sunknowledge.dme.rcm.service.ItemSerialNumberAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.ItemSerialNumberAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ItemSerialNumberAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class ItemSerialNumberAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(ItemSerialNumberAuditLogResource.class);

    private static final String ENTITY_NAME = "itemsItemSerialNumberAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ItemSerialNumberAuditLogService itemSerialNumberAuditLogService;

    private final ItemSerialNumberAuditLogRepository itemSerialNumberAuditLogRepository;

    public ItemSerialNumberAuditLogResource(
        ItemSerialNumberAuditLogService itemSerialNumberAuditLogService,
        ItemSerialNumberAuditLogRepository itemSerialNumberAuditLogRepository
    ) {
        this.itemSerialNumberAuditLogService = itemSerialNumberAuditLogService;
        this.itemSerialNumberAuditLogRepository = itemSerialNumberAuditLogRepository;
    }

    /**
     * {@code POST  /item-serial-number-audit-logs} : Create a new itemSerialNumberAuditLog.
     *
     * @param itemSerialNumberAuditLogDTO the itemSerialNumberAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new itemSerialNumberAuditLogDTO, or with status {@code 400 (Bad Request)} if the itemSerialNumberAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/item-serial-number-audit-logs")
    public ResponseEntity<ItemSerialNumberAuditLogDTO> createItemSerialNumberAuditLog(
        @RequestBody ItemSerialNumberAuditLogDTO itemSerialNumberAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to save ItemSerialNumberAuditLog : {}", itemSerialNumberAuditLogDTO);
        if (itemSerialNumberAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new itemSerialNumberAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemSerialNumberAuditLogDTO result = itemSerialNumberAuditLogService.save(itemSerialNumberAuditLogDTO);
        return ResponseEntity
            .created(new URI("/api/item-serial-number-audit-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /item-serial-number-audit-logs/:id} : Updates an existing itemSerialNumberAuditLog.
     *
     * @param id the id of the itemSerialNumberAuditLogDTO to save.
     * @param itemSerialNumberAuditLogDTO the itemSerialNumberAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemSerialNumberAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the itemSerialNumberAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the itemSerialNumberAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/item-serial-number-audit-logs/{id}")
    public ResponseEntity<ItemSerialNumberAuditLogDTO> updateItemSerialNumberAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ItemSerialNumberAuditLogDTO itemSerialNumberAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ItemSerialNumberAuditLog : {}, {}", id, itemSerialNumberAuditLogDTO);
        if (itemSerialNumberAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, itemSerialNumberAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!itemSerialNumberAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ItemSerialNumberAuditLogDTO result = itemSerialNumberAuditLogService.update(itemSerialNumberAuditLogDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, itemSerialNumberAuditLogDTO.getId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /item-serial-number-audit-logs/:id} : Partial updates given fields of an existing itemSerialNumberAuditLog, field will ignore if it is null
     *
     * @param id the id of the itemSerialNumberAuditLogDTO to save.
     * @param itemSerialNumberAuditLogDTO the itemSerialNumberAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemSerialNumberAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the itemSerialNumberAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the itemSerialNumberAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the itemSerialNumberAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/item-serial-number-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ItemSerialNumberAuditLogDTO> partialUpdateItemSerialNumberAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ItemSerialNumberAuditLogDTO itemSerialNumberAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ItemSerialNumberAuditLog partially : {}, {}", id, itemSerialNumberAuditLogDTO);
        if (itemSerialNumberAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, itemSerialNumberAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!itemSerialNumberAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ItemSerialNumberAuditLogDTO> result = itemSerialNumberAuditLogService.partialUpdate(itemSerialNumberAuditLogDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, itemSerialNumberAuditLogDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /item-serial-number-audit-logs} : get all the itemSerialNumberAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of itemSerialNumberAuditLogs in body.
     */
    @GetMapping("/item-serial-number-audit-logs")
    public ResponseEntity<List<ItemSerialNumberAuditLogDTO>> getAllItemSerialNumberAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ItemSerialNumberAuditLogs");
        Page<ItemSerialNumberAuditLogDTO> page = itemSerialNumberAuditLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /item-serial-number-audit-logs/:id} : get the "id" itemSerialNumberAuditLog.
     *
     * @param id the id of the itemSerialNumberAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the itemSerialNumberAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/item-serial-number-audit-logs/{id}")
    public ResponseEntity<ItemSerialNumberAuditLogDTO> getItemSerialNumberAuditLog(@PathVariable Long id) {
        log.debug("REST request to get ItemSerialNumberAuditLog : {}", id);
        Optional<ItemSerialNumberAuditLogDTO> itemSerialNumberAuditLogDTO = itemSerialNumberAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(itemSerialNumberAuditLogDTO);
    }

    /**
     * {@code DELETE  /item-serial-number-audit-logs/:id} : delete the "id" itemSerialNumberAuditLog.
     *
     * @param id the id of the itemSerialNumberAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/item-serial-number-audit-logs/{id}")
    public ResponseEntity<Void> deleteItemSerialNumberAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete ItemSerialNumberAuditLog : {}", id);
        itemSerialNumberAuditLogService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
