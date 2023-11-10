package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.HcpcsDocTypeRepository;
import com.sunknowledge.dme.rcm.service.HcpcsDocTypeService;
import com.sunknowledge.dme.rcm.service.dto.HcpcsDocTypeDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.HcpcsDocType}.
 */
@RestController
@RequestMapping("/api")
public class HcpcsDocTypeResource {

    private final Logger log = LoggerFactory.getLogger(HcpcsDocTypeResource.class);

    private static final String ENTITY_NAME = "deliveryHcpcsDocType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HcpcsDocTypeService hcpcsDocTypeService;

    private final HcpcsDocTypeRepository hcpcsDocTypeRepository;

    public HcpcsDocTypeResource(HcpcsDocTypeService hcpcsDocTypeService, HcpcsDocTypeRepository hcpcsDocTypeRepository) {
        this.hcpcsDocTypeService = hcpcsDocTypeService;
        this.hcpcsDocTypeRepository = hcpcsDocTypeRepository;
    }

    /**
     * {@code POST  /hcpcs-doc-types} : Create a new hcpcsDocType.
     *
     * @param hcpcsDocTypeDTO the hcpcsDocTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new hcpcsDocTypeDTO, or with status {@code 400 (Bad Request)} if the hcpcsDocType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/hcpcs-doc-types")
    public ResponseEntity<HcpcsDocTypeDTO> createHcpcsDocType(@Valid @RequestBody HcpcsDocTypeDTO hcpcsDocTypeDTO)
        throws URISyntaxException {
        log.debug("REST request to save HcpcsDocType : {}", hcpcsDocTypeDTO);
        if (hcpcsDocTypeDTO.getHcpcsDoctypeId() != null) {
            throw new BadRequestAlertException("A new hcpcsDocType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HcpcsDocTypeDTO result = hcpcsDocTypeService.save(hcpcsDocTypeDTO);
        return ResponseEntity
            .created(new URI("/api/hcpcs-doc-types/" + result.getHcpcsDoctypeId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getHcpcsDoctypeId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /hcpcs-doc-types/:hcpcsDoctypeId} : Updates an existing hcpcsDocType.
     *
     * @param hcpcsDoctypeId the id of the hcpcsDocTypeDTO to save.
     * @param hcpcsDocTypeDTO the hcpcsDocTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated hcpcsDocTypeDTO,
     * or with status {@code 400 (Bad Request)} if the hcpcsDocTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the hcpcsDocTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/hcpcs-doc-types/{hcpcsDoctypeId}")
    public ResponseEntity<HcpcsDocTypeDTO> updateHcpcsDocType(
        @PathVariable(value = "hcpcsDoctypeId", required = false) final Long hcpcsDoctypeId,
        @Valid @RequestBody HcpcsDocTypeDTO hcpcsDocTypeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update HcpcsDocType : {}, {}", hcpcsDoctypeId, hcpcsDocTypeDTO);
        if (hcpcsDocTypeDTO.getHcpcsDoctypeId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(hcpcsDoctypeId, hcpcsDocTypeDTO.getHcpcsDoctypeId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!hcpcsDocTypeRepository.existsById(hcpcsDoctypeId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        HcpcsDocTypeDTO result = hcpcsDocTypeService.update(hcpcsDocTypeDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, hcpcsDocTypeDTO.getHcpcsDoctypeId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /hcpcs-doc-types/:hcpcsDoctypeId} : Partial updates given fields of an existing hcpcsDocType, field will ignore if it is null
     *
     * @param hcpcsDoctypeId the id of the hcpcsDocTypeDTO to save.
     * @param hcpcsDocTypeDTO the hcpcsDocTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated hcpcsDocTypeDTO,
     * or with status {@code 400 (Bad Request)} if the hcpcsDocTypeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the hcpcsDocTypeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the hcpcsDocTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/hcpcs-doc-types/{hcpcsDoctypeId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<HcpcsDocTypeDTO> partialUpdateHcpcsDocType(
        @PathVariable(value = "hcpcsDoctypeId", required = false) final Long hcpcsDoctypeId,
        @NotNull @RequestBody HcpcsDocTypeDTO hcpcsDocTypeDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update HcpcsDocType partially : {}, {}", hcpcsDoctypeId, hcpcsDocTypeDTO);
        if (hcpcsDocTypeDTO.getHcpcsDoctypeId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(hcpcsDoctypeId, hcpcsDocTypeDTO.getHcpcsDoctypeId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!hcpcsDocTypeRepository.existsById(hcpcsDoctypeId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<HcpcsDocTypeDTO> result = hcpcsDocTypeService.partialUpdate(hcpcsDocTypeDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, hcpcsDocTypeDTO.getHcpcsDoctypeId().toString())
        );
    }

    /**
     * {@code GET  /hcpcs-doc-types} : get all the hcpcsDocTypes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of hcpcsDocTypes in body.
     */
    @GetMapping("/hcpcs-doc-types")
    public ResponseEntity<List<HcpcsDocTypeDTO>> getAllHcpcsDocTypes(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of HcpcsDocTypes");
        Page<HcpcsDocTypeDTO> page = hcpcsDocTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /hcpcs-doc-types/:id} : get the "id" hcpcsDocType.
     *
     * @param id the id of the hcpcsDocTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the hcpcsDocTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/hcpcs-doc-types/{id}")
    public ResponseEntity<HcpcsDocTypeDTO> getHcpcsDocType(@PathVariable Long id) {
        log.debug("REST request to get HcpcsDocType : {}", id);
        Optional<HcpcsDocTypeDTO> hcpcsDocTypeDTO = hcpcsDocTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(hcpcsDocTypeDTO);
    }

    /**
     * {@code DELETE  /hcpcs-doc-types/:id} : delete the "id" hcpcsDocType.
     *
     * @param id the id of the hcpcsDocTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/hcpcs-doc-types/{id}")
    public ResponseEntity<Void> deleteHcpcsDocType(@PathVariable Long id) {
        log.debug("REST request to delete HcpcsDocType : {}", id);
        hcpcsDocTypeService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
