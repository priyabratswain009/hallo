package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ParDetailsRepository;
import com.sunknowledge.dme.rcm.service.ParDetailsService;
import com.sunknowledge.dme.rcm.service.dto.ParDetailsDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ParDetails}.
 */
@RestController
@RequestMapping("/api")
public class ParDetailsResource {

    private final Logger log = LoggerFactory.getLogger(ParDetailsResource.class);

    private static final String ENTITY_NAME = "salesorderParDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ParDetailsService parDetailsService;

    private final ParDetailsRepository parDetailsRepository;

    public ParDetailsResource(ParDetailsService parDetailsService, ParDetailsRepository parDetailsRepository) {
        this.parDetailsService = parDetailsService;
        this.parDetailsRepository = parDetailsRepository;
    }

    /**
     * {@code POST  /par-details} : Create a new parDetails.
     *
     * @param parDetailsDTO the parDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new parDetailsDTO, or with status {@code 400 (Bad Request)} if the parDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/par-details")
    public Mono<ResponseEntity<ParDetailsDTO>> createParDetails(@Valid @RequestBody ParDetailsDTO parDetailsDTO) throws URISyntaxException {
        log.debug("REST request to save ParDetails : {}", parDetailsDTO);
        if (parDetailsDTO.getParDetailsId() != null) {
            throw new BadRequestAlertException("A new parDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return parDetailsService
            .save(parDetailsDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/par-details/" + result.getParDetailsId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getParDetailsId().toString())
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /par-details/:parDetailsId} : Updates an existing parDetails.
     *
     * @param parDetailsId the id of the parDetailsDTO to save.
     * @param parDetailsDTO the parDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated parDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the parDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the parDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/par-details/{parDetailsId}")
    public Mono<ResponseEntity<ParDetailsDTO>> updateParDetails(
        @PathVariable(value = "parDetailsId", required = false) final Long parDetailsId,
        @Valid @RequestBody ParDetailsDTO parDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ParDetails : {}, {}", parDetailsId, parDetailsDTO);
        if (parDetailsDTO.getParDetailsId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(parDetailsId, parDetailsDTO.getParDetailsId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return parDetailsRepository
            .existsById(parDetailsId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return parDetailsService
                    .update(parDetailsDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, result.getParDetailsId().toString())
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /par-details/:parDetailsId} : Partial updates given fields of an existing parDetails, field will ignore if it is null
     *
     * @param parDetailsId the id of the parDetailsDTO to save.
     * @param parDetailsDTO the parDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated parDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the parDetailsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the parDetailsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the parDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/par-details/{parDetailsId}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<ParDetailsDTO>> partialUpdateParDetails(
        @PathVariable(value = "parDetailsId", required = false) final Long parDetailsId,
        @NotNull @RequestBody ParDetailsDTO parDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ParDetails partially : {}, {}", parDetailsId, parDetailsDTO);
        if (parDetailsDTO.getParDetailsId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(parDetailsId, parDetailsDTO.getParDetailsId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return parDetailsRepository
            .existsById(parDetailsId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<ParDetailsDTO> result = parDetailsService.partialUpdate(parDetailsDTO);

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(res ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, res.getParDetailsId().toString())
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /par-details} : get all the parDetails.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of parDetails in body.
     */
    @GetMapping("/par-details")
    public Mono<ResponseEntity<List<ParDetailsDTO>>> getAllParDetails(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of ParDetails");
        return parDetailsService
            .countAll()
            .zipWith(parDetailsService.findAll(pageable).collectList())
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
     * {@code GET  /par-details/:id} : get the "id" parDetails.
     *
     * @param id the id of the parDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the parDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/par-details/{id}")
    public Mono<ResponseEntity<ParDetailsDTO>> getParDetails(@PathVariable Long id) {
        log.debug("REST request to get ParDetails : {}", id);
        Mono<ParDetailsDTO> parDetailsDTO = parDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(parDetailsDTO);
    }

    /**
     * {@code DELETE  /par-details/:id} : delete the "id" parDetails.
     *
     * @param id the id of the parDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/par-details/{id}")
    public Mono<ResponseEntity<Void>> deleteParDetails(@PathVariable Long id) {
        log.debug("REST request to delete ParDetails : {}", id);
        return parDetailsService
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
