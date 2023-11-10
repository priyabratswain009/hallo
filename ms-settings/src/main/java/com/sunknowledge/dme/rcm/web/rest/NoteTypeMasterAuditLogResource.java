package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.NoteTypeMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.NoteTypeMasterAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.NoteTypeMasterAuditLogDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.NoteTypeMasterAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class NoteTypeMasterAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(NoteTypeMasterAuditLogResource.class);

    private static final String ENTITY_NAME = "settingsNoteTypeMasterAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NoteTypeMasterAuditLogService noteTypeMasterAuditLogService;

    private final NoteTypeMasterAuditLogRepository noteTypeMasterAuditLogRepository;

    public NoteTypeMasterAuditLogResource(
        NoteTypeMasterAuditLogService noteTypeMasterAuditLogService,
        NoteTypeMasterAuditLogRepository noteTypeMasterAuditLogRepository
    ) {
        this.noteTypeMasterAuditLogService = noteTypeMasterAuditLogService;
        this.noteTypeMasterAuditLogRepository = noteTypeMasterAuditLogRepository;
    }

    /**
     * {@code POST  /note-type-master-audit-logs} : Create a new noteTypeMasterAuditLog.
     *
     * @param noteTypeMasterAuditLogDTO the noteTypeMasterAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new noteTypeMasterAuditLogDTO, or with status {@code 400 (Bad Request)} if the noteTypeMasterAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/note-type-master-audit-logs")
    public ResponseEntity<NoteTypeMasterAuditLogDTO> createNoteTypeMasterAuditLog(
        @RequestBody NoteTypeMasterAuditLogDTO noteTypeMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to save NoteTypeMasterAuditLog : {}", noteTypeMasterAuditLogDTO);
        if (noteTypeMasterAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new noteTypeMasterAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NoteTypeMasterAuditLogDTO result = noteTypeMasterAuditLogService.save(noteTypeMasterAuditLogDTO);
        return ResponseEntity
            .created(new URI("/api/note-type-master-audit-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /note-type-master-audit-logs/:id} : Updates an existing noteTypeMasterAuditLog.
     *
     * @param id the id of the noteTypeMasterAuditLogDTO to save.
     * @param noteTypeMasterAuditLogDTO the noteTypeMasterAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated noteTypeMasterAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the noteTypeMasterAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the noteTypeMasterAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/note-type-master-audit-logs/{id}")
    public ResponseEntity<NoteTypeMasterAuditLogDTO> updateNoteTypeMasterAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody NoteTypeMasterAuditLogDTO noteTypeMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update NoteTypeMasterAuditLog : {}, {}", id, noteTypeMasterAuditLogDTO);
        if (noteTypeMasterAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, noteTypeMasterAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!noteTypeMasterAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        NoteTypeMasterAuditLogDTO result = noteTypeMasterAuditLogService.update(noteTypeMasterAuditLogDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, noteTypeMasterAuditLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /note-type-master-audit-logs/:id} : Partial updates given fields of an existing noteTypeMasterAuditLog, field will ignore if it is null
     *
     * @param id the id of the noteTypeMasterAuditLogDTO to save.
     * @param noteTypeMasterAuditLogDTO the noteTypeMasterAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated noteTypeMasterAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the noteTypeMasterAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the noteTypeMasterAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the noteTypeMasterAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/note-type-master-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<NoteTypeMasterAuditLogDTO> partialUpdateNoteTypeMasterAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody NoteTypeMasterAuditLogDTO noteTypeMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update NoteTypeMasterAuditLog partially : {}, {}", id, noteTypeMasterAuditLogDTO);
        if (noteTypeMasterAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, noteTypeMasterAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!noteTypeMasterAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<NoteTypeMasterAuditLogDTO> result = noteTypeMasterAuditLogService.partialUpdate(noteTypeMasterAuditLogDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, noteTypeMasterAuditLogDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /note-type-master-audit-logs} : get all the noteTypeMasterAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of noteTypeMasterAuditLogs in body.
     */
    @GetMapping("/note-type-master-audit-logs")
    public ResponseEntity<List<NoteTypeMasterAuditLogDTO>> getAllNoteTypeMasterAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of NoteTypeMasterAuditLogs");
        Page<NoteTypeMasterAuditLogDTO> page = noteTypeMasterAuditLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /note-type-master-audit-logs/:id} : get the "id" noteTypeMasterAuditLog.
     *
     * @param id the id of the noteTypeMasterAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the noteTypeMasterAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/note-type-master-audit-logs/{id}")
    public ResponseEntity<NoteTypeMasterAuditLogDTO> getNoteTypeMasterAuditLog(@PathVariable Long id) {
        log.debug("REST request to get NoteTypeMasterAuditLog : {}", id);
        Optional<NoteTypeMasterAuditLogDTO> noteTypeMasterAuditLogDTO = noteTypeMasterAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(noteTypeMasterAuditLogDTO);
    }

    /**
     * {@code DELETE  /note-type-master-audit-logs/:id} : delete the "id" noteTypeMasterAuditLog.
     *
     * @param id the id of the noteTypeMasterAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/note-type-master-audit-logs/{id}")
    public ResponseEntity<Void> deleteNoteTypeMasterAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete NoteTypeMasterAuditLog : {}", id);
        noteTypeMasterAuditLogService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
