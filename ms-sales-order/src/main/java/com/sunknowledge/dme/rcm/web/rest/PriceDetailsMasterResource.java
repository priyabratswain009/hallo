package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.PriceDetailsMasterRepository;
import com.sunknowledge.dme.rcm.service.PriceDetailsMasterService;
import com.sunknowledge.dme.rcm.service.dto.PriceDetailsMasterDTO;
import com.sunknowledge.dme.rcm.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.PriceDetailsMaster}.
 */
@RestController
@RequestMapping("/api")
public class PriceDetailsMasterResource {

    private final Logger log = LoggerFactory.getLogger(PriceDetailsMasterResource.class);

    private static final String ENTITY_NAME = "salesorderPriceDetailsMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PriceDetailsMasterService priceDetailsMasterService;

    private final PriceDetailsMasterRepository priceDetailsMasterRepository;

    public PriceDetailsMasterResource(
        PriceDetailsMasterService priceDetailsMasterService,
        PriceDetailsMasterRepository priceDetailsMasterRepository
    ) {
        this.priceDetailsMasterService = priceDetailsMasterService;
        this.priceDetailsMasterRepository = priceDetailsMasterRepository;
    }

    /**
     * {@code POST  /price-details-masters} : Create a new priceDetailsMaster.
     *
     * @param priceDetailsMasterDTO the priceDetailsMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new priceDetailsMasterDTO, or with status {@code 400 (Bad Request)} if the priceDetailsMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/price-details-masters")
    public Mono<ResponseEntity<PriceDetailsMasterDTO>> createPriceDetailsMaster(
        @Valid @RequestBody PriceDetailsMasterDTO priceDetailsMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to save PriceDetailsMaster : {}", priceDetailsMasterDTO);
        if (priceDetailsMasterDTO.getPriceDetailsId() != null) {
            throw new BadRequestAlertException("A new priceDetailsMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return priceDetailsMasterService
            .save(priceDetailsMasterDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/price-details-masters/" + result.getPriceDetailsId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getPriceDetailsId().toString())
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /price-details-masters/:priceDetailsId} : Updates an existing priceDetailsMaster.
     *
     * @param priceDetailsId the id of the priceDetailsMasterDTO to save.
     * @param priceDetailsMasterDTO the priceDetailsMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated priceDetailsMasterDTO,
     * or with status {@code 400 (Bad Request)} if the priceDetailsMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the priceDetailsMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/price-details-masters/{priceDetailsId}")
    public Mono<ResponseEntity<PriceDetailsMasterDTO>> updatePriceDetailsMaster(
        @PathVariable(value = "priceDetailsId", required = false) final Long priceDetailsId,
        @Valid @RequestBody PriceDetailsMasterDTO priceDetailsMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PriceDetailsMaster : {}, {}", priceDetailsId, priceDetailsMasterDTO);
        if (priceDetailsMasterDTO.getPriceDetailsId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(priceDetailsId, priceDetailsMasterDTO.getPriceDetailsId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return priceDetailsMasterRepository
            .existsById(priceDetailsId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return priceDetailsMasterService
                    .update(priceDetailsMasterDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getPriceDetailsId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /price-details-masters/:priceDetailsId} : Partial updates given fields of an existing priceDetailsMaster, field will ignore if it is null
     *
     * @param priceDetailsId the id of the priceDetailsMasterDTO to save.
     * @param priceDetailsMasterDTO the priceDetailsMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated priceDetailsMasterDTO,
     * or with status {@code 400 (Bad Request)} if the priceDetailsMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the priceDetailsMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the priceDetailsMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/price-details-masters/{priceDetailsId}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<PriceDetailsMasterDTO>> partialUpdatePriceDetailsMaster(
        @PathVariable(value = "priceDetailsId", required = false) final Long priceDetailsId,
        @NotNull @RequestBody PriceDetailsMasterDTO priceDetailsMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PriceDetailsMaster partially : {}, {}", priceDetailsId, priceDetailsMasterDTO);
        if (priceDetailsMasterDTO.getPriceDetailsId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(priceDetailsId, priceDetailsMasterDTO.getPriceDetailsId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return priceDetailsMasterRepository
            .existsById(priceDetailsId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<PriceDetailsMasterDTO> result = priceDetailsMasterService.partialUpdate(priceDetailsMasterDTO);

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(res ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, res.getPriceDetailsId().toString())
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /price-details-masters} : get all the priceDetailsMasters.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of priceDetailsMasters in body.
     */
    @GetMapping("/price-details-masters")
    public Mono<ResponseEntity<List<PriceDetailsMasterDTO>>> getAllPriceDetailsMasters(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of PriceDetailsMasters");
        return priceDetailsMasterService
            .countAll()
            .zipWith(priceDetailsMasterService.findAll(pageable).collectList())
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
     * {@code GET  /price-details-masters/:id} : get the "id" priceDetailsMaster.
     *
     * @param id the id of the priceDetailsMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the priceDetailsMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/price-details-masters/{id}")
    public Mono<ResponseEntity<PriceDetailsMasterDTO>> getPriceDetailsMaster(@PathVariable Long id) {
        log.debug("REST request to get PriceDetailsMaster : {}", id);
        Mono<PriceDetailsMasterDTO> priceDetailsMasterDTO = priceDetailsMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(priceDetailsMasterDTO);
    }

    /**
     * {@code DELETE  /price-details-masters/:id} : delete the "id" priceDetailsMaster.
     *
     * @param id the id of the priceDetailsMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/price-details-masters/{id}")
    public Mono<ResponseEntity<Void>> deletePriceDetailsMaster(@PathVariable Long id) {
        log.debug("REST request to delete PriceDetailsMaster : {}", id);
        return priceDetailsMasterService
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
