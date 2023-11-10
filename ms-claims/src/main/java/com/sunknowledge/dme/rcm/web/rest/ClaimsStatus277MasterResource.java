package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ClaimsStatus277MasterRepository;
import com.sunknowledge.dme.rcm.service.ClaimsStatus277MasterService;
import com.sunknowledge.dme.rcm.service.dto.ClaimsStatus277MasterDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ClaimsStatus277Master}.
 */
@RestController
@RequestMapping("/api")
public class ClaimsStatus277MasterResource {

    private final Logger log = LoggerFactory.getLogger(ClaimsStatus277MasterResource.class);

    private static final String ENTITY_NAME = "claimsClaimsStatus277Master";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClaimsStatus277MasterService claimsStatus277MasterService;

    private final ClaimsStatus277MasterRepository claimsStatus277MasterRepository;

    public ClaimsStatus277MasterResource(
        ClaimsStatus277MasterService claimsStatus277MasterService,
        ClaimsStatus277MasterRepository claimsStatus277MasterRepository
    ) {
        this.claimsStatus277MasterService = claimsStatus277MasterService;
        this.claimsStatus277MasterRepository = claimsStatus277MasterRepository;
    }

    /**
     * {@code POST  /claims-status-277-masters} : Create a new claimsStatus277Master.
     *
     * @param claimsStatus277MasterDTO the claimsStatus277MasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new claimsStatus277MasterDTO, or with status {@code 400 (Bad Request)} if the claimsStatus277Master has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/claims-status-277-masters")
    public ResponseEntity<ClaimsStatus277MasterDTO> createClaimsStatus277Master(
        @Valid @RequestBody ClaimsStatus277MasterDTO claimsStatus277MasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to save ClaimsStatus277Master : {}", claimsStatus277MasterDTO);
        if (claimsStatus277MasterDTO.getClaimStatus277MasterId() != null) {
            throw new BadRequestAlertException("A new claimsStatus277Master cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClaimsStatus277MasterDTO result = claimsStatus277MasterService.save(claimsStatus277MasterDTO);
        return ResponseEntity
            .created(new URI("/api/claims-status-277-masters/" + result.getClaimStatus277MasterId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getClaimStatus277MasterId().toString())
            )
            .body(result);
    }

    /**
     * {@code PUT  /claims-status-277-masters/:claimStatus277MasterId} : Updates an existing claimsStatus277Master.
     *
     * @param claimStatus277MasterId the id of the claimsStatus277MasterDTO to save.
     * @param claimsStatus277MasterDTO the claimsStatus277MasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claimsStatus277MasterDTO,
     * or with status {@code 400 (Bad Request)} if the claimsStatus277MasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the claimsStatus277MasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/claims-status-277-masters/{claimStatus277MasterId}")
    public ResponseEntity<ClaimsStatus277MasterDTO> updateClaimsStatus277Master(
        @PathVariable(value = "claimStatus277MasterId", required = false) final Long claimStatus277MasterId,
        @Valid @RequestBody ClaimsStatus277MasterDTO claimsStatus277MasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ClaimsStatus277Master : {}, {}", claimStatus277MasterId, claimsStatus277MasterDTO);
        if (claimsStatus277MasterDTO.getClaimStatus277MasterId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(claimStatus277MasterId, claimsStatus277MasterDTO.getClaimStatus277MasterId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!claimsStatus277MasterRepository.existsById(claimStatus277MasterId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ClaimsStatus277MasterDTO result = claimsStatus277MasterService.update(claimsStatus277MasterDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    claimsStatus277MasterDTO.getClaimStatus277MasterId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /claims-status-277-masters/:claimStatus277MasterId} : Partial updates given fields of an existing claimsStatus277Master, field will ignore if it is null
     *
     * @param claimStatus277MasterId the id of the claimsStatus277MasterDTO to save.
     * @param claimsStatus277MasterDTO the claimsStatus277MasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claimsStatus277MasterDTO,
     * or with status {@code 400 (Bad Request)} if the claimsStatus277MasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the claimsStatus277MasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the claimsStatus277MasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/claims-status-277-masters/{claimStatus277MasterId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public ResponseEntity<ClaimsStatus277MasterDTO> partialUpdateClaimsStatus277Master(
        @PathVariable(value = "claimStatus277MasterId", required = false) final Long claimStatus277MasterId,
        @NotNull @RequestBody ClaimsStatus277MasterDTO claimsStatus277MasterDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update ClaimsStatus277Master partially : {}, {}",
            claimStatus277MasterId,
            claimsStatus277MasterDTO
        );
        if (claimsStatus277MasterDTO.getClaimStatus277MasterId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(claimStatus277MasterId, claimsStatus277MasterDTO.getClaimStatus277MasterId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!claimsStatus277MasterRepository.existsById(claimStatus277MasterId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ClaimsStatus277MasterDTO> result = claimsStatus277MasterService.partialUpdate(claimsStatus277MasterDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                false,
                ENTITY_NAME,
                claimsStatus277MasterDTO.getClaimStatus277MasterId().toString()
            )
        );
    }

    /**
     * {@code GET  /claims-status-277-masters} : get all the claimsStatus277Masters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of claimsStatus277Masters in body.
     */
    @GetMapping("/claims-status-277-masters")
    public ResponseEntity<List<ClaimsStatus277MasterDTO>> getAllClaimsStatus277Masters(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ClaimsStatus277Masters");
        Page<ClaimsStatus277MasterDTO> page = claimsStatus277MasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /claims-status-277-masters/:id} : get the "id" claimsStatus277Master.
     *
     * @param id the id of the claimsStatus277MasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the claimsStatus277MasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/claims-status-277-masters/{id}")
    public ResponseEntity<ClaimsStatus277MasterDTO> getClaimsStatus277Master(@PathVariable Long id) {
        log.debug("REST request to get ClaimsStatus277Master : {}", id);
        Optional<ClaimsStatus277MasterDTO> claimsStatus277MasterDTO = claimsStatus277MasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(claimsStatus277MasterDTO);
    }

    /**
     * {@code DELETE  /claims-status-277-masters/:id} : delete the "id" claimsStatus277Master.
     *
     * @param id the id of the claimsStatus277MasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/claims-status-277-masters/{id}")
    public ResponseEntity<Void> deleteClaimsStatus277Master(@PathVariable Long id) {
        log.debug("REST request to delete ClaimsStatus277Master : {}", id);
        claimsStatus277MasterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
