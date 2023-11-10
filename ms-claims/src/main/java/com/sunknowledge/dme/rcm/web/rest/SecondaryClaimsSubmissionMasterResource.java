package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.SecondaryClaimsSubmissionMasterRepository;
import com.sunknowledge.dme.rcm.service.SecondaryClaimsSubmissionMasterService;
import com.sunknowledge.dme.rcm.service.dto.SecondaryClaimsSubmissionMasterDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.SecondaryClaimsSubmissionMaster}.
 */
@RestController
@RequestMapping("/api")
public class SecondaryClaimsSubmissionMasterResource {

    private final Logger log = LoggerFactory.getLogger(SecondaryClaimsSubmissionMasterResource.class);

    private static final String ENTITY_NAME = "claimsSecondaryClaimsSubmissionMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SecondaryClaimsSubmissionMasterService secondaryClaimsSubmissionMasterService;

    private final SecondaryClaimsSubmissionMasterRepository secondaryClaimsSubmissionMasterRepository;

    public SecondaryClaimsSubmissionMasterResource(
        SecondaryClaimsSubmissionMasterService secondaryClaimsSubmissionMasterService,
        SecondaryClaimsSubmissionMasterRepository secondaryClaimsSubmissionMasterRepository
    ) {
        this.secondaryClaimsSubmissionMasterService = secondaryClaimsSubmissionMasterService;
        this.secondaryClaimsSubmissionMasterRepository = secondaryClaimsSubmissionMasterRepository;
    }

