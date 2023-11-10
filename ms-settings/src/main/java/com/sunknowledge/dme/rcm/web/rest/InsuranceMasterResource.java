package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.InsuranceMasterRepository;
import com.sunknowledge.dme.rcm.service.InsuranceMasterService;
import com.sunknowledge.dme.rcm.service.dto.InsuranceMasterDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.InsuranceMaster}.
 */
@RestController
@RequestMapping("/api")
public class InsuranceMasterResource {

    private final Logger log = LoggerFactory.getLogger(InsuranceMasterResource.class);

    private static final String ENTITY_NAME = "settingsInsuranceMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InsuranceMasterService insuranceMasterService;

    private final InsuranceMasterRepository insuranceMasterRepository;

    public InsuranceMasterResource(InsuranceMasterService insuranceMasterService, InsuranceMasterRepository insuranceMasterRepository) {
        this.insuranceMasterService = insuranceMasterService;
        this.insuranceMasterRepository = insuranceMasterRepository;
    }

    /**
     * {@code POST  /insurance-masters} : Create a new insuranceMaster.
     *
     * @param insuranceMasterDTO the insuranceMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new insuranceMasterDTO, or with status {@code 400 (Bad Request)} if the insuranceMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/insurance-masters")
    public ResponseEntity<InsuranceMasterDTO> createInsuranceMaster(@RequestBody InsuranceMasterDTO insuranceMasterDTO)
        throws URISyntaxException {
        log.debug("REST request to save InsuranceMaster : {}", insuranceMasterDTO);
        if (insuranceMasterDTO.getInsuranceId() != null) {
            throw new BadRequestAlertException("A new insuranceMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InsuranceMasterDTO result = insuranceMasterService.save(insuranceMasterDTO);
        return ResponseEntity
            .created(new URI("/api/insurance-masters/" + result.getInsuranceId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getInsuranceId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /insurance-masters/:insuranceId} : Updates an existing insuranceMaster.
     *
     * @param insuranceId the id of the insuranceMasterDTO to save.
     * @param insuranceMasterDTO the insuranceMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated insuranceMasterDTO,
     * or with status {@code 400 (Bad Request)} if the insuranceMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the insuranceMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/insurance-masters/{insuranceId}")
    public ResponseEntity<InsuranceMasterDTO> updateInsuranceMaster(
        @PathVariable(value = "insuranceId", required = false) final Long insuranceId,
        @RequestBody InsuranceMasterDTO insuranceMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update InsuranceMaster : {}, {}", insuranceId, insuranceMasterDTO);
        if (insuranceMasterDTO.getInsuranceId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(insuranceId, insuranceMasterDTO.getInsuranceId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!insuranceMasterRepository.existsById(insuranceId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        InsuranceMasterDTO result = insuranceMasterService.update(insuranceMasterDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, insuranceMasterDTO.getInsuranceId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /insurance-masters/:insuranceId} : Partial updates given fields of an existing insuranceMaster, field will ignore if it is null
     *
     * @param insuranceId the id of the insuranceMasterDTO to save.
     * @param insuranceMasterDTO the insuranceMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated insuranceMasterDTO,
     * or with status {@code 400 (Bad Request)} if the insuranceMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the insuranceMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the insuranceMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/insurance-masters/{insuranceId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<InsuranceMasterDTO> partialUpdateInsuranceMaster(
        @PathVariable(value = "insuranceId", required = false) final Long insuranceId,
        @RequestBody InsuranceMasterDTO insuranceMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update InsuranceMaster partially : {}, {}", insuranceId, insuranceMasterDTO);
        if (insuranceMasterDTO.getInsuranceId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(insuranceId, insuranceMasterDTO.getInsuranceId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!insuranceMasterRepository.existsById(insuranceId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<InsuranceMasterDTO> result = insuranceMasterService.partialUpdate(insuranceMasterDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, insuranceMasterDTO.getInsuranceId().toString())
        );
    }

    /**
     * {@code GET  /insurance-masters} : get all the insuranceMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of insuranceMasters in body.
     */
    @GetMapping("/insurance-masters")
    public ResponseEntity<List<InsuranceMasterDTO>> getAllInsuranceMasters(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of InsuranceMasters");
        Page<InsuranceMasterDTO> page = insuranceMasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /insurance-masters/:id} : get the "id" insuranceMaster.
     *
     * @param id the id of the insuranceMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the insuranceMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/insurance-masters/{id}")
    public ResponseEntity<InsuranceMasterDTO> getInsuranceMaster(@PathVariable Long id) {
        log.debug("REST request to get InsuranceMaster : {}", id);
        Optional<InsuranceMasterDTO> insuranceMasterDTO = insuranceMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(insuranceMasterDTO);
    }

    /**
     * {@code DELETE  /insurance-masters/:id} : delete the "id" insuranceMaster.
     *
     * @param id the id of the insuranceMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/insurance-masters/{id}")
    public ResponseEntity<Void> deleteInsuranceMaster(@PathVariable Long id) {
        log.debug("REST request to delete InsuranceMaster : {}", id);
        insuranceMasterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
