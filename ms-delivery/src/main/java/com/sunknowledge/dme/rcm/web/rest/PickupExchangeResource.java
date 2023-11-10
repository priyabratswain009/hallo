package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.PickupExchangeRepository;
import com.sunknowledge.dme.rcm.service.PickupExchangeService;
import com.sunknowledge.dme.rcm.service.dto.PickupExchangeDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.PickupExchange}.
 */
@RestController
@RequestMapping("/api")
public class PickupExchangeResource {

    private final Logger log = LoggerFactory.getLogger(PickupExchangeResource.class);

    private static final String ENTITY_NAME = "deliveryPickupExchange";

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
    public ResponseEntity<PickupExchangeDTO> createPickupExchange(@Valid @RequestBody PickupExchangeDTO pickupExchangeDTO)
        throws URISyntaxException {
        log.debug("REST request to save PickupExchange : {}", pickupExchangeDTO);
        if (pickupExchangeDTO.getPickupExchangeId() != null) {
            throw new BadRequestAlertException("A new pickupExchange cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PickupExchangeDTO result = pickupExchangeService.save(pickupExchangeDTO);
        return ResponseEntity
            .created(new URI("/api/pickup-exchanges/" + result.getPickupExchangeId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getPickupExchangeId().toString()))
            .body(result);
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
    public ResponseEntity<PickupExchangeDTO> updatePickupExchange(
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

        if (!pickupExchangeRepository.existsById(pickupExchangeId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PickupExchangeDTO result = pickupExchangeService.update(pickupExchangeDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, pickupExchangeDTO.getPickupExchangeId().toString())
            )
            .body(result);
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
    public ResponseEntity<PickupExchangeDTO> partialUpdatePickupExchange(
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

        if (!pickupExchangeRepository.existsById(pickupExchangeId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PickupExchangeDTO> result = pickupExchangeService.partialUpdate(pickupExchangeDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, pickupExchangeDTO.getPickupExchangeId().toString())
        );
    }

    /**
     * {@code GET  /pickup-exchanges} : get all the pickupExchanges.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pickupExchanges in body.
     */
    @GetMapping("/pickup-exchanges")
    public ResponseEntity<List<PickupExchangeDTO>> getAllPickupExchanges(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of PickupExchanges");
        Page<PickupExchangeDTO> page = pickupExchangeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /pickup-exchanges/:id} : get the "id" pickupExchange.
     *
     * @param id the id of the pickupExchangeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pickupExchangeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pickup-exchanges/{id}")
    public ResponseEntity<PickupExchangeDTO> getPickupExchange(@PathVariable Long id) {
        log.debug("REST request to get PickupExchange : {}", id);
        Optional<PickupExchangeDTO> pickupExchangeDTO = pickupExchangeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pickupExchangeDTO);
    }

    /**
     * {@code DELETE  /pickup-exchanges/:id} : delete the "id" pickupExchange.
     *
     * @param id the id of the pickupExchangeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pickup-exchanges/{id}")
    public ResponseEntity<Void> deletePickupExchange(@PathVariable Long id) {
        log.debug("REST request to delete PickupExchange : {}", id);
        pickupExchangeService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
