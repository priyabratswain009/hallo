package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.MemberElligibilityRepository;
import com.sunknowledge.dme.rcm.service.MemberElligibilityService;
import com.sunknowledge.dme.rcm.service.dto.MemberElligibilityDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.MemberElligibility}.
 */
@RestController
@RequestMapping("/api")
public class MemberElligibilityResource {

    private final Logger log = LoggerFactory.getLogger(MemberElligibilityResource.class);

    private static final String ENTITY_NAME = "salesorderMemberElligibility";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MemberElligibilityService memberElligibilityService;

    private final MemberElligibilityRepository memberElligibilityRepository;

    public MemberElligibilityResource(
        MemberElligibilityService memberElligibilityService,
        MemberElligibilityRepository memberElligibilityRepository
    ) {
        this.memberElligibilityService = memberElligibilityService;
        this.memberElligibilityRepository = memberElligibilityRepository;
    }

    /**
     * {@code POST  /member-elligibilities} : Create a new memberElligibility.
     *
     * @param memberElligibilityDTO the memberElligibilityDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new memberElligibilityDTO, or with status {@code 400 (Bad Request)} if the memberElligibility has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/member-elligibilities")
    public Mono<ResponseEntity<MemberElligibilityDTO>> createMemberElligibility(
        @Valid @RequestBody MemberElligibilityDTO memberElligibilityDTO
    ) throws URISyntaxException {
        log.debug("REST request to save MemberElligibility : {}", memberElligibilityDTO);
        if (memberElligibilityDTO.getClaimElligibilityMasterId() != null) {
            throw new BadRequestAlertException("A new memberElligibility cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return memberElligibilityService
            .save(memberElligibilityDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/member-elligibilities/" + result.getClaimElligibilityMasterId()))
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
     * {@code PUT  /member-elligibilities/:claimElligibilityMasterId} : Updates an existing memberElligibility.
     *
     * @param claimElligibilityMasterId the id of the memberElligibilityDTO to save.
     * @param memberElligibilityDTO the memberElligibilityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated memberElligibilityDTO,
     * or with status {@code 400 (Bad Request)} if the memberElligibilityDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the memberElligibilityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/member-elligibilities/{claimElligibilityMasterId}")
    public Mono<ResponseEntity<MemberElligibilityDTO>> updateMemberElligibility(
        @PathVariable(value = "claimElligibilityMasterId", required = false) final Long claimElligibilityMasterId,
        @Valid @RequestBody MemberElligibilityDTO memberElligibilityDTO
    ) throws URISyntaxException {
        log.debug("REST request to update MemberElligibility : {}, {}", claimElligibilityMasterId, memberElligibilityDTO);
        if (memberElligibilityDTO.getClaimElligibilityMasterId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(claimElligibilityMasterId, memberElligibilityDTO.getClaimElligibilityMasterId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return memberElligibilityRepository
            .existsById(claimElligibilityMasterId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return memberElligibilityService
                    .update(memberElligibilityDTO)
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
     * {@code PATCH  /member-elligibilities/:claimElligibilityMasterId} : Partial updates given fields of an existing memberElligibility, field will ignore if it is null
     *
     * @param claimElligibilityMasterId the id of the memberElligibilityDTO to save.
     * @param memberElligibilityDTO the memberElligibilityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated memberElligibilityDTO,
     * or with status {@code 400 (Bad Request)} if the memberElligibilityDTO is not valid,
     * or with status {@code 404 (Not Found)} if the memberElligibilityDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the memberElligibilityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/member-elligibilities/{claimElligibilityMasterId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public Mono<ResponseEntity<MemberElligibilityDTO>> partialUpdateMemberElligibility(
        @PathVariable(value = "claimElligibilityMasterId", required = false) final Long claimElligibilityMasterId,
        @NotNull @RequestBody MemberElligibilityDTO memberElligibilityDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update MemberElligibility partially : {}, {}", claimElligibilityMasterId, memberElligibilityDTO);
        if (memberElligibilityDTO.getClaimElligibilityMasterId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(claimElligibilityMasterId, memberElligibilityDTO.getClaimElligibilityMasterId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return memberElligibilityRepository
            .existsById(claimElligibilityMasterId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<MemberElligibilityDTO> result = memberElligibilityService.partialUpdate(memberElligibilityDTO);

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
     * {@code GET  /member-elligibilities} : get all the memberElligibilities.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of memberElligibilities in body.
     */
    @GetMapping("/member-elligibilities")
    public Mono<ResponseEntity<List<MemberElligibilityDTO>>> getAllMemberElligibilities(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of MemberElligibilities");
        return memberElligibilityService
            .countAll()
            .zipWith(memberElligibilityService.findAll(pageable).collectList())
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
     * {@code GET  /member-elligibilities/:id} : get the "id" memberElligibility.
     *
     * @param id the id of the memberElligibilityDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the memberElligibilityDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/member-elligibilities/{id}")
    public Mono<ResponseEntity<MemberElligibilityDTO>> getMemberElligibility(@PathVariable Long id) {
        log.debug("REST request to get MemberElligibility : {}", id);
        Mono<MemberElligibilityDTO> memberElligibilityDTO = memberElligibilityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(memberElligibilityDTO);
    }

    /**
     * {@code DELETE  /member-elligibilities/:id} : delete the "id" memberElligibility.
     *
     * @param id the id of the memberElligibilityDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/member-elligibilities/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteMemberElligibility(@PathVariable Long id) {
        log.debug("REST request to delete MemberElligibility : {}", id);
        return memberElligibilityService
            .delete(id)
            .map(result ->
                ResponseEntity
                    .noContent()
                    .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                    .build()
            );
    }
}
