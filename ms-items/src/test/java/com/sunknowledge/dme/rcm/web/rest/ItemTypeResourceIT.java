package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ItemType;
import com.sunknowledge.dme.rcm.repository.ItemTypeRepository;
import com.sunknowledge.dme.rcm.service.dto.ItemTypeDTO;
import com.sunknowledge.dme.rcm.service.mapper.ItemTypeMapper;
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
 * Integration tests for the {@link ItemTypeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ItemTypeResourceIT {

    private static final String DEFAULT_ITEM_TYPE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_TYPE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final UUID DEFAULT_ITEM_TYPE_UUID = UUID.randomUUID();
    private static final UUID UPDATED_ITEM_TYPE_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/item-types";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{itemTypeId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ItemTypeRepository itemTypeRepository;

    @Autowired
    private ItemTypeMapper itemTypeMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restItemTypeMockMvc;

    private ItemType itemType;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemType createEntity(EntityManager em) {
        ItemType itemType = new ItemType()
            .itemTypeName(DEFAULT_ITEM_TYPE_NAME)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .itemTypeUuid(DEFAULT_ITEM_TYPE_UUID);
        return itemType;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemType createUpdatedEntity(EntityManager em) {
        ItemType itemType = new ItemType()
            .itemTypeName(UPDATED_ITEM_TYPE_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .itemTypeUuid(UPDATED_ITEM_TYPE_UUID);
        return itemType;
    }

    @BeforeEach
    public void initTest() {
        itemType = createEntity(em);
    }

    @Test
    @Transactional
    void createItemType() throws Exception {
        int databaseSizeBeforeCreate = itemTypeRepository.findAll().size();
        // Create the ItemType
        ItemTypeDTO itemTypeDTO = itemTypeMapper.toDto(itemType);
        restItemTypeMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemTypeDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ItemType in the database
        List<ItemType> itemTypeList = itemTypeRepository.findAll();
        assertThat(itemTypeList).hasSize(databaseSizeBeforeCreate + 1);
        ItemType testItemType = itemTypeList.get(itemTypeList.size() - 1);
        assertThat(testItemType.getItemTypeName()).isEqualTo(DEFAULT_ITEM_TYPE_NAME);
        assertThat(testItemType.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testItemType.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testItemType.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testItemType.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testItemType.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testItemType.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testItemType.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testItemType.getItemTypeUuid()).isEqualTo(DEFAULT_ITEM_TYPE_UUID);
    }

    @Test
    @Transactional
    void createItemTypeWithExistingId() throws Exception {
        // Create the ItemType with an existing ID
        itemType.setItemTypeId(1L);
        ItemTypeDTO itemTypeDTO = itemTypeMapper.toDto(itemType);

        int databaseSizeBeforeCreate = itemTypeRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemTypeMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemType in the database
        List<ItemType> itemTypeList = itemTypeRepository.findAll();
        assertThat(itemTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllItemTypes() throws Exception {
        // Initialize the database
        itemTypeRepository.saveAndFlush(itemType);

        // Get all the itemTypeList
        restItemTypeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=itemTypeId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].itemTypeId").value(hasItem(itemType.getItemTypeId().intValue())))
            .andExpect(jsonPath("$.[*].itemTypeName").value(hasItem(DEFAULT_ITEM_TYPE_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].itemTypeUuid").value(hasItem(DEFAULT_ITEM_TYPE_UUID.toString())));
    }

    @Test
    @Transactional
    void getItemType() throws Exception {
        // Initialize the database
        itemTypeRepository.saveAndFlush(itemType);

        // Get the itemType
        restItemTypeMockMvc
            .perform(get(ENTITY_API_URL_ID, itemType.getItemTypeId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.itemTypeId").value(itemType.getItemTypeId().intValue()))
            .andExpect(jsonPath("$.itemTypeName").value(DEFAULT_ITEM_TYPE_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.itemTypeUuid").value(DEFAULT_ITEM_TYPE_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingItemType() throws Exception {
        // Get the itemType
        restItemTypeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewItemType() throws Exception {
        // Initialize the database
        itemTypeRepository.saveAndFlush(itemType);

        int databaseSizeBeforeUpdate = itemTypeRepository.findAll().size();

        // Update the itemType
        ItemType updatedItemType = itemTypeRepository.findById(itemType.getItemTypeId()).get();
        // Disconnect from session so that the updates on updatedItemType are not directly saved in db
        em.detach(updatedItemType);
        updatedItemType
            .itemTypeName(UPDATED_ITEM_TYPE_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .itemTypeUuid(UPDATED_ITEM_TYPE_UUID);
        ItemTypeDTO itemTypeDTO = itemTypeMapper.toDto(updatedItemType);

        restItemTypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, itemTypeDTO.getItemTypeId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemTypeDTO))
            )
            .andExpect(status().isOk());

        // Validate the ItemType in the database
        List<ItemType> itemTypeList = itemTypeRepository.findAll();
        assertThat(itemTypeList).hasSize(databaseSizeBeforeUpdate);
        ItemType testItemType = itemTypeList.get(itemTypeList.size() - 1);
        assertThat(testItemType.getItemTypeName()).isEqualTo(UPDATED_ITEM_TYPE_NAME);
        assertThat(testItemType.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testItemType.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testItemType.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testItemType.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testItemType.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testItemType.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testItemType.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testItemType.getItemTypeUuid()).isEqualTo(UPDATED_ITEM_TYPE_UUID);
    }

    @Test
    @Transactional
    void putNonExistingItemType() throws Exception {
        int databaseSizeBeforeUpdate = itemTypeRepository.findAll().size();
        itemType.setItemTypeId(count.incrementAndGet());

        // Create the ItemType
        ItemTypeDTO itemTypeDTO = itemTypeMapper.toDto(itemType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemTypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, itemTypeDTO.getItemTypeId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemType in the database
        List<ItemType> itemTypeList = itemTypeRepository.findAll();
        assertThat(itemTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchItemType() throws Exception {
        int databaseSizeBeforeUpdate = itemTypeRepository.findAll().size();
        itemType.setItemTypeId(count.incrementAndGet());

        // Create the ItemType
        ItemTypeDTO itemTypeDTO = itemTypeMapper.toDto(itemType);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemTypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemType in the database
        List<ItemType> itemTypeList = itemTypeRepository.findAll();
        assertThat(itemTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamItemType() throws Exception {
        int databaseSizeBeforeUpdate = itemTypeRepository.findAll().size();
        itemType.setItemTypeId(count.incrementAndGet());

        // Create the ItemType
        ItemTypeDTO itemTypeDTO = itemTypeMapper.toDto(itemType);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemTypeMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemTypeDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ItemType in the database
        List<ItemType> itemTypeList = itemTypeRepository.findAll();
        assertThat(itemTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateItemTypeWithPatch() throws Exception {
        // Initialize the database
        itemTypeRepository.saveAndFlush(itemType);

        int databaseSizeBeforeUpdate = itemTypeRepository.findAll().size();

        // Update the itemType using partial update
        ItemType partialUpdatedItemType = new ItemType();
        partialUpdatedItemType.setItemTypeId(itemType.getItemTypeId());

        partialUpdatedItemType
            .itemTypeName(UPDATED_ITEM_TYPE_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .itemTypeUuid(UPDATED_ITEM_TYPE_UUID);

        restItemTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedItemType.getItemTypeId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedItemType))
            )
            .andExpect(status().isOk());

        // Validate the ItemType in the database
        List<ItemType> itemTypeList = itemTypeRepository.findAll();
        assertThat(itemTypeList).hasSize(databaseSizeBeforeUpdate);
        ItemType testItemType = itemTypeList.get(itemTypeList.size() - 1);
        assertThat(testItemType.getItemTypeName()).isEqualTo(UPDATED_ITEM_TYPE_NAME);
        assertThat(testItemType.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testItemType.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testItemType.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testItemType.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testItemType.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testItemType.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testItemType.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testItemType.getItemTypeUuid()).isEqualTo(UPDATED_ITEM_TYPE_UUID);
    }

    @Test
    @Transactional
    void fullUpdateItemTypeWithPatch() throws Exception {
        // Initialize the database
        itemTypeRepository.saveAndFlush(itemType);

        int databaseSizeBeforeUpdate = itemTypeRepository.findAll().size();

        // Update the itemType using partial update
        ItemType partialUpdatedItemType = new ItemType();
        partialUpdatedItemType.setItemTypeId(itemType.getItemTypeId());

        partialUpdatedItemType
            .itemTypeName(UPDATED_ITEM_TYPE_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .itemTypeUuid(UPDATED_ITEM_TYPE_UUID);

        restItemTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedItemType.getItemTypeId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedItemType))
            )
            .andExpect(status().isOk());

        // Validate the ItemType in the database
        List<ItemType> itemTypeList = itemTypeRepository.findAll();
        assertThat(itemTypeList).hasSize(databaseSizeBeforeUpdate);
        ItemType testItemType = itemTypeList.get(itemTypeList.size() - 1);
        assertThat(testItemType.getItemTypeName()).isEqualTo(UPDATED_ITEM_TYPE_NAME);
        assertThat(testItemType.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testItemType.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testItemType.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testItemType.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testItemType.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testItemType.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testItemType.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testItemType.getItemTypeUuid()).isEqualTo(UPDATED_ITEM_TYPE_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingItemType() throws Exception {
        int databaseSizeBeforeUpdate = itemTypeRepository.findAll().size();
        itemType.setItemTypeId(count.incrementAndGet());

        // Create the ItemType
        ItemTypeDTO itemTypeDTO = itemTypeMapper.toDto(itemType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, itemTypeDTO.getItemTypeId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemType in the database
        List<ItemType> itemTypeList = itemTypeRepository.findAll();
        assertThat(itemTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchItemType() throws Exception {
        int databaseSizeBeforeUpdate = itemTypeRepository.findAll().size();
        itemType.setItemTypeId(count.incrementAndGet());

        // Create the ItemType
        ItemTypeDTO itemTypeDTO = itemTypeMapper.toDto(itemType);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemType in the database
        List<ItemType> itemTypeList = itemTypeRepository.findAll();
        assertThat(itemTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamItemType() throws Exception {
        int databaseSizeBeforeUpdate = itemTypeRepository.findAll().size();
        itemType.setItemTypeId(count.incrementAndGet());

        // Create the ItemType
        ItemTypeDTO itemTypeDTO = itemTypeMapper.toDto(itemType);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemTypeMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemTypeDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ItemType in the database
        List<ItemType> itemTypeList = itemTypeRepository.findAll();
        assertThat(itemTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteItemType() throws Exception {
        // Initialize the database
        itemTypeRepository.saveAndFlush(itemType);

        int databaseSizeBeforeDelete = itemTypeRepository.findAll().size();

        // Delete the itemType
        restItemTypeMockMvc
            .perform(delete(ENTITY_API_URL_ID, itemType.getItemTypeId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ItemType> itemTypeList = itemTypeRepository.findAll();
        assertThat(itemTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
