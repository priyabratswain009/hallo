package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.DeliveryAbnDataRepository;
import com.sunknowledge.dme.rcm.service.DeliveryAbnDataService;
import com.sunknowledge.dme.rcm.service.dto.DeliveryAbnDataDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.DeliveryAbnData}.
 */
@RestController
@RequestMapping("/api")
public class DeliveryAbnDataResource {

    private final Logger log = LoggerFactory.getLogger(DeliveryAbnDataResource.class);

    private static final String ENTITY_NAME = "salesorderDeliveryAbnData";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DeliveryAbnDataService deliveryAbnDataService;

    private final DeliveryAbnDataRepository deliveryAbnDataRepository;

    public DeliveryAbnDataResource(DeliveryAbnDataService deliveryAbnDataService, DeliveryAbnDataRepository deliveryAbnDataRepository) {
        this.deliveryAbnDataService = deliveryAbnDataService;
        this.deliveryAbnDataRepository = deliveryAbnDataRepository;
    }

    /**
     * {@code POST  /delivery-abn-data} : Create a new deliveryAbnData.
     *
     * @param deliveryAbnDataDTO the deliveryAbnDataDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new deliveryAbnDataDTO, or with status {@code 400 (Bad Request)} if the deliveryAbnData has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/delivery-abn-data")
    public Mono<ResponseEntity<DeliveryAbnDataDTO>> createDeliveryAbnData(@RequestBody DeliveryAbnDataDTO deliveryAbnDataDTO)
        throws URISyntaxException {
        log.debug("REST request to save DeliveryAbnData : {}", deliveryAbnDataDTO);
        if (deliveryAbnDataDTO.getDeliveryAbnDataId() != null) {
            throw new BadRequestAlertException("A new deliveryAbnData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return deliveryAbnDataService
            .save(deliveryAbnDataDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/delivery-abn-data/" + result.getDeliveryAbnDataId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(
                                applicationName,
                                false,
                                ENTITY_NAME,
                                result.getDeliveryAbnDataId().toString()
                            )
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /delivery-abn-data/:deliveryAbnDataId} : Updates an existing deliveryAbnData.
     *
     * @param deliveryAbnDataId the id of the deliveryAbnDataDTO to save.
     * @param deliveryAbnDataDTO the deliveryAbnDataDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deliveryAbnDataDTO,
     * or with status {@code 400 (Bad Request)} if the deliveryAbnDataDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the deliveryAbnDataDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/delivery-abn-data/{deliveryAbnDataId}")
    public Mono<ResponseEntity<DeliveryAbnDataDTO>> updateDeliveryAbnData(
        @PathVariable(value = "deliveryAbnDataId", required = false) final Long deliveryAbnDataId,
        @RequestBody DeliveryAbnDataDTO deliveryAbnDataDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DeliveryAbnData : {}, {}", deliveryAbnDataId, deliveryAbnDataDTO);
        if (deliveryAbnDataDTO.getDeliveryAbnDataId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(deliveryAbnDataId, deliveryAbnDataDTO.getDeliveryAbnDataId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return deliveryAbnDataRepository
            .existsById(deliveryAbnDataId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return deliveryAbnDataService
                    .update(deliveryAbnDataDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getDeliveryAbnDataId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /delivery-abn-data/:deliveryAbnDataId} : Partial updates given fields of an existing deliveryAbnData, field will ignore if it is null
     *
     * @param deliveryAbnDataId the id of the deliveryAbnDataDTO to save.
     * @param deliveryAbnDataDTO the deliveryAbnDataDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deliveryAbnDataDTO,
     * or with status {@code 400 (Bad Request)} if the deliveryAbnDataDTO is not valid,
     * or with status {@code 404 (Not Found)} if the deliveryAbnDataDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the deliveryAbnDataDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/delivery-abn-data/{deliveryAbnDataId}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<DeliveryAbnDataDTO>> partialUpdateDeliveryAbnData(
        @PathVariable(value = "deliveryAbnDataId", required = false) final Long deliveryAbnDataId,
        @RequestBody DeliveryAbnDataDTO deliveryAbnDataDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DeliveryAbnData partially : {}, {}", deliveryAbnDataId, deliveryAbnDataDTO);
        if (deliveryAbnDataDTO.getDeliveryAbnDataId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(deliveryAbnDataId, deliveryAbnDataDTO.getDeliveryAbnDataId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return deliveryAbnDataRepository
            .existsById(deliveryAbnDataId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<DeliveryAbnDataDTO> result = deliveryAbnDataService.partialUpdate(deliveryAbnDataDTO);

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
                                    res.getDeliveryAbnDataId().toString()
                                )
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /delivery-abn-data} : get all the deliveryAbnData.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of deliveryAbnData in body.
     */
    @GetMapping("/delivery-abn-data")
    public Mono<ResponseEntity<List<DeliveryAbnDataDTO>>> getAllDeliveryAbnData(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of DeliveryAbnData");
        return deliveryAbnDataService
            .countAll()
            .zipWith(deliveryAbnDataService.findAll(pageable).collectList())
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
     * {@code GET  /delivery-abn-data/:id} : get the "id" deliveryAbnData.
     *
     * @param id the id of the deliveryAbnDataDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the deliveryAbnDataDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/delivery-abn-data/{id}")
    public Mono<ResponseEntity<DeliveryAbnDataDTO>> getDeliveryAbnData(@PathVariable Long id) {
        log.debug("REST request to get DeliveryAbnData : {}", id);
        Mono<DeliveryAbnDataDTO> deliveryAbnDataDTO = deliveryAbnDataService.findOne(id);
        return ResponseUtil.wrapOrNotFound(deliveryAbnDataDTO);
    }

    /**
     * {@code DELETE  /delivery-abn-data/:id} : delete the "id" deliveryAbnData.
     *
     * @param id the id of the deliveryAbnDataDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/delivery-abn-data/{id}")
    public Mono<ResponseEntity<Void>> deleteDeliveryAbnData(@PathVariable Long id) {
        log.debug("REST request to delete DeliveryAbnData : {}", id);
        return deliveryAbnDataService
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
