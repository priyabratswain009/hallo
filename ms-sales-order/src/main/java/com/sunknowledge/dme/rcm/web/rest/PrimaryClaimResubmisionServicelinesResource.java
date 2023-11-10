package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.PrimaryClaimResubmisionServicelinesRepository;
import com.sunknowledge.dme.rcm.service.PrimaryClaimResubmisionServicelinesService;
import com.sunknowledge.dme.rcm.service.dto.PrimaryClaimResubmisionServicelinesDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.PrimaryClaimResubmisionServicelines}.
 */
@RestController
@RequestMapping("/api")
public class PrimaryClaimResubmisionServicelinesResource {

    private final Logger log = LoggerFactory.getLogger(PrimaryClaimResubmisionServicelinesResource.class);

    private static final String ENTITY_NAME = "salesorderPrimaryClaimResubmisionServicelines";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PrimaryClaimResubmisionServicelinesService primaryClaimResubmisionServicelinesService;

    private final PrimaryClaimResubmisionServicelinesRepository primaryClaimResubmisionServicelinesRepository;

    public PrimaryClaimResubmisionServicelinesResource(
        PrimaryClaimResubmisionServicelinesService primaryClaimResubmisionServicelinesService,
        PrimaryClaimResubmisionServicelinesRepository primaryClaimResubmisionServicelinesRepository
    ) {
        this.primaryClaimResubmisionServicelinesService = primaryClaimResubmisionServicelinesService;
        this.primaryClaimResubmisionServicelinesRepository = primaryClaimResubmisionServicelinesRepository;
    }

