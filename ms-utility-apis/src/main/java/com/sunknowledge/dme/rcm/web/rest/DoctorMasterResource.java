package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.DoctorMasterRepository;
import com.sunknowledge.dme.rcm.service.DoctorMasterService;
import com.sunknowledge.dme.rcm.service.dto.DoctorMasterDTO;
import com.sunknowledge.dme.rcm.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.DoctorMaster}.
 */
@RestController
@RequestMapping("/api")
public class DoctorMasterResource {

    private final Logger log = LoggerFactory.getLogger(DoctorMasterResource.class);

    private static final String ENTITY_NAME = "utilityapisDoctorMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DoctorMasterService doctorMasterService;

    private final DoctorMasterRepository doctorMasterRepository;

    public DoctorMasterResource(DoctorMasterService doctorMasterService, DoctorMasterRepository doctorMasterRepository) {
        this.doctorMasterService = doctorMasterService;
        this.doctorMasterRepository = doctorMasterRepository;
    }

    /**
     * {@code POST  /doctor-masters} : Create a new doctorMaster.
     *
     * @param doctorMasterDTO the doctorMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new doctorMasterDTO, or with status {@code 400 (Bad Request)} if the doctorMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/doctor-masters")
    public ResponseEntity<DoctorMasterDTO> createDoctorMaster(@Valid @RequestBody DoctorMasterDTO doctorMasterDTO)
        throws URISyntaxException {
        log.debug("REST request to save DoctorMaster : {}", doctorMasterDTO);
        if (doctorMasterDTO.getDoctorId() != null) {
            throw new BadRequestAlertException("A new doctorMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DoctorMasterDTO result = doctorMasterService.save(doctorMasterDTO);
        return ResponseEntity
            .created(new URI("/api/doctor-masters/" + result.getDoctorId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getDoctorId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /doctor-masters/:doctorId} : Updates an existing doctorMaster.
     *
     * @param doctorId the id of the doctorMasterDTO to save.
     * @param doctorMasterDTO the doctorMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated doctorMasterDTO,
     * or with status {@code 400 (Bad Request)} if the doctorMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the doctorMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/doctor-masters/{doctorId}")
    public ResponseEntity<DoctorMasterDTO> updateDoctorMaster(
        @PathVariable(value = "doctorId", required = false) final Long doctorId,
        @Valid @RequestBody DoctorMasterDTO doctorMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DoctorMaster : {}, {}", doctorId, doctorMasterDTO);
        if (doctorMasterDTO.getDoctorId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(doctorId, doctorMasterDTO.getDoctorId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!doctorMasterRepository.existsById(doctorId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DoctorMasterDTO result = doctorMasterService.update(doctorMasterDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, doctorMasterDTO.getDoctorId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /doctor-masters/:doctorId} : Partial updates given fields of an existing doctorMaster, field will ignore if it is null
     *
     * @param doctorId the id of the doctorMasterDTO to save.
     * @param doctorMasterDTO the doctorMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated doctorMasterDTO,
     * or with status {@code 400 (Bad Request)} if the doctorMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the doctorMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the doctorMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/doctor-masters/{doctorId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DoctorMasterDTO> partialUpdateDoctorMaster(
        @PathVariable(value = "doctorId", required = false) final Long doctorId,
        @NotNull @RequestBody DoctorMasterDTO doctorMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DoctorMaster partially : {}, {}", doctorId, doctorMasterDTO);
        if (doctorMasterDTO.getDoctorId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(doctorId, doctorMasterDTO.getDoctorId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!doctorMasterRepository.existsById(doctorId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DoctorMasterDTO> result = doctorMasterService.partialUpdate(doctorMasterDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, doctorMasterDTO.getDoctorId().toString())
        );
    }

    /**
     * {@code GET  /doctor-masters} : get all the doctorMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of doctorMasters in body.
     */
    @GetMapping("/doctor-masters")
    public ResponseEntity<List<DoctorMasterDTO>> getAllDoctorMasters(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of DoctorMasters");
        Page<DoctorMasterDTO> page = doctorMasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /doctor-masters/:id} : get the "id" doctorMaster.
     *
     * @param id the id of the doctorMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the doctorMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/doctor-masters/{id}")
    public ResponseEntity<DoctorMasterDTO> getDoctorMaster(@PathVariable Long id) {
        log.debug("REST request to get DoctorMaster : {}", id);
        Optional<DoctorMasterDTO> doctorMasterDTO = doctorMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(doctorMasterDTO);
    }

    /**
     * {@code DELETE  /doctor-masters/:id} : delete the "id" doctorMaster.
     *
     * @param id the id of the doctorMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/doctor-masters/{id}")
    public ResponseEntity<Void> deleteDoctorMaster(@PathVariable Long id) {
        log.debug("REST request to delete DoctorMaster : {}", id);
        doctorMasterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
