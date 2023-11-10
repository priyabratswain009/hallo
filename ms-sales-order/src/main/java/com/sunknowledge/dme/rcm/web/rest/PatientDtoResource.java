package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.PatientDtoRepository;
import com.sunknowledge.dme.rcm.service.PatientDtoService;
import com.sunknowledge.dme.rcm.service.dto.PatientDtoDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.PatientDto}.
 */
@RestController
@RequestMapping("/api")
public class PatientDtoResource {

    private final Logger log = LoggerFactory.getLogger(PatientDtoResource.class);

    private static final String ENTITY_NAME = "salesorderPatientDto";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PatientDtoService patientDtoService;

    private final PatientDtoRepository patientDtoRepository;

    public PatientDtoResource(PatientDtoService patientDtoService, PatientDtoRepository patientDtoRepository) {
        this.patientDtoService = patientDtoService;
        this.patientDtoRepository = patientDtoRepository;
    }

    /**
     * {@code POST  /patient-dtos} : Create a new patientDto.
     *
     * @param patientDtoDTO the patientDtoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new patientDtoDTO, or with status {@code 400 (Bad Request)} if the patientDto has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/patient-dtos")
    public Mono<ResponseEntity<PatientDtoDTO>> createPatientDto(@RequestBody PatientDtoDTO patientDtoDTO) throws URISyntaxException {
        log.debug("REST request to save PatientDto : {}", patientDtoDTO);
        if (patientDtoDTO.getPatientDtoId() != null) {
            throw new BadRequestAlertException("A new patientDto cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return patientDtoService
            .save(patientDtoDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/patient-dtos/" + result.getPatientDtoId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getPatientDtoId().toString())
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /patient-dtos/:patientDtoId} : Updates an existing patientDto.
     *
     * @param patientDtoId the id of the patientDtoDTO to save.
     * @param patientDtoDTO the patientDtoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated patientDtoDTO,
     * or with status {@code 400 (Bad Request)} if the patientDtoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the patientDtoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/patient-dtos/{patientDtoId}")
    public Mono<ResponseEntity<PatientDtoDTO>> updatePatientDto(
        @PathVariable(value = "patientDtoId", required = false) final Long patientDtoId,
        @RequestBody PatientDtoDTO patientDtoDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PatientDto : {}, {}", patientDtoId, patientDtoDTO);
        if (patientDtoDTO.getPatientDtoId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(patientDtoId, patientDtoDTO.getPatientDtoId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return patientDtoRepository
            .existsById(patientDtoId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return patientDtoService
                    .update(patientDtoDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, result.getPatientDtoId().toString())
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /patient-dtos/:patientDtoId} : Partial updates given fields of an existing patientDto, field will ignore if it is null
     *
     * @param patientDtoId the id of the patientDtoDTO to save.
     * @param patientDtoDTO the patientDtoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated patientDtoDTO,
     * or with status {@code 400 (Bad Request)} if the patientDtoDTO is not valid,
     * or with status {@code 404 (Not Found)} if the patientDtoDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the patientDtoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/patient-dtos/{patientDtoId}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<PatientDtoDTO>> partialUpdatePatientDto(
        @PathVariable(value = "patientDtoId", required = false) final Long patientDtoId,
        @RequestBody PatientDtoDTO patientDtoDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PatientDto partially : {}, {}", patientDtoId, patientDtoDTO);
        if (patientDtoDTO.getPatientDtoId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(patientDtoId, patientDtoDTO.getPatientDtoId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return patientDtoRepository
            .existsById(patientDtoId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<PatientDtoDTO> result = patientDtoService.partialUpdate(patientDtoDTO);

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(res ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, res.getPatientDtoId().toString())
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /patient-dtos} : get all the patientDtos.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of patientDtos in body.
     */
    @GetMapping("/patient-dtos")
    public Mono<ResponseEntity<List<PatientDtoDTO>>> getAllPatientDtos(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of PatientDtos");
        return patientDtoService
            .countAll()
            .zipWith(patientDtoService.findAll(pageable).collectList())
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
     * {@code GET  /patient-dtos/:id} : get the "id" patientDto.
     *
     * @param id the id of the patientDtoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the patientDtoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/patient-dtos/{id}")
    public Mono<ResponseEntity<PatientDtoDTO>> getPatientDto(@PathVariable Long id) {
        log.debug("REST request to get PatientDto : {}", id);
        Mono<PatientDtoDTO> patientDtoDTO = patientDtoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(patientDtoDTO);
    }

    /**
     * {@code DELETE  /patient-dtos/:id} : delete the "id" patientDto.
     *
     * @param id the id of the patientDtoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/patient-dtos/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deletePatientDto(@PathVariable Long id) {
        log.debug("REST request to delete PatientDto : {}", id);
        return patientDtoService
            .delete(id)
            .map(result ->
                ResponseEntity
                    .noContent()
                    .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                    .build()
            );
    }
}
