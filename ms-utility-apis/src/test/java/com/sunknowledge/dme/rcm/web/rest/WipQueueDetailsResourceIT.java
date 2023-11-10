package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.WipQueueDetails;
import com.sunknowledge.dme.rcm.repository.WipQueueDetailsRepository;
import com.sunknowledge.dme.rcm.service.dto.WipQueueDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.WipQueueDetailsMapper;
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
 * Integration tests for the {@link WipQueueDetailsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class WipQueueDetailsResourceIT {

    private static final String DEFAULT_WIP_STATUS_ID = "AAAAAAAAAA";
    private static final String UPDATED_WIP_STATUS_ID = "BBBBBBBBBB";

    private static final String DEFAULT_WIP_STATUS_NAME = "AAAAAAAAAA";
    private static final String UPDATED_WIP_STATUS_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_TASK_ID = 1L;
    private static final Long UPDATED_TASK_ID = 2L;

    private static final String DEFAULT_TASK_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TASK_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_OBJECT_ID = 1L;
    private static final Long UPDATED_OBJECT_ID = 2L;

    private static final String DEFAULT_OBJECT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_OBJECT_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_OBJECT_INSTANCE_ID = 1L;
    private static final Long UPDATED_OBJECT_INSTANCE_ID = 2L;

    private static final Long DEFAULT_WIP_SET_BY_ID = 1L;
    private static final Long UPDATED_WIP_SET_BY_ID = 2L;

    private static final String DEFAULT_WIP_SET_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_WIP_SET_BY_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_WIP_SET_DATE_TIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_WIP_SET_DATE_TIME = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_ASSIGNED_BY_ID = 1L;
    private static final Long UPDATED_ASSIGNED_BY_ID = 2L;

    private static final String DEFAULT_ASSIGNED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ASSIGNED_BY_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_ASSIGNED_TO_ID = 1L;
    private static final Long UPDATED_ASSIGNED_TO_ID = 2L;

    private static final String DEFAULT_ASSIGNED_TO_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ASSIGNED_TO_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_ASSIGNED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ASSIGNED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ASSIGNED_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_ASSIGNED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_WIP_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_WIP_NOTES = "BBBBBBBBBB";

    private static final String DEFAULT_ASSIGNMENT_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_ASSIGNMENT_NOTES = "BBBBBBBBBB";

    private static final String DEFAULT_ASSIGNMENT_STATUS_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_ASSIGNMENT_STATUS_NOTES = "BBBBBBBBBB";

    private static final String DEFAULT_OBJECT_INSTANCE_ID_NO = "AAAAAAAAAA";
    private static final String UPDATED_OBJECT_INSTANCE_ID_NO = "BBBBBBBBBB";

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

    private static final UUID DEFAULT_OBJECT_INSTANCE_ID_UUID = UUID.randomUUID();
    private static final UUID UPDATED_OBJECT_INSTANCE_ID_UUID = UUID.randomUUID();

    private static final LocalDate DEFAULT_TARGETED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TARGETED_DATE = LocalDate.now(ZoneId.systemDefault());

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

    private static final UUID DEFAULT_WIP_QUEUE_DETAILS_UUID = UUID.randomUUID();
    private static final UUID UPDATED_WIP_QUEUE_DETAILS_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/wip-queue-details";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{wipQueueDetailsId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private WipQueueDetailsRepository wipQueueDetailsRepository;

    @Autowired
    private WipQueueDetailsMapper wipQueueDetailsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWipQueueDetailsMockMvc;

    private WipQueueDetails wipQueueDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WipQueueDetails createEntity(EntityManager em) {
        WipQueueDetails wipQueueDetails = new WipQueueDetails()
            .wipStatusId(DEFAULT_WIP_STATUS_ID)
            .wipStatusName(DEFAULT_WIP_STATUS_NAME)
            .taskId(DEFAULT_TASK_ID)
            .taskName(DEFAULT_TASK_NAME)
            .objectId(DEFAULT_OBJECT_ID)
            .objectName(DEFAULT_OBJECT_NAME)
            .objectInstanceId(DEFAULT_OBJECT_INSTANCE_ID)
            .wipSetById(DEFAULT_WIP_SET_BY_ID)
            .wipSetByName(DEFAULT_WIP_SET_BY_NAME)
            .wipSetDateTime(DEFAULT_WIP_SET_DATE_TIME)
            .assignedById(DEFAULT_ASSIGNED_BY_ID)
            .assignedByName(DEFAULT_ASSIGNED_BY_NAME)
            .assignedToId(DEFAULT_ASSIGNED_TO_ID)
            .assignedToName(DEFAULT_ASSIGNED_TO_NAME)
            .assignedDate(DEFAULT_ASSIGNED_DATE)
            .assignedStatus(DEFAULT_ASSIGNED_STATUS)
            .wipNotes(DEFAULT_WIP_NOTES)
            .assignmentNotes(DEFAULT_ASSIGNMENT_NOTES)
            .assignmentStatusNotes(DEFAULT_ASSIGNMENT_STATUS_NOTES)
            .objectInstanceIdNo(DEFAULT_OBJECT_INSTANCE_ID_NO)
            .state(DEFAULT_STATE)
            .objectInstanceIdUuid(DEFAULT_OBJECT_INSTANCE_ID_UUID)
            .targetedDate(DEFAULT_TARGETED_DATE)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .wipQueueDetailsUuid(DEFAULT_WIP_QUEUE_DETAILS_UUID);
        return wipQueueDetails;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WipQueueDetails createUpdatedEntity(EntityManager em) {
        WipQueueDetails wipQueueDetails = new WipQueueDetails()
            .wipStatusId(UPDATED_WIP_STATUS_ID)
            .wipStatusName(UPDATED_WIP_STATUS_NAME)
            .taskId(UPDATED_TASK_ID)
            .taskName(UPDATED_TASK_NAME)
            .objectId(UPDATED_OBJECT_ID)
            .objectName(UPDATED_OBJECT_NAME)
            .objectInstanceId(UPDATED_OBJECT_INSTANCE_ID)
            .wipSetById(UPDATED_WIP_SET_BY_ID)
            .wipSetByName(UPDATED_WIP_SET_BY_NAME)
            .wipSetDateTime(UPDATED_WIP_SET_DATE_TIME)
            .assignedById(UPDATED_ASSIGNED_BY_ID)
            .assignedByName(UPDATED_ASSIGNED_BY_NAME)
            .assignedToId(UPDATED_ASSIGNED_TO_ID)
            .assignedToName(UPDATED_ASSIGNED_TO_NAME)
            .assignedDate(UPDATED_ASSIGNED_DATE)
            .assignedStatus(UPDATED_ASSIGNED_STATUS)
            .wipNotes(UPDATED_WIP_NOTES)
            .assignmentNotes(UPDATED_ASSIGNMENT_NOTES)
            .assignmentStatusNotes(UPDATED_ASSIGNMENT_STATUS_NOTES)
            .objectInstanceIdNo(UPDATED_OBJECT_INSTANCE_ID_NO)
            .state(UPDATED_STATE)
            .objectInstanceIdUuid(UPDATED_OBJECT_INSTANCE_ID_UUID)
            .targetedDate(UPDATED_TARGETED_DATE)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .wipQueueDetailsUuid(UPDATED_WIP_QUEUE_DETAILS_UUID);
        return wipQueueDetails;
    }

    @BeforeEach
    public void initTest() {
        wipQueueDetails = createEntity(em);
    }

    @Test
    @Transactional
    void createWipQueueDetails() throws Exception {
        int databaseSizeBeforeCreate = wipQueueDetailsRepository.findAll().size();
        // Create the WipQueueDetails
        WipQueueDetailsDTO wipQueueDetailsDTO = wipQueueDetailsMapper.toDto(wipQueueDetails);
        restWipQueueDetailsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(wipQueueDetailsDTO))
            )
            .andExpect(status().isCreated());

        // Validate the WipQueueDetails in the database
        List<WipQueueDetails> wipQueueDetailsList = wipQueueDetailsRepository.findAll();
        assertThat(wipQueueDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        WipQueueDetails testWipQueueDetails = wipQueueDetailsList.get(wipQueueDetailsList.size() - 1);
        assertThat(testWipQueueDetails.getWipStatusId()).isEqualTo(DEFAULT_WIP_STATUS_ID);
        assertThat(testWipQueueDetails.getWipStatusName()).isEqualTo(DEFAULT_WIP_STATUS_NAME);
        assertThat(testWipQueueDetails.getTaskId()).isEqualTo(DEFAULT_TASK_ID);
        assertThat(testWipQueueDetails.getTaskName()).isEqualTo(DEFAULT_TASK_NAME);
        assertThat(testWipQueueDetails.getObjectId()).isEqualTo(DEFAULT_OBJECT_ID);
        assertThat(testWipQueueDetails.getObjectName()).isEqualTo(DEFAULT_OBJECT_NAME);
        assertThat(testWipQueueDetails.getObjectInstanceId()).isEqualTo(DEFAULT_OBJECT_INSTANCE_ID);
        assertThat(testWipQueueDetails.getWipSetById()).isEqualTo(DEFAULT_WIP_SET_BY_ID);
        assertThat(testWipQueueDetails.getWipSetByName()).isEqualTo(DEFAULT_WIP_SET_BY_NAME);
        assertThat(testWipQueueDetails.getWipSetDateTime()).isEqualTo(DEFAULT_WIP_SET_DATE_TIME);
        assertThat(testWipQueueDetails.getAssignedById()).isEqualTo(DEFAULT_ASSIGNED_BY_ID);
        assertThat(testWipQueueDetails.getAssignedByName()).isEqualTo(DEFAULT_ASSIGNED_BY_NAME);
        assertThat(testWipQueueDetails.getAssignedToId()).isEqualTo(DEFAULT_ASSIGNED_TO_ID);
        assertThat(testWipQueueDetails.getAssignedToName()).isEqualTo(DEFAULT_ASSIGNED_TO_NAME);
        assertThat(testWipQueueDetails.getAssignedDate()).isEqualTo(DEFAULT_ASSIGNED_DATE);
        assertThat(testWipQueueDetails.getAssignedStatus()).isEqualTo(DEFAULT_ASSIGNED_STATUS);
        assertThat(testWipQueueDetails.getWipNotes()).isEqualTo(DEFAULT_WIP_NOTES);
        assertThat(testWipQueueDetails.getAssignmentNotes()).isEqualTo(DEFAULT_ASSIGNMENT_NOTES);
        assertThat(testWipQueueDetails.getAssignmentStatusNotes()).isEqualTo(DEFAULT_ASSIGNMENT_STATUS_NOTES);
        assertThat(testWipQueueDetails.getObjectInstanceIdNo()).isEqualTo(DEFAULT_OBJECT_INSTANCE_ID_NO);
        assertThat(testWipQueueDetails.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testWipQueueDetails.getObjectInstanceIdUuid()).isEqualTo(DEFAULT_OBJECT_INSTANCE_ID_UUID);
        assertThat(testWipQueueDetails.getTargetedDate()).isEqualTo(DEFAULT_TARGETED_DATE);
        assertThat(testWipQueueDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testWipQueueDetails.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testWipQueueDetails.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testWipQueueDetails.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testWipQueueDetails.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testWipQueueDetails.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testWipQueueDetails.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testWipQueueDetails.getWipQueueDetailsUuid()).isEqualTo(DEFAULT_WIP_QUEUE_DETAILS_UUID);
    }

    @Test
    @Transactional
    void createWipQueueDetailsWithExistingId() throws Exception {
        // Create the WipQueueDetails with an existing ID
        wipQueueDetails.setWipQueueDetailsId(1L);
        WipQueueDetailsDTO wipQueueDetailsDTO = wipQueueDetailsMapper.toDto(wipQueueDetails);

        int databaseSizeBeforeCreate = wipQueueDetailsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restWipQueueDetailsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(wipQueueDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WipQueueDetails in the database
        List<WipQueueDetails> wipQueueDetailsList = wipQueueDetailsRepository.findAll();
        assertThat(wipQueueDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllWipQueueDetails() throws Exception {
        // Initialize the database
        wipQueueDetailsRepository.saveAndFlush(wipQueueDetails);

        // Get all the wipQueueDetailsList
        restWipQueueDetailsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=wipQueueDetailsId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].wipQueueDetailsId").value(hasItem(wipQueueDetails.getWipQueueDetailsId().intValue())))
            .andExpect(jsonPath("$.[*].wipStatusId").value(hasItem(DEFAULT_WIP_STATUS_ID)))
            .andExpect(jsonPath("$.[*].wipStatusName").value(hasItem(DEFAULT_WIP_STATUS_NAME)))
            .andExpect(jsonPath("$.[*].taskId").value(hasItem(DEFAULT_TASK_ID.intValue())))
            .andExpect(jsonPath("$.[*].taskName").value(hasItem(DEFAULT_TASK_NAME)))
            .andExpect(jsonPath("$.[*].objectId").value(hasItem(DEFAULT_OBJECT_ID.intValue())))
            .andExpect(jsonPath("$.[*].objectName").value(hasItem(DEFAULT_OBJECT_NAME)))
            .andExpect(jsonPath("$.[*].objectInstanceId").value(hasItem(DEFAULT_OBJECT_INSTANCE_ID.intValue())))
            .andExpect(jsonPath("$.[*].wipSetById").value(hasItem(DEFAULT_WIP_SET_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].wipSetByName").value(hasItem(DEFAULT_WIP_SET_BY_NAME)))
            .andExpect(jsonPath("$.[*].wipSetDateTime").value(hasItem(DEFAULT_WIP_SET_DATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].assignedById").value(hasItem(DEFAULT_ASSIGNED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].assignedByName").value(hasItem(DEFAULT_ASSIGNED_BY_NAME)))
            .andExpect(jsonPath("$.[*].assignedToId").value(hasItem(DEFAULT_ASSIGNED_TO_ID.intValue())))
            .andExpect(jsonPath("$.[*].assignedToName").value(hasItem(DEFAULT_ASSIGNED_TO_NAME)))
            .andExpect(jsonPath("$.[*].assignedDate").value(hasItem(DEFAULT_ASSIGNED_DATE.toString())))
            .andExpect(jsonPath("$.[*].assignedStatus").value(hasItem(DEFAULT_ASSIGNED_STATUS)))
            .andExpect(jsonPath("$.[*].wipNotes").value(hasItem(DEFAULT_WIP_NOTES)))
            .andExpect(jsonPath("$.[*].assignmentNotes").value(hasItem(DEFAULT_ASSIGNMENT_NOTES)))
            .andExpect(jsonPath("$.[*].assignmentStatusNotes").value(hasItem(DEFAULT_ASSIGNMENT_STATUS_NOTES)))
            .andExpect(jsonPath("$.[*].objectInstanceIdNo").value(hasItem(DEFAULT_OBJECT_INSTANCE_ID_NO)))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE)))
            .andExpect(jsonPath("$.[*].objectInstanceIdUuid").value(hasItem(DEFAULT_OBJECT_INSTANCE_ID_UUID.toString())))
            .andExpect(jsonPath("$.[*].targetedDate").value(hasItem(DEFAULT_TARGETED_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].wipQueueDetailsUuid").value(hasItem(DEFAULT_WIP_QUEUE_DETAILS_UUID.toString())));
    }

    @Test
    @Transactional
    void getWipQueueDetails() throws Exception {
        // Initialize the database
        wipQueueDetailsRepository.saveAndFlush(wipQueueDetails);

        // Get the wipQueueDetails
        restWipQueueDetailsMockMvc
            .perform(get(ENTITY_API_URL_ID, wipQueueDetails.getWipQueueDetailsId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.wipQueueDetailsId").value(wipQueueDetails.getWipQueueDetailsId().intValue()))
            .andExpect(jsonPath("$.wipStatusId").value(DEFAULT_WIP_STATUS_ID))
            .andExpect(jsonPath("$.wipStatusName").value(DEFAULT_WIP_STATUS_NAME))
            .andExpect(jsonPath("$.taskId").value(DEFAULT_TASK_ID.intValue()))
            .andExpect(jsonPath("$.taskName").value(DEFAULT_TASK_NAME))
            .andExpect(jsonPath("$.objectId").value(DEFAULT_OBJECT_ID.intValue()))
            .andExpect(jsonPath("$.objectName").value(DEFAULT_OBJECT_NAME))
            .andExpect(jsonPath("$.objectInstanceId").value(DEFAULT_OBJECT_INSTANCE_ID.intValue()))
            .andExpect(jsonPath("$.wipSetById").value(DEFAULT_WIP_SET_BY_ID.intValue()))
            .andExpect(jsonPath("$.wipSetByName").value(DEFAULT_WIP_SET_BY_NAME))
            .andExpect(jsonPath("$.wipSetDateTime").value(DEFAULT_WIP_SET_DATE_TIME.toString()))
            .andExpect(jsonPath("$.assignedById").value(DEFAULT_ASSIGNED_BY_ID.intValue()))
            .andExpect(jsonPath("$.assignedByName").value(DEFAULT_ASSIGNED_BY_NAME))
            .andExpect(jsonPath("$.assignedToId").value(DEFAULT_ASSIGNED_TO_ID.intValue()))
            .andExpect(jsonPath("$.assignedToName").value(DEFAULT_ASSIGNED_TO_NAME))
            .andExpect(jsonPath("$.assignedDate").value(DEFAULT_ASSIGNED_DATE.toString()))
            .andExpect(jsonPath("$.assignedStatus").value(DEFAULT_ASSIGNED_STATUS))
            .andExpect(jsonPath("$.wipNotes").value(DEFAULT_WIP_NOTES))
            .andExpect(jsonPath("$.assignmentNotes").value(DEFAULT_ASSIGNMENT_NOTES))
            .andExpect(jsonPath("$.assignmentStatusNotes").value(DEFAULT_ASSIGNMENT_STATUS_NOTES))
            .andExpect(jsonPath("$.objectInstanceIdNo").value(DEFAULT_OBJECT_INSTANCE_ID_NO))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE))
            .andExpect(jsonPath("$.objectInstanceIdUuid").value(DEFAULT_OBJECT_INSTANCE_ID_UUID.toString()))
            .andExpect(jsonPath("$.targetedDate").value(DEFAULT_TARGETED_DATE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.wipQueueDetailsUuid").value(DEFAULT_WIP_QUEUE_DETAILS_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingWipQueueDetails() throws Exception {
        // Get the wipQueueDetails
        restWipQueueDetailsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewWipQueueDetails() throws Exception {
        // Initialize the database
        wipQueueDetailsRepository.saveAndFlush(wipQueueDetails);

        int databaseSizeBeforeUpdate = wipQueueDetailsRepository.findAll().size();

        // Update the wipQueueDetails
        WipQueueDetails updatedWipQueueDetails = wipQueueDetailsRepository.findById(wipQueueDetails.getWipQueueDetailsId()).get();
        // Disconnect from session so that the updates on updatedWipQueueDetails are not directly saved in db
        em.detach(updatedWipQueueDetails);
        updatedWipQueueDetails
            .wipStatusId(UPDATED_WIP_STATUS_ID)
            .wipStatusName(UPDATED_WIP_STATUS_NAME)
            .taskId(UPDATED_TASK_ID)
            .taskName(UPDATED_TASK_NAME)
            .objectId(UPDATED_OBJECT_ID)
            .objectName(UPDATED_OBJECT_NAME)
            .objectInstanceId(UPDATED_OBJECT_INSTANCE_ID)
            .wipSetById(UPDATED_WIP_SET_BY_ID)
            .wipSetByName(UPDATED_WIP_SET_BY_NAME)
            .wipSetDateTime(UPDATED_WIP_SET_DATE_TIME)
            .assignedById(UPDATED_ASSIGNED_BY_ID)
            .assignedByName(UPDATED_ASSIGNED_BY_NAME)
            .assignedToId(UPDATED_ASSIGNED_TO_ID)
            .assignedToName(UPDATED_ASSIGNED_TO_NAME)
            .assignedDate(UPDATED_ASSIGNED_DATE)
            .assignedStatus(UPDATED_ASSIGNED_STATUS)
            .wipNotes(UPDATED_WIP_NOTES)
            .assignmentNotes(UPDATED_ASSIGNMENT_NOTES)
            .assignmentStatusNotes(UPDATED_ASSIGNMENT_STATUS_NOTES)
            .objectInstanceIdNo(UPDATED_OBJECT_INSTANCE_ID_NO)
            .state(UPDATED_STATE)
            .objectInstanceIdUuid(UPDATED_OBJECT_INSTANCE_ID_UUID)
            .targetedDate(UPDATED_TARGETED_DATE)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .wipQueueDetailsUuid(UPDATED_WIP_QUEUE_DETAILS_UUID);
        WipQueueDetailsDTO wipQueueDetailsDTO = wipQueueDetailsMapper.toDto(updatedWipQueueDetails);

        restWipQueueDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, wipQueueDetailsDTO.getWipQueueDetailsId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(wipQueueDetailsDTO))
            )
            .andExpect(status().isOk());

        // Validate the WipQueueDetails in the database
        List<WipQueueDetails> wipQueueDetailsList = wipQueueDetailsRepository.findAll();
        assertThat(wipQueueDetailsList).hasSize(databaseSizeBeforeUpdate);
        WipQueueDetails testWipQueueDetails = wipQueueDetailsList.get(wipQueueDetailsList.size() - 1);
        assertThat(testWipQueueDetails.getWipStatusId()).isEqualTo(UPDATED_WIP_STATUS_ID);
        assertThat(testWipQueueDetails.getWipStatusName()).isEqualTo(UPDATED_WIP_STATUS_NAME);
        assertThat(testWipQueueDetails.getTaskId()).isEqualTo(UPDATED_TASK_ID);
        assertThat(testWipQueueDetails.getTaskName()).isEqualTo(UPDATED_TASK_NAME);
        assertThat(testWipQueueDetails.getObjectId()).isEqualTo(UPDATED_OBJECT_ID);
        assertThat(testWipQueueDetails.getObjectName()).isEqualTo(UPDATED_OBJECT_NAME);
        assertThat(testWipQueueDetails.getObjectInstanceId()).isEqualTo(UPDATED_OBJECT_INSTANCE_ID);
        assertThat(testWipQueueDetails.getWipSetById()).isEqualTo(UPDATED_WIP_SET_BY_ID);
        assertThat(testWipQueueDetails.getWipSetByName()).isEqualTo(UPDATED_WIP_SET_BY_NAME);
        assertThat(testWipQueueDetails.getWipSetDateTime()).isEqualTo(UPDATED_WIP_SET_DATE_TIME);
        assertThat(testWipQueueDetails.getAssignedById()).isEqualTo(UPDATED_ASSIGNED_BY_ID);
        assertThat(testWipQueueDetails.getAssignedByName()).isEqualTo(UPDATED_ASSIGNED_BY_NAME);
        assertThat(testWipQueueDetails.getAssignedToId()).isEqualTo(UPDATED_ASSIGNED_TO_ID);
        assertThat(testWipQueueDetails.getAssignedToName()).isEqualTo(UPDATED_ASSIGNED_TO_NAME);
        assertThat(testWipQueueDetails.getAssignedDate()).isEqualTo(UPDATED_ASSIGNED_DATE);
        assertThat(testWipQueueDetails.getAssignedStatus()).isEqualTo(UPDATED_ASSIGNED_STATUS);
        assertThat(testWipQueueDetails.getWipNotes()).isEqualTo(UPDATED_WIP_NOTES);
        assertThat(testWipQueueDetails.getAssignmentNotes()).isEqualTo(UPDATED_ASSIGNMENT_NOTES);
        assertThat(testWipQueueDetails.getAssignmentStatusNotes()).isEqualTo(UPDATED_ASSIGNMENT_STATUS_NOTES);
        assertThat(testWipQueueDetails.getObjectInstanceIdNo()).isEqualTo(UPDATED_OBJECT_INSTANCE_ID_NO);
        assertThat(testWipQueueDetails.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testWipQueueDetails.getObjectInstanceIdUuid()).isEqualTo(UPDATED_OBJECT_INSTANCE_ID_UUID);
        assertThat(testWipQueueDetails.getTargetedDate()).isEqualTo(UPDATED_TARGETED_DATE);
        assertThat(testWipQueueDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testWipQueueDetails.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testWipQueueDetails.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testWipQueueDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testWipQueueDetails.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testWipQueueDetails.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testWipQueueDetails.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testWipQueueDetails.getWipQueueDetailsUuid()).isEqualTo(UPDATED_WIP_QUEUE_DETAILS_UUID);
    }

    @Test
    @Transactional
    void putNonExistingWipQueueDetails() throws Exception {
        int databaseSizeBeforeUpdate = wipQueueDetailsRepository.findAll().size();
        wipQueueDetails.setWipQueueDetailsId(count.incrementAndGet());

        // Create the WipQueueDetails
        WipQueueDetailsDTO wipQueueDetailsDTO = wipQueueDetailsMapper.toDto(wipQueueDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWipQueueDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, wipQueueDetailsDTO.getWipQueueDetailsId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(wipQueueDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WipQueueDetails in the database
        List<WipQueueDetails> wipQueueDetailsList = wipQueueDetailsRepository.findAll();
        assertThat(wipQueueDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchWipQueueDetails() throws Exception {
        int databaseSizeBeforeUpdate = wipQueueDetailsRepository.findAll().size();
        wipQueueDetails.setWipQueueDetailsId(count.incrementAndGet());

        // Create the WipQueueDetails
        WipQueueDetailsDTO wipQueueDetailsDTO = wipQueueDetailsMapper.toDto(wipQueueDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWipQueueDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(wipQueueDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WipQueueDetails in the database
        List<WipQueueDetails> wipQueueDetailsList = wipQueueDetailsRepository.findAll();
        assertThat(wipQueueDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamWipQueueDetails() throws Exception {
        int databaseSizeBeforeUpdate = wipQueueDetailsRepository.findAll().size();
        wipQueueDetails.setWipQueueDetailsId(count.incrementAndGet());

        // Create the WipQueueDetails
        WipQueueDetailsDTO wipQueueDetailsDTO = wipQueueDetailsMapper.toDto(wipQueueDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWipQueueDetailsMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(wipQueueDetailsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the WipQueueDetails in the database
        List<WipQueueDetails> wipQueueDetailsList = wipQueueDetailsRepository.findAll();
        assertThat(wipQueueDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateWipQueueDetailsWithPatch() throws Exception {
        // Initialize the database
        wipQueueDetailsRepository.saveAndFlush(wipQueueDetails);

        int databaseSizeBeforeUpdate = wipQueueDetailsRepository.findAll().size();

        // Update the wipQueueDetails using partial update
        WipQueueDetails partialUpdatedWipQueueDetails = new WipQueueDetails();
        partialUpdatedWipQueueDetails.setWipQueueDetailsId(wipQueueDetails.getWipQueueDetailsId());

        partialUpdatedWipQueueDetails
            .taskId(UPDATED_TASK_ID)
            .objectId(UPDATED_OBJECT_ID)
            .objectName(UPDATED_OBJECT_NAME)
            .objectInstanceId(UPDATED_OBJECT_INSTANCE_ID)
            .wipSetById(UPDATED_WIP_SET_BY_ID)
            .wipSetByName(UPDATED_WIP_SET_BY_NAME)
            .wipSetDateTime(UPDATED_WIP_SET_DATE_TIME)
            .assignedById(UPDATED_ASSIGNED_BY_ID)
            .assignedToId(UPDATED_ASSIGNED_TO_ID)
            .assignedStatus(UPDATED_ASSIGNED_STATUS)
            .assignmentStatusNotes(UPDATED_ASSIGNMENT_STATUS_NOTES)
            .objectInstanceIdNo(UPDATED_OBJECT_INSTANCE_ID_NO)
            .state(UPDATED_STATE)
            .objectInstanceIdUuid(UPDATED_OBJECT_INSTANCE_ID_UUID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE);

        restWipQueueDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWipQueueDetails.getWipQueueDetailsId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedWipQueueDetails))
            )
            .andExpect(status().isOk());

        // Validate the WipQueueDetails in the database
        List<WipQueueDetails> wipQueueDetailsList = wipQueueDetailsRepository.findAll();
        assertThat(wipQueueDetailsList).hasSize(databaseSizeBeforeUpdate);
        WipQueueDetails testWipQueueDetails = wipQueueDetailsList.get(wipQueueDetailsList.size() - 1);
        assertThat(testWipQueueDetails.getWipStatusId()).isEqualTo(DEFAULT_WIP_STATUS_ID);
        assertThat(testWipQueueDetails.getWipStatusName()).isEqualTo(DEFAULT_WIP_STATUS_NAME);
        assertThat(testWipQueueDetails.getTaskId()).isEqualTo(UPDATED_TASK_ID);
        assertThat(testWipQueueDetails.getTaskName()).isEqualTo(DEFAULT_TASK_NAME);
        assertThat(testWipQueueDetails.getObjectId()).isEqualTo(UPDATED_OBJECT_ID);
        assertThat(testWipQueueDetails.getObjectName()).isEqualTo(UPDATED_OBJECT_NAME);
        assertThat(testWipQueueDetails.getObjectInstanceId()).isEqualTo(UPDATED_OBJECT_INSTANCE_ID);
        assertThat(testWipQueueDetails.getWipSetById()).isEqualTo(UPDATED_WIP_SET_BY_ID);
        assertThat(testWipQueueDetails.getWipSetByName()).isEqualTo(UPDATED_WIP_SET_BY_NAME);
        assertThat(testWipQueueDetails.getWipSetDateTime()).isEqualTo(UPDATED_WIP_SET_DATE_TIME);
        assertThat(testWipQueueDetails.getAssignedById()).isEqualTo(UPDATED_ASSIGNED_BY_ID);
        assertThat(testWipQueueDetails.getAssignedByName()).isEqualTo(DEFAULT_ASSIGNED_BY_NAME);
        assertThat(testWipQueueDetails.getAssignedToId()).isEqualTo(UPDATED_ASSIGNED_TO_ID);
        assertThat(testWipQueueDetails.getAssignedToName()).isEqualTo(DEFAULT_ASSIGNED_TO_NAME);
        assertThat(testWipQueueDetails.getAssignedDate()).isEqualTo(DEFAULT_ASSIGNED_DATE);
        assertThat(testWipQueueDetails.getAssignedStatus()).isEqualTo(UPDATED_ASSIGNED_STATUS);
        assertThat(testWipQueueDetails.getWipNotes()).isEqualTo(DEFAULT_WIP_NOTES);
        assertThat(testWipQueueDetails.getAssignmentNotes()).isEqualTo(DEFAULT_ASSIGNMENT_NOTES);
        assertThat(testWipQueueDetails.getAssignmentStatusNotes()).isEqualTo(UPDATED_ASSIGNMENT_STATUS_NOTES);
        assertThat(testWipQueueDetails.getObjectInstanceIdNo()).isEqualTo(UPDATED_OBJECT_INSTANCE_ID_NO);
        assertThat(testWipQueueDetails.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testWipQueueDetails.getObjectInstanceIdUuid()).isEqualTo(UPDATED_OBJECT_INSTANCE_ID_UUID);
        assertThat(testWipQueueDetails.getTargetedDate()).isEqualTo(DEFAULT_TARGETED_DATE);
        assertThat(testWipQueueDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testWipQueueDetails.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testWipQueueDetails.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testWipQueueDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testWipQueueDetails.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testWipQueueDetails.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testWipQueueDetails.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testWipQueueDetails.getWipQueueDetailsUuid()).isEqualTo(DEFAULT_WIP_QUEUE_DETAILS_UUID);
    }

    @Test
    @Transactional
    void fullUpdateWipQueueDetailsWithPatch() throws Exception {
        // Initialize the database
        wipQueueDetailsRepository.saveAndFlush(wipQueueDetails);

        int databaseSizeBeforeUpdate = wipQueueDetailsRepository.findAll().size();

        // Update the wipQueueDetails using partial update
        WipQueueDetails partialUpdatedWipQueueDetails = new WipQueueDetails();
        partialUpdatedWipQueueDetails.setWipQueueDetailsId(wipQueueDetails.getWipQueueDetailsId());

        partialUpdatedWipQueueDetails
            .wipStatusId(UPDATED_WIP_STATUS_ID)
            .wipStatusName(UPDATED_WIP_STATUS_NAME)
            .taskId(UPDATED_TASK_ID)
            .taskName(UPDATED_TASK_NAME)
            .objectId(UPDATED_OBJECT_ID)
            .objectName(UPDATED_OBJECT_NAME)
            .objectInstanceId(UPDATED_OBJECT_INSTANCE_ID)
            .wipSetById(UPDATED_WIP_SET_BY_ID)
            .wipSetByName(UPDATED_WIP_SET_BY_NAME)
            .wipSetDateTime(UPDATED_WIP_SET_DATE_TIME)
            .assignedById(UPDATED_ASSIGNED_BY_ID)
            .assignedByName(UPDATED_ASSIGNED_BY_NAME)
            .assignedToId(UPDATED_ASSIGNED_TO_ID)
            .assignedToName(UPDATED_ASSIGNED_TO_NAME)
            .assignedDate(UPDATED_ASSIGNED_DATE)
            .assignedStatus(UPDATED_ASSIGNED_STATUS)
            .wipNotes(UPDATED_WIP_NOTES)
            .assignmentNotes(UPDATED_ASSIGNMENT_NOTES)
            .assignmentStatusNotes(UPDATED_ASSIGNMENT_STATUS_NOTES)
            .objectInstanceIdNo(UPDATED_OBJECT_INSTANCE_ID_NO)
            .state(UPDATED_STATE)
            .objectInstanceIdUuid(UPDATED_OBJECT_INSTANCE_ID_UUID)
            .targetedDate(UPDATED_TARGETED_DATE)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .wipQueueDetailsUuid(UPDATED_WIP_QUEUE_DETAILS_UUID);

        restWipQueueDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWipQueueDetails.getWipQueueDetailsId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedWipQueueDetails))
            )
            .andExpect(status().isOk());

        // Validate the WipQueueDetails in the database
        List<WipQueueDetails> wipQueueDetailsList = wipQueueDetailsRepository.findAll();
        assertThat(wipQueueDetailsList).hasSize(databaseSizeBeforeUpdate);
        WipQueueDetails testWipQueueDetails = wipQueueDetailsList.get(wipQueueDetailsList.size() - 1);
        assertThat(testWipQueueDetails.getWipStatusId()).isEqualTo(UPDATED_WIP_STATUS_ID);
        assertThat(testWipQueueDetails.getWipStatusName()).isEqualTo(UPDATED_WIP_STATUS_NAME);
        assertThat(testWipQueueDetails.getTaskId()).isEqualTo(UPDATED_TASK_ID);
        assertThat(testWipQueueDetails.getTaskName()).isEqualTo(UPDATED_TASK_NAME);
        assertThat(testWipQueueDetails.getObjectId()).isEqualTo(UPDATED_OBJECT_ID);
        assertThat(testWipQueueDetails.getObjectName()).isEqualTo(UPDATED_OBJECT_NAME);
        assertThat(testWipQueueDetails.getObjectInstanceId()).isEqualTo(UPDATED_OBJECT_INSTANCE_ID);
        assertThat(testWipQueueDetails.getWipSetById()).isEqualTo(UPDATED_WIP_SET_BY_ID);
        assertThat(testWipQueueDetails.getWipSetByName()).isEqualTo(UPDATED_WIP_SET_BY_NAME);
        assertThat(testWipQueueDetails.getWipSetDateTime()).isEqualTo(UPDATED_WIP_SET_DATE_TIME);
        assertThat(testWipQueueDetails.getAssignedById()).isEqualTo(UPDATED_ASSIGNED_BY_ID);
        assertThat(testWipQueueDetails.getAssignedByName()).isEqualTo(UPDATED_ASSIGNED_BY_NAME);
        assertThat(testWipQueueDetails.getAssignedToId()).isEqualTo(UPDATED_ASSIGNED_TO_ID);
        assertThat(testWipQueueDetails.getAssignedToName()).isEqualTo(UPDATED_ASSIGNED_TO_NAME);
        assertThat(testWipQueueDetails.getAssignedDate()).isEqualTo(UPDATED_ASSIGNED_DATE);
        assertThat(testWipQueueDetails.getAssignedStatus()).isEqualTo(UPDATED_ASSIGNED_STATUS);
        assertThat(testWipQueueDetails.getWipNotes()).isEqualTo(UPDATED_WIP_NOTES);
        assertThat(testWipQueueDetails.getAssignmentNotes()).isEqualTo(UPDATED_ASSIGNMENT_NOTES);
        assertThat(testWipQueueDetails.getAssignmentStatusNotes()).isEqualTo(UPDATED_ASSIGNMENT_STATUS_NOTES);
        assertThat(testWipQueueDetails.getObjectInstanceIdNo()).isEqualTo(UPDATED_OBJECT_INSTANCE_ID_NO);
        assertThat(testWipQueueDetails.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testWipQueueDetails.getObjectInstanceIdUuid()).isEqualTo(UPDATED_OBJECT_INSTANCE_ID_UUID);
        assertThat(testWipQueueDetails.getTargetedDate()).isEqualTo(UPDATED_TARGETED_DATE);
        assertThat(testWipQueueDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testWipQueueDetails.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testWipQueueDetails.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testWipQueueDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testWipQueueDetails.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testWipQueueDetails.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testWipQueueDetails.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testWipQueueDetails.getWipQueueDetailsUuid()).isEqualTo(UPDATED_WIP_QUEUE_DETAILS_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingWipQueueDetails() throws Exception {
        int databaseSizeBeforeUpdate = wipQueueDetailsRepository.findAll().size();
        wipQueueDetails.setWipQueueDetailsId(count.incrementAndGet());

        // Create the WipQueueDetails
        WipQueueDetailsDTO wipQueueDetailsDTO = wipQueueDetailsMapper.toDto(wipQueueDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWipQueueDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, wipQueueDetailsDTO.getWipQueueDetailsId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(wipQueueDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WipQueueDetails in the database
        List<WipQueueDetails> wipQueueDetailsList = wipQueueDetailsRepository.findAll();
        assertThat(wipQueueDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchWipQueueDetails() throws Exception {
        int databaseSizeBeforeUpdate = wipQueueDetailsRepository.findAll().size();
        wipQueueDetails.setWipQueueDetailsId(count.incrementAndGet());

        // Create the WipQueueDetails
        WipQueueDetailsDTO wipQueueDetailsDTO = wipQueueDetailsMapper.toDto(wipQueueDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWipQueueDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(wipQueueDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WipQueueDetails in the database
        List<WipQueueDetails> wipQueueDetailsList = wipQueueDetailsRepository.findAll();
        assertThat(wipQueueDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamWipQueueDetails() throws Exception {
        int databaseSizeBeforeUpdate = wipQueueDetailsRepository.findAll().size();
        wipQueueDetails.setWipQueueDetailsId(count.incrementAndGet());

        // Create the WipQueueDetails
        WipQueueDetailsDTO wipQueueDetailsDTO = wipQueueDetailsMapper.toDto(wipQueueDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWipQueueDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(wipQueueDetailsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the WipQueueDetails in the database
        List<WipQueueDetails> wipQueueDetailsList = wipQueueDetailsRepository.findAll();
        assertThat(wipQueueDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteWipQueueDetails() throws Exception {
        // Initialize the database
        wipQueueDetailsRepository.saveAndFlush(wipQueueDetails);

        int databaseSizeBeforeDelete = wipQueueDetailsRepository.findAll().size();

        // Delete the wipQueueDetails
        restWipQueueDetailsMockMvc
            .perform(delete(ENTITY_API_URL_ID, wipQueueDetails.getWipQueueDetailsId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WipQueueDetails> wipQueueDetailsList = wipQueueDetailsRepository.findAll();
        assertThat(wipQueueDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
