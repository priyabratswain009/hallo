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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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

    private static final String DEFAULT_BENEFIT_COVERAGE_REQUEST_ID = "AAAAAAAAAA";
    private static final String UPDATED_BENEFIT_COVERAGE_REQUEST_ID = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_EXPIRATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_EXPIRATION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_REQUESTED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_REQUESTED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_RESPONSE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_RESPONSE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SERVICE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_SUBSCRIBER_MEMBER_ID = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIBER_MEMBER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_RELATIONSHIP_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_RELATIONSHIP_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PAYER_ID = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PROVIDER_NPI = "AAAAAAAAAA";
    private static final String UPDATED_PROVIDER_NPI = "BBBBBBBBBB";

    private static final String DEFAULT_PLANS_STATUS_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PLANS_STATUS_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PLANS_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_PLANS_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_RESPONSE = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_RESPONSE = "BBBBBBBBBB";

    private static final String DEFAULT_SECONDARY_RESPONSE = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY_RESPONSE = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final UUID DEFAULT_BENEFIT_COVERAGE_RESPONSE_UUID = UUID.randomUUID();
    private static final UUID UPDATED_BENEFIT_COVERAGE_RESPONSE_UUID = UUID.randomUUID();

    private static final String DEFAULT_PATIENT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_SUBSCRIBER_RELATIONSHIP = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIBER_RELATIONSHIP = "BBBBBBBBBB";

    private static final String DEFAULT_MEMBER_ID = "AAAAAAAAAA";
    private static final String UPDATED_MEMBER_ID = "BBBBBBBBBB";

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
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .expirationDate(DEFAULT_EXPIRATION_DATE)
            .requestedDate(DEFAULT_REQUESTED_DATE)
            .responseDate(DEFAULT_RESPONSE_DATE)
            .serviceType(DEFAULT_SERVICE_TYPE)
            .subscriberMemberId(DEFAULT_SUBSCRIBER_MEMBER_ID)
            .patientRelationshipCode(DEFAULT_PATIENT_RELATIONSHIP_CODE)
            .payerId(DEFAULT_PAYER_ID)
            .providerNpi(DEFAULT_PROVIDER_NPI)
            .plansStatusCode(DEFAULT_PLANS_STATUS_CODE)
            .plansStatus(DEFAULT_PLANS_STATUS)
            .primaryResponse(DEFAULT_PRIMARY_RESPONSE)
            .secondaryResponse(DEFAULT_SECONDARY_RESPONSE)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .benefitCoverageResponseUuid(DEFAULT_BENEFIT_COVERAGE_RESPONSE_UUID)
            .patientState(DEFAULT_PATIENT_STATE)
            .subscriberRelationship(DEFAULT_SUBSCRIBER_RELATIONSHIP)
            .memberId(DEFAULT_MEMBER_ID);
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
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .expirationDate(UPDATED_EXPIRATION_DATE)
            .requestedDate(UPDATED_REQUESTED_DATE)
            .responseDate(UPDATED_RESPONSE_DATE)
            .serviceType(UPDATED_SERVICE_TYPE)
            .subscriberMemberId(UPDATED_SUBSCRIBER_MEMBER_ID)
            .patientRelationshipCode(UPDATED_PATIENT_RELATIONSHIP_CODE)
            .payerId(UPDATED_PAYER_ID)
            .providerNpi(UPDATED_PROVIDER_NPI)
            .plansStatusCode(UPDATED_PLANS_STATUS_CODE)
            .plansStatus(UPDATED_PLANS_STATUS)
            .primaryResponse(UPDATED_PRIMARY_RESPONSE)
            .secondaryResponse(UPDATED_SECONDARY_RESPONSE)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .benefitCoverageResponseUuid(UPDATED_BENEFIT_COVERAGE_RESPONSE_UUID)
            .patientState(UPDATED_PATIENT_STATE)
            .subscriberRelationship(UPDATED_SUBSCRIBER_RELATIONSHIP)
            .memberId(UPDATED_MEMBER_ID);
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
        assertThat(testBenefitCoverageResponse.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testBenefitCoverageResponse.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testBenefitCoverageResponse.getExpirationDate()).isEqualTo(DEFAULT_EXPIRATION_DATE);
        assertThat(testBenefitCoverageResponse.getRequestedDate()).isEqualTo(DEFAULT_REQUESTED_DATE);
        assertThat(testBenefitCoverageResponse.getResponseDate()).isEqualTo(DEFAULT_RESPONSE_DATE);
        assertThat(testBenefitCoverageResponse.getServiceType()).isEqualTo(DEFAULT_SERVICE_TYPE);
        assertThat(testBenefitCoverageResponse.getSubscriberMemberId()).isEqualTo(DEFAULT_SUBSCRIBER_MEMBER_ID);
        assertThat(testBenefitCoverageResponse.getPatientRelationshipCode()).isEqualTo(DEFAULT_PATIENT_RELATIONSHIP_CODE);
        assertThat(testBenefitCoverageResponse.getPayerId()).isEqualTo(DEFAULT_PAYER_ID);
        assertThat(testBenefitCoverageResponse.getProviderNpi()).isEqualTo(DEFAULT_PROVIDER_NPI);
        assertThat(testBenefitCoverageResponse.getPlansStatusCode()).isEqualTo(DEFAULT_PLANS_STATUS_CODE);
        assertThat(testBenefitCoverageResponse.getPlansStatus()).isEqualTo(DEFAULT_PLANS_STATUS);
        assertThat(testBenefitCoverageResponse.getPrimaryResponse()).isEqualTo(DEFAULT_PRIMARY_RESPONSE);
        assertThat(testBenefitCoverageResponse.getSecondaryResponse()).isEqualTo(DEFAULT_SECONDARY_RESPONSE);
        assertThat(testBenefitCoverageResponse.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testBenefitCoverageResponse.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testBenefitCoverageResponse.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testBenefitCoverageResponse.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testBenefitCoverageResponse.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testBenefitCoverageResponse.getBenefitCoverageResponseUuid()).isEqualTo(DEFAULT_BENEFIT_COVERAGE_RESPONSE_UUID);
        assertThat(testBenefitCoverageResponse.getPatientState()).isEqualTo(DEFAULT_PATIENT_STATE);
        assertThat(testBenefitCoverageResponse.getSubscriberRelationship()).isEqualTo(DEFAULT_SUBSCRIBER_RELATIONSHIP);
        assertThat(testBenefitCoverageResponse.getMemberId()).isEqualTo(DEFAULT_MEMBER_ID);
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
            .value(hasItem(DEFAULT_BENEFIT_COVERAGE_REQUEST_ID))
            .jsonPath("$.[*].createdDate")
            .value(hasItem(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.[*].updatedDate")
            .value(hasItem(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.[*].expirationDate")
            .value(hasItem(DEFAULT_EXPIRATION_DATE.toString()))
            .jsonPath("$.[*].requestedDate")
            .value(hasItem(DEFAULT_REQUESTED_DATE.toString()))
            .jsonPath("$.[*].responseDate")
            .value(hasItem(DEFAULT_RESPONSE_DATE.toString()))
            .jsonPath("$.[*].serviceType")
            .value(hasItem(DEFAULT_SERVICE_TYPE))
            .jsonPath("$.[*].subscriberMemberId")
            .value(hasItem(DEFAULT_SUBSCRIBER_MEMBER_ID))
            .jsonPath("$.[*].patientRelationshipCode")
            .value(hasItem(DEFAULT_PATIENT_RELATIONSHIP_CODE))
            .jsonPath("$.[*].payerId")
            .value(hasItem(DEFAULT_PAYER_ID))
            .jsonPath("$.[*].providerNpi")
            .value(hasItem(DEFAULT_PROVIDER_NPI))
            .jsonPath("$.[*].plansStatusCode")
            .value(hasItem(DEFAULT_PLANS_STATUS_CODE))
            .jsonPath("$.[*].plansStatus")
            .value(hasItem(DEFAULT_PLANS_STATUS))
            .jsonPath("$.[*].primaryResponse")
            .value(hasItem(DEFAULT_PRIMARY_RESPONSE))
            .jsonPath("$.[*].secondaryResponse")
            .value(hasItem(DEFAULT_SECONDARY_RESPONSE))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS))
            .jsonPath("$.[*].createdById")
            .value(hasItem(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.[*].createdByName")
            .value(hasItem(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.[*].updatedById")
            .value(hasItem(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.[*].updatedByName")
            .value(hasItem(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.[*].benefitCoverageResponseUuid")
            .value(hasItem(DEFAULT_BENEFIT_COVERAGE_RESPONSE_UUID.toString()))
            .jsonPath("$.[*].patientState")
            .value(hasItem(DEFAULT_PATIENT_STATE))
            .jsonPath("$.[*].subscriberRelationship")
            .value(hasItem(DEFAULT_SUBSCRIBER_RELATIONSHIP))
            .jsonPath("$.[*].memberId")
            .value(hasItem(DEFAULT_MEMBER_ID));
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
            .value(is(DEFAULT_BENEFIT_COVERAGE_REQUEST_ID))
            .jsonPath("$.createdDate")
            .value(is(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.updatedDate")
            .value(is(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.expirationDate")
            .value(is(DEFAULT_EXPIRATION_DATE.toString()))
            .jsonPath("$.requestedDate")
            .value(is(DEFAULT_REQUESTED_DATE.toString()))
            .jsonPath("$.responseDate")
            .value(is(DEFAULT_RESPONSE_DATE.toString()))
            .jsonPath("$.serviceType")
            .value(is(DEFAULT_SERVICE_TYPE))
            .jsonPath("$.subscriberMemberId")
            .value(is(DEFAULT_SUBSCRIBER_MEMBER_ID))
            .jsonPath("$.patientRelationshipCode")
            .value(is(DEFAULT_PATIENT_RELATIONSHIP_CODE))
            .jsonPath("$.payerId")
            .value(is(DEFAULT_PAYER_ID))
            .jsonPath("$.providerNpi")
            .value(is(DEFAULT_PROVIDER_NPI))
            .jsonPath("$.plansStatusCode")
            .value(is(DEFAULT_PLANS_STATUS_CODE))
            .jsonPath("$.plansStatus")
            .value(is(DEFAULT_PLANS_STATUS))
            .jsonPath("$.primaryResponse")
            .value(is(DEFAULT_PRIMARY_RESPONSE))
            .jsonPath("$.secondaryResponse")
            .value(is(DEFAULT_SECONDARY_RESPONSE))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS))
            .jsonPath("$.createdById")
            .value(is(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.createdByName")
            .value(is(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.updatedById")
            .value(is(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.updatedByName")
            .value(is(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.benefitCoverageResponseUuid")
            .value(is(DEFAULT_BENEFIT_COVERAGE_RESPONSE_UUID.toString()))
            .jsonPath("$.patientState")
            .value(is(DEFAULT_PATIENT_STATE))
            .jsonPath("$.subscriberRelationship")
            .value(is(DEFAULT_SUBSCRIBER_RELATIONSHIP))
            .jsonPath("$.memberId")
            .value(is(DEFAULT_MEMBER_ID));
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
    void putNewBenefitCoverageResponse() throws Exception {
        // Initialize the database
        benefitCoverageResponseRepository.save(benefitCoverageResponse).block();

        int databaseSizeBeforeUpdate = benefitCoverageResponseRepository.findAll().collectList().block().size();

        // Update the benefitCoverageResponse
        BenefitCoverageResponse updatedBenefitCoverageResponse = benefitCoverageResponseRepository
            .findById(benefitCoverageResponse.getBenefitCoverageResponseId())
            .block();
        updatedBenefitCoverageResponse
            .benefitCoverageRequestId(UPDATED_BENEFIT_COVERAGE_REQUEST_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .expirationDate(UPDATED_EXPIRATION_DATE)
            .requestedDate(UPDATED_REQUESTED_DATE)
            .responseDate(UPDATED_RESPONSE_DATE)
            .serviceType(UPDATED_SERVICE_TYPE)
            .subscriberMemberId(UPDATED_SUBSCRIBER_MEMBER_ID)
            .patientRelationshipCode(UPDATED_PATIENT_RELATIONSHIP_CODE)
            .payerId(UPDATED_PAYER_ID)
            .providerNpi(UPDATED_PROVIDER_NPI)
            .plansStatusCode(UPDATED_PLANS_STATUS_CODE)
            .plansStatus(UPDATED_PLANS_STATUS)
            .primaryResponse(UPDATED_PRIMARY_RESPONSE)
            .secondaryResponse(UPDATED_SECONDARY_RESPONSE)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .benefitCoverageResponseUuid(UPDATED_BENEFIT_COVERAGE_RESPONSE_UUID)
            .patientState(UPDATED_PATIENT_STATE)
            .subscriberRelationship(UPDATED_SUBSCRIBER_RELATIONSHIP)
            .memberId(UPDATED_MEMBER_ID);
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
        assertThat(testBenefitCoverageResponse.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBenefitCoverageResponse.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testBenefitCoverageResponse.getExpirationDate()).isEqualTo(UPDATED_EXPIRATION_DATE);
        assertThat(testBenefitCoverageResponse.getRequestedDate()).isEqualTo(UPDATED_REQUESTED_DATE);
        assertThat(testBenefitCoverageResponse.getResponseDate()).isEqualTo(UPDATED_RESPONSE_DATE);
        assertThat(testBenefitCoverageResponse.getServiceType()).isEqualTo(UPDATED_SERVICE_TYPE);
        assertThat(testBenefitCoverageResponse.getSubscriberMemberId()).isEqualTo(UPDATED_SUBSCRIBER_MEMBER_ID);
        assertThat(testBenefitCoverageResponse.getPatientRelationshipCode()).isEqualTo(UPDATED_PATIENT_RELATIONSHIP_CODE);
        assertThat(testBenefitCoverageResponse.getPayerId()).isEqualTo(UPDATED_PAYER_ID);
        assertThat(testBenefitCoverageResponse.getProviderNpi()).isEqualTo(UPDATED_PROVIDER_NPI);
        assertThat(testBenefitCoverageResponse.getPlansStatusCode()).isEqualTo(UPDATED_PLANS_STATUS_CODE);
        assertThat(testBenefitCoverageResponse.getPlansStatus()).isEqualTo(UPDATED_PLANS_STATUS);
        assertThat(testBenefitCoverageResponse.getPrimaryResponse()).isEqualTo(UPDATED_PRIMARY_RESPONSE);
        assertThat(testBenefitCoverageResponse.getSecondaryResponse()).isEqualTo(UPDATED_SECONDARY_RESPONSE);
        assertThat(testBenefitCoverageResponse.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testBenefitCoverageResponse.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testBenefitCoverageResponse.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testBenefitCoverageResponse.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testBenefitCoverageResponse.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testBenefitCoverageResponse.getBenefitCoverageResponseUuid()).isEqualTo(UPDATED_BENEFIT_COVERAGE_RESPONSE_UUID);
        assertThat(testBenefitCoverageResponse.getPatientState()).isEqualTo(UPDATED_PATIENT_STATE);
        assertThat(testBenefitCoverageResponse.getSubscriberRelationship()).isEqualTo(UPDATED_SUBSCRIBER_RELATIONSHIP);
        assertThat(testBenefitCoverageResponse.getMemberId()).isEqualTo(UPDATED_MEMBER_ID);
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
            .subscriberMemberId(UPDATED_SUBSCRIBER_MEMBER_ID)
            .payerId(UPDATED_PAYER_ID)
            .plansStatusCode(UPDATED_PLANS_STATUS_CODE)
            .primaryResponse(UPDATED_PRIMARY_RESPONSE)
            .secondaryResponse(UPDATED_SECONDARY_RESPONSE)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .benefitCoverageResponseUuid(UPDATED_BENEFIT_COVERAGE_RESPONSE_UUID)
            .subscriberRelationship(UPDATED_SUBSCRIBER_RELATIONSHIP);

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
        assertThat(testBenefitCoverageResponse.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testBenefitCoverageResponse.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testBenefitCoverageResponse.getExpirationDate()).isEqualTo(DEFAULT_EXPIRATION_DATE);
        assertThat(testBenefitCoverageResponse.getRequestedDate()).isEqualTo(DEFAULT_REQUESTED_DATE);
        assertThat(testBenefitCoverageResponse.getResponseDate()).isEqualTo(DEFAULT_RESPONSE_DATE);
        assertThat(testBenefitCoverageResponse.getServiceType()).isEqualTo(DEFAULT_SERVICE_TYPE);
        assertThat(testBenefitCoverageResponse.getSubscriberMemberId()).isEqualTo(UPDATED_SUBSCRIBER_MEMBER_ID);
        assertThat(testBenefitCoverageResponse.getPatientRelationshipCode()).isEqualTo(DEFAULT_PATIENT_RELATIONSHIP_CODE);
        assertThat(testBenefitCoverageResponse.getPayerId()).isEqualTo(UPDATED_PAYER_ID);
        assertThat(testBenefitCoverageResponse.getProviderNpi()).isEqualTo(DEFAULT_PROVIDER_NPI);
        assertThat(testBenefitCoverageResponse.getPlansStatusCode()).isEqualTo(UPDATED_PLANS_STATUS_CODE);
        assertThat(testBenefitCoverageResponse.getPlansStatus()).isEqualTo(DEFAULT_PLANS_STATUS);
        assertThat(testBenefitCoverageResponse.getPrimaryResponse()).isEqualTo(UPDATED_PRIMARY_RESPONSE);
        assertThat(testBenefitCoverageResponse.getSecondaryResponse()).isEqualTo(UPDATED_SECONDARY_RESPONSE);
        assertThat(testBenefitCoverageResponse.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testBenefitCoverageResponse.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testBenefitCoverageResponse.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testBenefitCoverageResponse.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testBenefitCoverageResponse.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testBenefitCoverageResponse.getBenefitCoverageResponseUuid()).isEqualTo(UPDATED_BENEFIT_COVERAGE_RESPONSE_UUID);
        assertThat(testBenefitCoverageResponse.getPatientState()).isEqualTo(DEFAULT_PATIENT_STATE);
        assertThat(testBenefitCoverageResponse.getSubscriberRelationship()).isEqualTo(UPDATED_SUBSCRIBER_RELATIONSHIP);
        assertThat(testBenefitCoverageResponse.getMemberId()).isEqualTo(DEFAULT_MEMBER_ID);
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
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .expirationDate(UPDATED_EXPIRATION_DATE)
            .requestedDate(UPDATED_REQUESTED_DATE)
            .responseDate(UPDATED_RESPONSE_DATE)
            .serviceType(UPDATED_SERVICE_TYPE)
            .subscriberMemberId(UPDATED_SUBSCRIBER_MEMBER_ID)
            .patientRelationshipCode(UPDATED_PATIENT_RELATIONSHIP_CODE)
            .payerId(UPDATED_PAYER_ID)
            .providerNpi(UPDATED_PROVIDER_NPI)
            .plansStatusCode(UPDATED_PLANS_STATUS_CODE)
            .plansStatus(UPDATED_PLANS_STATUS)
            .primaryResponse(UPDATED_PRIMARY_RESPONSE)
            .secondaryResponse(UPDATED_SECONDARY_RESPONSE)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .benefitCoverageResponseUuid(UPDATED_BENEFIT_COVERAGE_RESPONSE_UUID)
            .patientState(UPDATED_PATIENT_STATE)
            .subscriberRelationship(UPDATED_SUBSCRIBER_RELATIONSHIP)
            .memberId(UPDATED_MEMBER_ID);

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
        assertThat(testBenefitCoverageResponse.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBenefitCoverageResponse.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testBenefitCoverageResponse.getExpirationDate()).isEqualTo(UPDATED_EXPIRATION_DATE);
        assertThat(testBenefitCoverageResponse.getRequestedDate()).isEqualTo(UPDATED_REQUESTED_DATE);
        assertThat(testBenefitCoverageResponse.getResponseDate()).isEqualTo(UPDATED_RESPONSE_DATE);
        assertThat(testBenefitCoverageResponse.getServiceType()).isEqualTo(UPDATED_SERVICE_TYPE);
        assertThat(testBenefitCoverageResponse.getSubscriberMemberId()).isEqualTo(UPDATED_SUBSCRIBER_MEMBER_ID);
        assertThat(testBenefitCoverageResponse.getPatientRelationshipCode()).isEqualTo(UPDATED_PATIENT_RELATIONSHIP_CODE);
        assertThat(testBenefitCoverageResponse.getPayerId()).isEqualTo(UPDATED_PAYER_ID);
        assertThat(testBenefitCoverageResponse.getProviderNpi()).isEqualTo(UPDATED_PROVIDER_NPI);
        assertThat(testBenefitCoverageResponse.getPlansStatusCode()).isEqualTo(UPDATED_PLANS_STATUS_CODE);
        assertThat(testBenefitCoverageResponse.getPlansStatus()).isEqualTo(UPDATED_PLANS_STATUS);
        assertThat(testBenefitCoverageResponse.getPrimaryResponse()).isEqualTo(UPDATED_PRIMARY_RESPONSE);
        assertThat(testBenefitCoverageResponse.getSecondaryResponse()).isEqualTo(UPDATED_SECONDARY_RESPONSE);
        assertThat(testBenefitCoverageResponse.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testBenefitCoverageResponse.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testBenefitCoverageResponse.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testBenefitCoverageResponse.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testBenefitCoverageResponse.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testBenefitCoverageResponse.getBenefitCoverageResponseUuid()).isEqualTo(UPDATED_BENEFIT_COVERAGE_RESPONSE_UUID);
        assertThat(testBenefitCoverageResponse.getPatientState()).isEqualTo(UPDATED_PATIENT_STATE);
        assertThat(testBenefitCoverageResponse.getSubscriberRelationship()).isEqualTo(UPDATED_SUBSCRIBER_RELATIONSHIP);
        assertThat(testBenefitCoverageResponse.getMemberId()).isEqualTo(UPDATED_MEMBER_ID);
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
