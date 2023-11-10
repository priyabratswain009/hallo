package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ItemAssetNumberMap;
import com.sunknowledge.dme.rcm.repository.ItemAssetNumberMapRepository;
import com.sunknowledge.dme.rcm.service.dto.ItemAssetNumberMapDTO;
import com.sunknowledge.dme.rcm.service.mapper.ItemAssetNumberMapMapper;
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
 * Integration tests for the {@link ItemAssetNumberMapResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ItemAssetNumberMapResourceIT {

    private static final Long DEFAULT_ITEM_ID = 1L;
    private static final Long UPDATED_ITEM_ID = 2L;

    private static final String DEFAULT_ITEM_NO = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NO = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_LOCATION_ID = 1L;
    private static final Long UPDATED_LOCATION_ID = 2L;

    private static final String DEFAULT_LOCATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ASSET_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ASSET_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_ON_HAND_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_ON_HAND_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_ON_RENT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_ON_RENT_STATUS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PURCHASE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PURCHASE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_SALE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SALE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DEPRECIATION_READY_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_DEPRECIATION_READY_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_DEPRECIATION_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_DEPRECIATION_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_USEFUL_LIFE_IN_YEARS = 1L;
    private static final Long UPDATED_USEFUL_LIFE_IN_YEARS = 2L;

    private static final LocalDate DEFAULT_START_DEPRECIATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_START_DEPRECIATION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_ORIGINAL_COST = 1D;
    private static final Double UPDATED_ORIGINAL_COST = 2D;

    private static final Double DEFAULT_BOOK_VALUE = 1D;
    private static final Double UPDATED_BOOK_VALUE = 2D;

    private static final Double DEFAULT_ACCUMULATED_DEPRECIATION = 1D;
    private static final Double UPDATED_ACCUMULATED_DEPRECIATION = 2D;

    private static final Double DEFAULT_RESIDUAL_VALUE = 1D;
    private static final Double UPDATED_RESIDUAL_VALUE = 2D;

    private static final String DEFAULT_USER_VALUE_1 = "AAAAAAAAAA";
    private static final String UPDATED_USER_VALUE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_USER_VALUE_2 = "AAAAAAAAAA";
    private static final String UPDATED_USER_VALUE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_USER_VALUE_3 = "AAAAAAAAAA";
    private static final String UPDATED_USER_VALUE_3 = "BBBBBBBBBB";

    private static final String DEFAULT_USER_VALUE_4 = "AAAAAAAAAA";
    private static final String UPDATED_USER_VALUE_4 = "BBBBBBBBBB";

    private static final String DEFAULT_LOT_BATCH_NO = "AAAAAAAAAA";
    private static final String UPDATED_LOT_BATCH_NO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_LOT_BATCH_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LOT_BATCH_DATE = LocalDate.now(ZoneId.systemDefault());

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

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final UUID DEFAULT_ITEM_ASSET_NUMBER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_ITEM_ASSET_NUMBER_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/item-asset-number-maps";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{itemAssetNumberId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ItemAssetNumberMapRepository itemAssetNumberMapRepository;

    @Autowired
    private ItemAssetNumberMapMapper itemAssetNumberMapMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restItemAssetNumberMapMockMvc;

    private ItemAssetNumberMap itemAssetNumberMap;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemAssetNumberMap createEntity(EntityManager em) {
        ItemAssetNumberMap itemAssetNumberMap = new ItemAssetNumberMap()
            .itemId(DEFAULT_ITEM_ID)
            .itemNo(DEFAULT_ITEM_NO)
            .itemName(DEFAULT_ITEM_NAME)
            .locationId(DEFAULT_LOCATION_ID)
            .locationName(DEFAULT_LOCATION_NAME)
            .assetNumber(DEFAULT_ASSET_NUMBER)
            .onHandStatus(DEFAULT_ON_HAND_STATUS)
            .onRentStatus(DEFAULT_ON_RENT_STATUS)
            .purchaseDate(DEFAULT_PURCHASE_DATE)
            .saleDate(DEFAULT_SALE_DATE)
            .depreciationReadyStatus(DEFAULT_DEPRECIATION_READY_STATUS)
            .depreciationStatus(DEFAULT_DEPRECIATION_STATUS)
            .usefulLifeInYears(DEFAULT_USEFUL_LIFE_IN_YEARS)
            .startDepreciationDate(DEFAULT_START_DEPRECIATION_DATE)
            .originalCost(DEFAULT_ORIGINAL_COST)
            .bookValue(DEFAULT_BOOK_VALUE)
            .accumulatedDepreciation(DEFAULT_ACCUMULATED_DEPRECIATION)
            .residualValue(DEFAULT_RESIDUAL_VALUE)
            .userValue1(DEFAULT_USER_VALUE_1)
            .userValue2(DEFAULT_USER_VALUE_2)
            .userValue3(DEFAULT_USER_VALUE_3)
            .userValue4(DEFAULT_USER_VALUE_4)
            .lotBatchNo(DEFAULT_LOT_BATCH_NO)
            .lotBatchDate(DEFAULT_LOT_BATCH_DATE)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .itemAssetNumberUuid(DEFAULT_ITEM_ASSET_NUMBER_UUID);
        return itemAssetNumberMap;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemAssetNumberMap createUpdatedEntity(EntityManager em) {
        ItemAssetNumberMap itemAssetNumberMap = new ItemAssetNumberMap()
            .itemId(UPDATED_ITEM_ID)
            .itemNo(UPDATED_ITEM_NO)
            .itemName(UPDATED_ITEM_NAME)
            .locationId(UPDATED_LOCATION_ID)
            .locationName(UPDATED_LOCATION_NAME)
            .assetNumber(UPDATED_ASSET_NUMBER)
            .onHandStatus(UPDATED_ON_HAND_STATUS)
            .onRentStatus(UPDATED_ON_RENT_STATUS)
            .purchaseDate(UPDATED_PURCHASE_DATE)
            .saleDate(UPDATED_SALE_DATE)
            .depreciationReadyStatus(UPDATED_DEPRECIATION_READY_STATUS)
            .depreciationStatus(UPDATED_DEPRECIATION_STATUS)
            .usefulLifeInYears(UPDATED_USEFUL_LIFE_IN_YEARS)
            .startDepreciationDate(UPDATED_START_DEPRECIATION_DATE)
            .originalCost(UPDATED_ORIGINAL_COST)
            .bookValue(UPDATED_BOOK_VALUE)
            .accumulatedDepreciation(UPDATED_ACCUMULATED_DEPRECIATION)
            .residualValue(UPDATED_RESIDUAL_VALUE)
            .userValue1(UPDATED_USER_VALUE_1)
            .userValue2(UPDATED_USER_VALUE_2)
            .userValue3(UPDATED_USER_VALUE_3)
            .userValue4(UPDATED_USER_VALUE_4)
            .lotBatchNo(UPDATED_LOT_BATCH_NO)
            .lotBatchDate(UPDATED_LOT_BATCH_DATE)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .itemAssetNumberUuid(UPDATED_ITEM_ASSET_NUMBER_UUID);
        return itemAssetNumberMap;
    }

    @BeforeEach
    public void initTest() {
        itemAssetNumberMap = createEntity(em);
    }

    @Test
    @Transactional
    void createItemAssetNumberMap() throws Exception {
        int databaseSizeBeforeCreate = itemAssetNumberMapRepository.findAll().size();
        // Create the ItemAssetNumberMap
        ItemAssetNumberMapDTO itemAssetNumberMapDTO = itemAssetNumberMapMapper.toDto(itemAssetNumberMap);
        restItemAssetNumberMapMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemAssetNumberMapDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ItemAssetNumberMap in the database
        List<ItemAssetNumberMap> itemAssetNumberMapList = itemAssetNumberMapRepository.findAll();
        assertThat(itemAssetNumberMapList).hasSize(databaseSizeBeforeCreate + 1);
        ItemAssetNumberMap testItemAssetNumberMap = itemAssetNumberMapList.get(itemAssetNumberMapList.size() - 1);
        assertThat(testItemAssetNumberMap.getItemId()).isEqualTo(DEFAULT_ITEM_ID);
        assertThat(testItemAssetNumberMap.getItemNo()).isEqualTo(DEFAULT_ITEM_NO);
        assertThat(testItemAssetNumberMap.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testItemAssetNumberMap.getLocationId()).isEqualTo(DEFAULT_LOCATION_ID);
        assertThat(testItemAssetNumberMap.getLocationName()).isEqualTo(DEFAULT_LOCATION_NAME);
        assertThat(testItemAssetNumberMap.getAssetNumber()).isEqualTo(DEFAULT_ASSET_NUMBER);
        assertThat(testItemAssetNumberMap.getOnHandStatus()).isEqualTo(DEFAULT_ON_HAND_STATUS);
        assertThat(testItemAssetNumberMap.getOnRentStatus()).isEqualTo(DEFAULT_ON_RENT_STATUS);
        assertThat(testItemAssetNumberMap.getPurchaseDate()).isEqualTo(DEFAULT_PURCHASE_DATE);
        assertThat(testItemAssetNumberMap.getSaleDate()).isEqualTo(DEFAULT_SALE_DATE);
        assertThat(testItemAssetNumberMap.getDepreciationReadyStatus()).isEqualTo(DEFAULT_DEPRECIATION_READY_STATUS);
        assertThat(testItemAssetNumberMap.getDepreciationStatus()).isEqualTo(DEFAULT_DEPRECIATION_STATUS);
        assertThat(testItemAssetNumberMap.getUsefulLifeInYears()).isEqualTo(DEFAULT_USEFUL_LIFE_IN_YEARS);
        assertThat(testItemAssetNumberMap.getStartDepreciationDate()).isEqualTo(DEFAULT_START_DEPRECIATION_DATE);
        assertThat(testItemAssetNumberMap.getOriginalCost()).isEqualTo(DEFAULT_ORIGINAL_COST);
        assertThat(testItemAssetNumberMap.getBookValue()).isEqualTo(DEFAULT_BOOK_VALUE);
        assertThat(testItemAssetNumberMap.getAccumulatedDepreciation()).isEqualTo(DEFAULT_ACCUMULATED_DEPRECIATION);
        assertThat(testItemAssetNumberMap.getResidualValue()).isEqualTo(DEFAULT_RESIDUAL_VALUE);
        assertThat(testItemAssetNumberMap.getUserValue1()).isEqualTo(DEFAULT_USER_VALUE_1);
        assertThat(testItemAssetNumberMap.getUserValue2()).isEqualTo(DEFAULT_USER_VALUE_2);
        assertThat(testItemAssetNumberMap.getUserValue3()).isEqualTo(DEFAULT_USER_VALUE_3);
        assertThat(testItemAssetNumberMap.getUserValue4()).isEqualTo(DEFAULT_USER_VALUE_4);
        assertThat(testItemAssetNumberMap.getLotBatchNo()).isEqualTo(DEFAULT_LOT_BATCH_NO);
        assertThat(testItemAssetNumberMap.getLotBatchDate()).isEqualTo(DEFAULT_LOT_BATCH_DATE);
        assertThat(testItemAssetNumberMap.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testItemAssetNumberMap.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testItemAssetNumberMap.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testItemAssetNumberMap.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testItemAssetNumberMap.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testItemAssetNumberMap.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testItemAssetNumberMap.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testItemAssetNumberMap.getItemAssetNumberUuid()).isEqualTo(DEFAULT_ITEM_ASSET_NUMBER_UUID);
    }

    @Test
    @Transactional
    void createItemAssetNumberMapWithExistingId() throws Exception {
        // Create the ItemAssetNumberMap with an existing ID
        itemAssetNumberMap.setItemAssetNumberId(1L);
        ItemAssetNumberMapDTO itemAssetNumberMapDTO = itemAssetNumberMapMapper.toDto(itemAssetNumberMap);

        int databaseSizeBeforeCreate = itemAssetNumberMapRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemAssetNumberMapMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemAssetNumberMapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemAssetNumberMap in the database
        List<ItemAssetNumberMap> itemAssetNumberMapList = itemAssetNumberMapRepository.findAll();
        assertThat(itemAssetNumberMapList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllItemAssetNumberMaps() throws Exception {
        // Initialize the database
        itemAssetNumberMapRepository.saveAndFlush(itemAssetNumberMap);

        // Get all the itemAssetNumberMapList
        restItemAssetNumberMapMockMvc
            .perform(get(ENTITY_API_URL + "?sort=itemAssetNumberId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].itemAssetNumberId").value(hasItem(itemAssetNumberMap.getItemAssetNumberId().intValue())))
            .andExpect(jsonPath("$.[*].itemId").value(hasItem(DEFAULT_ITEM_ID.intValue())))
            .andExpect(jsonPath("$.[*].itemNo").value(hasItem(DEFAULT_ITEM_NO)))
            .andExpect(jsonPath("$.[*].itemName").value(hasItem(DEFAULT_ITEM_NAME)))
            .andExpect(jsonPath("$.[*].locationId").value(hasItem(DEFAULT_LOCATION_ID.intValue())))
            .andExpect(jsonPath("$.[*].locationName").value(hasItem(DEFAULT_LOCATION_NAME)))
            .andExpect(jsonPath("$.[*].assetNumber").value(hasItem(DEFAULT_ASSET_NUMBER)))
            .andExpect(jsonPath("$.[*].onHandStatus").value(hasItem(DEFAULT_ON_HAND_STATUS)))
            .andExpect(jsonPath("$.[*].onRentStatus").value(hasItem(DEFAULT_ON_RENT_STATUS)))
            .andExpect(jsonPath("$.[*].purchaseDate").value(hasItem(DEFAULT_PURCHASE_DATE.toString())))
            .andExpect(jsonPath("$.[*].saleDate").value(hasItem(DEFAULT_SALE_DATE.toString())))
            .andExpect(jsonPath("$.[*].depreciationReadyStatus").value(hasItem(DEFAULT_DEPRECIATION_READY_STATUS)))
            .andExpect(jsonPath("$.[*].depreciationStatus").value(hasItem(DEFAULT_DEPRECIATION_STATUS)))
            .andExpect(jsonPath("$.[*].usefulLifeInYears").value(hasItem(DEFAULT_USEFUL_LIFE_IN_YEARS.intValue())))
            .andExpect(jsonPath("$.[*].startDepreciationDate").value(hasItem(DEFAULT_START_DEPRECIATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].originalCost").value(hasItem(DEFAULT_ORIGINAL_COST.doubleValue())))
            .andExpect(jsonPath("$.[*].bookValue").value(hasItem(DEFAULT_BOOK_VALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].accumulatedDepreciation").value(hasItem(DEFAULT_ACCUMULATED_DEPRECIATION.doubleValue())))
            .andExpect(jsonPath("$.[*].residualValue").value(hasItem(DEFAULT_RESIDUAL_VALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].userValue1").value(hasItem(DEFAULT_USER_VALUE_1)))
            .andExpect(jsonPath("$.[*].userValue2").value(hasItem(DEFAULT_USER_VALUE_2)))
            .andExpect(jsonPath("$.[*].userValue3").value(hasItem(DEFAULT_USER_VALUE_3)))
            .andExpect(jsonPath("$.[*].userValue4").value(hasItem(DEFAULT_USER_VALUE_4)))
            .andExpect(jsonPath("$.[*].lotBatchNo").value(hasItem(DEFAULT_LOT_BATCH_NO)))
            .andExpect(jsonPath("$.[*].lotBatchDate").value(hasItem(DEFAULT_LOT_BATCH_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].itemAssetNumberUuid").value(hasItem(DEFAULT_ITEM_ASSET_NUMBER_UUID.toString())));
    }

    @Test
    @Transactional
    void getItemAssetNumberMap() throws Exception {
        // Initialize the database
        itemAssetNumberMapRepository.saveAndFlush(itemAssetNumberMap);

        // Get the itemAssetNumberMap
        restItemAssetNumberMapMockMvc
            .perform(get(ENTITY_API_URL_ID, itemAssetNumberMap.getItemAssetNumberId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.itemAssetNumberId").value(itemAssetNumberMap.getItemAssetNumberId().intValue()))
            .andExpect(jsonPath("$.itemId").value(DEFAULT_ITEM_ID.intValue()))
            .andExpect(jsonPath("$.itemNo").value(DEFAULT_ITEM_NO))
            .andExpect(jsonPath("$.itemName").value(DEFAULT_ITEM_NAME))
            .andExpect(jsonPath("$.locationId").value(DEFAULT_LOCATION_ID.intValue()))
            .andExpect(jsonPath("$.locationName").value(DEFAULT_LOCATION_NAME))
            .andExpect(jsonPath("$.assetNumber").value(DEFAULT_ASSET_NUMBER))
            .andExpect(jsonPath("$.onHandStatus").value(DEFAULT_ON_HAND_STATUS))
            .andExpect(jsonPath("$.onRentStatus").value(DEFAULT_ON_RENT_STATUS))
            .andExpect(jsonPath("$.purchaseDate").value(DEFAULT_PURCHASE_DATE.toString()))
            .andExpect(jsonPath("$.saleDate").value(DEFAULT_SALE_DATE.toString()))
            .andExpect(jsonPath("$.depreciationReadyStatus").value(DEFAULT_DEPRECIATION_READY_STATUS))
            .andExpect(jsonPath("$.depreciationStatus").value(DEFAULT_DEPRECIATION_STATUS))
            .andExpect(jsonPath("$.usefulLifeInYears").value(DEFAULT_USEFUL_LIFE_IN_YEARS.intValue()))
            .andExpect(jsonPath("$.startDepreciationDate").value(DEFAULT_START_DEPRECIATION_DATE.toString()))
            .andExpect(jsonPath("$.originalCost").value(DEFAULT_ORIGINAL_COST.doubleValue()))
            .andExpect(jsonPath("$.bookValue").value(DEFAULT_BOOK_VALUE.doubleValue()))
            .andExpect(jsonPath("$.accumulatedDepreciation").value(DEFAULT_ACCUMULATED_DEPRECIATION.doubleValue()))
            .andExpect(jsonPath("$.residualValue").value(DEFAULT_RESIDUAL_VALUE.doubleValue()))
            .andExpect(jsonPath("$.userValue1").value(DEFAULT_USER_VALUE_1))
            .andExpect(jsonPath("$.userValue2").value(DEFAULT_USER_VALUE_2))
            .andExpect(jsonPath("$.userValue3").value(DEFAULT_USER_VALUE_3))
            .andExpect(jsonPath("$.userValue4").value(DEFAULT_USER_VALUE_4))
            .andExpect(jsonPath("$.lotBatchNo").value(DEFAULT_LOT_BATCH_NO))
            .andExpect(jsonPath("$.lotBatchDate").value(DEFAULT_LOT_BATCH_DATE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.itemAssetNumberUuid").value(DEFAULT_ITEM_ASSET_NUMBER_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingItemAssetNumberMap() throws Exception {
        // Get the itemAssetNumberMap
        restItemAssetNumberMapMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingItemAssetNumberMap() throws Exception {
        // Initialize the database
        itemAssetNumberMapRepository.saveAndFlush(itemAssetNumberMap);

        int databaseSizeBeforeUpdate = itemAssetNumberMapRepository.findAll().size();

        // Update the itemAssetNumberMap
        ItemAssetNumberMap updatedItemAssetNumberMap = itemAssetNumberMapRepository
            .findById(itemAssetNumberMap.getItemAssetNumberId())
            .get();
        // Disconnect from session so that the updates on updatedItemAssetNumberMap are not directly saved in db
        em.detach(updatedItemAssetNumberMap);
        updatedItemAssetNumberMap
            .itemId(UPDATED_ITEM_ID)
            .itemNo(UPDATED_ITEM_NO)
            .itemName(UPDATED_ITEM_NAME)
            .locationId(UPDATED_LOCATION_ID)
            .locationName(UPDATED_LOCATION_NAME)
            .assetNumber(UPDATED_ASSET_NUMBER)
            .onHandStatus(UPDATED_ON_HAND_STATUS)
            .onRentStatus(UPDATED_ON_RENT_STATUS)
            .purchaseDate(UPDATED_PURCHASE_DATE)
            .saleDate(UPDATED_SALE_DATE)
            .depreciationReadyStatus(UPDATED_DEPRECIATION_READY_STATUS)
            .depreciationStatus(UPDATED_DEPRECIATION_STATUS)
            .usefulLifeInYears(UPDATED_USEFUL_LIFE_IN_YEARS)
            .startDepreciationDate(UPDATED_START_DEPRECIATION_DATE)
            .originalCost(UPDATED_ORIGINAL_COST)
            .bookValue(UPDATED_BOOK_VALUE)
            .accumulatedDepreciation(UPDATED_ACCUMULATED_DEPRECIATION)
            .residualValue(UPDATED_RESIDUAL_VALUE)
            .userValue1(UPDATED_USER_VALUE_1)
            .userValue2(UPDATED_USER_VALUE_2)
            .userValue3(UPDATED_USER_VALUE_3)
            .userValue4(UPDATED_USER_VALUE_4)
            .lotBatchNo(UPDATED_LOT_BATCH_NO)
            .lotBatchDate(UPDATED_LOT_BATCH_DATE)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .itemAssetNumberUuid(UPDATED_ITEM_ASSET_NUMBER_UUID);
        ItemAssetNumberMapDTO itemAssetNumberMapDTO = itemAssetNumberMapMapper.toDto(updatedItemAssetNumberMap);

        restItemAssetNumberMapMockMvc
            .perform(
                put(ENTITY_API_URL_ID, itemAssetNumberMapDTO.getItemAssetNumberId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemAssetNumberMapDTO))
            )
            .andExpect(status().isOk());

        // Validate the ItemAssetNumberMap in the database
        List<ItemAssetNumberMap> itemAssetNumberMapList = itemAssetNumberMapRepository.findAll();
        assertThat(itemAssetNumberMapList).hasSize(databaseSizeBeforeUpdate);
        ItemAssetNumberMap testItemAssetNumberMap = itemAssetNumberMapList.get(itemAssetNumberMapList.size() - 1);
        assertThat(testItemAssetNumberMap.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testItemAssetNumberMap.getItemNo()).isEqualTo(UPDATED_ITEM_NO);
        assertThat(testItemAssetNumberMap.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testItemAssetNumberMap.getLocationId()).isEqualTo(UPDATED_LOCATION_ID);
        assertThat(testItemAssetNumberMap.getLocationName()).isEqualTo(UPDATED_LOCATION_NAME);
        assertThat(testItemAssetNumberMap.getAssetNumber()).isEqualTo(UPDATED_ASSET_NUMBER);
        assertThat(testItemAssetNumberMap.getOnHandStatus()).isEqualTo(UPDATED_ON_HAND_STATUS);
        assertThat(testItemAssetNumberMap.getOnRentStatus()).isEqualTo(UPDATED_ON_RENT_STATUS);
        assertThat(testItemAssetNumberMap.getPurchaseDate()).isEqualTo(UPDATED_PURCHASE_DATE);
        assertThat(testItemAssetNumberMap.getSaleDate()).isEqualTo(UPDATED_SALE_DATE);
        assertThat(testItemAssetNumberMap.getDepreciationReadyStatus()).isEqualTo(UPDATED_DEPRECIATION_READY_STATUS);
        assertThat(testItemAssetNumberMap.getDepreciationStatus()).isEqualTo(UPDATED_DEPRECIATION_STATUS);
        assertThat(testItemAssetNumberMap.getUsefulLifeInYears()).isEqualTo(UPDATED_USEFUL_LIFE_IN_YEARS);
        assertThat(testItemAssetNumberMap.getStartDepreciationDate()).isEqualTo(UPDATED_START_DEPRECIATION_DATE);
        assertThat(testItemAssetNumberMap.getOriginalCost()).isEqualTo(UPDATED_ORIGINAL_COST);
        assertThat(testItemAssetNumberMap.getBookValue()).isEqualTo(UPDATED_BOOK_VALUE);
        assertThat(testItemAssetNumberMap.getAccumulatedDepreciation()).isEqualTo(UPDATED_ACCUMULATED_DEPRECIATION);
        assertThat(testItemAssetNumberMap.getResidualValue()).isEqualTo(UPDATED_RESIDUAL_VALUE);
        assertThat(testItemAssetNumberMap.getUserValue1()).isEqualTo(UPDATED_USER_VALUE_1);
        assertThat(testItemAssetNumberMap.getUserValue2()).isEqualTo(UPDATED_USER_VALUE_2);
        assertThat(testItemAssetNumberMap.getUserValue3()).isEqualTo(UPDATED_USER_VALUE_3);
        assertThat(testItemAssetNumberMap.getUserValue4()).isEqualTo(UPDATED_USER_VALUE_4);
        assertThat(testItemAssetNumberMap.getLotBatchNo()).isEqualTo(UPDATED_LOT_BATCH_NO);
        assertThat(testItemAssetNumberMap.getLotBatchDate()).isEqualTo(UPDATED_LOT_BATCH_DATE);
        assertThat(testItemAssetNumberMap.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testItemAssetNumberMap.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testItemAssetNumberMap.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testItemAssetNumberMap.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testItemAssetNumberMap.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testItemAssetNumberMap.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testItemAssetNumberMap.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testItemAssetNumberMap.getItemAssetNumberUuid()).isEqualTo(UPDATED_ITEM_ASSET_NUMBER_UUID);
    }

    @Test
    @Transactional
    void putNonExistingItemAssetNumberMap() throws Exception {
        int databaseSizeBeforeUpdate = itemAssetNumberMapRepository.findAll().size();
        itemAssetNumberMap.setItemAssetNumberId(count.incrementAndGet());

        // Create the ItemAssetNumberMap
        ItemAssetNumberMapDTO itemAssetNumberMapDTO = itemAssetNumberMapMapper.toDto(itemAssetNumberMap);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemAssetNumberMapMockMvc
            .perform(
                put(ENTITY_API_URL_ID, itemAssetNumberMapDTO.getItemAssetNumberId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemAssetNumberMapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemAssetNumberMap in the database
        List<ItemAssetNumberMap> itemAssetNumberMapList = itemAssetNumberMapRepository.findAll();
        assertThat(itemAssetNumberMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchItemAssetNumberMap() throws Exception {
        int databaseSizeBeforeUpdate = itemAssetNumberMapRepository.findAll().size();
        itemAssetNumberMap.setItemAssetNumberId(count.incrementAndGet());

        // Create the ItemAssetNumberMap
        ItemAssetNumberMapDTO itemAssetNumberMapDTO = itemAssetNumberMapMapper.toDto(itemAssetNumberMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemAssetNumberMapMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemAssetNumberMapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemAssetNumberMap in the database
        List<ItemAssetNumberMap> itemAssetNumberMapList = itemAssetNumberMapRepository.findAll();
        assertThat(itemAssetNumberMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamItemAssetNumberMap() throws Exception {
        int databaseSizeBeforeUpdate = itemAssetNumberMapRepository.findAll().size();
        itemAssetNumberMap.setItemAssetNumberId(count.incrementAndGet());

        // Create the ItemAssetNumberMap
        ItemAssetNumberMapDTO itemAssetNumberMapDTO = itemAssetNumberMapMapper.toDto(itemAssetNumberMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemAssetNumberMapMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemAssetNumberMapDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ItemAssetNumberMap in the database
        List<ItemAssetNumberMap> itemAssetNumberMapList = itemAssetNumberMapRepository.findAll();
        assertThat(itemAssetNumberMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateItemAssetNumberMapWithPatch() throws Exception {
        // Initialize the database
        itemAssetNumberMapRepository.saveAndFlush(itemAssetNumberMap);

        int databaseSizeBeforeUpdate = itemAssetNumberMapRepository.findAll().size();

        // Update the itemAssetNumberMap using partial update
        ItemAssetNumberMap partialUpdatedItemAssetNumberMap = new ItemAssetNumberMap();
        partialUpdatedItemAssetNumberMap.setItemAssetNumberId(itemAssetNumberMap.getItemAssetNumberId());

        partialUpdatedItemAssetNumberMap
            .itemId(UPDATED_ITEM_ID)
            .itemNo(UPDATED_ITEM_NO)
            .locationId(UPDATED_LOCATION_ID)
            .assetNumber(UPDATED_ASSET_NUMBER)
            .onRentStatus(UPDATED_ON_RENT_STATUS)
            .startDepreciationDate(UPDATED_START_DEPRECIATION_DATE)
            .bookValue(UPDATED_BOOK_VALUE)
            .accumulatedDepreciation(UPDATED_ACCUMULATED_DEPRECIATION)
            .userValue2(UPDATED_USER_VALUE_2)
            .userValue4(UPDATED_USER_VALUE_4)
            .lotBatchNo(UPDATED_LOT_BATCH_NO)
            .lotBatchDate(UPDATED_LOT_BATCH_DATE)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .itemAssetNumberUuid(UPDATED_ITEM_ASSET_NUMBER_UUID);

        restItemAssetNumberMapMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedItemAssetNumberMap.getItemAssetNumberId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedItemAssetNumberMap))
            )
            .andExpect(status().isOk());

        // Validate the ItemAssetNumberMap in the database
        List<ItemAssetNumberMap> itemAssetNumberMapList = itemAssetNumberMapRepository.findAll();
        assertThat(itemAssetNumberMapList).hasSize(databaseSizeBeforeUpdate);
        ItemAssetNumberMap testItemAssetNumberMap = itemAssetNumberMapList.get(itemAssetNumberMapList.size() - 1);
        assertThat(testItemAssetNumberMap.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testItemAssetNumberMap.getItemNo()).isEqualTo(UPDATED_ITEM_NO);
        assertThat(testItemAssetNumberMap.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testItemAssetNumberMap.getLocationId()).isEqualTo(UPDATED_LOCATION_ID);
        assertThat(testItemAssetNumberMap.getLocationName()).isEqualTo(DEFAULT_LOCATION_NAME);
        assertThat(testItemAssetNumberMap.getAssetNumber()).isEqualTo(UPDATED_ASSET_NUMBER);
        assertThat(testItemAssetNumberMap.getOnHandStatus()).isEqualTo(DEFAULT_ON_HAND_STATUS);
        assertThat(testItemAssetNumberMap.getOnRentStatus()).isEqualTo(UPDATED_ON_RENT_STATUS);
        assertThat(testItemAssetNumberMap.getPurchaseDate()).isEqualTo(DEFAULT_PURCHASE_DATE);
        assertThat(testItemAssetNumberMap.getSaleDate()).isEqualTo(DEFAULT_SALE_DATE);
        assertThat(testItemAssetNumberMap.getDepreciationReadyStatus()).isEqualTo(DEFAULT_DEPRECIATION_READY_STATUS);
        assertThat(testItemAssetNumberMap.getDepreciationStatus()).isEqualTo(DEFAULT_DEPRECIATION_STATUS);
        assertThat(testItemAssetNumberMap.getUsefulLifeInYears()).isEqualTo(DEFAULT_USEFUL_LIFE_IN_YEARS);
        assertThat(testItemAssetNumberMap.getStartDepreciationDate()).isEqualTo(UPDATED_START_DEPRECIATION_DATE);
        assertThat(testItemAssetNumberMap.getOriginalCost()).isEqualTo(DEFAULT_ORIGINAL_COST);
        assertThat(testItemAssetNumberMap.getBookValue()).isEqualTo(UPDATED_BOOK_VALUE);
        assertThat(testItemAssetNumberMap.getAccumulatedDepreciation()).isEqualTo(UPDATED_ACCUMULATED_DEPRECIATION);
        assertThat(testItemAssetNumberMap.getResidualValue()).isEqualTo(DEFAULT_RESIDUAL_VALUE);
        assertThat(testItemAssetNumberMap.getUserValue1()).isEqualTo(DEFAULT_USER_VALUE_1);
        assertThat(testItemAssetNumberMap.getUserValue2()).isEqualTo(UPDATED_USER_VALUE_2);
        assertThat(testItemAssetNumberMap.getUserValue3()).isEqualTo(DEFAULT_USER_VALUE_3);
        assertThat(testItemAssetNumberMap.getUserValue4()).isEqualTo(UPDATED_USER_VALUE_4);
        assertThat(testItemAssetNumberMap.getLotBatchNo()).isEqualTo(UPDATED_LOT_BATCH_NO);
        assertThat(testItemAssetNumberMap.getLotBatchDate()).isEqualTo(UPDATED_LOT_BATCH_DATE);
        assertThat(testItemAssetNumberMap.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testItemAssetNumberMap.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testItemAssetNumberMap.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testItemAssetNumberMap.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testItemAssetNumberMap.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testItemAssetNumberMap.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testItemAssetNumberMap.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testItemAssetNumberMap.getItemAssetNumberUuid()).isEqualTo(UPDATED_ITEM_ASSET_NUMBER_UUID);
    }

    @Test
    @Transactional
    void fullUpdateItemAssetNumberMapWithPatch() throws Exception {
        // Initialize the database
        itemAssetNumberMapRepository.saveAndFlush(itemAssetNumberMap);

        int databaseSizeBeforeUpdate = itemAssetNumberMapRepository.findAll().size();

        // Update the itemAssetNumberMap using partial update
        ItemAssetNumberMap partialUpdatedItemAssetNumberMap = new ItemAssetNumberMap();
        partialUpdatedItemAssetNumberMap.setItemAssetNumberId(itemAssetNumberMap.getItemAssetNumberId());

        partialUpdatedItemAssetNumberMap
            .itemId(UPDATED_ITEM_ID)
            .itemNo(UPDATED_ITEM_NO)
            .itemName(UPDATED_ITEM_NAME)
            .locationId(UPDATED_LOCATION_ID)
            .locationName(UPDATED_LOCATION_NAME)
            .assetNumber(UPDATED_ASSET_NUMBER)
            .onHandStatus(UPDATED_ON_HAND_STATUS)
            .onRentStatus(UPDATED_ON_RENT_STATUS)
            .purchaseDate(UPDATED_PURCHASE_DATE)
            .saleDate(UPDATED_SALE_DATE)
            .depreciationReadyStatus(UPDATED_DEPRECIATION_READY_STATUS)
            .depreciationStatus(UPDATED_DEPRECIATION_STATUS)
            .usefulLifeInYears(UPDATED_USEFUL_LIFE_IN_YEARS)
            .startDepreciationDate(UPDATED_START_DEPRECIATION_DATE)
            .originalCost(UPDATED_ORIGINAL_COST)
            .bookValue(UPDATED_BOOK_VALUE)
            .accumulatedDepreciation(UPDATED_ACCUMULATED_DEPRECIATION)
            .residualValue(UPDATED_RESIDUAL_VALUE)
            .userValue1(UPDATED_USER_VALUE_1)
            .userValue2(UPDATED_USER_VALUE_2)
            .userValue3(UPDATED_USER_VALUE_3)
            .userValue4(UPDATED_USER_VALUE_4)
            .lotBatchNo(UPDATED_LOT_BATCH_NO)
            .lotBatchDate(UPDATED_LOT_BATCH_DATE)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .itemAssetNumberUuid(UPDATED_ITEM_ASSET_NUMBER_UUID);

        restItemAssetNumberMapMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedItemAssetNumberMap.getItemAssetNumberId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedItemAssetNumberMap))
            )
            .andExpect(status().isOk());

        // Validate the ItemAssetNumberMap in the database
        List<ItemAssetNumberMap> itemAssetNumberMapList = itemAssetNumberMapRepository.findAll();
        assertThat(itemAssetNumberMapList).hasSize(databaseSizeBeforeUpdate);
        ItemAssetNumberMap testItemAssetNumberMap = itemAssetNumberMapList.get(itemAssetNumberMapList.size() - 1);
        assertThat(testItemAssetNumberMap.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testItemAssetNumberMap.getItemNo()).isEqualTo(UPDATED_ITEM_NO);
        assertThat(testItemAssetNumberMap.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testItemAssetNumberMap.getLocationId()).isEqualTo(UPDATED_LOCATION_ID);
        assertThat(testItemAssetNumberMap.getLocationName()).isEqualTo(UPDATED_LOCATION_NAME);
        assertThat(testItemAssetNumberMap.getAssetNumber()).isEqualTo(UPDATED_ASSET_NUMBER);
        assertThat(testItemAssetNumberMap.getOnHandStatus()).isEqualTo(UPDATED_ON_HAND_STATUS);
        assertThat(testItemAssetNumberMap.getOnRentStatus()).isEqualTo(UPDATED_ON_RENT_STATUS);
        assertThat(testItemAssetNumberMap.getPurchaseDate()).isEqualTo(UPDATED_PURCHASE_DATE);
        assertThat(testItemAssetNumberMap.getSaleDate()).isEqualTo(UPDATED_SALE_DATE);
        assertThat(testItemAssetNumberMap.getDepreciationReadyStatus()).isEqualTo(UPDATED_DEPRECIATION_READY_STATUS);
        assertThat(testItemAssetNumberMap.getDepreciationStatus()).isEqualTo(UPDATED_DEPRECIATION_STATUS);
        assertThat(testItemAssetNumberMap.getUsefulLifeInYears()).isEqualTo(UPDATED_USEFUL_LIFE_IN_YEARS);
        assertThat(testItemAssetNumberMap.getStartDepreciationDate()).isEqualTo(UPDATED_START_DEPRECIATION_DATE);
        assertThat(testItemAssetNumberMap.getOriginalCost()).isEqualTo(UPDATED_ORIGINAL_COST);
        assertThat(testItemAssetNumberMap.getBookValue()).isEqualTo(UPDATED_BOOK_VALUE);
        assertThat(testItemAssetNumberMap.getAccumulatedDepreciation()).isEqualTo(UPDATED_ACCUMULATED_DEPRECIATION);
        assertThat(testItemAssetNumberMap.getResidualValue()).isEqualTo(UPDATED_RESIDUAL_VALUE);
        assertThat(testItemAssetNumberMap.getUserValue1()).isEqualTo(UPDATED_USER_VALUE_1);
        assertThat(testItemAssetNumberMap.getUserValue2()).isEqualTo(UPDATED_USER_VALUE_2);
        assertThat(testItemAssetNumberMap.getUserValue3()).isEqualTo(UPDATED_USER_VALUE_3);
        assertThat(testItemAssetNumberMap.getUserValue4()).isEqualTo(UPDATED_USER_VALUE_4);
        assertThat(testItemAssetNumberMap.getLotBatchNo()).isEqualTo(UPDATED_LOT_BATCH_NO);
        assertThat(testItemAssetNumberMap.getLotBatchDate()).isEqualTo(UPDATED_LOT_BATCH_DATE);
        assertThat(testItemAssetNumberMap.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testItemAssetNumberMap.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testItemAssetNumberMap.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testItemAssetNumberMap.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testItemAssetNumberMap.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testItemAssetNumberMap.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testItemAssetNumberMap.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testItemAssetNumberMap.getItemAssetNumberUuid()).isEqualTo(UPDATED_ITEM_ASSET_NUMBER_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingItemAssetNumberMap() throws Exception {
        int databaseSizeBeforeUpdate = itemAssetNumberMapRepository.findAll().size();
        itemAssetNumberMap.setItemAssetNumberId(count.incrementAndGet());

        // Create the ItemAssetNumberMap
        ItemAssetNumberMapDTO itemAssetNumberMapDTO = itemAssetNumberMapMapper.toDto(itemAssetNumberMap);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemAssetNumberMapMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, itemAssetNumberMapDTO.getItemAssetNumberId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemAssetNumberMapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemAssetNumberMap in the database
        List<ItemAssetNumberMap> itemAssetNumberMapList = itemAssetNumberMapRepository.findAll();
        assertThat(itemAssetNumberMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchItemAssetNumberMap() throws Exception {
        int databaseSizeBeforeUpdate = itemAssetNumberMapRepository.findAll().size();
        itemAssetNumberMap.setItemAssetNumberId(count.incrementAndGet());

        // Create the ItemAssetNumberMap
        ItemAssetNumberMapDTO itemAssetNumberMapDTO = itemAssetNumberMapMapper.toDto(itemAssetNumberMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemAssetNumberMapMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemAssetNumberMapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemAssetNumberMap in the database
        List<ItemAssetNumberMap> itemAssetNumberMapList = itemAssetNumberMapRepository.findAll();
        assertThat(itemAssetNumberMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamItemAssetNumberMap() throws Exception {
        int databaseSizeBeforeUpdate = itemAssetNumberMapRepository.findAll().size();
        itemAssetNumberMap.setItemAssetNumberId(count.incrementAndGet());

        // Create the ItemAssetNumberMap
        ItemAssetNumberMapDTO itemAssetNumberMapDTO = itemAssetNumberMapMapper.toDto(itemAssetNumberMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemAssetNumberMapMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemAssetNumberMapDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ItemAssetNumberMap in the database
        List<ItemAssetNumberMap> itemAssetNumberMapList = itemAssetNumberMapRepository.findAll();
        assertThat(itemAssetNumberMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteItemAssetNumberMap() throws Exception {
        // Initialize the database
        itemAssetNumberMapRepository.saveAndFlush(itemAssetNumberMap);

        int databaseSizeBeforeDelete = itemAssetNumberMapRepository.findAll().size();

        // Delete the itemAssetNumberMap
        restItemAssetNumberMapMockMvc
            .perform(delete(ENTITY_API_URL_ID, itemAssetNumberMap.getItemAssetNumberId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ItemAssetNumberMap> itemAssetNumberMapList = itemAssetNumberMapRepository.findAll();
        assertThat(itemAssetNumberMapList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
