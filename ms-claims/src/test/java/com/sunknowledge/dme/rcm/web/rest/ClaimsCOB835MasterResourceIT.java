package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ClaimsCOB835Master;
import com.sunknowledge.dme.rcm.repository.ClaimsCOB835MasterRepository;
import com.sunknowledge.dme.rcm.service.dto.ClaimsCOB835MasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.ClaimsCOB835MasterMapper;
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
 * Integration tests for the {@link ClaimsCOB835MasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ClaimsCOB835MasterResourceIT {

    private static final String DEFAULT_PATIENT_CONTROL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_CONTROL_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_PAYER_CLAIM_CONTROL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_CLAIM_CONTROL_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_MEMBER_ID = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_MEMBER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CHECK_OR_EFT_TRACE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CHECK_OR_EFT_TRACE_NUMBER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CHECK_ISSUE_OR_EFT_EFFECTIVE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CHECK_ISSUE_OR_EFT_EFFECTIVE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREDIT_OR_DEBIT_FLAG_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CREDIT_OR_DEBIT_FLAG_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_METHOD_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_METHOD_CODE = "BBBBBBBBBB";

    private static final Double DEFAULT_TOTAL_CLAIM_CHARGE_AMOUNT = 1D;
    private static final Double UPDATED_TOTAL_CLAIM_CHARGE_AMOUNT = 2D;

    private static final Double DEFAULT_TOTAL_CLAIM_PAYMENT_AMOUNT = 1D;
    private static final Double UPDATED_TOTAL_CLAIM_PAYMENT_AMOUNT = 2D;

    private static final Double DEFAULT_TOTAL_PATIENT_RESPONSIBILITY_AMOUNT = 1D;
    private static final Double UPDATED_TOTAL_PATIENT_RESPONSIBILITY_AMOUNT = 2D;

    private static final Boolean DEFAULT_CROSSOVER_CARRIER_NAME = false;
    private static final Boolean UPDATED_CROSSOVER_CARRIER_NAME = true;

    private static final String DEFAULT_CROSSOVER_CARRIER_PAYER_ID = "AAAAAAAAAA";
    private static final String UPDATED_CROSSOVER_CARRIER_PAYER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CROSSOVER_CARRIER_PAYER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CROSSOVER_CARRIER_PAYER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PAYER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PAYEE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PAYEE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PAYEE_NPI = "AAAAAAAAAA";
    private static final String UPDATED_PAYEE_NPI = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CLAIM_RECEIVED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CLAIM_RECEIVED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FILE_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_RECEIVED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_RECEIVED_ON = LocalDate.now(ZoneId.systemDefault());

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

    private static final UUID DEFAULT_CLAIMS_COB_835_MASTER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_CLAIMS_COB_835_MASTER_UUID = UUID.randomUUID();

    private static final Double DEFAULT_CHEQUE_EFT_AMOUNT = 1D;
    private static final Double UPDATED_CHEQUE_EFT_AMOUNT = 2D;

    private static final String DEFAULT_COB_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_COB_TYPE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/claims-cob-835-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{claimCob835MasterId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ClaimsCOB835MasterRepository claimsCOB835MasterRepository;

    @Autowired
    private ClaimsCOB835MasterMapper claimsCOB835MasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restClaimsCOB835MasterMockMvc;

    private ClaimsCOB835Master claimsCOB835Master;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClaimsCOB835Master createEntity(EntityManager em) {
        ClaimsCOB835Master claimsCOB835Master = new ClaimsCOB835Master()
            .patientControlNumber(DEFAULT_PATIENT_CONTROL_NUMBER)
            .payerClaimControlNumber(DEFAULT_PAYER_CLAIM_CONTROL_NUMBER)
            .patientFirstName(DEFAULT_PATIENT_FIRST_NAME)
            .patientLastName(DEFAULT_PATIENT_LAST_NAME)
            .patientMemberId(DEFAULT_PATIENT_MEMBER_ID)
            .checkOrEFTTraceNumber(DEFAULT_CHECK_OR_EFT_TRACE_NUMBER)
            .checkIssueOrEFTEffectiveDate(DEFAULT_CHECK_ISSUE_OR_EFT_EFFECTIVE_DATE)
            .creditOrDebitFlagCode(DEFAULT_CREDIT_OR_DEBIT_FLAG_CODE)
            .paymentMethodCode(DEFAULT_PAYMENT_METHOD_CODE)
            .totalClaimChargeAmount(DEFAULT_TOTAL_CLAIM_CHARGE_AMOUNT)
            .totalClaimPaymentAmount(DEFAULT_TOTAL_CLAIM_PAYMENT_AMOUNT)
            .totalPatientResponsibilityAmount(DEFAULT_TOTAL_PATIENT_RESPONSIBILITY_AMOUNT)
            .crossoverCarrierName(DEFAULT_CROSSOVER_CARRIER_NAME)
            .crossoverCarrierPayerId(DEFAULT_CROSSOVER_CARRIER_PAYER_ID)
            .crossoverCarrierPayerName(DEFAULT_CROSSOVER_CARRIER_PAYER_NAME)
            .payerName(DEFAULT_PAYER_NAME)
            .payeeName(DEFAULT_PAYEE_NAME)
            .payeeNpi(DEFAULT_PAYEE_NPI)
            .claimReceivedDate(DEFAULT_CLAIM_RECEIVED_DATE)
            .fileName(DEFAULT_FILE_NAME)
            .receivedOn(DEFAULT_RECEIVED_ON)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .claimsCob835MasterUuid(DEFAULT_CLAIMS_COB_835_MASTER_UUID)
            .chequeEftAmount(DEFAULT_CHEQUE_EFT_AMOUNT)
            .cobType(DEFAULT_COB_TYPE);
        return claimsCOB835Master;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClaimsCOB835Master createUpdatedEntity(EntityManager em) {
        ClaimsCOB835Master claimsCOB835Master = new ClaimsCOB835Master()
            .patientControlNumber(UPDATED_PATIENT_CONTROL_NUMBER)
            .payerClaimControlNumber(UPDATED_PAYER_CLAIM_CONTROL_NUMBER)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .patientMemberId(UPDATED_PATIENT_MEMBER_ID)
            .checkOrEFTTraceNumber(UPDATED_CHECK_OR_EFT_TRACE_NUMBER)
            .checkIssueOrEFTEffectiveDate(UPDATED_CHECK_ISSUE_OR_EFT_EFFECTIVE_DATE)
            .creditOrDebitFlagCode(UPDATED_CREDIT_OR_DEBIT_FLAG_CODE)
            .paymentMethodCode(UPDATED_PAYMENT_METHOD_CODE)
            .totalClaimChargeAmount(UPDATED_TOTAL_CLAIM_CHARGE_AMOUNT)
            .totalClaimPaymentAmount(UPDATED_TOTAL_CLAIM_PAYMENT_AMOUNT)
            .totalPatientResponsibilityAmount(UPDATED_TOTAL_PATIENT_RESPONSIBILITY_AMOUNT)
            .crossoverCarrierName(UPDATED_CROSSOVER_CARRIER_NAME)
            .crossoverCarrierPayerId(UPDATED_CROSSOVER_CARRIER_PAYER_ID)
            .crossoverCarrierPayerName(UPDATED_CROSSOVER_CARRIER_PAYER_NAME)
            .payerName(UPDATED_PAYER_NAME)
            .payeeName(UPDATED_PAYEE_NAME)
            .payeeNpi(UPDATED_PAYEE_NPI)
            .claimReceivedDate(UPDATED_CLAIM_RECEIVED_DATE)
            .fileName(UPDATED_FILE_NAME)
            .receivedOn(UPDATED_RECEIVED_ON)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .claimsCob835MasterUuid(UPDATED_CLAIMS_COB_835_MASTER_UUID)
            .chequeEftAmount(UPDATED_CHEQUE_EFT_AMOUNT)
            .cobType(UPDATED_COB_TYPE);
        return claimsCOB835Master;
    }

    @BeforeEach
    public void initTest() {
        claimsCOB835Master = createEntity(em);
    }

    @Test
    @Transactional
    void createClaimsCOB835Master() throws Exception {
        int databaseSizeBeforeCreate = claimsCOB835MasterRepository.findAll().size();
        // Create the ClaimsCOB835Master
        ClaimsCOB835MasterDTO claimsCOB835MasterDTO = claimsCOB835MasterMapper.toDto(claimsCOB835Master);
        restClaimsCOB835MasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimsCOB835MasterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ClaimsCOB835Master in the database
        List<ClaimsCOB835Master> claimsCOB835MasterList = claimsCOB835MasterRepository.findAll();
        assertThat(claimsCOB835MasterList).hasSize(databaseSizeBeforeCreate + 1);
        ClaimsCOB835Master testClaimsCOB835Master = claimsCOB835MasterList.get(claimsCOB835MasterList.size() - 1);
        assertThat(testClaimsCOB835Master.getPatientControlNumber()).isEqualTo(DEFAULT_PATIENT_CONTROL_NUMBER);
        assertThat(testClaimsCOB835Master.getPayerClaimControlNumber()).isEqualTo(DEFAULT_PAYER_CLAIM_CONTROL_NUMBER);
        assertThat(testClaimsCOB835Master.getPatientFirstName()).isEqualTo(DEFAULT_PATIENT_FIRST_NAME);
        assertThat(testClaimsCOB835Master.getPatientLastName()).isEqualTo(DEFAULT_PATIENT_LAST_NAME);
        assertThat(testClaimsCOB835Master.getPatientMemberId()).isEqualTo(DEFAULT_PATIENT_MEMBER_ID);
        assertThat(testClaimsCOB835Master.getCheckOrEFTTraceNumber()).isEqualTo(DEFAULT_CHECK_OR_EFT_TRACE_NUMBER);
        assertThat(testClaimsCOB835Master.getCheckIssueOrEFTEffectiveDate()).isEqualTo(DEFAULT_CHECK_ISSUE_OR_EFT_EFFECTIVE_DATE);
        assertThat(testClaimsCOB835Master.getCreditOrDebitFlagCode()).isEqualTo(DEFAULT_CREDIT_OR_DEBIT_FLAG_CODE);
        assertThat(testClaimsCOB835Master.getPaymentMethodCode()).isEqualTo(DEFAULT_PAYMENT_METHOD_CODE);
        assertThat(testClaimsCOB835Master.getTotalClaimChargeAmount()).isEqualTo(DEFAULT_TOTAL_CLAIM_CHARGE_AMOUNT);
        assertThat(testClaimsCOB835Master.getTotalClaimPaymentAmount()).isEqualTo(DEFAULT_TOTAL_CLAIM_PAYMENT_AMOUNT);
        assertThat(testClaimsCOB835Master.getTotalPatientResponsibilityAmount()).isEqualTo(DEFAULT_TOTAL_PATIENT_RESPONSIBILITY_AMOUNT);
        assertThat(testClaimsCOB835Master.getCrossoverCarrierName()).isEqualTo(DEFAULT_CROSSOVER_CARRIER_NAME);
        assertThat(testClaimsCOB835Master.getCrossoverCarrierPayerId()).isEqualTo(DEFAULT_CROSSOVER_CARRIER_PAYER_ID);
        assertThat(testClaimsCOB835Master.getCrossoverCarrierPayerName()).isEqualTo(DEFAULT_CROSSOVER_CARRIER_PAYER_NAME);
        assertThat(testClaimsCOB835Master.getPayerName()).isEqualTo(DEFAULT_PAYER_NAME);
        assertThat(testClaimsCOB835Master.getPayeeName()).isEqualTo(DEFAULT_PAYEE_NAME);
        assertThat(testClaimsCOB835Master.getPayeeNpi()).isEqualTo(DEFAULT_PAYEE_NPI);
        assertThat(testClaimsCOB835Master.getClaimReceivedDate()).isEqualTo(DEFAULT_CLAIM_RECEIVED_DATE);
        assertThat(testClaimsCOB835Master.getFileName()).isEqualTo(DEFAULT_FILE_NAME);
        assertThat(testClaimsCOB835Master.getReceivedOn()).isEqualTo(DEFAULT_RECEIVED_ON);
        assertThat(testClaimsCOB835Master.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testClaimsCOB835Master.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testClaimsCOB835Master.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testClaimsCOB835Master.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testClaimsCOB835Master.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testClaimsCOB835Master.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testClaimsCOB835Master.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testClaimsCOB835Master.getClaimsCob835MasterUuid()).isEqualTo(DEFAULT_CLAIMS_COB_835_MASTER_UUID);
        assertThat(testClaimsCOB835Master.getChequeEftAmount()).isEqualTo(DEFAULT_CHEQUE_EFT_AMOUNT);
        assertThat(testClaimsCOB835Master.getCobType()).isEqualTo(DEFAULT_COB_TYPE);
    }

    @Test
    @Transactional
    void createClaimsCOB835MasterWithExistingId() throws Exception {
        // Create the ClaimsCOB835Master with an existing ID
        claimsCOB835Master.setClaimCob835MasterId(1L);
        ClaimsCOB835MasterDTO claimsCOB835MasterDTO = claimsCOB835MasterMapper.toDto(claimsCOB835Master);

        int databaseSizeBeforeCreate = claimsCOB835MasterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restClaimsCOB835MasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimsCOB835MasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimsCOB835Master in the database
        List<ClaimsCOB835Master> claimsCOB835MasterList = claimsCOB835MasterRepository.findAll();
        assertThat(claimsCOB835MasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllClaimsCOB835Masters() throws Exception {
        // Initialize the database
        claimsCOB835MasterRepository.saveAndFlush(claimsCOB835Master);

        // Get all the claimsCOB835MasterList
        restClaimsCOB835MasterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=claimCob835MasterId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].claimCob835MasterId").value(hasItem(claimsCOB835Master.getClaimCob835MasterId().intValue())))
            .andExpect(jsonPath("$.[*].patientControlNumber").value(hasItem(DEFAULT_PATIENT_CONTROL_NUMBER)))
            .andExpect(jsonPath("$.[*].payerClaimControlNumber").value(hasItem(DEFAULT_PAYER_CLAIM_CONTROL_NUMBER)))
            .andExpect(jsonPath("$.[*].patientFirstName").value(hasItem(DEFAULT_PATIENT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].patientLastName").value(hasItem(DEFAULT_PATIENT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].patientMemberId").value(hasItem(DEFAULT_PATIENT_MEMBER_ID)))
            .andExpect(jsonPath("$.[*].checkOrEFTTraceNumber").value(hasItem(DEFAULT_CHECK_OR_EFT_TRACE_NUMBER)))
            .andExpect(jsonPath("$.[*].checkIssueOrEFTEffectiveDate").value(hasItem(DEFAULT_CHECK_ISSUE_OR_EFT_EFFECTIVE_DATE.toString())))
            .andExpect(jsonPath("$.[*].creditOrDebitFlagCode").value(hasItem(DEFAULT_CREDIT_OR_DEBIT_FLAG_CODE)))
            .andExpect(jsonPath("$.[*].paymentMethodCode").value(hasItem(DEFAULT_PAYMENT_METHOD_CODE)))
            .andExpect(jsonPath("$.[*].totalClaimChargeAmount").value(hasItem(DEFAULT_TOTAL_CLAIM_CHARGE_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].totalClaimPaymentAmount").value(hasItem(DEFAULT_TOTAL_CLAIM_PAYMENT_AMOUNT.doubleValue())))
            .andExpect(
                jsonPath("$.[*].totalPatientResponsibilityAmount").value(hasItem(DEFAULT_TOTAL_PATIENT_RESPONSIBILITY_AMOUNT.doubleValue()))
            )
            .andExpect(jsonPath("$.[*].crossoverCarrierName").value(hasItem(DEFAULT_CROSSOVER_CARRIER_NAME.booleanValue())))
            .andExpect(jsonPath("$.[*].crossoverCarrierPayerId").value(hasItem(DEFAULT_CROSSOVER_CARRIER_PAYER_ID)))
            .andExpect(jsonPath("$.[*].crossoverCarrierPayerName").value(hasItem(DEFAULT_CROSSOVER_CARRIER_PAYER_NAME)))
            .andExpect(jsonPath("$.[*].payerName").value(hasItem(DEFAULT_PAYER_NAME)))
            .andExpect(jsonPath("$.[*].payeeName").value(hasItem(DEFAULT_PAYEE_NAME)))
            .andExpect(jsonPath("$.[*].payeeNpi").value(hasItem(DEFAULT_PAYEE_NPI)))
            .andExpect(jsonPath("$.[*].claimReceivedDate").value(hasItem(DEFAULT_CLAIM_RECEIVED_DATE.toString())))
            .andExpect(jsonPath("$.[*].fileName").value(hasItem(DEFAULT_FILE_NAME)))
            .andExpect(jsonPath("$.[*].receivedOn").value(hasItem(DEFAULT_RECEIVED_ON.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].claimsCob835MasterUuid").value(hasItem(DEFAULT_CLAIMS_COB_835_MASTER_UUID.toString())))
            .andExpect(jsonPath("$.[*].chequeEftAmount").value(hasItem(DEFAULT_CHEQUE_EFT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].cobType").value(hasItem(DEFAULT_COB_TYPE)));
    }

    @Test
    @Transactional
    void getClaimsCOB835Master() throws Exception {
        // Initialize the database
        claimsCOB835MasterRepository.saveAndFlush(claimsCOB835Master);

        // Get the claimsCOB835Master
        restClaimsCOB835MasterMockMvc
            .perform(get(ENTITY_API_URL_ID, claimsCOB835Master.getClaimCob835MasterId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.claimCob835MasterId").value(claimsCOB835Master.getClaimCob835MasterId().intValue()))
            .andExpect(jsonPath("$.patientControlNumber").value(DEFAULT_PATIENT_CONTROL_NUMBER))
            .andExpect(jsonPath("$.payerClaimControlNumber").value(DEFAULT_PAYER_CLAIM_CONTROL_NUMBER))
            .andExpect(jsonPath("$.patientFirstName").value(DEFAULT_PATIENT_FIRST_NAME))
            .andExpect(jsonPath("$.patientLastName").value(DEFAULT_PATIENT_LAST_NAME))
            .andExpect(jsonPath("$.patientMemberId").value(DEFAULT_PATIENT_MEMBER_ID))
            .andExpect(jsonPath("$.checkOrEFTTraceNumber").value(DEFAULT_CHECK_OR_EFT_TRACE_NUMBER))
            .andExpect(jsonPath("$.checkIssueOrEFTEffectiveDate").value(DEFAULT_CHECK_ISSUE_OR_EFT_EFFECTIVE_DATE.toString()))
            .andExpect(jsonPath("$.creditOrDebitFlagCode").value(DEFAULT_CREDIT_OR_DEBIT_FLAG_CODE))
            .andExpect(jsonPath("$.paymentMethodCode").value(DEFAULT_PAYMENT_METHOD_CODE))
            .andExpect(jsonPath("$.totalClaimChargeAmount").value(DEFAULT_TOTAL_CLAIM_CHARGE_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.totalClaimPaymentAmount").value(DEFAULT_TOTAL_CLAIM_PAYMENT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.totalPatientResponsibilityAmount").value(DEFAULT_TOTAL_PATIENT_RESPONSIBILITY_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.crossoverCarrierName").value(DEFAULT_CROSSOVER_CARRIER_NAME.booleanValue()))
            .andExpect(jsonPath("$.crossoverCarrierPayerId").value(DEFAULT_CROSSOVER_CARRIER_PAYER_ID))
            .andExpect(jsonPath("$.crossoverCarrierPayerName").value(DEFAULT_CROSSOVER_CARRIER_PAYER_NAME))
            .andExpect(jsonPath("$.payerName").value(DEFAULT_PAYER_NAME))
            .andExpect(jsonPath("$.payeeName").value(DEFAULT_PAYEE_NAME))
            .andExpect(jsonPath("$.payeeNpi").value(DEFAULT_PAYEE_NPI))
            .andExpect(jsonPath("$.claimReceivedDate").value(DEFAULT_CLAIM_RECEIVED_DATE.toString()))
            .andExpect(jsonPath("$.fileName").value(DEFAULT_FILE_NAME))
            .andExpect(jsonPath("$.receivedOn").value(DEFAULT_RECEIVED_ON.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.claimsCob835MasterUuid").value(DEFAULT_CLAIMS_COB_835_MASTER_UUID.toString()))
            .andExpect(jsonPath("$.chequeEftAmount").value(DEFAULT_CHEQUE_EFT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.cobType").value(DEFAULT_COB_TYPE));
    }

    @Test
    @Transactional
    void getNonExistingClaimsCOB835Master() throws Exception {
        // Get the claimsCOB835Master
        restClaimsCOB835MasterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewClaimsCOB835Master() throws Exception {
        // Initialize the database
        claimsCOB835MasterRepository.saveAndFlush(claimsCOB835Master);

        int databaseSizeBeforeUpdate = claimsCOB835MasterRepository.findAll().size();

        // Update the claimsCOB835Master
        ClaimsCOB835Master updatedClaimsCOB835Master = claimsCOB835MasterRepository
            .findById(claimsCOB835Master.getClaimCob835MasterId())
            .get();
        // Disconnect from session so that the updates on updatedClaimsCOB835Master are not directly saved in db
        em.detach(updatedClaimsCOB835Master);
        updatedClaimsCOB835Master
            .patientControlNumber(UPDATED_PATIENT_CONTROL_NUMBER)
            .payerClaimControlNumber(UPDATED_PAYER_CLAIM_CONTROL_NUMBER)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .patientMemberId(UPDATED_PATIENT_MEMBER_ID)
            .checkOrEFTTraceNumber(UPDATED_CHECK_OR_EFT_TRACE_NUMBER)
            .checkIssueOrEFTEffectiveDate(UPDATED_CHECK_ISSUE_OR_EFT_EFFECTIVE_DATE)
            .creditOrDebitFlagCode(UPDATED_CREDIT_OR_DEBIT_FLAG_CODE)
            .paymentMethodCode(UPDATED_PAYMENT_METHOD_CODE)
            .totalClaimChargeAmount(UPDATED_TOTAL_CLAIM_CHARGE_AMOUNT)
            .totalClaimPaymentAmount(UPDATED_TOTAL_CLAIM_PAYMENT_AMOUNT)
            .totalPatientResponsibilityAmount(UPDATED_TOTAL_PATIENT_RESPONSIBILITY_AMOUNT)
            .crossoverCarrierName(UPDATED_CROSSOVER_CARRIER_NAME)
            .crossoverCarrierPayerId(UPDATED_CROSSOVER_CARRIER_PAYER_ID)
            .crossoverCarrierPayerName(UPDATED_CROSSOVER_CARRIER_PAYER_NAME)
            .payerName(UPDATED_PAYER_NAME)
            .payeeName(UPDATED_PAYEE_NAME)
            .payeeNpi(UPDATED_PAYEE_NPI)
            .claimReceivedDate(UPDATED_CLAIM_RECEIVED_DATE)
            .fileName(UPDATED_FILE_NAME)
            .receivedOn(UPDATED_RECEIVED_ON)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .claimsCob835MasterUuid(UPDATED_CLAIMS_COB_835_MASTER_UUID)
            .chequeEftAmount(UPDATED_CHEQUE_EFT_AMOUNT)
            .cobType(UPDATED_COB_TYPE);
        ClaimsCOB835MasterDTO claimsCOB835MasterDTO = claimsCOB835MasterMapper.toDto(updatedClaimsCOB835Master);

        restClaimsCOB835MasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, claimsCOB835MasterDTO.getClaimCob835MasterId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimsCOB835MasterDTO))
            )
            .andExpect(status().isOk());

        // Validate the ClaimsCOB835Master in the database
        List<ClaimsCOB835Master> claimsCOB835MasterList = claimsCOB835MasterRepository.findAll();
        assertThat(claimsCOB835MasterList).hasSize(databaseSizeBeforeUpdate);
        ClaimsCOB835Master testClaimsCOB835Master = claimsCOB835MasterList.get(claimsCOB835MasterList.size() - 1);
        assertThat(testClaimsCOB835Master.getPatientControlNumber()).isEqualTo(UPDATED_PATIENT_CONTROL_NUMBER);
        assertThat(testClaimsCOB835Master.getPayerClaimControlNumber()).isEqualTo(UPDATED_PAYER_CLAIM_CONTROL_NUMBER);
        assertThat(testClaimsCOB835Master.getPatientFirstName()).isEqualTo(UPDATED_PATIENT_FIRST_NAME);
        assertThat(testClaimsCOB835Master.getPatientLastName()).isEqualTo(UPDATED_PATIENT_LAST_NAME);
        assertThat(testClaimsCOB835Master.getPatientMemberId()).isEqualTo(UPDATED_PATIENT_MEMBER_ID);
        assertThat(testClaimsCOB835Master.getCheckOrEFTTraceNumber()).isEqualTo(UPDATED_CHECK_OR_EFT_TRACE_NUMBER);
        assertThat(testClaimsCOB835Master.getCheckIssueOrEFTEffectiveDate()).isEqualTo(UPDATED_CHECK_ISSUE_OR_EFT_EFFECTIVE_DATE);
        assertThat(testClaimsCOB835Master.getCreditOrDebitFlagCode()).isEqualTo(UPDATED_CREDIT_OR_DEBIT_FLAG_CODE);
        assertThat(testClaimsCOB835Master.getPaymentMethodCode()).isEqualTo(UPDATED_PAYMENT_METHOD_CODE);
        assertThat(testClaimsCOB835Master.getTotalClaimChargeAmount()).isEqualTo(UPDATED_TOTAL_CLAIM_CHARGE_AMOUNT);
        assertThat(testClaimsCOB835Master.getTotalClaimPaymentAmount()).isEqualTo(UPDATED_TOTAL_CLAIM_PAYMENT_AMOUNT);
        assertThat(testClaimsCOB835Master.getTotalPatientResponsibilityAmount()).isEqualTo(UPDATED_TOTAL_PATIENT_RESPONSIBILITY_AMOUNT);
        assertThat(testClaimsCOB835Master.getCrossoverCarrierName()).isEqualTo(UPDATED_CROSSOVER_CARRIER_NAME);
        assertThat(testClaimsCOB835Master.getCrossoverCarrierPayerId()).isEqualTo(UPDATED_CROSSOVER_CARRIER_PAYER_ID);
        assertThat(testClaimsCOB835Master.getCrossoverCarrierPayerName()).isEqualTo(UPDATED_CROSSOVER_CARRIER_PAYER_NAME);
        assertThat(testClaimsCOB835Master.getPayerName()).isEqualTo(UPDATED_PAYER_NAME);
        assertThat(testClaimsCOB835Master.getPayeeName()).isEqualTo(UPDATED_PAYEE_NAME);
        assertThat(testClaimsCOB835Master.getPayeeNpi()).isEqualTo(UPDATED_PAYEE_NPI);
        assertThat(testClaimsCOB835Master.getClaimReceivedDate()).isEqualTo(UPDATED_CLAIM_RECEIVED_DATE);
        assertThat(testClaimsCOB835Master.getFileName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testClaimsCOB835Master.getReceivedOn()).isEqualTo(UPDATED_RECEIVED_ON);
        assertThat(testClaimsCOB835Master.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testClaimsCOB835Master.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testClaimsCOB835Master.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testClaimsCOB835Master.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testClaimsCOB835Master.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testClaimsCOB835Master.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testClaimsCOB835Master.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testClaimsCOB835Master.getClaimsCob835MasterUuid()).isEqualTo(UPDATED_CLAIMS_COB_835_MASTER_UUID);
        assertThat(testClaimsCOB835Master.getChequeEftAmount()).isEqualTo(UPDATED_CHEQUE_EFT_AMOUNT);
        assertThat(testClaimsCOB835Master.getCobType()).isEqualTo(UPDATED_COB_TYPE);
    }

    @Test
    @Transactional
    void putNonExistingClaimsCOB835Master() throws Exception {
        int databaseSizeBeforeUpdate = claimsCOB835MasterRepository.findAll().size();
        claimsCOB835Master.setClaimCob835MasterId(count.incrementAndGet());

        // Create the ClaimsCOB835Master
        ClaimsCOB835MasterDTO claimsCOB835MasterDTO = claimsCOB835MasterMapper.toDto(claimsCOB835Master);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClaimsCOB835MasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, claimsCOB835MasterDTO.getClaimCob835MasterId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimsCOB835MasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimsCOB835Master in the database
        List<ClaimsCOB835Master> claimsCOB835MasterList = claimsCOB835MasterRepository.findAll();
        assertThat(claimsCOB835MasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchClaimsCOB835Master() throws Exception {
        int databaseSizeBeforeUpdate = claimsCOB835MasterRepository.findAll().size();
        claimsCOB835Master.setClaimCob835MasterId(count.incrementAndGet());

        // Create the ClaimsCOB835Master
        ClaimsCOB835MasterDTO claimsCOB835MasterDTO = claimsCOB835MasterMapper.toDto(claimsCOB835Master);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaimsCOB835MasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimsCOB835MasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimsCOB835Master in the database
        List<ClaimsCOB835Master> claimsCOB835MasterList = claimsCOB835MasterRepository.findAll();
        assertThat(claimsCOB835MasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamClaimsCOB835Master() throws Exception {
        int databaseSizeBeforeUpdate = claimsCOB835MasterRepository.findAll().size();
        claimsCOB835Master.setClaimCob835MasterId(count.incrementAndGet());

        // Create the ClaimsCOB835Master
        ClaimsCOB835MasterDTO claimsCOB835MasterDTO = claimsCOB835MasterMapper.toDto(claimsCOB835Master);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaimsCOB835MasterMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimsCOB835MasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ClaimsCOB835Master in the database
        List<ClaimsCOB835Master> claimsCOB835MasterList = claimsCOB835MasterRepository.findAll();
        assertThat(claimsCOB835MasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateClaimsCOB835MasterWithPatch() throws Exception {
        // Initialize the database
        claimsCOB835MasterRepository.saveAndFlush(claimsCOB835Master);

        int databaseSizeBeforeUpdate = claimsCOB835MasterRepository.findAll().size();

        // Update the claimsCOB835Master using partial update
        ClaimsCOB835Master partialUpdatedClaimsCOB835Master = new ClaimsCOB835Master();
        partialUpdatedClaimsCOB835Master.setClaimCob835MasterId(claimsCOB835Master.getClaimCob835MasterId());

        partialUpdatedClaimsCOB835Master
            .payerClaimControlNumber(UPDATED_PAYER_CLAIM_CONTROL_NUMBER)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .checkIssueOrEFTEffectiveDate(UPDATED_CHECK_ISSUE_OR_EFT_EFFECTIVE_DATE)
            .totalClaimPaymentAmount(UPDATED_TOTAL_CLAIM_PAYMENT_AMOUNT)
            .crossoverCarrierPayerId(UPDATED_CROSSOVER_CARRIER_PAYER_ID)
            .crossoverCarrierPayerName(UPDATED_CROSSOVER_CARRIER_PAYER_NAME)
            .payerName(UPDATED_PAYER_NAME)
            .fileName(UPDATED_FILE_NAME)
            .receivedOn(UPDATED_RECEIVED_ON)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .cobType(UPDATED_COB_TYPE);

        restClaimsCOB835MasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClaimsCOB835Master.getClaimCob835MasterId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClaimsCOB835Master))
            )
            .andExpect(status().isOk());

        // Validate the ClaimsCOB835Master in the database
        List<ClaimsCOB835Master> claimsCOB835MasterList = claimsCOB835MasterRepository.findAll();
        assertThat(claimsCOB835MasterList).hasSize(databaseSizeBeforeUpdate);
        ClaimsCOB835Master testClaimsCOB835Master = claimsCOB835MasterList.get(claimsCOB835MasterList.size() - 1);
        assertThat(testClaimsCOB835Master.getPatientControlNumber()).isEqualTo(DEFAULT_PATIENT_CONTROL_NUMBER);
        assertThat(testClaimsCOB835Master.getPayerClaimControlNumber()).isEqualTo(UPDATED_PAYER_CLAIM_CONTROL_NUMBER);
        assertThat(testClaimsCOB835Master.getPatientFirstName()).isEqualTo(DEFAULT_PATIENT_FIRST_NAME);
        assertThat(testClaimsCOB835Master.getPatientLastName()).isEqualTo(UPDATED_PATIENT_LAST_NAME);
        assertThat(testClaimsCOB835Master.getPatientMemberId()).isEqualTo(DEFAULT_PATIENT_MEMBER_ID);
        assertThat(testClaimsCOB835Master.getCheckOrEFTTraceNumber()).isEqualTo(DEFAULT_CHECK_OR_EFT_TRACE_NUMBER);
        assertThat(testClaimsCOB835Master.getCheckIssueOrEFTEffectiveDate()).isEqualTo(UPDATED_CHECK_ISSUE_OR_EFT_EFFECTIVE_DATE);
        assertThat(testClaimsCOB835Master.getCreditOrDebitFlagCode()).isEqualTo(DEFAULT_CREDIT_OR_DEBIT_FLAG_CODE);
        assertThat(testClaimsCOB835Master.getPaymentMethodCode()).isEqualTo(DEFAULT_PAYMENT_METHOD_CODE);
        assertThat(testClaimsCOB835Master.getTotalClaimChargeAmount()).isEqualTo(DEFAULT_TOTAL_CLAIM_CHARGE_AMOUNT);
        assertThat(testClaimsCOB835Master.getTotalClaimPaymentAmount()).isEqualTo(UPDATED_TOTAL_CLAIM_PAYMENT_AMOUNT);
        assertThat(testClaimsCOB835Master.getTotalPatientResponsibilityAmount()).isEqualTo(DEFAULT_TOTAL_PATIENT_RESPONSIBILITY_AMOUNT);
        assertThat(testClaimsCOB835Master.getCrossoverCarrierName()).isEqualTo(DEFAULT_CROSSOVER_CARRIER_NAME);
        assertThat(testClaimsCOB835Master.getCrossoverCarrierPayerId()).isEqualTo(UPDATED_CROSSOVER_CARRIER_PAYER_ID);
        assertThat(testClaimsCOB835Master.getCrossoverCarrierPayerName()).isEqualTo(UPDATED_CROSSOVER_CARRIER_PAYER_NAME);
        assertThat(testClaimsCOB835Master.getPayerName()).isEqualTo(UPDATED_PAYER_NAME);
        assertThat(testClaimsCOB835Master.getPayeeName()).isEqualTo(DEFAULT_PAYEE_NAME);
        assertThat(testClaimsCOB835Master.getPayeeNpi()).isEqualTo(DEFAULT_PAYEE_NPI);
        assertThat(testClaimsCOB835Master.getClaimReceivedDate()).isEqualTo(DEFAULT_CLAIM_RECEIVED_DATE);
        assertThat(testClaimsCOB835Master.getFileName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testClaimsCOB835Master.getReceivedOn()).isEqualTo(UPDATED_RECEIVED_ON);
        assertThat(testClaimsCOB835Master.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testClaimsCOB835Master.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testClaimsCOB835Master.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testClaimsCOB835Master.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testClaimsCOB835Master.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testClaimsCOB835Master.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testClaimsCOB835Master.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testClaimsCOB835Master.getClaimsCob835MasterUuid()).isEqualTo(DEFAULT_CLAIMS_COB_835_MASTER_UUID);
        assertThat(testClaimsCOB835Master.getChequeEftAmount()).isEqualTo(DEFAULT_CHEQUE_EFT_AMOUNT);
        assertThat(testClaimsCOB835Master.getCobType()).isEqualTo(UPDATED_COB_TYPE);
    }

    @Test
    @Transactional
    void fullUpdateClaimsCOB835MasterWithPatch() throws Exception {
        // Initialize the database
        claimsCOB835MasterRepository.saveAndFlush(claimsCOB835Master);

        int databaseSizeBeforeUpdate = claimsCOB835MasterRepository.findAll().size();

        // Update the claimsCOB835Master using partial update
        ClaimsCOB835Master partialUpdatedClaimsCOB835Master = new ClaimsCOB835Master();
        partialUpdatedClaimsCOB835Master.setClaimCob835MasterId(claimsCOB835Master.getClaimCob835MasterId());

        partialUpdatedClaimsCOB835Master
            .patientControlNumber(UPDATED_PATIENT_CONTROL_NUMBER)
            .payerClaimControlNumber(UPDATED_PAYER_CLAIM_CONTROL_NUMBER)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .patientMemberId(UPDATED_PATIENT_MEMBER_ID)
            .checkOrEFTTraceNumber(UPDATED_CHECK_OR_EFT_TRACE_NUMBER)
            .checkIssueOrEFTEffectiveDate(UPDATED_CHECK_ISSUE_OR_EFT_EFFECTIVE_DATE)
            .creditOrDebitFlagCode(UPDATED_CREDIT_OR_DEBIT_FLAG_CODE)
            .paymentMethodCode(UPDATED_PAYMENT_METHOD_CODE)
            .totalClaimChargeAmount(UPDATED_TOTAL_CLAIM_CHARGE_AMOUNT)
            .totalClaimPaymentAmount(UPDATED_TOTAL_CLAIM_PAYMENT_AMOUNT)
            .totalPatientResponsibilityAmount(UPDATED_TOTAL_PATIENT_RESPONSIBILITY_AMOUNT)
            .crossoverCarrierName(UPDATED_CROSSOVER_CARRIER_NAME)
            .crossoverCarrierPayerId(UPDATED_CROSSOVER_CARRIER_PAYER_ID)
            .crossoverCarrierPayerName(UPDATED_CROSSOVER_CARRIER_PAYER_NAME)
            .payerName(UPDATED_PAYER_NAME)
            .payeeName(UPDATED_PAYEE_NAME)
            .payeeNpi(UPDATED_PAYEE_NPI)
            .claimReceivedDate(UPDATED_CLAIM_RECEIVED_DATE)
            .fileName(UPDATED_FILE_NAME)
            .receivedOn(UPDATED_RECEIVED_ON)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .claimsCob835MasterUuid(UPDATED_CLAIMS_COB_835_MASTER_UUID)
            .chequeEftAmount(UPDATED_CHEQUE_EFT_AMOUNT)
            .cobType(UPDATED_COB_TYPE);

        restClaimsCOB835MasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClaimsCOB835Master.getClaimCob835MasterId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClaimsCOB835Master))
            )
            .andExpect(status().isOk());

        // Validate the ClaimsCOB835Master in the database
        List<ClaimsCOB835Master> claimsCOB835MasterList = claimsCOB835MasterRepository.findAll();
        assertThat(claimsCOB835MasterList).hasSize(databaseSizeBeforeUpdate);
        ClaimsCOB835Master testClaimsCOB835Master = claimsCOB835MasterList.get(claimsCOB835MasterList.size() - 1);
        assertThat(testClaimsCOB835Master.getPatientControlNumber()).isEqualTo(UPDATED_PATIENT_CONTROL_NUMBER);
        assertThat(testClaimsCOB835Master.getPayerClaimControlNumber()).isEqualTo(UPDATED_PAYER_CLAIM_CONTROL_NUMBER);
        assertThat(testClaimsCOB835Master.getPatientFirstName()).isEqualTo(UPDATED_PATIENT_FIRST_NAME);
        assertThat(testClaimsCOB835Master.getPatientLastName()).isEqualTo(UPDATED_PATIENT_LAST_NAME);
        assertThat(testClaimsCOB835Master.getPatientMemberId()).isEqualTo(UPDATED_PATIENT_MEMBER_ID);
        assertThat(testClaimsCOB835Master.getCheckOrEFTTraceNumber()).isEqualTo(UPDATED_CHECK_OR_EFT_TRACE_NUMBER);
        assertThat(testClaimsCOB835Master.getCheckIssueOrEFTEffectiveDate()).isEqualTo(UPDATED_CHECK_ISSUE_OR_EFT_EFFECTIVE_DATE);
        assertThat(testClaimsCOB835Master.getCreditOrDebitFlagCode()).isEqualTo(UPDATED_CREDIT_OR_DEBIT_FLAG_CODE);
        assertThat(testClaimsCOB835Master.getPaymentMethodCode()).isEqualTo(UPDATED_PAYMENT_METHOD_CODE);
        assertThat(testClaimsCOB835Master.getTotalClaimChargeAmount()).isEqualTo(UPDATED_TOTAL_CLAIM_CHARGE_AMOUNT);
        assertThat(testClaimsCOB835Master.getTotalClaimPaymentAmount()).isEqualTo(UPDATED_TOTAL_CLAIM_PAYMENT_AMOUNT);
        assertThat(testClaimsCOB835Master.getTotalPatientResponsibilityAmount()).isEqualTo(UPDATED_TOTAL_PATIENT_RESPONSIBILITY_AMOUNT);
        assertThat(testClaimsCOB835Master.getCrossoverCarrierName()).isEqualTo(UPDATED_CROSSOVER_CARRIER_NAME);
        assertThat(testClaimsCOB835Master.getCrossoverCarrierPayerId()).isEqualTo(UPDATED_CROSSOVER_CARRIER_PAYER_ID);
        assertThat(testClaimsCOB835Master.getCrossoverCarrierPayerName()).isEqualTo(UPDATED_CROSSOVER_CARRIER_PAYER_NAME);
        assertThat(testClaimsCOB835Master.getPayerName()).isEqualTo(UPDATED_PAYER_NAME);
        assertThat(testClaimsCOB835Master.getPayeeName()).isEqualTo(UPDATED_PAYEE_NAME);
        assertThat(testClaimsCOB835Master.getPayeeNpi()).isEqualTo(UPDATED_PAYEE_NPI);
        assertThat(testClaimsCOB835Master.getClaimReceivedDate()).isEqualTo(UPDATED_CLAIM_RECEIVED_DATE);
        assertThat(testClaimsCOB835Master.getFileName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testClaimsCOB835Master.getReceivedOn()).isEqualTo(UPDATED_RECEIVED_ON);
        assertThat(testClaimsCOB835Master.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testClaimsCOB835Master.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testClaimsCOB835Master.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testClaimsCOB835Master.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testClaimsCOB835Master.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testClaimsCOB835Master.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testClaimsCOB835Master.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testClaimsCOB835Master.getClaimsCob835MasterUuid()).isEqualTo(UPDATED_CLAIMS_COB_835_MASTER_UUID);
        assertThat(testClaimsCOB835Master.getChequeEftAmount()).isEqualTo(UPDATED_CHEQUE_EFT_AMOUNT);
        assertThat(testClaimsCOB835Master.getCobType()).isEqualTo(UPDATED_COB_TYPE);
    }

    @Test
    @Transactional
    void patchNonExistingClaimsCOB835Master() throws Exception {
        int databaseSizeBeforeUpdate = claimsCOB835MasterRepository.findAll().size();
        claimsCOB835Master.setClaimCob835MasterId(count.incrementAndGet());

        // Create the ClaimsCOB835Master
        ClaimsCOB835MasterDTO claimsCOB835MasterDTO = claimsCOB835MasterMapper.toDto(claimsCOB835Master);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClaimsCOB835MasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, claimsCOB835MasterDTO.getClaimCob835MasterId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(claimsCOB835MasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimsCOB835Master in the database
        List<ClaimsCOB835Master> claimsCOB835MasterList = claimsCOB835MasterRepository.findAll();
        assertThat(claimsCOB835MasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchClaimsCOB835Master() throws Exception {
        int databaseSizeBeforeUpdate = claimsCOB835MasterRepository.findAll().size();
        claimsCOB835Master.setClaimCob835MasterId(count.incrementAndGet());

        // Create the ClaimsCOB835Master
        ClaimsCOB835MasterDTO claimsCOB835MasterDTO = claimsCOB835MasterMapper.toDto(claimsCOB835Master);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaimsCOB835MasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(claimsCOB835MasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimsCOB835Master in the database
        List<ClaimsCOB835Master> claimsCOB835MasterList = claimsCOB835MasterRepository.findAll();
        assertThat(claimsCOB835MasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamClaimsCOB835Master() throws Exception {
        int databaseSizeBeforeUpdate = claimsCOB835MasterRepository.findAll().size();
        claimsCOB835Master.setClaimCob835MasterId(count.incrementAndGet());

        // Create the ClaimsCOB835Master
        ClaimsCOB835MasterDTO claimsCOB835MasterDTO = claimsCOB835MasterMapper.toDto(claimsCOB835Master);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaimsCOB835MasterMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(claimsCOB835MasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ClaimsCOB835Master in the database
        List<ClaimsCOB835Master> claimsCOB835MasterList = claimsCOB835MasterRepository.findAll();
        assertThat(claimsCOB835MasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteClaimsCOB835Master() throws Exception {
        // Initialize the database
        claimsCOB835MasterRepository.saveAndFlush(claimsCOB835Master);

        int databaseSizeBeforeDelete = claimsCOB835MasterRepository.findAll().size();

        // Delete the claimsCOB835Master
        restClaimsCOB835MasterMockMvc
            .perform(delete(ENTITY_API_URL_ID, claimsCOB835Master.getClaimCob835MasterId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ClaimsCOB835Master> claimsCOB835MasterList = claimsCOB835MasterRepository.findAll();
        assertThat(claimsCOB835MasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
