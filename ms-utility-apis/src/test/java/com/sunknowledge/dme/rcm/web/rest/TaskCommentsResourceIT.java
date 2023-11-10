package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.TaskComments;
import com.sunknowledge.dme.rcm.repository.TaskCommentsRepository;
import com.sunknowledge.dme.rcm.service.dto.TaskCommentsDTO;
import com.sunknowledge.dme.rcm.service.mapper.TaskCommentsMapper;
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
 * Integration tests for the {@link TaskCommentsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TaskCommentsResourceIT {

    private static final String DEFAULT_COMMENT_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT_TEXT = "BBBBBBBBBB";

    private static final Long DEFAULT_COMMENT_BY_ID = 1L;
    private static final Long UPDATED_COMMENT_BY_ID = 2L;

    private static final LocalDate DEFAULT_COMMENT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_COMMENT_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_COMMENT_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT_BY_NAME = "BBBBBBBBBB";

    private static final UUID DEFAULT_TASK_DETAILS_UUID = UUID.randomUUID();
    private static final UUID UPDATED_TASK_DETAILS_UUID = UUID.randomUUID();

    private static final Long DEFAULT_TASK_DETAILS_ID = 1L;
    private static final Long UPDATED_TASK_DETAILS_ID = 2L;

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

    private static final UUID DEFAULT_TASK_COMMENTS_UUID = UUID.randomUUID();
    private static final UUID UPDATED_TASK_COMMENTS_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/task-comments";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{taskCommentId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TaskCommentsRepository taskCommentsRepository;

    @Autowired
    private TaskCommentsMapper taskCommentsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTaskCommentsMockMvc;

    private TaskComments taskComments;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaskComments createEntity(EntityManager em) {
        TaskComments taskComments = new TaskComments()
            .commentText(DEFAULT_COMMENT_TEXT)
            .commentById(DEFAULT_COMMENT_BY_ID)
            .commentDate(DEFAULT_COMMENT_DATE)
            .commentByName(DEFAULT_COMMENT_BY_NAME)
            .taskDetailsUuid(DEFAULT_TASK_DETAILS_UUID)
            .taskDetailsId(DEFAULT_TASK_DETAILS_ID)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .taskCommentsUuid(DEFAULT_TASK_COMMENTS_UUID);
        return taskComments;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaskComments createUpdatedEntity(EntityManager em) {
        TaskComments taskComments = new TaskComments()
            .commentText(UPDATED_COMMENT_TEXT)
            .commentById(UPDATED_COMMENT_BY_ID)
            .commentDate(UPDATED_COMMENT_DATE)
            .commentByName(UPDATED_COMMENT_BY_NAME)
            .taskDetailsUuid(UPDATED_TASK_DETAILS_UUID)
            .taskDetailsId(UPDATED_TASK_DETAILS_ID)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .taskCommentsUuid(UPDATED_TASK_COMMENTS_UUID);
        return taskComments;
    }

    @BeforeEach
    public void initTest() {
        taskComments = createEntity(em);
    }

    @Test
    @Transactional
    void createTaskComments() throws Exception {
        int databaseSizeBeforeCreate = taskCommentsRepository.findAll().size();
        // Create the TaskComments
        TaskCommentsDTO taskCommentsDTO = taskCommentsMapper.toDto(taskComments);
        restTaskCommentsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taskCommentsDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TaskComments in the database
        List<TaskComments> taskCommentsList = taskCommentsRepository.findAll();
        assertThat(taskCommentsList).hasSize(databaseSizeBeforeCreate + 1);
        TaskComments testTaskComments = taskCommentsList.get(taskCommentsList.size() - 1);
        assertThat(testTaskComments.getCommentText()).isEqualTo(DEFAULT_COMMENT_TEXT);
        assertThat(testTaskComments.getCommentById()).isEqualTo(DEFAULT_COMMENT_BY_ID);
        assertThat(testTaskComments.getCommentDate()).isEqualTo(DEFAULT_COMMENT_DATE);
        assertThat(testTaskComments.getCommentByName()).isEqualTo(DEFAULT_COMMENT_BY_NAME);
        assertThat(testTaskComments.getTaskDetailsUuid()).isEqualTo(DEFAULT_TASK_DETAILS_UUID);
        assertThat(testTaskComments.getTaskDetailsId()).isEqualTo(DEFAULT_TASK_DETAILS_ID);
        assertThat(testTaskComments.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testTaskComments.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testTaskComments.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testTaskComments.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testTaskComments.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testTaskComments.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testTaskComments.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testTaskComments.getTaskCommentsUuid()).isEqualTo(DEFAULT_TASK_COMMENTS_UUID);
    }

    @Test
    @Transactional
    void createTaskCommentsWithExistingId() throws Exception {
        // Create the TaskComments with an existing ID
        taskComments.setTaskCommentId(1L);
        TaskCommentsDTO taskCommentsDTO = taskCommentsMapper.toDto(taskComments);

        int databaseSizeBeforeCreate = taskCommentsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTaskCommentsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taskCommentsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaskComments in the database
        List<TaskComments> taskCommentsList = taskCommentsRepository.findAll();
        assertThat(taskCommentsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTaskComments() throws Exception {
        // Initialize the database
        taskCommentsRepository.saveAndFlush(taskComments);

        // Get all the taskCommentsList
        restTaskCommentsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=taskCommentId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].taskCommentId").value(hasItem(taskComments.getTaskCommentId().intValue())))
            .andExpect(jsonPath("$.[*].commentText").value(hasItem(DEFAULT_COMMENT_TEXT)))
            .andExpect(jsonPath("$.[*].commentById").value(hasItem(DEFAULT_COMMENT_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].commentDate").value(hasItem(DEFAULT_COMMENT_DATE.toString())))
            .andExpect(jsonPath("$.[*].commentByName").value(hasItem(DEFAULT_COMMENT_BY_NAME)))
            .andExpect(jsonPath("$.[*].taskDetailsUuid").value(hasItem(DEFAULT_TASK_DETAILS_UUID.toString())))
            .andExpect(jsonPath("$.[*].taskDetailsId").value(hasItem(DEFAULT_TASK_DETAILS_ID.intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].taskCommentsUuid").value(hasItem(DEFAULT_TASK_COMMENTS_UUID.toString())));
    }

    @Test
    @Transactional
    void getTaskComments() throws Exception {
        // Initialize the database
        taskCommentsRepository.saveAndFlush(taskComments);

        // Get the taskComments
        restTaskCommentsMockMvc
            .perform(get(ENTITY_API_URL_ID, taskComments.getTaskCommentId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.taskCommentId").value(taskComments.getTaskCommentId().intValue()))
            .andExpect(jsonPath("$.commentText").value(DEFAULT_COMMENT_TEXT))
            .andExpect(jsonPath("$.commentById").value(DEFAULT_COMMENT_BY_ID.intValue()))
            .andExpect(jsonPath("$.commentDate").value(DEFAULT_COMMENT_DATE.toString()))
            .andExpect(jsonPath("$.commentByName").value(DEFAULT_COMMENT_BY_NAME))
            .andExpect(jsonPath("$.taskDetailsUuid").value(DEFAULT_TASK_DETAILS_UUID.toString()))
            .andExpect(jsonPath("$.taskDetailsId").value(DEFAULT_TASK_DETAILS_ID.intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.taskCommentsUuid").value(DEFAULT_TASK_COMMENTS_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingTaskComments() throws Exception {
        // Get the taskComments
        restTaskCommentsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTaskComments() throws Exception {
        // Initialize the database
        taskCommentsRepository.saveAndFlush(taskComments);

        int databaseSizeBeforeUpdate = taskCommentsRepository.findAll().size();

        // Update the taskComments
        TaskComments updatedTaskComments = taskCommentsRepository.findById(taskComments.getTaskCommentId()).get();
        // Disconnect from session so that the updates on updatedTaskComments are not directly saved in db
        em.detach(updatedTaskComments);
        updatedTaskComments
            .commentText(UPDATED_COMMENT_TEXT)
            .commentById(UPDATED_COMMENT_BY_ID)
            .commentDate(UPDATED_COMMENT_DATE)
            .commentByName(UPDATED_COMMENT_BY_NAME)
            .taskDetailsUuid(UPDATED_TASK_DETAILS_UUID)
            .taskDetailsId(UPDATED_TASK_DETAILS_ID)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .taskCommentsUuid(UPDATED_TASK_COMMENTS_UUID);
        TaskCommentsDTO taskCommentsDTO = taskCommentsMapper.toDto(updatedTaskComments);

        restTaskCommentsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, taskCommentsDTO.getTaskCommentId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taskCommentsDTO))
            )
            .andExpect(status().isOk());

        // Validate the TaskComments in the database
        List<TaskComments> taskCommentsList = taskCommentsRepository.findAll();
        assertThat(taskCommentsList).hasSize(databaseSizeBeforeUpdate);
        TaskComments testTaskComments = taskCommentsList.get(taskCommentsList.size() - 1);
        assertThat(testTaskComments.getCommentText()).isEqualTo(UPDATED_COMMENT_TEXT);
        assertThat(testTaskComments.getCommentById()).isEqualTo(UPDATED_COMMENT_BY_ID);
        assertThat(testTaskComments.getCommentDate()).isEqualTo(UPDATED_COMMENT_DATE);
        assertThat(testTaskComments.getCommentByName()).isEqualTo(UPDATED_COMMENT_BY_NAME);
        assertThat(testTaskComments.getTaskDetailsUuid()).isEqualTo(UPDATED_TASK_DETAILS_UUID);
        assertThat(testTaskComments.getTaskDetailsId()).isEqualTo(UPDATED_TASK_DETAILS_ID);
        assertThat(testTaskComments.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testTaskComments.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testTaskComments.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testTaskComments.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testTaskComments.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testTaskComments.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testTaskComments.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testTaskComments.getTaskCommentsUuid()).isEqualTo(UPDATED_TASK_COMMENTS_UUID);
    }

    @Test
    @Transactional
    void putNonExistingTaskComments() throws Exception {
        int databaseSizeBeforeUpdate = taskCommentsRepository.findAll().size();
        taskComments.setTaskCommentId(count.incrementAndGet());

        // Create the TaskComments
        TaskCommentsDTO taskCommentsDTO = taskCommentsMapper.toDto(taskComments);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaskCommentsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, taskCommentsDTO.getTaskCommentId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taskCommentsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaskComments in the database
        List<TaskComments> taskCommentsList = taskCommentsRepository.findAll();
        assertThat(taskCommentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTaskComments() throws Exception {
        int databaseSizeBeforeUpdate = taskCommentsRepository.findAll().size();
        taskComments.setTaskCommentId(count.incrementAndGet());

        // Create the TaskComments
        TaskCommentsDTO taskCommentsDTO = taskCommentsMapper.toDto(taskComments);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaskCommentsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taskCommentsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaskComments in the database
        List<TaskComments> taskCommentsList = taskCommentsRepository.findAll();
        assertThat(taskCommentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTaskComments() throws Exception {
        int databaseSizeBeforeUpdate = taskCommentsRepository.findAll().size();
        taskComments.setTaskCommentId(count.incrementAndGet());

        // Create the TaskComments
        TaskCommentsDTO taskCommentsDTO = taskCommentsMapper.toDto(taskComments);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaskCommentsMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taskCommentsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TaskComments in the database
        List<TaskComments> taskCommentsList = taskCommentsRepository.findAll();
        assertThat(taskCommentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTaskCommentsWithPatch() throws Exception {
        // Initialize the database
        taskCommentsRepository.saveAndFlush(taskComments);

        int databaseSizeBeforeUpdate = taskCommentsRepository.findAll().size();

        // Update the taskComments using partial update
        TaskComments partialUpdatedTaskComments = new TaskComments();
        partialUpdatedTaskComments.setTaskCommentId(taskComments.getTaskCommentId());

        partialUpdatedTaskComments
            .commentDate(UPDATED_COMMENT_DATE)
            .commentByName(UPDATED_COMMENT_BY_NAME)
            .taskDetailsUuid(UPDATED_TASK_DETAILS_UUID)
            .taskDetailsId(UPDATED_TASK_DETAILS_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE);

        restTaskCommentsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaskComments.getTaskCommentId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTaskComments))
            )
            .andExpect(status().isOk());

        // Validate the TaskComments in the database
        List<TaskComments> taskCommentsList = taskCommentsRepository.findAll();
        assertThat(taskCommentsList).hasSize(databaseSizeBeforeUpdate);
        TaskComments testTaskComments = taskCommentsList.get(taskCommentsList.size() - 1);
        assertThat(testTaskComments.getCommentText()).isEqualTo(DEFAULT_COMMENT_TEXT);
        assertThat(testTaskComments.getCommentById()).isEqualTo(DEFAULT_COMMENT_BY_ID);
        assertThat(testTaskComments.getCommentDate()).isEqualTo(UPDATED_COMMENT_DATE);
        assertThat(testTaskComments.getCommentByName()).isEqualTo(UPDATED_COMMENT_BY_NAME);
        assertThat(testTaskComments.getTaskDetailsUuid()).isEqualTo(UPDATED_TASK_DETAILS_UUID);
        assertThat(testTaskComments.getTaskDetailsId()).isEqualTo(UPDATED_TASK_DETAILS_ID);
        assertThat(testTaskComments.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testTaskComments.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testTaskComments.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testTaskComments.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testTaskComments.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testTaskComments.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testTaskComments.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testTaskComments.getTaskCommentsUuid()).isEqualTo(DEFAULT_TASK_COMMENTS_UUID);
    }

    @Test
    @Transactional
    void fullUpdateTaskCommentsWithPatch() throws Exception {
        // Initialize the database
        taskCommentsRepository.saveAndFlush(taskComments);

        int databaseSizeBeforeUpdate = taskCommentsRepository.findAll().size();

        // Update the taskComments using partial update
        TaskComments partialUpdatedTaskComments = new TaskComments();
        partialUpdatedTaskComments.setTaskCommentId(taskComments.getTaskCommentId());

        partialUpdatedTaskComments
            .commentText(UPDATED_COMMENT_TEXT)
            .commentById(UPDATED_COMMENT_BY_ID)
            .commentDate(UPDATED_COMMENT_DATE)
            .commentByName(UPDATED_COMMENT_BY_NAME)
            .taskDetailsUuid(UPDATED_TASK_DETAILS_UUID)
            .taskDetailsId(UPDATED_TASK_DETAILS_ID)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .taskCommentsUuid(UPDATED_TASK_COMMENTS_UUID);

        restTaskCommentsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaskComments.getTaskCommentId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTaskComments))
            )
            .andExpect(status().isOk());

        // Validate the TaskComments in the database
        List<TaskComments> taskCommentsList = taskCommentsRepository.findAll();
        assertThat(taskCommentsList).hasSize(databaseSizeBeforeUpdate);
        TaskComments testTaskComments = taskCommentsList.get(taskCommentsList.size() - 1);
        assertThat(testTaskComments.getCommentText()).isEqualTo(UPDATED_COMMENT_TEXT);
        assertThat(testTaskComments.getCommentById()).isEqualTo(UPDATED_COMMENT_BY_ID);
        assertThat(testTaskComments.getCommentDate()).isEqualTo(UPDATED_COMMENT_DATE);
        assertThat(testTaskComments.getCommentByName()).isEqualTo(UPDATED_COMMENT_BY_NAME);
        assertThat(testTaskComments.getTaskDetailsUuid()).isEqualTo(UPDATED_TASK_DETAILS_UUID);
        assertThat(testTaskComments.getTaskDetailsId()).isEqualTo(UPDATED_TASK_DETAILS_ID);
        assertThat(testTaskComments.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testTaskComments.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testTaskComments.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testTaskComments.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testTaskComments.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testTaskComments.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testTaskComments.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testTaskComments.getTaskCommentsUuid()).isEqualTo(UPDATED_TASK_COMMENTS_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingTaskComments() throws Exception {
        int databaseSizeBeforeUpdate = taskCommentsRepository.findAll().size();
        taskComments.setTaskCommentId(count.incrementAndGet());

        // Create the TaskComments
        TaskCommentsDTO taskCommentsDTO = taskCommentsMapper.toDto(taskComments);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaskCommentsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, taskCommentsDTO.getTaskCommentId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(taskCommentsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaskComments in the database
        List<TaskComments> taskCommentsList = taskCommentsRepository.findAll();
        assertThat(taskCommentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTaskComments() throws Exception {
        int databaseSizeBeforeUpdate = taskCommentsRepository.findAll().size();
        taskComments.setTaskCommentId(count.incrementAndGet());

        // Create the TaskComments
        TaskCommentsDTO taskCommentsDTO = taskCommentsMapper.toDto(taskComments);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaskCommentsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(taskCommentsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaskComments in the database
        List<TaskComments> taskCommentsList = taskCommentsRepository.findAll();
        assertThat(taskCommentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTaskComments() throws Exception {
        int databaseSizeBeforeUpdate = taskCommentsRepository.findAll().size();
        taskComments.setTaskCommentId(count.incrementAndGet());

        // Create the TaskComments
        TaskCommentsDTO taskCommentsDTO = taskCommentsMapper.toDto(taskComments);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaskCommentsMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(taskCommentsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TaskComments in the database
        List<TaskComments> taskCommentsList = taskCommentsRepository.findAll();
        assertThat(taskCommentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTaskComments() throws Exception {
        // Initialize the database
        taskCommentsRepository.saveAndFlush(taskComments);

        int databaseSizeBeforeDelete = taskCommentsRepository.findAll().size();

        // Delete the taskComments
        restTaskCommentsMockMvc
            .perform(delete(ENTITY_API_URL_ID, taskComments.getTaskCommentId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TaskComments> taskCommentsList = taskCommentsRepository.findAll();
        assertThat(taskCommentsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
