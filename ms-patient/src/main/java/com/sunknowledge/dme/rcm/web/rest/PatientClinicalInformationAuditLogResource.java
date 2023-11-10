package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.PatientClinicalInformationAuditLogRepository;
import com.sunknowledge.dme.rcm.service.PatientClinicalInformationAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.PatientClinicalInformationAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.PatientClinicalInformationAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class PatientClinicalInformationAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(PatientClinicalInformationAuditLogResource.class);

    private static final String ENTITY_NAME = "patientPatientClinicalInformationAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PatientClinicalInformationAuditLogService patientClinicalInformationAuditLogService;

    private final PatientClinicalInformationAuditLogRepository patientClinicalInformationAuditLogRepository;

    public PatientClinicalInformationAuditLogResource(
        PatientClinicalInformationAuditLogService patientClinicalInformationAuditLogService,
        PatientClinicalInformationAuditLogRepository patientClinicalInformationAuditLogRepository
    ) {
        this.patientClinicalInformationAuditLogService = patientClinicalInformationAuditLogService;
        this.patientClinicalInformationAuditLogRepository = patientClinicalInformationAuditLogRepository;
    }

    /**
     * {@code POST  /patient-clinical-information-audit-logs} : Create a new patientClinicalInformationAuditLog.
     *
     * @param patientClinicalInformationAuditLogDTO the patientClinicalInformationAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new patientClinicalInformationAuditLogDTO, or with status {@code 400 (Bad Request)} if the patientClinicalInformationAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/patient-clinical-information-audit-logs")
    public Mono<ResponseEntity<PatientClinicalInformationAuditLogDTO>> createPatientClinicalInformationAuditLog(
        @RequestBody PatientClinicalInformationAuditLogDTO patientClinicalInformationAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to save PatientClinicalInformationAuditLog : {}", patientClinicalInformationAuditLogDTO);
        if (patientClinicalInformationAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException(
                "A new patientClinicalInformationAuditLog cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        return patientClinicalInformationAuditLogService
            .save(patientClinicalInformationAuditLogDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/patient-clinical-information-audit-logs/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /patient-clinical-information-audit-logs/:id} : Updates an existing patientClinicalInformationAuditLog.
     *
     * @param id the id of the patientClinicalInformationAuditLogDTO to save.
     * @param patientClinicalInformationAuditLogDTO the patientClinicalInformationAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated patientClinicalInformationAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the patientClinicalInformationAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the patientClinicalInformationAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/patient-clinical-information-audit-logs/{id}")
    public Mono<ResponseEntity<PatientClinicalInformationAuditLogDTO>> updatePatientClinicalInformationAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PatientClinicalInformationAuditLogDTO patientClinicalInformationAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PatientClinicalInformationAuditLog : {}, {}", id, patientClinicalInformationAuditLogDTO);
        if (patientClinicalInformationAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, patientClinicalInformationAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return patientClinicalInformationAuditLogRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return patientClinicalInformationAuditLogService
                    .update(patientClinicalInformationAuditLogDTO)
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
     * {@code PATCH  /patient-clinical-information-audit-logs/:id} : Partial updates given fields of an existing patientClinicalInformationAuditLog, field will ignore if it is null
     *
     * @param id the id of the patientClinicalInformationAuditLogDTO to save.
     * @param patientClinicalInformationAuditLogDTO the patientClinicalInformationAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated patientClinicalInformationAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the patientClinicalInformationAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the patientClinicalInformationAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the patientClinicalInformationAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/patient-clinical-information-audit-logs/{id}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public Mono<ResponseEntity<PatientClinicalInformationAuditLogDTO>> partialUpdatePatientClinicalInformationAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PatientClinicalInformationAuditLogDTO patientClinicalInformationAuditLogDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update PatientClinicalInformationAuditLog partially : {}, {}",
            id,
            patientClinicalInformationAuditLogDTO
        );
        if (patientClinicalInformationAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, patientClinicalInformationAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return patientClinicalInformationAuditLogRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<PatientClinicalInformationAuditLogDTO> result = patientClinicalInformationAuditLogService.partialUpdate(
                    patientClinicalInformationAuditLogDTO
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
     * {@code GET  /patient-clinical-information-audit-logs} : get all the patientClinicalInformationAuditLogs.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of patientClinicalInformationAuditLogs in body.
     */
    @GetMapping("/patient-clinical-information-audit-logs")
    public Mono<ResponseEntity<List<PatientClinicalInformationAuditLogDTO>>> getAllPatientClinicalInformationAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of PatientClinicalInformationAuditLogs");
        return patientClinicalInformationAuditLogService
            .countAll()
            .zipWith(patientClinicalInformationAuditLogService.findAll(pageable).collectList())
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
     * {@code GET  /patient-clinical-information-audit-logs/:id} : get the "id" patientClinicalInformationAuditLog.
     *
     * @param id the id of the patientClinicalInformationAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the patientClinicalInformationAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/patient-clinical-information-audit-logs/{id}")
    public Mono<ResponseEntity<PatientClinicalInformationAuditLogDTO>> getPatientClinicalInformationAuditLog(@PathVariable Long id) {
        log.debug("REST request to get PatientClinicalInformationAuditLog : {}", id);
        Mono<PatientClinicalInformationAuditLogDTO> patientClinicalInformationAuditLogDTO = patientClinicalInformationAuditLogService.findOne(
            id
        );
        return ResponseUtil.wrapOrNotFound(patientClinicalInformationAuditLogDTO);
    }

    /**
     * {@code DELETE  /patient-clinical-information-audit-logs/:id} : delete the "id" patientClinicalInformationAuditLog.
     *
     * @param id the id of the patientClinicalInformationAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/patient-clinical-information-audit-logs/{id}")
    public Mono<ResponseEntity<Void>> deletePatientClinicalInformationAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete PatientClinicalInformationAuditLog : {}", id);
        return patientClinicalInformationAuditLogService
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
