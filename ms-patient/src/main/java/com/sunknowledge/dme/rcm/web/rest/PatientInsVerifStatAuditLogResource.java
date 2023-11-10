package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.PatientInsVerifStatAuditLogRepository;
import com.sunknowledge.dme.rcm.service.PatientInsVerifStatAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.PatientInsVerifStatAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.PatientInsVerifStatAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class PatientInsVerifStatAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(PatientInsVerifStatAuditLogResource.class);

    private static final String ENTITY_NAME = "patientPatientInsVerifStatAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PatientInsVerifStatAuditLogService patientInsVerifStatAuditLogService;

    private final PatientInsVerifStatAuditLogRepository patientInsVerifStatAuditLogRepository;

    public PatientInsVerifStatAuditLogResource(
        PatientInsVerifStatAuditLogService patientInsVerifStatAuditLogService,
        PatientInsVerifStatAuditLogRepository patientInsVerifStatAuditLogRepository
    ) {
        this.patientInsVerifStatAuditLogService = patientInsVerifStatAuditLogService;
        this.patientInsVerifStatAuditLogRepository = patientInsVerifStatAuditLogRepository;
    }

    /**
     * {@code POST  /patient-ins-verif-stat-audit-logs} : Create a new patientInsVerifStatAuditLog.
     *
     * @param patientInsVerifStatAuditLogDTO the patientInsVerifStatAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new patientInsVerifStatAuditLogDTO, or with status {@code 400 (Bad Request)} if the patientInsVerifStatAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/patient-ins-verif-stat-audit-logs")
    public Mono<ResponseEntity<PatientInsVerifStatAuditLogDTO>> createPatientInsVerifStatAuditLog(
        @RequestBody PatientInsVerifStatAuditLogDTO patientInsVerifStatAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to save PatientInsVerifStatAuditLog : {}", patientInsVerifStatAuditLogDTO);
        if (patientInsVerifStatAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new patientInsVerifStatAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return patientInsVerifStatAuditLogService
            .save(patientInsVerifStatAuditLogDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/patient-ins-verif-stat-audit-logs/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /patient-ins-verif-stat-audit-logs/:id} : Updates an existing patientInsVerifStatAuditLog.
     *
     * @param id the id of the patientInsVerifStatAuditLogDTO to save.
     * @param patientInsVerifStatAuditLogDTO the patientInsVerifStatAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated patientInsVerifStatAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the patientInsVerifStatAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the patientInsVerifStatAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/patient-ins-verif-stat-audit-logs/{id}")
    public Mono<ResponseEntity<PatientInsVerifStatAuditLogDTO>> updatePatientInsVerifStatAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PatientInsVerifStatAuditLogDTO patientInsVerifStatAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PatientInsVerifStatAuditLog : {}, {}", id, patientInsVerifStatAuditLogDTO);
        if (patientInsVerifStatAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, patientInsVerifStatAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return patientInsVerifStatAuditLogRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return patientInsVerifStatAuditLogService
                    .update(patientInsVerifStatAuditLogDTO)
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
     * {@code PATCH  /patient-ins-verif-stat-audit-logs/:id} : Partial updates given fields of an existing patientInsVerifStatAuditLog, field will ignore if it is null
     *
     * @param id the id of the patientInsVerifStatAuditLogDTO to save.
     * @param patientInsVerifStatAuditLogDTO the patientInsVerifStatAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated patientInsVerifStatAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the patientInsVerifStatAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the patientInsVerifStatAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the patientInsVerifStatAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/patient-ins-verif-stat-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<PatientInsVerifStatAuditLogDTO>> partialUpdatePatientInsVerifStatAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PatientInsVerifStatAuditLogDTO patientInsVerifStatAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PatientInsVerifStatAuditLog partially : {}, {}", id, patientInsVerifStatAuditLogDTO);
        if (patientInsVerifStatAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, patientInsVerifStatAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return patientInsVerifStatAuditLogRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<PatientInsVerifStatAuditLogDTO> result = patientInsVerifStatAuditLogService.partialUpdate(
                    patientInsVerifStatAuditLogDTO
                );

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
     * {@code GET  /patient-ins-verif-stat-audit-logs} : get all the patientInsVerifStatAuditLogs.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of patientInsVerifStatAuditLogs in body.
     */
    @GetMapping("/patient-ins-verif-stat-audit-logs")
    public Mono<ResponseEntity<List<PatientInsVerifStatAuditLogDTO>>> getAllPatientInsVerifStatAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of PatientInsVerifStatAuditLogs");
        return patientInsVerifStatAuditLogService
            .countAll()
            .zipWith(patientInsVerifStatAuditLogService.findAll(pageable).collectList())
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
     * {@code GET  /patient-ins-verif-stat-audit-logs/:id} : get the "id" patientInsVerifStatAuditLog.
     *
     * @param id the id of the patientInsVerifStatAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the patientInsVerifStatAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/patient-ins-verif-stat-audit-logs/{id}")
    public Mono<ResponseEntity<PatientInsVerifStatAuditLogDTO>> getPatientInsVerifStatAuditLog(@PathVariable Long id) {
        log.debug("REST request to get PatientInsVerifStatAuditLog : {}", id);
        Mono<PatientInsVerifStatAuditLogDTO> patientInsVerifStatAuditLogDTO = patientInsVerifStatAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(patientInsVerifStatAuditLogDTO);
    }

    /**
     * {@code DELETE  /patient-ins-verif-stat-audit-logs/:id} : delete the "id" patientInsVerifStatAuditLog.
     *
     * @param id the id of the patientInsVerifStatAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/patient-ins-verif-stat-audit-logs/{id}")
    public Mono<ResponseEntity<Void>> deletePatientInsVerifStatAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete PatientInsVerifStatAuditLog : {}", id);
        return patientInsVerifStatAuditLogService
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
