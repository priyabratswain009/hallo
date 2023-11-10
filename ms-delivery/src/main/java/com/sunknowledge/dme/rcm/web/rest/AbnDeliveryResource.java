package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.AbnDeliveryRepository;
import com.sunknowledge.dme.rcm.service.AbnDeliveryService;
import com.sunknowledge.dme.rcm.service.dto.AbnDeliveryDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.AbnDelivery}.
 */
@RestController
@RequestMapping("/api")
public class AbnDeliveryResource {

    private final Logger log = LoggerFactory.getLogger(AbnDeliveryResource.class);

    private static final String ENTITY_NAME = "deliveryAbnDelivery";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AbnDeliveryService abnDeliveryService;

    private final AbnDeliveryRepository abnDeliveryRepository;

    public AbnDeliveryResource(AbnDeliveryService abnDeliveryService, AbnDeliveryRepository abnDeliveryRepository) {
        this.abnDeliveryService = abnDeliveryService;
        this.abnDeliveryRepository = abnDeliveryRepository;
    }

    /**
     * {@code POST  /abn-deliveries} : Create a new abnDelivery.
     *
     * @param abnDeliveryDTO the abnDeliveryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new abnDeliveryDTO, or with status {@code 400 (Bad Request)} if the abnDelivery has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/abn-deliveries")
    public ResponseEntity<AbnDeliveryDTO> createAbnDelivery(@Valid @RequestBody AbnDeliveryDTO abnDeliveryDTO) throws URISyntaxException {
        log.debug("REST request to save AbnDelivery : {}", abnDeliveryDTO);
        if (abnDeliveryDTO.getAbnDeliveryId() != null) {
            throw new BadRequestAlertException("A new abnDelivery cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AbnDeliveryDTO result = abnDeliveryService.save(abnDeliveryDTO);
        return ResponseEntity
            .created(new URI("/api/abn-deliveries/" + result.getAbnDeliveryId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getAbnDeliveryId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /abn-deliveries/:abnDeliveryId} : Updates an existing abnDelivery.
     *
     * @param abnDeliveryId the id of the abnDeliveryDTO to save.
     * @param abnDeliveryDTO the abnDeliveryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated abnDeliveryDTO,
     * or with status {@code 400 (Bad Request)} if the abnDeliveryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the abnDeliveryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/abn-deliveries/{abnDeliveryId}")
    public ResponseEntity<AbnDeliveryDTO> updateAbnDelivery(
        @PathVariable(value = "abnDeliveryId", required = false) final Long abnDeliveryId,
        @Valid @RequestBody AbnDeliveryDTO abnDeliveryDTO
    ) throws URISyntaxException {
        log.debug("REST request to update AbnDelivery : {}, {}", abnDeliveryId, abnDeliveryDTO);
        if (abnDeliveryDTO.getAbnDeliveryId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(abnDeliveryId, abnDeliveryDTO.getAbnDeliveryId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!abnDeliveryRepository.existsById(abnDeliveryId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AbnDeliveryDTO result = abnDeliveryService.update(abnDeliveryDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, abnDeliveryDTO.getAbnDeliveryId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /abn-deliveries/:abnDeliveryId} : Partial updates given fields of an existing abnDelivery, field will ignore if it is null
     *
     * @param abnDeliveryId the id of the abnDeliveryDTO to save.
     * @param abnDeliveryDTO the abnDeliveryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated abnDeliveryDTO,
     * or with status {@code 400 (Bad Request)} if the abnDeliveryDTO is not valid,
     * or with status {@code 404 (Not Found)} if the abnDeliveryDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the abnDeliveryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/abn-deliveries/{abnDeliveryId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AbnDeliveryDTO> partialUpdateAbnDelivery(
        @PathVariable(value = "abnDeliveryId", required = false) final Long abnDeliveryId,
        @NotNull @RequestBody AbnDeliveryDTO abnDeliveryDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update AbnDelivery partially : {}, {}", abnDeliveryId, abnDeliveryDTO);
        if (abnDeliveryDTO.getAbnDeliveryId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(abnDeliveryId, abnDeliveryDTO.getAbnDeliveryId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!abnDeliveryRepository.existsById(abnDeliveryId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AbnDeliveryDTO> result = abnDeliveryService.partialUpdate(abnDeliveryDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, abnDeliveryDTO.getAbnDeliveryId().toString())
        );
    }

    /**
     * {@code GET  /abn-deliveries} : get all the abnDeliveries.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of abnDeliveries in body.
     */
    @GetMapping("/abn-deliveries")
    public ResponseEntity<List<AbnDeliveryDTO>> getAllAbnDeliveries(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of AbnDeliveries");
        Page<AbnDeliveryDTO> page = abnDeliveryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /abn-deliveries/:id} : get the "id" abnDelivery.
     *
     * @param id the id of the abnDeliveryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the abnDeliveryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/abn-deliveries/{id}")
    public ResponseEntity<AbnDeliveryDTO> getAbnDelivery(@PathVariable Long id) {
        log.debug("REST request to get AbnDelivery : {}", id);
        Optional<AbnDeliveryDTO> abnDeliveryDTO = abnDeliveryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(abnDeliveryDTO);
    }

    /**
     * {@code DELETE  /abn-deliveries/:id} : delete the "id" abnDelivery.
     *
     * @param id the id of the abnDeliveryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/abn-deliveries/{id}")
    public ResponseEntity<Void> deleteAbnDelivery(@PathVariable Long id) {
        log.debug("REST request to delete AbnDelivery : {}", id);
        abnDeliveryService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
