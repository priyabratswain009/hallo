package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.InvoicePostingDetails;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.InvoicePostingDetailsRepository;
import com.sunknowledge.dme.rcm.service.dto.InvoicePostingDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.InvoicePostingDetailsMapper;
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
 * Integration tests for the {@link InvoicePostingDetailsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
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

    private static final String DEFAULT_DEPOSIT_ID = "AAAAAAAAAA";
    private static final String UPDATED_DEPOSIT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_RECEIPT_ID = "AAAAAAAAAA";
    private static final String UPDATED_RECEIPT_ID = "BBBBBBBBBB";

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
    private WebTestClient webTestClient;

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
            .invoiceLineItemDetailsId(DEFAULT_INVOICE_LINE_ITEM_DETAILS_ID);
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
            .invoiceLineItemDetailsId(UPDATED_INVOICE_LINE_ITEM_DETAILS_ID);
        return invoicePostingDetails;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(InvoicePostingDetails.class).block();
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
        invoicePostingDetails = createEntity(em);
    }

    @Test
    void createInvoicePostingDetails() throws Exception {
        int databaseSizeBeforeCreate = invoicePostingDetailsRepository.findAll().collectList().block().size();
        // Create the InvoicePostingDetails
        InvoicePostingDetailsDTO invoicePostingDetailsDTO = invoicePostingDetailsMapper.toDto(invoicePostingDetails);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoicePostingDetailsDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the InvoicePostingDetails in the database
        List<InvoicePostingDetails> invoicePostingDetailsList = invoicePostingDetailsRepository.findAll().collectList().block();
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
    }

    @Test
    void createInvoicePostingDetailsWithExistingId() throws Exception {
        // Create the InvoicePostingDetails with an existing ID
        invoicePostingDetails.setInvoiceLineItemPostingId(1L);
        InvoicePostingDetailsDTO invoicePostingDetailsDTO = invoicePostingDetailsMapper.toDto(invoicePostingDetails);

        int databaseSizeBeforeCreate = invoicePostingDetailsRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoicePostingDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the InvoicePostingDetails in the database
        List<InvoicePostingDetails> invoicePostingDetailsList = invoicePostingDetailsRepository.findAll().collectList().block();
        assertThat(invoicePostingDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllInvoicePostingDetails() {
        // Initialize the database
        invoicePostingDetailsRepository.save(invoicePostingDetails).block();

        // Get all the invoicePostingDetailsList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=invoiceLineItemPostingId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].invoiceLineItemPostingId")
            .value(hasItem(invoicePostingDetails.getInvoiceLineItemPostingId().intValue()))
            .jsonPath("$.[*].itemId")
            .value(hasItem(DEFAULT_ITEM_ID.intValue()))
            .jsonPath("$.[*].postingDate")
            .value(hasItem(DEFAULT_POSTING_DATE.toString()))
            .jsonPath("$.[*].postedById")
            .value(hasItem(DEFAULT_POSTED_BY_ID.intValue()))
            .jsonPath("$.[*].postedByName")
            .value(hasItem(DEFAULT_POSTED_BY_NAME))
            .jsonPath("$.[*].postingComment")
            .value(hasItem(DEFAULT_POSTING_COMMENT))
            .jsonPath("$.[*].postType")
            .value(hasItem(DEFAULT_POST_TYPE))
            .jsonPath("$.[*].depositId")
            .value(hasItem(DEFAULT_DEPOSIT_ID))
            .jsonPath("$.[*].receiptId")
            .value(hasItem(DEFAULT_RECEIPT_ID))
            .jsonPath("$.[*].postAmount")
            .value(hasItem(DEFAULT_POST_AMOUNT.doubleValue()))
            .jsonPath("$.[*].postStatus")
            .value(hasItem(DEFAULT_POST_STATUS))
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
            .jsonPath("$.[*].invoicePostingDetailsUuid")
            .value(hasItem(DEFAULT_INVOICE_POSTING_DETAILS_UUID.toString()))
            .jsonPath("$.[*].invoiceNo")
            .value(hasItem(DEFAULT_INVOICE_NO))
            .jsonPath("$.[*].invoiceDate")
            .value(hasItem(DEFAULT_INVOICE_DATE.toString()))
            .jsonPath("$.[*].invoiceLineItemDetailsId")
            .value(hasItem(DEFAULT_INVOICE_LINE_ITEM_DETAILS_ID.intValue()));
    }

    @Test
    void getInvoicePostingDetails() {
        // Initialize the database
        invoicePostingDetailsRepository.save(invoicePostingDetails).block();

        // Get the invoicePostingDetails
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, invoicePostingDetails.getInvoiceLineItemPostingId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.invoiceLineItemPostingId")
            .value(is(invoicePostingDetails.getInvoiceLineItemPostingId().intValue()))
            .jsonPath("$.itemId")
            .value(is(DEFAULT_ITEM_ID.intValue()))
            .jsonPath("$.postingDate")
            .value(is(DEFAULT_POSTING_DATE.toString()))
            .jsonPath("$.postedById")
            .value(is(DEFAULT_POSTED_BY_ID.intValue()))
            .jsonPath("$.postedByName")
            .value(is(DEFAULT_POSTED_BY_NAME))
            .jsonPath("$.postingComment")
            .value(is(DEFAULT_POSTING_COMMENT))
            .jsonPath("$.postType")
            .value(is(DEFAULT_POST_TYPE))
            .jsonPath("$.depositId")
            .value(is(DEFAULT_DEPOSIT_ID))
            .jsonPath("$.receiptId")
            .value(is(DEFAULT_RECEIPT_ID))
            .jsonPath("$.postAmount")
            .value(is(DEFAULT_POST_AMOUNT.doubleValue()))
            .jsonPath("$.postStatus")
            .value(is(DEFAULT_POST_STATUS))
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
            .jsonPath("$.invoicePostingDetailsUuid")
            .value(is(DEFAULT_INVOICE_POSTING_DETAILS_UUID.toString()))
            .jsonPath("$.invoiceNo")
            .value(is(DEFAULT_INVOICE_NO))
            .jsonPath("$.invoiceDate")
            .value(is(DEFAULT_INVOICE_DATE.toString()))
            .jsonPath("$.invoiceLineItemDetailsId")
            .value(is(DEFAULT_INVOICE_LINE_ITEM_DETAILS_ID.intValue()));
    }

    @Test
    void getNonExistingInvoicePostingDetails() {
        // Get the invoicePostingDetails
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putNewInvoicePostingDetails() throws Exception {
        // Initialize the database
        invoicePostingDetailsRepository.save(invoicePostingDetails).block();

        int databaseSizeBeforeUpdate = invoicePostingDetailsRepository.findAll().collectList().block().size();

        // Update the invoicePostingDetails
        InvoicePostingDetails updatedInvoicePostingDetails = invoicePostingDetailsRepository
            .findById(invoicePostingDetails.getInvoiceLineItemPostingId())
            .block();
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
            .invoiceLineItemDetailsId(UPDATED_INVOICE_LINE_ITEM_DETAILS_ID);
        InvoicePostingDetailsDTO invoicePostingDetailsDTO = invoicePostingDetailsMapper.toDto(updatedInvoicePostingDetails);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, invoicePostingDetailsDTO.getInvoiceLineItemPostingId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoicePostingDetailsDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the InvoicePostingDetails in the database
        List<InvoicePostingDetails> invoicePostingDetailsList = invoicePostingDetailsRepository.findAll().collectList().block();
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
    }

    @Test
    void putNonExistingInvoicePostingDetails() throws Exception {
        int databaseSizeBeforeUpdate = invoicePostingDetailsRepository.findAll().collectList().block().size();
        invoicePostingDetails.setInvoiceLineItemPostingId(count.incrementAndGet());

        // Create the InvoicePostingDetails
        InvoicePostingDetailsDTO invoicePostingDetailsDTO = invoicePostingDetailsMapper.toDto(invoicePostingDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, invoicePostingDetailsDTO.getInvoiceLineItemPostingId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoicePostingDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the InvoicePostingDetails in the database
        List<InvoicePostingDetails> invoicePostingDetailsList = invoicePostingDetailsRepository.findAll().collectList().block();
        assertThat(invoicePostingDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchInvoicePostingDetails() throws Exception {
        int databaseSizeBeforeUpdate = invoicePostingDetailsRepository.findAll().collectList().block().size();
        invoicePostingDetails.setInvoiceLineItemPostingId(count.incrementAndGet());

        // Create the InvoicePostingDetails
        InvoicePostingDetailsDTO invoicePostingDetailsDTO = invoicePostingDetailsMapper.toDto(invoicePostingDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoicePostingDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the InvoicePostingDetails in the database
        List<InvoicePostingDetails> invoicePostingDetailsList = invoicePostingDetailsRepository.findAll().collectList().block();
        assertThat(invoicePostingDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamInvoicePostingDetails() throws Exception {
        int databaseSizeBeforeUpdate = invoicePostingDetailsRepository.findAll().collectList().block().size();
        invoicePostingDetails.setInvoiceLineItemPostingId(count.incrementAndGet());

        // Create the InvoicePostingDetails
        InvoicePostingDetailsDTO invoicePostingDetailsDTO = invoicePostingDetailsMapper.toDto(invoicePostingDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoicePostingDetailsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the InvoicePostingDetails in the database
        List<InvoicePostingDetails> invoicePostingDetailsList = invoicePostingDetailsRepository.findAll().collectList().block();
        assertThat(invoicePostingDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateInvoicePostingDetailsWithPatch() throws Exception {
        // Initialize the database
        invoicePostingDetailsRepository.save(invoicePostingDetails).block();

        int databaseSizeBeforeUpdate = invoicePostingDetailsRepository.findAll().collectList().block().size();

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

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedInvoicePostingDetails.getInvoiceLineItemPostingId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedInvoicePostingDetails))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the InvoicePostingDetails in the database
        List<InvoicePostingDetails> invoicePostingDetailsList = invoicePostingDetailsRepository.findAll().collectList().block();
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
    }

    @Test
    void fullUpdateInvoicePostingDetailsWithPatch() throws Exception {
        // Initialize the database
        invoicePostingDetailsRepository.save(invoicePostingDetails).block();

        int databaseSizeBeforeUpdate = invoicePostingDetailsRepository.findAll().collectList().block().size();

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
            .invoiceLineItemDetailsId(UPDATED_INVOICE_LINE_ITEM_DETAILS_ID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedInvoicePostingDetails.getInvoiceLineItemPostingId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedInvoicePostingDetails))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the InvoicePostingDetails in the database
        List<InvoicePostingDetails> invoicePostingDetailsList = invoicePostingDetailsRepository.findAll().collectList().block();
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
    }

    @Test
    void patchNonExistingInvoicePostingDetails() throws Exception {
        int databaseSizeBeforeUpdate = invoicePostingDetailsRepository.findAll().collectList().block().size();
        invoicePostingDetails.setInvoiceLineItemPostingId(count.incrementAndGet());

        // Create the InvoicePostingDetails
        InvoicePostingDetailsDTO invoicePostingDetailsDTO = invoicePostingDetailsMapper.toDto(invoicePostingDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, invoicePostingDetailsDTO.getInvoiceLineItemPostingId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoicePostingDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the InvoicePostingDetails in the database
        List<InvoicePostingDetails> invoicePostingDetailsList = invoicePostingDetailsRepository.findAll().collectList().block();
        assertThat(invoicePostingDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchInvoicePostingDetails() throws Exception {
        int databaseSizeBeforeUpdate = invoicePostingDetailsRepository.findAll().collectList().block().size();
        invoicePostingDetails.setInvoiceLineItemPostingId(count.incrementAndGet());

        // Create the InvoicePostingDetails
        InvoicePostingDetailsDTO invoicePostingDetailsDTO = invoicePostingDetailsMapper.toDto(invoicePostingDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoicePostingDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the InvoicePostingDetails in the database
        List<InvoicePostingDetails> invoicePostingDetailsList = invoicePostingDetailsRepository.findAll().collectList().block();
        assertThat(invoicePostingDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamInvoicePostingDetails() throws Exception {
        int databaseSizeBeforeUpdate = invoicePostingDetailsRepository.findAll().collectList().block().size();
        invoicePostingDetails.setInvoiceLineItemPostingId(count.incrementAndGet());

        // Create the InvoicePostingDetails
        InvoicePostingDetailsDTO invoicePostingDetailsDTO = invoicePostingDetailsMapper.toDto(invoicePostingDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoicePostingDetailsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the InvoicePostingDetails in the database
        List<InvoicePostingDetails> invoicePostingDetailsList = invoicePostingDetailsRepository.findAll().collectList().block();
        assertThat(invoicePostingDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteInvoicePostingDetails() {
        // Initialize the database
        invoicePostingDetailsRepository.save(invoicePostingDetails).block();

        int databaseSizeBeforeDelete = invoicePostingDetailsRepository.findAll().collectList().block().size();

        // Delete the invoicePostingDetails
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, invoicePostingDetails.getInvoiceLineItemPostingId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<InvoicePostingDetails> invoicePostingDetailsList = invoicePostingDetailsRepository.findAll().collectList().block();
        assertThat(invoicePostingDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
