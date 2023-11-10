package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.InsurancePricetableMapRepository;
import com.sunknowledge.dme.rcm.service.InsurancePricetableMapService;
import com.sunknowledge.dme.rcm.service.dto.InsurancePricetableMapDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.InsurancePricetableMap}.
 */
@RestController
@RequestMapping("/api")
public class InsurancePricetableMapResource {

    private final Logger log = LoggerFactory.getLogger(InsurancePricetableMapResource.class);

    private static final String ENTITY_NAME = "salesorderInsurancePricetableMap";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InsurancePricetableMapService insurancePricetableMapService;

    private final InsurancePricetableMapRepository insurancePricetableMapRepository;

    public InsurancePricetableMapResource(
        InsurancePricetableMapService insurancePricetableMapService,
        InsurancePricetableMapRepository insurancePricetableMapRepository
    ) {
        this.insurancePricetableMapService = insurancePricetableMapService;
        this.insurancePricetableMapRepository = insurancePricetableMapRepository;
    }

    /**
     * {@code POST  /insurance-pricetable-maps} : Create a new insurancePricetableMap.
     *
     * @param insurancePricetableMapDTO the insurancePricetableMapDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new insurancePricetableMapDTO, or with status {@code 400 (Bad Request)} if the insurancePricetableMap has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/insurance-pricetable-maps")
    public Mono<ResponseEntity<InsurancePricetableMapDTO>> createInsurancePricetableMap(
        @RequestBody InsurancePricetableMapDTO insurancePricetableMapDTO
    ) throws URISyntaxException {
        log.debug("REST request to save InsurancePricetableMap : {}", insurancePricetableMapDTO);
        if (insurancePricetableMapDTO.getInsurancePricetableMapId() != null) {
            throw new BadRequestAlertException("A new insurancePricetableMap cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return insurancePricetableMapService
            .save(insurancePricetableMapDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/insurance-pricetable-maps/" + result.getInsurancePricetableMapId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(
                                applicationName,
                                false,
                                ENTITY_NAME,
                                result.getInsurancePricetableMapId().toString()
                            )
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /insurance-pricetable-maps/:insurancePricetableMapId} : Updates an existing insurancePricetableMap.
     *
     * @param insurancePricetableMapId the id of the insurancePricetableMapDTO to save.
     * @param insurancePricetableMapDTO the insurancePricetableMapDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated insurancePricetableMapDTO,
     * or with status {@code 400 (Bad Request)} if the insurancePricetableMapDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the insurancePricetableMapDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/insurance-pricetable-maps/{insurancePricetableMapId}")
    public Mono<ResponseEntity<InsurancePricetableMapDTO>> updateInsurancePricetableMap(
        @PathVariable(value = "insurancePricetableMapId", required = false) final Long insurancePricetableMapId,
        @RequestBody InsurancePricetableMapDTO insurancePricetableMapDTO
    ) throws URISyntaxException {
        log.debug("REST request to update InsurancePricetableMap : {}, {}", insurancePricetableMapId, insurancePricetableMapDTO);
        if (insurancePricetableMapDTO.getInsurancePricetableMapId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(insurancePricetableMapId, insurancePricetableMapDTO.getInsurancePricetableMapId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return insurancePricetableMapRepository
            .existsById(insurancePricetableMapId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return insurancePricetableMapService
                    .update(insurancePricetableMapDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getInsurancePricetableMapId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /insurance-pricetable-maps/:insurancePricetableMapId} : Partial updates given fields of an existing insurancePricetableMap, field will ignore if it is null
     *
     * @param insurancePricetableMapId the id of the insurancePricetableMapDTO to save.
     * @param insurancePricetableMapDTO the insurancePricetableMapDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated insurancePricetableMapDTO,
     * or with status {@code 400 (Bad Request)} if the insurancePricetableMapDTO is not valid,
     * or with status {@code 404 (Not Found)} if the insurancePricetableMapDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the insurancePricetableMapDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/insurance-pricetable-maps/{insurancePricetableMapId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public Mono<ResponseEntity<InsurancePricetableMapDTO>> partialUpdateInsurancePricetableMap(
        @PathVariable(value = "insurancePricetableMapId", required = false) final Long insurancePricetableMapId,
        @RequestBody InsurancePricetableMapDTO insurancePricetableMapDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update InsurancePricetableMap partially : {}, {}",
            insurancePricetableMapId,
            insurancePricetableMapDTO
        );
        if (insurancePricetableMapDTO.getInsurancePricetableMapId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(insurancePricetableMapId, insurancePricetableMapDTO.getInsurancePricetableMapId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return insurancePricetableMapRepository
            .existsById(insurancePricetableMapId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<InsurancePricetableMapDTO> result = insurancePricetableMapService.partialUpdate(insurancePricetableMapDTO);

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
                                    res.getInsurancePricetableMapId().toString()
                                )
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /insurance-pricetable-maps} : get all the insurancePricetableMaps.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of insurancePricetableMaps in body.
     */
    @GetMapping("/insurance-pricetable-maps")
    public Mono<ResponseEntity<List<InsurancePricetableMapDTO>>> getAllInsurancePricetableMaps(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of InsurancePricetableMaps");
        return insurancePricetableMapService
            .countAll()
            .zipWith(insurancePricetableMapService.findAll(pageable).collectList())
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
     * {@code GET  /insurance-pricetable-maps/:id} : get the "id" insurancePricetableMap.
     *
     * @param id the id of the insurancePricetableMapDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the insurancePricetableMapDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/insurance-pricetable-maps/{id}")
    public Mono<ResponseEntity<InsurancePricetableMapDTO>> getInsurancePricetableMap(@PathVariable Long id) {
        log.debug("REST request to get InsurancePricetableMap : {}", id);
        Mono<InsurancePricetableMapDTO> insurancePricetableMapDTO = insurancePricetableMapService.findOne(id);
        return ResponseUtil.wrapOrNotFound(insurancePricetableMapDTO);
    }

    /**
     * {@code DELETE  /insurance-pricetable-maps/:id} : delete the "id" insurancePricetableMap.
     *
     * @param id the id of the insurancePricetableMapDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/insurance-pricetable-maps/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteInsurancePricetableMap(@PathVariable Long id) {
        log.debug("REST request to delete InsurancePricetableMap : {}", id);
        return insurancePricetableMapService
            .delete(id)
            .map(result ->
                ResponseEntity
                    .noContent()
                    .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                    .build()
            );
    }
}
