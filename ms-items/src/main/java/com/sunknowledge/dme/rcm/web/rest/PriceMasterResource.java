package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.PriceMasterRepository;
import com.sunknowledge.dme.rcm.service.PriceMasterService;
import com.sunknowledge.dme.rcm.service.dto.PriceMasterDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.PriceMaster}.
 */
@RestController
@RequestMapping("/api")
public class PriceMasterResource {

    private final Logger log = LoggerFactory.getLogger(PriceMasterResource.class);

    private static final String ENTITY_NAME = "itemsPriceMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PriceMasterService priceMasterService;

    private final PriceMasterRepository priceMasterRepository;

    public PriceMasterResource(PriceMasterService priceMasterService, PriceMasterRepository priceMasterRepository) {
        this.priceMasterService = priceMasterService;
        this.priceMasterRepository = priceMasterRepository;
    }

    /**
     * {@code POST  /price-masters} : Create a new priceMaster.
     *
     * @param priceMasterDTO the priceMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new priceMasterDTO, or with status {@code 400 (Bad Request)} if the priceMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/price-masters")
    public ResponseEntity<PriceMasterDTO> createPriceMaster(@RequestBody PriceMasterDTO priceMasterDTO) throws URISyntaxException {
        log.debug("REST request to save PriceMaster : {}", priceMasterDTO);
        if (priceMasterDTO.getPriceTableId() != null) {
            throw new BadRequestAlertException("A new priceMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PriceMasterDTO result = priceMasterService.save(priceMasterDTO);
        return ResponseEntity
            .created(new URI("/api/price-masters/" + result.getPriceTableId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getPriceTableId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /price-masters/:priceTableId} : Updates an existing priceMaster.
     *
     * @param priceTableId the id of the priceMasterDTO to save.
     * @param priceMasterDTO the priceMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated priceMasterDTO,
     * or with status {@code 400 (Bad Request)} if the priceMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the priceMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/price-masters/{priceTableId}")
    public ResponseEntity<PriceMasterDTO> updatePriceMaster(
        @PathVariable(value = "priceTableId", required = false) final Long priceTableId,
        @RequestBody PriceMasterDTO priceMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PriceMaster : {}, {}", priceTableId, priceMasterDTO);
        if (priceMasterDTO.getPriceTableId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(priceTableId, priceMasterDTO.getPriceTableId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!priceMasterRepository.existsById(priceTableId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PriceMasterDTO result = priceMasterService.update(priceMasterDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, priceMasterDTO.getPriceTableId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /price-masters/:priceTableId} : Partial updates given fields of an existing priceMaster, field will ignore if it is null
     *
     * @param priceTableId the id of the priceMasterDTO to save.
     * @param priceMasterDTO the priceMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated priceMasterDTO,
     * or with status {@code 400 (Bad Request)} if the priceMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the priceMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the priceMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/price-masters/{priceTableId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PriceMasterDTO> partialUpdatePriceMaster(
        @PathVariable(value = "priceTableId", required = false) final Long priceTableId,
        @RequestBody PriceMasterDTO priceMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PriceMaster partially : {}, {}", priceTableId, priceMasterDTO);
        if (priceMasterDTO.getPriceTableId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(priceTableId, priceMasterDTO.getPriceTableId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!priceMasterRepository.existsById(priceTableId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PriceMasterDTO> result = priceMasterService.partialUpdate(priceMasterDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, priceMasterDTO.getPriceTableId().toString())
        );
    }

    /**
     * {@code GET  /price-masters} : get all the priceMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of priceMasters in body.
     */
    @GetMapping("/price-masters")
    public ResponseEntity<List<PriceMasterDTO>> getAllPriceMasters(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of PriceMasters");
        Page<PriceMasterDTO> page = priceMasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /price-masters/:id} : get the "id" priceMaster.
     *
     * @param id the id of the priceMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the priceMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/price-masters/{id}")
    public ResponseEntity<PriceMasterDTO> getPriceMaster(@PathVariable Long id) {
        log.debug("REST request to get PriceMaster : {}", id);
        Optional<PriceMasterDTO> priceMasterDTO = priceMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(priceMasterDTO);
    }

    /**
     * {@code DELETE  /price-masters/:id} : delete the "id" priceMaster.
     *
     * @param id the id of the priceMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/price-masters/{id}")
    public ResponseEntity<Void> deletePriceMaster(@PathVariable Long id) {
        log.debug("REST request to delete PriceMaster : {}", id);
        priceMasterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
