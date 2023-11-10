package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.InvoicePostingDetailsRepository;
import com.sunknowledge.dme.rcm.service.InvoicePostingDetailsService;
import com.sunknowledge.dme.rcm.service.dto.InvoicePostingDetailsDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.InvoicePostingDetails}.
 */
@RestController
@RequestMapping("/api")
public class InvoicePostingDetailsResource {

    private final Logger log = LoggerFactory.getLogger(InvoicePostingDetailsResource.class);

    private static final String ENTITY_NAME = "claimsInvoicePostingDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InvoicePostingDetailsService invoicePostingDetailsService;

    private final InvoicePostingDetailsRepository invoicePostingDetailsRepository;

    public InvoicePostingDetailsResource(
        InvoicePostingDetailsService invoicePostingDetailsService,
        InvoicePostingDetailsRepository invoicePostingDetailsRepository
    ) {
        this.invoicePostingDetailsService = invoicePostingDetailsService;
        this.invoicePostingDetailsRepository = invoicePostingDetailsRepository;
    }

    /**
     * {@code POST  /invoice-posting-details} : Create a new invoicePostingDetails.
     *
     * @param invoicePostingDetailsDTO the invoicePostingDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new invoicePostingDetailsDTO, or with status {@code 400 (Bad Request)} if the invoicePostingDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/invoice-posting-details")
    public ResponseEntity<InvoicePostingDetailsDTO> createInvoicePostingDetails(
        @Valid @RequestBody InvoicePostingDetailsDTO invoicePostingDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to save InvoicePostingDetails : {}", invoicePostingDetailsDTO);
        if (invoicePostingDetailsDTO.getInvoiceLineItemPostingId() != null) {
            throw new BadRequestAlertException("A new invoicePostingDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InvoicePostingDetailsDTO result = invoicePostingDetailsService.save(invoicePostingDetailsDTO);
        return ResponseEntity
            .created(new URI("/api/invoice-posting-details/" + result.getInvoiceLineItemPostingId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getInvoiceLineItemPostingId().toString())
            )
            .body(result);
    }

    /**
     * {@code PUT  /invoice-posting-details/:invoiceLineItemPostingId} : Updates an existing invoicePostingDetails.
     *
     * @param invoiceLineItemPostingId the id of the invoicePostingDetailsDTO to save.
     * @param invoicePostingDetailsDTO the invoicePostingDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated invoicePostingDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the invoicePostingDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the invoicePostingDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/invoice-posting-details/{invoiceLineItemPostingId}")
    public ResponseEntity<InvoicePostingDetailsDTO> updateInvoicePostingDetails(
        @PathVariable(value = "invoiceLineItemPostingId", required = false) final Long invoiceLineItemPostingId,
        @Valid @RequestBody InvoicePostingDetailsDTO invoicePostingDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update InvoicePostingDetails : {}, {}", invoiceLineItemPostingId, invoicePostingDetailsDTO);
        if (invoicePostingDetailsDTO.getInvoiceLineItemPostingId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(invoiceLineItemPostingId, invoicePostingDetailsDTO.getInvoiceLineItemPostingId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!invoicePostingDetailsRepository.existsById(invoiceLineItemPostingId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        InvoicePostingDetailsDTO result = invoicePostingDetailsService.update(invoicePostingDetailsDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    invoicePostingDetailsDTO.getInvoiceLineItemPostingId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /invoice-posting-details/:invoiceLineItemPostingId} : Partial updates given fields of an existing invoicePostingDetails, field will ignore if it is null
     *
     * @param invoiceLineItemPostingId the id of the invoicePostingDetailsDTO to save.
     * @param invoicePostingDetailsDTO the invoicePostingDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated invoicePostingDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the invoicePostingDetailsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the invoicePostingDetailsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the invoicePostingDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/invoice-posting-details/{invoiceLineItemPostingId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public ResponseEntity<InvoicePostingDetailsDTO> partialUpdateInvoicePostingDetails(
        @PathVariable(value = "invoiceLineItemPostingId", required = false) final Long invoiceLineItemPostingId,
        @NotNull @RequestBody InvoicePostingDetailsDTO invoicePostingDetailsDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update InvoicePostingDetails partially : {}, {}",
            invoiceLineItemPostingId,
            invoicePostingDetailsDTO
        );
        if (invoicePostingDetailsDTO.getInvoiceLineItemPostingId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(invoiceLineItemPostingId, invoicePostingDetailsDTO.getInvoiceLineItemPostingId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!invoicePostingDetailsRepository.existsById(invoiceLineItemPostingId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<InvoicePostingDetailsDTO> result = invoicePostingDetailsService.partialUpdate(invoicePostingDetailsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                false,
                ENTITY_NAME,
                invoicePostingDetailsDTO.getInvoiceLineItemPostingId().toString()
            )
        );
    }

    /**
     * {@code GET  /invoice-posting-details} : get all the invoicePostingDetails.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of invoicePostingDetails in body.
     */
    @GetMapping("/invoice-posting-details")
    public ResponseEntity<List<InvoicePostingDetailsDTO>> getAllInvoicePostingDetails(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of InvoicePostingDetails");
        Page<InvoicePostingDetailsDTO> page = invoicePostingDetailsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /invoice-posting-details/:id} : get the "id" invoicePostingDetails.
     *
     * @param id the id of the invoicePostingDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the invoicePostingDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/invoice-posting-details/{id}")
    public ResponseEntity<InvoicePostingDetailsDTO> getInvoicePostingDetails(@PathVariable Long id) {
        log.debug("REST request to get InvoicePostingDetails : {}", id);
        Optional<InvoicePostingDetailsDTO> invoicePostingDetailsDTO = invoicePostingDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(invoicePostingDetailsDTO);
    }

    /**
     * {@code DELETE  /invoice-posting-details/:id} : delete the "id" invoicePostingDetails.
     *
     * @param id the id of the invoicePostingDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/invoice-posting-details/{id}")
    public ResponseEntity<Void> deleteInvoicePostingDetails(@PathVariable Long id) {
        log.debug("REST request to delete InvoicePostingDetails : {}", id);
        invoicePostingDetailsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
