package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.UspsAddressVerificationResponseAuditLogRepository;
import com.sunknowledge.dme.rcm.service.UspsAddressVerificationResponseAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.UspsAddressVerificationResponseAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.UspsAddressVerificationResponseAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class UspsAddressVerificationResponseAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(UspsAddressVerificationResponseAuditLogResource.class);

    private static final String ENTITY_NAME = "settingsUspsAddressVerificationResponseAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UspsAddressVerificationResponseAuditLogService uspsAddressVerificationResponseAuditLogService;

    private final UspsAddressVerificationResponseAuditLogRepository uspsAddressVerificationResponseAuditLogRepository;

    public UspsAddressVerificationResponseAuditLogResource(
        UspsAddressVerificationResponseAuditLogService uspsAddressVerificationResponseAuditLogService,
        UspsAddressVerificationResponseAuditLogRepository uspsAddressVerificationResponseAuditLogRepository
    ) {
        this.uspsAddressVerificationResponseAuditLogService = uspsAddressVerificationResponseAuditLogService;
        this.uspsAddressVerificationResponseAuditLogRepository = uspsAddressVerificationResponseAuditLogRepository;
    }

    /**
     * {@code POST  /usps-address-verification-response-audit-logs} : Create a new uspsAddressVerificationResponseAuditLog.
     *
     * @param uspsAddressVerificationResponseAuditLogDTO the uspsAddressVerificationResponseAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new uspsAddressVerificationResponseAuditLogDTO, or with status {@code 400 (Bad Request)} if the uspsAddressVerificationResponseAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/usps-address-verification-response-audit-logs")
    public ResponseEntity<UspsAddressVerificationResponseAuditLogDTO> createUspsAddressVerificationResponseAuditLog(
        @RequestBody UspsAddressVerificationResponseAuditLogDTO uspsAddressVerificationResponseAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to save UspsAddressVerificationResponseAuditLog : {}", uspsAddressVerificationResponseAuditLogDTO);
        if (uspsAddressVerificationResponseAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException(
                "A new uspsAddressVerificationResponseAuditLog cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        UspsAddressVerificationResponseAuditLogDTO result = uspsAddressVerificationResponseAuditLogService.save(
            uspsAddressVerificationResponseAuditLogDTO
        );
        return ResponseEntity
            .created(new URI("/api/usps-address-verification-response-audit-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /usps-address-verification-response-audit-logs/:id} : Updates an existing uspsAddressVerificationResponseAuditLog.
     *
     * @param id the id of the uspsAddressVerificationResponseAuditLogDTO to save.
     * @param uspsAddressVerificationResponseAuditLogDTO the uspsAddressVerificationResponseAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated uspsAddressVerificationResponseAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the uspsAddressVerificationResponseAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the uspsAddressVerificationResponseAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/usps-address-verification-response-audit-logs/{id}")
    public ResponseEntity<UspsAddressVerificationResponseAuditLogDTO> updateUspsAddressVerificationResponseAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody UspsAddressVerificationResponseAuditLogDTO uspsAddressVerificationResponseAuditLogDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to update UspsAddressVerificationResponseAuditLog : {}, {}",
            id,
            uspsAddressVerificationResponseAuditLogDTO
        );
        if (uspsAddressVerificationResponseAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, uspsAddressVerificationResponseAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!uspsAddressVerificationResponseAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        UspsAddressVerificationResponseAuditLogDTO result = uspsAddressVerificationResponseAuditLogService.update(
            uspsAddressVerificationResponseAuditLogDTO
        );
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    uspsAddressVerificationResponseAuditLogDTO.getId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /usps-address-verification-response-audit-logs/:id} : Partial updates given fields of an existing uspsAddressVerificationResponseAuditLog, field will ignore if it is null
     *
     * @param id the id of the uspsAddressVerificationResponseAuditLogDTO to save.
     * @param uspsAddressVerificationResponseAuditLogDTO the uspsAddressVerificationResponseAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated uspsAddressVerificationResponseAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the uspsAddressVerificationResponseAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the uspsAddressVerificationResponseAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the uspsAddressVerificationResponseAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/usps-address-verification-response-audit-logs/{id}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public ResponseEntity<UspsAddressVerificationResponseAuditLogDTO> partialUpdateUspsAddressVerificationResponseAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody UspsAddressVerificationResponseAuditLogDTO uspsAddressVerificationResponseAuditLogDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update UspsAddressVerificationResponseAuditLog partially : {}, {}",
            id,
            uspsAddressVerificationResponseAuditLogDTO
        );
        if (uspsAddressVerificationResponseAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, uspsAddressVerificationResponseAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!uspsAddressVerificationResponseAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<UspsAddressVerificationResponseAuditLogDTO> result = uspsAddressVerificationResponseAuditLogService.partialUpdate(
            uspsAddressVerificationResponseAuditLogDTO
        );

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                false,
                ENTITY_NAME,
                uspsAddressVerificationResponseAuditLogDTO.getId().toString()
            )
        );
    }

    /**
     * {@code GET  /usps-address-verification-response-audit-logs} : get all the uspsAddressVerificationResponseAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of uspsAddressVerificationResponseAuditLogs in body.
     */
    @GetMapping("/usps-address-verification-response-audit-logs")
    public ResponseEntity<List<UspsAddressVerificationResponseAuditLogDTO>> getAllUspsAddressVerificationResponseAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of UspsAddressVerificationResponseAuditLogs");
        Page<UspsAddressVerificationResponseAuditLogDTO> page = uspsAddressVerificationResponseAuditLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /usps-address-verification-response-audit-logs/:id} : get the "id" uspsAddressVerificationResponseAuditLog.
     *
     * @param id the id of the uspsAddressVerificationResponseAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the uspsAddressVerificationResponseAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/usps-address-verification-response-audit-logs/{id}")
    public ResponseEntity<UspsAddressVerificationResponseAuditLogDTO> getUspsAddressVerificationResponseAuditLog(@PathVariable Long id) {
        log.debug("REST request to get UspsAddressVerificationResponseAuditLog : {}", id);
        Optional<UspsAddressVerificationResponseAuditLogDTO> uspsAddressVerificationResponseAuditLogDTO = uspsAddressVerificationResponseAuditLogService.findOne(
            id
        );
        return ResponseUtil.wrapOrNotFound(uspsAddressVerificationResponseAuditLogDTO);
    }

    /**
     * {@code DELETE  /usps-address-verification-response-audit-logs/:id} : delete the "id" uspsAddressVerificationResponseAuditLog.
     *
     * @param id the id of the uspsAddressVerificationResponseAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/usps-address-verification-response-audit-logs/{id}")
    public ResponseEntity<Void> deleteUspsAddressVerificationResponseAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete UspsAddressVerificationResponseAuditLog : {}", id);
        uspsAddressVerificationResponseAuditLogService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
