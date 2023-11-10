package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.TaxonomyDetailsRepository;
import com.sunknowledge.dme.rcm.service.TaxonomyDetailsService;
import com.sunknowledge.dme.rcm.service.dto.TaxonomyDetailsDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.TaxonomyDetails}.
 */
@RestController
@RequestMapping("/api")
public class TaxonomyDetailsResource {

    private final Logger log = LoggerFactory.getLogger(TaxonomyDetailsResource.class);

    private static final String ENTITY_NAME = "settingsTaxonomyDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TaxonomyDetailsService taxonomyDetailsService;

    private final TaxonomyDetailsRepository taxonomyDetailsRepository;

    public TaxonomyDetailsResource(TaxonomyDetailsService taxonomyDetailsService, TaxonomyDetailsRepository taxonomyDetailsRepository) {
        this.taxonomyDetailsService = taxonomyDetailsService;
        this.taxonomyDetailsRepository = taxonomyDetailsRepository;
    }

    /**
     * {@code POST  /taxonomy-details} : Create a new taxonomyDetails.
     *
     * @param taxonomyDetailsDTO the taxonomyDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new taxonomyDetailsDTO, or with status {@code 400 (Bad Request)} if the taxonomyDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/taxonomy-details")
    public ResponseEntity<TaxonomyDetailsDTO> createTaxonomyDetails(@RequestBody TaxonomyDetailsDTO taxonomyDetailsDTO)
        throws URISyntaxException {
        log.debug("REST request to save TaxonomyDetails : {}", taxonomyDetailsDTO);
        if (taxonomyDetailsDTO.getTaxonomyDetailsId() != null) {
            throw new BadRequestAlertException("A new taxonomyDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TaxonomyDetailsDTO result = taxonomyDetailsService.save(taxonomyDetailsDTO);
        return ResponseEntity
            .created(new URI("/api/taxonomy-details/" + result.getTaxonomyDetailsId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getTaxonomyDetailsId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /taxonomy-details/:taxonomyDetailsId} : Updates an existing taxonomyDetails.
     *
     * @param taxonomyDetailsId the id of the taxonomyDetailsDTO to save.
     * @param taxonomyDetailsDTO the taxonomyDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taxonomyDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the taxonomyDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the taxonomyDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/taxonomy-details/{taxonomyDetailsId}")
    public ResponseEntity<TaxonomyDetailsDTO> updateTaxonomyDetails(
        @PathVariable(value = "taxonomyDetailsId", required = false) final Long taxonomyDetailsId,
        @RequestBody TaxonomyDetailsDTO taxonomyDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TaxonomyDetails : {}, {}", taxonomyDetailsId, taxonomyDetailsDTO);
        if (taxonomyDetailsDTO.getTaxonomyDetailsId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(taxonomyDetailsId, taxonomyDetailsDTO.getTaxonomyDetailsId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!taxonomyDetailsRepository.existsById(taxonomyDetailsId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TaxonomyDetailsDTO result = taxonomyDetailsService.update(taxonomyDetailsDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    taxonomyDetailsDTO.getTaxonomyDetailsId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /taxonomy-details/:taxonomyDetailsId} : Partial updates given fields of an existing taxonomyDetails, field will ignore if it is null
     *
     * @param taxonomyDetailsId the id of the taxonomyDetailsDTO to save.
     * @param taxonomyDetailsDTO the taxonomyDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taxonomyDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the taxonomyDetailsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the taxonomyDetailsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the taxonomyDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/taxonomy-details/{taxonomyDetailsId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TaxonomyDetailsDTO> partialUpdateTaxonomyDetails(
        @PathVariable(value = "taxonomyDetailsId", required = false) final Long taxonomyDetailsId,
        @RequestBody TaxonomyDetailsDTO taxonomyDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TaxonomyDetails partially : {}, {}", taxonomyDetailsId, taxonomyDetailsDTO);
        if (taxonomyDetailsDTO.getTaxonomyDetailsId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(taxonomyDetailsId, taxonomyDetailsDTO.getTaxonomyDetailsId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!taxonomyDetailsRepository.existsById(taxonomyDetailsId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TaxonomyDetailsDTO> result = taxonomyDetailsService.partialUpdate(taxonomyDetailsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, taxonomyDetailsDTO.getTaxonomyDetailsId().toString())
        );
    }

    /**
     * {@code GET  /taxonomy-details} : get all the taxonomyDetails.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of taxonomyDetails in body.
     */
    @GetMapping("/taxonomy-details")
    public ResponseEntity<List<TaxonomyDetailsDTO>> getAllTaxonomyDetails(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of TaxonomyDetails");
        Page<TaxonomyDetailsDTO> page = taxonomyDetailsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /taxonomy-details/:id} : get the "id" taxonomyDetails.
     *
     * @param id the id of the taxonomyDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the taxonomyDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/taxonomy-details/{id}")
    public ResponseEntity<TaxonomyDetailsDTO> getTaxonomyDetails(@PathVariable Long id) {
        log.debug("REST request to get TaxonomyDetails : {}", id);
        Optional<TaxonomyDetailsDTO> taxonomyDetailsDTO = taxonomyDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(taxonomyDetailsDTO);
    }

    /**
     * {@code DELETE  /taxonomy-details/:id} : delete the "id" taxonomyDetails.
     *
     * @param id the id of the taxonomyDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/taxonomy-details/{id}")
    public ResponseEntity<Void> deleteTaxonomyDetails(@PathVariable Long id) {
        log.debug("REST request to delete TaxonomyDetails : {}", id);
        taxonomyDetailsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
