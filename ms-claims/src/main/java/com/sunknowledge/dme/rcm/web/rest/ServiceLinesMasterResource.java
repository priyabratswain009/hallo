package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ServiceLinesMasterRepository;
import com.sunknowledge.dme.rcm.service.ServiceLinesMasterService;
import com.sunknowledge.dme.rcm.service.dto.ServiceLinesMasterDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ServiceLinesMaster}.
 */
@RestController
@RequestMapping("/api")
public class ServiceLinesMasterResource {

    private final Logger log = LoggerFactory.getLogger(ServiceLinesMasterResource.class);

    private static final String ENTITY_NAME = "claimsServiceLinesMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ServiceLinesMasterService serviceLinesMasterService;

    private final ServiceLinesMasterRepository serviceLinesMasterRepository;

    public ServiceLinesMasterResource(
        ServiceLinesMasterService serviceLinesMasterService,
        ServiceLinesMasterRepository serviceLinesMasterRepository
    ) {
        this.serviceLinesMasterService = serviceLinesMasterService;
        this.serviceLinesMasterRepository = serviceLinesMasterRepository;
    }

    /**
     * {@code POST  /service-lines-masters} : Create a new serviceLinesMaster.
     *
     * @param serviceLinesMasterDTO the serviceLinesMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new serviceLinesMasterDTO, or with status {@code 400 (Bad Request)} if the serviceLinesMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/service-lines-masters")
    public ResponseEntity<ServiceLinesMasterDTO> createServiceLinesMaster(@Valid @RequestBody ServiceLinesMasterDTO serviceLinesMasterDTO)
        throws URISyntaxException {
        log.debug("REST request to save ServiceLinesMaster : {}", serviceLinesMasterDTO);
        if (serviceLinesMasterDTO.getChangeHealthPrimarySubmisionServicelinesId() != null) {
            throw new BadRequestAlertException("A new serviceLinesMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ServiceLinesMasterDTO result = serviceLinesMasterService.save(serviceLinesMasterDTO);
        return ResponseEntity
            .created(new URI("/api/service-lines-masters/" + result.getChangeHealthPrimarySubmisionServicelinesId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    result.getChangeHealthPrimarySubmisionServicelinesId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PUT  /service-lines-masters/:changeHealthPrimarySubmisionServicelinesId} : Updates an existing serviceLinesMaster.
     *
     * @param changeHealthPrimarySubmisionServicelinesId the id of the serviceLinesMasterDTO to save.
     * @param serviceLinesMasterDTO the serviceLinesMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated serviceLinesMasterDTO,
     * or with status {@code 400 (Bad Request)} if the serviceLinesMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the serviceLinesMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/service-lines-masters/{changeHealthPrimarySubmisionServicelinesId}")
    public ResponseEntity<ServiceLinesMasterDTO> updateServiceLinesMaster(
        @PathVariable(
            value = "changeHealthPrimarySubmisionServicelinesId",
            required = false
        ) final Long changeHealthPrimarySubmisionServicelinesId,
        @Valid @RequestBody ServiceLinesMasterDTO serviceLinesMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ServiceLinesMaster : {}, {}", changeHealthPrimarySubmisionServicelinesId, serviceLinesMasterDTO);
        if (serviceLinesMasterDTO.getChangeHealthPrimarySubmisionServicelinesId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (
            !Objects.equals(
                changeHealthPrimarySubmisionServicelinesId,
                serviceLinesMasterDTO.getChangeHealthPrimarySubmisionServicelinesId()
            )
        ) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!serviceLinesMasterRepository.existsById(changeHealthPrimarySubmisionServicelinesId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ServiceLinesMasterDTO result = serviceLinesMasterService.update(serviceLinesMasterDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    serviceLinesMasterDTO.getChangeHealthPrimarySubmisionServicelinesId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /service-lines-masters/:changeHealthPrimarySubmisionServicelinesId} : Partial updates given fields of an existing serviceLinesMaster, field will ignore if it is null
     *
     * @param changeHealthPrimarySubmisionServicelinesId the id of the serviceLinesMasterDTO to save.
     * @param serviceLinesMasterDTO the serviceLinesMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated serviceLinesMasterDTO,
     * or with status {@code 400 (Bad Request)} if the serviceLinesMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the serviceLinesMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the serviceLinesMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/service-lines-masters/{changeHealthPrimarySubmisionServicelinesId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public ResponseEntity<ServiceLinesMasterDTO> partialUpdateServiceLinesMaster(
        @PathVariable(
            value = "changeHealthPrimarySubmisionServicelinesId",
            required = false
        ) final Long changeHealthPrimarySubmisionServicelinesId,
        @NotNull @RequestBody ServiceLinesMasterDTO serviceLinesMasterDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update ServiceLinesMaster partially : {}, {}",
            changeHealthPrimarySubmisionServicelinesId,
            serviceLinesMasterDTO
        );
        if (serviceLinesMasterDTO.getChangeHealthPrimarySubmisionServicelinesId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (
            !Objects.equals(
                changeHealthPrimarySubmisionServicelinesId,
                serviceLinesMasterDTO.getChangeHealthPrimarySubmisionServicelinesId()
            )
        ) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!serviceLinesMasterRepository.existsById(changeHealthPrimarySubmisionServicelinesId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ServiceLinesMasterDTO> result = serviceLinesMasterService.partialUpdate(serviceLinesMasterDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                false,
                ENTITY_NAME,
                serviceLinesMasterDTO.getChangeHealthPrimarySubmisionServicelinesId().toString()
            )
        );
    }

    /**
     * {@code GET  /service-lines-masters} : get all the serviceLinesMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of serviceLinesMasters in body.
     */
    @GetMapping("/service-lines-masters")
    public ResponseEntity<List<ServiceLinesMasterDTO>> getAllServiceLinesMasters(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ServiceLinesMasters");
        Page<ServiceLinesMasterDTO> page = serviceLinesMasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /service-lines-masters/:id} : get the "id" serviceLinesMaster.
     *
     * @param id the id of the serviceLinesMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the serviceLinesMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/service-lines-masters/{id}")
    public ResponseEntity<ServiceLinesMasterDTO> getServiceLinesMaster(@PathVariable Long id) {
        log.debug("REST request to get ServiceLinesMaster : {}", id);
        Optional<ServiceLinesMasterDTO> serviceLinesMasterDTO = serviceLinesMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(serviceLinesMasterDTO);
    }

    /**
     * {@code DELETE  /service-lines-masters/:id} : delete the "id" serviceLinesMaster.
     *
     * @param id the id of the serviceLinesMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/service-lines-masters/{id}")
    public ResponseEntity<Void> deleteServiceLinesMaster(@PathVariable Long id) {
        log.debug("REST request to delete ServiceLinesMaster : {}", id);
        serviceLinesMasterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
