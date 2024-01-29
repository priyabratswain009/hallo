package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.InvoicePostingDetailsRepository;
import com.sunknowledge.dme.rcm.service.InvoicePostingDetailsService;
import com.sunknowledge.dme.rcm.service.dto.InvoicePostingDetailsDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.InvoicePostingDetails}.
 */
@RestController
@RequestMapping("/api")
public class InvoicePostingDetailsResource {

    private final Logger log = LoggerFactory.getLogger(InvoicePostingDetailsResource.class);

    private static final String ENTITY_NAME = "salesorderInvoicePostingDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InvoicePostingDetailsService invoicePostingDetailsService;

    private final InvoicePostingDetailsRepository invoicePostingDetailsRepository;

    public InvoicePostingDetailsResource(
        InvoicePostingDetailsService invoicePostingDetailsService,
        InvoicePostingDetailsRepository invoicePostingDetailsRepository
    ) {
        this.invoicePostingDetailsService = invoicePostingDetailsService;
        this.invoicePostingDetailsRepository = invoicePostingDetailsRepository;
    }

    /**
     * {@code POST  /invoice-posting-details} : Create a new invoicePostingDetails.
     *
     * @param invoicePostingDetailsDTO the invoicePostingDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new invoicePostingDetailsDTO, or with status {@code 400 (Bad Request)} if the invoicePostingDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/invoice-posting-details")
    public Mono<ResponseEntity<InvoicePostingDetailsDTO>> createInvoicePostingDetails(
        @RequestBody InvoicePostingDetailsDTO invoicePostingDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to save InvoicePostingDetails : {}", invoicePostingDetailsDTO);
        if (invoicePostingDetailsDTO.getInvoiceLineItemPostingId() != null) {
            throw new BadRequestAlertException("A new invoicePostingDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return invoicePostingDetailsService
            .save(invoicePostingDetailsDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/invoice-posting-details/" + result.getInvoiceLineItemPostingId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(
                                applicationName,
                                false,
                                ENTITY_NAME,
                                result.getInvoiceLineItemPostingId().toString()
                            )
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /invoice-posting-details/:invoiceLineItemPostingId} : Updates an existing invoicePostingDetails.
     *
     * @param invoiceLineItemPostingId the id of the invoicePostingDetailsDTO to save.
     * @param invoicePostingDetailsDTO the invoicePostingDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated invoicePostingDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the invoicePostingDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the invoicePostingDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/invoice-posting-details/{invoiceLineItemPostingId}")
    public Mono<ResponseEntity<InvoicePostingDetailsDTO>> updateInvoicePostingDetails(
        @PathVariable(value = "invoiceLineItemPostingId", required = false) final Long invoiceLineItemPostingId,
        @RequestBody InvoicePostingDetailsDTO invoicePostingDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update InvoicePostingDetails : {}, {}", invoiceLineItemPostingId, invoicePostingDetailsDTO);
        if (invoicePostingDetailsDTO.getInvoiceLineItemPostingId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(invoiceLineItemPostingId, invoicePostingDetailsDTO.getInvoiceLineItemPostingId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return invoicePostingDetailsRepository
            .existsById(invoiceLineItemPostingId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return invoicePostingDetailsService
                    .update(invoicePostingDetailsDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getInvoiceLineItemPostingId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /invoice-posting-details/:invoiceLineItemPostingId} : Partial updates given fields of an existing invoicePostingDetails, field will ignore if it is null
     *
     * @param invoiceLineItemPostingId the id of the invoicePostingDetailsDTO to save.
     * @param invoicePostingDetailsDTO the invoicePostingDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated invoicePostingDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the invoicePostingDetailsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the invoicePostingDetailsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the invoicePostingDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/invoice-posting-details/{invoiceLineItemPostingId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public Mono<ResponseEntity<InvoicePostingDetailsDTO>> partialUpdateInvoicePostingDetails(
        @PathVariable(value = "invoiceLineItemPostingId", required = false) final Long invoiceLineItemPostingId,
        @RequestBody InvoicePostingDetailsDTO invoicePostingDetailsDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update InvoicePostingDetails partially : {}, {}",
            invoiceLineItemPostingId,
            invoicePostingDetailsDTO
        );
        if (invoicePostingDetailsDTO.getInvoiceLineItemPostingId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(invoiceLineItemPostingId, invoicePostingDetailsDTO.getInvoiceLineItemPostingId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return invoicePostingDetailsRepository
            .existsById(invoiceLineItemPostingId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<InvoicePostingDetailsDTO> result = invoicePostingDetailsService.partialUpdate(invoicePostingDetailsDTO);

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
                                    res.getInvoiceLineItemPostingId().toString()
                                )
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /invoice-posting-details} : get all the invoicePostingDetails.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of invoicePostingDetails in body.
     */
    @GetMapping("/invoice-posting-details")
    public Mono<ResponseEntity<List<InvoicePostingDetailsDTO>>> getAllInvoicePostingDetails(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of InvoicePostingDetails");
        return invoicePostingDetailsService
            .countAll()
            .zipWith(invoicePostingDetailsService.findAll(pageable).collectList())
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
     * {@code GET  /invoice-posting-details/:id} : get the "id" invoicePostingDetails.
     *
     * @param id the id of the invoicePostingDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the invoicePostingDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/invoice-posting-details/{id}")
    public Mono<ResponseEntity<InvoicePostingDetailsDTO>> getInvoicePostingDetails(@PathVariable Long id) {
        log.debug("REST request to get InvoicePostingDetails : {}", id);
        Mono<InvoicePostingDetailsDTO> invoicePostingDetailsDTO = invoicePostingDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(invoicePostingDetailsDTO);
    }

    /**
     * {@code DELETE  /invoice-posting-details/:id} : delete the "id" invoicePostingDetails.
     *
     * @param id the id of the invoicePostingDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/invoice-posting-details/{id}")
    public Mono<ResponseEntity<Void>> deleteInvoicePostingDetails(@PathVariable Long id) {
        log.debug("REST request to delete InvoicePostingDetails : {}", id);
        return invoicePostingDetailsService
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
