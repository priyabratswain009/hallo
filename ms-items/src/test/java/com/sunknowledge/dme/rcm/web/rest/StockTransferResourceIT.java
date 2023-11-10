package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.StockTransfer;
import com.sunknowledge.dme.rcm.repository.StockTransferRepository;
import com.sunknowledge.dme.rcm.service.dto.StockTransferDTO;
import com.sunknowledge.dme.rcm.service.mapper.StockTransferMapper;
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
 * Integration tests for the {@link StockTransferResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class StockTransferResourceIT {

    private static final Long DEFAULT_SOURCE_LOACTION_ID = 1L;
    private static final Long UPDATED_SOURCE_LOACTION_ID = 2L;

    private static final String DEFAULT_SOURCE_LOACTION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_LOACTION_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_TRANSFER_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TRANSFER_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_DESTINATION_LOCATION_ID = 1L;
    private static final Long UPDATED_DESTINATION_LOCATION_ID = 2L;

    private static final String DEFAULT_DESTINATION_LOCATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DESTINATION_LOCATION_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_ITEM_ID = 1L;
    private static final Long UPDATED_ITEM_ID = 2L;

    private static final String DEFAULT_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_UOM = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_UOM = "BBBBBBBBBB";

    private static final Long DEFAULT_ITEM_QTY = 1L;
    private static final Long UPDATED_ITEM_QTY = 2L;

    private static final String DEFAULT_ITEM_NO = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NO = "BBBBBBBBBB";

    private static final String DEFAULT_WHEATHER_SERIALIZED = "AAAAAAAAAA";
    private static final String UPDATED_WHEATHER_SERIALIZED = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSFER_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_TRANSFER_STATUS = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_STOCK_TRANSFER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_STOCK_TRANSFER_UUID = UUID.randomUUID();

    private static final String DEFAULT_TRANSFER_NO = "AAAAAAAAAA";
    private static final String UPDATED_TRANSFER_NO = "BBBBBBBBBB";

    private static final String DEFAULT_SERIAL_NO = "AAAAAAAAAA";
    private static final String UPDATED_SERIAL_NO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_RECEIVED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_RECEIVED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_RECEIVED_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_RECEIVED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_BRANCH_ID = 1L;
    private static final Long UPDATED_BRANCH_ID = 2L;

    private static final String DEFAULT_BRANCH_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/stock-transfers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{stockTransferId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private StockTransferRepository stockTransferRepository;

    @Autowired
    private StockTransferMapper stockTransferMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStockTransferMockMvc;

    private StockTransfer stockTransfer;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StockTransfer createEntity(EntityManager em) {
        StockTransfer stockTransfer = new StockTransfer()
            .sourceLoactionId(DEFAULT_SOURCE_LOACTION_ID)
            .sourceLoactionName(DEFAULT_SOURCE_LOACTION_NAME)
            .transferDate(DEFAULT_TRANSFER_DATE)
            .destinationLocationId(DEFAULT_DESTINATION_LOCATION_ID)
            .destinationLocationName(DEFAULT_DESTINATION_LOCATION_NAME)
            .itemId(DEFAULT_ITEM_ID)
            .itemName(DEFAULT_ITEM_NAME)
            .itemUom(DEFAULT_ITEM_UOM)
            .itemQty(DEFAULT_ITEM_QTY)
            .itemNo(DEFAULT_ITEM_NO)
            .wheatherSerialized(DEFAULT_WHEATHER_SERIALIZED)
            .transferStatus(DEFAULT_TRANSFER_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .stockTransferUuid(DEFAULT_STOCK_TRANSFER_UUID)
            .transferNo(DEFAULT_TRANSFER_NO)
            .serialNo(DEFAULT_SERIAL_NO)
            .receivedDate(DEFAULT_RECEIVED_DATE)
            .receivedStatus(DEFAULT_RECEIVED_STATUS)
            .status(DEFAULT_STATUS)
            .branchId(DEFAULT_BRANCH_ID)
            .branchName(DEFAULT_BRANCH_NAME);
        return stockTransfer;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StockTransfer createUpdatedEntity(EntityManager em) {
        StockTransfer stockTransfer = new StockTransfer()
            .sourceLoactionId(UPDATED_SOURCE_LOACTION_ID)
            .sourceLoactionName(UPDATED_SOURCE_LOACTION_NAME)
            .transferDate(UPDATED_TRANSFER_DATE)
            .destinationLocationId(UPDATED_DESTINATION_LOCATION_ID)
            .destinationLocationName(UPDATED_DESTINATION_LOCATION_NAME)
            .itemId(UPDATED_ITEM_ID)
            .itemName(UPDATED_ITEM_NAME)
            .itemUom(UPDATED_ITEM_UOM)
            .itemQty(UPDATED_ITEM_QTY)
            .itemNo(UPDATED_ITEM_NO)
            .wheatherSerialized(UPDATED_WHEATHER_SERIALIZED)
            .transferStatus(UPDATED_TRANSFER_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .stockTransferUuid(UPDATED_STOCK_TRANSFER_UUID)
            .transferNo(UPDATED_TRANSFER_NO)
            .serialNo(UPDATED_SERIAL_NO)
            .receivedDate(UPDATED_RECEIVED_DATE)
            .receivedStatus(UPDATED_RECEIVED_STATUS)
            .status(UPDATED_STATUS)
            .branchId(UPDATED_BRANCH_ID)
            .branchName(UPDATED_BRANCH_NAME);
        return stockTransfer;
    }

    @BeforeEach
    public void initTest() {
        stockTransfer = createEntity(em);
    }

    @Test
    @Transactional
    void createStockTransfer() throws Exception {
        int databaseSizeBeforeCreate = stockTransferRepository.findAll().size();
        // Create the StockTransfer
        StockTransferDTO stockTransferDTO = stockTransferMapper.toDto(stockTransfer);
        restStockTransferMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(stockTransferDTO))
            )
            .andExpect(status().isCreated());

        // Validate the StockTransfer in the database
        List<StockTransfer> stockTransferList = stockTransferRepository.findAll();
        assertThat(stockTransferList).hasSize(databaseSizeBeforeCreate + 1);
        StockTransfer testStockTransfer = stockTransferList.get(stockTransferList.size() - 1);
        assertThat(testStockTransfer.getSourceLoactionId()).isEqualTo(DEFAULT_SOURCE_LOACTION_ID);
        assertThat(testStockTransfer.getSourceLoactionName()).isEqualTo(DEFAULT_SOURCE_LOACTION_NAME);
        assertThat(testStockTransfer.getTransferDate()).isEqualTo(DEFAULT_TRANSFER_DATE);
        assertThat(testStockTransfer.getDestinationLocationId()).isEqualTo(DEFAULT_DESTINATION_LOCATION_ID);
        assertThat(testStockTransfer.getDestinationLocationName()).isEqualTo(DEFAULT_DESTINATION_LOCATION_NAME);
        assertThat(testStockTransfer.getItemId()).isEqualTo(DEFAULT_ITEM_ID);
        assertThat(testStockTransfer.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testStockTransfer.getItemUom()).isEqualTo(DEFAULT_ITEM_UOM);
        assertThat(testStockTransfer.getItemQty()).isEqualTo(DEFAULT_ITEM_QTY);
        assertThat(testStockTransfer.getItemNo()).isEqualTo(DEFAULT_ITEM_NO);
        assertThat(testStockTransfer.getWheatherSerialized()).isEqualTo(DEFAULT_WHEATHER_SERIALIZED);
        assertThat(testStockTransfer.getTransferStatus()).isEqualTo(DEFAULT_TRANSFER_STATUS);
        assertThat(testStockTransfer.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testStockTransfer.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testStockTransfer.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testStockTransfer.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testStockTransfer.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testStockTransfer.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testStockTransfer.getStockTransferUuid()).isEqualTo(DEFAULT_STOCK_TRANSFER_UUID);
        assertThat(testStockTransfer.getTransferNo()).isEqualTo(DEFAULT_TRANSFER_NO);
        assertThat(testStockTransfer.getSerialNo()).isEqualTo(DEFAULT_SERIAL_NO);
        assertThat(testStockTransfer.getReceivedDate()).isEqualTo(DEFAULT_RECEIVED_DATE);
        assertThat(testStockTransfer.getReceivedStatus()).isEqualTo(DEFAULT_RECEIVED_STATUS);
        assertThat(testStockTransfer.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testStockTransfer.getBranchId()).isEqualTo(DEFAULT_BRANCH_ID);
        assertThat(testStockTransfer.getBranchName()).isEqualTo(DEFAULT_BRANCH_NAME);
    }

    @Test
    @Transactional
    void createStockTransferWithExistingId() throws Exception {
        // Create the StockTransfer with an existing ID
        stockTransfer.setStockTransferId(1L);
        StockTransferDTO stockTransferDTO = stockTransferMapper.toDto(stockTransfer);

        int databaseSizeBeforeCreate = stockTransferRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restStockTransferMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(stockTransferDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the StockTransfer in the database
        List<StockTransfer> stockTransferList = stockTransferRepository.findAll();
        assertThat(stockTransferList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllStockTransfers() throws Exception {
        // Initialize the database
        stockTransferRepository.saveAndFlush(stockTransfer);

        // Get all the stockTransferList
        restStockTransferMockMvc
            .perform(get(ENTITY_API_URL + "?sort=stockTransferId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].stockTransferId").value(hasItem(stockTransfer.getStockTransferId().intValue())))
            .andExpect(jsonPath("$.[*].sourceLoactionId").value(hasItem(DEFAULT_SOURCE_LOACTION_ID.intValue())))
            .andExpect(jsonPath("$.[*].sourceLoactionName").value(hasItem(DEFAULT_SOURCE_LOACTION_NAME)))
            .andExpect(jsonPath("$.[*].transferDate").value(hasItem(DEFAULT_TRANSFER_DATE.toString())))
            .andExpect(jsonPath("$.[*].destinationLocationId").value(hasItem(DEFAULT_DESTINATION_LOCATION_ID.intValue())))
            .andExpect(jsonPath("$.[*].destinationLocationName").value(hasItem(DEFAULT_DESTINATION_LOCATION_NAME)))
            .andExpect(jsonPath("$.[*].itemId").value(hasItem(DEFAULT_ITEM_ID.intValue())))
            .andExpect(jsonPath("$.[*].itemName").value(hasItem(DEFAULT_ITEM_NAME)))
            .andExpect(jsonPath("$.[*].itemUom").value(hasItem(DEFAULT_ITEM_UOM)))
            .andExpect(jsonPath("$.[*].itemQty").value(hasItem(DEFAULT_ITEM_QTY.intValue())))
            .andExpect(jsonPath("$.[*].itemNo").value(hasItem(DEFAULT_ITEM_NO)))
            .andExpect(jsonPath("$.[*].wheatherSerialized").value(hasItem(DEFAULT_WHEATHER_SERIALIZED)))
            .andExpect(jsonPath("$.[*].transferStatus").value(hasItem(DEFAULT_TRANSFER_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].stockTransferUuid").value(hasItem(DEFAULT_STOCK_TRANSFER_UUID.toString())))
            .andExpect(jsonPath("$.[*].transferNo").value(hasItem(DEFAULT_TRANSFER_NO)))
            .andExpect(jsonPath("$.[*].serialNo").value(hasItem(DEFAULT_SERIAL_NO)))
            .andExpect(jsonPath("$.[*].receivedDate").value(hasItem(DEFAULT_RECEIVED_DATE.toString())))
            .andExpect(jsonPath("$.[*].receivedStatus").value(hasItem(DEFAULT_RECEIVED_STATUS)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].branchId").value(hasItem(DEFAULT_BRANCH_ID.intValue())))
            .andExpect(jsonPath("$.[*].branchName").value(hasItem(DEFAULT_BRANCH_NAME)));
    }

    @Test
    @Transactional
    void getStockTransfer() throws Exception {
        // Initialize the database
        stockTransferRepository.saveAndFlush(stockTransfer);

        // Get the stockTransfer
        restStockTransferMockMvc
            .perform(get(ENTITY_API_URL_ID, stockTransfer.getStockTransferId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.stockTransferId").value(stockTransfer.getStockTransferId().intValue()))
            .andExpect(jsonPath("$.sourceLoactionId").value(DEFAULT_SOURCE_LOACTION_ID.intValue()))
            .andExpect(jsonPath("$.sourceLoactionName").value(DEFAULT_SOURCE_LOACTION_NAME))
            .andExpect(jsonPath("$.transferDate").value(DEFAULT_TRANSFER_DATE.toString()))
            .andExpect(jsonPath("$.destinationLocationId").value(DEFAULT_DESTINATION_LOCATION_ID.intValue()))
            .andExpect(jsonPath("$.destinationLocationName").value(DEFAULT_DESTINATION_LOCATION_NAME))
            .andExpect(jsonPath("$.itemId").value(DEFAULT_ITEM_ID.intValue()))
            .andExpect(jsonPath("$.itemName").value(DEFAULT_ITEM_NAME))
            .andExpect(jsonPath("$.itemUom").value(DEFAULT_ITEM_UOM))
            .andExpect(jsonPath("$.itemQty").value(DEFAULT_ITEM_QTY.intValue()))
            .andExpect(jsonPath("$.itemNo").value(DEFAULT_ITEM_NO))
            .andExpect(jsonPath("$.wheatherSerialized").value(DEFAULT_WHEATHER_SERIALIZED))
            .andExpect(jsonPath("$.transferStatus").value(DEFAULT_TRANSFER_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.stockTransferUuid").value(DEFAULT_STOCK_TRANSFER_UUID.toString()))
            .andExpect(jsonPath("$.transferNo").value(DEFAULT_TRANSFER_NO))
            .andExpect(jsonPath("$.serialNo").value(DEFAULT_SERIAL_NO))
            .andExpect(jsonPath("$.receivedDate").value(DEFAULT_RECEIVED_DATE.toString()))
            .andExpect(jsonPath("$.receivedStatus").value(DEFAULT_RECEIVED_STATUS))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.branchId").value(DEFAULT_BRANCH_ID.intValue()))
            .andExpect(jsonPath("$.branchName").value(DEFAULT_BRANCH_NAME));
    }

    @Test
    @Transactional
    void getNonExistingStockTransfer() throws Exception {
        // Get the stockTransfer
        restStockTransferMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingStockTransfer() throws Exception {
        // Initialize the database
        stockTransferRepository.saveAndFlush(stockTransfer);

        int databaseSizeBeforeUpdate = stockTransferRepository.findAll().size();

        // Update the stockTransfer
        StockTransfer updatedStockTransfer = stockTransferRepository.findById(stockTransfer.getStockTransferId()).get();
        // Disconnect from session so that the updates on updatedStockTransfer are not directly saved in db
        em.detach(updatedStockTransfer);
        updatedStockTransfer
            .sourceLoactionId(UPDATED_SOURCE_LOACTION_ID)
            .sourceLoactionName(UPDATED_SOURCE_LOACTION_NAME)
            .transferDate(UPDATED_TRANSFER_DATE)
            .destinationLocationId(UPDATED_DESTINATION_LOCATION_ID)
            .destinationLocationName(UPDATED_DESTINATION_LOCATION_NAME)
            .itemId(UPDATED_ITEM_ID)
            .itemName(UPDATED_ITEM_NAME)
            .itemUom(UPDATED_ITEM_UOM)
            .itemQty(UPDATED_ITEM_QTY)
            .itemNo(UPDATED_ITEM_NO)
            .wheatherSerialized(UPDATED_WHEATHER_SERIALIZED)
            .transferStatus(UPDATED_TRANSFER_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .stockTransferUuid(UPDATED_STOCK_TRANSFER_UUID)
            .transferNo(UPDATED_TRANSFER_NO)
            .serialNo(UPDATED_SERIAL_NO)
            .receivedDate(UPDATED_RECEIVED_DATE)
            .receivedStatus(UPDATED_RECEIVED_STATUS)
            .status(UPDATED_STATUS)
            .branchId(UPDATED_BRANCH_ID)
            .branchName(UPDATED_BRANCH_NAME);
        StockTransferDTO stockTransferDTO = stockTransferMapper.toDto(updatedStockTransfer);

        restStockTransferMockMvc
            .perform(
                put(ENTITY_API_URL_ID, stockTransferDTO.getStockTransferId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(stockTransferDTO))
            )
            .andExpect(status().isOk());

        // Validate the StockTransfer in the database
        List<StockTransfer> stockTransferList = stockTransferRepository.findAll();
        assertThat(stockTransferList).hasSize(databaseSizeBeforeUpdate);
        StockTransfer testStockTransfer = stockTransferList.get(stockTransferList.size() - 1);
        assertThat(testStockTransfer.getSourceLoactionId()).isEqualTo(UPDATED_SOURCE_LOACTION_ID);
        assertThat(testStockTransfer.getSourceLoactionName()).isEqualTo(UPDATED_SOURCE_LOACTION_NAME);
        assertThat(testStockTransfer.getTransferDate()).isEqualTo(UPDATED_TRANSFER_DATE);
        assertThat(testStockTransfer.getDestinationLocationId()).isEqualTo(UPDATED_DESTINATION_LOCATION_ID);
        assertThat(testStockTransfer.getDestinationLocationName()).isEqualTo(UPDATED_DESTINATION_LOCATION_NAME);
        assertThat(testStockTransfer.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testStockTransfer.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testStockTransfer.getItemUom()).isEqualTo(UPDATED_ITEM_UOM);
        assertThat(testStockTransfer.getItemQty()).isEqualTo(UPDATED_ITEM_QTY);
        assertThat(testStockTransfer.getItemNo()).isEqualTo(UPDATED_ITEM_NO);
        assertThat(testStockTransfer.getWheatherSerialized()).isEqualTo(UPDATED_WHEATHER_SERIALIZED);
        assertThat(testStockTransfer.getTransferStatus()).isEqualTo(UPDATED_TRANSFER_STATUS);
        assertThat(testStockTransfer.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testStockTransfer.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testStockTransfer.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testStockTransfer.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testStockTransfer.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testStockTransfer.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testStockTransfer.getStockTransferUuid()).isEqualTo(UPDATED_STOCK_TRANSFER_UUID);
        assertThat(testStockTransfer.getTransferNo()).isEqualTo(UPDATED_TRANSFER_NO);
        assertThat(testStockTransfer.getSerialNo()).isEqualTo(UPDATED_SERIAL_NO);
        assertThat(testStockTransfer.getReceivedDate()).isEqualTo(UPDATED_RECEIVED_DATE);
        assertThat(testStockTransfer.getReceivedStatus()).isEqualTo(UPDATED_RECEIVED_STATUS);
        assertThat(testStockTransfer.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testStockTransfer.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
        assertThat(testStockTransfer.getBranchName()).isEqualTo(UPDATED_BRANCH_NAME);
    }

    @Test
    @Transactional
    void putNonExistingStockTransfer() throws Exception {
        int databaseSizeBeforeUpdate = stockTransferRepository.findAll().size();
        stockTransfer.setStockTransferId(count.incrementAndGet());

        // Create the StockTransfer
        StockTransferDTO stockTransferDTO = stockTransferMapper.toDto(stockTransfer);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStockTransferMockMvc
            .perform(
                put(ENTITY_API_URL_ID, stockTransferDTO.getStockTransferId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(stockTransferDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the StockTransfer in the database
        List<StockTransfer> stockTransferList = stockTransferRepository.findAll();
        assertThat(stockTransferList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchStockTransfer() throws Exception {
        int databaseSizeBeforeUpdate = stockTransferRepository.findAll().size();
        stockTransfer.setStockTransferId(count.incrementAndGet());

        // Create the StockTransfer
        StockTransferDTO stockTransferDTO = stockTransferMapper.toDto(stockTransfer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStockTransferMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(stockTransferDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the StockTransfer in the database
        List<StockTransfer> stockTransferList = stockTransferRepository.findAll();
        assertThat(stockTransferList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamStockTransfer() throws Exception {
        int databaseSizeBeforeUpdate = stockTransferRepository.findAll().size();
        stockTransfer.setStockTransferId(count.incrementAndGet());

        // Create the StockTransfer
        StockTransferDTO stockTransferDTO = stockTransferMapper.toDto(stockTransfer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStockTransferMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(stockTransferDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the StockTransfer in the database
        List<StockTransfer> stockTransferList = stockTransferRepository.findAll();
        assertThat(stockTransferList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateStockTransferWithPatch() throws Exception {
        // Initialize the database
        stockTransferRepository.saveAndFlush(stockTransfer);

        int databaseSizeBeforeUpdate = stockTransferRepository.findAll().size();

        // Update the stockTransfer using partial update
        StockTransfer partialUpdatedStockTransfer = new StockTransfer();
        partialUpdatedStockTransfer.setStockTransferId(stockTransfer.getStockTransferId());

        partialUpdatedStockTransfer
            .sourceLoactionName(UPDATED_SOURCE_LOACTION_NAME)
            .transferDate(UPDATED_TRANSFER_DATE)
            .destinationLocationName(UPDATED_DESTINATION_LOCATION_NAME)
            .itemId(UPDATED_ITEM_ID)
            .itemName(UPDATED_ITEM_NAME)
            .itemNo(UPDATED_ITEM_NO)
            .wheatherSerialized(UPDATED_WHEATHER_SERIALIZED)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .stockTransferUuid(UPDATED_STOCK_TRANSFER_UUID)
            .serialNo(UPDATED_SERIAL_NO)
            .receivedStatus(UPDATED_RECEIVED_STATUS)
            .status(UPDATED_STATUS);

        restStockTransferMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStockTransfer.getStockTransferId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedStockTransfer))
            )
            .andExpect(status().isOk());

        // Validate the StockTransfer in the database
        List<StockTransfer> stockTransferList = stockTransferRepository.findAll();
        assertThat(stockTransferList).hasSize(databaseSizeBeforeUpdate);
        StockTransfer testStockTransfer = stockTransferList.get(stockTransferList.size() - 1);
        assertThat(testStockTransfer.getSourceLoactionId()).isEqualTo(DEFAULT_SOURCE_LOACTION_ID);
        assertThat(testStockTransfer.getSourceLoactionName()).isEqualTo(UPDATED_SOURCE_LOACTION_NAME);
        assertThat(testStockTransfer.getTransferDate()).isEqualTo(UPDATED_TRANSFER_DATE);
        assertThat(testStockTransfer.getDestinationLocationId()).isEqualTo(DEFAULT_DESTINATION_LOCATION_ID);
        assertThat(testStockTransfer.getDestinationLocationName()).isEqualTo(UPDATED_DESTINATION_LOCATION_NAME);
        assertThat(testStockTransfer.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testStockTransfer.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testStockTransfer.getItemUom()).isEqualTo(DEFAULT_ITEM_UOM);
        assertThat(testStockTransfer.getItemQty()).isEqualTo(DEFAULT_ITEM_QTY);
        assertThat(testStockTransfer.getItemNo()).isEqualTo(UPDATED_ITEM_NO);
        assertThat(testStockTransfer.getWheatherSerialized()).isEqualTo(UPDATED_WHEATHER_SERIALIZED);
        assertThat(testStockTransfer.getTransferStatus()).isEqualTo(DEFAULT_TRANSFER_STATUS);
        assertThat(testStockTransfer.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testStockTransfer.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testStockTransfer.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testStockTransfer.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testStockTransfer.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testStockTransfer.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testStockTransfer.getStockTransferUuid()).isEqualTo(UPDATED_STOCK_TRANSFER_UUID);
        assertThat(testStockTransfer.getTransferNo()).isEqualTo(DEFAULT_TRANSFER_NO);
        assertThat(testStockTransfer.getSerialNo()).isEqualTo(UPDATED_SERIAL_NO);
        assertThat(testStockTransfer.getReceivedDate()).isEqualTo(DEFAULT_RECEIVED_DATE);
        assertThat(testStockTransfer.getReceivedStatus()).isEqualTo(UPDATED_RECEIVED_STATUS);
        assertThat(testStockTransfer.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testStockTransfer.getBranchId()).isEqualTo(DEFAULT_BRANCH_ID);
        assertThat(testStockTransfer.getBranchName()).isEqualTo(DEFAULT_BRANCH_NAME);
    }

    @Test
    @Transactional
    void fullUpdateStockTransferWithPatch() throws Exception {
        // Initialize the database
        stockTransferRepository.saveAndFlush(stockTransfer);

        int databaseSizeBeforeUpdate = stockTransferRepository.findAll().size();

        // Update the stockTransfer using partial update
        StockTransfer partialUpdatedStockTransfer = new StockTransfer();
        partialUpdatedStockTransfer.setStockTransferId(stockTransfer.getStockTransferId());

        partialUpdatedStockTransfer
            .sourceLoactionId(UPDATED_SOURCE_LOACTION_ID)
            .sourceLoactionName(UPDATED_SOURCE_LOACTION_NAME)
            .transferDate(UPDATED_TRANSFER_DATE)
            .destinationLocationId(UPDATED_DESTINATION_LOCATION_ID)
            .destinationLocationName(UPDATED_DESTINATION_LOCATION_NAME)
            .itemId(UPDATED_ITEM_ID)
            .itemName(UPDATED_ITEM_NAME)
            .itemUom(UPDATED_ITEM_UOM)
            .itemQty(UPDATED_ITEM_QTY)
            .itemNo(UPDATED_ITEM_NO)
            .wheatherSerialized(UPDATED_WHEATHER_SERIALIZED)
            .transferStatus(UPDATED_TRANSFER_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .stockTransferUuid(UPDATED_STOCK_TRANSFER_UUID)
            .transferNo(UPDATED_TRANSFER_NO)
            .serialNo(UPDATED_SERIAL_NO)
            .receivedDate(UPDATED_RECEIVED_DATE)
            .receivedStatus(UPDATED_RECEIVED_STATUS)
            .status(UPDATED_STATUS)
            .branchId(UPDATED_BRANCH_ID)
            .branchName(UPDATED_BRANCH_NAME);

        restStockTransferMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStockTransfer.getStockTransferId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedStockTransfer))
            )
            .andExpect(status().isOk());

        // Validate the StockTransfer in the database
        List<StockTransfer> stockTransferList = stockTransferRepository.findAll();
        assertThat(stockTransferList).hasSize(databaseSizeBeforeUpdate);
        StockTransfer testStockTransfer = stockTransferList.get(stockTransferList.size() - 1);
        assertThat(testStockTransfer.getSourceLoactionId()).isEqualTo(UPDATED_SOURCE_LOACTION_ID);
        assertThat(testStockTransfer.getSourceLoactionName()).isEqualTo(UPDATED_SOURCE_LOACTION_NAME);
        assertThat(testStockTransfer.getTransferDate()).isEqualTo(UPDATED_TRANSFER_DATE);
        assertThat(testStockTransfer.getDestinationLocationId()).isEqualTo(UPDATED_DESTINATION_LOCATION_ID);
        assertThat(testStockTransfer.getDestinationLocationName()).isEqualTo(UPDATED_DESTINATION_LOCATION_NAME);
        assertThat(testStockTransfer.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testStockTransfer.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testStockTransfer.getItemUom()).isEqualTo(UPDATED_ITEM_UOM);
        assertThat(testStockTransfer.getItemQty()).isEqualTo(UPDATED_ITEM_QTY);
        assertThat(testStockTransfer.getItemNo()).isEqualTo(UPDATED_ITEM_NO);
        assertThat(testStockTransfer.getWheatherSerialized()).isEqualTo(UPDATED_WHEATHER_SERIALIZED);
        assertThat(testStockTransfer.getTransferStatus()).isEqualTo(UPDATED_TRANSFER_STATUS);
        assertThat(testStockTransfer.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testStockTransfer.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testStockTransfer.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testStockTransfer.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testStockTransfer.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testStockTransfer.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testStockTransfer.getStockTransferUuid()).isEqualTo(UPDATED_STOCK_TRANSFER_UUID);
        assertThat(testStockTransfer.getTransferNo()).isEqualTo(UPDATED_TRANSFER_NO);
        assertThat(testStockTransfer.getSerialNo()).isEqualTo(UPDATED_SERIAL_NO);
        assertThat(testStockTransfer.getReceivedDate()).isEqualTo(UPDATED_RECEIVED_DATE);
        assertThat(testStockTransfer.getReceivedStatus()).isEqualTo(UPDATED_RECEIVED_STATUS);
        assertThat(testStockTransfer.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testStockTransfer.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
        assertThat(testStockTransfer.getBranchName()).isEqualTo(UPDATED_BRANCH_NAME);
    }

    @Test
    @Transactional
    void patchNonExistingStockTransfer() throws Exception {
        int databaseSizeBeforeUpdate = stockTransferRepository.findAll().size();
        stockTransfer.setStockTransferId(count.incrementAndGet());

        // Create the StockTransfer
        StockTransferDTO stockTransferDTO = stockTransferMapper.toDto(stockTransfer);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStockTransferMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, stockTransferDTO.getStockTransferId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(stockTransferDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the StockTransfer in the database
        List<StockTransfer> stockTransferList = stockTransferRepository.findAll();
        assertThat(stockTransferList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchStockTransfer() throws Exception {
        int databaseSizeBeforeUpdate = stockTransferRepository.findAll().size();
        stockTransfer.setStockTransferId(count.incrementAndGet());

        // Create the StockTransfer
        StockTransferDTO stockTransferDTO = stockTransferMapper.toDto(stockTransfer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStockTransferMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(stockTransferDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the StockTransfer in the database
        List<StockTransfer> stockTransferList = stockTransferRepository.findAll();
        assertThat(stockTransferList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamStockTransfer() throws Exception {
        int databaseSizeBeforeUpdate = stockTransferRepository.findAll().size();
        stockTransfer.setStockTransferId(count.incrementAndGet());

        // Create the StockTransfer
        StockTransferDTO stockTransferDTO = stockTransferMapper.toDto(stockTransfer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStockTransferMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(stockTransferDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the StockTransfer in the database
        List<StockTransfer> stockTransferList = stockTransferRepository.findAll();
        assertThat(stockTransferList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteStockTransfer() throws Exception {
        // Initialize the database
        stockTransferRepository.saveAndFlush(stockTransfer);

        int databaseSizeBeforeDelete = stockTransferRepository.findAll().size();

        // Delete the stockTransfer
        restStockTransferMockMvc
            .perform(delete(ENTITY_API_URL_ID, stockTransfer.getStockTransferId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<StockTransfer> stockTransferList = stockTransferRepository.findAll();
        assertThat(stockTransferList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
