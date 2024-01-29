package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.InvoicePostingDetails;
import com.sunknowledge.dme.rcm.repository.InvoicePostingDetailsRepository;
import com.sunknowledge.dme.rcm.service.dto.InvoicePostingDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.InvoicePostingDetailsMapper;
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
 * Integration tests for the {@link InvoicePostingDetailsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class InvoicePostingDetailsResourceIT {

    private static final Long DEFAULT_ITEM_ID = 1L;
    private static final Long UPDATED_ITEM_ID = 2L;

    private static final LocalDate DEFAULT_POSTING_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_POSTING_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_POSTED_BY_ID = 1L;
    private static final Long UPDATED_POSTED_BY_ID = 2L;

    private static final String DEFAULT_POSTED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_POSTED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_POSTING_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_POSTING_COMMENT = "BBBBBBBBBB";

    private static final String DEFAULT_POST_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_POST_TYPE = "BBBBBBBBBB";

    private static final Long DEFAULT_DEPOSIT_ID = 1L;
    private static final Long UPDATED_DEPOSIT_ID = 2L;

    private static final Long DEFAULT_RECEIPT_ID = 1L;
    private static final Long UPDATED_RECEIPT_ID = 2L;

    private static final Double DEFAULT_POST_AMOUNT = 1D;
    private static final Double UPDATED_POST_AMOUNT = 2D;

    private static final String DEFAULT_POST_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_POST_STATUS = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_INVOICE_POSTING_DETAILS_UUID = UUID.randomUUID();
    private static final UUID UPDATED_INVOICE_POSTING_DETAILS_UUID = UUID.randomUUID();

    private static final String DEFAULT_INVOICE_NO = "AAAAAAAAAA";
    private static final String UPDATED_INVOICE_NO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_INVOICE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_INVOICE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_INVOICE_LINE_ITEM_DETAILS_ID = 1L;
    private static final Long UPDATED_INVOICE_LINE_ITEM_DETAILS_ID = 2L;

    private static final String DEFAULT_HCPCS_CODE = "AAAAAAAAAA";
    private static final String UPDATED_HCPCS_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_POSTING_NO = "AAAAAAAAAA";
    private static final String UPDATED_POSTING_NO = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_MANUAL_POSTING = false;
    private static final Boolean UPDATED_IS_MANUAL_POSTING = true;

    private static final String ENTITY_API_URL = "/api/invoice-posting-details";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{invoiceLineItemPostingId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private InvoicePostingDetailsRepository invoicePostingDetailsRepository;

    @Autowired
    private InvoicePostingDetailsMapper invoicePostingDetailsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInvoicePostingDetailsMockMvc;

    private InvoicePostingDetails invoicePostingDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InvoicePostingDetails createEntity(EntityManager em) {
        InvoicePostingDetails invoicePostingDetails = new InvoicePostingDetails()
            .itemId(DEFAULT_ITEM_ID)
            .postingDate(DEFAULT_POSTING_DATE)
            .postedById(DEFAULT_POSTED_BY_ID)
            .postedByName(DEFAULT_POSTED_BY_NAME)
            .postingComment(DEFAULT_POSTING_COMMENT)
            .postType(DEFAULT_POST_TYPE)
            .depositId(DEFAULT_DEPOSIT_ID)
            .receiptId(DEFAULT_RECEIPT_ID)
            .postAmount(DEFAULT_POST_AMOUNT)
            .postStatus(DEFAULT_POST_STATUS)
            .status(DEFAULT_STATUS)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .invoicePostingDetailsUuid(DEFAULT_INVOICE_POSTING_DETAILS_UUID)
            .invoiceNo(DEFAULT_INVOICE_NO)
            .invoiceDate(DEFAULT_INVOICE_DATE)
            .invoiceLineItemDetailsId(DEFAULT_INVOICE_LINE_ITEM_DETAILS_ID)
            .hcpcsCode(DEFAULT_HCPCS_CODE)
            .postingNo(DEFAULT_POSTING_NO)
            .isManualPosting(DEFAULT_IS_MANUAL_POSTING);
        return invoicePostingDetails;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InvoicePostingDetails createUpdatedEntity(EntityManager em) {
        InvoicePostingDetails invoicePostingDetails = new InvoicePostingDetails()
            .itemId(UPDATED_ITEM_ID)
            .postingDate(UPDATED_POSTING_DATE)
            .postedById(UPDATED_POSTED_BY_ID)
            .postedByName(UPDATED_POSTED_BY_NAME)
            .postingComment(UPDATED_POSTING_COMMENT)
            .postType(UPDATED_POST_TYPE)
            .depositId(UPDATED_DEPOSIT_ID)
            .receiptId(UPDATED_RECEIPT_ID)
            .postAmount(UPDATED_POST_AMOUNT)
            .postStatus(UPDATED_POST_STATUS)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .invoicePostingDetailsUuid(UPDATED_INVOICE_POSTING_DETAILS_UUID)
            .invoiceNo(UPDATED_INVOICE_NO)
            .invoiceDate(UPDATED_INVOICE_DATE)
            .invoiceLineItemDetailsId(UPDATED_INVOICE_LINE_ITEM_DETAILS_ID)
            .hcpcsCode(UPDATED_HCPCS_CODE)
            .postingNo(UPDATED_POSTING_NO)
            .isManualPosting(UPDATED_IS_MANUAL_POSTING);
        return invoicePostingDetails;
    }

    @BeforeEach
    public void initTest() {
        invoicePostingDetails = createEntity(em);
    }

    @Test
    @Transactional
    void createInvoicePostingDetails() throws Exception {
        int databaseSizeBeforeCreate = invoicePostingDetailsRepository.findAll().size();
        // Create the InvoicePostingDetails
        InvoicePostingDetailsDTO invoicePostingDetailsDTO = invoicePostingDetailsMapper.toDto(invoicePostingDetails);
        restInvoicePostingDetailsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(invoicePostingDetailsDTO))
            )
            .andExpect(status().isCreated());

        // Validate the InvoicePostingDetails in the database
        List<InvoicePostingDetails> invoicePostingDetailsList = invoicePostingDetailsRepository.findAll();
        assertThat(invoicePostingDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        InvoicePostingDetails testInvoicePostingDetails = invoicePostingDetailsList.get(invoicePostingDetailsList.size() - 1);
        assertThat(testInvoicePostingDetails.getItemId()).isEqualTo(DEFAULT_ITEM_ID);
        assertThat(testInvoicePostingDetails.getPostingDate()).isEqualTo(DEFAULT_POSTING_DATE);
        assertThat(testInvoicePostingDetails.getPostedById()).isEqualTo(DEFAULT_POSTED_BY_ID);
        assertThat(testInvoicePostingDetails.getPostedByName()).isEqualTo(DEFAULT_POSTED_BY_NAME);
        assertThat(testInvoicePostingDetails.getPostingComment()).isEqualTo(DEFAULT_POSTING_COMMENT);
        assertThat(testInvoicePostingDetails.getPostType()).isEqualTo(DEFAULT_POST_TYPE);
        assertThat(testInvoicePostingDetails.getDepositId()).isEqualTo(DEFAULT_DEPOSIT_ID);
        assertThat(testInvoicePostingDetails.getReceiptId()).isEqualTo(DEFAULT_RECEIPT_ID);
        assertThat(testInvoicePostingDetails.getPostAmount()).isEqualTo(DEFAULT_POST_AMOUNT);
        assertThat(testInvoicePostingDetails.getPostStatus()).isEqualTo(DEFAULT_POST_STATUS);
        assertThat(testInvoicePostingDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testInvoicePostingDetails.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testInvoicePostingDetails.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testInvoicePostingDetails.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testInvoicePostingDetails.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testInvoicePostingDetails.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testInvoicePostingDetails.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testInvoicePostingDetails.getInvoicePostingDetailsUuid()).isEqualTo(DEFAULT_INVOICE_POSTING_DETAILS_UUID);
        assertThat(testInvoicePostingDetails.getInvoiceNo()).isEqualTo(DEFAULT_INVOICE_NO);
        assertThat(testInvoicePostingDetails.getInvoiceDate()).isEqualTo(DEFAULT_INVOICE_DATE);
        assertThat(testInvoicePostingDetails.getInvoiceLineItemDetailsId()).isEqualTo(DEFAULT_INVOICE_LINE_ITEM_DETAILS_ID);
        assertThat(testInvoicePostingDetails.getHcpcsCode()).isEqualTo(DEFAULT_HCPCS_CODE);
        assertThat(testInvoicePostingDetails.getPostingNo()).isEqualTo(DEFAULT_POSTING_NO);
        assertThat(testInvoicePostingDetails.getIsManualPosting()).isEqualTo(DEFAULT_IS_MANUAL_POSTING);
    }

    @Test
    @Transactional
    void createInvoicePostingDetailsWithExistingId() throws Exception {
        // Create the InvoicePostingDetails with an existing ID
        invoicePostingDetails.setInvoiceLineItemPostingId(1L);
        InvoicePostingDetailsDTO invoicePostingDetailsDTO = invoicePostingDetailsMapper.toDto(invoicePostingDetails);

        int databaseSizeBeforeCreate = invoicePostingDetailsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restInvoicePostingDetailsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(invoicePostingDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InvoicePostingDetails in the database
        List<InvoicePostingDetails> invoicePostingDetailsList = invoicePostingDetailsRepository.findAll();
        assertThat(invoicePostingDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllInvoicePostingDetails() throws Exception {
        // Initialize the database
        invoicePostingDetailsRepository.saveAndFlush(invoicePostingDetails);

        // Get all the invoicePostingDetailsList
        restInvoicePostingDetailsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=invoiceLineItemPostingId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].invoiceLineItemPostingId").value(hasItem(invoicePostingDetails.getInvoiceLineItemPostingId().intValue()))
            )
            .andExpect(jsonPath("$.[*].itemId").value(hasItem(DEFAULT_ITEM_ID.intValue())))
            .andExpect(jsonPath("$.[*].postingDate").value(hasItem(DEFAULT_POSTING_DATE.toString())))
            .andExpect(jsonPath("$.[*].postedById").value(hasItem(DEFAULT_POSTED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].postedByName").value(hasItem(DEFAULT_POSTED_BY_NAME)))
            .andExpect(jsonPath("$.[*].postingComment").value(hasItem(DEFAULT_POSTING_COMMENT)))
            .andExpect(jsonPath("$.[*].postType").value(hasItem(DEFAULT_POST_TYPE)))
            .andExpect(jsonPath("$.[*].depositId").value(hasItem(DEFAULT_DEPOSIT_ID.intValue())))
            .andExpect(jsonPath("$.[*].receiptId").value(hasItem(DEFAULT_RECEIPT_ID.intValue())))
            .andExpect(jsonPath("$.[*].postAmount").value(hasItem(DEFAULT_POST_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].postStatus").value(hasItem(DEFAULT_POST_STATUS)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].invoicePostingDetailsUuid").value(hasItem(DEFAULT_INVOICE_POSTING_DETAILS_UUID.toString())))
            .andExpect(jsonPath("$.[*].invoiceNo").value(hasItem(DEFAULT_INVOICE_NO)))
            .andExpect(jsonPath("$.[*].invoiceDate").value(hasItem(DEFAULT_INVOICE_DATE.toString())))
            .andExpect(jsonPath("$.[*].invoiceLineItemDetailsId").value(hasItem(DEFAULT_INVOICE_LINE_ITEM_DETAILS_ID.intValue())))
            .andExpect(jsonPath("$.[*].hcpcsCode").value(hasItem(DEFAULT_HCPCS_CODE)))
            .andExpect(jsonPath("$.[*].postingNo").value(hasItem(DEFAULT_POSTING_NO)))
            .andExpect(jsonPath("$.[*].isManualPosting").value(hasItem(DEFAULT_IS_MANUAL_POSTING.booleanValue())));
    }

    @Test
    @Transactional
    void getInvoicePostingDetails() throws Exception {
        // Initialize the database
        invoicePostingDetailsRepository.saveAndFlush(invoicePostingDetails);

        // Get the invoicePostingDetails
        restInvoicePostingDetailsMockMvc
            .perform(get(ENTITY_API_URL_ID, invoicePostingDetails.getInvoiceLineItemPostingId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.invoiceLineItemPostingId").value(invoicePostingDetails.getInvoiceLineItemPostingId().intValue()))
            .andExpect(jsonPath("$.itemId").value(DEFAULT_ITEM_ID.intValue()))
            .andExpect(jsonPath("$.postingDate").value(DEFAULT_POSTING_DATE.toString()))
            .andExpect(jsonPath("$.postedById").value(DEFAULT_POSTED_BY_ID.intValue()))
            .andExpect(jsonPath("$.postedByName").value(DEFAULT_POSTED_BY_NAME))
            .andExpect(jsonPath("$.postingComment").value(DEFAULT_POSTING_COMMENT))
            .andExpect(jsonPath("$.postType").value(DEFAULT_POST_TYPE))
            .andExpect(jsonPath("$.depositId").value(DEFAULT_DEPOSIT_ID.intValue()))
            .andExpect(jsonPath("$.receiptId").value(DEFAULT_RECEIPT_ID.intValue()))
            .andExpect(jsonPath("$.postAmount").value(DEFAULT_POST_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.postStatus").value(DEFAULT_POST_STATUS))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.invoicePostingDetailsUuid").value(DEFAULT_INVOICE_POSTING_DETAILS_UUID.toString()))
            .andExpect(jsonPath("$.invoiceNo").value(DEFAULT_INVOICE_NO))
            .andExpect(jsonPath("$.invoiceDate").value(DEFAULT_INVOICE_DATE.toString()))
            .andExpect(jsonPath("$.invoiceLineItemDetailsId").value(DEFAULT_INVOICE_LINE_ITEM_DETAILS_ID.intValue()))
            .andExpect(jsonPath("$.hcpcsCode").value(DEFAULT_HCPCS_CODE))
            .andExpect(jsonPath("$.postingNo").value(DEFAULT_POSTING_NO))
            .andExpect(jsonPath("$.isManualPosting").value(DEFAULT_IS_MANUAL_POSTING.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingInvoicePostingDetails() throws Exception {
        // Get the invoicePostingDetails
        restInvoicePostingDetailsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewInvoicePostingDetails() throws Exception {
        // Initialize the database
        invoicePostingDetailsRepository.saveAndFlush(invoicePostingDetails);

        int databaseSizeBeforeUpdate = invoicePostingDetailsRepository.findAll().size();

        // Update the invoicePostingDetails
        InvoicePostingDetails updatedInvoicePostingDetails = invoicePostingDetailsRepository
            .findById(invoicePostingDetails.getInvoiceLineItemPostingId())
            .get();
        // Disconnect from session so that the updates on updatedInvoicePostingDetails are not directly saved in db
        em.detach(updatedInvoicePostingDetails);
        updatedInvoicePostingDetails
            .itemId(UPDATED_ITEM_ID)
            .postingDate(UPDATED_POSTING_DATE)
            .postedById(UPDATED_POSTED_BY_ID)
            .postedByName(UPDATED_POSTED_BY_NAME)
            .postingComment(UPDATED_POSTING_COMMENT)
            .postType(UPDATED_POST_TYPE)
            .depositId(UPDATED_DEPOSIT_ID)
            .receiptId(UPDATED_RECEIPT_ID)
            .postAmount(UPDATED_POST_AMOUNT)
            .postStatus(UPDATED_POST_STATUS)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .invoicePostingDetailsUuid(UPDATED_INVOICE_POSTING_DETAILS_UUID)
            .invoiceNo(UPDATED_INVOICE_NO)
            .invoiceDate(UPDATED_INVOICE_DATE)
            .invoiceLineItemDetailsId(UPDATED_INVOICE_LINE_ITEM_DETAILS_ID)
            .hcpcsCode(UPDATED_HCPCS_CODE)
            .postingNo(UPDATED_POSTING_NO)
            .isManualPosting(UPDATED_IS_MANUAL_POSTING);
        InvoicePostingDetailsDTO invoicePostingDetailsDTO = invoicePostingDetailsMapper.toDto(updatedInvoicePostingDetails);

        restInvoicePostingDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, invoicePostingDetailsDTO.getInvoiceLineItemPostingId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(invoicePostingDetailsDTO))
            )
            .andExpect(status().isOk());

        // Validate the InvoicePostingDetails in the database
        List<InvoicePostingDetails> invoicePostingDetailsList = invoicePostingDetailsRepository.findAll();
        assertThat(invoicePostingDetailsList).hasSize(databaseSizeBeforeUpdate);
        InvoicePostingDetails testInvoicePostingDetails = invoicePostingDetailsList.get(invoicePostingDetailsList.size() - 1);
        assertThat(testInvoicePostingDetails.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testInvoicePostingDetails.getPostingDate()).isEqualTo(UPDATED_POSTING_DATE);
        assertThat(testInvoicePostingDetails.getPostedById()).isEqualTo(UPDATED_POSTED_BY_ID);
        assertThat(testInvoicePostingDetails.getPostedByName()).isEqualTo(UPDATED_POSTED_BY_NAME);
        assertThat(testInvoicePostingDetails.getPostingComment()).isEqualTo(UPDATED_POSTING_COMMENT);
        assertThat(testInvoicePostingDetails.getPostType()).isEqualTo(UPDATED_POST_TYPE);
        assertThat(testInvoicePostingDetails.getDepositId()).isEqualTo(UPDATED_DEPOSIT_ID);
        assertThat(testInvoicePostingDetails.getReceiptId()).isEqualTo(UPDATED_RECEIPT_ID);
        assertThat(testInvoicePostingDetails.getPostAmount()).isEqualTo(UPDATED_POST_AMOUNT);
        assertThat(testInvoicePostingDetails.getPostStatus()).isEqualTo(UPDATED_POST_STATUS);
        assertThat(testInvoicePostingDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testInvoicePostingDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testInvoicePostingDetails.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testInvoicePostingDetails.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testInvoicePostingDetails.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testInvoicePostingDetails.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testInvoicePostingDetails.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testInvoicePostingDetails.getInvoicePostingDetailsUuid()).isEqualTo(UPDATED_INVOICE_POSTING_DETAILS_UUID);
        assertThat(testInvoicePostingDetails.getInvoiceNo()).isEqualTo(UPDATED_INVOICE_NO);
        assertThat(testInvoicePostingDetails.getInvoiceDate()).isEqualTo(UPDATED_INVOICE_DATE);
        assertThat(testInvoicePostingDetails.getInvoiceLineItemDetailsId()).isEqualTo(UPDATED_INVOICE_LINE_ITEM_DETAILS_ID);
        assertThat(testInvoicePostingDetails.getHcpcsCode()).isEqualTo(UPDATED_HCPCS_CODE);
        assertThat(testInvoicePostingDetails.getPostingNo()).isEqualTo(UPDATED_POSTING_NO);
        assertThat(testInvoicePostingDetails.getIsManualPosting()).isEqualTo(UPDATED_IS_MANUAL_POSTING);
    }

    @Test
    @Transactional
    void putNonExistingInvoicePostingDetails() throws Exception {
        int databaseSizeBeforeUpdate = invoicePostingDetailsRepository.findAll().size();
        invoicePostingDetails.setInvoiceLineItemPostingId(count.incrementAndGet());

        // Create the InvoicePostingDetails
        InvoicePostingDetailsDTO invoicePostingDetailsDTO = invoicePostingDetailsMapper.toDto(invoicePostingDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInvoicePostingDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, invoicePostingDetailsDTO.getInvoiceLineItemPostingId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(invoicePostingDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InvoicePostingDetails in the database
        List<InvoicePostingDetails> invoicePostingDetailsList = invoicePostingDetailsRepository.findAll();
        assertThat(invoicePostingDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchInvoicePostingDetails() throws Exception {
        int databaseSizeBeforeUpdate = invoicePostingDetailsRepository.findAll().size();
        invoicePostingDetails.setInvoiceLineItemPostingId(count.incrementAndGet());

        // Create the InvoicePostingDetails
        InvoicePostingDetailsDTO invoicePostingDetailsDTO = invoicePostingDetailsMapper.toDto(invoicePostingDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInvoicePostingDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(invoicePostingDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InvoicePostingDetails in the database
        List<InvoicePostingDetails> invoicePostingDetailsList = invoicePostingDetailsRepository.findAll();
        assertThat(invoicePostingDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamInvoicePostingDetails() throws Exception {
        int databaseSizeBeforeUpdate = invoicePostingDetailsRepository.findAll().size();
        invoicePostingDetails.setInvoiceLineItemPostingId(count.incrementAndGet());

        // Create the InvoicePostingDetails
        InvoicePostingDetailsDTO invoicePostingDetailsDTO = invoicePostingDetailsMapper.toDto(invoicePostingDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInvoicePostingDetailsMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(invoicePostingDetailsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the InvoicePostingDetails in the database
        List<InvoicePostingDetails> invoicePostingDetailsList = invoicePostingDetailsRepository.findAll();
        assertThat(invoicePostingDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateInvoicePostingDetailsWithPatch() throws Exception {
        // Initialize the database
        invoicePostingDetailsRepository.saveAndFlush(invoicePostingDetails);

        int databaseSizeBeforeUpdate = invoicePostingDetailsRepository.findAll().size();

        // Update the invoicePostingDetails using partial update
        InvoicePostingDetails partialUpdatedInvoicePostingDetails = new InvoicePostingDetails();
        partialUpdatedInvoicePostingDetails.setInvoiceLineItemPostingId(invoicePostingDetails.getInvoiceLineItemPostingId());

        partialUpdatedInvoicePostingDetails
            .itemId(UPDATED_ITEM_ID)
            .postingDate(UPDATED_POSTING_DATE)
            .postingComment(UPDATED_POSTING_COMMENT)
            .postType(UPDATED_POST_TYPE)
            .postAmount(UPDATED_POST_AMOUNT)
            .postStatus(UPDATED_POST_STATUS)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .invoicePostingDetailsUuid(UPDATED_INVOICE_POSTING_DETAILS_UUID)
            .invoiceDate(UPDATED_INVOICE_DATE);

        restInvoicePostingDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInvoicePostingDetails.getInvoiceLineItemPostingId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInvoicePostingDetails))
            )
            .andExpect(status().isOk());

        // Validate the InvoicePostingDetails in the database
        List<InvoicePostingDetails> invoicePostingDetailsList = invoicePostingDetailsRepository.findAll();
        assertThat(invoicePostingDetailsList).hasSize(databaseSizeBeforeUpdate);
        InvoicePostingDetails testInvoicePostingDetails = invoicePostingDetailsList.get(invoicePostingDetailsList.size() - 1);
        assertThat(testInvoicePostingDetails.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testInvoicePostingDetails.getPostingDate()).isEqualTo(UPDATED_POSTING_DATE);
        assertThat(testInvoicePostingDetails.getPostedById()).isEqualTo(DEFAULT_POSTED_BY_ID);
        assertThat(testInvoicePostingDetails.getPostedByName()).isEqualTo(DEFAULT_POSTED_BY_NAME);
        assertThat(testInvoicePostingDetails.getPostingComment()).isEqualTo(UPDATED_POSTING_COMMENT);
        assertThat(testInvoicePostingDetails.getPostType()).isEqualTo(UPDATED_POST_TYPE);
        assertThat(testInvoicePostingDetails.getDepositId()).isEqualTo(DEFAULT_DEPOSIT_ID);
        assertThat(testInvoicePostingDetails.getReceiptId()).isEqualTo(DEFAULT_RECEIPT_ID);
        assertThat(testInvoicePostingDetails.getPostAmount()).isEqualTo(UPDATED_POST_AMOUNT);
        assertThat(testInvoicePostingDetails.getPostStatus()).isEqualTo(UPDATED_POST_STATUS);
        assertThat(testInvoicePostingDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testInvoicePostingDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testInvoicePostingDetails.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testInvoicePostingDetails.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testInvoicePostingDetails.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testInvoicePostingDetails.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testInvoicePostingDetails.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testInvoicePostingDetails.getInvoicePostingDetailsUuid()).isEqualTo(UPDATED_INVOICE_POSTING_DETAILS_UUID);
        assertThat(testInvoicePostingDetails.getInvoiceNo()).isEqualTo(DEFAULT_INVOICE_NO);
        assertThat(testInvoicePostingDetails.getInvoiceDate()).isEqualTo(UPDATED_INVOICE_DATE);
        assertThat(testInvoicePostingDetails.getInvoiceLineItemDetailsId()).isEqualTo(DEFAULT_INVOICE_LINE_ITEM_DETAILS_ID);
        assertThat(testInvoicePostingDetails.getHcpcsCode()).isEqualTo(DEFAULT_HCPCS_CODE);
        assertThat(testInvoicePostingDetails.getPostingNo()).isEqualTo(DEFAULT_POSTING_NO);
        assertThat(testInvoicePostingDetails.getIsManualPosting()).isEqualTo(DEFAULT_IS_MANUAL_POSTING);
    }

    @Test
    @Transactional
    void fullUpdateInvoicePostingDetailsWithPatch() throws Exception {
        // Initialize the database
        invoicePostingDetailsRepository.saveAndFlush(invoicePostingDetails);

        int databaseSizeBeforeUpdate = invoicePostingDetailsRepository.findAll().size();

        // Update the invoicePostingDetails using partial update
        InvoicePostingDetails partialUpdatedInvoicePostingDetails = new InvoicePostingDetails();
        partialUpdatedInvoicePostingDetails.setInvoiceLineItemPostingId(invoicePostingDetails.getInvoiceLineItemPostingId());

        partialUpdatedInvoicePostingDetails
            .itemId(UPDATED_ITEM_ID)
            .postingDate(UPDATED_POSTING_DATE)
            .postedById(UPDATED_POSTED_BY_ID)
            .postedByName(UPDATED_POSTED_BY_NAME)
            .postingComment(UPDATED_POSTING_COMMENT)
            .postType(UPDATED_POST_TYPE)
            .depositId(UPDATED_DEPOSIT_ID)
            .receiptId(UPDATED_RECEIPT_ID)
            .postAmount(UPDATED_POST_AMOUNT)
            .postStatus(UPDATED_POST_STATUS)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .invoicePostingDetailsUuid(UPDATED_INVOICE_POSTING_DETAILS_UUID)
            .invoiceNo(UPDATED_INVOICE_NO)
            .invoiceDate(UPDATED_INVOICE_DATE)
            .invoiceLineItemDetailsId(UPDATED_INVOICE_LINE_ITEM_DETAILS_ID)
            .hcpcsCode(UPDATED_HCPCS_CODE)
            .postingNo(UPDATED_POSTING_NO)
            .isManualPosting(UPDATED_IS_MANUAL_POSTING);

        restInvoicePostingDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInvoicePostingDetails.getInvoiceLineItemPostingId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInvoicePostingDetails))
            )
            .andExpect(status().isOk());

        // Validate the InvoicePostingDetails in the database
        List<InvoicePostingDetails> invoicePostingDetailsList = invoicePostingDetailsRepository.findAll();
        assertThat(invoicePostingDetailsList).hasSize(databaseSizeBeforeUpdate);
        InvoicePostingDetails testInvoicePostingDetails = invoicePostingDetailsList.get(invoicePostingDetailsList.size() - 1);
        assertThat(testInvoicePostingDetails.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testInvoicePostingDetails.getPostingDate()).isEqualTo(UPDATED_POSTING_DATE);
        assertThat(testInvoicePostingDetails.getPostedById()).isEqualTo(UPDATED_POSTED_BY_ID);
        assertThat(testInvoicePostingDetails.getPostedByName()).isEqualTo(UPDATED_POSTED_BY_NAME);
        assertThat(testInvoicePostingDetails.getPostingComment()).isEqualTo(UPDATED_POSTING_COMMENT);
        assertThat(testInvoicePostingDetails.getPostType()).isEqualTo(UPDATED_POST_TYPE);
        assertThat(testInvoicePostingDetails.getDepositId()).isEqualTo(UPDATED_DEPOSIT_ID);
        assertThat(testInvoicePostingDetails.getReceiptId()).isEqualTo(UPDATED_RECEIPT_ID);
        assertThat(testInvoicePostingDetails.getPostAmount()).isEqualTo(UPDATED_POST_AMOUNT);
        assertThat(testInvoicePostingDetails.getPostStatus()).isEqualTo(UPDATED_POST_STATUS);
        assertThat(testInvoicePostingDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testInvoicePostingDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testInvoicePostingDetails.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testInvoicePostingDetails.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testInvoicePostingDetails.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testInvoicePostingDetails.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testInvoicePostingDetails.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testInvoicePostingDetails.getInvoicePostingDetailsUuid()).isEqualTo(UPDATED_INVOICE_POSTING_DETAILS_UUID);
        assertThat(testInvoicePostingDetails.getInvoiceNo()).isEqualTo(UPDATED_INVOICE_NO);
        assertThat(testInvoicePostingDetails.getInvoiceDate()).isEqualTo(UPDATED_INVOICE_DATE);
        assertThat(testInvoicePostingDetails.getInvoiceLineItemDetailsId()).isEqualTo(UPDATED_INVOICE_LINE_ITEM_DETAILS_ID);
        assertThat(testInvoicePostingDetails.getHcpcsCode()).isEqualTo(UPDATED_HCPCS_CODE);
        assertThat(testInvoicePostingDetails.getPostingNo()).isEqualTo(UPDATED_POSTING_NO);
        assertThat(testInvoicePostingDetails.getIsManualPosting()).isEqualTo(UPDATED_IS_MANUAL_POSTING);
    }

    @Test
    @Transactional
    void patchNonExistingInvoicePostingDetails() throws Exception {
        int databaseSizeBeforeUpdate = invoicePostingDetailsRepository.findAll().size();
        invoicePostingDetails.setInvoiceLineItemPostingId(count.incrementAndGet());

        // Create the InvoicePostingDetails
        InvoicePostingDetailsDTO invoicePostingDetailsDTO = invoicePostingDetailsMapper.toDto(invoicePostingDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInvoicePostingDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, invoicePostingDetailsDTO.getInvoiceLineItemPostingId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(invoicePostingDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InvoicePostingDetails in the database
        List<InvoicePostingDetails> invoicePostingDetailsList = invoicePostingDetailsRepository.findAll();
        assertThat(invoicePostingDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchInvoicePostingDetails() throws Exception {
        int databaseSizeBeforeUpdate = invoicePostingDetailsRepository.findAll().size();
        invoicePostingDetails.setInvoiceLineItemPostingId(count.incrementAndGet());

        // Create the InvoicePostingDetails
        InvoicePostingDetailsDTO invoicePostingDetailsDTO = invoicePostingDetailsMapper.toDto(invoicePostingDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInvoicePostingDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(invoicePostingDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InvoicePostingDetails in the database
        List<InvoicePostingDetails> invoicePostingDetailsList = invoicePostingDetailsRepository.findAll();
        assertThat(invoicePostingDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamInvoicePostingDetails() throws Exception {
        int databaseSizeBeforeUpdate = invoicePostingDetailsRepository.findAll().size();
        invoicePostingDetails.setInvoiceLineItemPostingId(count.incrementAndGet());

        // Create the InvoicePostingDetails
        InvoicePostingDetailsDTO invoicePostingDetailsDTO = invoicePostingDetailsMapper.toDto(invoicePostingDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInvoicePostingDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(invoicePostingDetailsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the InvoicePostingDetails in the database
        List<InvoicePostingDetails> invoicePostingDetailsList = invoicePostingDetailsRepository.findAll();
        assertThat(invoicePostingDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteInvoicePostingDetails() throws Exception {
        // Initialize the database
        invoicePostingDetailsRepository.saveAndFlush(invoicePostingDetails);

        int databaseSizeBeforeDelete = invoicePostingDetailsRepository.findAll().size();

        // Delete the invoicePostingDetails
        restInvoicePostingDetailsMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, invoicePostingDetails.getInvoiceLineItemPostingId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InvoicePostingDetails> invoicePostingDetailsList = invoicePostingDetailsRepository.findAll();
        assertThat(invoicePostingDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
