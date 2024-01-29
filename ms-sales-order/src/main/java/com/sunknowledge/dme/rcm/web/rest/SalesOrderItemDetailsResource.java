package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.SalesOrderItemDetailsRepository;
import com.sunknowledge.dme.rcm.service.SalesOrderItemDetailsService;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderItemDetailsDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.SalesOrderItemDetails}.
 */
@RestController
@RequestMapping("/api")
public class SalesOrderItemDetailsResource {

    private final Logger log = LoggerFactory.getLogger(SalesOrderItemDetailsResource.class);

    private static final String ENTITY_NAME = "salesorderSalesOrderItemDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SalesOrderItemDetailsService salesOrderItemDetailsService;

    private final SalesOrderItemDetailsRepository salesOrderItemDetailsRepository;

    public SalesOrderItemDetailsResource(
        SalesOrderItemDetailsService salesOrderItemDetailsService,
        SalesOrderItemDetailsRepository salesOrderItemDetailsRepository
    ) {
        this.salesOrderItemDetailsService = salesOrderItemDetailsService;
        this.salesOrderItemDetailsRepository = salesOrderItemDetailsRepository;
    }

    /**
     * {@code POST  /sales-order-item-details} : Create a new salesOrderItemDetails.
     *
     * @param salesOrderItemDetailsDTO the salesOrderItemDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new salesOrderItemDetailsDTO, or with status {@code 400 (Bad Request)} if the salesOrderItemDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sales-order-item-details")
    public Mono<ResponseEntity<SalesOrderItemDetailsDTO>> createSalesOrderItemDetails(
        @RequestBody SalesOrderItemDetailsDTO salesOrderItemDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to save SalesOrderItemDetails : {}", salesOrderItemDetailsDTO);
        if (salesOrderItemDetailsDTO.getSalesOrderItemDetailsId() != null) {
            throw new BadRequestAlertException("A new salesOrderItemDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return salesOrderItemDetailsService
            .save(salesOrderItemDetailsDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/sales-order-item-details/" + result.getSalesOrderItemDetailsId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(
                                applicationName,
                                false,
                                ENTITY_NAME,
                                result.getSalesOrderItemDetailsId().toString()
                            )
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /sales-order-item-details/:salesOrderItemDetailsId} : Updates an existing salesOrderItemDetails.
     *
     * @param salesOrderItemDetailsId the id of the salesOrderItemDetailsDTO to save.
     * @param salesOrderItemDetailsDTO the salesOrderItemDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesOrderItemDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the salesOrderItemDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the salesOrderItemDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sales-order-item-details/{salesOrderItemDetailsId}")
    public Mono<ResponseEntity<SalesOrderItemDetailsDTO>> updateSalesOrderItemDetails(
        @PathVariable(value = "salesOrderItemDetailsId", required = false) final Long salesOrderItemDetailsId,
        @RequestBody SalesOrderItemDetailsDTO salesOrderItemDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SalesOrderItemDetails : {}, {}", salesOrderItemDetailsId, salesOrderItemDetailsDTO);
        if (salesOrderItemDetailsDTO.getSalesOrderItemDetailsId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(salesOrderItemDetailsId, salesOrderItemDetailsDTO.getSalesOrderItemDetailsId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return salesOrderItemDetailsRepository
            .existsById(salesOrderItemDetailsId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return salesOrderItemDetailsService
                    .update(salesOrderItemDetailsDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getSalesOrderItemDetailsId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /sales-order-item-details/:salesOrderItemDetailsId} : Partial updates given fields of an existing salesOrderItemDetails, field will ignore if it is null
     *
     * @param salesOrderItemDetailsId the id of the salesOrderItemDetailsDTO to save.
     * @param salesOrderItemDetailsDTO the salesOrderItemDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesOrderItemDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the salesOrderItemDetailsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the salesOrderItemDetailsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the salesOrderItemDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/sales-order-item-details/{salesOrderItemDetailsId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public Mono<ResponseEntity<SalesOrderItemDetailsDTO>> partialUpdateSalesOrderItemDetails(
        @PathVariable(value = "salesOrderItemDetailsId", required = false) final Long salesOrderItemDetailsId,
        @RequestBody SalesOrderItemDetailsDTO salesOrderItemDetailsDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update SalesOrderItemDetails partially : {}, {}",
            salesOrderItemDetailsId,
            salesOrderItemDetailsDTO
        );
        if (salesOrderItemDetailsDTO.getSalesOrderItemDetailsId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(salesOrderItemDetailsId, salesOrderItemDetailsDTO.getSalesOrderItemDetailsId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return salesOrderItemDetailsRepository
            .existsById(salesOrderItemDetailsId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<SalesOrderItemDetailsDTO> result = salesOrderItemDetailsService.partialUpdate(salesOrderItemDetailsDTO);

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
                                    res.getSalesOrderItemDetailsId().toString()
                                )
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /sales-order-item-details} : get all the salesOrderItemDetails.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of salesOrderItemDetails in body.
     */
    @GetMapping("/sales-order-item-details")
    public Mono<ResponseEntity<List<SalesOrderItemDetailsDTO>>> getAllSalesOrderItemDetails(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of SalesOrderItemDetails");
        return salesOrderItemDetailsService
            .countAll()
            .zipWith(salesOrderItemDetailsService.findAll(pageable).collectList())
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
     * {@code GET  /sales-order-item-details/:id} : get the "id" salesOrderItemDetails.
     *
     * @param id the id of the salesOrderItemDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the salesOrderItemDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sales-order-item-details/{id}")
    public Mono<ResponseEntity<SalesOrderItemDetailsDTO>> getSalesOrderItemDetails(@PathVariable Long id) {
        log.debug("REST request to get SalesOrderItemDetails : {}", id);
        Mono<SalesOrderItemDetailsDTO> salesOrderItemDetailsDTO = salesOrderItemDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(salesOrderItemDetailsDTO);
    }

    /**
     * {@code DELETE  /sales-order-item-details/:id} : delete the "id" salesOrderItemDetails.
     *
     * @param id the id of the salesOrderItemDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sales-order-item-details/{id}")
    public Mono<ResponseEntity<Void>> deleteSalesOrderItemDetails(@PathVariable Long id) {
        log.debug("REST request to delete SalesOrderItemDetails : {}", id);
        return salesOrderItemDetailsService
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
