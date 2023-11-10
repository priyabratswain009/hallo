package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ClaimsReportFileProcessStatusRepository;
import com.sunknowledge.dme.rcm.service.ClaimsReportFileProcessStatusService;
import com.sunknowledge.dme.rcm.service.dto.ClaimsReportFileProcessStatusDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ClaimsReportFileProcessStatus}.
 */
@RestController
@RequestMapping("/api")
public class ClaimsReportFileProcessStatusResource {

    private final Logger log = LoggerFactory.getLogger(ClaimsReportFileProcessStatusResource.class);

    private static final String ENTITY_NAME = "claimsClaimsReportFileProcessStatus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClaimsReportFileProcessStatusService claimsReportFileProcessStatusService;

    private final ClaimsReportFileProcessStatusRepository claimsReportFileProcessStatusRepository;

    public ClaimsReportFileProcessStatusResource(
        ClaimsReportFileProcessStatusService claimsReportFileProcessStatusService,
        ClaimsReportFileProcessStatusRepository claimsReportFileProcessStatusRepository
    ) {
        this.claimsReportFileProcessStatusService = claimsReportFileProcessStatusService;
        this.claimsReportFileProcessStatusRepository = claimsReportFileProcessStatusRepository;
    }

    /**
     * {@code POST  /claims-report-file-process-statuses} : Create a new claimsReportFileProcessStatus.
     *
     * @param claimsReportFileProcessStatusDTO the claimsReportFileProcessStatusDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new claimsReportFileProcessStatusDTO, or with status {@code 400 (Bad Request)} if the claimsReportFileProcessStatus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/claims-report-file-process-statuses")
    public ResponseEntity<ClaimsReportFileProcessStatusDTO> createClaimsReportFileProcessStatus(
        @Valid @RequestBody ClaimsReportFileProcessStatusDTO claimsReportFileProcessStatusDTO
    ) throws URISyntaxException {
        log.debug("REST request to save ClaimsReportFileProcessStatus : {}", claimsReportFileProcessStatusDTO);
        if (claimsReportFileProcessStatusDTO.getFileStatusId() != null) {
            throw new BadRequestAlertException("A new claimsReportFileProcessStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClaimsReportFileProcessStatusDTO result = claimsReportFileProcessStatusService.save(claimsReportFileProcessStatusDTO);
        return ResponseEntity
            .created(new URI("/api/claims-report-file-process-statuses/" + result.getFileStatusId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getFileStatusId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /claims-report-file-process-statuses/:fileStatusId} : Updates an existing claimsReportFileProcessStatus.
     *
     * @param fileStatusId the id of the claimsReportFileProcessStatusDTO to save.
     * @param claimsReportFileProcessStatusDTO the claimsReportFileProcessStatusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claimsReportFileProcessStatusDTO,
     * or with status {@code 400 (Bad Request)} if the claimsReportFileProcessStatusDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the claimsReportFileProcessStatusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/claims-report-file-process-statuses/{fileStatusId}")
    public ResponseEntity<ClaimsReportFileProcessStatusDTO> updateClaimsReportFileProcessStatus(
        @PathVariable(value = "fileStatusId", required = false) final Long fileStatusId,
        @Valid @RequestBody ClaimsReportFileProcessStatusDTO claimsReportFileProcessStatusDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ClaimsReportFileProcessStatus : {}, {}", fileStatusId, claimsReportFileProcessStatusDTO);
        if (claimsReportFileProcessStatusDTO.getFileStatusId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(fileStatusId, claimsReportFileProcessStatusDTO.getFileStatusId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!claimsReportFileProcessStatusRepository.existsById(fileStatusId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ClaimsReportFileProcessStatusDTO result = claimsReportFileProcessStatusService.update(claimsReportFileProcessStatusDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    claimsReportFileProcessStatusDTO.getFileStatusId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /claims-report-file-process-statuses/:fileStatusId} : Partial updates given fields of an existing claimsReportFileProcessStatus, field will ignore if it is null
     *
     * @param fileStatusId the id of the claimsReportFileProcessStatusDTO to save.
     * @param claimsReportFileProcessStatusDTO the claimsReportFileProcessStatusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claimsReportFileProcessStatusDTO,
     * or with status {@code 400 (Bad Request)} if the claimsReportFileProcessStatusDTO is not valid,
     * or with status {@code 404 (Not Found)} if the claimsReportFileProcessStatusDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the claimsReportFileProcessStatusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/claims-report-file-process-statuses/{fileStatusId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public ResponseEntity<ClaimsReportFileProcessStatusDTO> partialUpdateClaimsReportFileProcessStatus(
        @PathVariable(value = "fileStatusId", required = false) final Long fileStatusId,
        @NotNull @RequestBody ClaimsReportFileProcessStatusDTO claimsReportFileProcessStatusDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update ClaimsReportFileProcessStatus partially : {}, {}",
            fileStatusId,
            claimsReportFileProcessStatusDTO
        );
        if (claimsReportFileProcessStatusDTO.getFileStatusId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(fileStatusId, claimsReportFileProcessStatusDTO.getFileStatusId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!claimsReportFileProcessStatusRepository.existsById(fileStatusId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ClaimsReportFileProcessStatusDTO> result = claimsReportFileProcessStatusService.partialUpdate(
            claimsReportFileProcessStatusDTO
        );

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                false,
                ENTITY_NAME,
                claimsReportFileProcessStatusDTO.getFileStatusId().toString()
            )
        );
    }

    /**
     * {@code GET  /claims-report-file-process-statuses} : get all the claimsReportFileProcessStatuses.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of claimsReportFileProcessStatuses in body.
     */
    @GetMapping("/claims-report-file-process-statuses")
    public ResponseEntity<List<ClaimsReportFileProcessStatusDTO>> getAllClaimsReportFileProcessStatuses(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ClaimsReportFileProcessStatuses");
        Page<ClaimsReportFileProcessStatusDTO> page = claimsReportFileProcessStatusService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /claims-report-file-process-statuses/:id} : get the "id" claimsReportFileProcessStatus.
     *
     * @param id the id of the claimsReportFileProcessStatusDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the claimsReportFileProcessStatusDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/claims-report-file-process-statuses/{id}")
    public ResponseEntity<ClaimsReportFileProcessStatusDTO> getClaimsReportFileProcessStatus(@PathVariable Long id) {
        log.debug("REST request to get ClaimsReportFileProcessStatus : {}", id);
        Optional<ClaimsReportFileProcessStatusDTO> claimsReportFileProcessStatusDTO = claimsReportFileProcessStatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(claimsReportFileProcessStatusDTO);
    }

    /**
     * {@code DELETE  /claims-report-file-process-statuses/:id} : delete the "id" claimsReportFileProcessStatus.
     *
     * @param id the id of the claimsReportFileProcessStatusDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/claims-report-file-process-statuses/{id}")
    public ResponseEntity<Void> deleteClaimsReportFileProcessStatus(@PathVariable Long id) {
        log.debug("REST request to delete ClaimsReportFileProcessStatus : {}", id);
        claimsReportFileProcessStatusService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
