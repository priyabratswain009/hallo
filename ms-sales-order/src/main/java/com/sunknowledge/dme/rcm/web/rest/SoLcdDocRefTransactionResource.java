package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.SoLcdDocRefTransactionRepository;
import com.sunknowledge.dme.rcm.service.SoLcdDocRefTransactionService;
import com.sunknowledge.dme.rcm.service.dto.SoLcdDocRefTransactionDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.SoLcdDocRefTransaction}.
 */
@RestController
@RequestMapping("/api")
public class SoLcdDocRefTransactionResource {

    private final Logger log = LoggerFactory.getLogger(SoLcdDocRefTransactionResource.class);

    private static final String ENTITY_NAME = "salesorderSoLcdDocRefTransaction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SoLcdDocRefTransactionService soLcdDocRefTransactionService;

    private final SoLcdDocRefTransactionRepository soLcdDocRefTransactionRepository;

    public SoLcdDocRefTransactionResource(
        SoLcdDocRefTransactionService soLcdDocRefTransactionService,
        SoLcdDocRefTransactionRepository soLcdDocRefTransactionRepository
    ) {
        this.soLcdDocRefTransactionService = soLcdDocRefTransactionService;
        this.soLcdDocRefTransactionRepository = soLcdDocRefTransactionRepository;
    }

    /**
     * {@code POST  /so-lcd-doc-ref-transactions} : Create a new soLcdDocRefTransaction.
     *
     * @param soLcdDocRefTransactionDTO the soLcdDocRefTransactionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new soLcdDocRefTransactionDTO, or with status {@code 400 (Bad Request)} if the soLcdDocRefTransaction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/so-lcd-doc-ref-transactions")
    public Mono<ResponseEntity<SoLcdDocRefTransactionDTO>> createSoLcdDocRefTransaction(
        @Valid @RequestBody SoLcdDocRefTransactionDTO soLcdDocRefTransactionDTO
    ) throws URISyntaxException {
        log.debug("REST request to save SoLcdDocRefTransaction : {}", soLcdDocRefTransactionDTO);
        if (soLcdDocRefTransactionDTO.getSoLcdDocRefId() != null) {
            throw new BadRequestAlertException("A new soLcdDocRefTransaction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return soLcdDocRefTransactionService
            .save(soLcdDocRefTransactionDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/so-lcd-doc-ref-transactions/" + result.getSoLcdDocRefId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getSoLcdDocRefId().toString())
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /so-lcd-doc-ref-transactions/:soLcdDocRefId} : Updates an existing soLcdDocRefTransaction.
     *
     * @param soLcdDocRefId the id of the soLcdDocRefTransactionDTO to save.
     * @param soLcdDocRefTransactionDTO the soLcdDocRefTransactionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated soLcdDocRefTransactionDTO,
     * or with status {@code 400 (Bad Request)} if the soLcdDocRefTransactionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the soLcdDocRefTransactionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/so-lcd-doc-ref-transactions/{soLcdDocRefId}")
    public Mono<ResponseEntity<SoLcdDocRefTransactionDTO>> updateSoLcdDocRefTransaction(
        @PathVariable(value = "soLcdDocRefId", required = false) final Long soLcdDocRefId,
        @Valid @RequestBody SoLcdDocRefTransactionDTO soLcdDocRefTransactionDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SoLcdDocRefTransaction : {}, {}", soLcdDocRefId, soLcdDocRefTransactionDTO);
        if (soLcdDocRefTransactionDTO.getSoLcdDocRefId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(soLcdDocRefId, soLcdDocRefTransactionDTO.getSoLcdDocRefId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return soLcdDocRefTransactionRepository
            .existsById(soLcdDocRefId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return soLcdDocRefTransactionService
                    .update(soLcdDocRefTransactionDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getSoLcdDocRefId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /so-lcd-doc-ref-transactions/:soLcdDocRefId} : Partial updates given fields of an existing soLcdDocRefTransaction, field will ignore if it is null
     *
     * @param soLcdDocRefId the id of the soLcdDocRefTransactionDTO to save.
     * @param soLcdDocRefTransactionDTO the soLcdDocRefTransactionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated soLcdDocRefTransactionDTO,
     * or with status {@code 400 (Bad Request)} if the soLcdDocRefTransactionDTO is not valid,
     * or with status {@code 404 (Not Found)} if the soLcdDocRefTransactionDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the soLcdDocRefTransactionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/so-lcd-doc-ref-transactions/{soLcdDocRefId}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<SoLcdDocRefTransactionDTO>> partialUpdateSoLcdDocRefTransaction(
        @PathVariable(value = "soLcdDocRefId", required = false) final Long soLcdDocRefId,
        @NotNull @RequestBody SoLcdDocRefTransactionDTO soLcdDocRefTransactionDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update SoLcdDocRefTransaction partially : {}, {}", soLcdDocRefId, soLcdDocRefTransactionDTO);
        if (soLcdDocRefTransactionDTO.getSoLcdDocRefId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(soLcdDocRefId, soLcdDocRefTransactionDTO.getSoLcdDocRefId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return soLcdDocRefTransactionRepository
            .existsById(soLcdDocRefId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<SoLcdDocRefTransactionDTO> result = soLcdDocRefTransactionService.partialUpdate(soLcdDocRefTransactionDTO);

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(res ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, res.getSoLcdDocRefId().toString())
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /so-lcd-doc-ref-transactions} : get all the soLcdDocRefTransactions.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of soLcdDocRefTransactions in body.
     */
    @GetMapping("/so-lcd-doc-ref-transactions")
    public Mono<ResponseEntity<List<SoLcdDocRefTransactionDTO>>> getAllSoLcdDocRefTransactions(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of SoLcdDocRefTransactions");
        return soLcdDocRefTransactionService
            .countAll()
            .zipWith(soLcdDocRefTransactionService.findAll(pageable).collectList())
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
     * {@code GET  /so-lcd-doc-ref-transactions/:id} : get the "id" soLcdDocRefTransaction.
     *
     * @param id the id of the soLcdDocRefTransactionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the soLcdDocRefTransactionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/so-lcd-doc-ref-transactions/{id}")
    public Mono<ResponseEntity<SoLcdDocRefTransactionDTO>> getSoLcdDocRefTransaction(@PathVariable Long id) {
        log.debug("REST request to get SoLcdDocRefTransaction : {}", id);
        Mono<SoLcdDocRefTransactionDTO> soLcdDocRefTransactionDTO = soLcdDocRefTransactionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(soLcdDocRefTransactionDTO);
    }

    /**
     * {@code DELETE  /so-lcd-doc-ref-transactions/:id} : delete the "id" soLcdDocRefTransaction.
     *
     * @param id the id of the soLcdDocRefTransactionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/so-lcd-doc-ref-transactions/{id}")
    public Mono<ResponseEntity<Void>> deleteSoLcdDocRefTransaction(@PathVariable Long id) {
        log.debug("REST request to delete SoLcdDocRefTransaction : {}", id);
        return soLcdDocRefTransactionService
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
