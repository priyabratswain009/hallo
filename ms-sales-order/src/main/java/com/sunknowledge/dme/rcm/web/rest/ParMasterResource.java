package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ParMasterRepository;
import com.sunknowledge.dme.rcm.service.ParMasterService;
import com.sunknowledge.dme.rcm.service.dto.ParMasterDTO;
import com.sunknowledge.dme.rcm.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ParMaster}.
 */
@RestController
@RequestMapping("/api")
public class ParMasterResource {

    private final Logger log = LoggerFactory.getLogger(ParMasterResource.class);

    private static final String ENTITY_NAME = "salesorderParMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ParMasterService parMasterService;

    private final ParMasterRepository parMasterRepository;

    public ParMasterResource(ParMasterService parMasterService, ParMasterRepository parMasterRepository) {
        this.parMasterService = parMasterService;
        this.parMasterRepository = parMasterRepository;
    }

    /**
     * {@code POST  /par-masters} : Create a new parMaster.
     *
     * @param parMasterDTO the parMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new parMasterDTO, or with status {@code 400 (Bad Request)} if the parMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/par-masters")
    public Mono<ResponseEntity<ParMasterDTO>> createParMaster(@Valid @RequestBody ParMasterDTO parMasterDTO) throws URISyntaxException {
        log.debug("REST request to save ParMaster : {}", parMasterDTO);
        if (parMasterDTO.getParId() != null) {
            throw new BadRequestAlertException("A new parMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return parMasterService
            .save(parMasterDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/par-masters/" + result.getParId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getParId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /par-masters/:parId} : Updates an existing parMaster.
     *
     * @param parId the id of the parMasterDTO to save.
     * @param parMasterDTO the parMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated parMasterDTO,
     * or with status {@code 400 (Bad Request)} if the parMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the parMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/par-masters/{parId}")
    public Mono<ResponseEntity<ParMasterDTO>> updateParMaster(
        @PathVariable(value = "parId", required = false) final Long parId,
        @Valid @RequestBody ParMasterDTO parMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ParMaster : {}, {}", parId, parMasterDTO);
        if (parMasterDTO.getParId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(parId, parMasterDTO.getParId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return parMasterRepository
            .existsById(parId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return parMasterService
                    .update(parMasterDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, result.getParId().toString()))
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /par-masters/:parId} : Partial updates given fields of an existing parMaster, field will ignore if it is null
     *
     * @param parId the id of the parMasterDTO to save.
     * @param parMasterDTO the parMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated parMasterDTO,
     * or with status {@code 400 (Bad Request)} if the parMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the parMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the parMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/par-masters/{parId}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<ParMasterDTO>> partialUpdateParMaster(
        @PathVariable(value = "parId", required = false) final Long parId,
        @NotNull @RequestBody ParMasterDTO parMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ParMaster partially : {}, {}", parId, parMasterDTO);
        if (parMasterDTO.getParId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(parId, parMasterDTO.getParId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return parMasterRepository
            .existsById(parId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<ParMasterDTO> result = parMasterService.partialUpdate(parMasterDTO);

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(res ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, res.getParId().toString()))
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /par-masters} : get all the parMasters.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of parMasters in body.
     */
    @GetMapping("/par-masters")
    public Mono<ResponseEntity<List<ParMasterDTO>>> getAllParMasters(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of ParMasters");
        return parMasterService
            .countAll()
            .zipWith(parMasterService.findAll(pageable).collectList())
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
     * {@code GET  /par-masters/:id} : get the "id" parMaster.
     *
     * @param id the id of the parMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the parMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/par-masters/{id}")
    public Mono<ResponseEntity<ParMasterDTO>> getParMaster(@PathVariable Long id) {
        log.debug("REST request to get ParMaster : {}", id);
        Mono<ParMasterDTO> parMasterDTO = parMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(parMasterDTO);
    }

    /**
     * {@code DELETE  /par-masters/:id} : delete the "id" parMaster.
     *
     * @param id the id of the parMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/par-masters/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteParMaster(@PathVariable Long id) {
        log.debug("REST request to delete ParMaster : {}", id);
        return parMasterService
            .delete(id)
            .map(result ->
                ResponseEntity
                    .noContent()
                    .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                    .build()
            );
    }
}
