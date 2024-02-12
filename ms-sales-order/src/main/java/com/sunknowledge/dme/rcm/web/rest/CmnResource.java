package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.CmnRepository;
import com.sunknowledge.dme.rcm.service.CmnService;
import com.sunknowledge.dme.rcm.service.dto.CmnDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.Cmn}.
 */
@RestController
@RequestMapping("/api")
public class CmnResource {

    private final Logger log = LoggerFactory.getLogger(CmnResource.class);

    private static final String ENTITY_NAME = "salesorderCmn";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CmnService cmnService;

    private final CmnRepository cmnRepository;

    public CmnResource(CmnService cmnService, CmnRepository cmnRepository) {
        this.cmnService = cmnService;
        this.cmnRepository = cmnRepository;
    }

    /**
     * {@code POST  /cmns} : Create a new cmn.
     *
     * @param cmnDTO the cmnDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cmnDTO, or with status {@code 400 (Bad Request)} if the cmn has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cmns")
    public Mono<ResponseEntity<CmnDTO>> createCmn(@Valid @RequestBody CmnDTO cmnDTO) throws URISyntaxException {
        log.debug("REST request to save Cmn : {}", cmnDTO);
        if (cmnDTO.getCmnId() != null) {
            throw new BadRequestAlertException("A new cmn cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return cmnService
            .save(cmnDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/cmns/" + result.getCmnId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getCmnId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /cmns/:cmnId} : Updates an existing cmn.
     *
     * @param cmnId the id of the cmnDTO to save.
     * @param cmnDTO the cmnDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cmnDTO,
     * or with status {@code 400 (Bad Request)} if the cmnDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cmnDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cmns/{cmnId}")
    public Mono<ResponseEntity<CmnDTO>> updateCmn(
        @PathVariable(value = "cmnId", required = false) final Long cmnId,
        @Valid @RequestBody CmnDTO cmnDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Cmn : {}, {}", cmnId, cmnDTO);
        if (cmnDTO.getCmnId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(cmnId, cmnDTO.getCmnId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return cmnRepository
            .existsById(cmnId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return cmnService
                    .update(cmnDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, result.getCmnId().toString()))
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /cmns/:cmnId} : Partial updates given fields of an existing cmn, field will ignore if it is null
     *
     * @param cmnId the id of the cmnDTO to save.
     * @param cmnDTO the cmnDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cmnDTO,
     * or with status {@code 400 (Bad Request)} if the cmnDTO is not valid,
     * or with status {@code 404 (Not Found)} if the cmnDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the cmnDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/cmns/{cmnId}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<CmnDTO>> partialUpdateCmn(
        @PathVariable(value = "cmnId", required = false) final Long cmnId,
        @NotNull @RequestBody CmnDTO cmnDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Cmn partially : {}, {}", cmnId, cmnDTO);
        if (cmnDTO.getCmnId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(cmnId, cmnDTO.getCmnId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return cmnRepository
            .existsById(cmnId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<CmnDTO> result = cmnService.partialUpdate(cmnDTO);

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(res ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, res.getCmnId().toString()))
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /cmns} : get all the cmns.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cmns in body.
     */
    @GetMapping("/cmns")
    public Mono<ResponseEntity<List<CmnDTO>>> getAllCmns(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of Cmns");
        return cmnService
            .countAll()
            .zipWith(cmnService.findAll(pageable).collectList())
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
     * {@code GET  /cmns/:id} : get the "id" cmn.
     *
     * @param id the id of the cmnDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cmnDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cmns/{id}")
    public Mono<ResponseEntity<CmnDTO>> getCmn(@PathVariable Long id) {
        log.debug("REST request to get Cmn : {}", id);
        Mono<CmnDTO> cmnDTO = cmnService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cmnDTO);
    }

    /**
     * {@code DELETE  /cmns/:id} : delete the "id" cmn.
     *
     * @param id the id of the cmnDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cmns/{id}")
    public Mono<ResponseEntity<Void>> deleteCmn(@PathVariable Long id) {
        log.debug("REST request to delete Cmn : {}", id);
        return cmnService
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
