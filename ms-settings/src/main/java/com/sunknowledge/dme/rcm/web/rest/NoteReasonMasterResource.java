package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.NoteReasonMasterRepository;
import com.sunknowledge.dme.rcm.service.NoteReasonMasterService;
import com.sunknowledge.dme.rcm.service.dto.NoteReasonMasterDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.NoteReasonMaster}.
 */
@RestController
@RequestMapping("/api")
public class NoteReasonMasterResource {

    private final Logger log = LoggerFactory.getLogger(NoteReasonMasterResource.class);

    private static final String ENTITY_NAME = "settingsNoteReasonMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NoteReasonMasterService noteReasonMasterService;

    private final NoteReasonMasterRepository noteReasonMasterRepository;

    public NoteReasonMasterResource(
        NoteReasonMasterService noteReasonMasterService,
        NoteReasonMasterRepository noteReasonMasterRepository
    ) {
        this.noteReasonMasterService = noteReasonMasterService;
        this.noteReasonMasterRepository = noteReasonMasterRepository;
    }

    /**
     * {@code POST  /note-reason-masters} : Create a new noteReasonMaster.
     *
     * @param noteReasonMasterDTO the noteReasonMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new noteReasonMasterDTO, or with status {@code 400 (Bad Request)} if the noteReasonMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/note-reason-masters")
    public ResponseEntity<NoteReasonMasterDTO> createNoteReasonMaster(@RequestBody NoteReasonMasterDTO noteReasonMasterDTO)
        throws URISyntaxException {
        log.debug("REST request to save NoteReasonMaster : {}", noteReasonMasterDTO);
        if (noteReasonMasterDTO.getNoteReasonId() != null) {
            throw new BadRequestAlertException("A new noteReasonMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NoteReasonMasterDTO result = noteReasonMasterService.save(noteReasonMasterDTO);
        return ResponseEntity
            .created(new URI("/api/note-reason-masters/" + result.getNoteReasonId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getNoteReasonId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /note-reason-masters/:noteReasonId} : Updates an existing noteReasonMaster.
     *
     * @param noteReasonId the id of the noteReasonMasterDTO to save.
     * @param noteReasonMasterDTO the noteReasonMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated noteReasonMasterDTO,
     * or with status {@code 400 (Bad Request)} if the noteReasonMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the noteReasonMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/note-reason-masters/{noteReasonId}")
    public ResponseEntity<NoteReasonMasterDTO> updateNoteReasonMaster(
        @PathVariable(value = "noteReasonId", required = false) final Long noteReasonId,
        @RequestBody NoteReasonMasterDTO noteReasonMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update NoteReasonMaster : {}, {}", noteReasonId, noteReasonMasterDTO);
        if (noteReasonMasterDTO.getNoteReasonId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(noteReasonId, noteReasonMasterDTO.getNoteReasonId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!noteReasonMasterRepository.existsById(noteReasonId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        NoteReasonMasterDTO result = noteReasonMasterService.update(noteReasonMasterDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, noteReasonMasterDTO.getNoteReasonId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /note-reason-masters/:noteReasonId} : Partial updates given fields of an existing noteReasonMaster, field will ignore if it is null
     *
     * @param noteReasonId the id of the noteReasonMasterDTO to save.
     * @param noteReasonMasterDTO the noteReasonMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated noteReasonMasterDTO,
     * or with status {@code 400 (Bad Request)} if the noteReasonMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the noteReasonMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the noteReasonMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/note-reason-masters/{noteReasonId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<NoteReasonMasterDTO> partialUpdateNoteReasonMaster(
        @PathVariable(value = "noteReasonId", required = false) final Long noteReasonId,
        @RequestBody NoteReasonMasterDTO noteReasonMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update NoteReasonMaster partially : {}, {}", noteReasonId, noteReasonMasterDTO);
        if (noteReasonMasterDTO.getNoteReasonId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(noteReasonId, noteReasonMasterDTO.getNoteReasonId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!noteReasonMasterRepository.existsById(noteReasonId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<NoteReasonMasterDTO> result = noteReasonMasterService.partialUpdate(noteReasonMasterDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, noteReasonMasterDTO.getNoteReasonId().toString())
        );
    }

    /**
     * {@code GET  /note-reason-masters} : get all the noteReasonMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of noteReasonMasters in body.
     */
    @GetMapping("/note-reason-masters")
    public ResponseEntity<List<NoteReasonMasterDTO>> getAllNoteReasonMasters(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of NoteReasonMasters");
        Page<NoteReasonMasterDTO> page = noteReasonMasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /note-reason-masters/:id} : get the "id" noteReasonMaster.
     *
     * @param id the id of the noteReasonMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the noteReasonMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/note-reason-masters/{id}")
    public ResponseEntity<NoteReasonMasterDTO> getNoteReasonMaster(@PathVariable Long id) {
        log.debug("REST request to get NoteReasonMaster : {}", id);
        Optional<NoteReasonMasterDTO> noteReasonMasterDTO = noteReasonMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(noteReasonMasterDTO);
    }

    /**
     * {@code DELETE  /note-reason-masters/:id} : delete the "id" noteReasonMaster.
     *
     * @param id the id of the noteReasonMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/note-reason-masters/{id}")
    public ResponseEntity<Void> deleteNoteReasonMaster(@PathVariable Long id) {
        log.debug("REST request to delete NoteReasonMaster : {}", id);
        noteReasonMasterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
