package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.PrimaryResubmissionServiceLinesMasterRepository;
import com.sunknowledge.dme.rcm.service.PrimaryResubmissionServiceLinesMasterService;
import com.sunknowledge.dme.rcm.service.dto.PrimaryResubmissionServiceLinesMasterDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.PrimaryResubmissionServiceLinesMaster}.
 */
@RestController
@RequestMapping("/api")
public class PrimaryResubmissionServiceLinesMasterResource {

    private final Logger log = LoggerFactory.getLogger(PrimaryResubmissionServiceLinesMasterResource.class);

    private static final String ENTITY_NAME = "claimsPrimaryResubmissionServiceLinesMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PrimaryResubmissionServiceLinesMasterService primaryResubmissionServiceLinesMasterService;

    private final PrimaryResubmissionServiceLinesMasterRepository primaryResubmissionServiceLinesMasterRepository;

    public PrimaryResubmissionServiceLinesMasterResource(
        PrimaryResubmissionServiceLinesMasterService primaryResubmissionServiceLinesMasterService,
        PrimaryResubmissionServiceLinesMasterRepository primaryResubmissionServiceLinesMasterRepository
    ) {
        this.primaryResubmissionServiceLinesMasterService = primaryResubmissionServiceLinesMasterService;
        this.primaryResubmissionServiceLinesMasterRepository = primaryResubmissionServiceLinesMasterRepository;
    }

