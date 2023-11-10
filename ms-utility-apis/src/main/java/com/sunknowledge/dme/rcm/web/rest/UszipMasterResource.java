package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.UszipMasterRepository;
import com.sunknowledge.dme.rcm.service.UszipMasterService;
import com.sunknowledge.dme.rcm.service.dto.UszipMasterDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.UszipMaster}.
 */
@RestController
@RequestMapping("/api")
public class UszipMasterResource {

    private final Logger log = LoggerFactory.getLogger(UszipMasterResource.class);

    private static final String ENTITY_NAME = "utilityapisUszipMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UszipMasterService uszipMasterService;

    private final UszipMasterRepository uszipMasterRepository;

    public UszipMasterResource(UszipMasterService uszipMasterService, UszipMasterRepository uszipMasterRepository) {
        this.uszipMasterService = uszipMasterService;
        this.uszipMasterRepository = uszipMasterRepository;
    }

    /**
     * {@code POST  /uszip-masters} : Create a new uszipMaster.
     *
     * @param uszipMasterDTO the uszipMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new uszipMasterDTO, or with status {@code 400 (Bad Request)} if the uszipMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/uszip-masters")
    public ResponseEntity<UszipMasterDTO> createUszipMaster(@RequestBody UszipMasterDTO uszipMasterDTO) throws URISyntaxException {
        log.debug("REST request to save UszipMaster : {}", uszipMasterDTO);
        if (uszipMasterDTO.getUszipMasterId() != null) {
            throw new BadRequestAlertException("A new uszipMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UszipMasterDTO result = uszipMasterService.save(uszipMasterDTO);
        return ResponseEntity
            .created(new URI("/api/uszip-masters/" + result.getUszipMasterId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getUszipMasterId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /uszip-masters/:uszipMasterId} : Updates an existing uszipMaster.
     *
     * @param uszipMasterId the id of the uszipMasterDTO to save.
     * @param uszipMasterDTO the uszipMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated uszipMasterDTO,
     * or with status {@code 400 (Bad Request)} if the uszipMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the uszipMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/uszip-masters/{uszipMasterId}")
    public ResponseEntity<UszipMasterDTO> updateUszipMaster(
        @PathVariable(value = "uszipMasterId", required = false) final Long uszipMasterId,
        @RequestBody UszipMasterDTO uszipMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update UszipMaster : {}, {}", uszipMasterId, uszipMasterDTO);
        if (uszipMasterDTO.getUszipMasterId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(uszipMasterId, uszipMasterDTO.getUszipMasterId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!uszipMasterRepository.existsById(uszipMasterId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        UszipMasterDTO result = uszipMasterService.update(uszipMasterDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, uszipMasterDTO.getUszipMasterId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /uszip-masters/:uszipMasterId} : Partial updates given fields of an existing uszipMaster, field will ignore if it is null
     *
     * @param uszipMasterId the id of the uszipMasterDTO to save.
     * @param uszipMasterDTO the uszipMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated uszipMasterDTO,
     * or with status {@code 400 (Bad Request)} if the uszipMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the uszipMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the uszipMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/uszip-masters/{uszipMasterId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<UszipMasterDTO> partialUpdateUszipMaster(
        @PathVariable(value = "uszipMasterId", required = false) final Long uszipMasterId,
        @RequestBody UszipMasterDTO uszipMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update UszipMaster partially : {}, {}", uszipMasterId, uszipMasterDTO);
        if (uszipMasterDTO.getUszipMasterId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(uszipMasterId, uszipMasterDTO.getUszipMasterId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!uszipMasterRepository.existsById(uszipMasterId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<UszipMasterDTO> result = uszipMasterService.partialUpdate(uszipMasterDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, uszipMasterDTO.getUszipMasterId().toString())
        );
    }

    /**
     * {@code GET  /uszip-masters} : get all the uszipMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of uszipMasters in body.
     */
    @GetMapping("/uszip-masters")
    public ResponseEntity<List<UszipMasterDTO>> getAllUszipMasters(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of UszipMasters");
        Page<UszipMasterDTO> page = uszipMasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /uszip-masters/:id} : get the "id" uszipMaster.
     *
     * @param id the id of the uszipMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the uszipMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/uszip-masters/{id}")
    public ResponseEntity<UszipMasterDTO> getUszipMaster(@PathVariable Long id) {
        log.debug("REST request to get UszipMaster : {}", id);
        Optional<UszipMasterDTO> uszipMasterDTO = uszipMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(uszipMasterDTO);
    }

    /**
     * {@code DELETE  /uszip-masters/:id} : delete the "id" uszipMaster.
     *
     * @param id the id of the uszipMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/uszip-masters/{id}")
    public ResponseEntity<Void> deleteUszipMaster(@PathVariable Long id) {
        log.debug("REST request to delete UszipMaster : {}", id);
        uszipMasterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
