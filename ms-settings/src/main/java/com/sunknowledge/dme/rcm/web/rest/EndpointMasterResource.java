package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.EndpointMasterRepository;
import com.sunknowledge.dme.rcm.service.EndpointMasterService;
import com.sunknowledge.dme.rcm.service.dto.EndpointMasterDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.EndpointMaster}.
 */
@RestController
@RequestMapping("/api")
public class EndpointMasterResource {

    private final Logger log = LoggerFactory.getLogger(EndpointMasterResource.class);

    private static final String ENTITY_NAME = "settingsEndpointMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EndpointMasterService endpointMasterService;

    private final EndpointMasterRepository endpointMasterRepository;

    public EndpointMasterResource(EndpointMasterService endpointMasterService, EndpointMasterRepository endpointMasterRepository) {
        this.endpointMasterService = endpointMasterService;
        this.endpointMasterRepository = endpointMasterRepository;
    }

    /**
     * {@code POST  /endpoint-masters} : Create a new endpointMaster.
     *
     * @param endpointMasterDTO the endpointMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new endpointMasterDTO, or with status {@code 400 (Bad Request)} if the endpointMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/endpoint-masters")
    public ResponseEntity<EndpointMasterDTO> createEndpointMaster(@RequestBody EndpointMasterDTO endpointMasterDTO)
        throws URISyntaxException {
        log.debug("REST request to save EndpointMaster : {}", endpointMasterDTO);
        if (endpointMasterDTO.getEndpointId() != null) {
            throw new BadRequestAlertException("A new endpointMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EndpointMasterDTO result = endpointMasterService.save(endpointMasterDTO);
        return ResponseEntity
            .created(new URI("/api/endpoint-masters/" + result.getEndpointId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getEndpointId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /endpoint-masters/:endpointId} : Updates an existing endpointMaster.
     *
     * @param endpointId the id of the endpointMasterDTO to save.
     * @param endpointMasterDTO the endpointMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated endpointMasterDTO,
     * or with status {@code 400 (Bad Request)} if the endpointMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the endpointMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/endpoint-masters/{endpointId}")
    public ResponseEntity<EndpointMasterDTO> updateEndpointMaster(
        @PathVariable(value = "endpointId", required = false) final Long endpointId,
        @RequestBody EndpointMasterDTO endpointMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update EndpointMaster : {}, {}", endpointId, endpointMasterDTO);
        if (endpointMasterDTO.getEndpointId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(endpointId, endpointMasterDTO.getEndpointId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!endpointMasterRepository.existsById(endpointId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        EndpointMasterDTO result = endpointMasterService.update(endpointMasterDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, endpointMasterDTO.getEndpointId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /endpoint-masters/:endpointId} : Partial updates given fields of an existing endpointMaster, field will ignore if it is null
     *
     * @param endpointId the id of the endpointMasterDTO to save.
     * @param endpointMasterDTO the endpointMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated endpointMasterDTO,
     * or with status {@code 400 (Bad Request)} if the endpointMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the endpointMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the endpointMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/endpoint-masters/{endpointId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EndpointMasterDTO> partialUpdateEndpointMaster(
        @PathVariable(value = "endpointId", required = false) final Long endpointId,
        @RequestBody EndpointMasterDTO endpointMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update EndpointMaster partially : {}, {}", endpointId, endpointMasterDTO);
        if (endpointMasterDTO.getEndpointId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(endpointId, endpointMasterDTO.getEndpointId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!endpointMasterRepository.existsById(endpointId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EndpointMasterDTO> result = endpointMasterService.partialUpdate(endpointMasterDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, endpointMasterDTO.getEndpointId().toString())
        );
    }

    /**
     * {@code GET  /endpoint-masters} : get all the endpointMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of endpointMasters in body.
     */
    @GetMapping("/endpoint-masters")
    public ResponseEntity<List<EndpointMasterDTO>> getAllEndpointMasters(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of EndpointMasters");
        Page<EndpointMasterDTO> page = endpointMasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /endpoint-masters/:id} : get the "id" endpointMaster.
     *
     * @param id the id of the endpointMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the endpointMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/endpoint-masters/{id}")
    public ResponseEntity<EndpointMasterDTO> getEndpointMaster(@PathVariable Long id) {
        log.debug("REST request to get EndpointMaster : {}", id);
        Optional<EndpointMasterDTO> endpointMasterDTO = endpointMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(endpointMasterDTO);
    }

    /**
     * {@code DELETE  /endpoint-masters/:id} : delete the "id" endpointMaster.
     *
     * @param id the id of the endpointMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/endpoint-masters/{id}")
    public ResponseEntity<Void> deleteEndpointMaster(@PathVariable Long id) {
        log.debug("REST request to delete EndpointMaster : {}", id);
        endpointMasterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
