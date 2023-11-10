package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.SalesOrderInsuranceDetailsRepository;
import com.sunknowledge.dme.rcm.service.SalesOrderInsuranceDetailsService;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderInsuranceDetailsDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.SalesOrderInsuranceDetails}.
 */
@RestController
@RequestMapping("/api")
public class SalesOrderInsuranceDetailsResource {

    private final Logger log = LoggerFactory.getLogger(SalesOrderInsuranceDetailsResource.class);

    private static final String ENTITY_NAME = "salesorderSalesOrderInsuranceDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SalesOrderInsuranceDetailsService salesOrderInsuranceDetailsService;

    private final SalesOrderInsuranceDetailsRepository salesOrderInsuranceDetailsRepository;

    public SalesOrderInsuranceDetailsResource(
        SalesOrderInsuranceDetailsService salesOrderInsuranceDetailsService,
        SalesOrderInsuranceDetailsRepository salesOrderInsuranceDetailsRepository
    ) {
        this.salesOrderInsuranceDetailsService = salesOrderInsuranceDetailsService;
        this.salesOrderInsuranceDetailsRepository = salesOrderInsuranceDetailsRepository;
    }

    /**
     * {@code POST  /sales-order-insurance-details} : Create a new salesOrderInsuranceDetails.
     *
     * @param salesOrderInsuranceDetailsDTO the salesOrderInsuranceDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new salesOrderInsuranceDetailsDTO, or with status {@code 400 (Bad Request)} if the salesOrderInsuranceDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sales-order-insurance-details")
    public Mono<ResponseEntity<SalesOrderInsuranceDetailsDTO>> createSalesOrderInsuranceDetails(
        @RequestBody SalesOrderInsuranceDetailsDTO salesOrderInsuranceDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to save SalesOrderInsuranceDetails : {}", salesOrderInsuranceDetailsDTO);
        if (salesOrderInsuranceDetailsDTO.getSalesOrderInsuranceDetailsId() != null) {
            throw new BadRequestAlertException("A new salesOrderInsuranceDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return salesOrderInsuranceDetailsService
            .save(salesOrderInsuranceDetailsDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/sales-order-insurance-details/" + result.getSalesOrderInsuranceDetailsId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(
                                applicationName,
                                false,
                                ENTITY_NAME,
                                result.getSalesOrderInsuranceDetailsId().toString()
                            )
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /sales-order-insurance-details/:salesOrderInsuranceDetailsId} : Updates an existing salesOrderInsuranceDetails.
     *
     * @param salesOrderInsuranceDetailsId the id of the salesOrderInsuranceDetailsDTO to save.
     * @param salesOrderInsuranceDetailsDTO the salesOrderInsuranceDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesOrderInsuranceDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the salesOrderInsuranceDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the salesOrderInsuranceDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sales-order-insurance-details/{salesOrderInsuranceDetailsId}")
    public Mono<ResponseEntity<SalesOrderInsuranceDetailsDTO>> updateSalesOrderInsuranceDetails(
        @PathVariable(value = "salesOrderInsuranceDetailsId", required = false) final Long salesOrderInsuranceDetailsId,
        @RequestBody SalesOrderInsuranceDetailsDTO salesOrderInsuranceDetailsDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to update SalesOrderInsuranceDetails : {}, {}",
            salesOrderInsuranceDetailsId,
            salesOrderInsuranceDetailsDTO
        );
        if (salesOrderInsuranceDetailsDTO.getSalesOrderInsuranceDetailsId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(salesOrderInsuranceDetailsId, salesOrderInsuranceDetailsDTO.getSalesOrderInsuranceDetailsId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return salesOrderInsuranceDetailsRepository
            .existsById(salesOrderInsuranceDetailsId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return salesOrderInsuranceDetailsService
                    .update(salesOrderInsuranceDetailsDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getSalesOrderInsuranceDetailsId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /sales-order-insurance-details/:salesOrderInsuranceDetailsId} : Partial updates given fields of an existing salesOrderInsuranceDetails, field will ignore if it is null
     *
     * @param salesOrderInsuranceDetailsId the id of the salesOrderInsuranceDetailsDTO to save.
     * @param salesOrderInsuranceDetailsDTO the salesOrderInsuranceDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesOrderInsuranceDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the salesOrderInsuranceDetailsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the salesOrderInsuranceDetailsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the salesOrderInsuranceDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/sales-order-insurance-details/{salesOrderInsuranceDetailsId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public Mono<ResponseEntity<SalesOrderInsuranceDetailsDTO>> partialUpdateSalesOrderInsuranceDetails(
        @PathVariable(value = "salesOrderInsuranceDetailsId", required = false) final Long salesOrderInsuranceDetailsId,
        @RequestBody SalesOrderInsuranceDetailsDTO salesOrderInsuranceDetailsDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update SalesOrderInsuranceDetails partially : {}, {}",
            salesOrderInsuranceDetailsId,
            salesOrderInsuranceDetailsDTO
        );
        if (salesOrderInsuranceDetailsDTO.getSalesOrderInsuranceDetailsId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(salesOrderInsuranceDetailsId, salesOrderInsuranceDetailsDTO.getSalesOrderInsuranceDetailsId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return salesOrderInsuranceDetailsRepository
            .existsById(salesOrderInsuranceDetailsId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<SalesOrderInsuranceDetailsDTO> result = salesOrderInsuranceDetailsService.partialUpdate(salesOrderInsuranceDetailsDTO);

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
                                    res.getSalesOrderInsuranceDetailsId().toString()
                                )
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /sales-order-insurance-details} : get all the salesOrderInsuranceDetails.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of salesOrderInsuranceDetails in body.
     */
    @GetMapping("/sales-order-insurance-details")
    public Mono<ResponseEntity<List<SalesOrderInsuranceDetailsDTO>>> getAllSalesOrderInsuranceDetails(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of SalesOrderInsuranceDetails");
        return salesOrderInsuranceDetailsService
            .countAll()
            .zipWith(salesOrderInsuranceDetailsService.findAll(pageable).collectList())
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
     * {@code GET  /sales-order-insurance-details/:id} : get the "id" salesOrderInsuranceDetails.
     *
     * @param id the id of the salesOrderInsuranceDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the salesOrderInsuranceDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sales-order-insurance-details/{id}")
    public Mono<ResponseEntity<SalesOrderInsuranceDetailsDTO>> getSalesOrderInsuranceDetails(@PathVariable Long id) {
        log.debug("REST request to get SalesOrderInsuranceDetails : {}", id);
        Mono<SalesOrderInsuranceDetailsDTO> salesOrderInsuranceDetailsDTO = salesOrderInsuranceDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(salesOrderInsuranceDetailsDTO);
    }

    /**
     * {@code DELETE  /sales-order-insurance-details/:id} : delete the "id" salesOrderInsuranceDetails.
     *
     * @param id the id of the salesOrderInsuranceDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sales-order-insurance-details/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteSalesOrderInsuranceDetails(@PathVariable Long id) {
        log.debug("REST request to delete SalesOrderInsuranceDetails : {}", id);
        return salesOrderInsuranceDetailsService
            .delete(id)
            .map(result ->
                ResponseEntity
                    .noContent()
                    .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                    .build()
            );
    }
}
