package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.TaxZoneAuditLogRepository;
import com.sunknowledge.dme.rcm.service.TaxZoneAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.TaxZoneAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.TaxZoneAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class TaxZoneAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(TaxZoneAuditLogResource.class);

    private static final String ENTITY_NAME = "settingsTaxZoneAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TaxZoneAuditLogService taxZoneAuditLogService;

    private final TaxZoneAuditLogRepository taxZoneAuditLogRepository;

    public TaxZoneAuditLogResource(TaxZoneAuditLogService taxZoneAuditLogService, TaxZoneAuditLogRepository taxZoneAuditLogRepository) {
        this.taxZoneAuditLogService = taxZoneAuditLogService;
        this.taxZoneAuditLogRepository = taxZoneAuditLogRepository;
    }

    /**
     * {@code POST  /tax-zone-audit-logs} : Create a new taxZoneAuditLog.
     *
     * @param taxZoneAuditLogDTO the taxZoneAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new taxZoneAuditLogDTO, or with status {@code 400 (Bad Request)} if the taxZoneAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tax-zone-audit-logs")
    public ResponseEntity<TaxZoneAuditLogDTO> createTaxZoneAuditLog(@RequestBody TaxZoneAuditLogDTO taxZoneAuditLogDTO)
        throws URISyntaxException {
        log.debug("REST request to save TaxZoneAuditLog : {}", taxZoneAuditLogDTO);
        if (taxZoneAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new taxZoneAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TaxZoneAuditLogDTO result = taxZoneAuditLogService.save(taxZoneAuditLogDTO);
        return ResponseEntity
            .created(new URI("/api/tax-zone-audit-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tax-zone-audit-logs/:id} : Updates an existing taxZoneAuditLog.
     *
     * @param id the id of the taxZoneAuditLogDTO to save.
     * @param taxZoneAuditLogDTO the taxZoneAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taxZoneAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the taxZoneAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the taxZoneAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tax-zone-audit-logs/{id}")
    public ResponseEntity<TaxZoneAuditLogDTO> updateTaxZoneAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TaxZoneAuditLogDTO taxZoneAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TaxZoneAuditLog : {}, {}", id, taxZoneAuditLogDTO);
        if (taxZoneAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, taxZoneAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!taxZoneAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TaxZoneAuditLogDTO result = taxZoneAuditLogService.update(taxZoneAuditLogDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, taxZoneAuditLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tax-zone-audit-logs/:id} : Partial updates given fields of an existing taxZoneAuditLog, field will ignore if it is null
     *
     * @param id the id of the taxZoneAuditLogDTO to save.
     * @param taxZoneAuditLogDTO the taxZoneAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taxZoneAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the taxZoneAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the taxZoneAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the taxZoneAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tax-zone-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TaxZoneAuditLogDTO> partialUpdateTaxZoneAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TaxZoneAuditLogDTO taxZoneAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TaxZoneAuditLog partially : {}, {}", id, taxZoneAuditLogDTO);
        if (taxZoneAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, taxZoneAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!taxZoneAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TaxZoneAuditLogDTO> result = taxZoneAuditLogService.partialUpdate(taxZoneAuditLogDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, taxZoneAuditLogDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tax-zone-audit-logs} : get all the taxZoneAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of taxZoneAuditLogs in body.
     */
    @GetMapping("/tax-zone-audit-logs")
    public ResponseEntity<List<TaxZoneAuditLogDTO>> getAllTaxZoneAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of TaxZoneAuditLogs");
        Page<TaxZoneAuditLogDTO> page = taxZoneAuditLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /tax-zone-audit-logs/:id} : get the "id" taxZoneAuditLog.
     *
     * @param id the id of the taxZoneAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the taxZoneAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tax-zone-audit-logs/{id}")
    public ResponseEntity<TaxZoneAuditLogDTO> getTaxZoneAuditLog(@PathVariable Long id) {
        log.debug("REST request to get TaxZoneAuditLog : {}", id);
        Optional<TaxZoneAuditLogDTO> taxZoneAuditLogDTO = taxZoneAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(taxZoneAuditLogDTO);
    }

    /**
     * {@code DELETE  /tax-zone-audit-logs/:id} : delete the "id" taxZoneAuditLog.
     *
     * @param id the id of the taxZoneAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tax-zone-audit-logs/{id}")
    public ResponseEntity<Void> deleteTaxZoneAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete TaxZoneAuditLog : {}", id);
        taxZoneAuditLogService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
