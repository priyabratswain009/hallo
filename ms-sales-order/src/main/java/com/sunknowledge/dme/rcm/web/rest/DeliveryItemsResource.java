package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.DeliveryItemsRepository;
import com.sunknowledge.dme.rcm.service.DeliveryItemsService;
import com.sunknowledge.dme.rcm.service.dto.DeliveryItemsDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.DeliveryItems}.
 */
@RestController
@RequestMapping("/api")
public class DeliveryItemsResource {

    private final Logger log = LoggerFactory.getLogger(DeliveryItemsResource.class);

    private static final String ENTITY_NAME = "salesorderDeliveryItems";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DeliveryItemsService deliveryItemsService;

    private final DeliveryItemsRepository deliveryItemsRepository;

    public DeliveryItemsResource(DeliveryItemsService deliveryItemsService, DeliveryItemsRepository deliveryItemsRepository) {
        this.deliveryItemsService = deliveryItemsService;
        this.deliveryItemsRepository = deliveryItemsRepository;
    }

    /**
     * {@code POST  /delivery-items} : Create a new deliveryItems.
     *
     * @param deliveryItemsDTO the deliveryItemsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new deliveryItemsDTO, or with status {@code 400 (Bad Request)} if the deliveryItems has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/delivery-items")
    public Mono<ResponseEntity<DeliveryItemsDTO>> createDeliveryItems(@Valid @RequestBody DeliveryItemsDTO deliveryItemsDTO)
        throws URISyntaxException {
        log.debug("REST request to save DeliveryItems : {}", deliveryItemsDTO);
        if (deliveryItemsDTO.getDeliveryItemId() != null) {
            throw new BadRequestAlertException("A new deliveryItems cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return deliveryItemsService
            .save(deliveryItemsDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/delivery-items/" + result.getDeliveryItemId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getDeliveryItemId().toString())
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /delivery-items/:deliveryItemId} : Updates an existing deliveryItems.
     *
     * @param deliveryItemId the id of the deliveryItemsDTO to save.
     * @param deliveryItemsDTO the deliveryItemsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deliveryItemsDTO,
     * or with status {@code 400 (Bad Request)} if the deliveryItemsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the deliveryItemsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/delivery-items/{deliveryItemId}")
    public Mono<ResponseEntity<DeliveryItemsDTO>> updateDeliveryItems(
        @PathVariable(value = "deliveryItemId", required = false) final Long deliveryItemId,
        @Valid @RequestBody DeliveryItemsDTO deliveryItemsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DeliveryItems : {}, {}", deliveryItemId, deliveryItemsDTO);
        if (deliveryItemsDTO.getDeliveryItemId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(deliveryItemId, deliveryItemsDTO.getDeliveryItemId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return deliveryItemsRepository
            .existsById(deliveryItemId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return deliveryItemsService
                    .update(deliveryItemsDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getDeliveryItemId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /delivery-items/:deliveryItemId} : Partial updates given fields of an existing deliveryItems, field will ignore if it is null
     *
     * @param deliveryItemId the id of the deliveryItemsDTO to save.
     * @param deliveryItemsDTO the deliveryItemsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deliveryItemsDTO,
     * or with status {@code 400 (Bad Request)} if the deliveryItemsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the deliveryItemsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the deliveryItemsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/delivery-items/{deliveryItemId}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<DeliveryItemsDTO>> partialUpdateDeliveryItems(
        @PathVariable(value = "deliveryItemId", required = false) final Long deliveryItemId,
        @NotNull @RequestBody DeliveryItemsDTO deliveryItemsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DeliveryItems partially : {}, {}", deliveryItemId, deliveryItemsDTO);
        if (deliveryItemsDTO.getDeliveryItemId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(deliveryItemId, deliveryItemsDTO.getDeliveryItemId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return deliveryItemsRepository
            .existsById(deliveryItemId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<DeliveryItemsDTO> result = deliveryItemsService.partialUpdate(deliveryItemsDTO);

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(res ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, res.getDeliveryItemId().toString())
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /delivery-items} : get all the deliveryItems.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of deliveryItems in body.
     */
    @GetMapping("/delivery-items")
    public Mono<ResponseEntity<List<DeliveryItemsDTO>>> getAllDeliveryItems(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of DeliveryItems");
        return deliveryItemsService
            .countAll()
            .zipWith(deliveryItemsService.findAll(pageable).collectList())
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
     * {@code GET  /delivery-items/:id} : get the "id" deliveryItems.
     *
     * @param id the id of the deliveryItemsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the deliveryItemsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/delivery-items/{id}")
    public Mono<ResponseEntity<DeliveryItemsDTO>> getDeliveryItems(@PathVariable Long id) {
        log.debug("REST request to get DeliveryItems : {}", id);
        Mono<DeliveryItemsDTO> deliveryItemsDTO = deliveryItemsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(deliveryItemsDTO);
    }

    /**
     * {@code DELETE  /delivery-items/:id} : delete the "id" deliveryItems.
     *
     * @param id the id of the deliveryItemsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/delivery-items/{id}")
    public Mono<ResponseEntity<Void>> deleteDeliveryItems(@PathVariable Long id) {
        log.debug("REST request to delete DeliveryItems : {}", id);
        return deliveryItemsService
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
