package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.NoteReasonMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.NoteReasonMasterAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.NoteReasonMasterAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.NoteReasonMasterAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class NoteReasonMasterAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(NoteReasonMasterAuditLogResource.class);

    private static final String ENTITY_NAME = "settingsNoteReasonMasterAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NoteReasonMasterAuditLogService noteReasonMasterAuditLogService;

    private final NoteReasonMasterAuditLogRepository noteReasonMasterAuditLogRepository;

    public NoteReasonMasterAuditLogResource(
        NoteReasonMasterAuditLogService noteReasonMasterAuditLogService,
        NoteReasonMasterAuditLogRepository noteReasonMasterAuditLogRepository
    ) {
        this.noteReasonMasterAuditLogService = noteReasonMasterAuditLogService;
        this.noteReasonMasterAuditLogRepository = noteReasonMasterAuditLogRepository;
    }

    /**
     * {@code POST  /note-reason-master-audit-logs} : Create a new noteReasonMasterAuditLog.
     *
     * @param noteReasonMasterAuditLogDTO the noteReasonMasterAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new noteReasonMasterAuditLogDTO, or with status {@code 400 (Bad Request)} if the noteReasonMasterAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/note-reason-master-audit-logs")
    public ResponseEntity<NoteReasonMasterAuditLogDTO> createNoteReasonMasterAuditLog(
        @RequestBody NoteReasonMasterAuditLogDTO noteReasonMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to save NoteReasonMasterAuditLog : {}", noteReasonMasterAuditLogDTO);
        if (noteReasonMasterAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new noteReasonMasterAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NoteReasonMasterAuditLogDTO result = noteReasonMasterAuditLogService.save(noteReasonMasterAuditLogDTO);
        return ResponseEntity
            .created(new URI("/api/note-reason-master-audit-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /note-reason-master-audit-logs/:id} : Updates an existing noteReasonMasterAuditLog.
     *
     * @param id the id of the noteReasonMasterAuditLogDTO to save.
     * @param noteReasonMasterAuditLogDTO the noteReasonMasterAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated noteReasonMasterAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the noteReasonMasterAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the noteReasonMasterAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/note-reason-master-audit-logs/{id}")
    public ResponseEntity<NoteReasonMasterAuditLogDTO> updateNoteReasonMasterAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody NoteReasonMasterAuditLogDTO noteReasonMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update NoteReasonMasterAuditLog : {}, {}", id, noteReasonMasterAuditLogDTO);
        if (noteReasonMasterAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, noteReasonMasterAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!noteReasonMasterAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        NoteReasonMasterAuditLogDTO result = noteReasonMasterAuditLogService.update(noteReasonMasterAuditLogDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, noteReasonMasterAuditLogDTO.getId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /note-reason-master-audit-logs/:id} : Partial updates given fields of an existing noteReasonMasterAuditLog, field will ignore if it is null
     *
     * @param id the id of the noteReasonMasterAuditLogDTO to save.
     * @param noteReasonMasterAuditLogDTO the noteReasonMasterAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated noteReasonMasterAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the noteReasonMasterAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the noteReasonMasterAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the noteReasonMasterAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/note-reason-master-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<NoteReasonMasterAuditLogDTO> partialUpdateNoteReasonMasterAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody NoteReasonMasterAuditLogDTO noteReasonMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update NoteReasonMasterAuditLog partially : {}, {}", id, noteReasonMasterAuditLogDTO);
        if (noteReasonMasterAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, noteReasonMasterAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!noteReasonMasterAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<NoteReasonMasterAuditLogDTO> result = noteReasonMasterAuditLogService.partialUpdate(noteReasonMasterAuditLogDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, noteReasonMasterAuditLogDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /note-reason-master-audit-logs} : get all the noteReasonMasterAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of noteReasonMasterAuditLogs in body.
     */
    @GetMapping("/note-reason-master-audit-logs")
    public ResponseEntity<List<NoteReasonMasterAuditLogDTO>> getAllNoteReasonMasterAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of NoteReasonMasterAuditLogs");
        Page<NoteReasonMasterAuditLogDTO> page = noteReasonMasterAuditLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /note-reason-master-audit-logs/:id} : get the "id" noteReasonMasterAuditLog.
     *
     * @param id the id of the noteReasonMasterAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the noteReasonMasterAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/note-reason-master-audit-logs/{id}")
    public ResponseEntity<NoteReasonMasterAuditLogDTO> getNoteReasonMasterAuditLog(@PathVariable Long id) {
        log.debug("REST request to get NoteReasonMasterAuditLog : {}", id);
        Optional<NoteReasonMasterAuditLogDTO> noteReasonMasterAuditLogDTO = noteReasonMasterAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(noteReasonMasterAuditLogDTO);
    }

    /**
     * {@code DELETE  /note-reason-master-audit-logs/:id} : delete the "id" noteReasonMasterAuditLog.
     *
     * @param id the id of the noteReasonMasterAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/note-reason-master-audit-logs/{id}")
    public ResponseEntity<Void> deleteNoteReasonMasterAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete NoteReasonMasterAuditLog : {}", id);
        noteReasonMasterAuditLogService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
