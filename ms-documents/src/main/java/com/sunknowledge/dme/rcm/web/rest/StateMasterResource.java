package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.domain.StateMaster;
import com.sunknowledge.dme.rcm.repository.StateMasterRepository;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.StateMaster}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class StateMasterResource {

    private final Logger log = LoggerFactory.getLogger(StateMasterResource.class);

    private static final String ENTITY_NAME = "documentsStateMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StateMasterRepository stateMasterRepository;

    public StateMasterResource(StateMasterRepository stateMasterRepository) {
        this.stateMasterRepository = stateMasterRepository;
    }

    /**
     * {@code POST  /state-masters} : Create a new stateMaster.
     *
     * @param stateMaster the stateMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new stateMaster, or with status {@code 400 (Bad Request)} if the stateMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/state-masters")
    public Mono<ResponseEntity<StateMaster>> createStateMaster(@RequestBody StateMaster stateMaster) throws URISyntaxException {
        log.debug("REST request to save StateMaster : {}", stateMaster);
        if (stateMaster.getStateId() != null) {
            throw new BadRequestAlertException("A new stateMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return stateMasterRepository
            .save(stateMaster)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/state-masters/" + result.getStateId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getStateId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /state-masters/:stateId} : Updates an existing stateMaster.
     *
     * @param stateId the id of the stateMaster to save.
     * @param stateMaster the stateMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated stateMaster,
     * or with status {@code 400 (Bad Request)} if the stateMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the stateMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/state-masters/{stateId}")
    public Mono<ResponseEntity<StateMaster>> updateStateMaster(
        @PathVariable(value = "stateId", required = false) final Long stateId,
        @RequestBody StateMaster stateMaster
    ) throws URISyntaxException {
        log.debug("REST request to update StateMaster : {}, {}", stateId, stateMaster);
        if (stateMaster.getStateId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(stateId, stateMaster.getStateId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return stateMasterRepository
            .existsById(stateId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return stateMasterRepository
                    .save(stateMaster)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, result.getStateId().toString())
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /state-masters/:stateId} : Partial updates given fields of an existing stateMaster, field will ignore if it is null
     *
     * @param stateId the id of the stateMaster to save.
     * @param stateMaster the stateMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated stateMaster,
     * or with status {@code 400 (Bad Request)} if the stateMaster is not valid,
     * or with status {@code 404 (Not Found)} if the stateMaster is not found,
     * or with status {@code 500 (Internal Server Error)} if the stateMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/state-masters/{stateId}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<StateMaster>> partialUpdateStateMaster(
        @PathVariable(value = "stateId", required = false) final Long stateId,
        @RequestBody StateMaster stateMaster
    ) throws URISyntaxException {
        log.debug("REST request to partial update StateMaster partially : {}, {}", stateId, stateMaster);
        if (stateMaster.getStateId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(stateId, stateMaster.getStateId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return stateMasterRepository
            .existsById(stateId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<StateMaster> result = stateMasterRepository
                    .findById(stateMaster.getStateId())
                    .map(existingStateMaster -> {
                        if (stateMaster.getStateCode() != null) {
                            existingStateMaster.setStateCode(stateMaster.getStateCode());
                        }
                        if (stateMaster.getStateName() != null) {
                            existingStateMaster.setStateName(stateMaster.getStateName());
                        }

                        return existingStateMaster;
                    })
                    .flatMap(stateMasterRepository::save);

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(res ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, res.getStateId().toString()))
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /state-masters} : get all the stateMasters.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of stateMasters in body.
     */
    @GetMapping("/state-masters")
    public Mono<List<StateMaster>> getAllStateMasters() {
        log.debug("REST request to get all StateMasters");
        return stateMasterRepository.findAll().collectList();
    }

    /**
     * {@code GET  /state-masters} : get all the stateMasters as a stream.
     * @return the {@link Flux} of stateMasters.
     */
    @GetMapping(value = "/state-masters", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<StateMaster> getAllStateMastersAsStream() {
        log.debug("REST request to get all StateMasters as a stream");
        return stateMasterRepository.findAll();
    }

    /**
     * {@code GET  /state-masters/:id} : get the "id" stateMaster.
     *
     * @param id the id of the stateMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the stateMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/state-masters/{id}")
    public Mono<ResponseEntity<StateMaster>> getStateMaster(@PathVariable Long id) {
        log.debug("REST request to get StateMaster : {}", id);
        Mono<StateMaster> stateMaster = stateMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(stateMaster);
    }

    /**
     * {@code DELETE  /state-masters/:id} : delete the "id" stateMaster.
     *
     * @param id the id of the stateMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/state-masters/{id}")
    public Mono<ResponseEntity<Void>> deleteStateMaster(@PathVariable Long id) {
        log.debug("REST request to delete StateMaster : {}", id);
        return stateMasterRepository
            .deleteById(id)
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
