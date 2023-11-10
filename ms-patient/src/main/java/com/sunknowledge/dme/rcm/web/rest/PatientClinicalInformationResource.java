package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.PatientClinicalInformationRepository;
import com.sunknowledge.dme.rcm.service.PatientClinicalInformationService;
import com.sunknowledge.dme.rcm.service.dto.PatientClinicalInformationDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.PatientClinicalInformation}.
 */
@RestController
@RequestMapping("/api")
public class PatientClinicalInformationResource {

    private final Logger log = LoggerFactory.getLogger(PatientClinicalInformationResource.class);

    private static final String ENTITY_NAME = "patientPatientClinicalInformation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PatientClinicalInformationService patientClinicalInformationService;

    private final PatientClinicalInformationRepository patientClinicalInformationRepository;

    public PatientClinicalInformationResource(
        PatientClinicalInformationService patientClinicalInformationService,
        PatientClinicalInformationRepository patientClinicalInformationRepository
    ) {
        this.patientClinicalInformationService = patientClinicalInformationService;
        this.patientClinicalInformationRepository = patientClinicalInformationRepository;
    }

    /**
     * {@code POST  /patient-clinical-informations} : Create a new patientClinicalInformation.
     *
     * @param patientClinicalInformationDTO the patientClinicalInformationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new patientClinicalInformationDTO, or with status {@code 400 (Bad Request)} if the patientClinicalInformation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/patient-clinical-informations")
    public Mono<ResponseEntity<PatientClinicalInformationDTO>> createPatientClinicalInformation(
        @RequestBody PatientClinicalInformationDTO patientClinicalInformationDTO
    ) throws URISyntaxException {
        log.debug("REST request to save PatientClinicalInformation : {}", patientClinicalInformationDTO);
        if (patientClinicalInformationDTO.getPatientClinicalInformationId() != null) {
            throw new BadRequestAlertException("A new patientClinicalInformation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return patientClinicalInformationService
            .save(patientClinicalInformationDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/patient-clinical-informations/" + result.getPatientClinicalInformationId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(
                                applicationName,
                                false,
                                ENTITY_NAME,
                                result.getPatientClinicalInformationId().toString()
                            )
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /patient-clinical-informations/:patientClinicalInformationId} : Updates an existing patientClinicalInformation.
     *
     * @param patientClinicalInformationId the id of the patientClinicalInformationDTO to save.
     * @param patientClinicalInformationDTO the patientClinicalInformationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated patientClinicalInformationDTO,
     * or with status {@code 400 (Bad Request)} if the patientClinicalInformationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the patientClinicalInformationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/patient-clinical-informations/{patientClinicalInformationId}")
    public Mono<ResponseEntity<PatientClinicalInformationDTO>> updatePatientClinicalInformation(
        @PathVariable(value = "patientClinicalInformationId", required = false) final Long patientClinicalInformationId,
        @RequestBody PatientClinicalInformationDTO patientClinicalInformationDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to update PatientClinicalInformation : {}, {}",
            patientClinicalInformationId,
            patientClinicalInformationDTO
        );
        if (patientClinicalInformationDTO.getPatientClinicalInformationId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(patientClinicalInformationId, patientClinicalInformationDTO.getPatientClinicalInformationId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return patientClinicalInformationRepository
            .existsById(patientClinicalInformationId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return patientClinicalInformationService
                    .update(patientClinicalInformationDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getPatientClinicalInformationId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /patient-clinical-informations/:patientClinicalInformationId} : Partial updates given fields of an existing patientClinicalInformation, field will ignore if it is null
     *
     * @param patientClinicalInformationId the id of the patientClinicalInformationDTO to save.
     * @param patientClinicalInformationDTO the patientClinicalInformationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated patientClinicalInformationDTO,
     * or with status {@code 400 (Bad Request)} if the patientClinicalInformationDTO is not valid,
     * or with status {@code 404 (Not Found)} if the patientClinicalInformationDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the patientClinicalInformationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/patient-clinical-informations/{patientClinicalInformationId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public Mono<ResponseEntity<PatientClinicalInformationDTO>> partialUpdatePatientClinicalInformation(
        @PathVariable(value = "patientClinicalInformationId", required = false) final Long patientClinicalInformationId,
        @RequestBody PatientClinicalInformationDTO patientClinicalInformationDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update PatientClinicalInformation partially : {}, {}",
            patientClinicalInformationId,
            patientClinicalInformationDTO
        );
        if (patientClinicalInformationDTO.getPatientClinicalInformationId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(patientClinicalInformationId, patientClinicalInformationDTO.getPatientClinicalInformationId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return patientClinicalInformationRepository
            .existsById(patientClinicalInformationId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<PatientClinicalInformationDTO> result = patientClinicalInformationService.partialUpdate(patientClinicalInformationDTO);

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
                                    res.getPatientClinicalInformationId().toString()
                                )
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /patient-clinical-informations} : get all the patientClinicalInformations.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of patientClinicalInformations in body.
     */
    @GetMapping("/patient-clinical-informations")
    public Mono<ResponseEntity<List<PatientClinicalInformationDTO>>> getAllPatientClinicalInformations(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of PatientClinicalInformations");
        return patientClinicalInformationService
            .countAll()
            .zipWith(patientClinicalInformationService.findAll(pageable).collectList())
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
     * {@code GET  /patient-clinical-informations/:id} : get the "id" patientClinicalInformation.
     *
     * @param id the id of the patientClinicalInformationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the patientClinicalInformationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/patient-clinical-informations/{id}")
    public Mono<ResponseEntity<PatientClinicalInformationDTO>> getPatientClinicalInformation(@PathVariable Long id) {
        log.debug("REST request to get PatientClinicalInformation : {}", id);
        Mono<PatientClinicalInformationDTO> patientClinicalInformationDTO = patientClinicalInformationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(patientClinicalInformationDTO);
    }

    /**
     * {@code DELETE  /patient-clinical-informations/:id} : delete the "id" patientClinicalInformation.
     *
     * @param id the id of the patientClinicalInformationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/patient-clinical-informations/{id}")
    public Mono<ResponseEntity<Void>> deletePatientClinicalInformation(@PathVariable Long id) {
        log.debug("REST request to delete PatientClinicalInformation : {}", id);
        return patientClinicalInformationService
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
