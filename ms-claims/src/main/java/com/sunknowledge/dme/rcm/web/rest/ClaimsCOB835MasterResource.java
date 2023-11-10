package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ClaimsCOB835MasterRepository;
import com.sunknowledge.dme.rcm.service.ClaimsCOB835MasterService;
import com.sunknowledge.dme.rcm.service.dto.ClaimsCOB835MasterDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ClaimsCOB835Master}.
 */
@RestController
@RequestMapping("/api")
public class ClaimsCOB835MasterResource {

    private final Logger log = LoggerFactory.getLogger(ClaimsCOB835MasterResource.class);

    private static final String ENTITY_NAME = "claimsClaimsCob835Master";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClaimsCOB835MasterService claimsCOB835MasterService;

    private final ClaimsCOB835MasterRepository claimsCOB835MasterRepository;

    public ClaimsCOB835MasterResource(
        ClaimsCOB835MasterService claimsCOB835MasterService,
        ClaimsCOB835MasterRepository claimsCOB835MasterRepository
    ) {
        this.claimsCOB835MasterService = claimsCOB835MasterService;
        this.claimsCOB835MasterRepository = claimsCOB835MasterRepository;
    }

    /**
     * {@code POST  /claims-cob-835-masters} : Create a new claimsCOB835Master.
     *
     * @param claimsCOB835MasterDTO the claimsCOB835MasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new claimsCOB835MasterDTO, or with status {@code 400 (Bad Request)} if the claimsCOB835Master has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/claims-cob-835-masters")
    public ResponseEntity<ClaimsCOB835MasterDTO> createClaimsCOB835Master(@Valid @RequestBody ClaimsCOB835MasterDTO claimsCOB835MasterDTO)
        throws URISyntaxException {
        log.debug("REST request to save ClaimsCOB835Master : {}", claimsCOB835MasterDTO);
        if (claimsCOB835MasterDTO.getClaimCob835MasterId() != null) {
            throw new BadRequestAlertException("A new claimsCOB835Master cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClaimsCOB835MasterDTO result = claimsCOB835MasterService.save(claimsCOB835MasterDTO);
        return ResponseEntity
            .created(new URI("/api/claims-cob-835-masters/" + result.getClaimCob835MasterId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getClaimCob835MasterId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /claims-cob-835-masters/:claimCob835MasterId} : Updates an existing claimsCOB835Master.
     *
     * @param claimCob835MasterId the id of the claimsCOB835MasterDTO to save.
     * @param claimsCOB835MasterDTO the claimsCOB835MasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claimsCOB835MasterDTO,
     * or with status {@code 400 (Bad Request)} if the claimsCOB835MasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the claimsCOB835MasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/claims-cob-835-masters/{claimCob835MasterId}")
    public ResponseEntity<ClaimsCOB835MasterDTO> updateClaimsCOB835Master(
        @PathVariable(value = "claimCob835MasterId", required = false) final Long claimCob835MasterId,
        @Valid @RequestBody ClaimsCOB835MasterDTO claimsCOB835MasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ClaimsCOB835Master : {}, {}", claimCob835MasterId, claimsCOB835MasterDTO);
        if (claimsCOB835MasterDTO.getClaimCob835MasterId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(claimCob835MasterId, claimsCOB835MasterDTO.getClaimCob835MasterId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!claimsCOB835MasterRepository.existsById(claimCob835MasterId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ClaimsCOB835MasterDTO result = claimsCOB835MasterService.update(claimsCOB835MasterDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    claimsCOB835MasterDTO.getClaimCob835MasterId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /claims-cob-835-masters/:claimCob835MasterId} : Partial updates given fields of an existing claimsCOB835Master, field will ignore if it is null
     *
     * @param claimCob835MasterId the id of the claimsCOB835MasterDTO to save.
     * @param claimsCOB835MasterDTO the claimsCOB835MasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claimsCOB835MasterDTO,
     * or with status {@code 400 (Bad Request)} if the claimsCOB835MasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the claimsCOB835MasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the claimsCOB835MasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/claims-cob-835-masters/{claimCob835MasterId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public ResponseEntity<ClaimsCOB835MasterDTO> partialUpdateClaimsCOB835Master(
        @PathVariable(value = "claimCob835MasterId", required = false) final Long claimCob835MasterId,
        @NotNull @RequestBody ClaimsCOB835MasterDTO claimsCOB835MasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ClaimsCOB835Master partially : {}, {}", claimCob835MasterId, claimsCOB835MasterDTO);
        if (claimsCOB835MasterDTO.getClaimCob835MasterId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(claimCob835MasterId, claimsCOB835MasterDTO.getClaimCob835MasterId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!claimsCOB835MasterRepository.existsById(claimCob835MasterId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ClaimsCOB835MasterDTO> result = claimsCOB835MasterService.partialUpdate(claimsCOB835MasterDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                false,
                ENTITY_NAME,
                claimsCOB835MasterDTO.getClaimCob835MasterId().toString()
            )
        );
    }

    /**
     * {@code GET  /claims-cob-835-masters} : get all the claimsCOB835Masters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of claimsCOB835Masters in body.
     */
    @GetMapping("/claims-cob-835-masters")
    public ResponseEntity<List<ClaimsCOB835MasterDTO>> getAllClaimsCOB835Masters(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ClaimsCOB835Masters");
        Page<ClaimsCOB835MasterDTO> page = claimsCOB835MasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /claims-cob-835-masters/:id} : get the "id" claimsCOB835Master.
     *
     * @param id the id of the claimsCOB835MasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the claimsCOB835MasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/claims-cob-835-masters/{id}")
    public ResponseEntity<ClaimsCOB835MasterDTO> getClaimsCOB835Master(@PathVariable Long id) {
        log.debug("REST request to get ClaimsCOB835Master : {}", id);
        Optional<ClaimsCOB835MasterDTO> claimsCOB835MasterDTO = claimsCOB835MasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(claimsCOB835MasterDTO);
    }

    /**
     * {@code DELETE  /claims-cob-835-masters/:id} : delete the "id" claimsCOB835Master.
     *
     * @param id the id of the claimsCOB835MasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/claims-cob-835-masters/{id}")
    public ResponseEntity<Void> deleteClaimsCOB835Master(@PathVariable Long id) {
        log.debug("REST request to delete ClaimsCOB835Master : {}", id);
        claimsCOB835MasterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
