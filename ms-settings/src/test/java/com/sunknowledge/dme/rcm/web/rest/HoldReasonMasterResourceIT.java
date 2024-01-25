package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.HoldReasonMaster;
import com.sunknowledge.dme.rcm.repository.HoldReasonMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.HoldReasonMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.HoldReasonMasterMapper;
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
 * Integration tests for the {@link HoldReasonMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class HoldReasonMasterResourceIT {

    private static final String DEFAULT_HOLD_REASON_NAME = "AAAAAAAAAA";
    private static final String UPDATED_HOLD_REASON_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final UUID DEFAULT_HOLD_REASON_MASTER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_HOLD_REASON_MASTER_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/hold-reason-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{holdReasonId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private HoldReasonMasterRepository holdReasonMasterRepository;

    @Autowired
    private HoldReasonMasterMapper holdReasonMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restHoldReasonMasterMockMvc;

    private HoldReasonMaster holdReasonMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HoldReasonMaster createEntity(EntityManager em) {
        HoldReasonMaster holdReasonMaster = new HoldReasonMaster()
            .holdReasonName(DEFAULT_HOLD_REASON_NAME)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .status(DEFAULT_STATUS)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .holdReasonMasterUuid(DEFAULT_HOLD_REASON_MASTER_UUID);
        return holdReasonMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HoldReasonMaster createUpdatedEntity(EntityManager em) {
        HoldReasonMaster holdReasonMaster = new HoldReasonMaster()
            .holdReasonName(UPDATED_HOLD_REASON_NAME)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .status(UPDATED_STATUS)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .holdReasonMasterUuid(UPDATED_HOLD_REASON_MASTER_UUID);
        return holdReasonMaster;
    }

    @BeforeEach
    public void initTest() {
        holdReasonMaster = createEntity(em);
    }

    @Test
    @Transactional
    void createHoldReasonMaster() throws Exception {
        int databaseSizeBeforeCreate = holdReasonMasterRepository.findAll().size();
        // Create the HoldReasonMaster
        HoldReasonMasterDTO holdReasonMasterDTO = holdReasonMasterMapper.toDto(holdReasonMaster);
        restHoldReasonMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(holdReasonMasterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the HoldReasonMaster in the database
        List<HoldReasonMaster> holdReasonMasterList = holdReasonMasterRepository.findAll();
        assertThat(holdReasonMasterList).hasSize(databaseSizeBeforeCreate + 1);
        HoldReasonMaster testHoldReasonMaster = holdReasonMasterList.get(holdReasonMasterList.size() - 1);
        assertThat(testHoldReasonMaster.getHoldReasonName()).isEqualTo(DEFAULT_HOLD_REASON_NAME);
        assertThat(testHoldReasonMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testHoldReasonMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testHoldReasonMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testHoldReasonMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testHoldReasonMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testHoldReasonMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testHoldReasonMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testHoldReasonMaster.getHoldReasonMasterUuid()).isEqualTo(DEFAULT_HOLD_REASON_MASTER_UUID);
    }

    @Test
    @Transactional
    void createHoldReasonMasterWithExistingId() throws Exception {
        // Create the HoldReasonMaster with an existing ID
        holdReasonMaster.setHoldReasonId(1L);
        HoldReasonMasterDTO holdReasonMasterDTO = holdReasonMasterMapper.toDto(holdReasonMaster);

        int databaseSizeBeforeCreate = holdReasonMasterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restHoldReasonMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(holdReasonMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HoldReasonMaster in the database
        List<HoldReasonMaster> holdReasonMasterList = holdReasonMasterRepository.findAll();
        assertThat(holdReasonMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllHoldReasonMasters() throws Exception {
        // Initialize the database
        holdReasonMasterRepository.saveAndFlush(holdReasonMaster);

        // Get all the holdReasonMasterList
        restHoldReasonMasterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=holdReasonId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].holdReasonId").value(hasItem(holdReasonMaster.getHoldReasonId().intValue())))
            .andExpect(jsonPath("$.[*].holdReasonName").value(hasItem(DEFAULT_HOLD_REASON_NAME)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].holdReasonMasterUuid").value(hasItem(DEFAULT_HOLD_REASON_MASTER_UUID.toString())));
    }

    @Test
    @Transactional
    void getHoldReasonMaster() throws Exception {
        // Initialize the database
        holdReasonMasterRepository.saveAndFlush(holdReasonMaster);

        // Get the holdReasonMaster
        restHoldReasonMasterMockMvc
            .perform(get(ENTITY_API_URL_ID, holdReasonMaster.getHoldReasonId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.holdReasonId").value(holdReasonMaster.getHoldReasonId().intValue()))
            .andExpect(jsonPath("$.holdReasonName").value(DEFAULT_HOLD_REASON_NAME))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.holdReasonMasterUuid").value(DEFAULT_HOLD_REASON_MASTER_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingHoldReasonMaster() throws Exception {
        // Get the holdReasonMaster
        restHoldReasonMasterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingHoldReasonMaster() throws Exception {
        // Initialize the database
        holdReasonMasterRepository.saveAndFlush(holdReasonMaster);

        int databaseSizeBeforeUpdate = holdReasonMasterRepository.findAll().size();

        // Update the holdReasonMaster
        HoldReasonMaster updatedHoldReasonMaster = holdReasonMasterRepository.findById(holdReasonMaster.getHoldReasonId()).get();
        // Disconnect from session so that the updates on updatedHoldReasonMaster are not directly saved in db
        em.detach(updatedHoldReasonMaster);
        updatedHoldReasonMaster
            .holdReasonName(UPDATED_HOLD_REASON_NAME)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .status(UPDATED_STATUS)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .holdReasonMasterUuid(UPDATED_HOLD_REASON_MASTER_UUID);
        HoldReasonMasterDTO holdReasonMasterDTO = holdReasonMasterMapper.toDto(updatedHoldReasonMaster);

        restHoldReasonMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, holdReasonMasterDTO.getHoldReasonId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(holdReasonMasterDTO))
            )
            .andExpect(status().isOk());

        // Validate the HoldReasonMaster in the database
        List<HoldReasonMaster> holdReasonMasterList = holdReasonMasterRepository.findAll();
        assertThat(holdReasonMasterList).hasSize(databaseSizeBeforeUpdate);
        HoldReasonMaster testHoldReasonMaster = holdReasonMasterList.get(holdReasonMasterList.size() - 1);
        assertThat(testHoldReasonMaster.getHoldReasonName()).isEqualTo(UPDATED_HOLD_REASON_NAME);
        assertThat(testHoldReasonMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testHoldReasonMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testHoldReasonMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testHoldReasonMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testHoldReasonMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testHoldReasonMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testHoldReasonMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testHoldReasonMaster.getHoldReasonMasterUuid()).isEqualTo(UPDATED_HOLD_REASON_MASTER_UUID);
    }

    @Test
    @Transactional
    void putNonExistingHoldReasonMaster() throws Exception {
        int databaseSizeBeforeUpdate = holdReasonMasterRepository.findAll().size();
        holdReasonMaster.setHoldReasonId(count.incrementAndGet());

        // Create the HoldReasonMaster
        HoldReasonMasterDTO holdReasonMasterDTO = holdReasonMasterMapper.toDto(holdReasonMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHoldReasonMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, holdReasonMasterDTO.getHoldReasonId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(holdReasonMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HoldReasonMaster in the database
        List<HoldReasonMaster> holdReasonMasterList = holdReasonMasterRepository.findAll();
        assertThat(holdReasonMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchHoldReasonMaster() throws Exception {
        int databaseSizeBeforeUpdate = holdReasonMasterRepository.findAll().size();
        holdReasonMaster.setHoldReasonId(count.incrementAndGet());

        // Create the HoldReasonMaster
        HoldReasonMasterDTO holdReasonMasterDTO = holdReasonMasterMapper.toDto(holdReasonMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHoldReasonMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(holdReasonMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HoldReasonMaster in the database
        List<HoldReasonMaster> holdReasonMasterList = holdReasonMasterRepository.findAll();
        assertThat(holdReasonMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamHoldReasonMaster() throws Exception {
        int databaseSizeBeforeUpdate = holdReasonMasterRepository.findAll().size();
        holdReasonMaster.setHoldReasonId(count.incrementAndGet());

        // Create the HoldReasonMaster
        HoldReasonMasterDTO holdReasonMasterDTO = holdReasonMasterMapper.toDto(holdReasonMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHoldReasonMasterMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(holdReasonMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the HoldReasonMaster in the database
        List<HoldReasonMaster> holdReasonMasterList = holdReasonMasterRepository.findAll();
        assertThat(holdReasonMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateHoldReasonMasterWithPatch() throws Exception {
        // Initialize the database
        holdReasonMasterRepository.saveAndFlush(holdReasonMaster);

        int databaseSizeBeforeUpdate = holdReasonMasterRepository.findAll().size();

        // Update the holdReasonMaster using partial update
        HoldReasonMaster partialUpdatedHoldReasonMaster = new HoldReasonMaster();
        partialUpdatedHoldReasonMaster.setHoldReasonId(holdReasonMaster.getHoldReasonId());

        partialUpdatedHoldReasonMaster
            .createdById(UPDATED_CREATED_BY_ID)
            .status(UPDATED_STATUS)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .holdReasonMasterUuid(UPDATED_HOLD_REASON_MASTER_UUID);

        restHoldReasonMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHoldReasonMaster.getHoldReasonId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedHoldReasonMaster))
            )
            .andExpect(status().isOk());

        // Validate the HoldReasonMaster in the database
        List<HoldReasonMaster> holdReasonMasterList = holdReasonMasterRepository.findAll();
        assertThat(holdReasonMasterList).hasSize(databaseSizeBeforeUpdate);
        HoldReasonMaster testHoldReasonMaster = holdReasonMasterList.get(holdReasonMasterList.size() - 1);
        assertThat(testHoldReasonMaster.getHoldReasonName()).isEqualTo(DEFAULT_HOLD_REASON_NAME);
        assertThat(testHoldReasonMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testHoldReasonMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testHoldReasonMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testHoldReasonMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testHoldReasonMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testHoldReasonMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testHoldReasonMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testHoldReasonMaster.getHoldReasonMasterUuid()).isEqualTo(UPDATED_HOLD_REASON_MASTER_UUID);
    }

    @Test
    @Transactional
    void fullUpdateHoldReasonMasterWithPatch() throws Exception {
        // Initialize the database
        holdReasonMasterRepository.saveAndFlush(holdReasonMaster);

        int databaseSizeBeforeUpdate = holdReasonMasterRepository.findAll().size();

        // Update the holdReasonMaster using partial update
        HoldReasonMaster partialUpdatedHoldReasonMaster = new HoldReasonMaster();
        partialUpdatedHoldReasonMaster.setHoldReasonId(holdReasonMaster.getHoldReasonId());

        partialUpdatedHoldReasonMaster
            .holdReasonName(UPDATED_HOLD_REASON_NAME)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .status(UPDATED_STATUS)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .holdReasonMasterUuid(UPDATED_HOLD_REASON_MASTER_UUID);

        restHoldReasonMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHoldReasonMaster.getHoldReasonId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedHoldReasonMaster))
            )
            .andExpect(status().isOk());

        // Validate the HoldReasonMaster in the database
        List<HoldReasonMaster> holdReasonMasterList = holdReasonMasterRepository.findAll();
        assertThat(holdReasonMasterList).hasSize(databaseSizeBeforeUpdate);
        HoldReasonMaster testHoldReasonMaster = holdReasonMasterList.get(holdReasonMasterList.size() - 1);
        assertThat(testHoldReasonMaster.getHoldReasonName()).isEqualTo(UPDATED_HOLD_REASON_NAME);
        assertThat(testHoldReasonMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testHoldReasonMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testHoldReasonMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testHoldReasonMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testHoldReasonMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testHoldReasonMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testHoldReasonMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testHoldReasonMaster.getHoldReasonMasterUuid()).isEqualTo(UPDATED_HOLD_REASON_MASTER_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingHoldReasonMaster() throws Exception {
        int databaseSizeBeforeUpdate = holdReasonMasterRepository.findAll().size();
        holdReasonMaster.setHoldReasonId(count.incrementAndGet());

        // Create the HoldReasonMaster
        HoldReasonMasterDTO holdReasonMasterDTO = holdReasonMasterMapper.toDto(holdReasonMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHoldReasonMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, holdReasonMasterDTO.getHoldReasonId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(holdReasonMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HoldReasonMaster in the database
        List<HoldReasonMaster> holdReasonMasterList = holdReasonMasterRepository.findAll();
        assertThat(holdReasonMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchHoldReasonMaster() throws Exception {
        int databaseSizeBeforeUpdate = holdReasonMasterRepository.findAll().size();
        holdReasonMaster.setHoldReasonId(count.incrementAndGet());

        // Create the HoldReasonMaster
        HoldReasonMasterDTO holdReasonMasterDTO = holdReasonMasterMapper.toDto(holdReasonMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHoldReasonMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(holdReasonMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HoldReasonMaster in the database
        List<HoldReasonMaster> holdReasonMasterList = holdReasonMasterRepository.findAll();
        assertThat(holdReasonMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamHoldReasonMaster() throws Exception {
        int databaseSizeBeforeUpdate = holdReasonMasterRepository.findAll().size();
        holdReasonMaster.setHoldReasonId(count.incrementAndGet());

        // Create the HoldReasonMaster
        HoldReasonMasterDTO holdReasonMasterDTO = holdReasonMasterMapper.toDto(holdReasonMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHoldReasonMasterMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(holdReasonMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the HoldReasonMaster in the database
        List<HoldReasonMaster> holdReasonMasterList = holdReasonMasterRepository.findAll();
        assertThat(holdReasonMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteHoldReasonMaster() throws Exception {
        // Initialize the database
        holdReasonMasterRepository.saveAndFlush(holdReasonMaster);

        int databaseSizeBeforeDelete = holdReasonMasterRepository.findAll().size();

        // Delete the holdReasonMaster
        restHoldReasonMasterMockMvc
            .perform(delete(ENTITY_API_URL_ID, holdReasonMaster.getHoldReasonId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<HoldReasonMaster> holdReasonMasterList = holdReasonMasterRepository.findAll();
        assertThat(holdReasonMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
