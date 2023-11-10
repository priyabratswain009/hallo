package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.BranchUserMapRepository;
import com.sunknowledge.dme.rcm.service.BranchUserMapService;
import com.sunknowledge.dme.rcm.service.dto.BranchUserMapDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.BranchUserMap}.
 */
@RestController
@RequestMapping("/api")
public class BranchUserMapResource {

    private final Logger log = LoggerFactory.getLogger(BranchUserMapResource.class);

    private static final String ENTITY_NAME = "settingsBranchUserMap";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BranchUserMapService branchUserMapService;

    private final BranchUserMapRepository branchUserMapRepository;

    public BranchUserMapResource(BranchUserMapService branchUserMapService, BranchUserMapRepository branchUserMapRepository) {
        this.branchUserMapService = branchUserMapService;
        this.branchUserMapRepository = branchUserMapRepository;
    }

    /**
     * {@code POST  /branch-user-maps} : Create a new branchUserMap.
     *
     * @param branchUserMapDTO the branchUserMapDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new branchUserMapDTO, or with status {@code 400 (Bad Request)} if the branchUserMap has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/branch-user-maps")
    public ResponseEntity<BranchUserMapDTO> createBranchUserMap(@RequestBody BranchUserMapDTO branchUserMapDTO) throws URISyntaxException {
        log.debug("REST request to save BranchUserMap : {}", branchUserMapDTO);
        if (branchUserMapDTO.getMapId() != null) {
            throw new BadRequestAlertException("A new branchUserMap cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BranchUserMapDTO result = branchUserMapService.save(branchUserMapDTO);
        return ResponseEntity
            .created(new URI("/api/branch-user-maps/" + result.getMapId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getMapId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /branch-user-maps/:mapId} : Updates an existing branchUserMap.
     *
     * @param mapId the id of the branchUserMapDTO to save.
     * @param branchUserMapDTO the branchUserMapDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated branchUserMapDTO,
     * or with status {@code 400 (Bad Request)} if the branchUserMapDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the branchUserMapDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/branch-user-maps/{mapId}")
    public ResponseEntity<BranchUserMapDTO> updateBranchUserMap(
        @PathVariable(value = "mapId", required = false) final Long mapId,
        @RequestBody BranchUserMapDTO branchUserMapDTO
    ) throws URISyntaxException {
        log.debug("REST request to update BranchUserMap : {}, {}", mapId, branchUserMapDTO);
        if (branchUserMapDTO.getMapId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(mapId, branchUserMapDTO.getMapId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!branchUserMapRepository.existsById(mapId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BranchUserMapDTO result = branchUserMapService.update(branchUserMapDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, branchUserMapDTO.getMapId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /branch-user-maps/:mapId} : Partial updates given fields of an existing branchUserMap, field will ignore if it is null
     *
     * @param mapId the id of the branchUserMapDTO to save.
     * @param branchUserMapDTO the branchUserMapDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated branchUserMapDTO,
     * or with status {@code 400 (Bad Request)} if the branchUserMapDTO is not valid,
     * or with status {@code 404 (Not Found)} if the branchUserMapDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the branchUserMapDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/branch-user-maps/{mapId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BranchUserMapDTO> partialUpdateBranchUserMap(
        @PathVariable(value = "mapId", required = false) final Long mapId,
        @RequestBody BranchUserMapDTO branchUserMapDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update BranchUserMap partially : {}, {}", mapId, branchUserMapDTO);
        if (branchUserMapDTO.getMapId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(mapId, branchUserMapDTO.getMapId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!branchUserMapRepository.existsById(mapId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BranchUserMapDTO> result = branchUserMapService.partialUpdate(branchUserMapDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, branchUserMapDTO.getMapId().toString())
        );
    }

    /**
     * {@code GET  /branch-user-maps} : get all the branchUserMaps.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of branchUserMaps in body.
     */
    @GetMapping("/branch-user-maps")
    public ResponseEntity<List<BranchUserMapDTO>> getAllBranchUserMaps(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of BranchUserMaps");
        Page<BranchUserMapDTO> page = branchUserMapService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /branch-user-maps/:id} : get the "id" branchUserMap.
     *
     * @param id the id of the branchUserMapDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the branchUserMapDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/branch-user-maps/{id}")
    public ResponseEntity<BranchUserMapDTO> getBranchUserMap(@PathVariable Long id) {
        log.debug("REST request to get BranchUserMap : {}", id);
        Optional<BranchUserMapDTO> branchUserMapDTO = branchUserMapService.findOne(id);
        return ResponseUtil.wrapOrNotFound(branchUserMapDTO);
    }

    /**
     * {@code DELETE  /branch-user-maps/:id} : delete the "id" branchUserMap.
     *
     * @param id the id of the branchUserMapDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/branch-user-maps/{id}")
    public ResponseEntity<Void> deleteBranchUserMap(@PathVariable Long id) {
        log.debug("REST request to delete BranchUserMap : {}", id);
        branchUserMapService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
