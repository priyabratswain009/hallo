package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.WorkersCompensationRepository;
import com.sunknowledge.dme.rcm.service.WorkersCompensationService;
import com.sunknowledge.dme.rcm.service.dto.WorkersCompensationDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.WorkersCompensation}.
 */
@RestController
@RequestMapping("/api")
public class WorkersCompensationResource {

    private final Logger log = LoggerFactory.getLogger(WorkersCompensationResource.class);

    private static final String ENTITY_NAME = "patientWorkersCompensation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WorkersCompensationService workersCompensationService;

    private final WorkersCompensationRepository workersCompensationRepository;

    public WorkersCompensationResource(
        WorkersCompensationService workersCompensationService,
        WorkersCompensationRepository workersCompensationRepository
    ) {
        this.workersCompensationService = workersCompensationService;
        this.workersCompensationRepository = workersCompensationRepository;
    }

    /**
     * {@code POST  /workers-compensations} : Create a new workersCompensation.
     *
     * @param workersCompensationDTO the workersCompensationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new workersCompensationDTO, or with status {@code 400 (Bad Request)} if the workersCompensation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/workers-compensations")
    public Mono<ResponseEntity<WorkersCompensationDTO>> createWorkersCompensation(
        @RequestBody WorkersCompensationDTO workersCompensationDTO
    ) throws URISyntaxException {
        log.debug("REST request to save WorkersCompensation : {}", workersCompensationDTO);
        if (workersCompensationDTO.getWorkersCompensationId() != null) {
            throw new BadRequestAlertException("A new workersCompensation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return workersCompensationService
            .save(workersCompensationDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/workers-compensations/" + result.getWorkersCompensationId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(
                                applicationName,
                                false,
                                ENTITY_NAME,
                                result.getWorkersCompensationId().toString()
                            )
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /workers-compensations/:workersCompensationId} : Updates an existing workersCompensation.
     *
     * @param workersCompensationId the id of the workersCompensationDTO to save.
     * @param workersCompensationDTO the workersCompensationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated workersCompensationDTO,
     * or with status {@code 400 (Bad Request)} if the workersCompensationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the workersCompensationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/workers-compensations/{workersCompensationId}")
    public Mono<ResponseEntity<WorkersCompensationDTO>> updateWorkersCompensation(
        @PathVariable(value = "workersCompensationId", required = false) final Long workersCompensationId,
        @RequestBody WorkersCompensationDTO workersCompensationDTO
    ) throws URISyntaxException {
        log.debug("REST request to update WorkersCompensation : {}, {}", workersCompensationId, workersCompensationDTO);
        if (workersCompensationDTO.getWorkersCompensationId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(workersCompensationId, workersCompensationDTO.getWorkersCompensationId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return workersCompensationRepository
            .existsById(workersCompensationId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return workersCompensationService
                    .update(workersCompensationDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getWorkersCompensationId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /workers-compensations/:workersCompensationId} : Partial updates given fields of an existing workersCompensation, field will ignore if it is null
     *
     * @param workersCompensationId the id of the workersCompensationDTO to save.
     * @param workersCompensationDTO the workersCompensationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated workersCompensationDTO,
     * or with status {@code 400 (Bad Request)} if the workersCompensationDTO is not valid,
     * or with status {@code 404 (Not Found)} if the workersCompensationDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the workersCompensationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/workers-compensations/{workersCompensationId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public Mono<ResponseEntity<WorkersCompensationDTO>> partialUpdateWorkersCompensation(
        @PathVariable(value = "workersCompensationId", required = false) final Long workersCompensationId,
        @RequestBody WorkersCompensationDTO workersCompensationDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update WorkersCompensation partially : {}, {}", workersCompensationId, workersCompensationDTO);
        if (workersCompensationDTO.getWorkersCompensationId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(workersCompensationId, workersCompensationDTO.getWorkersCompensationId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return workersCompensationRepository
            .existsById(workersCompensationId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<WorkersCompensationDTO> result = workersCompensationService.partialUpdate(workersCompensationDTO);

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
                                    res.getWorkersCompensationId().toString()
                                )
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /workers-compensations} : get all the workersCompensations.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of workersCompensations in body.
     */
    @GetMapping("/workers-compensations")
    public Mono<ResponseEntity<List<WorkersCompensationDTO>>> getAllWorkersCompensations(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of WorkersCompensations");
        return workersCompensationService
            .countAll()
            .zipWith(workersCompensationService.findAll(pageable).collectList())
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
     * {@code GET  /workers-compensations/:id} : get the "id" workersCompensation.
     *
     * @param id the id of the workersCompensationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the workersCompensationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/workers-compensations/{id}")
    public Mono<ResponseEntity<WorkersCompensationDTO>> getWorkersCompensation(@PathVariable Long id) {
        log.debug("REST request to get WorkersCompensation : {}", id);
        Mono<WorkersCompensationDTO> workersCompensationDTO = workersCompensationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(workersCompensationDTO);
    }

    /**
     * {@code DELETE  /workers-compensations/:id} : delete the "id" workersCompensation.
     *
     * @param id the id of the workersCompensationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/workers-compensations/{id}")
    public Mono<ResponseEntity<Void>> deleteWorkersCompensation(@PathVariable Long id) {
        log.debug("REST request to delete WorkersCompensation : {}", id);
        return workersCompensationService
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
