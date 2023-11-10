package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.InvoiceMasterDetails;
import com.sunknowledge.dme.rcm.repository.InvoiceMasterDetailsRepository;
import com.sunknowledge.dme.rcm.service.dto.InvoiceMasterDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.InvoiceMasterDetailsMapper;
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
 * Integration tests for the {@link InvoiceMasterDetailsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
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
    private MockMvc restInvoiceMasterDetailsMockMvc;

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
            .invoiceStatus(DEFAULT_INVOICE_STATUS);
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
            .invoiceStatus(UPDATED_INVOICE_STATUS);
        return invoiceMasterDetails;
    }

    @BeforeEach
    public void initTest() {
        invoiceMasterDetails = createEntity(em);
    }

    @Test
    @Transactional
    void createInvoiceMasterDetails() throws Exception {
        int databaseSizeBeforeCreate = invoiceMasterDetailsRepository.findAll().size();
        // Create the InvoiceMasterDetails
        InvoiceMasterDetailsDTO invoiceMasterDetailsDTO = invoiceMasterDetailsMapper.toDto(invoiceMasterDetails);
        restInvoiceMasterDetailsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(invoiceMasterDetailsDTO))
            )
            .andExpect(status().isCreated());

        // Validate the InvoiceMasterDetails in the database
        List<InvoiceMasterDetails> invoiceMasterDetailsList = invoiceMasterDetailsRepository.findAll();
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
    }

    @Test
    @Transactional
    void createInvoiceMasterDetailsWithExistingId() throws Exception {
        // Create the InvoiceMasterDetails with an existing ID
        invoiceMasterDetails.setInvoiceId(1L);
        InvoiceMasterDetailsDTO invoiceMasterDetailsDTO = invoiceMasterDetailsMapper.toDto(invoiceMasterDetails);

        int databaseSizeBeforeCreate = invoiceMasterDetailsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restInvoiceMasterDetailsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(invoiceMasterDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InvoiceMasterDetails in the database
        List<InvoiceMasterDetails> invoiceMasterDetailsList = invoiceMasterDetailsRepository.findAll();
        assertThat(invoiceMasterDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllInvoiceMasterDetails() throws Exception {
        // Initialize the database
        invoiceMasterDetailsRepository.saveAndFlush(invoiceMasterDetails);

        // Get all the invoiceMasterDetailsList
        restInvoiceMasterDetailsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=invoiceId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].invoiceId").value(hasItem(invoiceMasterDetails.getInvoiceId().intValue())))
            .andExpect(jsonPath("$.[*].invoiceNo").value(hasItem(DEFAULT_INVOICE_NO)))
            .andExpect(jsonPath("$.[*].invoiceDate").value(hasItem(DEFAULT_INVOICE_DATE.toString())))
            .andExpect(jsonPath("$.[*].invoiceToEntity").value(hasItem(DEFAULT_INVOICE_TO_ENTITY)))
            .andExpect(jsonPath("$.[*].invoiceToPayorId").value(hasItem(DEFAULT_INVOICE_TO_PAYOR_ID.intValue())))
            .andExpect(jsonPath("$.[*].invoiceToPayorName").value(hasItem(DEFAULT_INVOICE_TO_PAYOR_NAME)))
            .andExpect(jsonPath("$.[*].salesOrderId").value(hasItem(DEFAULT_SALES_ORDER_ID.intValue())))
            .andExpect(jsonPath("$.[*].salesOrderNo").value(hasItem(DEFAULT_SALES_ORDER_NO)))
            .andExpect(jsonPath("$.[*].patientId").value(hasItem(DEFAULT_PATIENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].patientFirstName").value(hasItem(DEFAULT_PATIENT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].patientMiddleName").value(hasItem(DEFAULT_PATIENT_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].patientLastName").value(hasItem(DEFAULT_PATIENT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].itemQtyTotal").value(hasItem(DEFAULT_ITEM_QTY_TOTAL.intValue())))
            .andExpect(jsonPath("$.[*].chargedAmountTotal").value(hasItem(DEFAULT_CHARGED_AMOUNT_TOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].allowAmountTotal").value(hasItem(DEFAULT_ALLOW_AMOUNT_TOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].paymentAmountTotal").value(hasItem(DEFAULT_PAYMENT_AMOUNT_TOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].taxAmountTotal").value(hasItem(DEFAULT_TAX_AMOUNT_TOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].adjAmountTotal").value(hasItem(DEFAULT_ADJ_AMOUNT_TOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].balanceAmountTotal").value(hasItem(DEFAULT_BALANCE_AMOUNT_TOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].invoiceMasterDetailsUuid").value(hasItem(DEFAULT_INVOICE_MASTER_DETAILS_UUID.toString())))
            .andExpect(jsonPath("$.[*].invoiceStatus").value(hasItem(DEFAULT_INVOICE_STATUS)));
    }

    @Test
    @Transactional
    void getInvoiceMasterDetails() throws Exception {
        // Initialize the database
        invoiceMasterDetailsRepository.saveAndFlush(invoiceMasterDetails);

        // Get the invoiceMasterDetails
        restInvoiceMasterDetailsMockMvc
            .perform(get(ENTITY_API_URL_ID, invoiceMasterDetails.getInvoiceId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.invoiceId").value(invoiceMasterDetails.getInvoiceId().intValue()))
            .andExpect(jsonPath("$.invoiceNo").value(DEFAULT_INVOICE_NO))
            .andExpect(jsonPath("$.invoiceDate").value(DEFAULT_INVOICE_DATE.toString()))
            .andExpect(jsonPath("$.invoiceToEntity").value(DEFAULT_INVOICE_TO_ENTITY))
            .andExpect(jsonPath("$.invoiceToPayorId").value(DEFAULT_INVOICE_TO_PAYOR_ID.intValue()))
            .andExpect(jsonPath("$.invoiceToPayorName").value(DEFAULT_INVOICE_TO_PAYOR_NAME))
            .andExpect(jsonPath("$.salesOrderId").value(DEFAULT_SALES_ORDER_ID.intValue()))
            .andExpect(jsonPath("$.salesOrderNo").value(DEFAULT_SALES_ORDER_NO))
            .andExpect(jsonPath("$.patientId").value(DEFAULT_PATIENT_ID.intValue()))
            .andExpect(jsonPath("$.patientFirstName").value(DEFAULT_PATIENT_FIRST_NAME))
            .andExpect(jsonPath("$.patientMiddleName").value(DEFAULT_PATIENT_MIDDLE_NAME))
            .andExpect(jsonPath("$.patientLastName").value(DEFAULT_PATIENT_LAST_NAME))
            .andExpect(jsonPath("$.itemQtyTotal").value(DEFAULT_ITEM_QTY_TOTAL.intValue()))
            .andExpect(jsonPath("$.chargedAmountTotal").value(DEFAULT_CHARGED_AMOUNT_TOTAL.doubleValue()))
            .andExpect(jsonPath("$.allowAmountTotal").value(DEFAULT_ALLOW_AMOUNT_TOTAL.doubleValue()))
            .andExpect(jsonPath("$.paymentAmountTotal").value(DEFAULT_PAYMENT_AMOUNT_TOTAL.doubleValue()))
            .andExpect(jsonPath("$.taxAmountTotal").value(DEFAULT_TAX_AMOUNT_TOTAL.doubleValue()))
            .andExpect(jsonPath("$.adjAmountTotal").value(DEFAULT_ADJ_AMOUNT_TOTAL.doubleValue()))
            .andExpect(jsonPath("$.balanceAmountTotal").value(DEFAULT_BALANCE_AMOUNT_TOTAL.doubleValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.invoiceMasterDetailsUuid").value(DEFAULT_INVOICE_MASTER_DETAILS_UUID.toString()))
            .andExpect(jsonPath("$.invoiceStatus").value(DEFAULT_INVOICE_STATUS));
    }

    @Test
    @Transactional
    void getNonExistingInvoiceMasterDetails() throws Exception {
        // Get the invoiceMasterDetails
        restInvoiceMasterDetailsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewInvoiceMasterDetails() throws Exception {
        // Initialize the database
        invoiceMasterDetailsRepository.saveAndFlush(invoiceMasterDetails);

        int databaseSizeBeforeUpdate = invoiceMasterDetailsRepository.findAll().size();

        // Update the invoiceMasterDetails
        InvoiceMasterDetails updatedInvoiceMasterDetails = invoiceMasterDetailsRepository
            .findById(invoiceMasterDetails.getInvoiceId())
            .get();
        // Disconnect from session so that the updates on updatedInvoiceMasterDetails are not directly saved in db
        em.detach(updatedInvoiceMasterDetails);
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
            .invoiceStatus(UPDATED_INVOICE_STATUS);
        InvoiceMasterDetailsDTO invoiceMasterDetailsDTO = invoiceMasterDetailsMapper.toDto(updatedInvoiceMasterDetails);

        restInvoiceMasterDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, invoiceMasterDetailsDTO.getInvoiceId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(invoiceMasterDetailsDTO))
            )
            .andExpect(status().isOk());

        // Validate the InvoiceMasterDetails in the database
        List<InvoiceMasterDetails> invoiceMasterDetailsList = invoiceMasterDetailsRepository.findAll();
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
    }

    @Test
    @Transactional
    void putNonExistingInvoiceMasterDetails() throws Exception {
        int databaseSizeBeforeUpdate = invoiceMasterDetailsRepository.findAll().size();
        invoiceMasterDetails.setInvoiceId(count.incrementAndGet());

        // Create the InvoiceMasterDetails
        InvoiceMasterDetailsDTO invoiceMasterDetailsDTO = invoiceMasterDetailsMapper.toDto(invoiceMasterDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInvoiceMasterDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, invoiceMasterDetailsDTO.getInvoiceId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(invoiceMasterDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InvoiceMasterDetails in the database
        List<InvoiceMasterDetails> invoiceMasterDetailsList = invoiceMasterDetailsRepository.findAll();
        assertThat(invoiceMasterDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchInvoiceMasterDetails() throws Exception {
        int databaseSizeBeforeUpdate = invoiceMasterDetailsRepository.findAll().size();
        invoiceMasterDetails.setInvoiceId(count.incrementAndGet());

        // Create the InvoiceMasterDetails
        InvoiceMasterDetailsDTO invoiceMasterDetailsDTO = invoiceMasterDetailsMapper.toDto(invoiceMasterDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInvoiceMasterDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(invoiceMasterDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InvoiceMasterDetails in the database
        List<InvoiceMasterDetails> invoiceMasterDetailsList = invoiceMasterDetailsRepository.findAll();
        assertThat(invoiceMasterDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamInvoiceMasterDetails() throws Exception {
        int databaseSizeBeforeUpdate = invoiceMasterDetailsRepository.findAll().size();
        invoiceMasterDetails.setInvoiceId(count.incrementAndGet());

        // Create the InvoiceMasterDetails
        InvoiceMasterDetailsDTO invoiceMasterDetailsDTO = invoiceMasterDetailsMapper.toDto(invoiceMasterDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInvoiceMasterDetailsMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(invoiceMasterDetailsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the InvoiceMasterDetails in the database
        List<InvoiceMasterDetails> invoiceMasterDetailsList = invoiceMasterDetailsRepository.findAll();
        assertThat(invoiceMasterDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateInvoiceMasterDetailsWithPatch() throws Exception {
        // Initialize the database
        invoiceMasterDetailsRepository.saveAndFlush(invoiceMasterDetails);

        int databaseSizeBeforeUpdate = invoiceMasterDetailsRepository.findAll().size();

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
            .invoiceMasterDetailsUuid(UPDATED_INVOICE_MASTER_DETAILS_UUID);

        restInvoiceMasterDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInvoiceMasterDetails.getInvoiceId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInvoiceMasterDetails))
            )
            .andExpect(status().isOk());

        // Validate the InvoiceMasterDetails in the database
        List<InvoiceMasterDetails> invoiceMasterDetailsList = invoiceMasterDetailsRepository.findAll();
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
    }

    @Test
    @Transactional
    void fullUpdateInvoiceMasterDetailsWithPatch() throws Exception {
        // Initialize the database
        invoiceMasterDetailsRepository.saveAndFlush(invoiceMasterDetails);

        int databaseSizeBeforeUpdate = invoiceMasterDetailsRepository.findAll().size();

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
            .invoiceStatus(UPDATED_INVOICE_STATUS);

        restInvoiceMasterDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInvoiceMasterDetails.getInvoiceId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInvoiceMasterDetails))
            )
            .andExpect(status().isOk());

        // Validate the InvoiceMasterDetails in the database
        List<InvoiceMasterDetails> invoiceMasterDetailsList = invoiceMasterDetailsRepository.findAll();
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
    }

    @Test
    @Transactional
    void patchNonExistingInvoiceMasterDetails() throws Exception {
        int databaseSizeBeforeUpdate = invoiceMasterDetailsRepository.findAll().size();
        invoiceMasterDetails.setInvoiceId(count.incrementAndGet());

        // Create the InvoiceMasterDetails
        InvoiceMasterDetailsDTO invoiceMasterDetailsDTO = invoiceMasterDetailsMapper.toDto(invoiceMasterDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInvoiceMasterDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, invoiceMasterDetailsDTO.getInvoiceId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(invoiceMasterDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InvoiceMasterDetails in the database
        List<InvoiceMasterDetails> invoiceMasterDetailsList = invoiceMasterDetailsRepository.findAll();
        assertThat(invoiceMasterDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchInvoiceMasterDetails() throws Exception {
        int databaseSizeBeforeUpdate = invoiceMasterDetailsRepository.findAll().size();
        invoiceMasterDetails.setInvoiceId(count.incrementAndGet());

        // Create the InvoiceMasterDetails
        InvoiceMasterDetailsDTO invoiceMasterDetailsDTO = invoiceMasterDetailsMapper.toDto(invoiceMasterDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInvoiceMasterDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(invoiceMasterDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InvoiceMasterDetails in the database
        List<InvoiceMasterDetails> invoiceMasterDetailsList = invoiceMasterDetailsRepository.findAll();
        assertThat(invoiceMasterDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamInvoiceMasterDetails() throws Exception {
        int databaseSizeBeforeUpdate = invoiceMasterDetailsRepository.findAll().size();
        invoiceMasterDetails.setInvoiceId(count.incrementAndGet());

        // Create the InvoiceMasterDetails
        InvoiceMasterDetailsDTO invoiceMasterDetailsDTO = invoiceMasterDetailsMapper.toDto(invoiceMasterDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInvoiceMasterDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(invoiceMasterDetailsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the InvoiceMasterDetails in the database
        List<InvoiceMasterDetails> invoiceMasterDetailsList = invoiceMasterDetailsRepository.findAll();
        assertThat(invoiceMasterDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteInvoiceMasterDetails() throws Exception {
        // Initialize the database
        invoiceMasterDetailsRepository.saveAndFlush(invoiceMasterDetails);

        int databaseSizeBeforeDelete = invoiceMasterDetailsRepository.findAll().size();

        // Delete the invoiceMasterDetails
        restInvoiceMasterDetailsMockMvc
            .perform(delete(ENTITY_API_URL_ID, invoiceMasterDetails.getInvoiceId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InvoiceMasterDetails> invoiceMasterDetailsList = invoiceMasterDetailsRepository.findAll();
        assertThat(invoiceMasterDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
