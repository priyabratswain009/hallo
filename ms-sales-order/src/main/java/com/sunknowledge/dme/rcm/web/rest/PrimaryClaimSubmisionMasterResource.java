package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.PrimaryClaimSubmisionMasterRepository;
import com.sunknowledge.dme.rcm.service.PrimaryClaimSubmisionMasterService;
import com.sunknowledge.dme.rcm.service.dto.PrimaryClaimSubmisionMasterDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.PrimaryClaimSubmisionMaster}.
 */
@RestController
@RequestMapping("/api")
public class PrimaryClaimSubmisionMasterResource {

    private final Logger log = LoggerFactory.getLogger(PrimaryClaimSubmisionMasterResource.class);

    private static final String ENTITY_NAME = "salesorderPrimaryClaimSubmisionMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PrimaryClaimSubmisionMasterService primaryClaimSubmisionMasterService;

    private final PrimaryClaimSubmisionMasterRepository primaryClaimSubmisionMasterRepository;

    public PrimaryClaimSubmisionMasterResource(
        PrimaryClaimSubmisionMasterService primaryClaimSubmisionMasterService,
        PrimaryClaimSubmisionMasterRepository primaryClaimSubmisionMasterRepository
    ) {
        this.primaryClaimSubmisionMasterService = primaryClaimSubmisionMasterService;
        this.primaryClaimSubmisionMasterRepository = primaryClaimSubmisionMasterRepository;
    }

