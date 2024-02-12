package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ClaimSubmissionStatusRepository;
import com.sunknowledge.dme.rcm.service.ClaimSubmissionStatusService;
import com.sunknowledge.dme.rcm.service.dto.ClaimSubmissionStatusDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ClaimSubmissionStatus}.
 */
@RestController
@RequestMapping("/api")
public class ClaimSubmissionStatusResource {

    private final Logger log = LoggerFactory.getLogger(ClaimSubmissionStatusResource.class);

    private static final String ENTITY_NAME = "salesorderClaimSubmissionStatus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClaimSubmissionStatusService claimSubmissionStatusService;

    private final ClaimSubmissionStatusRepository claimSubmissionStatusRepository;

    public ClaimSubmissionStatusResource(
        ClaimSubmissionStatusService claimSubmissionStatusService,
        ClaimSubmissionStatusRepository claimSubmissionStatusRepository
    ) {
        this.claimSubmissionStatusService = claimSubmissionStatusService;
        this.claimSubmissionStatusRepository = claimSubmissionStatusRepository;
    }

    /**
     * {@code POST  /claim-submission-statuses} : Create a new claimSubmissionStatus.
     *
     * @param claimSubmissionStatusDTO the claimSubmissionStatusDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new claimSubmissionStatusDTO, or with status {@code 400 (Bad Request)} if the claimSubmissionStatus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/claim-submission-statuses")
    public Mono<ResponseEntity<ClaimSubmissionStatusDTO>> createClaimSubmissionStatus(
        @RequestBody ClaimSubmissionStatusDTO claimSubmissionStatusDTO
    ) throws URISyntaxException {
        log.debug("REST request to save ClaimSubmissionStatus : {}", claimSubmissionStatusDTO);
        if (claimSubmissionStatusDTO.getClaimStatusId() != null) {
            throw new BadRequestAlertException("A new claimSubmissionStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return claimSubmissionStatusService
            .save(claimSubmissionStatusDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/claim-submission-statuses/" + result.getClaimStatusId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getClaimStatusId().toString())
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /claim-submission-statuses/:claimStatusId} : Updates an existing claimSubmissionStatus.
     *
     * @param claimStatusId the id of the claimSubmissionStatusDTO to save.
     * @param claimSubmissionStatusDTO the claimSubmissionStatusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claimSubmissionStatusDTO,
     * or with status {@code 400 (Bad Request)} if the claimSubmissionStatusDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the claimSubmissionStatusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/claim-submission-statuses/{claimStatusId}")
    public Mono<ResponseEntity<ClaimSubmissionStatusDTO>> updateClaimSubmissionStatus(
        @PathVariable(value = "claimStatusId", required = false) final Long claimStatusId,
        @RequestBody ClaimSubmissionStatusDTO claimSubmissionStatusDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ClaimSubmissionStatus : {}, {}", claimStatusId, claimSubmissionStatusDTO);
        if (claimSubmissionStatusDTO.getClaimStatusId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(claimStatusId, claimSubmissionStatusDTO.getClaimStatusId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return claimSubmissionStatusRepository
            .existsById(claimStatusId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return claimSubmissionStatusService
                    .update(claimSubmissionStatusDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getClaimStatusId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /claim-submission-statuses/:claimStatusId} : Partial updates given fields of an existing claimSubmissionStatus, field will ignore if it is null
     *
     * @param claimStatusId the id of the claimSubmissionStatusDTO to save.
     * @param claimSubmissionStatusDTO the claimSubmissionStatusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claimSubmissionStatusDTO,
     * or with status {@code 400 (Bad Request)} if the claimSubmissionStatusDTO is not valid,
     * or with status {@code 404 (Not Found)} if the claimSubmissionStatusDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the claimSubmissionStatusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/claim-submission-statuses/{claimStatusId}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<ClaimSubmissionStatusDTO>> partialUpdateClaimSubmissionStatus(
        @PathVariable(value = "claimStatusId", required = false) final Long claimStatusId,
        @RequestBody ClaimSubmissionStatusDTO claimSubmissionStatusDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ClaimSubmissionStatus partially : {}, {}", claimStatusId, claimSubmissionStatusDTO);
        if (claimSubmissionStatusDTO.getClaimStatusId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(claimStatusId, claimSubmissionStatusDTO.getClaimStatusId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return claimSubmissionStatusRepository
            .existsById(claimStatusId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<ClaimSubmissionStatusDTO> result = claimSubmissionStatusService.partialUpdate(claimSubmissionStatusDTO);

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(res ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, res.getClaimStatusId().toString())
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /claim-submission-statuses} : get all the claimSubmissionStatuses.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of claimSubmissionStatuses in body.
     */
    @GetMapping("/claim-submission-statuses")
    public Mono<ResponseEntity<List<ClaimSubmissionStatusDTO>>> getAllClaimSubmissionStatuses(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of ClaimSubmissionStatuses");
        return claimSubmissionStatusService
            .countAll()
            .zipWith(claimSubmissionStatusService.findAll(pageable).collectList())
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
     * {@code GET  /claim-submission-statuses/:id} : get the "id" claimSubmissionStatus.
     *
     * @param id the id of the claimSubmissionStatusDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the claimSubmissionStatusDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/claim-submission-statuses/{id}")
    public Mono<ResponseEntity<ClaimSubmissionStatusDTO>> getClaimSubmissionStatus(@PathVariable Long id) {
        log.debug("REST request to get ClaimSubmissionStatus : {}", id);
        Mono<ClaimSubmissionStatusDTO> claimSubmissionStatusDTO = claimSubmissionStatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(claimSubmissionStatusDTO);
    }

    /**
     * {@code DELETE  /claim-submission-statuses/:id} : delete the "id" claimSubmissionStatus.
     *
     * @param id the id of the claimSubmissionStatusDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/claim-submission-statuses/{id}")
    public Mono<ResponseEntity<Void>> deleteClaimSubmissionStatus(@PathVariable Long id) {
        log.debug("REST request to delete ClaimSubmissionStatus : {}", id);
        return claimSubmissionStatusService
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
