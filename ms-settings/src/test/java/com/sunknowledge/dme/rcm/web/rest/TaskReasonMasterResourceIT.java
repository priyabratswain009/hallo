package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.TaskReasonMaster;
import com.sunknowledge.dme.rcm.repository.TaskReasonMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.TaskReasonMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.TaskReasonMasterMapper;
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
 * Integration tests for the {@link TaskReasonMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TaskReasonMasterResourceIT {

    private static final String DEFAULT_TASK_REASON_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TASK_REASON_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final UUID DEFAULT_TASK_REASON_MASTER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_TASK_REASON_MASTER_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/task-reason-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{taskReasonId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TaskReasonMasterRepository taskReasonMasterRepository;

    @Autowired
    private TaskReasonMasterMapper taskReasonMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTaskReasonMasterMockMvc;

    private TaskReasonMaster taskReasonMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaskReasonMaster createEntity(EntityManager em) {
        TaskReasonMaster taskReasonMaster = new TaskReasonMaster()
            .taskReasonName(DEFAULT_TASK_REASON_NAME)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .taskReasonMasterUuid(DEFAULT_TASK_REASON_MASTER_UUID);
        return taskReasonMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaskReasonMaster createUpdatedEntity(EntityManager em) {
        TaskReasonMaster taskReasonMaster = new TaskReasonMaster()
            .taskReasonName(UPDATED_TASK_REASON_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .taskReasonMasterUuid(UPDATED_TASK_REASON_MASTER_UUID);
        return taskReasonMaster;
    }

    @BeforeEach
    public void initTest() {
        taskReasonMaster = createEntity(em);
    }

    @Test
    @Transactional
    void createTaskReasonMaster() throws Exception {
        int databaseSizeBeforeCreate = taskReasonMasterRepository.findAll().size();
        // Create the TaskReasonMaster
        TaskReasonMasterDTO taskReasonMasterDTO = taskReasonMasterMapper.toDto(taskReasonMaster);
        restTaskReasonMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taskReasonMasterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TaskReasonMaster in the database
        List<TaskReasonMaster> taskReasonMasterList = taskReasonMasterRepository.findAll();
        assertThat(taskReasonMasterList).hasSize(databaseSizeBeforeCreate + 1);
        TaskReasonMaster testTaskReasonMaster = taskReasonMasterList.get(taskReasonMasterList.size() - 1);
        assertThat(testTaskReasonMaster.getTaskReasonName()).isEqualTo(DEFAULT_TASK_REASON_NAME);
        assertThat(testTaskReasonMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testTaskReasonMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testTaskReasonMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testTaskReasonMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testTaskReasonMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testTaskReasonMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testTaskReasonMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testTaskReasonMaster.getTaskReasonMasterUuid()).isEqualTo(DEFAULT_TASK_REASON_MASTER_UUID);
    }

    @Test
    @Transactional
    void createTaskReasonMasterWithExistingId() throws Exception {
        // Create the TaskReasonMaster with an existing ID
        taskReasonMaster.setTaskReasonId(1L);
        TaskReasonMasterDTO taskReasonMasterDTO = taskReasonMasterMapper.toDto(taskReasonMaster);

        int databaseSizeBeforeCreate = taskReasonMasterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTaskReasonMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taskReasonMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaskReasonMaster in the database
        List<TaskReasonMaster> taskReasonMasterList = taskReasonMasterRepository.findAll();
        assertThat(taskReasonMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTaskReasonMasters() throws Exception {
        // Initialize the database
        taskReasonMasterRepository.saveAndFlush(taskReasonMaster);

        // Get all the taskReasonMasterList
        restTaskReasonMasterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=taskReasonId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].taskReasonId").value(hasItem(taskReasonMaster.getTaskReasonId().intValue())))
            .andExpect(jsonPath("$.[*].taskReasonName").value(hasItem(DEFAULT_TASK_REASON_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].taskReasonMasterUuid").value(hasItem(DEFAULT_TASK_REASON_MASTER_UUID.toString())));
    }

    @Test
    @Transactional
    void getTaskReasonMaster() throws Exception {
        // Initialize the database
        taskReasonMasterRepository.saveAndFlush(taskReasonMaster);

        // Get the taskReasonMaster
        restTaskReasonMasterMockMvc
            .perform(get(ENTITY_API_URL_ID, taskReasonMaster.getTaskReasonId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.taskReasonId").value(taskReasonMaster.getTaskReasonId().intValue()))
            .andExpect(jsonPath("$.taskReasonName").value(DEFAULT_TASK_REASON_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.taskReasonMasterUuid").value(DEFAULT_TASK_REASON_MASTER_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingTaskReasonMaster() throws Exception {
        // Get the taskReasonMaster
        restTaskReasonMasterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTaskReasonMaster() throws Exception {
        // Initialize the database
        taskReasonMasterRepository.saveAndFlush(taskReasonMaster);

        int databaseSizeBeforeUpdate = taskReasonMasterRepository.findAll().size();

        // Update the taskReasonMaster
        TaskReasonMaster updatedTaskReasonMaster = taskReasonMasterRepository.findById(taskReasonMaster.getTaskReasonId()).get();
        // Disconnect from session so that the updates on updatedTaskReasonMaster are not directly saved in db
        em.detach(updatedTaskReasonMaster);
        updatedTaskReasonMaster
            .taskReasonName(UPDATED_TASK_REASON_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .taskReasonMasterUuid(UPDATED_TASK_REASON_MASTER_UUID);
        TaskReasonMasterDTO taskReasonMasterDTO = taskReasonMasterMapper.toDto(updatedTaskReasonMaster);

        restTaskReasonMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, taskReasonMasterDTO.getTaskReasonId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taskReasonMasterDTO))
            )
            .andExpect(status().isOk());

        // Validate the TaskReasonMaster in the database
        List<TaskReasonMaster> taskReasonMasterList = taskReasonMasterRepository.findAll();
        assertThat(taskReasonMasterList).hasSize(databaseSizeBeforeUpdate);
        TaskReasonMaster testTaskReasonMaster = taskReasonMasterList.get(taskReasonMasterList.size() - 1);
        assertThat(testTaskReasonMaster.getTaskReasonName()).isEqualTo(UPDATED_TASK_REASON_NAME);
        assertThat(testTaskReasonMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testTaskReasonMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testTaskReasonMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testTaskReasonMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testTaskReasonMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testTaskReasonMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testTaskReasonMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testTaskReasonMaster.getTaskReasonMasterUuid()).isEqualTo(UPDATED_TASK_REASON_MASTER_UUID);
    }

    @Test
    @Transactional
    void putNonExistingTaskReasonMaster() throws Exception {
        int databaseSizeBeforeUpdate = taskReasonMasterRepository.findAll().size();
        taskReasonMaster.setTaskReasonId(count.incrementAndGet());

        // Create the TaskReasonMaster
        TaskReasonMasterDTO taskReasonMasterDTO = taskReasonMasterMapper.toDto(taskReasonMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaskReasonMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, taskReasonMasterDTO.getTaskReasonId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taskReasonMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaskReasonMaster in the database
        List<TaskReasonMaster> taskReasonMasterList = taskReasonMasterRepository.findAll();
        assertThat(taskReasonMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTaskReasonMaster() throws Exception {
        int databaseSizeBeforeUpdate = taskReasonMasterRepository.findAll().size();
        taskReasonMaster.setTaskReasonId(count.incrementAndGet());

        // Create the TaskReasonMaster
        TaskReasonMasterDTO taskReasonMasterDTO = taskReasonMasterMapper.toDto(taskReasonMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaskReasonMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taskReasonMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaskReasonMaster in the database
        List<TaskReasonMaster> taskReasonMasterList = taskReasonMasterRepository.findAll();
        assertThat(taskReasonMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTaskReasonMaster() throws Exception {
        int databaseSizeBeforeUpdate = taskReasonMasterRepository.findAll().size();
        taskReasonMaster.setTaskReasonId(count.incrementAndGet());

        // Create the TaskReasonMaster
        TaskReasonMasterDTO taskReasonMasterDTO = taskReasonMasterMapper.toDto(taskReasonMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaskReasonMasterMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taskReasonMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TaskReasonMaster in the database
        List<TaskReasonMaster> taskReasonMasterList = taskReasonMasterRepository.findAll();
        assertThat(taskReasonMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTaskReasonMasterWithPatch() throws Exception {
        // Initialize the database
        taskReasonMasterRepository.saveAndFlush(taskReasonMaster);

        int databaseSizeBeforeUpdate = taskReasonMasterRepository.findAll().size();

        // Update the taskReasonMaster using partial update
        TaskReasonMaster partialUpdatedTaskReasonMaster = new TaskReasonMaster();
        partialUpdatedTaskReasonMaster.setTaskReasonId(taskReasonMaster.getTaskReasonId());

        partialUpdatedTaskReasonMaster
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME);

        restTaskReasonMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaskReasonMaster.getTaskReasonId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTaskReasonMaster))
            )
            .andExpect(status().isOk());

        // Validate the TaskReasonMaster in the database
        List<TaskReasonMaster> taskReasonMasterList = taskReasonMasterRepository.findAll();
        assertThat(taskReasonMasterList).hasSize(databaseSizeBeforeUpdate);
        TaskReasonMaster testTaskReasonMaster = taskReasonMasterList.get(taskReasonMasterList.size() - 1);
        assertThat(testTaskReasonMaster.getTaskReasonName()).isEqualTo(DEFAULT_TASK_REASON_NAME);
        assertThat(testTaskReasonMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testTaskReasonMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testTaskReasonMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testTaskReasonMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testTaskReasonMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testTaskReasonMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testTaskReasonMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testTaskReasonMaster.getTaskReasonMasterUuid()).isEqualTo(DEFAULT_TASK_REASON_MASTER_UUID);
    }

    @Test
    @Transactional
    void fullUpdateTaskReasonMasterWithPatch() throws Exception {
        // Initialize the database
        taskReasonMasterRepository.saveAndFlush(taskReasonMaster);

        int databaseSizeBeforeUpdate = taskReasonMasterRepository.findAll().size();

        // Update the taskReasonMaster using partial update
        TaskReasonMaster partialUpdatedTaskReasonMaster = new TaskReasonMaster();
        partialUpdatedTaskReasonMaster.setTaskReasonId(taskReasonMaster.getTaskReasonId());

        partialUpdatedTaskReasonMaster
            .taskReasonName(UPDATED_TASK_REASON_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .taskReasonMasterUuid(UPDATED_TASK_REASON_MASTER_UUID);

        restTaskReasonMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaskReasonMaster.getTaskReasonId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTaskReasonMaster))
            )
            .andExpect(status().isOk());

        // Validate the TaskReasonMaster in the database
        List<TaskReasonMaster> taskReasonMasterList = taskReasonMasterRepository.findAll();
        assertThat(taskReasonMasterList).hasSize(databaseSizeBeforeUpdate);
        TaskReasonMaster testTaskReasonMaster = taskReasonMasterList.get(taskReasonMasterList.size() - 1);
        assertThat(testTaskReasonMaster.getTaskReasonName()).isEqualTo(UPDATED_TASK_REASON_NAME);
        assertThat(testTaskReasonMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testTaskReasonMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testTaskReasonMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testTaskReasonMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testTaskReasonMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testTaskReasonMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testTaskReasonMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testTaskReasonMaster.getTaskReasonMasterUuid()).isEqualTo(UPDATED_TASK_REASON_MASTER_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingTaskReasonMaster() throws Exception {
        int databaseSizeBeforeUpdate = taskReasonMasterRepository.findAll().size();
        taskReasonMaster.setTaskReasonId(count.incrementAndGet());

        // Create the TaskReasonMaster
        TaskReasonMasterDTO taskReasonMasterDTO = taskReasonMasterMapper.toDto(taskReasonMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaskReasonMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, taskReasonMasterDTO.getTaskReasonId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(taskReasonMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaskReasonMaster in the database
        List<TaskReasonMaster> taskReasonMasterList = taskReasonMasterRepository.findAll();
        assertThat(taskReasonMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTaskReasonMaster() throws Exception {
        int databaseSizeBeforeUpdate = taskReasonMasterRepository.findAll().size();
        taskReasonMaster.setTaskReasonId(count.incrementAndGet());

        // Create the TaskReasonMaster
        TaskReasonMasterDTO taskReasonMasterDTO = taskReasonMasterMapper.toDto(taskReasonMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaskReasonMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(taskReasonMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaskReasonMaster in the database
        List<TaskReasonMaster> taskReasonMasterList = taskReasonMasterRepository.findAll();
        assertThat(taskReasonMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTaskReasonMaster() throws Exception {
        int databaseSizeBeforeUpdate = taskReasonMasterRepository.findAll().size();
        taskReasonMaster.setTaskReasonId(count.incrementAndGet());

        // Create the TaskReasonMaster
        TaskReasonMasterDTO taskReasonMasterDTO = taskReasonMasterMapper.toDto(taskReasonMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaskReasonMasterMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(taskReasonMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TaskReasonMaster in the database
        List<TaskReasonMaster> taskReasonMasterList = taskReasonMasterRepository.findAll();
        assertThat(taskReasonMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTaskReasonMaster() throws Exception {
        // Initialize the database
        taskReasonMasterRepository.saveAndFlush(taskReasonMaster);

        int databaseSizeBeforeDelete = taskReasonMasterRepository.findAll().size();

        // Delete the taskReasonMaster
        restTaskReasonMasterMockMvc
            .perform(delete(ENTITY_API_URL_ID, taskReasonMaster.getTaskReasonId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TaskReasonMaster> taskReasonMasterList = taskReasonMasterRepository.findAll();
        assertThat(taskReasonMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
