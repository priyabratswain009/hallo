package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.SoItemTransactionDetails;
import com.sunknowledge.dme.rcm.repository.SoItemTransactionDetailsRepository;
import com.sunknowledge.dme.rcm.service.dto.SoItemTransactionDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.SoItemTransactionDetailsMapper;
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
 * Integration tests for the {@link SoItemTransactionDetailsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SoItemTransactionDetailsResourceIT {

    private static final String DEFAULT_SALES_ORDER_NO = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_NO = "BBBBBBBBBB";

    private static final String DEFAULT_SALE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SALE_TYPE = "BBBBBBBBBB";

    private static final Long DEFAULT_ITEM_ID = 1L;
    private static final Long UPDATED_ITEM_ID = 2L;

    private static final String DEFAULT_ITEM_NO = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NO = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_UOM = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_UOM = "BBBBBBBBBB";

    private static final Long DEFAULT_ITEM_QTY = 1L;
    private static final Long UPDATED_ITEM_QTY = 2L;

    private static final String DEFAULT_WHEATHER_SERIALIZED = "AAAAAAAAAA";
    private static final String UPDATED_WHEATHER_SERIALIZED = "BBBBBBBBBB";

    private static final String DEFAULT_SERIAL_NO = "AAAAAAAAAA";
    private static final String UPDATED_SERIAL_NO = "BBBBBBBBBB";

    private static final String DEFAULT_WHEATHER_ASSET_TAGGED = "AAAAAAAAAA";
    private static final String UPDATED_WHEATHER_ASSET_TAGGED = "BBBBBBBBBB";

    private static final String DEFAULT_ASSET_NO = "AAAAAAAAAA";
    private static final String UPDATED_ASSET_NO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_ORIGINAL_DOS = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ORIGINAL_DOS = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_BRANCH_ID = 1L;
    private static final Long UPDATED_BRANCH_ID = 2L;

    private static final Long DEFAULT_LOCATION_ID = 1L;
    private static final Long UPDATED_LOCATION_ID = 2L;

    private static final String DEFAULT_LOCATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_TRANSACTION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TRANSACTION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_TRANSACTION_NO = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_NO = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_SO_ITEM_TRANSACTION_DETAILS_UUID = UUID.randomUUID();
    private static final UUID UPDATED_SO_ITEM_TRANSACTION_DETAILS_UUID = UUID.randomUUID();

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_IS_DROPSHIP = "AAAAAAAAAA";
    private static final String UPDATED_IS_DROPSHIP = "BBBBBBBBBB";

    private static final String DEFAULT_PO_NO = "AAAAAAAAAA";
    private static final String UPDATED_PO_NO = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_TRANSACTION_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_TRANSACTION_STATUS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/so-item-transaction-details";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{soItemTransactionDetailsId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SoItemTransactionDetailsRepository soItemTransactionDetailsRepository;

    @Autowired
    private SoItemTransactionDetailsMapper soItemTransactionDetailsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSoItemTransactionDetailsMockMvc;

    private SoItemTransactionDetails soItemTransactionDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SoItemTransactionDetails createEntity(EntityManager em) {
        SoItemTransactionDetails soItemTransactionDetails = new SoItemTransactionDetails()
            .salesOrderNo(DEFAULT_SALES_ORDER_NO)
            .saleType(DEFAULT_SALE_TYPE)
            .itemId(DEFAULT_ITEM_ID)
            .itemNo(DEFAULT_ITEM_NO)
            .itemName(DEFAULT_ITEM_NAME)
            .itemUom(DEFAULT_ITEM_UOM)
            .itemQty(DEFAULT_ITEM_QTY)
            .wheatherSerialized(DEFAULT_WHEATHER_SERIALIZED)
            .serialNo(DEFAULT_SERIAL_NO)
            .wheatherAssetTagged(DEFAULT_WHEATHER_ASSET_TAGGED)
            .assetNo(DEFAULT_ASSET_NO)
            .originalDos(DEFAULT_ORIGINAL_DOS)
            .branchId(DEFAULT_BRANCH_ID)
            .locationId(DEFAULT_LOCATION_ID)
            .locationName(DEFAULT_LOCATION_NAME)
            .status(DEFAULT_STATUS)
            .transactionDate(DEFAULT_TRANSACTION_DATE)
            .transactionNo(DEFAULT_TRANSACTION_NO)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .soItemTransactionDetailsUuid(DEFAULT_SO_ITEM_TRANSACTION_DETAILS_UUID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .isDropship(DEFAULT_IS_DROPSHIP)
            .poNo(DEFAULT_PO_NO)
            .itemTransactionStatus(DEFAULT_ITEM_TRANSACTION_STATUS);
        return soItemTransactionDetails;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SoItemTransactionDetails createUpdatedEntity(EntityManager em) {
        SoItemTransactionDetails soItemTransactionDetails = new SoItemTransactionDetails()
            .salesOrderNo(UPDATED_SALES_ORDER_NO)
            .saleType(UPDATED_SALE_TYPE)
            .itemId(UPDATED_ITEM_ID)
            .itemNo(UPDATED_ITEM_NO)
            .itemName(UPDATED_ITEM_NAME)
            .itemUom(UPDATED_ITEM_UOM)
            .itemQty(UPDATED_ITEM_QTY)
            .wheatherSerialized(UPDATED_WHEATHER_SERIALIZED)
            .serialNo(UPDATED_SERIAL_NO)
            .wheatherAssetTagged(UPDATED_WHEATHER_ASSET_TAGGED)
            .assetNo(UPDATED_ASSET_NO)
            .originalDos(UPDATED_ORIGINAL_DOS)
            .branchId(UPDATED_BRANCH_ID)
            .locationId(UPDATED_LOCATION_ID)
            .locationName(UPDATED_LOCATION_NAME)
            .status(UPDATED_STATUS)
            .transactionDate(UPDATED_TRANSACTION_DATE)
            .transactionNo(UPDATED_TRANSACTION_NO)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .soItemTransactionDetailsUuid(UPDATED_SO_ITEM_TRANSACTION_DETAILS_UUID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .isDropship(UPDATED_IS_DROPSHIP)
            .poNo(UPDATED_PO_NO)
            .itemTransactionStatus(UPDATED_ITEM_TRANSACTION_STATUS);
        return soItemTransactionDetails;
    }

    @BeforeEach
    public void initTest() {
        soItemTransactionDetails = createEntity(em);
    }

    @Test
    @Transactional
    void createSoItemTransactionDetails() throws Exception {
        int databaseSizeBeforeCreate = soItemTransactionDetailsRepository.findAll().size();
        // Create the SoItemTransactionDetails
        SoItemTransactionDetailsDTO soItemTransactionDetailsDTO = soItemTransactionDetailsMapper.toDto(soItemTransactionDetails);
        restSoItemTransactionDetailsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(soItemTransactionDetailsDTO))
            )
            .andExpect(status().isCreated());

        // Validate the SoItemTransactionDetails in the database
        List<SoItemTransactionDetails> soItemTransactionDetailsList = soItemTransactionDetailsRepository.findAll();
        assertThat(soItemTransactionDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        SoItemTransactionDetails testSoItemTransactionDetails = soItemTransactionDetailsList.get(soItemTransactionDetailsList.size() - 1);
        assertThat(testSoItemTransactionDetails.getSalesOrderNo()).isEqualTo(DEFAULT_SALES_ORDER_NO);
        assertThat(testSoItemTransactionDetails.getSaleType()).isEqualTo(DEFAULT_SALE_TYPE);
        assertThat(testSoItemTransactionDetails.getItemId()).isEqualTo(DEFAULT_ITEM_ID);
        assertThat(testSoItemTransactionDetails.getItemNo()).isEqualTo(DEFAULT_ITEM_NO);
        assertThat(testSoItemTransactionDetails.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testSoItemTransactionDetails.getItemUom()).isEqualTo(DEFAULT_ITEM_UOM);
        assertThat(testSoItemTransactionDetails.getItemQty()).isEqualTo(DEFAULT_ITEM_QTY);
        assertThat(testSoItemTransactionDetails.getWheatherSerialized()).isEqualTo(DEFAULT_WHEATHER_SERIALIZED);
        assertThat(testSoItemTransactionDetails.getSerialNo()).isEqualTo(DEFAULT_SERIAL_NO);
        assertThat(testSoItemTransactionDetails.getWheatherAssetTagged()).isEqualTo(DEFAULT_WHEATHER_ASSET_TAGGED);
        assertThat(testSoItemTransactionDetails.getAssetNo()).isEqualTo(DEFAULT_ASSET_NO);
        assertThat(testSoItemTransactionDetails.getOriginalDos()).isEqualTo(DEFAULT_ORIGINAL_DOS);
        assertThat(testSoItemTransactionDetails.getBranchId()).isEqualTo(DEFAULT_BRANCH_ID);
        assertThat(testSoItemTransactionDetails.getLocationId()).isEqualTo(DEFAULT_LOCATION_ID);
        assertThat(testSoItemTransactionDetails.getLocationName()).isEqualTo(DEFAULT_LOCATION_NAME);
        assertThat(testSoItemTransactionDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSoItemTransactionDetails.getTransactionDate()).isEqualTo(DEFAULT_TRANSACTION_DATE);
        assertThat(testSoItemTransactionDetails.getTransactionNo()).isEqualTo(DEFAULT_TRANSACTION_NO);
        assertThat(testSoItemTransactionDetails.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testSoItemTransactionDetails.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testSoItemTransactionDetails.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testSoItemTransactionDetails.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testSoItemTransactionDetails.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testSoItemTransactionDetails.getSoItemTransactionDetailsUuid()).isEqualTo(DEFAULT_SO_ITEM_TRANSACTION_DETAILS_UUID);
        assertThat(testSoItemTransactionDetails.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testSoItemTransactionDetails.getIsDropship()).isEqualTo(DEFAULT_IS_DROPSHIP);
        assertThat(testSoItemTransactionDetails.getPoNo()).isEqualTo(DEFAULT_PO_NO);
        assertThat(testSoItemTransactionDetails.getItemTransactionStatus()).isEqualTo(DEFAULT_ITEM_TRANSACTION_STATUS);
    }

    @Test
    @Transactional
    void createSoItemTransactionDetailsWithExistingId() throws Exception {
        // Create the SoItemTransactionDetails with an existing ID
        soItemTransactionDetails.setSoItemTransactionDetailsId(1L);
        SoItemTransactionDetailsDTO soItemTransactionDetailsDTO = soItemTransactionDetailsMapper.toDto(soItemTransactionDetails);

        int databaseSizeBeforeCreate = soItemTransactionDetailsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSoItemTransactionDetailsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(soItemTransactionDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SoItemTransactionDetails in the database
        List<SoItemTransactionDetails> soItemTransactionDetailsList = soItemTransactionDetailsRepository.findAll();
        assertThat(soItemTransactionDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSoItemTransactionDetails() throws Exception {
        // Initialize the database
        soItemTransactionDetailsRepository.saveAndFlush(soItemTransactionDetails);

        // Get all the soItemTransactionDetailsList
        restSoItemTransactionDetailsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=soItemTransactionDetailsId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].soItemTransactionDetailsId")
                    .value(hasItem(soItemTransactionDetails.getSoItemTransactionDetailsId().intValue()))
            )
            .andExpect(jsonPath("$.[*].salesOrderNo").value(hasItem(DEFAULT_SALES_ORDER_NO)))
            .andExpect(jsonPath("$.[*].saleType").value(hasItem(DEFAULT_SALE_TYPE)))
            .andExpect(jsonPath("$.[*].itemId").value(hasItem(DEFAULT_ITEM_ID.intValue())))
            .andExpect(jsonPath("$.[*].itemNo").value(hasItem(DEFAULT_ITEM_NO)))
            .andExpect(jsonPath("$.[*].itemName").value(hasItem(DEFAULT_ITEM_NAME)))
            .andExpect(jsonPath("$.[*].itemUom").value(hasItem(DEFAULT_ITEM_UOM)))
            .andExpect(jsonPath("$.[*].itemQty").value(hasItem(DEFAULT_ITEM_QTY.intValue())))
            .andExpect(jsonPath("$.[*].wheatherSerialized").value(hasItem(DEFAULT_WHEATHER_SERIALIZED)))
            .andExpect(jsonPath("$.[*].serialNo").value(hasItem(DEFAULT_SERIAL_NO)))
            .andExpect(jsonPath("$.[*].wheatherAssetTagged").value(hasItem(DEFAULT_WHEATHER_ASSET_TAGGED)))
            .andExpect(jsonPath("$.[*].assetNo").value(hasItem(DEFAULT_ASSET_NO)))
            .andExpect(jsonPath("$.[*].originalDos").value(hasItem(DEFAULT_ORIGINAL_DOS.toString())))
            .andExpect(jsonPath("$.[*].branchId").value(hasItem(DEFAULT_BRANCH_ID.intValue())))
            .andExpect(jsonPath("$.[*].locationId").value(hasItem(DEFAULT_LOCATION_ID.intValue())))
            .andExpect(jsonPath("$.[*].locationName").value(hasItem(DEFAULT_LOCATION_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].transactionDate").value(hasItem(DEFAULT_TRANSACTION_DATE.toString())))
            .andExpect(jsonPath("$.[*].transactionNo").value(hasItem(DEFAULT_TRANSACTION_NO)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].soItemTransactionDetailsUuid").value(hasItem(DEFAULT_SO_ITEM_TRANSACTION_DETAILS_UUID.toString())))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].isDropship").value(hasItem(DEFAULT_IS_DROPSHIP)))
            .andExpect(jsonPath("$.[*].poNo").value(hasItem(DEFAULT_PO_NO)))
            .andExpect(jsonPath("$.[*].itemTransactionStatus").value(hasItem(DEFAULT_ITEM_TRANSACTION_STATUS)));
    }

    @Test
    @Transactional
    void getSoItemTransactionDetails() throws Exception {
        // Initialize the database
        soItemTransactionDetailsRepository.saveAndFlush(soItemTransactionDetails);

        // Get the soItemTransactionDetails
        restSoItemTransactionDetailsMockMvc
            .perform(get(ENTITY_API_URL_ID, soItemTransactionDetails.getSoItemTransactionDetailsId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.soItemTransactionDetailsId").value(soItemTransactionDetails.getSoItemTransactionDetailsId().intValue()))
            .andExpect(jsonPath("$.salesOrderNo").value(DEFAULT_SALES_ORDER_NO))
            .andExpect(jsonPath("$.saleType").value(DEFAULT_SALE_TYPE))
            .andExpect(jsonPath("$.itemId").value(DEFAULT_ITEM_ID.intValue()))
            .andExpect(jsonPath("$.itemNo").value(DEFAULT_ITEM_NO))
            .andExpect(jsonPath("$.itemName").value(DEFAULT_ITEM_NAME))
            .andExpect(jsonPath("$.itemUom").value(DEFAULT_ITEM_UOM))
            .andExpect(jsonPath("$.itemQty").value(DEFAULT_ITEM_QTY.intValue()))
            .andExpect(jsonPath("$.wheatherSerialized").value(DEFAULT_WHEATHER_SERIALIZED))
            .andExpect(jsonPath("$.serialNo").value(DEFAULT_SERIAL_NO))
            .andExpect(jsonPath("$.wheatherAssetTagged").value(DEFAULT_WHEATHER_ASSET_TAGGED))
            .andExpect(jsonPath("$.assetNo").value(DEFAULT_ASSET_NO))
            .andExpect(jsonPath("$.originalDos").value(DEFAULT_ORIGINAL_DOS.toString()))
            .andExpect(jsonPath("$.branchId").value(DEFAULT_BRANCH_ID.intValue()))
            .andExpect(jsonPath("$.locationId").value(DEFAULT_LOCATION_ID.intValue()))
            .andExpect(jsonPath("$.locationName").value(DEFAULT_LOCATION_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.transactionDate").value(DEFAULT_TRANSACTION_DATE.toString()))
            .andExpect(jsonPath("$.transactionNo").value(DEFAULT_TRANSACTION_NO))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.soItemTransactionDetailsUuid").value(DEFAULT_SO_ITEM_TRANSACTION_DETAILS_UUID.toString()))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.isDropship").value(DEFAULT_IS_DROPSHIP))
            .andExpect(jsonPath("$.poNo").value(DEFAULT_PO_NO))
            .andExpect(jsonPath("$.itemTransactionStatus").value(DEFAULT_ITEM_TRANSACTION_STATUS));
    }

    @Test
    @Transactional
    void getNonExistingSoItemTransactionDetails() throws Exception {
        // Get the soItemTransactionDetails
        restSoItemTransactionDetailsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSoItemTransactionDetails() throws Exception {
        // Initialize the database
        soItemTransactionDetailsRepository.saveAndFlush(soItemTransactionDetails);

        int databaseSizeBeforeUpdate = soItemTransactionDetailsRepository.findAll().size();

        // Update the soItemTransactionDetails
        SoItemTransactionDetails updatedSoItemTransactionDetails = soItemTransactionDetailsRepository
            .findById(soItemTransactionDetails.getSoItemTransactionDetailsId())
            .get();
        // Disconnect from session so that the updates on updatedSoItemTransactionDetails are not directly saved in db
        em.detach(updatedSoItemTransactionDetails);
        updatedSoItemTransactionDetails
            .salesOrderNo(UPDATED_SALES_ORDER_NO)
            .saleType(UPDATED_SALE_TYPE)
            .itemId(UPDATED_ITEM_ID)
            .itemNo(UPDATED_ITEM_NO)
            .itemName(UPDATED_ITEM_NAME)
            .itemUom(UPDATED_ITEM_UOM)
            .itemQty(UPDATED_ITEM_QTY)
            .wheatherSerialized(UPDATED_WHEATHER_SERIALIZED)
            .serialNo(UPDATED_SERIAL_NO)
            .wheatherAssetTagged(UPDATED_WHEATHER_ASSET_TAGGED)
            .assetNo(UPDATED_ASSET_NO)
            .originalDos(UPDATED_ORIGINAL_DOS)
            .branchId(UPDATED_BRANCH_ID)
            .locationId(UPDATED_LOCATION_ID)
            .locationName(UPDATED_LOCATION_NAME)
            .status(UPDATED_STATUS)
            .transactionDate(UPDATED_TRANSACTION_DATE)
            .transactionNo(UPDATED_TRANSACTION_NO)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .soItemTransactionDetailsUuid(UPDATED_SO_ITEM_TRANSACTION_DETAILS_UUID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .isDropship(UPDATED_IS_DROPSHIP)
            .poNo(UPDATED_PO_NO)
            .itemTransactionStatus(UPDATED_ITEM_TRANSACTION_STATUS);
        SoItemTransactionDetailsDTO soItemTransactionDetailsDTO = soItemTransactionDetailsMapper.toDto(updatedSoItemTransactionDetails);

        restSoItemTransactionDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, soItemTransactionDetailsDTO.getSoItemTransactionDetailsId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(soItemTransactionDetailsDTO))
            )
            .andExpect(status().isOk());

        // Validate the SoItemTransactionDetails in the database
        List<SoItemTransactionDetails> soItemTransactionDetailsList = soItemTransactionDetailsRepository.findAll();
        assertThat(soItemTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
        SoItemTransactionDetails testSoItemTransactionDetails = soItemTransactionDetailsList.get(soItemTransactionDetailsList.size() - 1);
        assertThat(testSoItemTransactionDetails.getSalesOrderNo()).isEqualTo(UPDATED_SALES_ORDER_NO);
        assertThat(testSoItemTransactionDetails.getSaleType()).isEqualTo(UPDATED_SALE_TYPE);
        assertThat(testSoItemTransactionDetails.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testSoItemTransactionDetails.getItemNo()).isEqualTo(UPDATED_ITEM_NO);
        assertThat(testSoItemTransactionDetails.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testSoItemTransactionDetails.getItemUom()).isEqualTo(UPDATED_ITEM_UOM);
        assertThat(testSoItemTransactionDetails.getItemQty()).isEqualTo(UPDATED_ITEM_QTY);
        assertThat(testSoItemTransactionDetails.getWheatherSerialized()).isEqualTo(UPDATED_WHEATHER_SERIALIZED);
        assertThat(testSoItemTransactionDetails.getSerialNo()).isEqualTo(UPDATED_SERIAL_NO);
        assertThat(testSoItemTransactionDetails.getWheatherAssetTagged()).isEqualTo(UPDATED_WHEATHER_ASSET_TAGGED);
        assertThat(testSoItemTransactionDetails.getAssetNo()).isEqualTo(UPDATED_ASSET_NO);
        assertThat(testSoItemTransactionDetails.getOriginalDos()).isEqualTo(UPDATED_ORIGINAL_DOS);
        assertThat(testSoItemTransactionDetails.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
        assertThat(testSoItemTransactionDetails.getLocationId()).isEqualTo(UPDATED_LOCATION_ID);
        assertThat(testSoItemTransactionDetails.getLocationName()).isEqualTo(UPDATED_LOCATION_NAME);
        assertThat(testSoItemTransactionDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSoItemTransactionDetails.getTransactionDate()).isEqualTo(UPDATED_TRANSACTION_DATE);
        assertThat(testSoItemTransactionDetails.getTransactionNo()).isEqualTo(UPDATED_TRANSACTION_NO);
        assertThat(testSoItemTransactionDetails.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testSoItemTransactionDetails.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testSoItemTransactionDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSoItemTransactionDetails.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testSoItemTransactionDetails.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testSoItemTransactionDetails.getSoItemTransactionDetailsUuid()).isEqualTo(UPDATED_SO_ITEM_TRANSACTION_DETAILS_UUID);
        assertThat(testSoItemTransactionDetails.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testSoItemTransactionDetails.getIsDropship()).isEqualTo(UPDATED_IS_DROPSHIP);
        assertThat(testSoItemTransactionDetails.getPoNo()).isEqualTo(UPDATED_PO_NO);
        assertThat(testSoItemTransactionDetails.getItemTransactionStatus()).isEqualTo(UPDATED_ITEM_TRANSACTION_STATUS);
    }

    @Test
    @Transactional
    void putNonExistingSoItemTransactionDetails() throws Exception {
        int databaseSizeBeforeUpdate = soItemTransactionDetailsRepository.findAll().size();
        soItemTransactionDetails.setSoItemTransactionDetailsId(count.incrementAndGet());

        // Create the SoItemTransactionDetails
        SoItemTransactionDetailsDTO soItemTransactionDetailsDTO = soItemTransactionDetailsMapper.toDto(soItemTransactionDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSoItemTransactionDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, soItemTransactionDetailsDTO.getSoItemTransactionDetailsId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(soItemTransactionDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SoItemTransactionDetails in the database
        List<SoItemTransactionDetails> soItemTransactionDetailsList = soItemTransactionDetailsRepository.findAll();
        assertThat(soItemTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSoItemTransactionDetails() throws Exception {
        int databaseSizeBeforeUpdate = soItemTransactionDetailsRepository.findAll().size();
        soItemTransactionDetails.setSoItemTransactionDetailsId(count.incrementAndGet());

        // Create the SoItemTransactionDetails
        SoItemTransactionDetailsDTO soItemTransactionDetailsDTO = soItemTransactionDetailsMapper.toDto(soItemTransactionDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSoItemTransactionDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(soItemTransactionDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SoItemTransactionDetails in the database
        List<SoItemTransactionDetails> soItemTransactionDetailsList = soItemTransactionDetailsRepository.findAll();
        assertThat(soItemTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSoItemTransactionDetails() throws Exception {
        int databaseSizeBeforeUpdate = soItemTransactionDetailsRepository.findAll().size();
        soItemTransactionDetails.setSoItemTransactionDetailsId(count.incrementAndGet());

        // Create the SoItemTransactionDetails
        SoItemTransactionDetailsDTO soItemTransactionDetailsDTO = soItemTransactionDetailsMapper.toDto(soItemTransactionDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSoItemTransactionDetailsMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(soItemTransactionDetailsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SoItemTransactionDetails in the database
        List<SoItemTransactionDetails> soItemTransactionDetailsList = soItemTransactionDetailsRepository.findAll();
        assertThat(soItemTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSoItemTransactionDetailsWithPatch() throws Exception {
        // Initialize the database
        soItemTransactionDetailsRepository.saveAndFlush(soItemTransactionDetails);

        int databaseSizeBeforeUpdate = soItemTransactionDetailsRepository.findAll().size();

        // Update the soItemTransactionDetails using partial update
        SoItemTransactionDetails partialUpdatedSoItemTransactionDetails = new SoItemTransactionDetails();
        partialUpdatedSoItemTransactionDetails.setSoItemTransactionDetailsId(soItemTransactionDetails.getSoItemTransactionDetailsId());

        partialUpdatedSoItemTransactionDetails
            .salesOrderNo(UPDATED_SALES_ORDER_NO)
            .itemId(UPDATED_ITEM_ID)
            .itemNo(UPDATED_ITEM_NO)
            .itemQty(UPDATED_ITEM_QTY)
            .assetNo(UPDATED_ASSET_NO)
            .branchId(UPDATED_BRANCH_ID)
            .status(UPDATED_STATUS)
            .transactionDate(UPDATED_TRANSACTION_DATE)
            .transactionNo(UPDATED_TRANSACTION_NO)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .soItemTransactionDetailsUuid(UPDATED_SO_ITEM_TRANSACTION_DETAILS_UUID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .poNo(UPDATED_PO_NO)
            .itemTransactionStatus(UPDATED_ITEM_TRANSACTION_STATUS);

        restSoItemTransactionDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSoItemTransactionDetails.getSoItemTransactionDetailsId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSoItemTransactionDetails))
            )
            .andExpect(status().isOk());

        // Validate the SoItemTransactionDetails in the database
        List<SoItemTransactionDetails> soItemTransactionDetailsList = soItemTransactionDetailsRepository.findAll();
        assertThat(soItemTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
        SoItemTransactionDetails testSoItemTransactionDetails = soItemTransactionDetailsList.get(soItemTransactionDetailsList.size() - 1);
        assertThat(testSoItemTransactionDetails.getSalesOrderNo()).isEqualTo(UPDATED_SALES_ORDER_NO);
        assertThat(testSoItemTransactionDetails.getSaleType()).isEqualTo(DEFAULT_SALE_TYPE);
        assertThat(testSoItemTransactionDetails.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testSoItemTransactionDetails.getItemNo()).isEqualTo(UPDATED_ITEM_NO);
        assertThat(testSoItemTransactionDetails.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testSoItemTransactionDetails.getItemUom()).isEqualTo(DEFAULT_ITEM_UOM);
        assertThat(testSoItemTransactionDetails.getItemQty()).isEqualTo(UPDATED_ITEM_QTY);
        assertThat(testSoItemTransactionDetails.getWheatherSerialized()).isEqualTo(DEFAULT_WHEATHER_SERIALIZED);
        assertThat(testSoItemTransactionDetails.getSerialNo()).isEqualTo(DEFAULT_SERIAL_NO);
        assertThat(testSoItemTransactionDetails.getWheatherAssetTagged()).isEqualTo(DEFAULT_WHEATHER_ASSET_TAGGED);
        assertThat(testSoItemTransactionDetails.getAssetNo()).isEqualTo(UPDATED_ASSET_NO);
        assertThat(testSoItemTransactionDetails.getOriginalDos()).isEqualTo(DEFAULT_ORIGINAL_DOS);
        assertThat(testSoItemTransactionDetails.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
        assertThat(testSoItemTransactionDetails.getLocationId()).isEqualTo(DEFAULT_LOCATION_ID);
        assertThat(testSoItemTransactionDetails.getLocationName()).isEqualTo(DEFAULT_LOCATION_NAME);
        assertThat(testSoItemTransactionDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSoItemTransactionDetails.getTransactionDate()).isEqualTo(UPDATED_TRANSACTION_DATE);
        assertThat(testSoItemTransactionDetails.getTransactionNo()).isEqualTo(UPDATED_TRANSACTION_NO);
        assertThat(testSoItemTransactionDetails.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testSoItemTransactionDetails.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testSoItemTransactionDetails.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testSoItemTransactionDetails.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testSoItemTransactionDetails.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testSoItemTransactionDetails.getSoItemTransactionDetailsUuid()).isEqualTo(UPDATED_SO_ITEM_TRANSACTION_DETAILS_UUID);
        assertThat(testSoItemTransactionDetails.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testSoItemTransactionDetails.getIsDropship()).isEqualTo(DEFAULT_IS_DROPSHIP);
        assertThat(testSoItemTransactionDetails.getPoNo()).isEqualTo(UPDATED_PO_NO);
        assertThat(testSoItemTransactionDetails.getItemTransactionStatus()).isEqualTo(UPDATED_ITEM_TRANSACTION_STATUS);
    }

    @Test
    @Transactional
    void fullUpdateSoItemTransactionDetailsWithPatch() throws Exception {
        // Initialize the database
        soItemTransactionDetailsRepository.saveAndFlush(soItemTransactionDetails);

        int databaseSizeBeforeUpdate = soItemTransactionDetailsRepository.findAll().size();

        // Update the soItemTransactionDetails using partial update
        SoItemTransactionDetails partialUpdatedSoItemTransactionDetails = new SoItemTransactionDetails();
        partialUpdatedSoItemTransactionDetails.setSoItemTransactionDetailsId(soItemTransactionDetails.getSoItemTransactionDetailsId());

        partialUpdatedSoItemTransactionDetails
            .salesOrderNo(UPDATED_SALES_ORDER_NO)
            .saleType(UPDATED_SALE_TYPE)
            .itemId(UPDATED_ITEM_ID)
            .itemNo(UPDATED_ITEM_NO)
            .itemName(UPDATED_ITEM_NAME)
            .itemUom(UPDATED_ITEM_UOM)
            .itemQty(UPDATED_ITEM_QTY)
            .wheatherSerialized(UPDATED_WHEATHER_SERIALIZED)
            .serialNo(UPDATED_SERIAL_NO)
            .wheatherAssetTagged(UPDATED_WHEATHER_ASSET_TAGGED)
            .assetNo(UPDATED_ASSET_NO)
            .originalDos(UPDATED_ORIGINAL_DOS)
            .branchId(UPDATED_BRANCH_ID)
            .locationId(UPDATED_LOCATION_ID)
            .locationName(UPDATED_LOCATION_NAME)
            .status(UPDATED_STATUS)
            .transactionDate(UPDATED_TRANSACTION_DATE)
            .transactionNo(UPDATED_TRANSACTION_NO)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .soItemTransactionDetailsUuid(UPDATED_SO_ITEM_TRANSACTION_DETAILS_UUID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .isDropship(UPDATED_IS_DROPSHIP)
            .poNo(UPDATED_PO_NO)
            .itemTransactionStatus(UPDATED_ITEM_TRANSACTION_STATUS);

        restSoItemTransactionDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSoItemTransactionDetails.getSoItemTransactionDetailsId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSoItemTransactionDetails))
            )
            .andExpect(status().isOk());

        // Validate the SoItemTransactionDetails in the database
        List<SoItemTransactionDetails> soItemTransactionDetailsList = soItemTransactionDetailsRepository.findAll();
        assertThat(soItemTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
        SoItemTransactionDetails testSoItemTransactionDetails = soItemTransactionDetailsList.get(soItemTransactionDetailsList.size() - 1);
        assertThat(testSoItemTransactionDetails.getSalesOrderNo()).isEqualTo(UPDATED_SALES_ORDER_NO);
        assertThat(testSoItemTransactionDetails.getSaleType()).isEqualTo(UPDATED_SALE_TYPE);
        assertThat(testSoItemTransactionDetails.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testSoItemTransactionDetails.getItemNo()).isEqualTo(UPDATED_ITEM_NO);
        assertThat(testSoItemTransactionDetails.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testSoItemTransactionDetails.getItemUom()).isEqualTo(UPDATED_ITEM_UOM);
        assertThat(testSoItemTransactionDetails.getItemQty()).isEqualTo(UPDATED_ITEM_QTY);
        assertThat(testSoItemTransactionDetails.getWheatherSerialized()).isEqualTo(UPDATED_WHEATHER_SERIALIZED);
        assertThat(testSoItemTransactionDetails.getSerialNo()).isEqualTo(UPDATED_SERIAL_NO);
        assertThat(testSoItemTransactionDetails.getWheatherAssetTagged()).isEqualTo(UPDATED_WHEATHER_ASSET_TAGGED);
        assertThat(testSoItemTransactionDetails.getAssetNo()).isEqualTo(UPDATED_ASSET_NO);
        assertThat(testSoItemTransactionDetails.getOriginalDos()).isEqualTo(UPDATED_ORIGINAL_DOS);
        assertThat(testSoItemTransactionDetails.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
        assertThat(testSoItemTransactionDetails.getLocationId()).isEqualTo(UPDATED_LOCATION_ID);
        assertThat(testSoItemTransactionDetails.getLocationName()).isEqualTo(UPDATED_LOCATION_NAME);
        assertThat(testSoItemTransactionDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSoItemTransactionDetails.getTransactionDate()).isEqualTo(UPDATED_TRANSACTION_DATE);
        assertThat(testSoItemTransactionDetails.getTransactionNo()).isEqualTo(UPDATED_TRANSACTION_NO);
        assertThat(testSoItemTransactionDetails.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testSoItemTransactionDetails.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testSoItemTransactionDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSoItemTransactionDetails.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testSoItemTransactionDetails.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testSoItemTransactionDetails.getSoItemTransactionDetailsUuid()).isEqualTo(UPDATED_SO_ITEM_TRANSACTION_DETAILS_UUID);
        assertThat(testSoItemTransactionDetails.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testSoItemTransactionDetails.getIsDropship()).isEqualTo(UPDATED_IS_DROPSHIP);
        assertThat(testSoItemTransactionDetails.getPoNo()).isEqualTo(UPDATED_PO_NO);
        assertThat(testSoItemTransactionDetails.getItemTransactionStatus()).isEqualTo(UPDATED_ITEM_TRANSACTION_STATUS);
    }

    @Test
    @Transactional
    void patchNonExistingSoItemTransactionDetails() throws Exception {
        int databaseSizeBeforeUpdate = soItemTransactionDetailsRepository.findAll().size();
        soItemTransactionDetails.setSoItemTransactionDetailsId(count.incrementAndGet());

        // Create the SoItemTransactionDetails
        SoItemTransactionDetailsDTO soItemTransactionDetailsDTO = soItemTransactionDetailsMapper.toDto(soItemTransactionDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSoItemTransactionDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, soItemTransactionDetailsDTO.getSoItemTransactionDetailsId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(soItemTransactionDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SoItemTransactionDetails in the database
        List<SoItemTransactionDetails> soItemTransactionDetailsList = soItemTransactionDetailsRepository.findAll();
        assertThat(soItemTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSoItemTransactionDetails() throws Exception {
        int databaseSizeBeforeUpdate = soItemTransactionDetailsRepository.findAll().size();
        soItemTransactionDetails.setSoItemTransactionDetailsId(count.incrementAndGet());

        // Create the SoItemTransactionDetails
        SoItemTransactionDetailsDTO soItemTransactionDetailsDTO = soItemTransactionDetailsMapper.toDto(soItemTransactionDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSoItemTransactionDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(soItemTransactionDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SoItemTransactionDetails in the database
        List<SoItemTransactionDetails> soItemTransactionDetailsList = soItemTransactionDetailsRepository.findAll();
        assertThat(soItemTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSoItemTransactionDetails() throws Exception {
        int databaseSizeBeforeUpdate = soItemTransactionDetailsRepository.findAll().size();
        soItemTransactionDetails.setSoItemTransactionDetailsId(count.incrementAndGet());

        // Create the SoItemTransactionDetails
        SoItemTransactionDetailsDTO soItemTransactionDetailsDTO = soItemTransactionDetailsMapper.toDto(soItemTransactionDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSoItemTransactionDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(soItemTransactionDetailsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SoItemTransactionDetails in the database
        List<SoItemTransactionDetails> soItemTransactionDetailsList = soItemTransactionDetailsRepository.findAll();
        assertThat(soItemTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSoItemTransactionDetails() throws Exception {
        // Initialize the database
        soItemTransactionDetailsRepository.saveAndFlush(soItemTransactionDetails);

        int databaseSizeBeforeDelete = soItemTransactionDetailsRepository.findAll().size();

        // Delete the soItemTransactionDetails
        restSoItemTransactionDetailsMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, soItemTransactionDetails.getSoItemTransactionDetailsId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SoItemTransactionDetails> soItemTransactionDetailsList = soItemTransactionDetailsRepository.findAll();
        assertThat(soItemTransactionDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
