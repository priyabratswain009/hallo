package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.PatientInsuranceRepository;
import com.sunknowledge.dme.rcm.service.PatientInsuranceService;
import com.sunknowledge.dme.rcm.service.dto.PatientInsuranceDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.PatientInsurance}.
 */
@RestController
@RequestMapping("/api")
public class PatientInsuranceResource {

    private final Logger log = LoggerFactory.getLogger(PatientInsuranceResource.class);

    private static final String ENTITY_NAME = "patientPatientInsurance";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PatientInsuranceService patientInsuranceService;

    private final PatientInsuranceRepository patientInsuranceRepository;

    public PatientInsuranceResource(
        PatientInsuranceService patientInsuranceService,
        PatientInsuranceRepository patientInsuranceRepository
    ) {
        this.patientInsuranceService = patientInsuranceService;
        this.patientInsuranceRepository = patientInsuranceRepository;
    }

    /**
     * {@code POST  /patient-insurances} : Create a new patientInsurance.
     *
     * @param patientInsuranceDTO the patientInsuranceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new patientInsuranceDTO, or with status {@code 400 (Bad Request)} if the patientInsurance has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/patient-insurances")
    public Mono<ResponseEntity<PatientInsuranceDTO>> createPatientInsurance(@RequestBody PatientInsuranceDTO patientInsuranceDTO)
        throws URISyntaxException {
        log.debug("REST request to save PatientInsurance : {}", patientInsuranceDTO);
        if (patientInsuranceDTO.getPatientInsuranceId() != null) {
            throw new BadRequestAlertException("A new patientInsurance cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return patientInsuranceService
            .save(patientInsuranceDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/patient-insurances/" + result.getPatientInsuranceId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(
                                applicationName,
                                false,
                                ENTITY_NAME,
                                result.getPatientInsuranceId().toString()
                            )
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /patient-insurances/:patientInsuranceId} : Updates an existing patientInsurance.
     *
     * @param patientInsuranceId the id of the patientInsuranceDTO to save.
     * @param patientInsuranceDTO the patientInsuranceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated patientInsuranceDTO,
     * or with status {@code 400 (Bad Request)} if the patientInsuranceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the patientInsuranceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/patient-insurances/{patientInsuranceId}")
    public Mono<ResponseEntity<PatientInsuranceDTO>> updatePatientInsurance(
        @PathVariable(value = "patientInsuranceId", required = false) final Long patientInsuranceId,
        @RequestBody PatientInsuranceDTO patientInsuranceDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PatientInsurance : {}, {}", patientInsuranceId, patientInsuranceDTO);
        if (patientInsuranceDTO.getPatientInsuranceId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(patientInsuranceId, patientInsuranceDTO.getPatientInsuranceId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return patientInsuranceRepository
            .existsById(patientInsuranceId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return patientInsuranceService
                    .update(patientInsuranceDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getPatientInsuranceId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /patient-insurances/:patientInsuranceId} : Partial updates given fields of an existing patientInsurance, field will ignore if it is null
     *
     * @param patientInsuranceId the id of the patientInsuranceDTO to save.
     * @param patientInsuranceDTO the patientInsuranceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated patientInsuranceDTO,
     * or with status {@code 400 (Bad Request)} if the patientInsuranceDTO is not valid,
     * or with status {@code 404 (Not Found)} if the patientInsuranceDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the patientInsuranceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/patient-insurances/{patientInsuranceId}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<PatientInsuranceDTO>> partialUpdatePatientInsurance(
        @PathVariable(value = "patientInsuranceId", required = false) final Long patientInsuranceId,
        @RequestBody PatientInsuranceDTO patientInsuranceDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PatientInsurance partially : {}, {}", patientInsuranceId, patientInsuranceDTO);
        if (patientInsuranceDTO.getPatientInsuranceId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(patientInsuranceId, patientInsuranceDTO.getPatientInsuranceId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return patientInsuranceRepository
            .existsById(patientInsuranceId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<PatientInsuranceDTO> result = patientInsuranceService.partialUpdate(patientInsuranceDTO);

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
                                    res.getPatientInsuranceId().toString()
                                )
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /patient-insurances} : get all the patientInsurances.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of patientInsurances in body.
     */
    @GetMapping("/patient-insurances")
    public Mono<ResponseEntity<List<PatientInsuranceDTO>>> getAllPatientInsurances(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of PatientInsurances");
        return patientInsuranceService
            .countAll()
            .zipWith(patientInsuranceService.findAll(pageable).collectList())
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
     * {@code GET  /patient-insurances/:id} : get the "id" patientInsurance.
     *
     * @param id the id of the patientInsuranceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the patientInsuranceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/patient-insurances/{id}")
    public Mono<ResponseEntity<PatientInsuranceDTO>> getPatientInsurance(@PathVariable Long id) {
        log.debug("REST request to get PatientInsurance : {}", id);
        Mono<PatientInsuranceDTO> patientInsuranceDTO = patientInsuranceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(patientInsuranceDTO);
    }

    /**
     * {@code DELETE  /patient-insurances/:id} : delete the "id" patientInsurance.
     *
     * @param id the id of the patientInsuranceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/patient-insurances/{id}")
    public Mono<ResponseEntity<Void>> deletePatientInsurance(@PathVariable Long id) {
        log.debug("REST request to delete PatientInsurance : {}", id);
        return patientInsuranceService
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
