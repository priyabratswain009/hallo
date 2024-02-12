package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.PickupExchangeRepository;
import com.sunknowledge.dme.rcm.service.PickupExchangeService;
import com.sunknowledge.dme.rcm.service.dto.PickupExchangeDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.PickupExchange}.
 */
@RestController
@RequestMapping("/api")
public class PickupExchangeResource {

    private final Logger log = LoggerFactory.getLogger(PickupExchangeResource.class);

    private static final String ENTITY_NAME = "salesorderPickupExchange";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PickupExchangeService pickupExchangeService;

    private final PickupExchangeRepository pickupExchangeRepository;

    public PickupExchangeResource(PickupExchangeService pickupExchangeService, PickupExchangeRepository pickupExchangeRepository) {
        this.pickupExchangeService = pickupExchangeService;
        this.pickupExchangeRepository = pickupExchangeRepository;
    }

    /**
     * {@code POST  /pickup-exchanges} : Create a new pickupExchange.
     *
     * @param pickupExchangeDTO the pickupExchangeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pickupExchangeDTO, or with status {@code 400 (Bad Request)} if the pickupExchange has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pickup-exchanges")
    public Mono<ResponseEntity<PickupExchangeDTO>> createPickupExchange(@Valid @RequestBody PickupExchangeDTO pickupExchangeDTO)
        throws URISyntaxException {
        log.debug("REST request to save PickupExchange : {}", pickupExchangeDTO);
        if (pickupExchangeDTO.getPickupExchangeId() != null) {
            throw new BadRequestAlertException("A new pickupExchange cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return pickupExchangeService
            .save(pickupExchangeDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/pickup-exchanges/" + result.getPickupExchangeId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(
                                applicationName,
                                false,
                                ENTITY_NAME,
                                result.getPickupExchangeId().toString()
                            )
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /pickup-exchanges/:pickupExchangeId} : Updates an existing pickupExchange.
     *
     * @param pickupExchangeId the id of the pickupExchangeDTO to save.
     * @param pickupExchangeDTO the pickupExchangeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pickupExchangeDTO,
     * or with status {@code 400 (Bad Request)} if the pickupExchangeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pickupExchangeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pickup-exchanges/{pickupExchangeId}")
    public Mono<ResponseEntity<PickupExchangeDTO>> updatePickupExchange(
        @PathVariable(value = "pickupExchangeId", required = false) final Long pickupExchangeId,
        @Valid @RequestBody PickupExchangeDTO pickupExchangeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PickupExchange : {}, {}", pickupExchangeId, pickupExchangeDTO);
        if (pickupExchangeDTO.getPickupExchangeId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(pickupExchangeId, pickupExchangeDTO.getPickupExchangeId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return pickupExchangeRepository
            .existsById(pickupExchangeId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return pickupExchangeService
                    .update(pickupExchangeDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getPickupExchangeId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /pickup-exchanges/:pickupExchangeId} : Partial updates given fields of an existing pickupExchange, field will ignore if it is null
     *
     * @param pickupExchangeId the id of the pickupExchangeDTO to save.
     * @param pickupExchangeDTO the pickupExchangeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pickupExchangeDTO,
     * or with status {@code 400 (Bad Request)} if the pickupExchangeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the pickupExchangeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the pickupExchangeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/pickup-exchanges/{pickupExchangeId}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<PickupExchangeDTO>> partialUpdatePickupExchange(
        @PathVariable(value = "pickupExchangeId", required = false) final Long pickupExchangeId,
        @NotNull @RequestBody PickupExchangeDTO pickupExchangeDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PickupExchange partially : {}, {}", pickupExchangeId, pickupExchangeDTO);
        if (pickupExchangeDTO.getPickupExchangeId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(pickupExchangeId, pickupExchangeDTO.getPickupExchangeId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return pickupExchangeRepository
            .existsById(pickupExchangeId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<PickupExchangeDTO> result = pickupExchangeService.partialUpdate(pickupExchangeDTO);

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
                                    res.getPickupExchangeId().toString()
                                )
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /pickup-exchanges} : get all the pickupExchanges.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pickupExchanges in body.
     */
    @GetMapping("/pickup-exchanges")
    public Mono<ResponseEntity<List<PickupExchangeDTO>>> getAllPickupExchanges(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of PickupExchanges");
        return pickupExchangeService
            .countAll()
            .zipWith(pickupExchangeService.findAll(pageable).collectList())
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
     * {@code GET  /pickup-exchanges/:id} : get the "id" pickupExchange.
     *
     * @param id the id of the pickupExchangeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pickupExchangeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pickup-exchanges/{id}")
    public Mono<ResponseEntity<PickupExchangeDTO>> getPickupExchange(@PathVariable Long id) {
        log.debug("REST request to get PickupExchange : {}", id);
        Mono<PickupExchangeDTO> pickupExchangeDTO = pickupExchangeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pickupExchangeDTO);
    }

    /**
     * {@code DELETE  /pickup-exchanges/:id} : delete the "id" pickupExchange.
     *
     * @param id the id of the pickupExchangeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pickup-exchanges/{id}")
    public Mono<ResponseEntity<Void>> deletePickupExchange(@PathVariable Long id) {
        log.debug("REST request to delete PickupExchange : {}", id);
        return pickupExchangeService
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
