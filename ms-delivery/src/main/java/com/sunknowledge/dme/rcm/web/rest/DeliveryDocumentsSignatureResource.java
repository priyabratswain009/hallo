package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.DeliveryDocumentsSignatureRepository;
import com.sunknowledge.dme.rcm.service.DeliveryDocumentsSignatureService;
import com.sunknowledge.dme.rcm.service.dto.DeliveryDocumentsSignatureDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.DeliveryDocumentsSignature}.
 */
@RestController
@RequestMapping("/api")
public class DeliveryDocumentsSignatureResource {

    private final Logger log = LoggerFactory.getLogger(DeliveryDocumentsSignatureResource.class);

    private static final String ENTITY_NAME = "deliveryDeliveryDocumentsSignature";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DeliveryDocumentsSignatureService deliveryDocumentsSignatureService;

    private final DeliveryDocumentsSignatureRepository deliveryDocumentsSignatureRepository;

    public DeliveryDocumentsSignatureResource(
        DeliveryDocumentsSignatureService deliveryDocumentsSignatureService,
        DeliveryDocumentsSignatureRepository deliveryDocumentsSignatureRepository
    ) {
        this.deliveryDocumentsSignatureService = deliveryDocumentsSignatureService;
        this.deliveryDocumentsSignatureRepository = deliveryDocumentsSignatureRepository;
    }

    /**
     * {@code POST  /delivery-documents-signatures} : Create a new deliveryDocumentsSignature.
     *
     * @param deliveryDocumentsSignatureDTO the deliveryDocumentsSignatureDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new deliveryDocumentsSignatureDTO, or with status {@code 400 (Bad Request)} if the deliveryDocumentsSignature has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/delivery-documents-signatures")
    public ResponseEntity<DeliveryDocumentsSignatureDTO> createDeliveryDocumentsSignature(
        @Valid @RequestBody DeliveryDocumentsSignatureDTO deliveryDocumentsSignatureDTO
    ) throws URISyntaxException {
        log.debug("REST request to save DeliveryDocumentsSignature : {}", deliveryDocumentsSignatureDTO);
        if (deliveryDocumentsSignatureDTO.getDeliveryDocumentSignatureId() != null) {
            throw new BadRequestAlertException("A new deliveryDocumentsSignature cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DeliveryDocumentsSignatureDTO result = deliveryDocumentsSignatureService.save(deliveryDocumentsSignatureDTO);
        return ResponseEntity
            .created(new URI("/api/delivery-documents-signatures/" + result.getDeliveryDocumentSignatureId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    result.getDeliveryDocumentSignatureId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PUT  /delivery-documents-signatures/:deliveryDocumentSignatureId} : Updates an existing deliveryDocumentsSignature.
     *
     * @param deliveryDocumentSignatureId the id of the deliveryDocumentsSignatureDTO to save.
     * @param deliveryDocumentsSignatureDTO the deliveryDocumentsSignatureDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deliveryDocumentsSignatureDTO,
     * or with status {@code 400 (Bad Request)} if the deliveryDocumentsSignatureDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the deliveryDocumentsSignatureDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/delivery-documents-signatures/{deliveryDocumentSignatureId}")
    public ResponseEntity<DeliveryDocumentsSignatureDTO> updateDeliveryDocumentsSignature(
        @PathVariable(value = "deliveryDocumentSignatureId", required = false) final Long deliveryDocumentSignatureId,
        @Valid @RequestBody DeliveryDocumentsSignatureDTO deliveryDocumentsSignatureDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DeliveryDocumentsSignature : {}, {}", deliveryDocumentSignatureId, deliveryDocumentsSignatureDTO);
        if (deliveryDocumentsSignatureDTO.getDeliveryDocumentSignatureId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(deliveryDocumentSignatureId, deliveryDocumentsSignatureDTO.getDeliveryDocumentSignatureId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!deliveryDocumentsSignatureRepository.existsById(deliveryDocumentSignatureId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DeliveryDocumentsSignatureDTO result = deliveryDocumentsSignatureService.update(deliveryDocumentsSignatureDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    deliveryDocumentsSignatureDTO.getDeliveryDocumentSignatureId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /delivery-documents-signatures/:deliveryDocumentSignatureId} : Partial updates given fields of an existing deliveryDocumentsSignature, field will ignore if it is null
     *
     * @param deliveryDocumentSignatureId the id of the deliveryDocumentsSignatureDTO to save.
     * @param deliveryDocumentsSignatureDTO the deliveryDocumentsSignatureDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deliveryDocumentsSignatureDTO,
     * or with status {@code 400 (Bad Request)} if the deliveryDocumentsSignatureDTO is not valid,
     * or with status {@code 404 (Not Found)} if the deliveryDocumentsSignatureDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the deliveryDocumentsSignatureDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/delivery-documents-signatures/{deliveryDocumentSignatureId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public ResponseEntity<DeliveryDocumentsSignatureDTO> partialUpdateDeliveryDocumentsSignature(
        @PathVariable(value = "deliveryDocumentSignatureId", required = false) final Long deliveryDocumentSignatureId,
        @NotNull @RequestBody DeliveryDocumentsSignatureDTO deliveryDocumentsSignatureDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update DeliveryDocumentsSignature partially : {}, {}",
            deliveryDocumentSignatureId,
            deliveryDocumentsSignatureDTO
        );
        if (deliveryDocumentsSignatureDTO.getDeliveryDocumentSignatureId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(deliveryDocumentSignatureId, deliveryDocumentsSignatureDTO.getDeliveryDocumentSignatureId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!deliveryDocumentsSignatureRepository.existsById(deliveryDocumentSignatureId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DeliveryDocumentsSignatureDTO> result = deliveryDocumentsSignatureService.partialUpdate(deliveryDocumentsSignatureDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                false,
                ENTITY_NAME,
                deliveryDocumentsSignatureDTO.getDeliveryDocumentSignatureId().toString()
            )
        );
    }

    /**
     * {@code GET  /delivery-documents-signatures} : get all the deliveryDocumentsSignatures.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of deliveryDocumentsSignatures in body.
     */
    @GetMapping("/delivery-documents-signatures")
    public ResponseEntity<List<DeliveryDocumentsSignatureDTO>> getAllDeliveryDocumentsSignatures(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of DeliveryDocumentsSignatures");
        Page<DeliveryDocumentsSignatureDTO> page = deliveryDocumentsSignatureService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /delivery-documents-signatures/:id} : get the "id" deliveryDocumentsSignature.
     *
     * @param id the id of the deliveryDocumentsSignatureDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the deliveryDocumentsSignatureDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/delivery-documents-signatures/{id}")
    public ResponseEntity<DeliveryDocumentsSignatureDTO> getDeliveryDocumentsSignature(@PathVariable Long id) {
        log.debug("REST request to get DeliveryDocumentsSignature : {}", id);
        Optional<DeliveryDocumentsSignatureDTO> deliveryDocumentsSignatureDTO = deliveryDocumentsSignatureService.findOne(id);
        return ResponseUtil.wrapOrNotFound(deliveryDocumentsSignatureDTO);
    }

    /**
     * {@code DELETE  /delivery-documents-signatures/:id} : delete the "id" deliveryDocumentsSignature.
     *
     * @param id the id of the deliveryDocumentsSignatureDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/delivery-documents-signatures/{id}")
    public ResponseEntity<Void> deleteDeliveryDocumentsSignature(@PathVariable Long id) {
        log.debug("REST request to delete DeliveryDocumentsSignature : {}", id);
        deliveryDocumentsSignatureService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
