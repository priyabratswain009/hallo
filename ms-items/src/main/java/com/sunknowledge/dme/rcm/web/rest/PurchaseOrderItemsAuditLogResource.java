package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.PurchaseOrderItemsAuditLogRepository;
import com.sunknowledge.dme.rcm.service.PurchaseOrderItemsAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderItemsAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.PurchaseOrderItemsAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class PurchaseOrderItemsAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(PurchaseOrderItemsAuditLogResource.class);

    private static final String ENTITY_NAME = "itemsPurchaseOrderItemsAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PurchaseOrderItemsAuditLogService purchaseOrderItemsAuditLogService;

    private final PurchaseOrderItemsAuditLogRepository purchaseOrderItemsAuditLogRepository;

    public PurchaseOrderItemsAuditLogResource(
        PurchaseOrderItemsAuditLogService purchaseOrderItemsAuditLogService,
        PurchaseOrderItemsAuditLogRepository purchaseOrderItemsAuditLogRepository
    ) {
        this.purchaseOrderItemsAuditLogService = purchaseOrderItemsAuditLogService;
        this.purchaseOrderItemsAuditLogRepository = purchaseOrderItemsAuditLogRepository;
    }

    /**
     * {@code POST  /purchase-order-items-audit-logs} : Create a new purchaseOrderItemsAuditLog.
     *
     * @param purchaseOrderItemsAuditLogDTO the purchaseOrderItemsAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new purchaseOrderItemsAuditLogDTO, or with status {@code 400 (Bad Request)} if the purchaseOrderItemsAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/purchase-order-items-audit-logs")
    public ResponseEntity<PurchaseOrderItemsAuditLogDTO> createPurchaseOrderItemsAuditLog(
        @RequestBody PurchaseOrderItemsAuditLogDTO purchaseOrderItemsAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to save PurchaseOrderItemsAuditLog : {}", purchaseOrderItemsAuditLogDTO);
        if (purchaseOrderItemsAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new purchaseOrderItemsAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PurchaseOrderItemsAuditLogDTO result = purchaseOrderItemsAuditLogService.save(purchaseOrderItemsAuditLogDTO);
        return ResponseEntity
            .created(new URI("/api/purchase-order-items-audit-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /purchase-order-items-audit-logs/:id} : Updates an existing purchaseOrderItemsAuditLog.
     *
     * @param id the id of the purchaseOrderItemsAuditLogDTO to save.
     * @param purchaseOrderItemsAuditLogDTO the purchaseOrderItemsAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated purchaseOrderItemsAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the purchaseOrderItemsAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the purchaseOrderItemsAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/purchase-order-items-audit-logs/{id}")
    public ResponseEntity<PurchaseOrderItemsAuditLogDTO> updatePurchaseOrderItemsAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PurchaseOrderItemsAuditLogDTO purchaseOrderItemsAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PurchaseOrderItemsAuditLog : {}, {}", id, purchaseOrderItemsAuditLogDTO);
        if (purchaseOrderItemsAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, purchaseOrderItemsAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!purchaseOrderItemsAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PurchaseOrderItemsAuditLogDTO result = purchaseOrderItemsAuditLogService.update(purchaseOrderItemsAuditLogDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, purchaseOrderItemsAuditLogDTO.getId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /purchase-order-items-audit-logs/:id} : Partial updates given fields of an existing purchaseOrderItemsAuditLog, field will ignore if it is null
     *
     * @param id the id of the purchaseOrderItemsAuditLogDTO to save.
     * @param purchaseOrderItemsAuditLogDTO the purchaseOrderItemsAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated purchaseOrderItemsAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the purchaseOrderItemsAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the purchaseOrderItemsAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the purchaseOrderItemsAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/purchase-order-items-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PurchaseOrderItemsAuditLogDTO> partialUpdatePurchaseOrderItemsAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PurchaseOrderItemsAuditLogDTO purchaseOrderItemsAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PurchaseOrderItemsAuditLog partially : {}, {}", id, purchaseOrderItemsAuditLogDTO);
        if (purchaseOrderItemsAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, purchaseOrderItemsAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!purchaseOrderItemsAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PurchaseOrderItemsAuditLogDTO> result = purchaseOrderItemsAuditLogService.partialUpdate(purchaseOrderItemsAuditLogDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, purchaseOrderItemsAuditLogDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /purchase-order-items-audit-logs} : get all the purchaseOrderItemsAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of purchaseOrderItemsAuditLogs in body.
     */
    @GetMapping("/purchase-order-items-audit-logs")
    public ResponseEntity<List<PurchaseOrderItemsAuditLogDTO>> getAllPurchaseOrderItemsAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of PurchaseOrderItemsAuditLogs");
        Page<PurchaseOrderItemsAuditLogDTO> page = purchaseOrderItemsAuditLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /purchase-order-items-audit-logs/:id} : get the "id" purchaseOrderItemsAuditLog.
     *
     * @param id the id of the purchaseOrderItemsAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the purchaseOrderItemsAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/purchase-order-items-audit-logs/{id}")
    public ResponseEntity<PurchaseOrderItemsAuditLogDTO> getPurchaseOrderItemsAuditLog(@PathVariable Long id) {
        log.debug("REST request to get PurchaseOrderItemsAuditLog : {}", id);
        Optional<PurchaseOrderItemsAuditLogDTO> purchaseOrderItemsAuditLogDTO = purchaseOrderItemsAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(purchaseOrderItemsAuditLogDTO);
    }

    /**
     * {@code DELETE  /purchase-order-items-audit-logs/:id} : delete the "id" purchaseOrderItemsAuditLog.
     *
     * @param id the id of the purchaseOrderItemsAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/purchase-order-items-audit-logs/{id}")
    public ResponseEntity<Void> deletePurchaseOrderItemsAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete PurchaseOrderItemsAuditLog : {}", id);
        purchaseOrderItemsAuditLogService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
