package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.PrimaryClaimResubmisionMasterRepository;
import com.sunknowledge.dme.rcm.service.PrimaryClaimResubmisionMasterService;
import com.sunknowledge.dme.rcm.service.dto.PrimaryClaimResubmisionMasterDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.PrimaryClaimResubmisionMaster}.
 */
@RestController
@RequestMapping("/api")
public class PrimaryClaimResubmisionMasterResource {

    private final Logger log = LoggerFactory.getLogger(PrimaryClaimResubmisionMasterResource.class);

    private static final String ENTITY_NAME = "salesorderPrimaryClaimResubmisionMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PrimaryClaimResubmisionMasterService primaryClaimResubmisionMasterService;

    private final PrimaryClaimResubmisionMasterRepository primaryClaimResubmisionMasterRepository;

    public PrimaryClaimResubmisionMasterResource(
        PrimaryClaimResubmisionMasterService primaryClaimResubmisionMasterService,
        PrimaryClaimResubmisionMasterRepository primaryClaimResubmisionMasterRepository
    ) {
        this.primaryClaimResubmisionMasterService = primaryClaimResubmisionMasterService;
        this.primaryClaimResubmisionMasterRepository = primaryClaimResubmisionMasterRepository;
    }

    /**
     * {@code POST  /primary-claim-resubmision-masters} : Create a new primaryClaimResubmisionMaster.
     *
     * @param primaryClaimResubmisionMasterDTO the primaryClaimResubmisionMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new primaryClaimResubmisionMasterDTO, or with status {@code 400 (Bad Request)} if the primaryClaimResubmisionMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/primary-claim-resubmision-masters")
    public Mono<ResponseEntity<PrimaryClaimResubmisionMasterDTO>> createPrimaryClaimResubmisionMaster(
        @RequestBody PrimaryClaimResubmisionMasterDTO primaryClaimResubmisionMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to save PrimaryClaimResubmisionMaster : {}", primaryClaimResubmisionMasterDTO);
        if (primaryClaimResubmisionMasterDTO.getChangeHealthPrimaryResubmisionMasterId() != null) {
            throw new BadRequestAlertException("A new primaryClaimResubmisionMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return primaryClaimResubmisionMasterService
            .save(primaryClaimResubmisionMasterDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/primary-claim-resubmision-masters/" + result.getChangeHealthPrimaryResubmisionMasterId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(
                                applicationName,
                                false,
                                ENTITY_NAME,
                                result.getChangeHealthPrimaryResubmisionMasterId().toString()
                            )
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /primary-claim-resubmision-masters/:changeHealthPrimaryResubmisionMasterId} : Updates an existing primaryClaimResubmisionMaster.
     *
     * @param changeHealthPrimaryResubmisionMasterId the id of the primaryClaimResubmisionMasterDTO to save.
     * @param primaryClaimResubmisionMasterDTO the primaryClaimResubmisionMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated primaryClaimResubmisionMasterDTO,
     * or with status {@code 400 (Bad Request)} if the primaryClaimResubmisionMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the primaryClaimResubmisionMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/primary-claim-resubmision-masters/{changeHealthPrimaryResubmisionMasterId}")
    public Mono<ResponseEntity<PrimaryClaimResubmisionMasterDTO>> updatePrimaryClaimResubmisionMaster(
        @PathVariable(value = "changeHealthPrimaryResubmisionMasterId", required = false) final Long changeHealthPrimaryResubmisionMasterId,
        @RequestBody PrimaryClaimResubmisionMasterDTO primaryClaimResubmisionMasterDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to update PrimaryClaimResubmisionMaster : {}, {}",
            changeHealthPrimaryResubmisionMasterId,
            primaryClaimResubmisionMasterDTO
        );
        if (primaryClaimResubmisionMasterDTO.getChangeHealthPrimaryResubmisionMasterId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (
            !Objects.equals(
                changeHealthPrimaryResubmisionMasterId,
                primaryClaimResubmisionMasterDTO.getChangeHealthPrimaryResubmisionMasterId()
            )
        ) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return primaryClaimResubmisionMasterRepository
            .existsById(changeHealthPrimaryResubmisionMasterId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return primaryClaimResubmisionMasterService
                    .update(primaryClaimResubmisionMasterDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getChangeHealthPrimaryResubmisionMasterId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /primary-claim-resubmision-masters/:changeHealthPrimaryResubmisionMasterId} : Partial updates given fields of an existing primaryClaimResubmisionMaster, field will ignore if it is null
     *
     * @param changeHealthPrimaryResubmisionMasterId the id of the primaryClaimResubmisionMasterDTO to save.
     * @param primaryClaimResubmisionMasterDTO the primaryClaimResubmisionMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated primaryClaimResubmisionMasterDTO,
     * or with status {@code 400 (Bad Request)} if the primaryClaimResubmisionMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the primaryClaimResubmisionMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the primaryClaimResubmisionMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/primary-claim-resubmision-masters/{changeHealthPrimaryResubmisionMasterId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public Mono<ResponseEntity<PrimaryClaimResubmisionMasterDTO>> partialUpdatePrimaryClaimResubmisionMaster(
        @PathVariable(value = "changeHealthPrimaryResubmisionMasterId", required = false) final Long changeHealthPrimaryResubmisionMasterId,
        @RequestBody PrimaryClaimResubmisionMasterDTO primaryClaimResubmisionMasterDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update PrimaryClaimResubmisionMaster partially : {}, {}",
            changeHealthPrimaryResubmisionMasterId,
            primaryClaimResubmisionMasterDTO
        );
        if (primaryClaimResubmisionMasterDTO.getChangeHealthPrimaryResubmisionMasterId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (
            !Objects.equals(
                changeHealthPrimaryResubmisionMasterId,
                primaryClaimResubmisionMasterDTO.getChangeHealthPrimaryResubmisionMasterId()
            )
        ) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return primaryClaimResubmisionMasterRepository
            .existsById(changeHealthPrimaryResubmisionMasterId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<PrimaryClaimResubmisionMasterDTO> result = primaryClaimResubmisionMasterService.partialUpdate(
                    primaryClaimResubmisionMasterDTO
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
                                    res.getChangeHealthPrimaryResubmisionMasterId().toString()
                                )
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /primary-claim-resubmision-masters} : get all the primaryClaimResubmisionMasters.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of primaryClaimResubmisionMasters in body.
     */
    @GetMapping("/primary-claim-resubmision-masters")
    public Mono<ResponseEntity<List<PrimaryClaimResubmisionMasterDTO>>> getAllPrimaryClaimResubmisionMasters(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of PrimaryClaimResubmisionMasters");
        return primaryClaimResubmisionMasterService
            .countAll()
            .zipWith(primaryClaimResubmisionMasterService.findAll(pageable).collectList())
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
     * {@code GET  /primary-claim-resubmision-masters/:id} : get the "id" primaryClaimResubmisionMaster.
     *
     * @param id the id of the primaryClaimResubmisionMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the primaryClaimResubmisionMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/primary-claim-resubmision-masters/{id}")
    public Mono<ResponseEntity<PrimaryClaimResubmisionMasterDTO>> getPrimaryClaimResubmisionMaster(@PathVariable Long id) {
        log.debug("REST request to get PrimaryClaimResubmisionMaster : {}", id);
        Mono<PrimaryClaimResubmisionMasterDTO> primaryClaimResubmisionMasterDTO = primaryClaimResubmisionMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(primaryClaimResubmisionMasterDTO);
    }

    /**
     * {@code DELETE  /primary-claim-resubmision-masters/:id} : delete the "id" primaryClaimResubmisionMaster.
     *
     * @param id the id of the primaryClaimResubmisionMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/primary-claim-resubmision-masters/{id}")
    public Mono<ResponseEntity<Void>> deletePrimaryClaimResubmisionMaster(@PathVariable Long id) {
        log.debug("REST request to delete PrimaryClaimResubmisionMaster : {}", id);
        return primaryClaimResubmisionMasterService
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
