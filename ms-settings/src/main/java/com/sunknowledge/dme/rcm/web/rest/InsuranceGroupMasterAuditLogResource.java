package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.InsuranceGroupMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.InsuranceGroupMasterAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.InsuranceGroupMasterAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.InsuranceGroupMasterAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class InsuranceGroupMasterAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(InsuranceGroupMasterAuditLogResource.class);

    private static final String ENTITY_NAME = "settingsInsuranceGroupMasterAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InsuranceGroupMasterAuditLogService insuranceGroupMasterAuditLogService;

    private final InsuranceGroupMasterAuditLogRepository insuranceGroupMasterAuditLogRepository;

    public InsuranceGroupMasterAuditLogResource(
        InsuranceGroupMasterAuditLogService insuranceGroupMasterAuditLogService,
        InsuranceGroupMasterAuditLogRepository insuranceGroupMasterAuditLogRepository
    ) {
        this.insuranceGroupMasterAuditLogService = insuranceGroupMasterAuditLogService;
        this.insuranceGroupMasterAuditLogRepository = insuranceGroupMasterAuditLogRepository;
    }

    /**
     * {@code POST  /insurance-group-master-audit-logs} : Create a new insuranceGroupMasterAuditLog.
     *
     * @param insuranceGroupMasterAuditLogDTO the insuranceGroupMasterAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new insuranceGroupMasterAuditLogDTO, or with status {@code 400 (Bad Request)} if the insuranceGroupMasterAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/insurance-group-master-audit-logs")
    public ResponseEntity<InsuranceGroupMasterAuditLogDTO> createInsuranceGroupMasterAuditLog(
        @RequestBody InsuranceGroupMasterAuditLogDTO insuranceGroupMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to save InsuranceGroupMasterAuditLog : {}", insuranceGroupMasterAuditLogDTO);
        if (insuranceGroupMasterAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new insuranceGroupMasterAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InsuranceGroupMasterAuditLogDTO result = insuranceGroupMasterAuditLogService.save(insuranceGroupMasterAuditLogDTO);
        return ResponseEntity
            .created(new URI("/api/insurance-group-master-audit-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /insurance-group-master-audit-logs/:id} : Updates an existing insuranceGroupMasterAuditLog.
     *
     * @param id the id of the insuranceGroupMasterAuditLogDTO to save.
     * @param insuranceGroupMasterAuditLogDTO the insuranceGroupMasterAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated insuranceGroupMasterAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the insuranceGroupMasterAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the insuranceGroupMasterAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/insurance-group-master-audit-logs/{id}")
    public ResponseEntity<InsuranceGroupMasterAuditLogDTO> updateInsuranceGroupMasterAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody InsuranceGroupMasterAuditLogDTO insuranceGroupMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update InsuranceGroupMasterAuditLog : {}, {}", id, insuranceGroupMasterAuditLogDTO);
        if (insuranceGroupMasterAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, insuranceGroupMasterAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!insuranceGroupMasterAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        InsuranceGroupMasterAuditLogDTO result = insuranceGroupMasterAuditLogService.update(insuranceGroupMasterAuditLogDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, insuranceGroupMasterAuditLogDTO.getId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /insurance-group-master-audit-logs/:id} : Partial updates given fields of an existing insuranceGroupMasterAuditLog, field will ignore if it is null
     *
     * @param id the id of the insuranceGroupMasterAuditLogDTO to save.
     * @param insuranceGroupMasterAuditLogDTO the insuranceGroupMasterAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated insuranceGroupMasterAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the insuranceGroupMasterAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the insuranceGroupMasterAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the insuranceGroupMasterAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/insurance-group-master-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<InsuranceGroupMasterAuditLogDTO> partialUpdateInsuranceGroupMasterAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody InsuranceGroupMasterAuditLogDTO insuranceGroupMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update InsuranceGroupMasterAuditLog partially : {}, {}", id, insuranceGroupMasterAuditLogDTO);
        if (insuranceGroupMasterAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, insuranceGroupMasterAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!insuranceGroupMasterAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<InsuranceGroupMasterAuditLogDTO> result = insuranceGroupMasterAuditLogService.partialUpdate(
            insuranceGroupMasterAuditLogDTO
        );

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, insuranceGroupMasterAuditLogDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /insurance-group-master-audit-logs} : get all the insuranceGroupMasterAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of insuranceGroupMasterAuditLogs in body.
     */
    @GetMapping("/insurance-group-master-audit-logs")
    public ResponseEntity<List<InsuranceGroupMasterAuditLogDTO>> getAllInsuranceGroupMasterAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of InsuranceGroupMasterAuditLogs");
        Page<InsuranceGroupMasterAuditLogDTO> page = insuranceGroupMasterAuditLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /insurance-group-master-audit-logs/:id} : get the "id" insuranceGroupMasterAuditLog.
     *
     * @param id the id of the insuranceGroupMasterAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the insuranceGroupMasterAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/insurance-group-master-audit-logs/{id}")
    public ResponseEntity<InsuranceGroupMasterAuditLogDTO> getInsuranceGroupMasterAuditLog(@PathVariable Long id) {
        log.debug("REST request to get InsuranceGroupMasterAuditLog : {}", id);
        Optional<InsuranceGroupMasterAuditLogDTO> insuranceGroupMasterAuditLogDTO = insuranceGroupMasterAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(insuranceGroupMasterAuditLogDTO);
    }

    /**
     * {@code DELETE  /insurance-group-master-audit-logs/:id} : delete the "id" insuranceGroupMasterAuditLog.
     *
     * @param id the id of the insuranceGroupMasterAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/insurance-group-master-audit-logs/{id}")
    public ResponseEntity<Void> deleteInsuranceGroupMasterAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete InsuranceGroupMasterAuditLog : {}", id);
        insuranceGroupMasterAuditLogService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
