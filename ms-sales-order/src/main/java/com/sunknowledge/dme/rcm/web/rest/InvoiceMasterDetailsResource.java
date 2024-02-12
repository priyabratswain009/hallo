package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.InvoiceMasterDetailsRepository;
import com.sunknowledge.dme.rcm.service.InvoiceMasterDetailsService;
import com.sunknowledge.dme.rcm.service.dto.InvoiceMasterDetailsDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.InvoiceMasterDetails}.
 */
@RestController
@RequestMapping("/api")
public class InvoiceMasterDetailsResource {

    private final Logger log = LoggerFactory.getLogger(InvoiceMasterDetailsResource.class);

    private static final String ENTITY_NAME = "salesorderInvoiceMasterDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InvoiceMasterDetailsService invoiceMasterDetailsService;

    private final InvoiceMasterDetailsRepository invoiceMasterDetailsRepository;

    public InvoiceMasterDetailsResource(
        InvoiceMasterDetailsService invoiceMasterDetailsService,
        InvoiceMasterDetailsRepository invoiceMasterDetailsRepository
    ) {
        this.invoiceMasterDetailsService = invoiceMasterDetailsService;
        this.invoiceMasterDetailsRepository = invoiceMasterDetailsRepository;
    }

    /**
     * {@code POST  /invoice-master-details} : Create a new invoiceMasterDetails.
     *
     * @param invoiceMasterDetailsDTO the invoiceMasterDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new invoiceMasterDetailsDTO, or with status {@code 400 (Bad Request)} if the invoiceMasterDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/invoice-master-details")
    public Mono<ResponseEntity<InvoiceMasterDetailsDTO>> createInvoiceMasterDetails(
        @RequestBody InvoiceMasterDetailsDTO invoiceMasterDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to save InvoiceMasterDetails : {}", invoiceMasterDetailsDTO);
        if (invoiceMasterDetailsDTO.getInvoiceId() != null) {
            throw new BadRequestAlertException("A new invoiceMasterDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return invoiceMasterDetailsService
            .save(invoiceMasterDetailsDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/invoice-master-details/" + result.getInvoiceId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getInvoiceId().toString())
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /invoice-master-details/:invoiceId} : Updates an existing invoiceMasterDetails.
     *
     * @param invoiceId the id of the invoiceMasterDetailsDTO to save.
     * @param invoiceMasterDetailsDTO the invoiceMasterDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated invoiceMasterDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the invoiceMasterDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the invoiceMasterDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/invoice-master-details/{invoiceId}")
    public Mono<ResponseEntity<InvoiceMasterDetailsDTO>> updateInvoiceMasterDetails(
        @PathVariable(value = "invoiceId", required = false) final Long invoiceId,
        @RequestBody InvoiceMasterDetailsDTO invoiceMasterDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update InvoiceMasterDetails : {}, {}", invoiceId, invoiceMasterDetailsDTO);
        if (invoiceMasterDetailsDTO.getInvoiceId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(invoiceId, invoiceMasterDetailsDTO.getInvoiceId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return invoiceMasterDetailsRepository
            .existsById(invoiceId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return invoiceMasterDetailsService
                    .update(invoiceMasterDetailsDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, result.getInvoiceId().toString())
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /invoice-master-details/:invoiceId} : Partial updates given fields of an existing invoiceMasterDetails, field will ignore if it is null
     *
     * @param invoiceId the id of the invoiceMasterDetailsDTO to save.
     * @param invoiceMasterDetailsDTO the invoiceMasterDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated invoiceMasterDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the invoiceMasterDetailsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the invoiceMasterDetailsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the invoiceMasterDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/invoice-master-details/{invoiceId}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<InvoiceMasterDetailsDTO>> partialUpdateInvoiceMasterDetails(
        @PathVariable(value = "invoiceId", required = false) final Long invoiceId,
        @RequestBody InvoiceMasterDetailsDTO invoiceMasterDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update InvoiceMasterDetails partially : {}, {}", invoiceId, invoiceMasterDetailsDTO);
        if (invoiceMasterDetailsDTO.getInvoiceId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(invoiceId, invoiceMasterDetailsDTO.getInvoiceId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return invoiceMasterDetailsRepository
            .existsById(invoiceId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<InvoiceMasterDetailsDTO> result = invoiceMasterDetailsService.partialUpdate(invoiceMasterDetailsDTO);

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(res ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, res.getInvoiceId().toString()))
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /invoice-master-details} : get all the invoiceMasterDetails.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of invoiceMasterDetails in body.
     */
    @GetMapping("/invoice-master-details")
    public Mono<ResponseEntity<List<InvoiceMasterDetailsDTO>>> getAllInvoiceMasterDetails(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of InvoiceMasterDetails");
        return invoiceMasterDetailsService
            .countAll()
            .zipWith(invoiceMasterDetailsService.findAll(pageable).collectList())
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
     * {@code GET  /invoice-master-details/:id} : get the "id" invoiceMasterDetails.
     *
     * @param id the id of the invoiceMasterDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the invoiceMasterDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/invoice-master-details/{id}")
    public Mono<ResponseEntity<InvoiceMasterDetailsDTO>> getInvoiceMasterDetails(@PathVariable Long id) {
        log.debug("REST request to get InvoiceMasterDetails : {}", id);
        Mono<InvoiceMasterDetailsDTO> invoiceMasterDetailsDTO = invoiceMasterDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(invoiceMasterDetailsDTO);
    }

    /**
     * {@code DELETE  /invoice-master-details/:id} : delete the "id" invoiceMasterDetails.
     *
     * @param id the id of the invoiceMasterDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/invoice-master-details/{id}")
    public Mono<ResponseEntity<Void>> deleteInvoiceMasterDetails(@PathVariable Long id) {
        log.debug("REST request to delete InvoiceMasterDetails : {}", id);
        return invoiceMasterDetailsService
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
