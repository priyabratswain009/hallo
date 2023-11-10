package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.BranchInsuranceMapAuditLogRepository;
import com.sunknowledge.dme.rcm.service.BranchInsuranceMapAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.BranchInsuranceMapAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.BranchInsuranceMapAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class BranchInsuranceMapAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(BranchInsuranceMapAuditLogResource.class);

    private static final String ENTITY_NAME = "settingsBranchInsuranceMapAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BranchInsuranceMapAuditLogService branchInsuranceMapAuditLogService;

    private final BranchInsuranceMapAuditLogRepository branchInsuranceMapAuditLogRepository;

    public BranchInsuranceMapAuditLogResource(
        BranchInsuranceMapAuditLogService branchInsuranceMapAuditLogService,
        BranchInsuranceMapAuditLogRepository branchInsuranceMapAuditLogRepository
    ) {
        this.branchInsuranceMapAuditLogService = branchInsuranceMapAuditLogService;
        this.branchInsuranceMapAuditLogRepository = branchInsuranceMapAuditLogRepository;
    }

    /**
     * {@code POST  /branch-insurance-map-audit-logs} : Create a new branchInsuranceMapAuditLog.
     *
     * @param branchInsuranceMapAuditLogDTO the branchInsuranceMapAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new branchInsuranceMapAuditLogDTO, or with status {@code 400 (Bad Request)} if the branchInsuranceMapAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/branch-insurance-map-audit-logs")
    public ResponseEntity<BranchInsuranceMapAuditLogDTO> createBranchInsuranceMapAuditLog(
        @RequestBody BranchInsuranceMapAuditLogDTO branchInsuranceMapAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to save BranchInsuranceMapAuditLog : {}", branchInsuranceMapAuditLogDTO);
        if (branchInsuranceMapAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new branchInsuranceMapAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BranchInsuranceMapAuditLogDTO result = branchInsuranceMapAuditLogService.save(branchInsuranceMapAuditLogDTO);
        return ResponseEntity
            .created(new URI("/api/branch-insurance-map-audit-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /branch-insurance-map-audit-logs/:id} : Updates an existing branchInsuranceMapAuditLog.
     *
     * @param id the id of the branchInsuranceMapAuditLogDTO to save.
     * @param branchInsuranceMapAuditLogDTO the branchInsuranceMapAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated branchInsuranceMapAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the branchInsuranceMapAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the branchInsuranceMapAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/branch-insurance-map-audit-logs/{id}")
    public ResponseEntity<BranchInsuranceMapAuditLogDTO> updateBranchInsuranceMapAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BranchInsuranceMapAuditLogDTO branchInsuranceMapAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update BranchInsuranceMapAuditLog : {}, {}", id, branchInsuranceMapAuditLogDTO);
        if (branchInsuranceMapAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, branchInsuranceMapAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!branchInsuranceMapAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BranchInsuranceMapAuditLogDTO result = branchInsuranceMapAuditLogService.update(branchInsuranceMapAuditLogDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, branchInsuranceMapAuditLogDTO.getId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /branch-insurance-map-audit-logs/:id} : Partial updates given fields of an existing branchInsuranceMapAuditLog, field will ignore if it is null
     *
     * @param id the id of the branchInsuranceMapAuditLogDTO to save.
     * @param branchInsuranceMapAuditLogDTO the branchInsuranceMapAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated branchInsuranceMapAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the branchInsuranceMapAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the branchInsuranceMapAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the branchInsuranceMapAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/branch-insurance-map-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BranchInsuranceMapAuditLogDTO> partialUpdateBranchInsuranceMapAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BranchInsuranceMapAuditLogDTO branchInsuranceMapAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update BranchInsuranceMapAuditLog partially : {}, {}", id, branchInsuranceMapAuditLogDTO);
        if (branchInsuranceMapAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, branchInsuranceMapAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!branchInsuranceMapAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BranchInsuranceMapAuditLogDTO> result = branchInsuranceMapAuditLogService.partialUpdate(branchInsuranceMapAuditLogDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, branchInsuranceMapAuditLogDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /branch-insurance-map-audit-logs} : get all the branchInsuranceMapAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of branchInsuranceMapAuditLogs in body.
     */
    @GetMapping("/branch-insurance-map-audit-logs")
    public ResponseEntity<List<BranchInsuranceMapAuditLogDTO>> getAllBranchInsuranceMapAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of BranchInsuranceMapAuditLogs");
        Page<BranchInsuranceMapAuditLogDTO> page = branchInsuranceMapAuditLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /branch-insurance-map-audit-logs/:id} : get the "id" branchInsuranceMapAuditLog.
     *
     * @param id the id of the branchInsuranceMapAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the branchInsuranceMapAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/branch-insurance-map-audit-logs/{id}")
    public ResponseEntity<BranchInsuranceMapAuditLogDTO> getBranchInsuranceMapAuditLog(@PathVariable Long id) {
        log.debug("REST request to get BranchInsuranceMapAuditLog : {}", id);
        Optional<BranchInsuranceMapAuditLogDTO> branchInsuranceMapAuditLogDTO = branchInsuranceMapAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(branchInsuranceMapAuditLogDTO);
    }

    /**
     * {@code DELETE  /branch-insurance-map-audit-logs/:id} : delete the "id" branchInsuranceMapAuditLog.
     *
     * @param id the id of the branchInsuranceMapAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/branch-insurance-map-audit-logs/{id}")
    public ResponseEntity<Void> deleteBranchInsuranceMapAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete BranchInsuranceMapAuditLog : {}", id);
        branchInsuranceMapAuditLogService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
