package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.Claim835277Exception;
import com.sunknowledge.dme.rcm.repository.Claim835277ExceptionRepository;
import com.sunknowledge.dme.rcm.service.dto.Claim835277ExceptionDTO;
import com.sunknowledge.dme.rcm.service.mapper.Claim835277ExceptionMapper;
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
 * Integration tests for the {@link Claim835277ExceptionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class Claim835277ExceptionResourceIT {

    private static final String DEFAULT_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FILE_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_RUN_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_RUN_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_IS_277 = false;
    private static final Boolean UPDATED_IS_277 = true;

    private static final Boolean DEFAULT_IS_835 = false;
    private static final Boolean UPDATED_IS_835 = true;

    private static final Long DEFAULT_CLAIM_STATUS_277_MASTER_ID = 1L;
    private static final Long UPDATED_CLAIM_STATUS_277_MASTER_ID = 2L;

    private static final Long DEFAULT_CLAIM_COB_835_MASTER_ID = 1L;
    private static final Long UPDATED_CLAIM_COB_835_MASTER_ID = 2L;

    private static final String DEFAULT_PAYER_CLAIM_CONTROL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_CLAIM_CONTROL_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_CONTROL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_CONTROL_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_MEMBER_ID = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_MEMBER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_ACCOUNT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_ACCOUNT_NUMBER = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_CLAIM_835277_EXCEPTION_UUID = UUID.randomUUID();
    private static final UUID UPDATED_CLAIM_835277_EXCEPTION_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/claim-835277-exceptions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{exceptionId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private Claim835277ExceptionRepository claim835277ExceptionRepository;

    @Autowired
    private Claim835277ExceptionMapper claim835277ExceptionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restClaim835277ExceptionMockMvc;

    private Claim835277Exception claim835277Exception;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Claim835277Exception createEntity(EntityManager em) {
        Claim835277Exception claim835277Exception = new Claim835277Exception()
            .fileName(DEFAULT_FILE_NAME)
            .runDate(DEFAULT_RUN_DATE)
            .is277(DEFAULT_IS_277)
            .is835(DEFAULT_IS_835)
            .claimStatus277MasterId(DEFAULT_CLAIM_STATUS_277_MASTER_ID)
            .claimCob835MasterId(DEFAULT_CLAIM_COB_835_MASTER_ID)
            .payerClaimControlNumber(DEFAULT_PAYER_CLAIM_CONTROL_NUMBER)
            .patientControlNumber(DEFAULT_PATIENT_CONTROL_NUMBER)
            .patientMemberId(DEFAULT_PATIENT_MEMBER_ID)
            .patientAccountNumber(DEFAULT_PATIENT_ACCOUNT_NUMBER)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .claim835277ExceptionUuid(DEFAULT_CLAIM_835277_EXCEPTION_UUID);
        return claim835277Exception;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Claim835277Exception createUpdatedEntity(EntityManager em) {
        Claim835277Exception claim835277Exception = new Claim835277Exception()
            .fileName(UPDATED_FILE_NAME)
            .runDate(UPDATED_RUN_DATE)
            .is277(UPDATED_IS_277)
            .is835(UPDATED_IS_835)
            .claimStatus277MasterId(UPDATED_CLAIM_STATUS_277_MASTER_ID)
            .claimCob835MasterId(UPDATED_CLAIM_COB_835_MASTER_ID)
            .payerClaimControlNumber(UPDATED_PAYER_CLAIM_CONTROL_NUMBER)
            .patientControlNumber(UPDATED_PATIENT_CONTROL_NUMBER)
            .patientMemberId(UPDATED_PATIENT_MEMBER_ID)
            .patientAccountNumber(UPDATED_PATIENT_ACCOUNT_NUMBER)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .claim835277ExceptionUuid(UPDATED_CLAIM_835277_EXCEPTION_UUID);
        return claim835277Exception;
    }

    @BeforeEach
    public void initTest() {
        claim835277Exception = createEntity(em);
    }

    @Test
    @Transactional
    void createClaim835277Exception() throws Exception {
        int databaseSizeBeforeCreate = claim835277ExceptionRepository.findAll().size();
        // Create the Claim835277Exception
        Claim835277ExceptionDTO claim835277ExceptionDTO = claim835277ExceptionMapper.toDto(claim835277Exception);
        restClaim835277ExceptionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claim835277ExceptionDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Claim835277Exception in the database
        List<Claim835277Exception> claim835277ExceptionList = claim835277ExceptionRepository.findAll();
        assertThat(claim835277ExceptionList).hasSize(databaseSizeBeforeCreate + 1);
        Claim835277Exception testClaim835277Exception = claim835277ExceptionList.get(claim835277ExceptionList.size() - 1);
        assertThat(testClaim835277Exception.getFileName()).isEqualTo(DEFAULT_FILE_NAME);
        assertThat(testClaim835277Exception.getRunDate()).isEqualTo(DEFAULT_RUN_DATE);
        assertThat(testClaim835277Exception.getIs277()).isEqualTo(DEFAULT_IS_277);
        assertThat(testClaim835277Exception.getIs835()).isEqualTo(DEFAULT_IS_835);
        assertThat(testClaim835277Exception.getClaimStatus277MasterId()).isEqualTo(DEFAULT_CLAIM_STATUS_277_MASTER_ID);
        assertThat(testClaim835277Exception.getClaimCob835MasterId()).isEqualTo(DEFAULT_CLAIM_COB_835_MASTER_ID);
        assertThat(testClaim835277Exception.getPayerClaimControlNumber()).isEqualTo(DEFAULT_PAYER_CLAIM_CONTROL_NUMBER);
        assertThat(testClaim835277Exception.getPatientControlNumber()).isEqualTo(DEFAULT_PATIENT_CONTROL_NUMBER);
        assertThat(testClaim835277Exception.getPatientMemberId()).isEqualTo(DEFAULT_PATIENT_MEMBER_ID);
        assertThat(testClaim835277Exception.getPatientAccountNumber()).isEqualTo(DEFAULT_PATIENT_ACCOUNT_NUMBER);
        assertThat(testClaim835277Exception.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testClaim835277Exception.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testClaim835277Exception.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testClaim835277Exception.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testClaim835277Exception.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testClaim835277Exception.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testClaim835277Exception.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testClaim835277Exception.getClaim835277ExceptionUuid()).isEqualTo(DEFAULT_CLAIM_835277_EXCEPTION_UUID);
    }

    @Test
    @Transactional
    void createClaim835277ExceptionWithExistingId() throws Exception {
        // Create the Claim835277Exception with an existing ID
        claim835277Exception.setExceptionId(1L);
        Claim835277ExceptionDTO claim835277ExceptionDTO = claim835277ExceptionMapper.toDto(claim835277Exception);

        int databaseSizeBeforeCreate = claim835277ExceptionRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restClaim835277ExceptionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claim835277ExceptionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Claim835277Exception in the database
        List<Claim835277Exception> claim835277ExceptionList = claim835277ExceptionRepository.findAll();
        assertThat(claim835277ExceptionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllClaim835277Exceptions() throws Exception {
        // Initialize the database
        claim835277ExceptionRepository.saveAndFlush(claim835277Exception);

        // Get all the claim835277ExceptionList
        restClaim835277ExceptionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=exceptionId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].exceptionId").value(hasItem(claim835277Exception.getExceptionId().intValue())))
            .andExpect(jsonPath("$.[*].fileName").value(hasItem(DEFAULT_FILE_NAME)))
            .andExpect(jsonPath("$.[*].runDate").value(hasItem(DEFAULT_RUN_DATE.toString())))
            .andExpect(jsonPath("$.[*].is277").value(hasItem(DEFAULT_IS_277.booleanValue())))
            .andExpect(jsonPath("$.[*].is835").value(hasItem(DEFAULT_IS_835.booleanValue())))
            .andExpect(jsonPath("$.[*].claimStatus277MasterId").value(hasItem(DEFAULT_CLAIM_STATUS_277_MASTER_ID.intValue())))
            .andExpect(jsonPath("$.[*].claimCob835MasterId").value(hasItem(DEFAULT_CLAIM_COB_835_MASTER_ID.intValue())))
            .andExpect(jsonPath("$.[*].payerClaimControlNumber").value(hasItem(DEFAULT_PAYER_CLAIM_CONTROL_NUMBER)))
            .andExpect(jsonPath("$.[*].patientControlNumber").value(hasItem(DEFAULT_PATIENT_CONTROL_NUMBER)))
            .andExpect(jsonPath("$.[*].patientMemberId").value(hasItem(DEFAULT_PATIENT_MEMBER_ID)))
            .andExpect(jsonPath("$.[*].patientAccountNumber").value(hasItem(DEFAULT_PATIENT_ACCOUNT_NUMBER)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].claim835277ExceptionUuid").value(hasItem(DEFAULT_CLAIM_835277_EXCEPTION_UUID.toString())));
    }

    @Test
    @Transactional
    void getClaim835277Exception() throws Exception {
        // Initialize the database
        claim835277ExceptionRepository.saveAndFlush(claim835277Exception);

        // Get the claim835277Exception
        restClaim835277ExceptionMockMvc
            .perform(get(ENTITY_API_URL_ID, claim835277Exception.getExceptionId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.exceptionId").value(claim835277Exception.getExceptionId().intValue()))
            .andExpect(jsonPath("$.fileName").value(DEFAULT_FILE_NAME))
            .andExpect(jsonPath("$.runDate").value(DEFAULT_RUN_DATE.toString()))
            .andExpect(jsonPath("$.is277").value(DEFAULT_IS_277.booleanValue()))
            .andExpect(jsonPath("$.is835").value(DEFAULT_IS_835.booleanValue()))
            .andExpect(jsonPath("$.claimStatus277MasterId").value(DEFAULT_CLAIM_STATUS_277_MASTER_ID.intValue()))
            .andExpect(jsonPath("$.claimCob835MasterId").value(DEFAULT_CLAIM_COB_835_MASTER_ID.intValue()))
            .andExpect(jsonPath("$.payerClaimControlNumber").value(DEFAULT_PAYER_CLAIM_CONTROL_NUMBER))
            .andExpect(jsonPath("$.patientControlNumber").value(DEFAULT_PATIENT_CONTROL_NUMBER))
            .andExpect(jsonPath("$.patientMemberId").value(DEFAULT_PATIENT_MEMBER_ID))
            .andExpect(jsonPath("$.patientAccountNumber").value(DEFAULT_PATIENT_ACCOUNT_NUMBER))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.claim835277ExceptionUuid").value(DEFAULT_CLAIM_835277_EXCEPTION_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingClaim835277Exception() throws Exception {
        // Get the claim835277Exception
        restClaim835277ExceptionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewClaim835277Exception() throws Exception {
        // Initialize the database
        claim835277ExceptionRepository.saveAndFlush(claim835277Exception);

        int databaseSizeBeforeUpdate = claim835277ExceptionRepository.findAll().size();

        // Update the claim835277Exception
        Claim835277Exception updatedClaim835277Exception = claim835277ExceptionRepository
            .findById(claim835277Exception.getExceptionId())
            .get();
        // Disconnect from session so that the updates on updatedClaim835277Exception are not directly saved in db
        em.detach(updatedClaim835277Exception);
        updatedClaim835277Exception
            .fileName(UPDATED_FILE_NAME)
            .runDate(UPDATED_RUN_DATE)
            .is277(UPDATED_IS_277)
            .is835(UPDATED_IS_835)
            .claimStatus277MasterId(UPDATED_CLAIM_STATUS_277_MASTER_ID)
            .claimCob835MasterId(UPDATED_CLAIM_COB_835_MASTER_ID)
            .payerClaimControlNumber(UPDATED_PAYER_CLAIM_CONTROL_NUMBER)
            .patientControlNumber(UPDATED_PATIENT_CONTROL_NUMBER)
            .patientMemberId(UPDATED_PATIENT_MEMBER_ID)
            .patientAccountNumber(UPDATED_PATIENT_ACCOUNT_NUMBER)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .claim835277ExceptionUuid(UPDATED_CLAIM_835277_EXCEPTION_UUID);
        Claim835277ExceptionDTO claim835277ExceptionDTO = claim835277ExceptionMapper.toDto(updatedClaim835277Exception);

        restClaim835277ExceptionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, claim835277ExceptionDTO.getExceptionId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claim835277ExceptionDTO))
            )
            .andExpect(status().isOk());

        // Validate the Claim835277Exception in the database
        List<Claim835277Exception> claim835277ExceptionList = claim835277ExceptionRepository.findAll();
        assertThat(claim835277ExceptionList).hasSize(databaseSizeBeforeUpdate);
        Claim835277Exception testClaim835277Exception = claim835277ExceptionList.get(claim835277ExceptionList.size() - 1);
        assertThat(testClaim835277Exception.getFileName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testClaim835277Exception.getRunDate()).isEqualTo(UPDATED_RUN_DATE);
        assertThat(testClaim835277Exception.getIs277()).isEqualTo(UPDATED_IS_277);
        assertThat(testClaim835277Exception.getIs835()).isEqualTo(UPDATED_IS_835);
        assertThat(testClaim835277Exception.getClaimStatus277MasterId()).isEqualTo(UPDATED_CLAIM_STATUS_277_MASTER_ID);
        assertThat(testClaim835277Exception.getClaimCob835MasterId()).isEqualTo(UPDATED_CLAIM_COB_835_MASTER_ID);
        assertThat(testClaim835277Exception.getPayerClaimControlNumber()).isEqualTo(UPDATED_PAYER_CLAIM_CONTROL_NUMBER);
        assertThat(testClaim835277Exception.getPatientControlNumber()).isEqualTo(UPDATED_PATIENT_CONTROL_NUMBER);
        assertThat(testClaim835277Exception.getPatientMemberId()).isEqualTo(UPDATED_PATIENT_MEMBER_ID);
        assertThat(testClaim835277Exception.getPatientAccountNumber()).isEqualTo(UPDATED_PATIENT_ACCOUNT_NUMBER);
        assertThat(testClaim835277Exception.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testClaim835277Exception.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testClaim835277Exception.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testClaim835277Exception.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testClaim835277Exception.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testClaim835277Exception.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testClaim835277Exception.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testClaim835277Exception.getClaim835277ExceptionUuid()).isEqualTo(UPDATED_CLAIM_835277_EXCEPTION_UUID);
    }

    @Test
    @Transactional
    void putNonExistingClaim835277Exception() throws Exception {
        int databaseSizeBeforeUpdate = claim835277ExceptionRepository.findAll().size();
        claim835277Exception.setExceptionId(count.incrementAndGet());

        // Create the Claim835277Exception
        Claim835277ExceptionDTO claim835277ExceptionDTO = claim835277ExceptionMapper.toDto(claim835277Exception);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClaim835277ExceptionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, claim835277ExceptionDTO.getExceptionId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claim835277ExceptionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Claim835277Exception in the database
        List<Claim835277Exception> claim835277ExceptionList = claim835277ExceptionRepository.findAll();
        assertThat(claim835277ExceptionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchClaim835277Exception() throws Exception {
        int databaseSizeBeforeUpdate = claim835277ExceptionRepository.findAll().size();
        claim835277Exception.setExceptionId(count.incrementAndGet());

        // Create the Claim835277Exception
        Claim835277ExceptionDTO claim835277ExceptionDTO = claim835277ExceptionMapper.toDto(claim835277Exception);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaim835277ExceptionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claim835277ExceptionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Claim835277Exception in the database
        List<Claim835277Exception> claim835277ExceptionList = claim835277ExceptionRepository.findAll();
        assertThat(claim835277ExceptionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamClaim835277Exception() throws Exception {
        int databaseSizeBeforeUpdate = claim835277ExceptionRepository.findAll().size();
        claim835277Exception.setExceptionId(count.incrementAndGet());

        // Create the Claim835277Exception
        Claim835277ExceptionDTO claim835277ExceptionDTO = claim835277ExceptionMapper.toDto(claim835277Exception);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaim835277ExceptionMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claim835277ExceptionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Claim835277Exception in the database
        List<Claim835277Exception> claim835277ExceptionList = claim835277ExceptionRepository.findAll();
        assertThat(claim835277ExceptionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateClaim835277ExceptionWithPatch() throws Exception {
        // Initialize the database
        claim835277ExceptionRepository.saveAndFlush(claim835277Exception);

        int databaseSizeBeforeUpdate = claim835277ExceptionRepository.findAll().size();

        // Update the claim835277Exception using partial update
        Claim835277Exception partialUpdatedClaim835277Exception = new Claim835277Exception();
        partialUpdatedClaim835277Exception.setExceptionId(claim835277Exception.getExceptionId());

        partialUpdatedClaim835277Exception
            .fileName(UPDATED_FILE_NAME)
            .runDate(UPDATED_RUN_DATE)
            .is835(UPDATED_IS_835)
            .claimStatus277MasterId(UPDATED_CLAIM_STATUS_277_MASTER_ID)
            .payerClaimControlNumber(UPDATED_PAYER_CLAIM_CONTROL_NUMBER)
            .patientAccountNumber(UPDATED_PATIENT_ACCOUNT_NUMBER)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .claim835277ExceptionUuid(UPDATED_CLAIM_835277_EXCEPTION_UUID);

        restClaim835277ExceptionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClaim835277Exception.getExceptionId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClaim835277Exception))
            )
            .andExpect(status().isOk());

        // Validate the Claim835277Exception in the database
        List<Claim835277Exception> claim835277ExceptionList = claim835277ExceptionRepository.findAll();
        assertThat(claim835277ExceptionList).hasSize(databaseSizeBeforeUpdate);
        Claim835277Exception testClaim835277Exception = claim835277ExceptionList.get(claim835277ExceptionList.size() - 1);
        assertThat(testClaim835277Exception.getFileName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testClaim835277Exception.getRunDate()).isEqualTo(UPDATED_RUN_DATE);
        assertThat(testClaim835277Exception.getIs277()).isEqualTo(DEFAULT_IS_277);
        assertThat(testClaim835277Exception.getIs835()).isEqualTo(UPDATED_IS_835);
        assertThat(testClaim835277Exception.getClaimStatus277MasterId()).isEqualTo(UPDATED_CLAIM_STATUS_277_MASTER_ID);
        assertThat(testClaim835277Exception.getClaimCob835MasterId()).isEqualTo(DEFAULT_CLAIM_COB_835_MASTER_ID);
        assertThat(testClaim835277Exception.getPayerClaimControlNumber()).isEqualTo(UPDATED_PAYER_CLAIM_CONTROL_NUMBER);
        assertThat(testClaim835277Exception.getPatientControlNumber()).isEqualTo(DEFAULT_PATIENT_CONTROL_NUMBER);
        assertThat(testClaim835277Exception.getPatientMemberId()).isEqualTo(DEFAULT_PATIENT_MEMBER_ID);
        assertThat(testClaim835277Exception.getPatientAccountNumber()).isEqualTo(UPDATED_PATIENT_ACCOUNT_NUMBER);
        assertThat(testClaim835277Exception.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testClaim835277Exception.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testClaim835277Exception.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testClaim835277Exception.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testClaim835277Exception.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testClaim835277Exception.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testClaim835277Exception.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testClaim835277Exception.getClaim835277ExceptionUuid()).isEqualTo(UPDATED_CLAIM_835277_EXCEPTION_UUID);
    }

    @Test
    @Transactional
    void fullUpdateClaim835277ExceptionWithPatch() throws Exception {
        // Initialize the database
        claim835277ExceptionRepository.saveAndFlush(claim835277Exception);

        int databaseSizeBeforeUpdate = claim835277ExceptionRepository.findAll().size();

        // Update the claim835277Exception using partial update
        Claim835277Exception partialUpdatedClaim835277Exception = new Claim835277Exception();
        partialUpdatedClaim835277Exception.setExceptionId(claim835277Exception.getExceptionId());

        partialUpdatedClaim835277Exception
            .fileName(UPDATED_FILE_NAME)
            .runDate(UPDATED_RUN_DATE)
            .is277(UPDATED_IS_277)
            .is835(UPDATED_IS_835)
            .claimStatus277MasterId(UPDATED_CLAIM_STATUS_277_MASTER_ID)
            .claimCob835MasterId(UPDATED_CLAIM_COB_835_MASTER_ID)
            .payerClaimControlNumber(UPDATED_PAYER_CLAIM_CONTROL_NUMBER)
            .patientControlNumber(UPDATED_PATIENT_CONTROL_NUMBER)
            .patientMemberId(UPDATED_PATIENT_MEMBER_ID)
            .patientAccountNumber(UPDATED_PATIENT_ACCOUNT_NUMBER)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .claim835277ExceptionUuid(UPDATED_CLAIM_835277_EXCEPTION_UUID);

        restClaim835277ExceptionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClaim835277Exception.getExceptionId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClaim835277Exception))
            )
            .andExpect(status().isOk());

        // Validate the Claim835277Exception in the database
        List<Claim835277Exception> claim835277ExceptionList = claim835277ExceptionRepository.findAll();
        assertThat(claim835277ExceptionList).hasSize(databaseSizeBeforeUpdate);
        Claim835277Exception testClaim835277Exception = claim835277ExceptionList.get(claim835277ExceptionList.size() - 1);
        assertThat(testClaim835277Exception.getFileName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testClaim835277Exception.getRunDate()).isEqualTo(UPDATED_RUN_DATE);
        assertThat(testClaim835277Exception.getIs277()).isEqualTo(UPDATED_IS_277);
        assertThat(testClaim835277Exception.getIs835()).isEqualTo(UPDATED_IS_835);
        assertThat(testClaim835277Exception.getClaimStatus277MasterId()).isEqualTo(UPDATED_CLAIM_STATUS_277_MASTER_ID);
        assertThat(testClaim835277Exception.getClaimCob835MasterId()).isEqualTo(UPDATED_CLAIM_COB_835_MASTER_ID);
        assertThat(testClaim835277Exception.getPayerClaimControlNumber()).isEqualTo(UPDATED_PAYER_CLAIM_CONTROL_NUMBER);
        assertThat(testClaim835277Exception.getPatientControlNumber()).isEqualTo(UPDATED_PATIENT_CONTROL_NUMBER);
        assertThat(testClaim835277Exception.getPatientMemberId()).isEqualTo(UPDATED_PATIENT_MEMBER_ID);
        assertThat(testClaim835277Exception.getPatientAccountNumber()).isEqualTo(UPDATED_PATIENT_ACCOUNT_NUMBER);
        assertThat(testClaim835277Exception.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testClaim835277Exception.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testClaim835277Exception.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testClaim835277Exception.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testClaim835277Exception.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testClaim835277Exception.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testClaim835277Exception.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testClaim835277Exception.getClaim835277ExceptionUuid()).isEqualTo(UPDATED_CLAIM_835277_EXCEPTION_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingClaim835277Exception() throws Exception {
        int databaseSizeBeforeUpdate = claim835277ExceptionRepository.findAll().size();
        claim835277Exception.setExceptionId(count.incrementAndGet());

        // Create the Claim835277Exception
        Claim835277ExceptionDTO claim835277ExceptionDTO = claim835277ExceptionMapper.toDto(claim835277Exception);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClaim835277ExceptionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, claim835277ExceptionDTO.getExceptionId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(claim835277ExceptionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Claim835277Exception in the database
        List<Claim835277Exception> claim835277ExceptionList = claim835277ExceptionRepository.findAll();
        assertThat(claim835277ExceptionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchClaim835277Exception() throws Exception {
        int databaseSizeBeforeUpdate = claim835277ExceptionRepository.findAll().size();
        claim835277Exception.setExceptionId(count.incrementAndGet());

        // Create the Claim835277Exception
        Claim835277ExceptionDTO claim835277ExceptionDTO = claim835277ExceptionMapper.toDto(claim835277Exception);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaim835277ExceptionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(claim835277ExceptionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Claim835277Exception in the database
        List<Claim835277Exception> claim835277ExceptionList = claim835277ExceptionRepository.findAll();
        assertThat(claim835277ExceptionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamClaim835277Exception() throws Exception {
        int databaseSizeBeforeUpdate = claim835277ExceptionRepository.findAll().size();
        claim835277Exception.setExceptionId(count.incrementAndGet());

        // Create the Claim835277Exception
        Claim835277ExceptionDTO claim835277ExceptionDTO = claim835277ExceptionMapper.toDto(claim835277Exception);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaim835277ExceptionMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(claim835277ExceptionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Claim835277Exception in the database
        List<Claim835277Exception> claim835277ExceptionList = claim835277ExceptionRepository.findAll();
        assertThat(claim835277ExceptionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteClaim835277Exception() throws Exception {
        // Initialize the database
        claim835277ExceptionRepository.saveAndFlush(claim835277Exception);

        int databaseSizeBeforeDelete = claim835277ExceptionRepository.findAll().size();

        // Delete the claim835277Exception
        restClaim835277ExceptionMockMvc
            .perform(delete(ENTITY_API_URL_ID, claim835277Exception.getExceptionId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Claim835277Exception> claim835277ExceptionList = claim835277ExceptionRepository.findAll();
        assertThat(claim835277ExceptionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
