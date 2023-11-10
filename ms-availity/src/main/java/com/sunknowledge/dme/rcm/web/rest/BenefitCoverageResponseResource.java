package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.BenefitCoverageResponseRepository;
import com.sunknowledge.dme.rcm.service.BenefitCoverageResponseService;
import com.sunknowledge.dme.rcm.service.dto.BenefitCoverageResponseDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.BenefitCoverageResponse}.
 */
@RestController
@RequestMapping("/api")
public class BenefitCoverageResponseResource {

    private final Logger log = LoggerFactory.getLogger(BenefitCoverageResponseResource.class);

    private static final String ENTITY_NAME = "availityBenefitCoverageResponse";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BenefitCoverageResponseService benefitCoverageResponseService;

    private final BenefitCoverageResponseRepository benefitCoverageResponseRepository;

    public BenefitCoverageResponseResource(
        BenefitCoverageResponseService benefitCoverageResponseService,
        BenefitCoverageResponseRepository benefitCoverageResponseRepository
    ) {
        this.benefitCoverageResponseService = benefitCoverageResponseService;
        this.benefitCoverageResponseRepository = benefitCoverageResponseRepository;
    }

    /**
     * {@code POST  /benefit-coverage-responses} : Create a new benefitCoverageResponse.
     *
     * @param benefitCoverageResponseDTO the benefitCoverageResponseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new benefitCoverageResponseDTO, or with status {@code 400 (Bad Request)} if the benefitCoverageResponse has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/benefit-coverage-responses")
    public ResponseEntity<BenefitCoverageResponseDTO> createBenefitCoverageResponse(
        @RequestBody BenefitCoverageResponseDTO benefitCoverageResponseDTO
    ) throws URISyntaxException {
        log.debug("REST request to save BenefitCoverageResponse : {}", benefitCoverageResponseDTO);
        if (benefitCoverageResponseDTO.getBenefitCoverageResponseId() != null) {
            throw new BadRequestAlertException("A new benefitCoverageResponse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BenefitCoverageResponseDTO result = benefitCoverageResponseService.save(benefitCoverageResponseDTO);
        return ResponseEntity
            .created(new URI("/api/benefit-coverage-responses/" + result.getBenefitCoverageResponseId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getBenefitCoverageResponseId().toString())
            )
            .body(result);
    }

    /**
     * {@code PUT  /benefit-coverage-responses/:benefitCoverageResponseId} : Updates an existing benefitCoverageResponse.
     *
     * @param benefitCoverageResponseId the id of the benefitCoverageResponseDTO to save.
     * @param benefitCoverageResponseDTO the benefitCoverageResponseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated benefitCoverageResponseDTO,
     * or with status {@code 400 (Bad Request)} if the benefitCoverageResponseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the benefitCoverageResponseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/benefit-coverage-responses/{benefitCoverageResponseId}")
    public ResponseEntity<BenefitCoverageResponseDTO> updateBenefitCoverageResponse(
        @PathVariable(value = "benefitCoverageResponseId", required = false) final Long benefitCoverageResponseId,
        @RequestBody BenefitCoverageResponseDTO benefitCoverageResponseDTO
    ) throws URISyntaxException {
        log.debug("REST request to update BenefitCoverageResponse : {}, {}", benefitCoverageResponseId, benefitCoverageResponseDTO);
        if (benefitCoverageResponseDTO.getBenefitCoverageResponseId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(benefitCoverageResponseId, benefitCoverageResponseDTO.getBenefitCoverageResponseId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!benefitCoverageResponseRepository.existsById(benefitCoverageResponseId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BenefitCoverageResponseDTO result = benefitCoverageResponseService.update(benefitCoverageResponseDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    benefitCoverageResponseDTO.getBenefitCoverageResponseId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /benefit-coverage-responses/:benefitCoverageResponseId} : Partial updates given fields of an existing benefitCoverageResponse, field will ignore if it is null
     *
     * @param benefitCoverageResponseId the id of the benefitCoverageResponseDTO to save.
     * @param benefitCoverageResponseDTO the benefitCoverageResponseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated benefitCoverageResponseDTO,
     * or with status {@code 400 (Bad Request)} if the benefitCoverageResponseDTO is not valid,
     * or with status {@code 404 (Not Found)} if the benefitCoverageResponseDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the benefitCoverageResponseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/benefit-coverage-responses/{benefitCoverageResponseId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public ResponseEntity<BenefitCoverageResponseDTO> partialUpdateBenefitCoverageResponse(
        @PathVariable(value = "benefitCoverageResponseId", required = false) final Long benefitCoverageResponseId,
        @RequestBody BenefitCoverageResponseDTO benefitCoverageResponseDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update BenefitCoverageResponse partially : {}, {}",
            benefitCoverageResponseId,
            benefitCoverageResponseDTO
        );
        if (benefitCoverageResponseDTO.getBenefitCoverageResponseId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(benefitCoverageResponseId, benefitCoverageResponseDTO.getBenefitCoverageResponseId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!benefitCoverageResponseRepository.existsById(benefitCoverageResponseId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BenefitCoverageResponseDTO> result = benefitCoverageResponseService.partialUpdate(benefitCoverageResponseDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                false,
                ENTITY_NAME,
                benefitCoverageResponseDTO.getBenefitCoverageResponseId().toString()
            )
        );
    }

    /**
     * {@code GET  /benefit-coverage-responses} : get all the benefitCoverageResponses.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of benefitCoverageResponses in body.
     */
    @GetMapping("/benefit-coverage-responses")
    public ResponseEntity<List<BenefitCoverageResponseDTO>> getAllBenefitCoverageResponses(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of BenefitCoverageResponses");
        Page<BenefitCoverageResponseDTO> page = benefitCoverageResponseService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /benefit-coverage-responses/:id} : get the "id" benefitCoverageResponse.
     *
     * @param id the id of the benefitCoverageResponseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the benefitCoverageResponseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/benefit-coverage-responses/{id}")
    public ResponseEntity<BenefitCoverageResponseDTO> getBenefitCoverageResponse(@PathVariable Long id) {
        log.debug("REST request to get BenefitCoverageResponse : {}", id);
        Optional<BenefitCoverageResponseDTO> benefitCoverageResponseDTO = benefitCoverageResponseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(benefitCoverageResponseDTO);
    }

    /**
     * {@code DELETE  /benefit-coverage-responses/:id} : delete the "id" benefitCoverageResponse.
     *
     * @param id the id of the benefitCoverageResponseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/benefit-coverage-responses/{id}")
    public ResponseEntity<Void> deleteBenefitCoverageResponse(@PathVariable Long id) {
        log.debug("REST request to delete BenefitCoverageResponse : {}", id);
        benefitCoverageResponseService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
