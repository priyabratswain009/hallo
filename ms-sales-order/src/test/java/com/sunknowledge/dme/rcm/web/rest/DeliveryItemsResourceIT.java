package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.DeliveryItems;
import com.sunknowledge.dme.rcm.repository.DeliveryItemsRepository;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.service.dto.DeliveryItemsDTO;
import com.sunknowledge.dme.rcm.service.mapper.DeliveryItemsMapper;
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
 * Integration tests for the {@link DeliveryItemsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class DeliveryItemsResourceIT {

    private static final Long DEFAULT_DELIVERY_TICKET_ID = 1L;
    private static final Long UPDATED_DELIVERY_TICKET_ID = 2L;

    private static final String DEFAULT_DELIVERY_TICKET_NO = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_TICKET_NO = "BBBBBBBBBB";

    private static final Long DEFAULT_SO_ID = 1L;
    private static final Long UPDATED_SO_ID = 2L;

    private static final String DEFAULT_SO_NO = "AAAAAAAAAA";
    private static final String UPDATED_SO_NO = "BBBBBBBBBB";

    private static final Long DEFAULT_ITEM_ID = 1L;
    private static final Long UPDATED_ITEM_ID = 2L;

    private static final String DEFAULT_ITEM_NO = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NO = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_HCPCS_CODE = "AAAAAAAAAA";
    private static final String UPDATED_HCPCS_CODE = "BBBBBBBBBB";

    private static final Integer DEFAULT_ITEM_QUANTITY = 1;
    private static final Integer UPDATED_ITEM_QUANTITY = 2;

    private static final String DEFAULT_ITEM_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_UNIT = "BBBBBBBBBB";

    private static final String DEFAULT_WHETHER_ITEM_SERIALIZED = "AAAAAAAAAA";
    private static final String UPDATED_WHETHER_ITEM_SERIALIZED = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_SERIAL = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_SERIAL = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_BATCH_LOT_NO = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_BATCH_LOT_NO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_ITEM_MFG_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ITEM_MFG_DATE = LocalDate.now(ZoneId.systemDefault());

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

    private static final UUID DEFAULT_DELIVERY_ITEMS_UUID = UUID.randomUUID();
    private static final UUID UPDATED_DELIVERY_ITEMS_UUID = UUID.randomUUID();

    private static final Double DEFAULT_CHARGED_AMOUNT = 1D;
    private static final Double UPDATED_CHARGED_AMOUNT = 2D;

    private static final Double DEFAULT_ALLOWED_AMOUNT = 1D;
    private static final Double UPDATED_ALLOWED_AMOUNT = 2D;

    private static final String DEFAULT_ITEM_MANUFACTURER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_MANUFACTURER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_IS_DROPSHIP = "AAAAAAAAAA";
    private static final String UPDATED_IS_DROPSHIP = "BBBBBBBBBB";

    private static final String DEFAULT_PO_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PO_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_SO_SALE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SO_SALE_TYPE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/delivery-items";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{deliveryItemId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DeliveryItemsRepository deliveryItemsRepository;

    @Autowired
    private DeliveryItemsMapper deliveryItemsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private DeliveryItems deliveryItems;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DeliveryItems createEntity(EntityManager em) {
        DeliveryItems deliveryItems = new DeliveryItems()
            .deliveryTicketId(DEFAULT_DELIVERY_TICKET_ID)
            .deliveryTicketNo(DEFAULT_DELIVERY_TICKET_NO)
            .soId(DEFAULT_SO_ID)
            .soNo(DEFAULT_SO_NO)
            .itemId(DEFAULT_ITEM_ID)
            .itemNo(DEFAULT_ITEM_NO)
            .itemName(DEFAULT_ITEM_NAME)
            .itemDescription(DEFAULT_ITEM_DESCRIPTION)
            .hcpcsCode(DEFAULT_HCPCS_CODE)
            .itemQuantity(DEFAULT_ITEM_QUANTITY)
            .itemUnit(DEFAULT_ITEM_UNIT)
            .whetherItemSerialized(DEFAULT_WHETHER_ITEM_SERIALIZED)
            .itemSerial(DEFAULT_ITEM_SERIAL)
            .itemBatchLotNo(DEFAULT_ITEM_BATCH_LOT_NO)
            .itemMfgDate(DEFAULT_ITEM_MFG_DATE)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .deliveryItemsUuid(DEFAULT_DELIVERY_ITEMS_UUID)
            .chargedAmount(DEFAULT_CHARGED_AMOUNT)
            .allowedAmount(DEFAULT_ALLOWED_AMOUNT)
            .itemManufacturerName(DEFAULT_ITEM_MANUFACTURER_NAME)
            .isDropship(DEFAULT_IS_DROPSHIP)
            .poNumber(DEFAULT_PO_NUMBER)
            .soSaleType(DEFAULT_SO_SALE_TYPE);
        return deliveryItems;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DeliveryItems createUpdatedEntity(EntityManager em) {
        DeliveryItems deliveryItems = new DeliveryItems()
            .deliveryTicketId(UPDATED_DELIVERY_TICKET_ID)
            .deliveryTicketNo(UPDATED_DELIVERY_TICKET_NO)
            .soId(UPDATED_SO_ID)
            .soNo(UPDATED_SO_NO)
            .itemId(UPDATED_ITEM_ID)
            .itemNo(UPDATED_ITEM_NO)
            .itemName(UPDATED_ITEM_NAME)
            .itemDescription(UPDATED_ITEM_DESCRIPTION)
            .hcpcsCode(UPDATED_HCPCS_CODE)
            .itemQuantity(UPDATED_ITEM_QUANTITY)
            .itemUnit(UPDATED_ITEM_UNIT)
            .whetherItemSerialized(UPDATED_WHETHER_ITEM_SERIALIZED)
            .itemSerial(UPDATED_ITEM_SERIAL)
            .itemBatchLotNo(UPDATED_ITEM_BATCH_LOT_NO)
            .itemMfgDate(UPDATED_ITEM_MFG_DATE)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .deliveryItemsUuid(UPDATED_DELIVERY_ITEMS_UUID)
            .chargedAmount(UPDATED_CHARGED_AMOUNT)
            .allowedAmount(UPDATED_ALLOWED_AMOUNT)
            .itemManufacturerName(UPDATED_ITEM_MANUFACTURER_NAME)
            .isDropship(UPDATED_IS_DROPSHIP)
            .poNumber(UPDATED_PO_NUMBER)
            .soSaleType(UPDATED_SO_SALE_TYPE);
        return deliveryItems;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(DeliveryItems.class).block();
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
        deliveryItems = createEntity(em);
    }

    @Test
    void createDeliveryItems() throws Exception {
        int databaseSizeBeforeCreate = deliveryItemsRepository.findAll().collectList().block().size();
        // Create the DeliveryItems
        DeliveryItemsDTO deliveryItemsDTO = deliveryItemsMapper.toDto(deliveryItems);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(deliveryItemsDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the DeliveryItems in the database
        List<DeliveryItems> deliveryItemsList = deliveryItemsRepository.findAll().collectList().block();
        assertThat(deliveryItemsList).hasSize(databaseSizeBeforeCreate + 1);
        DeliveryItems testDeliveryItems = deliveryItemsList.get(deliveryItemsList.size() - 1);
        assertThat(testDeliveryItems.getDeliveryTicketId()).isEqualTo(DEFAULT_DELIVERY_TICKET_ID);
        assertThat(testDeliveryItems.getDeliveryTicketNo()).isEqualTo(DEFAULT_DELIVERY_TICKET_NO);
        assertThat(testDeliveryItems.getSoId()).isEqualTo(DEFAULT_SO_ID);
        assertThat(testDeliveryItems.getSoNo()).isEqualTo(DEFAULT_SO_NO);
        assertThat(testDeliveryItems.getItemId()).isEqualTo(DEFAULT_ITEM_ID);
        assertThat(testDeliveryItems.getItemNo()).isEqualTo(DEFAULT_ITEM_NO);
        assertThat(testDeliveryItems.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testDeliveryItems.getItemDescription()).isEqualTo(DEFAULT_ITEM_DESCRIPTION);
        assertThat(testDeliveryItems.getHcpcsCode()).isEqualTo(DEFAULT_HCPCS_CODE);
        assertThat(testDeliveryItems.getItemQuantity()).isEqualTo(DEFAULT_ITEM_QUANTITY);
        assertThat(testDeliveryItems.getItemUnit()).isEqualTo(DEFAULT_ITEM_UNIT);
        assertThat(testDeliveryItems.getWhetherItemSerialized()).isEqualTo(DEFAULT_WHETHER_ITEM_SERIALIZED);
        assertThat(testDeliveryItems.getItemSerial()).isEqualTo(DEFAULT_ITEM_SERIAL);
        assertThat(testDeliveryItems.getItemBatchLotNo()).isEqualTo(DEFAULT_ITEM_BATCH_LOT_NO);
        assertThat(testDeliveryItems.getItemMfgDate()).isEqualTo(DEFAULT_ITEM_MFG_DATE);
        assertThat(testDeliveryItems.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testDeliveryItems.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testDeliveryItems.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testDeliveryItems.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testDeliveryItems.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testDeliveryItems.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testDeliveryItems.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testDeliveryItems.getDeliveryItemsUuid()).isEqualTo(DEFAULT_DELIVERY_ITEMS_UUID);
        assertThat(testDeliveryItems.getChargedAmount()).isEqualTo(DEFAULT_CHARGED_AMOUNT);
        assertThat(testDeliveryItems.getAllowedAmount()).isEqualTo(DEFAULT_ALLOWED_AMOUNT);
        assertThat(testDeliveryItems.getItemManufacturerName()).isEqualTo(DEFAULT_ITEM_MANUFACTURER_NAME);
        assertThat(testDeliveryItems.getIsDropship()).isEqualTo(DEFAULT_IS_DROPSHIP);
        assertThat(testDeliveryItems.getPoNumber()).isEqualTo(DEFAULT_PO_NUMBER);
        assertThat(testDeliveryItems.getSoSaleType()).isEqualTo(DEFAULT_SO_SALE_TYPE);
    }

    @Test
    void createDeliveryItemsWithExistingId() throws Exception {
        // Create the DeliveryItems with an existing ID
        deliveryItems.setDeliveryItemId(1L);
        DeliveryItemsDTO deliveryItemsDTO = deliveryItemsMapper.toDto(deliveryItems);

        int databaseSizeBeforeCreate = deliveryItemsRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(deliveryItemsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DeliveryItems in the database
        List<DeliveryItems> deliveryItemsList = deliveryItemsRepository.findAll().collectList().block();
        assertThat(deliveryItemsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllDeliveryItems() {
        // Initialize the database
        deliveryItemsRepository.save(deliveryItems).block();

        // Get all the deliveryItemsList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=deliveryItemId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].deliveryItemId")
            .value(hasItem(deliveryItems.getDeliveryItemId().intValue()))
            .jsonPath("$.[*].deliveryTicketId")
            .value(hasItem(DEFAULT_DELIVERY_TICKET_ID.intValue()))
            .jsonPath("$.[*].deliveryTicketNo")
            .value(hasItem(DEFAULT_DELIVERY_TICKET_NO))
            .jsonPath("$.[*].soId")
            .value(hasItem(DEFAULT_SO_ID.intValue()))
            .jsonPath("$.[*].soNo")
            .value(hasItem(DEFAULT_SO_NO))
            .jsonPath("$.[*].itemId")
            .value(hasItem(DEFAULT_ITEM_ID.intValue()))
            .jsonPath("$.[*].itemNo")
            .value(hasItem(DEFAULT_ITEM_NO))
            .jsonPath("$.[*].itemName")
            .value(hasItem(DEFAULT_ITEM_NAME))
            .jsonPath("$.[*].itemDescription")
            .value(hasItem(DEFAULT_ITEM_DESCRIPTION))
            .jsonPath("$.[*].hcpcsCode")
            .value(hasItem(DEFAULT_HCPCS_CODE))
            .jsonPath("$.[*].itemQuantity")
            .value(hasItem(DEFAULT_ITEM_QUANTITY))
            .jsonPath("$.[*].itemUnit")
            .value(hasItem(DEFAULT_ITEM_UNIT))
            .jsonPath("$.[*].whetherItemSerialized")
            .value(hasItem(DEFAULT_WHETHER_ITEM_SERIALIZED))
            .jsonPath("$.[*].itemSerial")
            .value(hasItem(DEFAULT_ITEM_SERIAL))
            .jsonPath("$.[*].itemBatchLotNo")
            .value(hasItem(DEFAULT_ITEM_BATCH_LOT_NO))
            .jsonPath("$.[*].itemMfgDate")
            .value(hasItem(DEFAULT_ITEM_MFG_DATE.toString()))
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
            .jsonPath("$.[*].deliveryItemsUuid")
            .value(hasItem(DEFAULT_DELIVERY_ITEMS_UUID.toString()))
            .jsonPath("$.[*].chargedAmount")
            .value(hasItem(DEFAULT_CHARGED_AMOUNT.doubleValue()))
            .jsonPath("$.[*].allowedAmount")
            .value(hasItem(DEFAULT_ALLOWED_AMOUNT.doubleValue()))
            .jsonPath("$.[*].itemManufacturerName")
            .value(hasItem(DEFAULT_ITEM_MANUFACTURER_NAME))
            .jsonPath("$.[*].isDropship")
            .value(hasItem(DEFAULT_IS_DROPSHIP))
            .jsonPath("$.[*].poNumber")
            .value(hasItem(DEFAULT_PO_NUMBER))
            .jsonPath("$.[*].soSaleType")
            .value(hasItem(DEFAULT_SO_SALE_TYPE));
    }

    @Test
    void getDeliveryItems() {
        // Initialize the database
        deliveryItemsRepository.save(deliveryItems).block();

        // Get the deliveryItems
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, deliveryItems.getDeliveryItemId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.deliveryItemId")
            .value(is(deliveryItems.getDeliveryItemId().intValue()))
            .jsonPath("$.deliveryTicketId")
            .value(is(DEFAULT_DELIVERY_TICKET_ID.intValue()))
            .jsonPath("$.deliveryTicketNo")
            .value(is(DEFAULT_DELIVERY_TICKET_NO))
            .jsonPath("$.soId")
            .value(is(DEFAULT_SO_ID.intValue()))
            .jsonPath("$.soNo")
            .value(is(DEFAULT_SO_NO))
            .jsonPath("$.itemId")
            .value(is(DEFAULT_ITEM_ID.intValue()))
            .jsonPath("$.itemNo")
            .value(is(DEFAULT_ITEM_NO))
            .jsonPath("$.itemName")
            .value(is(DEFAULT_ITEM_NAME))
            .jsonPath("$.itemDescription")
            .value(is(DEFAULT_ITEM_DESCRIPTION))
            .jsonPath("$.hcpcsCode")
            .value(is(DEFAULT_HCPCS_CODE))
            .jsonPath("$.itemQuantity")
            .value(is(DEFAULT_ITEM_QUANTITY))
            .jsonPath("$.itemUnit")
            .value(is(DEFAULT_ITEM_UNIT))
            .jsonPath("$.whetherItemSerialized")
            .value(is(DEFAULT_WHETHER_ITEM_SERIALIZED))
            .jsonPath("$.itemSerial")
            .value(is(DEFAULT_ITEM_SERIAL))
            .jsonPath("$.itemBatchLotNo")
            .value(is(DEFAULT_ITEM_BATCH_LOT_NO))
            .jsonPath("$.itemMfgDate")
            .value(is(DEFAULT_ITEM_MFG_DATE.toString()))
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
            .jsonPath("$.deliveryItemsUuid")
            .value(is(DEFAULT_DELIVERY_ITEMS_UUID.toString()))
            .jsonPath("$.chargedAmount")
            .value(is(DEFAULT_CHARGED_AMOUNT.doubleValue()))
            .jsonPath("$.allowedAmount")
            .value(is(DEFAULT_ALLOWED_AMOUNT.doubleValue()))
            .jsonPath("$.itemManufacturerName")
            .value(is(DEFAULT_ITEM_MANUFACTURER_NAME))
            .jsonPath("$.isDropship")
            .value(is(DEFAULT_IS_DROPSHIP))
            .jsonPath("$.poNumber")
            .value(is(DEFAULT_PO_NUMBER))
            .jsonPath("$.soSaleType")
            .value(is(DEFAULT_SO_SALE_TYPE));
    }

    @Test
    void getNonExistingDeliveryItems() {
        // Get the deliveryItems
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingDeliveryItems() throws Exception {
        // Initialize the database
        deliveryItemsRepository.save(deliveryItems).block();

        int databaseSizeBeforeUpdate = deliveryItemsRepository.findAll().collectList().block().size();

        // Update the deliveryItems
        DeliveryItems updatedDeliveryItems = deliveryItemsRepository.findById(deliveryItems.getDeliveryItemId()).block();
        updatedDeliveryItems
            .deliveryTicketId(UPDATED_DELIVERY_TICKET_ID)
            .deliveryTicketNo(UPDATED_DELIVERY_TICKET_NO)
            .soId(UPDATED_SO_ID)
            .soNo(UPDATED_SO_NO)
            .itemId(UPDATED_ITEM_ID)
            .itemNo(UPDATED_ITEM_NO)
            .itemName(UPDATED_ITEM_NAME)
            .itemDescription(UPDATED_ITEM_DESCRIPTION)
            .hcpcsCode(UPDATED_HCPCS_CODE)
            .itemQuantity(UPDATED_ITEM_QUANTITY)
            .itemUnit(UPDATED_ITEM_UNIT)
            .whetherItemSerialized(UPDATED_WHETHER_ITEM_SERIALIZED)
            .itemSerial(UPDATED_ITEM_SERIAL)
            .itemBatchLotNo(UPDATED_ITEM_BATCH_LOT_NO)
            .itemMfgDate(UPDATED_ITEM_MFG_DATE)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .deliveryItemsUuid(UPDATED_DELIVERY_ITEMS_UUID)
            .chargedAmount(UPDATED_CHARGED_AMOUNT)
            .allowedAmount(UPDATED_ALLOWED_AMOUNT)
            .itemManufacturerName(UPDATED_ITEM_MANUFACTURER_NAME)
            .isDropship(UPDATED_IS_DROPSHIP)
            .poNumber(UPDATED_PO_NUMBER)
            .soSaleType(UPDATED_SO_SALE_TYPE);
        DeliveryItemsDTO deliveryItemsDTO = deliveryItemsMapper.toDto(updatedDeliveryItems);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, deliveryItemsDTO.getDeliveryItemId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(deliveryItemsDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the DeliveryItems in the database
        List<DeliveryItems> deliveryItemsList = deliveryItemsRepository.findAll().collectList().block();
        assertThat(deliveryItemsList).hasSize(databaseSizeBeforeUpdate);
        DeliveryItems testDeliveryItems = deliveryItemsList.get(deliveryItemsList.size() - 1);
        assertThat(testDeliveryItems.getDeliveryTicketId()).isEqualTo(UPDATED_DELIVERY_TICKET_ID);
        assertThat(testDeliveryItems.getDeliveryTicketNo()).isEqualTo(UPDATED_DELIVERY_TICKET_NO);
        assertThat(testDeliveryItems.getSoId()).isEqualTo(UPDATED_SO_ID);
        assertThat(testDeliveryItems.getSoNo()).isEqualTo(UPDATED_SO_NO);
        assertThat(testDeliveryItems.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testDeliveryItems.getItemNo()).isEqualTo(UPDATED_ITEM_NO);
        assertThat(testDeliveryItems.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testDeliveryItems.getItemDescription()).isEqualTo(UPDATED_ITEM_DESCRIPTION);
        assertThat(testDeliveryItems.getHcpcsCode()).isEqualTo(UPDATED_HCPCS_CODE);
        assertThat(testDeliveryItems.getItemQuantity()).isEqualTo(UPDATED_ITEM_QUANTITY);
        assertThat(testDeliveryItems.getItemUnit()).isEqualTo(UPDATED_ITEM_UNIT);
        assertThat(testDeliveryItems.getWhetherItemSerialized()).isEqualTo(UPDATED_WHETHER_ITEM_SERIALIZED);
        assertThat(testDeliveryItems.getItemSerial()).isEqualTo(UPDATED_ITEM_SERIAL);
        assertThat(testDeliveryItems.getItemBatchLotNo()).isEqualTo(UPDATED_ITEM_BATCH_LOT_NO);
        assertThat(testDeliveryItems.getItemMfgDate()).isEqualTo(UPDATED_ITEM_MFG_DATE);
        assertThat(testDeliveryItems.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDeliveryItems.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testDeliveryItems.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testDeliveryItems.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testDeliveryItems.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testDeliveryItems.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testDeliveryItems.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testDeliveryItems.getDeliveryItemsUuid()).isEqualTo(UPDATED_DELIVERY_ITEMS_UUID);
        assertThat(testDeliveryItems.getChargedAmount()).isEqualTo(UPDATED_CHARGED_AMOUNT);
        assertThat(testDeliveryItems.getAllowedAmount()).isEqualTo(UPDATED_ALLOWED_AMOUNT);
        assertThat(testDeliveryItems.getItemManufacturerName()).isEqualTo(UPDATED_ITEM_MANUFACTURER_NAME);
        assertThat(testDeliveryItems.getIsDropship()).isEqualTo(UPDATED_IS_DROPSHIP);
        assertThat(testDeliveryItems.getPoNumber()).isEqualTo(UPDATED_PO_NUMBER);
        assertThat(testDeliveryItems.getSoSaleType()).isEqualTo(UPDATED_SO_SALE_TYPE);
    }

    @Test
    void putNonExistingDeliveryItems() throws Exception {
        int databaseSizeBeforeUpdate = deliveryItemsRepository.findAll().collectList().block().size();
        deliveryItems.setDeliveryItemId(count.incrementAndGet());

        // Create the DeliveryItems
        DeliveryItemsDTO deliveryItemsDTO = deliveryItemsMapper.toDto(deliveryItems);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, deliveryItemsDTO.getDeliveryItemId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(deliveryItemsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DeliveryItems in the database
        List<DeliveryItems> deliveryItemsList = deliveryItemsRepository.findAll().collectList().block();
        assertThat(deliveryItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchDeliveryItems() throws Exception {
        int databaseSizeBeforeUpdate = deliveryItemsRepository.findAll().collectList().block().size();
        deliveryItems.setDeliveryItemId(count.incrementAndGet());

        // Create the DeliveryItems
        DeliveryItemsDTO deliveryItemsDTO = deliveryItemsMapper.toDto(deliveryItems);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(deliveryItemsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DeliveryItems in the database
        List<DeliveryItems> deliveryItemsList = deliveryItemsRepository.findAll().collectList().block();
        assertThat(deliveryItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamDeliveryItems() throws Exception {
        int databaseSizeBeforeUpdate = deliveryItemsRepository.findAll().collectList().block().size();
        deliveryItems.setDeliveryItemId(count.incrementAndGet());

        // Create the DeliveryItems
        DeliveryItemsDTO deliveryItemsDTO = deliveryItemsMapper.toDto(deliveryItems);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(deliveryItemsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the DeliveryItems in the database
        List<DeliveryItems> deliveryItemsList = deliveryItemsRepository.findAll().collectList().block();
        assertThat(deliveryItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateDeliveryItemsWithPatch() throws Exception {
        // Initialize the database
        deliveryItemsRepository.save(deliveryItems).block();

        int databaseSizeBeforeUpdate = deliveryItemsRepository.findAll().collectList().block().size();

        // Update the deliveryItems using partial update
        DeliveryItems partialUpdatedDeliveryItems = new DeliveryItems();
        partialUpdatedDeliveryItems.setDeliveryItemId(deliveryItems.getDeliveryItemId());

        partialUpdatedDeliveryItems
            .deliveryTicketId(UPDATED_DELIVERY_TICKET_ID)
            .soId(UPDATED_SO_ID)
            .itemId(UPDATED_ITEM_ID)
            .itemNo(UPDATED_ITEM_NO)
            .itemName(UPDATED_ITEM_NAME)
            .whetherItemSerialized(UPDATED_WHETHER_ITEM_SERIALIZED)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .itemManufacturerName(UPDATED_ITEM_MANUFACTURER_NAME)
            .isDropship(UPDATED_IS_DROPSHIP)
            .poNumber(UPDATED_PO_NUMBER);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedDeliveryItems.getDeliveryItemId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedDeliveryItems))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the DeliveryItems in the database
        List<DeliveryItems> deliveryItemsList = deliveryItemsRepository.findAll().collectList().block();
        assertThat(deliveryItemsList).hasSize(databaseSizeBeforeUpdate);
        DeliveryItems testDeliveryItems = deliveryItemsList.get(deliveryItemsList.size() - 1);
        assertThat(testDeliveryItems.getDeliveryTicketId()).isEqualTo(UPDATED_DELIVERY_TICKET_ID);
        assertThat(testDeliveryItems.getDeliveryTicketNo()).isEqualTo(DEFAULT_DELIVERY_TICKET_NO);
        assertThat(testDeliveryItems.getSoId()).isEqualTo(UPDATED_SO_ID);
        assertThat(testDeliveryItems.getSoNo()).isEqualTo(DEFAULT_SO_NO);
        assertThat(testDeliveryItems.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testDeliveryItems.getItemNo()).isEqualTo(UPDATED_ITEM_NO);
        assertThat(testDeliveryItems.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testDeliveryItems.getItemDescription()).isEqualTo(DEFAULT_ITEM_DESCRIPTION);
        assertThat(testDeliveryItems.getHcpcsCode()).isEqualTo(DEFAULT_HCPCS_CODE);
        assertThat(testDeliveryItems.getItemQuantity()).isEqualTo(DEFAULT_ITEM_QUANTITY);
        assertThat(testDeliveryItems.getItemUnit()).isEqualTo(DEFAULT_ITEM_UNIT);
        assertThat(testDeliveryItems.getWhetherItemSerialized()).isEqualTo(UPDATED_WHETHER_ITEM_SERIALIZED);
        assertThat(testDeliveryItems.getItemSerial()).isEqualTo(DEFAULT_ITEM_SERIAL);
        assertThat(testDeliveryItems.getItemBatchLotNo()).isEqualTo(DEFAULT_ITEM_BATCH_LOT_NO);
        assertThat(testDeliveryItems.getItemMfgDate()).isEqualTo(DEFAULT_ITEM_MFG_DATE);
        assertThat(testDeliveryItems.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDeliveryItems.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testDeliveryItems.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testDeliveryItems.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testDeliveryItems.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testDeliveryItems.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testDeliveryItems.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testDeliveryItems.getDeliveryItemsUuid()).isEqualTo(DEFAULT_DELIVERY_ITEMS_UUID);
        assertThat(testDeliveryItems.getChargedAmount()).isEqualTo(DEFAULT_CHARGED_AMOUNT);
        assertThat(testDeliveryItems.getAllowedAmount()).isEqualTo(DEFAULT_ALLOWED_AMOUNT);
        assertThat(testDeliveryItems.getItemManufacturerName()).isEqualTo(UPDATED_ITEM_MANUFACTURER_NAME);
        assertThat(testDeliveryItems.getIsDropship()).isEqualTo(UPDATED_IS_DROPSHIP);
        assertThat(testDeliveryItems.getPoNumber()).isEqualTo(UPDATED_PO_NUMBER);
        assertThat(testDeliveryItems.getSoSaleType()).isEqualTo(DEFAULT_SO_SALE_TYPE);
    }

    @Test
    void fullUpdateDeliveryItemsWithPatch() throws Exception {
        // Initialize the database
        deliveryItemsRepository.save(deliveryItems).block();

        int databaseSizeBeforeUpdate = deliveryItemsRepository.findAll().collectList().block().size();

        // Update the deliveryItems using partial update
        DeliveryItems partialUpdatedDeliveryItems = new DeliveryItems();
        partialUpdatedDeliveryItems.setDeliveryItemId(deliveryItems.getDeliveryItemId());

        partialUpdatedDeliveryItems
            .deliveryTicketId(UPDATED_DELIVERY_TICKET_ID)
            .deliveryTicketNo(UPDATED_DELIVERY_TICKET_NO)
            .soId(UPDATED_SO_ID)
            .soNo(UPDATED_SO_NO)
            .itemId(UPDATED_ITEM_ID)
            .itemNo(UPDATED_ITEM_NO)
            .itemName(UPDATED_ITEM_NAME)
            .itemDescription(UPDATED_ITEM_DESCRIPTION)
            .hcpcsCode(UPDATED_HCPCS_CODE)
            .itemQuantity(UPDATED_ITEM_QUANTITY)
            .itemUnit(UPDATED_ITEM_UNIT)
            .whetherItemSerialized(UPDATED_WHETHER_ITEM_SERIALIZED)
            .itemSerial(UPDATED_ITEM_SERIAL)
            .itemBatchLotNo(UPDATED_ITEM_BATCH_LOT_NO)
            .itemMfgDate(UPDATED_ITEM_MFG_DATE)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .deliveryItemsUuid(UPDATED_DELIVERY_ITEMS_UUID)
            .chargedAmount(UPDATED_CHARGED_AMOUNT)
            .allowedAmount(UPDATED_ALLOWED_AMOUNT)
            .itemManufacturerName(UPDATED_ITEM_MANUFACTURER_NAME)
            .isDropship(UPDATED_IS_DROPSHIP)
            .poNumber(UPDATED_PO_NUMBER)
            .soSaleType(UPDATED_SO_SALE_TYPE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedDeliveryItems.getDeliveryItemId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedDeliveryItems))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the DeliveryItems in the database
        List<DeliveryItems> deliveryItemsList = deliveryItemsRepository.findAll().collectList().block();
        assertThat(deliveryItemsList).hasSize(databaseSizeBeforeUpdate);
        DeliveryItems testDeliveryItems = deliveryItemsList.get(deliveryItemsList.size() - 1);
        assertThat(testDeliveryItems.getDeliveryTicketId()).isEqualTo(UPDATED_DELIVERY_TICKET_ID);
        assertThat(testDeliveryItems.getDeliveryTicketNo()).isEqualTo(UPDATED_DELIVERY_TICKET_NO);
        assertThat(testDeliveryItems.getSoId()).isEqualTo(UPDATED_SO_ID);
        assertThat(testDeliveryItems.getSoNo()).isEqualTo(UPDATED_SO_NO);
        assertThat(testDeliveryItems.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testDeliveryItems.getItemNo()).isEqualTo(UPDATED_ITEM_NO);
        assertThat(testDeliveryItems.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testDeliveryItems.getItemDescription()).isEqualTo(UPDATED_ITEM_DESCRIPTION);
        assertThat(testDeliveryItems.getHcpcsCode()).isEqualTo(UPDATED_HCPCS_CODE);
        assertThat(testDeliveryItems.getItemQuantity()).isEqualTo(UPDATED_ITEM_QUANTITY);
        assertThat(testDeliveryItems.getItemUnit()).isEqualTo(UPDATED_ITEM_UNIT);
        assertThat(testDeliveryItems.getWhetherItemSerialized()).isEqualTo(UPDATED_WHETHER_ITEM_SERIALIZED);
        assertThat(testDeliveryItems.getItemSerial()).isEqualTo(UPDATED_ITEM_SERIAL);
        assertThat(testDeliveryItems.getItemBatchLotNo()).isEqualTo(UPDATED_ITEM_BATCH_LOT_NO);
        assertThat(testDeliveryItems.getItemMfgDate()).isEqualTo(UPDATED_ITEM_MFG_DATE);
        assertThat(testDeliveryItems.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDeliveryItems.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testDeliveryItems.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testDeliveryItems.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testDeliveryItems.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testDeliveryItems.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testDeliveryItems.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testDeliveryItems.getDeliveryItemsUuid()).isEqualTo(UPDATED_DELIVERY_ITEMS_UUID);
        assertThat(testDeliveryItems.getChargedAmount()).isEqualTo(UPDATED_CHARGED_AMOUNT);
        assertThat(testDeliveryItems.getAllowedAmount()).isEqualTo(UPDATED_ALLOWED_AMOUNT);
        assertThat(testDeliveryItems.getItemManufacturerName()).isEqualTo(UPDATED_ITEM_MANUFACTURER_NAME);
        assertThat(testDeliveryItems.getIsDropship()).isEqualTo(UPDATED_IS_DROPSHIP);
        assertThat(testDeliveryItems.getPoNumber()).isEqualTo(UPDATED_PO_NUMBER);
        assertThat(testDeliveryItems.getSoSaleType()).isEqualTo(UPDATED_SO_SALE_TYPE);
    }

    @Test
    void patchNonExistingDeliveryItems() throws Exception {
        int databaseSizeBeforeUpdate = deliveryItemsRepository.findAll().collectList().block().size();
        deliveryItems.setDeliveryItemId(count.incrementAndGet());

        // Create the DeliveryItems
        DeliveryItemsDTO deliveryItemsDTO = deliveryItemsMapper.toDto(deliveryItems);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, deliveryItemsDTO.getDeliveryItemId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(deliveryItemsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DeliveryItems in the database
        List<DeliveryItems> deliveryItemsList = deliveryItemsRepository.findAll().collectList().block();
        assertThat(deliveryItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchDeliveryItems() throws Exception {
        int databaseSizeBeforeUpdate = deliveryItemsRepository.findAll().collectList().block().size();
        deliveryItems.setDeliveryItemId(count.incrementAndGet());

        // Create the DeliveryItems
        DeliveryItemsDTO deliveryItemsDTO = deliveryItemsMapper.toDto(deliveryItems);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(deliveryItemsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DeliveryItems in the database
        List<DeliveryItems> deliveryItemsList = deliveryItemsRepository.findAll().collectList().block();
        assertThat(deliveryItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamDeliveryItems() throws Exception {
        int databaseSizeBeforeUpdate = deliveryItemsRepository.findAll().collectList().block().size();
        deliveryItems.setDeliveryItemId(count.incrementAndGet());

        // Create the DeliveryItems
        DeliveryItemsDTO deliveryItemsDTO = deliveryItemsMapper.toDto(deliveryItems);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(deliveryItemsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the DeliveryItems in the database
        List<DeliveryItems> deliveryItemsList = deliveryItemsRepository.findAll().collectList().block();
        assertThat(deliveryItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteDeliveryItems() {
        // Initialize the database
        deliveryItemsRepository.save(deliveryItems).block();

        int databaseSizeBeforeDelete = deliveryItemsRepository.findAll().collectList().block().size();

        // Delete the deliveryItems
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, deliveryItems.getDeliveryItemId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<DeliveryItems> deliveryItemsList = deliveryItemsRepository.findAll().collectList().block();
        assertThat(deliveryItemsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