    /**
     * {@code POST  /primary-claim-resubmision-servicelines} : Create a new primaryClaimResubmisionServicelines.
     *
     * @param primaryClaimResubmisionServicelinesDTO the primaryClaimResubmisionServicelinesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new primaryClaimResubmisionServicelinesDTO, or with status {@code 400 (Bad Request)} if the primaryClaimResubmisionServicelines has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/primary-claim-resubmision-servicelines")
    public Mono<ResponseEntity<PrimaryClaimResubmisionServicelinesDTO>> createPrimaryClaimResubmisionServicelines(
        @RequestBody PrimaryClaimResubmisionServicelinesDTO primaryClaimResubmisionServicelinesDTO
    ) throws URISyntaxException {
        log.debug("REST request to save PrimaryClaimResubmisionServicelines : {}", primaryClaimResubmisionServicelinesDTO);
        if (primaryClaimResubmisionServicelinesDTO.getChangeHealthPrimaryResubmisionServicelinesId() != null) {
            throw new BadRequestAlertException(
                "A new primaryClaimResubmisionServicelines cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        return primaryClaimResubmisionServicelinesService
            .save(primaryClaimResubmisionServicelinesDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(
                            new URI(
                                "/api/primary-claim-resubmision-servicelines/" + result.getChangeHealthPrimaryResubmisionServicelinesId()
                            )
                        )
                        .headers(
                            HeaderUtil.createEntityCreationAlert(
                                applicationName,
                                false,
                                ENTITY_NAME,
                                result.getChangeHealthPrimaryResubmisionServicelinesId().toString()
                            )
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /primary-claim-resubmision-servicelines/:changeHealthPrimaryResubmisionServicelinesId} : Updates an existing primaryClaimResubmisionServicelines.
     *
     * @param changeHealthPrimaryResubmisionServicelinesId the id of the primaryClaimResubmisionServicelinesDTO to save.
     * @param primaryClaimResubmisionServicelinesDTO the primaryClaimResubmisionServicelinesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated primaryClaimResubmisionServicelinesDTO,
     * or with status {@code 400 (Bad Request)} if the primaryClaimResubmisionServicelinesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the primaryClaimResubmisionServicelinesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/primary-claim-resubmision-servicelines/{changeHealthPrimaryResubmisionServicelinesId}")
    public Mono<ResponseEntity<PrimaryClaimResubmisionServicelinesDTO>> updatePrimaryClaimResubmisionServicelines(
        @PathVariable(
            value = "changeHealthPrimaryResubmisionServicelinesId",
            required = false
        ) final Long changeHealthPrimaryResubmisionServicelinesId,
        @RequestBody PrimaryClaimResubmisionServicelinesDTO primaryClaimResubmisionServicelinesDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to update PrimaryClaimResubmisionServicelines : {}, {}",
            changeHealthPrimaryResubmisionServicelinesId,
            primaryClaimResubmisionServicelinesDTO
        );
        if (primaryClaimResubmisionServicelinesDTO.getChangeHealthPrimaryResubmisionServicelinesId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (
            !Objects.equals(
                changeHealthPrimaryResubmisionServicelinesId,
                primaryClaimResubmisionServicelinesDTO.getChangeHealthPrimaryResubmisionServicelinesId()
            )
        ) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return primaryClaimResubmisionServicelinesRepository
            .existsById(changeHealthPrimaryResubmisionServicelinesId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return primaryClaimResubmisionServicelinesService
                    .update(primaryClaimResubmisionServicelinesDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getChangeHealthPrimaryResubmisionServicelinesId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /primary-claim-resubmision-servicelines/:changeHealthPrimaryResubmisionServicelinesId} : Partial updates given fields of an existing primaryClaimResubmisionServicelines, field will ignore if it is null
     *
     * @param changeHealthPrimaryResubmisionServicelinesId the id of the primaryClaimResubmisionServicelinesDTO to save.
     * @param primaryClaimResubmisionServicelinesDTO the primaryClaimResubmisionServicelinesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated primaryClaimResubmisionServicelinesDTO,
     * or with status {@code 400 (Bad Request)} if the primaryClaimResubmisionServicelinesDTO is not valid,
     * or with status {@code 404 (Not Found)} if the primaryClaimResubmisionServicelinesDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the primaryClaimResubmisionServicelinesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/primary-claim-resubmision-servicelines/{changeHealthPrimaryResubmisionServicelinesId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public Mono<ResponseEntity<PrimaryClaimResubmisionServicelinesDTO>> partialUpdatePrimaryClaimResubmisionServicelines(
        @PathVariable(
            value = "changeHealthPrimaryResubmisionServicelinesId",
            required = false
        ) final Long changeHealthPrimaryResubmisionServicelinesId,
        @RequestBody PrimaryClaimResubmisionServicelinesDTO primaryClaimResubmisionServicelinesDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update PrimaryClaimResubmisionServicelines partially : {}, {}",
            changeHealthPrimaryResubmisionServicelinesId,
            primaryClaimResubmisionServicelinesDTO
        );
        if (primaryClaimResubmisionServicelinesDTO.getChangeHealthPrimaryResubmisionServicelinesId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (
            !Objects.equals(
                changeHealthPrimaryResubmisionServicelinesId,
                primaryClaimResubmisionServicelinesDTO.getChangeHealthPrimaryResubmisionServicelinesId()
            )
        ) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return primaryClaimResubmisionServicelinesRepository
            .existsById(changeHealthPrimaryResubmisionServicelinesId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<PrimaryClaimResubmisionServicelinesDTO> result = primaryClaimResubmisionServicelinesService.partialUpdate(
                    primaryClaimResubmisionServicelinesDTO
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
                                    res.getChangeHealthPrimaryResubmisionServicelinesId().toString()
                                )
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /primary-claim-resubmision-servicelines} : get all the primaryClaimResubmisionServicelines.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of primaryClaimResubmisionServicelines in body.
     */
    @GetMapping("/primary-claim-resubmision-servicelines")
    public Mono<ResponseEntity<List<PrimaryClaimResubmisionServicelinesDTO>>> getAllPrimaryClaimResubmisionServicelines(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of PrimaryClaimResubmisionServicelines");
        return primaryClaimResubmisionServicelinesService
            .countAll()
            .zipWith(primaryClaimResubmisionServicelinesService.findAll(pageable).collectList())
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
     * {@code GET  /primary-claim-resubmision-servicelines/:id} : get the "id" primaryClaimResubmisionServicelines.
     *
     * @param id the id of the primaryClaimResubmisionServicelinesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the primaryClaimResubmisionServicelinesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/primary-claim-resubmision-servicelines/{id}")
    public Mono<ResponseEntity<PrimaryClaimResubmisionServicelinesDTO>> getPrimaryClaimResubmisionServicelines(@PathVariable Long id) {
        log.debug("REST request to get PrimaryClaimResubmisionServicelines : {}", id);
        Mono<PrimaryClaimResubmisionServicelinesDTO> primaryClaimResubmisionServicelinesDTO = primaryClaimResubmisionServicelinesService.findOne(
            id
        );
        return ResponseUtil.wrapOrNotFound(primaryClaimResubmisionServicelinesDTO);
    }

    /**
     * {@code DELETE  /primary-claim-resubmision-servicelines/:id} : delete the "id" primaryClaimResubmisionServicelines.
     *
     * @param id the id of the primaryClaimResubmisionServicelinesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/primary-claim-resubmision-servicelines/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deletePrimaryClaimResubmisionServicelines(@PathVariable Long id) {
        log.debug("REST request to delete PrimaryClaimResubmisionServicelines : {}", id);
        return primaryClaimResubmisionServicelinesService
            .delete(id)
            .map(result ->
                ResponseEntity
                    .noContent()
                    .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                    .build()
            );
    }
}
