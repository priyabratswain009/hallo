package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.SalesOrderDocumentsAuditLogRepository;
import com.sunknowledge.dme.rcm.service.SalesOrderDocumentsAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderDocumentsAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.SalesOrderDocumentsAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class SalesOrderDocumentsAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(SalesOrderDocumentsAuditLogResource.class);

    private static final String ENTITY_NAME = "salesorderSalesOrderDocumentsAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SalesOrderDocumentsAuditLogService salesOrderDocumentsAuditLogService;

    private final SalesOrderDocumentsAuditLogRepository salesOrderDocumentsAuditLogRepository;

    public SalesOrderDocumentsAuditLogResource(
        SalesOrderDocumentsAuditLogService salesOrderDocumentsAuditLogService,
        SalesOrderDocumentsAuditLogRepository salesOrderDocumentsAuditLogRepository
    ) {
        this.salesOrderDocumentsAuditLogService = salesOrderDocumentsAuditLogService;
        this.salesOrderDocumentsAuditLogRepository = salesOrderDocumentsAuditLogRepository;
    }

    /**
     * {@code POST  /sales-order-documents-audit-logs} : Create a new salesOrderDocumentsAuditLog.
     *
     * @param salesOrderDocumentsAuditLogDTO the salesOrderDocumentsAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new salesOrderDocumentsAuditLogDTO, or with status {@code 400 (Bad Request)} if the salesOrderDocumentsAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sales-order-documents-audit-logs")
    public Mono<ResponseEntity<SalesOrderDocumentsAuditLogDTO>> createSalesOrderDocumentsAuditLog(
        @RequestBody SalesOrderDocumentsAuditLogDTO salesOrderDocumentsAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to save SalesOrderDocumentsAuditLog : {}", salesOrderDocumentsAuditLogDTO);
        if (salesOrderDocumentsAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new salesOrderDocumentsAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return salesOrderDocumentsAuditLogService
            .save(salesOrderDocumentsAuditLogDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/sales-order-documents-audit-logs/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /sales-order-documents-audit-logs/:id} : Updates an existing salesOrderDocumentsAuditLog.
     *
     * @param id the id of the salesOrderDocumentsAuditLogDTO to save.
     * @param salesOrderDocumentsAuditLogDTO the salesOrderDocumentsAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesOrderDocumentsAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the salesOrderDocumentsAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the salesOrderDocumentsAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sales-order-documents-audit-logs/{id}")
    public Mono<ResponseEntity<SalesOrderDocumentsAuditLogDTO>> updateSalesOrderDocumentsAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SalesOrderDocumentsAuditLogDTO salesOrderDocumentsAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SalesOrderDocumentsAuditLog : {}, {}", id, salesOrderDocumentsAuditLogDTO);
        if (salesOrderDocumentsAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, salesOrderDocumentsAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return salesOrderDocumentsAuditLogRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return salesOrderDocumentsAuditLogService
                    .update(salesOrderDocumentsAuditLogDTO)
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
     * {@code PATCH  /sales-order-documents-audit-logs/:id} : Partial updates given fields of an existing salesOrderDocumentsAuditLog, field will ignore if it is null
     *
     * @param id the id of the salesOrderDocumentsAuditLogDTO to save.
     * @param salesOrderDocumentsAuditLogDTO the salesOrderDocumentsAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesOrderDocumentsAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the salesOrderDocumentsAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the salesOrderDocumentsAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the salesOrderDocumentsAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/sales-order-documents-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<SalesOrderDocumentsAuditLogDTO>> partialUpdateSalesOrderDocumentsAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SalesOrderDocumentsAuditLogDTO salesOrderDocumentsAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update SalesOrderDocumentsAuditLog partially : {}, {}", id, salesOrderDocumentsAuditLogDTO);
        if (salesOrderDocumentsAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, salesOrderDocumentsAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return salesOrderDocumentsAuditLogRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<SalesOrderDocumentsAuditLogDTO> result = salesOrderDocumentsAuditLogService.partialUpdate(
                    salesOrderDocumentsAuditLogDTO
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
     * {@code GET  /sales-order-documents-audit-logs} : get all the salesOrderDocumentsAuditLogs.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of salesOrderDocumentsAuditLogs in body.
     */
    @GetMapping("/sales-order-documents-audit-logs")
    public Mono<ResponseEntity<List<SalesOrderDocumentsAuditLogDTO>>> getAllSalesOrderDocumentsAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of SalesOrderDocumentsAuditLogs");
        return salesOrderDocumentsAuditLogService
            .countAll()
            .zipWith(salesOrderDocumentsAuditLogService.findAll(pageable).collectList())
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
     * {@code GET  /sales-order-documents-audit-logs/:id} : get the "id" salesOrderDocumentsAuditLog.
     *
     * @param id the id of the salesOrderDocumentsAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the salesOrderDocumentsAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sales-order-documents-audit-logs/{id}")
    public Mono<ResponseEntity<SalesOrderDocumentsAuditLogDTO>> getSalesOrderDocumentsAuditLog(@PathVariable Long id) {
        log.debug("REST request to get SalesOrderDocumentsAuditLog : {}", id);
        Mono<SalesOrderDocumentsAuditLogDTO> salesOrderDocumentsAuditLogDTO = salesOrderDocumentsAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(salesOrderDocumentsAuditLogDTO);
    }

    /**
     * {@code DELETE  /sales-order-documents-audit-logs/:id} : delete the "id" salesOrderDocumentsAuditLog.
     *
     * @param id the id of the salesOrderDocumentsAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sales-order-documents-audit-logs/{id}")
    public Mono<ResponseEntity<Void>> deleteSalesOrderDocumentsAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete SalesOrderDocumentsAuditLog : {}", id);
        return salesOrderDocumentsAuditLogService
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
