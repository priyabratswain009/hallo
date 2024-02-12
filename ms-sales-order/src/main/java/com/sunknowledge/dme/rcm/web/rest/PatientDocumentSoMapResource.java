package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.PatientDocumentSoMapRepository;
import com.sunknowledge.dme.rcm.service.PatientDocumentSoMapService;
import com.sunknowledge.dme.rcm.service.dto.PatientDocumentSoMapDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.PatientDocumentSoMap}.
 */
@RestController
@RequestMapping("/api")
public class PatientDocumentSoMapResource {

    private final Logger log = LoggerFactory.getLogger(PatientDocumentSoMapResource.class);

    private static final String ENTITY_NAME = "salesorderPatientDocumentSoMap";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PatientDocumentSoMapService patientDocumentSoMapService;

    private final PatientDocumentSoMapRepository patientDocumentSoMapRepository;

    public PatientDocumentSoMapResource(
        PatientDocumentSoMapService patientDocumentSoMapService,
        PatientDocumentSoMapRepository patientDocumentSoMapRepository
    ) {
        this.patientDocumentSoMapService = patientDocumentSoMapService;
        this.patientDocumentSoMapRepository = patientDocumentSoMapRepository;
    }

    /**
     * {@code POST  /patient-document-so-maps} : Create a new patientDocumentSoMap.
     *
     * @param patientDocumentSoMapDTO the patientDocumentSoMapDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new patientDocumentSoMapDTO, or with status {@code 400 (Bad Request)} if the patientDocumentSoMap has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/patient-document-so-maps")
    public Mono<ResponseEntity<PatientDocumentSoMapDTO>> createPatientDocumentSoMap(
        @RequestBody PatientDocumentSoMapDTO patientDocumentSoMapDTO
    ) throws URISyntaxException {
        log.debug("REST request to save PatientDocumentSoMap : {}", patientDocumentSoMapDTO);
        if (patientDocumentSoMapDTO.getPatientDocumentSoMapId() != null) {
            throw new BadRequestAlertException("A new patientDocumentSoMap cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return patientDocumentSoMapService
            .save(patientDocumentSoMapDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/patient-document-so-maps/" + result.getPatientDocumentSoMapId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(
                                applicationName,
                                false,
                                ENTITY_NAME,
                                result.getPatientDocumentSoMapId().toString()
                            )
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /patient-document-so-maps/:patientDocumentSoMapId} : Updates an existing patientDocumentSoMap.
     *
     * @param patientDocumentSoMapId the id of the patientDocumentSoMapDTO to save.
     * @param patientDocumentSoMapDTO the patientDocumentSoMapDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated patientDocumentSoMapDTO,
     * or with status {@code 400 (Bad Request)} if the patientDocumentSoMapDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the patientDocumentSoMapDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/patient-document-so-maps/{patientDocumentSoMapId}")
    public Mono<ResponseEntity<PatientDocumentSoMapDTO>> updatePatientDocumentSoMap(
        @PathVariable(value = "patientDocumentSoMapId", required = false) final Long patientDocumentSoMapId,
        @RequestBody PatientDocumentSoMapDTO patientDocumentSoMapDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PatientDocumentSoMap : {}, {}", patientDocumentSoMapId, patientDocumentSoMapDTO);
        if (patientDocumentSoMapDTO.getPatientDocumentSoMapId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(patientDocumentSoMapId, patientDocumentSoMapDTO.getPatientDocumentSoMapId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return patientDocumentSoMapRepository
            .existsById(patientDocumentSoMapId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return patientDocumentSoMapService
                    .update(patientDocumentSoMapDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getPatientDocumentSoMapId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /patient-document-so-maps/:patientDocumentSoMapId} : Partial updates given fields of an existing patientDocumentSoMap, field will ignore if it is null
     *
     * @param patientDocumentSoMapId the id of the patientDocumentSoMapDTO to save.
     * @param patientDocumentSoMapDTO the patientDocumentSoMapDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated patientDocumentSoMapDTO,
     * or with status {@code 400 (Bad Request)} if the patientDocumentSoMapDTO is not valid,
     * or with status {@code 404 (Not Found)} if the patientDocumentSoMapDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the patientDocumentSoMapDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/patient-document-so-maps/{patientDocumentSoMapId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public Mono<ResponseEntity<PatientDocumentSoMapDTO>> partialUpdatePatientDocumentSoMap(
        @PathVariable(value = "patientDocumentSoMapId", required = false) final Long patientDocumentSoMapId,
        @RequestBody PatientDocumentSoMapDTO patientDocumentSoMapDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update PatientDocumentSoMap partially : {}, {}",
            patientDocumentSoMapId,
            patientDocumentSoMapDTO
        );
        if (patientDocumentSoMapDTO.getPatientDocumentSoMapId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(patientDocumentSoMapId, patientDocumentSoMapDTO.getPatientDocumentSoMapId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return patientDocumentSoMapRepository
            .existsById(patientDocumentSoMapId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<PatientDocumentSoMapDTO> result = patientDocumentSoMapService.partialUpdate(patientDocumentSoMapDTO);

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
                                    res.getPatientDocumentSoMapId().toString()
                                )
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /patient-document-so-maps} : get all the patientDocumentSoMaps.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of patientDocumentSoMaps in body.
     */
    @GetMapping("/patient-document-so-maps")
    public Mono<ResponseEntity<List<PatientDocumentSoMapDTO>>> getAllPatientDocumentSoMaps(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of PatientDocumentSoMaps");
        return patientDocumentSoMapService
            .countAll()
            .zipWith(patientDocumentSoMapService.findAll(pageable).collectList())
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
     * {@code GET  /patient-document-so-maps/:id} : get the "id" patientDocumentSoMap.
     *
     * @param id the id of the patientDocumentSoMapDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the patientDocumentSoMapDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/patient-document-so-maps/{id}")
    public Mono<ResponseEntity<PatientDocumentSoMapDTO>> getPatientDocumentSoMap(@PathVariable Long id) {
        log.debug("REST request to get PatientDocumentSoMap : {}", id);
        Mono<PatientDocumentSoMapDTO> patientDocumentSoMapDTO = patientDocumentSoMapService.findOne(id);
        return ResponseUtil.wrapOrNotFound(patientDocumentSoMapDTO);
    }

    /**
     * {@code DELETE  /patient-document-so-maps/:id} : delete the "id" patientDocumentSoMap.
     *
     * @param id the id of the patientDocumentSoMapDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/patient-document-so-maps/{id}")
    public Mono<ResponseEntity<Void>> deletePatientDocumentSoMap(@PathVariable Long id) {
        log.debug("REST request to delete PatientDocumentSoMap : {}", id);
        return patientDocumentSoMapService
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
