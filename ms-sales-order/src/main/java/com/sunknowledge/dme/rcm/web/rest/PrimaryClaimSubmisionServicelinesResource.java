package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.PrimaryClaimSubmisionServicelinesRepository;
import com.sunknowledge.dme.rcm.service.PrimaryClaimSubmisionServicelinesService;
import com.sunknowledge.dme.rcm.service.dto.PrimaryClaimSubmisionServicelinesDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.PrimaryClaimSubmisionServicelines}.
 */
@RestController
@RequestMapping("/api")
public class PrimaryClaimSubmisionServicelinesResource {

    private final Logger log = LoggerFactory.getLogger(PrimaryClaimSubmisionServicelinesResource.class);

    private static final String ENTITY_NAME = "salesorderPrimaryClaimSubmisionServicelines";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PrimaryClaimSubmisionServicelinesService primaryClaimSubmisionServicelinesService;

    private final PrimaryClaimSubmisionServicelinesRepository primaryClaimSubmisionServicelinesRepository;

    public PrimaryClaimSubmisionServicelinesResource(
        PrimaryClaimSubmisionServicelinesService primaryClaimSubmisionServicelinesService,
        PrimaryClaimSubmisionServicelinesRepository primaryClaimSubmisionServicelinesRepository
    ) {
        this.primaryClaimSubmisionServicelinesService = primaryClaimSubmisionServicelinesService;
        this.primaryClaimSubmisionServicelinesRepository = primaryClaimSubmisionServicelinesRepository;
    }

