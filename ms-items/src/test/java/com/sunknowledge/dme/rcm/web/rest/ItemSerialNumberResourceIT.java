package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ItemSerialNumber;
import com.sunknowledge.dme.rcm.repository.ItemSerialNumberRepository;
import com.sunknowledge.dme.rcm.service.dto.ItemSerialNumberDTO;
import com.sunknowledge.dme.rcm.service.mapper.ItemSerialNumberMapper;
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
 * Integration tests for the {@link ItemSerialNumberResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ItemSerialNumberResourceIT {

    private static final Long DEFAULT_ITEM_ID = 1L;
    private static final Long UPDATED_ITEM_ID = 2L;

    private static final Long DEFAULT_LOCATION_ID = 1L;
    private static final Long UPDATED_LOCATION_ID = 2L;

    private static final String DEFAULT_SERIAL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_SERIAL_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_ASSET_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ASSET_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_ON_HAND_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_ON_HAND_STATUS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PURCHASE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PURCHASE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_SALE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SALE_DATE = LocalDate.now(ZoneId.systemDefault());

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

    private static final UUID DEFAULT_ITEM_SERIAL_NUMBER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_ITEM_SERIAL_NUMBER_UUID = UUID.randomUUID();

    private static final String DEFAULT_ITEM_NO = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NO = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ON_RENT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_ON_RENT_STATUS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_LOT_BATCH_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LOT_BATCH_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_LOT_NO = "AAAAAAAAAA";
    private static final String UPDATED_LOT_NO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_MFG_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MFG_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_PO_ID = 1L;
    private static final Long UPDATED_PO_ID = 2L;

    private static final String DEFAULT_PO_NO = "AAAAAAAAAA";
    private static final String UPDATED_PO_NO = "BBBBBBBBBB";

    private static final Long DEFAULT_ADJUSTMENT_ID = 1L;
    private static final Long UPDATED_ADJUSTMENT_ID = 2L;

    private static final String DEFAULT_ADJUSTMENT_NO = "AAAAAAAAAA";
    private static final String UPDATED_ADJUSTMENT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_IS_DROPSHIP = "AAAAAAAAAA";
    private static final String UPDATED_IS_DROPSHIP = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/item-serial-numbers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{itemSerialNumberId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ItemSerialNumberRepository itemSerialNumberRepository;

    @Autowired
    private ItemSerialNumberMapper itemSerialNumberMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restItemSerialNumberMockMvc;

    private ItemSerialNumber itemSerialNumber;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemSerialNumber createEntity(EntityManager em) {
        ItemSerialNumber itemSerialNumber = new ItemSerialNumber()
            .itemId(DEFAULT_ITEM_ID)
            .locationId(DEFAULT_LOCATION_ID)
            .serialNumber(DEFAULT_SERIAL_NUMBER)
            .assetNumber(DEFAULT_ASSET_NUMBER)
            .onHandStatus(DEFAULT_ON_HAND_STATUS)
            .purchaseDate(DEFAULT_PURCHASE_DATE)
            .saleDate(DEFAULT_SALE_DATE)
            .depreciationStatus(DEFAULT_DEPRECIATION_STATUS)
            .usefulLifeInYears(DEFAULT_USEFUL_LIFE_IN_YEARS)
            .startDepreciationDate(DEFAULT_START_DEPRECIATION_DATE)
            .originalCost(DEFAULT_ORIGINAL_COST)
            .bookValue(DEFAULT_BOOK_VALUE)
            .userValue1(DEFAULT_USER_VALUE_1)
            .userValue2(DEFAULT_USER_VALUE_2)
            .userValue3(DEFAULT_USER_VALUE_3)
            .userValue4(DEFAULT_USER_VALUE_4)
            .lotBatchNo(DEFAULT_LOT_BATCH_NO)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .itemSerialNumberUuid(DEFAULT_ITEM_SERIAL_NUMBER_UUID)
            .itemNo(DEFAULT_ITEM_NO)
            .itemName(DEFAULT_ITEM_NAME)
            .locationName(DEFAULT_LOCATION_NAME)
            .onRentStatus(DEFAULT_ON_RENT_STATUS)
            .lotBatchDate(DEFAULT_LOT_BATCH_DATE)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .lotNo(DEFAULT_LOT_NO)
            .mfgDate(DEFAULT_MFG_DATE)
            .poId(DEFAULT_PO_ID)
            .poNo(DEFAULT_PO_NO)
            .adjustmentId(DEFAULT_ADJUSTMENT_ID)
            .adjustmentNo(DEFAULT_ADJUSTMENT_NO)
            .isDropship(DEFAULT_IS_DROPSHIP);
        return itemSerialNumber;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemSerialNumber createUpdatedEntity(EntityManager em) {
        ItemSerialNumber itemSerialNumber = new ItemSerialNumber()
            .itemId(UPDATED_ITEM_ID)
            .locationId(UPDATED_LOCATION_ID)
            .serialNumber(UPDATED_SERIAL_NUMBER)
            .assetNumber(UPDATED_ASSET_NUMBER)
            .onHandStatus(UPDATED_ON_HAND_STATUS)
            .purchaseDate(UPDATED_PURCHASE_DATE)
            .saleDate(UPDATED_SALE_DATE)
            .depreciationStatus(UPDATED_DEPRECIATION_STATUS)
            .usefulLifeInYears(UPDATED_USEFUL_LIFE_IN_YEARS)
            .startDepreciationDate(UPDATED_START_DEPRECIATION_DATE)
            .originalCost(UPDATED_ORIGINAL_COST)
            .bookValue(UPDATED_BOOK_VALUE)
            .userValue1(UPDATED_USER_VALUE_1)
            .userValue2(UPDATED_USER_VALUE_2)
            .userValue3(UPDATED_USER_VALUE_3)
            .userValue4(UPDATED_USER_VALUE_4)
            .lotBatchNo(UPDATED_LOT_BATCH_NO)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .itemSerialNumberUuid(UPDATED_ITEM_SERIAL_NUMBER_UUID)
            .itemNo(UPDATED_ITEM_NO)
            .itemName(UPDATED_ITEM_NAME)
            .locationName(UPDATED_LOCATION_NAME)
            .onRentStatus(UPDATED_ON_RENT_STATUS)
            .lotBatchDate(UPDATED_LOT_BATCH_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .lotNo(UPDATED_LOT_NO)
            .mfgDate(UPDATED_MFG_DATE)
            .poId(UPDATED_PO_ID)
            .poNo(UPDATED_PO_NO)
            .adjustmentId(UPDATED_ADJUSTMENT_ID)
            .adjustmentNo(UPDATED_ADJUSTMENT_NO)
            .isDropship(UPDATED_IS_DROPSHIP);
        return itemSerialNumber;
    }

    @BeforeEach
    public void initTest() {
        itemSerialNumber = createEntity(em);
    }

    @Test
    @Transactional
    void createItemSerialNumber() throws Exception {
        int databaseSizeBeforeCreate = itemSerialNumberRepository.findAll().size();
        // Create the ItemSerialNumber
        ItemSerialNumberDTO itemSerialNumberDTO = itemSerialNumberMapper.toDto(itemSerialNumber);
        restItemSerialNumberMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemSerialNumberDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ItemSerialNumber in the database
        List<ItemSerialNumber> itemSerialNumberList = itemSerialNumberRepository.findAll();
        assertThat(itemSerialNumberList).hasSize(databaseSizeBeforeCreate + 1);
        ItemSerialNumber testItemSerialNumber = itemSerialNumberList.get(itemSerialNumberList.size() - 1);
        assertThat(testItemSerialNumber.getItemId()).isEqualTo(DEFAULT_ITEM_ID);
        assertThat(testItemSerialNumber.getLocationId()).isEqualTo(DEFAULT_LOCATION_ID);
        assertThat(testItemSerialNumber.getSerialNumber()).isEqualTo(DEFAULT_SERIAL_NUMBER);
        assertThat(testItemSerialNumber.getAssetNumber()).isEqualTo(DEFAULT_ASSET_NUMBER);
        assertThat(testItemSerialNumber.getOnHandStatus()).isEqualTo(DEFAULT_ON_HAND_STATUS);
        assertThat(testItemSerialNumber.getPurchaseDate()).isEqualTo(DEFAULT_PURCHASE_DATE);
        assertThat(testItemSerialNumber.getSaleDate()).isEqualTo(DEFAULT_SALE_DATE);
        assertThat(testItemSerialNumber.getDepreciationStatus()).isEqualTo(DEFAULT_DEPRECIATION_STATUS);
        assertThat(testItemSerialNumber.getUsefulLifeInYears()).isEqualTo(DEFAULT_USEFUL_LIFE_IN_YEARS);
        assertThat(testItemSerialNumber.getStartDepreciationDate()).isEqualTo(DEFAULT_START_DEPRECIATION_DATE);
        assertThat(testItemSerialNumber.getOriginalCost()).isEqualTo(DEFAULT_ORIGINAL_COST);
        assertThat(testItemSerialNumber.getBookValue()).isEqualTo(DEFAULT_BOOK_VALUE);
        assertThat(testItemSerialNumber.getUserValue1()).isEqualTo(DEFAULT_USER_VALUE_1);
        assertThat(testItemSerialNumber.getUserValue2()).isEqualTo(DEFAULT_USER_VALUE_2);
        assertThat(testItemSerialNumber.getUserValue3()).isEqualTo(DEFAULT_USER_VALUE_3);
        assertThat(testItemSerialNumber.getUserValue4()).isEqualTo(DEFAULT_USER_VALUE_4);
        assertThat(testItemSerialNumber.getLotBatchNo()).isEqualTo(DEFAULT_LOT_BATCH_NO);
        assertThat(testItemSerialNumber.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testItemSerialNumber.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testItemSerialNumber.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testItemSerialNumber.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testItemSerialNumber.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testItemSerialNumber.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testItemSerialNumber.getItemSerialNumberUuid()).isEqualTo(DEFAULT_ITEM_SERIAL_NUMBER_UUID);
        assertThat(testItemSerialNumber.getItemNo()).isEqualTo(DEFAULT_ITEM_NO);
        assertThat(testItemSerialNumber.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testItemSerialNumber.getLocationName()).isEqualTo(DEFAULT_LOCATION_NAME);
        assertThat(testItemSerialNumber.getOnRentStatus()).isEqualTo(DEFAULT_ON_RENT_STATUS);
        assertThat(testItemSerialNumber.getLotBatchDate()).isEqualTo(DEFAULT_LOT_BATCH_DATE);
        assertThat(testItemSerialNumber.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testItemSerialNumber.getLotNo()).isEqualTo(DEFAULT_LOT_NO);
        assertThat(testItemSerialNumber.getMfgDate()).isEqualTo(DEFAULT_MFG_DATE);
        assertThat(testItemSerialNumber.getPoId()).isEqualTo(DEFAULT_PO_ID);
        assertThat(testItemSerialNumber.getPoNo()).isEqualTo(DEFAULT_PO_NO);
        assertThat(testItemSerialNumber.getAdjustmentId()).isEqualTo(DEFAULT_ADJUSTMENT_ID);
        assertThat(testItemSerialNumber.getAdjustmentNo()).isEqualTo(DEFAULT_ADJUSTMENT_NO);
        assertThat(testItemSerialNumber.getIsDropship()).isEqualTo(DEFAULT_IS_DROPSHIP);
    }

    @Test
    @Transactional
    void createItemSerialNumberWithExistingId() throws Exception {
        // Create the ItemSerialNumber with an existing ID
        itemSerialNumber.setItemSerialNumberId(1L);
        ItemSerialNumberDTO itemSerialNumberDTO = itemSerialNumberMapper.toDto(itemSerialNumber);

        int databaseSizeBeforeCreate = itemSerialNumberRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemSerialNumberMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemSerialNumberDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemSerialNumber in the database
        List<ItemSerialNumber> itemSerialNumberList = itemSerialNumberRepository.findAll();
        assertThat(itemSerialNumberList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllItemSerialNumbers() throws Exception {
        // Initialize the database
        itemSerialNumberRepository.saveAndFlush(itemSerialNumber);

        // Get all the itemSerialNumberList
        restItemSerialNumberMockMvc
            .perform(get(ENTITY_API_URL + "?sort=itemSerialNumberId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].itemSerialNumberId").value(hasItem(itemSerialNumber.getItemSerialNumberId().intValue())))
            .andExpect(jsonPath("$.[*].itemId").value(hasItem(DEFAULT_ITEM_ID.intValue())))
            .andExpect(jsonPath("$.[*].locationId").value(hasItem(DEFAULT_LOCATION_ID.intValue())))
            .andExpect(jsonPath("$.[*].serialNumber").value(hasItem(DEFAULT_SERIAL_NUMBER)))
            .andExpect(jsonPath("$.[*].assetNumber").value(hasItem(DEFAULT_ASSET_NUMBER)))
            .andExpect(jsonPath("$.[*].onHandStatus").value(hasItem(DEFAULT_ON_HAND_STATUS)))
            .andExpect(jsonPath("$.[*].purchaseDate").value(hasItem(DEFAULT_PURCHASE_DATE.toString())))
            .andExpect(jsonPath("$.[*].saleDate").value(hasItem(DEFAULT_SALE_DATE.toString())))
            .andExpect(jsonPath("$.[*].depreciationStatus").value(hasItem(DEFAULT_DEPRECIATION_STATUS)))
            .andExpect(jsonPath("$.[*].usefulLifeInYears").value(hasItem(DEFAULT_USEFUL_LIFE_IN_YEARS.intValue())))
            .andExpect(jsonPath("$.[*].startDepreciationDate").value(hasItem(DEFAULT_START_DEPRECIATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].originalCost").value(hasItem(DEFAULT_ORIGINAL_COST.doubleValue())))
            .andExpect(jsonPath("$.[*].bookValue").value(hasItem(DEFAULT_BOOK_VALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].userValue1").value(hasItem(DEFAULT_USER_VALUE_1)))
            .andExpect(jsonPath("$.[*].userValue2").value(hasItem(DEFAULT_USER_VALUE_2)))
            .andExpect(jsonPath("$.[*].userValue3").value(hasItem(DEFAULT_USER_VALUE_3)))
            .andExpect(jsonPath("$.[*].userValue4").value(hasItem(DEFAULT_USER_VALUE_4)))
            .andExpect(jsonPath("$.[*].lotBatchNo").value(hasItem(DEFAULT_LOT_BATCH_NO)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].itemSerialNumberUuid").value(hasItem(DEFAULT_ITEM_SERIAL_NUMBER_UUID.toString())))
            .andExpect(jsonPath("$.[*].itemNo").value(hasItem(DEFAULT_ITEM_NO)))
            .andExpect(jsonPath("$.[*].itemName").value(hasItem(DEFAULT_ITEM_NAME)))
            .andExpect(jsonPath("$.[*].locationName").value(hasItem(DEFAULT_LOCATION_NAME)))
            .andExpect(jsonPath("$.[*].onRentStatus").value(hasItem(DEFAULT_ON_RENT_STATUS)))
            .andExpect(jsonPath("$.[*].lotBatchDate").value(hasItem(DEFAULT_LOT_BATCH_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lotNo").value(hasItem(DEFAULT_LOT_NO)))
            .andExpect(jsonPath("$.[*].mfgDate").value(hasItem(DEFAULT_MFG_DATE.toString())))
            .andExpect(jsonPath("$.[*].poId").value(hasItem(DEFAULT_PO_ID.intValue())))
            .andExpect(jsonPath("$.[*].poNo").value(hasItem(DEFAULT_PO_NO)))
            .andExpect(jsonPath("$.[*].adjustmentId").value(hasItem(DEFAULT_ADJUSTMENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].adjustmentNo").value(hasItem(DEFAULT_ADJUSTMENT_NO)))
            .andExpect(jsonPath("$.[*].isDropship").value(hasItem(DEFAULT_IS_DROPSHIP)));
    }

    @Test
    @Transactional
    void getItemSerialNumber() throws Exception {
        // Initialize the database
        itemSerialNumberRepository.saveAndFlush(itemSerialNumber);

        // Get the itemSerialNumber
        restItemSerialNumberMockMvc
            .perform(get(ENTITY_API_URL_ID, itemSerialNumber.getItemSerialNumberId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.itemSerialNumberId").value(itemSerialNumber.getItemSerialNumberId().intValue()))
            .andExpect(jsonPath("$.itemId").value(DEFAULT_ITEM_ID.intValue()))
            .andExpect(jsonPath("$.locationId").value(DEFAULT_LOCATION_ID.intValue()))
            .andExpect(jsonPath("$.serialNumber").value(DEFAULT_SERIAL_NUMBER))
            .andExpect(jsonPath("$.assetNumber").value(DEFAULT_ASSET_NUMBER))
            .andExpect(jsonPath("$.onHandStatus").value(DEFAULT_ON_HAND_STATUS))
            .andExpect(jsonPath("$.purchaseDate").value(DEFAULT_PURCHASE_DATE.toString()))
            .andExpect(jsonPath("$.saleDate").value(DEFAULT_SALE_DATE.toString()))
            .andExpect(jsonPath("$.depreciationStatus").value(DEFAULT_DEPRECIATION_STATUS))
            .andExpect(jsonPath("$.usefulLifeInYears").value(DEFAULT_USEFUL_LIFE_IN_YEARS.intValue()))
            .andExpect(jsonPath("$.startDepreciationDate").value(DEFAULT_START_DEPRECIATION_DATE.toString()))
            .andExpect(jsonPath("$.originalCost").value(DEFAULT_ORIGINAL_COST.doubleValue()))
            .andExpect(jsonPath("$.bookValue").value(DEFAULT_BOOK_VALUE.doubleValue()))
            .andExpect(jsonPath("$.userValue1").value(DEFAULT_USER_VALUE_1))
            .andExpect(jsonPath("$.userValue2").value(DEFAULT_USER_VALUE_2))
            .andExpect(jsonPath("$.userValue3").value(DEFAULT_USER_VALUE_3))
            .andExpect(jsonPath("$.userValue4").value(DEFAULT_USER_VALUE_4))
            .andExpect(jsonPath("$.lotBatchNo").value(DEFAULT_LOT_BATCH_NO))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.itemSerialNumberUuid").value(DEFAULT_ITEM_SERIAL_NUMBER_UUID.toString()))
            .andExpect(jsonPath("$.itemNo").value(DEFAULT_ITEM_NO))
            .andExpect(jsonPath("$.itemName").value(DEFAULT_ITEM_NAME))
            .andExpect(jsonPath("$.locationName").value(DEFAULT_LOCATION_NAME))
            .andExpect(jsonPath("$.onRentStatus").value(DEFAULT_ON_RENT_STATUS))
            .andExpect(jsonPath("$.lotBatchDate").value(DEFAULT_LOT_BATCH_DATE.toString()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lotNo").value(DEFAULT_LOT_NO))
            .andExpect(jsonPath("$.mfgDate").value(DEFAULT_MFG_DATE.toString()))
            .andExpect(jsonPath("$.poId").value(DEFAULT_PO_ID.intValue()))
            .andExpect(jsonPath("$.poNo").value(DEFAULT_PO_NO))
            .andExpect(jsonPath("$.adjustmentId").value(DEFAULT_ADJUSTMENT_ID.intValue()))
            .andExpect(jsonPath("$.adjustmentNo").value(DEFAULT_ADJUSTMENT_NO))
            .andExpect(jsonPath("$.isDropship").value(DEFAULT_IS_DROPSHIP));
    }

    @Test
    @Transactional
    void getNonExistingItemSerialNumber() throws Exception {
        // Get the itemSerialNumber
        restItemSerialNumberMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewItemSerialNumber() throws Exception {
        // Initialize the database
        itemSerialNumberRepository.saveAndFlush(itemSerialNumber);

        int databaseSizeBeforeUpdate = itemSerialNumberRepository.findAll().size();

        // Update the itemSerialNumber
        ItemSerialNumber updatedItemSerialNumber = itemSerialNumberRepository.findById(itemSerialNumber.getItemSerialNumberId()).get();
        // Disconnect from session so that the updates on updatedItemSerialNumber are not directly saved in db
        em.detach(updatedItemSerialNumber);
        updatedItemSerialNumber
            .itemId(UPDATED_ITEM_ID)
            .locationId(UPDATED_LOCATION_ID)
            .serialNumber(UPDATED_SERIAL_NUMBER)
            .assetNumber(UPDATED_ASSET_NUMBER)
            .onHandStatus(UPDATED_ON_HAND_STATUS)
            .purchaseDate(UPDATED_PURCHASE_DATE)
            .saleDate(UPDATED_SALE_DATE)
            .depreciationStatus(UPDATED_DEPRECIATION_STATUS)
            .usefulLifeInYears(UPDATED_USEFUL_LIFE_IN_YEARS)
            .startDepreciationDate(UPDATED_START_DEPRECIATION_DATE)
            .originalCost(UPDATED_ORIGINAL_COST)
            .bookValue(UPDATED_BOOK_VALUE)
            .userValue1(UPDATED_USER_VALUE_1)
            .userValue2(UPDATED_USER_VALUE_2)
            .userValue3(UPDATED_USER_VALUE_3)
            .userValue4(UPDATED_USER_VALUE_4)
            .lotBatchNo(UPDATED_LOT_BATCH_NO)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .itemSerialNumberUuid(UPDATED_ITEM_SERIAL_NUMBER_UUID)
            .itemNo(UPDATED_ITEM_NO)
            .itemName(UPDATED_ITEM_NAME)
            .locationName(UPDATED_LOCATION_NAME)
            .onRentStatus(UPDATED_ON_RENT_STATUS)
            .lotBatchDate(UPDATED_LOT_BATCH_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .lotNo(UPDATED_LOT_NO)
            .mfgDate(UPDATED_MFG_DATE)
            .poId(UPDATED_PO_ID)
            .poNo(UPDATED_PO_NO)
            .adjustmentId(UPDATED_ADJUSTMENT_ID)
            .adjustmentNo(UPDATED_ADJUSTMENT_NO)
            .isDropship(UPDATED_IS_DROPSHIP);
        ItemSerialNumberDTO itemSerialNumberDTO = itemSerialNumberMapper.toDto(updatedItemSerialNumber);

        restItemSerialNumberMockMvc
            .perform(
                put(ENTITY_API_URL_ID, itemSerialNumberDTO.getItemSerialNumberId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemSerialNumberDTO))
            )
            .andExpect(status().isOk());

        // Validate the ItemSerialNumber in the database
        List<ItemSerialNumber> itemSerialNumberList = itemSerialNumberRepository.findAll();
        assertThat(itemSerialNumberList).hasSize(databaseSizeBeforeUpdate);
        ItemSerialNumber testItemSerialNumber = itemSerialNumberList.get(itemSerialNumberList.size() - 1);
        assertThat(testItemSerialNumber.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testItemSerialNumber.getLocationId()).isEqualTo(UPDATED_LOCATION_ID);
        assertThat(testItemSerialNumber.getSerialNumber()).isEqualTo(UPDATED_SERIAL_NUMBER);
        assertThat(testItemSerialNumber.getAssetNumber()).isEqualTo(UPDATED_ASSET_NUMBER);
        assertThat(testItemSerialNumber.getOnHandStatus()).isEqualTo(UPDATED_ON_HAND_STATUS);
        assertThat(testItemSerialNumber.getPurchaseDate()).isEqualTo(UPDATED_PURCHASE_DATE);
        assertThat(testItemSerialNumber.getSaleDate()).isEqualTo(UPDATED_SALE_DATE);
        assertThat(testItemSerialNumber.getDepreciationStatus()).isEqualTo(UPDATED_DEPRECIATION_STATUS);
        assertThat(testItemSerialNumber.getUsefulLifeInYears()).isEqualTo(UPDATED_USEFUL_LIFE_IN_YEARS);
        assertThat(testItemSerialNumber.getStartDepreciationDate()).isEqualTo(UPDATED_START_DEPRECIATION_DATE);
        assertThat(testItemSerialNumber.getOriginalCost()).isEqualTo(UPDATED_ORIGINAL_COST);
        assertThat(testItemSerialNumber.getBookValue()).isEqualTo(UPDATED_BOOK_VALUE);
        assertThat(testItemSerialNumber.getUserValue1()).isEqualTo(UPDATED_USER_VALUE_1);
        assertThat(testItemSerialNumber.getUserValue2()).isEqualTo(UPDATED_USER_VALUE_2);
        assertThat(testItemSerialNumber.getUserValue3()).isEqualTo(UPDATED_USER_VALUE_3);
        assertThat(testItemSerialNumber.getUserValue4()).isEqualTo(UPDATED_USER_VALUE_4);
        assertThat(testItemSerialNumber.getLotBatchNo()).isEqualTo(UPDATED_LOT_BATCH_NO);
        assertThat(testItemSerialNumber.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testItemSerialNumber.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testItemSerialNumber.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testItemSerialNumber.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testItemSerialNumber.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testItemSerialNumber.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testItemSerialNumber.getItemSerialNumberUuid()).isEqualTo(UPDATED_ITEM_SERIAL_NUMBER_UUID);
        assertThat(testItemSerialNumber.getItemNo()).isEqualTo(UPDATED_ITEM_NO);
        assertThat(testItemSerialNumber.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testItemSerialNumber.getLocationName()).isEqualTo(UPDATED_LOCATION_NAME);
        assertThat(testItemSerialNumber.getOnRentStatus()).isEqualTo(UPDATED_ON_RENT_STATUS);
        assertThat(testItemSerialNumber.getLotBatchDate()).isEqualTo(UPDATED_LOT_BATCH_DATE);
        assertThat(testItemSerialNumber.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testItemSerialNumber.getLotNo()).isEqualTo(UPDATED_LOT_NO);
        assertThat(testItemSerialNumber.getMfgDate()).isEqualTo(UPDATED_MFG_DATE);
        assertThat(testItemSerialNumber.getPoId()).isEqualTo(UPDATED_PO_ID);
        assertThat(testItemSerialNumber.getPoNo()).isEqualTo(UPDATED_PO_NO);
        assertThat(testItemSerialNumber.getAdjustmentId()).isEqualTo(UPDATED_ADJUSTMENT_ID);
        assertThat(testItemSerialNumber.getAdjustmentNo()).isEqualTo(UPDATED_ADJUSTMENT_NO);
        assertThat(testItemSerialNumber.getIsDropship()).isEqualTo(UPDATED_IS_DROPSHIP);
    }

    @Test
    @Transactional
    void putNonExistingItemSerialNumber() throws Exception {
        int databaseSizeBeforeUpdate = itemSerialNumberRepository.findAll().size();
        itemSerialNumber.setItemSerialNumberId(count.incrementAndGet());

        // Create the ItemSerialNumber
        ItemSerialNumberDTO itemSerialNumberDTO = itemSerialNumberMapper.toDto(itemSerialNumber);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemSerialNumberMockMvc
            .perform(
                put(ENTITY_API_URL_ID, itemSerialNumberDTO.getItemSerialNumberId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemSerialNumberDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemSerialNumber in the database
        List<ItemSerialNumber> itemSerialNumberList = itemSerialNumberRepository.findAll();
        assertThat(itemSerialNumberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchItemSerialNumber() throws Exception {
        int databaseSizeBeforeUpdate = itemSerialNumberRepository.findAll().size();
        itemSerialNumber.setItemSerialNumberId(count.incrementAndGet());

        // Create the ItemSerialNumber
        ItemSerialNumberDTO itemSerialNumberDTO = itemSerialNumberMapper.toDto(itemSerialNumber);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemSerialNumberMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemSerialNumberDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemSerialNumber in the database
        List<ItemSerialNumber> itemSerialNumberList = itemSerialNumberRepository.findAll();
        assertThat(itemSerialNumberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamItemSerialNumber() throws Exception {
        int databaseSizeBeforeUpdate = itemSerialNumberRepository.findAll().size();
        itemSerialNumber.setItemSerialNumberId(count.incrementAndGet());

        // Create the ItemSerialNumber
        ItemSerialNumberDTO itemSerialNumberDTO = itemSerialNumberMapper.toDto(itemSerialNumber);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemSerialNumberMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemSerialNumberDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ItemSerialNumber in the database
        List<ItemSerialNumber> itemSerialNumberList = itemSerialNumberRepository.findAll();
        assertThat(itemSerialNumberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateItemSerialNumberWithPatch() throws Exception {
        // Initialize the database
        itemSerialNumberRepository.saveAndFlush(itemSerialNumber);

        int databaseSizeBeforeUpdate = itemSerialNumberRepository.findAll().size();

        // Update the itemSerialNumber using partial update
        ItemSerialNumber partialUpdatedItemSerialNumber = new ItemSerialNumber();
        partialUpdatedItemSerialNumber.setItemSerialNumberId(itemSerialNumber.getItemSerialNumberId());

        partialUpdatedItemSerialNumber
            .locationId(UPDATED_LOCATION_ID)
            .serialNumber(UPDATED_SERIAL_NUMBER)
            .depreciationStatus(UPDATED_DEPRECIATION_STATUS)
            .originalCost(UPDATED_ORIGINAL_COST)
            .bookValue(UPDATED_BOOK_VALUE)
            .userValue2(UPDATED_USER_VALUE_2)
            .userValue3(UPDATED_USER_VALUE_3)
            .userValue4(UPDATED_USER_VALUE_4)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .itemSerialNumberUuid(UPDATED_ITEM_SERIAL_NUMBER_UUID)
            .itemName(UPDATED_ITEM_NAME)
            .locationName(UPDATED_LOCATION_NAME)
            .lotBatchDate(UPDATED_LOT_BATCH_DATE)
            .poId(UPDATED_PO_ID)
            .poNo(UPDATED_PO_NO)
            .adjustmentNo(UPDATED_ADJUSTMENT_NO)
            .isDropship(UPDATED_IS_DROPSHIP);

        restItemSerialNumberMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedItemSerialNumber.getItemSerialNumberId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedItemSerialNumber))
            )
            .andExpect(status().isOk());

        // Validate the ItemSerialNumber in the database
        List<ItemSerialNumber> itemSerialNumberList = itemSerialNumberRepository.findAll();
        assertThat(itemSerialNumberList).hasSize(databaseSizeBeforeUpdate);
        ItemSerialNumber testItemSerialNumber = itemSerialNumberList.get(itemSerialNumberList.size() - 1);
        assertThat(testItemSerialNumber.getItemId()).isEqualTo(DEFAULT_ITEM_ID);
        assertThat(testItemSerialNumber.getLocationId()).isEqualTo(UPDATED_LOCATION_ID);
        assertThat(testItemSerialNumber.getSerialNumber()).isEqualTo(UPDATED_SERIAL_NUMBER);
        assertThat(testItemSerialNumber.getAssetNumber()).isEqualTo(DEFAULT_ASSET_NUMBER);
        assertThat(testItemSerialNumber.getOnHandStatus()).isEqualTo(DEFAULT_ON_HAND_STATUS);
        assertThat(testItemSerialNumber.getPurchaseDate()).isEqualTo(DEFAULT_PURCHASE_DATE);
        assertThat(testItemSerialNumber.getSaleDate()).isEqualTo(DEFAULT_SALE_DATE);
        assertThat(testItemSerialNumber.getDepreciationStatus()).isEqualTo(UPDATED_DEPRECIATION_STATUS);
        assertThat(testItemSerialNumber.getUsefulLifeInYears()).isEqualTo(DEFAULT_USEFUL_LIFE_IN_YEARS);
        assertThat(testItemSerialNumber.getStartDepreciationDate()).isEqualTo(DEFAULT_START_DEPRECIATION_DATE);
        assertThat(testItemSerialNumber.getOriginalCost()).isEqualTo(UPDATED_ORIGINAL_COST);
        assertThat(testItemSerialNumber.getBookValue()).isEqualTo(UPDATED_BOOK_VALUE);
        assertThat(testItemSerialNumber.getUserValue1()).isEqualTo(DEFAULT_USER_VALUE_1);
        assertThat(testItemSerialNumber.getUserValue2()).isEqualTo(UPDATED_USER_VALUE_2);
        assertThat(testItemSerialNumber.getUserValue3()).isEqualTo(UPDATED_USER_VALUE_3);
        assertThat(testItemSerialNumber.getUserValue4()).isEqualTo(UPDATED_USER_VALUE_4);
        assertThat(testItemSerialNumber.getLotBatchNo()).isEqualTo(DEFAULT_LOT_BATCH_NO);
        assertThat(testItemSerialNumber.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testItemSerialNumber.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testItemSerialNumber.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testItemSerialNumber.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testItemSerialNumber.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testItemSerialNumber.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testItemSerialNumber.getItemSerialNumberUuid()).isEqualTo(UPDATED_ITEM_SERIAL_NUMBER_UUID);
        assertThat(testItemSerialNumber.getItemNo()).isEqualTo(DEFAULT_ITEM_NO);
        assertThat(testItemSerialNumber.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testItemSerialNumber.getLocationName()).isEqualTo(UPDATED_LOCATION_NAME);
        assertThat(testItemSerialNumber.getOnRentStatus()).isEqualTo(DEFAULT_ON_RENT_STATUS);
        assertThat(testItemSerialNumber.getLotBatchDate()).isEqualTo(UPDATED_LOT_BATCH_DATE);
        assertThat(testItemSerialNumber.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testItemSerialNumber.getLotNo()).isEqualTo(DEFAULT_LOT_NO);
        assertThat(testItemSerialNumber.getMfgDate()).isEqualTo(DEFAULT_MFG_DATE);
        assertThat(testItemSerialNumber.getPoId()).isEqualTo(UPDATED_PO_ID);
        assertThat(testItemSerialNumber.getPoNo()).isEqualTo(UPDATED_PO_NO);
        assertThat(testItemSerialNumber.getAdjustmentId()).isEqualTo(DEFAULT_ADJUSTMENT_ID);
        assertThat(testItemSerialNumber.getAdjustmentNo()).isEqualTo(UPDATED_ADJUSTMENT_NO);
        assertThat(testItemSerialNumber.getIsDropship()).isEqualTo(UPDATED_IS_DROPSHIP);
    }

    @Test
    @Transactional
    void fullUpdateItemSerialNumberWithPatch() throws Exception {
        // Initialize the database
        itemSerialNumberRepository.saveAndFlush(itemSerialNumber);

        int databaseSizeBeforeUpdate = itemSerialNumberRepository.findAll().size();

        // Update the itemSerialNumber using partial update
        ItemSerialNumber partialUpdatedItemSerialNumber = new ItemSerialNumber();
        partialUpdatedItemSerialNumber.setItemSerialNumberId(itemSerialNumber.getItemSerialNumberId());

        partialUpdatedItemSerialNumber
            .itemId(UPDATED_ITEM_ID)
            .locationId(UPDATED_LOCATION_ID)
            .serialNumber(UPDATED_SERIAL_NUMBER)
            .assetNumber(UPDATED_ASSET_NUMBER)
            .onHandStatus(UPDATED_ON_HAND_STATUS)
            .purchaseDate(UPDATED_PURCHASE_DATE)
            .saleDate(UPDATED_SALE_DATE)
            .depreciationStatus(UPDATED_DEPRECIATION_STATUS)
            .usefulLifeInYears(UPDATED_USEFUL_LIFE_IN_YEARS)
            .startDepreciationDate(UPDATED_START_DEPRECIATION_DATE)
            .originalCost(UPDATED_ORIGINAL_COST)
            .bookValue(UPDATED_BOOK_VALUE)
            .userValue1(UPDATED_USER_VALUE_1)
            .userValue2(UPDATED_USER_VALUE_2)
            .userValue3(UPDATED_USER_VALUE_3)
            .userValue4(UPDATED_USER_VALUE_4)
            .lotBatchNo(UPDATED_LOT_BATCH_NO)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .itemSerialNumberUuid(UPDATED_ITEM_SERIAL_NUMBER_UUID)
            .itemNo(UPDATED_ITEM_NO)
            .itemName(UPDATED_ITEM_NAME)
            .locationName(UPDATED_LOCATION_NAME)
            .onRentStatus(UPDATED_ON_RENT_STATUS)
            .lotBatchDate(UPDATED_LOT_BATCH_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .lotNo(UPDATED_LOT_NO)
            .mfgDate(UPDATED_MFG_DATE)
            .poId(UPDATED_PO_ID)
            .poNo(UPDATED_PO_NO)
            .adjustmentId(UPDATED_ADJUSTMENT_ID)
            .adjustmentNo(UPDATED_ADJUSTMENT_NO)
            .isDropship(UPDATED_IS_DROPSHIP);

        restItemSerialNumberMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedItemSerialNumber.getItemSerialNumberId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedItemSerialNumber))
            )
            .andExpect(status().isOk());

        // Validate the ItemSerialNumber in the database
        List<ItemSerialNumber> itemSerialNumberList = itemSerialNumberRepository.findAll();
        assertThat(itemSerialNumberList).hasSize(databaseSizeBeforeUpdate);
        ItemSerialNumber testItemSerialNumber = itemSerialNumberList.get(itemSerialNumberList.size() - 1);
        assertThat(testItemSerialNumber.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testItemSerialNumber.getLocationId()).isEqualTo(UPDATED_LOCATION_ID);
        assertThat(testItemSerialNumber.getSerialNumber()).isEqualTo(UPDATED_SERIAL_NUMBER);
        assertThat(testItemSerialNumber.getAssetNumber()).isEqualTo(UPDATED_ASSET_NUMBER);
        assertThat(testItemSerialNumber.getOnHandStatus()).isEqualTo(UPDATED_ON_HAND_STATUS);
        assertThat(testItemSerialNumber.getPurchaseDate()).isEqualTo(UPDATED_PURCHASE_DATE);
        assertThat(testItemSerialNumber.getSaleDate()).isEqualTo(UPDATED_SALE_DATE);
        assertThat(testItemSerialNumber.getDepreciationStatus()).isEqualTo(UPDATED_DEPRECIATION_STATUS);
        assertThat(testItemSerialNumber.getUsefulLifeInYears()).isEqualTo(UPDATED_USEFUL_LIFE_IN_YEARS);
        assertThat(testItemSerialNumber.getStartDepreciationDate()).isEqualTo(UPDATED_START_DEPRECIATION_DATE);
        assertThat(testItemSerialNumber.getOriginalCost()).isEqualTo(UPDATED_ORIGINAL_COST);
        assertThat(testItemSerialNumber.getBookValue()).isEqualTo(UPDATED_BOOK_VALUE);
        assertThat(testItemSerialNumber.getUserValue1()).isEqualTo(UPDATED_USER_VALUE_1);
        assertThat(testItemSerialNumber.getUserValue2()).isEqualTo(UPDATED_USER_VALUE_2);
        assertThat(testItemSerialNumber.getUserValue3()).isEqualTo(UPDATED_USER_VALUE_3);
        assertThat(testItemSerialNumber.getUserValue4()).isEqualTo(UPDATED_USER_VALUE_4);
        assertThat(testItemSerialNumber.getLotBatchNo()).isEqualTo(UPDATED_LOT_BATCH_NO);
        assertThat(testItemSerialNumber.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testItemSerialNumber.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testItemSerialNumber.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testItemSerialNumber.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testItemSerialNumber.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testItemSerialNumber.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testItemSerialNumber.getItemSerialNumberUuid()).isEqualTo(UPDATED_ITEM_SERIAL_NUMBER_UUID);
        assertThat(testItemSerialNumber.getItemNo()).isEqualTo(UPDATED_ITEM_NO);
        assertThat(testItemSerialNumber.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testItemSerialNumber.getLocationName()).isEqualTo(UPDATED_LOCATION_NAME);
        assertThat(testItemSerialNumber.getOnRentStatus()).isEqualTo(UPDATED_ON_RENT_STATUS);
        assertThat(testItemSerialNumber.getLotBatchDate()).isEqualTo(UPDATED_LOT_BATCH_DATE);
        assertThat(testItemSerialNumber.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testItemSerialNumber.getLotNo()).isEqualTo(UPDATED_LOT_NO);
        assertThat(testItemSerialNumber.getMfgDate()).isEqualTo(UPDATED_MFG_DATE);
        assertThat(testItemSerialNumber.getPoId()).isEqualTo(UPDATED_PO_ID);
        assertThat(testItemSerialNumber.getPoNo()).isEqualTo(UPDATED_PO_NO);
        assertThat(testItemSerialNumber.getAdjustmentId()).isEqualTo(UPDATED_ADJUSTMENT_ID);
        assertThat(testItemSerialNumber.getAdjustmentNo()).isEqualTo(UPDATED_ADJUSTMENT_NO);
        assertThat(testItemSerialNumber.getIsDropship()).isEqualTo(UPDATED_IS_DROPSHIP);
    }

    @Test
    @Transactional
    void patchNonExistingItemSerialNumber() throws Exception {
        int databaseSizeBeforeUpdate = itemSerialNumberRepository.findAll().size();
        itemSerialNumber.setItemSerialNumberId(count.incrementAndGet());

        // Create the ItemSerialNumber
        ItemSerialNumberDTO itemSerialNumberDTO = itemSerialNumberMapper.toDto(itemSerialNumber);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemSerialNumberMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, itemSerialNumberDTO.getItemSerialNumberId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemSerialNumberDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemSerialNumber in the database
        List<ItemSerialNumber> itemSerialNumberList = itemSerialNumberRepository.findAll();
        assertThat(itemSerialNumberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchItemSerialNumber() throws Exception {
        int databaseSizeBeforeUpdate = itemSerialNumberRepository.findAll().size();
        itemSerialNumber.setItemSerialNumberId(count.incrementAndGet());

        // Create the ItemSerialNumber
        ItemSerialNumberDTO itemSerialNumberDTO = itemSerialNumberMapper.toDto(itemSerialNumber);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemSerialNumberMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemSerialNumberDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemSerialNumber in the database
        List<ItemSerialNumber> itemSerialNumberList = itemSerialNumberRepository.findAll();
        assertThat(itemSerialNumberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamItemSerialNumber() throws Exception {
        int databaseSizeBeforeUpdate = itemSerialNumberRepository.findAll().size();
        itemSerialNumber.setItemSerialNumberId(count.incrementAndGet());

        // Create the ItemSerialNumber
        ItemSerialNumberDTO itemSerialNumberDTO = itemSerialNumberMapper.toDto(itemSerialNumber);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemSerialNumberMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemSerialNumberDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ItemSerialNumber in the database
        List<ItemSerialNumber> itemSerialNumberList = itemSerialNumberRepository.findAll();
        assertThat(itemSerialNumberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteItemSerialNumber() throws Exception {
        // Initialize the database
        itemSerialNumberRepository.saveAndFlush(itemSerialNumber);

        int databaseSizeBeforeDelete = itemSerialNumberRepository.findAll().size();

        // Delete the itemSerialNumber
        restItemSerialNumberMockMvc
            .perform(delete(ENTITY_API_URL_ID, itemSerialNumber.getItemSerialNumberId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ItemSerialNumber> itemSerialNumberList = itemSerialNumberRepository.findAll();
        assertThat(itemSerialNumberList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
