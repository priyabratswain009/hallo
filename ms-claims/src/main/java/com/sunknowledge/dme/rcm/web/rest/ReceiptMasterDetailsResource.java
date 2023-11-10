package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ReceiptMasterDetailsRepository;
import com.sunknowledge.dme.rcm.service.ReceiptMasterDetailsService;
import com.sunknowledge.dme.rcm.service.dto.ReceiptMasterDetailsDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ReceiptMasterDetails}.
 */
@RestController
@RequestMapping("/api")
public class ReceiptMasterDetailsResource {

    private final Logger log = LoggerFactory.getLogger(ReceiptMasterDetailsResource.class);

    private static final String ENTITY_NAME = "claimsReceiptMasterDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReceiptMasterDetailsService receiptMasterDetailsService;

    private final ReceiptMasterDetailsRepository receiptMasterDetailsRepository;

    public ReceiptMasterDetailsResource(
        ReceiptMasterDetailsService receiptMasterDetailsService,
        ReceiptMasterDetailsRepository receiptMasterDetailsRepository
    ) {
        this.receiptMasterDetailsService = receiptMasterDetailsService;
        this.receiptMasterDetailsRepository = receiptMasterDetailsRepository;
    }

    /**
     * {@code POST  /receipt-master-details} : Create a new receiptMasterDetails.
     *
     * @param receiptMasterDetailsDTO the receiptMasterDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new receiptMasterDetailsDTO, or with status {@code 400 (Bad Request)} if the receiptMasterDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/receipt-master-details")
    public ResponseEntity<ReceiptMasterDetailsDTO> createReceiptMasterDetails(
        @Valid @RequestBody ReceiptMasterDetailsDTO receiptMasterDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to save ReceiptMasterDetails : {}", receiptMasterDetailsDTO);
        if (receiptMasterDetailsDTO.getReceiptId() != null) {
            throw new BadRequestAlertException("A new receiptMasterDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReceiptMasterDetailsDTO result = receiptMasterDetailsService.save(receiptMasterDetailsDTO);
        return ResponseEntity
            .created(new URI("/api/receipt-master-details/" + result.getReceiptId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getReceiptId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /receipt-master-details/:receiptId} : Updates an existing receiptMasterDetails.
     *
     * @param receiptId the id of the receiptMasterDetailsDTO to save.
     * @param receiptMasterDetailsDTO the receiptMasterDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated receiptMasterDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the receiptMasterDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the receiptMasterDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/receipt-master-details/{receiptId}")
    public ResponseEntity<ReceiptMasterDetailsDTO> updateReceiptMasterDetails(
        @PathVariable(value = "receiptId", required = false) final Long receiptId,
        @Valid @RequestBody ReceiptMasterDetailsDTO receiptMasterDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ReceiptMasterDetails : {}, {}", receiptId, receiptMasterDetailsDTO);
        if (receiptMasterDetailsDTO.getReceiptId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(receiptId, receiptMasterDetailsDTO.getReceiptId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!receiptMasterDetailsRepository.existsById(receiptId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ReceiptMasterDetailsDTO result = receiptMasterDetailsService.update(receiptMasterDetailsDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, receiptMasterDetailsDTO.getReceiptId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /receipt-master-details/:receiptId} : Partial updates given fields of an existing receiptMasterDetails, field will ignore if it is null
     *
     * @param receiptId the id of the receiptMasterDetailsDTO to save.
     * @param receiptMasterDetailsDTO the receiptMasterDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated receiptMasterDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the receiptMasterDetailsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the receiptMasterDetailsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the receiptMasterDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/receipt-master-details/{receiptId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ReceiptMasterDetailsDTO> partialUpdateReceiptMasterDetails(
        @PathVariable(value = "receiptId", required = false) final Long receiptId,
        @NotNull @RequestBody ReceiptMasterDetailsDTO receiptMasterDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ReceiptMasterDetails partially : {}, {}", receiptId, receiptMasterDetailsDTO);
        if (receiptMasterDetailsDTO.getReceiptId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(receiptId, receiptMasterDetailsDTO.getReceiptId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!receiptMasterDetailsRepository.existsById(receiptId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ReceiptMasterDetailsDTO> result = receiptMasterDetailsService.partialUpdate(receiptMasterDetailsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, receiptMasterDetailsDTO.getReceiptId().toString())
        );
    }

    /**
     * {@code GET  /receipt-master-details} : get all the receiptMasterDetails.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of receiptMasterDetails in body.
     */
    @GetMapping("/receipt-master-details")
    public ResponseEntity<List<ReceiptMasterDetailsDTO>> getAllReceiptMasterDetails(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ReceiptMasterDetails");
        Page<ReceiptMasterDetailsDTO> page = receiptMasterDetailsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /receipt-master-details/:id} : get the "id" receiptMasterDetails.
     *
     * @param id the id of the receiptMasterDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the receiptMasterDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/receipt-master-details/{id}")
    public ResponseEntity<ReceiptMasterDetailsDTO> getReceiptMasterDetails(@PathVariable Long id) {
        log.debug("REST request to get ReceiptMasterDetails : {}", id);
        Optional<ReceiptMasterDetailsDTO> receiptMasterDetailsDTO = receiptMasterDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(receiptMasterDetailsDTO);
    }

    /**
     * {@code DELETE  /receipt-master-details/:id} : delete the "id" receiptMasterDetails.
     *
     * @param id the id of the receiptMasterDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/receipt-master-details/{id}")
    public ResponseEntity<Void> deleteReceiptMasterDetails(@PathVariable Long id) {
        log.debug("REST request to delete ReceiptMasterDetails : {}", id);
        receiptMasterDetailsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
