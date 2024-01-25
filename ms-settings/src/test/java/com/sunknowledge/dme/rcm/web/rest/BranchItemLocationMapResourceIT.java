package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.BranchItemLocationMap;
import com.sunknowledge.dme.rcm.repository.BranchItemLocationMapRepository;
import com.sunknowledge.dme.rcm.service.dto.BranchItemLocationMapDTO;
import com.sunknowledge.dme.rcm.service.mapper.BranchItemLocationMapMapper;
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
 * Integration tests for the {@link BranchItemLocationMapResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BranchItemLocationMapResourceIT {

    private static final Long DEFAULT_BRANCH_ID = 1L;
    private static final Long UPDATED_BRANCH_ID = 2L;

    private static final Long DEFAULT_ITEM_LOCATION_ID = 1L;
    private static final Long UPDATED_ITEM_LOCATION_ID = 2L;

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

    private static final UUID DEFAULT_BRANCH_ITEM_LOCATION_MAP_UUID = UUID.randomUUID();
    private static final UUID UPDATED_BRANCH_ITEM_LOCATION_MAP_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/branch-item-location-maps";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{branchItemLocationMapId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BranchItemLocationMapRepository branchItemLocationMapRepository;

    @Autowired
    private BranchItemLocationMapMapper branchItemLocationMapMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBranchItemLocationMapMockMvc;

    private BranchItemLocationMap branchItemLocationMap;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BranchItemLocationMap createEntity(EntityManager em) {
        BranchItemLocationMap branchItemLocationMap = new BranchItemLocationMap()
            .branchId(DEFAULT_BRANCH_ID)
            .itemLocationId(DEFAULT_ITEM_LOCATION_ID)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .branchItemLocationMapUuid(DEFAULT_BRANCH_ITEM_LOCATION_MAP_UUID);
        return branchItemLocationMap;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BranchItemLocationMap createUpdatedEntity(EntityManager em) {
        BranchItemLocationMap branchItemLocationMap = new BranchItemLocationMap()
            .branchId(UPDATED_BRANCH_ID)
            .itemLocationId(UPDATED_ITEM_LOCATION_ID)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .branchItemLocationMapUuid(UPDATED_BRANCH_ITEM_LOCATION_MAP_UUID);
        return branchItemLocationMap;
    }

    @BeforeEach
    public void initTest() {
        branchItemLocationMap = createEntity(em);
    }

    @Test
    @Transactional
    void createBranchItemLocationMap() throws Exception {
        int databaseSizeBeforeCreate = branchItemLocationMapRepository.findAll().size();
        // Create the BranchItemLocationMap
        BranchItemLocationMapDTO branchItemLocationMapDTO = branchItemLocationMapMapper.toDto(branchItemLocationMap);
        restBranchItemLocationMapMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchItemLocationMapDTO))
            )
            .andExpect(status().isCreated());

        // Validate the BranchItemLocationMap in the database
        List<BranchItemLocationMap> branchItemLocationMapList = branchItemLocationMapRepository.findAll();
        assertThat(branchItemLocationMapList).hasSize(databaseSizeBeforeCreate + 1);
        BranchItemLocationMap testBranchItemLocationMap = branchItemLocationMapList.get(branchItemLocationMapList.size() - 1);
        assertThat(testBranchItemLocationMap.getBranchId()).isEqualTo(DEFAULT_BRANCH_ID);
        assertThat(testBranchItemLocationMap.getItemLocationId()).isEqualTo(DEFAULT_ITEM_LOCATION_ID);
        assertThat(testBranchItemLocationMap.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testBranchItemLocationMap.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testBranchItemLocationMap.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testBranchItemLocationMap.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testBranchItemLocationMap.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testBranchItemLocationMap.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testBranchItemLocationMap.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testBranchItemLocationMap.getBranchItemLocationMapUuid()).isEqualTo(DEFAULT_BRANCH_ITEM_LOCATION_MAP_UUID);
    }

    @Test
    @Transactional
    void createBranchItemLocationMapWithExistingId() throws Exception {
        // Create the BranchItemLocationMap with an existing ID
        branchItemLocationMap.setBranchItemLocationMapId(1L);
        BranchItemLocationMapDTO branchItemLocationMapDTO = branchItemLocationMapMapper.toDto(branchItemLocationMap);

        int databaseSizeBeforeCreate = branchItemLocationMapRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBranchItemLocationMapMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchItemLocationMapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchItemLocationMap in the database
        List<BranchItemLocationMap> branchItemLocationMapList = branchItemLocationMapRepository.findAll();
        assertThat(branchItemLocationMapList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBranchItemLocationMaps() throws Exception {
        // Initialize the database
        branchItemLocationMapRepository.saveAndFlush(branchItemLocationMap);

        // Get all the branchItemLocationMapList
        restBranchItemLocationMapMockMvc
            .perform(get(ENTITY_API_URL + "?sort=branchItemLocationMapId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].branchItemLocationMapId").value(hasItem(branchItemLocationMap.getBranchItemLocationMapId().intValue()))
            )
            .andExpect(jsonPath("$.[*].branchId").value(hasItem(DEFAULT_BRANCH_ID.intValue())))
            .andExpect(jsonPath("$.[*].itemLocationId").value(hasItem(DEFAULT_ITEM_LOCATION_ID.intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].branchItemLocationMapUuid").value(hasItem(DEFAULT_BRANCH_ITEM_LOCATION_MAP_UUID.toString())));
    }

    @Test
    @Transactional
    void getBranchItemLocationMap() throws Exception {
        // Initialize the database
        branchItemLocationMapRepository.saveAndFlush(branchItemLocationMap);

        // Get the branchItemLocationMap
        restBranchItemLocationMapMockMvc
            .perform(get(ENTITY_API_URL_ID, branchItemLocationMap.getBranchItemLocationMapId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.branchItemLocationMapId").value(branchItemLocationMap.getBranchItemLocationMapId().intValue()))
            .andExpect(jsonPath("$.branchId").value(DEFAULT_BRANCH_ID.intValue()))
            .andExpect(jsonPath("$.itemLocationId").value(DEFAULT_ITEM_LOCATION_ID.intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.branchItemLocationMapUuid").value(DEFAULT_BRANCH_ITEM_LOCATION_MAP_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingBranchItemLocationMap() throws Exception {
        // Get the branchItemLocationMap
        restBranchItemLocationMapMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingBranchItemLocationMap() throws Exception {
        // Initialize the database
        branchItemLocationMapRepository.saveAndFlush(branchItemLocationMap);

        int databaseSizeBeforeUpdate = branchItemLocationMapRepository.findAll().size();

        // Update the branchItemLocationMap
        BranchItemLocationMap updatedBranchItemLocationMap = branchItemLocationMapRepository
            .findById(branchItemLocationMap.getBranchItemLocationMapId())
            .get();
        // Disconnect from session so that the updates on updatedBranchItemLocationMap are not directly saved in db
        em.detach(updatedBranchItemLocationMap);
        updatedBranchItemLocationMap
            .branchId(UPDATED_BRANCH_ID)
            .itemLocationId(UPDATED_ITEM_LOCATION_ID)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .branchItemLocationMapUuid(UPDATED_BRANCH_ITEM_LOCATION_MAP_UUID);
        BranchItemLocationMapDTO branchItemLocationMapDTO = branchItemLocationMapMapper.toDto(updatedBranchItemLocationMap);

        restBranchItemLocationMapMockMvc
            .perform(
                put(ENTITY_API_URL_ID, branchItemLocationMapDTO.getBranchItemLocationMapId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchItemLocationMapDTO))
            )
            .andExpect(status().isOk());

        // Validate the BranchItemLocationMap in the database
        List<BranchItemLocationMap> branchItemLocationMapList = branchItemLocationMapRepository.findAll();
        assertThat(branchItemLocationMapList).hasSize(databaseSizeBeforeUpdate);
        BranchItemLocationMap testBranchItemLocationMap = branchItemLocationMapList.get(branchItemLocationMapList.size() - 1);
        assertThat(testBranchItemLocationMap.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
        assertThat(testBranchItemLocationMap.getItemLocationId()).isEqualTo(UPDATED_ITEM_LOCATION_ID);
        assertThat(testBranchItemLocationMap.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testBranchItemLocationMap.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testBranchItemLocationMap.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testBranchItemLocationMap.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBranchItemLocationMap.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testBranchItemLocationMap.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testBranchItemLocationMap.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testBranchItemLocationMap.getBranchItemLocationMapUuid()).isEqualTo(UPDATED_BRANCH_ITEM_LOCATION_MAP_UUID);
    }

    @Test
    @Transactional
    void putNonExistingBranchItemLocationMap() throws Exception {
        int databaseSizeBeforeUpdate = branchItemLocationMapRepository.findAll().size();
        branchItemLocationMap.setBranchItemLocationMapId(count.incrementAndGet());

        // Create the BranchItemLocationMap
        BranchItemLocationMapDTO branchItemLocationMapDTO = branchItemLocationMapMapper.toDto(branchItemLocationMap);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBranchItemLocationMapMockMvc
            .perform(
                put(ENTITY_API_URL_ID, branchItemLocationMapDTO.getBranchItemLocationMapId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchItemLocationMapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchItemLocationMap in the database
        List<BranchItemLocationMap> branchItemLocationMapList = branchItemLocationMapRepository.findAll();
        assertThat(branchItemLocationMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBranchItemLocationMap() throws Exception {
        int databaseSizeBeforeUpdate = branchItemLocationMapRepository.findAll().size();
        branchItemLocationMap.setBranchItemLocationMapId(count.incrementAndGet());

        // Create the BranchItemLocationMap
        BranchItemLocationMapDTO branchItemLocationMapDTO = branchItemLocationMapMapper.toDto(branchItemLocationMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchItemLocationMapMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchItemLocationMapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchItemLocationMap in the database
        List<BranchItemLocationMap> branchItemLocationMapList = branchItemLocationMapRepository.findAll();
        assertThat(branchItemLocationMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBranchItemLocationMap() throws Exception {
        int databaseSizeBeforeUpdate = branchItemLocationMapRepository.findAll().size();
        branchItemLocationMap.setBranchItemLocationMapId(count.incrementAndGet());

        // Create the BranchItemLocationMap
        BranchItemLocationMapDTO branchItemLocationMapDTO = branchItemLocationMapMapper.toDto(branchItemLocationMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchItemLocationMapMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchItemLocationMapDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BranchItemLocationMap in the database
        List<BranchItemLocationMap> branchItemLocationMapList = branchItemLocationMapRepository.findAll();
        assertThat(branchItemLocationMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBranchItemLocationMapWithPatch() throws Exception {
        // Initialize the database
        branchItemLocationMapRepository.saveAndFlush(branchItemLocationMap);

        int databaseSizeBeforeUpdate = branchItemLocationMapRepository.findAll().size();

        // Update the branchItemLocationMap using partial update
        BranchItemLocationMap partialUpdatedBranchItemLocationMap = new BranchItemLocationMap();
        partialUpdatedBranchItemLocationMap.setBranchItemLocationMapId(branchItemLocationMap.getBranchItemLocationMapId());

        partialUpdatedBranchItemLocationMap
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME);

        restBranchItemLocationMapMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBranchItemLocationMap.getBranchItemLocationMapId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBranchItemLocationMap))
            )
            .andExpect(status().isOk());

        // Validate the BranchItemLocationMap in the database
        List<BranchItemLocationMap> branchItemLocationMapList = branchItemLocationMapRepository.findAll();
        assertThat(branchItemLocationMapList).hasSize(databaseSizeBeforeUpdate);
        BranchItemLocationMap testBranchItemLocationMap = branchItemLocationMapList.get(branchItemLocationMapList.size() - 1);
        assertThat(testBranchItemLocationMap.getBranchId()).isEqualTo(DEFAULT_BRANCH_ID);
        assertThat(testBranchItemLocationMap.getItemLocationId()).isEqualTo(DEFAULT_ITEM_LOCATION_ID);
        assertThat(testBranchItemLocationMap.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testBranchItemLocationMap.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testBranchItemLocationMap.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testBranchItemLocationMap.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBranchItemLocationMap.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testBranchItemLocationMap.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testBranchItemLocationMap.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testBranchItemLocationMap.getBranchItemLocationMapUuid()).isEqualTo(DEFAULT_BRANCH_ITEM_LOCATION_MAP_UUID);
    }

    @Test
    @Transactional
    void fullUpdateBranchItemLocationMapWithPatch() throws Exception {
        // Initialize the database
        branchItemLocationMapRepository.saveAndFlush(branchItemLocationMap);

        int databaseSizeBeforeUpdate = branchItemLocationMapRepository.findAll().size();

        // Update the branchItemLocationMap using partial update
        BranchItemLocationMap partialUpdatedBranchItemLocationMap = new BranchItemLocationMap();
        partialUpdatedBranchItemLocationMap.setBranchItemLocationMapId(branchItemLocationMap.getBranchItemLocationMapId());

        partialUpdatedBranchItemLocationMap
            .branchId(UPDATED_BRANCH_ID)
            .itemLocationId(UPDATED_ITEM_LOCATION_ID)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .branchItemLocationMapUuid(UPDATED_BRANCH_ITEM_LOCATION_MAP_UUID);

        restBranchItemLocationMapMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBranchItemLocationMap.getBranchItemLocationMapId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBranchItemLocationMap))
            )
            .andExpect(status().isOk());

        // Validate the BranchItemLocationMap in the database
        List<BranchItemLocationMap> branchItemLocationMapList = branchItemLocationMapRepository.findAll();
        assertThat(branchItemLocationMapList).hasSize(databaseSizeBeforeUpdate);
        BranchItemLocationMap testBranchItemLocationMap = branchItemLocationMapList.get(branchItemLocationMapList.size() - 1);
        assertThat(testBranchItemLocationMap.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
        assertThat(testBranchItemLocationMap.getItemLocationId()).isEqualTo(UPDATED_ITEM_LOCATION_ID);
        assertThat(testBranchItemLocationMap.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testBranchItemLocationMap.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testBranchItemLocationMap.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testBranchItemLocationMap.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBranchItemLocationMap.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testBranchItemLocationMap.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testBranchItemLocationMap.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testBranchItemLocationMap.getBranchItemLocationMapUuid()).isEqualTo(UPDATED_BRANCH_ITEM_LOCATION_MAP_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingBranchItemLocationMap() throws Exception {
        int databaseSizeBeforeUpdate = branchItemLocationMapRepository.findAll().size();
        branchItemLocationMap.setBranchItemLocationMapId(count.incrementAndGet());

        // Create the BranchItemLocationMap
        BranchItemLocationMapDTO branchItemLocationMapDTO = branchItemLocationMapMapper.toDto(branchItemLocationMap);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBranchItemLocationMapMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, branchItemLocationMapDTO.getBranchItemLocationMapId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(branchItemLocationMapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchItemLocationMap in the database
        List<BranchItemLocationMap> branchItemLocationMapList = branchItemLocationMapRepository.findAll();
        assertThat(branchItemLocationMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBranchItemLocationMap() throws Exception {
        int databaseSizeBeforeUpdate = branchItemLocationMapRepository.findAll().size();
        branchItemLocationMap.setBranchItemLocationMapId(count.incrementAndGet());

        // Create the BranchItemLocationMap
        BranchItemLocationMapDTO branchItemLocationMapDTO = branchItemLocationMapMapper.toDto(branchItemLocationMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchItemLocationMapMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(branchItemLocationMapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchItemLocationMap in the database
        List<BranchItemLocationMap> branchItemLocationMapList = branchItemLocationMapRepository.findAll();
        assertThat(branchItemLocationMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBranchItemLocationMap() throws Exception {
        int databaseSizeBeforeUpdate = branchItemLocationMapRepository.findAll().size();
        branchItemLocationMap.setBranchItemLocationMapId(count.incrementAndGet());

        // Create the BranchItemLocationMap
        BranchItemLocationMapDTO branchItemLocationMapDTO = branchItemLocationMapMapper.toDto(branchItemLocationMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchItemLocationMapMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(branchItemLocationMapDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BranchItemLocationMap in the database
        List<BranchItemLocationMap> branchItemLocationMapList = branchItemLocationMapRepository.findAll();
        assertThat(branchItemLocationMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBranchItemLocationMap() throws Exception {
        // Initialize the database
        branchItemLocationMapRepository.saveAndFlush(branchItemLocationMap);

        int databaseSizeBeforeDelete = branchItemLocationMapRepository.findAll().size();

        // Delete the branchItemLocationMap
        restBranchItemLocationMapMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, branchItemLocationMap.getBranchItemLocationMapId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BranchItemLocationMap> branchItemLocationMapList = branchItemLocationMapRepository.findAll();
        assertThat(branchItemLocationMapList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
