package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.CoverageCriteriaFileMapRepository;
import com.sunknowledge.dme.rcm.service.CoverageCriteriaFileMapService;
import com.sunknowledge.dme.rcm.service.dto.CoverageCriteriaFileMapDTO;
import com.sunknowledge.dme.rcm.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.CoverageCriteriaFileMap}.
 */
@RestController
@RequestMapping("/api")
public class CoverageCriteriaFileMapResource {

    private final Logger log = LoggerFactory.getLogger(CoverageCriteriaFileMapResource.class);

    private static final String ENTITY_NAME = "salesorderCoverageCriteriaFileMap";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CoverageCriteriaFileMapService coverageCriteriaFileMapService;

    private final CoverageCriteriaFileMapRepository coverageCriteriaFileMapRepository;

    public CoverageCriteriaFileMapResource(
        CoverageCriteriaFileMapService coverageCriteriaFileMapService,
        CoverageCriteriaFileMapRepository coverageCriteriaFileMapRepository
    ) {
        this.coverageCriteriaFileMapService = coverageCriteriaFileMapService;
        this.coverageCriteriaFileMapRepository = coverageCriteriaFileMapRepository;
    }

    /**
     * {@code POST  /coverage-criteria-file-maps} : Create a new coverageCriteriaFileMap.
     *
     * @param coverageCriteriaFileMapDTO the coverageCriteriaFileMapDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new coverageCriteriaFileMapDTO, or with status {@code 400 (Bad Request)} if the coverageCriteriaFileMap has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/coverage-criteria-file-maps")
    public Mono<ResponseEntity<CoverageCriteriaFileMapDTO>> createCoverageCriteriaFileMap(
        @Valid @RequestBody CoverageCriteriaFileMapDTO coverageCriteriaFileMapDTO
    ) throws URISyntaxException {
        log.debug("REST request to save CoverageCriteriaFileMap : {}", coverageCriteriaFileMapDTO);
        if (coverageCriteriaFileMapDTO.getCoverageCriteriaFileMapId() != null) {
            throw new BadRequestAlertException("A new coverageCriteriaFileMap cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return coverageCriteriaFileMapService
            .save(coverageCriteriaFileMapDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/coverage-criteria-file-maps/" + result.getCoverageCriteriaFileMapId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(
                                applicationName,
                                false,
                                ENTITY_NAME,
                                result.getCoverageCriteriaFileMapId().toString()
                            )
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /coverage-criteria-file-maps/:coverageCriteriaFileMapId} : Updates an existing coverageCriteriaFileMap.
     *
     * @param coverageCriteriaFileMapId the id of the coverageCriteriaFileMapDTO to save.
     * @param coverageCriteriaFileMapDTO the coverageCriteriaFileMapDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated coverageCriteriaFileMapDTO,
     * or with status {@code 400 (Bad Request)} if the coverageCriteriaFileMapDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the coverageCriteriaFileMapDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/coverage-criteria-file-maps/{coverageCriteriaFileMapId}")
    public Mono<ResponseEntity<CoverageCriteriaFileMapDTO>> updateCoverageCriteriaFileMap(
        @PathVariable(value = "coverageCriteriaFileMapId", required = false) final Long coverageCriteriaFileMapId,
        @Valid @RequestBody CoverageCriteriaFileMapDTO coverageCriteriaFileMapDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CoverageCriteriaFileMap : {}, {}", coverageCriteriaFileMapId, coverageCriteriaFileMapDTO);
        if (coverageCriteriaFileMapDTO.getCoverageCriteriaFileMapId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(coverageCriteriaFileMapId, coverageCriteriaFileMapDTO.getCoverageCriteriaFileMapId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return coverageCriteriaFileMapRepository
            .existsById(coverageCriteriaFileMapId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return coverageCriteriaFileMapService
                    .update(coverageCriteriaFileMapDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getCoverageCriteriaFileMapId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /coverage-criteria-file-maps/:coverageCriteriaFileMapId} : Partial updates given fields of an existing coverageCriteriaFileMap, field will ignore if it is null
     *
     * @param coverageCriteriaFileMapId the id of the coverageCriteriaFileMapDTO to save.
     * @param coverageCriteriaFileMapDTO the coverageCriteriaFileMapDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated coverageCriteriaFileMapDTO,
     * or with status {@code 400 (Bad Request)} if the coverageCriteriaFileMapDTO is not valid,
     * or with status {@code 404 (Not Found)} if the coverageCriteriaFileMapDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the coverageCriteriaFileMapDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/coverage-criteria-file-maps/{coverageCriteriaFileMapId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public Mono<ResponseEntity<CoverageCriteriaFileMapDTO>> partialUpdateCoverageCriteriaFileMap(
        @PathVariable(value = "coverageCriteriaFileMapId", required = false) final Long coverageCriteriaFileMapId,
        @NotNull @RequestBody CoverageCriteriaFileMapDTO coverageCriteriaFileMapDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update CoverageCriteriaFileMap partially : {}, {}",
            coverageCriteriaFileMapId,
            coverageCriteriaFileMapDTO
        );
        if (coverageCriteriaFileMapDTO.getCoverageCriteriaFileMapId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(coverageCriteriaFileMapId, coverageCriteriaFileMapDTO.getCoverageCriteriaFileMapId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return coverageCriteriaFileMapRepository
            .existsById(coverageCriteriaFileMapId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<CoverageCriteriaFileMapDTO> result = coverageCriteriaFileMapService.partialUpdate(coverageCriteriaFileMapDTO);

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
                                    res.getCoverageCriteriaFileMapId().toString()
                                )
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /coverage-criteria-file-maps} : get all the coverageCriteriaFileMaps.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of coverageCriteriaFileMaps in body.
     */
    @GetMapping("/coverage-criteria-file-maps")
    public Mono<ResponseEntity<List<CoverageCriteriaFileMapDTO>>> getAllCoverageCriteriaFileMaps(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of CoverageCriteriaFileMaps");
        return coverageCriteriaFileMapService
            .countAll()
            .zipWith(coverageCriteriaFileMapService.findAll(pageable).collectList())
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
     * {@code GET  /coverage-criteria-file-maps/:id} : get the "id" coverageCriteriaFileMap.
     *
     * @param id the id of the coverageCriteriaFileMapDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the coverageCriteriaFileMapDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/coverage-criteria-file-maps/{id}")
    public Mono<ResponseEntity<CoverageCriteriaFileMapDTO>> getCoverageCriteriaFileMap(@PathVariable Long id) {
        log.debug("REST request to get CoverageCriteriaFileMap : {}", id);
        Mono<CoverageCriteriaFileMapDTO> coverageCriteriaFileMapDTO = coverageCriteriaFileMapService.findOne(id);
        return ResponseUtil.wrapOrNotFound(coverageCriteriaFileMapDTO);
    }

    /**
     * {@code DELETE  /coverage-criteria-file-maps/:id} : delete the "id" coverageCriteriaFileMap.
     *
     * @param id the id of the coverageCriteriaFileMapDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/coverage-criteria-file-maps/{id}")
    public Mono<ResponseEntity<Void>> deleteCoverageCriteriaFileMap(@PathVariable Long id) {
        log.debug("REST request to delete CoverageCriteriaFileMap : {}", id);
        return coverageCriteriaFileMapService
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
