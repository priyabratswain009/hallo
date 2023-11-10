package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.TaxZoneRepository;
import com.sunknowledge.dme.rcm.service.TaxZoneService;
import com.sunknowledge.dme.rcm.service.dto.TaxZoneDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.TaxZone}.
 */
@RestController
@RequestMapping("/api")
public class TaxZoneResource {

    private final Logger log = LoggerFactory.getLogger(TaxZoneResource.class);

    private static final String ENTITY_NAME = "settingsTaxZone";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TaxZoneService taxZoneService;

    private final TaxZoneRepository taxZoneRepository;

    public TaxZoneResource(TaxZoneService taxZoneService, TaxZoneRepository taxZoneRepository) {
        this.taxZoneService = taxZoneService;
        this.taxZoneRepository = taxZoneRepository;
    }

    /**
     * {@code POST  /tax-zones} : Create a new taxZone.
     *
     * @param taxZoneDTO the taxZoneDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new taxZoneDTO, or with status {@code 400 (Bad Request)} if the taxZone has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tax-zones")
    public ResponseEntity<TaxZoneDTO> createTaxZone(@RequestBody TaxZoneDTO taxZoneDTO) throws URISyntaxException {
        log.debug("REST request to save TaxZone : {}", taxZoneDTO);
        if (taxZoneDTO.getTaxZoneId() != null) {
            throw new BadRequestAlertException("A new taxZone cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TaxZoneDTO result = taxZoneService.save(taxZoneDTO);
        return ResponseEntity
            .created(new URI("/api/tax-zones/" + result.getTaxZoneId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getTaxZoneId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tax-zones/:taxZoneId} : Updates an existing taxZone.
     *
     * @param taxZoneId the id of the taxZoneDTO to save.
     * @param taxZoneDTO the taxZoneDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taxZoneDTO,
     * or with status {@code 400 (Bad Request)} if the taxZoneDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the taxZoneDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tax-zones/{taxZoneId}")
    public ResponseEntity<TaxZoneDTO> updateTaxZone(
        @PathVariable(value = "taxZoneId", required = false) final Long taxZoneId,
        @RequestBody TaxZoneDTO taxZoneDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TaxZone : {}, {}", taxZoneId, taxZoneDTO);
        if (taxZoneDTO.getTaxZoneId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(taxZoneId, taxZoneDTO.getTaxZoneId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!taxZoneRepository.existsById(taxZoneId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TaxZoneDTO result = taxZoneService.update(taxZoneDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, taxZoneDTO.getTaxZoneId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tax-zones/:taxZoneId} : Partial updates given fields of an existing taxZone, field will ignore if it is null
     *
     * @param taxZoneId the id of the taxZoneDTO to save.
     * @param taxZoneDTO the taxZoneDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taxZoneDTO,
     * or with status {@code 400 (Bad Request)} if the taxZoneDTO is not valid,
     * or with status {@code 404 (Not Found)} if the taxZoneDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the taxZoneDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tax-zones/{taxZoneId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TaxZoneDTO> partialUpdateTaxZone(
        @PathVariable(value = "taxZoneId", required = false) final Long taxZoneId,
        @RequestBody TaxZoneDTO taxZoneDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TaxZone partially : {}, {}", taxZoneId, taxZoneDTO);
        if (taxZoneDTO.getTaxZoneId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(taxZoneId, taxZoneDTO.getTaxZoneId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!taxZoneRepository.existsById(taxZoneId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TaxZoneDTO> result = taxZoneService.partialUpdate(taxZoneDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, taxZoneDTO.getTaxZoneId().toString())
        );
    }

    /**
     * {@code GET  /tax-zones} : get all the taxZones.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of taxZones in body.
     */
    @GetMapping("/tax-zones")
    public ResponseEntity<List<TaxZoneDTO>> getAllTaxZones(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of TaxZones");
        Page<TaxZoneDTO> page = taxZoneService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /tax-zones/:id} : get the "id" taxZone.
     *
     * @param id the id of the taxZoneDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the taxZoneDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tax-zones/{id}")
    public ResponseEntity<TaxZoneDTO> getTaxZone(@PathVariable Long id) {
        log.debug("REST request to get TaxZone : {}", id);
        Optional<TaxZoneDTO> taxZoneDTO = taxZoneService.findOne(id);
        return ResponseUtil.wrapOrNotFound(taxZoneDTO);
    }

    /**
     * {@code DELETE  /tax-zones/:id} : delete the "id" taxZone.
     *
     * @param id the id of the taxZoneDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tax-zones/{id}")
    public ResponseEntity<Void> deleteTaxZone(@PathVariable Long id) {
        log.debug("REST request to delete TaxZone : {}", id);
        taxZoneService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
