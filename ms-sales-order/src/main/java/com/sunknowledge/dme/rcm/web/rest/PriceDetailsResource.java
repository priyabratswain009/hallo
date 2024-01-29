package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.PriceDetailsRepository;
import com.sunknowledge.dme.rcm.service.PriceDetailsService;
import com.sunknowledge.dme.rcm.service.dto.PriceDetailsDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.PriceDetails}.
 */
@RestController
@RequestMapping("/api")
public class PriceDetailsResource {

    private final Logger log = LoggerFactory.getLogger(PriceDetailsResource.class);

    private static final String ENTITY_NAME = "salesorderPriceDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PriceDetailsService priceDetailsService;

    private final PriceDetailsRepository priceDetailsRepository;

    public PriceDetailsResource(PriceDetailsService priceDetailsService, PriceDetailsRepository priceDetailsRepository) {
        this.priceDetailsService = priceDetailsService;
        this.priceDetailsRepository = priceDetailsRepository;
    }

    /**
     * {@code POST  /price-details} : Create a new priceDetails.
     *
     * @param priceDetailsDTO the priceDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new priceDetailsDTO, or with status {@code 400 (Bad Request)} if the priceDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/price-details")
    public Mono<ResponseEntity<PriceDetailsDTO>> createPriceDetails(@RequestBody PriceDetailsDTO priceDetailsDTO)
        throws URISyntaxException {
        log.debug("REST request to save PriceDetails : {}", priceDetailsDTO);
        if (priceDetailsDTO.getPriceDetailsId() != null) {
            throw new BadRequestAlertException("A new priceDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return priceDetailsService
            .save(priceDetailsDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/price-details/" + result.getPriceDetailsId()))
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
     * {@code PUT  /price-details/:priceDetailsId} : Updates an existing priceDetails.
     *
     * @param priceDetailsId the id of the priceDetailsDTO to save.
     * @param priceDetailsDTO the priceDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated priceDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the priceDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the priceDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/price-details/{priceDetailsId}")
    public Mono<ResponseEntity<PriceDetailsDTO>> updatePriceDetails(
        @PathVariable(value = "priceDetailsId", required = false) final Long priceDetailsId,
        @RequestBody PriceDetailsDTO priceDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PriceDetails : {}, {}", priceDetailsId, priceDetailsDTO);
        if (priceDetailsDTO.getPriceDetailsId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(priceDetailsId, priceDetailsDTO.getPriceDetailsId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return priceDetailsRepository
            .existsById(priceDetailsId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return priceDetailsService
                    .update(priceDetailsDTO)
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
     * {@code PATCH  /price-details/:priceDetailsId} : Partial updates given fields of an existing priceDetails, field will ignore if it is null
     *
     * @param priceDetailsId the id of the priceDetailsDTO to save.
     * @param priceDetailsDTO the priceDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated priceDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the priceDetailsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the priceDetailsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the priceDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/price-details/{priceDetailsId}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<PriceDetailsDTO>> partialUpdatePriceDetails(
        @PathVariable(value = "priceDetailsId", required = false) final Long priceDetailsId,
        @RequestBody PriceDetailsDTO priceDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PriceDetails partially : {}, {}", priceDetailsId, priceDetailsDTO);
        if (priceDetailsDTO.getPriceDetailsId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(priceDetailsId, priceDetailsDTO.getPriceDetailsId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return priceDetailsRepository
            .existsById(priceDetailsId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<PriceDetailsDTO> result = priceDetailsService.partialUpdate(priceDetailsDTO);

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
     * {@code GET  /price-details} : get all the priceDetails.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of priceDetails in body.
     */
    @GetMapping("/price-details")
    public Mono<ResponseEntity<List<PriceDetailsDTO>>> getAllPriceDetails(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of PriceDetails");
        return priceDetailsService
            .countAll()
            .zipWith(priceDetailsService.findAll(pageable).collectList())
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
     * {@code GET  /price-details/:id} : get the "id" priceDetails.
     *
     * @param id the id of the priceDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the priceDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/price-details/{id}")
    public Mono<ResponseEntity<PriceDetailsDTO>> getPriceDetails(@PathVariable Long id) {
        log.debug("REST request to get PriceDetails : {}", id);
        Mono<PriceDetailsDTO> priceDetailsDTO = priceDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(priceDetailsDTO);
    }

    /**
     * {@code DELETE  /price-details/:id} : delete the "id" priceDetails.
     *
     * @param id the id of the priceDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/price-details/{id}")
    public Mono<ResponseEntity<Void>> deletePriceDetails(@PathVariable Long id) {
        log.debug("REST request to delete PriceDetails : {}", id);
        return priceDetailsService
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
