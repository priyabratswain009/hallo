package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.HoldReasonMasterRepository;
import com.sunknowledge.dme.rcm.service.HoldReasonMasterService;
import com.sunknowledge.dme.rcm.service.dto.HoldReasonMasterDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.HoldReasonMaster}.
 */
@RestController
@RequestMapping("/api")
public class HoldReasonMasterResource {

    private final Logger log = LoggerFactory.getLogger(HoldReasonMasterResource.class);

    private static final String ENTITY_NAME = "settingsHoldReasonMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HoldReasonMasterService holdReasonMasterService;

    private final HoldReasonMasterRepository holdReasonMasterRepository;

    public HoldReasonMasterResource(
        HoldReasonMasterService holdReasonMasterService,
        HoldReasonMasterRepository holdReasonMasterRepository
    ) {
        this.holdReasonMasterService = holdReasonMasterService;
        this.holdReasonMasterRepository = holdReasonMasterRepository;
    }

    /**
     * {@code POST  /hold-reason-masters} : Create a new holdReasonMaster.
     *
     * @param holdReasonMasterDTO the holdReasonMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new holdReasonMasterDTO, or with status {@code 400 (Bad Request)} if the holdReasonMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/hold-reason-masters")
    public ResponseEntity<HoldReasonMasterDTO> createHoldReasonMaster(@RequestBody HoldReasonMasterDTO holdReasonMasterDTO)
        throws URISyntaxException {
        log.debug("REST request to save HoldReasonMaster : {}", holdReasonMasterDTO);
        if (holdReasonMasterDTO.getHoldReasonId() != null) {
            throw new BadRequestAlertException("A new holdReasonMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HoldReasonMasterDTO result = holdReasonMasterService.save(holdReasonMasterDTO);
        return ResponseEntity
            .created(new URI("/api/hold-reason-masters/" + result.getHoldReasonId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getHoldReasonId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /hold-reason-masters/:holdReasonId} : Updates an existing holdReasonMaster.
     *
     * @param holdReasonId the id of the holdReasonMasterDTO to save.
     * @param holdReasonMasterDTO the holdReasonMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated holdReasonMasterDTO,
     * or with status {@code 400 (Bad Request)} if the holdReasonMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the holdReasonMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/hold-reason-masters/{holdReasonId}")
    public ResponseEntity<HoldReasonMasterDTO> updateHoldReasonMaster(
        @PathVariable(value = "holdReasonId", required = false) final Long holdReasonId,
        @RequestBody HoldReasonMasterDTO holdReasonMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update HoldReasonMaster : {}, {}", holdReasonId, holdReasonMasterDTO);
        if (holdReasonMasterDTO.getHoldReasonId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(holdReasonId, holdReasonMasterDTO.getHoldReasonId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!holdReasonMasterRepository.existsById(holdReasonId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        HoldReasonMasterDTO result = holdReasonMasterService.update(holdReasonMasterDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, holdReasonMasterDTO.getHoldReasonId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /hold-reason-masters/:holdReasonId} : Partial updates given fields of an existing holdReasonMaster, field will ignore if it is null
     *
     * @param holdReasonId the id of the holdReasonMasterDTO to save.
     * @param holdReasonMasterDTO the holdReasonMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated holdReasonMasterDTO,
     * or with status {@code 400 (Bad Request)} if the holdReasonMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the holdReasonMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the holdReasonMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/hold-reason-masters/{holdReasonId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<HoldReasonMasterDTO> partialUpdateHoldReasonMaster(
        @PathVariable(value = "holdReasonId", required = false) final Long holdReasonId,
        @RequestBody HoldReasonMasterDTO holdReasonMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update HoldReasonMaster partially : {}, {}", holdReasonId, holdReasonMasterDTO);
        if (holdReasonMasterDTO.getHoldReasonId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(holdReasonId, holdReasonMasterDTO.getHoldReasonId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!holdReasonMasterRepository.existsById(holdReasonId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<HoldReasonMasterDTO> result = holdReasonMasterService.partialUpdate(holdReasonMasterDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, holdReasonMasterDTO.getHoldReasonId().toString())
        );
    }

    /**
     * {@code GET  /hold-reason-masters} : get all the holdReasonMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of holdReasonMasters in body.
     */
    @GetMapping("/hold-reason-masters")
    public ResponseEntity<List<HoldReasonMasterDTO>> getAllHoldReasonMasters(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of HoldReasonMasters");
        Page<HoldReasonMasterDTO> page = holdReasonMasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /hold-reason-masters/:id} : get the "id" holdReasonMaster.
     *
     * @param id the id of the holdReasonMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the holdReasonMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/hold-reason-masters/{id}")
    public ResponseEntity<HoldReasonMasterDTO> getHoldReasonMaster(@PathVariable Long id) {
        log.debug("REST request to get HoldReasonMaster : {}", id);
        Optional<HoldReasonMasterDTO> holdReasonMasterDTO = holdReasonMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(holdReasonMasterDTO);
    }

    /**
     * {@code DELETE  /hold-reason-masters/:id} : delete the "id" holdReasonMaster.
     *
     * @param id the id of the holdReasonMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/hold-reason-masters/{id}")
    public ResponseEntity<Void> deleteHoldReasonMaster(@PathVariable Long id) {
        log.debug("REST request to delete HoldReasonMaster : {}", id);
        holdReasonMasterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
