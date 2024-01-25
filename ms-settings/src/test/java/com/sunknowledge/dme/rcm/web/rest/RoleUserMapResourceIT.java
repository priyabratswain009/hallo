package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.RoleUserMap;
import com.sunknowledge.dme.rcm.repository.RoleUserMapRepository;
import com.sunknowledge.dme.rcm.service.dto.RoleUserMapDTO;
import com.sunknowledge.dme.rcm.service.mapper.RoleUserMapMapper;
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
 * Integration tests for the {@link RoleUserMapResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RoleUserMapResourceIT {

    private static final Long DEFAULT_USER_ID = 1L;
    private static final Long UPDATED_USER_ID = 2L;

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

    private static final UUID DEFAULT_ROLE_USER_MAP_UUID = UUID.randomUUID();
    private static final UUID UPDATED_ROLE_USER_MAP_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/role-user-maps";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{roleUserMapId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private RoleUserMapRepository roleUserMapRepository;

    @Autowired
    private RoleUserMapMapper roleUserMapMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRoleUserMapMockMvc;

    private RoleUserMap roleUserMap;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RoleUserMap createEntity(EntityManager em) {
        RoleUserMap roleUserMap = new RoleUserMap()
            .userId(DEFAULT_USER_ID)
            .roleId(DEFAULT_ROLE_ID)
            .description(DEFAULT_DESCRIPTION)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .roleUserMapUuid(DEFAULT_ROLE_USER_MAP_UUID);
        return roleUserMap;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RoleUserMap createUpdatedEntity(EntityManager em) {
        RoleUserMap roleUserMap = new RoleUserMap()
            .userId(UPDATED_USER_ID)
            .roleId(UPDATED_ROLE_ID)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .roleUserMapUuid(UPDATED_ROLE_USER_MAP_UUID);
        return roleUserMap;
    }

    @BeforeEach
    public void initTest() {
        roleUserMap = createEntity(em);
    }

    @Test
    @Transactional
    void createRoleUserMap() throws Exception {
        int databaseSizeBeforeCreate = roleUserMapRepository.findAll().size();
        // Create the RoleUserMap
        RoleUserMapDTO roleUserMapDTO = roleUserMapMapper.toDto(roleUserMap);
        restRoleUserMapMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(roleUserMapDTO))
            )
            .andExpect(status().isCreated());

        // Validate the RoleUserMap in the database
        List<RoleUserMap> roleUserMapList = roleUserMapRepository.findAll();
        assertThat(roleUserMapList).hasSize(databaseSizeBeforeCreate + 1);
        RoleUserMap testRoleUserMap = roleUserMapList.get(roleUserMapList.size() - 1);
        assertThat(testRoleUserMap.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testRoleUserMap.getRoleId()).isEqualTo(DEFAULT_ROLE_ID);
        assertThat(testRoleUserMap.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testRoleUserMap.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testRoleUserMap.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testRoleUserMap.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testRoleUserMap.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testRoleUserMap.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testRoleUserMap.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testRoleUserMap.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testRoleUserMap.getRoleUserMapUuid()).isEqualTo(DEFAULT_ROLE_USER_MAP_UUID);
    }

    @Test
    @Transactional
    void createRoleUserMapWithExistingId() throws Exception {
        // Create the RoleUserMap with an existing ID
        roleUserMap.setRoleUserMapId(1L);
        RoleUserMapDTO roleUserMapDTO = roleUserMapMapper.toDto(roleUserMap);

        int databaseSizeBeforeCreate = roleUserMapRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRoleUserMapMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(roleUserMapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RoleUserMap in the database
        List<RoleUserMap> roleUserMapList = roleUserMapRepository.findAll();
        assertThat(roleUserMapList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllRoleUserMaps() throws Exception {
        // Initialize the database
        roleUserMapRepository.saveAndFlush(roleUserMap);

        // Get all the roleUserMapList
        restRoleUserMapMockMvc
            .perform(get(ENTITY_API_URL + "?sort=roleUserMapId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].roleUserMapId").value(hasItem(roleUserMap.getRoleUserMapId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].roleId").value(hasItem(DEFAULT_ROLE_ID.intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].roleUserMapUuid").value(hasItem(DEFAULT_ROLE_USER_MAP_UUID.toString())));
    }

    @Test
    @Transactional
    void getRoleUserMap() throws Exception {
        // Initialize the database
        roleUserMapRepository.saveAndFlush(roleUserMap);

        // Get the roleUserMap
        restRoleUserMapMockMvc
            .perform(get(ENTITY_API_URL_ID, roleUserMap.getRoleUserMapId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.roleUserMapId").value(roleUserMap.getRoleUserMapId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.intValue()))
            .andExpect(jsonPath("$.roleId").value(DEFAULT_ROLE_ID.intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.roleUserMapUuid").value(DEFAULT_ROLE_USER_MAP_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingRoleUserMap() throws Exception {
        // Get the roleUserMap
        restRoleUserMapMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingRoleUserMap() throws Exception {
        // Initialize the database
        roleUserMapRepository.saveAndFlush(roleUserMap);

        int databaseSizeBeforeUpdate = roleUserMapRepository.findAll().size();

        // Update the roleUserMap
        RoleUserMap updatedRoleUserMap = roleUserMapRepository.findById(roleUserMap.getRoleUserMapId()).get();
        // Disconnect from session so that the updates on updatedRoleUserMap are not directly saved in db
        em.detach(updatedRoleUserMap);
        updatedRoleUserMap
            .userId(UPDATED_USER_ID)
            .roleId(UPDATED_ROLE_ID)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .roleUserMapUuid(UPDATED_ROLE_USER_MAP_UUID);
        RoleUserMapDTO roleUserMapDTO = roleUserMapMapper.toDto(updatedRoleUserMap);

        restRoleUserMapMockMvc
            .perform(
                put(ENTITY_API_URL_ID, roleUserMapDTO.getRoleUserMapId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(roleUserMapDTO))
            )
            .andExpect(status().isOk());

        // Validate the RoleUserMap in the database
        List<RoleUserMap> roleUserMapList = roleUserMapRepository.findAll();
        assertThat(roleUserMapList).hasSize(databaseSizeBeforeUpdate);
        RoleUserMap testRoleUserMap = roleUserMapList.get(roleUserMapList.size() - 1);
        assertThat(testRoleUserMap.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testRoleUserMap.getRoleId()).isEqualTo(UPDATED_ROLE_ID);
        assertThat(testRoleUserMap.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testRoleUserMap.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testRoleUserMap.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testRoleUserMap.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testRoleUserMap.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testRoleUserMap.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testRoleUserMap.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testRoleUserMap.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testRoleUserMap.getRoleUserMapUuid()).isEqualTo(UPDATED_ROLE_USER_MAP_UUID);
    }

    @Test
    @Transactional
    void putNonExistingRoleUserMap() throws Exception {
        int databaseSizeBeforeUpdate = roleUserMapRepository.findAll().size();
        roleUserMap.setRoleUserMapId(count.incrementAndGet());

        // Create the RoleUserMap
        RoleUserMapDTO roleUserMapDTO = roleUserMapMapper.toDto(roleUserMap);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRoleUserMapMockMvc
            .perform(
                put(ENTITY_API_URL_ID, roleUserMapDTO.getRoleUserMapId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(roleUserMapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RoleUserMap in the database
        List<RoleUserMap> roleUserMapList = roleUserMapRepository.findAll();
        assertThat(roleUserMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRoleUserMap() throws Exception {
        int databaseSizeBeforeUpdate = roleUserMapRepository.findAll().size();
        roleUserMap.setRoleUserMapId(count.incrementAndGet());

        // Create the RoleUserMap
        RoleUserMapDTO roleUserMapDTO = roleUserMapMapper.toDto(roleUserMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRoleUserMapMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(roleUserMapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RoleUserMap in the database
        List<RoleUserMap> roleUserMapList = roleUserMapRepository.findAll();
        assertThat(roleUserMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRoleUserMap() throws Exception {
        int databaseSizeBeforeUpdate = roleUserMapRepository.findAll().size();
        roleUserMap.setRoleUserMapId(count.incrementAndGet());

        // Create the RoleUserMap
        RoleUserMapDTO roleUserMapDTO = roleUserMapMapper.toDto(roleUserMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRoleUserMapMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(roleUserMapDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RoleUserMap in the database
        List<RoleUserMap> roleUserMapList = roleUserMapRepository.findAll();
        assertThat(roleUserMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRoleUserMapWithPatch() throws Exception {
        // Initialize the database
        roleUserMapRepository.saveAndFlush(roleUserMap);

        int databaseSizeBeforeUpdate = roleUserMapRepository.findAll().size();

        // Update the roleUserMap using partial update
        RoleUserMap partialUpdatedRoleUserMap = new RoleUserMap();
        partialUpdatedRoleUserMap.setRoleUserMapId(roleUserMap.getRoleUserMapId());

        partialUpdatedRoleUserMap
            .roleId(UPDATED_ROLE_ID)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .roleUserMapUuid(UPDATED_ROLE_USER_MAP_UUID);

        restRoleUserMapMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRoleUserMap.getRoleUserMapId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRoleUserMap))
            )
            .andExpect(status().isOk());

        // Validate the RoleUserMap in the database
        List<RoleUserMap> roleUserMapList = roleUserMapRepository.findAll();
        assertThat(roleUserMapList).hasSize(databaseSizeBeforeUpdate);
        RoleUserMap testRoleUserMap = roleUserMapList.get(roleUserMapList.size() - 1);
        assertThat(testRoleUserMap.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testRoleUserMap.getRoleId()).isEqualTo(UPDATED_ROLE_ID);
        assertThat(testRoleUserMap.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testRoleUserMap.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testRoleUserMap.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testRoleUserMap.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testRoleUserMap.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testRoleUserMap.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testRoleUserMap.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testRoleUserMap.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testRoleUserMap.getRoleUserMapUuid()).isEqualTo(UPDATED_ROLE_USER_MAP_UUID);
    }

    @Test
    @Transactional
    void fullUpdateRoleUserMapWithPatch() throws Exception {
        // Initialize the database
        roleUserMapRepository.saveAndFlush(roleUserMap);

        int databaseSizeBeforeUpdate = roleUserMapRepository.findAll().size();

        // Update the roleUserMap using partial update
        RoleUserMap partialUpdatedRoleUserMap = new RoleUserMap();
        partialUpdatedRoleUserMap.setRoleUserMapId(roleUserMap.getRoleUserMapId());

        partialUpdatedRoleUserMap
            .userId(UPDATED_USER_ID)
            .roleId(UPDATED_ROLE_ID)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .roleUserMapUuid(UPDATED_ROLE_USER_MAP_UUID);

        restRoleUserMapMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRoleUserMap.getRoleUserMapId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRoleUserMap))
            )
            .andExpect(status().isOk());

        // Validate the RoleUserMap in the database
        List<RoleUserMap> roleUserMapList = roleUserMapRepository.findAll();
        assertThat(roleUserMapList).hasSize(databaseSizeBeforeUpdate);
        RoleUserMap testRoleUserMap = roleUserMapList.get(roleUserMapList.size() - 1);
        assertThat(testRoleUserMap.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testRoleUserMap.getRoleId()).isEqualTo(UPDATED_ROLE_ID);
        assertThat(testRoleUserMap.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testRoleUserMap.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testRoleUserMap.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testRoleUserMap.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testRoleUserMap.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testRoleUserMap.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testRoleUserMap.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testRoleUserMap.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testRoleUserMap.getRoleUserMapUuid()).isEqualTo(UPDATED_ROLE_USER_MAP_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingRoleUserMap() throws Exception {
        int databaseSizeBeforeUpdate = roleUserMapRepository.findAll().size();
        roleUserMap.setRoleUserMapId(count.incrementAndGet());

        // Create the RoleUserMap
        RoleUserMapDTO roleUserMapDTO = roleUserMapMapper.toDto(roleUserMap);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRoleUserMapMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, roleUserMapDTO.getRoleUserMapId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(roleUserMapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RoleUserMap in the database
        List<RoleUserMap> roleUserMapList = roleUserMapRepository.findAll();
        assertThat(roleUserMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRoleUserMap() throws Exception {
        int databaseSizeBeforeUpdate = roleUserMapRepository.findAll().size();
        roleUserMap.setRoleUserMapId(count.incrementAndGet());

        // Create the RoleUserMap
        RoleUserMapDTO roleUserMapDTO = roleUserMapMapper.toDto(roleUserMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRoleUserMapMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(roleUserMapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RoleUserMap in the database
        List<RoleUserMap> roleUserMapList = roleUserMapRepository.findAll();
        assertThat(roleUserMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRoleUserMap() throws Exception {
        int databaseSizeBeforeUpdate = roleUserMapRepository.findAll().size();
        roleUserMap.setRoleUserMapId(count.incrementAndGet());

        // Create the RoleUserMap
        RoleUserMapDTO roleUserMapDTO = roleUserMapMapper.toDto(roleUserMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRoleUserMapMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(roleUserMapDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RoleUserMap in the database
        List<RoleUserMap> roleUserMapList = roleUserMapRepository.findAll();
        assertThat(roleUserMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRoleUserMap() throws Exception {
        // Initialize the database
        roleUserMapRepository.saveAndFlush(roleUserMap);

        int databaseSizeBeforeDelete = roleUserMapRepository.findAll().size();

        // Delete the roleUserMap
        restRoleUserMapMockMvc
            .perform(delete(ENTITY_API_URL_ID, roleUserMap.getRoleUserMapId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RoleUserMap> roleUserMapList = roleUserMapRepository.findAll();
        assertThat(roleUserMapList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
