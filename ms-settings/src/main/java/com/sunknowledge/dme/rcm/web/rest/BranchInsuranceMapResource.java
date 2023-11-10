package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.BranchInsuranceMapRepository;
import com.sunknowledge.dme.rcm.service.BranchInsuranceMapService;
import com.sunknowledge.dme.rcm.service.dto.BranchInsuranceMapDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.BranchInsuranceMap}.
 */
@RestController
@RequestMapping("/api")
public class BranchInsuranceMapResource {

    private final Logger log = LoggerFactory.getLogger(BranchInsuranceMapResource.class);

    private static final String ENTITY_NAME = "settingsBranchInsuranceMap";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BranchInsuranceMapService branchInsuranceMapService;

    private final BranchInsuranceMapRepository branchInsuranceMapRepository;

    public BranchInsuranceMapResource(
        BranchInsuranceMapService branchInsuranceMapService,
        BranchInsuranceMapRepository branchInsuranceMapRepository
    ) {
        this.branchInsuranceMapService = branchInsuranceMapService;
        this.branchInsuranceMapRepository = branchInsuranceMapRepository;
    }

    /**
     * {@code POST  /branch-insurance-maps} : Create a new branchInsuranceMap.
     *
     * @param branchInsuranceMapDTO the branchInsuranceMapDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new branchInsuranceMapDTO, or with status {@code 400 (Bad Request)} if the branchInsuranceMap has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/branch-insurance-maps")
    public ResponseEntity<BranchInsuranceMapDTO> createBranchInsuranceMap(@RequestBody BranchInsuranceMapDTO branchInsuranceMapDTO)
        throws URISyntaxException {
        log.debug("REST request to save BranchInsuranceMap : {}", branchInsuranceMapDTO);
        if (branchInsuranceMapDTO.getBranchInsuranceMapId() != null) {
            throw new BadRequestAlertException("A new branchInsuranceMap cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BranchInsuranceMapDTO result = branchInsuranceMapService.save(branchInsuranceMapDTO);
        return ResponseEntity
            .created(new URI("/api/branch-insurance-maps/" + result.getBranchInsuranceMapId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getBranchInsuranceMapId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /branch-insurance-maps/:branchInsuranceMapId} : Updates an existing branchInsuranceMap.
     *
     * @param branchInsuranceMapId the id of the branchInsuranceMapDTO to save.
     * @param branchInsuranceMapDTO the branchInsuranceMapDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated branchInsuranceMapDTO,
     * or with status {@code 400 (Bad Request)} if the branchInsuranceMapDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the branchInsuranceMapDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/branch-insurance-maps/{branchInsuranceMapId}")
    public ResponseEntity<BranchInsuranceMapDTO> updateBranchInsuranceMap(
        @PathVariable(value = "branchInsuranceMapId", required = false) final Long branchInsuranceMapId,
        @RequestBody BranchInsuranceMapDTO branchInsuranceMapDTO
    ) throws URISyntaxException {
        log.debug("REST request to update BranchInsuranceMap : {}, {}", branchInsuranceMapId, branchInsuranceMapDTO);
        if (branchInsuranceMapDTO.getBranchInsuranceMapId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(branchInsuranceMapId, branchInsuranceMapDTO.getBranchInsuranceMapId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!branchInsuranceMapRepository.existsById(branchInsuranceMapId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BranchInsuranceMapDTO result = branchInsuranceMapService.update(branchInsuranceMapDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    branchInsuranceMapDTO.getBranchInsuranceMapId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /branch-insurance-maps/:branchInsuranceMapId} : Partial updates given fields of an existing branchInsuranceMap, field will ignore if it is null
     *
     * @param branchInsuranceMapId the id of the branchInsuranceMapDTO to save.
     * @param branchInsuranceMapDTO the branchInsuranceMapDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated branchInsuranceMapDTO,
     * or with status {@code 400 (Bad Request)} if the branchInsuranceMapDTO is not valid,
     * or with status {@code 404 (Not Found)} if the branchInsuranceMapDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the branchInsuranceMapDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/branch-insurance-maps/{branchInsuranceMapId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public ResponseEntity<BranchInsuranceMapDTO> partialUpdateBranchInsuranceMap(
        @PathVariable(value = "branchInsuranceMapId", required = false) final Long branchInsuranceMapId,
        @RequestBody BranchInsuranceMapDTO branchInsuranceMapDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update BranchInsuranceMap partially : {}, {}", branchInsuranceMapId, branchInsuranceMapDTO);
        if (branchInsuranceMapDTO.getBranchInsuranceMapId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(branchInsuranceMapId, branchInsuranceMapDTO.getBranchInsuranceMapId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!branchInsuranceMapRepository.existsById(branchInsuranceMapId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BranchInsuranceMapDTO> result = branchInsuranceMapService.partialUpdate(branchInsuranceMapDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                false,
                ENTITY_NAME,
                branchInsuranceMapDTO.getBranchInsuranceMapId().toString()
            )
        );
    }

    /**
     * {@code GET  /branch-insurance-maps} : get all the branchInsuranceMaps.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of branchInsuranceMaps in body.
     */
    @GetMapping("/branch-insurance-maps")
    public ResponseEntity<List<BranchInsuranceMapDTO>> getAllBranchInsuranceMaps(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of BranchInsuranceMaps");
        Page<BranchInsuranceMapDTO> page = branchInsuranceMapService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /branch-insurance-maps/:id} : get the "id" branchInsuranceMap.
     *
     * @param id the id of the branchInsuranceMapDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the branchInsuranceMapDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/branch-insurance-maps/{id}")
    public ResponseEntity<BranchInsuranceMapDTO> getBranchInsuranceMap(@PathVariable Long id) {
        log.debug("REST request to get BranchInsuranceMap : {}", id);
        Optional<BranchInsuranceMapDTO> branchInsuranceMapDTO = branchInsuranceMapService.findOne(id);
        return ResponseUtil.wrapOrNotFound(branchInsuranceMapDTO);
    }

    /**
     * {@code DELETE  /branch-insurance-maps/:id} : delete the "id" branchInsuranceMap.
     *
     * @param id the id of the branchInsuranceMapDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/branch-insurance-maps/{id}")
    public ResponseEntity<Void> deleteBranchInsuranceMap(@PathVariable Long id) {
        log.debug("REST request to delete BranchInsuranceMap : {}", id);
        branchInsuranceMapService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
