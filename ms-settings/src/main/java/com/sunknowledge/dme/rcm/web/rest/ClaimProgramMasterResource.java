package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ClaimProgramMasterRepository;
import com.sunknowledge.dme.rcm.service.ClaimProgramMasterService;
import com.sunknowledge.dme.rcm.service.dto.ClaimProgramMasterDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ClaimProgramMaster}.
 */
@RestController
@RequestMapping("/api")
public class ClaimProgramMasterResource {

    private final Logger log = LoggerFactory.getLogger(ClaimProgramMasterResource.class);

    private static final String ENTITY_NAME = "settingsClaimProgramMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClaimProgramMasterService claimProgramMasterService;

    private final ClaimProgramMasterRepository claimProgramMasterRepository;

    public ClaimProgramMasterResource(
        ClaimProgramMasterService claimProgramMasterService,
        ClaimProgramMasterRepository claimProgramMasterRepository
    ) {
        this.claimProgramMasterService = claimProgramMasterService;
        this.claimProgramMasterRepository = claimProgramMasterRepository;
    }

    /**
     * {@code POST  /claim-program-masters} : Create a new claimProgramMaster.
     *
     * @param claimProgramMasterDTO the claimProgramMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new claimProgramMasterDTO, or with status {@code 400 (Bad Request)} if the claimProgramMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/claim-program-masters")
    public ResponseEntity<ClaimProgramMasterDTO> createClaimProgramMaster(@RequestBody ClaimProgramMasterDTO claimProgramMasterDTO)
        throws URISyntaxException {
        log.debug("REST request to save ClaimProgramMaster : {}", claimProgramMasterDTO);
        if (claimProgramMasterDTO.getClaimProgramMasterId() != null) {
            throw new BadRequestAlertException("A new claimProgramMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClaimProgramMasterDTO result = claimProgramMasterService.save(claimProgramMasterDTO);
        return ResponseEntity
            .created(new URI("/api/claim-program-masters/" + result.getClaimProgramMasterId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getClaimProgramMasterId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /claim-program-masters/:claimProgramMasterId} : Updates an existing claimProgramMaster.
     *
     * @param claimProgramMasterId the id of the claimProgramMasterDTO to save.
     * @param claimProgramMasterDTO the claimProgramMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claimProgramMasterDTO,
     * or with status {@code 400 (Bad Request)} if the claimProgramMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the claimProgramMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/claim-program-masters/{claimProgramMasterId}")
    public ResponseEntity<ClaimProgramMasterDTO> updateClaimProgramMaster(
        @PathVariable(value = "claimProgramMasterId", required = false) final Long claimProgramMasterId,
        @RequestBody ClaimProgramMasterDTO claimProgramMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ClaimProgramMaster : {}, {}", claimProgramMasterId, claimProgramMasterDTO);
        if (claimProgramMasterDTO.getClaimProgramMasterId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(claimProgramMasterId, claimProgramMasterDTO.getClaimProgramMasterId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!claimProgramMasterRepository.existsById(claimProgramMasterId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ClaimProgramMasterDTO result = claimProgramMasterService.update(claimProgramMasterDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    claimProgramMasterDTO.getClaimProgramMasterId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /claim-program-masters/:claimProgramMasterId} : Partial updates given fields of an existing claimProgramMaster, field will ignore if it is null
     *
     * @param claimProgramMasterId the id of the claimProgramMasterDTO to save.
     * @param claimProgramMasterDTO the claimProgramMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claimProgramMasterDTO,
     * or with status {@code 400 (Bad Request)} if the claimProgramMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the claimProgramMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the claimProgramMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/claim-program-masters/{claimProgramMasterId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public ResponseEntity<ClaimProgramMasterDTO> partialUpdateClaimProgramMaster(
        @PathVariable(value = "claimProgramMasterId", required = false) final Long claimProgramMasterId,
        @RequestBody ClaimProgramMasterDTO claimProgramMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ClaimProgramMaster partially : {}, {}", claimProgramMasterId, claimProgramMasterDTO);
        if (claimProgramMasterDTO.getClaimProgramMasterId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(claimProgramMasterId, claimProgramMasterDTO.getClaimProgramMasterId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!claimProgramMasterRepository.existsById(claimProgramMasterId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ClaimProgramMasterDTO> result = claimProgramMasterService.partialUpdate(claimProgramMasterDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                false,
                ENTITY_NAME,
                claimProgramMasterDTO.getClaimProgramMasterId().toString()
            )
        );
    }

    /**
     * {@code GET  /claim-program-masters} : get all the claimProgramMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of claimProgramMasters in body.
     */
    @GetMapping("/claim-program-masters")
    public ResponseEntity<List<ClaimProgramMasterDTO>> getAllClaimProgramMasters(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ClaimProgramMasters");
        Page<ClaimProgramMasterDTO> page = claimProgramMasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /claim-program-masters/:id} : get the "id" claimProgramMaster.
     *
     * @param id the id of the claimProgramMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the claimProgramMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/claim-program-masters/{id}")
    public ResponseEntity<ClaimProgramMasterDTO> getClaimProgramMaster(@PathVariable Long id) {
        log.debug("REST request to get ClaimProgramMaster : {}", id);
        Optional<ClaimProgramMasterDTO> claimProgramMasterDTO = claimProgramMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(claimProgramMasterDTO);
    }

    /**
     * {@code DELETE  /claim-program-masters/:id} : delete the "id" claimProgramMaster.
     *
     * @param id the id of the claimProgramMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/claim-program-masters/{id}")
    public ResponseEntity<Void> deleteClaimProgramMaster(@PathVariable Long id) {
        log.debug("REST request to delete ClaimProgramMaster : {}", id);
        claimProgramMasterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
