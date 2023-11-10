package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ClaimFormMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.ClaimFormMasterAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.ClaimFormMasterAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ClaimFormMasterAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class ClaimFormMasterAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(ClaimFormMasterAuditLogResource.class);

    private static final String ENTITY_NAME = "settingsClaimFormMasterAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClaimFormMasterAuditLogService claimFormMasterAuditLogService;

    private final ClaimFormMasterAuditLogRepository claimFormMasterAuditLogRepository;

    public ClaimFormMasterAuditLogResource(
        ClaimFormMasterAuditLogService claimFormMasterAuditLogService,
        ClaimFormMasterAuditLogRepository claimFormMasterAuditLogRepository
    ) {
        this.claimFormMasterAuditLogService = claimFormMasterAuditLogService;
        this.claimFormMasterAuditLogRepository = claimFormMasterAuditLogRepository;
    }

    /**
     * {@code POST  /claim-form-master-audit-logs} : Create a new claimFormMasterAuditLog.
     *
     * @param claimFormMasterAuditLogDTO the claimFormMasterAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new claimFormMasterAuditLogDTO, or with status {@code 400 (Bad Request)} if the claimFormMasterAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/claim-form-master-audit-logs")
    public ResponseEntity<ClaimFormMasterAuditLogDTO> createClaimFormMasterAuditLog(
        @RequestBody ClaimFormMasterAuditLogDTO claimFormMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to save ClaimFormMasterAuditLog : {}", claimFormMasterAuditLogDTO);
        if (claimFormMasterAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new claimFormMasterAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClaimFormMasterAuditLogDTO result = claimFormMasterAuditLogService.save(claimFormMasterAuditLogDTO);
        return ResponseEntity
            .created(new URI("/api/claim-form-master-audit-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /claim-form-master-audit-logs/:id} : Updates an existing claimFormMasterAuditLog.
     *
     * @param id the id of the claimFormMasterAuditLogDTO to save.
     * @param claimFormMasterAuditLogDTO the claimFormMasterAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claimFormMasterAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the claimFormMasterAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the claimFormMasterAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/claim-form-master-audit-logs/{id}")
    public ResponseEntity<ClaimFormMasterAuditLogDTO> updateClaimFormMasterAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ClaimFormMasterAuditLogDTO claimFormMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ClaimFormMasterAuditLog : {}, {}", id, claimFormMasterAuditLogDTO);
        if (claimFormMasterAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, claimFormMasterAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!claimFormMasterAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ClaimFormMasterAuditLogDTO result = claimFormMasterAuditLogService.update(claimFormMasterAuditLogDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, claimFormMasterAuditLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /claim-form-master-audit-logs/:id} : Partial updates given fields of an existing claimFormMasterAuditLog, field will ignore if it is null
     *
     * @param id the id of the claimFormMasterAuditLogDTO to save.
     * @param claimFormMasterAuditLogDTO the claimFormMasterAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claimFormMasterAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the claimFormMasterAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the claimFormMasterAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the claimFormMasterAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/claim-form-master-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ClaimFormMasterAuditLogDTO> partialUpdateClaimFormMasterAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ClaimFormMasterAuditLogDTO claimFormMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ClaimFormMasterAuditLog partially : {}, {}", id, claimFormMasterAuditLogDTO);
        if (claimFormMasterAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, claimFormMasterAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!claimFormMasterAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ClaimFormMasterAuditLogDTO> result = claimFormMasterAuditLogService.partialUpdate(claimFormMasterAuditLogDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, claimFormMasterAuditLogDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /claim-form-master-audit-logs} : get all the claimFormMasterAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of claimFormMasterAuditLogs in body.
     */
    @GetMapping("/claim-form-master-audit-logs")
    public ResponseEntity<List<ClaimFormMasterAuditLogDTO>> getAllClaimFormMasterAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ClaimFormMasterAuditLogs");
        Page<ClaimFormMasterAuditLogDTO> page = claimFormMasterAuditLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /claim-form-master-audit-logs/:id} : get the "id" claimFormMasterAuditLog.
     *
     * @param id the id of the claimFormMasterAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the claimFormMasterAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/claim-form-master-audit-logs/{id}")
    public ResponseEntity<ClaimFormMasterAuditLogDTO> getClaimFormMasterAuditLog(@PathVariable Long id) {
        log.debug("REST request to get ClaimFormMasterAuditLog : {}", id);
        Optional<ClaimFormMasterAuditLogDTO> claimFormMasterAuditLogDTO = claimFormMasterAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(claimFormMasterAuditLogDTO);
    }

    /**
     * {@code DELETE  /claim-form-master-audit-logs/:id} : delete the "id" claimFormMasterAuditLog.
     *
     * @param id the id of the claimFormMasterAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/claim-form-master-audit-logs/{id}")
    public ResponseEntity<Void> deleteClaimFormMasterAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete ClaimFormMasterAuditLog : {}", id);
        claimFormMasterAuditLogService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
