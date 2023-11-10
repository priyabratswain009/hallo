package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.MemberElligibilityMasterRepository;
import com.sunknowledge.dme.rcm.service.MemberElligibilityMasterService;
import com.sunknowledge.dme.rcm.service.dto.MemberElligibilityMasterDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.MemberElligibilityMaster}.
 */
@RestController
@RequestMapping("/api")
public class MemberElligibilityMasterResource {

    private final Logger log = LoggerFactory.getLogger(MemberElligibilityMasterResource.class);

    private static final String ENTITY_NAME = "patientMemberElligibilityMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MemberElligibilityMasterService memberElligibilityMasterService;

    private final MemberElligibilityMasterRepository memberElligibilityMasterRepository;

    public MemberElligibilityMasterResource(
        MemberElligibilityMasterService memberElligibilityMasterService,
        MemberElligibilityMasterRepository memberElligibilityMasterRepository
    ) {
        this.memberElligibilityMasterService = memberElligibilityMasterService;
        this.memberElligibilityMasterRepository = memberElligibilityMasterRepository;
    }

    /**
     * {@code POST  /member-elligibility-masters} : Create a new memberElligibilityMaster.
     *
     * @param memberElligibilityMasterDTO the memberElligibilityMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new memberElligibilityMasterDTO, or with status {@code 400 (Bad Request)} if the memberElligibilityMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/member-elligibility-masters")
    public Mono<ResponseEntity<MemberElligibilityMasterDTO>> createMemberElligibilityMaster(
        @RequestBody MemberElligibilityMasterDTO memberElligibilityMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to save MemberElligibilityMaster : {}", memberElligibilityMasterDTO);
        if (memberElligibilityMasterDTO.getClaimElligibilityMasterId() != null) {
            throw new BadRequestAlertException("A new memberElligibilityMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return memberElligibilityMasterService
            .save(memberElligibilityMasterDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/member-elligibility-masters/" + result.getClaimElligibilityMasterId()))
                        .headers(
                            HeaderUtil.createEntityCreationAlert(
                                applicationName,
                                false,
                                ENTITY_NAME,
                                result.getClaimElligibilityMasterId().toString()
                            )
                        )
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /member-elligibility-masters/:claimElligibilityMasterId} : Updates an existing memberElligibilityMaster.
     *
     * @param claimElligibilityMasterId the id of the memberElligibilityMasterDTO to save.
     * @param memberElligibilityMasterDTO the memberElligibilityMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated memberElligibilityMasterDTO,
     * or with status {@code 400 (Bad Request)} if the memberElligibilityMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the memberElligibilityMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/member-elligibility-masters/{claimElligibilityMasterId}")
    public Mono<ResponseEntity<MemberElligibilityMasterDTO>> updateMemberElligibilityMaster(
        @PathVariable(value = "claimElligibilityMasterId", required = false) final Long claimElligibilityMasterId,
        @RequestBody MemberElligibilityMasterDTO memberElligibilityMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update MemberElligibilityMaster : {}, {}", claimElligibilityMasterId, memberElligibilityMasterDTO);
        if (memberElligibilityMasterDTO.getClaimElligibilityMasterId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(claimElligibilityMasterId, memberElligibilityMasterDTO.getClaimElligibilityMasterId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return memberElligibilityMasterRepository
            .existsById(claimElligibilityMasterId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return memberElligibilityMasterService
                    .update(memberElligibilityMasterDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                    applicationName,
                                    false,
                                    ENTITY_NAME,
                                    result.getClaimElligibilityMasterId().toString()
                                )
                            )
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /member-elligibility-masters/:claimElligibilityMasterId} : Partial updates given fields of an existing memberElligibilityMaster, field will ignore if it is null
     *
     * @param claimElligibilityMasterId the id of the memberElligibilityMasterDTO to save.
     * @param memberElligibilityMasterDTO the memberElligibilityMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated memberElligibilityMasterDTO,
     * or with status {@code 400 (Bad Request)} if the memberElligibilityMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the memberElligibilityMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the memberElligibilityMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/member-elligibility-masters/{claimElligibilityMasterId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public Mono<ResponseEntity<MemberElligibilityMasterDTO>> partialUpdateMemberElligibilityMaster(
        @PathVariable(value = "claimElligibilityMasterId", required = false) final Long claimElligibilityMasterId,
        @RequestBody MemberElligibilityMasterDTO memberElligibilityMasterDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update MemberElligibilityMaster partially : {}, {}",
            claimElligibilityMasterId,
            memberElligibilityMasterDTO
        );
        if (memberElligibilityMasterDTO.getClaimElligibilityMasterId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(claimElligibilityMasterId, memberElligibilityMasterDTO.getClaimElligibilityMasterId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return memberElligibilityMasterRepository
            .existsById(claimElligibilityMasterId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<MemberElligibilityMasterDTO> result = memberElligibilityMasterService.partialUpdate(memberElligibilityMasterDTO);

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
                                    res.getClaimElligibilityMasterId().toString()
                                )
                            )
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /member-elligibility-masters} : get all the memberElligibilityMasters.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of memberElligibilityMasters in body.
     */
    @GetMapping("/member-elligibility-masters")
    public Mono<ResponseEntity<List<MemberElligibilityMasterDTO>>> getAllMemberElligibilityMasters(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of MemberElligibilityMasters");
        return memberElligibilityMasterService
            .countAll()
            .zipWith(memberElligibilityMasterService.findAll(pageable).collectList())
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
     * {@code GET  /member-elligibility-masters/:id} : get the "id" memberElligibilityMaster.
     *
     * @param id the id of the memberElligibilityMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the memberElligibilityMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/member-elligibility-masters/{id}")
    public Mono<ResponseEntity<MemberElligibilityMasterDTO>> getMemberElligibilityMaster(@PathVariable Long id) {
        log.debug("REST request to get MemberElligibilityMaster : {}", id);
        Mono<MemberElligibilityMasterDTO> memberElligibilityMasterDTO = memberElligibilityMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(memberElligibilityMasterDTO);
    }

    /**
     * {@code DELETE  /member-elligibility-masters/:id} : delete the "id" memberElligibilityMaster.
     *
     * @param id the id of the memberElligibilityMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/member-elligibility-masters/{id}")
    public Mono<ResponseEntity<Void>> deleteMemberElligibilityMaster(@PathVariable Long id) {
        log.debug("REST request to delete MemberElligibilityMaster : {}", id);
        return memberElligibilityMasterService
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
