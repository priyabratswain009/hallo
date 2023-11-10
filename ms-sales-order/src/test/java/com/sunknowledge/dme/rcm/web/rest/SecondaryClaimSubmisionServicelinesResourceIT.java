package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.SecondaryClaimSubmisionServicelines;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.SecondaryClaimSubmisionServicelinesRepository;
import com.sunknowledge.dme.rcm.service.dto.SecondaryClaimSubmisionServicelinesDTO;
import com.sunknowledge.dme.rcm.service.mapper.SecondaryClaimSubmisionServicelinesMapper;
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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link SecondaryClaimSubmisionServicelinesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class SecondaryClaimSubmisionServicelinesResourceIT {

    private static final Long DEFAULT_CHANGE_HEALTH_SECONDARY_SUBMISION_MASTER_ID = 1L;
    private static final Long UPDATED_CHANGE_HEALTH_SECONDARY_SUBMISION_MASTER_ID = 2L;

    private static final LocalDate DEFAULT_ORIGINAL_DOS = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ORIGINAL_DOS = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DOS_TO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DOS_TO = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_PROC_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PROC_CODE = "BBBBBBBBBB";

    private static final Double DEFAULT_CHARGE_AMT = 1D;
    private static final Double UPDATED_CHARGE_AMT = 2D;

    private static final String DEFAULT_ITEM_UOM = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_UOM = "BBBBBBBBBB";

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

    private static final Long DEFAULT_QTY = 1L;
    private static final Long UPDATED_QTY = 2L;

    private static final String DEFAULT_INSERTED_BY_ID = "AAAAAAAAAA";
    private static final String UPDATED_INSERTED_BY_ID = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_INSERTED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_INSERTED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CLAIM_SERVICELINE_CONTROL_NO = "AAAAAAAAAA";
    private static final String UPDATED_CLAIM_SERVICELINE_CONTROL_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PROCEDURE_IDENTIFIER = "AAAAAAAAAA";
    private static final String UPDATED_PROCEDURE_IDENTIFIER = "BBBBBBBBBB";

    private static final String DEFAULT_INSERTED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INSERTED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERING_PROVIDER_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ORDERING_PROVIDER_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERING_PROVIDER_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ORDERING_PROVIDER_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERING_PROVIDER_NPI = "AAAAAAAAAA";
    private static final String UPDATED_ORDERING_PROVIDER_NPI = "BBBBBBBBBB";

    private static final String DEFAULT_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE = "BBBBBBBBBB";

    private static final String DEFAULT_PAYOR_CLAIM_CONTROL_NO = "AAAAAAAAAA";
    private static final String UPDATED_PAYOR_CLAIM_CONTROL_NO = "BBBBBBBBBB";

    private static final Double DEFAULT_PROVIDER_PAYMENT_AMOUNT = 1D;
    private static final Double UPDATED_PROVIDER_PAYMENT_AMOUNT = 2D;

    private static final String DEFAULT_LINE_ADJUSTMENT = "AAAAAAAAAA";
    private static final String UPDATED_LINE_ADJUSTMENT = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY_ID = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_ID = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final UUID DEFAULT_SECONDARY_CLAIM_SUBMISION_SERVICELINES_UUID = UUID.randomUUID();
    private static final UUID UPDATED_SECONDARY_CLAIM_SUBMISION_SERVICELINES_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/secondary-claim-submision-servicelines";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{changeHealthSecondarySubmisionServicelinesId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SecondaryClaimSubmisionServicelinesRepository secondaryClaimSubmisionServicelinesRepository;

    @Autowired
    private SecondaryClaimSubmisionServicelinesMapper secondaryClaimSubmisionServicelinesMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private SecondaryClaimSubmisionServicelines secondaryClaimSubmisionServicelines;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SecondaryClaimSubmisionServicelines createEntity(EntityManager em) {
        SecondaryClaimSubmisionServicelines secondaryClaimSubmisionServicelines = new SecondaryClaimSubmisionServicelines()
            .changeHealthSecondarySubmisionMasterId(DEFAULT_CHANGE_HEALTH_SECONDARY_SUBMISION_MASTER_ID)
            .originalDos(DEFAULT_ORIGINAL_DOS)
            .dosTo(DEFAULT_DOS_TO)
            .procCode(DEFAULT_PROC_CODE)
            .chargeAmt(DEFAULT_CHARGE_AMT)
            .itemUom(DEFAULT_ITEM_UOM)
            .modifier1(DEFAULT_MODIFIER_1)
            .modifier2(DEFAULT_MODIFIER_2)
            .modifier3(DEFAULT_MODIFIER_3)
            .modifier4(DEFAULT_MODIFIER_4)
            .icdPointer(DEFAULT_ICD_POINTER)
            .qty(DEFAULT_QTY)
            .insertedById(DEFAULT_INSERTED_BY_ID)
            .insertedDate(DEFAULT_INSERTED_DATE)
            .claimServicelineControlNo(DEFAULT_CLAIM_SERVICELINE_CONTROL_NO)
            .procedureIdentifier(DEFAULT_PROCEDURE_IDENTIFIER)
            .insertedByName(DEFAULT_INSERTED_BY_NAME)
            .status(DEFAULT_STATUS)
            .orderingProviderFirstName(DEFAULT_ORDERING_PROVIDER_FIRST_NAME)
            .orderingProviderLastName(DEFAULT_ORDERING_PROVIDER_LAST_NAME)
            .orderingProviderNpi(DEFAULT_ORDERING_PROVIDER_NPI)
            .reference(DEFAULT_REFERENCE)
            .payorClaimControlNo(DEFAULT_PAYOR_CLAIM_CONTROL_NO)
            .providerPaymentAmount(DEFAULT_PROVIDER_PAYMENT_AMOUNT)
            .lineAdjustment(DEFAULT_LINE_ADJUSTMENT)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .secondaryClaimSubmisionServicelinesUuid(DEFAULT_SECONDARY_CLAIM_SUBMISION_SERVICELINES_UUID);
        return secondaryClaimSubmisionServicelines;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SecondaryClaimSubmisionServicelines createUpdatedEntity(EntityManager em) {
        SecondaryClaimSubmisionServicelines secondaryClaimSubmisionServicelines = new SecondaryClaimSubmisionServicelines()
            .changeHealthSecondarySubmisionMasterId(UPDATED_CHANGE_HEALTH_SECONDARY_SUBMISION_MASTER_ID)
            .originalDos(UPDATED_ORIGINAL_DOS)
            .dosTo(UPDATED_DOS_TO)
            .procCode(UPDATED_PROC_CODE)
            .chargeAmt(UPDATED_CHARGE_AMT)
            .itemUom(UPDATED_ITEM_UOM)
            .modifier1(UPDATED_MODIFIER_1)
            .modifier2(UPDATED_MODIFIER_2)
            .modifier3(UPDATED_MODIFIER_3)
            .modifier4(UPDATED_MODIFIER_4)
            .icdPointer(UPDATED_ICD_POINTER)
            .qty(UPDATED_QTY)
            .insertedById(UPDATED_INSERTED_BY_ID)
            .insertedDate(UPDATED_INSERTED_DATE)
            .claimServicelineControlNo(UPDATED_CLAIM_SERVICELINE_CONTROL_NO)
            .procedureIdentifier(UPDATED_PROCEDURE_IDENTIFIER)
            .insertedByName(UPDATED_INSERTED_BY_NAME)
            .status(UPDATED_STATUS)
            .orderingProviderFirstName(UPDATED_ORDERING_PROVIDER_FIRST_NAME)
            .orderingProviderLastName(UPDATED_ORDERING_PROVIDER_LAST_NAME)
            .orderingProviderNpi(UPDATED_ORDERING_PROVIDER_NPI)
            .reference(UPDATED_REFERENCE)
            .payorClaimControlNo(UPDATED_PAYOR_CLAIM_CONTROL_NO)
            .providerPaymentAmount(UPDATED_PROVIDER_PAYMENT_AMOUNT)
            .lineAdjustment(UPDATED_LINE_ADJUSTMENT)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .secondaryClaimSubmisionServicelinesUuid(UPDATED_SECONDARY_CLAIM_SUBMISION_SERVICELINES_UUID);
        return secondaryClaimSubmisionServicelines;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(SecondaryClaimSubmisionServicelines.class).block();
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
        secondaryClaimSubmisionServicelines = createEntity(em);
    }

    @Test
    void createSecondaryClaimSubmisionServicelines() throws Exception {
        int databaseSizeBeforeCreate = secondaryClaimSubmisionServicelinesRepository.findAll().collectList().block().size();
        // Create the SecondaryClaimSubmisionServicelines
        SecondaryClaimSubmisionServicelinesDTO secondaryClaimSubmisionServicelinesDTO = secondaryClaimSubmisionServicelinesMapper.toDto(
            secondaryClaimSubmisionServicelines
        );
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(secondaryClaimSubmisionServicelinesDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the SecondaryClaimSubmisionServicelines in the database
        List<SecondaryClaimSubmisionServicelines> secondaryClaimSubmisionServicelinesList = secondaryClaimSubmisionServicelinesRepository
            .findAll()
            .collectList()
            .block();
        assertThat(secondaryClaimSubmisionServicelinesList).hasSize(databaseSizeBeforeCreate + 1);
        SecondaryClaimSubmisionServicelines testSecondaryClaimSubmisionServicelines = secondaryClaimSubmisionServicelinesList.get(
            secondaryClaimSubmisionServicelinesList.size() - 1
        );
        assertThat(testSecondaryClaimSubmisionServicelines.getChangeHealthSecondarySubmisionMasterId())
            .isEqualTo(DEFAULT_CHANGE_HEALTH_SECONDARY_SUBMISION_MASTER_ID);
        assertThat(testSecondaryClaimSubmisionServicelines.getOriginalDos()).isEqualTo(DEFAULT_ORIGINAL_DOS);
        assertThat(testSecondaryClaimSubmisionServicelines.getDosTo()).isEqualTo(DEFAULT_DOS_TO);
        assertThat(testSecondaryClaimSubmisionServicelines.getProcCode()).isEqualTo(DEFAULT_PROC_CODE);
        assertThat(testSecondaryClaimSubmisionServicelines.getChargeAmt()).isEqualTo(DEFAULT_CHARGE_AMT);
        assertThat(testSecondaryClaimSubmisionServicelines.getItemUom()).isEqualTo(DEFAULT_ITEM_UOM);
        assertThat(testSecondaryClaimSubmisionServicelines.getModifier1()).isEqualTo(DEFAULT_MODIFIER_1);
        assertThat(testSecondaryClaimSubmisionServicelines.getModifier2()).isEqualTo(DEFAULT_MODIFIER_2);
        assertThat(testSecondaryClaimSubmisionServicelines.getModifier3()).isEqualTo(DEFAULT_MODIFIER_3);
        assertThat(testSecondaryClaimSubmisionServicelines.getModifier4()).isEqualTo(DEFAULT_MODIFIER_4);
        assertThat(testSecondaryClaimSubmisionServicelines.getIcdPointer()).isEqualTo(DEFAULT_ICD_POINTER);
        assertThat(testSecondaryClaimSubmisionServicelines.getQty()).isEqualTo(DEFAULT_QTY);
        assertThat(testSecondaryClaimSubmisionServicelines.getInsertedById()).isEqualTo(DEFAULT_INSERTED_BY_ID);
        assertThat(testSecondaryClaimSubmisionServicelines.getInsertedDate()).isEqualTo(DEFAULT_INSERTED_DATE);
        assertThat(testSecondaryClaimSubmisionServicelines.getClaimServicelineControlNo()).isEqualTo(DEFAULT_CLAIM_SERVICELINE_CONTROL_NO);
        assertThat(testSecondaryClaimSubmisionServicelines.getProcedureIdentifier()).isEqualTo(DEFAULT_PROCEDURE_IDENTIFIER);
        assertThat(testSecondaryClaimSubmisionServicelines.getInsertedByName()).isEqualTo(DEFAULT_INSERTED_BY_NAME);
        assertThat(testSecondaryClaimSubmisionServicelines.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSecondaryClaimSubmisionServicelines.getOrderingProviderFirstName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testSecondaryClaimSubmisionServicelines.getOrderingProviderLastName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testSecondaryClaimSubmisionServicelines.getOrderingProviderNpi()).isEqualTo(DEFAULT_ORDERING_PROVIDER_NPI);
        assertThat(testSecondaryClaimSubmisionServicelines.getReference()).isEqualTo(DEFAULT_REFERENCE);
        assertThat(testSecondaryClaimSubmisionServicelines.getPayorClaimControlNo()).isEqualTo(DEFAULT_PAYOR_CLAIM_CONTROL_NO);
        assertThat(testSecondaryClaimSubmisionServicelines.getProviderPaymentAmount()).isEqualTo(DEFAULT_PROVIDER_PAYMENT_AMOUNT);
        assertThat(testSecondaryClaimSubmisionServicelines.getLineAdjustment()).isEqualTo(DEFAULT_LINE_ADJUSTMENT);
        assertThat(testSecondaryClaimSubmisionServicelines.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testSecondaryClaimSubmisionServicelines.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testSecondaryClaimSubmisionServicelines.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testSecondaryClaimSubmisionServicelines.getSecondaryClaimSubmisionServicelinesUuid())
            .isEqualTo(DEFAULT_SECONDARY_CLAIM_SUBMISION_SERVICELINES_UUID);
    }

    @Test
    void createSecondaryClaimSubmisionServicelinesWithExistingId() throws Exception {
        // Create the SecondaryClaimSubmisionServicelines with an existing ID
        secondaryClaimSubmisionServicelines.setChangeHealthSecondarySubmisionServicelinesId(1L);
        SecondaryClaimSubmisionServicelinesDTO secondaryClaimSubmisionServicelinesDTO = secondaryClaimSubmisionServicelinesMapper.toDto(
            secondaryClaimSubmisionServicelines
        );

        int databaseSizeBeforeCreate = secondaryClaimSubmisionServicelinesRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(secondaryClaimSubmisionServicelinesDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SecondaryClaimSubmisionServicelines in the database
        List<SecondaryClaimSubmisionServicelines> secondaryClaimSubmisionServicelinesList = secondaryClaimSubmisionServicelinesRepository
            .findAll()
            .collectList()
            .block();
        assertThat(secondaryClaimSubmisionServicelinesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllSecondaryClaimSubmisionServicelines() {
        // Initialize the database
        secondaryClaimSubmisionServicelinesRepository.save(secondaryClaimSubmisionServicelines).block();

        // Get all the secondaryClaimSubmisionServicelinesList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=changeHealthSecondarySubmisionServicelinesId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].changeHealthSecondarySubmisionServicelinesId")
            .value(hasItem(secondaryClaimSubmisionServicelines.getChangeHealthSecondarySubmisionServicelinesId().intValue()))
            .jsonPath("$.[*].changeHealthSecondarySubmisionMasterId")
            .value(hasItem(DEFAULT_CHANGE_HEALTH_SECONDARY_SUBMISION_MASTER_ID.intValue()))
            .jsonPath("$.[*].originalDos")
            .value(hasItem(DEFAULT_ORIGINAL_DOS.toString()))
            .jsonPath("$.[*].dosTo")
            .value(hasItem(DEFAULT_DOS_TO.toString()))
            .jsonPath("$.[*].procCode")
            .value(hasItem(DEFAULT_PROC_CODE))
            .jsonPath("$.[*].chargeAmt")
            .value(hasItem(DEFAULT_CHARGE_AMT.doubleValue()))
            .jsonPath("$.[*].itemUom")
            .value(hasItem(DEFAULT_ITEM_UOM))
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
            .jsonPath("$.[*].qty")
            .value(hasItem(DEFAULT_QTY.intValue()))
            .jsonPath("$.[*].insertedById")
            .value(hasItem(DEFAULT_INSERTED_BY_ID))
            .jsonPath("$.[*].insertedDate")
            .value(hasItem(DEFAULT_INSERTED_DATE.toString()))
            .jsonPath("$.[*].claimServicelineControlNo")
            .value(hasItem(DEFAULT_CLAIM_SERVICELINE_CONTROL_NO))
            .jsonPath("$.[*].procedureIdentifier")
            .value(hasItem(DEFAULT_PROCEDURE_IDENTIFIER))
            .jsonPath("$.[*].insertedByName")
            .value(hasItem(DEFAULT_INSERTED_BY_NAME))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS))
            .jsonPath("$.[*].orderingProviderFirstName")
            .value(hasItem(DEFAULT_ORDERING_PROVIDER_FIRST_NAME))
            .jsonPath("$.[*].orderingProviderLastName")
            .value(hasItem(DEFAULT_ORDERING_PROVIDER_LAST_NAME))
            .jsonPath("$.[*].orderingProviderNpi")
            .value(hasItem(DEFAULT_ORDERING_PROVIDER_NPI))
            .jsonPath("$.[*].reference")
            .value(hasItem(DEFAULT_REFERENCE))
            .jsonPath("$.[*].payorClaimControlNo")
            .value(hasItem(DEFAULT_PAYOR_CLAIM_CONTROL_NO))
            .jsonPath("$.[*].providerPaymentAmount")
            .value(hasItem(DEFAULT_PROVIDER_PAYMENT_AMOUNT.doubleValue()))
            .jsonPath("$.[*].lineAdjustment")
            .value(hasItem(DEFAULT_LINE_ADJUSTMENT))
            .jsonPath("$.[*].updatedById")
            .value(hasItem(DEFAULT_UPDATED_BY_ID))
            .jsonPath("$.[*].updatedDate")
            .value(hasItem(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.[*].updatedByName")
            .value(hasItem(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.[*].secondaryClaimSubmisionServicelinesUuid")
            .value(hasItem(DEFAULT_SECONDARY_CLAIM_SUBMISION_SERVICELINES_UUID.toString()));
    }

    @Test
    void getSecondaryClaimSubmisionServicelines() {
        // Initialize the database
        secondaryClaimSubmisionServicelinesRepository.save(secondaryClaimSubmisionServicelines).block();

        // Get the secondaryClaimSubmisionServicelines
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, secondaryClaimSubmisionServicelines.getChangeHealthSecondarySubmisionServicelinesId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.changeHealthSecondarySubmisionServicelinesId")
            .value(is(secondaryClaimSubmisionServicelines.getChangeHealthSecondarySubmisionServicelinesId().intValue()))
            .jsonPath("$.changeHealthSecondarySubmisionMasterId")
            .value(is(DEFAULT_CHANGE_HEALTH_SECONDARY_SUBMISION_MASTER_ID.intValue()))
            .jsonPath("$.originalDos")
            .value(is(DEFAULT_ORIGINAL_DOS.toString()))
            .jsonPath("$.dosTo")
            .value(is(DEFAULT_DOS_TO.toString()))
            .jsonPath("$.procCode")
            .value(is(DEFAULT_PROC_CODE))
            .jsonPath("$.chargeAmt")
            .value(is(DEFAULT_CHARGE_AMT.doubleValue()))
            .jsonPath("$.itemUom")
            .value(is(DEFAULT_ITEM_UOM))
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
            .jsonPath("$.qty")
            .value(is(DEFAULT_QTY.intValue()))
            .jsonPath("$.insertedById")
            .value(is(DEFAULT_INSERTED_BY_ID))
            .jsonPath("$.insertedDate")
            .value(is(DEFAULT_INSERTED_DATE.toString()))
            .jsonPath("$.claimServicelineControlNo")
            .value(is(DEFAULT_CLAIM_SERVICELINE_CONTROL_NO))
            .jsonPath("$.procedureIdentifier")
            .value(is(DEFAULT_PROCEDURE_IDENTIFIER))
            .jsonPath("$.insertedByName")
            .value(is(DEFAULT_INSERTED_BY_NAME))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS))
            .jsonPath("$.orderingProviderFirstName")
            .value(is(DEFAULT_ORDERING_PROVIDER_FIRST_NAME))
            .jsonPath("$.orderingProviderLastName")
            .value(is(DEFAULT_ORDERING_PROVIDER_LAST_NAME))
            .jsonPath("$.orderingProviderNpi")
            .value(is(DEFAULT_ORDERING_PROVIDER_NPI))
            .jsonPath("$.reference")
            .value(is(DEFAULT_REFERENCE))
            .jsonPath("$.payorClaimControlNo")
            .value(is(DEFAULT_PAYOR_CLAIM_CONTROL_NO))
            .jsonPath("$.providerPaymentAmount")
            .value(is(DEFAULT_PROVIDER_PAYMENT_AMOUNT.doubleValue()))
            .jsonPath("$.lineAdjustment")
            .value(is(DEFAULT_LINE_ADJUSTMENT))
            .jsonPath("$.updatedById")
            .value(is(DEFAULT_UPDATED_BY_ID))
            .jsonPath("$.updatedDate")
            .value(is(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.updatedByName")
            .value(is(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.secondaryClaimSubmisionServicelinesUuid")
            .value(is(DEFAULT_SECONDARY_CLAIM_SUBMISION_SERVICELINES_UUID.toString()));
    }

    @Test
    void getNonExistingSecondaryClaimSubmisionServicelines() {
        // Get the secondaryClaimSubmisionServicelines
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putNewSecondaryClaimSubmisionServicelines() throws Exception {
        // Initialize the database
        secondaryClaimSubmisionServicelinesRepository.save(secondaryClaimSubmisionServicelines).block();

        int databaseSizeBeforeUpdate = secondaryClaimSubmisionServicelinesRepository.findAll().collectList().block().size();

        // Update the secondaryClaimSubmisionServicelines
        SecondaryClaimSubmisionServicelines updatedSecondaryClaimSubmisionServicelines = secondaryClaimSubmisionServicelinesRepository
            .findById(secondaryClaimSubmisionServicelines.getChangeHealthSecondarySubmisionServicelinesId())
            .block();
        updatedSecondaryClaimSubmisionServicelines
            .changeHealthSecondarySubmisionMasterId(UPDATED_CHANGE_HEALTH_SECONDARY_SUBMISION_MASTER_ID)
            .originalDos(UPDATED_ORIGINAL_DOS)
            .dosTo(UPDATED_DOS_TO)
            .procCode(UPDATED_PROC_CODE)
            .chargeAmt(UPDATED_CHARGE_AMT)
            .itemUom(UPDATED_ITEM_UOM)
            .modifier1(UPDATED_MODIFIER_1)
            .modifier2(UPDATED_MODIFIER_2)
            .modifier3(UPDATED_MODIFIER_3)
            .modifier4(UPDATED_MODIFIER_4)
            .icdPointer(UPDATED_ICD_POINTER)
            .qty(UPDATED_QTY)
            .insertedById(UPDATED_INSERTED_BY_ID)
            .insertedDate(UPDATED_INSERTED_DATE)
            .claimServicelineControlNo(UPDATED_CLAIM_SERVICELINE_CONTROL_NO)
            .procedureIdentifier(UPDATED_PROCEDURE_IDENTIFIER)
            .insertedByName(UPDATED_INSERTED_BY_NAME)
            .status(UPDATED_STATUS)
            .orderingProviderFirstName(UPDATED_ORDERING_PROVIDER_FIRST_NAME)
            .orderingProviderLastName(UPDATED_ORDERING_PROVIDER_LAST_NAME)
            .orderingProviderNpi(UPDATED_ORDERING_PROVIDER_NPI)
            .reference(UPDATED_REFERENCE)
            .payorClaimControlNo(UPDATED_PAYOR_CLAIM_CONTROL_NO)
            .providerPaymentAmount(UPDATED_PROVIDER_PAYMENT_AMOUNT)
            .lineAdjustment(UPDATED_LINE_ADJUSTMENT)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .secondaryClaimSubmisionServicelinesUuid(UPDATED_SECONDARY_CLAIM_SUBMISION_SERVICELINES_UUID);
        SecondaryClaimSubmisionServicelinesDTO secondaryClaimSubmisionServicelinesDTO = secondaryClaimSubmisionServicelinesMapper.toDto(
            updatedSecondaryClaimSubmisionServicelines
        );

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, secondaryClaimSubmisionServicelinesDTO.getChangeHealthSecondarySubmisionServicelinesId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(secondaryClaimSubmisionServicelinesDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SecondaryClaimSubmisionServicelines in the database
        List<SecondaryClaimSubmisionServicelines> secondaryClaimSubmisionServicelinesList = secondaryClaimSubmisionServicelinesRepository
            .findAll()
            .collectList()
            .block();
        assertThat(secondaryClaimSubmisionServicelinesList).hasSize(databaseSizeBeforeUpdate);
        SecondaryClaimSubmisionServicelines testSecondaryClaimSubmisionServicelines = secondaryClaimSubmisionServicelinesList.get(
            secondaryClaimSubmisionServicelinesList.size() - 1
        );
        assertThat(testSecondaryClaimSubmisionServicelines.getChangeHealthSecondarySubmisionMasterId())
            .isEqualTo(UPDATED_CHANGE_HEALTH_SECONDARY_SUBMISION_MASTER_ID);
        assertThat(testSecondaryClaimSubmisionServicelines.getOriginalDos()).isEqualTo(UPDATED_ORIGINAL_DOS);
        assertThat(testSecondaryClaimSubmisionServicelines.getDosTo()).isEqualTo(UPDATED_DOS_TO);
        assertThat(testSecondaryClaimSubmisionServicelines.getProcCode()).isEqualTo(UPDATED_PROC_CODE);
        assertThat(testSecondaryClaimSubmisionServicelines.getChargeAmt()).isEqualTo(UPDATED_CHARGE_AMT);
        assertThat(testSecondaryClaimSubmisionServicelines.getItemUom()).isEqualTo(UPDATED_ITEM_UOM);
        assertThat(testSecondaryClaimSubmisionServicelines.getModifier1()).isEqualTo(UPDATED_MODIFIER_1);
        assertThat(testSecondaryClaimSubmisionServicelines.getModifier2()).isEqualTo(UPDATED_MODIFIER_2);
        assertThat(testSecondaryClaimSubmisionServicelines.getModifier3()).isEqualTo(UPDATED_MODIFIER_3);
        assertThat(testSecondaryClaimSubmisionServicelines.getModifier4()).isEqualTo(UPDATED_MODIFIER_4);
        assertThat(testSecondaryClaimSubmisionServicelines.getIcdPointer()).isEqualTo(UPDATED_ICD_POINTER);
        assertThat(testSecondaryClaimSubmisionServicelines.getQty()).isEqualTo(UPDATED_QTY);
        assertThat(testSecondaryClaimSubmisionServicelines.getInsertedById()).isEqualTo(UPDATED_INSERTED_BY_ID);
        assertThat(testSecondaryClaimSubmisionServicelines.getInsertedDate()).isEqualTo(UPDATED_INSERTED_DATE);
        assertThat(testSecondaryClaimSubmisionServicelines.getClaimServicelineControlNo()).isEqualTo(UPDATED_CLAIM_SERVICELINE_CONTROL_NO);
        assertThat(testSecondaryClaimSubmisionServicelines.getProcedureIdentifier()).isEqualTo(UPDATED_PROCEDURE_IDENTIFIER);
        assertThat(testSecondaryClaimSubmisionServicelines.getInsertedByName()).isEqualTo(UPDATED_INSERTED_BY_NAME);
        assertThat(testSecondaryClaimSubmisionServicelines.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSecondaryClaimSubmisionServicelines.getOrderingProviderFirstName()).isEqualTo(UPDATED_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testSecondaryClaimSubmisionServicelines.getOrderingProviderLastName()).isEqualTo(UPDATED_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testSecondaryClaimSubmisionServicelines.getOrderingProviderNpi()).isEqualTo(UPDATED_ORDERING_PROVIDER_NPI);
        assertThat(testSecondaryClaimSubmisionServicelines.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testSecondaryClaimSubmisionServicelines.getPayorClaimControlNo()).isEqualTo(UPDATED_PAYOR_CLAIM_CONTROL_NO);
        assertThat(testSecondaryClaimSubmisionServicelines.getProviderPaymentAmount()).isEqualTo(UPDATED_PROVIDER_PAYMENT_AMOUNT);
        assertThat(testSecondaryClaimSubmisionServicelines.getLineAdjustment()).isEqualTo(UPDATED_LINE_ADJUSTMENT);
        assertThat(testSecondaryClaimSubmisionServicelines.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testSecondaryClaimSubmisionServicelines.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testSecondaryClaimSubmisionServicelines.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testSecondaryClaimSubmisionServicelines.getSecondaryClaimSubmisionServicelinesUuid())
            .isEqualTo(UPDATED_SECONDARY_CLAIM_SUBMISION_SERVICELINES_UUID);
    }

    @Test
    void putNonExistingSecondaryClaimSubmisionServicelines() throws Exception {
        int databaseSizeBeforeUpdate = secondaryClaimSubmisionServicelinesRepository.findAll().collectList().block().size();
        secondaryClaimSubmisionServicelines.setChangeHealthSecondarySubmisionServicelinesId(count.incrementAndGet());

        // Create the SecondaryClaimSubmisionServicelines
        SecondaryClaimSubmisionServicelinesDTO secondaryClaimSubmisionServicelinesDTO = secondaryClaimSubmisionServicelinesMapper.toDto(
            secondaryClaimSubmisionServicelines
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, secondaryClaimSubmisionServicelinesDTO.getChangeHealthSecondarySubmisionServicelinesId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(secondaryClaimSubmisionServicelinesDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SecondaryClaimSubmisionServicelines in the database
        List<SecondaryClaimSubmisionServicelines> secondaryClaimSubmisionServicelinesList = secondaryClaimSubmisionServicelinesRepository
            .findAll()
            .collectList()
            .block();
        assertThat(secondaryClaimSubmisionServicelinesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchSecondaryClaimSubmisionServicelines() throws Exception {
        int databaseSizeBeforeUpdate = secondaryClaimSubmisionServicelinesRepository.findAll().collectList().block().size();
        secondaryClaimSubmisionServicelines.setChangeHealthSecondarySubmisionServicelinesId(count.incrementAndGet());

        // Create the SecondaryClaimSubmisionServicelines
        SecondaryClaimSubmisionServicelinesDTO secondaryClaimSubmisionServicelinesDTO = secondaryClaimSubmisionServicelinesMapper.toDto(
            secondaryClaimSubmisionServicelines
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(secondaryClaimSubmisionServicelinesDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SecondaryClaimSubmisionServicelines in the database
        List<SecondaryClaimSubmisionServicelines> secondaryClaimSubmisionServicelinesList = secondaryClaimSubmisionServicelinesRepository
            .findAll()
            .collectList()
            .block();
        assertThat(secondaryClaimSubmisionServicelinesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamSecondaryClaimSubmisionServicelines() throws Exception {
        int databaseSizeBeforeUpdate = secondaryClaimSubmisionServicelinesRepository.findAll().collectList().block().size();
        secondaryClaimSubmisionServicelines.setChangeHealthSecondarySubmisionServicelinesId(count.incrementAndGet());

        // Create the SecondaryClaimSubmisionServicelines
        SecondaryClaimSubmisionServicelinesDTO secondaryClaimSubmisionServicelinesDTO = secondaryClaimSubmisionServicelinesMapper.toDto(
            secondaryClaimSubmisionServicelines
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(secondaryClaimSubmisionServicelinesDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the SecondaryClaimSubmisionServicelines in the database
        List<SecondaryClaimSubmisionServicelines> secondaryClaimSubmisionServicelinesList = secondaryClaimSubmisionServicelinesRepository
            .findAll()
            .collectList()
            .block();
        assertThat(secondaryClaimSubmisionServicelinesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateSecondaryClaimSubmisionServicelinesWithPatch() throws Exception {
        // Initialize the database
        secondaryClaimSubmisionServicelinesRepository.save(secondaryClaimSubmisionServicelines).block();

        int databaseSizeBeforeUpdate = secondaryClaimSubmisionServicelinesRepository.findAll().collectList().block().size();

        // Update the secondaryClaimSubmisionServicelines using partial update
        SecondaryClaimSubmisionServicelines partialUpdatedSecondaryClaimSubmisionServicelines = new SecondaryClaimSubmisionServicelines();
        partialUpdatedSecondaryClaimSubmisionServicelines.setChangeHealthSecondarySubmisionServicelinesId(
            secondaryClaimSubmisionServicelines.getChangeHealthSecondarySubmisionServicelinesId()
        );

        partialUpdatedSecondaryClaimSubmisionServicelines
            .changeHealthSecondarySubmisionMasterId(UPDATED_CHANGE_HEALTH_SECONDARY_SUBMISION_MASTER_ID)
            .originalDos(UPDATED_ORIGINAL_DOS)
            .chargeAmt(UPDATED_CHARGE_AMT)
            .modifier1(UPDATED_MODIFIER_1)
            .modifier2(UPDATED_MODIFIER_2)
            .modifier3(UPDATED_MODIFIER_3)
            .modifier4(UPDATED_MODIFIER_4)
            .qty(UPDATED_QTY)
            .claimServicelineControlNo(UPDATED_CLAIM_SERVICELINE_CONTROL_NO)
            .insertedByName(UPDATED_INSERTED_BY_NAME)
            .orderingProviderFirstName(UPDATED_ORDERING_PROVIDER_FIRST_NAME)
            .orderingProviderNpi(UPDATED_ORDERING_PROVIDER_NPI)
            .reference(UPDATED_REFERENCE)
            .payorClaimControlNo(UPDATED_PAYOR_CLAIM_CONTROL_NO)
            .providerPaymentAmount(UPDATED_PROVIDER_PAYMENT_AMOUNT)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .secondaryClaimSubmisionServicelinesUuid(UPDATED_SECONDARY_CLAIM_SUBMISION_SERVICELINES_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedSecondaryClaimSubmisionServicelines.getChangeHealthSecondarySubmisionServicelinesId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedSecondaryClaimSubmisionServicelines))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SecondaryClaimSubmisionServicelines in the database
        List<SecondaryClaimSubmisionServicelines> secondaryClaimSubmisionServicelinesList = secondaryClaimSubmisionServicelinesRepository
            .findAll()
            .collectList()
            .block();
        assertThat(secondaryClaimSubmisionServicelinesList).hasSize(databaseSizeBeforeUpdate);
        SecondaryClaimSubmisionServicelines testSecondaryClaimSubmisionServicelines = secondaryClaimSubmisionServicelinesList.get(
            secondaryClaimSubmisionServicelinesList.size() - 1
        );
        assertThat(testSecondaryClaimSubmisionServicelines.getChangeHealthSecondarySubmisionMasterId())
            .isEqualTo(UPDATED_CHANGE_HEALTH_SECONDARY_SUBMISION_MASTER_ID);
        assertThat(testSecondaryClaimSubmisionServicelines.getOriginalDos()).isEqualTo(UPDATED_ORIGINAL_DOS);
        assertThat(testSecondaryClaimSubmisionServicelines.getDosTo()).isEqualTo(DEFAULT_DOS_TO);
        assertThat(testSecondaryClaimSubmisionServicelines.getProcCode()).isEqualTo(DEFAULT_PROC_CODE);
        assertThat(testSecondaryClaimSubmisionServicelines.getChargeAmt()).isEqualTo(UPDATED_CHARGE_AMT);
        assertThat(testSecondaryClaimSubmisionServicelines.getItemUom()).isEqualTo(DEFAULT_ITEM_UOM);
        assertThat(testSecondaryClaimSubmisionServicelines.getModifier1()).isEqualTo(UPDATED_MODIFIER_1);
        assertThat(testSecondaryClaimSubmisionServicelines.getModifier2()).isEqualTo(UPDATED_MODIFIER_2);
        assertThat(testSecondaryClaimSubmisionServicelines.getModifier3()).isEqualTo(UPDATED_MODIFIER_3);
        assertThat(testSecondaryClaimSubmisionServicelines.getModifier4()).isEqualTo(UPDATED_MODIFIER_4);
        assertThat(testSecondaryClaimSubmisionServicelines.getIcdPointer()).isEqualTo(DEFAULT_ICD_POINTER);
        assertThat(testSecondaryClaimSubmisionServicelines.getQty()).isEqualTo(UPDATED_QTY);
        assertThat(testSecondaryClaimSubmisionServicelines.getInsertedById()).isEqualTo(DEFAULT_INSERTED_BY_ID);
        assertThat(testSecondaryClaimSubmisionServicelines.getInsertedDate()).isEqualTo(DEFAULT_INSERTED_DATE);
        assertThat(testSecondaryClaimSubmisionServicelines.getClaimServicelineControlNo()).isEqualTo(UPDATED_CLAIM_SERVICELINE_CONTROL_NO);
        assertThat(testSecondaryClaimSubmisionServicelines.getProcedureIdentifier()).isEqualTo(DEFAULT_PROCEDURE_IDENTIFIER);
        assertThat(testSecondaryClaimSubmisionServicelines.getInsertedByName()).isEqualTo(UPDATED_INSERTED_BY_NAME);
        assertThat(testSecondaryClaimSubmisionServicelines.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSecondaryClaimSubmisionServicelines.getOrderingProviderFirstName()).isEqualTo(UPDATED_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testSecondaryClaimSubmisionServicelines.getOrderingProviderLastName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testSecondaryClaimSubmisionServicelines.getOrderingProviderNpi()).isEqualTo(UPDATED_ORDERING_PROVIDER_NPI);
        assertThat(testSecondaryClaimSubmisionServicelines.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testSecondaryClaimSubmisionServicelines.getPayorClaimControlNo()).isEqualTo(UPDATED_PAYOR_CLAIM_CONTROL_NO);
        assertThat(testSecondaryClaimSubmisionServicelines.getProviderPaymentAmount()).isEqualTo(UPDATED_PROVIDER_PAYMENT_AMOUNT);
        assertThat(testSecondaryClaimSubmisionServicelines.getLineAdjustment()).isEqualTo(DEFAULT_LINE_ADJUSTMENT);
        assertThat(testSecondaryClaimSubmisionServicelines.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testSecondaryClaimSubmisionServicelines.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testSecondaryClaimSubmisionServicelines.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testSecondaryClaimSubmisionServicelines.getSecondaryClaimSubmisionServicelinesUuid())
            .isEqualTo(UPDATED_SECONDARY_CLAIM_SUBMISION_SERVICELINES_UUID);
    }

    @Test
    void fullUpdateSecondaryClaimSubmisionServicelinesWithPatch() throws Exception {
        // Initialize the database
        secondaryClaimSubmisionServicelinesRepository.save(secondaryClaimSubmisionServicelines).block();

        int databaseSizeBeforeUpdate = secondaryClaimSubmisionServicelinesRepository.findAll().collectList().block().size();

        // Update the secondaryClaimSubmisionServicelines using partial update
        SecondaryClaimSubmisionServicelines partialUpdatedSecondaryClaimSubmisionServicelines = new SecondaryClaimSubmisionServicelines();
        partialUpdatedSecondaryClaimSubmisionServicelines.setChangeHealthSecondarySubmisionServicelinesId(
            secondaryClaimSubmisionServicelines.getChangeHealthSecondarySubmisionServicelinesId()
        );

        partialUpdatedSecondaryClaimSubmisionServicelines
            .changeHealthSecondarySubmisionMasterId(UPDATED_CHANGE_HEALTH_SECONDARY_SUBMISION_MASTER_ID)
            .originalDos(UPDATED_ORIGINAL_DOS)
            .dosTo(UPDATED_DOS_TO)
            .procCode(UPDATED_PROC_CODE)
            .chargeAmt(UPDATED_CHARGE_AMT)
            .itemUom(UPDATED_ITEM_UOM)
            .modifier1(UPDATED_MODIFIER_1)
            .modifier2(UPDATED_MODIFIER_2)
            .modifier3(UPDATED_MODIFIER_3)
            .modifier4(UPDATED_MODIFIER_4)
            .icdPointer(UPDATED_ICD_POINTER)
            .qty(UPDATED_QTY)
            .insertedById(UPDATED_INSERTED_BY_ID)
            .insertedDate(UPDATED_INSERTED_DATE)
            .claimServicelineControlNo(UPDATED_CLAIM_SERVICELINE_CONTROL_NO)
            .procedureIdentifier(UPDATED_PROCEDURE_IDENTIFIER)
            .insertedByName(UPDATED_INSERTED_BY_NAME)
            .status(UPDATED_STATUS)
            .orderingProviderFirstName(UPDATED_ORDERING_PROVIDER_FIRST_NAME)
            .orderingProviderLastName(UPDATED_ORDERING_PROVIDER_LAST_NAME)
            .orderingProviderNpi(UPDATED_ORDERING_PROVIDER_NPI)
            .reference(UPDATED_REFERENCE)
            .payorClaimControlNo(UPDATED_PAYOR_CLAIM_CONTROL_NO)
            .providerPaymentAmount(UPDATED_PROVIDER_PAYMENT_AMOUNT)
            .lineAdjustment(UPDATED_LINE_ADJUSTMENT)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .secondaryClaimSubmisionServicelinesUuid(UPDATED_SECONDARY_CLAIM_SUBMISION_SERVICELINES_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedSecondaryClaimSubmisionServicelines.getChangeHealthSecondarySubmisionServicelinesId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedSecondaryClaimSubmisionServicelines))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SecondaryClaimSubmisionServicelines in the database
        List<SecondaryClaimSubmisionServicelines> secondaryClaimSubmisionServicelinesList = secondaryClaimSubmisionServicelinesRepository
            .findAll()
            .collectList()
            .block();
        assertThat(secondaryClaimSubmisionServicelinesList).hasSize(databaseSizeBeforeUpdate);
        SecondaryClaimSubmisionServicelines testSecondaryClaimSubmisionServicelines = secondaryClaimSubmisionServicelinesList.get(
            secondaryClaimSubmisionServicelinesList.size() - 1
        );
        assertThat(testSecondaryClaimSubmisionServicelines.getChangeHealthSecondarySubmisionMasterId())
            .isEqualTo(UPDATED_CHANGE_HEALTH_SECONDARY_SUBMISION_MASTER_ID);
        assertThat(testSecondaryClaimSubmisionServicelines.getOriginalDos()).isEqualTo(UPDATED_ORIGINAL_DOS);
        assertThat(testSecondaryClaimSubmisionServicelines.getDosTo()).isEqualTo(UPDATED_DOS_TO);
        assertThat(testSecondaryClaimSubmisionServicelines.getProcCode()).isEqualTo(UPDATED_PROC_CODE);
        assertThat(testSecondaryClaimSubmisionServicelines.getChargeAmt()).isEqualTo(UPDATED_CHARGE_AMT);
        assertThat(testSecondaryClaimSubmisionServicelines.getItemUom()).isEqualTo(UPDATED_ITEM_UOM);
        assertThat(testSecondaryClaimSubmisionServicelines.getModifier1()).isEqualTo(UPDATED_MODIFIER_1);
        assertThat(testSecondaryClaimSubmisionServicelines.getModifier2()).isEqualTo(UPDATED_MODIFIER_2);
        assertThat(testSecondaryClaimSubmisionServicelines.getModifier3()).isEqualTo(UPDATED_MODIFIER_3);
        assertThat(testSecondaryClaimSubmisionServicelines.getModifier4()).isEqualTo(UPDATED_MODIFIER_4);
        assertThat(testSecondaryClaimSubmisionServicelines.getIcdPointer()).isEqualTo(UPDATED_ICD_POINTER);
        assertThat(testSecondaryClaimSubmisionServicelines.getQty()).isEqualTo(UPDATED_QTY);
        assertThat(testSecondaryClaimSubmisionServicelines.getInsertedById()).isEqualTo(UPDATED_INSERTED_BY_ID);
        assertThat(testSecondaryClaimSubmisionServicelines.getInsertedDate()).isEqualTo(UPDATED_INSERTED_DATE);
        assertThat(testSecondaryClaimSubmisionServicelines.getClaimServicelineControlNo()).isEqualTo(UPDATED_CLAIM_SERVICELINE_CONTROL_NO);
        assertThat(testSecondaryClaimSubmisionServicelines.getProcedureIdentifier()).isEqualTo(UPDATED_PROCEDURE_IDENTIFIER);
        assertThat(testSecondaryClaimSubmisionServicelines.getInsertedByName()).isEqualTo(UPDATED_INSERTED_BY_NAME);
        assertThat(testSecondaryClaimSubmisionServicelines.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSecondaryClaimSubmisionServicelines.getOrderingProviderFirstName()).isEqualTo(UPDATED_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testSecondaryClaimSubmisionServicelines.getOrderingProviderLastName()).isEqualTo(UPDATED_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testSecondaryClaimSubmisionServicelines.getOrderingProviderNpi()).isEqualTo(UPDATED_ORDERING_PROVIDER_NPI);
        assertThat(testSecondaryClaimSubmisionServicelines.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testSecondaryClaimSubmisionServicelines.getPayorClaimControlNo()).isEqualTo(UPDATED_PAYOR_CLAIM_CONTROL_NO);
        assertThat(testSecondaryClaimSubmisionServicelines.getProviderPaymentAmount()).isEqualTo(UPDATED_PROVIDER_PAYMENT_AMOUNT);
        assertThat(testSecondaryClaimSubmisionServicelines.getLineAdjustment()).isEqualTo(UPDATED_LINE_ADJUSTMENT);
        assertThat(testSecondaryClaimSubmisionServicelines.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testSecondaryClaimSubmisionServicelines.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testSecondaryClaimSubmisionServicelines.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testSecondaryClaimSubmisionServicelines.getSecondaryClaimSubmisionServicelinesUuid())
            .isEqualTo(UPDATED_SECONDARY_CLAIM_SUBMISION_SERVICELINES_UUID);
    }

    @Test
    void patchNonExistingSecondaryClaimSubmisionServicelines() throws Exception {
        int databaseSizeBeforeUpdate = secondaryClaimSubmisionServicelinesRepository.findAll().collectList().block().size();
        secondaryClaimSubmisionServicelines.setChangeHealthSecondarySubmisionServicelinesId(count.incrementAndGet());

        // Create the SecondaryClaimSubmisionServicelines
        SecondaryClaimSubmisionServicelinesDTO secondaryClaimSubmisionServicelinesDTO = secondaryClaimSubmisionServicelinesMapper.toDto(
            secondaryClaimSubmisionServicelines
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, secondaryClaimSubmisionServicelinesDTO.getChangeHealthSecondarySubmisionServicelinesId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(secondaryClaimSubmisionServicelinesDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SecondaryClaimSubmisionServicelines in the database
        List<SecondaryClaimSubmisionServicelines> secondaryClaimSubmisionServicelinesList = secondaryClaimSubmisionServicelinesRepository
            .findAll()
            .collectList()
            .block();
        assertThat(secondaryClaimSubmisionServicelinesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchSecondaryClaimSubmisionServicelines() throws Exception {
        int databaseSizeBeforeUpdate = secondaryClaimSubmisionServicelinesRepository.findAll().collectList().block().size();
        secondaryClaimSubmisionServicelines.setChangeHealthSecondarySubmisionServicelinesId(count.incrementAndGet());

        // Create the SecondaryClaimSubmisionServicelines
        SecondaryClaimSubmisionServicelinesDTO secondaryClaimSubmisionServicelinesDTO = secondaryClaimSubmisionServicelinesMapper.toDto(
            secondaryClaimSubmisionServicelines
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(secondaryClaimSubmisionServicelinesDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SecondaryClaimSubmisionServicelines in the database
        List<SecondaryClaimSubmisionServicelines> secondaryClaimSubmisionServicelinesList = secondaryClaimSubmisionServicelinesRepository
            .findAll()
            .collectList()
            .block();
        assertThat(secondaryClaimSubmisionServicelinesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamSecondaryClaimSubmisionServicelines() throws Exception {
        int databaseSizeBeforeUpdate = secondaryClaimSubmisionServicelinesRepository.findAll().collectList().block().size();
        secondaryClaimSubmisionServicelines.setChangeHealthSecondarySubmisionServicelinesId(count.incrementAndGet());

        // Create the SecondaryClaimSubmisionServicelines
        SecondaryClaimSubmisionServicelinesDTO secondaryClaimSubmisionServicelinesDTO = secondaryClaimSubmisionServicelinesMapper.toDto(
            secondaryClaimSubmisionServicelines
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(secondaryClaimSubmisionServicelinesDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the SecondaryClaimSubmisionServicelines in the database
        List<SecondaryClaimSubmisionServicelines> secondaryClaimSubmisionServicelinesList = secondaryClaimSubmisionServicelinesRepository
            .findAll()
            .collectList()
            .block();
        assertThat(secondaryClaimSubmisionServicelinesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteSecondaryClaimSubmisionServicelines() {
        // Initialize the database
        secondaryClaimSubmisionServicelinesRepository.save(secondaryClaimSubmisionServicelines).block();

        int databaseSizeBeforeDelete = secondaryClaimSubmisionServicelinesRepository.findAll().collectList().block().size();

        // Delete the secondaryClaimSubmisionServicelines
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, secondaryClaimSubmisionServicelines.getChangeHealthSecondarySubmisionServicelinesId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<SecondaryClaimSubmisionServicelines> secondaryClaimSubmisionServicelinesList = secondaryClaimSubmisionServicelinesRepository
            .findAll()
            .collectList()
            .block();
        assertThat(secondaryClaimSubmisionServicelinesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
