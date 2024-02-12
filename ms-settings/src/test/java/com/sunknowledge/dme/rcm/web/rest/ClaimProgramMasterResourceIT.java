package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ClaimProgramMaster;
import com.sunknowledge.dme.rcm.repository.ClaimProgramMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.ClaimProgramMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.ClaimProgramMasterMapper;
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
 * Integration tests for the {@link ClaimProgramMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ClaimProgramMasterResourceIT {

    private static final String DEFAULT_CLAIM_PROGRAM_MASTER_KEY = "AAAAAAAAAA";
    private static final String UPDATED_CLAIM_PROGRAM_MASTER_KEY = "BBBBBBBBBB";

    private static final UUID DEFAULT_CLAIM_PROGRAM_MASTER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_CLAIM_PROGRAM_MASTER_UUID = UUID.randomUUID();

    private static final String DEFAULT_CLAIM_PROGRAM_MASTER_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_CLAIM_PROGRAM_MASTER_VALUE = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/claim-program-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{claimProgramMasterId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ClaimProgramMasterRepository claimProgramMasterRepository;

    @Autowired
    private ClaimProgramMasterMapper claimProgramMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restClaimProgramMasterMockMvc;

    private ClaimProgramMaster claimProgramMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClaimProgramMaster createEntity(EntityManager em) {
        ClaimProgramMaster claimProgramMaster = new ClaimProgramMaster()
            .claimProgramMasterKey(DEFAULT_CLAIM_PROGRAM_MASTER_KEY)
            .claimProgramMasterUuid(DEFAULT_CLAIM_PROGRAM_MASTER_UUID)
            .claimProgramMasterValue(DEFAULT_CLAIM_PROGRAM_MASTER_VALUE)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .status(DEFAULT_STATUS)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE);
        return claimProgramMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClaimProgramMaster createUpdatedEntity(EntityManager em) {
        ClaimProgramMaster claimProgramMaster = new ClaimProgramMaster()
            .claimProgramMasterKey(UPDATED_CLAIM_PROGRAM_MASTER_KEY)
            .claimProgramMasterUuid(UPDATED_CLAIM_PROGRAM_MASTER_UUID)
            .claimProgramMasterValue(UPDATED_CLAIM_PROGRAM_MASTER_VALUE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .status(UPDATED_STATUS)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE);
        return claimProgramMaster;
    }

    @BeforeEach
    public void initTest() {
        claimProgramMaster = createEntity(em);
    }

    @Test
    @Transactional
    void createClaimProgramMaster() throws Exception {
        int databaseSizeBeforeCreate = claimProgramMasterRepository.findAll().size();
        // Create the ClaimProgramMaster
        ClaimProgramMasterDTO claimProgramMasterDTO = claimProgramMasterMapper.toDto(claimProgramMaster);
        restClaimProgramMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimProgramMasterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ClaimProgramMaster in the database
        List<ClaimProgramMaster> claimProgramMasterList = claimProgramMasterRepository.findAll();
        assertThat(claimProgramMasterList).hasSize(databaseSizeBeforeCreate + 1);
        ClaimProgramMaster testClaimProgramMaster = claimProgramMasterList.get(claimProgramMasterList.size() - 1);
        assertThat(testClaimProgramMaster.getClaimProgramMasterKey()).isEqualTo(DEFAULT_CLAIM_PROGRAM_MASTER_KEY);
        assertThat(testClaimProgramMaster.getClaimProgramMasterUuid()).isEqualTo(DEFAULT_CLAIM_PROGRAM_MASTER_UUID);
        assertThat(testClaimProgramMaster.getClaimProgramMasterValue()).isEqualTo(DEFAULT_CLAIM_PROGRAM_MASTER_VALUE);
        assertThat(testClaimProgramMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testClaimProgramMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testClaimProgramMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testClaimProgramMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testClaimProgramMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testClaimProgramMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testClaimProgramMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
    }

    @Test
    @Transactional
    void createClaimProgramMasterWithExistingId() throws Exception {
        // Create the ClaimProgramMaster with an existing ID
        claimProgramMaster.setClaimProgramMasterId(1L);
        ClaimProgramMasterDTO claimProgramMasterDTO = claimProgramMasterMapper.toDto(claimProgramMaster);

        int databaseSizeBeforeCreate = claimProgramMasterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restClaimProgramMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimProgramMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimProgramMaster in the database
        List<ClaimProgramMaster> claimProgramMasterList = claimProgramMasterRepository.findAll();
        assertThat(claimProgramMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllClaimProgramMasters() throws Exception {
        // Initialize the database
        claimProgramMasterRepository.saveAndFlush(claimProgramMaster);

        // Get all the claimProgramMasterList
        restClaimProgramMasterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=claimProgramMasterId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].claimProgramMasterId").value(hasItem(claimProgramMaster.getClaimProgramMasterId().intValue())))
            .andExpect(jsonPath("$.[*].claimProgramMasterKey").value(hasItem(DEFAULT_CLAIM_PROGRAM_MASTER_KEY)))
            .andExpect(jsonPath("$.[*].claimProgramMasterUuid").value(hasItem(DEFAULT_CLAIM_PROGRAM_MASTER_UUID.toString())))
            .andExpect(jsonPath("$.[*].claimProgramMasterValue").value(hasItem(DEFAULT_CLAIM_PROGRAM_MASTER_VALUE)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())));
    }

    @Test
    @Transactional
    void getClaimProgramMaster() throws Exception {
        // Initialize the database
        claimProgramMasterRepository.saveAndFlush(claimProgramMaster);

        // Get the claimProgramMaster
        restClaimProgramMasterMockMvc
            .perform(get(ENTITY_API_URL_ID, claimProgramMaster.getClaimProgramMasterId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.claimProgramMasterId").value(claimProgramMaster.getClaimProgramMasterId().intValue()))
            .andExpect(jsonPath("$.claimProgramMasterKey").value(DEFAULT_CLAIM_PROGRAM_MASTER_KEY))
            .andExpect(jsonPath("$.claimProgramMasterUuid").value(DEFAULT_CLAIM_PROGRAM_MASTER_UUID.toString()))
            .andExpect(jsonPath("$.claimProgramMasterValue").value(DEFAULT_CLAIM_PROGRAM_MASTER_VALUE))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingClaimProgramMaster() throws Exception {
        // Get the claimProgramMaster
        restClaimProgramMasterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingClaimProgramMaster() throws Exception {
        // Initialize the database
        claimProgramMasterRepository.saveAndFlush(claimProgramMaster);

        int databaseSizeBeforeUpdate = claimProgramMasterRepository.findAll().size();

        // Update the claimProgramMaster
        ClaimProgramMaster updatedClaimProgramMaster = claimProgramMasterRepository
            .findById(claimProgramMaster.getClaimProgramMasterId())
            .get();
        // Disconnect from session so that the updates on updatedClaimProgramMaster are not directly saved in db
        em.detach(updatedClaimProgramMaster);
        updatedClaimProgramMaster
            .claimProgramMasterKey(UPDATED_CLAIM_PROGRAM_MASTER_KEY)
            .claimProgramMasterUuid(UPDATED_CLAIM_PROGRAM_MASTER_UUID)
            .claimProgramMasterValue(UPDATED_CLAIM_PROGRAM_MASTER_VALUE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .status(UPDATED_STATUS)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE);
        ClaimProgramMasterDTO claimProgramMasterDTO = claimProgramMasterMapper.toDto(updatedClaimProgramMaster);

        restClaimProgramMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, claimProgramMasterDTO.getClaimProgramMasterId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimProgramMasterDTO))
            )
            .andExpect(status().isOk());

        // Validate the ClaimProgramMaster in the database
        List<ClaimProgramMaster> claimProgramMasterList = claimProgramMasterRepository.findAll();
        assertThat(claimProgramMasterList).hasSize(databaseSizeBeforeUpdate);
        ClaimProgramMaster testClaimProgramMaster = claimProgramMasterList.get(claimProgramMasterList.size() - 1);
        assertThat(testClaimProgramMaster.getClaimProgramMasterKey()).isEqualTo(UPDATED_CLAIM_PROGRAM_MASTER_KEY);
        assertThat(testClaimProgramMaster.getClaimProgramMasterUuid()).isEqualTo(UPDATED_CLAIM_PROGRAM_MASTER_UUID);
        assertThat(testClaimProgramMaster.getClaimProgramMasterValue()).isEqualTo(UPDATED_CLAIM_PROGRAM_MASTER_VALUE);
        assertThat(testClaimProgramMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testClaimProgramMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testClaimProgramMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testClaimProgramMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testClaimProgramMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testClaimProgramMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testClaimProgramMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
    }

    @Test
    @Transactional
    void putNonExistingClaimProgramMaster() throws Exception {
        int databaseSizeBeforeUpdate = claimProgramMasterRepository.findAll().size();
        claimProgramMaster.setClaimProgramMasterId(count.incrementAndGet());

        // Create the ClaimProgramMaster
        ClaimProgramMasterDTO claimProgramMasterDTO = claimProgramMasterMapper.toDto(claimProgramMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClaimProgramMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, claimProgramMasterDTO.getClaimProgramMasterId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimProgramMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimProgramMaster in the database
        List<ClaimProgramMaster> claimProgramMasterList = claimProgramMasterRepository.findAll();
        assertThat(claimProgramMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchClaimProgramMaster() throws Exception {
        int databaseSizeBeforeUpdate = claimProgramMasterRepository.findAll().size();
        claimProgramMaster.setClaimProgramMasterId(count.incrementAndGet());

        // Create the ClaimProgramMaster
        ClaimProgramMasterDTO claimProgramMasterDTO = claimProgramMasterMapper.toDto(claimProgramMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaimProgramMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimProgramMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimProgramMaster in the database
        List<ClaimProgramMaster> claimProgramMasterList = claimProgramMasterRepository.findAll();
        assertThat(claimProgramMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamClaimProgramMaster() throws Exception {
        int databaseSizeBeforeUpdate = claimProgramMasterRepository.findAll().size();
        claimProgramMaster.setClaimProgramMasterId(count.incrementAndGet());

        // Create the ClaimProgramMaster
        ClaimProgramMasterDTO claimProgramMasterDTO = claimProgramMasterMapper.toDto(claimProgramMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaimProgramMasterMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimProgramMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ClaimProgramMaster in the database
        List<ClaimProgramMaster> claimProgramMasterList = claimProgramMasterRepository.findAll();
        assertThat(claimProgramMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateClaimProgramMasterWithPatch() throws Exception {
        // Initialize the database
        claimProgramMasterRepository.saveAndFlush(claimProgramMaster);

        int databaseSizeBeforeUpdate = claimProgramMasterRepository.findAll().size();

        // Update the claimProgramMaster using partial update
        ClaimProgramMaster partialUpdatedClaimProgramMaster = new ClaimProgramMaster();
        partialUpdatedClaimProgramMaster.setClaimProgramMasterId(claimProgramMaster.getClaimProgramMasterId());

        partialUpdatedClaimProgramMaster
            .claimProgramMasterKey(UPDATED_CLAIM_PROGRAM_MASTER_KEY)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .status(UPDATED_STATUS)
            .updatedDate(UPDATED_UPDATED_DATE);

        restClaimProgramMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClaimProgramMaster.getClaimProgramMasterId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClaimProgramMaster))
            )
            .andExpect(status().isOk());

        // Validate the ClaimProgramMaster in the database
        List<ClaimProgramMaster> claimProgramMasterList = claimProgramMasterRepository.findAll();
        assertThat(claimProgramMasterList).hasSize(databaseSizeBeforeUpdate);
        ClaimProgramMaster testClaimProgramMaster = claimProgramMasterList.get(claimProgramMasterList.size() - 1);
        assertThat(testClaimProgramMaster.getClaimProgramMasterKey()).isEqualTo(UPDATED_CLAIM_PROGRAM_MASTER_KEY);
        assertThat(testClaimProgramMaster.getClaimProgramMasterUuid()).isEqualTo(DEFAULT_CLAIM_PROGRAM_MASTER_UUID);
        assertThat(testClaimProgramMaster.getClaimProgramMasterValue()).isEqualTo(DEFAULT_CLAIM_PROGRAM_MASTER_VALUE);
        assertThat(testClaimProgramMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testClaimProgramMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testClaimProgramMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testClaimProgramMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testClaimProgramMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testClaimProgramMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testClaimProgramMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
    }

    @Test
    @Transactional
    void fullUpdateClaimProgramMasterWithPatch() throws Exception {
        // Initialize the database
        claimProgramMasterRepository.saveAndFlush(claimProgramMaster);

        int databaseSizeBeforeUpdate = claimProgramMasterRepository.findAll().size();

        // Update the claimProgramMaster using partial update
        ClaimProgramMaster partialUpdatedClaimProgramMaster = new ClaimProgramMaster();
        partialUpdatedClaimProgramMaster.setClaimProgramMasterId(claimProgramMaster.getClaimProgramMasterId());

        partialUpdatedClaimProgramMaster
            .claimProgramMasterKey(UPDATED_CLAIM_PROGRAM_MASTER_KEY)
            .claimProgramMasterUuid(UPDATED_CLAIM_PROGRAM_MASTER_UUID)
            .claimProgramMasterValue(UPDATED_CLAIM_PROGRAM_MASTER_VALUE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .status(UPDATED_STATUS)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE);

        restClaimProgramMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClaimProgramMaster.getClaimProgramMasterId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClaimProgramMaster))
            )
            .andExpect(status().isOk());

        // Validate the ClaimProgramMaster in the database
        List<ClaimProgramMaster> claimProgramMasterList = claimProgramMasterRepository.findAll();
        assertThat(claimProgramMasterList).hasSize(databaseSizeBeforeUpdate);
        ClaimProgramMaster testClaimProgramMaster = claimProgramMasterList.get(claimProgramMasterList.size() - 1);
        assertThat(testClaimProgramMaster.getClaimProgramMasterKey()).isEqualTo(UPDATED_CLAIM_PROGRAM_MASTER_KEY);
        assertThat(testClaimProgramMaster.getClaimProgramMasterUuid()).isEqualTo(UPDATED_CLAIM_PROGRAM_MASTER_UUID);
        assertThat(testClaimProgramMaster.getClaimProgramMasterValue()).isEqualTo(UPDATED_CLAIM_PROGRAM_MASTER_VALUE);
        assertThat(testClaimProgramMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testClaimProgramMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testClaimProgramMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testClaimProgramMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testClaimProgramMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testClaimProgramMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testClaimProgramMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingClaimProgramMaster() throws Exception {
        int databaseSizeBeforeUpdate = claimProgramMasterRepository.findAll().size();
        claimProgramMaster.setClaimProgramMasterId(count.incrementAndGet());

        // Create the ClaimProgramMaster
        ClaimProgramMasterDTO claimProgramMasterDTO = claimProgramMasterMapper.toDto(claimProgramMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClaimProgramMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, claimProgramMasterDTO.getClaimProgramMasterId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(claimProgramMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimProgramMaster in the database
        List<ClaimProgramMaster> claimProgramMasterList = claimProgramMasterRepository.findAll();
        assertThat(claimProgramMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchClaimProgramMaster() throws Exception {
        int databaseSizeBeforeUpdate = claimProgramMasterRepository.findAll().size();
        claimProgramMaster.setClaimProgramMasterId(count.incrementAndGet());

        // Create the ClaimProgramMaster
        ClaimProgramMasterDTO claimProgramMasterDTO = claimProgramMasterMapper.toDto(claimProgramMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaimProgramMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(claimProgramMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimProgramMaster in the database
        List<ClaimProgramMaster> claimProgramMasterList = claimProgramMasterRepository.findAll();
        assertThat(claimProgramMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamClaimProgramMaster() throws Exception {
        int databaseSizeBeforeUpdate = claimProgramMasterRepository.findAll().size();
        claimProgramMaster.setClaimProgramMasterId(count.incrementAndGet());

        // Create the ClaimProgramMaster
        ClaimProgramMasterDTO claimProgramMasterDTO = claimProgramMasterMapper.toDto(claimProgramMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaimProgramMasterMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(claimProgramMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ClaimProgramMaster in the database
        List<ClaimProgramMaster> claimProgramMasterList = claimProgramMasterRepository.findAll();
        assertThat(claimProgramMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteClaimProgramMaster() throws Exception {
        // Initialize the database
        claimProgramMasterRepository.saveAndFlush(claimProgramMaster);

        int databaseSizeBeforeDelete = claimProgramMasterRepository.findAll().size();

        // Delete the claimProgramMaster
        restClaimProgramMasterMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, claimProgramMaster.getClaimProgramMasterId()).with(csrf()).accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ClaimProgramMaster> claimProgramMasterList = claimProgramMasterRepository.findAll();
        assertThat(claimProgramMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