    /**
     * {@code POST  /primary-resubmission-service-lines-masters} : Create a new primaryResubmissionServiceLinesMaster.
     *
     * @param primaryResubmissionServiceLinesMasterDTO the primaryResubmissionServiceLinesMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new primaryResubmissionServiceLinesMasterDTO, or with status {@code 400 (Bad Request)} if the primaryResubmissionServiceLinesMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/primary-resubmission-service-lines-masters")
    public ResponseEntity<PrimaryResubmissionServiceLinesMasterDTO> createPrimaryResubmissionServiceLinesMaster(
        @Valid @RequestBody PrimaryResubmissionServiceLinesMasterDTO primaryResubmissionServiceLinesMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to save PrimaryResubmissionServiceLinesMaster : {}", primaryResubmissionServiceLinesMasterDTO);
        if (primaryResubmissionServiceLinesMasterDTO.getChangeHealthPrimaryResubmisionServicelinesId() != null) {
            throw new BadRequestAlertException(
                "A new primaryResubmissionServiceLinesMaster cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        PrimaryResubmissionServiceLinesMasterDTO result = primaryResubmissionServiceLinesMasterService.save(
            primaryResubmissionServiceLinesMasterDTO
        );
        return ResponseEntity
            .created(new URI("/api/primary-resubmission-service-lines-masters/" + result.getChangeHealthPrimaryResubmisionServicelinesId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    result.getChangeHealthPrimaryResubmisionServicelinesId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PUT  /primary-resubmission-service-lines-masters/:changeHealthPrimaryResubmisionServicelinesId} : Updates an existing primaryResubmissionServiceLinesMaster.
     *
     * @param changeHealthPrimaryResubmisionServicelinesId the id of the primaryResubmissionServiceLinesMasterDTO to save.
     * @param primaryResubmissionServiceLinesMasterDTO the primaryResubmissionServiceLinesMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated primaryResubmissionServiceLinesMasterDTO,
     * or with status {@code 400 (Bad Request)} if the primaryResubmissionServiceLinesMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the primaryResubmissionServiceLinesMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/primary-resubmission-service-lines-masters/{changeHealthPrimaryResubmisionServicelinesId}")
    public ResponseEntity<PrimaryResubmissionServiceLinesMasterDTO> updatePrimaryResubmissionServiceLinesMaster(
        @PathVariable(
            value = "changeHealthPrimaryResubmisionServicelinesId",
            required = false
        ) final Long changeHealthPrimaryResubmisionServicelinesId,
        @Valid @RequestBody PrimaryResubmissionServiceLinesMasterDTO primaryResubmissionServiceLinesMasterDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to update PrimaryResubmissionServiceLinesMaster : {}, {}",
            changeHealthPrimaryResubmisionServicelinesId,
            primaryResubmissionServiceLinesMasterDTO
        );
        if (primaryResubmissionServiceLinesMasterDTO.getChangeHealthPrimaryResubmisionServicelinesId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (
            !Objects.equals(
                changeHealthPrimaryResubmisionServicelinesId,
                primaryResubmissionServiceLinesMasterDTO.getChangeHealthPrimaryResubmisionServicelinesId()
            )
        ) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!primaryResubmissionServiceLinesMasterRepository.existsById(changeHealthPrimaryResubmisionServicelinesId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PrimaryResubmissionServiceLinesMasterDTO result = primaryResubmissionServiceLinesMasterService.update(
            primaryResubmissionServiceLinesMasterDTO
        );
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    primaryResubmissionServiceLinesMasterDTO.getChangeHealthPrimaryResubmisionServicelinesId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /primary-resubmission-service-lines-masters/:changeHealthPrimaryResubmisionServicelinesId} : Partial updates given fields of an existing primaryResubmissionServiceLinesMaster, field will ignore if it is null
     *
     * @param changeHealthPrimaryResubmisionServicelinesId the id of the primaryResubmissionServiceLinesMasterDTO to save.
     * @param primaryResubmissionServiceLinesMasterDTO the primaryResubmissionServiceLinesMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated primaryResubmissionServiceLinesMasterDTO,
     * or with status {@code 400 (Bad Request)} if the primaryResubmissionServiceLinesMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the primaryResubmissionServiceLinesMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the primaryResubmissionServiceLinesMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/primary-resubmission-service-lines-masters/{changeHealthPrimaryResubmisionServicelinesId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public ResponseEntity<PrimaryResubmissionServiceLinesMasterDTO> partialUpdatePrimaryResubmissionServiceLinesMaster(
        @PathVariable(
            value = "changeHealthPrimaryResubmisionServicelinesId",
            required = false
        ) final Long changeHealthPrimaryResubmisionServicelinesId,
        @NotNull @RequestBody PrimaryResubmissionServiceLinesMasterDTO primaryResubmissionServiceLinesMasterDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update PrimaryResubmissionServiceLinesMaster partially : {}, {}",
            changeHealthPrimaryResubmisionServicelinesId,
            primaryResubmissionServiceLinesMasterDTO
        );
        if (primaryResubmissionServiceLinesMasterDTO.getChangeHealthPrimaryResubmisionServicelinesId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (
            !Objects.equals(
                changeHealthPrimaryResubmisionServicelinesId,
                primaryResubmissionServiceLinesMasterDTO.getChangeHealthPrimaryResubmisionServicelinesId()
            )
        ) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!primaryResubmissionServiceLinesMasterRepository.existsById(changeHealthPrimaryResubmisionServicelinesId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PrimaryResubmissionServiceLinesMasterDTO> result = primaryResubmissionServiceLinesMasterService.partialUpdate(
            primaryResubmissionServiceLinesMasterDTO
        );

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                false,
                ENTITY_NAME,
                primaryResubmissionServiceLinesMasterDTO.getChangeHealthPrimaryResubmisionServicelinesId().toString()
            )
        );
    }

    /**
     * {@code GET  /primary-resubmission-service-lines-masters} : get all the primaryResubmissionServiceLinesMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of primaryResubmissionServiceLinesMasters in body.
     */
    @GetMapping("/primary-resubmission-service-lines-masters")
    public ResponseEntity<List<PrimaryResubmissionServiceLinesMasterDTO>> getAllPrimaryResubmissionServiceLinesMasters(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of PrimaryResubmissionServiceLinesMasters");
        Page<PrimaryResubmissionServiceLinesMasterDTO> page = primaryResubmissionServiceLinesMasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /primary-resubmission-service-lines-masters/:id} : get the "id" primaryResubmissionServiceLinesMaster.
     *
     * @param id the id of the primaryResubmissionServiceLinesMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the primaryResubmissionServiceLinesMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/primary-resubmission-service-lines-masters/{id}")
    public ResponseEntity<PrimaryResubmissionServiceLinesMasterDTO> getPrimaryResubmissionServiceLinesMaster(@PathVariable Long id) {
        log.debug("REST request to get PrimaryResubmissionServiceLinesMaster : {}", id);
        Optional<PrimaryResubmissionServiceLinesMasterDTO> primaryResubmissionServiceLinesMasterDTO = primaryResubmissionServiceLinesMasterService.findOne(
            id
        );
        return ResponseUtil.wrapOrNotFound(primaryResubmissionServiceLinesMasterDTO);
    }

    /**
     * {@code DELETE  /primary-resubmission-service-lines-masters/:id} : delete the "id" primaryResubmissionServiceLinesMaster.
     *
     * @param id the id of the primaryResubmissionServiceLinesMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/primary-resubmission-service-lines-masters/{id}")
    public ResponseEntity<Void> deletePrimaryResubmissionServiceLinesMaster(@PathVariable Long id) {
        log.debug("REST request to delete PrimaryResubmissionServiceLinesMaster : {}", id);
        primaryResubmissionServiceLinesMasterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
