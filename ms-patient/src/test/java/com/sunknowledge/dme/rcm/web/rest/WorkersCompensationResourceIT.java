package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.WorkersCompensation;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.WorkersCompensationRepository;
import com.sunknowledge.dme.rcm.service.dto.WorkersCompensationDTO;
import com.sunknowledge.dme.rcm.service.mapper.WorkersCompensationMapper;
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
 * Integration tests for the {@link WorkersCompensationResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class WorkersCompensationResourceIT {

    private static final Long DEFAULT_PATIENT_ID = 1L;
    private static final Long UPDATED_PATIENT_ID = 2L;

    private static final String DEFAULT_INSURED_EMPLOYER = "AAAAAAAAAA";
    private static final String UPDATED_INSURED_EMPLOYER = "BBBBBBBBBB";

    private static final String DEFAULT_WORKERS_COMPENSATION_PAYER_ID_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_WORKERS_COMPENSATION_PAYER_ID_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_WORKERS_COMPENSATION_PLAN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_WORKERS_COMPENSATION_PLAN_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_WORKERS_COMPENSATION_ADDITIONAL_DTLS = "AAAAAAAAAA";
    private static final String UPDATED_WORKERS_COMPENSATION_ADDITIONAL_DTLS = "BBBBBBBBBB";

    private static final String DEFAULT_WORKERS_COMPENSATION_CLAIM_FILLING_CODE = "AAAAAAAAAA";
    private static final String UPDATED_WORKERS_COMPENSATION_CLAIM_FILLING_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_WORKERS_COMPENSATION_TPL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_WORKERS_COMPENSATION_TPL_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_WORKERS_COMPENSATION_TPL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_WORKERS_COMPENSATION_TPL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_WC_PROPERTY_CASUALTY_AGENCY_CLAIM_NO = "AAAAAAAAAA";
    private static final String UPDATED_WC_PROPERTY_CASUALTY_AGENCY_CLAIM_NO = "BBBBBBBBBB";

    private static final Long DEFAULT_WC_CARRIER_ID = 1L;
    private static final Long UPDATED_WC_CARRIER_ID = 2L;

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

    private static final UUID DEFAULT_WORKERS_COMPENSATION_UUID = UUID.randomUUID();
    private static final UUID UPDATED_WORKERS_COMPENSATION_UUID = UUID.randomUUID();

    private static final String DEFAULT_EMPLOYER_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYER_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYER_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYER_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYER_CITY = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYER_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYER_STATE = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYER_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYER_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYER_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYER_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYER_ZIP = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYER_CONTACT_NO_1 = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYER_CONTACT_NO_1 = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYER_CONTACT_NO_2 = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYER_CONTACT_NO_2 = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYER_FAX = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYER_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYER_EFAX = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYER_EFAX = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYER_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYER_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_RELATIONSHIP = "AAAAAAAAAA";
    private static final String UPDATED_RELATIONSHIP = "BBBBBBBBBB";

    private static final String DEFAULT_MODE_OF_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_MODE_OF_CONTACT = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/workers-compensations";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{workersCompensationId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private WorkersCompensationRepository workersCompensationRepository;

    @Autowired
    private WorkersCompensationMapper workersCompensationMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private WorkersCompensation workersCompensation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WorkersCompensation createEntity(EntityManager em) {
        WorkersCompensation workersCompensation = new WorkersCompensation()
            .patientId(DEFAULT_PATIENT_ID)
            .insuredEmployer(DEFAULT_INSURED_EMPLOYER)
            .workersCompensationPayerIdNumber(DEFAULT_WORKERS_COMPENSATION_PAYER_ID_NUMBER)
            .workersCompensationPlanName(DEFAULT_WORKERS_COMPENSATION_PLAN_NAME)
            .workersCompensationAdditionalDtls(DEFAULT_WORKERS_COMPENSATION_ADDITIONAL_DTLS)
            .workersCompensationClaimFillingCode(DEFAULT_WORKERS_COMPENSATION_CLAIM_FILLING_CODE)
            .workersCompensationTplCode(DEFAULT_WORKERS_COMPENSATION_TPL_CODE)
            .workersCompensationTplName(DEFAULT_WORKERS_COMPENSATION_TPL_NAME)
            .wcPropertyCasualtyAgencyClaimNo(DEFAULT_WC_PROPERTY_CASUALTY_AGENCY_CLAIM_NO)
            .wcCarrierId(DEFAULT_WC_CARRIER_ID)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .workersCompensationUuid(DEFAULT_WORKERS_COMPENSATION_UUID)
            .employerAddressLine1(DEFAULT_EMPLOYER_ADDRESS_LINE_1)
            .employerAddressLine2(DEFAULT_EMPLOYER_ADDRESS_LINE_2)
            .employerCity(DEFAULT_EMPLOYER_CITY)
            .employerState(DEFAULT_EMPLOYER_STATE)
            .employerCountry(DEFAULT_EMPLOYER_COUNTRY)
            .employerZip(DEFAULT_EMPLOYER_ZIP)
            .employerContactNo1(DEFAULT_EMPLOYER_CONTACT_NO_1)
            .employerContactNo2(DEFAULT_EMPLOYER_CONTACT_NO_2)
            .employerFax(DEFAULT_EMPLOYER_FAX)
            .employerEfax(DEFAULT_EMPLOYER_EFAX)
            .employerEmail(DEFAULT_EMPLOYER_EMAIL)
            .relationship(DEFAULT_RELATIONSHIP)
            .modeOfContact(DEFAULT_MODE_OF_CONTACT);
        return workersCompensation;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WorkersCompensation createUpdatedEntity(EntityManager em) {
        WorkersCompensation workersCompensation = new WorkersCompensation()
            .patientId(UPDATED_PATIENT_ID)
            .insuredEmployer(UPDATED_INSURED_EMPLOYER)
            .workersCompensationPayerIdNumber(UPDATED_WORKERS_COMPENSATION_PAYER_ID_NUMBER)
            .workersCompensationPlanName(UPDATED_WORKERS_COMPENSATION_PLAN_NAME)
            .workersCompensationAdditionalDtls(UPDATED_WORKERS_COMPENSATION_ADDITIONAL_DTLS)
            .workersCompensationClaimFillingCode(UPDATED_WORKERS_COMPENSATION_CLAIM_FILLING_CODE)
            .workersCompensationTplCode(UPDATED_WORKERS_COMPENSATION_TPL_CODE)
            .workersCompensationTplName(UPDATED_WORKERS_COMPENSATION_TPL_NAME)
            .wcPropertyCasualtyAgencyClaimNo(UPDATED_WC_PROPERTY_CASUALTY_AGENCY_CLAIM_NO)
            .wcCarrierId(UPDATED_WC_CARRIER_ID)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .workersCompensationUuid(UPDATED_WORKERS_COMPENSATION_UUID)
            .employerAddressLine1(UPDATED_EMPLOYER_ADDRESS_LINE_1)
            .employerAddressLine2(UPDATED_EMPLOYER_ADDRESS_LINE_2)
            .employerCity(UPDATED_EMPLOYER_CITY)
            .employerState(UPDATED_EMPLOYER_STATE)
            .employerCountry(UPDATED_EMPLOYER_COUNTRY)
            .employerZip(UPDATED_EMPLOYER_ZIP)
            .employerContactNo1(UPDATED_EMPLOYER_CONTACT_NO_1)
            .employerContactNo2(UPDATED_EMPLOYER_CONTACT_NO_2)
            .employerFax(UPDATED_EMPLOYER_FAX)
            .employerEfax(UPDATED_EMPLOYER_EFAX)
            .employerEmail(UPDATED_EMPLOYER_EMAIL)
            .relationship(UPDATED_RELATIONSHIP)
            .modeOfContact(UPDATED_MODE_OF_CONTACT);
        return workersCompensation;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(WorkersCompensation.class).block();
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
        workersCompensation = createEntity(em);
    }

    @Test
    void createWorkersCompensation() throws Exception {
        int databaseSizeBeforeCreate = workersCompensationRepository.findAll().collectList().block().size();
        // Create the WorkersCompensation
        WorkersCompensationDTO workersCompensationDTO = workersCompensationMapper.toDto(workersCompensation);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(workersCompensationDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the WorkersCompensation in the database
        List<WorkersCompensation> workersCompensationList = workersCompensationRepository.findAll().collectList().block();
        assertThat(workersCompensationList).hasSize(databaseSizeBeforeCreate + 1);
        WorkersCompensation testWorkersCompensation = workersCompensationList.get(workersCompensationList.size() - 1);
        assertThat(testWorkersCompensation.getPatientId()).isEqualTo(DEFAULT_PATIENT_ID);
        assertThat(testWorkersCompensation.getInsuredEmployer()).isEqualTo(DEFAULT_INSURED_EMPLOYER);
        assertThat(testWorkersCompensation.getWorkersCompensationPayerIdNumber()).isEqualTo(DEFAULT_WORKERS_COMPENSATION_PAYER_ID_NUMBER);
        assertThat(testWorkersCompensation.getWorkersCompensationPlanName()).isEqualTo(DEFAULT_WORKERS_COMPENSATION_PLAN_NAME);
        assertThat(testWorkersCompensation.getWorkersCompensationAdditionalDtls()).isEqualTo(DEFAULT_WORKERS_COMPENSATION_ADDITIONAL_DTLS);
        assertThat(testWorkersCompensation.getWorkersCompensationClaimFillingCode())
            .isEqualTo(DEFAULT_WORKERS_COMPENSATION_CLAIM_FILLING_CODE);
        assertThat(testWorkersCompensation.getWorkersCompensationTplCode()).isEqualTo(DEFAULT_WORKERS_COMPENSATION_TPL_CODE);
        assertThat(testWorkersCompensation.getWorkersCompensationTplName()).isEqualTo(DEFAULT_WORKERS_COMPENSATION_TPL_NAME);
        assertThat(testWorkersCompensation.getWcPropertyCasualtyAgencyClaimNo()).isEqualTo(DEFAULT_WC_PROPERTY_CASUALTY_AGENCY_CLAIM_NO);
        assertThat(testWorkersCompensation.getWcCarrierId()).isEqualTo(DEFAULT_WC_CARRIER_ID);
        assertThat(testWorkersCompensation.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testWorkersCompensation.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testWorkersCompensation.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testWorkersCompensation.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testWorkersCompensation.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testWorkersCompensation.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testWorkersCompensation.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testWorkersCompensation.getWorkersCompensationUuid()).isEqualTo(DEFAULT_WORKERS_COMPENSATION_UUID);
        assertThat(testWorkersCompensation.getEmployerAddressLine1()).isEqualTo(DEFAULT_EMPLOYER_ADDRESS_LINE_1);
        assertThat(testWorkersCompensation.getEmployerAddressLine2()).isEqualTo(DEFAULT_EMPLOYER_ADDRESS_LINE_2);
        assertThat(testWorkersCompensation.getEmployerCity()).isEqualTo(DEFAULT_EMPLOYER_CITY);
        assertThat(testWorkersCompensation.getEmployerState()).isEqualTo(DEFAULT_EMPLOYER_STATE);
        assertThat(testWorkersCompensation.getEmployerCountry()).isEqualTo(DEFAULT_EMPLOYER_COUNTRY);
        assertThat(testWorkersCompensation.getEmployerZip()).isEqualTo(DEFAULT_EMPLOYER_ZIP);
        assertThat(testWorkersCompensation.getEmployerContactNo1()).isEqualTo(DEFAULT_EMPLOYER_CONTACT_NO_1);
        assertThat(testWorkersCompensation.getEmployerContactNo2()).isEqualTo(DEFAULT_EMPLOYER_CONTACT_NO_2);
        assertThat(testWorkersCompensation.getEmployerFax()).isEqualTo(DEFAULT_EMPLOYER_FAX);
        assertThat(testWorkersCompensation.getEmployerEfax()).isEqualTo(DEFAULT_EMPLOYER_EFAX);
        assertThat(testWorkersCompensation.getEmployerEmail()).isEqualTo(DEFAULT_EMPLOYER_EMAIL);
        assertThat(testWorkersCompensation.getRelationship()).isEqualTo(DEFAULT_RELATIONSHIP);
        assertThat(testWorkersCompensation.getModeOfContact()).isEqualTo(DEFAULT_MODE_OF_CONTACT);
    }

    @Test
    void createWorkersCompensationWithExistingId() throws Exception {
        // Create the WorkersCompensation with an existing ID
        workersCompensation.setWorkersCompensationId(1L);
        WorkersCompensationDTO workersCompensationDTO = workersCompensationMapper.toDto(workersCompensation);

        int databaseSizeBeforeCreate = workersCompensationRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(workersCompensationDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the WorkersCompensation in the database
        List<WorkersCompensation> workersCompensationList = workersCompensationRepository.findAll().collectList().block();
        assertThat(workersCompensationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllWorkersCompensations() {
        // Initialize the database
        workersCompensationRepository.save(workersCompensation).block();

        // Get all the workersCompensationList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=workersCompensationId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].workersCompensationId")
            .value(hasItem(workersCompensation.getWorkersCompensationId().intValue()))
            .jsonPath("$.[*].patientId")
            .value(hasItem(DEFAULT_PATIENT_ID.intValue()))
            .jsonPath("$.[*].insuredEmployer")
            .value(hasItem(DEFAULT_INSURED_EMPLOYER))
            .jsonPath("$.[*].workersCompensationPayerIdNumber")
            .value(hasItem(DEFAULT_WORKERS_COMPENSATION_PAYER_ID_NUMBER))
            .jsonPath("$.[*].workersCompensationPlanName")
            .value(hasItem(DEFAULT_WORKERS_COMPENSATION_PLAN_NAME))
            .jsonPath("$.[*].workersCompensationAdditionalDtls")
            .value(hasItem(DEFAULT_WORKERS_COMPENSATION_ADDITIONAL_DTLS))
            .jsonPath("$.[*].workersCompensationClaimFillingCode")
            .value(hasItem(DEFAULT_WORKERS_COMPENSATION_CLAIM_FILLING_CODE))
            .jsonPath("$.[*].workersCompensationTplCode")
            .value(hasItem(DEFAULT_WORKERS_COMPENSATION_TPL_CODE))
            .jsonPath("$.[*].workersCompensationTplName")
            .value(hasItem(DEFAULT_WORKERS_COMPENSATION_TPL_NAME))
            .jsonPath("$.[*].wcPropertyCasualtyAgencyClaimNo")
            .value(hasItem(DEFAULT_WC_PROPERTY_CASUALTY_AGENCY_CLAIM_NO))
            .jsonPath("$.[*].wcCarrierId")
            .value(hasItem(DEFAULT_WC_CARRIER_ID.intValue()))
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
            .jsonPath("$.[*].workersCompensationUuid")
            .value(hasItem(DEFAULT_WORKERS_COMPENSATION_UUID.toString()))
            .jsonPath("$.[*].employerAddressLine1")
            .value(hasItem(DEFAULT_EMPLOYER_ADDRESS_LINE_1))
            .jsonPath("$.[*].employerAddressLine2")
            .value(hasItem(DEFAULT_EMPLOYER_ADDRESS_LINE_2))
            .jsonPath("$.[*].employerCity")
            .value(hasItem(DEFAULT_EMPLOYER_CITY))
            .jsonPath("$.[*].employerState")
            .value(hasItem(DEFAULT_EMPLOYER_STATE))
            .jsonPath("$.[*].employerCountry")
            .value(hasItem(DEFAULT_EMPLOYER_COUNTRY))
            .jsonPath("$.[*].employerZip")
            .value(hasItem(DEFAULT_EMPLOYER_ZIP))
            .jsonPath("$.[*].employerContactNo1")
            .value(hasItem(DEFAULT_EMPLOYER_CONTACT_NO_1))
            .jsonPath("$.[*].employerContactNo2")
            .value(hasItem(DEFAULT_EMPLOYER_CONTACT_NO_2))
            .jsonPath("$.[*].employerFax")
            .value(hasItem(DEFAULT_EMPLOYER_FAX))
            .jsonPath("$.[*].employerEfax")
            .value(hasItem(DEFAULT_EMPLOYER_EFAX))
            .jsonPath("$.[*].employerEmail")
            .value(hasItem(DEFAULT_EMPLOYER_EMAIL))
            .jsonPath("$.[*].relationship")
            .value(hasItem(DEFAULT_RELATIONSHIP))
            .jsonPath("$.[*].modeOfContact")
            .value(hasItem(DEFAULT_MODE_OF_CONTACT));
    }

    @Test
    void getWorkersCompensation() {
        // Initialize the database
        workersCompensationRepository.save(workersCompensation).block();

        // Get the workersCompensation
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, workersCompensation.getWorkersCompensationId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.workersCompensationId")
            .value(is(workersCompensation.getWorkersCompensationId().intValue()))
            .jsonPath("$.patientId")
            .value(is(DEFAULT_PATIENT_ID.intValue()))
            .jsonPath("$.insuredEmployer")
            .value(is(DEFAULT_INSURED_EMPLOYER))
            .jsonPath("$.workersCompensationPayerIdNumber")
            .value(is(DEFAULT_WORKERS_COMPENSATION_PAYER_ID_NUMBER))
            .jsonPath("$.workersCompensationPlanName")
            .value(is(DEFAULT_WORKERS_COMPENSATION_PLAN_NAME))
            .jsonPath("$.workersCompensationAdditionalDtls")
            .value(is(DEFAULT_WORKERS_COMPENSATION_ADDITIONAL_DTLS))
            .jsonPath("$.workersCompensationClaimFillingCode")
            .value(is(DEFAULT_WORKERS_COMPENSATION_CLAIM_FILLING_CODE))
            .jsonPath("$.workersCompensationTplCode")
            .value(is(DEFAULT_WORKERS_COMPENSATION_TPL_CODE))
            .jsonPath("$.workersCompensationTplName")
            .value(is(DEFAULT_WORKERS_COMPENSATION_TPL_NAME))
            .jsonPath("$.wcPropertyCasualtyAgencyClaimNo")
            .value(is(DEFAULT_WC_PROPERTY_CASUALTY_AGENCY_CLAIM_NO))
            .jsonPath("$.wcCarrierId")
            .value(is(DEFAULT_WC_CARRIER_ID.intValue()))
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
            .jsonPath("$.workersCompensationUuid")
            .value(is(DEFAULT_WORKERS_COMPENSATION_UUID.toString()))
            .jsonPath("$.employerAddressLine1")
            .value(is(DEFAULT_EMPLOYER_ADDRESS_LINE_1))
            .jsonPath("$.employerAddressLine2")
            .value(is(DEFAULT_EMPLOYER_ADDRESS_LINE_2))
            .jsonPath("$.employerCity")
            .value(is(DEFAULT_EMPLOYER_CITY))
            .jsonPath("$.employerState")
            .value(is(DEFAULT_EMPLOYER_STATE))
            .jsonPath("$.employerCountry")
            .value(is(DEFAULT_EMPLOYER_COUNTRY))
            .jsonPath("$.employerZip")
            .value(is(DEFAULT_EMPLOYER_ZIP))
            .jsonPath("$.employerContactNo1")
            .value(is(DEFAULT_EMPLOYER_CONTACT_NO_1))
            .jsonPath("$.employerContactNo2")
            .value(is(DEFAULT_EMPLOYER_CONTACT_NO_2))
            .jsonPath("$.employerFax")
            .value(is(DEFAULT_EMPLOYER_FAX))
            .jsonPath("$.employerEfax")
            .value(is(DEFAULT_EMPLOYER_EFAX))
            .jsonPath("$.employerEmail")
            .value(is(DEFAULT_EMPLOYER_EMAIL))
            .jsonPath("$.relationship")
            .value(is(DEFAULT_RELATIONSHIP))
            .jsonPath("$.modeOfContact")
            .value(is(DEFAULT_MODE_OF_CONTACT));
    }

    @Test
    void getNonExistingWorkersCompensation() {
        // Get the workersCompensation
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingWorkersCompensation() throws Exception {
        // Initialize the database
        workersCompensationRepository.save(workersCompensation).block();

        int databaseSizeBeforeUpdate = workersCompensationRepository.findAll().collectList().block().size();

        // Update the workersCompensation
        WorkersCompensation updatedWorkersCompensation = workersCompensationRepository
            .findById(workersCompensation.getWorkersCompensationId())
            .block();
        updatedWorkersCompensation
            .patientId(UPDATED_PATIENT_ID)
            .insuredEmployer(UPDATED_INSURED_EMPLOYER)
            .workersCompensationPayerIdNumber(UPDATED_WORKERS_COMPENSATION_PAYER_ID_NUMBER)
            .workersCompensationPlanName(UPDATED_WORKERS_COMPENSATION_PLAN_NAME)
            .workersCompensationAdditionalDtls(UPDATED_WORKERS_COMPENSATION_ADDITIONAL_DTLS)
            .workersCompensationClaimFillingCode(UPDATED_WORKERS_COMPENSATION_CLAIM_FILLING_CODE)
            .workersCompensationTplCode(UPDATED_WORKERS_COMPENSATION_TPL_CODE)
            .workersCompensationTplName(UPDATED_WORKERS_COMPENSATION_TPL_NAME)
            .wcPropertyCasualtyAgencyClaimNo(UPDATED_WC_PROPERTY_CASUALTY_AGENCY_CLAIM_NO)
            .wcCarrierId(UPDATED_WC_CARRIER_ID)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .workersCompensationUuid(UPDATED_WORKERS_COMPENSATION_UUID)
            .employerAddressLine1(UPDATED_EMPLOYER_ADDRESS_LINE_1)
            .employerAddressLine2(UPDATED_EMPLOYER_ADDRESS_LINE_2)
            .employerCity(UPDATED_EMPLOYER_CITY)
            .employerState(UPDATED_EMPLOYER_STATE)
            .employerCountry(UPDATED_EMPLOYER_COUNTRY)
            .employerZip(UPDATED_EMPLOYER_ZIP)
            .employerContactNo1(UPDATED_EMPLOYER_CONTACT_NO_1)
            .employerContactNo2(UPDATED_EMPLOYER_CONTACT_NO_2)
            .employerFax(UPDATED_EMPLOYER_FAX)
            .employerEfax(UPDATED_EMPLOYER_EFAX)
            .employerEmail(UPDATED_EMPLOYER_EMAIL)
            .relationship(UPDATED_RELATIONSHIP)
            .modeOfContact(UPDATED_MODE_OF_CONTACT);
        WorkersCompensationDTO workersCompensationDTO = workersCompensationMapper.toDto(updatedWorkersCompensation);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, workersCompensationDTO.getWorkersCompensationId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(workersCompensationDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the WorkersCompensation in the database
        List<WorkersCompensation> workersCompensationList = workersCompensationRepository.findAll().collectList().block();
        assertThat(workersCompensationList).hasSize(databaseSizeBeforeUpdate);
        WorkersCompensation testWorkersCompensation = workersCompensationList.get(workersCompensationList.size() - 1);
        assertThat(testWorkersCompensation.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testWorkersCompensation.getInsuredEmployer()).isEqualTo(UPDATED_INSURED_EMPLOYER);
        assertThat(testWorkersCompensation.getWorkersCompensationPayerIdNumber()).isEqualTo(UPDATED_WORKERS_COMPENSATION_PAYER_ID_NUMBER);
        assertThat(testWorkersCompensation.getWorkersCompensationPlanName()).isEqualTo(UPDATED_WORKERS_COMPENSATION_PLAN_NAME);
        assertThat(testWorkersCompensation.getWorkersCompensationAdditionalDtls()).isEqualTo(UPDATED_WORKERS_COMPENSATION_ADDITIONAL_DTLS);
        assertThat(testWorkersCompensation.getWorkersCompensationClaimFillingCode())
            .isEqualTo(UPDATED_WORKERS_COMPENSATION_CLAIM_FILLING_CODE);
        assertThat(testWorkersCompensation.getWorkersCompensationTplCode()).isEqualTo(UPDATED_WORKERS_COMPENSATION_TPL_CODE);
        assertThat(testWorkersCompensation.getWorkersCompensationTplName()).isEqualTo(UPDATED_WORKERS_COMPENSATION_TPL_NAME);
        assertThat(testWorkersCompensation.getWcPropertyCasualtyAgencyClaimNo()).isEqualTo(UPDATED_WC_PROPERTY_CASUALTY_AGENCY_CLAIM_NO);
        assertThat(testWorkersCompensation.getWcCarrierId()).isEqualTo(UPDATED_WC_CARRIER_ID);
        assertThat(testWorkersCompensation.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testWorkersCompensation.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testWorkersCompensation.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testWorkersCompensation.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testWorkersCompensation.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testWorkersCompensation.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testWorkersCompensation.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testWorkersCompensation.getWorkersCompensationUuid()).isEqualTo(UPDATED_WORKERS_COMPENSATION_UUID);
        assertThat(testWorkersCompensation.getEmployerAddressLine1()).isEqualTo(UPDATED_EMPLOYER_ADDRESS_LINE_1);
        assertThat(testWorkersCompensation.getEmployerAddressLine2()).isEqualTo(UPDATED_EMPLOYER_ADDRESS_LINE_2);
        assertThat(testWorkersCompensation.getEmployerCity()).isEqualTo(UPDATED_EMPLOYER_CITY);
        assertThat(testWorkersCompensation.getEmployerState()).isEqualTo(UPDATED_EMPLOYER_STATE);
        assertThat(testWorkersCompensation.getEmployerCountry()).isEqualTo(UPDATED_EMPLOYER_COUNTRY);
        assertThat(testWorkersCompensation.getEmployerZip()).isEqualTo(UPDATED_EMPLOYER_ZIP);
        assertThat(testWorkersCompensation.getEmployerContactNo1()).isEqualTo(UPDATED_EMPLOYER_CONTACT_NO_1);
        assertThat(testWorkersCompensation.getEmployerContactNo2()).isEqualTo(UPDATED_EMPLOYER_CONTACT_NO_2);
        assertThat(testWorkersCompensation.getEmployerFax()).isEqualTo(UPDATED_EMPLOYER_FAX);
        assertThat(testWorkersCompensation.getEmployerEfax()).isEqualTo(UPDATED_EMPLOYER_EFAX);
        assertThat(testWorkersCompensation.getEmployerEmail()).isEqualTo(UPDATED_EMPLOYER_EMAIL);
        assertThat(testWorkersCompensation.getRelationship()).isEqualTo(UPDATED_RELATIONSHIP);
        assertThat(testWorkersCompensation.getModeOfContact()).isEqualTo(UPDATED_MODE_OF_CONTACT);
    }

    @Test
    void putNonExistingWorkersCompensation() throws Exception {
        int databaseSizeBeforeUpdate = workersCompensationRepository.findAll().collectList().block().size();
        workersCompensation.setWorkersCompensationId(count.incrementAndGet());

        // Create the WorkersCompensation
        WorkersCompensationDTO workersCompensationDTO = workersCompensationMapper.toDto(workersCompensation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, workersCompensationDTO.getWorkersCompensationId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(workersCompensationDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the WorkersCompensation in the database
        List<WorkersCompensation> workersCompensationList = workersCompensationRepository.findAll().collectList().block();
        assertThat(workersCompensationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchWorkersCompensation() throws Exception {
        int databaseSizeBeforeUpdate = workersCompensationRepository.findAll().collectList().block().size();
        workersCompensation.setWorkersCompensationId(count.incrementAndGet());

        // Create the WorkersCompensation
        WorkersCompensationDTO workersCompensationDTO = workersCompensationMapper.toDto(workersCompensation);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(workersCompensationDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the WorkersCompensation in the database
        List<WorkersCompensation> workersCompensationList = workersCompensationRepository.findAll().collectList().block();
        assertThat(workersCompensationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamWorkersCompensation() throws Exception {
        int databaseSizeBeforeUpdate = workersCompensationRepository.findAll().collectList().block().size();
        workersCompensation.setWorkersCompensationId(count.incrementAndGet());

        // Create the WorkersCompensation
        WorkersCompensationDTO workersCompensationDTO = workersCompensationMapper.toDto(workersCompensation);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(workersCompensationDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the WorkersCompensation in the database
        List<WorkersCompensation> workersCompensationList = workersCompensationRepository.findAll().collectList().block();
        assertThat(workersCompensationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateWorkersCompensationWithPatch() throws Exception {
        // Initialize the database
        workersCompensationRepository.save(workersCompensation).block();

        int databaseSizeBeforeUpdate = workersCompensationRepository.findAll().collectList().block().size();

        // Update the workersCompensation using partial update
        WorkersCompensation partialUpdatedWorkersCompensation = new WorkersCompensation();
        partialUpdatedWorkersCompensation.setWorkersCompensationId(workersCompensation.getWorkersCompensationId());

        partialUpdatedWorkersCompensation
            .patientId(UPDATED_PATIENT_ID)
            .workersCompensationPayerIdNumber(UPDATED_WORKERS_COMPENSATION_PAYER_ID_NUMBER)
            .workersCompensationPlanName(UPDATED_WORKERS_COMPENSATION_PLAN_NAME)
            .workersCompensationTplName(UPDATED_WORKERS_COMPENSATION_TPL_NAME)
            .status(UPDATED_STATUS)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .workersCompensationUuid(UPDATED_WORKERS_COMPENSATION_UUID)
            .employerCity(UPDATED_EMPLOYER_CITY)
            .employerState(UPDATED_EMPLOYER_STATE)
            .employerZip(UPDATED_EMPLOYER_ZIP)
            .employerContactNo2(UPDATED_EMPLOYER_CONTACT_NO_2)
            .employerFax(UPDATED_EMPLOYER_FAX)
            .employerEmail(UPDATED_EMPLOYER_EMAIL)
            .modeOfContact(UPDATED_MODE_OF_CONTACT);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedWorkersCompensation.getWorkersCompensationId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedWorkersCompensation))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the WorkersCompensation in the database
        List<WorkersCompensation> workersCompensationList = workersCompensationRepository.findAll().collectList().block();
        assertThat(workersCompensationList).hasSize(databaseSizeBeforeUpdate);
        WorkersCompensation testWorkersCompensation = workersCompensationList.get(workersCompensationList.size() - 1);
        assertThat(testWorkersCompensation.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testWorkersCompensation.getInsuredEmployer()).isEqualTo(DEFAULT_INSURED_EMPLOYER);
        assertThat(testWorkersCompensation.getWorkersCompensationPayerIdNumber()).isEqualTo(UPDATED_WORKERS_COMPENSATION_PAYER_ID_NUMBER);
        assertThat(testWorkersCompensation.getWorkersCompensationPlanName()).isEqualTo(UPDATED_WORKERS_COMPENSATION_PLAN_NAME);
        assertThat(testWorkersCompensation.getWorkersCompensationAdditionalDtls()).isEqualTo(DEFAULT_WORKERS_COMPENSATION_ADDITIONAL_DTLS);
        assertThat(testWorkersCompensation.getWorkersCompensationClaimFillingCode())
            .isEqualTo(DEFAULT_WORKERS_COMPENSATION_CLAIM_FILLING_CODE);
        assertThat(testWorkersCompensation.getWorkersCompensationTplCode()).isEqualTo(DEFAULT_WORKERS_COMPENSATION_TPL_CODE);
        assertThat(testWorkersCompensation.getWorkersCompensationTplName()).isEqualTo(UPDATED_WORKERS_COMPENSATION_TPL_NAME);
        assertThat(testWorkersCompensation.getWcPropertyCasualtyAgencyClaimNo()).isEqualTo(DEFAULT_WC_PROPERTY_CASUALTY_AGENCY_CLAIM_NO);
        assertThat(testWorkersCompensation.getWcCarrierId()).isEqualTo(DEFAULT_WC_CARRIER_ID);
        assertThat(testWorkersCompensation.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testWorkersCompensation.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testWorkersCompensation.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testWorkersCompensation.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testWorkersCompensation.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testWorkersCompensation.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testWorkersCompensation.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testWorkersCompensation.getWorkersCompensationUuid()).isEqualTo(UPDATED_WORKERS_COMPENSATION_UUID);
        assertThat(testWorkersCompensation.getEmployerAddressLine1()).isEqualTo(DEFAULT_EMPLOYER_ADDRESS_LINE_1);
        assertThat(testWorkersCompensation.getEmployerAddressLine2()).isEqualTo(DEFAULT_EMPLOYER_ADDRESS_LINE_2);
        assertThat(testWorkersCompensation.getEmployerCity()).isEqualTo(UPDATED_EMPLOYER_CITY);
        assertThat(testWorkersCompensation.getEmployerState()).isEqualTo(UPDATED_EMPLOYER_STATE);
        assertThat(testWorkersCompensation.getEmployerCountry()).isEqualTo(DEFAULT_EMPLOYER_COUNTRY);
        assertThat(testWorkersCompensation.getEmployerZip()).isEqualTo(UPDATED_EMPLOYER_ZIP);
        assertThat(testWorkersCompensation.getEmployerContactNo1()).isEqualTo(DEFAULT_EMPLOYER_CONTACT_NO_1);
        assertThat(testWorkersCompensation.getEmployerContactNo2()).isEqualTo(UPDATED_EMPLOYER_CONTACT_NO_2);
        assertThat(testWorkersCompensation.getEmployerFax()).isEqualTo(UPDATED_EMPLOYER_FAX);
        assertThat(testWorkersCompensation.getEmployerEfax()).isEqualTo(DEFAULT_EMPLOYER_EFAX);
        assertThat(testWorkersCompensation.getEmployerEmail()).isEqualTo(UPDATED_EMPLOYER_EMAIL);
        assertThat(testWorkersCompensation.getRelationship()).isEqualTo(DEFAULT_RELATIONSHIP);
        assertThat(testWorkersCompensation.getModeOfContact()).isEqualTo(UPDATED_MODE_OF_CONTACT);
    }

    @Test
    void fullUpdateWorkersCompensationWithPatch() throws Exception {
        // Initialize the database
        workersCompensationRepository.save(workersCompensation).block();

        int databaseSizeBeforeUpdate = workersCompensationRepository.findAll().collectList().block().size();

        // Update the workersCompensation using partial update
        WorkersCompensation partialUpdatedWorkersCompensation = new WorkersCompensation();
        partialUpdatedWorkersCompensation.setWorkersCompensationId(workersCompensation.getWorkersCompensationId());

        partialUpdatedWorkersCompensation
            .patientId(UPDATED_PATIENT_ID)
            .insuredEmployer(UPDATED_INSURED_EMPLOYER)
            .workersCompensationPayerIdNumber(UPDATED_WORKERS_COMPENSATION_PAYER_ID_NUMBER)
            .workersCompensationPlanName(UPDATED_WORKERS_COMPENSATION_PLAN_NAME)
            .workersCompensationAdditionalDtls(UPDATED_WORKERS_COMPENSATION_ADDITIONAL_DTLS)
            .workersCompensationClaimFillingCode(UPDATED_WORKERS_COMPENSATION_CLAIM_FILLING_CODE)
            .workersCompensationTplCode(UPDATED_WORKERS_COMPENSATION_TPL_CODE)
            .workersCompensationTplName(UPDATED_WORKERS_COMPENSATION_TPL_NAME)
            .wcPropertyCasualtyAgencyClaimNo(UPDATED_WC_PROPERTY_CASUALTY_AGENCY_CLAIM_NO)
            .wcCarrierId(UPDATED_WC_CARRIER_ID)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .workersCompensationUuid(UPDATED_WORKERS_COMPENSATION_UUID)
            .employerAddressLine1(UPDATED_EMPLOYER_ADDRESS_LINE_1)
            .employerAddressLine2(UPDATED_EMPLOYER_ADDRESS_LINE_2)
            .employerCity(UPDATED_EMPLOYER_CITY)
            .employerState(UPDATED_EMPLOYER_STATE)
            .employerCountry(UPDATED_EMPLOYER_COUNTRY)
            .employerZip(UPDATED_EMPLOYER_ZIP)
            .employerContactNo1(UPDATED_EMPLOYER_CONTACT_NO_1)
            .employerContactNo2(UPDATED_EMPLOYER_CONTACT_NO_2)
            .employerFax(UPDATED_EMPLOYER_FAX)
            .employerEfax(UPDATED_EMPLOYER_EFAX)
            .employerEmail(UPDATED_EMPLOYER_EMAIL)
            .relationship(UPDATED_RELATIONSHIP)
            .modeOfContact(UPDATED_MODE_OF_CONTACT);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedWorkersCompensation.getWorkersCompensationId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedWorkersCompensation))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the WorkersCompensation in the database
        List<WorkersCompensation> workersCompensationList = workersCompensationRepository.findAll().collectList().block();
        assertThat(workersCompensationList).hasSize(databaseSizeBeforeUpdate);
        WorkersCompensation testWorkersCompensation = workersCompensationList.get(workersCompensationList.size() - 1);
        assertThat(testWorkersCompensation.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testWorkersCompensation.getInsuredEmployer()).isEqualTo(UPDATED_INSURED_EMPLOYER);
        assertThat(testWorkersCompensation.getWorkersCompensationPayerIdNumber()).isEqualTo(UPDATED_WORKERS_COMPENSATION_PAYER_ID_NUMBER);
        assertThat(testWorkersCompensation.getWorkersCompensationPlanName()).isEqualTo(UPDATED_WORKERS_COMPENSATION_PLAN_NAME);
        assertThat(testWorkersCompensation.getWorkersCompensationAdditionalDtls()).isEqualTo(UPDATED_WORKERS_COMPENSATION_ADDITIONAL_DTLS);
        assertThat(testWorkersCompensation.getWorkersCompensationClaimFillingCode())
            .isEqualTo(UPDATED_WORKERS_COMPENSATION_CLAIM_FILLING_CODE);
        assertThat(testWorkersCompensation.getWorkersCompensationTplCode()).isEqualTo(UPDATED_WORKERS_COMPENSATION_TPL_CODE);
        assertThat(testWorkersCompensation.getWorkersCompensationTplName()).isEqualTo(UPDATED_WORKERS_COMPENSATION_TPL_NAME);
        assertThat(testWorkersCompensation.getWcPropertyCasualtyAgencyClaimNo()).isEqualTo(UPDATED_WC_PROPERTY_CASUALTY_AGENCY_CLAIM_NO);
        assertThat(testWorkersCompensation.getWcCarrierId()).isEqualTo(UPDATED_WC_CARRIER_ID);
        assertThat(testWorkersCompensation.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testWorkersCompensation.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testWorkersCompensation.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testWorkersCompensation.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testWorkersCompensation.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testWorkersCompensation.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testWorkersCompensation.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testWorkersCompensation.getWorkersCompensationUuid()).isEqualTo(UPDATED_WORKERS_COMPENSATION_UUID);
        assertThat(testWorkersCompensation.getEmployerAddressLine1()).isEqualTo(UPDATED_EMPLOYER_ADDRESS_LINE_1);
        assertThat(testWorkersCompensation.getEmployerAddressLine2()).isEqualTo(UPDATED_EMPLOYER_ADDRESS_LINE_2);
        assertThat(testWorkersCompensation.getEmployerCity()).isEqualTo(UPDATED_EMPLOYER_CITY);
        assertThat(testWorkersCompensation.getEmployerState()).isEqualTo(UPDATED_EMPLOYER_STATE);
        assertThat(testWorkersCompensation.getEmployerCountry()).isEqualTo(UPDATED_EMPLOYER_COUNTRY);
        assertThat(testWorkersCompensation.getEmployerZip()).isEqualTo(UPDATED_EMPLOYER_ZIP);
        assertThat(testWorkersCompensation.getEmployerContactNo1()).isEqualTo(UPDATED_EMPLOYER_CONTACT_NO_1);
        assertThat(testWorkersCompensation.getEmployerContactNo2()).isEqualTo(UPDATED_EMPLOYER_CONTACT_NO_2);
        assertThat(testWorkersCompensation.getEmployerFax()).isEqualTo(UPDATED_EMPLOYER_FAX);
        assertThat(testWorkersCompensation.getEmployerEfax()).isEqualTo(UPDATED_EMPLOYER_EFAX);
        assertThat(testWorkersCompensation.getEmployerEmail()).isEqualTo(UPDATED_EMPLOYER_EMAIL);
        assertThat(testWorkersCompensation.getRelationship()).isEqualTo(UPDATED_RELATIONSHIP);
        assertThat(testWorkersCompensation.getModeOfContact()).isEqualTo(UPDATED_MODE_OF_CONTACT);
    }

    @Test
    void patchNonExistingWorkersCompensation() throws Exception {
        int databaseSizeBeforeUpdate = workersCompensationRepository.findAll().collectList().block().size();
        workersCompensation.setWorkersCompensationId(count.incrementAndGet());

        // Create the WorkersCompensation
        WorkersCompensationDTO workersCompensationDTO = workersCompensationMapper.toDto(workersCompensation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, workersCompensationDTO.getWorkersCompensationId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(workersCompensationDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the WorkersCompensation in the database
        List<WorkersCompensation> workersCompensationList = workersCompensationRepository.findAll().collectList().block();
        assertThat(workersCompensationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchWorkersCompensation() throws Exception {
        int databaseSizeBeforeUpdate = workersCompensationRepository.findAll().collectList().block().size();
        workersCompensation.setWorkersCompensationId(count.incrementAndGet());

        // Create the WorkersCompensation
        WorkersCompensationDTO workersCompensationDTO = workersCompensationMapper.toDto(workersCompensation);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(workersCompensationDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the WorkersCompensation in the database
        List<WorkersCompensation> workersCompensationList = workersCompensationRepository.findAll().collectList().block();
        assertThat(workersCompensationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamWorkersCompensation() throws Exception {
        int databaseSizeBeforeUpdate = workersCompensationRepository.findAll().collectList().block().size();
        workersCompensation.setWorkersCompensationId(count.incrementAndGet());

        // Create the WorkersCompensation
        WorkersCompensationDTO workersCompensationDTO = workersCompensationMapper.toDto(workersCompensation);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(workersCompensationDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the WorkersCompensation in the database
        List<WorkersCompensation> workersCompensationList = workersCompensationRepository.findAll().collectList().block();
        assertThat(workersCompensationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteWorkersCompensation() {
        // Initialize the database
        workersCompensationRepository.save(workersCompensation).block();

        int databaseSizeBeforeDelete = workersCompensationRepository.findAll().collectList().block().size();

        // Delete the workersCompensation
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, workersCompensation.getWorkersCompensationId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<WorkersCompensation> workersCompensationList = workersCompensationRepository.findAll().collectList().block();
        assertThat(workersCompensationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
