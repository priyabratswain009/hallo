package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.PatientDoctorMap;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.PatientDoctorMapRepository;
import com.sunknowledge.dme.rcm.service.dto.PatientDoctorMapDTO;
import com.sunknowledge.dme.rcm.service.mapper.PatientDoctorMapMapper;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link PatientDoctorMapResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class PatientDoctorMapResourceIT {

    private static final Long DEFAULT_PATIENT_ID = 1L;
    private static final Long UPDATED_PATIENT_ID = 2L;

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DOCTOR_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DOCTOR_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DOCTOR_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DOCTOR_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DOCTOR_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DOCTOR_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DOCTOR_NAME_SUFFIX = "AAAAAAAAAA";
    private static final String UPDATED_DOCTOR_NAME_SUFFIX = "BBBBBBBBBB";

    private static final String DEFAULT_DOCTOR_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_DOCTOR_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_DOCTOR_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_DOCTOR_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_DOCTOR_ADDRESS_CITY = "AAAAAAAAAA";
    private static final String UPDATED_DOCTOR_ADDRESS_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_DOCTOR_ADDRESS_STATE = "AAAAAAAAAA";
    private static final String UPDATED_DOCTOR_ADDRESS_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_DOCTOR_ADDRESS_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_DOCTOR_ADDRESS_ZIP = "BBBBBBBBBB";

    private static final String DEFAULT_DOCTOR_CONTACT_1 = "AAAAAAAAAA";
    private static final String UPDATED_DOCTOR_CONTACT_1 = "BBBBBBBBBB";

    private static final String DEFAULT_DOCTOR_CONTACT_2 = "AAAAAAAAAA";
    private static final String UPDATED_DOCTOR_CONTACT_2 = "BBBBBBBBBB";

    private static final String DEFAULT_DOCTOR_FAX = "AAAAAAAAAA";
    private static final String UPDATED_DOCTOR_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_DOCTOR_NPI_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_DOCTOR_NPI_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_DOCTOR_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_DOCTOR_GENDER = "BBBBBBBBBB";

    private static final String DEFAULT_DOCTOR_TAXONOMY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_DOCTOR_TAXONOMY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DOCTOR_TAXONOMY_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DOCTOR_TAXONOMY_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_DOCTOR_PRACTICE_STATE = "AAAAAAAAAA";
    private static final String UPDATED_DOCTOR_PRACTICE_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_DOCTOR_LICENSE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_DOCTOR_LICENSE_NUMBER = "BBBBBBBBBB";

    private static final UUID DEFAULT_PATIENT_DOCTOR_MAP_UUID = UUID.randomUUID();
    private static final UUID UPDATED_PATIENT_DOCTOR_MAP_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/patient-doctor-maps";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{patientDoctorMapId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PatientDoctorMapRepository patientDoctorMapRepository;

    @Autowired
    private PatientDoctorMapMapper patientDoctorMapMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private PatientDoctorMap patientDoctorMap;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PatientDoctorMap createEntity(EntityManager em) {
        PatientDoctorMap patientDoctorMap = new PatientDoctorMap()
            .patientId(DEFAULT_PATIENT_ID)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .status(DEFAULT_STATUS)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .doctorFirstName(DEFAULT_DOCTOR_FIRST_NAME)
            .doctorMiddleName(DEFAULT_DOCTOR_MIDDLE_NAME)
            .doctorLastName(DEFAULT_DOCTOR_LAST_NAME)
            .doctorNameSuffix(DEFAULT_DOCTOR_NAME_SUFFIX)
            .doctorAddressLine1(DEFAULT_DOCTOR_ADDRESS_LINE_1)
            .doctorAddressLine2(DEFAULT_DOCTOR_ADDRESS_LINE_2)
            .doctorAddressCity(DEFAULT_DOCTOR_ADDRESS_CITY)
            .doctorAddressState(DEFAULT_DOCTOR_ADDRESS_STATE)
            .doctorAddressZip(DEFAULT_DOCTOR_ADDRESS_ZIP)
            .doctorContact1(DEFAULT_DOCTOR_CONTACT_1)
            .doctorContact2(DEFAULT_DOCTOR_CONTACT_2)
            .doctorFax(DEFAULT_DOCTOR_FAX)
            .doctorNpiNumber(DEFAULT_DOCTOR_NPI_NUMBER)
            .doctorGender(DEFAULT_DOCTOR_GENDER)
            .doctorTaxonomyCode(DEFAULT_DOCTOR_TAXONOMY_CODE)
            .doctorTaxonomyDescription(DEFAULT_DOCTOR_TAXONOMY_DESCRIPTION)
            .doctorPracticeState(DEFAULT_DOCTOR_PRACTICE_STATE)
            .doctorLicenseNumber(DEFAULT_DOCTOR_LICENSE_NUMBER)
            .patientDoctorMapUuid(DEFAULT_PATIENT_DOCTOR_MAP_UUID);
        return patientDoctorMap;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PatientDoctorMap createUpdatedEntity(EntityManager em) {
        PatientDoctorMap patientDoctorMap = new PatientDoctorMap()
            .patientId(UPDATED_PATIENT_ID)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .status(UPDATED_STATUS)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .doctorFirstName(UPDATED_DOCTOR_FIRST_NAME)
            .doctorMiddleName(UPDATED_DOCTOR_MIDDLE_NAME)
            .doctorLastName(UPDATED_DOCTOR_LAST_NAME)
            .doctorNameSuffix(UPDATED_DOCTOR_NAME_SUFFIX)
            .doctorAddressLine1(UPDATED_DOCTOR_ADDRESS_LINE_1)
            .doctorAddressLine2(UPDATED_DOCTOR_ADDRESS_LINE_2)
            .doctorAddressCity(UPDATED_DOCTOR_ADDRESS_CITY)
            .doctorAddressState(UPDATED_DOCTOR_ADDRESS_STATE)
            .doctorAddressZip(UPDATED_DOCTOR_ADDRESS_ZIP)
            .doctorContact1(UPDATED_DOCTOR_CONTACT_1)
            .doctorContact2(UPDATED_DOCTOR_CONTACT_2)
            .doctorFax(UPDATED_DOCTOR_FAX)
            .doctorNpiNumber(UPDATED_DOCTOR_NPI_NUMBER)
            .doctorGender(UPDATED_DOCTOR_GENDER)
            .doctorTaxonomyCode(UPDATED_DOCTOR_TAXONOMY_CODE)
            .doctorTaxonomyDescription(UPDATED_DOCTOR_TAXONOMY_DESCRIPTION)
            .doctorPracticeState(UPDATED_DOCTOR_PRACTICE_STATE)
            .doctorLicenseNumber(UPDATED_DOCTOR_LICENSE_NUMBER)
            .patientDoctorMapUuid(UPDATED_PATIENT_DOCTOR_MAP_UUID);
        return patientDoctorMap;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(PatientDoctorMap.class).block();
        } catch (Exception e) {
            // It can fail, if other entities are still referring this - it will be removed later.
        }
    }

    @AfterEach
    public void cleanup() {
        deleteEntities(em);
    }

    @BeforeEach
    public void setupCsrf() {
        webTestClient = webTestClient.mutateWith(csrf());
    }

    @BeforeEach
    public void initTest() {
        deleteEntities(em);
        patientDoctorMap = createEntity(em);
    }

    @Test
    void createPatientDoctorMap() throws Exception {
        int databaseSizeBeforeCreate = patientDoctorMapRepository.findAll().collectList().block().size();
        // Create the PatientDoctorMap
        PatientDoctorMapDTO patientDoctorMapDTO = patientDoctorMapMapper.toDto(patientDoctorMap);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDoctorMapDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the PatientDoctorMap in the database
        List<PatientDoctorMap> patientDoctorMapList = patientDoctorMapRepository.findAll().collectList().block();
        assertThat(patientDoctorMapList).hasSize(databaseSizeBeforeCreate + 1);
        PatientDoctorMap testPatientDoctorMap = patientDoctorMapList.get(patientDoctorMapList.size() - 1);
        assertThat(testPatientDoctorMap.getPatientId()).isEqualTo(DEFAULT_PATIENT_ID);
        assertThat(testPatientDoctorMap.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testPatientDoctorMap.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testPatientDoctorMap.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPatientDoctorMap.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testPatientDoctorMap.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testPatientDoctorMap.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testPatientDoctorMap.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testPatientDoctorMap.getDoctorFirstName()).isEqualTo(DEFAULT_DOCTOR_FIRST_NAME);
        assertThat(testPatientDoctorMap.getDoctorMiddleName()).isEqualTo(DEFAULT_DOCTOR_MIDDLE_NAME);
        assertThat(testPatientDoctorMap.getDoctorLastName()).isEqualTo(DEFAULT_DOCTOR_LAST_NAME);
        assertThat(testPatientDoctorMap.getDoctorNameSuffix()).isEqualTo(DEFAULT_DOCTOR_NAME_SUFFIX);
        assertThat(testPatientDoctorMap.getDoctorAddressLine1()).isEqualTo(DEFAULT_DOCTOR_ADDRESS_LINE_1);
        assertThat(testPatientDoctorMap.getDoctorAddressLine2()).isEqualTo(DEFAULT_DOCTOR_ADDRESS_LINE_2);
        assertThat(testPatientDoctorMap.getDoctorAddressCity()).isEqualTo(DEFAULT_DOCTOR_ADDRESS_CITY);
        assertThat(testPatientDoctorMap.getDoctorAddressState()).isEqualTo(DEFAULT_DOCTOR_ADDRESS_STATE);
        assertThat(testPatientDoctorMap.getDoctorAddressZip()).isEqualTo(DEFAULT_DOCTOR_ADDRESS_ZIP);
        assertThat(testPatientDoctorMap.getDoctorContact1()).isEqualTo(DEFAULT_DOCTOR_CONTACT_1);
        assertThat(testPatientDoctorMap.getDoctorContact2()).isEqualTo(DEFAULT_DOCTOR_CONTACT_2);
        assertThat(testPatientDoctorMap.getDoctorFax()).isEqualTo(DEFAULT_DOCTOR_FAX);
        assertThat(testPatientDoctorMap.getDoctorNpiNumber()).isEqualTo(DEFAULT_DOCTOR_NPI_NUMBER);
        assertThat(testPatientDoctorMap.getDoctorGender()).isEqualTo(DEFAULT_DOCTOR_GENDER);
        assertThat(testPatientDoctorMap.getDoctorTaxonomyCode()).isEqualTo(DEFAULT_DOCTOR_TAXONOMY_CODE);
        assertThat(testPatientDoctorMap.getDoctorTaxonomyDescription()).isEqualTo(DEFAULT_DOCTOR_TAXONOMY_DESCRIPTION);
        assertThat(testPatientDoctorMap.getDoctorPracticeState()).isEqualTo(DEFAULT_DOCTOR_PRACTICE_STATE);
        assertThat(testPatientDoctorMap.getDoctorLicenseNumber()).isEqualTo(DEFAULT_DOCTOR_LICENSE_NUMBER);
        assertThat(testPatientDoctorMap.getPatientDoctorMapUuid()).isEqualTo(DEFAULT_PATIENT_DOCTOR_MAP_UUID);
    }

    @Test
    void createPatientDoctorMapWithExistingId() throws Exception {
        // Create the PatientDoctorMap with an existing ID
        patientDoctorMap.setPatientDoctorMapId(1L);
        PatientDoctorMapDTO patientDoctorMapDTO = patientDoctorMapMapper.toDto(patientDoctorMap);

        int databaseSizeBeforeCreate = patientDoctorMapRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDoctorMapDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDoctorMap in the database
        List<PatientDoctorMap> patientDoctorMapList = patientDoctorMapRepository.findAll().collectList().block();
        assertThat(patientDoctorMapList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllPatientDoctorMaps() {
        // Initialize the database
        patientDoctorMapRepository.save(patientDoctorMap).block();

        // Get all the patientDoctorMapList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=patientDoctorMapId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].patientDoctorMapId")
            .value(hasItem(patientDoctorMap.getPatientDoctorMapId().intValue()))
            .jsonPath("$.[*].patientId")
            .value(hasItem(DEFAULT_PATIENT_ID.intValue()))
            .jsonPath("$.[*].createdById")
            .value(hasItem(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.[*].createdDate")
            .value(hasItem(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS))
            .jsonPath("$.[*].createdByName")
            .value(hasItem(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.[*].updatedByName")
            .value(hasItem(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.[*].updatedById")
            .value(hasItem(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.[*].updatedDate")
            .value(hasItem(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.[*].doctorFirstName")
            .value(hasItem(DEFAULT_DOCTOR_FIRST_NAME))
            .jsonPath("$.[*].doctorMiddleName")
            .value(hasItem(DEFAULT_DOCTOR_MIDDLE_NAME))
            .jsonPath("$.[*].doctorLastName")
            .value(hasItem(DEFAULT_DOCTOR_LAST_NAME))
            .jsonPath("$.[*].doctorNameSuffix")
            .value(hasItem(DEFAULT_DOCTOR_NAME_SUFFIX))
            .jsonPath("$.[*].doctorAddressLine1")
            .value(hasItem(DEFAULT_DOCTOR_ADDRESS_LINE_1))
            .jsonPath("$.[*].doctorAddressLine2")
            .value(hasItem(DEFAULT_DOCTOR_ADDRESS_LINE_2))
            .jsonPath("$.[*].doctorAddressCity")
            .value(hasItem(DEFAULT_DOCTOR_ADDRESS_CITY))
            .jsonPath("$.[*].doctorAddressState")
            .value(hasItem(DEFAULT_DOCTOR_ADDRESS_STATE))
            .jsonPath("$.[*].doctorAddressZip")
            .value(hasItem(DEFAULT_DOCTOR_ADDRESS_ZIP))
            .jsonPath("$.[*].doctorContact1")
            .value(hasItem(DEFAULT_DOCTOR_CONTACT_1))
            .jsonPath("$.[*].doctorContact2")
            .value(hasItem(DEFAULT_DOCTOR_CONTACT_2))
            .jsonPath("$.[*].doctorFax")
            .value(hasItem(DEFAULT_DOCTOR_FAX))
            .jsonPath("$.[*].doctorNpiNumber")
            .value(hasItem(DEFAULT_DOCTOR_NPI_NUMBER))
            .jsonPath("$.[*].doctorGender")
            .value(hasItem(DEFAULT_DOCTOR_GENDER))
            .jsonPath("$.[*].doctorTaxonomyCode")
            .value(hasItem(DEFAULT_DOCTOR_TAXONOMY_CODE))
            .jsonPath("$.[*].doctorTaxonomyDescription")
            .value(hasItem(DEFAULT_DOCTOR_TAXONOMY_DESCRIPTION))
            .jsonPath("$.[*].doctorPracticeState")
            .value(hasItem(DEFAULT_DOCTOR_PRACTICE_STATE))
            .jsonPath("$.[*].doctorLicenseNumber")
            .value(hasItem(DEFAULT_DOCTOR_LICENSE_NUMBER))
            .jsonPath("$.[*].patientDoctorMapUuid")
            .value(hasItem(DEFAULT_PATIENT_DOCTOR_MAP_UUID.toString()));
    }

    @Test
    void getPatientDoctorMap() {
        // Initialize the database
        patientDoctorMapRepository.save(patientDoctorMap).block();

        // Get the patientDoctorMap
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, patientDoctorMap.getPatientDoctorMapId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.patientDoctorMapId")
            .value(is(patientDoctorMap.getPatientDoctorMapId().intValue()))
            .jsonPath("$.patientId")
            .value(is(DEFAULT_PATIENT_ID.intValue()))
            .jsonPath("$.createdById")
            .value(is(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.createdDate")
            .value(is(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS))
            .jsonPath("$.createdByName")
            .value(is(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.updatedByName")
            .value(is(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.updatedById")
            .value(is(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.updatedDate")
            .value(is(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.doctorFirstName")
            .value(is(DEFAULT_DOCTOR_FIRST_NAME))
            .jsonPath("$.doctorMiddleName")
            .value(is(DEFAULT_DOCTOR_MIDDLE_NAME))
            .jsonPath("$.doctorLastName")
            .value(is(DEFAULT_DOCTOR_LAST_NAME))
            .jsonPath("$.doctorNameSuffix")
            .value(is(DEFAULT_DOCTOR_NAME_SUFFIX))
            .jsonPath("$.doctorAddressLine1")
            .value(is(DEFAULT_DOCTOR_ADDRESS_LINE_1))
            .jsonPath("$.doctorAddressLine2")
            .value(is(DEFAULT_DOCTOR_ADDRESS_LINE_2))
            .jsonPath("$.doctorAddressCity")
            .value(is(DEFAULT_DOCTOR_ADDRESS_CITY))
            .jsonPath("$.doctorAddressState")
            .value(is(DEFAULT_DOCTOR_ADDRESS_STATE))
            .jsonPath("$.doctorAddressZip")
            .value(is(DEFAULT_DOCTOR_ADDRESS_ZIP))
            .jsonPath("$.doctorContact1")
            .value(is(DEFAULT_DOCTOR_CONTACT_1))
            .jsonPath("$.doctorContact2")
            .value(is(DEFAULT_DOCTOR_CONTACT_2))
            .jsonPath("$.doctorFax")
            .value(is(DEFAULT_DOCTOR_FAX))
            .jsonPath("$.doctorNpiNumber")
            .value(is(DEFAULT_DOCTOR_NPI_NUMBER))
            .jsonPath("$.doctorGender")
            .value(is(DEFAULT_DOCTOR_GENDER))
            .jsonPath("$.doctorTaxonomyCode")
            .value(is(DEFAULT_DOCTOR_TAXONOMY_CODE))
            .jsonPath("$.doctorTaxonomyDescription")
            .value(is(DEFAULT_DOCTOR_TAXONOMY_DESCRIPTION))
            .jsonPath("$.doctorPracticeState")
            .value(is(DEFAULT_DOCTOR_PRACTICE_STATE))
            .jsonPath("$.doctorLicenseNumber")
            .value(is(DEFAULT_DOCTOR_LICENSE_NUMBER))
            .jsonPath("$.patientDoctorMapUuid")
            .value(is(DEFAULT_PATIENT_DOCTOR_MAP_UUID.toString()));
    }

    @Test
    void getNonExistingPatientDoctorMap() {
        // Get the patientDoctorMap
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingPatientDoctorMap() throws Exception {
        // Initialize the database
        patientDoctorMapRepository.save(patientDoctorMap).block();

        int databaseSizeBeforeUpdate = patientDoctorMapRepository.findAll().collectList().block().size();

        // Update the patientDoctorMap
        PatientDoctorMap updatedPatientDoctorMap = patientDoctorMapRepository.findById(patientDoctorMap.getPatientDoctorMapId()).block();
        updatedPatientDoctorMap
            .patientId(UPDATED_PATIENT_ID)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .status(UPDATED_STATUS)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .doctorFirstName(UPDATED_DOCTOR_FIRST_NAME)
            .doctorMiddleName(UPDATED_DOCTOR_MIDDLE_NAME)
            .doctorLastName(UPDATED_DOCTOR_LAST_NAME)
            .doctorNameSuffix(UPDATED_DOCTOR_NAME_SUFFIX)
            .doctorAddressLine1(UPDATED_DOCTOR_ADDRESS_LINE_1)
            .doctorAddressLine2(UPDATED_DOCTOR_ADDRESS_LINE_2)
            .doctorAddressCity(UPDATED_DOCTOR_ADDRESS_CITY)
            .doctorAddressState(UPDATED_DOCTOR_ADDRESS_STATE)
            .doctorAddressZip(UPDATED_DOCTOR_ADDRESS_ZIP)
            .doctorContact1(UPDATED_DOCTOR_CONTACT_1)
            .doctorContact2(UPDATED_DOCTOR_CONTACT_2)
            .doctorFax(UPDATED_DOCTOR_FAX)
            .doctorNpiNumber(UPDATED_DOCTOR_NPI_NUMBER)
            .doctorGender(UPDATED_DOCTOR_GENDER)
            .doctorTaxonomyCode(UPDATED_DOCTOR_TAXONOMY_CODE)
            .doctorTaxonomyDescription(UPDATED_DOCTOR_TAXONOMY_DESCRIPTION)
            .doctorPracticeState(UPDATED_DOCTOR_PRACTICE_STATE)
            .doctorLicenseNumber(UPDATED_DOCTOR_LICENSE_NUMBER)
            .patientDoctorMapUuid(UPDATED_PATIENT_DOCTOR_MAP_UUID);
        PatientDoctorMapDTO patientDoctorMapDTO = patientDoctorMapMapper.toDto(updatedPatientDoctorMap);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, patientDoctorMapDTO.getPatientDoctorMapId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDoctorMapDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientDoctorMap in the database
        List<PatientDoctorMap> patientDoctorMapList = patientDoctorMapRepository.findAll().collectList().block();
        assertThat(patientDoctorMapList).hasSize(databaseSizeBeforeUpdate);
        PatientDoctorMap testPatientDoctorMap = patientDoctorMapList.get(patientDoctorMapList.size() - 1);
        assertThat(testPatientDoctorMap.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testPatientDoctorMap.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPatientDoctorMap.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPatientDoctorMap.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPatientDoctorMap.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPatientDoctorMap.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPatientDoctorMap.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPatientDoctorMap.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPatientDoctorMap.getDoctorFirstName()).isEqualTo(UPDATED_DOCTOR_FIRST_NAME);
        assertThat(testPatientDoctorMap.getDoctorMiddleName()).isEqualTo(UPDATED_DOCTOR_MIDDLE_NAME);
        assertThat(testPatientDoctorMap.getDoctorLastName()).isEqualTo(UPDATED_DOCTOR_LAST_NAME);
        assertThat(testPatientDoctorMap.getDoctorNameSuffix()).isEqualTo(UPDATED_DOCTOR_NAME_SUFFIX);
        assertThat(testPatientDoctorMap.getDoctorAddressLine1()).isEqualTo(UPDATED_DOCTOR_ADDRESS_LINE_1);
        assertThat(testPatientDoctorMap.getDoctorAddressLine2()).isEqualTo(UPDATED_DOCTOR_ADDRESS_LINE_2);
        assertThat(testPatientDoctorMap.getDoctorAddressCity()).isEqualTo(UPDATED_DOCTOR_ADDRESS_CITY);
        assertThat(testPatientDoctorMap.getDoctorAddressState()).isEqualTo(UPDATED_DOCTOR_ADDRESS_STATE);
        assertThat(testPatientDoctorMap.getDoctorAddressZip()).isEqualTo(UPDATED_DOCTOR_ADDRESS_ZIP);
        assertThat(testPatientDoctorMap.getDoctorContact1()).isEqualTo(UPDATED_DOCTOR_CONTACT_1);
        assertThat(testPatientDoctorMap.getDoctorContact2()).isEqualTo(UPDATED_DOCTOR_CONTACT_2);
        assertThat(testPatientDoctorMap.getDoctorFax()).isEqualTo(UPDATED_DOCTOR_FAX);
        assertThat(testPatientDoctorMap.getDoctorNpiNumber()).isEqualTo(UPDATED_DOCTOR_NPI_NUMBER);
        assertThat(testPatientDoctorMap.getDoctorGender()).isEqualTo(UPDATED_DOCTOR_GENDER);
        assertThat(testPatientDoctorMap.getDoctorTaxonomyCode()).isEqualTo(UPDATED_DOCTOR_TAXONOMY_CODE);
        assertThat(testPatientDoctorMap.getDoctorTaxonomyDescription()).isEqualTo(UPDATED_DOCTOR_TAXONOMY_DESCRIPTION);
        assertThat(testPatientDoctorMap.getDoctorPracticeState()).isEqualTo(UPDATED_DOCTOR_PRACTICE_STATE);
        assertThat(testPatientDoctorMap.getDoctorLicenseNumber()).isEqualTo(UPDATED_DOCTOR_LICENSE_NUMBER);
        assertThat(testPatientDoctorMap.getPatientDoctorMapUuid()).isEqualTo(UPDATED_PATIENT_DOCTOR_MAP_UUID);
    }

    @Test
    void putNonExistingPatientDoctorMap() throws Exception {
        int databaseSizeBeforeUpdate = patientDoctorMapRepository.findAll().collectList().block().size();
        patientDoctorMap.setPatientDoctorMapId(count.incrementAndGet());

        // Create the PatientDoctorMap
        PatientDoctorMapDTO patientDoctorMapDTO = patientDoctorMapMapper.toDto(patientDoctorMap);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, patientDoctorMapDTO.getPatientDoctorMapId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDoctorMapDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDoctorMap in the database
        List<PatientDoctorMap> patientDoctorMapList = patientDoctorMapRepository.findAll().collectList().block();
        assertThat(patientDoctorMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchPatientDoctorMap() throws Exception {
        int databaseSizeBeforeUpdate = patientDoctorMapRepository.findAll().collectList().block().size();
        patientDoctorMap.setPatientDoctorMapId(count.incrementAndGet());

        // Create the PatientDoctorMap
        PatientDoctorMapDTO patientDoctorMapDTO = patientDoctorMapMapper.toDto(patientDoctorMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDoctorMapDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDoctorMap in the database
        List<PatientDoctorMap> patientDoctorMapList = patientDoctorMapRepository.findAll().collectList().block();
        assertThat(patientDoctorMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamPatientDoctorMap() throws Exception {
        int databaseSizeBeforeUpdate = patientDoctorMapRepository.findAll().collectList().block().size();
        patientDoctorMap.setPatientDoctorMapId(count.incrementAndGet());

        // Create the PatientDoctorMap
        PatientDoctorMapDTO patientDoctorMapDTO = patientDoctorMapMapper.toDto(patientDoctorMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDoctorMapDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PatientDoctorMap in the database
        List<PatientDoctorMap> patientDoctorMapList = patientDoctorMapRepository.findAll().collectList().block();
        assertThat(patientDoctorMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdatePatientDoctorMapWithPatch() throws Exception {
        // Initialize the database
        patientDoctorMapRepository.save(patientDoctorMap).block();

        int databaseSizeBeforeUpdate = patientDoctorMapRepository.findAll().collectList().block().size();

        // Update the patientDoctorMap using partial update
        PatientDoctorMap partialUpdatedPatientDoctorMap = new PatientDoctorMap();
        partialUpdatedPatientDoctorMap.setPatientDoctorMapId(patientDoctorMap.getPatientDoctorMapId());

        partialUpdatedPatientDoctorMap
            .patientId(UPDATED_PATIENT_ID)
            .status(UPDATED_STATUS)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .doctorFirstName(UPDATED_DOCTOR_FIRST_NAME)
            .doctorLastName(UPDATED_DOCTOR_LAST_NAME)
            .doctorNameSuffix(UPDATED_DOCTOR_NAME_SUFFIX)
            .doctorAddressLine1(UPDATED_DOCTOR_ADDRESS_LINE_1)
            .doctorAddressCity(UPDATED_DOCTOR_ADDRESS_CITY)
            .doctorAddressZip(UPDATED_DOCTOR_ADDRESS_ZIP)
            .doctorFax(UPDATED_DOCTOR_FAX);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPatientDoctorMap.getPatientDoctorMapId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPatientDoctorMap))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientDoctorMap in the database
        List<PatientDoctorMap> patientDoctorMapList = patientDoctorMapRepository.findAll().collectList().block();
        assertThat(patientDoctorMapList).hasSize(databaseSizeBeforeUpdate);
        PatientDoctorMap testPatientDoctorMap = patientDoctorMapList.get(patientDoctorMapList.size() - 1);
        assertThat(testPatientDoctorMap.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testPatientDoctorMap.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testPatientDoctorMap.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testPatientDoctorMap.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPatientDoctorMap.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPatientDoctorMap.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPatientDoctorMap.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testPatientDoctorMap.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPatientDoctorMap.getDoctorFirstName()).isEqualTo(UPDATED_DOCTOR_FIRST_NAME);
        assertThat(testPatientDoctorMap.getDoctorMiddleName()).isEqualTo(DEFAULT_DOCTOR_MIDDLE_NAME);
        assertThat(testPatientDoctorMap.getDoctorLastName()).isEqualTo(UPDATED_DOCTOR_LAST_NAME);
        assertThat(testPatientDoctorMap.getDoctorNameSuffix()).isEqualTo(UPDATED_DOCTOR_NAME_SUFFIX);
        assertThat(testPatientDoctorMap.getDoctorAddressLine1()).isEqualTo(UPDATED_DOCTOR_ADDRESS_LINE_1);
        assertThat(testPatientDoctorMap.getDoctorAddressLine2()).isEqualTo(DEFAULT_DOCTOR_ADDRESS_LINE_2);
        assertThat(testPatientDoctorMap.getDoctorAddressCity()).isEqualTo(UPDATED_DOCTOR_ADDRESS_CITY);
        assertThat(testPatientDoctorMap.getDoctorAddressState()).isEqualTo(DEFAULT_DOCTOR_ADDRESS_STATE);
        assertThat(testPatientDoctorMap.getDoctorAddressZip()).isEqualTo(UPDATED_DOCTOR_ADDRESS_ZIP);
        assertThat(testPatientDoctorMap.getDoctorContact1()).isEqualTo(DEFAULT_DOCTOR_CONTACT_1);
        assertThat(testPatientDoctorMap.getDoctorContact2()).isEqualTo(DEFAULT_DOCTOR_CONTACT_2);
        assertThat(testPatientDoctorMap.getDoctorFax()).isEqualTo(UPDATED_DOCTOR_FAX);
        assertThat(testPatientDoctorMap.getDoctorNpiNumber()).isEqualTo(DEFAULT_DOCTOR_NPI_NUMBER);
        assertThat(testPatientDoctorMap.getDoctorGender()).isEqualTo(DEFAULT_DOCTOR_GENDER);
        assertThat(testPatientDoctorMap.getDoctorTaxonomyCode()).isEqualTo(DEFAULT_DOCTOR_TAXONOMY_CODE);
        assertThat(testPatientDoctorMap.getDoctorTaxonomyDescription()).isEqualTo(DEFAULT_DOCTOR_TAXONOMY_DESCRIPTION);
        assertThat(testPatientDoctorMap.getDoctorPracticeState()).isEqualTo(DEFAULT_DOCTOR_PRACTICE_STATE);
        assertThat(testPatientDoctorMap.getDoctorLicenseNumber()).isEqualTo(DEFAULT_DOCTOR_LICENSE_NUMBER);
        assertThat(testPatientDoctorMap.getPatientDoctorMapUuid()).isEqualTo(DEFAULT_PATIENT_DOCTOR_MAP_UUID);
    }

    @Test
    void fullUpdatePatientDoctorMapWithPatch() throws Exception {
        // Initialize the database
        patientDoctorMapRepository.save(patientDoctorMap).block();

        int databaseSizeBeforeUpdate = patientDoctorMapRepository.findAll().collectList().block().size();

        // Update the patientDoctorMap using partial update
        PatientDoctorMap partialUpdatedPatientDoctorMap = new PatientDoctorMap();
        partialUpdatedPatientDoctorMap.setPatientDoctorMapId(patientDoctorMap.getPatientDoctorMapId());

        partialUpdatedPatientDoctorMap
            .patientId(UPDATED_PATIENT_ID)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .status(UPDATED_STATUS)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .doctorFirstName(UPDATED_DOCTOR_FIRST_NAME)
            .doctorMiddleName(UPDATED_DOCTOR_MIDDLE_NAME)
            .doctorLastName(UPDATED_DOCTOR_LAST_NAME)
            .doctorNameSuffix(UPDATED_DOCTOR_NAME_SUFFIX)
            .doctorAddressLine1(UPDATED_DOCTOR_ADDRESS_LINE_1)
            .doctorAddressLine2(UPDATED_DOCTOR_ADDRESS_LINE_2)
            .doctorAddressCity(UPDATED_DOCTOR_ADDRESS_CITY)
            .doctorAddressState(UPDATED_DOCTOR_ADDRESS_STATE)
            .doctorAddressZip(UPDATED_DOCTOR_ADDRESS_ZIP)
            .doctorContact1(UPDATED_DOCTOR_CONTACT_1)
            .doctorContact2(UPDATED_DOCTOR_CONTACT_2)
            .doctorFax(UPDATED_DOCTOR_FAX)
            .doctorNpiNumber(UPDATED_DOCTOR_NPI_NUMBER)
            .doctorGender(UPDATED_DOCTOR_GENDER)
            .doctorTaxonomyCode(UPDATED_DOCTOR_TAXONOMY_CODE)
            .doctorTaxonomyDescription(UPDATED_DOCTOR_TAXONOMY_DESCRIPTION)
            .doctorPracticeState(UPDATED_DOCTOR_PRACTICE_STATE)
            .doctorLicenseNumber(UPDATED_DOCTOR_LICENSE_NUMBER)
            .patientDoctorMapUuid(UPDATED_PATIENT_DOCTOR_MAP_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPatientDoctorMap.getPatientDoctorMapId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPatientDoctorMap))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientDoctorMap in the database
        List<PatientDoctorMap> patientDoctorMapList = patientDoctorMapRepository.findAll().collectList().block();
        assertThat(patientDoctorMapList).hasSize(databaseSizeBeforeUpdate);
        PatientDoctorMap testPatientDoctorMap = patientDoctorMapList.get(patientDoctorMapList.size() - 1);
        assertThat(testPatientDoctorMap.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testPatientDoctorMap.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPatientDoctorMap.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPatientDoctorMap.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPatientDoctorMap.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPatientDoctorMap.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPatientDoctorMap.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPatientDoctorMap.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPatientDoctorMap.getDoctorFirstName()).isEqualTo(UPDATED_DOCTOR_FIRST_NAME);
        assertThat(testPatientDoctorMap.getDoctorMiddleName()).isEqualTo(UPDATED_DOCTOR_MIDDLE_NAME);
        assertThat(testPatientDoctorMap.getDoctorLastName()).isEqualTo(UPDATED_DOCTOR_LAST_NAME);
        assertThat(testPatientDoctorMap.getDoctorNameSuffix()).isEqualTo(UPDATED_DOCTOR_NAME_SUFFIX);
        assertThat(testPatientDoctorMap.getDoctorAddressLine1()).isEqualTo(UPDATED_DOCTOR_ADDRESS_LINE_1);
        assertThat(testPatientDoctorMap.getDoctorAddressLine2()).isEqualTo(UPDATED_DOCTOR_ADDRESS_LINE_2);
        assertThat(testPatientDoctorMap.getDoctorAddressCity()).isEqualTo(UPDATED_DOCTOR_ADDRESS_CITY);
        assertThat(testPatientDoctorMap.getDoctorAddressState()).isEqualTo(UPDATED_DOCTOR_ADDRESS_STATE);
        assertThat(testPatientDoctorMap.getDoctorAddressZip()).isEqualTo(UPDATED_DOCTOR_ADDRESS_ZIP);
        assertThat(testPatientDoctorMap.getDoctorContact1()).isEqualTo(UPDATED_DOCTOR_CONTACT_1);
        assertThat(testPatientDoctorMap.getDoctorContact2()).isEqualTo(UPDATED_DOCTOR_CONTACT_2);
        assertThat(testPatientDoctorMap.getDoctorFax()).isEqualTo(UPDATED_DOCTOR_FAX);
        assertThat(testPatientDoctorMap.getDoctorNpiNumber()).isEqualTo(UPDATED_DOCTOR_NPI_NUMBER);
        assertThat(testPatientDoctorMap.getDoctorGender()).isEqualTo(UPDATED_DOCTOR_GENDER);
        assertThat(testPatientDoctorMap.getDoctorTaxonomyCode()).isEqualTo(UPDATED_DOCTOR_TAXONOMY_CODE);
        assertThat(testPatientDoctorMap.getDoctorTaxonomyDescription()).isEqualTo(UPDATED_DOCTOR_TAXONOMY_DESCRIPTION);
        assertThat(testPatientDoctorMap.getDoctorPracticeState()).isEqualTo(UPDATED_DOCTOR_PRACTICE_STATE);
        assertThat(testPatientDoctorMap.getDoctorLicenseNumber()).isEqualTo(UPDATED_DOCTOR_LICENSE_NUMBER);
        assertThat(testPatientDoctorMap.getPatientDoctorMapUuid()).isEqualTo(UPDATED_PATIENT_DOCTOR_MAP_UUID);
    }

    @Test
    void patchNonExistingPatientDoctorMap() throws Exception {
        int databaseSizeBeforeUpdate = patientDoctorMapRepository.findAll().collectList().block().size();
        patientDoctorMap.setPatientDoctorMapId(count.incrementAndGet());

        // Create the PatientDoctorMap
        PatientDoctorMapDTO patientDoctorMapDTO = patientDoctorMapMapper.toDto(patientDoctorMap);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, patientDoctorMapDTO.getPatientDoctorMapId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDoctorMapDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDoctorMap in the database
        List<PatientDoctorMap> patientDoctorMapList = patientDoctorMapRepository.findAll().collectList().block();
        assertThat(patientDoctorMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchPatientDoctorMap() throws Exception {
        int databaseSizeBeforeUpdate = patientDoctorMapRepository.findAll().collectList().block().size();
        patientDoctorMap.setPatientDoctorMapId(count.incrementAndGet());

        // Create the PatientDoctorMap
        PatientDoctorMapDTO patientDoctorMapDTO = patientDoctorMapMapper.toDto(patientDoctorMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDoctorMapDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDoctorMap in the database
        List<PatientDoctorMap> patientDoctorMapList = patientDoctorMapRepository.findAll().collectList().block();
        assertThat(patientDoctorMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamPatientDoctorMap() throws Exception {
        int databaseSizeBeforeUpdate = patientDoctorMapRepository.findAll().collectList().block().size();
        patientDoctorMap.setPatientDoctorMapId(count.incrementAndGet());

        // Create the PatientDoctorMap
        PatientDoctorMapDTO patientDoctorMapDTO = patientDoctorMapMapper.toDto(patientDoctorMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDoctorMapDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PatientDoctorMap in the database
        List<PatientDoctorMap> patientDoctorMapList = patientDoctorMapRepository.findAll().collectList().block();
        assertThat(patientDoctorMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deletePatientDoctorMap() {
        // Initialize the database
        patientDoctorMapRepository.save(patientDoctorMap).block();

        int databaseSizeBeforeDelete = patientDoctorMapRepository.findAll().collectList().block().size();

        // Delete the patientDoctorMap
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, patientDoctorMap.getPatientDoctorMapId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<PatientDoctorMap> patientDoctorMapList = patientDoctorMapRepository.findAll().collectList().block();
        assertThat(patientDoctorMapList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
