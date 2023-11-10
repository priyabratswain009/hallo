package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.DeliveryTicketRepository;
import com.sunknowledge.dme.rcm.service.DeliveryTicketService;
import com.sunknowledge.dme.rcm.service.dto.DeliveryTicketDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.DeliveryTicket}.
 */
@RestController
@RequestMapping("/api")
public class DeliveryTicketResource {

    private final Logger log = LoggerFactory.getLogger(DeliveryTicketResource.class);

    private static final String ENTITY_NAME = "salesorderDeliveryTicket";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DeliveryTicketService deliveryTicketService;

    private final DeliveryTicketRepository deliveryTicketRepository;

    public DeliveryTicketResource(DeliveryTicketService deliveryTicketService, DeliveryTicketRepository deliveryTicketRepository) {
        this.deliveryTicketService = deliveryTicketService;
        this.deliveryTicketRepository = deliveryTicketRepository;
    }

    /**
     * {@code POST  /delivery-tickets} : Create a new deliveryTicket.
     *
     * @param deliveryTicketDTO the deliveryTicketDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new deliveryTicketDTO, or with status {@code 400 (Bad Request)} if the deliveryTicket has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/delivery-tickets")
    public Mono<ResponseEntity<DeliveryTicketDTO>> createDeliveryTicket(@Valid @RequestBody DeliveryTicketDTO deliveryTicketDTO)
        throws URISyntaxException {
        log.debug("REST request to save DeliveryTicket : {}", deliveryTicketDTO);
        if (deliveryTicketDTO.getDeliveryTicketId() != null) {
            throw new BadRequestAlertException("A new deliveryTicket cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return deliveryTicketService
            .save(deliveryTicketDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/delivery-tickets/" + result.getDeliveryTicketId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(
                                applicationName,
                                false,
                                ENTITY_NAME,
                                result.getDeliveryTicketId().toString()
                            )
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /delivery-tickets/:deliveryTicketId} : Updates an existing deliveryTicket.
     *
     * @param deliveryTicketId the id of the deliveryTicketDTO to save.
     * @param deliveryTicketDTO the deliveryTicketDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deliveryTicketDTO,
     * or with status {@code 400 (Bad Request)} if the deliveryTicketDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the deliveryTicketDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/delivery-tickets/{deliveryTicketId}")
    public Mono<ResponseEntity<DeliveryTicketDTO>> updateDeliveryTicket(
        @PathVariable(value = "deliveryTicketId", required = false) final Long deliveryTicketId,
        @Valid @RequestBody DeliveryTicketDTO deliveryTicketDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DeliveryTicket : {}, {}", deliveryTicketId, deliveryTicketDTO);
        if (deliveryTicketDTO.getDeliveryTicketId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(deliveryTicketId, deliveryTicketDTO.getDeliveryTicketId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return deliveryTicketRepository
            .existsById(deliveryTicketId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return deliveryTicketService
                    .update(deliveryTicketDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getDeliveryTicketId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /delivery-tickets/:deliveryTicketId} : Partial updates given fields of an existing deliveryTicket, field will ignore if it is null
     *
     * @param deliveryTicketId the id of the deliveryTicketDTO to save.
     * @param deliveryTicketDTO the deliveryTicketDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deliveryTicketDTO,
     * or with status {@code 400 (Bad Request)} if the deliveryTicketDTO is not valid,
     * or with status {@code 404 (Not Found)} if the deliveryTicketDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the deliveryTicketDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/delivery-tickets/{deliveryTicketId}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<DeliveryTicketDTO>> partialUpdateDeliveryTicket(
        @PathVariable(value = "deliveryTicketId", required = false) final Long deliveryTicketId,
        @NotNull @RequestBody DeliveryTicketDTO deliveryTicketDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DeliveryTicket partially : {}, {}", deliveryTicketId, deliveryTicketDTO);
        if (deliveryTicketDTO.getDeliveryTicketId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(deliveryTicketId, deliveryTicketDTO.getDeliveryTicketId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return deliveryTicketRepository
            .existsById(deliveryTicketId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<DeliveryTicketDTO> result = deliveryTicketService.partialUpdate(deliveryTicketDTO);

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
                                    res.getDeliveryTicketId().toString()
                                )
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /delivery-tickets} : get all the deliveryTickets.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of deliveryTickets in body.
     */
    @GetMapping("/delivery-tickets")
    public Mono<ResponseEntity<List<DeliveryTicketDTO>>> getAllDeliveryTickets(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of DeliveryTickets");
        return deliveryTicketService
            .countAll()
            .zipWith(deliveryTicketService.findAll(pageable).collectList())
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
     * {@code GET  /delivery-tickets/:id} : get the "id" deliveryTicket.
     *
     * @param id the id of the deliveryTicketDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the deliveryTicketDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/delivery-tickets/{id}")
    public Mono<ResponseEntity<DeliveryTicketDTO>> getDeliveryTicket(@PathVariable Long id) {
        log.debug("REST request to get DeliveryTicket : {}", id);
        Mono<DeliveryTicketDTO> deliveryTicketDTO = deliveryTicketService.findOne(id);
        return ResponseUtil.wrapOrNotFound(deliveryTicketDTO);
    }

    /**
     * {@code DELETE  /delivery-tickets/:id} : delete the "id" deliveryTicket.
     *
     * @param id the id of the deliveryTicketDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/delivery-tickets/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteDeliveryTicket(@PathVariable Long id) {
        log.debug("REST request to delete DeliveryTicket : {}", id);
        return deliveryTicketService
            .delete(id)
            .map(result ->
                ResponseEntity
                    .noContent()
                    .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                    .build()
            );
    }
}
