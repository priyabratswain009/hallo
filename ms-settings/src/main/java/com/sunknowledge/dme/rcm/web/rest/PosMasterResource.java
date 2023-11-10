package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.PosMasterRepository;
import com.sunknowledge.dme.rcm.service.PosMasterService;
import com.sunknowledge.dme.rcm.service.dto.PosMasterDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.PosMaster}.
 */
@RestController
@RequestMapping("/api")
public class PosMasterResource {

    private final Logger log = LoggerFactory.getLogger(PosMasterResource.class);

    private static final String ENTITY_NAME = "settingsPosMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PosMasterService posMasterService;

    private final PosMasterRepository posMasterRepository;

    public PosMasterResource(PosMasterService posMasterService, PosMasterRepository posMasterRepository) {
        this.posMasterService = posMasterService;
        this.posMasterRepository = posMasterRepository;
    }

    /**
     * {@code POST  /pos-masters} : Create a new posMaster.
     *
     * @param posMasterDTO the posMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new posMasterDTO, or with status {@code 400 (Bad Request)} if the posMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pos-masters")
    public ResponseEntity<PosMasterDTO> createPosMaster(@RequestBody PosMasterDTO posMasterDTO) throws URISyntaxException {
        log.debug("REST request to save PosMaster : {}", posMasterDTO);
        if (posMasterDTO.getPosId() != null) {
            throw new BadRequestAlertException("A new posMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PosMasterDTO result = posMasterService.save(posMasterDTO);
        return ResponseEntity
            .created(new URI("/api/pos-masters/" + result.getPosId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getPosId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pos-masters/:posId} : Updates an existing posMaster.
     *
     * @param posId the id of the posMasterDTO to save.
     * @param posMasterDTO the posMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated posMasterDTO,
     * or with status {@code 400 (Bad Request)} if the posMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the posMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pos-masters/{posId}")
    public ResponseEntity<PosMasterDTO> updatePosMaster(
        @PathVariable(value = "posId", required = false) final Long posId,
        @RequestBody PosMasterDTO posMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PosMaster : {}, {}", posId, posMasterDTO);
        if (posMasterDTO.getPosId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(posId, posMasterDTO.getPosId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!posMasterRepository.existsById(posId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PosMasterDTO result = posMasterService.update(posMasterDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, posMasterDTO.getPosId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /pos-masters/:posId} : Partial updates given fields of an existing posMaster, field will ignore if it is null
     *
     * @param posId the id of the posMasterDTO to save.
     * @param posMasterDTO the posMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated posMasterDTO,
     * or with status {@code 400 (Bad Request)} if the posMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the posMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the posMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/pos-masters/{posId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PosMasterDTO> partialUpdatePosMaster(
        @PathVariable(value = "posId", required = false) final Long posId,
        @RequestBody PosMasterDTO posMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PosMaster partially : {}, {}", posId, posMasterDTO);
        if (posMasterDTO.getPosId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(posId, posMasterDTO.getPosId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!posMasterRepository.existsById(posId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PosMasterDTO> result = posMasterService.partialUpdate(posMasterDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, posMasterDTO.getPosId().toString())
        );
    }

    /**
     * {@code GET  /pos-masters} : get all the posMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of posMasters in body.
     */
    @GetMapping("/pos-masters")
    public ResponseEntity<List<PosMasterDTO>> getAllPosMasters(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of PosMasters");
        Page<PosMasterDTO> page = posMasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /pos-masters/:id} : get the "id" posMaster.
     *
     * @param id the id of the posMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the posMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pos-masters/{id}")
    public ResponseEntity<PosMasterDTO> getPosMaster(@PathVariable Long id) {
        log.debug("REST request to get PosMaster : {}", id);
        Optional<PosMasterDTO> posMasterDTO = posMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(posMasterDTO);
    }

    /**
     * {@code DELETE  /pos-masters/:id} : delete the "id" posMaster.
     *
     * @param id the id of the posMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pos-masters/{id}")
    public ResponseEntity<Void> deletePosMaster(@PathVariable Long id) {
        log.debug("REST request to delete PosMaster : {}", id);
        posMasterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
