package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ClaimSubmissionStatus;
import com.sunknowledge.dme.rcm.repository.ClaimSubmissionStatusRepository;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.service.dto.ClaimSubmissionStatusDTO;
import com.sunknowledge.dme.rcm.service.mapper.ClaimSubmissionStatusMapper;
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
 * Integration tests for the {@link ClaimSubmissionStatusResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class ClaimSubmissionStatusResourceIT {

    private static final Long DEFAULT_SALES_ORDER_ID = 1L;
    private static final Long UPDATED_SALES_ORDER_ID = 2L;

    private static final String DEFAULT_SALES_ORDER_NO = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_NO = "BBBBBBBBBB";

    private static final Long DEFAULT_INVOICE_ID = 1L;
    private static final Long UPDATED_INVOICE_ID = 2L;

    private static final String DEFAULT_INVOICE_NO = "AAAAAAAAAA";
    private static final String UPDATED_INVOICE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PAYOR_LEVEL = "AAAAAAAAAA";
    private static final String UPDATED_PAYOR_LEVEL = "BBBBBBBBBB";

    private static final String DEFAULT_PAYOR_ID_NO = "AAAAAAAAAA";
    private static final String UPDATED_PAYOR_ID_NO = "BBBBBBBBBB";

    private static final Long DEFAULT_CLAIM_SUBMISSION_DATA_ID = 1L;
    private static final Long UPDATED_CLAIM_SUBMISSION_DATA_ID = 2L;

    private static final String DEFAULT_INT_CLAIM_NO = "AAAAAAAAAA";
    private static final String UPDATED_INT_CLAIM_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_ACCOUNT_NO = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_ACCOUNT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PAYOR_CLAIM_CONTROL_NO = "AAAAAAAAAA";
    private static final String UPDATED_PAYOR_CLAIM_CONTROL_NO = "BBBBBBBBBB";

    private static final String DEFAULT_ORIGINAL_CLAIM_CONTROL_NO = "AAAAAAAAAA";
    private static final String UPDATED_ORIGINAL_CLAIM_CONTROL_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_ID_NO = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_ID_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PAYOR = "AAAAAAAAAA";
    private static final String UPDATED_PAYOR = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CLAIM_SUBMISSION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CLAIM_SUBMISSION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SUBMISSION_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_SUBMISSION_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_SUBMISSION_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_SUBMISSION_NOTE = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_STATUS_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_STATUS_NOTES = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_RESPONSE_STATUS_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_RESPONSE_STATUS_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_RESPONSE_277_RECORD_ID = 1L;
    private static final Long UPDATED_RESPONSE_277_RECORD_ID = 2L;

    private static final String DEFAULT_ERA_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_ERA_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_ERA_STATUS_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_ERA_STATUS_NOTES = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_ERA_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ERA_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_ERA_835_RECORD_ID = 1L;
    private static final Long UPDATED_ERA_835_RECORD_ID = 2L;

    private static final String DEFAULT_RESUBMISSIN_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_RESUBMISSIN_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_RESUBMISSION_DETAIL_ID = 1L;
    private static final Long UPDATED_RESUBMISSION_DETAIL_ID = 2L;

    private static final String DEFAULT_RESUBMISSION_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_RESUBMISSION_NOTES = "BBBBBBBBBB";

    private static final String DEFAULT_VOID_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_VOID_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_VOID_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_VOID_NOTE = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_CLAIM_SUBMISSION_STATUS_UUID = UUID.randomUUID();
    private static final UUID UPDATED_CLAIM_SUBMISSION_STATUS_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/claim-submission-statuses";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{claimStatusId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ClaimSubmissionStatusRepository claimSubmissionStatusRepository;

    @Autowired
    private ClaimSubmissionStatusMapper claimSubmissionStatusMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private ClaimSubmissionStatus claimSubmissionStatus;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClaimSubmissionStatus createEntity(EntityManager em) {
        ClaimSubmissionStatus claimSubmissionStatus = new ClaimSubmissionStatus()
            .salesOrderId(DEFAULT_SALES_ORDER_ID)
            .salesOrderNo(DEFAULT_SALES_ORDER_NO)
            .invoiceId(DEFAULT_INVOICE_ID)
            .invoiceNo(DEFAULT_INVOICE_NO)
            .payorLevel(DEFAULT_PAYOR_LEVEL)
            .payorIdNo(DEFAULT_PAYOR_ID_NO)
            .claimSubmissionDataId(DEFAULT_CLAIM_SUBMISSION_DATA_ID)
            .intClaimNo(DEFAULT_INT_CLAIM_NO)
            .patientAccountNo(DEFAULT_PATIENT_ACCOUNT_NO)
            .payorClaimControlNo(DEFAULT_PAYOR_CLAIM_CONTROL_NO)
            .originalClaimControlNo(DEFAULT_ORIGINAL_CLAIM_CONTROL_NO)
            .patientIdNo(DEFAULT_PATIENT_ID_NO)
            .payor(DEFAULT_PAYOR)
            .claimSubmissionDate(DEFAULT_CLAIM_SUBMISSION_DATE)
            .submissionStatus(DEFAULT_SUBMISSION_STATUS)
            .submissionNote(DEFAULT_SUBMISSION_NOTE)
            .responseStatus(DEFAULT_RESPONSE_STATUS)
            .responseStatusNotes(DEFAULT_RESPONSE_STATUS_NOTES)
            .responseStatusDate(DEFAULT_RESPONSE_STATUS_DATE)
            .response277RecordId(DEFAULT_RESPONSE_277_RECORD_ID)
            .eraStatus(DEFAULT_ERA_STATUS)
            .eraStatusNotes(DEFAULT_ERA_STATUS_NOTES)
            .eraDate(DEFAULT_ERA_DATE)
            .era835RecordId(DEFAULT_ERA_835_RECORD_ID)
            .resubmissinStatus(DEFAULT_RESUBMISSIN_STATUS)
            .resubmissionDetailId(DEFAULT_RESUBMISSION_DETAIL_ID)
            .resubmissionNotes(DEFAULT_RESUBMISSION_NOTES)
            .voidStatus(DEFAULT_VOID_STATUS)
            .voidNote(DEFAULT_VOID_NOTE)
            .status(DEFAULT_STATUS)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .claimSubmissionStatusUuid(DEFAULT_CLAIM_SUBMISSION_STATUS_UUID);
        return claimSubmissionStatus;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClaimSubmissionStatus createUpdatedEntity(EntityManager em) {
        ClaimSubmissionStatus claimSubmissionStatus = new ClaimSubmissionStatus()
            .salesOrderId(UPDATED_SALES_ORDER_ID)
            .salesOrderNo(UPDATED_SALES_ORDER_NO)
            .invoiceId(UPDATED_INVOICE_ID)
            .invoiceNo(UPDATED_INVOICE_NO)
            .payorLevel(UPDATED_PAYOR_LEVEL)
            .payorIdNo(UPDATED_PAYOR_ID_NO)
            .claimSubmissionDataId(UPDATED_CLAIM_SUBMISSION_DATA_ID)
            .intClaimNo(UPDATED_INT_CLAIM_NO)
            .patientAccountNo(UPDATED_PATIENT_ACCOUNT_NO)
            .payorClaimControlNo(UPDATED_PAYOR_CLAIM_CONTROL_NO)
            .originalClaimControlNo(UPDATED_ORIGINAL_CLAIM_CONTROL_NO)
            .patientIdNo(UPDATED_PATIENT_ID_NO)
            .payor(UPDATED_PAYOR)
            .claimSubmissionDate(UPDATED_CLAIM_SUBMISSION_DATE)
            .submissionStatus(UPDATED_SUBMISSION_STATUS)
            .submissionNote(UPDATED_SUBMISSION_NOTE)
            .responseStatus(UPDATED_RESPONSE_STATUS)
            .responseStatusNotes(UPDATED_RESPONSE_STATUS_NOTES)
            .responseStatusDate(UPDATED_RESPONSE_STATUS_DATE)
            .response277RecordId(UPDATED_RESPONSE_277_RECORD_ID)
            .eraStatus(UPDATED_ERA_STATUS)
            .eraStatusNotes(UPDATED_ERA_STATUS_NOTES)
            .eraDate(UPDATED_ERA_DATE)
            .era835RecordId(UPDATED_ERA_835_RECORD_ID)
            .resubmissinStatus(UPDATED_RESUBMISSIN_STATUS)
            .resubmissionDetailId(UPDATED_RESUBMISSION_DETAIL_ID)
            .resubmissionNotes(UPDATED_RESUBMISSION_NOTES)
            .voidStatus(UPDATED_VOID_STATUS)
            .voidNote(UPDATED_VOID_NOTE)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .claimSubmissionStatusUuid(UPDATED_CLAIM_SUBMISSION_STATUS_UUID);
        return claimSubmissionStatus;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(ClaimSubmissionStatus.class).block();
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
        claimSubmissionStatus = createEntity(em);
    }

    @Test
    void createClaimSubmissionStatus() throws Exception {
        int databaseSizeBeforeCreate = claimSubmissionStatusRepository.findAll().collectList().block().size();
        // Create the ClaimSubmissionStatus
        ClaimSubmissionStatusDTO claimSubmissionStatusDTO = claimSubmissionStatusMapper.toDto(claimSubmissionStatus);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(claimSubmissionStatusDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the ClaimSubmissionStatus in the database
        List<ClaimSubmissionStatus> claimSubmissionStatusList = claimSubmissionStatusRepository.findAll().collectList().block();
        assertThat(claimSubmissionStatusList).hasSize(databaseSizeBeforeCreate + 1);
        ClaimSubmissionStatus testClaimSubmissionStatus = claimSubmissionStatusList.get(claimSubmissionStatusList.size() - 1);
        assertThat(testClaimSubmissionStatus.getSalesOrderId()).isEqualTo(DEFAULT_SALES_ORDER_ID);
        assertThat(testClaimSubmissionStatus.getSalesOrderNo()).isEqualTo(DEFAULT_SALES_ORDER_NO);
        assertThat(testClaimSubmissionStatus.getInvoiceId()).isEqualTo(DEFAULT_INVOICE_ID);
        assertThat(testClaimSubmissionStatus.getInvoiceNo()).isEqualTo(DEFAULT_INVOICE_NO);
        assertThat(testClaimSubmissionStatus.getPayorLevel()).isEqualTo(DEFAULT_PAYOR_LEVEL);
        assertThat(testClaimSubmissionStatus.getPayorIdNo()).isEqualTo(DEFAULT_PAYOR_ID_NO);
        assertThat(testClaimSubmissionStatus.getClaimSubmissionDataId()).isEqualTo(DEFAULT_CLAIM_SUBMISSION_DATA_ID);
        assertThat(testClaimSubmissionStatus.getIntClaimNo()).isEqualTo(DEFAULT_INT_CLAIM_NO);
        assertThat(testClaimSubmissionStatus.getPatientAccountNo()).isEqualTo(DEFAULT_PATIENT_ACCOUNT_NO);
        assertThat(testClaimSubmissionStatus.getPayorClaimControlNo()).isEqualTo(DEFAULT_PAYOR_CLAIM_CONTROL_NO);
        assertThat(testClaimSubmissionStatus.getOriginalClaimControlNo()).isEqualTo(DEFAULT_ORIGINAL_CLAIM_CONTROL_NO);
        assertThat(testClaimSubmissionStatus.getPatientIdNo()).isEqualTo(DEFAULT_PATIENT_ID_NO);
        assertThat(testClaimSubmissionStatus.getPayor()).isEqualTo(DEFAULT_PAYOR);
        assertThat(testClaimSubmissionStatus.getClaimSubmissionDate()).isEqualTo(DEFAULT_CLAIM_SUBMISSION_DATE);
        assertThat(testClaimSubmissionStatus.getSubmissionStatus()).isEqualTo(DEFAULT_SUBMISSION_STATUS);
        assertThat(testClaimSubmissionStatus.getSubmissionNote()).isEqualTo(DEFAULT_SUBMISSION_NOTE);
        assertThat(testClaimSubmissionStatus.getResponseStatus()).isEqualTo(DEFAULT_RESPONSE_STATUS);
        assertThat(testClaimSubmissionStatus.getResponseStatusNotes()).isEqualTo(DEFAULT_RESPONSE_STATUS_NOTES);
        assertThat(testClaimSubmissionStatus.getResponseStatusDate()).isEqualTo(DEFAULT_RESPONSE_STATUS_DATE);
        assertThat(testClaimSubmissionStatus.getResponse277RecordId()).isEqualTo(DEFAULT_RESPONSE_277_RECORD_ID);
        assertThat(testClaimSubmissionStatus.getEraStatus()).isEqualTo(DEFAULT_ERA_STATUS);
        assertThat(testClaimSubmissionStatus.getEraStatusNotes()).isEqualTo(DEFAULT_ERA_STATUS_NOTES);
        assertThat(testClaimSubmissionStatus.getEraDate()).isEqualTo(DEFAULT_ERA_DATE);
        assertThat(testClaimSubmissionStatus.getEra835RecordId()).isEqualTo(DEFAULT_ERA_835_RECORD_ID);
        assertThat(testClaimSubmissionStatus.getResubmissinStatus()).isEqualTo(DEFAULT_RESUBMISSIN_STATUS);
        assertThat(testClaimSubmissionStatus.getResubmissionDetailId()).isEqualTo(DEFAULT_RESUBMISSION_DETAIL_ID);
        assertThat(testClaimSubmissionStatus.getResubmissionNotes()).isEqualTo(DEFAULT_RESUBMISSION_NOTES);
        assertThat(testClaimSubmissionStatus.getVoidStatus()).isEqualTo(DEFAULT_VOID_STATUS);
        assertThat(testClaimSubmissionStatus.getVoidNote()).isEqualTo(DEFAULT_VOID_NOTE);
        assertThat(testClaimSubmissionStatus.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testClaimSubmissionStatus.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testClaimSubmissionStatus.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testClaimSubmissionStatus.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testClaimSubmissionStatus.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testClaimSubmissionStatus.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testClaimSubmissionStatus.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testClaimSubmissionStatus.getClaimSubmissionStatusUuid()).isEqualTo(DEFAULT_CLAIM_SUBMISSION_STATUS_UUID);
    }

    @Test
    void createClaimSubmissionStatusWithExistingId() throws Exception {
        // Create the ClaimSubmissionStatus with an existing ID
        claimSubmissionStatus.setClaimStatusId(1L);
        ClaimSubmissionStatusDTO claimSubmissionStatusDTO = claimSubmissionStatusMapper.toDto(claimSubmissionStatus);

        int databaseSizeBeforeCreate = claimSubmissionStatusRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(claimSubmissionStatusDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ClaimSubmissionStatus in the database
        List<ClaimSubmissionStatus> claimSubmissionStatusList = claimSubmissionStatusRepository.findAll().collectList().block();
        assertThat(claimSubmissionStatusList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllClaimSubmissionStatuses() {
        // Initialize the database
        claimSubmissionStatusRepository.save(claimSubmissionStatus).block();

        // Get all the claimSubmissionStatusList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=claimStatusId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].claimStatusId")
            .value(hasItem(claimSubmissionStatus.getClaimStatusId().intValue()))
            .jsonPath("$.[*].salesOrderId")
            .value(hasItem(DEFAULT_SALES_ORDER_ID.intValue()))
            .jsonPath("$.[*].salesOrderNo")
            .value(hasItem(DEFAULT_SALES_ORDER_NO))
            .jsonPath("$.[*].invoiceId")
            .value(hasItem(DEFAULT_INVOICE_ID.intValue()))
            .jsonPath("$.[*].invoiceNo")
            .value(hasItem(DEFAULT_INVOICE_NO))
            .jsonPath("$.[*].payorLevel")
            .value(hasItem(DEFAULT_PAYOR_LEVEL))
            .jsonPath("$.[*].payorIdNo")
            .value(hasItem(DEFAULT_PAYOR_ID_NO))
            .jsonPath("$.[*].claimSubmissionDataId")
            .value(hasItem(DEFAULT_CLAIM_SUBMISSION_DATA_ID.intValue()))
            .jsonPath("$.[*].intClaimNo")
            .value(hasItem(DEFAULT_INT_CLAIM_NO))
            .jsonPath("$.[*].patientAccountNo")
            .value(hasItem(DEFAULT_PATIENT_ACCOUNT_NO))
            .jsonPath("$.[*].payorClaimControlNo")
            .value(hasItem(DEFAULT_PAYOR_CLAIM_CONTROL_NO))
            .jsonPath("$.[*].originalClaimControlNo")
            .value(hasItem(DEFAULT_ORIGINAL_CLAIM_CONTROL_NO))
            .jsonPath("$.[*].patientIdNo")
            .value(hasItem(DEFAULT_PATIENT_ID_NO))
            .jsonPath("$.[*].payor")
            .value(hasItem(DEFAULT_PAYOR))
            .jsonPath("$.[*].claimSubmissionDate")
            .value(hasItem(DEFAULT_CLAIM_SUBMISSION_DATE.toString()))
            .jsonPath("$.[*].submissionStatus")
            .value(hasItem(DEFAULT_SUBMISSION_STATUS))
            .jsonPath("$.[*].submissionNote")
            .value(hasItem(DEFAULT_SUBMISSION_NOTE))
            .jsonPath("$.[*].responseStatus")
            .value(hasItem(DEFAULT_RESPONSE_STATUS))
            .jsonPath("$.[*].responseStatusNotes")
            .value(hasItem(DEFAULT_RESPONSE_STATUS_NOTES))
            .jsonPath("$.[*].responseStatusDate")
            .value(hasItem(DEFAULT_RESPONSE_STATUS_DATE.toString()))
            .jsonPath("$.[*].response277RecordId")
            .value(hasItem(DEFAULT_RESPONSE_277_RECORD_ID.intValue()))
            .jsonPath("$.[*].eraStatus")
            .value(hasItem(DEFAULT_ERA_STATUS))
            .jsonPath("$.[*].eraStatusNotes")
            .value(hasItem(DEFAULT_ERA_STATUS_NOTES))
            .jsonPath("$.[*].eraDate")
            .value(hasItem(DEFAULT_ERA_DATE.toString()))
            .jsonPath("$.[*].era835RecordId")
            .value(hasItem(DEFAULT_ERA_835_RECORD_ID.intValue()))
            .jsonPath("$.[*].resubmissinStatus")
            .value(hasItem(DEFAULT_RESUBMISSIN_STATUS))
            .jsonPath("$.[*].resubmissionDetailId")
            .value(hasItem(DEFAULT_RESUBMISSION_DETAIL_ID.intValue()))
            .jsonPath("$.[*].resubmissionNotes")
            .value(hasItem(DEFAULT_RESUBMISSION_NOTES))
            .jsonPath("$.[*].voidStatus")
            .value(hasItem(DEFAULT_VOID_STATUS))
            .jsonPath("$.[*].voidNote")
            .value(hasItem(DEFAULT_VOID_NOTE))
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
            .jsonPath("$.[*].claimSubmissionStatusUuid")
            .value(hasItem(DEFAULT_CLAIM_SUBMISSION_STATUS_UUID.toString()));
    }

    @Test
    void getClaimSubmissionStatus() {
        // Initialize the database
        claimSubmissionStatusRepository.save(claimSubmissionStatus).block();

        // Get the claimSubmissionStatus
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, claimSubmissionStatus.getClaimStatusId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.claimStatusId")
            .value(is(claimSubmissionStatus.getClaimStatusId().intValue()))
            .jsonPath("$.salesOrderId")
            .value(is(DEFAULT_SALES_ORDER_ID.intValue()))
            .jsonPath("$.salesOrderNo")
            .value(is(DEFAULT_SALES_ORDER_NO))
            .jsonPath("$.invoiceId")
            .value(is(DEFAULT_INVOICE_ID.intValue()))
            .jsonPath("$.invoiceNo")
            .value(is(DEFAULT_INVOICE_NO))
            .jsonPath("$.payorLevel")
            .value(is(DEFAULT_PAYOR_LEVEL))
            .jsonPath("$.payorIdNo")
            .value(is(DEFAULT_PAYOR_ID_NO))
            .jsonPath("$.claimSubmissionDataId")
            .value(is(DEFAULT_CLAIM_SUBMISSION_DATA_ID.intValue()))
            .jsonPath("$.intClaimNo")
            .value(is(DEFAULT_INT_CLAIM_NO))
            .jsonPath("$.patientAccountNo")
            .value(is(DEFAULT_PATIENT_ACCOUNT_NO))
            .jsonPath("$.payorClaimControlNo")
            .value(is(DEFAULT_PAYOR_CLAIM_CONTROL_NO))
            .jsonPath("$.originalClaimControlNo")
            .value(is(DEFAULT_ORIGINAL_CLAIM_CONTROL_NO))
            .jsonPath("$.patientIdNo")
            .value(is(DEFAULT_PATIENT_ID_NO))
            .jsonPath("$.payor")
            .value(is(DEFAULT_PAYOR))
            .jsonPath("$.claimSubmissionDate")
            .value(is(DEFAULT_CLAIM_SUBMISSION_DATE.toString()))
            .jsonPath("$.submissionStatus")
            .value(is(DEFAULT_SUBMISSION_STATUS))
            .jsonPath("$.submissionNote")
            .value(is(DEFAULT_SUBMISSION_NOTE))
            .jsonPath("$.responseStatus")
            .value(is(DEFAULT_RESPONSE_STATUS))
            .jsonPath("$.responseStatusNotes")
            .value(is(DEFAULT_RESPONSE_STATUS_NOTES))
            .jsonPath("$.responseStatusDate")
            .value(is(DEFAULT_RESPONSE_STATUS_DATE.toString()))
            .jsonPath("$.response277RecordId")
            .value(is(DEFAULT_RESPONSE_277_RECORD_ID.intValue()))
            .jsonPath("$.eraStatus")
            .value(is(DEFAULT_ERA_STATUS))
            .jsonPath("$.eraStatusNotes")
            .value(is(DEFAULT_ERA_STATUS_NOTES))
            .jsonPath("$.eraDate")
            .value(is(DEFAULT_ERA_DATE.toString()))
            .jsonPath("$.era835RecordId")
            .value(is(DEFAULT_ERA_835_RECORD_ID.intValue()))
            .jsonPath("$.resubmissinStatus")
            .value(is(DEFAULT_RESUBMISSIN_STATUS))
            .jsonPath("$.resubmissionDetailId")
            .value(is(DEFAULT_RESUBMISSION_DETAIL_ID.intValue()))
            .jsonPath("$.resubmissionNotes")
            .value(is(DEFAULT_RESUBMISSION_NOTES))
            .jsonPath("$.voidStatus")
            .value(is(DEFAULT_VOID_STATUS))
            .jsonPath("$.voidNote")
            .value(is(DEFAULT_VOID_NOTE))
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
            .jsonPath("$.claimSubmissionStatusUuid")
            .value(is(DEFAULT_CLAIM_SUBMISSION_STATUS_UUID.toString()));
    }

    @Test
    void getNonExistingClaimSubmissionStatus() {
        // Get the claimSubmissionStatus
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putNewClaimSubmissionStatus() throws Exception {
        // Initialize the database
        claimSubmissionStatusRepository.save(claimSubmissionStatus).block();

        int databaseSizeBeforeUpdate = claimSubmissionStatusRepository.findAll().collectList().block().size();

        // Update the claimSubmissionStatus
        ClaimSubmissionStatus updatedClaimSubmissionStatus = claimSubmissionStatusRepository
            .findById(claimSubmissionStatus.getClaimStatusId())
            .block();
        updatedClaimSubmissionStatus
            .salesOrderId(UPDATED_SALES_ORDER_ID)
            .salesOrderNo(UPDATED_SALES_ORDER_NO)
            .invoiceId(UPDATED_INVOICE_ID)
            .invoiceNo(UPDATED_INVOICE_NO)
            .payorLevel(UPDATED_PAYOR_LEVEL)
            .payorIdNo(UPDATED_PAYOR_ID_NO)
            .claimSubmissionDataId(UPDATED_CLAIM_SUBMISSION_DATA_ID)
            .intClaimNo(UPDATED_INT_CLAIM_NO)
            .patientAccountNo(UPDATED_PATIENT_ACCOUNT_NO)
            .payorClaimControlNo(UPDATED_PAYOR_CLAIM_CONTROL_NO)
            .originalClaimControlNo(UPDATED_ORIGINAL_CLAIM_CONTROL_NO)
            .patientIdNo(UPDATED_PATIENT_ID_NO)
            .payor(UPDATED_PAYOR)
            .claimSubmissionDate(UPDATED_CLAIM_SUBMISSION_DATE)
            .submissionStatus(UPDATED_SUBMISSION_STATUS)
            .submissionNote(UPDATED_SUBMISSION_NOTE)
            .responseStatus(UPDATED_RESPONSE_STATUS)
            .responseStatusNotes(UPDATED_RESPONSE_STATUS_NOTES)
            .responseStatusDate(UPDATED_RESPONSE_STATUS_DATE)
            .response277RecordId(UPDATED_RESPONSE_277_RECORD_ID)
            .eraStatus(UPDATED_ERA_STATUS)
            .eraStatusNotes(UPDATED_ERA_STATUS_NOTES)
            .eraDate(UPDATED_ERA_DATE)
            .era835RecordId(UPDATED_ERA_835_RECORD_ID)
            .resubmissinStatus(UPDATED_RESUBMISSIN_STATUS)
            .resubmissionDetailId(UPDATED_RESUBMISSION_DETAIL_ID)
            .resubmissionNotes(UPDATED_RESUBMISSION_NOTES)
            .voidStatus(UPDATED_VOID_STATUS)
            .voidNote(UPDATED_VOID_NOTE)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .claimSubmissionStatusUuid(UPDATED_CLAIM_SUBMISSION_STATUS_UUID);
        ClaimSubmissionStatusDTO claimSubmissionStatusDTO = claimSubmissionStatusMapper.toDto(updatedClaimSubmissionStatus);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, claimSubmissionStatusDTO.getClaimStatusId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(claimSubmissionStatusDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ClaimSubmissionStatus in the database
        List<ClaimSubmissionStatus> claimSubmissionStatusList = claimSubmissionStatusRepository.findAll().collectList().block();
        assertThat(claimSubmissionStatusList).hasSize(databaseSizeBeforeUpdate);
        ClaimSubmissionStatus testClaimSubmissionStatus = claimSubmissionStatusList.get(claimSubmissionStatusList.size() - 1);
        assertThat(testClaimSubmissionStatus.getSalesOrderId()).isEqualTo(UPDATED_SALES_ORDER_ID);
        assertThat(testClaimSubmissionStatus.getSalesOrderNo()).isEqualTo(UPDATED_SALES_ORDER_NO);
        assertThat(testClaimSubmissionStatus.getInvoiceId()).isEqualTo(UPDATED_INVOICE_ID);
        assertThat(testClaimSubmissionStatus.getInvoiceNo()).isEqualTo(UPDATED_INVOICE_NO);
        assertThat(testClaimSubmissionStatus.getPayorLevel()).isEqualTo(UPDATED_PAYOR_LEVEL);
        assertThat(testClaimSubmissionStatus.getPayorIdNo()).isEqualTo(UPDATED_PAYOR_ID_NO);
        assertThat(testClaimSubmissionStatus.getClaimSubmissionDataId()).isEqualTo(UPDATED_CLAIM_SUBMISSION_DATA_ID);
        assertThat(testClaimSubmissionStatus.getIntClaimNo()).isEqualTo(UPDATED_INT_CLAIM_NO);
        assertThat(testClaimSubmissionStatus.getPatientAccountNo()).isEqualTo(UPDATED_PATIENT_ACCOUNT_NO);
        assertThat(testClaimSubmissionStatus.getPayorClaimControlNo()).isEqualTo(UPDATED_PAYOR_CLAIM_CONTROL_NO);
        assertThat(testClaimSubmissionStatus.getOriginalClaimControlNo()).isEqualTo(UPDATED_ORIGINAL_CLAIM_CONTROL_NO);
        assertThat(testClaimSubmissionStatus.getPatientIdNo()).isEqualTo(UPDATED_PATIENT_ID_NO);
        assertThat(testClaimSubmissionStatus.getPayor()).isEqualTo(UPDATED_PAYOR);
        assertThat(testClaimSubmissionStatus.getClaimSubmissionDate()).isEqualTo(UPDATED_CLAIM_SUBMISSION_DATE);
        assertThat(testClaimSubmissionStatus.getSubmissionStatus()).isEqualTo(UPDATED_SUBMISSION_STATUS);
        assertThat(testClaimSubmissionStatus.getSubmissionNote()).isEqualTo(UPDATED_SUBMISSION_NOTE);
        assertThat(testClaimSubmissionStatus.getResponseStatus()).isEqualTo(UPDATED_RESPONSE_STATUS);
        assertThat(testClaimSubmissionStatus.getResponseStatusNotes()).isEqualTo(UPDATED_RESPONSE_STATUS_NOTES);
        assertThat(testClaimSubmissionStatus.getResponseStatusDate()).isEqualTo(UPDATED_RESPONSE_STATUS_DATE);
        assertThat(testClaimSubmissionStatus.getResponse277RecordId()).isEqualTo(UPDATED_RESPONSE_277_RECORD_ID);
        assertThat(testClaimSubmissionStatus.getEraStatus()).isEqualTo(UPDATED_ERA_STATUS);
        assertThat(testClaimSubmissionStatus.getEraStatusNotes()).isEqualTo(UPDATED_ERA_STATUS_NOTES);
        assertThat(testClaimSubmissionStatus.getEraDate()).isEqualTo(UPDATED_ERA_DATE);
        assertThat(testClaimSubmissionStatus.getEra835RecordId()).isEqualTo(UPDATED_ERA_835_RECORD_ID);
        assertThat(testClaimSubmissionStatus.getResubmissinStatus()).isEqualTo(UPDATED_RESUBMISSIN_STATUS);
        assertThat(testClaimSubmissionStatus.getResubmissionDetailId()).isEqualTo(UPDATED_RESUBMISSION_DETAIL_ID);
        assertThat(testClaimSubmissionStatus.getResubmissionNotes()).isEqualTo(UPDATED_RESUBMISSION_NOTES);
        assertThat(testClaimSubmissionStatus.getVoidStatus()).isEqualTo(UPDATED_VOID_STATUS);
        assertThat(testClaimSubmissionStatus.getVoidNote()).isEqualTo(UPDATED_VOID_NOTE);
        assertThat(testClaimSubmissionStatus.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testClaimSubmissionStatus.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testClaimSubmissionStatus.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testClaimSubmissionStatus.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testClaimSubmissionStatus.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testClaimSubmissionStatus.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testClaimSubmissionStatus.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testClaimSubmissionStatus.getClaimSubmissionStatusUuid()).isEqualTo(UPDATED_CLAIM_SUBMISSION_STATUS_UUID);
    }

    @Test
    void putNonExistingClaimSubmissionStatus() throws Exception {
        int databaseSizeBeforeUpdate = claimSubmissionStatusRepository.findAll().collectList().block().size();
        claimSubmissionStatus.setClaimStatusId(count.incrementAndGet());

        // Create the ClaimSubmissionStatus
        ClaimSubmissionStatusDTO claimSubmissionStatusDTO = claimSubmissionStatusMapper.toDto(claimSubmissionStatus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, claimSubmissionStatusDTO.getClaimStatusId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(claimSubmissionStatusDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ClaimSubmissionStatus in the database
        List<ClaimSubmissionStatus> claimSubmissionStatusList = claimSubmissionStatusRepository.findAll().collectList().block();
        assertThat(claimSubmissionStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchClaimSubmissionStatus() throws Exception {
        int databaseSizeBeforeUpdate = claimSubmissionStatusRepository.findAll().collectList().block().size();
        claimSubmissionStatus.setClaimStatusId(count.incrementAndGet());

        // Create the ClaimSubmissionStatus
        ClaimSubmissionStatusDTO claimSubmissionStatusDTO = claimSubmissionStatusMapper.toDto(claimSubmissionStatus);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(claimSubmissionStatusDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ClaimSubmissionStatus in the database
        List<ClaimSubmissionStatus> claimSubmissionStatusList = claimSubmissionStatusRepository.findAll().collectList().block();
        assertThat(claimSubmissionStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamClaimSubmissionStatus() throws Exception {
        int databaseSizeBeforeUpdate = claimSubmissionStatusRepository.findAll().collectList().block().size();
        claimSubmissionStatus.setClaimStatusId(count.incrementAndGet());

        // Create the ClaimSubmissionStatus
        ClaimSubmissionStatusDTO claimSubmissionStatusDTO = claimSubmissionStatusMapper.toDto(claimSubmissionStatus);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(claimSubmissionStatusDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the ClaimSubmissionStatus in the database
        List<ClaimSubmissionStatus> claimSubmissionStatusList = claimSubmissionStatusRepository.findAll().collectList().block();
        assertThat(claimSubmissionStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateClaimSubmissionStatusWithPatch() throws Exception {
        // Initialize the database
        claimSubmissionStatusRepository.save(claimSubmissionStatus).block();

        int databaseSizeBeforeUpdate = claimSubmissionStatusRepository.findAll().collectList().block().size();

        // Update the claimSubmissionStatus using partial update
        ClaimSubmissionStatus partialUpdatedClaimSubmissionStatus = new ClaimSubmissionStatus();
        partialUpdatedClaimSubmissionStatus.setClaimStatusId(claimSubmissionStatus.getClaimStatusId());

        partialUpdatedClaimSubmissionStatus
            .invoiceId(UPDATED_INVOICE_ID)
            .payorLevel(UPDATED_PAYOR_LEVEL)
            .payorClaimControlNo(UPDATED_PAYOR_CLAIM_CONTROL_NO)
            .patientIdNo(UPDATED_PATIENT_ID_NO)
            .payor(UPDATED_PAYOR)
            .claimSubmissionDate(UPDATED_CLAIM_SUBMISSION_DATE)
            .responseStatus(UPDATED_RESPONSE_STATUS)
            .responseStatusNotes(UPDATED_RESPONSE_STATUS_NOTES)
            .responseStatusDate(UPDATED_RESPONSE_STATUS_DATE)
            .eraStatus(UPDATED_ERA_STATUS)
            .resubmissinStatus(UPDATED_RESUBMISSIN_STATUS)
            .resubmissionDetailId(UPDATED_RESUBMISSION_DETAIL_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedClaimSubmissionStatus.getClaimStatusId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedClaimSubmissionStatus))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ClaimSubmissionStatus in the database
        List<ClaimSubmissionStatus> claimSubmissionStatusList = claimSubmissionStatusRepository.findAll().collectList().block();
        assertThat(claimSubmissionStatusList).hasSize(databaseSizeBeforeUpdate);
        ClaimSubmissionStatus testClaimSubmissionStatus = claimSubmissionStatusList.get(claimSubmissionStatusList.size() - 1);
        assertThat(testClaimSubmissionStatus.getSalesOrderId()).isEqualTo(DEFAULT_SALES_ORDER_ID);
        assertThat(testClaimSubmissionStatus.getSalesOrderNo()).isEqualTo(DEFAULT_SALES_ORDER_NO);
        assertThat(testClaimSubmissionStatus.getInvoiceId()).isEqualTo(UPDATED_INVOICE_ID);
        assertThat(testClaimSubmissionStatus.getInvoiceNo()).isEqualTo(DEFAULT_INVOICE_NO);
        assertThat(testClaimSubmissionStatus.getPayorLevel()).isEqualTo(UPDATED_PAYOR_LEVEL);
        assertThat(testClaimSubmissionStatus.getPayorIdNo()).isEqualTo(DEFAULT_PAYOR_ID_NO);
        assertThat(testClaimSubmissionStatus.getClaimSubmissionDataId()).isEqualTo(DEFAULT_CLAIM_SUBMISSION_DATA_ID);
        assertThat(testClaimSubmissionStatus.getIntClaimNo()).isEqualTo(DEFAULT_INT_CLAIM_NO);
        assertThat(testClaimSubmissionStatus.getPatientAccountNo()).isEqualTo(DEFAULT_PATIENT_ACCOUNT_NO);
        assertThat(testClaimSubmissionStatus.getPayorClaimControlNo()).isEqualTo(UPDATED_PAYOR_CLAIM_CONTROL_NO);
        assertThat(testClaimSubmissionStatus.getOriginalClaimControlNo()).isEqualTo(DEFAULT_ORIGINAL_CLAIM_CONTROL_NO);
        assertThat(testClaimSubmissionStatus.getPatientIdNo()).isEqualTo(UPDATED_PATIENT_ID_NO);
        assertThat(testClaimSubmissionStatus.getPayor()).isEqualTo(UPDATED_PAYOR);
        assertThat(testClaimSubmissionStatus.getClaimSubmissionDate()).isEqualTo(UPDATED_CLAIM_SUBMISSION_DATE);
        assertThat(testClaimSubmissionStatus.getSubmissionStatus()).isEqualTo(DEFAULT_SUBMISSION_STATUS);
        assertThat(testClaimSubmissionStatus.getSubmissionNote()).isEqualTo(DEFAULT_SUBMISSION_NOTE);
        assertThat(testClaimSubmissionStatus.getResponseStatus()).isEqualTo(UPDATED_RESPONSE_STATUS);
        assertThat(testClaimSubmissionStatus.getResponseStatusNotes()).isEqualTo(UPDATED_RESPONSE_STATUS_NOTES);
        assertThat(testClaimSubmissionStatus.getResponseStatusDate()).isEqualTo(UPDATED_RESPONSE_STATUS_DATE);
        assertThat(testClaimSubmissionStatus.getResponse277RecordId()).isEqualTo(DEFAULT_RESPONSE_277_RECORD_ID);
        assertThat(testClaimSubmissionStatus.getEraStatus()).isEqualTo(UPDATED_ERA_STATUS);
        assertThat(testClaimSubmissionStatus.getEraStatusNotes()).isEqualTo(DEFAULT_ERA_STATUS_NOTES);
        assertThat(testClaimSubmissionStatus.getEraDate()).isEqualTo(DEFAULT_ERA_DATE);
        assertThat(testClaimSubmissionStatus.getEra835RecordId()).isEqualTo(DEFAULT_ERA_835_RECORD_ID);
        assertThat(testClaimSubmissionStatus.getResubmissinStatus()).isEqualTo(UPDATED_RESUBMISSIN_STATUS);
        assertThat(testClaimSubmissionStatus.getResubmissionDetailId()).isEqualTo(UPDATED_RESUBMISSION_DETAIL_ID);
        assertThat(testClaimSubmissionStatus.getResubmissionNotes()).isEqualTo(DEFAULT_RESUBMISSION_NOTES);
        assertThat(testClaimSubmissionStatus.getVoidStatus()).isEqualTo(DEFAULT_VOID_STATUS);
        assertThat(testClaimSubmissionStatus.getVoidNote()).isEqualTo(DEFAULT_VOID_NOTE);
        assertThat(testClaimSubmissionStatus.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testClaimSubmissionStatus.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testClaimSubmissionStatus.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testClaimSubmissionStatus.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testClaimSubmissionStatus.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testClaimSubmissionStatus.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testClaimSubmissionStatus.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testClaimSubmissionStatus.getClaimSubmissionStatusUuid()).isEqualTo(DEFAULT_CLAIM_SUBMISSION_STATUS_UUID);
    }

    @Test
    void fullUpdateClaimSubmissionStatusWithPatch() throws Exception {
        // Initialize the database
        claimSubmissionStatusRepository.save(claimSubmissionStatus).block();

        int databaseSizeBeforeUpdate = claimSubmissionStatusRepository.findAll().collectList().block().size();

        // Update the claimSubmissionStatus using partial update
        ClaimSubmissionStatus partialUpdatedClaimSubmissionStatus = new ClaimSubmissionStatus();
        partialUpdatedClaimSubmissionStatus.setClaimStatusId(claimSubmissionStatus.getClaimStatusId());

        partialUpdatedClaimSubmissionStatus
            .salesOrderId(UPDATED_SALES_ORDER_ID)
            .salesOrderNo(UPDATED_SALES_ORDER_NO)
            .invoiceId(UPDATED_INVOICE_ID)
            .invoiceNo(UPDATED_INVOICE_NO)
            .payorLevel(UPDATED_PAYOR_LEVEL)
            .payorIdNo(UPDATED_PAYOR_ID_NO)
            .claimSubmissionDataId(UPDATED_CLAIM_SUBMISSION_DATA_ID)
            .intClaimNo(UPDATED_INT_CLAIM_NO)
            .patientAccountNo(UPDATED_PATIENT_ACCOUNT_NO)
            .payorClaimControlNo(UPDATED_PAYOR_CLAIM_CONTROL_NO)
            .originalClaimControlNo(UPDATED_ORIGINAL_CLAIM_CONTROL_NO)
            .patientIdNo(UPDATED_PATIENT_ID_NO)
            .payor(UPDATED_PAYOR)
            .claimSubmissionDate(UPDATED_CLAIM_SUBMISSION_DATE)
            .submissionStatus(UPDATED_SUBMISSION_STATUS)
            .submissionNote(UPDATED_SUBMISSION_NOTE)
            .responseStatus(UPDATED_RESPONSE_STATUS)
            .responseStatusNotes(UPDATED_RESPONSE_STATUS_NOTES)
            .responseStatusDate(UPDATED_RESPONSE_STATUS_DATE)
            .response277RecordId(UPDATED_RESPONSE_277_RECORD_ID)
            .eraStatus(UPDATED_ERA_STATUS)
            .eraStatusNotes(UPDATED_ERA_STATUS_NOTES)
            .eraDate(UPDATED_ERA_DATE)
            .era835RecordId(UPDATED_ERA_835_RECORD_ID)
            .resubmissinStatus(UPDATED_RESUBMISSIN_STATUS)
            .resubmissionDetailId(UPDATED_RESUBMISSION_DETAIL_ID)
            .resubmissionNotes(UPDATED_RESUBMISSION_NOTES)
            .voidStatus(UPDATED_VOID_STATUS)
            .voidNote(UPDATED_VOID_NOTE)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .claimSubmissionStatusUuid(UPDATED_CLAIM_SUBMISSION_STATUS_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedClaimSubmissionStatus.getClaimStatusId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedClaimSubmissionStatus))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ClaimSubmissionStatus in the database
        List<ClaimSubmissionStatus> claimSubmissionStatusList = claimSubmissionStatusRepository.findAll().collectList().block();
        assertThat(claimSubmissionStatusList).hasSize(databaseSizeBeforeUpdate);
        ClaimSubmissionStatus testClaimSubmissionStatus = claimSubmissionStatusList.get(claimSubmissionStatusList.size() - 1);
        assertThat(testClaimSubmissionStatus.getSalesOrderId()).isEqualTo(UPDATED_SALES_ORDER_ID);
        assertThat(testClaimSubmissionStatus.getSalesOrderNo()).isEqualTo(UPDATED_SALES_ORDER_NO);
        assertThat(testClaimSubmissionStatus.getInvoiceId()).isEqualTo(UPDATED_INVOICE_ID);
        assertThat(testClaimSubmissionStatus.getInvoiceNo()).isEqualTo(UPDATED_INVOICE_NO);
        assertThat(testClaimSubmissionStatus.getPayorLevel()).isEqualTo(UPDATED_PAYOR_LEVEL);
        assertThat(testClaimSubmissionStatus.getPayorIdNo()).isEqualTo(UPDATED_PAYOR_ID_NO);
        assertThat(testClaimSubmissionStatus.getClaimSubmissionDataId()).isEqualTo(UPDATED_CLAIM_SUBMISSION_DATA_ID);
        assertThat(testClaimSubmissionStatus.getIntClaimNo()).isEqualTo(UPDATED_INT_CLAIM_NO);
        assertThat(testClaimSubmissionStatus.getPatientAccountNo()).isEqualTo(UPDATED_PATIENT_ACCOUNT_NO);
        assertThat(testClaimSubmissionStatus.getPayorClaimControlNo()).isEqualTo(UPDATED_PAYOR_CLAIM_CONTROL_NO);
        assertThat(testClaimSubmissionStatus.getOriginalClaimControlNo()).isEqualTo(UPDATED_ORIGINAL_CLAIM_CONTROL_NO);
        assertThat(testClaimSubmissionStatus.getPatientIdNo()).isEqualTo(UPDATED_PATIENT_ID_NO);
        assertThat(testClaimSubmissionStatus.getPayor()).isEqualTo(UPDATED_PAYOR);
        assertThat(testClaimSubmissionStatus.getClaimSubmissionDate()).isEqualTo(UPDATED_CLAIM_SUBMISSION_DATE);
        assertThat(testClaimSubmissionStatus.getSubmissionStatus()).isEqualTo(UPDATED_SUBMISSION_STATUS);
        assertThat(testClaimSubmissionStatus.getSubmissionNote()).isEqualTo(UPDATED_SUBMISSION_NOTE);
        assertThat(testClaimSubmissionStatus.getResponseStatus()).isEqualTo(UPDATED_RESPONSE_STATUS);
        assertThat(testClaimSubmissionStatus.getResponseStatusNotes()).isEqualTo(UPDATED_RESPONSE_STATUS_NOTES);
        assertThat(testClaimSubmissionStatus.getResponseStatusDate()).isEqualTo(UPDATED_RESPONSE_STATUS_DATE);
        assertThat(testClaimSubmissionStatus.getResponse277RecordId()).isEqualTo(UPDATED_RESPONSE_277_RECORD_ID);
        assertThat(testClaimSubmissionStatus.getEraStatus()).isEqualTo(UPDATED_ERA_STATUS);
        assertThat(testClaimSubmissionStatus.getEraStatusNotes()).isEqualTo(UPDATED_ERA_STATUS_NOTES);
        assertThat(testClaimSubmissionStatus.getEraDate()).isEqualTo(UPDATED_ERA_DATE);
        assertThat(testClaimSubmissionStatus.getEra835RecordId()).isEqualTo(UPDATED_ERA_835_RECORD_ID);
        assertThat(testClaimSubmissionStatus.getResubmissinStatus()).isEqualTo(UPDATED_RESUBMISSIN_STATUS);
        assertThat(testClaimSubmissionStatus.getResubmissionDetailId()).isEqualTo(UPDATED_RESUBMISSION_DETAIL_ID);
        assertThat(testClaimSubmissionStatus.getResubmissionNotes()).isEqualTo(UPDATED_RESUBMISSION_NOTES);
        assertThat(testClaimSubmissionStatus.getVoidStatus()).isEqualTo(UPDATED_VOID_STATUS);
        assertThat(testClaimSubmissionStatus.getVoidNote()).isEqualTo(UPDATED_VOID_NOTE);
        assertThat(testClaimSubmissionStatus.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testClaimSubmissionStatus.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testClaimSubmissionStatus.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testClaimSubmissionStatus.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testClaimSubmissionStatus.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testClaimSubmissionStatus.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testClaimSubmissionStatus.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testClaimSubmissionStatus.getClaimSubmissionStatusUuid()).isEqualTo(UPDATED_CLAIM_SUBMISSION_STATUS_UUID);
    }

    @Test
    void patchNonExistingClaimSubmissionStatus() throws Exception {
        int databaseSizeBeforeUpdate = claimSubmissionStatusRepository.findAll().collectList().block().size();
        claimSubmissionStatus.setClaimStatusId(count.incrementAndGet());

        // Create the ClaimSubmissionStatus
        ClaimSubmissionStatusDTO claimSubmissionStatusDTO = claimSubmissionStatusMapper.toDto(claimSubmissionStatus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, claimSubmissionStatusDTO.getClaimStatusId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(claimSubmissionStatusDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ClaimSubmissionStatus in the database
        List<ClaimSubmissionStatus> claimSubmissionStatusList = claimSubmissionStatusRepository.findAll().collectList().block();
        assertThat(claimSubmissionStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchClaimSubmissionStatus() throws Exception {
        int databaseSizeBeforeUpdate = claimSubmissionStatusRepository.findAll().collectList().block().size();
        claimSubmissionStatus.setClaimStatusId(count.incrementAndGet());

        // Create the ClaimSubmissionStatus
        ClaimSubmissionStatusDTO claimSubmissionStatusDTO = claimSubmissionStatusMapper.toDto(claimSubmissionStatus);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(claimSubmissionStatusDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ClaimSubmissionStatus in the database
        List<ClaimSubmissionStatus> claimSubmissionStatusList = claimSubmissionStatusRepository.findAll().collectList().block();
        assertThat(claimSubmissionStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamClaimSubmissionStatus() throws Exception {
        int databaseSizeBeforeUpdate = claimSubmissionStatusRepository.findAll().collectList().block().size();
        claimSubmissionStatus.setClaimStatusId(count.incrementAndGet());

        // Create the ClaimSubmissionStatus
        ClaimSubmissionStatusDTO claimSubmissionStatusDTO = claimSubmissionStatusMapper.toDto(claimSubmissionStatus);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(claimSubmissionStatusDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the ClaimSubmissionStatus in the database
        List<ClaimSubmissionStatus> claimSubmissionStatusList = claimSubmissionStatusRepository.findAll().collectList().block();
        assertThat(claimSubmissionStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteClaimSubmissionStatus() {
        // Initialize the database
        claimSubmissionStatusRepository.save(claimSubmissionStatus).block();

        int databaseSizeBeforeDelete = claimSubmissionStatusRepository.findAll().collectList().block().size();

        // Delete the claimSubmissionStatus
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, claimSubmissionStatus.getClaimStatusId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<ClaimSubmissionStatus> claimSubmissionStatusList = claimSubmissionStatusRepository.findAll().collectList().block();
        assertThat(claimSubmissionStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
