package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ClaimsStatus277Details;
import com.sunknowledge.dme.rcm.repository.ClaimsStatus277DetailsRepository;
import com.sunknowledge.dme.rcm.service.dto.ClaimsStatus277DetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.ClaimsStatus277DetailsMapper;
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
 * Integration tests for the {@link ClaimsStatus277DetailsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ClaimsStatus277DetailsResourceIT {

    private static final Long DEFAULT_CLAIM_STATUS_277_MASTER_ID = 1L;
    private static final Long UPDATED_CLAIM_STATUS_277_MASTER_ID = 2L;

    private static final String DEFAULT_PROCEDURE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PROCEDURE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PROCEDURE_MODIFIERS = "AAAAAAAAAA";
    private static final String UPDATED_PROCEDURE_MODIFIERS = "BBBBBBBBBB";

    private static final String DEFAULT_SUBMITTED_UNITS = "AAAAAAAAAA";
    private static final String UPDATED_SUBMITTED_UNITS = "BBBBBBBBBB";

    private static final Double DEFAULT_CHARGE_AMOUNT = 1D;
    private static final Double UPDATED_CHARGE_AMOUNT = 2D;

    private static final Double DEFAULT_PAID_AMOUNT = 1D;
    private static final Double UPDATED_PAID_AMOUNT = 2D;

    private static final LocalDate DEFAULT_SERVICE_LINE_BEGIN_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SERVICE_LINE_BEGIN_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_SERVICE_LINE_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SERVICE_LINE_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CLAIM_STATUS_CATEGORY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CLAIM_STATUS_CATEGORY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CLAIM_STATUS_CATEGORY_CODE_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_CLAIM_STATUS_CATEGORY_CODE_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS_CODE = "AAAAAAAAAA";
    private static final String UPDATED_STATUS_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS_CODE_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_STATUS_CODE_VALUE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_EFFECTIVE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_EFFECTIVE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final UUID DEFAULT_CLAIMS_STATUS_277_DETAILS_UUID = UUID.randomUUID();
    private static final UUID UPDATED_CLAIMS_STATUS_277_DETAILS_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/claims-status-277-details";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{claimStatus277DetailId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ClaimsStatus277DetailsRepository claimsStatus277DetailsRepository;

    @Autowired
    private ClaimsStatus277DetailsMapper claimsStatus277DetailsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restClaimsStatus277DetailsMockMvc;

    private ClaimsStatus277Details claimsStatus277Details;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClaimsStatus277Details createEntity(EntityManager em) {
        ClaimsStatus277Details claimsStatus277Details = new ClaimsStatus277Details()
            .claimStatus277MasterId(DEFAULT_CLAIM_STATUS_277_MASTER_ID)
            .procedureCode(DEFAULT_PROCEDURE_CODE)
            .procedureModifiers(DEFAULT_PROCEDURE_MODIFIERS)
            .submittedUnits(DEFAULT_SUBMITTED_UNITS)
            .chargeAmount(DEFAULT_CHARGE_AMOUNT)
            .paidAmount(DEFAULT_PAID_AMOUNT)
            .serviceLineBeginDate(DEFAULT_SERVICE_LINE_BEGIN_DATE)
            .serviceLineEndDate(DEFAULT_SERVICE_LINE_END_DATE)
            .claimStatusCategoryCode(DEFAULT_CLAIM_STATUS_CATEGORY_CODE)
            .claimStatusCategoryCodeValue(DEFAULT_CLAIM_STATUS_CATEGORY_CODE_VALUE)
            .statusCode(DEFAULT_STATUS_CODE)
            .statusCodeValue(DEFAULT_STATUS_CODE_VALUE)
            .effectiveDate(DEFAULT_EFFECTIVE_DATE)
            .status(DEFAULT_STATUS)
            .claimsStatus277DetailsUuid(DEFAULT_CLAIMS_STATUS_277_DETAILS_UUID);
        return claimsStatus277Details;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClaimsStatus277Details createUpdatedEntity(EntityManager em) {
        ClaimsStatus277Details claimsStatus277Details = new ClaimsStatus277Details()
            .claimStatus277MasterId(UPDATED_CLAIM_STATUS_277_MASTER_ID)
            .procedureCode(UPDATED_PROCEDURE_CODE)
            .procedureModifiers(UPDATED_PROCEDURE_MODIFIERS)
            .submittedUnits(UPDATED_SUBMITTED_UNITS)
            .chargeAmount(UPDATED_CHARGE_AMOUNT)
            .paidAmount(UPDATED_PAID_AMOUNT)
            .serviceLineBeginDate(UPDATED_SERVICE_LINE_BEGIN_DATE)
            .serviceLineEndDate(UPDATED_SERVICE_LINE_END_DATE)
            .claimStatusCategoryCode(UPDATED_CLAIM_STATUS_CATEGORY_CODE)
            .claimStatusCategoryCodeValue(UPDATED_CLAIM_STATUS_CATEGORY_CODE_VALUE)
            .statusCode(UPDATED_STATUS_CODE)
            .statusCodeValue(UPDATED_STATUS_CODE_VALUE)
            .effectiveDate(UPDATED_EFFECTIVE_DATE)
            .status(UPDATED_STATUS)
            .claimsStatus277DetailsUuid(UPDATED_CLAIMS_STATUS_277_DETAILS_UUID);
        return claimsStatus277Details;
    }

    @BeforeEach
    public void initTest() {
        claimsStatus277Details = createEntity(em);
    }

    @Test
    @Transactional
    void createClaimsStatus277Details() throws Exception {
        int databaseSizeBeforeCreate = claimsStatus277DetailsRepository.findAll().size();
        // Create the ClaimsStatus277Details
        ClaimsStatus277DetailsDTO claimsStatus277DetailsDTO = claimsStatus277DetailsMapper.toDto(claimsStatus277Details);
        restClaimsStatus277DetailsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimsStatus277DetailsDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ClaimsStatus277Details in the database
        List<ClaimsStatus277Details> claimsStatus277DetailsList = claimsStatus277DetailsRepository.findAll();
        assertThat(claimsStatus277DetailsList).hasSize(databaseSizeBeforeCreate + 1);
        ClaimsStatus277Details testClaimsStatus277Details = claimsStatus277DetailsList.get(claimsStatus277DetailsList.size() - 1);
        assertThat(testClaimsStatus277Details.getClaimStatus277MasterId()).isEqualTo(DEFAULT_CLAIM_STATUS_277_MASTER_ID);
        assertThat(testClaimsStatus277Details.getProcedureCode()).isEqualTo(DEFAULT_PROCEDURE_CODE);
        assertThat(testClaimsStatus277Details.getProcedureModifiers()).isEqualTo(DEFAULT_PROCEDURE_MODIFIERS);
        assertThat(testClaimsStatus277Details.getSubmittedUnits()).isEqualTo(DEFAULT_SUBMITTED_UNITS);
        assertThat(testClaimsStatus277Details.getChargeAmount()).isEqualTo(DEFAULT_CHARGE_AMOUNT);
        assertThat(testClaimsStatus277Details.getPaidAmount()).isEqualTo(DEFAULT_PAID_AMOUNT);
        assertThat(testClaimsStatus277Details.getServiceLineBeginDate()).isEqualTo(DEFAULT_SERVICE_LINE_BEGIN_DATE);
        assertThat(testClaimsStatus277Details.getServiceLineEndDate()).isEqualTo(DEFAULT_SERVICE_LINE_END_DATE);
        assertThat(testClaimsStatus277Details.getClaimStatusCategoryCode()).isEqualTo(DEFAULT_CLAIM_STATUS_CATEGORY_CODE);
        assertThat(testClaimsStatus277Details.getClaimStatusCategoryCodeValue()).isEqualTo(DEFAULT_CLAIM_STATUS_CATEGORY_CODE_VALUE);
        assertThat(testClaimsStatus277Details.getStatusCode()).isEqualTo(DEFAULT_STATUS_CODE);
        assertThat(testClaimsStatus277Details.getStatusCodeValue()).isEqualTo(DEFAULT_STATUS_CODE_VALUE);
        assertThat(testClaimsStatus277Details.getEffectiveDate()).isEqualTo(DEFAULT_EFFECTIVE_DATE);
        assertThat(testClaimsStatus277Details.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testClaimsStatus277Details.getClaimsStatus277DetailsUuid()).isEqualTo(DEFAULT_CLAIMS_STATUS_277_DETAILS_UUID);
    }

    @Test
    @Transactional
    void createClaimsStatus277DetailsWithExistingId() throws Exception {
        // Create the ClaimsStatus277Details with an existing ID
        claimsStatus277Details.setClaimStatus277DetailId(1L);
        ClaimsStatus277DetailsDTO claimsStatus277DetailsDTO = claimsStatus277DetailsMapper.toDto(claimsStatus277Details);

        int databaseSizeBeforeCreate = claimsStatus277DetailsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restClaimsStatus277DetailsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimsStatus277DetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimsStatus277Details in the database
        List<ClaimsStatus277Details> claimsStatus277DetailsList = claimsStatus277DetailsRepository.findAll();
        assertThat(claimsStatus277DetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllClaimsStatus277Details() throws Exception {
        // Initialize the database
        claimsStatus277DetailsRepository.saveAndFlush(claimsStatus277Details);

        // Get all the claimsStatus277DetailsList
        restClaimsStatus277DetailsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=claimStatus277DetailId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].claimStatus277DetailId").value(hasItem(claimsStatus277Details.getClaimStatus277DetailId().intValue()))
            )
            .andExpect(jsonPath("$.[*].claimStatus277MasterId").value(hasItem(DEFAULT_CLAIM_STATUS_277_MASTER_ID.intValue())))
            .andExpect(jsonPath("$.[*].procedureCode").value(hasItem(DEFAULT_PROCEDURE_CODE)))
            .andExpect(jsonPath("$.[*].procedureModifiers").value(hasItem(DEFAULT_PROCEDURE_MODIFIERS)))
            .andExpect(jsonPath("$.[*].submittedUnits").value(hasItem(DEFAULT_SUBMITTED_UNITS)))
            .andExpect(jsonPath("$.[*].chargeAmount").value(hasItem(DEFAULT_CHARGE_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].paidAmount").value(hasItem(DEFAULT_PAID_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].serviceLineBeginDate").value(hasItem(DEFAULT_SERVICE_LINE_BEGIN_DATE.toString())))
            .andExpect(jsonPath("$.[*].serviceLineEndDate").value(hasItem(DEFAULT_SERVICE_LINE_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].claimStatusCategoryCode").value(hasItem(DEFAULT_CLAIM_STATUS_CATEGORY_CODE)))
            .andExpect(jsonPath("$.[*].claimStatusCategoryCodeValue").value(hasItem(DEFAULT_CLAIM_STATUS_CATEGORY_CODE_VALUE)))
            .andExpect(jsonPath("$.[*].statusCode").value(hasItem(DEFAULT_STATUS_CODE)))
            .andExpect(jsonPath("$.[*].statusCodeValue").value(hasItem(DEFAULT_STATUS_CODE_VALUE)))
            .andExpect(jsonPath("$.[*].effectiveDate").value(hasItem(DEFAULT_EFFECTIVE_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].claimsStatus277DetailsUuid").value(hasItem(DEFAULT_CLAIMS_STATUS_277_DETAILS_UUID.toString())));
    }

    @Test
    @Transactional
    void getClaimsStatus277Details() throws Exception {
        // Initialize the database
        claimsStatus277DetailsRepository.saveAndFlush(claimsStatus277Details);

        // Get the claimsStatus277Details
        restClaimsStatus277DetailsMockMvc
            .perform(get(ENTITY_API_URL_ID, claimsStatus277Details.getClaimStatus277DetailId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.claimStatus277DetailId").value(claimsStatus277Details.getClaimStatus277DetailId().intValue()))
            .andExpect(jsonPath("$.claimStatus277MasterId").value(DEFAULT_CLAIM_STATUS_277_MASTER_ID.intValue()))
            .andExpect(jsonPath("$.procedureCode").value(DEFAULT_PROCEDURE_CODE))
            .andExpect(jsonPath("$.procedureModifiers").value(DEFAULT_PROCEDURE_MODIFIERS))
            .andExpect(jsonPath("$.submittedUnits").value(DEFAULT_SUBMITTED_UNITS))
            .andExpect(jsonPath("$.chargeAmount").value(DEFAULT_CHARGE_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.paidAmount").value(DEFAULT_PAID_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.serviceLineBeginDate").value(DEFAULT_SERVICE_LINE_BEGIN_DATE.toString()))
            .andExpect(jsonPath("$.serviceLineEndDate").value(DEFAULT_SERVICE_LINE_END_DATE.toString()))
            .andExpect(jsonPath("$.claimStatusCategoryCode").value(DEFAULT_CLAIM_STATUS_CATEGORY_CODE))
            .andExpect(jsonPath("$.claimStatusCategoryCodeValue").value(DEFAULT_CLAIM_STATUS_CATEGORY_CODE_VALUE))
            .andExpect(jsonPath("$.statusCode").value(DEFAULT_STATUS_CODE))
            .andExpect(jsonPath("$.statusCodeValue").value(DEFAULT_STATUS_CODE_VALUE))
            .andExpect(jsonPath("$.effectiveDate").value(DEFAULT_EFFECTIVE_DATE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.claimsStatus277DetailsUuid").value(DEFAULT_CLAIMS_STATUS_277_DETAILS_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingClaimsStatus277Details() throws Exception {
        // Get the claimsStatus277Details
        restClaimsStatus277DetailsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewClaimsStatus277Details() throws Exception {
        // Initialize the database
        claimsStatus277DetailsRepository.saveAndFlush(claimsStatus277Details);

        int databaseSizeBeforeUpdate = claimsStatus277DetailsRepository.findAll().size();

        // Update the claimsStatus277Details
        ClaimsStatus277Details updatedClaimsStatus277Details = claimsStatus277DetailsRepository
            .findById(claimsStatus277Details.getClaimStatus277DetailId())
            .get();
        // Disconnect from session so that the updates on updatedClaimsStatus277Details are not directly saved in db
        em.detach(updatedClaimsStatus277Details);
        updatedClaimsStatus277Details
            .claimStatus277MasterId(UPDATED_CLAIM_STATUS_277_MASTER_ID)
            .procedureCode(UPDATED_PROCEDURE_CODE)
            .procedureModifiers(UPDATED_PROCEDURE_MODIFIERS)
            .submittedUnits(UPDATED_SUBMITTED_UNITS)
            .chargeAmount(UPDATED_CHARGE_AMOUNT)
            .paidAmount(UPDATED_PAID_AMOUNT)
            .serviceLineBeginDate(UPDATED_SERVICE_LINE_BEGIN_DATE)
            .serviceLineEndDate(UPDATED_SERVICE_LINE_END_DATE)
            .claimStatusCategoryCode(UPDATED_CLAIM_STATUS_CATEGORY_CODE)
            .claimStatusCategoryCodeValue(UPDATED_CLAIM_STATUS_CATEGORY_CODE_VALUE)
            .statusCode(UPDATED_STATUS_CODE)
            .statusCodeValue(UPDATED_STATUS_CODE_VALUE)
            .effectiveDate(UPDATED_EFFECTIVE_DATE)
            .status(UPDATED_STATUS)
            .claimsStatus277DetailsUuid(UPDATED_CLAIMS_STATUS_277_DETAILS_UUID);
        ClaimsStatus277DetailsDTO claimsStatus277DetailsDTO = claimsStatus277DetailsMapper.toDto(updatedClaimsStatus277Details);

        restClaimsStatus277DetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, claimsStatus277DetailsDTO.getClaimStatus277DetailId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimsStatus277DetailsDTO))
            )
            .andExpect(status().isOk());

        // Validate the ClaimsStatus277Details in the database
        List<ClaimsStatus277Details> claimsStatus277DetailsList = claimsStatus277DetailsRepository.findAll();
        assertThat(claimsStatus277DetailsList).hasSize(databaseSizeBeforeUpdate);
        ClaimsStatus277Details testClaimsStatus277Details = claimsStatus277DetailsList.get(claimsStatus277DetailsList.size() - 1);
        assertThat(testClaimsStatus277Details.getClaimStatus277MasterId()).isEqualTo(UPDATED_CLAIM_STATUS_277_MASTER_ID);
        assertThat(testClaimsStatus277Details.getProcedureCode()).isEqualTo(UPDATED_PROCEDURE_CODE);
        assertThat(testClaimsStatus277Details.getProcedureModifiers()).isEqualTo(UPDATED_PROCEDURE_MODIFIERS);
        assertThat(testClaimsStatus277Details.getSubmittedUnits()).isEqualTo(UPDATED_SUBMITTED_UNITS);
        assertThat(testClaimsStatus277Details.getChargeAmount()).isEqualTo(UPDATED_CHARGE_AMOUNT);
        assertThat(testClaimsStatus277Details.getPaidAmount()).isEqualTo(UPDATED_PAID_AMOUNT);
        assertThat(testClaimsStatus277Details.getServiceLineBeginDate()).isEqualTo(UPDATED_SERVICE_LINE_BEGIN_DATE);
        assertThat(testClaimsStatus277Details.getServiceLineEndDate()).isEqualTo(UPDATED_SERVICE_LINE_END_DATE);
        assertThat(testClaimsStatus277Details.getClaimStatusCategoryCode()).isEqualTo(UPDATED_CLAIM_STATUS_CATEGORY_CODE);
        assertThat(testClaimsStatus277Details.getClaimStatusCategoryCodeValue()).isEqualTo(UPDATED_CLAIM_STATUS_CATEGORY_CODE_VALUE);
        assertThat(testClaimsStatus277Details.getStatusCode()).isEqualTo(UPDATED_STATUS_CODE);
        assertThat(testClaimsStatus277Details.getStatusCodeValue()).isEqualTo(UPDATED_STATUS_CODE_VALUE);
        assertThat(testClaimsStatus277Details.getEffectiveDate()).isEqualTo(UPDATED_EFFECTIVE_DATE);
        assertThat(testClaimsStatus277Details.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testClaimsStatus277Details.getClaimsStatus277DetailsUuid()).isEqualTo(UPDATED_CLAIMS_STATUS_277_DETAILS_UUID);
    }

    @Test
    @Transactional
    void putNonExistingClaimsStatus277Details() throws Exception {
        int databaseSizeBeforeUpdate = claimsStatus277DetailsRepository.findAll().size();
        claimsStatus277Details.setClaimStatus277DetailId(count.incrementAndGet());

        // Create the ClaimsStatus277Details
        ClaimsStatus277DetailsDTO claimsStatus277DetailsDTO = claimsStatus277DetailsMapper.toDto(claimsStatus277Details);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClaimsStatus277DetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, claimsStatus277DetailsDTO.getClaimStatus277DetailId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimsStatus277DetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimsStatus277Details in the database
        List<ClaimsStatus277Details> claimsStatus277DetailsList = claimsStatus277DetailsRepository.findAll();
        assertThat(claimsStatus277DetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchClaimsStatus277Details() throws Exception {
        int databaseSizeBeforeUpdate = claimsStatus277DetailsRepository.findAll().size();
        claimsStatus277Details.setClaimStatus277DetailId(count.incrementAndGet());

        // Create the ClaimsStatus277Details
        ClaimsStatus277DetailsDTO claimsStatus277DetailsDTO = claimsStatus277DetailsMapper.toDto(claimsStatus277Details);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaimsStatus277DetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimsStatus277DetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimsStatus277Details in the database
        List<ClaimsStatus277Details> claimsStatus277DetailsList = claimsStatus277DetailsRepository.findAll();
        assertThat(claimsStatus277DetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamClaimsStatus277Details() throws Exception {
        int databaseSizeBeforeUpdate = claimsStatus277DetailsRepository.findAll().size();
        claimsStatus277Details.setClaimStatus277DetailId(count.incrementAndGet());

        // Create the ClaimsStatus277Details
        ClaimsStatus277DetailsDTO claimsStatus277DetailsDTO = claimsStatus277DetailsMapper.toDto(claimsStatus277Details);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaimsStatus277DetailsMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimsStatus277DetailsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ClaimsStatus277Details in the database
        List<ClaimsStatus277Details> claimsStatus277DetailsList = claimsStatus277DetailsRepository.findAll();
        assertThat(claimsStatus277DetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateClaimsStatus277DetailsWithPatch() throws Exception {
        // Initialize the database
        claimsStatus277DetailsRepository.saveAndFlush(claimsStatus277Details);

        int databaseSizeBeforeUpdate = claimsStatus277DetailsRepository.findAll().size();

        // Update the claimsStatus277Details using partial update
        ClaimsStatus277Details partialUpdatedClaimsStatus277Details = new ClaimsStatus277Details();
        partialUpdatedClaimsStatus277Details.setClaimStatus277DetailId(claimsStatus277Details.getClaimStatus277DetailId());

        partialUpdatedClaimsStatus277Details
            .procedureModifiers(UPDATED_PROCEDURE_MODIFIERS)
            .submittedUnits(UPDATED_SUBMITTED_UNITS)
            .claimsStatus277DetailsUuid(UPDATED_CLAIMS_STATUS_277_DETAILS_UUID);

        restClaimsStatus277DetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClaimsStatus277Details.getClaimStatus277DetailId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClaimsStatus277Details))
            )
            .andExpect(status().isOk());

        // Validate the ClaimsStatus277Details in the database
        List<ClaimsStatus277Details> claimsStatus277DetailsList = claimsStatus277DetailsRepository.findAll();
        assertThat(claimsStatus277DetailsList).hasSize(databaseSizeBeforeUpdate);
        ClaimsStatus277Details testClaimsStatus277Details = claimsStatus277DetailsList.get(claimsStatus277DetailsList.size() - 1);
        assertThat(testClaimsStatus277Details.getClaimStatus277MasterId()).isEqualTo(DEFAULT_CLAIM_STATUS_277_MASTER_ID);
        assertThat(testClaimsStatus277Details.getProcedureCode()).isEqualTo(DEFAULT_PROCEDURE_CODE);
        assertThat(testClaimsStatus277Details.getProcedureModifiers()).isEqualTo(UPDATED_PROCEDURE_MODIFIERS);
        assertThat(testClaimsStatus277Details.getSubmittedUnits()).isEqualTo(UPDATED_SUBMITTED_UNITS);
        assertThat(testClaimsStatus277Details.getChargeAmount()).isEqualTo(DEFAULT_CHARGE_AMOUNT);
        assertThat(testClaimsStatus277Details.getPaidAmount()).isEqualTo(DEFAULT_PAID_AMOUNT);
        assertThat(testClaimsStatus277Details.getServiceLineBeginDate()).isEqualTo(DEFAULT_SERVICE_LINE_BEGIN_DATE);
        assertThat(testClaimsStatus277Details.getServiceLineEndDate()).isEqualTo(DEFAULT_SERVICE_LINE_END_DATE);
        assertThat(testClaimsStatus277Details.getClaimStatusCategoryCode()).isEqualTo(DEFAULT_CLAIM_STATUS_CATEGORY_CODE);
        assertThat(testClaimsStatus277Details.getClaimStatusCategoryCodeValue()).isEqualTo(DEFAULT_CLAIM_STATUS_CATEGORY_CODE_VALUE);
        assertThat(testClaimsStatus277Details.getStatusCode()).isEqualTo(DEFAULT_STATUS_CODE);
        assertThat(testClaimsStatus277Details.getStatusCodeValue()).isEqualTo(DEFAULT_STATUS_CODE_VALUE);
        assertThat(testClaimsStatus277Details.getEffectiveDate()).isEqualTo(DEFAULT_EFFECTIVE_DATE);
        assertThat(testClaimsStatus277Details.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testClaimsStatus277Details.getClaimsStatus277DetailsUuid()).isEqualTo(UPDATED_CLAIMS_STATUS_277_DETAILS_UUID);
    }

    @Test
    @Transactional
    void fullUpdateClaimsStatus277DetailsWithPatch() throws Exception {
        // Initialize the database
        claimsStatus277DetailsRepository.saveAndFlush(claimsStatus277Details);

        int databaseSizeBeforeUpdate = claimsStatus277DetailsRepository.findAll().size();

        // Update the claimsStatus277Details using partial update
        ClaimsStatus277Details partialUpdatedClaimsStatus277Details = new ClaimsStatus277Details();
        partialUpdatedClaimsStatus277Details.setClaimStatus277DetailId(claimsStatus277Details.getClaimStatus277DetailId());

        partialUpdatedClaimsStatus277Details
            .claimStatus277MasterId(UPDATED_CLAIM_STATUS_277_MASTER_ID)
            .procedureCode(UPDATED_PROCEDURE_CODE)
            .procedureModifiers(UPDATED_PROCEDURE_MODIFIERS)
            .submittedUnits(UPDATED_SUBMITTED_UNITS)
            .chargeAmount(UPDATED_CHARGE_AMOUNT)
            .paidAmount(UPDATED_PAID_AMOUNT)
            .serviceLineBeginDate(UPDATED_SERVICE_LINE_BEGIN_DATE)
            .serviceLineEndDate(UPDATED_SERVICE_LINE_END_DATE)
            .claimStatusCategoryCode(UPDATED_CLAIM_STATUS_CATEGORY_CODE)
            .claimStatusCategoryCodeValue(UPDATED_CLAIM_STATUS_CATEGORY_CODE_VALUE)
            .statusCode(UPDATED_STATUS_CODE)
            .statusCodeValue(UPDATED_STATUS_CODE_VALUE)
            .effectiveDate(UPDATED_EFFECTIVE_DATE)
            .status(UPDATED_STATUS)
            .claimsStatus277DetailsUuid(UPDATED_CLAIMS_STATUS_277_DETAILS_UUID);

        restClaimsStatus277DetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClaimsStatus277Details.getClaimStatus277DetailId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClaimsStatus277Details))
            )
            .andExpect(status().isOk());

        // Validate the ClaimsStatus277Details in the database
        List<ClaimsStatus277Details> claimsStatus277DetailsList = claimsStatus277DetailsRepository.findAll();
        assertThat(claimsStatus277DetailsList).hasSize(databaseSizeBeforeUpdate);
        ClaimsStatus277Details testClaimsStatus277Details = claimsStatus277DetailsList.get(claimsStatus277DetailsList.size() - 1);
        assertThat(testClaimsStatus277Details.getClaimStatus277MasterId()).isEqualTo(UPDATED_CLAIM_STATUS_277_MASTER_ID);
        assertThat(testClaimsStatus277Details.getProcedureCode()).isEqualTo(UPDATED_PROCEDURE_CODE);
        assertThat(testClaimsStatus277Details.getProcedureModifiers()).isEqualTo(UPDATED_PROCEDURE_MODIFIERS);
        assertThat(testClaimsStatus277Details.getSubmittedUnits()).isEqualTo(UPDATED_SUBMITTED_UNITS);
        assertThat(testClaimsStatus277Details.getChargeAmount()).isEqualTo(UPDATED_CHARGE_AMOUNT);
        assertThat(testClaimsStatus277Details.getPaidAmount()).isEqualTo(UPDATED_PAID_AMOUNT);
        assertThat(testClaimsStatus277Details.getServiceLineBeginDate()).isEqualTo(UPDATED_SERVICE_LINE_BEGIN_DATE);
        assertThat(testClaimsStatus277Details.getServiceLineEndDate()).isEqualTo(UPDATED_SERVICE_LINE_END_DATE);
        assertThat(testClaimsStatus277Details.getClaimStatusCategoryCode()).isEqualTo(UPDATED_CLAIM_STATUS_CATEGORY_CODE);
        assertThat(testClaimsStatus277Details.getClaimStatusCategoryCodeValue()).isEqualTo(UPDATED_CLAIM_STATUS_CATEGORY_CODE_VALUE);
        assertThat(testClaimsStatus277Details.getStatusCode()).isEqualTo(UPDATED_STATUS_CODE);
        assertThat(testClaimsStatus277Details.getStatusCodeValue()).isEqualTo(UPDATED_STATUS_CODE_VALUE);
        assertThat(testClaimsStatus277Details.getEffectiveDate()).isEqualTo(UPDATED_EFFECTIVE_DATE);
        assertThat(testClaimsStatus277Details.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testClaimsStatus277Details.getClaimsStatus277DetailsUuid()).isEqualTo(UPDATED_CLAIMS_STATUS_277_DETAILS_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingClaimsStatus277Details() throws Exception {
        int databaseSizeBeforeUpdate = claimsStatus277DetailsRepository.findAll().size();
        claimsStatus277Details.setClaimStatus277DetailId(count.incrementAndGet());

        // Create the ClaimsStatus277Details
        ClaimsStatus277DetailsDTO claimsStatus277DetailsDTO = claimsStatus277DetailsMapper.toDto(claimsStatus277Details);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClaimsStatus277DetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, claimsStatus277DetailsDTO.getClaimStatus277DetailId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(claimsStatus277DetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimsStatus277Details in the database
        List<ClaimsStatus277Details> claimsStatus277DetailsList = claimsStatus277DetailsRepository.findAll();
        assertThat(claimsStatus277DetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchClaimsStatus277Details() throws Exception {
        int databaseSizeBeforeUpdate = claimsStatus277DetailsRepository.findAll().size();
        claimsStatus277Details.setClaimStatus277DetailId(count.incrementAndGet());

        // Create the ClaimsStatus277Details
        ClaimsStatus277DetailsDTO claimsStatus277DetailsDTO = claimsStatus277DetailsMapper.toDto(claimsStatus277Details);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaimsStatus277DetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(claimsStatus277DetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimsStatus277Details in the database
        List<ClaimsStatus277Details> claimsStatus277DetailsList = claimsStatus277DetailsRepository.findAll();
        assertThat(claimsStatus277DetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamClaimsStatus277Details() throws Exception {
        int databaseSizeBeforeUpdate = claimsStatus277DetailsRepository.findAll().size();
        claimsStatus277Details.setClaimStatus277DetailId(count.incrementAndGet());

        // Create the ClaimsStatus277Details
        ClaimsStatus277DetailsDTO claimsStatus277DetailsDTO = claimsStatus277DetailsMapper.toDto(claimsStatus277Details);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaimsStatus277DetailsMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(claimsStatus277DetailsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ClaimsStatus277Details in the database
        List<ClaimsStatus277Details> claimsStatus277DetailsList = claimsStatus277DetailsRepository.findAll();
        assertThat(claimsStatus277DetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteClaimsStatus277Details() throws Exception {
        // Initialize the database
        claimsStatus277DetailsRepository.saveAndFlush(claimsStatus277Details);

        int databaseSizeBeforeDelete = claimsStatus277DetailsRepository.findAll().size();

        // Delete the claimsStatus277Details
        restClaimsStatus277DetailsMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, claimsStatus277Details.getClaimStatus277DetailId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ClaimsStatus277Details> claimsStatus277DetailsList = claimsStatus277DetailsRepository.findAll();
        assertThat(claimsStatus277DetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
