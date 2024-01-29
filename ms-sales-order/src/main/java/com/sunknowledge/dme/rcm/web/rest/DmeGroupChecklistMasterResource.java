package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.DmeGroupChecklistMasterRepository;
import com.sunknowledge.dme.rcm.service.DmeGroupChecklistMasterService;
import com.sunknowledge.dme.rcm.service.dto.DmeGroupChecklistMasterDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.DmeGroupChecklistMaster}.
 */
@RestController
@RequestMapping("/api")
public class DmeGroupChecklistMasterResource {

    private final Logger log = LoggerFactory.getLogger(DmeGroupChecklistMasterResource.class);

    private static final String ENTITY_NAME = "salesorderDmeGroupChecklistMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DmeGroupChecklistMasterService dmeGroupChecklistMasterService;

    private final DmeGroupChecklistMasterRepository dmeGroupChecklistMasterRepository;

    public DmeGroupChecklistMasterResource(
        DmeGroupChecklistMasterService dmeGroupChecklistMasterService,
        DmeGroupChecklistMasterRepository dmeGroupChecklistMasterRepository
    ) {
        this.dmeGroupChecklistMasterService = dmeGroupChecklistMasterService;
        this.dmeGroupChecklistMasterRepository = dmeGroupChecklistMasterRepository;
    }

    /**
     * {@code POST  /dme-group-checklist-masters} : Create a new dmeGroupChecklistMaster.
     *
     * @param dmeGroupChecklistMasterDTO the dmeGroupChecklistMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dmeGroupChecklistMasterDTO, or with status {@code 400 (Bad Request)} if the dmeGroupChecklistMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dme-group-checklist-masters")
    public Mono<ResponseEntity<DmeGroupChecklistMasterDTO>> createDmeGroupChecklistMaster(
        @Valid @RequestBody DmeGroupChecklistMasterDTO dmeGroupChecklistMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to save DmeGroupChecklistMaster : {}", dmeGroupChecklistMasterDTO);
        if (dmeGroupChecklistMasterDTO.getDmeGroupChecklistId() != null) {
            throw new BadRequestAlertException("A new dmeGroupChecklistMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return dmeGroupChecklistMasterService
            .save(dmeGroupChecklistMasterDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/dme-group-checklist-masters/" + result.getDmeGroupChecklistId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(
                                applicationName,
                                false,
                                ENTITY_NAME,
                                result.getDmeGroupChecklistId().toString()
                            )
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /dme-group-checklist-masters/:dmeGroupChecklistId} : Updates an existing dmeGroupChecklistMaster.
     *
     * @param dmeGroupChecklistId the id of the dmeGroupChecklistMasterDTO to save.
     * @param dmeGroupChecklistMasterDTO the dmeGroupChecklistMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dmeGroupChecklistMasterDTO,
     * or with status {@code 400 (Bad Request)} if the dmeGroupChecklistMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dmeGroupChecklistMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dme-group-checklist-masters/{dmeGroupChecklistId}")
    public Mono<ResponseEntity<DmeGroupChecklistMasterDTO>> updateDmeGroupChecklistMaster(
        @PathVariable(value = "dmeGroupChecklistId", required = false) final Long dmeGroupChecklistId,
        @Valid @RequestBody DmeGroupChecklistMasterDTO dmeGroupChecklistMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DmeGroupChecklistMaster : {}, {}", dmeGroupChecklistId, dmeGroupChecklistMasterDTO);
        if (dmeGroupChecklistMasterDTO.getDmeGroupChecklistId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(dmeGroupChecklistId, dmeGroupChecklistMasterDTO.getDmeGroupChecklistId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return dmeGroupChecklistMasterRepository
            .existsById(dmeGroupChecklistId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return dmeGroupChecklistMasterService
                    .update(dmeGroupChecklistMasterDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getDmeGroupChecklistId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /dme-group-checklist-masters/:dmeGroupChecklistId} : Partial updates given fields of an existing dmeGroupChecklistMaster, field will ignore if it is null
     *
     * @param dmeGroupChecklistId the id of the dmeGroupChecklistMasterDTO to save.
     * @param dmeGroupChecklistMasterDTO the dmeGroupChecklistMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dmeGroupChecklistMasterDTO,
     * or with status {@code 400 (Bad Request)} if the dmeGroupChecklistMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the dmeGroupChecklistMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the dmeGroupChecklistMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/dme-group-checklist-masters/{dmeGroupChecklistId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public Mono<ResponseEntity<DmeGroupChecklistMasterDTO>> partialUpdateDmeGroupChecklistMaster(
        @PathVariable(value = "dmeGroupChecklistId", required = false) final Long dmeGroupChecklistId,
        @NotNull @RequestBody DmeGroupChecklistMasterDTO dmeGroupChecklistMasterDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update DmeGroupChecklistMaster partially : {}, {}",
            dmeGroupChecklistId,
            dmeGroupChecklistMasterDTO
        );
        if (dmeGroupChecklistMasterDTO.getDmeGroupChecklistId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(dmeGroupChecklistId, dmeGroupChecklistMasterDTO.getDmeGroupChecklistId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return dmeGroupChecklistMasterRepository
            .existsById(dmeGroupChecklistId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<DmeGroupChecklistMasterDTO> result = dmeGroupChecklistMasterService.partialUpdate(dmeGroupChecklistMasterDTO);

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
                                    res.getDmeGroupChecklistId().toString()
                                )
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /dme-group-checklist-masters} : get all the dmeGroupChecklistMasters.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dmeGroupChecklistMasters in body.
     */
    @GetMapping("/dme-group-checklist-masters")
    public Mono<ResponseEntity<List<DmeGroupChecklistMasterDTO>>> getAllDmeGroupChecklistMasters(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of DmeGroupChecklistMasters");
        return dmeGroupChecklistMasterService
            .countAll()
            .zipWith(dmeGroupChecklistMasterService.findAll(pageable).collectList())
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
     * {@code GET  /dme-group-checklist-masters/:id} : get the "id" dmeGroupChecklistMaster.
     *
     * @param id the id of the dmeGroupChecklistMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dmeGroupChecklistMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dme-group-checklist-masters/{id}")
    public Mono<ResponseEntity<DmeGroupChecklistMasterDTO>> getDmeGroupChecklistMaster(@PathVariable Long id) {
        log.debug("REST request to get DmeGroupChecklistMaster : {}", id);
        Mono<DmeGroupChecklistMasterDTO> dmeGroupChecklistMasterDTO = dmeGroupChecklistMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dmeGroupChecklistMasterDTO);
    }

    /**
     * {@code DELETE  /dme-group-checklist-masters/:id} : delete the "id" dmeGroupChecklistMaster.
     *
     * @param id the id of the dmeGroupChecklistMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dme-group-checklist-masters/{id}")
    public Mono<ResponseEntity<Void>> deleteDmeGroupChecklistMaster(@PathVariable Long id) {
        log.debug("REST request to delete DmeGroupChecklistMaster : {}", id);
        return dmeGroupChecklistMasterService
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
