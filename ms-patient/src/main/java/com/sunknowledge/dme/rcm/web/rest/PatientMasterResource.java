package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.PatientMasterRepository;
import com.sunknowledge.dme.rcm.service.PatientMasterService;
import com.sunknowledge.dme.rcm.service.dto.PatientMasterDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.PatientMaster}.
 */
@RestController
@RequestMapping("/api")
public class PatientMasterResource {

    private final Logger log = LoggerFactory.getLogger(PatientMasterResource.class);

    private static final String ENTITY_NAME = "patientPatientMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PatientMasterService patientMasterService;

    private final PatientMasterRepository patientMasterRepository;

    public PatientMasterResource(PatientMasterService patientMasterService, PatientMasterRepository patientMasterRepository) {
        this.patientMasterService = patientMasterService;
        this.patientMasterRepository = patientMasterRepository;
    }

    /**
     * {@code POST  /patient-masters} : Create a new patientMaster.
     *
     * @param patientMasterDTO the patientMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new patientMasterDTO, or with status {@code 400 (Bad Request)} if the patientMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/patient-masters")
    public Mono<ResponseEntity<PatientMasterDTO>> createPatientMaster(@RequestBody PatientMasterDTO patientMasterDTO)
        throws URISyntaxException {
        log.debug("REST request to save PatientMaster : {}", patientMasterDTO);
        if (patientMasterDTO.getPatientId() != null) {
            throw new BadRequestAlertException("A new patientMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return patientMasterService
            .save(patientMasterDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/patient-masters/" + result.getPatientId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getPatientId().toString())
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /patient-masters/:patientId} : Updates an existing patientMaster.
     *
     * @param patientId the id of the patientMasterDTO to save.
     * @param patientMasterDTO the patientMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated patientMasterDTO,
     * or with status {@code 400 (Bad Request)} if the patientMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the patientMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/patient-masters/{patientId}")
    public Mono<ResponseEntity<PatientMasterDTO>> updatePatientMaster(
        @PathVariable(value = "patientId", required = false) final Long patientId,
        @RequestBody PatientMasterDTO patientMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PatientMaster : {}, {}", patientId, patientMasterDTO);
        if (patientMasterDTO.getPatientId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(patientId, patientMasterDTO.getPatientId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return patientMasterRepository
            .existsById(patientId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return patientMasterService
                    .update(patientMasterDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, result.getPatientId().toString())
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /patient-masters/:patientId} : Partial updates given fields of an existing patientMaster, field will ignore if it is null
     *
     * @param patientId the id of the patientMasterDTO to save.
     * @param patientMasterDTO the patientMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated patientMasterDTO,
     * or with status {@code 400 (Bad Request)} if the patientMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the patientMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the patientMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/patient-masters/{patientId}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<PatientMasterDTO>> partialUpdatePatientMaster(
        @PathVariable(value = "patientId", required = false) final Long patientId,
        @RequestBody PatientMasterDTO patientMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PatientMaster partially : {}, {}", patientId, patientMasterDTO);
        if (patientMasterDTO.getPatientId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(patientId, patientMasterDTO.getPatientId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return patientMasterRepository
            .existsById(patientId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<PatientMasterDTO> result = patientMasterService.partialUpdate(patientMasterDTO);

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(res ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, res.getPatientId().toString()))
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /patient-masters} : get all the patientMasters.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of patientMasters in body.
     */
    @GetMapping("/patient-masters")
    public Mono<ResponseEntity<List<PatientMasterDTO>>> getAllPatientMasters(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of PatientMasters");
        return patientMasterService
            .countAll()
            .zipWith(patientMasterService.findAll(pageable).collectList())
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
     * {@code GET  /patient-masters/:id} : get the "id" patientMaster.
     *
     * @param id the id of the patientMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the patientMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/patient-masters/{id}")
    public Mono<ResponseEntity<PatientMasterDTO>> getPatientMaster(@PathVariable Long id) {
        log.debug("REST request to get PatientMaster : {}", id);
        Mono<PatientMasterDTO> patientMasterDTO = patientMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(patientMasterDTO);
    }

    /**
     * {@code DELETE  /patient-masters/:id} : delete the "id" patientMaster.
     *
     * @param id the id of the patientMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/patient-masters/{id}")
    public Mono<ResponseEntity<Void>> deletePatientMaster(@PathVariable Long id) {
        log.debug("REST request to delete PatientMaster : {}", id);
        return patientMasterService
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
