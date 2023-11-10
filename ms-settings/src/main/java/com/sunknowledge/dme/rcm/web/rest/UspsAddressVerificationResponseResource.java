package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.UspsAddressVerificationResponseRepository;
import com.sunknowledge.dme.rcm.service.UspsAddressVerificationResponseService;
import com.sunknowledge.dme.rcm.service.dto.UspsAddressVerificationResponseDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.UspsAddressVerificationResponse}.
 */
@RestController
@RequestMapping("/api")
public class UspsAddressVerificationResponseResource {

    private final Logger log = LoggerFactory.getLogger(UspsAddressVerificationResponseResource.class);

    private static final String ENTITY_NAME = "settingsUspsAddressVerificationResponse";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UspsAddressVerificationResponseService uspsAddressVerificationResponseService;

    private final UspsAddressVerificationResponseRepository uspsAddressVerificationResponseRepository;

    public UspsAddressVerificationResponseResource(
        UspsAddressVerificationResponseService uspsAddressVerificationResponseService,
        UspsAddressVerificationResponseRepository uspsAddressVerificationResponseRepository
    ) {
        this.uspsAddressVerificationResponseService = uspsAddressVerificationResponseService;
        this.uspsAddressVerificationResponseRepository = uspsAddressVerificationResponseRepository;
    }

    /**
     * {@code POST  /usps-address-verification-responses} : Create a new uspsAddressVerificationResponse.
     *
     * @param uspsAddressVerificationResponseDTO the uspsAddressVerificationResponseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new uspsAddressVerificationResponseDTO, or with status {@code 400 (Bad Request)} if the uspsAddressVerificationResponse has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/usps-address-verification-responses")
    public ResponseEntity<UspsAddressVerificationResponseDTO> createUspsAddressVerificationResponse(
        @RequestBody UspsAddressVerificationResponseDTO uspsAddressVerificationResponseDTO
    ) throws URISyntaxException {
        log.debug("REST request to save UspsAddressVerificationResponse : {}", uspsAddressVerificationResponseDTO);
        if (uspsAddressVerificationResponseDTO.getUspsAddressVerificationId() != null) {
            throw new BadRequestAlertException("A new uspsAddressVerificationResponse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UspsAddressVerificationResponseDTO result = uspsAddressVerificationResponseService.save(uspsAddressVerificationResponseDTO);
        return ResponseEntity
            .created(new URI("/api/usps-address-verification-responses/" + result.getUspsAddressVerificationId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getUspsAddressVerificationId().toString())
            )
            .body(result);
    }

    /**
     * {@code PUT  /usps-address-verification-responses/:uspsAddressVerificationId} : Updates an existing uspsAddressVerificationResponse.
     *
     * @param uspsAddressVerificationId the id of the uspsAddressVerificationResponseDTO to save.
     * @param uspsAddressVerificationResponseDTO the uspsAddressVerificationResponseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated uspsAddressVerificationResponseDTO,
     * or with status {@code 400 (Bad Request)} if the uspsAddressVerificationResponseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the uspsAddressVerificationResponseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/usps-address-verification-responses/{uspsAddressVerificationId}")
    public ResponseEntity<UspsAddressVerificationResponseDTO> updateUspsAddressVerificationResponse(
        @PathVariable(value = "uspsAddressVerificationId", required = false) final Long uspsAddressVerificationId,
        @RequestBody UspsAddressVerificationResponseDTO uspsAddressVerificationResponseDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to update UspsAddressVerificationResponse : {}, {}",
            uspsAddressVerificationId,
            uspsAddressVerificationResponseDTO
        );
        if (uspsAddressVerificationResponseDTO.getUspsAddressVerificationId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(uspsAddressVerificationId, uspsAddressVerificationResponseDTO.getUspsAddressVerificationId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!uspsAddressVerificationResponseRepository.existsById(uspsAddressVerificationId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        UspsAddressVerificationResponseDTO result = uspsAddressVerificationResponseService.update(uspsAddressVerificationResponseDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    uspsAddressVerificationResponseDTO.getUspsAddressVerificationId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /usps-address-verification-responses/:uspsAddressVerificationId} : Partial updates given fields of an existing uspsAddressVerificationResponse, field will ignore if it is null
     *
     * @param uspsAddressVerificationId the id of the uspsAddressVerificationResponseDTO to save.
     * @param uspsAddressVerificationResponseDTO the uspsAddressVerificationResponseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated uspsAddressVerificationResponseDTO,
     * or with status {@code 400 (Bad Request)} if the uspsAddressVerificationResponseDTO is not valid,
     * or with status {@code 404 (Not Found)} if the uspsAddressVerificationResponseDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the uspsAddressVerificationResponseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/usps-address-verification-responses/{uspsAddressVerificationId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public ResponseEntity<UspsAddressVerificationResponseDTO> partialUpdateUspsAddressVerificationResponse(
        @PathVariable(value = "uspsAddressVerificationId", required = false) final Long uspsAddressVerificationId,
        @RequestBody UspsAddressVerificationResponseDTO uspsAddressVerificationResponseDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update UspsAddressVerificationResponse partially : {}, {}",
            uspsAddressVerificationId,
            uspsAddressVerificationResponseDTO
        );
        if (uspsAddressVerificationResponseDTO.getUspsAddressVerificationId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(uspsAddressVerificationId, uspsAddressVerificationResponseDTO.getUspsAddressVerificationId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!uspsAddressVerificationResponseRepository.existsById(uspsAddressVerificationId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<UspsAddressVerificationResponseDTO> result = uspsAddressVerificationResponseService.partialUpdate(
            uspsAddressVerificationResponseDTO
        );

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                false,
                ENTITY_NAME,
                uspsAddressVerificationResponseDTO.getUspsAddressVerificationId().toString()
            )
        );
    }

    /**
     * {@code GET  /usps-address-verification-responses} : get all the uspsAddressVerificationResponses.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of uspsAddressVerificationResponses in body.
     */
    @GetMapping("/usps-address-verification-responses")
    public ResponseEntity<List<UspsAddressVerificationResponseDTO>> getAllUspsAddressVerificationResponses(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of UspsAddressVerificationResponses");
        Page<UspsAddressVerificationResponseDTO> page = uspsAddressVerificationResponseService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /usps-address-verification-responses/:id} : get the "id" uspsAddressVerificationResponse.
     *
     * @param id the id of the uspsAddressVerificationResponseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the uspsAddressVerificationResponseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/usps-address-verification-responses/{id}")
    public ResponseEntity<UspsAddressVerificationResponseDTO> getUspsAddressVerificationResponse(@PathVariable Long id) {
        log.debug("REST request to get UspsAddressVerificationResponse : {}", id);
        Optional<UspsAddressVerificationResponseDTO> uspsAddressVerificationResponseDTO = uspsAddressVerificationResponseService.findOne(
            id
        );
        return ResponseUtil.wrapOrNotFound(uspsAddressVerificationResponseDTO);
    }

    /**
     * {@code DELETE  /usps-address-verification-responses/:id} : delete the "id" uspsAddressVerificationResponse.
     *
     * @param id the id of the uspsAddressVerificationResponseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/usps-address-verification-responses/{id}")
    public ResponseEntity<Void> deleteUspsAddressVerificationResponse(@PathVariable Long id) {
        log.debug("REST request to delete UspsAddressVerificationResponse : {}", id);
        uspsAddressVerificationResponseService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
