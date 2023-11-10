package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.InvoiceMasterDetails;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.InvoiceMasterDetailsRepository;
import com.sunknowledge.dme.rcm.service.dto.InvoiceMasterDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.InvoiceMasterDetailsMapper;
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
 * Integration tests for the {@link InvoiceMasterDetailsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class InvoiceMasterDetailsResourceIT {

    private static final String DEFAULT_INVOICE_NO = "AAAAAAAAAA";
    private static final String UPDATED_INVOICE_NO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_INVOICE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_INVOICE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_INVOICE_TO_ENTITY = "AAAAAAAAAA";
    private static final String UPDATED_INVOICE_TO_ENTITY = "BBBBBBBBBB";

    private static final Long DEFAULT_INVOICE_TO_PAYOR_ID = 1L;
    private static final Long UPDATED_INVOICE_TO_PAYOR_ID = 2L;

    private static final String DEFAULT_INVOICE_TO_PAYOR_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INVOICE_TO_PAYOR_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_SALES_ORDER_ID = 1L;
    private static final Long UPDATED_SALES_ORDER_ID = 2L;

    private static final String DEFAULT_SALES_ORDER_NO = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_NO = "BBBBBBBBBB";

    private static final Long DEFAULT_PATIENT_ID = 1L;
    private static final Long UPDATED_PATIENT_ID = 2L;

    private static final String DEFAULT_PATIENT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_LAST_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_ITEM_QTY_TOTAL = 1L;
    private static final Long UPDATED_ITEM_QTY_TOTAL = 2L;

    private static final Double DEFAULT_CHARGED_AMOUNT_TOTAL = 1D;
    private static final Double UPDATED_CHARGED_AMOUNT_TOTAL = 2D;

    private static final Double DEFAULT_ALLOW_AMOUNT_TOTAL = 1D;
    private static final Double UPDATED_ALLOW_AMOUNT_TOTAL = 2D;

    private static final Double DEFAULT_PAYMENT_AMOUNT_TOTAL = 1D;
    private static final Double UPDATED_PAYMENT_AMOUNT_TOTAL = 2D;

    private static final Double DEFAULT_TAX_AMOUNT_TOTAL = 1D;
    private static final Double UPDATED_TAX_AMOUNT_TOTAL = 2D;

    private static final Double DEFAULT_ADJ_AMOUNT_TOTAL = 1D;
    private static final Double UPDATED_ADJ_AMOUNT_TOTAL = 2D;

    private static final Double DEFAULT_BALANCE_AMOUNT_TOTAL = 1D;
    private static final Double UPDATED_BALANCE_AMOUNT_TOTAL = 2D;

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

    private static final UUID DEFAULT_INVOICE_MASTER_DETAILS_UUID = UUID.randomUUID();
    private static final UUID UPDATED_INVOICE_MASTER_DETAILS_UUID = UUID.randomUUID();

    private static final String DEFAULT_INVOICE_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_INVOICE_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_PRIMARY_SUBMISSION_MASTER_ID = 1L;
    private static final Long UPDATED_PRIMARY_SUBMISSION_MASTER_ID = 2L;

    private static final String DEFAULT_CLAIM_CONTROL_NO = "AAAAAAAAAA";
    private static final String UPDATED_CLAIM_CONTROL_NO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/invoice-master-details";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{invoiceId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private InvoiceMasterDetailsRepository invoiceMasterDetailsRepository;

    @Autowired
    private InvoiceMasterDetailsMapper invoiceMasterDetailsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private InvoiceMasterDetails invoiceMasterDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InvoiceMasterDetails createEntity(EntityManager em) {
        InvoiceMasterDetails invoiceMasterDetails = new InvoiceMasterDetails()
            .invoiceNo(DEFAULT_INVOICE_NO)
            .invoiceDate(DEFAULT_INVOICE_DATE)
            .invoiceToEntity(DEFAULT_INVOICE_TO_ENTITY)
            .invoiceToPayorId(DEFAULT_INVOICE_TO_PAYOR_ID)
            .invoiceToPayorName(DEFAULT_INVOICE_TO_PAYOR_NAME)
            .salesOrderId(DEFAULT_SALES_ORDER_ID)
            .salesOrderNo(DEFAULT_SALES_ORDER_NO)
            .patientId(DEFAULT_PATIENT_ID)
            .patientFirstName(DEFAULT_PATIENT_FIRST_NAME)
            .patientMiddleName(DEFAULT_PATIENT_MIDDLE_NAME)
            .patientLastName(DEFAULT_PATIENT_LAST_NAME)
            .itemQtyTotal(DEFAULT_ITEM_QTY_TOTAL)
            .chargedAmountTotal(DEFAULT_CHARGED_AMOUNT_TOTAL)
            .allowAmountTotal(DEFAULT_ALLOW_AMOUNT_TOTAL)
            .paymentAmountTotal(DEFAULT_PAYMENT_AMOUNT_TOTAL)
            .taxAmountTotal(DEFAULT_TAX_AMOUNT_TOTAL)
            .adjAmountTotal(DEFAULT_ADJ_AMOUNT_TOTAL)
            .balanceAmountTotal(DEFAULT_BALANCE_AMOUNT_TOTAL)
            .status(DEFAULT_STATUS)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .invoiceMasterDetailsUuid(DEFAULT_INVOICE_MASTER_DETAILS_UUID)
            .invoiceStatus(DEFAULT_INVOICE_STATUS)
            .primarySubmissionMasterId(DEFAULT_PRIMARY_SUBMISSION_MASTER_ID)
            .claimControlNo(DEFAULT_CLAIM_CONTROL_NO);
        return invoiceMasterDetails;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InvoiceMasterDetails createUpdatedEntity(EntityManager em) {
        InvoiceMasterDetails invoiceMasterDetails = new InvoiceMasterDetails()
            .invoiceNo(UPDATED_INVOICE_NO)
            .invoiceDate(UPDATED_INVOICE_DATE)
            .invoiceToEntity(UPDATED_INVOICE_TO_ENTITY)
            .invoiceToPayorId(UPDATED_INVOICE_TO_PAYOR_ID)
            .invoiceToPayorName(UPDATED_INVOICE_TO_PAYOR_NAME)
            .salesOrderId(UPDATED_SALES_ORDER_ID)
            .salesOrderNo(UPDATED_SALES_ORDER_NO)
            .patientId(UPDATED_PATIENT_ID)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientMiddleName(UPDATED_PATIENT_MIDDLE_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .itemQtyTotal(UPDATED_ITEM_QTY_TOTAL)
            .chargedAmountTotal(UPDATED_CHARGED_AMOUNT_TOTAL)
            .allowAmountTotal(UPDATED_ALLOW_AMOUNT_TOTAL)
            .paymentAmountTotal(UPDATED_PAYMENT_AMOUNT_TOTAL)
            .taxAmountTotal(UPDATED_TAX_AMOUNT_TOTAL)
            .adjAmountTotal(UPDATED_ADJ_AMOUNT_TOTAL)
            .balanceAmountTotal(UPDATED_BALANCE_AMOUNT_TOTAL)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .invoiceMasterDetailsUuid(UPDATED_INVOICE_MASTER_DETAILS_UUID)
            .invoiceStatus(UPDATED_INVOICE_STATUS)
            .primarySubmissionMasterId(UPDATED_PRIMARY_SUBMISSION_MASTER_ID)
            .claimControlNo(UPDATED_CLAIM_CONTROL_NO);
        return invoiceMasterDetails;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(InvoiceMasterDetails.class).block();
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
        invoiceMasterDetails = createEntity(em);
    }

    @Test
    void createInvoiceMasterDetails() throws Exception {
        int databaseSizeBeforeCreate = invoiceMasterDetailsRepository.findAll().collectList().block().size();
        // Create the InvoiceMasterDetails
        InvoiceMasterDetailsDTO invoiceMasterDetailsDTO = invoiceMasterDetailsMapper.toDto(invoiceMasterDetails);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoiceMasterDetailsDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the InvoiceMasterDetails in the database
        List<InvoiceMasterDetails> invoiceMasterDetailsList = invoiceMasterDetailsRepository.findAll().collectList().block();
        assertThat(invoiceMasterDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        InvoiceMasterDetails testInvoiceMasterDetails = invoiceMasterDetailsList.get(invoiceMasterDetailsList.size() - 1);
        assertThat(testInvoiceMasterDetails.getInvoiceNo()).isEqualTo(DEFAULT_INVOICE_NO);
        assertThat(testInvoiceMasterDetails.getInvoiceDate()).isEqualTo(DEFAULT_INVOICE_DATE);
        assertThat(testInvoiceMasterDetails.getInvoiceToEntity()).isEqualTo(DEFAULT_INVOICE_TO_ENTITY);
        assertThat(testInvoiceMasterDetails.getInvoiceToPayorId()).isEqualTo(DEFAULT_INVOICE_TO_PAYOR_ID);
        assertThat(testInvoiceMasterDetails.getInvoiceToPayorName()).isEqualTo(DEFAULT_INVOICE_TO_PAYOR_NAME);
        assertThat(testInvoiceMasterDetails.getSalesOrderId()).isEqualTo(DEFAULT_SALES_ORDER_ID);
        assertThat(testInvoiceMasterDetails.getSalesOrderNo()).isEqualTo(DEFAULT_SALES_ORDER_NO);
        assertThat(testInvoiceMasterDetails.getPatientId()).isEqualTo(DEFAULT_PATIENT_ID);
        assertThat(testInvoiceMasterDetails.getPatientFirstName()).isEqualTo(DEFAULT_PATIENT_FIRST_NAME);
        assertThat(testInvoiceMasterDetails.getPatientMiddleName()).isEqualTo(DEFAULT_PATIENT_MIDDLE_NAME);
        assertThat(testInvoiceMasterDetails.getPatientLastName()).isEqualTo(DEFAULT_PATIENT_LAST_NAME);
        assertThat(testInvoiceMasterDetails.getItemQtyTotal()).isEqualTo(DEFAULT_ITEM_QTY_TOTAL);
        assertThat(testInvoiceMasterDetails.getChargedAmountTotal()).isEqualTo(DEFAULT_CHARGED_AMOUNT_TOTAL);
        assertThat(testInvoiceMasterDetails.getAllowAmountTotal()).isEqualTo(DEFAULT_ALLOW_AMOUNT_TOTAL);
        assertThat(testInvoiceMasterDetails.getPaymentAmountTotal()).isEqualTo(DEFAULT_PAYMENT_AMOUNT_TOTAL);
        assertThat(testInvoiceMasterDetails.getTaxAmountTotal()).isEqualTo(DEFAULT_TAX_AMOUNT_TOTAL);
        assertThat(testInvoiceMasterDetails.getAdjAmountTotal()).isEqualTo(DEFAULT_ADJ_AMOUNT_TOTAL);
        assertThat(testInvoiceMasterDetails.getBalanceAmountTotal()).isEqualTo(DEFAULT_BALANCE_AMOUNT_TOTAL);
        assertThat(testInvoiceMasterDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testInvoiceMasterDetails.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testInvoiceMasterDetails.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testInvoiceMasterDetails.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testInvoiceMasterDetails.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testInvoiceMasterDetails.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testInvoiceMasterDetails.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testInvoiceMasterDetails.getInvoiceMasterDetailsUuid()).isEqualTo(DEFAULT_INVOICE_MASTER_DETAILS_UUID);
        assertThat(testInvoiceMasterDetails.getInvoiceStatus()).isEqualTo(DEFAULT_INVOICE_STATUS);
        assertThat(testInvoiceMasterDetails.getPrimarySubmissionMasterId()).isEqualTo(DEFAULT_PRIMARY_SUBMISSION_MASTER_ID);
        assertThat(testInvoiceMasterDetails.getClaimControlNo()).isEqualTo(DEFAULT_CLAIM_CONTROL_NO);
    }

    @Test
    void createInvoiceMasterDetailsWithExistingId() throws Exception {
        // Create the InvoiceMasterDetails with an existing ID
        invoiceMasterDetails.setInvoiceId(1L);
        InvoiceMasterDetailsDTO invoiceMasterDetailsDTO = invoiceMasterDetailsMapper.toDto(invoiceMasterDetails);

        int databaseSizeBeforeCreate = invoiceMasterDetailsRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoiceMasterDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the InvoiceMasterDetails in the database
        List<InvoiceMasterDetails> invoiceMasterDetailsList = invoiceMasterDetailsRepository.findAll().collectList().block();
        assertThat(invoiceMasterDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllInvoiceMasterDetails() {
        // Initialize the database
        invoiceMasterDetailsRepository.save(invoiceMasterDetails).block();

        // Get all the invoiceMasterDetailsList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=invoiceId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].invoiceId")
            .value(hasItem(invoiceMasterDetails.getInvoiceId().intValue()))
            .jsonPath("$.[*].invoiceNo")
            .value(hasItem(DEFAULT_INVOICE_NO))
            .jsonPath("$.[*].invoiceDate")
            .value(hasItem(DEFAULT_INVOICE_DATE.toString()))
            .jsonPath("$.[*].invoiceToEntity")
            .value(hasItem(DEFAULT_INVOICE_TO_ENTITY))
            .jsonPath("$.[*].invoiceToPayorId")
            .value(hasItem(DEFAULT_INVOICE_TO_PAYOR_ID.intValue()))
            .jsonPath("$.[*].invoiceToPayorName")
            .value(hasItem(DEFAULT_INVOICE_TO_PAYOR_NAME))
            .jsonPath("$.[*].salesOrderId")
            .value(hasItem(DEFAULT_SALES_ORDER_ID.intValue()))
            .jsonPath("$.[*].salesOrderNo")
            .value(hasItem(DEFAULT_SALES_ORDER_NO))
            .jsonPath("$.[*].patientId")
            .value(hasItem(DEFAULT_PATIENT_ID.intValue()))
            .jsonPath("$.[*].patientFirstName")
            .value(hasItem(DEFAULT_PATIENT_FIRST_NAME))
            .jsonPath("$.[*].patientMiddleName")
            .value(hasItem(DEFAULT_PATIENT_MIDDLE_NAME))
            .jsonPath("$.[*].patientLastName")
            .value(hasItem(DEFAULT_PATIENT_LAST_NAME))
            .jsonPath("$.[*].itemQtyTotal")
            .value(hasItem(DEFAULT_ITEM_QTY_TOTAL.intValue()))
            .jsonPath("$.[*].chargedAmountTotal")
            .value(hasItem(DEFAULT_CHARGED_AMOUNT_TOTAL.doubleValue()))
            .jsonPath("$.[*].allowAmountTotal")
            .value(hasItem(DEFAULT_ALLOW_AMOUNT_TOTAL.doubleValue()))
            .jsonPath("$.[*].paymentAmountTotal")
            .value(hasItem(DEFAULT_PAYMENT_AMOUNT_TOTAL.doubleValue()))
            .jsonPath("$.[*].taxAmountTotal")
            .value(hasItem(DEFAULT_TAX_AMOUNT_TOTAL.doubleValue()))
            .jsonPath("$.[*].adjAmountTotal")
            .value(hasItem(DEFAULT_ADJ_AMOUNT_TOTAL.doubleValue()))
            .jsonPath("$.[*].balanceAmountTotal")
            .value(hasItem(DEFAULT_BALANCE_AMOUNT_TOTAL.doubleValue()))
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
            .jsonPath("$.[*].invoiceMasterDetailsUuid")
            .value(hasItem(DEFAULT_INVOICE_MASTER_DETAILS_UUID.toString()))
            .jsonPath("$.[*].invoiceStatus")
            .value(hasItem(DEFAULT_INVOICE_STATUS))
            .jsonPath("$.[*].primarySubmissionMasterId")
            .value(hasItem(DEFAULT_PRIMARY_SUBMISSION_MASTER_ID.intValue()))
            .jsonPath("$.[*].claimControlNo")
            .value(hasItem(DEFAULT_CLAIM_CONTROL_NO));
    }

    @Test
    void getInvoiceMasterDetails() {
        // Initialize the database
        invoiceMasterDetailsRepository.save(invoiceMasterDetails).block();

        // Get the invoiceMasterDetails
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, invoiceMasterDetails.getInvoiceId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.invoiceId")
            .value(is(invoiceMasterDetails.getInvoiceId().intValue()))
            .jsonPath("$.invoiceNo")
            .value(is(DEFAULT_INVOICE_NO))
            .jsonPath("$.invoiceDate")
            .value(is(DEFAULT_INVOICE_DATE.toString()))
            .jsonPath("$.invoiceToEntity")
            .value(is(DEFAULT_INVOICE_TO_ENTITY))
            .jsonPath("$.invoiceToPayorId")
            .value(is(DEFAULT_INVOICE_TO_PAYOR_ID.intValue()))
            .jsonPath("$.invoiceToPayorName")
            .value(is(DEFAULT_INVOICE_TO_PAYOR_NAME))
            .jsonPath("$.salesOrderId")
            .value(is(DEFAULT_SALES_ORDER_ID.intValue()))
            .jsonPath("$.salesOrderNo")
            .value(is(DEFAULT_SALES_ORDER_NO))
            .jsonPath("$.patientId")
            .value(is(DEFAULT_PATIENT_ID.intValue()))
            .jsonPath("$.patientFirstName")
            .value(is(DEFAULT_PATIENT_FIRST_NAME))
            .jsonPath("$.patientMiddleName")
            .value(is(DEFAULT_PATIENT_MIDDLE_NAME))
            .jsonPath("$.patientLastName")
            .value(is(DEFAULT_PATIENT_LAST_NAME))
            .jsonPath("$.itemQtyTotal")
            .value(is(DEFAULT_ITEM_QTY_TOTAL.intValue()))
            .jsonPath("$.chargedAmountTotal")
            .value(is(DEFAULT_CHARGED_AMOUNT_TOTAL.doubleValue()))
            .jsonPath("$.allowAmountTotal")
            .value(is(DEFAULT_ALLOW_AMOUNT_TOTAL.doubleValue()))
            .jsonPath("$.paymentAmountTotal")
            .value(is(DEFAULT_PAYMENT_AMOUNT_TOTAL.doubleValue()))
            .jsonPath("$.taxAmountTotal")
            .value(is(DEFAULT_TAX_AMOUNT_TOTAL.doubleValue()))
            .jsonPath("$.adjAmountTotal")
            .value(is(DEFAULT_ADJ_AMOUNT_TOTAL.doubleValue()))
            .jsonPath("$.balanceAmountTotal")
            .value(is(DEFAULT_BALANCE_AMOUNT_TOTAL.doubleValue()))
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
            .jsonPath("$.invoiceMasterDetailsUuid")
            .value(is(DEFAULT_INVOICE_MASTER_DETAILS_UUID.toString()))
            .jsonPath("$.invoiceStatus")
            .value(is(DEFAULT_INVOICE_STATUS))
            .jsonPath("$.primarySubmissionMasterId")
            .value(is(DEFAULT_PRIMARY_SUBMISSION_MASTER_ID.intValue()))
            .jsonPath("$.claimControlNo")
            .value(is(DEFAULT_CLAIM_CONTROL_NO));
    }

    @Test
    void getNonExistingInvoiceMasterDetails() {
        // Get the invoiceMasterDetails
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putNewInvoiceMasterDetails() throws Exception {
        // Initialize the database
        invoiceMasterDetailsRepository.save(invoiceMasterDetails).block();

        int databaseSizeBeforeUpdate = invoiceMasterDetailsRepository.findAll().collectList().block().size();

        // Update the invoiceMasterDetails
        InvoiceMasterDetails updatedInvoiceMasterDetails = invoiceMasterDetailsRepository
            .findById(invoiceMasterDetails.getInvoiceId())
            .block();
        updatedInvoiceMasterDetails
            .invoiceNo(UPDATED_INVOICE_NO)
            .invoiceDate(UPDATED_INVOICE_DATE)
            .invoiceToEntity(UPDATED_INVOICE_TO_ENTITY)
            .invoiceToPayorId(UPDATED_INVOICE_TO_PAYOR_ID)
            .invoiceToPayorName(UPDATED_INVOICE_TO_PAYOR_NAME)
            .salesOrderId(UPDATED_SALES_ORDER_ID)
            .salesOrderNo(UPDATED_SALES_ORDER_NO)
            .patientId(UPDATED_PATIENT_ID)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientMiddleName(UPDATED_PATIENT_MIDDLE_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .itemQtyTotal(UPDATED_ITEM_QTY_TOTAL)
            .chargedAmountTotal(UPDATED_CHARGED_AMOUNT_TOTAL)
            .allowAmountTotal(UPDATED_ALLOW_AMOUNT_TOTAL)
            .paymentAmountTotal(UPDATED_PAYMENT_AMOUNT_TOTAL)
            .taxAmountTotal(UPDATED_TAX_AMOUNT_TOTAL)
            .adjAmountTotal(UPDATED_ADJ_AMOUNT_TOTAL)
            .balanceAmountTotal(UPDATED_BALANCE_AMOUNT_TOTAL)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .invoiceMasterDetailsUuid(UPDATED_INVOICE_MASTER_DETAILS_UUID)
            .invoiceStatus(UPDATED_INVOICE_STATUS)
            .primarySubmissionMasterId(UPDATED_PRIMARY_SUBMISSION_MASTER_ID)
            .claimControlNo(UPDATED_CLAIM_CONTROL_NO);
        InvoiceMasterDetailsDTO invoiceMasterDetailsDTO = invoiceMasterDetailsMapper.toDto(updatedInvoiceMasterDetails);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, invoiceMasterDetailsDTO.getInvoiceId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoiceMasterDetailsDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the InvoiceMasterDetails in the database
        List<InvoiceMasterDetails> invoiceMasterDetailsList = invoiceMasterDetailsRepository.findAll().collectList().block();
        assertThat(invoiceMasterDetailsList).hasSize(databaseSizeBeforeUpdate);
        InvoiceMasterDetails testInvoiceMasterDetails = invoiceMasterDetailsList.get(invoiceMasterDetailsList.size() - 1);
        assertThat(testInvoiceMasterDetails.getInvoiceNo()).isEqualTo(UPDATED_INVOICE_NO);
        assertThat(testInvoiceMasterDetails.getInvoiceDate()).isEqualTo(UPDATED_INVOICE_DATE);
        assertThat(testInvoiceMasterDetails.getInvoiceToEntity()).isEqualTo(UPDATED_INVOICE_TO_ENTITY);
        assertThat(testInvoiceMasterDetails.getInvoiceToPayorId()).isEqualTo(UPDATED_INVOICE_TO_PAYOR_ID);
        assertThat(testInvoiceMasterDetails.getInvoiceToPayorName()).isEqualTo(UPDATED_INVOICE_TO_PAYOR_NAME);
        assertThat(testInvoiceMasterDetails.getSalesOrderId()).isEqualTo(UPDATED_SALES_ORDER_ID);
        assertThat(testInvoiceMasterDetails.getSalesOrderNo()).isEqualTo(UPDATED_SALES_ORDER_NO);
        assertThat(testInvoiceMasterDetails.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testInvoiceMasterDetails.getPatientFirstName()).isEqualTo(UPDATED_PATIENT_FIRST_NAME);
        assertThat(testInvoiceMasterDetails.getPatientMiddleName()).isEqualTo(UPDATED_PATIENT_MIDDLE_NAME);
        assertThat(testInvoiceMasterDetails.getPatientLastName()).isEqualTo(UPDATED_PATIENT_LAST_NAME);
        assertThat(testInvoiceMasterDetails.getItemQtyTotal()).isEqualTo(UPDATED_ITEM_QTY_TOTAL);
        assertThat(testInvoiceMasterDetails.getChargedAmountTotal()).isEqualTo(UPDATED_CHARGED_AMOUNT_TOTAL);
        assertThat(testInvoiceMasterDetails.getAllowAmountTotal()).isEqualTo(UPDATED_ALLOW_AMOUNT_TOTAL);
        assertThat(testInvoiceMasterDetails.getPaymentAmountTotal()).isEqualTo(UPDATED_PAYMENT_AMOUNT_TOTAL);
        assertThat(testInvoiceMasterDetails.getTaxAmountTotal()).isEqualTo(UPDATED_TAX_AMOUNT_TOTAL);
        assertThat(testInvoiceMasterDetails.getAdjAmountTotal()).isEqualTo(UPDATED_ADJ_AMOUNT_TOTAL);
        assertThat(testInvoiceMasterDetails.getBalanceAmountTotal()).isEqualTo(UPDATED_BALANCE_AMOUNT_TOTAL);
        assertThat(testInvoiceMasterDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testInvoiceMasterDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testInvoiceMasterDetails.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testInvoiceMasterDetails.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testInvoiceMasterDetails.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testInvoiceMasterDetails.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testInvoiceMasterDetails.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testInvoiceMasterDetails.getInvoiceMasterDetailsUuid()).isEqualTo(UPDATED_INVOICE_MASTER_DETAILS_UUID);
        assertThat(testInvoiceMasterDetails.getInvoiceStatus()).isEqualTo(UPDATED_INVOICE_STATUS);
        assertThat(testInvoiceMasterDetails.getPrimarySubmissionMasterId()).isEqualTo(UPDATED_PRIMARY_SUBMISSION_MASTER_ID);
        assertThat(testInvoiceMasterDetails.getClaimControlNo()).isEqualTo(UPDATED_CLAIM_CONTROL_NO);
    }

    @Test
    void putNonExistingInvoiceMasterDetails() throws Exception {
        int databaseSizeBeforeUpdate = invoiceMasterDetailsRepository.findAll().collectList().block().size();
        invoiceMasterDetails.setInvoiceId(count.incrementAndGet());

        // Create the InvoiceMasterDetails
        InvoiceMasterDetailsDTO invoiceMasterDetailsDTO = invoiceMasterDetailsMapper.toDto(invoiceMasterDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, invoiceMasterDetailsDTO.getInvoiceId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoiceMasterDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the InvoiceMasterDetails in the database
        List<InvoiceMasterDetails> invoiceMasterDetailsList = invoiceMasterDetailsRepository.findAll().collectList().block();
        assertThat(invoiceMasterDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchInvoiceMasterDetails() throws Exception {
        int databaseSizeBeforeUpdate = invoiceMasterDetailsRepository.findAll().collectList().block().size();
        invoiceMasterDetails.setInvoiceId(count.incrementAndGet());

        // Create the InvoiceMasterDetails
        InvoiceMasterDetailsDTO invoiceMasterDetailsDTO = invoiceMasterDetailsMapper.toDto(invoiceMasterDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoiceMasterDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the InvoiceMasterDetails in the database
        List<InvoiceMasterDetails> invoiceMasterDetailsList = invoiceMasterDetailsRepository.findAll().collectList().block();
        assertThat(invoiceMasterDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamInvoiceMasterDetails() throws Exception {
        int databaseSizeBeforeUpdate = invoiceMasterDetailsRepository.findAll().collectList().block().size();
        invoiceMasterDetails.setInvoiceId(count.incrementAndGet());

        // Create the InvoiceMasterDetails
        InvoiceMasterDetailsDTO invoiceMasterDetailsDTO = invoiceMasterDetailsMapper.toDto(invoiceMasterDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoiceMasterDetailsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the InvoiceMasterDetails in the database
        List<InvoiceMasterDetails> invoiceMasterDetailsList = invoiceMasterDetailsRepository.findAll().collectList().block();
        assertThat(invoiceMasterDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateInvoiceMasterDetailsWithPatch() throws Exception {
        // Initialize the database
        invoiceMasterDetailsRepository.save(invoiceMasterDetails).block();

        int databaseSizeBeforeUpdate = invoiceMasterDetailsRepository.findAll().collectList().block().size();

        // Update the invoiceMasterDetails using partial update
        InvoiceMasterDetails partialUpdatedInvoiceMasterDetails = new InvoiceMasterDetails();
        partialUpdatedInvoiceMasterDetails.setInvoiceId(invoiceMasterDetails.getInvoiceId());

        partialUpdatedInvoiceMasterDetails
            .invoiceToEntity(UPDATED_INVOICE_TO_ENTITY)
            .invoiceToPayorId(UPDATED_INVOICE_TO_PAYOR_ID)
            .invoiceToPayorName(UPDATED_INVOICE_TO_PAYOR_NAME)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .chargedAmountTotal(UPDATED_CHARGED_AMOUNT_TOTAL)
            .allowAmountTotal(UPDATED_ALLOW_AMOUNT_TOTAL)
            .taxAmountTotal(UPDATED_TAX_AMOUNT_TOTAL)
            .status(UPDATED_STATUS)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .invoiceMasterDetailsUuid(UPDATED_INVOICE_MASTER_DETAILS_UUID)
            .primarySubmissionMasterId(UPDATED_PRIMARY_SUBMISSION_MASTER_ID)
            .claimControlNo(UPDATED_CLAIM_CONTROL_NO);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedInvoiceMasterDetails.getInvoiceId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedInvoiceMasterDetails))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the InvoiceMasterDetails in the database
        List<InvoiceMasterDetails> invoiceMasterDetailsList = invoiceMasterDetailsRepository.findAll().collectList().block();
        assertThat(invoiceMasterDetailsList).hasSize(databaseSizeBeforeUpdate);
        InvoiceMasterDetails testInvoiceMasterDetails = invoiceMasterDetailsList.get(invoiceMasterDetailsList.size() - 1);
        assertThat(testInvoiceMasterDetails.getInvoiceNo()).isEqualTo(DEFAULT_INVOICE_NO);
        assertThat(testInvoiceMasterDetails.getInvoiceDate()).isEqualTo(DEFAULT_INVOICE_DATE);
        assertThat(testInvoiceMasterDetails.getInvoiceToEntity()).isEqualTo(UPDATED_INVOICE_TO_ENTITY);
        assertThat(testInvoiceMasterDetails.getInvoiceToPayorId()).isEqualTo(UPDATED_INVOICE_TO_PAYOR_ID);
        assertThat(testInvoiceMasterDetails.getInvoiceToPayorName()).isEqualTo(UPDATED_INVOICE_TO_PAYOR_NAME);
        assertThat(testInvoiceMasterDetails.getSalesOrderId()).isEqualTo(DEFAULT_SALES_ORDER_ID);
        assertThat(testInvoiceMasterDetails.getSalesOrderNo()).isEqualTo(DEFAULT_SALES_ORDER_NO);
        assertThat(testInvoiceMasterDetails.getPatientId()).isEqualTo(DEFAULT_PATIENT_ID);
        assertThat(testInvoiceMasterDetails.getPatientFirstName()).isEqualTo(UPDATED_PATIENT_FIRST_NAME);
        assertThat(testInvoiceMasterDetails.getPatientMiddleName()).isEqualTo(DEFAULT_PATIENT_MIDDLE_NAME);
        assertThat(testInvoiceMasterDetails.getPatientLastName()).isEqualTo(DEFAULT_PATIENT_LAST_NAME);
        assertThat(testInvoiceMasterDetails.getItemQtyTotal()).isEqualTo(DEFAULT_ITEM_QTY_TOTAL);
        assertThat(testInvoiceMasterDetails.getChargedAmountTotal()).isEqualTo(UPDATED_CHARGED_AMOUNT_TOTAL);
        assertThat(testInvoiceMasterDetails.getAllowAmountTotal()).isEqualTo(UPDATED_ALLOW_AMOUNT_TOTAL);
        assertThat(testInvoiceMasterDetails.getPaymentAmountTotal()).isEqualTo(DEFAULT_PAYMENT_AMOUNT_TOTAL);
        assertThat(testInvoiceMasterDetails.getTaxAmountTotal()).isEqualTo(UPDATED_TAX_AMOUNT_TOTAL);
        assertThat(testInvoiceMasterDetails.getAdjAmountTotal()).isEqualTo(DEFAULT_ADJ_AMOUNT_TOTAL);
        assertThat(testInvoiceMasterDetails.getBalanceAmountTotal()).isEqualTo(DEFAULT_BALANCE_AMOUNT_TOTAL);
        assertThat(testInvoiceMasterDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testInvoiceMasterDetails.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testInvoiceMasterDetails.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testInvoiceMasterDetails.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testInvoiceMasterDetails.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testInvoiceMasterDetails.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testInvoiceMasterDetails.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testInvoiceMasterDetails.getInvoiceMasterDetailsUuid()).isEqualTo(UPDATED_INVOICE_MASTER_DETAILS_UUID);
        assertThat(testInvoiceMasterDetails.getInvoiceStatus()).isEqualTo(DEFAULT_INVOICE_STATUS);
        assertThat(testInvoiceMasterDetails.getPrimarySubmissionMasterId()).isEqualTo(UPDATED_PRIMARY_SUBMISSION_MASTER_ID);
        assertThat(testInvoiceMasterDetails.getClaimControlNo()).isEqualTo(UPDATED_CLAIM_CONTROL_NO);
    }

    @Test
    void fullUpdateInvoiceMasterDetailsWithPatch() throws Exception {
        // Initialize the database
        invoiceMasterDetailsRepository.save(invoiceMasterDetails).block();

        int databaseSizeBeforeUpdate = invoiceMasterDetailsRepository.findAll().collectList().block().size();

        // Update the invoiceMasterDetails using partial update
        InvoiceMasterDetails partialUpdatedInvoiceMasterDetails = new InvoiceMasterDetails();
        partialUpdatedInvoiceMasterDetails.setInvoiceId(invoiceMasterDetails.getInvoiceId());

        partialUpdatedInvoiceMasterDetails
            .invoiceNo(UPDATED_INVOICE_NO)
            .invoiceDate(UPDATED_INVOICE_DATE)
            .invoiceToEntity(UPDATED_INVOICE_TO_ENTITY)
            .invoiceToPayorId(UPDATED_INVOICE_TO_PAYOR_ID)
            .invoiceToPayorName(UPDATED_INVOICE_TO_PAYOR_NAME)
            .salesOrderId(UPDATED_SALES_ORDER_ID)
            .salesOrderNo(UPDATED_SALES_ORDER_NO)
            .patientId(UPDATED_PATIENT_ID)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientMiddleName(UPDATED_PATIENT_MIDDLE_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .itemQtyTotal(UPDATED_ITEM_QTY_TOTAL)
            .chargedAmountTotal(UPDATED_CHARGED_AMOUNT_TOTAL)
            .allowAmountTotal(UPDATED_ALLOW_AMOUNT_TOTAL)
            .paymentAmountTotal(UPDATED_PAYMENT_AMOUNT_TOTAL)
            .taxAmountTotal(UPDATED_TAX_AMOUNT_TOTAL)
            .adjAmountTotal(UPDATED_ADJ_AMOUNT_TOTAL)
            .balanceAmountTotal(UPDATED_BALANCE_AMOUNT_TOTAL)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .invoiceMasterDetailsUuid(UPDATED_INVOICE_MASTER_DETAILS_UUID)
            .invoiceStatus(UPDATED_INVOICE_STATUS)
            .primarySubmissionMasterId(UPDATED_PRIMARY_SUBMISSION_MASTER_ID)
            .claimControlNo(UPDATED_CLAIM_CONTROL_NO);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedInvoiceMasterDetails.getInvoiceId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedInvoiceMasterDetails))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the InvoiceMasterDetails in the database
        List<InvoiceMasterDetails> invoiceMasterDetailsList = invoiceMasterDetailsRepository.findAll().collectList().block();
        assertThat(invoiceMasterDetailsList).hasSize(databaseSizeBeforeUpdate);
        InvoiceMasterDetails testInvoiceMasterDetails = invoiceMasterDetailsList.get(invoiceMasterDetailsList.size() - 1);
        assertThat(testInvoiceMasterDetails.getInvoiceNo()).isEqualTo(UPDATED_INVOICE_NO);
        assertThat(testInvoiceMasterDetails.getInvoiceDate()).isEqualTo(UPDATED_INVOICE_DATE);
        assertThat(testInvoiceMasterDetails.getInvoiceToEntity()).isEqualTo(UPDATED_INVOICE_TO_ENTITY);
        assertThat(testInvoiceMasterDetails.getInvoiceToPayorId()).isEqualTo(UPDATED_INVOICE_TO_PAYOR_ID);
        assertThat(testInvoiceMasterDetails.getInvoiceToPayorName()).isEqualTo(UPDATED_INVOICE_TO_PAYOR_NAME);
        assertThat(testInvoiceMasterDetails.getSalesOrderId()).isEqualTo(UPDATED_SALES_ORDER_ID);
        assertThat(testInvoiceMasterDetails.getSalesOrderNo()).isEqualTo(UPDATED_SALES_ORDER_NO);
        assertThat(testInvoiceMasterDetails.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testInvoiceMasterDetails.getPatientFirstName()).isEqualTo(UPDATED_PATIENT_FIRST_NAME);
        assertThat(testInvoiceMasterDetails.getPatientMiddleName()).isEqualTo(UPDATED_PATIENT_MIDDLE_NAME);
        assertThat(testInvoiceMasterDetails.getPatientLastName()).isEqualTo(UPDATED_PATIENT_LAST_NAME);
        assertThat(testInvoiceMasterDetails.getItemQtyTotal()).isEqualTo(UPDATED_ITEM_QTY_TOTAL);
        assertThat(testInvoiceMasterDetails.getChargedAmountTotal()).isEqualTo(UPDATED_CHARGED_AMOUNT_TOTAL);
        assertThat(testInvoiceMasterDetails.getAllowAmountTotal()).isEqualTo(UPDATED_ALLOW_AMOUNT_TOTAL);
        assertThat(testInvoiceMasterDetails.getPaymentAmountTotal()).isEqualTo(UPDATED_PAYMENT_AMOUNT_TOTAL);
        assertThat(testInvoiceMasterDetails.getTaxAmountTotal()).isEqualTo(UPDATED_TAX_AMOUNT_TOTAL);
        assertThat(testInvoiceMasterDetails.getAdjAmountTotal()).isEqualTo(UPDATED_ADJ_AMOUNT_TOTAL);
        assertThat(testInvoiceMasterDetails.getBalanceAmountTotal()).isEqualTo(UPDATED_BALANCE_AMOUNT_TOTAL);
        assertThat(testInvoiceMasterDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testInvoiceMasterDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testInvoiceMasterDetails.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testInvoiceMasterDetails.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testInvoiceMasterDetails.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testInvoiceMasterDetails.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testInvoiceMasterDetails.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testInvoiceMasterDetails.getInvoiceMasterDetailsUuid()).isEqualTo(UPDATED_INVOICE_MASTER_DETAILS_UUID);
        assertThat(testInvoiceMasterDetails.getInvoiceStatus()).isEqualTo(UPDATED_INVOICE_STATUS);
        assertThat(testInvoiceMasterDetails.getPrimarySubmissionMasterId()).isEqualTo(UPDATED_PRIMARY_SUBMISSION_MASTER_ID);
        assertThat(testInvoiceMasterDetails.getClaimControlNo()).isEqualTo(UPDATED_CLAIM_CONTROL_NO);
    }

    @Test
    void patchNonExistingInvoiceMasterDetails() throws Exception {
        int databaseSizeBeforeUpdate = invoiceMasterDetailsRepository.findAll().collectList().block().size();
        invoiceMasterDetails.setInvoiceId(count.incrementAndGet());

        // Create the InvoiceMasterDetails
        InvoiceMasterDetailsDTO invoiceMasterDetailsDTO = invoiceMasterDetailsMapper.toDto(invoiceMasterDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, invoiceMasterDetailsDTO.getInvoiceId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoiceMasterDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the InvoiceMasterDetails in the database
        List<InvoiceMasterDetails> invoiceMasterDetailsList = invoiceMasterDetailsRepository.findAll().collectList().block();
        assertThat(invoiceMasterDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchInvoiceMasterDetails() throws Exception {
        int databaseSizeBeforeUpdate = invoiceMasterDetailsRepository.findAll().collectList().block().size();
        invoiceMasterDetails.setInvoiceId(count.incrementAndGet());

        // Create the InvoiceMasterDetails
        InvoiceMasterDetailsDTO invoiceMasterDetailsDTO = invoiceMasterDetailsMapper.toDto(invoiceMasterDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoiceMasterDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the InvoiceMasterDetails in the database
        List<InvoiceMasterDetails> invoiceMasterDetailsList = invoiceMasterDetailsRepository.findAll().collectList().block();
        assertThat(invoiceMasterDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamInvoiceMasterDetails() throws Exception {
        int databaseSizeBeforeUpdate = invoiceMasterDetailsRepository.findAll().collectList().block().size();
        invoiceMasterDetails.setInvoiceId(count.incrementAndGet());

        // Create the InvoiceMasterDetails
        InvoiceMasterDetailsDTO invoiceMasterDetailsDTO = invoiceMasterDetailsMapper.toDto(invoiceMasterDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoiceMasterDetailsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the InvoiceMasterDetails in the database
        List<InvoiceMasterDetails> invoiceMasterDetailsList = invoiceMasterDetailsRepository.findAll().collectList().block();
        assertThat(invoiceMasterDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteInvoiceMasterDetails() {
        // Initialize the database
        invoiceMasterDetailsRepository.save(invoiceMasterDetails).block();

        int databaseSizeBeforeDelete = invoiceMasterDetailsRepository.findAll().collectList().block().size();

        // Delete the invoiceMasterDetails
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, invoiceMasterDetails.getInvoiceId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<InvoiceMasterDetails> invoiceMasterDetailsList = invoiceMasterDetailsRepository.findAll().collectList().block();
        assertThat(invoiceMasterDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
