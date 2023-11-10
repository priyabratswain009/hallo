package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.Transaction835MasterDetailsRepository;
import com.sunknowledge.dme.rcm.service.Transaction835MasterDetailsService;
import com.sunknowledge.dme.rcm.service.dto.Transaction835MasterDetailsDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.Transaction835MasterDetails}.
 */
@RestController
@RequestMapping("/api")
public class Transaction835MasterDetailsResource {

    private final Logger log = LoggerFactory.getLogger(Transaction835MasterDetailsResource.class);

    private static final String ENTITY_NAME = "claimsTransaction835MasterDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final Transaction835MasterDetailsService transaction835MasterDetailsService;

    private final Transaction835MasterDetailsRepository transaction835MasterDetailsRepository;

    public Transaction835MasterDetailsResource(
        Transaction835MasterDetailsService transaction835MasterDetailsService,
        Transaction835MasterDetailsRepository transaction835MasterDetailsRepository
    ) {
        this.transaction835MasterDetailsService = transaction835MasterDetailsService;
        this.transaction835MasterDetailsRepository = transaction835MasterDetailsRepository;
    }

    /**
     * {@code POST  /transaction-835-master-details} : Create a new transaction835MasterDetails.
     *
     * @param transaction835MasterDetailsDTO the transaction835MasterDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new transaction835MasterDetailsDTO, or with status {@code 400 (Bad Request)} if the transaction835MasterDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/transaction-835-master-details")
    public ResponseEntity<Transaction835MasterDetailsDTO> createTransaction835MasterDetails(
        @Valid @RequestBody Transaction835MasterDetailsDTO transaction835MasterDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to save Transaction835MasterDetails : {}", transaction835MasterDetailsDTO);
        if (transaction835MasterDetailsDTO.getTransactionId() != null) {
            throw new BadRequestAlertException("A new transaction835MasterDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Transaction835MasterDetailsDTO result = transaction835MasterDetailsService.save(transaction835MasterDetailsDTO);
        return ResponseEntity
            .created(new URI("/api/transaction-835-master-details/" + result.getTransactionId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getTransactionId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /transaction-835-master-details/:transactionId} : Updates an existing transaction835MasterDetails.
     *
     * @param transactionId the id of the transaction835MasterDetailsDTO to save.
     * @param transaction835MasterDetailsDTO the transaction835MasterDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated transaction835MasterDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the transaction835MasterDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the transaction835MasterDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/transaction-835-master-details/{transactionId}")
    public ResponseEntity<Transaction835MasterDetailsDTO> updateTransaction835MasterDetails(
        @PathVariable(value = "transactionId", required = false) final Long transactionId,
        @Valid @RequestBody Transaction835MasterDetailsDTO transaction835MasterDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Transaction835MasterDetails : {}, {}", transactionId, transaction835MasterDetailsDTO);
        if (transaction835MasterDetailsDTO.getTransactionId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(transactionId, transaction835MasterDetailsDTO.getTransactionId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!transaction835MasterDetailsRepository.existsById(transactionId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Transaction835MasterDetailsDTO result = transaction835MasterDetailsService.update(transaction835MasterDetailsDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    transaction835MasterDetailsDTO.getTransactionId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /transaction-835-master-details/:transactionId} : Partial updates given fields of an existing transaction835MasterDetails, field will ignore if it is null
     *
     * @param transactionId the id of the transaction835MasterDetailsDTO to save.
     * @param transaction835MasterDetailsDTO the transaction835MasterDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated transaction835MasterDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the transaction835MasterDetailsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the transaction835MasterDetailsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the transaction835MasterDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/transaction-835-master-details/{transactionId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public ResponseEntity<Transaction835MasterDetailsDTO> partialUpdateTransaction835MasterDetails(
        @PathVariable(value = "transactionId", required = false) final Long transactionId,
        @NotNull @RequestBody Transaction835MasterDetailsDTO transaction835MasterDetailsDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update Transaction835MasterDetails partially : {}, {}",
            transactionId,
            transaction835MasterDetailsDTO
        );
        if (transaction835MasterDetailsDTO.getTransactionId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(transactionId, transaction835MasterDetailsDTO.getTransactionId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!transaction835MasterDetailsRepository.existsById(transactionId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Transaction835MasterDetailsDTO> result = transaction835MasterDetailsService.partialUpdate(transaction835MasterDetailsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                false,
                ENTITY_NAME,
                transaction835MasterDetailsDTO.getTransactionId().toString()
            )
        );
    }

    /**
     * {@code GET  /transaction-835-master-details} : get all the transaction835MasterDetails.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of transaction835MasterDetails in body.
     */
    @GetMapping("/transaction-835-master-details")
    public ResponseEntity<List<Transaction835MasterDetailsDTO>> getAllTransaction835MasterDetails(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of Transaction835MasterDetails");
        Page<Transaction835MasterDetailsDTO> page = transaction835MasterDetailsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /transaction-835-master-details/:id} : get the "id" transaction835MasterDetails.
     *
     * @param id the id of the transaction835MasterDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the transaction835MasterDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/transaction-835-master-details/{id}")
    public ResponseEntity<Transaction835MasterDetailsDTO> getTransaction835MasterDetails(@PathVariable Long id) {
        log.debug("REST request to get Transaction835MasterDetails : {}", id);
        Optional<Transaction835MasterDetailsDTO> transaction835MasterDetailsDTO = transaction835MasterDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(transaction835MasterDetailsDTO);
    }

    /**
     * {@code DELETE  /transaction-835-master-details/:id} : delete the "id" transaction835MasterDetails.
     *
     * @param id the id of the transaction835MasterDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/transaction-835-master-details/{id}")
    public ResponseEntity<Void> deleteTransaction835MasterDetails(@PathVariable Long id) {
        log.debug("REST request to delete Transaction835MasterDetails : {}", id);
        transaction835MasterDetailsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
