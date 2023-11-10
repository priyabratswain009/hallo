package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.PatientInsVerifStatRepository;
import com.sunknowledge.dme.rcm.service.PatientInsVerifStatService;
import com.sunknowledge.dme.rcm.service.dto.PatientInsVerifStatDTO;
import com.sunknowledge.dme.rcm.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.reactive.ResponseUtil;

/**
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.PatientInsVerifStat}.
 */
@RestController
@RequestMapping("/api")
public class PatientInsVerifStatResource {

    private final Logger log = LoggerFactory.getLogger(PatientInsVerifStatResource.class);

    private static final String ENTITY_NAME = "patientPatientInsVerifStat";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PatientInsVerifStatService patientInsVerifStatService;

    private final PatientInsVerifStatRepository patientInsVerifStatRepository;

    public PatientInsVerifStatResource(
        PatientInsVerifStatService patientInsVerifStatService,
        PatientInsVerifStatRepository patientInsVerifStatRepository
    ) {
        this.patientInsVerifStatService = patientInsVerifStatService;
        this.patientInsVerifStatRepository = patientInsVerifStatRepository;
    }

    /**
     * {@code POST  /patient-ins-verif-stats} : Create a new patientInsVerifStat.
     *
     * @param patientInsVerifStatDTO the patientInsVerifStatDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new patientInsVerifStatDTO, or with status {@code 400 (Bad Request)} if the patientInsVerifStat has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/patient-ins-verif-stats")
    public Mono<ResponseEntity<PatientInsVerifStatDTO>> createPatientInsVerifStat(
        @RequestBody PatientInsVerifStatDTO patientInsVerifStatDTO
    ) throws URISyntaxException {
        log.debug("REST request to save PatientInsVerifStat : {}", patientInsVerifStatDTO);
        if (patientInsVerifStatDTO.getInsuranceVerifId() != null) {
            throw new BadRequestAlertException("A new patientInsVerifStat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return patientInsVerifStatService
            .save(patientInsVerifStatDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/patient-ins-verif-stats/" + result.getInsuranceVerifId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(
                                applicationName,
                                false,
                                ENTITY_NAME,
                                result.getInsuranceVerifId().toString()
                            )
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /patient-ins-verif-stats/:insuranceVerifId} : Updates an existing patientInsVerifStat.
     *
     * @param insuranceVerifId the id of the patientInsVerifStatDTO to save.
     * @param patientInsVerifStatDTO the patientInsVerifStatDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated patientInsVerifStatDTO,
     * or with status {@code 400 (Bad Request)} if the patientInsVerifStatDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the patientInsVerifStatDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/patient-ins-verif-stats/{insuranceVerifId}")
    public Mono<ResponseEntity<PatientInsVerifStatDTO>> updatePatientInsVerifStat(
        @PathVariable(value = "insuranceVerifId", required = false) final Long insuranceVerifId,
        @RequestBody PatientInsVerifStatDTO patientInsVerifStatDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PatientInsVerifStat : {}, {}", insuranceVerifId, patientInsVerifStatDTO);
        if (patientInsVerifStatDTO.getInsuranceVerifId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(insuranceVerifId, patientInsVerifStatDTO.getInsuranceVerifId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return patientInsVerifStatRepository
            .existsById(insuranceVerifId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return patientInsVerifStatService
                    .update(patientInsVerifStatDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getInsuranceVerifId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /patient-ins-verif-stats/:insuranceVerifId} : Partial updates given fields of an existing patientInsVerifStat, field will ignore if it is null
     *
     * @param insuranceVerifId the id of the patientInsVerifStatDTO to save.
     * @param patientInsVerifStatDTO the patientInsVerifStatDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated patientInsVerifStatDTO,
     * or with status {@code 400 (Bad Request)} if the patientInsVerifStatDTO is not valid,
     * or with status {@code 404 (Not Found)} if the patientInsVerifStatDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the patientInsVerifStatDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/patient-ins-verif-stats/{insuranceVerifId}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<PatientInsVerifStatDTO>> partialUpdatePatientInsVerifStat(
        @PathVariable(value = "insuranceVerifId", required = false) final Long insuranceVerifId,
        @RequestBody PatientInsVerifStatDTO patientInsVerifStatDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PatientInsVerifStat partially : {}, {}", insuranceVerifId, patientInsVerifStatDTO);
        if (patientInsVerifStatDTO.getInsuranceVerifId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(insuranceVerifId, patientInsVerifStatDTO.getInsuranceVerifId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return patientInsVerifStatRepository
            .existsById(insuranceVerifId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<PatientInsVerifStatDTO> result = patientInsVerifStatService.partialUpdate(patientInsVerifStatDTO);

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(res ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    res.getInsuranceVerifId().toString()
                                )
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /patient-ins-verif-stats} : get all the patientInsVerifStats.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of patientInsVerifStats in body.
     */
    @GetMapping("/patient-ins-verif-stats")
    public Mono<ResponseEntity<List<PatientInsVerifStatDTO>>> getAllPatientInsVerifStats(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of PatientInsVerifStats");
        return patientInsVerifStatService
            .countAll()
            .zipWith(patientInsVerifStatService.findAll(pageable).collectList())
            .map(countWithEntities ->
                ResponseEntity
                    .ok()
                    .headers(
                        PaginationUtil.generatePaginationHttpHeaders(
                            UriComponentsBuilder.fromHttpRequest(request),
                            new PageImpl<>(countWithEntities.getT2(), pageable, countWithEntities.getT1())
                        )
                    )
                    .body(countWithEntities.getT2())
            );
    }

    /**
     * {@code GET  /patient-ins-verif-stats/:id} : get the "id" patientInsVerifStat.
     *
     * @param id the id of the patientInsVerifStatDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the patientInsVerifStatDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/patient-ins-verif-stats/{id}")
    public Mono<ResponseEntity<PatientInsVerifStatDTO>> getPatientInsVerifStat(@PathVariable Long id) {
        log.debug("REST request to get PatientInsVerifStat : {}", id);
        Mono<PatientInsVerifStatDTO> patientInsVerifStatDTO = patientInsVerifStatService.findOne(id);
        return ResponseUtil.wrapOrNotFound(patientInsVerifStatDTO);
    }

    /**
     * {@code DELETE  /patient-ins-verif-stats/:id} : delete the "id" patientInsVerifStat.
     *
     * @param id the id of the patientInsVerifStatDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/patient-ins-verif-stats/{id}")
    public Mono<ResponseEntity<Void>> deletePatientInsVerifStat(@PathVariable Long id) {
        log.debug("REST request to delete PatientInsVerifStat : {}", id);
        return patientInsVerifStatService
            .delete(id)
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
