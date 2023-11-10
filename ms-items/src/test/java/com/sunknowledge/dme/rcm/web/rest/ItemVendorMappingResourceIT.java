package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ItemVendorMapping;
import com.sunknowledge.dme.rcm.repository.ItemVendorMappingRepository;
import com.sunknowledge.dme.rcm.service.dto.ItemVendorMappingDTO;
import com.sunknowledge.dme.rcm.service.mapper.ItemVendorMappingMapper;
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
 * Integration tests for the {@link ItemVendorMappingResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ItemVendorMappingResourceIT {

    private static final Long DEFAULT_ITEM_ID = 1L;
    private static final Long UPDATED_ITEM_ID = 2L;

    private static final String DEFAULT_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_VENDOR_ID = 1L;
    private static final Long UPDATED_VENDOR_ID = 2L;

    private static final String DEFAULT_VENDOR_NAME = "AAAAAAAAAA";
    private static final String UPDATED_VENDOR_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final UUID DEFAULT_ITEM_VENDOR_MAPPING_UUID = UUID.randomUUID();
    private static final UUID UPDATED_ITEM_VENDOR_MAPPING_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/item-vendor-mappings";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{itemVendorId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ItemVendorMappingRepository itemVendorMappingRepository;

    @Autowired
    private ItemVendorMappingMapper itemVendorMappingMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restItemVendorMappingMockMvc;

    private ItemVendorMapping itemVendorMapping;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemVendorMapping createEntity(EntityManager em) {
        ItemVendorMapping itemVendorMapping = new ItemVendorMapping()
            .itemId(DEFAULT_ITEM_ID)
            .itemName(DEFAULT_ITEM_NAME)
            .vendorId(DEFAULT_VENDOR_ID)
            .vendorName(DEFAULT_VENDOR_NAME)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .itemVendorMappingUuid(DEFAULT_ITEM_VENDOR_MAPPING_UUID);
        return itemVendorMapping;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemVendorMapping createUpdatedEntity(EntityManager em) {
        ItemVendorMapping itemVendorMapping = new ItemVendorMapping()
            .itemId(UPDATED_ITEM_ID)
            .itemName(UPDATED_ITEM_NAME)
            .vendorId(UPDATED_VENDOR_ID)
            .vendorName(UPDATED_VENDOR_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .itemVendorMappingUuid(UPDATED_ITEM_VENDOR_MAPPING_UUID);
        return itemVendorMapping;
    }

    @BeforeEach
    public void initTest() {
        itemVendorMapping = createEntity(em);
    }

    @Test
    @Transactional
    void createItemVendorMapping() throws Exception {
        int databaseSizeBeforeCreate = itemVendorMappingRepository.findAll().size();
        // Create the ItemVendorMapping
        ItemVendorMappingDTO itemVendorMappingDTO = itemVendorMappingMapper.toDto(itemVendorMapping);
        restItemVendorMappingMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemVendorMappingDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ItemVendorMapping in the database
        List<ItemVendorMapping> itemVendorMappingList = itemVendorMappingRepository.findAll();
        assertThat(itemVendorMappingList).hasSize(databaseSizeBeforeCreate + 1);
        ItemVendorMapping testItemVendorMapping = itemVendorMappingList.get(itemVendorMappingList.size() - 1);
        assertThat(testItemVendorMapping.getItemId()).isEqualTo(DEFAULT_ITEM_ID);
        assertThat(testItemVendorMapping.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testItemVendorMapping.getVendorId()).isEqualTo(DEFAULT_VENDOR_ID);
        assertThat(testItemVendorMapping.getVendorName()).isEqualTo(DEFAULT_VENDOR_NAME);
        assertThat(testItemVendorMapping.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testItemVendorMapping.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testItemVendorMapping.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testItemVendorMapping.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testItemVendorMapping.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testItemVendorMapping.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testItemVendorMapping.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testItemVendorMapping.getItemVendorMappingUuid()).isEqualTo(DEFAULT_ITEM_VENDOR_MAPPING_UUID);
    }

    @Test
    @Transactional
    void createItemVendorMappingWithExistingId() throws Exception {
        // Create the ItemVendorMapping with an existing ID
        itemVendorMapping.setItemVendorId(1L);
        ItemVendorMappingDTO itemVendorMappingDTO = itemVendorMappingMapper.toDto(itemVendorMapping);

        int databaseSizeBeforeCreate = itemVendorMappingRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemVendorMappingMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemVendorMappingDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemVendorMapping in the database
        List<ItemVendorMapping> itemVendorMappingList = itemVendorMappingRepository.findAll();
        assertThat(itemVendorMappingList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkItemNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemVendorMappingRepository.findAll().size();
        // set the field null
        itemVendorMapping.setItemName(null);

        // Create the ItemVendorMapping, which fails.
        ItemVendorMappingDTO itemVendorMappingDTO = itemVendorMappingMapper.toDto(itemVendorMapping);

        restItemVendorMappingMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemVendorMappingDTO))
            )
            .andExpect(status().isBadRequest());

        List<ItemVendorMapping> itemVendorMappingList = itemVendorMappingRepository.findAll();
        assertThat(itemVendorMappingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllItemVendorMappings() throws Exception {
        // Initialize the database
        itemVendorMappingRepository.saveAndFlush(itemVendorMapping);

        // Get all the itemVendorMappingList
        restItemVendorMappingMockMvc
            .perform(get(ENTITY_API_URL + "?sort=itemVendorId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].itemVendorId").value(hasItem(itemVendorMapping.getItemVendorId().intValue())))
            .andExpect(jsonPath("$.[*].itemId").value(hasItem(DEFAULT_ITEM_ID.intValue())))
            .andExpect(jsonPath("$.[*].itemName").value(hasItem(DEFAULT_ITEM_NAME)))
            .andExpect(jsonPath("$.[*].vendorId").value(hasItem(DEFAULT_VENDOR_ID.intValue())))
            .andExpect(jsonPath("$.[*].vendorName").value(hasItem(DEFAULT_VENDOR_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].itemVendorMappingUuid").value(hasItem(DEFAULT_ITEM_VENDOR_MAPPING_UUID.toString())));
    }

    @Test
    @Transactional
    void getItemVendorMapping() throws Exception {
        // Initialize the database
        itemVendorMappingRepository.saveAndFlush(itemVendorMapping);

        // Get the itemVendorMapping
        restItemVendorMappingMockMvc
            .perform(get(ENTITY_API_URL_ID, itemVendorMapping.getItemVendorId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.itemVendorId").value(itemVendorMapping.getItemVendorId().intValue()))
            .andExpect(jsonPath("$.itemId").value(DEFAULT_ITEM_ID.intValue()))
            .andExpect(jsonPath("$.itemName").value(DEFAULT_ITEM_NAME))
            .andExpect(jsonPath("$.vendorId").value(DEFAULT_VENDOR_ID.intValue()))
            .andExpect(jsonPath("$.vendorName").value(DEFAULT_VENDOR_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.itemVendorMappingUuid").value(DEFAULT_ITEM_VENDOR_MAPPING_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingItemVendorMapping() throws Exception {
        // Get the itemVendorMapping
        restItemVendorMappingMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingItemVendorMapping() throws Exception {
        // Initialize the database
        itemVendorMappingRepository.saveAndFlush(itemVendorMapping);

        int databaseSizeBeforeUpdate = itemVendorMappingRepository.findAll().size();

        // Update the itemVendorMapping
        ItemVendorMapping updatedItemVendorMapping = itemVendorMappingRepository.findById(itemVendorMapping.getItemVendorId()).get();
        // Disconnect from session so that the updates on updatedItemVendorMapping are not directly saved in db
        em.detach(updatedItemVendorMapping);
        updatedItemVendorMapping
            .itemId(UPDATED_ITEM_ID)
            .itemName(UPDATED_ITEM_NAME)
            .vendorId(UPDATED_VENDOR_ID)
            .vendorName(UPDATED_VENDOR_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .itemVendorMappingUuid(UPDATED_ITEM_VENDOR_MAPPING_UUID);
        ItemVendorMappingDTO itemVendorMappingDTO = itemVendorMappingMapper.toDto(updatedItemVendorMapping);

        restItemVendorMappingMockMvc
            .perform(
                put(ENTITY_API_URL_ID, itemVendorMappingDTO.getItemVendorId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemVendorMappingDTO))
            )
            .andExpect(status().isOk());

        // Validate the ItemVendorMapping in the database
        List<ItemVendorMapping> itemVendorMappingList = itemVendorMappingRepository.findAll();
        assertThat(itemVendorMappingList).hasSize(databaseSizeBeforeUpdate);
        ItemVendorMapping testItemVendorMapping = itemVendorMappingList.get(itemVendorMappingList.size() - 1);
        assertThat(testItemVendorMapping.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testItemVendorMapping.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testItemVendorMapping.getVendorId()).isEqualTo(UPDATED_VENDOR_ID);
        assertThat(testItemVendorMapping.getVendorName()).isEqualTo(UPDATED_VENDOR_NAME);
        assertThat(testItemVendorMapping.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testItemVendorMapping.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testItemVendorMapping.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testItemVendorMapping.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testItemVendorMapping.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testItemVendorMapping.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testItemVendorMapping.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testItemVendorMapping.getItemVendorMappingUuid()).isEqualTo(UPDATED_ITEM_VENDOR_MAPPING_UUID);
    }

    @Test
    @Transactional
    void putNonExistingItemVendorMapping() throws Exception {
        int databaseSizeBeforeUpdate = itemVendorMappingRepository.findAll().size();
        itemVendorMapping.setItemVendorId(count.incrementAndGet());

        // Create the ItemVendorMapping
        ItemVendorMappingDTO itemVendorMappingDTO = itemVendorMappingMapper.toDto(itemVendorMapping);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemVendorMappingMockMvc
            .perform(
                put(ENTITY_API_URL_ID, itemVendorMappingDTO.getItemVendorId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemVendorMappingDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemVendorMapping in the database
        List<ItemVendorMapping> itemVendorMappingList = itemVendorMappingRepository.findAll();
        assertThat(itemVendorMappingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchItemVendorMapping() throws Exception {
        int databaseSizeBeforeUpdate = itemVendorMappingRepository.findAll().size();
        itemVendorMapping.setItemVendorId(count.incrementAndGet());

        // Create the ItemVendorMapping
        ItemVendorMappingDTO itemVendorMappingDTO = itemVendorMappingMapper.toDto(itemVendorMapping);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemVendorMappingMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemVendorMappingDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemVendorMapping in the database
        List<ItemVendorMapping> itemVendorMappingList = itemVendorMappingRepository.findAll();
        assertThat(itemVendorMappingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamItemVendorMapping() throws Exception {
        int databaseSizeBeforeUpdate = itemVendorMappingRepository.findAll().size();
        itemVendorMapping.setItemVendorId(count.incrementAndGet());

        // Create the ItemVendorMapping
        ItemVendorMappingDTO itemVendorMappingDTO = itemVendorMappingMapper.toDto(itemVendorMapping);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemVendorMappingMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemVendorMappingDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ItemVendorMapping in the database
        List<ItemVendorMapping> itemVendorMappingList = itemVendorMappingRepository.findAll();
        assertThat(itemVendorMappingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateItemVendorMappingWithPatch() throws Exception {
        // Initialize the database
        itemVendorMappingRepository.saveAndFlush(itemVendorMapping);

        int databaseSizeBeforeUpdate = itemVendorMappingRepository.findAll().size();

        // Update the itemVendorMapping using partial update
        ItemVendorMapping partialUpdatedItemVendorMapping = new ItemVendorMapping();
        partialUpdatedItemVendorMapping.setItemVendorId(itemVendorMapping.getItemVendorId());

        partialUpdatedItemVendorMapping
            .itemName(UPDATED_ITEM_NAME)
            .vendorId(UPDATED_VENDOR_ID)
            .vendorName(UPDATED_VENDOR_NAME)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME);

        restItemVendorMappingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedItemVendorMapping.getItemVendorId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedItemVendorMapping))
            )
            .andExpect(status().isOk());

        // Validate the ItemVendorMapping in the database
        List<ItemVendorMapping> itemVendorMappingList = itemVendorMappingRepository.findAll();
        assertThat(itemVendorMappingList).hasSize(databaseSizeBeforeUpdate);
        ItemVendorMapping testItemVendorMapping = itemVendorMappingList.get(itemVendorMappingList.size() - 1);
        assertThat(testItemVendorMapping.getItemId()).isEqualTo(DEFAULT_ITEM_ID);
        assertThat(testItemVendorMapping.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testItemVendorMapping.getVendorId()).isEqualTo(UPDATED_VENDOR_ID);
        assertThat(testItemVendorMapping.getVendorName()).isEqualTo(UPDATED_VENDOR_NAME);
        assertThat(testItemVendorMapping.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testItemVendorMapping.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testItemVendorMapping.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testItemVendorMapping.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testItemVendorMapping.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testItemVendorMapping.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testItemVendorMapping.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testItemVendorMapping.getItemVendorMappingUuid()).isEqualTo(DEFAULT_ITEM_VENDOR_MAPPING_UUID);
    }

    @Test
    @Transactional
    void fullUpdateItemVendorMappingWithPatch() throws Exception {
        // Initialize the database
        itemVendorMappingRepository.saveAndFlush(itemVendorMapping);

        int databaseSizeBeforeUpdate = itemVendorMappingRepository.findAll().size();

        // Update the itemVendorMapping using partial update
        ItemVendorMapping partialUpdatedItemVendorMapping = new ItemVendorMapping();
        partialUpdatedItemVendorMapping.setItemVendorId(itemVendorMapping.getItemVendorId());

        partialUpdatedItemVendorMapping
            .itemId(UPDATED_ITEM_ID)
            .itemName(UPDATED_ITEM_NAME)
            .vendorId(UPDATED_VENDOR_ID)
            .vendorName(UPDATED_VENDOR_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .itemVendorMappingUuid(UPDATED_ITEM_VENDOR_MAPPING_UUID);

        restItemVendorMappingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedItemVendorMapping.getItemVendorId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedItemVendorMapping))
            )
            .andExpect(status().isOk());

        // Validate the ItemVendorMapping in the database
        List<ItemVendorMapping> itemVendorMappingList = itemVendorMappingRepository.findAll();
        assertThat(itemVendorMappingList).hasSize(databaseSizeBeforeUpdate);
        ItemVendorMapping testItemVendorMapping = itemVendorMappingList.get(itemVendorMappingList.size() - 1);
        assertThat(testItemVendorMapping.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testItemVendorMapping.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testItemVendorMapping.getVendorId()).isEqualTo(UPDATED_VENDOR_ID);
        assertThat(testItemVendorMapping.getVendorName()).isEqualTo(UPDATED_VENDOR_NAME);
        assertThat(testItemVendorMapping.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testItemVendorMapping.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testItemVendorMapping.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testItemVendorMapping.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testItemVendorMapping.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testItemVendorMapping.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testItemVendorMapping.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testItemVendorMapping.getItemVendorMappingUuid()).isEqualTo(UPDATED_ITEM_VENDOR_MAPPING_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingItemVendorMapping() throws Exception {
        int databaseSizeBeforeUpdate = itemVendorMappingRepository.findAll().size();
        itemVendorMapping.setItemVendorId(count.incrementAndGet());

        // Create the ItemVendorMapping
        ItemVendorMappingDTO itemVendorMappingDTO = itemVendorMappingMapper.toDto(itemVendorMapping);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemVendorMappingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, itemVendorMappingDTO.getItemVendorId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemVendorMappingDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemVendorMapping in the database
        List<ItemVendorMapping> itemVendorMappingList = itemVendorMappingRepository.findAll();
        assertThat(itemVendorMappingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchItemVendorMapping() throws Exception {
        int databaseSizeBeforeUpdate = itemVendorMappingRepository.findAll().size();
        itemVendorMapping.setItemVendorId(count.incrementAndGet());

        // Create the ItemVendorMapping
        ItemVendorMappingDTO itemVendorMappingDTO = itemVendorMappingMapper.toDto(itemVendorMapping);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemVendorMappingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemVendorMappingDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemVendorMapping in the database
        List<ItemVendorMapping> itemVendorMappingList = itemVendorMappingRepository.findAll();
        assertThat(itemVendorMappingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamItemVendorMapping() throws Exception {
        int databaseSizeBeforeUpdate = itemVendorMappingRepository.findAll().size();
        itemVendorMapping.setItemVendorId(count.incrementAndGet());

        // Create the ItemVendorMapping
        ItemVendorMappingDTO itemVendorMappingDTO = itemVendorMappingMapper.toDto(itemVendorMapping);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemVendorMappingMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemVendorMappingDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ItemVendorMapping in the database
        List<ItemVendorMapping> itemVendorMappingList = itemVendorMappingRepository.findAll();
        assertThat(itemVendorMappingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteItemVendorMapping() throws Exception {
        // Initialize the database
        itemVendorMappingRepository.saveAndFlush(itemVendorMapping);

        int databaseSizeBeforeDelete = itemVendorMappingRepository.findAll().size();

        // Delete the itemVendorMapping
        restItemVendorMappingMockMvc
            .perform(delete(ENTITY_API_URL_ID, itemVendorMapping.getItemVendorId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ItemVendorMapping> itemVendorMappingList = itemVendorMappingRepository.findAll();
        assertThat(itemVendorMappingList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
