package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.InvoiceLineItemDetailsAuditLogRepository;
import com.sunknowledge.dme.rcm.service.InvoiceLineItemDetailsAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.InvoiceLineItemDetailsAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.InvoiceLineItemDetailsAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class InvoiceLineItemDetailsAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(InvoiceLineItemDetailsAuditLogResource.class);

    private static final String ENTITY_NAME = "salesorderInvoiceLineItemDetailsAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InvoiceLineItemDetailsAuditLogService invoiceLineItemDetailsAuditLogService;

    private final InvoiceLineItemDetailsAuditLogRepository invoiceLineItemDetailsAuditLogRepository;

    public InvoiceLineItemDetailsAuditLogResource(
        InvoiceLineItemDetailsAuditLogService invoiceLineItemDetailsAuditLogService,
        InvoiceLineItemDetailsAuditLogRepository invoiceLineItemDetailsAuditLogRepository
    ) {
        this.invoiceLineItemDetailsAuditLogService = invoiceLineItemDetailsAuditLogService;
        this.invoiceLineItemDetailsAuditLogRepository = invoiceLineItemDetailsAuditLogRepository;
    }

    /**
     * {@code POST  /invoice-line-item-details-audit-logs} : Create a new invoiceLineItemDetailsAuditLog.
     *
     * @param invoiceLineItemDetailsAuditLogDTO the invoiceLineItemDetailsAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new invoiceLineItemDetailsAuditLogDTO, or with status {@code 400 (Bad Request)} if the invoiceLineItemDetailsAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/invoice-line-item-details-audit-logs")
    public Mono<ResponseEntity<InvoiceLineItemDetailsAuditLogDTO>> createInvoiceLineItemDetailsAuditLog(
        @RequestBody InvoiceLineItemDetailsAuditLogDTO invoiceLineItemDetailsAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to save InvoiceLineItemDetailsAuditLog : {}", invoiceLineItemDetailsAuditLogDTO);
        if (invoiceLineItemDetailsAuditLogDTO.getInvceLinItmDetilId() != null) {
            throw new BadRequestAlertException("A new invoiceLineItemDetailsAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return invoiceLineItemDetailsAuditLogService
            .save(invoiceLineItemDetailsAuditLogDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/invoice-line-item-details-audit-logs/" + result.getInvceLinItmDetilId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(
                                applicationName,
                                false,
                                ENTITY_NAME,
                                result.getInvceLinItmDetilId().toString()
                            )
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /invoice-line-item-details-audit-logs/:invceLinItmDetilId} : Updates an existing invoiceLineItemDetailsAuditLog.
     *
     * @param invceLinItmDetilId the id of the invoiceLineItemDetailsAuditLogDTO to save.
     * @param invoiceLineItemDetailsAuditLogDTO the invoiceLineItemDetailsAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated invoiceLineItemDetailsAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the invoiceLineItemDetailsAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the invoiceLineItemDetailsAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/invoice-line-item-details-audit-logs/{invceLinItmDetilId}")
    public Mono<ResponseEntity<InvoiceLineItemDetailsAuditLogDTO>> updateInvoiceLineItemDetailsAuditLog(
        @PathVariable(value = "invceLinItmDetilId", required = false) final Long invceLinItmDetilId,
        @RequestBody InvoiceLineItemDetailsAuditLogDTO invoiceLineItemDetailsAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update InvoiceLineItemDetailsAuditLog : {}, {}", invceLinItmDetilId, invoiceLineItemDetailsAuditLogDTO);
        if (invoiceLineItemDetailsAuditLogDTO.getInvceLinItmDetilId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(invceLinItmDetilId, invoiceLineItemDetailsAuditLogDTO.getInvceLinItmDetilId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return invoiceLineItemDetailsAuditLogRepository
            .existsById(invceLinItmDetilId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return invoiceLineItemDetailsAuditLogService
                    .update(invoiceLineItemDetailsAuditLogDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getInvceLinItmDetilId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /invoice-line-item-details-audit-logs/:invceLinItmDetilId} : Partial updates given fields of an existing invoiceLineItemDetailsAuditLog, field will ignore if it is null
     *
     * @param invceLinItmDetilId the id of the invoiceLineItemDetailsAuditLogDTO to save.
     * @param invoiceLineItemDetailsAuditLogDTO the invoiceLineItemDetailsAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated invoiceLineItemDetailsAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the invoiceLineItemDetailsAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the invoiceLineItemDetailsAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the invoiceLineItemDetailsAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/invoice-line-item-details-audit-logs/{invceLinItmDetilId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public Mono<ResponseEntity<InvoiceLineItemDetailsAuditLogDTO>> partialUpdateInvoiceLineItemDetailsAuditLog(
        @PathVariable(value = "invceLinItmDetilId", required = false) final Long invceLinItmDetilId,
        @RequestBody InvoiceLineItemDetailsAuditLogDTO invoiceLineItemDetailsAuditLogDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update InvoiceLineItemDetailsAuditLog partially : {}, {}",
            invceLinItmDetilId,
            invoiceLineItemDetailsAuditLogDTO
        );
        if (invoiceLineItemDetailsAuditLogDTO.getInvceLinItmDetilId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(invceLinItmDetilId, invoiceLineItemDetailsAuditLogDTO.getInvceLinItmDetilId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return invoiceLineItemDetailsAuditLogRepository
            .existsById(invceLinItmDetilId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<InvoiceLineItemDetailsAuditLogDTO> result = invoiceLineItemDetailsAuditLogService.partialUpdate(
                    invoiceLineItemDetailsAuditLogDTO
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
                                    res.getInvceLinItmDetilId().toString()
                                )
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /invoice-line-item-details-audit-logs} : get all the invoiceLineItemDetailsAuditLogs.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of invoiceLineItemDetailsAuditLogs in body.
     */
    @GetMapping("/invoice-line-item-details-audit-logs")
    public Mono<ResponseEntity<List<InvoiceLineItemDetailsAuditLogDTO>>> getAllInvoiceLineItemDetailsAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of InvoiceLineItemDetailsAuditLogs");
        return invoiceLineItemDetailsAuditLogService
            .countAll()
            .zipWith(invoiceLineItemDetailsAuditLogService.findAll(pageable).collectList())
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
     * {@code GET  /invoice-line-item-details-audit-logs/:id} : get the "id" invoiceLineItemDetailsAuditLog.
     *
     * @param id the id of the invoiceLineItemDetailsAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the invoiceLineItemDetailsAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/invoice-line-item-details-audit-logs/{id}")
    public Mono<ResponseEntity<InvoiceLineItemDetailsAuditLogDTO>> getInvoiceLineItemDetailsAuditLog(@PathVariable Long id) {
        log.debug("REST request to get InvoiceLineItemDetailsAuditLog : {}", id);
        Mono<InvoiceLineItemDetailsAuditLogDTO> invoiceLineItemDetailsAuditLogDTO = invoiceLineItemDetailsAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(invoiceLineItemDetailsAuditLogDTO);
    }

    /**
     * {@code DELETE  /invoice-line-item-details-audit-logs/:id} : delete the "id" invoiceLineItemDetailsAuditLog.
     *
     * @param id the id of the invoiceLineItemDetailsAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/invoice-line-item-details-audit-logs/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteInvoiceLineItemDetailsAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete InvoiceLineItemDetailsAuditLog : {}", id);
        return invoiceLineItemDetailsAuditLogService
            .delete(id)
            .map(result ->
                ResponseEntity
                    .noContent()
                    .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                    .build()
            );
    }
}
