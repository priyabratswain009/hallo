package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.StopReasonMaster;
import com.sunknowledge.dme.rcm.repository.StopReasonMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.StopReasonMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.StopReasonMasterMapper;
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
 * Integration tests for the {@link StopReasonMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class StopReasonMasterResourceIT {

    private static final String DEFAULT_STOP_REASON_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STOP_REASON_NAME = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_STOP_REASON_MASTER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_STOP_REASON_MASTER_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/stop-reason-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{stopReasonId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private StopReasonMasterRepository stopReasonMasterRepository;

    @Autowired
    private StopReasonMasterMapper stopReasonMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStopReasonMasterMockMvc;

    private StopReasonMaster stopReasonMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StopReasonMaster createEntity(EntityManager em) {
        StopReasonMaster stopReasonMaster = new StopReasonMaster()
            .stopReasonName(DEFAULT_STOP_REASON_NAME)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .status(DEFAULT_STATUS)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .stopReasonMasterUuid(DEFAULT_STOP_REASON_MASTER_UUID);
        return stopReasonMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StopReasonMaster createUpdatedEntity(EntityManager em) {
        StopReasonMaster stopReasonMaster = new StopReasonMaster()
            .stopReasonName(UPDATED_STOP_REASON_NAME)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .status(UPDATED_STATUS)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .stopReasonMasterUuid(UPDATED_STOP_REASON_MASTER_UUID);
        return stopReasonMaster;
    }

    @BeforeEach
    public void initTest() {
        stopReasonMaster = createEntity(em);
    }

    @Test
    @Transactional
    void createStopReasonMaster() throws Exception {
        int databaseSizeBeforeCreate = stopReasonMasterRepository.findAll().size();
        // Create the StopReasonMaster
        StopReasonMasterDTO stopReasonMasterDTO = stopReasonMasterMapper.toDto(stopReasonMaster);
        restStopReasonMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(stopReasonMasterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the StopReasonMaster in the database
        List<StopReasonMaster> stopReasonMasterList = stopReasonMasterRepository.findAll();
        assertThat(stopReasonMasterList).hasSize(databaseSizeBeforeCreate + 1);
        StopReasonMaster testStopReasonMaster = stopReasonMasterList.get(stopReasonMasterList.size() - 1);
        assertThat(testStopReasonMaster.getStopReasonName()).isEqualTo(DEFAULT_STOP_REASON_NAME);
        assertThat(testStopReasonMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testStopReasonMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testStopReasonMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testStopReasonMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testStopReasonMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testStopReasonMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testStopReasonMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testStopReasonMaster.getStopReasonMasterUuid()).isEqualTo(DEFAULT_STOP_REASON_MASTER_UUID);
    }

    @Test
    @Transactional
    void createStopReasonMasterWithExistingId() throws Exception {
        // Create the StopReasonMaster with an existing ID
        stopReasonMaster.setStopReasonId(1L);
        StopReasonMasterDTO stopReasonMasterDTO = stopReasonMasterMapper.toDto(stopReasonMaster);

        int databaseSizeBeforeCreate = stopReasonMasterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restStopReasonMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(stopReasonMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the StopReasonMaster in the database
        List<StopReasonMaster> stopReasonMasterList = stopReasonMasterRepository.findAll();
        assertThat(stopReasonMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllStopReasonMasters() throws Exception {
        // Initialize the database
        stopReasonMasterRepository.saveAndFlush(stopReasonMaster);

        // Get all the stopReasonMasterList
        restStopReasonMasterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=stopReasonId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].stopReasonId").value(hasItem(stopReasonMaster.getStopReasonId().intValue())))
            .andExpect(jsonPath("$.[*].stopReasonName").value(hasItem(DEFAULT_STOP_REASON_NAME)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].stopReasonMasterUuid").value(hasItem(DEFAULT_STOP_REASON_MASTER_UUID.toString())));
    }

    @Test
    @Transactional
    void getStopReasonMaster() throws Exception {
        // Initialize the database
        stopReasonMasterRepository.saveAndFlush(stopReasonMaster);

        // Get the stopReasonMaster
        restStopReasonMasterMockMvc
            .perform(get(ENTITY_API_URL_ID, stopReasonMaster.getStopReasonId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.stopReasonId").value(stopReasonMaster.getStopReasonId().intValue()))
            .andExpect(jsonPath("$.stopReasonName").value(DEFAULT_STOP_REASON_NAME))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.stopReasonMasterUuid").value(DEFAULT_STOP_REASON_MASTER_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingStopReasonMaster() throws Exception {
        // Get the stopReasonMaster
        restStopReasonMasterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingStopReasonMaster() throws Exception {
        // Initialize the database
        stopReasonMasterRepository.saveAndFlush(stopReasonMaster);

        int databaseSizeBeforeUpdate = stopReasonMasterRepository.findAll().size();

        // Update the stopReasonMaster
        StopReasonMaster updatedStopReasonMaster = stopReasonMasterRepository.findById(stopReasonMaster.getStopReasonId()).get();
        // Disconnect from session so that the updates on updatedStopReasonMaster are not directly saved in db
        em.detach(updatedStopReasonMaster);
        updatedStopReasonMaster
            .stopReasonName(UPDATED_STOP_REASON_NAME)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .status(UPDATED_STATUS)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .stopReasonMasterUuid(UPDATED_STOP_REASON_MASTER_UUID);
        StopReasonMasterDTO stopReasonMasterDTO = stopReasonMasterMapper.toDto(updatedStopReasonMaster);

        restStopReasonMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, stopReasonMasterDTO.getStopReasonId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(stopReasonMasterDTO))
            )
            .andExpect(status().isOk());

        // Validate the StopReasonMaster in the database
        List<StopReasonMaster> stopReasonMasterList = stopReasonMasterRepository.findAll();
        assertThat(stopReasonMasterList).hasSize(databaseSizeBeforeUpdate);
        StopReasonMaster testStopReasonMaster = stopReasonMasterList.get(stopReasonMasterList.size() - 1);
        assertThat(testStopReasonMaster.getStopReasonName()).isEqualTo(UPDATED_STOP_REASON_NAME);
        assertThat(testStopReasonMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testStopReasonMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testStopReasonMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testStopReasonMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testStopReasonMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testStopReasonMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testStopReasonMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testStopReasonMaster.getStopReasonMasterUuid()).isEqualTo(UPDATED_STOP_REASON_MASTER_UUID);
    }

    @Test
    @Transactional
    void putNonExistingStopReasonMaster() throws Exception {
        int databaseSizeBeforeUpdate = stopReasonMasterRepository.findAll().size();
        stopReasonMaster.setStopReasonId(count.incrementAndGet());

        // Create the StopReasonMaster
        StopReasonMasterDTO stopReasonMasterDTO = stopReasonMasterMapper.toDto(stopReasonMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStopReasonMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, stopReasonMasterDTO.getStopReasonId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(stopReasonMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the StopReasonMaster in the database
        List<StopReasonMaster> stopReasonMasterList = stopReasonMasterRepository.findAll();
        assertThat(stopReasonMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchStopReasonMaster() throws Exception {
        int databaseSizeBeforeUpdate = stopReasonMasterRepository.findAll().size();
        stopReasonMaster.setStopReasonId(count.incrementAndGet());

        // Create the StopReasonMaster
        StopReasonMasterDTO stopReasonMasterDTO = stopReasonMasterMapper.toDto(stopReasonMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStopReasonMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(stopReasonMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the StopReasonMaster in the database
        List<StopReasonMaster> stopReasonMasterList = stopReasonMasterRepository.findAll();
        assertThat(stopReasonMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamStopReasonMaster() throws Exception {
        int databaseSizeBeforeUpdate = stopReasonMasterRepository.findAll().size();
        stopReasonMaster.setStopReasonId(count.incrementAndGet());

        // Create the StopReasonMaster
        StopReasonMasterDTO stopReasonMasterDTO = stopReasonMasterMapper.toDto(stopReasonMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStopReasonMasterMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(stopReasonMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the StopReasonMaster in the database
        List<StopReasonMaster> stopReasonMasterList = stopReasonMasterRepository.findAll();
        assertThat(stopReasonMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateStopReasonMasterWithPatch() throws Exception {
        // Initialize the database
        stopReasonMasterRepository.saveAndFlush(stopReasonMaster);

        int databaseSizeBeforeUpdate = stopReasonMasterRepository.findAll().size();

        // Update the stopReasonMaster using partial update
        StopReasonMaster partialUpdatedStopReasonMaster = new StopReasonMaster();
        partialUpdatedStopReasonMaster.setStopReasonId(stopReasonMaster.getStopReasonId());

        partialUpdatedStopReasonMaster
            .stopReasonName(UPDATED_STOP_REASON_NAME)
            .createdById(UPDATED_CREATED_BY_ID)
            .status(UPDATED_STATUS)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME);

        restStopReasonMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStopReasonMaster.getStopReasonId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedStopReasonMaster))
            )
            .andExpect(status().isOk());

        // Validate the StopReasonMaster in the database
        List<StopReasonMaster> stopReasonMasterList = stopReasonMasterRepository.findAll();
        assertThat(stopReasonMasterList).hasSize(databaseSizeBeforeUpdate);
        StopReasonMaster testStopReasonMaster = stopReasonMasterList.get(stopReasonMasterList.size() - 1);
        assertThat(testStopReasonMaster.getStopReasonName()).isEqualTo(UPDATED_STOP_REASON_NAME);
        assertThat(testStopReasonMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testStopReasonMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testStopReasonMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testStopReasonMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testStopReasonMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testStopReasonMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testStopReasonMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testStopReasonMaster.getStopReasonMasterUuid()).isEqualTo(DEFAULT_STOP_REASON_MASTER_UUID);
    }

    @Test
    @Transactional
    void fullUpdateStopReasonMasterWithPatch() throws Exception {
        // Initialize the database
        stopReasonMasterRepository.saveAndFlush(stopReasonMaster);

        int databaseSizeBeforeUpdate = stopReasonMasterRepository.findAll().size();

        // Update the stopReasonMaster using partial update
        StopReasonMaster partialUpdatedStopReasonMaster = new StopReasonMaster();
        partialUpdatedStopReasonMaster.setStopReasonId(stopReasonMaster.getStopReasonId());

        partialUpdatedStopReasonMaster
            .stopReasonName(UPDATED_STOP_REASON_NAME)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .status(UPDATED_STATUS)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .stopReasonMasterUuid(UPDATED_STOP_REASON_MASTER_UUID);

        restStopReasonMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStopReasonMaster.getStopReasonId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedStopReasonMaster))
            )
            .andExpect(status().isOk());

        // Validate the StopReasonMaster in the database
        List<StopReasonMaster> stopReasonMasterList = stopReasonMasterRepository.findAll();
        assertThat(stopReasonMasterList).hasSize(databaseSizeBeforeUpdate);
        StopReasonMaster testStopReasonMaster = stopReasonMasterList.get(stopReasonMasterList.size() - 1);
        assertThat(testStopReasonMaster.getStopReasonName()).isEqualTo(UPDATED_STOP_REASON_NAME);
        assertThat(testStopReasonMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testStopReasonMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testStopReasonMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testStopReasonMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testStopReasonMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testStopReasonMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testStopReasonMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testStopReasonMaster.getStopReasonMasterUuid()).isEqualTo(UPDATED_STOP_REASON_MASTER_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingStopReasonMaster() throws Exception {
        int databaseSizeBeforeUpdate = stopReasonMasterRepository.findAll().size();
        stopReasonMaster.setStopReasonId(count.incrementAndGet());

        // Create the StopReasonMaster
        StopReasonMasterDTO stopReasonMasterDTO = stopReasonMasterMapper.toDto(stopReasonMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStopReasonMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, stopReasonMasterDTO.getStopReasonId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(stopReasonMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the StopReasonMaster in the database
        List<StopReasonMaster> stopReasonMasterList = stopReasonMasterRepository.findAll();
        assertThat(stopReasonMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchStopReasonMaster() throws Exception {
        int databaseSizeBeforeUpdate = stopReasonMasterRepository.findAll().size();
        stopReasonMaster.setStopReasonId(count.incrementAndGet());

        // Create the StopReasonMaster
        StopReasonMasterDTO stopReasonMasterDTO = stopReasonMasterMapper.toDto(stopReasonMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStopReasonMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(stopReasonMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the StopReasonMaster in the database
        List<StopReasonMaster> stopReasonMasterList = stopReasonMasterRepository.findAll();
        assertThat(stopReasonMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamStopReasonMaster() throws Exception {
        int databaseSizeBeforeUpdate = stopReasonMasterRepository.findAll().size();
        stopReasonMaster.setStopReasonId(count.incrementAndGet());

        // Create the StopReasonMaster
        StopReasonMasterDTO stopReasonMasterDTO = stopReasonMasterMapper.toDto(stopReasonMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStopReasonMasterMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(stopReasonMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the StopReasonMaster in the database
        List<StopReasonMaster> stopReasonMasterList = stopReasonMasterRepository.findAll();
        assertThat(stopReasonMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteStopReasonMaster() throws Exception {
        // Initialize the database
        stopReasonMasterRepository.saveAndFlush(stopReasonMaster);

        int databaseSizeBeforeDelete = stopReasonMasterRepository.findAll().size();

        // Delete the stopReasonMaster
        restStopReasonMasterMockMvc
            .perform(delete(ENTITY_API_URL_ID, stopReasonMaster.getStopReasonId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<StopReasonMaster> stopReasonMasterList = stopReasonMasterRepository.findAll();
        assertThat(stopReasonMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
