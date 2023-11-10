package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.FunctionalAbilityRepository;
import com.sunknowledge.dme.rcm.service.FunctionalAbilityService;
import com.sunknowledge.dme.rcm.service.dto.FunctionalAbilityDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.FunctionalAbility}.
 */
@RestController
@RequestMapping("/api")
public class FunctionalAbilityResource {

    private final Logger log = LoggerFactory.getLogger(FunctionalAbilityResource.class);

    private static final String ENTITY_NAME = "patientFunctionalAbility";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FunctionalAbilityService functionalAbilityService;

    private final FunctionalAbilityRepository functionalAbilityRepository;

    public FunctionalAbilityResource(
        FunctionalAbilityService functionalAbilityService,
        FunctionalAbilityRepository functionalAbilityRepository
    ) {
        this.functionalAbilityService = functionalAbilityService;
        this.functionalAbilityRepository = functionalAbilityRepository;
    }

    /**
     * {@code POST  /functional-abilities} : Create a new functionalAbility.
     *
     * @param functionalAbilityDTO the functionalAbilityDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new functionalAbilityDTO, or with status {@code 400 (Bad Request)} if the functionalAbility has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/functional-abilities")
    public Mono<ResponseEntity<FunctionalAbilityDTO>> createFunctionalAbility(@RequestBody FunctionalAbilityDTO functionalAbilityDTO)
        throws URISyntaxException {
        log.debug("REST request to save FunctionalAbility : {}", functionalAbilityDTO);
        if (functionalAbilityDTO.getFunctionalAbilityId() != null) {
            throw new BadRequestAlertException("A new functionalAbility cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return functionalAbilityService
            .save(functionalAbilityDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/functional-abilities/" + result.getFunctionalAbilityId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(
                                applicationName,
                                false,
                                ENTITY_NAME,
                                result.getFunctionalAbilityId().toString()
                            )
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /functional-abilities/:functionalAbilityId} : Updates an existing functionalAbility.
     *
     * @param functionalAbilityId the id of the functionalAbilityDTO to save.
     * @param functionalAbilityDTO the functionalAbilityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated functionalAbilityDTO,
     * or with status {@code 400 (Bad Request)} if the functionalAbilityDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the functionalAbilityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/functional-abilities/{functionalAbilityId}")
    public Mono<ResponseEntity<FunctionalAbilityDTO>> updateFunctionalAbility(
        @PathVariable(value = "functionalAbilityId", required = false) final Long functionalAbilityId,
        @RequestBody FunctionalAbilityDTO functionalAbilityDTO
    ) throws URISyntaxException {
        log.debug("REST request to update FunctionalAbility : {}, {}", functionalAbilityId, functionalAbilityDTO);
        if (functionalAbilityDTO.getFunctionalAbilityId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(functionalAbilityId, functionalAbilityDTO.getFunctionalAbilityId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return functionalAbilityRepository
            .existsById(functionalAbilityId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return functionalAbilityService
                    .update(functionalAbilityDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getFunctionalAbilityId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /functional-abilities/:functionalAbilityId} : Partial updates given fields of an existing functionalAbility, field will ignore if it is null
     *
     * @param functionalAbilityId the id of the functionalAbilityDTO to save.
     * @param functionalAbilityDTO the functionalAbilityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated functionalAbilityDTO,
     * or with status {@code 400 (Bad Request)} if the functionalAbilityDTO is not valid,
     * or with status {@code 404 (Not Found)} if the functionalAbilityDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the functionalAbilityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/functional-abilities/{functionalAbilityId}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<FunctionalAbilityDTO>> partialUpdateFunctionalAbility(
        @PathVariable(value = "functionalAbilityId", required = false) final Long functionalAbilityId,
        @RequestBody FunctionalAbilityDTO functionalAbilityDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update FunctionalAbility partially : {}, {}", functionalAbilityId, functionalAbilityDTO);
        if (functionalAbilityDTO.getFunctionalAbilityId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(functionalAbilityId, functionalAbilityDTO.getFunctionalAbilityId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return functionalAbilityRepository
            .existsById(functionalAbilityId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<FunctionalAbilityDTO> result = functionalAbilityService.partialUpdate(functionalAbilityDTO);

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
                                    res.getFunctionalAbilityId().toString()
                                )
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /functional-abilities} : get all the functionalAbilities.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of functionalAbilities in body.
     */
    @GetMapping("/functional-abilities")
    public Mono<ResponseEntity<List<FunctionalAbilityDTO>>> getAllFunctionalAbilities(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of FunctionalAbilities");
        return functionalAbilityService
            .countAll()
            .zipWith(functionalAbilityService.findAll(pageable).collectList())
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
     * {@code GET  /functional-abilities/:id} : get the "id" functionalAbility.
     *
     * @param id the id of the functionalAbilityDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the functionalAbilityDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/functional-abilities/{id}")
    public Mono<ResponseEntity<FunctionalAbilityDTO>> getFunctionalAbility(@PathVariable Long id) {
        log.debug("REST request to get FunctionalAbility : {}", id);
        Mono<FunctionalAbilityDTO> functionalAbilityDTO = functionalAbilityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(functionalAbilityDTO);
    }

    /**
     * {@code DELETE  /functional-abilities/:id} : delete the "id" functionalAbility.
     *
     * @param id the id of the functionalAbilityDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/functional-abilities/{id}")
    public Mono<ResponseEntity<Void>> deleteFunctionalAbility(@PathVariable Long id) {
        log.debug("REST request to delete FunctionalAbility : {}", id);
        return functionalAbilityService
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
