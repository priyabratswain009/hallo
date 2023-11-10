package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.InvoicePostingDetailsAuditLogRepository;
import com.sunknowledge.dme.rcm.service.InvoicePostingDetailsAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.InvoicePostingDetailsAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.InvoicePostingDetailsAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class InvoicePostingDetailsAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(InvoicePostingDetailsAuditLogResource.class);

    private static final String ENTITY_NAME = "salesorderInvoicePostingDetailsAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InvoicePostingDetailsAuditLogService invoicePostingDetailsAuditLogService;

    private final InvoicePostingDetailsAuditLogRepository invoicePostingDetailsAuditLogRepository;

    public InvoicePostingDetailsAuditLogResource(
        InvoicePostingDetailsAuditLogService invoicePostingDetailsAuditLogService,
        InvoicePostingDetailsAuditLogRepository invoicePostingDetailsAuditLogRepository
    ) {
        this.invoicePostingDetailsAuditLogService = invoicePostingDetailsAuditLogService;
        this.invoicePostingDetailsAuditLogRepository = invoicePostingDetailsAuditLogRepository;
    }

    /**
     * {@code POST  /invoice-posting-details-audit-logs} : Create a new invoicePostingDetailsAuditLog.
     *
     * @param invoicePostingDetailsAuditLogDTO the invoicePostingDetailsAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new invoicePostingDetailsAuditLogDTO, or with status {@code 400 (Bad Request)} if the invoicePostingDetailsAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/invoice-posting-details-audit-logs")
    public Mono<ResponseEntity<InvoicePostingDetailsAuditLogDTO>> createInvoicePostingDetailsAuditLog(
        @RequestBody InvoicePostingDetailsAuditLogDTO invoicePostingDetailsAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to save InvoicePostingDetailsAuditLog : {}", invoicePostingDetailsAuditLogDTO);
        if (invoicePostingDetailsAuditLogDTO.getInvLineItmPstingId() != null) {
            throw new BadRequestAlertException("A new invoicePostingDetailsAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return invoicePostingDetailsAuditLogService
            .save(invoicePostingDetailsAuditLogDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/invoice-posting-details-audit-logs/" + result.getInvLineItmPstingId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(
                                applicationName,
                                false,
                                ENTITY_NAME,
                                result.getInvLineItmPstingId().toString()
                            )
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /invoice-posting-details-audit-logs/:invLineItmPstingId} : Updates an existing invoicePostingDetailsAuditLog.
     *
     * @param invLineItmPstingId the id of the invoicePostingDetailsAuditLogDTO to save.
     * @param invoicePostingDetailsAuditLogDTO the invoicePostingDetailsAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated invoicePostingDetailsAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the invoicePostingDetailsAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the invoicePostingDetailsAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/invoice-posting-details-audit-logs/{invLineItmPstingId}")
    public Mono<ResponseEntity<InvoicePostingDetailsAuditLogDTO>> updateInvoicePostingDetailsAuditLog(
        @PathVariable(value = "invLineItmPstingId", required = false) final Long invLineItmPstingId,
        @RequestBody InvoicePostingDetailsAuditLogDTO invoicePostingDetailsAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update InvoicePostingDetailsAuditLog : {}, {}", invLineItmPstingId, invoicePostingDetailsAuditLogDTO);
        if (invoicePostingDetailsAuditLogDTO.getInvLineItmPstingId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(invLineItmPstingId, invoicePostingDetailsAuditLogDTO.getInvLineItmPstingId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return invoicePostingDetailsAuditLogRepository
            .existsById(invLineItmPstingId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return invoicePostingDetailsAuditLogService
                    .update(invoicePostingDetailsAuditLogDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getInvLineItmPstingId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /invoice-posting-details-audit-logs/:invLineItmPstingId} : Partial updates given fields of an existing invoicePostingDetailsAuditLog, field will ignore if it is null
     *
     * @param invLineItmPstingId the id of the invoicePostingDetailsAuditLogDTO to save.
     * @param invoicePostingDetailsAuditLogDTO the invoicePostingDetailsAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated invoicePostingDetailsAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the invoicePostingDetailsAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the invoicePostingDetailsAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the invoicePostingDetailsAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/invoice-posting-details-audit-logs/{invLineItmPstingId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public Mono<ResponseEntity<InvoicePostingDetailsAuditLogDTO>> partialUpdateInvoicePostingDetailsAuditLog(
        @PathVariable(value = "invLineItmPstingId", required = false) final Long invLineItmPstingId,
        @RequestBody InvoicePostingDetailsAuditLogDTO invoicePostingDetailsAuditLogDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update InvoicePostingDetailsAuditLog partially : {}, {}",
            invLineItmPstingId,
            invoicePostingDetailsAuditLogDTO
        );
        if (invoicePostingDetailsAuditLogDTO.getInvLineItmPstingId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(invLineItmPstingId, invoicePostingDetailsAuditLogDTO.getInvLineItmPstingId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return invoicePostingDetailsAuditLogRepository
            .existsById(invLineItmPstingId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<InvoicePostingDetailsAuditLogDTO> result = invoicePostingDetailsAuditLogService.partialUpdate(
                    invoicePostingDetailsAuditLogDTO
                );

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(res ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    res.getInvLineItmPstingId().toString()
                                )
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /invoice-posting-details-audit-logs} : get all the invoicePostingDetailsAuditLogs.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of invoicePostingDetailsAuditLogs in body.
     */
    @GetMapping("/invoice-posting-details-audit-logs")
    public Mono<ResponseEntity<List<InvoicePostingDetailsAuditLogDTO>>> getAllInvoicePostingDetailsAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of InvoicePostingDetailsAuditLogs");
        return invoicePostingDetailsAuditLogService
            .countAll()
            .zipWith(invoicePostingDetailsAuditLogService.findAll(pageable).collectList())
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
     * {@code GET  /invoice-posting-details-audit-logs/:id} : get the "id" invoicePostingDetailsAuditLog.
     *
     * @param id the id of the invoicePostingDetailsAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the invoicePostingDetailsAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/invoice-posting-details-audit-logs/{id}")
    public Mono<ResponseEntity<InvoicePostingDetailsAuditLogDTO>> getInvoicePostingDetailsAuditLog(@PathVariable Long id) {
        log.debug("REST request to get InvoicePostingDetailsAuditLog : {}", id);
        Mono<InvoicePostingDetailsAuditLogDTO> invoicePostingDetailsAuditLogDTO = invoicePostingDetailsAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(invoicePostingDetailsAuditLogDTO);
    }

    /**
     * {@code DELETE  /invoice-posting-details-audit-logs/:id} : delete the "id" invoicePostingDetailsAuditLog.
     *
     * @param id the id of the invoicePostingDetailsAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/invoice-posting-details-audit-logs/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteInvoicePostingDetailsAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete InvoicePostingDetailsAuditLog : {}", id);
        return invoicePostingDetailsAuditLogService
            .delete(id)
            .map(result ->
                ResponseEntity
                    .noContent()
                    .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                    .build()
            );
    }
}
