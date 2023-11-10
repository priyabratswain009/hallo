package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.MarketingReferalTypeMasterRepository;
import com.sunknowledge.dme.rcm.service.MarketingReferalTypeMasterService;
import com.sunknowledge.dme.rcm.service.dto.MarketingReferalTypeMasterDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.MarketingReferalTypeMaster}.
 */
@RestController
@RequestMapping("/api")
public class MarketingReferalTypeMasterResource {

    private final Logger log = LoggerFactory.getLogger(MarketingReferalTypeMasterResource.class);

    private static final String ENTITY_NAME = "utilityapisMarketingReferalTypeMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MarketingReferalTypeMasterService marketingReferalTypeMasterService;

    private final MarketingReferalTypeMasterRepository marketingReferalTypeMasterRepository;

    public MarketingReferalTypeMasterResource(
        MarketingReferalTypeMasterService marketingReferalTypeMasterService,
        MarketingReferalTypeMasterRepository marketingReferalTypeMasterRepository
    ) {
        this.marketingReferalTypeMasterService = marketingReferalTypeMasterService;
        this.marketingReferalTypeMasterRepository = marketingReferalTypeMasterRepository;
    }

    /**
     * {@code POST  /marketing-referal-type-masters} : Create a new marketingReferalTypeMaster.
     *
     * @param marketingReferalTypeMasterDTO the marketingReferalTypeMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new marketingReferalTypeMasterDTO, or with status {@code 400 (Bad Request)} if the marketingReferalTypeMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/marketing-referal-type-masters")
    public ResponseEntity<MarketingReferalTypeMasterDTO> createMarketingReferalTypeMaster(
        @RequestBody MarketingReferalTypeMasterDTO marketingReferalTypeMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to save MarketingReferalTypeMaster : {}", marketingReferalTypeMasterDTO);
        if (marketingReferalTypeMasterDTO.getMarketingReferralTypeId() != null) {
            throw new BadRequestAlertException("A new marketingReferalTypeMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MarketingReferalTypeMasterDTO result = marketingReferalTypeMasterService.save(marketingReferalTypeMasterDTO);
        return ResponseEntity
            .created(new URI("/api/marketing-referal-type-masters/" + result.getMarketingReferralTypeId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getMarketingReferralTypeId().toString())
            )
            .body(result);
    }

    /**
     * {@code PUT  /marketing-referal-type-masters/:marketingReferralTypeId} : Updates an existing marketingReferalTypeMaster.
     *
     * @param marketingReferralTypeId the id of the marketingReferalTypeMasterDTO to save.
     * @param marketingReferalTypeMasterDTO the marketingReferalTypeMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated marketingReferalTypeMasterDTO,
     * or with status {@code 400 (Bad Request)} if the marketingReferalTypeMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the marketingReferalTypeMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/marketing-referal-type-masters/{marketingReferralTypeId}")
    public ResponseEntity<MarketingReferalTypeMasterDTO> updateMarketingReferalTypeMaster(
        @PathVariable(value = "marketingReferralTypeId", required = false) final Long marketingReferralTypeId,
        @RequestBody MarketingReferalTypeMasterDTO marketingReferalTypeMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update MarketingReferalTypeMaster : {}, {}", marketingReferralTypeId, marketingReferalTypeMasterDTO);
        if (marketingReferalTypeMasterDTO.getMarketingReferralTypeId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(marketingReferralTypeId, marketingReferalTypeMasterDTO.getMarketingReferralTypeId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!marketingReferalTypeMasterRepository.existsById(marketingReferralTypeId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        MarketingReferalTypeMasterDTO result = marketingReferalTypeMasterService.update(marketingReferalTypeMasterDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    marketingReferalTypeMasterDTO.getMarketingReferralTypeId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /marketing-referal-type-masters/:marketingReferralTypeId} : Partial updates given fields of an existing marketingReferalTypeMaster, field will ignore if it is null
     *
     * @param marketingReferralTypeId the id of the marketingReferalTypeMasterDTO to save.
     * @param marketingReferalTypeMasterDTO the marketingReferalTypeMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated marketingReferalTypeMasterDTO,
     * or with status {@code 400 (Bad Request)} if the marketingReferalTypeMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the marketingReferalTypeMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the marketingReferalTypeMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/marketing-referal-type-masters/{marketingReferralTypeId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public ResponseEntity<MarketingReferalTypeMasterDTO> partialUpdateMarketingReferalTypeMaster(
        @PathVariable(value = "marketingReferralTypeId", required = false) final Long marketingReferralTypeId,
        @RequestBody MarketingReferalTypeMasterDTO marketingReferalTypeMasterDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update MarketingReferalTypeMaster partially : {}, {}",
            marketingReferralTypeId,
            marketingReferalTypeMasterDTO
        );
        if (marketingReferalTypeMasterDTO.getMarketingReferralTypeId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(marketingReferralTypeId, marketingReferalTypeMasterDTO.getMarketingReferralTypeId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!marketingReferalTypeMasterRepository.existsById(marketingReferralTypeId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<MarketingReferalTypeMasterDTO> result = marketingReferalTypeMasterService.partialUpdate(marketingReferalTypeMasterDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                false,
                ENTITY_NAME,
                marketingReferalTypeMasterDTO.getMarketingReferralTypeId().toString()
            )
        );
    }

    /**
     * {@code GET  /marketing-referal-type-masters} : get all the marketingReferalTypeMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of marketingReferalTypeMasters in body.
     */
    @GetMapping("/marketing-referal-type-masters")
    public ResponseEntity<List<MarketingReferalTypeMasterDTO>> getAllMarketingReferalTypeMasters(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of MarketingReferalTypeMasters");
        Page<MarketingReferalTypeMasterDTO> page = marketingReferalTypeMasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /marketing-referal-type-masters/:id} : get the "id" marketingReferalTypeMaster.
     *
     * @param id the id of the marketingReferalTypeMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the marketingReferalTypeMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/marketing-referal-type-masters/{id}")
    public ResponseEntity<MarketingReferalTypeMasterDTO> getMarketingReferalTypeMaster(@PathVariable Long id) {
        log.debug("REST request to get MarketingReferalTypeMaster : {}", id);
        Optional<MarketingReferalTypeMasterDTO> marketingReferalTypeMasterDTO = marketingReferalTypeMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(marketingReferalTypeMasterDTO);
    }

    /**
     * {@code DELETE  /marketing-referal-type-masters/:id} : delete the "id" marketingReferalTypeMaster.
     *
     * @param id the id of the marketingReferalTypeMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/marketing-referal-type-masters/{id}")
    public ResponseEntity<Void> deleteMarketingReferalTypeMaster(@PathVariable Long id) {
        log.debug("REST request to delete MarketingReferalTypeMaster : {}", id);
        marketingReferalTypeMasterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
