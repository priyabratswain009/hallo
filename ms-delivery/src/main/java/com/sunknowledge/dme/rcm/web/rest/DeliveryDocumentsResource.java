package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.DeliveryDocumentsRepository;
import com.sunknowledge.dme.rcm.service.DeliveryDocumentsService;
import com.sunknowledge.dme.rcm.service.dto.DeliveryDocumentsDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.DeliveryDocuments}.
 */
@RestController
@RequestMapping("/api")
public class DeliveryDocumentsResource {

    private final Logger log = LoggerFactory.getLogger(DeliveryDocumentsResource.class);

    private static final String ENTITY_NAME = "deliveryDeliveryDocuments";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DeliveryDocumentsService deliveryDocumentsService;

    private final DeliveryDocumentsRepository deliveryDocumentsRepository;

    public DeliveryDocumentsResource(
        DeliveryDocumentsService deliveryDocumentsService,
        DeliveryDocumentsRepository deliveryDocumentsRepository
    ) {
        this.deliveryDocumentsService = deliveryDocumentsService;
        this.deliveryDocumentsRepository = deliveryDocumentsRepository;
    }

    /**
     * {@code POST  /delivery-documents} : Create a new deliveryDocuments.
     *
     * @param deliveryDocumentsDTO the deliveryDocumentsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new deliveryDocumentsDTO, or with status {@code 400 (Bad Request)} if the deliveryDocuments has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/delivery-documents")
    public ResponseEntity<DeliveryDocumentsDTO> createDeliveryDocuments(@Valid @RequestBody DeliveryDocumentsDTO deliveryDocumentsDTO)
        throws URISyntaxException {
        log.debug("REST request to save DeliveryDocuments : {}", deliveryDocumentsDTO);
        if (deliveryDocumentsDTO.getDeliveryDocId() != null) {
            throw new BadRequestAlertException("A new deliveryDocuments cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DeliveryDocumentsDTO result = deliveryDocumentsService.save(deliveryDocumentsDTO);
        return ResponseEntity
            .created(new URI("/api/delivery-documents/" + result.getDeliveryDocId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getDeliveryDocId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /delivery-documents/:deliveryDocId} : Updates an existing deliveryDocuments.
     *
     * @param deliveryDocId the id of the deliveryDocumentsDTO to save.
     * @param deliveryDocumentsDTO the deliveryDocumentsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deliveryDocumentsDTO,
     * or with status {@code 400 (Bad Request)} if the deliveryDocumentsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the deliveryDocumentsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/delivery-documents/{deliveryDocId}")
    public ResponseEntity<DeliveryDocumentsDTO> updateDeliveryDocuments(
        @PathVariable(value = "deliveryDocId", required = false) final Long deliveryDocId,
        @Valid @RequestBody DeliveryDocumentsDTO deliveryDocumentsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DeliveryDocuments : {}, {}", deliveryDocId, deliveryDocumentsDTO);
        if (deliveryDocumentsDTO.getDeliveryDocId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(deliveryDocId, deliveryDocumentsDTO.getDeliveryDocId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!deliveryDocumentsRepository.existsById(deliveryDocId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DeliveryDocumentsDTO result = deliveryDocumentsService.update(deliveryDocumentsDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, deliveryDocumentsDTO.getDeliveryDocId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /delivery-documents/:deliveryDocId} : Partial updates given fields of an existing deliveryDocuments, field will ignore if it is null
     *
     * @param deliveryDocId the id of the deliveryDocumentsDTO to save.
     * @param deliveryDocumentsDTO the deliveryDocumentsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deliveryDocumentsDTO,
     * or with status {@code 400 (Bad Request)} if the deliveryDocumentsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the deliveryDocumentsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the deliveryDocumentsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/delivery-documents/{deliveryDocId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DeliveryDocumentsDTO> partialUpdateDeliveryDocuments(
        @PathVariable(value = "deliveryDocId", required = false) final Long deliveryDocId,
        @NotNull @RequestBody DeliveryDocumentsDTO deliveryDocumentsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DeliveryDocuments partially : {}, {}", deliveryDocId, deliveryDocumentsDTO);
        if (deliveryDocumentsDTO.getDeliveryDocId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(deliveryDocId, deliveryDocumentsDTO.getDeliveryDocId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!deliveryDocumentsRepository.existsById(deliveryDocId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DeliveryDocumentsDTO> result = deliveryDocumentsService.partialUpdate(deliveryDocumentsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, deliveryDocumentsDTO.getDeliveryDocId().toString())
        );
    }

    /**
     * {@code GET  /delivery-documents} : get all the deliveryDocuments.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of deliveryDocuments in body.
     */
    @GetMapping("/delivery-documents")
    public ResponseEntity<List<DeliveryDocumentsDTO>> getAllDeliveryDocuments(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of DeliveryDocuments");
        Page<DeliveryDocumentsDTO> page = deliveryDocumentsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /delivery-documents/:id} : get the "id" deliveryDocuments.
     *
     * @param id the id of the deliveryDocumentsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the deliveryDocumentsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/delivery-documents/{id}")
    public ResponseEntity<DeliveryDocumentsDTO> getDeliveryDocuments(@PathVariable Long id) {
        log.debug("REST request to get DeliveryDocuments : {}", id);
        Optional<DeliveryDocumentsDTO> deliveryDocumentsDTO = deliveryDocumentsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(deliveryDocumentsDTO);
    }

    /**
     * {@code DELETE  /delivery-documents/:id} : delete the "id" deliveryDocuments.
     *
     * @param id the id of the deliveryDocumentsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/delivery-documents/{id}")
    public ResponseEntity<Void> deleteDeliveryDocuments(@PathVariable Long id) {
        log.debug("REST request to delete DeliveryDocuments : {}", id);
        deliveryDocumentsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
