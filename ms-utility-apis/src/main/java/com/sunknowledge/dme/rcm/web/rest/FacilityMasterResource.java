package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.FacilityMasterRepository;
import com.sunknowledge.dme.rcm.service.FacilityMasterService;
import com.sunknowledge.dme.rcm.service.dto.FacilityMasterDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.FacilityMaster}.
 */
@RestController
@RequestMapping("/api")
public class FacilityMasterResource {

    private final Logger log = LoggerFactory.getLogger(FacilityMasterResource.class);

    private static final String ENTITY_NAME = "utilityapisFacilityMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FacilityMasterService facilityMasterService;

    private final FacilityMasterRepository facilityMasterRepository;

    public FacilityMasterResource(FacilityMasterService facilityMasterService, FacilityMasterRepository facilityMasterRepository) {
        this.facilityMasterService = facilityMasterService;
        this.facilityMasterRepository = facilityMasterRepository;
    }

    /**
     * {@code POST  /facility-masters} : Create a new facilityMaster.
     *
     * @param facilityMasterDTO the facilityMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new facilityMasterDTO, or with status {@code 400 (Bad Request)} if the facilityMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/facility-masters")
    public ResponseEntity<FacilityMasterDTO> createFacilityMaster(@RequestBody FacilityMasterDTO facilityMasterDTO)
        throws URISyntaxException {
        log.debug("REST request to save FacilityMaster : {}", facilityMasterDTO);
        if (facilityMasterDTO.getFacilityId() != null) {
            throw new BadRequestAlertException("A new facilityMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FacilityMasterDTO result = facilityMasterService.save(facilityMasterDTO);
        return ResponseEntity
            .created(new URI("/api/facility-masters/" + result.getFacilityId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getFacilityId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /facility-masters/:facilityId} : Updates an existing facilityMaster.
     *
     * @param facilityId the id of the facilityMasterDTO to save.
     * @param facilityMasterDTO the facilityMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated facilityMasterDTO,
     * or with status {@code 400 (Bad Request)} if the facilityMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the facilityMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/facility-masters/{facilityId}")
    public ResponseEntity<FacilityMasterDTO> updateFacilityMaster(
        @PathVariable(value = "facilityId", required = false) final Long facilityId,
        @RequestBody FacilityMasterDTO facilityMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update FacilityMaster : {}, {}", facilityId, facilityMasterDTO);
        if (facilityMasterDTO.getFacilityId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(facilityId, facilityMasterDTO.getFacilityId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!facilityMasterRepository.existsById(facilityId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        FacilityMasterDTO result = facilityMasterService.update(facilityMasterDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, facilityMasterDTO.getFacilityId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /facility-masters/:facilityId} : Partial updates given fields of an existing facilityMaster, field will ignore if it is null
     *
     * @param facilityId the id of the facilityMasterDTO to save.
     * @param facilityMasterDTO the facilityMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated facilityMasterDTO,
     * or with status {@code 400 (Bad Request)} if the facilityMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the facilityMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the facilityMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/facility-masters/{facilityId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<FacilityMasterDTO> partialUpdateFacilityMaster(
        @PathVariable(value = "facilityId", required = false) final Long facilityId,
        @RequestBody FacilityMasterDTO facilityMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update FacilityMaster partially : {}, {}", facilityId, facilityMasterDTO);
        if (facilityMasterDTO.getFacilityId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(facilityId, facilityMasterDTO.getFacilityId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!facilityMasterRepository.existsById(facilityId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FacilityMasterDTO> result = facilityMasterService.partialUpdate(facilityMasterDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, facilityMasterDTO.getFacilityId().toString())
        );
    }

    /**
     * {@code GET  /facility-masters} : get all the facilityMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of facilityMasters in body.
     */
    @GetMapping("/facility-masters")
    public ResponseEntity<List<FacilityMasterDTO>> getAllFacilityMasters(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of FacilityMasters");
        Page<FacilityMasterDTO> page = facilityMasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /facility-masters/:id} : get the "id" facilityMaster.
     *
     * @param id the id of the facilityMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the facilityMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/facility-masters/{id}")
    public ResponseEntity<FacilityMasterDTO> getFacilityMaster(@PathVariable Long id) {
        log.debug("REST request to get FacilityMaster : {}", id);
        Optional<FacilityMasterDTO> facilityMasterDTO = facilityMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(facilityMasterDTO);
    }

    /**
     * {@code DELETE  /facility-masters/:id} : delete the "id" facilityMaster.
     *
     * @param id the id of the facilityMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/facility-masters/{id}")
    public ResponseEntity<Void> deleteFacilityMaster(@PathVariable Long id) {
        log.debug("REST request to delete FacilityMaster : {}", id);
        facilityMasterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
