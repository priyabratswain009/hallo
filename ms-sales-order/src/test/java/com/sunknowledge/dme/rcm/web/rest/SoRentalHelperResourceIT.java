package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.SoRentalHelper;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.SoRentalHelperRepository;
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
 * Integration tests for the {@link SoRentalHelperResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class SoRentalHelperResourceIT {

    private static final Long DEFAULT_SO_ID = 1L;
    private static final Long UPDATED_SO_ID = 2L;

    private static final Long DEFAULT_PRIMARY_INSURER_ID = 1L;
    private static final Long UPDATED_PRIMARY_INSURER_ID = 2L;

    private static final String DEFAULT_PRIMARY_INSURER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_INSURER_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_ITEM_ID = 1L;
    private static final Long UPDATED_ITEM_ID = 2L;

    private static final String DEFAULT_ITEM_NO = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NO = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NAME = "BBBBBBBBBB";

    private static final Double DEFAULT_CHARGED_AMOUNT = 1D;
    private static final Double UPDATED_CHARGED_AMOUNT = 2D;

    private static final Double DEFAULT_ALLOWED_AMOUNT = 1D;
    private static final Double UPDATED_ALLOWED_AMOUNT = 2D;

    private static final String DEFAULT_SOU = "AAAAAAAAAA";
    private static final String UPDATED_SOU = "BBBBBBBBBB";

    private static final Double DEFAULT_QTY = 1D;
    private static final Double UPDATED_QTY = 2D;

    private static final LocalDate DEFAULT_DOS_START = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DOS_START = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DOS_END = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DOS_END = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_PERIOD_NO = "AAAAAAAAAA";
    private static final String UPDATED_PERIOD_NO = "BBBBBBBBBB";

    private static final String DEFAULT_RENTAL_PERIOD = "AAAAAAAAAA";
    private static final String UPDATED_RENTAL_PERIOD = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NEXT_DOS = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NEXT_DOS = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final UUID DEFAULT_SO_RENTAL_HELPER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_SO_RENTAL_HELPER_UUID = UUID.randomUUID();

    private static final Long DEFAULT_PATIENT_ID = 1L;
    private static final Long UPDATED_PATIENT_ID = 2L;

    private static final String DEFAULT_SALE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SALE_TYPE = "BBBBBBBBBB";

    private static final Long DEFAULT_PRIMARY_INSURANCE_PRICE_TABLE_ID = 1L;
    private static final Long UPDATED_PRIMARY_INSURANCE_PRICE_TABLE_ID = 2L;

    private static final String DEFAULT_PRIMARY_INSURANCE_PRICE_TABLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_INSURANCE_PRICE_TABLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MODIFIER_1 = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIER_1 = "BBBBBBBBBB";

    private static final String DEFAULT_MODIFIER_2 = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIER_2 = "BBBBBBBBBB";

    private static final String DEFAULT_MODIFIER_3 = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIER_3 = "BBBBBBBBBB";

    private static final String DEFAULT_MODIFIER_4 = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIER_4 = "BBBBBBBBBB";

    private static final String DEFAULT_ICD_POINTER = "AAAAAAAAAA";
    private static final String UPDATED_ICD_POINTER = "BBBBBBBBBB";

    private static final String DEFAULT_PROCEDURE_IDENTIFIER = "AAAAAAAAAA";
    private static final String UPDATED_PROCEDURE_IDENTIFIER = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERING_PROVIDER_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ORDERING_PROVIDER_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERING_PROVIDER_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ORDERING_PROVIDER_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERING_PROVIDER_NPI = "AAAAAAAAAA";
    private static final String UPDATED_ORDERING_PROVIDER_NPI = "BBBBBBBBBB";

    private static final String DEFAULT_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE = "BBBBBBBBBB";

    private static final String DEFAULT_PROC_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PROC_CODE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/so-rental-helpers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{soRentalHelperId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SoRentalHelperRepository soRentalHelperRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private SoRentalHelper soRentalHelper;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SoRentalHelper createEntity(EntityManager em) {
        SoRentalHelper soRentalHelper = new SoRentalHelper()
            .soId(DEFAULT_SO_ID)
            .primaryInsurerId(DEFAULT_PRIMARY_INSURER_ID)
            .primaryInsurerName(DEFAULT_PRIMARY_INSURER_NAME)
            .itemId(DEFAULT_ITEM_ID)
            .itemNo(DEFAULT_ITEM_NO)
            .itemName(DEFAULT_ITEM_NAME)
            .chargedAmount(DEFAULT_CHARGED_AMOUNT)
            .allowedAmount(DEFAULT_ALLOWED_AMOUNT)
            .sou(DEFAULT_SOU)
            .qty(DEFAULT_QTY)
            .dosStart(DEFAULT_DOS_START)
            .dosEnd(DEFAULT_DOS_END)
            .periodNo(DEFAULT_PERIOD_NO)
            .rentalPeriod(DEFAULT_RENTAL_PERIOD)
            .nextDos(DEFAULT_NEXT_DOS)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .soRentalHelperUuid(DEFAULT_SO_RENTAL_HELPER_UUID)
            .patientId(DEFAULT_PATIENT_ID)
            .saleType(DEFAULT_SALE_TYPE)
            .primaryInsurancePriceTableId(DEFAULT_PRIMARY_INSURANCE_PRICE_TABLE_ID)
            .primaryInsurancePriceTableName(DEFAULT_PRIMARY_INSURANCE_PRICE_TABLE_NAME)
            .modifier1(DEFAULT_MODIFIER_1)
            .modifier2(DEFAULT_MODIFIER_2)
            .modifier3(DEFAULT_MODIFIER_3)
            .modifier4(DEFAULT_MODIFIER_4)
            .icdPointer(DEFAULT_ICD_POINTER)
            .procedureIdentifier(DEFAULT_PROCEDURE_IDENTIFIER)
            .orderingProviderFirstName(DEFAULT_ORDERING_PROVIDER_FIRST_NAME)
            .orderingProviderLastName(DEFAULT_ORDERING_PROVIDER_LAST_NAME)
            .orderingProviderNpi(DEFAULT_ORDERING_PROVIDER_NPI)
            .reference(DEFAULT_REFERENCE)
            .procCode(DEFAULT_PROC_CODE);
        return soRentalHelper;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SoRentalHelper createUpdatedEntity(EntityManager em) {
        SoRentalHelper soRentalHelper = new SoRentalHelper()
            .soId(UPDATED_SO_ID)
            .primaryInsurerId(UPDATED_PRIMARY_INSURER_ID)
            .primaryInsurerName(UPDATED_PRIMARY_INSURER_NAME)
            .itemId(UPDATED_ITEM_ID)
            .itemNo(UPDATED_ITEM_NO)
            .itemName(UPDATED_ITEM_NAME)
            .chargedAmount(UPDATED_CHARGED_AMOUNT)
            .allowedAmount(UPDATED_ALLOWED_AMOUNT)
            .sou(UPDATED_SOU)
            .qty(UPDATED_QTY)
            .dosStart(UPDATED_DOS_START)
            .dosEnd(UPDATED_DOS_END)
            .periodNo(UPDATED_PERIOD_NO)
            .rentalPeriod(UPDATED_RENTAL_PERIOD)
            .nextDos(UPDATED_NEXT_DOS)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .soRentalHelperUuid(UPDATED_SO_RENTAL_HELPER_UUID)
            .patientId(UPDATED_PATIENT_ID)
            .saleType(UPDATED_SALE_TYPE)
            .primaryInsurancePriceTableId(UPDATED_PRIMARY_INSURANCE_PRICE_TABLE_ID)
            .primaryInsurancePriceTableName(UPDATED_PRIMARY_INSURANCE_PRICE_TABLE_NAME)
            .modifier1(UPDATED_MODIFIER_1)
            .modifier2(UPDATED_MODIFIER_2)
            .modifier3(UPDATED_MODIFIER_3)
            .modifier4(UPDATED_MODIFIER_4)
            .icdPointer(UPDATED_ICD_POINTER)
            .procedureIdentifier(UPDATED_PROCEDURE_IDENTIFIER)
            .orderingProviderFirstName(UPDATED_ORDERING_PROVIDER_FIRST_NAME)
            .orderingProviderLastName(UPDATED_ORDERING_PROVIDER_LAST_NAME)
            .orderingProviderNpi(UPDATED_ORDERING_PROVIDER_NPI)
            .reference(UPDATED_REFERENCE)
            .procCode(UPDATED_PROC_CODE);
        return soRentalHelper;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(SoRentalHelper.class).block();
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
        soRentalHelper = createEntity(em);
    }

    @Test
    void createSoRentalHelper() throws Exception {
        int databaseSizeBeforeCreate = soRentalHelperRepository.findAll().collectList().block().size();
        // Create the SoRentalHelper
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(soRentalHelper))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the SoRentalHelper in the database
        List<SoRentalHelper> soRentalHelperList = soRentalHelperRepository.findAll().collectList().block();
        assertThat(soRentalHelperList).hasSize(databaseSizeBeforeCreate + 1);
        SoRentalHelper testSoRentalHelper = soRentalHelperList.get(soRentalHelperList.size() - 1);
        assertThat(testSoRentalHelper.getSoId()).isEqualTo(DEFAULT_SO_ID);
        assertThat(testSoRentalHelper.getPrimaryInsurerId()).isEqualTo(DEFAULT_PRIMARY_INSURER_ID);
        assertThat(testSoRentalHelper.getPrimaryInsurerName()).isEqualTo(DEFAULT_PRIMARY_INSURER_NAME);
        assertThat(testSoRentalHelper.getItemId()).isEqualTo(DEFAULT_ITEM_ID);
        assertThat(testSoRentalHelper.getItemNo()).isEqualTo(DEFAULT_ITEM_NO);
        assertThat(testSoRentalHelper.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testSoRentalHelper.getChargedAmount()).isEqualTo(DEFAULT_CHARGED_AMOUNT);
        assertThat(testSoRentalHelper.getAllowedAmount()).isEqualTo(DEFAULT_ALLOWED_AMOUNT);
        assertThat(testSoRentalHelper.getSou()).isEqualTo(DEFAULT_SOU);
        assertThat(testSoRentalHelper.getQty()).isEqualTo(DEFAULT_QTY);
        assertThat(testSoRentalHelper.getDosStart()).isEqualTo(DEFAULT_DOS_START);
        assertThat(testSoRentalHelper.getDosEnd()).isEqualTo(DEFAULT_DOS_END);
        assertThat(testSoRentalHelper.getPeriodNo()).isEqualTo(DEFAULT_PERIOD_NO);
        assertThat(testSoRentalHelper.getRentalPeriod()).isEqualTo(DEFAULT_RENTAL_PERIOD);
        assertThat(testSoRentalHelper.getNextDos()).isEqualTo(DEFAULT_NEXT_DOS);
        assertThat(testSoRentalHelper.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSoRentalHelper.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testSoRentalHelper.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testSoRentalHelper.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testSoRentalHelper.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testSoRentalHelper.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testSoRentalHelper.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testSoRentalHelper.getSoRentalHelperUuid()).isEqualTo(DEFAULT_SO_RENTAL_HELPER_UUID);
        assertThat(testSoRentalHelper.getPatientId()).isEqualTo(DEFAULT_PATIENT_ID);
        assertThat(testSoRentalHelper.getSaleType()).isEqualTo(DEFAULT_SALE_TYPE);
        assertThat(testSoRentalHelper.getPrimaryInsurancePriceTableId()).isEqualTo(DEFAULT_PRIMARY_INSURANCE_PRICE_TABLE_ID);
        assertThat(testSoRentalHelper.getPrimaryInsurancePriceTableName()).isEqualTo(DEFAULT_PRIMARY_INSURANCE_PRICE_TABLE_NAME);
        assertThat(testSoRentalHelper.getModifier1()).isEqualTo(DEFAULT_MODIFIER_1);
        assertThat(testSoRentalHelper.getModifier2()).isEqualTo(DEFAULT_MODIFIER_2);
        assertThat(testSoRentalHelper.getModifier3()).isEqualTo(DEFAULT_MODIFIER_3);
        assertThat(testSoRentalHelper.getModifier4()).isEqualTo(DEFAULT_MODIFIER_4);
        assertThat(testSoRentalHelper.getIcdPointer()).isEqualTo(DEFAULT_ICD_POINTER);
        assertThat(testSoRentalHelper.getProcedureIdentifier()).isEqualTo(DEFAULT_PROCEDURE_IDENTIFIER);
        assertThat(testSoRentalHelper.getOrderingProviderFirstName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testSoRentalHelper.getOrderingProviderLastName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testSoRentalHelper.getOrderingProviderNpi()).isEqualTo(DEFAULT_ORDERING_PROVIDER_NPI);
        assertThat(testSoRentalHelper.getReference()).isEqualTo(DEFAULT_REFERENCE);
        assertThat(testSoRentalHelper.getProcCode()).isEqualTo(DEFAULT_PROC_CODE);
    }

    @Test
    void createSoRentalHelperWithExistingId() throws Exception {
        // Create the SoRentalHelper with an existing ID
        soRentalHelper.setSoRentalHelperId(1L);

        int databaseSizeBeforeCreate = soRentalHelperRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(soRentalHelper))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SoRentalHelper in the database
        List<SoRentalHelper> soRentalHelperList = soRentalHelperRepository.findAll().collectList().block();
        assertThat(soRentalHelperList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllSoRentalHelpersAsStream() {
        // Initialize the database
        soRentalHelperRepository.save(soRentalHelper).block();

        List<SoRentalHelper> soRentalHelperList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(SoRentalHelper.class)
            .getResponseBody()
            .filter(soRentalHelper::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(soRentalHelperList).isNotNull();
        assertThat(soRentalHelperList).hasSize(1);
        SoRentalHelper testSoRentalHelper = soRentalHelperList.get(0);
        assertThat(testSoRentalHelper.getSoId()).isEqualTo(DEFAULT_SO_ID);
        assertThat(testSoRentalHelper.getPrimaryInsurerId()).isEqualTo(DEFAULT_PRIMARY_INSURER_ID);
        assertThat(testSoRentalHelper.getPrimaryInsurerName()).isEqualTo(DEFAULT_PRIMARY_INSURER_NAME);
        assertThat(testSoRentalHelper.getItemId()).isEqualTo(DEFAULT_ITEM_ID);
        assertThat(testSoRentalHelper.getItemNo()).isEqualTo(DEFAULT_ITEM_NO);
        assertThat(testSoRentalHelper.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testSoRentalHelper.getChargedAmount()).isEqualTo(DEFAULT_CHARGED_AMOUNT);
        assertThat(testSoRentalHelper.getAllowedAmount()).isEqualTo(DEFAULT_ALLOWED_AMOUNT);
        assertThat(testSoRentalHelper.getSou()).isEqualTo(DEFAULT_SOU);
        assertThat(testSoRentalHelper.getQty()).isEqualTo(DEFAULT_QTY);
        assertThat(testSoRentalHelper.getDosStart()).isEqualTo(DEFAULT_DOS_START);
        assertThat(testSoRentalHelper.getDosEnd()).isEqualTo(DEFAULT_DOS_END);
        assertThat(testSoRentalHelper.getPeriodNo()).isEqualTo(DEFAULT_PERIOD_NO);
        assertThat(testSoRentalHelper.getRentalPeriod()).isEqualTo(DEFAULT_RENTAL_PERIOD);
        assertThat(testSoRentalHelper.getNextDos()).isEqualTo(DEFAULT_NEXT_DOS);
        assertThat(testSoRentalHelper.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSoRentalHelper.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testSoRentalHelper.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testSoRentalHelper.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testSoRentalHelper.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testSoRentalHelper.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testSoRentalHelper.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testSoRentalHelper.getSoRentalHelperUuid()).isEqualTo(DEFAULT_SO_RENTAL_HELPER_UUID);
        assertThat(testSoRentalHelper.getPatientId()).isEqualTo(DEFAULT_PATIENT_ID);
        assertThat(testSoRentalHelper.getSaleType()).isEqualTo(DEFAULT_SALE_TYPE);
        assertThat(testSoRentalHelper.getPrimaryInsurancePriceTableId()).isEqualTo(DEFAULT_PRIMARY_INSURANCE_PRICE_TABLE_ID);
        assertThat(testSoRentalHelper.getPrimaryInsurancePriceTableName()).isEqualTo(DEFAULT_PRIMARY_INSURANCE_PRICE_TABLE_NAME);
        assertThat(testSoRentalHelper.getModifier1()).isEqualTo(DEFAULT_MODIFIER_1);
        assertThat(testSoRentalHelper.getModifier2()).isEqualTo(DEFAULT_MODIFIER_2);
        assertThat(testSoRentalHelper.getModifier3()).isEqualTo(DEFAULT_MODIFIER_3);
        assertThat(testSoRentalHelper.getModifier4()).isEqualTo(DEFAULT_MODIFIER_4);
        assertThat(testSoRentalHelper.getIcdPointer()).isEqualTo(DEFAULT_ICD_POINTER);
        assertThat(testSoRentalHelper.getProcedureIdentifier()).isEqualTo(DEFAULT_PROCEDURE_IDENTIFIER);
        assertThat(testSoRentalHelper.getOrderingProviderFirstName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testSoRentalHelper.getOrderingProviderLastName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testSoRentalHelper.getOrderingProviderNpi()).isEqualTo(DEFAULT_ORDERING_PROVIDER_NPI);
        assertThat(testSoRentalHelper.getReference()).isEqualTo(DEFAULT_REFERENCE);
        assertThat(testSoRentalHelper.getProcCode()).isEqualTo(DEFAULT_PROC_CODE);
    }

    @Test
    void getAllSoRentalHelpers() {
        // Initialize the database
        soRentalHelperRepository.save(soRentalHelper).block();

        // Get all the soRentalHelperList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=soRentalHelperId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].soRentalHelperId")
            .value(hasItem(soRentalHelper.getSoRentalHelperId().intValue()))
            .jsonPath("$.[*].soId")
            .value(hasItem(DEFAULT_SO_ID.intValue()))
            .jsonPath("$.[*].primaryInsurerId")
            .value(hasItem(DEFAULT_PRIMARY_INSURER_ID.intValue()))
            .jsonPath("$.[*].primaryInsurerName")
            .value(hasItem(DEFAULT_PRIMARY_INSURER_NAME))
            .jsonPath("$.[*].itemId")
            .value(hasItem(DEFAULT_ITEM_ID.intValue()))
            .jsonPath("$.[*].itemNo")
            .value(hasItem(DEFAULT_ITEM_NO))
            .jsonPath("$.[*].itemName")
            .value(hasItem(DEFAULT_ITEM_NAME))
            .jsonPath("$.[*].chargedAmount")
            .value(hasItem(DEFAULT_CHARGED_AMOUNT.doubleValue()))
            .jsonPath("$.[*].allowedAmount")
            .value(hasItem(DEFAULT_ALLOWED_AMOUNT.doubleValue()))
            .jsonPath("$.[*].sou")
            .value(hasItem(DEFAULT_SOU))
            .jsonPath("$.[*].qty")
            .value(hasItem(DEFAULT_QTY.doubleValue()))
            .jsonPath("$.[*].dosStart")
            .value(hasItem(DEFAULT_DOS_START.toString()))
            .jsonPath("$.[*].dosEnd")
            .value(hasItem(DEFAULT_DOS_END.toString()))
            .jsonPath("$.[*].periodNo")
            .value(hasItem(DEFAULT_PERIOD_NO))
            .jsonPath("$.[*].rentalPeriod")
            .value(hasItem(DEFAULT_RENTAL_PERIOD))
            .jsonPath("$.[*].nextDos")
            .value(hasItem(DEFAULT_NEXT_DOS.toString()))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS))
            .jsonPath("$.[*].createdById")
            .value(hasItem(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.[*].createdDate")
            .value(hasItem(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.[*].createdByName")
            .value(hasItem(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.[*].updatedById")
            .value(hasItem(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.[*].updatedDate")
            .value(hasItem(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.[*].updatedByName")
            .value(hasItem(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.[*].soRentalHelperUuid")
            .value(hasItem(DEFAULT_SO_RENTAL_HELPER_UUID.toString()))
            .jsonPath("$.[*].patientId")
            .value(hasItem(DEFAULT_PATIENT_ID.intValue()))
            .jsonPath("$.[*].saleType")
            .value(hasItem(DEFAULT_SALE_TYPE))
            .jsonPath("$.[*].primaryInsurancePriceTableId")
            .value(hasItem(DEFAULT_PRIMARY_INSURANCE_PRICE_TABLE_ID.intValue()))
            .jsonPath("$.[*].primaryInsurancePriceTableName")
            .value(hasItem(DEFAULT_PRIMARY_INSURANCE_PRICE_TABLE_NAME))
            .jsonPath("$.[*].modifier1")
            .value(hasItem(DEFAULT_MODIFIER_1))
            .jsonPath("$.[*].modifier2")
            .value(hasItem(DEFAULT_MODIFIER_2))
            .jsonPath("$.[*].modifier3")
            .value(hasItem(DEFAULT_MODIFIER_3))
            .jsonPath("$.[*].modifier4")
            .value(hasItem(DEFAULT_MODIFIER_4))
            .jsonPath("$.[*].icdPointer")
            .value(hasItem(DEFAULT_ICD_POINTER))
            .jsonPath("$.[*].procedureIdentifier")
            .value(hasItem(DEFAULT_PROCEDURE_IDENTIFIER))
            .jsonPath("$.[*].orderingProviderFirstName")
            .value(hasItem(DEFAULT_ORDERING_PROVIDER_FIRST_NAME))
            .jsonPath("$.[*].orderingProviderLastName")
            .value(hasItem(DEFAULT_ORDERING_PROVIDER_LAST_NAME))
            .jsonPath("$.[*].orderingProviderNpi")
            .value(hasItem(DEFAULT_ORDERING_PROVIDER_NPI))
            .jsonPath("$.[*].reference")
            .value(hasItem(DEFAULT_REFERENCE))
            .jsonPath("$.[*].procCode")
            .value(hasItem(DEFAULT_PROC_CODE));
    }

    @Test
    void getSoRentalHelper() {
        // Initialize the database
        soRentalHelperRepository.save(soRentalHelper).block();

        // Get the soRentalHelper
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, soRentalHelper.getSoRentalHelperId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.soRentalHelperId")
            .value(is(soRentalHelper.getSoRentalHelperId().intValue()))
            .jsonPath("$.soId")
            .value(is(DEFAULT_SO_ID.intValue()))
            .jsonPath("$.primaryInsurerId")
            .value(is(DEFAULT_PRIMARY_INSURER_ID.intValue()))
            .jsonPath("$.primaryInsurerName")
            .value(is(DEFAULT_PRIMARY_INSURER_NAME))
            .jsonPath("$.itemId")
            .value(is(DEFAULT_ITEM_ID.intValue()))
            .jsonPath("$.itemNo")
            .value(is(DEFAULT_ITEM_NO))
            .jsonPath("$.itemName")
            .value(is(DEFAULT_ITEM_NAME))
            .jsonPath("$.chargedAmount")
            .value(is(DEFAULT_CHARGED_AMOUNT.doubleValue()))
            .jsonPath("$.allowedAmount")
            .value(is(DEFAULT_ALLOWED_AMOUNT.doubleValue()))
            .jsonPath("$.sou")
            .value(is(DEFAULT_SOU))
            .jsonPath("$.qty")
            .value(is(DEFAULT_QTY.doubleValue()))
            .jsonPath("$.dosStart")
            .value(is(DEFAULT_DOS_START.toString()))
            .jsonPath("$.dosEnd")
            .value(is(DEFAULT_DOS_END.toString()))
            .jsonPath("$.periodNo")
            .value(is(DEFAULT_PERIOD_NO))
            .jsonPath("$.rentalPeriod")
            .value(is(DEFAULT_RENTAL_PERIOD))
            .jsonPath("$.nextDos")
            .value(is(DEFAULT_NEXT_DOS.toString()))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS))
            .jsonPath("$.createdById")
            .value(is(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.createdDate")
            .value(is(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.createdByName")
            .value(is(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.updatedById")
            .value(is(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.updatedDate")
            .value(is(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.updatedByName")
            .value(is(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.soRentalHelperUuid")
            .value(is(DEFAULT_SO_RENTAL_HELPER_UUID.toString()))
            .jsonPath("$.patientId")
            .value(is(DEFAULT_PATIENT_ID.intValue()))
            .jsonPath("$.saleType")
            .value(is(DEFAULT_SALE_TYPE))
            .jsonPath("$.primaryInsurancePriceTableId")
            .value(is(DEFAULT_PRIMARY_INSURANCE_PRICE_TABLE_ID.intValue()))
            .jsonPath("$.primaryInsurancePriceTableName")
            .value(is(DEFAULT_PRIMARY_INSURANCE_PRICE_TABLE_NAME))
            .jsonPath("$.modifier1")
            .value(is(DEFAULT_MODIFIER_1))
            .jsonPath("$.modifier2")
            .value(is(DEFAULT_MODIFIER_2))
            .jsonPath("$.modifier3")
            .value(is(DEFAULT_MODIFIER_3))
            .jsonPath("$.modifier4")
            .value(is(DEFAULT_MODIFIER_4))
            .jsonPath("$.icdPointer")
            .value(is(DEFAULT_ICD_POINTER))
            .jsonPath("$.procedureIdentifier")
            .value(is(DEFAULT_PROCEDURE_IDENTIFIER))
            .jsonPath("$.orderingProviderFirstName")
            .value(is(DEFAULT_ORDERING_PROVIDER_FIRST_NAME))
            .jsonPath("$.orderingProviderLastName")
            .value(is(DEFAULT_ORDERING_PROVIDER_LAST_NAME))
            .jsonPath("$.orderingProviderNpi")
            .value(is(DEFAULT_ORDERING_PROVIDER_NPI))
            .jsonPath("$.reference")
            .value(is(DEFAULT_REFERENCE))
            .jsonPath("$.procCode")
            .value(is(DEFAULT_PROC_CODE));
    }

    @Test
    void getNonExistingSoRentalHelper() {
        // Get the soRentalHelper
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingSoRentalHelper() throws Exception {
        // Initialize the database
        soRentalHelperRepository.save(soRentalHelper).block();

        int databaseSizeBeforeUpdate = soRentalHelperRepository.findAll().collectList().block().size();

        // Update the soRentalHelper
        SoRentalHelper updatedSoRentalHelper = soRentalHelperRepository.findById(soRentalHelper.getSoRentalHelperId()).block();
        updatedSoRentalHelper
            .soId(UPDATED_SO_ID)
            .primaryInsurerId(UPDATED_PRIMARY_INSURER_ID)
            .primaryInsurerName(UPDATED_PRIMARY_INSURER_NAME)
            .itemId(UPDATED_ITEM_ID)
            .itemNo(UPDATED_ITEM_NO)
            .itemName(UPDATED_ITEM_NAME)
            .chargedAmount(UPDATED_CHARGED_AMOUNT)
            .allowedAmount(UPDATED_ALLOWED_AMOUNT)
            .sou(UPDATED_SOU)
            .qty(UPDATED_QTY)
            .dosStart(UPDATED_DOS_START)
            .dosEnd(UPDATED_DOS_END)
            .periodNo(UPDATED_PERIOD_NO)
            .rentalPeriod(UPDATED_RENTAL_PERIOD)
            .nextDos(UPDATED_NEXT_DOS)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .soRentalHelperUuid(UPDATED_SO_RENTAL_HELPER_UUID)
            .patientId(UPDATED_PATIENT_ID)
            .saleType(UPDATED_SALE_TYPE)
            .primaryInsurancePriceTableId(UPDATED_PRIMARY_INSURANCE_PRICE_TABLE_ID)
            .primaryInsurancePriceTableName(UPDATED_PRIMARY_INSURANCE_PRICE_TABLE_NAME)
            .modifier1(UPDATED_MODIFIER_1)
            .modifier2(UPDATED_MODIFIER_2)
            .modifier3(UPDATED_MODIFIER_3)
            .modifier4(UPDATED_MODIFIER_4)
            .icdPointer(UPDATED_ICD_POINTER)
            .procedureIdentifier(UPDATED_PROCEDURE_IDENTIFIER)
            .orderingProviderFirstName(UPDATED_ORDERING_PROVIDER_FIRST_NAME)
            .orderingProviderLastName(UPDATED_ORDERING_PROVIDER_LAST_NAME)
            .orderingProviderNpi(UPDATED_ORDERING_PROVIDER_NPI)
            .reference(UPDATED_REFERENCE)
            .procCode(UPDATED_PROC_CODE);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedSoRentalHelper.getSoRentalHelperId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedSoRentalHelper))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SoRentalHelper in the database
        List<SoRentalHelper> soRentalHelperList = soRentalHelperRepository.findAll().collectList().block();
        assertThat(soRentalHelperList).hasSize(databaseSizeBeforeUpdate);
        SoRentalHelper testSoRentalHelper = soRentalHelperList.get(soRentalHelperList.size() - 1);
        assertThat(testSoRentalHelper.getSoId()).isEqualTo(UPDATED_SO_ID);
        assertThat(testSoRentalHelper.getPrimaryInsurerId()).isEqualTo(UPDATED_PRIMARY_INSURER_ID);
        assertThat(testSoRentalHelper.getPrimaryInsurerName()).isEqualTo(UPDATED_PRIMARY_INSURER_NAME);
        assertThat(testSoRentalHelper.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testSoRentalHelper.getItemNo()).isEqualTo(UPDATED_ITEM_NO);
        assertThat(testSoRentalHelper.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testSoRentalHelper.getChargedAmount()).isEqualTo(UPDATED_CHARGED_AMOUNT);
        assertThat(testSoRentalHelper.getAllowedAmount()).isEqualTo(UPDATED_ALLOWED_AMOUNT);
        assertThat(testSoRentalHelper.getSou()).isEqualTo(UPDATED_SOU);
        assertThat(testSoRentalHelper.getQty()).isEqualTo(UPDATED_QTY);
        assertThat(testSoRentalHelper.getDosStart()).isEqualTo(UPDATED_DOS_START);
        assertThat(testSoRentalHelper.getDosEnd()).isEqualTo(UPDATED_DOS_END);
        assertThat(testSoRentalHelper.getPeriodNo()).isEqualTo(UPDATED_PERIOD_NO);
        assertThat(testSoRentalHelper.getRentalPeriod()).isEqualTo(UPDATED_RENTAL_PERIOD);
        assertThat(testSoRentalHelper.getNextDos()).isEqualTo(UPDATED_NEXT_DOS);
        assertThat(testSoRentalHelper.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSoRentalHelper.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testSoRentalHelper.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSoRentalHelper.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testSoRentalHelper.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testSoRentalHelper.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testSoRentalHelper.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testSoRentalHelper.getSoRentalHelperUuid()).isEqualTo(UPDATED_SO_RENTAL_HELPER_UUID);
        assertThat(testSoRentalHelper.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testSoRentalHelper.getSaleType()).isEqualTo(UPDATED_SALE_TYPE);
        assertThat(testSoRentalHelper.getPrimaryInsurancePriceTableId()).isEqualTo(UPDATED_PRIMARY_INSURANCE_PRICE_TABLE_ID);
        assertThat(testSoRentalHelper.getPrimaryInsurancePriceTableName()).isEqualTo(UPDATED_PRIMARY_INSURANCE_PRICE_TABLE_NAME);
        assertThat(testSoRentalHelper.getModifier1()).isEqualTo(UPDATED_MODIFIER_1);
        assertThat(testSoRentalHelper.getModifier2()).isEqualTo(UPDATED_MODIFIER_2);
        assertThat(testSoRentalHelper.getModifier3()).isEqualTo(UPDATED_MODIFIER_3);
        assertThat(testSoRentalHelper.getModifier4()).isEqualTo(UPDATED_MODIFIER_4);
        assertThat(testSoRentalHelper.getIcdPointer()).isEqualTo(UPDATED_ICD_POINTER);
        assertThat(testSoRentalHelper.getProcedureIdentifier()).isEqualTo(UPDATED_PROCEDURE_IDENTIFIER);
        assertThat(testSoRentalHelper.getOrderingProviderFirstName()).isEqualTo(UPDATED_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testSoRentalHelper.getOrderingProviderLastName()).isEqualTo(UPDATED_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testSoRentalHelper.getOrderingProviderNpi()).isEqualTo(UPDATED_ORDERING_PROVIDER_NPI);
        assertThat(testSoRentalHelper.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testSoRentalHelper.getProcCode()).isEqualTo(UPDATED_PROC_CODE);
    }

    @Test
    void putNonExistingSoRentalHelper() throws Exception {
        int databaseSizeBeforeUpdate = soRentalHelperRepository.findAll().collectList().block().size();
        soRentalHelper.setSoRentalHelperId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, soRentalHelper.getSoRentalHelperId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(soRentalHelper))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SoRentalHelper in the database
        List<SoRentalHelper> soRentalHelperList = soRentalHelperRepository.findAll().collectList().block();
        assertThat(soRentalHelperList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchSoRentalHelper() throws Exception {
        int databaseSizeBeforeUpdate = soRentalHelperRepository.findAll().collectList().block().size();
        soRentalHelper.setSoRentalHelperId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(soRentalHelper))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SoRentalHelper in the database
        List<SoRentalHelper> soRentalHelperList = soRentalHelperRepository.findAll().collectList().block();
        assertThat(soRentalHelperList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamSoRentalHelper() throws Exception {
        int databaseSizeBeforeUpdate = soRentalHelperRepository.findAll().collectList().block().size();
        soRentalHelper.setSoRentalHelperId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(soRentalHelper))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the SoRentalHelper in the database
        List<SoRentalHelper> soRentalHelperList = soRentalHelperRepository.findAll().collectList().block();
        assertThat(soRentalHelperList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateSoRentalHelperWithPatch() throws Exception {
        // Initialize the database
        soRentalHelperRepository.save(soRentalHelper).block();

        int databaseSizeBeforeUpdate = soRentalHelperRepository.findAll().collectList().block().size();

        // Update the soRentalHelper using partial update
        SoRentalHelper partialUpdatedSoRentalHelper = new SoRentalHelper();
        partialUpdatedSoRentalHelper.setSoRentalHelperId(soRentalHelper.getSoRentalHelperId());

        partialUpdatedSoRentalHelper
            .itemNo(UPDATED_ITEM_NO)
            .sou(UPDATED_SOU)
            .dosStart(UPDATED_DOS_START)
            .dosEnd(UPDATED_DOS_END)
            .nextDos(UPDATED_NEXT_DOS)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .patientId(UPDATED_PATIENT_ID)
            .primaryInsurancePriceTableId(UPDATED_PRIMARY_INSURANCE_PRICE_TABLE_ID)
            .primaryInsurancePriceTableName(UPDATED_PRIMARY_INSURANCE_PRICE_TABLE_NAME)
            .modifier2(UPDATED_MODIFIER_2)
            .modifier3(UPDATED_MODIFIER_3)
            .modifier4(UPDATED_MODIFIER_4)
            .orderingProviderNpi(UPDATED_ORDERING_PROVIDER_NPI);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedSoRentalHelper.getSoRentalHelperId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedSoRentalHelper))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SoRentalHelper in the database
        List<SoRentalHelper> soRentalHelperList = soRentalHelperRepository.findAll().collectList().block();
        assertThat(soRentalHelperList).hasSize(databaseSizeBeforeUpdate);
        SoRentalHelper testSoRentalHelper = soRentalHelperList.get(soRentalHelperList.size() - 1);
        assertThat(testSoRentalHelper.getSoId()).isEqualTo(DEFAULT_SO_ID);
        assertThat(testSoRentalHelper.getPrimaryInsurerId()).isEqualTo(DEFAULT_PRIMARY_INSURER_ID);
        assertThat(testSoRentalHelper.getPrimaryInsurerName()).isEqualTo(DEFAULT_PRIMARY_INSURER_NAME);
        assertThat(testSoRentalHelper.getItemId()).isEqualTo(DEFAULT_ITEM_ID);
        assertThat(testSoRentalHelper.getItemNo()).isEqualTo(UPDATED_ITEM_NO);
        assertThat(testSoRentalHelper.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testSoRentalHelper.getChargedAmount()).isEqualTo(DEFAULT_CHARGED_AMOUNT);
        assertThat(testSoRentalHelper.getAllowedAmount()).isEqualTo(DEFAULT_ALLOWED_AMOUNT);
        assertThat(testSoRentalHelper.getSou()).isEqualTo(UPDATED_SOU);
        assertThat(testSoRentalHelper.getQty()).isEqualTo(DEFAULT_QTY);
        assertThat(testSoRentalHelper.getDosStart()).isEqualTo(UPDATED_DOS_START);
        assertThat(testSoRentalHelper.getDosEnd()).isEqualTo(UPDATED_DOS_END);
        assertThat(testSoRentalHelper.getPeriodNo()).isEqualTo(DEFAULT_PERIOD_NO);
        assertThat(testSoRentalHelper.getRentalPeriod()).isEqualTo(DEFAULT_RENTAL_PERIOD);
        assertThat(testSoRentalHelper.getNextDos()).isEqualTo(UPDATED_NEXT_DOS);
        assertThat(testSoRentalHelper.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSoRentalHelper.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testSoRentalHelper.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSoRentalHelper.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testSoRentalHelper.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testSoRentalHelper.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testSoRentalHelper.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testSoRentalHelper.getSoRentalHelperUuid()).isEqualTo(DEFAULT_SO_RENTAL_HELPER_UUID);
        assertThat(testSoRentalHelper.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testSoRentalHelper.getSaleType()).isEqualTo(DEFAULT_SALE_TYPE);
        assertThat(testSoRentalHelper.getPrimaryInsurancePriceTableId()).isEqualTo(UPDATED_PRIMARY_INSURANCE_PRICE_TABLE_ID);
        assertThat(testSoRentalHelper.getPrimaryInsurancePriceTableName()).isEqualTo(UPDATED_PRIMARY_INSURANCE_PRICE_TABLE_NAME);
        assertThat(testSoRentalHelper.getModifier1()).isEqualTo(DEFAULT_MODIFIER_1);
        assertThat(testSoRentalHelper.getModifier2()).isEqualTo(UPDATED_MODIFIER_2);
        assertThat(testSoRentalHelper.getModifier3()).isEqualTo(UPDATED_MODIFIER_3);
        assertThat(testSoRentalHelper.getModifier4()).isEqualTo(UPDATED_MODIFIER_4);
        assertThat(testSoRentalHelper.getIcdPointer()).isEqualTo(DEFAULT_ICD_POINTER);
        assertThat(testSoRentalHelper.getProcedureIdentifier()).isEqualTo(DEFAULT_PROCEDURE_IDENTIFIER);
        assertThat(testSoRentalHelper.getOrderingProviderFirstName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testSoRentalHelper.getOrderingProviderLastName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testSoRentalHelper.getOrderingProviderNpi()).isEqualTo(UPDATED_ORDERING_PROVIDER_NPI);
        assertThat(testSoRentalHelper.getReference()).isEqualTo(DEFAULT_REFERENCE);
        assertThat(testSoRentalHelper.getProcCode()).isEqualTo(DEFAULT_PROC_CODE);
    }

    @Test
    void fullUpdateSoRentalHelperWithPatch() throws Exception {
        // Initialize the database
        soRentalHelperRepository.save(soRentalHelper).block();

        int databaseSizeBeforeUpdate = soRentalHelperRepository.findAll().collectList().block().size();

        // Update the soRentalHelper using partial update
        SoRentalHelper partialUpdatedSoRentalHelper = new SoRentalHelper();
        partialUpdatedSoRentalHelper.setSoRentalHelperId(soRentalHelper.getSoRentalHelperId());

        partialUpdatedSoRentalHelper
            .soId(UPDATED_SO_ID)
            .primaryInsurerId(UPDATED_PRIMARY_INSURER_ID)
            .primaryInsurerName(UPDATED_PRIMARY_INSURER_NAME)
            .itemId(UPDATED_ITEM_ID)
            .itemNo(UPDATED_ITEM_NO)
            .itemName(UPDATED_ITEM_NAME)
            .chargedAmount(UPDATED_CHARGED_AMOUNT)
            .allowedAmount(UPDATED_ALLOWED_AMOUNT)
            .sou(UPDATED_SOU)
            .qty(UPDATED_QTY)
            .dosStart(UPDATED_DOS_START)
            .dosEnd(UPDATED_DOS_END)
            .periodNo(UPDATED_PERIOD_NO)
            .rentalPeriod(UPDATED_RENTAL_PERIOD)
            .nextDos(UPDATED_NEXT_DOS)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .soRentalHelperUuid(UPDATED_SO_RENTAL_HELPER_UUID)
            .patientId(UPDATED_PATIENT_ID)
            .saleType(UPDATED_SALE_TYPE)
            .primaryInsurancePriceTableId(UPDATED_PRIMARY_INSURANCE_PRICE_TABLE_ID)
            .primaryInsurancePriceTableName(UPDATED_PRIMARY_INSURANCE_PRICE_TABLE_NAME)
            .modifier1(UPDATED_MODIFIER_1)
            .modifier2(UPDATED_MODIFIER_2)
            .modifier3(UPDATED_MODIFIER_3)
            .modifier4(UPDATED_MODIFIER_4)
            .icdPointer(UPDATED_ICD_POINTER)
            .procedureIdentifier(UPDATED_PROCEDURE_IDENTIFIER)
            .orderingProviderFirstName(UPDATED_ORDERING_PROVIDER_FIRST_NAME)
            .orderingProviderLastName(UPDATED_ORDERING_PROVIDER_LAST_NAME)
            .orderingProviderNpi(UPDATED_ORDERING_PROVIDER_NPI)
            .reference(UPDATED_REFERENCE)
            .procCode(UPDATED_PROC_CODE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedSoRentalHelper.getSoRentalHelperId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedSoRentalHelper))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SoRentalHelper in the database
        List<SoRentalHelper> soRentalHelperList = soRentalHelperRepository.findAll().collectList().block();
        assertThat(soRentalHelperList).hasSize(databaseSizeBeforeUpdate);
        SoRentalHelper testSoRentalHelper = soRentalHelperList.get(soRentalHelperList.size() - 1);
        assertThat(testSoRentalHelper.getSoId()).isEqualTo(UPDATED_SO_ID);
        assertThat(testSoRentalHelper.getPrimaryInsurerId()).isEqualTo(UPDATED_PRIMARY_INSURER_ID);
        assertThat(testSoRentalHelper.getPrimaryInsurerName()).isEqualTo(UPDATED_PRIMARY_INSURER_NAME);
        assertThat(testSoRentalHelper.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testSoRentalHelper.getItemNo()).isEqualTo(UPDATED_ITEM_NO);
        assertThat(testSoRentalHelper.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testSoRentalHelper.getChargedAmount()).isEqualTo(UPDATED_CHARGED_AMOUNT);
        assertThat(testSoRentalHelper.getAllowedAmount()).isEqualTo(UPDATED_ALLOWED_AMOUNT);
        assertThat(testSoRentalHelper.getSou()).isEqualTo(UPDATED_SOU);
        assertThat(testSoRentalHelper.getQty()).isEqualTo(UPDATED_QTY);
        assertThat(testSoRentalHelper.getDosStart()).isEqualTo(UPDATED_DOS_START);
        assertThat(testSoRentalHelper.getDosEnd()).isEqualTo(UPDATED_DOS_END);
        assertThat(testSoRentalHelper.getPeriodNo()).isEqualTo(UPDATED_PERIOD_NO);
        assertThat(testSoRentalHelper.getRentalPeriod()).isEqualTo(UPDATED_RENTAL_PERIOD);
        assertThat(testSoRentalHelper.getNextDos()).isEqualTo(UPDATED_NEXT_DOS);
        assertThat(testSoRentalHelper.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSoRentalHelper.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testSoRentalHelper.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSoRentalHelper.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testSoRentalHelper.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testSoRentalHelper.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testSoRentalHelper.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testSoRentalHelper.getSoRentalHelperUuid()).isEqualTo(UPDATED_SO_RENTAL_HELPER_UUID);
        assertThat(testSoRentalHelper.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testSoRentalHelper.getSaleType()).isEqualTo(UPDATED_SALE_TYPE);
        assertThat(testSoRentalHelper.getPrimaryInsurancePriceTableId()).isEqualTo(UPDATED_PRIMARY_INSURANCE_PRICE_TABLE_ID);
        assertThat(testSoRentalHelper.getPrimaryInsurancePriceTableName()).isEqualTo(UPDATED_PRIMARY_INSURANCE_PRICE_TABLE_NAME);
        assertThat(testSoRentalHelper.getModifier1()).isEqualTo(UPDATED_MODIFIER_1);
        assertThat(testSoRentalHelper.getModifier2()).isEqualTo(UPDATED_MODIFIER_2);
        assertThat(testSoRentalHelper.getModifier3()).isEqualTo(UPDATED_MODIFIER_3);
        assertThat(testSoRentalHelper.getModifier4()).isEqualTo(UPDATED_MODIFIER_4);
        assertThat(testSoRentalHelper.getIcdPointer()).isEqualTo(UPDATED_ICD_POINTER);
        assertThat(testSoRentalHelper.getProcedureIdentifier()).isEqualTo(UPDATED_PROCEDURE_IDENTIFIER);
        assertThat(testSoRentalHelper.getOrderingProviderFirstName()).isEqualTo(UPDATED_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testSoRentalHelper.getOrderingProviderLastName()).isEqualTo(UPDATED_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testSoRentalHelper.getOrderingProviderNpi()).isEqualTo(UPDATED_ORDERING_PROVIDER_NPI);
        assertThat(testSoRentalHelper.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testSoRentalHelper.getProcCode()).isEqualTo(UPDATED_PROC_CODE);
    }

    @Test
    void patchNonExistingSoRentalHelper() throws Exception {
        int databaseSizeBeforeUpdate = soRentalHelperRepository.findAll().collectList().block().size();
        soRentalHelper.setSoRentalHelperId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, soRentalHelper.getSoRentalHelperId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(soRentalHelper))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SoRentalHelper in the database
        List<SoRentalHelper> soRentalHelperList = soRentalHelperRepository.findAll().collectList().block();
        assertThat(soRentalHelperList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchSoRentalHelper() throws Exception {
        int databaseSizeBeforeUpdate = soRentalHelperRepository.findAll().collectList().block().size();
        soRentalHelper.setSoRentalHelperId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(soRentalHelper))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SoRentalHelper in the database
        List<SoRentalHelper> soRentalHelperList = soRentalHelperRepository.findAll().collectList().block();
        assertThat(soRentalHelperList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamSoRentalHelper() throws Exception {
        int databaseSizeBeforeUpdate = soRentalHelperRepository.findAll().collectList().block().size();
        soRentalHelper.setSoRentalHelperId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(soRentalHelper))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the SoRentalHelper in the database
        List<SoRentalHelper> soRentalHelperList = soRentalHelperRepository.findAll().collectList().block();
        assertThat(soRentalHelperList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteSoRentalHelper() {
        // Initialize the database
        soRentalHelperRepository.save(soRentalHelper).block();

        int databaseSizeBeforeDelete = soRentalHelperRepository.findAll().collectList().block().size();

        // Delete the soRentalHelper
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, soRentalHelper.getSoRentalHelperId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<SoRentalHelper> soRentalHelperList = soRentalHelperRepository.findAll().collectList().block();
        assertThat(soRentalHelperList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
