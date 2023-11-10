package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.Claims835CrossoverException;
import com.sunknowledge.dme.rcm.repository.Claims835CrossoverExceptionRepository;
import com.sunknowledge.dme.rcm.service.dto.Claims835CrossoverExceptionDTO;
import com.sunknowledge.dme.rcm.service.mapper.Claims835CrossoverExceptionMapper;
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
 * Integration tests for the {@link Claims835CrossoverExceptionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class Claims835CrossoverExceptionResourceIT {

    private static final Long DEFAULT_CLAIM_COB_835_MASTER_ID = 1L;
    private static final Long UPDATED_CLAIM_COB_835_MASTER_ID = 2L;

    private static final String DEFAULT_PATIENT_CONTROL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_CONTROL_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_PAYER_CLAIM_CONTROL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_CLAIM_CONTROL_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_CROSSOVER_CARRIER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CROSSOVER_CARRIER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CROSSOVER_CARRIER_PAYER_ID = "AAAAAAAAAA";
    private static final String UPDATED_CROSSOVER_CARRIER_PAYER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CROSSOVER_CARRIER_PAYER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CROSSOVER_CARRIER_PAYER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EXCEPTION_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_EXCEPTION_TYPE = "BBBBBBBBBB";

    private static final UUID DEFAULT_CLAIMS_835_CROSSOVER_EXCEPTION_UUID = UUID.randomUUID();
    private static final UUID UPDATED_CLAIMS_835_CROSSOVER_EXCEPTION_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/claims-835-crossover-exceptions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{claims835CrossoverExceptionId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private Claims835CrossoverExceptionRepository claims835CrossoverExceptionRepository;

    @Autowired
    private Claims835CrossoverExceptionMapper claims835CrossoverExceptionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restClaims835CrossoverExceptionMockMvc;

    private Claims835CrossoverException claims835CrossoverException;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Claims835CrossoverException createEntity(EntityManager em) {
        Claims835CrossoverException claims835CrossoverException = new Claims835CrossoverException()
            .claimCob835MasterId(DEFAULT_CLAIM_COB_835_MASTER_ID)
            .patientControlNumber(DEFAULT_PATIENT_CONTROL_NUMBER)
            .payerClaimControlNumber(DEFAULT_PAYER_CLAIM_CONTROL_NUMBER)
            .crossoverCarrierName(DEFAULT_CROSSOVER_CARRIER_NAME)
            .crossoverCarrierPayerId(DEFAULT_CROSSOVER_CARRIER_PAYER_ID)
            .crossoverCarrierPayerName(DEFAULT_CROSSOVER_CARRIER_PAYER_NAME)
            .exceptionType(DEFAULT_EXCEPTION_TYPE)
            .claims835CrossoverExceptionUuid(DEFAULT_CLAIMS_835_CROSSOVER_EXCEPTION_UUID);
        return claims835CrossoverException;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Claims835CrossoverException createUpdatedEntity(EntityManager em) {
        Claims835CrossoverException claims835CrossoverException = new Claims835CrossoverException()
            .claimCob835MasterId(UPDATED_CLAIM_COB_835_MASTER_ID)
            .patientControlNumber(UPDATED_PATIENT_CONTROL_NUMBER)
            .payerClaimControlNumber(UPDATED_PAYER_CLAIM_CONTROL_NUMBER)
            .crossoverCarrierName(UPDATED_CROSSOVER_CARRIER_NAME)
            .crossoverCarrierPayerId(UPDATED_CROSSOVER_CARRIER_PAYER_ID)
            .crossoverCarrierPayerName(UPDATED_CROSSOVER_CARRIER_PAYER_NAME)
            .exceptionType(UPDATED_EXCEPTION_TYPE)
            .claims835CrossoverExceptionUuid(UPDATED_CLAIMS_835_CROSSOVER_EXCEPTION_UUID);
        return claims835CrossoverException;
    }

    @BeforeEach
    public void initTest() {
        claims835CrossoverException = createEntity(em);
    }

    @Test
    @Transactional
    void createClaims835CrossoverException() throws Exception {
        int databaseSizeBeforeCreate = claims835CrossoverExceptionRepository.findAll().size();
        // Create the Claims835CrossoverException
        Claims835CrossoverExceptionDTO claims835CrossoverExceptionDTO = claims835CrossoverExceptionMapper.toDto(
            claims835CrossoverException
        );
        restClaims835CrossoverExceptionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claims835CrossoverExceptionDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Claims835CrossoverException in the database
        List<Claims835CrossoverException> claims835CrossoverExceptionList = claims835CrossoverExceptionRepository.findAll();
        assertThat(claims835CrossoverExceptionList).hasSize(databaseSizeBeforeCreate + 1);
        Claims835CrossoverException testClaims835CrossoverException = claims835CrossoverExceptionList.get(
            claims835CrossoverExceptionList.size() - 1
        );
        assertThat(testClaims835CrossoverException.getClaimCob835MasterId()).isEqualTo(DEFAULT_CLAIM_COB_835_MASTER_ID);
        assertThat(testClaims835CrossoverException.getPatientControlNumber()).isEqualTo(DEFAULT_PATIENT_CONTROL_NUMBER);
        assertThat(testClaims835CrossoverException.getPayerClaimControlNumber()).isEqualTo(DEFAULT_PAYER_CLAIM_CONTROL_NUMBER);
        assertThat(testClaims835CrossoverException.getCrossoverCarrierName()).isEqualTo(DEFAULT_CROSSOVER_CARRIER_NAME);
        assertThat(testClaims835CrossoverException.getCrossoverCarrierPayerId()).isEqualTo(DEFAULT_CROSSOVER_CARRIER_PAYER_ID);
        assertThat(testClaims835CrossoverException.getCrossoverCarrierPayerName()).isEqualTo(DEFAULT_CROSSOVER_CARRIER_PAYER_NAME);
        assertThat(testClaims835CrossoverException.getExceptionType()).isEqualTo(DEFAULT_EXCEPTION_TYPE);
        assertThat(testClaims835CrossoverException.getClaims835CrossoverExceptionUuid())
            .isEqualTo(DEFAULT_CLAIMS_835_CROSSOVER_EXCEPTION_UUID);
    }

    @Test
    @Transactional
    void createClaims835CrossoverExceptionWithExistingId() throws Exception {
        // Create the Claims835CrossoverException with an existing ID
        claims835CrossoverException.setClaims835CrossoverExceptionId(1L);
        Claims835CrossoverExceptionDTO claims835CrossoverExceptionDTO = claims835CrossoverExceptionMapper.toDto(
            claims835CrossoverException
        );

        int databaseSizeBeforeCreate = claims835CrossoverExceptionRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restClaims835CrossoverExceptionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claims835CrossoverExceptionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Claims835CrossoverException in the database
        List<Claims835CrossoverException> claims835CrossoverExceptionList = claims835CrossoverExceptionRepository.findAll();
        assertThat(claims835CrossoverExceptionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllClaims835CrossoverExceptions() throws Exception {
        // Initialize the database
        claims835CrossoverExceptionRepository.saveAndFlush(claims835CrossoverException);

        // Get all the claims835CrossoverExceptionList
        restClaims835CrossoverExceptionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=claims835CrossoverExceptionId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].claims835CrossoverExceptionId")
                    .value(hasItem(claims835CrossoverException.getClaims835CrossoverExceptionId().intValue()))
            )
            .andExpect(jsonPath("$.[*].claimCob835MasterId").value(hasItem(DEFAULT_CLAIM_COB_835_MASTER_ID.intValue())))
            .andExpect(jsonPath("$.[*].patientControlNumber").value(hasItem(DEFAULT_PATIENT_CONTROL_NUMBER)))
            .andExpect(jsonPath("$.[*].payerClaimControlNumber").value(hasItem(DEFAULT_PAYER_CLAIM_CONTROL_NUMBER)))
            .andExpect(jsonPath("$.[*].crossoverCarrierName").value(hasItem(DEFAULT_CROSSOVER_CARRIER_NAME)))
            .andExpect(jsonPath("$.[*].crossoverCarrierPayerId").value(hasItem(DEFAULT_CROSSOVER_CARRIER_PAYER_ID)))
            .andExpect(jsonPath("$.[*].crossoverCarrierPayerName").value(hasItem(DEFAULT_CROSSOVER_CARRIER_PAYER_NAME)))
            .andExpect(jsonPath("$.[*].exceptionType").value(hasItem(DEFAULT_EXCEPTION_TYPE)))
            .andExpect(
                jsonPath("$.[*].claims835CrossoverExceptionUuid").value(hasItem(DEFAULT_CLAIMS_835_CROSSOVER_EXCEPTION_UUID.toString()))
            );
    }

    @Test
    @Transactional
    void getClaims835CrossoverException() throws Exception {
        // Initialize the database
        claims835CrossoverExceptionRepository.saveAndFlush(claims835CrossoverException);

        // Get the claims835CrossoverException
        restClaims835CrossoverExceptionMockMvc
            .perform(get(ENTITY_API_URL_ID, claims835CrossoverException.getClaims835CrossoverExceptionId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.claims835CrossoverExceptionId").value(claims835CrossoverException.getClaims835CrossoverExceptionId().intValue())
            )
            .andExpect(jsonPath("$.claimCob835MasterId").value(DEFAULT_CLAIM_COB_835_MASTER_ID.intValue()))
            .andExpect(jsonPath("$.patientControlNumber").value(DEFAULT_PATIENT_CONTROL_NUMBER))
            .andExpect(jsonPath("$.payerClaimControlNumber").value(DEFAULT_PAYER_CLAIM_CONTROL_NUMBER))
            .andExpect(jsonPath("$.crossoverCarrierName").value(DEFAULT_CROSSOVER_CARRIER_NAME))
            .andExpect(jsonPath("$.crossoverCarrierPayerId").value(DEFAULT_CROSSOVER_CARRIER_PAYER_ID))
            .andExpect(jsonPath("$.crossoverCarrierPayerName").value(DEFAULT_CROSSOVER_CARRIER_PAYER_NAME))
            .andExpect(jsonPath("$.exceptionType").value(DEFAULT_EXCEPTION_TYPE))
            .andExpect(jsonPath("$.claims835CrossoverExceptionUuid").value(DEFAULT_CLAIMS_835_CROSSOVER_EXCEPTION_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingClaims835CrossoverException() throws Exception {
        // Get the claims835CrossoverException
        restClaims835CrossoverExceptionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewClaims835CrossoverException() throws Exception {
        // Initialize the database
        claims835CrossoverExceptionRepository.saveAndFlush(claims835CrossoverException);

        int databaseSizeBeforeUpdate = claims835CrossoverExceptionRepository.findAll().size();

        // Update the claims835CrossoverException
        Claims835CrossoverException updatedClaims835CrossoverException = claims835CrossoverExceptionRepository
            .findById(claims835CrossoverException.getClaims835CrossoverExceptionId())
            .get();
        // Disconnect from session so that the updates on updatedClaims835CrossoverException are not directly saved in db
        em.detach(updatedClaims835CrossoverException);
        updatedClaims835CrossoverException
            .claimCob835MasterId(UPDATED_CLAIM_COB_835_MASTER_ID)
            .patientControlNumber(UPDATED_PATIENT_CONTROL_NUMBER)
            .payerClaimControlNumber(UPDATED_PAYER_CLAIM_CONTROL_NUMBER)
            .crossoverCarrierName(UPDATED_CROSSOVER_CARRIER_NAME)
            .crossoverCarrierPayerId(UPDATED_CROSSOVER_CARRIER_PAYER_ID)
            .crossoverCarrierPayerName(UPDATED_CROSSOVER_CARRIER_PAYER_NAME)
            .exceptionType(UPDATED_EXCEPTION_TYPE)
            .claims835CrossoverExceptionUuid(UPDATED_CLAIMS_835_CROSSOVER_EXCEPTION_UUID);
        Claims835CrossoverExceptionDTO claims835CrossoverExceptionDTO = claims835CrossoverExceptionMapper.toDto(
            updatedClaims835CrossoverException
        );

        restClaims835CrossoverExceptionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, claims835CrossoverExceptionDTO.getClaims835CrossoverExceptionId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claims835CrossoverExceptionDTO))
            )
            .andExpect(status().isOk());

        // Validate the Claims835CrossoverException in the database
        List<Claims835CrossoverException> claims835CrossoverExceptionList = claims835CrossoverExceptionRepository.findAll();
        assertThat(claims835CrossoverExceptionList).hasSize(databaseSizeBeforeUpdate);
        Claims835CrossoverException testClaims835CrossoverException = claims835CrossoverExceptionList.get(
            claims835CrossoverExceptionList.size() - 1
        );
        assertThat(testClaims835CrossoverException.getClaimCob835MasterId()).isEqualTo(UPDATED_CLAIM_COB_835_MASTER_ID);
        assertThat(testClaims835CrossoverException.getPatientControlNumber()).isEqualTo(UPDATED_PATIENT_CONTROL_NUMBER);
        assertThat(testClaims835CrossoverException.getPayerClaimControlNumber()).isEqualTo(UPDATED_PAYER_CLAIM_CONTROL_NUMBER);
        assertThat(testClaims835CrossoverException.getCrossoverCarrierName()).isEqualTo(UPDATED_CROSSOVER_CARRIER_NAME);
        assertThat(testClaims835CrossoverException.getCrossoverCarrierPayerId()).isEqualTo(UPDATED_CROSSOVER_CARRIER_PAYER_ID);
        assertThat(testClaims835CrossoverException.getCrossoverCarrierPayerName()).isEqualTo(UPDATED_CROSSOVER_CARRIER_PAYER_NAME);
        assertThat(testClaims835CrossoverException.getExceptionType()).isEqualTo(UPDATED_EXCEPTION_TYPE);
        assertThat(testClaims835CrossoverException.getClaims835CrossoverExceptionUuid())
            .isEqualTo(UPDATED_CLAIMS_835_CROSSOVER_EXCEPTION_UUID);
    }

    @Test
    @Transactional
    void putNonExistingClaims835CrossoverException() throws Exception {
        int databaseSizeBeforeUpdate = claims835CrossoverExceptionRepository.findAll().size();
        claims835CrossoverException.setClaims835CrossoverExceptionId(count.incrementAndGet());

        // Create the Claims835CrossoverException
        Claims835CrossoverExceptionDTO claims835CrossoverExceptionDTO = claims835CrossoverExceptionMapper.toDto(
            claims835CrossoverException
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClaims835CrossoverExceptionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, claims835CrossoverExceptionDTO.getClaims835CrossoverExceptionId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claims835CrossoverExceptionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Claims835CrossoverException in the database
        List<Claims835CrossoverException> claims835CrossoverExceptionList = claims835CrossoverExceptionRepository.findAll();
        assertThat(claims835CrossoverExceptionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchClaims835CrossoverException() throws Exception {
        int databaseSizeBeforeUpdate = claims835CrossoverExceptionRepository.findAll().size();
        claims835CrossoverException.setClaims835CrossoverExceptionId(count.incrementAndGet());

        // Create the Claims835CrossoverException
        Claims835CrossoverExceptionDTO claims835CrossoverExceptionDTO = claims835CrossoverExceptionMapper.toDto(
            claims835CrossoverException
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaims835CrossoverExceptionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claims835CrossoverExceptionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Claims835CrossoverException in the database
        List<Claims835CrossoverException> claims835CrossoverExceptionList = claims835CrossoverExceptionRepository.findAll();
        assertThat(claims835CrossoverExceptionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamClaims835CrossoverException() throws Exception {
        int databaseSizeBeforeUpdate = claims835CrossoverExceptionRepository.findAll().size();
        claims835CrossoverException.setClaims835CrossoverExceptionId(count.incrementAndGet());

        // Create the Claims835CrossoverException
        Claims835CrossoverExceptionDTO claims835CrossoverExceptionDTO = claims835CrossoverExceptionMapper.toDto(
            claims835CrossoverException
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaims835CrossoverExceptionMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claims835CrossoverExceptionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Claims835CrossoverException in the database
        List<Claims835CrossoverException> claims835CrossoverExceptionList = claims835CrossoverExceptionRepository.findAll();
        assertThat(claims835CrossoverExceptionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateClaims835CrossoverExceptionWithPatch() throws Exception {
        // Initialize the database
        claims835CrossoverExceptionRepository.saveAndFlush(claims835CrossoverException);

        int databaseSizeBeforeUpdate = claims835CrossoverExceptionRepository.findAll().size();

        // Update the claims835CrossoverException using partial update
        Claims835CrossoverException partialUpdatedClaims835CrossoverException = new Claims835CrossoverException();
        partialUpdatedClaims835CrossoverException.setClaims835CrossoverExceptionId(
            claims835CrossoverException.getClaims835CrossoverExceptionId()
        );

        partialUpdatedClaims835CrossoverException
            .crossoverCarrierName(UPDATED_CROSSOVER_CARRIER_NAME)
            .exceptionType(UPDATED_EXCEPTION_TYPE);

        restClaims835CrossoverExceptionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClaims835CrossoverException.getClaims835CrossoverExceptionId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClaims835CrossoverException))
            )
            .andExpect(status().isOk());

        // Validate the Claims835CrossoverException in the database
        List<Claims835CrossoverException> claims835CrossoverExceptionList = claims835CrossoverExceptionRepository.findAll();
        assertThat(claims835CrossoverExceptionList).hasSize(databaseSizeBeforeUpdate);
        Claims835CrossoverException testClaims835CrossoverException = claims835CrossoverExceptionList.get(
            claims835CrossoverExceptionList.size() - 1
        );
        assertThat(testClaims835CrossoverException.getClaimCob835MasterId()).isEqualTo(DEFAULT_CLAIM_COB_835_MASTER_ID);
        assertThat(testClaims835CrossoverException.getPatientControlNumber()).isEqualTo(DEFAULT_PATIENT_CONTROL_NUMBER);
        assertThat(testClaims835CrossoverException.getPayerClaimControlNumber()).isEqualTo(DEFAULT_PAYER_CLAIM_CONTROL_NUMBER);
        assertThat(testClaims835CrossoverException.getCrossoverCarrierName()).isEqualTo(UPDATED_CROSSOVER_CARRIER_NAME);
        assertThat(testClaims835CrossoverException.getCrossoverCarrierPayerId()).isEqualTo(DEFAULT_CROSSOVER_CARRIER_PAYER_ID);
        assertThat(testClaims835CrossoverException.getCrossoverCarrierPayerName()).isEqualTo(DEFAULT_CROSSOVER_CARRIER_PAYER_NAME);
        assertThat(testClaims835CrossoverException.getExceptionType()).isEqualTo(UPDATED_EXCEPTION_TYPE);
        assertThat(testClaims835CrossoverException.getClaims835CrossoverExceptionUuid())
            .isEqualTo(DEFAULT_CLAIMS_835_CROSSOVER_EXCEPTION_UUID);
    }

    @Test
    @Transactional
    void fullUpdateClaims835CrossoverExceptionWithPatch() throws Exception {
        // Initialize the database
        claims835CrossoverExceptionRepository.saveAndFlush(claims835CrossoverException);

        int databaseSizeBeforeUpdate = claims835CrossoverExceptionRepository.findAll().size();

        // Update the claims835CrossoverException using partial update
        Claims835CrossoverException partialUpdatedClaims835CrossoverException = new Claims835CrossoverException();
        partialUpdatedClaims835CrossoverException.setClaims835CrossoverExceptionId(
            claims835CrossoverException.getClaims835CrossoverExceptionId()
        );

        partialUpdatedClaims835CrossoverException
            .claimCob835MasterId(UPDATED_CLAIM_COB_835_MASTER_ID)
            .patientControlNumber(UPDATED_PATIENT_CONTROL_NUMBER)
            .payerClaimControlNumber(UPDATED_PAYER_CLAIM_CONTROL_NUMBER)
            .crossoverCarrierName(UPDATED_CROSSOVER_CARRIER_NAME)
            .crossoverCarrierPayerId(UPDATED_CROSSOVER_CARRIER_PAYER_ID)
            .crossoverCarrierPayerName(UPDATED_CROSSOVER_CARRIER_PAYER_NAME)
            .exceptionType(UPDATED_EXCEPTION_TYPE)
            .claims835CrossoverExceptionUuid(UPDATED_CLAIMS_835_CROSSOVER_EXCEPTION_UUID);

        restClaims835CrossoverExceptionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClaims835CrossoverException.getClaims835CrossoverExceptionId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClaims835CrossoverException))
            )
            .andExpect(status().isOk());

        // Validate the Claims835CrossoverException in the database
        List<Claims835CrossoverException> claims835CrossoverExceptionList = claims835CrossoverExceptionRepository.findAll();
        assertThat(claims835CrossoverExceptionList).hasSize(databaseSizeBeforeUpdate);
        Claims835CrossoverException testClaims835CrossoverException = claims835CrossoverExceptionList.get(
            claims835CrossoverExceptionList.size() - 1
        );
        assertThat(testClaims835CrossoverException.getClaimCob835MasterId()).isEqualTo(UPDATED_CLAIM_COB_835_MASTER_ID);
        assertThat(testClaims835CrossoverException.getPatientControlNumber()).isEqualTo(UPDATED_PATIENT_CONTROL_NUMBER);
        assertThat(testClaims835CrossoverException.getPayerClaimControlNumber()).isEqualTo(UPDATED_PAYER_CLAIM_CONTROL_NUMBER);
        assertThat(testClaims835CrossoverException.getCrossoverCarrierName()).isEqualTo(UPDATED_CROSSOVER_CARRIER_NAME);
        assertThat(testClaims835CrossoverException.getCrossoverCarrierPayerId()).isEqualTo(UPDATED_CROSSOVER_CARRIER_PAYER_ID);
        assertThat(testClaims835CrossoverException.getCrossoverCarrierPayerName()).isEqualTo(UPDATED_CROSSOVER_CARRIER_PAYER_NAME);
        assertThat(testClaims835CrossoverException.getExceptionType()).isEqualTo(UPDATED_EXCEPTION_TYPE);
        assertThat(testClaims835CrossoverException.getClaims835CrossoverExceptionUuid())
            .isEqualTo(UPDATED_CLAIMS_835_CROSSOVER_EXCEPTION_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingClaims835CrossoverException() throws Exception {
        int databaseSizeBeforeUpdate = claims835CrossoverExceptionRepository.findAll().size();
        claims835CrossoverException.setClaims835CrossoverExceptionId(count.incrementAndGet());

        // Create the Claims835CrossoverException
        Claims835CrossoverExceptionDTO claims835CrossoverExceptionDTO = claims835CrossoverExceptionMapper.toDto(
            claims835CrossoverException
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClaims835CrossoverExceptionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, claims835CrossoverExceptionDTO.getClaims835CrossoverExceptionId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(claims835CrossoverExceptionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Claims835CrossoverException in the database
        List<Claims835CrossoverException> claims835CrossoverExceptionList = claims835CrossoverExceptionRepository.findAll();
        assertThat(claims835CrossoverExceptionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchClaims835CrossoverException() throws Exception {
        int databaseSizeBeforeUpdate = claims835CrossoverExceptionRepository.findAll().size();
        claims835CrossoverException.setClaims835CrossoverExceptionId(count.incrementAndGet());

        // Create the Claims835CrossoverException
        Claims835CrossoverExceptionDTO claims835CrossoverExceptionDTO = claims835CrossoverExceptionMapper.toDto(
            claims835CrossoverException
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaims835CrossoverExceptionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(claims835CrossoverExceptionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Claims835CrossoverException in the database
        List<Claims835CrossoverException> claims835CrossoverExceptionList = claims835CrossoverExceptionRepository.findAll();
        assertThat(claims835CrossoverExceptionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamClaims835CrossoverException() throws Exception {
        int databaseSizeBeforeUpdate = claims835CrossoverExceptionRepository.findAll().size();
        claims835CrossoverException.setClaims835CrossoverExceptionId(count.incrementAndGet());

        // Create the Claims835CrossoverException
        Claims835CrossoverExceptionDTO claims835CrossoverExceptionDTO = claims835CrossoverExceptionMapper.toDto(
            claims835CrossoverException
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaims835CrossoverExceptionMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(claims835CrossoverExceptionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Claims835CrossoverException in the database
        List<Claims835CrossoverException> claims835CrossoverExceptionList = claims835CrossoverExceptionRepository.findAll();
        assertThat(claims835CrossoverExceptionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteClaims835CrossoverException() throws Exception {
        // Initialize the database
        claims835CrossoverExceptionRepository.saveAndFlush(claims835CrossoverException);

        int databaseSizeBeforeDelete = claims835CrossoverExceptionRepository.findAll().size();

        // Delete the claims835CrossoverException
        restClaims835CrossoverExceptionMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, claims835CrossoverException.getClaims835CrossoverExceptionId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Claims835CrossoverException> claims835CrossoverExceptionList = claims835CrossoverExceptionRepository.findAll();
        assertThat(claims835CrossoverExceptionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
