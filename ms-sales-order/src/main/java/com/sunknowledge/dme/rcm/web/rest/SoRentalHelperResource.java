package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.domain.SoRentalHelper;
import com.sunknowledge.dme.rcm.repository.SoRentalHelperRepository;
import com.sunknowledge.dme.rcm.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.reactive.ResponseUtil;

/**
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.SoRentalHelper}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SoRentalHelperResource {

    private final Logger log = LoggerFactory.getLogger(SoRentalHelperResource.class);

    private static final String ENTITY_NAME = "salesorderSoRentalHelper";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SoRentalHelperRepository soRentalHelperRepository;

    public SoRentalHelperResource(SoRentalHelperRepository soRentalHelperRepository) {
        this.soRentalHelperRepository = soRentalHelperRepository;
    }

    /**
     * {@code POST  /so-rental-helpers} : Create a new soRentalHelper.
     *
     * @param soRentalHelper the soRentalHelper to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new soRentalHelper, or with status {@code 400 (Bad Request)} if the soRentalHelper has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/so-rental-helpers")
    public Mono<ResponseEntity<SoRentalHelper>> createSoRentalHelper(@RequestBody SoRentalHelper soRentalHelper) throws URISyntaxException {
        log.debug("REST request to save SoRentalHelper : {}", soRentalHelper);
        if (soRentalHelper.getSoRentalHelperId() != null) {
            throw new BadRequestAlertException("A new soRentalHelper cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return soRentalHelperRepository
            .save(soRentalHelper)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/so-rental-helpers/" + result.getSoRentalHelperId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(
                                applicationName,
                                false,
                                ENTITY_NAME,
                                result.getSoRentalHelperId().toString()
                            )
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /so-rental-helpers/:soRentalHelperId} : Updates an existing soRentalHelper.
     *
     * @param soRentalHelperId the id of the soRentalHelper to save.
     * @param soRentalHelper the soRentalHelper to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated soRentalHelper,
     * or with status {@code 400 (Bad Request)} if the soRentalHelper is not valid,
     * or with status {@code 500 (Internal Server Error)} if the soRentalHelper couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/so-rental-helpers/{soRentalHelperId}")
    public Mono<ResponseEntity<SoRentalHelper>> updateSoRentalHelper(
        @PathVariable(value = "soRentalHelperId", required = false) final Long soRentalHelperId,
        @RequestBody SoRentalHelper soRentalHelper
    ) throws URISyntaxException {
        log.debug("REST request to update SoRentalHelper : {}, {}", soRentalHelperId, soRentalHelper);
        if (soRentalHelper.getSoRentalHelperId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(soRentalHelperId, soRentalHelper.getSoRentalHelperId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return soRentalHelperRepository
            .existsById(soRentalHelperId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return soRentalHelperRepository
                    .save(soRentalHelper)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getSoRentalHelperId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /so-rental-helpers/:soRentalHelperId} : Partial updates given fields of an existing soRentalHelper, field will ignore if it is null
     *
     * @param soRentalHelperId the id of the soRentalHelper to save.
     * @param soRentalHelper the soRentalHelper to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated soRentalHelper,
     * or with status {@code 400 (Bad Request)} if the soRentalHelper is not valid,
     * or with status {@code 404 (Not Found)} if the soRentalHelper is not found,
     * or with status {@code 500 (Internal Server Error)} if the soRentalHelper couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/so-rental-helpers/{soRentalHelperId}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<SoRentalHelper>> partialUpdateSoRentalHelper(
        @PathVariable(value = "soRentalHelperId", required = false) final Long soRentalHelperId,
        @RequestBody SoRentalHelper soRentalHelper
    ) throws URISyntaxException {
        log.debug("REST request to partial update SoRentalHelper partially : {}, {}", soRentalHelperId, soRentalHelper);
        if (soRentalHelper.getSoRentalHelperId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(soRentalHelperId, soRentalHelper.getSoRentalHelperId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return soRentalHelperRepository
            .existsById(soRentalHelperId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<SoRentalHelper> result = soRentalHelperRepository
                    .findById(soRentalHelper.getSoRentalHelperId())
                    .map(existingSoRentalHelper -> {
                        if (soRentalHelper.getSoId() != null) {
                            existingSoRentalHelper.setSoId(soRentalHelper.getSoId());
                        }
                        if (soRentalHelper.getPrimaryInsurerId() != null) {
                            existingSoRentalHelper.setPrimaryInsurerId(soRentalHelper.getPrimaryInsurerId());
                        }
                        if (soRentalHelper.getPrimaryInsurerName() != null) {
                            existingSoRentalHelper.setPrimaryInsurerName(soRentalHelper.getPrimaryInsurerName());
                        }
                        if (soRentalHelper.getItemId() != null) {
                            existingSoRentalHelper.setItemId(soRentalHelper.getItemId());
                        }
                        if (soRentalHelper.getItemNo() != null) {
                            existingSoRentalHelper.setItemNo(soRentalHelper.getItemNo());
                        }
                        if (soRentalHelper.getItemName() != null) {
                            existingSoRentalHelper.setItemName(soRentalHelper.getItemName());
                        }
                        if (soRentalHelper.getChargedAmount() != null) {
                            existingSoRentalHelper.setChargedAmount(soRentalHelper.getChargedAmount());
                        }
                        if (soRentalHelper.getAllowedAmount() != null) {
                            existingSoRentalHelper.setAllowedAmount(soRentalHelper.getAllowedAmount());
                        }
                        if (soRentalHelper.getSou() != null) {
                            existingSoRentalHelper.setSou(soRentalHelper.getSou());
                        }
                        if (soRentalHelper.getQty() != null) {
                            existingSoRentalHelper.setQty(soRentalHelper.getQty());
                        }
                        if (soRentalHelper.getDosStart() != null) {
                            existingSoRentalHelper.setDosStart(soRentalHelper.getDosStart());
                        }
                        if (soRentalHelper.getDosEnd() != null) {
                            existingSoRentalHelper.setDosEnd(soRentalHelper.getDosEnd());
                        }
                        if (soRentalHelper.getPeriodNo() != null) {
                            existingSoRentalHelper.setPeriodNo(soRentalHelper.getPeriodNo());
                        }
                        if (soRentalHelper.getRentalPeriod() != null) {
                            existingSoRentalHelper.setRentalPeriod(soRentalHelper.getRentalPeriod());
                        }
                        if (soRentalHelper.getNextDos() != null) {
                            existingSoRentalHelper.setNextDos(soRentalHelper.getNextDos());
                        }
                        if (soRentalHelper.getStatus() != null) {
                            existingSoRentalHelper.setStatus(soRentalHelper.getStatus());
                        }
                        if (soRentalHelper.getCreatedById() != null) {
                            existingSoRentalHelper.setCreatedById(soRentalHelper.getCreatedById());
                        }
                        if (soRentalHelper.getCreatedDate() != null) {
                            existingSoRentalHelper.setCreatedDate(soRentalHelper.getCreatedDate());
                        }
                        if (soRentalHelper.getCreatedByName() != null) {
                            existingSoRentalHelper.setCreatedByName(soRentalHelper.getCreatedByName());
                        }
                        if (soRentalHelper.getUpdatedById() != null) {
                            existingSoRentalHelper.setUpdatedById(soRentalHelper.getUpdatedById());
                        }
                        if (soRentalHelper.getUpdatedDate() != null) {
                            existingSoRentalHelper.setUpdatedDate(soRentalHelper.getUpdatedDate());
                        }
                        if (soRentalHelper.getUpdatedByName() != null) {
                            existingSoRentalHelper.setUpdatedByName(soRentalHelper.getUpdatedByName());
                        }
                        if (soRentalHelper.getSoRentalHelperUuid() != null) {
                            existingSoRentalHelper.setSoRentalHelperUuid(soRentalHelper.getSoRentalHelperUuid());
                        }
                        if (soRentalHelper.getPatientId() != null) {
                            existingSoRentalHelper.setPatientId(soRentalHelper.getPatientId());
                        }
                        if (soRentalHelper.getSaleType() != null) {
                            existingSoRentalHelper.setSaleType(soRentalHelper.getSaleType());
                        }
                        if (soRentalHelper.getPrimaryInsurancePriceTableId() != null) {
                            existingSoRentalHelper.setPrimaryInsurancePriceTableId(soRentalHelper.getPrimaryInsurancePriceTableId());
                        }
                        if (soRentalHelper.getPrimaryInsurancePriceTableName() != null) {
                            existingSoRentalHelper.setPrimaryInsurancePriceTableName(soRentalHelper.getPrimaryInsurancePriceTableName());
                        }
                        if (soRentalHelper.getModifier1() != null) {
                            existingSoRentalHelper.setModifier1(soRentalHelper.getModifier1());
                        }
                        if (soRentalHelper.getModifier2() != null) {
                            existingSoRentalHelper.setModifier2(soRentalHelper.getModifier2());
                        }
                        if (soRentalHelper.getModifier3() != null) {
                            existingSoRentalHelper.setModifier3(soRentalHelper.getModifier3());
                        }
                        if (soRentalHelper.getModifier4() != null) {
                            existingSoRentalHelper.setModifier4(soRentalHelper.getModifier4());
                        }
                        if (soRentalHelper.getIcdPointer() != null) {
                            existingSoRentalHelper.setIcdPointer(soRentalHelper.getIcdPointer());
                        }
                        if (soRentalHelper.getProcedureIdentifier() != null) {
                            existingSoRentalHelper.setProcedureIdentifier(soRentalHelper.getProcedureIdentifier());
                        }
                        if (soRentalHelper.getOrderingProviderFirstName() != null) {
                            existingSoRentalHelper.setOrderingProviderFirstName(soRentalHelper.getOrderingProviderFirstName());
                        }
                        if (soRentalHelper.getOrderingProviderLastName() != null) {
                            existingSoRentalHelper.setOrderingProviderLastName(soRentalHelper.getOrderingProviderLastName());
                        }
                        if (soRentalHelper.getOrderingProviderNpi() != null) {
                            existingSoRentalHelper.setOrderingProviderNpi(soRentalHelper.getOrderingProviderNpi());
                        }
                        if (soRentalHelper.getReference() != null) {
                            existingSoRentalHelper.setReference(soRentalHelper.getReference());
                        }
                        if (soRentalHelper.getProcCode() != null) {
                            existingSoRentalHelper.setProcCode(soRentalHelper.getProcCode());
                        }

                        return existingSoRentalHelper;
                    })
                    .flatMap(soRentalHelperRepository::save);

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
                                    res.getSoRentalHelperId().toString()
                                )
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /so-rental-helpers} : get all the soRentalHelpers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of soRentalHelpers in body.
     */
    @GetMapping("/so-rental-helpers")
    public Mono<List<SoRentalHelper>> getAllSoRentalHelpers() {
        log.debug("REST request to get all SoRentalHelpers");
        return soRentalHelperRepository.findAll().collectList();
    }

    /**
     * {@code GET  /so-rental-helpers} : get all the soRentalHelpers as a stream.
     * @return the {@link Flux} of soRentalHelpers.
     */
    @GetMapping(value = "/so-rental-helpers", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<SoRentalHelper> getAllSoRentalHelpersAsStream() {
        log.debug("REST request to get all SoRentalHelpers as a stream");
        return soRentalHelperRepository.findAll();
    }

    /**
     * {@code GET  /so-rental-helpers/:id} : get the "id" soRentalHelper.
     *
     * @param id the id of the soRentalHelper to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the soRentalHelper, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/so-rental-helpers/{id}")
    public Mono<ResponseEntity<SoRentalHelper>> getSoRentalHelper(@PathVariable Long id) {
        log.debug("REST request to get SoRentalHelper : {}", id);
        Mono<SoRentalHelper> soRentalHelper = soRentalHelperRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(soRentalHelper);
    }

    /**
     * {@code DELETE  /so-rental-helpers/:id} : delete the "id" soRentalHelper.
     *
     * @param id the id of the soRentalHelper to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/so-rental-helpers/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteSoRentalHelper(@PathVariable Long id) {
        log.debug("REST request to delete SoRentalHelper : {}", id);
        return soRentalHelperRepository
            .deleteById(id)
            .map(result ->
                ResponseEntity
                    .noContent()
                    .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                    .build()
            );
    }
}
