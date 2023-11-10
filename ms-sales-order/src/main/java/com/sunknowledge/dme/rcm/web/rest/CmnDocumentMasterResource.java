package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.CmnDocumentMasterRepository;
import com.sunknowledge.dme.rcm.service.CmnDocumentMasterService;
import com.sunknowledge.dme.rcm.service.dto.CmnDocumentMasterDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.CmnDocumentMaster}.
 */
@RestController
@RequestMapping("/api")
public class CmnDocumentMasterResource {

    private final Logger log = LoggerFactory.getLogger(CmnDocumentMasterResource.class);

    private static final String ENTITY_NAME = "salesorderCmnDocumentMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CmnDocumentMasterService cmnDocumentMasterService;

    private final CmnDocumentMasterRepository cmnDocumentMasterRepository;

    public CmnDocumentMasterResource(
        CmnDocumentMasterService cmnDocumentMasterService,
        CmnDocumentMasterRepository cmnDocumentMasterRepository
    ) {
        this.cmnDocumentMasterService = cmnDocumentMasterService;
        this.cmnDocumentMasterRepository = cmnDocumentMasterRepository;
    }

    /**
     * {@code POST  /cmn-document-masters} : Create a new cmnDocumentMaster.
     *
     * @param cmnDocumentMasterDTO the cmnDocumentMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cmnDocumentMasterDTO, or with status {@code 400 (Bad Request)} if the cmnDocumentMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cmn-document-masters")
    public Mono<ResponseEntity<CmnDocumentMasterDTO>> createCmnDocumentMaster(
        @Valid @RequestBody CmnDocumentMasterDTO cmnDocumentMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to save CmnDocumentMaster : {}", cmnDocumentMasterDTO);
        if (cmnDocumentMasterDTO.getCmnDocumentId() != null) {
            throw new BadRequestAlertException("A new cmnDocumentMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return cmnDocumentMasterService
            .save(cmnDocumentMasterDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/cmn-document-masters/" + result.getCmnDocumentId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getCmnDocumentId().toString())
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /cmn-document-masters/:cmnDocumentId} : Updates an existing cmnDocumentMaster.
     *
     * @param cmnDocumentId the id of the cmnDocumentMasterDTO to save.
     * @param cmnDocumentMasterDTO the cmnDocumentMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cmnDocumentMasterDTO,
     * or with status {@code 400 (Bad Request)} if the cmnDocumentMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cmnDocumentMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cmn-document-masters/{cmnDocumentId}")
    public Mono<ResponseEntity<CmnDocumentMasterDTO>> updateCmnDocumentMaster(
        @PathVariable(value = "cmnDocumentId", required = false) final Long cmnDocumentId,
        @Valid @RequestBody CmnDocumentMasterDTO cmnDocumentMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CmnDocumentMaster : {}, {}", cmnDocumentId, cmnDocumentMasterDTO);
        if (cmnDocumentMasterDTO.getCmnDocumentId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(cmnDocumentId, cmnDocumentMasterDTO.getCmnDocumentId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return cmnDocumentMasterRepository
            .existsById(cmnDocumentId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return cmnDocumentMasterService
                    .update(cmnDocumentMasterDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getCmnDocumentId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /cmn-document-masters/:cmnDocumentId} : Partial updates given fields of an existing cmnDocumentMaster, field will ignore if it is null
     *
     * @param cmnDocumentId the id of the cmnDocumentMasterDTO to save.
     * @param cmnDocumentMasterDTO the cmnDocumentMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cmnDocumentMasterDTO,
     * or with status {@code 400 (Bad Request)} if the cmnDocumentMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the cmnDocumentMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the cmnDocumentMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/cmn-document-masters/{cmnDocumentId}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<CmnDocumentMasterDTO>> partialUpdateCmnDocumentMaster(
        @PathVariable(value = "cmnDocumentId", required = false) final Long cmnDocumentId,
        @NotNull @RequestBody CmnDocumentMasterDTO cmnDocumentMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CmnDocumentMaster partially : {}, {}", cmnDocumentId, cmnDocumentMasterDTO);
        if (cmnDocumentMasterDTO.getCmnDocumentId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(cmnDocumentId, cmnDocumentMasterDTO.getCmnDocumentId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return cmnDocumentMasterRepository
            .existsById(cmnDocumentId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<CmnDocumentMasterDTO> result = cmnDocumentMasterService.partialUpdate(cmnDocumentMasterDTO);

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(res ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, res.getCmnDocumentId().toString())
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /cmn-document-masters} : get all the cmnDocumentMasters.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cmnDocumentMasters in body.
     */
    @GetMapping("/cmn-document-masters")
    public Mono<ResponseEntity<List<CmnDocumentMasterDTO>>> getAllCmnDocumentMasters(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of CmnDocumentMasters");
        return cmnDocumentMasterService
            .countAll()
            .zipWith(cmnDocumentMasterService.findAll(pageable).collectList())
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
     * {@code GET  /cmn-document-masters/:id} : get the "id" cmnDocumentMaster.
     *
     * @param id the id of the cmnDocumentMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cmnDocumentMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cmn-document-masters/{id}")
    public Mono<ResponseEntity<CmnDocumentMasterDTO>> getCmnDocumentMaster(@PathVariable Long id) {
        log.debug("REST request to get CmnDocumentMaster : {}", id);
        Mono<CmnDocumentMasterDTO> cmnDocumentMasterDTO = cmnDocumentMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cmnDocumentMasterDTO);
    }

    /**
     * {@code DELETE  /cmn-document-masters/:id} : delete the "id" cmnDocumentMaster.
     *
     * @param id the id of the cmnDocumentMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cmn-document-masters/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteCmnDocumentMaster(@PathVariable Long id) {
        log.debug("REST request to delete CmnDocumentMaster : {}", id);
        return cmnDocumentMasterService
            .delete(id)
            .map(result ->
                ResponseEntity
                    .noContent()
                    .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                    .build()
            );
    }
}
