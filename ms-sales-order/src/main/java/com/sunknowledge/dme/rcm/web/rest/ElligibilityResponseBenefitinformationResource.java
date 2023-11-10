package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ElligibilityResponseBenefitinformationRepository;
import com.sunknowledge.dme.rcm.service.ElligibilityResponseBenefitinformationService;
import com.sunknowledge.dme.rcm.service.dto.ElligibilityResponseBenefitinformationDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ElligibilityResponseBenefitinformation}.
 */
@RestController
@RequestMapping("/api")
public class ElligibilityResponseBenefitinformationResource {

    private final Logger log = LoggerFactory.getLogger(ElligibilityResponseBenefitinformationResource.class);

    private static final String ENTITY_NAME = "salesorderElligibilityResponseBenefitinformation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ElligibilityResponseBenefitinformationService elligibilityResponseBenefitinformationService;

    private final ElligibilityResponseBenefitinformationRepository elligibilityResponseBenefitinformationRepository;

    public ElligibilityResponseBenefitinformationResource(
        ElligibilityResponseBenefitinformationService elligibilityResponseBenefitinformationService,
        ElligibilityResponseBenefitinformationRepository elligibilityResponseBenefitinformationRepository
    ) {
        this.elligibilityResponseBenefitinformationService = elligibilityResponseBenefitinformationService;
        this.elligibilityResponseBenefitinformationRepository = elligibilityResponseBenefitinformationRepository;
    }

