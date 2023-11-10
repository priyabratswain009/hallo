package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.PriceMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.PriceMasterAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.PriceMasterAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.PriceMasterAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class PriceMasterAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(PriceMasterAuditLogResource.class);

    private static final String ENTITY_NAME = "itemsPriceMasterAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PriceMasterAuditLogService priceMasterAuditLogService;

    private final PriceMasterAuditLogRepository priceMasterAuditLogRepository;

    public PriceMasterAuditLogResource(
        PriceMasterAuditLogService priceMasterAuditLogService,
        PriceMasterAuditLogRepository priceMasterAuditLogRepository
    ) {
        this.priceMasterAuditLogService = priceMasterAuditLogService;
        this.priceMasterAuditLogRepository = priceMasterAuditLogRepository;
    }

    /**
     * {@code POST  /price-master-audit-logs} : Create a new priceMasterAuditLog.
     *
     * @param priceMasterAuditLogDTO the priceMasterAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new priceMasterAuditLogDTO, or with status {@code 400 (Bad Request)} if the priceMasterAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/price-master-audit-logs")
    public ResponseEntity<PriceMasterAuditLogDTO> createPriceMasterAuditLog(@RequestBody PriceMasterAuditLogDTO priceMasterAuditLogDTO)
        throws URISyntaxException {
        log.debug("REST request to save PriceMasterAuditLog : {}", priceMasterAuditLogDTO);
        if (priceMasterAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new priceMasterAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PriceMasterAuditLogDTO result = priceMasterAuditLogService.save(priceMasterAuditLogDTO);
        return ResponseEntity
            .created(new URI("/api/price-master-audit-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /price-master-audit-logs/:id} : Updates an existing priceMasterAuditLog.
     *
     * @param id the id of the priceMasterAuditLogDTO to save.
     * @param priceMasterAuditLogDTO the priceMasterAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated priceMasterAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the priceMasterAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the priceMasterAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/price-master-audit-logs/{id}")
    public ResponseEntity<PriceMasterAuditLogDTO> updatePriceMasterAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PriceMasterAuditLogDTO priceMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PriceMasterAuditLog : {}, {}", id, priceMasterAuditLogDTO);
        if (priceMasterAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, priceMasterAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!priceMasterAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PriceMasterAuditLogDTO result = priceMasterAuditLogService.update(priceMasterAuditLogDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, priceMasterAuditLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /price-master-audit-logs/:id} : Partial updates given fields of an existing priceMasterAuditLog, field will ignore if it is null
     *
     * @param id the id of the priceMasterAuditLogDTO to save.
     * @param priceMasterAuditLogDTO the priceMasterAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated priceMasterAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the priceMasterAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the priceMasterAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the priceMasterAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/price-master-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PriceMasterAuditLogDTO> partialUpdatePriceMasterAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PriceMasterAuditLogDTO priceMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PriceMasterAuditLog partially : {}, {}", id, priceMasterAuditLogDTO);
        if (priceMasterAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, priceMasterAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!priceMasterAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PriceMasterAuditLogDTO> result = priceMasterAuditLogService.partialUpdate(priceMasterAuditLogDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, priceMasterAuditLogDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /price-master-audit-logs} : get all the priceMasterAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of priceMasterAuditLogs in body.
     */
    @GetMapping("/price-master-audit-logs")
    public ResponseEntity<List<PriceMasterAuditLogDTO>> getAllPriceMasterAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of PriceMasterAuditLogs");
        Page<PriceMasterAuditLogDTO> page = priceMasterAuditLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /price-master-audit-logs/:id} : get the "id" priceMasterAuditLog.
     *
     * @param id the id of the priceMasterAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the priceMasterAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/price-master-audit-logs/{id}")
    public ResponseEntity<PriceMasterAuditLogDTO> getPriceMasterAuditLog(@PathVariable Long id) {
        log.debug("REST request to get PriceMasterAuditLog : {}", id);
        Optional<PriceMasterAuditLogDTO> priceMasterAuditLogDTO = priceMasterAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(priceMasterAuditLogDTO);
    }

    /**
     * {@code DELETE  /price-master-audit-logs/:id} : delete the "id" priceMasterAuditLog.
     *
     * @param id the id of the priceMasterAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/price-master-audit-logs/{id}")
    public ResponseEntity<Void> deletePriceMasterAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete PriceMasterAuditLog : {}", id);
        priceMasterAuditLogService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
