package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.DoctorMedicalInfoRepository;
import com.sunknowledge.dme.rcm.service.DoctorMedicalInfoService;
import com.sunknowledge.dme.rcm.service.dto.DoctorMedicalInfoDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.DoctorMedicalInfo}.
 */
@RestController
@RequestMapping("/api")
public class DoctorMedicalInfoResource {

    private final Logger log = LoggerFactory.getLogger(DoctorMedicalInfoResource.class);

    private static final String ENTITY_NAME = "utilityapisDoctorMedicalInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DoctorMedicalInfoService doctorMedicalInfoService;

    private final DoctorMedicalInfoRepository doctorMedicalInfoRepository;

    public DoctorMedicalInfoResource(
        DoctorMedicalInfoService doctorMedicalInfoService,
        DoctorMedicalInfoRepository doctorMedicalInfoRepository
    ) {
        this.doctorMedicalInfoService = doctorMedicalInfoService;
        this.doctorMedicalInfoRepository = doctorMedicalInfoRepository;
    }

    /**
     * {@code POST  /doctor-medical-infos} : Create a new doctorMedicalInfo.
     *
     * @param doctorMedicalInfoDTO the doctorMedicalInfoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new doctorMedicalInfoDTO, or with status {@code 400 (Bad Request)} if the doctorMedicalInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/doctor-medical-infos")
    public ResponseEntity<DoctorMedicalInfoDTO> createDoctorMedicalInfo(@Valid @RequestBody DoctorMedicalInfoDTO doctorMedicalInfoDTO)
        throws URISyntaxException {
        log.debug("REST request to save DoctorMedicalInfo : {}", doctorMedicalInfoDTO);
        if (doctorMedicalInfoDTO.getMedicalInfoId() != null) {
            throw new BadRequestAlertException("A new doctorMedicalInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DoctorMedicalInfoDTO result = doctorMedicalInfoService.save(doctorMedicalInfoDTO);
        return ResponseEntity
            .created(new URI("/api/doctor-medical-infos/" + result.getMedicalInfoId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getMedicalInfoId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /doctor-medical-infos/:medicalInfoId} : Updates an existing doctorMedicalInfo.
     *
     * @param medicalInfoId the id of the doctorMedicalInfoDTO to save.
     * @param doctorMedicalInfoDTO the doctorMedicalInfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated doctorMedicalInfoDTO,
     * or with status {@code 400 (Bad Request)} if the doctorMedicalInfoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the doctorMedicalInfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/doctor-medical-infos/{medicalInfoId}")
    public ResponseEntity<DoctorMedicalInfoDTO> updateDoctorMedicalInfo(
        @PathVariable(value = "medicalInfoId", required = false) final Long medicalInfoId,
        @Valid @RequestBody DoctorMedicalInfoDTO doctorMedicalInfoDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DoctorMedicalInfo : {}, {}", medicalInfoId, doctorMedicalInfoDTO);
        if (doctorMedicalInfoDTO.getMedicalInfoId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(medicalInfoId, doctorMedicalInfoDTO.getMedicalInfoId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!doctorMedicalInfoRepository.existsById(medicalInfoId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DoctorMedicalInfoDTO result = doctorMedicalInfoService.update(doctorMedicalInfoDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, doctorMedicalInfoDTO.getMedicalInfoId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /doctor-medical-infos/:medicalInfoId} : Partial updates given fields of an existing doctorMedicalInfo, field will ignore if it is null
     *
     * @param medicalInfoId the id of the doctorMedicalInfoDTO to save.
     * @param doctorMedicalInfoDTO the doctorMedicalInfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated doctorMedicalInfoDTO,
     * or with status {@code 400 (Bad Request)} if the doctorMedicalInfoDTO is not valid,
     * or with status {@code 404 (Not Found)} if the doctorMedicalInfoDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the doctorMedicalInfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/doctor-medical-infos/{medicalInfoId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DoctorMedicalInfoDTO> partialUpdateDoctorMedicalInfo(
        @PathVariable(value = "medicalInfoId", required = false) final Long medicalInfoId,
        @NotNull @RequestBody DoctorMedicalInfoDTO doctorMedicalInfoDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DoctorMedicalInfo partially : {}, {}", medicalInfoId, doctorMedicalInfoDTO);
        if (doctorMedicalInfoDTO.getMedicalInfoId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(medicalInfoId, doctorMedicalInfoDTO.getMedicalInfoId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!doctorMedicalInfoRepository.existsById(medicalInfoId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DoctorMedicalInfoDTO> result = doctorMedicalInfoService.partialUpdate(doctorMedicalInfoDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, doctorMedicalInfoDTO.getMedicalInfoId().toString())
        );
    }

    /**
     * {@code GET  /doctor-medical-infos} : get all the doctorMedicalInfos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of doctorMedicalInfos in body.
     */
    @GetMapping("/doctor-medical-infos")
    public ResponseEntity<List<DoctorMedicalInfoDTO>> getAllDoctorMedicalInfos(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of DoctorMedicalInfos");
        Page<DoctorMedicalInfoDTO> page = doctorMedicalInfoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /doctor-medical-infos/:id} : get the "id" doctorMedicalInfo.
     *
     * @param id the id of the doctorMedicalInfoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the doctorMedicalInfoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/doctor-medical-infos/{id}")
    public ResponseEntity<DoctorMedicalInfoDTO> getDoctorMedicalInfo(@PathVariable Long id) {
        log.debug("REST request to get DoctorMedicalInfo : {}", id);
        Optional<DoctorMedicalInfoDTO> doctorMedicalInfoDTO = doctorMedicalInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(doctorMedicalInfoDTO);
    }

    /**
     * {@code DELETE  /doctor-medical-infos/:id} : delete the "id" doctorMedicalInfo.
     *
     * @param id the id of the doctorMedicalInfoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/doctor-medical-infos/{id}")
    public ResponseEntity<Void> deleteDoctorMedicalInfo(@PathVariable Long id) {
        log.debug("REST request to delete DoctorMedicalInfo : {}", id);
        doctorMedicalInfoService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
