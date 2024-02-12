package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.BenefitCoverageRequest;
import com.sunknowledge.dme.rcm.repository.BenefitCoverageRequestRepository;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.service.dto.BenefitCoverageRequestDTO;
import com.sunknowledge.dme.rcm.service.mapper.BenefitCoverageRequestMapper;
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
 * Integration tests for the {@link BenefitCoverageRequestResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class BenefitCoverageRequestResourceIT {

    private static final String DEFAULT_PAYER_ID = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PROVIDER_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PROVIDER_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PROVIDER_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PROVIDER_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PROVIDER_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_PROVIDER_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_PROVIDER_NPI = "AAAAAAAAAA";
    private static final String UPDATED_PROVIDER_NPI = "BBBBBBBBBB";

    private static final String DEFAULT_PROVIDER_CITY = "AAAAAAAAAA";
    private static final String UPDATED_PROVIDER_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_PROVIDER_STATE = "AAAAAAAAAA";
    private static final String UPDATED_PROVIDER_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_PROVIDER_ZIPCODE = "AAAAAAAAAA";
    private static final String UPDATED_PROVIDER_ZIPCODE = "BBBBBBBBBB";

    private static final String DEFAULT_SUBMITTER_ID = "AAAAAAAAAA";
    private static final String UPDATED_SUBMITTER_ID = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_AS_OF_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_AS_OF_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SERVICE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_MEMBER_ID = "AAAAAAAAAA";
    private static final String UPDATED_MEMBER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_FIRST_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PATIENT_DOB = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PATIENT_DOB = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_PATIENT_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_GENDER = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_SUBSCRIBER_RELATIONSHIP = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIBER_RELATIONSHIP = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_BENEFIT_COVERAGE_REQUEST_UUID = UUID.randomUUID();
    private static final UUID UPDATED_BENEFIT_COVERAGE_REQUEST_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/benefit-coverage-requests";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{benefitCoverageRequestId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BenefitCoverageRequestRepository benefitCoverageRequestRepository;

    @Autowired
    private BenefitCoverageRequestMapper benefitCoverageRequestMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private BenefitCoverageRequest benefitCoverageRequest;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BenefitCoverageRequest createEntity(EntityManager em) {
        BenefitCoverageRequest benefitCoverageRequest = new BenefitCoverageRequest()
            .payerId(DEFAULT_PAYER_ID)
            .providerFirstName(DEFAULT_PROVIDER_FIRST_NAME)
            .providerLastName(DEFAULT_PROVIDER_LAST_NAME)
            .providerType(DEFAULT_PROVIDER_TYPE)
            .providerNpi(DEFAULT_PROVIDER_NPI)
            .providerCity(DEFAULT_PROVIDER_CITY)
            .providerState(DEFAULT_PROVIDER_STATE)
            .providerZipcode(DEFAULT_PROVIDER_ZIPCODE)
            .submitterId(DEFAULT_SUBMITTER_ID)
            .asOfDate(DEFAULT_AS_OF_DATE)
            .serviceType(DEFAULT_SERVICE_TYPE)
            .memberId(DEFAULT_MEMBER_ID)
            .patientLastName(DEFAULT_PATIENT_LAST_NAME)
            .patientFirstName(DEFAULT_PATIENT_FIRST_NAME)
            .patientDob(DEFAULT_PATIENT_DOB)
            .patientGender(DEFAULT_PATIENT_GENDER)
            .patientState(DEFAULT_PATIENT_STATE)
            .subscriberRelationship(DEFAULT_SUBSCRIBER_RELATIONSHIP)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .benefitCoverageRequestUuid(DEFAULT_BENEFIT_COVERAGE_REQUEST_UUID);
        return benefitCoverageRequest;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BenefitCoverageRequest createUpdatedEntity(EntityManager em) {
        BenefitCoverageRequest benefitCoverageRequest = new BenefitCoverageRequest()
            .payerId(UPDATED_PAYER_ID)
            .providerFirstName(UPDATED_PROVIDER_FIRST_NAME)
            .providerLastName(UPDATED_PROVIDER_LAST_NAME)
            .providerType(UPDATED_PROVIDER_TYPE)
            .providerNpi(UPDATED_PROVIDER_NPI)
            .providerCity(UPDATED_PROVIDER_CITY)
            .providerState(UPDATED_PROVIDER_STATE)
            .providerZipcode(UPDATED_PROVIDER_ZIPCODE)
            .submitterId(UPDATED_SUBMITTER_ID)
            .asOfDate(UPDATED_AS_OF_DATE)
            .serviceType(UPDATED_SERVICE_TYPE)
            .memberId(UPDATED_MEMBER_ID)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientDob(UPDATED_PATIENT_DOB)
            .patientGender(UPDATED_PATIENT_GENDER)
            .patientState(UPDATED_PATIENT_STATE)
            .subscriberRelationship(UPDATED_SUBSCRIBER_RELATIONSHIP)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .benefitCoverageRequestUuid(UPDATED_BENEFIT_COVERAGE_REQUEST_UUID);
        return benefitCoverageRequest;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(BenefitCoverageRequest.class).block();
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
        benefitCoverageRequest = createEntity(em);
    }

    @Test
    void createBenefitCoverageRequest() throws Exception {
        int databaseSizeBeforeCreate = benefitCoverageRequestRepository.findAll().collectList().block().size();
        // Create the BenefitCoverageRequest
        BenefitCoverageRequestDTO benefitCoverageRequestDTO = benefitCoverageRequestMapper.toDto(benefitCoverageRequest);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(benefitCoverageRequestDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the BenefitCoverageRequest in the database
        List<BenefitCoverageRequest> benefitCoverageRequestList = benefitCoverageRequestRepository.findAll().collectList().block();
        assertThat(benefitCoverageRequestList).hasSize(databaseSizeBeforeCreate + 1);
        BenefitCoverageRequest testBenefitCoverageRequest = benefitCoverageRequestList.get(benefitCoverageRequestList.size() - 1);
        assertThat(testBenefitCoverageRequest.getPayerId()).isEqualTo(DEFAULT_PAYER_ID);
        assertThat(testBenefitCoverageRequest.getProviderFirstName()).isEqualTo(DEFAULT_PROVIDER_FIRST_NAME);
        assertThat(testBenefitCoverageRequest.getProviderLastName()).isEqualTo(DEFAULT_PROVIDER_LAST_NAME);
        assertThat(testBenefitCoverageRequest.getProviderType()).isEqualTo(DEFAULT_PROVIDER_TYPE);
        assertThat(testBenefitCoverageRequest.getProviderNpi()).isEqualTo(DEFAULT_PROVIDER_NPI);
        assertThat(testBenefitCoverageRequest.getProviderCity()).isEqualTo(DEFAULT_PROVIDER_CITY);
        assertThat(testBenefitCoverageRequest.getProviderState()).isEqualTo(DEFAULT_PROVIDER_STATE);
        assertThat(testBenefitCoverageRequest.getProviderZipcode()).isEqualTo(DEFAULT_PROVIDER_ZIPCODE);
        assertThat(testBenefitCoverageRequest.getSubmitterId()).isEqualTo(DEFAULT_SUBMITTER_ID);
        assertThat(testBenefitCoverageRequest.getAsOfDate()).isEqualTo(DEFAULT_AS_OF_DATE);
        assertThat(testBenefitCoverageRequest.getServiceType()).isEqualTo(DEFAULT_SERVICE_TYPE);
        assertThat(testBenefitCoverageRequest.getMemberId()).isEqualTo(DEFAULT_MEMBER_ID);
        assertThat(testBenefitCoverageRequest.getPatientLastName()).isEqualTo(DEFAULT_PATIENT_LAST_NAME);
        assertThat(testBenefitCoverageRequest.getPatientFirstName()).isEqualTo(DEFAULT_PATIENT_FIRST_NAME);
        assertThat(testBenefitCoverageRequest.getPatientDob()).isEqualTo(DEFAULT_PATIENT_DOB);
        assertThat(testBenefitCoverageRequest.getPatientGender()).isEqualTo(DEFAULT_PATIENT_GENDER);
        assertThat(testBenefitCoverageRequest.getPatientState()).isEqualTo(DEFAULT_PATIENT_STATE);
        assertThat(testBenefitCoverageRequest.getSubscriberRelationship()).isEqualTo(DEFAULT_SUBSCRIBER_RELATIONSHIP);
        assertThat(testBenefitCoverageRequest.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testBenefitCoverageRequest.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testBenefitCoverageRequest.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testBenefitCoverageRequest.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testBenefitCoverageRequest.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testBenefitCoverageRequest.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testBenefitCoverageRequest.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testBenefitCoverageRequest.getBenefitCoverageRequestUuid()).isEqualTo(DEFAULT_BENEFIT_COVERAGE_REQUEST_UUID);
    }

    @Test
    void createBenefitCoverageRequestWithExistingId() throws Exception {
        // Create the BenefitCoverageRequest with an existing ID
        benefitCoverageRequest.setBenefitCoverageRequestId(1L);
        BenefitCoverageRequestDTO benefitCoverageRequestDTO = benefitCoverageRequestMapper.toDto(benefitCoverageRequest);

        int databaseSizeBeforeCreate = benefitCoverageRequestRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(benefitCoverageRequestDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the BenefitCoverageRequest in the database
        List<BenefitCoverageRequest> benefitCoverageRequestList = benefitCoverageRequestRepository.findAll().collectList().block();
        assertThat(benefitCoverageRequestList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllBenefitCoverageRequests() {
        // Initialize the database
        benefitCoverageRequestRepository.save(benefitCoverageRequest).block();

        // Get all the benefitCoverageRequestList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=benefitCoverageRequestId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].benefitCoverageRequestId")
            .value(hasItem(benefitCoverageRequest.getBenefitCoverageRequestId().intValue()))
            .jsonPath("$.[*].payerId")
            .value(hasItem(DEFAULT_PAYER_ID))
            .jsonPath("$.[*].providerFirstName")
            .value(hasItem(DEFAULT_PROVIDER_FIRST_NAME))
            .jsonPath("$.[*].providerLastName")
            .value(hasItem(DEFAULT_PROVIDER_LAST_NAME))
            .jsonPath("$.[*].providerType")
            .value(hasItem(DEFAULT_PROVIDER_TYPE))
            .jsonPath("$.[*].providerNpi")
            .value(hasItem(DEFAULT_PROVIDER_NPI))
            .jsonPath("$.[*].providerCity")
            .value(hasItem(DEFAULT_PROVIDER_CITY))
            .jsonPath("$.[*].providerState")
            .value(hasItem(DEFAULT_PROVIDER_STATE))
            .jsonPath("$.[*].providerZipcode")
            .value(hasItem(DEFAULT_PROVIDER_ZIPCODE))
            .jsonPath("$.[*].submitterId")
            .value(hasItem(DEFAULT_SUBMITTER_ID))
            .jsonPath("$.[*].asOfDate")
            .value(hasItem(DEFAULT_AS_OF_DATE.toString()))
            .jsonPath("$.[*].serviceType")
            .value(hasItem(DEFAULT_SERVICE_TYPE))
            .jsonPath("$.[*].memberId")
            .value(hasItem(DEFAULT_MEMBER_ID))
            .jsonPath("$.[*].patientLastName")
            .value(hasItem(DEFAULT_PATIENT_LAST_NAME))
            .jsonPath("$.[*].patientFirstName")
            .value(hasItem(DEFAULT_PATIENT_FIRST_NAME))
            .jsonPath("$.[*].patientDob")
            .value(hasItem(DEFAULT_PATIENT_DOB.toString()))
            .jsonPath("$.[*].patientGender")
            .value(hasItem(DEFAULT_PATIENT_GENDER))
            .jsonPath("$.[*].patientState")
            .value(hasItem(DEFAULT_PATIENT_STATE))
            .jsonPath("$.[*].subscriberRelationship")
            .value(hasItem(DEFAULT_SUBSCRIBER_RELATIONSHIP))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS))
            .jsonPath("$.[*].createdById")
            .value(hasItem(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.[*].createdByName")
            .value(hasItem(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.[*].createdDate")
            .value(hasItem(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.[*].updatedById")
            .value(hasItem(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.[*].updatedByName")
            .value(hasItem(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.[*].updatedDate")
            .value(hasItem(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.[*].benefitCoverageRequestUuid")
            .value(hasItem(DEFAULT_BENEFIT_COVERAGE_REQUEST_UUID.toString()));
    }

    @Test
    void getBenefitCoverageRequest() {
        // Initialize the database
        benefitCoverageRequestRepository.save(benefitCoverageRequest).block();

        // Get the benefitCoverageRequest
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, benefitCoverageRequest.getBenefitCoverageRequestId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.benefitCoverageRequestId")
            .value(is(benefitCoverageRequest.getBenefitCoverageRequestId().intValue()))
            .jsonPath("$.payerId")
            .value(is(DEFAULT_PAYER_ID))
            .jsonPath("$.providerFirstName")
            .value(is(DEFAULT_PROVIDER_FIRST_NAME))
            .jsonPath("$.providerLastName")
            .value(is(DEFAULT_PROVIDER_LAST_NAME))
            .jsonPath("$.providerType")
            .value(is(DEFAULT_PROVIDER_TYPE))
            .jsonPath("$.providerNpi")
            .value(is(DEFAULT_PROVIDER_NPI))
            .jsonPath("$.providerCity")
            .value(is(DEFAULT_PROVIDER_CITY))
            .jsonPath("$.providerState")
            .value(is(DEFAULT_PROVIDER_STATE))
            .jsonPath("$.providerZipcode")
            .value(is(DEFAULT_PROVIDER_ZIPCODE))
            .jsonPath("$.submitterId")
            .value(is(DEFAULT_SUBMITTER_ID))
            .jsonPath("$.asOfDate")
            .value(is(DEFAULT_AS_OF_DATE.toString()))
            .jsonPath("$.serviceType")
            .value(is(DEFAULT_SERVICE_TYPE))
            .jsonPath("$.memberId")
            .value(is(DEFAULT_MEMBER_ID))
            .jsonPath("$.patientLastName")
            .value(is(DEFAULT_PATIENT_LAST_NAME))
            .jsonPath("$.patientFirstName")
            .value(is(DEFAULT_PATIENT_FIRST_NAME))
            .jsonPath("$.patientDob")
            .value(is(DEFAULT_PATIENT_DOB.toString()))
            .jsonPath("$.patientGender")
            .value(is(DEFAULT_PATIENT_GENDER))
            .jsonPath("$.patientState")
            .value(is(DEFAULT_PATIENT_STATE))
            .jsonPath("$.subscriberRelationship")
            .value(is(DEFAULT_SUBSCRIBER_RELATIONSHIP))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS))
            .jsonPath("$.createdById")
            .value(is(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.createdByName")
            .value(is(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.createdDate")
            .value(is(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.updatedById")
            .value(is(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.updatedByName")
            .value(is(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.updatedDate")
            .value(is(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.benefitCoverageRequestUuid")
            .value(is(DEFAULT_BENEFIT_COVERAGE_REQUEST_UUID.toString()));
    }

    @Test
    void getNonExistingBenefitCoverageRequest() {
        // Get the benefitCoverageRequest
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingBenefitCoverageRequest() throws Exception {
        // Initialize the database
        benefitCoverageRequestRepository.save(benefitCoverageRequest).block();

        int databaseSizeBeforeUpdate = benefitCoverageRequestRepository.findAll().collectList().block().size();

        // Update the benefitCoverageRequest
        BenefitCoverageRequest updatedBenefitCoverageRequest = benefitCoverageRequestRepository
            .findById(benefitCoverageRequest.getBenefitCoverageRequestId())
            .block();
        updatedBenefitCoverageRequest
            .payerId(UPDATED_PAYER_ID)
            .providerFirstName(UPDATED_PROVIDER_FIRST_NAME)
            .providerLastName(UPDATED_PROVIDER_LAST_NAME)
            .providerType(UPDATED_PROVIDER_TYPE)
            .providerNpi(UPDATED_PROVIDER_NPI)
            .providerCity(UPDATED_PROVIDER_CITY)
            .providerState(UPDATED_PROVIDER_STATE)
            .providerZipcode(UPDATED_PROVIDER_ZIPCODE)
            .submitterId(UPDATED_SUBMITTER_ID)
            .asOfDate(UPDATED_AS_OF_DATE)
            .serviceType(UPDATED_SERVICE_TYPE)
            .memberId(UPDATED_MEMBER_ID)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientDob(UPDATED_PATIENT_DOB)
            .patientGender(UPDATED_PATIENT_GENDER)
            .patientState(UPDATED_PATIENT_STATE)
            .subscriberRelationship(UPDATED_SUBSCRIBER_RELATIONSHIP)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .benefitCoverageRequestUuid(UPDATED_BENEFIT_COVERAGE_REQUEST_UUID);
        BenefitCoverageRequestDTO benefitCoverageRequestDTO = benefitCoverageRequestMapper.toDto(updatedBenefitCoverageRequest);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, benefitCoverageRequestDTO.getBenefitCoverageRequestId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(benefitCoverageRequestDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the BenefitCoverageRequest in the database
        List<BenefitCoverageRequest> benefitCoverageRequestList = benefitCoverageRequestRepository.findAll().collectList().block();
        assertThat(benefitCoverageRequestList).hasSize(databaseSizeBeforeUpdate);
        BenefitCoverageRequest testBenefitCoverageRequest = benefitCoverageRequestList.get(benefitCoverageRequestList.size() - 1);
        assertThat(testBenefitCoverageRequest.getPayerId()).isEqualTo(UPDATED_PAYER_ID);
        assertThat(testBenefitCoverageRequest.getProviderFirstName()).isEqualTo(UPDATED_PROVIDER_FIRST_NAME);
        assertThat(testBenefitCoverageRequest.getProviderLastName()).isEqualTo(UPDATED_PROVIDER_LAST_NAME);
        assertThat(testBenefitCoverageRequest.getProviderType()).isEqualTo(UPDATED_PROVIDER_TYPE);
        assertThat(testBenefitCoverageRequest.getProviderNpi()).isEqualTo(UPDATED_PROVIDER_NPI);
        assertThat(testBenefitCoverageRequest.getProviderCity()).isEqualTo(UPDATED_PROVIDER_CITY);
        assertThat(testBenefitCoverageRequest.getProviderState()).isEqualTo(UPDATED_PROVIDER_STATE);
        assertThat(testBenefitCoverageRequest.getProviderZipcode()).isEqualTo(UPDATED_PROVIDER_ZIPCODE);
        assertThat(testBenefitCoverageRequest.getSubmitterId()).isEqualTo(UPDATED_SUBMITTER_ID);
        assertThat(testBenefitCoverageRequest.getAsOfDate()).isEqualTo(UPDATED_AS_OF_DATE);
        assertThat(testBenefitCoverageRequest.getServiceType()).isEqualTo(UPDATED_SERVICE_TYPE);
        assertThat(testBenefitCoverageRequest.getMemberId()).isEqualTo(UPDATED_MEMBER_ID);
        assertThat(testBenefitCoverageRequest.getPatientLastName()).isEqualTo(UPDATED_PATIENT_LAST_NAME);
        assertThat(testBenefitCoverageRequest.getPatientFirstName()).isEqualTo(UPDATED_PATIENT_FIRST_NAME);
        assertThat(testBenefitCoverageRequest.getPatientDob()).isEqualTo(UPDATED_PATIENT_DOB);
        assertThat(testBenefitCoverageRequest.getPatientGender()).isEqualTo(UPDATED_PATIENT_GENDER);
        assertThat(testBenefitCoverageRequest.getPatientState()).isEqualTo(UPDATED_PATIENT_STATE);
        assertThat(testBenefitCoverageRequest.getSubscriberRelationship()).isEqualTo(UPDATED_SUBSCRIBER_RELATIONSHIP);
        assertThat(testBenefitCoverageRequest.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testBenefitCoverageRequest.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testBenefitCoverageRequest.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testBenefitCoverageRequest.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBenefitCoverageRequest.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testBenefitCoverageRequest.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testBenefitCoverageRequest.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testBenefitCoverageRequest.getBenefitCoverageRequestUuid()).isEqualTo(UPDATED_BENEFIT_COVERAGE_REQUEST_UUID);
    }

    @Test
    void putNonExistingBenefitCoverageRequest() throws Exception {
        int databaseSizeBeforeUpdate = benefitCoverageRequestRepository.findAll().collectList().block().size();
        benefitCoverageRequest.setBenefitCoverageRequestId(count.incrementAndGet());

        // Create the BenefitCoverageRequest
        BenefitCoverageRequestDTO benefitCoverageRequestDTO = benefitCoverageRequestMapper.toDto(benefitCoverageRequest);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, benefitCoverageRequestDTO.getBenefitCoverageRequestId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(benefitCoverageRequestDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the BenefitCoverageRequest in the database
        List<BenefitCoverageRequest> benefitCoverageRequestList = benefitCoverageRequestRepository.findAll().collectList().block();
        assertThat(benefitCoverageRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchBenefitCoverageRequest() throws Exception {
        int databaseSizeBeforeUpdate = benefitCoverageRequestRepository.findAll().collectList().block().size();
        benefitCoverageRequest.setBenefitCoverageRequestId(count.incrementAndGet());

        // Create the BenefitCoverageRequest
        BenefitCoverageRequestDTO benefitCoverageRequestDTO = benefitCoverageRequestMapper.toDto(benefitCoverageRequest);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(benefitCoverageRequestDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the BenefitCoverageRequest in the database
        List<BenefitCoverageRequest> benefitCoverageRequestList = benefitCoverageRequestRepository.findAll().collectList().block();
        assertThat(benefitCoverageRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamBenefitCoverageRequest() throws Exception {
        int databaseSizeBeforeUpdate = benefitCoverageRequestRepository.findAll().collectList().block().size();
        benefitCoverageRequest.setBenefitCoverageRequestId(count.incrementAndGet());

        // Create the BenefitCoverageRequest
        BenefitCoverageRequestDTO benefitCoverageRequestDTO = benefitCoverageRequestMapper.toDto(benefitCoverageRequest);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(benefitCoverageRequestDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the BenefitCoverageRequest in the database
        List<BenefitCoverageRequest> benefitCoverageRequestList = benefitCoverageRequestRepository.findAll().collectList().block();
        assertThat(benefitCoverageRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateBenefitCoverageRequestWithPatch() throws Exception {
        // Initialize the database
        benefitCoverageRequestRepository.save(benefitCoverageRequest).block();

        int databaseSizeBeforeUpdate = benefitCoverageRequestRepository.findAll().collectList().block().size();

        // Update the benefitCoverageRequest using partial update
        BenefitCoverageRequest partialUpdatedBenefitCoverageRequest = new BenefitCoverageRequest();
        partialUpdatedBenefitCoverageRequest.setBenefitCoverageRequestId(benefitCoverageRequest.getBenefitCoverageRequestId());

        partialUpdatedBenefitCoverageRequest
            .providerLastName(UPDATED_PROVIDER_LAST_NAME)
            .providerNpi(UPDATED_PROVIDER_NPI)
            .providerCity(UPDATED_PROVIDER_CITY)
            .asOfDate(UPDATED_AS_OF_DATE)
            .serviceType(UPDATED_SERVICE_TYPE)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientDob(UPDATED_PATIENT_DOB)
            .subscriberRelationship(UPDATED_SUBSCRIBER_RELATIONSHIP)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .benefitCoverageRequestUuid(UPDATED_BENEFIT_COVERAGE_REQUEST_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedBenefitCoverageRequest.getBenefitCoverageRequestId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedBenefitCoverageRequest))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the BenefitCoverageRequest in the database
        List<BenefitCoverageRequest> benefitCoverageRequestList = benefitCoverageRequestRepository.findAll().collectList().block();
        assertThat(benefitCoverageRequestList).hasSize(databaseSizeBeforeUpdate);
        BenefitCoverageRequest testBenefitCoverageRequest = benefitCoverageRequestList.get(benefitCoverageRequestList.size() - 1);
        assertThat(testBenefitCoverageRequest.getPayerId()).isEqualTo(DEFAULT_PAYER_ID);
        assertThat(testBenefitCoverageRequest.getProviderFirstName()).isEqualTo(DEFAULT_PROVIDER_FIRST_NAME);
        assertThat(testBenefitCoverageRequest.getProviderLastName()).isEqualTo(UPDATED_PROVIDER_LAST_NAME);
        assertThat(testBenefitCoverageRequest.getProviderType()).isEqualTo(DEFAULT_PROVIDER_TYPE);
        assertThat(testBenefitCoverageRequest.getProviderNpi()).isEqualTo(UPDATED_PROVIDER_NPI);
        assertThat(testBenefitCoverageRequest.getProviderCity()).isEqualTo(UPDATED_PROVIDER_CITY);
        assertThat(testBenefitCoverageRequest.getProviderState()).isEqualTo(DEFAULT_PROVIDER_STATE);
        assertThat(testBenefitCoverageRequest.getProviderZipcode()).isEqualTo(DEFAULT_PROVIDER_ZIPCODE);
        assertThat(testBenefitCoverageRequest.getSubmitterId()).isEqualTo(DEFAULT_SUBMITTER_ID);
        assertThat(testBenefitCoverageRequest.getAsOfDate()).isEqualTo(UPDATED_AS_OF_DATE);
        assertThat(testBenefitCoverageRequest.getServiceType()).isEqualTo(UPDATED_SERVICE_TYPE);
        assertThat(testBenefitCoverageRequest.getMemberId()).isEqualTo(DEFAULT_MEMBER_ID);
        assertThat(testBenefitCoverageRequest.getPatientLastName()).isEqualTo(DEFAULT_PATIENT_LAST_NAME);
        assertThat(testBenefitCoverageRequest.getPatientFirstName()).isEqualTo(UPDATED_PATIENT_FIRST_NAME);
        assertThat(testBenefitCoverageRequest.getPatientDob()).isEqualTo(UPDATED_PATIENT_DOB);
        assertThat(testBenefitCoverageRequest.getPatientGender()).isEqualTo(DEFAULT_PATIENT_GENDER);
        assertThat(testBenefitCoverageRequest.getPatientState()).isEqualTo(DEFAULT_PATIENT_STATE);
        assertThat(testBenefitCoverageRequest.getSubscriberRelationship()).isEqualTo(UPDATED_SUBSCRIBER_RELATIONSHIP);
        assertThat(testBenefitCoverageRequest.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testBenefitCoverageRequest.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testBenefitCoverageRequest.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testBenefitCoverageRequest.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBenefitCoverageRequest.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testBenefitCoverageRequest.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testBenefitCoverageRequest.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testBenefitCoverageRequest.getBenefitCoverageRequestUuid()).isEqualTo(UPDATED_BENEFIT_COVERAGE_REQUEST_UUID);
    }

    @Test
    void fullUpdateBenefitCoverageRequestWithPatch() throws Exception {
        // Initialize the database
        benefitCoverageRequestRepository.save(benefitCoverageRequest).block();

        int databaseSizeBeforeUpdate = benefitCoverageRequestRepository.findAll().collectList().block().size();

        // Update the benefitCoverageRequest using partial update
        BenefitCoverageRequest partialUpdatedBenefitCoverageRequest = new BenefitCoverageRequest();
        partialUpdatedBenefitCoverageRequest.setBenefitCoverageRequestId(benefitCoverageRequest.getBenefitCoverageRequestId());

        partialUpdatedBenefitCoverageRequest
            .payerId(UPDATED_PAYER_ID)
            .providerFirstName(UPDATED_PROVIDER_FIRST_NAME)
            .providerLastName(UPDATED_PROVIDER_LAST_NAME)
            .providerType(UPDATED_PROVIDER_TYPE)
            .providerNpi(UPDATED_PROVIDER_NPI)
            .providerCity(UPDATED_PROVIDER_CITY)
            .providerState(UPDATED_PROVIDER_STATE)
            .providerZipcode(UPDATED_PROVIDER_ZIPCODE)
            .submitterId(UPDATED_SUBMITTER_ID)
            .asOfDate(UPDATED_AS_OF_DATE)
            .serviceType(UPDATED_SERVICE_TYPE)
            .memberId(UPDATED_MEMBER_ID)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientDob(UPDATED_PATIENT_DOB)
            .patientGender(UPDATED_PATIENT_GENDER)
            .patientState(UPDATED_PATIENT_STATE)
            .subscriberRelationship(UPDATED_SUBSCRIBER_RELATIONSHIP)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .benefitCoverageRequestUuid(UPDATED_BENEFIT_COVERAGE_REQUEST_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedBenefitCoverageRequest.getBenefitCoverageRequestId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedBenefitCoverageRequest))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the BenefitCoverageRequest in the database
        List<BenefitCoverageRequest> benefitCoverageRequestList = benefitCoverageRequestRepository.findAll().collectList().block();
        assertThat(benefitCoverageRequestList).hasSize(databaseSizeBeforeUpdate);
        BenefitCoverageRequest testBenefitCoverageRequest = benefitCoverageRequestList.get(benefitCoverageRequestList.size() - 1);
        assertThat(testBenefitCoverageRequest.getPayerId()).isEqualTo(UPDATED_PAYER_ID);
        assertThat(testBenefitCoverageRequest.getProviderFirstName()).isEqualTo(UPDATED_PROVIDER_FIRST_NAME);
        assertThat(testBenefitCoverageRequest.getProviderLastName()).isEqualTo(UPDATED_PROVIDER_LAST_NAME);
        assertThat(testBenefitCoverageRequest.getProviderType()).isEqualTo(UPDATED_PROVIDER_TYPE);
        assertThat(testBenefitCoverageRequest.getProviderNpi()).isEqualTo(UPDATED_PROVIDER_NPI);
        assertThat(testBenefitCoverageRequest.getProviderCity()).isEqualTo(UPDATED_PROVIDER_CITY);
        assertThat(testBenefitCoverageRequest.getProviderState()).isEqualTo(UPDATED_PROVIDER_STATE);
        assertThat(testBenefitCoverageRequest.getProviderZipcode()).isEqualTo(UPDATED_PROVIDER_ZIPCODE);
        assertThat(testBenefitCoverageRequest.getSubmitterId()).isEqualTo(UPDATED_SUBMITTER_ID);
        assertThat(testBenefitCoverageRequest.getAsOfDate()).isEqualTo(UPDATED_AS_OF_DATE);
        assertThat(testBenefitCoverageRequest.getServiceType()).isEqualTo(UPDATED_SERVICE_TYPE);
        assertThat(testBenefitCoverageRequest.getMemberId()).isEqualTo(UPDATED_MEMBER_ID);
        assertThat(testBenefitCoverageRequest.getPatientLastName()).isEqualTo(UPDATED_PATIENT_LAST_NAME);
        assertThat(testBenefitCoverageRequest.getPatientFirstName()).isEqualTo(UPDATED_PATIENT_FIRST_NAME);
        assertThat(testBenefitCoverageRequest.getPatientDob()).isEqualTo(UPDATED_PATIENT_DOB);
        assertThat(testBenefitCoverageRequest.getPatientGender()).isEqualTo(UPDATED_PATIENT_GENDER);
        assertThat(testBenefitCoverageRequest.getPatientState()).isEqualTo(UPDATED_PATIENT_STATE);
        assertThat(testBenefitCoverageRequest.getSubscriberRelationship()).isEqualTo(UPDATED_SUBSCRIBER_RELATIONSHIP);
        assertThat(testBenefitCoverageRequest.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testBenefitCoverageRequest.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testBenefitCoverageRequest.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testBenefitCoverageRequest.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBenefitCoverageRequest.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testBenefitCoverageRequest.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testBenefitCoverageRequest.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testBenefitCoverageRequest.getBenefitCoverageRequestUuid()).isEqualTo(UPDATED_BENEFIT_COVERAGE_REQUEST_UUID);
    }

    @Test
    void patchNonExistingBenefitCoverageRequest() throws Exception {
        int databaseSizeBeforeUpdate = benefitCoverageRequestRepository.findAll().collectList().block().size();
        benefitCoverageRequest.setBenefitCoverageRequestId(count.incrementAndGet());

        // Create the BenefitCoverageRequest
        BenefitCoverageRequestDTO benefitCoverageRequestDTO = benefitCoverageRequestMapper.toDto(benefitCoverageRequest);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, benefitCoverageRequestDTO.getBenefitCoverageRequestId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(benefitCoverageRequestDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the BenefitCoverageRequest in the database
        List<BenefitCoverageRequest> benefitCoverageRequestList = benefitCoverageRequestRepository.findAll().collectList().block();
        assertThat(benefitCoverageRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchBenefitCoverageRequest() throws Exception {
        int databaseSizeBeforeUpdate = benefitCoverageRequestRepository.findAll().collectList().block().size();
        benefitCoverageRequest.setBenefitCoverageRequestId(count.incrementAndGet());

        // Create the BenefitCoverageRequest
        BenefitCoverageRequestDTO benefitCoverageRequestDTO = benefitCoverageRequestMapper.toDto(benefitCoverageRequest);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(benefitCoverageRequestDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the BenefitCoverageRequest in the database
        List<BenefitCoverageRequest> benefitCoverageRequestList = benefitCoverageRequestRepository.findAll().collectList().block();
        assertThat(benefitCoverageRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamBenefitCoverageRequest() throws Exception {
        int databaseSizeBeforeUpdate = benefitCoverageRequestRepository.findAll().collectList().block().size();
        benefitCoverageRequest.setBenefitCoverageRequestId(count.incrementAndGet());

        // Create the BenefitCoverageRequest
        BenefitCoverageRequestDTO benefitCoverageRequestDTO = benefitCoverageRequestMapper.toDto(benefitCoverageRequest);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(benefitCoverageRequestDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the BenefitCoverageRequest in the database
        List<BenefitCoverageRequest> benefitCoverageRequestList = benefitCoverageRequestRepository.findAll().collectList().block();
        assertThat(benefitCoverageRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteBenefitCoverageRequest() {
        // Initialize the database
        benefitCoverageRequestRepository.save(benefitCoverageRequest).block();

        int databaseSizeBeforeDelete = benefitCoverageRequestRepository.findAll().collectList().block().size();

        // Delete the benefitCoverageRequest
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, benefitCoverageRequest.getBenefitCoverageRequestId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<BenefitCoverageRequest> benefitCoverageRequestList = benefitCoverageRequestRepository.findAll().collectList().block();
        assertThat(benefitCoverageRequestList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
