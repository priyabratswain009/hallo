package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.InvoiceMasterDetailsAuditLogRepository;
import com.sunknowledge.dme.rcm.service.InvoiceMasterDetailsAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.InvoiceMasterDetailsAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.InvoiceMasterDetailsAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class InvoiceMasterDetailsAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(InvoiceMasterDetailsAuditLogResource.class);

    private static final String ENTITY_NAME = "salesorderInvoiceMasterDetailsAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InvoiceMasterDetailsAuditLogService invoiceMasterDetailsAuditLogService;

    private final InvoiceMasterDetailsAuditLogRepository invoiceMasterDetailsAuditLogRepository;

    public InvoiceMasterDetailsAuditLogResource(
        InvoiceMasterDetailsAuditLogService invoiceMasterDetailsAuditLogService,
        InvoiceMasterDetailsAuditLogRepository invoiceMasterDetailsAuditLogRepository
    ) {
        this.invoiceMasterDetailsAuditLogService = invoiceMasterDetailsAuditLogService;
        this.invoiceMasterDetailsAuditLogRepository = invoiceMasterDetailsAuditLogRepository;
    }

    /**
     * {@code POST  /invoice-master-details-audit-logs} : Create a new invoiceMasterDetailsAuditLog.
     *
     * @param invoiceMasterDetailsAuditLogDTO the invoiceMasterDetailsAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new invoiceMasterDetailsAuditLogDTO, or with status {@code 400 (Bad Request)} if the invoiceMasterDetailsAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/invoice-master-details-audit-logs")
    public Mono<ResponseEntity<InvoiceMasterDetailsAuditLogDTO>> createInvoiceMasterDetailsAuditLog(
        @RequestBody InvoiceMasterDetailsAuditLogDTO invoiceMasterDetailsAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to save InvoiceMasterDetailsAuditLog : {}", invoiceMasterDetailsAuditLogDTO);
        if (invoiceMasterDetailsAuditLogDTO.getInvceId() != null) {
            throw new BadRequestAlertException("A new invoiceMasterDetailsAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return invoiceMasterDetailsAuditLogService
            .save(invoiceMasterDetailsAuditLogDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/invoice-master-details-audit-logs/" + result.getInvceId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getInvceId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /invoice-master-details-audit-logs/:invceId} : Updates an existing invoiceMasterDetailsAuditLog.
     *
     * @param invceId the id of the invoiceMasterDetailsAuditLogDTO to save.
     * @param invoiceMasterDetailsAuditLogDTO the invoiceMasterDetailsAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated invoiceMasterDetailsAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the invoiceMasterDetailsAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the invoiceMasterDetailsAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/invoice-master-details-audit-logs/{invceId}")
    public Mono<ResponseEntity<InvoiceMasterDetailsAuditLogDTO>> updateInvoiceMasterDetailsAuditLog(
        @PathVariable(value = "invceId", required = false) final Long invceId,
        @RequestBody InvoiceMasterDetailsAuditLogDTO invoiceMasterDetailsAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update InvoiceMasterDetailsAuditLog : {}, {}", invceId, invoiceMasterDetailsAuditLogDTO);
        if (invoiceMasterDetailsAuditLogDTO.getInvceId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(invceId, invoiceMasterDetailsAuditLogDTO.getInvceId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return invoiceMasterDetailsAuditLogRepository
            .existsById(invceId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return invoiceMasterDetailsAuditLogService
                    .update(invoiceMasterDetailsAuditLogDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, result.getInvceId().toString())
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /invoice-master-details-audit-logs/:invceId} : Partial updates given fields of an existing invoiceMasterDetailsAuditLog, field will ignore if it is null
     *
     * @param invceId the id of the invoiceMasterDetailsAuditLogDTO to save.
     * @param invoiceMasterDetailsAuditLogDTO the invoiceMasterDetailsAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated invoiceMasterDetailsAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the invoiceMasterDetailsAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the invoiceMasterDetailsAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the invoiceMasterDetailsAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/invoice-master-details-audit-logs/{invceId}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<InvoiceMasterDetailsAuditLogDTO>> partialUpdateInvoiceMasterDetailsAuditLog(
        @PathVariable(value = "invceId", required = false) final Long invceId,
        @RequestBody InvoiceMasterDetailsAuditLogDTO invoiceMasterDetailsAuditLogDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update InvoiceMasterDetailsAuditLog partially : {}, {}",
            invceId,
            invoiceMasterDetailsAuditLogDTO
        );
        if (invoiceMasterDetailsAuditLogDTO.getInvceId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(invceId, invoiceMasterDetailsAuditLogDTO.getInvceId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return invoiceMasterDetailsAuditLogRepository
            .existsById(invceId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<InvoiceMasterDetailsAuditLogDTO> result = invoiceMasterDetailsAuditLogService.partialUpdate(
                    invoiceMasterDetailsAuditLogDTO
                );

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(res ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, res.getInvceId().toString()))
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /invoice-master-details-audit-logs} : get all the invoiceMasterDetailsAuditLogs.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of invoiceMasterDetailsAuditLogs in body.
     */
    @GetMapping("/invoice-master-details-audit-logs")
    public Mono<ResponseEntity<List<InvoiceMasterDetailsAuditLogDTO>>> getAllInvoiceMasterDetailsAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of InvoiceMasterDetailsAuditLogs");
        return invoiceMasterDetailsAuditLogService
            .countAll()
            .zipWith(invoiceMasterDetailsAuditLogService.findAll(pageable).collectList())
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
     * {@code GET  /invoice-master-details-audit-logs/:id} : get the "id" invoiceMasterDetailsAuditLog.
     *
     * @param id the id of the invoiceMasterDetailsAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the invoiceMasterDetailsAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/invoice-master-details-audit-logs/{id}")
    public Mono<ResponseEntity<InvoiceMasterDetailsAuditLogDTO>> getInvoiceMasterDetailsAuditLog(@PathVariable Long id) {
        log.debug("REST request to get InvoiceMasterDetailsAuditLog : {}", id);
        Mono<InvoiceMasterDetailsAuditLogDTO> invoiceMasterDetailsAuditLogDTO = invoiceMasterDetailsAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(invoiceMasterDetailsAuditLogDTO);
    }

    /**
     * {@code DELETE  /invoice-master-details-audit-logs/:id} : delete the "id" invoiceMasterDetailsAuditLog.
     *
     * @param id the id of the invoiceMasterDetailsAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/invoice-master-details-audit-logs/{id}")
    public Mono<ResponseEntity<Void>> deleteInvoiceMasterDetailsAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete InvoiceMasterDetailsAuditLog : {}", id);
        return invoiceMasterDetailsAuditLogService
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
