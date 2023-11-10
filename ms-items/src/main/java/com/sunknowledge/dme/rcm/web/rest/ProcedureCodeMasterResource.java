package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ProcedureCodeMasterRepository;
import com.sunknowledge.dme.rcm.service.ProcedureCodeMasterService;
import com.sunknowledge.dme.rcm.service.dto.ProcedureCodeMasterDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ProcedureCodeMaster}.
 */
@RestController
@RequestMapping("/api")
public class ProcedureCodeMasterResource {

    private final Logger log = LoggerFactory.getLogger(ProcedureCodeMasterResource.class);

    private static final String ENTITY_NAME = "itemsProcedureCodeMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProcedureCodeMasterService procedureCodeMasterService;

    private final ProcedureCodeMasterRepository procedureCodeMasterRepository;

    public ProcedureCodeMasterResource(
        ProcedureCodeMasterService procedureCodeMasterService,
        ProcedureCodeMasterRepository procedureCodeMasterRepository
    ) {
        this.procedureCodeMasterService = procedureCodeMasterService;
        this.procedureCodeMasterRepository = procedureCodeMasterRepository;
    }

    /**
     * {@code POST  /procedure-code-masters} : Create a new procedureCodeMaster.
     *
     * @param procedureCodeMasterDTO the procedureCodeMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new procedureCodeMasterDTO, or with status {@code 400 (Bad Request)} if the procedureCodeMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/procedure-code-masters")
    public ResponseEntity<ProcedureCodeMasterDTO> createProcedureCodeMaster(@RequestBody ProcedureCodeMasterDTO procedureCodeMasterDTO)
        throws URISyntaxException {
        log.debug("REST request to save ProcedureCodeMaster : {}", procedureCodeMasterDTO);
        if (procedureCodeMasterDTO.getProcedureCodeId() != null) {
            throw new BadRequestAlertException("A new procedureCodeMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProcedureCodeMasterDTO result = procedureCodeMasterService.save(procedureCodeMasterDTO);
        return ResponseEntity
            .created(new URI("/api/procedure-code-masters/" + result.getProcedureCodeId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getProcedureCodeId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /procedure-code-masters/:procedureCodeId} : Updates an existing procedureCodeMaster.
     *
     * @param procedureCodeId the id of the procedureCodeMasterDTO to save.
     * @param procedureCodeMasterDTO the procedureCodeMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated procedureCodeMasterDTO,
     * or with status {@code 400 (Bad Request)} if the procedureCodeMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the procedureCodeMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/procedure-code-masters/{procedureCodeId}")
    public ResponseEntity<ProcedureCodeMasterDTO> updateProcedureCodeMaster(
        @PathVariable(value = "procedureCodeId", required = false) final Long procedureCodeId,
        @RequestBody ProcedureCodeMasterDTO procedureCodeMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ProcedureCodeMaster : {}, {}", procedureCodeId, procedureCodeMasterDTO);
        if (procedureCodeMasterDTO.getProcedureCodeId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(procedureCodeId, procedureCodeMasterDTO.getProcedureCodeId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!procedureCodeMasterRepository.existsById(procedureCodeId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ProcedureCodeMasterDTO result = procedureCodeMasterService.update(procedureCodeMasterDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    procedureCodeMasterDTO.getProcedureCodeId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /procedure-code-masters/:procedureCodeId} : Partial updates given fields of an existing procedureCodeMaster, field will ignore if it is null
     *
     * @param procedureCodeId the id of the procedureCodeMasterDTO to save.
     * @param procedureCodeMasterDTO the procedureCodeMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated procedureCodeMasterDTO,
     * or with status {@code 400 (Bad Request)} if the procedureCodeMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the procedureCodeMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the procedureCodeMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/procedure-code-masters/{procedureCodeId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ProcedureCodeMasterDTO> partialUpdateProcedureCodeMaster(
        @PathVariable(value = "procedureCodeId", required = false) final Long procedureCodeId,
        @RequestBody ProcedureCodeMasterDTO procedureCodeMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ProcedureCodeMaster partially : {}, {}", procedureCodeId, procedureCodeMasterDTO);
        if (procedureCodeMasterDTO.getProcedureCodeId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(procedureCodeId, procedureCodeMasterDTO.getProcedureCodeId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!procedureCodeMasterRepository.existsById(procedureCodeId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ProcedureCodeMasterDTO> result = procedureCodeMasterService.partialUpdate(procedureCodeMasterDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, procedureCodeMasterDTO.getProcedureCodeId().toString())
        );
    }

    /**
     * {@code GET  /procedure-code-masters} : get all the procedureCodeMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of procedureCodeMasters in body.
     */
    @GetMapping("/procedure-code-masters")
    public ResponseEntity<List<ProcedureCodeMasterDTO>> getAllProcedureCodeMasters(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ProcedureCodeMasters");
        Page<ProcedureCodeMasterDTO> page = procedureCodeMasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /procedure-code-masters/:id} : get the "id" procedureCodeMaster.
     *
     * @param id the id of the procedureCodeMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the procedureCodeMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/procedure-code-masters/{id}")
    public ResponseEntity<ProcedureCodeMasterDTO> getProcedureCodeMaster(@PathVariable Long id) {
        log.debug("REST request to get ProcedureCodeMaster : {}", id);
        Optional<ProcedureCodeMasterDTO> procedureCodeMasterDTO = procedureCodeMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(procedureCodeMasterDTO);
    }

    /**
     * {@code DELETE  /procedure-code-masters/:id} : delete the "id" procedureCodeMaster.
     *
     * @param id the id of the procedureCodeMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/procedure-code-masters/{id}")
    public ResponseEntity<Void> deleteProcedureCodeMaster(@PathVariable Long id) {
        log.debug("REST request to delete ProcedureCodeMaster : {}", id);
        procedureCodeMasterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
