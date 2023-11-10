package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.RoleUserMapRepository;
import com.sunknowledge.dme.rcm.service.RoleUserMapService;
import com.sunknowledge.dme.rcm.service.dto.RoleUserMapDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.RoleUserMap}.
 */
@RestController
@RequestMapping("/api")
public class RoleUserMapResource {

    private final Logger log = LoggerFactory.getLogger(RoleUserMapResource.class);

    private static final String ENTITY_NAME = "settingsRoleUserMap";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RoleUserMapService roleUserMapService;

    private final RoleUserMapRepository roleUserMapRepository;

    public RoleUserMapResource(RoleUserMapService roleUserMapService, RoleUserMapRepository roleUserMapRepository) {
        this.roleUserMapService = roleUserMapService;
        this.roleUserMapRepository = roleUserMapRepository;
    }

    /**
     * {@code POST  /role-user-maps} : Create a new roleUserMap.
     *
     * @param roleUserMapDTO the roleUserMapDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new roleUserMapDTO, or with status {@code 400 (Bad Request)} if the roleUserMap has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/role-user-maps")
    public ResponseEntity<RoleUserMapDTO> createRoleUserMap(@RequestBody RoleUserMapDTO roleUserMapDTO) throws URISyntaxException {
        log.debug("REST request to save RoleUserMap : {}", roleUserMapDTO);
        if (roleUserMapDTO.getRoleUserMapId() != null) {
            throw new BadRequestAlertException("A new roleUserMap cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RoleUserMapDTO result = roleUserMapService.save(roleUserMapDTO);
        return ResponseEntity
            .created(new URI("/api/role-user-maps/" + result.getRoleUserMapId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getRoleUserMapId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /role-user-maps/:roleUserMapId} : Updates an existing roleUserMap.
     *
     * @param roleUserMapId the id of the roleUserMapDTO to save.
     * @param roleUserMapDTO the roleUserMapDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated roleUserMapDTO,
     * or with status {@code 400 (Bad Request)} if the roleUserMapDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the roleUserMapDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/role-user-maps/{roleUserMapId}")
    public ResponseEntity<RoleUserMapDTO> updateRoleUserMap(
        @PathVariable(value = "roleUserMapId", required = false) final Long roleUserMapId,
        @RequestBody RoleUserMapDTO roleUserMapDTO
    ) throws URISyntaxException {
        log.debug("REST request to update RoleUserMap : {}, {}", roleUserMapId, roleUserMapDTO);
        if (roleUserMapDTO.getRoleUserMapId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(roleUserMapId, roleUserMapDTO.getRoleUserMapId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!roleUserMapRepository.existsById(roleUserMapId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RoleUserMapDTO result = roleUserMapService.update(roleUserMapDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, roleUserMapDTO.getRoleUserMapId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /role-user-maps/:roleUserMapId} : Partial updates given fields of an existing roleUserMap, field will ignore if it is null
     *
     * @param roleUserMapId the id of the roleUserMapDTO to save.
     * @param roleUserMapDTO the roleUserMapDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated roleUserMapDTO,
     * or with status {@code 400 (Bad Request)} if the roleUserMapDTO is not valid,
     * or with status {@code 404 (Not Found)} if the roleUserMapDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the roleUserMapDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/role-user-maps/{roleUserMapId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RoleUserMapDTO> partialUpdateRoleUserMap(
        @PathVariable(value = "roleUserMapId", required = false) final Long roleUserMapId,
        @RequestBody RoleUserMapDTO roleUserMapDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update RoleUserMap partially : {}, {}", roleUserMapId, roleUserMapDTO);
        if (roleUserMapDTO.getRoleUserMapId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(roleUserMapId, roleUserMapDTO.getRoleUserMapId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!roleUserMapRepository.existsById(roleUserMapId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RoleUserMapDTO> result = roleUserMapService.partialUpdate(roleUserMapDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, roleUserMapDTO.getRoleUserMapId().toString())
        );
    }

    /**
     * {@code GET  /role-user-maps} : get all the roleUserMaps.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of roleUserMaps in body.
     */
    @GetMapping("/role-user-maps")
    public ResponseEntity<List<RoleUserMapDTO>> getAllRoleUserMaps(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of RoleUserMaps");
        Page<RoleUserMapDTO> page = roleUserMapService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /role-user-maps/:id} : get the "id" roleUserMap.
     *
     * @param id the id of the roleUserMapDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the roleUserMapDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/role-user-maps/{id}")
    public ResponseEntity<RoleUserMapDTO> getRoleUserMap(@PathVariable Long id) {
        log.debug("REST request to get RoleUserMap : {}", id);
        Optional<RoleUserMapDTO> roleUserMapDTO = roleUserMapService.findOne(id);
        return ResponseUtil.wrapOrNotFound(roleUserMapDTO);
    }

    /**
     * {@code DELETE  /role-user-maps/:id} : delete the "id" roleUserMap.
     *
     * @param id the id of the roleUserMapDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/role-user-maps/{id}")
    public ResponseEntity<Void> deleteRoleUserMap(@PathVariable Long id) {
        log.debug("REST request to delete RoleUserMap : {}", id);
        roleUserMapService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
