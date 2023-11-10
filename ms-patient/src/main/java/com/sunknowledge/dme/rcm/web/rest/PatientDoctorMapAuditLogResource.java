package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.PatientDoctorMapAuditLogRepository;
import com.sunknowledge.dme.rcm.service.PatientDoctorMapAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.PatientDoctorMapAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.PatientDoctorMapAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class PatientDoctorMapAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(PatientDoctorMapAuditLogResource.class);

    private static final String ENTITY_NAME = "patientPatientDoctorMapAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PatientDoctorMapAuditLogService patientDoctorMapAuditLogService;

    private final PatientDoctorMapAuditLogRepository patientDoctorMapAuditLogRepository;

    public PatientDoctorMapAuditLogResource(
        PatientDoctorMapAuditLogService patientDoctorMapAuditLogService,
        PatientDoctorMapAuditLogRepository patientDoctorMapAuditLogRepository
    ) {
        this.patientDoctorMapAuditLogService = patientDoctorMapAuditLogService;
        this.patientDoctorMapAuditLogRepository = patientDoctorMapAuditLogRepository;
    }

    /**
     * {@code POST  /patient-doctor-map-audit-logs} : Create a new patientDoctorMapAuditLog.
     *
     * @param patientDoctorMapAuditLogDTO the patientDoctorMapAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new patientDoctorMapAuditLogDTO, or with status {@code 400 (Bad Request)} if the patientDoctorMapAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/patient-doctor-map-audit-logs")
    public Mono<ResponseEntity<PatientDoctorMapAuditLogDTO>> createPatientDoctorMapAuditLog(
        @RequestBody PatientDoctorMapAuditLogDTO patientDoctorMapAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to save PatientDoctorMapAuditLog : {}", patientDoctorMapAuditLogDTO);
        if (patientDoctorMapAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new patientDoctorMapAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return patientDoctorMapAuditLogService
            .save(patientDoctorMapAuditLogDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/patient-doctor-map-audit-logs/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /patient-doctor-map-audit-logs/:id} : Updates an existing patientDoctorMapAuditLog.
     *
     * @param id the id of the patientDoctorMapAuditLogDTO to save.
     * @param patientDoctorMapAuditLogDTO the patientDoctorMapAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated patientDoctorMapAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the patientDoctorMapAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the patientDoctorMapAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/patient-doctor-map-audit-logs/{id}")
    public Mono<ResponseEntity<PatientDoctorMapAuditLogDTO>> updatePatientDoctorMapAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PatientDoctorMapAuditLogDTO patientDoctorMapAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PatientDoctorMapAuditLog : {}, {}", id, patientDoctorMapAuditLogDTO);
        if (patientDoctorMapAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, patientDoctorMapAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return patientDoctorMapAuditLogRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return patientDoctorMapAuditLogService
                    .update(patientDoctorMapAuditLogDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /patient-doctor-map-audit-logs/:id} : Partial updates given fields of an existing patientDoctorMapAuditLog, field will ignore if it is null
     *
     * @param id the id of the patientDoctorMapAuditLogDTO to save.
     * @param patientDoctorMapAuditLogDTO the patientDoctorMapAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated patientDoctorMapAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the patientDoctorMapAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the patientDoctorMapAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the patientDoctorMapAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/patient-doctor-map-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<PatientDoctorMapAuditLogDTO>> partialUpdatePatientDoctorMapAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PatientDoctorMapAuditLogDTO patientDoctorMapAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PatientDoctorMapAuditLog partially : {}, {}", id, patientDoctorMapAuditLogDTO);
        if (patientDoctorMapAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, patientDoctorMapAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return patientDoctorMapAuditLogRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<PatientDoctorMapAuditLogDTO> result = patientDoctorMapAuditLogService.partialUpdate(patientDoctorMapAuditLogDTO);

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(res ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, res.getId().toString()))
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /patient-doctor-map-audit-logs} : get all the patientDoctorMapAuditLogs.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of patientDoctorMapAuditLogs in body.
     */
    @GetMapping("/patient-doctor-map-audit-logs")
    public Mono<ResponseEntity<List<PatientDoctorMapAuditLogDTO>>> getAllPatientDoctorMapAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of PatientDoctorMapAuditLogs");
        return patientDoctorMapAuditLogService
            .countAll()
            .zipWith(patientDoctorMapAuditLogService.findAll(pageable).collectList())
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
     * {@code GET  /patient-doctor-map-audit-logs/:id} : get the "id" patientDoctorMapAuditLog.
     *
     * @param id the id of the patientDoctorMapAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the patientDoctorMapAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/patient-doctor-map-audit-logs/{id}")
    public Mono<ResponseEntity<PatientDoctorMapAuditLogDTO>> getPatientDoctorMapAuditLog(@PathVariable Long id) {
        log.debug("REST request to get PatientDoctorMapAuditLog : {}", id);
        Mono<PatientDoctorMapAuditLogDTO> patientDoctorMapAuditLogDTO = patientDoctorMapAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(patientDoctorMapAuditLogDTO);
    }

    /**
     * {@code DELETE  /patient-doctor-map-audit-logs/:id} : delete the "id" patientDoctorMapAuditLog.
     *
     * @param id the id of the patientDoctorMapAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/patient-doctor-map-audit-logs/{id}")
    public Mono<ResponseEntity<Void>> deletePatientDoctorMapAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete PatientDoctorMapAuditLog : {}", id);
        return patientDoctorMapAuditLogService
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
