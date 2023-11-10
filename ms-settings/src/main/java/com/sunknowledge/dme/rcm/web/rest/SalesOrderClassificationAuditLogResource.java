package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.SalesOrderClassificationAuditLogRepository;
import com.sunknowledge.dme.rcm.service.SalesOrderClassificationAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderClassificationAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.SalesOrderClassificationAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class SalesOrderClassificationAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(SalesOrderClassificationAuditLogResource.class);

    private static final String ENTITY_NAME = "settingsSalesOrderClassificationAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SalesOrderClassificationAuditLogService salesOrderClassificationAuditLogService;

    private final SalesOrderClassificationAuditLogRepository salesOrderClassificationAuditLogRepository;

    public SalesOrderClassificationAuditLogResource(
        SalesOrderClassificationAuditLogService salesOrderClassificationAuditLogService,
        SalesOrderClassificationAuditLogRepository salesOrderClassificationAuditLogRepository
    ) {
        this.salesOrderClassificationAuditLogService = salesOrderClassificationAuditLogService;
        this.salesOrderClassificationAuditLogRepository = salesOrderClassificationAuditLogRepository;
    }

    /**
     * {@code POST  /sales-order-classification-audit-logs} : Create a new salesOrderClassificationAuditLog.
     *
     * @param salesOrderClassificationAuditLogDTO the salesOrderClassificationAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new salesOrderClassificationAuditLogDTO, or with status {@code 400 (Bad Request)} if the salesOrderClassificationAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sales-order-classification-audit-logs")
    public ResponseEntity<SalesOrderClassificationAuditLogDTO> createSalesOrderClassificationAuditLog(
        @RequestBody SalesOrderClassificationAuditLogDTO salesOrderClassificationAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to save SalesOrderClassificationAuditLog : {}", salesOrderClassificationAuditLogDTO);
        if (salesOrderClassificationAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new salesOrderClassificationAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SalesOrderClassificationAuditLogDTO result = salesOrderClassificationAuditLogService.save(salesOrderClassificationAuditLogDTO);
        return ResponseEntity
            .created(new URI("/api/sales-order-classification-audit-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sales-order-classification-audit-logs/:id} : Updates an existing salesOrderClassificationAuditLog.
     *
     * @param id the id of the salesOrderClassificationAuditLogDTO to save.
     * @param salesOrderClassificationAuditLogDTO the salesOrderClassificationAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesOrderClassificationAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the salesOrderClassificationAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the salesOrderClassificationAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sales-order-classification-audit-logs/{id}")
    public ResponseEntity<SalesOrderClassificationAuditLogDTO> updateSalesOrderClassificationAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SalesOrderClassificationAuditLogDTO salesOrderClassificationAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SalesOrderClassificationAuditLog : {}, {}", id, salesOrderClassificationAuditLogDTO);
        if (salesOrderClassificationAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, salesOrderClassificationAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!salesOrderClassificationAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SalesOrderClassificationAuditLogDTO result = salesOrderClassificationAuditLogService.update(salesOrderClassificationAuditLogDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    salesOrderClassificationAuditLogDTO.getId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /sales-order-classification-audit-logs/:id} : Partial updates given fields of an existing salesOrderClassificationAuditLog, field will ignore if it is null
     *
     * @param id the id of the salesOrderClassificationAuditLogDTO to save.
     * @param salesOrderClassificationAuditLogDTO the salesOrderClassificationAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesOrderClassificationAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the salesOrderClassificationAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the salesOrderClassificationAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the salesOrderClassificationAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/sales-order-classification-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SalesOrderClassificationAuditLogDTO> partialUpdateSalesOrderClassificationAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SalesOrderClassificationAuditLogDTO salesOrderClassificationAuditLogDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update SalesOrderClassificationAuditLog partially : {}, {}",
            id,
            salesOrderClassificationAuditLogDTO
        );
        if (salesOrderClassificationAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, salesOrderClassificationAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!salesOrderClassificationAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SalesOrderClassificationAuditLogDTO> result = salesOrderClassificationAuditLogService.partialUpdate(
            salesOrderClassificationAuditLogDTO
        );

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, salesOrderClassificationAuditLogDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /sales-order-classification-audit-logs} : get all the salesOrderClassificationAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of salesOrderClassificationAuditLogs in body.
     */
    @GetMapping("/sales-order-classification-audit-logs")
    public ResponseEntity<List<SalesOrderClassificationAuditLogDTO>> getAllSalesOrderClassificationAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of SalesOrderClassificationAuditLogs");
        Page<SalesOrderClassificationAuditLogDTO> page = salesOrderClassificationAuditLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sales-order-classification-audit-logs/:id} : get the "id" salesOrderClassificationAuditLog.
     *
     * @param id the id of the salesOrderClassificationAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the salesOrderClassificationAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sales-order-classification-audit-logs/{id}")
    public ResponseEntity<SalesOrderClassificationAuditLogDTO> getSalesOrderClassificationAuditLog(@PathVariable Long id) {
        log.debug("REST request to get SalesOrderClassificationAuditLog : {}", id);
        Optional<SalesOrderClassificationAuditLogDTO> salesOrderClassificationAuditLogDTO = salesOrderClassificationAuditLogService.findOne(
            id
        );
        return ResponseUtil.wrapOrNotFound(salesOrderClassificationAuditLogDTO);
    }

    /**
     * {@code DELETE  /sales-order-classification-audit-logs/:id} : delete the "id" salesOrderClassificationAuditLog.
     *
     * @param id the id of the salesOrderClassificationAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sales-order-classification-audit-logs/{id}")
    public ResponseEntity<Void> deleteSalesOrderClassificationAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete SalesOrderClassificationAuditLog : {}", id);
        salesOrderClassificationAuditLogService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
