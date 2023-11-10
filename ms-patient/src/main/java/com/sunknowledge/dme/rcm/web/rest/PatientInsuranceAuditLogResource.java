package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.PatientInsuranceAuditLogRepository;
import com.sunknowledge.dme.rcm.service.PatientInsuranceAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.PatientInsuranceAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.PatientInsuranceAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class PatientInsuranceAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(PatientInsuranceAuditLogResource.class);

    private static final String ENTITY_NAME = "patientPatientInsuranceAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PatientInsuranceAuditLogService patientInsuranceAuditLogService;

    private final PatientInsuranceAuditLogRepository patientInsuranceAuditLogRepository;

    public PatientInsuranceAuditLogResource(
        PatientInsuranceAuditLogService patientInsuranceAuditLogService,
        PatientInsuranceAuditLogRepository patientInsuranceAuditLogRepository
    ) {
        this.patientInsuranceAuditLogService = patientInsuranceAuditLogService;
        this.patientInsuranceAuditLogRepository = patientInsuranceAuditLogRepository;
    }

    /**
     * {@code POST  /patient-insurance-audit-logs} : Create a new patientInsuranceAuditLog.
     *
     * @param patientInsuranceAuditLogDTO the patientInsuranceAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new patientInsuranceAuditLogDTO, or with status {@code 400 (Bad Request)} if the patientInsuranceAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/patient-insurance-audit-logs")
    public Mono<ResponseEntity<PatientInsuranceAuditLogDTO>> createPatientInsuranceAuditLog(
        @RequestBody PatientInsuranceAuditLogDTO patientInsuranceAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to save PatientInsuranceAuditLog : {}", patientInsuranceAuditLogDTO);
        if (patientInsuranceAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new patientInsuranceAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return patientInsuranceAuditLogService
            .save(patientInsuranceAuditLogDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/patient-insurance-audit-logs/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /patient-insurance-audit-logs/:id} : Updates an existing patientInsuranceAuditLog.
     *
     * @param id the id of the patientInsuranceAuditLogDTO to save.
     * @param patientInsuranceAuditLogDTO the patientInsuranceAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated patientInsuranceAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the patientInsuranceAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the patientInsuranceAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/patient-insurance-audit-logs/{id}")
    public Mono<ResponseEntity<PatientInsuranceAuditLogDTO>> updatePatientInsuranceAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PatientInsuranceAuditLogDTO patientInsuranceAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PatientInsuranceAuditLog : {}, {}", id, patientInsuranceAuditLogDTO);
        if (patientInsuranceAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, patientInsuranceAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return patientInsuranceAuditLogRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return patientInsuranceAuditLogService
                    .update(patientInsuranceAuditLogDTO)
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
     * {@code PATCH  /patient-insurance-audit-logs/:id} : Partial updates given fields of an existing patientInsuranceAuditLog, field will ignore if it is null
     *
     * @param id the id of the patientInsuranceAuditLogDTO to save.
     * @param patientInsuranceAuditLogDTO the patientInsuranceAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated patientInsuranceAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the patientInsuranceAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the patientInsuranceAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the patientInsuranceAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/patient-insurance-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<PatientInsuranceAuditLogDTO>> partialUpdatePatientInsuranceAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PatientInsuranceAuditLogDTO patientInsuranceAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PatientInsuranceAuditLog partially : {}, {}", id, patientInsuranceAuditLogDTO);
        if (patientInsuranceAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, patientInsuranceAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return patientInsuranceAuditLogRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<PatientInsuranceAuditLogDTO> result = patientInsuranceAuditLogService.partialUpdate(patientInsuranceAuditLogDTO);

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
     * {@code GET  /patient-insurance-audit-logs} : get all the patientInsuranceAuditLogs.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of patientInsuranceAuditLogs in body.
     */
    @GetMapping("/patient-insurance-audit-logs")
    public Mono<ResponseEntity<List<PatientInsuranceAuditLogDTO>>> getAllPatientInsuranceAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of PatientInsuranceAuditLogs");
        return patientInsuranceAuditLogService
            .countAll()
            .zipWith(patientInsuranceAuditLogService.findAll(pageable).collectList())
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
     * {@code GET  /patient-insurance-audit-logs/:id} : get the "id" patientInsuranceAuditLog.
     *
     * @param id the id of the patientInsuranceAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the patientInsuranceAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/patient-insurance-audit-logs/{id}")
    public Mono<ResponseEntity<PatientInsuranceAuditLogDTO>> getPatientInsuranceAuditLog(@PathVariable Long id) {
        log.debug("REST request to get PatientInsuranceAuditLog : {}", id);
        Mono<PatientInsuranceAuditLogDTO> patientInsuranceAuditLogDTO = patientInsuranceAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(patientInsuranceAuditLogDTO);
    }

    /**
     * {@code DELETE  /patient-insurance-audit-logs/:id} : delete the "id" patientInsuranceAuditLog.
     *
     * @param id the id of the patientInsuranceAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/patient-insurance-audit-logs/{id}")
    public Mono<ResponseEntity<Void>> deletePatientInsuranceAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete PatientInsuranceAuditLog : {}", id);
        return patientInsuranceAuditLogService
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
