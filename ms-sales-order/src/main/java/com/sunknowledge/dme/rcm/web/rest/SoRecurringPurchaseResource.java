package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.SoRecurringPurchaseRepository;
import com.sunknowledge.dme.rcm.service.SoRecurringPurchaseService;
import com.sunknowledge.dme.rcm.service.dto.SoRecurringPurchaseDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.SoRecurringPurchase}.
 */
@RestController
@RequestMapping("/api")
public class SoRecurringPurchaseResource {

    private final Logger log = LoggerFactory.getLogger(SoRecurringPurchaseResource.class);

    private static final String ENTITY_NAME = "salesorderSoRecurringPurchase";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SoRecurringPurchaseService soRecurringPurchaseService;

    private final SoRecurringPurchaseRepository soRecurringPurchaseRepository;

    public SoRecurringPurchaseResource(
        SoRecurringPurchaseService soRecurringPurchaseService,
        SoRecurringPurchaseRepository soRecurringPurchaseRepository
    ) {
        this.soRecurringPurchaseService = soRecurringPurchaseService;
        this.soRecurringPurchaseRepository = soRecurringPurchaseRepository;
    }

    /**
     * {@code POST  /so-recurring-purchases} : Create a new soRecurringPurchase.
     *
     * @param soRecurringPurchaseDTO the soRecurringPurchaseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new soRecurringPurchaseDTO, or with status {@code 400 (Bad Request)} if the soRecurringPurchase has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/so-recurring-purchases")
    public Mono<ResponseEntity<SoRecurringPurchaseDTO>> createSoRecurringPurchase(
        @RequestBody SoRecurringPurchaseDTO soRecurringPurchaseDTO
    ) throws URISyntaxException {
        log.debug("REST request to save SoRecurringPurchase : {}", soRecurringPurchaseDTO);
        if (soRecurringPurchaseDTO.getRpId() != null) {
            throw new BadRequestAlertException("A new soRecurringPurchase cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return soRecurringPurchaseService
            .save(soRecurringPurchaseDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/so-recurring-purchases/" + result.getRpId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getRpId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /so-recurring-purchases/:rpId} : Updates an existing soRecurringPurchase.
     *
     * @param rpId the id of the soRecurringPurchaseDTO to save.
     * @param soRecurringPurchaseDTO the soRecurringPurchaseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated soRecurringPurchaseDTO,
     * or with status {@code 400 (Bad Request)} if the soRecurringPurchaseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the soRecurringPurchaseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/so-recurring-purchases/{rpId}")
    public Mono<ResponseEntity<SoRecurringPurchaseDTO>> updateSoRecurringPurchase(
        @PathVariable(value = "rpId", required = false) final Long rpId,
        @RequestBody SoRecurringPurchaseDTO soRecurringPurchaseDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SoRecurringPurchase : {}, {}", rpId, soRecurringPurchaseDTO);
        if (soRecurringPurchaseDTO.getRpId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(rpId, soRecurringPurchaseDTO.getRpId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return soRecurringPurchaseRepository
            .existsById(rpId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return soRecurringPurchaseService
                    .update(soRecurringPurchaseDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, result.getRpId().toString()))
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /so-recurring-purchases/:rpId} : Partial updates given fields of an existing soRecurringPurchase, field will ignore if it is null
     *
     * @param rpId the id of the soRecurringPurchaseDTO to save.
     * @param soRecurringPurchaseDTO the soRecurringPurchaseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated soRecurringPurchaseDTO,
     * or with status {@code 400 (Bad Request)} if the soRecurringPurchaseDTO is not valid,
     * or with status {@code 404 (Not Found)} if the soRecurringPurchaseDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the soRecurringPurchaseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/so-recurring-purchases/{rpId}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<SoRecurringPurchaseDTO>> partialUpdateSoRecurringPurchase(
        @PathVariable(value = "rpId", required = false) final Long rpId,
        @RequestBody SoRecurringPurchaseDTO soRecurringPurchaseDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update SoRecurringPurchase partially : {}, {}", rpId, soRecurringPurchaseDTO);
        if (soRecurringPurchaseDTO.getRpId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(rpId, soRecurringPurchaseDTO.getRpId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return soRecurringPurchaseRepository
            .existsById(rpId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<SoRecurringPurchaseDTO> result = soRecurringPurchaseService.partialUpdate(soRecurringPurchaseDTO);

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(res ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, res.getRpId().toString()))
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /so-recurring-purchases} : get all the soRecurringPurchases.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of soRecurringPurchases in body.
     */
    @GetMapping("/so-recurring-purchases")
    public Mono<ResponseEntity<List<SoRecurringPurchaseDTO>>> getAllSoRecurringPurchases(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of SoRecurringPurchases");
        return soRecurringPurchaseService
            .countAll()
            .zipWith(soRecurringPurchaseService.findAll(pageable).collectList())
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
     * {@code GET  /so-recurring-purchases/:id} : get the "id" soRecurringPurchase.
     *
     * @param id the id of the soRecurringPurchaseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the soRecurringPurchaseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/so-recurring-purchases/{id}")
    public Mono<ResponseEntity<SoRecurringPurchaseDTO>> getSoRecurringPurchase(@PathVariable Long id) {
        log.debug("REST request to get SoRecurringPurchase : {}", id);
        Mono<SoRecurringPurchaseDTO> soRecurringPurchaseDTO = soRecurringPurchaseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(soRecurringPurchaseDTO);
    }

    /**
     * {@code DELETE  /so-recurring-purchases/:id} : delete the "id" soRecurringPurchase.
     *
     * @param id the id of the soRecurringPurchaseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/so-recurring-purchases/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteSoRecurringPurchase(@PathVariable Long id) {
        log.debug("REST request to delete SoRecurringPurchase : {}", id);
        return soRecurringPurchaseService
            .delete(id)
            .map(result ->
                ResponseEntity
                    .noContent()
                    .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                    .build()
            );
    }
}
