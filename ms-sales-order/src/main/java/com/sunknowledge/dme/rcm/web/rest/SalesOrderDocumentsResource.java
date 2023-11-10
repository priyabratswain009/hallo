package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.SalesOrderDocumentsRepository;
import com.sunknowledge.dme.rcm.service.SalesOrderDocumentsService;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderDocumentsDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.SalesOrderDocuments}.
 */
@RestController
@RequestMapping("/api")
public class SalesOrderDocumentsResource {

    private final Logger log = LoggerFactory.getLogger(SalesOrderDocumentsResource.class);

    private static final String ENTITY_NAME = "salesorderSalesOrderDocuments";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SalesOrderDocumentsService salesOrderDocumentsService;

    private final SalesOrderDocumentsRepository salesOrderDocumentsRepository;

    public SalesOrderDocumentsResource(
        SalesOrderDocumentsService salesOrderDocumentsService,
        SalesOrderDocumentsRepository salesOrderDocumentsRepository
    ) {
        this.salesOrderDocumentsService = salesOrderDocumentsService;
        this.salesOrderDocumentsRepository = salesOrderDocumentsRepository;
    }

    /**
     * {@code POST  /sales-order-documents} : Create a new salesOrderDocuments.
     *
     * @param salesOrderDocumentsDTO the salesOrderDocumentsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new salesOrderDocumentsDTO, or with status {@code 400 (Bad Request)} if the salesOrderDocuments has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sales-order-documents")
    public Mono<ResponseEntity<SalesOrderDocumentsDTO>> createSalesOrderDocuments(
        @RequestBody SalesOrderDocumentsDTO salesOrderDocumentsDTO
    ) throws URISyntaxException {
        log.debug("REST request to save SalesOrderDocuments : {}", salesOrderDocumentsDTO);
        if (salesOrderDocumentsDTO.getSalesOrderDocumentId() != null) {
            throw new BadRequestAlertException("A new salesOrderDocuments cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return salesOrderDocumentsService
            .save(salesOrderDocumentsDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/sales-order-documents/" + result.getSalesOrderDocumentId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(
                                applicationName,
                                false,
                                ENTITY_NAME,
                                result.getSalesOrderDocumentId().toString()
                            )
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /sales-order-documents/:salesOrderDocumentId} : Updates an existing salesOrderDocuments.
     *
     * @param salesOrderDocumentId the id of the salesOrderDocumentsDTO to save.
     * @param salesOrderDocumentsDTO the salesOrderDocumentsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesOrderDocumentsDTO,
     * or with status {@code 400 (Bad Request)} if the salesOrderDocumentsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the salesOrderDocumentsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sales-order-documents/{salesOrderDocumentId}")
    public Mono<ResponseEntity<SalesOrderDocumentsDTO>> updateSalesOrderDocuments(
        @PathVariable(value = "salesOrderDocumentId", required = false) final Long salesOrderDocumentId,
        @RequestBody SalesOrderDocumentsDTO salesOrderDocumentsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SalesOrderDocuments : {}, {}", salesOrderDocumentId, salesOrderDocumentsDTO);
        if (salesOrderDocumentsDTO.getSalesOrderDocumentId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(salesOrderDocumentId, salesOrderDocumentsDTO.getSalesOrderDocumentId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return salesOrderDocumentsRepository
            .existsById(salesOrderDocumentId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return salesOrderDocumentsService
                    .update(salesOrderDocumentsDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getSalesOrderDocumentId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /sales-order-documents/:salesOrderDocumentId} : Partial updates given fields of an existing salesOrderDocuments, field will ignore if it is null
     *
     * @param salesOrderDocumentId the id of the salesOrderDocumentsDTO to save.
     * @param salesOrderDocumentsDTO the salesOrderDocumentsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesOrderDocumentsDTO,
     * or with status {@code 400 (Bad Request)} if the salesOrderDocumentsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the salesOrderDocumentsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the salesOrderDocumentsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/sales-order-documents/{salesOrderDocumentId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public Mono<ResponseEntity<SalesOrderDocumentsDTO>> partialUpdateSalesOrderDocuments(
        @PathVariable(value = "salesOrderDocumentId", required = false) final Long salesOrderDocumentId,
        @RequestBody SalesOrderDocumentsDTO salesOrderDocumentsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update SalesOrderDocuments partially : {}, {}", salesOrderDocumentId, salesOrderDocumentsDTO);
        if (salesOrderDocumentsDTO.getSalesOrderDocumentId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(salesOrderDocumentId, salesOrderDocumentsDTO.getSalesOrderDocumentId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return salesOrderDocumentsRepository
            .existsById(salesOrderDocumentId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<SalesOrderDocumentsDTO> result = salesOrderDocumentsService.partialUpdate(salesOrderDocumentsDTO);

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
                                    res.getSalesOrderDocumentId().toString()
                                )
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /sales-order-documents} : get all the salesOrderDocuments.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of salesOrderDocuments in body.
     */
    @GetMapping("/sales-order-documents")
    public Mono<ResponseEntity<List<SalesOrderDocumentsDTO>>> getAllSalesOrderDocuments(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of SalesOrderDocuments");
        return salesOrderDocumentsService
            .countAll()
            .zipWith(salesOrderDocumentsService.findAll(pageable).collectList())
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
     * {@code GET  /sales-order-documents/:id} : get the "id" salesOrderDocuments.
     *
     * @param id the id of the salesOrderDocumentsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the salesOrderDocumentsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sales-order-documents/{id}")
    public Mono<ResponseEntity<SalesOrderDocumentsDTO>> getSalesOrderDocuments(@PathVariable Long id) {
        log.debug("REST request to get SalesOrderDocuments : {}", id);
        Mono<SalesOrderDocumentsDTO> salesOrderDocumentsDTO = salesOrderDocumentsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(salesOrderDocumentsDTO);
    }

    /**
     * {@code DELETE  /sales-order-documents/:id} : delete the "id" salesOrderDocuments.
     *
     * @param id the id of the salesOrderDocumentsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sales-order-documents/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteSalesOrderDocuments(@PathVariable Long id) {
        log.debug("REST request to delete SalesOrderDocuments : {}", id);
        return salesOrderDocumentsService
            .delete(id)
            .map(result ->
                ResponseEntity
                    .noContent()
                    .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                    .build()
            );
    }
}
