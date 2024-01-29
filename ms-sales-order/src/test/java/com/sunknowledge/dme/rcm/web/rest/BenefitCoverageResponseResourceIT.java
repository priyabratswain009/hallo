package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.BenefitCoverageResponse;
import com.sunknowledge.dme.rcm.repository.BenefitCoverageResponseRepository;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.service.dto.BenefitCoverageResponseDTO;
import com.sunknowledge.dme.rcm.service.mapper.BenefitCoverageResponseMapper;
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
 * Integration tests for the {@link BenefitCoverageResponseResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class BenefitCoverageResponseResourceIT {

    private static final Long DEFAULT_BENEFIT_COVERAGE_REQUEST_ID = 1L;
    private static final Long UPDATED_BENEFIT_COVERAGE_REQUEST_ID = 2L;

    private static final String DEFAULT_REQUEST_CONTROL_NUMBER_EXT = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_CONTROL_NUMBER_EXT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_AS_ON_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_AS_ON_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SERVICE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_MEMBER_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MEMBER_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MEMBER_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MEMBER_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SUBSCRIBER_MEMBER_ID = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIBER_MEMBER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_MEMBER_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_MEMBER_GENDER = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_GENDER = "BBBBBBBBBB";

    private static final String DEFAULT_PAYER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_RELATIONSHIP_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_RELATIONSHIP_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_COVERAGE_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_COVERAGE_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_PAYER_GROUP_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_GROUP_NUMBER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_SERVICE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SERVICE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_PLAN_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PLAN_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_RESPONSE_JSON_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_JSON_TEXT = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final UUID DEFAULT_BENEFIT_COVERAGE_RESPONSE_UUID = UUID.randomUUID();
    private static final UUID UPDATED_BENEFIT_COVERAGE_RESPONSE_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/benefit-coverage-responses";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{benefitCoverageResponseId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BenefitCoverageResponseRepository benefitCoverageResponseRepository;

    @Autowired
    private BenefitCoverageResponseMapper benefitCoverageResponseMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private BenefitCoverageResponse benefitCoverageResponse;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BenefitCoverageResponse createEntity(EntityManager em) {
        BenefitCoverageResponse benefitCoverageResponse = new BenefitCoverageResponse()
            .benefitCoverageRequestId(DEFAULT_BENEFIT_COVERAGE_REQUEST_ID)
            .requestControlNumberExt(DEFAULT_REQUEST_CONTROL_NUMBER_EXT)
            .asOnDate(DEFAULT_AS_ON_DATE)
            .serviceType(DEFAULT_SERVICE_TYPE)
            .memberFirstName(DEFAULT_MEMBER_FIRST_NAME)
            .memberLastName(DEFAULT_MEMBER_LAST_NAME)
            .subscriberMemberId(DEFAULT_SUBSCRIBER_MEMBER_ID)
            .memberGender(DEFAULT_MEMBER_GENDER)
            .patientFirstName(DEFAULT_PATIENT_FIRST_NAME)
            .patientLastName(DEFAULT_PATIENT_LAST_NAME)
            .patientGender(DEFAULT_PATIENT_GENDER)
            .payerName(DEFAULT_PAYER_NAME)
            .patientRelationshipCode(DEFAULT_PATIENT_RELATIONSHIP_CODE)
            .patientState(DEFAULT_PATIENT_STATE)
            .coverageStatus(DEFAULT_COVERAGE_STATUS)
            .payerGroupNumber(DEFAULT_PAYER_GROUP_NUMBER)
            .serviceDate(DEFAULT_SERVICE_DATE)
            .planStartDate(DEFAULT_PLAN_START_DATE)
            .responseJsonText(DEFAULT_RESPONSE_JSON_TEXT)
            .status(DEFAULT_STATUS)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .benefitCoverageResponseUuid(DEFAULT_BENEFIT_COVERAGE_RESPONSE_UUID);
        return benefitCoverageResponse;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BenefitCoverageResponse createUpdatedEntity(EntityManager em) {
        BenefitCoverageResponse benefitCoverageResponse = new BenefitCoverageResponse()
            .benefitCoverageRequestId(UPDATED_BENEFIT_COVERAGE_REQUEST_ID)
            .requestControlNumberExt(UPDATED_REQUEST_CONTROL_NUMBER_EXT)
            .asOnDate(UPDATED_AS_ON_DATE)
            .serviceType(UPDATED_SERVICE_TYPE)
            .memberFirstName(UPDATED_MEMBER_FIRST_NAME)
            .memberLastName(UPDATED_MEMBER_LAST_NAME)
            .subscriberMemberId(UPDATED_SUBSCRIBER_MEMBER_ID)
            .memberGender(UPDATED_MEMBER_GENDER)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .patientGender(UPDATED_PATIENT_GENDER)
            .payerName(UPDATED_PAYER_NAME)
            .patientRelationshipCode(UPDATED_PATIENT_RELATIONSHIP_CODE)
            .patientState(UPDATED_PATIENT_STATE)
            .coverageStatus(UPDATED_COVERAGE_STATUS)
            .payerGroupNumber(UPDATED_PAYER_GROUP_NUMBER)
            .serviceDate(UPDATED_SERVICE_DATE)
            .planStartDate(UPDATED_PLAN_START_DATE)
            .responseJsonText(UPDATED_RESPONSE_JSON_TEXT)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .benefitCoverageResponseUuid(UPDATED_BENEFIT_COVERAGE_RESPONSE_UUID);
        return benefitCoverageResponse;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(BenefitCoverageResponse.class).block();
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
        benefitCoverageResponse = createEntity(em);
    }

    @Test
    void createBenefitCoverageResponse() throws Exception {
        int databaseSizeBeforeCreate = benefitCoverageResponseRepository.findAll().collectList().block().size();
        // Create the BenefitCoverageResponse
        BenefitCoverageResponseDTO benefitCoverageResponseDTO = benefitCoverageResponseMapper.toDto(benefitCoverageResponse);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(benefitCoverageResponseDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the BenefitCoverageResponse in the database
        List<BenefitCoverageResponse> benefitCoverageResponseList = benefitCoverageResponseRepository.findAll().collectList().block();
        assertThat(benefitCoverageResponseList).hasSize(databaseSizeBeforeCreate + 1);
        BenefitCoverageResponse testBenefitCoverageResponse = benefitCoverageResponseList.get(benefitCoverageResponseList.size() - 1);
        assertThat(testBenefitCoverageResponse.getBenefitCoverageRequestId()).isEqualTo(DEFAULT_BENEFIT_COVERAGE_REQUEST_ID);
        assertThat(testBenefitCoverageResponse.getRequestControlNumberExt()).isEqualTo(DEFAULT_REQUEST_CONTROL_NUMBER_EXT);
        assertThat(testBenefitCoverageResponse.getAsOnDate()).isEqualTo(DEFAULT_AS_ON_DATE);
        assertThat(testBenefitCoverageResponse.getServiceType()).isEqualTo(DEFAULT_SERVICE_TYPE);
        assertThat(testBenefitCoverageResponse.getMemberFirstName()).isEqualTo(DEFAULT_MEMBER_FIRST_NAME);
        assertThat(testBenefitCoverageResponse.getMemberLastName()).isEqualTo(DEFAULT_MEMBER_LAST_NAME);
        assertThat(testBenefitCoverageResponse.getSubscriberMemberId()).isEqualTo(DEFAULT_SUBSCRIBER_MEMBER_ID);
        assertThat(testBenefitCoverageResponse.getMemberGender()).isEqualTo(DEFAULT_MEMBER_GENDER);
        assertThat(testBenefitCoverageResponse.getPatientFirstName()).isEqualTo(DEFAULT_PATIENT_FIRST_NAME);
        assertThat(testBenefitCoverageResponse.getPatientLastName()).isEqualTo(DEFAULT_PATIENT_LAST_NAME);
        assertThat(testBenefitCoverageResponse.getPatientGender()).isEqualTo(DEFAULT_PATIENT_GENDER);
        assertThat(testBenefitCoverageResponse.getPayerName()).isEqualTo(DEFAULT_PAYER_NAME);
        assertThat(testBenefitCoverageResponse.getPatientRelationshipCode()).isEqualTo(DEFAULT_PATIENT_RELATIONSHIP_CODE);
        assertThat(testBenefitCoverageResponse.getPatientState()).isEqualTo(DEFAULT_PATIENT_STATE);
        assertThat(testBenefitCoverageResponse.getCoverageStatus()).isEqualTo(DEFAULT_COVERAGE_STATUS);
        assertThat(testBenefitCoverageResponse.getPayerGroupNumber()).isEqualTo(DEFAULT_PAYER_GROUP_NUMBER);
        assertThat(testBenefitCoverageResponse.getServiceDate()).isEqualTo(DEFAULT_SERVICE_DATE);
        assertThat(testBenefitCoverageResponse.getPlanStartDate()).isEqualTo(DEFAULT_PLAN_START_DATE);
        assertThat(testBenefitCoverageResponse.getResponseJsonText()).isEqualTo(DEFAULT_RESPONSE_JSON_TEXT);
        assertThat(testBenefitCoverageResponse.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testBenefitCoverageResponse.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testBenefitCoverageResponse.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testBenefitCoverageResponse.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testBenefitCoverageResponse.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testBenefitCoverageResponse.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testBenefitCoverageResponse.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testBenefitCoverageResponse.getBenefitCoverageResponseUuid()).isEqualTo(DEFAULT_BENEFIT_COVERAGE_RESPONSE_UUID);
    }

    @Test
    void createBenefitCoverageResponseWithExistingId() throws Exception {
        // Create the BenefitCoverageResponse with an existing ID
        benefitCoverageResponse.setBenefitCoverageResponseId(1L);
        BenefitCoverageResponseDTO benefitCoverageResponseDTO = benefitCoverageResponseMapper.toDto(benefitCoverageResponse);

        int databaseSizeBeforeCreate = benefitCoverageResponseRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(benefitCoverageResponseDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the BenefitCoverageResponse in the database
        List<BenefitCoverageResponse> benefitCoverageResponseList = benefitCoverageResponseRepository.findAll().collectList().block();
        assertThat(benefitCoverageResponseList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllBenefitCoverageResponses() {
        // Initialize the database
        benefitCoverageResponseRepository.save(benefitCoverageResponse).block();

        // Get all the benefitCoverageResponseList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=benefitCoverageResponseId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].benefitCoverageResponseId")
            .value(hasItem(benefitCoverageResponse.getBenefitCoverageResponseId().intValue()))
            .jsonPath("$.[*].benefitCoverageRequestId")
            .value(hasItem(DEFAULT_BENEFIT_COVERAGE_REQUEST_ID.intValue()))
            .jsonPath("$.[*].requestControlNumberExt")
            .value(hasItem(DEFAULT_REQUEST_CONTROL_NUMBER_EXT))
            .jsonPath("$.[*].asOnDate")
            .value(hasItem(DEFAULT_AS_ON_DATE.toString()))
            .jsonPath("$.[*].serviceType")
            .value(hasItem(DEFAULT_SERVICE_TYPE))
            .jsonPath("$.[*].memberFirstName")
            .value(hasItem(DEFAULT_MEMBER_FIRST_NAME))
            .jsonPath("$.[*].memberLastName")
            .value(hasItem(DEFAULT_MEMBER_LAST_NAME))
            .jsonPath("$.[*].subscriberMemberId")
            .value(hasItem(DEFAULT_SUBSCRIBER_MEMBER_ID))
            .jsonPath("$.[*].memberGender")
            .value(hasItem(DEFAULT_MEMBER_GENDER))
            .jsonPath("$.[*].patientFirstName")
            .value(hasItem(DEFAULT_PATIENT_FIRST_NAME))
            .jsonPath("$.[*].patientLastName")
            .value(hasItem(DEFAULT_PATIENT_LAST_NAME))
            .jsonPath("$.[*].patientGender")
            .value(hasItem(DEFAULT_PATIENT_GENDER))
            .jsonPath("$.[*].payerName")
            .value(hasItem(DEFAULT_PAYER_NAME))
            .jsonPath("$.[*].patientRelationshipCode")
            .value(hasItem(DEFAULT_PATIENT_RELATIONSHIP_CODE))
            .jsonPath("$.[*].patientState")
            .value(hasItem(DEFAULT_PATIENT_STATE))
            .jsonPath("$.[*].coverageStatus")
            .value(hasItem(DEFAULT_COVERAGE_STATUS))
            .jsonPath("$.[*].payerGroupNumber")
            .value(hasItem(DEFAULT_PAYER_GROUP_NUMBER))
            .jsonPath("$.[*].serviceDate")
            .value(hasItem(DEFAULT_SERVICE_DATE.toString()))
            .jsonPath("$.[*].planStartDate")
            .value(hasItem(DEFAULT_PLAN_START_DATE.toString()))
            .jsonPath("$.[*].responseJsonText")
            .value(hasItem(DEFAULT_RESPONSE_JSON_TEXT))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS))
            .jsonPath("$.[*].createdDate")
            .value(hasItem(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.[*].createdById")
            .value(hasItem(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.[*].createdByName")
            .value(hasItem(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.[*].updatedDate")
            .value(hasItem(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.[*].updatedById")
            .value(hasItem(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.[*].updatedByName")
            .value(hasItem(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.[*].benefitCoverageResponseUuid")
            .value(hasItem(DEFAULT_BENEFIT_COVERAGE_RESPONSE_UUID.toString()));
    }

    @Test
    void getBenefitCoverageResponse() {
        // Initialize the database
        benefitCoverageResponseRepository.save(benefitCoverageResponse).block();

        // Get the benefitCoverageResponse
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, benefitCoverageResponse.getBenefitCoverageResponseId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.benefitCoverageResponseId")
            .value(is(benefitCoverageResponse.getBenefitCoverageResponseId().intValue()))
            .jsonPath("$.benefitCoverageRequestId")
            .value(is(DEFAULT_BENEFIT_COVERAGE_REQUEST_ID.intValue()))
            .jsonPath("$.requestControlNumberExt")
            .value(is(DEFAULT_REQUEST_CONTROL_NUMBER_EXT))
            .jsonPath("$.asOnDate")
            .value(is(DEFAULT_AS_ON_DATE.toString()))
            .jsonPath("$.serviceType")
            .value(is(DEFAULT_SERVICE_TYPE))
            .jsonPath("$.memberFirstName")
            .value(is(DEFAULT_MEMBER_FIRST_NAME))
            .jsonPath("$.memberLastName")
            .value(is(DEFAULT_MEMBER_LAST_NAME))
            .jsonPath("$.subscriberMemberId")
            .value(is(DEFAULT_SUBSCRIBER_MEMBER_ID))
            .jsonPath("$.memberGender")
            .value(is(DEFAULT_MEMBER_GENDER))
            .jsonPath("$.patientFirstName")
            .value(is(DEFAULT_PATIENT_FIRST_NAME))
            .jsonPath("$.patientLastName")
            .value(is(DEFAULT_PATIENT_LAST_NAME))
            .jsonPath("$.patientGender")
            .value(is(DEFAULT_PATIENT_GENDER))
            .jsonPath("$.payerName")
            .value(is(DEFAULT_PAYER_NAME))
            .jsonPath("$.patientRelationshipCode")
            .value(is(DEFAULT_PATIENT_RELATIONSHIP_CODE))
            .jsonPath("$.patientState")
            .value(is(DEFAULT_PATIENT_STATE))
            .jsonPath("$.coverageStatus")
            .value(is(DEFAULT_COVERAGE_STATUS))
            .jsonPath("$.payerGroupNumber")
            .value(is(DEFAULT_PAYER_GROUP_NUMBER))
            .jsonPath("$.serviceDate")
            .value(is(DEFAULT_SERVICE_DATE.toString()))
            .jsonPath("$.planStartDate")
            .value(is(DEFAULT_PLAN_START_DATE.toString()))
            .jsonPath("$.responseJsonText")
            .value(is(DEFAULT_RESPONSE_JSON_TEXT))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS))
            .jsonPath("$.createdDate")
            .value(is(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.createdById")
            .value(is(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.createdByName")
            .value(is(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.updatedDate")
            .value(is(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.updatedById")
            .value(is(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.updatedByName")
            .value(is(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.benefitCoverageResponseUuid")
            .value(is(DEFAULT_BENEFIT_COVERAGE_RESPONSE_UUID.toString()));
    }

    @Test
    void getNonExistingBenefitCoverageResponse() {
        // Get the benefitCoverageResponse
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingBenefitCoverageResponse() throws Exception {
        // Initialize the database
        benefitCoverageResponseRepository.save(benefitCoverageResponse).block();

        int databaseSizeBeforeUpdate = benefitCoverageResponseRepository.findAll().collectList().block().size();

        // Update the benefitCoverageResponse
        BenefitCoverageResponse updatedBenefitCoverageResponse = benefitCoverageResponseRepository
            .findById(benefitCoverageResponse.getBenefitCoverageResponseId())
            .block();
        updatedBenefitCoverageResponse
            .benefitCoverageRequestId(UPDATED_BENEFIT_COVERAGE_REQUEST_ID)
            .requestControlNumberExt(UPDATED_REQUEST_CONTROL_NUMBER_EXT)
            .asOnDate(UPDATED_AS_ON_DATE)
            .serviceType(UPDATED_SERVICE_TYPE)
            .memberFirstName(UPDATED_MEMBER_FIRST_NAME)
            .memberLastName(UPDATED_MEMBER_LAST_NAME)
            .subscriberMemberId(UPDATED_SUBSCRIBER_MEMBER_ID)
            .memberGender(UPDATED_MEMBER_GENDER)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .patientGender(UPDATED_PATIENT_GENDER)
            .payerName(UPDATED_PAYER_NAME)
            .patientRelationshipCode(UPDATED_PATIENT_RELATIONSHIP_CODE)
            .patientState(UPDATED_PATIENT_STATE)
            .coverageStatus(UPDATED_COVERAGE_STATUS)
            .payerGroupNumber(UPDATED_PAYER_GROUP_NUMBER)
            .serviceDate(UPDATED_SERVICE_DATE)
            .planStartDate(UPDATED_PLAN_START_DATE)
            .responseJsonText(UPDATED_RESPONSE_JSON_TEXT)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .benefitCoverageResponseUuid(UPDATED_BENEFIT_COVERAGE_RESPONSE_UUID);
        BenefitCoverageResponseDTO benefitCoverageResponseDTO = benefitCoverageResponseMapper.toDto(updatedBenefitCoverageResponse);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, benefitCoverageResponseDTO.getBenefitCoverageResponseId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(benefitCoverageResponseDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the BenefitCoverageResponse in the database
        List<BenefitCoverageResponse> benefitCoverageResponseList = benefitCoverageResponseRepository.findAll().collectList().block();
        assertThat(benefitCoverageResponseList).hasSize(databaseSizeBeforeUpdate);
        BenefitCoverageResponse testBenefitCoverageResponse = benefitCoverageResponseList.get(benefitCoverageResponseList.size() - 1);
        assertThat(testBenefitCoverageResponse.getBenefitCoverageRequestId()).isEqualTo(UPDATED_BENEFIT_COVERAGE_REQUEST_ID);
        assertThat(testBenefitCoverageResponse.getRequestControlNumberExt()).isEqualTo(UPDATED_REQUEST_CONTROL_NUMBER_EXT);
        assertThat(testBenefitCoverageResponse.getAsOnDate()).isEqualTo(UPDATED_AS_ON_DATE);
        assertThat(testBenefitCoverageResponse.getServiceType()).isEqualTo(UPDATED_SERVICE_TYPE);
        assertThat(testBenefitCoverageResponse.getMemberFirstName()).isEqualTo(UPDATED_MEMBER_FIRST_NAME);
        assertThat(testBenefitCoverageResponse.getMemberLastName()).isEqualTo(UPDATED_MEMBER_LAST_NAME);
        assertThat(testBenefitCoverageResponse.getSubscriberMemberId()).isEqualTo(UPDATED_SUBSCRIBER_MEMBER_ID);
        assertThat(testBenefitCoverageResponse.getMemberGender()).isEqualTo(UPDATED_MEMBER_GENDER);
        assertThat(testBenefitCoverageResponse.getPatientFirstName()).isEqualTo(UPDATED_PATIENT_FIRST_NAME);
        assertThat(testBenefitCoverageResponse.getPatientLastName()).isEqualTo(UPDATED_PATIENT_LAST_NAME);
        assertThat(testBenefitCoverageResponse.getPatientGender()).isEqualTo(UPDATED_PATIENT_GENDER);
        assertThat(testBenefitCoverageResponse.getPayerName()).isEqualTo(UPDATED_PAYER_NAME);
        assertThat(testBenefitCoverageResponse.getPatientRelationshipCode()).isEqualTo(UPDATED_PATIENT_RELATIONSHIP_CODE);
        assertThat(testBenefitCoverageResponse.getPatientState()).isEqualTo(UPDATED_PATIENT_STATE);
        assertThat(testBenefitCoverageResponse.getCoverageStatus()).isEqualTo(UPDATED_COVERAGE_STATUS);
        assertThat(testBenefitCoverageResponse.getPayerGroupNumber()).isEqualTo(UPDATED_PAYER_GROUP_NUMBER);
        assertThat(testBenefitCoverageResponse.getServiceDate()).isEqualTo(UPDATED_SERVICE_DATE);
        assertThat(testBenefitCoverageResponse.getPlanStartDate()).isEqualTo(UPDATED_PLAN_START_DATE);
        assertThat(testBenefitCoverageResponse.getResponseJsonText()).isEqualTo(UPDATED_RESPONSE_JSON_TEXT);
        assertThat(testBenefitCoverageResponse.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testBenefitCoverageResponse.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBenefitCoverageResponse.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testBenefitCoverageResponse.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testBenefitCoverageResponse.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testBenefitCoverageResponse.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testBenefitCoverageResponse.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testBenefitCoverageResponse.getBenefitCoverageResponseUuid()).isEqualTo(UPDATED_BENEFIT_COVERAGE_RESPONSE_UUID);
    }

    @Test
    void putNonExistingBenefitCoverageResponse() throws Exception {
        int databaseSizeBeforeUpdate = benefitCoverageResponseRepository.findAll().collectList().block().size();
        benefitCoverageResponse.setBenefitCoverageResponseId(count.incrementAndGet());

        // Create the BenefitCoverageResponse
        BenefitCoverageResponseDTO benefitCoverageResponseDTO = benefitCoverageResponseMapper.toDto(benefitCoverageResponse);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, benefitCoverageResponseDTO.getBenefitCoverageResponseId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(benefitCoverageResponseDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the BenefitCoverageResponse in the database
        List<BenefitCoverageResponse> benefitCoverageResponseList = benefitCoverageResponseRepository.findAll().collectList().block();
        assertThat(benefitCoverageResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchBenefitCoverageResponse() throws Exception {
        int databaseSizeBeforeUpdate = benefitCoverageResponseRepository.findAll().collectList().block().size();
        benefitCoverageResponse.setBenefitCoverageResponseId(count.incrementAndGet());

        // Create the BenefitCoverageResponse
        BenefitCoverageResponseDTO benefitCoverageResponseDTO = benefitCoverageResponseMapper.toDto(benefitCoverageResponse);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(benefitCoverageResponseDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the BenefitCoverageResponse in the database
        List<BenefitCoverageResponse> benefitCoverageResponseList = benefitCoverageResponseRepository.findAll().collectList().block();
        assertThat(benefitCoverageResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamBenefitCoverageResponse() throws Exception {
        int databaseSizeBeforeUpdate = benefitCoverageResponseRepository.findAll().collectList().block().size();
        benefitCoverageResponse.setBenefitCoverageResponseId(count.incrementAndGet());

        // Create the BenefitCoverageResponse
        BenefitCoverageResponseDTO benefitCoverageResponseDTO = benefitCoverageResponseMapper.toDto(benefitCoverageResponse);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(benefitCoverageResponseDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the BenefitCoverageResponse in the database
        List<BenefitCoverageResponse> benefitCoverageResponseList = benefitCoverageResponseRepository.findAll().collectList().block();
        assertThat(benefitCoverageResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateBenefitCoverageResponseWithPatch() throws Exception {
        // Initialize the database
        benefitCoverageResponseRepository.save(benefitCoverageResponse).block();

        int databaseSizeBeforeUpdate = benefitCoverageResponseRepository.findAll().collectList().block().size();

        // Update the benefitCoverageResponse using partial update
        BenefitCoverageResponse partialUpdatedBenefitCoverageResponse = new BenefitCoverageResponse();
        partialUpdatedBenefitCoverageResponse.setBenefitCoverageResponseId(benefitCoverageResponse.getBenefitCoverageResponseId());

        partialUpdatedBenefitCoverageResponse
            .memberGender(UPDATED_MEMBER_GENDER)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .payerName(UPDATED_PAYER_NAME)
            .patientState(UPDATED_PATIENT_STATE)
            .coverageStatus(UPDATED_COVERAGE_STATUS)
            .payerGroupNumber(UPDATED_PAYER_GROUP_NUMBER)
            .serviceDate(UPDATED_SERVICE_DATE)
            .planStartDate(UPDATED_PLAN_START_DATE)
            .responseJsonText(UPDATED_RESPONSE_JSON_TEXT)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .benefitCoverageResponseUuid(UPDATED_BENEFIT_COVERAGE_RESPONSE_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedBenefitCoverageResponse.getBenefitCoverageResponseId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedBenefitCoverageResponse))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the BenefitCoverageResponse in the database
        List<BenefitCoverageResponse> benefitCoverageResponseList = benefitCoverageResponseRepository.findAll().collectList().block();
        assertThat(benefitCoverageResponseList).hasSize(databaseSizeBeforeUpdate);
        BenefitCoverageResponse testBenefitCoverageResponse = benefitCoverageResponseList.get(benefitCoverageResponseList.size() - 1);
        assertThat(testBenefitCoverageResponse.getBenefitCoverageRequestId()).isEqualTo(DEFAULT_BENEFIT_COVERAGE_REQUEST_ID);
        assertThat(testBenefitCoverageResponse.getRequestControlNumberExt()).isEqualTo(DEFAULT_REQUEST_CONTROL_NUMBER_EXT);
        assertThat(testBenefitCoverageResponse.getAsOnDate()).isEqualTo(DEFAULT_AS_ON_DATE);
        assertThat(testBenefitCoverageResponse.getServiceType()).isEqualTo(DEFAULT_SERVICE_TYPE);
        assertThat(testBenefitCoverageResponse.getMemberFirstName()).isEqualTo(DEFAULT_MEMBER_FIRST_NAME);
        assertThat(testBenefitCoverageResponse.getMemberLastName()).isEqualTo(DEFAULT_MEMBER_LAST_NAME);
        assertThat(testBenefitCoverageResponse.getSubscriberMemberId()).isEqualTo(DEFAULT_SUBSCRIBER_MEMBER_ID);
        assertThat(testBenefitCoverageResponse.getMemberGender()).isEqualTo(UPDATED_MEMBER_GENDER);
        assertThat(testBenefitCoverageResponse.getPatientFirstName()).isEqualTo(DEFAULT_PATIENT_FIRST_NAME);
        assertThat(testBenefitCoverageResponse.getPatientLastName()).isEqualTo(UPDATED_PATIENT_LAST_NAME);
        assertThat(testBenefitCoverageResponse.getPatientGender()).isEqualTo(DEFAULT_PATIENT_GENDER);
        assertThat(testBenefitCoverageResponse.getPayerName()).isEqualTo(UPDATED_PAYER_NAME);
        assertThat(testBenefitCoverageResponse.getPatientRelationshipCode()).isEqualTo(DEFAULT_PATIENT_RELATIONSHIP_CODE);
        assertThat(testBenefitCoverageResponse.getPatientState()).isEqualTo(UPDATED_PATIENT_STATE);
        assertThat(testBenefitCoverageResponse.getCoverageStatus()).isEqualTo(UPDATED_COVERAGE_STATUS);
        assertThat(testBenefitCoverageResponse.getPayerGroupNumber()).isEqualTo(UPDATED_PAYER_GROUP_NUMBER);
        assertThat(testBenefitCoverageResponse.getServiceDate()).isEqualTo(UPDATED_SERVICE_DATE);
        assertThat(testBenefitCoverageResponse.getPlanStartDate()).isEqualTo(UPDATED_PLAN_START_DATE);
        assertThat(testBenefitCoverageResponse.getResponseJsonText()).isEqualTo(UPDATED_RESPONSE_JSON_TEXT);
        assertThat(testBenefitCoverageResponse.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testBenefitCoverageResponse.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBenefitCoverageResponse.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testBenefitCoverageResponse.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testBenefitCoverageResponse.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testBenefitCoverageResponse.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testBenefitCoverageResponse.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testBenefitCoverageResponse.getBenefitCoverageResponseUuid()).isEqualTo(UPDATED_BENEFIT_COVERAGE_RESPONSE_UUID);
    }

    @Test
    void fullUpdateBenefitCoverageResponseWithPatch() throws Exception {
        // Initialize the database
        benefitCoverageResponseRepository.save(benefitCoverageResponse).block();

        int databaseSizeBeforeUpdate = benefitCoverageResponseRepository.findAll().collectList().block().size();

        // Update the benefitCoverageResponse using partial update
        BenefitCoverageResponse partialUpdatedBenefitCoverageResponse = new BenefitCoverageResponse();
        partialUpdatedBenefitCoverageResponse.setBenefitCoverageResponseId(benefitCoverageResponse.getBenefitCoverageResponseId());

        partialUpdatedBenefitCoverageResponse
            .benefitCoverageRequestId(UPDATED_BENEFIT_COVERAGE_REQUEST_ID)
            .requestControlNumberExt(UPDATED_REQUEST_CONTROL_NUMBER_EXT)
            .asOnDate(UPDATED_AS_ON_DATE)
            .serviceType(UPDATED_SERVICE_TYPE)
            .memberFirstName(UPDATED_MEMBER_FIRST_NAME)
            .memberLastName(UPDATED_MEMBER_LAST_NAME)
            .subscriberMemberId(UPDATED_SUBSCRIBER_MEMBER_ID)
            .memberGender(UPDATED_MEMBER_GENDER)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .patientGender(UPDATED_PATIENT_GENDER)
            .payerName(UPDATED_PAYER_NAME)
            .patientRelationshipCode(UPDATED_PATIENT_RELATIONSHIP_CODE)
            .patientState(UPDATED_PATIENT_STATE)
            .coverageStatus(UPDATED_COVERAGE_STATUS)
            .payerGroupNumber(UPDATED_PAYER_GROUP_NUMBER)
            .serviceDate(UPDATED_SERVICE_DATE)
            .planStartDate(UPDATED_PLAN_START_DATE)
            .responseJsonText(UPDATED_RESPONSE_JSON_TEXT)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .benefitCoverageResponseUuid(UPDATED_BENEFIT_COVERAGE_RESPONSE_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedBenefitCoverageResponse.getBenefitCoverageResponseId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedBenefitCoverageResponse))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the BenefitCoverageResponse in the database
        List<BenefitCoverageResponse> benefitCoverageResponseList = benefitCoverageResponseRepository.findAll().collectList().block();
        assertThat(benefitCoverageResponseList).hasSize(databaseSizeBeforeUpdate);
        BenefitCoverageResponse testBenefitCoverageResponse = benefitCoverageResponseList.get(benefitCoverageResponseList.size() - 1);
        assertThat(testBenefitCoverageResponse.getBenefitCoverageRequestId()).isEqualTo(UPDATED_BENEFIT_COVERAGE_REQUEST_ID);
        assertThat(testBenefitCoverageResponse.getRequestControlNumberExt()).isEqualTo(UPDATED_REQUEST_CONTROL_NUMBER_EXT);
        assertThat(testBenefitCoverageResponse.getAsOnDate()).isEqualTo(UPDATED_AS_ON_DATE);
        assertThat(testBenefitCoverageResponse.getServiceType()).isEqualTo(UPDATED_SERVICE_TYPE);
        assertThat(testBenefitCoverageResponse.getMemberFirstName()).isEqualTo(UPDATED_MEMBER_FIRST_NAME);
        assertThat(testBenefitCoverageResponse.getMemberLastName()).isEqualTo(UPDATED_MEMBER_LAST_NAME);
        assertThat(testBenefitCoverageResponse.getSubscriberMemberId()).isEqualTo(UPDATED_SUBSCRIBER_MEMBER_ID);
        assertThat(testBenefitCoverageResponse.getMemberGender()).isEqualTo(UPDATED_MEMBER_GENDER);
        assertThat(testBenefitCoverageResponse.getPatientFirstName()).isEqualTo(UPDATED_PATIENT_FIRST_NAME);
        assertThat(testBenefitCoverageResponse.getPatientLastName()).isEqualTo(UPDATED_PATIENT_LAST_NAME);
        assertThat(testBenefitCoverageResponse.getPatientGender()).isEqualTo(UPDATED_PATIENT_GENDER);
        assertThat(testBenefitCoverageResponse.getPayerName()).isEqualTo(UPDATED_PAYER_NAME);
        assertThat(testBenefitCoverageResponse.getPatientRelationshipCode()).isEqualTo(UPDATED_PATIENT_RELATIONSHIP_CODE);
        assertThat(testBenefitCoverageResponse.getPatientState()).isEqualTo(UPDATED_PATIENT_STATE);
        assertThat(testBenefitCoverageResponse.getCoverageStatus()).isEqualTo(UPDATED_COVERAGE_STATUS);
        assertThat(testBenefitCoverageResponse.getPayerGroupNumber()).isEqualTo(UPDATED_PAYER_GROUP_NUMBER);
        assertThat(testBenefitCoverageResponse.getServiceDate()).isEqualTo(UPDATED_SERVICE_DATE);
        assertThat(testBenefitCoverageResponse.getPlanStartDate()).isEqualTo(UPDATED_PLAN_START_DATE);
        assertThat(testBenefitCoverageResponse.getResponseJsonText()).isEqualTo(UPDATED_RESPONSE_JSON_TEXT);
        assertThat(testBenefitCoverageResponse.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testBenefitCoverageResponse.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBenefitCoverageResponse.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testBenefitCoverageResponse.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testBenefitCoverageResponse.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testBenefitCoverageResponse.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testBenefitCoverageResponse.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testBenefitCoverageResponse.getBenefitCoverageResponseUuid()).isEqualTo(UPDATED_BENEFIT_COVERAGE_RESPONSE_UUID);
    }

    @Test
    void patchNonExistingBenefitCoverageResponse() throws Exception {
        int databaseSizeBeforeUpdate = benefitCoverageResponseRepository.findAll().collectList().block().size();
        benefitCoverageResponse.setBenefitCoverageResponseId(count.incrementAndGet());

        // Create the BenefitCoverageResponse
        BenefitCoverageResponseDTO benefitCoverageResponseDTO = benefitCoverageResponseMapper.toDto(benefitCoverageResponse);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, benefitCoverageResponseDTO.getBenefitCoverageResponseId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(benefitCoverageResponseDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the BenefitCoverageResponse in the database
        List<BenefitCoverageResponse> benefitCoverageResponseList = benefitCoverageResponseRepository.findAll().collectList().block();
        assertThat(benefitCoverageResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchBenefitCoverageResponse() throws Exception {
        int databaseSizeBeforeUpdate = benefitCoverageResponseRepository.findAll().collectList().block().size();
        benefitCoverageResponse.setBenefitCoverageResponseId(count.incrementAndGet());

        // Create the BenefitCoverageResponse
        BenefitCoverageResponseDTO benefitCoverageResponseDTO = benefitCoverageResponseMapper.toDto(benefitCoverageResponse);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(benefitCoverageResponseDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the BenefitCoverageResponse in the database
        List<BenefitCoverageResponse> benefitCoverageResponseList = benefitCoverageResponseRepository.findAll().collectList().block();
        assertThat(benefitCoverageResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamBenefitCoverageResponse() throws Exception {
        int databaseSizeBeforeUpdate = benefitCoverageResponseRepository.findAll().collectList().block().size();
        benefitCoverageResponse.setBenefitCoverageResponseId(count.incrementAndGet());

        // Create the BenefitCoverageResponse
        BenefitCoverageResponseDTO benefitCoverageResponseDTO = benefitCoverageResponseMapper.toDto(benefitCoverageResponse);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(benefitCoverageResponseDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the BenefitCoverageResponse in the database
        List<BenefitCoverageResponse> benefitCoverageResponseList = benefitCoverageResponseRepository.findAll().collectList().block();
        assertThat(benefitCoverageResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteBenefitCoverageResponse() {
        // Initialize the database
        benefitCoverageResponseRepository.save(benefitCoverageResponse).block();

        int databaseSizeBeforeDelete = benefitCoverageResponseRepository.findAll().collectList().block().size();

        // Delete the benefitCoverageResponse
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, benefitCoverageResponse.getBenefitCoverageResponseId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<BenefitCoverageResponse> benefitCoverageResponseList = benefitCoverageResponseRepository.findAll().collectList().block();
        assertThat(benefitCoverageResponseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
