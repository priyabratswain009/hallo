package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.WipQueueOwner;
import com.sunknowledge.dme.rcm.repository.WipQueueOwnerRepository;
import com.sunknowledge.dme.rcm.service.dto.WipQueueOwnerDTO;
import com.sunknowledge.dme.rcm.service.mapper.WipQueueOwnerMapper;
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
 * Integration tests for the {@link WipQueueOwnerResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class WipQueueOwnerResourceIT {

    private static final String DEFAULT_WIP_STATUS_ID = "AAAAAAAAAA";
    private static final String UPDATED_WIP_STATUS_ID = "BBBBBBBBBB";

    private static final String DEFAULT_WIP_STATUS_NAME = "AAAAAAAAAA";
    private static final String UPDATED_WIP_STATUS_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_TASK_ID = 1L;
    private static final Long UPDATED_TASK_ID = 2L;

    private static final String DEFAULT_TASK_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TASK_NAME = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_WIP_QUEUE_OWNER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_WIP_QUEUE_OWNER_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/wip-queue-owners";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{wipQueueOwnerId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private WipQueueOwnerRepository wipQueueOwnerRepository;

    @Autowired
    private WipQueueOwnerMapper wipQueueOwnerMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWipQueueOwnerMockMvc;

    private WipQueueOwner wipQueueOwner;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WipQueueOwner createEntity(EntityManager em) {
        WipQueueOwner wipQueueOwner = new WipQueueOwner()
            .wipStatusId(DEFAULT_WIP_STATUS_ID)
            .wipStatusName(DEFAULT_WIP_STATUS_NAME)
            .taskId(DEFAULT_TASK_ID)
            .taskName(DEFAULT_TASK_NAME)
            .assignedById(DEFAULT_ASSIGNED_BY_ID)
            .assignedByName(DEFAULT_ASSIGNED_BY_NAME)
            .assignedToId(DEFAULT_ASSIGNED_TO_ID)
            .assignedToName(DEFAULT_ASSIGNED_TO_NAME)
            .assignedDate(DEFAULT_ASSIGNED_DATE)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .wipQueueOwnerUuid(DEFAULT_WIP_QUEUE_OWNER_UUID);
        return wipQueueOwner;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WipQueueOwner createUpdatedEntity(EntityManager em) {
        WipQueueOwner wipQueueOwner = new WipQueueOwner()
            .wipStatusId(UPDATED_WIP_STATUS_ID)
            .wipStatusName(UPDATED_WIP_STATUS_NAME)
            .taskId(UPDATED_TASK_ID)
            .taskName(UPDATED_TASK_NAME)
            .assignedById(UPDATED_ASSIGNED_BY_ID)
            .assignedByName(UPDATED_ASSIGNED_BY_NAME)
            .assignedToId(UPDATED_ASSIGNED_TO_ID)
            .assignedToName(UPDATED_ASSIGNED_TO_NAME)
            .assignedDate(UPDATED_ASSIGNED_DATE)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .wipQueueOwnerUuid(UPDATED_WIP_QUEUE_OWNER_UUID);
        return wipQueueOwner;
    }

    @BeforeEach
    public void initTest() {
        wipQueueOwner = createEntity(em);
    }

    @Test
    @Transactional
    void createWipQueueOwner() throws Exception {
        int databaseSizeBeforeCreate = wipQueueOwnerRepository.findAll().size();
        // Create the WipQueueOwner
        WipQueueOwnerDTO wipQueueOwnerDTO = wipQueueOwnerMapper.toDto(wipQueueOwner);
        restWipQueueOwnerMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(wipQueueOwnerDTO))
            )
            .andExpect(status().isCreated());

        // Validate the WipQueueOwner in the database
        List<WipQueueOwner> wipQueueOwnerList = wipQueueOwnerRepository.findAll();
        assertThat(wipQueueOwnerList).hasSize(databaseSizeBeforeCreate + 1);
        WipQueueOwner testWipQueueOwner = wipQueueOwnerList.get(wipQueueOwnerList.size() - 1);
        assertThat(testWipQueueOwner.getWipStatusId()).isEqualTo(DEFAULT_WIP_STATUS_ID);
        assertThat(testWipQueueOwner.getWipStatusName()).isEqualTo(DEFAULT_WIP_STATUS_NAME);
        assertThat(testWipQueueOwner.getTaskId()).isEqualTo(DEFAULT_TASK_ID);
        assertThat(testWipQueueOwner.getTaskName()).isEqualTo(DEFAULT_TASK_NAME);
        assertThat(testWipQueueOwner.getAssignedById()).isEqualTo(DEFAULT_ASSIGNED_BY_ID);
        assertThat(testWipQueueOwner.getAssignedByName()).isEqualTo(DEFAULT_ASSIGNED_BY_NAME);
        assertThat(testWipQueueOwner.getAssignedToId()).isEqualTo(DEFAULT_ASSIGNED_TO_ID);
        assertThat(testWipQueueOwner.getAssignedToName()).isEqualTo(DEFAULT_ASSIGNED_TO_NAME);
        assertThat(testWipQueueOwner.getAssignedDate()).isEqualTo(DEFAULT_ASSIGNED_DATE);
        assertThat(testWipQueueOwner.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testWipQueueOwner.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testWipQueueOwner.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testWipQueueOwner.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testWipQueueOwner.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testWipQueueOwner.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testWipQueueOwner.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testWipQueueOwner.getWipQueueOwnerUuid()).isEqualTo(DEFAULT_WIP_QUEUE_OWNER_UUID);
    }

    @Test
    @Transactional
    void createWipQueueOwnerWithExistingId() throws Exception {
        // Create the WipQueueOwner with an existing ID
        wipQueueOwner.setWipQueueOwnerId(1L);
        WipQueueOwnerDTO wipQueueOwnerDTO = wipQueueOwnerMapper.toDto(wipQueueOwner);

        int databaseSizeBeforeCreate = wipQueueOwnerRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restWipQueueOwnerMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(wipQueueOwnerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WipQueueOwner in the database
        List<WipQueueOwner> wipQueueOwnerList = wipQueueOwnerRepository.findAll();
        assertThat(wipQueueOwnerList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllWipQueueOwners() throws Exception {
        // Initialize the database
        wipQueueOwnerRepository.saveAndFlush(wipQueueOwner);

        // Get all the wipQueueOwnerList
        restWipQueueOwnerMockMvc
            .perform(get(ENTITY_API_URL + "?sort=wipQueueOwnerId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].wipQueueOwnerId").value(hasItem(wipQueueOwner.getWipQueueOwnerId().intValue())))
            .andExpect(jsonPath("$.[*].wipStatusId").value(hasItem(DEFAULT_WIP_STATUS_ID)))
            .andExpect(jsonPath("$.[*].wipStatusName").value(hasItem(DEFAULT_WIP_STATUS_NAME)))
            .andExpect(jsonPath("$.[*].taskId").value(hasItem(DEFAULT_TASK_ID.intValue())))
            .andExpect(jsonPath("$.[*].taskName").value(hasItem(DEFAULT_TASK_NAME)))
            .andExpect(jsonPath("$.[*].assignedById").value(hasItem(DEFAULT_ASSIGNED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].assignedByName").value(hasItem(DEFAULT_ASSIGNED_BY_NAME)))
            .andExpect(jsonPath("$.[*].assignedToId").value(hasItem(DEFAULT_ASSIGNED_TO_ID.intValue())))
            .andExpect(jsonPath("$.[*].assignedToName").value(hasItem(DEFAULT_ASSIGNED_TO_NAME)))
            .andExpect(jsonPath("$.[*].assignedDate").value(hasItem(DEFAULT_ASSIGNED_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].wipQueueOwnerUuid").value(hasItem(DEFAULT_WIP_QUEUE_OWNER_UUID.toString())));
    }

    @Test
    @Transactional
    void getWipQueueOwner() throws Exception {
        // Initialize the database
        wipQueueOwnerRepository.saveAndFlush(wipQueueOwner);

        // Get the wipQueueOwner
        restWipQueueOwnerMockMvc
            .perform(get(ENTITY_API_URL_ID, wipQueueOwner.getWipQueueOwnerId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.wipQueueOwnerId").value(wipQueueOwner.getWipQueueOwnerId().intValue()))
            .andExpect(jsonPath("$.wipStatusId").value(DEFAULT_WIP_STATUS_ID))
            .andExpect(jsonPath("$.wipStatusName").value(DEFAULT_WIP_STATUS_NAME))
            .andExpect(jsonPath("$.taskId").value(DEFAULT_TASK_ID.intValue()))
            .andExpect(jsonPath("$.taskName").value(DEFAULT_TASK_NAME))
            .andExpect(jsonPath("$.assignedById").value(DEFAULT_ASSIGNED_BY_ID.intValue()))
            .andExpect(jsonPath("$.assignedByName").value(DEFAULT_ASSIGNED_BY_NAME))
            .andExpect(jsonPath("$.assignedToId").value(DEFAULT_ASSIGNED_TO_ID.intValue()))
            .andExpect(jsonPath("$.assignedToName").value(DEFAULT_ASSIGNED_TO_NAME))
            .andExpect(jsonPath("$.assignedDate").value(DEFAULT_ASSIGNED_DATE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.wipQueueOwnerUuid").value(DEFAULT_WIP_QUEUE_OWNER_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingWipQueueOwner() throws Exception {
        // Get the wipQueueOwner
        restWipQueueOwnerMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewWipQueueOwner() throws Exception {
        // Initialize the database
        wipQueueOwnerRepository.saveAndFlush(wipQueueOwner);

        int databaseSizeBeforeUpdate = wipQueueOwnerRepository.findAll().size();

        // Update the wipQueueOwner
        WipQueueOwner updatedWipQueueOwner = wipQueueOwnerRepository.findById(wipQueueOwner.getWipQueueOwnerId()).get();
        // Disconnect from session so that the updates on updatedWipQueueOwner are not directly saved in db
        em.detach(updatedWipQueueOwner);
        updatedWipQueueOwner
            .wipStatusId(UPDATED_WIP_STATUS_ID)
            .wipStatusName(UPDATED_WIP_STATUS_NAME)
            .taskId(UPDATED_TASK_ID)
            .taskName(UPDATED_TASK_NAME)
            .assignedById(UPDATED_ASSIGNED_BY_ID)
            .assignedByName(UPDATED_ASSIGNED_BY_NAME)
            .assignedToId(UPDATED_ASSIGNED_TO_ID)
            .assignedToName(UPDATED_ASSIGNED_TO_NAME)
            .assignedDate(UPDATED_ASSIGNED_DATE)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .wipQueueOwnerUuid(UPDATED_WIP_QUEUE_OWNER_UUID);
        WipQueueOwnerDTO wipQueueOwnerDTO = wipQueueOwnerMapper.toDto(updatedWipQueueOwner);

        restWipQueueOwnerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, wipQueueOwnerDTO.getWipQueueOwnerId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(wipQueueOwnerDTO))
            )
            .andExpect(status().isOk());

        // Validate the WipQueueOwner in the database
        List<WipQueueOwner> wipQueueOwnerList = wipQueueOwnerRepository.findAll();
        assertThat(wipQueueOwnerList).hasSize(databaseSizeBeforeUpdate);
        WipQueueOwner testWipQueueOwner = wipQueueOwnerList.get(wipQueueOwnerList.size() - 1);
        assertThat(testWipQueueOwner.getWipStatusId()).isEqualTo(UPDATED_WIP_STATUS_ID);
        assertThat(testWipQueueOwner.getWipStatusName()).isEqualTo(UPDATED_WIP_STATUS_NAME);
        assertThat(testWipQueueOwner.getTaskId()).isEqualTo(UPDATED_TASK_ID);
        assertThat(testWipQueueOwner.getTaskName()).isEqualTo(UPDATED_TASK_NAME);
        assertThat(testWipQueueOwner.getAssignedById()).isEqualTo(UPDATED_ASSIGNED_BY_ID);
        assertThat(testWipQueueOwner.getAssignedByName()).isEqualTo(UPDATED_ASSIGNED_BY_NAME);
        assertThat(testWipQueueOwner.getAssignedToId()).isEqualTo(UPDATED_ASSIGNED_TO_ID);
        assertThat(testWipQueueOwner.getAssignedToName()).isEqualTo(UPDATED_ASSIGNED_TO_NAME);
        assertThat(testWipQueueOwner.getAssignedDate()).isEqualTo(UPDATED_ASSIGNED_DATE);
        assertThat(testWipQueueOwner.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testWipQueueOwner.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testWipQueueOwner.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testWipQueueOwner.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testWipQueueOwner.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testWipQueueOwner.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testWipQueueOwner.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testWipQueueOwner.getWipQueueOwnerUuid()).isEqualTo(UPDATED_WIP_QUEUE_OWNER_UUID);
    }

    @Test
    @Transactional
    void putNonExistingWipQueueOwner() throws Exception {
        int databaseSizeBeforeUpdate = wipQueueOwnerRepository.findAll().size();
        wipQueueOwner.setWipQueueOwnerId(count.incrementAndGet());

        // Create the WipQueueOwner
        WipQueueOwnerDTO wipQueueOwnerDTO = wipQueueOwnerMapper.toDto(wipQueueOwner);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWipQueueOwnerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, wipQueueOwnerDTO.getWipQueueOwnerId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(wipQueueOwnerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WipQueueOwner in the database
        List<WipQueueOwner> wipQueueOwnerList = wipQueueOwnerRepository.findAll();
        assertThat(wipQueueOwnerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchWipQueueOwner() throws Exception {
        int databaseSizeBeforeUpdate = wipQueueOwnerRepository.findAll().size();
        wipQueueOwner.setWipQueueOwnerId(count.incrementAndGet());

        // Create the WipQueueOwner
        WipQueueOwnerDTO wipQueueOwnerDTO = wipQueueOwnerMapper.toDto(wipQueueOwner);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWipQueueOwnerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(wipQueueOwnerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WipQueueOwner in the database
        List<WipQueueOwner> wipQueueOwnerList = wipQueueOwnerRepository.findAll();
        assertThat(wipQueueOwnerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamWipQueueOwner() throws Exception {
        int databaseSizeBeforeUpdate = wipQueueOwnerRepository.findAll().size();
        wipQueueOwner.setWipQueueOwnerId(count.incrementAndGet());

        // Create the WipQueueOwner
        WipQueueOwnerDTO wipQueueOwnerDTO = wipQueueOwnerMapper.toDto(wipQueueOwner);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWipQueueOwnerMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(wipQueueOwnerDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the WipQueueOwner in the database
        List<WipQueueOwner> wipQueueOwnerList = wipQueueOwnerRepository.findAll();
        assertThat(wipQueueOwnerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateWipQueueOwnerWithPatch() throws Exception {
        // Initialize the database
        wipQueueOwnerRepository.saveAndFlush(wipQueueOwner);

        int databaseSizeBeforeUpdate = wipQueueOwnerRepository.findAll().size();

        // Update the wipQueueOwner using partial update
        WipQueueOwner partialUpdatedWipQueueOwner = new WipQueueOwner();
        partialUpdatedWipQueueOwner.setWipQueueOwnerId(wipQueueOwner.getWipQueueOwnerId());

        partialUpdatedWipQueueOwner
            .wipStatusName(UPDATED_WIP_STATUS_NAME)
            .taskId(UPDATED_TASK_ID)
            .assignedById(UPDATED_ASSIGNED_BY_ID)
            .assignedToName(UPDATED_ASSIGNED_TO_NAME)
            .assignedDate(UPDATED_ASSIGNED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE);

        restWipQueueOwnerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWipQueueOwner.getWipQueueOwnerId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedWipQueueOwner))
            )
            .andExpect(status().isOk());

        // Validate the WipQueueOwner in the database
        List<WipQueueOwner> wipQueueOwnerList = wipQueueOwnerRepository.findAll();
        assertThat(wipQueueOwnerList).hasSize(databaseSizeBeforeUpdate);
        WipQueueOwner testWipQueueOwner = wipQueueOwnerList.get(wipQueueOwnerList.size() - 1);
        assertThat(testWipQueueOwner.getWipStatusId()).isEqualTo(DEFAULT_WIP_STATUS_ID);
        assertThat(testWipQueueOwner.getWipStatusName()).isEqualTo(UPDATED_WIP_STATUS_NAME);
        assertThat(testWipQueueOwner.getTaskId()).isEqualTo(UPDATED_TASK_ID);
        assertThat(testWipQueueOwner.getTaskName()).isEqualTo(DEFAULT_TASK_NAME);
        assertThat(testWipQueueOwner.getAssignedById()).isEqualTo(UPDATED_ASSIGNED_BY_ID);
        assertThat(testWipQueueOwner.getAssignedByName()).isEqualTo(DEFAULT_ASSIGNED_BY_NAME);
        assertThat(testWipQueueOwner.getAssignedToId()).isEqualTo(DEFAULT_ASSIGNED_TO_ID);
        assertThat(testWipQueueOwner.getAssignedToName()).isEqualTo(UPDATED_ASSIGNED_TO_NAME);
        assertThat(testWipQueueOwner.getAssignedDate()).isEqualTo(UPDATED_ASSIGNED_DATE);
        assertThat(testWipQueueOwner.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testWipQueueOwner.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testWipQueueOwner.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testWipQueueOwner.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testWipQueueOwner.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testWipQueueOwner.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testWipQueueOwner.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testWipQueueOwner.getWipQueueOwnerUuid()).isEqualTo(DEFAULT_WIP_QUEUE_OWNER_UUID);
    }

    @Test
    @Transactional
    void fullUpdateWipQueueOwnerWithPatch() throws Exception {
        // Initialize the database
        wipQueueOwnerRepository.saveAndFlush(wipQueueOwner);

        int databaseSizeBeforeUpdate = wipQueueOwnerRepository.findAll().size();

        // Update the wipQueueOwner using partial update
        WipQueueOwner partialUpdatedWipQueueOwner = new WipQueueOwner();
        partialUpdatedWipQueueOwner.setWipQueueOwnerId(wipQueueOwner.getWipQueueOwnerId());

        partialUpdatedWipQueueOwner
            .wipStatusId(UPDATED_WIP_STATUS_ID)
            .wipStatusName(UPDATED_WIP_STATUS_NAME)
            .taskId(UPDATED_TASK_ID)
            .taskName(UPDATED_TASK_NAME)
            .assignedById(UPDATED_ASSIGNED_BY_ID)
            .assignedByName(UPDATED_ASSIGNED_BY_NAME)
            .assignedToId(UPDATED_ASSIGNED_TO_ID)
            .assignedToName(UPDATED_ASSIGNED_TO_NAME)
            .assignedDate(UPDATED_ASSIGNED_DATE)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .wipQueueOwnerUuid(UPDATED_WIP_QUEUE_OWNER_UUID);

        restWipQueueOwnerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWipQueueOwner.getWipQueueOwnerId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedWipQueueOwner))
            )
            .andExpect(status().isOk());

        // Validate the WipQueueOwner in the database
        List<WipQueueOwner> wipQueueOwnerList = wipQueueOwnerRepository.findAll();
        assertThat(wipQueueOwnerList).hasSize(databaseSizeBeforeUpdate);
        WipQueueOwner testWipQueueOwner = wipQueueOwnerList.get(wipQueueOwnerList.size() - 1);
        assertThat(testWipQueueOwner.getWipStatusId()).isEqualTo(UPDATED_WIP_STATUS_ID);
        assertThat(testWipQueueOwner.getWipStatusName()).isEqualTo(UPDATED_WIP_STATUS_NAME);
        assertThat(testWipQueueOwner.getTaskId()).isEqualTo(UPDATED_TASK_ID);
        assertThat(testWipQueueOwner.getTaskName()).isEqualTo(UPDATED_TASK_NAME);
        assertThat(testWipQueueOwner.getAssignedById()).isEqualTo(UPDATED_ASSIGNED_BY_ID);
        assertThat(testWipQueueOwner.getAssignedByName()).isEqualTo(UPDATED_ASSIGNED_BY_NAME);
        assertThat(testWipQueueOwner.getAssignedToId()).isEqualTo(UPDATED_ASSIGNED_TO_ID);
        assertThat(testWipQueueOwner.getAssignedToName()).isEqualTo(UPDATED_ASSIGNED_TO_NAME);
        assertThat(testWipQueueOwner.getAssignedDate()).isEqualTo(UPDATED_ASSIGNED_DATE);
        assertThat(testWipQueueOwner.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testWipQueueOwner.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testWipQueueOwner.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testWipQueueOwner.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testWipQueueOwner.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testWipQueueOwner.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testWipQueueOwner.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testWipQueueOwner.getWipQueueOwnerUuid()).isEqualTo(UPDATED_WIP_QUEUE_OWNER_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingWipQueueOwner() throws Exception {
        int databaseSizeBeforeUpdate = wipQueueOwnerRepository.findAll().size();
        wipQueueOwner.setWipQueueOwnerId(count.incrementAndGet());

        // Create the WipQueueOwner
        WipQueueOwnerDTO wipQueueOwnerDTO = wipQueueOwnerMapper.toDto(wipQueueOwner);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWipQueueOwnerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, wipQueueOwnerDTO.getWipQueueOwnerId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(wipQueueOwnerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WipQueueOwner in the database
        List<WipQueueOwner> wipQueueOwnerList = wipQueueOwnerRepository.findAll();
        assertThat(wipQueueOwnerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchWipQueueOwner() throws Exception {
        int databaseSizeBeforeUpdate = wipQueueOwnerRepository.findAll().size();
        wipQueueOwner.setWipQueueOwnerId(count.incrementAndGet());

        // Create the WipQueueOwner
        WipQueueOwnerDTO wipQueueOwnerDTO = wipQueueOwnerMapper.toDto(wipQueueOwner);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWipQueueOwnerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(wipQueueOwnerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WipQueueOwner in the database
        List<WipQueueOwner> wipQueueOwnerList = wipQueueOwnerRepository.findAll();
        assertThat(wipQueueOwnerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamWipQueueOwner() throws Exception {
        int databaseSizeBeforeUpdate = wipQueueOwnerRepository.findAll().size();
        wipQueueOwner.setWipQueueOwnerId(count.incrementAndGet());

        // Create the WipQueueOwner
        WipQueueOwnerDTO wipQueueOwnerDTO = wipQueueOwnerMapper.toDto(wipQueueOwner);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWipQueueOwnerMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(wipQueueOwnerDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the WipQueueOwner in the database
        List<WipQueueOwner> wipQueueOwnerList = wipQueueOwnerRepository.findAll();
        assertThat(wipQueueOwnerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteWipQueueOwner() throws Exception {
        // Initialize the database
        wipQueueOwnerRepository.saveAndFlush(wipQueueOwner);

        int databaseSizeBeforeDelete = wipQueueOwnerRepository.findAll().size();

        // Delete the wipQueueOwner
        restWipQueueOwnerMockMvc
            .perform(delete(ENTITY_API_URL_ID, wipQueueOwner.getWipQueueOwnerId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WipQueueOwner> wipQueueOwnerList = wipQueueOwnerRepository.findAll();
        assertThat(wipQueueOwnerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
