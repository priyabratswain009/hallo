package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.DocumentReferenceFileMapRepository;
import com.sunknowledge.dme.rcm.service.DocumentReferenceFileMapService;
import com.sunknowledge.dme.rcm.service.dto.DocumentReferenceFileMapDTO;
import com.sunknowledge.dme.rcm.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.reactive.ResponseUtil;

/**
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.DocumentReferenceFileMap}.
 */
@RestController
@RequestMapping("/api")
public class DocumentReferenceFileMapResource {

    private final Logger log = LoggerFactory.getLogger(DocumentReferenceFileMapResource.class);

    private static final String ENTITY_NAME = "salesorderDocumentReferenceFileMap";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DocumentReferenceFileMapService documentReferenceFileMapService;

    private final DocumentReferenceFileMapRepository documentReferenceFileMapRepository;

    public DocumentReferenceFileMapResource(
        DocumentReferenceFileMapService documentReferenceFileMapService,
        DocumentReferenceFileMapRepository documentReferenceFileMapRepository
    ) {
        this.documentReferenceFileMapService = documentReferenceFileMapService;
        this.documentReferenceFileMapRepository = documentReferenceFileMapRepository;
    }

    /**
     * {@code POST  /document-reference-file-maps} : Create a new documentReferenceFileMap.
     *
     * @param documentReferenceFileMapDTO the documentReferenceFileMapDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new documentReferenceFileMapDTO, or with status {@code 400 (Bad Request)} if the documentReferenceFileMap has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/document-reference-file-maps")
    public Mono<ResponseEntity<DocumentReferenceFileMapDTO>> createDocumentReferenceFileMap(
        @Valid @RequestBody DocumentReferenceFileMapDTO documentReferenceFileMapDTO
    ) throws URISyntaxException {
        log.debug("REST request to save DocumentReferenceFileMap : {}", documentReferenceFileMapDTO);
        if (documentReferenceFileMapDTO.getDocumentReferenceFileMapId() != null) {
            throw new BadRequestAlertException("A new documentReferenceFileMap cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return documentReferenceFileMapService
            .save(documentReferenceFileMapDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/document-reference-file-maps/" + result.getDocumentReferenceFileMapId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(
                                applicationName,
                                false,
                                ENTITY_NAME,
                                result.getDocumentReferenceFileMapId().toString()
                            )
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /document-reference-file-maps/:documentReferenceFileMapId} : Updates an existing documentReferenceFileMap.
     *
     * @param documentReferenceFileMapId the id of the documentReferenceFileMapDTO to save.
     * @param documentReferenceFileMapDTO the documentReferenceFileMapDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated documentReferenceFileMapDTO,
     * or with status {@code 400 (Bad Request)} if the documentReferenceFileMapDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the documentReferenceFileMapDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/document-reference-file-maps/{documentReferenceFileMapId}")
    public Mono<ResponseEntity<DocumentReferenceFileMapDTO>> updateDocumentReferenceFileMap(
        @PathVariable(value = "documentReferenceFileMapId", required = false) final Long documentReferenceFileMapId,
        @Valid @RequestBody DocumentReferenceFileMapDTO documentReferenceFileMapDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DocumentReferenceFileMap : {}, {}", documentReferenceFileMapId, documentReferenceFileMapDTO);
        if (documentReferenceFileMapDTO.getDocumentReferenceFileMapId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(documentReferenceFileMapId, documentReferenceFileMapDTO.getDocumentReferenceFileMapId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return documentReferenceFileMapRepository
            .existsById(documentReferenceFileMapId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return documentReferenceFileMapService
                    .update(documentReferenceFileMapDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getDocumentReferenceFileMapId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /document-reference-file-maps/:documentReferenceFileMapId} : Partial updates given fields of an existing documentReferenceFileMap, field will ignore if it is null
     *
     * @param documentReferenceFileMapId the id of the documentReferenceFileMapDTO to save.
     * @param documentReferenceFileMapDTO the documentReferenceFileMapDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated documentReferenceFileMapDTO,
     * or with status {@code 400 (Bad Request)} if the documentReferenceFileMapDTO is not valid,
     * or with status {@code 404 (Not Found)} if the documentReferenceFileMapDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the documentReferenceFileMapDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/document-reference-file-maps/{documentReferenceFileMapId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public Mono<ResponseEntity<DocumentReferenceFileMapDTO>> partialUpdateDocumentReferenceFileMap(
        @PathVariable(value = "documentReferenceFileMapId", required = false) final Long documentReferenceFileMapId,
        @NotNull @RequestBody DocumentReferenceFileMapDTO documentReferenceFileMapDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update DocumentReferenceFileMap partially : {}, {}",
            documentReferenceFileMapId,
            documentReferenceFileMapDTO
        );
        if (documentReferenceFileMapDTO.getDocumentReferenceFileMapId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(documentReferenceFileMapId, documentReferenceFileMapDTO.getDocumentReferenceFileMapId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return documentReferenceFileMapRepository
            .existsById(documentReferenceFileMapId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<DocumentReferenceFileMapDTO> result = documentReferenceFileMapService.partialUpdate(documentReferenceFileMapDTO);

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(res ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    res.getDocumentReferenceFileMapId().toString()
                                )
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /document-reference-file-maps} : get all the documentReferenceFileMaps.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of documentReferenceFileMaps in body.
     */
    @GetMapping("/document-reference-file-maps")
    public Mono<ResponseEntity<List<DocumentReferenceFileMapDTO>>> getAllDocumentReferenceFileMaps(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of DocumentReferenceFileMaps");
        return documentReferenceFileMapService
            .countAll()
            .zipWith(documentReferenceFileMapService.findAll(pageable).collectList())
            .map(countWithEntities ->
                ResponseEntity
                    .ok()
                    .headers(
                        PaginationUtil.generatePaginationHttpHeaders(
                            UriComponentsBuilder.fromHttpRequest(request),
                            new PageImpl<>(countWithEntities.getT2(), pageable, countWithEntities.getT1())
                        )
                    )
                    .body(countWithEntities.getT2())
            );
    }

    /**
     * {@code GET  /document-reference-file-maps/:id} : get the "id" documentReferenceFileMap.
     *
     * @param id the id of the documentReferenceFileMapDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the documentReferenceFileMapDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/document-reference-file-maps/{id}")
    public Mono<ResponseEntity<DocumentReferenceFileMapDTO>> getDocumentReferenceFileMap(@PathVariable Long id) {
        log.debug("REST request to get DocumentReferenceFileMap : {}", id);
        Mono<DocumentReferenceFileMapDTO> documentReferenceFileMapDTO = documentReferenceFileMapService.findOne(id);
        return ResponseUtil.wrapOrNotFound(documentReferenceFileMapDTO);
    }

    /**
     * {@code DELETE  /document-reference-file-maps/:id} : delete the "id" documentReferenceFileMap.
     *
     * @param id the id of the documentReferenceFileMapDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/document-reference-file-maps/{id}")
    public Mono<ResponseEntity<Void>> deleteDocumentReferenceFileMap(@PathVariable Long id) {
        log.debug("REST request to delete DocumentReferenceFileMap : {}", id);
        return documentReferenceFileMapService
            .delete(id)
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
