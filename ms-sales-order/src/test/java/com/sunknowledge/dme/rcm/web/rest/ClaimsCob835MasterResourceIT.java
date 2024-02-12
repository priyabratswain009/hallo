package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ClaimsCob835Master;
import com.sunknowledge.dme.rcm.repository.ClaimsCob835MasterRepository;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.service.dto.ClaimsCob835MasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.ClaimsCob835MasterMapper;
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
 * Integration tests for the {@link ClaimsCob835MasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class ClaimsCob835MasterResourceIT {

    private static final String DEFAULT_PATIENT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_MEMBER_ID = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_MEMBER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FILE_NAME = "BBBBBBBBBB";

    private static final Double DEFAULT_TOTAL_CLAIM_CHARGE_AMOUNT = 1D;
    private static final Double UPDATED_TOTAL_CLAIM_CHARGE_AMOUNT = 2D;

    private static final Double DEFAULT_TOTAL_CLAIM_PAYMENT_AMOUNT = 1D;
    private static final Double UPDATED_TOTAL_CLAIM_PAYMENT_AMOUNT = 2D;

    private static final Double DEFAULT_TOTAL_PATIENT_RESPONSIBILITY_AMOUNT = 1D;
    private static final Double UPDATED_TOTAL_PATIENT_RESPONSIBILITY_AMOUNT = 2D;

    private static final LocalDate DEFAULT_CLAIM_RECEIVED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CLAIM_RECEIVED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_RECEIVED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_RECEIVED_ON = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_CONTROL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_CONTROL_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_PAYER_CLAIM_CONTROL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_CLAIM_CONTROL_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_CHECK_OR_EFT_TRACE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CHECK_OR_EFT_TRACE_NUMBER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CHECK_ISSUE_OR_EFT_EFFECTIVE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CHECK_ISSUE_OR_EFT_EFFECTIVE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREDIT_OR_DEBIT_FLAG_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CREDIT_OR_DEBIT_FLAG_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_METHOD_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_METHOD_CODE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_CROSSOVER_CARRIER_NAME = false;
    private static final Boolean UPDATED_CROSSOVER_CARRIER_NAME = true;

    private static final String DEFAULT_ENTITY_IDENTIFIER_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ENTITY_IDENTIFIER_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_ENTITY_TYPE_QUALIFIER = "AAAAAAAAAA";
    private static final String UPDATED_ENTITY_TYPE_QUALIFIER = "BBBBBBBBBB";

    private static final String DEFAULT_PAYER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PAYEE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PAYEE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PAYEE_NPI = "AAAAAAAAAA";
    private static final String UPDATED_PAYEE_NPI = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final UUID DEFAULT_CLAIMS_COB_835_MASTER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_CLAIMS_COB_835_MASTER_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/claims-cob-835-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{claimCob835MasterId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ClaimsCob835MasterRepository claimsCob835MasterRepository;

    @Autowired
    private ClaimsCob835MasterMapper claimsCob835MasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private ClaimsCob835Master claimsCob835Master;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClaimsCob835Master createEntity(EntityManager em) {
        ClaimsCob835Master claimsCob835Master = new ClaimsCob835Master()
            .patientFirstName(DEFAULT_PATIENT_FIRST_NAME)
            .patientLastName(DEFAULT_PATIENT_LAST_NAME)
            .patientMemberId(DEFAULT_PATIENT_MEMBER_ID)
            .fileName(DEFAULT_FILE_NAME)
            .totalClaimChargeAmount(DEFAULT_TOTAL_CLAIM_CHARGE_AMOUNT)
            .totalClaimPaymentAmount(DEFAULT_TOTAL_CLAIM_PAYMENT_AMOUNT)
            .totalPatientResponsibilityAmount(DEFAULT_TOTAL_PATIENT_RESPONSIBILITY_AMOUNT)
            .claimReceivedDate(DEFAULT_CLAIM_RECEIVED_DATE)
            .receivedOn(DEFAULT_RECEIVED_ON)
            .status(DEFAULT_STATUS)
            .patientControlNumber(DEFAULT_PATIENT_CONTROL_NUMBER)
            .payerClaimControlNumber(DEFAULT_PAYER_CLAIM_CONTROL_NUMBER)
            .checkOrEftTraceNumber(DEFAULT_CHECK_OR_EFT_TRACE_NUMBER)
            .checkIssueOrEftEffectiveDate(DEFAULT_CHECK_ISSUE_OR_EFT_EFFECTIVE_DATE)
            .creditOrDebitFlagCode(DEFAULT_CREDIT_OR_DEBIT_FLAG_CODE)
            .paymentMethodCode(DEFAULT_PAYMENT_METHOD_CODE)
            .crossoverCarrierName(DEFAULT_CROSSOVER_CARRIER_NAME)
            .entityIdentifierCode(DEFAULT_ENTITY_IDENTIFIER_CODE)
            .entityTypeQualifier(DEFAULT_ENTITY_TYPE_QUALIFIER)
            .payerName(DEFAULT_PAYER_NAME)
            .payeeName(DEFAULT_PAYEE_NAME)
            .payeeNpi(DEFAULT_PAYEE_NPI)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .claimsCob835MasterUuid(DEFAULT_CLAIMS_COB_835_MASTER_UUID);
        return claimsCob835Master;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClaimsCob835Master createUpdatedEntity(EntityManager em) {
        ClaimsCob835Master claimsCob835Master = new ClaimsCob835Master()
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .patientMemberId(UPDATED_PATIENT_MEMBER_ID)
            .fileName(UPDATED_FILE_NAME)
            .totalClaimChargeAmount(UPDATED_TOTAL_CLAIM_CHARGE_AMOUNT)
            .totalClaimPaymentAmount(UPDATED_TOTAL_CLAIM_PAYMENT_AMOUNT)
            .totalPatientResponsibilityAmount(UPDATED_TOTAL_PATIENT_RESPONSIBILITY_AMOUNT)
            .claimReceivedDate(UPDATED_CLAIM_RECEIVED_DATE)
            .receivedOn(UPDATED_RECEIVED_ON)
            .status(UPDATED_STATUS)
            .patientControlNumber(UPDATED_PATIENT_CONTROL_NUMBER)
            .payerClaimControlNumber(UPDATED_PAYER_CLAIM_CONTROL_NUMBER)
            .checkOrEftTraceNumber(UPDATED_CHECK_OR_EFT_TRACE_NUMBER)
            .checkIssueOrEftEffectiveDate(UPDATED_CHECK_ISSUE_OR_EFT_EFFECTIVE_DATE)
            .creditOrDebitFlagCode(UPDATED_CREDIT_OR_DEBIT_FLAG_CODE)
            .paymentMethodCode(UPDATED_PAYMENT_METHOD_CODE)
            .crossoverCarrierName(UPDATED_CROSSOVER_CARRIER_NAME)
            .entityIdentifierCode(UPDATED_ENTITY_IDENTIFIER_CODE)
            .entityTypeQualifier(UPDATED_ENTITY_TYPE_QUALIFIER)
            .payerName(UPDATED_PAYER_NAME)
            .payeeName(UPDATED_PAYEE_NAME)
            .payeeNpi(UPDATED_PAYEE_NPI)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .claimsCob835MasterUuid(UPDATED_CLAIMS_COB_835_MASTER_UUID);
        return claimsCob835Master;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(ClaimsCob835Master.class).block();
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
        claimsCob835Master = createEntity(em);
    }

    @Test
    void createClaimsCob835Master() throws Exception {
        int databaseSizeBeforeCreate = claimsCob835MasterRepository.findAll().collectList().block().size();
        // Create the ClaimsCob835Master
        ClaimsCob835MasterDTO claimsCob835MasterDTO = claimsCob835MasterMapper.toDto(claimsCob835Master);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(claimsCob835MasterDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the ClaimsCob835Master in the database
        List<ClaimsCob835Master> claimsCob835MasterList = claimsCob835MasterRepository.findAll().collectList().block();
        assertThat(claimsCob835MasterList).hasSize(databaseSizeBeforeCreate + 1);
        ClaimsCob835Master testClaimsCob835Master = claimsCob835MasterList.get(claimsCob835MasterList.size() - 1);
        assertThat(testClaimsCob835Master.getPatientFirstName()).isEqualTo(DEFAULT_PATIENT_FIRST_NAME);
        assertThat(testClaimsCob835Master.getPatientLastName()).isEqualTo(DEFAULT_PATIENT_LAST_NAME);
        assertThat(testClaimsCob835Master.getPatientMemberId()).isEqualTo(DEFAULT_PATIENT_MEMBER_ID);
        assertThat(testClaimsCob835Master.getFileName()).isEqualTo(DEFAULT_FILE_NAME);
        assertThat(testClaimsCob835Master.getTotalClaimChargeAmount()).isEqualTo(DEFAULT_TOTAL_CLAIM_CHARGE_AMOUNT);
        assertThat(testClaimsCob835Master.getTotalClaimPaymentAmount()).isEqualTo(DEFAULT_TOTAL_CLAIM_PAYMENT_AMOUNT);
        assertThat(testClaimsCob835Master.getTotalPatientResponsibilityAmount()).isEqualTo(DEFAULT_TOTAL_PATIENT_RESPONSIBILITY_AMOUNT);
        assertThat(testClaimsCob835Master.getClaimReceivedDate()).isEqualTo(DEFAULT_CLAIM_RECEIVED_DATE);
        assertThat(testClaimsCob835Master.getReceivedOn()).isEqualTo(DEFAULT_RECEIVED_ON);
        assertThat(testClaimsCob835Master.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testClaimsCob835Master.getPatientControlNumber()).isEqualTo(DEFAULT_PATIENT_CONTROL_NUMBER);
        assertThat(testClaimsCob835Master.getPayerClaimControlNumber()).isEqualTo(DEFAULT_PAYER_CLAIM_CONTROL_NUMBER);
        assertThat(testClaimsCob835Master.getCheckOrEftTraceNumber()).isEqualTo(DEFAULT_CHECK_OR_EFT_TRACE_NUMBER);
        assertThat(testClaimsCob835Master.getCheckIssueOrEftEffectiveDate()).isEqualTo(DEFAULT_CHECK_ISSUE_OR_EFT_EFFECTIVE_DATE);
        assertThat(testClaimsCob835Master.getCreditOrDebitFlagCode()).isEqualTo(DEFAULT_CREDIT_OR_DEBIT_FLAG_CODE);
        assertThat(testClaimsCob835Master.getPaymentMethodCode()).isEqualTo(DEFAULT_PAYMENT_METHOD_CODE);
        assertThat(testClaimsCob835Master.getCrossoverCarrierName()).isEqualTo(DEFAULT_CROSSOVER_CARRIER_NAME);
        assertThat(testClaimsCob835Master.getEntityIdentifierCode()).isEqualTo(DEFAULT_ENTITY_IDENTIFIER_CODE);
        assertThat(testClaimsCob835Master.getEntityTypeQualifier()).isEqualTo(DEFAULT_ENTITY_TYPE_QUALIFIER);
        assertThat(testClaimsCob835Master.getPayerName()).isEqualTo(DEFAULT_PAYER_NAME);
        assertThat(testClaimsCob835Master.getPayeeName()).isEqualTo(DEFAULT_PAYEE_NAME);
        assertThat(testClaimsCob835Master.getPayeeNpi()).isEqualTo(DEFAULT_PAYEE_NPI);
        assertThat(testClaimsCob835Master.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testClaimsCob835Master.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testClaimsCob835Master.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testClaimsCob835Master.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testClaimsCob835Master.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testClaimsCob835Master.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testClaimsCob835Master.getClaimsCob835MasterUuid()).isEqualTo(DEFAULT_CLAIMS_COB_835_MASTER_UUID);
    }

    @Test
    void createClaimsCob835MasterWithExistingId() throws Exception {
        // Create the ClaimsCob835Master with an existing ID
        claimsCob835Master.setClaimCob835MasterId(1L);
        ClaimsCob835MasterDTO claimsCob835MasterDTO = claimsCob835MasterMapper.toDto(claimsCob835Master);

        int databaseSizeBeforeCreate = claimsCob835MasterRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(claimsCob835MasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ClaimsCob835Master in the database
        List<ClaimsCob835Master> claimsCob835MasterList = claimsCob835MasterRepository.findAll().collectList().block();
        assertThat(claimsCob835MasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllClaimsCob835Masters() {
        // Initialize the database
        claimsCob835MasterRepository.save(claimsCob835Master).block();

        // Get all the claimsCob835MasterList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=claimCob835MasterId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].claimCob835MasterId")
            .value(hasItem(claimsCob835Master.getClaimCob835MasterId().intValue()))
            .jsonPath("$.[*].patientFirstName")
            .value(hasItem(DEFAULT_PATIENT_FIRST_NAME))
            .jsonPath("$.[*].patientLastName")
            .value(hasItem(DEFAULT_PATIENT_LAST_NAME))
            .jsonPath("$.[*].patientMemberId")
            .value(hasItem(DEFAULT_PATIENT_MEMBER_ID))
            .jsonPath("$.[*].fileName")
            .value(hasItem(DEFAULT_FILE_NAME))
            .jsonPath("$.[*].totalClaimChargeAmount")
            .value(hasItem(DEFAULT_TOTAL_CLAIM_CHARGE_AMOUNT.doubleValue()))
            .jsonPath("$.[*].totalClaimPaymentAmount")
            .value(hasItem(DEFAULT_TOTAL_CLAIM_PAYMENT_AMOUNT.doubleValue()))
            .jsonPath("$.[*].totalPatientResponsibilityAmount")
            .value(hasItem(DEFAULT_TOTAL_PATIENT_RESPONSIBILITY_AMOUNT.doubleValue()))
            .jsonPath("$.[*].claimReceivedDate")
            .value(hasItem(DEFAULT_CLAIM_RECEIVED_DATE.toString()))
            .jsonPath("$.[*].receivedOn")
            .value(hasItem(DEFAULT_RECEIVED_ON.toString()))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS))
            .jsonPath("$.[*].patientControlNumber")
            .value(hasItem(DEFAULT_PATIENT_CONTROL_NUMBER))
            .jsonPath("$.[*].payerClaimControlNumber")
            .value(hasItem(DEFAULT_PAYER_CLAIM_CONTROL_NUMBER))
            .jsonPath("$.[*].checkOrEftTraceNumber")
            .value(hasItem(DEFAULT_CHECK_OR_EFT_TRACE_NUMBER))
            .jsonPath("$.[*].checkIssueOrEftEffectiveDate")
            .value(hasItem(DEFAULT_CHECK_ISSUE_OR_EFT_EFFECTIVE_DATE.toString()))
            .jsonPath("$.[*].creditOrDebitFlagCode")
            .value(hasItem(DEFAULT_CREDIT_OR_DEBIT_FLAG_CODE))
            .jsonPath("$.[*].paymentMethodCode")
            .value(hasItem(DEFAULT_PAYMENT_METHOD_CODE))
            .jsonPath("$.[*].crossoverCarrierName")
            .value(hasItem(DEFAULT_CROSSOVER_CARRIER_NAME.booleanValue()))
            .jsonPath("$.[*].entityIdentifierCode")
            .value(hasItem(DEFAULT_ENTITY_IDENTIFIER_CODE))
            .jsonPath("$.[*].entityTypeQualifier")
            .value(hasItem(DEFAULT_ENTITY_TYPE_QUALIFIER))
            .jsonPath("$.[*].payerName")
            .value(hasItem(DEFAULT_PAYER_NAME))
            .jsonPath("$.[*].payeeName")
            .value(hasItem(DEFAULT_PAYEE_NAME))
            .jsonPath("$.[*].payeeNpi")
            .value(hasItem(DEFAULT_PAYEE_NPI))
            .jsonPath("$.[*].createdDate")
            .value(hasItem(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.[*].updatedDate")
            .value(hasItem(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.[*].createdById")
            .value(hasItem(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.[*].createdByName")
            .value(hasItem(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.[*].updatedById")
            .value(hasItem(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.[*].updatedByName")
            .value(hasItem(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.[*].claimsCob835MasterUuid")
            .value(hasItem(DEFAULT_CLAIMS_COB_835_MASTER_UUID.toString()));
    }

    @Test
    void getClaimsCob835Master() {
        // Initialize the database
        claimsCob835MasterRepository.save(claimsCob835Master).block();

        // Get the claimsCob835Master
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, claimsCob835Master.getClaimCob835MasterId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.claimCob835MasterId")
            .value(is(claimsCob835Master.getClaimCob835MasterId().intValue()))
            .jsonPath("$.patientFirstName")
            .value(is(DEFAULT_PATIENT_FIRST_NAME))
            .jsonPath("$.patientLastName")
            .value(is(DEFAULT_PATIENT_LAST_NAME))
            .jsonPath("$.patientMemberId")
            .value(is(DEFAULT_PATIENT_MEMBER_ID))
            .jsonPath("$.fileName")
            .value(is(DEFAULT_FILE_NAME))
            .jsonPath("$.totalClaimChargeAmount")
            .value(is(DEFAULT_TOTAL_CLAIM_CHARGE_AMOUNT.doubleValue()))
            .jsonPath("$.totalClaimPaymentAmount")
            .value(is(DEFAULT_TOTAL_CLAIM_PAYMENT_AMOUNT.doubleValue()))
            .jsonPath("$.totalPatientResponsibilityAmount")
            .value(is(DEFAULT_TOTAL_PATIENT_RESPONSIBILITY_AMOUNT.doubleValue()))
            .jsonPath("$.claimReceivedDate")
            .value(is(DEFAULT_CLAIM_RECEIVED_DATE.toString()))
            .jsonPath("$.receivedOn")
            .value(is(DEFAULT_RECEIVED_ON.toString()))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS))
            .jsonPath("$.patientControlNumber")
            .value(is(DEFAULT_PATIENT_CONTROL_NUMBER))
            .jsonPath("$.payerClaimControlNumber")
            .value(is(DEFAULT_PAYER_CLAIM_CONTROL_NUMBER))
            .jsonPath("$.checkOrEftTraceNumber")
            .value(is(DEFAULT_CHECK_OR_EFT_TRACE_NUMBER))
            .jsonPath("$.checkIssueOrEftEffectiveDate")
            .value(is(DEFAULT_CHECK_ISSUE_OR_EFT_EFFECTIVE_DATE.toString()))
            .jsonPath("$.creditOrDebitFlagCode")
            .value(is(DEFAULT_CREDIT_OR_DEBIT_FLAG_CODE))
            .jsonPath("$.paymentMethodCode")
            .value(is(DEFAULT_PAYMENT_METHOD_CODE))
            .jsonPath("$.crossoverCarrierName")
            .value(is(DEFAULT_CROSSOVER_CARRIER_NAME.booleanValue()))
            .jsonPath("$.entityIdentifierCode")
            .value(is(DEFAULT_ENTITY_IDENTIFIER_CODE))
            .jsonPath("$.entityTypeQualifier")
            .value(is(DEFAULT_ENTITY_TYPE_QUALIFIER))
            .jsonPath("$.payerName")
            .value(is(DEFAULT_PAYER_NAME))
            .jsonPath("$.payeeName")
            .value(is(DEFAULT_PAYEE_NAME))
            .jsonPath("$.payeeNpi")
            .value(is(DEFAULT_PAYEE_NPI))
            .jsonPath("$.createdDate")
            .value(is(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.updatedDate")
            .value(is(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.createdById")
            .value(is(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.createdByName")
            .value(is(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.updatedById")
            .value(is(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.updatedByName")
            .value(is(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.claimsCob835MasterUuid")
            .value(is(DEFAULT_CLAIMS_COB_835_MASTER_UUID.toString()));
    }

    @Test
    void getNonExistingClaimsCob835Master() {
        // Get the claimsCob835Master
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingClaimsCob835Master() throws Exception {
        // Initialize the database
        claimsCob835MasterRepository.save(claimsCob835Master).block();

        int databaseSizeBeforeUpdate = claimsCob835MasterRepository.findAll().collectList().block().size();

        // Update the claimsCob835Master
        ClaimsCob835Master updatedClaimsCob835Master = claimsCob835MasterRepository
            .findById(claimsCob835Master.getClaimCob835MasterId())
            .block();
        updatedClaimsCob835Master
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .patientMemberId(UPDATED_PATIENT_MEMBER_ID)
            .fileName(UPDATED_FILE_NAME)
            .totalClaimChargeAmount(UPDATED_TOTAL_CLAIM_CHARGE_AMOUNT)
            .totalClaimPaymentAmount(UPDATED_TOTAL_CLAIM_PAYMENT_AMOUNT)
            .totalPatientResponsibilityAmount(UPDATED_TOTAL_PATIENT_RESPONSIBILITY_AMOUNT)
            .claimReceivedDate(UPDATED_CLAIM_RECEIVED_DATE)
            .receivedOn(UPDATED_RECEIVED_ON)
            .status(UPDATED_STATUS)
            .patientControlNumber(UPDATED_PATIENT_CONTROL_NUMBER)
            .payerClaimControlNumber(UPDATED_PAYER_CLAIM_CONTROL_NUMBER)
            .checkOrEftTraceNumber(UPDATED_CHECK_OR_EFT_TRACE_NUMBER)
            .checkIssueOrEftEffectiveDate(UPDATED_CHECK_ISSUE_OR_EFT_EFFECTIVE_DATE)
            .creditOrDebitFlagCode(UPDATED_CREDIT_OR_DEBIT_FLAG_CODE)
            .paymentMethodCode(UPDATED_PAYMENT_METHOD_CODE)
            .crossoverCarrierName(UPDATED_CROSSOVER_CARRIER_NAME)
            .entityIdentifierCode(UPDATED_ENTITY_IDENTIFIER_CODE)
            .entityTypeQualifier(UPDATED_ENTITY_TYPE_QUALIFIER)
            .payerName(UPDATED_PAYER_NAME)
            .payeeName(UPDATED_PAYEE_NAME)
            .payeeNpi(UPDATED_PAYEE_NPI)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .claimsCob835MasterUuid(UPDATED_CLAIMS_COB_835_MASTER_UUID);
        ClaimsCob835MasterDTO claimsCob835MasterDTO = claimsCob835MasterMapper.toDto(updatedClaimsCob835Master);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, claimsCob835MasterDTO.getClaimCob835MasterId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(claimsCob835MasterDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ClaimsCob835Master in the database
        List<ClaimsCob835Master> claimsCob835MasterList = claimsCob835MasterRepository.findAll().collectList().block();
        assertThat(claimsCob835MasterList).hasSize(databaseSizeBeforeUpdate);
        ClaimsCob835Master testClaimsCob835Master = claimsCob835MasterList.get(claimsCob835MasterList.size() - 1);
        assertThat(testClaimsCob835Master.getPatientFirstName()).isEqualTo(UPDATED_PATIENT_FIRST_NAME);
        assertThat(testClaimsCob835Master.getPatientLastName()).isEqualTo(UPDATED_PATIENT_LAST_NAME);
        assertThat(testClaimsCob835Master.getPatientMemberId()).isEqualTo(UPDATED_PATIENT_MEMBER_ID);
        assertThat(testClaimsCob835Master.getFileName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testClaimsCob835Master.getTotalClaimChargeAmount()).isEqualTo(UPDATED_TOTAL_CLAIM_CHARGE_AMOUNT);
        assertThat(testClaimsCob835Master.getTotalClaimPaymentAmount()).isEqualTo(UPDATED_TOTAL_CLAIM_PAYMENT_AMOUNT);
        assertThat(testClaimsCob835Master.getTotalPatientResponsibilityAmount()).isEqualTo(UPDATED_TOTAL_PATIENT_RESPONSIBILITY_AMOUNT);
        assertThat(testClaimsCob835Master.getClaimReceivedDate()).isEqualTo(UPDATED_CLAIM_RECEIVED_DATE);
        assertThat(testClaimsCob835Master.getReceivedOn()).isEqualTo(UPDATED_RECEIVED_ON);
        assertThat(testClaimsCob835Master.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testClaimsCob835Master.getPatientControlNumber()).isEqualTo(UPDATED_PATIENT_CONTROL_NUMBER);
        assertThat(testClaimsCob835Master.getPayerClaimControlNumber()).isEqualTo(UPDATED_PAYER_CLAIM_CONTROL_NUMBER);
        assertThat(testClaimsCob835Master.getCheckOrEftTraceNumber()).isEqualTo(UPDATED_CHECK_OR_EFT_TRACE_NUMBER);
        assertThat(testClaimsCob835Master.getCheckIssueOrEftEffectiveDate()).isEqualTo(UPDATED_CHECK_ISSUE_OR_EFT_EFFECTIVE_DATE);
        assertThat(testClaimsCob835Master.getCreditOrDebitFlagCode()).isEqualTo(UPDATED_CREDIT_OR_DEBIT_FLAG_CODE);
        assertThat(testClaimsCob835Master.getPaymentMethodCode()).isEqualTo(UPDATED_PAYMENT_METHOD_CODE);
        assertThat(testClaimsCob835Master.getCrossoverCarrierName()).isEqualTo(UPDATED_CROSSOVER_CARRIER_NAME);
        assertThat(testClaimsCob835Master.getEntityIdentifierCode()).isEqualTo(UPDATED_ENTITY_IDENTIFIER_CODE);
        assertThat(testClaimsCob835Master.getEntityTypeQualifier()).isEqualTo(UPDATED_ENTITY_TYPE_QUALIFIER);
        assertThat(testClaimsCob835Master.getPayerName()).isEqualTo(UPDATED_PAYER_NAME);
        assertThat(testClaimsCob835Master.getPayeeName()).isEqualTo(UPDATED_PAYEE_NAME);
        assertThat(testClaimsCob835Master.getPayeeNpi()).isEqualTo(UPDATED_PAYEE_NPI);
        assertThat(testClaimsCob835Master.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testClaimsCob835Master.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testClaimsCob835Master.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testClaimsCob835Master.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testClaimsCob835Master.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testClaimsCob835Master.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testClaimsCob835Master.getClaimsCob835MasterUuid()).isEqualTo(UPDATED_CLAIMS_COB_835_MASTER_UUID);
    }

    @Test
    void putNonExistingClaimsCob835Master() throws Exception {
        int databaseSizeBeforeUpdate = claimsCob835MasterRepository.findAll().collectList().block().size();
        claimsCob835Master.setClaimCob835MasterId(count.incrementAndGet());

        // Create the ClaimsCob835Master
        ClaimsCob835MasterDTO claimsCob835MasterDTO = claimsCob835MasterMapper.toDto(claimsCob835Master);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, claimsCob835MasterDTO.getClaimCob835MasterId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(claimsCob835MasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ClaimsCob835Master in the database
        List<ClaimsCob835Master> claimsCob835MasterList = claimsCob835MasterRepository.findAll().collectList().block();
        assertThat(claimsCob835MasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchClaimsCob835Master() throws Exception {
        int databaseSizeBeforeUpdate = claimsCob835MasterRepository.findAll().collectList().block().size();
        claimsCob835Master.setClaimCob835MasterId(count.incrementAndGet());

        // Create the ClaimsCob835Master
        ClaimsCob835MasterDTO claimsCob835MasterDTO = claimsCob835MasterMapper.toDto(claimsCob835Master);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(claimsCob835MasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ClaimsCob835Master in the database
        List<ClaimsCob835Master> claimsCob835MasterList = claimsCob835MasterRepository.findAll().collectList().block();
        assertThat(claimsCob835MasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamClaimsCob835Master() throws Exception {
        int databaseSizeBeforeUpdate = claimsCob835MasterRepository.findAll().collectList().block().size();
        claimsCob835Master.setClaimCob835MasterId(count.incrementAndGet());

        // Create the ClaimsCob835Master
        ClaimsCob835MasterDTO claimsCob835MasterDTO = claimsCob835MasterMapper.toDto(claimsCob835Master);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(claimsCob835MasterDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the ClaimsCob835Master in the database
        List<ClaimsCob835Master> claimsCob835MasterList = claimsCob835MasterRepository.findAll().collectList().block();
        assertThat(claimsCob835MasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateClaimsCob835MasterWithPatch() throws Exception {
        // Initialize the database
        claimsCob835MasterRepository.save(claimsCob835Master).block();

        int databaseSizeBeforeUpdate = claimsCob835MasterRepository.findAll().collectList().block().size();

        // Update the claimsCob835Master using partial update
        ClaimsCob835Master partialUpdatedClaimsCob835Master = new ClaimsCob835Master();
        partialUpdatedClaimsCob835Master.setClaimCob835MasterId(claimsCob835Master.getClaimCob835MasterId());

        partialUpdatedClaimsCob835Master
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .fileName(UPDATED_FILE_NAME)
            .totalPatientResponsibilityAmount(UPDATED_TOTAL_PATIENT_RESPONSIBILITY_AMOUNT)
            .status(UPDATED_STATUS)
            .patientControlNumber(UPDATED_PATIENT_CONTROL_NUMBER)
            .checkIssueOrEftEffectiveDate(UPDATED_CHECK_ISSUE_OR_EFT_EFFECTIVE_DATE)
            .paymentMethodCode(UPDATED_PAYMENT_METHOD_CODE)
            .entityIdentifierCode(UPDATED_ENTITY_IDENTIFIER_CODE)
            .payerName(UPDATED_PAYER_NAME)
            .payeeName(UPDATED_PAYEE_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .claimsCob835MasterUuid(UPDATED_CLAIMS_COB_835_MASTER_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedClaimsCob835Master.getClaimCob835MasterId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedClaimsCob835Master))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ClaimsCob835Master in the database
        List<ClaimsCob835Master> claimsCob835MasterList = claimsCob835MasterRepository.findAll().collectList().block();
        assertThat(claimsCob835MasterList).hasSize(databaseSizeBeforeUpdate);
        ClaimsCob835Master testClaimsCob835Master = claimsCob835MasterList.get(claimsCob835MasterList.size() - 1);
        assertThat(testClaimsCob835Master.getPatientFirstName()).isEqualTo(DEFAULT_PATIENT_FIRST_NAME);
        assertThat(testClaimsCob835Master.getPatientLastName()).isEqualTo(UPDATED_PATIENT_LAST_NAME);
        assertThat(testClaimsCob835Master.getPatientMemberId()).isEqualTo(DEFAULT_PATIENT_MEMBER_ID);
        assertThat(testClaimsCob835Master.getFileName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testClaimsCob835Master.getTotalClaimChargeAmount()).isEqualTo(DEFAULT_TOTAL_CLAIM_CHARGE_AMOUNT);
        assertThat(testClaimsCob835Master.getTotalClaimPaymentAmount()).isEqualTo(DEFAULT_TOTAL_CLAIM_PAYMENT_AMOUNT);
        assertThat(testClaimsCob835Master.getTotalPatientResponsibilityAmount()).isEqualTo(UPDATED_TOTAL_PATIENT_RESPONSIBILITY_AMOUNT);
        assertThat(testClaimsCob835Master.getClaimReceivedDate()).isEqualTo(DEFAULT_CLAIM_RECEIVED_DATE);
        assertThat(testClaimsCob835Master.getReceivedOn()).isEqualTo(DEFAULT_RECEIVED_ON);
        assertThat(testClaimsCob835Master.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testClaimsCob835Master.getPatientControlNumber()).isEqualTo(UPDATED_PATIENT_CONTROL_NUMBER);
        assertThat(testClaimsCob835Master.getPayerClaimControlNumber()).isEqualTo(DEFAULT_PAYER_CLAIM_CONTROL_NUMBER);
        assertThat(testClaimsCob835Master.getCheckOrEftTraceNumber()).isEqualTo(DEFAULT_CHECK_OR_EFT_TRACE_NUMBER);
        assertThat(testClaimsCob835Master.getCheckIssueOrEftEffectiveDate()).isEqualTo(UPDATED_CHECK_ISSUE_OR_EFT_EFFECTIVE_DATE);
        assertThat(testClaimsCob835Master.getCreditOrDebitFlagCode()).isEqualTo(DEFAULT_CREDIT_OR_DEBIT_FLAG_CODE);
        assertThat(testClaimsCob835Master.getPaymentMethodCode()).isEqualTo(UPDATED_PAYMENT_METHOD_CODE);
        assertThat(testClaimsCob835Master.getCrossoverCarrierName()).isEqualTo(DEFAULT_CROSSOVER_CARRIER_NAME);
        assertThat(testClaimsCob835Master.getEntityIdentifierCode()).isEqualTo(UPDATED_ENTITY_IDENTIFIER_CODE);
        assertThat(testClaimsCob835Master.getEntityTypeQualifier()).isEqualTo(DEFAULT_ENTITY_TYPE_QUALIFIER);
        assertThat(testClaimsCob835Master.getPayerName()).isEqualTo(UPDATED_PAYER_NAME);
        assertThat(testClaimsCob835Master.getPayeeName()).isEqualTo(UPDATED_PAYEE_NAME);
        assertThat(testClaimsCob835Master.getPayeeNpi()).isEqualTo(DEFAULT_PAYEE_NPI);
        assertThat(testClaimsCob835Master.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testClaimsCob835Master.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testClaimsCob835Master.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testClaimsCob835Master.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testClaimsCob835Master.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testClaimsCob835Master.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testClaimsCob835Master.getClaimsCob835MasterUuid()).isEqualTo(UPDATED_CLAIMS_COB_835_MASTER_UUID);
    }

    @Test
    void fullUpdateClaimsCob835MasterWithPatch() throws Exception {
        // Initialize the database
        claimsCob835MasterRepository.save(claimsCob835Master).block();

        int databaseSizeBeforeUpdate = claimsCob835MasterRepository.findAll().collectList().block().size();

        // Update the claimsCob835Master using partial update
        ClaimsCob835Master partialUpdatedClaimsCob835Master = new ClaimsCob835Master();
        partialUpdatedClaimsCob835Master.setClaimCob835MasterId(claimsCob835Master.getClaimCob835MasterId());

        partialUpdatedClaimsCob835Master
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .patientMemberId(UPDATED_PATIENT_MEMBER_ID)
            .fileName(UPDATED_FILE_NAME)
            .totalClaimChargeAmount(UPDATED_TOTAL_CLAIM_CHARGE_AMOUNT)
            .totalClaimPaymentAmount(UPDATED_TOTAL_CLAIM_PAYMENT_AMOUNT)
            .totalPatientResponsibilityAmount(UPDATED_TOTAL_PATIENT_RESPONSIBILITY_AMOUNT)
            .claimReceivedDate(UPDATED_CLAIM_RECEIVED_DATE)
            .receivedOn(UPDATED_RECEIVED_ON)
            .status(UPDATED_STATUS)
            .patientControlNumber(UPDATED_PATIENT_CONTROL_NUMBER)
            .payerClaimControlNumber(UPDATED_PAYER_CLAIM_CONTROL_NUMBER)
            .checkOrEftTraceNumber(UPDATED_CHECK_OR_EFT_TRACE_NUMBER)
            .checkIssueOrEftEffectiveDate(UPDATED_CHECK_ISSUE_OR_EFT_EFFECTIVE_DATE)
            .creditOrDebitFlagCode(UPDATED_CREDIT_OR_DEBIT_FLAG_CODE)
            .paymentMethodCode(UPDATED_PAYMENT_METHOD_CODE)
            .crossoverCarrierName(UPDATED_CROSSOVER_CARRIER_NAME)
            .entityIdentifierCode(UPDATED_ENTITY_IDENTIFIER_CODE)
            .entityTypeQualifier(UPDATED_ENTITY_TYPE_QUALIFIER)
            .payerName(UPDATED_PAYER_NAME)
            .payeeName(UPDATED_PAYEE_NAME)
            .payeeNpi(UPDATED_PAYEE_NPI)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .claimsCob835MasterUuid(UPDATED_CLAIMS_COB_835_MASTER_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedClaimsCob835Master.getClaimCob835MasterId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedClaimsCob835Master))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ClaimsCob835Master in the database
        List<ClaimsCob835Master> claimsCob835MasterList = claimsCob835MasterRepository.findAll().collectList().block();
        assertThat(claimsCob835MasterList).hasSize(databaseSizeBeforeUpdate);
        ClaimsCob835Master testClaimsCob835Master = claimsCob835MasterList.get(claimsCob835MasterList.size() - 1);
        assertThat(testClaimsCob835Master.getPatientFirstName()).isEqualTo(UPDATED_PATIENT_FIRST_NAME);
        assertThat(testClaimsCob835Master.getPatientLastName()).isEqualTo(UPDATED_PATIENT_LAST_NAME);
        assertThat(testClaimsCob835Master.getPatientMemberId()).isEqualTo(UPDATED_PATIENT_MEMBER_ID);
        assertThat(testClaimsCob835Master.getFileName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testClaimsCob835Master.getTotalClaimChargeAmount()).isEqualTo(UPDATED_TOTAL_CLAIM_CHARGE_AMOUNT);
        assertThat(testClaimsCob835Master.getTotalClaimPaymentAmount()).isEqualTo(UPDATED_TOTAL_CLAIM_PAYMENT_AMOUNT);
        assertThat(testClaimsCob835Master.getTotalPatientResponsibilityAmount()).isEqualTo(UPDATED_TOTAL_PATIENT_RESPONSIBILITY_AMOUNT);
        assertThat(testClaimsCob835Master.getClaimReceivedDate()).isEqualTo(UPDATED_CLAIM_RECEIVED_DATE);
        assertThat(testClaimsCob835Master.getReceivedOn()).isEqualTo(UPDATED_RECEIVED_ON);
        assertThat(testClaimsCob835Master.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testClaimsCob835Master.getPatientControlNumber()).isEqualTo(UPDATED_PATIENT_CONTROL_NUMBER);
        assertThat(testClaimsCob835Master.getPayerClaimControlNumber()).isEqualTo(UPDATED_PAYER_CLAIM_CONTROL_NUMBER);
        assertThat(testClaimsCob835Master.getCheckOrEftTraceNumber()).isEqualTo(UPDATED_CHECK_OR_EFT_TRACE_NUMBER);
        assertThat(testClaimsCob835Master.getCheckIssueOrEftEffectiveDate()).isEqualTo(UPDATED_CHECK_ISSUE_OR_EFT_EFFECTIVE_DATE);
        assertThat(testClaimsCob835Master.getCreditOrDebitFlagCode()).isEqualTo(UPDATED_CREDIT_OR_DEBIT_FLAG_CODE);
        assertThat(testClaimsCob835Master.getPaymentMethodCode()).isEqualTo(UPDATED_PAYMENT_METHOD_CODE);
        assertThat(testClaimsCob835Master.getCrossoverCarrierName()).isEqualTo(UPDATED_CROSSOVER_CARRIER_NAME);
        assertThat(testClaimsCob835Master.getEntityIdentifierCode()).isEqualTo(UPDATED_ENTITY_IDENTIFIER_CODE);
        assertThat(testClaimsCob835Master.getEntityTypeQualifier()).isEqualTo(UPDATED_ENTITY_TYPE_QUALIFIER);
        assertThat(testClaimsCob835Master.getPayerName()).isEqualTo(UPDATED_PAYER_NAME);
        assertThat(testClaimsCob835Master.getPayeeName()).isEqualTo(UPDATED_PAYEE_NAME);
        assertThat(testClaimsCob835Master.getPayeeNpi()).isEqualTo(UPDATED_PAYEE_NPI);
        assertThat(testClaimsCob835Master.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testClaimsCob835Master.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testClaimsCob835Master.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testClaimsCob835Master.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testClaimsCob835Master.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testClaimsCob835Master.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testClaimsCob835Master.getClaimsCob835MasterUuid()).isEqualTo(UPDATED_CLAIMS_COB_835_MASTER_UUID);
    }

    @Test
    void patchNonExistingClaimsCob835Master() throws Exception {
        int databaseSizeBeforeUpdate = claimsCob835MasterRepository.findAll().collectList().block().size();
        claimsCob835Master.setClaimCob835MasterId(count.incrementAndGet());

        // Create the ClaimsCob835Master
        ClaimsCob835MasterDTO claimsCob835MasterDTO = claimsCob835MasterMapper.toDto(claimsCob835Master);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, claimsCob835MasterDTO.getClaimCob835MasterId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(claimsCob835MasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ClaimsCob835Master in the database
        List<ClaimsCob835Master> claimsCob835MasterList = claimsCob835MasterRepository.findAll().collectList().block();
        assertThat(claimsCob835MasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchClaimsCob835Master() throws Exception {
        int databaseSizeBeforeUpdate = claimsCob835MasterRepository.findAll().collectList().block().size();
        claimsCob835Master.setClaimCob835MasterId(count.incrementAndGet());

        // Create the ClaimsCob835Master
        ClaimsCob835MasterDTO claimsCob835MasterDTO = claimsCob835MasterMapper.toDto(claimsCob835Master);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(claimsCob835MasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ClaimsCob835Master in the database
        List<ClaimsCob835Master> claimsCob835MasterList = claimsCob835MasterRepository.findAll().collectList().block();
        assertThat(claimsCob835MasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamClaimsCob835Master() throws Exception {
        int databaseSizeBeforeUpdate = claimsCob835MasterRepository.findAll().collectList().block().size();
        claimsCob835Master.setClaimCob835MasterId(count.incrementAndGet());

        // Create the ClaimsCob835Master
        ClaimsCob835MasterDTO claimsCob835MasterDTO = claimsCob835MasterMapper.toDto(claimsCob835Master);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(claimsCob835MasterDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the ClaimsCob835Master in the database
        List<ClaimsCob835Master> claimsCob835MasterList = claimsCob835MasterRepository.findAll().collectList().block();
        assertThat(claimsCob835MasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteClaimsCob835Master() {
        // Initialize the database
        claimsCob835MasterRepository.save(claimsCob835Master).block();

        int databaseSizeBeforeDelete = claimsCob835MasterRepository.findAll().collectList().block().size();

        // Delete the claimsCob835Master
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, claimsCob835Master.getClaimCob835MasterId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<ClaimsCob835Master> claimsCob835MasterList = claimsCob835MasterRepository.findAll().collectList().block();
        assertThat(claimsCob835MasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
