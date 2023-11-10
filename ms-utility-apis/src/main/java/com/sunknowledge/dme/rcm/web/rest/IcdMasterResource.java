package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.IcdMasterRepository;
import com.sunknowledge.dme.rcm.service.IcdMasterService;
import com.sunknowledge.dme.rcm.service.dto.IcdMasterDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.IcdMaster}.
 */
@RestController
@RequestMapping("/api")
public class IcdMasterResource {

    private final Logger log = LoggerFactory.getLogger(IcdMasterResource.class);

    private static final String ENTITY_NAME = "utilityapisIcdMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final IcdMasterService icdMasterService;

    private final IcdMasterRepository icdMasterRepository;

    public IcdMasterResource(IcdMasterService icdMasterService, IcdMasterRepository icdMasterRepository) {
        this.icdMasterService = icdMasterService;
        this.icdMasterRepository = icdMasterRepository;
    }

    /**
     * {@code POST  /icd-masters} : Create a new icdMaster.
     *
     * @param icdMasterDTO the icdMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new icdMasterDTO, or with status {@code 400 (Bad Request)} if the icdMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/icd-masters")
    public ResponseEntity<IcdMasterDTO> createIcdMaster(@Valid @RequestBody IcdMasterDTO icdMasterDTO) throws URISyntaxException {
        log.debug("REST request to save IcdMaster : {}", icdMasterDTO);
        if (icdMasterDTO.getIcdMasterId() != null) {
            throw new BadRequestAlertException("A new icdMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        IcdMasterDTO result = icdMasterService.save(icdMasterDTO);
        return ResponseEntity
            .created(new URI("/api/icd-masters/" + result.getIcdMasterId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getIcdMasterId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /icd-masters/:icdMasterId} : Updates an existing icdMaster.
     *
     * @param icdMasterId the id of the icdMasterDTO to save.
     * @param icdMasterDTO the icdMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated icdMasterDTO,
     * or with status {@code 400 (Bad Request)} if the icdMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the icdMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/icd-masters/{icdMasterId}")
    public ResponseEntity<IcdMasterDTO> updateIcdMaster(
        @PathVariable(value = "icdMasterId", required = false) final Long icdMasterId,
        @Valid @RequestBody IcdMasterDTO icdMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update IcdMaster : {}, {}", icdMasterId, icdMasterDTO);
        if (icdMasterDTO.getIcdMasterId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(icdMasterId, icdMasterDTO.getIcdMasterId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!icdMasterRepository.existsById(icdMasterId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        IcdMasterDTO result = icdMasterService.update(icdMasterDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, icdMasterDTO.getIcdMasterId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /icd-masters/:icdMasterId} : Partial updates given fields of an existing icdMaster, field will ignore if it is null
     *
     * @param icdMasterId the id of the icdMasterDTO to save.
     * @param icdMasterDTO the icdMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated icdMasterDTO,
     * or with status {@code 400 (Bad Request)} if the icdMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the icdMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the icdMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/icd-masters/{icdMasterId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<IcdMasterDTO> partialUpdateIcdMaster(
        @PathVariable(value = "icdMasterId", required = false) final Long icdMasterId,
        @NotNull @RequestBody IcdMasterDTO icdMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update IcdMaster partially : {}, {}", icdMasterId, icdMasterDTO);
        if (icdMasterDTO.getIcdMasterId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(icdMasterId, icdMasterDTO.getIcdMasterId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!icdMasterRepository.existsById(icdMasterId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<IcdMasterDTO> result = icdMasterService.partialUpdate(icdMasterDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, icdMasterDTO.getIcdMasterId().toString())
        );
    }

    /**
     * {@code GET  /icd-masters} : get all the icdMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of icdMasters in body.
     */
    @GetMapping("/icd-masters")
    public ResponseEntity<List<IcdMasterDTO>> getAllIcdMasters(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of IcdMasters");
        Page<IcdMasterDTO> page = icdMasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /icd-masters/:id} : get the "id" icdMaster.
     *
     * @param id the id of the icdMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the icdMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/icd-masters/{id}")
    public ResponseEntity<IcdMasterDTO> getIcdMaster(@PathVariable Long id) {
        log.debug("REST request to get IcdMaster : {}", id);
        Optional<IcdMasterDTO> icdMasterDTO = icdMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(icdMasterDTO);
    }

    /**
     * {@code DELETE  /icd-masters/:id} : delete the "id" icdMaster.
     *
     * @param id the id of the icdMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/icd-masters/{id}")
    public ResponseEntity<Void> deleteIcdMaster(@PathVariable Long id) {
        log.debug("REST request to delete IcdMaster : {}", id);
        icdMasterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
