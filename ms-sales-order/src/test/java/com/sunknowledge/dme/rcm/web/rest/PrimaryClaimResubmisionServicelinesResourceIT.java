package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.PrimaryClaimResubmisionServicelines;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.PrimaryClaimResubmisionServicelinesRepository;
import com.sunknowledge.dme.rcm.service.dto.PrimaryClaimResubmisionServicelinesDTO;
import com.sunknowledge.dme.rcm.service.mapper.PrimaryClaimResubmisionServicelinesMapper;
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
 * Integration tests for the {@link PrimaryClaimResubmisionServicelinesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class PrimaryClaimResubmisionServicelinesResourceIT {

    private static final Long DEFAULT_CHANGE_HEALTH_PRIMARY_RESUBMISION_MASTER_ID = 1L;
    private static final Long UPDATED_CHANGE_HEALTH_PRIMARY_RESUBMISION_MASTER_ID = 2L;

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

    private static final Long DEFAULT_INSERTED_BY_ID = 1L;
    private static final Long UPDATED_INSERTED_BY_ID = 2L;

    private static final LocalDate DEFAULT_NSERTED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NSERTED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CLAIM_SERVICELINE_CONTROL_NO = "AAAAAAAAAA";
    private static final String UPDATED_CLAIM_SERVICELINE_CONTROL_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PROCEDURE_IDENTIFIER = "AAAAAAAAAA";
    private static final String UPDATED_PROCEDURE_IDENTIFIER = "BBBBBBBBBB";

    private static final String DEFAULT_INSERTED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INSERTED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_PRIMARY_CLAIM_RESUBMISION_SERVICELINES_UUID = UUID.randomUUID();
    private static final UUID UPDATED_PRIMARY_CLAIM_RESUBMISION_SERVICELINES_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/primary-claim-resubmision-servicelines";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{changeHealthPrimaryResubmisionServicelinesId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PrimaryClaimResubmisionServicelinesRepository primaryClaimResubmisionServicelinesRepository;

    @Autowired
    private PrimaryClaimResubmisionServicelinesMapper primaryClaimResubmisionServicelinesMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private PrimaryClaimResubmisionServicelines primaryClaimResubmisionServicelines;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PrimaryClaimResubmisionServicelines createEntity(EntityManager em) {
        PrimaryClaimResubmisionServicelines primaryClaimResubmisionServicelines = new PrimaryClaimResubmisionServicelines()
            .changeHealthPrimaryResubmisionMasterId(DEFAULT_CHANGE_HEALTH_PRIMARY_RESUBMISION_MASTER_ID)
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
            .nsertedDate(DEFAULT_NSERTED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .claimServicelineControlNo(DEFAULT_CLAIM_SERVICELINE_CONTROL_NO)
            .procedureIdentifier(DEFAULT_PROCEDURE_IDENTIFIER)
            .insertedByName(DEFAULT_INSERTED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .status(DEFAULT_STATUS)
            .orderingProviderFirstName(DEFAULT_ORDERING_PROVIDER_FIRST_NAME)
            .orderingProviderLastName(DEFAULT_ORDERING_PROVIDER_LAST_NAME)
            .orderingProviderNpi(DEFAULT_ORDERING_PROVIDER_NPI)
            .reference(DEFAULT_REFERENCE)
            .primaryClaimResubmisionServicelinesUuid(DEFAULT_PRIMARY_CLAIM_RESUBMISION_SERVICELINES_UUID);
        return primaryClaimResubmisionServicelines;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PrimaryClaimResubmisionServicelines createUpdatedEntity(EntityManager em) {
        PrimaryClaimResubmisionServicelines primaryClaimResubmisionServicelines = new PrimaryClaimResubmisionServicelines()
            .changeHealthPrimaryResubmisionMasterId(UPDATED_CHANGE_HEALTH_PRIMARY_RESUBMISION_MASTER_ID)
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
            .nsertedDate(UPDATED_NSERTED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .claimServicelineControlNo(UPDATED_CLAIM_SERVICELINE_CONTROL_NO)
            .procedureIdentifier(UPDATED_PROCEDURE_IDENTIFIER)
            .insertedByName(UPDATED_INSERTED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .status(UPDATED_STATUS)
            .orderingProviderFirstName(UPDATED_ORDERING_PROVIDER_FIRST_NAME)
            .orderingProviderLastName(UPDATED_ORDERING_PROVIDER_LAST_NAME)
            .orderingProviderNpi(UPDATED_ORDERING_PROVIDER_NPI)
            .reference(UPDATED_REFERENCE)
            .primaryClaimResubmisionServicelinesUuid(UPDATED_PRIMARY_CLAIM_RESUBMISION_SERVICELINES_UUID);
        return primaryClaimResubmisionServicelines;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(PrimaryClaimResubmisionServicelines.class).block();
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
        primaryClaimResubmisionServicelines = createEntity(em);
    }

    @Test
    void createPrimaryClaimResubmisionServicelines() throws Exception {
        int databaseSizeBeforeCreate = primaryClaimResubmisionServicelinesRepository.findAll().collectList().block().size();
        // Create the PrimaryClaimResubmisionServicelines
        PrimaryClaimResubmisionServicelinesDTO primaryClaimResubmisionServicelinesDTO = primaryClaimResubmisionServicelinesMapper.toDto(
            primaryClaimResubmisionServicelines
        );
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(primaryClaimResubmisionServicelinesDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the PrimaryClaimResubmisionServicelines in the database
        List<PrimaryClaimResubmisionServicelines> primaryClaimResubmisionServicelinesList = primaryClaimResubmisionServicelinesRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimResubmisionServicelinesList).hasSize(databaseSizeBeforeCreate + 1);
        PrimaryClaimResubmisionServicelines testPrimaryClaimResubmisionServicelines = primaryClaimResubmisionServicelinesList.get(
            primaryClaimResubmisionServicelinesList.size() - 1
        );
        assertThat(testPrimaryClaimResubmisionServicelines.getChangeHealthPrimaryResubmisionMasterId())
            .isEqualTo(DEFAULT_CHANGE_HEALTH_PRIMARY_RESUBMISION_MASTER_ID);
        assertThat(testPrimaryClaimResubmisionServicelines.getOriginalDos()).isEqualTo(DEFAULT_ORIGINAL_DOS);
        assertThat(testPrimaryClaimResubmisionServicelines.getDosTo()).isEqualTo(DEFAULT_DOS_TO);
        assertThat(testPrimaryClaimResubmisionServicelines.getProcCode()).isEqualTo(DEFAULT_PROC_CODE);
        assertThat(testPrimaryClaimResubmisionServicelines.getChargeAmt()).isEqualTo(DEFAULT_CHARGE_AMT);
        assertThat(testPrimaryClaimResubmisionServicelines.getItemUom()).isEqualTo(DEFAULT_ITEM_UOM);
        assertThat(testPrimaryClaimResubmisionServicelines.getModifier1()).isEqualTo(DEFAULT_MODIFIER_1);
        assertThat(testPrimaryClaimResubmisionServicelines.getModifier2()).isEqualTo(DEFAULT_MODIFIER_2);
        assertThat(testPrimaryClaimResubmisionServicelines.getModifier3()).isEqualTo(DEFAULT_MODIFIER_3);
        assertThat(testPrimaryClaimResubmisionServicelines.getModifier4()).isEqualTo(DEFAULT_MODIFIER_4);
        assertThat(testPrimaryClaimResubmisionServicelines.getIcdPointer()).isEqualTo(DEFAULT_ICD_POINTER);
        assertThat(testPrimaryClaimResubmisionServicelines.getQty()).isEqualTo(DEFAULT_QTY);
        assertThat(testPrimaryClaimResubmisionServicelines.getInsertedById()).isEqualTo(DEFAULT_INSERTED_BY_ID);
        assertThat(testPrimaryClaimResubmisionServicelines.getNsertedDate()).isEqualTo(DEFAULT_NSERTED_DATE);
        assertThat(testPrimaryClaimResubmisionServicelines.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testPrimaryClaimResubmisionServicelines.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testPrimaryClaimResubmisionServicelines.getClaimServicelineControlNo()).isEqualTo(DEFAULT_CLAIM_SERVICELINE_CONTROL_NO);
        assertThat(testPrimaryClaimResubmisionServicelines.getProcedureIdentifier()).isEqualTo(DEFAULT_PROCEDURE_IDENTIFIER);
        assertThat(testPrimaryClaimResubmisionServicelines.getInsertedByName()).isEqualTo(DEFAULT_INSERTED_BY_NAME);
        assertThat(testPrimaryClaimResubmisionServicelines.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testPrimaryClaimResubmisionServicelines.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPrimaryClaimResubmisionServicelines.getOrderingProviderFirstName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testPrimaryClaimResubmisionServicelines.getOrderingProviderLastName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testPrimaryClaimResubmisionServicelines.getOrderingProviderNpi()).isEqualTo(DEFAULT_ORDERING_PROVIDER_NPI);
        assertThat(testPrimaryClaimResubmisionServicelines.getReference()).isEqualTo(DEFAULT_REFERENCE);
        assertThat(testPrimaryClaimResubmisionServicelines.getPrimaryClaimResubmisionServicelinesUuid())
            .isEqualTo(DEFAULT_PRIMARY_CLAIM_RESUBMISION_SERVICELINES_UUID);
    }

    @Test
    void createPrimaryClaimResubmisionServicelinesWithExistingId() throws Exception {
        // Create the PrimaryClaimResubmisionServicelines with an existing ID
        primaryClaimResubmisionServicelines.setChangeHealthPrimaryResubmisionServicelinesId(1L);
        PrimaryClaimResubmisionServicelinesDTO primaryClaimResubmisionServicelinesDTO = primaryClaimResubmisionServicelinesMapper.toDto(
            primaryClaimResubmisionServicelines
        );

        int databaseSizeBeforeCreate = primaryClaimResubmisionServicelinesRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(primaryClaimResubmisionServicelinesDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PrimaryClaimResubmisionServicelines in the database
        List<PrimaryClaimResubmisionServicelines> primaryClaimResubmisionServicelinesList = primaryClaimResubmisionServicelinesRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimResubmisionServicelinesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllPrimaryClaimResubmisionServicelines() {
        // Initialize the database
        primaryClaimResubmisionServicelinesRepository.save(primaryClaimResubmisionServicelines).block();

        // Get all the primaryClaimResubmisionServicelinesList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=changeHealthPrimaryResubmisionServicelinesId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].changeHealthPrimaryResubmisionServicelinesId")
            .value(hasItem(primaryClaimResubmisionServicelines.getChangeHealthPrimaryResubmisionServicelinesId().intValue()))
            .jsonPath("$.[*].changeHealthPrimaryResubmisionMasterId")
            .value(hasItem(DEFAULT_CHANGE_HEALTH_PRIMARY_RESUBMISION_MASTER_ID.intValue()))
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
            .value(hasItem(DEFAULT_INSERTED_BY_ID.intValue()))
            .jsonPath("$.[*].nsertedDate")
            .value(hasItem(DEFAULT_NSERTED_DATE.toString()))
            .jsonPath("$.[*].updatedById")
            .value(hasItem(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.[*].updatedDate")
            .value(hasItem(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.[*].claimServicelineControlNo")
            .value(hasItem(DEFAULT_CLAIM_SERVICELINE_CONTROL_NO))
            .jsonPath("$.[*].procedureIdentifier")
            .value(hasItem(DEFAULT_PROCEDURE_IDENTIFIER))
            .jsonPath("$.[*].insertedByName")
            .value(hasItem(DEFAULT_INSERTED_BY_NAME))
            .jsonPath("$.[*].updatedByName")
            .value(hasItem(DEFAULT_UPDATED_BY_NAME))
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
            .jsonPath("$.[*].primaryClaimResubmisionServicelinesUuid")
            .value(hasItem(DEFAULT_PRIMARY_CLAIM_RESUBMISION_SERVICELINES_UUID.toString()));
    }

    @Test
    void getPrimaryClaimResubmisionServicelines() {
        // Initialize the database
        primaryClaimResubmisionServicelinesRepository.save(primaryClaimResubmisionServicelines).block();

        // Get the primaryClaimResubmisionServicelines
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, primaryClaimResubmisionServicelines.getChangeHealthPrimaryResubmisionServicelinesId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.changeHealthPrimaryResubmisionServicelinesId")
            .value(is(primaryClaimResubmisionServicelines.getChangeHealthPrimaryResubmisionServicelinesId().intValue()))
            .jsonPath("$.changeHealthPrimaryResubmisionMasterId")
            .value(is(DEFAULT_CHANGE_HEALTH_PRIMARY_RESUBMISION_MASTER_ID.intValue()))
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
            .value(is(DEFAULT_INSERTED_BY_ID.intValue()))
            .jsonPath("$.nsertedDate")
            .value(is(DEFAULT_NSERTED_DATE.toString()))
            .jsonPath("$.updatedById")
            .value(is(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.updatedDate")
            .value(is(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.claimServicelineControlNo")
            .value(is(DEFAULT_CLAIM_SERVICELINE_CONTROL_NO))
            .jsonPath("$.procedureIdentifier")
            .value(is(DEFAULT_PROCEDURE_IDENTIFIER))
            .jsonPath("$.insertedByName")
            .value(is(DEFAULT_INSERTED_BY_NAME))
            .jsonPath("$.updatedByName")
            .value(is(DEFAULT_UPDATED_BY_NAME))
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
            .jsonPath("$.primaryClaimResubmisionServicelinesUuid")
            .value(is(DEFAULT_PRIMARY_CLAIM_RESUBMISION_SERVICELINES_UUID.toString()));
    }

    @Test
    void getNonExistingPrimaryClaimResubmisionServicelines() {
        // Get the primaryClaimResubmisionServicelines
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingPrimaryClaimResubmisionServicelines() throws Exception {
        // Initialize the database
        primaryClaimResubmisionServicelinesRepository.save(primaryClaimResubmisionServicelines).block();

        int databaseSizeBeforeUpdate = primaryClaimResubmisionServicelinesRepository.findAll().collectList().block().size();

        // Update the primaryClaimResubmisionServicelines
        PrimaryClaimResubmisionServicelines updatedPrimaryClaimResubmisionServicelines = primaryClaimResubmisionServicelinesRepository
            .findById(primaryClaimResubmisionServicelines.getChangeHealthPrimaryResubmisionServicelinesId())
            .block();
        updatedPrimaryClaimResubmisionServicelines
            .changeHealthPrimaryResubmisionMasterId(UPDATED_CHANGE_HEALTH_PRIMARY_RESUBMISION_MASTER_ID)
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
            .nsertedDate(UPDATED_NSERTED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .claimServicelineControlNo(UPDATED_CLAIM_SERVICELINE_CONTROL_NO)
            .procedureIdentifier(UPDATED_PROCEDURE_IDENTIFIER)
            .insertedByName(UPDATED_INSERTED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .status(UPDATED_STATUS)
            .orderingProviderFirstName(UPDATED_ORDERING_PROVIDER_FIRST_NAME)
            .orderingProviderLastName(UPDATED_ORDERING_PROVIDER_LAST_NAME)
            .orderingProviderNpi(UPDATED_ORDERING_PROVIDER_NPI)
            .reference(UPDATED_REFERENCE)
            .primaryClaimResubmisionServicelinesUuid(UPDATED_PRIMARY_CLAIM_RESUBMISION_SERVICELINES_UUID);
        PrimaryClaimResubmisionServicelinesDTO primaryClaimResubmisionServicelinesDTO = primaryClaimResubmisionServicelinesMapper.toDto(
            updatedPrimaryClaimResubmisionServicelines
        );

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, primaryClaimResubmisionServicelinesDTO.getChangeHealthPrimaryResubmisionServicelinesId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(primaryClaimResubmisionServicelinesDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PrimaryClaimResubmisionServicelines in the database
        List<PrimaryClaimResubmisionServicelines> primaryClaimResubmisionServicelinesList = primaryClaimResubmisionServicelinesRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimResubmisionServicelinesList).hasSize(databaseSizeBeforeUpdate);
        PrimaryClaimResubmisionServicelines testPrimaryClaimResubmisionServicelines = primaryClaimResubmisionServicelinesList.get(
            primaryClaimResubmisionServicelinesList.size() - 1
        );
        assertThat(testPrimaryClaimResubmisionServicelines.getChangeHealthPrimaryResubmisionMasterId())
            .isEqualTo(UPDATED_CHANGE_HEALTH_PRIMARY_RESUBMISION_MASTER_ID);
        assertThat(testPrimaryClaimResubmisionServicelines.getOriginalDos()).isEqualTo(UPDATED_ORIGINAL_DOS);
        assertThat(testPrimaryClaimResubmisionServicelines.getDosTo()).isEqualTo(UPDATED_DOS_TO);
        assertThat(testPrimaryClaimResubmisionServicelines.getProcCode()).isEqualTo(UPDATED_PROC_CODE);
        assertThat(testPrimaryClaimResubmisionServicelines.getChargeAmt()).isEqualTo(UPDATED_CHARGE_AMT);
        assertThat(testPrimaryClaimResubmisionServicelines.getItemUom()).isEqualTo(UPDATED_ITEM_UOM);
        assertThat(testPrimaryClaimResubmisionServicelines.getModifier1()).isEqualTo(UPDATED_MODIFIER_1);
        assertThat(testPrimaryClaimResubmisionServicelines.getModifier2()).isEqualTo(UPDATED_MODIFIER_2);
        assertThat(testPrimaryClaimResubmisionServicelines.getModifier3()).isEqualTo(UPDATED_MODIFIER_3);
        assertThat(testPrimaryClaimResubmisionServicelines.getModifier4()).isEqualTo(UPDATED_MODIFIER_4);
        assertThat(testPrimaryClaimResubmisionServicelines.getIcdPointer()).isEqualTo(UPDATED_ICD_POINTER);
        assertThat(testPrimaryClaimResubmisionServicelines.getQty()).isEqualTo(UPDATED_QTY);
        assertThat(testPrimaryClaimResubmisionServicelines.getInsertedById()).isEqualTo(UPDATED_INSERTED_BY_ID);
        assertThat(testPrimaryClaimResubmisionServicelines.getNsertedDate()).isEqualTo(UPDATED_NSERTED_DATE);
        assertThat(testPrimaryClaimResubmisionServicelines.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPrimaryClaimResubmisionServicelines.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPrimaryClaimResubmisionServicelines.getClaimServicelineControlNo()).isEqualTo(UPDATED_CLAIM_SERVICELINE_CONTROL_NO);
        assertThat(testPrimaryClaimResubmisionServicelines.getProcedureIdentifier()).isEqualTo(UPDATED_PROCEDURE_IDENTIFIER);
        assertThat(testPrimaryClaimResubmisionServicelines.getInsertedByName()).isEqualTo(UPDATED_INSERTED_BY_NAME);
        assertThat(testPrimaryClaimResubmisionServicelines.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPrimaryClaimResubmisionServicelines.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPrimaryClaimResubmisionServicelines.getOrderingProviderFirstName()).isEqualTo(UPDATED_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testPrimaryClaimResubmisionServicelines.getOrderingProviderLastName()).isEqualTo(UPDATED_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testPrimaryClaimResubmisionServicelines.getOrderingProviderNpi()).isEqualTo(UPDATED_ORDERING_PROVIDER_NPI);
        assertThat(testPrimaryClaimResubmisionServicelines.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testPrimaryClaimResubmisionServicelines.getPrimaryClaimResubmisionServicelinesUuid())
            .isEqualTo(UPDATED_PRIMARY_CLAIM_RESUBMISION_SERVICELINES_UUID);
    }

    @Test
    void putNonExistingPrimaryClaimResubmisionServicelines() throws Exception {
        int databaseSizeBeforeUpdate = primaryClaimResubmisionServicelinesRepository.findAll().collectList().block().size();
        primaryClaimResubmisionServicelines.setChangeHealthPrimaryResubmisionServicelinesId(count.incrementAndGet());

        // Create the PrimaryClaimResubmisionServicelines
        PrimaryClaimResubmisionServicelinesDTO primaryClaimResubmisionServicelinesDTO = primaryClaimResubmisionServicelinesMapper.toDto(
            primaryClaimResubmisionServicelines
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, primaryClaimResubmisionServicelinesDTO.getChangeHealthPrimaryResubmisionServicelinesId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(primaryClaimResubmisionServicelinesDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PrimaryClaimResubmisionServicelines in the database
        List<PrimaryClaimResubmisionServicelines> primaryClaimResubmisionServicelinesList = primaryClaimResubmisionServicelinesRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimResubmisionServicelinesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchPrimaryClaimResubmisionServicelines() throws Exception {
        int databaseSizeBeforeUpdate = primaryClaimResubmisionServicelinesRepository.findAll().collectList().block().size();
        primaryClaimResubmisionServicelines.setChangeHealthPrimaryResubmisionServicelinesId(count.incrementAndGet());

        // Create the PrimaryClaimResubmisionServicelines
        PrimaryClaimResubmisionServicelinesDTO primaryClaimResubmisionServicelinesDTO = primaryClaimResubmisionServicelinesMapper.toDto(
            primaryClaimResubmisionServicelines
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(primaryClaimResubmisionServicelinesDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PrimaryClaimResubmisionServicelines in the database
        List<PrimaryClaimResubmisionServicelines> primaryClaimResubmisionServicelinesList = primaryClaimResubmisionServicelinesRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimResubmisionServicelinesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamPrimaryClaimResubmisionServicelines() throws Exception {
        int databaseSizeBeforeUpdate = primaryClaimResubmisionServicelinesRepository.findAll().collectList().block().size();
        primaryClaimResubmisionServicelines.setChangeHealthPrimaryResubmisionServicelinesId(count.incrementAndGet());

        // Create the PrimaryClaimResubmisionServicelines
        PrimaryClaimResubmisionServicelinesDTO primaryClaimResubmisionServicelinesDTO = primaryClaimResubmisionServicelinesMapper.toDto(
            primaryClaimResubmisionServicelines
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(primaryClaimResubmisionServicelinesDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PrimaryClaimResubmisionServicelines in the database
        List<PrimaryClaimResubmisionServicelines> primaryClaimResubmisionServicelinesList = primaryClaimResubmisionServicelinesRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimResubmisionServicelinesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdatePrimaryClaimResubmisionServicelinesWithPatch() throws Exception {
        // Initialize the database
        primaryClaimResubmisionServicelinesRepository.save(primaryClaimResubmisionServicelines).block();

        int databaseSizeBeforeUpdate = primaryClaimResubmisionServicelinesRepository.findAll().collectList().block().size();

        // Update the primaryClaimResubmisionServicelines using partial update
        PrimaryClaimResubmisionServicelines partialUpdatedPrimaryClaimResubmisionServicelines = new PrimaryClaimResubmisionServicelines();
        partialUpdatedPrimaryClaimResubmisionServicelines.setChangeHealthPrimaryResubmisionServicelinesId(
            primaryClaimResubmisionServicelines.getChangeHealthPrimaryResubmisionServicelinesId()
        );

        partialUpdatedPrimaryClaimResubmisionServicelines
            .changeHealthPrimaryResubmisionMasterId(UPDATED_CHANGE_HEALTH_PRIMARY_RESUBMISION_MASTER_ID)
            .dosTo(UPDATED_DOS_TO)
            .procCode(UPDATED_PROC_CODE)
            .chargeAmt(UPDATED_CHARGE_AMT)
            .modifier1(UPDATED_MODIFIER_1)
            .icdPointer(UPDATED_ICD_POINTER)
            .nsertedDate(UPDATED_NSERTED_DATE)
            .claimServicelineControlNo(UPDATED_CLAIM_SERVICELINE_CONTROL_NO)
            .procedureIdentifier(UPDATED_PROCEDURE_IDENTIFIER)
            .insertedByName(UPDATED_INSERTED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .orderingProviderLastName(UPDATED_ORDERING_PROVIDER_LAST_NAME)
            .reference(UPDATED_REFERENCE)
            .primaryClaimResubmisionServicelinesUuid(UPDATED_PRIMARY_CLAIM_RESUBMISION_SERVICELINES_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPrimaryClaimResubmisionServicelines.getChangeHealthPrimaryResubmisionServicelinesId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPrimaryClaimResubmisionServicelines))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PrimaryClaimResubmisionServicelines in the database
        List<PrimaryClaimResubmisionServicelines> primaryClaimResubmisionServicelinesList = primaryClaimResubmisionServicelinesRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimResubmisionServicelinesList).hasSize(databaseSizeBeforeUpdate);
        PrimaryClaimResubmisionServicelines testPrimaryClaimResubmisionServicelines = primaryClaimResubmisionServicelinesList.get(
            primaryClaimResubmisionServicelinesList.size() - 1
        );
        assertThat(testPrimaryClaimResubmisionServicelines.getChangeHealthPrimaryResubmisionMasterId())
            .isEqualTo(UPDATED_CHANGE_HEALTH_PRIMARY_RESUBMISION_MASTER_ID);
        assertThat(testPrimaryClaimResubmisionServicelines.getOriginalDos()).isEqualTo(DEFAULT_ORIGINAL_DOS);
        assertThat(testPrimaryClaimResubmisionServicelines.getDosTo()).isEqualTo(UPDATED_DOS_TO);
        assertThat(testPrimaryClaimResubmisionServicelines.getProcCode()).isEqualTo(UPDATED_PROC_CODE);
        assertThat(testPrimaryClaimResubmisionServicelines.getChargeAmt()).isEqualTo(UPDATED_CHARGE_AMT);
        assertThat(testPrimaryClaimResubmisionServicelines.getItemUom()).isEqualTo(DEFAULT_ITEM_UOM);
        assertThat(testPrimaryClaimResubmisionServicelines.getModifier1()).isEqualTo(UPDATED_MODIFIER_1);
        assertThat(testPrimaryClaimResubmisionServicelines.getModifier2()).isEqualTo(DEFAULT_MODIFIER_2);
        assertThat(testPrimaryClaimResubmisionServicelines.getModifier3()).isEqualTo(DEFAULT_MODIFIER_3);
        assertThat(testPrimaryClaimResubmisionServicelines.getModifier4()).isEqualTo(DEFAULT_MODIFIER_4);
        assertThat(testPrimaryClaimResubmisionServicelines.getIcdPointer()).isEqualTo(UPDATED_ICD_POINTER);
        assertThat(testPrimaryClaimResubmisionServicelines.getQty()).isEqualTo(DEFAULT_QTY);
        assertThat(testPrimaryClaimResubmisionServicelines.getInsertedById()).isEqualTo(DEFAULT_INSERTED_BY_ID);
        assertThat(testPrimaryClaimResubmisionServicelines.getNsertedDate()).isEqualTo(UPDATED_NSERTED_DATE);
        assertThat(testPrimaryClaimResubmisionServicelines.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testPrimaryClaimResubmisionServicelines.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testPrimaryClaimResubmisionServicelines.getClaimServicelineControlNo()).isEqualTo(UPDATED_CLAIM_SERVICELINE_CONTROL_NO);
        assertThat(testPrimaryClaimResubmisionServicelines.getProcedureIdentifier()).isEqualTo(UPDATED_PROCEDURE_IDENTIFIER);
        assertThat(testPrimaryClaimResubmisionServicelines.getInsertedByName()).isEqualTo(UPDATED_INSERTED_BY_NAME);
        assertThat(testPrimaryClaimResubmisionServicelines.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPrimaryClaimResubmisionServicelines.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPrimaryClaimResubmisionServicelines.getOrderingProviderFirstName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testPrimaryClaimResubmisionServicelines.getOrderingProviderLastName()).isEqualTo(UPDATED_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testPrimaryClaimResubmisionServicelines.getOrderingProviderNpi()).isEqualTo(DEFAULT_ORDERING_PROVIDER_NPI);
        assertThat(testPrimaryClaimResubmisionServicelines.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testPrimaryClaimResubmisionServicelines.getPrimaryClaimResubmisionServicelinesUuid())
            .isEqualTo(UPDATED_PRIMARY_CLAIM_RESUBMISION_SERVICELINES_UUID);
    }

    @Test
    void fullUpdatePrimaryClaimResubmisionServicelinesWithPatch() throws Exception {
        // Initialize the database
        primaryClaimResubmisionServicelinesRepository.save(primaryClaimResubmisionServicelines).block();

        int databaseSizeBeforeUpdate = primaryClaimResubmisionServicelinesRepository.findAll().collectList().block().size();

        // Update the primaryClaimResubmisionServicelines using partial update
        PrimaryClaimResubmisionServicelines partialUpdatedPrimaryClaimResubmisionServicelines = new PrimaryClaimResubmisionServicelines();
        partialUpdatedPrimaryClaimResubmisionServicelines.setChangeHealthPrimaryResubmisionServicelinesId(
            primaryClaimResubmisionServicelines.getChangeHealthPrimaryResubmisionServicelinesId()
        );

        partialUpdatedPrimaryClaimResubmisionServicelines
            .changeHealthPrimaryResubmisionMasterId(UPDATED_CHANGE_HEALTH_PRIMARY_RESUBMISION_MASTER_ID)
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
            .nsertedDate(UPDATED_NSERTED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .claimServicelineControlNo(UPDATED_CLAIM_SERVICELINE_CONTROL_NO)
            .procedureIdentifier(UPDATED_PROCEDURE_IDENTIFIER)
            .insertedByName(UPDATED_INSERTED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .status(UPDATED_STATUS)
            .orderingProviderFirstName(UPDATED_ORDERING_PROVIDER_FIRST_NAME)
            .orderingProviderLastName(UPDATED_ORDERING_PROVIDER_LAST_NAME)
            .orderingProviderNpi(UPDATED_ORDERING_PROVIDER_NPI)
            .reference(UPDATED_REFERENCE)
            .primaryClaimResubmisionServicelinesUuid(UPDATED_PRIMARY_CLAIM_RESUBMISION_SERVICELINES_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPrimaryClaimResubmisionServicelines.getChangeHealthPrimaryResubmisionServicelinesId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPrimaryClaimResubmisionServicelines))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PrimaryClaimResubmisionServicelines in the database
        List<PrimaryClaimResubmisionServicelines> primaryClaimResubmisionServicelinesList = primaryClaimResubmisionServicelinesRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimResubmisionServicelinesList).hasSize(databaseSizeBeforeUpdate);
        PrimaryClaimResubmisionServicelines testPrimaryClaimResubmisionServicelines = primaryClaimResubmisionServicelinesList.get(
            primaryClaimResubmisionServicelinesList.size() - 1
        );
        assertThat(testPrimaryClaimResubmisionServicelines.getChangeHealthPrimaryResubmisionMasterId())
            .isEqualTo(UPDATED_CHANGE_HEALTH_PRIMARY_RESUBMISION_MASTER_ID);
        assertThat(testPrimaryClaimResubmisionServicelines.getOriginalDos()).isEqualTo(UPDATED_ORIGINAL_DOS);
        assertThat(testPrimaryClaimResubmisionServicelines.getDosTo()).isEqualTo(UPDATED_DOS_TO);
        assertThat(testPrimaryClaimResubmisionServicelines.getProcCode()).isEqualTo(UPDATED_PROC_CODE);
        assertThat(testPrimaryClaimResubmisionServicelines.getChargeAmt()).isEqualTo(UPDATED_CHARGE_AMT);
        assertThat(testPrimaryClaimResubmisionServicelines.getItemUom()).isEqualTo(UPDATED_ITEM_UOM);
        assertThat(testPrimaryClaimResubmisionServicelines.getModifier1()).isEqualTo(UPDATED_MODIFIER_1);
        assertThat(testPrimaryClaimResubmisionServicelines.getModifier2()).isEqualTo(UPDATED_MODIFIER_2);
        assertThat(testPrimaryClaimResubmisionServicelines.getModifier3()).isEqualTo(UPDATED_MODIFIER_3);
        assertThat(testPrimaryClaimResubmisionServicelines.getModifier4()).isEqualTo(UPDATED_MODIFIER_4);
        assertThat(testPrimaryClaimResubmisionServicelines.getIcdPointer()).isEqualTo(UPDATED_ICD_POINTER);
        assertThat(testPrimaryClaimResubmisionServicelines.getQty()).isEqualTo(UPDATED_QTY);
        assertThat(testPrimaryClaimResubmisionServicelines.getInsertedById()).isEqualTo(UPDATED_INSERTED_BY_ID);
        assertThat(testPrimaryClaimResubmisionServicelines.getNsertedDate()).isEqualTo(UPDATED_NSERTED_DATE);
        assertThat(testPrimaryClaimResubmisionServicelines.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPrimaryClaimResubmisionServicelines.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPrimaryClaimResubmisionServicelines.getClaimServicelineControlNo()).isEqualTo(UPDATED_CLAIM_SERVICELINE_CONTROL_NO);
        assertThat(testPrimaryClaimResubmisionServicelines.getProcedureIdentifier()).isEqualTo(UPDATED_PROCEDURE_IDENTIFIER);
        assertThat(testPrimaryClaimResubmisionServicelines.getInsertedByName()).isEqualTo(UPDATED_INSERTED_BY_NAME);
        assertThat(testPrimaryClaimResubmisionServicelines.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPrimaryClaimResubmisionServicelines.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPrimaryClaimResubmisionServicelines.getOrderingProviderFirstName()).isEqualTo(UPDATED_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testPrimaryClaimResubmisionServicelines.getOrderingProviderLastName()).isEqualTo(UPDATED_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testPrimaryClaimResubmisionServicelines.getOrderingProviderNpi()).isEqualTo(UPDATED_ORDERING_PROVIDER_NPI);
        assertThat(testPrimaryClaimResubmisionServicelines.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testPrimaryClaimResubmisionServicelines.getPrimaryClaimResubmisionServicelinesUuid())
            .isEqualTo(UPDATED_PRIMARY_CLAIM_RESUBMISION_SERVICELINES_UUID);
    }

    @Test
    void patchNonExistingPrimaryClaimResubmisionServicelines() throws Exception {
        int databaseSizeBeforeUpdate = primaryClaimResubmisionServicelinesRepository.findAll().collectList().block().size();
        primaryClaimResubmisionServicelines.setChangeHealthPrimaryResubmisionServicelinesId(count.incrementAndGet());

        // Create the PrimaryClaimResubmisionServicelines
        PrimaryClaimResubmisionServicelinesDTO primaryClaimResubmisionServicelinesDTO = primaryClaimResubmisionServicelinesMapper.toDto(
            primaryClaimResubmisionServicelines
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, primaryClaimResubmisionServicelinesDTO.getChangeHealthPrimaryResubmisionServicelinesId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(primaryClaimResubmisionServicelinesDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PrimaryClaimResubmisionServicelines in the database
        List<PrimaryClaimResubmisionServicelines> primaryClaimResubmisionServicelinesList = primaryClaimResubmisionServicelinesRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimResubmisionServicelinesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchPrimaryClaimResubmisionServicelines() throws Exception {
        int databaseSizeBeforeUpdate = primaryClaimResubmisionServicelinesRepository.findAll().collectList().block().size();
        primaryClaimResubmisionServicelines.setChangeHealthPrimaryResubmisionServicelinesId(count.incrementAndGet());

        // Create the PrimaryClaimResubmisionServicelines
        PrimaryClaimResubmisionServicelinesDTO primaryClaimResubmisionServicelinesDTO = primaryClaimResubmisionServicelinesMapper.toDto(
            primaryClaimResubmisionServicelines
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(primaryClaimResubmisionServicelinesDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PrimaryClaimResubmisionServicelines in the database
        List<PrimaryClaimResubmisionServicelines> primaryClaimResubmisionServicelinesList = primaryClaimResubmisionServicelinesRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimResubmisionServicelinesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamPrimaryClaimResubmisionServicelines() throws Exception {
        int databaseSizeBeforeUpdate = primaryClaimResubmisionServicelinesRepository.findAll().collectList().block().size();
        primaryClaimResubmisionServicelines.setChangeHealthPrimaryResubmisionServicelinesId(count.incrementAndGet());

        // Create the PrimaryClaimResubmisionServicelines
        PrimaryClaimResubmisionServicelinesDTO primaryClaimResubmisionServicelinesDTO = primaryClaimResubmisionServicelinesMapper.toDto(
            primaryClaimResubmisionServicelines
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(primaryClaimResubmisionServicelinesDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PrimaryClaimResubmisionServicelines in the database
        List<PrimaryClaimResubmisionServicelines> primaryClaimResubmisionServicelinesList = primaryClaimResubmisionServicelinesRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimResubmisionServicelinesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deletePrimaryClaimResubmisionServicelines() {
        // Initialize the database
        primaryClaimResubmisionServicelinesRepository.save(primaryClaimResubmisionServicelines).block();

        int databaseSizeBeforeDelete = primaryClaimResubmisionServicelinesRepository.findAll().collectList().block().size();

        // Delete the primaryClaimResubmisionServicelines
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, primaryClaimResubmisionServicelines.getChangeHealthPrimaryResubmisionServicelinesId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<PrimaryClaimResubmisionServicelines> primaryClaimResubmisionServicelinesList = primaryClaimResubmisionServicelinesRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimResubmisionServicelinesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
