package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.InvoiceLineItemDetails;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.InvoiceLineItemDetailsRepository;
import com.sunknowledge.dme.rcm.service.dto.InvoiceLineItemDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.InvoiceLineItemDetailsMapper;
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
 * Integration tests for the {@link InvoiceLineItemDetailsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class InvoiceLineItemDetailsResourceIT {

    private static final String DEFAULT_PRIMARY_INVOICE_NO = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_INVOICE_NO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_INVOICE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_INVOICE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_ITEM_ID = 1L;
    private static final Long UPDATED_ITEM_ID = 2L;

    private static final Long DEFAULT_ITEM_QTY = 1L;
    private static final Long UPDATED_ITEM_QTY = 2L;

    private static final String DEFAULT_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_HCPCS_CODE = "AAAAAAAAAA";
    private static final String UPDATED_HCPCS_CODE = "BBBBBBBBBB";

    private static final Long DEFAULT_CHARGED_AMOUNT = 1L;
    private static final Long UPDATED_CHARGED_AMOUNT = 2L;

    private static final Double DEFAULT_ALLOW_AMOUNT = 1D;
    private static final Double UPDATED_ALLOW_AMOUNT = 2D;

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

    private static final UUID DEFAULT_INVOICE_LINE_ITEM_DETAILS_UUID = UUID.randomUUID();
    private static final UUID UPDATED_INVOICE_LINE_ITEM_DETAILS_UUID = UUID.randomUUID();

    private static final String DEFAULT_SECONDARY_INVOICE_NO = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY_INVOICE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_TERTIARY_INVOICE_NO = "AAAAAAAAAA";
    private static final String UPDATED_TERTIARY_INVOICE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_INVOICE_NO = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_INVOICE_NO = "BBBBBBBBBB";

    private static final Long DEFAULT_PRIMARY_INVOICE_ID = 1L;
    private static final Long UPDATED_PRIMARY_INVOICE_ID = 2L;

    private static final Long DEFAULT_SECONDARY_INVOICE_ID = 1L;
    private static final Long UPDATED_SECONDARY_INVOICE_ID = 2L;

    private static final Long DEFAULT_TERTIARY_INVOICE_ID = 1L;
    private static final Long UPDATED_TERTIARY_INVOICE_ID = 2L;

    private static final Long DEFAULT_PATIENT_INVOICE_ID = 1L;
    private static final Long UPDATED_PATIENT_INVOICE_ID = 2L;

    private static final String ENTITY_API_URL = "/api/invoice-line-item-details";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{invoiceLineItemDetailsId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private InvoiceLineItemDetailsRepository invoiceLineItemDetailsRepository;

    @Autowired
    private InvoiceLineItemDetailsMapper invoiceLineItemDetailsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private InvoiceLineItemDetails invoiceLineItemDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InvoiceLineItemDetails createEntity(EntityManager em) {
        InvoiceLineItemDetails invoiceLineItemDetails = new InvoiceLineItemDetails()
            .primaryInvoiceNo(DEFAULT_PRIMARY_INVOICE_NO)
            .invoiceDate(DEFAULT_INVOICE_DATE)
            .itemId(DEFAULT_ITEM_ID)
            .itemQty(DEFAULT_ITEM_QTY)
            .itemName(DEFAULT_ITEM_NAME)
            .hcpcsCode(DEFAULT_HCPCS_CODE)
            .chargedAmount(DEFAULT_CHARGED_AMOUNT)
            .allowAmount(DEFAULT_ALLOW_AMOUNT)
            .status(DEFAULT_STATUS)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .invoiceLineItemDetailsUuid(DEFAULT_INVOICE_LINE_ITEM_DETAILS_UUID)
            .secondaryInvoiceNo(DEFAULT_SECONDARY_INVOICE_NO)
            .tertiaryInvoiceNo(DEFAULT_TERTIARY_INVOICE_NO)
            .patientInvoiceNo(DEFAULT_PATIENT_INVOICE_NO)
            .primaryInvoiceId(DEFAULT_PRIMARY_INVOICE_ID)
            .secondaryInvoiceId(DEFAULT_SECONDARY_INVOICE_ID)
            .tertiaryInvoiceId(DEFAULT_TERTIARY_INVOICE_ID)
            .patientInvoiceId(DEFAULT_PATIENT_INVOICE_ID);
        return invoiceLineItemDetails;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InvoiceLineItemDetails createUpdatedEntity(EntityManager em) {
        InvoiceLineItemDetails invoiceLineItemDetails = new InvoiceLineItemDetails()
            .primaryInvoiceNo(UPDATED_PRIMARY_INVOICE_NO)
            .invoiceDate(UPDATED_INVOICE_DATE)
            .itemId(UPDATED_ITEM_ID)
            .itemQty(UPDATED_ITEM_QTY)
            .itemName(UPDATED_ITEM_NAME)
            .hcpcsCode(UPDATED_HCPCS_CODE)
            .chargedAmount(UPDATED_CHARGED_AMOUNT)
            .allowAmount(UPDATED_ALLOW_AMOUNT)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .invoiceLineItemDetailsUuid(UPDATED_INVOICE_LINE_ITEM_DETAILS_UUID)
            .secondaryInvoiceNo(UPDATED_SECONDARY_INVOICE_NO)
            .tertiaryInvoiceNo(UPDATED_TERTIARY_INVOICE_NO)
            .patientInvoiceNo(UPDATED_PATIENT_INVOICE_NO)
            .primaryInvoiceId(UPDATED_PRIMARY_INVOICE_ID)
            .secondaryInvoiceId(UPDATED_SECONDARY_INVOICE_ID)
            .tertiaryInvoiceId(UPDATED_TERTIARY_INVOICE_ID)
            .patientInvoiceId(UPDATED_PATIENT_INVOICE_ID);
        return invoiceLineItemDetails;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(InvoiceLineItemDetails.class).block();
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
        invoiceLineItemDetails = createEntity(em);
    }

    @Test
    void createInvoiceLineItemDetails() throws Exception {
        int databaseSizeBeforeCreate = invoiceLineItemDetailsRepository.findAll().collectList().block().size();
        // Create the InvoiceLineItemDetails
        InvoiceLineItemDetailsDTO invoiceLineItemDetailsDTO = invoiceLineItemDetailsMapper.toDto(invoiceLineItemDetails);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoiceLineItemDetailsDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the InvoiceLineItemDetails in the database
        List<InvoiceLineItemDetails> invoiceLineItemDetailsList = invoiceLineItemDetailsRepository.findAll().collectList().block();
        assertThat(invoiceLineItemDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        InvoiceLineItemDetails testInvoiceLineItemDetails = invoiceLineItemDetailsList.get(invoiceLineItemDetailsList.size() - 1);
        assertThat(testInvoiceLineItemDetails.getPrimaryInvoiceNo()).isEqualTo(DEFAULT_PRIMARY_INVOICE_NO);
        assertThat(testInvoiceLineItemDetails.getInvoiceDate()).isEqualTo(DEFAULT_INVOICE_DATE);
        assertThat(testInvoiceLineItemDetails.getItemId()).isEqualTo(DEFAULT_ITEM_ID);
        assertThat(testInvoiceLineItemDetails.getItemQty()).isEqualTo(DEFAULT_ITEM_QTY);
        assertThat(testInvoiceLineItemDetails.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testInvoiceLineItemDetails.getHcpcsCode()).isEqualTo(DEFAULT_HCPCS_CODE);
        assertThat(testInvoiceLineItemDetails.getChargedAmount()).isEqualTo(DEFAULT_CHARGED_AMOUNT);
        assertThat(testInvoiceLineItemDetails.getAllowAmount()).isEqualTo(DEFAULT_ALLOW_AMOUNT);
        assertThat(testInvoiceLineItemDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testInvoiceLineItemDetails.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testInvoiceLineItemDetails.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testInvoiceLineItemDetails.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testInvoiceLineItemDetails.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testInvoiceLineItemDetails.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testInvoiceLineItemDetails.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testInvoiceLineItemDetails.getInvoiceLineItemDetailsUuid()).isEqualTo(DEFAULT_INVOICE_LINE_ITEM_DETAILS_UUID);
        assertThat(testInvoiceLineItemDetails.getSecondaryInvoiceNo()).isEqualTo(DEFAULT_SECONDARY_INVOICE_NO);
        assertThat(testInvoiceLineItemDetails.getTertiaryInvoiceNo()).isEqualTo(DEFAULT_TERTIARY_INVOICE_NO);
        assertThat(testInvoiceLineItemDetails.getPatientInvoiceNo()).isEqualTo(DEFAULT_PATIENT_INVOICE_NO);
        assertThat(testInvoiceLineItemDetails.getPrimaryInvoiceId()).isEqualTo(DEFAULT_PRIMARY_INVOICE_ID);
        assertThat(testInvoiceLineItemDetails.getSecondaryInvoiceId()).isEqualTo(DEFAULT_SECONDARY_INVOICE_ID);
        assertThat(testInvoiceLineItemDetails.getTertiaryInvoiceId()).isEqualTo(DEFAULT_TERTIARY_INVOICE_ID);
        assertThat(testInvoiceLineItemDetails.getPatientInvoiceId()).isEqualTo(DEFAULT_PATIENT_INVOICE_ID);
    }

    @Test
    void createInvoiceLineItemDetailsWithExistingId() throws Exception {
        // Create the InvoiceLineItemDetails with an existing ID
        invoiceLineItemDetails.setInvoiceLineItemDetailsId(1L);
        InvoiceLineItemDetailsDTO invoiceLineItemDetailsDTO = invoiceLineItemDetailsMapper.toDto(invoiceLineItemDetails);

        int databaseSizeBeforeCreate = invoiceLineItemDetailsRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoiceLineItemDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the InvoiceLineItemDetails in the database
        List<InvoiceLineItemDetails> invoiceLineItemDetailsList = invoiceLineItemDetailsRepository.findAll().collectList().block();
        assertThat(invoiceLineItemDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllInvoiceLineItemDetails() {
        // Initialize the database
        invoiceLineItemDetailsRepository.save(invoiceLineItemDetails).block();

        // Get all the invoiceLineItemDetailsList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=invoiceLineItemDetailsId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].invoiceLineItemDetailsId")
            .value(hasItem(invoiceLineItemDetails.getInvoiceLineItemDetailsId().intValue()))
            .jsonPath("$.[*].primaryInvoiceNo")
            .value(hasItem(DEFAULT_PRIMARY_INVOICE_NO))
            .jsonPath("$.[*].invoiceDate")
            .value(hasItem(DEFAULT_INVOICE_DATE.toString()))
            .jsonPath("$.[*].itemId")
            .value(hasItem(DEFAULT_ITEM_ID.intValue()))
            .jsonPath("$.[*].itemQty")
            .value(hasItem(DEFAULT_ITEM_QTY.intValue()))
            .jsonPath("$.[*].itemName")
            .value(hasItem(DEFAULT_ITEM_NAME))
            .jsonPath("$.[*].hcpcsCode")
            .value(hasItem(DEFAULT_HCPCS_CODE))
            .jsonPath("$.[*].chargedAmount")
            .value(hasItem(DEFAULT_CHARGED_AMOUNT.intValue()))
            .jsonPath("$.[*].allowAmount")
            .value(hasItem(DEFAULT_ALLOW_AMOUNT.doubleValue()))
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
            .jsonPath("$.[*].invoiceLineItemDetailsUuid")
            .value(hasItem(DEFAULT_INVOICE_LINE_ITEM_DETAILS_UUID.toString()))
            .jsonPath("$.[*].secondaryInvoiceNo")
            .value(hasItem(DEFAULT_SECONDARY_INVOICE_NO))
            .jsonPath("$.[*].tertiaryInvoiceNo")
            .value(hasItem(DEFAULT_TERTIARY_INVOICE_NO))
            .jsonPath("$.[*].patientInvoiceNo")
            .value(hasItem(DEFAULT_PATIENT_INVOICE_NO))
            .jsonPath("$.[*].primaryInvoiceId")
            .value(hasItem(DEFAULT_PRIMARY_INVOICE_ID.intValue()))
            .jsonPath("$.[*].secondaryInvoiceId")
            .value(hasItem(DEFAULT_SECONDARY_INVOICE_ID.intValue()))
            .jsonPath("$.[*].tertiaryInvoiceId")
            .value(hasItem(DEFAULT_TERTIARY_INVOICE_ID.intValue()))
            .jsonPath("$.[*].patientInvoiceId")
            .value(hasItem(DEFAULT_PATIENT_INVOICE_ID.intValue()));
    }

    @Test
    void getInvoiceLineItemDetails() {
        // Initialize the database
        invoiceLineItemDetailsRepository.save(invoiceLineItemDetails).block();

        // Get the invoiceLineItemDetails
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, invoiceLineItemDetails.getInvoiceLineItemDetailsId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.invoiceLineItemDetailsId")
            .value(is(invoiceLineItemDetails.getInvoiceLineItemDetailsId().intValue()))
            .jsonPath("$.primaryInvoiceNo")
            .value(is(DEFAULT_PRIMARY_INVOICE_NO))
            .jsonPath("$.invoiceDate")
            .value(is(DEFAULT_INVOICE_DATE.toString()))
            .jsonPath("$.itemId")
            .value(is(DEFAULT_ITEM_ID.intValue()))
            .jsonPath("$.itemQty")
            .value(is(DEFAULT_ITEM_QTY.intValue()))
            .jsonPath("$.itemName")
            .value(is(DEFAULT_ITEM_NAME))
            .jsonPath("$.hcpcsCode")
            .value(is(DEFAULT_HCPCS_CODE))
            .jsonPath("$.chargedAmount")
            .value(is(DEFAULT_CHARGED_AMOUNT.intValue()))
            .jsonPath("$.allowAmount")
            .value(is(DEFAULT_ALLOW_AMOUNT.doubleValue()))
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
            .jsonPath("$.invoiceLineItemDetailsUuid")
            .value(is(DEFAULT_INVOICE_LINE_ITEM_DETAILS_UUID.toString()))
            .jsonPath("$.secondaryInvoiceNo")
            .value(is(DEFAULT_SECONDARY_INVOICE_NO))
            .jsonPath("$.tertiaryInvoiceNo")
            .value(is(DEFAULT_TERTIARY_INVOICE_NO))
            .jsonPath("$.patientInvoiceNo")
            .value(is(DEFAULT_PATIENT_INVOICE_NO))
            .jsonPath("$.primaryInvoiceId")
            .value(is(DEFAULT_PRIMARY_INVOICE_ID.intValue()))
            .jsonPath("$.secondaryInvoiceId")
            .value(is(DEFAULT_SECONDARY_INVOICE_ID.intValue()))
            .jsonPath("$.tertiaryInvoiceId")
            .value(is(DEFAULT_TERTIARY_INVOICE_ID.intValue()))
            .jsonPath("$.patientInvoiceId")
            .value(is(DEFAULT_PATIENT_INVOICE_ID.intValue()));
    }

    @Test
    void getNonExistingInvoiceLineItemDetails() {
        // Get the invoiceLineItemDetails
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingInvoiceLineItemDetails() throws Exception {
        // Initialize the database
        invoiceLineItemDetailsRepository.save(invoiceLineItemDetails).block();

        int databaseSizeBeforeUpdate = invoiceLineItemDetailsRepository.findAll().collectList().block().size();

        // Update the invoiceLineItemDetails
        InvoiceLineItemDetails updatedInvoiceLineItemDetails = invoiceLineItemDetailsRepository
            .findById(invoiceLineItemDetails.getInvoiceLineItemDetailsId())
            .block();
        updatedInvoiceLineItemDetails
            .primaryInvoiceNo(UPDATED_PRIMARY_INVOICE_NO)
            .invoiceDate(UPDATED_INVOICE_DATE)
            .itemId(UPDATED_ITEM_ID)
            .itemQty(UPDATED_ITEM_QTY)
            .itemName(UPDATED_ITEM_NAME)
            .hcpcsCode(UPDATED_HCPCS_CODE)
            .chargedAmount(UPDATED_CHARGED_AMOUNT)
            .allowAmount(UPDATED_ALLOW_AMOUNT)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .invoiceLineItemDetailsUuid(UPDATED_INVOICE_LINE_ITEM_DETAILS_UUID)
            .secondaryInvoiceNo(UPDATED_SECONDARY_INVOICE_NO)
            .tertiaryInvoiceNo(UPDATED_TERTIARY_INVOICE_NO)
            .patientInvoiceNo(UPDATED_PATIENT_INVOICE_NO)
            .primaryInvoiceId(UPDATED_PRIMARY_INVOICE_ID)
            .secondaryInvoiceId(UPDATED_SECONDARY_INVOICE_ID)
            .tertiaryInvoiceId(UPDATED_TERTIARY_INVOICE_ID)
            .patientInvoiceId(UPDATED_PATIENT_INVOICE_ID);
        InvoiceLineItemDetailsDTO invoiceLineItemDetailsDTO = invoiceLineItemDetailsMapper.toDto(updatedInvoiceLineItemDetails);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, invoiceLineItemDetailsDTO.getInvoiceLineItemDetailsId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoiceLineItemDetailsDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the InvoiceLineItemDetails in the database
        List<InvoiceLineItemDetails> invoiceLineItemDetailsList = invoiceLineItemDetailsRepository.findAll().collectList().block();
        assertThat(invoiceLineItemDetailsList).hasSize(databaseSizeBeforeUpdate);
        InvoiceLineItemDetails testInvoiceLineItemDetails = invoiceLineItemDetailsList.get(invoiceLineItemDetailsList.size() - 1);
        assertThat(testInvoiceLineItemDetails.getPrimaryInvoiceNo()).isEqualTo(UPDATED_PRIMARY_INVOICE_NO);
        assertThat(testInvoiceLineItemDetails.getInvoiceDate()).isEqualTo(UPDATED_INVOICE_DATE);
        assertThat(testInvoiceLineItemDetails.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testInvoiceLineItemDetails.getItemQty()).isEqualTo(UPDATED_ITEM_QTY);
        assertThat(testInvoiceLineItemDetails.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testInvoiceLineItemDetails.getHcpcsCode()).isEqualTo(UPDATED_HCPCS_CODE);
        assertThat(testInvoiceLineItemDetails.getChargedAmount()).isEqualTo(UPDATED_CHARGED_AMOUNT);
        assertThat(testInvoiceLineItemDetails.getAllowAmount()).isEqualTo(UPDATED_ALLOW_AMOUNT);
        assertThat(testInvoiceLineItemDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testInvoiceLineItemDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testInvoiceLineItemDetails.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testInvoiceLineItemDetails.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testInvoiceLineItemDetails.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testInvoiceLineItemDetails.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testInvoiceLineItemDetails.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testInvoiceLineItemDetails.getInvoiceLineItemDetailsUuid()).isEqualTo(UPDATED_INVOICE_LINE_ITEM_DETAILS_UUID);
        assertThat(testInvoiceLineItemDetails.getSecondaryInvoiceNo()).isEqualTo(UPDATED_SECONDARY_INVOICE_NO);
        assertThat(testInvoiceLineItemDetails.getTertiaryInvoiceNo()).isEqualTo(UPDATED_TERTIARY_INVOICE_NO);
        assertThat(testInvoiceLineItemDetails.getPatientInvoiceNo()).isEqualTo(UPDATED_PATIENT_INVOICE_NO);
        assertThat(testInvoiceLineItemDetails.getPrimaryInvoiceId()).isEqualTo(UPDATED_PRIMARY_INVOICE_ID);
        assertThat(testInvoiceLineItemDetails.getSecondaryInvoiceId()).isEqualTo(UPDATED_SECONDARY_INVOICE_ID);
        assertThat(testInvoiceLineItemDetails.getTertiaryInvoiceId()).isEqualTo(UPDATED_TERTIARY_INVOICE_ID);
        assertThat(testInvoiceLineItemDetails.getPatientInvoiceId()).isEqualTo(UPDATED_PATIENT_INVOICE_ID);
    }

    @Test
    void putNonExistingInvoiceLineItemDetails() throws Exception {
        int databaseSizeBeforeUpdate = invoiceLineItemDetailsRepository.findAll().collectList().block().size();
        invoiceLineItemDetails.setInvoiceLineItemDetailsId(count.incrementAndGet());

        // Create the InvoiceLineItemDetails
        InvoiceLineItemDetailsDTO invoiceLineItemDetailsDTO = invoiceLineItemDetailsMapper.toDto(invoiceLineItemDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, invoiceLineItemDetailsDTO.getInvoiceLineItemDetailsId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoiceLineItemDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the InvoiceLineItemDetails in the database
        List<InvoiceLineItemDetails> invoiceLineItemDetailsList = invoiceLineItemDetailsRepository.findAll().collectList().block();
        assertThat(invoiceLineItemDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchInvoiceLineItemDetails() throws Exception {
        int databaseSizeBeforeUpdate = invoiceLineItemDetailsRepository.findAll().collectList().block().size();
        invoiceLineItemDetails.setInvoiceLineItemDetailsId(count.incrementAndGet());

        // Create the InvoiceLineItemDetails
        InvoiceLineItemDetailsDTO invoiceLineItemDetailsDTO = invoiceLineItemDetailsMapper.toDto(invoiceLineItemDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoiceLineItemDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the InvoiceLineItemDetails in the database
        List<InvoiceLineItemDetails> invoiceLineItemDetailsList = invoiceLineItemDetailsRepository.findAll().collectList().block();
        assertThat(invoiceLineItemDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamInvoiceLineItemDetails() throws Exception {
        int databaseSizeBeforeUpdate = invoiceLineItemDetailsRepository.findAll().collectList().block().size();
        invoiceLineItemDetails.setInvoiceLineItemDetailsId(count.incrementAndGet());

        // Create the InvoiceLineItemDetails
        InvoiceLineItemDetailsDTO invoiceLineItemDetailsDTO = invoiceLineItemDetailsMapper.toDto(invoiceLineItemDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoiceLineItemDetailsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the InvoiceLineItemDetails in the database
        List<InvoiceLineItemDetails> invoiceLineItemDetailsList = invoiceLineItemDetailsRepository.findAll().collectList().block();
        assertThat(invoiceLineItemDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateInvoiceLineItemDetailsWithPatch() throws Exception {
        // Initialize the database
        invoiceLineItemDetailsRepository.save(invoiceLineItemDetails).block();

        int databaseSizeBeforeUpdate = invoiceLineItemDetailsRepository.findAll().collectList().block().size();

        // Update the invoiceLineItemDetails using partial update
        InvoiceLineItemDetails partialUpdatedInvoiceLineItemDetails = new InvoiceLineItemDetails();
        partialUpdatedInvoiceLineItemDetails.setInvoiceLineItemDetailsId(invoiceLineItemDetails.getInvoiceLineItemDetailsId());

        partialUpdatedInvoiceLineItemDetails
            .primaryInvoiceNo(UPDATED_PRIMARY_INVOICE_NO)
            .itemQty(UPDATED_ITEM_QTY)
            .hcpcsCode(UPDATED_HCPCS_CODE)
            .chargedAmount(UPDATED_CHARGED_AMOUNT)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .invoiceLineItemDetailsUuid(UPDATED_INVOICE_LINE_ITEM_DETAILS_UUID)
            .secondaryInvoiceNo(UPDATED_SECONDARY_INVOICE_NO)
            .tertiaryInvoiceNo(UPDATED_TERTIARY_INVOICE_NO)
            .patientInvoiceNo(UPDATED_PATIENT_INVOICE_NO);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedInvoiceLineItemDetails.getInvoiceLineItemDetailsId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedInvoiceLineItemDetails))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the InvoiceLineItemDetails in the database
        List<InvoiceLineItemDetails> invoiceLineItemDetailsList = invoiceLineItemDetailsRepository.findAll().collectList().block();
        assertThat(invoiceLineItemDetailsList).hasSize(databaseSizeBeforeUpdate);
        InvoiceLineItemDetails testInvoiceLineItemDetails = invoiceLineItemDetailsList.get(invoiceLineItemDetailsList.size() - 1);
        assertThat(testInvoiceLineItemDetails.getPrimaryInvoiceNo()).isEqualTo(UPDATED_PRIMARY_INVOICE_NO);
        assertThat(testInvoiceLineItemDetails.getInvoiceDate()).isEqualTo(DEFAULT_INVOICE_DATE);
        assertThat(testInvoiceLineItemDetails.getItemId()).isEqualTo(DEFAULT_ITEM_ID);
        assertThat(testInvoiceLineItemDetails.getItemQty()).isEqualTo(UPDATED_ITEM_QTY);
        assertThat(testInvoiceLineItemDetails.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testInvoiceLineItemDetails.getHcpcsCode()).isEqualTo(UPDATED_HCPCS_CODE);
        assertThat(testInvoiceLineItemDetails.getChargedAmount()).isEqualTo(UPDATED_CHARGED_AMOUNT);
        assertThat(testInvoiceLineItemDetails.getAllowAmount()).isEqualTo(DEFAULT_ALLOW_AMOUNT);
        assertThat(testInvoiceLineItemDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testInvoiceLineItemDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testInvoiceLineItemDetails.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testInvoiceLineItemDetails.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testInvoiceLineItemDetails.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testInvoiceLineItemDetails.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testInvoiceLineItemDetails.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testInvoiceLineItemDetails.getInvoiceLineItemDetailsUuid()).isEqualTo(UPDATED_INVOICE_LINE_ITEM_DETAILS_UUID);
        assertThat(testInvoiceLineItemDetails.getSecondaryInvoiceNo()).isEqualTo(UPDATED_SECONDARY_INVOICE_NO);
        assertThat(testInvoiceLineItemDetails.getTertiaryInvoiceNo()).isEqualTo(UPDATED_TERTIARY_INVOICE_NO);
        assertThat(testInvoiceLineItemDetails.getPatientInvoiceNo()).isEqualTo(UPDATED_PATIENT_INVOICE_NO);
        assertThat(testInvoiceLineItemDetails.getPrimaryInvoiceId()).isEqualTo(DEFAULT_PRIMARY_INVOICE_ID);
        assertThat(testInvoiceLineItemDetails.getSecondaryInvoiceId()).isEqualTo(DEFAULT_SECONDARY_INVOICE_ID);
        assertThat(testInvoiceLineItemDetails.getTertiaryInvoiceId()).isEqualTo(DEFAULT_TERTIARY_INVOICE_ID);
        assertThat(testInvoiceLineItemDetails.getPatientInvoiceId()).isEqualTo(DEFAULT_PATIENT_INVOICE_ID);
    }

    @Test
    void fullUpdateInvoiceLineItemDetailsWithPatch() throws Exception {
        // Initialize the database
        invoiceLineItemDetailsRepository.save(invoiceLineItemDetails).block();

        int databaseSizeBeforeUpdate = invoiceLineItemDetailsRepository.findAll().collectList().block().size();

        // Update the invoiceLineItemDetails using partial update
        InvoiceLineItemDetails partialUpdatedInvoiceLineItemDetails = new InvoiceLineItemDetails();
        partialUpdatedInvoiceLineItemDetails.setInvoiceLineItemDetailsId(invoiceLineItemDetails.getInvoiceLineItemDetailsId());

        partialUpdatedInvoiceLineItemDetails
            .primaryInvoiceNo(UPDATED_PRIMARY_INVOICE_NO)
            .invoiceDate(UPDATED_INVOICE_DATE)
            .itemId(UPDATED_ITEM_ID)
            .itemQty(UPDATED_ITEM_QTY)
            .itemName(UPDATED_ITEM_NAME)
            .hcpcsCode(UPDATED_HCPCS_CODE)
            .chargedAmount(UPDATED_CHARGED_AMOUNT)
            .allowAmount(UPDATED_ALLOW_AMOUNT)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .invoiceLineItemDetailsUuid(UPDATED_INVOICE_LINE_ITEM_DETAILS_UUID)
            .secondaryInvoiceNo(UPDATED_SECONDARY_INVOICE_NO)
            .tertiaryInvoiceNo(UPDATED_TERTIARY_INVOICE_NO)
            .patientInvoiceNo(UPDATED_PATIENT_INVOICE_NO)
            .primaryInvoiceId(UPDATED_PRIMARY_INVOICE_ID)
            .secondaryInvoiceId(UPDATED_SECONDARY_INVOICE_ID)
            .tertiaryInvoiceId(UPDATED_TERTIARY_INVOICE_ID)
            .patientInvoiceId(UPDATED_PATIENT_INVOICE_ID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedInvoiceLineItemDetails.getInvoiceLineItemDetailsId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedInvoiceLineItemDetails))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the InvoiceLineItemDetails in the database
        List<InvoiceLineItemDetails> invoiceLineItemDetailsList = invoiceLineItemDetailsRepository.findAll().collectList().block();
        assertThat(invoiceLineItemDetailsList).hasSize(databaseSizeBeforeUpdate);
        InvoiceLineItemDetails testInvoiceLineItemDetails = invoiceLineItemDetailsList.get(invoiceLineItemDetailsList.size() - 1);
        assertThat(testInvoiceLineItemDetails.getPrimaryInvoiceNo()).isEqualTo(UPDATED_PRIMARY_INVOICE_NO);
        assertThat(testInvoiceLineItemDetails.getInvoiceDate()).isEqualTo(UPDATED_INVOICE_DATE);
        assertThat(testInvoiceLineItemDetails.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testInvoiceLineItemDetails.getItemQty()).isEqualTo(UPDATED_ITEM_QTY);
        assertThat(testInvoiceLineItemDetails.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testInvoiceLineItemDetails.getHcpcsCode()).isEqualTo(UPDATED_HCPCS_CODE);
        assertThat(testInvoiceLineItemDetails.getChargedAmount()).isEqualTo(UPDATED_CHARGED_AMOUNT);
        assertThat(testInvoiceLineItemDetails.getAllowAmount()).isEqualTo(UPDATED_ALLOW_AMOUNT);
        assertThat(testInvoiceLineItemDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testInvoiceLineItemDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testInvoiceLineItemDetails.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testInvoiceLineItemDetails.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testInvoiceLineItemDetails.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testInvoiceLineItemDetails.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testInvoiceLineItemDetails.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testInvoiceLineItemDetails.getInvoiceLineItemDetailsUuid()).isEqualTo(UPDATED_INVOICE_LINE_ITEM_DETAILS_UUID);
        assertThat(testInvoiceLineItemDetails.getSecondaryInvoiceNo()).isEqualTo(UPDATED_SECONDARY_INVOICE_NO);
        assertThat(testInvoiceLineItemDetails.getTertiaryInvoiceNo()).isEqualTo(UPDATED_TERTIARY_INVOICE_NO);
        assertThat(testInvoiceLineItemDetails.getPatientInvoiceNo()).isEqualTo(UPDATED_PATIENT_INVOICE_NO);
        assertThat(testInvoiceLineItemDetails.getPrimaryInvoiceId()).isEqualTo(UPDATED_PRIMARY_INVOICE_ID);
        assertThat(testInvoiceLineItemDetails.getSecondaryInvoiceId()).isEqualTo(UPDATED_SECONDARY_INVOICE_ID);
        assertThat(testInvoiceLineItemDetails.getTertiaryInvoiceId()).isEqualTo(UPDATED_TERTIARY_INVOICE_ID);
        assertThat(testInvoiceLineItemDetails.getPatientInvoiceId()).isEqualTo(UPDATED_PATIENT_INVOICE_ID);
    }

    @Test
    void patchNonExistingInvoiceLineItemDetails() throws Exception {
        int databaseSizeBeforeUpdate = invoiceLineItemDetailsRepository.findAll().collectList().block().size();
        invoiceLineItemDetails.setInvoiceLineItemDetailsId(count.incrementAndGet());

        // Create the InvoiceLineItemDetails
        InvoiceLineItemDetailsDTO invoiceLineItemDetailsDTO = invoiceLineItemDetailsMapper.toDto(invoiceLineItemDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, invoiceLineItemDetailsDTO.getInvoiceLineItemDetailsId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoiceLineItemDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the InvoiceLineItemDetails in the database
        List<InvoiceLineItemDetails> invoiceLineItemDetailsList = invoiceLineItemDetailsRepository.findAll().collectList().block();
        assertThat(invoiceLineItemDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchInvoiceLineItemDetails() throws Exception {
        int databaseSizeBeforeUpdate = invoiceLineItemDetailsRepository.findAll().collectList().block().size();
        invoiceLineItemDetails.setInvoiceLineItemDetailsId(count.incrementAndGet());

        // Create the InvoiceLineItemDetails
        InvoiceLineItemDetailsDTO invoiceLineItemDetailsDTO = invoiceLineItemDetailsMapper.toDto(invoiceLineItemDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoiceLineItemDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the InvoiceLineItemDetails in the database
        List<InvoiceLineItemDetails> invoiceLineItemDetailsList = invoiceLineItemDetailsRepository.findAll().collectList().block();
        assertThat(invoiceLineItemDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamInvoiceLineItemDetails() throws Exception {
        int databaseSizeBeforeUpdate = invoiceLineItemDetailsRepository.findAll().collectList().block().size();
        invoiceLineItemDetails.setInvoiceLineItemDetailsId(count.incrementAndGet());

        // Create the InvoiceLineItemDetails
        InvoiceLineItemDetailsDTO invoiceLineItemDetailsDTO = invoiceLineItemDetailsMapper.toDto(invoiceLineItemDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoiceLineItemDetailsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the InvoiceLineItemDetails in the database
        List<InvoiceLineItemDetails> invoiceLineItemDetailsList = invoiceLineItemDetailsRepository.findAll().collectList().block();
        assertThat(invoiceLineItemDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteInvoiceLineItemDetails() {
        // Initialize the database
        invoiceLineItemDetailsRepository.save(invoiceLineItemDetails).block();

        int databaseSizeBeforeDelete = invoiceLineItemDetailsRepository.findAll().collectList().block().size();

        // Delete the invoiceLineItemDetails
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, invoiceLineItemDetails.getInvoiceLineItemDetailsId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<InvoiceLineItemDetails> invoiceLineItemDetailsList = invoiceLineItemDetailsRepository.findAll().collectList().block();
        assertThat(invoiceLineItemDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
