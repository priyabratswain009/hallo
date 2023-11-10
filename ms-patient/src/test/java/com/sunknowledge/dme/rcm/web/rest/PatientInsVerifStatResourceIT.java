package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.PatientInsVerifStat;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.PatientInsVerifStatRepository;
import com.sunknowledge.dme.rcm.service.dto.PatientInsVerifStatDTO;
import com.sunknowledge.dme.rcm.service.mapper.PatientInsVerifStatMapper;
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
 * Integration tests for the {@link PatientInsVerifStatResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class PatientInsVerifStatResourceIT {

    private static final Long DEFAULT_PATIENT_INSURANCE_ID = 1L;
    private static final Long UPDATED_PATIENT_INSURANCE_ID = 2L;

    private static final LocalDate DEFAULT_ELLIGIBILITY_CHECK_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ELLIGIBILITY_CHECK_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ELLIGIBILITY_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_ELLIGIBILITY_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_ELLIGIBILITY_CHECK_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_ELLIGIBILITY_CHECK_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_PERIOD_YEAR = "AAAAAAAAAA";
    private static final String UPDATED_PERIOD_YEAR = "BBBBBBBBBB";

    private static final String DEFAULT_COVERAGE_INFO = "AAAAAAAAAA";
    private static final String UPDATED_COVERAGE_INFO = "BBBBBBBBBB";

    private static final String DEFAULT_NETWORK_INFO = "AAAAAAAAAA";
    private static final String UPDATED_NETWORK_INFO = "BBBBBBBBBB";

    private static final Double DEFAULT_DEDUCTABLE_AMT = 1D;
    private static final Double UPDATED_DEDUCTABLE_AMT = 2D;

    private static final Double DEFAULT_REMAINING_AMT = 1D;
    private static final Double UPDATED_REMAINING_AMT = 2D;

    private static final String DEFAULT_COINSURANCE_OR_COPAY = "AAAAAAAAAA";
    private static final String UPDATED_COINSURANCE_OR_COPAY = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final UUID DEFAULT_PATIENT_INS_VERIF_STAT_UUID = UUID.randomUUID();
    private static final UUID UPDATED_PATIENT_INS_VERIF_STAT_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/patient-ins-verif-stats";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{insuranceVerifId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PatientInsVerifStatRepository patientInsVerifStatRepository;

    @Autowired
    private PatientInsVerifStatMapper patientInsVerifStatMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private PatientInsVerifStat patientInsVerifStat;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PatientInsVerifStat createEntity(EntityManager em) {
        PatientInsVerifStat patientInsVerifStat = new PatientInsVerifStat()
            .patientInsuranceId(DEFAULT_PATIENT_INSURANCE_ID)
            .elligibilityCheckDate(DEFAULT_ELLIGIBILITY_CHECK_DATE)
            .elligibilityStatus(DEFAULT_ELLIGIBILITY_STATUS)
            .elligibilityCheckType(DEFAULT_ELLIGIBILITY_CHECK_TYPE)
            .periodYear(DEFAULT_PERIOD_YEAR)
            .coverageInfo(DEFAULT_COVERAGE_INFO)
            .networkInfo(DEFAULT_NETWORK_INFO)
            .deductableAmt(DEFAULT_DEDUCTABLE_AMT)
            .remainingAmt(DEFAULT_REMAINING_AMT)
            .coinsuranceOrCopay(DEFAULT_COINSURANCE_OR_COPAY)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .patientInsVerifStatUuid(DEFAULT_PATIENT_INS_VERIF_STAT_UUID);
        return patientInsVerifStat;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PatientInsVerifStat createUpdatedEntity(EntityManager em) {
        PatientInsVerifStat patientInsVerifStat = new PatientInsVerifStat()
            .patientInsuranceId(UPDATED_PATIENT_INSURANCE_ID)
            .elligibilityCheckDate(UPDATED_ELLIGIBILITY_CHECK_DATE)
            .elligibilityStatus(UPDATED_ELLIGIBILITY_STATUS)
            .elligibilityCheckType(UPDATED_ELLIGIBILITY_CHECK_TYPE)
            .periodYear(UPDATED_PERIOD_YEAR)
            .coverageInfo(UPDATED_COVERAGE_INFO)
            .networkInfo(UPDATED_NETWORK_INFO)
            .deductableAmt(UPDATED_DEDUCTABLE_AMT)
            .remainingAmt(UPDATED_REMAINING_AMT)
            .coinsuranceOrCopay(UPDATED_COINSURANCE_OR_COPAY)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .patientInsVerifStatUuid(UPDATED_PATIENT_INS_VERIF_STAT_UUID);
        return patientInsVerifStat;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(PatientInsVerifStat.class).block();
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
        patientInsVerifStat = createEntity(em);
    }

    @Test
    void createPatientInsVerifStat() throws Exception {
        int databaseSizeBeforeCreate = patientInsVerifStatRepository.findAll().collectList().block().size();
        // Create the PatientInsVerifStat
        PatientInsVerifStatDTO patientInsVerifStatDTO = patientInsVerifStatMapper.toDto(patientInsVerifStat);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientInsVerifStatDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the PatientInsVerifStat in the database
        List<PatientInsVerifStat> patientInsVerifStatList = patientInsVerifStatRepository.findAll().collectList().block();
        assertThat(patientInsVerifStatList).hasSize(databaseSizeBeforeCreate + 1);
        PatientInsVerifStat testPatientInsVerifStat = patientInsVerifStatList.get(patientInsVerifStatList.size() - 1);
        assertThat(testPatientInsVerifStat.getPatientInsuranceId()).isEqualTo(DEFAULT_PATIENT_INSURANCE_ID);
        assertThat(testPatientInsVerifStat.getElligibilityCheckDate()).isEqualTo(DEFAULT_ELLIGIBILITY_CHECK_DATE);
        assertThat(testPatientInsVerifStat.getElligibilityStatus()).isEqualTo(DEFAULT_ELLIGIBILITY_STATUS);
        assertThat(testPatientInsVerifStat.getElligibilityCheckType()).isEqualTo(DEFAULT_ELLIGIBILITY_CHECK_TYPE);
        assertThat(testPatientInsVerifStat.getPeriodYear()).isEqualTo(DEFAULT_PERIOD_YEAR);
        assertThat(testPatientInsVerifStat.getCoverageInfo()).isEqualTo(DEFAULT_COVERAGE_INFO);
        assertThat(testPatientInsVerifStat.getNetworkInfo()).isEqualTo(DEFAULT_NETWORK_INFO);
        assertThat(testPatientInsVerifStat.getDeductableAmt()).isEqualTo(DEFAULT_DEDUCTABLE_AMT);
        assertThat(testPatientInsVerifStat.getRemainingAmt()).isEqualTo(DEFAULT_REMAINING_AMT);
        assertThat(testPatientInsVerifStat.getCoinsuranceOrCopay()).isEqualTo(DEFAULT_COINSURANCE_OR_COPAY);
        assertThat(testPatientInsVerifStat.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPatientInsVerifStat.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testPatientInsVerifStat.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testPatientInsVerifStat.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testPatientInsVerifStat.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testPatientInsVerifStat.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testPatientInsVerifStat.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testPatientInsVerifStat.getPatientInsVerifStatUuid()).isEqualTo(DEFAULT_PATIENT_INS_VERIF_STAT_UUID);
    }

    @Test
    void createPatientInsVerifStatWithExistingId() throws Exception {
        // Create the PatientInsVerifStat with an existing ID
        patientInsVerifStat.setInsuranceVerifId(1L);
        PatientInsVerifStatDTO patientInsVerifStatDTO = patientInsVerifStatMapper.toDto(patientInsVerifStat);

        int databaseSizeBeforeCreate = patientInsVerifStatRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientInsVerifStatDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientInsVerifStat in the database
        List<PatientInsVerifStat> patientInsVerifStatList = patientInsVerifStatRepository.findAll().collectList().block();
        assertThat(patientInsVerifStatList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllPatientInsVerifStats() {
        // Initialize the database
        patientInsVerifStatRepository.save(patientInsVerifStat).block();

        // Get all the patientInsVerifStatList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=insuranceVerifId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].insuranceVerifId")
            .value(hasItem(patientInsVerifStat.getInsuranceVerifId().intValue()))
            .jsonPath("$.[*].patientInsuranceId")
            .value(hasItem(DEFAULT_PATIENT_INSURANCE_ID.intValue()))
            .jsonPath("$.[*].elligibilityCheckDate")
            .value(hasItem(DEFAULT_ELLIGIBILITY_CHECK_DATE.toString()))
            .jsonPath("$.[*].elligibilityStatus")
            .value(hasItem(DEFAULT_ELLIGIBILITY_STATUS))
            .jsonPath("$.[*].elligibilityCheckType")
            .value(hasItem(DEFAULT_ELLIGIBILITY_CHECK_TYPE))
            .jsonPath("$.[*].periodYear")
            .value(hasItem(DEFAULT_PERIOD_YEAR))
            .jsonPath("$.[*].coverageInfo")
            .value(hasItem(DEFAULT_COVERAGE_INFO))
            .jsonPath("$.[*].networkInfo")
            .value(hasItem(DEFAULT_NETWORK_INFO))
            .jsonPath("$.[*].deductableAmt")
            .value(hasItem(DEFAULT_DEDUCTABLE_AMT.doubleValue()))
            .jsonPath("$.[*].remainingAmt")
            .value(hasItem(DEFAULT_REMAINING_AMT.doubleValue()))
            .jsonPath("$.[*].coinsuranceOrCopay")
            .value(hasItem(DEFAULT_COINSURANCE_OR_COPAY))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS))
            .jsonPath("$.[*].createdById")
            .value(hasItem(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.[*].createdDate")
            .value(hasItem(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.[*].updatedDate")
            .value(hasItem(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.[*].createdByName")
            .value(hasItem(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.[*].updatedByName")
            .value(hasItem(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.[*].updatedById")
            .value(hasItem(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.[*].patientInsVerifStatUuid")
            .value(hasItem(DEFAULT_PATIENT_INS_VERIF_STAT_UUID.toString()));
    }

    @Test
    void getPatientInsVerifStat() {
        // Initialize the database
        patientInsVerifStatRepository.save(patientInsVerifStat).block();

        // Get the patientInsVerifStat
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, patientInsVerifStat.getInsuranceVerifId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.insuranceVerifId")
            .value(is(patientInsVerifStat.getInsuranceVerifId().intValue()))
            .jsonPath("$.patientInsuranceId")
            .value(is(DEFAULT_PATIENT_INSURANCE_ID.intValue()))
            .jsonPath("$.elligibilityCheckDate")
            .value(is(DEFAULT_ELLIGIBILITY_CHECK_DATE.toString()))
            .jsonPath("$.elligibilityStatus")
            .value(is(DEFAULT_ELLIGIBILITY_STATUS))
            .jsonPath("$.elligibilityCheckType")
            .value(is(DEFAULT_ELLIGIBILITY_CHECK_TYPE))
            .jsonPath("$.periodYear")
            .value(is(DEFAULT_PERIOD_YEAR))
            .jsonPath("$.coverageInfo")
            .value(is(DEFAULT_COVERAGE_INFO))
            .jsonPath("$.networkInfo")
            .value(is(DEFAULT_NETWORK_INFO))
            .jsonPath("$.deductableAmt")
            .value(is(DEFAULT_DEDUCTABLE_AMT.doubleValue()))
            .jsonPath("$.remainingAmt")
            .value(is(DEFAULT_REMAINING_AMT.doubleValue()))
            .jsonPath("$.coinsuranceOrCopay")
            .value(is(DEFAULT_COINSURANCE_OR_COPAY))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS))
            .jsonPath("$.createdById")
            .value(is(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.createdDate")
            .value(is(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.updatedDate")
            .value(is(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.createdByName")
            .value(is(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.updatedByName")
            .value(is(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.updatedById")
            .value(is(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.patientInsVerifStatUuid")
            .value(is(DEFAULT_PATIENT_INS_VERIF_STAT_UUID.toString()));
    }

    @Test
    void getNonExistingPatientInsVerifStat() {
        // Get the patientInsVerifStat
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingPatientInsVerifStat() throws Exception {
        // Initialize the database
        patientInsVerifStatRepository.save(patientInsVerifStat).block();

        int databaseSizeBeforeUpdate = patientInsVerifStatRepository.findAll().collectList().block().size();

        // Update the patientInsVerifStat
        PatientInsVerifStat updatedPatientInsVerifStat = patientInsVerifStatRepository
            .findById(patientInsVerifStat.getInsuranceVerifId())
            .block();
        updatedPatientInsVerifStat
            .patientInsuranceId(UPDATED_PATIENT_INSURANCE_ID)
            .elligibilityCheckDate(UPDATED_ELLIGIBILITY_CHECK_DATE)
            .elligibilityStatus(UPDATED_ELLIGIBILITY_STATUS)
            .elligibilityCheckType(UPDATED_ELLIGIBILITY_CHECK_TYPE)
            .periodYear(UPDATED_PERIOD_YEAR)
            .coverageInfo(UPDATED_COVERAGE_INFO)
            .networkInfo(UPDATED_NETWORK_INFO)
            .deductableAmt(UPDATED_DEDUCTABLE_AMT)
            .remainingAmt(UPDATED_REMAINING_AMT)
            .coinsuranceOrCopay(UPDATED_COINSURANCE_OR_COPAY)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .patientInsVerifStatUuid(UPDATED_PATIENT_INS_VERIF_STAT_UUID);
        PatientInsVerifStatDTO patientInsVerifStatDTO = patientInsVerifStatMapper.toDto(updatedPatientInsVerifStat);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, patientInsVerifStatDTO.getInsuranceVerifId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientInsVerifStatDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientInsVerifStat in the database
        List<PatientInsVerifStat> patientInsVerifStatList = patientInsVerifStatRepository.findAll().collectList().block();
        assertThat(patientInsVerifStatList).hasSize(databaseSizeBeforeUpdate);
        PatientInsVerifStat testPatientInsVerifStat = patientInsVerifStatList.get(patientInsVerifStatList.size() - 1);
        assertThat(testPatientInsVerifStat.getPatientInsuranceId()).isEqualTo(UPDATED_PATIENT_INSURANCE_ID);
        assertThat(testPatientInsVerifStat.getElligibilityCheckDate()).isEqualTo(UPDATED_ELLIGIBILITY_CHECK_DATE);
        assertThat(testPatientInsVerifStat.getElligibilityStatus()).isEqualTo(UPDATED_ELLIGIBILITY_STATUS);
        assertThat(testPatientInsVerifStat.getElligibilityCheckType()).isEqualTo(UPDATED_ELLIGIBILITY_CHECK_TYPE);
        assertThat(testPatientInsVerifStat.getPeriodYear()).isEqualTo(UPDATED_PERIOD_YEAR);
        assertThat(testPatientInsVerifStat.getCoverageInfo()).isEqualTo(UPDATED_COVERAGE_INFO);
        assertThat(testPatientInsVerifStat.getNetworkInfo()).isEqualTo(UPDATED_NETWORK_INFO);
        assertThat(testPatientInsVerifStat.getDeductableAmt()).isEqualTo(UPDATED_DEDUCTABLE_AMT);
        assertThat(testPatientInsVerifStat.getRemainingAmt()).isEqualTo(UPDATED_REMAINING_AMT);
        assertThat(testPatientInsVerifStat.getCoinsuranceOrCopay()).isEqualTo(UPDATED_COINSURANCE_OR_COPAY);
        assertThat(testPatientInsVerifStat.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPatientInsVerifStat.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPatientInsVerifStat.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPatientInsVerifStat.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPatientInsVerifStat.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPatientInsVerifStat.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPatientInsVerifStat.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPatientInsVerifStat.getPatientInsVerifStatUuid()).isEqualTo(UPDATED_PATIENT_INS_VERIF_STAT_UUID);
    }

    @Test
    void putNonExistingPatientInsVerifStat() throws Exception {
        int databaseSizeBeforeUpdate = patientInsVerifStatRepository.findAll().collectList().block().size();
        patientInsVerifStat.setInsuranceVerifId(count.incrementAndGet());

        // Create the PatientInsVerifStat
        PatientInsVerifStatDTO patientInsVerifStatDTO = patientInsVerifStatMapper.toDto(patientInsVerifStat);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, patientInsVerifStatDTO.getInsuranceVerifId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientInsVerifStatDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientInsVerifStat in the database
        List<PatientInsVerifStat> patientInsVerifStatList = patientInsVerifStatRepository.findAll().collectList().block();
        assertThat(patientInsVerifStatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchPatientInsVerifStat() throws Exception {
        int databaseSizeBeforeUpdate = patientInsVerifStatRepository.findAll().collectList().block().size();
        patientInsVerifStat.setInsuranceVerifId(count.incrementAndGet());

        // Create the PatientInsVerifStat
        PatientInsVerifStatDTO patientInsVerifStatDTO = patientInsVerifStatMapper.toDto(patientInsVerifStat);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientInsVerifStatDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientInsVerifStat in the database
        List<PatientInsVerifStat> patientInsVerifStatList = patientInsVerifStatRepository.findAll().collectList().block();
        assertThat(patientInsVerifStatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamPatientInsVerifStat() throws Exception {
        int databaseSizeBeforeUpdate = patientInsVerifStatRepository.findAll().collectList().block().size();
        patientInsVerifStat.setInsuranceVerifId(count.incrementAndGet());

        // Create the PatientInsVerifStat
        PatientInsVerifStatDTO patientInsVerifStatDTO = patientInsVerifStatMapper.toDto(patientInsVerifStat);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientInsVerifStatDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PatientInsVerifStat in the database
        List<PatientInsVerifStat> patientInsVerifStatList = patientInsVerifStatRepository.findAll().collectList().block();
        assertThat(patientInsVerifStatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdatePatientInsVerifStatWithPatch() throws Exception {
        // Initialize the database
        patientInsVerifStatRepository.save(patientInsVerifStat).block();

        int databaseSizeBeforeUpdate = patientInsVerifStatRepository.findAll().collectList().block().size();

        // Update the patientInsVerifStat using partial update
        PatientInsVerifStat partialUpdatedPatientInsVerifStat = new PatientInsVerifStat();
        partialUpdatedPatientInsVerifStat.setInsuranceVerifId(patientInsVerifStat.getInsuranceVerifId());

        partialUpdatedPatientInsVerifStat
            .patientInsuranceId(UPDATED_PATIENT_INSURANCE_ID)
            .elligibilityCheckDate(UPDATED_ELLIGIBILITY_CHECK_DATE)
            .elligibilityStatus(UPDATED_ELLIGIBILITY_STATUS)
            .periodYear(UPDATED_PERIOD_YEAR)
            .networkInfo(UPDATED_NETWORK_INFO)
            .remainingAmt(UPDATED_REMAINING_AMT)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .patientInsVerifStatUuid(UPDATED_PATIENT_INS_VERIF_STAT_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPatientInsVerifStat.getInsuranceVerifId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPatientInsVerifStat))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientInsVerifStat in the database
        List<PatientInsVerifStat> patientInsVerifStatList = patientInsVerifStatRepository.findAll().collectList().block();
        assertThat(patientInsVerifStatList).hasSize(databaseSizeBeforeUpdate);
        PatientInsVerifStat testPatientInsVerifStat = patientInsVerifStatList.get(patientInsVerifStatList.size() - 1);
        assertThat(testPatientInsVerifStat.getPatientInsuranceId()).isEqualTo(UPDATED_PATIENT_INSURANCE_ID);
        assertThat(testPatientInsVerifStat.getElligibilityCheckDate()).isEqualTo(UPDATED_ELLIGIBILITY_CHECK_DATE);
        assertThat(testPatientInsVerifStat.getElligibilityStatus()).isEqualTo(UPDATED_ELLIGIBILITY_STATUS);
        assertThat(testPatientInsVerifStat.getElligibilityCheckType()).isEqualTo(DEFAULT_ELLIGIBILITY_CHECK_TYPE);
        assertThat(testPatientInsVerifStat.getPeriodYear()).isEqualTo(UPDATED_PERIOD_YEAR);
        assertThat(testPatientInsVerifStat.getCoverageInfo()).isEqualTo(DEFAULT_COVERAGE_INFO);
        assertThat(testPatientInsVerifStat.getNetworkInfo()).isEqualTo(UPDATED_NETWORK_INFO);
        assertThat(testPatientInsVerifStat.getDeductableAmt()).isEqualTo(DEFAULT_DEDUCTABLE_AMT);
        assertThat(testPatientInsVerifStat.getRemainingAmt()).isEqualTo(UPDATED_REMAINING_AMT);
        assertThat(testPatientInsVerifStat.getCoinsuranceOrCopay()).isEqualTo(DEFAULT_COINSURANCE_OR_COPAY);
        assertThat(testPatientInsVerifStat.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPatientInsVerifStat.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPatientInsVerifStat.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPatientInsVerifStat.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testPatientInsVerifStat.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPatientInsVerifStat.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPatientInsVerifStat.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testPatientInsVerifStat.getPatientInsVerifStatUuid()).isEqualTo(UPDATED_PATIENT_INS_VERIF_STAT_UUID);
    }

    @Test
    void fullUpdatePatientInsVerifStatWithPatch() throws Exception {
        // Initialize the database
        patientInsVerifStatRepository.save(patientInsVerifStat).block();

        int databaseSizeBeforeUpdate = patientInsVerifStatRepository.findAll().collectList().block().size();

        // Update the patientInsVerifStat using partial update
        PatientInsVerifStat partialUpdatedPatientInsVerifStat = new PatientInsVerifStat();
        partialUpdatedPatientInsVerifStat.setInsuranceVerifId(patientInsVerifStat.getInsuranceVerifId());

        partialUpdatedPatientInsVerifStat
            .patientInsuranceId(UPDATED_PATIENT_INSURANCE_ID)
            .elligibilityCheckDate(UPDATED_ELLIGIBILITY_CHECK_DATE)
            .elligibilityStatus(UPDATED_ELLIGIBILITY_STATUS)
            .elligibilityCheckType(UPDATED_ELLIGIBILITY_CHECK_TYPE)
            .periodYear(UPDATED_PERIOD_YEAR)
            .coverageInfo(UPDATED_COVERAGE_INFO)
            .networkInfo(UPDATED_NETWORK_INFO)
            .deductableAmt(UPDATED_DEDUCTABLE_AMT)
            .remainingAmt(UPDATED_REMAINING_AMT)
            .coinsuranceOrCopay(UPDATED_COINSURANCE_OR_COPAY)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .patientInsVerifStatUuid(UPDATED_PATIENT_INS_VERIF_STAT_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPatientInsVerifStat.getInsuranceVerifId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPatientInsVerifStat))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientInsVerifStat in the database
        List<PatientInsVerifStat> patientInsVerifStatList = patientInsVerifStatRepository.findAll().collectList().block();
        assertThat(patientInsVerifStatList).hasSize(databaseSizeBeforeUpdate);
        PatientInsVerifStat testPatientInsVerifStat = patientInsVerifStatList.get(patientInsVerifStatList.size() - 1);
        assertThat(testPatientInsVerifStat.getPatientInsuranceId()).isEqualTo(UPDATED_PATIENT_INSURANCE_ID);
        assertThat(testPatientInsVerifStat.getElligibilityCheckDate()).isEqualTo(UPDATED_ELLIGIBILITY_CHECK_DATE);
        assertThat(testPatientInsVerifStat.getElligibilityStatus()).isEqualTo(UPDATED_ELLIGIBILITY_STATUS);
        assertThat(testPatientInsVerifStat.getElligibilityCheckType()).isEqualTo(UPDATED_ELLIGIBILITY_CHECK_TYPE);
        assertThat(testPatientInsVerifStat.getPeriodYear()).isEqualTo(UPDATED_PERIOD_YEAR);
        assertThat(testPatientInsVerifStat.getCoverageInfo()).isEqualTo(UPDATED_COVERAGE_INFO);
        assertThat(testPatientInsVerifStat.getNetworkInfo()).isEqualTo(UPDATED_NETWORK_INFO);
        assertThat(testPatientInsVerifStat.getDeductableAmt()).isEqualTo(UPDATED_DEDUCTABLE_AMT);
        assertThat(testPatientInsVerifStat.getRemainingAmt()).isEqualTo(UPDATED_REMAINING_AMT);
        assertThat(testPatientInsVerifStat.getCoinsuranceOrCopay()).isEqualTo(UPDATED_COINSURANCE_OR_COPAY);
        assertThat(testPatientInsVerifStat.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPatientInsVerifStat.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPatientInsVerifStat.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPatientInsVerifStat.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPatientInsVerifStat.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPatientInsVerifStat.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPatientInsVerifStat.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPatientInsVerifStat.getPatientInsVerifStatUuid()).isEqualTo(UPDATED_PATIENT_INS_VERIF_STAT_UUID);
    }

    @Test
    void patchNonExistingPatientInsVerifStat() throws Exception {
        int databaseSizeBeforeUpdate = patientInsVerifStatRepository.findAll().collectList().block().size();
        patientInsVerifStat.setInsuranceVerifId(count.incrementAndGet());

        // Create the PatientInsVerifStat
        PatientInsVerifStatDTO patientInsVerifStatDTO = patientInsVerifStatMapper.toDto(patientInsVerifStat);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, patientInsVerifStatDTO.getInsuranceVerifId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientInsVerifStatDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientInsVerifStat in the database
        List<PatientInsVerifStat> patientInsVerifStatList = patientInsVerifStatRepository.findAll().collectList().block();
        assertThat(patientInsVerifStatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchPatientInsVerifStat() throws Exception {
        int databaseSizeBeforeUpdate = patientInsVerifStatRepository.findAll().collectList().block().size();
        patientInsVerifStat.setInsuranceVerifId(count.incrementAndGet());

        // Create the PatientInsVerifStat
        PatientInsVerifStatDTO patientInsVerifStatDTO = patientInsVerifStatMapper.toDto(patientInsVerifStat);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientInsVerifStatDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientInsVerifStat in the database
        List<PatientInsVerifStat> patientInsVerifStatList = patientInsVerifStatRepository.findAll().collectList().block();
        assertThat(patientInsVerifStatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamPatientInsVerifStat() throws Exception {
        int databaseSizeBeforeUpdate = patientInsVerifStatRepository.findAll().collectList().block().size();
        patientInsVerifStat.setInsuranceVerifId(count.incrementAndGet());

        // Create the PatientInsVerifStat
        PatientInsVerifStatDTO patientInsVerifStatDTO = patientInsVerifStatMapper.toDto(patientInsVerifStat);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientInsVerifStatDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PatientInsVerifStat in the database
        List<PatientInsVerifStat> patientInsVerifStatList = patientInsVerifStatRepository.findAll().collectList().block();
        assertThat(patientInsVerifStatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deletePatientInsVerifStat() {
        // Initialize the database
        patientInsVerifStatRepository.save(patientInsVerifStat).block();

        int databaseSizeBeforeDelete = patientInsVerifStatRepository.findAll().collectList().block().size();

        // Delete the patientInsVerifStat
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, patientInsVerifStat.getInsuranceVerifId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<PatientInsVerifStat> patientInsVerifStatList = patientInsVerifStatRepository.findAll().collectList().block();
        assertThat(patientInsVerifStatList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
