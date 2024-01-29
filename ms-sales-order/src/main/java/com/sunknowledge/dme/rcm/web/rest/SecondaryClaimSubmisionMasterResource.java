package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.SecondaryClaimSubmisionMasterRepository;
import com.sunknowledge.dme.rcm.service.SecondaryClaimSubmisionMasterService;
import com.sunknowledge.dme.rcm.service.dto.SecondaryClaimSubmisionMasterDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.SecondaryClaimSubmisionMaster}.
 */
@RestController
@RequestMapping("/api")
public class SecondaryClaimSubmisionMasterResource {

    private final Logger log = LoggerFactory.getLogger(SecondaryClaimSubmisionMasterResource.class);

    private static final String ENTITY_NAME = "salesorderSecondaryClaimSubmisionMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SecondaryClaimSubmisionMasterService secondaryClaimSubmisionMasterService;

    private final SecondaryClaimSubmisionMasterRepository secondaryClaimSubmisionMasterRepository;

    public SecondaryClaimSubmisionMasterResource(
        SecondaryClaimSubmisionMasterService secondaryClaimSubmisionMasterService,
        SecondaryClaimSubmisionMasterRepository secondaryClaimSubmisionMasterRepository
    ) {
        this.secondaryClaimSubmisionMasterService = secondaryClaimSubmisionMasterService;
        this.secondaryClaimSubmisionMasterRepository = secondaryClaimSubmisionMasterRepository;
    }

