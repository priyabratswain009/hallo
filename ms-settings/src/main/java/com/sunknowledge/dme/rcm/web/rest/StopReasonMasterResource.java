package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.StopReasonMasterRepository;
import com.sunknowledge.dme.rcm.service.StopReasonMasterService;
import com.sunknowledge.dme.rcm.service.dto.StopReasonMasterDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.StopReasonMaster}.
 */
@RestController
@RequestMapping("/api")
public class StopReasonMasterResource {

    private final Logger log = LoggerFactory.getLogger(StopReasonMasterResource.class);

    private static final String ENTITY_NAME = "settingsStopReasonMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StopReasonMasterService stopReasonMasterService;

    private final StopReasonMasterRepository stopReasonMasterRepository;

    public StopReasonMasterResource(
        StopReasonMasterService stopReasonMasterService,
        StopReasonMasterRepository stopReasonMasterRepository
    ) {
        this.stopReasonMasterService = stopReasonMasterService;
        this.stopReasonMasterRepository = stopReasonMasterRepository;
    }

    /**
     * {@code POST  /stop-reason-masters} : Create a new stopReasonMaster.
     *
     * @param stopReasonMasterDTO the stopReasonMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new stopReasonMasterDTO, or with status {@code 400 (Bad Request)} if the stopReasonMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/stop-reason-masters")
    public ResponseEntity<StopReasonMasterDTO> createStopReasonMaster(@RequestBody StopReasonMasterDTO stopReasonMasterDTO)
        throws URISyntaxException {
        log.debug("REST request to save StopReasonMaster : {}", stopReasonMasterDTO);
        if (stopReasonMasterDTO.getStopReasonId() != null) {
            throw new BadRequestAlertException("A new stopReasonMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StopReasonMasterDTO result = stopReasonMasterService.save(stopReasonMasterDTO);
        return ResponseEntity
            .created(new URI("/api/stop-reason-masters/" + result.getStopReasonId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getStopReasonId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /stop-reason-masters/:stopReasonId} : Updates an existing stopReasonMaster.
     *
     * @param stopReasonId the id of the stopReasonMasterDTO to save.
     * @param stopReasonMasterDTO the stopReasonMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated stopReasonMasterDTO,
     * or with status {@code 400 (Bad Request)} if the stopReasonMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the stopReasonMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/stop-reason-masters/{stopReasonId}")
    public ResponseEntity<StopReasonMasterDTO> updateStopReasonMaster(
        @PathVariable(value = "stopReasonId", required = false) final Long stopReasonId,
        @RequestBody StopReasonMasterDTO stopReasonMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update StopReasonMaster : {}, {}", stopReasonId, stopReasonMasterDTO);
        if (stopReasonMasterDTO.getStopReasonId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(stopReasonId, stopReasonMasterDTO.getStopReasonId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!stopReasonMasterRepository.existsById(stopReasonId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        StopReasonMasterDTO result = stopReasonMasterService.update(stopReasonMasterDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, stopReasonMasterDTO.getStopReasonId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /stop-reason-masters/:stopReasonId} : Partial updates given fields of an existing stopReasonMaster, field will ignore if it is null
     *
     * @param stopReasonId the id of the stopReasonMasterDTO to save.
     * @param stopReasonMasterDTO the stopReasonMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated stopReasonMasterDTO,
     * or with status {@code 400 (Bad Request)} if the stopReasonMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the stopReasonMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the stopReasonMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/stop-reason-masters/{stopReasonId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<StopReasonMasterDTO> partialUpdateStopReasonMaster(
        @PathVariable(value = "stopReasonId", required = false) final Long stopReasonId,
        @RequestBody StopReasonMasterDTO stopReasonMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update StopReasonMaster partially : {}, {}", stopReasonId, stopReasonMasterDTO);
        if (stopReasonMasterDTO.getStopReasonId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(stopReasonId, stopReasonMasterDTO.getStopReasonId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!stopReasonMasterRepository.existsById(stopReasonId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<StopReasonMasterDTO> result = stopReasonMasterService.partialUpdate(stopReasonMasterDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, stopReasonMasterDTO.getStopReasonId().toString())
        );
    }

    /**
     * {@code GET  /stop-reason-masters} : get all the stopReasonMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of stopReasonMasters in body.
     */
    @GetMapping("/stop-reason-masters")
    public ResponseEntity<List<StopReasonMasterDTO>> getAllStopReasonMasters(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of StopReasonMasters");
        Page<StopReasonMasterDTO> page = stopReasonMasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /stop-reason-masters/:id} : get the "id" stopReasonMaster.
     *
     * @param id the id of the stopReasonMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the stopReasonMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/stop-reason-masters/{id}")
    public ResponseEntity<StopReasonMasterDTO> getStopReasonMaster(@PathVariable Long id) {
        log.debug("REST request to get StopReasonMaster : {}", id);
        Optional<StopReasonMasterDTO> stopReasonMasterDTO = stopReasonMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(stopReasonMasterDTO);
    }

    /**
     * {@code DELETE  /stop-reason-masters/:id} : delete the "id" stopReasonMaster.
     *
     * @param id the id of the stopReasonMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/stop-reason-masters/{id}")
    public ResponseEntity<Void> deleteStopReasonMaster(@PathVariable Long id) {
        log.debug("REST request to delete StopReasonMaster : {}", id);
        stopReasonMasterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
