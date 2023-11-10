package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ManufacturerRepository;
import com.sunknowledge.dme.rcm.service.ManufacturerService;
import com.sunknowledge.dme.rcm.service.dto.ManufacturerDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.Manufacturer}.
 */
@RestController
@RequestMapping("/api")
public class ManufacturerResource {

    private final Logger log = LoggerFactory.getLogger(ManufacturerResource.class);

    private static final String ENTITY_NAME = "itemsManufacturer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ManufacturerService manufacturerService;

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerResource(ManufacturerService manufacturerService, ManufacturerRepository manufacturerRepository) {
        this.manufacturerService = manufacturerService;
        this.manufacturerRepository = manufacturerRepository;
    }

    /**
     * {@code POST  /manufacturers} : Create a new manufacturer.
     *
     * @param manufacturerDTO the manufacturerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new manufacturerDTO, or with status {@code 400 (Bad Request)} if the manufacturer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/manufacturers")
    public ResponseEntity<ManufacturerDTO> createManufacturer(@RequestBody ManufacturerDTO manufacturerDTO) throws URISyntaxException {
        log.debug("REST request to save Manufacturer : {}", manufacturerDTO);
        if (manufacturerDTO.getManufacturerId() != null) {
            throw new BadRequestAlertException("A new manufacturer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ManufacturerDTO result = manufacturerService.save(manufacturerDTO);
        return ResponseEntity
            .created(new URI("/api/manufacturers/" + result.getManufacturerId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getManufacturerId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /manufacturers/:manufacturerId} : Updates an existing manufacturer.
     *
     * @param manufacturerId the id of the manufacturerDTO to save.
     * @param manufacturerDTO the manufacturerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated manufacturerDTO,
     * or with status {@code 400 (Bad Request)} if the manufacturerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the manufacturerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/manufacturers/{manufacturerId}")
    public ResponseEntity<ManufacturerDTO> updateManufacturer(
        @PathVariable(value = "manufacturerId", required = false) final Long manufacturerId,
        @RequestBody ManufacturerDTO manufacturerDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Manufacturer : {}, {}", manufacturerId, manufacturerDTO);
        if (manufacturerDTO.getManufacturerId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(manufacturerId, manufacturerDTO.getManufacturerId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!manufacturerRepository.existsById(manufacturerId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ManufacturerDTO result = manufacturerService.update(manufacturerDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, manufacturerDTO.getManufacturerId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /manufacturers/:manufacturerId} : Partial updates given fields of an existing manufacturer, field will ignore if it is null
     *
     * @param manufacturerId the id of the manufacturerDTO to save.
     * @param manufacturerDTO the manufacturerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated manufacturerDTO,
     * or with status {@code 400 (Bad Request)} if the manufacturerDTO is not valid,
     * or with status {@code 404 (Not Found)} if the manufacturerDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the manufacturerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/manufacturers/{manufacturerId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ManufacturerDTO> partialUpdateManufacturer(
        @PathVariable(value = "manufacturerId", required = false) final Long manufacturerId,
        @RequestBody ManufacturerDTO manufacturerDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Manufacturer partially : {}, {}", manufacturerId, manufacturerDTO);
        if (manufacturerDTO.getManufacturerId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(manufacturerId, manufacturerDTO.getManufacturerId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!manufacturerRepository.existsById(manufacturerId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ManufacturerDTO> result = manufacturerService.partialUpdate(manufacturerDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, manufacturerDTO.getManufacturerId().toString())
        );
    }

    /**
     * {@code GET  /manufacturers} : get all the manufacturers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of manufacturers in body.
     */
    @GetMapping("/manufacturers")
    public ResponseEntity<List<ManufacturerDTO>> getAllManufacturers(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Manufacturers");
        Page<ManufacturerDTO> page = manufacturerService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /manufacturers/:id} : get the "id" manufacturer.
     *
     * @param id the id of the manufacturerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the manufacturerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/manufacturers/{id}")
    public ResponseEntity<ManufacturerDTO> getManufacturer(@PathVariable Long id) {
        log.debug("REST request to get Manufacturer : {}", id);
        Optional<ManufacturerDTO> manufacturerDTO = manufacturerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(manufacturerDTO);
    }

    /**
     * {@code DELETE  /manufacturers/:id} : delete the "id" manufacturer.
     *
     * @param id the id of the manufacturerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/manufacturers/{id}")
    public ResponseEntity<Void> deleteManufacturer(@PathVariable Long id) {
        log.debug("REST request to delete Manufacturer : {}", id);
        manufacturerService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
