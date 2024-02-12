package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.PickupExchangeItems;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.PickupExchangeItemsRepository;
import com.sunknowledge.dme.rcm.service.dto.PickupExchangeItemsDTO;
import com.sunknowledge.dme.rcm.service.mapper.PickupExchangeItemsMapper;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link PickupExchangeItemsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
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

    private static final Double DEFAULT_QUANTITY = 1D;
    private static final Double UPDATED_QUANTITY = 2D;

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
    private WebTestClient webTestClient;

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

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(PickupExchangeItems.class).block();
        } catch (Exception e) {
            // It can fail, if other entities are still referring this - it will be removed later.
        }
    }

    @AfterEach
    public void cleanup() {
        deleteEntities(em);
    }

    @BeforeEach
    public void setupCsrf() {
        webTestClient = webTestClient.mutateWith(csrf());
    }

    @BeforeEach
    public void initTest() {
        deleteEntities(em);
        pickupExchangeItems = createEntity(em);
    }

    @Test
    void createPickupExchangeItems() throws Exception {
        int databaseSizeBeforeCreate = pickupExchangeItemsRepository.findAll().collectList().block().size();
        // Create the PickupExchangeItems
        PickupExchangeItemsDTO pickupExchangeItemsDTO = pickupExchangeItemsMapper.toDto(pickupExchangeItems);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(pickupExchangeItemsDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the PickupExchangeItems in the database
        List<PickupExchangeItems> pickupExchangeItemsList = pickupExchangeItemsRepository.findAll().collectList().block();
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
    void createPickupExchangeItemsWithExistingId() throws Exception {
        // Create the PickupExchangeItems with an existing ID
        pickupExchangeItems.setPickupExchangeItemId(1L);
        PickupExchangeItemsDTO pickupExchangeItemsDTO = pickupExchangeItemsMapper.toDto(pickupExchangeItems);

        int databaseSizeBeforeCreate = pickupExchangeItemsRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(pickupExchangeItemsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PickupExchangeItems in the database
        List<PickupExchangeItems> pickupExchangeItemsList = pickupExchangeItemsRepository.findAll().collectList().block();
        assertThat(pickupExchangeItemsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllPickupExchangeItems() {
        // Initialize the database
        pickupExchangeItemsRepository.save(pickupExchangeItems).block();

        // Get all the pickupExchangeItemsList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=pickupExchangeItemId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].pickupExchangeItemId")
            .value(hasItem(pickupExchangeItems.getPickupExchangeItemId().intValue()))
            .jsonPath("$.[*].pickupExchangeId")
            .value(hasItem(DEFAULT_PICKUP_EXCHANGE_ID.intValue()))
            .jsonPath("$.[*].soId")
            .value(hasItem(DEFAULT_SO_ID.intValue()))
            .jsonPath("$.[*].itemId")
            .value(hasItem(DEFAULT_ITEM_ID.intValue()))
            .jsonPath("$.[*].itemNo")
            .value(hasItem(DEFAULT_ITEM_NO))
            .jsonPath("$.[*].itemName")
            .value(hasItem(DEFAULT_ITEM_NAME))
            .jsonPath("$.[*].whetherSerialized")
            .value(hasItem(DEFAULT_WHETHER_SERIALIZED))
            .jsonPath("$.[*].pickupItemSerialNo")
            .value(hasItem(DEFAULT_PICKUP_ITEM_SERIAL_NO))
            .jsonPath("$.[*].pickupItemAssetNo")
            .value(hasItem(DEFAULT_PICKUP_ITEM_ASSET_NO))
            .jsonPath("$.[*].replacementItemSerialNo")
            .value(hasItem(DEFAULT_REPLACEMENT_ITEM_SERIAL_NO))
            .jsonPath("$.[*].replacementItemAssetNo")
            .value(hasItem(DEFAULT_REPLACEMENT_ITEM_ASSET_NO))
            .jsonPath("$.[*].quantity")
            .value(hasItem(DEFAULT_QUANTITY.doubleValue()))
            .jsonPath("$.[*].itemPickupExchangeType")
            .value(hasItem(DEFAULT_ITEM_PICKUP_EXCHANGE_TYPE))
            .jsonPath("$.[*].itemPickupExchangeNote")
            .value(hasItem(DEFAULT_ITEM_PICKUP_EXCHANGE_NOTE))
            .jsonPath("$.[*].itemPickupExchangeComment")
            .value(hasItem(DEFAULT_ITEM_PICKUP_EXCHANGE_COMMENT))
            .jsonPath("$.[*].itemPickupExchangeStatus")
            .value(hasItem(DEFAULT_ITEM_PICKUP_EXCHANGE_STATUS))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS))
            .jsonPath("$.[*].createdById")
            .value(hasItem(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.[*].createdByName")
            .value(hasItem(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.[*].createdDate")
            .value(hasItem(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.[*].updatedById")
            .value(hasItem(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.[*].updatedByName")
            .value(hasItem(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.[*].updatedDate")
            .value(hasItem(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.[*].pickupExchangeItemUuid")
            .value(hasItem(DEFAULT_PICKUP_EXCHANGE_ITEM_UUID.toString()));
    }

    @Test
    void getPickupExchangeItems() {
        // Initialize the database
        pickupExchangeItemsRepository.save(pickupExchangeItems).block();

        // Get the pickupExchangeItems
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, pickupExchangeItems.getPickupExchangeItemId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.pickupExchangeItemId")
            .value(is(pickupExchangeItems.getPickupExchangeItemId().intValue()))
            .jsonPath("$.pickupExchangeId")
            .value(is(DEFAULT_PICKUP_EXCHANGE_ID.intValue()))
            .jsonPath("$.soId")
            .value(is(DEFAULT_SO_ID.intValue()))
            .jsonPath("$.itemId")
            .value(is(DEFAULT_ITEM_ID.intValue()))
            .jsonPath("$.itemNo")
            .value(is(DEFAULT_ITEM_NO))
            .jsonPath("$.itemName")
            .value(is(DEFAULT_ITEM_NAME))
            .jsonPath("$.whetherSerialized")
            .value(is(DEFAULT_WHETHER_SERIALIZED))
            .jsonPath("$.pickupItemSerialNo")
            .value(is(DEFAULT_PICKUP_ITEM_SERIAL_NO))
            .jsonPath("$.pickupItemAssetNo")
            .value(is(DEFAULT_PICKUP_ITEM_ASSET_NO))
            .jsonPath("$.replacementItemSerialNo")
            .value(is(DEFAULT_REPLACEMENT_ITEM_SERIAL_NO))
            .jsonPath("$.replacementItemAssetNo")
            .value(is(DEFAULT_REPLACEMENT_ITEM_ASSET_NO))
            .jsonPath("$.quantity")
            .value(is(DEFAULT_QUANTITY.doubleValue()))
            .jsonPath("$.itemPickupExchangeType")
            .value(is(DEFAULT_ITEM_PICKUP_EXCHANGE_TYPE))
            .jsonPath("$.itemPickupExchangeNote")
            .value(is(DEFAULT_ITEM_PICKUP_EXCHANGE_NOTE))
            .jsonPath("$.itemPickupExchangeComment")
            .value(is(DEFAULT_ITEM_PICKUP_EXCHANGE_COMMENT))
            .jsonPath("$.itemPickupExchangeStatus")
            .value(is(DEFAULT_ITEM_PICKUP_EXCHANGE_STATUS))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS))
            .jsonPath("$.createdById")
            .value(is(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.createdByName")
            .value(is(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.createdDate")
            .value(is(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.updatedById")
            .value(is(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.updatedByName")
            .value(is(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.updatedDate")
            .value(is(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.pickupExchangeItemUuid")
            .value(is(DEFAULT_PICKUP_EXCHANGE_ITEM_UUID.toString()));
    }

    @Test
    void getNonExistingPickupExchangeItems() {
        // Get the pickupExchangeItems
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingPickupExchangeItems() throws Exception {
        // Initialize the database
        pickupExchangeItemsRepository.save(pickupExchangeItems).block();

        int databaseSizeBeforeUpdate = pickupExchangeItemsRepository.findAll().collectList().block().size();

        // Update the pickupExchangeItems
        PickupExchangeItems updatedPickupExchangeItems = pickupExchangeItemsRepository
            .findById(pickupExchangeItems.getPickupExchangeItemId())
            .block();
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

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, pickupExchangeItemsDTO.getPickupExchangeItemId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(pickupExchangeItemsDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PickupExchangeItems in the database
        List<PickupExchangeItems> pickupExchangeItemsList = pickupExchangeItemsRepository.findAll().collectList().block();
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
    void putNonExistingPickupExchangeItems() throws Exception {
        int databaseSizeBeforeUpdate = pickupExchangeItemsRepository.findAll().collectList().block().size();
        pickupExchangeItems.setPickupExchangeItemId(count.incrementAndGet());

        // Create the PickupExchangeItems
        PickupExchangeItemsDTO pickupExchangeItemsDTO = pickupExchangeItemsMapper.toDto(pickupExchangeItems);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, pickupExchangeItemsDTO.getPickupExchangeItemId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(pickupExchangeItemsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PickupExchangeItems in the database
        List<PickupExchangeItems> pickupExchangeItemsList = pickupExchangeItemsRepository.findAll().collectList().block();
        assertThat(pickupExchangeItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchPickupExchangeItems() throws Exception {
        int databaseSizeBeforeUpdate = pickupExchangeItemsRepository.findAll().collectList().block().size();
        pickupExchangeItems.setPickupExchangeItemId(count.incrementAndGet());

        // Create the PickupExchangeItems
        PickupExchangeItemsDTO pickupExchangeItemsDTO = pickupExchangeItemsMapper.toDto(pickupExchangeItems);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(pickupExchangeItemsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PickupExchangeItems in the database
        List<PickupExchangeItems> pickupExchangeItemsList = pickupExchangeItemsRepository.findAll().collectList().block();
        assertThat(pickupExchangeItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamPickupExchangeItems() throws Exception {
        int databaseSizeBeforeUpdate = pickupExchangeItemsRepository.findAll().collectList().block().size();
        pickupExchangeItems.setPickupExchangeItemId(count.incrementAndGet());

        // Create the PickupExchangeItems
        PickupExchangeItemsDTO pickupExchangeItemsDTO = pickupExchangeItemsMapper.toDto(pickupExchangeItems);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(pickupExchangeItemsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PickupExchangeItems in the database
        List<PickupExchangeItems> pickupExchangeItemsList = pickupExchangeItemsRepository.findAll().collectList().block();
        assertThat(pickupExchangeItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdatePickupExchangeItemsWithPatch() throws Exception {
        // Initialize the database
        pickupExchangeItemsRepository.save(pickupExchangeItems).block();

        int databaseSizeBeforeUpdate = pickupExchangeItemsRepository.findAll().collectList().block().size();

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

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPickupExchangeItems.getPickupExchangeItemId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPickupExchangeItems))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PickupExchangeItems in the database
        List<PickupExchangeItems> pickupExchangeItemsList = pickupExchangeItemsRepository.findAll().collectList().block();
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
    void fullUpdatePickupExchangeItemsWithPatch() throws Exception {
        // Initialize the database
        pickupExchangeItemsRepository.save(pickupExchangeItems).block();

        int databaseSizeBeforeUpdate = pickupExchangeItemsRepository.findAll().collectList().block().size();

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

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPickupExchangeItems.getPickupExchangeItemId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPickupExchangeItems))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PickupExchangeItems in the database
        List<PickupExchangeItems> pickupExchangeItemsList = pickupExchangeItemsRepository.findAll().collectList().block();
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
    void patchNonExistingPickupExchangeItems() throws Exception {
        int databaseSizeBeforeUpdate = pickupExchangeItemsRepository.findAll().collectList().block().size();
        pickupExchangeItems.setPickupExchangeItemId(count.incrementAndGet());

        // Create the PickupExchangeItems
        PickupExchangeItemsDTO pickupExchangeItemsDTO = pickupExchangeItemsMapper.toDto(pickupExchangeItems);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, pickupExchangeItemsDTO.getPickupExchangeItemId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(pickupExchangeItemsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PickupExchangeItems in the database
        List<PickupExchangeItems> pickupExchangeItemsList = pickupExchangeItemsRepository.findAll().collectList().block();
        assertThat(pickupExchangeItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchPickupExchangeItems() throws Exception {
        int databaseSizeBeforeUpdate = pickupExchangeItemsRepository.findAll().collectList().block().size();
        pickupExchangeItems.setPickupExchangeItemId(count.incrementAndGet());

        // Create the PickupExchangeItems
        PickupExchangeItemsDTO pickupExchangeItemsDTO = pickupExchangeItemsMapper.toDto(pickupExchangeItems);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(pickupExchangeItemsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PickupExchangeItems in the database
        List<PickupExchangeItems> pickupExchangeItemsList = pickupExchangeItemsRepository.findAll().collectList().block();
        assertThat(pickupExchangeItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamPickupExchangeItems() throws Exception {
        int databaseSizeBeforeUpdate = pickupExchangeItemsRepository.findAll().collectList().block().size();
        pickupExchangeItems.setPickupExchangeItemId(count.incrementAndGet());

        // Create the PickupExchangeItems
        PickupExchangeItemsDTO pickupExchangeItemsDTO = pickupExchangeItemsMapper.toDto(pickupExchangeItems);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(pickupExchangeItemsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PickupExchangeItems in the database
        List<PickupExchangeItems> pickupExchangeItemsList = pickupExchangeItemsRepository.findAll().collectList().block();
        assertThat(pickupExchangeItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deletePickupExchangeItems() {
        // Initialize the database
        pickupExchangeItemsRepository.save(pickupExchangeItems).block();

        int databaseSizeBeforeDelete = pickupExchangeItemsRepository.findAll().collectList().block().size();

        // Delete the pickupExchangeItems
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, pickupExchangeItems.getPickupExchangeItemId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<PickupExchangeItems> pickupExchangeItemsList = pickupExchangeItemsRepository.findAll().collectList().block();
        assertThat(pickupExchangeItemsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
