package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.RoleFunctionalityMap;
import com.sunknowledge.dme.rcm.repository.RoleFunctionalityMapRepository;
import com.sunknowledge.dme.rcm.service.dto.RoleFunctionalityMapDTO;
import com.sunknowledge.dme.rcm.service.mapper.RoleFunctionalityMapMapper;
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
 * Integration tests for the {@link RoleFunctionalityMapResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RoleFunctionalityMapResourceIT {

    private static final Long DEFAULT_FUNCTIONALITY_ID = 1L;
    private static final Long UPDATED_FUNCTIONALITY_ID = 2L;

    private static final Long DEFAULT_ROLE_ID = 1L;
    private static final Long UPDATED_ROLE_ID = 2L;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_ROLE_FUNCTIONALITY_MAP_UUID = UUID.randomUUID();
    private static final UUID UPDATED_ROLE_FUNCTIONALITY_MAP_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/role-functionality-maps";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{roleFunctionalityMapId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private RoleFunctionalityMapRepository roleFunctionalityMapRepository;

    @Autowired
    private RoleFunctionalityMapMapper roleFunctionalityMapMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRoleFunctionalityMapMockMvc;

    private RoleFunctionalityMap roleFunctionalityMap;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RoleFunctionalityMap createEntity(EntityManager em) {
        RoleFunctionalityMap roleFunctionalityMap = new RoleFunctionalityMap()
            .functionalityId(DEFAULT_FUNCTIONALITY_ID)
            .roleId(DEFAULT_ROLE_ID)
            .description(DEFAULT_DESCRIPTION)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .roleFunctionalityMapUuid(DEFAULT_ROLE_FUNCTIONALITY_MAP_UUID);
        return roleFunctionalityMap;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RoleFunctionalityMap createUpdatedEntity(EntityManager em) {
        RoleFunctionalityMap roleFunctionalityMap = new RoleFunctionalityMap()
            .functionalityId(UPDATED_FUNCTIONALITY_ID)
            .roleId(UPDATED_ROLE_ID)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .roleFunctionalityMapUuid(UPDATED_ROLE_FUNCTIONALITY_MAP_UUID);
        return roleFunctionalityMap;
    }

    @BeforeEach
    public void initTest() {
        roleFunctionalityMap = createEntity(em);
    }

    @Test
    @Transactional
    void createRoleFunctionalityMap() throws Exception {
        int databaseSizeBeforeCreate = roleFunctionalityMapRepository.findAll().size();
        // Create the RoleFunctionalityMap
        RoleFunctionalityMapDTO roleFunctionalityMapDTO = roleFunctionalityMapMapper.toDto(roleFunctionalityMap);
        restRoleFunctionalityMapMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(roleFunctionalityMapDTO))
            )
            .andExpect(status().isCreated());

        // Validate the RoleFunctionalityMap in the database
        List<RoleFunctionalityMap> roleFunctionalityMapList = roleFunctionalityMapRepository.findAll();
        assertThat(roleFunctionalityMapList).hasSize(databaseSizeBeforeCreate + 1);
        RoleFunctionalityMap testRoleFunctionalityMap = roleFunctionalityMapList.get(roleFunctionalityMapList.size() - 1);
        assertThat(testRoleFunctionalityMap.getFunctionalityId()).isEqualTo(DEFAULT_FUNCTIONALITY_ID);
        assertThat(testRoleFunctionalityMap.getRoleId()).isEqualTo(DEFAULT_ROLE_ID);
        assertThat(testRoleFunctionalityMap.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testRoleFunctionalityMap.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testRoleFunctionalityMap.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testRoleFunctionalityMap.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testRoleFunctionalityMap.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testRoleFunctionalityMap.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testRoleFunctionalityMap.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testRoleFunctionalityMap.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testRoleFunctionalityMap.getRoleFunctionalityMapUuid()).isEqualTo(DEFAULT_ROLE_FUNCTIONALITY_MAP_UUID);
    }

    @Test
    @Transactional
    void createRoleFunctionalityMapWithExistingId() throws Exception {
        // Create the RoleFunctionalityMap with an existing ID
        roleFunctionalityMap.setRoleFunctionalityMapId(1L);
        RoleFunctionalityMapDTO roleFunctionalityMapDTO = roleFunctionalityMapMapper.toDto(roleFunctionalityMap);

        int databaseSizeBeforeCreate = roleFunctionalityMapRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRoleFunctionalityMapMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(roleFunctionalityMapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RoleFunctionalityMap in the database
        List<RoleFunctionalityMap> roleFunctionalityMapList = roleFunctionalityMapRepository.findAll();
        assertThat(roleFunctionalityMapList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllRoleFunctionalityMaps() throws Exception {
        // Initialize the database
        roleFunctionalityMapRepository.saveAndFlush(roleFunctionalityMap);

        // Get all the roleFunctionalityMapList
        restRoleFunctionalityMapMockMvc
            .perform(get(ENTITY_API_URL + "?sort=roleFunctionalityMapId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].roleFunctionalityMapId").value(hasItem(roleFunctionalityMap.getRoleFunctionalityMapId().intValue())))
            .andExpect(jsonPath("$.[*].functionalityId").value(hasItem(DEFAULT_FUNCTIONALITY_ID.intValue())))
            .andExpect(jsonPath("$.[*].roleId").value(hasItem(DEFAULT_ROLE_ID.intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].roleFunctionalityMapUuid").value(hasItem(DEFAULT_ROLE_FUNCTIONALITY_MAP_UUID.toString())));
    }

    @Test
    @Transactional
    void getRoleFunctionalityMap() throws Exception {
        // Initialize the database
        roleFunctionalityMapRepository.saveAndFlush(roleFunctionalityMap);

        // Get the roleFunctionalityMap
        restRoleFunctionalityMapMockMvc
            .perform(get(ENTITY_API_URL_ID, roleFunctionalityMap.getRoleFunctionalityMapId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.roleFunctionalityMapId").value(roleFunctionalityMap.getRoleFunctionalityMapId().intValue()))
            .andExpect(jsonPath("$.functionalityId").value(DEFAULT_FUNCTIONALITY_ID.intValue()))
            .andExpect(jsonPath("$.roleId").value(DEFAULT_ROLE_ID.intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.roleFunctionalityMapUuid").value(DEFAULT_ROLE_FUNCTIONALITY_MAP_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingRoleFunctionalityMap() throws Exception {
        // Get the roleFunctionalityMap
        restRoleFunctionalityMapMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingRoleFunctionalityMap() throws Exception {
        // Initialize the database
        roleFunctionalityMapRepository.saveAndFlush(roleFunctionalityMap);

        int databaseSizeBeforeUpdate = roleFunctionalityMapRepository.findAll().size();

        // Update the roleFunctionalityMap
        RoleFunctionalityMap updatedRoleFunctionalityMap = roleFunctionalityMapRepository
            .findById(roleFunctionalityMap.getRoleFunctionalityMapId())
            .get();
        // Disconnect from session so that the updates on updatedRoleFunctionalityMap are not directly saved in db
        em.detach(updatedRoleFunctionalityMap);
        updatedRoleFunctionalityMap
            .functionalityId(UPDATED_FUNCTIONALITY_ID)
            .roleId(UPDATED_ROLE_ID)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .roleFunctionalityMapUuid(UPDATED_ROLE_FUNCTIONALITY_MAP_UUID);
        RoleFunctionalityMapDTO roleFunctionalityMapDTO = roleFunctionalityMapMapper.toDto(updatedRoleFunctionalityMap);

        restRoleFunctionalityMapMockMvc
            .perform(
                put(ENTITY_API_URL_ID, roleFunctionalityMapDTO.getRoleFunctionalityMapId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(roleFunctionalityMapDTO))
            )
            .andExpect(status().isOk());

        // Validate the RoleFunctionalityMap in the database
        List<RoleFunctionalityMap> roleFunctionalityMapList = roleFunctionalityMapRepository.findAll();
        assertThat(roleFunctionalityMapList).hasSize(databaseSizeBeforeUpdate);
        RoleFunctionalityMap testRoleFunctionalityMap = roleFunctionalityMapList.get(roleFunctionalityMapList.size() - 1);
        assertThat(testRoleFunctionalityMap.getFunctionalityId()).isEqualTo(UPDATED_FUNCTIONALITY_ID);
        assertThat(testRoleFunctionalityMap.getRoleId()).isEqualTo(UPDATED_ROLE_ID);
        assertThat(testRoleFunctionalityMap.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testRoleFunctionalityMap.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testRoleFunctionalityMap.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testRoleFunctionalityMap.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testRoleFunctionalityMap.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testRoleFunctionalityMap.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testRoleFunctionalityMap.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testRoleFunctionalityMap.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testRoleFunctionalityMap.getRoleFunctionalityMapUuid()).isEqualTo(UPDATED_ROLE_FUNCTIONALITY_MAP_UUID);
    }

    @Test
    @Transactional
    void putNonExistingRoleFunctionalityMap() throws Exception {
        int databaseSizeBeforeUpdate = roleFunctionalityMapRepository.findAll().size();
        roleFunctionalityMap.setRoleFunctionalityMapId(count.incrementAndGet());

        // Create the RoleFunctionalityMap
        RoleFunctionalityMapDTO roleFunctionalityMapDTO = roleFunctionalityMapMapper.toDto(roleFunctionalityMap);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRoleFunctionalityMapMockMvc
            .perform(
                put(ENTITY_API_URL_ID, roleFunctionalityMapDTO.getRoleFunctionalityMapId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(roleFunctionalityMapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RoleFunctionalityMap in the database
        List<RoleFunctionalityMap> roleFunctionalityMapList = roleFunctionalityMapRepository.findAll();
        assertThat(roleFunctionalityMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRoleFunctionalityMap() throws Exception {
        int databaseSizeBeforeUpdate = roleFunctionalityMapRepository.findAll().size();
        roleFunctionalityMap.setRoleFunctionalityMapId(count.incrementAndGet());

        // Create the RoleFunctionalityMap
        RoleFunctionalityMapDTO roleFunctionalityMapDTO = roleFunctionalityMapMapper.toDto(roleFunctionalityMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRoleFunctionalityMapMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(roleFunctionalityMapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RoleFunctionalityMap in the database
        List<RoleFunctionalityMap> roleFunctionalityMapList = roleFunctionalityMapRepository.findAll();
        assertThat(roleFunctionalityMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRoleFunctionalityMap() throws Exception {
        int databaseSizeBeforeUpdate = roleFunctionalityMapRepository.findAll().size();
        roleFunctionalityMap.setRoleFunctionalityMapId(count.incrementAndGet());

        // Create the RoleFunctionalityMap
        RoleFunctionalityMapDTO roleFunctionalityMapDTO = roleFunctionalityMapMapper.toDto(roleFunctionalityMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRoleFunctionalityMapMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(roleFunctionalityMapDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RoleFunctionalityMap in the database
        List<RoleFunctionalityMap> roleFunctionalityMapList = roleFunctionalityMapRepository.findAll();
        assertThat(roleFunctionalityMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRoleFunctionalityMapWithPatch() throws Exception {
        // Initialize the database
        roleFunctionalityMapRepository.saveAndFlush(roleFunctionalityMap);

        int databaseSizeBeforeUpdate = roleFunctionalityMapRepository.findAll().size();

        // Update the roleFunctionalityMap using partial update
        RoleFunctionalityMap partialUpdatedRoleFunctionalityMap = new RoleFunctionalityMap();
        partialUpdatedRoleFunctionalityMap.setRoleFunctionalityMapId(roleFunctionalityMap.getRoleFunctionalityMapId());

        partialUpdatedRoleFunctionalityMap
            .roleId(UPDATED_ROLE_ID)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .roleFunctionalityMapUuid(UPDATED_ROLE_FUNCTIONALITY_MAP_UUID);

        restRoleFunctionalityMapMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRoleFunctionalityMap.getRoleFunctionalityMapId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRoleFunctionalityMap))
            )
            .andExpect(status().isOk());

        // Validate the RoleFunctionalityMap in the database
        List<RoleFunctionalityMap> roleFunctionalityMapList = roleFunctionalityMapRepository.findAll();
        assertThat(roleFunctionalityMapList).hasSize(databaseSizeBeforeUpdate);
        RoleFunctionalityMap testRoleFunctionalityMap = roleFunctionalityMapList.get(roleFunctionalityMapList.size() - 1);
        assertThat(testRoleFunctionalityMap.getFunctionalityId()).isEqualTo(DEFAULT_FUNCTIONALITY_ID);
        assertThat(testRoleFunctionalityMap.getRoleId()).isEqualTo(UPDATED_ROLE_ID);
        assertThat(testRoleFunctionalityMap.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testRoleFunctionalityMap.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testRoleFunctionalityMap.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testRoleFunctionalityMap.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testRoleFunctionalityMap.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testRoleFunctionalityMap.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testRoleFunctionalityMap.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testRoleFunctionalityMap.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testRoleFunctionalityMap.getRoleFunctionalityMapUuid()).isEqualTo(UPDATED_ROLE_FUNCTIONALITY_MAP_UUID);
    }

    @Test
    @Transactional
    void fullUpdateRoleFunctionalityMapWithPatch() throws Exception {
        // Initialize the database
        roleFunctionalityMapRepository.saveAndFlush(roleFunctionalityMap);

        int databaseSizeBeforeUpdate = roleFunctionalityMapRepository.findAll().size();

        // Update the roleFunctionalityMap using partial update
        RoleFunctionalityMap partialUpdatedRoleFunctionalityMap = new RoleFunctionalityMap();
        partialUpdatedRoleFunctionalityMap.setRoleFunctionalityMapId(roleFunctionalityMap.getRoleFunctionalityMapId());

        partialUpdatedRoleFunctionalityMap
            .functionalityId(UPDATED_FUNCTIONALITY_ID)
            .roleId(UPDATED_ROLE_ID)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .roleFunctionalityMapUuid(UPDATED_ROLE_FUNCTIONALITY_MAP_UUID);

        restRoleFunctionalityMapMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRoleFunctionalityMap.getRoleFunctionalityMapId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRoleFunctionalityMap))
            )
            .andExpect(status().isOk());

        // Validate the RoleFunctionalityMap in the database
        List<RoleFunctionalityMap> roleFunctionalityMapList = roleFunctionalityMapRepository.findAll();
        assertThat(roleFunctionalityMapList).hasSize(databaseSizeBeforeUpdate);
        RoleFunctionalityMap testRoleFunctionalityMap = roleFunctionalityMapList.get(roleFunctionalityMapList.size() - 1);
        assertThat(testRoleFunctionalityMap.getFunctionalityId()).isEqualTo(UPDATED_FUNCTIONALITY_ID);
        assertThat(testRoleFunctionalityMap.getRoleId()).isEqualTo(UPDATED_ROLE_ID);
        assertThat(testRoleFunctionalityMap.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testRoleFunctionalityMap.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testRoleFunctionalityMap.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testRoleFunctionalityMap.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testRoleFunctionalityMap.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testRoleFunctionalityMap.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testRoleFunctionalityMap.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testRoleFunctionalityMap.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testRoleFunctionalityMap.getRoleFunctionalityMapUuid()).isEqualTo(UPDATED_ROLE_FUNCTIONALITY_MAP_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingRoleFunctionalityMap() throws Exception {
        int databaseSizeBeforeUpdate = roleFunctionalityMapRepository.findAll().size();
        roleFunctionalityMap.setRoleFunctionalityMapId(count.incrementAndGet());

        // Create the RoleFunctionalityMap
        RoleFunctionalityMapDTO roleFunctionalityMapDTO = roleFunctionalityMapMapper.toDto(roleFunctionalityMap);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRoleFunctionalityMapMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, roleFunctionalityMapDTO.getRoleFunctionalityMapId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(roleFunctionalityMapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RoleFunctionalityMap in the database
        List<RoleFunctionalityMap> roleFunctionalityMapList = roleFunctionalityMapRepository.findAll();
        assertThat(roleFunctionalityMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRoleFunctionalityMap() throws Exception {
        int databaseSizeBeforeUpdate = roleFunctionalityMapRepository.findAll().size();
        roleFunctionalityMap.setRoleFunctionalityMapId(count.incrementAndGet());

        // Create the RoleFunctionalityMap
        RoleFunctionalityMapDTO roleFunctionalityMapDTO = roleFunctionalityMapMapper.toDto(roleFunctionalityMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRoleFunctionalityMapMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(roleFunctionalityMapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RoleFunctionalityMap in the database
        List<RoleFunctionalityMap> roleFunctionalityMapList = roleFunctionalityMapRepository.findAll();
        assertThat(roleFunctionalityMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRoleFunctionalityMap() throws Exception {
        int databaseSizeBeforeUpdate = roleFunctionalityMapRepository.findAll().size();
        roleFunctionalityMap.setRoleFunctionalityMapId(count.incrementAndGet());

        // Create the RoleFunctionalityMap
        RoleFunctionalityMapDTO roleFunctionalityMapDTO = roleFunctionalityMapMapper.toDto(roleFunctionalityMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRoleFunctionalityMapMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(roleFunctionalityMapDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RoleFunctionalityMap in the database
        List<RoleFunctionalityMap> roleFunctionalityMapList = roleFunctionalityMapRepository.findAll();
        assertThat(roleFunctionalityMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRoleFunctionalityMap() throws Exception {
        // Initialize the database
        roleFunctionalityMapRepository.saveAndFlush(roleFunctionalityMap);

        int databaseSizeBeforeDelete = roleFunctionalityMapRepository.findAll().size();

        // Delete the roleFunctionalityMap
        restRoleFunctionalityMapMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, roleFunctionalityMap.getRoleFunctionalityMapId()).with(csrf()).accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RoleFunctionalityMap> roleFunctionalityMapList = roleFunctionalityMapRepository.findAll();
        assertThat(roleFunctionalityMapList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
