package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.DocumentTypeMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.DocumentTypeMasterAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.DocumentTypeMasterAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.DocumentTypeMasterAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class DocumentTypeMasterAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(DocumentTypeMasterAuditLogResource.class);

    private static final String ENTITY_NAME = "settingsDocumentTypeMasterAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DocumentTypeMasterAuditLogService documentTypeMasterAuditLogService;

    private final DocumentTypeMasterAuditLogRepository documentTypeMasterAuditLogRepository;

    public DocumentTypeMasterAuditLogResource(
        DocumentTypeMasterAuditLogService documentTypeMasterAuditLogService,
        DocumentTypeMasterAuditLogRepository documentTypeMasterAuditLogRepository
    ) {
        this.documentTypeMasterAuditLogService = documentTypeMasterAuditLogService;
        this.documentTypeMasterAuditLogRepository = documentTypeMasterAuditLogRepository;
    }

    /**
     * {@code POST  /document-type-master-audit-logs} : Create a new documentTypeMasterAuditLog.
     *
     * @param documentTypeMasterAuditLogDTO the documentTypeMasterAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new documentTypeMasterAuditLogDTO, or with status {@code 400 (Bad Request)} if the documentTypeMasterAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/document-type-master-audit-logs")
    public ResponseEntity<DocumentTypeMasterAuditLogDTO> createDocumentTypeMasterAuditLog(
        @RequestBody DocumentTypeMasterAuditLogDTO documentTypeMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to save DocumentTypeMasterAuditLog : {}", documentTypeMasterAuditLogDTO);
        if (documentTypeMasterAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new documentTypeMasterAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DocumentTypeMasterAuditLogDTO result = documentTypeMasterAuditLogService.save(documentTypeMasterAuditLogDTO);
        return ResponseEntity
            .created(new URI("/api/document-type-master-audit-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /document-type-master-audit-logs/:id} : Updates an existing documentTypeMasterAuditLog.
     *
     * @param id the id of the documentTypeMasterAuditLogDTO to save.
     * @param documentTypeMasterAuditLogDTO the documentTypeMasterAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated documentTypeMasterAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the documentTypeMasterAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the documentTypeMasterAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/document-type-master-audit-logs/{id}")
    public ResponseEntity<DocumentTypeMasterAuditLogDTO> updateDocumentTypeMasterAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DocumentTypeMasterAuditLogDTO documentTypeMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DocumentTypeMasterAuditLog : {}, {}", id, documentTypeMasterAuditLogDTO);
        if (documentTypeMasterAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, documentTypeMasterAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!documentTypeMasterAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DocumentTypeMasterAuditLogDTO result = documentTypeMasterAuditLogService.update(documentTypeMasterAuditLogDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, documentTypeMasterAuditLogDTO.getId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /document-type-master-audit-logs/:id} : Partial updates given fields of an existing documentTypeMasterAuditLog, field will ignore if it is null
     *
     * @param id the id of the documentTypeMasterAuditLogDTO to save.
     * @param documentTypeMasterAuditLogDTO the documentTypeMasterAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated documentTypeMasterAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the documentTypeMasterAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the documentTypeMasterAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the documentTypeMasterAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/document-type-master-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DocumentTypeMasterAuditLogDTO> partialUpdateDocumentTypeMasterAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DocumentTypeMasterAuditLogDTO documentTypeMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DocumentTypeMasterAuditLog partially : {}, {}", id, documentTypeMasterAuditLogDTO);
        if (documentTypeMasterAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, documentTypeMasterAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!documentTypeMasterAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DocumentTypeMasterAuditLogDTO> result = documentTypeMasterAuditLogService.partialUpdate(documentTypeMasterAuditLogDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, documentTypeMasterAuditLogDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /document-type-master-audit-logs} : get all the documentTypeMasterAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of documentTypeMasterAuditLogs in body.
     */
    @GetMapping("/document-type-master-audit-logs")
    public ResponseEntity<List<DocumentTypeMasterAuditLogDTO>> getAllDocumentTypeMasterAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of DocumentTypeMasterAuditLogs");
        Page<DocumentTypeMasterAuditLogDTO> page = documentTypeMasterAuditLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /document-type-master-audit-logs/:id} : get the "id" documentTypeMasterAuditLog.
     *
     * @param id the id of the documentTypeMasterAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the documentTypeMasterAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/document-type-master-audit-logs/{id}")
    public ResponseEntity<DocumentTypeMasterAuditLogDTO> getDocumentTypeMasterAuditLog(@PathVariable Long id) {
        log.debug("REST request to get DocumentTypeMasterAuditLog : {}", id);
        Optional<DocumentTypeMasterAuditLogDTO> documentTypeMasterAuditLogDTO = documentTypeMasterAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(documentTypeMasterAuditLogDTO);
    }

    /**
     * {@code DELETE  /document-type-master-audit-logs/:id} : delete the "id" documentTypeMasterAuditLog.
     *
     * @param id the id of the documentTypeMasterAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/document-type-master-audit-logs/{id}")
    public ResponseEntity<Void> deleteDocumentTypeMasterAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete DocumentTypeMasterAuditLog : {}", id);
        documentTypeMasterAuditLogService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
