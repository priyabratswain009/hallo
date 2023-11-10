package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.PatientDocumentRepository;
import com.sunknowledge.dme.rcm.service.PatientDocumentService;
import com.sunknowledge.dme.rcm.service.dto.PatientDocumentDTO;
import com.sunknowledge.dme.rcm.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.PatientDocument}.
 */
@RestController
@RequestMapping("/api")
public class PatientDocumentResource {

    private final Logger log = LoggerFactory.getLogger(PatientDocumentResource.class);

    private static final String ENTITY_NAME = "patientPatientDocument";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PatientDocumentService patientDocumentService;

    private final PatientDocumentRepository patientDocumentRepository;

    public PatientDocumentResource(PatientDocumentService patientDocumentService, PatientDocumentRepository patientDocumentRepository) {
        this.patientDocumentService = patientDocumentService;
        this.patientDocumentRepository = patientDocumentRepository;
    }

    /**
     * {@code POST  /patient-documents} : Create a new patientDocument.
     *
     * @param patientDocumentDTO the patientDocumentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new patientDocumentDTO, or with status {@code 400 (Bad Request)} if the patientDocument has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/patient-documents")
    public Mono<ResponseEntity<PatientDocumentDTO>> createPatientDocument(@RequestBody PatientDocumentDTO patientDocumentDTO)
        throws URISyntaxException {
        log.debug("REST request to save PatientDocument : {}", patientDocumentDTO);
        if (patientDocumentDTO.getPatientDocumentId() != null) {
            throw new BadRequestAlertException("A new patientDocument cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return patientDocumentService
            .save(patientDocumentDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/patient-documents/" + result.getPatientDocumentId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(
                                applicationName,
                                false,
                                ENTITY_NAME,
                                result.getPatientDocumentId().toString()
                            )
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /patient-documents/:patientDocumentId} : Updates an existing patientDocument.
     *
     * @param patientDocumentId the id of the patientDocumentDTO to save.
     * @param patientDocumentDTO the patientDocumentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated patientDocumentDTO,
     * or with status {@code 400 (Bad Request)} if the patientDocumentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the patientDocumentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/patient-documents/{patientDocumentId}")
    public Mono<ResponseEntity<PatientDocumentDTO>> updatePatientDocument(
        @PathVariable(value = "patientDocumentId", required = false) final Long patientDocumentId,
        @RequestBody PatientDocumentDTO patientDocumentDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PatientDocument : {}, {}", patientDocumentId, patientDocumentDTO);
        if (patientDocumentDTO.getPatientDocumentId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(patientDocumentId, patientDocumentDTO.getPatientDocumentId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return patientDocumentRepository
            .existsById(patientDocumentId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return patientDocumentService
                    .update(patientDocumentDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getPatientDocumentId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /patient-documents/:patientDocumentId} : Partial updates given fields of an existing patientDocument, field will ignore if it is null
     *
     * @param patientDocumentId the id of the patientDocumentDTO to save.
     * @param patientDocumentDTO the patientDocumentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated patientDocumentDTO,
     * or with status {@code 400 (Bad Request)} if the patientDocumentDTO is not valid,
     * or with status {@code 404 (Not Found)} if the patientDocumentDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the patientDocumentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/patient-documents/{patientDocumentId}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<PatientDocumentDTO>> partialUpdatePatientDocument(
        @PathVariable(value = "patientDocumentId", required = false) final Long patientDocumentId,
        @RequestBody PatientDocumentDTO patientDocumentDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PatientDocument partially : {}, {}", patientDocumentId, patientDocumentDTO);
        if (patientDocumentDTO.getPatientDocumentId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(patientDocumentId, patientDocumentDTO.getPatientDocumentId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return patientDocumentRepository
            .existsById(patientDocumentId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<PatientDocumentDTO> result = patientDocumentService.partialUpdate(patientDocumentDTO);

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
                                    res.getPatientDocumentId().toString()
                                )
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /patient-documents} : get all the patientDocuments.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of patientDocuments in body.
     */
    @GetMapping("/patient-documents")
    public Mono<ResponseEntity<List<PatientDocumentDTO>>> getAllPatientDocuments(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of PatientDocuments");
        return patientDocumentService
            .countAll()
            .zipWith(patientDocumentService.findAll(pageable).collectList())
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
     * {@code GET  /patient-documents/:id} : get the "id" patientDocument.
     *
     * @param id the id of the patientDocumentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the patientDocumentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/patient-documents/{id}")
    public Mono<ResponseEntity<PatientDocumentDTO>> getPatientDocument(@PathVariable Long id) {
        log.debug("REST request to get PatientDocument : {}", id);
        Mono<PatientDocumentDTO> patientDocumentDTO = patientDocumentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(patientDocumentDTO);
    }

    /**
     * {@code DELETE  /patient-documents/:id} : delete the "id" patientDocument.
     *
     * @param id the id of the patientDocumentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/patient-documents/{id}")
    public Mono<ResponseEntity<Void>> deletePatientDocument(@PathVariable Long id) {
        log.debug("REST request to delete PatientDocument : {}", id);
        return patientDocumentService
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
