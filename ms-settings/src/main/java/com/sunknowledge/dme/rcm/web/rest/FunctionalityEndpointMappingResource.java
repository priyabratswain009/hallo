package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.FunctionalityEndpointMappingRepository;
import com.sunknowledge.dme.rcm.service.FunctionalityEndpointMappingService;
import com.sunknowledge.dme.rcm.service.dto.FunctionalityEndpointMappingDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.FunctionalityEndpointMapping}.
 */
@RestController
@RequestMapping("/api")
public class FunctionalityEndpointMappingResource {

    private final Logger log = LoggerFactory.getLogger(FunctionalityEndpointMappingResource.class);

    private static final String ENTITY_NAME = "settingsFunctionalityEndpointMapping";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FunctionalityEndpointMappingService functionalityEndpointMappingService;

    private final FunctionalityEndpointMappingRepository functionalityEndpointMappingRepository;

    public FunctionalityEndpointMappingResource(
        FunctionalityEndpointMappingService functionalityEndpointMappingService,
        FunctionalityEndpointMappingRepository functionalityEndpointMappingRepository
    ) {
        this.functionalityEndpointMappingService = functionalityEndpointMappingService;
        this.functionalityEndpointMappingRepository = functionalityEndpointMappingRepository;
    }

    /**
     * {@code POST  /functionality-endpoint-mappings} : Create a new functionalityEndpointMapping.
     *
     * @param functionalityEndpointMappingDTO the functionalityEndpointMappingDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new functionalityEndpointMappingDTO, or with status {@code 400 (Bad Request)} if the functionalityEndpointMapping has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/functionality-endpoint-mappings")
    public ResponseEntity<FunctionalityEndpointMappingDTO> createFunctionalityEndpointMapping(
        @RequestBody FunctionalityEndpointMappingDTO functionalityEndpointMappingDTO
    ) throws URISyntaxException {
        log.debug("REST request to save FunctionalityEndpointMapping : {}", functionalityEndpointMappingDTO);
        if (functionalityEndpointMappingDTO.getFunctionalityEndpointMappingId() != null) {
            throw new BadRequestAlertException("A new functionalityEndpointMapping cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FunctionalityEndpointMappingDTO result = functionalityEndpointMappingService.save(functionalityEndpointMappingDTO);
        return ResponseEntity
            .created(new URI("/api/functionality-endpoint-mappings/" + result.getFunctionalityEndpointMappingId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    result.getFunctionalityEndpointMappingId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PUT  /functionality-endpoint-mappings/:functionalityEndpointMappingId} : Updates an existing functionalityEndpointMapping.
     *
     * @param functionalityEndpointMappingId the id of the functionalityEndpointMappingDTO to save.
     * @param functionalityEndpointMappingDTO the functionalityEndpointMappingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated functionalityEndpointMappingDTO,
     * or with status {@code 400 (Bad Request)} if the functionalityEndpointMappingDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the functionalityEndpointMappingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/functionality-endpoint-mappings/{functionalityEndpointMappingId}")
    public ResponseEntity<FunctionalityEndpointMappingDTO> updateFunctionalityEndpointMapping(
        @PathVariable(value = "functionalityEndpointMappingId", required = false) final Long functionalityEndpointMappingId,
        @RequestBody FunctionalityEndpointMappingDTO functionalityEndpointMappingDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to update FunctionalityEndpointMapping : {}, {}",
            functionalityEndpointMappingId,
            functionalityEndpointMappingDTO
        );
        if (functionalityEndpointMappingDTO.getFunctionalityEndpointMappingId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(functionalityEndpointMappingId, functionalityEndpointMappingDTO.getFunctionalityEndpointMappingId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!functionalityEndpointMappingRepository.existsById(functionalityEndpointMappingId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        FunctionalityEndpointMappingDTO result = functionalityEndpointMappingService.update(functionalityEndpointMappingDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    functionalityEndpointMappingDTO.getFunctionalityEndpointMappingId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /functionality-endpoint-mappings/:functionalityEndpointMappingId} : Partial updates given fields of an existing functionalityEndpointMapping, field will ignore if it is null
     *
     * @param functionalityEndpointMappingId the id of the functionalityEndpointMappingDTO to save.
     * @param functionalityEndpointMappingDTO the functionalityEndpointMappingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated functionalityEndpointMappingDTO,
     * or with status {@code 400 (Bad Request)} if the functionalityEndpointMappingDTO is not valid,
     * or with status {@code 404 (Not Found)} if the functionalityEndpointMappingDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the functionalityEndpointMappingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/functionality-endpoint-mappings/{functionalityEndpointMappingId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public ResponseEntity<FunctionalityEndpointMappingDTO> partialUpdateFunctionalityEndpointMapping(
        @PathVariable(value = "functionalityEndpointMappingId", required = false) final Long functionalityEndpointMappingId,
        @RequestBody FunctionalityEndpointMappingDTO functionalityEndpointMappingDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update FunctionalityEndpointMapping partially : {}, {}",
            functionalityEndpointMappingId,
            functionalityEndpointMappingDTO
        );
        if (functionalityEndpointMappingDTO.getFunctionalityEndpointMappingId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(functionalityEndpointMappingId, functionalityEndpointMappingDTO.getFunctionalityEndpointMappingId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!functionalityEndpointMappingRepository.existsById(functionalityEndpointMappingId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FunctionalityEndpointMappingDTO> result = functionalityEndpointMappingService.partialUpdate(
            functionalityEndpointMappingDTO
        );

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                false,
                ENTITY_NAME,
                functionalityEndpointMappingDTO.getFunctionalityEndpointMappingId().toString()
            )
        );
    }

    /**
     * {@code GET  /functionality-endpoint-mappings} : get all the functionalityEndpointMappings.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of functionalityEndpointMappings in body.
     */
    @GetMapping("/functionality-endpoint-mappings")
    public ResponseEntity<List<FunctionalityEndpointMappingDTO>> getAllFunctionalityEndpointMappings(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of FunctionalityEndpointMappings");
        Page<FunctionalityEndpointMappingDTO> page = functionalityEndpointMappingService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /functionality-endpoint-mappings/:id} : get the "id" functionalityEndpointMapping.
     *
     * @param id the id of the functionalityEndpointMappingDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the functionalityEndpointMappingDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/functionality-endpoint-mappings/{id}")
    public ResponseEntity<FunctionalityEndpointMappingDTO> getFunctionalityEndpointMapping(@PathVariable Long id) {
        log.debug("REST request to get FunctionalityEndpointMapping : {}", id);
        Optional<FunctionalityEndpointMappingDTO> functionalityEndpointMappingDTO = functionalityEndpointMappingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(functionalityEndpointMappingDTO);
    }

    /**
     * {@code DELETE  /functionality-endpoint-mappings/:id} : delete the "id" functionalityEndpointMapping.
     *
     * @param id the id of the functionalityEndpointMappingDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/functionality-endpoint-mappings/{id}")
    public ResponseEntity<Void> deleteFunctionalityEndpointMapping(@PathVariable Long id) {
        log.debug("REST request to delete FunctionalityEndpointMapping : {}", id);
        functionalityEndpointMappingService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
