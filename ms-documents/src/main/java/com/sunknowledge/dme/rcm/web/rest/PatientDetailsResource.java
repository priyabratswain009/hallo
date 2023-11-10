package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.domain.PatientDetails;
import com.sunknowledge.dme.rcm.repository.PatientDetailsRepository;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.reactive.ResponseUtil;

/**
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.PatientDetails}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PatientDetailsResource {

    private final Logger log = LoggerFactory.getLogger(PatientDetailsResource.class);

    private static final String ENTITY_NAME = "documentsPatientDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PatientDetailsRepository patientDetailsRepository;

    public PatientDetailsResource(PatientDetailsRepository patientDetailsRepository) {
        this.patientDetailsRepository = patientDetailsRepository;
    }

    /**
     * {@code POST  /patient-details} : Create a new patientDetails.
     *
     * @param patientDetails the patientDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new patientDetails, or with status {@code 400 (Bad Request)} if the patientDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/patient-details")
    public Mono<ResponseEntity<PatientDetails>> createPatientDetails(@Valid @RequestBody PatientDetails patientDetails)
        throws URISyntaxException {
        log.debug("REST request to save PatientDetails : {}", patientDetails);
        if (patientDetails.getPatientId() != null) {
            throw new BadRequestAlertException("A new patientDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return patientDetailsRepository
            .save(patientDetails)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/patient-details/" + result.getPatientId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getPatientId().toString())
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /patient-details/:patientId} : Updates an existing patientDetails.
     *
     * @param patientId the id of the patientDetails to save.
     * @param patientDetails the patientDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated patientDetails,
     * or with status {@code 400 (Bad Request)} if the patientDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the patientDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/patient-details/{patientId}")
    public Mono<ResponseEntity<PatientDetails>> updatePatientDetails(
        @PathVariable(value = "patientId", required = false) final Long patientId,
        @Valid @RequestBody PatientDetails patientDetails
    ) throws URISyntaxException {
        log.debug("REST request to update PatientDetails : {}, {}", patientId, patientDetails);
        if (patientDetails.getPatientId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(patientId, patientDetails.getPatientId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return patientDetailsRepository
            .existsById(patientId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return patientDetailsRepository
                    .save(patientDetails)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, result.getPatientId().toString())
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /patient-details/:patientId} : Partial updates given fields of an existing patientDetails, field will ignore if it is null
     *
     * @param patientId the id of the patientDetails to save.
     * @param patientDetails the patientDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated patientDetails,
     * or with status {@code 400 (Bad Request)} if the patientDetails is not valid,
     * or with status {@code 404 (Not Found)} if the patientDetails is not found,
     * or with status {@code 500 (Internal Server Error)} if the patientDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/patient-details/{patientId}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<PatientDetails>> partialUpdatePatientDetails(
        @PathVariable(value = "patientId", required = false) final Long patientId,
        @NotNull @RequestBody PatientDetails patientDetails
    ) throws URISyntaxException {
        log.debug("REST request to partial update PatientDetails partially : {}, {}", patientId, patientDetails);
        if (patientDetails.getPatientId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(patientId, patientDetails.getPatientId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return patientDetailsRepository
            .existsById(patientId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<PatientDetails> result = patientDetailsRepository
                    .findById(patientDetails.getPatientId())
                    .map(existingPatientDetails -> {
                        if (patientDetails.getPatientFname() != null) {
                            existingPatientDetails.setPatientFname(patientDetails.getPatientFname());
                        }
                        if (patientDetails.getPatientLname() != null) {
                            existingPatientDetails.setPatientLname(patientDetails.getPatientLname());
                        }
                        if (patientDetails.getDob() != null) {
                            existingPatientDetails.setDob(patientDetails.getDob());
                        }
                        if (patientDetails.getAddress() != null) {
                            existingPatientDetails.setAddress(patientDetails.getAddress());
                        }
                        if (patientDetails.getCity() != null) {
                            existingPatientDetails.setCity(patientDetails.getCity());
                        }
                        if (patientDetails.getZip() != null) {
                            existingPatientDetails.setZip(patientDetails.getZip());
                        }
                        if (patientDetails.getEmail() != null) {
                            existingPatientDetails.setEmail(patientDetails.getEmail());
                        }
                        if (patientDetails.getPhoneNo() != null) {
                            existingPatientDetails.setPhoneNo(patientDetails.getPhoneNo());
                        }
                        if (patientDetails.getDocumentName() != null) {
                            existingPatientDetails.setDocumentName(patientDetails.getDocumentName());
                        }
                        if (patientDetails.getDescription() != null) {
                            existingPatientDetails.setDescription(patientDetails.getDescription());
                        }
                        if (patientDetails.getMrno() != null) {
                            existingPatientDetails.setMrno(patientDetails.getMrno());
                        }
                        if (patientDetails.getDateTime() != null) {
                            existingPatientDetails.setDateTime(patientDetails.getDateTime());
                        }
                        if (patientDetails.getStatus() != null) {
                            existingPatientDetails.setStatus(patientDetails.getStatus());
                        }
                        if (patientDetails.getIsTagged() != null) {
                            existingPatientDetails.setIsTagged(patientDetails.getIsTagged());
                        }
                        if (patientDetails.getDocumentPath() != null) {
                            existingPatientDetails.setDocumentPath(patientDetails.getDocumentPath());
                        }
                        if (patientDetails.getQrCodeStatus() != null) {
                            existingPatientDetails.setQrCodeStatus(patientDetails.getQrCodeStatus());
                        }

                        return existingPatientDetails;
                    })
                    .flatMap(patientDetailsRepository::save);

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(res ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, res.getPatientId().toString()))
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /patient-details} : get all the patientDetails.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of patientDetails in body.
     */
    @GetMapping("/patient-details")
    public Mono<List<PatientDetails>> getAllPatientDetails() {
        log.debug("REST request to get all PatientDetails");
        return patientDetailsRepository.findAll().collectList();
    }

    /**
     * {@code GET  /patient-details} : get all the patientDetails as a stream.
     * @return the {@link Flux} of patientDetails.
     */
    @GetMapping(value = "/patient-details", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<PatientDetails> getAllPatientDetailsAsStream() {
        log.debug("REST request to get all PatientDetails as a stream");
        return patientDetailsRepository.findAll();
    }

    /**
     * {@code GET  /patient-details/:id} : get the "id" patientDetails.
     *
     * @param id the id of the patientDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the patientDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/patient-details/{id}")
    public Mono<ResponseEntity<PatientDetails>> getPatientDetails(@PathVariable Long id) {
        log.debug("REST request to get PatientDetails : {}", id);
        Mono<PatientDetails> patientDetails = patientDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(patientDetails);
    }

    /**
     * {@code DELETE  /patient-details/:id} : delete the "id" patientDetails.
     *
     * @param id the id of the patientDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/patient-details/{id}")
    public Mono<ResponseEntity<Void>> deletePatientDetails(@PathVariable Long id) {
        log.debug("REST request to delete PatientDetails : {}", id);
        return patientDetailsRepository
            .deleteById(id)
            .then(
                Mono.just(
                    ResponseEntity
                        .noContent()
                        .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                        .build()
                )
            );
    }
}
