package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ClaimsCOB835DetailsRepository;
import com.sunknowledge.dme.rcm.service.ClaimsCOB835DetailsService;
import com.sunknowledge.dme.rcm.service.dto.ClaimsCOB835DetailsDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ClaimsCOB835Details}.
 */
@RestController
@RequestMapping("/api")
public class ClaimsCOB835DetailsResource {

    private final Logger log = LoggerFactory.getLogger(ClaimsCOB835DetailsResource.class);

    private static final String ENTITY_NAME = "claimsClaimsCob835Details";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClaimsCOB835DetailsService claimsCOB835DetailsService;

    private final ClaimsCOB835DetailsRepository claimsCOB835DetailsRepository;

    public ClaimsCOB835DetailsResource(
        ClaimsCOB835DetailsService claimsCOB835DetailsService,
        ClaimsCOB835DetailsRepository claimsCOB835DetailsRepository
    ) {
        this.claimsCOB835DetailsService = claimsCOB835DetailsService;
        this.claimsCOB835DetailsRepository = claimsCOB835DetailsRepository;
    }

    /**
     * {@code POST  /claims-cob-835-details} : Create a new claimsCOB835Details.
     *
     * @param claimsCOB835DetailsDTO the claimsCOB835DetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new claimsCOB835DetailsDTO, or with status {@code 400 (Bad Request)} if the claimsCOB835Details has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/claims-cob-835-details")
    public ResponseEntity<ClaimsCOB835DetailsDTO> createClaimsCOB835Details(
        @Valid @RequestBody ClaimsCOB835DetailsDTO claimsCOB835DetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to save ClaimsCOB835Details : {}", claimsCOB835DetailsDTO);
        if (claimsCOB835DetailsDTO.getClaimCob835DetailId() != null) {
            throw new BadRequestAlertException("A new claimsCOB835Details cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClaimsCOB835DetailsDTO result = claimsCOB835DetailsService.save(claimsCOB835DetailsDTO);
        return ResponseEntity
            .created(new URI("/api/claims-cob-835-details/" + result.getClaimCob835DetailId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getClaimCob835DetailId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /claims-cob-835-details/:claimCob835DetailId} : Updates an existing claimsCOB835Details.
     *
     * @param claimCob835DetailId the id of the claimsCOB835DetailsDTO to save.
     * @param claimsCOB835DetailsDTO the claimsCOB835DetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claimsCOB835DetailsDTO,
     * or with status {@code 400 (Bad Request)} if the claimsCOB835DetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the claimsCOB835DetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/claims-cob-835-details/{claimCob835DetailId}")
    public ResponseEntity<ClaimsCOB835DetailsDTO> updateClaimsCOB835Details(
        @PathVariable(value = "claimCob835DetailId", required = false) final Long claimCob835DetailId,
        @Valid @RequestBody ClaimsCOB835DetailsDTO claimsCOB835DetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ClaimsCOB835Details : {}, {}", claimCob835DetailId, claimsCOB835DetailsDTO);
        if (claimsCOB835DetailsDTO.getClaimCob835DetailId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(claimCob835DetailId, claimsCOB835DetailsDTO.getClaimCob835DetailId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!claimsCOB835DetailsRepository.existsById(claimCob835DetailId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ClaimsCOB835DetailsDTO result = claimsCOB835DetailsService.update(claimsCOB835DetailsDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    claimsCOB835DetailsDTO.getClaimCob835DetailId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /claims-cob-835-details/:claimCob835DetailId} : Partial updates given fields of an existing claimsCOB835Details, field will ignore if it is null
     *
     * @param claimCob835DetailId the id of the claimsCOB835DetailsDTO to save.
     * @param claimsCOB835DetailsDTO the claimsCOB835DetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claimsCOB835DetailsDTO,
     * or with status {@code 400 (Bad Request)} if the claimsCOB835DetailsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the claimsCOB835DetailsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the claimsCOB835DetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/claims-cob-835-details/{claimCob835DetailId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public ResponseEntity<ClaimsCOB835DetailsDTO> partialUpdateClaimsCOB835Details(
        @PathVariable(value = "claimCob835DetailId", required = false) final Long claimCob835DetailId,
        @NotNull @RequestBody ClaimsCOB835DetailsDTO claimsCOB835DetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ClaimsCOB835Details partially : {}, {}", claimCob835DetailId, claimsCOB835DetailsDTO);
        if (claimsCOB835DetailsDTO.getClaimCob835DetailId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(claimCob835DetailId, claimsCOB835DetailsDTO.getClaimCob835DetailId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!claimsCOB835DetailsRepository.existsById(claimCob835DetailId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ClaimsCOB835DetailsDTO> result = claimsCOB835DetailsService.partialUpdate(claimsCOB835DetailsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                false,
                ENTITY_NAME,
                claimsCOB835DetailsDTO.getClaimCob835DetailId().toString()
            )
        );
    }

    /**
     * {@code GET  /claims-cob-835-details} : get all the claimsCOB835Details.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of claimsCOB835Details in body.
     */
    @GetMapping("/claims-cob-835-details")
    public ResponseEntity<List<ClaimsCOB835DetailsDTO>> getAllClaimsCOB835Details(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ClaimsCOB835Details");
        Page<ClaimsCOB835DetailsDTO> page = claimsCOB835DetailsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /claims-cob-835-details/:id} : get the "id" claimsCOB835Details.
     *
     * @param id the id of the claimsCOB835DetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the claimsCOB835DetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/claims-cob-835-details/{id}")
    public ResponseEntity<ClaimsCOB835DetailsDTO> getClaimsCOB835Details(@PathVariable Long id) {
        log.debug("REST request to get ClaimsCOB835Details : {}", id);
        Optional<ClaimsCOB835DetailsDTO> claimsCOB835DetailsDTO = claimsCOB835DetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(claimsCOB835DetailsDTO);
    }

    /**
     * {@code DELETE  /claims-cob-835-details/:id} : delete the "id" claimsCOB835Details.
     *
     * @param id the id of the claimsCOB835DetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/claims-cob-835-details/{id}")
    public ResponseEntity<Void> deleteClaimsCOB835Details(@PathVariable Long id) {
        log.debug("REST request to delete ClaimsCOB835Details : {}", id);
        claimsCOB835DetailsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
