package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ClaimsSubmissionMasterRepository;
import com.sunknowledge.dme.rcm.service.ClaimsSubmissionMasterService;
import com.sunknowledge.dme.rcm.service.dto.ClaimsSubmissionMasterDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ClaimsSubmissionMaster}.
 */
@RestController
@RequestMapping("/api")
public class ClaimsSubmissionMasterResource {

    private final Logger log = LoggerFactory.getLogger(ClaimsSubmissionMasterResource.class);

    private static final String ENTITY_NAME = "claimsClaimsSubmissionMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClaimsSubmissionMasterService claimsSubmissionMasterService;

    private final ClaimsSubmissionMasterRepository claimsSubmissionMasterRepository;

    public ClaimsSubmissionMasterResource(
        ClaimsSubmissionMasterService claimsSubmissionMasterService,
        ClaimsSubmissionMasterRepository claimsSubmissionMasterRepository
    ) {
        this.claimsSubmissionMasterService = claimsSubmissionMasterService;
        this.claimsSubmissionMasterRepository = claimsSubmissionMasterRepository;
    }

    /**
     * {@code POST  /claims-submission-masters} : Create a new claimsSubmissionMaster.
     *
     * @param claimsSubmissionMasterDTO the claimsSubmissionMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new claimsSubmissionMasterDTO, or with status {@code 400 (Bad Request)} if the claimsSubmissionMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/claims-submission-masters")
    public ResponseEntity<ClaimsSubmissionMasterDTO> createClaimsSubmissionMaster(
        @Valid @RequestBody ClaimsSubmissionMasterDTO claimsSubmissionMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to save ClaimsSubmissionMaster : {}", claimsSubmissionMasterDTO);
        if (claimsSubmissionMasterDTO.getChangeHealthPrimarySubmisionMasterId() != null) {
            throw new BadRequestAlertException("A new claimsSubmissionMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClaimsSubmissionMasterDTO result = claimsSubmissionMasterService.save(claimsSubmissionMasterDTO);
        return ResponseEntity
            .created(new URI("/api/claims-submission-masters/" + result.getChangeHealthPrimarySubmisionMasterId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    result.getChangeHealthPrimarySubmisionMasterId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PUT  /claims-submission-masters/:changeHealthPrimarySubmisionMasterId} : Updates an existing claimsSubmissionMaster.
     *
     * @param changeHealthPrimarySubmisionMasterId the id of the claimsSubmissionMasterDTO to save.
     * @param claimsSubmissionMasterDTO the claimsSubmissionMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claimsSubmissionMasterDTO,
     * or with status {@code 400 (Bad Request)} if the claimsSubmissionMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the claimsSubmissionMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/claims-submission-masters/{changeHealthPrimarySubmisionMasterId}")
    public ResponseEntity<ClaimsSubmissionMasterDTO> updateClaimsSubmissionMaster(
        @PathVariable(value = "changeHealthPrimarySubmisionMasterId", required = false) final Long changeHealthPrimarySubmisionMasterId,
        @Valid @RequestBody ClaimsSubmissionMasterDTO claimsSubmissionMasterDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to update ClaimsSubmissionMaster : {}, {}",
            changeHealthPrimarySubmisionMasterId,
            claimsSubmissionMasterDTO
        );
        if (claimsSubmissionMasterDTO.getChangeHealthPrimarySubmisionMasterId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(changeHealthPrimarySubmisionMasterId, claimsSubmissionMasterDTO.getChangeHealthPrimarySubmisionMasterId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!claimsSubmissionMasterRepository.existsById(changeHealthPrimarySubmisionMasterId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ClaimsSubmissionMasterDTO result = claimsSubmissionMasterService.update(claimsSubmissionMasterDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    claimsSubmissionMasterDTO.getChangeHealthPrimarySubmisionMasterId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /claims-submission-masters/:changeHealthPrimarySubmisionMasterId} : Partial updates given fields of an existing claimsSubmissionMaster, field will ignore if it is null
     *
     * @param changeHealthPrimarySubmisionMasterId the id of the claimsSubmissionMasterDTO to save.
     * @param claimsSubmissionMasterDTO the claimsSubmissionMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claimsSubmissionMasterDTO,
     * or with status {@code 400 (Bad Request)} if the claimsSubmissionMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the claimsSubmissionMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the claimsSubmissionMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/claims-submission-masters/{changeHealthPrimarySubmisionMasterId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public ResponseEntity<ClaimsSubmissionMasterDTO> partialUpdateClaimsSubmissionMaster(
        @PathVariable(value = "changeHealthPrimarySubmisionMasterId", required = false) final Long changeHealthPrimarySubmisionMasterId,
        @NotNull @RequestBody ClaimsSubmissionMasterDTO claimsSubmissionMasterDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update ClaimsSubmissionMaster partially : {}, {}",
            changeHealthPrimarySubmisionMasterId,
            claimsSubmissionMasterDTO
        );
        if (claimsSubmissionMasterDTO.getChangeHealthPrimarySubmisionMasterId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(changeHealthPrimarySubmisionMasterId, claimsSubmissionMasterDTO.getChangeHealthPrimarySubmisionMasterId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!claimsSubmissionMasterRepository.existsById(changeHealthPrimarySubmisionMasterId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ClaimsSubmissionMasterDTO> result = claimsSubmissionMasterService.partialUpdate(claimsSubmissionMasterDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                false,
                ENTITY_NAME,
                claimsSubmissionMasterDTO.getChangeHealthPrimarySubmisionMasterId().toString()
            )
        );
    }

    /**
     * {@code GET  /claims-submission-masters} : get all the claimsSubmissionMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of claimsSubmissionMasters in body.
     */
    @GetMapping("/claims-submission-masters")
    public ResponseEntity<List<ClaimsSubmissionMasterDTO>> getAllClaimsSubmissionMasters(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ClaimsSubmissionMasters");
        Page<ClaimsSubmissionMasterDTO> page = claimsSubmissionMasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /claims-submission-masters/:id} : get the "id" claimsSubmissionMaster.
     *
     * @param id the id of the claimsSubmissionMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the claimsSubmissionMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/claims-submission-masters/{id}")
    public ResponseEntity<ClaimsSubmissionMasterDTO> getClaimsSubmissionMaster(@PathVariable Long id) {
        log.debug("REST request to get ClaimsSubmissionMaster : {}", id);
        Optional<ClaimsSubmissionMasterDTO> claimsSubmissionMasterDTO = claimsSubmissionMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(claimsSubmissionMasterDTO);
    }

    /**
     * {@code DELETE  /claims-submission-masters/:id} : delete the "id" claimsSubmissionMaster.
     *
     * @param id the id of the claimsSubmissionMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/claims-submission-masters/{id}")
    public ResponseEntity<Void> deleteClaimsSubmissionMaster(@PathVariable Long id) {
        log.debug("REST request to delete ClaimsSubmissionMaster : {}", id);
        claimsSubmissionMasterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
