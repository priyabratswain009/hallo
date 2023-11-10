package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.RoleMasterRepository;
import com.sunknowledge.dme.rcm.service.RoleMasterService;
import com.sunknowledge.dme.rcm.service.dto.RoleMasterDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.RoleMaster}.
 */
@RestController
@RequestMapping("/api")
public class RoleMasterResource {

    private final Logger log = LoggerFactory.getLogger(RoleMasterResource.class);

    private static final String ENTITY_NAME = "settingsRoleMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RoleMasterService roleMasterService;

    private final RoleMasterRepository roleMasterRepository;

    public RoleMasterResource(RoleMasterService roleMasterService, RoleMasterRepository roleMasterRepository) {
        this.roleMasterService = roleMasterService;
        this.roleMasterRepository = roleMasterRepository;
    }

    /**
     * {@code POST  /role-masters} : Create a new roleMaster.
     *
     * @param roleMasterDTO the roleMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new roleMasterDTO, or with status {@code 400 (Bad Request)} if the roleMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/role-masters")
    public ResponseEntity<RoleMasterDTO> createRoleMaster(@RequestBody RoleMasterDTO roleMasterDTO) throws URISyntaxException {
        log.debug("REST request to save RoleMaster : {}", roleMasterDTO);
        if (roleMasterDTO.getRoleId() != null) {
            throw new BadRequestAlertException("A new roleMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RoleMasterDTO result = roleMasterService.save(roleMasterDTO);
        return ResponseEntity
            .created(new URI("/api/role-masters/" + result.getRoleId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getRoleId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /role-masters/:roleId} : Updates an existing roleMaster.
     *
     * @param roleId the id of the roleMasterDTO to save.
     * @param roleMasterDTO the roleMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated roleMasterDTO,
     * or with status {@code 400 (Bad Request)} if the roleMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the roleMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/role-masters/{roleId}")
    public ResponseEntity<RoleMasterDTO> updateRoleMaster(
        @PathVariable(value = "roleId", required = false) final Long roleId,
        @RequestBody RoleMasterDTO roleMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update RoleMaster : {}, {}", roleId, roleMasterDTO);
        if (roleMasterDTO.getRoleId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(roleId, roleMasterDTO.getRoleId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!roleMasterRepository.existsById(roleId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RoleMasterDTO result = roleMasterService.update(roleMasterDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, roleMasterDTO.getRoleId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /role-masters/:roleId} : Partial updates given fields of an existing roleMaster, field will ignore if it is null
     *
     * @param roleId the id of the roleMasterDTO to save.
     * @param roleMasterDTO the roleMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated roleMasterDTO,
     * or with status {@code 400 (Bad Request)} if the roleMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the roleMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the roleMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/role-masters/{roleId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RoleMasterDTO> partialUpdateRoleMaster(
        @PathVariable(value = "roleId", required = false) final Long roleId,
        @RequestBody RoleMasterDTO roleMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update RoleMaster partially : {}, {}", roleId, roleMasterDTO);
        if (roleMasterDTO.getRoleId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(roleId, roleMasterDTO.getRoleId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!roleMasterRepository.existsById(roleId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RoleMasterDTO> result = roleMasterService.partialUpdate(roleMasterDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, roleMasterDTO.getRoleId().toString())
        );
    }

    /**
     * {@code GET  /role-masters} : get all the roleMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of roleMasters in body.
     */
    @GetMapping("/role-masters")
    public ResponseEntity<List<RoleMasterDTO>> getAllRoleMasters(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of RoleMasters");
        Page<RoleMasterDTO> page = roleMasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /role-masters/:id} : get the "id" roleMaster.
     *
     * @param id the id of the roleMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the roleMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/role-masters/{id}")
    public ResponseEntity<RoleMasterDTO> getRoleMaster(@PathVariable Long id) {
        log.debug("REST request to get RoleMaster : {}", id);
        Optional<RoleMasterDTO> roleMasterDTO = roleMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(roleMasterDTO);
    }

    /**
     * {@code DELETE  /role-masters/:id} : delete the "id" roleMaster.
     *
     * @param id the id of the roleMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/role-masters/{id}")
    public ResponseEntity<Void> deleteRoleMaster(@PathVariable Long id) {
        log.debug("REST request to delete RoleMaster : {}", id);
        roleMasterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
