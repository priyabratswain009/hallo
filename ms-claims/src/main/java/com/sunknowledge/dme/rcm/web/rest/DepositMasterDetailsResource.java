package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.DepositMasterDetailsRepository;
import com.sunknowledge.dme.rcm.service.DepositMasterDetailsService;
import com.sunknowledge.dme.rcm.service.dto.DepositMasterDetailsDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.DepositMasterDetails}.
 */
@RestController
@RequestMapping("/api")
public class DepositMasterDetailsResource {

    private final Logger log = LoggerFactory.getLogger(DepositMasterDetailsResource.class);

    private static final String ENTITY_NAME = "claimsDepositMasterDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DepositMasterDetailsService depositMasterDetailsService;

    private final DepositMasterDetailsRepository depositMasterDetailsRepository;

    public DepositMasterDetailsResource(
        DepositMasterDetailsService depositMasterDetailsService,
        DepositMasterDetailsRepository depositMasterDetailsRepository
    ) {
        this.depositMasterDetailsService = depositMasterDetailsService;
        this.depositMasterDetailsRepository = depositMasterDetailsRepository;
    }

    /**
     * {@code POST  /deposit-master-details} : Create a new depositMasterDetails.
     *
     * @param depositMasterDetailsDTO the depositMasterDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new depositMasterDetailsDTO, or with status {@code 400 (Bad Request)} if the depositMasterDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/deposit-master-details")
    public ResponseEntity<DepositMasterDetailsDTO> createDepositMasterDetails(
        @Valid @RequestBody DepositMasterDetailsDTO depositMasterDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to save DepositMasterDetails : {}", depositMasterDetailsDTO);
        if (depositMasterDetailsDTO.getDepositId() != null) {
            throw new BadRequestAlertException("A new depositMasterDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DepositMasterDetailsDTO result = depositMasterDetailsService.save(depositMasterDetailsDTO);
        return ResponseEntity
            .created(new URI("/api/deposit-master-details/" + result.getDepositId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getDepositId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /deposit-master-details/:depositId} : Updates an existing depositMasterDetails.
     *
     * @param depositId the id of the depositMasterDetailsDTO to save.
     * @param depositMasterDetailsDTO the depositMasterDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated depositMasterDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the depositMasterDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the depositMasterDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/deposit-master-details/{depositId}")
    public ResponseEntity<DepositMasterDetailsDTO> updateDepositMasterDetails(
        @PathVariable(value = "depositId", required = false) final Long depositId,
        @Valid @RequestBody DepositMasterDetailsDTO depositMasterDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DepositMasterDetails : {}, {}", depositId, depositMasterDetailsDTO);
        if (depositMasterDetailsDTO.getDepositId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(depositId, depositMasterDetailsDTO.getDepositId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!depositMasterDetailsRepository.existsById(depositId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DepositMasterDetailsDTO result = depositMasterDetailsService.update(depositMasterDetailsDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, depositMasterDetailsDTO.getDepositId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /deposit-master-details/:depositId} : Partial updates given fields of an existing depositMasterDetails, field will ignore if it is null
     *
     * @param depositId the id of the depositMasterDetailsDTO to save.
     * @param depositMasterDetailsDTO the depositMasterDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated depositMasterDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the depositMasterDetailsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the depositMasterDetailsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the depositMasterDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/deposit-master-details/{depositId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DepositMasterDetailsDTO> partialUpdateDepositMasterDetails(
        @PathVariable(value = "depositId", required = false) final Long depositId,
        @NotNull @RequestBody DepositMasterDetailsDTO depositMasterDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DepositMasterDetails partially : {}, {}", depositId, depositMasterDetailsDTO);
        if (depositMasterDetailsDTO.getDepositId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(depositId, depositMasterDetailsDTO.getDepositId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!depositMasterDetailsRepository.existsById(depositId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DepositMasterDetailsDTO> result = depositMasterDetailsService.partialUpdate(depositMasterDetailsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, depositMasterDetailsDTO.getDepositId().toString())
        );
    }

    /**
     * {@code GET  /deposit-master-details} : get all the depositMasterDetails.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of depositMasterDetails in body.
     */
    @GetMapping("/deposit-master-details")
    public ResponseEntity<List<DepositMasterDetailsDTO>> getAllDepositMasterDetails(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of DepositMasterDetails");
        Page<DepositMasterDetailsDTO> page = depositMasterDetailsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /deposit-master-details/:id} : get the "id" depositMasterDetails.
     *
     * @param id the id of the depositMasterDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the depositMasterDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/deposit-master-details/{id}")
    public ResponseEntity<DepositMasterDetailsDTO> getDepositMasterDetails(@PathVariable Long id) {
        log.debug("REST request to get DepositMasterDetails : {}", id);
        Optional<DepositMasterDetailsDTO> depositMasterDetailsDTO = depositMasterDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(depositMasterDetailsDTO);
    }

    /**
     * {@code DELETE  /deposit-master-details/:id} : delete the "id" depositMasterDetails.
     *
     * @param id the id of the depositMasterDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/deposit-master-details/{id}")
    public ResponseEntity<Void> deleteDepositMasterDetails(@PathVariable Long id) {
        log.debug("REST request to delete DepositMasterDetails : {}", id);
        depositMasterDetailsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
