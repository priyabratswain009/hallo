package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.PurchaseOrderItems;
import com.sunknowledge.dme.rcm.repository.PurchaseOrderItemsRepository;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderItemsDTO;
import com.sunknowledge.dme.rcm.service.mapper.PurchaseOrderItemsMapper;
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
 * Integration tests for the {@link PurchaseOrderItemsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PurchaseOrderItemsResourceIT {

    private static final Long DEFAULT_PO_ID = 1L;
    private static final Long UPDATED_PO_ID = 2L;

    private static final String DEFAULT_PO_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PO_NUMBER = "BBBBBBBBBB";

    private static final Long DEFAULT_ITEM_ID = 1L;
    private static final Long UPDATED_ITEM_ID = 2L;

    private static final String DEFAULT_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_NO = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NO = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_UOM = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_UOM = "BBBBBBBBBB";

    private static final Long DEFAULT_ORDERED_QTY = 1L;
    private static final Long UPDATED_ORDERED_QTY = 2L;

    private static final Long DEFAULT_RECEIVED_QTY = 1L;
    private static final Long UPDATED_RECEIVED_QTY = 2L;

    private static final Long DEFAULT_CANCELLED_QTY = 1L;
    private static final Long UPDATED_CANCELLED_QTY = 2L;

    private static final Double DEFAULT_UNIT_PRICE = 1D;
    private static final Double UPDATED_UNIT_PRICE = 2D;

    private static final Double DEFAULT_TOTAL_AMOUNT = 1D;
    private static final Double UPDATED_TOTAL_AMOUNT = 2D;

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_NOTES = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_PURCHASE_ORDER_ITEMS_UUID = UUID.randomUUID();
    private static final UUID UPDATED_PURCHASE_ORDER_ITEMS_UUID = UUID.randomUUID();

    private static final String DEFAULT_PO_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_PO_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_WHEATHER_SERIALISED = "AAAAAAAAAA";
    private static final String UPDATED_WHEATHER_SERIALISED = "BBBBBBBBBB";

    private static final String DEFAULT_LOT_NO = "AAAAAAAAAA";
    private static final String UPDATED_LOT_NO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_MFG_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MFG_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_RECEIVED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_RECEIVED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/purchase-order-items";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{poItemsId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PurchaseOrderItemsRepository purchaseOrderItemsRepository;

    @Autowired
    private PurchaseOrderItemsMapper purchaseOrderItemsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPurchaseOrderItemsMockMvc;

    private PurchaseOrderItems purchaseOrderItems;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PurchaseOrderItems createEntity(EntityManager em) {
        PurchaseOrderItems purchaseOrderItems = new PurchaseOrderItems()
            .poId(DEFAULT_PO_ID)
            .poNumber(DEFAULT_PO_NUMBER)
            .itemId(DEFAULT_ITEM_ID)
            .itemName(DEFAULT_ITEM_NAME)
            .itemNo(DEFAULT_ITEM_NO)
            .itemUom(DEFAULT_ITEM_UOM)
            .orderedQty(DEFAULT_ORDERED_QTY)
            .receivedQty(DEFAULT_RECEIVED_QTY)
            .cancelledQty(DEFAULT_CANCELLED_QTY)
            .unitPrice(DEFAULT_UNIT_PRICE)
            .totalAmount(DEFAULT_TOTAL_AMOUNT)
            .status(DEFAULT_STATUS)
            .notes(DEFAULT_NOTES)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedName(DEFAULT_UPDATED_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .purchaseOrderItemsUuid(DEFAULT_PURCHASE_ORDER_ITEMS_UUID)
            .poStatus(DEFAULT_PO_STATUS)
            .wheatherSerialised(DEFAULT_WHEATHER_SERIALISED)
            .lotNo(DEFAULT_LOT_NO)
            .mfgDate(DEFAULT_MFG_DATE)
            .receivedDate(DEFAULT_RECEIVED_DATE);
        return purchaseOrderItems;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PurchaseOrderItems createUpdatedEntity(EntityManager em) {
        PurchaseOrderItems purchaseOrderItems = new PurchaseOrderItems()
            .poId(UPDATED_PO_ID)
            .poNumber(UPDATED_PO_NUMBER)
            .itemId(UPDATED_ITEM_ID)
            .itemName(UPDATED_ITEM_NAME)
            .itemNo(UPDATED_ITEM_NO)
            .itemUom(UPDATED_ITEM_UOM)
            .orderedQty(UPDATED_ORDERED_QTY)
            .receivedQty(UPDATED_RECEIVED_QTY)
            .cancelledQty(UPDATED_CANCELLED_QTY)
            .unitPrice(UPDATED_UNIT_PRICE)
            .totalAmount(UPDATED_TOTAL_AMOUNT)
            .status(UPDATED_STATUS)
            .notes(UPDATED_NOTES)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedName(UPDATED_UPDATED_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .purchaseOrderItemsUuid(UPDATED_PURCHASE_ORDER_ITEMS_UUID)
            .poStatus(UPDATED_PO_STATUS)
            .wheatherSerialised(UPDATED_WHEATHER_SERIALISED)
            .lotNo(UPDATED_LOT_NO)
            .mfgDate(UPDATED_MFG_DATE)
            .receivedDate(UPDATED_RECEIVED_DATE);
        return purchaseOrderItems;
    }

    @BeforeEach
    public void initTest() {
        purchaseOrderItems = createEntity(em);
    }

    @Test
    @Transactional
    void createPurchaseOrderItems() throws Exception {
        int databaseSizeBeforeCreate = purchaseOrderItemsRepository.findAll().size();
        // Create the PurchaseOrderItems
        PurchaseOrderItemsDTO purchaseOrderItemsDTO = purchaseOrderItemsMapper.toDto(purchaseOrderItems);
        restPurchaseOrderItemsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderItemsDTO))
            )
            .andExpect(status().isCreated());

        // Validate the PurchaseOrderItems in the database
        List<PurchaseOrderItems> purchaseOrderItemsList = purchaseOrderItemsRepository.findAll();
        assertThat(purchaseOrderItemsList).hasSize(databaseSizeBeforeCreate + 1);
        PurchaseOrderItems testPurchaseOrderItems = purchaseOrderItemsList.get(purchaseOrderItemsList.size() - 1);
        assertThat(testPurchaseOrderItems.getPoId()).isEqualTo(DEFAULT_PO_ID);
        assertThat(testPurchaseOrderItems.getPoNumber()).isEqualTo(DEFAULT_PO_NUMBER);
        assertThat(testPurchaseOrderItems.getItemId()).isEqualTo(DEFAULT_ITEM_ID);
        assertThat(testPurchaseOrderItems.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testPurchaseOrderItems.getItemNo()).isEqualTo(DEFAULT_ITEM_NO);
        assertThat(testPurchaseOrderItems.getItemUom()).isEqualTo(DEFAULT_ITEM_UOM);
        assertThat(testPurchaseOrderItems.getOrderedQty()).isEqualTo(DEFAULT_ORDERED_QTY);
        assertThat(testPurchaseOrderItems.getReceivedQty()).isEqualTo(DEFAULT_RECEIVED_QTY);
        assertThat(testPurchaseOrderItems.getCancelledQty()).isEqualTo(DEFAULT_CANCELLED_QTY);
        assertThat(testPurchaseOrderItems.getUnitPrice()).isEqualTo(DEFAULT_UNIT_PRICE);
        assertThat(testPurchaseOrderItems.getTotalAmount()).isEqualTo(DEFAULT_TOTAL_AMOUNT);
        assertThat(testPurchaseOrderItems.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPurchaseOrderItems.getNotes()).isEqualTo(DEFAULT_NOTES);
        assertThat(testPurchaseOrderItems.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testPurchaseOrderItems.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testPurchaseOrderItems.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testPurchaseOrderItems.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testPurchaseOrderItems.getUpdatedName()).isEqualTo(DEFAULT_UPDATED_NAME);
        assertThat(testPurchaseOrderItems.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testPurchaseOrderItems.getPurchaseOrderItemsUuid()).isEqualTo(DEFAULT_PURCHASE_ORDER_ITEMS_UUID);
        assertThat(testPurchaseOrderItems.getPoStatus()).isEqualTo(DEFAULT_PO_STATUS);
        assertThat(testPurchaseOrderItems.getWheatherSerialised()).isEqualTo(DEFAULT_WHEATHER_SERIALISED);
        assertThat(testPurchaseOrderItems.getLotNo()).isEqualTo(DEFAULT_LOT_NO);
        assertThat(testPurchaseOrderItems.getMfgDate()).isEqualTo(DEFAULT_MFG_DATE);
        assertThat(testPurchaseOrderItems.getReceivedDate()).isEqualTo(DEFAULT_RECEIVED_DATE);
    }

    @Test
    @Transactional
    void createPurchaseOrderItemsWithExistingId() throws Exception {
        // Create the PurchaseOrderItems with an existing ID
        purchaseOrderItems.setPoItemsId(1L);
        PurchaseOrderItemsDTO purchaseOrderItemsDTO = purchaseOrderItemsMapper.toDto(purchaseOrderItems);

        int databaseSizeBeforeCreate = purchaseOrderItemsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPurchaseOrderItemsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderItemsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PurchaseOrderItems in the database
        List<PurchaseOrderItems> purchaseOrderItemsList = purchaseOrderItemsRepository.findAll();
        assertThat(purchaseOrderItemsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPurchaseOrderItems() throws Exception {
        // Initialize the database
        purchaseOrderItemsRepository.saveAndFlush(purchaseOrderItems);

        // Get all the purchaseOrderItemsList
        restPurchaseOrderItemsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=poItemsId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].poItemsId").value(hasItem(purchaseOrderItems.getPoItemsId().intValue())))
            .andExpect(jsonPath("$.[*].poId").value(hasItem(DEFAULT_PO_ID.intValue())))
            .andExpect(jsonPath("$.[*].poNumber").value(hasItem(DEFAULT_PO_NUMBER)))
            .andExpect(jsonPath("$.[*].itemId").value(hasItem(DEFAULT_ITEM_ID.intValue())))
            .andExpect(jsonPath("$.[*].itemName").value(hasItem(DEFAULT_ITEM_NAME)))
            .andExpect(jsonPath("$.[*].itemNo").value(hasItem(DEFAULT_ITEM_NO)))
            .andExpect(jsonPath("$.[*].itemUom").value(hasItem(DEFAULT_ITEM_UOM)))
            .andExpect(jsonPath("$.[*].orderedQty").value(hasItem(DEFAULT_ORDERED_QTY.intValue())))
            .andExpect(jsonPath("$.[*].receivedQty").value(hasItem(DEFAULT_RECEIVED_QTY.intValue())))
            .andExpect(jsonPath("$.[*].cancelledQty").value(hasItem(DEFAULT_CANCELLED_QTY.intValue())))
            .andExpect(jsonPath("$.[*].unitPrice").value(hasItem(DEFAULT_UNIT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].totalAmount").value(hasItem(DEFAULT_TOTAL_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedName").value(hasItem(DEFAULT_UPDATED_NAME)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].purchaseOrderItemsUuid").value(hasItem(DEFAULT_PURCHASE_ORDER_ITEMS_UUID.toString())))
            .andExpect(jsonPath("$.[*].poStatus").value(hasItem(DEFAULT_PO_STATUS)))
            .andExpect(jsonPath("$.[*].wheatherSerialised").value(hasItem(DEFAULT_WHEATHER_SERIALISED)))
            .andExpect(jsonPath("$.[*].lotNo").value(hasItem(DEFAULT_LOT_NO)))
            .andExpect(jsonPath("$.[*].mfgDate").value(hasItem(DEFAULT_MFG_DATE.toString())))
            .andExpect(jsonPath("$.[*].receivedDate").value(hasItem(DEFAULT_RECEIVED_DATE.toString())));
    }

    @Test
    @Transactional
    void getPurchaseOrderItems() throws Exception {
        // Initialize the database
        purchaseOrderItemsRepository.saveAndFlush(purchaseOrderItems);

        // Get the purchaseOrderItems
        restPurchaseOrderItemsMockMvc
            .perform(get(ENTITY_API_URL_ID, purchaseOrderItems.getPoItemsId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.poItemsId").value(purchaseOrderItems.getPoItemsId().intValue()))
            .andExpect(jsonPath("$.poId").value(DEFAULT_PO_ID.intValue()))
            .andExpect(jsonPath("$.poNumber").value(DEFAULT_PO_NUMBER))
            .andExpect(jsonPath("$.itemId").value(DEFAULT_ITEM_ID.intValue()))
            .andExpect(jsonPath("$.itemName").value(DEFAULT_ITEM_NAME))
            .andExpect(jsonPath("$.itemNo").value(DEFAULT_ITEM_NO))
            .andExpect(jsonPath("$.itemUom").value(DEFAULT_ITEM_UOM))
            .andExpect(jsonPath("$.orderedQty").value(DEFAULT_ORDERED_QTY.intValue()))
            .andExpect(jsonPath("$.receivedQty").value(DEFAULT_RECEIVED_QTY.intValue()))
            .andExpect(jsonPath("$.cancelledQty").value(DEFAULT_CANCELLED_QTY.intValue()))
            .andExpect(jsonPath("$.unitPrice").value(DEFAULT_UNIT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.totalAmount").value(DEFAULT_TOTAL_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.notes").value(DEFAULT_NOTES))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedName").value(DEFAULT_UPDATED_NAME))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.purchaseOrderItemsUuid").value(DEFAULT_PURCHASE_ORDER_ITEMS_UUID.toString()))
            .andExpect(jsonPath("$.poStatus").value(DEFAULT_PO_STATUS))
            .andExpect(jsonPath("$.wheatherSerialised").value(DEFAULT_WHEATHER_SERIALISED))
            .andExpect(jsonPath("$.lotNo").value(DEFAULT_LOT_NO))
            .andExpect(jsonPath("$.mfgDate").value(DEFAULT_MFG_DATE.toString()))
            .andExpect(jsonPath("$.receivedDate").value(DEFAULT_RECEIVED_DATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingPurchaseOrderItems() throws Exception {
        // Get the purchaseOrderItems
        restPurchaseOrderItemsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingPurchaseOrderItems() throws Exception {
        // Initialize the database
        purchaseOrderItemsRepository.saveAndFlush(purchaseOrderItems);

        int databaseSizeBeforeUpdate = purchaseOrderItemsRepository.findAll().size();

        // Update the purchaseOrderItems
        PurchaseOrderItems updatedPurchaseOrderItems = purchaseOrderItemsRepository.findById(purchaseOrderItems.getPoItemsId()).get();
        // Disconnect from session so that the updates on updatedPurchaseOrderItems are not directly saved in db
        em.detach(updatedPurchaseOrderItems);
        updatedPurchaseOrderItems
            .poId(UPDATED_PO_ID)
            .poNumber(UPDATED_PO_NUMBER)
            .itemId(UPDATED_ITEM_ID)
            .itemName(UPDATED_ITEM_NAME)
            .itemNo(UPDATED_ITEM_NO)
            .itemUom(UPDATED_ITEM_UOM)
            .orderedQty(UPDATED_ORDERED_QTY)
            .receivedQty(UPDATED_RECEIVED_QTY)
            .cancelledQty(UPDATED_CANCELLED_QTY)
            .unitPrice(UPDATED_UNIT_PRICE)
            .totalAmount(UPDATED_TOTAL_AMOUNT)
            .status(UPDATED_STATUS)
            .notes(UPDATED_NOTES)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedName(UPDATED_UPDATED_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .purchaseOrderItemsUuid(UPDATED_PURCHASE_ORDER_ITEMS_UUID)
            .poStatus(UPDATED_PO_STATUS)
            .wheatherSerialised(UPDATED_WHEATHER_SERIALISED)
            .lotNo(UPDATED_LOT_NO)
            .mfgDate(UPDATED_MFG_DATE)
            .receivedDate(UPDATED_RECEIVED_DATE);
        PurchaseOrderItemsDTO purchaseOrderItemsDTO = purchaseOrderItemsMapper.toDto(updatedPurchaseOrderItems);

        restPurchaseOrderItemsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, purchaseOrderItemsDTO.getPoItemsId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderItemsDTO))
            )
            .andExpect(status().isOk());

        // Validate the PurchaseOrderItems in the database
        List<PurchaseOrderItems> purchaseOrderItemsList = purchaseOrderItemsRepository.findAll();
        assertThat(purchaseOrderItemsList).hasSize(databaseSizeBeforeUpdate);
        PurchaseOrderItems testPurchaseOrderItems = purchaseOrderItemsList.get(purchaseOrderItemsList.size() - 1);
        assertThat(testPurchaseOrderItems.getPoId()).isEqualTo(UPDATED_PO_ID);
        assertThat(testPurchaseOrderItems.getPoNumber()).isEqualTo(UPDATED_PO_NUMBER);
        assertThat(testPurchaseOrderItems.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testPurchaseOrderItems.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testPurchaseOrderItems.getItemNo()).isEqualTo(UPDATED_ITEM_NO);
        assertThat(testPurchaseOrderItems.getItemUom()).isEqualTo(UPDATED_ITEM_UOM);
        assertThat(testPurchaseOrderItems.getOrderedQty()).isEqualTo(UPDATED_ORDERED_QTY);
        assertThat(testPurchaseOrderItems.getReceivedQty()).isEqualTo(UPDATED_RECEIVED_QTY);
        assertThat(testPurchaseOrderItems.getCancelledQty()).isEqualTo(UPDATED_CANCELLED_QTY);
        assertThat(testPurchaseOrderItems.getUnitPrice()).isEqualTo(UPDATED_UNIT_PRICE);
        assertThat(testPurchaseOrderItems.getTotalAmount()).isEqualTo(UPDATED_TOTAL_AMOUNT);
        assertThat(testPurchaseOrderItems.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPurchaseOrderItems.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testPurchaseOrderItems.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPurchaseOrderItems.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPurchaseOrderItems.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPurchaseOrderItems.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPurchaseOrderItems.getUpdatedName()).isEqualTo(UPDATED_UPDATED_NAME);
        assertThat(testPurchaseOrderItems.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPurchaseOrderItems.getPurchaseOrderItemsUuid()).isEqualTo(UPDATED_PURCHASE_ORDER_ITEMS_UUID);
        assertThat(testPurchaseOrderItems.getPoStatus()).isEqualTo(UPDATED_PO_STATUS);
        assertThat(testPurchaseOrderItems.getWheatherSerialised()).isEqualTo(UPDATED_WHEATHER_SERIALISED);
        assertThat(testPurchaseOrderItems.getLotNo()).isEqualTo(UPDATED_LOT_NO);
        assertThat(testPurchaseOrderItems.getMfgDate()).isEqualTo(UPDATED_MFG_DATE);
        assertThat(testPurchaseOrderItems.getReceivedDate()).isEqualTo(UPDATED_RECEIVED_DATE);
    }

    @Test
    @Transactional
    void putNonExistingPurchaseOrderItems() throws Exception {
        int databaseSizeBeforeUpdate = purchaseOrderItemsRepository.findAll().size();
        purchaseOrderItems.setPoItemsId(count.incrementAndGet());

        // Create the PurchaseOrderItems
        PurchaseOrderItemsDTO purchaseOrderItemsDTO = purchaseOrderItemsMapper.toDto(purchaseOrderItems);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPurchaseOrderItemsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, purchaseOrderItemsDTO.getPoItemsId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderItemsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PurchaseOrderItems in the database
        List<PurchaseOrderItems> purchaseOrderItemsList = purchaseOrderItemsRepository.findAll();
        assertThat(purchaseOrderItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPurchaseOrderItems() throws Exception {
        int databaseSizeBeforeUpdate = purchaseOrderItemsRepository.findAll().size();
        purchaseOrderItems.setPoItemsId(count.incrementAndGet());

        // Create the PurchaseOrderItems
        PurchaseOrderItemsDTO purchaseOrderItemsDTO = purchaseOrderItemsMapper.toDto(purchaseOrderItems);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPurchaseOrderItemsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderItemsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PurchaseOrderItems in the database
        List<PurchaseOrderItems> purchaseOrderItemsList = purchaseOrderItemsRepository.findAll();
        assertThat(purchaseOrderItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPurchaseOrderItems() throws Exception {
        int databaseSizeBeforeUpdate = purchaseOrderItemsRepository.findAll().size();
        purchaseOrderItems.setPoItemsId(count.incrementAndGet());

        // Create the PurchaseOrderItems
        PurchaseOrderItemsDTO purchaseOrderItemsDTO = purchaseOrderItemsMapper.toDto(purchaseOrderItems);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPurchaseOrderItemsMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderItemsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PurchaseOrderItems in the database
        List<PurchaseOrderItems> purchaseOrderItemsList = purchaseOrderItemsRepository.findAll();
        assertThat(purchaseOrderItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePurchaseOrderItemsWithPatch() throws Exception {
        // Initialize the database
        purchaseOrderItemsRepository.saveAndFlush(purchaseOrderItems);

        int databaseSizeBeforeUpdate = purchaseOrderItemsRepository.findAll().size();

        // Update the purchaseOrderItems using partial update
        PurchaseOrderItems partialUpdatedPurchaseOrderItems = new PurchaseOrderItems();
        partialUpdatedPurchaseOrderItems.setPoItemsId(purchaseOrderItems.getPoItemsId());

        partialUpdatedPurchaseOrderItems
            .poId(UPDATED_PO_ID)
            .itemId(UPDATED_ITEM_ID)
            .itemUom(UPDATED_ITEM_UOM)
            .orderedQty(UPDATED_ORDERED_QTY)
            .cancelledQty(UPDATED_CANCELLED_QTY)
            .status(UPDATED_STATUS)
            .notes(UPDATED_NOTES)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedName(UPDATED_UPDATED_NAME)
            .purchaseOrderItemsUuid(UPDATED_PURCHASE_ORDER_ITEMS_UUID)
            .poStatus(UPDATED_PO_STATUS);

        restPurchaseOrderItemsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPurchaseOrderItems.getPoItemsId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPurchaseOrderItems))
            )
            .andExpect(status().isOk());

        // Validate the PurchaseOrderItems in the database
        List<PurchaseOrderItems> purchaseOrderItemsList = purchaseOrderItemsRepository.findAll();
        assertThat(purchaseOrderItemsList).hasSize(databaseSizeBeforeUpdate);
        PurchaseOrderItems testPurchaseOrderItems = purchaseOrderItemsList.get(purchaseOrderItemsList.size() - 1);
        assertThat(testPurchaseOrderItems.getPoId()).isEqualTo(UPDATED_PO_ID);
        assertThat(testPurchaseOrderItems.getPoNumber()).isEqualTo(DEFAULT_PO_NUMBER);
        assertThat(testPurchaseOrderItems.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testPurchaseOrderItems.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testPurchaseOrderItems.getItemNo()).isEqualTo(DEFAULT_ITEM_NO);
        assertThat(testPurchaseOrderItems.getItemUom()).isEqualTo(UPDATED_ITEM_UOM);
        assertThat(testPurchaseOrderItems.getOrderedQty()).isEqualTo(UPDATED_ORDERED_QTY);
        assertThat(testPurchaseOrderItems.getReceivedQty()).isEqualTo(DEFAULT_RECEIVED_QTY);
        assertThat(testPurchaseOrderItems.getCancelledQty()).isEqualTo(UPDATED_CANCELLED_QTY);
        assertThat(testPurchaseOrderItems.getUnitPrice()).isEqualTo(DEFAULT_UNIT_PRICE);
        assertThat(testPurchaseOrderItems.getTotalAmount()).isEqualTo(DEFAULT_TOTAL_AMOUNT);
        assertThat(testPurchaseOrderItems.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPurchaseOrderItems.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testPurchaseOrderItems.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPurchaseOrderItems.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPurchaseOrderItems.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testPurchaseOrderItems.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testPurchaseOrderItems.getUpdatedName()).isEqualTo(UPDATED_UPDATED_NAME);
        assertThat(testPurchaseOrderItems.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testPurchaseOrderItems.getPurchaseOrderItemsUuid()).isEqualTo(UPDATED_PURCHASE_ORDER_ITEMS_UUID);
        assertThat(testPurchaseOrderItems.getPoStatus()).isEqualTo(UPDATED_PO_STATUS);
        assertThat(testPurchaseOrderItems.getWheatherSerialised()).isEqualTo(DEFAULT_WHEATHER_SERIALISED);
        assertThat(testPurchaseOrderItems.getLotNo()).isEqualTo(DEFAULT_LOT_NO);
        assertThat(testPurchaseOrderItems.getMfgDate()).isEqualTo(DEFAULT_MFG_DATE);
        assertThat(testPurchaseOrderItems.getReceivedDate()).isEqualTo(DEFAULT_RECEIVED_DATE);
    }

    @Test
    @Transactional
    void fullUpdatePurchaseOrderItemsWithPatch() throws Exception {
        // Initialize the database
        purchaseOrderItemsRepository.saveAndFlush(purchaseOrderItems);

        int databaseSizeBeforeUpdate = purchaseOrderItemsRepository.findAll().size();

        // Update the purchaseOrderItems using partial update
        PurchaseOrderItems partialUpdatedPurchaseOrderItems = new PurchaseOrderItems();
        partialUpdatedPurchaseOrderItems.setPoItemsId(purchaseOrderItems.getPoItemsId());

        partialUpdatedPurchaseOrderItems
            .poId(UPDATED_PO_ID)
            .poNumber(UPDATED_PO_NUMBER)
            .itemId(UPDATED_ITEM_ID)
            .itemName(UPDATED_ITEM_NAME)
            .itemNo(UPDATED_ITEM_NO)
            .itemUom(UPDATED_ITEM_UOM)
            .orderedQty(UPDATED_ORDERED_QTY)
            .receivedQty(UPDATED_RECEIVED_QTY)
            .cancelledQty(UPDATED_CANCELLED_QTY)
            .unitPrice(UPDATED_UNIT_PRICE)
            .totalAmount(UPDATED_TOTAL_AMOUNT)
            .status(UPDATED_STATUS)
            .notes(UPDATED_NOTES)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedName(UPDATED_UPDATED_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .purchaseOrderItemsUuid(UPDATED_PURCHASE_ORDER_ITEMS_UUID)
            .poStatus(UPDATED_PO_STATUS)
            .wheatherSerialised(UPDATED_WHEATHER_SERIALISED)
            .lotNo(UPDATED_LOT_NO)
            .mfgDate(UPDATED_MFG_DATE)
            .receivedDate(UPDATED_RECEIVED_DATE);

        restPurchaseOrderItemsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPurchaseOrderItems.getPoItemsId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPurchaseOrderItems))
            )
            .andExpect(status().isOk());

        // Validate the PurchaseOrderItems in the database
        List<PurchaseOrderItems> purchaseOrderItemsList = purchaseOrderItemsRepository.findAll();
        assertThat(purchaseOrderItemsList).hasSize(databaseSizeBeforeUpdate);
        PurchaseOrderItems testPurchaseOrderItems = purchaseOrderItemsList.get(purchaseOrderItemsList.size() - 1);
        assertThat(testPurchaseOrderItems.getPoId()).isEqualTo(UPDATED_PO_ID);
        assertThat(testPurchaseOrderItems.getPoNumber()).isEqualTo(UPDATED_PO_NUMBER);
        assertThat(testPurchaseOrderItems.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testPurchaseOrderItems.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testPurchaseOrderItems.getItemNo()).isEqualTo(UPDATED_ITEM_NO);
        assertThat(testPurchaseOrderItems.getItemUom()).isEqualTo(UPDATED_ITEM_UOM);
        assertThat(testPurchaseOrderItems.getOrderedQty()).isEqualTo(UPDATED_ORDERED_QTY);
        assertThat(testPurchaseOrderItems.getReceivedQty()).isEqualTo(UPDATED_RECEIVED_QTY);
        assertThat(testPurchaseOrderItems.getCancelledQty()).isEqualTo(UPDATED_CANCELLED_QTY);
        assertThat(testPurchaseOrderItems.getUnitPrice()).isEqualTo(UPDATED_UNIT_PRICE);
        assertThat(testPurchaseOrderItems.getTotalAmount()).isEqualTo(UPDATED_TOTAL_AMOUNT);
        assertThat(testPurchaseOrderItems.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPurchaseOrderItems.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testPurchaseOrderItems.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPurchaseOrderItems.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPurchaseOrderItems.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPurchaseOrderItems.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPurchaseOrderItems.getUpdatedName()).isEqualTo(UPDATED_UPDATED_NAME);
        assertThat(testPurchaseOrderItems.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPurchaseOrderItems.getPurchaseOrderItemsUuid()).isEqualTo(UPDATED_PURCHASE_ORDER_ITEMS_UUID);
        assertThat(testPurchaseOrderItems.getPoStatus()).isEqualTo(UPDATED_PO_STATUS);
        assertThat(testPurchaseOrderItems.getWheatherSerialised()).isEqualTo(UPDATED_WHEATHER_SERIALISED);
        assertThat(testPurchaseOrderItems.getLotNo()).isEqualTo(UPDATED_LOT_NO);
        assertThat(testPurchaseOrderItems.getMfgDate()).isEqualTo(UPDATED_MFG_DATE);
        assertThat(testPurchaseOrderItems.getReceivedDate()).isEqualTo(UPDATED_RECEIVED_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingPurchaseOrderItems() throws Exception {
        int databaseSizeBeforeUpdate = purchaseOrderItemsRepository.findAll().size();
        purchaseOrderItems.setPoItemsId(count.incrementAndGet());

        // Create the PurchaseOrderItems
        PurchaseOrderItemsDTO purchaseOrderItemsDTO = purchaseOrderItemsMapper.toDto(purchaseOrderItems);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPurchaseOrderItemsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, purchaseOrderItemsDTO.getPoItemsId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderItemsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PurchaseOrderItems in the database
        List<PurchaseOrderItems> purchaseOrderItemsList = purchaseOrderItemsRepository.findAll();
        assertThat(purchaseOrderItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPurchaseOrderItems() throws Exception {
        int databaseSizeBeforeUpdate = purchaseOrderItemsRepository.findAll().size();
        purchaseOrderItems.setPoItemsId(count.incrementAndGet());

        // Create the PurchaseOrderItems
        PurchaseOrderItemsDTO purchaseOrderItemsDTO = purchaseOrderItemsMapper.toDto(purchaseOrderItems);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPurchaseOrderItemsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderItemsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PurchaseOrderItems in the database
        List<PurchaseOrderItems> purchaseOrderItemsList = purchaseOrderItemsRepository.findAll();
        assertThat(purchaseOrderItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPurchaseOrderItems() throws Exception {
        int databaseSizeBeforeUpdate = purchaseOrderItemsRepository.findAll().size();
        purchaseOrderItems.setPoItemsId(count.incrementAndGet());

        // Create the PurchaseOrderItems
        PurchaseOrderItemsDTO purchaseOrderItemsDTO = purchaseOrderItemsMapper.toDto(purchaseOrderItems);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPurchaseOrderItemsMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderItemsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PurchaseOrderItems in the database
        List<PurchaseOrderItems> purchaseOrderItemsList = purchaseOrderItemsRepository.findAll();
        assertThat(purchaseOrderItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePurchaseOrderItems() throws Exception {
        // Initialize the database
        purchaseOrderItemsRepository.saveAndFlush(purchaseOrderItems);

        int databaseSizeBeforeDelete = purchaseOrderItemsRepository.findAll().size();

        // Delete the purchaseOrderItems
        restPurchaseOrderItemsMockMvc
            .perform(delete(ENTITY_API_URL_ID, purchaseOrderItems.getPoItemsId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PurchaseOrderItems> purchaseOrderItemsList = purchaseOrderItemsRepository.findAll();
        assertThat(purchaseOrderItemsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
