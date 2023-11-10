package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.SalesOrderMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.SalesOrderMasterAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderMasterAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.SalesOrderMasterAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class SalesOrderMasterAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(SalesOrderMasterAuditLogResource.class);

    private static final String ENTITY_NAME = "salesorderSalesOrderMasterAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SalesOrderMasterAuditLogService salesOrderMasterAuditLogService;

    private final SalesOrderMasterAuditLogRepository salesOrderMasterAuditLogRepository;

    public SalesOrderMasterAuditLogResource(
        SalesOrderMasterAuditLogService salesOrderMasterAuditLogService,
        SalesOrderMasterAuditLogRepository salesOrderMasterAuditLogRepository
    ) {
        this.salesOrderMasterAuditLogService = salesOrderMasterAuditLogService;
        this.salesOrderMasterAuditLogRepository = salesOrderMasterAuditLogRepository;
    }

    /**
     * {@code POST  /sales-order-master-audit-logs} : Create a new salesOrderMasterAuditLog.
     *
     * @param salesOrderMasterAuditLogDTO the salesOrderMasterAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new salesOrderMasterAuditLogDTO, or with status {@code 400 (Bad Request)} if the salesOrderMasterAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sales-order-master-audit-logs")
    public Mono<ResponseEntity<SalesOrderMasterAuditLogDTO>> createSalesOrderMasterAuditLog(
        @RequestBody SalesOrderMasterAuditLogDTO salesOrderMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to save SalesOrderMasterAuditLog : {}", salesOrderMasterAuditLogDTO);
        if (salesOrderMasterAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new salesOrderMasterAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return salesOrderMasterAuditLogService
            .save(salesOrderMasterAuditLogDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/sales-order-master-audit-logs/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /sales-order-master-audit-logs/:id} : Updates an existing salesOrderMasterAuditLog.
     *
     * @param id the id of the salesOrderMasterAuditLogDTO to save.
     * @param salesOrderMasterAuditLogDTO the salesOrderMasterAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesOrderMasterAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the salesOrderMasterAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the salesOrderMasterAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sales-order-master-audit-logs/{id}")
    public Mono<ResponseEntity<SalesOrderMasterAuditLogDTO>> updateSalesOrderMasterAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SalesOrderMasterAuditLogDTO salesOrderMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SalesOrderMasterAuditLog : {}, {}", id, salesOrderMasterAuditLogDTO);
        if (salesOrderMasterAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, salesOrderMasterAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return salesOrderMasterAuditLogRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return salesOrderMasterAuditLogService
                    .update(salesOrderMasterAuditLogDTO)
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
     * {@code PATCH  /sales-order-master-audit-logs/:id} : Partial updates given fields of an existing salesOrderMasterAuditLog, field will ignore if it is null
     *
     * @param id the id of the salesOrderMasterAuditLogDTO to save.
     * @param salesOrderMasterAuditLogDTO the salesOrderMasterAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesOrderMasterAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the salesOrderMasterAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the salesOrderMasterAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the salesOrderMasterAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/sales-order-master-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<SalesOrderMasterAuditLogDTO>> partialUpdateSalesOrderMasterAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SalesOrderMasterAuditLogDTO salesOrderMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update SalesOrderMasterAuditLog partially : {}, {}", id, salesOrderMasterAuditLogDTO);
        if (salesOrderMasterAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, salesOrderMasterAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return salesOrderMasterAuditLogRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<SalesOrderMasterAuditLogDTO> result = salesOrderMasterAuditLogService.partialUpdate(salesOrderMasterAuditLogDTO);

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
     * {@code GET  /sales-order-master-audit-logs} : get all the salesOrderMasterAuditLogs.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of salesOrderMasterAuditLogs in body.
     */
    @GetMapping("/sales-order-master-audit-logs")
    public Mono<ResponseEntity<List<SalesOrderMasterAuditLogDTO>>> getAllSalesOrderMasterAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of SalesOrderMasterAuditLogs");
        return salesOrderMasterAuditLogService
            .countAll()
            .zipWith(salesOrderMasterAuditLogService.findAll(pageable).collectList())
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
     * {@code GET  /sales-order-master-audit-logs/:id} : get the "id" salesOrderMasterAuditLog.
     *
     * @param id the id of the salesOrderMasterAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the salesOrderMasterAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sales-order-master-audit-logs/{id}")
    public Mono<ResponseEntity<SalesOrderMasterAuditLogDTO>> getSalesOrderMasterAuditLog(@PathVariable Long id) {
        log.debug("REST request to get SalesOrderMasterAuditLog : {}", id);
        Mono<SalesOrderMasterAuditLogDTO> salesOrderMasterAuditLogDTO = salesOrderMasterAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(salesOrderMasterAuditLogDTO);
    }

    /**
     * {@code DELETE  /sales-order-master-audit-logs/:id} : delete the "id" salesOrderMasterAuditLog.
     *
     * @param id the id of the salesOrderMasterAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sales-order-master-audit-logs/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteSalesOrderMasterAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete SalesOrderMasterAuditLog : {}", id);
        return salesOrderMasterAuditLogService
            .delete(id)
            .map(result ->
                ResponseEntity
                    .noContent()
                    .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                    .build()
            );
    }
}
