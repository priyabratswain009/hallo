package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.WipQueueDetailsRepository;
import com.sunknowledge.dme.rcm.service.WipQueueDetailsService;
import com.sunknowledge.dme.rcm.service.dto.WipQueueDetailsDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.WipQueueDetails}.
 */
@RestController
@RequestMapping("/api")
public class WipQueueDetailsResource {

    private final Logger log = LoggerFactory.getLogger(WipQueueDetailsResource.class);

    private static final String ENTITY_NAME = "utilityapisWipQueueDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WipQueueDetailsService wipQueueDetailsService;

    private final WipQueueDetailsRepository wipQueueDetailsRepository;

    public WipQueueDetailsResource(WipQueueDetailsService wipQueueDetailsService, WipQueueDetailsRepository wipQueueDetailsRepository) {
        this.wipQueueDetailsService = wipQueueDetailsService;
        this.wipQueueDetailsRepository = wipQueueDetailsRepository;
    }

    /**
     * {@code POST  /wip-queue-details} : Create a new wipQueueDetails.
     *
     * @param wipQueueDetailsDTO the wipQueueDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new wipQueueDetailsDTO, or with status {@code 400 (Bad Request)} if the wipQueueDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/wip-queue-details")
    public ResponseEntity<WipQueueDetailsDTO> createWipQueueDetails(@Valid @RequestBody WipQueueDetailsDTO wipQueueDetailsDTO)
        throws URISyntaxException {
        log.debug("REST request to save WipQueueDetails : {}", wipQueueDetailsDTO);
        if (wipQueueDetailsDTO.getWipQueueDetailsId() != null) {
            throw new BadRequestAlertException("A new wipQueueDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WipQueueDetailsDTO result = wipQueueDetailsService.save(wipQueueDetailsDTO);
        return ResponseEntity
            .created(new URI("/api/wip-queue-details/" + result.getWipQueueDetailsId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getWipQueueDetailsId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /wip-queue-details/:wipQueueDetailsId} : Updates an existing wipQueueDetails.
     *
     * @param wipQueueDetailsId the id of the wipQueueDetailsDTO to save.
     * @param wipQueueDetailsDTO the wipQueueDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated wipQueueDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the wipQueueDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the wipQueueDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/wip-queue-details/{wipQueueDetailsId}")
    public ResponseEntity<WipQueueDetailsDTO> updateWipQueueDetails(
        @PathVariable(value = "wipQueueDetailsId", required = false) final Long wipQueueDetailsId,
        @Valid @RequestBody WipQueueDetailsDTO wipQueueDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update WipQueueDetails : {}, {}", wipQueueDetailsId, wipQueueDetailsDTO);
        if (wipQueueDetailsDTO.getWipQueueDetailsId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(wipQueueDetailsId, wipQueueDetailsDTO.getWipQueueDetailsId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!wipQueueDetailsRepository.existsById(wipQueueDetailsId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        WipQueueDetailsDTO result = wipQueueDetailsService.update(wipQueueDetailsDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    wipQueueDetailsDTO.getWipQueueDetailsId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /wip-queue-details/:wipQueueDetailsId} : Partial updates given fields of an existing wipQueueDetails, field will ignore if it is null
     *
     * @param wipQueueDetailsId the id of the wipQueueDetailsDTO to save.
     * @param wipQueueDetailsDTO the wipQueueDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated wipQueueDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the wipQueueDetailsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the wipQueueDetailsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the wipQueueDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/wip-queue-details/{wipQueueDetailsId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<WipQueueDetailsDTO> partialUpdateWipQueueDetails(
        @PathVariable(value = "wipQueueDetailsId", required = false) final Long wipQueueDetailsId,
        @NotNull @RequestBody WipQueueDetailsDTO wipQueueDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update WipQueueDetails partially : {}, {}", wipQueueDetailsId, wipQueueDetailsDTO);
        if (wipQueueDetailsDTO.getWipQueueDetailsId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(wipQueueDetailsId, wipQueueDetailsDTO.getWipQueueDetailsId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!wipQueueDetailsRepository.existsById(wipQueueDetailsId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<WipQueueDetailsDTO> result = wipQueueDetailsService.partialUpdate(wipQueueDetailsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, wipQueueDetailsDTO.getWipQueueDetailsId().toString())
        );
    }

    /**
     * {@code GET  /wip-queue-details} : get all the wipQueueDetails.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of wipQueueDetails in body.
     */
    @GetMapping("/wip-queue-details")
    public ResponseEntity<List<WipQueueDetailsDTO>> getAllWipQueueDetails(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of WipQueueDetails");
        Page<WipQueueDetailsDTO> page = wipQueueDetailsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /wip-queue-details/:id} : get the "id" wipQueueDetails.
     *
     * @param id the id of the wipQueueDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the wipQueueDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/wip-queue-details/{id}")
    public ResponseEntity<WipQueueDetailsDTO> getWipQueueDetails(@PathVariable Long id) {
        log.debug("REST request to get WipQueueDetails : {}", id);
        Optional<WipQueueDetailsDTO> wipQueueDetailsDTO = wipQueueDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(wipQueueDetailsDTO);
    }

    /**
     * {@code DELETE  /wip-queue-details/:id} : delete the "id" wipQueueDetails.
     *
     * @param id the id of the wipQueueDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/wip-queue-details/{id}")
    public ResponseEntity<Void> deleteWipQueueDetails(@PathVariable Long id) {
        log.debug("REST request to delete WipQueueDetails : {}", id);
        wipQueueDetailsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
