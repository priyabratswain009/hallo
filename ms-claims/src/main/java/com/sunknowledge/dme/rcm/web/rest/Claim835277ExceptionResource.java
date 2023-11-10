package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.Claim835277ExceptionRepository;
import com.sunknowledge.dme.rcm.service.Claim835277ExceptionService;
import com.sunknowledge.dme.rcm.service.dto.Claim835277ExceptionDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.Claim835277Exception}.
 */
@RestController
@RequestMapping("/api")
public class Claim835277ExceptionResource {

    private final Logger log = LoggerFactory.getLogger(Claim835277ExceptionResource.class);

    private static final String ENTITY_NAME = "claimsClaim835277Exception";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final Claim835277ExceptionService claim835277ExceptionService;

    private final Claim835277ExceptionRepository claim835277ExceptionRepository;

    public Claim835277ExceptionResource(
        Claim835277ExceptionService claim835277ExceptionService,
        Claim835277ExceptionRepository claim835277ExceptionRepository
    ) {
        this.claim835277ExceptionService = claim835277ExceptionService;
        this.claim835277ExceptionRepository = claim835277ExceptionRepository;
    }

    /**
     * {@code POST  /claim-835277-exceptions} : Create a new claim835277Exception.
     *
     * @param claim835277ExceptionDTO the claim835277ExceptionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new claim835277ExceptionDTO, or with status {@code 400 (Bad Request)} if the claim835277Exception has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/claim-835277-exceptions")
    public ResponseEntity<Claim835277ExceptionDTO> createClaim835277Exception(
        @Valid @RequestBody Claim835277ExceptionDTO claim835277ExceptionDTO
    ) throws URISyntaxException {
        log.debug("REST request to save Claim835277Exception : {}", claim835277ExceptionDTO);
        if (claim835277ExceptionDTO.getExceptionId() != null) {
            throw new BadRequestAlertException("A new claim835277Exception cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Claim835277ExceptionDTO result = claim835277ExceptionService.save(claim835277ExceptionDTO);
        return ResponseEntity
            .created(new URI("/api/claim-835277-exceptions/" + result.getExceptionId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getExceptionId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /claim-835277-exceptions/:exceptionId} : Updates an existing claim835277Exception.
     *
     * @param exceptionId the id of the claim835277ExceptionDTO to save.
     * @param claim835277ExceptionDTO the claim835277ExceptionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claim835277ExceptionDTO,
     * or with status {@code 400 (Bad Request)} if the claim835277ExceptionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the claim835277ExceptionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/claim-835277-exceptions/{exceptionId}")
    public ResponseEntity<Claim835277ExceptionDTO> updateClaim835277Exception(
        @PathVariable(value = "exceptionId", required = false) final Long exceptionId,
        @Valid @RequestBody Claim835277ExceptionDTO claim835277ExceptionDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Claim835277Exception : {}, {}", exceptionId, claim835277ExceptionDTO);
        if (claim835277ExceptionDTO.getExceptionId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(exceptionId, claim835277ExceptionDTO.getExceptionId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!claim835277ExceptionRepository.existsById(exceptionId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Claim835277ExceptionDTO result = claim835277ExceptionService.update(claim835277ExceptionDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, claim835277ExceptionDTO.getExceptionId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /claim-835277-exceptions/:exceptionId} : Partial updates given fields of an existing claim835277Exception, field will ignore if it is null
     *
     * @param exceptionId the id of the claim835277ExceptionDTO to save.
     * @param claim835277ExceptionDTO the claim835277ExceptionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claim835277ExceptionDTO,
     * or with status {@code 400 (Bad Request)} if the claim835277ExceptionDTO is not valid,
     * or with status {@code 404 (Not Found)} if the claim835277ExceptionDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the claim835277ExceptionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/claim-835277-exceptions/{exceptionId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Claim835277ExceptionDTO> partialUpdateClaim835277Exception(
        @PathVariable(value = "exceptionId", required = false) final Long exceptionId,
        @NotNull @RequestBody Claim835277ExceptionDTO claim835277ExceptionDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Claim835277Exception partially : {}, {}", exceptionId, claim835277ExceptionDTO);
        if (claim835277ExceptionDTO.getExceptionId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(exceptionId, claim835277ExceptionDTO.getExceptionId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!claim835277ExceptionRepository.existsById(exceptionId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Claim835277ExceptionDTO> result = claim835277ExceptionService.partialUpdate(claim835277ExceptionDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, claim835277ExceptionDTO.getExceptionId().toString())
        );
    }

    /**
     * {@code GET  /claim-835277-exceptions} : get all the claim835277Exceptions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of claim835277Exceptions in body.
     */
    @GetMapping("/claim-835277-exceptions")
    public ResponseEntity<List<Claim835277ExceptionDTO>> getAllClaim835277Exceptions(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of Claim835277Exceptions");
        Page<Claim835277ExceptionDTO> page = claim835277ExceptionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /claim-835277-exceptions/:id} : get the "id" claim835277Exception.
     *
     * @param id the id of the claim835277ExceptionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the claim835277ExceptionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/claim-835277-exceptions/{id}")
    public ResponseEntity<Claim835277ExceptionDTO> getClaim835277Exception(@PathVariable Long id) {
        log.debug("REST request to get Claim835277Exception : {}", id);
        Optional<Claim835277ExceptionDTO> claim835277ExceptionDTO = claim835277ExceptionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(claim835277ExceptionDTO);
    }

    /**
     * {@code DELETE  /claim-835277-exceptions/:id} : delete the "id" claim835277Exception.
     *
     * @param id the id of the claim835277ExceptionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/claim-835277-exceptions/{id}")
    public ResponseEntity<Void> deleteClaim835277Exception(@PathVariable Long id) {
        log.debug("REST request to delete Claim835277Exception : {}", id);
        claim835277ExceptionService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