    /**
     * {@code POST  /elligibility-response-benefitinformations} : Create a new elligibilityResponseBenefitinformation.
     *
     * @param elligibilityResponseBenefitinformationDTO the elligibilityResponseBenefitinformationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new elligibilityResponseBenefitinformationDTO, or with status {@code 400 (Bad Request)} if the elligibilityResponseBenefitinformation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/elligibility-response-benefitinformations")
    public Mono<ResponseEntity<ElligibilityResponseBenefitinformationDTO>> createElligibilityResponseBenefitinformation(
        @RequestBody ElligibilityResponseBenefitinformationDTO elligibilityResponseBenefitinformationDTO
    ) throws URISyntaxException {
        log.debug("REST request to save ElligibilityResponseBenefitinformation : {}", elligibilityResponseBenefitinformationDTO);
        if (elligibilityResponseBenefitinformationDTO.getElligibilityResponseBenefitinformationId() != null) {
            throw new BadRequestAlertException(
                "A new elligibilityResponseBenefitinformation cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        return elligibilityResponseBenefitinformationService
            .save(elligibilityResponseBenefitinformationDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(
                            new URI(
                                "/api/elligibility-response-benefitinformations/" + result.getElligibilityResponseBenefitinformationId()
                            )
                        )
                        .headers(
                            HeaderUtil.createEntityCreationAlert(
                                applicationName,
                                false,
                                ENTITY_NAME,
                                result.getElligibilityResponseBenefitinformationId().toString()
                            )
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /elligibility-response-benefitinformations/:elligibilityResponseBenefitinformationId} : Updates an existing elligibilityResponseBenefitinformation.
     *
     * @param elligibilityResponseBenefitinformationId the id of the elligibilityResponseBenefitinformationDTO to save.
     * @param elligibilityResponseBenefitinformationDTO the elligibilityResponseBenefitinformationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated elligibilityResponseBenefitinformationDTO,
     * or with status {@code 400 (Bad Request)} if the elligibilityResponseBenefitinformationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the elligibilityResponseBenefitinformationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/elligibility-response-benefitinformations/{elligibilityResponseBenefitinformationId}")
    public Mono<ResponseEntity<ElligibilityResponseBenefitinformationDTO>> updateElligibilityResponseBenefitinformation(
        @PathVariable(
            value = "elligibilityResponseBenefitinformationId",
            required = false
        ) final Long elligibilityResponseBenefitinformationId,
        @RequestBody ElligibilityResponseBenefitinformationDTO elligibilityResponseBenefitinformationDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to update ElligibilityResponseBenefitinformation : {}, {}",
            elligibilityResponseBenefitinformationId,
            elligibilityResponseBenefitinformationDTO
        );
        if (elligibilityResponseBenefitinformationDTO.getElligibilityResponseBenefitinformationId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (
            !Objects.equals(
                elligibilityResponseBenefitinformationId,
                elligibilityResponseBenefitinformationDTO.getElligibilityResponseBenefitinformationId()
            )
        ) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return elligibilityResponseBenefitinformationRepository
            .existsById(elligibilityResponseBenefitinformationId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return elligibilityResponseBenefitinformationService
                    .update(elligibilityResponseBenefitinformationDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getElligibilityResponseBenefitinformationId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /elligibility-response-benefitinformations/:elligibilityResponseBenefitinformationId} : Partial updates given fields of an existing elligibilityResponseBenefitinformation, field will ignore if it is null
     *
     * @param elligibilityResponseBenefitinformationId the id of the elligibilityResponseBenefitinformationDTO to save.
     * @param elligibilityResponseBenefitinformationDTO the elligibilityResponseBenefitinformationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated elligibilityResponseBenefitinformationDTO,
     * or with status {@code 400 (Bad Request)} if the elligibilityResponseBenefitinformationDTO is not valid,
     * or with status {@code 404 (Not Found)} if the elligibilityResponseBenefitinformationDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the elligibilityResponseBenefitinformationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/elligibility-response-benefitinformations/{elligibilityResponseBenefitinformationId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public Mono<ResponseEntity<ElligibilityResponseBenefitinformationDTO>> partialUpdateElligibilityResponseBenefitinformation(
        @PathVariable(
            value = "elligibilityResponseBenefitinformationId",
            required = false
        ) final Long elligibilityResponseBenefitinformationId,
        @RequestBody ElligibilityResponseBenefitinformationDTO elligibilityResponseBenefitinformationDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update ElligibilityResponseBenefitinformation partially : {}, {}",
            elligibilityResponseBenefitinformationId,
            elligibilityResponseBenefitinformationDTO
        );
        if (elligibilityResponseBenefitinformationDTO.getElligibilityResponseBenefitinformationId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (
            !Objects.equals(
                elligibilityResponseBenefitinformationId,
                elligibilityResponseBenefitinformationDTO.getElligibilityResponseBenefitinformationId()
            )
        ) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return elligibilityResponseBenefitinformationRepository
            .existsById(elligibilityResponseBenefitinformationId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<ElligibilityResponseBenefitinformationDTO> result = elligibilityResponseBenefitinformationService.partialUpdate(
                    elligibilityResponseBenefitinformationDTO
                );

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
                                    res.getElligibilityResponseBenefitinformationId().toString()
                                )
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /elligibility-response-benefitinformations} : get all the elligibilityResponseBenefitinformations.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of elligibilityResponseBenefitinformations in body.
     */
    @GetMapping("/elligibility-response-benefitinformations")
    public Mono<ResponseEntity<List<ElligibilityResponseBenefitinformationDTO>>> getAllElligibilityResponseBenefitinformations(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of ElligibilityResponseBenefitinformations");
        return elligibilityResponseBenefitinformationService
            .countAll()
            .zipWith(elligibilityResponseBenefitinformationService.findAll(pageable).collectList())
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
     * {@code GET  /elligibility-response-benefitinformations/:id} : get the "id" elligibilityResponseBenefitinformation.
     *
     * @param id the id of the elligibilityResponseBenefitinformationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the elligibilityResponseBenefitinformationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/elligibility-response-benefitinformations/{id}")
    public Mono<ResponseEntity<ElligibilityResponseBenefitinformationDTO>> getElligibilityResponseBenefitinformation(
        @PathVariable Long id
    ) {
        log.debug("REST request to get ElligibilityResponseBenefitinformation : {}", id);
        Mono<ElligibilityResponseBenefitinformationDTO> elligibilityResponseBenefitinformationDTO = elligibilityResponseBenefitinformationService.findOne(
            id
        );
        return ResponseUtil.wrapOrNotFound(elligibilityResponseBenefitinformationDTO);
    }

    /**
     * {@code DELETE  /elligibility-response-benefitinformations/:id} : delete the "id" elligibilityResponseBenefitinformation.
     *
     * @param id the id of the elligibilityResponseBenefitinformationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/elligibility-response-benefitinformations/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteElligibilityResponseBenefitinformation(@PathVariable Long id) {
        log.debug("REST request to delete ElligibilityResponseBenefitinformation : {}", id);
        return elligibilityResponseBenefitinformationService
            .delete(id)
            .map(result ->
                ResponseEntity
                    .noContent()
                    .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                    .build()
            );
    }
}
