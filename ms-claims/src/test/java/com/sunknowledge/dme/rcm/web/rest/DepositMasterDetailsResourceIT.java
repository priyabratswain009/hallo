package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.DepositMasterDetails;
import com.sunknowledge.dme.rcm.repository.DepositMasterDetailsRepository;
import com.sunknowledge.dme.rcm.service.dto.DepositMasterDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.DepositMasterDetailsMapper;
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
 * Integration tests for the {@link DepositMasterDetailsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DepositMasterDetailsResourceIT {

    private static final String DEFAULT_DEPOSIT_NO = "AAAAAAAAAA";
    private static final String UPDATED_DEPOSIT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_DEPOSIT_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_DEPOSIT_REFERENCE = "BBBBBBBBBB";

    private static final String DEFAULT_PAYOR_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PAYOR_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DEPOSIT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DEPOSIT_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DEPOSIT_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_DEPOSIT_NOTES = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_DEPOSIT_MASTER_DETAILS_UUID = UUID.randomUUID();
    private static final UUID UPDATED_DEPOSIT_MASTER_DETAILS_UUID = UUID.randomUUID();

    private static final Long DEFAULT_CLAIM_COB_835_MASTER_ID = 1L;
    private static final Long UPDATED_CLAIM_COB_835_MASTER_ID = 2L;

    private static final String DEFAULT_DEPOSIT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_DEPOSIT_STATUS = "BBBBBBBBBB";

    private static final Double DEFAULT_DEPOSIT_AMOUNT = 1D;
    private static final Double UPDATED_DEPOSIT_AMOUNT = 2D;

    private static final String ENTITY_API_URL = "/api/deposit-master-details";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{depositId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DepositMasterDetailsRepository depositMasterDetailsRepository;

    @Autowired
    private DepositMasterDetailsMapper depositMasterDetailsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDepositMasterDetailsMockMvc;

    private DepositMasterDetails depositMasterDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DepositMasterDetails createEntity(EntityManager em) {
        DepositMasterDetails depositMasterDetails = new DepositMasterDetails()
            .depositNo(DEFAULT_DEPOSIT_NO)
            .depositReference(DEFAULT_DEPOSIT_REFERENCE)
            .payorName(DEFAULT_PAYOR_NAME)
            .depositDate(DEFAULT_DEPOSIT_DATE)
            .depositNotes(DEFAULT_DEPOSIT_NOTES)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .depositMasterDetailsUuid(DEFAULT_DEPOSIT_MASTER_DETAILS_UUID)
            .claimCob835MasterId(DEFAULT_CLAIM_COB_835_MASTER_ID)
            .depositStatus(DEFAULT_DEPOSIT_STATUS)
            .depositAmount(DEFAULT_DEPOSIT_AMOUNT);
        return depositMasterDetails;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DepositMasterDetails createUpdatedEntity(EntityManager em) {
        DepositMasterDetails depositMasterDetails = new DepositMasterDetails()
            .depositNo(UPDATED_DEPOSIT_NO)
            .depositReference(UPDATED_DEPOSIT_REFERENCE)
            .payorName(UPDATED_PAYOR_NAME)
            .depositDate(UPDATED_DEPOSIT_DATE)
            .depositNotes(UPDATED_DEPOSIT_NOTES)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .depositMasterDetailsUuid(UPDATED_DEPOSIT_MASTER_DETAILS_UUID)
            .claimCob835MasterId(UPDATED_CLAIM_COB_835_MASTER_ID)
            .depositStatus(UPDATED_DEPOSIT_STATUS)
            .depositAmount(UPDATED_DEPOSIT_AMOUNT);
        return depositMasterDetails;
    }

    @BeforeEach
    public void initTest() {
        depositMasterDetails = createEntity(em);
    }

    @Test
    @Transactional
    void createDepositMasterDetails() throws Exception {
        int databaseSizeBeforeCreate = depositMasterDetailsRepository.findAll().size();
        // Create the DepositMasterDetails
        DepositMasterDetailsDTO depositMasterDetailsDTO = depositMasterDetailsMapper.toDto(depositMasterDetails);
        restDepositMasterDetailsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(depositMasterDetailsDTO))
            )
            .andExpect(status().isCreated());

        // Validate the DepositMasterDetails in the database
        List<DepositMasterDetails> depositMasterDetailsList = depositMasterDetailsRepository.findAll();
        assertThat(depositMasterDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        DepositMasterDetails testDepositMasterDetails = depositMasterDetailsList.get(depositMasterDetailsList.size() - 1);
        assertThat(testDepositMasterDetails.getDepositNo()).isEqualTo(DEFAULT_DEPOSIT_NO);
        assertThat(testDepositMasterDetails.getDepositReference()).isEqualTo(DEFAULT_DEPOSIT_REFERENCE);
        assertThat(testDepositMasterDetails.getPayorName()).isEqualTo(DEFAULT_PAYOR_NAME);
        assertThat(testDepositMasterDetails.getDepositDate()).isEqualTo(DEFAULT_DEPOSIT_DATE);
        assertThat(testDepositMasterDetails.getDepositNotes()).isEqualTo(DEFAULT_DEPOSIT_NOTES);
        assertThat(testDepositMasterDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testDepositMasterDetails.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testDepositMasterDetails.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testDepositMasterDetails.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testDepositMasterDetails.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testDepositMasterDetails.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testDepositMasterDetails.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testDepositMasterDetails.getDepositMasterDetailsUuid()).isEqualTo(DEFAULT_DEPOSIT_MASTER_DETAILS_UUID);
        assertThat(testDepositMasterDetails.getClaimCob835MasterId()).isEqualTo(DEFAULT_CLAIM_COB_835_MASTER_ID);
        assertThat(testDepositMasterDetails.getDepositStatus()).isEqualTo(DEFAULT_DEPOSIT_STATUS);
        assertThat(testDepositMasterDetails.getDepositAmount()).isEqualTo(DEFAULT_DEPOSIT_AMOUNT);
    }

    @Test
    @Transactional
    void createDepositMasterDetailsWithExistingId() throws Exception {
        // Create the DepositMasterDetails with an existing ID
        depositMasterDetails.setDepositId(1L);
        DepositMasterDetailsDTO depositMasterDetailsDTO = depositMasterDetailsMapper.toDto(depositMasterDetails);

        int databaseSizeBeforeCreate = depositMasterDetailsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDepositMasterDetailsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(depositMasterDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DepositMasterDetails in the database
        List<DepositMasterDetails> depositMasterDetailsList = depositMasterDetailsRepository.findAll();
        assertThat(depositMasterDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDepositMasterDetails() throws Exception {
        // Initialize the database
        depositMasterDetailsRepository.saveAndFlush(depositMasterDetails);

        // Get all the depositMasterDetailsList
        restDepositMasterDetailsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=depositId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].depositId").value(hasItem(depositMasterDetails.getDepositId().intValue())))
            .andExpect(jsonPath("$.[*].depositNo").value(hasItem(DEFAULT_DEPOSIT_NO)))
            .andExpect(jsonPath("$.[*].depositReference").value(hasItem(DEFAULT_DEPOSIT_REFERENCE)))
            .andExpect(jsonPath("$.[*].payorName").value(hasItem(DEFAULT_PAYOR_NAME)))
            .andExpect(jsonPath("$.[*].depositDate").value(hasItem(DEFAULT_DEPOSIT_DATE.toString())))
            .andExpect(jsonPath("$.[*].depositNotes").value(hasItem(DEFAULT_DEPOSIT_NOTES)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].depositMasterDetailsUuid").value(hasItem(DEFAULT_DEPOSIT_MASTER_DETAILS_UUID.toString())))
            .andExpect(jsonPath("$.[*].claimCob835MasterId").value(hasItem(DEFAULT_CLAIM_COB_835_MASTER_ID.intValue())))
            .andExpect(jsonPath("$.[*].depositStatus").value(hasItem(DEFAULT_DEPOSIT_STATUS)))
            .andExpect(jsonPath("$.[*].depositAmount").value(hasItem(DEFAULT_DEPOSIT_AMOUNT.doubleValue())));
    }

    @Test
    @Transactional
    void getDepositMasterDetails() throws Exception {
        // Initialize the database
        depositMasterDetailsRepository.saveAndFlush(depositMasterDetails);

        // Get the depositMasterDetails
        restDepositMasterDetailsMockMvc
            .perform(get(ENTITY_API_URL_ID, depositMasterDetails.getDepositId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.depositId").value(depositMasterDetails.getDepositId().intValue()))
            .andExpect(jsonPath("$.depositNo").value(DEFAULT_DEPOSIT_NO))
            .andExpect(jsonPath("$.depositReference").value(DEFAULT_DEPOSIT_REFERENCE))
            .andExpect(jsonPath("$.payorName").value(DEFAULT_PAYOR_NAME))
            .andExpect(jsonPath("$.depositDate").value(DEFAULT_DEPOSIT_DATE.toString()))
            .andExpect(jsonPath("$.depositNotes").value(DEFAULT_DEPOSIT_NOTES))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.depositMasterDetailsUuid").value(DEFAULT_DEPOSIT_MASTER_DETAILS_UUID.toString()))
            .andExpect(jsonPath("$.claimCob835MasterId").value(DEFAULT_CLAIM_COB_835_MASTER_ID.intValue()))
            .andExpect(jsonPath("$.depositStatus").value(DEFAULT_DEPOSIT_STATUS))
            .andExpect(jsonPath("$.depositAmount").value(DEFAULT_DEPOSIT_AMOUNT.doubleValue()));
    }

    @Test
    @Transactional
    void getNonExistingDepositMasterDetails() throws Exception {
        // Get the depositMasterDetails
        restDepositMasterDetailsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewDepositMasterDetails() throws Exception {
        // Initialize the database
        depositMasterDetailsRepository.saveAndFlush(depositMasterDetails);

        int databaseSizeBeforeUpdate = depositMasterDetailsRepository.findAll().size();

        // Update the depositMasterDetails
        DepositMasterDetails updatedDepositMasterDetails = depositMasterDetailsRepository
            .findById(depositMasterDetails.getDepositId())
            .get();
        // Disconnect from session so that the updates on updatedDepositMasterDetails are not directly saved in db
        em.detach(updatedDepositMasterDetails);
        updatedDepositMasterDetails
            .depositNo(UPDATED_DEPOSIT_NO)
            .depositReference(UPDATED_DEPOSIT_REFERENCE)
            .payorName(UPDATED_PAYOR_NAME)
            .depositDate(UPDATED_DEPOSIT_DATE)
            .depositNotes(UPDATED_DEPOSIT_NOTES)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .depositMasterDetailsUuid(UPDATED_DEPOSIT_MASTER_DETAILS_UUID)
            .claimCob835MasterId(UPDATED_CLAIM_COB_835_MASTER_ID)
            .depositStatus(UPDATED_DEPOSIT_STATUS)
            .depositAmount(UPDATED_DEPOSIT_AMOUNT);
        DepositMasterDetailsDTO depositMasterDetailsDTO = depositMasterDetailsMapper.toDto(updatedDepositMasterDetails);

        restDepositMasterDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, depositMasterDetailsDTO.getDepositId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(depositMasterDetailsDTO))
            )
            .andExpect(status().isOk());

        // Validate the DepositMasterDetails in the database
        List<DepositMasterDetails> depositMasterDetailsList = depositMasterDetailsRepository.findAll();
        assertThat(depositMasterDetailsList).hasSize(databaseSizeBeforeUpdate);
        DepositMasterDetails testDepositMasterDetails = depositMasterDetailsList.get(depositMasterDetailsList.size() - 1);
        assertThat(testDepositMasterDetails.getDepositNo()).isEqualTo(UPDATED_DEPOSIT_NO);
        assertThat(testDepositMasterDetails.getDepositReference()).isEqualTo(UPDATED_DEPOSIT_REFERENCE);
        assertThat(testDepositMasterDetails.getPayorName()).isEqualTo(UPDATED_PAYOR_NAME);
        assertThat(testDepositMasterDetails.getDepositDate()).isEqualTo(UPDATED_DEPOSIT_DATE);
        assertThat(testDepositMasterDetails.getDepositNotes()).isEqualTo(UPDATED_DEPOSIT_NOTES);
        assertThat(testDepositMasterDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDepositMasterDetails.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testDepositMasterDetails.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testDepositMasterDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testDepositMasterDetails.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testDepositMasterDetails.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testDepositMasterDetails.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testDepositMasterDetails.getDepositMasterDetailsUuid()).isEqualTo(UPDATED_DEPOSIT_MASTER_DETAILS_UUID);
        assertThat(testDepositMasterDetails.getClaimCob835MasterId()).isEqualTo(UPDATED_CLAIM_COB_835_MASTER_ID);
        assertThat(testDepositMasterDetails.getDepositStatus()).isEqualTo(UPDATED_DEPOSIT_STATUS);
        assertThat(testDepositMasterDetails.getDepositAmount()).isEqualTo(UPDATED_DEPOSIT_AMOUNT);
    }

    @Test
    @Transactional
    void putNonExistingDepositMasterDetails() throws Exception {
        int databaseSizeBeforeUpdate = depositMasterDetailsRepository.findAll().size();
        depositMasterDetails.setDepositId(count.incrementAndGet());

        // Create the DepositMasterDetails
        DepositMasterDetailsDTO depositMasterDetailsDTO = depositMasterDetailsMapper.toDto(depositMasterDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDepositMasterDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, depositMasterDetailsDTO.getDepositId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(depositMasterDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DepositMasterDetails in the database
        List<DepositMasterDetails> depositMasterDetailsList = depositMasterDetailsRepository.findAll();
        assertThat(depositMasterDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDepositMasterDetails() throws Exception {
        int databaseSizeBeforeUpdate = depositMasterDetailsRepository.findAll().size();
        depositMasterDetails.setDepositId(count.incrementAndGet());

        // Create the DepositMasterDetails
        DepositMasterDetailsDTO depositMasterDetailsDTO = depositMasterDetailsMapper.toDto(depositMasterDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDepositMasterDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(depositMasterDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DepositMasterDetails in the database
        List<DepositMasterDetails> depositMasterDetailsList = depositMasterDetailsRepository.findAll();
        assertThat(depositMasterDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDepositMasterDetails() throws Exception {
        int databaseSizeBeforeUpdate = depositMasterDetailsRepository.findAll().size();
        depositMasterDetails.setDepositId(count.incrementAndGet());

        // Create the DepositMasterDetails
        DepositMasterDetailsDTO depositMasterDetailsDTO = depositMasterDetailsMapper.toDto(depositMasterDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDepositMasterDetailsMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(depositMasterDetailsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DepositMasterDetails in the database
        List<DepositMasterDetails> depositMasterDetailsList = depositMasterDetailsRepository.findAll();
        assertThat(depositMasterDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDepositMasterDetailsWithPatch() throws Exception {
        // Initialize the database
        depositMasterDetailsRepository.saveAndFlush(depositMasterDetails);

        int databaseSizeBeforeUpdate = depositMasterDetailsRepository.findAll().size();

        // Update the depositMasterDetails using partial update
        DepositMasterDetails partialUpdatedDepositMasterDetails = new DepositMasterDetails();
        partialUpdatedDepositMasterDetails.setDepositId(depositMasterDetails.getDepositId());

        partialUpdatedDepositMasterDetails
            .depositNo(UPDATED_DEPOSIT_NO)
            .depositReference(UPDATED_DEPOSIT_REFERENCE)
            .depositDate(UPDATED_DEPOSIT_DATE)
            .depositNotes(UPDATED_DEPOSIT_NOTES)
            .status(UPDATED_STATUS)
            .updatedDate(UPDATED_UPDATED_DATE)
            .depositMasterDetailsUuid(UPDATED_DEPOSIT_MASTER_DETAILS_UUID)
            .claimCob835MasterId(UPDATED_CLAIM_COB_835_MASTER_ID);

        restDepositMasterDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDepositMasterDetails.getDepositId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDepositMasterDetails))
            )
            .andExpect(status().isOk());

        // Validate the DepositMasterDetails in the database
        List<DepositMasterDetails> depositMasterDetailsList = depositMasterDetailsRepository.findAll();
        assertThat(depositMasterDetailsList).hasSize(databaseSizeBeforeUpdate);
        DepositMasterDetails testDepositMasterDetails = depositMasterDetailsList.get(depositMasterDetailsList.size() - 1);
        assertThat(testDepositMasterDetails.getDepositNo()).isEqualTo(UPDATED_DEPOSIT_NO);
        assertThat(testDepositMasterDetails.getDepositReference()).isEqualTo(UPDATED_DEPOSIT_REFERENCE);
        assertThat(testDepositMasterDetails.getPayorName()).isEqualTo(DEFAULT_PAYOR_NAME);
        assertThat(testDepositMasterDetails.getDepositDate()).isEqualTo(UPDATED_DEPOSIT_DATE);
        assertThat(testDepositMasterDetails.getDepositNotes()).isEqualTo(UPDATED_DEPOSIT_NOTES);
        assertThat(testDepositMasterDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDepositMasterDetails.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testDepositMasterDetails.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testDepositMasterDetails.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testDepositMasterDetails.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testDepositMasterDetails.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testDepositMasterDetails.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testDepositMasterDetails.getDepositMasterDetailsUuid()).isEqualTo(UPDATED_DEPOSIT_MASTER_DETAILS_UUID);
        assertThat(testDepositMasterDetails.getClaimCob835MasterId()).isEqualTo(UPDATED_CLAIM_COB_835_MASTER_ID);
        assertThat(testDepositMasterDetails.getDepositStatus()).isEqualTo(DEFAULT_DEPOSIT_STATUS);
        assertThat(testDepositMasterDetails.getDepositAmount()).isEqualTo(DEFAULT_DEPOSIT_AMOUNT);
    }

    @Test
    @Transactional
    void fullUpdateDepositMasterDetailsWithPatch() throws Exception {
        // Initialize the database
        depositMasterDetailsRepository.saveAndFlush(depositMasterDetails);

        int databaseSizeBeforeUpdate = depositMasterDetailsRepository.findAll().size();

        // Update the depositMasterDetails using partial update
        DepositMasterDetails partialUpdatedDepositMasterDetails = new DepositMasterDetails();
        partialUpdatedDepositMasterDetails.setDepositId(depositMasterDetails.getDepositId());

        partialUpdatedDepositMasterDetails
            .depositNo(UPDATED_DEPOSIT_NO)
            .depositReference(UPDATED_DEPOSIT_REFERENCE)
            .payorName(UPDATED_PAYOR_NAME)
            .depositDate(UPDATED_DEPOSIT_DATE)
            .depositNotes(UPDATED_DEPOSIT_NOTES)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .depositMasterDetailsUuid(UPDATED_DEPOSIT_MASTER_DETAILS_UUID)
            .claimCob835MasterId(UPDATED_CLAIM_COB_835_MASTER_ID)
            .depositStatus(UPDATED_DEPOSIT_STATUS)
            .depositAmount(UPDATED_DEPOSIT_AMOUNT);

        restDepositMasterDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDepositMasterDetails.getDepositId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDepositMasterDetails))
            )
            .andExpect(status().isOk());

        // Validate the DepositMasterDetails in the database
        List<DepositMasterDetails> depositMasterDetailsList = depositMasterDetailsRepository.findAll();
        assertThat(depositMasterDetailsList).hasSize(databaseSizeBeforeUpdate);
        DepositMasterDetails testDepositMasterDetails = depositMasterDetailsList.get(depositMasterDetailsList.size() - 1);
        assertThat(testDepositMasterDetails.getDepositNo()).isEqualTo(UPDATED_DEPOSIT_NO);
        assertThat(testDepositMasterDetails.getDepositReference()).isEqualTo(UPDATED_DEPOSIT_REFERENCE);
        assertThat(testDepositMasterDetails.getPayorName()).isEqualTo(UPDATED_PAYOR_NAME);
        assertThat(testDepositMasterDetails.getDepositDate()).isEqualTo(UPDATED_DEPOSIT_DATE);
        assertThat(testDepositMasterDetails.getDepositNotes()).isEqualTo(UPDATED_DEPOSIT_NOTES);
        assertThat(testDepositMasterDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDepositMasterDetails.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testDepositMasterDetails.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testDepositMasterDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testDepositMasterDetails.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testDepositMasterDetails.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testDepositMasterDetails.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testDepositMasterDetails.getDepositMasterDetailsUuid()).isEqualTo(UPDATED_DEPOSIT_MASTER_DETAILS_UUID);
        assertThat(testDepositMasterDetails.getClaimCob835MasterId()).isEqualTo(UPDATED_CLAIM_COB_835_MASTER_ID);
        assertThat(testDepositMasterDetails.getDepositStatus()).isEqualTo(UPDATED_DEPOSIT_STATUS);
        assertThat(testDepositMasterDetails.getDepositAmount()).isEqualTo(UPDATED_DEPOSIT_AMOUNT);
    }

    @Test
    @Transactional
    void patchNonExistingDepositMasterDetails() throws Exception {
        int databaseSizeBeforeUpdate = depositMasterDetailsRepository.findAll().size();
        depositMasterDetails.setDepositId(count.incrementAndGet());

        // Create the DepositMasterDetails
        DepositMasterDetailsDTO depositMasterDetailsDTO = depositMasterDetailsMapper.toDto(depositMasterDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDepositMasterDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, depositMasterDetailsDTO.getDepositId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(depositMasterDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DepositMasterDetails in the database
        List<DepositMasterDetails> depositMasterDetailsList = depositMasterDetailsRepository.findAll();
        assertThat(depositMasterDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDepositMasterDetails() throws Exception {
        int databaseSizeBeforeUpdate = depositMasterDetailsRepository.findAll().size();
        depositMasterDetails.setDepositId(count.incrementAndGet());

        // Create the DepositMasterDetails
        DepositMasterDetailsDTO depositMasterDetailsDTO = depositMasterDetailsMapper.toDto(depositMasterDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDepositMasterDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(depositMasterDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DepositMasterDetails in the database
        List<DepositMasterDetails> depositMasterDetailsList = depositMasterDetailsRepository.findAll();
        assertThat(depositMasterDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDepositMasterDetails() throws Exception {
        int databaseSizeBeforeUpdate = depositMasterDetailsRepository.findAll().size();
        depositMasterDetails.setDepositId(count.incrementAndGet());

        // Create the DepositMasterDetails
        DepositMasterDetailsDTO depositMasterDetailsDTO = depositMasterDetailsMapper.toDto(depositMasterDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDepositMasterDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(depositMasterDetailsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DepositMasterDetails in the database
        List<DepositMasterDetails> depositMasterDetailsList = depositMasterDetailsRepository.findAll();
        assertThat(depositMasterDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDepositMasterDetails() throws Exception {
        // Initialize the database
        depositMasterDetailsRepository.saveAndFlush(depositMasterDetails);

        int databaseSizeBeforeDelete = depositMasterDetailsRepository.findAll().size();

        // Delete the depositMasterDetails
        restDepositMasterDetailsMockMvc
            .perform(delete(ENTITY_API_URL_ID, depositMasterDetails.getDepositId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DepositMasterDetails> depositMasterDetailsList = depositMasterDetailsRepository.findAll();
        assertThat(depositMasterDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
