package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ProcedureCodeMaster;
import com.sunknowledge.dme.rcm.repository.ProcedureCodeMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.ProcedureCodeMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.ProcedureCodeMasterMapper;
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
 * Integration tests for the {@link ProcedureCodeMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProcedureCodeMasterResourceIT {

    private static final String DEFAULT_ITEM_PROCEDURE_CODE_DESC = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_PROCEDURE_CODE_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_PROCEDURE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PROCEDURE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final UUID DEFAULT_ITEM_PROCEDURE_CODE_UUID = UUID.randomUUID();
    private static final UUID UPDATED_ITEM_PROCEDURE_CODE_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/procedure-code-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{procedureCodeId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ProcedureCodeMasterRepository procedureCodeMasterRepository;

    @Autowired
    private ProcedureCodeMasterMapper procedureCodeMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProcedureCodeMasterMockMvc;

    private ProcedureCodeMaster procedureCodeMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProcedureCodeMaster createEntity(EntityManager em) {
        ProcedureCodeMaster procedureCodeMaster = new ProcedureCodeMaster()
            .itemProcedureCodeDesc(DEFAULT_ITEM_PROCEDURE_CODE_DESC)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .procedureCode(DEFAULT_PROCEDURE_CODE)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .itemProcedureCodeUuid(DEFAULT_ITEM_PROCEDURE_CODE_UUID);
        return procedureCodeMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProcedureCodeMaster createUpdatedEntity(EntityManager em) {
        ProcedureCodeMaster procedureCodeMaster = new ProcedureCodeMaster()
            .itemProcedureCodeDesc(UPDATED_ITEM_PROCEDURE_CODE_DESC)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .procedureCode(UPDATED_PROCEDURE_CODE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .itemProcedureCodeUuid(UPDATED_ITEM_PROCEDURE_CODE_UUID);
        return procedureCodeMaster;
    }

    @BeforeEach
    public void initTest() {
        procedureCodeMaster = createEntity(em);
    }

    @Test
    @Transactional
    void createProcedureCodeMaster() throws Exception {
        int databaseSizeBeforeCreate = procedureCodeMasterRepository.findAll().size();
        // Create the ProcedureCodeMaster
        ProcedureCodeMasterDTO procedureCodeMasterDTO = procedureCodeMasterMapper.toDto(procedureCodeMaster);
        restProcedureCodeMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(procedureCodeMasterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ProcedureCodeMaster in the database
        List<ProcedureCodeMaster> procedureCodeMasterList = procedureCodeMasterRepository.findAll();
        assertThat(procedureCodeMasterList).hasSize(databaseSizeBeforeCreate + 1);
        ProcedureCodeMaster testProcedureCodeMaster = procedureCodeMasterList.get(procedureCodeMasterList.size() - 1);
        assertThat(testProcedureCodeMaster.getItemProcedureCodeDesc()).isEqualTo(DEFAULT_ITEM_PROCEDURE_CODE_DESC);
        assertThat(testProcedureCodeMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testProcedureCodeMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testProcedureCodeMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testProcedureCodeMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testProcedureCodeMaster.getProcedureCode()).isEqualTo(DEFAULT_PROCEDURE_CODE);
        assertThat(testProcedureCodeMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testProcedureCodeMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testProcedureCodeMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testProcedureCodeMaster.getItemProcedureCodeUuid()).isEqualTo(DEFAULT_ITEM_PROCEDURE_CODE_UUID);
    }

    @Test
    @Transactional
    void createProcedureCodeMasterWithExistingId() throws Exception {
        // Create the ProcedureCodeMaster with an existing ID
        procedureCodeMaster.setProcedureCodeId(1L);
        ProcedureCodeMasterDTO procedureCodeMasterDTO = procedureCodeMasterMapper.toDto(procedureCodeMaster);

        int databaseSizeBeforeCreate = procedureCodeMasterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProcedureCodeMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(procedureCodeMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProcedureCodeMaster in the database
        List<ProcedureCodeMaster> procedureCodeMasterList = procedureCodeMasterRepository.findAll();
        assertThat(procedureCodeMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllProcedureCodeMasters() throws Exception {
        // Initialize the database
        procedureCodeMasterRepository.saveAndFlush(procedureCodeMaster);

        // Get all the procedureCodeMasterList
        restProcedureCodeMasterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=procedureCodeId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].procedureCodeId").value(hasItem(procedureCodeMaster.getProcedureCodeId().intValue())))
            .andExpect(jsonPath("$.[*].itemProcedureCodeDesc").value(hasItem(DEFAULT_ITEM_PROCEDURE_CODE_DESC)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].procedureCode").value(hasItem(DEFAULT_PROCEDURE_CODE)))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].itemProcedureCodeUuid").value(hasItem(DEFAULT_ITEM_PROCEDURE_CODE_UUID.toString())));
    }

    @Test
    @Transactional
    void getProcedureCodeMaster() throws Exception {
        // Initialize the database
        procedureCodeMasterRepository.saveAndFlush(procedureCodeMaster);

        // Get the procedureCodeMaster
        restProcedureCodeMasterMockMvc
            .perform(get(ENTITY_API_URL_ID, procedureCodeMaster.getProcedureCodeId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.procedureCodeId").value(procedureCodeMaster.getProcedureCodeId().intValue()))
            .andExpect(jsonPath("$.itemProcedureCodeDesc").value(DEFAULT_ITEM_PROCEDURE_CODE_DESC))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.procedureCode").value(DEFAULT_PROCEDURE_CODE))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.itemProcedureCodeUuid").value(DEFAULT_ITEM_PROCEDURE_CODE_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingProcedureCodeMaster() throws Exception {
        // Get the procedureCodeMaster
        restProcedureCodeMasterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewProcedureCodeMaster() throws Exception {
        // Initialize the database
        procedureCodeMasterRepository.saveAndFlush(procedureCodeMaster);

        int databaseSizeBeforeUpdate = procedureCodeMasterRepository.findAll().size();

        // Update the procedureCodeMaster
        ProcedureCodeMaster updatedProcedureCodeMaster = procedureCodeMasterRepository
            .findById(procedureCodeMaster.getProcedureCodeId())
            .get();
        // Disconnect from session so that the updates on updatedProcedureCodeMaster are not directly saved in db
        em.detach(updatedProcedureCodeMaster);
        updatedProcedureCodeMaster
            .itemProcedureCodeDesc(UPDATED_ITEM_PROCEDURE_CODE_DESC)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .procedureCode(UPDATED_PROCEDURE_CODE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .itemProcedureCodeUuid(UPDATED_ITEM_PROCEDURE_CODE_UUID);
        ProcedureCodeMasterDTO procedureCodeMasterDTO = procedureCodeMasterMapper.toDto(updatedProcedureCodeMaster);

        restProcedureCodeMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, procedureCodeMasterDTO.getProcedureCodeId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(procedureCodeMasterDTO))
            )
            .andExpect(status().isOk());

        // Validate the ProcedureCodeMaster in the database
        List<ProcedureCodeMaster> procedureCodeMasterList = procedureCodeMasterRepository.findAll();
        assertThat(procedureCodeMasterList).hasSize(databaseSizeBeforeUpdate);
        ProcedureCodeMaster testProcedureCodeMaster = procedureCodeMasterList.get(procedureCodeMasterList.size() - 1);
        assertThat(testProcedureCodeMaster.getItemProcedureCodeDesc()).isEqualTo(UPDATED_ITEM_PROCEDURE_CODE_DESC);
        assertThat(testProcedureCodeMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testProcedureCodeMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testProcedureCodeMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testProcedureCodeMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testProcedureCodeMaster.getProcedureCode()).isEqualTo(UPDATED_PROCEDURE_CODE);
        assertThat(testProcedureCodeMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testProcedureCodeMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testProcedureCodeMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testProcedureCodeMaster.getItemProcedureCodeUuid()).isEqualTo(UPDATED_ITEM_PROCEDURE_CODE_UUID);
    }

    @Test
    @Transactional
    void putNonExistingProcedureCodeMaster() throws Exception {
        int databaseSizeBeforeUpdate = procedureCodeMasterRepository.findAll().size();
        procedureCodeMaster.setProcedureCodeId(count.incrementAndGet());

        // Create the ProcedureCodeMaster
        ProcedureCodeMasterDTO procedureCodeMasterDTO = procedureCodeMasterMapper.toDto(procedureCodeMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProcedureCodeMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, procedureCodeMasterDTO.getProcedureCodeId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(procedureCodeMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProcedureCodeMaster in the database
        List<ProcedureCodeMaster> procedureCodeMasterList = procedureCodeMasterRepository.findAll();
        assertThat(procedureCodeMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProcedureCodeMaster() throws Exception {
        int databaseSizeBeforeUpdate = procedureCodeMasterRepository.findAll().size();
        procedureCodeMaster.setProcedureCodeId(count.incrementAndGet());

        // Create the ProcedureCodeMaster
        ProcedureCodeMasterDTO procedureCodeMasterDTO = procedureCodeMasterMapper.toDto(procedureCodeMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProcedureCodeMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(procedureCodeMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProcedureCodeMaster in the database
        List<ProcedureCodeMaster> procedureCodeMasterList = procedureCodeMasterRepository.findAll();
        assertThat(procedureCodeMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProcedureCodeMaster() throws Exception {
        int databaseSizeBeforeUpdate = procedureCodeMasterRepository.findAll().size();
        procedureCodeMaster.setProcedureCodeId(count.incrementAndGet());

        // Create the ProcedureCodeMaster
        ProcedureCodeMasterDTO procedureCodeMasterDTO = procedureCodeMasterMapper.toDto(procedureCodeMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProcedureCodeMasterMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(procedureCodeMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProcedureCodeMaster in the database
        List<ProcedureCodeMaster> procedureCodeMasterList = procedureCodeMasterRepository.findAll();
        assertThat(procedureCodeMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProcedureCodeMasterWithPatch() throws Exception {
        // Initialize the database
        procedureCodeMasterRepository.saveAndFlush(procedureCodeMaster);

        int databaseSizeBeforeUpdate = procedureCodeMasterRepository.findAll().size();

        // Update the procedureCodeMaster using partial update
        ProcedureCodeMaster partialUpdatedProcedureCodeMaster = new ProcedureCodeMaster();
        partialUpdatedProcedureCodeMaster.setProcedureCodeId(procedureCodeMaster.getProcedureCodeId());

        partialUpdatedProcedureCodeMaster
            .updatedDate(UPDATED_UPDATED_DATE)
            .procedureCode(UPDATED_PROCEDURE_CODE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .itemProcedureCodeUuid(UPDATED_ITEM_PROCEDURE_CODE_UUID);

        restProcedureCodeMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProcedureCodeMaster.getProcedureCodeId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProcedureCodeMaster))
            )
            .andExpect(status().isOk());

        // Validate the ProcedureCodeMaster in the database
        List<ProcedureCodeMaster> procedureCodeMasterList = procedureCodeMasterRepository.findAll();
        assertThat(procedureCodeMasterList).hasSize(databaseSizeBeforeUpdate);
        ProcedureCodeMaster testProcedureCodeMaster = procedureCodeMasterList.get(procedureCodeMasterList.size() - 1);
        assertThat(testProcedureCodeMaster.getItemProcedureCodeDesc()).isEqualTo(DEFAULT_ITEM_PROCEDURE_CODE_DESC);
        assertThat(testProcedureCodeMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testProcedureCodeMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testProcedureCodeMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testProcedureCodeMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testProcedureCodeMaster.getProcedureCode()).isEqualTo(UPDATED_PROCEDURE_CODE);
        assertThat(testProcedureCodeMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testProcedureCodeMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testProcedureCodeMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testProcedureCodeMaster.getItemProcedureCodeUuid()).isEqualTo(UPDATED_ITEM_PROCEDURE_CODE_UUID);
    }

    @Test
    @Transactional
    void fullUpdateProcedureCodeMasterWithPatch() throws Exception {
        // Initialize the database
        procedureCodeMasterRepository.saveAndFlush(procedureCodeMaster);

        int databaseSizeBeforeUpdate = procedureCodeMasterRepository.findAll().size();

        // Update the procedureCodeMaster using partial update
        ProcedureCodeMaster partialUpdatedProcedureCodeMaster = new ProcedureCodeMaster();
        partialUpdatedProcedureCodeMaster.setProcedureCodeId(procedureCodeMaster.getProcedureCodeId());

        partialUpdatedProcedureCodeMaster
            .itemProcedureCodeDesc(UPDATED_ITEM_PROCEDURE_CODE_DESC)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .procedureCode(UPDATED_PROCEDURE_CODE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .itemProcedureCodeUuid(UPDATED_ITEM_PROCEDURE_CODE_UUID);

        restProcedureCodeMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProcedureCodeMaster.getProcedureCodeId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProcedureCodeMaster))
            )
            .andExpect(status().isOk());

        // Validate the ProcedureCodeMaster in the database
        List<ProcedureCodeMaster> procedureCodeMasterList = procedureCodeMasterRepository.findAll();
        assertThat(procedureCodeMasterList).hasSize(databaseSizeBeforeUpdate);
        ProcedureCodeMaster testProcedureCodeMaster = procedureCodeMasterList.get(procedureCodeMasterList.size() - 1);
        assertThat(testProcedureCodeMaster.getItemProcedureCodeDesc()).isEqualTo(UPDATED_ITEM_PROCEDURE_CODE_DESC);
        assertThat(testProcedureCodeMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testProcedureCodeMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testProcedureCodeMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testProcedureCodeMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testProcedureCodeMaster.getProcedureCode()).isEqualTo(UPDATED_PROCEDURE_CODE);
        assertThat(testProcedureCodeMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testProcedureCodeMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testProcedureCodeMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testProcedureCodeMaster.getItemProcedureCodeUuid()).isEqualTo(UPDATED_ITEM_PROCEDURE_CODE_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingProcedureCodeMaster() throws Exception {
        int databaseSizeBeforeUpdate = procedureCodeMasterRepository.findAll().size();
        procedureCodeMaster.setProcedureCodeId(count.incrementAndGet());

        // Create the ProcedureCodeMaster
        ProcedureCodeMasterDTO procedureCodeMasterDTO = procedureCodeMasterMapper.toDto(procedureCodeMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProcedureCodeMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, procedureCodeMasterDTO.getProcedureCodeId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(procedureCodeMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProcedureCodeMaster in the database
        List<ProcedureCodeMaster> procedureCodeMasterList = procedureCodeMasterRepository.findAll();
        assertThat(procedureCodeMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProcedureCodeMaster() throws Exception {
        int databaseSizeBeforeUpdate = procedureCodeMasterRepository.findAll().size();
        procedureCodeMaster.setProcedureCodeId(count.incrementAndGet());

        // Create the ProcedureCodeMaster
        ProcedureCodeMasterDTO procedureCodeMasterDTO = procedureCodeMasterMapper.toDto(procedureCodeMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProcedureCodeMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(procedureCodeMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProcedureCodeMaster in the database
        List<ProcedureCodeMaster> procedureCodeMasterList = procedureCodeMasterRepository.findAll();
        assertThat(procedureCodeMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProcedureCodeMaster() throws Exception {
        int databaseSizeBeforeUpdate = procedureCodeMasterRepository.findAll().size();
        procedureCodeMaster.setProcedureCodeId(count.incrementAndGet());

        // Create the ProcedureCodeMaster
        ProcedureCodeMasterDTO procedureCodeMasterDTO = procedureCodeMasterMapper.toDto(procedureCodeMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProcedureCodeMasterMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(procedureCodeMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProcedureCodeMaster in the database
        List<ProcedureCodeMaster> procedureCodeMasterList = procedureCodeMasterRepository.findAll();
        assertThat(procedureCodeMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProcedureCodeMaster() throws Exception {
        // Initialize the database
        procedureCodeMasterRepository.saveAndFlush(procedureCodeMaster);

        int databaseSizeBeforeDelete = procedureCodeMasterRepository.findAll().size();

        // Delete the procedureCodeMaster
        restProcedureCodeMasterMockMvc
            .perform(delete(ENTITY_API_URL_ID, procedureCodeMaster.getProcedureCodeId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProcedureCodeMaster> procedureCodeMasterList = procedureCodeMasterRepository.findAll();
        assertThat(procedureCodeMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
