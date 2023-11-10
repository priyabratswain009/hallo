package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.HoldReasonMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.HoldReasonMasterAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.HoldReasonMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.HoldReasonMasterAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class HoldReasonMasterAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(HoldReasonMasterAuditLogResource.class);

    private static final String ENTITY_NAME = "settingsHoldReasonMasterAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HoldReasonMasterAuditLogService holdReasonMasterAuditLogService;

    private final HoldReasonMasterAuditLogRepository holdReasonMasterAuditLogRepository;

    public HoldReasonMasterAuditLogResource(
        HoldReasonMasterAuditLogService holdReasonMasterAuditLogService,
        HoldReasonMasterAuditLogRepository holdReasonMasterAuditLogRepository
    ) {
        this.holdReasonMasterAuditLogService = holdReasonMasterAuditLogService;
        this.holdReasonMasterAuditLogRepository = holdReasonMasterAuditLogRepository;
    }

    /**
     * {@code POST  /hold-reason-master-audit-logs} : Create a new holdReasonMasterAuditLog.
     *
     * @param holdReasonMasterAuditLogDTO the holdReasonMasterAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new holdReasonMasterAuditLogDTO, or with status {@code 400 (Bad Request)} if the holdReasonMasterAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/hold-reason-master-audit-logs")
    public ResponseEntity<HoldReasonMasterAuditLogDTO> createHoldReasonMasterAuditLog(
        @RequestBody HoldReasonMasterAuditLogDTO holdReasonMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to save HoldReasonMasterAuditLog : {}", holdReasonMasterAuditLogDTO);
        if (holdReasonMasterAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new holdReasonMasterAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HoldReasonMasterAuditLogDTO result = holdReasonMasterAuditLogService.save(holdReasonMasterAuditLogDTO);
        return ResponseEntity
            .created(new URI("/api/hold-reason-master-audit-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /hold-reason-master-audit-logs/:id} : Updates an existing holdReasonMasterAuditLog.
     *
     * @param id the id of the holdReasonMasterAuditLogDTO to save.
     * @param holdReasonMasterAuditLogDTO the holdReasonMasterAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated holdReasonMasterAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the holdReasonMasterAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the holdReasonMasterAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/hold-reason-master-audit-logs/{id}")
    public ResponseEntity<HoldReasonMasterAuditLogDTO> updateHoldReasonMasterAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody HoldReasonMasterAuditLogDTO holdReasonMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update HoldReasonMasterAuditLog : {}, {}", id, holdReasonMasterAuditLogDTO);
        if (holdReasonMasterAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, holdReasonMasterAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!holdReasonMasterAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        HoldReasonMasterAuditLogDTO result = holdReasonMasterAuditLogService.update(holdReasonMasterAuditLogDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, holdReasonMasterAuditLogDTO.getId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /hold-reason-master-audit-logs/:id} : Partial updates given fields of an existing holdReasonMasterAuditLog, field will ignore if it is null
     *
     * @param id the id of the holdReasonMasterAuditLogDTO to save.
     * @param holdReasonMasterAuditLogDTO the holdReasonMasterAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated holdReasonMasterAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the holdReasonMasterAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the holdReasonMasterAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the holdReasonMasterAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/hold-reason-master-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<HoldReasonMasterAuditLogDTO> partialUpdateHoldReasonMasterAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody HoldReasonMasterAuditLogDTO holdReasonMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update HoldReasonMasterAuditLog partially : {}, {}", id, holdReasonMasterAuditLogDTO);
        if (holdReasonMasterAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, holdReasonMasterAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!holdReasonMasterAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<HoldReasonMasterAuditLogDTO> result = holdReasonMasterAuditLogService.partialUpdate(holdReasonMasterAuditLogDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, holdReasonMasterAuditLogDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /hold-reason-master-audit-logs} : get all the holdReasonMasterAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of holdReasonMasterAuditLogs in body.
     */
    @GetMapping("/hold-reason-master-audit-logs")
    public ResponseEntity<List<HoldReasonMasterAuditLogDTO>> getAllHoldReasonMasterAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of HoldReasonMasterAuditLogs");
        Page<HoldReasonMasterAuditLogDTO> page = holdReasonMasterAuditLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /hold-reason-master-audit-logs/:id} : get the "id" holdReasonMasterAuditLog.
     *
     * @param id the id of the holdReasonMasterAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the holdReasonMasterAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/hold-reason-master-audit-logs/{id}")
    public ResponseEntity<HoldReasonMasterAuditLogDTO> getHoldReasonMasterAuditLog(@PathVariable Long id) {
        log.debug("REST request to get HoldReasonMasterAuditLog : {}", id);
        Optional<HoldReasonMasterAuditLogDTO> holdReasonMasterAuditLogDTO = holdReasonMasterAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(holdReasonMasterAuditLogDTO);
    }

    /**
     * {@code DELETE  /hold-reason-master-audit-logs/:id} : delete the "id" holdReasonMasterAuditLog.
     *
     * @param id the id of the holdReasonMasterAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/hold-reason-master-audit-logs/{id}")
    public ResponseEntity<Void> deleteHoldReasonMasterAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete HoldReasonMasterAuditLog : {}", id);
        holdReasonMasterAuditLogService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
