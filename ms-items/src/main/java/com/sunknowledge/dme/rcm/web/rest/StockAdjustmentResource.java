package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.StockAdjustmentRepository;
import com.sunknowledge.dme.rcm.service.StockAdjustmentService;
import com.sunknowledge.dme.rcm.service.dto.StockAdjustmentDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.StockAdjustment}.
 */
@RestController
@RequestMapping("/api")
public class StockAdjustmentResource {

    private final Logger log = LoggerFactory.getLogger(StockAdjustmentResource.class);

    private static final String ENTITY_NAME = "itemsStockAdjustment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StockAdjustmentService stockAdjustmentService;

    private final StockAdjustmentRepository stockAdjustmentRepository;

    public StockAdjustmentResource(StockAdjustmentService stockAdjustmentService, StockAdjustmentRepository stockAdjustmentRepository) {
        this.stockAdjustmentService = stockAdjustmentService;
        this.stockAdjustmentRepository = stockAdjustmentRepository;
    }

    /**
     * {@code POST  /stock-adjustments} : Create a new stockAdjustment.
     *
     * @param stockAdjustmentDTO the stockAdjustmentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new stockAdjustmentDTO, or with status {@code 400 (Bad Request)} if the stockAdjustment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/stock-adjustments")
    public ResponseEntity<StockAdjustmentDTO> createStockAdjustment(@RequestBody StockAdjustmentDTO stockAdjustmentDTO)
        throws URISyntaxException {
        log.debug("REST request to save StockAdjustment : {}", stockAdjustmentDTO);
        if (stockAdjustmentDTO.getStockAdjustmentId() != null) {
            throw new BadRequestAlertException("A new stockAdjustment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StockAdjustmentDTO result = stockAdjustmentService.save(stockAdjustmentDTO);
        return ResponseEntity
            .created(new URI("/api/stock-adjustments/" + result.getStockAdjustmentId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getStockAdjustmentId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /stock-adjustments/:stockAdjustmentId} : Updates an existing stockAdjustment.
     *
     * @param stockAdjustmentId the id of the stockAdjustmentDTO to save.
     * @param stockAdjustmentDTO the stockAdjustmentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated stockAdjustmentDTO,
     * or with status {@code 400 (Bad Request)} if the stockAdjustmentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the stockAdjustmentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/stock-adjustments/{stockAdjustmentId}")
    public ResponseEntity<StockAdjustmentDTO> updateStockAdjustment(
        @PathVariable(value = "stockAdjustmentId", required = false) final Long stockAdjustmentId,
        @RequestBody StockAdjustmentDTO stockAdjustmentDTO
    ) throws URISyntaxException {
        log.debug("REST request to update StockAdjustment : {}, {}", stockAdjustmentId, stockAdjustmentDTO);
        if (stockAdjustmentDTO.getStockAdjustmentId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(stockAdjustmentId, stockAdjustmentDTO.getStockAdjustmentId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!stockAdjustmentRepository.existsById(stockAdjustmentId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        StockAdjustmentDTO result = stockAdjustmentService.update(stockAdjustmentDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    stockAdjustmentDTO.getStockAdjustmentId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /stock-adjustments/:stockAdjustmentId} : Partial updates given fields of an existing stockAdjustment, field will ignore if it is null
     *
     * @param stockAdjustmentId the id of the stockAdjustmentDTO to save.
     * @param stockAdjustmentDTO the stockAdjustmentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated stockAdjustmentDTO,
     * or with status {@code 400 (Bad Request)} if the stockAdjustmentDTO is not valid,
     * or with status {@code 404 (Not Found)} if the stockAdjustmentDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the stockAdjustmentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/stock-adjustments/{stockAdjustmentId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<StockAdjustmentDTO> partialUpdateStockAdjustment(
        @PathVariable(value = "stockAdjustmentId", required = false) final Long stockAdjustmentId,
        @RequestBody StockAdjustmentDTO stockAdjustmentDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update StockAdjustment partially : {}, {}", stockAdjustmentId, stockAdjustmentDTO);
        if (stockAdjustmentDTO.getStockAdjustmentId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(stockAdjustmentId, stockAdjustmentDTO.getStockAdjustmentId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!stockAdjustmentRepository.existsById(stockAdjustmentId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<StockAdjustmentDTO> result = stockAdjustmentService.partialUpdate(stockAdjustmentDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, stockAdjustmentDTO.getStockAdjustmentId().toString())
        );
    }

    /**
     * {@code GET  /stock-adjustments} : get all the stockAdjustments.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of stockAdjustments in body.
     */
    @GetMapping("/stock-adjustments")
    public ResponseEntity<List<StockAdjustmentDTO>> getAllStockAdjustments(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of StockAdjustments");
        Page<StockAdjustmentDTO> page = stockAdjustmentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /stock-adjustments/:id} : get the "id" stockAdjustment.
     *
     * @param id the id of the stockAdjustmentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the stockAdjustmentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/stock-adjustments/{id}")
    public ResponseEntity<StockAdjustmentDTO> getStockAdjustment(@PathVariable Long id) {
        log.debug("REST request to get StockAdjustment : {}", id);
        Optional<StockAdjustmentDTO> stockAdjustmentDTO = stockAdjustmentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(stockAdjustmentDTO);
    }

    /**
     * {@code DELETE  /stock-adjustments/:id} : delete the "id" stockAdjustment.
     *
     * @param id the id of the stockAdjustmentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/stock-adjustments/{id}")
    public ResponseEntity<Void> deleteStockAdjustment(@PathVariable Long id) {
        log.debug("REST request to delete StockAdjustment : {}", id);
        stockAdjustmentService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
