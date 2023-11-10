package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.TaskTypeMaster;
import com.sunknowledge.dme.rcm.repository.TaskTypeMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.TaskTypeMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.TaskTypeMasterMapper;
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
 * Integration tests for the {@link TaskTypeMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TaskTypeMasterResourceIT {

    private static final String DEFAULT_TASK_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TASK_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_OBJECT_ID = 1L;
    private static final Long UPDATED_OBJECT_ID = 2L;

    private static final String DEFAULT_OBJECT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_OBJECT_NAME = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_TASK_TYPE_MASTER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_TASK_TYPE_MASTER_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/task-type-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{taskId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TaskTypeMasterRepository taskTypeMasterRepository;

    @Autowired
    private TaskTypeMasterMapper taskTypeMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTaskTypeMasterMockMvc;

    private TaskTypeMaster taskTypeMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaskTypeMaster createEntity(EntityManager em) {
        TaskTypeMaster taskTypeMaster = new TaskTypeMaster()
            .taskName(DEFAULT_TASK_NAME)
            .objectId(DEFAULT_OBJECT_ID)
            .objectName(DEFAULT_OBJECT_NAME)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .taskTypeMasterUuid(DEFAULT_TASK_TYPE_MASTER_UUID);
        return taskTypeMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaskTypeMaster createUpdatedEntity(EntityManager em) {
        TaskTypeMaster taskTypeMaster = new TaskTypeMaster()
            .taskName(UPDATED_TASK_NAME)
            .objectId(UPDATED_OBJECT_ID)
            .objectName(UPDATED_OBJECT_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .taskTypeMasterUuid(UPDATED_TASK_TYPE_MASTER_UUID);
        return taskTypeMaster;
    }

    @BeforeEach
    public void initTest() {
        taskTypeMaster = createEntity(em);
    }

    @Test
    @Transactional
    void createTaskTypeMaster() throws Exception {
        int databaseSizeBeforeCreate = taskTypeMasterRepository.findAll().size();
        // Create the TaskTypeMaster
        TaskTypeMasterDTO taskTypeMasterDTO = taskTypeMasterMapper.toDto(taskTypeMaster);
        restTaskTypeMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taskTypeMasterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TaskTypeMaster in the database
        List<TaskTypeMaster> taskTypeMasterList = taskTypeMasterRepository.findAll();
        assertThat(taskTypeMasterList).hasSize(databaseSizeBeforeCreate + 1);
        TaskTypeMaster testTaskTypeMaster = taskTypeMasterList.get(taskTypeMasterList.size() - 1);
        assertThat(testTaskTypeMaster.getTaskName()).isEqualTo(DEFAULT_TASK_NAME);
        assertThat(testTaskTypeMaster.getObjectId()).isEqualTo(DEFAULT_OBJECT_ID);
        assertThat(testTaskTypeMaster.getObjectName()).isEqualTo(DEFAULT_OBJECT_NAME);
        assertThat(testTaskTypeMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testTaskTypeMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testTaskTypeMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testTaskTypeMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testTaskTypeMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testTaskTypeMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testTaskTypeMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testTaskTypeMaster.getTaskTypeMasterUuid()).isEqualTo(DEFAULT_TASK_TYPE_MASTER_UUID);
    }

    @Test
    @Transactional
    void createTaskTypeMasterWithExistingId() throws Exception {
        // Create the TaskTypeMaster with an existing ID
        taskTypeMaster.setTaskId(1L);
        TaskTypeMasterDTO taskTypeMasterDTO = taskTypeMasterMapper.toDto(taskTypeMaster);

        int databaseSizeBeforeCreate = taskTypeMasterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTaskTypeMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taskTypeMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaskTypeMaster in the database
        List<TaskTypeMaster> taskTypeMasterList = taskTypeMasterRepository.findAll();
        assertThat(taskTypeMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTaskTypeMasters() throws Exception {
        // Initialize the database
        taskTypeMasterRepository.saveAndFlush(taskTypeMaster);

        // Get all the taskTypeMasterList
        restTaskTypeMasterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=taskId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].taskId").value(hasItem(taskTypeMaster.getTaskId().intValue())))
            .andExpect(jsonPath("$.[*].taskName").value(hasItem(DEFAULT_TASK_NAME)))
            .andExpect(jsonPath("$.[*].objectId").value(hasItem(DEFAULT_OBJECT_ID.intValue())))
            .andExpect(jsonPath("$.[*].objectName").value(hasItem(DEFAULT_OBJECT_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].taskTypeMasterUuid").value(hasItem(DEFAULT_TASK_TYPE_MASTER_UUID.toString())));
    }

    @Test
    @Transactional
    void getTaskTypeMaster() throws Exception {
        // Initialize the database
        taskTypeMasterRepository.saveAndFlush(taskTypeMaster);

        // Get the taskTypeMaster
        restTaskTypeMasterMockMvc
            .perform(get(ENTITY_API_URL_ID, taskTypeMaster.getTaskId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.taskId").value(taskTypeMaster.getTaskId().intValue()))
            .andExpect(jsonPath("$.taskName").value(DEFAULT_TASK_NAME))
            .andExpect(jsonPath("$.objectId").value(DEFAULT_OBJECT_ID.intValue()))
            .andExpect(jsonPath("$.objectName").value(DEFAULT_OBJECT_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.taskTypeMasterUuid").value(DEFAULT_TASK_TYPE_MASTER_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingTaskTypeMaster() throws Exception {
        // Get the taskTypeMaster
        restTaskTypeMasterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTaskTypeMaster() throws Exception {
        // Initialize the database
        taskTypeMasterRepository.saveAndFlush(taskTypeMaster);

        int databaseSizeBeforeUpdate = taskTypeMasterRepository.findAll().size();

        // Update the taskTypeMaster
        TaskTypeMaster updatedTaskTypeMaster = taskTypeMasterRepository.findById(taskTypeMaster.getTaskId()).get();
        // Disconnect from session so that the updates on updatedTaskTypeMaster are not directly saved in db
        em.detach(updatedTaskTypeMaster);
        updatedTaskTypeMaster
            .taskName(UPDATED_TASK_NAME)
            .objectId(UPDATED_OBJECT_ID)
            .objectName(UPDATED_OBJECT_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .taskTypeMasterUuid(UPDATED_TASK_TYPE_MASTER_UUID);
        TaskTypeMasterDTO taskTypeMasterDTO = taskTypeMasterMapper.toDto(updatedTaskTypeMaster);

        restTaskTypeMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, taskTypeMasterDTO.getTaskId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taskTypeMasterDTO))
            )
            .andExpect(status().isOk());

        // Validate the TaskTypeMaster in the database
        List<TaskTypeMaster> taskTypeMasterList = taskTypeMasterRepository.findAll();
        assertThat(taskTypeMasterList).hasSize(databaseSizeBeforeUpdate);
        TaskTypeMaster testTaskTypeMaster = taskTypeMasterList.get(taskTypeMasterList.size() - 1);
        assertThat(testTaskTypeMaster.getTaskName()).isEqualTo(UPDATED_TASK_NAME);
        assertThat(testTaskTypeMaster.getObjectId()).isEqualTo(UPDATED_OBJECT_ID);
        assertThat(testTaskTypeMaster.getObjectName()).isEqualTo(UPDATED_OBJECT_NAME);
        assertThat(testTaskTypeMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testTaskTypeMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testTaskTypeMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testTaskTypeMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testTaskTypeMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testTaskTypeMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testTaskTypeMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testTaskTypeMaster.getTaskTypeMasterUuid()).isEqualTo(UPDATED_TASK_TYPE_MASTER_UUID);
    }

    @Test
    @Transactional
    void putNonExistingTaskTypeMaster() throws Exception {
        int databaseSizeBeforeUpdate = taskTypeMasterRepository.findAll().size();
        taskTypeMaster.setTaskId(count.incrementAndGet());

        // Create the TaskTypeMaster
        TaskTypeMasterDTO taskTypeMasterDTO = taskTypeMasterMapper.toDto(taskTypeMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaskTypeMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, taskTypeMasterDTO.getTaskId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taskTypeMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaskTypeMaster in the database
        List<TaskTypeMaster> taskTypeMasterList = taskTypeMasterRepository.findAll();
        assertThat(taskTypeMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTaskTypeMaster() throws Exception {
        int databaseSizeBeforeUpdate = taskTypeMasterRepository.findAll().size();
        taskTypeMaster.setTaskId(count.incrementAndGet());

        // Create the TaskTypeMaster
        TaskTypeMasterDTO taskTypeMasterDTO = taskTypeMasterMapper.toDto(taskTypeMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaskTypeMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taskTypeMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaskTypeMaster in the database
        List<TaskTypeMaster> taskTypeMasterList = taskTypeMasterRepository.findAll();
        assertThat(taskTypeMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTaskTypeMaster() throws Exception {
        int databaseSizeBeforeUpdate = taskTypeMasterRepository.findAll().size();
        taskTypeMaster.setTaskId(count.incrementAndGet());

        // Create the TaskTypeMaster
        TaskTypeMasterDTO taskTypeMasterDTO = taskTypeMasterMapper.toDto(taskTypeMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaskTypeMasterMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taskTypeMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TaskTypeMaster in the database
        List<TaskTypeMaster> taskTypeMasterList = taskTypeMasterRepository.findAll();
        assertThat(taskTypeMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTaskTypeMasterWithPatch() throws Exception {
        // Initialize the database
        taskTypeMasterRepository.saveAndFlush(taskTypeMaster);

        int databaseSizeBeforeUpdate = taskTypeMasterRepository.findAll().size();

        // Update the taskTypeMaster using partial update
        TaskTypeMaster partialUpdatedTaskTypeMaster = new TaskTypeMaster();
        partialUpdatedTaskTypeMaster.setTaskId(taskTypeMaster.getTaskId());

        partialUpdatedTaskTypeMaster
            .taskName(UPDATED_TASK_NAME)
            .objectId(UPDATED_OBJECT_ID)
            .objectName(UPDATED_OBJECT_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .taskTypeMasterUuid(UPDATED_TASK_TYPE_MASTER_UUID);

        restTaskTypeMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaskTypeMaster.getTaskId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTaskTypeMaster))
            )
            .andExpect(status().isOk());

        // Validate the TaskTypeMaster in the database
        List<TaskTypeMaster> taskTypeMasterList = taskTypeMasterRepository.findAll();
        assertThat(taskTypeMasterList).hasSize(databaseSizeBeforeUpdate);
        TaskTypeMaster testTaskTypeMaster = taskTypeMasterList.get(taskTypeMasterList.size() - 1);
        assertThat(testTaskTypeMaster.getTaskName()).isEqualTo(UPDATED_TASK_NAME);
        assertThat(testTaskTypeMaster.getObjectId()).isEqualTo(UPDATED_OBJECT_ID);
        assertThat(testTaskTypeMaster.getObjectName()).isEqualTo(UPDATED_OBJECT_NAME);
        assertThat(testTaskTypeMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testTaskTypeMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testTaskTypeMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testTaskTypeMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testTaskTypeMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testTaskTypeMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testTaskTypeMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testTaskTypeMaster.getTaskTypeMasterUuid()).isEqualTo(UPDATED_TASK_TYPE_MASTER_UUID);
    }

    @Test
    @Transactional
    void fullUpdateTaskTypeMasterWithPatch() throws Exception {
        // Initialize the database
        taskTypeMasterRepository.saveAndFlush(taskTypeMaster);

        int databaseSizeBeforeUpdate = taskTypeMasterRepository.findAll().size();

        // Update the taskTypeMaster using partial update
        TaskTypeMaster partialUpdatedTaskTypeMaster = new TaskTypeMaster();
        partialUpdatedTaskTypeMaster.setTaskId(taskTypeMaster.getTaskId());

        partialUpdatedTaskTypeMaster
            .taskName(UPDATED_TASK_NAME)
            .objectId(UPDATED_OBJECT_ID)
            .objectName(UPDATED_OBJECT_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .taskTypeMasterUuid(UPDATED_TASK_TYPE_MASTER_UUID);

        restTaskTypeMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaskTypeMaster.getTaskId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTaskTypeMaster))
            )
            .andExpect(status().isOk());

        // Validate the TaskTypeMaster in the database
        List<TaskTypeMaster> taskTypeMasterList = taskTypeMasterRepository.findAll();
        assertThat(taskTypeMasterList).hasSize(databaseSizeBeforeUpdate);
        TaskTypeMaster testTaskTypeMaster = taskTypeMasterList.get(taskTypeMasterList.size() - 1);
        assertThat(testTaskTypeMaster.getTaskName()).isEqualTo(UPDATED_TASK_NAME);
        assertThat(testTaskTypeMaster.getObjectId()).isEqualTo(UPDATED_OBJECT_ID);
        assertThat(testTaskTypeMaster.getObjectName()).isEqualTo(UPDATED_OBJECT_NAME);
        assertThat(testTaskTypeMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testTaskTypeMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testTaskTypeMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testTaskTypeMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testTaskTypeMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testTaskTypeMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testTaskTypeMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testTaskTypeMaster.getTaskTypeMasterUuid()).isEqualTo(UPDATED_TASK_TYPE_MASTER_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingTaskTypeMaster() throws Exception {
        int databaseSizeBeforeUpdate = taskTypeMasterRepository.findAll().size();
        taskTypeMaster.setTaskId(count.incrementAndGet());

        // Create the TaskTypeMaster
        TaskTypeMasterDTO taskTypeMasterDTO = taskTypeMasterMapper.toDto(taskTypeMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaskTypeMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, taskTypeMasterDTO.getTaskId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(taskTypeMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaskTypeMaster in the database
        List<TaskTypeMaster> taskTypeMasterList = taskTypeMasterRepository.findAll();
        assertThat(taskTypeMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTaskTypeMaster() throws Exception {
        int databaseSizeBeforeUpdate = taskTypeMasterRepository.findAll().size();
        taskTypeMaster.setTaskId(count.incrementAndGet());

        // Create the TaskTypeMaster
        TaskTypeMasterDTO taskTypeMasterDTO = taskTypeMasterMapper.toDto(taskTypeMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaskTypeMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(taskTypeMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaskTypeMaster in the database
        List<TaskTypeMaster> taskTypeMasterList = taskTypeMasterRepository.findAll();
        assertThat(taskTypeMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTaskTypeMaster() throws Exception {
        int databaseSizeBeforeUpdate = taskTypeMasterRepository.findAll().size();
        taskTypeMaster.setTaskId(count.incrementAndGet());

        // Create the TaskTypeMaster
        TaskTypeMasterDTO taskTypeMasterDTO = taskTypeMasterMapper.toDto(taskTypeMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaskTypeMasterMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(taskTypeMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TaskTypeMaster in the database
        List<TaskTypeMaster> taskTypeMasterList = taskTypeMasterRepository.findAll();
        assertThat(taskTypeMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTaskTypeMaster() throws Exception {
        // Initialize the database
        taskTypeMasterRepository.saveAndFlush(taskTypeMaster);

        int databaseSizeBeforeDelete = taskTypeMasterRepository.findAll().size();

        // Delete the taskTypeMaster
        restTaskTypeMasterMockMvc
            .perform(delete(ENTITY_API_URL_ID, taskTypeMaster.getTaskId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TaskTypeMaster> taskTypeMasterList = taskTypeMasterRepository.findAll();
        assertThat(taskTypeMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