    /**
     * {@code POST  /primary-claim-submision-masters} : Create a new primaryClaimSubmisionMaster.
     *
     * @param primaryClaimSubmisionMasterDTO the primaryClaimSubmisionMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new primaryClaimSubmisionMasterDTO, or with status {@code 400 (Bad Request)} if the primaryClaimSubmisionMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/primary-claim-submision-masters")
    public Mono<ResponseEntity<PrimaryClaimSubmisionMasterDTO>> createPrimaryClaimSubmisionMaster(
        @RequestBody PrimaryClaimSubmisionMasterDTO primaryClaimSubmisionMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to save PrimaryClaimSubmisionMaster : {}", primaryClaimSubmisionMasterDTO);
        if (primaryClaimSubmisionMasterDTO.getChangeHealthPrimarySubmisionMasterId() != null) {
            throw new BadRequestAlertException("A new primaryClaimSubmisionMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return primaryClaimSubmisionMasterService
            .save(primaryClaimSubmisionMasterDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/primary-claim-submision-masters/" + result.getChangeHealthPrimarySubmisionMasterId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(
                                applicationName,
                                false,
                                ENTITY_NAME,
                                result.getChangeHealthPrimarySubmisionMasterId().toString()
                            )
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /primary-claim-submision-masters/:changeHealthPrimarySubmisionMasterId} : Updates an existing primaryClaimSubmisionMaster.
     *
     * @param changeHealthPrimarySubmisionMasterId the id of the primaryClaimSubmisionMasterDTO to save.
     * @param primaryClaimSubmisionMasterDTO the primaryClaimSubmisionMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated primaryClaimSubmisionMasterDTO,
     * or with status {@code 400 (Bad Request)} if the primaryClaimSubmisionMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the primaryClaimSubmisionMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/primary-claim-submision-masters/{changeHealthPrimarySubmisionMasterId}")
    public Mono<ResponseEntity<PrimaryClaimSubmisionMasterDTO>> updatePrimaryClaimSubmisionMaster(
        @PathVariable(value = "changeHealthPrimarySubmisionMasterId", required = false) final Long changeHealthPrimarySubmisionMasterId,
        @RequestBody PrimaryClaimSubmisionMasterDTO primaryClaimSubmisionMasterDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to update PrimaryClaimSubmisionMaster : {}, {}",
            changeHealthPrimarySubmisionMasterId,
            primaryClaimSubmisionMasterDTO
        );
        if (primaryClaimSubmisionMasterDTO.getChangeHealthPrimarySubmisionMasterId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (
            !Objects.equals(changeHealthPrimarySubmisionMasterId, primaryClaimSubmisionMasterDTO.getChangeHealthPrimarySubmisionMasterId())
        ) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return primaryClaimSubmisionMasterRepository
            .existsById(changeHealthPrimarySubmisionMasterId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return primaryClaimSubmisionMasterService
                    .update(primaryClaimSubmisionMasterDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getChangeHealthPrimarySubmisionMasterId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /primary-claim-submision-masters/:changeHealthPrimarySubmisionMasterId} : Partial updates given fields of an existing primaryClaimSubmisionMaster, field will ignore if it is null
     *
     * @param changeHealthPrimarySubmisionMasterId the id of the primaryClaimSubmisionMasterDTO to save.
     * @param primaryClaimSubmisionMasterDTO the primaryClaimSubmisionMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated primaryClaimSubmisionMasterDTO,
     * or with status {@code 400 (Bad Request)} if the primaryClaimSubmisionMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the primaryClaimSubmisionMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the primaryClaimSubmisionMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/primary-claim-submision-masters/{changeHealthPrimarySubmisionMasterId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public Mono<ResponseEntity<PrimaryClaimSubmisionMasterDTO>> partialUpdatePrimaryClaimSubmisionMaster(
        @PathVariable(value = "changeHealthPrimarySubmisionMasterId", required = false) final Long changeHealthPrimarySubmisionMasterId,
        @RequestBody PrimaryClaimSubmisionMasterDTO primaryClaimSubmisionMasterDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update PrimaryClaimSubmisionMaster partially : {}, {}",
            changeHealthPrimarySubmisionMasterId,
            primaryClaimSubmisionMasterDTO
        );
        if (primaryClaimSubmisionMasterDTO.getChangeHealthPrimarySubmisionMasterId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (
            !Objects.equals(changeHealthPrimarySubmisionMasterId, primaryClaimSubmisionMasterDTO.getChangeHealthPrimarySubmisionMasterId())
        ) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return primaryClaimSubmisionMasterRepository
            .existsById(changeHealthPrimarySubmisionMasterId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<PrimaryClaimSubmisionMasterDTO> result = primaryClaimSubmisionMasterService.partialUpdate(
                    primaryClaimSubmisionMasterDTO
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
                                    res.getChangeHealthPrimarySubmisionMasterId().toString()
                                )
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /primary-claim-submision-masters} : get all the primaryClaimSubmisionMasters.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of primaryClaimSubmisionMasters in body.
     */
    @GetMapping("/primary-claim-submision-masters")
    public Mono<ResponseEntity<List<PrimaryClaimSubmisionMasterDTO>>> getAllPrimaryClaimSubmisionMasters(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of PrimaryClaimSubmisionMasters");
        return primaryClaimSubmisionMasterService
            .countAll()
            .zipWith(primaryClaimSubmisionMasterService.findAll(pageable).collectList())
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
     * {@code GET  /primary-claim-submision-masters/:id} : get the "id" primaryClaimSubmisionMaster.
     *
     * @param id the id of the primaryClaimSubmisionMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the primaryClaimSubmisionMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/primary-claim-submision-masters/{id}")
    public Mono<ResponseEntity<PrimaryClaimSubmisionMasterDTO>> getPrimaryClaimSubmisionMaster(@PathVariable Long id) {
        log.debug("REST request to get PrimaryClaimSubmisionMaster : {}", id);
        Mono<PrimaryClaimSubmisionMasterDTO> primaryClaimSubmisionMasterDTO = primaryClaimSubmisionMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(primaryClaimSubmisionMasterDTO);
    }

    /**
     * {@code DELETE  /primary-claim-submision-masters/:id} : delete the "id" primaryClaimSubmisionMaster.
     *
     * @param id the id of the primaryClaimSubmisionMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/primary-claim-submision-masters/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deletePrimaryClaimSubmisionMaster(@PathVariable Long id) {
        log.debug("REST request to delete PrimaryClaimSubmisionMaster : {}", id);
        return primaryClaimSubmisionMasterService
            .delete(id)
            .map(result ->
                ResponseEntity
                    .noContent()
                    .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                    .build()
            );
    }
}
