package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.AddressVerificationResponseRepository;
import com.sunknowledge.dme.rcm.service.AddressVerificationResponseService;
import com.sunknowledge.dme.rcm.service.dto.AddressVerificationResponseDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.AddressVerificationResponse}.
 */
@RestController
@RequestMapping("/api")
public class AddressVerificationResponseResource {

    private final Logger log = LoggerFactory.getLogger(AddressVerificationResponseResource.class);

    private static final String ENTITY_NAME = "utilityapisAddressVerificationResponse";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AddressVerificationResponseService addressVerificationResponseService;

    private final AddressVerificationResponseRepository addressVerificationResponseRepository;

    public AddressVerificationResponseResource(
        AddressVerificationResponseService addressVerificationResponseService,
        AddressVerificationResponseRepository addressVerificationResponseRepository
    ) {
        this.addressVerificationResponseService = addressVerificationResponseService;
        this.addressVerificationResponseRepository = addressVerificationResponseRepository;
    }

    /**
     * {@code POST  /address-verification-responses} : Create a new addressVerificationResponse.
     *
     * @param addressVerificationResponseDTO the addressVerificationResponseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new addressVerificationResponseDTO, or with status {@code 400 (Bad Request)} if the addressVerificationResponse has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/address-verification-responses")
    public ResponseEntity<AddressVerificationResponseDTO> createAddressVerificationResponse(
        @Valid @RequestBody AddressVerificationResponseDTO addressVerificationResponseDTO
    ) throws URISyntaxException {
        log.debug("REST request to save AddressVerificationResponse : {}", addressVerificationResponseDTO);
        if (addressVerificationResponseDTO.getUspsAddressVerificationId() != null) {
            throw new BadRequestAlertException("A new addressVerificationResponse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AddressVerificationResponseDTO result = addressVerificationResponseService.save(addressVerificationResponseDTO);
        return ResponseEntity
            .created(new URI("/api/address-verification-responses/" + result.getUspsAddressVerificationId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getUspsAddressVerificationId().toString())
            )
            .body(result);
    }

    /**
     * {@code PUT  /address-verification-responses/:uspsAddressVerificationId} : Updates an existing addressVerificationResponse.
     *
     * @param uspsAddressVerificationId the id of the addressVerificationResponseDTO to save.
     * @param addressVerificationResponseDTO the addressVerificationResponseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated addressVerificationResponseDTO,
     * or with status {@code 400 (Bad Request)} if the addressVerificationResponseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the addressVerificationResponseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/address-verification-responses/{uspsAddressVerificationId}")
    public ResponseEntity<AddressVerificationResponseDTO> updateAddressVerificationResponse(
        @PathVariable(value = "uspsAddressVerificationId", required = false) final Long uspsAddressVerificationId,
        @Valid @RequestBody AddressVerificationResponseDTO addressVerificationResponseDTO
    ) throws URISyntaxException {
        log.debug("REST request to update AddressVerificationResponse : {}, {}", uspsAddressVerificationId, addressVerificationResponseDTO);
        if (addressVerificationResponseDTO.getUspsAddressVerificationId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(uspsAddressVerificationId, addressVerificationResponseDTO.getUspsAddressVerificationId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!addressVerificationResponseRepository.existsById(uspsAddressVerificationId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AddressVerificationResponseDTO result = addressVerificationResponseService.update(addressVerificationResponseDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    addressVerificationResponseDTO.getUspsAddressVerificationId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /address-verification-responses/:uspsAddressVerificationId} : Partial updates given fields of an existing addressVerificationResponse, field will ignore if it is null
     *
     * @param uspsAddressVerificationId the id of the addressVerificationResponseDTO to save.
     * @param addressVerificationResponseDTO the addressVerificationResponseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated addressVerificationResponseDTO,
     * or with status {@code 400 (Bad Request)} if the addressVerificationResponseDTO is not valid,
     * or with status {@code 404 (Not Found)} if the addressVerificationResponseDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the addressVerificationResponseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/address-verification-responses/{uspsAddressVerificationId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public ResponseEntity<AddressVerificationResponseDTO> partialUpdateAddressVerificationResponse(
        @PathVariable(value = "uspsAddressVerificationId", required = false) final Long uspsAddressVerificationId,
        @NotNull @RequestBody AddressVerificationResponseDTO addressVerificationResponseDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update AddressVerificationResponse partially : {}, {}",
            uspsAddressVerificationId,
            addressVerificationResponseDTO
        );
        if (addressVerificationResponseDTO.getUspsAddressVerificationId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(uspsAddressVerificationId, addressVerificationResponseDTO.getUspsAddressVerificationId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!addressVerificationResponseRepository.existsById(uspsAddressVerificationId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AddressVerificationResponseDTO> result = addressVerificationResponseService.partialUpdate(addressVerificationResponseDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                false,
                ENTITY_NAME,
                addressVerificationResponseDTO.getUspsAddressVerificationId().toString()
            )
        );
    }

    /**
     * {@code GET  /address-verification-responses} : get all the addressVerificationResponses.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of addressVerificationResponses in body.
     */
    @GetMapping("/address-verification-responses")
    public ResponseEntity<List<AddressVerificationResponseDTO>> getAllAddressVerificationResponses(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of AddressVerificationResponses");
        Page<AddressVerificationResponseDTO> page = addressVerificationResponseService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /address-verification-responses/:id} : get the "id" addressVerificationResponse.
     *
     * @param id the id of the addressVerificationResponseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the addressVerificationResponseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/address-verification-responses/{id}")
    public ResponseEntity<AddressVerificationResponseDTO> getAddressVerificationResponse(@PathVariable Long id) {
        log.debug("REST request to get AddressVerificationResponse : {}", id);
        Optional<AddressVerificationResponseDTO> addressVerificationResponseDTO = addressVerificationResponseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(addressVerificationResponseDTO);
    }

    /**
     * {@code DELETE  /address-verification-responses/:id} : delete the "id" addressVerificationResponse.
     *
     * @param id the id of the addressVerificationResponseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/address-verification-responses/{id}")
    public ResponseEntity<Void> deleteAddressVerificationResponse(@PathVariable Long id) {
        log.debug("REST request to delete AddressVerificationResponse : {}", id);
        addressVerificationResponseService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
