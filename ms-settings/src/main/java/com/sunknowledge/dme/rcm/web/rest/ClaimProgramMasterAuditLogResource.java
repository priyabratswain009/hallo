package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ClaimProgramMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.ClaimProgramMasterAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.ClaimProgramMasterAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ClaimProgramMasterAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class ClaimProgramMasterAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(ClaimProgramMasterAuditLogResource.class);

    private static final String ENTITY_NAME = "settingsClaimProgramMasterAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClaimProgramMasterAuditLogService claimProgramMasterAuditLogService;

    private final ClaimProgramMasterAuditLogRepository claimProgramMasterAuditLogRepository;

    public ClaimProgramMasterAuditLogResource(
        ClaimProgramMasterAuditLogService claimProgramMasterAuditLogService,
        ClaimProgramMasterAuditLogRepository claimProgramMasterAuditLogRepository
    ) {
        this.claimProgramMasterAuditLogService = claimProgramMasterAuditLogService;
        this.claimProgramMasterAuditLogRepository = claimProgramMasterAuditLogRepository;
    }

    /**
     * {@code POST  /claim-program-master-audit-logs} : Create a new claimProgramMasterAuditLog.
     *
     * @param claimProgramMasterAuditLogDTO the claimProgramMasterAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new claimProgramMasterAuditLogDTO, or with status {@code 400 (Bad Request)} if the claimProgramMasterAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/claim-program-master-audit-logs")
    public ResponseEntity<ClaimProgramMasterAuditLogDTO> createClaimProgramMasterAuditLog(
        @RequestBody ClaimProgramMasterAuditLogDTO claimProgramMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to save ClaimProgramMasterAuditLog : {}", claimProgramMasterAuditLogDTO);
        if (claimProgramMasterAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new claimProgramMasterAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClaimProgramMasterAuditLogDTO result = claimProgramMasterAuditLogService.save(claimProgramMasterAuditLogDTO);
        return ResponseEntity
            .created(new URI("/api/claim-program-master-audit-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /claim-program-master-audit-logs/:id} : Updates an existing claimProgramMasterAuditLog.
     *
     * @param id the id of the claimProgramMasterAuditLogDTO to save.
     * @param claimProgramMasterAuditLogDTO the claimProgramMasterAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claimProgramMasterAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the claimProgramMasterAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the claimProgramMasterAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/claim-program-master-audit-logs/{id}")
    public ResponseEntity<ClaimProgramMasterAuditLogDTO> updateClaimProgramMasterAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ClaimProgramMasterAuditLogDTO claimProgramMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ClaimProgramMasterAuditLog : {}, {}", id, claimProgramMasterAuditLogDTO);
        if (claimProgramMasterAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, claimProgramMasterAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!claimProgramMasterAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ClaimProgramMasterAuditLogDTO result = claimProgramMasterAuditLogService.update(claimProgramMasterAuditLogDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, claimProgramMasterAuditLogDTO.getId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /claim-program-master-audit-logs/:id} : Partial updates given fields of an existing claimProgramMasterAuditLog, field will ignore if it is null
     *
     * @param id the id of the claimProgramMasterAuditLogDTO to save.
     * @param claimProgramMasterAuditLogDTO the claimProgramMasterAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claimProgramMasterAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the claimProgramMasterAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the claimProgramMasterAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the claimProgramMasterAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/claim-program-master-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ClaimProgramMasterAuditLogDTO> partialUpdateClaimProgramMasterAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ClaimProgramMasterAuditLogDTO claimProgramMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ClaimProgramMasterAuditLog partially : {}, {}", id, claimProgramMasterAuditLogDTO);
        if (claimProgramMasterAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, claimProgramMasterAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!claimProgramMasterAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ClaimProgramMasterAuditLogDTO> result = claimProgramMasterAuditLogService.partialUpdate(claimProgramMasterAuditLogDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, claimProgramMasterAuditLogDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /claim-program-master-audit-logs} : get all the claimProgramMasterAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of claimProgramMasterAuditLogs in body.
     */
    @GetMapping("/claim-program-master-audit-logs")
    public ResponseEntity<List<ClaimProgramMasterAuditLogDTO>> getAllClaimProgramMasterAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ClaimProgramMasterAuditLogs");
        Page<ClaimProgramMasterAuditLogDTO> page = claimProgramMasterAuditLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /claim-program-master-audit-logs/:id} : get the "id" claimProgramMasterAuditLog.
     *
     * @param id the id of the claimProgramMasterAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the claimProgramMasterAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/claim-program-master-audit-logs/{id}")
    public ResponseEntity<ClaimProgramMasterAuditLogDTO> getClaimProgramMasterAuditLog(@PathVariable Long id) {
        log.debug("REST request to get ClaimProgramMasterAuditLog : {}", id);
        Optional<ClaimProgramMasterAuditLogDTO> claimProgramMasterAuditLogDTO = claimProgramMasterAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(claimProgramMasterAuditLogDTO);
    }

    /**
     * {@code DELETE  /claim-program-master-audit-logs/:id} : delete the "id" claimProgramMasterAuditLog.
     *
     * @param id the id of the claimProgramMasterAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/claim-program-master-audit-logs/{id}")
    public ResponseEntity<Void> deleteClaimProgramMasterAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete ClaimProgramMasterAuditLog : {}", id);
        claimProgramMasterAuditLogService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
