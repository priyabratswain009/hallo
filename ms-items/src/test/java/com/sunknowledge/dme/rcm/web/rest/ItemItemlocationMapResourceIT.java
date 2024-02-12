package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ItemItemlocationMap;
import com.sunknowledge.dme.rcm.repository.ItemItemlocationMapRepository;
import com.sunknowledge.dme.rcm.service.dto.ItemItemlocationMapDTO;
import com.sunknowledge.dme.rcm.service.mapper.ItemItemlocationMapMapper;
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
 * Integration tests for the {@link ItemItemlocationMapResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ItemItemlocationMapResourceIT {

    private static final Long DEFAULT_ITEM_ID = 1L;
    private static final Long UPDATED_ITEM_ID = 2L;

    private static final String DEFAULT_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_ITEM_LOCATION_ID = 1L;
    private static final Long UPDATED_ITEM_LOCATION_ID = 2L;

    private static final String DEFAULT_ITEM_LOCATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_LOCATION_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_NO = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NO = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_UOM = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_UOM = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_ITEM_ITEMLOCATION_MAP_UUID = UUID.randomUUID();
    private static final UUID UPDATED_ITEM_ITEMLOCATION_MAP_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/item-itemlocation-maps";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{itemItemlocationMapId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ItemItemlocationMapRepository itemItemlocationMapRepository;

    @Autowired
    private ItemItemlocationMapMapper itemItemlocationMapMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restItemItemlocationMapMockMvc;

    private ItemItemlocationMap itemItemlocationMap;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemItemlocationMap createEntity(EntityManager em) {
        ItemItemlocationMap itemItemlocationMap = new ItemItemlocationMap()
            .itemId(DEFAULT_ITEM_ID)
            .itemName(DEFAULT_ITEM_NAME)
            .itemLocationId(DEFAULT_ITEM_LOCATION_ID)
            .itemLocationName(DEFAULT_ITEM_LOCATION_NAME)
            .itemNo(DEFAULT_ITEM_NO)
            .itemUom(DEFAULT_ITEM_UOM)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .itemItemlocationMapUuid(DEFAULT_ITEM_ITEMLOCATION_MAP_UUID);
        return itemItemlocationMap;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemItemlocationMap createUpdatedEntity(EntityManager em) {
        ItemItemlocationMap itemItemlocationMap = new ItemItemlocationMap()
            .itemId(UPDATED_ITEM_ID)
            .itemName(UPDATED_ITEM_NAME)
            .itemLocationId(UPDATED_ITEM_LOCATION_ID)
            .itemLocationName(UPDATED_ITEM_LOCATION_NAME)
            .itemNo(UPDATED_ITEM_NO)
            .itemUom(UPDATED_ITEM_UOM)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .itemItemlocationMapUuid(UPDATED_ITEM_ITEMLOCATION_MAP_UUID);
        return itemItemlocationMap;
    }

    @BeforeEach
    public void initTest() {
        itemItemlocationMap = createEntity(em);
    }

    @Test
    @Transactional
    void createItemItemlocationMap() throws Exception {
        int databaseSizeBeforeCreate = itemItemlocationMapRepository.findAll().size();
        // Create the ItemItemlocationMap
        ItemItemlocationMapDTO itemItemlocationMapDTO = itemItemlocationMapMapper.toDto(itemItemlocationMap);
        restItemItemlocationMapMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemItemlocationMapDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ItemItemlocationMap in the database
        List<ItemItemlocationMap> itemItemlocationMapList = itemItemlocationMapRepository.findAll();
        assertThat(itemItemlocationMapList).hasSize(databaseSizeBeforeCreate + 1);
        ItemItemlocationMap testItemItemlocationMap = itemItemlocationMapList.get(itemItemlocationMapList.size() - 1);
        assertThat(testItemItemlocationMap.getItemId()).isEqualTo(DEFAULT_ITEM_ID);
        assertThat(testItemItemlocationMap.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testItemItemlocationMap.getItemLocationId()).isEqualTo(DEFAULT_ITEM_LOCATION_ID);
        assertThat(testItemItemlocationMap.getItemLocationName()).isEqualTo(DEFAULT_ITEM_LOCATION_NAME);
        assertThat(testItemItemlocationMap.getItemNo()).isEqualTo(DEFAULT_ITEM_NO);
        assertThat(testItemItemlocationMap.getItemUom()).isEqualTo(DEFAULT_ITEM_UOM);
        assertThat(testItemItemlocationMap.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testItemItemlocationMap.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testItemItemlocationMap.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testItemItemlocationMap.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testItemItemlocationMap.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testItemItemlocationMap.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testItemItemlocationMap.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testItemItemlocationMap.getItemItemlocationMapUuid()).isEqualTo(DEFAULT_ITEM_ITEMLOCATION_MAP_UUID);
    }

    @Test
    @Transactional
    void createItemItemlocationMapWithExistingId() throws Exception {
        // Create the ItemItemlocationMap with an existing ID
        itemItemlocationMap.setItemItemlocationMapId(1L);
        ItemItemlocationMapDTO itemItemlocationMapDTO = itemItemlocationMapMapper.toDto(itemItemlocationMap);

        int databaseSizeBeforeCreate = itemItemlocationMapRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemItemlocationMapMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemItemlocationMapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemItemlocationMap in the database
        List<ItemItemlocationMap> itemItemlocationMapList = itemItemlocationMapRepository.findAll();
        assertThat(itemItemlocationMapList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllItemItemlocationMaps() throws Exception {
        // Initialize the database
        itemItemlocationMapRepository.saveAndFlush(itemItemlocationMap);

        // Get all the itemItemlocationMapList
        restItemItemlocationMapMockMvc
            .perform(get(ENTITY_API_URL + "?sort=itemItemlocationMapId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].itemItemlocationMapId").value(hasItem(itemItemlocationMap.getItemItemlocationMapId().intValue())))
            .andExpect(jsonPath("$.[*].itemId").value(hasItem(DEFAULT_ITEM_ID.intValue())))
            .andExpect(jsonPath("$.[*].itemName").value(hasItem(DEFAULT_ITEM_NAME)))
            .andExpect(jsonPath("$.[*].itemLocationId").value(hasItem(DEFAULT_ITEM_LOCATION_ID.intValue())))
            .andExpect(jsonPath("$.[*].itemLocationName").value(hasItem(DEFAULT_ITEM_LOCATION_NAME)))
            .andExpect(jsonPath("$.[*].itemNo").value(hasItem(DEFAULT_ITEM_NO)))
            .andExpect(jsonPath("$.[*].itemUom").value(hasItem(DEFAULT_ITEM_UOM)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].itemItemlocationMapUuid").value(hasItem(DEFAULT_ITEM_ITEMLOCATION_MAP_UUID.toString())));
    }

    @Test
    @Transactional
    void getItemItemlocationMap() throws Exception {
        // Initialize the database
        itemItemlocationMapRepository.saveAndFlush(itemItemlocationMap);

        // Get the itemItemlocationMap
        restItemItemlocationMapMockMvc
            .perform(get(ENTITY_API_URL_ID, itemItemlocationMap.getItemItemlocationMapId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.itemItemlocationMapId").value(itemItemlocationMap.getItemItemlocationMapId().intValue()))
            .andExpect(jsonPath("$.itemId").value(DEFAULT_ITEM_ID.intValue()))
            .andExpect(jsonPath("$.itemName").value(DEFAULT_ITEM_NAME))
            .andExpect(jsonPath("$.itemLocationId").value(DEFAULT_ITEM_LOCATION_ID.intValue()))
            .andExpect(jsonPath("$.itemLocationName").value(DEFAULT_ITEM_LOCATION_NAME))
            .andExpect(jsonPath("$.itemNo").value(DEFAULT_ITEM_NO))
            .andExpect(jsonPath("$.itemUom").value(DEFAULT_ITEM_UOM))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.itemItemlocationMapUuid").value(DEFAULT_ITEM_ITEMLOCATION_MAP_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingItemItemlocationMap() throws Exception {
        // Get the itemItemlocationMap
        restItemItemlocationMapMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewItemItemlocationMap() throws Exception {
        // Initialize the database
        itemItemlocationMapRepository.saveAndFlush(itemItemlocationMap);

        int databaseSizeBeforeUpdate = itemItemlocationMapRepository.findAll().size();

        // Update the itemItemlocationMap
        ItemItemlocationMap updatedItemItemlocationMap = itemItemlocationMapRepository
            .findById(itemItemlocationMap.getItemItemlocationMapId())
            .get();
        // Disconnect from session so that the updates on updatedItemItemlocationMap are not directly saved in db
        em.detach(updatedItemItemlocationMap);
        updatedItemItemlocationMap
            .itemId(UPDATED_ITEM_ID)
            .itemName(UPDATED_ITEM_NAME)
            .itemLocationId(UPDATED_ITEM_LOCATION_ID)
            .itemLocationName(UPDATED_ITEM_LOCATION_NAME)
            .itemNo(UPDATED_ITEM_NO)
            .itemUom(UPDATED_ITEM_UOM)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .itemItemlocationMapUuid(UPDATED_ITEM_ITEMLOCATION_MAP_UUID);
        ItemItemlocationMapDTO itemItemlocationMapDTO = itemItemlocationMapMapper.toDto(updatedItemItemlocationMap);

        restItemItemlocationMapMockMvc
            .perform(
                put(ENTITY_API_URL_ID, itemItemlocationMapDTO.getItemItemlocationMapId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemItemlocationMapDTO))
            )
            .andExpect(status().isOk());

        // Validate the ItemItemlocationMap in the database
        List<ItemItemlocationMap> itemItemlocationMapList = itemItemlocationMapRepository.findAll();
        assertThat(itemItemlocationMapList).hasSize(databaseSizeBeforeUpdate);
        ItemItemlocationMap testItemItemlocationMap = itemItemlocationMapList.get(itemItemlocationMapList.size() - 1);
        assertThat(testItemItemlocationMap.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testItemItemlocationMap.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testItemItemlocationMap.getItemLocationId()).isEqualTo(UPDATED_ITEM_LOCATION_ID);
        assertThat(testItemItemlocationMap.getItemLocationName()).isEqualTo(UPDATED_ITEM_LOCATION_NAME);
        assertThat(testItemItemlocationMap.getItemNo()).isEqualTo(UPDATED_ITEM_NO);
        assertThat(testItemItemlocationMap.getItemUom()).isEqualTo(UPDATED_ITEM_UOM);
        assertThat(testItemItemlocationMap.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testItemItemlocationMap.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testItemItemlocationMap.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testItemItemlocationMap.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testItemItemlocationMap.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testItemItemlocationMap.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testItemItemlocationMap.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testItemItemlocationMap.getItemItemlocationMapUuid()).isEqualTo(UPDATED_ITEM_ITEMLOCATION_MAP_UUID);
    }

    @Test
    @Transactional
    void putNonExistingItemItemlocationMap() throws Exception {
        int databaseSizeBeforeUpdate = itemItemlocationMapRepository.findAll().size();
        itemItemlocationMap.setItemItemlocationMapId(count.incrementAndGet());

        // Create the ItemItemlocationMap
        ItemItemlocationMapDTO itemItemlocationMapDTO = itemItemlocationMapMapper.toDto(itemItemlocationMap);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemItemlocationMapMockMvc
            .perform(
                put(ENTITY_API_URL_ID, itemItemlocationMapDTO.getItemItemlocationMapId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemItemlocationMapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemItemlocationMap in the database
        List<ItemItemlocationMap> itemItemlocationMapList = itemItemlocationMapRepository.findAll();
        assertThat(itemItemlocationMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchItemItemlocationMap() throws Exception {
        int databaseSizeBeforeUpdate = itemItemlocationMapRepository.findAll().size();
        itemItemlocationMap.setItemItemlocationMapId(count.incrementAndGet());

        // Create the ItemItemlocationMap
        ItemItemlocationMapDTO itemItemlocationMapDTO = itemItemlocationMapMapper.toDto(itemItemlocationMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemItemlocationMapMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemItemlocationMapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemItemlocationMap in the database
        List<ItemItemlocationMap> itemItemlocationMapList = itemItemlocationMapRepository.findAll();
        assertThat(itemItemlocationMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamItemItemlocationMap() throws Exception {
        int databaseSizeBeforeUpdate = itemItemlocationMapRepository.findAll().size();
        itemItemlocationMap.setItemItemlocationMapId(count.incrementAndGet());

        // Create the ItemItemlocationMap
        ItemItemlocationMapDTO itemItemlocationMapDTO = itemItemlocationMapMapper.toDto(itemItemlocationMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemItemlocationMapMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemItemlocationMapDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ItemItemlocationMap in the database
        List<ItemItemlocationMap> itemItemlocationMapList = itemItemlocationMapRepository.findAll();
        assertThat(itemItemlocationMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateItemItemlocationMapWithPatch() throws Exception {
        // Initialize the database
        itemItemlocationMapRepository.saveAndFlush(itemItemlocationMap);

        int databaseSizeBeforeUpdate = itemItemlocationMapRepository.findAll().size();

        // Update the itemItemlocationMap using partial update
        ItemItemlocationMap partialUpdatedItemItemlocationMap = new ItemItemlocationMap();
        partialUpdatedItemItemlocationMap.setItemItemlocationMapId(itemItemlocationMap.getItemItemlocationMapId());

        partialUpdatedItemItemlocationMap
            .itemId(UPDATED_ITEM_ID)
            .itemNo(UPDATED_ITEM_NO)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .itemItemlocationMapUuid(UPDATED_ITEM_ITEMLOCATION_MAP_UUID);

        restItemItemlocationMapMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedItemItemlocationMap.getItemItemlocationMapId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedItemItemlocationMap))
            )
            .andExpect(status().isOk());

        // Validate the ItemItemlocationMap in the database
        List<ItemItemlocationMap> itemItemlocationMapList = itemItemlocationMapRepository.findAll();
        assertThat(itemItemlocationMapList).hasSize(databaseSizeBeforeUpdate);
        ItemItemlocationMap testItemItemlocationMap = itemItemlocationMapList.get(itemItemlocationMapList.size() - 1);
        assertThat(testItemItemlocationMap.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testItemItemlocationMap.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testItemItemlocationMap.getItemLocationId()).isEqualTo(DEFAULT_ITEM_LOCATION_ID);
        assertThat(testItemItemlocationMap.getItemLocationName()).isEqualTo(DEFAULT_ITEM_LOCATION_NAME);
        assertThat(testItemItemlocationMap.getItemNo()).isEqualTo(UPDATED_ITEM_NO);
        assertThat(testItemItemlocationMap.getItemUom()).isEqualTo(DEFAULT_ITEM_UOM);
        assertThat(testItemItemlocationMap.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testItemItemlocationMap.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testItemItemlocationMap.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testItemItemlocationMap.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testItemItemlocationMap.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testItemItemlocationMap.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testItemItemlocationMap.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testItemItemlocationMap.getItemItemlocationMapUuid()).isEqualTo(UPDATED_ITEM_ITEMLOCATION_MAP_UUID);
    }

    @Test
    @Transactional
    void fullUpdateItemItemlocationMapWithPatch() throws Exception {
        // Initialize the database
        itemItemlocationMapRepository.saveAndFlush(itemItemlocationMap);

        int databaseSizeBeforeUpdate = itemItemlocationMapRepository.findAll().size();

        // Update the itemItemlocationMap using partial update
        ItemItemlocationMap partialUpdatedItemItemlocationMap = new ItemItemlocationMap();
        partialUpdatedItemItemlocationMap.setItemItemlocationMapId(itemItemlocationMap.getItemItemlocationMapId());

        partialUpdatedItemItemlocationMap
            .itemId(UPDATED_ITEM_ID)
            .itemName(UPDATED_ITEM_NAME)
            .itemLocationId(UPDATED_ITEM_LOCATION_ID)
            .itemLocationName(UPDATED_ITEM_LOCATION_NAME)
            .itemNo(UPDATED_ITEM_NO)
            .itemUom(UPDATED_ITEM_UOM)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .itemItemlocationMapUuid(UPDATED_ITEM_ITEMLOCATION_MAP_UUID);

        restItemItemlocationMapMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedItemItemlocationMap.getItemItemlocationMapId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedItemItemlocationMap))
            )
            .andExpect(status().isOk());

        // Validate the ItemItemlocationMap in the database
        List<ItemItemlocationMap> itemItemlocationMapList = itemItemlocationMapRepository.findAll();
        assertThat(itemItemlocationMapList).hasSize(databaseSizeBeforeUpdate);
        ItemItemlocationMap testItemItemlocationMap = itemItemlocationMapList.get(itemItemlocationMapList.size() - 1);
        assertThat(testItemItemlocationMap.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testItemItemlocationMap.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testItemItemlocationMap.getItemLocationId()).isEqualTo(UPDATED_ITEM_LOCATION_ID);
        assertThat(testItemItemlocationMap.getItemLocationName()).isEqualTo(UPDATED_ITEM_LOCATION_NAME);
        assertThat(testItemItemlocationMap.getItemNo()).isEqualTo(UPDATED_ITEM_NO);
        assertThat(testItemItemlocationMap.getItemUom()).isEqualTo(UPDATED_ITEM_UOM);
        assertThat(testItemItemlocationMap.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testItemItemlocationMap.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testItemItemlocationMap.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testItemItemlocationMap.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testItemItemlocationMap.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testItemItemlocationMap.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testItemItemlocationMap.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testItemItemlocationMap.getItemItemlocationMapUuid()).isEqualTo(UPDATED_ITEM_ITEMLOCATION_MAP_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingItemItemlocationMap() throws Exception {
        int databaseSizeBeforeUpdate = itemItemlocationMapRepository.findAll().size();
        itemItemlocationMap.setItemItemlocationMapId(count.incrementAndGet());

        // Create the ItemItemlocationMap
        ItemItemlocationMapDTO itemItemlocationMapDTO = itemItemlocationMapMapper.toDto(itemItemlocationMap);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemItemlocationMapMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, itemItemlocationMapDTO.getItemItemlocationMapId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemItemlocationMapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemItemlocationMap in the database
        List<ItemItemlocationMap> itemItemlocationMapList = itemItemlocationMapRepository.findAll();
        assertThat(itemItemlocationMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchItemItemlocationMap() throws Exception {
        int databaseSizeBeforeUpdate = itemItemlocationMapRepository.findAll().size();
        itemItemlocationMap.setItemItemlocationMapId(count.incrementAndGet());

        // Create the ItemItemlocationMap
        ItemItemlocationMapDTO itemItemlocationMapDTO = itemItemlocationMapMapper.toDto(itemItemlocationMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemItemlocationMapMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemItemlocationMapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemItemlocationMap in the database
        List<ItemItemlocationMap> itemItemlocationMapList = itemItemlocationMapRepository.findAll();
        assertThat(itemItemlocationMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamItemItemlocationMap() throws Exception {
        int databaseSizeBeforeUpdate = itemItemlocationMapRepository.findAll().size();
        itemItemlocationMap.setItemItemlocationMapId(count.incrementAndGet());

        // Create the ItemItemlocationMap
        ItemItemlocationMapDTO itemItemlocationMapDTO = itemItemlocationMapMapper.toDto(itemItemlocationMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemItemlocationMapMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemItemlocationMapDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ItemItemlocationMap in the database
        List<ItemItemlocationMap> itemItemlocationMapList = itemItemlocationMapRepository.findAll();
        assertThat(itemItemlocationMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteItemItemlocationMap() throws Exception {
        // Initialize the database
        itemItemlocationMapRepository.saveAndFlush(itemItemlocationMap);

        int databaseSizeBeforeDelete = itemItemlocationMapRepository.findAll().size();

        // Delete the itemItemlocationMap
        restItemItemlocationMapMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, itemItemlocationMap.getItemItemlocationMapId()).with(csrf()).accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ItemItemlocationMap> itemItemlocationMapList = itemItemlocationMapRepository.findAll();
        assertThat(itemItemlocationMapList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
