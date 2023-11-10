package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.PosMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.PosMasterAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.PosMasterAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.PosMasterAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class PosMasterAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(PosMasterAuditLogResource.class);

    private static final String ENTITY_NAME = "settingsPosMasterAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PosMasterAuditLogService posMasterAuditLogService;

    private final PosMasterAuditLogRepository posMasterAuditLogRepository;

    public PosMasterAuditLogResource(
        PosMasterAuditLogService posMasterAuditLogService,
        PosMasterAuditLogRepository posMasterAuditLogRepository
    ) {
        this.posMasterAuditLogService = posMasterAuditLogService;
        this.posMasterAuditLogRepository = posMasterAuditLogRepository;
    }

    /**
     * {@code POST  /pos-master-audit-logs} : Create a new posMasterAuditLog.
     *
     * @param posMasterAuditLogDTO the posMasterAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new posMasterAuditLogDTO, or with status {@code 400 (Bad Request)} if the posMasterAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pos-master-audit-logs")
    public ResponseEntity<PosMasterAuditLogDTO> createPosMasterAuditLog(@RequestBody PosMasterAuditLogDTO posMasterAuditLogDTO)
        throws URISyntaxException {
        log.debug("REST request to save PosMasterAuditLog : {}", posMasterAuditLogDTO);
        if (posMasterAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new posMasterAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PosMasterAuditLogDTO result = posMasterAuditLogService.save(posMasterAuditLogDTO);
        return ResponseEntity
            .created(new URI("/api/pos-master-audit-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pos-master-audit-logs/:id} : Updates an existing posMasterAuditLog.
     *
     * @param id the id of the posMasterAuditLogDTO to save.
     * @param posMasterAuditLogDTO the posMasterAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated posMasterAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the posMasterAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the posMasterAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pos-master-audit-logs/{id}")
    public ResponseEntity<PosMasterAuditLogDTO> updatePosMasterAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PosMasterAuditLogDTO posMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PosMasterAuditLog : {}, {}", id, posMasterAuditLogDTO);
        if (posMasterAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, posMasterAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!posMasterAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PosMasterAuditLogDTO result = posMasterAuditLogService.update(posMasterAuditLogDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, posMasterAuditLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /pos-master-audit-logs/:id} : Partial updates given fields of an existing posMasterAuditLog, field will ignore if it is null
     *
     * @param id the id of the posMasterAuditLogDTO to save.
     * @param posMasterAuditLogDTO the posMasterAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated posMasterAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the posMasterAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the posMasterAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the posMasterAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/pos-master-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PosMasterAuditLogDTO> partialUpdatePosMasterAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PosMasterAuditLogDTO posMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PosMasterAuditLog partially : {}, {}", id, posMasterAuditLogDTO);
        if (posMasterAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, posMasterAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!posMasterAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PosMasterAuditLogDTO> result = posMasterAuditLogService.partialUpdate(posMasterAuditLogDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, posMasterAuditLogDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /pos-master-audit-logs} : get all the posMasterAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of posMasterAuditLogs in body.
     */
    @GetMapping("/pos-master-audit-logs")
    public ResponseEntity<List<PosMasterAuditLogDTO>> getAllPosMasterAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of PosMasterAuditLogs");
        Page<PosMasterAuditLogDTO> page = posMasterAuditLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /pos-master-audit-logs/:id} : get the "id" posMasterAuditLog.
     *
     * @param id the id of the posMasterAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the posMasterAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pos-master-audit-logs/{id}")
    public ResponseEntity<PosMasterAuditLogDTO> getPosMasterAuditLog(@PathVariable Long id) {
        log.debug("REST request to get PosMasterAuditLog : {}", id);
        Optional<PosMasterAuditLogDTO> posMasterAuditLogDTO = posMasterAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(posMasterAuditLogDTO);
    }

    /**
     * {@code DELETE  /pos-master-audit-logs/:id} : delete the "id" posMasterAuditLog.
     *
     * @param id the id of the posMasterAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pos-master-audit-logs/{id}")
    public ResponseEntity<Void> deletePosMasterAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete PosMasterAuditLog : {}", id);
        posMasterAuditLogService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
