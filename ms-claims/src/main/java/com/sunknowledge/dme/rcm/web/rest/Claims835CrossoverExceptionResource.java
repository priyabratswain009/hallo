package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.Claims835CrossoverExceptionRepository;
import com.sunknowledge.dme.rcm.service.Claims835CrossoverExceptionService;
import com.sunknowledge.dme.rcm.service.dto.Claims835CrossoverExceptionDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.Claims835CrossoverException}.
 */
@RestController
@RequestMapping("/api")
public class Claims835CrossoverExceptionResource {

    private final Logger log = LoggerFactory.getLogger(Claims835CrossoverExceptionResource.class);

    private static final String ENTITY_NAME = "claimsClaims835CrossoverException";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final Claims835CrossoverExceptionService claims835CrossoverExceptionService;

    private final Claims835CrossoverExceptionRepository claims835CrossoverExceptionRepository;

    public Claims835CrossoverExceptionResource(
        Claims835CrossoverExceptionService claims835CrossoverExceptionService,
        Claims835CrossoverExceptionRepository claims835CrossoverExceptionRepository
    ) {
        this.claims835CrossoverExceptionService = claims835CrossoverExceptionService;
        this.claims835CrossoverExceptionRepository = claims835CrossoverExceptionRepository;
    }

    /**
     * {@code POST  /claims-835-crossover-exceptions} : Create a new claims835CrossoverException.
     *
     * @param claims835CrossoverExceptionDTO the claims835CrossoverExceptionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new claims835CrossoverExceptionDTO, or with status {@code 400 (Bad Request)} if the claims835CrossoverException has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/claims-835-crossover-exceptions")
    public ResponseEntity<Claims835CrossoverExceptionDTO> createClaims835CrossoverException(
        @Valid @RequestBody Claims835CrossoverExceptionDTO claims835CrossoverExceptionDTO
    ) throws URISyntaxException {
        log.debug("REST request to save Claims835CrossoverException : {}", claims835CrossoverExceptionDTO);
        if (claims835CrossoverExceptionDTO.getClaims835CrossoverExceptionId() != null) {
            throw new BadRequestAlertException("A new claims835CrossoverException cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Claims835CrossoverExceptionDTO result = claims835CrossoverExceptionService.save(claims835CrossoverExceptionDTO);
        return ResponseEntity
            .created(new URI("/api/claims-835-crossover-exceptions/" + result.getClaims835CrossoverExceptionId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    result.getClaims835CrossoverExceptionId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PUT  /claims-835-crossover-exceptions/:claims835CrossoverExceptionId} : Updates an existing claims835CrossoverException.
     *
     * @param claims835CrossoverExceptionId the id of the claims835CrossoverExceptionDTO to save.
     * @param claims835CrossoverExceptionDTO the claims835CrossoverExceptionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claims835CrossoverExceptionDTO,
     * or with status {@code 400 (Bad Request)} if the claims835CrossoverExceptionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the claims835CrossoverExceptionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/claims-835-crossover-exceptions/{claims835CrossoverExceptionId}")
    public ResponseEntity<Claims835CrossoverExceptionDTO> updateClaims835CrossoverException(
        @PathVariable(value = "claims835CrossoverExceptionId", required = false) final Long claims835CrossoverExceptionId,
        @Valid @RequestBody Claims835CrossoverExceptionDTO claims835CrossoverExceptionDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to update Claims835CrossoverException : {}, {}",
            claims835CrossoverExceptionId,
            claims835CrossoverExceptionDTO
        );
        if (claims835CrossoverExceptionDTO.getClaims835CrossoverExceptionId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(claims835CrossoverExceptionId, claims835CrossoverExceptionDTO.getClaims835CrossoverExceptionId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!claims835CrossoverExceptionRepository.existsById(claims835CrossoverExceptionId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Claims835CrossoverExceptionDTO result = claims835CrossoverExceptionService.update(claims835CrossoverExceptionDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    claims835CrossoverExceptionDTO.getClaims835CrossoverExceptionId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /claims-835-crossover-exceptions/:claims835CrossoverExceptionId} : Partial updates given fields of an existing claims835CrossoverException, field will ignore if it is null
     *
     * @param claims835CrossoverExceptionId the id of the claims835CrossoverExceptionDTO to save.
     * @param claims835CrossoverExceptionDTO the claims835CrossoverExceptionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claims835CrossoverExceptionDTO,
     * or with status {@code 400 (Bad Request)} if the claims835CrossoverExceptionDTO is not valid,
     * or with status {@code 404 (Not Found)} if the claims835CrossoverExceptionDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the claims835CrossoverExceptionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/claims-835-crossover-exceptions/{claims835CrossoverExceptionId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public ResponseEntity<Claims835CrossoverExceptionDTO> partialUpdateClaims835CrossoverException(
        @PathVariable(value = "claims835CrossoverExceptionId", required = false) final Long claims835CrossoverExceptionId,
        @NotNull @RequestBody Claims835CrossoverExceptionDTO claims835CrossoverExceptionDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update Claims835CrossoverException partially : {}, {}",
            claims835CrossoverExceptionId,
            claims835CrossoverExceptionDTO
        );
        if (claims835CrossoverExceptionDTO.getClaims835CrossoverExceptionId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(claims835CrossoverExceptionId, claims835CrossoverExceptionDTO.getClaims835CrossoverExceptionId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!claims835CrossoverExceptionRepository.existsById(claims835CrossoverExceptionId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Claims835CrossoverExceptionDTO> result = claims835CrossoverExceptionService.partialUpdate(claims835CrossoverExceptionDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                false,
                ENTITY_NAME,
                claims835CrossoverExceptionDTO.getClaims835CrossoverExceptionId().toString()
            )
        );
    }

    /**
     * {@code GET  /claims-835-crossover-exceptions} : get all the claims835CrossoverExceptions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of claims835CrossoverExceptions in body.
     */
    @GetMapping("/claims-835-crossover-exceptions")
    public ResponseEntity<List<Claims835CrossoverExceptionDTO>> getAllClaims835CrossoverExceptions(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of Claims835CrossoverExceptions");
        Page<Claims835CrossoverExceptionDTO> page = claims835CrossoverExceptionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /claims-835-crossover-exceptions/:id} : get the "id" claims835CrossoverException.
     *
     * @param id the id of the claims835CrossoverExceptionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the claims835CrossoverExceptionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/claims-835-crossover-exceptions/{id}")
    public ResponseEntity<Claims835CrossoverExceptionDTO> getClaims835CrossoverException(@PathVariable Long id) {
        log.debug("REST request to get Claims835CrossoverException : {}", id);
        Optional<Claims835CrossoverExceptionDTO> claims835CrossoverExceptionDTO = claims835CrossoverExceptionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(claims835CrossoverExceptionDTO);
    }

    /**
     * {@code DELETE  /claims-835-crossover-exceptions/:id} : delete the "id" claims835CrossoverException.
     *
     * @param id the id of the claims835CrossoverExceptionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/claims-835-crossover-exceptions/{id}")
    public ResponseEntity<Void> deleteClaims835CrossoverException(@PathVariable Long id) {
        log.debug("REST request to delete Claims835CrossoverException : {}", id);
        claims835CrossoverExceptionService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
