package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ClaimFormMasterRepository;
import com.sunknowledge.dme.rcm.service.ClaimFormMasterService;
import com.sunknowledge.dme.rcm.service.dto.ClaimFormMasterDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ClaimFormMaster}.
 */
@RestController
@RequestMapping("/api")
public class ClaimFormMasterResource {

    private final Logger log = LoggerFactory.getLogger(ClaimFormMasterResource.class);

    private static final String ENTITY_NAME = "settingsClaimFormMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClaimFormMasterService claimFormMasterService;

    private final ClaimFormMasterRepository claimFormMasterRepository;

    public ClaimFormMasterResource(ClaimFormMasterService claimFormMasterService, ClaimFormMasterRepository claimFormMasterRepository) {
        this.claimFormMasterService = claimFormMasterService;
        this.claimFormMasterRepository = claimFormMasterRepository;
    }

    /**
     * {@code POST  /claim-form-masters} : Create a new claimFormMaster.
     *
     * @param claimFormMasterDTO the claimFormMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new claimFormMasterDTO, or with status {@code 400 (Bad Request)} if the claimFormMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/claim-form-masters")
    public ResponseEntity<ClaimFormMasterDTO> createClaimFormMaster(@RequestBody ClaimFormMasterDTO claimFormMasterDTO)
        throws URISyntaxException {
        log.debug("REST request to save ClaimFormMaster : {}", claimFormMasterDTO);
        if (claimFormMasterDTO.getClaimFormId() != null) {
            throw new BadRequestAlertException("A new claimFormMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClaimFormMasterDTO result = claimFormMasterService.save(claimFormMasterDTO);
        return ResponseEntity
            .created(new URI("/api/claim-form-masters/" + result.getClaimFormId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getClaimFormId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /claim-form-masters/:claimFormId} : Updates an existing claimFormMaster.
     *
     * @param claimFormId the id of the claimFormMasterDTO to save.
     * @param claimFormMasterDTO the claimFormMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claimFormMasterDTO,
     * or with status {@code 400 (Bad Request)} if the claimFormMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the claimFormMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/claim-form-masters/{claimFormId}")
    public ResponseEntity<ClaimFormMasterDTO> updateClaimFormMaster(
        @PathVariable(value = "claimFormId", required = false) final Long claimFormId,
        @RequestBody ClaimFormMasterDTO claimFormMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ClaimFormMaster : {}, {}", claimFormId, claimFormMasterDTO);
        if (claimFormMasterDTO.getClaimFormId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(claimFormId, claimFormMasterDTO.getClaimFormId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!claimFormMasterRepository.existsById(claimFormId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ClaimFormMasterDTO result = claimFormMasterService.update(claimFormMasterDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, claimFormMasterDTO.getClaimFormId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /claim-form-masters/:claimFormId} : Partial updates given fields of an existing claimFormMaster, field will ignore if it is null
     *
     * @param claimFormId the id of the claimFormMasterDTO to save.
     * @param claimFormMasterDTO the claimFormMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claimFormMasterDTO,
     * or with status {@code 400 (Bad Request)} if the claimFormMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the claimFormMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the claimFormMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/claim-form-masters/{claimFormId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ClaimFormMasterDTO> partialUpdateClaimFormMaster(
        @PathVariable(value = "claimFormId", required = false) final Long claimFormId,
        @RequestBody ClaimFormMasterDTO claimFormMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ClaimFormMaster partially : {}, {}", claimFormId, claimFormMasterDTO);
        if (claimFormMasterDTO.getClaimFormId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(claimFormId, claimFormMasterDTO.getClaimFormId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!claimFormMasterRepository.existsById(claimFormId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ClaimFormMasterDTO> result = claimFormMasterService.partialUpdate(claimFormMasterDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, claimFormMasterDTO.getClaimFormId().toString())
        );
    }

    /**
     * {@code GET  /claim-form-masters} : get all the claimFormMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of claimFormMasters in body.
     */
    @GetMapping("/claim-form-masters")
    public ResponseEntity<List<ClaimFormMasterDTO>> getAllClaimFormMasters(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ClaimFormMasters");
        Page<ClaimFormMasterDTO> page = claimFormMasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /claim-form-masters/:id} : get the "id" claimFormMaster.
     *
     * @param id the id of the claimFormMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the claimFormMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/claim-form-masters/{id}")
    public ResponseEntity<ClaimFormMasterDTO> getClaimFormMaster(@PathVariable Long id) {
        log.debug("REST request to get ClaimFormMaster : {}", id);
        Optional<ClaimFormMasterDTO> claimFormMasterDTO = claimFormMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(claimFormMasterDTO);
    }

    /**
     * {@code DELETE  /claim-form-masters/:id} : delete the "id" claimFormMaster.
     *
     * @param id the id of the claimFormMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/claim-form-masters/{id}")
    public ResponseEntity<Void> deleteClaimFormMaster(@PathVariable Long id) {
        log.debug("REST request to delete ClaimFormMaster : {}", id);
        claimFormMasterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
