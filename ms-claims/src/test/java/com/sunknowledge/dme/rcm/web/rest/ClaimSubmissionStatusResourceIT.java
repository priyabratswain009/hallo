package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ClaimSubmissionStatus;
import com.sunknowledge.dme.rcm.repository.ClaimSubmissionStatusRepository;
import com.sunknowledge.dme.rcm.service.dto.ClaimSubmissionStatusDTO;
import com.sunknowledge.dme.rcm.service.mapper.ClaimSubmissionStatusMapper;
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
 * Integration tests for the {@link ClaimSubmissionStatusResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
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

    private static final String DEFAULT_ERA_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_ERA_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_ERA_STATUS_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_ERA_STATUS_NOTES = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_ERA_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ERA_DATE = LocalDate.now(ZoneId.systemDefault());

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

    private static final UUID DEFAULT_CLAIM_SUBMISSION_STATUS_UUID = UUID.randomUUID();
    private static final UUID UPDATED_CLAIM_SUBMISSION_STATUS_UUID = UUID.randomUUID();

    private static final Long DEFAULT_CLAIM_COB_835_MASTER_ID = 1L;
    private static final Long UPDATED_CLAIM_COB_835_MASTER_ID = 2L;

    private static final Long DEFAULT_CLAIM_STATUS_277_MASTER_ID = 1L;
    private static final Long UPDATED_CLAIM_STATUS_277_MASTER_ID = 2L;

    private static final String DEFAULT_PAYOR_ID = "AAAAAAAAAA";
    private static final String UPDATED_PAYOR_ID = "BBBBBBBBBB";

    private static final String DEFAULT_READY_FOR_SUBMISSION_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_READY_FOR_SUBMISSION_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_PERIOD_NO = "AAAAAAAAAA";
    private static final String UPDATED_PERIOD_NO = "BBBBBBBBBB";

    private static final Long DEFAULT_BRANCH_ID = 1L;
    private static final Long UPDATED_BRANCH_ID = 2L;

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
    private MockMvc restClaimSubmissionStatusMockMvc;

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
            .eraStatus(DEFAULT_ERA_STATUS)
            .eraStatusNotes(DEFAULT_ERA_STATUS_NOTES)
            .eraDate(DEFAULT_ERA_DATE)
            .resubmissinStatus(DEFAULT_RESUBMISSIN_STATUS)
            .resubmissionDetailId(DEFAULT_RESUBMISSION_DETAIL_ID)
            .resubmissionNotes(DEFAULT_RESUBMISSION_NOTES)
            .voidStatus(DEFAULT_VOID_STATUS)
            .voidNote(DEFAULT_VOID_NOTE)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .claimSubmissionStatusUuid(DEFAULT_CLAIM_SUBMISSION_STATUS_UUID)
            .claimCob835MasterId(DEFAULT_CLAIM_COB_835_MASTER_ID)
            .claimStatus277MasterId(DEFAULT_CLAIM_STATUS_277_MASTER_ID)
            .payorId(DEFAULT_PAYOR_ID)
            .readyForSubmissionStatus(DEFAULT_READY_FOR_SUBMISSION_STATUS)
            .periodNo(DEFAULT_PERIOD_NO)
            .branchId(DEFAULT_BRANCH_ID);
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
            .eraStatus(UPDATED_ERA_STATUS)
            .eraStatusNotes(UPDATED_ERA_STATUS_NOTES)
            .eraDate(UPDATED_ERA_DATE)
            .resubmissinStatus(UPDATED_RESUBMISSIN_STATUS)
            .resubmissionDetailId(UPDATED_RESUBMISSION_DETAIL_ID)
            .resubmissionNotes(UPDATED_RESUBMISSION_NOTES)
            .voidStatus(UPDATED_VOID_STATUS)
            .voidNote(UPDATED_VOID_NOTE)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .claimSubmissionStatusUuid(UPDATED_CLAIM_SUBMISSION_STATUS_UUID)
            .claimCob835MasterId(UPDATED_CLAIM_COB_835_MASTER_ID)
            .claimStatus277MasterId(UPDATED_CLAIM_STATUS_277_MASTER_ID)
            .payorId(UPDATED_PAYOR_ID)
            .readyForSubmissionStatus(UPDATED_READY_FOR_SUBMISSION_STATUS)
            .periodNo(UPDATED_PERIOD_NO)
            .branchId(UPDATED_BRANCH_ID);
        return claimSubmissionStatus;
    }

    @BeforeEach
    public void initTest() {
        claimSubmissionStatus = createEntity(em);
    }

    @Test
    @Transactional
    void createClaimSubmissionStatus() throws Exception {
        int databaseSizeBeforeCreate = claimSubmissionStatusRepository.findAll().size();
        // Create the ClaimSubmissionStatus
        ClaimSubmissionStatusDTO claimSubmissionStatusDTO = claimSubmissionStatusMapper.toDto(claimSubmissionStatus);
        restClaimSubmissionStatusMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimSubmissionStatusDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ClaimSubmissionStatus in the database
        List<ClaimSubmissionStatus> claimSubmissionStatusList = claimSubmissionStatusRepository.findAll();
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
        assertThat(testClaimSubmissionStatus.getEraStatus()).isEqualTo(DEFAULT_ERA_STATUS);
        assertThat(testClaimSubmissionStatus.getEraStatusNotes()).isEqualTo(DEFAULT_ERA_STATUS_NOTES);
        assertThat(testClaimSubmissionStatus.getEraDate()).isEqualTo(DEFAULT_ERA_DATE);
        assertThat(testClaimSubmissionStatus.getResubmissinStatus()).isEqualTo(DEFAULT_RESUBMISSIN_STATUS);
        assertThat(testClaimSubmissionStatus.getResubmissionDetailId()).isEqualTo(DEFAULT_RESUBMISSION_DETAIL_ID);
        assertThat(testClaimSubmissionStatus.getResubmissionNotes()).isEqualTo(DEFAULT_RESUBMISSION_NOTES);
        assertThat(testClaimSubmissionStatus.getVoidStatus()).isEqualTo(DEFAULT_VOID_STATUS);
        assertThat(testClaimSubmissionStatus.getVoidNote()).isEqualTo(DEFAULT_VOID_NOTE);
        assertThat(testClaimSubmissionStatus.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testClaimSubmissionStatus.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testClaimSubmissionStatus.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testClaimSubmissionStatus.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testClaimSubmissionStatus.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testClaimSubmissionStatus.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testClaimSubmissionStatus.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testClaimSubmissionStatus.getClaimSubmissionStatusUuid()).isEqualTo(DEFAULT_CLAIM_SUBMISSION_STATUS_UUID);
        assertThat(testClaimSubmissionStatus.getClaimCob835MasterId()).isEqualTo(DEFAULT_CLAIM_COB_835_MASTER_ID);
        assertThat(testClaimSubmissionStatus.getClaimStatus277MasterId()).isEqualTo(DEFAULT_CLAIM_STATUS_277_MASTER_ID);
        assertThat(testClaimSubmissionStatus.getPayorId()).isEqualTo(DEFAULT_PAYOR_ID);
        assertThat(testClaimSubmissionStatus.getReadyForSubmissionStatus()).isEqualTo(DEFAULT_READY_FOR_SUBMISSION_STATUS);
        assertThat(testClaimSubmissionStatus.getPeriodNo()).isEqualTo(DEFAULT_PERIOD_NO);
        assertThat(testClaimSubmissionStatus.getBranchId()).isEqualTo(DEFAULT_BRANCH_ID);
    }

    @Test
    @Transactional
    void createClaimSubmissionStatusWithExistingId() throws Exception {
        // Create the ClaimSubmissionStatus with an existing ID
        claimSubmissionStatus.setClaimStatusId(1L);
        ClaimSubmissionStatusDTO claimSubmissionStatusDTO = claimSubmissionStatusMapper.toDto(claimSubmissionStatus);

        int databaseSizeBeforeCreate = claimSubmissionStatusRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restClaimSubmissionStatusMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimSubmissionStatusDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimSubmissionStatus in the database
        List<ClaimSubmissionStatus> claimSubmissionStatusList = claimSubmissionStatusRepository.findAll();
        assertThat(claimSubmissionStatusList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllClaimSubmissionStatuses() throws Exception {
        // Initialize the database
        claimSubmissionStatusRepository.saveAndFlush(claimSubmissionStatus);

        // Get all the claimSubmissionStatusList
        restClaimSubmissionStatusMockMvc
            .perform(get(ENTITY_API_URL + "?sort=claimStatusId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].claimStatusId").value(hasItem(claimSubmissionStatus.getClaimStatusId().intValue())))
            .andExpect(jsonPath("$.[*].salesOrderId").value(hasItem(DEFAULT_SALES_ORDER_ID.intValue())))
            .andExpect(jsonPath("$.[*].salesOrderNo").value(hasItem(DEFAULT_SALES_ORDER_NO)))
            .andExpect(jsonPath("$.[*].invoiceId").value(hasItem(DEFAULT_INVOICE_ID.intValue())))
            .andExpect(jsonPath("$.[*].invoiceNo").value(hasItem(DEFAULT_INVOICE_NO)))
            .andExpect(jsonPath("$.[*].payorLevel").value(hasItem(DEFAULT_PAYOR_LEVEL)))
            .andExpect(jsonPath("$.[*].payorIdNo").value(hasItem(DEFAULT_PAYOR_ID_NO)))
            .andExpect(jsonPath("$.[*].claimSubmissionDataId").value(hasItem(DEFAULT_CLAIM_SUBMISSION_DATA_ID.intValue())))
            .andExpect(jsonPath("$.[*].intClaimNo").value(hasItem(DEFAULT_INT_CLAIM_NO)))
            .andExpect(jsonPath("$.[*].patientAccountNo").value(hasItem(DEFAULT_PATIENT_ACCOUNT_NO)))
            .andExpect(jsonPath("$.[*].payorClaimControlNo").value(hasItem(DEFAULT_PAYOR_CLAIM_CONTROL_NO)))
            .andExpect(jsonPath("$.[*].originalClaimControlNo").value(hasItem(DEFAULT_ORIGINAL_CLAIM_CONTROL_NO)))
            .andExpect(jsonPath("$.[*].patientIdNo").value(hasItem(DEFAULT_PATIENT_ID_NO)))
            .andExpect(jsonPath("$.[*].payor").value(hasItem(DEFAULT_PAYOR)))
            .andExpect(jsonPath("$.[*].claimSubmissionDate").value(hasItem(DEFAULT_CLAIM_SUBMISSION_DATE.toString())))
            .andExpect(jsonPath("$.[*].submissionStatus").value(hasItem(DEFAULT_SUBMISSION_STATUS)))
            .andExpect(jsonPath("$.[*].submissionNote").value(hasItem(DEFAULT_SUBMISSION_NOTE)))
            .andExpect(jsonPath("$.[*].responseStatus").value(hasItem(DEFAULT_RESPONSE_STATUS)))
            .andExpect(jsonPath("$.[*].responseStatusNotes").value(hasItem(DEFAULT_RESPONSE_STATUS_NOTES)))
            .andExpect(jsonPath("$.[*].responseStatusDate").value(hasItem(DEFAULT_RESPONSE_STATUS_DATE.toString())))
            .andExpect(jsonPath("$.[*].eraStatus").value(hasItem(DEFAULT_ERA_STATUS)))
            .andExpect(jsonPath("$.[*].eraStatusNotes").value(hasItem(DEFAULT_ERA_STATUS_NOTES)))
            .andExpect(jsonPath("$.[*].eraDate").value(hasItem(DEFAULT_ERA_DATE.toString())))
            .andExpect(jsonPath("$.[*].resubmissinStatus").value(hasItem(DEFAULT_RESUBMISSIN_STATUS)))
            .andExpect(jsonPath("$.[*].resubmissionDetailId").value(hasItem(DEFAULT_RESUBMISSION_DETAIL_ID.intValue())))
            .andExpect(jsonPath("$.[*].resubmissionNotes").value(hasItem(DEFAULT_RESUBMISSION_NOTES)))
            .andExpect(jsonPath("$.[*].voidStatus").value(hasItem(DEFAULT_VOID_STATUS)))
            .andExpect(jsonPath("$.[*].voidNote").value(hasItem(DEFAULT_VOID_NOTE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].claimSubmissionStatusUuid").value(hasItem(DEFAULT_CLAIM_SUBMISSION_STATUS_UUID.toString())))
            .andExpect(jsonPath("$.[*].claimCob835MasterId").value(hasItem(DEFAULT_CLAIM_COB_835_MASTER_ID.intValue())))
            .andExpect(jsonPath("$.[*].claimStatus277MasterId").value(hasItem(DEFAULT_CLAIM_STATUS_277_MASTER_ID.intValue())))
            .andExpect(jsonPath("$.[*].payorId").value(hasItem(DEFAULT_PAYOR_ID)))
            .andExpect(jsonPath("$.[*].readyForSubmissionStatus").value(hasItem(DEFAULT_READY_FOR_SUBMISSION_STATUS)))
            .andExpect(jsonPath("$.[*].periodNo").value(hasItem(DEFAULT_PERIOD_NO)))
            .andExpect(jsonPath("$.[*].branchId").value(hasItem(DEFAULT_BRANCH_ID.intValue())));
    }

    @Test
    @Transactional
    void getClaimSubmissionStatus() throws Exception {
        // Initialize the database
        claimSubmissionStatusRepository.saveAndFlush(claimSubmissionStatus);

        // Get the claimSubmissionStatus
        restClaimSubmissionStatusMockMvc
            .perform(get(ENTITY_API_URL_ID, claimSubmissionStatus.getClaimStatusId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.claimStatusId").value(claimSubmissionStatus.getClaimStatusId().intValue()))
            .andExpect(jsonPath("$.salesOrderId").value(DEFAULT_SALES_ORDER_ID.intValue()))
            .andExpect(jsonPath("$.salesOrderNo").value(DEFAULT_SALES_ORDER_NO))
            .andExpect(jsonPath("$.invoiceId").value(DEFAULT_INVOICE_ID.intValue()))
            .andExpect(jsonPath("$.invoiceNo").value(DEFAULT_INVOICE_NO))
            .andExpect(jsonPath("$.payorLevel").value(DEFAULT_PAYOR_LEVEL))
            .andExpect(jsonPath("$.payorIdNo").value(DEFAULT_PAYOR_ID_NO))
            .andExpect(jsonPath("$.claimSubmissionDataId").value(DEFAULT_CLAIM_SUBMISSION_DATA_ID.intValue()))
            .andExpect(jsonPath("$.intClaimNo").value(DEFAULT_INT_CLAIM_NO))
            .andExpect(jsonPath("$.patientAccountNo").value(DEFAULT_PATIENT_ACCOUNT_NO))
            .andExpect(jsonPath("$.payorClaimControlNo").value(DEFAULT_PAYOR_CLAIM_CONTROL_NO))
            .andExpect(jsonPath("$.originalClaimControlNo").value(DEFAULT_ORIGINAL_CLAIM_CONTROL_NO))
            .andExpect(jsonPath("$.patientIdNo").value(DEFAULT_PATIENT_ID_NO))
            .andExpect(jsonPath("$.payor").value(DEFAULT_PAYOR))
            .andExpect(jsonPath("$.claimSubmissionDate").value(DEFAULT_CLAIM_SUBMISSION_DATE.toString()))
            .andExpect(jsonPath("$.submissionStatus").value(DEFAULT_SUBMISSION_STATUS))
            .andExpect(jsonPath("$.submissionNote").value(DEFAULT_SUBMISSION_NOTE))
            .andExpect(jsonPath("$.responseStatus").value(DEFAULT_RESPONSE_STATUS))
            .andExpect(jsonPath("$.responseStatusNotes").value(DEFAULT_RESPONSE_STATUS_NOTES))
            .andExpect(jsonPath("$.responseStatusDate").value(DEFAULT_RESPONSE_STATUS_DATE.toString()))
            .andExpect(jsonPath("$.eraStatus").value(DEFAULT_ERA_STATUS))
            .andExpect(jsonPath("$.eraStatusNotes").value(DEFAULT_ERA_STATUS_NOTES))
            .andExpect(jsonPath("$.eraDate").value(DEFAULT_ERA_DATE.toString()))
            .andExpect(jsonPath("$.resubmissinStatus").value(DEFAULT_RESUBMISSIN_STATUS))
            .andExpect(jsonPath("$.resubmissionDetailId").value(DEFAULT_RESUBMISSION_DETAIL_ID.intValue()))
            .andExpect(jsonPath("$.resubmissionNotes").value(DEFAULT_RESUBMISSION_NOTES))
            .andExpect(jsonPath("$.voidStatus").value(DEFAULT_VOID_STATUS))
            .andExpect(jsonPath("$.voidNote").value(DEFAULT_VOID_NOTE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.claimSubmissionStatusUuid").value(DEFAULT_CLAIM_SUBMISSION_STATUS_UUID.toString()))
            .andExpect(jsonPath("$.claimCob835MasterId").value(DEFAULT_CLAIM_COB_835_MASTER_ID.intValue()))
            .andExpect(jsonPath("$.claimStatus277MasterId").value(DEFAULT_CLAIM_STATUS_277_MASTER_ID.intValue()))
            .andExpect(jsonPath("$.payorId").value(DEFAULT_PAYOR_ID))
            .andExpect(jsonPath("$.readyForSubmissionStatus").value(DEFAULT_READY_FOR_SUBMISSION_STATUS))
            .andExpect(jsonPath("$.periodNo").value(DEFAULT_PERIOD_NO))
            .andExpect(jsonPath("$.branchId").value(DEFAULT_BRANCH_ID.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingClaimSubmissionStatus() throws Exception {
        // Get the claimSubmissionStatus
        restClaimSubmissionStatusMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewClaimSubmissionStatus() throws Exception {
        // Initialize the database
        claimSubmissionStatusRepository.saveAndFlush(claimSubmissionStatus);

        int databaseSizeBeforeUpdate = claimSubmissionStatusRepository.findAll().size();

        // Update the claimSubmissionStatus
        ClaimSubmissionStatus updatedClaimSubmissionStatus = claimSubmissionStatusRepository
            .findById(claimSubmissionStatus.getClaimStatusId())
            .get();
        // Disconnect from session so that the updates on updatedClaimSubmissionStatus are not directly saved in db
        em.detach(updatedClaimSubmissionStatus);
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
            .eraStatus(UPDATED_ERA_STATUS)
            .eraStatusNotes(UPDATED_ERA_STATUS_NOTES)
            .eraDate(UPDATED_ERA_DATE)
            .resubmissinStatus(UPDATED_RESUBMISSIN_STATUS)
            .resubmissionDetailId(UPDATED_RESUBMISSION_DETAIL_ID)
            .resubmissionNotes(UPDATED_RESUBMISSION_NOTES)
            .voidStatus(UPDATED_VOID_STATUS)
            .voidNote(UPDATED_VOID_NOTE)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .claimSubmissionStatusUuid(UPDATED_CLAIM_SUBMISSION_STATUS_UUID)
            .claimCob835MasterId(UPDATED_CLAIM_COB_835_MASTER_ID)
            .claimStatus277MasterId(UPDATED_CLAIM_STATUS_277_MASTER_ID)
            .payorId(UPDATED_PAYOR_ID)
            .readyForSubmissionStatus(UPDATED_READY_FOR_SUBMISSION_STATUS)
            .periodNo(UPDATED_PERIOD_NO)
            .branchId(UPDATED_BRANCH_ID);
        ClaimSubmissionStatusDTO claimSubmissionStatusDTO = claimSubmissionStatusMapper.toDto(updatedClaimSubmissionStatus);

        restClaimSubmissionStatusMockMvc
            .perform(
                put(ENTITY_API_URL_ID, claimSubmissionStatusDTO.getClaimStatusId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimSubmissionStatusDTO))
            )
            .andExpect(status().isOk());

        // Validate the ClaimSubmissionStatus in the database
        List<ClaimSubmissionStatus> claimSubmissionStatusList = claimSubmissionStatusRepository.findAll();
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
        assertThat(testClaimSubmissionStatus.getEraStatus()).isEqualTo(UPDATED_ERA_STATUS);
        assertThat(testClaimSubmissionStatus.getEraStatusNotes()).isEqualTo(UPDATED_ERA_STATUS_NOTES);
        assertThat(testClaimSubmissionStatus.getEraDate()).isEqualTo(UPDATED_ERA_DATE);
        assertThat(testClaimSubmissionStatus.getResubmissinStatus()).isEqualTo(UPDATED_RESUBMISSIN_STATUS);
        assertThat(testClaimSubmissionStatus.getResubmissionDetailId()).isEqualTo(UPDATED_RESUBMISSION_DETAIL_ID);
        assertThat(testClaimSubmissionStatus.getResubmissionNotes()).isEqualTo(UPDATED_RESUBMISSION_NOTES);
        assertThat(testClaimSubmissionStatus.getVoidStatus()).isEqualTo(UPDATED_VOID_STATUS);
        assertThat(testClaimSubmissionStatus.getVoidNote()).isEqualTo(UPDATED_VOID_NOTE);
        assertThat(testClaimSubmissionStatus.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testClaimSubmissionStatus.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testClaimSubmissionStatus.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testClaimSubmissionStatus.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testClaimSubmissionStatus.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testClaimSubmissionStatus.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testClaimSubmissionStatus.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testClaimSubmissionStatus.getClaimSubmissionStatusUuid()).isEqualTo(UPDATED_CLAIM_SUBMISSION_STATUS_UUID);
        assertThat(testClaimSubmissionStatus.getClaimCob835MasterId()).isEqualTo(UPDATED_CLAIM_COB_835_MASTER_ID);
        assertThat(testClaimSubmissionStatus.getClaimStatus277MasterId()).isEqualTo(UPDATED_CLAIM_STATUS_277_MASTER_ID);
        assertThat(testClaimSubmissionStatus.getPayorId()).isEqualTo(UPDATED_PAYOR_ID);
        assertThat(testClaimSubmissionStatus.getReadyForSubmissionStatus()).isEqualTo(UPDATED_READY_FOR_SUBMISSION_STATUS);
        assertThat(testClaimSubmissionStatus.getPeriodNo()).isEqualTo(UPDATED_PERIOD_NO);
        assertThat(testClaimSubmissionStatus.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
    }

    @Test
    @Transactional
    void putNonExistingClaimSubmissionStatus() throws Exception {
        int databaseSizeBeforeUpdate = claimSubmissionStatusRepository.findAll().size();
        claimSubmissionStatus.setClaimStatusId(count.incrementAndGet());

        // Create the ClaimSubmissionStatus
        ClaimSubmissionStatusDTO claimSubmissionStatusDTO = claimSubmissionStatusMapper.toDto(claimSubmissionStatus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClaimSubmissionStatusMockMvc
            .perform(
                put(ENTITY_API_URL_ID, claimSubmissionStatusDTO.getClaimStatusId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimSubmissionStatusDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimSubmissionStatus in the database
        List<ClaimSubmissionStatus> claimSubmissionStatusList = claimSubmissionStatusRepository.findAll();
        assertThat(claimSubmissionStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchClaimSubmissionStatus() throws Exception {
        int databaseSizeBeforeUpdate = claimSubmissionStatusRepository.findAll().size();
        claimSubmissionStatus.setClaimStatusId(count.incrementAndGet());

        // Create the ClaimSubmissionStatus
        ClaimSubmissionStatusDTO claimSubmissionStatusDTO = claimSubmissionStatusMapper.toDto(claimSubmissionStatus);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaimSubmissionStatusMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimSubmissionStatusDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimSubmissionStatus in the database
        List<ClaimSubmissionStatus> claimSubmissionStatusList = claimSubmissionStatusRepository.findAll();
        assertThat(claimSubmissionStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamClaimSubmissionStatus() throws Exception {
        int databaseSizeBeforeUpdate = claimSubmissionStatusRepository.findAll().size();
        claimSubmissionStatus.setClaimStatusId(count.incrementAndGet());

        // Create the ClaimSubmissionStatus
        ClaimSubmissionStatusDTO claimSubmissionStatusDTO = claimSubmissionStatusMapper.toDto(claimSubmissionStatus);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaimSubmissionStatusMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimSubmissionStatusDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ClaimSubmissionStatus in the database
        List<ClaimSubmissionStatus> claimSubmissionStatusList = claimSubmissionStatusRepository.findAll();
        assertThat(claimSubmissionStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateClaimSubmissionStatusWithPatch() throws Exception {
        // Initialize the database
        claimSubmissionStatusRepository.saveAndFlush(claimSubmissionStatus);

        int databaseSizeBeforeUpdate = claimSubmissionStatusRepository.findAll().size();

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
            .eraStatusNotes(UPDATED_ERA_STATUS_NOTES)
            .resubmissionNotes(UPDATED_RESUBMISSION_NOTES)
            .voidStatus(UPDATED_VOID_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .claimSubmissionStatusUuid(UPDATED_CLAIM_SUBMISSION_STATUS_UUID)
            .periodNo(UPDATED_PERIOD_NO)
            .branchId(UPDATED_BRANCH_ID);

        restClaimSubmissionStatusMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClaimSubmissionStatus.getClaimStatusId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClaimSubmissionStatus))
            )
            .andExpect(status().isOk());

        // Validate the ClaimSubmissionStatus in the database
        List<ClaimSubmissionStatus> claimSubmissionStatusList = claimSubmissionStatusRepository.findAll();
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
        assertThat(testClaimSubmissionStatus.getEraStatus()).isEqualTo(DEFAULT_ERA_STATUS);
        assertThat(testClaimSubmissionStatus.getEraStatusNotes()).isEqualTo(UPDATED_ERA_STATUS_NOTES);
        assertThat(testClaimSubmissionStatus.getEraDate()).isEqualTo(DEFAULT_ERA_DATE);
        assertThat(testClaimSubmissionStatus.getResubmissinStatus()).isEqualTo(DEFAULT_RESUBMISSIN_STATUS);
        assertThat(testClaimSubmissionStatus.getResubmissionDetailId()).isEqualTo(DEFAULT_RESUBMISSION_DETAIL_ID);
        assertThat(testClaimSubmissionStatus.getResubmissionNotes()).isEqualTo(UPDATED_RESUBMISSION_NOTES);
        assertThat(testClaimSubmissionStatus.getVoidStatus()).isEqualTo(UPDATED_VOID_STATUS);
        assertThat(testClaimSubmissionStatus.getVoidNote()).isEqualTo(DEFAULT_VOID_NOTE);
        assertThat(testClaimSubmissionStatus.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testClaimSubmissionStatus.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testClaimSubmissionStatus.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testClaimSubmissionStatus.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testClaimSubmissionStatus.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testClaimSubmissionStatus.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testClaimSubmissionStatus.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testClaimSubmissionStatus.getClaimSubmissionStatusUuid()).isEqualTo(UPDATED_CLAIM_SUBMISSION_STATUS_UUID);
        assertThat(testClaimSubmissionStatus.getClaimCob835MasterId()).isEqualTo(DEFAULT_CLAIM_COB_835_MASTER_ID);
        assertThat(testClaimSubmissionStatus.getClaimStatus277MasterId()).isEqualTo(DEFAULT_CLAIM_STATUS_277_MASTER_ID);
        assertThat(testClaimSubmissionStatus.getPayorId()).isEqualTo(DEFAULT_PAYOR_ID);
        assertThat(testClaimSubmissionStatus.getReadyForSubmissionStatus()).isEqualTo(DEFAULT_READY_FOR_SUBMISSION_STATUS);
        assertThat(testClaimSubmissionStatus.getPeriodNo()).isEqualTo(UPDATED_PERIOD_NO);
        assertThat(testClaimSubmissionStatus.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
    }

    @Test
    @Transactional
    void fullUpdateClaimSubmissionStatusWithPatch() throws Exception {
        // Initialize the database
        claimSubmissionStatusRepository.saveAndFlush(claimSubmissionStatus);

        int databaseSizeBeforeUpdate = claimSubmissionStatusRepository.findAll().size();

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
            .eraStatus(UPDATED_ERA_STATUS)
            .eraStatusNotes(UPDATED_ERA_STATUS_NOTES)
            .eraDate(UPDATED_ERA_DATE)
            .resubmissinStatus(UPDATED_RESUBMISSIN_STATUS)
            .resubmissionDetailId(UPDATED_RESUBMISSION_DETAIL_ID)
            .resubmissionNotes(UPDATED_RESUBMISSION_NOTES)
            .voidStatus(UPDATED_VOID_STATUS)
            .voidNote(UPDATED_VOID_NOTE)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .claimSubmissionStatusUuid(UPDATED_CLAIM_SUBMISSION_STATUS_UUID)
            .claimCob835MasterId(UPDATED_CLAIM_COB_835_MASTER_ID)
            .claimStatus277MasterId(UPDATED_CLAIM_STATUS_277_MASTER_ID)
            .payorId(UPDATED_PAYOR_ID)
            .readyForSubmissionStatus(UPDATED_READY_FOR_SUBMISSION_STATUS)
            .periodNo(UPDATED_PERIOD_NO)
            .branchId(UPDATED_BRANCH_ID);

        restClaimSubmissionStatusMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClaimSubmissionStatus.getClaimStatusId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClaimSubmissionStatus))
            )
            .andExpect(status().isOk());

        // Validate the ClaimSubmissionStatus in the database
        List<ClaimSubmissionStatus> claimSubmissionStatusList = claimSubmissionStatusRepository.findAll();
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
        assertThat(testClaimSubmissionStatus.getEraStatus()).isEqualTo(UPDATED_ERA_STATUS);
        assertThat(testClaimSubmissionStatus.getEraStatusNotes()).isEqualTo(UPDATED_ERA_STATUS_NOTES);
        assertThat(testClaimSubmissionStatus.getEraDate()).isEqualTo(UPDATED_ERA_DATE);
        assertThat(testClaimSubmissionStatus.getResubmissinStatus()).isEqualTo(UPDATED_RESUBMISSIN_STATUS);
        assertThat(testClaimSubmissionStatus.getResubmissionDetailId()).isEqualTo(UPDATED_RESUBMISSION_DETAIL_ID);
        assertThat(testClaimSubmissionStatus.getResubmissionNotes()).isEqualTo(UPDATED_RESUBMISSION_NOTES);
        assertThat(testClaimSubmissionStatus.getVoidStatus()).isEqualTo(UPDATED_VOID_STATUS);
        assertThat(testClaimSubmissionStatus.getVoidNote()).isEqualTo(UPDATED_VOID_NOTE);
        assertThat(testClaimSubmissionStatus.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testClaimSubmissionStatus.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testClaimSubmissionStatus.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testClaimSubmissionStatus.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testClaimSubmissionStatus.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testClaimSubmissionStatus.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testClaimSubmissionStatus.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testClaimSubmissionStatus.getClaimSubmissionStatusUuid()).isEqualTo(UPDATED_CLAIM_SUBMISSION_STATUS_UUID);
        assertThat(testClaimSubmissionStatus.getClaimCob835MasterId()).isEqualTo(UPDATED_CLAIM_COB_835_MASTER_ID);
        assertThat(testClaimSubmissionStatus.getClaimStatus277MasterId()).isEqualTo(UPDATED_CLAIM_STATUS_277_MASTER_ID);
        assertThat(testClaimSubmissionStatus.getPayorId()).isEqualTo(UPDATED_PAYOR_ID);
        assertThat(testClaimSubmissionStatus.getReadyForSubmissionStatus()).isEqualTo(UPDATED_READY_FOR_SUBMISSION_STATUS);
        assertThat(testClaimSubmissionStatus.getPeriodNo()).isEqualTo(UPDATED_PERIOD_NO);
        assertThat(testClaimSubmissionStatus.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
    }

    @Test
    @Transactional
    void patchNonExistingClaimSubmissionStatus() throws Exception {
        int databaseSizeBeforeUpdate = claimSubmissionStatusRepository.findAll().size();
        claimSubmissionStatus.setClaimStatusId(count.incrementAndGet());

        // Create the ClaimSubmissionStatus
        ClaimSubmissionStatusDTO claimSubmissionStatusDTO = claimSubmissionStatusMapper.toDto(claimSubmissionStatus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClaimSubmissionStatusMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, claimSubmissionStatusDTO.getClaimStatusId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(claimSubmissionStatusDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimSubmissionStatus in the database
        List<ClaimSubmissionStatus> claimSubmissionStatusList = claimSubmissionStatusRepository.findAll();
        assertThat(claimSubmissionStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchClaimSubmissionStatus() throws Exception {
        int databaseSizeBeforeUpdate = claimSubmissionStatusRepository.findAll().size();
        claimSubmissionStatus.setClaimStatusId(count.incrementAndGet());

        // Create the ClaimSubmissionStatus
        ClaimSubmissionStatusDTO claimSubmissionStatusDTO = claimSubmissionStatusMapper.toDto(claimSubmissionStatus);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaimSubmissionStatusMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(claimSubmissionStatusDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimSubmissionStatus in the database
        List<ClaimSubmissionStatus> claimSubmissionStatusList = claimSubmissionStatusRepository.findAll();
        assertThat(claimSubmissionStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamClaimSubmissionStatus() throws Exception {
        int databaseSizeBeforeUpdate = claimSubmissionStatusRepository.findAll().size();
        claimSubmissionStatus.setClaimStatusId(count.incrementAndGet());

        // Create the ClaimSubmissionStatus
        ClaimSubmissionStatusDTO claimSubmissionStatusDTO = claimSubmissionStatusMapper.toDto(claimSubmissionStatus);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaimSubmissionStatusMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(claimSubmissionStatusDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ClaimSubmissionStatus in the database
        List<ClaimSubmissionStatus> claimSubmissionStatusList = claimSubmissionStatusRepository.findAll();
        assertThat(claimSubmissionStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteClaimSubmissionStatus() throws Exception {
        // Initialize the database
        claimSubmissionStatusRepository.saveAndFlush(claimSubmissionStatus);

        int databaseSizeBeforeDelete = claimSubmissionStatusRepository.findAll().size();

        // Delete the claimSubmissionStatus
        restClaimSubmissionStatusMockMvc
            .perform(delete(ENTITY_API_URL_ID, claimSubmissionStatus.getClaimStatusId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ClaimSubmissionStatus> claimSubmissionStatusList = claimSubmissionStatusRepository.findAll();
        assertThat(claimSubmissionStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
