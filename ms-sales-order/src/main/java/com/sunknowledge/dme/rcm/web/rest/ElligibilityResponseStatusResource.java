package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ElligibilityResponseStatusRepository;
import com.sunknowledge.dme.rcm.service.ElligibilityResponseStatusService;
import com.sunknowledge.dme.rcm.service.dto.ElligibilityResponseStatusDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ElligibilityResponseStatus}.
 */
@RestController
@RequestMapping("/api")
public class ElligibilityResponseStatusResource {

    private final Logger log = LoggerFactory.getLogger(ElligibilityResponseStatusResource.class);

    private static final String ENTITY_NAME = "salesorderElligibilityResponseStatus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ElligibilityResponseStatusService elligibilityResponseStatusService;

    private final ElligibilityResponseStatusRepository elligibilityResponseStatusRepository;

    public ElligibilityResponseStatusResource(
        ElligibilityResponseStatusService elligibilityResponseStatusService,
        ElligibilityResponseStatusRepository elligibilityResponseStatusRepository
    ) {
        this.elligibilityResponseStatusService = elligibilityResponseStatusService;
        this.elligibilityResponseStatusRepository = elligibilityResponseStatusRepository;
    }

    /**
     * {@code POST  /elligibility-response-statuses} : Create a new elligibilityResponseStatus.
     *
     * @param elligibilityResponseStatusDTO the elligibilityResponseStatusDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new elligibilityResponseStatusDTO, or with status {@code 400 (Bad Request)} if the elligibilityResponseStatus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/elligibility-response-statuses")
    public Mono<ResponseEntity<ElligibilityResponseStatusDTO>> createElligibilityResponseStatus(
        @RequestBody ElligibilityResponseStatusDTO elligibilityResponseStatusDTO
    ) throws URISyntaxException {
        log.debug("REST request to save ElligibilityResponseStatus : {}", elligibilityResponseStatusDTO);
        if (elligibilityResponseStatusDTO.getElligibilityResponseStatusId() != null) {
            throw new BadRequestAlertException("A new elligibilityResponseStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return elligibilityResponseStatusService
            .save(elligibilityResponseStatusDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/elligibility-response-statuses/" + result.getElligibilityResponseStatusId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(
                                applicationName,
                                false,
                                ENTITY_NAME,
                                result.getElligibilityResponseStatusId().toString()
                            )
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /elligibility-response-statuses/:elligibilityResponseStatusId} : Updates an existing elligibilityResponseStatus.
     *
     * @param elligibilityResponseStatusId the id of the elligibilityResponseStatusDTO to save.
     * @param elligibilityResponseStatusDTO the elligibilityResponseStatusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated elligibilityResponseStatusDTO,
     * or with status {@code 400 (Bad Request)} if the elligibilityResponseStatusDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the elligibilityResponseStatusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/elligibility-response-statuses/{elligibilityResponseStatusId}")
    public Mono<ResponseEntity<ElligibilityResponseStatusDTO>> updateElligibilityResponseStatus(
        @PathVariable(value = "elligibilityResponseStatusId", required = false) final Long elligibilityResponseStatusId,
        @RequestBody ElligibilityResponseStatusDTO elligibilityResponseStatusDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to update ElligibilityResponseStatus : {}, {}",
            elligibilityResponseStatusId,
            elligibilityResponseStatusDTO
        );
        if (elligibilityResponseStatusDTO.getElligibilityResponseStatusId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(elligibilityResponseStatusId, elligibilityResponseStatusDTO.getElligibilityResponseStatusId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return elligibilityResponseStatusRepository
            .existsById(elligibilityResponseStatusId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return elligibilityResponseStatusService
                    .update(elligibilityResponseStatusDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getElligibilityResponseStatusId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /elligibility-response-statuses/:elligibilityResponseStatusId} : Partial updates given fields of an existing elligibilityResponseStatus, field will ignore if it is null
     *
     * @param elligibilityResponseStatusId the id of the elligibilityResponseStatusDTO to save.
     * @param elligibilityResponseStatusDTO the elligibilityResponseStatusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated elligibilityResponseStatusDTO,
     * or with status {@code 400 (Bad Request)} if the elligibilityResponseStatusDTO is not valid,
     * or with status {@code 404 (Not Found)} if the elligibilityResponseStatusDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the elligibilityResponseStatusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/elligibility-response-statuses/{elligibilityResponseStatusId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public Mono<ResponseEntity<ElligibilityResponseStatusDTO>> partialUpdateElligibilityResponseStatus(
        @PathVariable(value = "elligibilityResponseStatusId", required = false) final Long elligibilityResponseStatusId,
        @RequestBody ElligibilityResponseStatusDTO elligibilityResponseStatusDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update ElligibilityResponseStatus partially : {}, {}",
            elligibilityResponseStatusId,
            elligibilityResponseStatusDTO
        );
        if (elligibilityResponseStatusDTO.getElligibilityResponseStatusId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(elligibilityResponseStatusId, elligibilityResponseStatusDTO.getElligibilityResponseStatusId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return elligibilityResponseStatusRepository
            .existsById(elligibilityResponseStatusId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<ElligibilityResponseStatusDTO> result = elligibilityResponseStatusService.partialUpdate(elligibilityResponseStatusDTO);

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
                                    res.getElligibilityResponseStatusId().toString()
                                )
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /elligibility-response-statuses} : get all the elligibilityResponseStatuses.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of elligibilityResponseStatuses in body.
     */
    @GetMapping("/elligibility-response-statuses")
    public Mono<ResponseEntity<List<ElligibilityResponseStatusDTO>>> getAllElligibilityResponseStatuses(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of ElligibilityResponseStatuses");
        return elligibilityResponseStatusService
            .countAll()
            .zipWith(elligibilityResponseStatusService.findAll(pageable).collectList())
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
     * {@code GET  /elligibility-response-statuses/:id} : get the "id" elligibilityResponseStatus.
     *
     * @param id the id of the elligibilityResponseStatusDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the elligibilityResponseStatusDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/elligibility-response-statuses/{id}")
    public Mono<ResponseEntity<ElligibilityResponseStatusDTO>> getElligibilityResponseStatus(@PathVariable Long id) {
        log.debug("REST request to get ElligibilityResponseStatus : {}", id);
        Mono<ElligibilityResponseStatusDTO> elligibilityResponseStatusDTO = elligibilityResponseStatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(elligibilityResponseStatusDTO);
    }

    /**
     * {@code DELETE  /elligibility-response-statuses/:id} : delete the "id" elligibilityResponseStatus.
     *
     * @param id the id of the elligibilityResponseStatusDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/elligibility-response-statuses/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteElligibilityResponseStatus(@PathVariable Long id) {
        log.debug("REST request to delete ElligibilityResponseStatus : {}", id);
        return elligibilityResponseStatusService
            .delete(id)
            .map(result ->
                ResponseEntity
                    .noContent()
                    .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                    .build()
            );
    }
}
