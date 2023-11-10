package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.PatientDiagnosisRepository;
import com.sunknowledge.dme.rcm.service.PatientDiagnosisService;
import com.sunknowledge.dme.rcm.service.dto.PatientDiagnosisDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.PatientDiagnosis}.
 */
@RestController
@RequestMapping("/api")
public class PatientDiagnosisResource {

    private final Logger log = LoggerFactory.getLogger(PatientDiagnosisResource.class);

    private static final String ENTITY_NAME = "patientPatientDiagnosis";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PatientDiagnosisService patientDiagnosisService;

    private final PatientDiagnosisRepository patientDiagnosisRepository;

    public PatientDiagnosisResource(
        PatientDiagnosisService patientDiagnosisService,
        PatientDiagnosisRepository patientDiagnosisRepository
    ) {
        this.patientDiagnosisService = patientDiagnosisService;
        this.patientDiagnosisRepository = patientDiagnosisRepository;
    }

    /**
     * {@code POST  /patient-diagnoses} : Create a new patientDiagnosis.
     *
     * @param patientDiagnosisDTO the patientDiagnosisDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new patientDiagnosisDTO, or with status {@code 400 (Bad Request)} if the patientDiagnosis has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/patient-diagnoses")
    public Mono<ResponseEntity<PatientDiagnosisDTO>> createPatientDiagnosis(@RequestBody PatientDiagnosisDTO patientDiagnosisDTO)
        throws URISyntaxException {
        log.debug("REST request to save PatientDiagnosis : {}", patientDiagnosisDTO);
        if (patientDiagnosisDTO.getPatientDiagnosisId() != null) {
            throw new BadRequestAlertException("A new patientDiagnosis cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return patientDiagnosisService
            .save(patientDiagnosisDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/patient-diagnoses/" + result.getPatientDiagnosisId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(
                                applicationName,
                                false,
                                ENTITY_NAME,
                                result.getPatientDiagnosisId().toString()
                            )
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /patient-diagnoses/:patientDiagnosisId} : Updates an existing patientDiagnosis.
     *
     * @param patientDiagnosisId the id of the patientDiagnosisDTO to save.
     * @param patientDiagnosisDTO the patientDiagnosisDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated patientDiagnosisDTO,
     * or with status {@code 400 (Bad Request)} if the patientDiagnosisDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the patientDiagnosisDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/patient-diagnoses/{patientDiagnosisId}")
    public Mono<ResponseEntity<PatientDiagnosisDTO>> updatePatientDiagnosis(
        @PathVariable(value = "patientDiagnosisId", required = false) final Long patientDiagnosisId,
        @RequestBody PatientDiagnosisDTO patientDiagnosisDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PatientDiagnosis : {}, {}", patientDiagnosisId, patientDiagnosisDTO);
        if (patientDiagnosisDTO.getPatientDiagnosisId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(patientDiagnosisId, patientDiagnosisDTO.getPatientDiagnosisId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return patientDiagnosisRepository
            .existsById(patientDiagnosisId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return patientDiagnosisService
                    .update(patientDiagnosisDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getPatientDiagnosisId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /patient-diagnoses/:patientDiagnosisId} : Partial updates given fields of an existing patientDiagnosis, field will ignore if it is null
     *
     * @param patientDiagnosisId the id of the patientDiagnosisDTO to save.
     * @param patientDiagnosisDTO the patientDiagnosisDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated patientDiagnosisDTO,
     * or with status {@code 400 (Bad Request)} if the patientDiagnosisDTO is not valid,
     * or with status {@code 404 (Not Found)} if the patientDiagnosisDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the patientDiagnosisDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/patient-diagnoses/{patientDiagnosisId}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<PatientDiagnosisDTO>> partialUpdatePatientDiagnosis(
        @PathVariable(value = "patientDiagnosisId", required = false) final Long patientDiagnosisId,
        @RequestBody PatientDiagnosisDTO patientDiagnosisDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PatientDiagnosis partially : {}, {}", patientDiagnosisId, patientDiagnosisDTO);
        if (patientDiagnosisDTO.getPatientDiagnosisId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(patientDiagnosisId, patientDiagnosisDTO.getPatientDiagnosisId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return patientDiagnosisRepository
            .existsById(patientDiagnosisId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<PatientDiagnosisDTO> result = patientDiagnosisService.partialUpdate(patientDiagnosisDTO);

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
                                    res.getPatientDiagnosisId().toString()
                                )
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /patient-diagnoses} : get all the patientDiagnoses.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of patientDiagnoses in body.
     */
    @GetMapping("/patient-diagnoses")
    public Mono<ResponseEntity<List<PatientDiagnosisDTO>>> getAllPatientDiagnoses(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of PatientDiagnoses");
        return patientDiagnosisService
            .countAll()
            .zipWith(patientDiagnosisService.findAll(pageable).collectList())
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
     * {@code GET  /patient-diagnoses/:id} : get the "id" patientDiagnosis.
     *
     * @param id the id of the patientDiagnosisDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the patientDiagnosisDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/patient-diagnoses/{id}")
    public Mono<ResponseEntity<PatientDiagnosisDTO>> getPatientDiagnosis(@PathVariable Long id) {
        log.debug("REST request to get PatientDiagnosis : {}", id);
        Mono<PatientDiagnosisDTO> patientDiagnosisDTO = patientDiagnosisService.findOne(id);
        return ResponseUtil.wrapOrNotFound(patientDiagnosisDTO);
    }

    /**
     * {@code DELETE  /patient-diagnoses/:id} : delete the "id" patientDiagnosis.
     *
     * @param id the id of the patientDiagnosisDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/patient-diagnoses/{id}")
    public Mono<ResponseEntity<Void>> deletePatientDiagnosis(@PathVariable Long id) {
        log.debug("REST request to delete PatientDiagnosis : {}", id);
        return patientDiagnosisService
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
