package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ParSoMapRepository;
import com.sunknowledge.dme.rcm.service.ParSoMapService;
import com.sunknowledge.dme.rcm.service.dto.ParSoMapDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ParSoMap}.
 */
@RestController
@RequestMapping("/api")
public class ParSoMapResource {

    private final Logger log = LoggerFactory.getLogger(ParSoMapResource.class);

    private static final String ENTITY_NAME = "salesorderParSoMap";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ParSoMapService parSoMapService;

    private final ParSoMapRepository parSoMapRepository;

    public ParSoMapResource(ParSoMapService parSoMapService, ParSoMapRepository parSoMapRepository) {
        this.parSoMapService = parSoMapService;
        this.parSoMapRepository = parSoMapRepository;
    }

    /**
     * {@code POST  /par-so-maps} : Create a new parSoMap.
     *
     * @param parSoMapDTO the parSoMapDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new parSoMapDTO, or with status {@code 400 (Bad Request)} if the parSoMap has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/par-so-maps")
    public Mono<ResponseEntity<ParSoMapDTO>> createParSoMap(@Valid @RequestBody ParSoMapDTO parSoMapDTO) throws URISyntaxException {
        log.debug("REST request to save ParSoMap : {}", parSoMapDTO);
        if (parSoMapDTO.getParSoId() != null) {
            throw new BadRequestAlertException("A new parSoMap cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return parSoMapService
            .save(parSoMapDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/par-so-maps/" + result.getParSoId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getParSoId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /par-so-maps/:parSoId} : Updates an existing parSoMap.
     *
     * @param parSoId the id of the parSoMapDTO to save.
     * @param parSoMapDTO the parSoMapDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated parSoMapDTO,
     * or with status {@code 400 (Bad Request)} if the parSoMapDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the parSoMapDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/par-so-maps/{parSoId}")
    public Mono<ResponseEntity<ParSoMapDTO>> updateParSoMap(
        @PathVariable(value = "parSoId", required = false) final Long parSoId,
        @Valid @RequestBody ParSoMapDTO parSoMapDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ParSoMap : {}, {}", parSoId, parSoMapDTO);
        if (parSoMapDTO.getParSoId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(parSoId, parSoMapDTO.getParSoId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return parSoMapRepository
            .existsById(parSoId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return parSoMapService
                    .update(parSoMapDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, result.getParSoId().toString())
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /par-so-maps/:parSoId} : Partial updates given fields of an existing parSoMap, field will ignore if it is null
     *
     * @param parSoId the id of the parSoMapDTO to save.
     * @param parSoMapDTO the parSoMapDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated parSoMapDTO,
     * or with status {@code 400 (Bad Request)} if the parSoMapDTO is not valid,
     * or with status {@code 404 (Not Found)} if the parSoMapDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the parSoMapDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/par-so-maps/{parSoId}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<ParSoMapDTO>> partialUpdateParSoMap(
        @PathVariable(value = "parSoId", required = false) final Long parSoId,
        @NotNull @RequestBody ParSoMapDTO parSoMapDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ParSoMap partially : {}, {}", parSoId, parSoMapDTO);
        if (parSoMapDTO.getParSoId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(parSoId, parSoMapDTO.getParSoId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return parSoMapRepository
            .existsById(parSoId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<ParSoMapDTO> result = parSoMapService.partialUpdate(parSoMapDTO);

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(res ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, res.getParSoId().toString()))
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /par-so-maps} : get all the parSoMaps.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of parSoMaps in body.
     */
    @GetMapping("/par-so-maps")
    public Mono<ResponseEntity<List<ParSoMapDTO>>> getAllParSoMaps(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of ParSoMaps");
        return parSoMapService
            .countAll()
            .zipWith(parSoMapService.findAll(pageable).collectList())
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
     * {@code GET  /par-so-maps/:id} : get the "id" parSoMap.
     *
     * @param id the id of the parSoMapDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the parSoMapDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/par-so-maps/{id}")
    public Mono<ResponseEntity<ParSoMapDTO>> getParSoMap(@PathVariable Long id) {
        log.debug("REST request to get ParSoMap : {}", id);
        Mono<ParSoMapDTO> parSoMapDTO = parSoMapService.findOne(id);
        return ResponseUtil.wrapOrNotFound(parSoMapDTO);
    }

    /**
     * {@code DELETE  /par-so-maps/:id} : delete the "id" parSoMap.
     *
     * @param id the id of the parSoMapDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/par-so-maps/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteParSoMap(@PathVariable Long id) {
        log.debug("REST request to delete ParSoMap : {}", id);
        return parSoMapService
            .delete(id)
            .map(result ->
                ResponseEntity
                    .noContent()
                    .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                    .build()
            );
    }
}
