package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.StopReasonMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.StopReasonMasterAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.StopReasonMasterAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.StopReasonMasterAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class StopReasonMasterAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(StopReasonMasterAuditLogResource.class);

    private static final String ENTITY_NAME = "settingsStopReasonMasterAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StopReasonMasterAuditLogService stopReasonMasterAuditLogService;

    private final StopReasonMasterAuditLogRepository stopReasonMasterAuditLogRepository;

    public StopReasonMasterAuditLogResource(
        StopReasonMasterAuditLogService stopReasonMasterAuditLogService,
        StopReasonMasterAuditLogRepository stopReasonMasterAuditLogRepository
    ) {
        this.stopReasonMasterAuditLogService = stopReasonMasterAuditLogService;
        this.stopReasonMasterAuditLogRepository = stopReasonMasterAuditLogRepository;
    }

    /**
     * {@code POST  /stop-reason-master-audit-logs} : Create a new stopReasonMasterAuditLog.
     *
     * @param stopReasonMasterAuditLogDTO the stopReasonMasterAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new stopReasonMasterAuditLogDTO, or with status {@code 400 (Bad Request)} if the stopReasonMasterAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/stop-reason-master-audit-logs")
    public ResponseEntity<StopReasonMasterAuditLogDTO> createStopReasonMasterAuditLog(
        @RequestBody StopReasonMasterAuditLogDTO stopReasonMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to save StopReasonMasterAuditLog : {}", stopReasonMasterAuditLogDTO);
        if (stopReasonMasterAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new stopReasonMasterAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StopReasonMasterAuditLogDTO result = stopReasonMasterAuditLogService.save(stopReasonMasterAuditLogDTO);
        return ResponseEntity
            .created(new URI("/api/stop-reason-master-audit-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /stop-reason-master-audit-logs/:id} : Updates an existing stopReasonMasterAuditLog.
     *
     * @param id the id of the stopReasonMasterAuditLogDTO to save.
     * @param stopReasonMasterAuditLogDTO the stopReasonMasterAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated stopReasonMasterAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the stopReasonMasterAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the stopReasonMasterAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/stop-reason-master-audit-logs/{id}")
    public ResponseEntity<StopReasonMasterAuditLogDTO> updateStopReasonMasterAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody StopReasonMasterAuditLogDTO stopReasonMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update StopReasonMasterAuditLog : {}, {}", id, stopReasonMasterAuditLogDTO);
        if (stopReasonMasterAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, stopReasonMasterAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!stopReasonMasterAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        StopReasonMasterAuditLogDTO result = stopReasonMasterAuditLogService.update(stopReasonMasterAuditLogDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, stopReasonMasterAuditLogDTO.getId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /stop-reason-master-audit-logs/:id} : Partial updates given fields of an existing stopReasonMasterAuditLog, field will ignore if it is null
     *
     * @param id the id of the stopReasonMasterAuditLogDTO to save.
     * @param stopReasonMasterAuditLogDTO the stopReasonMasterAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated stopReasonMasterAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the stopReasonMasterAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the stopReasonMasterAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the stopReasonMasterAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/stop-reason-master-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<StopReasonMasterAuditLogDTO> partialUpdateStopReasonMasterAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody StopReasonMasterAuditLogDTO stopReasonMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update StopReasonMasterAuditLog partially : {}, {}", id, stopReasonMasterAuditLogDTO);
        if (stopReasonMasterAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, stopReasonMasterAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!stopReasonMasterAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<StopReasonMasterAuditLogDTO> result = stopReasonMasterAuditLogService.partialUpdate(stopReasonMasterAuditLogDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, stopReasonMasterAuditLogDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /stop-reason-master-audit-logs} : get all the stopReasonMasterAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of stopReasonMasterAuditLogs in body.
     */
    @GetMapping("/stop-reason-master-audit-logs")
    public ResponseEntity<List<StopReasonMasterAuditLogDTO>> getAllStopReasonMasterAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of StopReasonMasterAuditLogs");
        Page<StopReasonMasterAuditLogDTO> page = stopReasonMasterAuditLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /stop-reason-master-audit-logs/:id} : get the "id" stopReasonMasterAuditLog.
     *
     * @param id the id of the stopReasonMasterAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the stopReasonMasterAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/stop-reason-master-audit-logs/{id}")
    public ResponseEntity<StopReasonMasterAuditLogDTO> getStopReasonMasterAuditLog(@PathVariable Long id) {
        log.debug("REST request to get StopReasonMasterAuditLog : {}", id);
        Optional<StopReasonMasterAuditLogDTO> stopReasonMasterAuditLogDTO = stopReasonMasterAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(stopReasonMasterAuditLogDTO);
    }

    /**
     * {@code DELETE  /stop-reason-master-audit-logs/:id} : delete the "id" stopReasonMasterAuditLog.
     *
     * @param id the id of the stopReasonMasterAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/stop-reason-master-audit-logs/{id}")
    public ResponseEntity<Void> deleteStopReasonMasterAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete StopReasonMasterAuditLog : {}", id);
        stopReasonMasterAuditLogService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
