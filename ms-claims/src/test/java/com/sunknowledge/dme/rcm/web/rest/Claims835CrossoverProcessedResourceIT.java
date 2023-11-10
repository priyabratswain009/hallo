package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.Claims835CrossoverProcessed;
import com.sunknowledge.dme.rcm.repository.Claims835CrossoverProcessedRepository;
import com.sunknowledge.dme.rcm.service.dto.Claims835CrossoverProcessedDTO;
import com.sunknowledge.dme.rcm.service.mapper.Claims835CrossoverProcessedMapper;
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
 * Integration tests for the {@link Claims835CrossoverProcessedResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class Claims835CrossoverProcessedResourceIT {

    private static final Long DEFAULT_CLAIM_COB_835_MASTER_ID = 1L;
    private static final Long UPDATED_CLAIM_COB_835_MASTER_ID = 2L;

    private static final String DEFAULT_PATIENT_CONTROL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_CONTROL_NUMBER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PROCESSED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PROCESSED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final UUID DEFAULT_CLAIMS_835_CROSSOVER_PROCESSED = UUID.randomUUID();
    private static final UUID UPDATED_CLAIMS_835_CROSSOVER_PROCESSED = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/claims-835-crossover-processeds";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{claims835CrossoverProcessedId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private Claims835CrossoverProcessedRepository claims835CrossoverProcessedRepository;

    @Autowired
    private Claims835CrossoverProcessedMapper claims835CrossoverProcessedMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restClaims835CrossoverProcessedMockMvc;

    private Claims835CrossoverProcessed claims835CrossoverProcessed;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Claims835CrossoverProcessed createEntity(EntityManager em) {
        Claims835CrossoverProcessed claims835CrossoverProcessed = new Claims835CrossoverProcessed()
            .claimCob835MasterId(DEFAULT_CLAIM_COB_835_MASTER_ID)
            .patientControlNumber(DEFAULT_PATIENT_CONTROL_NUMBER)
            .processedDate(DEFAULT_PROCESSED_DATE)
            .claims835CrossoverProcessed(DEFAULT_CLAIMS_835_CROSSOVER_PROCESSED);
        return claims835CrossoverProcessed;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Claims835CrossoverProcessed createUpdatedEntity(EntityManager em) {
        Claims835CrossoverProcessed claims835CrossoverProcessed = new Claims835CrossoverProcessed()
            .claimCob835MasterId(UPDATED_CLAIM_COB_835_MASTER_ID)
            .patientControlNumber(UPDATED_PATIENT_CONTROL_NUMBER)
            .processedDate(UPDATED_PROCESSED_DATE)
            .claims835CrossoverProcessed(UPDATED_CLAIMS_835_CROSSOVER_PROCESSED);
        return claims835CrossoverProcessed;
    }

    @BeforeEach
    public void initTest() {
        claims835CrossoverProcessed = createEntity(em);
    }

    @Test
    @Transactional
    void createClaims835CrossoverProcessed() throws Exception {
        int databaseSizeBeforeCreate = claims835CrossoverProcessedRepository.findAll().size();
        // Create the Claims835CrossoverProcessed
        Claims835CrossoverProcessedDTO claims835CrossoverProcessedDTO = claims835CrossoverProcessedMapper.toDto(
            claims835CrossoverProcessed
        );
        restClaims835CrossoverProcessedMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claims835CrossoverProcessedDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Claims835CrossoverProcessed in the database
        List<Claims835CrossoverProcessed> claims835CrossoverProcessedList = claims835CrossoverProcessedRepository.findAll();
        assertThat(claims835CrossoverProcessedList).hasSize(databaseSizeBeforeCreate + 1);
        Claims835CrossoverProcessed testClaims835CrossoverProcessed = claims835CrossoverProcessedList.get(
            claims835CrossoverProcessedList.size() - 1
        );
        assertThat(testClaims835CrossoverProcessed.getClaimCob835MasterId()).isEqualTo(DEFAULT_CLAIM_COB_835_MASTER_ID);
        assertThat(testClaims835CrossoverProcessed.getPatientControlNumber()).isEqualTo(DEFAULT_PATIENT_CONTROL_NUMBER);
        assertThat(testClaims835CrossoverProcessed.getProcessedDate()).isEqualTo(DEFAULT_PROCESSED_DATE);
        assertThat(testClaims835CrossoverProcessed.getClaims835CrossoverProcessed()).isEqualTo(DEFAULT_CLAIMS_835_CROSSOVER_PROCESSED);
    }

    @Test
    @Transactional
    void createClaims835CrossoverProcessedWithExistingId() throws Exception {
        // Create the Claims835CrossoverProcessed with an existing ID
        claims835CrossoverProcessed.setClaims835CrossoverProcessedId(1L);
        Claims835CrossoverProcessedDTO claims835CrossoverProcessedDTO = claims835CrossoverProcessedMapper.toDto(
            claims835CrossoverProcessed
        );

        int databaseSizeBeforeCreate = claims835CrossoverProcessedRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restClaims835CrossoverProcessedMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claims835CrossoverProcessedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Claims835CrossoverProcessed in the database
        List<Claims835CrossoverProcessed> claims835CrossoverProcessedList = claims835CrossoverProcessedRepository.findAll();
        assertThat(claims835CrossoverProcessedList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllClaims835CrossoverProcesseds() throws Exception {
        // Initialize the database
        claims835CrossoverProcessedRepository.saveAndFlush(claims835CrossoverProcessed);

        // Get all the claims835CrossoverProcessedList
        restClaims835CrossoverProcessedMockMvc
            .perform(get(ENTITY_API_URL + "?sort=claims835CrossoverProcessedId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].claims835CrossoverProcessedId")
                    .value(hasItem(claims835CrossoverProcessed.getClaims835CrossoverProcessedId().intValue()))
            )
            .andExpect(jsonPath("$.[*].claimCob835MasterId").value(hasItem(DEFAULT_CLAIM_COB_835_MASTER_ID.intValue())))
            .andExpect(jsonPath("$.[*].patientControlNumber").value(hasItem(DEFAULT_PATIENT_CONTROL_NUMBER)))
            .andExpect(jsonPath("$.[*].processedDate").value(hasItem(DEFAULT_PROCESSED_DATE.toString())))
            .andExpect(jsonPath("$.[*].claims835CrossoverProcessed").value(hasItem(DEFAULT_CLAIMS_835_CROSSOVER_PROCESSED.toString())));
    }

    @Test
    @Transactional
    void getClaims835CrossoverProcessed() throws Exception {
        // Initialize the database
        claims835CrossoverProcessedRepository.saveAndFlush(claims835CrossoverProcessed);

        // Get the claims835CrossoverProcessed
        restClaims835CrossoverProcessedMockMvc
            .perform(get(ENTITY_API_URL_ID, claims835CrossoverProcessed.getClaims835CrossoverProcessedId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.claims835CrossoverProcessedId").value(claims835CrossoverProcessed.getClaims835CrossoverProcessedId().intValue())
            )
            .andExpect(jsonPath("$.claimCob835MasterId").value(DEFAULT_CLAIM_COB_835_MASTER_ID.intValue()))
            .andExpect(jsonPath("$.patientControlNumber").value(DEFAULT_PATIENT_CONTROL_NUMBER))
            .andExpect(jsonPath("$.processedDate").value(DEFAULT_PROCESSED_DATE.toString()))
            .andExpect(jsonPath("$.claims835CrossoverProcessed").value(DEFAULT_CLAIMS_835_CROSSOVER_PROCESSED.toString()));
    }

    @Test
    @Transactional
    void getNonExistingClaims835CrossoverProcessed() throws Exception {
        // Get the claims835CrossoverProcessed
        restClaims835CrossoverProcessedMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewClaims835CrossoverProcessed() throws Exception {
        // Initialize the database
        claims835CrossoverProcessedRepository.saveAndFlush(claims835CrossoverProcessed);

        int databaseSizeBeforeUpdate = claims835CrossoverProcessedRepository.findAll().size();

        // Update the claims835CrossoverProcessed
        Claims835CrossoverProcessed updatedClaims835CrossoverProcessed = claims835CrossoverProcessedRepository
            .findById(claims835CrossoverProcessed.getClaims835CrossoverProcessedId())
            .get();
        // Disconnect from session so that the updates on updatedClaims835CrossoverProcessed are not directly saved in db
        em.detach(updatedClaims835CrossoverProcessed);
        updatedClaims835CrossoverProcessed
            .claimCob835MasterId(UPDATED_CLAIM_COB_835_MASTER_ID)
            .patientControlNumber(UPDATED_PATIENT_CONTROL_NUMBER)
            .processedDate(UPDATED_PROCESSED_DATE)
            .claims835CrossoverProcessed(UPDATED_CLAIMS_835_CROSSOVER_PROCESSED);
        Claims835CrossoverProcessedDTO claims835CrossoverProcessedDTO = claims835CrossoverProcessedMapper.toDto(
            updatedClaims835CrossoverProcessed
        );

        restClaims835CrossoverProcessedMockMvc
            .perform(
                put(ENTITY_API_URL_ID, claims835CrossoverProcessedDTO.getClaims835CrossoverProcessedId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claims835CrossoverProcessedDTO))
            )
            .andExpect(status().isOk());

        // Validate the Claims835CrossoverProcessed in the database
        List<Claims835CrossoverProcessed> claims835CrossoverProcessedList = claims835CrossoverProcessedRepository.findAll();
        assertThat(claims835CrossoverProcessedList).hasSize(databaseSizeBeforeUpdate);
        Claims835CrossoverProcessed testClaims835CrossoverProcessed = claims835CrossoverProcessedList.get(
            claims835CrossoverProcessedList.size() - 1
        );
        assertThat(testClaims835CrossoverProcessed.getClaimCob835MasterId()).isEqualTo(UPDATED_CLAIM_COB_835_MASTER_ID);
        assertThat(testClaims835CrossoverProcessed.getPatientControlNumber()).isEqualTo(UPDATED_PATIENT_CONTROL_NUMBER);
        assertThat(testClaims835CrossoverProcessed.getProcessedDate()).isEqualTo(UPDATED_PROCESSED_DATE);
        assertThat(testClaims835CrossoverProcessed.getClaims835CrossoverProcessed()).isEqualTo(UPDATED_CLAIMS_835_CROSSOVER_PROCESSED);
    }

    @Test
    @Transactional
    void putNonExistingClaims835CrossoverProcessed() throws Exception {
        int databaseSizeBeforeUpdate = claims835CrossoverProcessedRepository.findAll().size();
        claims835CrossoverProcessed.setClaims835CrossoverProcessedId(count.incrementAndGet());

        // Create the Claims835CrossoverProcessed
        Claims835CrossoverProcessedDTO claims835CrossoverProcessedDTO = claims835CrossoverProcessedMapper.toDto(
            claims835CrossoverProcessed
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClaims835CrossoverProcessedMockMvc
            .perform(
                put(ENTITY_API_URL_ID, claims835CrossoverProcessedDTO.getClaims835CrossoverProcessedId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claims835CrossoverProcessedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Claims835CrossoverProcessed in the database
        List<Claims835CrossoverProcessed> claims835CrossoverProcessedList = claims835CrossoverProcessedRepository.findAll();
        assertThat(claims835CrossoverProcessedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchClaims835CrossoverProcessed() throws Exception {
        int databaseSizeBeforeUpdate = claims835CrossoverProcessedRepository.findAll().size();
        claims835CrossoverProcessed.setClaims835CrossoverProcessedId(count.incrementAndGet());

        // Create the Claims835CrossoverProcessed
        Claims835CrossoverProcessedDTO claims835CrossoverProcessedDTO = claims835CrossoverProcessedMapper.toDto(
            claims835CrossoverProcessed
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaims835CrossoverProcessedMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claims835CrossoverProcessedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Claims835CrossoverProcessed in the database
        List<Claims835CrossoverProcessed> claims835CrossoverProcessedList = claims835CrossoverProcessedRepository.findAll();
        assertThat(claims835CrossoverProcessedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamClaims835CrossoverProcessed() throws Exception {
        int databaseSizeBeforeUpdate = claims835CrossoverProcessedRepository.findAll().size();
        claims835CrossoverProcessed.setClaims835CrossoverProcessedId(count.incrementAndGet());

        // Create the Claims835CrossoverProcessed
        Claims835CrossoverProcessedDTO claims835CrossoverProcessedDTO = claims835CrossoverProcessedMapper.toDto(
            claims835CrossoverProcessed
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaims835CrossoverProcessedMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claims835CrossoverProcessedDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Claims835CrossoverProcessed in the database
        List<Claims835CrossoverProcessed> claims835CrossoverProcessedList = claims835CrossoverProcessedRepository.findAll();
        assertThat(claims835CrossoverProcessedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateClaims835CrossoverProcessedWithPatch() throws Exception {
        // Initialize the database
        claims835CrossoverProcessedRepository.saveAndFlush(claims835CrossoverProcessed);

        int databaseSizeBeforeUpdate = claims835CrossoverProcessedRepository.findAll().size();

        // Update the claims835CrossoverProcessed using partial update
        Claims835CrossoverProcessed partialUpdatedClaims835CrossoverProcessed = new Claims835CrossoverProcessed();
        partialUpdatedClaims835CrossoverProcessed.setClaims835CrossoverProcessedId(
            claims835CrossoverProcessed.getClaims835CrossoverProcessedId()
        );

        partialUpdatedClaims835CrossoverProcessed.claimCob835MasterId(UPDATED_CLAIM_COB_835_MASTER_ID);

        restClaims835CrossoverProcessedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClaims835CrossoverProcessed.getClaims835CrossoverProcessedId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClaims835CrossoverProcessed))
            )
            .andExpect(status().isOk());

        // Validate the Claims835CrossoverProcessed in the database
        List<Claims835CrossoverProcessed> claims835CrossoverProcessedList = claims835CrossoverProcessedRepository.findAll();
        assertThat(claims835CrossoverProcessedList).hasSize(databaseSizeBeforeUpdate);
        Claims835CrossoverProcessed testClaims835CrossoverProcessed = claims835CrossoverProcessedList.get(
            claims835CrossoverProcessedList.size() - 1
        );
        assertThat(testClaims835CrossoverProcessed.getClaimCob835MasterId()).isEqualTo(UPDATED_CLAIM_COB_835_MASTER_ID);
        assertThat(testClaims835CrossoverProcessed.getPatientControlNumber()).isEqualTo(DEFAULT_PATIENT_CONTROL_NUMBER);
        assertThat(testClaims835CrossoverProcessed.getProcessedDate()).isEqualTo(DEFAULT_PROCESSED_DATE);
        assertThat(testClaims835CrossoverProcessed.getClaims835CrossoverProcessed()).isEqualTo(DEFAULT_CLAIMS_835_CROSSOVER_PROCESSED);
    }

    @Test
    @Transactional
    void fullUpdateClaims835CrossoverProcessedWithPatch() throws Exception {
        // Initialize the database
        claims835CrossoverProcessedRepository.saveAndFlush(claims835CrossoverProcessed);

        int databaseSizeBeforeUpdate = claims835CrossoverProcessedRepository.findAll().size();

        // Update the claims835CrossoverProcessed using partial update
        Claims835CrossoverProcessed partialUpdatedClaims835CrossoverProcessed = new Claims835CrossoverProcessed();
        partialUpdatedClaims835CrossoverProcessed.setClaims835CrossoverProcessedId(
            claims835CrossoverProcessed.getClaims835CrossoverProcessedId()
        );

        partialUpdatedClaims835CrossoverProcessed
            .claimCob835MasterId(UPDATED_CLAIM_COB_835_MASTER_ID)
            .patientControlNumber(UPDATED_PATIENT_CONTROL_NUMBER)
            .processedDate(UPDATED_PROCESSED_DATE)
            .claims835CrossoverProcessed(UPDATED_CLAIMS_835_CROSSOVER_PROCESSED);

        restClaims835CrossoverProcessedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClaims835CrossoverProcessed.getClaims835CrossoverProcessedId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClaims835CrossoverProcessed))
            )
            .andExpect(status().isOk());

        // Validate the Claims835CrossoverProcessed in the database
        List<Claims835CrossoverProcessed> claims835CrossoverProcessedList = claims835CrossoverProcessedRepository.findAll();
        assertThat(claims835CrossoverProcessedList).hasSize(databaseSizeBeforeUpdate);
        Claims835CrossoverProcessed testClaims835CrossoverProcessed = claims835CrossoverProcessedList.get(
            claims835CrossoverProcessedList.size() - 1
        );
        assertThat(testClaims835CrossoverProcessed.getClaimCob835MasterId()).isEqualTo(UPDATED_CLAIM_COB_835_MASTER_ID);
        assertThat(testClaims835CrossoverProcessed.getPatientControlNumber()).isEqualTo(UPDATED_PATIENT_CONTROL_NUMBER);
        assertThat(testClaims835CrossoverProcessed.getProcessedDate()).isEqualTo(UPDATED_PROCESSED_DATE);
        assertThat(testClaims835CrossoverProcessed.getClaims835CrossoverProcessed()).isEqualTo(UPDATED_CLAIMS_835_CROSSOVER_PROCESSED);
    }

    @Test
    @Transactional
    void patchNonExistingClaims835CrossoverProcessed() throws Exception {
        int databaseSizeBeforeUpdate = claims835CrossoverProcessedRepository.findAll().size();
        claims835CrossoverProcessed.setClaims835CrossoverProcessedId(count.incrementAndGet());

        // Create the Claims835CrossoverProcessed
        Claims835CrossoverProcessedDTO claims835CrossoverProcessedDTO = claims835CrossoverProcessedMapper.toDto(
            claims835CrossoverProcessed
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClaims835CrossoverProcessedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, claims835CrossoverProcessedDTO.getClaims835CrossoverProcessedId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(claims835CrossoverProcessedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Claims835CrossoverProcessed in the database
        List<Claims835CrossoverProcessed> claims835CrossoverProcessedList = claims835CrossoverProcessedRepository.findAll();
        assertThat(claims835CrossoverProcessedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchClaims835CrossoverProcessed() throws Exception {
        int databaseSizeBeforeUpdate = claims835CrossoverProcessedRepository.findAll().size();
        claims835CrossoverProcessed.setClaims835CrossoverProcessedId(count.incrementAndGet());

        // Create the Claims835CrossoverProcessed
        Claims835CrossoverProcessedDTO claims835CrossoverProcessedDTO = claims835CrossoverProcessedMapper.toDto(
            claims835CrossoverProcessed
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaims835CrossoverProcessedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(claims835CrossoverProcessedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Claims835CrossoverProcessed in the database
        List<Claims835CrossoverProcessed> claims835CrossoverProcessedList = claims835CrossoverProcessedRepository.findAll();
        assertThat(claims835CrossoverProcessedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamClaims835CrossoverProcessed() throws Exception {
        int databaseSizeBeforeUpdate = claims835CrossoverProcessedRepository.findAll().size();
        claims835CrossoverProcessed.setClaims835CrossoverProcessedId(count.incrementAndGet());

        // Create the Claims835CrossoverProcessed
        Claims835CrossoverProcessedDTO claims835CrossoverProcessedDTO = claims835CrossoverProcessedMapper.toDto(
            claims835CrossoverProcessed
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaims835CrossoverProcessedMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(claims835CrossoverProcessedDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Claims835CrossoverProcessed in the database
        List<Claims835CrossoverProcessed> claims835CrossoverProcessedList = claims835CrossoverProcessedRepository.findAll();
        assertThat(claims835CrossoverProcessedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteClaims835CrossoverProcessed() throws Exception {
        // Initialize the database
        claims835CrossoverProcessedRepository.saveAndFlush(claims835CrossoverProcessed);

        int databaseSizeBeforeDelete = claims835CrossoverProcessedRepository.findAll().size();

        // Delete the claims835CrossoverProcessed
        restClaims835CrossoverProcessedMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, claims835CrossoverProcessed.getClaims835CrossoverProcessedId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Claims835CrossoverProcessed> claims835CrossoverProcessedList = claims835CrossoverProcessedRepository.findAll();
        assertThat(claims835CrossoverProcessedList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
