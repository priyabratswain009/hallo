package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.BranchGroupRepository;
import com.sunknowledge.dme.rcm.service.BranchGroupService;
import com.sunknowledge.dme.rcm.service.dto.BranchGroupDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.BranchGroup}.
 */
@RestController
@RequestMapping("/api")
public class BranchGroupResource {

    private final Logger log = LoggerFactory.getLogger(BranchGroupResource.class);

    private static final String ENTITY_NAME = "settingsBranchGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BranchGroupService branchGroupService;

    private final BranchGroupRepository branchGroupRepository;

    public BranchGroupResource(BranchGroupService branchGroupService, BranchGroupRepository branchGroupRepository) {
        this.branchGroupService = branchGroupService;
        this.branchGroupRepository = branchGroupRepository;
    }

    /**
     * {@code POST  /branch-groups} : Create a new branchGroup.
     *
     * @param branchGroupDTO the branchGroupDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new branchGroupDTO, or with status {@code 400 (Bad Request)} if the branchGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/branch-groups")
    public ResponseEntity<BranchGroupDTO> createBranchGroup(@RequestBody BranchGroupDTO branchGroupDTO) throws URISyntaxException {
        log.debug("REST request to save BranchGroup : {}", branchGroupDTO);
        if (branchGroupDTO.getBranchGroupId() != null) {
            throw new BadRequestAlertException("A new branchGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BranchGroupDTO result = branchGroupService.save(branchGroupDTO);
        return ResponseEntity
            .created(new URI("/api/branch-groups/" + result.getBranchGroupId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getBranchGroupId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /branch-groups/:branchGroupId} : Updates an existing branchGroup.
     *
     * @param branchGroupId the id of the branchGroupDTO to save.
     * @param branchGroupDTO the branchGroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated branchGroupDTO,
     * or with status {@code 400 (Bad Request)} if the branchGroupDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the branchGroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/branch-groups/{branchGroupId}")
    public ResponseEntity<BranchGroupDTO> updateBranchGroup(
        @PathVariable(value = "branchGroupId", required = false) final Long branchGroupId,
        @RequestBody BranchGroupDTO branchGroupDTO
    ) throws URISyntaxException {
        log.debug("REST request to update BranchGroup : {}, {}", branchGroupId, branchGroupDTO);
        if (branchGroupDTO.getBranchGroupId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(branchGroupId, branchGroupDTO.getBranchGroupId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!branchGroupRepository.existsById(branchGroupId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BranchGroupDTO result = branchGroupService.update(branchGroupDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, branchGroupDTO.getBranchGroupId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /branch-groups/:branchGroupId} : Partial updates given fields of an existing branchGroup, field will ignore if it is null
     *
     * @param branchGroupId the id of the branchGroupDTO to save.
     * @param branchGroupDTO the branchGroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated branchGroupDTO,
     * or with status {@code 400 (Bad Request)} if the branchGroupDTO is not valid,
     * or with status {@code 404 (Not Found)} if the branchGroupDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the branchGroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/branch-groups/{branchGroupId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BranchGroupDTO> partialUpdateBranchGroup(
        @PathVariable(value = "branchGroupId", required = false) final Long branchGroupId,
        @RequestBody BranchGroupDTO branchGroupDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update BranchGroup partially : {}, {}", branchGroupId, branchGroupDTO);
        if (branchGroupDTO.getBranchGroupId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(branchGroupId, branchGroupDTO.getBranchGroupId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!branchGroupRepository.existsById(branchGroupId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BranchGroupDTO> result = branchGroupService.partialUpdate(branchGroupDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, branchGroupDTO.getBranchGroupId().toString())
        );
    }

    /**
     * {@code GET  /branch-groups} : get all the branchGroups.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of branchGroups in body.
     */
    @GetMapping("/branch-groups")
    public ResponseEntity<List<BranchGroupDTO>> getAllBranchGroups(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of BranchGroups");
        Page<BranchGroupDTO> page = branchGroupService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /branch-groups/:id} : get the "id" branchGroup.
     *
     * @param id the id of the branchGroupDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the branchGroupDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/branch-groups/{id}")
    public ResponseEntity<BranchGroupDTO> getBranchGroup(@PathVariable Long id) {
        log.debug("REST request to get BranchGroup : {}", id);
        Optional<BranchGroupDTO> branchGroupDTO = branchGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(branchGroupDTO);
    }

    /**
     * {@code DELETE  /branch-groups/:id} : delete the "id" branchGroup.
     *
     * @param id the id of the branchGroupDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/branch-groups/{id}")
    public ResponseEntity<Void> deleteBranchGroup(@PathVariable Long id) {
        log.debug("REST request to delete BranchGroup : {}", id);
        branchGroupService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
