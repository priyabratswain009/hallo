package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.BranchGroupAuditLogRepository;
import com.sunknowledge.dme.rcm.service.BranchGroupAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.BranchGroupAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.BranchGroupAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class BranchGroupAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(BranchGroupAuditLogResource.class);

    private static final String ENTITY_NAME = "settingsBranchGroupAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BranchGroupAuditLogService branchGroupAuditLogService;

    private final BranchGroupAuditLogRepository branchGroupAuditLogRepository;

    public BranchGroupAuditLogResource(
        BranchGroupAuditLogService branchGroupAuditLogService,
        BranchGroupAuditLogRepository branchGroupAuditLogRepository
    ) {
        this.branchGroupAuditLogService = branchGroupAuditLogService;
        this.branchGroupAuditLogRepository = branchGroupAuditLogRepository;
    }

    /**
     * {@code POST  /branch-group-audit-logs} : Create a new branchGroupAuditLog.
     *
     * @param branchGroupAuditLogDTO the branchGroupAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new branchGroupAuditLogDTO, or with status {@code 400 (Bad Request)} if the branchGroupAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/branch-group-audit-logs")
    public ResponseEntity<BranchGroupAuditLogDTO> createBranchGroupAuditLog(@RequestBody BranchGroupAuditLogDTO branchGroupAuditLogDTO)
        throws URISyntaxException {
        log.debug("REST request to save BranchGroupAuditLog : {}", branchGroupAuditLogDTO);
        if (branchGroupAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new branchGroupAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BranchGroupAuditLogDTO result = branchGroupAuditLogService.save(branchGroupAuditLogDTO);
        return ResponseEntity
            .created(new URI("/api/branch-group-audit-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /branch-group-audit-logs/:id} : Updates an existing branchGroupAuditLog.
     *
     * @param id the id of the branchGroupAuditLogDTO to save.
     * @param branchGroupAuditLogDTO the branchGroupAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated branchGroupAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the branchGroupAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the branchGroupAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/branch-group-audit-logs/{id}")
    public ResponseEntity<BranchGroupAuditLogDTO> updateBranchGroupAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BranchGroupAuditLogDTO branchGroupAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update BranchGroupAuditLog : {}, {}", id, branchGroupAuditLogDTO);
        if (branchGroupAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, branchGroupAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!branchGroupAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BranchGroupAuditLogDTO result = branchGroupAuditLogService.update(branchGroupAuditLogDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, branchGroupAuditLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /branch-group-audit-logs/:id} : Partial updates given fields of an existing branchGroupAuditLog, field will ignore if it is null
     *
     * @param id the id of the branchGroupAuditLogDTO to save.
     * @param branchGroupAuditLogDTO the branchGroupAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated branchGroupAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the branchGroupAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the branchGroupAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the branchGroupAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/branch-group-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BranchGroupAuditLogDTO> partialUpdateBranchGroupAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BranchGroupAuditLogDTO branchGroupAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update BranchGroupAuditLog partially : {}, {}", id, branchGroupAuditLogDTO);
        if (branchGroupAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, branchGroupAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!branchGroupAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BranchGroupAuditLogDTO> result = branchGroupAuditLogService.partialUpdate(branchGroupAuditLogDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, branchGroupAuditLogDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /branch-group-audit-logs} : get all the branchGroupAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of branchGroupAuditLogs in body.
     */
    @GetMapping("/branch-group-audit-logs")
    public ResponseEntity<List<BranchGroupAuditLogDTO>> getAllBranchGroupAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of BranchGroupAuditLogs");
        Page<BranchGroupAuditLogDTO> page = branchGroupAuditLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /branch-group-audit-logs/:id} : get the "id" branchGroupAuditLog.
     *
     * @param id the id of the branchGroupAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the branchGroupAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/branch-group-audit-logs/{id}")
    public ResponseEntity<BranchGroupAuditLogDTO> getBranchGroupAuditLog(@PathVariable Long id) {
        log.debug("REST request to get BranchGroupAuditLog : {}", id);
        Optional<BranchGroupAuditLogDTO> branchGroupAuditLogDTO = branchGroupAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(branchGroupAuditLogDTO);
    }

    /**
     * {@code DELETE  /branch-group-audit-logs/:id} : delete the "id" branchGroupAuditLog.
     *
     * @param id the id of the branchGroupAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/branch-group-audit-logs/{id}")
    public ResponseEntity<Void> deleteBranchGroupAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete BranchGroupAuditLog : {}", id);
        branchGroupAuditLogService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
