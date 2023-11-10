package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ClaimsCob835DetailsRepository;
import com.sunknowledge.dme.rcm.service.ClaimsCob835DetailsService;
import com.sunknowledge.dme.rcm.service.dto.ClaimsCob835DetailsDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ClaimsCob835Details}.
 */
@RestController
@RequestMapping("/api")
public class ClaimsCob835DetailsResource {

    private final Logger log = LoggerFactory.getLogger(ClaimsCob835DetailsResource.class);

    private static final String ENTITY_NAME = "salesorderClaimsCob835Details";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClaimsCob835DetailsService claimsCob835DetailsService;

    private final ClaimsCob835DetailsRepository claimsCob835DetailsRepository;

    public ClaimsCob835DetailsResource(
        ClaimsCob835DetailsService claimsCob835DetailsService,
        ClaimsCob835DetailsRepository claimsCob835DetailsRepository
    ) {
        this.claimsCob835DetailsService = claimsCob835DetailsService;
        this.claimsCob835DetailsRepository = claimsCob835DetailsRepository;
    }

    /**
     * {@code POST  /claims-cob-835-details} : Create a new claimsCob835Details.
     *
     * @param claimsCob835DetailsDTO the claimsCob835DetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new claimsCob835DetailsDTO, or with status {@code 400 (Bad Request)} if the claimsCob835Details has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/claims-cob-835-details")
    public Mono<ResponseEntity<ClaimsCob835DetailsDTO>> createClaimsCob835Details(
        @RequestBody ClaimsCob835DetailsDTO claimsCob835DetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to save ClaimsCob835Details : {}", claimsCob835DetailsDTO);
        if (claimsCob835DetailsDTO.getClaimCob835DetailId() != null) {
            throw new BadRequestAlertException("A new claimsCob835Details cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return claimsCob835DetailsService
            .save(claimsCob835DetailsDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/claims-cob-835-details/" + result.getClaimCob835DetailId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(
                                applicationName,
                                false,
                                ENTITY_NAME,
                                result.getClaimCob835DetailId().toString()
                            )
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /claims-cob-835-details/:claimCob835DetailId} : Updates an existing claimsCob835Details.
     *
     * @param claimCob835DetailId the id of the claimsCob835DetailsDTO to save.
     * @param claimsCob835DetailsDTO the claimsCob835DetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claimsCob835DetailsDTO,
     * or with status {@code 400 (Bad Request)} if the claimsCob835DetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the claimsCob835DetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/claims-cob-835-details/{claimCob835DetailId}")
    public Mono<ResponseEntity<ClaimsCob835DetailsDTO>> updateClaimsCob835Details(
        @PathVariable(value = "claimCob835DetailId", required = false) final Long claimCob835DetailId,
        @RequestBody ClaimsCob835DetailsDTO claimsCob835DetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ClaimsCob835Details : {}, {}", claimCob835DetailId, claimsCob835DetailsDTO);
        if (claimsCob835DetailsDTO.getClaimCob835DetailId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(claimCob835DetailId, claimsCob835DetailsDTO.getClaimCob835DetailId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return claimsCob835DetailsRepository
            .existsById(claimCob835DetailId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return claimsCob835DetailsService
                    .update(claimsCob835DetailsDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getClaimCob835DetailId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /claims-cob-835-details/:claimCob835DetailId} : Partial updates given fields of an existing claimsCob835Details, field will ignore if it is null
     *
     * @param claimCob835DetailId the id of the claimsCob835DetailsDTO to save.
     * @param claimsCob835DetailsDTO the claimsCob835DetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claimsCob835DetailsDTO,
     * or with status {@code 400 (Bad Request)} if the claimsCob835DetailsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the claimsCob835DetailsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the claimsCob835DetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/claims-cob-835-details/{claimCob835DetailId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public Mono<ResponseEntity<ClaimsCob835DetailsDTO>> partialUpdateClaimsCob835Details(
        @PathVariable(value = "claimCob835DetailId", required = false) final Long claimCob835DetailId,
        @RequestBody ClaimsCob835DetailsDTO claimsCob835DetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ClaimsCob835Details partially : {}, {}", claimCob835DetailId, claimsCob835DetailsDTO);
        if (claimsCob835DetailsDTO.getClaimCob835DetailId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(claimCob835DetailId, claimsCob835DetailsDTO.getClaimCob835DetailId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return claimsCob835DetailsRepository
            .existsById(claimCob835DetailId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<ClaimsCob835DetailsDTO> result = claimsCob835DetailsService.partialUpdate(claimsCob835DetailsDTO);

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
                                    res.getClaimCob835DetailId().toString()
                                )
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /claims-cob-835-details} : get all the claimsCob835Details.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of claimsCob835Details in body.
     */
    @GetMapping("/claims-cob-835-details")
    public Mono<ResponseEntity<List<ClaimsCob835DetailsDTO>>> getAllClaimsCob835Details(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of ClaimsCob835Details");
        return claimsCob835DetailsService
            .countAll()
            .zipWith(claimsCob835DetailsService.findAll(pageable).collectList())
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
     * {@code GET  /claims-cob-835-details/:id} : get the "id" claimsCob835Details.
     *
     * @param id the id of the claimsCob835DetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the claimsCob835DetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/claims-cob-835-details/{id}")
    public Mono<ResponseEntity<ClaimsCob835DetailsDTO>> getClaimsCob835Details(@PathVariable Long id) {
        log.debug("REST request to get ClaimsCob835Details : {}", id);
        Mono<ClaimsCob835DetailsDTO> claimsCob835DetailsDTO = claimsCob835DetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(claimsCob835DetailsDTO);
    }

    /**
     * {@code DELETE  /claims-cob-835-details/:id} : delete the "id" claimsCob835Details.
     *
     * @param id the id of the claimsCob835DetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/claims-cob-835-details/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteClaimsCob835Details(@PathVariable Long id) {
        log.debug("REST request to delete ClaimsCob835Details : {}", id);
        return claimsCob835DetailsService
            .delete(id)
            .map(result ->
                ResponseEntity
                    .noContent()
                    .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                    .build()
            );
    }
}
