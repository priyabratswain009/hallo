package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.PurchaseOrderItemsReceived;
import com.sunknowledge.dme.rcm.repository.PurchaseOrderItemsReceivedRepository;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderItemsReceivedDTO;
import com.sunknowledge.dme.rcm.service.mapper.PurchaseOrderItemsReceivedMapper;
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
 * Integration tests for the {@link PurchaseOrderItemsReceivedResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PurchaseOrderItemsReceivedResourceIT {

    private static final Long DEFAULT_PO_ID = 1L;
    private static final Long UPDATED_PO_ID = 2L;

    private static final String DEFAULT_PO_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PO_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_LOCATION_ID = 1L;
    private static final Long UPDATED_LOCATION_ID = 2L;

    private static final String DEFAULT_LOCATION_NO = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION_NO = "BBBBBBBBBB";

    private static final Long DEFAULT_ITEM_ID = 1L;
    private static final Long UPDATED_ITEM_ID = 2L;

    private static final String DEFAULT_ITEM_NO = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NO = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_UOM = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_UOM = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_RECEIVED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_RECEIVED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_ITEM_QTY = 1L;
    private static final Long UPDATED_ITEM_QTY = 2L;

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

    private static final String DEFAULT_LOT_BATCH_NO = "AAAAAAAAAA";
    private static final String UPDATED_LOT_BATCH_NO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_MFG_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MFG_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final UUID DEFAULT_PURCHASE_ORDER_ITEMS_RECEIVED_UUID = UUID.randomUUID();
    private static final UUID UPDATED_PURCHASE_ORDER_ITEMS_RECEIVED_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/purchase-order-items-receiveds";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{poItemReceivedId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PurchaseOrderItemsReceivedRepository purchaseOrderItemsReceivedRepository;

    @Autowired
    private PurchaseOrderItemsReceivedMapper purchaseOrderItemsReceivedMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPurchaseOrderItemsReceivedMockMvc;

    private PurchaseOrderItemsReceived purchaseOrderItemsReceived;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PurchaseOrderItemsReceived createEntity(EntityManager em) {
        PurchaseOrderItemsReceived purchaseOrderItemsReceived = new PurchaseOrderItemsReceived()
            .poId(DEFAULT_PO_ID)
            .poNumber(DEFAULT_PO_NUMBER)
            .status(DEFAULT_STATUS)
            .locationId(DEFAULT_LOCATION_ID)
            .locationNo(DEFAULT_LOCATION_NO)
            .itemId(DEFAULT_ITEM_ID)
            .itemNo(DEFAULT_ITEM_NO)
            .itemUom(DEFAULT_ITEM_UOM)
            .itemName(DEFAULT_ITEM_NAME)
            .receivedDate(DEFAULT_RECEIVED_DATE)
            .itemQty(DEFAULT_ITEM_QTY)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .lotBatchNo(DEFAULT_LOT_BATCH_NO)
            .mfgDate(DEFAULT_MFG_DATE)
            .purchaseOrderItemsReceivedUuid(DEFAULT_PURCHASE_ORDER_ITEMS_RECEIVED_UUID);
        return purchaseOrderItemsReceived;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PurchaseOrderItemsReceived createUpdatedEntity(EntityManager em) {
        PurchaseOrderItemsReceived purchaseOrderItemsReceived = new PurchaseOrderItemsReceived()
            .poId(UPDATED_PO_ID)
            .poNumber(UPDATED_PO_NUMBER)
            .status(UPDATED_STATUS)
            .locationId(UPDATED_LOCATION_ID)
            .locationNo(UPDATED_LOCATION_NO)
            .itemId(UPDATED_ITEM_ID)
            .itemNo(UPDATED_ITEM_NO)
            .itemUom(UPDATED_ITEM_UOM)
            .itemName(UPDATED_ITEM_NAME)
            .receivedDate(UPDATED_RECEIVED_DATE)
            .itemQty(UPDATED_ITEM_QTY)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .lotBatchNo(UPDATED_LOT_BATCH_NO)
            .mfgDate(UPDATED_MFG_DATE)
            .purchaseOrderItemsReceivedUuid(UPDATED_PURCHASE_ORDER_ITEMS_RECEIVED_UUID);
        return purchaseOrderItemsReceived;
    }

    @BeforeEach
    public void initTest() {
        purchaseOrderItemsReceived = createEntity(em);
    }

    @Test
    @Transactional
    void createPurchaseOrderItemsReceived() throws Exception {
        int databaseSizeBeforeCreate = purchaseOrderItemsReceivedRepository.findAll().size();
        // Create the PurchaseOrderItemsReceived
        PurchaseOrderItemsReceivedDTO purchaseOrderItemsReceivedDTO = purchaseOrderItemsReceivedMapper.toDto(purchaseOrderItemsReceived);
        restPurchaseOrderItemsReceivedMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderItemsReceivedDTO))
            )
            .andExpect(status().isCreated());

        // Validate the PurchaseOrderItemsReceived in the database
        List<PurchaseOrderItemsReceived> purchaseOrderItemsReceivedList = purchaseOrderItemsReceivedRepository.findAll();
        assertThat(purchaseOrderItemsReceivedList).hasSize(databaseSizeBeforeCreate + 1);
        PurchaseOrderItemsReceived testPurchaseOrderItemsReceived = purchaseOrderItemsReceivedList.get(
            purchaseOrderItemsReceivedList.size() - 1
        );
        assertThat(testPurchaseOrderItemsReceived.getPoId()).isEqualTo(DEFAULT_PO_ID);
        assertThat(testPurchaseOrderItemsReceived.getPoNumber()).isEqualTo(DEFAULT_PO_NUMBER);
        assertThat(testPurchaseOrderItemsReceived.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPurchaseOrderItemsReceived.getLocationId()).isEqualTo(DEFAULT_LOCATION_ID);
        assertThat(testPurchaseOrderItemsReceived.getLocationNo()).isEqualTo(DEFAULT_LOCATION_NO);
        assertThat(testPurchaseOrderItemsReceived.getItemId()).isEqualTo(DEFAULT_ITEM_ID);
        assertThat(testPurchaseOrderItemsReceived.getItemNo()).isEqualTo(DEFAULT_ITEM_NO);
        assertThat(testPurchaseOrderItemsReceived.getItemUom()).isEqualTo(DEFAULT_ITEM_UOM);
        assertThat(testPurchaseOrderItemsReceived.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testPurchaseOrderItemsReceived.getReceivedDate()).isEqualTo(DEFAULT_RECEIVED_DATE);
        assertThat(testPurchaseOrderItemsReceived.getItemQty()).isEqualTo(DEFAULT_ITEM_QTY);
        assertThat(testPurchaseOrderItemsReceived.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testPurchaseOrderItemsReceived.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testPurchaseOrderItemsReceived.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testPurchaseOrderItemsReceived.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testPurchaseOrderItemsReceived.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testPurchaseOrderItemsReceived.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testPurchaseOrderItemsReceived.getLotBatchNo()).isEqualTo(DEFAULT_LOT_BATCH_NO);
        assertThat(testPurchaseOrderItemsReceived.getMfgDate()).isEqualTo(DEFAULT_MFG_DATE);
        assertThat(testPurchaseOrderItemsReceived.getPurchaseOrderItemsReceivedUuid())
            .isEqualTo(DEFAULT_PURCHASE_ORDER_ITEMS_RECEIVED_UUID);
    }

    @Test
    @Transactional
    void createPurchaseOrderItemsReceivedWithExistingId() throws Exception {
        // Create the PurchaseOrderItemsReceived with an existing ID
        purchaseOrderItemsReceived.setPoItemReceivedId(1L);
        PurchaseOrderItemsReceivedDTO purchaseOrderItemsReceivedDTO = purchaseOrderItemsReceivedMapper.toDto(purchaseOrderItemsReceived);

        int databaseSizeBeforeCreate = purchaseOrderItemsReceivedRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPurchaseOrderItemsReceivedMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderItemsReceivedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PurchaseOrderItemsReceived in the database
        List<PurchaseOrderItemsReceived> purchaseOrderItemsReceivedList = purchaseOrderItemsReceivedRepository.findAll();
        assertThat(purchaseOrderItemsReceivedList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPurchaseOrderItemsReceiveds() throws Exception {
        // Initialize the database
        purchaseOrderItemsReceivedRepository.saveAndFlush(purchaseOrderItemsReceived);

        // Get all the purchaseOrderItemsReceivedList
        restPurchaseOrderItemsReceivedMockMvc
            .perform(get(ENTITY_API_URL + "?sort=poItemReceivedId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].poItemReceivedId").value(hasItem(purchaseOrderItemsReceived.getPoItemReceivedId().intValue())))
            .andExpect(jsonPath("$.[*].poId").value(hasItem(DEFAULT_PO_ID.intValue())))
            .andExpect(jsonPath("$.[*].poNumber").value(hasItem(DEFAULT_PO_NUMBER)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].locationId").value(hasItem(DEFAULT_LOCATION_ID.intValue())))
            .andExpect(jsonPath("$.[*].locationNo").value(hasItem(DEFAULT_LOCATION_NO)))
            .andExpect(jsonPath("$.[*].itemId").value(hasItem(DEFAULT_ITEM_ID.intValue())))
            .andExpect(jsonPath("$.[*].itemNo").value(hasItem(DEFAULT_ITEM_NO)))
            .andExpect(jsonPath("$.[*].itemUom").value(hasItem(DEFAULT_ITEM_UOM)))
            .andExpect(jsonPath("$.[*].itemName").value(hasItem(DEFAULT_ITEM_NAME)))
            .andExpect(jsonPath("$.[*].receivedDate").value(hasItem(DEFAULT_RECEIVED_DATE.toString())))
            .andExpect(jsonPath("$.[*].itemQty").value(hasItem(DEFAULT_ITEM_QTY.intValue())))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lotBatchNo").value(hasItem(DEFAULT_LOT_BATCH_NO)))
            .andExpect(jsonPath("$.[*].mfgDate").value(hasItem(DEFAULT_MFG_DATE.toString())))
            .andExpect(
                jsonPath("$.[*].purchaseOrderItemsReceivedUuid").value(hasItem(DEFAULT_PURCHASE_ORDER_ITEMS_RECEIVED_UUID.toString()))
            );
    }

    @Test
    @Transactional
    void getPurchaseOrderItemsReceived() throws Exception {
        // Initialize the database
        purchaseOrderItemsReceivedRepository.saveAndFlush(purchaseOrderItemsReceived);

        // Get the purchaseOrderItemsReceived
        restPurchaseOrderItemsReceivedMockMvc
            .perform(get(ENTITY_API_URL_ID, purchaseOrderItemsReceived.getPoItemReceivedId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.poItemReceivedId").value(purchaseOrderItemsReceived.getPoItemReceivedId().intValue()))
            .andExpect(jsonPath("$.poId").value(DEFAULT_PO_ID.intValue()))
            .andExpect(jsonPath("$.poNumber").value(DEFAULT_PO_NUMBER))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.locationId").value(DEFAULT_LOCATION_ID.intValue()))
            .andExpect(jsonPath("$.locationNo").value(DEFAULT_LOCATION_NO))
            .andExpect(jsonPath("$.itemId").value(DEFAULT_ITEM_ID.intValue()))
            .andExpect(jsonPath("$.itemNo").value(DEFAULT_ITEM_NO))
            .andExpect(jsonPath("$.itemUom").value(DEFAULT_ITEM_UOM))
            .andExpect(jsonPath("$.itemName").value(DEFAULT_ITEM_NAME))
            .andExpect(jsonPath("$.receivedDate").value(DEFAULT_RECEIVED_DATE.toString()))
            .andExpect(jsonPath("$.itemQty").value(DEFAULT_ITEM_QTY.intValue()))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lotBatchNo").value(DEFAULT_LOT_BATCH_NO))
            .andExpect(jsonPath("$.mfgDate").value(DEFAULT_MFG_DATE.toString()))
            .andExpect(jsonPath("$.purchaseOrderItemsReceivedUuid").value(DEFAULT_PURCHASE_ORDER_ITEMS_RECEIVED_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingPurchaseOrderItemsReceived() throws Exception {
        // Get the purchaseOrderItemsReceived
        restPurchaseOrderItemsReceivedMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewPurchaseOrderItemsReceived() throws Exception {
        // Initialize the database
        purchaseOrderItemsReceivedRepository.saveAndFlush(purchaseOrderItemsReceived);

        int databaseSizeBeforeUpdate = purchaseOrderItemsReceivedRepository.findAll().size();

        // Update the purchaseOrderItemsReceived
        PurchaseOrderItemsReceived updatedPurchaseOrderItemsReceived = purchaseOrderItemsReceivedRepository
            .findById(purchaseOrderItemsReceived.getPoItemReceivedId())
            .get();
        // Disconnect from session so that the updates on updatedPurchaseOrderItemsReceived are not directly saved in db
        em.detach(updatedPurchaseOrderItemsReceived);
        updatedPurchaseOrderItemsReceived
            .poId(UPDATED_PO_ID)
            .poNumber(UPDATED_PO_NUMBER)
            .status(UPDATED_STATUS)
            .locationId(UPDATED_LOCATION_ID)
            .locationNo(UPDATED_LOCATION_NO)
            .itemId(UPDATED_ITEM_ID)
            .itemNo(UPDATED_ITEM_NO)
            .itemUom(UPDATED_ITEM_UOM)
            .itemName(UPDATED_ITEM_NAME)
            .receivedDate(UPDATED_RECEIVED_DATE)
            .itemQty(UPDATED_ITEM_QTY)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .lotBatchNo(UPDATED_LOT_BATCH_NO)
            .mfgDate(UPDATED_MFG_DATE)
            .purchaseOrderItemsReceivedUuid(UPDATED_PURCHASE_ORDER_ITEMS_RECEIVED_UUID);
        PurchaseOrderItemsReceivedDTO purchaseOrderItemsReceivedDTO = purchaseOrderItemsReceivedMapper.toDto(
            updatedPurchaseOrderItemsReceived
        );

        restPurchaseOrderItemsReceivedMockMvc
            .perform(
                put(ENTITY_API_URL_ID, purchaseOrderItemsReceivedDTO.getPoItemReceivedId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderItemsReceivedDTO))
            )
            .andExpect(status().isOk());

        // Validate the PurchaseOrderItemsReceived in the database
        List<PurchaseOrderItemsReceived> purchaseOrderItemsReceivedList = purchaseOrderItemsReceivedRepository.findAll();
        assertThat(purchaseOrderItemsReceivedList).hasSize(databaseSizeBeforeUpdate);
        PurchaseOrderItemsReceived testPurchaseOrderItemsReceived = purchaseOrderItemsReceivedList.get(
            purchaseOrderItemsReceivedList.size() - 1
        );
        assertThat(testPurchaseOrderItemsReceived.getPoId()).isEqualTo(UPDATED_PO_ID);
        assertThat(testPurchaseOrderItemsReceived.getPoNumber()).isEqualTo(UPDATED_PO_NUMBER);
        assertThat(testPurchaseOrderItemsReceived.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPurchaseOrderItemsReceived.getLocationId()).isEqualTo(UPDATED_LOCATION_ID);
        assertThat(testPurchaseOrderItemsReceived.getLocationNo()).isEqualTo(UPDATED_LOCATION_NO);
        assertThat(testPurchaseOrderItemsReceived.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testPurchaseOrderItemsReceived.getItemNo()).isEqualTo(UPDATED_ITEM_NO);
        assertThat(testPurchaseOrderItemsReceived.getItemUom()).isEqualTo(UPDATED_ITEM_UOM);
        assertThat(testPurchaseOrderItemsReceived.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testPurchaseOrderItemsReceived.getReceivedDate()).isEqualTo(UPDATED_RECEIVED_DATE);
        assertThat(testPurchaseOrderItemsReceived.getItemQty()).isEqualTo(UPDATED_ITEM_QTY);
        assertThat(testPurchaseOrderItemsReceived.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPurchaseOrderItemsReceived.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPurchaseOrderItemsReceived.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPurchaseOrderItemsReceived.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPurchaseOrderItemsReceived.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPurchaseOrderItemsReceived.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPurchaseOrderItemsReceived.getLotBatchNo()).isEqualTo(UPDATED_LOT_BATCH_NO);
        assertThat(testPurchaseOrderItemsReceived.getMfgDate()).isEqualTo(UPDATED_MFG_DATE);
        assertThat(testPurchaseOrderItemsReceived.getPurchaseOrderItemsReceivedUuid())
            .isEqualTo(UPDATED_PURCHASE_ORDER_ITEMS_RECEIVED_UUID);
    }

    @Test
    @Transactional
    void putNonExistingPurchaseOrderItemsReceived() throws Exception {
        int databaseSizeBeforeUpdate = purchaseOrderItemsReceivedRepository.findAll().size();
        purchaseOrderItemsReceived.setPoItemReceivedId(count.incrementAndGet());

        // Create the PurchaseOrderItemsReceived
        PurchaseOrderItemsReceivedDTO purchaseOrderItemsReceivedDTO = purchaseOrderItemsReceivedMapper.toDto(purchaseOrderItemsReceived);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPurchaseOrderItemsReceivedMockMvc
            .perform(
                put(ENTITY_API_URL_ID, purchaseOrderItemsReceivedDTO.getPoItemReceivedId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderItemsReceivedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PurchaseOrderItemsReceived in the database
        List<PurchaseOrderItemsReceived> purchaseOrderItemsReceivedList = purchaseOrderItemsReceivedRepository.findAll();
        assertThat(purchaseOrderItemsReceivedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPurchaseOrderItemsReceived() throws Exception {
        int databaseSizeBeforeUpdate = purchaseOrderItemsReceivedRepository.findAll().size();
        purchaseOrderItemsReceived.setPoItemReceivedId(count.incrementAndGet());

        // Create the PurchaseOrderItemsReceived
        PurchaseOrderItemsReceivedDTO purchaseOrderItemsReceivedDTO = purchaseOrderItemsReceivedMapper.toDto(purchaseOrderItemsReceived);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPurchaseOrderItemsReceivedMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderItemsReceivedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PurchaseOrderItemsReceived in the database
        List<PurchaseOrderItemsReceived> purchaseOrderItemsReceivedList = purchaseOrderItemsReceivedRepository.findAll();
        assertThat(purchaseOrderItemsReceivedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPurchaseOrderItemsReceived() throws Exception {
        int databaseSizeBeforeUpdate = purchaseOrderItemsReceivedRepository.findAll().size();
        purchaseOrderItemsReceived.setPoItemReceivedId(count.incrementAndGet());

        // Create the PurchaseOrderItemsReceived
        PurchaseOrderItemsReceivedDTO purchaseOrderItemsReceivedDTO = purchaseOrderItemsReceivedMapper.toDto(purchaseOrderItemsReceived);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPurchaseOrderItemsReceivedMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderItemsReceivedDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PurchaseOrderItemsReceived in the database
        List<PurchaseOrderItemsReceived> purchaseOrderItemsReceivedList = purchaseOrderItemsReceivedRepository.findAll();
        assertThat(purchaseOrderItemsReceivedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePurchaseOrderItemsReceivedWithPatch() throws Exception {
        // Initialize the database
        purchaseOrderItemsReceivedRepository.saveAndFlush(purchaseOrderItemsReceived);

        int databaseSizeBeforeUpdate = purchaseOrderItemsReceivedRepository.findAll().size();

        // Update the purchaseOrderItemsReceived using partial update
        PurchaseOrderItemsReceived partialUpdatedPurchaseOrderItemsReceived = new PurchaseOrderItemsReceived();
        partialUpdatedPurchaseOrderItemsReceived.setPoItemReceivedId(purchaseOrderItemsReceived.getPoItemReceivedId());

        partialUpdatedPurchaseOrderItemsReceived
            .status(UPDATED_STATUS)
            .locationId(UPDATED_LOCATION_ID)
            .itemNo(UPDATED_ITEM_NO)
            .itemUom(UPDATED_ITEM_UOM)
            .receivedDate(UPDATED_RECEIVED_DATE)
            .itemQty(UPDATED_ITEM_QTY)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .mfgDate(UPDATED_MFG_DATE);

        restPurchaseOrderItemsReceivedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPurchaseOrderItemsReceived.getPoItemReceivedId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPurchaseOrderItemsReceived))
            )
            .andExpect(status().isOk());

        // Validate the PurchaseOrderItemsReceived in the database
        List<PurchaseOrderItemsReceived> purchaseOrderItemsReceivedList = purchaseOrderItemsReceivedRepository.findAll();
        assertThat(purchaseOrderItemsReceivedList).hasSize(databaseSizeBeforeUpdate);
        PurchaseOrderItemsReceived testPurchaseOrderItemsReceived = purchaseOrderItemsReceivedList.get(
            purchaseOrderItemsReceivedList.size() - 1
        );
        assertThat(testPurchaseOrderItemsReceived.getPoId()).isEqualTo(DEFAULT_PO_ID);
        assertThat(testPurchaseOrderItemsReceived.getPoNumber()).isEqualTo(DEFAULT_PO_NUMBER);
        assertThat(testPurchaseOrderItemsReceived.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPurchaseOrderItemsReceived.getLocationId()).isEqualTo(UPDATED_LOCATION_ID);
        assertThat(testPurchaseOrderItemsReceived.getLocationNo()).isEqualTo(DEFAULT_LOCATION_NO);
        assertThat(testPurchaseOrderItemsReceived.getItemId()).isEqualTo(DEFAULT_ITEM_ID);
        assertThat(testPurchaseOrderItemsReceived.getItemNo()).isEqualTo(UPDATED_ITEM_NO);
        assertThat(testPurchaseOrderItemsReceived.getItemUom()).isEqualTo(UPDATED_ITEM_UOM);
        assertThat(testPurchaseOrderItemsReceived.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testPurchaseOrderItemsReceived.getReceivedDate()).isEqualTo(UPDATED_RECEIVED_DATE);
        assertThat(testPurchaseOrderItemsReceived.getItemQty()).isEqualTo(UPDATED_ITEM_QTY);
        assertThat(testPurchaseOrderItemsReceived.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPurchaseOrderItemsReceived.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPurchaseOrderItemsReceived.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPurchaseOrderItemsReceived.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testPurchaseOrderItemsReceived.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPurchaseOrderItemsReceived.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testPurchaseOrderItemsReceived.getLotBatchNo()).isEqualTo(DEFAULT_LOT_BATCH_NO);
        assertThat(testPurchaseOrderItemsReceived.getMfgDate()).isEqualTo(UPDATED_MFG_DATE);
        assertThat(testPurchaseOrderItemsReceived.getPurchaseOrderItemsReceivedUuid())
            .isEqualTo(DEFAULT_PURCHASE_ORDER_ITEMS_RECEIVED_UUID);
    }

    @Test
    @Transactional
    void fullUpdatePurchaseOrderItemsReceivedWithPatch() throws Exception {
        // Initialize the database
        purchaseOrderItemsReceivedRepository.saveAndFlush(purchaseOrderItemsReceived);

        int databaseSizeBeforeUpdate = purchaseOrderItemsReceivedRepository.findAll().size();

        // Update the purchaseOrderItemsReceived using partial update
        PurchaseOrderItemsReceived partialUpdatedPurchaseOrderItemsReceived = new PurchaseOrderItemsReceived();
        partialUpdatedPurchaseOrderItemsReceived.setPoItemReceivedId(purchaseOrderItemsReceived.getPoItemReceivedId());

        partialUpdatedPurchaseOrderItemsReceived
            .poId(UPDATED_PO_ID)
            .poNumber(UPDATED_PO_NUMBER)
            .status(UPDATED_STATUS)
            .locationId(UPDATED_LOCATION_ID)
            .locationNo(UPDATED_LOCATION_NO)
            .itemId(UPDATED_ITEM_ID)
            .itemNo(UPDATED_ITEM_NO)
            .itemUom(UPDATED_ITEM_UOM)
            .itemName(UPDATED_ITEM_NAME)
            .receivedDate(UPDATED_RECEIVED_DATE)
            .itemQty(UPDATED_ITEM_QTY)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .lotBatchNo(UPDATED_LOT_BATCH_NO)
            .mfgDate(UPDATED_MFG_DATE)
            .purchaseOrderItemsReceivedUuid(UPDATED_PURCHASE_ORDER_ITEMS_RECEIVED_UUID);

        restPurchaseOrderItemsReceivedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPurchaseOrderItemsReceived.getPoItemReceivedId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPurchaseOrderItemsReceived))
            )
            .andExpect(status().isOk());

        // Validate the PurchaseOrderItemsReceived in the database
        List<PurchaseOrderItemsReceived> purchaseOrderItemsReceivedList = purchaseOrderItemsReceivedRepository.findAll();
        assertThat(purchaseOrderItemsReceivedList).hasSize(databaseSizeBeforeUpdate);
        PurchaseOrderItemsReceived testPurchaseOrderItemsReceived = purchaseOrderItemsReceivedList.get(
            purchaseOrderItemsReceivedList.size() - 1
        );
        assertThat(testPurchaseOrderItemsReceived.getPoId()).isEqualTo(UPDATED_PO_ID);
        assertThat(testPurchaseOrderItemsReceived.getPoNumber()).isEqualTo(UPDATED_PO_NUMBER);
        assertThat(testPurchaseOrderItemsReceived.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPurchaseOrderItemsReceived.getLocationId()).isEqualTo(UPDATED_LOCATION_ID);
        assertThat(testPurchaseOrderItemsReceived.getLocationNo()).isEqualTo(UPDATED_LOCATION_NO);
        assertThat(testPurchaseOrderItemsReceived.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testPurchaseOrderItemsReceived.getItemNo()).isEqualTo(UPDATED_ITEM_NO);
        assertThat(testPurchaseOrderItemsReceived.getItemUom()).isEqualTo(UPDATED_ITEM_UOM);
        assertThat(testPurchaseOrderItemsReceived.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testPurchaseOrderItemsReceived.getReceivedDate()).isEqualTo(UPDATED_RECEIVED_DATE);
        assertThat(testPurchaseOrderItemsReceived.getItemQty()).isEqualTo(UPDATED_ITEM_QTY);
        assertThat(testPurchaseOrderItemsReceived.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPurchaseOrderItemsReceived.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPurchaseOrderItemsReceived.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPurchaseOrderItemsReceived.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPurchaseOrderItemsReceived.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPurchaseOrderItemsReceived.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPurchaseOrderItemsReceived.getLotBatchNo()).isEqualTo(UPDATED_LOT_BATCH_NO);
        assertThat(testPurchaseOrderItemsReceived.getMfgDate()).isEqualTo(UPDATED_MFG_DATE);
        assertThat(testPurchaseOrderItemsReceived.getPurchaseOrderItemsReceivedUuid())
            .isEqualTo(UPDATED_PURCHASE_ORDER_ITEMS_RECEIVED_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingPurchaseOrderItemsReceived() throws Exception {
        int databaseSizeBeforeUpdate = purchaseOrderItemsReceivedRepository.findAll().size();
        purchaseOrderItemsReceived.setPoItemReceivedId(count.incrementAndGet());

        // Create the PurchaseOrderItemsReceived
        PurchaseOrderItemsReceivedDTO purchaseOrderItemsReceivedDTO = purchaseOrderItemsReceivedMapper.toDto(purchaseOrderItemsReceived);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPurchaseOrderItemsReceivedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, purchaseOrderItemsReceivedDTO.getPoItemReceivedId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderItemsReceivedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PurchaseOrderItemsReceived in the database
        List<PurchaseOrderItemsReceived> purchaseOrderItemsReceivedList = purchaseOrderItemsReceivedRepository.findAll();
        assertThat(purchaseOrderItemsReceivedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPurchaseOrderItemsReceived() throws Exception {
        int databaseSizeBeforeUpdate = purchaseOrderItemsReceivedRepository.findAll().size();
        purchaseOrderItemsReceived.setPoItemReceivedId(count.incrementAndGet());

        // Create the PurchaseOrderItemsReceived
        PurchaseOrderItemsReceivedDTO purchaseOrderItemsReceivedDTO = purchaseOrderItemsReceivedMapper.toDto(purchaseOrderItemsReceived);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPurchaseOrderItemsReceivedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderItemsReceivedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PurchaseOrderItemsReceived in the database
        List<PurchaseOrderItemsReceived> purchaseOrderItemsReceivedList = purchaseOrderItemsReceivedRepository.findAll();
        assertThat(purchaseOrderItemsReceivedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPurchaseOrderItemsReceived() throws Exception {
        int databaseSizeBeforeUpdate = purchaseOrderItemsReceivedRepository.findAll().size();
        purchaseOrderItemsReceived.setPoItemReceivedId(count.incrementAndGet());

        // Create the PurchaseOrderItemsReceived
        PurchaseOrderItemsReceivedDTO purchaseOrderItemsReceivedDTO = purchaseOrderItemsReceivedMapper.toDto(purchaseOrderItemsReceived);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPurchaseOrderItemsReceivedMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderItemsReceivedDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PurchaseOrderItemsReceived in the database
        List<PurchaseOrderItemsReceived> purchaseOrderItemsReceivedList = purchaseOrderItemsReceivedRepository.findAll();
        assertThat(purchaseOrderItemsReceivedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePurchaseOrderItemsReceived() throws Exception {
        // Initialize the database
        purchaseOrderItemsReceivedRepository.saveAndFlush(purchaseOrderItemsReceived);

        int databaseSizeBeforeDelete = purchaseOrderItemsReceivedRepository.findAll().size();

        // Delete the purchaseOrderItemsReceived
        restPurchaseOrderItemsReceivedMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, purchaseOrderItemsReceived.getPoItemReceivedId()).with(csrf()).accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PurchaseOrderItemsReceived> purchaseOrderItemsReceivedList = purchaseOrderItemsReceivedRepository.findAll();
        assertThat(purchaseOrderItemsReceivedList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
