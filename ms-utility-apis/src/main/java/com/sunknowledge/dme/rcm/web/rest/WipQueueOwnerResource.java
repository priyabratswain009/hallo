package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.WipQueueOwnerRepository;
import com.sunknowledge.dme.rcm.service.WipQueueOwnerService;
import com.sunknowledge.dme.rcm.service.dto.WipQueueOwnerDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.WipQueueOwner}.
 */
@RestController
@RequestMapping("/api")
public class WipQueueOwnerResource {

    private final Logger log = LoggerFactory.getLogger(WipQueueOwnerResource.class);

    private static final String ENTITY_NAME = "utilityapisWipQueueOwner";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WipQueueOwnerService wipQueueOwnerService;

    private final WipQueueOwnerRepository wipQueueOwnerRepository;

    public WipQueueOwnerResource(WipQueueOwnerService wipQueueOwnerService, WipQueueOwnerRepository wipQueueOwnerRepository) {
        this.wipQueueOwnerService = wipQueueOwnerService;
        this.wipQueueOwnerRepository = wipQueueOwnerRepository;
    }

    /**
     * {@code POST  /wip-queue-owners} : Create a new wipQueueOwner.
     *
     * @param wipQueueOwnerDTO the wipQueueOwnerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new wipQueueOwnerDTO, or with status {@code 400 (Bad Request)} if the wipQueueOwner has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/wip-queue-owners")
    public ResponseEntity<WipQueueOwnerDTO> createWipQueueOwner(@Valid @RequestBody WipQueueOwnerDTO wipQueueOwnerDTO)
        throws URISyntaxException {
        log.debug("REST request to save WipQueueOwner : {}", wipQueueOwnerDTO);
        if (wipQueueOwnerDTO.getWipQueueOwnerId() != null) {
            throw new BadRequestAlertException("A new wipQueueOwner cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WipQueueOwnerDTO result = wipQueueOwnerService.save(wipQueueOwnerDTO);
        return ResponseEntity
            .created(new URI("/api/wip-queue-owners/" + result.getWipQueueOwnerId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getWipQueueOwnerId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /wip-queue-owners/:wipQueueOwnerId} : Updates an existing wipQueueOwner.
     *
     * @param wipQueueOwnerId the id of the wipQueueOwnerDTO to save.
     * @param wipQueueOwnerDTO the wipQueueOwnerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated wipQueueOwnerDTO,
     * or with status {@code 400 (Bad Request)} if the wipQueueOwnerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the wipQueueOwnerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/wip-queue-owners/{wipQueueOwnerId}")
    public ResponseEntity<WipQueueOwnerDTO> updateWipQueueOwner(
        @PathVariable(value = "wipQueueOwnerId", required = false) final Long wipQueueOwnerId,
        @Valid @RequestBody WipQueueOwnerDTO wipQueueOwnerDTO
    ) throws URISyntaxException {
        log.debug("REST request to update WipQueueOwner : {}, {}", wipQueueOwnerId, wipQueueOwnerDTO);
        if (wipQueueOwnerDTO.getWipQueueOwnerId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(wipQueueOwnerId, wipQueueOwnerDTO.getWipQueueOwnerId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!wipQueueOwnerRepository.existsById(wipQueueOwnerId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        WipQueueOwnerDTO result = wipQueueOwnerService.update(wipQueueOwnerDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, wipQueueOwnerDTO.getWipQueueOwnerId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /wip-queue-owners/:wipQueueOwnerId} : Partial updates given fields of an existing wipQueueOwner, field will ignore if it is null
     *
     * @param wipQueueOwnerId the id of the wipQueueOwnerDTO to save.
     * @param wipQueueOwnerDTO the wipQueueOwnerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated wipQueueOwnerDTO,
     * or with status {@code 400 (Bad Request)} if the wipQueueOwnerDTO is not valid,
     * or with status {@code 404 (Not Found)} if the wipQueueOwnerDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the wipQueueOwnerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/wip-queue-owners/{wipQueueOwnerId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<WipQueueOwnerDTO> partialUpdateWipQueueOwner(
        @PathVariable(value = "wipQueueOwnerId", required = false) final Long wipQueueOwnerId,
        @NotNull @RequestBody WipQueueOwnerDTO wipQueueOwnerDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update WipQueueOwner partially : {}, {}", wipQueueOwnerId, wipQueueOwnerDTO);
        if (wipQueueOwnerDTO.getWipQueueOwnerId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(wipQueueOwnerId, wipQueueOwnerDTO.getWipQueueOwnerId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!wipQueueOwnerRepository.existsById(wipQueueOwnerId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<WipQueueOwnerDTO> result = wipQueueOwnerService.partialUpdate(wipQueueOwnerDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, wipQueueOwnerDTO.getWipQueueOwnerId().toString())
        );
    }

    /**
     * {@code GET  /wip-queue-owners} : get all the wipQueueOwners.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of wipQueueOwners in body.
     */
    @GetMapping("/wip-queue-owners")
    public ResponseEntity<List<WipQueueOwnerDTO>> getAllWipQueueOwners(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of WipQueueOwners");
        Page<WipQueueOwnerDTO> page = wipQueueOwnerService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /wip-queue-owners/:id} : get the "id" wipQueueOwner.
     *
     * @param id the id of the wipQueueOwnerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the wipQueueOwnerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/wip-queue-owners/{id}")
    public ResponseEntity<WipQueueOwnerDTO> getWipQueueOwner(@PathVariable Long id) {
        log.debug("REST request to get WipQueueOwner : {}", id);
        Optional<WipQueueOwnerDTO> wipQueueOwnerDTO = wipQueueOwnerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(wipQueueOwnerDTO);
    }

    /**
     * {@code DELETE  /wip-queue-owners/:id} : delete the "id" wipQueueOwner.
     *
     * @param id the id of the wipQueueOwnerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/wip-queue-owners/{id}")
    public ResponseEntity<Void> deleteWipQueueOwner(@PathVariable Long id) {
        log.debug("REST request to delete WipQueueOwner : {}", id);
        wipQueueOwnerService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
