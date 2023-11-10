package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.PickupExchangeItems;
import com.sunknowledge.dme.rcm.repository.PickupExchangeItemsRepository;
import com.sunknowledge.dme.rcm.service.dto.PickupExchangeItemsDTO;
import com.sunknowledge.dme.rcm.service.mapper.PickupExchangeItemsMapper;
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
 * Integration tests for the {@link PickupExchangeItemsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PickupExchangeItemsResourceIT {

    private static final Long DEFAULT_PICKUP_EXCHANGE_ID = 1L;
    private static final Long UPDATED_PICKUP_EXCHANGE_ID = 2L;

    private static final Long DEFAULT_SO_ID = 1L;
    private static final Long UPDATED_SO_ID = 2L;

    private static final Long DEFAULT_ITEM_ID = 1L;
    private static final Long UPDATED_ITEM_ID = 2L;

    private static final String DEFAULT_ITEM_NO = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NO = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_WHETHER_SERIALIZED = "AAAAAAAAAA";
    private static final String UPDATED_WHETHER_SERIALIZED = "BBBBBBBBBB";

    private static final String DEFAULT_PICKUP_ITEM_SERIAL_NO = "AAAAAAAAAA";
    private static final String UPDATED_PICKUP_ITEM_SERIAL_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PICKUP_ITEM_ASSET_NO = "AAAAAAAAAA";
    private static final String UPDATED_PICKUP_ITEM_ASSET_NO = "BBBBBBBBBB";

    private static final String DEFAULT_REPLACEMENT_ITEM_SERIAL_NO = "AAAAAAAAAA";
    private static final String UPDATED_REPLACEMENT_ITEM_SERIAL_NO = "BBBBBBBBBB";

    private static final String DEFAULT_REPLACEMENT_ITEM_ASSET_NO = "AAAAAAAAAA";
    private static final String UPDATED_REPLACEMENT_ITEM_ASSET_NO = "BBBBBBBBBB";

    private static final Long DEFAULT_QUANTITY = 1L;
    private static final Long UPDATED_QUANTITY = 2L;

    private static final String DEFAULT_ITEM_PICKUP_EXCHANGE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_PICKUP_EXCHANGE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_PICKUP_EXCHANGE_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_PICKUP_EXCHANGE_NOTE = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_PICKUP_EXCHANGE_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_PICKUP_EXCHANGE_COMMENT = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_PICKUP_EXCHANGE_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_PICKUP_EXCHANGE_STATUS = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_PICKUP_EXCHANGE_ITEM_UUID = UUID.randomUUID();
    private static final UUID UPDATED_PICKUP_EXCHANGE_ITEM_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/pickup-exchange-items";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{pickupExchangeItemId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PickupExchangeItemsRepository pickupExchangeItemsRepository;

    @Autowired
    private PickupExchangeItemsMapper pickupExchangeItemsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPickupExchangeItemsMockMvc;

    private PickupExchangeItems pickupExchangeItems;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PickupExchangeItems createEntity(EntityManager em) {
        PickupExchangeItems pickupExchangeItems = new PickupExchangeItems()
            .pickupExchangeId(DEFAULT_PICKUP_EXCHANGE_ID)
            .soId(DEFAULT_SO_ID)
            .itemId(DEFAULT_ITEM_ID)
            .itemNo(DEFAULT_ITEM_NO)
            .itemName(DEFAULT_ITEM_NAME)
            .whetherSerialized(DEFAULT_WHETHER_SERIALIZED)
            .pickupItemSerialNo(DEFAULT_PICKUP_ITEM_SERIAL_NO)
            .pickupItemAssetNo(DEFAULT_PICKUP_ITEM_ASSET_NO)
            .replacementItemSerialNo(DEFAULT_REPLACEMENT_ITEM_SERIAL_NO)
            .replacementItemAssetNo(DEFAULT_REPLACEMENT_ITEM_ASSET_NO)
            .quantity(DEFAULT_QUANTITY)
            .itemPickupExchangeType(DEFAULT_ITEM_PICKUP_EXCHANGE_TYPE)
            .itemPickupExchangeNote(DEFAULT_ITEM_PICKUP_EXCHANGE_NOTE)
            .itemPickupExchangeComment(DEFAULT_ITEM_PICKUP_EXCHANGE_COMMENT)
            .itemPickupExchangeStatus(DEFAULT_ITEM_PICKUP_EXCHANGE_STATUS)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .pickupExchangeItemUuid(DEFAULT_PICKUP_EXCHANGE_ITEM_UUID);
        return pickupExchangeItems;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PickupExchangeItems createUpdatedEntity(EntityManager em) {
        PickupExchangeItems pickupExchangeItems = new PickupExchangeItems()
            .pickupExchangeId(UPDATED_PICKUP_EXCHANGE_ID)
            .soId(UPDATED_SO_ID)
            .itemId(UPDATED_ITEM_ID)
            .itemNo(UPDATED_ITEM_NO)
            .itemName(UPDATED_ITEM_NAME)
            .whetherSerialized(UPDATED_WHETHER_SERIALIZED)
            .pickupItemSerialNo(UPDATED_PICKUP_ITEM_SERIAL_NO)
            .pickupItemAssetNo(UPDATED_PICKUP_ITEM_ASSET_NO)
            .replacementItemSerialNo(UPDATED_REPLACEMENT_ITEM_SERIAL_NO)
            .replacementItemAssetNo(UPDATED_REPLACEMENT_ITEM_ASSET_NO)
            .quantity(UPDATED_QUANTITY)
            .itemPickupExchangeType(UPDATED_ITEM_PICKUP_EXCHANGE_TYPE)
            .itemPickupExchangeNote(UPDATED_ITEM_PICKUP_EXCHANGE_NOTE)
            .itemPickupExchangeComment(UPDATED_ITEM_PICKUP_EXCHANGE_COMMENT)
            .itemPickupExchangeStatus(UPDATED_ITEM_PICKUP_EXCHANGE_STATUS)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .pickupExchangeItemUuid(UPDATED_PICKUP_EXCHANGE_ITEM_UUID);
        return pickupExchangeItems;
    }

    @BeforeEach
    public void initTest() {
        pickupExchangeItems = createEntity(em);
    }

    @Test
    @Transactional
    void createPickupExchangeItems() throws Exception {
        int databaseSizeBeforeCreate = pickupExchangeItemsRepository.findAll().size();
        // Create the PickupExchangeItems
        PickupExchangeItemsDTO pickupExchangeItemsDTO = pickupExchangeItemsMapper.toDto(pickupExchangeItems);
        restPickupExchangeItemsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pickupExchangeItemsDTO))
            )
            .andExpect(status().isCreated());

        // Validate the PickupExchangeItems in the database
        List<PickupExchangeItems> pickupExchangeItemsList = pickupExchangeItemsRepository.findAll();
        assertThat(pickupExchangeItemsList).hasSize(databaseSizeBeforeCreate + 1);
        PickupExchangeItems testPickupExchangeItems = pickupExchangeItemsList.get(pickupExchangeItemsList.size() - 1);
        assertThat(testPickupExchangeItems.getPickupExchangeId()).isEqualTo(DEFAULT_PICKUP_EXCHANGE_ID);
        assertThat(testPickupExchangeItems.getSoId()).isEqualTo(DEFAULT_SO_ID);
        assertThat(testPickupExchangeItems.getItemId()).isEqualTo(DEFAULT_ITEM_ID);
        assertThat(testPickupExchangeItems.getItemNo()).isEqualTo(DEFAULT_ITEM_NO);
        assertThat(testPickupExchangeItems.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testPickupExchangeItems.getWhetherSerialized()).isEqualTo(DEFAULT_WHETHER_SERIALIZED);
        assertThat(testPickupExchangeItems.getPickupItemSerialNo()).isEqualTo(DEFAULT_PICKUP_ITEM_SERIAL_NO);
        assertThat(testPickupExchangeItems.getPickupItemAssetNo()).isEqualTo(DEFAULT_PICKUP_ITEM_ASSET_NO);
        assertThat(testPickupExchangeItems.getReplacementItemSerialNo()).isEqualTo(DEFAULT_REPLACEMENT_ITEM_SERIAL_NO);
        assertThat(testPickupExchangeItems.getReplacementItemAssetNo()).isEqualTo(DEFAULT_REPLACEMENT_ITEM_ASSET_NO);
        assertThat(testPickupExchangeItems.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testPickupExchangeItems.getItemPickupExchangeType()).isEqualTo(DEFAULT_ITEM_PICKUP_EXCHANGE_TYPE);
        assertThat(testPickupExchangeItems.getItemPickupExchangeNote()).isEqualTo(DEFAULT_ITEM_PICKUP_EXCHANGE_NOTE);
        assertThat(testPickupExchangeItems.getItemPickupExchangeComment()).isEqualTo(DEFAULT_ITEM_PICKUP_EXCHANGE_COMMENT);
        assertThat(testPickupExchangeItems.getItemPickupExchangeStatus()).isEqualTo(DEFAULT_ITEM_PICKUP_EXCHANGE_STATUS);
        assertThat(testPickupExchangeItems.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPickupExchangeItems.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testPickupExchangeItems.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testPickupExchangeItems.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testPickupExchangeItems.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testPickupExchangeItems.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testPickupExchangeItems.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testPickupExchangeItems.getPickupExchangeItemUuid()).isEqualTo(DEFAULT_PICKUP_EXCHANGE_ITEM_UUID);
    }

    @Test
    @Transactional
    void createPickupExchangeItemsWithExistingId() throws Exception {
        // Create the PickupExchangeItems with an existing ID
        pickupExchangeItems.setPickupExchangeItemId(1L);
        PickupExchangeItemsDTO pickupExchangeItemsDTO = pickupExchangeItemsMapper.toDto(pickupExchangeItems);

        int databaseSizeBeforeCreate = pickupExchangeItemsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPickupExchangeItemsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pickupExchangeItemsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PickupExchangeItems in the database
        List<PickupExchangeItems> pickupExchangeItemsList = pickupExchangeItemsRepository.findAll();
        assertThat(pickupExchangeItemsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPickupExchangeItems() throws Exception {
        // Initialize the database
        pickupExchangeItemsRepository.saveAndFlush(pickupExchangeItems);

        // Get all the pickupExchangeItemsList
        restPickupExchangeItemsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=pickupExchangeItemId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].pickupExchangeItemId").value(hasItem(pickupExchangeItems.getPickupExchangeItemId().intValue())))
            .andExpect(jsonPath("$.[*].pickupExchangeId").value(hasItem(DEFAULT_PICKUP_EXCHANGE_ID.intValue())))
            .andExpect(jsonPath("$.[*].soId").value(hasItem(DEFAULT_SO_ID.intValue())))
            .andExpect(jsonPath("$.[*].itemId").value(hasItem(DEFAULT_ITEM_ID.intValue())))
            .andExpect(jsonPath("$.[*].itemNo").value(hasItem(DEFAULT_ITEM_NO)))
            .andExpect(jsonPath("$.[*].itemName").value(hasItem(DEFAULT_ITEM_NAME)))
            .andExpect(jsonPath("$.[*].whetherSerialized").value(hasItem(DEFAULT_WHETHER_SERIALIZED)))
            .andExpect(jsonPath("$.[*].pickupItemSerialNo").value(hasItem(DEFAULT_PICKUP_ITEM_SERIAL_NO)))
            .andExpect(jsonPath("$.[*].pickupItemAssetNo").value(hasItem(DEFAULT_PICKUP_ITEM_ASSET_NO)))
            .andExpect(jsonPath("$.[*].replacementItemSerialNo").value(hasItem(DEFAULT_REPLACEMENT_ITEM_SERIAL_NO)))
            .andExpect(jsonPath("$.[*].replacementItemAssetNo").value(hasItem(DEFAULT_REPLACEMENT_ITEM_ASSET_NO)))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].itemPickupExchangeType").value(hasItem(DEFAULT_ITEM_PICKUP_EXCHANGE_TYPE)))
            .andExpect(jsonPath("$.[*].itemPickupExchangeNote").value(hasItem(DEFAULT_ITEM_PICKUP_EXCHANGE_NOTE)))
            .andExpect(jsonPath("$.[*].itemPickupExchangeComment").value(hasItem(DEFAULT_ITEM_PICKUP_EXCHANGE_COMMENT)))
            .andExpect(jsonPath("$.[*].itemPickupExchangeStatus").value(hasItem(DEFAULT_ITEM_PICKUP_EXCHANGE_STATUS)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].pickupExchangeItemUuid").value(hasItem(DEFAULT_PICKUP_EXCHANGE_ITEM_UUID.toString())));
    }

    @Test
    @Transactional
    void getPickupExchangeItems() throws Exception {
        // Initialize the database
        pickupExchangeItemsRepository.saveAndFlush(pickupExchangeItems);

        // Get the pickupExchangeItems
        restPickupExchangeItemsMockMvc
            .perform(get(ENTITY_API_URL_ID, pickupExchangeItems.getPickupExchangeItemId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.pickupExchangeItemId").value(pickupExchangeItems.getPickupExchangeItemId().intValue()))
            .andExpect(jsonPath("$.pickupExchangeId").value(DEFAULT_PICKUP_EXCHANGE_ID.intValue()))
            .andExpect(jsonPath("$.soId").value(DEFAULT_SO_ID.intValue()))
            .andExpect(jsonPath("$.itemId").value(DEFAULT_ITEM_ID.intValue()))
            .andExpect(jsonPath("$.itemNo").value(DEFAULT_ITEM_NO))
            .andExpect(jsonPath("$.itemName").value(DEFAULT_ITEM_NAME))
            .andExpect(jsonPath("$.whetherSerialized").value(DEFAULT_WHETHER_SERIALIZED))
            .andExpect(jsonPath("$.pickupItemSerialNo").value(DEFAULT_PICKUP_ITEM_SERIAL_NO))
            .andExpect(jsonPath("$.pickupItemAssetNo").value(DEFAULT_PICKUP_ITEM_ASSET_NO))
            .andExpect(jsonPath("$.replacementItemSerialNo").value(DEFAULT_REPLACEMENT_ITEM_SERIAL_NO))
            .andExpect(jsonPath("$.replacementItemAssetNo").value(DEFAULT_REPLACEMENT_ITEM_ASSET_NO))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY.intValue()))
            .andExpect(jsonPath("$.itemPickupExchangeType").value(DEFAULT_ITEM_PICKUP_EXCHANGE_TYPE))
            .andExpect(jsonPath("$.itemPickupExchangeNote").value(DEFAULT_ITEM_PICKUP_EXCHANGE_NOTE))
            .andExpect(jsonPath("$.itemPickupExchangeComment").value(DEFAULT_ITEM_PICKUP_EXCHANGE_COMMENT))
            .andExpect(jsonPath("$.itemPickupExchangeStatus").value(DEFAULT_ITEM_PICKUP_EXCHANGE_STATUS))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.pickupExchangeItemUuid").value(DEFAULT_PICKUP_EXCHANGE_ITEM_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingPickupExchangeItems() throws Exception {
        // Get the pickupExchangeItems
        restPickupExchangeItemsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewPickupExchangeItems() throws Exception {
        // Initialize the database
        pickupExchangeItemsRepository.saveAndFlush(pickupExchangeItems);

        int databaseSizeBeforeUpdate = pickupExchangeItemsRepository.findAll().size();

        // Update the pickupExchangeItems
        PickupExchangeItems updatedPickupExchangeItems = pickupExchangeItemsRepository
            .findById(pickupExchangeItems.getPickupExchangeItemId())
            .get();
        // Disconnect from session so that the updates on updatedPickupExchangeItems are not directly saved in db
        em.detach(updatedPickupExchangeItems);
        updatedPickupExchangeItems
            .pickupExchangeId(UPDATED_PICKUP_EXCHANGE_ID)
            .soId(UPDATED_SO_ID)
            .itemId(UPDATED_ITEM_ID)
            .itemNo(UPDATED_ITEM_NO)
            .itemName(UPDATED_ITEM_NAME)
            .whetherSerialized(UPDATED_WHETHER_SERIALIZED)
            .pickupItemSerialNo(UPDATED_PICKUP_ITEM_SERIAL_NO)
            .pickupItemAssetNo(UPDATED_PICKUP_ITEM_ASSET_NO)
            .replacementItemSerialNo(UPDATED_REPLACEMENT_ITEM_SERIAL_NO)
            .replacementItemAssetNo(UPDATED_REPLACEMENT_ITEM_ASSET_NO)
            .quantity(UPDATED_QUANTITY)
            .itemPickupExchangeType(UPDATED_ITEM_PICKUP_EXCHANGE_TYPE)
            .itemPickupExchangeNote(UPDATED_ITEM_PICKUP_EXCHANGE_NOTE)
            .itemPickupExchangeComment(UPDATED_ITEM_PICKUP_EXCHANGE_COMMENT)
            .itemPickupExchangeStatus(UPDATED_ITEM_PICKUP_EXCHANGE_STATUS)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .pickupExchangeItemUuid(UPDATED_PICKUP_EXCHANGE_ITEM_UUID);
        PickupExchangeItemsDTO pickupExchangeItemsDTO = pickupExchangeItemsMapper.toDto(updatedPickupExchangeItems);

        restPickupExchangeItemsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, pickupExchangeItemsDTO.getPickupExchangeItemId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pickupExchangeItemsDTO))
            )
            .andExpect(status().isOk());

        // Validate the PickupExchangeItems in the database
        List<PickupExchangeItems> pickupExchangeItemsList = pickupExchangeItemsRepository.findAll();
        assertThat(pickupExchangeItemsList).hasSize(databaseSizeBeforeUpdate);
        PickupExchangeItems testPickupExchangeItems = pickupExchangeItemsList.get(pickupExchangeItemsList.size() - 1);
        assertThat(testPickupExchangeItems.getPickupExchangeId()).isEqualTo(UPDATED_PICKUP_EXCHANGE_ID);
        assertThat(testPickupExchangeItems.getSoId()).isEqualTo(UPDATED_SO_ID);
        assertThat(testPickupExchangeItems.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testPickupExchangeItems.getItemNo()).isEqualTo(UPDATED_ITEM_NO);
        assertThat(testPickupExchangeItems.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testPickupExchangeItems.getWhetherSerialized()).isEqualTo(UPDATED_WHETHER_SERIALIZED);
        assertThat(testPickupExchangeItems.getPickupItemSerialNo()).isEqualTo(UPDATED_PICKUP_ITEM_SERIAL_NO);
        assertThat(testPickupExchangeItems.getPickupItemAssetNo()).isEqualTo(UPDATED_PICKUP_ITEM_ASSET_NO);
        assertThat(testPickupExchangeItems.getReplacementItemSerialNo()).isEqualTo(UPDATED_REPLACEMENT_ITEM_SERIAL_NO);
        assertThat(testPickupExchangeItems.getReplacementItemAssetNo()).isEqualTo(UPDATED_REPLACEMENT_ITEM_ASSET_NO);
        assertThat(testPickupExchangeItems.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testPickupExchangeItems.getItemPickupExchangeType()).isEqualTo(UPDATED_ITEM_PICKUP_EXCHANGE_TYPE);
        assertThat(testPickupExchangeItems.getItemPickupExchangeNote()).isEqualTo(UPDATED_ITEM_PICKUP_EXCHANGE_NOTE);
        assertThat(testPickupExchangeItems.getItemPickupExchangeComment()).isEqualTo(UPDATED_ITEM_PICKUP_EXCHANGE_COMMENT);
        assertThat(testPickupExchangeItems.getItemPickupExchangeStatus()).isEqualTo(UPDATED_ITEM_PICKUP_EXCHANGE_STATUS);
        assertThat(testPickupExchangeItems.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPickupExchangeItems.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPickupExchangeItems.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPickupExchangeItems.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPickupExchangeItems.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPickupExchangeItems.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPickupExchangeItems.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPickupExchangeItems.getPickupExchangeItemUuid()).isEqualTo(UPDATED_PICKUP_EXCHANGE_ITEM_UUID);
    }

    @Test
    @Transactional
    void putNonExistingPickupExchangeItems() throws Exception {
        int databaseSizeBeforeUpdate = pickupExchangeItemsRepository.findAll().size();
        pickupExchangeItems.setPickupExchangeItemId(count.incrementAndGet());

        // Create the PickupExchangeItems
        PickupExchangeItemsDTO pickupExchangeItemsDTO = pickupExchangeItemsMapper.toDto(pickupExchangeItems);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPickupExchangeItemsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, pickupExchangeItemsDTO.getPickupExchangeItemId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pickupExchangeItemsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PickupExchangeItems in the database
        List<PickupExchangeItems> pickupExchangeItemsList = pickupExchangeItemsRepository.findAll();
        assertThat(pickupExchangeItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPickupExchangeItems() throws Exception {
        int databaseSizeBeforeUpdate = pickupExchangeItemsRepository.findAll().size();
        pickupExchangeItems.setPickupExchangeItemId(count.incrementAndGet());

        // Create the PickupExchangeItems
        PickupExchangeItemsDTO pickupExchangeItemsDTO = pickupExchangeItemsMapper.toDto(pickupExchangeItems);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPickupExchangeItemsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pickupExchangeItemsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PickupExchangeItems in the database
        List<PickupExchangeItems> pickupExchangeItemsList = pickupExchangeItemsRepository.findAll();
        assertThat(pickupExchangeItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPickupExchangeItems() throws Exception {
        int databaseSizeBeforeUpdate = pickupExchangeItemsRepository.findAll().size();
        pickupExchangeItems.setPickupExchangeItemId(count.incrementAndGet());

        // Create the PickupExchangeItems
        PickupExchangeItemsDTO pickupExchangeItemsDTO = pickupExchangeItemsMapper.toDto(pickupExchangeItems);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPickupExchangeItemsMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pickupExchangeItemsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PickupExchangeItems in the database
        List<PickupExchangeItems> pickupExchangeItemsList = pickupExchangeItemsRepository.findAll();
        assertThat(pickupExchangeItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePickupExchangeItemsWithPatch() throws Exception {
        // Initialize the database
        pickupExchangeItemsRepository.saveAndFlush(pickupExchangeItems);

        int databaseSizeBeforeUpdate = pickupExchangeItemsRepository.findAll().size();

        // Update the pickupExchangeItems using partial update
        PickupExchangeItems partialUpdatedPickupExchangeItems = new PickupExchangeItems();
        partialUpdatedPickupExchangeItems.setPickupExchangeItemId(pickupExchangeItems.getPickupExchangeItemId());

        partialUpdatedPickupExchangeItems
            .soId(UPDATED_SO_ID)
            .replacementItemSerialNo(UPDATED_REPLACEMENT_ITEM_SERIAL_NO)
            .replacementItemAssetNo(UPDATED_REPLACEMENT_ITEM_ASSET_NO)
            .itemPickupExchangeNote(UPDATED_ITEM_PICKUP_EXCHANGE_NOTE)
            .itemPickupExchangeComment(UPDATED_ITEM_PICKUP_EXCHANGE_COMMENT)
            .itemPickupExchangeStatus(UPDATED_ITEM_PICKUP_EXCHANGE_STATUS)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID);

        restPickupExchangeItemsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPickupExchangeItems.getPickupExchangeItemId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPickupExchangeItems))
            )
            .andExpect(status().isOk());

        // Validate the PickupExchangeItems in the database
        List<PickupExchangeItems> pickupExchangeItemsList = pickupExchangeItemsRepository.findAll();
        assertThat(pickupExchangeItemsList).hasSize(databaseSizeBeforeUpdate);
        PickupExchangeItems testPickupExchangeItems = pickupExchangeItemsList.get(pickupExchangeItemsList.size() - 1);
        assertThat(testPickupExchangeItems.getPickupExchangeId()).isEqualTo(DEFAULT_PICKUP_EXCHANGE_ID);
        assertThat(testPickupExchangeItems.getSoId()).isEqualTo(UPDATED_SO_ID);
        assertThat(testPickupExchangeItems.getItemId()).isEqualTo(DEFAULT_ITEM_ID);
        assertThat(testPickupExchangeItems.getItemNo()).isEqualTo(DEFAULT_ITEM_NO);
        assertThat(testPickupExchangeItems.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testPickupExchangeItems.getWhetherSerialized()).isEqualTo(DEFAULT_WHETHER_SERIALIZED);
        assertThat(testPickupExchangeItems.getPickupItemSerialNo()).isEqualTo(DEFAULT_PICKUP_ITEM_SERIAL_NO);
        assertThat(testPickupExchangeItems.getPickupItemAssetNo()).isEqualTo(DEFAULT_PICKUP_ITEM_ASSET_NO);
        assertThat(testPickupExchangeItems.getReplacementItemSerialNo()).isEqualTo(UPDATED_REPLACEMENT_ITEM_SERIAL_NO);
        assertThat(testPickupExchangeItems.getReplacementItemAssetNo()).isEqualTo(UPDATED_REPLACEMENT_ITEM_ASSET_NO);
        assertThat(testPickupExchangeItems.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testPickupExchangeItems.getItemPickupExchangeType()).isEqualTo(DEFAULT_ITEM_PICKUP_EXCHANGE_TYPE);
        assertThat(testPickupExchangeItems.getItemPickupExchangeNote()).isEqualTo(UPDATED_ITEM_PICKUP_EXCHANGE_NOTE);
        assertThat(testPickupExchangeItems.getItemPickupExchangeComment()).isEqualTo(UPDATED_ITEM_PICKUP_EXCHANGE_COMMENT);
        assertThat(testPickupExchangeItems.getItemPickupExchangeStatus()).isEqualTo(UPDATED_ITEM_PICKUP_EXCHANGE_STATUS);
        assertThat(testPickupExchangeItems.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPickupExchangeItems.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPickupExchangeItems.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testPickupExchangeItems.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testPickupExchangeItems.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testPickupExchangeItems.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testPickupExchangeItems.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testPickupExchangeItems.getPickupExchangeItemUuid()).isEqualTo(DEFAULT_PICKUP_EXCHANGE_ITEM_UUID);
    }

    @Test
    @Transactional
    void fullUpdatePickupExchangeItemsWithPatch() throws Exception {
        // Initialize the database
        pickupExchangeItemsRepository.saveAndFlush(pickupExchangeItems);

        int databaseSizeBeforeUpdate = pickupExchangeItemsRepository.findAll().size();

        // Update the pickupExchangeItems using partial update
        PickupExchangeItems partialUpdatedPickupExchangeItems = new PickupExchangeItems();
        partialUpdatedPickupExchangeItems.setPickupExchangeItemId(pickupExchangeItems.getPickupExchangeItemId());

        partialUpdatedPickupExchangeItems
            .pickupExchangeId(UPDATED_PICKUP_EXCHANGE_ID)
            .soId(UPDATED_SO_ID)
            .itemId(UPDATED_ITEM_ID)
            .itemNo(UPDATED_ITEM_NO)
            .itemName(UPDATED_ITEM_NAME)
            .whetherSerialized(UPDATED_WHETHER_SERIALIZED)
            .pickupItemSerialNo(UPDATED_PICKUP_ITEM_SERIAL_NO)
            .pickupItemAssetNo(UPDATED_PICKUP_ITEM_ASSET_NO)
            .replacementItemSerialNo(UPDATED_REPLACEMENT_ITEM_SERIAL_NO)
            .replacementItemAssetNo(UPDATED_REPLACEMENT_ITEM_ASSET_NO)
            .quantity(UPDATED_QUANTITY)
            .itemPickupExchangeType(UPDATED_ITEM_PICKUP_EXCHANGE_TYPE)
            .itemPickupExchangeNote(UPDATED_ITEM_PICKUP_EXCHANGE_NOTE)
            .itemPickupExchangeComment(UPDATED_ITEM_PICKUP_EXCHANGE_COMMENT)
            .itemPickupExchangeStatus(UPDATED_ITEM_PICKUP_EXCHANGE_STATUS)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .pickupExchangeItemUuid(UPDATED_PICKUP_EXCHANGE_ITEM_UUID);

        restPickupExchangeItemsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPickupExchangeItems.getPickupExchangeItemId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPickupExchangeItems))
            )
            .andExpect(status().isOk());

        // Validate the PickupExchangeItems in the database
        List<PickupExchangeItems> pickupExchangeItemsList = pickupExchangeItemsRepository.findAll();
        assertThat(pickupExchangeItemsList).hasSize(databaseSizeBeforeUpdate);
        PickupExchangeItems testPickupExchangeItems = pickupExchangeItemsList.get(pickupExchangeItemsList.size() - 1);
        assertThat(testPickupExchangeItems.getPickupExchangeId()).isEqualTo(UPDATED_PICKUP_EXCHANGE_ID);
        assertThat(testPickupExchangeItems.getSoId()).isEqualTo(UPDATED_SO_ID);
        assertThat(testPickupExchangeItems.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testPickupExchangeItems.getItemNo()).isEqualTo(UPDATED_ITEM_NO);
        assertThat(testPickupExchangeItems.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testPickupExchangeItems.getWhetherSerialized()).isEqualTo(UPDATED_WHETHER_SERIALIZED);
        assertThat(testPickupExchangeItems.getPickupItemSerialNo()).isEqualTo(UPDATED_PICKUP_ITEM_SERIAL_NO);
        assertThat(testPickupExchangeItems.getPickupItemAssetNo()).isEqualTo(UPDATED_PICKUP_ITEM_ASSET_NO);
        assertThat(testPickupExchangeItems.getReplacementItemSerialNo()).isEqualTo(UPDATED_REPLACEMENT_ITEM_SERIAL_NO);
        assertThat(testPickupExchangeItems.getReplacementItemAssetNo()).isEqualTo(UPDATED_REPLACEMENT_ITEM_ASSET_NO);
        assertThat(testPickupExchangeItems.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testPickupExchangeItems.getItemPickupExchangeType()).isEqualTo(UPDATED_ITEM_PICKUP_EXCHANGE_TYPE);
        assertThat(testPickupExchangeItems.getItemPickupExchangeNote()).isEqualTo(UPDATED_ITEM_PICKUP_EXCHANGE_NOTE);
        assertThat(testPickupExchangeItems.getItemPickupExchangeComment()).isEqualTo(UPDATED_ITEM_PICKUP_EXCHANGE_COMMENT);
        assertThat(testPickupExchangeItems.getItemPickupExchangeStatus()).isEqualTo(UPDATED_ITEM_PICKUP_EXCHANGE_STATUS);
        assertThat(testPickupExchangeItems.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPickupExchangeItems.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPickupExchangeItems.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPickupExchangeItems.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPickupExchangeItems.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPickupExchangeItems.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPickupExchangeItems.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPickupExchangeItems.getPickupExchangeItemUuid()).isEqualTo(UPDATED_PICKUP_EXCHANGE_ITEM_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingPickupExchangeItems() throws Exception {
        int databaseSizeBeforeUpdate = pickupExchangeItemsRepository.findAll().size();
        pickupExchangeItems.setPickupExchangeItemId(count.incrementAndGet());

        // Create the PickupExchangeItems
        PickupExchangeItemsDTO pickupExchangeItemsDTO = pickupExchangeItemsMapper.toDto(pickupExchangeItems);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPickupExchangeItemsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, pickupExchangeItemsDTO.getPickupExchangeItemId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pickupExchangeItemsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PickupExchangeItems in the database
        List<PickupExchangeItems> pickupExchangeItemsList = pickupExchangeItemsRepository.findAll();
        assertThat(pickupExchangeItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPickupExchangeItems() throws Exception {
        int databaseSizeBeforeUpdate = pickupExchangeItemsRepository.findAll().size();
        pickupExchangeItems.setPickupExchangeItemId(count.incrementAndGet());

        // Create the PickupExchangeItems
        PickupExchangeItemsDTO pickupExchangeItemsDTO = pickupExchangeItemsMapper.toDto(pickupExchangeItems);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPickupExchangeItemsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pickupExchangeItemsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PickupExchangeItems in the database
        List<PickupExchangeItems> pickupExchangeItemsList = pickupExchangeItemsRepository.findAll();
        assertThat(pickupExchangeItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPickupExchangeItems() throws Exception {
        int databaseSizeBeforeUpdate = pickupExchangeItemsRepository.findAll().size();
        pickupExchangeItems.setPickupExchangeItemId(count.incrementAndGet());

        // Create the PickupExchangeItems
        PickupExchangeItemsDTO pickupExchangeItemsDTO = pickupExchangeItemsMapper.toDto(pickupExchangeItems);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPickupExchangeItemsMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pickupExchangeItemsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PickupExchangeItems in the database
        List<PickupExchangeItems> pickupExchangeItemsList = pickupExchangeItemsRepository.findAll();
        assertThat(pickupExchangeItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePickupExchangeItems() throws Exception {
        // Initialize the database
        pickupExchangeItemsRepository.saveAndFlush(pickupExchangeItems);

        int databaseSizeBeforeDelete = pickupExchangeItemsRepository.findAll().size();

        // Delete the pickupExchangeItems
        restPickupExchangeItemsMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, pickupExchangeItems.getPickupExchangeItemId()).with(csrf()).accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PickupExchangeItems> pickupExchangeItemsList = pickupExchangeItemsRepository.findAll();
        assertThat(pickupExchangeItemsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
