package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ItemInventoryStatus;
import com.sunknowledge.dme.rcm.repository.ItemInventoryStatusRepository;
import com.sunknowledge.dme.rcm.service.dto.ItemInventoryStatusDTO;
import com.sunknowledge.dme.rcm.service.mapper.ItemInventoryStatusMapper;
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
 * Integration tests for the {@link ItemInventoryStatusResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ItemInventoryStatusResourceIT {

    private static final Long DEFAULT_ITEM_ID = 1L;
    private static final Long UPDATED_ITEM_ID = 2L;

    private static final Long DEFAULT_ITEM_LOCATION_ID = 1L;
    private static final Long UPDATED_ITEM_LOCATION_ID = 2L;

    private static final Long DEFAULT_ONHAND_QTY = 1L;
    private static final Long UPDATED_ONHAND_QTY = 2L;

    private static final Long DEFAULT_ONRENT_QTY = 1L;
    private static final Long UPDATED_ONRENT_QTY = 2L;

    private static final Long DEFAULT_COMITTED_QTY = 1L;
    private static final Long UPDATED_COMITTED_QTY = 2L;

    private static final Long DEFAULT_INORDER_QTY = 1L;
    private static final Long UPDATED_INORDER_QTY = 2L;

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

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final UUID DEFAULT_ITEM_INVENTORY_STATUS_UUID = UUID.randomUUID();
    private static final UUID UPDATED_ITEM_INVENTORY_STATUS_UUID = UUID.randomUUID();

    private static final String DEFAULT_ITEM_NO = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NO = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_LOCATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_LOCATION_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BIN_NO = "AAAAAAAAAA";
    private static final String UPDATED_BIN_NO = "BBBBBBBBBB";

    private static final String DEFAULT_WHETHER_SERIALISED = "AAAAAAAAAA";
    private static final String UPDATED_WHETHER_SERIALISED = "BBBBBBBBBB";

    private static final String DEFAULT_WHETHER_ASSET_TAGGED = "AAAAAAAAAA";
    private static final String UPDATED_WHETHER_ASSET_TAGGED = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_ON_BACKORDER = 1;
    private static final Integer UPDATED_ON_BACKORDER = 2;

    private static final String ENTITY_API_URL = "/api/item-inventory-statuses";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{itemInventoryStatusId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ItemInventoryStatusRepository itemInventoryStatusRepository;

    @Autowired
    private ItemInventoryStatusMapper itemInventoryStatusMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restItemInventoryStatusMockMvc;

    private ItemInventoryStatus itemInventoryStatus;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemInventoryStatus createEntity(EntityManager em) {
        ItemInventoryStatus itemInventoryStatus = new ItemInventoryStatus()
            .itemId(DEFAULT_ITEM_ID)
            .itemLocationId(DEFAULT_ITEM_LOCATION_ID)
            .onhandQty(DEFAULT_ONHAND_QTY)
            .onrentQty(DEFAULT_ONRENT_QTY)
            .comittedQty(DEFAULT_COMITTED_QTY)
            .inorderQty(DEFAULT_INORDER_QTY)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .itemInventoryStatusUuid(DEFAULT_ITEM_INVENTORY_STATUS_UUID)
            .itemNo(DEFAULT_ITEM_NO)
            .itemName(DEFAULT_ITEM_NAME)
            .itemDescription(DEFAULT_ITEM_DESCRIPTION)
            .itemLocationName(DEFAULT_ITEM_LOCATION_NAME)
            .binNo(DEFAULT_BIN_NO)
            .whetherSerialised(DEFAULT_WHETHER_SERIALISED)
            .whetherAssetTagged(DEFAULT_WHETHER_ASSET_TAGGED)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .onBackorder(DEFAULT_ON_BACKORDER);
        return itemInventoryStatus;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemInventoryStatus createUpdatedEntity(EntityManager em) {
        ItemInventoryStatus itemInventoryStatus = new ItemInventoryStatus()
            .itemId(UPDATED_ITEM_ID)
            .itemLocationId(UPDATED_ITEM_LOCATION_ID)
            .onhandQty(UPDATED_ONHAND_QTY)
            .onrentQty(UPDATED_ONRENT_QTY)
            .comittedQty(UPDATED_COMITTED_QTY)
            .inorderQty(UPDATED_INORDER_QTY)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .itemInventoryStatusUuid(UPDATED_ITEM_INVENTORY_STATUS_UUID)
            .itemNo(UPDATED_ITEM_NO)
            .itemName(UPDATED_ITEM_NAME)
            .itemDescription(UPDATED_ITEM_DESCRIPTION)
            .itemLocationName(UPDATED_ITEM_LOCATION_NAME)
            .binNo(UPDATED_BIN_NO)
            .whetherSerialised(UPDATED_WHETHER_SERIALISED)
            .whetherAssetTagged(UPDATED_WHETHER_ASSET_TAGGED)
            .updatedDate(UPDATED_UPDATED_DATE)
            .onBackorder(UPDATED_ON_BACKORDER);
        return itemInventoryStatus;
    }

    @BeforeEach
    public void initTest() {
        itemInventoryStatus = createEntity(em);
    }

    @Test
    @Transactional
    void createItemInventoryStatus() throws Exception {
        int databaseSizeBeforeCreate = itemInventoryStatusRepository.findAll().size();
        // Create the ItemInventoryStatus
        ItemInventoryStatusDTO itemInventoryStatusDTO = itemInventoryStatusMapper.toDto(itemInventoryStatus);
        restItemInventoryStatusMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemInventoryStatusDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ItemInventoryStatus in the database
        List<ItemInventoryStatus> itemInventoryStatusList = itemInventoryStatusRepository.findAll();
        assertThat(itemInventoryStatusList).hasSize(databaseSizeBeforeCreate + 1);
        ItemInventoryStatus testItemInventoryStatus = itemInventoryStatusList.get(itemInventoryStatusList.size() - 1);
        assertThat(testItemInventoryStatus.getItemId()).isEqualTo(DEFAULT_ITEM_ID);
        assertThat(testItemInventoryStatus.getItemLocationId()).isEqualTo(DEFAULT_ITEM_LOCATION_ID);
        assertThat(testItemInventoryStatus.getOnhandQty()).isEqualTo(DEFAULT_ONHAND_QTY);
        assertThat(testItemInventoryStatus.getOnrentQty()).isEqualTo(DEFAULT_ONRENT_QTY);
        assertThat(testItemInventoryStatus.getComittedQty()).isEqualTo(DEFAULT_COMITTED_QTY);
        assertThat(testItemInventoryStatus.getInorderQty()).isEqualTo(DEFAULT_INORDER_QTY);
        assertThat(testItemInventoryStatus.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testItemInventoryStatus.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testItemInventoryStatus.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testItemInventoryStatus.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testItemInventoryStatus.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testItemInventoryStatus.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testItemInventoryStatus.getItemInventoryStatusUuid()).isEqualTo(DEFAULT_ITEM_INVENTORY_STATUS_UUID);
        assertThat(testItemInventoryStatus.getItemNo()).isEqualTo(DEFAULT_ITEM_NO);
        assertThat(testItemInventoryStatus.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testItemInventoryStatus.getItemDescription()).isEqualTo(DEFAULT_ITEM_DESCRIPTION);
        assertThat(testItemInventoryStatus.getItemLocationName()).isEqualTo(DEFAULT_ITEM_LOCATION_NAME);
        assertThat(testItemInventoryStatus.getBinNo()).isEqualTo(DEFAULT_BIN_NO);
        assertThat(testItemInventoryStatus.getWhetherSerialised()).isEqualTo(DEFAULT_WHETHER_SERIALISED);
        assertThat(testItemInventoryStatus.getWhetherAssetTagged()).isEqualTo(DEFAULT_WHETHER_ASSET_TAGGED);
        assertThat(testItemInventoryStatus.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testItemInventoryStatus.getOnBackorder()).isEqualTo(DEFAULT_ON_BACKORDER);
    }

    @Test
    @Transactional
    void createItemInventoryStatusWithExistingId() throws Exception {
        // Create the ItemInventoryStatus with an existing ID
        itemInventoryStatus.setItemInventoryStatusId(1L);
        ItemInventoryStatusDTO itemInventoryStatusDTO = itemInventoryStatusMapper.toDto(itemInventoryStatus);

        int databaseSizeBeforeCreate = itemInventoryStatusRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemInventoryStatusMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemInventoryStatusDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemInventoryStatus in the database
        List<ItemInventoryStatus> itemInventoryStatusList = itemInventoryStatusRepository.findAll();
        assertThat(itemInventoryStatusList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllItemInventoryStatuses() throws Exception {
        // Initialize the database
        itemInventoryStatusRepository.saveAndFlush(itemInventoryStatus);

        // Get all the itemInventoryStatusList
        restItemInventoryStatusMockMvc
            .perform(get(ENTITY_API_URL + "?sort=itemInventoryStatusId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].itemInventoryStatusId").value(hasItem(itemInventoryStatus.getItemInventoryStatusId().intValue())))
            .andExpect(jsonPath("$.[*].itemId").value(hasItem(DEFAULT_ITEM_ID.intValue())))
            .andExpect(jsonPath("$.[*].itemLocationId").value(hasItem(DEFAULT_ITEM_LOCATION_ID.intValue())))
            .andExpect(jsonPath("$.[*].onhandQty").value(hasItem(DEFAULT_ONHAND_QTY.intValue())))
            .andExpect(jsonPath("$.[*].onrentQty").value(hasItem(DEFAULT_ONRENT_QTY.intValue())))
            .andExpect(jsonPath("$.[*].comittedQty").value(hasItem(DEFAULT_COMITTED_QTY.intValue())))
            .andExpect(jsonPath("$.[*].inorderQty").value(hasItem(DEFAULT_INORDER_QTY.intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].itemInventoryStatusUuid").value(hasItem(DEFAULT_ITEM_INVENTORY_STATUS_UUID.toString())))
            .andExpect(jsonPath("$.[*].itemNo").value(hasItem(DEFAULT_ITEM_NO)))
            .andExpect(jsonPath("$.[*].itemName").value(hasItem(DEFAULT_ITEM_NAME)))
            .andExpect(jsonPath("$.[*].itemDescription").value(hasItem(DEFAULT_ITEM_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].itemLocationName").value(hasItem(DEFAULT_ITEM_LOCATION_NAME)))
            .andExpect(jsonPath("$.[*].binNo").value(hasItem(DEFAULT_BIN_NO)))
            .andExpect(jsonPath("$.[*].whetherSerialised").value(hasItem(DEFAULT_WHETHER_SERIALISED)))
            .andExpect(jsonPath("$.[*].whetherAssetTagged").value(hasItem(DEFAULT_WHETHER_ASSET_TAGGED)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].onBackorder").value(hasItem(DEFAULT_ON_BACKORDER)));
    }

    @Test
    @Transactional
    void getItemInventoryStatus() throws Exception {
        // Initialize the database
        itemInventoryStatusRepository.saveAndFlush(itemInventoryStatus);

        // Get the itemInventoryStatus
        restItemInventoryStatusMockMvc
            .perform(get(ENTITY_API_URL_ID, itemInventoryStatus.getItemInventoryStatusId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.itemInventoryStatusId").value(itemInventoryStatus.getItemInventoryStatusId().intValue()))
            .andExpect(jsonPath("$.itemId").value(DEFAULT_ITEM_ID.intValue()))
            .andExpect(jsonPath("$.itemLocationId").value(DEFAULT_ITEM_LOCATION_ID.intValue()))
            .andExpect(jsonPath("$.onhandQty").value(DEFAULT_ONHAND_QTY.intValue()))
            .andExpect(jsonPath("$.onrentQty").value(DEFAULT_ONRENT_QTY.intValue()))
            .andExpect(jsonPath("$.comittedQty").value(DEFAULT_COMITTED_QTY.intValue()))
            .andExpect(jsonPath("$.inorderQty").value(DEFAULT_INORDER_QTY.intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.itemInventoryStatusUuid").value(DEFAULT_ITEM_INVENTORY_STATUS_UUID.toString()))
            .andExpect(jsonPath("$.itemNo").value(DEFAULT_ITEM_NO))
            .andExpect(jsonPath("$.itemName").value(DEFAULT_ITEM_NAME))
            .andExpect(jsonPath("$.itemDescription").value(DEFAULT_ITEM_DESCRIPTION))
            .andExpect(jsonPath("$.itemLocationName").value(DEFAULT_ITEM_LOCATION_NAME))
            .andExpect(jsonPath("$.binNo").value(DEFAULT_BIN_NO))
            .andExpect(jsonPath("$.whetherSerialised").value(DEFAULT_WHETHER_SERIALISED))
            .andExpect(jsonPath("$.whetherAssetTagged").value(DEFAULT_WHETHER_ASSET_TAGGED))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.onBackorder").value(DEFAULT_ON_BACKORDER));
    }

    @Test
    @Transactional
    void getNonExistingItemInventoryStatus() throws Exception {
        // Get the itemInventoryStatus
        restItemInventoryStatusMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewItemInventoryStatus() throws Exception {
        // Initialize the database
        itemInventoryStatusRepository.saveAndFlush(itemInventoryStatus);

        int databaseSizeBeforeUpdate = itemInventoryStatusRepository.findAll().size();

        // Update the itemInventoryStatus
        ItemInventoryStatus updatedItemInventoryStatus = itemInventoryStatusRepository
            .findById(itemInventoryStatus.getItemInventoryStatusId())
            .get();
        // Disconnect from session so that the updates on updatedItemInventoryStatus are not directly saved in db
        em.detach(updatedItemInventoryStatus);
        updatedItemInventoryStatus
            .itemId(UPDATED_ITEM_ID)
            .itemLocationId(UPDATED_ITEM_LOCATION_ID)
            .onhandQty(UPDATED_ONHAND_QTY)
            .onrentQty(UPDATED_ONRENT_QTY)
            .comittedQty(UPDATED_COMITTED_QTY)
            .inorderQty(UPDATED_INORDER_QTY)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .itemInventoryStatusUuid(UPDATED_ITEM_INVENTORY_STATUS_UUID)
            .itemNo(UPDATED_ITEM_NO)
            .itemName(UPDATED_ITEM_NAME)
            .itemDescription(UPDATED_ITEM_DESCRIPTION)
            .itemLocationName(UPDATED_ITEM_LOCATION_NAME)
            .binNo(UPDATED_BIN_NO)
            .whetherSerialised(UPDATED_WHETHER_SERIALISED)
            .whetherAssetTagged(UPDATED_WHETHER_ASSET_TAGGED)
            .updatedDate(UPDATED_UPDATED_DATE)
            .onBackorder(UPDATED_ON_BACKORDER);
        ItemInventoryStatusDTO itemInventoryStatusDTO = itemInventoryStatusMapper.toDto(updatedItemInventoryStatus);

        restItemInventoryStatusMockMvc
            .perform(
                put(ENTITY_API_URL_ID, itemInventoryStatusDTO.getItemInventoryStatusId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemInventoryStatusDTO))
            )
            .andExpect(status().isOk());

        // Validate the ItemInventoryStatus in the database
        List<ItemInventoryStatus> itemInventoryStatusList = itemInventoryStatusRepository.findAll();
        assertThat(itemInventoryStatusList).hasSize(databaseSizeBeforeUpdate);
        ItemInventoryStatus testItemInventoryStatus = itemInventoryStatusList.get(itemInventoryStatusList.size() - 1);
        assertThat(testItemInventoryStatus.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testItemInventoryStatus.getItemLocationId()).isEqualTo(UPDATED_ITEM_LOCATION_ID);
        assertThat(testItemInventoryStatus.getOnhandQty()).isEqualTo(UPDATED_ONHAND_QTY);
        assertThat(testItemInventoryStatus.getOnrentQty()).isEqualTo(UPDATED_ONRENT_QTY);
        assertThat(testItemInventoryStatus.getComittedQty()).isEqualTo(UPDATED_COMITTED_QTY);
        assertThat(testItemInventoryStatus.getInorderQty()).isEqualTo(UPDATED_INORDER_QTY);
        assertThat(testItemInventoryStatus.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testItemInventoryStatus.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testItemInventoryStatus.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testItemInventoryStatus.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testItemInventoryStatus.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testItemInventoryStatus.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testItemInventoryStatus.getItemInventoryStatusUuid()).isEqualTo(UPDATED_ITEM_INVENTORY_STATUS_UUID);
        assertThat(testItemInventoryStatus.getItemNo()).isEqualTo(UPDATED_ITEM_NO);
        assertThat(testItemInventoryStatus.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testItemInventoryStatus.getItemDescription()).isEqualTo(UPDATED_ITEM_DESCRIPTION);
        assertThat(testItemInventoryStatus.getItemLocationName()).isEqualTo(UPDATED_ITEM_LOCATION_NAME);
        assertThat(testItemInventoryStatus.getBinNo()).isEqualTo(UPDATED_BIN_NO);
        assertThat(testItemInventoryStatus.getWhetherSerialised()).isEqualTo(UPDATED_WHETHER_SERIALISED);
        assertThat(testItemInventoryStatus.getWhetherAssetTagged()).isEqualTo(UPDATED_WHETHER_ASSET_TAGGED);
        assertThat(testItemInventoryStatus.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testItemInventoryStatus.getOnBackorder()).isEqualTo(UPDATED_ON_BACKORDER);
    }

    @Test
    @Transactional
    void putNonExistingItemInventoryStatus() throws Exception {
        int databaseSizeBeforeUpdate = itemInventoryStatusRepository.findAll().size();
        itemInventoryStatus.setItemInventoryStatusId(count.incrementAndGet());

        // Create the ItemInventoryStatus
        ItemInventoryStatusDTO itemInventoryStatusDTO = itemInventoryStatusMapper.toDto(itemInventoryStatus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemInventoryStatusMockMvc
            .perform(
                put(ENTITY_API_URL_ID, itemInventoryStatusDTO.getItemInventoryStatusId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemInventoryStatusDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemInventoryStatus in the database
        List<ItemInventoryStatus> itemInventoryStatusList = itemInventoryStatusRepository.findAll();
        assertThat(itemInventoryStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchItemInventoryStatus() throws Exception {
        int databaseSizeBeforeUpdate = itemInventoryStatusRepository.findAll().size();
        itemInventoryStatus.setItemInventoryStatusId(count.incrementAndGet());

        // Create the ItemInventoryStatus
        ItemInventoryStatusDTO itemInventoryStatusDTO = itemInventoryStatusMapper.toDto(itemInventoryStatus);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemInventoryStatusMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemInventoryStatusDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemInventoryStatus in the database
        List<ItemInventoryStatus> itemInventoryStatusList = itemInventoryStatusRepository.findAll();
        assertThat(itemInventoryStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamItemInventoryStatus() throws Exception {
        int databaseSizeBeforeUpdate = itemInventoryStatusRepository.findAll().size();
        itemInventoryStatus.setItemInventoryStatusId(count.incrementAndGet());

        // Create the ItemInventoryStatus
        ItemInventoryStatusDTO itemInventoryStatusDTO = itemInventoryStatusMapper.toDto(itemInventoryStatus);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemInventoryStatusMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemInventoryStatusDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ItemInventoryStatus in the database
        List<ItemInventoryStatus> itemInventoryStatusList = itemInventoryStatusRepository.findAll();
        assertThat(itemInventoryStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateItemInventoryStatusWithPatch() throws Exception {
        // Initialize the database
        itemInventoryStatusRepository.saveAndFlush(itemInventoryStatus);

        int databaseSizeBeforeUpdate = itemInventoryStatusRepository.findAll().size();

        // Update the itemInventoryStatus using partial update
        ItemInventoryStatus partialUpdatedItemInventoryStatus = new ItemInventoryStatus();
        partialUpdatedItemInventoryStatus.setItemInventoryStatusId(itemInventoryStatus.getItemInventoryStatusId());

        partialUpdatedItemInventoryStatus
            .status(UPDATED_STATUS)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .itemLocationName(UPDATED_ITEM_LOCATION_NAME);

        restItemInventoryStatusMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedItemInventoryStatus.getItemInventoryStatusId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedItemInventoryStatus))
            )
            .andExpect(status().isOk());

        // Validate the ItemInventoryStatus in the database
        List<ItemInventoryStatus> itemInventoryStatusList = itemInventoryStatusRepository.findAll();
        assertThat(itemInventoryStatusList).hasSize(databaseSizeBeforeUpdate);
        ItemInventoryStatus testItemInventoryStatus = itemInventoryStatusList.get(itemInventoryStatusList.size() - 1);
        assertThat(testItemInventoryStatus.getItemId()).isEqualTo(DEFAULT_ITEM_ID);
        assertThat(testItemInventoryStatus.getItemLocationId()).isEqualTo(DEFAULT_ITEM_LOCATION_ID);
        assertThat(testItemInventoryStatus.getOnhandQty()).isEqualTo(DEFAULT_ONHAND_QTY);
        assertThat(testItemInventoryStatus.getOnrentQty()).isEqualTo(DEFAULT_ONRENT_QTY);
        assertThat(testItemInventoryStatus.getComittedQty()).isEqualTo(DEFAULT_COMITTED_QTY);
        assertThat(testItemInventoryStatus.getInorderQty()).isEqualTo(DEFAULT_INORDER_QTY);
        assertThat(testItemInventoryStatus.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testItemInventoryStatus.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testItemInventoryStatus.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testItemInventoryStatus.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testItemInventoryStatus.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testItemInventoryStatus.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testItemInventoryStatus.getItemInventoryStatusUuid()).isEqualTo(DEFAULT_ITEM_INVENTORY_STATUS_UUID);
        assertThat(testItemInventoryStatus.getItemNo()).isEqualTo(DEFAULT_ITEM_NO);
        assertThat(testItemInventoryStatus.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testItemInventoryStatus.getItemDescription()).isEqualTo(DEFAULT_ITEM_DESCRIPTION);
        assertThat(testItemInventoryStatus.getItemLocationName()).isEqualTo(UPDATED_ITEM_LOCATION_NAME);
        assertThat(testItemInventoryStatus.getBinNo()).isEqualTo(DEFAULT_BIN_NO);
        assertThat(testItemInventoryStatus.getWhetherSerialised()).isEqualTo(DEFAULT_WHETHER_SERIALISED);
        assertThat(testItemInventoryStatus.getWhetherAssetTagged()).isEqualTo(DEFAULT_WHETHER_ASSET_TAGGED);
        assertThat(testItemInventoryStatus.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testItemInventoryStatus.getOnBackorder()).isEqualTo(DEFAULT_ON_BACKORDER);
    }

    @Test
    @Transactional
    void fullUpdateItemInventoryStatusWithPatch() throws Exception {
        // Initialize the database
        itemInventoryStatusRepository.saveAndFlush(itemInventoryStatus);

        int databaseSizeBeforeUpdate = itemInventoryStatusRepository.findAll().size();

        // Update the itemInventoryStatus using partial update
        ItemInventoryStatus partialUpdatedItemInventoryStatus = new ItemInventoryStatus();
        partialUpdatedItemInventoryStatus.setItemInventoryStatusId(itemInventoryStatus.getItemInventoryStatusId());

        partialUpdatedItemInventoryStatus
            .itemId(UPDATED_ITEM_ID)
            .itemLocationId(UPDATED_ITEM_LOCATION_ID)
            .onhandQty(UPDATED_ONHAND_QTY)
            .onrentQty(UPDATED_ONRENT_QTY)
            .comittedQty(UPDATED_COMITTED_QTY)
            .inorderQty(UPDATED_INORDER_QTY)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .itemInventoryStatusUuid(UPDATED_ITEM_INVENTORY_STATUS_UUID)
            .itemNo(UPDATED_ITEM_NO)
            .itemName(UPDATED_ITEM_NAME)
            .itemDescription(UPDATED_ITEM_DESCRIPTION)
            .itemLocationName(UPDATED_ITEM_LOCATION_NAME)
            .binNo(UPDATED_BIN_NO)
            .whetherSerialised(UPDATED_WHETHER_SERIALISED)
            .whetherAssetTagged(UPDATED_WHETHER_ASSET_TAGGED)
            .updatedDate(UPDATED_UPDATED_DATE)
            .onBackorder(UPDATED_ON_BACKORDER);

        restItemInventoryStatusMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedItemInventoryStatus.getItemInventoryStatusId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedItemInventoryStatus))
            )
            .andExpect(status().isOk());

        // Validate the ItemInventoryStatus in the database
        List<ItemInventoryStatus> itemInventoryStatusList = itemInventoryStatusRepository.findAll();
        assertThat(itemInventoryStatusList).hasSize(databaseSizeBeforeUpdate);
        ItemInventoryStatus testItemInventoryStatus = itemInventoryStatusList.get(itemInventoryStatusList.size() - 1);
        assertThat(testItemInventoryStatus.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testItemInventoryStatus.getItemLocationId()).isEqualTo(UPDATED_ITEM_LOCATION_ID);
        assertThat(testItemInventoryStatus.getOnhandQty()).isEqualTo(UPDATED_ONHAND_QTY);
        assertThat(testItemInventoryStatus.getOnrentQty()).isEqualTo(UPDATED_ONRENT_QTY);
        assertThat(testItemInventoryStatus.getComittedQty()).isEqualTo(UPDATED_COMITTED_QTY);
        assertThat(testItemInventoryStatus.getInorderQty()).isEqualTo(UPDATED_INORDER_QTY);
        assertThat(testItemInventoryStatus.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testItemInventoryStatus.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testItemInventoryStatus.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testItemInventoryStatus.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testItemInventoryStatus.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testItemInventoryStatus.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testItemInventoryStatus.getItemInventoryStatusUuid()).isEqualTo(UPDATED_ITEM_INVENTORY_STATUS_UUID);
        assertThat(testItemInventoryStatus.getItemNo()).isEqualTo(UPDATED_ITEM_NO);
        assertThat(testItemInventoryStatus.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testItemInventoryStatus.getItemDescription()).isEqualTo(UPDATED_ITEM_DESCRIPTION);
        assertThat(testItemInventoryStatus.getItemLocationName()).isEqualTo(UPDATED_ITEM_LOCATION_NAME);
        assertThat(testItemInventoryStatus.getBinNo()).isEqualTo(UPDATED_BIN_NO);
        assertThat(testItemInventoryStatus.getWhetherSerialised()).isEqualTo(UPDATED_WHETHER_SERIALISED);
        assertThat(testItemInventoryStatus.getWhetherAssetTagged()).isEqualTo(UPDATED_WHETHER_ASSET_TAGGED);
        assertThat(testItemInventoryStatus.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testItemInventoryStatus.getOnBackorder()).isEqualTo(UPDATED_ON_BACKORDER);
    }

    @Test
    @Transactional
    void patchNonExistingItemInventoryStatus() throws Exception {
        int databaseSizeBeforeUpdate = itemInventoryStatusRepository.findAll().size();
        itemInventoryStatus.setItemInventoryStatusId(count.incrementAndGet());

        // Create the ItemInventoryStatus
        ItemInventoryStatusDTO itemInventoryStatusDTO = itemInventoryStatusMapper.toDto(itemInventoryStatus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemInventoryStatusMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, itemInventoryStatusDTO.getItemInventoryStatusId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemInventoryStatusDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemInventoryStatus in the database
        List<ItemInventoryStatus> itemInventoryStatusList = itemInventoryStatusRepository.findAll();
        assertThat(itemInventoryStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchItemInventoryStatus() throws Exception {
        int databaseSizeBeforeUpdate = itemInventoryStatusRepository.findAll().size();
        itemInventoryStatus.setItemInventoryStatusId(count.incrementAndGet());

        // Create the ItemInventoryStatus
        ItemInventoryStatusDTO itemInventoryStatusDTO = itemInventoryStatusMapper.toDto(itemInventoryStatus);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemInventoryStatusMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemInventoryStatusDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemInventoryStatus in the database
        List<ItemInventoryStatus> itemInventoryStatusList = itemInventoryStatusRepository.findAll();
        assertThat(itemInventoryStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamItemInventoryStatus() throws Exception {
        int databaseSizeBeforeUpdate = itemInventoryStatusRepository.findAll().size();
        itemInventoryStatus.setItemInventoryStatusId(count.incrementAndGet());

        // Create the ItemInventoryStatus
        ItemInventoryStatusDTO itemInventoryStatusDTO = itemInventoryStatusMapper.toDto(itemInventoryStatus);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemInventoryStatusMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemInventoryStatusDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ItemInventoryStatus in the database
        List<ItemInventoryStatus> itemInventoryStatusList = itemInventoryStatusRepository.findAll();
        assertThat(itemInventoryStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteItemInventoryStatus() throws Exception {
        // Initialize the database
        itemInventoryStatusRepository.saveAndFlush(itemInventoryStatus);

        int databaseSizeBeforeDelete = itemInventoryStatusRepository.findAll().size();

        // Delete the itemInventoryStatus
        restItemInventoryStatusMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, itemInventoryStatus.getItemInventoryStatusId()).with(csrf()).accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ItemInventoryStatus> itemInventoryStatusList = itemInventoryStatusRepository.findAll();
        assertThat(itemInventoryStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
