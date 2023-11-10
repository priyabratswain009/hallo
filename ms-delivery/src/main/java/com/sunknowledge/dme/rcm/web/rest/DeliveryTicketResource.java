package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.DeliveryTicketRepository;
import com.sunknowledge.dme.rcm.service.DeliveryTicketService;
import com.sunknowledge.dme.rcm.service.dto.DeliveryTicketDTO;
import com.sunknowledge.dme.rcm.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.DeliveryTicket}.
 */
@RestController
@RequestMapping("/api")
public class DeliveryTicketResource {

    private final Logger log = LoggerFactory.getLogger(DeliveryTicketResource.class);

    private static final String ENTITY_NAME = "deliveryDeliveryTicket";

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
    public ResponseEntity<DeliveryTicketDTO> createDeliveryTicket(@Valid @RequestBody DeliveryTicketDTO deliveryTicketDTO)
        throws URISyntaxException {
        log.debug("REST request to save DeliveryTicket : {}", deliveryTicketDTO);
        if (deliveryTicketDTO.getDeliveryTicketId() != null) {
            throw new BadRequestAlertException("A new deliveryTicket cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DeliveryTicketDTO result = deliveryTicketService.save(deliveryTicketDTO);
        return ResponseEntity
            .created(new URI("/api/delivery-tickets/" + result.getDeliveryTicketId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getDeliveryTicketId().toString()))
            .body(result);
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
    public ResponseEntity<DeliveryTicketDTO> updateDeliveryTicket(
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

        if (!deliveryTicketRepository.existsById(deliveryTicketId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DeliveryTicketDTO result = deliveryTicketService.update(deliveryTicketDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, deliveryTicketDTO.getDeliveryTicketId().toString())
            )
            .body(result);
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
    public ResponseEntity<DeliveryTicketDTO> partialUpdateDeliveryTicket(
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

        if (!deliveryTicketRepository.existsById(deliveryTicketId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DeliveryTicketDTO> result = deliveryTicketService.partialUpdate(deliveryTicketDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, deliveryTicketDTO.getDeliveryTicketId().toString())
        );
    }

    /**
     * {@code GET  /delivery-tickets} : get all the deliveryTickets.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of deliveryTickets in body.
     */
    @GetMapping("/delivery-tickets")
    public ResponseEntity<List<DeliveryTicketDTO>> getAllDeliveryTickets(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of DeliveryTickets");
        Page<DeliveryTicketDTO> page = deliveryTicketService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /delivery-tickets/:id} : get the "id" deliveryTicket.
     *
     * @param id the id of the deliveryTicketDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the deliveryTicketDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/delivery-tickets/{id}")
    public ResponseEntity<DeliveryTicketDTO> getDeliveryTicket(@PathVariable Long id) {
        log.debug("REST request to get DeliveryTicket : {}", id);
        Optional<DeliveryTicketDTO> deliveryTicketDTO = deliveryTicketService.findOne(id);
        return ResponseUtil.wrapOrNotFound(deliveryTicketDTO);
    }

    /**
     * {@code DELETE  /delivery-tickets/:id} : delete the "id" deliveryTicket.
     *
     * @param id the id of the deliveryTicketDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/delivery-tickets/{id}")
    public ResponseEntity<Void> deleteDeliveryTicket(@PathVariable Long id) {
        log.debug("REST request to delete DeliveryTicket : {}", id);
        deliveryTicketService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
