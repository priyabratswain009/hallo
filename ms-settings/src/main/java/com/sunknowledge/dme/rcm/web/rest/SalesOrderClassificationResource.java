package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.SalesOrderClassificationRepository;
import com.sunknowledge.dme.rcm.service.SalesOrderClassificationService;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderClassificationDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.SalesOrderClassification}.
 */
@RestController
@RequestMapping("/api")
public class SalesOrderClassificationResource {

    private final Logger log = LoggerFactory.getLogger(SalesOrderClassificationResource.class);

    private static final String ENTITY_NAME = "settingsSalesOrderClassification";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SalesOrderClassificationService salesOrderClassificationService;

    private final SalesOrderClassificationRepository salesOrderClassificationRepository;

    public SalesOrderClassificationResource(
        SalesOrderClassificationService salesOrderClassificationService,
        SalesOrderClassificationRepository salesOrderClassificationRepository
    ) {
        this.salesOrderClassificationService = salesOrderClassificationService;
        this.salesOrderClassificationRepository = salesOrderClassificationRepository;
    }

    /**
     * {@code POST  /sales-order-classifications} : Create a new salesOrderClassification.
     *
     * @param salesOrderClassificationDTO the salesOrderClassificationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new salesOrderClassificationDTO, or with status {@code 400 (Bad Request)} if the salesOrderClassification has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sales-order-classifications")
    public ResponseEntity<SalesOrderClassificationDTO> createSalesOrderClassification(
        @RequestBody SalesOrderClassificationDTO salesOrderClassificationDTO
    ) throws URISyntaxException {
        log.debug("REST request to save SalesOrderClassification : {}", salesOrderClassificationDTO);
        if (salesOrderClassificationDTO.getOrderClassificationId() != null) {
            throw new BadRequestAlertException("A new salesOrderClassification cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SalesOrderClassificationDTO result = salesOrderClassificationService.save(salesOrderClassificationDTO);
        return ResponseEntity
            .created(new URI("/api/sales-order-classifications/" + result.getOrderClassificationId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getOrderClassificationId().toString())
            )
            .body(result);
    }

    /**
     * {@code PUT  /sales-order-classifications/:orderClassificationId} : Updates an existing salesOrderClassification.
     *
     * @param orderClassificationId the id of the salesOrderClassificationDTO to save.
     * @param salesOrderClassificationDTO the salesOrderClassificationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesOrderClassificationDTO,
     * or with status {@code 400 (Bad Request)} if the salesOrderClassificationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the salesOrderClassificationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sales-order-classifications/{orderClassificationId}")
    public ResponseEntity<SalesOrderClassificationDTO> updateSalesOrderClassification(
        @PathVariable(value = "orderClassificationId", required = false) final Long orderClassificationId,
        @RequestBody SalesOrderClassificationDTO salesOrderClassificationDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SalesOrderClassification : {}, {}", orderClassificationId, salesOrderClassificationDTO);
        if (salesOrderClassificationDTO.getOrderClassificationId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(orderClassificationId, salesOrderClassificationDTO.getOrderClassificationId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!salesOrderClassificationRepository.existsById(orderClassificationId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SalesOrderClassificationDTO result = salesOrderClassificationService.update(salesOrderClassificationDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    salesOrderClassificationDTO.getOrderClassificationId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /sales-order-classifications/:orderClassificationId} : Partial updates given fields of an existing salesOrderClassification, field will ignore if it is null
     *
     * @param orderClassificationId the id of the salesOrderClassificationDTO to save.
     * @param salesOrderClassificationDTO the salesOrderClassificationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesOrderClassificationDTO,
     * or with status {@code 400 (Bad Request)} if the salesOrderClassificationDTO is not valid,
     * or with status {@code 404 (Not Found)} if the salesOrderClassificationDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the salesOrderClassificationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/sales-order-classifications/{orderClassificationId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public ResponseEntity<SalesOrderClassificationDTO> partialUpdateSalesOrderClassification(
        @PathVariable(value = "orderClassificationId", required = false) final Long orderClassificationId,
        @RequestBody SalesOrderClassificationDTO salesOrderClassificationDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update SalesOrderClassification partially : {}, {}",
            orderClassificationId,
            salesOrderClassificationDTO
        );
        if (salesOrderClassificationDTO.getOrderClassificationId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(orderClassificationId, salesOrderClassificationDTO.getOrderClassificationId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!salesOrderClassificationRepository.existsById(orderClassificationId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SalesOrderClassificationDTO> result = salesOrderClassificationService.partialUpdate(salesOrderClassificationDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                false,
                ENTITY_NAME,
                salesOrderClassificationDTO.getOrderClassificationId().toString()
            )
        );
    }

    /**
     * {@code GET  /sales-order-classifications} : get all the salesOrderClassifications.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of salesOrderClassifications in body.
     */
    @GetMapping("/sales-order-classifications")
    public ResponseEntity<List<SalesOrderClassificationDTO>> getAllSalesOrderClassifications(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of SalesOrderClassifications");
        Page<SalesOrderClassificationDTO> page = salesOrderClassificationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sales-order-classifications/:id} : get the "id" salesOrderClassification.
     *
     * @param id the id of the salesOrderClassificationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the salesOrderClassificationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sales-order-classifications/{id}")
    public ResponseEntity<SalesOrderClassificationDTO> getSalesOrderClassification(@PathVariable Long id) {
        log.debug("REST request to get SalesOrderClassification : {}", id);
        Optional<SalesOrderClassificationDTO> salesOrderClassificationDTO = salesOrderClassificationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(salesOrderClassificationDTO);
    }

    /**
     * {@code DELETE  /sales-order-classifications/:id} : delete the "id" salesOrderClassification.
     *
     * @param id the id of the salesOrderClassificationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sales-order-classifications/{id}")
    public ResponseEntity<Void> deleteSalesOrderClassification(@PathVariable Long id) {
        log.debug("REST request to delete SalesOrderClassification : {}", id);
        salesOrderClassificationService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
