package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.PriceDetails;
import com.sunknowledge.dme.rcm.repository.PriceDetailsRepository;
import com.sunknowledge.dme.rcm.service.dto.PriceDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.PriceDetailsMapper;
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
 * Integration tests for the {@link PriceDetailsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PriceDetailsResourceIT {

    private static final Long DEFAULT_PRICE_TABLE_ID = 1L;
    private static final Long UPDATED_PRICE_TABLE_ID = 2L;

    private static final Long DEFAULT_ITEM_ID = 1L;
    private static final Long UPDATED_ITEM_ID = 2L;

    private static final String DEFAULT_HCPCS = "AAAAAAAAAA";
    private static final String UPDATED_HCPCS = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_CODE_WHEN_SECONDARY = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_CODE_WHEN_SECONDARY = "BBBBBBBBBB";

    private static final String DEFAULT_PRICE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_PRICE_TYPE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_EFFECTIVE_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_EFFECTIVE_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_EFFECTIVE_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_EFFECTIVE_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CMN_REQD_TO_BILL_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_CMN_REQD_TO_BILL_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_CMN_FORM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CMN_FORM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PRIOR_AUTH_REQ_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_PRIOR_AUTH_REQ_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_FUNCTIONAL_ABILITY_REQ_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_FUNCTIONAL_ABILITY_REQ_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_OPTION_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_OPTION_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_DEFAULT_OPTION_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_DEFAULT_OPTION_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_CYCLE_PERIOD = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_CYCLE_PERIOD = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_CYCLE_INTERVAL = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_CYCLE_INTERVAL = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_IN_ARREARS_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_IN_ARREARS_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_PRO_RATE_BILLING_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_PRO_RATE_BILLING_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_DAILY_BILLING_INVOICE_FREQ = "AAAAAAAAAA";
    private static final String UPDATED_DAILY_BILLING_INVOICE_FREQ = "BBBBBBBBBB";

    private static final String DEFAULT_DAILY_BILLING_INVOICE_INTERVAL = "AAAAAAAAAA";
    private static final String UPDATED_DAILY_BILLING_INVOICE_INTERVAL = "BBBBBBBBBB";

    private static final Double DEFAULT_CHARGE_AMT = 1D;
    private static final Double UPDATED_CHARGE_AMT = 2D;

    private static final Double DEFAULT_ALLOWED_AMT = 1D;
    private static final Double UPDATED_ALLOWED_AMT = 2D;

    private static final String DEFAULT_ALLOWED_MODIFIER_1 = "AAAAAAAAAA";
    private static final String UPDATED_ALLOWED_MODIFIER_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ALLOWED_MODIFIER_2 = "AAAAAAAAAA";
    private static final String UPDATED_ALLOWED_MODIFIER_2 = "BBBBBBBBBB";

    private static final String DEFAULT_ALLOWED_MODIFIER_3 = "AAAAAAAAAA";
    private static final String UPDATED_ALLOWED_MODIFIER_3 = "BBBBBBBBBB";

    private static final String DEFAULT_ALLOWED_MODIFIER_4 = "AAAAAAAAAA";
    private static final String UPDATED_ALLOWED_MODIFIER_4 = "BBBBBBBBBB";

    private static final String DEFAULT_ACCEPT_ASSIGNMENT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_ACCEPT_ASSIGNMENT_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_TAXABLE_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_TAXABLE_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_NONTAX_TYPE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NONTAX_TYPE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CONVERT_TO_PURCHASE_LAST_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_CONVERT_TO_PURCHASE_LAST_STATUS = "BBBBBBBBBB";

    private static final Double DEFAULT_CONVERT_TO_PURCHASE_CHARGE_AMT = 1D;
    private static final Double UPDATED_CONVERT_TO_PURCHASE_CHARGE_AMT = 2D;

    private static final Double DEFAULT_CONVERT_TO_PURCHASE_ALLOW_AMT = 1D;
    private static final Double UPDATED_CONVERT_TO_PURCHASE_ALLOW_AMT = 2D;

    private static final String DEFAULT_CONVERT_TO_PURCHASE_MODIFIER_1 = "AAAAAAAAAA";
    private static final String UPDATED_CONVERT_TO_PURCHASE_MODIFIER_1 = "BBBBBBBBBB";

    private static final String DEFAULT_CONVERT_TO_PURCHASE_MODIFIER_2 = "AAAAAAAAAA";
    private static final String UPDATED_CONVERT_TO_PURCHASE_MODIFIER_2 = "BBBBBBBBBB";

    private static final String DEFAULT_CONVERT_TO_PURCHASE_MODIFIER_3 = "AAAAAAAAAA";
    private static final String UPDATED_CONVERT_TO_PURCHASE_MODIFIER_3 = "BBBBBBBBBB";

    private static final String DEFAULT_CONVERT_TO_PURCHASE_MODIFIER_4 = "AAAAAAAAAA";
    private static final String UPDATED_CONVERT_TO_PURCHASE_MODIFIER_4 = "BBBBBBBBBB";

    private static final Long DEFAULT_BILLING_MULTIPLIER_UNIT = 1L;
    private static final Long UPDATED_BILLING_MULTIPLIER_UNIT = 2L;

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

    private static final UUID DEFAULT_PRICE_DETAILS_UUID = UUID.randomUUID();
    private static final UUID UPDATED_PRICE_DETAILS_UUID = UUID.randomUUID();

    private static final String DEFAULT_PRICE_TABLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PRICE_TABLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_NO = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NO = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_UOM = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_UOM = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_PRICE_OPTION_BILLING_PERIOD_START = 1L;
    private static final Long UPDATED_PRICE_OPTION_BILLING_PERIOD_START = 2L;

    private static final Long DEFAULT_PRICE_OPTION_BILLING_PERIOD_END = 1L;
    private static final Long UPDATED_PRICE_OPTION_BILLING_PERIOD_END = 2L;

    private static final String ENTITY_API_URL = "/api/price-details";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{priceDetailsId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PriceDetailsRepository priceDetailsRepository;

    @Autowired
    private PriceDetailsMapper priceDetailsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPriceDetailsMockMvc;

    private PriceDetails priceDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PriceDetails createEntity(EntityManager em) {
        PriceDetails priceDetails = new PriceDetails()
            .priceTableId(DEFAULT_PRICE_TABLE_ID)
            .itemId(DEFAULT_ITEM_ID)
            .hcpcs(DEFAULT_HCPCS)
            .billingCodeWhenSecondary(DEFAULT_BILLING_CODE_WHEN_SECONDARY)
            .priceType(DEFAULT_PRICE_TYPE)
            .effectiveStartDate(DEFAULT_EFFECTIVE_START_DATE)
            .effectiveEndDate(DEFAULT_EFFECTIVE_END_DATE)
            .cmnReqdToBillStatus(DEFAULT_CMN_REQD_TO_BILL_STATUS)
            .cmnFormName(DEFAULT_CMN_FORM_NAME)
            .priorAuthReqStatus(DEFAULT_PRIOR_AUTH_REQ_STATUS)
            .functionalAbilityReqStatus(DEFAULT_FUNCTIONAL_ABILITY_REQ_STATUS)
            .optionNumber(DEFAULT_OPTION_NUMBER)
            .defaultOptionStatus(DEFAULT_DEFAULT_OPTION_STATUS)
            .billingCyclePeriod(DEFAULT_BILLING_CYCLE_PERIOD)
            .billingCycleInterval(DEFAULT_BILLING_CYCLE_INTERVAL)
            .billingInArrearsStatus(DEFAULT_BILLING_IN_ARREARS_STATUS)
            .proRateBillingStatus(DEFAULT_PRO_RATE_BILLING_STATUS)
            .dailyBillingInvoiceFreq(DEFAULT_DAILY_BILLING_INVOICE_FREQ)
            .dailyBillingInvoiceInterval(DEFAULT_DAILY_BILLING_INVOICE_INTERVAL)
            .chargeAmt(DEFAULT_CHARGE_AMT)
            .allowedAmt(DEFAULT_ALLOWED_AMT)
            .allowedModifier1(DEFAULT_ALLOWED_MODIFIER_1)
            .allowedModifier2(DEFAULT_ALLOWED_MODIFIER_2)
            .allowedModifier3(DEFAULT_ALLOWED_MODIFIER_3)
            .allowedModifier4(DEFAULT_ALLOWED_MODIFIER_4)
            .acceptAssignmentStatus(DEFAULT_ACCEPT_ASSIGNMENT_STATUS)
            .taxableStatus(DEFAULT_TAXABLE_STATUS)
            .nontaxTypeName(DEFAULT_NONTAX_TYPE_NAME)
            .convertToPurchaseLastStatus(DEFAULT_CONVERT_TO_PURCHASE_LAST_STATUS)
            .convertToPurchaseChargeAmt(DEFAULT_CONVERT_TO_PURCHASE_CHARGE_AMT)
            .convertToPurchaseAllowAmt(DEFAULT_CONVERT_TO_PURCHASE_ALLOW_AMT)
            .convertToPurchaseModifier1(DEFAULT_CONVERT_TO_PURCHASE_MODIFIER_1)
            .convertToPurchaseModifier2(DEFAULT_CONVERT_TO_PURCHASE_MODIFIER_2)
            .convertToPurchaseModifier3(DEFAULT_CONVERT_TO_PURCHASE_MODIFIER_3)
            .convertToPurchaseModifier4(DEFAULT_CONVERT_TO_PURCHASE_MODIFIER_4)
            .billingMultiplierUnit(DEFAULT_BILLING_MULTIPLIER_UNIT)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .priceDetailsUuid(DEFAULT_PRICE_DETAILS_UUID)
            .priceTableName(DEFAULT_PRICE_TABLE_NAME)
            .itemNo(DEFAULT_ITEM_NO)
            .itemName(DEFAULT_ITEM_NAME)
            .itemUom(DEFAULT_ITEM_UOM)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .priceOptionBillingPeriodStart(DEFAULT_PRICE_OPTION_BILLING_PERIOD_START)
            .priceOptionBillingPeriodEnd(DEFAULT_PRICE_OPTION_BILLING_PERIOD_END);
        return priceDetails;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PriceDetails createUpdatedEntity(EntityManager em) {
        PriceDetails priceDetails = new PriceDetails()
            .priceTableId(UPDATED_PRICE_TABLE_ID)
            .itemId(UPDATED_ITEM_ID)
            .hcpcs(UPDATED_HCPCS)
            .billingCodeWhenSecondary(UPDATED_BILLING_CODE_WHEN_SECONDARY)
            .priceType(UPDATED_PRICE_TYPE)
            .effectiveStartDate(UPDATED_EFFECTIVE_START_DATE)
            .effectiveEndDate(UPDATED_EFFECTIVE_END_DATE)
            .cmnReqdToBillStatus(UPDATED_CMN_REQD_TO_BILL_STATUS)
            .cmnFormName(UPDATED_CMN_FORM_NAME)
            .priorAuthReqStatus(UPDATED_PRIOR_AUTH_REQ_STATUS)
            .functionalAbilityReqStatus(UPDATED_FUNCTIONAL_ABILITY_REQ_STATUS)
            .optionNumber(UPDATED_OPTION_NUMBER)
            .defaultOptionStatus(UPDATED_DEFAULT_OPTION_STATUS)
            .billingCyclePeriod(UPDATED_BILLING_CYCLE_PERIOD)
            .billingCycleInterval(UPDATED_BILLING_CYCLE_INTERVAL)
            .billingInArrearsStatus(UPDATED_BILLING_IN_ARREARS_STATUS)
            .proRateBillingStatus(UPDATED_PRO_RATE_BILLING_STATUS)
            .dailyBillingInvoiceFreq(UPDATED_DAILY_BILLING_INVOICE_FREQ)
            .dailyBillingInvoiceInterval(UPDATED_DAILY_BILLING_INVOICE_INTERVAL)
            .chargeAmt(UPDATED_CHARGE_AMT)
            .allowedAmt(UPDATED_ALLOWED_AMT)
            .allowedModifier1(UPDATED_ALLOWED_MODIFIER_1)
            .allowedModifier2(UPDATED_ALLOWED_MODIFIER_2)
            .allowedModifier3(UPDATED_ALLOWED_MODIFIER_3)
            .allowedModifier4(UPDATED_ALLOWED_MODIFIER_4)
            .acceptAssignmentStatus(UPDATED_ACCEPT_ASSIGNMENT_STATUS)
            .taxableStatus(UPDATED_TAXABLE_STATUS)
            .nontaxTypeName(UPDATED_NONTAX_TYPE_NAME)
            .convertToPurchaseLastStatus(UPDATED_CONVERT_TO_PURCHASE_LAST_STATUS)
            .convertToPurchaseChargeAmt(UPDATED_CONVERT_TO_PURCHASE_CHARGE_AMT)
            .convertToPurchaseAllowAmt(UPDATED_CONVERT_TO_PURCHASE_ALLOW_AMT)
            .convertToPurchaseModifier1(UPDATED_CONVERT_TO_PURCHASE_MODIFIER_1)
            .convertToPurchaseModifier2(UPDATED_CONVERT_TO_PURCHASE_MODIFIER_2)
            .convertToPurchaseModifier3(UPDATED_CONVERT_TO_PURCHASE_MODIFIER_3)
            .convertToPurchaseModifier4(UPDATED_CONVERT_TO_PURCHASE_MODIFIER_4)
            .billingMultiplierUnit(UPDATED_BILLING_MULTIPLIER_UNIT)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .priceDetailsUuid(UPDATED_PRICE_DETAILS_UUID)
            .priceTableName(UPDATED_PRICE_TABLE_NAME)
            .itemNo(UPDATED_ITEM_NO)
            .itemName(UPDATED_ITEM_NAME)
            .itemUom(UPDATED_ITEM_UOM)
            .updatedDate(UPDATED_UPDATED_DATE)
            .priceOptionBillingPeriodStart(UPDATED_PRICE_OPTION_BILLING_PERIOD_START)
            .priceOptionBillingPeriodEnd(UPDATED_PRICE_OPTION_BILLING_PERIOD_END);
        return priceDetails;
    }

    @BeforeEach
    public void initTest() {
        priceDetails = createEntity(em);
    }

    @Test
    @Transactional
    void createPriceDetails() throws Exception {
        int databaseSizeBeforeCreate = priceDetailsRepository.findAll().size();
        // Create the PriceDetails
        PriceDetailsDTO priceDetailsDTO = priceDetailsMapper.toDto(priceDetails);
        restPriceDetailsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(priceDetailsDTO))
            )
            .andExpect(status().isCreated());

        // Validate the PriceDetails in the database
        List<PriceDetails> priceDetailsList = priceDetailsRepository.findAll();
        assertThat(priceDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        PriceDetails testPriceDetails = priceDetailsList.get(priceDetailsList.size() - 1);
        assertThat(testPriceDetails.getPriceTableId()).isEqualTo(DEFAULT_PRICE_TABLE_ID);
        assertThat(testPriceDetails.getItemId()).isEqualTo(DEFAULT_ITEM_ID);
        assertThat(testPriceDetails.getHcpcs()).isEqualTo(DEFAULT_HCPCS);
        assertThat(testPriceDetails.getBillingCodeWhenSecondary()).isEqualTo(DEFAULT_BILLING_CODE_WHEN_SECONDARY);
        assertThat(testPriceDetails.getPriceType()).isEqualTo(DEFAULT_PRICE_TYPE);
        assertThat(testPriceDetails.getEffectiveStartDate()).isEqualTo(DEFAULT_EFFECTIVE_START_DATE);
        assertThat(testPriceDetails.getEffectiveEndDate()).isEqualTo(DEFAULT_EFFECTIVE_END_DATE);
        assertThat(testPriceDetails.getCmnReqdToBillStatus()).isEqualTo(DEFAULT_CMN_REQD_TO_BILL_STATUS);
        assertThat(testPriceDetails.getCmnFormName()).isEqualTo(DEFAULT_CMN_FORM_NAME);
        assertThat(testPriceDetails.getPriorAuthReqStatus()).isEqualTo(DEFAULT_PRIOR_AUTH_REQ_STATUS);
        assertThat(testPriceDetails.getFunctionalAbilityReqStatus()).isEqualTo(DEFAULT_FUNCTIONAL_ABILITY_REQ_STATUS);
        assertThat(testPriceDetails.getOptionNumber()).isEqualTo(DEFAULT_OPTION_NUMBER);
        assertThat(testPriceDetails.getDefaultOptionStatus()).isEqualTo(DEFAULT_DEFAULT_OPTION_STATUS);
        assertThat(testPriceDetails.getBillingCyclePeriod()).isEqualTo(DEFAULT_BILLING_CYCLE_PERIOD);
        assertThat(testPriceDetails.getBillingCycleInterval()).isEqualTo(DEFAULT_BILLING_CYCLE_INTERVAL);
        assertThat(testPriceDetails.getBillingInArrearsStatus()).isEqualTo(DEFAULT_BILLING_IN_ARREARS_STATUS);
        assertThat(testPriceDetails.getProRateBillingStatus()).isEqualTo(DEFAULT_PRO_RATE_BILLING_STATUS);
        assertThat(testPriceDetails.getDailyBillingInvoiceFreq()).isEqualTo(DEFAULT_DAILY_BILLING_INVOICE_FREQ);
        assertThat(testPriceDetails.getDailyBillingInvoiceInterval()).isEqualTo(DEFAULT_DAILY_BILLING_INVOICE_INTERVAL);
        assertThat(testPriceDetails.getChargeAmt()).isEqualTo(DEFAULT_CHARGE_AMT);
        assertThat(testPriceDetails.getAllowedAmt()).isEqualTo(DEFAULT_ALLOWED_AMT);
        assertThat(testPriceDetails.getAllowedModifier1()).isEqualTo(DEFAULT_ALLOWED_MODIFIER_1);
        assertThat(testPriceDetails.getAllowedModifier2()).isEqualTo(DEFAULT_ALLOWED_MODIFIER_2);
        assertThat(testPriceDetails.getAllowedModifier3()).isEqualTo(DEFAULT_ALLOWED_MODIFIER_3);
        assertThat(testPriceDetails.getAllowedModifier4()).isEqualTo(DEFAULT_ALLOWED_MODIFIER_4);
        assertThat(testPriceDetails.getAcceptAssignmentStatus()).isEqualTo(DEFAULT_ACCEPT_ASSIGNMENT_STATUS);
        assertThat(testPriceDetails.getTaxableStatus()).isEqualTo(DEFAULT_TAXABLE_STATUS);
        assertThat(testPriceDetails.getNontaxTypeName()).isEqualTo(DEFAULT_NONTAX_TYPE_NAME);
        assertThat(testPriceDetails.getConvertToPurchaseLastStatus()).isEqualTo(DEFAULT_CONVERT_TO_PURCHASE_LAST_STATUS);
        assertThat(testPriceDetails.getConvertToPurchaseChargeAmt()).isEqualTo(DEFAULT_CONVERT_TO_PURCHASE_CHARGE_AMT);
        assertThat(testPriceDetails.getConvertToPurchaseAllowAmt()).isEqualTo(DEFAULT_CONVERT_TO_PURCHASE_ALLOW_AMT);
        assertThat(testPriceDetails.getConvertToPurchaseModifier1()).isEqualTo(DEFAULT_CONVERT_TO_PURCHASE_MODIFIER_1);
        assertThat(testPriceDetails.getConvertToPurchaseModifier2()).isEqualTo(DEFAULT_CONVERT_TO_PURCHASE_MODIFIER_2);
        assertThat(testPriceDetails.getConvertToPurchaseModifier3()).isEqualTo(DEFAULT_CONVERT_TO_PURCHASE_MODIFIER_3);
        assertThat(testPriceDetails.getConvertToPurchaseModifier4()).isEqualTo(DEFAULT_CONVERT_TO_PURCHASE_MODIFIER_4);
        assertThat(testPriceDetails.getBillingMultiplierUnit()).isEqualTo(DEFAULT_BILLING_MULTIPLIER_UNIT);
        assertThat(testPriceDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPriceDetails.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testPriceDetails.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testPriceDetails.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testPriceDetails.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testPriceDetails.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testPriceDetails.getPriceDetailsUuid()).isEqualTo(DEFAULT_PRICE_DETAILS_UUID);
        assertThat(testPriceDetails.getPriceTableName()).isEqualTo(DEFAULT_PRICE_TABLE_NAME);
        assertThat(testPriceDetails.getItemNo()).isEqualTo(DEFAULT_ITEM_NO);
        assertThat(testPriceDetails.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testPriceDetails.getItemUom()).isEqualTo(DEFAULT_ITEM_UOM);
        assertThat(testPriceDetails.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testPriceDetails.getPriceOptionBillingPeriodStart()).isEqualTo(DEFAULT_PRICE_OPTION_BILLING_PERIOD_START);
        assertThat(testPriceDetails.getPriceOptionBillingPeriodEnd()).isEqualTo(DEFAULT_PRICE_OPTION_BILLING_PERIOD_END);
    }

    @Test
    @Transactional
    void createPriceDetailsWithExistingId() throws Exception {
        // Create the PriceDetails with an existing ID
        priceDetails.setPriceDetailsId(1L);
        PriceDetailsDTO priceDetailsDTO = priceDetailsMapper.toDto(priceDetails);

        int databaseSizeBeforeCreate = priceDetailsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPriceDetailsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(priceDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PriceDetails in the database
        List<PriceDetails> priceDetailsList = priceDetailsRepository.findAll();
        assertThat(priceDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPriceDetails() throws Exception {
        // Initialize the database
        priceDetailsRepository.saveAndFlush(priceDetails);

        // Get all the priceDetailsList
        restPriceDetailsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=priceDetailsId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].priceDetailsId").value(hasItem(priceDetails.getPriceDetailsId().intValue())))
            .andExpect(jsonPath("$.[*].priceTableId").value(hasItem(DEFAULT_PRICE_TABLE_ID.intValue())))
            .andExpect(jsonPath("$.[*].itemId").value(hasItem(DEFAULT_ITEM_ID.intValue())))
            .andExpect(jsonPath("$.[*].hcpcs").value(hasItem(DEFAULT_HCPCS)))
            .andExpect(jsonPath("$.[*].billingCodeWhenSecondary").value(hasItem(DEFAULT_BILLING_CODE_WHEN_SECONDARY)))
            .andExpect(jsonPath("$.[*].priceType").value(hasItem(DEFAULT_PRICE_TYPE)))
            .andExpect(jsonPath("$.[*].effectiveStartDate").value(hasItem(DEFAULT_EFFECTIVE_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].effectiveEndDate").value(hasItem(DEFAULT_EFFECTIVE_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].cmnReqdToBillStatus").value(hasItem(DEFAULT_CMN_REQD_TO_BILL_STATUS)))
            .andExpect(jsonPath("$.[*].cmnFormName").value(hasItem(DEFAULT_CMN_FORM_NAME)))
            .andExpect(jsonPath("$.[*].priorAuthReqStatus").value(hasItem(DEFAULT_PRIOR_AUTH_REQ_STATUS)))
            .andExpect(jsonPath("$.[*].functionalAbilityReqStatus").value(hasItem(DEFAULT_FUNCTIONAL_ABILITY_REQ_STATUS)))
            .andExpect(jsonPath("$.[*].optionNumber").value(hasItem(DEFAULT_OPTION_NUMBER)))
            .andExpect(jsonPath("$.[*].defaultOptionStatus").value(hasItem(DEFAULT_DEFAULT_OPTION_STATUS)))
            .andExpect(jsonPath("$.[*].billingCyclePeriod").value(hasItem(DEFAULT_BILLING_CYCLE_PERIOD)))
            .andExpect(jsonPath("$.[*].billingCycleInterval").value(hasItem(DEFAULT_BILLING_CYCLE_INTERVAL)))
            .andExpect(jsonPath("$.[*].billingInArrearsStatus").value(hasItem(DEFAULT_BILLING_IN_ARREARS_STATUS)))
            .andExpect(jsonPath("$.[*].proRateBillingStatus").value(hasItem(DEFAULT_PRO_RATE_BILLING_STATUS)))
            .andExpect(jsonPath("$.[*].dailyBillingInvoiceFreq").value(hasItem(DEFAULT_DAILY_BILLING_INVOICE_FREQ)))
            .andExpect(jsonPath("$.[*].dailyBillingInvoiceInterval").value(hasItem(DEFAULT_DAILY_BILLING_INVOICE_INTERVAL)))
            .andExpect(jsonPath("$.[*].chargeAmt").value(hasItem(DEFAULT_CHARGE_AMT.doubleValue())))
            .andExpect(jsonPath("$.[*].allowedAmt").value(hasItem(DEFAULT_ALLOWED_AMT.doubleValue())))
            .andExpect(jsonPath("$.[*].allowedModifier1").value(hasItem(DEFAULT_ALLOWED_MODIFIER_1)))
            .andExpect(jsonPath("$.[*].allowedModifier2").value(hasItem(DEFAULT_ALLOWED_MODIFIER_2)))
            .andExpect(jsonPath("$.[*].allowedModifier3").value(hasItem(DEFAULT_ALLOWED_MODIFIER_3)))
            .andExpect(jsonPath("$.[*].allowedModifier4").value(hasItem(DEFAULT_ALLOWED_MODIFIER_4)))
            .andExpect(jsonPath("$.[*].acceptAssignmentStatus").value(hasItem(DEFAULT_ACCEPT_ASSIGNMENT_STATUS)))
            .andExpect(jsonPath("$.[*].taxableStatus").value(hasItem(DEFAULT_TAXABLE_STATUS)))
            .andExpect(jsonPath("$.[*].nontaxTypeName").value(hasItem(DEFAULT_NONTAX_TYPE_NAME)))
            .andExpect(jsonPath("$.[*].convertToPurchaseLastStatus").value(hasItem(DEFAULT_CONVERT_TO_PURCHASE_LAST_STATUS)))
            .andExpect(jsonPath("$.[*].convertToPurchaseChargeAmt").value(hasItem(DEFAULT_CONVERT_TO_PURCHASE_CHARGE_AMT.doubleValue())))
            .andExpect(jsonPath("$.[*].convertToPurchaseAllowAmt").value(hasItem(DEFAULT_CONVERT_TO_PURCHASE_ALLOW_AMT.doubleValue())))
            .andExpect(jsonPath("$.[*].convertToPurchaseModifier1").value(hasItem(DEFAULT_CONVERT_TO_PURCHASE_MODIFIER_1)))
            .andExpect(jsonPath("$.[*].convertToPurchaseModifier2").value(hasItem(DEFAULT_CONVERT_TO_PURCHASE_MODIFIER_2)))
            .andExpect(jsonPath("$.[*].convertToPurchaseModifier3").value(hasItem(DEFAULT_CONVERT_TO_PURCHASE_MODIFIER_3)))
            .andExpect(jsonPath("$.[*].convertToPurchaseModifier4").value(hasItem(DEFAULT_CONVERT_TO_PURCHASE_MODIFIER_4)))
            .andExpect(jsonPath("$.[*].billingMultiplierUnit").value(hasItem(DEFAULT_BILLING_MULTIPLIER_UNIT.intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].priceDetailsUuid").value(hasItem(DEFAULT_PRICE_DETAILS_UUID.toString())))
            .andExpect(jsonPath("$.[*].priceTableName").value(hasItem(DEFAULT_PRICE_TABLE_NAME)))
            .andExpect(jsonPath("$.[*].itemNo").value(hasItem(DEFAULT_ITEM_NO)))
            .andExpect(jsonPath("$.[*].itemName").value(hasItem(DEFAULT_ITEM_NAME)))
            .andExpect(jsonPath("$.[*].itemUom").value(hasItem(DEFAULT_ITEM_UOM)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].priceOptionBillingPeriodStart").value(hasItem(DEFAULT_PRICE_OPTION_BILLING_PERIOD_START.intValue())))
            .andExpect(jsonPath("$.[*].priceOptionBillingPeriodEnd").value(hasItem(DEFAULT_PRICE_OPTION_BILLING_PERIOD_END.intValue())));
    }

    @Test
    @Transactional
    void getPriceDetails() throws Exception {
        // Initialize the database
        priceDetailsRepository.saveAndFlush(priceDetails);

        // Get the priceDetails
        restPriceDetailsMockMvc
            .perform(get(ENTITY_API_URL_ID, priceDetails.getPriceDetailsId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.priceDetailsId").value(priceDetails.getPriceDetailsId().intValue()))
            .andExpect(jsonPath("$.priceTableId").value(DEFAULT_PRICE_TABLE_ID.intValue()))
            .andExpect(jsonPath("$.itemId").value(DEFAULT_ITEM_ID.intValue()))
            .andExpect(jsonPath("$.hcpcs").value(DEFAULT_HCPCS))
            .andExpect(jsonPath("$.billingCodeWhenSecondary").value(DEFAULT_BILLING_CODE_WHEN_SECONDARY))
            .andExpect(jsonPath("$.priceType").value(DEFAULT_PRICE_TYPE))
            .andExpect(jsonPath("$.effectiveStartDate").value(DEFAULT_EFFECTIVE_START_DATE.toString()))
            .andExpect(jsonPath("$.effectiveEndDate").value(DEFAULT_EFFECTIVE_END_DATE.toString()))
            .andExpect(jsonPath("$.cmnReqdToBillStatus").value(DEFAULT_CMN_REQD_TO_BILL_STATUS))
            .andExpect(jsonPath("$.cmnFormName").value(DEFAULT_CMN_FORM_NAME))
            .andExpect(jsonPath("$.priorAuthReqStatus").value(DEFAULT_PRIOR_AUTH_REQ_STATUS))
            .andExpect(jsonPath("$.functionalAbilityReqStatus").value(DEFAULT_FUNCTIONAL_ABILITY_REQ_STATUS))
            .andExpect(jsonPath("$.optionNumber").value(DEFAULT_OPTION_NUMBER))
            .andExpect(jsonPath("$.defaultOptionStatus").value(DEFAULT_DEFAULT_OPTION_STATUS))
            .andExpect(jsonPath("$.billingCyclePeriod").value(DEFAULT_BILLING_CYCLE_PERIOD))
            .andExpect(jsonPath("$.billingCycleInterval").value(DEFAULT_BILLING_CYCLE_INTERVAL))
            .andExpect(jsonPath("$.billingInArrearsStatus").value(DEFAULT_BILLING_IN_ARREARS_STATUS))
            .andExpect(jsonPath("$.proRateBillingStatus").value(DEFAULT_PRO_RATE_BILLING_STATUS))
            .andExpect(jsonPath("$.dailyBillingInvoiceFreq").value(DEFAULT_DAILY_BILLING_INVOICE_FREQ))
            .andExpect(jsonPath("$.dailyBillingInvoiceInterval").value(DEFAULT_DAILY_BILLING_INVOICE_INTERVAL))
            .andExpect(jsonPath("$.chargeAmt").value(DEFAULT_CHARGE_AMT.doubleValue()))
            .andExpect(jsonPath("$.allowedAmt").value(DEFAULT_ALLOWED_AMT.doubleValue()))
            .andExpect(jsonPath("$.allowedModifier1").value(DEFAULT_ALLOWED_MODIFIER_1))
            .andExpect(jsonPath("$.allowedModifier2").value(DEFAULT_ALLOWED_MODIFIER_2))
            .andExpect(jsonPath("$.allowedModifier3").value(DEFAULT_ALLOWED_MODIFIER_3))
            .andExpect(jsonPath("$.allowedModifier4").value(DEFAULT_ALLOWED_MODIFIER_4))
            .andExpect(jsonPath("$.acceptAssignmentStatus").value(DEFAULT_ACCEPT_ASSIGNMENT_STATUS))
            .andExpect(jsonPath("$.taxableStatus").value(DEFAULT_TAXABLE_STATUS))
            .andExpect(jsonPath("$.nontaxTypeName").value(DEFAULT_NONTAX_TYPE_NAME))
            .andExpect(jsonPath("$.convertToPurchaseLastStatus").value(DEFAULT_CONVERT_TO_PURCHASE_LAST_STATUS))
            .andExpect(jsonPath("$.convertToPurchaseChargeAmt").value(DEFAULT_CONVERT_TO_PURCHASE_CHARGE_AMT.doubleValue()))
            .andExpect(jsonPath("$.convertToPurchaseAllowAmt").value(DEFAULT_CONVERT_TO_PURCHASE_ALLOW_AMT.doubleValue()))
            .andExpect(jsonPath("$.convertToPurchaseModifier1").value(DEFAULT_CONVERT_TO_PURCHASE_MODIFIER_1))
            .andExpect(jsonPath("$.convertToPurchaseModifier2").value(DEFAULT_CONVERT_TO_PURCHASE_MODIFIER_2))
            .andExpect(jsonPath("$.convertToPurchaseModifier3").value(DEFAULT_CONVERT_TO_PURCHASE_MODIFIER_3))
            .andExpect(jsonPath("$.convertToPurchaseModifier4").value(DEFAULT_CONVERT_TO_PURCHASE_MODIFIER_4))
            .andExpect(jsonPath("$.billingMultiplierUnit").value(DEFAULT_BILLING_MULTIPLIER_UNIT.intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.priceDetailsUuid").value(DEFAULT_PRICE_DETAILS_UUID.toString()))
            .andExpect(jsonPath("$.priceTableName").value(DEFAULT_PRICE_TABLE_NAME))
            .andExpect(jsonPath("$.itemNo").value(DEFAULT_ITEM_NO))
            .andExpect(jsonPath("$.itemName").value(DEFAULT_ITEM_NAME))
            .andExpect(jsonPath("$.itemUom").value(DEFAULT_ITEM_UOM))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.priceOptionBillingPeriodStart").value(DEFAULT_PRICE_OPTION_BILLING_PERIOD_START.intValue()))
            .andExpect(jsonPath("$.priceOptionBillingPeriodEnd").value(DEFAULT_PRICE_OPTION_BILLING_PERIOD_END.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingPriceDetails() throws Exception {
        // Get the priceDetails
        restPriceDetailsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingPriceDetails() throws Exception {
        // Initialize the database
        priceDetailsRepository.saveAndFlush(priceDetails);

        int databaseSizeBeforeUpdate = priceDetailsRepository.findAll().size();

        // Update the priceDetails
        PriceDetails updatedPriceDetails = priceDetailsRepository.findById(priceDetails.getPriceDetailsId()).get();
        // Disconnect from session so that the updates on updatedPriceDetails are not directly saved in db
        em.detach(updatedPriceDetails);
        updatedPriceDetails
            .priceTableId(UPDATED_PRICE_TABLE_ID)
            .itemId(UPDATED_ITEM_ID)
            .hcpcs(UPDATED_HCPCS)
            .billingCodeWhenSecondary(UPDATED_BILLING_CODE_WHEN_SECONDARY)
            .priceType(UPDATED_PRICE_TYPE)
            .effectiveStartDate(UPDATED_EFFECTIVE_START_DATE)
            .effectiveEndDate(UPDATED_EFFECTIVE_END_DATE)
            .cmnReqdToBillStatus(UPDATED_CMN_REQD_TO_BILL_STATUS)
            .cmnFormName(UPDATED_CMN_FORM_NAME)
            .priorAuthReqStatus(UPDATED_PRIOR_AUTH_REQ_STATUS)
            .functionalAbilityReqStatus(UPDATED_FUNCTIONAL_ABILITY_REQ_STATUS)
            .optionNumber(UPDATED_OPTION_NUMBER)
            .defaultOptionStatus(UPDATED_DEFAULT_OPTION_STATUS)
            .billingCyclePeriod(UPDATED_BILLING_CYCLE_PERIOD)
            .billingCycleInterval(UPDATED_BILLING_CYCLE_INTERVAL)
            .billingInArrearsStatus(UPDATED_BILLING_IN_ARREARS_STATUS)
            .proRateBillingStatus(UPDATED_PRO_RATE_BILLING_STATUS)
            .dailyBillingInvoiceFreq(UPDATED_DAILY_BILLING_INVOICE_FREQ)
            .dailyBillingInvoiceInterval(UPDATED_DAILY_BILLING_INVOICE_INTERVAL)
            .chargeAmt(UPDATED_CHARGE_AMT)
            .allowedAmt(UPDATED_ALLOWED_AMT)
            .allowedModifier1(UPDATED_ALLOWED_MODIFIER_1)
            .allowedModifier2(UPDATED_ALLOWED_MODIFIER_2)
            .allowedModifier3(UPDATED_ALLOWED_MODIFIER_3)
            .allowedModifier4(UPDATED_ALLOWED_MODIFIER_4)
            .acceptAssignmentStatus(UPDATED_ACCEPT_ASSIGNMENT_STATUS)
            .taxableStatus(UPDATED_TAXABLE_STATUS)
            .nontaxTypeName(UPDATED_NONTAX_TYPE_NAME)
            .convertToPurchaseLastStatus(UPDATED_CONVERT_TO_PURCHASE_LAST_STATUS)
            .convertToPurchaseChargeAmt(UPDATED_CONVERT_TO_PURCHASE_CHARGE_AMT)
            .convertToPurchaseAllowAmt(UPDATED_CONVERT_TO_PURCHASE_ALLOW_AMT)
            .convertToPurchaseModifier1(UPDATED_CONVERT_TO_PURCHASE_MODIFIER_1)
            .convertToPurchaseModifier2(UPDATED_CONVERT_TO_PURCHASE_MODIFIER_2)
            .convertToPurchaseModifier3(UPDATED_CONVERT_TO_PURCHASE_MODIFIER_3)
            .convertToPurchaseModifier4(UPDATED_CONVERT_TO_PURCHASE_MODIFIER_4)
            .billingMultiplierUnit(UPDATED_BILLING_MULTIPLIER_UNIT)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .priceDetailsUuid(UPDATED_PRICE_DETAILS_UUID)
            .priceTableName(UPDATED_PRICE_TABLE_NAME)
            .itemNo(UPDATED_ITEM_NO)
            .itemName(UPDATED_ITEM_NAME)
            .itemUom(UPDATED_ITEM_UOM)
            .updatedDate(UPDATED_UPDATED_DATE)
            .priceOptionBillingPeriodStart(UPDATED_PRICE_OPTION_BILLING_PERIOD_START)
            .priceOptionBillingPeriodEnd(UPDATED_PRICE_OPTION_BILLING_PERIOD_END);
        PriceDetailsDTO priceDetailsDTO = priceDetailsMapper.toDto(updatedPriceDetails);

        restPriceDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, priceDetailsDTO.getPriceDetailsId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(priceDetailsDTO))
            )
            .andExpect(status().isOk());

        // Validate the PriceDetails in the database
        List<PriceDetails> priceDetailsList = priceDetailsRepository.findAll();
        assertThat(priceDetailsList).hasSize(databaseSizeBeforeUpdate);
        PriceDetails testPriceDetails = priceDetailsList.get(priceDetailsList.size() - 1);
        assertThat(testPriceDetails.getPriceTableId()).isEqualTo(UPDATED_PRICE_TABLE_ID);
        assertThat(testPriceDetails.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testPriceDetails.getHcpcs()).isEqualTo(UPDATED_HCPCS);
        assertThat(testPriceDetails.getBillingCodeWhenSecondary()).isEqualTo(UPDATED_BILLING_CODE_WHEN_SECONDARY);
        assertThat(testPriceDetails.getPriceType()).isEqualTo(UPDATED_PRICE_TYPE);
        assertThat(testPriceDetails.getEffectiveStartDate()).isEqualTo(UPDATED_EFFECTIVE_START_DATE);
        assertThat(testPriceDetails.getEffectiveEndDate()).isEqualTo(UPDATED_EFFECTIVE_END_DATE);
        assertThat(testPriceDetails.getCmnReqdToBillStatus()).isEqualTo(UPDATED_CMN_REQD_TO_BILL_STATUS);
        assertThat(testPriceDetails.getCmnFormName()).isEqualTo(UPDATED_CMN_FORM_NAME);
        assertThat(testPriceDetails.getPriorAuthReqStatus()).isEqualTo(UPDATED_PRIOR_AUTH_REQ_STATUS);
        assertThat(testPriceDetails.getFunctionalAbilityReqStatus()).isEqualTo(UPDATED_FUNCTIONAL_ABILITY_REQ_STATUS);
        assertThat(testPriceDetails.getOptionNumber()).isEqualTo(UPDATED_OPTION_NUMBER);
        assertThat(testPriceDetails.getDefaultOptionStatus()).isEqualTo(UPDATED_DEFAULT_OPTION_STATUS);
        assertThat(testPriceDetails.getBillingCyclePeriod()).isEqualTo(UPDATED_BILLING_CYCLE_PERIOD);
        assertThat(testPriceDetails.getBillingCycleInterval()).isEqualTo(UPDATED_BILLING_CYCLE_INTERVAL);
        assertThat(testPriceDetails.getBillingInArrearsStatus()).isEqualTo(UPDATED_BILLING_IN_ARREARS_STATUS);
        assertThat(testPriceDetails.getProRateBillingStatus()).isEqualTo(UPDATED_PRO_RATE_BILLING_STATUS);
        assertThat(testPriceDetails.getDailyBillingInvoiceFreq()).isEqualTo(UPDATED_DAILY_BILLING_INVOICE_FREQ);
        assertThat(testPriceDetails.getDailyBillingInvoiceInterval()).isEqualTo(UPDATED_DAILY_BILLING_INVOICE_INTERVAL);
        assertThat(testPriceDetails.getChargeAmt()).isEqualTo(UPDATED_CHARGE_AMT);
        assertThat(testPriceDetails.getAllowedAmt()).isEqualTo(UPDATED_ALLOWED_AMT);
        assertThat(testPriceDetails.getAllowedModifier1()).isEqualTo(UPDATED_ALLOWED_MODIFIER_1);
        assertThat(testPriceDetails.getAllowedModifier2()).isEqualTo(UPDATED_ALLOWED_MODIFIER_2);
        assertThat(testPriceDetails.getAllowedModifier3()).isEqualTo(UPDATED_ALLOWED_MODIFIER_3);
        assertThat(testPriceDetails.getAllowedModifier4()).isEqualTo(UPDATED_ALLOWED_MODIFIER_4);
        assertThat(testPriceDetails.getAcceptAssignmentStatus()).isEqualTo(UPDATED_ACCEPT_ASSIGNMENT_STATUS);
        assertThat(testPriceDetails.getTaxableStatus()).isEqualTo(UPDATED_TAXABLE_STATUS);
        assertThat(testPriceDetails.getNontaxTypeName()).isEqualTo(UPDATED_NONTAX_TYPE_NAME);
        assertThat(testPriceDetails.getConvertToPurchaseLastStatus()).isEqualTo(UPDATED_CONVERT_TO_PURCHASE_LAST_STATUS);
        assertThat(testPriceDetails.getConvertToPurchaseChargeAmt()).isEqualTo(UPDATED_CONVERT_TO_PURCHASE_CHARGE_AMT);
        assertThat(testPriceDetails.getConvertToPurchaseAllowAmt()).isEqualTo(UPDATED_CONVERT_TO_PURCHASE_ALLOW_AMT);
        assertThat(testPriceDetails.getConvertToPurchaseModifier1()).isEqualTo(UPDATED_CONVERT_TO_PURCHASE_MODIFIER_1);
        assertThat(testPriceDetails.getConvertToPurchaseModifier2()).isEqualTo(UPDATED_CONVERT_TO_PURCHASE_MODIFIER_2);
        assertThat(testPriceDetails.getConvertToPurchaseModifier3()).isEqualTo(UPDATED_CONVERT_TO_PURCHASE_MODIFIER_3);
        assertThat(testPriceDetails.getConvertToPurchaseModifier4()).isEqualTo(UPDATED_CONVERT_TO_PURCHASE_MODIFIER_4);
        assertThat(testPriceDetails.getBillingMultiplierUnit()).isEqualTo(UPDATED_BILLING_MULTIPLIER_UNIT);
        assertThat(testPriceDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPriceDetails.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPriceDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPriceDetails.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPriceDetails.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPriceDetails.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPriceDetails.getPriceDetailsUuid()).isEqualTo(UPDATED_PRICE_DETAILS_UUID);
        assertThat(testPriceDetails.getPriceTableName()).isEqualTo(UPDATED_PRICE_TABLE_NAME);
        assertThat(testPriceDetails.getItemNo()).isEqualTo(UPDATED_ITEM_NO);
        assertThat(testPriceDetails.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testPriceDetails.getItemUom()).isEqualTo(UPDATED_ITEM_UOM);
        assertThat(testPriceDetails.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPriceDetails.getPriceOptionBillingPeriodStart()).isEqualTo(UPDATED_PRICE_OPTION_BILLING_PERIOD_START);
        assertThat(testPriceDetails.getPriceOptionBillingPeriodEnd()).isEqualTo(UPDATED_PRICE_OPTION_BILLING_PERIOD_END);
    }

    @Test
    @Transactional
    void putNonExistingPriceDetails() throws Exception {
        int databaseSizeBeforeUpdate = priceDetailsRepository.findAll().size();
        priceDetails.setPriceDetailsId(count.incrementAndGet());

        // Create the PriceDetails
        PriceDetailsDTO priceDetailsDTO = priceDetailsMapper.toDto(priceDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPriceDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, priceDetailsDTO.getPriceDetailsId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(priceDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PriceDetails in the database
        List<PriceDetails> priceDetailsList = priceDetailsRepository.findAll();
        assertThat(priceDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPriceDetails() throws Exception {
        int databaseSizeBeforeUpdate = priceDetailsRepository.findAll().size();
        priceDetails.setPriceDetailsId(count.incrementAndGet());

        // Create the PriceDetails
        PriceDetailsDTO priceDetailsDTO = priceDetailsMapper.toDto(priceDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPriceDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(priceDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PriceDetails in the database
        List<PriceDetails> priceDetailsList = priceDetailsRepository.findAll();
        assertThat(priceDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPriceDetails() throws Exception {
        int databaseSizeBeforeUpdate = priceDetailsRepository.findAll().size();
        priceDetails.setPriceDetailsId(count.incrementAndGet());

        // Create the PriceDetails
        PriceDetailsDTO priceDetailsDTO = priceDetailsMapper.toDto(priceDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPriceDetailsMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(priceDetailsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PriceDetails in the database
        List<PriceDetails> priceDetailsList = priceDetailsRepository.findAll();
        assertThat(priceDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePriceDetailsWithPatch() throws Exception {
        // Initialize the database
        priceDetailsRepository.saveAndFlush(priceDetails);

        int databaseSizeBeforeUpdate = priceDetailsRepository.findAll().size();

        // Update the priceDetails using partial update
        PriceDetails partialUpdatedPriceDetails = new PriceDetails();
        partialUpdatedPriceDetails.setPriceDetailsId(priceDetails.getPriceDetailsId());

        partialUpdatedPriceDetails
            .itemId(UPDATED_ITEM_ID)
            .hcpcs(UPDATED_HCPCS)
            .effectiveStartDate(UPDATED_EFFECTIVE_START_DATE)
            .effectiveEndDate(UPDATED_EFFECTIVE_END_DATE)
            .cmnFormName(UPDATED_CMN_FORM_NAME)
            .priorAuthReqStatus(UPDATED_PRIOR_AUTH_REQ_STATUS)
            .functionalAbilityReqStatus(UPDATED_FUNCTIONAL_ABILITY_REQ_STATUS)
            .defaultOptionStatus(UPDATED_DEFAULT_OPTION_STATUS)
            .billingCycleInterval(UPDATED_BILLING_CYCLE_INTERVAL)
            .dailyBillingInvoiceFreq(UPDATED_DAILY_BILLING_INVOICE_FREQ)
            .dailyBillingInvoiceInterval(UPDATED_DAILY_BILLING_INVOICE_INTERVAL)
            .chargeAmt(UPDATED_CHARGE_AMT)
            .allowedModifier3(UPDATED_ALLOWED_MODIFIER_3)
            .allowedModifier4(UPDATED_ALLOWED_MODIFIER_4)
            .acceptAssignmentStatus(UPDATED_ACCEPT_ASSIGNMENT_STATUS)
            .nontaxTypeName(UPDATED_NONTAX_TYPE_NAME)
            .convertToPurchaseLastStatus(UPDATED_CONVERT_TO_PURCHASE_LAST_STATUS)
            .convertToPurchaseModifier3(UPDATED_CONVERT_TO_PURCHASE_MODIFIER_3)
            .convertToPurchaseModifier4(UPDATED_CONVERT_TO_PURCHASE_MODIFIER_4)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .priceTableName(UPDATED_PRICE_TABLE_NAME)
            .itemName(UPDATED_ITEM_NAME)
            .priceOptionBillingPeriodEnd(UPDATED_PRICE_OPTION_BILLING_PERIOD_END);

        restPriceDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPriceDetails.getPriceDetailsId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPriceDetails))
            )
            .andExpect(status().isOk());

        // Validate the PriceDetails in the database
        List<PriceDetails> priceDetailsList = priceDetailsRepository.findAll();
        assertThat(priceDetailsList).hasSize(databaseSizeBeforeUpdate);
        PriceDetails testPriceDetails = priceDetailsList.get(priceDetailsList.size() - 1);
        assertThat(testPriceDetails.getPriceTableId()).isEqualTo(DEFAULT_PRICE_TABLE_ID);
        assertThat(testPriceDetails.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testPriceDetails.getHcpcs()).isEqualTo(UPDATED_HCPCS);
        assertThat(testPriceDetails.getBillingCodeWhenSecondary()).isEqualTo(DEFAULT_BILLING_CODE_WHEN_SECONDARY);
        assertThat(testPriceDetails.getPriceType()).isEqualTo(DEFAULT_PRICE_TYPE);
        assertThat(testPriceDetails.getEffectiveStartDate()).isEqualTo(UPDATED_EFFECTIVE_START_DATE);
        assertThat(testPriceDetails.getEffectiveEndDate()).isEqualTo(UPDATED_EFFECTIVE_END_DATE);
        assertThat(testPriceDetails.getCmnReqdToBillStatus()).isEqualTo(DEFAULT_CMN_REQD_TO_BILL_STATUS);
        assertThat(testPriceDetails.getCmnFormName()).isEqualTo(UPDATED_CMN_FORM_NAME);
        assertThat(testPriceDetails.getPriorAuthReqStatus()).isEqualTo(UPDATED_PRIOR_AUTH_REQ_STATUS);
        assertThat(testPriceDetails.getFunctionalAbilityReqStatus()).isEqualTo(UPDATED_FUNCTIONAL_ABILITY_REQ_STATUS);
        assertThat(testPriceDetails.getOptionNumber()).isEqualTo(DEFAULT_OPTION_NUMBER);
        assertThat(testPriceDetails.getDefaultOptionStatus()).isEqualTo(UPDATED_DEFAULT_OPTION_STATUS);
        assertThat(testPriceDetails.getBillingCyclePeriod()).isEqualTo(DEFAULT_BILLING_CYCLE_PERIOD);
        assertThat(testPriceDetails.getBillingCycleInterval()).isEqualTo(UPDATED_BILLING_CYCLE_INTERVAL);
        assertThat(testPriceDetails.getBillingInArrearsStatus()).isEqualTo(DEFAULT_BILLING_IN_ARREARS_STATUS);
        assertThat(testPriceDetails.getProRateBillingStatus()).isEqualTo(DEFAULT_PRO_RATE_BILLING_STATUS);
        assertThat(testPriceDetails.getDailyBillingInvoiceFreq()).isEqualTo(UPDATED_DAILY_BILLING_INVOICE_FREQ);
        assertThat(testPriceDetails.getDailyBillingInvoiceInterval()).isEqualTo(UPDATED_DAILY_BILLING_INVOICE_INTERVAL);
        assertThat(testPriceDetails.getChargeAmt()).isEqualTo(UPDATED_CHARGE_AMT);
        assertThat(testPriceDetails.getAllowedAmt()).isEqualTo(DEFAULT_ALLOWED_AMT);
        assertThat(testPriceDetails.getAllowedModifier1()).isEqualTo(DEFAULT_ALLOWED_MODIFIER_1);
        assertThat(testPriceDetails.getAllowedModifier2()).isEqualTo(DEFAULT_ALLOWED_MODIFIER_2);
        assertThat(testPriceDetails.getAllowedModifier3()).isEqualTo(UPDATED_ALLOWED_MODIFIER_3);
        assertThat(testPriceDetails.getAllowedModifier4()).isEqualTo(UPDATED_ALLOWED_MODIFIER_4);
        assertThat(testPriceDetails.getAcceptAssignmentStatus()).isEqualTo(UPDATED_ACCEPT_ASSIGNMENT_STATUS);
        assertThat(testPriceDetails.getTaxableStatus()).isEqualTo(DEFAULT_TAXABLE_STATUS);
        assertThat(testPriceDetails.getNontaxTypeName()).isEqualTo(UPDATED_NONTAX_TYPE_NAME);
        assertThat(testPriceDetails.getConvertToPurchaseLastStatus()).isEqualTo(UPDATED_CONVERT_TO_PURCHASE_LAST_STATUS);
        assertThat(testPriceDetails.getConvertToPurchaseChargeAmt()).isEqualTo(DEFAULT_CONVERT_TO_PURCHASE_CHARGE_AMT);
        assertThat(testPriceDetails.getConvertToPurchaseAllowAmt()).isEqualTo(DEFAULT_CONVERT_TO_PURCHASE_ALLOW_AMT);
        assertThat(testPriceDetails.getConvertToPurchaseModifier1()).isEqualTo(DEFAULT_CONVERT_TO_PURCHASE_MODIFIER_1);
        assertThat(testPriceDetails.getConvertToPurchaseModifier2()).isEqualTo(DEFAULT_CONVERT_TO_PURCHASE_MODIFIER_2);
        assertThat(testPriceDetails.getConvertToPurchaseModifier3()).isEqualTo(UPDATED_CONVERT_TO_PURCHASE_MODIFIER_3);
        assertThat(testPriceDetails.getConvertToPurchaseModifier4()).isEqualTo(UPDATED_CONVERT_TO_PURCHASE_MODIFIER_4);
        assertThat(testPriceDetails.getBillingMultiplierUnit()).isEqualTo(DEFAULT_BILLING_MULTIPLIER_UNIT);
        assertThat(testPriceDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPriceDetails.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testPriceDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPriceDetails.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testPriceDetails.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testPriceDetails.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPriceDetails.getPriceDetailsUuid()).isEqualTo(DEFAULT_PRICE_DETAILS_UUID);
        assertThat(testPriceDetails.getPriceTableName()).isEqualTo(UPDATED_PRICE_TABLE_NAME);
        assertThat(testPriceDetails.getItemNo()).isEqualTo(DEFAULT_ITEM_NO);
        assertThat(testPriceDetails.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testPriceDetails.getItemUom()).isEqualTo(DEFAULT_ITEM_UOM);
        assertThat(testPriceDetails.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testPriceDetails.getPriceOptionBillingPeriodStart()).isEqualTo(DEFAULT_PRICE_OPTION_BILLING_PERIOD_START);
        assertThat(testPriceDetails.getPriceOptionBillingPeriodEnd()).isEqualTo(UPDATED_PRICE_OPTION_BILLING_PERIOD_END);
    }

    @Test
    @Transactional
    void fullUpdatePriceDetailsWithPatch() throws Exception {
        // Initialize the database
        priceDetailsRepository.saveAndFlush(priceDetails);

        int databaseSizeBeforeUpdate = priceDetailsRepository.findAll().size();

        // Update the priceDetails using partial update
        PriceDetails partialUpdatedPriceDetails = new PriceDetails();
        partialUpdatedPriceDetails.setPriceDetailsId(priceDetails.getPriceDetailsId());

        partialUpdatedPriceDetails
            .priceTableId(UPDATED_PRICE_TABLE_ID)
            .itemId(UPDATED_ITEM_ID)
            .hcpcs(UPDATED_HCPCS)
            .billingCodeWhenSecondary(UPDATED_BILLING_CODE_WHEN_SECONDARY)
            .priceType(UPDATED_PRICE_TYPE)
            .effectiveStartDate(UPDATED_EFFECTIVE_START_DATE)
            .effectiveEndDate(UPDATED_EFFECTIVE_END_DATE)
            .cmnReqdToBillStatus(UPDATED_CMN_REQD_TO_BILL_STATUS)
            .cmnFormName(UPDATED_CMN_FORM_NAME)
            .priorAuthReqStatus(UPDATED_PRIOR_AUTH_REQ_STATUS)
            .functionalAbilityReqStatus(UPDATED_FUNCTIONAL_ABILITY_REQ_STATUS)
            .optionNumber(UPDATED_OPTION_NUMBER)
            .defaultOptionStatus(UPDATED_DEFAULT_OPTION_STATUS)
            .billingCyclePeriod(UPDATED_BILLING_CYCLE_PERIOD)
            .billingCycleInterval(UPDATED_BILLING_CYCLE_INTERVAL)
            .billingInArrearsStatus(UPDATED_BILLING_IN_ARREARS_STATUS)
            .proRateBillingStatus(UPDATED_PRO_RATE_BILLING_STATUS)
            .dailyBillingInvoiceFreq(UPDATED_DAILY_BILLING_INVOICE_FREQ)
            .dailyBillingInvoiceInterval(UPDATED_DAILY_BILLING_INVOICE_INTERVAL)
            .chargeAmt(UPDATED_CHARGE_AMT)
            .allowedAmt(UPDATED_ALLOWED_AMT)
            .allowedModifier1(UPDATED_ALLOWED_MODIFIER_1)
            .allowedModifier2(UPDATED_ALLOWED_MODIFIER_2)
            .allowedModifier3(UPDATED_ALLOWED_MODIFIER_3)
            .allowedModifier4(UPDATED_ALLOWED_MODIFIER_4)
            .acceptAssignmentStatus(UPDATED_ACCEPT_ASSIGNMENT_STATUS)
            .taxableStatus(UPDATED_TAXABLE_STATUS)
            .nontaxTypeName(UPDATED_NONTAX_TYPE_NAME)
            .convertToPurchaseLastStatus(UPDATED_CONVERT_TO_PURCHASE_LAST_STATUS)
            .convertToPurchaseChargeAmt(UPDATED_CONVERT_TO_PURCHASE_CHARGE_AMT)
            .convertToPurchaseAllowAmt(UPDATED_CONVERT_TO_PURCHASE_ALLOW_AMT)
            .convertToPurchaseModifier1(UPDATED_CONVERT_TO_PURCHASE_MODIFIER_1)
            .convertToPurchaseModifier2(UPDATED_CONVERT_TO_PURCHASE_MODIFIER_2)
            .convertToPurchaseModifier3(UPDATED_CONVERT_TO_PURCHASE_MODIFIER_3)
            .convertToPurchaseModifier4(UPDATED_CONVERT_TO_PURCHASE_MODIFIER_4)
            .billingMultiplierUnit(UPDATED_BILLING_MULTIPLIER_UNIT)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .priceDetailsUuid(UPDATED_PRICE_DETAILS_UUID)
            .priceTableName(UPDATED_PRICE_TABLE_NAME)
            .itemNo(UPDATED_ITEM_NO)
            .itemName(UPDATED_ITEM_NAME)
            .itemUom(UPDATED_ITEM_UOM)
            .updatedDate(UPDATED_UPDATED_DATE)
            .priceOptionBillingPeriodStart(UPDATED_PRICE_OPTION_BILLING_PERIOD_START)
            .priceOptionBillingPeriodEnd(UPDATED_PRICE_OPTION_BILLING_PERIOD_END);

        restPriceDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPriceDetails.getPriceDetailsId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPriceDetails))
            )
            .andExpect(status().isOk());

        // Validate the PriceDetails in the database
        List<PriceDetails> priceDetailsList = priceDetailsRepository.findAll();
        assertThat(priceDetailsList).hasSize(databaseSizeBeforeUpdate);
        PriceDetails testPriceDetails = priceDetailsList.get(priceDetailsList.size() - 1);
        assertThat(testPriceDetails.getPriceTableId()).isEqualTo(UPDATED_PRICE_TABLE_ID);
        assertThat(testPriceDetails.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testPriceDetails.getHcpcs()).isEqualTo(UPDATED_HCPCS);
        assertThat(testPriceDetails.getBillingCodeWhenSecondary()).isEqualTo(UPDATED_BILLING_CODE_WHEN_SECONDARY);
        assertThat(testPriceDetails.getPriceType()).isEqualTo(UPDATED_PRICE_TYPE);
        assertThat(testPriceDetails.getEffectiveStartDate()).isEqualTo(UPDATED_EFFECTIVE_START_DATE);
        assertThat(testPriceDetails.getEffectiveEndDate()).isEqualTo(UPDATED_EFFECTIVE_END_DATE);
        assertThat(testPriceDetails.getCmnReqdToBillStatus()).isEqualTo(UPDATED_CMN_REQD_TO_BILL_STATUS);
        assertThat(testPriceDetails.getCmnFormName()).isEqualTo(UPDATED_CMN_FORM_NAME);
        assertThat(testPriceDetails.getPriorAuthReqStatus()).isEqualTo(UPDATED_PRIOR_AUTH_REQ_STATUS);
        assertThat(testPriceDetails.getFunctionalAbilityReqStatus()).isEqualTo(UPDATED_FUNCTIONAL_ABILITY_REQ_STATUS);
        assertThat(testPriceDetails.getOptionNumber()).isEqualTo(UPDATED_OPTION_NUMBER);
        assertThat(testPriceDetails.getDefaultOptionStatus()).isEqualTo(UPDATED_DEFAULT_OPTION_STATUS);
        assertThat(testPriceDetails.getBillingCyclePeriod()).isEqualTo(UPDATED_BILLING_CYCLE_PERIOD);
        assertThat(testPriceDetails.getBillingCycleInterval()).isEqualTo(UPDATED_BILLING_CYCLE_INTERVAL);
        assertThat(testPriceDetails.getBillingInArrearsStatus()).isEqualTo(UPDATED_BILLING_IN_ARREARS_STATUS);
        assertThat(testPriceDetails.getProRateBillingStatus()).isEqualTo(UPDATED_PRO_RATE_BILLING_STATUS);
        assertThat(testPriceDetails.getDailyBillingInvoiceFreq()).isEqualTo(UPDATED_DAILY_BILLING_INVOICE_FREQ);
        assertThat(testPriceDetails.getDailyBillingInvoiceInterval()).isEqualTo(UPDATED_DAILY_BILLING_INVOICE_INTERVAL);
        assertThat(testPriceDetails.getChargeAmt()).isEqualTo(UPDATED_CHARGE_AMT);
        assertThat(testPriceDetails.getAllowedAmt()).isEqualTo(UPDATED_ALLOWED_AMT);
        assertThat(testPriceDetails.getAllowedModifier1()).isEqualTo(UPDATED_ALLOWED_MODIFIER_1);
        assertThat(testPriceDetails.getAllowedModifier2()).isEqualTo(UPDATED_ALLOWED_MODIFIER_2);
        assertThat(testPriceDetails.getAllowedModifier3()).isEqualTo(UPDATED_ALLOWED_MODIFIER_3);
        assertThat(testPriceDetails.getAllowedModifier4()).isEqualTo(UPDATED_ALLOWED_MODIFIER_4);
        assertThat(testPriceDetails.getAcceptAssignmentStatus()).isEqualTo(UPDATED_ACCEPT_ASSIGNMENT_STATUS);
        assertThat(testPriceDetails.getTaxableStatus()).isEqualTo(UPDATED_TAXABLE_STATUS);
        assertThat(testPriceDetails.getNontaxTypeName()).isEqualTo(UPDATED_NONTAX_TYPE_NAME);
        assertThat(testPriceDetails.getConvertToPurchaseLastStatus()).isEqualTo(UPDATED_CONVERT_TO_PURCHASE_LAST_STATUS);
        assertThat(testPriceDetails.getConvertToPurchaseChargeAmt()).isEqualTo(UPDATED_CONVERT_TO_PURCHASE_CHARGE_AMT);
        assertThat(testPriceDetails.getConvertToPurchaseAllowAmt()).isEqualTo(UPDATED_CONVERT_TO_PURCHASE_ALLOW_AMT);
        assertThat(testPriceDetails.getConvertToPurchaseModifier1()).isEqualTo(UPDATED_CONVERT_TO_PURCHASE_MODIFIER_1);
        assertThat(testPriceDetails.getConvertToPurchaseModifier2()).isEqualTo(UPDATED_CONVERT_TO_PURCHASE_MODIFIER_2);
        assertThat(testPriceDetails.getConvertToPurchaseModifier3()).isEqualTo(UPDATED_CONVERT_TO_PURCHASE_MODIFIER_3);
        assertThat(testPriceDetails.getConvertToPurchaseModifier4()).isEqualTo(UPDATED_CONVERT_TO_PURCHASE_MODIFIER_4);
        assertThat(testPriceDetails.getBillingMultiplierUnit()).isEqualTo(UPDATED_BILLING_MULTIPLIER_UNIT);
        assertThat(testPriceDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPriceDetails.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPriceDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPriceDetails.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPriceDetails.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPriceDetails.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPriceDetails.getPriceDetailsUuid()).isEqualTo(UPDATED_PRICE_DETAILS_UUID);
        assertThat(testPriceDetails.getPriceTableName()).isEqualTo(UPDATED_PRICE_TABLE_NAME);
        assertThat(testPriceDetails.getItemNo()).isEqualTo(UPDATED_ITEM_NO);
        assertThat(testPriceDetails.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testPriceDetails.getItemUom()).isEqualTo(UPDATED_ITEM_UOM);
        assertThat(testPriceDetails.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPriceDetails.getPriceOptionBillingPeriodStart()).isEqualTo(UPDATED_PRICE_OPTION_BILLING_PERIOD_START);
        assertThat(testPriceDetails.getPriceOptionBillingPeriodEnd()).isEqualTo(UPDATED_PRICE_OPTION_BILLING_PERIOD_END);
    }

    @Test
    @Transactional
    void patchNonExistingPriceDetails() throws Exception {
        int databaseSizeBeforeUpdate = priceDetailsRepository.findAll().size();
        priceDetails.setPriceDetailsId(count.incrementAndGet());

        // Create the PriceDetails
        PriceDetailsDTO priceDetailsDTO = priceDetailsMapper.toDto(priceDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPriceDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, priceDetailsDTO.getPriceDetailsId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(priceDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PriceDetails in the database
        List<PriceDetails> priceDetailsList = priceDetailsRepository.findAll();
        assertThat(priceDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPriceDetails() throws Exception {
        int databaseSizeBeforeUpdate = priceDetailsRepository.findAll().size();
        priceDetails.setPriceDetailsId(count.incrementAndGet());

        // Create the PriceDetails
        PriceDetailsDTO priceDetailsDTO = priceDetailsMapper.toDto(priceDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPriceDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(priceDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PriceDetails in the database
        List<PriceDetails> priceDetailsList = priceDetailsRepository.findAll();
        assertThat(priceDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPriceDetails() throws Exception {
        int databaseSizeBeforeUpdate = priceDetailsRepository.findAll().size();
        priceDetails.setPriceDetailsId(count.incrementAndGet());

        // Create the PriceDetails
        PriceDetailsDTO priceDetailsDTO = priceDetailsMapper.toDto(priceDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPriceDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(priceDetailsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PriceDetails in the database
        List<PriceDetails> priceDetailsList = priceDetailsRepository.findAll();
        assertThat(priceDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePriceDetails() throws Exception {
        // Initialize the database
        priceDetailsRepository.saveAndFlush(priceDetails);

        int databaseSizeBeforeDelete = priceDetailsRepository.findAll().size();

        // Delete the priceDetails
        restPriceDetailsMockMvc
            .perform(delete(ENTITY_API_URL_ID, priceDetails.getPriceDetailsId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PriceDetails> priceDetailsList = priceDetailsRepository.findAll();
        assertThat(priceDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
