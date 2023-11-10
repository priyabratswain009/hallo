package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.PatientDocumentAuditLogRepository;
import com.sunknowledge.dme.rcm.service.PatientDocumentAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.PatientDocumentAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.PatientDocumentAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class PatientDocumentAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(PatientDocumentAuditLogResource.class);

    private static final String ENTITY_NAME = "patientPatientDocumentAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PatientDocumentAuditLogService patientDocumentAuditLogService;

    private final PatientDocumentAuditLogRepository patientDocumentAuditLogRepository;

    public PatientDocumentAuditLogResource(
        PatientDocumentAuditLogService patientDocumentAuditLogService,
        PatientDocumentAuditLogRepository patientDocumentAuditLogRepository
    ) {
        this.patientDocumentAuditLogService = patientDocumentAuditLogService;
        this.patientDocumentAuditLogRepository = patientDocumentAuditLogRepository;
    }

    /**
     * {@code POST  /patient-document-audit-logs} : Create a new patientDocumentAuditLog.
     *
     * @param patientDocumentAuditLogDTO the patientDocumentAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new patientDocumentAuditLogDTO, or with status {@code 400 (Bad Request)} if the patientDocumentAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/patient-document-audit-logs")
    public Mono<ResponseEntity<PatientDocumentAuditLogDTO>> createPatientDocumentAuditLog(
        @RequestBody PatientDocumentAuditLogDTO patientDocumentAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to save PatientDocumentAuditLog : {}", patientDocumentAuditLogDTO);
        if (patientDocumentAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new patientDocumentAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return patientDocumentAuditLogService
            .save(patientDocumentAuditLogDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/patient-document-audit-logs/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /patient-document-audit-logs/:id} : Updates an existing patientDocumentAuditLog.
     *
     * @param id the id of the patientDocumentAuditLogDTO to save.
     * @param patientDocumentAuditLogDTO the patientDocumentAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated patientDocumentAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the patientDocumentAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the patientDocumentAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/patient-document-audit-logs/{id}")
    public Mono<ResponseEntity<PatientDocumentAuditLogDTO>> updatePatientDocumentAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PatientDocumentAuditLogDTO patientDocumentAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PatientDocumentAuditLog : {}, {}", id, patientDocumentAuditLogDTO);
        if (patientDocumentAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, patientDocumentAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return patientDocumentAuditLogRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return patientDocumentAuditLogService
                    .update(patientDocumentAuditLogDTO)
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
     * {@code PATCH  /patient-document-audit-logs/:id} : Partial updates given fields of an existing patientDocumentAuditLog, field will ignore if it is null
     *
     * @param id the id of the patientDocumentAuditLogDTO to save.
     * @param patientDocumentAuditLogDTO the patientDocumentAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated patientDocumentAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the patientDocumentAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the patientDocumentAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the patientDocumentAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/patient-document-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<PatientDocumentAuditLogDTO>> partialUpdatePatientDocumentAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PatientDocumentAuditLogDTO patientDocumentAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PatientDocumentAuditLog partially : {}, {}", id, patientDocumentAuditLogDTO);
        if (patientDocumentAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, patientDocumentAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return patientDocumentAuditLogRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<PatientDocumentAuditLogDTO> result = patientDocumentAuditLogService.partialUpdate(patientDocumentAuditLogDTO);

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
     * {@code GET  /patient-document-audit-logs} : get all the patientDocumentAuditLogs.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of patientDocumentAuditLogs in body.
     */
    @GetMapping("/patient-document-audit-logs")
    public Mono<ResponseEntity<List<PatientDocumentAuditLogDTO>>> getAllPatientDocumentAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of PatientDocumentAuditLogs");
        return patientDocumentAuditLogService
            .countAll()
            .zipWith(patientDocumentAuditLogService.findAll(pageable).collectList())
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
     * {@code GET  /patient-document-audit-logs/:id} : get the "id" patientDocumentAuditLog.
     *
     * @param id the id of the patientDocumentAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the patientDocumentAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/patient-document-audit-logs/{id}")
    public Mono<ResponseEntity<PatientDocumentAuditLogDTO>> getPatientDocumentAuditLog(@PathVariable Long id) {
        log.debug("REST request to get PatientDocumentAuditLog : {}", id);
        Mono<PatientDocumentAuditLogDTO> patientDocumentAuditLogDTO = patientDocumentAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(patientDocumentAuditLogDTO);
    }

    /**
     * {@code DELETE  /patient-document-audit-logs/:id} : delete the "id" patientDocumentAuditLog.
     *
     * @param id the id of the patientDocumentAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/patient-document-audit-logs/{id}")
    public Mono<ResponseEntity<Void>> deletePatientDocumentAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete PatientDocumentAuditLog : {}", id);
        return patientDocumentAuditLogService
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
