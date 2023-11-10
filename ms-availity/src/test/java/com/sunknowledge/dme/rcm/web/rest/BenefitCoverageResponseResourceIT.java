package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.BenefitCoverageResponse;
import com.sunknowledge.dme.rcm.repository.BenefitCoverageResponseRepository;
import com.sunknowledge.dme.rcm.service.dto.BenefitCoverageResponseDTO;
import com.sunknowledge.dme.rcm.service.mapper.BenefitCoverageResponseMapper;
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
 * Integration tests for the {@link BenefitCoverageResponseResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
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

    private static final String DEFAULT_PATIENT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_SUBSCRIBER_RELATIONSHIP = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIBER_RELATIONSHIP = "BBBBBBBBBB";

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
    private MockMvc restBenefitCoverageResponseMockMvc;

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
            .patientState(DEFAULT_PATIENT_STATE)
            .subscriberRelationship(DEFAULT_SUBSCRIBER_RELATIONSHIP)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .benefitCoverageResponseUuid(DEFAULT_BENEFIT_COVERAGE_RESPONSE_UUID)
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
            .patientState(UPDATED_PATIENT_STATE)
            .subscriberRelationship(UPDATED_SUBSCRIBER_RELATIONSHIP)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .benefitCoverageResponseUuid(UPDATED_BENEFIT_COVERAGE_RESPONSE_UUID)
            .memberId(UPDATED_MEMBER_ID);
        return benefitCoverageResponse;
    }

    @BeforeEach
    public void initTest() {
        benefitCoverageResponse = createEntity(em);
    }

    @Test
    @Transactional
    void createBenefitCoverageResponse() throws Exception {
        int databaseSizeBeforeCreate = benefitCoverageResponseRepository.findAll().size();
        // Create the BenefitCoverageResponse
        BenefitCoverageResponseDTO benefitCoverageResponseDTO = benefitCoverageResponseMapper.toDto(benefitCoverageResponse);
        restBenefitCoverageResponseMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(benefitCoverageResponseDTO))
            )
            .andExpect(status().isCreated());

        // Validate the BenefitCoverageResponse in the database
        List<BenefitCoverageResponse> benefitCoverageResponseList = benefitCoverageResponseRepository.findAll();
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
        assertThat(testBenefitCoverageResponse.getPatientState()).isEqualTo(DEFAULT_PATIENT_STATE);
        assertThat(testBenefitCoverageResponse.getSubscriberRelationship()).isEqualTo(DEFAULT_SUBSCRIBER_RELATIONSHIP);
        assertThat(testBenefitCoverageResponse.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testBenefitCoverageResponse.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testBenefitCoverageResponse.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testBenefitCoverageResponse.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testBenefitCoverageResponse.getBenefitCoverageResponseUuid()).isEqualTo(DEFAULT_BENEFIT_COVERAGE_RESPONSE_UUID);
        assertThat(testBenefitCoverageResponse.getMemberId()).isEqualTo(DEFAULT_MEMBER_ID);
    }

    @Test
    @Transactional
    void createBenefitCoverageResponseWithExistingId() throws Exception {
        // Create the BenefitCoverageResponse with an existing ID
        benefitCoverageResponse.setBenefitCoverageResponseId(1L);
        BenefitCoverageResponseDTO benefitCoverageResponseDTO = benefitCoverageResponseMapper.toDto(benefitCoverageResponse);

        int databaseSizeBeforeCreate = benefitCoverageResponseRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBenefitCoverageResponseMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(benefitCoverageResponseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BenefitCoverageResponse in the database
        List<BenefitCoverageResponse> benefitCoverageResponseList = benefitCoverageResponseRepository.findAll();
        assertThat(benefitCoverageResponseList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBenefitCoverageResponses() throws Exception {
        // Initialize the database
        benefitCoverageResponseRepository.saveAndFlush(benefitCoverageResponse);

        // Get all the benefitCoverageResponseList
        restBenefitCoverageResponseMockMvc
            .perform(get(ENTITY_API_URL + "?sort=benefitCoverageResponseId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].benefitCoverageResponseId")
                    .value(hasItem(benefitCoverageResponse.getBenefitCoverageResponseId().intValue()))
            )
            .andExpect(jsonPath("$.[*].benefitCoverageRequestId").value(hasItem(DEFAULT_BENEFIT_COVERAGE_REQUEST_ID)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].expirationDate").value(hasItem(DEFAULT_EXPIRATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].requestedDate").value(hasItem(DEFAULT_REQUESTED_DATE.toString())))
            .andExpect(jsonPath("$.[*].responseDate").value(hasItem(DEFAULT_RESPONSE_DATE.toString())))
            .andExpect(jsonPath("$.[*].serviceType").value(hasItem(DEFAULT_SERVICE_TYPE)))
            .andExpect(jsonPath("$.[*].subscriberMemberId").value(hasItem(DEFAULT_SUBSCRIBER_MEMBER_ID)))
            .andExpect(jsonPath("$.[*].patientRelationshipCode").value(hasItem(DEFAULT_PATIENT_RELATIONSHIP_CODE)))
            .andExpect(jsonPath("$.[*].payerId").value(hasItem(DEFAULT_PAYER_ID)))
            .andExpect(jsonPath("$.[*].providerNpi").value(hasItem(DEFAULT_PROVIDER_NPI)))
            .andExpect(jsonPath("$.[*].plansStatusCode").value(hasItem(DEFAULT_PLANS_STATUS_CODE)))
            .andExpect(jsonPath("$.[*].plansStatus").value(hasItem(DEFAULT_PLANS_STATUS)))
            .andExpect(jsonPath("$.[*].primaryResponse").value(hasItem(DEFAULT_PRIMARY_RESPONSE)))
            .andExpect(jsonPath("$.[*].secondaryResponse").value(hasItem(DEFAULT_SECONDARY_RESPONSE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].patientState").value(hasItem(DEFAULT_PATIENT_STATE)))
            .andExpect(jsonPath("$.[*].subscriberRelationship").value(hasItem(DEFAULT_SUBSCRIBER_RELATIONSHIP)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].benefitCoverageResponseUuid").value(hasItem(DEFAULT_BENEFIT_COVERAGE_RESPONSE_UUID.toString())))
            .andExpect(jsonPath("$.[*].memberId").value(hasItem(DEFAULT_MEMBER_ID)));
    }

    @Test
    @Transactional
    void getBenefitCoverageResponse() throws Exception {
        // Initialize the database
        benefitCoverageResponseRepository.saveAndFlush(benefitCoverageResponse);

        // Get the benefitCoverageResponse
        restBenefitCoverageResponseMockMvc
            .perform(get(ENTITY_API_URL_ID, benefitCoverageResponse.getBenefitCoverageResponseId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.benefitCoverageResponseId").value(benefitCoverageResponse.getBenefitCoverageResponseId().intValue()))
            .andExpect(jsonPath("$.benefitCoverageRequestId").value(DEFAULT_BENEFIT_COVERAGE_REQUEST_ID))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.expirationDate").value(DEFAULT_EXPIRATION_DATE.toString()))
            .andExpect(jsonPath("$.requestedDate").value(DEFAULT_REQUESTED_DATE.toString()))
            .andExpect(jsonPath("$.responseDate").value(DEFAULT_RESPONSE_DATE.toString()))
            .andExpect(jsonPath("$.serviceType").value(DEFAULT_SERVICE_TYPE))
            .andExpect(jsonPath("$.subscriberMemberId").value(DEFAULT_SUBSCRIBER_MEMBER_ID))
            .andExpect(jsonPath("$.patientRelationshipCode").value(DEFAULT_PATIENT_RELATIONSHIP_CODE))
            .andExpect(jsonPath("$.payerId").value(DEFAULT_PAYER_ID))
            .andExpect(jsonPath("$.providerNpi").value(DEFAULT_PROVIDER_NPI))
            .andExpect(jsonPath("$.plansStatusCode").value(DEFAULT_PLANS_STATUS_CODE))
            .andExpect(jsonPath("$.plansStatus").value(DEFAULT_PLANS_STATUS))
            .andExpect(jsonPath("$.primaryResponse").value(DEFAULT_PRIMARY_RESPONSE))
            .andExpect(jsonPath("$.secondaryResponse").value(DEFAULT_SECONDARY_RESPONSE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.patientState").value(DEFAULT_PATIENT_STATE))
            .andExpect(jsonPath("$.subscriberRelationship").value(DEFAULT_SUBSCRIBER_RELATIONSHIP))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.benefitCoverageResponseUuid").value(DEFAULT_BENEFIT_COVERAGE_RESPONSE_UUID.toString()))
            .andExpect(jsonPath("$.memberId").value(DEFAULT_MEMBER_ID));
    }

    @Test
    @Transactional
    void getNonExistingBenefitCoverageResponse() throws Exception {
        // Get the benefitCoverageResponse
        restBenefitCoverageResponseMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBenefitCoverageResponse() throws Exception {
        // Initialize the database
        benefitCoverageResponseRepository.saveAndFlush(benefitCoverageResponse);

        int databaseSizeBeforeUpdate = benefitCoverageResponseRepository.findAll().size();

        // Update the benefitCoverageResponse
        BenefitCoverageResponse updatedBenefitCoverageResponse = benefitCoverageResponseRepository
            .findById(benefitCoverageResponse.getBenefitCoverageResponseId())
            .get();
        // Disconnect from session so that the updates on updatedBenefitCoverageResponse are not directly saved in db
        em.detach(updatedBenefitCoverageResponse);
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
            .patientState(UPDATED_PATIENT_STATE)
            .subscriberRelationship(UPDATED_SUBSCRIBER_RELATIONSHIP)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .benefitCoverageResponseUuid(UPDATED_BENEFIT_COVERAGE_RESPONSE_UUID)
            .memberId(UPDATED_MEMBER_ID);
        BenefitCoverageResponseDTO benefitCoverageResponseDTO = benefitCoverageResponseMapper.toDto(updatedBenefitCoverageResponse);

        restBenefitCoverageResponseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, benefitCoverageResponseDTO.getBenefitCoverageResponseId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(benefitCoverageResponseDTO))
            )
            .andExpect(status().isOk());

        // Validate the BenefitCoverageResponse in the database
        List<BenefitCoverageResponse> benefitCoverageResponseList = benefitCoverageResponseRepository.findAll();
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
        assertThat(testBenefitCoverageResponse.getPatientState()).isEqualTo(UPDATED_PATIENT_STATE);
        assertThat(testBenefitCoverageResponse.getSubscriberRelationship()).isEqualTo(UPDATED_SUBSCRIBER_RELATIONSHIP);
        assertThat(testBenefitCoverageResponse.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testBenefitCoverageResponse.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testBenefitCoverageResponse.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testBenefitCoverageResponse.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testBenefitCoverageResponse.getBenefitCoverageResponseUuid()).isEqualTo(UPDATED_BENEFIT_COVERAGE_RESPONSE_UUID);
        assertThat(testBenefitCoverageResponse.getMemberId()).isEqualTo(UPDATED_MEMBER_ID);
    }

    @Test
    @Transactional
    void putNonExistingBenefitCoverageResponse() throws Exception {
        int databaseSizeBeforeUpdate = benefitCoverageResponseRepository.findAll().size();
        benefitCoverageResponse.setBenefitCoverageResponseId(count.incrementAndGet());

        // Create the BenefitCoverageResponse
        BenefitCoverageResponseDTO benefitCoverageResponseDTO = benefitCoverageResponseMapper.toDto(benefitCoverageResponse);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBenefitCoverageResponseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, benefitCoverageResponseDTO.getBenefitCoverageResponseId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(benefitCoverageResponseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BenefitCoverageResponse in the database
        List<BenefitCoverageResponse> benefitCoverageResponseList = benefitCoverageResponseRepository.findAll();
        assertThat(benefitCoverageResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBenefitCoverageResponse() throws Exception {
        int databaseSizeBeforeUpdate = benefitCoverageResponseRepository.findAll().size();
        benefitCoverageResponse.setBenefitCoverageResponseId(count.incrementAndGet());

        // Create the BenefitCoverageResponse
        BenefitCoverageResponseDTO benefitCoverageResponseDTO = benefitCoverageResponseMapper.toDto(benefitCoverageResponse);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBenefitCoverageResponseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(benefitCoverageResponseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BenefitCoverageResponse in the database
        List<BenefitCoverageResponse> benefitCoverageResponseList = benefitCoverageResponseRepository.findAll();
        assertThat(benefitCoverageResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBenefitCoverageResponse() throws Exception {
        int databaseSizeBeforeUpdate = benefitCoverageResponseRepository.findAll().size();
        benefitCoverageResponse.setBenefitCoverageResponseId(count.incrementAndGet());

        // Create the BenefitCoverageResponse
        BenefitCoverageResponseDTO benefitCoverageResponseDTO = benefitCoverageResponseMapper.toDto(benefitCoverageResponse);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBenefitCoverageResponseMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(benefitCoverageResponseDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BenefitCoverageResponse in the database
        List<BenefitCoverageResponse> benefitCoverageResponseList = benefitCoverageResponseRepository.findAll();
        assertThat(benefitCoverageResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBenefitCoverageResponseWithPatch() throws Exception {
        // Initialize the database
        benefitCoverageResponseRepository.saveAndFlush(benefitCoverageResponse);

        int databaseSizeBeforeUpdate = benefitCoverageResponseRepository.findAll().size();

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
            .patientState(UPDATED_PATIENT_STATE)
            .subscriberRelationship(UPDATED_SUBSCRIBER_RELATIONSHIP)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .benefitCoverageResponseUuid(UPDATED_BENEFIT_COVERAGE_RESPONSE_UUID);

        restBenefitCoverageResponseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBenefitCoverageResponse.getBenefitCoverageResponseId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBenefitCoverageResponse))
            )
            .andExpect(status().isOk());

        // Validate the BenefitCoverageResponse in the database
        List<BenefitCoverageResponse> benefitCoverageResponseList = benefitCoverageResponseRepository.findAll();
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
        assertThat(testBenefitCoverageResponse.getPatientState()).isEqualTo(UPDATED_PATIENT_STATE);
        assertThat(testBenefitCoverageResponse.getSubscriberRelationship()).isEqualTo(UPDATED_SUBSCRIBER_RELATIONSHIP);
        assertThat(testBenefitCoverageResponse.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testBenefitCoverageResponse.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testBenefitCoverageResponse.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testBenefitCoverageResponse.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testBenefitCoverageResponse.getBenefitCoverageResponseUuid()).isEqualTo(UPDATED_BENEFIT_COVERAGE_RESPONSE_UUID);
        assertThat(testBenefitCoverageResponse.getMemberId()).isEqualTo(DEFAULT_MEMBER_ID);
    }

    @Test
    @Transactional
    void fullUpdateBenefitCoverageResponseWithPatch() throws Exception {
        // Initialize the database
        benefitCoverageResponseRepository.saveAndFlush(benefitCoverageResponse);

        int databaseSizeBeforeUpdate = benefitCoverageResponseRepository.findAll().size();

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
            .patientState(UPDATED_PATIENT_STATE)
            .subscriberRelationship(UPDATED_SUBSCRIBER_RELATIONSHIP)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .benefitCoverageResponseUuid(UPDATED_BENEFIT_COVERAGE_RESPONSE_UUID)
            .memberId(UPDATED_MEMBER_ID);

        restBenefitCoverageResponseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBenefitCoverageResponse.getBenefitCoverageResponseId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBenefitCoverageResponse))
            )
            .andExpect(status().isOk());

        // Validate the BenefitCoverageResponse in the database
        List<BenefitCoverageResponse> benefitCoverageResponseList = benefitCoverageResponseRepository.findAll();
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
        assertThat(testBenefitCoverageResponse.getPatientState()).isEqualTo(UPDATED_PATIENT_STATE);
        assertThat(testBenefitCoverageResponse.getSubscriberRelationship()).isEqualTo(UPDATED_SUBSCRIBER_RELATIONSHIP);
        assertThat(testBenefitCoverageResponse.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testBenefitCoverageResponse.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testBenefitCoverageResponse.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testBenefitCoverageResponse.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testBenefitCoverageResponse.getBenefitCoverageResponseUuid()).isEqualTo(UPDATED_BENEFIT_COVERAGE_RESPONSE_UUID);
        assertThat(testBenefitCoverageResponse.getMemberId()).isEqualTo(UPDATED_MEMBER_ID);
    }

    @Test
    @Transactional
    void patchNonExistingBenefitCoverageResponse() throws Exception {
        int databaseSizeBeforeUpdate = benefitCoverageResponseRepository.findAll().size();
        benefitCoverageResponse.setBenefitCoverageResponseId(count.incrementAndGet());

        // Create the BenefitCoverageResponse
        BenefitCoverageResponseDTO benefitCoverageResponseDTO = benefitCoverageResponseMapper.toDto(benefitCoverageResponse);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBenefitCoverageResponseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, benefitCoverageResponseDTO.getBenefitCoverageResponseId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(benefitCoverageResponseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BenefitCoverageResponse in the database
        List<BenefitCoverageResponse> benefitCoverageResponseList = benefitCoverageResponseRepository.findAll();
        assertThat(benefitCoverageResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBenefitCoverageResponse() throws Exception {
        int databaseSizeBeforeUpdate = benefitCoverageResponseRepository.findAll().size();
        benefitCoverageResponse.setBenefitCoverageResponseId(count.incrementAndGet());

        // Create the BenefitCoverageResponse
        BenefitCoverageResponseDTO benefitCoverageResponseDTO = benefitCoverageResponseMapper.toDto(benefitCoverageResponse);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBenefitCoverageResponseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(benefitCoverageResponseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BenefitCoverageResponse in the database
        List<BenefitCoverageResponse> benefitCoverageResponseList = benefitCoverageResponseRepository.findAll();
        assertThat(benefitCoverageResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBenefitCoverageResponse() throws Exception {
        int databaseSizeBeforeUpdate = benefitCoverageResponseRepository.findAll().size();
        benefitCoverageResponse.setBenefitCoverageResponseId(count.incrementAndGet());

        // Create the BenefitCoverageResponse
        BenefitCoverageResponseDTO benefitCoverageResponseDTO = benefitCoverageResponseMapper.toDto(benefitCoverageResponse);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBenefitCoverageResponseMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(benefitCoverageResponseDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BenefitCoverageResponse in the database
        List<BenefitCoverageResponse> benefitCoverageResponseList = benefitCoverageResponseRepository.findAll();
        assertThat(benefitCoverageResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBenefitCoverageResponse() throws Exception {
        // Initialize the database
        benefitCoverageResponseRepository.saveAndFlush(benefitCoverageResponse);

        int databaseSizeBeforeDelete = benefitCoverageResponseRepository.findAll().size();

        // Delete the benefitCoverageResponse
        restBenefitCoverageResponseMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, benefitCoverageResponse.getBenefitCoverageResponseId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BenefitCoverageResponse> benefitCoverageResponseList = benefitCoverageResponseRepository.findAll();
        assertThat(benefitCoverageResponseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
