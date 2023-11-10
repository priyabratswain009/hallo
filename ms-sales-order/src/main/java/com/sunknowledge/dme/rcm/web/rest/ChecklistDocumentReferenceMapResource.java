package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ChecklistDocumentReferenceMapRepository;
import com.sunknowledge.dme.rcm.service.ChecklistDocumentReferenceMapService;
import com.sunknowledge.dme.rcm.service.dto.ChecklistDocumentReferenceMapDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ChecklistDocumentReferenceMap}.
 */
@RestController
@RequestMapping("/api")
public class ChecklistDocumentReferenceMapResource {

    private final Logger log = LoggerFactory.getLogger(ChecklistDocumentReferenceMapResource.class);

    private static final String ENTITY_NAME = "salesorderChecklistDocumentReferenceMap";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ChecklistDocumentReferenceMapService checklistDocumentReferenceMapService;

    private final ChecklistDocumentReferenceMapRepository checklistDocumentReferenceMapRepository;

    public ChecklistDocumentReferenceMapResource(
        ChecklistDocumentReferenceMapService checklistDocumentReferenceMapService,
        ChecklistDocumentReferenceMapRepository checklistDocumentReferenceMapRepository
    ) {
        this.checklistDocumentReferenceMapService = checklistDocumentReferenceMapService;
        this.checklistDocumentReferenceMapRepository = checklistDocumentReferenceMapRepository;
    }

