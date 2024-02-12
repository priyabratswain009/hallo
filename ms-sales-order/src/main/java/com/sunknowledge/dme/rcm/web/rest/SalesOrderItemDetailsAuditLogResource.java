package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.SalesOrderItemDetailsAuditLogRepository;
import com.sunknowledge.dme.rcm.service.SalesOrderItemDetailsAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderItemDetailsAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.SalesOrderItemDetailsAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class SalesOrderItemDetailsAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(SalesOrderItemDetailsAuditLogResource.class);

    private static final String ENTITY_NAME = "salesorderSalesOrderItemDetailsAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SalesOrderItemDetailsAuditLogService salesOrderItemDetailsAuditLogService;

    private final SalesOrderItemDetailsAuditLogRepository salesOrderItemDetailsAuditLogRepository;

    public SalesOrderItemDetailsAuditLogResource(
        SalesOrderItemDetailsAuditLogService salesOrderItemDetailsAuditLogService,
        SalesOrderItemDetailsAuditLogRepository salesOrderItemDetailsAuditLogRepository
    ) {
        this.salesOrderItemDetailsAuditLogService = salesOrderItemDetailsAuditLogService;
        this.salesOrderItemDetailsAuditLogRepository = salesOrderItemDetailsAuditLogRepository;
    }

    /**
     * {@code POST  /sales-order-item-details-audit-logs} : Create a new salesOrderItemDetailsAuditLog.
     *
     * @param salesOrderItemDetailsAuditLogDTO the salesOrderItemDetailsAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new salesOrderItemDetailsAuditLogDTO, or with status {@code 400 (Bad Request)} if the salesOrderItemDetailsAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sales-order-item-details-audit-logs")
    public Mono<ResponseEntity<SalesOrderItemDetailsAuditLogDTO>> createSalesOrderItemDetailsAuditLog(
        @RequestBody SalesOrderItemDetailsAuditLogDTO salesOrderItemDetailsAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to save SalesOrderItemDetailsAuditLog : {}", salesOrderItemDetailsAuditLogDTO);
        if (salesOrderItemDetailsAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new salesOrderItemDetailsAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return salesOrderItemDetailsAuditLogService
            .save(salesOrderItemDetailsAuditLogDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/sales-order-item-details-audit-logs/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /sales-order-item-details-audit-logs/:id} : Updates an existing salesOrderItemDetailsAuditLog.
     *
     * @param id the id of the salesOrderItemDetailsAuditLogDTO to save.
     * @param salesOrderItemDetailsAuditLogDTO the salesOrderItemDetailsAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesOrderItemDetailsAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the salesOrderItemDetailsAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the salesOrderItemDetailsAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sales-order-item-details-audit-logs/{id}")
    public Mono<ResponseEntity<SalesOrderItemDetailsAuditLogDTO>> updateSalesOrderItemDetailsAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SalesOrderItemDetailsAuditLogDTO salesOrderItemDetailsAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SalesOrderItemDetailsAuditLog : {}, {}", id, salesOrderItemDetailsAuditLogDTO);
        if (salesOrderItemDetailsAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, salesOrderItemDetailsAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return salesOrderItemDetailsAuditLogRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return salesOrderItemDetailsAuditLogService
                    .update(salesOrderItemDetailsAuditLogDTO)
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
     * {@code PATCH  /sales-order-item-details-audit-logs/:id} : Partial updates given fields of an existing salesOrderItemDetailsAuditLog, field will ignore if it is null
     *
     * @param id the id of the salesOrderItemDetailsAuditLogDTO to save.
     * @param salesOrderItemDetailsAuditLogDTO the salesOrderItemDetailsAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesOrderItemDetailsAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the salesOrderItemDetailsAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the salesOrderItemDetailsAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the salesOrderItemDetailsAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/sales-order-item-details-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<SalesOrderItemDetailsAuditLogDTO>> partialUpdateSalesOrderItemDetailsAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SalesOrderItemDetailsAuditLogDTO salesOrderItemDetailsAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update SalesOrderItemDetailsAuditLog partially : {}, {}", id, salesOrderItemDetailsAuditLogDTO);
        if (salesOrderItemDetailsAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, salesOrderItemDetailsAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return salesOrderItemDetailsAuditLogRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<SalesOrderItemDetailsAuditLogDTO> result = salesOrderItemDetailsAuditLogService.partialUpdate(
                    salesOrderItemDetailsAuditLogDTO
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
     * {@code GET  /sales-order-item-details-audit-logs} : get all the salesOrderItemDetailsAuditLogs.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of salesOrderItemDetailsAuditLogs in body.
     */
    @GetMapping("/sales-order-item-details-audit-logs")
    public Mono<ResponseEntity<List<SalesOrderItemDetailsAuditLogDTO>>> getAllSalesOrderItemDetailsAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of SalesOrderItemDetailsAuditLogs");
        return salesOrderItemDetailsAuditLogService
            .countAll()
            .zipWith(salesOrderItemDetailsAuditLogService.findAll(pageable).collectList())
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
     * {@code GET  /sales-order-item-details-audit-logs/:id} : get the "id" salesOrderItemDetailsAuditLog.
     *
     * @param id the id of the salesOrderItemDetailsAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the salesOrderItemDetailsAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sales-order-item-details-audit-logs/{id}")
    public Mono<ResponseEntity<SalesOrderItemDetailsAuditLogDTO>> getSalesOrderItemDetailsAuditLog(@PathVariable Long id) {
        log.debug("REST request to get SalesOrderItemDetailsAuditLog : {}", id);
        Mono<SalesOrderItemDetailsAuditLogDTO> salesOrderItemDetailsAuditLogDTO = salesOrderItemDetailsAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(salesOrderItemDetailsAuditLogDTO);
    }

    /**
     * {@code DELETE  /sales-order-item-details-audit-logs/:id} : delete the "id" salesOrderItemDetailsAuditLog.
     *
     * @param id the id of the salesOrderItemDetailsAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sales-order-item-details-audit-logs/{id}")
    public Mono<ResponseEntity<Void>> deleteSalesOrderItemDetailsAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete SalesOrderItemDetailsAuditLog : {}", id);
        return salesOrderItemDetailsAuditLogService
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
