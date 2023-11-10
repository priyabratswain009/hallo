package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.RoleMaster;
import com.sunknowledge.dme.rcm.repository.RoleMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.RoleMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.RoleMasterMapper;
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
 * Integration tests for the {@link RoleMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RoleMasterResourceIT {

    private static final String DEFAULT_ROLE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ROLE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_ROLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ROLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ROLE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_ROLE_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_ROLE_NO = "AAAAAAAAAA";
    private static final String UPDATED_ROLE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final UUID DEFAULT_ROLE_MASTER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_ROLE_MASTER_UUID = UUID.randomUUID();

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/role-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{roleId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private RoleMasterRepository roleMasterRepository;

    @Autowired
    private RoleMasterMapper roleMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRoleMasterMockMvc;

    private RoleMaster roleMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RoleMaster createEntity(EntityManager em) {
        RoleMaster roleMaster = new RoleMaster()
            .roleCode(DEFAULT_ROLE_CODE)
            .roleName(DEFAULT_ROLE_NAME)
            .roleDescription(DEFAULT_ROLE_DESCRIPTION)
            .roleNo(DEFAULT_ROLE_NO)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .roleMasterUuid(DEFAULT_ROLE_MASTER_UUID)
            .updatedDate(DEFAULT_UPDATED_DATE);
        return roleMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RoleMaster createUpdatedEntity(EntityManager em) {
        RoleMaster roleMaster = new RoleMaster()
            .roleCode(UPDATED_ROLE_CODE)
            .roleName(UPDATED_ROLE_NAME)
            .roleDescription(UPDATED_ROLE_DESCRIPTION)
            .roleNo(UPDATED_ROLE_NO)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .roleMasterUuid(UPDATED_ROLE_MASTER_UUID)
            .updatedDate(UPDATED_UPDATED_DATE);
        return roleMaster;
    }

    @BeforeEach
    public void initTest() {
        roleMaster = createEntity(em);
    }

    @Test
    @Transactional
    void createRoleMaster() throws Exception {
        int databaseSizeBeforeCreate = roleMasterRepository.findAll().size();
        // Create the RoleMaster
        RoleMasterDTO roleMasterDTO = roleMasterMapper.toDto(roleMaster);
        restRoleMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(roleMasterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the RoleMaster in the database
        List<RoleMaster> roleMasterList = roleMasterRepository.findAll();
        assertThat(roleMasterList).hasSize(databaseSizeBeforeCreate + 1);
        RoleMaster testRoleMaster = roleMasterList.get(roleMasterList.size() - 1);
        assertThat(testRoleMaster.getRoleCode()).isEqualTo(DEFAULT_ROLE_CODE);
        assertThat(testRoleMaster.getRoleName()).isEqualTo(DEFAULT_ROLE_NAME);
        assertThat(testRoleMaster.getRoleDescription()).isEqualTo(DEFAULT_ROLE_DESCRIPTION);
        assertThat(testRoleMaster.getRoleNo()).isEqualTo(DEFAULT_ROLE_NO);
        assertThat(testRoleMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testRoleMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testRoleMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testRoleMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testRoleMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testRoleMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testRoleMaster.getRoleMasterUuid()).isEqualTo(DEFAULT_ROLE_MASTER_UUID);
        assertThat(testRoleMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
    }

    @Test
    @Transactional
    void createRoleMasterWithExistingId() throws Exception {
        // Create the RoleMaster with an existing ID
        roleMaster.setRoleId(1L);
        RoleMasterDTO roleMasterDTO = roleMasterMapper.toDto(roleMaster);

        int databaseSizeBeforeCreate = roleMasterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRoleMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(roleMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RoleMaster in the database
        List<RoleMaster> roleMasterList = roleMasterRepository.findAll();
        assertThat(roleMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllRoleMasters() throws Exception {
        // Initialize the database
        roleMasterRepository.saveAndFlush(roleMaster);

        // Get all the roleMasterList
        restRoleMasterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=roleId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].roleId").value(hasItem(roleMaster.getRoleId().intValue())))
            .andExpect(jsonPath("$.[*].roleCode").value(hasItem(DEFAULT_ROLE_CODE)))
            .andExpect(jsonPath("$.[*].roleName").value(hasItem(DEFAULT_ROLE_NAME)))
            .andExpect(jsonPath("$.[*].roleDescription").value(hasItem(DEFAULT_ROLE_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].roleNo").value(hasItem(DEFAULT_ROLE_NO)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].roleMasterUuid").value(hasItem(DEFAULT_ROLE_MASTER_UUID.toString())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())));
    }

    @Test
    @Transactional
    void getRoleMaster() throws Exception {
        // Initialize the database
        roleMasterRepository.saveAndFlush(roleMaster);

        // Get the roleMaster
        restRoleMasterMockMvc
            .perform(get(ENTITY_API_URL_ID, roleMaster.getRoleId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.roleId").value(roleMaster.getRoleId().intValue()))
            .andExpect(jsonPath("$.roleCode").value(DEFAULT_ROLE_CODE))
            .andExpect(jsonPath("$.roleName").value(DEFAULT_ROLE_NAME))
            .andExpect(jsonPath("$.roleDescription").value(DEFAULT_ROLE_DESCRIPTION))
            .andExpect(jsonPath("$.roleNo").value(DEFAULT_ROLE_NO))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.roleMasterUuid").value(DEFAULT_ROLE_MASTER_UUID.toString()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingRoleMaster() throws Exception {
        // Get the roleMaster
        restRoleMasterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewRoleMaster() throws Exception {
        // Initialize the database
        roleMasterRepository.saveAndFlush(roleMaster);

        int databaseSizeBeforeUpdate = roleMasterRepository.findAll().size();

        // Update the roleMaster
        RoleMaster updatedRoleMaster = roleMasterRepository.findById(roleMaster.getRoleId()).get();
        // Disconnect from session so that the updates on updatedRoleMaster are not directly saved in db
        em.detach(updatedRoleMaster);
        updatedRoleMaster
            .roleCode(UPDATED_ROLE_CODE)
            .roleName(UPDATED_ROLE_NAME)
            .roleDescription(UPDATED_ROLE_DESCRIPTION)
            .roleNo(UPDATED_ROLE_NO)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .roleMasterUuid(UPDATED_ROLE_MASTER_UUID)
            .updatedDate(UPDATED_UPDATED_DATE);
        RoleMasterDTO roleMasterDTO = roleMasterMapper.toDto(updatedRoleMaster);

        restRoleMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, roleMasterDTO.getRoleId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(roleMasterDTO))
            )
            .andExpect(status().isOk());

        // Validate the RoleMaster in the database
        List<RoleMaster> roleMasterList = roleMasterRepository.findAll();
        assertThat(roleMasterList).hasSize(databaseSizeBeforeUpdate);
        RoleMaster testRoleMaster = roleMasterList.get(roleMasterList.size() - 1);
        assertThat(testRoleMaster.getRoleCode()).isEqualTo(UPDATED_ROLE_CODE);
        assertThat(testRoleMaster.getRoleName()).isEqualTo(UPDATED_ROLE_NAME);
        assertThat(testRoleMaster.getRoleDescription()).isEqualTo(UPDATED_ROLE_DESCRIPTION);
        assertThat(testRoleMaster.getRoleNo()).isEqualTo(UPDATED_ROLE_NO);
        assertThat(testRoleMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testRoleMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testRoleMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testRoleMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testRoleMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testRoleMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testRoleMaster.getRoleMasterUuid()).isEqualTo(UPDATED_ROLE_MASTER_UUID);
        assertThat(testRoleMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
    }

    @Test
    @Transactional
    void putNonExistingRoleMaster() throws Exception {
        int databaseSizeBeforeUpdate = roleMasterRepository.findAll().size();
        roleMaster.setRoleId(count.incrementAndGet());

        // Create the RoleMaster
        RoleMasterDTO roleMasterDTO = roleMasterMapper.toDto(roleMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRoleMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, roleMasterDTO.getRoleId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(roleMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RoleMaster in the database
        List<RoleMaster> roleMasterList = roleMasterRepository.findAll();
        assertThat(roleMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRoleMaster() throws Exception {
        int databaseSizeBeforeUpdate = roleMasterRepository.findAll().size();
        roleMaster.setRoleId(count.incrementAndGet());

        // Create the RoleMaster
        RoleMasterDTO roleMasterDTO = roleMasterMapper.toDto(roleMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRoleMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(roleMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RoleMaster in the database
        List<RoleMaster> roleMasterList = roleMasterRepository.findAll();
        assertThat(roleMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRoleMaster() throws Exception {
        int databaseSizeBeforeUpdate = roleMasterRepository.findAll().size();
        roleMaster.setRoleId(count.incrementAndGet());

        // Create the RoleMaster
        RoleMasterDTO roleMasterDTO = roleMasterMapper.toDto(roleMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRoleMasterMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(roleMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RoleMaster in the database
        List<RoleMaster> roleMasterList = roleMasterRepository.findAll();
        assertThat(roleMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRoleMasterWithPatch() throws Exception {
        // Initialize the database
        roleMasterRepository.saveAndFlush(roleMaster);

        int databaseSizeBeforeUpdate = roleMasterRepository.findAll().size();

        // Update the roleMaster using partial update
        RoleMaster partialUpdatedRoleMaster = new RoleMaster();
        partialUpdatedRoleMaster.setRoleId(roleMaster.getRoleId());

        partialUpdatedRoleMaster
            .roleCode(UPDATED_ROLE_CODE)
            .roleName(UPDATED_ROLE_NAME)
            .roleDescription(UPDATED_ROLE_DESCRIPTION)
            .roleNo(UPDATED_ROLE_NO)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .roleMasterUuid(UPDATED_ROLE_MASTER_UUID)
            .updatedDate(UPDATED_UPDATED_DATE);

        restRoleMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRoleMaster.getRoleId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRoleMaster))
            )
            .andExpect(status().isOk());

        // Validate the RoleMaster in the database
        List<RoleMaster> roleMasterList = roleMasterRepository.findAll();
        assertThat(roleMasterList).hasSize(databaseSizeBeforeUpdate);
        RoleMaster testRoleMaster = roleMasterList.get(roleMasterList.size() - 1);
        assertThat(testRoleMaster.getRoleCode()).isEqualTo(UPDATED_ROLE_CODE);
        assertThat(testRoleMaster.getRoleName()).isEqualTo(UPDATED_ROLE_NAME);
        assertThat(testRoleMaster.getRoleDescription()).isEqualTo(UPDATED_ROLE_DESCRIPTION);
        assertThat(testRoleMaster.getRoleNo()).isEqualTo(UPDATED_ROLE_NO);
        assertThat(testRoleMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testRoleMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testRoleMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testRoleMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testRoleMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testRoleMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testRoleMaster.getRoleMasterUuid()).isEqualTo(UPDATED_ROLE_MASTER_UUID);
        assertThat(testRoleMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
    }

    @Test
    @Transactional
    void fullUpdateRoleMasterWithPatch() throws Exception {
        // Initialize the database
        roleMasterRepository.saveAndFlush(roleMaster);

        int databaseSizeBeforeUpdate = roleMasterRepository.findAll().size();

        // Update the roleMaster using partial update
        RoleMaster partialUpdatedRoleMaster = new RoleMaster();
        partialUpdatedRoleMaster.setRoleId(roleMaster.getRoleId());

        partialUpdatedRoleMaster
            .roleCode(UPDATED_ROLE_CODE)
            .roleName(UPDATED_ROLE_NAME)
            .roleDescription(UPDATED_ROLE_DESCRIPTION)
            .roleNo(UPDATED_ROLE_NO)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .roleMasterUuid(UPDATED_ROLE_MASTER_UUID)
            .updatedDate(UPDATED_UPDATED_DATE);

        restRoleMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRoleMaster.getRoleId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRoleMaster))
            )
            .andExpect(status().isOk());

        // Validate the RoleMaster in the database
        List<RoleMaster> roleMasterList = roleMasterRepository.findAll();
        assertThat(roleMasterList).hasSize(databaseSizeBeforeUpdate);
        RoleMaster testRoleMaster = roleMasterList.get(roleMasterList.size() - 1);
        assertThat(testRoleMaster.getRoleCode()).isEqualTo(UPDATED_ROLE_CODE);
        assertThat(testRoleMaster.getRoleName()).isEqualTo(UPDATED_ROLE_NAME);
        assertThat(testRoleMaster.getRoleDescription()).isEqualTo(UPDATED_ROLE_DESCRIPTION);
        assertThat(testRoleMaster.getRoleNo()).isEqualTo(UPDATED_ROLE_NO);
        assertThat(testRoleMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testRoleMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testRoleMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testRoleMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testRoleMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testRoleMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testRoleMaster.getRoleMasterUuid()).isEqualTo(UPDATED_ROLE_MASTER_UUID);
        assertThat(testRoleMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingRoleMaster() throws Exception {
        int databaseSizeBeforeUpdate = roleMasterRepository.findAll().size();
        roleMaster.setRoleId(count.incrementAndGet());

        // Create the RoleMaster
        RoleMasterDTO roleMasterDTO = roleMasterMapper.toDto(roleMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRoleMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, roleMasterDTO.getRoleId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(roleMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RoleMaster in the database
        List<RoleMaster> roleMasterList = roleMasterRepository.findAll();
        assertThat(roleMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRoleMaster() throws Exception {
        int databaseSizeBeforeUpdate = roleMasterRepository.findAll().size();
        roleMaster.setRoleId(count.incrementAndGet());

        // Create the RoleMaster
        RoleMasterDTO roleMasterDTO = roleMasterMapper.toDto(roleMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRoleMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(roleMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RoleMaster in the database
        List<RoleMaster> roleMasterList = roleMasterRepository.findAll();
        assertThat(roleMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRoleMaster() throws Exception {
        int databaseSizeBeforeUpdate = roleMasterRepository.findAll().size();
        roleMaster.setRoleId(count.incrementAndGet());

        // Create the RoleMaster
        RoleMasterDTO roleMasterDTO = roleMasterMapper.toDto(roleMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRoleMasterMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(roleMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RoleMaster in the database
        List<RoleMaster> roleMasterList = roleMasterRepository.findAll();
        assertThat(roleMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRoleMaster() throws Exception {
        // Initialize the database
        roleMasterRepository.saveAndFlush(roleMaster);

        int databaseSizeBeforeDelete = roleMasterRepository.findAll().size();

        // Delete the roleMaster
        restRoleMasterMockMvc
            .perform(delete(ENTITY_API_URL_ID, roleMaster.getRoleId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RoleMaster> roleMasterList = roleMasterRepository.findAll();
        assertThat(roleMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
