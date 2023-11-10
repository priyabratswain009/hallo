package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ClaimFormMaster;
import com.sunknowledge.dme.rcm.repository.ClaimFormMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.ClaimFormMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.ClaimFormMasterMapper;
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
 * Integration tests for the {@link ClaimFormMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ClaimFormMasterResourceIT {

    private static final String DEFAULT_CLAIM_FORM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CLAIM_FORM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final UUID DEFAULT_CLAIM_FORM_MASTER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_CLAIM_FORM_MASTER_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/claim-form-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{claimFormId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ClaimFormMasterRepository claimFormMasterRepository;

    @Autowired
    private ClaimFormMasterMapper claimFormMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restClaimFormMasterMockMvc;

    private ClaimFormMaster claimFormMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClaimFormMaster createEntity(EntityManager em) {
        ClaimFormMaster claimFormMaster = new ClaimFormMaster()
            .claimFormName(DEFAULT_CLAIM_FORM_NAME)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .claimFormMasterUuid(DEFAULT_CLAIM_FORM_MASTER_UUID);
        return claimFormMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClaimFormMaster createUpdatedEntity(EntityManager em) {
        ClaimFormMaster claimFormMaster = new ClaimFormMaster()
            .claimFormName(UPDATED_CLAIM_FORM_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .claimFormMasterUuid(UPDATED_CLAIM_FORM_MASTER_UUID);
        return claimFormMaster;
    }

    @BeforeEach
    public void initTest() {
        claimFormMaster = createEntity(em);
    }

    @Test
    @Transactional
    void createClaimFormMaster() throws Exception {
        int databaseSizeBeforeCreate = claimFormMasterRepository.findAll().size();
        // Create the ClaimFormMaster
        ClaimFormMasterDTO claimFormMasterDTO = claimFormMasterMapper.toDto(claimFormMaster);
        restClaimFormMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimFormMasterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ClaimFormMaster in the database
        List<ClaimFormMaster> claimFormMasterList = claimFormMasterRepository.findAll();
        assertThat(claimFormMasterList).hasSize(databaseSizeBeforeCreate + 1);
        ClaimFormMaster testClaimFormMaster = claimFormMasterList.get(claimFormMasterList.size() - 1);
        assertThat(testClaimFormMaster.getClaimFormName()).isEqualTo(DEFAULT_CLAIM_FORM_NAME);
        assertThat(testClaimFormMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testClaimFormMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testClaimFormMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testClaimFormMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testClaimFormMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testClaimFormMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testClaimFormMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testClaimFormMaster.getClaimFormMasterUuid()).isEqualTo(DEFAULT_CLAIM_FORM_MASTER_UUID);
    }

    @Test
    @Transactional
    void createClaimFormMasterWithExistingId() throws Exception {
        // Create the ClaimFormMaster with an existing ID
        claimFormMaster.setClaimFormId(1L);
        ClaimFormMasterDTO claimFormMasterDTO = claimFormMasterMapper.toDto(claimFormMaster);

        int databaseSizeBeforeCreate = claimFormMasterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restClaimFormMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimFormMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimFormMaster in the database
        List<ClaimFormMaster> claimFormMasterList = claimFormMasterRepository.findAll();
        assertThat(claimFormMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllClaimFormMasters() throws Exception {
        // Initialize the database
        claimFormMasterRepository.saveAndFlush(claimFormMaster);

        // Get all the claimFormMasterList
        restClaimFormMasterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=claimFormId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].claimFormId").value(hasItem(claimFormMaster.getClaimFormId().intValue())))
            .andExpect(jsonPath("$.[*].claimFormName").value(hasItem(DEFAULT_CLAIM_FORM_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].claimFormMasterUuid").value(hasItem(DEFAULT_CLAIM_FORM_MASTER_UUID.toString())));
    }

    @Test
    @Transactional
    void getClaimFormMaster() throws Exception {
        // Initialize the database
        claimFormMasterRepository.saveAndFlush(claimFormMaster);

        // Get the claimFormMaster
        restClaimFormMasterMockMvc
            .perform(get(ENTITY_API_URL_ID, claimFormMaster.getClaimFormId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.claimFormId").value(claimFormMaster.getClaimFormId().intValue()))
            .andExpect(jsonPath("$.claimFormName").value(DEFAULT_CLAIM_FORM_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.claimFormMasterUuid").value(DEFAULT_CLAIM_FORM_MASTER_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingClaimFormMaster() throws Exception {
        // Get the claimFormMaster
        restClaimFormMasterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewClaimFormMaster() throws Exception {
        // Initialize the database
        claimFormMasterRepository.saveAndFlush(claimFormMaster);

        int databaseSizeBeforeUpdate = claimFormMasterRepository.findAll().size();

        // Update the claimFormMaster
        ClaimFormMaster updatedClaimFormMaster = claimFormMasterRepository.findById(claimFormMaster.getClaimFormId()).get();
        // Disconnect from session so that the updates on updatedClaimFormMaster are not directly saved in db
        em.detach(updatedClaimFormMaster);
        updatedClaimFormMaster
            .claimFormName(UPDATED_CLAIM_FORM_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .claimFormMasterUuid(UPDATED_CLAIM_FORM_MASTER_UUID);
        ClaimFormMasterDTO claimFormMasterDTO = claimFormMasterMapper.toDto(updatedClaimFormMaster);

        restClaimFormMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, claimFormMasterDTO.getClaimFormId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimFormMasterDTO))
            )
            .andExpect(status().isOk());

        // Validate the ClaimFormMaster in the database
        List<ClaimFormMaster> claimFormMasterList = claimFormMasterRepository.findAll();
        assertThat(claimFormMasterList).hasSize(databaseSizeBeforeUpdate);
        ClaimFormMaster testClaimFormMaster = claimFormMasterList.get(claimFormMasterList.size() - 1);
        assertThat(testClaimFormMaster.getClaimFormName()).isEqualTo(UPDATED_CLAIM_FORM_NAME);
        assertThat(testClaimFormMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testClaimFormMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testClaimFormMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testClaimFormMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testClaimFormMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testClaimFormMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testClaimFormMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testClaimFormMaster.getClaimFormMasterUuid()).isEqualTo(UPDATED_CLAIM_FORM_MASTER_UUID);
    }

    @Test
    @Transactional
    void putNonExistingClaimFormMaster() throws Exception {
        int databaseSizeBeforeUpdate = claimFormMasterRepository.findAll().size();
        claimFormMaster.setClaimFormId(count.incrementAndGet());

        // Create the ClaimFormMaster
        ClaimFormMasterDTO claimFormMasterDTO = claimFormMasterMapper.toDto(claimFormMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClaimFormMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, claimFormMasterDTO.getClaimFormId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimFormMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimFormMaster in the database
        List<ClaimFormMaster> claimFormMasterList = claimFormMasterRepository.findAll();
        assertThat(claimFormMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchClaimFormMaster() throws Exception {
        int databaseSizeBeforeUpdate = claimFormMasterRepository.findAll().size();
        claimFormMaster.setClaimFormId(count.incrementAndGet());

        // Create the ClaimFormMaster
        ClaimFormMasterDTO claimFormMasterDTO = claimFormMasterMapper.toDto(claimFormMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaimFormMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimFormMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimFormMaster in the database
        List<ClaimFormMaster> claimFormMasterList = claimFormMasterRepository.findAll();
        assertThat(claimFormMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamClaimFormMaster() throws Exception {
        int databaseSizeBeforeUpdate = claimFormMasterRepository.findAll().size();
        claimFormMaster.setClaimFormId(count.incrementAndGet());

        // Create the ClaimFormMaster
        ClaimFormMasterDTO claimFormMasterDTO = claimFormMasterMapper.toDto(claimFormMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaimFormMasterMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimFormMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ClaimFormMaster in the database
        List<ClaimFormMaster> claimFormMasterList = claimFormMasterRepository.findAll();
        assertThat(claimFormMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateClaimFormMasterWithPatch() throws Exception {
        // Initialize the database
        claimFormMasterRepository.saveAndFlush(claimFormMaster);

        int databaseSizeBeforeUpdate = claimFormMasterRepository.findAll().size();

        // Update the claimFormMaster using partial update
        ClaimFormMaster partialUpdatedClaimFormMaster = new ClaimFormMaster();
        partialUpdatedClaimFormMaster.setClaimFormId(claimFormMaster.getClaimFormId());

        partialUpdatedClaimFormMaster
            .claimFormName(UPDATED_CLAIM_FORM_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID);

        restClaimFormMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClaimFormMaster.getClaimFormId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClaimFormMaster))
            )
            .andExpect(status().isOk());

        // Validate the ClaimFormMaster in the database
        List<ClaimFormMaster> claimFormMasterList = claimFormMasterRepository.findAll();
        assertThat(claimFormMasterList).hasSize(databaseSizeBeforeUpdate);
        ClaimFormMaster testClaimFormMaster = claimFormMasterList.get(claimFormMasterList.size() - 1);
        assertThat(testClaimFormMaster.getClaimFormName()).isEqualTo(UPDATED_CLAIM_FORM_NAME);
        assertThat(testClaimFormMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testClaimFormMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testClaimFormMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testClaimFormMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testClaimFormMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testClaimFormMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testClaimFormMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testClaimFormMaster.getClaimFormMasterUuid()).isEqualTo(DEFAULT_CLAIM_FORM_MASTER_UUID);
    }

    @Test
    @Transactional
    void fullUpdateClaimFormMasterWithPatch() throws Exception {
        // Initialize the database
        claimFormMasterRepository.saveAndFlush(claimFormMaster);

        int databaseSizeBeforeUpdate = claimFormMasterRepository.findAll().size();

        // Update the claimFormMaster using partial update
        ClaimFormMaster partialUpdatedClaimFormMaster = new ClaimFormMaster();
        partialUpdatedClaimFormMaster.setClaimFormId(claimFormMaster.getClaimFormId());

        partialUpdatedClaimFormMaster
            .claimFormName(UPDATED_CLAIM_FORM_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .claimFormMasterUuid(UPDATED_CLAIM_FORM_MASTER_UUID);

        restClaimFormMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClaimFormMaster.getClaimFormId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClaimFormMaster))
            )
            .andExpect(status().isOk());

        // Validate the ClaimFormMaster in the database
        List<ClaimFormMaster> claimFormMasterList = claimFormMasterRepository.findAll();
        assertThat(claimFormMasterList).hasSize(databaseSizeBeforeUpdate);
        ClaimFormMaster testClaimFormMaster = claimFormMasterList.get(claimFormMasterList.size() - 1);
        assertThat(testClaimFormMaster.getClaimFormName()).isEqualTo(UPDATED_CLAIM_FORM_NAME);
        assertThat(testClaimFormMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testClaimFormMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testClaimFormMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testClaimFormMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testClaimFormMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testClaimFormMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testClaimFormMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testClaimFormMaster.getClaimFormMasterUuid()).isEqualTo(UPDATED_CLAIM_FORM_MASTER_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingClaimFormMaster() throws Exception {
        int databaseSizeBeforeUpdate = claimFormMasterRepository.findAll().size();
        claimFormMaster.setClaimFormId(count.incrementAndGet());

        // Create the ClaimFormMaster
        ClaimFormMasterDTO claimFormMasterDTO = claimFormMasterMapper.toDto(claimFormMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClaimFormMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, claimFormMasterDTO.getClaimFormId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(claimFormMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimFormMaster in the database
        List<ClaimFormMaster> claimFormMasterList = claimFormMasterRepository.findAll();
        assertThat(claimFormMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchClaimFormMaster() throws Exception {
        int databaseSizeBeforeUpdate = claimFormMasterRepository.findAll().size();
        claimFormMaster.setClaimFormId(count.incrementAndGet());

        // Create the ClaimFormMaster
        ClaimFormMasterDTO claimFormMasterDTO = claimFormMasterMapper.toDto(claimFormMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaimFormMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(claimFormMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimFormMaster in the database
        List<ClaimFormMaster> claimFormMasterList = claimFormMasterRepository.findAll();
        assertThat(claimFormMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamClaimFormMaster() throws Exception {
        int databaseSizeBeforeUpdate = claimFormMasterRepository.findAll().size();
        claimFormMaster.setClaimFormId(count.incrementAndGet());

        // Create the ClaimFormMaster
        ClaimFormMasterDTO claimFormMasterDTO = claimFormMasterMapper.toDto(claimFormMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaimFormMasterMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(claimFormMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ClaimFormMaster in the database
        List<ClaimFormMaster> claimFormMasterList = claimFormMasterRepository.findAll();
        assertThat(claimFormMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteClaimFormMaster() throws Exception {
        // Initialize the database
        claimFormMasterRepository.saveAndFlush(claimFormMaster);

        int databaseSizeBeforeDelete = claimFormMasterRepository.findAll().size();

        // Delete the claimFormMaster
        restClaimFormMasterMockMvc
            .perform(delete(ENTITY_API_URL_ID, claimFormMaster.getClaimFormId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ClaimFormMaster> claimFormMasterList = claimFormMasterRepository.findAll();
        assertThat(claimFormMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
