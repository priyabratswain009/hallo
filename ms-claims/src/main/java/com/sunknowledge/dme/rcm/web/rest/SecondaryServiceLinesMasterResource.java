package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.SecondaryServiceLinesMasterRepository;
import com.sunknowledge.dme.rcm.service.SecondaryServiceLinesMasterService;
import com.sunknowledge.dme.rcm.service.dto.SecondaryServiceLinesMasterDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.SecondaryServiceLinesMaster}.
 */
@RestController
@RequestMapping("/api")
public class SecondaryServiceLinesMasterResource {

    private final Logger log = LoggerFactory.getLogger(SecondaryServiceLinesMasterResource.class);

    private static final String ENTITY_NAME = "claimsSecondaryServiceLinesMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SecondaryServiceLinesMasterService secondaryServiceLinesMasterService;

    private final SecondaryServiceLinesMasterRepository secondaryServiceLinesMasterRepository;

    public SecondaryServiceLinesMasterResource(
        SecondaryServiceLinesMasterService secondaryServiceLinesMasterService,
        SecondaryServiceLinesMasterRepository secondaryServiceLinesMasterRepository
    ) {
        this.secondaryServiceLinesMasterService = secondaryServiceLinesMasterService;
        this.secondaryServiceLinesMasterRepository = secondaryServiceLinesMasterRepository;
    }

