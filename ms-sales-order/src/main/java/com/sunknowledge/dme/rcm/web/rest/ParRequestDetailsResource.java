package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ParRequestDetailsRepository;
import com.sunknowledge.dme.rcm.service.ParRequestDetailsService;
import com.sunknowledge.dme.rcm.service.dto.ParRequestDetailsDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ParRequestDetails}.
 */
@RestController
@RequestMapping("/api")
public class ParRequestDetailsResource {

    private final Logger log = LoggerFactory.getLogger(ParRequestDetailsResource.class);

    private static final String ENTITY_NAME = "salesorderParRequestDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ParRequestDetailsService parRequestDetailsService;

    private final ParRequestDetailsRepository parRequestDetailsRepository;

    public ParRequestDetailsResource(
        ParRequestDetailsService parRequestDetailsService,
        ParRequestDetailsRepository parRequestDetailsRepository
    ) {
        this.parRequestDetailsService = parRequestDetailsService;
        this.parRequestDetailsRepository = parRequestDetailsRepository;
    }

    /**
     * {@code POST  /par-request-details} : Create a new parRequestDetails.
     *
     * @param parRequestDetailsDTO the parRequestDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new parRequestDetailsDTO, or with status {@code 400 (Bad Request)} if the parRequestDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/par-request-details")
    public Mono<ResponseEntity<ParRequestDetailsDTO>> createParRequestDetails(
        @Valid @RequestBody ParRequestDetailsDTO parRequestDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to save ParRequestDetails : {}", parRequestDetailsDTO);
        if (parRequestDetailsDTO.getParRequestDetailsId() != null) {
            throw new BadRequestAlertException("A new parRequestDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return parRequestDetailsService
            .save(parRequestDetailsDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/par-request-details/" + result.getParRequestDetailsId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(
                                applicationName,
                                false,
                                ENTITY_NAME,
                                result.getParRequestDetailsId().toString()
                            )
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /par-request-details/:parRequestDetailsId} : Updates an existing parRequestDetails.
     *
     * @param parRequestDetailsId the id of the parRequestDetailsDTO to save.
     * @param parRequestDetailsDTO the parRequestDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated parRequestDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the parRequestDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the parRequestDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/par-request-details/{parRequestDetailsId}")
    public Mono<ResponseEntity<ParRequestDetailsDTO>> updateParRequestDetails(
        @PathVariable(value = "parRequestDetailsId", required = false) final Long parRequestDetailsId,
        @Valid @RequestBody ParRequestDetailsDTO parRequestDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ParRequestDetails : {}, {}", parRequestDetailsId, parRequestDetailsDTO);
        if (parRequestDetailsDTO.getParRequestDetailsId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(parRequestDetailsId, parRequestDetailsDTO.getParRequestDetailsId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return parRequestDetailsRepository
            .existsById(parRequestDetailsId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return parRequestDetailsService
                    .update(parRequestDetailsDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getParRequestDetailsId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /par-request-details/:parRequestDetailsId} : Partial updates given fields of an existing parRequestDetails, field will ignore if it is null
     *
     * @param parRequestDetailsId the id of the parRequestDetailsDTO to save.
     * @param parRequestDetailsDTO the parRequestDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated parRequestDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the parRequestDetailsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the parRequestDetailsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the parRequestDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/par-request-details/{parRequestDetailsId}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<ParRequestDetailsDTO>> partialUpdateParRequestDetails(
        @PathVariable(value = "parRequestDetailsId", required = false) final Long parRequestDetailsId,
        @NotNull @RequestBody ParRequestDetailsDTO parRequestDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ParRequestDetails partially : {}, {}", parRequestDetailsId, parRequestDetailsDTO);
        if (parRequestDetailsDTO.getParRequestDetailsId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(parRequestDetailsId, parRequestDetailsDTO.getParRequestDetailsId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return parRequestDetailsRepository
            .existsById(parRequestDetailsId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<ParRequestDetailsDTO> result = parRequestDetailsService.partialUpdate(parRequestDetailsDTO);

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
                                    res.getParRequestDetailsId().toString()
                                )
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /par-request-details} : get all the parRequestDetails.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of parRequestDetails in body.
     */
    @GetMapping("/par-request-details")
    public Mono<ResponseEntity<List<ParRequestDetailsDTO>>> getAllParRequestDetails(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of ParRequestDetails");
        return parRequestDetailsService
            .countAll()
            .zipWith(parRequestDetailsService.findAll(pageable).collectList())
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
     * {@code GET  /par-request-details/:id} : get the "id" parRequestDetails.
     *
     * @param id the id of the parRequestDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the parRequestDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/par-request-details/{id}")
    public Mono<ResponseEntity<ParRequestDetailsDTO>> getParRequestDetails(@PathVariable Long id) {
        log.debug("REST request to get ParRequestDetails : {}", id);
        Mono<ParRequestDetailsDTO> parRequestDetailsDTO = parRequestDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(parRequestDetailsDTO);
    }

    /**
     * {@code DELETE  /par-request-details/:id} : delete the "id" parRequestDetails.
     *
     * @param id the id of the parRequestDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/par-request-details/{id}")
    public Mono<ResponseEntity<Void>> deleteParRequestDetails(@PathVariable Long id) {
        log.debug("REST request to delete ParRequestDetails : {}", id);
        return parRequestDetailsService
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
