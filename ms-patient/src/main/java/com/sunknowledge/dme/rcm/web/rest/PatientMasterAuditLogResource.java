package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.PatientMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.PatientMasterAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.PatientMasterAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.PatientMasterAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class PatientMasterAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(PatientMasterAuditLogResource.class);

    private static final String ENTITY_NAME = "patientPatientMasterAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PatientMasterAuditLogService patientMasterAuditLogService;

    private final PatientMasterAuditLogRepository patientMasterAuditLogRepository;

    public PatientMasterAuditLogResource(
        PatientMasterAuditLogService patientMasterAuditLogService,
        PatientMasterAuditLogRepository patientMasterAuditLogRepository
    ) {
        this.patientMasterAuditLogService = patientMasterAuditLogService;
        this.patientMasterAuditLogRepository = patientMasterAuditLogRepository;
    }

    /**
     * {@code POST  /patient-master-audit-logs} : Create a new patientMasterAuditLog.
     *
     * @param patientMasterAuditLogDTO the patientMasterAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new patientMasterAuditLogDTO, or with status {@code 400 (Bad Request)} if the patientMasterAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/patient-master-audit-logs")
    public Mono<ResponseEntity<PatientMasterAuditLogDTO>> createPatientMasterAuditLog(
        @RequestBody PatientMasterAuditLogDTO patientMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to save PatientMasterAuditLog : {}", patientMasterAuditLogDTO);
        if (patientMasterAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new patientMasterAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return patientMasterAuditLogService
            .save(patientMasterAuditLogDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/patient-master-audit-logs/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /patient-master-audit-logs/:id} : Updates an existing patientMasterAuditLog.
     *
     * @param id the id of the patientMasterAuditLogDTO to save.
     * @param patientMasterAuditLogDTO the patientMasterAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated patientMasterAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the patientMasterAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the patientMasterAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/patient-master-audit-logs/{id}")
    public Mono<ResponseEntity<PatientMasterAuditLogDTO>> updatePatientMasterAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PatientMasterAuditLogDTO patientMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PatientMasterAuditLog : {}, {}", id, patientMasterAuditLogDTO);
        if (patientMasterAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, patientMasterAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return patientMasterAuditLogRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return patientMasterAuditLogService
                    .update(patientMasterAuditLogDTO)
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
     * {@code PATCH  /patient-master-audit-logs/:id} : Partial updates given fields of an existing patientMasterAuditLog, field will ignore if it is null
     *
     * @param id the id of the patientMasterAuditLogDTO to save.
     * @param patientMasterAuditLogDTO the patientMasterAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated patientMasterAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the patientMasterAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the patientMasterAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the patientMasterAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/patient-master-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<PatientMasterAuditLogDTO>> partialUpdatePatientMasterAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PatientMasterAuditLogDTO patientMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PatientMasterAuditLog partially : {}, {}", id, patientMasterAuditLogDTO);
        if (patientMasterAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, patientMasterAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return patientMasterAuditLogRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<PatientMasterAuditLogDTO> result = patientMasterAuditLogService.partialUpdate(patientMasterAuditLogDTO);

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
     * {@code GET  /patient-master-audit-logs} : get all the patientMasterAuditLogs.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of patientMasterAuditLogs in body.
     */
    @GetMapping("/patient-master-audit-logs")
    public Mono<ResponseEntity<List<PatientMasterAuditLogDTO>>> getAllPatientMasterAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of PatientMasterAuditLogs");
        return patientMasterAuditLogService
            .countAll()
            .zipWith(patientMasterAuditLogService.findAll(pageable).collectList())
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
     * {@code GET  /patient-master-audit-logs/:id} : get the "id" patientMasterAuditLog.
     *
     * @param id the id of the patientMasterAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the patientMasterAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/patient-master-audit-logs/{id}")
    public Mono<ResponseEntity<PatientMasterAuditLogDTO>> getPatientMasterAuditLog(@PathVariable Long id) {
        log.debug("REST request to get PatientMasterAuditLog : {}", id);
        Mono<PatientMasterAuditLogDTO> patientMasterAuditLogDTO = patientMasterAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(patientMasterAuditLogDTO);
    }

    /**
     * {@code DELETE  /patient-master-audit-logs/:id} : delete the "id" patientMasterAuditLog.
     *
     * @param id the id of the patientMasterAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/patient-master-audit-logs/{id}")
    public Mono<ResponseEntity<Void>> deletePatientMasterAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete PatientMasterAuditLog : {}", id);
        return patientMasterAuditLogService
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
