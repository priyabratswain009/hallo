package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.NoteTypeMasterRepository;
import com.sunknowledge.dme.rcm.service.NoteTypeMasterService;
import com.sunknowledge.dme.rcm.service.dto.NoteTypeMasterDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.NoteTypeMaster}.
 */
@RestController
@RequestMapping("/api")
public class NoteTypeMasterResource {

    private final Logger log = LoggerFactory.getLogger(NoteTypeMasterResource.class);

    private static final String ENTITY_NAME = "settingsNoteTypeMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NoteTypeMasterService noteTypeMasterService;

    private final NoteTypeMasterRepository noteTypeMasterRepository;

    public NoteTypeMasterResource(NoteTypeMasterService noteTypeMasterService, NoteTypeMasterRepository noteTypeMasterRepository) {
        this.noteTypeMasterService = noteTypeMasterService;
        this.noteTypeMasterRepository = noteTypeMasterRepository;
    }

    /**
     * {@code POST  /note-type-masters} : Create a new noteTypeMaster.
     *
     * @param noteTypeMasterDTO the noteTypeMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new noteTypeMasterDTO, or with status {@code 400 (Bad Request)} if the noteTypeMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/note-type-masters")
    public ResponseEntity<NoteTypeMasterDTO> createNoteTypeMaster(@RequestBody NoteTypeMasterDTO noteTypeMasterDTO)
        throws URISyntaxException {
        log.debug("REST request to save NoteTypeMaster : {}", noteTypeMasterDTO);
        if (noteTypeMasterDTO.getNoteTypeId() != null) {
            throw new BadRequestAlertException("A new noteTypeMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NoteTypeMasterDTO result = noteTypeMasterService.save(noteTypeMasterDTO);
        return ResponseEntity
            .created(new URI("/api/note-type-masters/" + result.getNoteTypeId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getNoteTypeId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /note-type-masters/:noteTypeId} : Updates an existing noteTypeMaster.
     *
     * @param noteTypeId the id of the noteTypeMasterDTO to save.
     * @param noteTypeMasterDTO the noteTypeMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated noteTypeMasterDTO,
     * or with status {@code 400 (Bad Request)} if the noteTypeMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the noteTypeMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/note-type-masters/{noteTypeId}")
    public ResponseEntity<NoteTypeMasterDTO> updateNoteTypeMaster(
        @PathVariable(value = "noteTypeId", required = false) final Long noteTypeId,
        @RequestBody NoteTypeMasterDTO noteTypeMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update NoteTypeMaster : {}, {}", noteTypeId, noteTypeMasterDTO);
        if (noteTypeMasterDTO.getNoteTypeId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(noteTypeId, noteTypeMasterDTO.getNoteTypeId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!noteTypeMasterRepository.existsById(noteTypeId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        NoteTypeMasterDTO result = noteTypeMasterService.update(noteTypeMasterDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, noteTypeMasterDTO.getNoteTypeId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /note-type-masters/:noteTypeId} : Partial updates given fields of an existing noteTypeMaster, field will ignore if it is null
     *
     * @param noteTypeId the id of the noteTypeMasterDTO to save.
     * @param noteTypeMasterDTO the noteTypeMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated noteTypeMasterDTO,
     * or with status {@code 400 (Bad Request)} if the noteTypeMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the noteTypeMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the noteTypeMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/note-type-masters/{noteTypeId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<NoteTypeMasterDTO> partialUpdateNoteTypeMaster(
        @PathVariable(value = "noteTypeId", required = false) final Long noteTypeId,
        @RequestBody NoteTypeMasterDTO noteTypeMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update NoteTypeMaster partially : {}, {}", noteTypeId, noteTypeMasterDTO);
        if (noteTypeMasterDTO.getNoteTypeId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(noteTypeId, noteTypeMasterDTO.getNoteTypeId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!noteTypeMasterRepository.existsById(noteTypeId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<NoteTypeMasterDTO> result = noteTypeMasterService.partialUpdate(noteTypeMasterDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, noteTypeMasterDTO.getNoteTypeId().toString())
        );
    }

    /**
     * {@code GET  /note-type-masters} : get all the noteTypeMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of noteTypeMasters in body.
     */
    @GetMapping("/note-type-masters")
    public ResponseEntity<List<NoteTypeMasterDTO>> getAllNoteTypeMasters(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of NoteTypeMasters");
        Page<NoteTypeMasterDTO> page = noteTypeMasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /note-type-masters/:id} : get the "id" noteTypeMaster.
     *
     * @param id the id of the noteTypeMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the noteTypeMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/note-type-masters/{id}")
    public ResponseEntity<NoteTypeMasterDTO> getNoteTypeMaster(@PathVariable Long id) {
        log.debug("REST request to get NoteTypeMaster : {}", id);
        Optional<NoteTypeMasterDTO> noteTypeMasterDTO = noteTypeMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(noteTypeMasterDTO);
    }

    /**
     * {@code DELETE  /note-type-masters/:id} : delete the "id" noteTypeMaster.
     *
     * @param id the id of the noteTypeMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/note-type-masters/{id}")
    public ResponseEntity<Void> deleteNoteTypeMaster(@PathVariable Long id) {
        log.debug("REST request to delete NoteTypeMaster : {}", id);
        noteTypeMasterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
