package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.PayerMasterRepository;
import com.sunknowledge.dme.rcm.service.PayerMasterService;
import com.sunknowledge.dme.rcm.service.dto.PayerMasterDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.PayerMaster}.
 */
@RestController
@RequestMapping("/api")
public class PayerMasterResource {

    private final Logger log = LoggerFactory.getLogger(PayerMasterResource.class);

    private static final String ENTITY_NAME = "salesorderPayerMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PayerMasterService payerMasterService;

    private final PayerMasterRepository payerMasterRepository;

    public PayerMasterResource(PayerMasterService payerMasterService, PayerMasterRepository payerMasterRepository) {
        this.payerMasterService = payerMasterService;
        this.payerMasterRepository = payerMasterRepository;
    }

    /**
     * {@code POST  /payer-masters} : Create a new payerMaster.
     *
     * @param payerMasterDTO the payerMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new payerMasterDTO, or with status {@code 400 (Bad Request)} if the payerMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/payer-masters")
    public Mono<ResponseEntity<PayerMasterDTO>> createPayerMaster(@RequestBody PayerMasterDTO payerMasterDTO) throws URISyntaxException {
        log.debug("REST request to save PayerMaster : {}", payerMasterDTO);
        if (payerMasterDTO.getPayerMasterId() != null) {
            throw new BadRequestAlertException("A new payerMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return payerMasterService
            .save(payerMasterDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/payer-masters/" + result.getPayerMasterId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getPayerMasterId().toString())
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /payer-masters/:payerMasterId} : Updates an existing payerMaster.
     *
     * @param payerMasterId the id of the payerMasterDTO to save.
     * @param payerMasterDTO the payerMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated payerMasterDTO,
     * or with status {@code 400 (Bad Request)} if the payerMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the payerMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/payer-masters/{payerMasterId}")
    public Mono<ResponseEntity<PayerMasterDTO>> updatePayerMaster(
        @PathVariable(value = "payerMasterId", required = false) final Long payerMasterId,
        @RequestBody PayerMasterDTO payerMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PayerMaster : {}, {}", payerMasterId, payerMasterDTO);
        if (payerMasterDTO.getPayerMasterId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(payerMasterId, payerMasterDTO.getPayerMasterId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return payerMasterRepository
            .existsById(payerMasterId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return payerMasterService
                    .update(payerMasterDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getPayerMasterId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /payer-masters/:payerMasterId} : Partial updates given fields of an existing payerMaster, field will ignore if it is null
     *
     * @param payerMasterId the id of the payerMasterDTO to save.
     * @param payerMasterDTO the payerMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated payerMasterDTO,
     * or with status {@code 400 (Bad Request)} if the payerMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the payerMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the payerMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/payer-masters/{payerMasterId}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<PayerMasterDTO>> partialUpdatePayerMaster(
        @PathVariable(value = "payerMasterId", required = false) final Long payerMasterId,
        @RequestBody PayerMasterDTO payerMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PayerMaster partially : {}, {}", payerMasterId, payerMasterDTO);
        if (payerMasterDTO.getPayerMasterId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(payerMasterId, payerMasterDTO.getPayerMasterId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return payerMasterRepository
            .existsById(payerMasterId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<PayerMasterDTO> result = payerMasterService.partialUpdate(payerMasterDTO);

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(res ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, res.getPayerMasterId().toString())
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /payer-masters} : get all the payerMasters.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of payerMasters in body.
     */
    @GetMapping("/payer-masters")
    public Mono<ResponseEntity<List<PayerMasterDTO>>> getAllPayerMasters(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of PayerMasters");
        return payerMasterService
            .countAll()
            .zipWith(payerMasterService.findAll(pageable).collectList())
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
     * {@code GET  /payer-masters/:id} : get the "id" payerMaster.
     *
     * @param id the id of the payerMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the payerMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/payer-masters/{id}")
    public Mono<ResponseEntity<PayerMasterDTO>> getPayerMaster(@PathVariable Long id) {
        log.debug("REST request to get PayerMaster : {}", id);
        Mono<PayerMasterDTO> payerMasterDTO = payerMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(payerMasterDTO);
    }

    /**
     * {@code DELETE  /payer-masters/:id} : delete the "id" payerMaster.
     *
     * @param id the id of the payerMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/payer-masters/{id}")
    public Mono<ResponseEntity<Void>> deletePayerMaster(@PathVariable Long id) {
        log.debug("REST request to delete PayerMaster : {}", id);
        return payerMasterService
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
