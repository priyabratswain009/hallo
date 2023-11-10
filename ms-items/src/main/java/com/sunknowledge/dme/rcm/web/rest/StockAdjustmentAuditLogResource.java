package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.StockAdjustmentAuditLogRepository;
import com.sunknowledge.dme.rcm.service.StockAdjustmentAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.StockAdjustmentAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.StockAdjustmentAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class StockAdjustmentAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(StockAdjustmentAuditLogResource.class);

    private static final String ENTITY_NAME = "itemsStockAdjustmentAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StockAdjustmentAuditLogService stockAdjustmentAuditLogService;

    private final StockAdjustmentAuditLogRepository stockAdjustmentAuditLogRepository;

    public StockAdjustmentAuditLogResource(
        StockAdjustmentAuditLogService stockAdjustmentAuditLogService,
        StockAdjustmentAuditLogRepository stockAdjustmentAuditLogRepository
    ) {
        this.stockAdjustmentAuditLogService = stockAdjustmentAuditLogService;
        this.stockAdjustmentAuditLogRepository = stockAdjustmentAuditLogRepository;
    }

    /**
     * {@code POST  /stock-adjustment-audit-logs} : Create a new stockAdjustmentAuditLog.
     *
     * @param stockAdjustmentAuditLogDTO the stockAdjustmentAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new stockAdjustmentAuditLogDTO, or with status {@code 400 (Bad Request)} if the stockAdjustmentAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/stock-adjustment-audit-logs")
    public ResponseEntity<StockAdjustmentAuditLogDTO> createStockAdjustmentAuditLog(
        @RequestBody StockAdjustmentAuditLogDTO stockAdjustmentAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to save StockAdjustmentAuditLog : {}", stockAdjustmentAuditLogDTO);
        if (stockAdjustmentAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new stockAdjustmentAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StockAdjustmentAuditLogDTO result = stockAdjustmentAuditLogService.save(stockAdjustmentAuditLogDTO);
        return ResponseEntity
            .created(new URI("/api/stock-adjustment-audit-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /stock-adjustment-audit-logs/:id} : Updates an existing stockAdjustmentAuditLog.
     *
     * @param id the id of the stockAdjustmentAuditLogDTO to save.
     * @param stockAdjustmentAuditLogDTO the stockAdjustmentAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated stockAdjustmentAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the stockAdjustmentAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the stockAdjustmentAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/stock-adjustment-audit-logs/{id}")
    public ResponseEntity<StockAdjustmentAuditLogDTO> updateStockAdjustmentAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody StockAdjustmentAuditLogDTO stockAdjustmentAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update StockAdjustmentAuditLog : {}, {}", id, stockAdjustmentAuditLogDTO);
        if (stockAdjustmentAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, stockAdjustmentAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!stockAdjustmentAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        StockAdjustmentAuditLogDTO result = stockAdjustmentAuditLogService.update(stockAdjustmentAuditLogDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, stockAdjustmentAuditLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /stock-adjustment-audit-logs/:id} : Partial updates given fields of an existing stockAdjustmentAuditLog, field will ignore if it is null
     *
     * @param id the id of the stockAdjustmentAuditLogDTO to save.
     * @param stockAdjustmentAuditLogDTO the stockAdjustmentAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated stockAdjustmentAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the stockAdjustmentAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the stockAdjustmentAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the stockAdjustmentAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/stock-adjustment-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<StockAdjustmentAuditLogDTO> partialUpdateStockAdjustmentAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody StockAdjustmentAuditLogDTO stockAdjustmentAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update StockAdjustmentAuditLog partially : {}, {}", id, stockAdjustmentAuditLogDTO);
        if (stockAdjustmentAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, stockAdjustmentAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!stockAdjustmentAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<StockAdjustmentAuditLogDTO> result = stockAdjustmentAuditLogService.partialUpdate(stockAdjustmentAuditLogDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, stockAdjustmentAuditLogDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /stock-adjustment-audit-logs} : get all the stockAdjustmentAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of stockAdjustmentAuditLogs in body.
     */
    @GetMapping("/stock-adjustment-audit-logs")
    public ResponseEntity<List<StockAdjustmentAuditLogDTO>> getAllStockAdjustmentAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of StockAdjustmentAuditLogs");
        Page<StockAdjustmentAuditLogDTO> page = stockAdjustmentAuditLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /stock-adjustment-audit-logs/:id} : get the "id" stockAdjustmentAuditLog.
     *
     * @param id the id of the stockAdjustmentAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the stockAdjustmentAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/stock-adjustment-audit-logs/{id}")
    public ResponseEntity<StockAdjustmentAuditLogDTO> getStockAdjustmentAuditLog(@PathVariable Long id) {
        log.debug("REST request to get StockAdjustmentAuditLog : {}", id);
        Optional<StockAdjustmentAuditLogDTO> stockAdjustmentAuditLogDTO = stockAdjustmentAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(stockAdjustmentAuditLogDTO);
    }

    /**
     * {@code DELETE  /stock-adjustment-audit-logs/:id} : delete the "id" stockAdjustmentAuditLog.
     *
     * @param id the id of the stockAdjustmentAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/stock-adjustment-audit-logs/{id}")
    public ResponseEntity<Void> deleteStockAdjustmentAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete StockAdjustmentAuditLog : {}", id);
        stockAdjustmentAuditLogService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
