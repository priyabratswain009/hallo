package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.SoLcdCoverageCriteriaTransactionRepository;
import com.sunknowledge.dme.rcm.service.SoLcdCoverageCriteriaTransactionService;
import com.sunknowledge.dme.rcm.service.dto.SoLcdCoverageCriteriaTransactionDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.SoLcdCoverageCriteriaTransaction}.
 */
@RestController
@RequestMapping("/api")
public class SoLcdCoverageCriteriaTransactionResource {

    private final Logger log = LoggerFactory.getLogger(SoLcdCoverageCriteriaTransactionResource.class);

    private static final String ENTITY_NAME = "salesorderSoLcdCoverageCriteriaTransaction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SoLcdCoverageCriteriaTransactionService soLcdCoverageCriteriaTransactionService;

    private final SoLcdCoverageCriteriaTransactionRepository soLcdCoverageCriteriaTransactionRepository;

    public SoLcdCoverageCriteriaTransactionResource(
        SoLcdCoverageCriteriaTransactionService soLcdCoverageCriteriaTransactionService,
        SoLcdCoverageCriteriaTransactionRepository soLcdCoverageCriteriaTransactionRepository
    ) {
        this.soLcdCoverageCriteriaTransactionService = soLcdCoverageCriteriaTransactionService;
        this.soLcdCoverageCriteriaTransactionRepository = soLcdCoverageCriteriaTransactionRepository;
    }

