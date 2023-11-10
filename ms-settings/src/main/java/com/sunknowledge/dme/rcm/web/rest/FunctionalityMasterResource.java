package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.FunctionalityMasterRepository;
import com.sunknowledge.dme.rcm.service.FunctionalityMasterService;
import com.sunknowledge.dme.rcm.service.dto.FunctionalityMasterDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.FunctionalityMaster}.
 */
@RestController
@RequestMapping("/api")
public class FunctionalityMasterResource {

    private final Logger log = LoggerFactory.getLogger(FunctionalityMasterResource.class);

    private static final String ENTITY_NAME = "settingsFunctionalityMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FunctionalityMasterService functionalityMasterService;

    private final FunctionalityMasterRepository functionalityMasterRepository;

    public FunctionalityMasterResource(
        FunctionalityMasterService functionalityMasterService,
        FunctionalityMasterRepository functionalityMasterRepository
    ) {
        this.functionalityMasterService = functionalityMasterService;
        this.functionalityMasterRepository = functionalityMasterRepository;
    }

    /**
     * {@code POST  /functionality-masters} : Create a new functionalityMaster.
     *
     * @param functionalityMasterDTO the functionalityMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new functionalityMasterDTO, or with status {@code 400 (Bad Request)} if the functionalityMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/functionality-masters")
    public ResponseEntity<FunctionalityMasterDTO> createFunctionalityMaster(@RequestBody FunctionalityMasterDTO functionalityMasterDTO)
        throws URISyntaxException {
        log.debug("REST request to save FunctionalityMaster : {}", functionalityMasterDTO);
        if (functionalityMasterDTO.getFunctionalityId() != null) {
            throw new BadRequestAlertException("A new functionalityMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FunctionalityMasterDTO result = functionalityMasterService.save(functionalityMasterDTO);
        return ResponseEntity
            .created(new URI("/api/functionality-masters/" + result.getFunctionalityId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getFunctionalityId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /functionality-masters/:functionalityId} : Updates an existing functionalityMaster.
     *
     * @param functionalityId the id of the functionalityMasterDTO to save.
     * @param functionalityMasterDTO the functionalityMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated functionalityMasterDTO,
     * or with status {@code 400 (Bad Request)} if the functionalityMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the functionalityMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/functionality-masters/{functionalityId}")
    public ResponseEntity<FunctionalityMasterDTO> updateFunctionalityMaster(
        @PathVariable(value = "functionalityId", required = false) final Long functionalityId,
        @RequestBody FunctionalityMasterDTO functionalityMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update FunctionalityMaster : {}, {}", functionalityId, functionalityMasterDTO);
        if (functionalityMasterDTO.getFunctionalityId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(functionalityId, functionalityMasterDTO.getFunctionalityId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!functionalityMasterRepository.existsById(functionalityId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        FunctionalityMasterDTO result = functionalityMasterService.update(functionalityMasterDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    functionalityMasterDTO.getFunctionalityId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /functionality-masters/:functionalityId} : Partial updates given fields of an existing functionalityMaster, field will ignore if it is null
     *
     * @param functionalityId the id of the functionalityMasterDTO to save.
     * @param functionalityMasterDTO the functionalityMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated functionalityMasterDTO,
     * or with status {@code 400 (Bad Request)} if the functionalityMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the functionalityMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the functionalityMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/functionality-masters/{functionalityId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<FunctionalityMasterDTO> partialUpdateFunctionalityMaster(
        @PathVariable(value = "functionalityId", required = false) final Long functionalityId,
        @RequestBody FunctionalityMasterDTO functionalityMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update FunctionalityMaster partially : {}, {}", functionalityId, functionalityMasterDTO);
        if (functionalityMasterDTO.getFunctionalityId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(functionalityId, functionalityMasterDTO.getFunctionalityId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!functionalityMasterRepository.existsById(functionalityId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FunctionalityMasterDTO> result = functionalityMasterService.partialUpdate(functionalityMasterDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, functionalityMasterDTO.getFunctionalityId().toString())
        );
    }

    /**
     * {@code GET  /functionality-masters} : get all the functionalityMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of functionalityMasters in body.
     */
    @GetMapping("/functionality-masters")
    public ResponseEntity<List<FunctionalityMasterDTO>> getAllFunctionalityMasters(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of FunctionalityMasters");
        Page<FunctionalityMasterDTO> page = functionalityMasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /functionality-masters/:id} : get the "id" functionalityMaster.
     *
     * @param id the id of the functionalityMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the functionalityMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/functionality-masters/{id}")
    public ResponseEntity<FunctionalityMasterDTO> getFunctionalityMaster(@PathVariable Long id) {
        log.debug("REST request to get FunctionalityMaster : {}", id);
        Optional<FunctionalityMasterDTO> functionalityMasterDTO = functionalityMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(functionalityMasterDTO);
    }

    /**
     * {@code DELETE  /functionality-masters/:id} : delete the "id" functionalityMaster.
     *
     * @param id the id of the functionalityMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/functionality-masters/{id}")
    public ResponseEntity<Void> deleteFunctionalityMaster(@PathVariable Long id) {
        log.debug("REST request to delete FunctionalityMaster : {}", id);
        functionalityMasterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
