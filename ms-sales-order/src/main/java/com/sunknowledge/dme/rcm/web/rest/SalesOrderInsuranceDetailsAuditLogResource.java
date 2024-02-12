package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.SalesOrderInsuranceDetailsAuditLogRepository;
import com.sunknowledge.dme.rcm.service.SalesOrderInsuranceDetailsAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderInsuranceDetailsAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.SalesOrderInsuranceDetailsAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class SalesOrderInsuranceDetailsAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(SalesOrderInsuranceDetailsAuditLogResource.class);

    private static final String ENTITY_NAME = "salesorderSalesOrderInsuranceDetailsAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SalesOrderInsuranceDetailsAuditLogService salesOrderInsuranceDetailsAuditLogService;

    private final SalesOrderInsuranceDetailsAuditLogRepository salesOrderInsuranceDetailsAuditLogRepository;

    public SalesOrderInsuranceDetailsAuditLogResource(
        SalesOrderInsuranceDetailsAuditLogService salesOrderInsuranceDetailsAuditLogService,
        SalesOrderInsuranceDetailsAuditLogRepository salesOrderInsuranceDetailsAuditLogRepository
    ) {
        this.salesOrderInsuranceDetailsAuditLogService = salesOrderInsuranceDetailsAuditLogService;
        this.salesOrderInsuranceDetailsAuditLogRepository = salesOrderInsuranceDetailsAuditLogRepository;
    }

    /**
     * {@code POST  /sales-order-insurance-details-audit-logs} : Create a new salesOrderInsuranceDetailsAuditLog.
     *
     * @param salesOrderInsuranceDetailsAuditLogDTO the salesOrderInsuranceDetailsAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new salesOrderInsuranceDetailsAuditLogDTO, or with status {@code 400 (Bad Request)} if the salesOrderInsuranceDetailsAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sales-order-insurance-details-audit-logs")
    public Mono<ResponseEntity<SalesOrderInsuranceDetailsAuditLogDTO>> createSalesOrderInsuranceDetailsAuditLog(
        @RequestBody SalesOrderInsuranceDetailsAuditLogDTO salesOrderInsuranceDetailsAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to save SalesOrderInsuranceDetailsAuditLog : {}", salesOrderInsuranceDetailsAuditLogDTO);
        if (salesOrderInsuranceDetailsAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException(
                "A new salesOrderInsuranceDetailsAuditLog cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        return salesOrderInsuranceDetailsAuditLogService
            .save(salesOrderInsuranceDetailsAuditLogDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/sales-order-insurance-details-audit-logs/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /sales-order-insurance-details-audit-logs/:id} : Updates an existing salesOrderInsuranceDetailsAuditLog.
     *
     * @param id the id of the salesOrderInsuranceDetailsAuditLogDTO to save.
     * @param salesOrderInsuranceDetailsAuditLogDTO the salesOrderInsuranceDetailsAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesOrderInsuranceDetailsAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the salesOrderInsuranceDetailsAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the salesOrderInsuranceDetailsAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sales-order-insurance-details-audit-logs/{id}")
    public Mono<ResponseEntity<SalesOrderInsuranceDetailsAuditLogDTO>> updateSalesOrderInsuranceDetailsAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SalesOrderInsuranceDetailsAuditLogDTO salesOrderInsuranceDetailsAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SalesOrderInsuranceDetailsAuditLog : {}, {}", id, salesOrderInsuranceDetailsAuditLogDTO);
        if (salesOrderInsuranceDetailsAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, salesOrderInsuranceDetailsAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return salesOrderInsuranceDetailsAuditLogRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return salesOrderInsuranceDetailsAuditLogService
                    .update(salesOrderInsuranceDetailsAuditLogDTO)
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
     * {@code PATCH  /sales-order-insurance-details-audit-logs/:id} : Partial updates given fields of an existing salesOrderInsuranceDetailsAuditLog, field will ignore if it is null
     *
     * @param id the id of the salesOrderInsuranceDetailsAuditLogDTO to save.
     * @param salesOrderInsuranceDetailsAuditLogDTO the salesOrderInsuranceDetailsAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesOrderInsuranceDetailsAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the salesOrderInsuranceDetailsAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the salesOrderInsuranceDetailsAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the salesOrderInsuranceDetailsAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/sales-order-insurance-details-audit-logs/{id}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public Mono<ResponseEntity<SalesOrderInsuranceDetailsAuditLogDTO>> partialUpdateSalesOrderInsuranceDetailsAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SalesOrderInsuranceDetailsAuditLogDTO salesOrderInsuranceDetailsAuditLogDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update SalesOrderInsuranceDetailsAuditLog partially : {}, {}",
            id,
            salesOrderInsuranceDetailsAuditLogDTO
        );
        if (salesOrderInsuranceDetailsAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, salesOrderInsuranceDetailsAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return salesOrderInsuranceDetailsAuditLogRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<SalesOrderInsuranceDetailsAuditLogDTO> result = salesOrderInsuranceDetailsAuditLogService.partialUpdate(
                    salesOrderInsuranceDetailsAuditLogDTO
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
     * {@code GET  /sales-order-insurance-details-audit-logs} : get all the salesOrderInsuranceDetailsAuditLogs.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of salesOrderInsuranceDetailsAuditLogs in body.
     */
    @GetMapping("/sales-order-insurance-details-audit-logs")
    public Mono<ResponseEntity<List<SalesOrderInsuranceDetailsAuditLogDTO>>> getAllSalesOrderInsuranceDetailsAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of SalesOrderInsuranceDetailsAuditLogs");
        return salesOrderInsuranceDetailsAuditLogService
            .countAll()
            .zipWith(salesOrderInsuranceDetailsAuditLogService.findAll(pageable).collectList())
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
     * {@code GET  /sales-order-insurance-details-audit-logs/:id} : get the "id" salesOrderInsuranceDetailsAuditLog.
     *
     * @param id the id of the salesOrderInsuranceDetailsAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the salesOrderInsuranceDetailsAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sales-order-insurance-details-audit-logs/{id}")
    public Mono<ResponseEntity<SalesOrderInsuranceDetailsAuditLogDTO>> getSalesOrderInsuranceDetailsAuditLog(@PathVariable Long id) {
        log.debug("REST request to get SalesOrderInsuranceDetailsAuditLog : {}", id);
        Mono<SalesOrderInsuranceDetailsAuditLogDTO> salesOrderInsuranceDetailsAuditLogDTO = salesOrderInsuranceDetailsAuditLogService.findOne(
            id
        );
        return ResponseUtil.wrapOrNotFound(salesOrderInsuranceDetailsAuditLogDTO);
    }

    /**
     * {@code DELETE  /sales-order-insurance-details-audit-logs/:id} : delete the "id" salesOrderInsuranceDetailsAuditLog.
     *
     * @param id the id of the salesOrderInsuranceDetailsAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sales-order-insurance-details-audit-logs/{id}")
    public Mono<ResponseEntity<Void>> deleteSalesOrderInsuranceDetailsAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete SalesOrderInsuranceDetailsAuditLog : {}", id);
        return salesOrderInsuranceDetailsAuditLogService
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
