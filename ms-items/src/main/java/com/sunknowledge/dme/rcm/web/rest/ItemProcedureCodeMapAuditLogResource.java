package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ItemProcedureCodeMapAuditLogRepository;
import com.sunknowledge.dme.rcm.service.ItemProcedureCodeMapAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.ItemProcedureCodeMapAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ItemProcedureCodeMapAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class ItemProcedureCodeMapAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(ItemProcedureCodeMapAuditLogResource.class);

    private static final String ENTITY_NAME = "itemsItemProcedureCodeMapAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ItemProcedureCodeMapAuditLogService itemProcedureCodeMapAuditLogService;

    private final ItemProcedureCodeMapAuditLogRepository itemProcedureCodeMapAuditLogRepository;

    public ItemProcedureCodeMapAuditLogResource(
        ItemProcedureCodeMapAuditLogService itemProcedureCodeMapAuditLogService,
        ItemProcedureCodeMapAuditLogRepository itemProcedureCodeMapAuditLogRepository
    ) {
        this.itemProcedureCodeMapAuditLogService = itemProcedureCodeMapAuditLogService;
        this.itemProcedureCodeMapAuditLogRepository = itemProcedureCodeMapAuditLogRepository;
    }

    /**
     * {@code POST  /item-procedure-code-map-audit-logs} : Create a new itemProcedureCodeMapAuditLog.
     *
     * @param itemProcedureCodeMapAuditLogDTO the itemProcedureCodeMapAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new itemProcedureCodeMapAuditLogDTO, or with status {@code 400 (Bad Request)} if the itemProcedureCodeMapAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/item-procedure-code-map-audit-logs")
    public ResponseEntity<ItemProcedureCodeMapAuditLogDTO> createItemProcedureCodeMapAuditLog(
        @RequestBody ItemProcedureCodeMapAuditLogDTO itemProcedureCodeMapAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to save ItemProcedureCodeMapAuditLog : {}", itemProcedureCodeMapAuditLogDTO);
        if (itemProcedureCodeMapAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new itemProcedureCodeMapAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemProcedureCodeMapAuditLogDTO result = itemProcedureCodeMapAuditLogService.save(itemProcedureCodeMapAuditLogDTO);
        return ResponseEntity
            .created(new URI("/api/item-procedure-code-map-audit-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /item-procedure-code-map-audit-logs/:id} : Updates an existing itemProcedureCodeMapAuditLog.
     *
     * @param id the id of the itemProcedureCodeMapAuditLogDTO to save.
     * @param itemProcedureCodeMapAuditLogDTO the itemProcedureCodeMapAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemProcedureCodeMapAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the itemProcedureCodeMapAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the itemProcedureCodeMapAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/item-procedure-code-map-audit-logs/{id}")
    public ResponseEntity<ItemProcedureCodeMapAuditLogDTO> updateItemProcedureCodeMapAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ItemProcedureCodeMapAuditLogDTO itemProcedureCodeMapAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ItemProcedureCodeMapAuditLog : {}, {}", id, itemProcedureCodeMapAuditLogDTO);
        if (itemProcedureCodeMapAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, itemProcedureCodeMapAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!itemProcedureCodeMapAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ItemProcedureCodeMapAuditLogDTO result = itemProcedureCodeMapAuditLogService.update(itemProcedureCodeMapAuditLogDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, itemProcedureCodeMapAuditLogDTO.getId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /item-procedure-code-map-audit-logs/:id} : Partial updates given fields of an existing itemProcedureCodeMapAuditLog, field will ignore if it is null
     *
     * @param id the id of the itemProcedureCodeMapAuditLogDTO to save.
     * @param itemProcedureCodeMapAuditLogDTO the itemProcedureCodeMapAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemProcedureCodeMapAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the itemProcedureCodeMapAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the itemProcedureCodeMapAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the itemProcedureCodeMapAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/item-procedure-code-map-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ItemProcedureCodeMapAuditLogDTO> partialUpdateItemProcedureCodeMapAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ItemProcedureCodeMapAuditLogDTO itemProcedureCodeMapAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ItemProcedureCodeMapAuditLog partially : {}, {}", id, itemProcedureCodeMapAuditLogDTO);
        if (itemProcedureCodeMapAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, itemProcedureCodeMapAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!itemProcedureCodeMapAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ItemProcedureCodeMapAuditLogDTO> result = itemProcedureCodeMapAuditLogService.partialUpdate(
            itemProcedureCodeMapAuditLogDTO
        );

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, itemProcedureCodeMapAuditLogDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /item-procedure-code-map-audit-logs} : get all the itemProcedureCodeMapAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of itemProcedureCodeMapAuditLogs in body.
     */
    @GetMapping("/item-procedure-code-map-audit-logs")
    public ResponseEntity<List<ItemProcedureCodeMapAuditLogDTO>> getAllItemProcedureCodeMapAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ItemProcedureCodeMapAuditLogs");
        Page<ItemProcedureCodeMapAuditLogDTO> page = itemProcedureCodeMapAuditLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /item-procedure-code-map-audit-logs/:id} : get the "id" itemProcedureCodeMapAuditLog.
     *
     * @param id the id of the itemProcedureCodeMapAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the itemProcedureCodeMapAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/item-procedure-code-map-audit-logs/{id}")
    public ResponseEntity<ItemProcedureCodeMapAuditLogDTO> getItemProcedureCodeMapAuditLog(@PathVariable Long id) {
        log.debug("REST request to get ItemProcedureCodeMapAuditLog : {}", id);
        Optional<ItemProcedureCodeMapAuditLogDTO> itemProcedureCodeMapAuditLogDTO = itemProcedureCodeMapAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(itemProcedureCodeMapAuditLogDTO);
    }

    /**
     * {@code DELETE  /item-procedure-code-map-audit-logs/:id} : delete the "id" itemProcedureCodeMapAuditLog.
     *
     * @param id the id of the itemProcedureCodeMapAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/item-procedure-code-map-audit-logs/{id}")
    public ResponseEntity<Void> deleteItemProcedureCodeMapAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete ItemProcedureCodeMapAuditLog : {}", id);
        itemProcedureCodeMapAuditLogService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
