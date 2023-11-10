package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.FunctionalAbilityAuditLogRepository;
import com.sunknowledge.dme.rcm.service.FunctionalAbilityAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.FunctionalAbilityAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.FunctionalAbilityAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class FunctionalAbilityAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(FunctionalAbilityAuditLogResource.class);

    private static final String ENTITY_NAME = "patientFunctionalAbilityAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FunctionalAbilityAuditLogService functionalAbilityAuditLogService;

    private final FunctionalAbilityAuditLogRepository functionalAbilityAuditLogRepository;

    public FunctionalAbilityAuditLogResource(
        FunctionalAbilityAuditLogService functionalAbilityAuditLogService,
        FunctionalAbilityAuditLogRepository functionalAbilityAuditLogRepository
    ) {
        this.functionalAbilityAuditLogService = functionalAbilityAuditLogService;
        this.functionalAbilityAuditLogRepository = functionalAbilityAuditLogRepository;
    }

    /**
     * {@code POST  /functional-ability-audit-logs} : Create a new functionalAbilityAuditLog.
     *
     * @param functionalAbilityAuditLogDTO the functionalAbilityAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new functionalAbilityAuditLogDTO, or with status {@code 400 (Bad Request)} if the functionalAbilityAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/functional-ability-audit-logs")
    public Mono<ResponseEntity<FunctionalAbilityAuditLogDTO>> createFunctionalAbilityAuditLog(
        @RequestBody FunctionalAbilityAuditLogDTO functionalAbilityAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to save FunctionalAbilityAuditLog : {}", functionalAbilityAuditLogDTO);
        if (functionalAbilityAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new functionalAbilityAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return functionalAbilityAuditLogService
            .save(functionalAbilityAuditLogDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/functional-ability-audit-logs/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /functional-ability-audit-logs/:id} : Updates an existing functionalAbilityAuditLog.
     *
     * @param id the id of the functionalAbilityAuditLogDTO to save.
     * @param functionalAbilityAuditLogDTO the functionalAbilityAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated functionalAbilityAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the functionalAbilityAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the functionalAbilityAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/functional-ability-audit-logs/{id}")
    public Mono<ResponseEntity<FunctionalAbilityAuditLogDTO>> updateFunctionalAbilityAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody FunctionalAbilityAuditLogDTO functionalAbilityAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update FunctionalAbilityAuditLog : {}, {}", id, functionalAbilityAuditLogDTO);
        if (functionalAbilityAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, functionalAbilityAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return functionalAbilityAuditLogRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return functionalAbilityAuditLogService
                    .update(functionalAbilityAuditLogDTO)
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
     * {@code PATCH  /functional-ability-audit-logs/:id} : Partial updates given fields of an existing functionalAbilityAuditLog, field will ignore if it is null
     *
     * @param id the id of the functionalAbilityAuditLogDTO to save.
     * @param functionalAbilityAuditLogDTO the functionalAbilityAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated functionalAbilityAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the functionalAbilityAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the functionalAbilityAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the functionalAbilityAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/functional-ability-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<FunctionalAbilityAuditLogDTO>> partialUpdateFunctionalAbilityAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody FunctionalAbilityAuditLogDTO functionalAbilityAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update FunctionalAbilityAuditLog partially : {}, {}", id, functionalAbilityAuditLogDTO);
        if (functionalAbilityAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, functionalAbilityAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return functionalAbilityAuditLogRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<FunctionalAbilityAuditLogDTO> result = functionalAbilityAuditLogService.partialUpdate(functionalAbilityAuditLogDTO);

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
     * {@code GET  /functional-ability-audit-logs} : get all the functionalAbilityAuditLogs.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of functionalAbilityAuditLogs in body.
     */
    @GetMapping("/functional-ability-audit-logs")
    public Mono<ResponseEntity<List<FunctionalAbilityAuditLogDTO>>> getAllFunctionalAbilityAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of FunctionalAbilityAuditLogs");
        return functionalAbilityAuditLogService
            .countAll()
            .zipWith(functionalAbilityAuditLogService.findAll(pageable).collectList())
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
     * {@code GET  /functional-ability-audit-logs/:id} : get the "id" functionalAbilityAuditLog.
     *
     * @param id the id of the functionalAbilityAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the functionalAbilityAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/functional-ability-audit-logs/{id}")
    public Mono<ResponseEntity<FunctionalAbilityAuditLogDTO>> getFunctionalAbilityAuditLog(@PathVariable Long id) {
        log.debug("REST request to get FunctionalAbilityAuditLog : {}", id);
        Mono<FunctionalAbilityAuditLogDTO> functionalAbilityAuditLogDTO = functionalAbilityAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(functionalAbilityAuditLogDTO);
    }

    /**
     * {@code DELETE  /functional-ability-audit-logs/:id} : delete the "id" functionalAbilityAuditLog.
     *
     * @param id the id of the functionalAbilityAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/functional-ability-audit-logs/{id}")
    public Mono<ResponseEntity<Void>> deleteFunctionalAbilityAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete FunctionalAbilityAuditLog : {}", id);
        return functionalAbilityAuditLogService
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
