package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.RoleFunctionalityMapRepository;
import com.sunknowledge.dme.rcm.service.RoleFunctionalityMapService;
import com.sunknowledge.dme.rcm.service.dto.RoleFunctionalityMapDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.RoleFunctionalityMap}.
 */
@RestController
@RequestMapping("/api")
public class RoleFunctionalityMapResource {

    private final Logger log = LoggerFactory.getLogger(RoleFunctionalityMapResource.class);

    private static final String ENTITY_NAME = "settingsRoleFunctionalityMap";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RoleFunctionalityMapService roleFunctionalityMapService;

    private final RoleFunctionalityMapRepository roleFunctionalityMapRepository;

    public RoleFunctionalityMapResource(
        RoleFunctionalityMapService roleFunctionalityMapService,
        RoleFunctionalityMapRepository roleFunctionalityMapRepository
    ) {
        this.roleFunctionalityMapService = roleFunctionalityMapService;
        this.roleFunctionalityMapRepository = roleFunctionalityMapRepository;
    }

    /**
     * {@code POST  /role-functionality-maps} : Create a new roleFunctionalityMap.
     *
     * @param roleFunctionalityMapDTO the roleFunctionalityMapDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new roleFunctionalityMapDTO, or with status {@code 400 (Bad Request)} if the roleFunctionalityMap has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/role-functionality-maps")
    public ResponseEntity<RoleFunctionalityMapDTO> createRoleFunctionalityMap(@RequestBody RoleFunctionalityMapDTO roleFunctionalityMapDTO)
        throws URISyntaxException {
        log.debug("REST request to save RoleFunctionalityMap : {}", roleFunctionalityMapDTO);
        if (roleFunctionalityMapDTO.getRoleFunctionalityMapId() != null) {
            throw new BadRequestAlertException("A new roleFunctionalityMap cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RoleFunctionalityMapDTO result = roleFunctionalityMapService.save(roleFunctionalityMapDTO);
        return ResponseEntity
            .created(new URI("/api/role-functionality-maps/" + result.getRoleFunctionalityMapId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getRoleFunctionalityMapId().toString())
            )
            .body(result);
    }

    /**
     * {@code PUT  /role-functionality-maps/:roleFunctionalityMapId} : Updates an existing roleFunctionalityMap.
     *
     * @param roleFunctionalityMapId the id of the roleFunctionalityMapDTO to save.
     * @param roleFunctionalityMapDTO the roleFunctionalityMapDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated roleFunctionalityMapDTO,
     * or with status {@code 400 (Bad Request)} if the roleFunctionalityMapDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the roleFunctionalityMapDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/role-functionality-maps/{roleFunctionalityMapId}")
    public ResponseEntity<RoleFunctionalityMapDTO> updateRoleFunctionalityMap(
        @PathVariable(value = "roleFunctionalityMapId", required = false) final Long roleFunctionalityMapId,
        @RequestBody RoleFunctionalityMapDTO roleFunctionalityMapDTO
    ) throws URISyntaxException {
        log.debug("REST request to update RoleFunctionalityMap : {}, {}", roleFunctionalityMapId, roleFunctionalityMapDTO);
        if (roleFunctionalityMapDTO.getRoleFunctionalityMapId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(roleFunctionalityMapId, roleFunctionalityMapDTO.getRoleFunctionalityMapId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!roleFunctionalityMapRepository.existsById(roleFunctionalityMapId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RoleFunctionalityMapDTO result = roleFunctionalityMapService.update(roleFunctionalityMapDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    roleFunctionalityMapDTO.getRoleFunctionalityMapId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /role-functionality-maps/:roleFunctionalityMapId} : Partial updates given fields of an existing roleFunctionalityMap, field will ignore if it is null
     *
     * @param roleFunctionalityMapId the id of the roleFunctionalityMapDTO to save.
     * @param roleFunctionalityMapDTO the roleFunctionalityMapDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated roleFunctionalityMapDTO,
     * or with status {@code 400 (Bad Request)} if the roleFunctionalityMapDTO is not valid,
     * or with status {@code 404 (Not Found)} if the roleFunctionalityMapDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the roleFunctionalityMapDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/role-functionality-maps/{roleFunctionalityMapId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public ResponseEntity<RoleFunctionalityMapDTO> partialUpdateRoleFunctionalityMap(
        @PathVariable(value = "roleFunctionalityMapId", required = false) final Long roleFunctionalityMapId,
        @RequestBody RoleFunctionalityMapDTO roleFunctionalityMapDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update RoleFunctionalityMap partially : {}, {}",
            roleFunctionalityMapId,
            roleFunctionalityMapDTO
        );
        if (roleFunctionalityMapDTO.getRoleFunctionalityMapId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(roleFunctionalityMapId, roleFunctionalityMapDTO.getRoleFunctionalityMapId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!roleFunctionalityMapRepository.existsById(roleFunctionalityMapId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RoleFunctionalityMapDTO> result = roleFunctionalityMapService.partialUpdate(roleFunctionalityMapDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                false,
                ENTITY_NAME,
                roleFunctionalityMapDTO.getRoleFunctionalityMapId().toString()
            )
        );
    }

    /**
     * {@code GET  /role-functionality-maps} : get all the roleFunctionalityMaps.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of roleFunctionalityMaps in body.
     */
    @GetMapping("/role-functionality-maps")
    public ResponseEntity<List<RoleFunctionalityMapDTO>> getAllRoleFunctionalityMaps(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of RoleFunctionalityMaps");
        Page<RoleFunctionalityMapDTO> page = roleFunctionalityMapService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /role-functionality-maps/:id} : get the "id" roleFunctionalityMap.
     *
     * @param id the id of the roleFunctionalityMapDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the roleFunctionalityMapDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/role-functionality-maps/{id}")
    public ResponseEntity<RoleFunctionalityMapDTO> getRoleFunctionalityMap(@PathVariable Long id) {
        log.debug("REST request to get RoleFunctionalityMap : {}", id);
        Optional<RoleFunctionalityMapDTO> roleFunctionalityMapDTO = roleFunctionalityMapService.findOne(id);
        return ResponseUtil.wrapOrNotFound(roleFunctionalityMapDTO);
    }

    /**
     * {@code DELETE  /role-functionality-maps/:id} : delete the "id" roleFunctionalityMap.
     *
     * @param id the id of the roleFunctionalityMapDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/role-functionality-maps/{id}")
    public ResponseEntity<Void> deleteRoleFunctionalityMap(@PathVariable Long id) {
        log.debug("REST request to delete RoleFunctionalityMap : {}", id);
        roleFunctionalityMapService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
