package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.BenefitCoverageRequestRepository;
import com.sunknowledge.dme.rcm.service.BenefitCoverageRequestService;
import com.sunknowledge.dme.rcm.service.dto.BenefitCoverageRequestDTO;
import com.sunknowledge.dme.rcm.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.BenefitCoverageRequest}.
 */
@RestController
@RequestMapping("/api")
public class BenefitCoverageRequestResource {

    private final Logger log = LoggerFactory.getLogger(BenefitCoverageRequestResource.class);

    private static final String ENTITY_NAME = "availityBenefitCoverageRequest";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BenefitCoverageRequestService benefitCoverageRequestService;

    private final BenefitCoverageRequestRepository benefitCoverageRequestRepository;

    public BenefitCoverageRequestResource(
        BenefitCoverageRequestService benefitCoverageRequestService,
        BenefitCoverageRequestRepository benefitCoverageRequestRepository
    ) {
        this.benefitCoverageRequestService = benefitCoverageRequestService;
        this.benefitCoverageRequestRepository = benefitCoverageRequestRepository;
    }

    /**
     * {@code POST  /benefit-coverage-requests} : Create a new benefitCoverageRequest.
     *
     * @param benefitCoverageRequestDTO the benefitCoverageRequestDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new benefitCoverageRequestDTO, or with status {@code 400 (Bad Request)} if the benefitCoverageRequest has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/benefit-coverage-requests")
    public ResponseEntity<BenefitCoverageRequestDTO> createBenefitCoverageRequest(
        @RequestBody BenefitCoverageRequestDTO benefitCoverageRequestDTO
    ) throws URISyntaxException {
        log.debug("REST request to save BenefitCoverageRequest : {}", benefitCoverageRequestDTO);
        if (benefitCoverageRequestDTO.getBenefitCoverageRequestId() != null) {
            throw new BadRequestAlertException("A new benefitCoverageRequest cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BenefitCoverageRequestDTO result = benefitCoverageRequestService.save(benefitCoverageRequestDTO);
        return ResponseEntity
            .created(new URI("/api/benefit-coverage-requests/" + result.getBenefitCoverageRequestId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getBenefitCoverageRequestId().toString())
            )
            .body(result);
    }

    /**
     * {@code PUT  /benefit-coverage-requests/:benefitCoverageRequestId} : Updates an existing benefitCoverageRequest.
     *
     * @param benefitCoverageRequestId the id of the benefitCoverageRequestDTO to save.
     * @param benefitCoverageRequestDTO the benefitCoverageRequestDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated benefitCoverageRequestDTO,
     * or with status {@code 400 (Bad Request)} if the benefitCoverageRequestDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the benefitCoverageRequestDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/benefit-coverage-requests/{benefitCoverageRequestId}")
    public ResponseEntity<BenefitCoverageRequestDTO> updateBenefitCoverageRequest(
        @PathVariable(value = "benefitCoverageRequestId", required = false) final Long benefitCoverageRequestId,
        @RequestBody BenefitCoverageRequestDTO benefitCoverageRequestDTO
    ) throws URISyntaxException {
        log.debug("REST request to update BenefitCoverageRequest : {}, {}", benefitCoverageRequestId, benefitCoverageRequestDTO);
        if (benefitCoverageRequestDTO.getBenefitCoverageRequestId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(benefitCoverageRequestId, benefitCoverageRequestDTO.getBenefitCoverageRequestId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!benefitCoverageRequestRepository.existsById(benefitCoverageRequestId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BenefitCoverageRequestDTO result = benefitCoverageRequestService.update(benefitCoverageRequestDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    benefitCoverageRequestDTO.getBenefitCoverageRequestId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /benefit-coverage-requests/:benefitCoverageRequestId} : Partial updates given fields of an existing benefitCoverageRequest, field will ignore if it is null
     *
     * @param benefitCoverageRequestId the id of the benefitCoverageRequestDTO to save.
     * @param benefitCoverageRequestDTO the benefitCoverageRequestDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated benefitCoverageRequestDTO,
     * or with status {@code 400 (Bad Request)} if the benefitCoverageRequestDTO is not valid,
     * or with status {@code 404 (Not Found)} if the benefitCoverageRequestDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the benefitCoverageRequestDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/benefit-coverage-requests/{benefitCoverageRequestId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public ResponseEntity<BenefitCoverageRequestDTO> partialUpdateBenefitCoverageRequest(
        @PathVariable(value = "benefitCoverageRequestId", required = false) final Long benefitCoverageRequestId,
        @RequestBody BenefitCoverageRequestDTO benefitCoverageRequestDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update BenefitCoverageRequest partially : {}, {}",
            benefitCoverageRequestId,
            benefitCoverageRequestDTO
        );
        if (benefitCoverageRequestDTO.getBenefitCoverageRequestId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(benefitCoverageRequestId, benefitCoverageRequestDTO.getBenefitCoverageRequestId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!benefitCoverageRequestRepository.existsById(benefitCoverageRequestId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BenefitCoverageRequestDTO> result = benefitCoverageRequestService.partialUpdate(benefitCoverageRequestDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                false,
                ENTITY_NAME,
                benefitCoverageRequestDTO.getBenefitCoverageRequestId().toString()
            )
        );
    }

    /**
     * {@code GET  /benefit-coverage-requests} : get all the benefitCoverageRequests.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of benefitCoverageRequests in body.
     */
    @GetMapping("/benefit-coverage-requests")
    public ResponseEntity<List<BenefitCoverageRequestDTO>> getAllBenefitCoverageRequests(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of BenefitCoverageRequests");
        Page<BenefitCoverageRequestDTO> page = benefitCoverageRequestService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /benefit-coverage-requests/:id} : get the "id" benefitCoverageRequest.
     *
     * @param id the id of the benefitCoverageRequestDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the benefitCoverageRequestDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/benefit-coverage-requests/{id}")
    public ResponseEntity<BenefitCoverageRequestDTO> getBenefitCoverageRequest(@PathVariable Long id) {
        log.debug("REST request to get BenefitCoverageRequest : {}", id);
        Optional<BenefitCoverageRequestDTO> benefitCoverageRequestDTO = benefitCoverageRequestService.findOne(id);
        return ResponseUtil.wrapOrNotFound(benefitCoverageRequestDTO);
    }

    /**
     * {@code DELETE  /benefit-coverage-requests/:id} : delete the "id" benefitCoverageRequest.
     *
     * @param id the id of the benefitCoverageRequestDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/benefit-coverage-requests/{id}")
    public ResponseEntity<Void> deleteBenefitCoverageRequest(@PathVariable Long id) {
        log.debug("REST request to delete BenefitCoverageRequest : {}", id);
        benefitCoverageRequestService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
