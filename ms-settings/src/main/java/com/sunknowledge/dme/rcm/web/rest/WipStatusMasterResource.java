package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.WipStatusMasterRepository;
import com.sunknowledge.dme.rcm.service.WipStatusMasterService;
import com.sunknowledge.dme.rcm.service.dto.WipStatusMasterDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.WipStatusMaster}.
 */
@RestController
@RequestMapping("/api")
public class WipStatusMasterResource {

    private final Logger log = LoggerFactory.getLogger(WipStatusMasterResource.class);

    private static final String ENTITY_NAME = "settingsWipStatusMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WipStatusMasterService wipStatusMasterService;

    private final WipStatusMasterRepository wipStatusMasterRepository;

    public WipStatusMasterResource(WipStatusMasterService wipStatusMasterService, WipStatusMasterRepository wipStatusMasterRepository) {
        this.wipStatusMasterService = wipStatusMasterService;
        this.wipStatusMasterRepository = wipStatusMasterRepository;
    }

    /**
     * {@code POST  /wip-status-masters} : Create a new wipStatusMaster.
     *
     * @param wipStatusMasterDTO the wipStatusMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new wipStatusMasterDTO, or with status {@code 400 (Bad Request)} if the wipStatusMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/wip-status-masters")
    public ResponseEntity<WipStatusMasterDTO> createWipStatusMaster(@RequestBody WipStatusMasterDTO wipStatusMasterDTO)
        throws URISyntaxException {
        log.debug("REST request to save WipStatusMaster : {}", wipStatusMasterDTO);
        if (wipStatusMasterDTO.getWipStatusId() != null) {
            throw new BadRequestAlertException("A new wipStatusMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WipStatusMasterDTO result = wipStatusMasterService.save(wipStatusMasterDTO);
        return ResponseEntity
            .created(new URI("/api/wip-status-masters/" + result.getWipStatusId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getWipStatusId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /wip-status-masters/:wipStatusId} : Updates an existing wipStatusMaster.
     *
     * @param wipStatusId the id of the wipStatusMasterDTO to save.
     * @param wipStatusMasterDTO the wipStatusMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated wipStatusMasterDTO,
     * or with status {@code 400 (Bad Request)} if the wipStatusMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the wipStatusMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/wip-status-masters/{wipStatusId}")
    public ResponseEntity<WipStatusMasterDTO> updateWipStatusMaster(
        @PathVariable(value = "wipStatusId", required = false) final Long wipStatusId,
        @RequestBody WipStatusMasterDTO wipStatusMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update WipStatusMaster : {}, {}", wipStatusId, wipStatusMasterDTO);
        if (wipStatusMasterDTO.getWipStatusId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(wipStatusId, wipStatusMasterDTO.getWipStatusId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!wipStatusMasterRepository.existsById(wipStatusId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        WipStatusMasterDTO result = wipStatusMasterService.update(wipStatusMasterDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, wipStatusMasterDTO.getWipStatusId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /wip-status-masters/:wipStatusId} : Partial updates given fields of an existing wipStatusMaster, field will ignore if it is null
     *
     * @param wipStatusId the id of the wipStatusMasterDTO to save.
     * @param wipStatusMasterDTO the wipStatusMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated wipStatusMasterDTO,
     * or with status {@code 400 (Bad Request)} if the wipStatusMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the wipStatusMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the wipStatusMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/wip-status-masters/{wipStatusId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<WipStatusMasterDTO> partialUpdateWipStatusMaster(
        @PathVariable(value = "wipStatusId", required = false) final Long wipStatusId,
        @RequestBody WipStatusMasterDTO wipStatusMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update WipStatusMaster partially : {}, {}", wipStatusId, wipStatusMasterDTO);
        if (wipStatusMasterDTO.getWipStatusId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(wipStatusId, wipStatusMasterDTO.getWipStatusId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!wipStatusMasterRepository.existsById(wipStatusId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<WipStatusMasterDTO> result = wipStatusMasterService.partialUpdate(wipStatusMasterDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, wipStatusMasterDTO.getWipStatusId().toString())
        );
    }

    /**
     * {@code GET  /wip-status-masters} : get all the wipStatusMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of wipStatusMasters in body.
     */
    @GetMapping("/wip-status-masters")
    public ResponseEntity<List<WipStatusMasterDTO>> getAllWipStatusMasters(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of WipStatusMasters");
        Page<WipStatusMasterDTO> page = wipStatusMasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /wip-status-masters/:id} : get the "id" wipStatusMaster.
     *
     * @param id the id of the wipStatusMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the wipStatusMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/wip-status-masters/{id}")
    public ResponseEntity<WipStatusMasterDTO> getWipStatusMaster(@PathVariable Long id) {
        log.debug("REST request to get WipStatusMaster : {}", id);
        Optional<WipStatusMasterDTO> wipStatusMasterDTO = wipStatusMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(wipStatusMasterDTO);
    }

    /**
     * {@code DELETE  /wip-status-masters/:id} : delete the "id" wipStatusMaster.
     *
     * @param id the id of the wipStatusMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/wip-status-masters/{id}")
    public ResponseEntity<Void> deleteWipStatusMaster(@PathVariable Long id) {
        log.debug("REST request to delete WipStatusMaster : {}", id);
        wipStatusMasterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
