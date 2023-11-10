package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ClaimsStatus277Master;
import com.sunknowledge.dme.rcm.repository.ClaimsStatus277MasterRepository;
import com.sunknowledge.dme.rcm.service.dto.ClaimsStatus277MasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.ClaimsStatus277MasterMapper;
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
 * Integration tests for the {@link ClaimsStatus277MasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ClaimsStatus277MasterResourceIT {

    private static final String DEFAULT_CONTROL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CONTROL_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FILE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TRADING_PARTNER_CLAIM_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_TRADING_PARTNER_CLAIM_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_MEMBER_ID = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_MEMBER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICE_PROVIDER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_PROVIDER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICE_PROVIDER_NPI = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_PROVIDER_NPI = "BBBBBBBBBB";

    private static final Double DEFAULT_TOTAL_CLAIM_CHARGE_AMOUNT = 1D;
    private static final Double UPDATED_TOTAL_CLAIM_CHARGE_AMOUNT = 2D;

    private static final Double DEFAULT_TOTAL_CLAIM_PAYMENT_AMOUNT = 1D;
    private static final Double UPDATED_TOTAL_CLAIM_PAYMENT_AMOUNT = 2D;

    private static final LocalDate DEFAULT_ADJUDICATED_FINALIZED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ADJUDICATED_FINALIZED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_REMITTANCE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_REMITTANCE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CLAIM_STATUS_CATEGORY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CLAIM_STATUS_CATEGORY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CLAIM_STATUS_CATEGORY_CODE_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_CLAIM_STATUS_CATEGORY_CODE_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS_CODE = "AAAAAAAAAA";
    private static final String UPDATED_STATUS_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS_CODE_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_STATUS_CODE_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_CLEARINGHOUSE_TRACE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CLEARINGHOUSE_TRACE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_REMITTANCE_TRACE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_REMITTANCE_TRACE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_ACCOUNT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_ACCOUNT_NUMBER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CLAIM_SERVICE_BEGIN_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CLAIM_SERVICE_BEGIN_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_CLAIM_SERVICE_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CLAIM_SERVICE_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_STATUS_INFORMATION_EFFECTIVE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_STATUS_INFORMATION_EFFECTIVE_DATE = LocalDate.now(ZoneId.systemDefault());

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

    private static final UUID DEFAULT_CLAIMS_STATUS_277_MASTER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_CLAIMS_STATUS_277_MASTER_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/claims-status-277-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{claimStatus277MasterId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ClaimsStatus277MasterRepository claimsStatus277MasterRepository;

    @Autowired
    private ClaimsStatus277MasterMapper claimsStatus277MasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restClaimsStatus277MasterMockMvc;

    private ClaimsStatus277Master claimsStatus277Master;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClaimsStatus277Master createEntity(EntityManager em) {
        ClaimsStatus277Master claimsStatus277Master = new ClaimsStatus277Master()
            .controlNumber(DEFAULT_CONTROL_NUMBER)
            .fileName(DEFAULT_FILE_NAME)
            .tradingPartnerClaimNumber(DEFAULT_TRADING_PARTNER_CLAIM_NUMBER)
            .patientFirstName(DEFAULT_PATIENT_FIRST_NAME)
            .patientLastName(DEFAULT_PATIENT_LAST_NAME)
            .patientMemberId(DEFAULT_PATIENT_MEMBER_ID)
            .serviceProviderName(DEFAULT_SERVICE_PROVIDER_NAME)
            .serviceProviderNpi(DEFAULT_SERVICE_PROVIDER_NPI)
            .totalClaimChargeAmount(DEFAULT_TOTAL_CLAIM_CHARGE_AMOUNT)
            .totalClaimPaymentAmount(DEFAULT_TOTAL_CLAIM_PAYMENT_AMOUNT)
            .adjudicatedFinalizedDate(DEFAULT_ADJUDICATED_FINALIZED_DATE)
            .remittanceDate(DEFAULT_REMITTANCE_DATE)
            .claimStatusCategoryCode(DEFAULT_CLAIM_STATUS_CATEGORY_CODE)
            .claimStatusCategoryCodeValue(DEFAULT_CLAIM_STATUS_CATEGORY_CODE_VALUE)
            .statusCode(DEFAULT_STATUS_CODE)
            .statusCodeValue(DEFAULT_STATUS_CODE_VALUE)
            .clearinghouseTraceNumber(DEFAULT_CLEARINGHOUSE_TRACE_NUMBER)
            .remittanceTraceNumber(DEFAULT_REMITTANCE_TRACE_NUMBER)
            .patientAccountNumber(DEFAULT_PATIENT_ACCOUNT_NUMBER)
            .claimServiceBeginDate(DEFAULT_CLAIM_SERVICE_BEGIN_DATE)
            .claimServiceEndDate(DEFAULT_CLAIM_SERVICE_END_DATE)
            .statusInformationEffectiveDate(DEFAULT_STATUS_INFORMATION_EFFECTIVE_DATE)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .claimsStatus277MasterUuid(DEFAULT_CLAIMS_STATUS_277_MASTER_UUID);
        return claimsStatus277Master;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClaimsStatus277Master createUpdatedEntity(EntityManager em) {
        ClaimsStatus277Master claimsStatus277Master = new ClaimsStatus277Master()
            .controlNumber(UPDATED_CONTROL_NUMBER)
            .fileName(UPDATED_FILE_NAME)
            .tradingPartnerClaimNumber(UPDATED_TRADING_PARTNER_CLAIM_NUMBER)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .patientMemberId(UPDATED_PATIENT_MEMBER_ID)
            .serviceProviderName(UPDATED_SERVICE_PROVIDER_NAME)
            .serviceProviderNpi(UPDATED_SERVICE_PROVIDER_NPI)
            .totalClaimChargeAmount(UPDATED_TOTAL_CLAIM_CHARGE_AMOUNT)
            .totalClaimPaymentAmount(UPDATED_TOTAL_CLAIM_PAYMENT_AMOUNT)
            .adjudicatedFinalizedDate(UPDATED_ADJUDICATED_FINALIZED_DATE)
            .remittanceDate(UPDATED_REMITTANCE_DATE)
            .claimStatusCategoryCode(UPDATED_CLAIM_STATUS_CATEGORY_CODE)
            .claimStatusCategoryCodeValue(UPDATED_CLAIM_STATUS_CATEGORY_CODE_VALUE)
            .statusCode(UPDATED_STATUS_CODE)
            .statusCodeValue(UPDATED_STATUS_CODE_VALUE)
            .clearinghouseTraceNumber(UPDATED_CLEARINGHOUSE_TRACE_NUMBER)
            .remittanceTraceNumber(UPDATED_REMITTANCE_TRACE_NUMBER)
            .patientAccountNumber(UPDATED_PATIENT_ACCOUNT_NUMBER)
            .claimServiceBeginDate(UPDATED_CLAIM_SERVICE_BEGIN_DATE)
            .claimServiceEndDate(UPDATED_CLAIM_SERVICE_END_DATE)
            .statusInformationEffectiveDate(UPDATED_STATUS_INFORMATION_EFFECTIVE_DATE)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .claimsStatus277MasterUuid(UPDATED_CLAIMS_STATUS_277_MASTER_UUID);
        return claimsStatus277Master;
    }

    @BeforeEach
    public void initTest() {
        claimsStatus277Master = createEntity(em);
    }

    @Test
    @Transactional
    void createClaimsStatus277Master() throws Exception {
        int databaseSizeBeforeCreate = claimsStatus277MasterRepository.findAll().size();
        // Create the ClaimsStatus277Master
        ClaimsStatus277MasterDTO claimsStatus277MasterDTO = claimsStatus277MasterMapper.toDto(claimsStatus277Master);
        restClaimsStatus277MasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimsStatus277MasterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ClaimsStatus277Master in the database
        List<ClaimsStatus277Master> claimsStatus277MasterList = claimsStatus277MasterRepository.findAll();
        assertThat(claimsStatus277MasterList).hasSize(databaseSizeBeforeCreate + 1);
        ClaimsStatus277Master testClaimsStatus277Master = claimsStatus277MasterList.get(claimsStatus277MasterList.size() - 1);
        assertThat(testClaimsStatus277Master.getControlNumber()).isEqualTo(DEFAULT_CONTROL_NUMBER);
        assertThat(testClaimsStatus277Master.getFileName()).isEqualTo(DEFAULT_FILE_NAME);
        assertThat(testClaimsStatus277Master.getTradingPartnerClaimNumber()).isEqualTo(DEFAULT_TRADING_PARTNER_CLAIM_NUMBER);
        assertThat(testClaimsStatus277Master.getPatientFirstName()).isEqualTo(DEFAULT_PATIENT_FIRST_NAME);
        assertThat(testClaimsStatus277Master.getPatientLastName()).isEqualTo(DEFAULT_PATIENT_LAST_NAME);
        assertThat(testClaimsStatus277Master.getPatientMemberId()).isEqualTo(DEFAULT_PATIENT_MEMBER_ID);
        assertThat(testClaimsStatus277Master.getServiceProviderName()).isEqualTo(DEFAULT_SERVICE_PROVIDER_NAME);
        assertThat(testClaimsStatus277Master.getServiceProviderNpi()).isEqualTo(DEFAULT_SERVICE_PROVIDER_NPI);
        assertThat(testClaimsStatus277Master.getTotalClaimChargeAmount()).isEqualTo(DEFAULT_TOTAL_CLAIM_CHARGE_AMOUNT);
        assertThat(testClaimsStatus277Master.getTotalClaimPaymentAmount()).isEqualTo(DEFAULT_TOTAL_CLAIM_PAYMENT_AMOUNT);
        assertThat(testClaimsStatus277Master.getAdjudicatedFinalizedDate()).isEqualTo(DEFAULT_ADJUDICATED_FINALIZED_DATE);
        assertThat(testClaimsStatus277Master.getRemittanceDate()).isEqualTo(DEFAULT_REMITTANCE_DATE);
        assertThat(testClaimsStatus277Master.getClaimStatusCategoryCode()).isEqualTo(DEFAULT_CLAIM_STATUS_CATEGORY_CODE);
        assertThat(testClaimsStatus277Master.getClaimStatusCategoryCodeValue()).isEqualTo(DEFAULT_CLAIM_STATUS_CATEGORY_CODE_VALUE);
        assertThat(testClaimsStatus277Master.getStatusCode()).isEqualTo(DEFAULT_STATUS_CODE);
        assertThat(testClaimsStatus277Master.getStatusCodeValue()).isEqualTo(DEFAULT_STATUS_CODE_VALUE);
        assertThat(testClaimsStatus277Master.getClearinghouseTraceNumber()).isEqualTo(DEFAULT_CLEARINGHOUSE_TRACE_NUMBER);
        assertThat(testClaimsStatus277Master.getRemittanceTraceNumber()).isEqualTo(DEFAULT_REMITTANCE_TRACE_NUMBER);
        assertThat(testClaimsStatus277Master.getPatientAccountNumber()).isEqualTo(DEFAULT_PATIENT_ACCOUNT_NUMBER);
        assertThat(testClaimsStatus277Master.getClaimServiceBeginDate()).isEqualTo(DEFAULT_CLAIM_SERVICE_BEGIN_DATE);
        assertThat(testClaimsStatus277Master.getClaimServiceEndDate()).isEqualTo(DEFAULT_CLAIM_SERVICE_END_DATE);
        assertThat(testClaimsStatus277Master.getStatusInformationEffectiveDate()).isEqualTo(DEFAULT_STATUS_INFORMATION_EFFECTIVE_DATE);
        assertThat(testClaimsStatus277Master.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testClaimsStatus277Master.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testClaimsStatus277Master.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testClaimsStatus277Master.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testClaimsStatus277Master.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testClaimsStatus277Master.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testClaimsStatus277Master.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testClaimsStatus277Master.getClaimsStatus277MasterUuid()).isEqualTo(DEFAULT_CLAIMS_STATUS_277_MASTER_UUID);
    }

    @Test
    @Transactional
    void createClaimsStatus277MasterWithExistingId() throws Exception {
        // Create the ClaimsStatus277Master with an existing ID
        claimsStatus277Master.setClaimStatus277MasterId(1L);
        ClaimsStatus277MasterDTO claimsStatus277MasterDTO = claimsStatus277MasterMapper.toDto(claimsStatus277Master);

        int databaseSizeBeforeCreate = claimsStatus277MasterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restClaimsStatus277MasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimsStatus277MasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimsStatus277Master in the database
        List<ClaimsStatus277Master> claimsStatus277MasterList = claimsStatus277MasterRepository.findAll();
        assertThat(claimsStatus277MasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllClaimsStatus277Masters() throws Exception {
        // Initialize the database
        claimsStatus277MasterRepository.saveAndFlush(claimsStatus277Master);

        // Get all the claimsStatus277MasterList
        restClaimsStatus277MasterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=claimStatus277MasterId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].claimStatus277MasterId").value(hasItem(claimsStatus277Master.getClaimStatus277MasterId().intValue()))
            )
            .andExpect(jsonPath("$.[*].controlNumber").value(hasItem(DEFAULT_CONTROL_NUMBER)))
            .andExpect(jsonPath("$.[*].fileName").value(hasItem(DEFAULT_FILE_NAME)))
            .andExpect(jsonPath("$.[*].tradingPartnerClaimNumber").value(hasItem(DEFAULT_TRADING_PARTNER_CLAIM_NUMBER)))
            .andExpect(jsonPath("$.[*].patientFirstName").value(hasItem(DEFAULT_PATIENT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].patientLastName").value(hasItem(DEFAULT_PATIENT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].patientMemberId").value(hasItem(DEFAULT_PATIENT_MEMBER_ID)))
            .andExpect(jsonPath("$.[*].serviceProviderName").value(hasItem(DEFAULT_SERVICE_PROVIDER_NAME)))
            .andExpect(jsonPath("$.[*].serviceProviderNpi").value(hasItem(DEFAULT_SERVICE_PROVIDER_NPI)))
            .andExpect(jsonPath("$.[*].totalClaimChargeAmount").value(hasItem(DEFAULT_TOTAL_CLAIM_CHARGE_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].totalClaimPaymentAmount").value(hasItem(DEFAULT_TOTAL_CLAIM_PAYMENT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].adjudicatedFinalizedDate").value(hasItem(DEFAULT_ADJUDICATED_FINALIZED_DATE.toString())))
            .andExpect(jsonPath("$.[*].remittanceDate").value(hasItem(DEFAULT_REMITTANCE_DATE.toString())))
            .andExpect(jsonPath("$.[*].claimStatusCategoryCode").value(hasItem(DEFAULT_CLAIM_STATUS_CATEGORY_CODE)))
            .andExpect(jsonPath("$.[*].claimStatusCategoryCodeValue").value(hasItem(DEFAULT_CLAIM_STATUS_CATEGORY_CODE_VALUE)))
            .andExpect(jsonPath("$.[*].statusCode").value(hasItem(DEFAULT_STATUS_CODE)))
            .andExpect(jsonPath("$.[*].statusCodeValue").value(hasItem(DEFAULT_STATUS_CODE_VALUE)))
            .andExpect(jsonPath("$.[*].clearinghouseTraceNumber").value(hasItem(DEFAULT_CLEARINGHOUSE_TRACE_NUMBER)))
            .andExpect(jsonPath("$.[*].remittanceTraceNumber").value(hasItem(DEFAULT_REMITTANCE_TRACE_NUMBER)))
            .andExpect(jsonPath("$.[*].patientAccountNumber").value(hasItem(DEFAULT_PATIENT_ACCOUNT_NUMBER)))
            .andExpect(jsonPath("$.[*].claimServiceBeginDate").value(hasItem(DEFAULT_CLAIM_SERVICE_BEGIN_DATE.toString())))
            .andExpect(jsonPath("$.[*].claimServiceEndDate").value(hasItem(DEFAULT_CLAIM_SERVICE_END_DATE.toString())))
            .andExpect(
                jsonPath("$.[*].statusInformationEffectiveDate").value(hasItem(DEFAULT_STATUS_INFORMATION_EFFECTIVE_DATE.toString()))
            )
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].claimsStatus277MasterUuid").value(hasItem(DEFAULT_CLAIMS_STATUS_277_MASTER_UUID.toString())));
    }

    @Test
    @Transactional
    void getClaimsStatus277Master() throws Exception {
        // Initialize the database
        claimsStatus277MasterRepository.saveAndFlush(claimsStatus277Master);

        // Get the claimsStatus277Master
        restClaimsStatus277MasterMockMvc
            .perform(get(ENTITY_API_URL_ID, claimsStatus277Master.getClaimStatus277MasterId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.claimStatus277MasterId").value(claimsStatus277Master.getClaimStatus277MasterId().intValue()))
            .andExpect(jsonPath("$.controlNumber").value(DEFAULT_CONTROL_NUMBER))
            .andExpect(jsonPath("$.fileName").value(DEFAULT_FILE_NAME))
            .andExpect(jsonPath("$.tradingPartnerClaimNumber").value(DEFAULT_TRADING_PARTNER_CLAIM_NUMBER))
            .andExpect(jsonPath("$.patientFirstName").value(DEFAULT_PATIENT_FIRST_NAME))
            .andExpect(jsonPath("$.patientLastName").value(DEFAULT_PATIENT_LAST_NAME))
            .andExpect(jsonPath("$.patientMemberId").value(DEFAULT_PATIENT_MEMBER_ID))
            .andExpect(jsonPath("$.serviceProviderName").value(DEFAULT_SERVICE_PROVIDER_NAME))
            .andExpect(jsonPath("$.serviceProviderNpi").value(DEFAULT_SERVICE_PROVIDER_NPI))
            .andExpect(jsonPath("$.totalClaimChargeAmount").value(DEFAULT_TOTAL_CLAIM_CHARGE_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.totalClaimPaymentAmount").value(DEFAULT_TOTAL_CLAIM_PAYMENT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.adjudicatedFinalizedDate").value(DEFAULT_ADJUDICATED_FINALIZED_DATE.toString()))
            .andExpect(jsonPath("$.remittanceDate").value(DEFAULT_REMITTANCE_DATE.toString()))
            .andExpect(jsonPath("$.claimStatusCategoryCode").value(DEFAULT_CLAIM_STATUS_CATEGORY_CODE))
            .andExpect(jsonPath("$.claimStatusCategoryCodeValue").value(DEFAULT_CLAIM_STATUS_CATEGORY_CODE_VALUE))
            .andExpect(jsonPath("$.statusCode").value(DEFAULT_STATUS_CODE))
            .andExpect(jsonPath("$.statusCodeValue").value(DEFAULT_STATUS_CODE_VALUE))
            .andExpect(jsonPath("$.clearinghouseTraceNumber").value(DEFAULT_CLEARINGHOUSE_TRACE_NUMBER))
            .andExpect(jsonPath("$.remittanceTraceNumber").value(DEFAULT_REMITTANCE_TRACE_NUMBER))
            .andExpect(jsonPath("$.patientAccountNumber").value(DEFAULT_PATIENT_ACCOUNT_NUMBER))
            .andExpect(jsonPath("$.claimServiceBeginDate").value(DEFAULT_CLAIM_SERVICE_BEGIN_DATE.toString()))
            .andExpect(jsonPath("$.claimServiceEndDate").value(DEFAULT_CLAIM_SERVICE_END_DATE.toString()))
            .andExpect(jsonPath("$.statusInformationEffectiveDate").value(DEFAULT_STATUS_INFORMATION_EFFECTIVE_DATE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.claimsStatus277MasterUuid").value(DEFAULT_CLAIMS_STATUS_277_MASTER_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingClaimsStatus277Master() throws Exception {
        // Get the claimsStatus277Master
        restClaimsStatus277MasterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewClaimsStatus277Master() throws Exception {
        // Initialize the database
        claimsStatus277MasterRepository.saveAndFlush(claimsStatus277Master);

        int databaseSizeBeforeUpdate = claimsStatus277MasterRepository.findAll().size();

        // Update the claimsStatus277Master
        ClaimsStatus277Master updatedClaimsStatus277Master = claimsStatus277MasterRepository
            .findById(claimsStatus277Master.getClaimStatus277MasterId())
            .get();
        // Disconnect from session so that the updates on updatedClaimsStatus277Master are not directly saved in db
        em.detach(updatedClaimsStatus277Master);
        updatedClaimsStatus277Master
            .controlNumber(UPDATED_CONTROL_NUMBER)
            .fileName(UPDATED_FILE_NAME)
            .tradingPartnerClaimNumber(UPDATED_TRADING_PARTNER_CLAIM_NUMBER)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .patientMemberId(UPDATED_PATIENT_MEMBER_ID)
            .serviceProviderName(UPDATED_SERVICE_PROVIDER_NAME)
            .serviceProviderNpi(UPDATED_SERVICE_PROVIDER_NPI)
            .totalClaimChargeAmount(UPDATED_TOTAL_CLAIM_CHARGE_AMOUNT)
            .totalClaimPaymentAmount(UPDATED_TOTAL_CLAIM_PAYMENT_AMOUNT)
            .adjudicatedFinalizedDate(UPDATED_ADJUDICATED_FINALIZED_DATE)
            .remittanceDate(UPDATED_REMITTANCE_DATE)
            .claimStatusCategoryCode(UPDATED_CLAIM_STATUS_CATEGORY_CODE)
            .claimStatusCategoryCodeValue(UPDATED_CLAIM_STATUS_CATEGORY_CODE_VALUE)
            .statusCode(UPDATED_STATUS_CODE)
            .statusCodeValue(UPDATED_STATUS_CODE_VALUE)
            .clearinghouseTraceNumber(UPDATED_CLEARINGHOUSE_TRACE_NUMBER)
            .remittanceTraceNumber(UPDATED_REMITTANCE_TRACE_NUMBER)
            .patientAccountNumber(UPDATED_PATIENT_ACCOUNT_NUMBER)
            .claimServiceBeginDate(UPDATED_CLAIM_SERVICE_BEGIN_DATE)
            .claimServiceEndDate(UPDATED_CLAIM_SERVICE_END_DATE)
            .statusInformationEffectiveDate(UPDATED_STATUS_INFORMATION_EFFECTIVE_DATE)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .claimsStatus277MasterUuid(UPDATED_CLAIMS_STATUS_277_MASTER_UUID);
        ClaimsStatus277MasterDTO claimsStatus277MasterDTO = claimsStatus277MasterMapper.toDto(updatedClaimsStatus277Master);

        restClaimsStatus277MasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, claimsStatus277MasterDTO.getClaimStatus277MasterId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimsStatus277MasterDTO))
            )
            .andExpect(status().isOk());

        // Validate the ClaimsStatus277Master in the database
        List<ClaimsStatus277Master> claimsStatus277MasterList = claimsStatus277MasterRepository.findAll();
        assertThat(claimsStatus277MasterList).hasSize(databaseSizeBeforeUpdate);
        ClaimsStatus277Master testClaimsStatus277Master = claimsStatus277MasterList.get(claimsStatus277MasterList.size() - 1);
        assertThat(testClaimsStatus277Master.getControlNumber()).isEqualTo(UPDATED_CONTROL_NUMBER);
        assertThat(testClaimsStatus277Master.getFileName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testClaimsStatus277Master.getTradingPartnerClaimNumber()).isEqualTo(UPDATED_TRADING_PARTNER_CLAIM_NUMBER);
        assertThat(testClaimsStatus277Master.getPatientFirstName()).isEqualTo(UPDATED_PATIENT_FIRST_NAME);
        assertThat(testClaimsStatus277Master.getPatientLastName()).isEqualTo(UPDATED_PATIENT_LAST_NAME);
        assertThat(testClaimsStatus277Master.getPatientMemberId()).isEqualTo(UPDATED_PATIENT_MEMBER_ID);
        assertThat(testClaimsStatus277Master.getServiceProviderName()).isEqualTo(UPDATED_SERVICE_PROVIDER_NAME);
        assertThat(testClaimsStatus277Master.getServiceProviderNpi()).isEqualTo(UPDATED_SERVICE_PROVIDER_NPI);
        assertThat(testClaimsStatus277Master.getTotalClaimChargeAmount()).isEqualTo(UPDATED_TOTAL_CLAIM_CHARGE_AMOUNT);
        assertThat(testClaimsStatus277Master.getTotalClaimPaymentAmount()).isEqualTo(UPDATED_TOTAL_CLAIM_PAYMENT_AMOUNT);
        assertThat(testClaimsStatus277Master.getAdjudicatedFinalizedDate()).isEqualTo(UPDATED_ADJUDICATED_FINALIZED_DATE);
        assertThat(testClaimsStatus277Master.getRemittanceDate()).isEqualTo(UPDATED_REMITTANCE_DATE);
        assertThat(testClaimsStatus277Master.getClaimStatusCategoryCode()).isEqualTo(UPDATED_CLAIM_STATUS_CATEGORY_CODE);
        assertThat(testClaimsStatus277Master.getClaimStatusCategoryCodeValue()).isEqualTo(UPDATED_CLAIM_STATUS_CATEGORY_CODE_VALUE);
        assertThat(testClaimsStatus277Master.getStatusCode()).isEqualTo(UPDATED_STATUS_CODE);
        assertThat(testClaimsStatus277Master.getStatusCodeValue()).isEqualTo(UPDATED_STATUS_CODE_VALUE);
        assertThat(testClaimsStatus277Master.getClearinghouseTraceNumber()).isEqualTo(UPDATED_CLEARINGHOUSE_TRACE_NUMBER);
        assertThat(testClaimsStatus277Master.getRemittanceTraceNumber()).isEqualTo(UPDATED_REMITTANCE_TRACE_NUMBER);
        assertThat(testClaimsStatus277Master.getPatientAccountNumber()).isEqualTo(UPDATED_PATIENT_ACCOUNT_NUMBER);
        assertThat(testClaimsStatus277Master.getClaimServiceBeginDate()).isEqualTo(UPDATED_CLAIM_SERVICE_BEGIN_DATE);
        assertThat(testClaimsStatus277Master.getClaimServiceEndDate()).isEqualTo(UPDATED_CLAIM_SERVICE_END_DATE);
        assertThat(testClaimsStatus277Master.getStatusInformationEffectiveDate()).isEqualTo(UPDATED_STATUS_INFORMATION_EFFECTIVE_DATE);
        assertThat(testClaimsStatus277Master.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testClaimsStatus277Master.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testClaimsStatus277Master.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testClaimsStatus277Master.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testClaimsStatus277Master.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testClaimsStatus277Master.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testClaimsStatus277Master.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testClaimsStatus277Master.getClaimsStatus277MasterUuid()).isEqualTo(UPDATED_CLAIMS_STATUS_277_MASTER_UUID);
    }

    @Test
    @Transactional
    void putNonExistingClaimsStatus277Master() throws Exception {
        int databaseSizeBeforeUpdate = claimsStatus277MasterRepository.findAll().size();
        claimsStatus277Master.setClaimStatus277MasterId(count.incrementAndGet());

        // Create the ClaimsStatus277Master
        ClaimsStatus277MasterDTO claimsStatus277MasterDTO = claimsStatus277MasterMapper.toDto(claimsStatus277Master);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClaimsStatus277MasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, claimsStatus277MasterDTO.getClaimStatus277MasterId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimsStatus277MasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimsStatus277Master in the database
        List<ClaimsStatus277Master> claimsStatus277MasterList = claimsStatus277MasterRepository.findAll();
        assertThat(claimsStatus277MasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchClaimsStatus277Master() throws Exception {
        int databaseSizeBeforeUpdate = claimsStatus277MasterRepository.findAll().size();
        claimsStatus277Master.setClaimStatus277MasterId(count.incrementAndGet());

        // Create the ClaimsStatus277Master
        ClaimsStatus277MasterDTO claimsStatus277MasterDTO = claimsStatus277MasterMapper.toDto(claimsStatus277Master);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaimsStatus277MasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimsStatus277MasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimsStatus277Master in the database
        List<ClaimsStatus277Master> claimsStatus277MasterList = claimsStatus277MasterRepository.findAll();
        assertThat(claimsStatus277MasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamClaimsStatus277Master() throws Exception {
        int databaseSizeBeforeUpdate = claimsStatus277MasterRepository.findAll().size();
        claimsStatus277Master.setClaimStatus277MasterId(count.incrementAndGet());

        // Create the ClaimsStatus277Master
        ClaimsStatus277MasterDTO claimsStatus277MasterDTO = claimsStatus277MasterMapper.toDto(claimsStatus277Master);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaimsStatus277MasterMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimsStatus277MasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ClaimsStatus277Master in the database
        List<ClaimsStatus277Master> claimsStatus277MasterList = claimsStatus277MasterRepository.findAll();
        assertThat(claimsStatus277MasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateClaimsStatus277MasterWithPatch() throws Exception {
        // Initialize the database
        claimsStatus277MasterRepository.saveAndFlush(claimsStatus277Master);

        int databaseSizeBeforeUpdate = claimsStatus277MasterRepository.findAll().size();

        // Update the claimsStatus277Master using partial update
        ClaimsStatus277Master partialUpdatedClaimsStatus277Master = new ClaimsStatus277Master();
        partialUpdatedClaimsStatus277Master.setClaimStatus277MasterId(claimsStatus277Master.getClaimStatus277MasterId());

        partialUpdatedClaimsStatus277Master
            .fileName(UPDATED_FILE_NAME)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .patientMemberId(UPDATED_PATIENT_MEMBER_ID)
            .serviceProviderName(UPDATED_SERVICE_PROVIDER_NAME)
            .serviceProviderNpi(UPDATED_SERVICE_PROVIDER_NPI)
            .totalClaimPaymentAmount(UPDATED_TOTAL_CLAIM_PAYMENT_AMOUNT)
            .adjudicatedFinalizedDate(UPDATED_ADJUDICATED_FINALIZED_DATE)
            .remittanceDate(UPDATED_REMITTANCE_DATE)
            .claimStatusCategoryCode(UPDATED_CLAIM_STATUS_CATEGORY_CODE)
            .claimStatusCategoryCodeValue(UPDATED_CLAIM_STATUS_CATEGORY_CODE_VALUE)
            .statusCode(UPDATED_STATUS_CODE)
            .statusCodeValue(UPDATED_STATUS_CODE_VALUE)
            .remittanceTraceNumber(UPDATED_REMITTANCE_TRACE_NUMBER)
            .patientAccountNumber(UPDATED_PATIENT_ACCOUNT_NUMBER)
            .claimServiceEndDate(UPDATED_CLAIM_SERVICE_END_DATE)
            .statusInformationEffectiveDate(UPDATED_STATUS_INFORMATION_EFFECTIVE_DATE)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .claimsStatus277MasterUuid(UPDATED_CLAIMS_STATUS_277_MASTER_UUID);

        restClaimsStatus277MasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClaimsStatus277Master.getClaimStatus277MasterId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClaimsStatus277Master))
            )
            .andExpect(status().isOk());

        // Validate the ClaimsStatus277Master in the database
        List<ClaimsStatus277Master> claimsStatus277MasterList = claimsStatus277MasterRepository.findAll();
        assertThat(claimsStatus277MasterList).hasSize(databaseSizeBeforeUpdate);
        ClaimsStatus277Master testClaimsStatus277Master = claimsStatus277MasterList.get(claimsStatus277MasterList.size() - 1);
        assertThat(testClaimsStatus277Master.getControlNumber()).isEqualTo(DEFAULT_CONTROL_NUMBER);
        assertThat(testClaimsStatus277Master.getFileName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testClaimsStatus277Master.getTradingPartnerClaimNumber()).isEqualTo(DEFAULT_TRADING_PARTNER_CLAIM_NUMBER);
        assertThat(testClaimsStatus277Master.getPatientFirstName()).isEqualTo(UPDATED_PATIENT_FIRST_NAME);
        assertThat(testClaimsStatus277Master.getPatientLastName()).isEqualTo(UPDATED_PATIENT_LAST_NAME);
        assertThat(testClaimsStatus277Master.getPatientMemberId()).isEqualTo(UPDATED_PATIENT_MEMBER_ID);
        assertThat(testClaimsStatus277Master.getServiceProviderName()).isEqualTo(UPDATED_SERVICE_PROVIDER_NAME);
        assertThat(testClaimsStatus277Master.getServiceProviderNpi()).isEqualTo(UPDATED_SERVICE_PROVIDER_NPI);
        assertThat(testClaimsStatus277Master.getTotalClaimChargeAmount()).isEqualTo(DEFAULT_TOTAL_CLAIM_CHARGE_AMOUNT);
        assertThat(testClaimsStatus277Master.getTotalClaimPaymentAmount()).isEqualTo(UPDATED_TOTAL_CLAIM_PAYMENT_AMOUNT);
        assertThat(testClaimsStatus277Master.getAdjudicatedFinalizedDate()).isEqualTo(UPDATED_ADJUDICATED_FINALIZED_DATE);
        assertThat(testClaimsStatus277Master.getRemittanceDate()).isEqualTo(UPDATED_REMITTANCE_DATE);
        assertThat(testClaimsStatus277Master.getClaimStatusCategoryCode()).isEqualTo(UPDATED_CLAIM_STATUS_CATEGORY_CODE);
        assertThat(testClaimsStatus277Master.getClaimStatusCategoryCodeValue()).isEqualTo(UPDATED_CLAIM_STATUS_CATEGORY_CODE_VALUE);
        assertThat(testClaimsStatus277Master.getStatusCode()).isEqualTo(UPDATED_STATUS_CODE);
        assertThat(testClaimsStatus277Master.getStatusCodeValue()).isEqualTo(UPDATED_STATUS_CODE_VALUE);
        assertThat(testClaimsStatus277Master.getClearinghouseTraceNumber()).isEqualTo(DEFAULT_CLEARINGHOUSE_TRACE_NUMBER);
        assertThat(testClaimsStatus277Master.getRemittanceTraceNumber()).isEqualTo(UPDATED_REMITTANCE_TRACE_NUMBER);
        assertThat(testClaimsStatus277Master.getPatientAccountNumber()).isEqualTo(UPDATED_PATIENT_ACCOUNT_NUMBER);
        assertThat(testClaimsStatus277Master.getClaimServiceBeginDate()).isEqualTo(DEFAULT_CLAIM_SERVICE_BEGIN_DATE);
        assertThat(testClaimsStatus277Master.getClaimServiceEndDate()).isEqualTo(UPDATED_CLAIM_SERVICE_END_DATE);
        assertThat(testClaimsStatus277Master.getStatusInformationEffectiveDate()).isEqualTo(UPDATED_STATUS_INFORMATION_EFFECTIVE_DATE);
        assertThat(testClaimsStatus277Master.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testClaimsStatus277Master.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testClaimsStatus277Master.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testClaimsStatus277Master.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testClaimsStatus277Master.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testClaimsStatus277Master.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testClaimsStatus277Master.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testClaimsStatus277Master.getClaimsStatus277MasterUuid()).isEqualTo(UPDATED_CLAIMS_STATUS_277_MASTER_UUID);
    }

    @Test
    @Transactional
    void fullUpdateClaimsStatus277MasterWithPatch() throws Exception {
        // Initialize the database
        claimsStatus277MasterRepository.saveAndFlush(claimsStatus277Master);

        int databaseSizeBeforeUpdate = claimsStatus277MasterRepository.findAll().size();

        // Update the claimsStatus277Master using partial update
        ClaimsStatus277Master partialUpdatedClaimsStatus277Master = new ClaimsStatus277Master();
        partialUpdatedClaimsStatus277Master.setClaimStatus277MasterId(claimsStatus277Master.getClaimStatus277MasterId());

        partialUpdatedClaimsStatus277Master
            .controlNumber(UPDATED_CONTROL_NUMBER)
            .fileName(UPDATED_FILE_NAME)
            .tradingPartnerClaimNumber(UPDATED_TRADING_PARTNER_CLAIM_NUMBER)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .patientMemberId(UPDATED_PATIENT_MEMBER_ID)
            .serviceProviderName(UPDATED_SERVICE_PROVIDER_NAME)
            .serviceProviderNpi(UPDATED_SERVICE_PROVIDER_NPI)
            .totalClaimChargeAmount(UPDATED_TOTAL_CLAIM_CHARGE_AMOUNT)
            .totalClaimPaymentAmount(UPDATED_TOTAL_CLAIM_PAYMENT_AMOUNT)
            .adjudicatedFinalizedDate(UPDATED_ADJUDICATED_FINALIZED_DATE)
            .remittanceDate(UPDATED_REMITTANCE_DATE)
            .claimStatusCategoryCode(UPDATED_CLAIM_STATUS_CATEGORY_CODE)
            .claimStatusCategoryCodeValue(UPDATED_CLAIM_STATUS_CATEGORY_CODE_VALUE)
            .statusCode(UPDATED_STATUS_CODE)
            .statusCodeValue(UPDATED_STATUS_CODE_VALUE)
            .clearinghouseTraceNumber(UPDATED_CLEARINGHOUSE_TRACE_NUMBER)
            .remittanceTraceNumber(UPDATED_REMITTANCE_TRACE_NUMBER)
            .patientAccountNumber(UPDATED_PATIENT_ACCOUNT_NUMBER)
            .claimServiceBeginDate(UPDATED_CLAIM_SERVICE_BEGIN_DATE)
            .claimServiceEndDate(UPDATED_CLAIM_SERVICE_END_DATE)
            .statusInformationEffectiveDate(UPDATED_STATUS_INFORMATION_EFFECTIVE_DATE)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .claimsStatus277MasterUuid(UPDATED_CLAIMS_STATUS_277_MASTER_UUID);

        restClaimsStatus277MasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClaimsStatus277Master.getClaimStatus277MasterId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClaimsStatus277Master))
            )
            .andExpect(status().isOk());

        // Validate the ClaimsStatus277Master in the database
        List<ClaimsStatus277Master> claimsStatus277MasterList = claimsStatus277MasterRepository.findAll();
        assertThat(claimsStatus277MasterList).hasSize(databaseSizeBeforeUpdate);
        ClaimsStatus277Master testClaimsStatus277Master = claimsStatus277MasterList.get(claimsStatus277MasterList.size() - 1);
        assertThat(testClaimsStatus277Master.getControlNumber()).isEqualTo(UPDATED_CONTROL_NUMBER);
        assertThat(testClaimsStatus277Master.getFileName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testClaimsStatus277Master.getTradingPartnerClaimNumber()).isEqualTo(UPDATED_TRADING_PARTNER_CLAIM_NUMBER);
        assertThat(testClaimsStatus277Master.getPatientFirstName()).isEqualTo(UPDATED_PATIENT_FIRST_NAME);
        assertThat(testClaimsStatus277Master.getPatientLastName()).isEqualTo(UPDATED_PATIENT_LAST_NAME);
        assertThat(testClaimsStatus277Master.getPatientMemberId()).isEqualTo(UPDATED_PATIENT_MEMBER_ID);
        assertThat(testClaimsStatus277Master.getServiceProviderName()).isEqualTo(UPDATED_SERVICE_PROVIDER_NAME);
        assertThat(testClaimsStatus277Master.getServiceProviderNpi()).isEqualTo(UPDATED_SERVICE_PROVIDER_NPI);
        assertThat(testClaimsStatus277Master.getTotalClaimChargeAmount()).isEqualTo(UPDATED_TOTAL_CLAIM_CHARGE_AMOUNT);
        assertThat(testClaimsStatus277Master.getTotalClaimPaymentAmount()).isEqualTo(UPDATED_TOTAL_CLAIM_PAYMENT_AMOUNT);
        assertThat(testClaimsStatus277Master.getAdjudicatedFinalizedDate()).isEqualTo(UPDATED_ADJUDICATED_FINALIZED_DATE);
        assertThat(testClaimsStatus277Master.getRemittanceDate()).isEqualTo(UPDATED_REMITTANCE_DATE);
        assertThat(testClaimsStatus277Master.getClaimStatusCategoryCode()).isEqualTo(UPDATED_CLAIM_STATUS_CATEGORY_CODE);
        assertThat(testClaimsStatus277Master.getClaimStatusCategoryCodeValue()).isEqualTo(UPDATED_CLAIM_STATUS_CATEGORY_CODE_VALUE);
        assertThat(testClaimsStatus277Master.getStatusCode()).isEqualTo(UPDATED_STATUS_CODE);
        assertThat(testClaimsStatus277Master.getStatusCodeValue()).isEqualTo(UPDATED_STATUS_CODE_VALUE);
        assertThat(testClaimsStatus277Master.getClearinghouseTraceNumber()).isEqualTo(UPDATED_CLEARINGHOUSE_TRACE_NUMBER);
        assertThat(testClaimsStatus277Master.getRemittanceTraceNumber()).isEqualTo(UPDATED_REMITTANCE_TRACE_NUMBER);
        assertThat(testClaimsStatus277Master.getPatientAccountNumber()).isEqualTo(UPDATED_PATIENT_ACCOUNT_NUMBER);
        assertThat(testClaimsStatus277Master.getClaimServiceBeginDate()).isEqualTo(UPDATED_CLAIM_SERVICE_BEGIN_DATE);
        assertThat(testClaimsStatus277Master.getClaimServiceEndDate()).isEqualTo(UPDATED_CLAIM_SERVICE_END_DATE);
        assertThat(testClaimsStatus277Master.getStatusInformationEffectiveDate()).isEqualTo(UPDATED_STATUS_INFORMATION_EFFECTIVE_DATE);
        assertThat(testClaimsStatus277Master.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testClaimsStatus277Master.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testClaimsStatus277Master.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testClaimsStatus277Master.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testClaimsStatus277Master.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testClaimsStatus277Master.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testClaimsStatus277Master.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testClaimsStatus277Master.getClaimsStatus277MasterUuid()).isEqualTo(UPDATED_CLAIMS_STATUS_277_MASTER_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingClaimsStatus277Master() throws Exception {
        int databaseSizeBeforeUpdate = claimsStatus277MasterRepository.findAll().size();
        claimsStatus277Master.setClaimStatus277MasterId(count.incrementAndGet());

        // Create the ClaimsStatus277Master
        ClaimsStatus277MasterDTO claimsStatus277MasterDTO = claimsStatus277MasterMapper.toDto(claimsStatus277Master);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClaimsStatus277MasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, claimsStatus277MasterDTO.getClaimStatus277MasterId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(claimsStatus277MasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimsStatus277Master in the database
        List<ClaimsStatus277Master> claimsStatus277MasterList = claimsStatus277MasterRepository.findAll();
        assertThat(claimsStatus277MasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchClaimsStatus277Master() throws Exception {
        int databaseSizeBeforeUpdate = claimsStatus277MasterRepository.findAll().size();
        claimsStatus277Master.setClaimStatus277MasterId(count.incrementAndGet());

        // Create the ClaimsStatus277Master
        ClaimsStatus277MasterDTO claimsStatus277MasterDTO = claimsStatus277MasterMapper.toDto(claimsStatus277Master);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaimsStatus277MasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(claimsStatus277MasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimsStatus277Master in the database
        List<ClaimsStatus277Master> claimsStatus277MasterList = claimsStatus277MasterRepository.findAll();
        assertThat(claimsStatus277MasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamClaimsStatus277Master() throws Exception {
        int databaseSizeBeforeUpdate = claimsStatus277MasterRepository.findAll().size();
        claimsStatus277Master.setClaimStatus277MasterId(count.incrementAndGet());

        // Create the ClaimsStatus277Master
        ClaimsStatus277MasterDTO claimsStatus277MasterDTO = claimsStatus277MasterMapper.toDto(claimsStatus277Master);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaimsStatus277MasterMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(claimsStatus277MasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ClaimsStatus277Master in the database
        List<ClaimsStatus277Master> claimsStatus277MasterList = claimsStatus277MasterRepository.findAll();
        assertThat(claimsStatus277MasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteClaimsStatus277Master() throws Exception {
        // Initialize the database
        claimsStatus277MasterRepository.saveAndFlush(claimsStatus277Master);

        int databaseSizeBeforeDelete = claimsStatus277MasterRepository.findAll().size();

        // Delete the claimsStatus277Master
        restClaimsStatus277MasterMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, claimsStatus277Master.getClaimStatus277MasterId()).with(csrf()).accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ClaimsStatus277Master> claimsStatus277MasterList = claimsStatus277MasterRepository.findAll();
        assertThat(claimsStatus277MasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
