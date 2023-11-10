package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.UserMasterRepository;
import com.sunknowledge.dme.rcm.service.UserMasterService;
import com.sunknowledge.dme.rcm.service.dto.UserMasterDTO;
import com.sunknowledge.dme.rcm.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.UserMaster}.
 */
@RestController
@RequestMapping("/api")
public class UserMasterResource {

    private final Logger log = LoggerFactory.getLogger(UserMasterResource.class);

    private static final String ENTITY_NAME = "settingsUserMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserMasterService userMasterService;

    private final UserMasterRepository userMasterRepository;

    public UserMasterResource(UserMasterService userMasterService, UserMasterRepository userMasterRepository) {
        this.userMasterService = userMasterService;
        this.userMasterRepository = userMasterRepository;
    }

    /**
     * {@code POST  /user-masters} : Create a new userMaster.
     *
     * @param userMasterDTO the userMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userMasterDTO, or with status {@code 400 (Bad Request)} if the userMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-masters")
    public ResponseEntity<UserMasterDTO> createUserMaster(@RequestBody UserMasterDTO userMasterDTO) throws URISyntaxException {
        log.debug("REST request to save UserMaster : {}", userMasterDTO);
        if (userMasterDTO.getUserId() != null) {
            throw new BadRequestAlertException("A new userMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserMasterDTO result = userMasterService.save(userMasterDTO);
        return ResponseEntity
            .created(new URI("/api/user-masters/" + result.getUserId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getUserId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-masters/:userId} : Updates an existing userMaster.
     *
     * @param userId the id of the userMasterDTO to save.
     * @param userMasterDTO the userMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userMasterDTO,
     * or with status {@code 400 (Bad Request)} if the userMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-masters/{userId}")
    public ResponseEntity<UserMasterDTO> updateUserMaster(
        @PathVariable(value = "userId", required = false) final Long userId,
        @RequestBody UserMasterDTO userMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update UserMaster : {}, {}", userId, userMasterDTO);
        if (userMasterDTO.getUserId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(userId, userMasterDTO.getUserId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!userMasterRepository.existsById(userId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        UserMasterDTO result = userMasterService.update(userMasterDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, userMasterDTO.getUserId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /user-masters/:userId} : Partial updates given fields of an existing userMaster, field will ignore if it is null
     *
     * @param userId the id of the userMasterDTO to save.
     * @param userMasterDTO the userMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userMasterDTO,
     * or with status {@code 400 (Bad Request)} if the userMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the userMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the userMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/user-masters/{userId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<UserMasterDTO> partialUpdateUserMaster(
        @PathVariable(value = "userId", required = false) final Long userId,
        @RequestBody UserMasterDTO userMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update UserMaster partially : {}, {}", userId, userMasterDTO);
        if (userMasterDTO.getUserId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(userId, userMasterDTO.getUserId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!userMasterRepository.existsById(userId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<UserMasterDTO> result = userMasterService.partialUpdate(userMasterDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, userMasterDTO.getUserId().toString())
        );
    }

    /**
     * {@code GET  /user-masters} : get all the userMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userMasters in body.
     */
    @GetMapping("/user-masters")
    public ResponseEntity<List<UserMasterDTO>> getAllUserMasters(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of UserMasters");
        Page<UserMasterDTO> page = userMasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /user-masters/:id} : get the "id" userMaster.
     *
     * @param id the id of the userMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-masters/{id}")
    public ResponseEntity<UserMasterDTO> getUserMaster(@PathVariable Long id) {
        log.debug("REST request to get UserMaster : {}", id);
        Optional<UserMasterDTO> userMasterDTO = userMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userMasterDTO);
    }

    /**
     * {@code DELETE  /user-masters/:id} : delete the "id" userMaster.
     *
     * @param id the id of the userMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-masters/{id}")
    public ResponseEntity<Void> deleteUserMaster(@PathVariable Long id) {
        log.debug("REST request to delete UserMaster : {}", id);
        userMasterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
