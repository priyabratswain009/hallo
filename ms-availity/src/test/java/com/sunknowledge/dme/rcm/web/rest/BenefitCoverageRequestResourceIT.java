package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.BenefitCoverageRequest;
import com.sunknowledge.dme.rcm.repository.BenefitCoverageRequestRepository;
import com.sunknowledge.dme.rcm.service.dto.BenefitCoverageRequestDTO;
import com.sunknowledge.dme.rcm.service.mapper.BenefitCoverageRequestMapper;
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
 * Integration tests for the {@link BenefitCoverageRequestResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
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
    private MockMvc restBenefitCoverageRequestMockMvc;

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

    @BeforeEach
    public void initTest() {
        benefitCoverageRequest = createEntity(em);
    }

    @Test
    @Transactional
    void createBenefitCoverageRequest() throws Exception {
        int databaseSizeBeforeCreate = benefitCoverageRequestRepository.findAll().size();
        // Create the BenefitCoverageRequest
        BenefitCoverageRequestDTO benefitCoverageRequestDTO = benefitCoverageRequestMapper.toDto(benefitCoverageRequest);
        restBenefitCoverageRequestMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(benefitCoverageRequestDTO))
            )
            .andExpect(status().isCreated());

        // Validate the BenefitCoverageRequest in the database
        List<BenefitCoverageRequest> benefitCoverageRequestList = benefitCoverageRequestRepository.findAll();
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
    @Transactional
    void createBenefitCoverageRequestWithExistingId() throws Exception {
        // Create the BenefitCoverageRequest with an existing ID
        benefitCoverageRequest.setBenefitCoverageRequestId(1L);
        BenefitCoverageRequestDTO benefitCoverageRequestDTO = benefitCoverageRequestMapper.toDto(benefitCoverageRequest);

        int databaseSizeBeforeCreate = benefitCoverageRequestRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBenefitCoverageRequestMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(benefitCoverageRequestDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BenefitCoverageRequest in the database
        List<BenefitCoverageRequest> benefitCoverageRequestList = benefitCoverageRequestRepository.findAll();
        assertThat(benefitCoverageRequestList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBenefitCoverageRequests() throws Exception {
        // Initialize the database
        benefitCoverageRequestRepository.saveAndFlush(benefitCoverageRequest);

        // Get all the benefitCoverageRequestList
        restBenefitCoverageRequestMockMvc
            .perform(get(ENTITY_API_URL + "?sort=benefitCoverageRequestId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].benefitCoverageRequestId").value(hasItem(benefitCoverageRequest.getBenefitCoverageRequestId().intValue()))
            )
            .andExpect(jsonPath("$.[*].payerId").value(hasItem(DEFAULT_PAYER_ID)))
            .andExpect(jsonPath("$.[*].providerFirstName").value(hasItem(DEFAULT_PROVIDER_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].providerLastName").value(hasItem(DEFAULT_PROVIDER_LAST_NAME)))
            .andExpect(jsonPath("$.[*].providerType").value(hasItem(DEFAULT_PROVIDER_TYPE)))
            .andExpect(jsonPath("$.[*].providerNpi").value(hasItem(DEFAULT_PROVIDER_NPI)))
            .andExpect(jsonPath("$.[*].providerCity").value(hasItem(DEFAULT_PROVIDER_CITY)))
            .andExpect(jsonPath("$.[*].providerState").value(hasItem(DEFAULT_PROVIDER_STATE)))
            .andExpect(jsonPath("$.[*].providerZipcode").value(hasItem(DEFAULT_PROVIDER_ZIPCODE)))
            .andExpect(jsonPath("$.[*].submitterId").value(hasItem(DEFAULT_SUBMITTER_ID)))
            .andExpect(jsonPath("$.[*].asOfDate").value(hasItem(DEFAULT_AS_OF_DATE.toString())))
            .andExpect(jsonPath("$.[*].serviceType").value(hasItem(DEFAULT_SERVICE_TYPE)))
            .andExpect(jsonPath("$.[*].memberId").value(hasItem(DEFAULT_MEMBER_ID)))
            .andExpect(jsonPath("$.[*].patientLastName").value(hasItem(DEFAULT_PATIENT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].patientFirstName").value(hasItem(DEFAULT_PATIENT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].patientDob").value(hasItem(DEFAULT_PATIENT_DOB.toString())))
            .andExpect(jsonPath("$.[*].patientGender").value(hasItem(DEFAULT_PATIENT_GENDER)))
            .andExpect(jsonPath("$.[*].patientState").value(hasItem(DEFAULT_PATIENT_STATE)))
            .andExpect(jsonPath("$.[*].subscriberRelationship").value(hasItem(DEFAULT_SUBSCRIBER_RELATIONSHIP)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].benefitCoverageRequestUuid").value(hasItem(DEFAULT_BENEFIT_COVERAGE_REQUEST_UUID.toString())));
    }

    @Test
    @Transactional
    void getBenefitCoverageRequest() throws Exception {
        // Initialize the database
        benefitCoverageRequestRepository.saveAndFlush(benefitCoverageRequest);

        // Get the benefitCoverageRequest
        restBenefitCoverageRequestMockMvc
            .perform(get(ENTITY_API_URL_ID, benefitCoverageRequest.getBenefitCoverageRequestId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.benefitCoverageRequestId").value(benefitCoverageRequest.getBenefitCoverageRequestId().intValue()))
            .andExpect(jsonPath("$.payerId").value(DEFAULT_PAYER_ID))
            .andExpect(jsonPath("$.providerFirstName").value(DEFAULT_PROVIDER_FIRST_NAME))
            .andExpect(jsonPath("$.providerLastName").value(DEFAULT_PROVIDER_LAST_NAME))
            .andExpect(jsonPath("$.providerType").value(DEFAULT_PROVIDER_TYPE))
            .andExpect(jsonPath("$.providerNpi").value(DEFAULT_PROVIDER_NPI))
            .andExpect(jsonPath("$.providerCity").value(DEFAULT_PROVIDER_CITY))
            .andExpect(jsonPath("$.providerState").value(DEFAULT_PROVIDER_STATE))
            .andExpect(jsonPath("$.providerZipcode").value(DEFAULT_PROVIDER_ZIPCODE))
            .andExpect(jsonPath("$.submitterId").value(DEFAULT_SUBMITTER_ID))
            .andExpect(jsonPath("$.asOfDate").value(DEFAULT_AS_OF_DATE.toString()))
            .andExpect(jsonPath("$.serviceType").value(DEFAULT_SERVICE_TYPE))
            .andExpect(jsonPath("$.memberId").value(DEFAULT_MEMBER_ID))
            .andExpect(jsonPath("$.patientLastName").value(DEFAULT_PATIENT_LAST_NAME))
            .andExpect(jsonPath("$.patientFirstName").value(DEFAULT_PATIENT_FIRST_NAME))
            .andExpect(jsonPath("$.patientDob").value(DEFAULT_PATIENT_DOB.toString()))
            .andExpect(jsonPath("$.patientGender").value(DEFAULT_PATIENT_GENDER))
            .andExpect(jsonPath("$.patientState").value(DEFAULT_PATIENT_STATE))
            .andExpect(jsonPath("$.subscriberRelationship").value(DEFAULT_SUBSCRIBER_RELATIONSHIP))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.benefitCoverageRequestUuid").value(DEFAULT_BENEFIT_COVERAGE_REQUEST_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingBenefitCoverageRequest() throws Exception {
        // Get the benefitCoverageRequest
        restBenefitCoverageRequestMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBenefitCoverageRequest() throws Exception {
        // Initialize the database
        benefitCoverageRequestRepository.saveAndFlush(benefitCoverageRequest);

        int databaseSizeBeforeUpdate = benefitCoverageRequestRepository.findAll().size();

        // Update the benefitCoverageRequest
        BenefitCoverageRequest updatedBenefitCoverageRequest = benefitCoverageRequestRepository
            .findById(benefitCoverageRequest.getBenefitCoverageRequestId())
            .get();
        // Disconnect from session so that the updates on updatedBenefitCoverageRequest are not directly saved in db
        em.detach(updatedBenefitCoverageRequest);
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

        restBenefitCoverageRequestMockMvc
            .perform(
                put(ENTITY_API_URL_ID, benefitCoverageRequestDTO.getBenefitCoverageRequestId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(benefitCoverageRequestDTO))
            )
            .andExpect(status().isOk());

        // Validate the BenefitCoverageRequest in the database
        List<BenefitCoverageRequest> benefitCoverageRequestList = benefitCoverageRequestRepository.findAll();
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
    @Transactional
    void putNonExistingBenefitCoverageRequest() throws Exception {
        int databaseSizeBeforeUpdate = benefitCoverageRequestRepository.findAll().size();
        benefitCoverageRequest.setBenefitCoverageRequestId(count.incrementAndGet());

        // Create the BenefitCoverageRequest
        BenefitCoverageRequestDTO benefitCoverageRequestDTO = benefitCoverageRequestMapper.toDto(benefitCoverageRequest);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBenefitCoverageRequestMockMvc
            .perform(
                put(ENTITY_API_URL_ID, benefitCoverageRequestDTO.getBenefitCoverageRequestId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(benefitCoverageRequestDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BenefitCoverageRequest in the database
        List<BenefitCoverageRequest> benefitCoverageRequestList = benefitCoverageRequestRepository.findAll();
        assertThat(benefitCoverageRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBenefitCoverageRequest() throws Exception {
        int databaseSizeBeforeUpdate = benefitCoverageRequestRepository.findAll().size();
        benefitCoverageRequest.setBenefitCoverageRequestId(count.incrementAndGet());

        // Create the BenefitCoverageRequest
        BenefitCoverageRequestDTO benefitCoverageRequestDTO = benefitCoverageRequestMapper.toDto(benefitCoverageRequest);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBenefitCoverageRequestMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(benefitCoverageRequestDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BenefitCoverageRequest in the database
        List<BenefitCoverageRequest> benefitCoverageRequestList = benefitCoverageRequestRepository.findAll();
        assertThat(benefitCoverageRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBenefitCoverageRequest() throws Exception {
        int databaseSizeBeforeUpdate = benefitCoverageRequestRepository.findAll().size();
        benefitCoverageRequest.setBenefitCoverageRequestId(count.incrementAndGet());

        // Create the BenefitCoverageRequest
        BenefitCoverageRequestDTO benefitCoverageRequestDTO = benefitCoverageRequestMapper.toDto(benefitCoverageRequest);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBenefitCoverageRequestMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(benefitCoverageRequestDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BenefitCoverageRequest in the database
        List<BenefitCoverageRequest> benefitCoverageRequestList = benefitCoverageRequestRepository.findAll();
        assertThat(benefitCoverageRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBenefitCoverageRequestWithPatch() throws Exception {
        // Initialize the database
        benefitCoverageRequestRepository.saveAndFlush(benefitCoverageRequest);

        int databaseSizeBeforeUpdate = benefitCoverageRequestRepository.findAll().size();

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

        restBenefitCoverageRequestMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBenefitCoverageRequest.getBenefitCoverageRequestId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBenefitCoverageRequest))
            )
            .andExpect(status().isOk());

        // Validate the BenefitCoverageRequest in the database
        List<BenefitCoverageRequest> benefitCoverageRequestList = benefitCoverageRequestRepository.findAll();
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
    @Transactional
    void fullUpdateBenefitCoverageRequestWithPatch() throws Exception {
        // Initialize the database
        benefitCoverageRequestRepository.saveAndFlush(benefitCoverageRequest);

        int databaseSizeBeforeUpdate = benefitCoverageRequestRepository.findAll().size();

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

        restBenefitCoverageRequestMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBenefitCoverageRequest.getBenefitCoverageRequestId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBenefitCoverageRequest))
            )
            .andExpect(status().isOk());

        // Validate the BenefitCoverageRequest in the database
        List<BenefitCoverageRequest> benefitCoverageRequestList = benefitCoverageRequestRepository.findAll();
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
    @Transactional
    void patchNonExistingBenefitCoverageRequest() throws Exception {
        int databaseSizeBeforeUpdate = benefitCoverageRequestRepository.findAll().size();
        benefitCoverageRequest.setBenefitCoverageRequestId(count.incrementAndGet());

        // Create the BenefitCoverageRequest
        BenefitCoverageRequestDTO benefitCoverageRequestDTO = benefitCoverageRequestMapper.toDto(benefitCoverageRequest);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBenefitCoverageRequestMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, benefitCoverageRequestDTO.getBenefitCoverageRequestId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(benefitCoverageRequestDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BenefitCoverageRequest in the database
        List<BenefitCoverageRequest> benefitCoverageRequestList = benefitCoverageRequestRepository.findAll();
        assertThat(benefitCoverageRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBenefitCoverageRequest() throws Exception {
        int databaseSizeBeforeUpdate = benefitCoverageRequestRepository.findAll().size();
        benefitCoverageRequest.setBenefitCoverageRequestId(count.incrementAndGet());

        // Create the BenefitCoverageRequest
        BenefitCoverageRequestDTO benefitCoverageRequestDTO = benefitCoverageRequestMapper.toDto(benefitCoverageRequest);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBenefitCoverageRequestMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(benefitCoverageRequestDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BenefitCoverageRequest in the database
        List<BenefitCoverageRequest> benefitCoverageRequestList = benefitCoverageRequestRepository.findAll();
        assertThat(benefitCoverageRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBenefitCoverageRequest() throws Exception {
        int databaseSizeBeforeUpdate = benefitCoverageRequestRepository.findAll().size();
        benefitCoverageRequest.setBenefitCoverageRequestId(count.incrementAndGet());

        // Create the BenefitCoverageRequest
        BenefitCoverageRequestDTO benefitCoverageRequestDTO = benefitCoverageRequestMapper.toDto(benefitCoverageRequest);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBenefitCoverageRequestMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(benefitCoverageRequestDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BenefitCoverageRequest in the database
        List<BenefitCoverageRequest> benefitCoverageRequestList = benefitCoverageRequestRepository.findAll();
        assertThat(benefitCoverageRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBenefitCoverageRequest() throws Exception {
        // Initialize the database
        benefitCoverageRequestRepository.saveAndFlush(benefitCoverageRequest);

        int databaseSizeBeforeDelete = benefitCoverageRequestRepository.findAll().size();

        // Delete the benefitCoverageRequest
        restBenefitCoverageRequestMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, benefitCoverageRequest.getBenefitCoverageRequestId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BenefitCoverageRequest> benefitCoverageRequestList = benefitCoverageRequestRepository.findAll();
        assertThat(benefitCoverageRequestList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
