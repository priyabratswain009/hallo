package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.PatientDiagnosis;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.PatientDiagnosisRepository;
import com.sunknowledge.dme.rcm.service.dto.PatientDiagnosisDTO;
import com.sunknowledge.dme.rcm.service.mapper.PatientDiagnosisMapper;
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
 * Integration tests for the {@link PatientDiagnosisResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class PatientDiagnosisResourceIT {

    private static final Long DEFAULT_PATIENT_ID = 1L;
    private static final Long UPDATED_PATIENT_ID = 2L;

    private static final String DEFAULT_DIAGNOSIS_CODE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_DIAGNOSIS_CODE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_DIAGNOSIS_CODE = "AAAAAAAAAA";
    private static final String UPDATED_DIAGNOSIS_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DIAGNOSIS_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DIAGNOSIS_DESCRIPTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_EFFECTIVE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_EFFECTIVE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final UUID DEFAULT_PATIENT_DIAGNOSIS_UUID = UUID.randomUUID();
    private static final UUID UPDATED_PATIENT_DIAGNOSIS_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/patient-diagnoses";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{patientDiagnosisId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PatientDiagnosisRepository patientDiagnosisRepository;

    @Autowired
    private PatientDiagnosisMapper patientDiagnosisMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private PatientDiagnosis patientDiagnosis;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PatientDiagnosis createEntity(EntityManager em) {
        PatientDiagnosis patientDiagnosis = new PatientDiagnosis()
            .patientId(DEFAULT_PATIENT_ID)
            .diagnosisCodeType(DEFAULT_DIAGNOSIS_CODE_TYPE)
            .diagnosisCode(DEFAULT_DIAGNOSIS_CODE)
            .diagnosisDescription(DEFAULT_DIAGNOSIS_DESCRIPTION)
            .effectiveDate(DEFAULT_EFFECTIVE_DATE)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .patientDiagnosisUuid(DEFAULT_PATIENT_DIAGNOSIS_UUID);
        return patientDiagnosis;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PatientDiagnosis createUpdatedEntity(EntityManager em) {
        PatientDiagnosis patientDiagnosis = new PatientDiagnosis()
            .patientId(UPDATED_PATIENT_ID)
            .diagnosisCodeType(UPDATED_DIAGNOSIS_CODE_TYPE)
            .diagnosisCode(UPDATED_DIAGNOSIS_CODE)
            .diagnosisDescription(UPDATED_DIAGNOSIS_DESCRIPTION)
            .effectiveDate(UPDATED_EFFECTIVE_DATE)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .patientDiagnosisUuid(UPDATED_PATIENT_DIAGNOSIS_UUID);
        return patientDiagnosis;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(PatientDiagnosis.class).block();
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
        patientDiagnosis = createEntity(em);
    }

    @Test
    void createPatientDiagnosis() throws Exception {
        int databaseSizeBeforeCreate = patientDiagnosisRepository.findAll().collectList().block().size();
        // Create the PatientDiagnosis
        PatientDiagnosisDTO patientDiagnosisDTO = patientDiagnosisMapper.toDto(patientDiagnosis);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDiagnosisDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the PatientDiagnosis in the database
        List<PatientDiagnosis> patientDiagnosisList = patientDiagnosisRepository.findAll().collectList().block();
        assertThat(patientDiagnosisList).hasSize(databaseSizeBeforeCreate + 1);
        PatientDiagnosis testPatientDiagnosis = patientDiagnosisList.get(patientDiagnosisList.size() - 1);
        assertThat(testPatientDiagnosis.getPatientId()).isEqualTo(DEFAULT_PATIENT_ID);
        assertThat(testPatientDiagnosis.getDiagnosisCodeType()).isEqualTo(DEFAULT_DIAGNOSIS_CODE_TYPE);
        assertThat(testPatientDiagnosis.getDiagnosisCode()).isEqualTo(DEFAULT_DIAGNOSIS_CODE);
        assertThat(testPatientDiagnosis.getDiagnosisDescription()).isEqualTo(DEFAULT_DIAGNOSIS_DESCRIPTION);
        assertThat(testPatientDiagnosis.getEffectiveDate()).isEqualTo(DEFAULT_EFFECTIVE_DATE);
        assertThat(testPatientDiagnosis.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPatientDiagnosis.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testPatientDiagnosis.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testPatientDiagnosis.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testPatientDiagnosis.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testPatientDiagnosis.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testPatientDiagnosis.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testPatientDiagnosis.getPatientDiagnosisUuid()).isEqualTo(DEFAULT_PATIENT_DIAGNOSIS_UUID);
    }

    @Test
    void createPatientDiagnosisWithExistingId() throws Exception {
        // Create the PatientDiagnosis with an existing ID
        patientDiagnosis.setPatientDiagnosisId(1L);
        PatientDiagnosisDTO patientDiagnosisDTO = patientDiagnosisMapper.toDto(patientDiagnosis);

        int databaseSizeBeforeCreate = patientDiagnosisRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDiagnosisDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDiagnosis in the database
        List<PatientDiagnosis> patientDiagnosisList = patientDiagnosisRepository.findAll().collectList().block();
        assertThat(patientDiagnosisList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllPatientDiagnoses() {
        // Initialize the database
        patientDiagnosisRepository.save(patientDiagnosis).block();

        // Get all the patientDiagnosisList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=patientDiagnosisId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].patientDiagnosisId")
            .value(hasItem(patientDiagnosis.getPatientDiagnosisId().intValue()))
            .jsonPath("$.[*].patientId")
            .value(hasItem(DEFAULT_PATIENT_ID.intValue()))
            .jsonPath("$.[*].diagnosisCodeType")
            .value(hasItem(DEFAULT_DIAGNOSIS_CODE_TYPE))
            .jsonPath("$.[*].diagnosisCode")
            .value(hasItem(DEFAULT_DIAGNOSIS_CODE))
            .jsonPath("$.[*].diagnosisDescription")
            .value(hasItem(DEFAULT_DIAGNOSIS_DESCRIPTION))
            .jsonPath("$.[*].effectiveDate")
            .value(hasItem(DEFAULT_EFFECTIVE_DATE.toString()))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS))
            .jsonPath("$.[*].createdById")
            .value(hasItem(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.[*].createdDate")
            .value(hasItem(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.[*].createdByName")
            .value(hasItem(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.[*].updatedByName")
            .value(hasItem(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.[*].updatedById")
            .value(hasItem(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.[*].updatedDate")
            .value(hasItem(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.[*].patientDiagnosisUuid")
            .value(hasItem(DEFAULT_PATIENT_DIAGNOSIS_UUID.toString()));
    }

    @Test
    void getPatientDiagnosis() {
        // Initialize the database
        patientDiagnosisRepository.save(patientDiagnosis).block();

        // Get the patientDiagnosis
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, patientDiagnosis.getPatientDiagnosisId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.patientDiagnosisId")
            .value(is(patientDiagnosis.getPatientDiagnosisId().intValue()))
            .jsonPath("$.patientId")
            .value(is(DEFAULT_PATIENT_ID.intValue()))
            .jsonPath("$.diagnosisCodeType")
            .value(is(DEFAULT_DIAGNOSIS_CODE_TYPE))
            .jsonPath("$.diagnosisCode")
            .value(is(DEFAULT_DIAGNOSIS_CODE))
            .jsonPath("$.diagnosisDescription")
            .value(is(DEFAULT_DIAGNOSIS_DESCRIPTION))
            .jsonPath("$.effectiveDate")
            .value(is(DEFAULT_EFFECTIVE_DATE.toString()))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS))
            .jsonPath("$.createdById")
            .value(is(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.createdDate")
            .value(is(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.createdByName")
            .value(is(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.updatedByName")
            .value(is(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.updatedById")
            .value(is(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.updatedDate")
            .value(is(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.patientDiagnosisUuid")
            .value(is(DEFAULT_PATIENT_DIAGNOSIS_UUID.toString()));
    }

    @Test
    void getNonExistingPatientDiagnosis() {
        // Get the patientDiagnosis
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingPatientDiagnosis() throws Exception {
        // Initialize the database
        patientDiagnosisRepository.save(patientDiagnosis).block();

        int databaseSizeBeforeUpdate = patientDiagnosisRepository.findAll().collectList().block().size();

        // Update the patientDiagnosis
        PatientDiagnosis updatedPatientDiagnosis = patientDiagnosisRepository.findById(patientDiagnosis.getPatientDiagnosisId()).block();
        updatedPatientDiagnosis
            .patientId(UPDATED_PATIENT_ID)
            .diagnosisCodeType(UPDATED_DIAGNOSIS_CODE_TYPE)
            .diagnosisCode(UPDATED_DIAGNOSIS_CODE)
            .diagnosisDescription(UPDATED_DIAGNOSIS_DESCRIPTION)
            .effectiveDate(UPDATED_EFFECTIVE_DATE)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .patientDiagnosisUuid(UPDATED_PATIENT_DIAGNOSIS_UUID);
        PatientDiagnosisDTO patientDiagnosisDTO = patientDiagnosisMapper.toDto(updatedPatientDiagnosis);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, patientDiagnosisDTO.getPatientDiagnosisId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDiagnosisDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientDiagnosis in the database
        List<PatientDiagnosis> patientDiagnosisList = patientDiagnosisRepository.findAll().collectList().block();
        assertThat(patientDiagnosisList).hasSize(databaseSizeBeforeUpdate);
        PatientDiagnosis testPatientDiagnosis = patientDiagnosisList.get(patientDiagnosisList.size() - 1);
        assertThat(testPatientDiagnosis.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testPatientDiagnosis.getDiagnosisCodeType()).isEqualTo(UPDATED_DIAGNOSIS_CODE_TYPE);
        assertThat(testPatientDiagnosis.getDiagnosisCode()).isEqualTo(UPDATED_DIAGNOSIS_CODE);
        assertThat(testPatientDiagnosis.getDiagnosisDescription()).isEqualTo(UPDATED_DIAGNOSIS_DESCRIPTION);
        assertThat(testPatientDiagnosis.getEffectiveDate()).isEqualTo(UPDATED_EFFECTIVE_DATE);
        assertThat(testPatientDiagnosis.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPatientDiagnosis.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPatientDiagnosis.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPatientDiagnosis.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPatientDiagnosis.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPatientDiagnosis.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPatientDiagnosis.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPatientDiagnosis.getPatientDiagnosisUuid()).isEqualTo(UPDATED_PATIENT_DIAGNOSIS_UUID);
    }

    @Test
    void putNonExistingPatientDiagnosis() throws Exception {
        int databaseSizeBeforeUpdate = patientDiagnosisRepository.findAll().collectList().block().size();
        patientDiagnosis.setPatientDiagnosisId(count.incrementAndGet());

        // Create the PatientDiagnosis
        PatientDiagnosisDTO patientDiagnosisDTO = patientDiagnosisMapper.toDto(patientDiagnosis);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, patientDiagnosisDTO.getPatientDiagnosisId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDiagnosisDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDiagnosis in the database
        List<PatientDiagnosis> patientDiagnosisList = patientDiagnosisRepository.findAll().collectList().block();
        assertThat(patientDiagnosisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchPatientDiagnosis() throws Exception {
        int databaseSizeBeforeUpdate = patientDiagnosisRepository.findAll().collectList().block().size();
        patientDiagnosis.setPatientDiagnosisId(count.incrementAndGet());

        // Create the PatientDiagnosis
        PatientDiagnosisDTO patientDiagnosisDTO = patientDiagnosisMapper.toDto(patientDiagnosis);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDiagnosisDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDiagnosis in the database
        List<PatientDiagnosis> patientDiagnosisList = patientDiagnosisRepository.findAll().collectList().block();
        assertThat(patientDiagnosisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamPatientDiagnosis() throws Exception {
        int databaseSizeBeforeUpdate = patientDiagnosisRepository.findAll().collectList().block().size();
        patientDiagnosis.setPatientDiagnosisId(count.incrementAndGet());

        // Create the PatientDiagnosis
        PatientDiagnosisDTO patientDiagnosisDTO = patientDiagnosisMapper.toDto(patientDiagnosis);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDiagnosisDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PatientDiagnosis in the database
        List<PatientDiagnosis> patientDiagnosisList = patientDiagnosisRepository.findAll().collectList().block();
        assertThat(patientDiagnosisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdatePatientDiagnosisWithPatch() throws Exception {
        // Initialize the database
        patientDiagnosisRepository.save(patientDiagnosis).block();

        int databaseSizeBeforeUpdate = patientDiagnosisRepository.findAll().collectList().block().size();

        // Update the patientDiagnosis using partial update
        PatientDiagnosis partialUpdatedPatientDiagnosis = new PatientDiagnosis();
        partialUpdatedPatientDiagnosis.setPatientDiagnosisId(patientDiagnosis.getPatientDiagnosisId());

        partialUpdatedPatientDiagnosis
            .patientId(UPDATED_PATIENT_ID)
            .diagnosisDescription(UPDATED_DIAGNOSIS_DESCRIPTION)
            .effectiveDate(UPDATED_EFFECTIVE_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPatientDiagnosis.getPatientDiagnosisId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPatientDiagnosis))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientDiagnosis in the database
        List<PatientDiagnosis> patientDiagnosisList = patientDiagnosisRepository.findAll().collectList().block();
        assertThat(patientDiagnosisList).hasSize(databaseSizeBeforeUpdate);
        PatientDiagnosis testPatientDiagnosis = patientDiagnosisList.get(patientDiagnosisList.size() - 1);
        assertThat(testPatientDiagnosis.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testPatientDiagnosis.getDiagnosisCodeType()).isEqualTo(DEFAULT_DIAGNOSIS_CODE_TYPE);
        assertThat(testPatientDiagnosis.getDiagnosisCode()).isEqualTo(DEFAULT_DIAGNOSIS_CODE);
        assertThat(testPatientDiagnosis.getDiagnosisDescription()).isEqualTo(UPDATED_DIAGNOSIS_DESCRIPTION);
        assertThat(testPatientDiagnosis.getEffectiveDate()).isEqualTo(UPDATED_EFFECTIVE_DATE);
        assertThat(testPatientDiagnosis.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPatientDiagnosis.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPatientDiagnosis.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPatientDiagnosis.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testPatientDiagnosis.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPatientDiagnosis.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPatientDiagnosis.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testPatientDiagnosis.getPatientDiagnosisUuid()).isEqualTo(DEFAULT_PATIENT_DIAGNOSIS_UUID);
    }

    @Test
    void fullUpdatePatientDiagnosisWithPatch() throws Exception {
        // Initialize the database
        patientDiagnosisRepository.save(patientDiagnosis).block();

        int databaseSizeBeforeUpdate = patientDiagnosisRepository.findAll().collectList().block().size();

        // Update the patientDiagnosis using partial update
        PatientDiagnosis partialUpdatedPatientDiagnosis = new PatientDiagnosis();
        partialUpdatedPatientDiagnosis.setPatientDiagnosisId(patientDiagnosis.getPatientDiagnosisId());

        partialUpdatedPatientDiagnosis
            .patientId(UPDATED_PATIENT_ID)
            .diagnosisCodeType(UPDATED_DIAGNOSIS_CODE_TYPE)
            .diagnosisCode(UPDATED_DIAGNOSIS_CODE)
            .diagnosisDescription(UPDATED_DIAGNOSIS_DESCRIPTION)
            .effectiveDate(UPDATED_EFFECTIVE_DATE)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .patientDiagnosisUuid(UPDATED_PATIENT_DIAGNOSIS_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPatientDiagnosis.getPatientDiagnosisId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPatientDiagnosis))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientDiagnosis in the database
        List<PatientDiagnosis> patientDiagnosisList = patientDiagnosisRepository.findAll().collectList().block();
        assertThat(patientDiagnosisList).hasSize(databaseSizeBeforeUpdate);
        PatientDiagnosis testPatientDiagnosis = patientDiagnosisList.get(patientDiagnosisList.size() - 1);
        assertThat(testPatientDiagnosis.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testPatientDiagnosis.getDiagnosisCodeType()).isEqualTo(UPDATED_DIAGNOSIS_CODE_TYPE);
        assertThat(testPatientDiagnosis.getDiagnosisCode()).isEqualTo(UPDATED_DIAGNOSIS_CODE);
        assertThat(testPatientDiagnosis.getDiagnosisDescription()).isEqualTo(UPDATED_DIAGNOSIS_DESCRIPTION);
        assertThat(testPatientDiagnosis.getEffectiveDate()).isEqualTo(UPDATED_EFFECTIVE_DATE);
        assertThat(testPatientDiagnosis.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPatientDiagnosis.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPatientDiagnosis.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPatientDiagnosis.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPatientDiagnosis.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPatientDiagnosis.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPatientDiagnosis.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPatientDiagnosis.getPatientDiagnosisUuid()).isEqualTo(UPDATED_PATIENT_DIAGNOSIS_UUID);
    }

    @Test
    void patchNonExistingPatientDiagnosis() throws Exception {
        int databaseSizeBeforeUpdate = patientDiagnosisRepository.findAll().collectList().block().size();
        patientDiagnosis.setPatientDiagnosisId(count.incrementAndGet());

        // Create the PatientDiagnosis
        PatientDiagnosisDTO patientDiagnosisDTO = patientDiagnosisMapper.toDto(patientDiagnosis);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, patientDiagnosisDTO.getPatientDiagnosisId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDiagnosisDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDiagnosis in the database
        List<PatientDiagnosis> patientDiagnosisList = patientDiagnosisRepository.findAll().collectList().block();
        assertThat(patientDiagnosisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchPatientDiagnosis() throws Exception {
        int databaseSizeBeforeUpdate = patientDiagnosisRepository.findAll().collectList().block().size();
        patientDiagnosis.setPatientDiagnosisId(count.incrementAndGet());

        // Create the PatientDiagnosis
        PatientDiagnosisDTO patientDiagnosisDTO = patientDiagnosisMapper.toDto(patientDiagnosis);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDiagnosisDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDiagnosis in the database
        List<PatientDiagnosis> patientDiagnosisList = patientDiagnosisRepository.findAll().collectList().block();
        assertThat(patientDiagnosisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamPatientDiagnosis() throws Exception {
        int databaseSizeBeforeUpdate = patientDiagnosisRepository.findAll().collectList().block().size();
        patientDiagnosis.setPatientDiagnosisId(count.incrementAndGet());

        // Create the PatientDiagnosis
        PatientDiagnosisDTO patientDiagnosisDTO = patientDiagnosisMapper.toDto(patientDiagnosis);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDiagnosisDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PatientDiagnosis in the database
        List<PatientDiagnosis> patientDiagnosisList = patientDiagnosisRepository.findAll().collectList().block();
        assertThat(patientDiagnosisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deletePatientDiagnosis() {
        // Initialize the database
        patientDiagnosisRepository.save(patientDiagnosis).block();

        int databaseSizeBeforeDelete = patientDiagnosisRepository.findAll().collectList().block().size();

        // Delete the patientDiagnosis
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, patientDiagnosis.getPatientDiagnosisId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<PatientDiagnosis> patientDiagnosisList = patientDiagnosisRepository.findAll().collectList().block();
        assertThat(patientDiagnosisList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