    /**
     * {@code POST  /secondary-claims-submission-masters} : Create a new secondaryClaimsSubmissionMaster.
     *
     * @param secondaryClaimsSubmissionMasterDTO the secondaryClaimsSubmissionMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new secondaryClaimsSubmissionMasterDTO, or with status {@code 400 (Bad Request)} if the secondaryClaimsSubmissionMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/secondary-claims-submission-masters")
    public ResponseEntity<SecondaryClaimsSubmissionMasterDTO> createSecondaryClaimsSubmissionMaster(
        @Valid @RequestBody SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to save SecondaryClaimsSubmissionMaster : {}", secondaryClaimsSubmissionMasterDTO);
        if (secondaryClaimsSubmissionMasterDTO.getChangeHealthSecondarySubmisionMasterId() != null) {
            throw new BadRequestAlertException("A new secondaryClaimsSubmissionMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SecondaryClaimsSubmissionMasterDTO result = secondaryClaimsSubmissionMasterService.save(secondaryClaimsSubmissionMasterDTO);
        return ResponseEntity
            .created(new URI("/api/secondary-claims-submission-masters/" + result.getChangeHealthSecondarySubmisionMasterId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    result.getChangeHealthSecondarySubmisionMasterId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PUT  /secondary-claims-submission-masters/:changeHealthSecondarySubmisionMasterId} : Updates an existing secondaryClaimsSubmissionMaster.
     *
     * @param changeHealthSecondarySubmisionMasterId the id of the secondaryClaimsSubmissionMasterDTO to save.
     * @param secondaryClaimsSubmissionMasterDTO the secondaryClaimsSubmissionMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated secondaryClaimsSubmissionMasterDTO,
     * or with status {@code 400 (Bad Request)} if the secondaryClaimsSubmissionMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the secondaryClaimsSubmissionMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/secondary-claims-submission-masters/{changeHealthSecondarySubmisionMasterId}")
    public ResponseEntity<SecondaryClaimsSubmissionMasterDTO> updateSecondaryClaimsSubmissionMaster(
        @PathVariable(value = "changeHealthSecondarySubmisionMasterId", required = false) final Long changeHealthSecondarySubmisionMasterId,
        @Valid @RequestBody SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to update SecondaryClaimsSubmissionMaster : {}, {}",
            changeHealthSecondarySubmisionMasterId,
            secondaryClaimsSubmissionMasterDTO
        );
        if (secondaryClaimsSubmissionMasterDTO.getChangeHealthSecondarySubmisionMasterId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (
            !Objects.equals(
                changeHealthSecondarySubmisionMasterId,
                secondaryClaimsSubmissionMasterDTO.getChangeHealthSecondarySubmisionMasterId()
            )
        ) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!secondaryClaimsSubmissionMasterRepository.existsById(changeHealthSecondarySubmisionMasterId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SecondaryClaimsSubmissionMasterDTO result = secondaryClaimsSubmissionMasterService.update(secondaryClaimsSubmissionMasterDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    secondaryClaimsSubmissionMasterDTO.getChangeHealthSecondarySubmisionMasterId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /secondary-claims-submission-masters/:changeHealthSecondarySubmisionMasterId} : Partial updates given fields of an existing secondaryClaimsSubmissionMaster, field will ignore if it is null
     *
     * @param changeHealthSecondarySubmisionMasterId the id of the secondaryClaimsSubmissionMasterDTO to save.
     * @param secondaryClaimsSubmissionMasterDTO the secondaryClaimsSubmissionMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated secondaryClaimsSubmissionMasterDTO,
     * or with status {@code 400 (Bad Request)} if the secondaryClaimsSubmissionMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the secondaryClaimsSubmissionMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the secondaryClaimsSubmissionMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/secondary-claims-submission-masters/{changeHealthSecondarySubmisionMasterId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public ResponseEntity<SecondaryClaimsSubmissionMasterDTO> partialUpdateSecondaryClaimsSubmissionMaster(
        @PathVariable(value = "changeHealthSecondarySubmisionMasterId", required = false) final Long changeHealthSecondarySubmisionMasterId,
        @NotNull @RequestBody SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update SecondaryClaimsSubmissionMaster partially : {}, {}",
            changeHealthSecondarySubmisionMasterId,
            secondaryClaimsSubmissionMasterDTO
        );
        if (secondaryClaimsSubmissionMasterDTO.getChangeHealthSecondarySubmisionMasterId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (
            !Objects.equals(
                changeHealthSecondarySubmisionMasterId,
                secondaryClaimsSubmissionMasterDTO.getChangeHealthSecondarySubmisionMasterId()
            )
        ) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!secondaryClaimsSubmissionMasterRepository.existsById(changeHealthSecondarySubmisionMasterId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SecondaryClaimsSubmissionMasterDTO> result = secondaryClaimsSubmissionMasterService.partialUpdate(
            secondaryClaimsSubmissionMasterDTO
        );

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                false,
                ENTITY_NAME,
                secondaryClaimsSubmissionMasterDTO.getChangeHealthSecondarySubmisionMasterId().toString()
            )
        );
    }

    /**
     * {@code GET  /secondary-claims-submission-masters} : get all the secondaryClaimsSubmissionMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of secondaryClaimsSubmissionMasters in body.
     */
    @GetMapping("/secondary-claims-submission-masters")
    public ResponseEntity<List<SecondaryClaimsSubmissionMasterDTO>> getAllSecondaryClaimsSubmissionMasters(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of SecondaryClaimsSubmissionMasters");
        Page<SecondaryClaimsSubmissionMasterDTO> page = secondaryClaimsSubmissionMasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /secondary-claims-submission-masters/:id} : get the "id" secondaryClaimsSubmissionMaster.
     *
     * @param id the id of the secondaryClaimsSubmissionMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the secondaryClaimsSubmissionMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/secondary-claims-submission-masters/{id}")
    public ResponseEntity<SecondaryClaimsSubmissionMasterDTO> getSecondaryClaimsSubmissionMaster(@PathVariable Long id) {
        log.debug("REST request to get SecondaryClaimsSubmissionMaster : {}", id);
        Optional<SecondaryClaimsSubmissionMasterDTO> secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterService.findOne(
            id
        );
        return ResponseUtil.wrapOrNotFound(secondaryClaimsSubmissionMasterDTO);
    }

    /**
     * {@code DELETE  /secondary-claims-submission-masters/:id} : delete the "id" secondaryClaimsSubmissionMaster.
     *
     * @param id the id of the secondaryClaimsSubmissionMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/secondary-claims-submission-masters/{id}")
    public ResponseEntity<Void> deleteSecondaryClaimsSubmissionMaster(@PathVariable Long id) {
        log.debug("REST request to delete SecondaryClaimsSubmissionMaster : {}", id);
        secondaryClaimsSubmissionMasterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
