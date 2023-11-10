package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.PrimaryClaimsReSubmissionMasterRepository;
import com.sunknowledge.dme.rcm.service.PrimaryClaimsReSubmissionMasterService;
import com.sunknowledge.dme.rcm.service.dto.PrimaryClaimsReSubmissionMasterDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.PrimaryClaimsReSubmissionMaster}.
 */
@RestController
@RequestMapping("/api")
public class PrimaryClaimsReSubmissionMasterResource {

    private final Logger log = LoggerFactory.getLogger(PrimaryClaimsReSubmissionMasterResource.class);

    private static final String ENTITY_NAME = "claimsPrimaryClaimsReSubmissionMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PrimaryClaimsReSubmissionMasterService primaryClaimsReSubmissionMasterService;

    private final PrimaryClaimsReSubmissionMasterRepository primaryClaimsReSubmissionMasterRepository;

    public PrimaryClaimsReSubmissionMasterResource(
        PrimaryClaimsReSubmissionMasterService primaryClaimsReSubmissionMasterService,
        PrimaryClaimsReSubmissionMasterRepository primaryClaimsReSubmissionMasterRepository
    ) {
        this.primaryClaimsReSubmissionMasterService = primaryClaimsReSubmissionMasterService;
        this.primaryClaimsReSubmissionMasterRepository = primaryClaimsReSubmissionMasterRepository;
    }

