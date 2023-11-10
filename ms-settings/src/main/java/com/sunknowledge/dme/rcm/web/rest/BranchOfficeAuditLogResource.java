package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.BranchOfficeAuditLogRepository;
import com.sunknowledge.dme.rcm.service.BranchOfficeAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.BranchOfficeAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.BranchOfficeAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class BranchOfficeAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(BranchOfficeAuditLogResource.class);

    private static final String ENTITY_NAME = "settingsBranchOfficeAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BranchOfficeAuditLogService branchOfficeAuditLogService;

    private final BranchOfficeAuditLogRepository branchOfficeAuditLogRepository;

    public BranchOfficeAuditLogResource(
        BranchOfficeAuditLogService branchOfficeAuditLogService,
        BranchOfficeAuditLogRepository branchOfficeAuditLogRepository
    ) {
        this.branchOfficeAuditLogService = branchOfficeAuditLogService;
        this.branchOfficeAuditLogRepository = branchOfficeAuditLogRepository;
    }

    /**
     * {@code POST  /branch-office-audit-logs} : Create a new branchOfficeAuditLog.
     *
     * @param branchOfficeAuditLogDTO the branchOfficeAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new branchOfficeAuditLogDTO, or with status {@code 400 (Bad Request)} if the branchOfficeAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/branch-office-audit-logs")
    public ResponseEntity<BranchOfficeAuditLogDTO> createBranchOfficeAuditLog(@RequestBody BranchOfficeAuditLogDTO branchOfficeAuditLogDTO)
        throws URISyntaxException {
        log.debug("REST request to save BranchOfficeAuditLog : {}", branchOfficeAuditLogDTO);
        if (branchOfficeAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new branchOfficeAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BranchOfficeAuditLogDTO result = branchOfficeAuditLogService.save(branchOfficeAuditLogDTO);
        return ResponseEntity
            .created(new URI("/api/branch-office-audit-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /branch-office-audit-logs/:id} : Updates an existing branchOfficeAuditLog.
     *
     * @param id the id of the branchOfficeAuditLogDTO to save.
     * @param branchOfficeAuditLogDTO the branchOfficeAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated branchOfficeAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the branchOfficeAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the branchOfficeAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/branch-office-audit-logs/{id}")
    public ResponseEntity<BranchOfficeAuditLogDTO> updateBranchOfficeAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BranchOfficeAuditLogDTO branchOfficeAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update BranchOfficeAuditLog : {}, {}", id, branchOfficeAuditLogDTO);
        if (branchOfficeAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, branchOfficeAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!branchOfficeAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BranchOfficeAuditLogDTO result = branchOfficeAuditLogService.update(branchOfficeAuditLogDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, branchOfficeAuditLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /branch-office-audit-logs/:id} : Partial updates given fields of an existing branchOfficeAuditLog, field will ignore if it is null
     *
     * @param id the id of the branchOfficeAuditLogDTO to save.
     * @param branchOfficeAuditLogDTO the branchOfficeAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated branchOfficeAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the branchOfficeAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the branchOfficeAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the branchOfficeAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/branch-office-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BranchOfficeAuditLogDTO> partialUpdateBranchOfficeAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BranchOfficeAuditLogDTO branchOfficeAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update BranchOfficeAuditLog partially : {}, {}", id, branchOfficeAuditLogDTO);
        if (branchOfficeAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, branchOfficeAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!branchOfficeAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BranchOfficeAuditLogDTO> result = branchOfficeAuditLogService.partialUpdate(branchOfficeAuditLogDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, branchOfficeAuditLogDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /branch-office-audit-logs} : get all the branchOfficeAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of branchOfficeAuditLogs in body.
     */
    @GetMapping("/branch-office-audit-logs")
    public ResponseEntity<List<BranchOfficeAuditLogDTO>> getAllBranchOfficeAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of BranchOfficeAuditLogs");
        Page<BranchOfficeAuditLogDTO> page = branchOfficeAuditLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /branch-office-audit-logs/:id} : get the "id" branchOfficeAuditLog.
     *
     * @param id the id of the branchOfficeAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the branchOfficeAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/branch-office-audit-logs/{id}")
    public ResponseEntity<BranchOfficeAuditLogDTO> getBranchOfficeAuditLog(@PathVariable Long id) {
        log.debug("REST request to get BranchOfficeAuditLog : {}", id);
        Optional<BranchOfficeAuditLogDTO> branchOfficeAuditLogDTO = branchOfficeAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(branchOfficeAuditLogDTO);
    }

    /**
     * {@code DELETE  /branch-office-audit-logs/:id} : delete the "id" branchOfficeAuditLog.
     *
     * @param id the id of the branchOfficeAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/branch-office-audit-logs/{id}")
    public ResponseEntity<Void> deleteBranchOfficeAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete BranchOfficeAuditLog : {}", id);
        branchOfficeAuditLogService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
