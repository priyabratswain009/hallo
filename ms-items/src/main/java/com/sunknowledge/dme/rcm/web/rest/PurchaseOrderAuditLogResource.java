package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.PurchaseOrderAuditLogRepository;
import com.sunknowledge.dme.rcm.service.PurchaseOrderAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.PurchaseOrderAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class PurchaseOrderAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(PurchaseOrderAuditLogResource.class);

    private static final String ENTITY_NAME = "itemsPurchaseOrderAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PurchaseOrderAuditLogService purchaseOrderAuditLogService;

    private final PurchaseOrderAuditLogRepository purchaseOrderAuditLogRepository;

    public PurchaseOrderAuditLogResource(
        PurchaseOrderAuditLogService purchaseOrderAuditLogService,
        PurchaseOrderAuditLogRepository purchaseOrderAuditLogRepository
    ) {
        this.purchaseOrderAuditLogService = purchaseOrderAuditLogService;
        this.purchaseOrderAuditLogRepository = purchaseOrderAuditLogRepository;
    }

    /**
     * {@code POST  /purchase-order-audit-logs} : Create a new purchaseOrderAuditLog.
     *
     * @param purchaseOrderAuditLogDTO the purchaseOrderAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new purchaseOrderAuditLogDTO, or with status {@code 400 (Bad Request)} if the purchaseOrderAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/purchase-order-audit-logs")
    public ResponseEntity<PurchaseOrderAuditLogDTO> createPurchaseOrderAuditLog(
        @RequestBody PurchaseOrderAuditLogDTO purchaseOrderAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to save PurchaseOrderAuditLog : {}", purchaseOrderAuditLogDTO);
        if (purchaseOrderAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new purchaseOrderAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PurchaseOrderAuditLogDTO result = purchaseOrderAuditLogService.save(purchaseOrderAuditLogDTO);
        return ResponseEntity
            .created(new URI("/api/purchase-order-audit-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /purchase-order-audit-logs/:id} : Updates an existing purchaseOrderAuditLog.
     *
     * @param id the id of the purchaseOrderAuditLogDTO to save.
     * @param purchaseOrderAuditLogDTO the purchaseOrderAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated purchaseOrderAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the purchaseOrderAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the purchaseOrderAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/purchase-order-audit-logs/{id}")
    public ResponseEntity<PurchaseOrderAuditLogDTO> updatePurchaseOrderAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PurchaseOrderAuditLogDTO purchaseOrderAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PurchaseOrderAuditLog : {}, {}", id, purchaseOrderAuditLogDTO);
        if (purchaseOrderAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, purchaseOrderAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!purchaseOrderAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PurchaseOrderAuditLogDTO result = purchaseOrderAuditLogService.update(purchaseOrderAuditLogDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, purchaseOrderAuditLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /purchase-order-audit-logs/:id} : Partial updates given fields of an existing purchaseOrderAuditLog, field will ignore if it is null
     *
     * @param id the id of the purchaseOrderAuditLogDTO to save.
     * @param purchaseOrderAuditLogDTO the purchaseOrderAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated purchaseOrderAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the purchaseOrderAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the purchaseOrderAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the purchaseOrderAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/purchase-order-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PurchaseOrderAuditLogDTO> partialUpdatePurchaseOrderAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PurchaseOrderAuditLogDTO purchaseOrderAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PurchaseOrderAuditLog partially : {}, {}", id, purchaseOrderAuditLogDTO);
        if (purchaseOrderAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, purchaseOrderAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!purchaseOrderAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PurchaseOrderAuditLogDTO> result = purchaseOrderAuditLogService.partialUpdate(purchaseOrderAuditLogDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, purchaseOrderAuditLogDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /purchase-order-audit-logs} : get all the purchaseOrderAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of purchaseOrderAuditLogs in body.
     */
    @GetMapping("/purchase-order-audit-logs")
    public ResponseEntity<List<PurchaseOrderAuditLogDTO>> getAllPurchaseOrderAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of PurchaseOrderAuditLogs");
        Page<PurchaseOrderAuditLogDTO> page = purchaseOrderAuditLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /purchase-order-audit-logs/:id} : get the "id" purchaseOrderAuditLog.
     *
     * @param id the id of the purchaseOrderAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the purchaseOrderAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/purchase-order-audit-logs/{id}")
    public ResponseEntity<PurchaseOrderAuditLogDTO> getPurchaseOrderAuditLog(@PathVariable Long id) {
        log.debug("REST request to get PurchaseOrderAuditLog : {}", id);
        Optional<PurchaseOrderAuditLogDTO> purchaseOrderAuditLogDTO = purchaseOrderAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(purchaseOrderAuditLogDTO);
    }

    /**
     * {@code DELETE  /purchase-order-audit-logs/:id} : delete the "id" purchaseOrderAuditLog.
     *
     * @param id the id of the purchaseOrderAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/purchase-order-audit-logs/{id}")
    public ResponseEntity<Void> deletePurchaseOrderAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete PurchaseOrderAuditLog : {}", id);
        purchaseOrderAuditLogService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
