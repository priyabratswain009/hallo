package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.DepreciationMethodRepository;
import com.sunknowledge.dme.rcm.service.DepreciationMethodService;
import com.sunknowledge.dme.rcm.service.dto.DepreciationMethodDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.DepreciationMethod}.
 */
@RestController
@RequestMapping("/api")
public class DepreciationMethodResource {

    private final Logger log = LoggerFactory.getLogger(DepreciationMethodResource.class);

    private static final String ENTITY_NAME = "itemsDepreciationMethod";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DepreciationMethodService depreciationMethodService;

    private final DepreciationMethodRepository depreciationMethodRepository;

    public DepreciationMethodResource(
        DepreciationMethodService depreciationMethodService,
        DepreciationMethodRepository depreciationMethodRepository
    ) {
        this.depreciationMethodService = depreciationMethodService;
        this.depreciationMethodRepository = depreciationMethodRepository;
    }

    /**
     * {@code POST  /depreciation-methods} : Create a new depreciationMethod.
     *
     * @param depreciationMethodDTO the depreciationMethodDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new depreciationMethodDTO, or with status {@code 400 (Bad Request)} if the depreciationMethod has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/depreciation-methods")
    public ResponseEntity<DepreciationMethodDTO> createDepreciationMethod(@RequestBody DepreciationMethodDTO depreciationMethodDTO)
        throws URISyntaxException {
        log.debug("REST request to save DepreciationMethod : {}", depreciationMethodDTO);
        if (depreciationMethodDTO.getDepreciationMethodId() != null) {
            throw new BadRequestAlertException("A new depreciationMethod cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DepreciationMethodDTO result = depreciationMethodService.save(depreciationMethodDTO);
        return ResponseEntity
            .created(new URI("/api/depreciation-methods/" + result.getDepreciationMethodId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getDepreciationMethodId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /depreciation-methods/:depreciationMethodId} : Updates an existing depreciationMethod.
     *
     * @param depreciationMethodId the id of the depreciationMethodDTO to save.
     * @param depreciationMethodDTO the depreciationMethodDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated depreciationMethodDTO,
     * or with status {@code 400 (Bad Request)} if the depreciationMethodDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the depreciationMethodDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/depreciation-methods/{depreciationMethodId}")
    public ResponseEntity<DepreciationMethodDTO> updateDepreciationMethod(
        @PathVariable(value = "depreciationMethodId", required = false) final Long depreciationMethodId,
        @RequestBody DepreciationMethodDTO depreciationMethodDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DepreciationMethod : {}, {}", depreciationMethodId, depreciationMethodDTO);
        if (depreciationMethodDTO.getDepreciationMethodId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(depreciationMethodId, depreciationMethodDTO.getDepreciationMethodId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!depreciationMethodRepository.existsById(depreciationMethodId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DepreciationMethodDTO result = depreciationMethodService.update(depreciationMethodDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    depreciationMethodDTO.getDepreciationMethodId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /depreciation-methods/:depreciationMethodId} : Partial updates given fields of an existing depreciationMethod, field will ignore if it is null
     *
     * @param depreciationMethodId the id of the depreciationMethodDTO to save.
     * @param depreciationMethodDTO the depreciationMethodDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated depreciationMethodDTO,
     * or with status {@code 400 (Bad Request)} if the depreciationMethodDTO is not valid,
     * or with status {@code 404 (Not Found)} if the depreciationMethodDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the depreciationMethodDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/depreciation-methods/{depreciationMethodId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DepreciationMethodDTO> partialUpdateDepreciationMethod(
        @PathVariable(value = "depreciationMethodId", required = false) final Long depreciationMethodId,
        @RequestBody DepreciationMethodDTO depreciationMethodDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DepreciationMethod partially : {}, {}", depreciationMethodId, depreciationMethodDTO);
        if (depreciationMethodDTO.getDepreciationMethodId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(depreciationMethodId, depreciationMethodDTO.getDepreciationMethodId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!depreciationMethodRepository.existsById(depreciationMethodId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DepreciationMethodDTO> result = depreciationMethodService.partialUpdate(depreciationMethodDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                false,
                ENTITY_NAME,
                depreciationMethodDTO.getDepreciationMethodId().toString()
            )
        );
    }

    /**
     * {@code GET  /depreciation-methods} : get all the depreciationMethods.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of depreciationMethods in body.
     */
    @GetMapping("/depreciation-methods")
    public ResponseEntity<List<DepreciationMethodDTO>> getAllDepreciationMethods(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of DepreciationMethods");
        Page<DepreciationMethodDTO> page = depreciationMethodService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /depreciation-methods/:id} : get the "id" depreciationMethod.
     *
     * @param id the id of the depreciationMethodDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the depreciationMethodDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/depreciation-methods/{id}")
    public ResponseEntity<DepreciationMethodDTO> getDepreciationMethod(@PathVariable Long id) {
        log.debug("REST request to get DepreciationMethod : {}", id);
        Optional<DepreciationMethodDTO> depreciationMethodDTO = depreciationMethodService.findOne(id);
        return ResponseUtil.wrapOrNotFound(depreciationMethodDTO);
    }

    /**
     * {@code DELETE  /depreciation-methods/:id} : delete the "id" depreciationMethod.
     *
     * @param id the id of the depreciationMethodDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/depreciation-methods/{id}")
    public ResponseEntity<Void> deleteDepreciationMethod(@PathVariable Long id) {
        log.debug("REST request to delete DepreciationMethod : {}", id);
        depreciationMethodService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