    /**
     * {@code POST  /checklist-document-reference-maps} : Create a new checklistDocumentReferenceMap.
     *
     * @param checklistDocumentReferenceMapDTO the checklistDocumentReferenceMapDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new checklistDocumentReferenceMapDTO, or with status {@code 400 (Bad Request)} if the checklistDocumentReferenceMap has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/checklist-document-reference-maps")
    public Mono<ResponseEntity<ChecklistDocumentReferenceMapDTO>> createChecklistDocumentReferenceMap(
        @RequestBody ChecklistDocumentReferenceMapDTO checklistDocumentReferenceMapDTO
    ) throws URISyntaxException {
        log.debug("REST request to save ChecklistDocumentReferenceMap : {}", checklistDocumentReferenceMapDTO);
        if (checklistDocumentReferenceMapDTO.getChecklistDocumentReferenceId() != null) {
            throw new BadRequestAlertException("A new checklistDocumentReferenceMap cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return checklistDocumentReferenceMapService
            .save(checklistDocumentReferenceMapDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/checklist-document-reference-maps/" + result.getChecklistDocumentReferenceId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(
                                applicationName,
                                false,
                                ENTITY_NAME,
                                result.getChecklistDocumentReferenceId().toString()
                            )
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /checklist-document-reference-maps/:checklistDocumentReferenceId} : Updates an existing checklistDocumentReferenceMap.
     *
     * @param checklistDocumentReferenceId the id of the checklistDocumentReferenceMapDTO to save.
     * @param checklistDocumentReferenceMapDTO the checklistDocumentReferenceMapDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated checklistDocumentReferenceMapDTO,
     * or with status {@code 400 (Bad Request)} if the checklistDocumentReferenceMapDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the checklistDocumentReferenceMapDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/checklist-document-reference-maps/{checklistDocumentReferenceId}")
    public Mono<ResponseEntity<ChecklistDocumentReferenceMapDTO>> updateChecklistDocumentReferenceMap(
        @PathVariable(value = "checklistDocumentReferenceId", required = false) final Long checklistDocumentReferenceId,
        @RequestBody ChecklistDocumentReferenceMapDTO checklistDocumentReferenceMapDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to update ChecklistDocumentReferenceMap : {}, {}",
            checklistDocumentReferenceId,
            checklistDocumentReferenceMapDTO
        );
        if (checklistDocumentReferenceMapDTO.getChecklistDocumentReferenceId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(checklistDocumentReferenceId, checklistDocumentReferenceMapDTO.getChecklistDocumentReferenceId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return checklistDocumentReferenceMapRepository
            .existsById(checklistDocumentReferenceId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return checklistDocumentReferenceMapService
                    .update(checklistDocumentReferenceMapDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getChecklistDocumentReferenceId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /checklist-document-reference-maps/:checklistDocumentReferenceId} : Partial updates given fields of an existing checklistDocumentReferenceMap, field will ignore if it is null
     *
     * @param checklistDocumentReferenceId the id of the checklistDocumentReferenceMapDTO to save.
     * @param checklistDocumentReferenceMapDTO the checklistDocumentReferenceMapDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated checklistDocumentReferenceMapDTO,
     * or with status {@code 400 (Bad Request)} if the checklistDocumentReferenceMapDTO is not valid,
     * or with status {@code 404 (Not Found)} if the checklistDocumentReferenceMapDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the checklistDocumentReferenceMapDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/checklist-document-reference-maps/{checklistDocumentReferenceId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public Mono<ResponseEntity<ChecklistDocumentReferenceMapDTO>> partialUpdateChecklistDocumentReferenceMap(
        @PathVariable(value = "checklistDocumentReferenceId", required = false) final Long checklistDocumentReferenceId,
        @RequestBody ChecklistDocumentReferenceMapDTO checklistDocumentReferenceMapDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update ChecklistDocumentReferenceMap partially : {}, {}",
            checklistDocumentReferenceId,
            checklistDocumentReferenceMapDTO
        );
        if (checklistDocumentReferenceMapDTO.getChecklistDocumentReferenceId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(checklistDocumentReferenceId, checklistDocumentReferenceMapDTO.getChecklistDocumentReferenceId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return checklistDocumentReferenceMapRepository
            .existsById(checklistDocumentReferenceId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<ChecklistDocumentReferenceMapDTO> result = checklistDocumentReferenceMapService.partialUpdate(
                    checklistDocumentReferenceMapDTO
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
                                    res.getChecklistDocumentReferenceId().toString()
                                )
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /checklist-document-reference-maps} : get all the checklistDocumentReferenceMaps.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of checklistDocumentReferenceMaps in body.
     */
    @GetMapping("/checklist-document-reference-maps")
    public Mono<ResponseEntity<List<ChecklistDocumentReferenceMapDTO>>> getAllChecklistDocumentReferenceMaps(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of ChecklistDocumentReferenceMaps");
        return checklistDocumentReferenceMapService
            .countAll()
            .zipWith(checklistDocumentReferenceMapService.findAll(pageable).collectList())
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
     * {@code GET  /checklist-document-reference-maps/:id} : get the "id" checklistDocumentReferenceMap.
     *
     * @param id the id of the checklistDocumentReferenceMapDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the checklistDocumentReferenceMapDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/checklist-document-reference-maps/{id}")
    public Mono<ResponseEntity<ChecklistDocumentReferenceMapDTO>> getChecklistDocumentReferenceMap(@PathVariable Long id) {
        log.debug("REST request to get ChecklistDocumentReferenceMap : {}", id);
        Mono<ChecklistDocumentReferenceMapDTO> checklistDocumentReferenceMapDTO = checklistDocumentReferenceMapService.findOne(id);
        return ResponseUtil.wrapOrNotFound(checklistDocumentReferenceMapDTO);
    }

    /**
     * {@code DELETE  /checklist-document-reference-maps/:id} : delete the "id" checklistDocumentReferenceMap.
     *
     * @param id the id of the checklistDocumentReferenceMapDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/checklist-document-reference-maps/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteChecklistDocumentReferenceMap(@PathVariable Long id) {
        log.debug("REST request to delete ChecklistDocumentReferenceMap : {}", id);
        return checklistDocumentReferenceMapService
            .delete(id)
            .map(result ->
                ResponseEntity
                    .noContent()
                    .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                    .build()
            );
    }
}