    /**
     * {@code POST  /so-lcd-coverage-criteria-transactions} : Create a new soLcdCoverageCriteriaTransaction.
     *
     * @param soLcdCoverageCriteriaTransactionDTO the soLcdCoverageCriteriaTransactionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new soLcdCoverageCriteriaTransactionDTO, or with status {@code 400 (Bad Request)} if the soLcdCoverageCriteriaTransaction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/so-lcd-coverage-criteria-transactions")
    public Mono<ResponseEntity<SoLcdCoverageCriteriaTransactionDTO>> createSoLcdCoverageCriteriaTransaction(
        @RequestBody SoLcdCoverageCriteriaTransactionDTO soLcdCoverageCriteriaTransactionDTO
    ) throws URISyntaxException {
        log.debug("REST request to save SoLcdCoverageCriteriaTransaction : {}", soLcdCoverageCriteriaTransactionDTO);
        if (soLcdCoverageCriteriaTransactionDTO.getSoLcdDocRefId() != null) {
            throw new BadRequestAlertException("A new soLcdCoverageCriteriaTransaction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return soLcdCoverageCriteriaTransactionService
            .save(soLcdCoverageCriteriaTransactionDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/so-lcd-coverage-criteria-transactions/" + result.getSoLcdDocRefId()))
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
     * {@code PUT  /so-lcd-coverage-criteria-transactions/:soLcdDocRefId} : Updates an existing soLcdCoverageCriteriaTransaction.
     *
     * @param soLcdDocRefId the id of the soLcdCoverageCriteriaTransactionDTO to save.
     * @param soLcdCoverageCriteriaTransactionDTO the soLcdCoverageCriteriaTransactionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated soLcdCoverageCriteriaTransactionDTO,
     * or with status {@code 400 (Bad Request)} if the soLcdCoverageCriteriaTransactionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the soLcdCoverageCriteriaTransactionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/so-lcd-coverage-criteria-transactions/{soLcdDocRefId}")
    public Mono<ResponseEntity<SoLcdCoverageCriteriaTransactionDTO>> updateSoLcdCoverageCriteriaTransaction(
        @PathVariable(value = "soLcdDocRefId", required = false) final Long soLcdDocRefId,
        @RequestBody SoLcdCoverageCriteriaTransactionDTO soLcdCoverageCriteriaTransactionDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SoLcdCoverageCriteriaTransaction : {}, {}", soLcdDocRefId, soLcdCoverageCriteriaTransactionDTO);
        if (soLcdCoverageCriteriaTransactionDTO.getSoLcdDocRefId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(soLcdDocRefId, soLcdCoverageCriteriaTransactionDTO.getSoLcdDocRefId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return soLcdCoverageCriteriaTransactionRepository
            .existsById(soLcdDocRefId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return soLcdCoverageCriteriaTransactionService
                    .update(soLcdCoverageCriteriaTransactionDTO)
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
     * {@code PATCH  /so-lcd-coverage-criteria-transactions/:soLcdDocRefId} : Partial updates given fields of an existing soLcdCoverageCriteriaTransaction, field will ignore if it is null
     *
     * @param soLcdDocRefId the id of the soLcdCoverageCriteriaTransactionDTO to save.
     * @param soLcdCoverageCriteriaTransactionDTO the soLcdCoverageCriteriaTransactionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated soLcdCoverageCriteriaTransactionDTO,
     * or with status {@code 400 (Bad Request)} if the soLcdCoverageCriteriaTransactionDTO is not valid,
     * or with status {@code 404 (Not Found)} if the soLcdCoverageCriteriaTransactionDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the soLcdCoverageCriteriaTransactionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/so-lcd-coverage-criteria-transactions/{soLcdDocRefId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public Mono<ResponseEntity<SoLcdCoverageCriteriaTransactionDTO>> partialUpdateSoLcdCoverageCriteriaTransaction(
        @PathVariable(value = "soLcdDocRefId", required = false) final Long soLcdDocRefId,
        @RequestBody SoLcdCoverageCriteriaTransactionDTO soLcdCoverageCriteriaTransactionDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update SoLcdCoverageCriteriaTransaction partially : {}, {}",
            soLcdDocRefId,
            soLcdCoverageCriteriaTransactionDTO
        );
        if (soLcdCoverageCriteriaTransactionDTO.getSoLcdDocRefId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(soLcdDocRefId, soLcdCoverageCriteriaTransactionDTO.getSoLcdDocRefId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return soLcdCoverageCriteriaTransactionRepository
            .existsById(soLcdDocRefId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<SoLcdCoverageCriteriaTransactionDTO> result = soLcdCoverageCriteriaTransactionService.partialUpdate(
                    soLcdCoverageCriteriaTransactionDTO
                );

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
     * {@code GET  /so-lcd-coverage-criteria-transactions} : get all the soLcdCoverageCriteriaTransactions.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of soLcdCoverageCriteriaTransactions in body.
     */
    @GetMapping("/so-lcd-coverage-criteria-transactions")
    public Mono<ResponseEntity<List<SoLcdCoverageCriteriaTransactionDTO>>> getAllSoLcdCoverageCriteriaTransactions(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of SoLcdCoverageCriteriaTransactions");
        return soLcdCoverageCriteriaTransactionService
            .countAll()
            .zipWith(soLcdCoverageCriteriaTransactionService.findAll(pageable).collectList())
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
     * {@code GET  /so-lcd-coverage-criteria-transactions/:id} : get the "id" soLcdCoverageCriteriaTransaction.
     *
     * @param id the id of the soLcdCoverageCriteriaTransactionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the soLcdCoverageCriteriaTransactionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/so-lcd-coverage-criteria-transactions/{id}")
    public Mono<ResponseEntity<SoLcdCoverageCriteriaTransactionDTO>> getSoLcdCoverageCriteriaTransaction(@PathVariable Long id) {
        log.debug("REST request to get SoLcdCoverageCriteriaTransaction : {}", id);
        Mono<SoLcdCoverageCriteriaTransactionDTO> soLcdCoverageCriteriaTransactionDTO = soLcdCoverageCriteriaTransactionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(soLcdCoverageCriteriaTransactionDTO);
    }

    /**
     * {@code DELETE  /so-lcd-coverage-criteria-transactions/:id} : delete the "id" soLcdCoverageCriteriaTransaction.
     *
     * @param id the id of the soLcdCoverageCriteriaTransactionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/so-lcd-coverage-criteria-transactions/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteSoLcdCoverageCriteriaTransaction(@PathVariable Long id) {
        log.debug("REST request to delete SoLcdCoverageCriteriaTransaction : {}", id);
        return soLcdCoverageCriteriaTransactionService
            .delete(id)
            .map(result ->
                ResponseEntity
                    .noContent()
                    .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                    .build()
            );
    }
}
