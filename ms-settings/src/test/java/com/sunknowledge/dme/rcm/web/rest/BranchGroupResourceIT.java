package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.BranchGroup;
import com.sunknowledge.dme.rcm.repository.BranchGroupRepository;
import com.sunknowledge.dme.rcm.service.dto.BranchGroupDTO;
import com.sunknowledge.dme.rcm.service.mapper.BranchGroupMapper;
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
 * Integration tests for the {@link BranchGroupResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BranchGroupResourceIT {

    private static final String DEFAULT_BRANCH_GROUP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_GROUP_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_COMPANY_ID = 1L;
    private static final Long UPDATED_COMPANY_ID = 2L;

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

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final UUID DEFAULT_BRANCH_GROUP_UUID = UUID.randomUUID();
    private static final UUID UPDATED_BRANCH_GROUP_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/branch-groups";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{branchGroupId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BranchGroupRepository branchGroupRepository;

    @Autowired
    private BranchGroupMapper branchGroupMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBranchGroupMockMvc;

    private BranchGroup branchGroup;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BranchGroup createEntity(EntityManager em) {
        BranchGroup branchGroup = new BranchGroup()
            .branchGroupName(DEFAULT_BRANCH_GROUP_NAME)
            .companyId(DEFAULT_COMPANY_ID)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .branchGroupUuid(DEFAULT_BRANCH_GROUP_UUID);
        return branchGroup;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BranchGroup createUpdatedEntity(EntityManager em) {
        BranchGroup branchGroup = new BranchGroup()
            .branchGroupName(UPDATED_BRANCH_GROUP_NAME)
            .companyId(UPDATED_COMPANY_ID)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .branchGroupUuid(UPDATED_BRANCH_GROUP_UUID);
        return branchGroup;
    }

    @BeforeEach
    public void initTest() {
        branchGroup = createEntity(em);
    }

    @Test
    @Transactional
    void createBranchGroup() throws Exception {
        int databaseSizeBeforeCreate = branchGroupRepository.findAll().size();
        // Create the BranchGroup
        BranchGroupDTO branchGroupDTO = branchGroupMapper.toDto(branchGroup);
        restBranchGroupMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchGroupDTO))
            )
            .andExpect(status().isCreated());

        // Validate the BranchGroup in the database
        List<BranchGroup> branchGroupList = branchGroupRepository.findAll();
        assertThat(branchGroupList).hasSize(databaseSizeBeforeCreate + 1);
        BranchGroup testBranchGroup = branchGroupList.get(branchGroupList.size() - 1);
        assertThat(testBranchGroup.getBranchGroupName()).isEqualTo(DEFAULT_BRANCH_GROUP_NAME);
        assertThat(testBranchGroup.getCompanyId()).isEqualTo(DEFAULT_COMPANY_ID);
        assertThat(testBranchGroup.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testBranchGroup.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testBranchGroup.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testBranchGroup.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testBranchGroup.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testBranchGroup.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testBranchGroup.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testBranchGroup.getBranchGroupUuid()).isEqualTo(DEFAULT_BRANCH_GROUP_UUID);
    }

    @Test
    @Transactional
    void createBranchGroupWithExistingId() throws Exception {
        // Create the BranchGroup with an existing ID
        branchGroup.setBranchGroupId(1L);
        BranchGroupDTO branchGroupDTO = branchGroupMapper.toDto(branchGroup);

        int databaseSizeBeforeCreate = branchGroupRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBranchGroupMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchGroupDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchGroup in the database
        List<BranchGroup> branchGroupList = branchGroupRepository.findAll();
        assertThat(branchGroupList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBranchGroups() throws Exception {
        // Initialize the database
        branchGroupRepository.saveAndFlush(branchGroup);

        // Get all the branchGroupList
        restBranchGroupMockMvc
            .perform(get(ENTITY_API_URL + "?sort=branchGroupId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].branchGroupId").value(hasItem(branchGroup.getBranchGroupId().intValue())))
            .andExpect(jsonPath("$.[*].branchGroupName").value(hasItem(DEFAULT_BRANCH_GROUP_NAME)))
            .andExpect(jsonPath("$.[*].companyId").value(hasItem(DEFAULT_COMPANY_ID.intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].branchGroupUuid").value(hasItem(DEFAULT_BRANCH_GROUP_UUID.toString())));
    }

    @Test
    @Transactional
    void getBranchGroup() throws Exception {
        // Initialize the database
        branchGroupRepository.saveAndFlush(branchGroup);

        // Get the branchGroup
        restBranchGroupMockMvc
            .perform(get(ENTITY_API_URL_ID, branchGroup.getBranchGroupId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.branchGroupId").value(branchGroup.getBranchGroupId().intValue()))
            .andExpect(jsonPath("$.branchGroupName").value(DEFAULT_BRANCH_GROUP_NAME))
            .andExpect(jsonPath("$.companyId").value(DEFAULT_COMPANY_ID.intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.branchGroupUuid").value(DEFAULT_BRANCH_GROUP_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingBranchGroup() throws Exception {
        // Get the branchGroup
        restBranchGroupMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingBranchGroup() throws Exception {
        // Initialize the database
        branchGroupRepository.saveAndFlush(branchGroup);

        int databaseSizeBeforeUpdate = branchGroupRepository.findAll().size();

        // Update the branchGroup
        BranchGroup updatedBranchGroup = branchGroupRepository.findById(branchGroup.getBranchGroupId()).get();
        // Disconnect from session so that the updates on updatedBranchGroup are not directly saved in db
        em.detach(updatedBranchGroup);
        updatedBranchGroup
            .branchGroupName(UPDATED_BRANCH_GROUP_NAME)
            .companyId(UPDATED_COMPANY_ID)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .branchGroupUuid(UPDATED_BRANCH_GROUP_UUID);
        BranchGroupDTO branchGroupDTO = branchGroupMapper.toDto(updatedBranchGroup);

        restBranchGroupMockMvc
            .perform(
                put(ENTITY_API_URL_ID, branchGroupDTO.getBranchGroupId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchGroupDTO))
            )
            .andExpect(status().isOk());

        // Validate the BranchGroup in the database
        List<BranchGroup> branchGroupList = branchGroupRepository.findAll();
        assertThat(branchGroupList).hasSize(databaseSizeBeforeUpdate);
        BranchGroup testBranchGroup = branchGroupList.get(branchGroupList.size() - 1);
        assertThat(testBranchGroup.getBranchGroupName()).isEqualTo(UPDATED_BRANCH_GROUP_NAME);
        assertThat(testBranchGroup.getCompanyId()).isEqualTo(UPDATED_COMPANY_ID);
        assertThat(testBranchGroup.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testBranchGroup.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testBranchGroup.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBranchGroup.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testBranchGroup.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testBranchGroup.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testBranchGroup.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testBranchGroup.getBranchGroupUuid()).isEqualTo(UPDATED_BRANCH_GROUP_UUID);
    }

    @Test
    @Transactional
    void putNonExistingBranchGroup() throws Exception {
        int databaseSizeBeforeUpdate = branchGroupRepository.findAll().size();
        branchGroup.setBranchGroupId(count.incrementAndGet());

        // Create the BranchGroup
        BranchGroupDTO branchGroupDTO = branchGroupMapper.toDto(branchGroup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBranchGroupMockMvc
            .perform(
                put(ENTITY_API_URL_ID, branchGroupDTO.getBranchGroupId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchGroupDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchGroup in the database
        List<BranchGroup> branchGroupList = branchGroupRepository.findAll();
        assertThat(branchGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBranchGroup() throws Exception {
        int databaseSizeBeforeUpdate = branchGroupRepository.findAll().size();
        branchGroup.setBranchGroupId(count.incrementAndGet());

        // Create the BranchGroup
        BranchGroupDTO branchGroupDTO = branchGroupMapper.toDto(branchGroup);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchGroupMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchGroupDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchGroup in the database
        List<BranchGroup> branchGroupList = branchGroupRepository.findAll();
        assertThat(branchGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBranchGroup() throws Exception {
        int databaseSizeBeforeUpdate = branchGroupRepository.findAll().size();
        branchGroup.setBranchGroupId(count.incrementAndGet());

        // Create the BranchGroup
        BranchGroupDTO branchGroupDTO = branchGroupMapper.toDto(branchGroup);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchGroupMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchGroupDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BranchGroup in the database
        List<BranchGroup> branchGroupList = branchGroupRepository.findAll();
        assertThat(branchGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBranchGroupWithPatch() throws Exception {
        // Initialize the database
        branchGroupRepository.saveAndFlush(branchGroup);

        int databaseSizeBeforeUpdate = branchGroupRepository.findAll().size();

        // Update the branchGroup using partial update
        BranchGroup partialUpdatedBranchGroup = new BranchGroup();
        partialUpdatedBranchGroup.setBranchGroupId(branchGroup.getBranchGroupId());

        partialUpdatedBranchGroup
            .branchGroupName(UPDATED_BRANCH_GROUP_NAME)
            .companyId(UPDATED_COMPANY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME);

        restBranchGroupMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBranchGroup.getBranchGroupId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBranchGroup))
            )
            .andExpect(status().isOk());

        // Validate the BranchGroup in the database
        List<BranchGroup> branchGroupList = branchGroupRepository.findAll();
        assertThat(branchGroupList).hasSize(databaseSizeBeforeUpdate);
        BranchGroup testBranchGroup = branchGroupList.get(branchGroupList.size() - 1);
        assertThat(testBranchGroup.getBranchGroupName()).isEqualTo(UPDATED_BRANCH_GROUP_NAME);
        assertThat(testBranchGroup.getCompanyId()).isEqualTo(UPDATED_COMPANY_ID);
        assertThat(testBranchGroup.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testBranchGroup.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testBranchGroup.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testBranchGroup.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testBranchGroup.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testBranchGroup.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testBranchGroup.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testBranchGroup.getBranchGroupUuid()).isEqualTo(DEFAULT_BRANCH_GROUP_UUID);
    }

    @Test
    @Transactional
    void fullUpdateBranchGroupWithPatch() throws Exception {
        // Initialize the database
        branchGroupRepository.saveAndFlush(branchGroup);

        int databaseSizeBeforeUpdate = branchGroupRepository.findAll().size();

        // Update the branchGroup using partial update
        BranchGroup partialUpdatedBranchGroup = new BranchGroup();
        partialUpdatedBranchGroup.setBranchGroupId(branchGroup.getBranchGroupId());

        partialUpdatedBranchGroup
            .branchGroupName(UPDATED_BRANCH_GROUP_NAME)
            .companyId(UPDATED_COMPANY_ID)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .branchGroupUuid(UPDATED_BRANCH_GROUP_UUID);

        restBranchGroupMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBranchGroup.getBranchGroupId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBranchGroup))
            )
            .andExpect(status().isOk());

        // Validate the BranchGroup in the database
        List<BranchGroup> branchGroupList = branchGroupRepository.findAll();
        assertThat(branchGroupList).hasSize(databaseSizeBeforeUpdate);
        BranchGroup testBranchGroup = branchGroupList.get(branchGroupList.size() - 1);
        assertThat(testBranchGroup.getBranchGroupName()).isEqualTo(UPDATED_BRANCH_GROUP_NAME);
        assertThat(testBranchGroup.getCompanyId()).isEqualTo(UPDATED_COMPANY_ID);
        assertThat(testBranchGroup.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testBranchGroup.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testBranchGroup.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBranchGroup.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testBranchGroup.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testBranchGroup.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testBranchGroup.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testBranchGroup.getBranchGroupUuid()).isEqualTo(UPDATED_BRANCH_GROUP_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingBranchGroup() throws Exception {
        int databaseSizeBeforeUpdate = branchGroupRepository.findAll().size();
        branchGroup.setBranchGroupId(count.incrementAndGet());

        // Create the BranchGroup
        BranchGroupDTO branchGroupDTO = branchGroupMapper.toDto(branchGroup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBranchGroupMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, branchGroupDTO.getBranchGroupId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(branchGroupDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchGroup in the database
        List<BranchGroup> branchGroupList = branchGroupRepository.findAll();
        assertThat(branchGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBranchGroup() throws Exception {
        int databaseSizeBeforeUpdate = branchGroupRepository.findAll().size();
        branchGroup.setBranchGroupId(count.incrementAndGet());

        // Create the BranchGroup
        BranchGroupDTO branchGroupDTO = branchGroupMapper.toDto(branchGroup);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchGroupMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(branchGroupDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchGroup in the database
        List<BranchGroup> branchGroupList = branchGroupRepository.findAll();
        assertThat(branchGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBranchGroup() throws Exception {
        int databaseSizeBeforeUpdate = branchGroupRepository.findAll().size();
        branchGroup.setBranchGroupId(count.incrementAndGet());

        // Create the BranchGroup
        BranchGroupDTO branchGroupDTO = branchGroupMapper.toDto(branchGroup);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchGroupMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(branchGroupDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BranchGroup in the database
        List<BranchGroup> branchGroupList = branchGroupRepository.findAll();
        assertThat(branchGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBranchGroup() throws Exception {
        // Initialize the database
        branchGroupRepository.saveAndFlush(branchGroup);

        int databaseSizeBeforeDelete = branchGroupRepository.findAll().size();

        // Delete the branchGroup
        restBranchGroupMockMvc
            .perform(delete(ENTITY_API_URL_ID, branchGroup.getBranchGroupId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BranchGroup> branchGroupList = branchGroupRepository.findAll();
        assertThat(branchGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
