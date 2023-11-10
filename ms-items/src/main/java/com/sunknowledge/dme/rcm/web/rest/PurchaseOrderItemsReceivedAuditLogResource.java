package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.PurchaseOrderItemsReceivedAuditLogRepository;
import com.sunknowledge.dme.rcm.service.PurchaseOrderItemsReceivedAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderItemsReceivedAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.PurchaseOrderItemsReceivedAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class PurchaseOrderItemsReceivedAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(PurchaseOrderItemsReceivedAuditLogResource.class);

    private static final String ENTITY_NAME = "itemsPurchaseOrderItemsReceivedAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PurchaseOrderItemsReceivedAuditLogService purchaseOrderItemsReceivedAuditLogService;

    private final PurchaseOrderItemsReceivedAuditLogRepository purchaseOrderItemsReceivedAuditLogRepository;

    public PurchaseOrderItemsReceivedAuditLogResource(
        PurchaseOrderItemsReceivedAuditLogService purchaseOrderItemsReceivedAuditLogService,
        PurchaseOrderItemsReceivedAuditLogRepository purchaseOrderItemsReceivedAuditLogRepository
    ) {
        this.purchaseOrderItemsReceivedAuditLogService = purchaseOrderItemsReceivedAuditLogService;
        this.purchaseOrderItemsReceivedAuditLogRepository = purchaseOrderItemsReceivedAuditLogRepository;
    }

    /**
     * {@code POST  /purchase-order-items-received-audit-logs} : Create a new purchaseOrderItemsReceivedAuditLog.
     *
     * @param purchaseOrderItemsReceivedAuditLogDTO the purchaseOrderItemsReceivedAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new purchaseOrderItemsReceivedAuditLogDTO, or with status {@code 400 (Bad Request)} if the purchaseOrderItemsReceivedAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/purchase-order-items-received-audit-logs")
    public ResponseEntity<PurchaseOrderItemsReceivedAuditLogDTO> createPurchaseOrderItemsReceivedAuditLog(
        @RequestBody PurchaseOrderItemsReceivedAuditLogDTO purchaseOrderItemsReceivedAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to save PurchaseOrderItemsReceivedAuditLog : {}", purchaseOrderItemsReceivedAuditLogDTO);
        if (purchaseOrderItemsReceivedAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException(
                "A new purchaseOrderItemsReceivedAuditLog cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        PurchaseOrderItemsReceivedAuditLogDTO result = purchaseOrderItemsReceivedAuditLogService.save(
            purchaseOrderItemsReceivedAuditLogDTO
        );
        return ResponseEntity
            .created(new URI("/api/purchase-order-items-received-audit-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /purchase-order-items-received-audit-logs/:id} : Updates an existing purchaseOrderItemsReceivedAuditLog.
     *
     * @param id the id of the purchaseOrderItemsReceivedAuditLogDTO to save.
     * @param purchaseOrderItemsReceivedAuditLogDTO the purchaseOrderItemsReceivedAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated purchaseOrderItemsReceivedAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the purchaseOrderItemsReceivedAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the purchaseOrderItemsReceivedAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/purchase-order-items-received-audit-logs/{id}")
    public ResponseEntity<PurchaseOrderItemsReceivedAuditLogDTO> updatePurchaseOrderItemsReceivedAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PurchaseOrderItemsReceivedAuditLogDTO purchaseOrderItemsReceivedAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PurchaseOrderItemsReceivedAuditLog : {}, {}", id, purchaseOrderItemsReceivedAuditLogDTO);
        if (purchaseOrderItemsReceivedAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, purchaseOrderItemsReceivedAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!purchaseOrderItemsReceivedAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PurchaseOrderItemsReceivedAuditLogDTO result = purchaseOrderItemsReceivedAuditLogService.update(
            purchaseOrderItemsReceivedAuditLogDTO
        );
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    purchaseOrderItemsReceivedAuditLogDTO.getId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /purchase-order-items-received-audit-logs/:id} : Partial updates given fields of an existing purchaseOrderItemsReceivedAuditLog, field will ignore if it is null
     *
     * @param id the id of the purchaseOrderItemsReceivedAuditLogDTO to save.
     * @param purchaseOrderItemsReceivedAuditLogDTO the purchaseOrderItemsReceivedAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated purchaseOrderItemsReceivedAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the purchaseOrderItemsReceivedAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the purchaseOrderItemsReceivedAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the purchaseOrderItemsReceivedAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/purchase-order-items-received-audit-logs/{id}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public ResponseEntity<PurchaseOrderItemsReceivedAuditLogDTO> partialUpdatePurchaseOrderItemsReceivedAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PurchaseOrderItemsReceivedAuditLogDTO purchaseOrderItemsReceivedAuditLogDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update PurchaseOrderItemsReceivedAuditLog partially : {}, {}",
            id,
            purchaseOrderItemsReceivedAuditLogDTO
        );
        if (purchaseOrderItemsReceivedAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, purchaseOrderItemsReceivedAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!purchaseOrderItemsReceivedAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PurchaseOrderItemsReceivedAuditLogDTO> result = purchaseOrderItemsReceivedAuditLogService.partialUpdate(
            purchaseOrderItemsReceivedAuditLogDTO
        );

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                false,
                ENTITY_NAME,
                purchaseOrderItemsReceivedAuditLogDTO.getId().toString()
            )
        );
    }

    /**
     * {@code GET  /purchase-order-items-received-audit-logs} : get all the purchaseOrderItemsReceivedAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of purchaseOrderItemsReceivedAuditLogs in body.
     */
    @GetMapping("/purchase-order-items-received-audit-logs")
    public ResponseEntity<List<PurchaseOrderItemsReceivedAuditLogDTO>> getAllPurchaseOrderItemsReceivedAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of PurchaseOrderItemsReceivedAuditLogs");
        Page<PurchaseOrderItemsReceivedAuditLogDTO> page = purchaseOrderItemsReceivedAuditLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /purchase-order-items-received-audit-logs/:id} : get the "id" purchaseOrderItemsReceivedAuditLog.
     *
     * @param id the id of the purchaseOrderItemsReceivedAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the purchaseOrderItemsReceivedAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/purchase-order-items-received-audit-logs/{id}")
    public ResponseEntity<PurchaseOrderItemsReceivedAuditLogDTO> getPurchaseOrderItemsReceivedAuditLog(@PathVariable Long id) {
        log.debug("REST request to get PurchaseOrderItemsReceivedAuditLog : {}", id);
        Optional<PurchaseOrderItemsReceivedAuditLogDTO> purchaseOrderItemsReceivedAuditLogDTO = purchaseOrderItemsReceivedAuditLogService.findOne(
            id
        );
        return ResponseUtil.wrapOrNotFound(purchaseOrderItemsReceivedAuditLogDTO);
    }

    /**
     * {@code DELETE  /purchase-order-items-received-audit-logs/:id} : delete the "id" purchaseOrderItemsReceivedAuditLog.
     *
     * @param id the id of the purchaseOrderItemsReceivedAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/purchase-order-items-received-audit-logs/{id}")
    public ResponseEntity<Void> deletePurchaseOrderItemsReceivedAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete PurchaseOrderItemsReceivedAuditLog : {}", id);
        purchaseOrderItemsReceivedAuditLogService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
