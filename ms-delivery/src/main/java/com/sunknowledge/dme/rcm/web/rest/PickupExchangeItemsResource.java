package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.PickupExchangeItemsRepository;
import com.sunknowledge.dme.rcm.service.PickupExchangeItemsService;
import com.sunknowledge.dme.rcm.service.dto.PickupExchangeItemsDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.PickupExchangeItems}.
 */
@RestController
@RequestMapping("/api")
public class PickupExchangeItemsResource {

    private final Logger log = LoggerFactory.getLogger(PickupExchangeItemsResource.class);

    private static final String ENTITY_NAME = "deliveryPickupExchangeItems";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PickupExchangeItemsService pickupExchangeItemsService;

    private final PickupExchangeItemsRepository pickupExchangeItemsRepository;

    public PickupExchangeItemsResource(
        PickupExchangeItemsService pickupExchangeItemsService,
        PickupExchangeItemsRepository pickupExchangeItemsRepository
    ) {
        this.pickupExchangeItemsService = pickupExchangeItemsService;
        this.pickupExchangeItemsRepository = pickupExchangeItemsRepository;
    }

    /**
     * {@code POST  /pickup-exchange-items} : Create a new pickupExchangeItems.
     *
     * @param pickupExchangeItemsDTO the pickupExchangeItemsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pickupExchangeItemsDTO, or with status {@code 400 (Bad Request)} if the pickupExchangeItems has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pickup-exchange-items")
    public ResponseEntity<PickupExchangeItemsDTO> createPickupExchangeItems(
        @Valid @RequestBody PickupExchangeItemsDTO pickupExchangeItemsDTO
    ) throws URISyntaxException {
        log.debug("REST request to save PickupExchangeItems : {}", pickupExchangeItemsDTO);
        if (pickupExchangeItemsDTO.getPickupExchangeItemId() != null) {
            throw new BadRequestAlertException("A new pickupExchangeItems cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PickupExchangeItemsDTO result = pickupExchangeItemsService.save(pickupExchangeItemsDTO);
        return ResponseEntity
            .created(new URI("/api/pickup-exchange-items/" + result.getPickupExchangeItemId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getPickupExchangeItemId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pickup-exchange-items/:pickupExchangeItemId} : Updates an existing pickupExchangeItems.
     *
     * @param pickupExchangeItemId the id of the pickupExchangeItemsDTO to save.
     * @param pickupExchangeItemsDTO the pickupExchangeItemsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pickupExchangeItemsDTO,
     * or with status {@code 400 (Bad Request)} if the pickupExchangeItemsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pickupExchangeItemsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pickup-exchange-items/{pickupExchangeItemId}")
    public ResponseEntity<PickupExchangeItemsDTO> updatePickupExchangeItems(
        @PathVariable(value = "pickupExchangeItemId", required = false) final Long pickupExchangeItemId,
        @Valid @RequestBody PickupExchangeItemsDTO pickupExchangeItemsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PickupExchangeItems : {}, {}", pickupExchangeItemId, pickupExchangeItemsDTO);
        if (pickupExchangeItemsDTO.getPickupExchangeItemId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(pickupExchangeItemId, pickupExchangeItemsDTO.getPickupExchangeItemId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pickupExchangeItemsRepository.existsById(pickupExchangeItemId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PickupExchangeItemsDTO result = pickupExchangeItemsService.update(pickupExchangeItemsDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    pickupExchangeItemsDTO.getPickupExchangeItemId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /pickup-exchange-items/:pickupExchangeItemId} : Partial updates given fields of an existing pickupExchangeItems, field will ignore if it is null
     *
     * @param pickupExchangeItemId the id of the pickupExchangeItemsDTO to save.
     * @param pickupExchangeItemsDTO the pickupExchangeItemsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pickupExchangeItemsDTO,
     * or with status {@code 400 (Bad Request)} if the pickupExchangeItemsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the pickupExchangeItemsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the pickupExchangeItemsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/pickup-exchange-items/{pickupExchangeItemId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public ResponseEntity<PickupExchangeItemsDTO> partialUpdatePickupExchangeItems(
        @PathVariable(value = "pickupExchangeItemId", required = false) final Long pickupExchangeItemId,
        @NotNull @RequestBody PickupExchangeItemsDTO pickupExchangeItemsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PickupExchangeItems partially : {}, {}", pickupExchangeItemId, pickupExchangeItemsDTO);
        if (pickupExchangeItemsDTO.getPickupExchangeItemId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(pickupExchangeItemId, pickupExchangeItemsDTO.getPickupExchangeItemId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pickupExchangeItemsRepository.existsById(pickupExchangeItemId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PickupExchangeItemsDTO> result = pickupExchangeItemsService.partialUpdate(pickupExchangeItemsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                false,
                ENTITY_NAME,
                pickupExchangeItemsDTO.getPickupExchangeItemId().toString()
            )
        );
    }

    /**
     * {@code GET  /pickup-exchange-items} : get all the pickupExchangeItems.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pickupExchangeItems in body.
     */
    @GetMapping("/pickup-exchange-items")
    public ResponseEntity<List<PickupExchangeItemsDTO>> getAllPickupExchangeItems(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of PickupExchangeItems");
        Page<PickupExchangeItemsDTO> page = pickupExchangeItemsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /pickup-exchange-items/:id} : get the "id" pickupExchangeItems.
     *
     * @param id the id of the pickupExchangeItemsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pickupExchangeItemsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pickup-exchange-items/{id}")
    public ResponseEntity<PickupExchangeItemsDTO> getPickupExchangeItems(@PathVariable Long id) {
        log.debug("REST request to get PickupExchangeItems : {}", id);
        Optional<PickupExchangeItemsDTO> pickupExchangeItemsDTO = pickupExchangeItemsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pickupExchangeItemsDTO);
    }

    /**
     * {@code DELETE  /pickup-exchange-items/:id} : delete the "id" pickupExchangeItems.
     *
     * @param id the id of the pickupExchangeItemsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pickup-exchange-items/{id}")
    public ResponseEntity<Void> deletePickupExchangeItems(@PathVariable Long id) {
        log.debug("REST request to delete PickupExchangeItems : {}", id);
        pickupExchangeItemsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
