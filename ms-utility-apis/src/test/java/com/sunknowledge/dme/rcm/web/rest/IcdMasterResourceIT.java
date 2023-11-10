package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.IcdMaster;
import com.sunknowledge.dme.rcm.repository.IcdMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.IcdMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.IcdMasterMapper;
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
 * Integration tests for the {@link IcdMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class IcdMasterResourceIT {

    private static final String DEFAULT_ICD_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ICD_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_ICD_CODE_DESC = "AAAAAAAAAA";
    private static final String UPDATED_ICD_CODE_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_ICD_CODE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_ICD_CODE_TYPE = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_ICD_MASTER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_ICD_MASTER_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/icd-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{icdMasterId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private IcdMasterRepository icdMasterRepository;

    @Autowired
    private IcdMasterMapper icdMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restIcdMasterMockMvc;

    private IcdMaster icdMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static IcdMaster createEntity(EntityManager em) {
        IcdMaster icdMaster = new IcdMaster()
            .icdCode(DEFAULT_ICD_CODE)
            .icdCodeDesc(DEFAULT_ICD_CODE_DESC)
            .icdCodeType(DEFAULT_ICD_CODE_TYPE)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .icdMasterUuid(DEFAULT_ICD_MASTER_UUID);
        return icdMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static IcdMaster createUpdatedEntity(EntityManager em) {
        IcdMaster icdMaster = new IcdMaster()
            .icdCode(UPDATED_ICD_CODE)
            .icdCodeDesc(UPDATED_ICD_CODE_DESC)
            .icdCodeType(UPDATED_ICD_CODE_TYPE)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .icdMasterUuid(UPDATED_ICD_MASTER_UUID);
        return icdMaster;
    }

    @BeforeEach
    public void initTest() {
        icdMaster = createEntity(em);
    }

    @Test
    @Transactional
    void createIcdMaster() throws Exception {
        int databaseSizeBeforeCreate = icdMasterRepository.findAll().size();
        // Create the IcdMaster
        IcdMasterDTO icdMasterDTO = icdMasterMapper.toDto(icdMaster);
        restIcdMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(icdMasterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the IcdMaster in the database
        List<IcdMaster> icdMasterList = icdMasterRepository.findAll();
        assertThat(icdMasterList).hasSize(databaseSizeBeforeCreate + 1);
        IcdMaster testIcdMaster = icdMasterList.get(icdMasterList.size() - 1);
        assertThat(testIcdMaster.getIcdCode()).isEqualTo(DEFAULT_ICD_CODE);
        assertThat(testIcdMaster.getIcdCodeDesc()).isEqualTo(DEFAULT_ICD_CODE_DESC);
        assertThat(testIcdMaster.getIcdCodeType()).isEqualTo(DEFAULT_ICD_CODE_TYPE);
        assertThat(testIcdMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testIcdMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testIcdMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testIcdMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testIcdMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testIcdMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testIcdMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testIcdMaster.getIcdMasterUuid()).isEqualTo(DEFAULT_ICD_MASTER_UUID);
    }

    @Test
    @Transactional
    void createIcdMasterWithExistingId() throws Exception {
        // Create the IcdMaster with an existing ID
        icdMaster.setIcdMasterId(1L);
        IcdMasterDTO icdMasterDTO = icdMasterMapper.toDto(icdMaster);

        int databaseSizeBeforeCreate = icdMasterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restIcdMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(icdMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the IcdMaster in the database
        List<IcdMaster> icdMasterList = icdMasterRepository.findAll();
        assertThat(icdMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllIcdMasters() throws Exception {
        // Initialize the database
        icdMasterRepository.saveAndFlush(icdMaster);

        // Get all the icdMasterList
        restIcdMasterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=icdMasterId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].icdMasterId").value(hasItem(icdMaster.getIcdMasterId().intValue())))
            .andExpect(jsonPath("$.[*].icdCode").value(hasItem(DEFAULT_ICD_CODE)))
            .andExpect(jsonPath("$.[*].icdCodeDesc").value(hasItem(DEFAULT_ICD_CODE_DESC)))
            .andExpect(jsonPath("$.[*].icdCodeType").value(hasItem(DEFAULT_ICD_CODE_TYPE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].icdMasterUuid").value(hasItem(DEFAULT_ICD_MASTER_UUID.toString())));
    }

    @Test
    @Transactional
    void getIcdMaster() throws Exception {
        // Initialize the database
        icdMasterRepository.saveAndFlush(icdMaster);

        // Get the icdMaster
        restIcdMasterMockMvc
            .perform(get(ENTITY_API_URL_ID, icdMaster.getIcdMasterId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.icdMasterId").value(icdMaster.getIcdMasterId().intValue()))
            .andExpect(jsonPath("$.icdCode").value(DEFAULT_ICD_CODE))
            .andExpect(jsonPath("$.icdCodeDesc").value(DEFAULT_ICD_CODE_DESC))
            .andExpect(jsonPath("$.icdCodeType").value(DEFAULT_ICD_CODE_TYPE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.icdMasterUuid").value(DEFAULT_ICD_MASTER_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingIcdMaster() throws Exception {
        // Get the icdMaster
        restIcdMasterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewIcdMaster() throws Exception {
        // Initialize the database
        icdMasterRepository.saveAndFlush(icdMaster);

        int databaseSizeBeforeUpdate = icdMasterRepository.findAll().size();

        // Update the icdMaster
        IcdMaster updatedIcdMaster = icdMasterRepository.findById(icdMaster.getIcdMasterId()).get();
        // Disconnect from session so that the updates on updatedIcdMaster are not directly saved in db
        em.detach(updatedIcdMaster);
        updatedIcdMaster
            .icdCode(UPDATED_ICD_CODE)
            .icdCodeDesc(UPDATED_ICD_CODE_DESC)
            .icdCodeType(UPDATED_ICD_CODE_TYPE)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .icdMasterUuid(UPDATED_ICD_MASTER_UUID);
        IcdMasterDTO icdMasterDTO = icdMasterMapper.toDto(updatedIcdMaster);

        restIcdMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, icdMasterDTO.getIcdMasterId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(icdMasterDTO))
            )
            .andExpect(status().isOk());

        // Validate the IcdMaster in the database
        List<IcdMaster> icdMasterList = icdMasterRepository.findAll();
        assertThat(icdMasterList).hasSize(databaseSizeBeforeUpdate);
        IcdMaster testIcdMaster = icdMasterList.get(icdMasterList.size() - 1);
        assertThat(testIcdMaster.getIcdCode()).isEqualTo(UPDATED_ICD_CODE);
        assertThat(testIcdMaster.getIcdCodeDesc()).isEqualTo(UPDATED_ICD_CODE_DESC);
        assertThat(testIcdMaster.getIcdCodeType()).isEqualTo(UPDATED_ICD_CODE_TYPE);
        assertThat(testIcdMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testIcdMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testIcdMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testIcdMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testIcdMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testIcdMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testIcdMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testIcdMaster.getIcdMasterUuid()).isEqualTo(UPDATED_ICD_MASTER_UUID);
    }

    @Test
    @Transactional
    void putNonExistingIcdMaster() throws Exception {
        int databaseSizeBeforeUpdate = icdMasterRepository.findAll().size();
        icdMaster.setIcdMasterId(count.incrementAndGet());

        // Create the IcdMaster
        IcdMasterDTO icdMasterDTO = icdMasterMapper.toDto(icdMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIcdMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, icdMasterDTO.getIcdMasterId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(icdMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the IcdMaster in the database
        List<IcdMaster> icdMasterList = icdMasterRepository.findAll();
        assertThat(icdMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchIcdMaster() throws Exception {
        int databaseSizeBeforeUpdate = icdMasterRepository.findAll().size();
        icdMaster.setIcdMasterId(count.incrementAndGet());

        // Create the IcdMaster
        IcdMasterDTO icdMasterDTO = icdMasterMapper.toDto(icdMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restIcdMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(icdMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the IcdMaster in the database
        List<IcdMaster> icdMasterList = icdMasterRepository.findAll();
        assertThat(icdMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamIcdMaster() throws Exception {
        int databaseSizeBeforeUpdate = icdMasterRepository.findAll().size();
        icdMaster.setIcdMasterId(count.incrementAndGet());

        // Create the IcdMaster
        IcdMasterDTO icdMasterDTO = icdMasterMapper.toDto(icdMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restIcdMasterMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(icdMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the IcdMaster in the database
        List<IcdMaster> icdMasterList = icdMasterRepository.findAll();
        assertThat(icdMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateIcdMasterWithPatch() throws Exception {
        // Initialize the database
        icdMasterRepository.saveAndFlush(icdMaster);

        int databaseSizeBeforeUpdate = icdMasterRepository.findAll().size();

        // Update the icdMaster using partial update
        IcdMaster partialUpdatedIcdMaster = new IcdMaster();
        partialUpdatedIcdMaster.setIcdMasterId(icdMaster.getIcdMasterId());

        partialUpdatedIcdMaster
            .icdCode(UPDATED_ICD_CODE)
            .icdCodeType(UPDATED_ICD_CODE_TYPE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE);

        restIcdMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedIcdMaster.getIcdMasterId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedIcdMaster))
            )
            .andExpect(status().isOk());

        // Validate the IcdMaster in the database
        List<IcdMaster> icdMasterList = icdMasterRepository.findAll();
        assertThat(icdMasterList).hasSize(databaseSizeBeforeUpdate);
        IcdMaster testIcdMaster = icdMasterList.get(icdMasterList.size() - 1);
        assertThat(testIcdMaster.getIcdCode()).isEqualTo(UPDATED_ICD_CODE);
        assertThat(testIcdMaster.getIcdCodeDesc()).isEqualTo(DEFAULT_ICD_CODE_DESC);
        assertThat(testIcdMaster.getIcdCodeType()).isEqualTo(UPDATED_ICD_CODE_TYPE);
        assertThat(testIcdMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testIcdMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testIcdMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testIcdMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testIcdMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testIcdMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testIcdMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testIcdMaster.getIcdMasterUuid()).isEqualTo(DEFAULT_ICD_MASTER_UUID);
    }

    @Test
    @Transactional
    void fullUpdateIcdMasterWithPatch() throws Exception {
        // Initialize the database
        icdMasterRepository.saveAndFlush(icdMaster);

        int databaseSizeBeforeUpdate = icdMasterRepository.findAll().size();

        // Update the icdMaster using partial update
        IcdMaster partialUpdatedIcdMaster = new IcdMaster();
        partialUpdatedIcdMaster.setIcdMasterId(icdMaster.getIcdMasterId());

        partialUpdatedIcdMaster
            .icdCode(UPDATED_ICD_CODE)
            .icdCodeDesc(UPDATED_ICD_CODE_DESC)
            .icdCodeType(UPDATED_ICD_CODE_TYPE)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .icdMasterUuid(UPDATED_ICD_MASTER_UUID);

        restIcdMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedIcdMaster.getIcdMasterId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedIcdMaster))
            )
            .andExpect(status().isOk());

        // Validate the IcdMaster in the database
        List<IcdMaster> icdMasterList = icdMasterRepository.findAll();
        assertThat(icdMasterList).hasSize(databaseSizeBeforeUpdate);
        IcdMaster testIcdMaster = icdMasterList.get(icdMasterList.size() - 1);
        assertThat(testIcdMaster.getIcdCode()).isEqualTo(UPDATED_ICD_CODE);
        assertThat(testIcdMaster.getIcdCodeDesc()).isEqualTo(UPDATED_ICD_CODE_DESC);
        assertThat(testIcdMaster.getIcdCodeType()).isEqualTo(UPDATED_ICD_CODE_TYPE);
        assertThat(testIcdMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testIcdMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testIcdMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testIcdMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testIcdMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testIcdMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testIcdMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testIcdMaster.getIcdMasterUuid()).isEqualTo(UPDATED_ICD_MASTER_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingIcdMaster() throws Exception {
        int databaseSizeBeforeUpdate = icdMasterRepository.findAll().size();
        icdMaster.setIcdMasterId(count.incrementAndGet());

        // Create the IcdMaster
        IcdMasterDTO icdMasterDTO = icdMasterMapper.toDto(icdMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIcdMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, icdMasterDTO.getIcdMasterId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(icdMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the IcdMaster in the database
        List<IcdMaster> icdMasterList = icdMasterRepository.findAll();
        assertThat(icdMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchIcdMaster() throws Exception {
        int databaseSizeBeforeUpdate = icdMasterRepository.findAll().size();
        icdMaster.setIcdMasterId(count.incrementAndGet());

        // Create the IcdMaster
        IcdMasterDTO icdMasterDTO = icdMasterMapper.toDto(icdMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restIcdMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(icdMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the IcdMaster in the database
        List<IcdMaster> icdMasterList = icdMasterRepository.findAll();
        assertThat(icdMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamIcdMaster() throws Exception {
        int databaseSizeBeforeUpdate = icdMasterRepository.findAll().size();
        icdMaster.setIcdMasterId(count.incrementAndGet());

        // Create the IcdMaster
        IcdMasterDTO icdMasterDTO = icdMasterMapper.toDto(icdMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restIcdMasterMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(icdMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the IcdMaster in the database
        List<IcdMaster> icdMasterList = icdMasterRepository.findAll();
        assertThat(icdMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteIcdMaster() throws Exception {
        // Initialize the database
        icdMasterRepository.saveAndFlush(icdMaster);

        int databaseSizeBeforeDelete = icdMasterRepository.findAll().size();

        // Delete the icdMaster
        restIcdMasterMockMvc
            .perform(delete(ENTITY_API_URL_ID, icdMaster.getIcdMasterId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<IcdMaster> icdMasterList = icdMasterRepository.findAll();
        assertThat(icdMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
