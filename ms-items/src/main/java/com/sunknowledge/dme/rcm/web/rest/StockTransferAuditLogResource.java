package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.StockTransferAuditLogRepository;
import com.sunknowledge.dme.rcm.service.StockTransferAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.StockTransferAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.StockTransferAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class StockTransferAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(StockTransferAuditLogResource.class);

    private static final String ENTITY_NAME = "itemsStockTransferAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StockTransferAuditLogService stockTransferAuditLogService;

    private final StockTransferAuditLogRepository stockTransferAuditLogRepository;

    public StockTransferAuditLogResource(
        StockTransferAuditLogService stockTransferAuditLogService,
        StockTransferAuditLogRepository stockTransferAuditLogRepository
    ) {
        this.stockTransferAuditLogService = stockTransferAuditLogService;
        this.stockTransferAuditLogRepository = stockTransferAuditLogRepository;
    }

    /**
     * {@code POST  /stock-transfer-audit-logs} : Create a new stockTransferAuditLog.
     *
     * @param stockTransferAuditLogDTO the stockTransferAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new stockTransferAuditLogDTO, or with status {@code 400 (Bad Request)} if the stockTransferAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/stock-transfer-audit-logs")
    public ResponseEntity<StockTransferAuditLogDTO> createStockTransferAuditLog(
        @RequestBody StockTransferAuditLogDTO stockTransferAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to save StockTransferAuditLog : {}", stockTransferAuditLogDTO);
        if (stockTransferAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new stockTransferAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StockTransferAuditLogDTO result = stockTransferAuditLogService.save(stockTransferAuditLogDTO);
        return ResponseEntity
            .created(new URI("/api/stock-transfer-audit-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /stock-transfer-audit-logs/:id} : Updates an existing stockTransferAuditLog.
     *
     * @param id the id of the stockTransferAuditLogDTO to save.
     * @param stockTransferAuditLogDTO the stockTransferAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated stockTransferAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the stockTransferAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the stockTransferAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/stock-transfer-audit-logs/{id}")
    public ResponseEntity<StockTransferAuditLogDTO> updateStockTransferAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody StockTransferAuditLogDTO stockTransferAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update StockTransferAuditLog : {}, {}", id, stockTransferAuditLogDTO);
        if (stockTransferAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, stockTransferAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!stockTransferAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        StockTransferAuditLogDTO result = stockTransferAuditLogService.update(stockTransferAuditLogDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, stockTransferAuditLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /stock-transfer-audit-logs/:id} : Partial updates given fields of an existing stockTransferAuditLog, field will ignore if it is null
     *
     * @param id the id of the stockTransferAuditLogDTO to save.
     * @param stockTransferAuditLogDTO the stockTransferAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated stockTransferAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the stockTransferAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the stockTransferAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the stockTransferAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/stock-transfer-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<StockTransferAuditLogDTO> partialUpdateStockTransferAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody StockTransferAuditLogDTO stockTransferAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update StockTransferAuditLog partially : {}, {}", id, stockTransferAuditLogDTO);
        if (stockTransferAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, stockTransferAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!stockTransferAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<StockTransferAuditLogDTO> result = stockTransferAuditLogService.partialUpdate(stockTransferAuditLogDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, stockTransferAuditLogDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /stock-transfer-audit-logs} : get all the stockTransferAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of stockTransferAuditLogs in body.
     */
    @GetMapping("/stock-transfer-audit-logs")
    public ResponseEntity<List<StockTransferAuditLogDTO>> getAllStockTransferAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of StockTransferAuditLogs");
        Page<StockTransferAuditLogDTO> page = stockTransferAuditLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /stock-transfer-audit-logs/:id} : get the "id" stockTransferAuditLog.
     *
     * @param id the id of the stockTransferAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the stockTransferAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/stock-transfer-audit-logs/{id}")
    public ResponseEntity<StockTransferAuditLogDTO> getStockTransferAuditLog(@PathVariable Long id) {
        log.debug("REST request to get StockTransferAuditLog : {}", id);
        Optional<StockTransferAuditLogDTO> stockTransferAuditLogDTO = stockTransferAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(stockTransferAuditLogDTO);
    }

    /**
     * {@code DELETE  /stock-transfer-audit-logs/:id} : delete the "id" stockTransferAuditLog.
     *
     * @param id the id of the stockTransferAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/stock-transfer-audit-logs/{id}")
    public ResponseEntity<Void> deleteStockTransferAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete StockTransferAuditLog : {}", id);
        stockTransferAuditLogService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
