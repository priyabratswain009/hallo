package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.SalesOrderFinancialDetailsAuditLogRepository;
import com.sunknowledge.dme.rcm.service.SalesOrderFinancialDetailsAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderFinancialDetailsAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.SalesOrderFinancialDetailsAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class SalesOrderFinancialDetailsAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(SalesOrderFinancialDetailsAuditLogResource.class);

    private static final String ENTITY_NAME = "salesorderSalesOrderFinancialDetailsAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SalesOrderFinancialDetailsAuditLogService salesOrderFinancialDetailsAuditLogService;

    private final SalesOrderFinancialDetailsAuditLogRepository salesOrderFinancialDetailsAuditLogRepository;

    public SalesOrderFinancialDetailsAuditLogResource(
        SalesOrderFinancialDetailsAuditLogService salesOrderFinancialDetailsAuditLogService,
        SalesOrderFinancialDetailsAuditLogRepository salesOrderFinancialDetailsAuditLogRepository
    ) {
        this.salesOrderFinancialDetailsAuditLogService = salesOrderFinancialDetailsAuditLogService;
        this.salesOrderFinancialDetailsAuditLogRepository = salesOrderFinancialDetailsAuditLogRepository;
    }

    /**
     * {@code POST  /sales-order-financial-details-audit-logs} : Create a new salesOrderFinancialDetailsAuditLog.
     *
     * @param salesOrderFinancialDetailsAuditLogDTO the salesOrderFinancialDetailsAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new salesOrderFinancialDetailsAuditLogDTO, or with status {@code 400 (Bad Request)} if the salesOrderFinancialDetailsAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sales-order-financial-details-audit-logs")
    public Mono<ResponseEntity<SalesOrderFinancialDetailsAuditLogDTO>> createSalesOrderFinancialDetailsAuditLog(
        @RequestBody SalesOrderFinancialDetailsAuditLogDTO salesOrderFinancialDetailsAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to save SalesOrderFinancialDetailsAuditLog : {}", salesOrderFinancialDetailsAuditLogDTO);
        if (salesOrderFinancialDetailsAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException(
                "A new salesOrderFinancialDetailsAuditLog cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        return salesOrderFinancialDetailsAuditLogService
            .save(salesOrderFinancialDetailsAuditLogDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/sales-order-financial-details-audit-logs/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /sales-order-financial-details-audit-logs/:id} : Updates an existing salesOrderFinancialDetailsAuditLog.
     *
     * @param id the id of the salesOrderFinancialDetailsAuditLogDTO to save.
     * @param salesOrderFinancialDetailsAuditLogDTO the salesOrderFinancialDetailsAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesOrderFinancialDetailsAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the salesOrderFinancialDetailsAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the salesOrderFinancialDetailsAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sales-order-financial-details-audit-logs/{id}")
    public Mono<ResponseEntity<SalesOrderFinancialDetailsAuditLogDTO>> updateSalesOrderFinancialDetailsAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SalesOrderFinancialDetailsAuditLogDTO salesOrderFinancialDetailsAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SalesOrderFinancialDetailsAuditLog : {}, {}", id, salesOrderFinancialDetailsAuditLogDTO);
        if (salesOrderFinancialDetailsAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, salesOrderFinancialDetailsAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return salesOrderFinancialDetailsAuditLogRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return salesOrderFinancialDetailsAuditLogService
                    .update(salesOrderFinancialDetailsAuditLogDTO)
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
     * {@code PATCH  /sales-order-financial-details-audit-logs/:id} : Partial updates given fields of an existing salesOrderFinancialDetailsAuditLog, field will ignore if it is null
     *
     * @param id the id of the salesOrderFinancialDetailsAuditLogDTO to save.
     * @param salesOrderFinancialDetailsAuditLogDTO the salesOrderFinancialDetailsAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesOrderFinancialDetailsAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the salesOrderFinancialDetailsAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the salesOrderFinancialDetailsAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the salesOrderFinancialDetailsAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/sales-order-financial-details-audit-logs/{id}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public Mono<ResponseEntity<SalesOrderFinancialDetailsAuditLogDTO>> partialUpdateSalesOrderFinancialDetailsAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SalesOrderFinancialDetailsAuditLogDTO salesOrderFinancialDetailsAuditLogDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update SalesOrderFinancialDetailsAuditLog partially : {}, {}",
            id,
            salesOrderFinancialDetailsAuditLogDTO
        );
        if (salesOrderFinancialDetailsAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, salesOrderFinancialDetailsAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return salesOrderFinancialDetailsAuditLogRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<SalesOrderFinancialDetailsAuditLogDTO> result = salesOrderFinancialDetailsAuditLogService.partialUpdate(
                    salesOrderFinancialDetailsAuditLogDTO
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
     * {@code GET  /sales-order-financial-details-audit-logs} : get all the salesOrderFinancialDetailsAuditLogs.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of salesOrderFinancialDetailsAuditLogs in body.
     */
    @GetMapping("/sales-order-financial-details-audit-logs")
    public Mono<ResponseEntity<List<SalesOrderFinancialDetailsAuditLogDTO>>> getAllSalesOrderFinancialDetailsAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of SalesOrderFinancialDetailsAuditLogs");
        return salesOrderFinancialDetailsAuditLogService
            .countAll()
            .zipWith(salesOrderFinancialDetailsAuditLogService.findAll(pageable).collectList())
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
     * {@code GET  /sales-order-financial-details-audit-logs/:id} : get the "id" salesOrderFinancialDetailsAuditLog.
     *
     * @param id the id of the salesOrderFinancialDetailsAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the salesOrderFinancialDetailsAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sales-order-financial-details-audit-logs/{id}")
    public Mono<ResponseEntity<SalesOrderFinancialDetailsAuditLogDTO>> getSalesOrderFinancialDetailsAuditLog(@PathVariable Long id) {
        log.debug("REST request to get SalesOrderFinancialDetailsAuditLog : {}", id);
        Mono<SalesOrderFinancialDetailsAuditLogDTO> salesOrderFinancialDetailsAuditLogDTO = salesOrderFinancialDetailsAuditLogService.findOne(
            id
        );
        return ResponseUtil.wrapOrNotFound(salesOrderFinancialDetailsAuditLogDTO);
    }

    /**
     * {@code DELETE  /sales-order-financial-details-audit-logs/:id} : delete the "id" salesOrderFinancialDetailsAuditLog.
     *
     * @param id the id of the salesOrderFinancialDetailsAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sales-order-financial-details-audit-logs/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteSalesOrderFinancialDetailsAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete SalesOrderFinancialDetailsAuditLog : {}", id);
        return salesOrderFinancialDetailsAuditLogService
            .delete(id)
            .map(result ->
                ResponseEntity
                    .noContent()
                    .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                    .build()
            );
    }
}
