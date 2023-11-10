package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.InvoiceMasterDetailsRepository;
import com.sunknowledge.dme.rcm.service.InvoiceMasterDetailsService;
import com.sunknowledge.dme.rcm.service.dto.InvoiceMasterDetailsDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.InvoiceMasterDetails}.
 */
@RestController
@RequestMapping("/api")
public class InvoiceMasterDetailsResource {

    private final Logger log = LoggerFactory.getLogger(InvoiceMasterDetailsResource.class);

    private static final String ENTITY_NAME = "claimsInvoiceMasterDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InvoiceMasterDetailsService invoiceMasterDetailsService;

    private final InvoiceMasterDetailsRepository invoiceMasterDetailsRepository;

    public InvoiceMasterDetailsResource(
        InvoiceMasterDetailsService invoiceMasterDetailsService,
        InvoiceMasterDetailsRepository invoiceMasterDetailsRepository
    ) {
        this.invoiceMasterDetailsService = invoiceMasterDetailsService;
        this.invoiceMasterDetailsRepository = invoiceMasterDetailsRepository;
    }

    /**
     * {@code POST  /invoice-master-details} : Create a new invoiceMasterDetails.
     *
     * @param invoiceMasterDetailsDTO the invoiceMasterDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new invoiceMasterDetailsDTO, or with status {@code 400 (Bad Request)} if the invoiceMasterDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/invoice-master-details")
    public ResponseEntity<InvoiceMasterDetailsDTO> createInvoiceMasterDetails(
        @Valid @RequestBody InvoiceMasterDetailsDTO invoiceMasterDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to save InvoiceMasterDetails : {}", invoiceMasterDetailsDTO);
        if (invoiceMasterDetailsDTO.getInvoiceId() != null) {
            throw new BadRequestAlertException("A new invoiceMasterDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InvoiceMasterDetailsDTO result = invoiceMasterDetailsService.save(invoiceMasterDetailsDTO);
        return ResponseEntity
            .created(new URI("/api/invoice-master-details/" + result.getInvoiceId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getInvoiceId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /invoice-master-details/:invoiceId} : Updates an existing invoiceMasterDetails.
     *
     * @param invoiceId the id of the invoiceMasterDetailsDTO to save.
     * @param invoiceMasterDetailsDTO the invoiceMasterDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated invoiceMasterDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the invoiceMasterDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the invoiceMasterDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/invoice-master-details/{invoiceId}")
    public ResponseEntity<InvoiceMasterDetailsDTO> updateInvoiceMasterDetails(
        @PathVariable(value = "invoiceId", required = false) final Long invoiceId,
        @Valid @RequestBody InvoiceMasterDetailsDTO invoiceMasterDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update InvoiceMasterDetails : {}, {}", invoiceId, invoiceMasterDetailsDTO);
        if (invoiceMasterDetailsDTO.getInvoiceId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(invoiceId, invoiceMasterDetailsDTO.getInvoiceId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!invoiceMasterDetailsRepository.existsById(invoiceId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        InvoiceMasterDetailsDTO result = invoiceMasterDetailsService.update(invoiceMasterDetailsDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, invoiceMasterDetailsDTO.getInvoiceId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /invoice-master-details/:invoiceId} : Partial updates given fields of an existing invoiceMasterDetails, field will ignore if it is null
     *
     * @param invoiceId the id of the invoiceMasterDetailsDTO to save.
     * @param invoiceMasterDetailsDTO the invoiceMasterDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated invoiceMasterDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the invoiceMasterDetailsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the invoiceMasterDetailsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the invoiceMasterDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/invoice-master-details/{invoiceId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<InvoiceMasterDetailsDTO> partialUpdateInvoiceMasterDetails(
        @PathVariable(value = "invoiceId", required = false) final Long invoiceId,
        @NotNull @RequestBody InvoiceMasterDetailsDTO invoiceMasterDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update InvoiceMasterDetails partially : {}, {}", invoiceId, invoiceMasterDetailsDTO);
        if (invoiceMasterDetailsDTO.getInvoiceId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(invoiceId, invoiceMasterDetailsDTO.getInvoiceId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!invoiceMasterDetailsRepository.existsById(invoiceId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<InvoiceMasterDetailsDTO> result = invoiceMasterDetailsService.partialUpdate(invoiceMasterDetailsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, invoiceMasterDetailsDTO.getInvoiceId().toString())
        );
    }

    /**
     * {@code GET  /invoice-master-details} : get all the invoiceMasterDetails.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of invoiceMasterDetails in body.
     */
    @GetMapping("/invoice-master-details")
    public ResponseEntity<List<InvoiceMasterDetailsDTO>> getAllInvoiceMasterDetails(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of InvoiceMasterDetails");
        Page<InvoiceMasterDetailsDTO> page = invoiceMasterDetailsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /invoice-master-details/:id} : get the "id" invoiceMasterDetails.
     *
     * @param id the id of the invoiceMasterDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the invoiceMasterDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/invoice-master-details/{id}")
    public ResponseEntity<InvoiceMasterDetailsDTO> getInvoiceMasterDetails(@PathVariable Long id) {
        log.debug("REST request to get InvoiceMasterDetails : {}", id);
        Optional<InvoiceMasterDetailsDTO> invoiceMasterDetailsDTO = invoiceMasterDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(invoiceMasterDetailsDTO);
    }

    /**
     * {@code DELETE  /invoice-master-details/:id} : delete the "id" invoiceMasterDetails.
     *
     * @param id the id of the invoiceMasterDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/invoice-master-details/{id}")
    public ResponseEntity<Void> deleteInvoiceMasterDetails(@PathVariable Long id) {
        log.debug("REST request to delete InvoiceMasterDetails : {}", id);
        invoiceMasterDetailsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
