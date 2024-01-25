package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.BranchInsuranceMap;
import com.sunknowledge.dme.rcm.repository.BranchInsuranceMapRepository;
import com.sunknowledge.dme.rcm.service.dto.BranchInsuranceMapDTO;
import com.sunknowledge.dme.rcm.service.mapper.BranchInsuranceMapMapper;
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
 * Integration tests for the {@link BranchInsuranceMapResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BranchInsuranceMapResourceIT {

    private static final Long DEFAULT_BRANCH_ID = 1L;
    private static final Long UPDATED_BRANCH_ID = 2L;

    private static final String DEFAULT_BRANCH_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_INSURANCE_ID = 1L;
    private static final Long UPDATED_INSURANCE_ID = 2L;

    private static final String DEFAULT_INSURANCE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INSURANCE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_INSURANCE_ID_NO = "AAAAAAAAAA";
    private static final String UPDATED_INSURANCE_ID_NO = "BBBBBBBBBB";

    private static final String DEFAULT_INSURANCE_STATE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INSURANCE_STATE_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_PRICE_TABLE_ID = 1L;
    private static final Long UPDATED_PRICE_TABLE_ID = 2L;

    private static final String DEFAULT_PRICE_TABLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PRICE_TABLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_NPI = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_NPI = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_PTAN = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_PTAN = "BBBBBBBBBB";

    private static final String DEFAULT_ESUBMITTER_PAYOR_ID = "AAAAAAAAAA";
    private static final String UPDATED_ESUBMITTER_PAYOR_ID = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_TAXONOMY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_TAXONOMY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_PROVIDER_OVERRIDE_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_PROVIDER_OVERRIDE_COMPANY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_PROVIDER_OVERRIDE_TAX_ID_EIN = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_PROVIDER_OVERRIDE_TAX_ID_EIN = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_PROVIDER_OVERRIDE_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_PROVIDER_OVERRIDE_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_PROVIDER_OVERRIDE_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_PROVIDER_OVERRIDE_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_PROVIDER_OVERRIDE_CITY = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_PROVIDER_OVERRIDE_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_PROVIDER_OVERRIDE_STATE = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_PROVIDER_OVERRIDE_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_PROVIDER_OVERRIDE_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_PROVIDER_OVERRIDE_ZIP = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_PROVIDER_OVERRIDE_CONTACT_1 = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_PROVIDER_OVERRIDE_CONTACT_1 = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_PROVIDER_OVERRIDE_CONTACT_2 = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_PROVIDER_OVERRIDE_CONTACT_2 = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_PROVIDER_OVERRIDE_FAX = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_PROVIDER_OVERRIDE_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_PROVIDER_OVERRIDE_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_PROVIDER_OVERRIDE_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PAY_TO_PROVIDER_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PAY_TO_PROVIDER_COMPANY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PAY_TO_PROVIDER_TAX_ID_EIN = "AAAAAAAAAA";
    private static final String UPDATED_PAY_TO_PROVIDER_TAX_ID_EIN = "BBBBBBBBBB";

    private static final String DEFAULT_PAY_TO_PROVIDER_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_PAY_TO_PROVIDER_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_PAY_TO_PROVIDER_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_PAY_TO_PROVIDER_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_PAY_TO_PROVIDER_CITY = "AAAAAAAAAA";
    private static final String UPDATED_PAY_TO_PROVIDER_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_PAY_TO_PROVIDER_STATE = "AAAAAAAAAA";
    private static final String UPDATED_PAY_TO_PROVIDER_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_PAY_TO_PROVIDER_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_PAY_TO_PROVIDER_ZIP = "BBBBBBBBBB";

    private static final String DEFAULT_PAY_TO_PROVIDER_CONTACT_1 = "AAAAAAAAAA";
    private static final String UPDATED_PAY_TO_PROVIDER_CONTACT_1 = "BBBBBBBBBB";

    private static final String DEFAULT_PAY_TO_PROVIDER_CONTACT_2 = "AAAAAAAAAA";
    private static final String UPDATED_PAY_TO_PROVIDER_CONTACT_2 = "BBBBBBBBBB";

    private static final String DEFAULT_PAY_TO_PROVIDER_FAX = "AAAAAAAAAA";
    private static final String UPDATED_PAY_TO_PROVIDER_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_PAY_TO_PROVIDER_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_PAY_TO_PROVIDER_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_SUBMITTER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SUBMITTER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SUBMITTER_CONTACT_1 = "AAAAAAAAAA";
    private static final String UPDATED_SUBMITTER_CONTACT_1 = "BBBBBBBBBB";

    private static final String DEFAULT_SUBMITTER_CONTACT_2 = "AAAAAAAAAA";
    private static final String UPDATED_SUBMITTER_CONTACT_2 = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_BRANCH_INSURANCE_MAP_UUID = UUID.randomUUID();
    private static final UUID UPDATED_BRANCH_INSURANCE_MAP_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/branch-insurance-maps";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{branchInsuranceMapId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BranchInsuranceMapRepository branchInsuranceMapRepository;

    @Autowired
    private BranchInsuranceMapMapper branchInsuranceMapMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBranchInsuranceMapMockMvc;

    private BranchInsuranceMap branchInsuranceMap;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BranchInsuranceMap createEntity(EntityManager em) {
        BranchInsuranceMap branchInsuranceMap = new BranchInsuranceMap()
            .branchId(DEFAULT_BRANCH_ID)
            .branchName(DEFAULT_BRANCH_NAME)
            .insuranceId(DEFAULT_INSURANCE_ID)
            .insuranceName(DEFAULT_INSURANCE_NAME)
            .insuranceIdNo(DEFAULT_INSURANCE_ID_NO)
            .insuranceStateName(DEFAULT_INSURANCE_STATE_NAME)
            .priceTableId(DEFAULT_PRICE_TABLE_ID)
            .priceTableName(DEFAULT_PRICE_TABLE_NAME)
            .branchNpi(DEFAULT_BRANCH_NPI)
            .branchPtan(DEFAULT_BRANCH_PTAN)
            .esubmitterPayorId(DEFAULT_ESUBMITTER_PAYOR_ID)
            .branchTaxonomyCode(DEFAULT_BRANCH_TAXONOMY_CODE)
            .billingProviderOverrideCompanyName(DEFAULT_BILLING_PROVIDER_OVERRIDE_COMPANY_NAME)
            .billingProviderOverrideTaxIdEin(DEFAULT_BILLING_PROVIDER_OVERRIDE_TAX_ID_EIN)
            .billingProviderOverrideAddressLine1(DEFAULT_BILLING_PROVIDER_OVERRIDE_ADDRESS_LINE_1)
            .billingProviderOverrideAddressLine2(DEFAULT_BILLING_PROVIDER_OVERRIDE_ADDRESS_LINE_2)
            .billingProviderOverrideCity(DEFAULT_BILLING_PROVIDER_OVERRIDE_CITY)
            .billingProviderOverrideState(DEFAULT_BILLING_PROVIDER_OVERRIDE_STATE)
            .billingProviderOverrideZip(DEFAULT_BILLING_PROVIDER_OVERRIDE_ZIP)
            .billingProviderOverrideContact1(DEFAULT_BILLING_PROVIDER_OVERRIDE_CONTACT_1)
            .billingProviderOverrideContact2(DEFAULT_BILLING_PROVIDER_OVERRIDE_CONTACT_2)
            .billingProviderOverrideFax(DEFAULT_BILLING_PROVIDER_OVERRIDE_FAX)
            .billingProviderOverrideEmail(DEFAULT_BILLING_PROVIDER_OVERRIDE_EMAIL)
            .payToProviderCompanyName(DEFAULT_PAY_TO_PROVIDER_COMPANY_NAME)
            .payToProviderTaxIdEin(DEFAULT_PAY_TO_PROVIDER_TAX_ID_EIN)
            .payToProviderAddressLine1(DEFAULT_PAY_TO_PROVIDER_ADDRESS_LINE_1)
            .payToProviderAddressLine2(DEFAULT_PAY_TO_PROVIDER_ADDRESS_LINE_2)
            .payToProviderCity(DEFAULT_PAY_TO_PROVIDER_CITY)
            .payToProviderState(DEFAULT_PAY_TO_PROVIDER_STATE)
            .payToProviderZip(DEFAULT_PAY_TO_PROVIDER_ZIP)
            .payToProviderContact1(DEFAULT_PAY_TO_PROVIDER_CONTACT_1)
            .payToProviderContact2(DEFAULT_PAY_TO_PROVIDER_CONTACT_2)
            .payToProviderFax(DEFAULT_PAY_TO_PROVIDER_FAX)
            .payToProviderEmail(DEFAULT_PAY_TO_PROVIDER_EMAIL)
            .submitterName(DEFAULT_SUBMITTER_NAME)
            .submitterContact1(DEFAULT_SUBMITTER_CONTACT_1)
            .submitterContact2(DEFAULT_SUBMITTER_CONTACT_2)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .branchInsuranceMapUuid(DEFAULT_BRANCH_INSURANCE_MAP_UUID);
        return branchInsuranceMap;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BranchInsuranceMap createUpdatedEntity(EntityManager em) {
        BranchInsuranceMap branchInsuranceMap = new BranchInsuranceMap()
            .branchId(UPDATED_BRANCH_ID)
            .branchName(UPDATED_BRANCH_NAME)
            .insuranceId(UPDATED_INSURANCE_ID)
            .insuranceName(UPDATED_INSURANCE_NAME)
            .insuranceIdNo(UPDATED_INSURANCE_ID_NO)
            .insuranceStateName(UPDATED_INSURANCE_STATE_NAME)
            .priceTableId(UPDATED_PRICE_TABLE_ID)
            .priceTableName(UPDATED_PRICE_TABLE_NAME)
            .branchNpi(UPDATED_BRANCH_NPI)
            .branchPtan(UPDATED_BRANCH_PTAN)
            .esubmitterPayorId(UPDATED_ESUBMITTER_PAYOR_ID)
            .branchTaxonomyCode(UPDATED_BRANCH_TAXONOMY_CODE)
            .billingProviderOverrideCompanyName(UPDATED_BILLING_PROVIDER_OVERRIDE_COMPANY_NAME)
            .billingProviderOverrideTaxIdEin(UPDATED_BILLING_PROVIDER_OVERRIDE_TAX_ID_EIN)
            .billingProviderOverrideAddressLine1(UPDATED_BILLING_PROVIDER_OVERRIDE_ADDRESS_LINE_1)
            .billingProviderOverrideAddressLine2(UPDATED_BILLING_PROVIDER_OVERRIDE_ADDRESS_LINE_2)
            .billingProviderOverrideCity(UPDATED_BILLING_PROVIDER_OVERRIDE_CITY)
            .billingProviderOverrideState(UPDATED_BILLING_PROVIDER_OVERRIDE_STATE)
            .billingProviderOverrideZip(UPDATED_BILLING_PROVIDER_OVERRIDE_ZIP)
            .billingProviderOverrideContact1(UPDATED_BILLING_PROVIDER_OVERRIDE_CONTACT_1)
            .billingProviderOverrideContact2(UPDATED_BILLING_PROVIDER_OVERRIDE_CONTACT_2)
            .billingProviderOverrideFax(UPDATED_BILLING_PROVIDER_OVERRIDE_FAX)
            .billingProviderOverrideEmail(UPDATED_BILLING_PROVIDER_OVERRIDE_EMAIL)
            .payToProviderCompanyName(UPDATED_PAY_TO_PROVIDER_COMPANY_NAME)
            .payToProviderTaxIdEin(UPDATED_PAY_TO_PROVIDER_TAX_ID_EIN)
            .payToProviderAddressLine1(UPDATED_PAY_TO_PROVIDER_ADDRESS_LINE_1)
            .payToProviderAddressLine2(UPDATED_PAY_TO_PROVIDER_ADDRESS_LINE_2)
            .payToProviderCity(UPDATED_PAY_TO_PROVIDER_CITY)
            .payToProviderState(UPDATED_PAY_TO_PROVIDER_STATE)
            .payToProviderZip(UPDATED_PAY_TO_PROVIDER_ZIP)
            .payToProviderContact1(UPDATED_PAY_TO_PROVIDER_CONTACT_1)
            .payToProviderContact2(UPDATED_PAY_TO_PROVIDER_CONTACT_2)
            .payToProviderFax(UPDATED_PAY_TO_PROVIDER_FAX)
            .payToProviderEmail(UPDATED_PAY_TO_PROVIDER_EMAIL)
            .submitterName(UPDATED_SUBMITTER_NAME)
            .submitterContact1(UPDATED_SUBMITTER_CONTACT_1)
            .submitterContact2(UPDATED_SUBMITTER_CONTACT_2)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .branchInsuranceMapUuid(UPDATED_BRANCH_INSURANCE_MAP_UUID);
        return branchInsuranceMap;
    }

    @BeforeEach
    public void initTest() {
        branchInsuranceMap = createEntity(em);
    }

    @Test
    @Transactional
    void createBranchInsuranceMap() throws Exception {
        int databaseSizeBeforeCreate = branchInsuranceMapRepository.findAll().size();
        // Create the BranchInsuranceMap
        BranchInsuranceMapDTO branchInsuranceMapDTO = branchInsuranceMapMapper.toDto(branchInsuranceMap);
        restBranchInsuranceMapMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchInsuranceMapDTO))
            )
            .andExpect(status().isCreated());

        // Validate the BranchInsuranceMap in the database
        List<BranchInsuranceMap> branchInsuranceMapList = branchInsuranceMapRepository.findAll();
        assertThat(branchInsuranceMapList).hasSize(databaseSizeBeforeCreate + 1);
        BranchInsuranceMap testBranchInsuranceMap = branchInsuranceMapList.get(branchInsuranceMapList.size() - 1);
        assertThat(testBranchInsuranceMap.getBranchId()).isEqualTo(DEFAULT_BRANCH_ID);
        assertThat(testBranchInsuranceMap.getBranchName()).isEqualTo(DEFAULT_BRANCH_NAME);
        assertThat(testBranchInsuranceMap.getInsuranceId()).isEqualTo(DEFAULT_INSURANCE_ID);
        assertThat(testBranchInsuranceMap.getInsuranceName()).isEqualTo(DEFAULT_INSURANCE_NAME);
        assertThat(testBranchInsuranceMap.getInsuranceIdNo()).isEqualTo(DEFAULT_INSURANCE_ID_NO);
        assertThat(testBranchInsuranceMap.getInsuranceStateName()).isEqualTo(DEFAULT_INSURANCE_STATE_NAME);
        assertThat(testBranchInsuranceMap.getPriceTableId()).isEqualTo(DEFAULT_PRICE_TABLE_ID);
        assertThat(testBranchInsuranceMap.getPriceTableName()).isEqualTo(DEFAULT_PRICE_TABLE_NAME);
        assertThat(testBranchInsuranceMap.getBranchNpi()).isEqualTo(DEFAULT_BRANCH_NPI);
        assertThat(testBranchInsuranceMap.getBranchPtan()).isEqualTo(DEFAULT_BRANCH_PTAN);
        assertThat(testBranchInsuranceMap.getEsubmitterPayorId()).isEqualTo(DEFAULT_ESUBMITTER_PAYOR_ID);
        assertThat(testBranchInsuranceMap.getBranchTaxonomyCode()).isEqualTo(DEFAULT_BRANCH_TAXONOMY_CODE);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideCompanyName())
            .isEqualTo(DEFAULT_BILLING_PROVIDER_OVERRIDE_COMPANY_NAME);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideTaxIdEin()).isEqualTo(DEFAULT_BILLING_PROVIDER_OVERRIDE_TAX_ID_EIN);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideAddressLine1())
            .isEqualTo(DEFAULT_BILLING_PROVIDER_OVERRIDE_ADDRESS_LINE_1);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideAddressLine2())
            .isEqualTo(DEFAULT_BILLING_PROVIDER_OVERRIDE_ADDRESS_LINE_2);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideCity()).isEqualTo(DEFAULT_BILLING_PROVIDER_OVERRIDE_CITY);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideState()).isEqualTo(DEFAULT_BILLING_PROVIDER_OVERRIDE_STATE);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideZip()).isEqualTo(DEFAULT_BILLING_PROVIDER_OVERRIDE_ZIP);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideContact1()).isEqualTo(DEFAULT_BILLING_PROVIDER_OVERRIDE_CONTACT_1);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideContact2()).isEqualTo(DEFAULT_BILLING_PROVIDER_OVERRIDE_CONTACT_2);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideFax()).isEqualTo(DEFAULT_BILLING_PROVIDER_OVERRIDE_FAX);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideEmail()).isEqualTo(DEFAULT_BILLING_PROVIDER_OVERRIDE_EMAIL);
        assertThat(testBranchInsuranceMap.getPayToProviderCompanyName()).isEqualTo(DEFAULT_PAY_TO_PROVIDER_COMPANY_NAME);
        assertThat(testBranchInsuranceMap.getPayToProviderTaxIdEin()).isEqualTo(DEFAULT_PAY_TO_PROVIDER_TAX_ID_EIN);
        assertThat(testBranchInsuranceMap.getPayToProviderAddressLine1()).isEqualTo(DEFAULT_PAY_TO_PROVIDER_ADDRESS_LINE_1);
        assertThat(testBranchInsuranceMap.getPayToProviderAddressLine2()).isEqualTo(DEFAULT_PAY_TO_PROVIDER_ADDRESS_LINE_2);
        assertThat(testBranchInsuranceMap.getPayToProviderCity()).isEqualTo(DEFAULT_PAY_TO_PROVIDER_CITY);
        assertThat(testBranchInsuranceMap.getPayToProviderState()).isEqualTo(DEFAULT_PAY_TO_PROVIDER_STATE);
        assertThat(testBranchInsuranceMap.getPayToProviderZip()).isEqualTo(DEFAULT_PAY_TO_PROVIDER_ZIP);
        assertThat(testBranchInsuranceMap.getPayToProviderContact1()).isEqualTo(DEFAULT_PAY_TO_PROVIDER_CONTACT_1);
        assertThat(testBranchInsuranceMap.getPayToProviderContact2()).isEqualTo(DEFAULT_PAY_TO_PROVIDER_CONTACT_2);
        assertThat(testBranchInsuranceMap.getPayToProviderFax()).isEqualTo(DEFAULT_PAY_TO_PROVIDER_FAX);
        assertThat(testBranchInsuranceMap.getPayToProviderEmail()).isEqualTo(DEFAULT_PAY_TO_PROVIDER_EMAIL);
        assertThat(testBranchInsuranceMap.getSubmitterName()).isEqualTo(DEFAULT_SUBMITTER_NAME);
        assertThat(testBranchInsuranceMap.getSubmitterContact1()).isEqualTo(DEFAULT_SUBMITTER_CONTACT_1);
        assertThat(testBranchInsuranceMap.getSubmitterContact2()).isEqualTo(DEFAULT_SUBMITTER_CONTACT_2);
        assertThat(testBranchInsuranceMap.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testBranchInsuranceMap.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testBranchInsuranceMap.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testBranchInsuranceMap.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testBranchInsuranceMap.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testBranchInsuranceMap.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testBranchInsuranceMap.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testBranchInsuranceMap.getBranchInsuranceMapUuid()).isEqualTo(DEFAULT_BRANCH_INSURANCE_MAP_UUID);
    }

    @Test
    @Transactional
    void createBranchInsuranceMapWithExistingId() throws Exception {
        // Create the BranchInsuranceMap with an existing ID
        branchInsuranceMap.setBranchInsuranceMapId(1L);
        BranchInsuranceMapDTO branchInsuranceMapDTO = branchInsuranceMapMapper.toDto(branchInsuranceMap);

        int databaseSizeBeforeCreate = branchInsuranceMapRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBranchInsuranceMapMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchInsuranceMapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchInsuranceMap in the database
        List<BranchInsuranceMap> branchInsuranceMapList = branchInsuranceMapRepository.findAll();
        assertThat(branchInsuranceMapList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBranchInsuranceMaps() throws Exception {
        // Initialize the database
        branchInsuranceMapRepository.saveAndFlush(branchInsuranceMap);

        // Get all the branchInsuranceMapList
        restBranchInsuranceMapMockMvc
            .perform(get(ENTITY_API_URL + "?sort=branchInsuranceMapId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].branchInsuranceMapId").value(hasItem(branchInsuranceMap.getBranchInsuranceMapId().intValue())))
            .andExpect(jsonPath("$.[*].branchId").value(hasItem(DEFAULT_BRANCH_ID.intValue())))
            .andExpect(jsonPath("$.[*].branchName").value(hasItem(DEFAULT_BRANCH_NAME)))
            .andExpect(jsonPath("$.[*].insuranceId").value(hasItem(DEFAULT_INSURANCE_ID.intValue())))
            .andExpect(jsonPath("$.[*].insuranceName").value(hasItem(DEFAULT_INSURANCE_NAME)))
            .andExpect(jsonPath("$.[*].insuranceIdNo").value(hasItem(DEFAULT_INSURANCE_ID_NO)))
            .andExpect(jsonPath("$.[*].insuranceStateName").value(hasItem(DEFAULT_INSURANCE_STATE_NAME)))
            .andExpect(jsonPath("$.[*].priceTableId").value(hasItem(DEFAULT_PRICE_TABLE_ID.intValue())))
            .andExpect(jsonPath("$.[*].priceTableName").value(hasItem(DEFAULT_PRICE_TABLE_NAME)))
            .andExpect(jsonPath("$.[*].branchNpi").value(hasItem(DEFAULT_BRANCH_NPI)))
            .andExpect(jsonPath("$.[*].branchPtan").value(hasItem(DEFAULT_BRANCH_PTAN)))
            .andExpect(jsonPath("$.[*].esubmitterPayorId").value(hasItem(DEFAULT_ESUBMITTER_PAYOR_ID)))
            .andExpect(jsonPath("$.[*].branchTaxonomyCode").value(hasItem(DEFAULT_BRANCH_TAXONOMY_CODE)))
            .andExpect(jsonPath("$.[*].billingProviderOverrideCompanyName").value(hasItem(DEFAULT_BILLING_PROVIDER_OVERRIDE_COMPANY_NAME)))
            .andExpect(jsonPath("$.[*].billingProviderOverrideTaxIdEin").value(hasItem(DEFAULT_BILLING_PROVIDER_OVERRIDE_TAX_ID_EIN)))
            .andExpect(
                jsonPath("$.[*].billingProviderOverrideAddressLine1").value(hasItem(DEFAULT_BILLING_PROVIDER_OVERRIDE_ADDRESS_LINE_1))
            )
            .andExpect(
                jsonPath("$.[*].billingProviderOverrideAddressLine2").value(hasItem(DEFAULT_BILLING_PROVIDER_OVERRIDE_ADDRESS_LINE_2))
            )
            .andExpect(jsonPath("$.[*].billingProviderOverrideCity").value(hasItem(DEFAULT_BILLING_PROVIDER_OVERRIDE_CITY)))
            .andExpect(jsonPath("$.[*].billingProviderOverrideState").value(hasItem(DEFAULT_BILLING_PROVIDER_OVERRIDE_STATE)))
            .andExpect(jsonPath("$.[*].billingProviderOverrideZip").value(hasItem(DEFAULT_BILLING_PROVIDER_OVERRIDE_ZIP)))
            .andExpect(jsonPath("$.[*].billingProviderOverrideContact1").value(hasItem(DEFAULT_BILLING_PROVIDER_OVERRIDE_CONTACT_1)))
            .andExpect(jsonPath("$.[*].billingProviderOverrideContact2").value(hasItem(DEFAULT_BILLING_PROVIDER_OVERRIDE_CONTACT_2)))
            .andExpect(jsonPath("$.[*].billingProviderOverrideFax").value(hasItem(DEFAULT_BILLING_PROVIDER_OVERRIDE_FAX)))
            .andExpect(jsonPath("$.[*].billingProviderOverrideEmail").value(hasItem(DEFAULT_BILLING_PROVIDER_OVERRIDE_EMAIL)))
            .andExpect(jsonPath("$.[*].payToProviderCompanyName").value(hasItem(DEFAULT_PAY_TO_PROVIDER_COMPANY_NAME)))
            .andExpect(jsonPath("$.[*].payToProviderTaxIdEin").value(hasItem(DEFAULT_PAY_TO_PROVIDER_TAX_ID_EIN)))
            .andExpect(jsonPath("$.[*].payToProviderAddressLine1").value(hasItem(DEFAULT_PAY_TO_PROVIDER_ADDRESS_LINE_1)))
            .andExpect(jsonPath("$.[*].payToProviderAddressLine2").value(hasItem(DEFAULT_PAY_TO_PROVIDER_ADDRESS_LINE_2)))
            .andExpect(jsonPath("$.[*].payToProviderCity").value(hasItem(DEFAULT_PAY_TO_PROVIDER_CITY)))
            .andExpect(jsonPath("$.[*].payToProviderState").value(hasItem(DEFAULT_PAY_TO_PROVIDER_STATE)))
            .andExpect(jsonPath("$.[*].payToProviderZip").value(hasItem(DEFAULT_PAY_TO_PROVIDER_ZIP)))
            .andExpect(jsonPath("$.[*].payToProviderContact1").value(hasItem(DEFAULT_PAY_TO_PROVIDER_CONTACT_1)))
            .andExpect(jsonPath("$.[*].payToProviderContact2").value(hasItem(DEFAULT_PAY_TO_PROVIDER_CONTACT_2)))
            .andExpect(jsonPath("$.[*].payToProviderFax").value(hasItem(DEFAULT_PAY_TO_PROVIDER_FAX)))
            .andExpect(jsonPath("$.[*].payToProviderEmail").value(hasItem(DEFAULT_PAY_TO_PROVIDER_EMAIL)))
            .andExpect(jsonPath("$.[*].submitterName").value(hasItem(DEFAULT_SUBMITTER_NAME)))
            .andExpect(jsonPath("$.[*].submitterContact1").value(hasItem(DEFAULT_SUBMITTER_CONTACT_1)))
            .andExpect(jsonPath("$.[*].submitterContact2").value(hasItem(DEFAULT_SUBMITTER_CONTACT_2)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].branchInsuranceMapUuid").value(hasItem(DEFAULT_BRANCH_INSURANCE_MAP_UUID.toString())));
    }

    @Test
    @Transactional
    void getBranchInsuranceMap() throws Exception {
        // Initialize the database
        branchInsuranceMapRepository.saveAndFlush(branchInsuranceMap);

        // Get the branchInsuranceMap
        restBranchInsuranceMapMockMvc
            .perform(get(ENTITY_API_URL_ID, branchInsuranceMap.getBranchInsuranceMapId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.branchInsuranceMapId").value(branchInsuranceMap.getBranchInsuranceMapId().intValue()))
            .andExpect(jsonPath("$.branchId").value(DEFAULT_BRANCH_ID.intValue()))
            .andExpect(jsonPath("$.branchName").value(DEFAULT_BRANCH_NAME))
            .andExpect(jsonPath("$.insuranceId").value(DEFAULT_INSURANCE_ID.intValue()))
            .andExpect(jsonPath("$.insuranceName").value(DEFAULT_INSURANCE_NAME))
            .andExpect(jsonPath("$.insuranceIdNo").value(DEFAULT_INSURANCE_ID_NO))
            .andExpect(jsonPath("$.insuranceStateName").value(DEFAULT_INSURANCE_STATE_NAME))
            .andExpect(jsonPath("$.priceTableId").value(DEFAULT_PRICE_TABLE_ID.intValue()))
            .andExpect(jsonPath("$.priceTableName").value(DEFAULT_PRICE_TABLE_NAME))
            .andExpect(jsonPath("$.branchNpi").value(DEFAULT_BRANCH_NPI))
            .andExpect(jsonPath("$.branchPtan").value(DEFAULT_BRANCH_PTAN))
            .andExpect(jsonPath("$.esubmitterPayorId").value(DEFAULT_ESUBMITTER_PAYOR_ID))
            .andExpect(jsonPath("$.branchTaxonomyCode").value(DEFAULT_BRANCH_TAXONOMY_CODE))
            .andExpect(jsonPath("$.billingProviderOverrideCompanyName").value(DEFAULT_BILLING_PROVIDER_OVERRIDE_COMPANY_NAME))
            .andExpect(jsonPath("$.billingProviderOverrideTaxIdEin").value(DEFAULT_BILLING_PROVIDER_OVERRIDE_TAX_ID_EIN))
            .andExpect(jsonPath("$.billingProviderOverrideAddressLine1").value(DEFAULT_BILLING_PROVIDER_OVERRIDE_ADDRESS_LINE_1))
            .andExpect(jsonPath("$.billingProviderOverrideAddressLine2").value(DEFAULT_BILLING_PROVIDER_OVERRIDE_ADDRESS_LINE_2))
            .andExpect(jsonPath("$.billingProviderOverrideCity").value(DEFAULT_BILLING_PROVIDER_OVERRIDE_CITY))
            .andExpect(jsonPath("$.billingProviderOverrideState").value(DEFAULT_BILLING_PROVIDER_OVERRIDE_STATE))
            .andExpect(jsonPath("$.billingProviderOverrideZip").value(DEFAULT_BILLING_PROVIDER_OVERRIDE_ZIP))
            .andExpect(jsonPath("$.billingProviderOverrideContact1").value(DEFAULT_BILLING_PROVIDER_OVERRIDE_CONTACT_1))
            .andExpect(jsonPath("$.billingProviderOverrideContact2").value(DEFAULT_BILLING_PROVIDER_OVERRIDE_CONTACT_2))
            .andExpect(jsonPath("$.billingProviderOverrideFax").value(DEFAULT_BILLING_PROVIDER_OVERRIDE_FAX))
            .andExpect(jsonPath("$.billingProviderOverrideEmail").value(DEFAULT_BILLING_PROVIDER_OVERRIDE_EMAIL))
            .andExpect(jsonPath("$.payToProviderCompanyName").value(DEFAULT_PAY_TO_PROVIDER_COMPANY_NAME))
            .andExpect(jsonPath("$.payToProviderTaxIdEin").value(DEFAULT_PAY_TO_PROVIDER_TAX_ID_EIN))
            .andExpect(jsonPath("$.payToProviderAddressLine1").value(DEFAULT_PAY_TO_PROVIDER_ADDRESS_LINE_1))
            .andExpect(jsonPath("$.payToProviderAddressLine2").value(DEFAULT_PAY_TO_PROVIDER_ADDRESS_LINE_2))
            .andExpect(jsonPath("$.payToProviderCity").value(DEFAULT_PAY_TO_PROVIDER_CITY))
            .andExpect(jsonPath("$.payToProviderState").value(DEFAULT_PAY_TO_PROVIDER_STATE))
            .andExpect(jsonPath("$.payToProviderZip").value(DEFAULT_PAY_TO_PROVIDER_ZIP))
            .andExpect(jsonPath("$.payToProviderContact1").value(DEFAULT_PAY_TO_PROVIDER_CONTACT_1))
            .andExpect(jsonPath("$.payToProviderContact2").value(DEFAULT_PAY_TO_PROVIDER_CONTACT_2))
            .andExpect(jsonPath("$.payToProviderFax").value(DEFAULT_PAY_TO_PROVIDER_FAX))
            .andExpect(jsonPath("$.payToProviderEmail").value(DEFAULT_PAY_TO_PROVIDER_EMAIL))
            .andExpect(jsonPath("$.submitterName").value(DEFAULT_SUBMITTER_NAME))
            .andExpect(jsonPath("$.submitterContact1").value(DEFAULT_SUBMITTER_CONTACT_1))
            .andExpect(jsonPath("$.submitterContact2").value(DEFAULT_SUBMITTER_CONTACT_2))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.branchInsuranceMapUuid").value(DEFAULT_BRANCH_INSURANCE_MAP_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingBranchInsuranceMap() throws Exception {
        // Get the branchInsuranceMap
        restBranchInsuranceMapMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingBranchInsuranceMap() throws Exception {
        // Initialize the database
        branchInsuranceMapRepository.saveAndFlush(branchInsuranceMap);

        int databaseSizeBeforeUpdate = branchInsuranceMapRepository.findAll().size();

        // Update the branchInsuranceMap
        BranchInsuranceMap updatedBranchInsuranceMap = branchInsuranceMapRepository
            .findById(branchInsuranceMap.getBranchInsuranceMapId())
            .get();
        // Disconnect from session so that the updates on updatedBranchInsuranceMap are not directly saved in db
        em.detach(updatedBranchInsuranceMap);
        updatedBranchInsuranceMap
            .branchId(UPDATED_BRANCH_ID)
            .branchName(UPDATED_BRANCH_NAME)
            .insuranceId(UPDATED_INSURANCE_ID)
            .insuranceName(UPDATED_INSURANCE_NAME)
            .insuranceIdNo(UPDATED_INSURANCE_ID_NO)
            .insuranceStateName(UPDATED_INSURANCE_STATE_NAME)
            .priceTableId(UPDATED_PRICE_TABLE_ID)
            .priceTableName(UPDATED_PRICE_TABLE_NAME)
            .branchNpi(UPDATED_BRANCH_NPI)
            .branchPtan(UPDATED_BRANCH_PTAN)
            .esubmitterPayorId(UPDATED_ESUBMITTER_PAYOR_ID)
            .branchTaxonomyCode(UPDATED_BRANCH_TAXONOMY_CODE)
            .billingProviderOverrideCompanyName(UPDATED_BILLING_PROVIDER_OVERRIDE_COMPANY_NAME)
            .billingProviderOverrideTaxIdEin(UPDATED_BILLING_PROVIDER_OVERRIDE_TAX_ID_EIN)
            .billingProviderOverrideAddressLine1(UPDATED_BILLING_PROVIDER_OVERRIDE_ADDRESS_LINE_1)
            .billingProviderOverrideAddressLine2(UPDATED_BILLING_PROVIDER_OVERRIDE_ADDRESS_LINE_2)
            .billingProviderOverrideCity(UPDATED_BILLING_PROVIDER_OVERRIDE_CITY)
            .billingProviderOverrideState(UPDATED_BILLING_PROVIDER_OVERRIDE_STATE)
            .billingProviderOverrideZip(UPDATED_BILLING_PROVIDER_OVERRIDE_ZIP)
            .billingProviderOverrideContact1(UPDATED_BILLING_PROVIDER_OVERRIDE_CONTACT_1)
            .billingProviderOverrideContact2(UPDATED_BILLING_PROVIDER_OVERRIDE_CONTACT_2)
            .billingProviderOverrideFax(UPDATED_BILLING_PROVIDER_OVERRIDE_FAX)
            .billingProviderOverrideEmail(UPDATED_BILLING_PROVIDER_OVERRIDE_EMAIL)
            .payToProviderCompanyName(UPDATED_PAY_TO_PROVIDER_COMPANY_NAME)
            .payToProviderTaxIdEin(UPDATED_PAY_TO_PROVIDER_TAX_ID_EIN)
            .payToProviderAddressLine1(UPDATED_PAY_TO_PROVIDER_ADDRESS_LINE_1)
            .payToProviderAddressLine2(UPDATED_PAY_TO_PROVIDER_ADDRESS_LINE_2)
            .payToProviderCity(UPDATED_PAY_TO_PROVIDER_CITY)
            .payToProviderState(UPDATED_PAY_TO_PROVIDER_STATE)
            .payToProviderZip(UPDATED_PAY_TO_PROVIDER_ZIP)
            .payToProviderContact1(UPDATED_PAY_TO_PROVIDER_CONTACT_1)
            .payToProviderContact2(UPDATED_PAY_TO_PROVIDER_CONTACT_2)
            .payToProviderFax(UPDATED_PAY_TO_PROVIDER_FAX)
            .payToProviderEmail(UPDATED_PAY_TO_PROVIDER_EMAIL)
            .submitterName(UPDATED_SUBMITTER_NAME)
            .submitterContact1(UPDATED_SUBMITTER_CONTACT_1)
            .submitterContact2(UPDATED_SUBMITTER_CONTACT_2)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .branchInsuranceMapUuid(UPDATED_BRANCH_INSURANCE_MAP_UUID);
        BranchInsuranceMapDTO branchInsuranceMapDTO = branchInsuranceMapMapper.toDto(updatedBranchInsuranceMap);

        restBranchInsuranceMapMockMvc
            .perform(
                put(ENTITY_API_URL_ID, branchInsuranceMapDTO.getBranchInsuranceMapId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchInsuranceMapDTO))
            )
            .andExpect(status().isOk());

        // Validate the BranchInsuranceMap in the database
        List<BranchInsuranceMap> branchInsuranceMapList = branchInsuranceMapRepository.findAll();
        assertThat(branchInsuranceMapList).hasSize(databaseSizeBeforeUpdate);
        BranchInsuranceMap testBranchInsuranceMap = branchInsuranceMapList.get(branchInsuranceMapList.size() - 1);
        assertThat(testBranchInsuranceMap.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
        assertThat(testBranchInsuranceMap.getBranchName()).isEqualTo(UPDATED_BRANCH_NAME);
        assertThat(testBranchInsuranceMap.getInsuranceId()).isEqualTo(UPDATED_INSURANCE_ID);
        assertThat(testBranchInsuranceMap.getInsuranceName()).isEqualTo(UPDATED_INSURANCE_NAME);
        assertThat(testBranchInsuranceMap.getInsuranceIdNo()).isEqualTo(UPDATED_INSURANCE_ID_NO);
        assertThat(testBranchInsuranceMap.getInsuranceStateName()).isEqualTo(UPDATED_INSURANCE_STATE_NAME);
        assertThat(testBranchInsuranceMap.getPriceTableId()).isEqualTo(UPDATED_PRICE_TABLE_ID);
        assertThat(testBranchInsuranceMap.getPriceTableName()).isEqualTo(UPDATED_PRICE_TABLE_NAME);
        assertThat(testBranchInsuranceMap.getBranchNpi()).isEqualTo(UPDATED_BRANCH_NPI);
        assertThat(testBranchInsuranceMap.getBranchPtan()).isEqualTo(UPDATED_BRANCH_PTAN);
        assertThat(testBranchInsuranceMap.getEsubmitterPayorId()).isEqualTo(UPDATED_ESUBMITTER_PAYOR_ID);
        assertThat(testBranchInsuranceMap.getBranchTaxonomyCode()).isEqualTo(UPDATED_BRANCH_TAXONOMY_CODE);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideCompanyName())
            .isEqualTo(UPDATED_BILLING_PROVIDER_OVERRIDE_COMPANY_NAME);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideTaxIdEin()).isEqualTo(UPDATED_BILLING_PROVIDER_OVERRIDE_TAX_ID_EIN);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideAddressLine1())
            .isEqualTo(UPDATED_BILLING_PROVIDER_OVERRIDE_ADDRESS_LINE_1);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideAddressLine2())
            .isEqualTo(UPDATED_BILLING_PROVIDER_OVERRIDE_ADDRESS_LINE_2);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideCity()).isEqualTo(UPDATED_BILLING_PROVIDER_OVERRIDE_CITY);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideState()).isEqualTo(UPDATED_BILLING_PROVIDER_OVERRIDE_STATE);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideZip()).isEqualTo(UPDATED_BILLING_PROVIDER_OVERRIDE_ZIP);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideContact1()).isEqualTo(UPDATED_BILLING_PROVIDER_OVERRIDE_CONTACT_1);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideContact2()).isEqualTo(UPDATED_BILLING_PROVIDER_OVERRIDE_CONTACT_2);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideFax()).isEqualTo(UPDATED_BILLING_PROVIDER_OVERRIDE_FAX);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideEmail()).isEqualTo(UPDATED_BILLING_PROVIDER_OVERRIDE_EMAIL);
        assertThat(testBranchInsuranceMap.getPayToProviderCompanyName()).isEqualTo(UPDATED_PAY_TO_PROVIDER_COMPANY_NAME);
        assertThat(testBranchInsuranceMap.getPayToProviderTaxIdEin()).isEqualTo(UPDATED_PAY_TO_PROVIDER_TAX_ID_EIN);
        assertThat(testBranchInsuranceMap.getPayToProviderAddressLine1()).isEqualTo(UPDATED_PAY_TO_PROVIDER_ADDRESS_LINE_1);
        assertThat(testBranchInsuranceMap.getPayToProviderAddressLine2()).isEqualTo(UPDATED_PAY_TO_PROVIDER_ADDRESS_LINE_2);
        assertThat(testBranchInsuranceMap.getPayToProviderCity()).isEqualTo(UPDATED_PAY_TO_PROVIDER_CITY);
        assertThat(testBranchInsuranceMap.getPayToProviderState()).isEqualTo(UPDATED_PAY_TO_PROVIDER_STATE);
        assertThat(testBranchInsuranceMap.getPayToProviderZip()).isEqualTo(UPDATED_PAY_TO_PROVIDER_ZIP);
        assertThat(testBranchInsuranceMap.getPayToProviderContact1()).isEqualTo(UPDATED_PAY_TO_PROVIDER_CONTACT_1);
        assertThat(testBranchInsuranceMap.getPayToProviderContact2()).isEqualTo(UPDATED_PAY_TO_PROVIDER_CONTACT_2);
        assertThat(testBranchInsuranceMap.getPayToProviderFax()).isEqualTo(UPDATED_PAY_TO_PROVIDER_FAX);
        assertThat(testBranchInsuranceMap.getPayToProviderEmail()).isEqualTo(UPDATED_PAY_TO_PROVIDER_EMAIL);
        assertThat(testBranchInsuranceMap.getSubmitterName()).isEqualTo(UPDATED_SUBMITTER_NAME);
        assertThat(testBranchInsuranceMap.getSubmitterContact1()).isEqualTo(UPDATED_SUBMITTER_CONTACT_1);
        assertThat(testBranchInsuranceMap.getSubmitterContact2()).isEqualTo(UPDATED_SUBMITTER_CONTACT_2);
        assertThat(testBranchInsuranceMap.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testBranchInsuranceMap.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testBranchInsuranceMap.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testBranchInsuranceMap.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBranchInsuranceMap.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testBranchInsuranceMap.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testBranchInsuranceMap.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testBranchInsuranceMap.getBranchInsuranceMapUuid()).isEqualTo(UPDATED_BRANCH_INSURANCE_MAP_UUID);
    }

    @Test
    @Transactional
    void putNonExistingBranchInsuranceMap() throws Exception {
        int databaseSizeBeforeUpdate = branchInsuranceMapRepository.findAll().size();
        branchInsuranceMap.setBranchInsuranceMapId(count.incrementAndGet());

        // Create the BranchInsuranceMap
        BranchInsuranceMapDTO branchInsuranceMapDTO = branchInsuranceMapMapper.toDto(branchInsuranceMap);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBranchInsuranceMapMockMvc
            .perform(
                put(ENTITY_API_URL_ID, branchInsuranceMapDTO.getBranchInsuranceMapId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchInsuranceMapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchInsuranceMap in the database
        List<BranchInsuranceMap> branchInsuranceMapList = branchInsuranceMapRepository.findAll();
        assertThat(branchInsuranceMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBranchInsuranceMap() throws Exception {
        int databaseSizeBeforeUpdate = branchInsuranceMapRepository.findAll().size();
        branchInsuranceMap.setBranchInsuranceMapId(count.incrementAndGet());

        // Create the BranchInsuranceMap
        BranchInsuranceMapDTO branchInsuranceMapDTO = branchInsuranceMapMapper.toDto(branchInsuranceMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchInsuranceMapMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchInsuranceMapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchInsuranceMap in the database
        List<BranchInsuranceMap> branchInsuranceMapList = branchInsuranceMapRepository.findAll();
        assertThat(branchInsuranceMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBranchInsuranceMap() throws Exception {
        int databaseSizeBeforeUpdate = branchInsuranceMapRepository.findAll().size();
        branchInsuranceMap.setBranchInsuranceMapId(count.incrementAndGet());

        // Create the BranchInsuranceMap
        BranchInsuranceMapDTO branchInsuranceMapDTO = branchInsuranceMapMapper.toDto(branchInsuranceMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchInsuranceMapMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchInsuranceMapDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BranchInsuranceMap in the database
        List<BranchInsuranceMap> branchInsuranceMapList = branchInsuranceMapRepository.findAll();
        assertThat(branchInsuranceMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBranchInsuranceMapWithPatch() throws Exception {
        // Initialize the database
        branchInsuranceMapRepository.saveAndFlush(branchInsuranceMap);

        int databaseSizeBeforeUpdate = branchInsuranceMapRepository.findAll().size();

        // Update the branchInsuranceMap using partial update
        BranchInsuranceMap partialUpdatedBranchInsuranceMap = new BranchInsuranceMap();
        partialUpdatedBranchInsuranceMap.setBranchInsuranceMapId(branchInsuranceMap.getBranchInsuranceMapId());

        partialUpdatedBranchInsuranceMap
            .branchId(UPDATED_BRANCH_ID)
            .insuranceId(UPDATED_INSURANCE_ID)
            .insuranceStateName(UPDATED_INSURANCE_STATE_NAME)
            .priceTableName(UPDATED_PRICE_TABLE_NAME)
            .billingProviderOverrideTaxIdEin(UPDATED_BILLING_PROVIDER_OVERRIDE_TAX_ID_EIN)
            .billingProviderOverrideAddressLine2(UPDATED_BILLING_PROVIDER_OVERRIDE_ADDRESS_LINE_2)
            .billingProviderOverrideContact2(UPDATED_BILLING_PROVIDER_OVERRIDE_CONTACT_2)
            .billingProviderOverrideEmail(UPDATED_BILLING_PROVIDER_OVERRIDE_EMAIL)
            .payToProviderCompanyName(UPDATED_PAY_TO_PROVIDER_COMPANY_NAME)
            .payToProviderTaxIdEin(UPDATED_PAY_TO_PROVIDER_TAX_ID_EIN)
            .payToProviderAddressLine2(UPDATED_PAY_TO_PROVIDER_ADDRESS_LINE_2)
            .payToProviderCity(UPDATED_PAY_TO_PROVIDER_CITY)
            .payToProviderContact1(UPDATED_PAY_TO_PROVIDER_CONTACT_1)
            .payToProviderEmail(UPDATED_PAY_TO_PROVIDER_EMAIL)
            .submitterName(UPDATED_SUBMITTER_NAME)
            .submitterContact1(UPDATED_SUBMITTER_CONTACT_1)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME);

        restBranchInsuranceMapMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBranchInsuranceMap.getBranchInsuranceMapId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBranchInsuranceMap))
            )
            .andExpect(status().isOk());

        // Validate the BranchInsuranceMap in the database
        List<BranchInsuranceMap> branchInsuranceMapList = branchInsuranceMapRepository.findAll();
        assertThat(branchInsuranceMapList).hasSize(databaseSizeBeforeUpdate);
        BranchInsuranceMap testBranchInsuranceMap = branchInsuranceMapList.get(branchInsuranceMapList.size() - 1);
        assertThat(testBranchInsuranceMap.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
        assertThat(testBranchInsuranceMap.getBranchName()).isEqualTo(DEFAULT_BRANCH_NAME);
        assertThat(testBranchInsuranceMap.getInsuranceId()).isEqualTo(UPDATED_INSURANCE_ID);
        assertThat(testBranchInsuranceMap.getInsuranceName()).isEqualTo(DEFAULT_INSURANCE_NAME);
        assertThat(testBranchInsuranceMap.getInsuranceIdNo()).isEqualTo(DEFAULT_INSURANCE_ID_NO);
        assertThat(testBranchInsuranceMap.getInsuranceStateName()).isEqualTo(UPDATED_INSURANCE_STATE_NAME);
        assertThat(testBranchInsuranceMap.getPriceTableId()).isEqualTo(DEFAULT_PRICE_TABLE_ID);
        assertThat(testBranchInsuranceMap.getPriceTableName()).isEqualTo(UPDATED_PRICE_TABLE_NAME);
        assertThat(testBranchInsuranceMap.getBranchNpi()).isEqualTo(DEFAULT_BRANCH_NPI);
        assertThat(testBranchInsuranceMap.getBranchPtan()).isEqualTo(DEFAULT_BRANCH_PTAN);
        assertThat(testBranchInsuranceMap.getEsubmitterPayorId()).isEqualTo(DEFAULT_ESUBMITTER_PAYOR_ID);
        assertThat(testBranchInsuranceMap.getBranchTaxonomyCode()).isEqualTo(DEFAULT_BRANCH_TAXONOMY_CODE);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideCompanyName())
            .isEqualTo(DEFAULT_BILLING_PROVIDER_OVERRIDE_COMPANY_NAME);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideTaxIdEin()).isEqualTo(UPDATED_BILLING_PROVIDER_OVERRIDE_TAX_ID_EIN);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideAddressLine1())
            .isEqualTo(DEFAULT_BILLING_PROVIDER_OVERRIDE_ADDRESS_LINE_1);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideAddressLine2())
            .isEqualTo(UPDATED_BILLING_PROVIDER_OVERRIDE_ADDRESS_LINE_2);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideCity()).isEqualTo(DEFAULT_BILLING_PROVIDER_OVERRIDE_CITY);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideState()).isEqualTo(DEFAULT_BILLING_PROVIDER_OVERRIDE_STATE);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideZip()).isEqualTo(DEFAULT_BILLING_PROVIDER_OVERRIDE_ZIP);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideContact1()).isEqualTo(DEFAULT_BILLING_PROVIDER_OVERRIDE_CONTACT_1);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideContact2()).isEqualTo(UPDATED_BILLING_PROVIDER_OVERRIDE_CONTACT_2);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideFax()).isEqualTo(DEFAULT_BILLING_PROVIDER_OVERRIDE_FAX);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideEmail()).isEqualTo(UPDATED_BILLING_PROVIDER_OVERRIDE_EMAIL);
        assertThat(testBranchInsuranceMap.getPayToProviderCompanyName()).isEqualTo(UPDATED_PAY_TO_PROVIDER_COMPANY_NAME);
        assertThat(testBranchInsuranceMap.getPayToProviderTaxIdEin()).isEqualTo(UPDATED_PAY_TO_PROVIDER_TAX_ID_EIN);
        assertThat(testBranchInsuranceMap.getPayToProviderAddressLine1()).isEqualTo(DEFAULT_PAY_TO_PROVIDER_ADDRESS_LINE_1);
        assertThat(testBranchInsuranceMap.getPayToProviderAddressLine2()).isEqualTo(UPDATED_PAY_TO_PROVIDER_ADDRESS_LINE_2);
        assertThat(testBranchInsuranceMap.getPayToProviderCity()).isEqualTo(UPDATED_PAY_TO_PROVIDER_CITY);
        assertThat(testBranchInsuranceMap.getPayToProviderState()).isEqualTo(DEFAULT_PAY_TO_PROVIDER_STATE);
        assertThat(testBranchInsuranceMap.getPayToProviderZip()).isEqualTo(DEFAULT_PAY_TO_PROVIDER_ZIP);
        assertThat(testBranchInsuranceMap.getPayToProviderContact1()).isEqualTo(UPDATED_PAY_TO_PROVIDER_CONTACT_1);
        assertThat(testBranchInsuranceMap.getPayToProviderContact2()).isEqualTo(DEFAULT_PAY_TO_PROVIDER_CONTACT_2);
        assertThat(testBranchInsuranceMap.getPayToProviderFax()).isEqualTo(DEFAULT_PAY_TO_PROVIDER_FAX);
        assertThat(testBranchInsuranceMap.getPayToProviderEmail()).isEqualTo(UPDATED_PAY_TO_PROVIDER_EMAIL);
        assertThat(testBranchInsuranceMap.getSubmitterName()).isEqualTo(UPDATED_SUBMITTER_NAME);
        assertThat(testBranchInsuranceMap.getSubmitterContact1()).isEqualTo(UPDATED_SUBMITTER_CONTACT_1);
        assertThat(testBranchInsuranceMap.getSubmitterContact2()).isEqualTo(DEFAULT_SUBMITTER_CONTACT_2);
        assertThat(testBranchInsuranceMap.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testBranchInsuranceMap.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testBranchInsuranceMap.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testBranchInsuranceMap.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testBranchInsuranceMap.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testBranchInsuranceMap.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testBranchInsuranceMap.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testBranchInsuranceMap.getBranchInsuranceMapUuid()).isEqualTo(DEFAULT_BRANCH_INSURANCE_MAP_UUID);
    }

    @Test
    @Transactional
    void fullUpdateBranchInsuranceMapWithPatch() throws Exception {
        // Initialize the database
        branchInsuranceMapRepository.saveAndFlush(branchInsuranceMap);

        int databaseSizeBeforeUpdate = branchInsuranceMapRepository.findAll().size();

        // Update the branchInsuranceMap using partial update
        BranchInsuranceMap partialUpdatedBranchInsuranceMap = new BranchInsuranceMap();
        partialUpdatedBranchInsuranceMap.setBranchInsuranceMapId(branchInsuranceMap.getBranchInsuranceMapId());

        partialUpdatedBranchInsuranceMap
            .branchId(UPDATED_BRANCH_ID)
            .branchName(UPDATED_BRANCH_NAME)
            .insuranceId(UPDATED_INSURANCE_ID)
            .insuranceName(UPDATED_INSURANCE_NAME)
            .insuranceIdNo(UPDATED_INSURANCE_ID_NO)
            .insuranceStateName(UPDATED_INSURANCE_STATE_NAME)
            .priceTableId(UPDATED_PRICE_TABLE_ID)
            .priceTableName(UPDATED_PRICE_TABLE_NAME)
            .branchNpi(UPDATED_BRANCH_NPI)
            .branchPtan(UPDATED_BRANCH_PTAN)
            .esubmitterPayorId(UPDATED_ESUBMITTER_PAYOR_ID)
            .branchTaxonomyCode(UPDATED_BRANCH_TAXONOMY_CODE)
            .billingProviderOverrideCompanyName(UPDATED_BILLING_PROVIDER_OVERRIDE_COMPANY_NAME)
            .billingProviderOverrideTaxIdEin(UPDATED_BILLING_PROVIDER_OVERRIDE_TAX_ID_EIN)
            .billingProviderOverrideAddressLine1(UPDATED_BILLING_PROVIDER_OVERRIDE_ADDRESS_LINE_1)
            .billingProviderOverrideAddressLine2(UPDATED_BILLING_PROVIDER_OVERRIDE_ADDRESS_LINE_2)
            .billingProviderOverrideCity(UPDATED_BILLING_PROVIDER_OVERRIDE_CITY)
            .billingProviderOverrideState(UPDATED_BILLING_PROVIDER_OVERRIDE_STATE)
            .billingProviderOverrideZip(UPDATED_BILLING_PROVIDER_OVERRIDE_ZIP)
            .billingProviderOverrideContact1(UPDATED_BILLING_PROVIDER_OVERRIDE_CONTACT_1)
            .billingProviderOverrideContact2(UPDATED_BILLING_PROVIDER_OVERRIDE_CONTACT_2)
            .billingProviderOverrideFax(UPDATED_BILLING_PROVIDER_OVERRIDE_FAX)
            .billingProviderOverrideEmail(UPDATED_BILLING_PROVIDER_OVERRIDE_EMAIL)
            .payToProviderCompanyName(UPDATED_PAY_TO_PROVIDER_COMPANY_NAME)
            .payToProviderTaxIdEin(UPDATED_PAY_TO_PROVIDER_TAX_ID_EIN)
            .payToProviderAddressLine1(UPDATED_PAY_TO_PROVIDER_ADDRESS_LINE_1)
            .payToProviderAddressLine2(UPDATED_PAY_TO_PROVIDER_ADDRESS_LINE_2)
            .payToProviderCity(UPDATED_PAY_TO_PROVIDER_CITY)
            .payToProviderState(UPDATED_PAY_TO_PROVIDER_STATE)
            .payToProviderZip(UPDATED_PAY_TO_PROVIDER_ZIP)
            .payToProviderContact1(UPDATED_PAY_TO_PROVIDER_CONTACT_1)
            .payToProviderContact2(UPDATED_PAY_TO_PROVIDER_CONTACT_2)
            .payToProviderFax(UPDATED_PAY_TO_PROVIDER_FAX)
            .payToProviderEmail(UPDATED_PAY_TO_PROVIDER_EMAIL)
            .submitterName(UPDATED_SUBMITTER_NAME)
            .submitterContact1(UPDATED_SUBMITTER_CONTACT_1)
            .submitterContact2(UPDATED_SUBMITTER_CONTACT_2)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .branchInsuranceMapUuid(UPDATED_BRANCH_INSURANCE_MAP_UUID);

        restBranchInsuranceMapMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBranchInsuranceMap.getBranchInsuranceMapId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBranchInsuranceMap))
            )
            .andExpect(status().isOk());

        // Validate the BranchInsuranceMap in the database
        List<BranchInsuranceMap> branchInsuranceMapList = branchInsuranceMapRepository.findAll();
        assertThat(branchInsuranceMapList).hasSize(databaseSizeBeforeUpdate);
        BranchInsuranceMap testBranchInsuranceMap = branchInsuranceMapList.get(branchInsuranceMapList.size() - 1);
        assertThat(testBranchInsuranceMap.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
        assertThat(testBranchInsuranceMap.getBranchName()).isEqualTo(UPDATED_BRANCH_NAME);
        assertThat(testBranchInsuranceMap.getInsuranceId()).isEqualTo(UPDATED_INSURANCE_ID);
        assertThat(testBranchInsuranceMap.getInsuranceName()).isEqualTo(UPDATED_INSURANCE_NAME);
        assertThat(testBranchInsuranceMap.getInsuranceIdNo()).isEqualTo(UPDATED_INSURANCE_ID_NO);
        assertThat(testBranchInsuranceMap.getInsuranceStateName()).isEqualTo(UPDATED_INSURANCE_STATE_NAME);
        assertThat(testBranchInsuranceMap.getPriceTableId()).isEqualTo(UPDATED_PRICE_TABLE_ID);
        assertThat(testBranchInsuranceMap.getPriceTableName()).isEqualTo(UPDATED_PRICE_TABLE_NAME);
        assertThat(testBranchInsuranceMap.getBranchNpi()).isEqualTo(UPDATED_BRANCH_NPI);
        assertThat(testBranchInsuranceMap.getBranchPtan()).isEqualTo(UPDATED_BRANCH_PTAN);
        assertThat(testBranchInsuranceMap.getEsubmitterPayorId()).isEqualTo(UPDATED_ESUBMITTER_PAYOR_ID);
        assertThat(testBranchInsuranceMap.getBranchTaxonomyCode()).isEqualTo(UPDATED_BRANCH_TAXONOMY_CODE);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideCompanyName())
            .isEqualTo(UPDATED_BILLING_PROVIDER_OVERRIDE_COMPANY_NAME);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideTaxIdEin()).isEqualTo(UPDATED_BILLING_PROVIDER_OVERRIDE_TAX_ID_EIN);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideAddressLine1())
            .isEqualTo(UPDATED_BILLING_PROVIDER_OVERRIDE_ADDRESS_LINE_1);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideAddressLine2())
            .isEqualTo(UPDATED_BILLING_PROVIDER_OVERRIDE_ADDRESS_LINE_2);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideCity()).isEqualTo(UPDATED_BILLING_PROVIDER_OVERRIDE_CITY);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideState()).isEqualTo(UPDATED_BILLING_PROVIDER_OVERRIDE_STATE);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideZip()).isEqualTo(UPDATED_BILLING_PROVIDER_OVERRIDE_ZIP);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideContact1()).isEqualTo(UPDATED_BILLING_PROVIDER_OVERRIDE_CONTACT_1);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideContact2()).isEqualTo(UPDATED_BILLING_PROVIDER_OVERRIDE_CONTACT_2);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideFax()).isEqualTo(UPDATED_BILLING_PROVIDER_OVERRIDE_FAX);
        assertThat(testBranchInsuranceMap.getBillingProviderOverrideEmail()).isEqualTo(UPDATED_BILLING_PROVIDER_OVERRIDE_EMAIL);
        assertThat(testBranchInsuranceMap.getPayToProviderCompanyName()).isEqualTo(UPDATED_PAY_TO_PROVIDER_COMPANY_NAME);
        assertThat(testBranchInsuranceMap.getPayToProviderTaxIdEin()).isEqualTo(UPDATED_PAY_TO_PROVIDER_TAX_ID_EIN);
        assertThat(testBranchInsuranceMap.getPayToProviderAddressLine1()).isEqualTo(UPDATED_PAY_TO_PROVIDER_ADDRESS_LINE_1);
        assertThat(testBranchInsuranceMap.getPayToProviderAddressLine2()).isEqualTo(UPDATED_PAY_TO_PROVIDER_ADDRESS_LINE_2);
        assertThat(testBranchInsuranceMap.getPayToProviderCity()).isEqualTo(UPDATED_PAY_TO_PROVIDER_CITY);
        assertThat(testBranchInsuranceMap.getPayToProviderState()).isEqualTo(UPDATED_PAY_TO_PROVIDER_STATE);
        assertThat(testBranchInsuranceMap.getPayToProviderZip()).isEqualTo(UPDATED_PAY_TO_PROVIDER_ZIP);
        assertThat(testBranchInsuranceMap.getPayToProviderContact1()).isEqualTo(UPDATED_PAY_TO_PROVIDER_CONTACT_1);
        assertThat(testBranchInsuranceMap.getPayToProviderContact2()).isEqualTo(UPDATED_PAY_TO_PROVIDER_CONTACT_2);
        assertThat(testBranchInsuranceMap.getPayToProviderFax()).isEqualTo(UPDATED_PAY_TO_PROVIDER_FAX);
        assertThat(testBranchInsuranceMap.getPayToProviderEmail()).isEqualTo(UPDATED_PAY_TO_PROVIDER_EMAIL);
        assertThat(testBranchInsuranceMap.getSubmitterName()).isEqualTo(UPDATED_SUBMITTER_NAME);
        assertThat(testBranchInsuranceMap.getSubmitterContact1()).isEqualTo(UPDATED_SUBMITTER_CONTACT_1);
        assertThat(testBranchInsuranceMap.getSubmitterContact2()).isEqualTo(UPDATED_SUBMITTER_CONTACT_2);
        assertThat(testBranchInsuranceMap.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testBranchInsuranceMap.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testBranchInsuranceMap.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testBranchInsuranceMap.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBranchInsuranceMap.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testBranchInsuranceMap.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testBranchInsuranceMap.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testBranchInsuranceMap.getBranchInsuranceMapUuid()).isEqualTo(UPDATED_BRANCH_INSURANCE_MAP_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingBranchInsuranceMap() throws Exception {
        int databaseSizeBeforeUpdate = branchInsuranceMapRepository.findAll().size();
        branchInsuranceMap.setBranchInsuranceMapId(count.incrementAndGet());

        // Create the BranchInsuranceMap
        BranchInsuranceMapDTO branchInsuranceMapDTO = branchInsuranceMapMapper.toDto(branchInsuranceMap);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBranchInsuranceMapMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, branchInsuranceMapDTO.getBranchInsuranceMapId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(branchInsuranceMapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchInsuranceMap in the database
        List<BranchInsuranceMap> branchInsuranceMapList = branchInsuranceMapRepository.findAll();
        assertThat(branchInsuranceMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBranchInsuranceMap() throws Exception {
        int databaseSizeBeforeUpdate = branchInsuranceMapRepository.findAll().size();
        branchInsuranceMap.setBranchInsuranceMapId(count.incrementAndGet());

        // Create the BranchInsuranceMap
        BranchInsuranceMapDTO branchInsuranceMapDTO = branchInsuranceMapMapper.toDto(branchInsuranceMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchInsuranceMapMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(branchInsuranceMapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchInsuranceMap in the database
        List<BranchInsuranceMap> branchInsuranceMapList = branchInsuranceMapRepository.findAll();
        assertThat(branchInsuranceMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBranchInsuranceMap() throws Exception {
        int databaseSizeBeforeUpdate = branchInsuranceMapRepository.findAll().size();
        branchInsuranceMap.setBranchInsuranceMapId(count.incrementAndGet());

        // Create the BranchInsuranceMap
        BranchInsuranceMapDTO branchInsuranceMapDTO = branchInsuranceMapMapper.toDto(branchInsuranceMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchInsuranceMapMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(branchInsuranceMapDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BranchInsuranceMap in the database
        List<BranchInsuranceMap> branchInsuranceMapList = branchInsuranceMapRepository.findAll();
        assertThat(branchInsuranceMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBranchInsuranceMap() throws Exception {
        // Initialize the database
        branchInsuranceMapRepository.saveAndFlush(branchInsuranceMap);

        int databaseSizeBeforeDelete = branchInsuranceMapRepository.findAll().size();

        // Delete the branchInsuranceMap
        restBranchInsuranceMapMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, branchInsuranceMap.getBranchInsuranceMapId()).with(csrf()).accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BranchInsuranceMap> branchInsuranceMapList = branchInsuranceMapRepository.findAll();
        assertThat(branchInsuranceMapList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
