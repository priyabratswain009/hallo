package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ItemGroup;
import com.sunknowledge.dme.rcm.repository.ItemGroupRepository;
import com.sunknowledge.dme.rcm.service.dto.ItemGroupDTO;
import com.sunknowledge.dme.rcm.service.mapper.ItemGroupMapper;
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
 * Integration tests for the {@link ItemGroupResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ItemGroupResourceIT {

    private static final String DEFAULT_ITEM_GROUP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_GROUP_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final UUID DEFAULT_ITEM_GROUP_UUID = UUID.randomUUID();
    private static final UUID UPDATED_ITEM_GROUP_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/item-groups";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{itemGroupId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ItemGroupRepository itemGroupRepository;

    @Autowired
    private ItemGroupMapper itemGroupMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restItemGroupMockMvc;

    private ItemGroup itemGroup;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemGroup createEntity(EntityManager em) {
        ItemGroup itemGroup = new ItemGroup()
            .itemGroupName(DEFAULT_ITEM_GROUP_NAME)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .itemGroupUuid(DEFAULT_ITEM_GROUP_UUID);
        return itemGroup;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemGroup createUpdatedEntity(EntityManager em) {
        ItemGroup itemGroup = new ItemGroup()
            .itemGroupName(UPDATED_ITEM_GROUP_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .itemGroupUuid(UPDATED_ITEM_GROUP_UUID);
        return itemGroup;
    }

    @BeforeEach
    public void initTest() {
        itemGroup = createEntity(em);
    }

    @Test
    @Transactional
    void createItemGroup() throws Exception {
        int databaseSizeBeforeCreate = itemGroupRepository.findAll().size();
        // Create the ItemGroup
        ItemGroupDTO itemGroupDTO = itemGroupMapper.toDto(itemGroup);
        restItemGroupMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemGroupDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ItemGroup in the database
        List<ItemGroup> itemGroupList = itemGroupRepository.findAll();
        assertThat(itemGroupList).hasSize(databaseSizeBeforeCreate + 1);
        ItemGroup testItemGroup = itemGroupList.get(itemGroupList.size() - 1);
        assertThat(testItemGroup.getItemGroupName()).isEqualTo(DEFAULT_ITEM_GROUP_NAME);
        assertThat(testItemGroup.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testItemGroup.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testItemGroup.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testItemGroup.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testItemGroup.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testItemGroup.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testItemGroup.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testItemGroup.getItemGroupUuid()).isEqualTo(DEFAULT_ITEM_GROUP_UUID);
    }

    @Test
    @Transactional
    void createItemGroupWithExistingId() throws Exception {
        // Create the ItemGroup with an existing ID
        itemGroup.setItemGroupId(1L);
        ItemGroupDTO itemGroupDTO = itemGroupMapper.toDto(itemGroup);

        int databaseSizeBeforeCreate = itemGroupRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemGroupMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemGroupDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemGroup in the database
        List<ItemGroup> itemGroupList = itemGroupRepository.findAll();
        assertThat(itemGroupList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllItemGroups() throws Exception {
        // Initialize the database
        itemGroupRepository.saveAndFlush(itemGroup);

        // Get all the itemGroupList
        restItemGroupMockMvc
            .perform(get(ENTITY_API_URL + "?sort=itemGroupId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].itemGroupId").value(hasItem(itemGroup.getItemGroupId().intValue())))
            .andExpect(jsonPath("$.[*].itemGroupName").value(hasItem(DEFAULT_ITEM_GROUP_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].itemGroupUuid").value(hasItem(DEFAULT_ITEM_GROUP_UUID.toString())));
    }

    @Test
    @Transactional
    void getItemGroup() throws Exception {
        // Initialize the database
        itemGroupRepository.saveAndFlush(itemGroup);

        // Get the itemGroup
        restItemGroupMockMvc
            .perform(get(ENTITY_API_URL_ID, itemGroup.getItemGroupId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.itemGroupId").value(itemGroup.getItemGroupId().intValue()))
            .andExpect(jsonPath("$.itemGroupName").value(DEFAULT_ITEM_GROUP_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.itemGroupUuid").value(DEFAULT_ITEM_GROUP_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingItemGroup() throws Exception {
        // Get the itemGroup
        restItemGroupMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewItemGroup() throws Exception {
        // Initialize the database
        itemGroupRepository.saveAndFlush(itemGroup);

        int databaseSizeBeforeUpdate = itemGroupRepository.findAll().size();

        // Update the itemGroup
        ItemGroup updatedItemGroup = itemGroupRepository.findById(itemGroup.getItemGroupId()).get();
        // Disconnect from session so that the updates on updatedItemGroup are not directly saved in db
        em.detach(updatedItemGroup);
        updatedItemGroup
            .itemGroupName(UPDATED_ITEM_GROUP_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .itemGroupUuid(UPDATED_ITEM_GROUP_UUID);
        ItemGroupDTO itemGroupDTO = itemGroupMapper.toDto(updatedItemGroup);

        restItemGroupMockMvc
            .perform(
                put(ENTITY_API_URL_ID, itemGroupDTO.getItemGroupId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemGroupDTO))
            )
            .andExpect(status().isOk());

        // Validate the ItemGroup in the database
        List<ItemGroup> itemGroupList = itemGroupRepository.findAll();
        assertThat(itemGroupList).hasSize(databaseSizeBeforeUpdate);
        ItemGroup testItemGroup = itemGroupList.get(itemGroupList.size() - 1);
        assertThat(testItemGroup.getItemGroupName()).isEqualTo(UPDATED_ITEM_GROUP_NAME);
        assertThat(testItemGroup.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testItemGroup.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testItemGroup.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testItemGroup.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testItemGroup.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testItemGroup.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testItemGroup.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testItemGroup.getItemGroupUuid()).isEqualTo(UPDATED_ITEM_GROUP_UUID);
    }

    @Test
    @Transactional
    void putNonExistingItemGroup() throws Exception {
        int databaseSizeBeforeUpdate = itemGroupRepository.findAll().size();
        itemGroup.setItemGroupId(count.incrementAndGet());

        // Create the ItemGroup
        ItemGroupDTO itemGroupDTO = itemGroupMapper.toDto(itemGroup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemGroupMockMvc
            .perform(
                put(ENTITY_API_URL_ID, itemGroupDTO.getItemGroupId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemGroupDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemGroup in the database
        List<ItemGroup> itemGroupList = itemGroupRepository.findAll();
        assertThat(itemGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchItemGroup() throws Exception {
        int databaseSizeBeforeUpdate = itemGroupRepository.findAll().size();
        itemGroup.setItemGroupId(count.incrementAndGet());

        // Create the ItemGroup
        ItemGroupDTO itemGroupDTO = itemGroupMapper.toDto(itemGroup);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemGroupMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemGroupDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemGroup in the database
        List<ItemGroup> itemGroupList = itemGroupRepository.findAll();
        assertThat(itemGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamItemGroup() throws Exception {
        int databaseSizeBeforeUpdate = itemGroupRepository.findAll().size();
        itemGroup.setItemGroupId(count.incrementAndGet());

        // Create the ItemGroup
        ItemGroupDTO itemGroupDTO = itemGroupMapper.toDto(itemGroup);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemGroupMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemGroupDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ItemGroup in the database
        List<ItemGroup> itemGroupList = itemGroupRepository.findAll();
        assertThat(itemGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateItemGroupWithPatch() throws Exception {
        // Initialize the database
        itemGroupRepository.saveAndFlush(itemGroup);

        int databaseSizeBeforeUpdate = itemGroupRepository.findAll().size();

        // Update the itemGroup using partial update
        ItemGroup partialUpdatedItemGroup = new ItemGroup();
        partialUpdatedItemGroup.setItemGroupId(itemGroup.getItemGroupId());

        partialUpdatedItemGroup
            .itemGroupName(UPDATED_ITEM_GROUP_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedByName(UPDATED_UPDATED_BY_NAME);

        restItemGroupMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedItemGroup.getItemGroupId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedItemGroup))
            )
            .andExpect(status().isOk());

        // Validate the ItemGroup in the database
        List<ItemGroup> itemGroupList = itemGroupRepository.findAll();
        assertThat(itemGroupList).hasSize(databaseSizeBeforeUpdate);
        ItemGroup testItemGroup = itemGroupList.get(itemGroupList.size() - 1);
        assertThat(testItemGroup.getItemGroupName()).isEqualTo(UPDATED_ITEM_GROUP_NAME);
        assertThat(testItemGroup.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testItemGroup.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testItemGroup.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testItemGroup.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testItemGroup.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testItemGroup.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testItemGroup.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testItemGroup.getItemGroupUuid()).isEqualTo(DEFAULT_ITEM_GROUP_UUID);
    }

    @Test
    @Transactional
    void fullUpdateItemGroupWithPatch() throws Exception {
        // Initialize the database
        itemGroupRepository.saveAndFlush(itemGroup);

        int databaseSizeBeforeUpdate = itemGroupRepository.findAll().size();

        // Update the itemGroup using partial update
        ItemGroup partialUpdatedItemGroup = new ItemGroup();
        partialUpdatedItemGroup.setItemGroupId(itemGroup.getItemGroupId());

        partialUpdatedItemGroup
            .itemGroupName(UPDATED_ITEM_GROUP_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .itemGroupUuid(UPDATED_ITEM_GROUP_UUID);

        restItemGroupMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedItemGroup.getItemGroupId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedItemGroup))
            )
            .andExpect(status().isOk());

        // Validate the ItemGroup in the database
        List<ItemGroup> itemGroupList = itemGroupRepository.findAll();
        assertThat(itemGroupList).hasSize(databaseSizeBeforeUpdate);
        ItemGroup testItemGroup = itemGroupList.get(itemGroupList.size() - 1);
        assertThat(testItemGroup.getItemGroupName()).isEqualTo(UPDATED_ITEM_GROUP_NAME);
        assertThat(testItemGroup.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testItemGroup.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testItemGroup.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testItemGroup.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testItemGroup.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testItemGroup.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testItemGroup.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testItemGroup.getItemGroupUuid()).isEqualTo(UPDATED_ITEM_GROUP_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingItemGroup() throws Exception {
        int databaseSizeBeforeUpdate = itemGroupRepository.findAll().size();
        itemGroup.setItemGroupId(count.incrementAndGet());

        // Create the ItemGroup
        ItemGroupDTO itemGroupDTO = itemGroupMapper.toDto(itemGroup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemGroupMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, itemGroupDTO.getItemGroupId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemGroupDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemGroup in the database
        List<ItemGroup> itemGroupList = itemGroupRepository.findAll();
        assertThat(itemGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchItemGroup() throws Exception {
        int databaseSizeBeforeUpdate = itemGroupRepository.findAll().size();
        itemGroup.setItemGroupId(count.incrementAndGet());

        // Create the ItemGroup
        ItemGroupDTO itemGroupDTO = itemGroupMapper.toDto(itemGroup);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemGroupMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemGroupDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemGroup in the database
        List<ItemGroup> itemGroupList = itemGroupRepository.findAll();
        assertThat(itemGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamItemGroup() throws Exception {
        int databaseSizeBeforeUpdate = itemGroupRepository.findAll().size();
        itemGroup.setItemGroupId(count.incrementAndGet());

        // Create the ItemGroup
        ItemGroupDTO itemGroupDTO = itemGroupMapper.toDto(itemGroup);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemGroupMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemGroupDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ItemGroup in the database
        List<ItemGroup> itemGroupList = itemGroupRepository.findAll();
        assertThat(itemGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteItemGroup() throws Exception {
        // Initialize the database
        itemGroupRepository.saveAndFlush(itemGroup);

        int databaseSizeBeforeDelete = itemGroupRepository.findAll().size();

        // Delete the itemGroup
        restItemGroupMockMvc
            .perform(delete(ENTITY_API_URL_ID, itemGroup.getItemGroupId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ItemGroup> itemGroupList = itemGroupRepository.findAll();
        assertThat(itemGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
