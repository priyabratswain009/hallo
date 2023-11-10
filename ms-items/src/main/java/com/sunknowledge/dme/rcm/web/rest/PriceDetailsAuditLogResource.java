package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.PriceDetailsAuditLogRepository;
import com.sunknowledge.dme.rcm.service.PriceDetailsAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.PriceDetailsAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.PriceDetailsAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class PriceDetailsAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(PriceDetailsAuditLogResource.class);

    private static final String ENTITY_NAME = "itemsPriceDetailsAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PriceDetailsAuditLogService priceDetailsAuditLogService;

    private final PriceDetailsAuditLogRepository priceDetailsAuditLogRepository;

    public PriceDetailsAuditLogResource(
        PriceDetailsAuditLogService priceDetailsAuditLogService,
        PriceDetailsAuditLogRepository priceDetailsAuditLogRepository
    ) {
        this.priceDetailsAuditLogService = priceDetailsAuditLogService;
        this.priceDetailsAuditLogRepository = priceDetailsAuditLogRepository;
    }

    /**
     * {@code POST  /price-details-audit-logs} : Create a new priceDetailsAuditLog.
     *
     * @param priceDetailsAuditLogDTO the priceDetailsAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new priceDetailsAuditLogDTO, or with status {@code 400 (Bad Request)} if the priceDetailsAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/price-details-audit-logs")
    public ResponseEntity<PriceDetailsAuditLogDTO> createPriceDetailsAuditLog(@RequestBody PriceDetailsAuditLogDTO priceDetailsAuditLogDTO)
        throws URISyntaxException {
        log.debug("REST request to save PriceDetailsAuditLog : {}", priceDetailsAuditLogDTO);
        if (priceDetailsAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new priceDetailsAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PriceDetailsAuditLogDTO result = priceDetailsAuditLogService.save(priceDetailsAuditLogDTO);
        return ResponseEntity
            .created(new URI("/api/price-details-audit-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /price-details-audit-logs/:id} : Updates an existing priceDetailsAuditLog.
     *
     * @param id the id of the priceDetailsAuditLogDTO to save.
     * @param priceDetailsAuditLogDTO the priceDetailsAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated priceDetailsAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the priceDetailsAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the priceDetailsAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/price-details-audit-logs/{id}")
    public ResponseEntity<PriceDetailsAuditLogDTO> updatePriceDetailsAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PriceDetailsAuditLogDTO priceDetailsAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PriceDetailsAuditLog : {}, {}", id, priceDetailsAuditLogDTO);
        if (priceDetailsAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, priceDetailsAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!priceDetailsAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PriceDetailsAuditLogDTO result = priceDetailsAuditLogService.update(priceDetailsAuditLogDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, priceDetailsAuditLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /price-details-audit-logs/:id} : Partial updates given fields of an existing priceDetailsAuditLog, field will ignore if it is null
     *
     * @param id the id of the priceDetailsAuditLogDTO to save.
     * @param priceDetailsAuditLogDTO the priceDetailsAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated priceDetailsAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the priceDetailsAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the priceDetailsAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the priceDetailsAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/price-details-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PriceDetailsAuditLogDTO> partialUpdatePriceDetailsAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PriceDetailsAuditLogDTO priceDetailsAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PriceDetailsAuditLog partially : {}, {}", id, priceDetailsAuditLogDTO);
        if (priceDetailsAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, priceDetailsAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!priceDetailsAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PriceDetailsAuditLogDTO> result = priceDetailsAuditLogService.partialUpdate(priceDetailsAuditLogDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, priceDetailsAuditLogDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /price-details-audit-logs} : get all the priceDetailsAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of priceDetailsAuditLogs in body.
     */
    @GetMapping("/price-details-audit-logs")
    public ResponseEntity<List<PriceDetailsAuditLogDTO>> getAllPriceDetailsAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of PriceDetailsAuditLogs");
        Page<PriceDetailsAuditLogDTO> page = priceDetailsAuditLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /price-details-audit-logs/:id} : get the "id" priceDetailsAuditLog.
     *
     * @param id the id of the priceDetailsAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the priceDetailsAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/price-details-audit-logs/{id}")
    public ResponseEntity<PriceDetailsAuditLogDTO> getPriceDetailsAuditLog(@PathVariable Long id) {
        log.debug("REST request to get PriceDetailsAuditLog : {}", id);
        Optional<PriceDetailsAuditLogDTO> priceDetailsAuditLogDTO = priceDetailsAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(priceDetailsAuditLogDTO);
    }

    /**
     * {@code DELETE  /price-details-audit-logs/:id} : delete the "id" priceDetailsAuditLog.
     *
     * @param id the id of the priceDetailsAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/price-details-audit-logs/{id}")
    public ResponseEntity<Void> deletePriceDetailsAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete PriceDetailsAuditLog : {}", id);
        priceDetailsAuditLogService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
