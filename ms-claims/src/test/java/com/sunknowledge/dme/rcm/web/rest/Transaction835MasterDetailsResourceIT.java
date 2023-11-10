package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.Transaction835MasterDetails;
import com.sunknowledge.dme.rcm.repository.Transaction835MasterDetailsRepository;
import com.sunknowledge.dme.rcm.service.dto.Transaction835MasterDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.Transaction835MasterDetailsMapper;
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
 * Integration tests for the {@link Transaction835MasterDetailsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class Transaction835MasterDetailsResourceIT {

    private static final String DEFAULT_PATIENT_CONTROL_NO = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_CONTROL_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PAYOR_CLAIM_CONTROL_NO = "AAAAAAAAAA";
    private static final String UPDATED_PAYOR_CLAIM_CONTROL_NO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_SERVICE_DATE_FROM = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SERVICE_DATE_FROM = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_SERVICE_DATE_TO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SERVICE_DATE_TO = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_RECEIPT_ID = 1L;
    private static final Long UPDATED_RECEIPT_ID = 2L;

    private static final String DEFAULT_RECEIPT_NO = "AAAAAAAAAA";
    private static final String UPDATED_RECEIPT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_PROC_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_PROC_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_MODIFIERS = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_MODIFIERS = "BBBBBBBBBB";

    private static final Long DEFAULT_ITEM_QTY = 1L;
    private static final Long UPDATED_ITEM_QTY = 2L;

    private static final String DEFAULT_TRANSACTION_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSACTION_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_NOTES = "BBBBBBBBBB";

    private static final Double DEFAULT_TRANSACTION_AMOUNT = 1D;
    private static final Double UPDATED_TRANSACTION_AMOUNT = 2D;

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

    private static final UUID DEFAULT_EDI_835_TRANSACTION_MASTER_DETAILS_UUID = UUID.randomUUID();
    private static final UUID UPDATED_EDI_835_TRANSACTION_MASTER_DETAILS_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/transaction-835-master-details";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{transactionId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private Transaction835MasterDetailsRepository transaction835MasterDetailsRepository;

    @Autowired
    private Transaction835MasterDetailsMapper transaction835MasterDetailsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTransaction835MasterDetailsMockMvc;

    private Transaction835MasterDetails transaction835MasterDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Transaction835MasterDetails createEntity(EntityManager em) {
        Transaction835MasterDetails transaction835MasterDetails = new Transaction835MasterDetails()
            .patientControlNo(DEFAULT_PATIENT_CONTROL_NO)
            .payorClaimControlNo(DEFAULT_PAYOR_CLAIM_CONTROL_NO)
            .serviceDateFrom(DEFAULT_SERVICE_DATE_FROM)
            .serviceDateTo(DEFAULT_SERVICE_DATE_TO)
            .receiptId(DEFAULT_RECEIPT_ID)
            .receiptNo(DEFAULT_RECEIPT_NO)
            .itemProcCode(DEFAULT_ITEM_PROC_CODE)
            .itemModifiers(DEFAULT_ITEM_MODIFIERS)
            .itemQty(DEFAULT_ITEM_QTY)
            .transactionType(DEFAULT_TRANSACTION_TYPE)
            .transactionNotes(DEFAULT_TRANSACTION_NOTES)
            .transactionAmount(DEFAULT_TRANSACTION_AMOUNT)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .edi835TransactionMasterDetailsUuid(DEFAULT_EDI_835_TRANSACTION_MASTER_DETAILS_UUID);
        return transaction835MasterDetails;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Transaction835MasterDetails createUpdatedEntity(EntityManager em) {
        Transaction835MasterDetails transaction835MasterDetails = new Transaction835MasterDetails()
            .patientControlNo(UPDATED_PATIENT_CONTROL_NO)
            .payorClaimControlNo(UPDATED_PAYOR_CLAIM_CONTROL_NO)
            .serviceDateFrom(UPDATED_SERVICE_DATE_FROM)
            .serviceDateTo(UPDATED_SERVICE_DATE_TO)
            .receiptId(UPDATED_RECEIPT_ID)
            .receiptNo(UPDATED_RECEIPT_NO)
            .itemProcCode(UPDATED_ITEM_PROC_CODE)
            .itemModifiers(UPDATED_ITEM_MODIFIERS)
            .itemQty(UPDATED_ITEM_QTY)
            .transactionType(UPDATED_TRANSACTION_TYPE)
            .transactionNotes(UPDATED_TRANSACTION_NOTES)
            .transactionAmount(UPDATED_TRANSACTION_AMOUNT)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .edi835TransactionMasterDetailsUuid(UPDATED_EDI_835_TRANSACTION_MASTER_DETAILS_UUID);
        return transaction835MasterDetails;
    }

    @BeforeEach
    public void initTest() {
        transaction835MasterDetails = createEntity(em);
    }

    @Test
    @Transactional
    void createTransaction835MasterDetails() throws Exception {
        int databaseSizeBeforeCreate = transaction835MasterDetailsRepository.findAll().size();
        // Create the Transaction835MasterDetails
        Transaction835MasterDetailsDTO transaction835MasterDetailsDTO = transaction835MasterDetailsMapper.toDto(
            transaction835MasterDetails
        );
        restTransaction835MasterDetailsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(transaction835MasterDetailsDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Transaction835MasterDetails in the database
        List<Transaction835MasterDetails> transaction835MasterDetailsList = transaction835MasterDetailsRepository.findAll();
        assertThat(transaction835MasterDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        Transaction835MasterDetails testTransaction835MasterDetails = transaction835MasterDetailsList.get(
            transaction835MasterDetailsList.size() - 1
        );
        assertThat(testTransaction835MasterDetails.getPatientControlNo()).isEqualTo(DEFAULT_PATIENT_CONTROL_NO);
        assertThat(testTransaction835MasterDetails.getPayorClaimControlNo()).isEqualTo(DEFAULT_PAYOR_CLAIM_CONTROL_NO);
        assertThat(testTransaction835MasterDetails.getServiceDateFrom()).isEqualTo(DEFAULT_SERVICE_DATE_FROM);
        assertThat(testTransaction835MasterDetails.getServiceDateTo()).isEqualTo(DEFAULT_SERVICE_DATE_TO);
        assertThat(testTransaction835MasterDetails.getReceiptId()).isEqualTo(DEFAULT_RECEIPT_ID);
        assertThat(testTransaction835MasterDetails.getReceiptNo()).isEqualTo(DEFAULT_RECEIPT_NO);
        assertThat(testTransaction835MasterDetails.getItemProcCode()).isEqualTo(DEFAULT_ITEM_PROC_CODE);
        assertThat(testTransaction835MasterDetails.getItemModifiers()).isEqualTo(DEFAULT_ITEM_MODIFIERS);
        assertThat(testTransaction835MasterDetails.getItemQty()).isEqualTo(DEFAULT_ITEM_QTY);
        assertThat(testTransaction835MasterDetails.getTransactionType()).isEqualTo(DEFAULT_TRANSACTION_TYPE);
        assertThat(testTransaction835MasterDetails.getTransactionNotes()).isEqualTo(DEFAULT_TRANSACTION_NOTES);
        assertThat(testTransaction835MasterDetails.getTransactionAmount()).isEqualTo(DEFAULT_TRANSACTION_AMOUNT);
        assertThat(testTransaction835MasterDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testTransaction835MasterDetails.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testTransaction835MasterDetails.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testTransaction835MasterDetails.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testTransaction835MasterDetails.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testTransaction835MasterDetails.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testTransaction835MasterDetails.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testTransaction835MasterDetails.getEdi835TransactionMasterDetailsUuid())
            .isEqualTo(DEFAULT_EDI_835_TRANSACTION_MASTER_DETAILS_UUID);
    }

    @Test
    @Transactional
    void createTransaction835MasterDetailsWithExistingId() throws Exception {
        // Create the Transaction835MasterDetails with an existing ID
        transaction835MasterDetails.setTransactionId(1L);
        Transaction835MasterDetailsDTO transaction835MasterDetailsDTO = transaction835MasterDetailsMapper.toDto(
            transaction835MasterDetails
        );

        int databaseSizeBeforeCreate = transaction835MasterDetailsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTransaction835MasterDetailsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(transaction835MasterDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Transaction835MasterDetails in the database
        List<Transaction835MasterDetails> transaction835MasterDetailsList = transaction835MasterDetailsRepository.findAll();
        assertThat(transaction835MasterDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTransaction835MasterDetails() throws Exception {
        // Initialize the database
        transaction835MasterDetailsRepository.saveAndFlush(transaction835MasterDetails);

        // Get all the transaction835MasterDetailsList
        restTransaction835MasterDetailsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=transactionId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].transactionId").value(hasItem(transaction835MasterDetails.getTransactionId().intValue())))
            .andExpect(jsonPath("$.[*].patientControlNo").value(hasItem(DEFAULT_PATIENT_CONTROL_NO)))
            .andExpect(jsonPath("$.[*].payorClaimControlNo").value(hasItem(DEFAULT_PAYOR_CLAIM_CONTROL_NO)))
            .andExpect(jsonPath("$.[*].serviceDateFrom").value(hasItem(DEFAULT_SERVICE_DATE_FROM.toString())))
            .andExpect(jsonPath("$.[*].serviceDateTo").value(hasItem(DEFAULT_SERVICE_DATE_TO.toString())))
            .andExpect(jsonPath("$.[*].receiptId").value(hasItem(DEFAULT_RECEIPT_ID.intValue())))
            .andExpect(jsonPath("$.[*].receiptNo").value(hasItem(DEFAULT_RECEIPT_NO)))
            .andExpect(jsonPath("$.[*].itemProcCode").value(hasItem(DEFAULT_ITEM_PROC_CODE)))
            .andExpect(jsonPath("$.[*].itemModifiers").value(hasItem(DEFAULT_ITEM_MODIFIERS)))
            .andExpect(jsonPath("$.[*].itemQty").value(hasItem(DEFAULT_ITEM_QTY.intValue())))
            .andExpect(jsonPath("$.[*].transactionType").value(hasItem(DEFAULT_TRANSACTION_TYPE)))
            .andExpect(jsonPath("$.[*].transactionNotes").value(hasItem(DEFAULT_TRANSACTION_NOTES)))
            .andExpect(jsonPath("$.[*].transactionAmount").value(hasItem(DEFAULT_TRANSACTION_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(
                jsonPath("$.[*].edi835TransactionMasterDetailsUuid")
                    .value(hasItem(DEFAULT_EDI_835_TRANSACTION_MASTER_DETAILS_UUID.toString()))
            );
    }

    @Test
    @Transactional
    void getTransaction835MasterDetails() throws Exception {
        // Initialize the database
        transaction835MasterDetailsRepository.saveAndFlush(transaction835MasterDetails);

        // Get the transaction835MasterDetails
        restTransaction835MasterDetailsMockMvc
            .perform(get(ENTITY_API_URL_ID, transaction835MasterDetails.getTransactionId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.transactionId").value(transaction835MasterDetails.getTransactionId().intValue()))
            .andExpect(jsonPath("$.patientControlNo").value(DEFAULT_PATIENT_CONTROL_NO))
            .andExpect(jsonPath("$.payorClaimControlNo").value(DEFAULT_PAYOR_CLAIM_CONTROL_NO))
            .andExpect(jsonPath("$.serviceDateFrom").value(DEFAULT_SERVICE_DATE_FROM.toString()))
            .andExpect(jsonPath("$.serviceDateTo").value(DEFAULT_SERVICE_DATE_TO.toString()))
            .andExpect(jsonPath("$.receiptId").value(DEFAULT_RECEIPT_ID.intValue()))
            .andExpect(jsonPath("$.receiptNo").value(DEFAULT_RECEIPT_NO))
            .andExpect(jsonPath("$.itemProcCode").value(DEFAULT_ITEM_PROC_CODE))
            .andExpect(jsonPath("$.itemModifiers").value(DEFAULT_ITEM_MODIFIERS))
            .andExpect(jsonPath("$.itemQty").value(DEFAULT_ITEM_QTY.intValue()))
            .andExpect(jsonPath("$.transactionType").value(DEFAULT_TRANSACTION_TYPE))
            .andExpect(jsonPath("$.transactionNotes").value(DEFAULT_TRANSACTION_NOTES))
            .andExpect(jsonPath("$.transactionAmount").value(DEFAULT_TRANSACTION_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.edi835TransactionMasterDetailsUuid").value(DEFAULT_EDI_835_TRANSACTION_MASTER_DETAILS_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingTransaction835MasterDetails() throws Exception {
        // Get the transaction835MasterDetails
        restTransaction835MasterDetailsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTransaction835MasterDetails() throws Exception {
        // Initialize the database
        transaction835MasterDetailsRepository.saveAndFlush(transaction835MasterDetails);

        int databaseSizeBeforeUpdate = transaction835MasterDetailsRepository.findAll().size();

        // Update the transaction835MasterDetails
        Transaction835MasterDetails updatedTransaction835MasterDetails = transaction835MasterDetailsRepository
            .findById(transaction835MasterDetails.getTransactionId())
            .get();
        // Disconnect from session so that the updates on updatedTransaction835MasterDetails are not directly saved in db
        em.detach(updatedTransaction835MasterDetails);
        updatedTransaction835MasterDetails
            .patientControlNo(UPDATED_PATIENT_CONTROL_NO)
            .payorClaimControlNo(UPDATED_PAYOR_CLAIM_CONTROL_NO)
            .serviceDateFrom(UPDATED_SERVICE_DATE_FROM)
            .serviceDateTo(UPDATED_SERVICE_DATE_TO)
            .receiptId(UPDATED_RECEIPT_ID)
            .receiptNo(UPDATED_RECEIPT_NO)
            .itemProcCode(UPDATED_ITEM_PROC_CODE)
            .itemModifiers(UPDATED_ITEM_MODIFIERS)
            .itemQty(UPDATED_ITEM_QTY)
            .transactionType(UPDATED_TRANSACTION_TYPE)
            .transactionNotes(UPDATED_TRANSACTION_NOTES)
            .transactionAmount(UPDATED_TRANSACTION_AMOUNT)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .edi835TransactionMasterDetailsUuid(UPDATED_EDI_835_TRANSACTION_MASTER_DETAILS_UUID);
        Transaction835MasterDetailsDTO transaction835MasterDetailsDTO = transaction835MasterDetailsMapper.toDto(
            updatedTransaction835MasterDetails
        );

        restTransaction835MasterDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, transaction835MasterDetailsDTO.getTransactionId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(transaction835MasterDetailsDTO))
            )
            .andExpect(status().isOk());

        // Validate the Transaction835MasterDetails in the database
        List<Transaction835MasterDetails> transaction835MasterDetailsList = transaction835MasterDetailsRepository.findAll();
        assertThat(transaction835MasterDetailsList).hasSize(databaseSizeBeforeUpdate);
        Transaction835MasterDetails testTransaction835MasterDetails = transaction835MasterDetailsList.get(
            transaction835MasterDetailsList.size() - 1
        );
        assertThat(testTransaction835MasterDetails.getPatientControlNo()).isEqualTo(UPDATED_PATIENT_CONTROL_NO);
        assertThat(testTransaction835MasterDetails.getPayorClaimControlNo()).isEqualTo(UPDATED_PAYOR_CLAIM_CONTROL_NO);
        assertThat(testTransaction835MasterDetails.getServiceDateFrom()).isEqualTo(UPDATED_SERVICE_DATE_FROM);
        assertThat(testTransaction835MasterDetails.getServiceDateTo()).isEqualTo(UPDATED_SERVICE_DATE_TO);
        assertThat(testTransaction835MasterDetails.getReceiptId()).isEqualTo(UPDATED_RECEIPT_ID);
        assertThat(testTransaction835MasterDetails.getReceiptNo()).isEqualTo(UPDATED_RECEIPT_NO);
        assertThat(testTransaction835MasterDetails.getItemProcCode()).isEqualTo(UPDATED_ITEM_PROC_CODE);
        assertThat(testTransaction835MasterDetails.getItemModifiers()).isEqualTo(UPDATED_ITEM_MODIFIERS);
        assertThat(testTransaction835MasterDetails.getItemQty()).isEqualTo(UPDATED_ITEM_QTY);
        assertThat(testTransaction835MasterDetails.getTransactionType()).isEqualTo(UPDATED_TRANSACTION_TYPE);
        assertThat(testTransaction835MasterDetails.getTransactionNotes()).isEqualTo(UPDATED_TRANSACTION_NOTES);
        assertThat(testTransaction835MasterDetails.getTransactionAmount()).isEqualTo(UPDATED_TRANSACTION_AMOUNT);
        assertThat(testTransaction835MasterDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testTransaction835MasterDetails.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testTransaction835MasterDetails.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testTransaction835MasterDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testTransaction835MasterDetails.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testTransaction835MasterDetails.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testTransaction835MasterDetails.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testTransaction835MasterDetails.getEdi835TransactionMasterDetailsUuid())
            .isEqualTo(UPDATED_EDI_835_TRANSACTION_MASTER_DETAILS_UUID);
    }

    @Test
    @Transactional
    void putNonExistingTransaction835MasterDetails() throws Exception {
        int databaseSizeBeforeUpdate = transaction835MasterDetailsRepository.findAll().size();
        transaction835MasterDetails.setTransactionId(count.incrementAndGet());

        // Create the Transaction835MasterDetails
        Transaction835MasterDetailsDTO transaction835MasterDetailsDTO = transaction835MasterDetailsMapper.toDto(
            transaction835MasterDetails
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTransaction835MasterDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, transaction835MasterDetailsDTO.getTransactionId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(transaction835MasterDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Transaction835MasterDetails in the database
        List<Transaction835MasterDetails> transaction835MasterDetailsList = transaction835MasterDetailsRepository.findAll();
        assertThat(transaction835MasterDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTransaction835MasterDetails() throws Exception {
        int databaseSizeBeforeUpdate = transaction835MasterDetailsRepository.findAll().size();
        transaction835MasterDetails.setTransactionId(count.incrementAndGet());

        // Create the Transaction835MasterDetails
        Transaction835MasterDetailsDTO transaction835MasterDetailsDTO = transaction835MasterDetailsMapper.toDto(
            transaction835MasterDetails
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTransaction835MasterDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(transaction835MasterDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Transaction835MasterDetails in the database
        List<Transaction835MasterDetails> transaction835MasterDetailsList = transaction835MasterDetailsRepository.findAll();
        assertThat(transaction835MasterDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTransaction835MasterDetails() throws Exception {
        int databaseSizeBeforeUpdate = transaction835MasterDetailsRepository.findAll().size();
        transaction835MasterDetails.setTransactionId(count.incrementAndGet());

        // Create the Transaction835MasterDetails
        Transaction835MasterDetailsDTO transaction835MasterDetailsDTO = transaction835MasterDetailsMapper.toDto(
            transaction835MasterDetails
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTransaction835MasterDetailsMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(transaction835MasterDetailsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Transaction835MasterDetails in the database
        List<Transaction835MasterDetails> transaction835MasterDetailsList = transaction835MasterDetailsRepository.findAll();
        assertThat(transaction835MasterDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTransaction835MasterDetailsWithPatch() throws Exception {
        // Initialize the database
        transaction835MasterDetailsRepository.saveAndFlush(transaction835MasterDetails);

        int databaseSizeBeforeUpdate = transaction835MasterDetailsRepository.findAll().size();

        // Update the transaction835MasterDetails using partial update
        Transaction835MasterDetails partialUpdatedTransaction835MasterDetails = new Transaction835MasterDetails();
        partialUpdatedTransaction835MasterDetails.setTransactionId(transaction835MasterDetails.getTransactionId());

        partialUpdatedTransaction835MasterDetails
            .patientControlNo(UPDATED_PATIENT_CONTROL_NO)
            .payorClaimControlNo(UPDATED_PAYOR_CLAIM_CONTROL_NO)
            .serviceDateFrom(UPDATED_SERVICE_DATE_FROM)
            .receiptNo(UPDATED_RECEIPT_NO)
            .itemProcCode(UPDATED_ITEM_PROC_CODE)
            .itemQty(UPDATED_ITEM_QTY)
            .transactionNotes(UPDATED_TRANSACTION_NOTES)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .edi835TransactionMasterDetailsUuid(UPDATED_EDI_835_TRANSACTION_MASTER_DETAILS_UUID);

        restTransaction835MasterDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTransaction835MasterDetails.getTransactionId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTransaction835MasterDetails))
            )
            .andExpect(status().isOk());

        // Validate the Transaction835MasterDetails in the database
        List<Transaction835MasterDetails> transaction835MasterDetailsList = transaction835MasterDetailsRepository.findAll();
        assertThat(transaction835MasterDetailsList).hasSize(databaseSizeBeforeUpdate);
        Transaction835MasterDetails testTransaction835MasterDetails = transaction835MasterDetailsList.get(
            transaction835MasterDetailsList.size() - 1
        );
        assertThat(testTransaction835MasterDetails.getPatientControlNo()).isEqualTo(UPDATED_PATIENT_CONTROL_NO);
        assertThat(testTransaction835MasterDetails.getPayorClaimControlNo()).isEqualTo(UPDATED_PAYOR_CLAIM_CONTROL_NO);
        assertThat(testTransaction835MasterDetails.getServiceDateFrom()).isEqualTo(UPDATED_SERVICE_DATE_FROM);
        assertThat(testTransaction835MasterDetails.getServiceDateTo()).isEqualTo(DEFAULT_SERVICE_DATE_TO);
        assertThat(testTransaction835MasterDetails.getReceiptId()).isEqualTo(DEFAULT_RECEIPT_ID);
        assertThat(testTransaction835MasterDetails.getReceiptNo()).isEqualTo(UPDATED_RECEIPT_NO);
        assertThat(testTransaction835MasterDetails.getItemProcCode()).isEqualTo(UPDATED_ITEM_PROC_CODE);
        assertThat(testTransaction835MasterDetails.getItemModifiers()).isEqualTo(DEFAULT_ITEM_MODIFIERS);
        assertThat(testTransaction835MasterDetails.getItemQty()).isEqualTo(UPDATED_ITEM_QTY);
        assertThat(testTransaction835MasterDetails.getTransactionType()).isEqualTo(DEFAULT_TRANSACTION_TYPE);
        assertThat(testTransaction835MasterDetails.getTransactionNotes()).isEqualTo(UPDATED_TRANSACTION_NOTES);
        assertThat(testTransaction835MasterDetails.getTransactionAmount()).isEqualTo(DEFAULT_TRANSACTION_AMOUNT);
        assertThat(testTransaction835MasterDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testTransaction835MasterDetails.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testTransaction835MasterDetails.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testTransaction835MasterDetails.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testTransaction835MasterDetails.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testTransaction835MasterDetails.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testTransaction835MasterDetails.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testTransaction835MasterDetails.getEdi835TransactionMasterDetailsUuid())
            .isEqualTo(UPDATED_EDI_835_TRANSACTION_MASTER_DETAILS_UUID);
    }

    @Test
    @Transactional
    void fullUpdateTransaction835MasterDetailsWithPatch() throws Exception {
        // Initialize the database
        transaction835MasterDetailsRepository.saveAndFlush(transaction835MasterDetails);

        int databaseSizeBeforeUpdate = transaction835MasterDetailsRepository.findAll().size();

        // Update the transaction835MasterDetails using partial update
        Transaction835MasterDetails partialUpdatedTransaction835MasterDetails = new Transaction835MasterDetails();
        partialUpdatedTransaction835MasterDetails.setTransactionId(transaction835MasterDetails.getTransactionId());

        partialUpdatedTransaction835MasterDetails
            .patientControlNo(UPDATED_PATIENT_CONTROL_NO)
            .payorClaimControlNo(UPDATED_PAYOR_CLAIM_CONTROL_NO)
            .serviceDateFrom(UPDATED_SERVICE_DATE_FROM)
            .serviceDateTo(UPDATED_SERVICE_DATE_TO)
            .receiptId(UPDATED_RECEIPT_ID)
            .receiptNo(UPDATED_RECEIPT_NO)
            .itemProcCode(UPDATED_ITEM_PROC_CODE)
            .itemModifiers(UPDATED_ITEM_MODIFIERS)
            .itemQty(UPDATED_ITEM_QTY)
            .transactionType(UPDATED_TRANSACTION_TYPE)
            .transactionNotes(UPDATED_TRANSACTION_NOTES)
            .transactionAmount(UPDATED_TRANSACTION_AMOUNT)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .edi835TransactionMasterDetailsUuid(UPDATED_EDI_835_TRANSACTION_MASTER_DETAILS_UUID);

        restTransaction835MasterDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTransaction835MasterDetails.getTransactionId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTransaction835MasterDetails))
            )
            .andExpect(status().isOk());

        // Validate the Transaction835MasterDetails in the database
        List<Transaction835MasterDetails> transaction835MasterDetailsList = transaction835MasterDetailsRepository.findAll();
        assertThat(transaction835MasterDetailsList).hasSize(databaseSizeBeforeUpdate);
        Transaction835MasterDetails testTransaction835MasterDetails = transaction835MasterDetailsList.get(
            transaction835MasterDetailsList.size() - 1
        );
        assertThat(testTransaction835MasterDetails.getPatientControlNo()).isEqualTo(UPDATED_PATIENT_CONTROL_NO);
        assertThat(testTransaction835MasterDetails.getPayorClaimControlNo()).isEqualTo(UPDATED_PAYOR_CLAIM_CONTROL_NO);
        assertThat(testTransaction835MasterDetails.getServiceDateFrom()).isEqualTo(UPDATED_SERVICE_DATE_FROM);
        assertThat(testTransaction835MasterDetails.getServiceDateTo()).isEqualTo(UPDATED_SERVICE_DATE_TO);
        assertThat(testTransaction835MasterDetails.getReceiptId()).isEqualTo(UPDATED_RECEIPT_ID);
        assertThat(testTransaction835MasterDetails.getReceiptNo()).isEqualTo(UPDATED_RECEIPT_NO);
        assertThat(testTransaction835MasterDetails.getItemProcCode()).isEqualTo(UPDATED_ITEM_PROC_CODE);
        assertThat(testTransaction835MasterDetails.getItemModifiers()).isEqualTo(UPDATED_ITEM_MODIFIERS);
        assertThat(testTransaction835MasterDetails.getItemQty()).isEqualTo(UPDATED_ITEM_QTY);
        assertThat(testTransaction835MasterDetails.getTransactionType()).isEqualTo(UPDATED_TRANSACTION_TYPE);
        assertThat(testTransaction835MasterDetails.getTransactionNotes()).isEqualTo(UPDATED_TRANSACTION_NOTES);
        assertThat(testTransaction835MasterDetails.getTransactionAmount()).isEqualTo(UPDATED_TRANSACTION_AMOUNT);
        assertThat(testTransaction835MasterDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testTransaction835MasterDetails.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testTransaction835MasterDetails.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testTransaction835MasterDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testTransaction835MasterDetails.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testTransaction835MasterDetails.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testTransaction835MasterDetails.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testTransaction835MasterDetails.getEdi835TransactionMasterDetailsUuid())
            .isEqualTo(UPDATED_EDI_835_TRANSACTION_MASTER_DETAILS_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingTransaction835MasterDetails() throws Exception {
        int databaseSizeBeforeUpdate = transaction835MasterDetailsRepository.findAll().size();
        transaction835MasterDetails.setTransactionId(count.incrementAndGet());

        // Create the Transaction835MasterDetails
        Transaction835MasterDetailsDTO transaction835MasterDetailsDTO = transaction835MasterDetailsMapper.toDto(
            transaction835MasterDetails
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTransaction835MasterDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, transaction835MasterDetailsDTO.getTransactionId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(transaction835MasterDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Transaction835MasterDetails in the database
        List<Transaction835MasterDetails> transaction835MasterDetailsList = transaction835MasterDetailsRepository.findAll();
        assertThat(transaction835MasterDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTransaction835MasterDetails() throws Exception {
        int databaseSizeBeforeUpdate = transaction835MasterDetailsRepository.findAll().size();
        transaction835MasterDetails.setTransactionId(count.incrementAndGet());

        // Create the Transaction835MasterDetails
        Transaction835MasterDetailsDTO transaction835MasterDetailsDTO = transaction835MasterDetailsMapper.toDto(
            transaction835MasterDetails
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTransaction835MasterDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(transaction835MasterDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Transaction835MasterDetails in the database
        List<Transaction835MasterDetails> transaction835MasterDetailsList = transaction835MasterDetailsRepository.findAll();
        assertThat(transaction835MasterDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTransaction835MasterDetails() throws Exception {
        int databaseSizeBeforeUpdate = transaction835MasterDetailsRepository.findAll().size();
        transaction835MasterDetails.setTransactionId(count.incrementAndGet());

        // Create the Transaction835MasterDetails
        Transaction835MasterDetailsDTO transaction835MasterDetailsDTO = transaction835MasterDetailsMapper.toDto(
            transaction835MasterDetails
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTransaction835MasterDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(transaction835MasterDetailsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Transaction835MasterDetails in the database
        List<Transaction835MasterDetails> transaction835MasterDetailsList = transaction835MasterDetailsRepository.findAll();
        assertThat(transaction835MasterDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTransaction835MasterDetails() throws Exception {
        // Initialize the database
        transaction835MasterDetailsRepository.saveAndFlush(transaction835MasterDetails);

        int databaseSizeBeforeDelete = transaction835MasterDetailsRepository.findAll().size();

        // Delete the transaction835MasterDetails
        restTransaction835MasterDetailsMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, transaction835MasterDetails.getTransactionId()).with(csrf()).accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Transaction835MasterDetails> transaction835MasterDetailsList = transaction835MasterDetailsRepository.findAll();
        assertThat(transaction835MasterDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
