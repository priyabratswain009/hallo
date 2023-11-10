package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.SoItemTransactionDetailsRepository;
import com.sunknowledge.dme.rcm.service.SoItemTransactionDetailsService;
import com.sunknowledge.dme.rcm.service.dto.SoItemTransactionDetailsDTO;
import com.sunknowledge.dme.rcm.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.SoItemTransactionDetails}.
 */
@RestController
@RequestMapping("/api")
public class SoItemTransactionDetailsResource {

    private final Logger log = LoggerFactory.getLogger(SoItemTransactionDetailsResource.class);

    private static final String ENTITY_NAME = "itemsSoItemTransactionDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SoItemTransactionDetailsService soItemTransactionDetailsService;

    private final SoItemTransactionDetailsRepository soItemTransactionDetailsRepository;

    public SoItemTransactionDetailsResource(
        SoItemTransactionDetailsService soItemTransactionDetailsService,
        SoItemTransactionDetailsRepository soItemTransactionDetailsRepository
    ) {
        this.soItemTransactionDetailsService = soItemTransactionDetailsService;
        this.soItemTransactionDetailsRepository = soItemTransactionDetailsRepository;
    }

    /**
     * {@code POST  /so-item-transaction-details} : Create a new soItemTransactionDetails.
     *
     * @param soItemTransactionDetailsDTO the soItemTransactionDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new soItemTransactionDetailsDTO, or with status {@code 400 (Bad Request)} if the soItemTransactionDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/so-item-transaction-details")
    public ResponseEntity<SoItemTransactionDetailsDTO> createSoItemTransactionDetails(
        @Valid @RequestBody SoItemTransactionDetailsDTO soItemTransactionDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to save SoItemTransactionDetails : {}", soItemTransactionDetailsDTO);
        if (soItemTransactionDetailsDTO.getSoItemTransactionDetailsId() != null) {
            throw new BadRequestAlertException("A new soItemTransactionDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SoItemTransactionDetailsDTO result = soItemTransactionDetailsService.save(soItemTransactionDetailsDTO);
        return ResponseEntity
            .created(new URI("/api/so-item-transaction-details/" + result.getSoItemTransactionDetailsId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getSoItemTransactionDetailsId().toString())
            )
            .body(result);
    }

    /**
     * {@code PUT  /so-item-transaction-details/:soItemTransactionDetailsId} : Updates an existing soItemTransactionDetails.
     *
     * @param soItemTransactionDetailsId the id of the soItemTransactionDetailsDTO to save.
     * @param soItemTransactionDetailsDTO the soItemTransactionDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated soItemTransactionDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the soItemTransactionDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the soItemTransactionDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/so-item-transaction-details/{soItemTransactionDetailsId}")
    public ResponseEntity<SoItemTransactionDetailsDTO> updateSoItemTransactionDetails(
        @PathVariable(value = "soItemTransactionDetailsId", required = false) final Long soItemTransactionDetailsId,
        @Valid @RequestBody SoItemTransactionDetailsDTO soItemTransactionDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SoItemTransactionDetails : {}, {}", soItemTransactionDetailsId, soItemTransactionDetailsDTO);
        if (soItemTransactionDetailsDTO.getSoItemTransactionDetailsId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(soItemTransactionDetailsId, soItemTransactionDetailsDTO.getSoItemTransactionDetailsId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!soItemTransactionDetailsRepository.existsById(soItemTransactionDetailsId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SoItemTransactionDetailsDTO result = soItemTransactionDetailsService.update(soItemTransactionDetailsDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    soItemTransactionDetailsDTO.getSoItemTransactionDetailsId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /so-item-transaction-details/:soItemTransactionDetailsId} : Partial updates given fields of an existing soItemTransactionDetails, field will ignore if it is null
     *
     * @param soItemTransactionDetailsId the id of the soItemTransactionDetailsDTO to save.
     * @param soItemTransactionDetailsDTO the soItemTransactionDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated soItemTransactionDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the soItemTransactionDetailsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the soItemTransactionDetailsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the soItemTransactionDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/so-item-transaction-details/{soItemTransactionDetailsId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public ResponseEntity<SoItemTransactionDetailsDTO> partialUpdateSoItemTransactionDetails(
        @PathVariable(value = "soItemTransactionDetailsId", required = false) final Long soItemTransactionDetailsId,
        @NotNull @RequestBody SoItemTransactionDetailsDTO soItemTransactionDetailsDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update SoItemTransactionDetails partially : {}, {}",
            soItemTransactionDetailsId,
            soItemTransactionDetailsDTO
        );
        if (soItemTransactionDetailsDTO.getSoItemTransactionDetailsId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(soItemTransactionDetailsId, soItemTransactionDetailsDTO.getSoItemTransactionDetailsId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!soItemTransactionDetailsRepository.existsById(soItemTransactionDetailsId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SoItemTransactionDetailsDTO> result = soItemTransactionDetailsService.partialUpdate(soItemTransactionDetailsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                false,
                ENTITY_NAME,
                soItemTransactionDetailsDTO.getSoItemTransactionDetailsId().toString()
            )
        );
    }

    /**
     * {@code GET  /so-item-transaction-details} : get all the soItemTransactionDetails.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of soItemTransactionDetails in body.
     */
    @GetMapping("/so-item-transaction-details")
    public ResponseEntity<List<SoItemTransactionDetailsDTO>> getAllSoItemTransactionDetails(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of SoItemTransactionDetails");
        Page<SoItemTransactionDetailsDTO> page = soItemTransactionDetailsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /so-item-transaction-details/:id} : get the "id" soItemTransactionDetails.
     *
     * @param id the id of the soItemTransactionDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the soItemTransactionDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/so-item-transaction-details/{id}")
    public ResponseEntity<SoItemTransactionDetailsDTO> getSoItemTransactionDetails(@PathVariable Long id) {
        log.debug("REST request to get SoItemTransactionDetails : {}", id);
        Optional<SoItemTransactionDetailsDTO> soItemTransactionDetailsDTO = soItemTransactionDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(soItemTransactionDetailsDTO);
    }

    /**
     * {@code DELETE  /so-item-transaction-details/:id} : delete the "id" soItemTransactionDetails.
     *
     * @param id the id of the soItemTransactionDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/so-item-transaction-details/{id}")
    public ResponseEntity<Void> deleteSoItemTransactionDetails(@PathVariable Long id) {
        log.debug("REST request to delete SoItemTransactionDetails : {}", id);
        soItemTransactionDetailsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
