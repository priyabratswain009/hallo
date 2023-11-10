package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.InsuranceMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.InsuranceMasterAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.InsuranceMasterAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.InsuranceMasterAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class InsuranceMasterAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(InsuranceMasterAuditLogResource.class);

    private static final String ENTITY_NAME = "settingsInsuranceMasterAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InsuranceMasterAuditLogService insuranceMasterAuditLogService;

    private final InsuranceMasterAuditLogRepository insuranceMasterAuditLogRepository;

    public InsuranceMasterAuditLogResource(
        InsuranceMasterAuditLogService insuranceMasterAuditLogService,
        InsuranceMasterAuditLogRepository insuranceMasterAuditLogRepository
    ) {
        this.insuranceMasterAuditLogService = insuranceMasterAuditLogService;
        this.insuranceMasterAuditLogRepository = insuranceMasterAuditLogRepository;
    }

    /**
     * {@code POST  /insurance-master-audit-logs} : Create a new insuranceMasterAuditLog.
     *
     * @param insuranceMasterAuditLogDTO the insuranceMasterAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new insuranceMasterAuditLogDTO, or with status {@code 400 (Bad Request)} if the insuranceMasterAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/insurance-master-audit-logs")
    public ResponseEntity<InsuranceMasterAuditLogDTO> createInsuranceMasterAuditLog(
        @RequestBody InsuranceMasterAuditLogDTO insuranceMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to save InsuranceMasterAuditLog : {}", insuranceMasterAuditLogDTO);
        if (insuranceMasterAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new insuranceMasterAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InsuranceMasterAuditLogDTO result = insuranceMasterAuditLogService.save(insuranceMasterAuditLogDTO);
        return ResponseEntity
            .created(new URI("/api/insurance-master-audit-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /insurance-master-audit-logs/:id} : Updates an existing insuranceMasterAuditLog.
     *
     * @param id the id of the insuranceMasterAuditLogDTO to save.
     * @param insuranceMasterAuditLogDTO the insuranceMasterAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated insuranceMasterAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the insuranceMasterAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the insuranceMasterAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/insurance-master-audit-logs/{id}")
    public ResponseEntity<InsuranceMasterAuditLogDTO> updateInsuranceMasterAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody InsuranceMasterAuditLogDTO insuranceMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update InsuranceMasterAuditLog : {}, {}", id, insuranceMasterAuditLogDTO);
        if (insuranceMasterAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, insuranceMasterAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!insuranceMasterAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        InsuranceMasterAuditLogDTO result = insuranceMasterAuditLogService.update(insuranceMasterAuditLogDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, insuranceMasterAuditLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /insurance-master-audit-logs/:id} : Partial updates given fields of an existing insuranceMasterAuditLog, field will ignore if it is null
     *
     * @param id the id of the insuranceMasterAuditLogDTO to save.
     * @param insuranceMasterAuditLogDTO the insuranceMasterAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated insuranceMasterAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the insuranceMasterAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the insuranceMasterAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the insuranceMasterAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/insurance-master-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<InsuranceMasterAuditLogDTO> partialUpdateInsuranceMasterAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody InsuranceMasterAuditLogDTO insuranceMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update InsuranceMasterAuditLog partially : {}, {}", id, insuranceMasterAuditLogDTO);
        if (insuranceMasterAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, insuranceMasterAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!insuranceMasterAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<InsuranceMasterAuditLogDTO> result = insuranceMasterAuditLogService.partialUpdate(insuranceMasterAuditLogDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, insuranceMasterAuditLogDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /insurance-master-audit-logs} : get all the insuranceMasterAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of insuranceMasterAuditLogs in body.
     */
    @GetMapping("/insurance-master-audit-logs")
    public ResponseEntity<List<InsuranceMasterAuditLogDTO>> getAllInsuranceMasterAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of InsuranceMasterAuditLogs");
        Page<InsuranceMasterAuditLogDTO> page = insuranceMasterAuditLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /insurance-master-audit-logs/:id} : get the "id" insuranceMasterAuditLog.
     *
     * @param id the id of the insuranceMasterAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the insuranceMasterAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/insurance-master-audit-logs/{id}")
    public ResponseEntity<InsuranceMasterAuditLogDTO> getInsuranceMasterAuditLog(@PathVariable Long id) {
        log.debug("REST request to get InsuranceMasterAuditLog : {}", id);
        Optional<InsuranceMasterAuditLogDTO> insuranceMasterAuditLogDTO = insuranceMasterAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(insuranceMasterAuditLogDTO);
    }

    /**
     * {@code DELETE  /insurance-master-audit-logs/:id} : delete the "id" insuranceMasterAuditLog.
     *
     * @param id the id of the insuranceMasterAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/insurance-master-audit-logs/{id}")
    public ResponseEntity<Void> deleteInsuranceMasterAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete InsuranceMasterAuditLog : {}", id);
        insuranceMasterAuditLogService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
