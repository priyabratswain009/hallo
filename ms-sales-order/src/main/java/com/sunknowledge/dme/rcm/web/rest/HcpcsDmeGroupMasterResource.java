package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.HcpcsDmeGroupMasterRepository;
import com.sunknowledge.dme.rcm.service.HcpcsDmeGroupMasterService;
import com.sunknowledge.dme.rcm.service.dto.HcpcsDmeGroupMasterDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.HcpcsDmeGroupMaster}.
 */
@RestController
@RequestMapping("/api")
public class HcpcsDmeGroupMasterResource {

    private final Logger log = LoggerFactory.getLogger(HcpcsDmeGroupMasterResource.class);

    private static final String ENTITY_NAME = "salesorderHcpcsDmeGroupMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HcpcsDmeGroupMasterService hcpcsDmeGroupMasterService;

    private final HcpcsDmeGroupMasterRepository hcpcsDmeGroupMasterRepository;

    public HcpcsDmeGroupMasterResource(
        HcpcsDmeGroupMasterService hcpcsDmeGroupMasterService,
        HcpcsDmeGroupMasterRepository hcpcsDmeGroupMasterRepository
    ) {
        this.hcpcsDmeGroupMasterService = hcpcsDmeGroupMasterService;
        this.hcpcsDmeGroupMasterRepository = hcpcsDmeGroupMasterRepository;
    }

    /**
     * {@code POST  /hcpcs-dme-group-masters} : Create a new hcpcsDmeGroupMaster.
     *
     * @param hcpcsDmeGroupMasterDTO the hcpcsDmeGroupMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new hcpcsDmeGroupMasterDTO, or with status {@code 400 (Bad Request)} if the hcpcsDmeGroupMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/hcpcs-dme-group-masters")
    public Mono<ResponseEntity<HcpcsDmeGroupMasterDTO>> createHcpcsDmeGroupMaster(
        @Valid @RequestBody HcpcsDmeGroupMasterDTO hcpcsDmeGroupMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to save HcpcsDmeGroupMaster : {}", hcpcsDmeGroupMasterDTO);
        if (hcpcsDmeGroupMasterDTO.getHcpcsDmeId() != null) {
            throw new BadRequestAlertException("A new hcpcsDmeGroupMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return hcpcsDmeGroupMasterService
            .save(hcpcsDmeGroupMasterDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/hcpcs-dme-group-masters/" + result.getHcpcsDmeId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getHcpcsDmeId().toString())
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /hcpcs-dme-group-masters/:hcpcsDmeId} : Updates an existing hcpcsDmeGroupMaster.
     *
     * @param hcpcsDmeId the id of the hcpcsDmeGroupMasterDTO to save.
     * @param hcpcsDmeGroupMasterDTO the hcpcsDmeGroupMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated hcpcsDmeGroupMasterDTO,
     * or with status {@code 400 (Bad Request)} if the hcpcsDmeGroupMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the hcpcsDmeGroupMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/hcpcs-dme-group-masters/{hcpcsDmeId}")
    public Mono<ResponseEntity<HcpcsDmeGroupMasterDTO>> updateHcpcsDmeGroupMaster(
        @PathVariable(value = "hcpcsDmeId", required = false) final Long hcpcsDmeId,
        @Valid @RequestBody HcpcsDmeGroupMasterDTO hcpcsDmeGroupMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update HcpcsDmeGroupMaster : {}, {}", hcpcsDmeId, hcpcsDmeGroupMasterDTO);
        if (hcpcsDmeGroupMasterDTO.getHcpcsDmeId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(hcpcsDmeId, hcpcsDmeGroupMasterDTO.getHcpcsDmeId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return hcpcsDmeGroupMasterRepository
            .existsById(hcpcsDmeId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return hcpcsDmeGroupMasterService
                    .update(hcpcsDmeGroupMasterDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, result.getHcpcsDmeId().toString())
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /hcpcs-dme-group-masters/:hcpcsDmeId} : Partial updates given fields of an existing hcpcsDmeGroupMaster, field will ignore if it is null
     *
     * @param hcpcsDmeId the id of the hcpcsDmeGroupMasterDTO to save.
     * @param hcpcsDmeGroupMasterDTO the hcpcsDmeGroupMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated hcpcsDmeGroupMasterDTO,
     * or with status {@code 400 (Bad Request)} if the hcpcsDmeGroupMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the hcpcsDmeGroupMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the hcpcsDmeGroupMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/hcpcs-dme-group-masters/{hcpcsDmeId}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<HcpcsDmeGroupMasterDTO>> partialUpdateHcpcsDmeGroupMaster(
        @PathVariable(value = "hcpcsDmeId", required = false) final Long hcpcsDmeId,
        @NotNull @RequestBody HcpcsDmeGroupMasterDTO hcpcsDmeGroupMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update HcpcsDmeGroupMaster partially : {}, {}", hcpcsDmeId, hcpcsDmeGroupMasterDTO);
        if (hcpcsDmeGroupMasterDTO.getHcpcsDmeId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(hcpcsDmeId, hcpcsDmeGroupMasterDTO.getHcpcsDmeId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return hcpcsDmeGroupMasterRepository
            .existsById(hcpcsDmeId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<HcpcsDmeGroupMasterDTO> result = hcpcsDmeGroupMasterService.partialUpdate(hcpcsDmeGroupMasterDTO);

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(res ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, res.getHcpcsDmeId().toString())
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /hcpcs-dme-group-masters} : get all the hcpcsDmeGroupMasters.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of hcpcsDmeGroupMasters in body.
     */
    @GetMapping("/hcpcs-dme-group-masters")
    public Mono<ResponseEntity<List<HcpcsDmeGroupMasterDTO>>> getAllHcpcsDmeGroupMasters(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of HcpcsDmeGroupMasters");
        return hcpcsDmeGroupMasterService
            .countAll()
            .zipWith(hcpcsDmeGroupMasterService.findAll(pageable).collectList())
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
     * {@code GET  /hcpcs-dme-group-masters/:id} : get the "id" hcpcsDmeGroupMaster.
     *
     * @param id the id of the hcpcsDmeGroupMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the hcpcsDmeGroupMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/hcpcs-dme-group-masters/{id}")
    public Mono<ResponseEntity<HcpcsDmeGroupMasterDTO>> getHcpcsDmeGroupMaster(@PathVariable Long id) {
        log.debug("REST request to get HcpcsDmeGroupMaster : {}", id);
        Mono<HcpcsDmeGroupMasterDTO> hcpcsDmeGroupMasterDTO = hcpcsDmeGroupMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(hcpcsDmeGroupMasterDTO);
    }

    /**
     * {@code DELETE  /hcpcs-dme-group-masters/:id} : delete the "id" hcpcsDmeGroupMaster.
     *
     * @param id the id of the hcpcsDmeGroupMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/hcpcs-dme-group-masters/{id}")
    public Mono<ResponseEntity<Void>> deleteHcpcsDmeGroupMaster(@PathVariable Long id) {
        log.debug("REST request to delete HcpcsDmeGroupMaster : {}", id);
        return hcpcsDmeGroupMasterService
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
