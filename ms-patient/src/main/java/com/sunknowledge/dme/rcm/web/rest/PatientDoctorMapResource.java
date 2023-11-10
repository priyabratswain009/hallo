package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.PatientDoctorMapRepository;
import com.sunknowledge.dme.rcm.service.PatientDoctorMapService;
import com.sunknowledge.dme.rcm.service.dto.PatientDoctorMapDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.PatientDoctorMap}.
 */
@RestController
@RequestMapping("/api")
public class PatientDoctorMapResource {

    private final Logger log = LoggerFactory.getLogger(PatientDoctorMapResource.class);

    private static final String ENTITY_NAME = "patientPatientDoctorMap";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PatientDoctorMapService patientDoctorMapService;

    private final PatientDoctorMapRepository patientDoctorMapRepository;

    public PatientDoctorMapResource(
        PatientDoctorMapService patientDoctorMapService,
        PatientDoctorMapRepository patientDoctorMapRepository
    ) {
        this.patientDoctorMapService = patientDoctorMapService;
        this.patientDoctorMapRepository = patientDoctorMapRepository;
    }

    /**
     * {@code POST  /patient-doctor-maps} : Create a new patientDoctorMap.
     *
     * @param patientDoctorMapDTO the patientDoctorMapDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new patientDoctorMapDTO, or with status {@code 400 (Bad Request)} if the patientDoctorMap has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/patient-doctor-maps")
    public Mono<ResponseEntity<PatientDoctorMapDTO>> createPatientDoctorMap(@RequestBody PatientDoctorMapDTO patientDoctorMapDTO)
        throws URISyntaxException {
        log.debug("REST request to save PatientDoctorMap : {}", patientDoctorMapDTO);
        if (patientDoctorMapDTO.getPatientDoctorMapId() != null) {
            throw new BadRequestAlertException("A new patientDoctorMap cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return patientDoctorMapService
            .save(patientDoctorMapDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/patient-doctor-maps/" + result.getPatientDoctorMapId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(
                                applicationName,
                                false,
                                ENTITY_NAME,
                                result.getPatientDoctorMapId().toString()
                            )
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /patient-doctor-maps/:patientDoctorMapId} : Updates an existing patientDoctorMap.
     *
     * @param patientDoctorMapId the id of the patientDoctorMapDTO to save.
     * @param patientDoctorMapDTO the patientDoctorMapDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated patientDoctorMapDTO,
     * or with status {@code 400 (Bad Request)} if the patientDoctorMapDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the patientDoctorMapDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/patient-doctor-maps/{patientDoctorMapId}")
    public Mono<ResponseEntity<PatientDoctorMapDTO>> updatePatientDoctorMap(
        @PathVariable(value = "patientDoctorMapId", required = false) final Long patientDoctorMapId,
        @RequestBody PatientDoctorMapDTO patientDoctorMapDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PatientDoctorMap : {}, {}", patientDoctorMapId, patientDoctorMapDTO);
        if (patientDoctorMapDTO.getPatientDoctorMapId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(patientDoctorMapId, patientDoctorMapDTO.getPatientDoctorMapId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return patientDoctorMapRepository
            .existsById(patientDoctorMapId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return patientDoctorMapService
                    .update(patientDoctorMapDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getPatientDoctorMapId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /patient-doctor-maps/:patientDoctorMapId} : Partial updates given fields of an existing patientDoctorMap, field will ignore if it is null
     *
     * @param patientDoctorMapId the id of the patientDoctorMapDTO to save.
     * @param patientDoctorMapDTO the patientDoctorMapDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated patientDoctorMapDTO,
     * or with status {@code 400 (Bad Request)} if the patientDoctorMapDTO is not valid,
     * or with status {@code 404 (Not Found)} if the patientDoctorMapDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the patientDoctorMapDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/patient-doctor-maps/{patientDoctorMapId}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<PatientDoctorMapDTO>> partialUpdatePatientDoctorMap(
        @PathVariable(value = "patientDoctorMapId", required = false) final Long patientDoctorMapId,
        @RequestBody PatientDoctorMapDTO patientDoctorMapDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PatientDoctorMap partially : {}, {}", patientDoctorMapId, patientDoctorMapDTO);
        if (patientDoctorMapDTO.getPatientDoctorMapId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(patientDoctorMapId, patientDoctorMapDTO.getPatientDoctorMapId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return patientDoctorMapRepository
            .existsById(patientDoctorMapId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<PatientDoctorMapDTO> result = patientDoctorMapService.partialUpdate(patientDoctorMapDTO);

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
                                    res.getPatientDoctorMapId().toString()
                                )
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /patient-doctor-maps} : get all the patientDoctorMaps.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of patientDoctorMaps in body.
     */
    @GetMapping("/patient-doctor-maps")
    public Mono<ResponseEntity<List<PatientDoctorMapDTO>>> getAllPatientDoctorMaps(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of PatientDoctorMaps");
        return patientDoctorMapService
            .countAll()
            .zipWith(patientDoctorMapService.findAll(pageable).collectList())
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
     * {@code GET  /patient-doctor-maps/:id} : get the "id" patientDoctorMap.
     *
     * @param id the id of the patientDoctorMapDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the patientDoctorMapDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/patient-doctor-maps/{id}")
    public Mono<ResponseEntity<PatientDoctorMapDTO>> getPatientDoctorMap(@PathVariable Long id) {
        log.debug("REST request to get PatientDoctorMap : {}", id);
        Mono<PatientDoctorMapDTO> patientDoctorMapDTO = patientDoctorMapService.findOne(id);
        return ResponseUtil.wrapOrNotFound(patientDoctorMapDTO);
    }

    /**
     * {@code DELETE  /patient-doctor-maps/:id} : delete the "id" patientDoctorMap.
     *
     * @param id the id of the patientDoctorMapDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/patient-doctor-maps/{id}")
    public Mono<ResponseEntity<Void>> deletePatientDoctorMap(@PathVariable Long id) {
        log.debug("REST request to delete PatientDoctorMap : {}", id);
        return patientDoctorMapService
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
