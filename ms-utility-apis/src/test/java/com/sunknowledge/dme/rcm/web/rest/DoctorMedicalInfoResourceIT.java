package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.DoctorMedicalInfo;
import com.sunknowledge.dme.rcm.repository.DoctorMedicalInfoRepository;
import com.sunknowledge.dme.rcm.service.dto.DoctorMedicalInfoDTO;
import com.sunknowledge.dme.rcm.service.mapper.DoctorMedicalInfoMapper;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link DoctorMedicalInfoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DoctorMedicalInfoResourceIT {

    private static final Long DEFAULT_DOCTOR_ID = 1L;
    private static final Long UPDATED_DOCTOR_ID = 2L;

    private static final String DEFAULT_LICENCE_NO = "AAAAAAAAAA";
    private static final String UPDATED_LICENCE_NO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_LIC_EXPERIATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LIC_EXPERIATION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DEA = "AAAAAAAAAA";
    private static final String UPDATED_DEA = "BBBBBBBBBB";

    private static final String DEFAULT_UPIN = "AAAAAAAAAA";
    private static final String UPDATED_UPIN = "BBBBBBBBBB";

    private static final String DEFAULT_STATE_MEDICAID_ID = "AAAAAAAAAA";
    private static final String UPDATED_STATE_MEDICAID_ID = "BBBBBBBBBB";

    private static final String DEFAULT_NPI = "AAAAAAAAAA";
    private static final String UPDATED_NPI = "BBBBBBBBBB";

    private static final String DEFAULT_COMMERICIAL_NO = "AAAAAAAAAA";
    private static final String UPDATED_COMMERICIAL_NO = "BBBBBBBBBB";

    private static final Long DEFAULT_LOCATION_ID = 1L;
    private static final Long UPDATED_LOCATION_ID = 2L;

    private static final String DEFAULT_TAXONOMY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TAXONOMY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PECOS_CERTIFIED_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_PECOS_CERTIFIED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_USER_1 = "AAAAAAAAAA";
    private static final String UPDATED_USER_1 = "BBBBBBBBBB";

    private static final String DEFAULT_USER_2 = "AAAAAAAAAA";
    private static final String UPDATED_USER_2 = "BBBBBBBBBB";

    private static final String DEFAULT_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_NOTES = "BBBBBBBBBB";

    private static final String DEFAULT_TAXONOMY_GROUP = "AAAAAAAAAA";
    private static final String UPDATED_TAXONOMY_GROUP = "BBBBBBBBBB";

    private static final String DEFAULT_TAXONOMY_DESC = "AAAAAAAAAA";
    private static final String UPDATED_TAXONOMY_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_PRACTICE_STATE = "AAAAAAAAAA";
    private static final String UPDATED_PRACTICE_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_TAXONOMY = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_TAXONOMY = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final UUID DEFAULT_DOCTOR_MEDICAL_INFO_UUID = UUID.randomUUID();
    private static final UUID UPDATED_DOCTOR_MEDICAL_INFO_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/doctor-medical-infos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{medicalInfoId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DoctorMedicalInfoRepository doctorMedicalInfoRepository;

    @Autowired
    private DoctorMedicalInfoMapper doctorMedicalInfoMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDoctorMedicalInfoMockMvc;

    private DoctorMedicalInfo doctorMedicalInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DoctorMedicalInfo createEntity(EntityManager em) {
        DoctorMedicalInfo doctorMedicalInfo = new DoctorMedicalInfo()
            .doctorId(DEFAULT_DOCTOR_ID)
            .licenceNo(DEFAULT_LICENCE_NO)
            .licExperiationDate(DEFAULT_LIC_EXPERIATION_DATE)
            .dea(DEFAULT_DEA)
            .upin(DEFAULT_UPIN)
            .stateMedicaidId(DEFAULT_STATE_MEDICAID_ID)
            .npi(DEFAULT_NPI)
            .commericialNo(DEFAULT_COMMERICIAL_NO)
            .locationId(DEFAULT_LOCATION_ID)
            .taxonomyCode(DEFAULT_TAXONOMY_CODE)
            .pecosCertifiedStatus(DEFAULT_PECOS_CERTIFIED_STATUS)
            .user1(DEFAULT_USER_1)
            .user2(DEFAULT_USER_2)
            .notes(DEFAULT_NOTES)
            .taxonomyGroup(DEFAULT_TAXONOMY_GROUP)
            .taxonomyDesc(DEFAULT_TAXONOMY_DESC)
            .practiceState(DEFAULT_PRACTICE_STATE)
            .primaryTaxonomy(DEFAULT_PRIMARY_TAXONOMY)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .doctorMedicalInfoUuid(DEFAULT_DOCTOR_MEDICAL_INFO_UUID);
        return doctorMedicalInfo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DoctorMedicalInfo createUpdatedEntity(EntityManager em) {
        DoctorMedicalInfo doctorMedicalInfo = new DoctorMedicalInfo()
            .doctorId(UPDATED_DOCTOR_ID)
            .licenceNo(UPDATED_LICENCE_NO)
            .licExperiationDate(UPDATED_LIC_EXPERIATION_DATE)
            .dea(UPDATED_DEA)
            .upin(UPDATED_UPIN)
            .stateMedicaidId(UPDATED_STATE_MEDICAID_ID)
            .npi(UPDATED_NPI)
            .commericialNo(UPDATED_COMMERICIAL_NO)
            .locationId(UPDATED_LOCATION_ID)
            .taxonomyCode(UPDATED_TAXONOMY_CODE)
            .pecosCertifiedStatus(UPDATED_PECOS_CERTIFIED_STATUS)
            .user1(UPDATED_USER_1)
            .user2(UPDATED_USER_2)
            .notes(UPDATED_NOTES)
            .taxonomyGroup(UPDATED_TAXONOMY_GROUP)
            .taxonomyDesc(UPDATED_TAXONOMY_DESC)
            .practiceState(UPDATED_PRACTICE_STATE)
            .primaryTaxonomy(UPDATED_PRIMARY_TAXONOMY)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .doctorMedicalInfoUuid(UPDATED_DOCTOR_MEDICAL_INFO_UUID);
        return doctorMedicalInfo;
    }

    @BeforeEach
    public void initTest() {
        doctorMedicalInfo = createEntity(em);
    }

    @Test
    @Transactional
    void createDoctorMedicalInfo() throws Exception {
        int databaseSizeBeforeCreate = doctorMedicalInfoRepository.findAll().size();
        // Create the DoctorMedicalInfo
        DoctorMedicalInfoDTO doctorMedicalInfoDTO = doctorMedicalInfoMapper.toDto(doctorMedicalInfo);
        restDoctorMedicalInfoMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(doctorMedicalInfoDTO))
            )
            .andExpect(status().isCreated());

        // Validate the DoctorMedicalInfo in the database
        List<DoctorMedicalInfo> doctorMedicalInfoList = doctorMedicalInfoRepository.findAll();
        assertThat(doctorMedicalInfoList).hasSize(databaseSizeBeforeCreate + 1);
        DoctorMedicalInfo testDoctorMedicalInfo = doctorMedicalInfoList.get(doctorMedicalInfoList.size() - 1);
        assertThat(testDoctorMedicalInfo.getDoctorId()).isEqualTo(DEFAULT_DOCTOR_ID);
        assertThat(testDoctorMedicalInfo.getLicenceNo()).isEqualTo(DEFAULT_LICENCE_NO);
        assertThat(testDoctorMedicalInfo.getLicExperiationDate()).isEqualTo(DEFAULT_LIC_EXPERIATION_DATE);
        assertThat(testDoctorMedicalInfo.getDea()).isEqualTo(DEFAULT_DEA);
        assertThat(testDoctorMedicalInfo.getUpin()).isEqualTo(DEFAULT_UPIN);
        assertThat(testDoctorMedicalInfo.getStateMedicaidId()).isEqualTo(DEFAULT_STATE_MEDICAID_ID);
        assertThat(testDoctorMedicalInfo.getNpi()).isEqualTo(DEFAULT_NPI);
        assertThat(testDoctorMedicalInfo.getCommericialNo()).isEqualTo(DEFAULT_COMMERICIAL_NO);
        assertThat(testDoctorMedicalInfo.getLocationId()).isEqualTo(DEFAULT_LOCATION_ID);
        assertThat(testDoctorMedicalInfo.getTaxonomyCode()).isEqualTo(DEFAULT_TAXONOMY_CODE);
        assertThat(testDoctorMedicalInfo.getPecosCertifiedStatus()).isEqualTo(DEFAULT_PECOS_CERTIFIED_STATUS);
        assertThat(testDoctorMedicalInfo.getUser1()).isEqualTo(DEFAULT_USER_1);
        assertThat(testDoctorMedicalInfo.getUser2()).isEqualTo(DEFAULT_USER_2);
        assertThat(testDoctorMedicalInfo.getNotes()).isEqualTo(DEFAULT_NOTES);
        assertThat(testDoctorMedicalInfo.getTaxonomyGroup()).isEqualTo(DEFAULT_TAXONOMY_GROUP);
        assertThat(testDoctorMedicalInfo.getTaxonomyDesc()).isEqualTo(DEFAULT_TAXONOMY_DESC);
        assertThat(testDoctorMedicalInfo.getPracticeState()).isEqualTo(DEFAULT_PRACTICE_STATE);
        assertThat(testDoctorMedicalInfo.getPrimaryTaxonomy()).isEqualTo(DEFAULT_PRIMARY_TAXONOMY);
        assertThat(testDoctorMedicalInfo.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testDoctorMedicalInfo.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testDoctorMedicalInfo.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testDoctorMedicalInfo.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testDoctorMedicalInfo.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testDoctorMedicalInfo.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testDoctorMedicalInfo.getDoctorMedicalInfoUuid()).isEqualTo(DEFAULT_DOCTOR_MEDICAL_INFO_UUID);
    }

    @Test
    @Transactional
    void createDoctorMedicalInfoWithExistingId() throws Exception {
        // Create the DoctorMedicalInfo with an existing ID
        doctorMedicalInfo.setMedicalInfoId(1L);
        DoctorMedicalInfoDTO doctorMedicalInfoDTO = doctorMedicalInfoMapper.toDto(doctorMedicalInfo);

        int databaseSizeBeforeCreate = doctorMedicalInfoRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDoctorMedicalInfoMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(doctorMedicalInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DoctorMedicalInfo in the database
        List<DoctorMedicalInfo> doctorMedicalInfoList = doctorMedicalInfoRepository.findAll();
        assertThat(doctorMedicalInfoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDoctorMedicalInfos() throws Exception {
        // Initialize the database
        doctorMedicalInfoRepository.saveAndFlush(doctorMedicalInfo);

        // Get all the doctorMedicalInfoList
        restDoctorMedicalInfoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=medicalInfoId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].medicalInfoId").value(hasItem(doctorMedicalInfo.getMedicalInfoId().intValue())))
            .andExpect(jsonPath("$.[*].doctorId").value(hasItem(DEFAULT_DOCTOR_ID.intValue())))
            .andExpect(jsonPath("$.[*].licenceNo").value(hasItem(DEFAULT_LICENCE_NO)))
            .andExpect(jsonPath("$.[*].licExperiationDate").value(hasItem(DEFAULT_LIC_EXPERIATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].dea").value(hasItem(DEFAULT_DEA)))
            .andExpect(jsonPath("$.[*].upin").value(hasItem(DEFAULT_UPIN)))
            .andExpect(jsonPath("$.[*].stateMedicaidId").value(hasItem(DEFAULT_STATE_MEDICAID_ID)))
            .andExpect(jsonPath("$.[*].npi").value(hasItem(DEFAULT_NPI)))
            .andExpect(jsonPath("$.[*].commericialNo").value(hasItem(DEFAULT_COMMERICIAL_NO)))
            .andExpect(jsonPath("$.[*].locationId").value(hasItem(DEFAULT_LOCATION_ID.intValue())))
            .andExpect(jsonPath("$.[*].taxonomyCode").value(hasItem(DEFAULT_TAXONOMY_CODE)))
            .andExpect(jsonPath("$.[*].pecosCertifiedStatus").value(hasItem(DEFAULT_PECOS_CERTIFIED_STATUS)))
            .andExpect(jsonPath("$.[*].user1").value(hasItem(DEFAULT_USER_1)))
            .andExpect(jsonPath("$.[*].user2").value(hasItem(DEFAULT_USER_2)))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES)))
            .andExpect(jsonPath("$.[*].taxonomyGroup").value(hasItem(DEFAULT_TAXONOMY_GROUP)))
            .andExpect(jsonPath("$.[*].taxonomyDesc").value(hasItem(DEFAULT_TAXONOMY_DESC)))
            .andExpect(jsonPath("$.[*].practiceState").value(hasItem(DEFAULT_PRACTICE_STATE)))
            .andExpect(jsonPath("$.[*].primaryTaxonomy").value(hasItem(DEFAULT_PRIMARY_TAXONOMY)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].doctorMedicalInfoUuid").value(hasItem(DEFAULT_DOCTOR_MEDICAL_INFO_UUID.toString())));
    }

    @Test
    @Transactional
    void getDoctorMedicalInfo() throws Exception {
        // Initialize the database
        doctorMedicalInfoRepository.saveAndFlush(doctorMedicalInfo);

        // Get the doctorMedicalInfo
        restDoctorMedicalInfoMockMvc
            .perform(get(ENTITY_API_URL_ID, doctorMedicalInfo.getMedicalInfoId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.medicalInfoId").value(doctorMedicalInfo.getMedicalInfoId().intValue()))
            .andExpect(jsonPath("$.doctorId").value(DEFAULT_DOCTOR_ID.intValue()))
            .andExpect(jsonPath("$.licenceNo").value(DEFAULT_LICENCE_NO))
            .andExpect(jsonPath("$.licExperiationDate").value(DEFAULT_LIC_EXPERIATION_DATE.toString()))
            .andExpect(jsonPath("$.dea").value(DEFAULT_DEA))
            .andExpect(jsonPath("$.upin").value(DEFAULT_UPIN))
            .andExpect(jsonPath("$.stateMedicaidId").value(DEFAULT_STATE_MEDICAID_ID))
            .andExpect(jsonPath("$.npi").value(DEFAULT_NPI))
            .andExpect(jsonPath("$.commericialNo").value(DEFAULT_COMMERICIAL_NO))
            .andExpect(jsonPath("$.locationId").value(DEFAULT_LOCATION_ID.intValue()))
            .andExpect(jsonPath("$.taxonomyCode").value(DEFAULT_TAXONOMY_CODE))
            .andExpect(jsonPath("$.pecosCertifiedStatus").value(DEFAULT_PECOS_CERTIFIED_STATUS))
            .andExpect(jsonPath("$.user1").value(DEFAULT_USER_1))
            .andExpect(jsonPath("$.user2").value(DEFAULT_USER_2))
            .andExpect(jsonPath("$.notes").value(DEFAULT_NOTES))
            .andExpect(jsonPath("$.taxonomyGroup").value(DEFAULT_TAXONOMY_GROUP))
            .andExpect(jsonPath("$.taxonomyDesc").value(DEFAULT_TAXONOMY_DESC))
            .andExpect(jsonPath("$.practiceState").value(DEFAULT_PRACTICE_STATE))
            .andExpect(jsonPath("$.primaryTaxonomy").value(DEFAULT_PRIMARY_TAXONOMY))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.doctorMedicalInfoUuid").value(DEFAULT_DOCTOR_MEDICAL_INFO_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingDoctorMedicalInfo() throws Exception {
        // Get the doctorMedicalInfo
        restDoctorMedicalInfoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewDoctorMedicalInfo() throws Exception {
        // Initialize the database
        doctorMedicalInfoRepository.saveAndFlush(doctorMedicalInfo);

        int databaseSizeBeforeUpdate = doctorMedicalInfoRepository.findAll().size();

        // Update the doctorMedicalInfo
        DoctorMedicalInfo updatedDoctorMedicalInfo = doctorMedicalInfoRepository.findById(doctorMedicalInfo.getMedicalInfoId()).get();
        // Disconnect from session so that the updates on updatedDoctorMedicalInfo are not directly saved in db
        em.detach(updatedDoctorMedicalInfo);
        updatedDoctorMedicalInfo
            .doctorId(UPDATED_DOCTOR_ID)
            .licenceNo(UPDATED_LICENCE_NO)
            .licExperiationDate(UPDATED_LIC_EXPERIATION_DATE)
            .dea(UPDATED_DEA)
            .upin(UPDATED_UPIN)
            .stateMedicaidId(UPDATED_STATE_MEDICAID_ID)
            .npi(UPDATED_NPI)
            .commericialNo(UPDATED_COMMERICIAL_NO)
            .locationId(UPDATED_LOCATION_ID)
            .taxonomyCode(UPDATED_TAXONOMY_CODE)
            .pecosCertifiedStatus(UPDATED_PECOS_CERTIFIED_STATUS)
            .user1(UPDATED_USER_1)
            .user2(UPDATED_USER_2)
            .notes(UPDATED_NOTES)
            .taxonomyGroup(UPDATED_TAXONOMY_GROUP)
            .taxonomyDesc(UPDATED_TAXONOMY_DESC)
            .practiceState(UPDATED_PRACTICE_STATE)
            .primaryTaxonomy(UPDATED_PRIMARY_TAXONOMY)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .doctorMedicalInfoUuid(UPDATED_DOCTOR_MEDICAL_INFO_UUID);
        DoctorMedicalInfoDTO doctorMedicalInfoDTO = doctorMedicalInfoMapper.toDto(updatedDoctorMedicalInfo);

        restDoctorMedicalInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, doctorMedicalInfoDTO.getMedicalInfoId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(doctorMedicalInfoDTO))
            )
            .andExpect(status().isOk());

        // Validate the DoctorMedicalInfo in the database
        List<DoctorMedicalInfo> doctorMedicalInfoList = doctorMedicalInfoRepository.findAll();
        assertThat(doctorMedicalInfoList).hasSize(databaseSizeBeforeUpdate);
        DoctorMedicalInfo testDoctorMedicalInfo = doctorMedicalInfoList.get(doctorMedicalInfoList.size() - 1);
        assertThat(testDoctorMedicalInfo.getDoctorId()).isEqualTo(UPDATED_DOCTOR_ID);
        assertThat(testDoctorMedicalInfo.getLicenceNo()).isEqualTo(UPDATED_LICENCE_NO);
        assertThat(testDoctorMedicalInfo.getLicExperiationDate()).isEqualTo(UPDATED_LIC_EXPERIATION_DATE);
        assertThat(testDoctorMedicalInfo.getDea()).isEqualTo(UPDATED_DEA);
        assertThat(testDoctorMedicalInfo.getUpin()).isEqualTo(UPDATED_UPIN);
        assertThat(testDoctorMedicalInfo.getStateMedicaidId()).isEqualTo(UPDATED_STATE_MEDICAID_ID);
        assertThat(testDoctorMedicalInfo.getNpi()).isEqualTo(UPDATED_NPI);
        assertThat(testDoctorMedicalInfo.getCommericialNo()).isEqualTo(UPDATED_COMMERICIAL_NO);
        assertThat(testDoctorMedicalInfo.getLocationId()).isEqualTo(UPDATED_LOCATION_ID);
        assertThat(testDoctorMedicalInfo.getTaxonomyCode()).isEqualTo(UPDATED_TAXONOMY_CODE);
        assertThat(testDoctorMedicalInfo.getPecosCertifiedStatus()).isEqualTo(UPDATED_PECOS_CERTIFIED_STATUS);
        assertThat(testDoctorMedicalInfo.getUser1()).isEqualTo(UPDATED_USER_1);
        assertThat(testDoctorMedicalInfo.getUser2()).isEqualTo(UPDATED_USER_2);
        assertThat(testDoctorMedicalInfo.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testDoctorMedicalInfo.getTaxonomyGroup()).isEqualTo(UPDATED_TAXONOMY_GROUP);
        assertThat(testDoctorMedicalInfo.getTaxonomyDesc()).isEqualTo(UPDATED_TAXONOMY_DESC);
        assertThat(testDoctorMedicalInfo.getPracticeState()).isEqualTo(UPDATED_PRACTICE_STATE);
        assertThat(testDoctorMedicalInfo.getPrimaryTaxonomy()).isEqualTo(UPDATED_PRIMARY_TAXONOMY);
        assertThat(testDoctorMedicalInfo.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testDoctorMedicalInfo.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testDoctorMedicalInfo.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testDoctorMedicalInfo.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testDoctorMedicalInfo.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testDoctorMedicalInfo.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testDoctorMedicalInfo.getDoctorMedicalInfoUuid()).isEqualTo(UPDATED_DOCTOR_MEDICAL_INFO_UUID);
    }

    @Test
    @Transactional
    void putNonExistingDoctorMedicalInfo() throws Exception {
        int databaseSizeBeforeUpdate = doctorMedicalInfoRepository.findAll().size();
        doctorMedicalInfo.setMedicalInfoId(count.incrementAndGet());

        // Create the DoctorMedicalInfo
        DoctorMedicalInfoDTO doctorMedicalInfoDTO = doctorMedicalInfoMapper.toDto(doctorMedicalInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDoctorMedicalInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, doctorMedicalInfoDTO.getMedicalInfoId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(doctorMedicalInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DoctorMedicalInfo in the database
        List<DoctorMedicalInfo> doctorMedicalInfoList = doctorMedicalInfoRepository.findAll();
        assertThat(doctorMedicalInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDoctorMedicalInfo() throws Exception {
        int databaseSizeBeforeUpdate = doctorMedicalInfoRepository.findAll().size();
        doctorMedicalInfo.setMedicalInfoId(count.incrementAndGet());

        // Create the DoctorMedicalInfo
        DoctorMedicalInfoDTO doctorMedicalInfoDTO = doctorMedicalInfoMapper.toDto(doctorMedicalInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDoctorMedicalInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(doctorMedicalInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DoctorMedicalInfo in the database
        List<DoctorMedicalInfo> doctorMedicalInfoList = doctorMedicalInfoRepository.findAll();
        assertThat(doctorMedicalInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDoctorMedicalInfo() throws Exception {
        int databaseSizeBeforeUpdate = doctorMedicalInfoRepository.findAll().size();
        doctorMedicalInfo.setMedicalInfoId(count.incrementAndGet());

        // Create the DoctorMedicalInfo
        DoctorMedicalInfoDTO doctorMedicalInfoDTO = doctorMedicalInfoMapper.toDto(doctorMedicalInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDoctorMedicalInfoMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(doctorMedicalInfoDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DoctorMedicalInfo in the database
        List<DoctorMedicalInfo> doctorMedicalInfoList = doctorMedicalInfoRepository.findAll();
        assertThat(doctorMedicalInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDoctorMedicalInfoWithPatch() throws Exception {
        // Initialize the database
        doctorMedicalInfoRepository.saveAndFlush(doctorMedicalInfo);

        int databaseSizeBeforeUpdate = doctorMedicalInfoRepository.findAll().size();

        // Update the doctorMedicalInfo using partial update
        DoctorMedicalInfo partialUpdatedDoctorMedicalInfo = new DoctorMedicalInfo();
        partialUpdatedDoctorMedicalInfo.setMedicalInfoId(doctorMedicalInfo.getMedicalInfoId());

        partialUpdatedDoctorMedicalInfo
            .doctorId(UPDATED_DOCTOR_ID)
            .licExperiationDate(UPDATED_LIC_EXPERIATION_DATE)
            .stateMedicaidId(UPDATED_STATE_MEDICAID_ID)
            .npi(UPDATED_NPI)
            .locationId(UPDATED_LOCATION_ID)
            .taxonomyCode(UPDATED_TAXONOMY_CODE)
            .practiceState(UPDATED_PRACTICE_STATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE);

        restDoctorMedicalInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDoctorMedicalInfo.getMedicalInfoId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDoctorMedicalInfo))
            )
            .andExpect(status().isOk());

        // Validate the DoctorMedicalInfo in the database
        List<DoctorMedicalInfo> doctorMedicalInfoList = doctorMedicalInfoRepository.findAll();
        assertThat(doctorMedicalInfoList).hasSize(databaseSizeBeforeUpdate);
        DoctorMedicalInfo testDoctorMedicalInfo = doctorMedicalInfoList.get(doctorMedicalInfoList.size() - 1);
        assertThat(testDoctorMedicalInfo.getDoctorId()).isEqualTo(UPDATED_DOCTOR_ID);
        assertThat(testDoctorMedicalInfo.getLicenceNo()).isEqualTo(DEFAULT_LICENCE_NO);
        assertThat(testDoctorMedicalInfo.getLicExperiationDate()).isEqualTo(UPDATED_LIC_EXPERIATION_DATE);
        assertThat(testDoctorMedicalInfo.getDea()).isEqualTo(DEFAULT_DEA);
        assertThat(testDoctorMedicalInfo.getUpin()).isEqualTo(DEFAULT_UPIN);
        assertThat(testDoctorMedicalInfo.getStateMedicaidId()).isEqualTo(UPDATED_STATE_MEDICAID_ID);
        assertThat(testDoctorMedicalInfo.getNpi()).isEqualTo(UPDATED_NPI);
        assertThat(testDoctorMedicalInfo.getCommericialNo()).isEqualTo(DEFAULT_COMMERICIAL_NO);
        assertThat(testDoctorMedicalInfo.getLocationId()).isEqualTo(UPDATED_LOCATION_ID);
        assertThat(testDoctorMedicalInfo.getTaxonomyCode()).isEqualTo(UPDATED_TAXONOMY_CODE);
        assertThat(testDoctorMedicalInfo.getPecosCertifiedStatus()).isEqualTo(DEFAULT_PECOS_CERTIFIED_STATUS);
        assertThat(testDoctorMedicalInfo.getUser1()).isEqualTo(DEFAULT_USER_1);
        assertThat(testDoctorMedicalInfo.getUser2()).isEqualTo(DEFAULT_USER_2);
        assertThat(testDoctorMedicalInfo.getNotes()).isEqualTo(DEFAULT_NOTES);
        assertThat(testDoctorMedicalInfo.getTaxonomyGroup()).isEqualTo(DEFAULT_TAXONOMY_GROUP);
        assertThat(testDoctorMedicalInfo.getTaxonomyDesc()).isEqualTo(DEFAULT_TAXONOMY_DESC);
        assertThat(testDoctorMedicalInfo.getPracticeState()).isEqualTo(UPDATED_PRACTICE_STATE);
        assertThat(testDoctorMedicalInfo.getPrimaryTaxonomy()).isEqualTo(DEFAULT_PRIMARY_TAXONOMY);
        assertThat(testDoctorMedicalInfo.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testDoctorMedicalInfo.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testDoctorMedicalInfo.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testDoctorMedicalInfo.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testDoctorMedicalInfo.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testDoctorMedicalInfo.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testDoctorMedicalInfo.getDoctorMedicalInfoUuid()).isEqualTo(DEFAULT_DOCTOR_MEDICAL_INFO_UUID);
    }

    @Test
    @Transactional
    void fullUpdateDoctorMedicalInfoWithPatch() throws Exception {
        // Initialize the database
        doctorMedicalInfoRepository.saveAndFlush(doctorMedicalInfo);

        int databaseSizeBeforeUpdate = doctorMedicalInfoRepository.findAll().size();

        // Update the doctorMedicalInfo using partial update
        DoctorMedicalInfo partialUpdatedDoctorMedicalInfo = new DoctorMedicalInfo();
        partialUpdatedDoctorMedicalInfo.setMedicalInfoId(doctorMedicalInfo.getMedicalInfoId());

        partialUpdatedDoctorMedicalInfo
            .doctorId(UPDATED_DOCTOR_ID)
            .licenceNo(UPDATED_LICENCE_NO)
            .licExperiationDate(UPDATED_LIC_EXPERIATION_DATE)
            .dea(UPDATED_DEA)
            .upin(UPDATED_UPIN)
            .stateMedicaidId(UPDATED_STATE_MEDICAID_ID)
            .npi(UPDATED_NPI)
            .commericialNo(UPDATED_COMMERICIAL_NO)
            .locationId(UPDATED_LOCATION_ID)
            .taxonomyCode(UPDATED_TAXONOMY_CODE)
            .pecosCertifiedStatus(UPDATED_PECOS_CERTIFIED_STATUS)
            .user1(UPDATED_USER_1)
            .user2(UPDATED_USER_2)
            .notes(UPDATED_NOTES)
            .taxonomyGroup(UPDATED_TAXONOMY_GROUP)
            .taxonomyDesc(UPDATED_TAXONOMY_DESC)
            .practiceState(UPDATED_PRACTICE_STATE)
            .primaryTaxonomy(UPDATED_PRIMARY_TAXONOMY)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .doctorMedicalInfoUuid(UPDATED_DOCTOR_MEDICAL_INFO_UUID);

        restDoctorMedicalInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDoctorMedicalInfo.getMedicalInfoId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDoctorMedicalInfo))
            )
            .andExpect(status().isOk());

        // Validate the DoctorMedicalInfo in the database
        List<DoctorMedicalInfo> doctorMedicalInfoList = doctorMedicalInfoRepository.findAll();
        assertThat(doctorMedicalInfoList).hasSize(databaseSizeBeforeUpdate);
        DoctorMedicalInfo testDoctorMedicalInfo = doctorMedicalInfoList.get(doctorMedicalInfoList.size() - 1);
        assertThat(testDoctorMedicalInfo.getDoctorId()).isEqualTo(UPDATED_DOCTOR_ID);
        assertThat(testDoctorMedicalInfo.getLicenceNo()).isEqualTo(UPDATED_LICENCE_NO);
        assertThat(testDoctorMedicalInfo.getLicExperiationDate()).isEqualTo(UPDATED_LIC_EXPERIATION_DATE);
        assertThat(testDoctorMedicalInfo.getDea()).isEqualTo(UPDATED_DEA);
        assertThat(testDoctorMedicalInfo.getUpin()).isEqualTo(UPDATED_UPIN);
        assertThat(testDoctorMedicalInfo.getStateMedicaidId()).isEqualTo(UPDATED_STATE_MEDICAID_ID);
        assertThat(testDoctorMedicalInfo.getNpi()).isEqualTo(UPDATED_NPI);
        assertThat(testDoctorMedicalInfo.getCommericialNo()).isEqualTo(UPDATED_COMMERICIAL_NO);
        assertThat(testDoctorMedicalInfo.getLocationId()).isEqualTo(UPDATED_LOCATION_ID);
        assertThat(testDoctorMedicalInfo.getTaxonomyCode()).isEqualTo(UPDATED_TAXONOMY_CODE);
        assertThat(testDoctorMedicalInfo.getPecosCertifiedStatus()).isEqualTo(UPDATED_PECOS_CERTIFIED_STATUS);
        assertThat(testDoctorMedicalInfo.getUser1()).isEqualTo(UPDATED_USER_1);
        assertThat(testDoctorMedicalInfo.getUser2()).isEqualTo(UPDATED_USER_2);
        assertThat(testDoctorMedicalInfo.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testDoctorMedicalInfo.getTaxonomyGroup()).isEqualTo(UPDATED_TAXONOMY_GROUP);
        assertThat(testDoctorMedicalInfo.getTaxonomyDesc()).isEqualTo(UPDATED_TAXONOMY_DESC);
        assertThat(testDoctorMedicalInfo.getPracticeState()).isEqualTo(UPDATED_PRACTICE_STATE);
        assertThat(testDoctorMedicalInfo.getPrimaryTaxonomy()).isEqualTo(UPDATED_PRIMARY_TAXONOMY);
        assertThat(testDoctorMedicalInfo.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testDoctorMedicalInfo.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testDoctorMedicalInfo.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testDoctorMedicalInfo.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testDoctorMedicalInfo.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testDoctorMedicalInfo.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testDoctorMedicalInfo.getDoctorMedicalInfoUuid()).isEqualTo(UPDATED_DOCTOR_MEDICAL_INFO_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingDoctorMedicalInfo() throws Exception {
        int databaseSizeBeforeUpdate = doctorMedicalInfoRepository.findAll().size();
        doctorMedicalInfo.setMedicalInfoId(count.incrementAndGet());

        // Create the DoctorMedicalInfo
        DoctorMedicalInfoDTO doctorMedicalInfoDTO = doctorMedicalInfoMapper.toDto(doctorMedicalInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDoctorMedicalInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, doctorMedicalInfoDTO.getMedicalInfoId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(doctorMedicalInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DoctorMedicalInfo in the database
        List<DoctorMedicalInfo> doctorMedicalInfoList = doctorMedicalInfoRepository.findAll();
        assertThat(doctorMedicalInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDoctorMedicalInfo() throws Exception {
        int databaseSizeBeforeUpdate = doctorMedicalInfoRepository.findAll().size();
        doctorMedicalInfo.setMedicalInfoId(count.incrementAndGet());

        // Create the DoctorMedicalInfo
        DoctorMedicalInfoDTO doctorMedicalInfoDTO = doctorMedicalInfoMapper.toDto(doctorMedicalInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDoctorMedicalInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(doctorMedicalInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DoctorMedicalInfo in the database
        List<DoctorMedicalInfo> doctorMedicalInfoList = doctorMedicalInfoRepository.findAll();
        assertThat(doctorMedicalInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDoctorMedicalInfo() throws Exception {
        int databaseSizeBeforeUpdate = doctorMedicalInfoRepository.findAll().size();
        doctorMedicalInfo.setMedicalInfoId(count.incrementAndGet());

        // Create the DoctorMedicalInfo
        DoctorMedicalInfoDTO doctorMedicalInfoDTO = doctorMedicalInfoMapper.toDto(doctorMedicalInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDoctorMedicalInfoMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(doctorMedicalInfoDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DoctorMedicalInfo in the database
        List<DoctorMedicalInfo> doctorMedicalInfoList = doctorMedicalInfoRepository.findAll();
        assertThat(doctorMedicalInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDoctorMedicalInfo() throws Exception {
        // Initialize the database
        doctorMedicalInfoRepository.saveAndFlush(doctorMedicalInfo);

        int databaseSizeBeforeDelete = doctorMedicalInfoRepository.findAll().size();

        // Delete the doctorMedicalInfo
        restDoctorMedicalInfoMockMvc
            .perform(delete(ENTITY_API_URL_ID, doctorMedicalInfo.getMedicalInfoId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DoctorMedicalInfo> doctorMedicalInfoList = doctorMedicalInfoRepository.findAll();
        assertThat(doctorMedicalInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
