package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ClaimsStatus277DetailsRepository;
import com.sunknowledge.dme.rcm.service.ClaimsStatus277DetailsService;
import com.sunknowledge.dme.rcm.service.dto.ClaimsStatus277DetailsDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ClaimsStatus277Details}.
 */
@RestController
@RequestMapping("/api")
public class ClaimsStatus277DetailsResource {

    private final Logger log = LoggerFactory.getLogger(ClaimsStatus277DetailsResource.class);

    private static final String ENTITY_NAME = "claimsClaimsStatus277Details";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClaimsStatus277DetailsService claimsStatus277DetailsService;

    private final ClaimsStatus277DetailsRepository claimsStatus277DetailsRepository;

    public ClaimsStatus277DetailsResource(
        ClaimsStatus277DetailsService claimsStatus277DetailsService,
        ClaimsStatus277DetailsRepository claimsStatus277DetailsRepository
    ) {
        this.claimsStatus277DetailsService = claimsStatus277DetailsService;
        this.claimsStatus277DetailsRepository = claimsStatus277DetailsRepository;
    }

    /**
     * {@code POST  /claims-status-277-details} : Create a new claimsStatus277Details.
     *
     * @param claimsStatus277DetailsDTO the claimsStatus277DetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new claimsStatus277DetailsDTO, or with status {@code 400 (Bad Request)} if the claimsStatus277Details has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/claims-status-277-details")
    public ResponseEntity<ClaimsStatus277DetailsDTO> createClaimsStatus277Details(
        @Valid @RequestBody ClaimsStatus277DetailsDTO claimsStatus277DetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to save ClaimsStatus277Details : {}", claimsStatus277DetailsDTO);
        if (claimsStatus277DetailsDTO.getClaimStatus277DetailId() != null) {
            throw new BadRequestAlertException("A new claimsStatus277Details cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClaimsStatus277DetailsDTO result = claimsStatus277DetailsService.save(claimsStatus277DetailsDTO);
        return ResponseEntity
            .created(new URI("/api/claims-status-277-details/" + result.getClaimStatus277DetailId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getClaimStatus277DetailId().toString())
            )
            .body(result);
    }

    /**
     * {@code PUT  /claims-status-277-details/:claimStatus277DetailId} : Updates an existing claimsStatus277Details.
     *
     * @param claimStatus277DetailId the id of the claimsStatus277DetailsDTO to save.
     * @param claimsStatus277DetailsDTO the claimsStatus277DetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claimsStatus277DetailsDTO,
     * or with status {@code 400 (Bad Request)} if the claimsStatus277DetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the claimsStatus277DetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/claims-status-277-details/{claimStatus277DetailId}")
    public ResponseEntity<ClaimsStatus277DetailsDTO> updateClaimsStatus277Details(
        @PathVariable(value = "claimStatus277DetailId", required = false) final Long claimStatus277DetailId,
        @Valid @RequestBody ClaimsStatus277DetailsDTO claimsStatus277DetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ClaimsStatus277Details : {}, {}", claimStatus277DetailId, claimsStatus277DetailsDTO);
        if (claimsStatus277DetailsDTO.getClaimStatus277DetailId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(claimStatus277DetailId, claimsStatus277DetailsDTO.getClaimStatus277DetailId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!claimsStatus277DetailsRepository.existsById(claimStatus277DetailId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ClaimsStatus277DetailsDTO result = claimsStatus277DetailsService.update(claimsStatus277DetailsDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    claimsStatus277DetailsDTO.getClaimStatus277DetailId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /claims-status-277-details/:claimStatus277DetailId} : Partial updates given fields of an existing claimsStatus277Details, field will ignore if it is null
     *
     * @param claimStatus277DetailId the id of the claimsStatus277DetailsDTO to save.
     * @param claimsStatus277DetailsDTO the claimsStatus277DetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claimsStatus277DetailsDTO,
     * or with status {@code 400 (Bad Request)} if the claimsStatus277DetailsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the claimsStatus277DetailsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the claimsStatus277DetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/claims-status-277-details/{claimStatus277DetailId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public ResponseEntity<ClaimsStatus277DetailsDTO> partialUpdateClaimsStatus277Details(
        @PathVariable(value = "claimStatus277DetailId", required = false) final Long claimStatus277DetailId,
        @NotNull @RequestBody ClaimsStatus277DetailsDTO claimsStatus277DetailsDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update ClaimsStatus277Details partially : {}, {}",
            claimStatus277DetailId,
            claimsStatus277DetailsDTO
        );
        if (claimsStatus277DetailsDTO.getClaimStatus277DetailId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(claimStatus277DetailId, claimsStatus277DetailsDTO.getClaimStatus277DetailId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!claimsStatus277DetailsRepository.existsById(claimStatus277DetailId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ClaimsStatus277DetailsDTO> result = claimsStatus277DetailsService.partialUpdate(claimsStatus277DetailsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                false,
                ENTITY_NAME,
                claimsStatus277DetailsDTO.getClaimStatus277DetailId().toString()
            )
        );
    }

    /**
     * {@code GET  /claims-status-277-details} : get all the claimsStatus277Details.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of claimsStatus277Details in body.
     */
    @GetMapping("/claims-status-277-details")
    public ResponseEntity<List<ClaimsStatus277DetailsDTO>> getAllClaimsStatus277Details(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ClaimsStatus277Details");
        Page<ClaimsStatus277DetailsDTO> page = claimsStatus277DetailsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /claims-status-277-details/:id} : get the "id" claimsStatus277Details.
     *
     * @param id the id of the claimsStatus277DetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the claimsStatus277DetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/claims-status-277-details/{id}")
    public ResponseEntity<ClaimsStatus277DetailsDTO> getClaimsStatus277Details(@PathVariable Long id) {
        log.debug("REST request to get ClaimsStatus277Details : {}", id);
        Optional<ClaimsStatus277DetailsDTO> claimsStatus277DetailsDTO = claimsStatus277DetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(claimsStatus277DetailsDTO);
    }

    /**
     * {@code DELETE  /claims-status-277-details/:id} : delete the "id" claimsStatus277Details.
     *
     * @param id the id of the claimsStatus277DetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/claims-status-277-details/{id}")
    public ResponseEntity<Void> deleteClaimsStatus277Details(@PathVariable Long id) {
        log.debug("REST request to delete ClaimsStatus277Details : {}", id);
        claimsStatus277DetailsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
