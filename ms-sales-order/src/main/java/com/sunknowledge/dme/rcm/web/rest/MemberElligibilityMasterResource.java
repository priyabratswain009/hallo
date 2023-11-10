package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.domain.MemberElligibilityMaster;
import com.sunknowledge.dme.rcm.repository.MemberElligibilityMasterRepository;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.MemberElligibilityMaster}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class MemberElligibilityMasterResource {

    private final Logger log = LoggerFactory.getLogger(MemberElligibilityMasterResource.class);

    private static final String ENTITY_NAME = "salesorderMemberElligibilityMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MemberElligibilityMasterRepository memberElligibilityMasterRepository;

    public MemberElligibilityMasterResource(MemberElligibilityMasterRepository memberElligibilityMasterRepository) {
        this.memberElligibilityMasterRepository = memberElligibilityMasterRepository;
    }

    /**
     * {@code POST  /member-elligibility-masters} : Create a new memberElligibilityMaster.
     *
     * @param memberElligibilityMaster the memberElligibilityMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new memberElligibilityMaster, or with status {@code 400 (Bad Request)} if the memberElligibilityMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/member-elligibility-masters")
    public Mono<ResponseEntity<MemberElligibilityMaster>> createMemberElligibilityMaster(
        @RequestBody MemberElligibilityMaster memberElligibilityMaster
    ) throws URISyntaxException {
        log.debug("REST request to save MemberElligibilityMaster : {}", memberElligibilityMaster);
        if (memberElligibilityMaster.getClaimElligibilityMasterId() != null) {
            throw new BadRequestAlertException("A new memberElligibilityMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return memberElligibilityMasterRepository
            .save(memberElligibilityMaster)
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
     * @param claimElligibilityMasterId the id of the memberElligibilityMaster to save.
     * @param memberElligibilityMaster the memberElligibilityMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated memberElligibilityMaster,
     * or with status {@code 400 (Bad Request)} if the memberElligibilityMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the memberElligibilityMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/member-elligibility-masters/{claimElligibilityMasterId}")
    public Mono<ResponseEntity<MemberElligibilityMaster>> updateMemberElligibilityMaster(
        @PathVariable(value = "claimElligibilityMasterId", required = false) final Long claimElligibilityMasterId,
        @RequestBody MemberElligibilityMaster memberElligibilityMaster
    ) throws URISyntaxException {
        log.debug("REST request to update MemberElligibilityMaster : {}, {}", claimElligibilityMasterId, memberElligibilityMaster);
        if (memberElligibilityMaster.getClaimElligibilityMasterId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(claimElligibilityMasterId, memberElligibilityMaster.getClaimElligibilityMasterId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return memberElligibilityMasterRepository
            .existsById(claimElligibilityMasterId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return memberElligibilityMasterRepository
                    .save(memberElligibilityMaster)
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
     * @param claimElligibilityMasterId the id of the memberElligibilityMaster to save.
     * @param memberElligibilityMaster the memberElligibilityMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated memberElligibilityMaster,
     * or with status {@code 400 (Bad Request)} if the memberElligibilityMaster is not valid,
     * or with status {@code 404 (Not Found)} if the memberElligibilityMaster is not found,
     * or with status {@code 500 (Internal Server Error)} if the memberElligibilityMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/member-elligibility-masters/{claimElligibilityMasterId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public Mono<ResponseEntity<MemberElligibilityMaster>> partialUpdateMemberElligibilityMaster(
        @PathVariable(value = "claimElligibilityMasterId", required = false) final Long claimElligibilityMasterId,
        @RequestBody MemberElligibilityMaster memberElligibilityMaster
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update MemberElligibilityMaster partially : {}, {}",
            claimElligibilityMasterId,
            memberElligibilityMaster
        );
        if (memberElligibilityMaster.getClaimElligibilityMasterId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(claimElligibilityMasterId, memberElligibilityMaster.getClaimElligibilityMasterId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return memberElligibilityMasterRepository
            .existsById(claimElligibilityMasterId)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<MemberElligibilityMaster> result = memberElligibilityMasterRepository
                    .findById(memberElligibilityMaster.getClaimElligibilityMasterId())
                    .map(existingMemberElligibilityMaster -> {
                        if (memberElligibilityMaster.getSalesOrderId() != null) {
                            existingMemberElligibilityMaster.setSalesOrderId(memberElligibilityMaster.getSalesOrderId());
                        }
                        if (memberElligibilityMaster.getElligibilityControlNumber() != null) {
                            existingMemberElligibilityMaster.setElligibilityControlNumber(
                                memberElligibilityMaster.getElligibilityControlNumber()
                            );
                        }
                        if (memberElligibilityMaster.getTradingPartnerServiceId() != null) {
                            existingMemberElligibilityMaster.setTradingPartnerServiceId(
                                memberElligibilityMaster.getTradingPartnerServiceId()
                            );
                        }
                        if (memberElligibilityMaster.getTradingPartnerName() != null) {
                            existingMemberElligibilityMaster.setTradingPartnerName(memberElligibilityMaster.getTradingPartnerName());
                        }
                        if (memberElligibilityMaster.getProviderOrganizationName() != null) {
                            existingMemberElligibilityMaster.setProviderOrganizationName(
                                memberElligibilityMaster.getProviderOrganizationName()
                            );
                        }
                        if (memberElligibilityMaster.getProviderNpi() != null) {
                            existingMemberElligibilityMaster.setProviderNpi(memberElligibilityMaster.getProviderNpi());
                        }
                        if (memberElligibilityMaster.getProviderType() != null) {
                            existingMemberElligibilityMaster.setProviderType(memberElligibilityMaster.getProviderType());
                        }
                        if (memberElligibilityMaster.getSubscriberFirstName() != null) {
                            existingMemberElligibilityMaster.setSubscriberFirstName(memberElligibilityMaster.getSubscriberFirstName());
                        }
                        if (memberElligibilityMaster.getSubscriberLastName() != null) {
                            existingMemberElligibilityMaster.setSubscriberLastName(memberElligibilityMaster.getSubscriberLastName());
                        }
                        if (memberElligibilityMaster.getSubscriberMemberId() != null) {
                            existingMemberElligibilityMaster.setSubscriberMemberId(memberElligibilityMaster.getSubscriberMemberId());
                        }
                        if (memberElligibilityMaster.getSubscriberIdcard() != null) {
                            existingMemberElligibilityMaster.setSubscriberIdcard(memberElligibilityMaster.getSubscriberIdcard());
                        }
                        if (memberElligibilityMaster.getSubscriberDob() != null) {
                            existingMemberElligibilityMaster.setSubscriberDob(memberElligibilityMaster.getSubscriberDob());
                        }
                        if (memberElligibilityMaster.getSubscriberGender() != null) {
                            existingMemberElligibilityMaster.setSubscriberGender(memberElligibilityMaster.getSubscriberGender());
                        }
                        if (memberElligibilityMaster.getSubscriberPlanIssueDate() != null) {
                            existingMemberElligibilityMaster.setSubscriberPlanIssueDate(
                                memberElligibilityMaster.getSubscriberPlanIssueDate()
                            );
                        }
                        if (memberElligibilityMaster.getInsuredFirstName() != null) {
                            existingMemberElligibilityMaster.setInsuredFirstName(memberElligibilityMaster.getInsuredFirstName());
                        }
                        if (memberElligibilityMaster.getInsuredLastName() != null) {
                            existingMemberElligibilityMaster.setInsuredLastName(memberElligibilityMaster.getInsuredLastName());
                        }
                        if (memberElligibilityMaster.getInsuredGender() != null) {
                            existingMemberElligibilityMaster.setInsuredGender(memberElligibilityMaster.getInsuredGender());
                        }
                        if (memberElligibilityMaster.getInsuredDob() != null) {
                            existingMemberElligibilityMaster.setInsuredDob(memberElligibilityMaster.getInsuredDob());
                        }
                        if (memberElligibilityMaster.getInsuredRelationshipwithSubscriber() != null) {
                            existingMemberElligibilityMaster.setInsuredRelationshipwithSubscriber(
                                memberElligibilityMaster.getInsuredRelationshipwithSubscriber()
                            );
                        }
                        if (memberElligibilityMaster.getDateOfService() != null) {
                            existingMemberElligibilityMaster.setDateOfService(memberElligibilityMaster.getDateOfService());
                        }
                        if (memberElligibilityMaster.getServiceTypeCodes() != null) {
                            existingMemberElligibilityMaster.setServiceTypeCodes(memberElligibilityMaster.getServiceTypeCodes());
                        }
                        if (memberElligibilityMaster.getStatus() != null) {
                            existingMemberElligibilityMaster.setStatus(memberElligibilityMaster.getStatus());
                        }
                        if (memberElligibilityMaster.getCreatedById() != null) {
                            existingMemberElligibilityMaster.setCreatedById(memberElligibilityMaster.getCreatedById());
                        }
                        if (memberElligibilityMaster.getCreatedByName() != null) {
                            existingMemberElligibilityMaster.setCreatedByName(memberElligibilityMaster.getCreatedByName());
                        }
                        if (memberElligibilityMaster.getCreatedDate() != null) {
                            existingMemberElligibilityMaster.setCreatedDate(memberElligibilityMaster.getCreatedDate());
                        }
                        if (memberElligibilityMaster.getUpdatedById() != null) {
                            existingMemberElligibilityMaster.setUpdatedById(memberElligibilityMaster.getUpdatedById());
                        }
                        if (memberElligibilityMaster.getUpdatedByName() != null) {
                            existingMemberElligibilityMaster.setUpdatedByName(memberElligibilityMaster.getUpdatedByName());
                        }
                        if (memberElligibilityMaster.getUpdatedDate() != null) {
                            existingMemberElligibilityMaster.setUpdatedDate(memberElligibilityMaster.getUpdatedDate());
                        }
                        if (memberElligibilityMaster.getMemberElligibilityMasterUuid() != null) {
                            existingMemberElligibilityMaster.setMemberElligibilityMasterUuid(
                                memberElligibilityMaster.getMemberElligibilityMasterUuid()
                            );
                        }

                        return existingMemberElligibilityMaster;
                    })
                    .flatMap(memberElligibilityMasterRepository::save);

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
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of memberElligibilityMasters in body.
     */
    @GetMapping("/member-elligibility-masters")
    public Mono<List<MemberElligibilityMaster>> getAllMemberElligibilityMasters() {
        log.debug("REST request to get all MemberElligibilityMasters");
        return memberElligibilityMasterRepository.findAll().collectList();
    }

    /**
     * {@code GET  /member-elligibility-masters} : get all the memberElligibilityMasters as a stream.
     * @return the {@link Flux} of memberElligibilityMasters.
     */
    @GetMapping(value = "/member-elligibility-masters", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<MemberElligibilityMaster> getAllMemberElligibilityMastersAsStream() {
        log.debug("REST request to get all MemberElligibilityMasters as a stream");
        return memberElligibilityMasterRepository.findAll();
    }

    /**
     * {@code GET  /member-elligibility-masters/:id} : get the "id" memberElligibilityMaster.
     *
     * @param id the id of the memberElligibilityMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the memberElligibilityMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/member-elligibility-masters/{id}")
    public Mono<ResponseEntity<MemberElligibilityMaster>> getMemberElligibilityMaster(@PathVariable Long id) {
        log.debug("REST request to get MemberElligibilityMaster : {}", id);
        Mono<MemberElligibilityMaster> memberElligibilityMaster = memberElligibilityMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(memberElligibilityMaster);
    }

    /**
     * {@code DELETE  /member-elligibility-masters/:id} : delete the "id" memberElligibilityMaster.
     *
     * @param id the id of the memberElligibilityMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/member-elligibility-masters/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteMemberElligibilityMaster(@PathVariable Long id) {
        log.debug("REST request to delete MemberElligibilityMaster : {}", id);
        return memberElligibilityMasterRepository
            .deleteById(id)
            .map(result ->
                ResponseEntity
                    .noContent()
                    .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                    .build()
            );
    }
}