    /**
     * {@code POST  /primary-claims-re-submission-masters} : Create a new primaryClaimsReSubmissionMaster.
     *
     * @param primaryClaimsReSubmissionMasterDTO the primaryClaimsReSubmissionMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new primaryClaimsReSubmissionMasterDTO, or with status {@code 400 (Bad Request)} if the primaryClaimsReSubmissionMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/primary-claims-re-submission-masters")
    public ResponseEntity<PrimaryClaimsReSubmissionMasterDTO> createPrimaryClaimsReSubmissionMaster(
        @Valid @RequestBody PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to save PrimaryClaimsReSubmissionMaster : {}", primaryClaimsReSubmissionMasterDTO);
        if (primaryClaimsReSubmissionMasterDTO.getChangeHealthPrimaryResubmisionMasterId() != null) {
            throw new BadRequestAlertException("A new primaryClaimsReSubmissionMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PrimaryClaimsReSubmissionMasterDTO result = primaryClaimsReSubmissionMasterService.save(primaryClaimsReSubmissionMasterDTO);
        return ResponseEntity
            .created(new URI("/api/primary-claims-re-submission-masters/" + result.getChangeHealthPrimaryResubmisionMasterId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    result.getChangeHealthPrimaryResubmisionMasterId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PUT  /primary-claims-re-submission-masters/:changeHealthPrimaryResubmisionMasterId} : Updates an existing primaryClaimsReSubmissionMaster.
     *
     * @param changeHealthPrimaryResubmisionMasterId the id of the primaryClaimsReSubmissionMasterDTO to save.
     * @param primaryClaimsReSubmissionMasterDTO the primaryClaimsReSubmissionMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated primaryClaimsReSubmissionMasterDTO,
     * or with status {@code 400 (Bad Request)} if the primaryClaimsReSubmissionMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the primaryClaimsReSubmissionMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/primary-claims-re-submission-masters/{changeHealthPrimaryResubmisionMasterId}")
    public ResponseEntity<PrimaryClaimsReSubmissionMasterDTO> updatePrimaryClaimsReSubmissionMaster(
        @PathVariable(value = "changeHealthPrimaryResubmisionMasterId", required = false) final Long changeHealthPrimaryResubmisionMasterId,
        @Valid @RequestBody PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to update PrimaryClaimsReSubmissionMaster : {}, {}",
            changeHealthPrimaryResubmisionMasterId,
            primaryClaimsReSubmissionMasterDTO
        );
        if (primaryClaimsReSubmissionMasterDTO.getChangeHealthPrimaryResubmisionMasterId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (
            !Objects.equals(
                changeHealthPrimaryResubmisionMasterId,
                primaryClaimsReSubmissionMasterDTO.getChangeHealthPrimaryResubmisionMasterId()
            )
        ) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!primaryClaimsReSubmissionMasterRepository.existsById(changeHealthPrimaryResubmisionMasterId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PrimaryClaimsReSubmissionMasterDTO result = primaryClaimsReSubmissionMasterService.update(primaryClaimsReSubmissionMasterDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    primaryClaimsReSubmissionMasterDTO.getChangeHealthPrimaryResubmisionMasterId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /primary-claims-re-submission-masters/:changeHealthPrimaryResubmisionMasterId} : Partial updates given fields of an existing primaryClaimsReSubmissionMaster, field will ignore if it is null
     *
     * @param changeHealthPrimaryResubmisionMasterId the id of the primaryClaimsReSubmissionMasterDTO to save.
     * @param primaryClaimsReSubmissionMasterDTO the primaryClaimsReSubmissionMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated primaryClaimsReSubmissionMasterDTO,
     * or with status {@code 400 (Bad Request)} if the primaryClaimsReSubmissionMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the primaryClaimsReSubmissionMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the primaryClaimsReSubmissionMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/primary-claims-re-submission-masters/{changeHealthPrimaryResubmisionMasterId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public ResponseEntity<PrimaryClaimsReSubmissionMasterDTO> partialUpdatePrimaryClaimsReSubmissionMaster(
        @PathVariable(value = "changeHealthPrimaryResubmisionMasterId", required = false) final Long changeHealthPrimaryResubmisionMasterId,
        @NotNull @RequestBody PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update PrimaryClaimsReSubmissionMaster partially : {}, {}",
            changeHealthPrimaryResubmisionMasterId,
            primaryClaimsReSubmissionMasterDTO
        );
        if (primaryClaimsReSubmissionMasterDTO.getChangeHealthPrimaryResubmisionMasterId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (
            !Objects.equals(
                changeHealthPrimaryResubmisionMasterId,
                primaryClaimsReSubmissionMasterDTO.getChangeHealthPrimaryResubmisionMasterId()
            )
        ) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!primaryClaimsReSubmissionMasterRepository.existsById(changeHealthPrimaryResubmisionMasterId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PrimaryClaimsReSubmissionMasterDTO> result = primaryClaimsReSubmissionMasterService.partialUpdate(
            primaryClaimsReSubmissionMasterDTO
        );

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                false,
                ENTITY_NAME,
                primaryClaimsReSubmissionMasterDTO.getChangeHealthPrimaryResubmisionMasterId().toString()
            )
        );
    }

    /**
     * {@code GET  /primary-claims-re-submission-masters} : get all the primaryClaimsReSubmissionMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of primaryClaimsReSubmissionMasters in body.
     */
    @GetMapping("/primary-claims-re-submission-masters")
    public ResponseEntity<List<PrimaryClaimsReSubmissionMasterDTO>> getAllPrimaryClaimsReSubmissionMasters(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of PrimaryClaimsReSubmissionMasters");
        Page<PrimaryClaimsReSubmissionMasterDTO> page = primaryClaimsReSubmissionMasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /primary-claims-re-submission-masters/:id} : get the "id" primaryClaimsReSubmissionMaster.
     *
     * @param id the id of the primaryClaimsReSubmissionMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the primaryClaimsReSubmissionMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/primary-claims-re-submission-masters/{id}")
    public ResponseEntity<PrimaryClaimsReSubmissionMasterDTO> getPrimaryClaimsReSubmissionMaster(@PathVariable Long id) {
        log.debug("REST request to get PrimaryClaimsReSubmissionMaster : {}", id);
        Optional<PrimaryClaimsReSubmissionMasterDTO> primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterService.findOne(
            id
        );
        return ResponseUtil.wrapOrNotFound(primaryClaimsReSubmissionMasterDTO);
    }

    /**
     * {@code DELETE  /primary-claims-re-submission-masters/:id} : delete the "id" primaryClaimsReSubmissionMaster.
     *
     * @param id the id of the primaryClaimsReSubmissionMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/primary-claims-re-submission-masters/{id}")
    public ResponseEntity<Void> deletePrimaryClaimsReSubmissionMaster(@PathVariable Long id) {
        log.debug("REST request to delete PrimaryClaimsReSubmissionMaster : {}", id);
        primaryClaimsReSubmissionMasterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
