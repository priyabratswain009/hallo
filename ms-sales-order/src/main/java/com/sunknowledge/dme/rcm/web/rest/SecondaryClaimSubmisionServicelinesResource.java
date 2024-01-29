package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.SecondaryClaimSubmisionServicelinesRepository;
import com.sunknowledge.dme.rcm.service.SecondaryClaimSubmisionServicelinesService;
import com.sunknowledge.dme.rcm.service.dto.SecondaryClaimSubmisionServicelinesDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.SecondaryClaimSubmisionServicelines}.
 */
@RestController
@RequestMapping("/api")
public class SecondaryClaimSubmisionServicelinesResource {

    private final Logger log = LoggerFactory.getLogger(SecondaryClaimSubmisionServicelinesResource.class);

    private static final String ENTITY_NAME = "salesorderSecondaryClaimSubmisionServicelines";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SecondaryClaimSubmisionServicelinesService secondaryClaimSubmisionServicelinesService;

    private final SecondaryClaimSubmisionServicelinesRepository secondaryClaimSubmisionServicelinesRepository;

    public SecondaryClaimSubmisionServicelinesResource(
        SecondaryClaimSubmisionServicelinesService secondaryClaimSubmisionServicelinesService,
        SecondaryClaimSubmisionServicelinesRepository secondaryClaimSubmisionServicelinesRepository
    ) {
        this.secondaryClaimSubmisionServicelinesService = secondaryClaimSubmisionServicelinesService;
        this.secondaryClaimSubmisionServicelinesRepository = secondaryClaimSubmisionServicelinesRepository;
    }

