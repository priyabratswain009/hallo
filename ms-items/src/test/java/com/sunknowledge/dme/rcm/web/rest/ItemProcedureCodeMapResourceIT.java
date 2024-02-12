package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ItemProcedureCodeMap;
import com.sunknowledge.dme.rcm.repository.ItemProcedureCodeMapRepository;
import com.sunknowledge.dme.rcm.service.dto.ItemProcedureCodeMapDTO;
import com.sunknowledge.dme.rcm.service.mapper.ItemProcedureCodeMapMapper;
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
 * Integration tests for the {@link ItemProcedureCodeMapResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ItemProcedureCodeMapResourceIT {

    private static final String DEFAULT_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_NO = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NO = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_UOM = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_UOM = "BBBBBBBBBB";

    private static final String DEFAULT_PROCEDURE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PROCEDURE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_MODIFIER_1 = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIER_1 = "BBBBBBBBBB";

    private static final String DEFAULT_MODIFIER_2 = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIER_2 = "BBBBBBBBBB";

    private static final String DEFAULT_MODIFIER_3 = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIER_3 = "BBBBBBBBBB";

    private static final String DEFAULT_MODIFIER_4 = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIER_4 = "BBBBBBBBBB";

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

    private static final String DEFAULT_UPDATED_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final UUID DEFAULT_ITEM_PROCEDURE_CODE_MAP_UUID = UUID.randomUUID();
    private static final UUID UPDATED_ITEM_PROCEDURE_CODE_MAP_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/item-procedure-code-maps";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{itemProcedureCodeMapId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ItemProcedureCodeMapRepository itemProcedureCodeMapRepository;

    @Autowired
    private ItemProcedureCodeMapMapper itemProcedureCodeMapMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restItemProcedureCodeMapMockMvc;

    private ItemProcedureCodeMap itemProcedureCodeMap;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemProcedureCodeMap createEntity(EntityManager em) {
        ItemProcedureCodeMap itemProcedureCodeMap = new ItemProcedureCodeMap()
            .itemName(DEFAULT_ITEM_NAME)
            .itemNo(DEFAULT_ITEM_NO)
            .itemDescription(DEFAULT_ITEM_DESCRIPTION)
            .itemUom(DEFAULT_ITEM_UOM)
            .procedureCode(DEFAULT_PROCEDURE_CODE)
            .modifier1(DEFAULT_MODIFIER_1)
            .modifier2(DEFAULT_MODIFIER_2)
            .modifier3(DEFAULT_MODIFIER_3)
            .modifier4(DEFAULT_MODIFIER_4)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedName(DEFAULT_UPDATED_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .itemProcedureCodeMapUuid(DEFAULT_ITEM_PROCEDURE_CODE_MAP_UUID);
        return itemProcedureCodeMap;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemProcedureCodeMap createUpdatedEntity(EntityManager em) {
        ItemProcedureCodeMap itemProcedureCodeMap = new ItemProcedureCodeMap()
            .itemName(UPDATED_ITEM_NAME)
            .itemNo(UPDATED_ITEM_NO)
            .itemDescription(UPDATED_ITEM_DESCRIPTION)
            .itemUom(UPDATED_ITEM_UOM)
            .procedureCode(UPDATED_PROCEDURE_CODE)
            .modifier1(UPDATED_MODIFIER_1)
            .modifier2(UPDATED_MODIFIER_2)
            .modifier3(UPDATED_MODIFIER_3)
            .modifier4(UPDATED_MODIFIER_4)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedName(UPDATED_UPDATED_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .itemProcedureCodeMapUuid(UPDATED_ITEM_PROCEDURE_CODE_MAP_UUID);
        return itemProcedureCodeMap;
    }

    @BeforeEach
    public void initTest() {
        itemProcedureCodeMap = createEntity(em);
    }

    @Test
    @Transactional
    void createItemProcedureCodeMap() throws Exception {
        int databaseSizeBeforeCreate = itemProcedureCodeMapRepository.findAll().size();
        // Create the ItemProcedureCodeMap
        ItemProcedureCodeMapDTO itemProcedureCodeMapDTO = itemProcedureCodeMapMapper.toDto(itemProcedureCodeMap);
        restItemProcedureCodeMapMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemProcedureCodeMapDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ItemProcedureCodeMap in the database
        List<ItemProcedureCodeMap> itemProcedureCodeMapList = itemProcedureCodeMapRepository.findAll();
        assertThat(itemProcedureCodeMapList).hasSize(databaseSizeBeforeCreate + 1);
        ItemProcedureCodeMap testItemProcedureCodeMap = itemProcedureCodeMapList.get(itemProcedureCodeMapList.size() - 1);
        assertThat(testItemProcedureCodeMap.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testItemProcedureCodeMap.getItemNo()).isEqualTo(DEFAULT_ITEM_NO);
        assertThat(testItemProcedureCodeMap.getItemDescription()).isEqualTo(DEFAULT_ITEM_DESCRIPTION);
        assertThat(testItemProcedureCodeMap.getItemUom()).isEqualTo(DEFAULT_ITEM_UOM);
        assertThat(testItemProcedureCodeMap.getProcedureCode()).isEqualTo(DEFAULT_PROCEDURE_CODE);
        assertThat(testItemProcedureCodeMap.getModifier1()).isEqualTo(DEFAULT_MODIFIER_1);
        assertThat(testItemProcedureCodeMap.getModifier2()).isEqualTo(DEFAULT_MODIFIER_2);
        assertThat(testItemProcedureCodeMap.getModifier3()).isEqualTo(DEFAULT_MODIFIER_3);
        assertThat(testItemProcedureCodeMap.getModifier4()).isEqualTo(DEFAULT_MODIFIER_4);
        assertThat(testItemProcedureCodeMap.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testItemProcedureCodeMap.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testItemProcedureCodeMap.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testItemProcedureCodeMap.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testItemProcedureCodeMap.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testItemProcedureCodeMap.getUpdatedName()).isEqualTo(DEFAULT_UPDATED_NAME);
        assertThat(testItemProcedureCodeMap.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testItemProcedureCodeMap.getItemProcedureCodeMapUuid()).isEqualTo(DEFAULT_ITEM_PROCEDURE_CODE_MAP_UUID);
    }

    @Test
    @Transactional
    void createItemProcedureCodeMapWithExistingId() throws Exception {
        // Create the ItemProcedureCodeMap with an existing ID
        itemProcedureCodeMap.setItemProcedureCodeMapId(1L);
        ItemProcedureCodeMapDTO itemProcedureCodeMapDTO = itemProcedureCodeMapMapper.toDto(itemProcedureCodeMap);

        int databaseSizeBeforeCreate = itemProcedureCodeMapRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemProcedureCodeMapMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemProcedureCodeMapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemProcedureCodeMap in the database
        List<ItemProcedureCodeMap> itemProcedureCodeMapList = itemProcedureCodeMapRepository.findAll();
        assertThat(itemProcedureCodeMapList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllItemProcedureCodeMaps() throws Exception {
        // Initialize the database
        itemProcedureCodeMapRepository.saveAndFlush(itemProcedureCodeMap);

        // Get all the itemProcedureCodeMapList
        restItemProcedureCodeMapMockMvc
            .perform(get(ENTITY_API_URL + "?sort=itemProcedureCodeMapId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].itemProcedureCodeMapId").value(hasItem(itemProcedureCodeMap.getItemProcedureCodeMapId().intValue())))
            .andExpect(jsonPath("$.[*].itemName").value(hasItem(DEFAULT_ITEM_NAME)))
            .andExpect(jsonPath("$.[*].itemNo").value(hasItem(DEFAULT_ITEM_NO)))
            .andExpect(jsonPath("$.[*].itemDescription").value(hasItem(DEFAULT_ITEM_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].itemUom").value(hasItem(DEFAULT_ITEM_UOM)))
            .andExpect(jsonPath("$.[*].procedureCode").value(hasItem(DEFAULT_PROCEDURE_CODE)))
            .andExpect(jsonPath("$.[*].modifier1").value(hasItem(DEFAULT_MODIFIER_1)))
            .andExpect(jsonPath("$.[*].modifier2").value(hasItem(DEFAULT_MODIFIER_2)))
            .andExpect(jsonPath("$.[*].modifier3").value(hasItem(DEFAULT_MODIFIER_3)))
            .andExpect(jsonPath("$.[*].modifier4").value(hasItem(DEFAULT_MODIFIER_4)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedName").value(hasItem(DEFAULT_UPDATED_NAME)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].itemProcedureCodeMapUuid").value(hasItem(DEFAULT_ITEM_PROCEDURE_CODE_MAP_UUID.toString())));
    }

    @Test
    @Transactional
    void getItemProcedureCodeMap() throws Exception {
        // Initialize the database
        itemProcedureCodeMapRepository.saveAndFlush(itemProcedureCodeMap);

        // Get the itemProcedureCodeMap
        restItemProcedureCodeMapMockMvc
            .perform(get(ENTITY_API_URL_ID, itemProcedureCodeMap.getItemProcedureCodeMapId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.itemProcedureCodeMapId").value(itemProcedureCodeMap.getItemProcedureCodeMapId().intValue()))
            .andExpect(jsonPath("$.itemName").value(DEFAULT_ITEM_NAME))
            .andExpect(jsonPath("$.itemNo").value(DEFAULT_ITEM_NO))
            .andExpect(jsonPath("$.itemDescription").value(DEFAULT_ITEM_DESCRIPTION))
            .andExpect(jsonPath("$.itemUom").value(DEFAULT_ITEM_UOM))
            .andExpect(jsonPath("$.procedureCode").value(DEFAULT_PROCEDURE_CODE))
            .andExpect(jsonPath("$.modifier1").value(DEFAULT_MODIFIER_1))
            .andExpect(jsonPath("$.modifier2").value(DEFAULT_MODIFIER_2))
            .andExpect(jsonPath("$.modifier3").value(DEFAULT_MODIFIER_3))
            .andExpect(jsonPath("$.modifier4").value(DEFAULT_MODIFIER_4))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedName").value(DEFAULT_UPDATED_NAME))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.itemProcedureCodeMapUuid").value(DEFAULT_ITEM_PROCEDURE_CODE_MAP_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingItemProcedureCodeMap() throws Exception {
        // Get the itemProcedureCodeMap
        restItemProcedureCodeMapMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewItemProcedureCodeMap() throws Exception {
        // Initialize the database
        itemProcedureCodeMapRepository.saveAndFlush(itemProcedureCodeMap);

        int databaseSizeBeforeUpdate = itemProcedureCodeMapRepository.findAll().size();

        // Update the itemProcedureCodeMap
        ItemProcedureCodeMap updatedItemProcedureCodeMap = itemProcedureCodeMapRepository
            .findById(itemProcedureCodeMap.getItemProcedureCodeMapId())
            .get();
        // Disconnect from session so that the updates on updatedItemProcedureCodeMap are not directly saved in db
        em.detach(updatedItemProcedureCodeMap);
        updatedItemProcedureCodeMap
            .itemName(UPDATED_ITEM_NAME)
            .itemNo(UPDATED_ITEM_NO)
            .itemDescription(UPDATED_ITEM_DESCRIPTION)
            .itemUom(UPDATED_ITEM_UOM)
            .procedureCode(UPDATED_PROCEDURE_CODE)
            .modifier1(UPDATED_MODIFIER_1)
            .modifier2(UPDATED_MODIFIER_2)
            .modifier3(UPDATED_MODIFIER_3)
            .modifier4(UPDATED_MODIFIER_4)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedName(UPDATED_UPDATED_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .itemProcedureCodeMapUuid(UPDATED_ITEM_PROCEDURE_CODE_MAP_UUID);
        ItemProcedureCodeMapDTO itemProcedureCodeMapDTO = itemProcedureCodeMapMapper.toDto(updatedItemProcedureCodeMap);

        restItemProcedureCodeMapMockMvc
            .perform(
                put(ENTITY_API_URL_ID, itemProcedureCodeMapDTO.getItemProcedureCodeMapId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemProcedureCodeMapDTO))
            )
            .andExpect(status().isOk());

        // Validate the ItemProcedureCodeMap in the database
        List<ItemProcedureCodeMap> itemProcedureCodeMapList = itemProcedureCodeMapRepository.findAll();
        assertThat(itemProcedureCodeMapList).hasSize(databaseSizeBeforeUpdate);
        ItemProcedureCodeMap testItemProcedureCodeMap = itemProcedureCodeMapList.get(itemProcedureCodeMapList.size() - 1);
        assertThat(testItemProcedureCodeMap.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testItemProcedureCodeMap.getItemNo()).isEqualTo(UPDATED_ITEM_NO);
        assertThat(testItemProcedureCodeMap.getItemDescription()).isEqualTo(UPDATED_ITEM_DESCRIPTION);
        assertThat(testItemProcedureCodeMap.getItemUom()).isEqualTo(UPDATED_ITEM_UOM);
        assertThat(testItemProcedureCodeMap.getProcedureCode()).isEqualTo(UPDATED_PROCEDURE_CODE);
        assertThat(testItemProcedureCodeMap.getModifier1()).isEqualTo(UPDATED_MODIFIER_1);
        assertThat(testItemProcedureCodeMap.getModifier2()).isEqualTo(UPDATED_MODIFIER_2);
        assertThat(testItemProcedureCodeMap.getModifier3()).isEqualTo(UPDATED_MODIFIER_3);
        assertThat(testItemProcedureCodeMap.getModifier4()).isEqualTo(UPDATED_MODIFIER_4);
        assertThat(testItemProcedureCodeMap.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testItemProcedureCodeMap.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testItemProcedureCodeMap.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testItemProcedureCodeMap.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testItemProcedureCodeMap.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testItemProcedureCodeMap.getUpdatedName()).isEqualTo(UPDATED_UPDATED_NAME);
        assertThat(testItemProcedureCodeMap.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testItemProcedureCodeMap.getItemProcedureCodeMapUuid()).isEqualTo(UPDATED_ITEM_PROCEDURE_CODE_MAP_UUID);
    }

    @Test
    @Transactional
    void putNonExistingItemProcedureCodeMap() throws Exception {
        int databaseSizeBeforeUpdate = itemProcedureCodeMapRepository.findAll().size();
        itemProcedureCodeMap.setItemProcedureCodeMapId(count.incrementAndGet());

        // Create the ItemProcedureCodeMap
        ItemProcedureCodeMapDTO itemProcedureCodeMapDTO = itemProcedureCodeMapMapper.toDto(itemProcedureCodeMap);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemProcedureCodeMapMockMvc
            .perform(
                put(ENTITY_API_URL_ID, itemProcedureCodeMapDTO.getItemProcedureCodeMapId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemProcedureCodeMapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemProcedureCodeMap in the database
        List<ItemProcedureCodeMap> itemProcedureCodeMapList = itemProcedureCodeMapRepository.findAll();
        assertThat(itemProcedureCodeMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchItemProcedureCodeMap() throws Exception {
        int databaseSizeBeforeUpdate = itemProcedureCodeMapRepository.findAll().size();
        itemProcedureCodeMap.setItemProcedureCodeMapId(count.incrementAndGet());

        // Create the ItemProcedureCodeMap
        ItemProcedureCodeMapDTO itemProcedureCodeMapDTO = itemProcedureCodeMapMapper.toDto(itemProcedureCodeMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemProcedureCodeMapMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemProcedureCodeMapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemProcedureCodeMap in the database
        List<ItemProcedureCodeMap> itemProcedureCodeMapList = itemProcedureCodeMapRepository.findAll();
        assertThat(itemProcedureCodeMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamItemProcedureCodeMap() throws Exception {
        int databaseSizeBeforeUpdate = itemProcedureCodeMapRepository.findAll().size();
        itemProcedureCodeMap.setItemProcedureCodeMapId(count.incrementAndGet());

        // Create the ItemProcedureCodeMap
        ItemProcedureCodeMapDTO itemProcedureCodeMapDTO = itemProcedureCodeMapMapper.toDto(itemProcedureCodeMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemProcedureCodeMapMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemProcedureCodeMapDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ItemProcedureCodeMap in the database
        List<ItemProcedureCodeMap> itemProcedureCodeMapList = itemProcedureCodeMapRepository.findAll();
        assertThat(itemProcedureCodeMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateItemProcedureCodeMapWithPatch() throws Exception {
        // Initialize the database
        itemProcedureCodeMapRepository.saveAndFlush(itemProcedureCodeMap);

        int databaseSizeBeforeUpdate = itemProcedureCodeMapRepository.findAll().size();

        // Update the itemProcedureCodeMap using partial update
        ItemProcedureCodeMap partialUpdatedItemProcedureCodeMap = new ItemProcedureCodeMap();
        partialUpdatedItemProcedureCodeMap.setItemProcedureCodeMapId(itemProcedureCodeMap.getItemProcedureCodeMapId());

        partialUpdatedItemProcedureCodeMap
            .itemName(UPDATED_ITEM_NAME)
            .itemNo(UPDATED_ITEM_NO)
            .modifier1(UPDATED_MODIFIER_1)
            .modifier2(UPDATED_MODIFIER_2)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedName(UPDATED_UPDATED_NAME)
            .itemProcedureCodeMapUuid(UPDATED_ITEM_PROCEDURE_CODE_MAP_UUID);

        restItemProcedureCodeMapMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedItemProcedureCodeMap.getItemProcedureCodeMapId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedItemProcedureCodeMap))
            )
            .andExpect(status().isOk());

        // Validate the ItemProcedureCodeMap in the database
        List<ItemProcedureCodeMap> itemProcedureCodeMapList = itemProcedureCodeMapRepository.findAll();
        assertThat(itemProcedureCodeMapList).hasSize(databaseSizeBeforeUpdate);
        ItemProcedureCodeMap testItemProcedureCodeMap = itemProcedureCodeMapList.get(itemProcedureCodeMapList.size() - 1);
        assertThat(testItemProcedureCodeMap.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testItemProcedureCodeMap.getItemNo()).isEqualTo(UPDATED_ITEM_NO);
        assertThat(testItemProcedureCodeMap.getItemDescription()).isEqualTo(DEFAULT_ITEM_DESCRIPTION);
        assertThat(testItemProcedureCodeMap.getItemUom()).isEqualTo(DEFAULT_ITEM_UOM);
        assertThat(testItemProcedureCodeMap.getProcedureCode()).isEqualTo(DEFAULT_PROCEDURE_CODE);
        assertThat(testItemProcedureCodeMap.getModifier1()).isEqualTo(UPDATED_MODIFIER_1);
        assertThat(testItemProcedureCodeMap.getModifier2()).isEqualTo(UPDATED_MODIFIER_2);
        assertThat(testItemProcedureCodeMap.getModifier3()).isEqualTo(DEFAULT_MODIFIER_3);
        assertThat(testItemProcedureCodeMap.getModifier4()).isEqualTo(DEFAULT_MODIFIER_4);
        assertThat(testItemProcedureCodeMap.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testItemProcedureCodeMap.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testItemProcedureCodeMap.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testItemProcedureCodeMap.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testItemProcedureCodeMap.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testItemProcedureCodeMap.getUpdatedName()).isEqualTo(UPDATED_UPDATED_NAME);
        assertThat(testItemProcedureCodeMap.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testItemProcedureCodeMap.getItemProcedureCodeMapUuid()).isEqualTo(UPDATED_ITEM_PROCEDURE_CODE_MAP_UUID);
    }

    @Test
    @Transactional
    void fullUpdateItemProcedureCodeMapWithPatch() throws Exception {
        // Initialize the database
        itemProcedureCodeMapRepository.saveAndFlush(itemProcedureCodeMap);

        int databaseSizeBeforeUpdate = itemProcedureCodeMapRepository.findAll().size();

        // Update the itemProcedureCodeMap using partial update
        ItemProcedureCodeMap partialUpdatedItemProcedureCodeMap = new ItemProcedureCodeMap();
        partialUpdatedItemProcedureCodeMap.setItemProcedureCodeMapId(itemProcedureCodeMap.getItemProcedureCodeMapId());

        partialUpdatedItemProcedureCodeMap
            .itemName(UPDATED_ITEM_NAME)
            .itemNo(UPDATED_ITEM_NO)
            .itemDescription(UPDATED_ITEM_DESCRIPTION)
            .itemUom(UPDATED_ITEM_UOM)
            .procedureCode(UPDATED_PROCEDURE_CODE)
            .modifier1(UPDATED_MODIFIER_1)
            .modifier2(UPDATED_MODIFIER_2)
            .modifier3(UPDATED_MODIFIER_3)
            .modifier4(UPDATED_MODIFIER_4)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedName(UPDATED_UPDATED_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .itemProcedureCodeMapUuid(UPDATED_ITEM_PROCEDURE_CODE_MAP_UUID);

        restItemProcedureCodeMapMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedItemProcedureCodeMap.getItemProcedureCodeMapId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedItemProcedureCodeMap))
            )
            .andExpect(status().isOk());

        // Validate the ItemProcedureCodeMap in the database
        List<ItemProcedureCodeMap> itemProcedureCodeMapList = itemProcedureCodeMapRepository.findAll();
        assertThat(itemProcedureCodeMapList).hasSize(databaseSizeBeforeUpdate);
        ItemProcedureCodeMap testItemProcedureCodeMap = itemProcedureCodeMapList.get(itemProcedureCodeMapList.size() - 1);
        assertThat(testItemProcedureCodeMap.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testItemProcedureCodeMap.getItemNo()).isEqualTo(UPDATED_ITEM_NO);
        assertThat(testItemProcedureCodeMap.getItemDescription()).isEqualTo(UPDATED_ITEM_DESCRIPTION);
        assertThat(testItemProcedureCodeMap.getItemUom()).isEqualTo(UPDATED_ITEM_UOM);
        assertThat(testItemProcedureCodeMap.getProcedureCode()).isEqualTo(UPDATED_PROCEDURE_CODE);
        assertThat(testItemProcedureCodeMap.getModifier1()).isEqualTo(UPDATED_MODIFIER_1);
        assertThat(testItemProcedureCodeMap.getModifier2()).isEqualTo(UPDATED_MODIFIER_2);
        assertThat(testItemProcedureCodeMap.getModifier3()).isEqualTo(UPDATED_MODIFIER_3);
        assertThat(testItemProcedureCodeMap.getModifier4()).isEqualTo(UPDATED_MODIFIER_4);
        assertThat(testItemProcedureCodeMap.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testItemProcedureCodeMap.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testItemProcedureCodeMap.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testItemProcedureCodeMap.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testItemProcedureCodeMap.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testItemProcedureCodeMap.getUpdatedName()).isEqualTo(UPDATED_UPDATED_NAME);
        assertThat(testItemProcedureCodeMap.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testItemProcedureCodeMap.getItemProcedureCodeMapUuid()).isEqualTo(UPDATED_ITEM_PROCEDURE_CODE_MAP_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingItemProcedureCodeMap() throws Exception {
        int databaseSizeBeforeUpdate = itemProcedureCodeMapRepository.findAll().size();
        itemProcedureCodeMap.setItemProcedureCodeMapId(count.incrementAndGet());

        // Create the ItemProcedureCodeMap
        ItemProcedureCodeMapDTO itemProcedureCodeMapDTO = itemProcedureCodeMapMapper.toDto(itemProcedureCodeMap);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemProcedureCodeMapMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, itemProcedureCodeMapDTO.getItemProcedureCodeMapId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemProcedureCodeMapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemProcedureCodeMap in the database
        List<ItemProcedureCodeMap> itemProcedureCodeMapList = itemProcedureCodeMapRepository.findAll();
        assertThat(itemProcedureCodeMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchItemProcedureCodeMap() throws Exception {
        int databaseSizeBeforeUpdate = itemProcedureCodeMapRepository.findAll().size();
        itemProcedureCodeMap.setItemProcedureCodeMapId(count.incrementAndGet());

        // Create the ItemProcedureCodeMap
        ItemProcedureCodeMapDTO itemProcedureCodeMapDTO = itemProcedureCodeMapMapper.toDto(itemProcedureCodeMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemProcedureCodeMapMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemProcedureCodeMapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemProcedureCodeMap in the database
        List<ItemProcedureCodeMap> itemProcedureCodeMapList = itemProcedureCodeMapRepository.findAll();
        assertThat(itemProcedureCodeMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamItemProcedureCodeMap() throws Exception {
        int databaseSizeBeforeUpdate = itemProcedureCodeMapRepository.findAll().size();
        itemProcedureCodeMap.setItemProcedureCodeMapId(count.incrementAndGet());

        // Create the ItemProcedureCodeMap
        ItemProcedureCodeMapDTO itemProcedureCodeMapDTO = itemProcedureCodeMapMapper.toDto(itemProcedureCodeMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemProcedureCodeMapMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemProcedureCodeMapDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ItemProcedureCodeMap in the database
        List<ItemProcedureCodeMap> itemProcedureCodeMapList = itemProcedureCodeMapRepository.findAll();
        assertThat(itemProcedureCodeMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteItemProcedureCodeMap() throws Exception {
        // Initialize the database
        itemProcedureCodeMapRepository.saveAndFlush(itemProcedureCodeMap);

        int databaseSizeBeforeDelete = itemProcedureCodeMapRepository.findAll().size();

        // Delete the itemProcedureCodeMap
        restItemProcedureCodeMapMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, itemProcedureCodeMap.getItemProcedureCodeMapId()).with(csrf()).accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ItemProcedureCodeMap> itemProcedureCodeMapList = itemProcedureCodeMapRepository.findAll();
        assertThat(itemProcedureCodeMapList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
