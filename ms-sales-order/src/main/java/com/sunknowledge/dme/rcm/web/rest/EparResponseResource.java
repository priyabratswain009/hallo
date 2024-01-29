package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.EparResponseRepository;
import com.sunknowledge.dme.rcm.service.EparResponseService;
import com.sunknowledge.dme.rcm.service.dto.EparResponseDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.EparResponse}.
 */
@RestController
@RequestMapping("/api")
public class EparResponseResource {

    private final Logger log = LoggerFactory.getLogger(EparResponseResource.class);

    private static final String ENTITY_NAME = "salesorderEparResponse";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EparResponseService eparResponseService;

    private final EparResponseRepository eparResponseRepository;

    public EparResponseResource(EparResponseService eparResponseService, EparResponseRepository eparResponseRepository) {
        this.eparResponseService = eparResponseService;
        this.eparResponseRepository = eparResponseRepository;
    }

    /**
     * {@code POST  /epar-responses} : Create a new eparResponse.
     *
     * @param eparResponseDTO the eparResponseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new eparResponseDTO, or with status {@code 400 (Bad Request)} if the eparResponse has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/epar-responses")
    public Mono<ResponseEntity<EparResponseDTO>> createEparResponse(@Valid @RequestBody EparResponseDTO eparResponseDTO)
        throws URISyntaxException {
        log.debug("REST request to save EparResponse : {}", eparResponseDTO);
        if (eparResponseDTO.getEparResponseId() != null) {
            throw new BadRequestAlertException("A new eparResponse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return eparResponseService
            .save(eparResponseDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/epar-responses/" + result.getEparResponseId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getEparResponseId().toString())
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /epar-responses/:eparResponseId} : Updates an existing eparResponse.
     *
     * @param eparResponseId the id of the eparResponseDTO to save.
     * @param eparResponseDTO the eparResponseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated eparResponseDTO,
     * or with status {@code 400 (Bad Request)} if the eparResponseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the eparResponseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/epar-responses/{eparResponseId}")
    public Mono<ResponseEntity<EparResponseDTO>> updateEparResponse(
        @PathVariable(value = "eparResponseId", required = false) final Long eparResponseId,
        @Valid @RequestBody EparResponseDTO eparResponseDTO
    ) throws URISyntaxException {
        log.debug("REST request to update EparResponse : {}, {}", eparResponseId, eparResponseDTO);
        if (eparResponseDTO.getEparResponseId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(eparResponseId, eparResponseDTO.getEparResponseId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return eparResponseRepository
            .existsById(eparResponseId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return eparResponseService
                    .update(eparResponseDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getEparResponseId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /epar-responses/:eparResponseId} : Partial updates given fields of an existing eparResponse, field will ignore if it is null
     *
     * @param eparResponseId the id of the eparResponseDTO to save.
     * @param eparResponseDTO the eparResponseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated eparResponseDTO,
     * or with status {@code 400 (Bad Request)} if the eparResponseDTO is not valid,
     * or with status {@code 404 (Not Found)} if the eparResponseDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the eparResponseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/epar-responses/{eparResponseId}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<EparResponseDTO>> partialUpdateEparResponse(
        @PathVariable(value = "eparResponseId", required = false) final Long eparResponseId,
        @NotNull @RequestBody EparResponseDTO eparResponseDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update EparResponse partially : {}, {}", eparResponseId, eparResponseDTO);
        if (eparResponseDTO.getEparResponseId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(eparResponseId, eparResponseDTO.getEparResponseId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return eparResponseRepository
            .existsById(eparResponseId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<EparResponseDTO> result = eparResponseService.partialUpdate(eparResponseDTO);

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(res ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, res.getEparResponseId().toString())
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /epar-responses} : get all the eparResponses.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of eparResponses in body.
     */
    @GetMapping("/epar-responses")
    public Mono<ResponseEntity<List<EparResponseDTO>>> getAllEparResponses(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of EparResponses");
        return eparResponseService
            .countAll()
            .zipWith(eparResponseService.findAll(pageable).collectList())
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
     * {@code GET  /epar-responses/:id} : get the "id" eparResponse.
     *
     * @param id the id of the eparResponseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the eparResponseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/epar-responses/{id}")
    public Mono<ResponseEntity<EparResponseDTO>> getEparResponse(@PathVariable Long id) {
        log.debug("REST request to get EparResponse : {}", id);
        Mono<EparResponseDTO> eparResponseDTO = eparResponseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(eparResponseDTO);
    }

    /**
     * {@code DELETE  /epar-responses/:id} : delete the "id" eparResponse.
     *
     * @param id the id of the eparResponseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/epar-responses/{id}")
    public Mono<ResponseEntity<Void>> deleteEparResponse(@PathVariable Long id) {
        log.debug("REST request to delete EparResponse : {}", id);
        return eparResponseService
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
