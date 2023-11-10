package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.BranchOfficeRepository;
import com.sunknowledge.dme.rcm.service.BranchOfficeService;
import com.sunknowledge.dme.rcm.service.dto.BranchOfficeDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.BranchOffice}.
 */
@RestController
@RequestMapping("/api")
public class BranchOfficeResource {

    private final Logger log = LoggerFactory.getLogger(BranchOfficeResource.class);

    private static final String ENTITY_NAME = "settingsBranchOffice";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BranchOfficeService branchOfficeService;

    private final BranchOfficeRepository branchOfficeRepository;

    public BranchOfficeResource(BranchOfficeService branchOfficeService, BranchOfficeRepository branchOfficeRepository) {
        this.branchOfficeService = branchOfficeService;
        this.branchOfficeRepository = branchOfficeRepository;
    }

    /**
     * {@code POST  /branch-offices} : Create a new branchOffice.
     *
     * @param branchOfficeDTO the branchOfficeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new branchOfficeDTO, or with status {@code 400 (Bad Request)} if the branchOffice has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/branch-offices")
    public ResponseEntity<BranchOfficeDTO> createBranchOffice(@RequestBody BranchOfficeDTO branchOfficeDTO) throws URISyntaxException {
        log.debug("REST request to save BranchOffice : {}", branchOfficeDTO);
        if (branchOfficeDTO.getBranchId() != null) {
            throw new BadRequestAlertException("A new branchOffice cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BranchOfficeDTO result = branchOfficeService.save(branchOfficeDTO);
        return ResponseEntity
            .created(new URI("/api/branch-offices/" + result.getBranchId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getBranchId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /branch-offices/:branchId} : Updates an existing branchOffice.
     *
     * @param branchId the id of the branchOfficeDTO to save.
     * @param branchOfficeDTO the branchOfficeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated branchOfficeDTO,
     * or with status {@code 400 (Bad Request)} if the branchOfficeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the branchOfficeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/branch-offices/{branchId}")
    public ResponseEntity<BranchOfficeDTO> updateBranchOffice(
        @PathVariable(value = "branchId", required = false) final Long branchId,
        @RequestBody BranchOfficeDTO branchOfficeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update BranchOffice : {}, {}", branchId, branchOfficeDTO);
        if (branchOfficeDTO.getBranchId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(branchId, branchOfficeDTO.getBranchId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!branchOfficeRepository.existsById(branchId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BranchOfficeDTO result = branchOfficeService.update(branchOfficeDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, branchOfficeDTO.getBranchId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /branch-offices/:branchId} : Partial updates given fields of an existing branchOffice, field will ignore if it is null
     *
     * @param branchId the id of the branchOfficeDTO to save.
     * @param branchOfficeDTO the branchOfficeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated branchOfficeDTO,
     * or with status {@code 400 (Bad Request)} if the branchOfficeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the branchOfficeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the branchOfficeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/branch-offices/{branchId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BranchOfficeDTO> partialUpdateBranchOffice(
        @PathVariable(value = "branchId", required = false) final Long branchId,
        @RequestBody BranchOfficeDTO branchOfficeDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update BranchOffice partially : {}, {}", branchId, branchOfficeDTO);
        if (branchOfficeDTO.getBranchId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(branchId, branchOfficeDTO.getBranchId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!branchOfficeRepository.existsById(branchId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BranchOfficeDTO> result = branchOfficeService.partialUpdate(branchOfficeDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, branchOfficeDTO.getBranchId().toString())
        );
    }

    /**
     * {@code GET  /branch-offices} : get all the branchOffices.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of branchOffices in body.
     */
    @GetMapping("/branch-offices")
    public ResponseEntity<List<BranchOfficeDTO>> getAllBranchOffices(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of BranchOffices");
        Page<BranchOfficeDTO> page = branchOfficeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /branch-offices/:id} : get the "id" branchOffice.
     *
     * @param id the id of the branchOfficeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the branchOfficeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/branch-offices/{id}")
    public ResponseEntity<BranchOfficeDTO> getBranchOffice(@PathVariable Long id) {
        log.debug("REST request to get BranchOffice : {}", id);
        Optional<BranchOfficeDTO> branchOfficeDTO = branchOfficeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(branchOfficeDTO);
    }

    /**
     * {@code DELETE  /branch-offices/:id} : delete the "id" branchOffice.
     *
     * @param id the id of the branchOfficeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/branch-offices/{id}")
    public ResponseEntity<Void> deleteBranchOffice(@PathVariable Long id) {
        log.debug("REST request to delete BranchOffice : {}", id);
        branchOfficeService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
