package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.BranchUserMap;
import com.sunknowledge.dme.rcm.repository.BranchUserMapRepository;
import com.sunknowledge.dme.rcm.service.dto.BranchUserMapDTO;
import com.sunknowledge.dme.rcm.service.mapper.BranchUserMapMapper;
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
 * Integration tests for the {@link BranchUserMapResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BranchUserMapResourceIT {

    private static final Long DEFAULT_BRANCH_ID = 1L;
    private static final Long UPDATED_BRANCH_ID = 2L;

    private static final Long DEFAULT_USER_ID = 1L;
    private static final Long UPDATED_USER_ID = 2L;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final UUID DEFAULT_BRANCH_USER_MAP_UUID = UUID.randomUUID();
    private static final UUID UPDATED_BRANCH_USER_MAP_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/branch-user-maps";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{mapId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BranchUserMapRepository branchUserMapRepository;

    @Autowired
    private BranchUserMapMapper branchUserMapMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBranchUserMapMockMvc;

    private BranchUserMap branchUserMap;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BranchUserMap createEntity(EntityManager em) {
        BranchUserMap branchUserMap = new BranchUserMap()
            .branchId(DEFAULT_BRANCH_ID)
            .userId(DEFAULT_USER_ID)
            .description(DEFAULT_DESCRIPTION)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .branchUserMapUuid(DEFAULT_BRANCH_USER_MAP_UUID);
        return branchUserMap;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BranchUserMap createUpdatedEntity(EntityManager em) {
        BranchUserMap branchUserMap = new BranchUserMap()
            .branchId(UPDATED_BRANCH_ID)
            .userId(UPDATED_USER_ID)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .branchUserMapUuid(UPDATED_BRANCH_USER_MAP_UUID);
        return branchUserMap;
    }

    @BeforeEach
    public void initTest() {
        branchUserMap = createEntity(em);
    }

    @Test
    @Transactional
    void createBranchUserMap() throws Exception {
        int databaseSizeBeforeCreate = branchUserMapRepository.findAll().size();
        // Create the BranchUserMap
        BranchUserMapDTO branchUserMapDTO = branchUserMapMapper.toDto(branchUserMap);
        restBranchUserMapMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchUserMapDTO))
            )
            .andExpect(status().isCreated());

        // Validate the BranchUserMap in the database
        List<BranchUserMap> branchUserMapList = branchUserMapRepository.findAll();
        assertThat(branchUserMapList).hasSize(databaseSizeBeforeCreate + 1);
        BranchUserMap testBranchUserMap = branchUserMapList.get(branchUserMapList.size() - 1);
        assertThat(testBranchUserMap.getBranchId()).isEqualTo(DEFAULT_BRANCH_ID);
        assertThat(testBranchUserMap.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testBranchUserMap.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testBranchUserMap.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testBranchUserMap.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testBranchUserMap.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testBranchUserMap.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testBranchUserMap.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testBranchUserMap.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testBranchUserMap.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testBranchUserMap.getBranchUserMapUuid()).isEqualTo(DEFAULT_BRANCH_USER_MAP_UUID);
    }

    @Test
    @Transactional
    void createBranchUserMapWithExistingId() throws Exception {
        // Create the BranchUserMap with an existing ID
        branchUserMap.setMapId(1L);
        BranchUserMapDTO branchUserMapDTO = branchUserMapMapper.toDto(branchUserMap);

        int databaseSizeBeforeCreate = branchUserMapRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBranchUserMapMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchUserMapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchUserMap in the database
        List<BranchUserMap> branchUserMapList = branchUserMapRepository.findAll();
        assertThat(branchUserMapList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBranchUserMaps() throws Exception {
        // Initialize the database
        branchUserMapRepository.saveAndFlush(branchUserMap);

        // Get all the branchUserMapList
        restBranchUserMapMockMvc
            .perform(get(ENTITY_API_URL + "?sort=mapId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].mapId").value(hasItem(branchUserMap.getMapId().intValue())))
            .andExpect(jsonPath("$.[*].branchId").value(hasItem(DEFAULT_BRANCH_ID.intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].branchUserMapUuid").value(hasItem(DEFAULT_BRANCH_USER_MAP_UUID.toString())));
    }

    @Test
    @Transactional
    void getBranchUserMap() throws Exception {
        // Initialize the database
        branchUserMapRepository.saveAndFlush(branchUserMap);

        // Get the branchUserMap
        restBranchUserMapMockMvc
            .perform(get(ENTITY_API_URL_ID, branchUserMap.getMapId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.mapId").value(branchUserMap.getMapId().intValue()))
            .andExpect(jsonPath("$.branchId").value(DEFAULT_BRANCH_ID.intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.branchUserMapUuid").value(DEFAULT_BRANCH_USER_MAP_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingBranchUserMap() throws Exception {
        // Get the branchUserMap
        restBranchUserMapMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingBranchUserMap() throws Exception {
        // Initialize the database
        branchUserMapRepository.saveAndFlush(branchUserMap);

        int databaseSizeBeforeUpdate = branchUserMapRepository.findAll().size();

        // Update the branchUserMap
        BranchUserMap updatedBranchUserMap = branchUserMapRepository.findById(branchUserMap.getMapId()).get();
        // Disconnect from session so that the updates on updatedBranchUserMap are not directly saved in db
        em.detach(updatedBranchUserMap);
        updatedBranchUserMap
            .branchId(UPDATED_BRANCH_ID)
            .userId(UPDATED_USER_ID)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .branchUserMapUuid(UPDATED_BRANCH_USER_MAP_UUID);
        BranchUserMapDTO branchUserMapDTO = branchUserMapMapper.toDto(updatedBranchUserMap);

        restBranchUserMapMockMvc
            .perform(
                put(ENTITY_API_URL_ID, branchUserMapDTO.getMapId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchUserMapDTO))
            )
            .andExpect(status().isOk());

        // Validate the BranchUserMap in the database
        List<BranchUserMap> branchUserMapList = branchUserMapRepository.findAll();
        assertThat(branchUserMapList).hasSize(databaseSizeBeforeUpdate);
        BranchUserMap testBranchUserMap = branchUserMapList.get(branchUserMapList.size() - 1);
        assertThat(testBranchUserMap.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
        assertThat(testBranchUserMap.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testBranchUserMap.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testBranchUserMap.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testBranchUserMap.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testBranchUserMap.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBranchUserMap.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testBranchUserMap.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testBranchUserMap.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testBranchUserMap.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testBranchUserMap.getBranchUserMapUuid()).isEqualTo(UPDATED_BRANCH_USER_MAP_UUID);
    }

    @Test
    @Transactional
    void putNonExistingBranchUserMap() throws Exception {
        int databaseSizeBeforeUpdate = branchUserMapRepository.findAll().size();
        branchUserMap.setMapId(count.incrementAndGet());

        // Create the BranchUserMap
        BranchUserMapDTO branchUserMapDTO = branchUserMapMapper.toDto(branchUserMap);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBranchUserMapMockMvc
            .perform(
                put(ENTITY_API_URL_ID, branchUserMapDTO.getMapId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchUserMapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchUserMap in the database
        List<BranchUserMap> branchUserMapList = branchUserMapRepository.findAll();
        assertThat(branchUserMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBranchUserMap() throws Exception {
        int databaseSizeBeforeUpdate = branchUserMapRepository.findAll().size();
        branchUserMap.setMapId(count.incrementAndGet());

        // Create the BranchUserMap
        BranchUserMapDTO branchUserMapDTO = branchUserMapMapper.toDto(branchUserMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchUserMapMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchUserMapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchUserMap in the database
        List<BranchUserMap> branchUserMapList = branchUserMapRepository.findAll();
        assertThat(branchUserMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBranchUserMap() throws Exception {
        int databaseSizeBeforeUpdate = branchUserMapRepository.findAll().size();
        branchUserMap.setMapId(count.incrementAndGet());

        // Create the BranchUserMap
        BranchUserMapDTO branchUserMapDTO = branchUserMapMapper.toDto(branchUserMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchUserMapMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchUserMapDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BranchUserMap in the database
        List<BranchUserMap> branchUserMapList = branchUserMapRepository.findAll();
        assertThat(branchUserMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBranchUserMapWithPatch() throws Exception {
        // Initialize the database
        branchUserMapRepository.saveAndFlush(branchUserMap);

        int databaseSizeBeforeUpdate = branchUserMapRepository.findAll().size();

        // Update the branchUserMap using partial update
        BranchUserMap partialUpdatedBranchUserMap = new BranchUserMap();
        partialUpdatedBranchUserMap.setMapId(branchUserMap.getMapId());

        partialUpdatedBranchUserMap
            .userId(UPDATED_USER_ID)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .branchUserMapUuid(UPDATED_BRANCH_USER_MAP_UUID);

        restBranchUserMapMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBranchUserMap.getMapId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBranchUserMap))
            )
            .andExpect(status().isOk());

        // Validate the BranchUserMap in the database
        List<BranchUserMap> branchUserMapList = branchUserMapRepository.findAll();
        assertThat(branchUserMapList).hasSize(databaseSizeBeforeUpdate);
        BranchUserMap testBranchUserMap = branchUserMapList.get(branchUserMapList.size() - 1);
        assertThat(testBranchUserMap.getBranchId()).isEqualTo(DEFAULT_BRANCH_ID);
        assertThat(testBranchUserMap.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testBranchUserMap.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testBranchUserMap.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testBranchUserMap.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testBranchUserMap.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBranchUserMap.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testBranchUserMap.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testBranchUserMap.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testBranchUserMap.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testBranchUserMap.getBranchUserMapUuid()).isEqualTo(UPDATED_BRANCH_USER_MAP_UUID);
    }

    @Test
    @Transactional
    void fullUpdateBranchUserMapWithPatch() throws Exception {
        // Initialize the database
        branchUserMapRepository.saveAndFlush(branchUserMap);

        int databaseSizeBeforeUpdate = branchUserMapRepository.findAll().size();

        // Update the branchUserMap using partial update
        BranchUserMap partialUpdatedBranchUserMap = new BranchUserMap();
        partialUpdatedBranchUserMap.setMapId(branchUserMap.getMapId());

        partialUpdatedBranchUserMap
            .branchId(UPDATED_BRANCH_ID)
            .userId(UPDATED_USER_ID)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .branchUserMapUuid(UPDATED_BRANCH_USER_MAP_UUID);

        restBranchUserMapMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBranchUserMap.getMapId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBranchUserMap))
            )
            .andExpect(status().isOk());

        // Validate the BranchUserMap in the database
        List<BranchUserMap> branchUserMapList = branchUserMapRepository.findAll();
        assertThat(branchUserMapList).hasSize(databaseSizeBeforeUpdate);
        BranchUserMap testBranchUserMap = branchUserMapList.get(branchUserMapList.size() - 1);
        assertThat(testBranchUserMap.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
        assertThat(testBranchUserMap.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testBranchUserMap.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testBranchUserMap.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testBranchUserMap.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testBranchUserMap.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBranchUserMap.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testBranchUserMap.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testBranchUserMap.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testBranchUserMap.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testBranchUserMap.getBranchUserMapUuid()).isEqualTo(UPDATED_BRANCH_USER_MAP_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingBranchUserMap() throws Exception {
        int databaseSizeBeforeUpdate = branchUserMapRepository.findAll().size();
        branchUserMap.setMapId(count.incrementAndGet());

        // Create the BranchUserMap
        BranchUserMapDTO branchUserMapDTO = branchUserMapMapper.toDto(branchUserMap);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBranchUserMapMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, branchUserMapDTO.getMapId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(branchUserMapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchUserMap in the database
        List<BranchUserMap> branchUserMapList = branchUserMapRepository.findAll();
        assertThat(branchUserMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBranchUserMap() throws Exception {
        int databaseSizeBeforeUpdate = branchUserMapRepository.findAll().size();
        branchUserMap.setMapId(count.incrementAndGet());

        // Create the BranchUserMap
        BranchUserMapDTO branchUserMapDTO = branchUserMapMapper.toDto(branchUserMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchUserMapMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(branchUserMapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchUserMap in the database
        List<BranchUserMap> branchUserMapList = branchUserMapRepository.findAll();
        assertThat(branchUserMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBranchUserMap() throws Exception {
        int databaseSizeBeforeUpdate = branchUserMapRepository.findAll().size();
        branchUserMap.setMapId(count.incrementAndGet());

        // Create the BranchUserMap
        BranchUserMapDTO branchUserMapDTO = branchUserMapMapper.toDto(branchUserMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchUserMapMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(branchUserMapDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BranchUserMap in the database
        List<BranchUserMap> branchUserMapList = branchUserMapRepository.findAll();
        assertThat(branchUserMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBranchUserMap() throws Exception {
        // Initialize the database
        branchUserMapRepository.saveAndFlush(branchUserMap);

        int databaseSizeBeforeDelete = branchUserMapRepository.findAll().size();

        // Delete the branchUserMap
        restBranchUserMapMockMvc
            .perform(delete(ENTITY_API_URL_ID, branchUserMap.getMapId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BranchUserMap> branchUserMapList = branchUserMapRepository.findAll();
        assertThat(branchUserMapList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
