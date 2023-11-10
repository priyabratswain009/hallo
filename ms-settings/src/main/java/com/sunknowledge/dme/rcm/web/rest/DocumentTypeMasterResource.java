package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.DocumentTypeMasterRepository;
import com.sunknowledge.dme.rcm.service.DocumentTypeMasterService;
import com.sunknowledge.dme.rcm.service.dto.DocumentTypeMasterDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.DocumentTypeMaster}.
 */
@RestController
@RequestMapping("/api")
public class DocumentTypeMasterResource {

    private final Logger log = LoggerFactory.getLogger(DocumentTypeMasterResource.class);

    private static final String ENTITY_NAME = "settingsDocumentTypeMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DocumentTypeMasterService documentTypeMasterService;

    private final DocumentTypeMasterRepository documentTypeMasterRepository;

    public DocumentTypeMasterResource(
        DocumentTypeMasterService documentTypeMasterService,
        DocumentTypeMasterRepository documentTypeMasterRepository
    ) {
        this.documentTypeMasterService = documentTypeMasterService;
        this.documentTypeMasterRepository = documentTypeMasterRepository;
    }

    /**
     * {@code POST  /document-type-masters} : Create a new documentTypeMaster.
     *
     * @param documentTypeMasterDTO the documentTypeMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new documentTypeMasterDTO, or with status {@code 400 (Bad Request)} if the documentTypeMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/document-type-masters")
    public ResponseEntity<DocumentTypeMasterDTO> createDocumentTypeMaster(@RequestBody DocumentTypeMasterDTO documentTypeMasterDTO)
        throws URISyntaxException {
        log.debug("REST request to save DocumentTypeMaster : {}", documentTypeMasterDTO);
        if (documentTypeMasterDTO.getDocumentTypeId() != null) {
            throw new BadRequestAlertException("A new documentTypeMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DocumentTypeMasterDTO result = documentTypeMasterService.save(documentTypeMasterDTO);
        return ResponseEntity
            .created(new URI("/api/document-type-masters/" + result.getDocumentTypeId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getDocumentTypeId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /document-type-masters/:documentTypeId} : Updates an existing documentTypeMaster.
     *
     * @param documentTypeId the id of the documentTypeMasterDTO to save.
     * @param documentTypeMasterDTO the documentTypeMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated documentTypeMasterDTO,
     * or with status {@code 400 (Bad Request)} if the documentTypeMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the documentTypeMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/document-type-masters/{documentTypeId}")
    public ResponseEntity<DocumentTypeMasterDTO> updateDocumentTypeMaster(
        @PathVariable(value = "documentTypeId", required = false) final Long documentTypeId,
        @RequestBody DocumentTypeMasterDTO documentTypeMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DocumentTypeMaster : {}, {}", documentTypeId, documentTypeMasterDTO);
        if (documentTypeMasterDTO.getDocumentTypeId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(documentTypeId, documentTypeMasterDTO.getDocumentTypeId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!documentTypeMasterRepository.existsById(documentTypeId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DocumentTypeMasterDTO result = documentTypeMasterService.update(documentTypeMasterDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    documentTypeMasterDTO.getDocumentTypeId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /document-type-masters/:documentTypeId} : Partial updates given fields of an existing documentTypeMaster, field will ignore if it is null
     *
     * @param documentTypeId the id of the documentTypeMasterDTO to save.
     * @param documentTypeMasterDTO the documentTypeMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated documentTypeMasterDTO,
     * or with status {@code 400 (Bad Request)} if the documentTypeMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the documentTypeMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the documentTypeMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/document-type-masters/{documentTypeId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DocumentTypeMasterDTO> partialUpdateDocumentTypeMaster(
        @PathVariable(value = "documentTypeId", required = false) final Long documentTypeId,
        @RequestBody DocumentTypeMasterDTO documentTypeMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DocumentTypeMaster partially : {}, {}", documentTypeId, documentTypeMasterDTO);
        if (documentTypeMasterDTO.getDocumentTypeId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(documentTypeId, documentTypeMasterDTO.getDocumentTypeId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!documentTypeMasterRepository.existsById(documentTypeId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DocumentTypeMasterDTO> result = documentTypeMasterService.partialUpdate(documentTypeMasterDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, documentTypeMasterDTO.getDocumentTypeId().toString())
        );
    }

    /**
     * {@code GET  /document-type-masters} : get all the documentTypeMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of documentTypeMasters in body.
     */
    @GetMapping("/document-type-masters")
    public ResponseEntity<List<DocumentTypeMasterDTO>> getAllDocumentTypeMasters(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of DocumentTypeMasters");
        Page<DocumentTypeMasterDTO> page = documentTypeMasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /document-type-masters/:id} : get the "id" documentTypeMaster.
     *
     * @param id the id of the documentTypeMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the documentTypeMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/document-type-masters/{id}")
    public ResponseEntity<DocumentTypeMasterDTO> getDocumentTypeMaster(@PathVariable Long id) {
        log.debug("REST request to get DocumentTypeMaster : {}", id);
        Optional<DocumentTypeMasterDTO> documentTypeMasterDTO = documentTypeMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(documentTypeMasterDTO);
    }

    /**
     * {@code DELETE  /document-type-masters/:id} : delete the "id" documentTypeMaster.
     *
     * @param id the id of the documentTypeMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/document-type-masters/{id}")
    public ResponseEntity<Void> deleteDocumentTypeMaster(@PathVariable Long id) {
        log.debug("REST request to delete DocumentTypeMaster : {}", id);
        documentTypeMasterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