    /**
     * {@code POST  /secondary-claim-submision-masters} : Create a new secondaryClaimSubmisionMaster.
     *
     * @param secondaryClaimSubmisionMasterDTO the secondaryClaimSubmisionMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new secondaryClaimSubmisionMasterDTO, or with status {@code 400 (Bad Request)} if the secondaryClaimSubmisionMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/secondary-claim-submision-masters")
    public Mono<ResponseEntity<SecondaryClaimSubmisionMasterDTO>> createSecondaryClaimSubmisionMaster(
        @RequestBody SecondaryClaimSubmisionMasterDTO secondaryClaimSubmisionMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to save SecondaryClaimSubmisionMaster : {}", secondaryClaimSubmisionMasterDTO);
        if (secondaryClaimSubmisionMasterDTO.getChangeHealthSecondarySubmisionMasterId() != null) {
            throw new BadRequestAlertException("A new secondaryClaimSubmisionMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return secondaryClaimSubmisionMasterService
            .save(secondaryClaimSubmisionMasterDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/secondary-claim-submision-masters/" + result.getChangeHealthSecondarySubmisionMasterId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(
                                applicationName,
                                false,
                                ENTITY_NAME,
                                result.getChangeHealthSecondarySubmisionMasterId().toString()
                            )
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /secondary-claim-submision-masters/:changeHealthSecondarySubmisionMasterId} : Updates an existing secondaryClaimSubmisionMaster.
     *
     * @param changeHealthSecondarySubmisionMasterId the id of the secondaryClaimSubmisionMasterDTO to save.
     * @param secondaryClaimSubmisionMasterDTO the secondaryClaimSubmisionMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated secondaryClaimSubmisionMasterDTO,
     * or with status {@code 400 (Bad Request)} if the secondaryClaimSubmisionMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the secondaryClaimSubmisionMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/secondary-claim-submision-masters/{changeHealthSecondarySubmisionMasterId}")
    public Mono<ResponseEntity<SecondaryClaimSubmisionMasterDTO>> updateSecondaryClaimSubmisionMaster(
        @PathVariable(value = "changeHealthSecondarySubmisionMasterId", required = false) final Long changeHealthSecondarySubmisionMasterId,
        @RequestBody SecondaryClaimSubmisionMasterDTO secondaryClaimSubmisionMasterDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to update SecondaryClaimSubmisionMaster : {}, {}",
            changeHealthSecondarySubmisionMasterId,
            secondaryClaimSubmisionMasterDTO
        );
        if (secondaryClaimSubmisionMasterDTO.getChangeHealthSecondarySubmisionMasterId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (
            !Objects.equals(
                changeHealthSecondarySubmisionMasterId,
                secondaryClaimSubmisionMasterDTO.getChangeHealthSecondarySubmisionMasterId()
            )
        ) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return secondaryClaimSubmisionMasterRepository
            .existsById(changeHealthSecondarySubmisionMasterId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return secondaryClaimSubmisionMasterService
                    .update(secondaryClaimSubmisionMasterDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getChangeHealthSecondarySubmisionMasterId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /secondary-claim-submision-masters/:changeHealthSecondarySubmisionMasterId} : Partial updates given fields of an existing secondaryClaimSubmisionMaster, field will ignore if it is null
     *
     * @param changeHealthSecondarySubmisionMasterId the id of the secondaryClaimSubmisionMasterDTO to save.
     * @param secondaryClaimSubmisionMasterDTO the secondaryClaimSubmisionMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated secondaryClaimSubmisionMasterDTO,
     * or with status {@code 400 (Bad Request)} if the secondaryClaimSubmisionMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the secondaryClaimSubmisionMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the secondaryClaimSubmisionMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/secondary-claim-submision-masters/{changeHealthSecondarySubmisionMasterId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public Mono<ResponseEntity<SecondaryClaimSubmisionMasterDTO>> partialUpdateSecondaryClaimSubmisionMaster(
        @PathVariable(value = "changeHealthSecondarySubmisionMasterId", required = false) final Long changeHealthSecondarySubmisionMasterId,
        @RequestBody SecondaryClaimSubmisionMasterDTO secondaryClaimSubmisionMasterDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update SecondaryClaimSubmisionMaster partially : {}, {}",
            changeHealthSecondarySubmisionMasterId,
            secondaryClaimSubmisionMasterDTO
        );
        if (secondaryClaimSubmisionMasterDTO.getChangeHealthSecondarySubmisionMasterId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (
            !Objects.equals(
                changeHealthSecondarySubmisionMasterId,
                secondaryClaimSubmisionMasterDTO.getChangeHealthSecondarySubmisionMasterId()
            )
        ) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return secondaryClaimSubmisionMasterRepository
            .existsById(changeHealthSecondarySubmisionMasterId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<SecondaryClaimSubmisionMasterDTO> result = secondaryClaimSubmisionMasterService.partialUpdate(
                    secondaryClaimSubmisionMasterDTO
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
                                    res.getChangeHealthSecondarySubmisionMasterId().toString()
                                )
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /secondary-claim-submision-masters} : get all the secondaryClaimSubmisionMasters.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of secondaryClaimSubmisionMasters in body.
     */
    @GetMapping("/secondary-claim-submision-masters")
    public Mono<ResponseEntity<List<SecondaryClaimSubmisionMasterDTO>>> getAllSecondaryClaimSubmisionMasters(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of SecondaryClaimSubmisionMasters");
        return secondaryClaimSubmisionMasterService
            .countAll()
            .zipWith(secondaryClaimSubmisionMasterService.findAll(pageable).collectList())
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
     * {@code GET  /secondary-claim-submision-masters/:id} : get the "id" secondaryClaimSubmisionMaster.
     *
     * @param id the id of the secondaryClaimSubmisionMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the secondaryClaimSubmisionMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/secondary-claim-submision-masters/{id}")
    public Mono<ResponseEntity<SecondaryClaimSubmisionMasterDTO>> getSecondaryClaimSubmisionMaster(@PathVariable Long id) {
        log.debug("REST request to get SecondaryClaimSubmisionMaster : {}", id);
        Mono<SecondaryClaimSubmisionMasterDTO> secondaryClaimSubmisionMasterDTO = secondaryClaimSubmisionMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(secondaryClaimSubmisionMasterDTO);
    }

    /**
     * {@code DELETE  /secondary-claim-submision-masters/:id} : delete the "id" secondaryClaimSubmisionMaster.
     *
     * @param id the id of the secondaryClaimSubmisionMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/secondary-claim-submision-masters/{id}")
    public Mono<ResponseEntity<Void>> deleteSecondaryClaimSubmisionMaster(@PathVariable Long id) {
        log.debug("REST request to delete SecondaryClaimSubmisionMaster : {}", id);
        return secondaryClaimSubmisionMasterService
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
