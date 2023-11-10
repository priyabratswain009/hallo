package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.PriceDetailsRepository;
import com.sunknowledge.dme.rcm.service.PriceDetailsService;
import com.sunknowledge.dme.rcm.service.dto.PriceDetailsDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.PriceDetails}.
 */
@RestController
@RequestMapping("/api")
public class PriceDetailsResource {

    private final Logger log = LoggerFactory.getLogger(PriceDetailsResource.class);

    private static final String ENTITY_NAME = "itemsPriceDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PriceDetailsService priceDetailsService;

    private final PriceDetailsRepository priceDetailsRepository;

    public PriceDetailsResource(PriceDetailsService priceDetailsService, PriceDetailsRepository priceDetailsRepository) {
        this.priceDetailsService = priceDetailsService;
        this.priceDetailsRepository = priceDetailsRepository;
    }

    /**
     * {@code POST  /price-details} : Create a new priceDetails.
     *
     * @param priceDetailsDTO the priceDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new priceDetailsDTO, or with status {@code 400 (Bad Request)} if the priceDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/price-details")
    public ResponseEntity<PriceDetailsDTO> createPriceDetails(@RequestBody PriceDetailsDTO priceDetailsDTO) throws URISyntaxException {
        log.debug("REST request to save PriceDetails : {}", priceDetailsDTO);
        if (priceDetailsDTO.getPriceDetailsId() != null) {
            throw new BadRequestAlertException("A new priceDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PriceDetailsDTO result = priceDetailsService.save(priceDetailsDTO);
        return ResponseEntity
            .created(new URI("/api/price-details/" + result.getPriceDetailsId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getPriceDetailsId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /price-details/:priceDetailsId} : Updates an existing priceDetails.
     *
     * @param priceDetailsId the id of the priceDetailsDTO to save.
     * @param priceDetailsDTO the priceDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated priceDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the priceDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the priceDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/price-details/{priceDetailsId}")
    public ResponseEntity<PriceDetailsDTO> updatePriceDetails(
        @PathVariable(value = "priceDetailsId", required = false) final Long priceDetailsId,
        @RequestBody PriceDetailsDTO priceDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PriceDetails : {}, {}", priceDetailsId, priceDetailsDTO);
        if (priceDetailsDTO.getPriceDetailsId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(priceDetailsId, priceDetailsDTO.getPriceDetailsId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!priceDetailsRepository.existsById(priceDetailsId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PriceDetailsDTO result = priceDetailsService.update(priceDetailsDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, priceDetailsDTO.getPriceDetailsId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /price-details/:priceDetailsId} : Partial updates given fields of an existing priceDetails, field will ignore if it is null
     *
     * @param priceDetailsId the id of the priceDetailsDTO to save.
     * @param priceDetailsDTO the priceDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated priceDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the priceDetailsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the priceDetailsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the priceDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/price-details/{priceDetailsId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PriceDetailsDTO> partialUpdatePriceDetails(
        @PathVariable(value = "priceDetailsId", required = false) final Long priceDetailsId,
        @RequestBody PriceDetailsDTO priceDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PriceDetails partially : {}, {}", priceDetailsId, priceDetailsDTO);
        if (priceDetailsDTO.getPriceDetailsId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(priceDetailsId, priceDetailsDTO.getPriceDetailsId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!priceDetailsRepository.existsById(priceDetailsId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PriceDetailsDTO> result = priceDetailsService.partialUpdate(priceDetailsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, priceDetailsDTO.getPriceDetailsId().toString())
        );
    }

    /**
     * {@code GET  /price-details} : get all the priceDetails.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of priceDetails in body.
     */
    @GetMapping("/price-details")
    public ResponseEntity<List<PriceDetailsDTO>> getAllPriceDetails(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of PriceDetails");
        Page<PriceDetailsDTO> page = priceDetailsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /price-details/:id} : get the "id" priceDetails.
     *
     * @param id the id of the priceDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the priceDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/price-details/{id}")
    public ResponseEntity<PriceDetailsDTO> getPriceDetails(@PathVariable Long id) {
        log.debug("REST request to get PriceDetails : {}", id);
        Optional<PriceDetailsDTO> priceDetailsDTO = priceDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(priceDetailsDTO);
    }

    /**
     * {@code DELETE  /price-details/:id} : delete the "id" priceDetails.
     *
     * @param id the id of the priceDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/price-details/{id}")
    public ResponseEntity<Void> deletePriceDetails(@PathVariable Long id) {
        log.debug("REST request to delete PriceDetails : {}", id);
        priceDetailsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
