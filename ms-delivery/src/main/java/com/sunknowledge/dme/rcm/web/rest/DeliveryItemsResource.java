package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.DeliveryItemsRepository;
import com.sunknowledge.dme.rcm.service.DeliveryItemsService;
import com.sunknowledge.dme.rcm.service.dto.DeliveryItemsDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.DeliveryItems}.
 */
@RestController
@RequestMapping("/api")
public class DeliveryItemsResource {

    private final Logger log = LoggerFactory.getLogger(DeliveryItemsResource.class);

    private static final String ENTITY_NAME = "deliveryDeliveryItems";

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
    public ResponseEntity<DeliveryItemsDTO> createDeliveryItems(@Valid @RequestBody DeliveryItemsDTO deliveryItemsDTO)
        throws URISyntaxException {
        log.debug("REST request to save DeliveryItems : {}", deliveryItemsDTO);
        if (deliveryItemsDTO.getDeliveryItemId() != null) {
            throw new BadRequestAlertException("A new deliveryItems cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DeliveryItemsDTO result = deliveryItemsService.save(deliveryItemsDTO);
        return ResponseEntity
            .created(new URI("/api/delivery-items/" + result.getDeliveryItemId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getDeliveryItemId().toString()))
            .body(result);
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
    public ResponseEntity<DeliveryItemsDTO> updateDeliveryItems(
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

        if (!deliveryItemsRepository.existsById(deliveryItemId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DeliveryItemsDTO result = deliveryItemsService.update(deliveryItemsDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, deliveryItemsDTO.getDeliveryItemId().toString())
            )
            .body(result);
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
    public ResponseEntity<DeliveryItemsDTO> partialUpdateDeliveryItems(
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

        if (!deliveryItemsRepository.existsById(deliveryItemId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DeliveryItemsDTO> result = deliveryItemsService.partialUpdate(deliveryItemsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, deliveryItemsDTO.getDeliveryItemId().toString())
        );
    }

    /**
     * {@code GET  /delivery-items} : get all the deliveryItems.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of deliveryItems in body.
     */
    @GetMapping("/delivery-items")
    public ResponseEntity<List<DeliveryItemsDTO>> getAllDeliveryItems(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of DeliveryItems");
        Page<DeliveryItemsDTO> page = deliveryItemsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /delivery-items/:id} : get the "id" deliveryItems.
     *
     * @param id the id of the deliveryItemsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the deliveryItemsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/delivery-items/{id}")
    public ResponseEntity<DeliveryItemsDTO> getDeliveryItems(@PathVariable Long id) {
        log.debug("REST request to get DeliveryItems : {}", id);
        Optional<DeliveryItemsDTO> deliveryItemsDTO = deliveryItemsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(deliveryItemsDTO);
    }

    /**
     * {@code DELETE  /delivery-items/:id} : delete the "id" deliveryItems.
     *
     * @param id the id of the deliveryItemsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/delivery-items/{id}")
    public ResponseEntity<Void> deleteDeliveryItems(@PathVariable Long id) {
        log.debug("REST request to delete DeliveryItems : {}", id);
        deliveryItemsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