    /**
     * {@code POST  /secondary-service-lines-masters} : Create a new secondaryServiceLinesMaster.
     *
     * @param secondaryServiceLinesMasterDTO the secondaryServiceLinesMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new secondaryServiceLinesMasterDTO, or with status {@code 400 (Bad Request)} if the secondaryServiceLinesMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/secondary-service-lines-masters")
    public ResponseEntity<SecondaryServiceLinesMasterDTO> createSecondaryServiceLinesMaster(
        @Valid @RequestBody SecondaryServiceLinesMasterDTO secondaryServiceLinesMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to save SecondaryServiceLinesMaster : {}", secondaryServiceLinesMasterDTO);
        if (secondaryServiceLinesMasterDTO.getChangeHealthSecondarySubmisionServicelinesId() != null) {
            throw new BadRequestAlertException("A new secondaryServiceLinesMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SecondaryServiceLinesMasterDTO result = secondaryServiceLinesMasterService.save(secondaryServiceLinesMasterDTO);
        return ResponseEntity
            .created(new URI("/api/secondary-service-lines-masters/" + result.getChangeHealthSecondarySubmisionServicelinesId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    result.getChangeHealthSecondarySubmisionServicelinesId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PUT  /secondary-service-lines-masters/:changeHealthSecondarySubmisionServicelinesId} : Updates an existing secondaryServiceLinesMaster.
     *
     * @param changeHealthSecondarySubmisionServicelinesId the id of the secondaryServiceLinesMasterDTO to save.
     * @param secondaryServiceLinesMasterDTO the secondaryServiceLinesMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated secondaryServiceLinesMasterDTO,
     * or with status {@code 400 (Bad Request)} if the secondaryServiceLinesMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the secondaryServiceLinesMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/secondary-service-lines-masters/{changeHealthSecondarySubmisionServicelinesId}")
    public ResponseEntity<SecondaryServiceLinesMasterDTO> updateSecondaryServiceLinesMaster(
        @PathVariable(
            value = "changeHealthSecondarySubmisionServicelinesId",
            required = false
        ) final Long changeHealthSecondarySubmisionServicelinesId,
        @Valid @RequestBody SecondaryServiceLinesMasterDTO secondaryServiceLinesMasterDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to update SecondaryServiceLinesMaster : {}, {}",
            changeHealthSecondarySubmisionServicelinesId,
            secondaryServiceLinesMasterDTO
        );
        if (secondaryServiceLinesMasterDTO.getChangeHealthSecondarySubmisionServicelinesId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (
            !Objects.equals(
                changeHealthSecondarySubmisionServicelinesId,
                secondaryServiceLinesMasterDTO.getChangeHealthSecondarySubmisionServicelinesId()
            )
        ) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!secondaryServiceLinesMasterRepository.existsById(changeHealthSecondarySubmisionServicelinesId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SecondaryServiceLinesMasterDTO result = secondaryServiceLinesMasterService.update(secondaryServiceLinesMasterDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    secondaryServiceLinesMasterDTO.getChangeHealthSecondarySubmisionServicelinesId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /secondary-service-lines-masters/:changeHealthSecondarySubmisionServicelinesId} : Partial updates given fields of an existing secondaryServiceLinesMaster, field will ignore if it is null
     *
     * @param changeHealthSecondarySubmisionServicelinesId the id of the secondaryServiceLinesMasterDTO to save.
     * @param secondaryServiceLinesMasterDTO the secondaryServiceLinesMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated secondaryServiceLinesMasterDTO,
     * or with status {@code 400 (Bad Request)} if the secondaryServiceLinesMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the secondaryServiceLinesMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the secondaryServiceLinesMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/secondary-service-lines-masters/{changeHealthSecondarySubmisionServicelinesId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public ResponseEntity<SecondaryServiceLinesMasterDTO> partialUpdateSecondaryServiceLinesMaster(
        @PathVariable(
            value = "changeHealthSecondarySubmisionServicelinesId",
            required = false
        ) final Long changeHealthSecondarySubmisionServicelinesId,
        @NotNull @RequestBody SecondaryServiceLinesMasterDTO secondaryServiceLinesMasterDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update SecondaryServiceLinesMaster partially : {}, {}",
            changeHealthSecondarySubmisionServicelinesId,
            secondaryServiceLinesMasterDTO
        );
        if (secondaryServiceLinesMasterDTO.getChangeHealthSecondarySubmisionServicelinesId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (
            !Objects.equals(
                changeHealthSecondarySubmisionServicelinesId,
                secondaryServiceLinesMasterDTO.getChangeHealthSecondarySubmisionServicelinesId()
            )
        ) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!secondaryServiceLinesMasterRepository.existsById(changeHealthSecondarySubmisionServicelinesId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SecondaryServiceLinesMasterDTO> result = secondaryServiceLinesMasterService.partialUpdate(secondaryServiceLinesMasterDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                false,
                ENTITY_NAME,
                secondaryServiceLinesMasterDTO.getChangeHealthSecondarySubmisionServicelinesId().toString()
            )
        );
    }

    /**
     * {@code GET  /secondary-service-lines-masters} : get all the secondaryServiceLinesMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of secondaryServiceLinesMasters in body.
     */
    @GetMapping("/secondary-service-lines-masters")
    public ResponseEntity<List<SecondaryServiceLinesMasterDTO>> getAllSecondaryServiceLinesMasters(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of SecondaryServiceLinesMasters");
        Page<SecondaryServiceLinesMasterDTO> page = secondaryServiceLinesMasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /secondary-service-lines-masters/:id} : get the "id" secondaryServiceLinesMaster.
     *
     * @param id the id of the secondaryServiceLinesMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the secondaryServiceLinesMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/secondary-service-lines-masters/{id}")
    public ResponseEntity<SecondaryServiceLinesMasterDTO> getSecondaryServiceLinesMaster(@PathVariable Long id) {
        log.debug("REST request to get SecondaryServiceLinesMaster : {}", id);
        Optional<SecondaryServiceLinesMasterDTO> secondaryServiceLinesMasterDTO = secondaryServiceLinesMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(secondaryServiceLinesMasterDTO);
    }

    /**
     * {@code DELETE  /secondary-service-lines-masters/:id} : delete the "id" secondaryServiceLinesMaster.
     *
     * @param id the id of the secondaryServiceLinesMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/secondary-service-lines-masters/{id}")
    public ResponseEntity<Void> deleteSecondaryServiceLinesMaster(@PathVariable Long id) {
        log.debug("REST request to delete SecondaryServiceLinesMaster : {}", id);
        secondaryServiceLinesMasterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
