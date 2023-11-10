package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.DeliveryItems;
import com.sunknowledge.dme.rcm.repository.DeliveryItemsRepository;
import com.sunknowledge.dme.rcm.service.dto.DeliveryItemsDTO;
import com.sunknowledge.dme.rcm.service.mapper.DeliveryItemsMapper;
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
 * Integration tests for the {@link DeliveryItemsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
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
    private MockMvc restDeliveryItemsMockMvc;

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

    @BeforeEach
    public void initTest() {
        deliveryItems = createEntity(em);
    }

    @Test
    @Transactional
    void createDeliveryItems() throws Exception {
        int databaseSizeBeforeCreate = deliveryItemsRepository.findAll().size();
        // Create the DeliveryItems
        DeliveryItemsDTO deliveryItemsDTO = deliveryItemsMapper.toDto(deliveryItems);
        restDeliveryItemsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(deliveryItemsDTO))
            )
            .andExpect(status().isCreated());

        // Validate the DeliveryItems in the database
        List<DeliveryItems> deliveryItemsList = deliveryItemsRepository.findAll();
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
    @Transactional
    void createDeliveryItemsWithExistingId() throws Exception {
        // Create the DeliveryItems with an existing ID
        deliveryItems.setDeliveryItemId(1L);
        DeliveryItemsDTO deliveryItemsDTO = deliveryItemsMapper.toDto(deliveryItems);

        int databaseSizeBeforeCreate = deliveryItemsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDeliveryItemsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(deliveryItemsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeliveryItems in the database
        List<DeliveryItems> deliveryItemsList = deliveryItemsRepository.findAll();
        assertThat(deliveryItemsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDeliveryItems() throws Exception {
        // Initialize the database
        deliveryItemsRepository.saveAndFlush(deliveryItems);

        // Get all the deliveryItemsList
        restDeliveryItemsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=deliveryItemId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].deliveryItemId").value(hasItem(deliveryItems.getDeliveryItemId().intValue())))
            .andExpect(jsonPath("$.[*].deliveryTicketId").value(hasItem(DEFAULT_DELIVERY_TICKET_ID.intValue())))
            .andExpect(jsonPath("$.[*].deliveryTicketNo").value(hasItem(DEFAULT_DELIVERY_TICKET_NO)))
            .andExpect(jsonPath("$.[*].soId").value(hasItem(DEFAULT_SO_ID.intValue())))
            .andExpect(jsonPath("$.[*].soNo").value(hasItem(DEFAULT_SO_NO)))
            .andExpect(jsonPath("$.[*].itemId").value(hasItem(DEFAULT_ITEM_ID.intValue())))
            .andExpect(jsonPath("$.[*].itemNo").value(hasItem(DEFAULT_ITEM_NO)))
            .andExpect(jsonPath("$.[*].itemName").value(hasItem(DEFAULT_ITEM_NAME)))
            .andExpect(jsonPath("$.[*].itemDescription").value(hasItem(DEFAULT_ITEM_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].hcpcsCode").value(hasItem(DEFAULT_HCPCS_CODE)))
            .andExpect(jsonPath("$.[*].itemQuantity").value(hasItem(DEFAULT_ITEM_QUANTITY)))
            .andExpect(jsonPath("$.[*].itemUnit").value(hasItem(DEFAULT_ITEM_UNIT)))
            .andExpect(jsonPath("$.[*].whetherItemSerialized").value(hasItem(DEFAULT_WHETHER_ITEM_SERIALIZED)))
            .andExpect(jsonPath("$.[*].itemSerial").value(hasItem(DEFAULT_ITEM_SERIAL)))
            .andExpect(jsonPath("$.[*].itemBatchLotNo").value(hasItem(DEFAULT_ITEM_BATCH_LOT_NO)))
            .andExpect(jsonPath("$.[*].itemMfgDate").value(hasItem(DEFAULT_ITEM_MFG_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].deliveryItemsUuid").value(hasItem(DEFAULT_DELIVERY_ITEMS_UUID.toString())))
            .andExpect(jsonPath("$.[*].chargedAmount").value(hasItem(DEFAULT_CHARGED_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].allowedAmount").value(hasItem(DEFAULT_ALLOWED_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].itemManufacturerName").value(hasItem(DEFAULT_ITEM_MANUFACTURER_NAME)))
            .andExpect(jsonPath("$.[*].isDropship").value(hasItem(DEFAULT_IS_DROPSHIP)))
            .andExpect(jsonPath("$.[*].poNumber").value(hasItem(DEFAULT_PO_NUMBER)))
            .andExpect(jsonPath("$.[*].soSaleType").value(hasItem(DEFAULT_SO_SALE_TYPE)));
    }

    @Test
    @Transactional
    void getDeliveryItems() throws Exception {
        // Initialize the database
        deliveryItemsRepository.saveAndFlush(deliveryItems);

        // Get the deliveryItems
        restDeliveryItemsMockMvc
            .perform(get(ENTITY_API_URL_ID, deliveryItems.getDeliveryItemId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.deliveryItemId").value(deliveryItems.getDeliveryItemId().intValue()))
            .andExpect(jsonPath("$.deliveryTicketId").value(DEFAULT_DELIVERY_TICKET_ID.intValue()))
            .andExpect(jsonPath("$.deliveryTicketNo").value(DEFAULT_DELIVERY_TICKET_NO))
            .andExpect(jsonPath("$.soId").value(DEFAULT_SO_ID.intValue()))
            .andExpect(jsonPath("$.soNo").value(DEFAULT_SO_NO))
            .andExpect(jsonPath("$.itemId").value(DEFAULT_ITEM_ID.intValue()))
            .andExpect(jsonPath("$.itemNo").value(DEFAULT_ITEM_NO))
            .andExpect(jsonPath("$.itemName").value(DEFAULT_ITEM_NAME))
            .andExpect(jsonPath("$.itemDescription").value(DEFAULT_ITEM_DESCRIPTION))
            .andExpect(jsonPath("$.hcpcsCode").value(DEFAULT_HCPCS_CODE))
            .andExpect(jsonPath("$.itemQuantity").value(DEFAULT_ITEM_QUANTITY))
            .andExpect(jsonPath("$.itemUnit").value(DEFAULT_ITEM_UNIT))
            .andExpect(jsonPath("$.whetherItemSerialized").value(DEFAULT_WHETHER_ITEM_SERIALIZED))
            .andExpect(jsonPath("$.itemSerial").value(DEFAULT_ITEM_SERIAL))
            .andExpect(jsonPath("$.itemBatchLotNo").value(DEFAULT_ITEM_BATCH_LOT_NO))
            .andExpect(jsonPath("$.itemMfgDate").value(DEFAULT_ITEM_MFG_DATE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.deliveryItemsUuid").value(DEFAULT_DELIVERY_ITEMS_UUID.toString()))
            .andExpect(jsonPath("$.chargedAmount").value(DEFAULT_CHARGED_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.allowedAmount").value(DEFAULT_ALLOWED_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.itemManufacturerName").value(DEFAULT_ITEM_MANUFACTURER_NAME))
            .andExpect(jsonPath("$.isDropship").value(DEFAULT_IS_DROPSHIP))
            .andExpect(jsonPath("$.poNumber").value(DEFAULT_PO_NUMBER))
            .andExpect(jsonPath("$.soSaleType").value(DEFAULT_SO_SALE_TYPE));
    }

    @Test
    @Transactional
    void getNonExistingDeliveryItems() throws Exception {
        // Get the deliveryItems
        restDeliveryItemsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewDeliveryItems() throws Exception {
        // Initialize the database
        deliveryItemsRepository.saveAndFlush(deliveryItems);

        int databaseSizeBeforeUpdate = deliveryItemsRepository.findAll().size();

        // Update the deliveryItems
        DeliveryItems updatedDeliveryItems = deliveryItemsRepository.findById(deliveryItems.getDeliveryItemId()).get();
        // Disconnect from session so that the updates on updatedDeliveryItems are not directly saved in db
        em.detach(updatedDeliveryItems);
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

        restDeliveryItemsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, deliveryItemsDTO.getDeliveryItemId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(deliveryItemsDTO))
            )
            .andExpect(status().isOk());

        // Validate the DeliveryItems in the database
        List<DeliveryItems> deliveryItemsList = deliveryItemsRepository.findAll();
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
    @Transactional
    void putNonExistingDeliveryItems() throws Exception {
        int databaseSizeBeforeUpdate = deliveryItemsRepository.findAll().size();
        deliveryItems.setDeliveryItemId(count.incrementAndGet());

        // Create the DeliveryItems
        DeliveryItemsDTO deliveryItemsDTO = deliveryItemsMapper.toDto(deliveryItems);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDeliveryItemsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, deliveryItemsDTO.getDeliveryItemId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(deliveryItemsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeliveryItems in the database
        List<DeliveryItems> deliveryItemsList = deliveryItemsRepository.findAll();
        assertThat(deliveryItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDeliveryItems() throws Exception {
        int databaseSizeBeforeUpdate = deliveryItemsRepository.findAll().size();
        deliveryItems.setDeliveryItemId(count.incrementAndGet());

        // Create the DeliveryItems
        DeliveryItemsDTO deliveryItemsDTO = deliveryItemsMapper.toDto(deliveryItems);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeliveryItemsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(deliveryItemsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeliveryItems in the database
        List<DeliveryItems> deliveryItemsList = deliveryItemsRepository.findAll();
        assertThat(deliveryItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDeliveryItems() throws Exception {
        int databaseSizeBeforeUpdate = deliveryItemsRepository.findAll().size();
        deliveryItems.setDeliveryItemId(count.incrementAndGet());

        // Create the DeliveryItems
        DeliveryItemsDTO deliveryItemsDTO = deliveryItemsMapper.toDto(deliveryItems);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeliveryItemsMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(deliveryItemsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DeliveryItems in the database
        List<DeliveryItems> deliveryItemsList = deliveryItemsRepository.findAll();
        assertThat(deliveryItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDeliveryItemsWithPatch() throws Exception {
        // Initialize the database
        deliveryItemsRepository.saveAndFlush(deliveryItems);

        int databaseSizeBeforeUpdate = deliveryItemsRepository.findAll().size();

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

        restDeliveryItemsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDeliveryItems.getDeliveryItemId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDeliveryItems))
            )
            .andExpect(status().isOk());

        // Validate the DeliveryItems in the database
        List<DeliveryItems> deliveryItemsList = deliveryItemsRepository.findAll();
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
    @Transactional
    void fullUpdateDeliveryItemsWithPatch() throws Exception {
        // Initialize the database
        deliveryItemsRepository.saveAndFlush(deliveryItems);

        int databaseSizeBeforeUpdate = deliveryItemsRepository.findAll().size();

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

        restDeliveryItemsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDeliveryItems.getDeliveryItemId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDeliveryItems))
            )
            .andExpect(status().isOk());

        // Validate the DeliveryItems in the database
        List<DeliveryItems> deliveryItemsList = deliveryItemsRepository.findAll();
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
    @Transactional
    void patchNonExistingDeliveryItems() throws Exception {
        int databaseSizeBeforeUpdate = deliveryItemsRepository.findAll().size();
        deliveryItems.setDeliveryItemId(count.incrementAndGet());

        // Create the DeliveryItems
        DeliveryItemsDTO deliveryItemsDTO = deliveryItemsMapper.toDto(deliveryItems);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDeliveryItemsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, deliveryItemsDTO.getDeliveryItemId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(deliveryItemsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeliveryItems in the database
        List<DeliveryItems> deliveryItemsList = deliveryItemsRepository.findAll();
        assertThat(deliveryItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDeliveryItems() throws Exception {
        int databaseSizeBeforeUpdate = deliveryItemsRepository.findAll().size();
        deliveryItems.setDeliveryItemId(count.incrementAndGet());

        // Create the DeliveryItems
        DeliveryItemsDTO deliveryItemsDTO = deliveryItemsMapper.toDto(deliveryItems);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeliveryItemsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(deliveryItemsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeliveryItems in the database
        List<DeliveryItems> deliveryItemsList = deliveryItemsRepository.findAll();
        assertThat(deliveryItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDeliveryItems() throws Exception {
        int databaseSizeBeforeUpdate = deliveryItemsRepository.findAll().size();
        deliveryItems.setDeliveryItemId(count.incrementAndGet());

        // Create the DeliveryItems
        DeliveryItemsDTO deliveryItemsDTO = deliveryItemsMapper.toDto(deliveryItems);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeliveryItemsMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(deliveryItemsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DeliveryItems in the database
        List<DeliveryItems> deliveryItemsList = deliveryItemsRepository.findAll();
        assertThat(deliveryItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDeliveryItems() throws Exception {
        // Initialize the database
        deliveryItemsRepository.saveAndFlush(deliveryItems);

        int databaseSizeBeforeDelete = deliveryItemsRepository.findAll().size();

        // Delete the deliveryItems
        restDeliveryItemsMockMvc
            .perform(delete(ENTITY_API_URL_ID, deliveryItems.getDeliveryItemId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DeliveryItems> deliveryItemsList = deliveryItemsRepository.findAll();
        assertThat(deliveryItemsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
