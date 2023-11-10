package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.TaskDetails;
import com.sunknowledge.dme.rcm.repository.TaskDetailsRepository;
import com.sunknowledge.dme.rcm.service.dto.TaskDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.TaskDetailsMapper;
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
 * Integration tests for the {@link TaskDetailsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TaskDetailsResourceIT {

    private static final String DEFAULT_TASK_NO = "AAAAAAAAAA";
    private static final String UPDATED_TASK_NO = "BBBBBBBBBB";

    private static final Long DEFAULT_TASK_ID = 1L;
    private static final Long UPDATED_TASK_ID = 2L;

    private static final String DEFAULT_TASK_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TASK_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TASK_REASON = "AAAAAAAAAA";
    private static final String UPDATED_TASK_REASON = "BBBBBBBBBB";

    private static final String DEFAULT_SEVERITY = "AAAAAAAAAA";
    private static final String UPDATED_SEVERITY = "BBBBBBBBBB";

    private static final String DEFAULT_SUBJECT_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_SUBJECT_TEXT = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION_TEXT = "BBBBBBBBBB";

    private static final Long DEFAULT_ASSIGNED_TO_ID = 1L;
    private static final Long UPDATED_ASSIGNED_TO_ID = 2L;

    private static final LocalDate DEFAULT_ASSIGNMENT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ASSIGNMENT_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_NEEDED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_NEEDED = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_COMPLETED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_COMPLETED = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_TASK_STATE = "AAAAAAAAAA";
    private static final String UPDATED_TASK_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_DEACTIVE_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_DEACTIVE_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_OBJECT_ID = 1L;
    private static final Long UPDATED_OBJECT_ID = 2L;

    private static final String DEFAULT_OBJECT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_OBJECT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_OBJECT_INSTANCE_ID_NO = "AAAAAAAAAA";
    private static final String UPDATED_OBJECT_INSTANCE_ID_NO = "BBBBBBBBBB";

    private static final String DEFAULT_ASSIGN_TO_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ASSIGN_TO_NAME = "BBBBBBBBBB";

    private static final UUID DEFAULT_OBJECT_INSTANCE_UUID = UUID.randomUUID();
    private static final UUID UPDATED_OBJECT_INSTANCE_UUID = UUID.randomUUID();

    private static final Long DEFAULT_WIP_STATUS_ID = 1L;
    private static final Long UPDATED_WIP_STATUS_ID = 2L;

    private static final String DEFAULT_WIP_STATUS_NAME = "AAAAAAAAAA";
    private static final String UPDATED_WIP_STATUS_NAME = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_TASK_DETAILS_UUID = UUID.randomUUID();
    private static final UUID UPDATED_TASK_DETAILS_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/task-details";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{taskDetailsId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TaskDetailsRepository taskDetailsRepository;

    @Autowired
    private TaskDetailsMapper taskDetailsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTaskDetailsMockMvc;

    private TaskDetails taskDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaskDetails createEntity(EntityManager em) {
        TaskDetails taskDetails = new TaskDetails()
            .taskNo(DEFAULT_TASK_NO)
            .taskId(DEFAULT_TASK_ID)
            .taskName(DEFAULT_TASK_NAME)
            .taskReason(DEFAULT_TASK_REASON)
            .severity(DEFAULT_SEVERITY)
            .subjectText(DEFAULT_SUBJECT_TEXT)
            .descriptionText(DEFAULT_DESCRIPTION_TEXT)
            .assignedToId(DEFAULT_ASSIGNED_TO_ID)
            .assignmentDate(DEFAULT_ASSIGNMENT_DATE)
            .dateNeeded(DEFAULT_DATE_NEEDED)
            .dateCompleted(DEFAULT_DATE_COMPLETED)
            .taskState(DEFAULT_TASK_STATE)
            .deactiveStatus(DEFAULT_DEACTIVE_STATUS)
            .objectId(DEFAULT_OBJECT_ID)
            .objectName(DEFAULT_OBJECT_NAME)
            .objectInstanceIdNo(DEFAULT_OBJECT_INSTANCE_ID_NO)
            .assignToName(DEFAULT_ASSIGN_TO_NAME)
            .objectInstanceUuid(DEFAULT_OBJECT_INSTANCE_UUID)
            .wipStatusId(DEFAULT_WIP_STATUS_ID)
            .wipStatusName(DEFAULT_WIP_STATUS_NAME)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .taskDetailsUuid(DEFAULT_TASK_DETAILS_UUID);
        return taskDetails;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaskDetails createUpdatedEntity(EntityManager em) {
        TaskDetails taskDetails = new TaskDetails()
            .taskNo(UPDATED_TASK_NO)
            .taskId(UPDATED_TASK_ID)
            .taskName(UPDATED_TASK_NAME)
            .taskReason(UPDATED_TASK_REASON)
            .severity(UPDATED_SEVERITY)
            .subjectText(UPDATED_SUBJECT_TEXT)
            .descriptionText(UPDATED_DESCRIPTION_TEXT)
            .assignedToId(UPDATED_ASSIGNED_TO_ID)
            .assignmentDate(UPDATED_ASSIGNMENT_DATE)
            .dateNeeded(UPDATED_DATE_NEEDED)
            .dateCompleted(UPDATED_DATE_COMPLETED)
            .taskState(UPDATED_TASK_STATE)
            .deactiveStatus(UPDATED_DEACTIVE_STATUS)
            .objectId(UPDATED_OBJECT_ID)
            .objectName(UPDATED_OBJECT_NAME)
            .objectInstanceIdNo(UPDATED_OBJECT_INSTANCE_ID_NO)
            .assignToName(UPDATED_ASSIGN_TO_NAME)
            .objectInstanceUuid(UPDATED_OBJECT_INSTANCE_UUID)
            .wipStatusId(UPDATED_WIP_STATUS_ID)
            .wipStatusName(UPDATED_WIP_STATUS_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .taskDetailsUuid(UPDATED_TASK_DETAILS_UUID);
        return taskDetails;
    }

    @BeforeEach
    public void initTest() {
        taskDetails = createEntity(em);
    }

    @Test
    @Transactional
    void createTaskDetails() throws Exception {
        int databaseSizeBeforeCreate = taskDetailsRepository.findAll().size();
        // Create the TaskDetails
        TaskDetailsDTO taskDetailsDTO = taskDetailsMapper.toDto(taskDetails);
        restTaskDetailsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taskDetailsDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TaskDetails in the database
        List<TaskDetails> taskDetailsList = taskDetailsRepository.findAll();
        assertThat(taskDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        TaskDetails testTaskDetails = taskDetailsList.get(taskDetailsList.size() - 1);
        assertThat(testTaskDetails.getTaskNo()).isEqualTo(DEFAULT_TASK_NO);
        assertThat(testTaskDetails.getTaskId()).isEqualTo(DEFAULT_TASK_ID);
        assertThat(testTaskDetails.getTaskName()).isEqualTo(DEFAULT_TASK_NAME);
        assertThat(testTaskDetails.getTaskReason()).isEqualTo(DEFAULT_TASK_REASON);
        assertThat(testTaskDetails.getSeverity()).isEqualTo(DEFAULT_SEVERITY);
        assertThat(testTaskDetails.getSubjectText()).isEqualTo(DEFAULT_SUBJECT_TEXT);
        assertThat(testTaskDetails.getDescriptionText()).isEqualTo(DEFAULT_DESCRIPTION_TEXT);
        assertThat(testTaskDetails.getAssignedToId()).isEqualTo(DEFAULT_ASSIGNED_TO_ID);
        assertThat(testTaskDetails.getAssignmentDate()).isEqualTo(DEFAULT_ASSIGNMENT_DATE);
        assertThat(testTaskDetails.getDateNeeded()).isEqualTo(DEFAULT_DATE_NEEDED);
        assertThat(testTaskDetails.getDateCompleted()).isEqualTo(DEFAULT_DATE_COMPLETED);
        assertThat(testTaskDetails.getTaskState()).isEqualTo(DEFAULT_TASK_STATE);
        assertThat(testTaskDetails.getDeactiveStatus()).isEqualTo(DEFAULT_DEACTIVE_STATUS);
        assertThat(testTaskDetails.getObjectId()).isEqualTo(DEFAULT_OBJECT_ID);
        assertThat(testTaskDetails.getObjectName()).isEqualTo(DEFAULT_OBJECT_NAME);
        assertThat(testTaskDetails.getObjectInstanceIdNo()).isEqualTo(DEFAULT_OBJECT_INSTANCE_ID_NO);
        assertThat(testTaskDetails.getAssignToName()).isEqualTo(DEFAULT_ASSIGN_TO_NAME);
        assertThat(testTaskDetails.getObjectInstanceUuid()).isEqualTo(DEFAULT_OBJECT_INSTANCE_UUID);
        assertThat(testTaskDetails.getWipStatusId()).isEqualTo(DEFAULT_WIP_STATUS_ID);
        assertThat(testTaskDetails.getWipStatusName()).isEqualTo(DEFAULT_WIP_STATUS_NAME);
        assertThat(testTaskDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testTaskDetails.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testTaskDetails.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testTaskDetails.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testTaskDetails.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testTaskDetails.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testTaskDetails.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testTaskDetails.getTaskDetailsUuid()).isEqualTo(DEFAULT_TASK_DETAILS_UUID);
    }

    @Test
    @Transactional
    void createTaskDetailsWithExistingId() throws Exception {
        // Create the TaskDetails with an existing ID
        taskDetails.setTaskDetailsId(1L);
        TaskDetailsDTO taskDetailsDTO = taskDetailsMapper.toDto(taskDetails);

        int databaseSizeBeforeCreate = taskDetailsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTaskDetailsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taskDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaskDetails in the database
        List<TaskDetails> taskDetailsList = taskDetailsRepository.findAll();
        assertThat(taskDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTaskDetails() throws Exception {
        // Initialize the database
        taskDetailsRepository.saveAndFlush(taskDetails);

        // Get all the taskDetailsList
        restTaskDetailsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=taskDetailsId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].taskDetailsId").value(hasItem(taskDetails.getTaskDetailsId().intValue())))
            .andExpect(jsonPath("$.[*].taskNo").value(hasItem(DEFAULT_TASK_NO)))
            .andExpect(jsonPath("$.[*].taskId").value(hasItem(DEFAULT_TASK_ID.intValue())))
            .andExpect(jsonPath("$.[*].taskName").value(hasItem(DEFAULT_TASK_NAME)))
            .andExpect(jsonPath("$.[*].taskReason").value(hasItem(DEFAULT_TASK_REASON)))
            .andExpect(jsonPath("$.[*].severity").value(hasItem(DEFAULT_SEVERITY)))
            .andExpect(jsonPath("$.[*].subjectText").value(hasItem(DEFAULT_SUBJECT_TEXT)))
            .andExpect(jsonPath("$.[*].descriptionText").value(hasItem(DEFAULT_DESCRIPTION_TEXT)))
            .andExpect(jsonPath("$.[*].assignedToId").value(hasItem(DEFAULT_ASSIGNED_TO_ID.intValue())))
            .andExpect(jsonPath("$.[*].assignmentDate").value(hasItem(DEFAULT_ASSIGNMENT_DATE.toString())))
            .andExpect(jsonPath("$.[*].dateNeeded").value(hasItem(DEFAULT_DATE_NEEDED.toString())))
            .andExpect(jsonPath("$.[*].dateCompleted").value(hasItem(DEFAULT_DATE_COMPLETED.toString())))
            .andExpect(jsonPath("$.[*].taskState").value(hasItem(DEFAULT_TASK_STATE)))
            .andExpect(jsonPath("$.[*].deactiveStatus").value(hasItem(DEFAULT_DEACTIVE_STATUS)))
            .andExpect(jsonPath("$.[*].objectId").value(hasItem(DEFAULT_OBJECT_ID.intValue())))
            .andExpect(jsonPath("$.[*].objectName").value(hasItem(DEFAULT_OBJECT_NAME)))
            .andExpect(jsonPath("$.[*].objectInstanceIdNo").value(hasItem(DEFAULT_OBJECT_INSTANCE_ID_NO)))
            .andExpect(jsonPath("$.[*].assignToName").value(hasItem(DEFAULT_ASSIGN_TO_NAME)))
            .andExpect(jsonPath("$.[*].objectInstanceUuid").value(hasItem(DEFAULT_OBJECT_INSTANCE_UUID.toString())))
            .andExpect(jsonPath("$.[*].wipStatusId").value(hasItem(DEFAULT_WIP_STATUS_ID.intValue())))
            .andExpect(jsonPath("$.[*].wipStatusName").value(hasItem(DEFAULT_WIP_STATUS_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].taskDetailsUuid").value(hasItem(DEFAULT_TASK_DETAILS_UUID.toString())));
    }

    @Test
    @Transactional
    void getTaskDetails() throws Exception {
        // Initialize the database
        taskDetailsRepository.saveAndFlush(taskDetails);

        // Get the taskDetails
        restTaskDetailsMockMvc
            .perform(get(ENTITY_API_URL_ID, taskDetails.getTaskDetailsId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.taskDetailsId").value(taskDetails.getTaskDetailsId().intValue()))
            .andExpect(jsonPath("$.taskNo").value(DEFAULT_TASK_NO))
            .andExpect(jsonPath("$.taskId").value(DEFAULT_TASK_ID.intValue()))
            .andExpect(jsonPath("$.taskName").value(DEFAULT_TASK_NAME))
            .andExpect(jsonPath("$.taskReason").value(DEFAULT_TASK_REASON))
            .andExpect(jsonPath("$.severity").value(DEFAULT_SEVERITY))
            .andExpect(jsonPath("$.subjectText").value(DEFAULT_SUBJECT_TEXT))
            .andExpect(jsonPath("$.descriptionText").value(DEFAULT_DESCRIPTION_TEXT))
            .andExpect(jsonPath("$.assignedToId").value(DEFAULT_ASSIGNED_TO_ID.intValue()))
            .andExpect(jsonPath("$.assignmentDate").value(DEFAULT_ASSIGNMENT_DATE.toString()))
            .andExpect(jsonPath("$.dateNeeded").value(DEFAULT_DATE_NEEDED.toString()))
            .andExpect(jsonPath("$.dateCompleted").value(DEFAULT_DATE_COMPLETED.toString()))
            .andExpect(jsonPath("$.taskState").value(DEFAULT_TASK_STATE))
            .andExpect(jsonPath("$.deactiveStatus").value(DEFAULT_DEACTIVE_STATUS))
            .andExpect(jsonPath("$.objectId").value(DEFAULT_OBJECT_ID.intValue()))
            .andExpect(jsonPath("$.objectName").value(DEFAULT_OBJECT_NAME))
            .andExpect(jsonPath("$.objectInstanceIdNo").value(DEFAULT_OBJECT_INSTANCE_ID_NO))
            .andExpect(jsonPath("$.assignToName").value(DEFAULT_ASSIGN_TO_NAME))
            .andExpect(jsonPath("$.objectInstanceUuid").value(DEFAULT_OBJECT_INSTANCE_UUID.toString()))
            .andExpect(jsonPath("$.wipStatusId").value(DEFAULT_WIP_STATUS_ID.intValue()))
            .andExpect(jsonPath("$.wipStatusName").value(DEFAULT_WIP_STATUS_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.taskDetailsUuid").value(DEFAULT_TASK_DETAILS_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingTaskDetails() throws Exception {
        // Get the taskDetails
        restTaskDetailsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTaskDetails() throws Exception {
        // Initialize the database
        taskDetailsRepository.saveAndFlush(taskDetails);

        int databaseSizeBeforeUpdate = taskDetailsRepository.findAll().size();

        // Update the taskDetails
        TaskDetails updatedTaskDetails = taskDetailsRepository.findById(taskDetails.getTaskDetailsId()).get();
        // Disconnect from session so that the updates on updatedTaskDetails are not directly saved in db
        em.detach(updatedTaskDetails);
        updatedTaskDetails
            .taskNo(UPDATED_TASK_NO)
            .taskId(UPDATED_TASK_ID)
            .taskName(UPDATED_TASK_NAME)
            .taskReason(UPDATED_TASK_REASON)
            .severity(UPDATED_SEVERITY)
            .subjectText(UPDATED_SUBJECT_TEXT)
            .descriptionText(UPDATED_DESCRIPTION_TEXT)
            .assignedToId(UPDATED_ASSIGNED_TO_ID)
            .assignmentDate(UPDATED_ASSIGNMENT_DATE)
            .dateNeeded(UPDATED_DATE_NEEDED)
            .dateCompleted(UPDATED_DATE_COMPLETED)
            .taskState(UPDATED_TASK_STATE)
            .deactiveStatus(UPDATED_DEACTIVE_STATUS)
            .objectId(UPDATED_OBJECT_ID)
            .objectName(UPDATED_OBJECT_NAME)
            .objectInstanceIdNo(UPDATED_OBJECT_INSTANCE_ID_NO)
            .assignToName(UPDATED_ASSIGN_TO_NAME)
            .objectInstanceUuid(UPDATED_OBJECT_INSTANCE_UUID)
            .wipStatusId(UPDATED_WIP_STATUS_ID)
            .wipStatusName(UPDATED_WIP_STATUS_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .taskDetailsUuid(UPDATED_TASK_DETAILS_UUID);
        TaskDetailsDTO taskDetailsDTO = taskDetailsMapper.toDto(updatedTaskDetails);

        restTaskDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, taskDetailsDTO.getTaskDetailsId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taskDetailsDTO))
            )
            .andExpect(status().isOk());

        // Validate the TaskDetails in the database
        List<TaskDetails> taskDetailsList = taskDetailsRepository.findAll();
        assertThat(taskDetailsList).hasSize(databaseSizeBeforeUpdate);
        TaskDetails testTaskDetails = taskDetailsList.get(taskDetailsList.size() - 1);
        assertThat(testTaskDetails.getTaskNo()).isEqualTo(UPDATED_TASK_NO);
        assertThat(testTaskDetails.getTaskId()).isEqualTo(UPDATED_TASK_ID);
        assertThat(testTaskDetails.getTaskName()).isEqualTo(UPDATED_TASK_NAME);
        assertThat(testTaskDetails.getTaskReason()).isEqualTo(UPDATED_TASK_REASON);
        assertThat(testTaskDetails.getSeverity()).isEqualTo(UPDATED_SEVERITY);
        assertThat(testTaskDetails.getSubjectText()).isEqualTo(UPDATED_SUBJECT_TEXT);
        assertThat(testTaskDetails.getDescriptionText()).isEqualTo(UPDATED_DESCRIPTION_TEXT);
        assertThat(testTaskDetails.getAssignedToId()).isEqualTo(UPDATED_ASSIGNED_TO_ID);
        assertThat(testTaskDetails.getAssignmentDate()).isEqualTo(UPDATED_ASSIGNMENT_DATE);
        assertThat(testTaskDetails.getDateNeeded()).isEqualTo(UPDATED_DATE_NEEDED);
        assertThat(testTaskDetails.getDateCompleted()).isEqualTo(UPDATED_DATE_COMPLETED);
        assertThat(testTaskDetails.getTaskState()).isEqualTo(UPDATED_TASK_STATE);
        assertThat(testTaskDetails.getDeactiveStatus()).isEqualTo(UPDATED_DEACTIVE_STATUS);
        assertThat(testTaskDetails.getObjectId()).isEqualTo(UPDATED_OBJECT_ID);
        assertThat(testTaskDetails.getObjectName()).isEqualTo(UPDATED_OBJECT_NAME);
        assertThat(testTaskDetails.getObjectInstanceIdNo()).isEqualTo(UPDATED_OBJECT_INSTANCE_ID_NO);
        assertThat(testTaskDetails.getAssignToName()).isEqualTo(UPDATED_ASSIGN_TO_NAME);
        assertThat(testTaskDetails.getObjectInstanceUuid()).isEqualTo(UPDATED_OBJECT_INSTANCE_UUID);
        assertThat(testTaskDetails.getWipStatusId()).isEqualTo(UPDATED_WIP_STATUS_ID);
        assertThat(testTaskDetails.getWipStatusName()).isEqualTo(UPDATED_WIP_STATUS_NAME);
        assertThat(testTaskDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testTaskDetails.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testTaskDetails.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testTaskDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testTaskDetails.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testTaskDetails.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testTaskDetails.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testTaskDetails.getTaskDetailsUuid()).isEqualTo(UPDATED_TASK_DETAILS_UUID);
    }

    @Test
    @Transactional
    void putNonExistingTaskDetails() throws Exception {
        int databaseSizeBeforeUpdate = taskDetailsRepository.findAll().size();
        taskDetails.setTaskDetailsId(count.incrementAndGet());

        // Create the TaskDetails
        TaskDetailsDTO taskDetailsDTO = taskDetailsMapper.toDto(taskDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaskDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, taskDetailsDTO.getTaskDetailsId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taskDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaskDetails in the database
        List<TaskDetails> taskDetailsList = taskDetailsRepository.findAll();
        assertThat(taskDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTaskDetails() throws Exception {
        int databaseSizeBeforeUpdate = taskDetailsRepository.findAll().size();
        taskDetails.setTaskDetailsId(count.incrementAndGet());

        // Create the TaskDetails
        TaskDetailsDTO taskDetailsDTO = taskDetailsMapper.toDto(taskDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaskDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taskDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaskDetails in the database
        List<TaskDetails> taskDetailsList = taskDetailsRepository.findAll();
        assertThat(taskDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTaskDetails() throws Exception {
        int databaseSizeBeforeUpdate = taskDetailsRepository.findAll().size();
        taskDetails.setTaskDetailsId(count.incrementAndGet());

        // Create the TaskDetails
        TaskDetailsDTO taskDetailsDTO = taskDetailsMapper.toDto(taskDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaskDetailsMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taskDetailsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TaskDetails in the database
        List<TaskDetails> taskDetailsList = taskDetailsRepository.findAll();
        assertThat(taskDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTaskDetailsWithPatch() throws Exception {
        // Initialize the database
        taskDetailsRepository.saveAndFlush(taskDetails);

        int databaseSizeBeforeUpdate = taskDetailsRepository.findAll().size();

        // Update the taskDetails using partial update
        TaskDetails partialUpdatedTaskDetails = new TaskDetails();
        partialUpdatedTaskDetails.setTaskDetailsId(taskDetails.getTaskDetailsId());

        partialUpdatedTaskDetails
            .taskNo(UPDATED_TASK_NO)
            .taskReason(UPDATED_TASK_REASON)
            .severity(UPDATED_SEVERITY)
            .descriptionText(UPDATED_DESCRIPTION_TEXT)
            .assignedToId(UPDATED_ASSIGNED_TO_ID)
            .assignmentDate(UPDATED_ASSIGNMENT_DATE)
            .dateNeeded(UPDATED_DATE_NEEDED)
            .dateCompleted(UPDATED_DATE_COMPLETED)
            .objectId(UPDATED_OBJECT_ID)
            .objectInstanceIdNo(UPDATED_OBJECT_INSTANCE_ID_NO)
            .assignToName(UPDATED_ASSIGN_TO_NAME)
            .objectInstanceUuid(UPDATED_OBJECT_INSTANCE_UUID)
            .wipStatusId(UPDATED_WIP_STATUS_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .taskDetailsUuid(UPDATED_TASK_DETAILS_UUID);

        restTaskDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaskDetails.getTaskDetailsId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTaskDetails))
            )
            .andExpect(status().isOk());

        // Validate the TaskDetails in the database
        List<TaskDetails> taskDetailsList = taskDetailsRepository.findAll();
        assertThat(taskDetailsList).hasSize(databaseSizeBeforeUpdate);
        TaskDetails testTaskDetails = taskDetailsList.get(taskDetailsList.size() - 1);
        assertThat(testTaskDetails.getTaskNo()).isEqualTo(UPDATED_TASK_NO);
        assertThat(testTaskDetails.getTaskId()).isEqualTo(DEFAULT_TASK_ID);
        assertThat(testTaskDetails.getTaskName()).isEqualTo(DEFAULT_TASK_NAME);
        assertThat(testTaskDetails.getTaskReason()).isEqualTo(UPDATED_TASK_REASON);
        assertThat(testTaskDetails.getSeverity()).isEqualTo(UPDATED_SEVERITY);
        assertThat(testTaskDetails.getSubjectText()).isEqualTo(DEFAULT_SUBJECT_TEXT);
        assertThat(testTaskDetails.getDescriptionText()).isEqualTo(UPDATED_DESCRIPTION_TEXT);
        assertThat(testTaskDetails.getAssignedToId()).isEqualTo(UPDATED_ASSIGNED_TO_ID);
        assertThat(testTaskDetails.getAssignmentDate()).isEqualTo(UPDATED_ASSIGNMENT_DATE);
        assertThat(testTaskDetails.getDateNeeded()).isEqualTo(UPDATED_DATE_NEEDED);
        assertThat(testTaskDetails.getDateCompleted()).isEqualTo(UPDATED_DATE_COMPLETED);
        assertThat(testTaskDetails.getTaskState()).isEqualTo(DEFAULT_TASK_STATE);
        assertThat(testTaskDetails.getDeactiveStatus()).isEqualTo(DEFAULT_DEACTIVE_STATUS);
        assertThat(testTaskDetails.getObjectId()).isEqualTo(UPDATED_OBJECT_ID);
        assertThat(testTaskDetails.getObjectName()).isEqualTo(DEFAULT_OBJECT_NAME);
        assertThat(testTaskDetails.getObjectInstanceIdNo()).isEqualTo(UPDATED_OBJECT_INSTANCE_ID_NO);
        assertThat(testTaskDetails.getAssignToName()).isEqualTo(UPDATED_ASSIGN_TO_NAME);
        assertThat(testTaskDetails.getObjectInstanceUuid()).isEqualTo(UPDATED_OBJECT_INSTANCE_UUID);
        assertThat(testTaskDetails.getWipStatusId()).isEqualTo(UPDATED_WIP_STATUS_ID);
        assertThat(testTaskDetails.getWipStatusName()).isEqualTo(DEFAULT_WIP_STATUS_NAME);
        assertThat(testTaskDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testTaskDetails.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testTaskDetails.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testTaskDetails.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testTaskDetails.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testTaskDetails.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testTaskDetails.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testTaskDetails.getTaskDetailsUuid()).isEqualTo(UPDATED_TASK_DETAILS_UUID);
    }

    @Test
    @Transactional
    void fullUpdateTaskDetailsWithPatch() throws Exception {
        // Initialize the database
        taskDetailsRepository.saveAndFlush(taskDetails);

        int databaseSizeBeforeUpdate = taskDetailsRepository.findAll().size();

        // Update the taskDetails using partial update
        TaskDetails partialUpdatedTaskDetails = new TaskDetails();
        partialUpdatedTaskDetails.setTaskDetailsId(taskDetails.getTaskDetailsId());

        partialUpdatedTaskDetails
            .taskNo(UPDATED_TASK_NO)
            .taskId(UPDATED_TASK_ID)
            .taskName(UPDATED_TASK_NAME)
            .taskReason(UPDATED_TASK_REASON)
            .severity(UPDATED_SEVERITY)
            .subjectText(UPDATED_SUBJECT_TEXT)
            .descriptionText(UPDATED_DESCRIPTION_TEXT)
            .assignedToId(UPDATED_ASSIGNED_TO_ID)
            .assignmentDate(UPDATED_ASSIGNMENT_DATE)
            .dateNeeded(UPDATED_DATE_NEEDED)
            .dateCompleted(UPDATED_DATE_COMPLETED)
            .taskState(UPDATED_TASK_STATE)
            .deactiveStatus(UPDATED_DEACTIVE_STATUS)
            .objectId(UPDATED_OBJECT_ID)
            .objectName(UPDATED_OBJECT_NAME)
            .objectInstanceIdNo(UPDATED_OBJECT_INSTANCE_ID_NO)
            .assignToName(UPDATED_ASSIGN_TO_NAME)
            .objectInstanceUuid(UPDATED_OBJECT_INSTANCE_UUID)
            .wipStatusId(UPDATED_WIP_STATUS_ID)
            .wipStatusName(UPDATED_WIP_STATUS_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .taskDetailsUuid(UPDATED_TASK_DETAILS_UUID);

        restTaskDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaskDetails.getTaskDetailsId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTaskDetails))
            )
            .andExpect(status().isOk());

        // Validate the TaskDetails in the database
        List<TaskDetails> taskDetailsList = taskDetailsRepository.findAll();
        assertThat(taskDetailsList).hasSize(databaseSizeBeforeUpdate);
        TaskDetails testTaskDetails = taskDetailsList.get(taskDetailsList.size() - 1);
        assertThat(testTaskDetails.getTaskNo()).isEqualTo(UPDATED_TASK_NO);
        assertThat(testTaskDetails.getTaskId()).isEqualTo(UPDATED_TASK_ID);
        assertThat(testTaskDetails.getTaskName()).isEqualTo(UPDATED_TASK_NAME);
        assertThat(testTaskDetails.getTaskReason()).isEqualTo(UPDATED_TASK_REASON);
        assertThat(testTaskDetails.getSeverity()).isEqualTo(UPDATED_SEVERITY);
        assertThat(testTaskDetails.getSubjectText()).isEqualTo(UPDATED_SUBJECT_TEXT);
        assertThat(testTaskDetails.getDescriptionText()).isEqualTo(UPDATED_DESCRIPTION_TEXT);
        assertThat(testTaskDetails.getAssignedToId()).isEqualTo(UPDATED_ASSIGNED_TO_ID);
        assertThat(testTaskDetails.getAssignmentDate()).isEqualTo(UPDATED_ASSIGNMENT_DATE);
        assertThat(testTaskDetails.getDateNeeded()).isEqualTo(UPDATED_DATE_NEEDED);
        assertThat(testTaskDetails.getDateCompleted()).isEqualTo(UPDATED_DATE_COMPLETED);
        assertThat(testTaskDetails.getTaskState()).isEqualTo(UPDATED_TASK_STATE);
        assertThat(testTaskDetails.getDeactiveStatus()).isEqualTo(UPDATED_DEACTIVE_STATUS);
        assertThat(testTaskDetails.getObjectId()).isEqualTo(UPDATED_OBJECT_ID);
        assertThat(testTaskDetails.getObjectName()).isEqualTo(UPDATED_OBJECT_NAME);
        assertThat(testTaskDetails.getObjectInstanceIdNo()).isEqualTo(UPDATED_OBJECT_INSTANCE_ID_NO);
        assertThat(testTaskDetails.getAssignToName()).isEqualTo(UPDATED_ASSIGN_TO_NAME);
        assertThat(testTaskDetails.getObjectInstanceUuid()).isEqualTo(UPDATED_OBJECT_INSTANCE_UUID);
        assertThat(testTaskDetails.getWipStatusId()).isEqualTo(UPDATED_WIP_STATUS_ID);
        assertThat(testTaskDetails.getWipStatusName()).isEqualTo(UPDATED_WIP_STATUS_NAME);
        assertThat(testTaskDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testTaskDetails.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testTaskDetails.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testTaskDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testTaskDetails.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testTaskDetails.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testTaskDetails.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testTaskDetails.getTaskDetailsUuid()).isEqualTo(UPDATED_TASK_DETAILS_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingTaskDetails() throws Exception {
        int databaseSizeBeforeUpdate = taskDetailsRepository.findAll().size();
        taskDetails.setTaskDetailsId(count.incrementAndGet());

        // Create the TaskDetails
        TaskDetailsDTO taskDetailsDTO = taskDetailsMapper.toDto(taskDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaskDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, taskDetailsDTO.getTaskDetailsId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(taskDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaskDetails in the database
        List<TaskDetails> taskDetailsList = taskDetailsRepository.findAll();
        assertThat(taskDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTaskDetails() throws Exception {
        int databaseSizeBeforeUpdate = taskDetailsRepository.findAll().size();
        taskDetails.setTaskDetailsId(count.incrementAndGet());

        // Create the TaskDetails
        TaskDetailsDTO taskDetailsDTO = taskDetailsMapper.toDto(taskDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaskDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(taskDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaskDetails in the database
        List<TaskDetails> taskDetailsList = taskDetailsRepository.findAll();
        assertThat(taskDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTaskDetails() throws Exception {
        int databaseSizeBeforeUpdate = taskDetailsRepository.findAll().size();
        taskDetails.setTaskDetailsId(count.incrementAndGet());

        // Create the TaskDetails
        TaskDetailsDTO taskDetailsDTO = taskDetailsMapper.toDto(taskDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaskDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(taskDetailsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TaskDetails in the database
        List<TaskDetails> taskDetailsList = taskDetailsRepository.findAll();
        assertThat(taskDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTaskDetails() throws Exception {
        // Initialize the database
        taskDetailsRepository.saveAndFlush(taskDetails);

        int databaseSizeBeforeDelete = taskDetailsRepository.findAll().size();

        // Delete the taskDetails
        restTaskDetailsMockMvc
            .perform(delete(ENTITY_API_URL_ID, taskDetails.getTaskDetailsId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TaskDetails> taskDetailsList = taskDetailsRepository.findAll();
        assertThat(taskDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
