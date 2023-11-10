package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ManufacturerAuditLogRepository;
import com.sunknowledge.dme.rcm.service.ManufacturerAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.ManufacturerAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ManufacturerAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class ManufacturerAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(ManufacturerAuditLogResource.class);

    private static final String ENTITY_NAME = "itemsManufacturerAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ManufacturerAuditLogService manufacturerAuditLogService;

    private final ManufacturerAuditLogRepository manufacturerAuditLogRepository;

    public ManufacturerAuditLogResource(
        ManufacturerAuditLogService manufacturerAuditLogService,
        ManufacturerAuditLogRepository manufacturerAuditLogRepository
    ) {
        this.manufacturerAuditLogService = manufacturerAuditLogService;
        this.manufacturerAuditLogRepository = manufacturerAuditLogRepository;
    }

    /**
     * {@code POST  /manufacturer-audit-logs} : Create a new manufacturerAuditLog.
     *
     * @param manufacturerAuditLogDTO the manufacturerAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new manufacturerAuditLogDTO, or with status {@code 400 (Bad Request)} if the manufacturerAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/manufacturer-audit-logs")
    public ResponseEntity<ManufacturerAuditLogDTO> createManufacturerAuditLog(@RequestBody ManufacturerAuditLogDTO manufacturerAuditLogDTO)
        throws URISyntaxException {
        log.debug("REST request to save ManufacturerAuditLog : {}", manufacturerAuditLogDTO);
        if (manufacturerAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new manufacturerAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ManufacturerAuditLogDTO result = manufacturerAuditLogService.save(manufacturerAuditLogDTO);
        return ResponseEntity
            .created(new URI("/api/manufacturer-audit-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /manufacturer-audit-logs/:id} : Updates an existing manufacturerAuditLog.
     *
     * @param id the id of the manufacturerAuditLogDTO to save.
     * @param manufacturerAuditLogDTO the manufacturerAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated manufacturerAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the manufacturerAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the manufacturerAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/manufacturer-audit-logs/{id}")
    public ResponseEntity<ManufacturerAuditLogDTO> updateManufacturerAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ManufacturerAuditLogDTO manufacturerAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ManufacturerAuditLog : {}, {}", id, manufacturerAuditLogDTO);
        if (manufacturerAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, manufacturerAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!manufacturerAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ManufacturerAuditLogDTO result = manufacturerAuditLogService.update(manufacturerAuditLogDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, manufacturerAuditLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /manufacturer-audit-logs/:id} : Partial updates given fields of an existing manufacturerAuditLog, field will ignore if it is null
     *
     * @param id the id of the manufacturerAuditLogDTO to save.
     * @param manufacturerAuditLogDTO the manufacturerAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated manufacturerAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the manufacturerAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the manufacturerAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the manufacturerAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/manufacturer-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ManufacturerAuditLogDTO> partialUpdateManufacturerAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ManufacturerAuditLogDTO manufacturerAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ManufacturerAuditLog partially : {}, {}", id, manufacturerAuditLogDTO);
        if (manufacturerAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, manufacturerAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!manufacturerAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ManufacturerAuditLogDTO> result = manufacturerAuditLogService.partialUpdate(manufacturerAuditLogDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, manufacturerAuditLogDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /manufacturer-audit-logs} : get all the manufacturerAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of manufacturerAuditLogs in body.
     */
    @GetMapping("/manufacturer-audit-logs")
    public ResponseEntity<List<ManufacturerAuditLogDTO>> getAllManufacturerAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ManufacturerAuditLogs");
        Page<ManufacturerAuditLogDTO> page = manufacturerAuditLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /manufacturer-audit-logs/:id} : get the "id" manufacturerAuditLog.
     *
     * @param id the id of the manufacturerAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the manufacturerAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/manufacturer-audit-logs/{id}")
    public ResponseEntity<ManufacturerAuditLogDTO> getManufacturerAuditLog(@PathVariable Long id) {
        log.debug("REST request to get ManufacturerAuditLog : {}", id);
        Optional<ManufacturerAuditLogDTO> manufacturerAuditLogDTO = manufacturerAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(manufacturerAuditLogDTO);
    }

    /**
     * {@code DELETE  /manufacturer-audit-logs/:id} : delete the "id" manufacturerAuditLog.
     *
     * @param id the id of the manufacturerAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/manufacturer-audit-logs/{id}")
    public ResponseEntity<Void> deleteManufacturerAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete ManufacturerAuditLog : {}", id);
        manufacturerAuditLogService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
