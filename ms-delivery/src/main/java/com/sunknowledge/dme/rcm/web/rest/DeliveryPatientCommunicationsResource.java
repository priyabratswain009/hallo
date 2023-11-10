package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.DeliveryPatientCommunicationsRepository;
import com.sunknowledge.dme.rcm.service.DeliveryPatientCommunicationsService;
import com.sunknowledge.dme.rcm.service.dto.DeliveryPatientCommunicationsDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.DeliveryPatientCommunications}.
 */
@RestController
@RequestMapping("/api")
public class DeliveryPatientCommunicationsResource {

    private final Logger log = LoggerFactory.getLogger(DeliveryPatientCommunicationsResource.class);

    private static final String ENTITY_NAME = "deliveryDeliveryPatientCommunications";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DeliveryPatientCommunicationsService deliveryPatientCommunicationsService;

    private final DeliveryPatientCommunicationsRepository deliveryPatientCommunicationsRepository;

    public DeliveryPatientCommunicationsResource(
        DeliveryPatientCommunicationsService deliveryPatientCommunicationsService,
        DeliveryPatientCommunicationsRepository deliveryPatientCommunicationsRepository
    ) {
        this.deliveryPatientCommunicationsService = deliveryPatientCommunicationsService;
        this.deliveryPatientCommunicationsRepository = deliveryPatientCommunicationsRepository;
    }

    /**
     * {@code POST  /delivery-patient-communications} : Create a new deliveryPatientCommunications.
     *
     * @param deliveryPatientCommunicationsDTO the deliveryPatientCommunicationsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new deliveryPatientCommunicationsDTO, or with status {@code 400 (Bad Request)} if the deliveryPatientCommunications has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/delivery-patient-communications")
    public ResponseEntity<DeliveryPatientCommunicationsDTO> createDeliveryPatientCommunications(
        @Valid @RequestBody DeliveryPatientCommunicationsDTO deliveryPatientCommunicationsDTO
    ) throws URISyntaxException {
        log.debug("REST request to save DeliveryPatientCommunications : {}", deliveryPatientCommunicationsDTO);
        if (deliveryPatientCommunicationsDTO.getDeliveryPatientCommunicationsId() != null) {
            throw new BadRequestAlertException("A new deliveryPatientCommunications cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DeliveryPatientCommunicationsDTO result = deliveryPatientCommunicationsService.save(deliveryPatientCommunicationsDTO);
        return ResponseEntity
            .created(new URI("/api/delivery-patient-communications/" + result.getDeliveryPatientCommunicationsId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    result.getDeliveryPatientCommunicationsId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PUT  /delivery-patient-communications/:deliveryPatientCommunicationsId} : Updates an existing deliveryPatientCommunications.
     *
     * @param deliveryPatientCommunicationsId the id of the deliveryPatientCommunicationsDTO to save.
     * @param deliveryPatientCommunicationsDTO the deliveryPatientCommunicationsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deliveryPatientCommunicationsDTO,
     * or with status {@code 400 (Bad Request)} if the deliveryPatientCommunicationsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the deliveryPatientCommunicationsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/delivery-patient-communications/{deliveryPatientCommunicationsId}")
    public ResponseEntity<DeliveryPatientCommunicationsDTO> updateDeliveryPatientCommunications(
        @PathVariable(value = "deliveryPatientCommunicationsId", required = false) final Long deliveryPatientCommunicationsId,
        @Valid @RequestBody DeliveryPatientCommunicationsDTO deliveryPatientCommunicationsDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to update DeliveryPatientCommunications : {}, {}",
            deliveryPatientCommunicationsId,
            deliveryPatientCommunicationsDTO
        );
        if (deliveryPatientCommunicationsDTO.getDeliveryPatientCommunicationsId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(deliveryPatientCommunicationsId, deliveryPatientCommunicationsDTO.getDeliveryPatientCommunicationsId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!deliveryPatientCommunicationsRepository.existsById(deliveryPatientCommunicationsId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DeliveryPatientCommunicationsDTO result = deliveryPatientCommunicationsService.update(deliveryPatientCommunicationsDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    deliveryPatientCommunicationsDTO.getDeliveryPatientCommunicationsId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /delivery-patient-communications/:deliveryPatientCommunicationsId} : Partial updates given fields of an existing deliveryPatientCommunications, field will ignore if it is null
     *
     * @param deliveryPatientCommunicationsId the id of the deliveryPatientCommunicationsDTO to save.
     * @param deliveryPatientCommunicationsDTO the deliveryPatientCommunicationsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deliveryPatientCommunicationsDTO,
     * or with status {@code 400 (Bad Request)} if the deliveryPatientCommunicationsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the deliveryPatientCommunicationsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the deliveryPatientCommunicationsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/delivery-patient-communications/{deliveryPatientCommunicationsId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public ResponseEntity<DeliveryPatientCommunicationsDTO> partialUpdateDeliveryPatientCommunications(
        @PathVariable(value = "deliveryPatientCommunicationsId", required = false) final Long deliveryPatientCommunicationsId,
        @NotNull @RequestBody DeliveryPatientCommunicationsDTO deliveryPatientCommunicationsDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update DeliveryPatientCommunications partially : {}, {}",
            deliveryPatientCommunicationsId,
            deliveryPatientCommunicationsDTO
        );
        if (deliveryPatientCommunicationsDTO.getDeliveryPatientCommunicationsId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(deliveryPatientCommunicationsId, deliveryPatientCommunicationsDTO.getDeliveryPatientCommunicationsId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!deliveryPatientCommunicationsRepository.existsById(deliveryPatientCommunicationsId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DeliveryPatientCommunicationsDTO> result = deliveryPatientCommunicationsService.partialUpdate(
            deliveryPatientCommunicationsDTO
        );

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                false,
                ENTITY_NAME,
                deliveryPatientCommunicationsDTO.getDeliveryPatientCommunicationsId().toString()
            )
        );
    }

    /**
     * {@code GET  /delivery-patient-communications} : get all the deliveryPatientCommunications.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of deliveryPatientCommunications in body.
     */
    @GetMapping("/delivery-patient-communications")
    public ResponseEntity<List<DeliveryPatientCommunicationsDTO>> getAllDeliveryPatientCommunications(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of DeliveryPatientCommunications");
        Page<DeliveryPatientCommunicationsDTO> page = deliveryPatientCommunicationsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /delivery-patient-communications/:id} : get the "id" deliveryPatientCommunications.
     *
     * @param id the id of the deliveryPatientCommunicationsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the deliveryPatientCommunicationsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/delivery-patient-communications/{id}")
    public ResponseEntity<DeliveryPatientCommunicationsDTO> getDeliveryPatientCommunications(@PathVariable Long id) {
        log.debug("REST request to get DeliveryPatientCommunications : {}", id);
        Optional<DeliveryPatientCommunicationsDTO> deliveryPatientCommunicationsDTO = deliveryPatientCommunicationsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(deliveryPatientCommunicationsDTO);
    }

    /**
     * {@code DELETE  /delivery-patient-communications/:id} : delete the "id" deliveryPatientCommunications.
     *
     * @param id the id of the deliveryPatientCommunicationsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/delivery-patient-communications/{id}")
    public ResponseEntity<Void> deleteDeliveryPatientCommunications(@PathVariable Long id) {
        log.debug("REST request to delete DeliveryPatientCommunications : {}", id);
        deliveryPatientCommunicationsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
