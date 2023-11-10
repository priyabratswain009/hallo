package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.WorkersCompensationAuditLogRepository;
import com.sunknowledge.dme.rcm.service.WorkersCompensationAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.WorkersCompensationAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.WorkersCompensationAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class WorkersCompensationAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(WorkersCompensationAuditLogResource.class);

    private static final String ENTITY_NAME = "patientWorkersCompensationAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WorkersCompensationAuditLogService workersCompensationAuditLogService;

    private final WorkersCompensationAuditLogRepository workersCompensationAuditLogRepository;

    public WorkersCompensationAuditLogResource(
        WorkersCompensationAuditLogService workersCompensationAuditLogService,
        WorkersCompensationAuditLogRepository workersCompensationAuditLogRepository
    ) {
        this.workersCompensationAuditLogService = workersCompensationAuditLogService;
        this.workersCompensationAuditLogRepository = workersCompensationAuditLogRepository;
    }

    /**
     * {@code POST  /workers-compensation-audit-logs} : Create a new workersCompensationAuditLog.
     *
     * @param workersCompensationAuditLogDTO the workersCompensationAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new workersCompensationAuditLogDTO, or with status {@code 400 (Bad Request)} if the workersCompensationAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/workers-compensation-audit-logs")
    public Mono<ResponseEntity<WorkersCompensationAuditLogDTO>> createWorkersCompensationAuditLog(
        @RequestBody WorkersCompensationAuditLogDTO workersCompensationAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to save WorkersCompensationAuditLog : {}", workersCompensationAuditLogDTO);
        if (workersCompensationAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new workersCompensationAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return workersCompensationAuditLogService
            .save(workersCompensationAuditLogDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/workers-compensation-audit-logs/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /workers-compensation-audit-logs/:id} : Updates an existing workersCompensationAuditLog.
     *
     * @param id the id of the workersCompensationAuditLogDTO to save.
     * @param workersCompensationAuditLogDTO the workersCompensationAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated workersCompensationAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the workersCompensationAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the workersCompensationAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/workers-compensation-audit-logs/{id}")
    public Mono<ResponseEntity<WorkersCompensationAuditLogDTO>> updateWorkersCompensationAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody WorkersCompensationAuditLogDTO workersCompensationAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update WorkersCompensationAuditLog : {}, {}", id, workersCompensationAuditLogDTO);
        if (workersCompensationAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, workersCompensationAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return workersCompensationAuditLogRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return workersCompensationAuditLogService
                    .update(workersCompensationAuditLogDTO)
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
     * {@code PATCH  /workers-compensation-audit-logs/:id} : Partial updates given fields of an existing workersCompensationAuditLog, field will ignore if it is null
     *
     * @param id the id of the workersCompensationAuditLogDTO to save.
     * @param workersCompensationAuditLogDTO the workersCompensationAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated workersCompensationAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the workersCompensationAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the workersCompensationAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the workersCompensationAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/workers-compensation-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<WorkersCompensationAuditLogDTO>> partialUpdateWorkersCompensationAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody WorkersCompensationAuditLogDTO workersCompensationAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update WorkersCompensationAuditLog partially : {}, {}", id, workersCompensationAuditLogDTO);
        if (workersCompensationAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, workersCompensationAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return workersCompensationAuditLogRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<WorkersCompensationAuditLogDTO> result = workersCompensationAuditLogService.partialUpdate(
                    workersCompensationAuditLogDTO
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
     * {@code GET  /workers-compensation-audit-logs} : get all the workersCompensationAuditLogs.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of workersCompensationAuditLogs in body.
     */
    @GetMapping("/workers-compensation-audit-logs")
    public Mono<ResponseEntity<List<WorkersCompensationAuditLogDTO>>> getAllWorkersCompensationAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of WorkersCompensationAuditLogs");
        return workersCompensationAuditLogService
            .countAll()
            .zipWith(workersCompensationAuditLogService.findAll(pageable).collectList())
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
     * {@code GET  /workers-compensation-audit-logs/:id} : get the "id" workersCompensationAuditLog.
     *
     * @param id the id of the workersCompensationAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the workersCompensationAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/workers-compensation-audit-logs/{id}")
    public Mono<ResponseEntity<WorkersCompensationAuditLogDTO>> getWorkersCompensationAuditLog(@PathVariable Long id) {
        log.debug("REST request to get WorkersCompensationAuditLog : {}", id);
        Mono<WorkersCompensationAuditLogDTO> workersCompensationAuditLogDTO = workersCompensationAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(workersCompensationAuditLogDTO);
    }

    /**
     * {@code DELETE  /workers-compensation-audit-logs/:id} : delete the "id" workersCompensationAuditLog.
     *
     * @param id the id of the workersCompensationAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/workers-compensation-audit-logs/{id}")
    public Mono<ResponseEntity<Void>> deleteWorkersCompensationAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete WorkersCompensationAuditLog : {}", id);
        return workersCompensationAuditLogService
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
