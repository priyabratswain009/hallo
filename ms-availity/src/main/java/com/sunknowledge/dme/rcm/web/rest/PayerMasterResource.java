package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.PayerMasterRepository;
import com.sunknowledge.dme.rcm.service.PayerMasterService;
import com.sunknowledge.dme.rcm.service.dto.PayerMasterDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.PayerMaster}.
 */
@RestController
@RequestMapping("/api")
public class PayerMasterResource {

    private final Logger log = LoggerFactory.getLogger(PayerMasterResource.class);

    private static final String ENTITY_NAME = "availityPayerMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PayerMasterService payerMasterService;

    private final PayerMasterRepository payerMasterRepository;

    public PayerMasterResource(PayerMasterService payerMasterService, PayerMasterRepository payerMasterRepository) {
        this.payerMasterService = payerMasterService;
        this.payerMasterRepository = payerMasterRepository;
    }

    /**
     * {@code POST  /payer-masters} : Create a new payerMaster.
     *
     * @param payerMasterDTO the payerMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new payerMasterDTO, or with status {@code 400 (Bad Request)} if the payerMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/payer-masters")
    public ResponseEntity<PayerMasterDTO> createPayerMaster(@Valid @RequestBody PayerMasterDTO payerMasterDTO) throws URISyntaxException {
        log.debug("REST request to save PayerMaster : {}", payerMasterDTO);
        if (payerMasterDTO.getPayerMasterId() != null) {
            throw new BadRequestAlertException("A new payerMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PayerMasterDTO result = payerMasterService.save(payerMasterDTO);
        return ResponseEntity
            .created(new URI("/api/payer-masters/" + result.getPayerMasterId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getPayerMasterId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /payer-masters/:payerMasterId} : Updates an existing payerMaster.
     *
     * @param payerMasterId the id of the payerMasterDTO to save.
     * @param payerMasterDTO the payerMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated payerMasterDTO,
     * or with status {@code 400 (Bad Request)} if the payerMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the payerMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/payer-masters/{payerMasterId}")
    public ResponseEntity<PayerMasterDTO> updatePayerMaster(
        @PathVariable(value = "payerMasterId", required = false) final Long payerMasterId,
        @Valid @RequestBody PayerMasterDTO payerMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PayerMaster : {}, {}", payerMasterId, payerMasterDTO);
        if (payerMasterDTO.getPayerMasterId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(payerMasterId, payerMasterDTO.getPayerMasterId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!payerMasterRepository.existsById(payerMasterId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PayerMasterDTO result = payerMasterService.update(payerMasterDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, payerMasterDTO.getPayerMasterId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /payer-masters/:payerMasterId} : Partial updates given fields of an existing payerMaster, field will ignore if it is null
     *
     * @param payerMasterId the id of the payerMasterDTO to save.
     * @param payerMasterDTO the payerMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated payerMasterDTO,
     * or with status {@code 400 (Bad Request)} if the payerMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the payerMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the payerMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/payer-masters/{payerMasterId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PayerMasterDTO> partialUpdatePayerMaster(
        @PathVariable(value = "payerMasterId", required = false) final Long payerMasterId,
        @NotNull @RequestBody PayerMasterDTO payerMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PayerMaster partially : {}, {}", payerMasterId, payerMasterDTO);
        if (payerMasterDTO.getPayerMasterId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(payerMasterId, payerMasterDTO.getPayerMasterId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!payerMasterRepository.existsById(payerMasterId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PayerMasterDTO> result = payerMasterService.partialUpdate(payerMasterDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, payerMasterDTO.getPayerMasterId().toString())
        );
    }

    /**
     * {@code GET  /payer-masters} : get all the payerMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of payerMasters in body.
     */
    @GetMapping("/payer-masters")
    public ResponseEntity<List<PayerMasterDTO>> getAllPayerMasters(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of PayerMasters");
        Page<PayerMasterDTO> page = payerMasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /payer-masters/:id} : get the "id" payerMaster.
     *
     * @param id the id of the payerMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the payerMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/payer-masters/{id}")
    public ResponseEntity<PayerMasterDTO> getPayerMaster(@PathVariable Long id) {
        log.debug("REST request to get PayerMaster : {}", id);
        Optional<PayerMasterDTO> payerMasterDTO = payerMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(payerMasterDTO);
    }

    /**
     * {@code DELETE  /payer-masters/:id} : delete the "id" payerMaster.
     *
     * @param id the id of the payerMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/payer-masters/{id}")
    public ResponseEntity<Void> deletePayerMaster(@PathVariable Long id) {
        log.debug("REST request to delete PayerMaster : {}", id);
        payerMasterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
