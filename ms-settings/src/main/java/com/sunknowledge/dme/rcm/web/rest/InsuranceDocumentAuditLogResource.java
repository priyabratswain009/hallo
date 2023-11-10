package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.InsuranceDocumentAuditLogRepository;
import com.sunknowledge.dme.rcm.service.InsuranceDocumentAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.InsuranceDocumentAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.InsuranceDocumentAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class InsuranceDocumentAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(InsuranceDocumentAuditLogResource.class);

    private static final String ENTITY_NAME = "settingsInsuranceDocumentAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InsuranceDocumentAuditLogService insuranceDocumentAuditLogService;

    private final InsuranceDocumentAuditLogRepository insuranceDocumentAuditLogRepository;

    public InsuranceDocumentAuditLogResource(
        InsuranceDocumentAuditLogService insuranceDocumentAuditLogService,
        InsuranceDocumentAuditLogRepository insuranceDocumentAuditLogRepository
    ) {
        this.insuranceDocumentAuditLogService = insuranceDocumentAuditLogService;
        this.insuranceDocumentAuditLogRepository = insuranceDocumentAuditLogRepository;
    }

    /**
     * {@code POST  /insurance-document-audit-logs} : Create a new insuranceDocumentAuditLog.
     *
     * @param insuranceDocumentAuditLogDTO the insuranceDocumentAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new insuranceDocumentAuditLogDTO, or with status {@code 400 (Bad Request)} if the insuranceDocumentAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/insurance-document-audit-logs")
    public ResponseEntity<InsuranceDocumentAuditLogDTO> createInsuranceDocumentAuditLog(
        @RequestBody InsuranceDocumentAuditLogDTO insuranceDocumentAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to save InsuranceDocumentAuditLog : {}", insuranceDocumentAuditLogDTO);
        if (insuranceDocumentAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new insuranceDocumentAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InsuranceDocumentAuditLogDTO result = insuranceDocumentAuditLogService.save(insuranceDocumentAuditLogDTO);
        return ResponseEntity
            .created(new URI("/api/insurance-document-audit-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /insurance-document-audit-logs/:id} : Updates an existing insuranceDocumentAuditLog.
     *
     * @param id the id of the insuranceDocumentAuditLogDTO to save.
     * @param insuranceDocumentAuditLogDTO the insuranceDocumentAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated insuranceDocumentAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the insuranceDocumentAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the insuranceDocumentAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/insurance-document-audit-logs/{id}")
    public ResponseEntity<InsuranceDocumentAuditLogDTO> updateInsuranceDocumentAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody InsuranceDocumentAuditLogDTO insuranceDocumentAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update InsuranceDocumentAuditLog : {}, {}", id, insuranceDocumentAuditLogDTO);
        if (insuranceDocumentAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, insuranceDocumentAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!insuranceDocumentAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        InsuranceDocumentAuditLogDTO result = insuranceDocumentAuditLogService.update(insuranceDocumentAuditLogDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, insuranceDocumentAuditLogDTO.getId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /insurance-document-audit-logs/:id} : Partial updates given fields of an existing insuranceDocumentAuditLog, field will ignore if it is null
     *
     * @param id the id of the insuranceDocumentAuditLogDTO to save.
     * @param insuranceDocumentAuditLogDTO the insuranceDocumentAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated insuranceDocumentAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the insuranceDocumentAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the insuranceDocumentAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the insuranceDocumentAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/insurance-document-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<InsuranceDocumentAuditLogDTO> partialUpdateInsuranceDocumentAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody InsuranceDocumentAuditLogDTO insuranceDocumentAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update InsuranceDocumentAuditLog partially : {}, {}", id, insuranceDocumentAuditLogDTO);
        if (insuranceDocumentAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, insuranceDocumentAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!insuranceDocumentAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<InsuranceDocumentAuditLogDTO> result = insuranceDocumentAuditLogService.partialUpdate(insuranceDocumentAuditLogDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, insuranceDocumentAuditLogDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /insurance-document-audit-logs} : get all the insuranceDocumentAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of insuranceDocumentAuditLogs in body.
     */
    @GetMapping("/insurance-document-audit-logs")
    public ResponseEntity<List<InsuranceDocumentAuditLogDTO>> getAllInsuranceDocumentAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of InsuranceDocumentAuditLogs");
        Page<InsuranceDocumentAuditLogDTO> page = insuranceDocumentAuditLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /insurance-document-audit-logs/:id} : get the "id" insuranceDocumentAuditLog.
     *
     * @param id the id of the insuranceDocumentAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the insuranceDocumentAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/insurance-document-audit-logs/{id}")
    public ResponseEntity<InsuranceDocumentAuditLogDTO> getInsuranceDocumentAuditLog(@PathVariable Long id) {
        log.debug("REST request to get InsuranceDocumentAuditLog : {}", id);
        Optional<InsuranceDocumentAuditLogDTO> insuranceDocumentAuditLogDTO = insuranceDocumentAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(insuranceDocumentAuditLogDTO);
    }

    /**
     * {@code DELETE  /insurance-document-audit-logs/:id} : delete the "id" insuranceDocumentAuditLog.
     *
     * @param id the id of the insuranceDocumentAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/insurance-document-audit-logs/{id}")
    public ResponseEntity<Void> deleteInsuranceDocumentAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete InsuranceDocumentAuditLog : {}", id);
        insuranceDocumentAuditLogService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
