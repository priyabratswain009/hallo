package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ChecklistCoverageCriteriaMapRepository;
import com.sunknowledge.dme.rcm.service.ChecklistCoverageCriteriaMapService;
import com.sunknowledge.dme.rcm.service.dto.ChecklistCoverageCriteriaMapDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ChecklistCoverageCriteriaMap}.
 */
@RestController
@RequestMapping("/api")
public class ChecklistCoverageCriteriaMapResource {

    private final Logger log = LoggerFactory.getLogger(ChecklistCoverageCriteriaMapResource.class);

    private static final String ENTITY_NAME = "salesorderChecklistCoverageCriteriaMap";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ChecklistCoverageCriteriaMapService checklistCoverageCriteriaMapService;

    private final ChecklistCoverageCriteriaMapRepository checklistCoverageCriteriaMapRepository;

    public ChecklistCoverageCriteriaMapResource(
        ChecklistCoverageCriteriaMapService checklistCoverageCriteriaMapService,
        ChecklistCoverageCriteriaMapRepository checklistCoverageCriteriaMapRepository
    ) {
        this.checklistCoverageCriteriaMapService = checklistCoverageCriteriaMapService;
        this.checklistCoverageCriteriaMapRepository = checklistCoverageCriteriaMapRepository;
    }

    /**
     * {@code POST  /checklist-coverage-criteria-maps} : Create a new checklistCoverageCriteriaMap.
     *
     * @param checklistCoverageCriteriaMapDTO the checklistCoverageCriteriaMapDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new checklistCoverageCriteriaMapDTO, or with status {@code 400 (Bad Request)} if the checklistCoverageCriteriaMap has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/checklist-coverage-criteria-maps")
    public Mono<ResponseEntity<ChecklistCoverageCriteriaMapDTO>> createChecklistCoverageCriteriaMap(
        @RequestBody ChecklistCoverageCriteriaMapDTO checklistCoverageCriteriaMapDTO
    ) throws URISyntaxException {
        log.debug("REST request to save ChecklistCoverageCriteriaMap : {}", checklistCoverageCriteriaMapDTO);
        if (checklistCoverageCriteriaMapDTO.getChecklistCoverageCriteriaId() != null) {
            throw new BadRequestAlertException("A new checklistCoverageCriteriaMap cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return checklistCoverageCriteriaMapService
            .save(checklistCoverageCriteriaMapDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/checklist-coverage-criteria-maps/" + result.getChecklistCoverageCriteriaId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(
                                applicationName,
                                false,
                                ENTITY_NAME,
                                result.getChecklistCoverageCriteriaId().toString()
                            )
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /checklist-coverage-criteria-maps/:checklistCoverageCriteriaId} : Updates an existing checklistCoverageCriteriaMap.
     *
     * @param checklistCoverageCriteriaId the id of the checklistCoverageCriteriaMapDTO to save.
     * @param checklistCoverageCriteriaMapDTO the checklistCoverageCriteriaMapDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated checklistCoverageCriteriaMapDTO,
     * or with status {@code 400 (Bad Request)} if the checklistCoverageCriteriaMapDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the checklistCoverageCriteriaMapDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/checklist-coverage-criteria-maps/{checklistCoverageCriteriaId}")
    public Mono<ResponseEntity<ChecklistCoverageCriteriaMapDTO>> updateChecklistCoverageCriteriaMap(
        @PathVariable(value = "checklistCoverageCriteriaId", required = false) final Long checklistCoverageCriteriaId,
        @RequestBody ChecklistCoverageCriteriaMapDTO checklistCoverageCriteriaMapDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to update ChecklistCoverageCriteriaMap : {}, {}",
            checklistCoverageCriteriaId,
            checklistCoverageCriteriaMapDTO
        );
        if (checklistCoverageCriteriaMapDTO.getChecklistCoverageCriteriaId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(checklistCoverageCriteriaId, checklistCoverageCriteriaMapDTO.getChecklistCoverageCriteriaId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return checklistCoverageCriteriaMapRepository
            .existsById(checklistCoverageCriteriaId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return checklistCoverageCriteriaMapService
                    .update(checklistCoverageCriteriaMapDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getChecklistCoverageCriteriaId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /checklist-coverage-criteria-maps/:checklistCoverageCriteriaId} : Partial updates given fields of an existing checklistCoverageCriteriaMap, field will ignore if it is null
     *
     * @param checklistCoverageCriteriaId the id of the checklistCoverageCriteriaMapDTO to save.
     * @param checklistCoverageCriteriaMapDTO the checklistCoverageCriteriaMapDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated checklistCoverageCriteriaMapDTO,
     * or with status {@code 400 (Bad Request)} if the checklistCoverageCriteriaMapDTO is not valid,
     * or with status {@code 404 (Not Found)} if the checklistCoverageCriteriaMapDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the checklistCoverageCriteriaMapDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/checklist-coverage-criteria-maps/{checklistCoverageCriteriaId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public Mono<ResponseEntity<ChecklistCoverageCriteriaMapDTO>> partialUpdateChecklistCoverageCriteriaMap(
        @PathVariable(value = "checklistCoverageCriteriaId", required = false) final Long checklistCoverageCriteriaId,
        @RequestBody ChecklistCoverageCriteriaMapDTO checklistCoverageCriteriaMapDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update ChecklistCoverageCriteriaMap partially : {}, {}",
            checklistCoverageCriteriaId,
            checklistCoverageCriteriaMapDTO
        );
        if (checklistCoverageCriteriaMapDTO.getChecklistCoverageCriteriaId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(checklistCoverageCriteriaId, checklistCoverageCriteriaMapDTO.getChecklistCoverageCriteriaId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return checklistCoverageCriteriaMapRepository
            .existsById(checklistCoverageCriteriaId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<ChecklistCoverageCriteriaMapDTO> result = checklistCoverageCriteriaMapService.partialUpdate(
                    checklistCoverageCriteriaMapDTO
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
                                    res.getChecklistCoverageCriteriaId().toString()
                                )
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /checklist-coverage-criteria-maps} : get all the checklistCoverageCriteriaMaps.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of checklistCoverageCriteriaMaps in body.
     */
    @GetMapping("/checklist-coverage-criteria-maps")
    public Mono<ResponseEntity<List<ChecklistCoverageCriteriaMapDTO>>> getAllChecklistCoverageCriteriaMaps(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of ChecklistCoverageCriteriaMaps");
        return checklistCoverageCriteriaMapService
            .countAll()
            .zipWith(checklistCoverageCriteriaMapService.findAll(pageable).collectList())
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
     * {@code GET  /checklist-coverage-criteria-maps/:id} : get the "id" checklistCoverageCriteriaMap.
     *
     * @param id the id of the checklistCoverageCriteriaMapDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the checklistCoverageCriteriaMapDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/checklist-coverage-criteria-maps/{id}")
    public Mono<ResponseEntity<ChecklistCoverageCriteriaMapDTO>> getChecklistCoverageCriteriaMap(@PathVariable Long id) {
        log.debug("REST request to get ChecklistCoverageCriteriaMap : {}", id);
        Mono<ChecklistCoverageCriteriaMapDTO> checklistCoverageCriteriaMapDTO = checklistCoverageCriteriaMapService.findOne(id);
        return ResponseUtil.wrapOrNotFound(checklistCoverageCriteriaMapDTO);
    }

    /**
     * {@code DELETE  /checklist-coverage-criteria-maps/:id} : delete the "id" checklistCoverageCriteriaMap.
     *
     * @param id the id of the checklistCoverageCriteriaMapDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/checklist-coverage-criteria-maps/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteChecklistCoverageCriteriaMap(@PathVariable Long id) {
        log.debug("REST request to delete ChecklistCoverageCriteriaMap : {}", id);
        return checklistCoverageCriteriaMapService
            .delete(id)
            .map(result ->
                ResponseEntity
                    .noContent()
                    .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                    .build()
            );
    }
}
