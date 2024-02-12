package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.SalesOrderFinancialDetailsRepository;
import com.sunknowledge.dme.rcm.service.SalesOrderFinancialDetailsService;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderFinancialDetailsDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.SalesOrderFinancialDetails}.
 */
@RestController
@RequestMapping("/api")
public class SalesOrderFinancialDetailsResource {

    private final Logger log = LoggerFactory.getLogger(SalesOrderFinancialDetailsResource.class);

    private static final String ENTITY_NAME = "salesorderSalesOrderFinancialDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SalesOrderFinancialDetailsService salesOrderFinancialDetailsService;

    private final SalesOrderFinancialDetailsRepository salesOrderFinancialDetailsRepository;

    public SalesOrderFinancialDetailsResource(
        SalesOrderFinancialDetailsService salesOrderFinancialDetailsService,
        SalesOrderFinancialDetailsRepository salesOrderFinancialDetailsRepository
    ) {
        this.salesOrderFinancialDetailsService = salesOrderFinancialDetailsService;
        this.salesOrderFinancialDetailsRepository = salesOrderFinancialDetailsRepository;
    }

    /**
     * {@code POST  /sales-order-financial-details} : Create a new salesOrderFinancialDetails.
     *
     * @param salesOrderFinancialDetailsDTO the salesOrderFinancialDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new salesOrderFinancialDetailsDTO, or with status {@code 400 (Bad Request)} if the salesOrderFinancialDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sales-order-financial-details")
    public Mono<ResponseEntity<SalesOrderFinancialDetailsDTO>> createSalesOrderFinancialDetails(
        @RequestBody SalesOrderFinancialDetailsDTO salesOrderFinancialDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to save SalesOrderFinancialDetails : {}", salesOrderFinancialDetailsDTO);
        if (salesOrderFinancialDetailsDTO.getSalesOrderFinancialId() != null) {
            throw new BadRequestAlertException("A new salesOrderFinancialDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return salesOrderFinancialDetailsService
            .save(salesOrderFinancialDetailsDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/sales-order-financial-details/" + result.getSalesOrderFinancialId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(
                                applicationName,
                                false,
                                ENTITY_NAME,
                                result.getSalesOrderFinancialId().toString()
                            )
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /sales-order-financial-details/:salesOrderFinancialId} : Updates an existing salesOrderFinancialDetails.
     *
     * @param salesOrderFinancialId the id of the salesOrderFinancialDetailsDTO to save.
     * @param salesOrderFinancialDetailsDTO the salesOrderFinancialDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesOrderFinancialDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the salesOrderFinancialDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the salesOrderFinancialDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sales-order-financial-details/{salesOrderFinancialId}")
    public Mono<ResponseEntity<SalesOrderFinancialDetailsDTO>> updateSalesOrderFinancialDetails(
        @PathVariable(value = "salesOrderFinancialId", required = false) final Long salesOrderFinancialId,
        @RequestBody SalesOrderFinancialDetailsDTO salesOrderFinancialDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SalesOrderFinancialDetails : {}, {}", salesOrderFinancialId, salesOrderFinancialDetailsDTO);
        if (salesOrderFinancialDetailsDTO.getSalesOrderFinancialId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(salesOrderFinancialId, salesOrderFinancialDetailsDTO.getSalesOrderFinancialId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return salesOrderFinancialDetailsRepository
            .existsById(salesOrderFinancialId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return salesOrderFinancialDetailsService
                    .update(salesOrderFinancialDetailsDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getSalesOrderFinancialId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /sales-order-financial-details/:salesOrderFinancialId} : Partial updates given fields of an existing salesOrderFinancialDetails, field will ignore if it is null
     *
     * @param salesOrderFinancialId the id of the salesOrderFinancialDetailsDTO to save.
     * @param salesOrderFinancialDetailsDTO the salesOrderFinancialDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesOrderFinancialDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the salesOrderFinancialDetailsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the salesOrderFinancialDetailsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the salesOrderFinancialDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/sales-order-financial-details/{salesOrderFinancialId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public Mono<ResponseEntity<SalesOrderFinancialDetailsDTO>> partialUpdateSalesOrderFinancialDetails(
        @PathVariable(value = "salesOrderFinancialId", required = false) final Long salesOrderFinancialId,
        @RequestBody SalesOrderFinancialDetailsDTO salesOrderFinancialDetailsDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update SalesOrderFinancialDetails partially : {}, {}",
            salesOrderFinancialId,
            salesOrderFinancialDetailsDTO
        );
        if (salesOrderFinancialDetailsDTO.getSalesOrderFinancialId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(salesOrderFinancialId, salesOrderFinancialDetailsDTO.getSalesOrderFinancialId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return salesOrderFinancialDetailsRepository
            .existsById(salesOrderFinancialId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<SalesOrderFinancialDetailsDTO> result = salesOrderFinancialDetailsService.partialUpdate(salesOrderFinancialDetailsDTO);

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
                                    res.getSalesOrderFinancialId().toString()
                                )
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /sales-order-financial-details} : get all the salesOrderFinancialDetails.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of salesOrderFinancialDetails in body.
     */
    @GetMapping("/sales-order-financial-details")
    public Mono<ResponseEntity<List<SalesOrderFinancialDetailsDTO>>> getAllSalesOrderFinancialDetails(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of SalesOrderFinancialDetails");
        return salesOrderFinancialDetailsService
            .countAll()
            .zipWith(salesOrderFinancialDetailsService.findAll(pageable).collectList())
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
     * {@code GET  /sales-order-financial-details/:id} : get the "id" salesOrderFinancialDetails.
     *
     * @param id the id of the salesOrderFinancialDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the salesOrderFinancialDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sales-order-financial-details/{id}")
    public Mono<ResponseEntity<SalesOrderFinancialDetailsDTO>> getSalesOrderFinancialDetails(@PathVariable Long id) {
        log.debug("REST request to get SalesOrderFinancialDetails : {}", id);
        Mono<SalesOrderFinancialDetailsDTO> salesOrderFinancialDetailsDTO = salesOrderFinancialDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(salesOrderFinancialDetailsDTO);
    }

    /**
     * {@code DELETE  /sales-order-financial-details/:id} : delete the "id" salesOrderFinancialDetails.
     *
     * @param id the id of the salesOrderFinancialDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sales-order-financial-details/{id}")
    public Mono<ResponseEntity<Void>> deleteSalesOrderFinancialDetails(@PathVariable Long id) {
        log.debug("REST request to delete SalesOrderFinancialDetails : {}", id);
        return salesOrderFinancialDetailsService
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
