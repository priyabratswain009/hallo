package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.WipStatusMaster;
import com.sunknowledge.dme.rcm.repository.WipStatusMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.WipStatusMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.WipStatusMasterMapper;
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
 * Integration tests for the {@link WipStatusMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class WipStatusMasterResourceIT {

    private static final String DEFAULT_WIP_STATUS_NAME = "AAAAAAAAAA";
    private static final String UPDATED_WIP_STATUS_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final UUID DEFAULT_WIP_STATUS_MASTER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_WIP_STATUS_MASTER_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/wip-status-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{wipStatusId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private WipStatusMasterRepository wipStatusMasterRepository;

    @Autowired
    private WipStatusMasterMapper wipStatusMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWipStatusMasterMockMvc;

    private WipStatusMaster wipStatusMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WipStatusMaster createEntity(EntityManager em) {
        WipStatusMaster wipStatusMaster = new WipStatusMaster()
            .wipStatusName(DEFAULT_WIP_STATUS_NAME)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .wipStatusMasterUuid(DEFAULT_WIP_STATUS_MASTER_UUID);
        return wipStatusMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WipStatusMaster createUpdatedEntity(EntityManager em) {
        WipStatusMaster wipStatusMaster = new WipStatusMaster()
            .wipStatusName(UPDATED_WIP_STATUS_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .wipStatusMasterUuid(UPDATED_WIP_STATUS_MASTER_UUID);
        return wipStatusMaster;
    }

    @BeforeEach
    public void initTest() {
        wipStatusMaster = createEntity(em);
    }

    @Test
    @Transactional
    void createWipStatusMaster() throws Exception {
        int databaseSizeBeforeCreate = wipStatusMasterRepository.findAll().size();
        // Create the WipStatusMaster
        WipStatusMasterDTO wipStatusMasterDTO = wipStatusMasterMapper.toDto(wipStatusMaster);
        restWipStatusMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(wipStatusMasterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the WipStatusMaster in the database
        List<WipStatusMaster> wipStatusMasterList = wipStatusMasterRepository.findAll();
        assertThat(wipStatusMasterList).hasSize(databaseSizeBeforeCreate + 1);
        WipStatusMaster testWipStatusMaster = wipStatusMasterList.get(wipStatusMasterList.size() - 1);
        assertThat(testWipStatusMaster.getWipStatusName()).isEqualTo(DEFAULT_WIP_STATUS_NAME);
        assertThat(testWipStatusMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testWipStatusMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testWipStatusMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testWipStatusMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testWipStatusMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testWipStatusMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testWipStatusMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testWipStatusMaster.getWipStatusMasterUuid()).isEqualTo(DEFAULT_WIP_STATUS_MASTER_UUID);
    }

    @Test
    @Transactional
    void createWipStatusMasterWithExistingId() throws Exception {
        // Create the WipStatusMaster with an existing ID
        wipStatusMaster.setWipStatusId(1L);
        WipStatusMasterDTO wipStatusMasterDTO = wipStatusMasterMapper.toDto(wipStatusMaster);

        int databaseSizeBeforeCreate = wipStatusMasterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restWipStatusMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(wipStatusMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WipStatusMaster in the database
        List<WipStatusMaster> wipStatusMasterList = wipStatusMasterRepository.findAll();
        assertThat(wipStatusMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllWipStatusMasters() throws Exception {
        // Initialize the database
        wipStatusMasterRepository.saveAndFlush(wipStatusMaster);

        // Get all the wipStatusMasterList
        restWipStatusMasterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=wipStatusId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].wipStatusId").value(hasItem(wipStatusMaster.getWipStatusId().intValue())))
            .andExpect(jsonPath("$.[*].wipStatusName").value(hasItem(DEFAULT_WIP_STATUS_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].wipStatusMasterUuid").value(hasItem(DEFAULT_WIP_STATUS_MASTER_UUID.toString())));
    }

    @Test
    @Transactional
    void getWipStatusMaster() throws Exception {
        // Initialize the database
        wipStatusMasterRepository.saveAndFlush(wipStatusMaster);

        // Get the wipStatusMaster
        restWipStatusMasterMockMvc
            .perform(get(ENTITY_API_URL_ID, wipStatusMaster.getWipStatusId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.wipStatusId").value(wipStatusMaster.getWipStatusId().intValue()))
            .andExpect(jsonPath("$.wipStatusName").value(DEFAULT_WIP_STATUS_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.wipStatusMasterUuid").value(DEFAULT_WIP_STATUS_MASTER_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingWipStatusMaster() throws Exception {
        // Get the wipStatusMaster
        restWipStatusMasterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingWipStatusMaster() throws Exception {
        // Initialize the database
        wipStatusMasterRepository.saveAndFlush(wipStatusMaster);

        int databaseSizeBeforeUpdate = wipStatusMasterRepository.findAll().size();

        // Update the wipStatusMaster
        WipStatusMaster updatedWipStatusMaster = wipStatusMasterRepository.findById(wipStatusMaster.getWipStatusId()).get();
        // Disconnect from session so that the updates on updatedWipStatusMaster are not directly saved in db
        em.detach(updatedWipStatusMaster);
        updatedWipStatusMaster
            .wipStatusName(UPDATED_WIP_STATUS_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .wipStatusMasterUuid(UPDATED_WIP_STATUS_MASTER_UUID);
        WipStatusMasterDTO wipStatusMasterDTO = wipStatusMasterMapper.toDto(updatedWipStatusMaster);

        restWipStatusMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, wipStatusMasterDTO.getWipStatusId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(wipStatusMasterDTO))
            )
            .andExpect(status().isOk());

        // Validate the WipStatusMaster in the database
        List<WipStatusMaster> wipStatusMasterList = wipStatusMasterRepository.findAll();
        assertThat(wipStatusMasterList).hasSize(databaseSizeBeforeUpdate);
        WipStatusMaster testWipStatusMaster = wipStatusMasterList.get(wipStatusMasterList.size() - 1);
        assertThat(testWipStatusMaster.getWipStatusName()).isEqualTo(UPDATED_WIP_STATUS_NAME);
        assertThat(testWipStatusMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testWipStatusMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testWipStatusMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testWipStatusMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testWipStatusMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testWipStatusMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testWipStatusMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testWipStatusMaster.getWipStatusMasterUuid()).isEqualTo(UPDATED_WIP_STATUS_MASTER_UUID);
    }

    @Test
    @Transactional
    void putNonExistingWipStatusMaster() throws Exception {
        int databaseSizeBeforeUpdate = wipStatusMasterRepository.findAll().size();
        wipStatusMaster.setWipStatusId(count.incrementAndGet());

        // Create the WipStatusMaster
        WipStatusMasterDTO wipStatusMasterDTO = wipStatusMasterMapper.toDto(wipStatusMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWipStatusMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, wipStatusMasterDTO.getWipStatusId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(wipStatusMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WipStatusMaster in the database
        List<WipStatusMaster> wipStatusMasterList = wipStatusMasterRepository.findAll();
        assertThat(wipStatusMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchWipStatusMaster() throws Exception {
        int databaseSizeBeforeUpdate = wipStatusMasterRepository.findAll().size();
        wipStatusMaster.setWipStatusId(count.incrementAndGet());

        // Create the WipStatusMaster
        WipStatusMasterDTO wipStatusMasterDTO = wipStatusMasterMapper.toDto(wipStatusMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWipStatusMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(wipStatusMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WipStatusMaster in the database
        List<WipStatusMaster> wipStatusMasterList = wipStatusMasterRepository.findAll();
        assertThat(wipStatusMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamWipStatusMaster() throws Exception {
        int databaseSizeBeforeUpdate = wipStatusMasterRepository.findAll().size();
        wipStatusMaster.setWipStatusId(count.incrementAndGet());

        // Create the WipStatusMaster
        WipStatusMasterDTO wipStatusMasterDTO = wipStatusMasterMapper.toDto(wipStatusMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWipStatusMasterMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(wipStatusMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the WipStatusMaster in the database
        List<WipStatusMaster> wipStatusMasterList = wipStatusMasterRepository.findAll();
        assertThat(wipStatusMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateWipStatusMasterWithPatch() throws Exception {
        // Initialize the database
        wipStatusMasterRepository.saveAndFlush(wipStatusMaster);

        int databaseSizeBeforeUpdate = wipStatusMasterRepository.findAll().size();

        // Update the wipStatusMaster using partial update
        WipStatusMaster partialUpdatedWipStatusMaster = new WipStatusMaster();
        partialUpdatedWipStatusMaster.setWipStatusId(wipStatusMaster.getWipStatusId());

        partialUpdatedWipStatusMaster
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME);

        restWipStatusMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWipStatusMaster.getWipStatusId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedWipStatusMaster))
            )
            .andExpect(status().isOk());

        // Validate the WipStatusMaster in the database
        List<WipStatusMaster> wipStatusMasterList = wipStatusMasterRepository.findAll();
        assertThat(wipStatusMasterList).hasSize(databaseSizeBeforeUpdate);
        WipStatusMaster testWipStatusMaster = wipStatusMasterList.get(wipStatusMasterList.size() - 1);
        assertThat(testWipStatusMaster.getWipStatusName()).isEqualTo(DEFAULT_WIP_STATUS_NAME);
        assertThat(testWipStatusMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testWipStatusMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testWipStatusMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testWipStatusMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testWipStatusMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testWipStatusMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testWipStatusMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testWipStatusMaster.getWipStatusMasterUuid()).isEqualTo(DEFAULT_WIP_STATUS_MASTER_UUID);
    }

    @Test
    @Transactional
    void fullUpdateWipStatusMasterWithPatch() throws Exception {
        // Initialize the database
        wipStatusMasterRepository.saveAndFlush(wipStatusMaster);

        int databaseSizeBeforeUpdate = wipStatusMasterRepository.findAll().size();

        // Update the wipStatusMaster using partial update
        WipStatusMaster partialUpdatedWipStatusMaster = new WipStatusMaster();
        partialUpdatedWipStatusMaster.setWipStatusId(wipStatusMaster.getWipStatusId());

        partialUpdatedWipStatusMaster
            .wipStatusName(UPDATED_WIP_STATUS_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .wipStatusMasterUuid(UPDATED_WIP_STATUS_MASTER_UUID);

        restWipStatusMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWipStatusMaster.getWipStatusId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedWipStatusMaster))
            )
            .andExpect(status().isOk());

        // Validate the WipStatusMaster in the database
        List<WipStatusMaster> wipStatusMasterList = wipStatusMasterRepository.findAll();
        assertThat(wipStatusMasterList).hasSize(databaseSizeBeforeUpdate);
        WipStatusMaster testWipStatusMaster = wipStatusMasterList.get(wipStatusMasterList.size() - 1);
        assertThat(testWipStatusMaster.getWipStatusName()).isEqualTo(UPDATED_WIP_STATUS_NAME);
        assertThat(testWipStatusMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testWipStatusMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testWipStatusMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testWipStatusMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testWipStatusMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testWipStatusMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testWipStatusMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testWipStatusMaster.getWipStatusMasterUuid()).isEqualTo(UPDATED_WIP_STATUS_MASTER_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingWipStatusMaster() throws Exception {
        int databaseSizeBeforeUpdate = wipStatusMasterRepository.findAll().size();
        wipStatusMaster.setWipStatusId(count.incrementAndGet());

        // Create the WipStatusMaster
        WipStatusMasterDTO wipStatusMasterDTO = wipStatusMasterMapper.toDto(wipStatusMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWipStatusMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, wipStatusMasterDTO.getWipStatusId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(wipStatusMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WipStatusMaster in the database
        List<WipStatusMaster> wipStatusMasterList = wipStatusMasterRepository.findAll();
        assertThat(wipStatusMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchWipStatusMaster() throws Exception {
        int databaseSizeBeforeUpdate = wipStatusMasterRepository.findAll().size();
        wipStatusMaster.setWipStatusId(count.incrementAndGet());

        // Create the WipStatusMaster
        WipStatusMasterDTO wipStatusMasterDTO = wipStatusMasterMapper.toDto(wipStatusMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWipStatusMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(wipStatusMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WipStatusMaster in the database
        List<WipStatusMaster> wipStatusMasterList = wipStatusMasterRepository.findAll();
        assertThat(wipStatusMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamWipStatusMaster() throws Exception {
        int databaseSizeBeforeUpdate = wipStatusMasterRepository.findAll().size();
        wipStatusMaster.setWipStatusId(count.incrementAndGet());

        // Create the WipStatusMaster
        WipStatusMasterDTO wipStatusMasterDTO = wipStatusMasterMapper.toDto(wipStatusMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWipStatusMasterMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(wipStatusMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the WipStatusMaster in the database
        List<WipStatusMaster> wipStatusMasterList = wipStatusMasterRepository.findAll();
        assertThat(wipStatusMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteWipStatusMaster() throws Exception {
        // Initialize the database
        wipStatusMasterRepository.saveAndFlush(wipStatusMaster);

        int databaseSizeBeforeDelete = wipStatusMasterRepository.findAll().size();

        // Delete the wipStatusMaster
        restWipStatusMasterMockMvc
            .perform(delete(ENTITY_API_URL_ID, wipStatusMaster.getWipStatusId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WipStatusMaster> wipStatusMasterList = wipStatusMasterRepository.findAll();
        assertThat(wipStatusMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
