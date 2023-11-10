package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ElligibilityResponseRepository;
import com.sunknowledge.dme.rcm.service.ElligibilityResponseService;
import com.sunknowledge.dme.rcm.service.dto.ElligibilityResponseDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ElligibilityResponse}.
 */
@RestController
@RequestMapping("/api")
public class ElligibilityResponseResource {

    private final Logger log = LoggerFactory.getLogger(ElligibilityResponseResource.class);

    private static final String ENTITY_NAME = "salesorderElligibilityResponse";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ElligibilityResponseService elligibilityResponseService;

    private final ElligibilityResponseRepository elligibilityResponseRepository;

    public ElligibilityResponseResource(
        ElligibilityResponseService elligibilityResponseService,
        ElligibilityResponseRepository elligibilityResponseRepository
    ) {
        this.elligibilityResponseService = elligibilityResponseService;
        this.elligibilityResponseRepository = elligibilityResponseRepository;
    }

    /**
     * {@code POST  /elligibility-responses} : Create a new elligibilityResponse.
     *
     * @param elligibilityResponseDTO the elligibilityResponseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new elligibilityResponseDTO, or with status {@code 400 (Bad Request)} if the elligibilityResponse has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/elligibility-responses")
    public Mono<ResponseEntity<ElligibilityResponseDTO>> createElligibilityResponse(
        @Valid @RequestBody ElligibilityResponseDTO elligibilityResponseDTO
    ) throws URISyntaxException {
        log.debug("REST request to save ElligibilityResponse : {}", elligibilityResponseDTO);
        if (elligibilityResponseDTO.getElligibilityResponseStatusId() != null) {
            throw new BadRequestAlertException("A new elligibilityResponse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return elligibilityResponseService
            .save(elligibilityResponseDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/elligibility-responses/" + result.getElligibilityResponseStatusId()))
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
     * {@code PUT  /elligibility-responses/:elligibilityResponseStatusId} : Updates an existing elligibilityResponse.
     *
     * @param elligibilityResponseStatusId the id of the elligibilityResponseDTO to save.
     * @param elligibilityResponseDTO the elligibilityResponseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated elligibilityResponseDTO,
     * or with status {@code 400 (Bad Request)} if the elligibilityResponseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the elligibilityResponseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/elligibility-responses/{elligibilityResponseStatusId}")
    public Mono<ResponseEntity<ElligibilityResponseDTO>> updateElligibilityResponse(
        @PathVariable(value = "elligibilityResponseStatusId", required = false) final Long elligibilityResponseStatusId,
        @Valid @RequestBody ElligibilityResponseDTO elligibilityResponseDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ElligibilityResponse : {}, {}", elligibilityResponseStatusId, elligibilityResponseDTO);
        if (elligibilityResponseDTO.getElligibilityResponseStatusId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(elligibilityResponseStatusId, elligibilityResponseDTO.getElligibilityResponseStatusId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return elligibilityResponseRepository
            .existsById(elligibilityResponseStatusId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return elligibilityResponseService
                    .update(elligibilityResponseDTO)
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
     * {@code PATCH  /elligibility-responses/:elligibilityResponseStatusId} : Partial updates given fields of an existing elligibilityResponse, field will ignore if it is null
     *
     * @param elligibilityResponseStatusId the id of the elligibilityResponseDTO to save.
     * @param elligibilityResponseDTO the elligibilityResponseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated elligibilityResponseDTO,
     * or with status {@code 400 (Bad Request)} if the elligibilityResponseDTO is not valid,
     * or with status {@code 404 (Not Found)} if the elligibilityResponseDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the elligibilityResponseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/elligibility-responses/{elligibilityResponseStatusId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public Mono<ResponseEntity<ElligibilityResponseDTO>> partialUpdateElligibilityResponse(
        @PathVariable(value = "elligibilityResponseStatusId", required = false) final Long elligibilityResponseStatusId,
        @NotNull @RequestBody ElligibilityResponseDTO elligibilityResponseDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update ElligibilityResponse partially : {}, {}",
            elligibilityResponseStatusId,
            elligibilityResponseDTO
        );
        if (elligibilityResponseDTO.getElligibilityResponseStatusId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(elligibilityResponseStatusId, elligibilityResponseDTO.getElligibilityResponseStatusId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return elligibilityResponseRepository
            .existsById(elligibilityResponseStatusId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<ElligibilityResponseDTO> result = elligibilityResponseService.partialUpdate(elligibilityResponseDTO);

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
     * {@code GET  /elligibility-responses} : get all the elligibilityResponses.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of elligibilityResponses in body.
     */
    @GetMapping("/elligibility-responses")
    public Mono<ResponseEntity<List<ElligibilityResponseDTO>>> getAllElligibilityResponses(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of ElligibilityResponses");
        return elligibilityResponseService
            .countAll()
            .zipWith(elligibilityResponseService.findAll(pageable).collectList())
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
     * {@code GET  /elligibility-responses/:id} : get the "id" elligibilityResponse.
     *
     * @param id the id of the elligibilityResponseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the elligibilityResponseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/elligibility-responses/{id}")
    public Mono<ResponseEntity<ElligibilityResponseDTO>> getElligibilityResponse(@PathVariable Long id) {
        log.debug("REST request to get ElligibilityResponse : {}", id);
        Mono<ElligibilityResponseDTO> elligibilityResponseDTO = elligibilityResponseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(elligibilityResponseDTO);
    }

    /**
     * {@code DELETE  /elligibility-responses/:id} : delete the "id" elligibilityResponse.
     *
     * @param id the id of the elligibilityResponseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/elligibility-responses/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteElligibilityResponse(@PathVariable Long id) {
        log.debug("REST request to delete ElligibilityResponse : {}", id);
        return elligibilityResponseService
            .delete(id)
            .map(result ->
                ResponseEntity
                    .noContent()
                    .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                    .build()
            );
    }
}
