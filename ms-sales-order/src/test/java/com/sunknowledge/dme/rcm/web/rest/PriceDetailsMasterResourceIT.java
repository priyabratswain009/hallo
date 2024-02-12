package com.sunknowledge.dme.rcm.web.rest;

import static com.sunknowledge.dme.rcm.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.PriceDetailsMaster;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.PriceDetailsMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.PriceDetailsMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.PriceDetailsMasterMapper;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
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
 * Integration tests for the {@link PriceDetailsMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class PriceDetailsMasterResourceIT {

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

    private static final String DEFAULT_OPTION_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_OPTION_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_OPTION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_OPTION_NAME = "BBBBBBBBBB";

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

    private static final ZonedDateTime DEFAULT_CREATED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY_ID = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_ID = "BBBBBBBBBB";

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

    private static final ZonedDateTime DEFAULT_UPDATED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Long DEFAULT_PRICE_OPTION_BILLING_PERIOD_START = 1L;
    private static final Long UPDATED_PRICE_OPTION_BILLING_PERIOD_START = 2L;

    private static final Long DEFAULT_PRICE_OPTION_BILLING_PERIOD_END = 1L;
    private static final Long UPDATED_PRICE_OPTION_BILLING_PERIOD_END = 2L;

    private static final String ENTITY_API_URL = "/api/price-details-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{priceDetailsId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PriceDetailsMasterRepository priceDetailsMasterRepository;

    @Autowired
    private PriceDetailsMasterMapper priceDetailsMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private PriceDetailsMaster priceDetailsMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PriceDetailsMaster createEntity(EntityManager em) {
        PriceDetailsMaster priceDetailsMaster = new PriceDetailsMaster()
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
            .optionNumber(DEFAULT_OPTION_NUMBER)
            .optionName(DEFAULT_OPTION_NAME)
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
        return priceDetailsMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PriceDetailsMaster createUpdatedEntity(EntityManager em) {
        PriceDetailsMaster priceDetailsMaster = new PriceDetailsMaster()
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
            .optionNumber(UPDATED_OPTION_NUMBER)
            .optionName(UPDATED_OPTION_NAME)
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
        return priceDetailsMaster;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(PriceDetailsMaster.class).block();
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
        priceDetailsMaster = createEntity(em);
    }

    @Test
    void createPriceDetailsMaster() throws Exception {
        int databaseSizeBeforeCreate = priceDetailsMasterRepository.findAll().collectList().block().size();
        // Create the PriceDetailsMaster
        PriceDetailsMasterDTO priceDetailsMasterDTO = priceDetailsMasterMapper.toDto(priceDetailsMaster);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(priceDetailsMasterDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the PriceDetailsMaster in the database
        List<PriceDetailsMaster> priceDetailsMasterList = priceDetailsMasterRepository.findAll().collectList().block();
        assertThat(priceDetailsMasterList).hasSize(databaseSizeBeforeCreate + 1);
        PriceDetailsMaster testPriceDetailsMaster = priceDetailsMasterList.get(priceDetailsMasterList.size() - 1);
        assertThat(testPriceDetailsMaster.getPriceTableId()).isEqualTo(DEFAULT_PRICE_TABLE_ID);
        assertThat(testPriceDetailsMaster.getItemId()).isEqualTo(DEFAULT_ITEM_ID);
        assertThat(testPriceDetailsMaster.getHcpcs()).isEqualTo(DEFAULT_HCPCS);
        assertThat(testPriceDetailsMaster.getBillingCodeWhenSecondary()).isEqualTo(DEFAULT_BILLING_CODE_WHEN_SECONDARY);
        assertThat(testPriceDetailsMaster.getPriceType()).isEqualTo(DEFAULT_PRICE_TYPE);
        assertThat(testPriceDetailsMaster.getEffectiveStartDate()).isEqualTo(DEFAULT_EFFECTIVE_START_DATE);
        assertThat(testPriceDetailsMaster.getEffectiveEndDate()).isEqualTo(DEFAULT_EFFECTIVE_END_DATE);
        assertThat(testPriceDetailsMaster.getCmnReqdToBillStatus()).isEqualTo(DEFAULT_CMN_REQD_TO_BILL_STATUS);
        assertThat(testPriceDetailsMaster.getCmnFormName()).isEqualTo(DEFAULT_CMN_FORM_NAME);
        assertThat(testPriceDetailsMaster.getPriorAuthReqStatus()).isEqualTo(DEFAULT_PRIOR_AUTH_REQ_STATUS);
        assertThat(testPriceDetailsMaster.getOptionNumber()).isEqualTo(DEFAULT_OPTION_NUMBER);
        assertThat(testPriceDetailsMaster.getOptionName()).isEqualTo(DEFAULT_OPTION_NAME);
        assertThat(testPriceDetailsMaster.getDefaultOptionStatus()).isEqualTo(DEFAULT_DEFAULT_OPTION_STATUS);
        assertThat(testPriceDetailsMaster.getBillingCyclePeriod()).isEqualTo(DEFAULT_BILLING_CYCLE_PERIOD);
        assertThat(testPriceDetailsMaster.getBillingCycleInterval()).isEqualTo(DEFAULT_BILLING_CYCLE_INTERVAL);
        assertThat(testPriceDetailsMaster.getBillingInArrearsStatus()).isEqualTo(DEFAULT_BILLING_IN_ARREARS_STATUS);
        assertThat(testPriceDetailsMaster.getProRateBillingStatus()).isEqualTo(DEFAULT_PRO_RATE_BILLING_STATUS);
        assertThat(testPriceDetailsMaster.getDailyBillingInvoiceFreq()).isEqualTo(DEFAULT_DAILY_BILLING_INVOICE_FREQ);
        assertThat(testPriceDetailsMaster.getDailyBillingInvoiceInterval()).isEqualTo(DEFAULT_DAILY_BILLING_INVOICE_INTERVAL);
        assertThat(testPriceDetailsMaster.getChargeAmt()).isEqualTo(DEFAULT_CHARGE_AMT);
        assertThat(testPriceDetailsMaster.getAllowedAmt()).isEqualTo(DEFAULT_ALLOWED_AMT);
        assertThat(testPriceDetailsMaster.getAllowedModifier1()).isEqualTo(DEFAULT_ALLOWED_MODIFIER_1);
        assertThat(testPriceDetailsMaster.getAllowedModifier2()).isEqualTo(DEFAULT_ALLOWED_MODIFIER_2);
        assertThat(testPriceDetailsMaster.getAllowedModifier3()).isEqualTo(DEFAULT_ALLOWED_MODIFIER_3);
        assertThat(testPriceDetailsMaster.getAllowedModifier4()).isEqualTo(DEFAULT_ALLOWED_MODIFIER_4);
        assertThat(testPriceDetailsMaster.getAcceptAssignmentStatus()).isEqualTo(DEFAULT_ACCEPT_ASSIGNMENT_STATUS);
        assertThat(testPriceDetailsMaster.getTaxableStatus()).isEqualTo(DEFAULT_TAXABLE_STATUS);
        assertThat(testPriceDetailsMaster.getNontaxTypeName()).isEqualTo(DEFAULT_NONTAX_TYPE_NAME);
        assertThat(testPriceDetailsMaster.getConvertToPurchaseLastStatus()).isEqualTo(DEFAULT_CONVERT_TO_PURCHASE_LAST_STATUS);
        assertThat(testPriceDetailsMaster.getConvertToPurchaseChargeAmt()).isEqualTo(DEFAULT_CONVERT_TO_PURCHASE_CHARGE_AMT);
        assertThat(testPriceDetailsMaster.getConvertToPurchaseAllowAmt()).isEqualTo(DEFAULT_CONVERT_TO_PURCHASE_ALLOW_AMT);
        assertThat(testPriceDetailsMaster.getConvertToPurchaseModifier1()).isEqualTo(DEFAULT_CONVERT_TO_PURCHASE_MODIFIER_1);
        assertThat(testPriceDetailsMaster.getConvertToPurchaseModifier2()).isEqualTo(DEFAULT_CONVERT_TO_PURCHASE_MODIFIER_2);
        assertThat(testPriceDetailsMaster.getConvertToPurchaseModifier3()).isEqualTo(DEFAULT_CONVERT_TO_PURCHASE_MODIFIER_3);
        assertThat(testPriceDetailsMaster.getConvertToPurchaseModifier4()).isEqualTo(DEFAULT_CONVERT_TO_PURCHASE_MODIFIER_4);
        assertThat(testPriceDetailsMaster.getBillingMultiplierUnit()).isEqualTo(DEFAULT_BILLING_MULTIPLIER_UNIT);
        assertThat(testPriceDetailsMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPriceDetailsMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testPriceDetailsMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testPriceDetailsMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testPriceDetailsMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testPriceDetailsMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testPriceDetailsMaster.getPriceDetailsUuid()).isEqualTo(DEFAULT_PRICE_DETAILS_UUID);
        assertThat(testPriceDetailsMaster.getPriceTableName()).isEqualTo(DEFAULT_PRICE_TABLE_NAME);
        assertThat(testPriceDetailsMaster.getItemNo()).isEqualTo(DEFAULT_ITEM_NO);
        assertThat(testPriceDetailsMaster.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testPriceDetailsMaster.getItemUom()).isEqualTo(DEFAULT_ITEM_UOM);
        assertThat(testPriceDetailsMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testPriceDetailsMaster.getPriceOptionBillingPeriodStart()).isEqualTo(DEFAULT_PRICE_OPTION_BILLING_PERIOD_START);
        assertThat(testPriceDetailsMaster.getPriceOptionBillingPeriodEnd()).isEqualTo(DEFAULT_PRICE_OPTION_BILLING_PERIOD_END);
    }

    @Test
    void createPriceDetailsMasterWithExistingId() throws Exception {
        // Create the PriceDetailsMaster with an existing ID
        priceDetailsMaster.setPriceDetailsId(1L);
        PriceDetailsMasterDTO priceDetailsMasterDTO = priceDetailsMasterMapper.toDto(priceDetailsMaster);

        int databaseSizeBeforeCreate = priceDetailsMasterRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(priceDetailsMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PriceDetailsMaster in the database
        List<PriceDetailsMaster> priceDetailsMasterList = priceDetailsMasterRepository.findAll().collectList().block();
        assertThat(priceDetailsMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllPriceDetailsMasters() {
        // Initialize the database
        priceDetailsMasterRepository.save(priceDetailsMaster).block();

        // Get all the priceDetailsMasterList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=priceDetailsId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].priceDetailsId")
            .value(hasItem(priceDetailsMaster.getPriceDetailsId().intValue()))
            .jsonPath("$.[*].priceTableId")
            .value(hasItem(DEFAULT_PRICE_TABLE_ID.intValue()))
            .jsonPath("$.[*].itemId")
            .value(hasItem(DEFAULT_ITEM_ID.intValue()))
            .jsonPath("$.[*].hcpcs")
            .value(hasItem(DEFAULT_HCPCS))
            .jsonPath("$.[*].billingCodeWhenSecondary")
            .value(hasItem(DEFAULT_BILLING_CODE_WHEN_SECONDARY))
            .jsonPath("$.[*].priceType")
            .value(hasItem(DEFAULT_PRICE_TYPE))
            .jsonPath("$.[*].effectiveStartDate")
            .value(hasItem(DEFAULT_EFFECTIVE_START_DATE.toString()))
            .jsonPath("$.[*].effectiveEndDate")
            .value(hasItem(DEFAULT_EFFECTIVE_END_DATE.toString()))
            .jsonPath("$.[*].cmnReqdToBillStatus")
            .value(hasItem(DEFAULT_CMN_REQD_TO_BILL_STATUS))
            .jsonPath("$.[*].cmnFormName")
            .value(hasItem(DEFAULT_CMN_FORM_NAME))
            .jsonPath("$.[*].priorAuthReqStatus")
            .value(hasItem(DEFAULT_PRIOR_AUTH_REQ_STATUS))
            .jsonPath("$.[*].optionNumber")
            .value(hasItem(DEFAULT_OPTION_NUMBER))
            .jsonPath("$.[*].optionName")
            .value(hasItem(DEFAULT_OPTION_NAME))
            .jsonPath("$.[*].defaultOptionStatus")
            .value(hasItem(DEFAULT_DEFAULT_OPTION_STATUS))
            .jsonPath("$.[*].billingCyclePeriod")
            .value(hasItem(DEFAULT_BILLING_CYCLE_PERIOD))
            .jsonPath("$.[*].billingCycleInterval")
            .value(hasItem(DEFAULT_BILLING_CYCLE_INTERVAL))
            .jsonPath("$.[*].billingInArrearsStatus")
            .value(hasItem(DEFAULT_BILLING_IN_ARREARS_STATUS))
            .jsonPath("$.[*].proRateBillingStatus")
            .value(hasItem(DEFAULT_PRO_RATE_BILLING_STATUS))
            .jsonPath("$.[*].dailyBillingInvoiceFreq")
            .value(hasItem(DEFAULT_DAILY_BILLING_INVOICE_FREQ))
            .jsonPath("$.[*].dailyBillingInvoiceInterval")
            .value(hasItem(DEFAULT_DAILY_BILLING_INVOICE_INTERVAL))
            .jsonPath("$.[*].chargeAmt")
            .value(hasItem(DEFAULT_CHARGE_AMT.doubleValue()))
            .jsonPath("$.[*].allowedAmt")
            .value(hasItem(DEFAULT_ALLOWED_AMT.doubleValue()))
            .jsonPath("$.[*].allowedModifier1")
            .value(hasItem(DEFAULT_ALLOWED_MODIFIER_1))
            .jsonPath("$.[*].allowedModifier2")
            .value(hasItem(DEFAULT_ALLOWED_MODIFIER_2))
            .jsonPath("$.[*].allowedModifier3")
            .value(hasItem(DEFAULT_ALLOWED_MODIFIER_3))
            .jsonPath("$.[*].allowedModifier4")
            .value(hasItem(DEFAULT_ALLOWED_MODIFIER_4))
            .jsonPath("$.[*].acceptAssignmentStatus")
            .value(hasItem(DEFAULT_ACCEPT_ASSIGNMENT_STATUS))
            .jsonPath("$.[*].taxableStatus")
            .value(hasItem(DEFAULT_TAXABLE_STATUS))
            .jsonPath("$.[*].nontaxTypeName")
            .value(hasItem(DEFAULT_NONTAX_TYPE_NAME))
            .jsonPath("$.[*].convertToPurchaseLastStatus")
            .value(hasItem(DEFAULT_CONVERT_TO_PURCHASE_LAST_STATUS))
            .jsonPath("$.[*].convertToPurchaseChargeAmt")
            .value(hasItem(DEFAULT_CONVERT_TO_PURCHASE_CHARGE_AMT.doubleValue()))
            .jsonPath("$.[*].convertToPurchaseAllowAmt")
            .value(hasItem(DEFAULT_CONVERT_TO_PURCHASE_ALLOW_AMT.doubleValue()))
            .jsonPath("$.[*].convertToPurchaseModifier1")
            .value(hasItem(DEFAULT_CONVERT_TO_PURCHASE_MODIFIER_1))
            .jsonPath("$.[*].convertToPurchaseModifier2")
            .value(hasItem(DEFAULT_CONVERT_TO_PURCHASE_MODIFIER_2))
            .jsonPath("$.[*].convertToPurchaseModifier3")
            .value(hasItem(DEFAULT_CONVERT_TO_PURCHASE_MODIFIER_3))
            .jsonPath("$.[*].convertToPurchaseModifier4")
            .value(hasItem(DEFAULT_CONVERT_TO_PURCHASE_MODIFIER_4))
            .jsonPath("$.[*].billingMultiplierUnit")
            .value(hasItem(DEFAULT_BILLING_MULTIPLIER_UNIT.intValue()))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS))
            .jsonPath("$.[*].createdById")
            .value(hasItem(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.[*].createdDate")
            .value(hasItem(sameInstant(DEFAULT_CREATED_DATE)))
            .jsonPath("$.[*].createdByName")
            .value(hasItem(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.[*].updatedByName")
            .value(hasItem(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.[*].updatedById")
            .value(hasItem(DEFAULT_UPDATED_BY_ID))
            .jsonPath("$.[*].priceDetailsUuid")
            .value(hasItem(DEFAULT_PRICE_DETAILS_UUID.toString()))
            .jsonPath("$.[*].priceTableName")
            .value(hasItem(DEFAULT_PRICE_TABLE_NAME))
            .jsonPath("$.[*].itemNo")
            .value(hasItem(DEFAULT_ITEM_NO))
            .jsonPath("$.[*].itemName")
            .value(hasItem(DEFAULT_ITEM_NAME))
            .jsonPath("$.[*].itemUom")
            .value(hasItem(DEFAULT_ITEM_UOM))
            .jsonPath("$.[*].updatedDate")
            .value(hasItem(sameInstant(DEFAULT_UPDATED_DATE)))
            .jsonPath("$.[*].priceOptionBillingPeriodStart")
            .value(hasItem(DEFAULT_PRICE_OPTION_BILLING_PERIOD_START.intValue()))
            .jsonPath("$.[*].priceOptionBillingPeriodEnd")
            .value(hasItem(DEFAULT_PRICE_OPTION_BILLING_PERIOD_END.intValue()));
    }

    @Test
    void getPriceDetailsMaster() {
        // Initialize the database
        priceDetailsMasterRepository.save(priceDetailsMaster).block();

        // Get the priceDetailsMaster
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, priceDetailsMaster.getPriceDetailsId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.priceDetailsId")
            .value(is(priceDetailsMaster.getPriceDetailsId().intValue()))
            .jsonPath("$.priceTableId")
            .value(is(DEFAULT_PRICE_TABLE_ID.intValue()))
            .jsonPath("$.itemId")
            .value(is(DEFAULT_ITEM_ID.intValue()))
            .jsonPath("$.hcpcs")
            .value(is(DEFAULT_HCPCS))
            .jsonPath("$.billingCodeWhenSecondary")
            .value(is(DEFAULT_BILLING_CODE_WHEN_SECONDARY))
            .jsonPath("$.priceType")
            .value(is(DEFAULT_PRICE_TYPE))
            .jsonPath("$.effectiveStartDate")
            .value(is(DEFAULT_EFFECTIVE_START_DATE.toString()))
            .jsonPath("$.effectiveEndDate")
            .value(is(DEFAULT_EFFECTIVE_END_DATE.toString()))
            .jsonPath("$.cmnReqdToBillStatus")
            .value(is(DEFAULT_CMN_REQD_TO_BILL_STATUS))
            .jsonPath("$.cmnFormName")
            .value(is(DEFAULT_CMN_FORM_NAME))
            .jsonPath("$.priorAuthReqStatus")
            .value(is(DEFAULT_PRIOR_AUTH_REQ_STATUS))
            .jsonPath("$.optionNumber")
            .value(is(DEFAULT_OPTION_NUMBER))
            .jsonPath("$.optionName")
            .value(is(DEFAULT_OPTION_NAME))
            .jsonPath("$.defaultOptionStatus")
            .value(is(DEFAULT_DEFAULT_OPTION_STATUS))
            .jsonPath("$.billingCyclePeriod")
            .value(is(DEFAULT_BILLING_CYCLE_PERIOD))
            .jsonPath("$.billingCycleInterval")
            .value(is(DEFAULT_BILLING_CYCLE_INTERVAL))
            .jsonPath("$.billingInArrearsStatus")
            .value(is(DEFAULT_BILLING_IN_ARREARS_STATUS))
            .jsonPath("$.proRateBillingStatus")
            .value(is(DEFAULT_PRO_RATE_BILLING_STATUS))
            .jsonPath("$.dailyBillingInvoiceFreq")
            .value(is(DEFAULT_DAILY_BILLING_INVOICE_FREQ))
            .jsonPath("$.dailyBillingInvoiceInterval")
            .value(is(DEFAULT_DAILY_BILLING_INVOICE_INTERVAL))
            .jsonPath("$.chargeAmt")
            .value(is(DEFAULT_CHARGE_AMT.doubleValue()))
            .jsonPath("$.allowedAmt")
            .value(is(DEFAULT_ALLOWED_AMT.doubleValue()))
            .jsonPath("$.allowedModifier1")
            .value(is(DEFAULT_ALLOWED_MODIFIER_1))
            .jsonPath("$.allowedModifier2")
            .value(is(DEFAULT_ALLOWED_MODIFIER_2))
            .jsonPath("$.allowedModifier3")
            .value(is(DEFAULT_ALLOWED_MODIFIER_3))
            .jsonPath("$.allowedModifier4")
            .value(is(DEFAULT_ALLOWED_MODIFIER_4))
            .jsonPath("$.acceptAssignmentStatus")
            .value(is(DEFAULT_ACCEPT_ASSIGNMENT_STATUS))
            .jsonPath("$.taxableStatus")
            .value(is(DEFAULT_TAXABLE_STATUS))
            .jsonPath("$.nontaxTypeName")
            .value(is(DEFAULT_NONTAX_TYPE_NAME))
            .jsonPath("$.convertToPurchaseLastStatus")
            .value(is(DEFAULT_CONVERT_TO_PURCHASE_LAST_STATUS))
            .jsonPath("$.convertToPurchaseChargeAmt")
            .value(is(DEFAULT_CONVERT_TO_PURCHASE_CHARGE_AMT.doubleValue()))
            .jsonPath("$.convertToPurchaseAllowAmt")
            .value(is(DEFAULT_CONVERT_TO_PURCHASE_ALLOW_AMT.doubleValue()))
            .jsonPath("$.convertToPurchaseModifier1")
            .value(is(DEFAULT_CONVERT_TO_PURCHASE_MODIFIER_1))
            .jsonPath("$.convertToPurchaseModifier2")
            .value(is(DEFAULT_CONVERT_TO_PURCHASE_MODIFIER_2))
            .jsonPath("$.convertToPurchaseModifier3")
            .value(is(DEFAULT_CONVERT_TO_PURCHASE_MODIFIER_3))
            .jsonPath("$.convertToPurchaseModifier4")
            .value(is(DEFAULT_CONVERT_TO_PURCHASE_MODIFIER_4))
            .jsonPath("$.billingMultiplierUnit")
            .value(is(DEFAULT_BILLING_MULTIPLIER_UNIT.intValue()))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS))
            .jsonPath("$.createdById")
            .value(is(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.createdDate")
            .value(is(sameInstant(DEFAULT_CREATED_DATE)))
            .jsonPath("$.createdByName")
            .value(is(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.updatedByName")
            .value(is(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.updatedById")
            .value(is(DEFAULT_UPDATED_BY_ID))
            .jsonPath("$.priceDetailsUuid")
            .value(is(DEFAULT_PRICE_DETAILS_UUID.toString()))
            .jsonPath("$.priceTableName")
            .value(is(DEFAULT_PRICE_TABLE_NAME))
            .jsonPath("$.itemNo")
            .value(is(DEFAULT_ITEM_NO))
            .jsonPath("$.itemName")
            .value(is(DEFAULT_ITEM_NAME))
            .jsonPath("$.itemUom")
            .value(is(DEFAULT_ITEM_UOM))
            .jsonPath("$.updatedDate")
            .value(is(sameInstant(DEFAULT_UPDATED_DATE)))
            .jsonPath("$.priceOptionBillingPeriodStart")
            .value(is(DEFAULT_PRICE_OPTION_BILLING_PERIOD_START.intValue()))
            .jsonPath("$.priceOptionBillingPeriodEnd")
            .value(is(DEFAULT_PRICE_OPTION_BILLING_PERIOD_END.intValue()));
    }

    @Test
    void getNonExistingPriceDetailsMaster() {
        // Get the priceDetailsMaster
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingPriceDetailsMaster() throws Exception {
        // Initialize the database
        priceDetailsMasterRepository.save(priceDetailsMaster).block();

        int databaseSizeBeforeUpdate = priceDetailsMasterRepository.findAll().collectList().block().size();

        // Update the priceDetailsMaster
        PriceDetailsMaster updatedPriceDetailsMaster = priceDetailsMasterRepository
            .findById(priceDetailsMaster.getPriceDetailsId())
            .block();
        updatedPriceDetailsMaster
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
            .optionNumber(UPDATED_OPTION_NUMBER)
            .optionName(UPDATED_OPTION_NAME)
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
        PriceDetailsMasterDTO priceDetailsMasterDTO = priceDetailsMasterMapper.toDto(updatedPriceDetailsMaster);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, priceDetailsMasterDTO.getPriceDetailsId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(priceDetailsMasterDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PriceDetailsMaster in the database
        List<PriceDetailsMaster> priceDetailsMasterList = priceDetailsMasterRepository.findAll().collectList().block();
        assertThat(priceDetailsMasterList).hasSize(databaseSizeBeforeUpdate);
        PriceDetailsMaster testPriceDetailsMaster = priceDetailsMasterList.get(priceDetailsMasterList.size() - 1);
        assertThat(testPriceDetailsMaster.getPriceTableId()).isEqualTo(UPDATED_PRICE_TABLE_ID);
        assertThat(testPriceDetailsMaster.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testPriceDetailsMaster.getHcpcs()).isEqualTo(UPDATED_HCPCS);
        assertThat(testPriceDetailsMaster.getBillingCodeWhenSecondary()).isEqualTo(UPDATED_BILLING_CODE_WHEN_SECONDARY);
        assertThat(testPriceDetailsMaster.getPriceType()).isEqualTo(UPDATED_PRICE_TYPE);
        assertThat(testPriceDetailsMaster.getEffectiveStartDate()).isEqualTo(UPDATED_EFFECTIVE_START_DATE);
        assertThat(testPriceDetailsMaster.getEffectiveEndDate()).isEqualTo(UPDATED_EFFECTIVE_END_DATE);
        assertThat(testPriceDetailsMaster.getCmnReqdToBillStatus()).isEqualTo(UPDATED_CMN_REQD_TO_BILL_STATUS);
        assertThat(testPriceDetailsMaster.getCmnFormName()).isEqualTo(UPDATED_CMN_FORM_NAME);
        assertThat(testPriceDetailsMaster.getPriorAuthReqStatus()).isEqualTo(UPDATED_PRIOR_AUTH_REQ_STATUS);
        assertThat(testPriceDetailsMaster.getOptionNumber()).isEqualTo(UPDATED_OPTION_NUMBER);
        assertThat(testPriceDetailsMaster.getOptionName()).isEqualTo(UPDATED_OPTION_NAME);
        assertThat(testPriceDetailsMaster.getDefaultOptionStatus()).isEqualTo(UPDATED_DEFAULT_OPTION_STATUS);
        assertThat(testPriceDetailsMaster.getBillingCyclePeriod()).isEqualTo(UPDATED_BILLING_CYCLE_PERIOD);
        assertThat(testPriceDetailsMaster.getBillingCycleInterval()).isEqualTo(UPDATED_BILLING_CYCLE_INTERVAL);
        assertThat(testPriceDetailsMaster.getBillingInArrearsStatus()).isEqualTo(UPDATED_BILLING_IN_ARREARS_STATUS);
        assertThat(testPriceDetailsMaster.getProRateBillingStatus()).isEqualTo(UPDATED_PRO_RATE_BILLING_STATUS);
        assertThat(testPriceDetailsMaster.getDailyBillingInvoiceFreq()).isEqualTo(UPDATED_DAILY_BILLING_INVOICE_FREQ);
        assertThat(testPriceDetailsMaster.getDailyBillingInvoiceInterval()).isEqualTo(UPDATED_DAILY_BILLING_INVOICE_INTERVAL);
        assertThat(testPriceDetailsMaster.getChargeAmt()).isEqualTo(UPDATED_CHARGE_AMT);
        assertThat(testPriceDetailsMaster.getAllowedAmt()).isEqualTo(UPDATED_ALLOWED_AMT);
        assertThat(testPriceDetailsMaster.getAllowedModifier1()).isEqualTo(UPDATED_ALLOWED_MODIFIER_1);
        assertThat(testPriceDetailsMaster.getAllowedModifier2()).isEqualTo(UPDATED_ALLOWED_MODIFIER_2);
        assertThat(testPriceDetailsMaster.getAllowedModifier3()).isEqualTo(UPDATED_ALLOWED_MODIFIER_3);
        assertThat(testPriceDetailsMaster.getAllowedModifier4()).isEqualTo(UPDATED_ALLOWED_MODIFIER_4);
        assertThat(testPriceDetailsMaster.getAcceptAssignmentStatus()).isEqualTo(UPDATED_ACCEPT_ASSIGNMENT_STATUS);
        assertThat(testPriceDetailsMaster.getTaxableStatus()).isEqualTo(UPDATED_TAXABLE_STATUS);
        assertThat(testPriceDetailsMaster.getNontaxTypeName()).isEqualTo(UPDATED_NONTAX_TYPE_NAME);
        assertThat(testPriceDetailsMaster.getConvertToPurchaseLastStatus()).isEqualTo(UPDATED_CONVERT_TO_PURCHASE_LAST_STATUS);
        assertThat(testPriceDetailsMaster.getConvertToPurchaseChargeAmt()).isEqualTo(UPDATED_CONVERT_TO_PURCHASE_CHARGE_AMT);
        assertThat(testPriceDetailsMaster.getConvertToPurchaseAllowAmt()).isEqualTo(UPDATED_CONVERT_TO_PURCHASE_ALLOW_AMT);
        assertThat(testPriceDetailsMaster.getConvertToPurchaseModifier1()).isEqualTo(UPDATED_CONVERT_TO_PURCHASE_MODIFIER_1);
        assertThat(testPriceDetailsMaster.getConvertToPurchaseModifier2()).isEqualTo(UPDATED_CONVERT_TO_PURCHASE_MODIFIER_2);
        assertThat(testPriceDetailsMaster.getConvertToPurchaseModifier3()).isEqualTo(UPDATED_CONVERT_TO_PURCHASE_MODIFIER_3);
        assertThat(testPriceDetailsMaster.getConvertToPurchaseModifier4()).isEqualTo(UPDATED_CONVERT_TO_PURCHASE_MODIFIER_4);
        assertThat(testPriceDetailsMaster.getBillingMultiplierUnit()).isEqualTo(UPDATED_BILLING_MULTIPLIER_UNIT);
        assertThat(testPriceDetailsMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPriceDetailsMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPriceDetailsMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPriceDetailsMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPriceDetailsMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPriceDetailsMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPriceDetailsMaster.getPriceDetailsUuid()).isEqualTo(UPDATED_PRICE_DETAILS_UUID);
        assertThat(testPriceDetailsMaster.getPriceTableName()).isEqualTo(UPDATED_PRICE_TABLE_NAME);
        assertThat(testPriceDetailsMaster.getItemNo()).isEqualTo(UPDATED_ITEM_NO);
        assertThat(testPriceDetailsMaster.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testPriceDetailsMaster.getItemUom()).isEqualTo(UPDATED_ITEM_UOM);
        assertThat(testPriceDetailsMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPriceDetailsMaster.getPriceOptionBillingPeriodStart()).isEqualTo(UPDATED_PRICE_OPTION_BILLING_PERIOD_START);
        assertThat(testPriceDetailsMaster.getPriceOptionBillingPeriodEnd()).isEqualTo(UPDATED_PRICE_OPTION_BILLING_PERIOD_END);
    }

    @Test
    void putNonExistingPriceDetailsMaster() throws Exception {
        int databaseSizeBeforeUpdate = priceDetailsMasterRepository.findAll().collectList().block().size();
        priceDetailsMaster.setPriceDetailsId(count.incrementAndGet());

        // Create the PriceDetailsMaster
        PriceDetailsMasterDTO priceDetailsMasterDTO = priceDetailsMasterMapper.toDto(priceDetailsMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, priceDetailsMasterDTO.getPriceDetailsId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(priceDetailsMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PriceDetailsMaster in the database
        List<PriceDetailsMaster> priceDetailsMasterList = priceDetailsMasterRepository.findAll().collectList().block();
        assertThat(priceDetailsMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchPriceDetailsMaster() throws Exception {
        int databaseSizeBeforeUpdate = priceDetailsMasterRepository.findAll().collectList().block().size();
        priceDetailsMaster.setPriceDetailsId(count.incrementAndGet());

        // Create the PriceDetailsMaster
        PriceDetailsMasterDTO priceDetailsMasterDTO = priceDetailsMasterMapper.toDto(priceDetailsMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(priceDetailsMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PriceDetailsMaster in the database
        List<PriceDetailsMaster> priceDetailsMasterList = priceDetailsMasterRepository.findAll().collectList().block();
        assertThat(priceDetailsMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamPriceDetailsMaster() throws Exception {
        int databaseSizeBeforeUpdate = priceDetailsMasterRepository.findAll().collectList().block().size();
        priceDetailsMaster.setPriceDetailsId(count.incrementAndGet());

        // Create the PriceDetailsMaster
        PriceDetailsMasterDTO priceDetailsMasterDTO = priceDetailsMasterMapper.toDto(priceDetailsMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(priceDetailsMasterDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PriceDetailsMaster in the database
        List<PriceDetailsMaster> priceDetailsMasterList = priceDetailsMasterRepository.findAll().collectList().block();
        assertThat(priceDetailsMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdatePriceDetailsMasterWithPatch() throws Exception {
        // Initialize the database
        priceDetailsMasterRepository.save(priceDetailsMaster).block();

        int databaseSizeBeforeUpdate = priceDetailsMasterRepository.findAll().collectList().block().size();

        // Update the priceDetailsMaster using partial update
        PriceDetailsMaster partialUpdatedPriceDetailsMaster = new PriceDetailsMaster();
        partialUpdatedPriceDetailsMaster.setPriceDetailsId(priceDetailsMaster.getPriceDetailsId());

        partialUpdatedPriceDetailsMaster
            .priceTableId(UPDATED_PRICE_TABLE_ID)
            .hcpcs(UPDATED_HCPCS)
            .priceType(UPDATED_PRICE_TYPE)
            .effectiveStartDate(UPDATED_EFFECTIVE_START_DATE)
            .effectiveEndDate(UPDATED_EFFECTIVE_END_DATE)
            .cmnFormName(UPDATED_CMN_FORM_NAME)
            .optionNumber(UPDATED_OPTION_NUMBER)
            .optionName(UPDATED_OPTION_NAME)
            .billingCyclePeriod(UPDATED_BILLING_CYCLE_PERIOD)
            .billingInArrearsStatus(UPDATED_BILLING_IN_ARREARS_STATUS)
            .dailyBillingInvoiceFreq(UPDATED_DAILY_BILLING_INVOICE_FREQ)
            .dailyBillingInvoiceInterval(UPDATED_DAILY_BILLING_INVOICE_INTERVAL)
            .chargeAmt(UPDATED_CHARGE_AMT)
            .allowedAmt(UPDATED_ALLOWED_AMT)
            .allowedModifier2(UPDATED_ALLOWED_MODIFIER_2)
            .taxableStatus(UPDATED_TAXABLE_STATUS)
            .nontaxTypeName(UPDATED_NONTAX_TYPE_NAME)
            .convertToPurchaseLastStatus(UPDATED_CONVERT_TO_PURCHASE_LAST_STATUS)
            .convertToPurchaseChargeAmt(UPDATED_CONVERT_TO_PURCHASE_CHARGE_AMT)
            .convertToPurchaseAllowAmt(UPDATED_CONVERT_TO_PURCHASE_ALLOW_AMT)
            .convertToPurchaseModifier1(UPDATED_CONVERT_TO_PURCHASE_MODIFIER_1)
            .convertToPurchaseModifier4(UPDATED_CONVERT_TO_PURCHASE_MODIFIER_4)
            .billingMultiplierUnit(UPDATED_BILLING_MULTIPLIER_UNIT)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .priceDetailsUuid(UPDATED_PRICE_DETAILS_UUID)
            .priceTableName(UPDATED_PRICE_TABLE_NAME)
            .itemName(UPDATED_ITEM_NAME)
            .updatedDate(UPDATED_UPDATED_DATE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPriceDetailsMaster.getPriceDetailsId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPriceDetailsMaster))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PriceDetailsMaster in the database
        List<PriceDetailsMaster> priceDetailsMasterList = priceDetailsMasterRepository.findAll().collectList().block();
        assertThat(priceDetailsMasterList).hasSize(databaseSizeBeforeUpdate);
        PriceDetailsMaster testPriceDetailsMaster = priceDetailsMasterList.get(priceDetailsMasterList.size() - 1);
        assertThat(testPriceDetailsMaster.getPriceTableId()).isEqualTo(UPDATED_PRICE_TABLE_ID);
        assertThat(testPriceDetailsMaster.getItemId()).isEqualTo(DEFAULT_ITEM_ID);
        assertThat(testPriceDetailsMaster.getHcpcs()).isEqualTo(UPDATED_HCPCS);
        assertThat(testPriceDetailsMaster.getBillingCodeWhenSecondary()).isEqualTo(DEFAULT_BILLING_CODE_WHEN_SECONDARY);
        assertThat(testPriceDetailsMaster.getPriceType()).isEqualTo(UPDATED_PRICE_TYPE);
        assertThat(testPriceDetailsMaster.getEffectiveStartDate()).isEqualTo(UPDATED_EFFECTIVE_START_DATE);
        assertThat(testPriceDetailsMaster.getEffectiveEndDate()).isEqualTo(UPDATED_EFFECTIVE_END_DATE);
        assertThat(testPriceDetailsMaster.getCmnReqdToBillStatus()).isEqualTo(DEFAULT_CMN_REQD_TO_BILL_STATUS);
        assertThat(testPriceDetailsMaster.getCmnFormName()).isEqualTo(UPDATED_CMN_FORM_NAME);
        assertThat(testPriceDetailsMaster.getPriorAuthReqStatus()).isEqualTo(DEFAULT_PRIOR_AUTH_REQ_STATUS);
        assertThat(testPriceDetailsMaster.getOptionNumber()).isEqualTo(UPDATED_OPTION_NUMBER);
        assertThat(testPriceDetailsMaster.getOptionName()).isEqualTo(UPDATED_OPTION_NAME);
        assertThat(testPriceDetailsMaster.getDefaultOptionStatus()).isEqualTo(DEFAULT_DEFAULT_OPTION_STATUS);
        assertThat(testPriceDetailsMaster.getBillingCyclePeriod()).isEqualTo(UPDATED_BILLING_CYCLE_PERIOD);
        assertThat(testPriceDetailsMaster.getBillingCycleInterval()).isEqualTo(DEFAULT_BILLING_CYCLE_INTERVAL);
        assertThat(testPriceDetailsMaster.getBillingInArrearsStatus()).isEqualTo(UPDATED_BILLING_IN_ARREARS_STATUS);
        assertThat(testPriceDetailsMaster.getProRateBillingStatus()).isEqualTo(DEFAULT_PRO_RATE_BILLING_STATUS);
        assertThat(testPriceDetailsMaster.getDailyBillingInvoiceFreq()).isEqualTo(UPDATED_DAILY_BILLING_INVOICE_FREQ);
        assertThat(testPriceDetailsMaster.getDailyBillingInvoiceInterval()).isEqualTo(UPDATED_DAILY_BILLING_INVOICE_INTERVAL);
        assertThat(testPriceDetailsMaster.getChargeAmt()).isEqualTo(UPDATED_CHARGE_AMT);
        assertThat(testPriceDetailsMaster.getAllowedAmt()).isEqualTo(UPDATED_ALLOWED_AMT);
        assertThat(testPriceDetailsMaster.getAllowedModifier1()).isEqualTo(DEFAULT_ALLOWED_MODIFIER_1);
        assertThat(testPriceDetailsMaster.getAllowedModifier2()).isEqualTo(UPDATED_ALLOWED_MODIFIER_2);
        assertThat(testPriceDetailsMaster.getAllowedModifier3()).isEqualTo(DEFAULT_ALLOWED_MODIFIER_3);
        assertThat(testPriceDetailsMaster.getAllowedModifier4()).isEqualTo(DEFAULT_ALLOWED_MODIFIER_4);
        assertThat(testPriceDetailsMaster.getAcceptAssignmentStatus()).isEqualTo(DEFAULT_ACCEPT_ASSIGNMENT_STATUS);
        assertThat(testPriceDetailsMaster.getTaxableStatus()).isEqualTo(UPDATED_TAXABLE_STATUS);
        assertThat(testPriceDetailsMaster.getNontaxTypeName()).isEqualTo(UPDATED_NONTAX_TYPE_NAME);
        assertThat(testPriceDetailsMaster.getConvertToPurchaseLastStatus()).isEqualTo(UPDATED_CONVERT_TO_PURCHASE_LAST_STATUS);
        assertThat(testPriceDetailsMaster.getConvertToPurchaseChargeAmt()).isEqualTo(UPDATED_CONVERT_TO_PURCHASE_CHARGE_AMT);
        assertThat(testPriceDetailsMaster.getConvertToPurchaseAllowAmt()).isEqualTo(UPDATED_CONVERT_TO_PURCHASE_ALLOW_AMT);
        assertThat(testPriceDetailsMaster.getConvertToPurchaseModifier1()).isEqualTo(UPDATED_CONVERT_TO_PURCHASE_MODIFIER_1);
        assertThat(testPriceDetailsMaster.getConvertToPurchaseModifier2()).isEqualTo(DEFAULT_CONVERT_TO_PURCHASE_MODIFIER_2);
        assertThat(testPriceDetailsMaster.getConvertToPurchaseModifier3()).isEqualTo(DEFAULT_CONVERT_TO_PURCHASE_MODIFIER_3);
        assertThat(testPriceDetailsMaster.getConvertToPurchaseModifier4()).isEqualTo(UPDATED_CONVERT_TO_PURCHASE_MODIFIER_4);
        assertThat(testPriceDetailsMaster.getBillingMultiplierUnit()).isEqualTo(UPDATED_BILLING_MULTIPLIER_UNIT);
        assertThat(testPriceDetailsMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPriceDetailsMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testPriceDetailsMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPriceDetailsMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPriceDetailsMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testPriceDetailsMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPriceDetailsMaster.getPriceDetailsUuid()).isEqualTo(UPDATED_PRICE_DETAILS_UUID);
        assertThat(testPriceDetailsMaster.getPriceTableName()).isEqualTo(UPDATED_PRICE_TABLE_NAME);
        assertThat(testPriceDetailsMaster.getItemNo()).isEqualTo(DEFAULT_ITEM_NO);
        assertThat(testPriceDetailsMaster.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testPriceDetailsMaster.getItemUom()).isEqualTo(DEFAULT_ITEM_UOM);
        assertThat(testPriceDetailsMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPriceDetailsMaster.getPriceOptionBillingPeriodStart()).isEqualTo(DEFAULT_PRICE_OPTION_BILLING_PERIOD_START);
        assertThat(testPriceDetailsMaster.getPriceOptionBillingPeriodEnd()).isEqualTo(DEFAULT_PRICE_OPTION_BILLING_PERIOD_END);
    }

    @Test
    void fullUpdatePriceDetailsMasterWithPatch() throws Exception {
        // Initialize the database
        priceDetailsMasterRepository.save(priceDetailsMaster).block();

        int databaseSizeBeforeUpdate = priceDetailsMasterRepository.findAll().collectList().block().size();

        // Update the priceDetailsMaster using partial update
        PriceDetailsMaster partialUpdatedPriceDetailsMaster = new PriceDetailsMaster();
        partialUpdatedPriceDetailsMaster.setPriceDetailsId(priceDetailsMaster.getPriceDetailsId());

        partialUpdatedPriceDetailsMaster
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
            .optionNumber(UPDATED_OPTION_NUMBER)
            .optionName(UPDATED_OPTION_NAME)
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

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPriceDetailsMaster.getPriceDetailsId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPriceDetailsMaster))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PriceDetailsMaster in the database
        List<PriceDetailsMaster> priceDetailsMasterList = priceDetailsMasterRepository.findAll().collectList().block();
        assertThat(priceDetailsMasterList).hasSize(databaseSizeBeforeUpdate);
        PriceDetailsMaster testPriceDetailsMaster = priceDetailsMasterList.get(priceDetailsMasterList.size() - 1);
        assertThat(testPriceDetailsMaster.getPriceTableId()).isEqualTo(UPDATED_PRICE_TABLE_ID);
        assertThat(testPriceDetailsMaster.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testPriceDetailsMaster.getHcpcs()).isEqualTo(UPDATED_HCPCS);
        assertThat(testPriceDetailsMaster.getBillingCodeWhenSecondary()).isEqualTo(UPDATED_BILLING_CODE_WHEN_SECONDARY);
        assertThat(testPriceDetailsMaster.getPriceType()).isEqualTo(UPDATED_PRICE_TYPE);
        assertThat(testPriceDetailsMaster.getEffectiveStartDate()).isEqualTo(UPDATED_EFFECTIVE_START_DATE);
        assertThat(testPriceDetailsMaster.getEffectiveEndDate()).isEqualTo(UPDATED_EFFECTIVE_END_DATE);
        assertThat(testPriceDetailsMaster.getCmnReqdToBillStatus()).isEqualTo(UPDATED_CMN_REQD_TO_BILL_STATUS);
        assertThat(testPriceDetailsMaster.getCmnFormName()).isEqualTo(UPDATED_CMN_FORM_NAME);
        assertThat(testPriceDetailsMaster.getPriorAuthReqStatus()).isEqualTo(UPDATED_PRIOR_AUTH_REQ_STATUS);
        assertThat(testPriceDetailsMaster.getOptionNumber()).isEqualTo(UPDATED_OPTION_NUMBER);
        assertThat(testPriceDetailsMaster.getOptionName()).isEqualTo(UPDATED_OPTION_NAME);
        assertThat(testPriceDetailsMaster.getDefaultOptionStatus()).isEqualTo(UPDATED_DEFAULT_OPTION_STATUS);
        assertThat(testPriceDetailsMaster.getBillingCyclePeriod()).isEqualTo(UPDATED_BILLING_CYCLE_PERIOD);
        assertThat(testPriceDetailsMaster.getBillingCycleInterval()).isEqualTo(UPDATED_BILLING_CYCLE_INTERVAL);
        assertThat(testPriceDetailsMaster.getBillingInArrearsStatus()).isEqualTo(UPDATED_BILLING_IN_ARREARS_STATUS);
        assertThat(testPriceDetailsMaster.getProRateBillingStatus()).isEqualTo(UPDATED_PRO_RATE_BILLING_STATUS);
        assertThat(testPriceDetailsMaster.getDailyBillingInvoiceFreq()).isEqualTo(UPDATED_DAILY_BILLING_INVOICE_FREQ);
        assertThat(testPriceDetailsMaster.getDailyBillingInvoiceInterval()).isEqualTo(UPDATED_DAILY_BILLING_INVOICE_INTERVAL);
        assertThat(testPriceDetailsMaster.getChargeAmt()).isEqualTo(UPDATED_CHARGE_AMT);
        assertThat(testPriceDetailsMaster.getAllowedAmt()).isEqualTo(UPDATED_ALLOWED_AMT);
        assertThat(testPriceDetailsMaster.getAllowedModifier1()).isEqualTo(UPDATED_ALLOWED_MODIFIER_1);
        assertThat(testPriceDetailsMaster.getAllowedModifier2()).isEqualTo(UPDATED_ALLOWED_MODIFIER_2);
        assertThat(testPriceDetailsMaster.getAllowedModifier3()).isEqualTo(UPDATED_ALLOWED_MODIFIER_3);
        assertThat(testPriceDetailsMaster.getAllowedModifier4()).isEqualTo(UPDATED_ALLOWED_MODIFIER_4);
        assertThat(testPriceDetailsMaster.getAcceptAssignmentStatus()).isEqualTo(UPDATED_ACCEPT_ASSIGNMENT_STATUS);
        assertThat(testPriceDetailsMaster.getTaxableStatus()).isEqualTo(UPDATED_TAXABLE_STATUS);
        assertThat(testPriceDetailsMaster.getNontaxTypeName()).isEqualTo(UPDATED_NONTAX_TYPE_NAME);
        assertThat(testPriceDetailsMaster.getConvertToPurchaseLastStatus()).isEqualTo(UPDATED_CONVERT_TO_PURCHASE_LAST_STATUS);
        assertThat(testPriceDetailsMaster.getConvertToPurchaseChargeAmt()).isEqualTo(UPDATED_CONVERT_TO_PURCHASE_CHARGE_AMT);
        assertThat(testPriceDetailsMaster.getConvertToPurchaseAllowAmt()).isEqualTo(UPDATED_CONVERT_TO_PURCHASE_ALLOW_AMT);
        assertThat(testPriceDetailsMaster.getConvertToPurchaseModifier1()).isEqualTo(UPDATED_CONVERT_TO_PURCHASE_MODIFIER_1);
        assertThat(testPriceDetailsMaster.getConvertToPurchaseModifier2()).isEqualTo(UPDATED_CONVERT_TO_PURCHASE_MODIFIER_2);
        assertThat(testPriceDetailsMaster.getConvertToPurchaseModifier3()).isEqualTo(UPDATED_CONVERT_TO_PURCHASE_MODIFIER_3);
        assertThat(testPriceDetailsMaster.getConvertToPurchaseModifier4()).isEqualTo(UPDATED_CONVERT_TO_PURCHASE_MODIFIER_4);
        assertThat(testPriceDetailsMaster.getBillingMultiplierUnit()).isEqualTo(UPDATED_BILLING_MULTIPLIER_UNIT);
        assertThat(testPriceDetailsMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPriceDetailsMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPriceDetailsMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPriceDetailsMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPriceDetailsMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPriceDetailsMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPriceDetailsMaster.getPriceDetailsUuid()).isEqualTo(UPDATED_PRICE_DETAILS_UUID);
        assertThat(testPriceDetailsMaster.getPriceTableName()).isEqualTo(UPDATED_PRICE_TABLE_NAME);
        assertThat(testPriceDetailsMaster.getItemNo()).isEqualTo(UPDATED_ITEM_NO);
        assertThat(testPriceDetailsMaster.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testPriceDetailsMaster.getItemUom()).isEqualTo(UPDATED_ITEM_UOM);
        assertThat(testPriceDetailsMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPriceDetailsMaster.getPriceOptionBillingPeriodStart()).isEqualTo(UPDATED_PRICE_OPTION_BILLING_PERIOD_START);
        assertThat(testPriceDetailsMaster.getPriceOptionBillingPeriodEnd()).isEqualTo(UPDATED_PRICE_OPTION_BILLING_PERIOD_END);
    }

    @Test
    void patchNonExistingPriceDetailsMaster() throws Exception {
        int databaseSizeBeforeUpdate = priceDetailsMasterRepository.findAll().collectList().block().size();
        priceDetailsMaster.setPriceDetailsId(count.incrementAndGet());

        // Create the PriceDetailsMaster
        PriceDetailsMasterDTO priceDetailsMasterDTO = priceDetailsMasterMapper.toDto(priceDetailsMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, priceDetailsMasterDTO.getPriceDetailsId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(priceDetailsMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PriceDetailsMaster in the database
        List<PriceDetailsMaster> priceDetailsMasterList = priceDetailsMasterRepository.findAll().collectList().block();
        assertThat(priceDetailsMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchPriceDetailsMaster() throws Exception {
        int databaseSizeBeforeUpdate = priceDetailsMasterRepository.findAll().collectList().block().size();
        priceDetailsMaster.setPriceDetailsId(count.incrementAndGet());

        // Create the PriceDetailsMaster
        PriceDetailsMasterDTO priceDetailsMasterDTO = priceDetailsMasterMapper.toDto(priceDetailsMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(priceDetailsMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PriceDetailsMaster in the database
        List<PriceDetailsMaster> priceDetailsMasterList = priceDetailsMasterRepository.findAll().collectList().block();
        assertThat(priceDetailsMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamPriceDetailsMaster() throws Exception {
        int databaseSizeBeforeUpdate = priceDetailsMasterRepository.findAll().collectList().block().size();
        priceDetailsMaster.setPriceDetailsId(count.incrementAndGet());

        // Create the PriceDetailsMaster
        PriceDetailsMasterDTO priceDetailsMasterDTO = priceDetailsMasterMapper.toDto(priceDetailsMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(priceDetailsMasterDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PriceDetailsMaster in the database
        List<PriceDetailsMaster> priceDetailsMasterList = priceDetailsMasterRepository.findAll().collectList().block();
        assertThat(priceDetailsMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deletePriceDetailsMaster() {
        // Initialize the database
        priceDetailsMasterRepository.save(priceDetailsMaster).block();

        int databaseSizeBeforeDelete = priceDetailsMasterRepository.findAll().collectList().block().size();

        // Delete the priceDetailsMaster
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, priceDetailsMaster.getPriceDetailsId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<PriceDetailsMaster> priceDetailsMasterList = priceDetailsMasterRepository.findAll().collectList().block();
        assertThat(priceDetailsMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
