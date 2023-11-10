package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.PatientClinicalInformation;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.PatientClinicalInformationRepository;
import com.sunknowledge.dme.rcm.service.dto.PatientClinicalInformationDTO;
import com.sunknowledge.dme.rcm.service.mapper.PatientClinicalInformationMapper;
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
 * Integration tests for the {@link PatientClinicalInformationResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class PatientClinicalInformationResourceIT {

    private static final Double DEFAULT_HEIGHT = 1D;
    private static final Double UPDATED_HEIGHT = 2D;

    private static final Double DEFAULT_WEIGHT = 1D;
    private static final Double UPDATED_WEIGHT = 2D;

    private static final String DEFAULT_FUNCTIONAL_ABILITIES = "AAAAAAAAAA";
    private static final String UPDATED_FUNCTIONAL_ABILITIES = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CAPTURE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CAPTURE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_INFECTION_CONDITION_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_INFECTION_CONDITION_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_DIABETES_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_DIABETES_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_PATIENT_ID = 1L;
    private static final Long UPDATED_PATIENT_ID = 2L;

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final UUID DEFAULT_PATIENT_CLINICAL_INFORMATION_UUID = UUID.randomUUID();
    private static final UUID UPDATED_PATIENT_CLINICAL_INFORMATION_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/patient-clinical-informations";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{patientClinicalInformationId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PatientClinicalInformationRepository patientClinicalInformationRepository;

    @Autowired
    private PatientClinicalInformationMapper patientClinicalInformationMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private PatientClinicalInformation patientClinicalInformation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PatientClinicalInformation createEntity(EntityManager em) {
        PatientClinicalInformation patientClinicalInformation = new PatientClinicalInformation()
            .height(DEFAULT_HEIGHT)
            .weight(DEFAULT_WEIGHT)
            .functionalAbilities(DEFAULT_FUNCTIONAL_ABILITIES)
            .captureDate(DEFAULT_CAPTURE_DATE)
            .infectionConditionStatus(DEFAULT_INFECTION_CONDITION_STATUS)
            .diabetesStatus(DEFAULT_DIABETES_STATUS)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .patientId(DEFAULT_PATIENT_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .patientClinicalInformationUuid(DEFAULT_PATIENT_CLINICAL_INFORMATION_UUID);
        return patientClinicalInformation;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PatientClinicalInformation createUpdatedEntity(EntityManager em) {
        PatientClinicalInformation patientClinicalInformation = new PatientClinicalInformation()
            .height(UPDATED_HEIGHT)
            .weight(UPDATED_WEIGHT)
            .functionalAbilities(UPDATED_FUNCTIONAL_ABILITIES)
            .captureDate(UPDATED_CAPTURE_DATE)
            .infectionConditionStatus(UPDATED_INFECTION_CONDITION_STATUS)
            .diabetesStatus(UPDATED_DIABETES_STATUS)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .patientId(UPDATED_PATIENT_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .patientClinicalInformationUuid(UPDATED_PATIENT_CLINICAL_INFORMATION_UUID);
        return patientClinicalInformation;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(PatientClinicalInformation.class).block();
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
        patientClinicalInformation = createEntity(em);
    }

    @Test
    void createPatientClinicalInformation() throws Exception {
        int databaseSizeBeforeCreate = patientClinicalInformationRepository.findAll().collectList().block().size();
        // Create the PatientClinicalInformation
        PatientClinicalInformationDTO patientClinicalInformationDTO = patientClinicalInformationMapper.toDto(patientClinicalInformation);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientClinicalInformationDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the PatientClinicalInformation in the database
        List<PatientClinicalInformation> patientClinicalInformationList = patientClinicalInformationRepository
            .findAll()
            .collectList()
            .block();
        assertThat(patientClinicalInformationList).hasSize(databaseSizeBeforeCreate + 1);
        PatientClinicalInformation testPatientClinicalInformation = patientClinicalInformationList.get(
            patientClinicalInformationList.size() - 1
        );
        assertThat(testPatientClinicalInformation.getHeight()).isEqualTo(DEFAULT_HEIGHT);
        assertThat(testPatientClinicalInformation.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testPatientClinicalInformation.getFunctionalAbilities()).isEqualTo(DEFAULT_FUNCTIONAL_ABILITIES);
        assertThat(testPatientClinicalInformation.getCaptureDate()).isEqualTo(DEFAULT_CAPTURE_DATE);
        assertThat(testPatientClinicalInformation.getInfectionConditionStatus()).isEqualTo(DEFAULT_INFECTION_CONDITION_STATUS);
        assertThat(testPatientClinicalInformation.getDiabetesStatus()).isEqualTo(DEFAULT_DIABETES_STATUS);
        assertThat(testPatientClinicalInformation.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPatientClinicalInformation.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testPatientClinicalInformation.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testPatientClinicalInformation.getPatientId()).isEqualTo(DEFAULT_PATIENT_ID);
        assertThat(testPatientClinicalInformation.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testPatientClinicalInformation.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testPatientClinicalInformation.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testPatientClinicalInformation.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testPatientClinicalInformation.getPatientClinicalInformationUuid()).isEqualTo(DEFAULT_PATIENT_CLINICAL_INFORMATION_UUID);
    }

    @Test
    void createPatientClinicalInformationWithExistingId() throws Exception {
        // Create the PatientClinicalInformation with an existing ID
        patientClinicalInformation.setPatientClinicalInformationId(1L);
        PatientClinicalInformationDTO patientClinicalInformationDTO = patientClinicalInformationMapper.toDto(patientClinicalInformation);

        int databaseSizeBeforeCreate = patientClinicalInformationRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientClinicalInformationDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientClinicalInformation in the database
        List<PatientClinicalInformation> patientClinicalInformationList = patientClinicalInformationRepository
            .findAll()
            .collectList()
            .block();
        assertThat(patientClinicalInformationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllPatientClinicalInformations() {
        // Initialize the database
        patientClinicalInformationRepository.save(patientClinicalInformation).block();

        // Get all the patientClinicalInformationList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=patientClinicalInformationId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].patientClinicalInformationId")
            .value(hasItem(patientClinicalInformation.getPatientClinicalInformationId().intValue()))
            .jsonPath("$.[*].height")
            .value(hasItem(DEFAULT_HEIGHT.doubleValue()))
            .jsonPath("$.[*].weight")
            .value(hasItem(DEFAULT_WEIGHT.doubleValue()))
            .jsonPath("$.[*].functionalAbilities")
            .value(hasItem(DEFAULT_FUNCTIONAL_ABILITIES))
            .jsonPath("$.[*].captureDate")
            .value(hasItem(DEFAULT_CAPTURE_DATE.toString()))
            .jsonPath("$.[*].infectionConditionStatus")
            .value(hasItem(DEFAULT_INFECTION_CONDITION_STATUS))
            .jsonPath("$.[*].diabetesStatus")
            .value(hasItem(DEFAULT_DIABETES_STATUS))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS))
            .jsonPath("$.[*].createdById")
            .value(hasItem(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.[*].createdDate")
            .value(hasItem(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.[*].patientId")
            .value(hasItem(DEFAULT_PATIENT_ID.intValue()))
            .jsonPath("$.[*].createdByName")
            .value(hasItem(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.[*].updatedByName")
            .value(hasItem(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.[*].updatedById")
            .value(hasItem(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.[*].updatedDate")
            .value(hasItem(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.[*].patientClinicalInformationUuid")
            .value(hasItem(DEFAULT_PATIENT_CLINICAL_INFORMATION_UUID.toString()));
    }

    @Test
    void getPatientClinicalInformation() {
        // Initialize the database
        patientClinicalInformationRepository.save(patientClinicalInformation).block();

        // Get the patientClinicalInformation
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, patientClinicalInformation.getPatientClinicalInformationId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.patientClinicalInformationId")
            .value(is(patientClinicalInformation.getPatientClinicalInformationId().intValue()))
            .jsonPath("$.height")
            .value(is(DEFAULT_HEIGHT.doubleValue()))
            .jsonPath("$.weight")
            .value(is(DEFAULT_WEIGHT.doubleValue()))
            .jsonPath("$.functionalAbilities")
            .value(is(DEFAULT_FUNCTIONAL_ABILITIES))
            .jsonPath("$.captureDate")
            .value(is(DEFAULT_CAPTURE_DATE.toString()))
            .jsonPath("$.infectionConditionStatus")
            .value(is(DEFAULT_INFECTION_CONDITION_STATUS))
            .jsonPath("$.diabetesStatus")
            .value(is(DEFAULT_DIABETES_STATUS))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS))
            .jsonPath("$.createdById")
            .value(is(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.createdDate")
            .value(is(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.patientId")
            .value(is(DEFAULT_PATIENT_ID.intValue()))
            .jsonPath("$.createdByName")
            .value(is(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.updatedByName")
            .value(is(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.updatedById")
            .value(is(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.updatedDate")
            .value(is(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.patientClinicalInformationUuid")
            .value(is(DEFAULT_PATIENT_CLINICAL_INFORMATION_UUID.toString()));
    }

    @Test
    void getNonExistingPatientClinicalInformation() {
        // Get the patientClinicalInformation
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingPatientClinicalInformation() throws Exception {
        // Initialize the database
        patientClinicalInformationRepository.save(patientClinicalInformation).block();

        int databaseSizeBeforeUpdate = patientClinicalInformationRepository.findAll().collectList().block().size();

        // Update the patientClinicalInformation
        PatientClinicalInformation updatedPatientClinicalInformation = patientClinicalInformationRepository
            .findById(patientClinicalInformation.getPatientClinicalInformationId())
            .block();
        updatedPatientClinicalInformation
            .height(UPDATED_HEIGHT)
            .weight(UPDATED_WEIGHT)
            .functionalAbilities(UPDATED_FUNCTIONAL_ABILITIES)
            .captureDate(UPDATED_CAPTURE_DATE)
            .infectionConditionStatus(UPDATED_INFECTION_CONDITION_STATUS)
            .diabetesStatus(UPDATED_DIABETES_STATUS)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .patientId(UPDATED_PATIENT_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .patientClinicalInformationUuid(UPDATED_PATIENT_CLINICAL_INFORMATION_UUID);
        PatientClinicalInformationDTO patientClinicalInformationDTO = patientClinicalInformationMapper.toDto(
            updatedPatientClinicalInformation
        );

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, patientClinicalInformationDTO.getPatientClinicalInformationId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientClinicalInformationDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientClinicalInformation in the database
        List<PatientClinicalInformation> patientClinicalInformationList = patientClinicalInformationRepository
            .findAll()
            .collectList()
            .block();
        assertThat(patientClinicalInformationList).hasSize(databaseSizeBeforeUpdate);
        PatientClinicalInformation testPatientClinicalInformation = patientClinicalInformationList.get(
            patientClinicalInformationList.size() - 1
        );
        assertThat(testPatientClinicalInformation.getHeight()).isEqualTo(UPDATED_HEIGHT);
        assertThat(testPatientClinicalInformation.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testPatientClinicalInformation.getFunctionalAbilities()).isEqualTo(UPDATED_FUNCTIONAL_ABILITIES);
        assertThat(testPatientClinicalInformation.getCaptureDate()).isEqualTo(UPDATED_CAPTURE_DATE);
        assertThat(testPatientClinicalInformation.getInfectionConditionStatus()).isEqualTo(UPDATED_INFECTION_CONDITION_STATUS);
        assertThat(testPatientClinicalInformation.getDiabetesStatus()).isEqualTo(UPDATED_DIABETES_STATUS);
        assertThat(testPatientClinicalInformation.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPatientClinicalInformation.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPatientClinicalInformation.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPatientClinicalInformation.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testPatientClinicalInformation.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPatientClinicalInformation.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPatientClinicalInformation.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPatientClinicalInformation.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPatientClinicalInformation.getPatientClinicalInformationUuid()).isEqualTo(UPDATED_PATIENT_CLINICAL_INFORMATION_UUID);
    }

    @Test
    void putNonExistingPatientClinicalInformation() throws Exception {
        int databaseSizeBeforeUpdate = patientClinicalInformationRepository.findAll().collectList().block().size();
        patientClinicalInformation.setPatientClinicalInformationId(count.incrementAndGet());

        // Create the PatientClinicalInformation
        PatientClinicalInformationDTO patientClinicalInformationDTO = patientClinicalInformationMapper.toDto(patientClinicalInformation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, patientClinicalInformationDTO.getPatientClinicalInformationId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientClinicalInformationDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientClinicalInformation in the database
        List<PatientClinicalInformation> patientClinicalInformationList = patientClinicalInformationRepository
            .findAll()
            .collectList()
            .block();
        assertThat(patientClinicalInformationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchPatientClinicalInformation() throws Exception {
        int databaseSizeBeforeUpdate = patientClinicalInformationRepository.findAll().collectList().block().size();
        patientClinicalInformation.setPatientClinicalInformationId(count.incrementAndGet());

        // Create the PatientClinicalInformation
        PatientClinicalInformationDTO patientClinicalInformationDTO = patientClinicalInformationMapper.toDto(patientClinicalInformation);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientClinicalInformationDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientClinicalInformation in the database
        List<PatientClinicalInformation> patientClinicalInformationList = patientClinicalInformationRepository
            .findAll()
            .collectList()
            .block();
        assertThat(patientClinicalInformationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamPatientClinicalInformation() throws Exception {
        int databaseSizeBeforeUpdate = patientClinicalInformationRepository.findAll().collectList().block().size();
        patientClinicalInformation.setPatientClinicalInformationId(count.incrementAndGet());

        // Create the PatientClinicalInformation
        PatientClinicalInformationDTO patientClinicalInformationDTO = patientClinicalInformationMapper.toDto(patientClinicalInformation);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientClinicalInformationDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PatientClinicalInformation in the database
        List<PatientClinicalInformation> patientClinicalInformationList = patientClinicalInformationRepository
            .findAll()
            .collectList()
            .block();
        assertThat(patientClinicalInformationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdatePatientClinicalInformationWithPatch() throws Exception {
        // Initialize the database
        patientClinicalInformationRepository.save(patientClinicalInformation).block();

        int databaseSizeBeforeUpdate = patientClinicalInformationRepository.findAll().collectList().block().size();

        // Update the patientClinicalInformation using partial update
        PatientClinicalInformation partialUpdatedPatientClinicalInformation = new PatientClinicalInformation();
        partialUpdatedPatientClinicalInformation.setPatientClinicalInformationId(
            patientClinicalInformation.getPatientClinicalInformationId()
        );

        partialUpdatedPatientClinicalInformation
            .weight(UPDATED_WEIGHT)
            .functionalAbilities(UPDATED_FUNCTIONAL_ABILITIES)
            .captureDate(UPDATED_CAPTURE_DATE)
            .infectionConditionStatus(UPDATED_INFECTION_CONDITION_STATUS)
            .diabetesStatus(UPDATED_DIABETES_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .patientId(UPDATED_PATIENT_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .patientClinicalInformationUuid(UPDATED_PATIENT_CLINICAL_INFORMATION_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPatientClinicalInformation.getPatientClinicalInformationId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPatientClinicalInformation))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientClinicalInformation in the database
        List<PatientClinicalInformation> patientClinicalInformationList = patientClinicalInformationRepository
            .findAll()
            .collectList()
            .block();
        assertThat(patientClinicalInformationList).hasSize(databaseSizeBeforeUpdate);
        PatientClinicalInformation testPatientClinicalInformation = patientClinicalInformationList.get(
            patientClinicalInformationList.size() - 1
        );
        assertThat(testPatientClinicalInformation.getHeight()).isEqualTo(DEFAULT_HEIGHT);
        assertThat(testPatientClinicalInformation.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testPatientClinicalInformation.getFunctionalAbilities()).isEqualTo(UPDATED_FUNCTIONAL_ABILITIES);
        assertThat(testPatientClinicalInformation.getCaptureDate()).isEqualTo(UPDATED_CAPTURE_DATE);
        assertThat(testPatientClinicalInformation.getInfectionConditionStatus()).isEqualTo(UPDATED_INFECTION_CONDITION_STATUS);
        assertThat(testPatientClinicalInformation.getDiabetesStatus()).isEqualTo(UPDATED_DIABETES_STATUS);
        assertThat(testPatientClinicalInformation.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPatientClinicalInformation.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPatientClinicalInformation.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testPatientClinicalInformation.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testPatientClinicalInformation.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPatientClinicalInformation.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testPatientClinicalInformation.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPatientClinicalInformation.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testPatientClinicalInformation.getPatientClinicalInformationUuid()).isEqualTo(UPDATED_PATIENT_CLINICAL_INFORMATION_UUID);
    }

    @Test
    void fullUpdatePatientClinicalInformationWithPatch() throws Exception {
        // Initialize the database
        patientClinicalInformationRepository.save(patientClinicalInformation).block();

        int databaseSizeBeforeUpdate = patientClinicalInformationRepository.findAll().collectList().block().size();

        // Update the patientClinicalInformation using partial update
        PatientClinicalInformation partialUpdatedPatientClinicalInformation = new PatientClinicalInformation();
        partialUpdatedPatientClinicalInformation.setPatientClinicalInformationId(
            patientClinicalInformation.getPatientClinicalInformationId()
        );

        partialUpdatedPatientClinicalInformation
            .height(UPDATED_HEIGHT)
            .weight(UPDATED_WEIGHT)
            .functionalAbilities(UPDATED_FUNCTIONAL_ABILITIES)
            .captureDate(UPDATED_CAPTURE_DATE)
            .infectionConditionStatus(UPDATED_INFECTION_CONDITION_STATUS)
            .diabetesStatus(UPDATED_DIABETES_STATUS)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .patientId(UPDATED_PATIENT_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .patientClinicalInformationUuid(UPDATED_PATIENT_CLINICAL_INFORMATION_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPatientClinicalInformation.getPatientClinicalInformationId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPatientClinicalInformation))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientClinicalInformation in the database
        List<PatientClinicalInformation> patientClinicalInformationList = patientClinicalInformationRepository
            .findAll()
            .collectList()
            .block();
        assertThat(patientClinicalInformationList).hasSize(databaseSizeBeforeUpdate);
        PatientClinicalInformation testPatientClinicalInformation = patientClinicalInformationList.get(
            patientClinicalInformationList.size() - 1
        );
        assertThat(testPatientClinicalInformation.getHeight()).isEqualTo(UPDATED_HEIGHT);
        assertThat(testPatientClinicalInformation.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testPatientClinicalInformation.getFunctionalAbilities()).isEqualTo(UPDATED_FUNCTIONAL_ABILITIES);
        assertThat(testPatientClinicalInformation.getCaptureDate()).isEqualTo(UPDATED_CAPTURE_DATE);
        assertThat(testPatientClinicalInformation.getInfectionConditionStatus()).isEqualTo(UPDATED_INFECTION_CONDITION_STATUS);
        assertThat(testPatientClinicalInformation.getDiabetesStatus()).isEqualTo(UPDATED_DIABETES_STATUS);
        assertThat(testPatientClinicalInformation.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPatientClinicalInformation.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPatientClinicalInformation.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPatientClinicalInformation.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testPatientClinicalInformation.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPatientClinicalInformation.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPatientClinicalInformation.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPatientClinicalInformation.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPatientClinicalInformation.getPatientClinicalInformationUuid()).isEqualTo(UPDATED_PATIENT_CLINICAL_INFORMATION_UUID);
    }

    @Test
    void patchNonExistingPatientClinicalInformation() throws Exception {
        int databaseSizeBeforeUpdate = patientClinicalInformationRepository.findAll().collectList().block().size();
        patientClinicalInformation.setPatientClinicalInformationId(count.incrementAndGet());

        // Create the PatientClinicalInformation
        PatientClinicalInformationDTO patientClinicalInformationDTO = patientClinicalInformationMapper.toDto(patientClinicalInformation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, patientClinicalInformationDTO.getPatientClinicalInformationId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientClinicalInformationDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientClinicalInformation in the database
        List<PatientClinicalInformation> patientClinicalInformationList = patientClinicalInformationRepository
            .findAll()
            .collectList()
            .block();
        assertThat(patientClinicalInformationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchPatientClinicalInformation() throws Exception {
        int databaseSizeBeforeUpdate = patientClinicalInformationRepository.findAll().collectList().block().size();
        patientClinicalInformation.setPatientClinicalInformationId(count.incrementAndGet());

        // Create the PatientClinicalInformation
        PatientClinicalInformationDTO patientClinicalInformationDTO = patientClinicalInformationMapper.toDto(patientClinicalInformation);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientClinicalInformationDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientClinicalInformation in the database
        List<PatientClinicalInformation> patientClinicalInformationList = patientClinicalInformationRepository
            .findAll()
            .collectList()
            .block();
        assertThat(patientClinicalInformationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamPatientClinicalInformation() throws Exception {
        int databaseSizeBeforeUpdate = patientClinicalInformationRepository.findAll().collectList().block().size();
        patientClinicalInformation.setPatientClinicalInformationId(count.incrementAndGet());

        // Create the PatientClinicalInformation
        PatientClinicalInformationDTO patientClinicalInformationDTO = patientClinicalInformationMapper.toDto(patientClinicalInformation);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientClinicalInformationDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PatientClinicalInformation in the database
        List<PatientClinicalInformation> patientClinicalInformationList = patientClinicalInformationRepository
            .findAll()
            .collectList()
            .block();
        assertThat(patientClinicalInformationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deletePatientClinicalInformation() {
        // Initialize the database
        patientClinicalInformationRepository.save(patientClinicalInformation).block();

        int databaseSizeBeforeDelete = patientClinicalInformationRepository.findAll().collectList().block().size();

        // Delete the patientClinicalInformation
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, patientClinicalInformation.getPatientClinicalInformationId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<PatientClinicalInformation> patientClinicalInformationList = patientClinicalInformationRepository
            .findAll()
            .collectList()
            .block();
        assertThat(patientClinicalInformationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
