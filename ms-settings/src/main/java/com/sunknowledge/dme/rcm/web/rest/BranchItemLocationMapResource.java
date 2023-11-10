package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.BranchItemLocationMapRepository;
import com.sunknowledge.dme.rcm.service.BranchItemLocationMapService;
import com.sunknowledge.dme.rcm.service.dto.BranchItemLocationMapDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.BranchItemLocationMap}.
 */
@RestController
@RequestMapping("/api")
public class BranchItemLocationMapResource {

    private final Logger log = LoggerFactory.getLogger(BranchItemLocationMapResource.class);

    private static final String ENTITY_NAME = "settingsBranchItemLocationMap";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BranchItemLocationMapService branchItemLocationMapService;

    private final BranchItemLocationMapRepository branchItemLocationMapRepository;

    public BranchItemLocationMapResource(
        BranchItemLocationMapService branchItemLocationMapService,
        BranchItemLocationMapRepository branchItemLocationMapRepository
    ) {
        this.branchItemLocationMapService = branchItemLocationMapService;
        this.branchItemLocationMapRepository = branchItemLocationMapRepository;
    }

    /**
     * {@code POST  /branch-item-location-maps} : Create a new branchItemLocationMap.
     *
     * @param branchItemLocationMapDTO the branchItemLocationMapDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new branchItemLocationMapDTO, or with status {@code 400 (Bad Request)} if the branchItemLocationMap has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/branch-item-location-maps")
    public ResponseEntity<BranchItemLocationMapDTO> createBranchItemLocationMap(
        @RequestBody BranchItemLocationMapDTO branchItemLocationMapDTO
    ) throws URISyntaxException {
        log.debug("REST request to save BranchItemLocationMap : {}", branchItemLocationMapDTO);
        if (branchItemLocationMapDTO.getBranchItemLocationMapId() != null) {
            throw new BadRequestAlertException("A new branchItemLocationMap cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BranchItemLocationMapDTO result = branchItemLocationMapService.save(branchItemLocationMapDTO);
        return ResponseEntity
            .created(new URI("/api/branch-item-location-maps/" + result.getBranchItemLocationMapId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getBranchItemLocationMapId().toString())
            )
            .body(result);
    }

    /**
     * {@code PUT  /branch-item-location-maps/:branchItemLocationMapId} : Updates an existing branchItemLocationMap.
     *
     * @param branchItemLocationMapId the id of the branchItemLocationMapDTO to save.
     * @param branchItemLocationMapDTO the branchItemLocationMapDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated branchItemLocationMapDTO,
     * or with status {@code 400 (Bad Request)} if the branchItemLocationMapDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the branchItemLocationMapDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/branch-item-location-maps/{branchItemLocationMapId}")
    public ResponseEntity<BranchItemLocationMapDTO> updateBranchItemLocationMap(
        @PathVariable(value = "branchItemLocationMapId", required = false) final Long branchItemLocationMapId,
        @RequestBody BranchItemLocationMapDTO branchItemLocationMapDTO
    ) throws URISyntaxException {
        log.debug("REST request to update BranchItemLocationMap : {}, {}", branchItemLocationMapId, branchItemLocationMapDTO);
        if (branchItemLocationMapDTO.getBranchItemLocationMapId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(branchItemLocationMapId, branchItemLocationMapDTO.getBranchItemLocationMapId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!branchItemLocationMapRepository.existsById(branchItemLocationMapId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BranchItemLocationMapDTO result = branchItemLocationMapService.update(branchItemLocationMapDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    branchItemLocationMapDTO.getBranchItemLocationMapId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /branch-item-location-maps/:branchItemLocationMapId} : Partial updates given fields of an existing branchItemLocationMap, field will ignore if it is null
     *
     * @param branchItemLocationMapId the id of the branchItemLocationMapDTO to save.
     * @param branchItemLocationMapDTO the branchItemLocationMapDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated branchItemLocationMapDTO,
     * or with status {@code 400 (Bad Request)} if the branchItemLocationMapDTO is not valid,
     * or with status {@code 404 (Not Found)} if the branchItemLocationMapDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the branchItemLocationMapDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/branch-item-location-maps/{branchItemLocationMapId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public ResponseEntity<BranchItemLocationMapDTO> partialUpdateBranchItemLocationMap(
        @PathVariable(value = "branchItemLocationMapId", required = false) final Long branchItemLocationMapId,
        @RequestBody BranchItemLocationMapDTO branchItemLocationMapDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update BranchItemLocationMap partially : {}, {}",
            branchItemLocationMapId,
            branchItemLocationMapDTO
        );
        if (branchItemLocationMapDTO.getBranchItemLocationMapId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(branchItemLocationMapId, branchItemLocationMapDTO.getBranchItemLocationMapId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!branchItemLocationMapRepository.existsById(branchItemLocationMapId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BranchItemLocationMapDTO> result = branchItemLocationMapService.partialUpdate(branchItemLocationMapDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                false,
                ENTITY_NAME,
                branchItemLocationMapDTO.getBranchItemLocationMapId().toString()
            )
        );
    }

    /**
     * {@code GET  /branch-item-location-maps} : get all the branchItemLocationMaps.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of branchItemLocationMaps in body.
     */
    @GetMapping("/branch-item-location-maps")
    public ResponseEntity<List<BranchItemLocationMapDTO>> getAllBranchItemLocationMaps(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of BranchItemLocationMaps");
        Page<BranchItemLocationMapDTO> page = branchItemLocationMapService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /branch-item-location-maps/:id} : get the "id" branchItemLocationMap.
     *
     * @param id the id of the branchItemLocationMapDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the branchItemLocationMapDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/branch-item-location-maps/{id}")
    public ResponseEntity<BranchItemLocationMapDTO> getBranchItemLocationMap(@PathVariable Long id) {
        log.debug("REST request to get BranchItemLocationMap : {}", id);
        Optional<BranchItemLocationMapDTO> branchItemLocationMapDTO = branchItemLocationMapService.findOne(id);
        return ResponseUtil.wrapOrNotFound(branchItemLocationMapDTO);
    }

    /**
     * {@code DELETE  /branch-item-location-maps/:id} : delete the "id" branchItemLocationMap.
     *
     * @param id the id of the branchItemLocationMapDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/branch-item-location-maps/{id}")
    public ResponseEntity<Void> deleteBranchItemLocationMap(@PathVariable Long id) {
        log.debug("REST request to delete BranchItemLocationMap : {}", id);
        branchItemLocationMapService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
