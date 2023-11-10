package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.InvoiceLineItemDetails;
import com.sunknowledge.dme.rcm.repository.InvoiceLineItemDetailsRepository;
import com.sunknowledge.dme.rcm.service.dto.InvoiceLineItemDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.InvoiceLineItemDetailsMapper;
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
 * Integration tests for the {@link InvoiceLineItemDetailsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
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

    private static final Double DEFAULT_CHARGED_AMOUNT = 1D;
    private static final Double UPDATED_CHARGED_AMOUNT = 2D;

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
    private MockMvc restInvoiceLineItemDetailsMockMvc;

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

    @BeforeEach
    public void initTest() {
        invoiceLineItemDetails = createEntity(em);
    }

    @Test
    @Transactional
    void createInvoiceLineItemDetails() throws Exception {
        int databaseSizeBeforeCreate = invoiceLineItemDetailsRepository.findAll().size();
        // Create the InvoiceLineItemDetails
        InvoiceLineItemDetailsDTO invoiceLineItemDetailsDTO = invoiceLineItemDetailsMapper.toDto(invoiceLineItemDetails);
        restInvoiceLineItemDetailsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(invoiceLineItemDetailsDTO))
            )
            .andExpect(status().isCreated());

        // Validate the InvoiceLineItemDetails in the database
        List<InvoiceLineItemDetails> invoiceLineItemDetailsList = invoiceLineItemDetailsRepository.findAll();
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
    @Transactional
    void createInvoiceLineItemDetailsWithExistingId() throws Exception {
        // Create the InvoiceLineItemDetails with an existing ID
        invoiceLineItemDetails.setInvoiceLineItemDetailsId(1L);
        InvoiceLineItemDetailsDTO invoiceLineItemDetailsDTO = invoiceLineItemDetailsMapper.toDto(invoiceLineItemDetails);

        int databaseSizeBeforeCreate = invoiceLineItemDetailsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restInvoiceLineItemDetailsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(invoiceLineItemDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InvoiceLineItemDetails in the database
        List<InvoiceLineItemDetails> invoiceLineItemDetailsList = invoiceLineItemDetailsRepository.findAll();
        assertThat(invoiceLineItemDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllInvoiceLineItemDetails() throws Exception {
        // Initialize the database
        invoiceLineItemDetailsRepository.saveAndFlush(invoiceLineItemDetails);

        // Get all the invoiceLineItemDetailsList
        restInvoiceLineItemDetailsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=invoiceLineItemDetailsId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].invoiceLineItemDetailsId").value(hasItem(invoiceLineItemDetails.getInvoiceLineItemDetailsId().intValue()))
            )
            .andExpect(jsonPath("$.[*].primaryInvoiceNo").value(hasItem(DEFAULT_PRIMARY_INVOICE_NO)))
            .andExpect(jsonPath("$.[*].invoiceDate").value(hasItem(DEFAULT_INVOICE_DATE.toString())))
            .andExpect(jsonPath("$.[*].itemId").value(hasItem(DEFAULT_ITEM_ID.intValue())))
            .andExpect(jsonPath("$.[*].itemQty").value(hasItem(DEFAULT_ITEM_QTY.intValue())))
            .andExpect(jsonPath("$.[*].itemName").value(hasItem(DEFAULT_ITEM_NAME)))
            .andExpect(jsonPath("$.[*].hcpcsCode").value(hasItem(DEFAULT_HCPCS_CODE)))
            .andExpect(jsonPath("$.[*].chargedAmount").value(hasItem(DEFAULT_CHARGED_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].allowAmount").value(hasItem(DEFAULT_ALLOW_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].invoiceLineItemDetailsUuid").value(hasItem(DEFAULT_INVOICE_LINE_ITEM_DETAILS_UUID.toString())))
            .andExpect(jsonPath("$.[*].secondaryInvoiceNo").value(hasItem(DEFAULT_SECONDARY_INVOICE_NO)))
            .andExpect(jsonPath("$.[*].tertiaryInvoiceNo").value(hasItem(DEFAULT_TERTIARY_INVOICE_NO)))
            .andExpect(jsonPath("$.[*].patientInvoiceNo").value(hasItem(DEFAULT_PATIENT_INVOICE_NO)))
            .andExpect(jsonPath("$.[*].primaryInvoiceId").value(hasItem(DEFAULT_PRIMARY_INVOICE_ID.intValue())))
            .andExpect(jsonPath("$.[*].secondaryInvoiceId").value(hasItem(DEFAULT_SECONDARY_INVOICE_ID.intValue())))
            .andExpect(jsonPath("$.[*].tertiaryInvoiceId").value(hasItem(DEFAULT_TERTIARY_INVOICE_ID.intValue())))
            .andExpect(jsonPath("$.[*].patientInvoiceId").value(hasItem(DEFAULT_PATIENT_INVOICE_ID.intValue())));
    }

    @Test
    @Transactional
    void getInvoiceLineItemDetails() throws Exception {
        // Initialize the database
        invoiceLineItemDetailsRepository.saveAndFlush(invoiceLineItemDetails);

        // Get the invoiceLineItemDetails
        restInvoiceLineItemDetailsMockMvc
            .perform(get(ENTITY_API_URL_ID, invoiceLineItemDetails.getInvoiceLineItemDetailsId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.invoiceLineItemDetailsId").value(invoiceLineItemDetails.getInvoiceLineItemDetailsId().intValue()))
            .andExpect(jsonPath("$.primaryInvoiceNo").value(DEFAULT_PRIMARY_INVOICE_NO))
            .andExpect(jsonPath("$.invoiceDate").value(DEFAULT_INVOICE_DATE.toString()))
            .andExpect(jsonPath("$.itemId").value(DEFAULT_ITEM_ID.intValue()))
            .andExpect(jsonPath("$.itemQty").value(DEFAULT_ITEM_QTY.intValue()))
            .andExpect(jsonPath("$.itemName").value(DEFAULT_ITEM_NAME))
            .andExpect(jsonPath("$.hcpcsCode").value(DEFAULT_HCPCS_CODE))
            .andExpect(jsonPath("$.chargedAmount").value(DEFAULT_CHARGED_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.allowAmount").value(DEFAULT_ALLOW_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.invoiceLineItemDetailsUuid").value(DEFAULT_INVOICE_LINE_ITEM_DETAILS_UUID.toString()))
            .andExpect(jsonPath("$.secondaryInvoiceNo").value(DEFAULT_SECONDARY_INVOICE_NO))
            .andExpect(jsonPath("$.tertiaryInvoiceNo").value(DEFAULT_TERTIARY_INVOICE_NO))
            .andExpect(jsonPath("$.patientInvoiceNo").value(DEFAULT_PATIENT_INVOICE_NO))
            .andExpect(jsonPath("$.primaryInvoiceId").value(DEFAULT_PRIMARY_INVOICE_ID.intValue()))
            .andExpect(jsonPath("$.secondaryInvoiceId").value(DEFAULT_SECONDARY_INVOICE_ID.intValue()))
            .andExpect(jsonPath("$.tertiaryInvoiceId").value(DEFAULT_TERTIARY_INVOICE_ID.intValue()))
            .andExpect(jsonPath("$.patientInvoiceId").value(DEFAULT_PATIENT_INVOICE_ID.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingInvoiceLineItemDetails() throws Exception {
        // Get the invoiceLineItemDetails
        restInvoiceLineItemDetailsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewInvoiceLineItemDetails() throws Exception {
        // Initialize the database
        invoiceLineItemDetailsRepository.saveAndFlush(invoiceLineItemDetails);

        int databaseSizeBeforeUpdate = invoiceLineItemDetailsRepository.findAll().size();

        // Update the invoiceLineItemDetails
        InvoiceLineItemDetails updatedInvoiceLineItemDetails = invoiceLineItemDetailsRepository
            .findById(invoiceLineItemDetails.getInvoiceLineItemDetailsId())
            .get();
        // Disconnect from session so that the updates on updatedInvoiceLineItemDetails are not directly saved in db
        em.detach(updatedInvoiceLineItemDetails);
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

        restInvoiceLineItemDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, invoiceLineItemDetailsDTO.getInvoiceLineItemDetailsId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(invoiceLineItemDetailsDTO))
            )
            .andExpect(status().isOk());

        // Validate the InvoiceLineItemDetails in the database
        List<InvoiceLineItemDetails> invoiceLineItemDetailsList = invoiceLineItemDetailsRepository.findAll();
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
    @Transactional
    void putNonExistingInvoiceLineItemDetails() throws Exception {
        int databaseSizeBeforeUpdate = invoiceLineItemDetailsRepository.findAll().size();
        invoiceLineItemDetails.setInvoiceLineItemDetailsId(count.incrementAndGet());

        // Create the InvoiceLineItemDetails
        InvoiceLineItemDetailsDTO invoiceLineItemDetailsDTO = invoiceLineItemDetailsMapper.toDto(invoiceLineItemDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInvoiceLineItemDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, invoiceLineItemDetailsDTO.getInvoiceLineItemDetailsId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(invoiceLineItemDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InvoiceLineItemDetails in the database
        List<InvoiceLineItemDetails> invoiceLineItemDetailsList = invoiceLineItemDetailsRepository.findAll();
        assertThat(invoiceLineItemDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchInvoiceLineItemDetails() throws Exception {
        int databaseSizeBeforeUpdate = invoiceLineItemDetailsRepository.findAll().size();
        invoiceLineItemDetails.setInvoiceLineItemDetailsId(count.incrementAndGet());

        // Create the InvoiceLineItemDetails
        InvoiceLineItemDetailsDTO invoiceLineItemDetailsDTO = invoiceLineItemDetailsMapper.toDto(invoiceLineItemDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInvoiceLineItemDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(invoiceLineItemDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InvoiceLineItemDetails in the database
        List<InvoiceLineItemDetails> invoiceLineItemDetailsList = invoiceLineItemDetailsRepository.findAll();
        assertThat(invoiceLineItemDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamInvoiceLineItemDetails() throws Exception {
        int databaseSizeBeforeUpdate = invoiceLineItemDetailsRepository.findAll().size();
        invoiceLineItemDetails.setInvoiceLineItemDetailsId(count.incrementAndGet());

        // Create the InvoiceLineItemDetails
        InvoiceLineItemDetailsDTO invoiceLineItemDetailsDTO = invoiceLineItemDetailsMapper.toDto(invoiceLineItemDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInvoiceLineItemDetailsMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(invoiceLineItemDetailsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the InvoiceLineItemDetails in the database
        List<InvoiceLineItemDetails> invoiceLineItemDetailsList = invoiceLineItemDetailsRepository.findAll();
        assertThat(invoiceLineItemDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateInvoiceLineItemDetailsWithPatch() throws Exception {
        // Initialize the database
        invoiceLineItemDetailsRepository.saveAndFlush(invoiceLineItemDetails);

        int databaseSizeBeforeUpdate = invoiceLineItemDetailsRepository.findAll().size();

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

        restInvoiceLineItemDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInvoiceLineItemDetails.getInvoiceLineItemDetailsId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInvoiceLineItemDetails))
            )
            .andExpect(status().isOk());

        // Validate the InvoiceLineItemDetails in the database
        List<InvoiceLineItemDetails> invoiceLineItemDetailsList = invoiceLineItemDetailsRepository.findAll();
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
    @Transactional
    void fullUpdateInvoiceLineItemDetailsWithPatch() throws Exception {
        // Initialize the database
        invoiceLineItemDetailsRepository.saveAndFlush(invoiceLineItemDetails);

        int databaseSizeBeforeUpdate = invoiceLineItemDetailsRepository.findAll().size();

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

        restInvoiceLineItemDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInvoiceLineItemDetails.getInvoiceLineItemDetailsId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInvoiceLineItemDetails))
            )
            .andExpect(status().isOk());

        // Validate the InvoiceLineItemDetails in the database
        List<InvoiceLineItemDetails> invoiceLineItemDetailsList = invoiceLineItemDetailsRepository.findAll();
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
    @Transactional
    void patchNonExistingInvoiceLineItemDetails() throws Exception {
        int databaseSizeBeforeUpdate = invoiceLineItemDetailsRepository.findAll().size();
        invoiceLineItemDetails.setInvoiceLineItemDetailsId(count.incrementAndGet());

        // Create the InvoiceLineItemDetails
        InvoiceLineItemDetailsDTO invoiceLineItemDetailsDTO = invoiceLineItemDetailsMapper.toDto(invoiceLineItemDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInvoiceLineItemDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, invoiceLineItemDetailsDTO.getInvoiceLineItemDetailsId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(invoiceLineItemDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InvoiceLineItemDetails in the database
        List<InvoiceLineItemDetails> invoiceLineItemDetailsList = invoiceLineItemDetailsRepository.findAll();
        assertThat(invoiceLineItemDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchInvoiceLineItemDetails() throws Exception {
        int databaseSizeBeforeUpdate = invoiceLineItemDetailsRepository.findAll().size();
        invoiceLineItemDetails.setInvoiceLineItemDetailsId(count.incrementAndGet());

        // Create the InvoiceLineItemDetails
        InvoiceLineItemDetailsDTO invoiceLineItemDetailsDTO = invoiceLineItemDetailsMapper.toDto(invoiceLineItemDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInvoiceLineItemDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(invoiceLineItemDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InvoiceLineItemDetails in the database
        List<InvoiceLineItemDetails> invoiceLineItemDetailsList = invoiceLineItemDetailsRepository.findAll();
        assertThat(invoiceLineItemDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamInvoiceLineItemDetails() throws Exception {
        int databaseSizeBeforeUpdate = invoiceLineItemDetailsRepository.findAll().size();
        invoiceLineItemDetails.setInvoiceLineItemDetailsId(count.incrementAndGet());

        // Create the InvoiceLineItemDetails
        InvoiceLineItemDetailsDTO invoiceLineItemDetailsDTO = invoiceLineItemDetailsMapper.toDto(invoiceLineItemDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInvoiceLineItemDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(invoiceLineItemDetailsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the InvoiceLineItemDetails in the database
        List<InvoiceLineItemDetails> invoiceLineItemDetailsList = invoiceLineItemDetailsRepository.findAll();
        assertThat(invoiceLineItemDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteInvoiceLineItemDetails() throws Exception {
        // Initialize the database
        invoiceLineItemDetailsRepository.saveAndFlush(invoiceLineItemDetails);

        int databaseSizeBeforeDelete = invoiceLineItemDetailsRepository.findAll().size();

        // Delete the invoiceLineItemDetails
        restInvoiceLineItemDetailsMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, invoiceLineItemDetails.getInvoiceLineItemDetailsId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InvoiceLineItemDetails> invoiceLineItemDetailsList = invoiceLineItemDetailsRepository.findAll();
        assertThat(invoiceLineItemDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
