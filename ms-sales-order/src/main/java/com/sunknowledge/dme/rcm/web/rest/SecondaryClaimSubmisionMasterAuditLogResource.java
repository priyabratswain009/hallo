package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.SecondaryClaimSubmisionMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.SecondaryClaimSubmisionMasterAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.SecondaryClaimSubmisionMasterAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.SecondaryClaimSubmisionMasterAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class SecondaryClaimSubmisionMasterAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(SecondaryClaimSubmisionMasterAuditLogResource.class);

    private static final String ENTITY_NAME = "salesorderSecondaryClaimSubmisionMasterAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SecondaryClaimSubmisionMasterAuditLogService secondaryClaimSubmisionMasterAuditLogService;

    private final SecondaryClaimSubmisionMasterAuditLogRepository secondaryClaimSubmisionMasterAuditLogRepository;

    public SecondaryClaimSubmisionMasterAuditLogResource(
        SecondaryClaimSubmisionMasterAuditLogService secondaryClaimSubmisionMasterAuditLogService,
        SecondaryClaimSubmisionMasterAuditLogRepository secondaryClaimSubmisionMasterAuditLogRepository
    ) {
        this.secondaryClaimSubmisionMasterAuditLogService = secondaryClaimSubmisionMasterAuditLogService;
        this.secondaryClaimSubmisionMasterAuditLogRepository = secondaryClaimSubmisionMasterAuditLogRepository;
    }

    /**
     * {@code POST  /secondary-claim-submision-master-audit-logs} : Create a new secondaryClaimSubmisionMasterAuditLog.
     *
     * @param secondaryClaimSubmisionMasterAuditLogDTO the secondaryClaimSubmisionMasterAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new secondaryClaimSubmisionMasterAuditLogDTO, or with status {@code 400 (Bad Request)} if the secondaryClaimSubmisionMasterAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/secondary-claim-submision-master-audit-logs")
    public Mono<ResponseEntity<SecondaryClaimSubmisionMasterAuditLogDTO>> createSecondaryClaimSubmisionMasterAuditLog(
        @RequestBody SecondaryClaimSubmisionMasterAuditLogDTO secondaryClaimSubmisionMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to save SecondaryClaimSubmisionMasterAuditLog : {}", secondaryClaimSubmisionMasterAuditLogDTO);
        if (secondaryClaimSubmisionMasterAuditLogDTO.getChgHealthSconarySubmnMsterId() != null) {
            throw new BadRequestAlertException(
                "A new secondaryClaimSubmisionMasterAuditLog cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        return secondaryClaimSubmisionMasterAuditLogService
            .save(secondaryClaimSubmisionMasterAuditLogDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/secondary-claim-submision-master-audit-logs/" + result.getChgHealthSconarySubmnMsterId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(
                                applicationName,
                                false,
                                ENTITY_NAME,
                                result.getChgHealthSconarySubmnMsterId().toString()
                            )
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /secondary-claim-submision-master-audit-logs/:chgHealthSconarySubmnMsterId} : Updates an existing secondaryClaimSubmisionMasterAuditLog.
     *
     * @param chgHealthSconarySubmnMsterId the id of the secondaryClaimSubmisionMasterAuditLogDTO to save.
     * @param secondaryClaimSubmisionMasterAuditLogDTO the secondaryClaimSubmisionMasterAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated secondaryClaimSubmisionMasterAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the secondaryClaimSubmisionMasterAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the secondaryClaimSubmisionMasterAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/secondary-claim-submision-master-audit-logs/{chgHealthSconarySubmnMsterId}")
    public Mono<ResponseEntity<SecondaryClaimSubmisionMasterAuditLogDTO>> updateSecondaryClaimSubmisionMasterAuditLog(
        @PathVariable(value = "chgHealthSconarySubmnMsterId", required = false) final Long chgHealthSconarySubmnMsterId,
        @RequestBody SecondaryClaimSubmisionMasterAuditLogDTO secondaryClaimSubmisionMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to update SecondaryClaimSubmisionMasterAuditLog : {}, {}",
            chgHealthSconarySubmnMsterId,
            secondaryClaimSubmisionMasterAuditLogDTO
        );
        if (secondaryClaimSubmisionMasterAuditLogDTO.getChgHealthSconarySubmnMsterId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(chgHealthSconarySubmnMsterId, secondaryClaimSubmisionMasterAuditLogDTO.getChgHealthSconarySubmnMsterId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return secondaryClaimSubmisionMasterAuditLogRepository
            .existsById(chgHealthSconarySubmnMsterId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return secondaryClaimSubmisionMasterAuditLogService
                    .update(secondaryClaimSubmisionMasterAuditLogDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getChgHealthSconarySubmnMsterId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /secondary-claim-submision-master-audit-logs/:chgHealthSconarySubmnMsterId} : Partial updates given fields of an existing secondaryClaimSubmisionMasterAuditLog, field will ignore if it is null
     *
     * @param chgHealthSconarySubmnMsterId the id of the secondaryClaimSubmisionMasterAuditLogDTO to save.
     * @param secondaryClaimSubmisionMasterAuditLogDTO the secondaryClaimSubmisionMasterAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated secondaryClaimSubmisionMasterAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the secondaryClaimSubmisionMasterAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the secondaryClaimSubmisionMasterAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the secondaryClaimSubmisionMasterAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/secondary-claim-submision-master-audit-logs/{chgHealthSconarySubmnMsterId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public Mono<ResponseEntity<SecondaryClaimSubmisionMasterAuditLogDTO>> partialUpdateSecondaryClaimSubmisionMasterAuditLog(
        @PathVariable(value = "chgHealthSconarySubmnMsterId", required = false) final Long chgHealthSconarySubmnMsterId,
        @RequestBody SecondaryClaimSubmisionMasterAuditLogDTO secondaryClaimSubmisionMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update SecondaryClaimSubmisionMasterAuditLog partially : {}, {}",
            chgHealthSconarySubmnMsterId,
            secondaryClaimSubmisionMasterAuditLogDTO
        );
        if (secondaryClaimSubmisionMasterAuditLogDTO.getChgHealthSconarySubmnMsterId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(chgHealthSconarySubmnMsterId, secondaryClaimSubmisionMasterAuditLogDTO.getChgHealthSconarySubmnMsterId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return secondaryClaimSubmisionMasterAuditLogRepository
            .existsById(chgHealthSconarySubmnMsterId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<SecondaryClaimSubmisionMasterAuditLogDTO> result = secondaryClaimSubmisionMasterAuditLogService.partialUpdate(
                    secondaryClaimSubmisionMasterAuditLogDTO
                );

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
                                    res.getChgHealthSconarySubmnMsterId().toString()
                                )
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /secondary-claim-submision-master-audit-logs} : get all the secondaryClaimSubmisionMasterAuditLogs.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of secondaryClaimSubmisionMasterAuditLogs in body.
     */
    @GetMapping("/secondary-claim-submision-master-audit-logs")
    public Mono<ResponseEntity<List<SecondaryClaimSubmisionMasterAuditLogDTO>>> getAllSecondaryClaimSubmisionMasterAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of SecondaryClaimSubmisionMasterAuditLogs");
        return secondaryClaimSubmisionMasterAuditLogService
            .countAll()
            .zipWith(secondaryClaimSubmisionMasterAuditLogService.findAll(pageable).collectList())
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
     * {@code GET  /secondary-claim-submision-master-audit-logs/:id} : get the "id" secondaryClaimSubmisionMasterAuditLog.
     *
     * @param id the id of the secondaryClaimSubmisionMasterAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the secondaryClaimSubmisionMasterAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/secondary-claim-submision-master-audit-logs/{id}")
    public Mono<ResponseEntity<SecondaryClaimSubmisionMasterAuditLogDTO>> getSecondaryClaimSubmisionMasterAuditLog(@PathVariable Long id) {
        log.debug("REST request to get SecondaryClaimSubmisionMasterAuditLog : {}", id);
        Mono<SecondaryClaimSubmisionMasterAuditLogDTO> secondaryClaimSubmisionMasterAuditLogDTO = secondaryClaimSubmisionMasterAuditLogService.findOne(
            id
        );
        return ResponseUtil.wrapOrNotFound(secondaryClaimSubmisionMasterAuditLogDTO);
    }

    /**
     * {@code DELETE  /secondary-claim-submision-master-audit-logs/:id} : delete the "id" secondaryClaimSubmisionMasterAuditLog.
     *
     * @param id the id of the secondaryClaimSubmisionMasterAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/secondary-claim-submision-master-audit-logs/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteSecondaryClaimSubmisionMasterAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete SecondaryClaimSubmisionMasterAuditLog : {}", id);
        return secondaryClaimSubmisionMasterAuditLogService
            .delete(id)
            .map(result ->
                ResponseEntity
                    .noContent()
                    .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                    .build()
            );
    }
}
