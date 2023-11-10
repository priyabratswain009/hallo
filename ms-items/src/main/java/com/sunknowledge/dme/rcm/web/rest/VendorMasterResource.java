package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.VendorMasterRepository;
import com.sunknowledge.dme.rcm.service.VendorMasterService;
import com.sunknowledge.dme.rcm.service.dto.VendorMasterDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.VendorMaster}.
 */
@RestController
@RequestMapping("/api")
public class VendorMasterResource {

    private final Logger log = LoggerFactory.getLogger(VendorMasterResource.class);

    private static final String ENTITY_NAME = "itemsVendorMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VendorMasterService vendorMasterService;

    private final VendorMasterRepository vendorMasterRepository;

    public VendorMasterResource(VendorMasterService vendorMasterService, VendorMasterRepository vendorMasterRepository) {
        this.vendorMasterService = vendorMasterService;
        this.vendorMasterRepository = vendorMasterRepository;
    }

    /**
     * {@code POST  /vendor-masters} : Create a new vendorMaster.
     *
     * @param vendorMasterDTO the vendorMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new vendorMasterDTO, or with status {@code 400 (Bad Request)} if the vendorMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/vendor-masters")
    public ResponseEntity<VendorMasterDTO> createVendorMaster(@RequestBody VendorMasterDTO vendorMasterDTO) throws URISyntaxException {
        log.debug("REST request to save VendorMaster : {}", vendorMasterDTO);
        if (vendorMasterDTO.getVendorId() != null) {
            throw new BadRequestAlertException("A new vendorMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VendorMasterDTO result = vendorMasterService.save(vendorMasterDTO);
        return ResponseEntity
            .created(new URI("/api/vendor-masters/" + result.getVendorId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getVendorId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /vendor-masters/:vendorId} : Updates an existing vendorMaster.
     *
     * @param vendorId the id of the vendorMasterDTO to save.
     * @param vendorMasterDTO the vendorMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vendorMasterDTO,
     * or with status {@code 400 (Bad Request)} if the vendorMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the vendorMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/vendor-masters/{vendorId}")
    public ResponseEntity<VendorMasterDTO> updateVendorMaster(
        @PathVariable(value = "vendorId", required = false) final Long vendorId,
        @RequestBody VendorMasterDTO vendorMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update VendorMaster : {}, {}", vendorId, vendorMasterDTO);
        if (vendorMasterDTO.getVendorId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(vendorId, vendorMasterDTO.getVendorId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!vendorMasterRepository.existsById(vendorId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        VendorMasterDTO result = vendorMasterService.update(vendorMasterDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, vendorMasterDTO.getVendorId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /vendor-masters/:vendorId} : Partial updates given fields of an existing vendorMaster, field will ignore if it is null
     *
     * @param vendorId the id of the vendorMasterDTO to save.
     * @param vendorMasterDTO the vendorMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vendorMasterDTO,
     * or with status {@code 400 (Bad Request)} if the vendorMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the vendorMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the vendorMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/vendor-masters/{vendorId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<VendorMasterDTO> partialUpdateVendorMaster(
        @PathVariable(value = "vendorId", required = false) final Long vendorId,
        @RequestBody VendorMasterDTO vendorMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update VendorMaster partially : {}, {}", vendorId, vendorMasterDTO);
        if (vendorMasterDTO.getVendorId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(vendorId, vendorMasterDTO.getVendorId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!vendorMasterRepository.existsById(vendorId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<VendorMasterDTO> result = vendorMasterService.partialUpdate(vendorMasterDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, vendorMasterDTO.getVendorId().toString())
        );
    }

    /**
     * {@code GET  /vendor-masters} : get all the vendorMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vendorMasters in body.
     */
    @GetMapping("/vendor-masters")
    public ResponseEntity<List<VendorMasterDTO>> getAllVendorMasters(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of VendorMasters");
        Page<VendorMasterDTO> page = vendorMasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /vendor-masters/:id} : get the "id" vendorMaster.
     *
     * @param id the id of the vendorMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the vendorMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/vendor-masters/{id}")
    public ResponseEntity<VendorMasterDTO> getVendorMaster(@PathVariable Long id) {
        log.debug("REST request to get VendorMaster : {}", id);
        Optional<VendorMasterDTO> vendorMasterDTO = vendorMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(vendorMasterDTO);
    }

    /**
     * {@code DELETE  /vendor-masters/:id} : delete the "id" vendorMaster.
     *
     * @param id the id of the vendorMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/vendor-masters/{id}")
    public ResponseEntity<Void> deleteVendorMaster(@PathVariable Long id) {
        log.debug("REST request to delete VendorMaster : {}", id);
        vendorMasterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
