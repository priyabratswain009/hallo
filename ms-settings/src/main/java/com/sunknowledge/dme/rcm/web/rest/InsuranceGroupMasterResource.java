package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.InsuranceGroupMasterRepository;
import com.sunknowledge.dme.rcm.service.InsuranceGroupMasterService;
import com.sunknowledge.dme.rcm.service.dto.InsuranceGroupMasterDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.InsuranceGroupMaster}.
 */
@RestController
@RequestMapping("/api")
public class InsuranceGroupMasterResource {

    private final Logger log = LoggerFactory.getLogger(InsuranceGroupMasterResource.class);

    private static final String ENTITY_NAME = "settingsInsuranceGroupMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InsuranceGroupMasterService insuranceGroupMasterService;

    private final InsuranceGroupMasterRepository insuranceGroupMasterRepository;

    public InsuranceGroupMasterResource(
        InsuranceGroupMasterService insuranceGroupMasterService,
        InsuranceGroupMasterRepository insuranceGroupMasterRepository
    ) {
        this.insuranceGroupMasterService = insuranceGroupMasterService;
        this.insuranceGroupMasterRepository = insuranceGroupMasterRepository;
    }

    /**
     * {@code POST  /insurance-group-masters} : Create a new insuranceGroupMaster.
     *
     * @param insuranceGroupMasterDTO the insuranceGroupMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new insuranceGroupMasterDTO, or with status {@code 400 (Bad Request)} if the insuranceGroupMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/insurance-group-masters")
    public ResponseEntity<InsuranceGroupMasterDTO> createInsuranceGroupMaster(@RequestBody InsuranceGroupMasterDTO insuranceGroupMasterDTO)
        throws URISyntaxException {
        log.debug("REST request to save InsuranceGroupMaster : {}", insuranceGroupMasterDTO);
        if (insuranceGroupMasterDTO.getInsuranceGroupMasterId() != null) {
            throw new BadRequestAlertException("A new insuranceGroupMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InsuranceGroupMasterDTO result = insuranceGroupMasterService.save(insuranceGroupMasterDTO);
        return ResponseEntity
            .created(new URI("/api/insurance-group-masters/" + result.getInsuranceGroupMasterId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getInsuranceGroupMasterId().toString())
            )
            .body(result);
    }

    /**
     * {@code PUT  /insurance-group-masters/:insuranceGroupMasterId} : Updates an existing insuranceGroupMaster.
     *
     * @param insuranceGroupMasterId the id of the insuranceGroupMasterDTO to save.
     * @param insuranceGroupMasterDTO the insuranceGroupMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated insuranceGroupMasterDTO,
     * or with status {@code 400 (Bad Request)} if the insuranceGroupMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the insuranceGroupMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/insurance-group-masters/{insuranceGroupMasterId}")
    public ResponseEntity<InsuranceGroupMasterDTO> updateInsuranceGroupMaster(
        @PathVariable(value = "insuranceGroupMasterId", required = false) final Long insuranceGroupMasterId,
        @RequestBody InsuranceGroupMasterDTO insuranceGroupMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update InsuranceGroupMaster : {}, {}", insuranceGroupMasterId, insuranceGroupMasterDTO);
        if (insuranceGroupMasterDTO.getInsuranceGroupMasterId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(insuranceGroupMasterId, insuranceGroupMasterDTO.getInsuranceGroupMasterId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!insuranceGroupMasterRepository.existsById(insuranceGroupMasterId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        InsuranceGroupMasterDTO result = insuranceGroupMasterService.update(insuranceGroupMasterDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    insuranceGroupMasterDTO.getInsuranceGroupMasterId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /insurance-group-masters/:insuranceGroupMasterId} : Partial updates given fields of an existing insuranceGroupMaster, field will ignore if it is null
     *
     * @param insuranceGroupMasterId the id of the insuranceGroupMasterDTO to save.
     * @param insuranceGroupMasterDTO the insuranceGroupMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated insuranceGroupMasterDTO,
     * or with status {@code 400 (Bad Request)} if the insuranceGroupMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the insuranceGroupMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the insuranceGroupMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/insurance-group-masters/{insuranceGroupMasterId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public ResponseEntity<InsuranceGroupMasterDTO> partialUpdateInsuranceGroupMaster(
        @PathVariable(value = "insuranceGroupMasterId", required = false) final Long insuranceGroupMasterId,
        @RequestBody InsuranceGroupMasterDTO insuranceGroupMasterDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update InsuranceGroupMaster partially : {}, {}",
            insuranceGroupMasterId,
            insuranceGroupMasterDTO
        );
        if (insuranceGroupMasterDTO.getInsuranceGroupMasterId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(insuranceGroupMasterId, insuranceGroupMasterDTO.getInsuranceGroupMasterId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!insuranceGroupMasterRepository.existsById(insuranceGroupMasterId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<InsuranceGroupMasterDTO> result = insuranceGroupMasterService.partialUpdate(insuranceGroupMasterDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                false,
                ENTITY_NAME,
                insuranceGroupMasterDTO.getInsuranceGroupMasterId().toString()
            )
        );
    }

    /**
     * {@code GET  /insurance-group-masters} : get all the insuranceGroupMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of insuranceGroupMasters in body.
     */
    @GetMapping("/insurance-group-masters")
    public ResponseEntity<List<InsuranceGroupMasterDTO>> getAllInsuranceGroupMasters(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of InsuranceGroupMasters");
        Page<InsuranceGroupMasterDTO> page = insuranceGroupMasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /insurance-group-masters/:id} : get the "id" insuranceGroupMaster.
     *
     * @param id the id of the insuranceGroupMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the insuranceGroupMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/insurance-group-masters/{id}")
    public ResponseEntity<InsuranceGroupMasterDTO> getInsuranceGroupMaster(@PathVariable Long id) {
        log.debug("REST request to get InsuranceGroupMaster : {}", id);
        Optional<InsuranceGroupMasterDTO> insuranceGroupMasterDTO = insuranceGroupMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(insuranceGroupMasterDTO);
    }

    /**
     * {@code DELETE  /insurance-group-masters/:id} : delete the "id" insuranceGroupMaster.
     *
     * @param id the id of the insuranceGroupMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/insurance-group-masters/{id}")
    public ResponseEntity<Void> deleteInsuranceGroupMaster(@PathVariable Long id) {
        log.debug("REST request to delete InsuranceGroupMaster : {}", id);
        insuranceGroupMasterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
