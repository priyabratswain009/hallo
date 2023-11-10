package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.PatientDiagnosisAuditLogRepository;
import com.sunknowledge.dme.rcm.service.PatientDiagnosisAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.PatientDiagnosisAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.PatientDiagnosisAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class PatientDiagnosisAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(PatientDiagnosisAuditLogResource.class);

    private static final String ENTITY_NAME = "patientPatientDiagnosisAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PatientDiagnosisAuditLogService patientDiagnosisAuditLogService;

    private final PatientDiagnosisAuditLogRepository patientDiagnosisAuditLogRepository;

    public PatientDiagnosisAuditLogResource(
        PatientDiagnosisAuditLogService patientDiagnosisAuditLogService,
        PatientDiagnosisAuditLogRepository patientDiagnosisAuditLogRepository
    ) {
        this.patientDiagnosisAuditLogService = patientDiagnosisAuditLogService;
        this.patientDiagnosisAuditLogRepository = patientDiagnosisAuditLogRepository;
    }

    /**
     * {@code POST  /patient-diagnosis-audit-logs} : Create a new patientDiagnosisAuditLog.
     *
     * @param patientDiagnosisAuditLogDTO the patientDiagnosisAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new patientDiagnosisAuditLogDTO, or with status {@code 400 (Bad Request)} if the patientDiagnosisAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/patient-diagnosis-audit-logs")
    public Mono<ResponseEntity<PatientDiagnosisAuditLogDTO>> createPatientDiagnosisAuditLog(
        @RequestBody PatientDiagnosisAuditLogDTO patientDiagnosisAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to save PatientDiagnosisAuditLog : {}", patientDiagnosisAuditLogDTO);
        if (patientDiagnosisAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new patientDiagnosisAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return patientDiagnosisAuditLogService
            .save(patientDiagnosisAuditLogDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/patient-diagnosis-audit-logs/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /patient-diagnosis-audit-logs/:id} : Updates an existing patientDiagnosisAuditLog.
     *
     * @param id the id of the patientDiagnosisAuditLogDTO to save.
     * @param patientDiagnosisAuditLogDTO the patientDiagnosisAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated patientDiagnosisAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the patientDiagnosisAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the patientDiagnosisAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/patient-diagnosis-audit-logs/{id}")
    public Mono<ResponseEntity<PatientDiagnosisAuditLogDTO>> updatePatientDiagnosisAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PatientDiagnosisAuditLogDTO patientDiagnosisAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PatientDiagnosisAuditLog : {}, {}", id, patientDiagnosisAuditLogDTO);
        if (patientDiagnosisAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, patientDiagnosisAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return patientDiagnosisAuditLogRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return patientDiagnosisAuditLogService
                    .update(patientDiagnosisAuditLogDTO)
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
     * {@code PATCH  /patient-diagnosis-audit-logs/:id} : Partial updates given fields of an existing patientDiagnosisAuditLog, field will ignore if it is null
     *
     * @param id the id of the patientDiagnosisAuditLogDTO to save.
     * @param patientDiagnosisAuditLogDTO the patientDiagnosisAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated patientDiagnosisAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the patientDiagnosisAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the patientDiagnosisAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the patientDiagnosisAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/patient-diagnosis-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<PatientDiagnosisAuditLogDTO>> partialUpdatePatientDiagnosisAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PatientDiagnosisAuditLogDTO patientDiagnosisAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PatientDiagnosisAuditLog partially : {}, {}", id, patientDiagnosisAuditLogDTO);
        if (patientDiagnosisAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, patientDiagnosisAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return patientDiagnosisAuditLogRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<PatientDiagnosisAuditLogDTO> result = patientDiagnosisAuditLogService.partialUpdate(patientDiagnosisAuditLogDTO);

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
     * {@code GET  /patient-diagnosis-audit-logs} : get all the patientDiagnosisAuditLogs.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of patientDiagnosisAuditLogs in body.
     */
    @GetMapping("/patient-diagnosis-audit-logs")
    public Mono<ResponseEntity<List<PatientDiagnosisAuditLogDTO>>> getAllPatientDiagnosisAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of PatientDiagnosisAuditLogs");
        return patientDiagnosisAuditLogService
            .countAll()
            .zipWith(patientDiagnosisAuditLogService.findAll(pageable).collectList())
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
     * {@code GET  /patient-diagnosis-audit-logs/:id} : get the "id" patientDiagnosisAuditLog.
     *
     * @param id the id of the patientDiagnosisAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the patientDiagnosisAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/patient-diagnosis-audit-logs/{id}")
    public Mono<ResponseEntity<PatientDiagnosisAuditLogDTO>> getPatientDiagnosisAuditLog(@PathVariable Long id) {
        log.debug("REST request to get PatientDiagnosisAuditLog : {}", id);
        Mono<PatientDiagnosisAuditLogDTO> patientDiagnosisAuditLogDTO = patientDiagnosisAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(patientDiagnosisAuditLogDTO);
    }

    /**
     * {@code DELETE  /patient-diagnosis-audit-logs/:id} : delete the "id" patientDiagnosisAuditLog.
     *
     * @param id the id of the patientDiagnosisAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/patient-diagnosis-audit-logs/{id}")
    public Mono<ResponseEntity<Void>> deletePatientDiagnosisAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete PatientDiagnosisAuditLog : {}", id);
        return patientDiagnosisAuditLogService
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
