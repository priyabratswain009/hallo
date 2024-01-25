package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.EndpointMaster;
import com.sunknowledge.dme.rcm.repository.EndpointMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.EndpointMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.EndpointMasterMapper;
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
 * Integration tests for the {@link EndpointMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EndpointMasterResourceIT {

    private static final String DEFAULT_ENDPOINT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ENDPOINT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ENDPOINT_GROUP = "AAAAAAAAAA";
    private static final String UPDATED_ENDPOINT_GROUP = "BBBBBBBBBB";

    private static final String DEFAULT_ENDPOINT_DESC = "AAAAAAAAAA";
    private static final String UPDATED_ENDPOINT_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_ENDPOINT_URL = "AAAAAAAAAA";
    private static final String UPDATED_ENDPOINT_URL = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_ENDPOINT_MASTER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_ENDPOINT_MASTER_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/endpoint-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{endpointId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private EndpointMasterRepository endpointMasterRepository;

    @Autowired
    private EndpointMasterMapper endpointMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEndpointMasterMockMvc;

    private EndpointMaster endpointMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EndpointMaster createEntity(EntityManager em) {
        EndpointMaster endpointMaster = new EndpointMaster()
            .endpointName(DEFAULT_ENDPOINT_NAME)
            .endpointGroup(DEFAULT_ENDPOINT_GROUP)
            .endpointDesc(DEFAULT_ENDPOINT_DESC)
            .endpointUrl(DEFAULT_ENDPOINT_URL)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .endpointMasterUuid(DEFAULT_ENDPOINT_MASTER_UUID);
        return endpointMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EndpointMaster createUpdatedEntity(EntityManager em) {
        EndpointMaster endpointMaster = new EndpointMaster()
            .endpointName(UPDATED_ENDPOINT_NAME)
            .endpointGroup(UPDATED_ENDPOINT_GROUP)
            .endpointDesc(UPDATED_ENDPOINT_DESC)
            .endpointUrl(UPDATED_ENDPOINT_URL)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .endpointMasterUuid(UPDATED_ENDPOINT_MASTER_UUID);
        return endpointMaster;
    }

    @BeforeEach
    public void initTest() {
        endpointMaster = createEntity(em);
    }

    @Test
    @Transactional
    void createEndpointMaster() throws Exception {
        int databaseSizeBeforeCreate = endpointMasterRepository.findAll().size();
        // Create the EndpointMaster
        EndpointMasterDTO endpointMasterDTO = endpointMasterMapper.toDto(endpointMaster);
        restEndpointMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(endpointMasterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the EndpointMaster in the database
        List<EndpointMaster> endpointMasterList = endpointMasterRepository.findAll();
        assertThat(endpointMasterList).hasSize(databaseSizeBeforeCreate + 1);
        EndpointMaster testEndpointMaster = endpointMasterList.get(endpointMasterList.size() - 1);
        assertThat(testEndpointMaster.getEndpointName()).isEqualTo(DEFAULT_ENDPOINT_NAME);
        assertThat(testEndpointMaster.getEndpointGroup()).isEqualTo(DEFAULT_ENDPOINT_GROUP);
        assertThat(testEndpointMaster.getEndpointDesc()).isEqualTo(DEFAULT_ENDPOINT_DESC);
        assertThat(testEndpointMaster.getEndpointUrl()).isEqualTo(DEFAULT_ENDPOINT_URL);
        assertThat(testEndpointMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testEndpointMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testEndpointMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testEndpointMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testEndpointMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testEndpointMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testEndpointMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testEndpointMaster.getEndpointMasterUuid()).isEqualTo(DEFAULT_ENDPOINT_MASTER_UUID);
    }

    @Test
    @Transactional
    void createEndpointMasterWithExistingId() throws Exception {
        // Create the EndpointMaster with an existing ID
        endpointMaster.setEndpointId(1L);
        EndpointMasterDTO endpointMasterDTO = endpointMasterMapper.toDto(endpointMaster);

        int databaseSizeBeforeCreate = endpointMasterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEndpointMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(endpointMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EndpointMaster in the database
        List<EndpointMaster> endpointMasterList = endpointMasterRepository.findAll();
        assertThat(endpointMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllEndpointMasters() throws Exception {
        // Initialize the database
        endpointMasterRepository.saveAndFlush(endpointMaster);

        // Get all the endpointMasterList
        restEndpointMasterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=endpointId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].endpointId").value(hasItem(endpointMaster.getEndpointId().intValue())))
            .andExpect(jsonPath("$.[*].endpointName").value(hasItem(DEFAULT_ENDPOINT_NAME)))
            .andExpect(jsonPath("$.[*].endpointGroup").value(hasItem(DEFAULT_ENDPOINT_GROUP)))
            .andExpect(jsonPath("$.[*].endpointDesc").value(hasItem(DEFAULT_ENDPOINT_DESC)))
            .andExpect(jsonPath("$.[*].endpointUrl").value(hasItem(DEFAULT_ENDPOINT_URL)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].endpointMasterUuid").value(hasItem(DEFAULT_ENDPOINT_MASTER_UUID.toString())));
    }

    @Test
    @Transactional
    void getEndpointMaster() throws Exception {
        // Initialize the database
        endpointMasterRepository.saveAndFlush(endpointMaster);

        // Get the endpointMaster
        restEndpointMasterMockMvc
            .perform(get(ENTITY_API_URL_ID, endpointMaster.getEndpointId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.endpointId").value(endpointMaster.getEndpointId().intValue()))
            .andExpect(jsonPath("$.endpointName").value(DEFAULT_ENDPOINT_NAME))
            .andExpect(jsonPath("$.endpointGroup").value(DEFAULT_ENDPOINT_GROUP))
            .andExpect(jsonPath("$.endpointDesc").value(DEFAULT_ENDPOINT_DESC))
            .andExpect(jsonPath("$.endpointUrl").value(DEFAULT_ENDPOINT_URL))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.endpointMasterUuid").value(DEFAULT_ENDPOINT_MASTER_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingEndpointMaster() throws Exception {
        // Get the endpointMaster
        restEndpointMasterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingEndpointMaster() throws Exception {
        // Initialize the database
        endpointMasterRepository.saveAndFlush(endpointMaster);

        int databaseSizeBeforeUpdate = endpointMasterRepository.findAll().size();

        // Update the endpointMaster
        EndpointMaster updatedEndpointMaster = endpointMasterRepository.findById(endpointMaster.getEndpointId()).get();
        // Disconnect from session so that the updates on updatedEndpointMaster are not directly saved in db
        em.detach(updatedEndpointMaster);
        updatedEndpointMaster
            .endpointName(UPDATED_ENDPOINT_NAME)
            .endpointGroup(UPDATED_ENDPOINT_GROUP)
            .endpointDesc(UPDATED_ENDPOINT_DESC)
            .endpointUrl(UPDATED_ENDPOINT_URL)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .endpointMasterUuid(UPDATED_ENDPOINT_MASTER_UUID);
        EndpointMasterDTO endpointMasterDTO = endpointMasterMapper.toDto(updatedEndpointMaster);

        restEndpointMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, endpointMasterDTO.getEndpointId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(endpointMasterDTO))
            )
            .andExpect(status().isOk());

        // Validate the EndpointMaster in the database
        List<EndpointMaster> endpointMasterList = endpointMasterRepository.findAll();
        assertThat(endpointMasterList).hasSize(databaseSizeBeforeUpdate);
        EndpointMaster testEndpointMaster = endpointMasterList.get(endpointMasterList.size() - 1);
        assertThat(testEndpointMaster.getEndpointName()).isEqualTo(UPDATED_ENDPOINT_NAME);
        assertThat(testEndpointMaster.getEndpointGroup()).isEqualTo(UPDATED_ENDPOINT_GROUP);
        assertThat(testEndpointMaster.getEndpointDesc()).isEqualTo(UPDATED_ENDPOINT_DESC);
        assertThat(testEndpointMaster.getEndpointUrl()).isEqualTo(UPDATED_ENDPOINT_URL);
        assertThat(testEndpointMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testEndpointMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testEndpointMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testEndpointMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testEndpointMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testEndpointMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testEndpointMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testEndpointMaster.getEndpointMasterUuid()).isEqualTo(UPDATED_ENDPOINT_MASTER_UUID);
    }

    @Test
    @Transactional
    void putNonExistingEndpointMaster() throws Exception {
        int databaseSizeBeforeUpdate = endpointMasterRepository.findAll().size();
        endpointMaster.setEndpointId(count.incrementAndGet());

        // Create the EndpointMaster
        EndpointMasterDTO endpointMasterDTO = endpointMasterMapper.toDto(endpointMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEndpointMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, endpointMasterDTO.getEndpointId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(endpointMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EndpointMaster in the database
        List<EndpointMaster> endpointMasterList = endpointMasterRepository.findAll();
        assertThat(endpointMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEndpointMaster() throws Exception {
        int databaseSizeBeforeUpdate = endpointMasterRepository.findAll().size();
        endpointMaster.setEndpointId(count.incrementAndGet());

        // Create the EndpointMaster
        EndpointMasterDTO endpointMasterDTO = endpointMasterMapper.toDto(endpointMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEndpointMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(endpointMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EndpointMaster in the database
        List<EndpointMaster> endpointMasterList = endpointMasterRepository.findAll();
        assertThat(endpointMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEndpointMaster() throws Exception {
        int databaseSizeBeforeUpdate = endpointMasterRepository.findAll().size();
        endpointMaster.setEndpointId(count.incrementAndGet());

        // Create the EndpointMaster
        EndpointMasterDTO endpointMasterDTO = endpointMasterMapper.toDto(endpointMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEndpointMasterMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(endpointMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the EndpointMaster in the database
        List<EndpointMaster> endpointMasterList = endpointMasterRepository.findAll();
        assertThat(endpointMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEndpointMasterWithPatch() throws Exception {
        // Initialize the database
        endpointMasterRepository.saveAndFlush(endpointMaster);

        int databaseSizeBeforeUpdate = endpointMasterRepository.findAll().size();

        // Update the endpointMaster using partial update
        EndpointMaster partialUpdatedEndpointMaster = new EndpointMaster();
        partialUpdatedEndpointMaster.setEndpointId(endpointMaster.getEndpointId());

        partialUpdatedEndpointMaster
            .endpointName(UPDATED_ENDPOINT_NAME)
            .endpointDesc(UPDATED_ENDPOINT_DESC)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME);

        restEndpointMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEndpointMaster.getEndpointId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEndpointMaster))
            )
            .andExpect(status().isOk());

        // Validate the EndpointMaster in the database
        List<EndpointMaster> endpointMasterList = endpointMasterRepository.findAll();
        assertThat(endpointMasterList).hasSize(databaseSizeBeforeUpdate);
        EndpointMaster testEndpointMaster = endpointMasterList.get(endpointMasterList.size() - 1);
        assertThat(testEndpointMaster.getEndpointName()).isEqualTo(UPDATED_ENDPOINT_NAME);
        assertThat(testEndpointMaster.getEndpointGroup()).isEqualTo(DEFAULT_ENDPOINT_GROUP);
        assertThat(testEndpointMaster.getEndpointDesc()).isEqualTo(UPDATED_ENDPOINT_DESC);
        assertThat(testEndpointMaster.getEndpointUrl()).isEqualTo(DEFAULT_ENDPOINT_URL);
        assertThat(testEndpointMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testEndpointMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testEndpointMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testEndpointMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testEndpointMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testEndpointMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testEndpointMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testEndpointMaster.getEndpointMasterUuid()).isEqualTo(DEFAULT_ENDPOINT_MASTER_UUID);
    }

    @Test
    @Transactional
    void fullUpdateEndpointMasterWithPatch() throws Exception {
        // Initialize the database
        endpointMasterRepository.saveAndFlush(endpointMaster);

        int databaseSizeBeforeUpdate = endpointMasterRepository.findAll().size();

        // Update the endpointMaster using partial update
        EndpointMaster partialUpdatedEndpointMaster = new EndpointMaster();
        partialUpdatedEndpointMaster.setEndpointId(endpointMaster.getEndpointId());

        partialUpdatedEndpointMaster
            .endpointName(UPDATED_ENDPOINT_NAME)
            .endpointGroup(UPDATED_ENDPOINT_GROUP)
            .endpointDesc(UPDATED_ENDPOINT_DESC)
            .endpointUrl(UPDATED_ENDPOINT_URL)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .endpointMasterUuid(UPDATED_ENDPOINT_MASTER_UUID);

        restEndpointMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEndpointMaster.getEndpointId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEndpointMaster))
            )
            .andExpect(status().isOk());

        // Validate the EndpointMaster in the database
        List<EndpointMaster> endpointMasterList = endpointMasterRepository.findAll();
        assertThat(endpointMasterList).hasSize(databaseSizeBeforeUpdate);
        EndpointMaster testEndpointMaster = endpointMasterList.get(endpointMasterList.size() - 1);
        assertThat(testEndpointMaster.getEndpointName()).isEqualTo(UPDATED_ENDPOINT_NAME);
        assertThat(testEndpointMaster.getEndpointGroup()).isEqualTo(UPDATED_ENDPOINT_GROUP);
        assertThat(testEndpointMaster.getEndpointDesc()).isEqualTo(UPDATED_ENDPOINT_DESC);
        assertThat(testEndpointMaster.getEndpointUrl()).isEqualTo(UPDATED_ENDPOINT_URL);
        assertThat(testEndpointMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testEndpointMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testEndpointMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testEndpointMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testEndpointMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testEndpointMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testEndpointMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testEndpointMaster.getEndpointMasterUuid()).isEqualTo(UPDATED_ENDPOINT_MASTER_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingEndpointMaster() throws Exception {
        int databaseSizeBeforeUpdate = endpointMasterRepository.findAll().size();
        endpointMaster.setEndpointId(count.incrementAndGet());

        // Create the EndpointMaster
        EndpointMasterDTO endpointMasterDTO = endpointMasterMapper.toDto(endpointMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEndpointMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, endpointMasterDTO.getEndpointId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(endpointMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EndpointMaster in the database
        List<EndpointMaster> endpointMasterList = endpointMasterRepository.findAll();
        assertThat(endpointMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEndpointMaster() throws Exception {
        int databaseSizeBeforeUpdate = endpointMasterRepository.findAll().size();
        endpointMaster.setEndpointId(count.incrementAndGet());

        // Create the EndpointMaster
        EndpointMasterDTO endpointMasterDTO = endpointMasterMapper.toDto(endpointMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEndpointMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(endpointMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EndpointMaster in the database
        List<EndpointMaster> endpointMasterList = endpointMasterRepository.findAll();
        assertThat(endpointMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEndpointMaster() throws Exception {
        int databaseSizeBeforeUpdate = endpointMasterRepository.findAll().size();
        endpointMaster.setEndpointId(count.incrementAndGet());

        // Create the EndpointMaster
        EndpointMasterDTO endpointMasterDTO = endpointMasterMapper.toDto(endpointMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEndpointMasterMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(endpointMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the EndpointMaster in the database
        List<EndpointMaster> endpointMasterList = endpointMasterRepository.findAll();
        assertThat(endpointMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEndpointMaster() throws Exception {
        // Initialize the database
        endpointMasterRepository.saveAndFlush(endpointMaster);

        int databaseSizeBeforeDelete = endpointMasterRepository.findAll().size();

        // Delete the endpointMaster
        restEndpointMasterMockMvc
            .perform(delete(ENTITY_API_URL_ID, endpointMaster.getEndpointId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EndpointMaster> endpointMasterList = endpointMasterRepository.findAll();
        assertThat(endpointMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
