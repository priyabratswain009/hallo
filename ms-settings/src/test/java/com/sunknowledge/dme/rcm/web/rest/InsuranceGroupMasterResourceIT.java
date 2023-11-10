package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.InsuranceGroupMaster;
import com.sunknowledge.dme.rcm.repository.InsuranceGroupMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.InsuranceGroupMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.InsuranceGroupMasterMapper;
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
 * Integration tests for the {@link InsuranceGroupMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class InsuranceGroupMasterResourceIT {

    private static final String DEFAULT_INSURANCE_GROUP_MASTER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INSURANCE_GROUP_MASTER_NAME = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_INSURANCE_GROUP_MASTER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_INSURANCE_GROUP_MASTER_UUID = UUID.randomUUID();

    private static final String DEFAULT_INSURANCE_GROUP_MASTER_NO = "AAAAAAAAAA";
    private static final String UPDATED_INSURANCE_GROUP_MASTER_NO = "BBBBBBBBBB";

    private static final String DEFAULT_INSURANCE_GROUP_MASTER_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_INSURANCE_GROUP_MASTER_DESCRIPTION = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/insurance-group-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{insuranceGroupMasterId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private InsuranceGroupMasterRepository insuranceGroupMasterRepository;

    @Autowired
    private InsuranceGroupMasterMapper insuranceGroupMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInsuranceGroupMasterMockMvc;

    private InsuranceGroupMaster insuranceGroupMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InsuranceGroupMaster createEntity(EntityManager em) {
        InsuranceGroupMaster insuranceGroupMaster = new InsuranceGroupMaster()
            .insuranceGroupMasterName(DEFAULT_INSURANCE_GROUP_MASTER_NAME)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .insuranceGroupMasterUuid(DEFAULT_INSURANCE_GROUP_MASTER_UUID)
            .insuranceGroupMasterNo(DEFAULT_INSURANCE_GROUP_MASTER_NO)
            .insuranceGroupMasterDescription(DEFAULT_INSURANCE_GROUP_MASTER_DESCRIPTION);
        return insuranceGroupMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InsuranceGroupMaster createUpdatedEntity(EntityManager em) {
        InsuranceGroupMaster insuranceGroupMaster = new InsuranceGroupMaster()
            .insuranceGroupMasterName(UPDATED_INSURANCE_GROUP_MASTER_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .insuranceGroupMasterUuid(UPDATED_INSURANCE_GROUP_MASTER_UUID)
            .insuranceGroupMasterNo(UPDATED_INSURANCE_GROUP_MASTER_NO)
            .insuranceGroupMasterDescription(UPDATED_INSURANCE_GROUP_MASTER_DESCRIPTION);
        return insuranceGroupMaster;
    }

    @BeforeEach
    public void initTest() {
        insuranceGroupMaster = createEntity(em);
    }

    @Test
    @Transactional
    void createInsuranceGroupMaster() throws Exception {
        int databaseSizeBeforeCreate = insuranceGroupMasterRepository.findAll().size();
        // Create the InsuranceGroupMaster
        InsuranceGroupMasterDTO insuranceGroupMasterDTO = insuranceGroupMasterMapper.toDto(insuranceGroupMaster);
        restInsuranceGroupMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(insuranceGroupMasterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the InsuranceGroupMaster in the database
        List<InsuranceGroupMaster> insuranceGroupMasterList = insuranceGroupMasterRepository.findAll();
        assertThat(insuranceGroupMasterList).hasSize(databaseSizeBeforeCreate + 1);
        InsuranceGroupMaster testInsuranceGroupMaster = insuranceGroupMasterList.get(insuranceGroupMasterList.size() - 1);
        assertThat(testInsuranceGroupMaster.getInsuranceGroupMasterName()).isEqualTo(DEFAULT_INSURANCE_GROUP_MASTER_NAME);
        assertThat(testInsuranceGroupMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testInsuranceGroupMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testInsuranceGroupMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testInsuranceGroupMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testInsuranceGroupMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testInsuranceGroupMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testInsuranceGroupMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testInsuranceGroupMaster.getInsuranceGroupMasterUuid()).isEqualTo(DEFAULT_INSURANCE_GROUP_MASTER_UUID);
        assertThat(testInsuranceGroupMaster.getInsuranceGroupMasterNo()).isEqualTo(DEFAULT_INSURANCE_GROUP_MASTER_NO);
        assertThat(testInsuranceGroupMaster.getInsuranceGroupMasterDescription()).isEqualTo(DEFAULT_INSURANCE_GROUP_MASTER_DESCRIPTION);
    }

    @Test
    @Transactional
    void createInsuranceGroupMasterWithExistingId() throws Exception {
        // Create the InsuranceGroupMaster with an existing ID
        insuranceGroupMaster.setInsuranceGroupMasterId(1L);
        InsuranceGroupMasterDTO insuranceGroupMasterDTO = insuranceGroupMasterMapper.toDto(insuranceGroupMaster);

        int databaseSizeBeforeCreate = insuranceGroupMasterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restInsuranceGroupMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(insuranceGroupMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InsuranceGroupMaster in the database
        List<InsuranceGroupMaster> insuranceGroupMasterList = insuranceGroupMasterRepository.findAll();
        assertThat(insuranceGroupMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllInsuranceGroupMasters() throws Exception {
        // Initialize the database
        insuranceGroupMasterRepository.saveAndFlush(insuranceGroupMaster);

        // Get all the insuranceGroupMasterList
        restInsuranceGroupMasterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=insuranceGroupMasterId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].insuranceGroupMasterId").value(hasItem(insuranceGroupMaster.getInsuranceGroupMasterId().intValue())))
            .andExpect(jsonPath("$.[*].insuranceGroupMasterName").value(hasItem(DEFAULT_INSURANCE_GROUP_MASTER_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].insuranceGroupMasterUuid").value(hasItem(DEFAULT_INSURANCE_GROUP_MASTER_UUID.toString())))
            .andExpect(jsonPath("$.[*].insuranceGroupMasterNo").value(hasItem(DEFAULT_INSURANCE_GROUP_MASTER_NO)))
            .andExpect(jsonPath("$.[*].insuranceGroupMasterDescription").value(hasItem(DEFAULT_INSURANCE_GROUP_MASTER_DESCRIPTION)));
    }

    @Test
    @Transactional
    void getInsuranceGroupMaster() throws Exception {
        // Initialize the database
        insuranceGroupMasterRepository.saveAndFlush(insuranceGroupMaster);

        // Get the insuranceGroupMaster
        restInsuranceGroupMasterMockMvc
            .perform(get(ENTITY_API_URL_ID, insuranceGroupMaster.getInsuranceGroupMasterId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.insuranceGroupMasterId").value(insuranceGroupMaster.getInsuranceGroupMasterId().intValue()))
            .andExpect(jsonPath("$.insuranceGroupMasterName").value(DEFAULT_INSURANCE_GROUP_MASTER_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.insuranceGroupMasterUuid").value(DEFAULT_INSURANCE_GROUP_MASTER_UUID.toString()))
            .andExpect(jsonPath("$.insuranceGroupMasterNo").value(DEFAULT_INSURANCE_GROUP_MASTER_NO))
            .andExpect(jsonPath("$.insuranceGroupMasterDescription").value(DEFAULT_INSURANCE_GROUP_MASTER_DESCRIPTION));
    }

    @Test
    @Transactional
    void getNonExistingInsuranceGroupMaster() throws Exception {
        // Get the insuranceGroupMaster
        restInsuranceGroupMasterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewInsuranceGroupMaster() throws Exception {
        // Initialize the database
        insuranceGroupMasterRepository.saveAndFlush(insuranceGroupMaster);

        int databaseSizeBeforeUpdate = insuranceGroupMasterRepository.findAll().size();

        // Update the insuranceGroupMaster
        InsuranceGroupMaster updatedInsuranceGroupMaster = insuranceGroupMasterRepository
            .findById(insuranceGroupMaster.getInsuranceGroupMasterId())
            .get();
        // Disconnect from session so that the updates on updatedInsuranceGroupMaster are not directly saved in db
        em.detach(updatedInsuranceGroupMaster);
        updatedInsuranceGroupMaster
            .insuranceGroupMasterName(UPDATED_INSURANCE_GROUP_MASTER_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .insuranceGroupMasterUuid(UPDATED_INSURANCE_GROUP_MASTER_UUID)
            .insuranceGroupMasterNo(UPDATED_INSURANCE_GROUP_MASTER_NO)
            .insuranceGroupMasterDescription(UPDATED_INSURANCE_GROUP_MASTER_DESCRIPTION);
        InsuranceGroupMasterDTO insuranceGroupMasterDTO = insuranceGroupMasterMapper.toDto(updatedInsuranceGroupMaster);

        restInsuranceGroupMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, insuranceGroupMasterDTO.getInsuranceGroupMasterId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(insuranceGroupMasterDTO))
            )
            .andExpect(status().isOk());

        // Validate the InsuranceGroupMaster in the database
        List<InsuranceGroupMaster> insuranceGroupMasterList = insuranceGroupMasterRepository.findAll();
        assertThat(insuranceGroupMasterList).hasSize(databaseSizeBeforeUpdate);
        InsuranceGroupMaster testInsuranceGroupMaster = insuranceGroupMasterList.get(insuranceGroupMasterList.size() - 1);
        assertThat(testInsuranceGroupMaster.getInsuranceGroupMasterName()).isEqualTo(UPDATED_INSURANCE_GROUP_MASTER_NAME);
        assertThat(testInsuranceGroupMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testInsuranceGroupMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testInsuranceGroupMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testInsuranceGroupMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testInsuranceGroupMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testInsuranceGroupMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testInsuranceGroupMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testInsuranceGroupMaster.getInsuranceGroupMasterUuid()).isEqualTo(UPDATED_INSURANCE_GROUP_MASTER_UUID);
        assertThat(testInsuranceGroupMaster.getInsuranceGroupMasterNo()).isEqualTo(UPDATED_INSURANCE_GROUP_MASTER_NO);
        assertThat(testInsuranceGroupMaster.getInsuranceGroupMasterDescription()).isEqualTo(UPDATED_INSURANCE_GROUP_MASTER_DESCRIPTION);
    }

    @Test
    @Transactional
    void putNonExistingInsuranceGroupMaster() throws Exception {
        int databaseSizeBeforeUpdate = insuranceGroupMasterRepository.findAll().size();
        insuranceGroupMaster.setInsuranceGroupMasterId(count.incrementAndGet());

        // Create the InsuranceGroupMaster
        InsuranceGroupMasterDTO insuranceGroupMasterDTO = insuranceGroupMasterMapper.toDto(insuranceGroupMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInsuranceGroupMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, insuranceGroupMasterDTO.getInsuranceGroupMasterId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(insuranceGroupMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InsuranceGroupMaster in the database
        List<InsuranceGroupMaster> insuranceGroupMasterList = insuranceGroupMasterRepository.findAll();
        assertThat(insuranceGroupMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchInsuranceGroupMaster() throws Exception {
        int databaseSizeBeforeUpdate = insuranceGroupMasterRepository.findAll().size();
        insuranceGroupMaster.setInsuranceGroupMasterId(count.incrementAndGet());

        // Create the InsuranceGroupMaster
        InsuranceGroupMasterDTO insuranceGroupMasterDTO = insuranceGroupMasterMapper.toDto(insuranceGroupMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInsuranceGroupMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(insuranceGroupMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InsuranceGroupMaster in the database
        List<InsuranceGroupMaster> insuranceGroupMasterList = insuranceGroupMasterRepository.findAll();
        assertThat(insuranceGroupMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamInsuranceGroupMaster() throws Exception {
        int databaseSizeBeforeUpdate = insuranceGroupMasterRepository.findAll().size();
        insuranceGroupMaster.setInsuranceGroupMasterId(count.incrementAndGet());

        // Create the InsuranceGroupMaster
        InsuranceGroupMasterDTO insuranceGroupMasterDTO = insuranceGroupMasterMapper.toDto(insuranceGroupMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInsuranceGroupMasterMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(insuranceGroupMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the InsuranceGroupMaster in the database
        List<InsuranceGroupMaster> insuranceGroupMasterList = insuranceGroupMasterRepository.findAll();
        assertThat(insuranceGroupMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateInsuranceGroupMasterWithPatch() throws Exception {
        // Initialize the database
        insuranceGroupMasterRepository.saveAndFlush(insuranceGroupMaster);

        int databaseSizeBeforeUpdate = insuranceGroupMasterRepository.findAll().size();

        // Update the insuranceGroupMaster using partial update
        InsuranceGroupMaster partialUpdatedInsuranceGroupMaster = new InsuranceGroupMaster();
        partialUpdatedInsuranceGroupMaster.setInsuranceGroupMasterId(insuranceGroupMaster.getInsuranceGroupMasterId());

        partialUpdatedInsuranceGroupMaster
            .insuranceGroupMasterName(UPDATED_INSURANCE_GROUP_MASTER_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .insuranceGroupMasterUuid(UPDATED_INSURANCE_GROUP_MASTER_UUID)
            .insuranceGroupMasterDescription(UPDATED_INSURANCE_GROUP_MASTER_DESCRIPTION);

        restInsuranceGroupMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInsuranceGroupMaster.getInsuranceGroupMasterId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInsuranceGroupMaster))
            )
            .andExpect(status().isOk());

        // Validate the InsuranceGroupMaster in the database
        List<InsuranceGroupMaster> insuranceGroupMasterList = insuranceGroupMasterRepository.findAll();
        assertThat(insuranceGroupMasterList).hasSize(databaseSizeBeforeUpdate);
        InsuranceGroupMaster testInsuranceGroupMaster = insuranceGroupMasterList.get(insuranceGroupMasterList.size() - 1);
        assertThat(testInsuranceGroupMaster.getInsuranceGroupMasterName()).isEqualTo(UPDATED_INSURANCE_GROUP_MASTER_NAME);
        assertThat(testInsuranceGroupMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testInsuranceGroupMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testInsuranceGroupMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testInsuranceGroupMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testInsuranceGroupMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testInsuranceGroupMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testInsuranceGroupMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testInsuranceGroupMaster.getInsuranceGroupMasterUuid()).isEqualTo(UPDATED_INSURANCE_GROUP_MASTER_UUID);
        assertThat(testInsuranceGroupMaster.getInsuranceGroupMasterNo()).isEqualTo(DEFAULT_INSURANCE_GROUP_MASTER_NO);
        assertThat(testInsuranceGroupMaster.getInsuranceGroupMasterDescription()).isEqualTo(UPDATED_INSURANCE_GROUP_MASTER_DESCRIPTION);
    }

    @Test
    @Transactional
    void fullUpdateInsuranceGroupMasterWithPatch() throws Exception {
        // Initialize the database
        insuranceGroupMasterRepository.saveAndFlush(insuranceGroupMaster);

        int databaseSizeBeforeUpdate = insuranceGroupMasterRepository.findAll().size();

        // Update the insuranceGroupMaster using partial update
        InsuranceGroupMaster partialUpdatedInsuranceGroupMaster = new InsuranceGroupMaster();
        partialUpdatedInsuranceGroupMaster.setInsuranceGroupMasterId(insuranceGroupMaster.getInsuranceGroupMasterId());

        partialUpdatedInsuranceGroupMaster
            .insuranceGroupMasterName(UPDATED_INSURANCE_GROUP_MASTER_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .insuranceGroupMasterUuid(UPDATED_INSURANCE_GROUP_MASTER_UUID)
            .insuranceGroupMasterNo(UPDATED_INSURANCE_GROUP_MASTER_NO)
            .insuranceGroupMasterDescription(UPDATED_INSURANCE_GROUP_MASTER_DESCRIPTION);

        restInsuranceGroupMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInsuranceGroupMaster.getInsuranceGroupMasterId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInsuranceGroupMaster))
            )
            .andExpect(status().isOk());

        // Validate the InsuranceGroupMaster in the database
        List<InsuranceGroupMaster> insuranceGroupMasterList = insuranceGroupMasterRepository.findAll();
        assertThat(insuranceGroupMasterList).hasSize(databaseSizeBeforeUpdate);
        InsuranceGroupMaster testInsuranceGroupMaster = insuranceGroupMasterList.get(insuranceGroupMasterList.size() - 1);
        assertThat(testInsuranceGroupMaster.getInsuranceGroupMasterName()).isEqualTo(UPDATED_INSURANCE_GROUP_MASTER_NAME);
        assertThat(testInsuranceGroupMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testInsuranceGroupMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testInsuranceGroupMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testInsuranceGroupMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testInsuranceGroupMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testInsuranceGroupMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testInsuranceGroupMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testInsuranceGroupMaster.getInsuranceGroupMasterUuid()).isEqualTo(UPDATED_INSURANCE_GROUP_MASTER_UUID);
        assertThat(testInsuranceGroupMaster.getInsuranceGroupMasterNo()).isEqualTo(UPDATED_INSURANCE_GROUP_MASTER_NO);
        assertThat(testInsuranceGroupMaster.getInsuranceGroupMasterDescription()).isEqualTo(UPDATED_INSURANCE_GROUP_MASTER_DESCRIPTION);
    }

    @Test
    @Transactional
    void patchNonExistingInsuranceGroupMaster() throws Exception {
        int databaseSizeBeforeUpdate = insuranceGroupMasterRepository.findAll().size();
        insuranceGroupMaster.setInsuranceGroupMasterId(count.incrementAndGet());

        // Create the InsuranceGroupMaster
        InsuranceGroupMasterDTO insuranceGroupMasterDTO = insuranceGroupMasterMapper.toDto(insuranceGroupMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInsuranceGroupMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, insuranceGroupMasterDTO.getInsuranceGroupMasterId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(insuranceGroupMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InsuranceGroupMaster in the database
        List<InsuranceGroupMaster> insuranceGroupMasterList = insuranceGroupMasterRepository.findAll();
        assertThat(insuranceGroupMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchInsuranceGroupMaster() throws Exception {
        int databaseSizeBeforeUpdate = insuranceGroupMasterRepository.findAll().size();
        insuranceGroupMaster.setInsuranceGroupMasterId(count.incrementAndGet());

        // Create the InsuranceGroupMaster
        InsuranceGroupMasterDTO insuranceGroupMasterDTO = insuranceGroupMasterMapper.toDto(insuranceGroupMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInsuranceGroupMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(insuranceGroupMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InsuranceGroupMaster in the database
        List<InsuranceGroupMaster> insuranceGroupMasterList = insuranceGroupMasterRepository.findAll();
        assertThat(insuranceGroupMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamInsuranceGroupMaster() throws Exception {
        int databaseSizeBeforeUpdate = insuranceGroupMasterRepository.findAll().size();
        insuranceGroupMaster.setInsuranceGroupMasterId(count.incrementAndGet());

        // Create the InsuranceGroupMaster
        InsuranceGroupMasterDTO insuranceGroupMasterDTO = insuranceGroupMasterMapper.toDto(insuranceGroupMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInsuranceGroupMasterMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(insuranceGroupMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the InsuranceGroupMaster in the database
        List<InsuranceGroupMaster> insuranceGroupMasterList = insuranceGroupMasterRepository.findAll();
        assertThat(insuranceGroupMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteInsuranceGroupMaster() throws Exception {
        // Initialize the database
        insuranceGroupMasterRepository.saveAndFlush(insuranceGroupMaster);

        int databaseSizeBeforeDelete = insuranceGroupMasterRepository.findAll().size();

        // Delete the insuranceGroupMaster
        restInsuranceGroupMasterMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, insuranceGroupMaster.getInsuranceGroupMasterId()).with(csrf()).accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InsuranceGroupMaster> insuranceGroupMasterList = insuranceGroupMasterRepository.findAll();
        assertThat(insuranceGroupMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
