package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.InsuranceDocumentRepository;
import com.sunknowledge.dme.rcm.service.InsuranceDocumentService;
import com.sunknowledge.dme.rcm.service.dto.InsuranceDocumentDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.InsuranceDocument}.
 */
@RestController
@RequestMapping("/api")
public class InsuranceDocumentResource {

    private final Logger log = LoggerFactory.getLogger(InsuranceDocumentResource.class);

    private static final String ENTITY_NAME = "settingsInsuranceDocument";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InsuranceDocumentService insuranceDocumentService;

    private final InsuranceDocumentRepository insuranceDocumentRepository;

    public InsuranceDocumentResource(
        InsuranceDocumentService insuranceDocumentService,
        InsuranceDocumentRepository insuranceDocumentRepository
    ) {
        this.insuranceDocumentService = insuranceDocumentService;
        this.insuranceDocumentRepository = insuranceDocumentRepository;
    }

    /**
     * {@code POST  /insurance-documents} : Create a new insuranceDocument.
     *
     * @param insuranceDocumentDTO the insuranceDocumentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new insuranceDocumentDTO, or with status {@code 400 (Bad Request)} if the insuranceDocument has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/insurance-documents")
    public ResponseEntity<InsuranceDocumentDTO> createInsuranceDocument(@RequestBody InsuranceDocumentDTO insuranceDocumentDTO)
        throws URISyntaxException {
        log.debug("REST request to save InsuranceDocument : {}", insuranceDocumentDTO);
        if (insuranceDocumentDTO.getInsuranceDocumentId() != null) {
            throw new BadRequestAlertException("A new insuranceDocument cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InsuranceDocumentDTO result = insuranceDocumentService.save(insuranceDocumentDTO);
        return ResponseEntity
            .created(new URI("/api/insurance-documents/" + result.getInsuranceDocumentId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getInsuranceDocumentId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /insurance-documents/:insuranceDocumentId} : Updates an existing insuranceDocument.
     *
     * @param insuranceDocumentId the id of the insuranceDocumentDTO to save.
     * @param insuranceDocumentDTO the insuranceDocumentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated insuranceDocumentDTO,
     * or with status {@code 400 (Bad Request)} if the insuranceDocumentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the insuranceDocumentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/insurance-documents/{insuranceDocumentId}")
    public ResponseEntity<InsuranceDocumentDTO> updateInsuranceDocument(
        @PathVariable(value = "insuranceDocumentId", required = false) final Long insuranceDocumentId,
        @RequestBody InsuranceDocumentDTO insuranceDocumentDTO
    ) throws URISyntaxException {
        log.debug("REST request to update InsuranceDocument : {}, {}", insuranceDocumentId, insuranceDocumentDTO);
        if (insuranceDocumentDTO.getInsuranceDocumentId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(insuranceDocumentId, insuranceDocumentDTO.getInsuranceDocumentId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!insuranceDocumentRepository.existsById(insuranceDocumentId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        InsuranceDocumentDTO result = insuranceDocumentService.update(insuranceDocumentDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    insuranceDocumentDTO.getInsuranceDocumentId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /insurance-documents/:insuranceDocumentId} : Partial updates given fields of an existing insuranceDocument, field will ignore if it is null
     *
     * @param insuranceDocumentId the id of the insuranceDocumentDTO to save.
     * @param insuranceDocumentDTO the insuranceDocumentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated insuranceDocumentDTO,
     * or with status {@code 400 (Bad Request)} if the insuranceDocumentDTO is not valid,
     * or with status {@code 404 (Not Found)} if the insuranceDocumentDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the insuranceDocumentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/insurance-documents/{insuranceDocumentId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<InsuranceDocumentDTO> partialUpdateInsuranceDocument(
        @PathVariable(value = "insuranceDocumentId", required = false) final Long insuranceDocumentId,
        @RequestBody InsuranceDocumentDTO insuranceDocumentDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update InsuranceDocument partially : {}, {}", insuranceDocumentId, insuranceDocumentDTO);
        if (insuranceDocumentDTO.getInsuranceDocumentId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(insuranceDocumentId, insuranceDocumentDTO.getInsuranceDocumentId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!insuranceDocumentRepository.existsById(insuranceDocumentId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<InsuranceDocumentDTO> result = insuranceDocumentService.partialUpdate(insuranceDocumentDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                false,
                ENTITY_NAME,
                insuranceDocumentDTO.getInsuranceDocumentId().toString()
            )
        );
    }

    /**
     * {@code GET  /insurance-documents} : get all the insuranceDocuments.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of insuranceDocuments in body.
     */
    @GetMapping("/insurance-documents")
    public ResponseEntity<List<InsuranceDocumentDTO>> getAllInsuranceDocuments(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of InsuranceDocuments");
        Page<InsuranceDocumentDTO> page = insuranceDocumentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /insurance-documents/:id} : get the "id" insuranceDocument.
     *
     * @param id the id of the insuranceDocumentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the insuranceDocumentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/insurance-documents/{id}")
    public ResponseEntity<InsuranceDocumentDTO> getInsuranceDocument(@PathVariable Long id) {
        log.debug("REST request to get InsuranceDocument : {}", id);
        Optional<InsuranceDocumentDTO> insuranceDocumentDTO = insuranceDocumentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(insuranceDocumentDTO);
    }

    /**
     * {@code DELETE  /insurance-documents/:id} : delete the "id" insuranceDocument.
     *
     * @param id the id of the insuranceDocumentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/insurance-documents/{id}")
    public ResponseEntity<Void> deleteInsuranceDocument(@PathVariable Long id) {
        log.debug("REST request to delete InsuranceDocument : {}", id);
        insuranceDocumentService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
