package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.InvoiceLineItemDetailsRepository;
import com.sunknowledge.dme.rcm.service.InvoiceLineItemDetailsService;
import com.sunknowledge.dme.rcm.service.dto.InvoiceLineItemDetailsDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.InvoiceLineItemDetails}.
 */
@RestController
@RequestMapping("/api")
public class InvoiceLineItemDetailsResource {

    private final Logger log = LoggerFactory.getLogger(InvoiceLineItemDetailsResource.class);

    private static final String ENTITY_NAME = "claimsInvoiceLineItemDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InvoiceLineItemDetailsService invoiceLineItemDetailsService;

    private final InvoiceLineItemDetailsRepository invoiceLineItemDetailsRepository;

    public InvoiceLineItemDetailsResource(
        InvoiceLineItemDetailsService invoiceLineItemDetailsService,
        InvoiceLineItemDetailsRepository invoiceLineItemDetailsRepository
    ) {
        this.invoiceLineItemDetailsService = invoiceLineItemDetailsService;
        this.invoiceLineItemDetailsRepository = invoiceLineItemDetailsRepository;
    }

    /**
     * {@code POST  /invoice-line-item-details} : Create a new invoiceLineItemDetails.
     *
     * @param invoiceLineItemDetailsDTO the invoiceLineItemDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new invoiceLineItemDetailsDTO, or with status {@code 400 (Bad Request)} if the invoiceLineItemDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/invoice-line-item-details")
    public ResponseEntity<InvoiceLineItemDetailsDTO> createInvoiceLineItemDetails(
        @Valid @RequestBody InvoiceLineItemDetailsDTO invoiceLineItemDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to save InvoiceLineItemDetails : {}", invoiceLineItemDetailsDTO);
        if (invoiceLineItemDetailsDTO.getInvoiceLineItemDetailsId() != null) {
            throw new BadRequestAlertException("A new invoiceLineItemDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InvoiceLineItemDetailsDTO result = invoiceLineItemDetailsService.save(invoiceLineItemDetailsDTO);
        return ResponseEntity
            .created(new URI("/api/invoice-line-item-details/" + result.getInvoiceLineItemDetailsId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getInvoiceLineItemDetailsId().toString())
            )
            .body(result);
    }

    /**
     * {@code PUT  /invoice-line-item-details/:invoiceLineItemDetailsId} : Updates an existing invoiceLineItemDetails.
     *
     * @param invoiceLineItemDetailsId the id of the invoiceLineItemDetailsDTO to save.
     * @param invoiceLineItemDetailsDTO the invoiceLineItemDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated invoiceLineItemDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the invoiceLineItemDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the invoiceLineItemDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/invoice-line-item-details/{invoiceLineItemDetailsId}")
    public ResponseEntity<InvoiceLineItemDetailsDTO> updateInvoiceLineItemDetails(
        @PathVariable(value = "invoiceLineItemDetailsId", required = false) final Long invoiceLineItemDetailsId,
        @Valid @RequestBody InvoiceLineItemDetailsDTO invoiceLineItemDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update InvoiceLineItemDetails : {}, {}", invoiceLineItemDetailsId, invoiceLineItemDetailsDTO);
        if (invoiceLineItemDetailsDTO.getInvoiceLineItemDetailsId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(invoiceLineItemDetailsId, invoiceLineItemDetailsDTO.getInvoiceLineItemDetailsId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!invoiceLineItemDetailsRepository.existsById(invoiceLineItemDetailsId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        InvoiceLineItemDetailsDTO result = invoiceLineItemDetailsService.update(invoiceLineItemDetailsDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    invoiceLineItemDetailsDTO.getInvoiceLineItemDetailsId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /invoice-line-item-details/:invoiceLineItemDetailsId} : Partial updates given fields of an existing invoiceLineItemDetails, field will ignore if it is null
     *
     * @param invoiceLineItemDetailsId the id of the invoiceLineItemDetailsDTO to save.
     * @param invoiceLineItemDetailsDTO the invoiceLineItemDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated invoiceLineItemDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the invoiceLineItemDetailsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the invoiceLineItemDetailsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the invoiceLineItemDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/invoice-line-item-details/{invoiceLineItemDetailsId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public ResponseEntity<InvoiceLineItemDetailsDTO> partialUpdateInvoiceLineItemDetails(
        @PathVariable(value = "invoiceLineItemDetailsId", required = false) final Long invoiceLineItemDetailsId,
        @NotNull @RequestBody InvoiceLineItemDetailsDTO invoiceLineItemDetailsDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update InvoiceLineItemDetails partially : {}, {}",
            invoiceLineItemDetailsId,
            invoiceLineItemDetailsDTO
        );
        if (invoiceLineItemDetailsDTO.getInvoiceLineItemDetailsId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(invoiceLineItemDetailsId, invoiceLineItemDetailsDTO.getInvoiceLineItemDetailsId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!invoiceLineItemDetailsRepository.existsById(invoiceLineItemDetailsId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<InvoiceLineItemDetailsDTO> result = invoiceLineItemDetailsService.partialUpdate(invoiceLineItemDetailsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                false,
                ENTITY_NAME,
                invoiceLineItemDetailsDTO.getInvoiceLineItemDetailsId().toString()
            )
        );
    }

    /**
     * {@code GET  /invoice-line-item-details} : get all the invoiceLineItemDetails.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of invoiceLineItemDetails in body.
     */
    @GetMapping("/invoice-line-item-details")
    public ResponseEntity<List<InvoiceLineItemDetailsDTO>> getAllInvoiceLineItemDetails(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of InvoiceLineItemDetails");
        Page<InvoiceLineItemDetailsDTO> page = invoiceLineItemDetailsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /invoice-line-item-details/:id} : get the "id" invoiceLineItemDetails.
     *
     * @param id the id of the invoiceLineItemDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the invoiceLineItemDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/invoice-line-item-details/{id}")
    public ResponseEntity<InvoiceLineItemDetailsDTO> getInvoiceLineItemDetails(@PathVariable Long id) {
        log.debug("REST request to get InvoiceLineItemDetails : {}", id);
        Optional<InvoiceLineItemDetailsDTO> invoiceLineItemDetailsDTO = invoiceLineItemDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(invoiceLineItemDetailsDTO);
    }

    /**
     * {@code DELETE  /invoice-line-item-details/:id} : delete the "id" invoiceLineItemDetails.
     *
     * @param id the id of the invoiceLineItemDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/invoice-line-item-details/{id}")
    public ResponseEntity<Void> deleteInvoiceLineItemDetails(@PathVariable Long id) {
        log.debug("REST request to delete InvoiceLineItemDetails : {}", id);
        invoiceLineItemDetailsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
