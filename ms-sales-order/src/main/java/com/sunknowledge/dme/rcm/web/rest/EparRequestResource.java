package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.EparRequestRepository;
import com.sunknowledge.dme.rcm.service.EparRequestService;
import com.sunknowledge.dme.rcm.service.dto.EparRequestDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.EparRequest}.
 */
@RestController
@RequestMapping("/api")
public class EparRequestResource {

    private final Logger log = LoggerFactory.getLogger(EparRequestResource.class);

    private static final String ENTITY_NAME = "salesorderEparRequest";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EparRequestService eparRequestService;

    private final EparRequestRepository eparRequestRepository;

    public EparRequestResource(EparRequestService eparRequestService, EparRequestRepository eparRequestRepository) {
        this.eparRequestService = eparRequestService;
        this.eparRequestRepository = eparRequestRepository;
    }

    /**
     * {@code POST  /epar-requests} : Create a new eparRequest.
     *
     * @param eparRequestDTO the eparRequestDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new eparRequestDTO, or with status {@code 400 (Bad Request)} if the eparRequest has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/epar-requests")
    public Mono<ResponseEntity<EparRequestDTO>> createEparRequest(@Valid @RequestBody EparRequestDTO eparRequestDTO)
        throws URISyntaxException {
        log.debug("REST request to save EparRequest : {}", eparRequestDTO);
        if (eparRequestDTO.getEparRequestId() != null) {
            throw new BadRequestAlertException("A new eparRequest cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return eparRequestService
            .save(eparRequestDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/epar-requests/" + result.getEparRequestId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getEparRequestId().toString())
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /epar-requests/:eparRequestId} : Updates an existing eparRequest.
     *
     * @param eparRequestId the id of the eparRequestDTO to save.
     * @param eparRequestDTO the eparRequestDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated eparRequestDTO,
     * or with status {@code 400 (Bad Request)} if the eparRequestDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the eparRequestDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/epar-requests/{eparRequestId}")
    public Mono<ResponseEntity<EparRequestDTO>> updateEparRequest(
        @PathVariable(value = "eparRequestId", required = false) final Long eparRequestId,
        @Valid @RequestBody EparRequestDTO eparRequestDTO
    ) throws URISyntaxException {
        log.debug("REST request to update EparRequest : {}, {}", eparRequestId, eparRequestDTO);
        if (eparRequestDTO.getEparRequestId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(eparRequestId, eparRequestDTO.getEparRequestId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return eparRequestRepository
            .existsById(eparRequestId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return eparRequestService
                    .update(eparRequestDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getEparRequestId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /epar-requests/:eparRequestId} : Partial updates given fields of an existing eparRequest, field will ignore if it is null
     *
     * @param eparRequestId the id of the eparRequestDTO to save.
     * @param eparRequestDTO the eparRequestDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated eparRequestDTO,
     * or with status {@code 400 (Bad Request)} if the eparRequestDTO is not valid,
     * or with status {@code 404 (Not Found)} if the eparRequestDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the eparRequestDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/epar-requests/{eparRequestId}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<EparRequestDTO>> partialUpdateEparRequest(
        @PathVariable(value = "eparRequestId", required = false) final Long eparRequestId,
        @NotNull @RequestBody EparRequestDTO eparRequestDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update EparRequest partially : {}, {}", eparRequestId, eparRequestDTO);
        if (eparRequestDTO.getEparRequestId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(eparRequestId, eparRequestDTO.getEparRequestId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return eparRequestRepository
            .existsById(eparRequestId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<EparRequestDTO> result = eparRequestService.partialUpdate(eparRequestDTO);

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(res ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, res.getEparRequestId().toString())
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /epar-requests} : get all the eparRequests.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of eparRequests in body.
     */
    @GetMapping("/epar-requests")
    public Mono<ResponseEntity<List<EparRequestDTO>>> getAllEparRequests(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of EparRequests");
        return eparRequestService
            .countAll()
            .zipWith(eparRequestService.findAll(pageable).collectList())
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
     * {@code GET  /epar-requests/:id} : get the "id" eparRequest.
     *
     * @param id the id of the eparRequestDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the eparRequestDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/epar-requests/{id}")
    public Mono<ResponseEntity<EparRequestDTO>> getEparRequest(@PathVariable Long id) {
        log.debug("REST request to get EparRequest : {}", id);
        Mono<EparRequestDTO> eparRequestDTO = eparRequestService.findOne(id);
        return ResponseUtil.wrapOrNotFound(eparRequestDTO);
    }

    /**
     * {@code DELETE  /epar-requests/:id} : delete the "id" eparRequest.
     *
     * @param id the id of the eparRequestDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/epar-requests/{id}")
    public Mono<ResponseEntity<Void>> deleteEparRequest(@PathVariable Long id) {
        log.debug("REST request to delete EparRequest : {}", id);
        return eparRequestService
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
