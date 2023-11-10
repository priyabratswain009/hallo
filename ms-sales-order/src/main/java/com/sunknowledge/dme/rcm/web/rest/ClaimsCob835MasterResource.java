package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ClaimsCob835MasterRepository;
import com.sunknowledge.dme.rcm.service.ClaimsCob835MasterService;
import com.sunknowledge.dme.rcm.service.dto.ClaimsCob835MasterDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ClaimsCob835Master}.
 */
@RestController
@RequestMapping("/api")
public class ClaimsCob835MasterResource {

    private final Logger log = LoggerFactory.getLogger(ClaimsCob835MasterResource.class);

    private static final String ENTITY_NAME = "salesorderClaimsCob835Master";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClaimsCob835MasterService claimsCob835MasterService;

    private final ClaimsCob835MasterRepository claimsCob835MasterRepository;

    public ClaimsCob835MasterResource(
        ClaimsCob835MasterService claimsCob835MasterService,
        ClaimsCob835MasterRepository claimsCob835MasterRepository
    ) {
        this.claimsCob835MasterService = claimsCob835MasterService;
        this.claimsCob835MasterRepository = claimsCob835MasterRepository;
    }

    /**
     * {@code POST  /claims-cob-835-masters} : Create a new claimsCob835Master.
     *
     * @param claimsCob835MasterDTO the claimsCob835MasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new claimsCob835MasterDTO, or with status {@code 400 (Bad Request)} if the claimsCob835Master has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/claims-cob-835-masters")
    public Mono<ResponseEntity<ClaimsCob835MasterDTO>> createClaimsCob835Master(@RequestBody ClaimsCob835MasterDTO claimsCob835MasterDTO)
        throws URISyntaxException {
        log.debug("REST request to save ClaimsCob835Master : {}", claimsCob835MasterDTO);
        if (claimsCob835MasterDTO.getClaimCob835MasterId() != null) {
            throw new BadRequestAlertException("A new claimsCob835Master cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return claimsCob835MasterService
            .save(claimsCob835MasterDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/claims-cob-835-masters/" + result.getClaimCob835MasterId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(
                                applicationName,
                                false,
                                ENTITY_NAME,
                                result.getClaimCob835MasterId().toString()
                            )
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /claims-cob-835-masters/:claimCob835MasterId} : Updates an existing claimsCob835Master.
     *
     * @param claimCob835MasterId the id of the claimsCob835MasterDTO to save.
     * @param claimsCob835MasterDTO the claimsCob835MasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claimsCob835MasterDTO,
     * or with status {@code 400 (Bad Request)} if the claimsCob835MasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the claimsCob835MasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/claims-cob-835-masters/{claimCob835MasterId}")
    public Mono<ResponseEntity<ClaimsCob835MasterDTO>> updateClaimsCob835Master(
        @PathVariable(value = "claimCob835MasterId", required = false) final Long claimCob835MasterId,
        @RequestBody ClaimsCob835MasterDTO claimsCob835MasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ClaimsCob835Master : {}, {}", claimCob835MasterId, claimsCob835MasterDTO);
        if (claimsCob835MasterDTO.getClaimCob835MasterId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(claimCob835MasterId, claimsCob835MasterDTO.getClaimCob835MasterId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return claimsCob835MasterRepository
            .existsById(claimCob835MasterId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return claimsCob835MasterService
                    .update(claimsCob835MasterDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getClaimCob835MasterId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /claims-cob-835-masters/:claimCob835MasterId} : Partial updates given fields of an existing claimsCob835Master, field will ignore if it is null
     *
     * @param claimCob835MasterId the id of the claimsCob835MasterDTO to save.
     * @param claimsCob835MasterDTO the claimsCob835MasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claimsCob835MasterDTO,
     * or with status {@code 400 (Bad Request)} if the claimsCob835MasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the claimsCob835MasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the claimsCob835MasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/claims-cob-835-masters/{claimCob835MasterId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public Mono<ResponseEntity<ClaimsCob835MasterDTO>> partialUpdateClaimsCob835Master(
        @PathVariable(value = "claimCob835MasterId", required = false) final Long claimCob835MasterId,
        @RequestBody ClaimsCob835MasterDTO claimsCob835MasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ClaimsCob835Master partially : {}, {}", claimCob835MasterId, claimsCob835MasterDTO);
        if (claimsCob835MasterDTO.getClaimCob835MasterId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(claimCob835MasterId, claimsCob835MasterDTO.getClaimCob835MasterId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return claimsCob835MasterRepository
            .existsById(claimCob835MasterId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<ClaimsCob835MasterDTO> result = claimsCob835MasterService.partialUpdate(claimsCob835MasterDTO);

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
                                    res.getClaimCob835MasterId().toString()
                                )
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /claims-cob-835-masters} : get all the claimsCob835Masters.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of claimsCob835Masters in body.
     */
    @GetMapping("/claims-cob-835-masters")
    public Mono<ResponseEntity<List<ClaimsCob835MasterDTO>>> getAllClaimsCob835Masters(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of ClaimsCob835Masters");
        return claimsCob835MasterService
            .countAll()
            .zipWith(claimsCob835MasterService.findAll(pageable).collectList())
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
     * {@code GET  /claims-cob-835-masters/:id} : get the "id" claimsCob835Master.
     *
     * @param id the id of the claimsCob835MasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the claimsCob835MasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/claims-cob-835-masters/{id}")
    public Mono<ResponseEntity<ClaimsCob835MasterDTO>> getClaimsCob835Master(@PathVariable Long id) {
        log.debug("REST request to get ClaimsCob835Master : {}", id);
        Mono<ClaimsCob835MasterDTO> claimsCob835MasterDTO = claimsCob835MasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(claimsCob835MasterDTO);
    }

    /**
     * {@code DELETE  /claims-cob-835-masters/:id} : delete the "id" claimsCob835Master.
     *
     * @param id the id of the claimsCob835MasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/claims-cob-835-masters/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteClaimsCob835Master(@PathVariable Long id) {
        log.debug("REST request to delete ClaimsCob835Master : {}", id);
        return claimsCob835MasterService
            .delete(id)
            .map(result ->
                ResponseEntity
                    .noContent()
                    .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                    .build()
            );
    }
}
