package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.SalesOrderMasterRepository;
import com.sunknowledge.dme.rcm.service.SalesOrderMasterService;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderMasterDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.SalesOrderMaster}.
 */
@RestController
@RequestMapping("/api")
public class SalesOrderMasterResource {

    private final Logger log = LoggerFactory.getLogger(SalesOrderMasterResource.class);

    private static final String ENTITY_NAME = "salesorderSalesOrderMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SalesOrderMasterService salesOrderMasterService;

    private final SalesOrderMasterRepository salesOrderMasterRepository;

    public SalesOrderMasterResource(
        SalesOrderMasterService salesOrderMasterService,
        SalesOrderMasterRepository salesOrderMasterRepository
    ) {
        this.salesOrderMasterService = salesOrderMasterService;
        this.salesOrderMasterRepository = salesOrderMasterRepository;
    }

    /**
     * {@code POST  /sales-order-masters} : Create a new salesOrderMaster.
     *
     * @param salesOrderMasterDTO the salesOrderMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new salesOrderMasterDTO, or with status {@code 400 (Bad Request)} if the salesOrderMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sales-order-masters")
    public Mono<ResponseEntity<SalesOrderMasterDTO>> createSalesOrderMaster(@RequestBody SalesOrderMasterDTO salesOrderMasterDTO)
        throws URISyntaxException {
        log.debug("REST request to save SalesOrderMaster : {}", salesOrderMasterDTO);
        if (salesOrderMasterDTO.getSalesOrderId() != null) {
            throw new BadRequestAlertException("A new salesOrderMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return salesOrderMasterService
            .save(salesOrderMasterDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/sales-order-masters/" + result.getSalesOrderId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getSalesOrderId().toString())
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /sales-order-masters/:salesOrderId} : Updates an existing salesOrderMaster.
     *
     * @param salesOrderId the id of the salesOrderMasterDTO to save.
     * @param salesOrderMasterDTO the salesOrderMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesOrderMasterDTO,
     * or with status {@code 400 (Bad Request)} if the salesOrderMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the salesOrderMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sales-order-masters/{salesOrderId}")
    public Mono<ResponseEntity<SalesOrderMasterDTO>> updateSalesOrderMaster(
        @PathVariable(value = "salesOrderId", required = false) final Long salesOrderId,
        @RequestBody SalesOrderMasterDTO salesOrderMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SalesOrderMaster : {}, {}", salesOrderId, salesOrderMasterDTO);
        if (salesOrderMasterDTO.getSalesOrderId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(salesOrderId, salesOrderMasterDTO.getSalesOrderId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return salesOrderMasterRepository
            .existsById(salesOrderId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return salesOrderMasterService
                    .update(salesOrderMasterDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, result.getSalesOrderId().toString())
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /sales-order-masters/:salesOrderId} : Partial updates given fields of an existing salesOrderMaster, field will ignore if it is null
     *
     * @param salesOrderId the id of the salesOrderMasterDTO to save.
     * @param salesOrderMasterDTO the salesOrderMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesOrderMasterDTO,
     * or with status {@code 400 (Bad Request)} if the salesOrderMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the salesOrderMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the salesOrderMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/sales-order-masters/{salesOrderId}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<SalesOrderMasterDTO>> partialUpdateSalesOrderMaster(
        @PathVariable(value = "salesOrderId", required = false) final Long salesOrderId,
        @RequestBody SalesOrderMasterDTO salesOrderMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update SalesOrderMaster partially : {}, {}", salesOrderId, salesOrderMasterDTO);
        if (salesOrderMasterDTO.getSalesOrderId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(salesOrderId, salesOrderMasterDTO.getSalesOrderId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return salesOrderMasterRepository
            .existsById(salesOrderId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<SalesOrderMasterDTO> result = salesOrderMasterService.partialUpdate(salesOrderMasterDTO);

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(res ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, res.getSalesOrderId().toString())
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /sales-order-masters} : get all the salesOrderMasters.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of salesOrderMasters in body.
     */
    @GetMapping("/sales-order-masters")
    public Mono<ResponseEntity<List<SalesOrderMasterDTO>>> getAllSalesOrderMasters(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of SalesOrderMasters");
        return salesOrderMasterService
            .countAll()
            .zipWith(salesOrderMasterService.findAll(pageable).collectList())
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
     * {@code GET  /sales-order-masters/:id} : get the "id" salesOrderMaster.
     *
     * @param id the id of the salesOrderMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the salesOrderMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sales-order-masters/{id}")
    public Mono<ResponseEntity<SalesOrderMasterDTO>> getSalesOrderMaster(@PathVariable Long id) {
        log.debug("REST request to get SalesOrderMaster : {}", id);
        Mono<SalesOrderMasterDTO> salesOrderMasterDTO = salesOrderMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(salesOrderMasterDTO);
    }

    /**
     * {@code DELETE  /sales-order-masters/:id} : delete the "id" salesOrderMaster.
     *
     * @param id the id of the salesOrderMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sales-order-masters/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteSalesOrderMaster(@PathVariable Long id) {
        log.debug("REST request to delete SalesOrderMaster : {}", id);
        return salesOrderMasterService
            .delete(id)
            .map(result ->
                ResponseEntity
                    .noContent()
                    .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                    .build()
            );
    }
}
