package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.SalesOrderClinicalDetailsAuditLogRepository;
import com.sunknowledge.dme.rcm.service.SalesOrderClinicalDetailsAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderClinicalDetailsAuditLogDTO;
import com.sunknowledge.dme.rcm.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.reactive.ResponseUtil;

/**
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.SalesOrderClinicalDetailsAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class SalesOrderClinicalDetailsAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(SalesOrderClinicalDetailsAuditLogResource.class);

    private static final String ENTITY_NAME = "salesorderSalesOrderClinicalDetailsAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SalesOrderClinicalDetailsAuditLogService salesOrderClinicalDetailsAuditLogService;

    private final SalesOrderClinicalDetailsAuditLogRepository salesOrderClinicalDetailsAuditLogRepository;

    public SalesOrderClinicalDetailsAuditLogResource(
        SalesOrderClinicalDetailsAuditLogService salesOrderClinicalDetailsAuditLogService,
        SalesOrderClinicalDetailsAuditLogRepository salesOrderClinicalDetailsAuditLogRepository
    ) {
        this.salesOrderClinicalDetailsAuditLogService = salesOrderClinicalDetailsAuditLogService;
        this.salesOrderClinicalDetailsAuditLogRepository = salesOrderClinicalDetailsAuditLogRepository;
    }

    /**
     * {@code POST  /sales-order-clinical-details-audit-logs} : Create a new salesOrderClinicalDetailsAuditLog.
     *
     * @param salesOrderClinicalDetailsAuditLogDTO the salesOrderClinicalDetailsAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new salesOrderClinicalDetailsAuditLogDTO, or with status {@code 400 (Bad Request)} if the salesOrderClinicalDetailsAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sales-order-clinical-details-audit-logs")
    public Mono<ResponseEntity<SalesOrderClinicalDetailsAuditLogDTO>> createSalesOrderClinicalDetailsAuditLog(
        @RequestBody SalesOrderClinicalDetailsAuditLogDTO salesOrderClinicalDetailsAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to save SalesOrderClinicalDetailsAuditLog : {}", salesOrderClinicalDetailsAuditLogDTO);
        if (salesOrderClinicalDetailsAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException(
                "A new salesOrderClinicalDetailsAuditLog cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        return salesOrderClinicalDetailsAuditLogService
            .save(salesOrderClinicalDetailsAuditLogDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/sales-order-clinical-details-audit-logs/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /sales-order-clinical-details-audit-logs/:id} : Updates an existing salesOrderClinicalDetailsAuditLog.
     *
     * @param id the id of the salesOrderClinicalDetailsAuditLogDTO to save.
     * @param salesOrderClinicalDetailsAuditLogDTO the salesOrderClinicalDetailsAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesOrderClinicalDetailsAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the salesOrderClinicalDetailsAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the salesOrderClinicalDetailsAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sales-order-clinical-details-audit-logs/{id}")
    public Mono<ResponseEntity<SalesOrderClinicalDetailsAuditLogDTO>> updateSalesOrderClinicalDetailsAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SalesOrderClinicalDetailsAuditLogDTO salesOrderClinicalDetailsAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SalesOrderClinicalDetailsAuditLog : {}, {}", id, salesOrderClinicalDetailsAuditLogDTO);
        if (salesOrderClinicalDetailsAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, salesOrderClinicalDetailsAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return salesOrderClinicalDetailsAuditLogRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return salesOrderClinicalDetailsAuditLogService
                    .update(salesOrderClinicalDetailsAuditLogDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /sales-order-clinical-details-audit-logs/:id} : Partial updates given fields of an existing salesOrderClinicalDetailsAuditLog, field will ignore if it is null
     *
     * @param id the id of the salesOrderClinicalDetailsAuditLogDTO to save.
     * @param salesOrderClinicalDetailsAuditLogDTO the salesOrderClinicalDetailsAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesOrderClinicalDetailsAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the salesOrderClinicalDetailsAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the salesOrderClinicalDetailsAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the salesOrderClinicalDetailsAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/sales-order-clinical-details-audit-logs/{id}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public Mono<ResponseEntity<SalesOrderClinicalDetailsAuditLogDTO>> partialUpdateSalesOrderClinicalDetailsAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SalesOrderClinicalDetailsAuditLogDTO salesOrderClinicalDetailsAuditLogDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update SalesOrderClinicalDetailsAuditLog partially : {}, {}",
            id,
            salesOrderClinicalDetailsAuditLogDTO
        );
        if (salesOrderClinicalDetailsAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, salesOrderClinicalDetailsAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return salesOrderClinicalDetailsAuditLogRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<SalesOrderClinicalDetailsAuditLogDTO> result = salesOrderClinicalDetailsAuditLogService.partialUpdate(
                    salesOrderClinicalDetailsAuditLogDTO
                );

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(res ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, res.getId().toString()))
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /sales-order-clinical-details-audit-logs} : get all the salesOrderClinicalDetailsAuditLogs.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of salesOrderClinicalDetailsAuditLogs in body.
     */
    @GetMapping("/sales-order-clinical-details-audit-logs")
    public Mono<ResponseEntity<List<SalesOrderClinicalDetailsAuditLogDTO>>> getAllSalesOrderClinicalDetailsAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of SalesOrderClinicalDetailsAuditLogs");
        return salesOrderClinicalDetailsAuditLogService
            .countAll()
            .zipWith(salesOrderClinicalDetailsAuditLogService.findAll(pageable).collectList())
            .map(countWithEntities ->
                ResponseEntity
                    .ok()
                    .headers(
                        PaginationUtil.generatePaginationHttpHeaders(
                            UriComponentsBuilder.fromHttpRequest(request),
                            new PageImpl<>(countWithEntities.getT2(), pageable, countWithEntities.getT1())
                        )
                    )
                    .body(countWithEntities.getT2())
            );
    }

    /**
     * {@code GET  /sales-order-clinical-details-audit-logs/:id} : get the "id" salesOrderClinicalDetailsAuditLog.
     *
     * @param id the id of the salesOrderClinicalDetailsAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the salesOrderClinicalDetailsAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sales-order-clinical-details-audit-logs/{id}")
    public Mono<ResponseEntity<SalesOrderClinicalDetailsAuditLogDTO>> getSalesOrderClinicalDetailsAuditLog(@PathVariable Long id) {
        log.debug("REST request to get SalesOrderClinicalDetailsAuditLog : {}", id);
        Mono<SalesOrderClinicalDetailsAuditLogDTO> salesOrderClinicalDetailsAuditLogDTO = salesOrderClinicalDetailsAuditLogService.findOne(
            id
        );
        return ResponseUtil.wrapOrNotFound(salesOrderClinicalDetailsAuditLogDTO);
    }

    /**
     * {@code DELETE  /sales-order-clinical-details-audit-logs/:id} : delete the "id" salesOrderClinicalDetailsAuditLog.
     *
     * @param id the id of the salesOrderClinicalDetailsAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sales-order-clinical-details-audit-logs/{id}")
    public Mono<ResponseEntity<Void>> deleteSalesOrderClinicalDetailsAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete SalesOrderClinicalDetailsAuditLog : {}", id);
        return salesOrderClinicalDetailsAuditLogService
            .delete(id)
            .then(
                Mono.just(
                    ResponseEntity
                        .noContent()
                        .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                        .build()
                )
            );
    }
}
