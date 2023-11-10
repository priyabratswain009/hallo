package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.BranchItemLocationMapAuditLogRepository;
import com.sunknowledge.dme.rcm.service.BranchItemLocationMapAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.BranchItemLocationMapAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.BranchItemLocationMapAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class BranchItemLocationMapAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(BranchItemLocationMapAuditLogResource.class);

    private static final String ENTITY_NAME = "settingsBranchItemLocationMapAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BranchItemLocationMapAuditLogService branchItemLocationMapAuditLogService;

    private final BranchItemLocationMapAuditLogRepository branchItemLocationMapAuditLogRepository;

    public BranchItemLocationMapAuditLogResource(
        BranchItemLocationMapAuditLogService branchItemLocationMapAuditLogService,
        BranchItemLocationMapAuditLogRepository branchItemLocationMapAuditLogRepository
    ) {
        this.branchItemLocationMapAuditLogService = branchItemLocationMapAuditLogService;
        this.branchItemLocationMapAuditLogRepository = branchItemLocationMapAuditLogRepository;
    }

    /**
     * {@code POST  /branch-item-location-map-audit-logs} : Create a new branchItemLocationMapAuditLog.
     *
     * @param branchItemLocationMapAuditLogDTO the branchItemLocationMapAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new branchItemLocationMapAuditLogDTO, or with status {@code 400 (Bad Request)} if the branchItemLocationMapAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/branch-item-location-map-audit-logs")
    public ResponseEntity<BranchItemLocationMapAuditLogDTO> createBranchItemLocationMapAuditLog(
        @RequestBody BranchItemLocationMapAuditLogDTO branchItemLocationMapAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to save BranchItemLocationMapAuditLog : {}", branchItemLocationMapAuditLogDTO);
        if (branchItemLocationMapAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new branchItemLocationMapAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BranchItemLocationMapAuditLogDTO result = branchItemLocationMapAuditLogService.save(branchItemLocationMapAuditLogDTO);
        return ResponseEntity
            .created(new URI("/api/branch-item-location-map-audit-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /branch-item-location-map-audit-logs/:id} : Updates an existing branchItemLocationMapAuditLog.
     *
     * @param id the id of the branchItemLocationMapAuditLogDTO to save.
     * @param branchItemLocationMapAuditLogDTO the branchItemLocationMapAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated branchItemLocationMapAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the branchItemLocationMapAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the branchItemLocationMapAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/branch-item-location-map-audit-logs/{id}")
    public ResponseEntity<BranchItemLocationMapAuditLogDTO> updateBranchItemLocationMapAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BranchItemLocationMapAuditLogDTO branchItemLocationMapAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update BranchItemLocationMapAuditLog : {}, {}", id, branchItemLocationMapAuditLogDTO);
        if (branchItemLocationMapAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, branchItemLocationMapAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!branchItemLocationMapAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BranchItemLocationMapAuditLogDTO result = branchItemLocationMapAuditLogService.update(branchItemLocationMapAuditLogDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, branchItemLocationMapAuditLogDTO.getId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /branch-item-location-map-audit-logs/:id} : Partial updates given fields of an existing branchItemLocationMapAuditLog, field will ignore if it is null
     *
     * @param id the id of the branchItemLocationMapAuditLogDTO to save.
     * @param branchItemLocationMapAuditLogDTO the branchItemLocationMapAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated branchItemLocationMapAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the branchItemLocationMapAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the branchItemLocationMapAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the branchItemLocationMapAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/branch-item-location-map-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BranchItemLocationMapAuditLogDTO> partialUpdateBranchItemLocationMapAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BranchItemLocationMapAuditLogDTO branchItemLocationMapAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update BranchItemLocationMapAuditLog partially : {}, {}", id, branchItemLocationMapAuditLogDTO);
        if (branchItemLocationMapAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, branchItemLocationMapAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!branchItemLocationMapAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BranchItemLocationMapAuditLogDTO> result = branchItemLocationMapAuditLogService.partialUpdate(
            branchItemLocationMapAuditLogDTO
        );

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, branchItemLocationMapAuditLogDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /branch-item-location-map-audit-logs} : get all the branchItemLocationMapAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of branchItemLocationMapAuditLogs in body.
     */
    @GetMapping("/branch-item-location-map-audit-logs")
    public ResponseEntity<List<BranchItemLocationMapAuditLogDTO>> getAllBranchItemLocationMapAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of BranchItemLocationMapAuditLogs");
        Page<BranchItemLocationMapAuditLogDTO> page = branchItemLocationMapAuditLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /branch-item-location-map-audit-logs/:id} : get the "id" branchItemLocationMapAuditLog.
     *
     * @param id the id of the branchItemLocationMapAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the branchItemLocationMapAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/branch-item-location-map-audit-logs/{id}")
    public ResponseEntity<BranchItemLocationMapAuditLogDTO> getBranchItemLocationMapAuditLog(@PathVariable Long id) {
        log.debug("REST request to get BranchItemLocationMapAuditLog : {}", id);
        Optional<BranchItemLocationMapAuditLogDTO> branchItemLocationMapAuditLogDTO = branchItemLocationMapAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(branchItemLocationMapAuditLogDTO);
    }

    /**
     * {@code DELETE  /branch-item-location-map-audit-logs/:id} : delete the "id" branchItemLocationMapAuditLog.
     *
     * @param id the id of the branchItemLocationMapAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/branch-item-location-map-audit-logs/{id}")
    public ResponseEntity<Void> deleteBranchItemLocationMapAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete BranchItemLocationMapAuditLog : {}", id);
        branchItemLocationMapAuditLogService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
