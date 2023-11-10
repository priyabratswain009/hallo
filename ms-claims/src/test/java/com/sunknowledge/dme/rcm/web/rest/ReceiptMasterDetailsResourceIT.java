package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ReceiptMasterDetails;
import com.sunknowledge.dme.rcm.repository.ReceiptMasterDetailsRepository;
import com.sunknowledge.dme.rcm.service.dto.ReceiptMasterDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.ReceiptMasterDetailsMapper;
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
 * Integration tests for the {@link ReceiptMasterDetailsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ReceiptMasterDetailsResourceIT {

    private static final String DEFAULT_RECEIPT_NO = "AAAAAAAAAA";
    private static final String UPDATED_RECEIPT_NO = "BBBBBBBBBB";

    private static final Long DEFAULT_DEPOSIT_ID = 1L;
    private static final Long UPDATED_DEPOSIT_ID = 2L;

    private static final String DEFAULT_DEPOSIT_NO = "AAAAAAAAAA";
    private static final String UPDATED_DEPOSIT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_RECEIPT_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_RECEIPT_REFERENCE = "BBBBBBBBBB";

    private static final Double DEFAULT_RECEIPT_AMOUNT = 1D;
    private static final Double UPDATED_RECEIPT_AMOUNT = 2D;

    private static final String DEFAULT_PAYMENT_MODE = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_MODE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PAYMENT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PAYMENT_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_RECEIPT_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_RECEIPT_NOTES = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_RECEIPT_MASTER_DETAILS_UUID = UUID.randomUUID();
    private static final UUID UPDATED_RECEIPT_MASTER_DETAILS_UUID = UUID.randomUUID();

    private static final String DEFAULT_RECEIPT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_RECEIPT_STATUS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/receipt-master-details";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{receiptId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ReceiptMasterDetailsRepository receiptMasterDetailsRepository;

    @Autowired
    private ReceiptMasterDetailsMapper receiptMasterDetailsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restReceiptMasterDetailsMockMvc;

    private ReceiptMasterDetails receiptMasterDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReceiptMasterDetails createEntity(EntityManager em) {
        ReceiptMasterDetails receiptMasterDetails = new ReceiptMasterDetails()
            .receiptNo(DEFAULT_RECEIPT_NO)
            .depositId(DEFAULT_DEPOSIT_ID)
            .depositNo(DEFAULT_DEPOSIT_NO)
            .receiptReference(DEFAULT_RECEIPT_REFERENCE)
            .receiptAmount(DEFAULT_RECEIPT_AMOUNT)
            .paymentMode(DEFAULT_PAYMENT_MODE)
            .paymentDate(DEFAULT_PAYMENT_DATE)
            .receiptNotes(DEFAULT_RECEIPT_NOTES)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .receiptMasterDetailsUuid(DEFAULT_RECEIPT_MASTER_DETAILS_UUID)
            .receiptStatus(DEFAULT_RECEIPT_STATUS);
        return receiptMasterDetails;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReceiptMasterDetails createUpdatedEntity(EntityManager em) {
        ReceiptMasterDetails receiptMasterDetails = new ReceiptMasterDetails()
            .receiptNo(UPDATED_RECEIPT_NO)
            .depositId(UPDATED_DEPOSIT_ID)
            .depositNo(UPDATED_DEPOSIT_NO)
            .receiptReference(UPDATED_RECEIPT_REFERENCE)
            .receiptAmount(UPDATED_RECEIPT_AMOUNT)
            .paymentMode(UPDATED_PAYMENT_MODE)
            .paymentDate(UPDATED_PAYMENT_DATE)
            .receiptNotes(UPDATED_RECEIPT_NOTES)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .receiptMasterDetailsUuid(UPDATED_RECEIPT_MASTER_DETAILS_UUID)
            .receiptStatus(UPDATED_RECEIPT_STATUS);
        return receiptMasterDetails;
    }

    @BeforeEach
    public void initTest() {
        receiptMasterDetails = createEntity(em);
    }

    @Test
    @Transactional
    void createReceiptMasterDetails() throws Exception {
        int databaseSizeBeforeCreate = receiptMasterDetailsRepository.findAll().size();
        // Create the ReceiptMasterDetails
        ReceiptMasterDetailsDTO receiptMasterDetailsDTO = receiptMasterDetailsMapper.toDto(receiptMasterDetails);
        restReceiptMasterDetailsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(receiptMasterDetailsDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ReceiptMasterDetails in the database
        List<ReceiptMasterDetails> receiptMasterDetailsList = receiptMasterDetailsRepository.findAll();
        assertThat(receiptMasterDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        ReceiptMasterDetails testReceiptMasterDetails = receiptMasterDetailsList.get(receiptMasterDetailsList.size() - 1);
        assertThat(testReceiptMasterDetails.getReceiptNo()).isEqualTo(DEFAULT_RECEIPT_NO);
        assertThat(testReceiptMasterDetails.getDepositId()).isEqualTo(DEFAULT_DEPOSIT_ID);
        assertThat(testReceiptMasterDetails.getDepositNo()).isEqualTo(DEFAULT_DEPOSIT_NO);
        assertThat(testReceiptMasterDetails.getReceiptReference()).isEqualTo(DEFAULT_RECEIPT_REFERENCE);
        assertThat(testReceiptMasterDetails.getReceiptAmount()).isEqualTo(DEFAULT_RECEIPT_AMOUNT);
        assertThat(testReceiptMasterDetails.getPaymentMode()).isEqualTo(DEFAULT_PAYMENT_MODE);
        assertThat(testReceiptMasterDetails.getPaymentDate()).isEqualTo(DEFAULT_PAYMENT_DATE);
        assertThat(testReceiptMasterDetails.getReceiptNotes()).isEqualTo(DEFAULT_RECEIPT_NOTES);
        assertThat(testReceiptMasterDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testReceiptMasterDetails.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testReceiptMasterDetails.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testReceiptMasterDetails.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testReceiptMasterDetails.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testReceiptMasterDetails.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testReceiptMasterDetails.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testReceiptMasterDetails.getReceiptMasterDetailsUuid()).isEqualTo(DEFAULT_RECEIPT_MASTER_DETAILS_UUID);
        assertThat(testReceiptMasterDetails.getReceiptStatus()).isEqualTo(DEFAULT_RECEIPT_STATUS);
    }

    @Test
    @Transactional
    void createReceiptMasterDetailsWithExistingId() throws Exception {
        // Create the ReceiptMasterDetails with an existing ID
        receiptMasterDetails.setReceiptId(1L);
        ReceiptMasterDetailsDTO receiptMasterDetailsDTO = receiptMasterDetailsMapper.toDto(receiptMasterDetails);

        int databaseSizeBeforeCreate = receiptMasterDetailsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restReceiptMasterDetailsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(receiptMasterDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReceiptMasterDetails in the database
        List<ReceiptMasterDetails> receiptMasterDetailsList = receiptMasterDetailsRepository.findAll();
        assertThat(receiptMasterDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllReceiptMasterDetails() throws Exception {
        // Initialize the database
        receiptMasterDetailsRepository.saveAndFlush(receiptMasterDetails);

        // Get all the receiptMasterDetailsList
        restReceiptMasterDetailsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=receiptId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].receiptId").value(hasItem(receiptMasterDetails.getReceiptId().intValue())))
            .andExpect(jsonPath("$.[*].receiptNo").value(hasItem(DEFAULT_RECEIPT_NO)))
            .andExpect(jsonPath("$.[*].depositId").value(hasItem(DEFAULT_DEPOSIT_ID.intValue())))
            .andExpect(jsonPath("$.[*].depositNo").value(hasItem(DEFAULT_DEPOSIT_NO)))
            .andExpect(jsonPath("$.[*].receiptReference").value(hasItem(DEFAULT_RECEIPT_REFERENCE)))
            .andExpect(jsonPath("$.[*].receiptAmount").value(hasItem(DEFAULT_RECEIPT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].paymentMode").value(hasItem(DEFAULT_PAYMENT_MODE)))
            .andExpect(jsonPath("$.[*].paymentDate").value(hasItem(DEFAULT_PAYMENT_DATE.toString())))
            .andExpect(jsonPath("$.[*].receiptNotes").value(hasItem(DEFAULT_RECEIPT_NOTES)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].receiptMasterDetailsUuid").value(hasItem(DEFAULT_RECEIPT_MASTER_DETAILS_UUID.toString())))
            .andExpect(jsonPath("$.[*].receiptStatus").value(hasItem(DEFAULT_RECEIPT_STATUS)));
    }

    @Test
    @Transactional
    void getReceiptMasterDetails() throws Exception {
        // Initialize the database
        receiptMasterDetailsRepository.saveAndFlush(receiptMasterDetails);

        // Get the receiptMasterDetails
        restReceiptMasterDetailsMockMvc
            .perform(get(ENTITY_API_URL_ID, receiptMasterDetails.getReceiptId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.receiptId").value(receiptMasterDetails.getReceiptId().intValue()))
            .andExpect(jsonPath("$.receiptNo").value(DEFAULT_RECEIPT_NO))
            .andExpect(jsonPath("$.depositId").value(DEFAULT_DEPOSIT_ID.intValue()))
            .andExpect(jsonPath("$.depositNo").value(DEFAULT_DEPOSIT_NO))
            .andExpect(jsonPath("$.receiptReference").value(DEFAULT_RECEIPT_REFERENCE))
            .andExpect(jsonPath("$.receiptAmount").value(DEFAULT_RECEIPT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.paymentMode").value(DEFAULT_PAYMENT_MODE))
            .andExpect(jsonPath("$.paymentDate").value(DEFAULT_PAYMENT_DATE.toString()))
            .andExpect(jsonPath("$.receiptNotes").value(DEFAULT_RECEIPT_NOTES))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.receiptMasterDetailsUuid").value(DEFAULT_RECEIPT_MASTER_DETAILS_UUID.toString()))
            .andExpect(jsonPath("$.receiptStatus").value(DEFAULT_RECEIPT_STATUS));
    }

    @Test
    @Transactional
    void getNonExistingReceiptMasterDetails() throws Exception {
        // Get the receiptMasterDetails
        restReceiptMasterDetailsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewReceiptMasterDetails() throws Exception {
        // Initialize the database
        receiptMasterDetailsRepository.saveAndFlush(receiptMasterDetails);

        int databaseSizeBeforeUpdate = receiptMasterDetailsRepository.findAll().size();

        // Update the receiptMasterDetails
        ReceiptMasterDetails updatedReceiptMasterDetails = receiptMasterDetailsRepository
            .findById(receiptMasterDetails.getReceiptId())
            .get();
        // Disconnect from session so that the updates on updatedReceiptMasterDetails are not directly saved in db
        em.detach(updatedReceiptMasterDetails);
        updatedReceiptMasterDetails
            .receiptNo(UPDATED_RECEIPT_NO)
            .depositId(UPDATED_DEPOSIT_ID)
            .depositNo(UPDATED_DEPOSIT_NO)
            .receiptReference(UPDATED_RECEIPT_REFERENCE)
            .receiptAmount(UPDATED_RECEIPT_AMOUNT)
            .paymentMode(UPDATED_PAYMENT_MODE)
            .paymentDate(UPDATED_PAYMENT_DATE)
            .receiptNotes(UPDATED_RECEIPT_NOTES)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .receiptMasterDetailsUuid(UPDATED_RECEIPT_MASTER_DETAILS_UUID)
            .receiptStatus(UPDATED_RECEIPT_STATUS);
        ReceiptMasterDetailsDTO receiptMasterDetailsDTO = receiptMasterDetailsMapper.toDto(updatedReceiptMasterDetails);

        restReceiptMasterDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, receiptMasterDetailsDTO.getReceiptId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(receiptMasterDetailsDTO))
            )
            .andExpect(status().isOk());

        // Validate the ReceiptMasterDetails in the database
        List<ReceiptMasterDetails> receiptMasterDetailsList = receiptMasterDetailsRepository.findAll();
        assertThat(receiptMasterDetailsList).hasSize(databaseSizeBeforeUpdate);
        ReceiptMasterDetails testReceiptMasterDetails = receiptMasterDetailsList.get(receiptMasterDetailsList.size() - 1);
        assertThat(testReceiptMasterDetails.getReceiptNo()).isEqualTo(UPDATED_RECEIPT_NO);
        assertThat(testReceiptMasterDetails.getDepositId()).isEqualTo(UPDATED_DEPOSIT_ID);
        assertThat(testReceiptMasterDetails.getDepositNo()).isEqualTo(UPDATED_DEPOSIT_NO);
        assertThat(testReceiptMasterDetails.getReceiptReference()).isEqualTo(UPDATED_RECEIPT_REFERENCE);
        assertThat(testReceiptMasterDetails.getReceiptAmount()).isEqualTo(UPDATED_RECEIPT_AMOUNT);
        assertThat(testReceiptMasterDetails.getPaymentMode()).isEqualTo(UPDATED_PAYMENT_MODE);
        assertThat(testReceiptMasterDetails.getPaymentDate()).isEqualTo(UPDATED_PAYMENT_DATE);
        assertThat(testReceiptMasterDetails.getReceiptNotes()).isEqualTo(UPDATED_RECEIPT_NOTES);
        assertThat(testReceiptMasterDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testReceiptMasterDetails.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testReceiptMasterDetails.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testReceiptMasterDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testReceiptMasterDetails.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testReceiptMasterDetails.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testReceiptMasterDetails.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testReceiptMasterDetails.getReceiptMasterDetailsUuid()).isEqualTo(UPDATED_RECEIPT_MASTER_DETAILS_UUID);
        assertThat(testReceiptMasterDetails.getReceiptStatus()).isEqualTo(UPDATED_RECEIPT_STATUS);
    }

    @Test
    @Transactional
    void putNonExistingReceiptMasterDetails() throws Exception {
        int databaseSizeBeforeUpdate = receiptMasterDetailsRepository.findAll().size();
        receiptMasterDetails.setReceiptId(count.incrementAndGet());

        // Create the ReceiptMasterDetails
        ReceiptMasterDetailsDTO receiptMasterDetailsDTO = receiptMasterDetailsMapper.toDto(receiptMasterDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReceiptMasterDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, receiptMasterDetailsDTO.getReceiptId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(receiptMasterDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReceiptMasterDetails in the database
        List<ReceiptMasterDetails> receiptMasterDetailsList = receiptMasterDetailsRepository.findAll();
        assertThat(receiptMasterDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchReceiptMasterDetails() throws Exception {
        int databaseSizeBeforeUpdate = receiptMasterDetailsRepository.findAll().size();
        receiptMasterDetails.setReceiptId(count.incrementAndGet());

        // Create the ReceiptMasterDetails
        ReceiptMasterDetailsDTO receiptMasterDetailsDTO = receiptMasterDetailsMapper.toDto(receiptMasterDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReceiptMasterDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(receiptMasterDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReceiptMasterDetails in the database
        List<ReceiptMasterDetails> receiptMasterDetailsList = receiptMasterDetailsRepository.findAll();
        assertThat(receiptMasterDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamReceiptMasterDetails() throws Exception {
        int databaseSizeBeforeUpdate = receiptMasterDetailsRepository.findAll().size();
        receiptMasterDetails.setReceiptId(count.incrementAndGet());

        // Create the ReceiptMasterDetails
        ReceiptMasterDetailsDTO receiptMasterDetailsDTO = receiptMasterDetailsMapper.toDto(receiptMasterDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReceiptMasterDetailsMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(receiptMasterDetailsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ReceiptMasterDetails in the database
        List<ReceiptMasterDetails> receiptMasterDetailsList = receiptMasterDetailsRepository.findAll();
        assertThat(receiptMasterDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateReceiptMasterDetailsWithPatch() throws Exception {
        // Initialize the database
        receiptMasterDetailsRepository.saveAndFlush(receiptMasterDetails);

        int databaseSizeBeforeUpdate = receiptMasterDetailsRepository.findAll().size();

        // Update the receiptMasterDetails using partial update
        ReceiptMasterDetails partialUpdatedReceiptMasterDetails = new ReceiptMasterDetails();
        partialUpdatedReceiptMasterDetails.setReceiptId(receiptMasterDetails.getReceiptId());

        partialUpdatedReceiptMasterDetails
            .receiptNo(UPDATED_RECEIPT_NO)
            .depositId(UPDATED_DEPOSIT_ID)
            .paymentMode(UPDATED_PAYMENT_MODE)
            .receiptNotes(UPDATED_RECEIPT_NOTES)
            .status(UPDATED_STATUS)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .receiptMasterDetailsUuid(UPDATED_RECEIPT_MASTER_DETAILS_UUID);

        restReceiptMasterDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedReceiptMasterDetails.getReceiptId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedReceiptMasterDetails))
            )
            .andExpect(status().isOk());

        // Validate the ReceiptMasterDetails in the database
        List<ReceiptMasterDetails> receiptMasterDetailsList = receiptMasterDetailsRepository.findAll();
        assertThat(receiptMasterDetailsList).hasSize(databaseSizeBeforeUpdate);
        ReceiptMasterDetails testReceiptMasterDetails = receiptMasterDetailsList.get(receiptMasterDetailsList.size() - 1);
        assertThat(testReceiptMasterDetails.getReceiptNo()).isEqualTo(UPDATED_RECEIPT_NO);
        assertThat(testReceiptMasterDetails.getDepositId()).isEqualTo(UPDATED_DEPOSIT_ID);
        assertThat(testReceiptMasterDetails.getDepositNo()).isEqualTo(DEFAULT_DEPOSIT_NO);
        assertThat(testReceiptMasterDetails.getReceiptReference()).isEqualTo(DEFAULT_RECEIPT_REFERENCE);
        assertThat(testReceiptMasterDetails.getReceiptAmount()).isEqualTo(DEFAULT_RECEIPT_AMOUNT);
        assertThat(testReceiptMasterDetails.getPaymentMode()).isEqualTo(UPDATED_PAYMENT_MODE);
        assertThat(testReceiptMasterDetails.getPaymentDate()).isEqualTo(DEFAULT_PAYMENT_DATE);
        assertThat(testReceiptMasterDetails.getReceiptNotes()).isEqualTo(UPDATED_RECEIPT_NOTES);
        assertThat(testReceiptMasterDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testReceiptMasterDetails.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testReceiptMasterDetails.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testReceiptMasterDetails.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testReceiptMasterDetails.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testReceiptMasterDetails.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testReceiptMasterDetails.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testReceiptMasterDetails.getReceiptMasterDetailsUuid()).isEqualTo(UPDATED_RECEIPT_MASTER_DETAILS_UUID);
        assertThat(testReceiptMasterDetails.getReceiptStatus()).isEqualTo(DEFAULT_RECEIPT_STATUS);
    }

    @Test
    @Transactional
    void fullUpdateReceiptMasterDetailsWithPatch() throws Exception {
        // Initialize the database
        receiptMasterDetailsRepository.saveAndFlush(receiptMasterDetails);

        int databaseSizeBeforeUpdate = receiptMasterDetailsRepository.findAll().size();

        // Update the receiptMasterDetails using partial update
        ReceiptMasterDetails partialUpdatedReceiptMasterDetails = new ReceiptMasterDetails();
        partialUpdatedReceiptMasterDetails.setReceiptId(receiptMasterDetails.getReceiptId());

        partialUpdatedReceiptMasterDetails
            .receiptNo(UPDATED_RECEIPT_NO)
            .depositId(UPDATED_DEPOSIT_ID)
            .depositNo(UPDATED_DEPOSIT_NO)
            .receiptReference(UPDATED_RECEIPT_REFERENCE)
            .receiptAmount(UPDATED_RECEIPT_AMOUNT)
            .paymentMode(UPDATED_PAYMENT_MODE)
            .paymentDate(UPDATED_PAYMENT_DATE)
            .receiptNotes(UPDATED_RECEIPT_NOTES)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .receiptMasterDetailsUuid(UPDATED_RECEIPT_MASTER_DETAILS_UUID)
            .receiptStatus(UPDATED_RECEIPT_STATUS);

        restReceiptMasterDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedReceiptMasterDetails.getReceiptId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedReceiptMasterDetails))
            )
            .andExpect(status().isOk());

        // Validate the ReceiptMasterDetails in the database
        List<ReceiptMasterDetails> receiptMasterDetailsList = receiptMasterDetailsRepository.findAll();
        assertThat(receiptMasterDetailsList).hasSize(databaseSizeBeforeUpdate);
        ReceiptMasterDetails testReceiptMasterDetails = receiptMasterDetailsList.get(receiptMasterDetailsList.size() - 1);
        assertThat(testReceiptMasterDetails.getReceiptNo()).isEqualTo(UPDATED_RECEIPT_NO);
        assertThat(testReceiptMasterDetails.getDepositId()).isEqualTo(UPDATED_DEPOSIT_ID);
        assertThat(testReceiptMasterDetails.getDepositNo()).isEqualTo(UPDATED_DEPOSIT_NO);
        assertThat(testReceiptMasterDetails.getReceiptReference()).isEqualTo(UPDATED_RECEIPT_REFERENCE);
        assertThat(testReceiptMasterDetails.getReceiptAmount()).isEqualTo(UPDATED_RECEIPT_AMOUNT);
        assertThat(testReceiptMasterDetails.getPaymentMode()).isEqualTo(UPDATED_PAYMENT_MODE);
        assertThat(testReceiptMasterDetails.getPaymentDate()).isEqualTo(UPDATED_PAYMENT_DATE);
        assertThat(testReceiptMasterDetails.getReceiptNotes()).isEqualTo(UPDATED_RECEIPT_NOTES);
        assertThat(testReceiptMasterDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testReceiptMasterDetails.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testReceiptMasterDetails.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testReceiptMasterDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testReceiptMasterDetails.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testReceiptMasterDetails.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testReceiptMasterDetails.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testReceiptMasterDetails.getReceiptMasterDetailsUuid()).isEqualTo(UPDATED_RECEIPT_MASTER_DETAILS_UUID);
        assertThat(testReceiptMasterDetails.getReceiptStatus()).isEqualTo(UPDATED_RECEIPT_STATUS);
    }

    @Test
    @Transactional
    void patchNonExistingReceiptMasterDetails() throws Exception {
        int databaseSizeBeforeUpdate = receiptMasterDetailsRepository.findAll().size();
        receiptMasterDetails.setReceiptId(count.incrementAndGet());

        // Create the ReceiptMasterDetails
        ReceiptMasterDetailsDTO receiptMasterDetailsDTO = receiptMasterDetailsMapper.toDto(receiptMasterDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReceiptMasterDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, receiptMasterDetailsDTO.getReceiptId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(receiptMasterDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReceiptMasterDetails in the database
        List<ReceiptMasterDetails> receiptMasterDetailsList = receiptMasterDetailsRepository.findAll();
        assertThat(receiptMasterDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchReceiptMasterDetails() throws Exception {
        int databaseSizeBeforeUpdate = receiptMasterDetailsRepository.findAll().size();
        receiptMasterDetails.setReceiptId(count.incrementAndGet());

        // Create the ReceiptMasterDetails
        ReceiptMasterDetailsDTO receiptMasterDetailsDTO = receiptMasterDetailsMapper.toDto(receiptMasterDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReceiptMasterDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(receiptMasterDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReceiptMasterDetails in the database
        List<ReceiptMasterDetails> receiptMasterDetailsList = receiptMasterDetailsRepository.findAll();
        assertThat(receiptMasterDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamReceiptMasterDetails() throws Exception {
        int databaseSizeBeforeUpdate = receiptMasterDetailsRepository.findAll().size();
        receiptMasterDetails.setReceiptId(count.incrementAndGet());

        // Create the ReceiptMasterDetails
        ReceiptMasterDetailsDTO receiptMasterDetailsDTO = receiptMasterDetailsMapper.toDto(receiptMasterDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReceiptMasterDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(receiptMasterDetailsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ReceiptMasterDetails in the database
        List<ReceiptMasterDetails> receiptMasterDetailsList = receiptMasterDetailsRepository.findAll();
        assertThat(receiptMasterDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteReceiptMasterDetails() throws Exception {
        // Initialize the database
        receiptMasterDetailsRepository.saveAndFlush(receiptMasterDetails);

        int databaseSizeBeforeDelete = receiptMasterDetailsRepository.findAll().size();

        // Delete the receiptMasterDetails
        restReceiptMasterDetailsMockMvc
            .perform(delete(ENTITY_API_URL_ID, receiptMasterDetails.getReceiptId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ReceiptMasterDetails> receiptMasterDetailsList = receiptMasterDetailsRepository.findAll();
        assertThat(receiptMasterDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
