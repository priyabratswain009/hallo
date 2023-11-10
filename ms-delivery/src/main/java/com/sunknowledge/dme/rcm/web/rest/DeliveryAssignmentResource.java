package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.DeliveryAssignmentRepository;
import com.sunknowledge.dme.rcm.service.DeliveryAssignmentService;
import com.sunknowledge.dme.rcm.service.dto.DeliveryAssignmentDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.DeliveryAssignment}.
 */
@RestController
@RequestMapping("/api")
public class DeliveryAssignmentResource {

    private final Logger log = LoggerFactory.getLogger(DeliveryAssignmentResource.class);

    private static final String ENTITY_NAME = "deliveryDeliveryAssignment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DeliveryAssignmentService deliveryAssignmentService;

    private final DeliveryAssignmentRepository deliveryAssignmentRepository;

    public DeliveryAssignmentResource(
        DeliveryAssignmentService deliveryAssignmentService,
        DeliveryAssignmentRepository deliveryAssignmentRepository
    ) {
        this.deliveryAssignmentService = deliveryAssignmentService;
        this.deliveryAssignmentRepository = deliveryAssignmentRepository;
    }

    /**
     * {@code POST  /delivery-assignments} : Create a new deliveryAssignment.
     *
     * @param deliveryAssignmentDTO the deliveryAssignmentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new deliveryAssignmentDTO, or with status {@code 400 (Bad Request)} if the deliveryAssignment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/delivery-assignments")
    public ResponseEntity<DeliveryAssignmentDTO> createDeliveryAssignment(@Valid @RequestBody DeliveryAssignmentDTO deliveryAssignmentDTO)
        throws URISyntaxException {
        log.debug("REST request to save DeliveryAssignment : {}", deliveryAssignmentDTO);
        if (deliveryAssignmentDTO.getDeliveryAssignmentId() != null) {
            throw new BadRequestAlertException("A new deliveryAssignment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DeliveryAssignmentDTO result = deliveryAssignmentService.save(deliveryAssignmentDTO);
        return ResponseEntity
            .created(new URI("/api/delivery-assignments/" + result.getDeliveryAssignmentId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getDeliveryAssignmentId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /delivery-assignments/:deliveryAssignmentId} : Updates an existing deliveryAssignment.
     *
     * @param deliveryAssignmentId the id of the deliveryAssignmentDTO to save.
     * @param deliveryAssignmentDTO the deliveryAssignmentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deliveryAssignmentDTO,
     * or with status {@code 400 (Bad Request)} if the deliveryAssignmentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the deliveryAssignmentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/delivery-assignments/{deliveryAssignmentId}")
    public ResponseEntity<DeliveryAssignmentDTO> updateDeliveryAssignment(
        @PathVariable(value = "deliveryAssignmentId", required = false) final Long deliveryAssignmentId,
        @Valid @RequestBody DeliveryAssignmentDTO deliveryAssignmentDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DeliveryAssignment : {}, {}", deliveryAssignmentId, deliveryAssignmentDTO);
        if (deliveryAssignmentDTO.getDeliveryAssignmentId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(deliveryAssignmentId, deliveryAssignmentDTO.getDeliveryAssignmentId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!deliveryAssignmentRepository.existsById(deliveryAssignmentId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DeliveryAssignmentDTO result = deliveryAssignmentService.update(deliveryAssignmentDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    deliveryAssignmentDTO.getDeliveryAssignmentId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /delivery-assignments/:deliveryAssignmentId} : Partial updates given fields of an existing deliveryAssignment, field will ignore if it is null
     *
     * @param deliveryAssignmentId the id of the deliveryAssignmentDTO to save.
     * @param deliveryAssignmentDTO the deliveryAssignmentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deliveryAssignmentDTO,
     * or with status {@code 400 (Bad Request)} if the deliveryAssignmentDTO is not valid,
     * or with status {@code 404 (Not Found)} if the deliveryAssignmentDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the deliveryAssignmentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/delivery-assignments/{deliveryAssignmentId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DeliveryAssignmentDTO> partialUpdateDeliveryAssignment(
        @PathVariable(value = "deliveryAssignmentId", required = false) final Long deliveryAssignmentId,
        @NotNull @RequestBody DeliveryAssignmentDTO deliveryAssignmentDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DeliveryAssignment partially : {}, {}", deliveryAssignmentId, deliveryAssignmentDTO);
        if (deliveryAssignmentDTO.getDeliveryAssignmentId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(deliveryAssignmentId, deliveryAssignmentDTO.getDeliveryAssignmentId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!deliveryAssignmentRepository.existsById(deliveryAssignmentId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DeliveryAssignmentDTO> result = deliveryAssignmentService.partialUpdate(deliveryAssignmentDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                false,
                ENTITY_NAME,
                deliveryAssignmentDTO.getDeliveryAssignmentId().toString()
            )
        );
    }

    /**
     * {@code GET  /delivery-assignments} : get all the deliveryAssignments.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of deliveryAssignments in body.
     */
    @GetMapping("/delivery-assignments")
    public ResponseEntity<List<DeliveryAssignmentDTO>> getAllDeliveryAssignments(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of DeliveryAssignments");
        Page<DeliveryAssignmentDTO> page = deliveryAssignmentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /delivery-assignments/:id} : get the "id" deliveryAssignment.
     *
     * @param id the id of the deliveryAssignmentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the deliveryAssignmentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/delivery-assignments/{id}")
    public ResponseEntity<DeliveryAssignmentDTO> getDeliveryAssignment(@PathVariable Long id) {
        log.debug("REST request to get DeliveryAssignment : {}", id);
        Optional<DeliveryAssignmentDTO> deliveryAssignmentDTO = deliveryAssignmentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(deliveryAssignmentDTO);
    }

    /**
     * {@code DELETE  /delivery-assignments/:id} : delete the "id" deliveryAssignment.
     *
     * @param id the id of the deliveryAssignmentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/delivery-assignments/{id}")
    public ResponseEntity<Void> deleteDeliveryAssignment(@PathVariable Long id) {
        log.debug("REST request to delete DeliveryAssignment : {}", id);
        deliveryAssignmentService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
