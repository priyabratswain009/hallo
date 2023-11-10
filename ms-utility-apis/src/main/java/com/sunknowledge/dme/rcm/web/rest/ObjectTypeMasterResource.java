package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ObjectTypeMasterRepository;
import com.sunknowledge.dme.rcm.service.ObjectTypeMasterService;
import com.sunknowledge.dme.rcm.service.dto.ObjectTypeMasterDTO;
import com.sunknowledge.dme.rcm.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ObjectTypeMaster}.
 */
@RestController
@RequestMapping("/api")
public class ObjectTypeMasterResource {

    private final Logger log = LoggerFactory.getLogger(ObjectTypeMasterResource.class);

    private static final String ENTITY_NAME = "utilityapisObjectTypeMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ObjectTypeMasterService objectTypeMasterService;

    private final ObjectTypeMasterRepository objectTypeMasterRepository;

    public ObjectTypeMasterResource(
        ObjectTypeMasterService objectTypeMasterService,
        ObjectTypeMasterRepository objectTypeMasterRepository
    ) {
        this.objectTypeMasterService = objectTypeMasterService;
        this.objectTypeMasterRepository = objectTypeMasterRepository;
    }

    /**
     * {@code POST  /object-type-masters} : Create a new objectTypeMaster.
     *
     * @param objectTypeMasterDTO the objectTypeMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new objectTypeMasterDTO, or with status {@code 400 (Bad Request)} if the objectTypeMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/object-type-masters")
    public ResponseEntity<ObjectTypeMasterDTO> createObjectTypeMaster(@Valid @RequestBody ObjectTypeMasterDTO objectTypeMasterDTO)
        throws URISyntaxException {
        log.debug("REST request to save ObjectTypeMaster : {}", objectTypeMasterDTO);
        if (objectTypeMasterDTO.getObjectId() != null) {
            throw new BadRequestAlertException("A new objectTypeMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ObjectTypeMasterDTO result = objectTypeMasterService.save(objectTypeMasterDTO);
        return ResponseEntity
            .created(new URI("/api/object-type-masters/" + result.getObjectId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getObjectId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /object-type-masters/:objectId} : Updates an existing objectTypeMaster.
     *
     * @param objectId the id of the objectTypeMasterDTO to save.
     * @param objectTypeMasterDTO the objectTypeMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated objectTypeMasterDTO,
     * or with status {@code 400 (Bad Request)} if the objectTypeMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the objectTypeMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/object-type-masters/{objectId}")
    public ResponseEntity<ObjectTypeMasterDTO> updateObjectTypeMaster(
        @PathVariable(value = "objectId", required = false) final Long objectId,
        @Valid @RequestBody ObjectTypeMasterDTO objectTypeMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ObjectTypeMaster : {}, {}", objectId, objectTypeMasterDTO);
        if (objectTypeMasterDTO.getObjectId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(objectId, objectTypeMasterDTO.getObjectId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!objectTypeMasterRepository.existsById(objectId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ObjectTypeMasterDTO result = objectTypeMasterService.update(objectTypeMasterDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, objectTypeMasterDTO.getObjectId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /object-type-masters/:objectId} : Partial updates given fields of an existing objectTypeMaster, field will ignore if it is null
     *
     * @param objectId the id of the objectTypeMasterDTO to save.
     * @param objectTypeMasterDTO the objectTypeMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated objectTypeMasterDTO,
     * or with status {@code 400 (Bad Request)} if the objectTypeMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the objectTypeMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the objectTypeMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/object-type-masters/{objectId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ObjectTypeMasterDTO> partialUpdateObjectTypeMaster(
        @PathVariable(value = "objectId", required = false) final Long objectId,
        @NotNull @RequestBody ObjectTypeMasterDTO objectTypeMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ObjectTypeMaster partially : {}, {}", objectId, objectTypeMasterDTO);
        if (objectTypeMasterDTO.getObjectId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(objectId, objectTypeMasterDTO.getObjectId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!objectTypeMasterRepository.existsById(objectId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ObjectTypeMasterDTO> result = objectTypeMasterService.partialUpdate(objectTypeMasterDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, objectTypeMasterDTO.getObjectId().toString())
        );
    }

    /**
     * {@code GET  /object-type-masters} : get all the objectTypeMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of objectTypeMasters in body.
     */
    @GetMapping("/object-type-masters")
    public ResponseEntity<List<ObjectTypeMasterDTO>> getAllObjectTypeMasters(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ObjectTypeMasters");
        Page<ObjectTypeMasterDTO> page = objectTypeMasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /object-type-masters/:id} : get the "id" objectTypeMaster.
     *
     * @param id the id of the objectTypeMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the objectTypeMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/object-type-masters/{id}")
    public ResponseEntity<ObjectTypeMasterDTO> getObjectTypeMaster(@PathVariable Long id) {
        log.debug("REST request to get ObjectTypeMaster : {}", id);
        Optional<ObjectTypeMasterDTO> objectTypeMasterDTO = objectTypeMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(objectTypeMasterDTO);
    }

    /**
     * {@code DELETE  /object-type-masters/:id} : delete the "id" objectTypeMaster.
     *
     * @param id the id of the objectTypeMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/object-type-masters/{id}")
    public ResponseEntity<Void> deleteObjectTypeMaster(@PathVariable Long id) {
        log.debug("REST request to delete ObjectTypeMaster : {}", id);
        objectTypeMasterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
