package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.SalesRepRepository;
import com.sunknowledge.dme.rcm.service.SalesRepService;
import com.sunknowledge.dme.rcm.service.dto.SalesRepDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.SalesRep}.
 */
@RestController
@RequestMapping("/api")
public class SalesRepResource {

    private final Logger log = LoggerFactory.getLogger(SalesRepResource.class);

    private static final String ENTITY_NAME = "utilityapisSalesRep";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SalesRepService salesRepService;

    private final SalesRepRepository salesRepRepository;

    public SalesRepResource(SalesRepService salesRepService, SalesRepRepository salesRepRepository) {
        this.salesRepService = salesRepService;
        this.salesRepRepository = salesRepRepository;
    }

    /**
     * {@code POST  /sales-reps} : Create a new salesRep.
     *
     * @param salesRepDTO the salesRepDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new salesRepDTO, or with status {@code 400 (Bad Request)} if the salesRep has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sales-reps")
    public ResponseEntity<SalesRepDTO> createSalesRep(@RequestBody SalesRepDTO salesRepDTO) throws URISyntaxException {
        log.debug("REST request to save SalesRep : {}", salesRepDTO);
        if (salesRepDTO.getSalesRepId() != null) {
            throw new BadRequestAlertException("A new salesRep cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SalesRepDTO result = salesRepService.save(salesRepDTO);
        return ResponseEntity
            .created(new URI("/api/sales-reps/" + result.getSalesRepId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getSalesRepId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sales-reps/:salesRepId} : Updates an existing salesRep.
     *
     * @param salesRepId the id of the salesRepDTO to save.
     * @param salesRepDTO the salesRepDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesRepDTO,
     * or with status {@code 400 (Bad Request)} if the salesRepDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the salesRepDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sales-reps/{salesRepId}")
    public ResponseEntity<SalesRepDTO> updateSalesRep(
        @PathVariable(value = "salesRepId", required = false) final Long salesRepId,
        @RequestBody SalesRepDTO salesRepDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SalesRep : {}, {}", salesRepId, salesRepDTO);
        if (salesRepDTO.getSalesRepId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(salesRepId, salesRepDTO.getSalesRepId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!salesRepRepository.existsById(salesRepId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SalesRepDTO result = salesRepService.update(salesRepDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, salesRepDTO.getSalesRepId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /sales-reps/:salesRepId} : Partial updates given fields of an existing salesRep, field will ignore if it is null
     *
     * @param salesRepId the id of the salesRepDTO to save.
     * @param salesRepDTO the salesRepDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesRepDTO,
     * or with status {@code 400 (Bad Request)} if the salesRepDTO is not valid,
     * or with status {@code 404 (Not Found)} if the salesRepDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the salesRepDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/sales-reps/{salesRepId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SalesRepDTO> partialUpdateSalesRep(
        @PathVariable(value = "salesRepId", required = false) final Long salesRepId,
        @RequestBody SalesRepDTO salesRepDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update SalesRep partially : {}, {}", salesRepId, salesRepDTO);
        if (salesRepDTO.getSalesRepId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(salesRepId, salesRepDTO.getSalesRepId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!salesRepRepository.existsById(salesRepId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SalesRepDTO> result = salesRepService.partialUpdate(salesRepDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, salesRepDTO.getSalesRepId().toString())
        );
    }

    /**
     * {@code GET  /sales-reps} : get all the salesReps.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of salesReps in body.
     */
    @GetMapping("/sales-reps")
    public ResponseEntity<List<SalesRepDTO>> getAllSalesReps(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of SalesReps");
        Page<SalesRepDTO> page = salesRepService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sales-reps/:id} : get the "id" salesRep.
     *
     * @param id the id of the salesRepDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the salesRepDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sales-reps/{id}")
    public ResponseEntity<SalesRepDTO> getSalesRep(@PathVariable Long id) {
        log.debug("REST request to get SalesRep : {}", id);
        Optional<SalesRepDTO> salesRepDTO = salesRepService.findOne(id);
        return ResponseUtil.wrapOrNotFound(salesRepDTO);
    }

    /**
     * {@code DELETE  /sales-reps/:id} : delete the "id" salesRep.
     *
     * @param id the id of the salesRepDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sales-reps/{id}")
    public ResponseEntity<Void> deleteSalesRep(@PathVariable Long id) {
        log.debug("REST request to delete SalesRep : {}", id);
        salesRepService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