    /**
     * {@code POST  /primary-claim-submision-servicelines} : Create a new primaryClaimSubmisionServicelines.
     *
     * @param primaryClaimSubmisionServicelinesDTO the primaryClaimSubmisionServicelinesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new primaryClaimSubmisionServicelinesDTO, or with status {@code 400 (Bad Request)} if the primaryClaimSubmisionServicelines has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/primary-claim-submision-servicelines")
    public Mono<ResponseEntity<PrimaryClaimSubmisionServicelinesDTO>> createPrimaryClaimSubmisionServicelines(
        @RequestBody PrimaryClaimSubmisionServicelinesDTO primaryClaimSubmisionServicelinesDTO
    ) throws URISyntaxException {
        log.debug("REST request to save PrimaryClaimSubmisionServicelines : {}", primaryClaimSubmisionServicelinesDTO);
        if (primaryClaimSubmisionServicelinesDTO.getChangeHealthPrimarySubmisionServicelinesId() != null) {
            throw new BadRequestAlertException(
                "A new primaryClaimSubmisionServicelines cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        return primaryClaimSubmisionServicelinesService
            .save(primaryClaimSubmisionServicelinesDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(
                            new URI("/api/primary-claim-submision-servicelines/" + result.getChangeHealthPrimarySubmisionServicelinesId())
                        )
                        .headers(
                            HeaderUtil.createEntityCreationAlert(
                                applicationName,
                                false,
                                ENTITY_NAME,
                                result.getChangeHealthPrimarySubmisionServicelinesId().toString()
                            )
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /primary-claim-submision-servicelines/:changeHealthPrimarySubmisionServicelinesId} : Updates an existing primaryClaimSubmisionServicelines.
     *
     * @param changeHealthPrimarySubmisionServicelinesId the id of the primaryClaimSubmisionServicelinesDTO to save.
     * @param primaryClaimSubmisionServicelinesDTO the primaryClaimSubmisionServicelinesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated primaryClaimSubmisionServicelinesDTO,
     * or with status {@code 400 (Bad Request)} if the primaryClaimSubmisionServicelinesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the primaryClaimSubmisionServicelinesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/primary-claim-submision-servicelines/{changeHealthPrimarySubmisionServicelinesId}")
    public Mono<ResponseEntity<PrimaryClaimSubmisionServicelinesDTO>> updatePrimaryClaimSubmisionServicelines(
        @PathVariable(
            value = "changeHealthPrimarySubmisionServicelinesId",
            required = false
        ) final Long changeHealthPrimarySubmisionServicelinesId,
        @RequestBody PrimaryClaimSubmisionServicelinesDTO primaryClaimSubmisionServicelinesDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to update PrimaryClaimSubmisionServicelines : {}, {}",
            changeHealthPrimarySubmisionServicelinesId,
            primaryClaimSubmisionServicelinesDTO
        );
        if (primaryClaimSubmisionServicelinesDTO.getChangeHealthPrimarySubmisionServicelinesId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (
            !Objects.equals(
                changeHealthPrimarySubmisionServicelinesId,
                primaryClaimSubmisionServicelinesDTO.getChangeHealthPrimarySubmisionServicelinesId()
            )
        ) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return primaryClaimSubmisionServicelinesRepository
            .existsById(changeHealthPrimarySubmisionServicelinesId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return primaryClaimSubmisionServicelinesService
                    .update(primaryClaimSubmisionServicelinesDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getChangeHealthPrimarySubmisionServicelinesId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /primary-claim-submision-servicelines/:changeHealthPrimarySubmisionServicelinesId} : Partial updates given fields of an existing primaryClaimSubmisionServicelines, field will ignore if it is null
     *
     * @param changeHealthPrimarySubmisionServicelinesId the id of the primaryClaimSubmisionServicelinesDTO to save.
     * @param primaryClaimSubmisionServicelinesDTO the primaryClaimSubmisionServicelinesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated primaryClaimSubmisionServicelinesDTO,
     * or with status {@code 400 (Bad Request)} if the primaryClaimSubmisionServicelinesDTO is not valid,
     * or with status {@code 404 (Not Found)} if the primaryClaimSubmisionServicelinesDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the primaryClaimSubmisionServicelinesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/primary-claim-submision-servicelines/{changeHealthPrimarySubmisionServicelinesId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public Mono<ResponseEntity<PrimaryClaimSubmisionServicelinesDTO>> partialUpdatePrimaryClaimSubmisionServicelines(
        @PathVariable(
            value = "changeHealthPrimarySubmisionServicelinesId",
            required = false
        ) final Long changeHealthPrimarySubmisionServicelinesId,
        @RequestBody PrimaryClaimSubmisionServicelinesDTO primaryClaimSubmisionServicelinesDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update PrimaryClaimSubmisionServicelines partially : {}, {}",
            changeHealthPrimarySubmisionServicelinesId,
            primaryClaimSubmisionServicelinesDTO
        );
        if (primaryClaimSubmisionServicelinesDTO.getChangeHealthPrimarySubmisionServicelinesId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (
            !Objects.equals(
                changeHealthPrimarySubmisionServicelinesId,
                primaryClaimSubmisionServicelinesDTO.getChangeHealthPrimarySubmisionServicelinesId()
            )
        ) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return primaryClaimSubmisionServicelinesRepository
            .existsById(changeHealthPrimarySubmisionServicelinesId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<PrimaryClaimSubmisionServicelinesDTO> result = primaryClaimSubmisionServicelinesService.partialUpdate(
                    primaryClaimSubmisionServicelinesDTO
                );

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
                                    res.getChangeHealthPrimarySubmisionServicelinesId().toString()
                                )
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /primary-claim-submision-servicelines} : get all the primaryClaimSubmisionServicelines.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of primaryClaimSubmisionServicelines in body.
     */
    @GetMapping("/primary-claim-submision-servicelines")
    public Mono<ResponseEntity<List<PrimaryClaimSubmisionServicelinesDTO>>> getAllPrimaryClaimSubmisionServicelines(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of PrimaryClaimSubmisionServicelines");
        return primaryClaimSubmisionServicelinesService
            .countAll()
            .zipWith(primaryClaimSubmisionServicelinesService.findAll(pageable).collectList())
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
     * {@code GET  /primary-claim-submision-servicelines/:id} : get the "id" primaryClaimSubmisionServicelines.
     *
     * @param id the id of the primaryClaimSubmisionServicelinesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the primaryClaimSubmisionServicelinesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/primary-claim-submision-servicelines/{id}")
    public Mono<ResponseEntity<PrimaryClaimSubmisionServicelinesDTO>> getPrimaryClaimSubmisionServicelines(@PathVariable Long id) {
        log.debug("REST request to get PrimaryClaimSubmisionServicelines : {}", id);
        Mono<PrimaryClaimSubmisionServicelinesDTO> primaryClaimSubmisionServicelinesDTO = primaryClaimSubmisionServicelinesService.findOne(
            id
        );
        return ResponseUtil.wrapOrNotFound(primaryClaimSubmisionServicelinesDTO);
    }

    /**
     * {@code DELETE  /primary-claim-submision-servicelines/:id} : delete the "id" primaryClaimSubmisionServicelines.
     *
     * @param id the id of the primaryClaimSubmisionServicelinesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/primary-claim-submision-servicelines/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deletePrimaryClaimSubmisionServicelines(@PathVariable Long id) {
        log.debug("REST request to delete PrimaryClaimSubmisionServicelines : {}", id);
        return primaryClaimSubmisionServicelinesService
            .delete(id)
            .map(result ->
                ResponseEntity
                    .noContent()
                    .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                    .build()
            );
    }
}