    /**
     * {@code POST  /secondary-claim-submision-servicelines} : Create a new secondaryClaimSubmisionServicelines.
     *
     * @param secondaryClaimSubmisionServicelinesDTO the secondaryClaimSubmisionServicelinesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new secondaryClaimSubmisionServicelinesDTO, or with status {@code 400 (Bad Request)} if the secondaryClaimSubmisionServicelines has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/secondary-claim-submision-servicelines")
    public Mono<ResponseEntity<SecondaryClaimSubmisionServicelinesDTO>> createSecondaryClaimSubmisionServicelines(
        @RequestBody SecondaryClaimSubmisionServicelinesDTO secondaryClaimSubmisionServicelinesDTO
    ) throws URISyntaxException {
        log.debug("REST request to save SecondaryClaimSubmisionServicelines : {}", secondaryClaimSubmisionServicelinesDTO);
        if (secondaryClaimSubmisionServicelinesDTO.getChangeHealthSecondarySubmisionServicelinesId() != null) {
            throw new BadRequestAlertException(
                "A new secondaryClaimSubmisionServicelines cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        return secondaryClaimSubmisionServicelinesService
            .save(secondaryClaimSubmisionServicelinesDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(
                            new URI(
                                "/api/secondary-claim-submision-servicelines/" + result.getChangeHealthSecondarySubmisionServicelinesId()
                            )
                        )
                        .headers(
                            HeaderUtil.createEntityCreationAlert(
                                applicationName,
                                false,
                                ENTITY_NAME,
                                result.getChangeHealthSecondarySubmisionServicelinesId().toString()
                            )
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /secondary-claim-submision-servicelines/:changeHealthSecondarySubmisionServicelinesId} : Updates an existing secondaryClaimSubmisionServicelines.
     *
     * @param changeHealthSecondarySubmisionServicelinesId the id of the secondaryClaimSubmisionServicelinesDTO to save.
     * @param secondaryClaimSubmisionServicelinesDTO the secondaryClaimSubmisionServicelinesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated secondaryClaimSubmisionServicelinesDTO,
     * or with status {@code 400 (Bad Request)} if the secondaryClaimSubmisionServicelinesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the secondaryClaimSubmisionServicelinesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/secondary-claim-submision-servicelines/{changeHealthSecondarySubmisionServicelinesId}")
    public Mono<ResponseEntity<SecondaryClaimSubmisionServicelinesDTO>> updateSecondaryClaimSubmisionServicelines(
        @PathVariable(
            value = "changeHealthSecondarySubmisionServicelinesId",
            required = false
        ) final Long changeHealthSecondarySubmisionServicelinesId,
        @RequestBody SecondaryClaimSubmisionServicelinesDTO secondaryClaimSubmisionServicelinesDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to update SecondaryClaimSubmisionServicelines : {}, {}",
            changeHealthSecondarySubmisionServicelinesId,
            secondaryClaimSubmisionServicelinesDTO
        );
        if (secondaryClaimSubmisionServicelinesDTO.getChangeHealthSecondarySubmisionServicelinesId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (
            !Objects.equals(
                changeHealthSecondarySubmisionServicelinesId,
                secondaryClaimSubmisionServicelinesDTO.getChangeHealthSecondarySubmisionServicelinesId()
            )
        ) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return secondaryClaimSubmisionServicelinesRepository
            .existsById(changeHealthSecondarySubmisionServicelinesId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return secondaryClaimSubmisionServicelinesService
                    .update(secondaryClaimSubmisionServicelinesDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getChangeHealthSecondarySubmisionServicelinesId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /secondary-claim-submision-servicelines/:changeHealthSecondarySubmisionServicelinesId} : Partial updates given fields of an existing secondaryClaimSubmisionServicelines, field will ignore if it is null
     *
     * @param changeHealthSecondarySubmisionServicelinesId the id of the secondaryClaimSubmisionServicelinesDTO to save.
     * @param secondaryClaimSubmisionServicelinesDTO the secondaryClaimSubmisionServicelinesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated secondaryClaimSubmisionServicelinesDTO,
     * or with status {@code 400 (Bad Request)} if the secondaryClaimSubmisionServicelinesDTO is not valid,
     * or with status {@code 404 (Not Found)} if the secondaryClaimSubmisionServicelinesDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the secondaryClaimSubmisionServicelinesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/secondary-claim-submision-servicelines/{changeHealthSecondarySubmisionServicelinesId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public Mono<ResponseEntity<SecondaryClaimSubmisionServicelinesDTO>> partialUpdateSecondaryClaimSubmisionServicelines(
        @PathVariable(
            value = "changeHealthSecondarySubmisionServicelinesId",
            required = false
        ) final Long changeHealthSecondarySubmisionServicelinesId,
        @RequestBody SecondaryClaimSubmisionServicelinesDTO secondaryClaimSubmisionServicelinesDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update SecondaryClaimSubmisionServicelines partially : {}, {}",
            changeHealthSecondarySubmisionServicelinesId,
            secondaryClaimSubmisionServicelinesDTO
        );
        if (secondaryClaimSubmisionServicelinesDTO.getChangeHealthSecondarySubmisionServicelinesId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (
            !Objects.equals(
                changeHealthSecondarySubmisionServicelinesId,
                secondaryClaimSubmisionServicelinesDTO.getChangeHealthSecondarySubmisionServicelinesId()
            )
        ) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return secondaryClaimSubmisionServicelinesRepository
            .existsById(changeHealthSecondarySubmisionServicelinesId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<SecondaryClaimSubmisionServicelinesDTO> result = secondaryClaimSubmisionServicelinesService.partialUpdate(
                    secondaryClaimSubmisionServicelinesDTO
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
                                    res.getChangeHealthSecondarySubmisionServicelinesId().toString()
                                )
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /secondary-claim-submision-servicelines} : get all the secondaryClaimSubmisionServicelines.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of secondaryClaimSubmisionServicelines in body.
     */
    @GetMapping("/secondary-claim-submision-servicelines")
    public Mono<ResponseEntity<List<SecondaryClaimSubmisionServicelinesDTO>>> getAllSecondaryClaimSubmisionServicelines(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of SecondaryClaimSubmisionServicelines");
        return secondaryClaimSubmisionServicelinesService
            .countAll()
            .zipWith(secondaryClaimSubmisionServicelinesService.findAll(pageable).collectList())
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
     * {@code GET  /secondary-claim-submision-servicelines/:id} : get the "id" secondaryClaimSubmisionServicelines.
     *
     * @param id the id of the secondaryClaimSubmisionServicelinesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the secondaryClaimSubmisionServicelinesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/secondary-claim-submision-servicelines/{id}")
    public Mono<ResponseEntity<SecondaryClaimSubmisionServicelinesDTO>> getSecondaryClaimSubmisionServicelines(@PathVariable Long id) {
        log.debug("REST request to get SecondaryClaimSubmisionServicelines : {}", id);
        Mono<SecondaryClaimSubmisionServicelinesDTO> secondaryClaimSubmisionServicelinesDTO = secondaryClaimSubmisionServicelinesService.findOne(
            id
        );
        return ResponseUtil.wrapOrNotFound(secondaryClaimSubmisionServicelinesDTO);
    }

    /**
     * {@code DELETE  /secondary-claim-submision-servicelines/:id} : delete the "id" secondaryClaimSubmisionServicelines.
     *
     * @param id the id of the secondaryClaimSubmisionServicelinesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/secondary-claim-submision-servicelines/{id}")
    public Mono<ResponseEntity<Void>> deleteSecondaryClaimSubmisionServicelines(@PathVariable Long id) {
        log.debug("REST request to delete SecondaryClaimSubmisionServicelines : {}", id);
        return secondaryClaimSubmisionServicelinesService
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
