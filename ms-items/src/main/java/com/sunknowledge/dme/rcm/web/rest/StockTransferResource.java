package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.StockTransferRepository;
import com.sunknowledge.dme.rcm.service.StockTransferService;
import com.sunknowledge.dme.rcm.service.dto.StockTransferDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.StockTransfer}.
 */
@RestController
@RequestMapping("/api")
public class StockTransferResource {

    private final Logger log = LoggerFactory.getLogger(StockTransferResource.class);

    private static final String ENTITY_NAME = "itemsStockTransfer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StockTransferService stockTransferService;

    private final StockTransferRepository stockTransferRepository;

    public StockTransferResource(StockTransferService stockTransferService, StockTransferRepository stockTransferRepository) {
        this.stockTransferService = stockTransferService;
        this.stockTransferRepository = stockTransferRepository;
    }

    /**
     * {@code POST  /stock-transfers} : Create a new stockTransfer.
     *
     * @param stockTransferDTO the stockTransferDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new stockTransferDTO, or with status {@code 400 (Bad Request)} if the stockTransfer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/stock-transfers")
    public ResponseEntity<StockTransferDTO> createStockTransfer(@RequestBody StockTransferDTO stockTransferDTO) throws URISyntaxException {
        log.debug("REST request to save StockTransfer : {}", stockTransferDTO);
        if (stockTransferDTO.getStockTransferId() != null) {
            throw new BadRequestAlertException("A new stockTransfer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StockTransferDTO result = stockTransferService.save(stockTransferDTO);
        return ResponseEntity
            .created(new URI("/api/stock-transfers/" + result.getStockTransferId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getStockTransferId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /stock-transfers/:stockTransferId} : Updates an existing stockTransfer.
     *
     * @param stockTransferId the id of the stockTransferDTO to save.
     * @param stockTransferDTO the stockTransferDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated stockTransferDTO,
     * or with status {@code 400 (Bad Request)} if the stockTransferDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the stockTransferDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/stock-transfers/{stockTransferId}")
    public ResponseEntity<StockTransferDTO> updateStockTransfer(
        @PathVariable(value = "stockTransferId", required = false) final Long stockTransferId,
        @RequestBody StockTransferDTO stockTransferDTO
    ) throws URISyntaxException {
        log.debug("REST request to update StockTransfer : {}, {}", stockTransferId, stockTransferDTO);
        if (stockTransferDTO.getStockTransferId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(stockTransferId, stockTransferDTO.getStockTransferId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!stockTransferRepository.existsById(stockTransferId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        StockTransferDTO result = stockTransferService.update(stockTransferDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, stockTransferDTO.getStockTransferId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /stock-transfers/:stockTransferId} : Partial updates given fields of an existing stockTransfer, field will ignore if it is null
     *
     * @param stockTransferId the id of the stockTransferDTO to save.
     * @param stockTransferDTO the stockTransferDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated stockTransferDTO,
     * or with status {@code 400 (Bad Request)} if the stockTransferDTO is not valid,
     * or with status {@code 404 (Not Found)} if the stockTransferDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the stockTransferDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/stock-transfers/{stockTransferId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<StockTransferDTO> partialUpdateStockTransfer(
        @PathVariable(value = "stockTransferId", required = false) final Long stockTransferId,
        @RequestBody StockTransferDTO stockTransferDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update StockTransfer partially : {}, {}", stockTransferId, stockTransferDTO);
        if (stockTransferDTO.getStockTransferId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(stockTransferId, stockTransferDTO.getStockTransferId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!stockTransferRepository.existsById(stockTransferId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<StockTransferDTO> result = stockTransferService.partialUpdate(stockTransferDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, stockTransferDTO.getStockTransferId().toString())
        );
    }

    /**
     * {@code GET  /stock-transfers} : get all the stockTransfers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of stockTransfers in body.
     */
    @GetMapping("/stock-transfers")
    public ResponseEntity<List<StockTransferDTO>> getAllStockTransfers(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of StockTransfers");
        Page<StockTransferDTO> page = stockTransferService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /stock-transfers/:id} : get the "id" stockTransfer.
     *
     * @param id the id of the stockTransferDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the stockTransferDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/stock-transfers/{id}")
    public ResponseEntity<StockTransferDTO> getStockTransfer(@PathVariable Long id) {
        log.debug("REST request to get StockTransfer : {}", id);
        Optional<StockTransferDTO> stockTransferDTO = stockTransferService.findOne(id);
        return ResponseUtil.wrapOrNotFound(stockTransferDTO);
    }

    /**
     * {@code DELETE  /stock-transfers/:id} : delete the "id" stockTransfer.
     *
     * @param id the id of the stockTransferDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/stock-transfers/{id}")
    public ResponseEntity<Void> deleteStockTransfer(@PathVariable Long id) {
        log.debug("REST request to delete StockTransfer : {}", id);
        stockTransferService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
