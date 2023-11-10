package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.Claims835CrossoverProcessedRepository;
import com.sunknowledge.dme.rcm.service.Claims835CrossoverProcessedService;
import com.sunknowledge.dme.rcm.service.dto.Claims835CrossoverProcessedDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.Claims835CrossoverProcessed}.
 */
@RestController
@RequestMapping("/api")
public class Claims835CrossoverProcessedResource {

    private final Logger log = LoggerFactory.getLogger(Claims835CrossoverProcessedResource.class);

    private static final String ENTITY_NAME = "claimsClaims835CrossoverProcessed";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final Claims835CrossoverProcessedService claims835CrossoverProcessedService;

    private final Claims835CrossoverProcessedRepository claims835CrossoverProcessedRepository;

    public Claims835CrossoverProcessedResource(
        Claims835CrossoverProcessedService claims835CrossoverProcessedService,
        Claims835CrossoverProcessedRepository claims835CrossoverProcessedRepository
    ) {
        this.claims835CrossoverProcessedService = claims835CrossoverProcessedService;
        this.claims835CrossoverProcessedRepository = claims835CrossoverProcessedRepository;
    }

    /**
     * {@code POST  /claims-835-crossover-processeds} : Create a new claims835CrossoverProcessed.
     *
     * @param claims835CrossoverProcessedDTO the claims835CrossoverProcessedDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new claims835CrossoverProcessedDTO, or with status {@code 400 (Bad Request)} if the claims835CrossoverProcessed has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/claims-835-crossover-processeds")
    public ResponseEntity<Claims835CrossoverProcessedDTO> createClaims835CrossoverProcessed(
        @Valid @RequestBody Claims835CrossoverProcessedDTO claims835CrossoverProcessedDTO
    ) throws URISyntaxException {
        log.debug("REST request to save Claims835CrossoverProcessed : {}", claims835CrossoverProcessedDTO);
        if (claims835CrossoverProcessedDTO.getClaims835CrossoverProcessedId() != null) {
            throw new BadRequestAlertException("A new claims835CrossoverProcessed cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Claims835CrossoverProcessedDTO result = claims835CrossoverProcessedService.save(claims835CrossoverProcessedDTO);
        return ResponseEntity
            .created(new URI("/api/claims-835-crossover-processeds/" + result.getClaims835CrossoverProcessedId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    result.getClaims835CrossoverProcessedId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PUT  /claims-835-crossover-processeds/:claims835CrossoverProcessedId} : Updates an existing claims835CrossoverProcessed.
     *
     * @param claims835CrossoverProcessedId the id of the claims835CrossoverProcessedDTO to save.
     * @param claims835CrossoverProcessedDTO the claims835CrossoverProcessedDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claims835CrossoverProcessedDTO,
     * or with status {@code 400 (Bad Request)} if the claims835CrossoverProcessedDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the claims835CrossoverProcessedDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/claims-835-crossover-processeds/{claims835CrossoverProcessedId}")
    public ResponseEntity<Claims835CrossoverProcessedDTO> updateClaims835CrossoverProcessed(
        @PathVariable(value = "claims835CrossoverProcessedId", required = false) final Long claims835CrossoverProcessedId,
        @Valid @RequestBody Claims835CrossoverProcessedDTO claims835CrossoverProcessedDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to update Claims835CrossoverProcessed : {}, {}",
            claims835CrossoverProcessedId,
            claims835CrossoverProcessedDTO
        );
        if (claims835CrossoverProcessedDTO.getClaims835CrossoverProcessedId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(claims835CrossoverProcessedId, claims835CrossoverProcessedDTO.getClaims835CrossoverProcessedId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!claims835CrossoverProcessedRepository.existsById(claims835CrossoverProcessedId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Claims835CrossoverProcessedDTO result = claims835CrossoverProcessedService.update(claims835CrossoverProcessedDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    claims835CrossoverProcessedDTO.getClaims835CrossoverProcessedId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /claims-835-crossover-processeds/:claims835CrossoverProcessedId} : Partial updates given fields of an existing claims835CrossoverProcessed, field will ignore if it is null
     *
     * @param claims835CrossoverProcessedId the id of the claims835CrossoverProcessedDTO to save.
     * @param claims835CrossoverProcessedDTO the claims835CrossoverProcessedDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claims835CrossoverProcessedDTO,
     * or with status {@code 400 (Bad Request)} if the claims835CrossoverProcessedDTO is not valid,
     * or with status {@code 404 (Not Found)} if the claims835CrossoverProcessedDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the claims835CrossoverProcessedDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/claims-835-crossover-processeds/{claims835CrossoverProcessedId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public ResponseEntity<Claims835CrossoverProcessedDTO> partialUpdateClaims835CrossoverProcessed(
        @PathVariable(value = "claims835CrossoverProcessedId", required = false) final Long claims835CrossoverProcessedId,
        @NotNull @RequestBody Claims835CrossoverProcessedDTO claims835CrossoverProcessedDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update Claims835CrossoverProcessed partially : {}, {}",
            claims835CrossoverProcessedId,
            claims835CrossoverProcessedDTO
        );
        if (claims835CrossoverProcessedDTO.getClaims835CrossoverProcessedId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(claims835CrossoverProcessedId, claims835CrossoverProcessedDTO.getClaims835CrossoverProcessedId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!claims835CrossoverProcessedRepository.existsById(claims835CrossoverProcessedId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Claims835CrossoverProcessedDTO> result = claims835CrossoverProcessedService.partialUpdate(claims835CrossoverProcessedDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                false,
                ENTITY_NAME,
                claims835CrossoverProcessedDTO.getClaims835CrossoverProcessedId().toString()
            )
        );
    }

    /**
     * {@code GET  /claims-835-crossover-processeds} : get all the claims835CrossoverProcesseds.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of claims835CrossoverProcesseds in body.
     */
    @GetMapping("/claims-835-crossover-processeds")
    public ResponseEntity<List<Claims835CrossoverProcessedDTO>> getAllClaims835CrossoverProcesseds(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of Claims835CrossoverProcesseds");
        Page<Claims835CrossoverProcessedDTO> page = claims835CrossoverProcessedService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /claims-835-crossover-processeds/:id} : get the "id" claims835CrossoverProcessed.
     *
     * @param id the id of the claims835CrossoverProcessedDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the claims835CrossoverProcessedDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/claims-835-crossover-processeds/{id}")
    public ResponseEntity<Claims835CrossoverProcessedDTO> getClaims835CrossoverProcessed(@PathVariable Long id) {
        log.debug("REST request to get Claims835CrossoverProcessed : {}", id);
        Optional<Claims835CrossoverProcessedDTO> claims835CrossoverProcessedDTO = claims835CrossoverProcessedService.findOne(id);
        return ResponseUtil.wrapOrNotFound(claims835CrossoverProcessedDTO);
    }

    /**
     * {@code DELETE  /claims-835-crossover-processeds/:id} : delete the "id" claims835CrossoverProcessed.
     *
     * @param id the id of the claims835CrossoverProcessedDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/claims-835-crossover-processeds/{id}")
    public ResponseEntity<Void> deleteClaims835CrossoverProcessed(@PathVariable Long id) {
        log.debug("REST request to delete Claims835CrossoverProcessed : {}", id);
        claims835CrossoverProcessedService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
