package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.InvoiceLineItemDetailsRepository;
import com.sunknowledge.dme.rcm.service.InvoiceLineItemDetailsService;
import com.sunknowledge.dme.rcm.service.dto.InvoiceLineItemDetailsDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.InvoiceLineItemDetails}.
 */
@RestController
@RequestMapping("/api")
public class InvoiceLineItemDetailsResource {

    private final Logger log = LoggerFactory.getLogger(InvoiceLineItemDetailsResource.class);

    private static final String ENTITY_NAME = "salesorderInvoiceLineItemDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InvoiceLineItemDetailsService invoiceLineItemDetailsService;

    private final InvoiceLineItemDetailsRepository invoiceLineItemDetailsRepository;

    public InvoiceLineItemDetailsResource(
        InvoiceLineItemDetailsService invoiceLineItemDetailsService,
        InvoiceLineItemDetailsRepository invoiceLineItemDetailsRepository
    ) {
        this.invoiceLineItemDetailsService = invoiceLineItemDetailsService;
        this.invoiceLineItemDetailsRepository = invoiceLineItemDetailsRepository;
    }

    /**
     * {@code POST  /invoice-line-item-details} : Create a new invoiceLineItemDetails.
     *
     * @param invoiceLineItemDetailsDTO the invoiceLineItemDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new invoiceLineItemDetailsDTO, or with status {@code 400 (Bad Request)} if the invoiceLineItemDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/invoice-line-item-details")
    public Mono<ResponseEntity<InvoiceLineItemDetailsDTO>> createInvoiceLineItemDetails(
        @RequestBody InvoiceLineItemDetailsDTO invoiceLineItemDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to save InvoiceLineItemDetails : {}", invoiceLineItemDetailsDTO);
        if (invoiceLineItemDetailsDTO.getInvoiceLineItemDetailsId() != null) {
            throw new BadRequestAlertException("A new invoiceLineItemDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return invoiceLineItemDetailsService
            .save(invoiceLineItemDetailsDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/invoice-line-item-details/" + result.getInvoiceLineItemDetailsId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(
                                applicationName,
                                false,
                                ENTITY_NAME,
                                result.getInvoiceLineItemDetailsId().toString()
                            )
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /invoice-line-item-details/:invoiceLineItemDetailsId} : Updates an existing invoiceLineItemDetails.
     *
     * @param invoiceLineItemDetailsId the id of the invoiceLineItemDetailsDTO to save.
     * @param invoiceLineItemDetailsDTO the invoiceLineItemDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated invoiceLineItemDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the invoiceLineItemDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the invoiceLineItemDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/invoice-line-item-details/{invoiceLineItemDetailsId}")
    public Mono<ResponseEntity<InvoiceLineItemDetailsDTO>> updateInvoiceLineItemDetails(
        @PathVariable(value = "invoiceLineItemDetailsId", required = false) final Long invoiceLineItemDetailsId,
        @RequestBody InvoiceLineItemDetailsDTO invoiceLineItemDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update InvoiceLineItemDetails : {}, {}", invoiceLineItemDetailsId, invoiceLineItemDetailsDTO);
        if (invoiceLineItemDetailsDTO.getInvoiceLineItemDetailsId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(invoiceLineItemDetailsId, invoiceLineItemDetailsDTO.getInvoiceLineItemDetailsId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return invoiceLineItemDetailsRepository
            .existsById(invoiceLineItemDetailsId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return invoiceLineItemDetailsService
                    .update(invoiceLineItemDetailsDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getInvoiceLineItemDetailsId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /invoice-line-item-details/:invoiceLineItemDetailsId} : Partial updates given fields of an existing invoiceLineItemDetails, field will ignore if it is null
     *
     * @param invoiceLineItemDetailsId the id of the invoiceLineItemDetailsDTO to save.
     * @param invoiceLineItemDetailsDTO the invoiceLineItemDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated invoiceLineItemDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the invoiceLineItemDetailsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the invoiceLineItemDetailsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the invoiceLineItemDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/invoice-line-item-details/{invoiceLineItemDetailsId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public Mono<ResponseEntity<InvoiceLineItemDetailsDTO>> partialUpdateInvoiceLineItemDetails(
        @PathVariable(value = "invoiceLineItemDetailsId", required = false) final Long invoiceLineItemDetailsId,
        @RequestBody InvoiceLineItemDetailsDTO invoiceLineItemDetailsDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update InvoiceLineItemDetails partially : {}, {}",
            invoiceLineItemDetailsId,
            invoiceLineItemDetailsDTO
        );
        if (invoiceLineItemDetailsDTO.getInvoiceLineItemDetailsId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(invoiceLineItemDetailsId, invoiceLineItemDetailsDTO.getInvoiceLineItemDetailsId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return invoiceLineItemDetailsRepository
            .existsById(invoiceLineItemDetailsId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<InvoiceLineItemDetailsDTO> result = invoiceLineItemDetailsService.partialUpdate(invoiceLineItemDetailsDTO);

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
                                    res.getInvoiceLineItemDetailsId().toString()
                                )
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /invoice-line-item-details} : get all the invoiceLineItemDetails.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of invoiceLineItemDetails in body.
     */
    @GetMapping("/invoice-line-item-details")
    public Mono<ResponseEntity<List<InvoiceLineItemDetailsDTO>>> getAllInvoiceLineItemDetails(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of InvoiceLineItemDetails");
        return invoiceLineItemDetailsService
            .countAll()
            .zipWith(invoiceLineItemDetailsService.findAll(pageable).collectList())
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
     * {@code GET  /invoice-line-item-details/:id} : get the "id" invoiceLineItemDetails.
     *
     * @param id the id of the invoiceLineItemDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the invoiceLineItemDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/invoice-line-item-details/{id}")
    public Mono<ResponseEntity<InvoiceLineItemDetailsDTO>> getInvoiceLineItemDetails(@PathVariable Long id) {
        log.debug("REST request to get InvoiceLineItemDetails : {}", id);
        Mono<InvoiceLineItemDetailsDTO> invoiceLineItemDetailsDTO = invoiceLineItemDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(invoiceLineItemDetailsDTO);
    }

    /**
     * {@code DELETE  /invoice-line-item-details/:id} : delete the "id" invoiceLineItemDetails.
     *
     * @param id the id of the invoiceLineItemDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/invoice-line-item-details/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteInvoiceLineItemDetails(@PathVariable Long id) {
        log.debug("REST request to delete InvoiceLineItemDetails : {}", id);
        return invoiceLineItemDetailsService
            .delete(id)
            .map(result ->
                ResponseEntity
                    .noContent()
                    .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                    .build()
            );
    }
}
