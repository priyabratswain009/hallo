package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.domain.DocumentType;
import com.sunknowledge.dme.rcm.repository.DocumentTypeRepository;
import com.sunknowledge.dme.rcm.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.reactive.ResponseUtil;

/**
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.DocumentType}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class DocumentTypeResource {

    private final Logger log = LoggerFactory.getLogger(DocumentTypeResource.class);

    private static final String ENTITY_NAME = "documentsDocumentType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DocumentTypeRepository documentTypeRepository;

    public DocumentTypeResource(DocumentTypeRepository documentTypeRepository) {
        this.documentTypeRepository = documentTypeRepository;
    }

    /**
     * {@code POST  /document-types} : Create a new documentType.
     *
     * @param documentType the documentType to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new documentType, or with status {@code 400 (Bad Request)} if the documentType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/document-types")
    public Mono<ResponseEntity<DocumentType>> createDocumentType(@RequestBody DocumentType documentType) throws URISyntaxException {
        log.debug("REST request to save DocumentType : {}", documentType);
        if (documentType.getDocumentTypeId() != null) {
            throw new BadRequestAlertException("A new documentType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return documentTypeRepository
            .save(documentType)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/document-types/" + result.getDocumentTypeId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getDocumentTypeId().toString())
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /document-types/:documentTypeId} : Updates an existing documentType.
     *
     * @param documentTypeId the id of the documentType to save.
     * @param documentType the documentType to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated documentType,
     * or with status {@code 400 (Bad Request)} if the documentType is not valid,
     * or with status {@code 500 (Internal Server Error)} if the documentType couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/document-types/{documentTypeId}")
    public Mono<ResponseEntity<DocumentType>> updateDocumentType(
        @PathVariable(value = "documentTypeId", required = false) final Long documentTypeId,
        @RequestBody DocumentType documentType
    ) throws URISyntaxException {
        log.debug("REST request to update DocumentType : {}, {}", documentTypeId, documentType);
        if (documentType.getDocumentTypeId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(documentTypeId, documentType.getDocumentTypeId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return documentTypeRepository
            .existsById(documentTypeId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return documentTypeRepository
                    .save(documentType)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getDocumentTypeId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /document-types/:documentTypeId} : Partial updates given fields of an existing documentType, field will ignore if it is null
     *
     * @param documentTypeId the id of the documentType to save.
     * @param documentType the documentType to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated documentType,
     * or with status {@code 400 (Bad Request)} if the documentType is not valid,
     * or with status {@code 404 (Not Found)} if the documentType is not found,
     * or with status {@code 500 (Internal Server Error)} if the documentType couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/document-types/{documentTypeId}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<DocumentType>> partialUpdateDocumentType(
        @PathVariable(value = "documentTypeId", required = false) final Long documentTypeId,
        @RequestBody DocumentType documentType
    ) throws URISyntaxException {
        log.debug("REST request to partial update DocumentType partially : {}, {}", documentTypeId, documentType);
        if (documentType.getDocumentTypeId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(documentTypeId, documentType.getDocumentTypeId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return documentTypeRepository
            .existsById(documentTypeId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<DocumentType> result = documentTypeRepository
                    .findById(documentType.getDocumentTypeId())
                    .map(existingDocumentType -> {
                        if (documentType.getDocumentType() != null) {
                            existingDocumentType.setDocumentType(documentType.getDocumentType());
                        }
                        if (documentType.getDescription() != null) {
                            existingDocumentType.setDescription(documentType.getDescription());
                        }

                        return existingDocumentType;
                    })
                    .flatMap(documentTypeRepository::save);

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(res ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, res.getDocumentTypeId().toString())
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /document-types} : get all the documentTypes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of documentTypes in body.
     */
    @GetMapping("/document-types")
    public Mono<List<DocumentType>> getAllDocumentTypes() {
        log.debug("REST request to get all DocumentTypes");
        return documentTypeRepository.findAll().collectList();
    }

    /**
     * {@code GET  /document-types} : get all the documentTypes as a stream.
     * @return the {@link Flux} of documentTypes.
     */
    @GetMapping(value = "/document-types", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<DocumentType> getAllDocumentTypesAsStream() {
        log.debug("REST request to get all DocumentTypes as a stream");
        return documentTypeRepository.findAll();
    }

    /**
     * {@code GET  /document-types/:id} : get the "id" documentType.
     *
     * @param id the id of the documentType to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the documentType, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/document-types/{id}")
    public Mono<ResponseEntity<DocumentType>> getDocumentType(@PathVariable Long id) {
        log.debug("REST request to get DocumentType : {}", id);
        Mono<DocumentType> documentType = documentTypeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(documentType);
    }

    /**
     * {@code DELETE  /document-types/:id} : delete the "id" documentType.
     *
     * @param id the id of the documentType to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/document-types/{id}")
    public Mono<ResponseEntity<Void>> deleteDocumentType(@PathVariable Long id) {
        log.debug("REST request to delete DocumentType : {}", id);
        return documentTypeRepository
            .deleteById(id)
            .then(
                Mono.just(
                    ResponseEntity
                        .noContent()
                        .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                        .build()
                )
            );
    }
}
