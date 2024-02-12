package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.StockAdjustment;
import com.sunknowledge.dme.rcm.repository.StockAdjustmentRepository;
import com.sunknowledge.dme.rcm.service.dto.StockAdjustmentDTO;
import com.sunknowledge.dme.rcm.service.mapper.StockAdjustmentMapper;
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
 * Integration tests for the {@link StockAdjustmentResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class StockAdjustmentResourceIT {

    private static final LocalDate DEFAULT_ADJUSTMENT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ADJUSTMENT_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ADJUSTMENT_NO = "AAAAAAAAAA";
    private static final String UPDATED_ADJUSTMENT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_ADJUSTMENT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_ADJUSTMENT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_NOTES = "BBBBBBBBBB";

    private static final Long DEFAULT_LOCATION_ID = 1L;
    private static final Long UPDATED_LOCATION_ID = 2L;

    private static final String DEFAULT_LOCATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_ITEM_ID = 1L;
    private static final Long UPDATED_ITEM_ID = 2L;

    private static final String DEFAULT_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_NO = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NO = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_UOM = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_UOM = "BBBBBBBBBB";

    private static final Long DEFAULT_ITEM_QTY = 1L;
    private static final Long UPDATED_ITEM_QTY = 2L;

    private static final String DEFAULT_WHEATHER_SERIALIZED = "AAAAAAAAAA";
    private static final String UPDATED_WHEATHER_SERIALIZED = "BBBBBBBBBB";

    private static final String DEFAULT_WHEATHER_ASSET_TAGGED = "AAAAAAAAAA";
    private static final String UPDATED_WHEATHER_ASSET_TAGGED = "BBBBBBBBBB";

    private static final Double DEFAULT_AVG_PRICE = 1D;
    private static final Double UPDATED_AVG_PRICE = 2D;

    private static final String DEFAULT_ADJUSTMENT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_ADJUSTMENT_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final UUID DEFAULT_STOCK_ADJUSTMENT_UUID = UUID.randomUUID();
    private static final UUID UPDATED_STOCK_ADJUSTMENT_UUID = UUID.randomUUID();

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_BRANCH_ID = 1L;
    private static final Long UPDATED_BRANCH_ID = 2L;

    private static final String DEFAULT_BRANCH_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/stock-adjustments";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{stockAdjustmentId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private StockAdjustmentRepository stockAdjustmentRepository;

    @Autowired
    private StockAdjustmentMapper stockAdjustmentMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStockAdjustmentMockMvc;

    private StockAdjustment stockAdjustment;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StockAdjustment createEntity(EntityManager em) {
        StockAdjustment stockAdjustment = new StockAdjustment()
            .adjustmentDate(DEFAULT_ADJUSTMENT_DATE)
            .adjustmentNo(DEFAULT_ADJUSTMENT_NO)
            .adjustmentType(DEFAULT_ADJUSTMENT_TYPE)
            .notes(DEFAULT_NOTES)
            .locationId(DEFAULT_LOCATION_ID)
            .locationName(DEFAULT_LOCATION_NAME)
            .itemId(DEFAULT_ITEM_ID)
            .itemName(DEFAULT_ITEM_NAME)
            .itemNo(DEFAULT_ITEM_NO)
            .itemUom(DEFAULT_ITEM_UOM)
            .itemQty(DEFAULT_ITEM_QTY)
            .wheatherSerialized(DEFAULT_WHEATHER_SERIALIZED)
            .wheatherAssetTagged(DEFAULT_WHEATHER_ASSET_TAGGED)
            .avgPrice(DEFAULT_AVG_PRICE)
            .adjustmentStatus(DEFAULT_ADJUSTMENT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .stockAdjustmentUuid(DEFAULT_STOCK_ADJUSTMENT_UUID)
            .status(DEFAULT_STATUS)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .branchId(DEFAULT_BRANCH_ID)
            .branchName(DEFAULT_BRANCH_NAME);
        return stockAdjustment;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StockAdjustment createUpdatedEntity(EntityManager em) {
        StockAdjustment stockAdjustment = new StockAdjustment()
            .adjustmentDate(UPDATED_ADJUSTMENT_DATE)
            .adjustmentNo(UPDATED_ADJUSTMENT_NO)
            .adjustmentType(UPDATED_ADJUSTMENT_TYPE)
            .notes(UPDATED_NOTES)
            .locationId(UPDATED_LOCATION_ID)
            .locationName(UPDATED_LOCATION_NAME)
            .itemId(UPDATED_ITEM_ID)
            .itemName(UPDATED_ITEM_NAME)
            .itemNo(UPDATED_ITEM_NO)
            .itemUom(UPDATED_ITEM_UOM)
            .itemQty(UPDATED_ITEM_QTY)
            .wheatherSerialized(UPDATED_WHEATHER_SERIALIZED)
            .wheatherAssetTagged(UPDATED_WHEATHER_ASSET_TAGGED)
            .avgPrice(UPDATED_AVG_PRICE)
            .adjustmentStatus(UPDATED_ADJUSTMENT_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .stockAdjustmentUuid(UPDATED_STOCK_ADJUSTMENT_UUID)
            .status(UPDATED_STATUS)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .branchId(UPDATED_BRANCH_ID)
            .branchName(UPDATED_BRANCH_NAME);
        return stockAdjustment;
    }

    @BeforeEach
    public void initTest() {
        stockAdjustment = createEntity(em);
    }

    @Test
    @Transactional
    void createStockAdjustment() throws Exception {
        int databaseSizeBeforeCreate = stockAdjustmentRepository.findAll().size();
        // Create the StockAdjustment
        StockAdjustmentDTO stockAdjustmentDTO = stockAdjustmentMapper.toDto(stockAdjustment);
        restStockAdjustmentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(stockAdjustmentDTO))
            )
            .andExpect(status().isCreated());

        // Validate the StockAdjustment in the database
        List<StockAdjustment> stockAdjustmentList = stockAdjustmentRepository.findAll();
        assertThat(stockAdjustmentList).hasSize(databaseSizeBeforeCreate + 1);
        StockAdjustment testStockAdjustment = stockAdjustmentList.get(stockAdjustmentList.size() - 1);
        assertThat(testStockAdjustment.getAdjustmentDate()).isEqualTo(DEFAULT_ADJUSTMENT_DATE);
        assertThat(testStockAdjustment.getAdjustmentNo()).isEqualTo(DEFAULT_ADJUSTMENT_NO);
        assertThat(testStockAdjustment.getAdjustmentType()).isEqualTo(DEFAULT_ADJUSTMENT_TYPE);
        assertThat(testStockAdjustment.getNotes()).isEqualTo(DEFAULT_NOTES);
        assertThat(testStockAdjustment.getLocationId()).isEqualTo(DEFAULT_LOCATION_ID);
        assertThat(testStockAdjustment.getLocationName()).isEqualTo(DEFAULT_LOCATION_NAME);
        assertThat(testStockAdjustment.getItemId()).isEqualTo(DEFAULT_ITEM_ID);
        assertThat(testStockAdjustment.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testStockAdjustment.getItemNo()).isEqualTo(DEFAULT_ITEM_NO);
        assertThat(testStockAdjustment.getItemUom()).isEqualTo(DEFAULT_ITEM_UOM);
        assertThat(testStockAdjustment.getItemQty()).isEqualTo(DEFAULT_ITEM_QTY);
        assertThat(testStockAdjustment.getWheatherSerialized()).isEqualTo(DEFAULT_WHEATHER_SERIALIZED);
        assertThat(testStockAdjustment.getWheatherAssetTagged()).isEqualTo(DEFAULT_WHEATHER_ASSET_TAGGED);
        assertThat(testStockAdjustment.getAvgPrice()).isEqualTo(DEFAULT_AVG_PRICE);
        assertThat(testStockAdjustment.getAdjustmentStatus()).isEqualTo(DEFAULT_ADJUSTMENT_STATUS);
        assertThat(testStockAdjustment.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testStockAdjustment.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testStockAdjustment.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testStockAdjustment.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testStockAdjustment.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testStockAdjustment.getStockAdjustmentUuid()).isEqualTo(DEFAULT_STOCK_ADJUSTMENT_UUID);
        assertThat(testStockAdjustment.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testStockAdjustment.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testStockAdjustment.getBranchId()).isEqualTo(DEFAULT_BRANCH_ID);
        assertThat(testStockAdjustment.getBranchName()).isEqualTo(DEFAULT_BRANCH_NAME);
    }

    @Test
    @Transactional
    void createStockAdjustmentWithExistingId() throws Exception {
        // Create the StockAdjustment with an existing ID
        stockAdjustment.setStockAdjustmentId(1L);
        StockAdjustmentDTO stockAdjustmentDTO = stockAdjustmentMapper.toDto(stockAdjustment);

        int databaseSizeBeforeCreate = stockAdjustmentRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restStockAdjustmentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(stockAdjustmentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the StockAdjustment in the database
        List<StockAdjustment> stockAdjustmentList = stockAdjustmentRepository.findAll();
        assertThat(stockAdjustmentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllStockAdjustments() throws Exception {
        // Initialize the database
        stockAdjustmentRepository.saveAndFlush(stockAdjustment);

        // Get all the stockAdjustmentList
        restStockAdjustmentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=stockAdjustmentId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].stockAdjustmentId").value(hasItem(stockAdjustment.getStockAdjustmentId().intValue())))
            .andExpect(jsonPath("$.[*].adjustmentDate").value(hasItem(DEFAULT_ADJUSTMENT_DATE.toString())))
            .andExpect(jsonPath("$.[*].adjustmentNo").value(hasItem(DEFAULT_ADJUSTMENT_NO)))
            .andExpect(jsonPath("$.[*].adjustmentType").value(hasItem(DEFAULT_ADJUSTMENT_TYPE)))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES)))
            .andExpect(jsonPath("$.[*].locationId").value(hasItem(DEFAULT_LOCATION_ID.intValue())))
            .andExpect(jsonPath("$.[*].locationName").value(hasItem(DEFAULT_LOCATION_NAME)))
            .andExpect(jsonPath("$.[*].itemId").value(hasItem(DEFAULT_ITEM_ID.intValue())))
            .andExpect(jsonPath("$.[*].itemName").value(hasItem(DEFAULT_ITEM_NAME)))
            .andExpect(jsonPath("$.[*].itemNo").value(hasItem(DEFAULT_ITEM_NO)))
            .andExpect(jsonPath("$.[*].itemUom").value(hasItem(DEFAULT_ITEM_UOM)))
            .andExpect(jsonPath("$.[*].itemQty").value(hasItem(DEFAULT_ITEM_QTY.intValue())))
            .andExpect(jsonPath("$.[*].wheatherSerialized").value(hasItem(DEFAULT_WHEATHER_SERIALIZED)))
            .andExpect(jsonPath("$.[*].wheatherAssetTagged").value(hasItem(DEFAULT_WHEATHER_ASSET_TAGGED)))
            .andExpect(jsonPath("$.[*].avgPrice").value(hasItem(DEFAULT_AVG_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].adjustmentStatus").value(hasItem(DEFAULT_ADJUSTMENT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].stockAdjustmentUuid").value(hasItem(DEFAULT_STOCK_ADJUSTMENT_UUID.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].branchId").value(hasItem(DEFAULT_BRANCH_ID.intValue())))
            .andExpect(jsonPath("$.[*].branchName").value(hasItem(DEFAULT_BRANCH_NAME)));
    }

    @Test
    @Transactional
    void getStockAdjustment() throws Exception {
        // Initialize the database
        stockAdjustmentRepository.saveAndFlush(stockAdjustment);

        // Get the stockAdjustment
        restStockAdjustmentMockMvc
            .perform(get(ENTITY_API_URL_ID, stockAdjustment.getStockAdjustmentId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.stockAdjustmentId").value(stockAdjustment.getStockAdjustmentId().intValue()))
            .andExpect(jsonPath("$.adjustmentDate").value(DEFAULT_ADJUSTMENT_DATE.toString()))
            .andExpect(jsonPath("$.adjustmentNo").value(DEFAULT_ADJUSTMENT_NO))
            .andExpect(jsonPath("$.adjustmentType").value(DEFAULT_ADJUSTMENT_TYPE))
            .andExpect(jsonPath("$.notes").value(DEFAULT_NOTES))
            .andExpect(jsonPath("$.locationId").value(DEFAULT_LOCATION_ID.intValue()))
            .andExpect(jsonPath("$.locationName").value(DEFAULT_LOCATION_NAME))
            .andExpect(jsonPath("$.itemId").value(DEFAULT_ITEM_ID.intValue()))
            .andExpect(jsonPath("$.itemName").value(DEFAULT_ITEM_NAME))
            .andExpect(jsonPath("$.itemNo").value(DEFAULT_ITEM_NO))
            .andExpect(jsonPath("$.itemUom").value(DEFAULT_ITEM_UOM))
            .andExpect(jsonPath("$.itemQty").value(DEFAULT_ITEM_QTY.intValue()))
            .andExpect(jsonPath("$.wheatherSerialized").value(DEFAULT_WHEATHER_SERIALIZED))
            .andExpect(jsonPath("$.wheatherAssetTagged").value(DEFAULT_WHEATHER_ASSET_TAGGED))
            .andExpect(jsonPath("$.avgPrice").value(DEFAULT_AVG_PRICE.doubleValue()))
            .andExpect(jsonPath("$.adjustmentStatus").value(DEFAULT_ADJUSTMENT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.stockAdjustmentUuid").value(DEFAULT_STOCK_ADJUSTMENT_UUID.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.branchId").value(DEFAULT_BRANCH_ID.intValue()))
            .andExpect(jsonPath("$.branchName").value(DEFAULT_BRANCH_NAME));
    }

    @Test
    @Transactional
    void getNonExistingStockAdjustment() throws Exception {
        // Get the stockAdjustment
        restStockAdjustmentMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewStockAdjustment() throws Exception {
        // Initialize the database
        stockAdjustmentRepository.saveAndFlush(stockAdjustment);

        int databaseSizeBeforeUpdate = stockAdjustmentRepository.findAll().size();

        // Update the stockAdjustment
        StockAdjustment updatedStockAdjustment = stockAdjustmentRepository.findById(stockAdjustment.getStockAdjustmentId()).get();
        // Disconnect from session so that the updates on updatedStockAdjustment are not directly saved in db
        em.detach(updatedStockAdjustment);
        updatedStockAdjustment
            .adjustmentDate(UPDATED_ADJUSTMENT_DATE)
            .adjustmentNo(UPDATED_ADJUSTMENT_NO)
            .adjustmentType(UPDATED_ADJUSTMENT_TYPE)
            .notes(UPDATED_NOTES)
            .locationId(UPDATED_LOCATION_ID)
            .locationName(UPDATED_LOCATION_NAME)
            .itemId(UPDATED_ITEM_ID)
            .itemName(UPDATED_ITEM_NAME)
            .itemNo(UPDATED_ITEM_NO)
            .itemUom(UPDATED_ITEM_UOM)
            .itemQty(UPDATED_ITEM_QTY)
            .wheatherSerialized(UPDATED_WHEATHER_SERIALIZED)
            .wheatherAssetTagged(UPDATED_WHEATHER_ASSET_TAGGED)
            .avgPrice(UPDATED_AVG_PRICE)
            .adjustmentStatus(UPDATED_ADJUSTMENT_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .stockAdjustmentUuid(UPDATED_STOCK_ADJUSTMENT_UUID)
            .status(UPDATED_STATUS)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .branchId(UPDATED_BRANCH_ID)
            .branchName(UPDATED_BRANCH_NAME);
        StockAdjustmentDTO stockAdjustmentDTO = stockAdjustmentMapper.toDto(updatedStockAdjustment);

        restStockAdjustmentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, stockAdjustmentDTO.getStockAdjustmentId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(stockAdjustmentDTO))
            )
            .andExpect(status().isOk());

        // Validate the StockAdjustment in the database
        List<StockAdjustment> stockAdjustmentList = stockAdjustmentRepository.findAll();
        assertThat(stockAdjustmentList).hasSize(databaseSizeBeforeUpdate);
        StockAdjustment testStockAdjustment = stockAdjustmentList.get(stockAdjustmentList.size() - 1);
        assertThat(testStockAdjustment.getAdjustmentDate()).isEqualTo(UPDATED_ADJUSTMENT_DATE);
        assertThat(testStockAdjustment.getAdjustmentNo()).isEqualTo(UPDATED_ADJUSTMENT_NO);
        assertThat(testStockAdjustment.getAdjustmentType()).isEqualTo(UPDATED_ADJUSTMENT_TYPE);
        assertThat(testStockAdjustment.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testStockAdjustment.getLocationId()).isEqualTo(UPDATED_LOCATION_ID);
        assertThat(testStockAdjustment.getLocationName()).isEqualTo(UPDATED_LOCATION_NAME);
        assertThat(testStockAdjustment.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testStockAdjustment.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testStockAdjustment.getItemNo()).isEqualTo(UPDATED_ITEM_NO);
        assertThat(testStockAdjustment.getItemUom()).isEqualTo(UPDATED_ITEM_UOM);
        assertThat(testStockAdjustment.getItemQty()).isEqualTo(UPDATED_ITEM_QTY);
        assertThat(testStockAdjustment.getWheatherSerialized()).isEqualTo(UPDATED_WHEATHER_SERIALIZED);
        assertThat(testStockAdjustment.getWheatherAssetTagged()).isEqualTo(UPDATED_WHEATHER_ASSET_TAGGED);
        assertThat(testStockAdjustment.getAvgPrice()).isEqualTo(UPDATED_AVG_PRICE);
        assertThat(testStockAdjustment.getAdjustmentStatus()).isEqualTo(UPDATED_ADJUSTMENT_STATUS);
        assertThat(testStockAdjustment.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testStockAdjustment.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testStockAdjustment.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testStockAdjustment.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testStockAdjustment.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testStockAdjustment.getStockAdjustmentUuid()).isEqualTo(UPDATED_STOCK_ADJUSTMENT_UUID);
        assertThat(testStockAdjustment.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testStockAdjustment.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testStockAdjustment.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
        assertThat(testStockAdjustment.getBranchName()).isEqualTo(UPDATED_BRANCH_NAME);
    }

    @Test
    @Transactional
    void putNonExistingStockAdjustment() throws Exception {
        int databaseSizeBeforeUpdate = stockAdjustmentRepository.findAll().size();
        stockAdjustment.setStockAdjustmentId(count.incrementAndGet());

        // Create the StockAdjustment
        StockAdjustmentDTO stockAdjustmentDTO = stockAdjustmentMapper.toDto(stockAdjustment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStockAdjustmentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, stockAdjustmentDTO.getStockAdjustmentId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(stockAdjustmentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the StockAdjustment in the database
        List<StockAdjustment> stockAdjustmentList = stockAdjustmentRepository.findAll();
        assertThat(stockAdjustmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchStockAdjustment() throws Exception {
        int databaseSizeBeforeUpdate = stockAdjustmentRepository.findAll().size();
        stockAdjustment.setStockAdjustmentId(count.incrementAndGet());

        // Create the StockAdjustment
        StockAdjustmentDTO stockAdjustmentDTO = stockAdjustmentMapper.toDto(stockAdjustment);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStockAdjustmentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(stockAdjustmentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the StockAdjustment in the database
        List<StockAdjustment> stockAdjustmentList = stockAdjustmentRepository.findAll();
        assertThat(stockAdjustmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamStockAdjustment() throws Exception {
        int databaseSizeBeforeUpdate = stockAdjustmentRepository.findAll().size();
        stockAdjustment.setStockAdjustmentId(count.incrementAndGet());

        // Create the StockAdjustment
        StockAdjustmentDTO stockAdjustmentDTO = stockAdjustmentMapper.toDto(stockAdjustment);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStockAdjustmentMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(stockAdjustmentDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the StockAdjustment in the database
        List<StockAdjustment> stockAdjustmentList = stockAdjustmentRepository.findAll();
        assertThat(stockAdjustmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateStockAdjustmentWithPatch() throws Exception {
        // Initialize the database
        stockAdjustmentRepository.saveAndFlush(stockAdjustment);

        int databaseSizeBeforeUpdate = stockAdjustmentRepository.findAll().size();

        // Update the stockAdjustment using partial update
        StockAdjustment partialUpdatedStockAdjustment = new StockAdjustment();
        partialUpdatedStockAdjustment.setStockAdjustmentId(stockAdjustment.getStockAdjustmentId());

        partialUpdatedStockAdjustment
            .locationName(UPDATED_LOCATION_NAME)
            .itemName(UPDATED_ITEM_NAME)
            .itemNo(UPDATED_ITEM_NO)
            .wheatherSerialized(UPDATED_WHEATHER_SERIALIZED)
            .avgPrice(UPDATED_AVG_PRICE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedByName(UPDATED_UPDATED_BY_NAME);

        restStockAdjustmentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStockAdjustment.getStockAdjustmentId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedStockAdjustment))
            )
            .andExpect(status().isOk());

        // Validate the StockAdjustment in the database
        List<StockAdjustment> stockAdjustmentList = stockAdjustmentRepository.findAll();
        assertThat(stockAdjustmentList).hasSize(databaseSizeBeforeUpdate);
        StockAdjustment testStockAdjustment = stockAdjustmentList.get(stockAdjustmentList.size() - 1);
        assertThat(testStockAdjustment.getAdjustmentDate()).isEqualTo(DEFAULT_ADJUSTMENT_DATE);
        assertThat(testStockAdjustment.getAdjustmentNo()).isEqualTo(DEFAULT_ADJUSTMENT_NO);
        assertThat(testStockAdjustment.getAdjustmentType()).isEqualTo(DEFAULT_ADJUSTMENT_TYPE);
        assertThat(testStockAdjustment.getNotes()).isEqualTo(DEFAULT_NOTES);
        assertThat(testStockAdjustment.getLocationId()).isEqualTo(DEFAULT_LOCATION_ID);
        assertThat(testStockAdjustment.getLocationName()).isEqualTo(UPDATED_LOCATION_NAME);
        assertThat(testStockAdjustment.getItemId()).isEqualTo(DEFAULT_ITEM_ID);
        assertThat(testStockAdjustment.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testStockAdjustment.getItemNo()).isEqualTo(UPDATED_ITEM_NO);
        assertThat(testStockAdjustment.getItemUom()).isEqualTo(DEFAULT_ITEM_UOM);
        assertThat(testStockAdjustment.getItemQty()).isEqualTo(DEFAULT_ITEM_QTY);
        assertThat(testStockAdjustment.getWheatherSerialized()).isEqualTo(UPDATED_WHEATHER_SERIALIZED);
        assertThat(testStockAdjustment.getWheatherAssetTagged()).isEqualTo(DEFAULT_WHEATHER_ASSET_TAGGED);
        assertThat(testStockAdjustment.getAvgPrice()).isEqualTo(UPDATED_AVG_PRICE);
        assertThat(testStockAdjustment.getAdjustmentStatus()).isEqualTo(DEFAULT_ADJUSTMENT_STATUS);
        assertThat(testStockAdjustment.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testStockAdjustment.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testStockAdjustment.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testStockAdjustment.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testStockAdjustment.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testStockAdjustment.getStockAdjustmentUuid()).isEqualTo(DEFAULT_STOCK_ADJUSTMENT_UUID);
        assertThat(testStockAdjustment.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testStockAdjustment.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testStockAdjustment.getBranchId()).isEqualTo(DEFAULT_BRANCH_ID);
        assertThat(testStockAdjustment.getBranchName()).isEqualTo(DEFAULT_BRANCH_NAME);
    }

    @Test
    @Transactional
    void fullUpdateStockAdjustmentWithPatch() throws Exception {
        // Initialize the database
        stockAdjustmentRepository.saveAndFlush(stockAdjustment);

        int databaseSizeBeforeUpdate = stockAdjustmentRepository.findAll().size();

        // Update the stockAdjustment using partial update
        StockAdjustment partialUpdatedStockAdjustment = new StockAdjustment();
        partialUpdatedStockAdjustment.setStockAdjustmentId(stockAdjustment.getStockAdjustmentId());

        partialUpdatedStockAdjustment
            .adjustmentDate(UPDATED_ADJUSTMENT_DATE)
            .adjustmentNo(UPDATED_ADJUSTMENT_NO)
            .adjustmentType(UPDATED_ADJUSTMENT_TYPE)
            .notes(UPDATED_NOTES)
            .locationId(UPDATED_LOCATION_ID)
            .locationName(UPDATED_LOCATION_NAME)
            .itemId(UPDATED_ITEM_ID)
            .itemName(UPDATED_ITEM_NAME)
            .itemNo(UPDATED_ITEM_NO)
            .itemUom(UPDATED_ITEM_UOM)
            .itemQty(UPDATED_ITEM_QTY)
            .wheatherSerialized(UPDATED_WHEATHER_SERIALIZED)
            .wheatherAssetTagged(UPDATED_WHEATHER_ASSET_TAGGED)
            .avgPrice(UPDATED_AVG_PRICE)
            .adjustmentStatus(UPDATED_ADJUSTMENT_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .stockAdjustmentUuid(UPDATED_STOCK_ADJUSTMENT_UUID)
            .status(UPDATED_STATUS)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .branchId(UPDATED_BRANCH_ID)
            .branchName(UPDATED_BRANCH_NAME);

        restStockAdjustmentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStockAdjustment.getStockAdjustmentId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedStockAdjustment))
            )
            .andExpect(status().isOk());

        // Validate the StockAdjustment in the database
        List<StockAdjustment> stockAdjustmentList = stockAdjustmentRepository.findAll();
        assertThat(stockAdjustmentList).hasSize(databaseSizeBeforeUpdate);
        StockAdjustment testStockAdjustment = stockAdjustmentList.get(stockAdjustmentList.size() - 1);
        assertThat(testStockAdjustment.getAdjustmentDate()).isEqualTo(UPDATED_ADJUSTMENT_DATE);
        assertThat(testStockAdjustment.getAdjustmentNo()).isEqualTo(UPDATED_ADJUSTMENT_NO);
        assertThat(testStockAdjustment.getAdjustmentType()).isEqualTo(UPDATED_ADJUSTMENT_TYPE);
        assertThat(testStockAdjustment.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testStockAdjustment.getLocationId()).isEqualTo(UPDATED_LOCATION_ID);
        assertThat(testStockAdjustment.getLocationName()).isEqualTo(UPDATED_LOCATION_NAME);
        assertThat(testStockAdjustment.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testStockAdjustment.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testStockAdjustment.getItemNo()).isEqualTo(UPDATED_ITEM_NO);
        assertThat(testStockAdjustment.getItemUom()).isEqualTo(UPDATED_ITEM_UOM);
        assertThat(testStockAdjustment.getItemQty()).isEqualTo(UPDATED_ITEM_QTY);
        assertThat(testStockAdjustment.getWheatherSerialized()).isEqualTo(UPDATED_WHEATHER_SERIALIZED);
        assertThat(testStockAdjustment.getWheatherAssetTagged()).isEqualTo(UPDATED_WHEATHER_ASSET_TAGGED);
        assertThat(testStockAdjustment.getAvgPrice()).isEqualTo(UPDATED_AVG_PRICE);
        assertThat(testStockAdjustment.getAdjustmentStatus()).isEqualTo(UPDATED_ADJUSTMENT_STATUS);
        assertThat(testStockAdjustment.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testStockAdjustment.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testStockAdjustment.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testStockAdjustment.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testStockAdjustment.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testStockAdjustment.getStockAdjustmentUuid()).isEqualTo(UPDATED_STOCK_ADJUSTMENT_UUID);
        assertThat(testStockAdjustment.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testStockAdjustment.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testStockAdjustment.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
        assertThat(testStockAdjustment.getBranchName()).isEqualTo(UPDATED_BRANCH_NAME);
    }

    @Test
    @Transactional
    void patchNonExistingStockAdjustment() throws Exception {
        int databaseSizeBeforeUpdate = stockAdjustmentRepository.findAll().size();
        stockAdjustment.setStockAdjustmentId(count.incrementAndGet());

        // Create the StockAdjustment
        StockAdjustmentDTO stockAdjustmentDTO = stockAdjustmentMapper.toDto(stockAdjustment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStockAdjustmentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, stockAdjustmentDTO.getStockAdjustmentId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(stockAdjustmentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the StockAdjustment in the database
        List<StockAdjustment> stockAdjustmentList = stockAdjustmentRepository.findAll();
        assertThat(stockAdjustmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchStockAdjustment() throws Exception {
        int databaseSizeBeforeUpdate = stockAdjustmentRepository.findAll().size();
        stockAdjustment.setStockAdjustmentId(count.incrementAndGet());

        // Create the StockAdjustment
        StockAdjustmentDTO stockAdjustmentDTO = stockAdjustmentMapper.toDto(stockAdjustment);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStockAdjustmentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(stockAdjustmentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the StockAdjustment in the database
        List<StockAdjustment> stockAdjustmentList = stockAdjustmentRepository.findAll();
        assertThat(stockAdjustmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamStockAdjustment() throws Exception {
        int databaseSizeBeforeUpdate = stockAdjustmentRepository.findAll().size();
        stockAdjustment.setStockAdjustmentId(count.incrementAndGet());

        // Create the StockAdjustment
        StockAdjustmentDTO stockAdjustmentDTO = stockAdjustmentMapper.toDto(stockAdjustment);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStockAdjustmentMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(stockAdjustmentDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the StockAdjustment in the database
        List<StockAdjustment> stockAdjustmentList = stockAdjustmentRepository.findAll();
        assertThat(stockAdjustmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteStockAdjustment() throws Exception {
        // Initialize the database
        stockAdjustmentRepository.saveAndFlush(stockAdjustment);

        int databaseSizeBeforeDelete = stockAdjustmentRepository.findAll().size();

        // Delete the stockAdjustment
        restStockAdjustmentMockMvc
            .perform(delete(ENTITY_API_URL_ID, stockAdjustment.getStockAdjustmentId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<StockAdjustment> stockAdjustmentList = stockAdjustmentRepository.findAll();
        assertThat(stockAdjustmentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
