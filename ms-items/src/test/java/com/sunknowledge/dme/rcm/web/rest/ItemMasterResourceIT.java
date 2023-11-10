package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ItemMaster;
import com.sunknowledge.dme.rcm.repository.ItemMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.ItemMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.ItemMasterMapper;
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
 * Integration tests for the {@link ItemMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ItemMasterResourceIT {

    private static final String DEFAULT_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_DESCRIPTION = "BBBBBBBBBB";

    private static final Long DEFAULT_ITEM_TYPE_ID = 1L;
    private static final Long UPDATED_ITEM_TYPE_ID = 2L;

    private static final Long DEFAULT_ITEM_GROUP_ID = 1L;
    private static final Long UPDATED_ITEM_GROUP_ID = 2L;

    private static final String DEFAULT_SALE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SALE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_COVERAGE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_COVERAGE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_WEIGHT = "AAAAAAAAAA";
    private static final String UPDATED_WEIGHT = "BBBBBBBBBB";

    private static final String DEFAULT_LOT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_LOT_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_KIT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_KIT_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_KIT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_KIT_TYPE = "BBBBBBBBBB";

    private static final Double DEFAULT_ITEM_PRICING_DEFAULT_RENTAL_AMT = 1D;
    private static final Double UPDATED_ITEM_PRICING_DEFAULT_RENTAL_AMT = 2D;

    private static final Double DEFAULT_ITEM_PRICING_DEFAULT_PURCHASE_AMT = 1D;
    private static final Double UPDATED_ITEM_PRICING_DEFAULT_PURCHASE_AMT = 2D;

    private static final String DEFAULT_AUTO_REORDER_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_AUTO_REORDER_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_EXCLUDE_PO_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_EXCLUDE_PO_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_EXCLUDE_SO_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_EXCLUDE_SO_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_NDC = "AAAAAAAAAA";
    private static final String UPDATED_NDC = "BBBBBBBBBB";

    private static final String DEFAULT_NDC_UNIT_OF_MEASUREMENT = "AAAAAAAAAA";
    private static final String UPDATED_NDC_UNIT_OF_MEASUREMENT = "BBBBBBBBBB";

    private static final String DEFAULT_MANUFACTURER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MANUFACTURER_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_MANUFACTURER_ID = 1L;
    private static final Long UPDATED_MANUFACTURER_ID = 2L;

    private static final String DEFAULT_MANUFACTURER_BARCODE = "AAAAAAAAAA";
    private static final String UPDATED_MANUFACTURER_BARCODE = "BBBBBBBBBB";

    private static final Long DEFAULT_DEFAULT_VENDOR_ID = 1L;
    private static final Long UPDATED_DEFAULT_VENDOR_ID = 2L;

    private static final String DEFAULT_EXCLUDE_STANDARD_PRICEING_STAT = "AAAAAAAAAA";
    private static final String UPDATED_EXCLUDE_STANDARD_PRICEING_STAT = "BBBBBBBBBB";

    private static final String DEFAULT_USER_FIELD_1 = "AAAAAAAAAA";
    private static final String UPDATED_USER_FIELD_1 = "BBBBBBBBBB";

    private static final String DEFAULT_USER_FIELD_2 = "AAAAAAAAAA";
    private static final String UPDATED_USER_FIELD_2 = "BBBBBBBBBB";

    private static final String DEFAULT_USER_FIELD_3 = "AAAAAAAAAA";
    private static final String UPDATED_USER_FIELD_3 = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_MULTIPLIER = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_MULTIPLIER = "BBBBBBBBBB";

    private static final String DEFAULT_CLAIM_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_CLAIM_NOTE = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_UOM = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_UOM = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final UUID DEFAULT_ITEM_MASTER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_ITEM_MASTER_UUID = UUID.randomUUID();

    private static final String DEFAULT_ITEM_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_GROUP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_GROUP_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_TYPE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_TYPE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_PROCEDURE_CODE_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_PROCEDURE_CODE_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_DEFAULT_VENDOR_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DEFAULT_VENDOR_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_RESUPPLY_TYPE_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_RESUPPLY_TYPE_STATUS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/item-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{itemId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ItemMasterRepository itemMasterRepository;

    @Autowired
    private ItemMasterMapper itemMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restItemMasterMockMvc;

    private ItemMaster itemMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemMaster createEntity(EntityManager em) {
        ItemMaster itemMaster = new ItemMaster()
            .itemName(DEFAULT_ITEM_NAME)
            .itemDescription(DEFAULT_ITEM_DESCRIPTION)
            .itemTypeId(DEFAULT_ITEM_TYPE_ID)
            .itemGroupId(DEFAULT_ITEM_GROUP_ID)
            .saleType(DEFAULT_SALE_TYPE)
            .coverageType(DEFAULT_COVERAGE_TYPE)
            .weight(DEFAULT_WEIGHT)
            .lotStatus(DEFAULT_LOT_STATUS)
            .kitStatus(DEFAULT_KIT_STATUS)
            .kitType(DEFAULT_KIT_TYPE)
            .itemPricingDefaultRentalAmt(DEFAULT_ITEM_PRICING_DEFAULT_RENTAL_AMT)
            .itemPricingDefaultPurchaseAmt(DEFAULT_ITEM_PRICING_DEFAULT_PURCHASE_AMT)
            .autoReorderStatus(DEFAULT_AUTO_REORDER_STATUS)
            .excludePoStatus(DEFAULT_EXCLUDE_PO_STATUS)
            .excludeSoStatus(DEFAULT_EXCLUDE_SO_STATUS)
            .ndc(DEFAULT_NDC)
            .ndcUnitOfMeasurement(DEFAULT_NDC_UNIT_OF_MEASUREMENT)
            .manufacturerName(DEFAULT_MANUFACTURER_NAME)
            .manufacturerId(DEFAULT_MANUFACTURER_ID)
            .manufacturerBarcode(DEFAULT_MANUFACTURER_BARCODE)
            .defaultVendorId(DEFAULT_DEFAULT_VENDOR_ID)
            .excludeStandardPriceingStat(DEFAULT_EXCLUDE_STANDARD_PRICEING_STAT)
            .userField1(DEFAULT_USER_FIELD_1)
            .userField2(DEFAULT_USER_FIELD_2)
            .userField3(DEFAULT_USER_FIELD_3)
            .billingMultiplier(DEFAULT_BILLING_MULTIPLIER)
            .claimNote(DEFAULT_CLAIM_NOTE)
            .itemUom(DEFAULT_ITEM_UOM)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .itemMasterUuid(DEFAULT_ITEM_MASTER_UUID)
            .itemNumber(DEFAULT_ITEM_NUMBER)
            .itemGroupName(DEFAULT_ITEM_GROUP_NAME)
            .itemTypeName(DEFAULT_ITEM_TYPE_NAME)
            .primaryProcedureCodeValue(DEFAULT_PRIMARY_PROCEDURE_CODE_VALUE)
            .defaultVendorName(DEFAULT_DEFAULT_VENDOR_NAME)
            .resupplyTypeStatus(DEFAULT_RESUPPLY_TYPE_STATUS);
        return itemMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemMaster createUpdatedEntity(EntityManager em) {
        ItemMaster itemMaster = new ItemMaster()
            .itemName(UPDATED_ITEM_NAME)
            .itemDescription(UPDATED_ITEM_DESCRIPTION)
            .itemTypeId(UPDATED_ITEM_TYPE_ID)
            .itemGroupId(UPDATED_ITEM_GROUP_ID)
            .saleType(UPDATED_SALE_TYPE)
            .coverageType(UPDATED_COVERAGE_TYPE)
            .weight(UPDATED_WEIGHT)
            .lotStatus(UPDATED_LOT_STATUS)
            .kitStatus(UPDATED_KIT_STATUS)
            .kitType(UPDATED_KIT_TYPE)
            .itemPricingDefaultRentalAmt(UPDATED_ITEM_PRICING_DEFAULT_RENTAL_AMT)
            .itemPricingDefaultPurchaseAmt(UPDATED_ITEM_PRICING_DEFAULT_PURCHASE_AMT)
            .autoReorderStatus(UPDATED_AUTO_REORDER_STATUS)
            .excludePoStatus(UPDATED_EXCLUDE_PO_STATUS)
            .excludeSoStatus(UPDATED_EXCLUDE_SO_STATUS)
            .ndc(UPDATED_NDC)
            .ndcUnitOfMeasurement(UPDATED_NDC_UNIT_OF_MEASUREMENT)
            .manufacturerName(UPDATED_MANUFACTURER_NAME)
            .manufacturerId(UPDATED_MANUFACTURER_ID)
            .manufacturerBarcode(UPDATED_MANUFACTURER_BARCODE)
            .defaultVendorId(UPDATED_DEFAULT_VENDOR_ID)
            .excludeStandardPriceingStat(UPDATED_EXCLUDE_STANDARD_PRICEING_STAT)
            .userField1(UPDATED_USER_FIELD_1)
            .userField2(UPDATED_USER_FIELD_2)
            .userField3(UPDATED_USER_FIELD_3)
            .billingMultiplier(UPDATED_BILLING_MULTIPLIER)
            .claimNote(UPDATED_CLAIM_NOTE)
            .itemUom(UPDATED_ITEM_UOM)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .itemMasterUuid(UPDATED_ITEM_MASTER_UUID)
            .itemNumber(UPDATED_ITEM_NUMBER)
            .itemGroupName(UPDATED_ITEM_GROUP_NAME)
            .itemTypeName(UPDATED_ITEM_TYPE_NAME)
            .primaryProcedureCodeValue(UPDATED_PRIMARY_PROCEDURE_CODE_VALUE)
            .defaultVendorName(UPDATED_DEFAULT_VENDOR_NAME)
            .resupplyTypeStatus(UPDATED_RESUPPLY_TYPE_STATUS);
        return itemMaster;
    }

    @BeforeEach
    public void initTest() {
        itemMaster = createEntity(em);
    }

    @Test
    @Transactional
    void createItemMaster() throws Exception {
        int databaseSizeBeforeCreate = itemMasterRepository.findAll().size();
        // Create the ItemMaster
        ItemMasterDTO itemMasterDTO = itemMasterMapper.toDto(itemMaster);
        restItemMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemMasterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ItemMaster in the database
        List<ItemMaster> itemMasterList = itemMasterRepository.findAll();
        assertThat(itemMasterList).hasSize(databaseSizeBeforeCreate + 1);
        ItemMaster testItemMaster = itemMasterList.get(itemMasterList.size() - 1);
        assertThat(testItemMaster.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testItemMaster.getItemDescription()).isEqualTo(DEFAULT_ITEM_DESCRIPTION);
        assertThat(testItemMaster.getItemTypeId()).isEqualTo(DEFAULT_ITEM_TYPE_ID);
        assertThat(testItemMaster.getItemGroupId()).isEqualTo(DEFAULT_ITEM_GROUP_ID);
        assertThat(testItemMaster.getSaleType()).isEqualTo(DEFAULT_SALE_TYPE);
        assertThat(testItemMaster.getCoverageType()).isEqualTo(DEFAULT_COVERAGE_TYPE);
        assertThat(testItemMaster.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testItemMaster.getLotStatus()).isEqualTo(DEFAULT_LOT_STATUS);
        assertThat(testItemMaster.getKitStatus()).isEqualTo(DEFAULT_KIT_STATUS);
        assertThat(testItemMaster.getKitType()).isEqualTo(DEFAULT_KIT_TYPE);
        assertThat(testItemMaster.getItemPricingDefaultRentalAmt()).isEqualTo(DEFAULT_ITEM_PRICING_DEFAULT_RENTAL_AMT);
        assertThat(testItemMaster.getItemPricingDefaultPurchaseAmt()).isEqualTo(DEFAULT_ITEM_PRICING_DEFAULT_PURCHASE_AMT);
        assertThat(testItemMaster.getAutoReorderStatus()).isEqualTo(DEFAULT_AUTO_REORDER_STATUS);
        assertThat(testItemMaster.getExcludePoStatus()).isEqualTo(DEFAULT_EXCLUDE_PO_STATUS);
        assertThat(testItemMaster.getExcludeSoStatus()).isEqualTo(DEFAULT_EXCLUDE_SO_STATUS);
        assertThat(testItemMaster.getNdc()).isEqualTo(DEFAULT_NDC);
        assertThat(testItemMaster.getNdcUnitOfMeasurement()).isEqualTo(DEFAULT_NDC_UNIT_OF_MEASUREMENT);
        assertThat(testItemMaster.getManufacturerName()).isEqualTo(DEFAULT_MANUFACTURER_NAME);
        assertThat(testItemMaster.getManufacturerId()).isEqualTo(DEFAULT_MANUFACTURER_ID);
        assertThat(testItemMaster.getManufacturerBarcode()).isEqualTo(DEFAULT_MANUFACTURER_BARCODE);
        assertThat(testItemMaster.getDefaultVendorId()).isEqualTo(DEFAULT_DEFAULT_VENDOR_ID);
        assertThat(testItemMaster.getExcludeStandardPriceingStat()).isEqualTo(DEFAULT_EXCLUDE_STANDARD_PRICEING_STAT);
        assertThat(testItemMaster.getUserField1()).isEqualTo(DEFAULT_USER_FIELD_1);
        assertThat(testItemMaster.getUserField2()).isEqualTo(DEFAULT_USER_FIELD_2);
        assertThat(testItemMaster.getUserField3()).isEqualTo(DEFAULT_USER_FIELD_3);
        assertThat(testItemMaster.getBillingMultiplier()).isEqualTo(DEFAULT_BILLING_MULTIPLIER);
        assertThat(testItemMaster.getClaimNote()).isEqualTo(DEFAULT_CLAIM_NOTE);
        assertThat(testItemMaster.getItemUom()).isEqualTo(DEFAULT_ITEM_UOM);
        assertThat(testItemMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testItemMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testItemMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testItemMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testItemMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testItemMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testItemMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testItemMaster.getItemMasterUuid()).isEqualTo(DEFAULT_ITEM_MASTER_UUID);
        assertThat(testItemMaster.getItemNumber()).isEqualTo(DEFAULT_ITEM_NUMBER);
        assertThat(testItemMaster.getItemGroupName()).isEqualTo(DEFAULT_ITEM_GROUP_NAME);
        assertThat(testItemMaster.getItemTypeName()).isEqualTo(DEFAULT_ITEM_TYPE_NAME);
        assertThat(testItemMaster.getPrimaryProcedureCodeValue()).isEqualTo(DEFAULT_PRIMARY_PROCEDURE_CODE_VALUE);
        assertThat(testItemMaster.getDefaultVendorName()).isEqualTo(DEFAULT_DEFAULT_VENDOR_NAME);
        assertThat(testItemMaster.getResupplyTypeStatus()).isEqualTo(DEFAULT_RESUPPLY_TYPE_STATUS);
    }

    @Test
    @Transactional
    void createItemMasterWithExistingId() throws Exception {
        // Create the ItemMaster with an existing ID
        itemMaster.setItemId(1L);
        ItemMasterDTO itemMasterDTO = itemMasterMapper.toDto(itemMaster);

        int databaseSizeBeforeCreate = itemMasterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemMaster in the database
        List<ItemMaster> itemMasterList = itemMasterRepository.findAll();
        assertThat(itemMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkItemNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemMasterRepository.findAll().size();
        // set the field null
        itemMaster.setItemName(null);

        // Create the ItemMaster, which fails.
        ItemMasterDTO itemMasterDTO = itemMasterMapper.toDto(itemMaster);

        restItemMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<ItemMaster> itemMasterList = itemMasterRepository.findAll();
        assertThat(itemMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkItemDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemMasterRepository.findAll().size();
        // set the field null
        itemMaster.setItemDescription(null);

        // Create the ItemMaster, which fails.
        ItemMasterDTO itemMasterDTO = itemMasterMapper.toDto(itemMaster);

        restItemMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<ItemMaster> itemMasterList = itemMasterRepository.findAll();
        assertThat(itemMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllItemMasters() throws Exception {
        // Initialize the database
        itemMasterRepository.saveAndFlush(itemMaster);

        // Get all the itemMasterList
        restItemMasterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=itemId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].itemId").value(hasItem(itemMaster.getItemId().intValue())))
            .andExpect(jsonPath("$.[*].itemName").value(hasItem(DEFAULT_ITEM_NAME)))
            .andExpect(jsonPath("$.[*].itemDescription").value(hasItem(DEFAULT_ITEM_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].itemTypeId").value(hasItem(DEFAULT_ITEM_TYPE_ID.intValue())))
            .andExpect(jsonPath("$.[*].itemGroupId").value(hasItem(DEFAULT_ITEM_GROUP_ID.intValue())))
            .andExpect(jsonPath("$.[*].saleType").value(hasItem(DEFAULT_SALE_TYPE)))
            .andExpect(jsonPath("$.[*].coverageType").value(hasItem(DEFAULT_COVERAGE_TYPE)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].lotStatus").value(hasItem(DEFAULT_LOT_STATUS)))
            .andExpect(jsonPath("$.[*].kitStatus").value(hasItem(DEFAULT_KIT_STATUS)))
            .andExpect(jsonPath("$.[*].kitType").value(hasItem(DEFAULT_KIT_TYPE)))
            .andExpect(jsonPath("$.[*].itemPricingDefaultRentalAmt").value(hasItem(DEFAULT_ITEM_PRICING_DEFAULT_RENTAL_AMT.doubleValue())))
            .andExpect(
                jsonPath("$.[*].itemPricingDefaultPurchaseAmt").value(hasItem(DEFAULT_ITEM_PRICING_DEFAULT_PURCHASE_AMT.doubleValue()))
            )
            .andExpect(jsonPath("$.[*].autoReorderStatus").value(hasItem(DEFAULT_AUTO_REORDER_STATUS)))
            .andExpect(jsonPath("$.[*].excludePoStatus").value(hasItem(DEFAULT_EXCLUDE_PO_STATUS)))
            .andExpect(jsonPath("$.[*].excludeSoStatus").value(hasItem(DEFAULT_EXCLUDE_SO_STATUS)))
            .andExpect(jsonPath("$.[*].ndc").value(hasItem(DEFAULT_NDC)))
            .andExpect(jsonPath("$.[*].ndcUnitOfMeasurement").value(hasItem(DEFAULT_NDC_UNIT_OF_MEASUREMENT)))
            .andExpect(jsonPath("$.[*].manufacturerName").value(hasItem(DEFAULT_MANUFACTURER_NAME)))
            .andExpect(jsonPath("$.[*].manufacturerId").value(hasItem(DEFAULT_MANUFACTURER_ID.intValue())))
            .andExpect(jsonPath("$.[*].manufacturerBarcode").value(hasItem(DEFAULT_MANUFACTURER_BARCODE)))
            .andExpect(jsonPath("$.[*].defaultVendorId").value(hasItem(DEFAULT_DEFAULT_VENDOR_ID.intValue())))
            .andExpect(jsonPath("$.[*].excludeStandardPriceingStat").value(hasItem(DEFAULT_EXCLUDE_STANDARD_PRICEING_STAT)))
            .andExpect(jsonPath("$.[*].userField1").value(hasItem(DEFAULT_USER_FIELD_1)))
            .andExpect(jsonPath("$.[*].userField2").value(hasItem(DEFAULT_USER_FIELD_2)))
            .andExpect(jsonPath("$.[*].userField3").value(hasItem(DEFAULT_USER_FIELD_3)))
            .andExpect(jsonPath("$.[*].billingMultiplier").value(hasItem(DEFAULT_BILLING_MULTIPLIER)))
            .andExpect(jsonPath("$.[*].claimNote").value(hasItem(DEFAULT_CLAIM_NOTE)))
            .andExpect(jsonPath("$.[*].itemUom").value(hasItem(DEFAULT_ITEM_UOM)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].itemMasterUuid").value(hasItem(DEFAULT_ITEM_MASTER_UUID.toString())))
            .andExpect(jsonPath("$.[*].itemNumber").value(hasItem(DEFAULT_ITEM_NUMBER)))
            .andExpect(jsonPath("$.[*].itemGroupName").value(hasItem(DEFAULT_ITEM_GROUP_NAME)))
            .andExpect(jsonPath("$.[*].itemTypeName").value(hasItem(DEFAULT_ITEM_TYPE_NAME)))
            .andExpect(jsonPath("$.[*].primaryProcedureCodeValue").value(hasItem(DEFAULT_PRIMARY_PROCEDURE_CODE_VALUE)))
            .andExpect(jsonPath("$.[*].defaultVendorName").value(hasItem(DEFAULT_DEFAULT_VENDOR_NAME)))
            .andExpect(jsonPath("$.[*].resupplyTypeStatus").value(hasItem(DEFAULT_RESUPPLY_TYPE_STATUS)));
    }

    @Test
    @Transactional
    void getItemMaster() throws Exception {
        // Initialize the database
        itemMasterRepository.saveAndFlush(itemMaster);

        // Get the itemMaster
        restItemMasterMockMvc
            .perform(get(ENTITY_API_URL_ID, itemMaster.getItemId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.itemId").value(itemMaster.getItemId().intValue()))
            .andExpect(jsonPath("$.itemName").value(DEFAULT_ITEM_NAME))
            .andExpect(jsonPath("$.itemDescription").value(DEFAULT_ITEM_DESCRIPTION))
            .andExpect(jsonPath("$.itemTypeId").value(DEFAULT_ITEM_TYPE_ID.intValue()))
            .andExpect(jsonPath("$.itemGroupId").value(DEFAULT_ITEM_GROUP_ID.intValue()))
            .andExpect(jsonPath("$.saleType").value(DEFAULT_SALE_TYPE))
            .andExpect(jsonPath("$.coverageType").value(DEFAULT_COVERAGE_TYPE))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT))
            .andExpect(jsonPath("$.lotStatus").value(DEFAULT_LOT_STATUS))
            .andExpect(jsonPath("$.kitStatus").value(DEFAULT_KIT_STATUS))
            .andExpect(jsonPath("$.kitType").value(DEFAULT_KIT_TYPE))
            .andExpect(jsonPath("$.itemPricingDefaultRentalAmt").value(DEFAULT_ITEM_PRICING_DEFAULT_RENTAL_AMT.doubleValue()))
            .andExpect(jsonPath("$.itemPricingDefaultPurchaseAmt").value(DEFAULT_ITEM_PRICING_DEFAULT_PURCHASE_AMT.doubleValue()))
            .andExpect(jsonPath("$.autoReorderStatus").value(DEFAULT_AUTO_REORDER_STATUS))
            .andExpect(jsonPath("$.excludePoStatus").value(DEFAULT_EXCLUDE_PO_STATUS))
            .andExpect(jsonPath("$.excludeSoStatus").value(DEFAULT_EXCLUDE_SO_STATUS))
            .andExpect(jsonPath("$.ndc").value(DEFAULT_NDC))
            .andExpect(jsonPath("$.ndcUnitOfMeasurement").value(DEFAULT_NDC_UNIT_OF_MEASUREMENT))
            .andExpect(jsonPath("$.manufacturerName").value(DEFAULT_MANUFACTURER_NAME))
            .andExpect(jsonPath("$.manufacturerId").value(DEFAULT_MANUFACTURER_ID.intValue()))
            .andExpect(jsonPath("$.manufacturerBarcode").value(DEFAULT_MANUFACTURER_BARCODE))
            .andExpect(jsonPath("$.defaultVendorId").value(DEFAULT_DEFAULT_VENDOR_ID.intValue()))
            .andExpect(jsonPath("$.excludeStandardPriceingStat").value(DEFAULT_EXCLUDE_STANDARD_PRICEING_STAT))
            .andExpect(jsonPath("$.userField1").value(DEFAULT_USER_FIELD_1))
            .andExpect(jsonPath("$.userField2").value(DEFAULT_USER_FIELD_2))
            .andExpect(jsonPath("$.userField3").value(DEFAULT_USER_FIELD_3))
            .andExpect(jsonPath("$.billingMultiplier").value(DEFAULT_BILLING_MULTIPLIER))
            .andExpect(jsonPath("$.claimNote").value(DEFAULT_CLAIM_NOTE))
            .andExpect(jsonPath("$.itemUom").value(DEFAULT_ITEM_UOM))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.itemMasterUuid").value(DEFAULT_ITEM_MASTER_UUID.toString()))
            .andExpect(jsonPath("$.itemNumber").value(DEFAULT_ITEM_NUMBER))
            .andExpect(jsonPath("$.itemGroupName").value(DEFAULT_ITEM_GROUP_NAME))
            .andExpect(jsonPath("$.itemTypeName").value(DEFAULT_ITEM_TYPE_NAME))
            .andExpect(jsonPath("$.primaryProcedureCodeValue").value(DEFAULT_PRIMARY_PROCEDURE_CODE_VALUE))
            .andExpect(jsonPath("$.defaultVendorName").value(DEFAULT_DEFAULT_VENDOR_NAME))
            .andExpect(jsonPath("$.resupplyTypeStatus").value(DEFAULT_RESUPPLY_TYPE_STATUS));
    }

    @Test
    @Transactional
    void getNonExistingItemMaster() throws Exception {
        // Get the itemMaster
        restItemMasterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingItemMaster() throws Exception {
        // Initialize the database
        itemMasterRepository.saveAndFlush(itemMaster);

        int databaseSizeBeforeUpdate = itemMasterRepository.findAll().size();

        // Update the itemMaster
        ItemMaster updatedItemMaster = itemMasterRepository.findById(itemMaster.getItemId()).get();
        // Disconnect from session so that the updates on updatedItemMaster are not directly saved in db
        em.detach(updatedItemMaster);
        updatedItemMaster
            .itemName(UPDATED_ITEM_NAME)
            .itemDescription(UPDATED_ITEM_DESCRIPTION)
            .itemTypeId(UPDATED_ITEM_TYPE_ID)
            .itemGroupId(UPDATED_ITEM_GROUP_ID)
            .saleType(UPDATED_SALE_TYPE)
            .coverageType(UPDATED_COVERAGE_TYPE)
            .weight(UPDATED_WEIGHT)
            .lotStatus(UPDATED_LOT_STATUS)
            .kitStatus(UPDATED_KIT_STATUS)
            .kitType(UPDATED_KIT_TYPE)
            .itemPricingDefaultRentalAmt(UPDATED_ITEM_PRICING_DEFAULT_RENTAL_AMT)
            .itemPricingDefaultPurchaseAmt(UPDATED_ITEM_PRICING_DEFAULT_PURCHASE_AMT)
            .autoReorderStatus(UPDATED_AUTO_REORDER_STATUS)
            .excludePoStatus(UPDATED_EXCLUDE_PO_STATUS)
            .excludeSoStatus(UPDATED_EXCLUDE_SO_STATUS)
            .ndc(UPDATED_NDC)
            .ndcUnitOfMeasurement(UPDATED_NDC_UNIT_OF_MEASUREMENT)
            .manufacturerName(UPDATED_MANUFACTURER_NAME)
            .manufacturerId(UPDATED_MANUFACTURER_ID)
            .manufacturerBarcode(UPDATED_MANUFACTURER_BARCODE)
            .defaultVendorId(UPDATED_DEFAULT_VENDOR_ID)
            .excludeStandardPriceingStat(UPDATED_EXCLUDE_STANDARD_PRICEING_STAT)
            .userField1(UPDATED_USER_FIELD_1)
            .userField2(UPDATED_USER_FIELD_2)
            .userField3(UPDATED_USER_FIELD_3)
            .billingMultiplier(UPDATED_BILLING_MULTIPLIER)
            .claimNote(UPDATED_CLAIM_NOTE)
            .itemUom(UPDATED_ITEM_UOM)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .itemMasterUuid(UPDATED_ITEM_MASTER_UUID)
            .itemNumber(UPDATED_ITEM_NUMBER)
            .itemGroupName(UPDATED_ITEM_GROUP_NAME)
            .itemTypeName(UPDATED_ITEM_TYPE_NAME)
            .primaryProcedureCodeValue(UPDATED_PRIMARY_PROCEDURE_CODE_VALUE)
            .defaultVendorName(UPDATED_DEFAULT_VENDOR_NAME)
            .resupplyTypeStatus(UPDATED_RESUPPLY_TYPE_STATUS);
        ItemMasterDTO itemMasterDTO = itemMasterMapper.toDto(updatedItemMaster);

        restItemMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, itemMasterDTO.getItemId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemMasterDTO))
            )
            .andExpect(status().isOk());

        // Validate the ItemMaster in the database
        List<ItemMaster> itemMasterList = itemMasterRepository.findAll();
        assertThat(itemMasterList).hasSize(databaseSizeBeforeUpdate);
        ItemMaster testItemMaster = itemMasterList.get(itemMasterList.size() - 1);
        assertThat(testItemMaster.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testItemMaster.getItemDescription()).isEqualTo(UPDATED_ITEM_DESCRIPTION);
        assertThat(testItemMaster.getItemTypeId()).isEqualTo(UPDATED_ITEM_TYPE_ID);
        assertThat(testItemMaster.getItemGroupId()).isEqualTo(UPDATED_ITEM_GROUP_ID);
        assertThat(testItemMaster.getSaleType()).isEqualTo(UPDATED_SALE_TYPE);
        assertThat(testItemMaster.getCoverageType()).isEqualTo(UPDATED_COVERAGE_TYPE);
        assertThat(testItemMaster.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testItemMaster.getLotStatus()).isEqualTo(UPDATED_LOT_STATUS);
        assertThat(testItemMaster.getKitStatus()).isEqualTo(UPDATED_KIT_STATUS);
        assertThat(testItemMaster.getKitType()).isEqualTo(UPDATED_KIT_TYPE);
        assertThat(testItemMaster.getItemPricingDefaultRentalAmt()).isEqualTo(UPDATED_ITEM_PRICING_DEFAULT_RENTAL_AMT);
        assertThat(testItemMaster.getItemPricingDefaultPurchaseAmt()).isEqualTo(UPDATED_ITEM_PRICING_DEFAULT_PURCHASE_AMT);
        assertThat(testItemMaster.getAutoReorderStatus()).isEqualTo(UPDATED_AUTO_REORDER_STATUS);
        assertThat(testItemMaster.getExcludePoStatus()).isEqualTo(UPDATED_EXCLUDE_PO_STATUS);
        assertThat(testItemMaster.getExcludeSoStatus()).isEqualTo(UPDATED_EXCLUDE_SO_STATUS);
        assertThat(testItemMaster.getNdc()).isEqualTo(UPDATED_NDC);
        assertThat(testItemMaster.getNdcUnitOfMeasurement()).isEqualTo(UPDATED_NDC_UNIT_OF_MEASUREMENT);
        assertThat(testItemMaster.getManufacturerName()).isEqualTo(UPDATED_MANUFACTURER_NAME);
        assertThat(testItemMaster.getManufacturerId()).isEqualTo(UPDATED_MANUFACTURER_ID);
        assertThat(testItemMaster.getManufacturerBarcode()).isEqualTo(UPDATED_MANUFACTURER_BARCODE);
        assertThat(testItemMaster.getDefaultVendorId()).isEqualTo(UPDATED_DEFAULT_VENDOR_ID);
        assertThat(testItemMaster.getExcludeStandardPriceingStat()).isEqualTo(UPDATED_EXCLUDE_STANDARD_PRICEING_STAT);
        assertThat(testItemMaster.getUserField1()).isEqualTo(UPDATED_USER_FIELD_1);
        assertThat(testItemMaster.getUserField2()).isEqualTo(UPDATED_USER_FIELD_2);
        assertThat(testItemMaster.getUserField3()).isEqualTo(UPDATED_USER_FIELD_3);
        assertThat(testItemMaster.getBillingMultiplier()).isEqualTo(UPDATED_BILLING_MULTIPLIER);
        assertThat(testItemMaster.getClaimNote()).isEqualTo(UPDATED_CLAIM_NOTE);
        assertThat(testItemMaster.getItemUom()).isEqualTo(UPDATED_ITEM_UOM);
        assertThat(testItemMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testItemMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testItemMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testItemMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testItemMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testItemMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testItemMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testItemMaster.getItemMasterUuid()).isEqualTo(UPDATED_ITEM_MASTER_UUID);
        assertThat(testItemMaster.getItemNumber()).isEqualTo(UPDATED_ITEM_NUMBER);
        assertThat(testItemMaster.getItemGroupName()).isEqualTo(UPDATED_ITEM_GROUP_NAME);
        assertThat(testItemMaster.getItemTypeName()).isEqualTo(UPDATED_ITEM_TYPE_NAME);
        assertThat(testItemMaster.getPrimaryProcedureCodeValue()).isEqualTo(UPDATED_PRIMARY_PROCEDURE_CODE_VALUE);
        assertThat(testItemMaster.getDefaultVendorName()).isEqualTo(UPDATED_DEFAULT_VENDOR_NAME);
        assertThat(testItemMaster.getResupplyTypeStatus()).isEqualTo(UPDATED_RESUPPLY_TYPE_STATUS);
    }

    @Test
    @Transactional
    void putNonExistingItemMaster() throws Exception {
        int databaseSizeBeforeUpdate = itemMasterRepository.findAll().size();
        itemMaster.setItemId(count.incrementAndGet());

        // Create the ItemMaster
        ItemMasterDTO itemMasterDTO = itemMasterMapper.toDto(itemMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, itemMasterDTO.getItemId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemMaster in the database
        List<ItemMaster> itemMasterList = itemMasterRepository.findAll();
        assertThat(itemMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchItemMaster() throws Exception {
        int databaseSizeBeforeUpdate = itemMasterRepository.findAll().size();
        itemMaster.setItemId(count.incrementAndGet());

        // Create the ItemMaster
        ItemMasterDTO itemMasterDTO = itemMasterMapper.toDto(itemMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemMaster in the database
        List<ItemMaster> itemMasterList = itemMasterRepository.findAll();
        assertThat(itemMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamItemMaster() throws Exception {
        int databaseSizeBeforeUpdate = itemMasterRepository.findAll().size();
        itemMaster.setItemId(count.incrementAndGet());

        // Create the ItemMaster
        ItemMasterDTO itemMasterDTO = itemMasterMapper.toDto(itemMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemMasterMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ItemMaster in the database
        List<ItemMaster> itemMasterList = itemMasterRepository.findAll();
        assertThat(itemMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateItemMasterWithPatch() throws Exception {
        // Initialize the database
        itemMasterRepository.saveAndFlush(itemMaster);

        int databaseSizeBeforeUpdate = itemMasterRepository.findAll().size();

        // Update the itemMaster using partial update
        ItemMaster partialUpdatedItemMaster = new ItemMaster();
        partialUpdatedItemMaster.setItemId(itemMaster.getItemId());

        partialUpdatedItemMaster
            .itemName(UPDATED_ITEM_NAME)
            .itemDescription(UPDATED_ITEM_DESCRIPTION)
            .itemTypeId(UPDATED_ITEM_TYPE_ID)
            .saleType(UPDATED_SALE_TYPE)
            .weight(UPDATED_WEIGHT)
            .lotStatus(UPDATED_LOT_STATUS)
            .kitStatus(UPDATED_KIT_STATUS)
            .kitType(UPDATED_KIT_TYPE)
            .itemPricingDefaultRentalAmt(UPDATED_ITEM_PRICING_DEFAULT_RENTAL_AMT)
            .excludePoStatus(UPDATED_EXCLUDE_PO_STATUS)
            .manufacturerId(UPDATED_MANUFACTURER_ID)
            .manufacturerBarcode(UPDATED_MANUFACTURER_BARCODE)
            .excludeStandardPriceingStat(UPDATED_EXCLUDE_STANDARD_PRICEING_STAT)
            .userField1(UPDATED_USER_FIELD_1)
            .userField2(UPDATED_USER_FIELD_2)
            .billingMultiplier(UPDATED_BILLING_MULTIPLIER)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .itemNumber(UPDATED_ITEM_NUMBER)
            .itemGroupName(UPDATED_ITEM_GROUP_NAME)
            .defaultVendorName(UPDATED_DEFAULT_VENDOR_NAME)
            .resupplyTypeStatus(UPDATED_RESUPPLY_TYPE_STATUS);

        restItemMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedItemMaster.getItemId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedItemMaster))
            )
            .andExpect(status().isOk());

        // Validate the ItemMaster in the database
        List<ItemMaster> itemMasterList = itemMasterRepository.findAll();
        assertThat(itemMasterList).hasSize(databaseSizeBeforeUpdate);
        ItemMaster testItemMaster = itemMasterList.get(itemMasterList.size() - 1);
        assertThat(testItemMaster.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testItemMaster.getItemDescription()).isEqualTo(UPDATED_ITEM_DESCRIPTION);
        assertThat(testItemMaster.getItemTypeId()).isEqualTo(UPDATED_ITEM_TYPE_ID);
        assertThat(testItemMaster.getItemGroupId()).isEqualTo(DEFAULT_ITEM_GROUP_ID);
        assertThat(testItemMaster.getSaleType()).isEqualTo(UPDATED_SALE_TYPE);
        assertThat(testItemMaster.getCoverageType()).isEqualTo(DEFAULT_COVERAGE_TYPE);
        assertThat(testItemMaster.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testItemMaster.getLotStatus()).isEqualTo(UPDATED_LOT_STATUS);
        assertThat(testItemMaster.getKitStatus()).isEqualTo(UPDATED_KIT_STATUS);
        assertThat(testItemMaster.getKitType()).isEqualTo(UPDATED_KIT_TYPE);
        assertThat(testItemMaster.getItemPricingDefaultRentalAmt()).isEqualTo(UPDATED_ITEM_PRICING_DEFAULT_RENTAL_AMT);
        assertThat(testItemMaster.getItemPricingDefaultPurchaseAmt()).isEqualTo(DEFAULT_ITEM_PRICING_DEFAULT_PURCHASE_AMT);
        assertThat(testItemMaster.getAutoReorderStatus()).isEqualTo(DEFAULT_AUTO_REORDER_STATUS);
        assertThat(testItemMaster.getExcludePoStatus()).isEqualTo(UPDATED_EXCLUDE_PO_STATUS);
        assertThat(testItemMaster.getExcludeSoStatus()).isEqualTo(DEFAULT_EXCLUDE_SO_STATUS);
        assertThat(testItemMaster.getNdc()).isEqualTo(DEFAULT_NDC);
        assertThat(testItemMaster.getNdcUnitOfMeasurement()).isEqualTo(DEFAULT_NDC_UNIT_OF_MEASUREMENT);
        assertThat(testItemMaster.getManufacturerName()).isEqualTo(DEFAULT_MANUFACTURER_NAME);
        assertThat(testItemMaster.getManufacturerId()).isEqualTo(UPDATED_MANUFACTURER_ID);
        assertThat(testItemMaster.getManufacturerBarcode()).isEqualTo(UPDATED_MANUFACTURER_BARCODE);
        assertThat(testItemMaster.getDefaultVendorId()).isEqualTo(DEFAULT_DEFAULT_VENDOR_ID);
        assertThat(testItemMaster.getExcludeStandardPriceingStat()).isEqualTo(UPDATED_EXCLUDE_STANDARD_PRICEING_STAT);
        assertThat(testItemMaster.getUserField1()).isEqualTo(UPDATED_USER_FIELD_1);
        assertThat(testItemMaster.getUserField2()).isEqualTo(UPDATED_USER_FIELD_2);
        assertThat(testItemMaster.getUserField3()).isEqualTo(DEFAULT_USER_FIELD_3);
        assertThat(testItemMaster.getBillingMultiplier()).isEqualTo(UPDATED_BILLING_MULTIPLIER);
        assertThat(testItemMaster.getClaimNote()).isEqualTo(DEFAULT_CLAIM_NOTE);
        assertThat(testItemMaster.getItemUom()).isEqualTo(DEFAULT_ITEM_UOM);
        assertThat(testItemMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testItemMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testItemMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testItemMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testItemMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testItemMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testItemMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testItemMaster.getItemMasterUuid()).isEqualTo(DEFAULT_ITEM_MASTER_UUID);
        assertThat(testItemMaster.getItemNumber()).isEqualTo(UPDATED_ITEM_NUMBER);
        assertThat(testItemMaster.getItemGroupName()).isEqualTo(UPDATED_ITEM_GROUP_NAME);
        assertThat(testItemMaster.getItemTypeName()).isEqualTo(DEFAULT_ITEM_TYPE_NAME);
        assertThat(testItemMaster.getPrimaryProcedureCodeValue()).isEqualTo(DEFAULT_PRIMARY_PROCEDURE_CODE_VALUE);
        assertThat(testItemMaster.getDefaultVendorName()).isEqualTo(UPDATED_DEFAULT_VENDOR_NAME);
        assertThat(testItemMaster.getResupplyTypeStatus()).isEqualTo(UPDATED_RESUPPLY_TYPE_STATUS);
    }

    @Test
    @Transactional
    void fullUpdateItemMasterWithPatch() throws Exception {
        // Initialize the database
        itemMasterRepository.saveAndFlush(itemMaster);

        int databaseSizeBeforeUpdate = itemMasterRepository.findAll().size();

        // Update the itemMaster using partial update
        ItemMaster partialUpdatedItemMaster = new ItemMaster();
        partialUpdatedItemMaster.setItemId(itemMaster.getItemId());

        partialUpdatedItemMaster
            .itemName(UPDATED_ITEM_NAME)
            .itemDescription(UPDATED_ITEM_DESCRIPTION)
            .itemTypeId(UPDATED_ITEM_TYPE_ID)
            .itemGroupId(UPDATED_ITEM_GROUP_ID)
            .saleType(UPDATED_SALE_TYPE)
            .coverageType(UPDATED_COVERAGE_TYPE)
            .weight(UPDATED_WEIGHT)
            .lotStatus(UPDATED_LOT_STATUS)
            .kitStatus(UPDATED_KIT_STATUS)
            .kitType(UPDATED_KIT_TYPE)
            .itemPricingDefaultRentalAmt(UPDATED_ITEM_PRICING_DEFAULT_RENTAL_AMT)
            .itemPricingDefaultPurchaseAmt(UPDATED_ITEM_PRICING_DEFAULT_PURCHASE_AMT)
            .autoReorderStatus(UPDATED_AUTO_REORDER_STATUS)
            .excludePoStatus(UPDATED_EXCLUDE_PO_STATUS)
            .excludeSoStatus(UPDATED_EXCLUDE_SO_STATUS)
            .ndc(UPDATED_NDC)
            .ndcUnitOfMeasurement(UPDATED_NDC_UNIT_OF_MEASUREMENT)
            .manufacturerName(UPDATED_MANUFACTURER_NAME)
            .manufacturerId(UPDATED_MANUFACTURER_ID)
            .manufacturerBarcode(UPDATED_MANUFACTURER_BARCODE)
            .defaultVendorId(UPDATED_DEFAULT_VENDOR_ID)
            .excludeStandardPriceingStat(UPDATED_EXCLUDE_STANDARD_PRICEING_STAT)
            .userField1(UPDATED_USER_FIELD_1)
            .userField2(UPDATED_USER_FIELD_2)
            .userField3(UPDATED_USER_FIELD_3)
            .billingMultiplier(UPDATED_BILLING_MULTIPLIER)
            .claimNote(UPDATED_CLAIM_NOTE)
            .itemUom(UPDATED_ITEM_UOM)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .itemMasterUuid(UPDATED_ITEM_MASTER_UUID)
            .itemNumber(UPDATED_ITEM_NUMBER)
            .itemGroupName(UPDATED_ITEM_GROUP_NAME)
            .itemTypeName(UPDATED_ITEM_TYPE_NAME)
            .primaryProcedureCodeValue(UPDATED_PRIMARY_PROCEDURE_CODE_VALUE)
            .defaultVendorName(UPDATED_DEFAULT_VENDOR_NAME)
            .resupplyTypeStatus(UPDATED_RESUPPLY_TYPE_STATUS);

        restItemMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedItemMaster.getItemId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedItemMaster))
            )
            .andExpect(status().isOk());

        // Validate the ItemMaster in the database
        List<ItemMaster> itemMasterList = itemMasterRepository.findAll();
        assertThat(itemMasterList).hasSize(databaseSizeBeforeUpdate);
        ItemMaster testItemMaster = itemMasterList.get(itemMasterList.size() - 1);
        assertThat(testItemMaster.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testItemMaster.getItemDescription()).isEqualTo(UPDATED_ITEM_DESCRIPTION);
        assertThat(testItemMaster.getItemTypeId()).isEqualTo(UPDATED_ITEM_TYPE_ID);
        assertThat(testItemMaster.getItemGroupId()).isEqualTo(UPDATED_ITEM_GROUP_ID);
        assertThat(testItemMaster.getSaleType()).isEqualTo(UPDATED_SALE_TYPE);
        assertThat(testItemMaster.getCoverageType()).isEqualTo(UPDATED_COVERAGE_TYPE);
        assertThat(testItemMaster.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testItemMaster.getLotStatus()).isEqualTo(UPDATED_LOT_STATUS);
        assertThat(testItemMaster.getKitStatus()).isEqualTo(UPDATED_KIT_STATUS);
        assertThat(testItemMaster.getKitType()).isEqualTo(UPDATED_KIT_TYPE);
        assertThat(testItemMaster.getItemPricingDefaultRentalAmt()).isEqualTo(UPDATED_ITEM_PRICING_DEFAULT_RENTAL_AMT);
        assertThat(testItemMaster.getItemPricingDefaultPurchaseAmt()).isEqualTo(UPDATED_ITEM_PRICING_DEFAULT_PURCHASE_AMT);
        assertThat(testItemMaster.getAutoReorderStatus()).isEqualTo(UPDATED_AUTO_REORDER_STATUS);
        assertThat(testItemMaster.getExcludePoStatus()).isEqualTo(UPDATED_EXCLUDE_PO_STATUS);
        assertThat(testItemMaster.getExcludeSoStatus()).isEqualTo(UPDATED_EXCLUDE_SO_STATUS);
        assertThat(testItemMaster.getNdc()).isEqualTo(UPDATED_NDC);
        assertThat(testItemMaster.getNdcUnitOfMeasurement()).isEqualTo(UPDATED_NDC_UNIT_OF_MEASUREMENT);
        assertThat(testItemMaster.getManufacturerName()).isEqualTo(UPDATED_MANUFACTURER_NAME);
        assertThat(testItemMaster.getManufacturerId()).isEqualTo(UPDATED_MANUFACTURER_ID);
        assertThat(testItemMaster.getManufacturerBarcode()).isEqualTo(UPDATED_MANUFACTURER_BARCODE);
        assertThat(testItemMaster.getDefaultVendorId()).isEqualTo(UPDATED_DEFAULT_VENDOR_ID);
        assertThat(testItemMaster.getExcludeStandardPriceingStat()).isEqualTo(UPDATED_EXCLUDE_STANDARD_PRICEING_STAT);
        assertThat(testItemMaster.getUserField1()).isEqualTo(UPDATED_USER_FIELD_1);
        assertThat(testItemMaster.getUserField2()).isEqualTo(UPDATED_USER_FIELD_2);
        assertThat(testItemMaster.getUserField3()).isEqualTo(UPDATED_USER_FIELD_3);
        assertThat(testItemMaster.getBillingMultiplier()).isEqualTo(UPDATED_BILLING_MULTIPLIER);
        assertThat(testItemMaster.getClaimNote()).isEqualTo(UPDATED_CLAIM_NOTE);
        assertThat(testItemMaster.getItemUom()).isEqualTo(UPDATED_ITEM_UOM);
        assertThat(testItemMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testItemMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testItemMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testItemMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testItemMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testItemMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testItemMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testItemMaster.getItemMasterUuid()).isEqualTo(UPDATED_ITEM_MASTER_UUID);
        assertThat(testItemMaster.getItemNumber()).isEqualTo(UPDATED_ITEM_NUMBER);
        assertThat(testItemMaster.getItemGroupName()).isEqualTo(UPDATED_ITEM_GROUP_NAME);
        assertThat(testItemMaster.getItemTypeName()).isEqualTo(UPDATED_ITEM_TYPE_NAME);
        assertThat(testItemMaster.getPrimaryProcedureCodeValue()).isEqualTo(UPDATED_PRIMARY_PROCEDURE_CODE_VALUE);
        assertThat(testItemMaster.getDefaultVendorName()).isEqualTo(UPDATED_DEFAULT_VENDOR_NAME);
        assertThat(testItemMaster.getResupplyTypeStatus()).isEqualTo(UPDATED_RESUPPLY_TYPE_STATUS);
    }

    @Test
    @Transactional
    void patchNonExistingItemMaster() throws Exception {
        int databaseSizeBeforeUpdate = itemMasterRepository.findAll().size();
        itemMaster.setItemId(count.incrementAndGet());

        // Create the ItemMaster
        ItemMasterDTO itemMasterDTO = itemMasterMapper.toDto(itemMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, itemMasterDTO.getItemId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemMaster in the database
        List<ItemMaster> itemMasterList = itemMasterRepository.findAll();
        assertThat(itemMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchItemMaster() throws Exception {
        int databaseSizeBeforeUpdate = itemMasterRepository.findAll().size();
        itemMaster.setItemId(count.incrementAndGet());

        // Create the ItemMaster
        ItemMasterDTO itemMasterDTO = itemMasterMapper.toDto(itemMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemMaster in the database
        List<ItemMaster> itemMasterList = itemMasterRepository.findAll();
        assertThat(itemMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamItemMaster() throws Exception {
        int databaseSizeBeforeUpdate = itemMasterRepository.findAll().size();
        itemMaster.setItemId(count.incrementAndGet());

        // Create the ItemMaster
        ItemMasterDTO itemMasterDTO = itemMasterMapper.toDto(itemMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemMasterMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ItemMaster in the database
        List<ItemMaster> itemMasterList = itemMasterRepository.findAll();
        assertThat(itemMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteItemMaster() throws Exception {
        // Initialize the database
        itemMasterRepository.saveAndFlush(itemMaster);

        int databaseSizeBeforeDelete = itemMasterRepository.findAll().size();

        // Delete the itemMaster
        restItemMasterMockMvc
            .perform(delete(ENTITY_API_URL_ID, itemMaster.getItemId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ItemMaster> itemMasterList = itemMasterRepository.findAll();
        assertThat(itemMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
