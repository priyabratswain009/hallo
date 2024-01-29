package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.EfaxResponseRepository;
import com.sunknowledge.dme.rcm.service.EfaxResponseService;
import com.sunknowledge.dme.rcm.service.dto.EfaxResponseDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.EfaxResponse}.
 */
@RestController
@RequestMapping("/api")
public class EfaxResponseResource {

    private final Logger log = LoggerFactory.getLogger(EfaxResponseResource.class);

    private static final String ENTITY_NAME = "salesorderEfaxResponse";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EfaxResponseService efaxResponseService;

    private final EfaxResponseRepository efaxResponseRepository;

    public EfaxResponseResource(EfaxResponseService efaxResponseService, EfaxResponseRepository efaxResponseRepository) {
        this.efaxResponseService = efaxResponseService;
        this.efaxResponseRepository = efaxResponseRepository;
    }

    /**
     * {@code POST  /efax-responses} : Create a new efaxResponse.
     *
     * @param efaxResponseDTO the efaxResponseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new efaxResponseDTO, or with status {@code 400 (Bad Request)} if the efaxResponse has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/efax-responses")
    public Mono<ResponseEntity<EfaxResponseDTO>> createEfaxResponse(@Valid @RequestBody EfaxResponseDTO efaxResponseDTO)
        throws URISyntaxException {
        log.debug("REST request to save EfaxResponse : {}", efaxResponseDTO);
        if (efaxResponseDTO.getEfaxResponseId() != null) {
            throw new BadRequestAlertException("A new efaxResponse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return efaxResponseService
            .save(efaxResponseDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/efax-responses/" + result.getEfaxResponseId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getEfaxResponseId().toString())
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /efax-responses/:efaxResponseId} : Updates an existing efaxResponse.
     *
     * @param efaxResponseId the id of the efaxResponseDTO to save.
     * @param efaxResponseDTO the efaxResponseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated efaxResponseDTO,
     * or with status {@code 400 (Bad Request)} if the efaxResponseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the efaxResponseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/efax-responses/{efaxResponseId}")
    public Mono<ResponseEntity<EfaxResponseDTO>> updateEfaxResponse(
        @PathVariable(value = "efaxResponseId", required = false) final Long efaxResponseId,
        @Valid @RequestBody EfaxResponseDTO efaxResponseDTO
    ) throws URISyntaxException {
        log.debug("REST request to update EfaxResponse : {}, {}", efaxResponseId, efaxResponseDTO);
        if (efaxResponseDTO.getEfaxResponseId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(efaxResponseId, efaxResponseDTO.getEfaxResponseId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return efaxResponseRepository
            .existsById(efaxResponseId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return efaxResponseService
                    .update(efaxResponseDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getEfaxResponseId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /efax-responses/:efaxResponseId} : Partial updates given fields of an existing efaxResponse, field will ignore if it is null
     *
     * @param efaxResponseId the id of the efaxResponseDTO to save.
     * @param efaxResponseDTO the efaxResponseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated efaxResponseDTO,
     * or with status {@code 400 (Bad Request)} if the efaxResponseDTO is not valid,
     * or with status {@code 404 (Not Found)} if the efaxResponseDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the efaxResponseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/efax-responses/{efaxResponseId}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<EfaxResponseDTO>> partialUpdateEfaxResponse(
        @PathVariable(value = "efaxResponseId", required = false) final Long efaxResponseId,
        @NotNull @RequestBody EfaxResponseDTO efaxResponseDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update EfaxResponse partially : {}, {}", efaxResponseId, efaxResponseDTO);
        if (efaxResponseDTO.getEfaxResponseId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(efaxResponseId, efaxResponseDTO.getEfaxResponseId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return efaxResponseRepository
            .existsById(efaxResponseId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<EfaxResponseDTO> result = efaxResponseService.partialUpdate(efaxResponseDTO);

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(res ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, res.getEfaxResponseId().toString())
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /efax-responses} : get all the efaxResponses.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of efaxResponses in body.
     */
    @GetMapping("/efax-responses")
    public Mono<ResponseEntity<List<EfaxResponseDTO>>> getAllEfaxResponses(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of EfaxResponses");
        return efaxResponseService
            .countAll()
            .zipWith(efaxResponseService.findAll(pageable).collectList())
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
     * {@code GET  /efax-responses/:id} : get the "id" efaxResponse.
     *
     * @param id the id of the efaxResponseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the efaxResponseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/efax-responses/{id}")
    public Mono<ResponseEntity<EfaxResponseDTO>> getEfaxResponse(@PathVariable Long id) {
        log.debug("REST request to get EfaxResponse : {}", id);
        Mono<EfaxResponseDTO> efaxResponseDTO = efaxResponseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(efaxResponseDTO);
    }

    /**
     * {@code DELETE  /efax-responses/:id} : delete the "id" efaxResponse.
     *
     * @param id the id of the efaxResponseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/efax-responses/{id}")
    public Mono<ResponseEntity<Void>> deleteEfaxResponse(@PathVariable Long id) {
        log.debug("REST request to delete EfaxResponse : {}", id);
        return efaxResponseService
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
