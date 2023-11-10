package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.PrimaryClaimSubmisionServicelines;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.PrimaryClaimSubmisionServicelinesRepository;
import com.sunknowledge.dme.rcm.service.dto.PrimaryClaimSubmisionServicelinesDTO;
import com.sunknowledge.dme.rcm.service.mapper.PrimaryClaimSubmisionServicelinesMapper;
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
 * Integration tests for the {@link PrimaryClaimSubmisionServicelinesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class PrimaryClaimSubmisionServicelinesResourceIT {

    private static final Long DEFAULT_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID = 1L;
    private static final Long UPDATED_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID = 2L;

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

    private static final LocalDate DEFAULT_INSERTED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_INSERTED_DATE = LocalDate.now(ZoneId.systemDefault());

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

    private static final UUID DEFAULT_PRIMARY_CLAIM_SUBMISION_SERVICELINES_UUID = UUID.randomUUID();
    private static final UUID UPDATED_PRIMARY_CLAIM_SUBMISION_SERVICELINES_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/primary-claim-submision-servicelines";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{changeHealthPrimarySubmisionServicelinesId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PrimaryClaimSubmisionServicelinesRepository primaryClaimSubmisionServicelinesRepository;

    @Autowired
    private PrimaryClaimSubmisionServicelinesMapper primaryClaimSubmisionServicelinesMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private PrimaryClaimSubmisionServicelines primaryClaimSubmisionServicelines;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PrimaryClaimSubmisionServicelines createEntity(EntityManager em) {
        PrimaryClaimSubmisionServicelines primaryClaimSubmisionServicelines = new PrimaryClaimSubmisionServicelines()
            .changeHealthPrimarySubmisionMasterId(DEFAULT_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID)
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
            .primaryClaimSubmisionServicelinesUuid(DEFAULT_PRIMARY_CLAIM_SUBMISION_SERVICELINES_UUID);
        return primaryClaimSubmisionServicelines;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PrimaryClaimSubmisionServicelines createUpdatedEntity(EntityManager em) {
        PrimaryClaimSubmisionServicelines primaryClaimSubmisionServicelines = new PrimaryClaimSubmisionServicelines()
            .changeHealthPrimarySubmisionMasterId(UPDATED_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID)
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
            .primaryClaimSubmisionServicelinesUuid(UPDATED_PRIMARY_CLAIM_SUBMISION_SERVICELINES_UUID);
        return primaryClaimSubmisionServicelines;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(PrimaryClaimSubmisionServicelines.class).block();
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
        primaryClaimSubmisionServicelines = createEntity(em);
    }

    @Test
    void createPrimaryClaimSubmisionServicelines() throws Exception {
        int databaseSizeBeforeCreate = primaryClaimSubmisionServicelinesRepository.findAll().collectList().block().size();
        // Create the PrimaryClaimSubmisionServicelines
        PrimaryClaimSubmisionServicelinesDTO primaryClaimSubmisionServicelinesDTO = primaryClaimSubmisionServicelinesMapper.toDto(
            primaryClaimSubmisionServicelines
        );
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(primaryClaimSubmisionServicelinesDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the PrimaryClaimSubmisionServicelines in the database
        List<PrimaryClaimSubmisionServicelines> primaryClaimSubmisionServicelinesList = primaryClaimSubmisionServicelinesRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimSubmisionServicelinesList).hasSize(databaseSizeBeforeCreate + 1);
        PrimaryClaimSubmisionServicelines testPrimaryClaimSubmisionServicelines = primaryClaimSubmisionServicelinesList.get(
            primaryClaimSubmisionServicelinesList.size() - 1
        );
        assertThat(testPrimaryClaimSubmisionServicelines.getChangeHealthPrimarySubmisionMasterId())
            .isEqualTo(DEFAULT_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID);
        assertThat(testPrimaryClaimSubmisionServicelines.getOriginalDos()).isEqualTo(DEFAULT_ORIGINAL_DOS);
        assertThat(testPrimaryClaimSubmisionServicelines.getDosTo()).isEqualTo(DEFAULT_DOS_TO);
        assertThat(testPrimaryClaimSubmisionServicelines.getProcCode()).isEqualTo(DEFAULT_PROC_CODE);
        assertThat(testPrimaryClaimSubmisionServicelines.getChargeAmt()).isEqualTo(DEFAULT_CHARGE_AMT);
        assertThat(testPrimaryClaimSubmisionServicelines.getItemUom()).isEqualTo(DEFAULT_ITEM_UOM);
        assertThat(testPrimaryClaimSubmisionServicelines.getModifier1()).isEqualTo(DEFAULT_MODIFIER_1);
        assertThat(testPrimaryClaimSubmisionServicelines.getModifier2()).isEqualTo(DEFAULT_MODIFIER_2);
        assertThat(testPrimaryClaimSubmisionServicelines.getModifier3()).isEqualTo(DEFAULT_MODIFIER_3);
        assertThat(testPrimaryClaimSubmisionServicelines.getModifier4()).isEqualTo(DEFAULT_MODIFIER_4);
        assertThat(testPrimaryClaimSubmisionServicelines.getIcdPointer()).isEqualTo(DEFAULT_ICD_POINTER);
        assertThat(testPrimaryClaimSubmisionServicelines.getQty()).isEqualTo(DEFAULT_QTY);
        assertThat(testPrimaryClaimSubmisionServicelines.getInsertedById()).isEqualTo(DEFAULT_INSERTED_BY_ID);
        assertThat(testPrimaryClaimSubmisionServicelines.getInsertedDate()).isEqualTo(DEFAULT_INSERTED_DATE);
        assertThat(testPrimaryClaimSubmisionServicelines.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testPrimaryClaimSubmisionServicelines.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testPrimaryClaimSubmisionServicelines.getClaimServicelineControlNo()).isEqualTo(DEFAULT_CLAIM_SERVICELINE_CONTROL_NO);
        assertThat(testPrimaryClaimSubmisionServicelines.getProcedureIdentifier()).isEqualTo(DEFAULT_PROCEDURE_IDENTIFIER);
        assertThat(testPrimaryClaimSubmisionServicelines.getInsertedByName()).isEqualTo(DEFAULT_INSERTED_BY_NAME);
        assertThat(testPrimaryClaimSubmisionServicelines.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testPrimaryClaimSubmisionServicelines.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPrimaryClaimSubmisionServicelines.getOrderingProviderFirstName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testPrimaryClaimSubmisionServicelines.getOrderingProviderLastName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testPrimaryClaimSubmisionServicelines.getOrderingProviderNpi()).isEqualTo(DEFAULT_ORDERING_PROVIDER_NPI);
        assertThat(testPrimaryClaimSubmisionServicelines.getReference()).isEqualTo(DEFAULT_REFERENCE);
        assertThat(testPrimaryClaimSubmisionServicelines.getPrimaryClaimSubmisionServicelinesUuid())
            .isEqualTo(DEFAULT_PRIMARY_CLAIM_SUBMISION_SERVICELINES_UUID);
    }

    @Test
    void createPrimaryClaimSubmisionServicelinesWithExistingId() throws Exception {
        // Create the PrimaryClaimSubmisionServicelines with an existing ID
        primaryClaimSubmisionServicelines.setChangeHealthPrimarySubmisionServicelinesId(1L);
        PrimaryClaimSubmisionServicelinesDTO primaryClaimSubmisionServicelinesDTO = primaryClaimSubmisionServicelinesMapper.toDto(
            primaryClaimSubmisionServicelines
        );

        int databaseSizeBeforeCreate = primaryClaimSubmisionServicelinesRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(primaryClaimSubmisionServicelinesDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PrimaryClaimSubmisionServicelines in the database
        List<PrimaryClaimSubmisionServicelines> primaryClaimSubmisionServicelinesList = primaryClaimSubmisionServicelinesRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimSubmisionServicelinesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllPrimaryClaimSubmisionServicelines() {
        // Initialize the database
        primaryClaimSubmisionServicelinesRepository.save(primaryClaimSubmisionServicelines).block();

        // Get all the primaryClaimSubmisionServicelinesList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=changeHealthPrimarySubmisionServicelinesId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].changeHealthPrimarySubmisionServicelinesId")
            .value(hasItem(primaryClaimSubmisionServicelines.getChangeHealthPrimarySubmisionServicelinesId().intValue()))
            .jsonPath("$.[*].changeHealthPrimarySubmisionMasterId")
            .value(hasItem(DEFAULT_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID.intValue()))
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
            .jsonPath("$.[*].insertedDate")
            .value(hasItem(DEFAULT_INSERTED_DATE.toString()))
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
            .jsonPath("$.[*].primaryClaimSubmisionServicelinesUuid")
            .value(hasItem(DEFAULT_PRIMARY_CLAIM_SUBMISION_SERVICELINES_UUID.toString()));
    }

    @Test
    void getPrimaryClaimSubmisionServicelines() {
        // Initialize the database
        primaryClaimSubmisionServicelinesRepository.save(primaryClaimSubmisionServicelines).block();

        // Get the primaryClaimSubmisionServicelines
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, primaryClaimSubmisionServicelines.getChangeHealthPrimarySubmisionServicelinesId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.changeHealthPrimarySubmisionServicelinesId")
            .value(is(primaryClaimSubmisionServicelines.getChangeHealthPrimarySubmisionServicelinesId().intValue()))
            .jsonPath("$.changeHealthPrimarySubmisionMasterId")
            .value(is(DEFAULT_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID.intValue()))
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
            .jsonPath("$.insertedDate")
            .value(is(DEFAULT_INSERTED_DATE.toString()))
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
            .jsonPath("$.primaryClaimSubmisionServicelinesUuid")
            .value(is(DEFAULT_PRIMARY_CLAIM_SUBMISION_SERVICELINES_UUID.toString()));
    }

    @Test
    void getNonExistingPrimaryClaimSubmisionServicelines() {
        // Get the primaryClaimSubmisionServicelines
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putNewPrimaryClaimSubmisionServicelines() throws Exception {
        // Initialize the database
        primaryClaimSubmisionServicelinesRepository.save(primaryClaimSubmisionServicelines).block();

        int databaseSizeBeforeUpdate = primaryClaimSubmisionServicelinesRepository.findAll().collectList().block().size();

        // Update the primaryClaimSubmisionServicelines
        PrimaryClaimSubmisionServicelines updatedPrimaryClaimSubmisionServicelines = primaryClaimSubmisionServicelinesRepository
            .findById(primaryClaimSubmisionServicelines.getChangeHealthPrimarySubmisionServicelinesId())
            .block();
        updatedPrimaryClaimSubmisionServicelines
            .changeHealthPrimarySubmisionMasterId(UPDATED_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID)
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
            .primaryClaimSubmisionServicelinesUuid(UPDATED_PRIMARY_CLAIM_SUBMISION_SERVICELINES_UUID);
        PrimaryClaimSubmisionServicelinesDTO primaryClaimSubmisionServicelinesDTO = primaryClaimSubmisionServicelinesMapper.toDto(
            updatedPrimaryClaimSubmisionServicelines
        );

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, primaryClaimSubmisionServicelinesDTO.getChangeHealthPrimarySubmisionServicelinesId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(primaryClaimSubmisionServicelinesDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PrimaryClaimSubmisionServicelines in the database
        List<PrimaryClaimSubmisionServicelines> primaryClaimSubmisionServicelinesList = primaryClaimSubmisionServicelinesRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimSubmisionServicelinesList).hasSize(databaseSizeBeforeUpdate);
        PrimaryClaimSubmisionServicelines testPrimaryClaimSubmisionServicelines = primaryClaimSubmisionServicelinesList.get(
            primaryClaimSubmisionServicelinesList.size() - 1
        );
        assertThat(testPrimaryClaimSubmisionServicelines.getChangeHealthPrimarySubmisionMasterId())
            .isEqualTo(UPDATED_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID);
        assertThat(testPrimaryClaimSubmisionServicelines.getOriginalDos()).isEqualTo(UPDATED_ORIGINAL_DOS);
        assertThat(testPrimaryClaimSubmisionServicelines.getDosTo()).isEqualTo(UPDATED_DOS_TO);
        assertThat(testPrimaryClaimSubmisionServicelines.getProcCode()).isEqualTo(UPDATED_PROC_CODE);
        assertThat(testPrimaryClaimSubmisionServicelines.getChargeAmt()).isEqualTo(UPDATED_CHARGE_AMT);
        assertThat(testPrimaryClaimSubmisionServicelines.getItemUom()).isEqualTo(UPDATED_ITEM_UOM);
        assertThat(testPrimaryClaimSubmisionServicelines.getModifier1()).isEqualTo(UPDATED_MODIFIER_1);
        assertThat(testPrimaryClaimSubmisionServicelines.getModifier2()).isEqualTo(UPDATED_MODIFIER_2);
        assertThat(testPrimaryClaimSubmisionServicelines.getModifier3()).isEqualTo(UPDATED_MODIFIER_3);
        assertThat(testPrimaryClaimSubmisionServicelines.getModifier4()).isEqualTo(UPDATED_MODIFIER_4);
        assertThat(testPrimaryClaimSubmisionServicelines.getIcdPointer()).isEqualTo(UPDATED_ICD_POINTER);
        assertThat(testPrimaryClaimSubmisionServicelines.getQty()).isEqualTo(UPDATED_QTY);
        assertThat(testPrimaryClaimSubmisionServicelines.getInsertedById()).isEqualTo(UPDATED_INSERTED_BY_ID);
        assertThat(testPrimaryClaimSubmisionServicelines.getInsertedDate()).isEqualTo(UPDATED_INSERTED_DATE);
        assertThat(testPrimaryClaimSubmisionServicelines.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPrimaryClaimSubmisionServicelines.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPrimaryClaimSubmisionServicelines.getClaimServicelineControlNo()).isEqualTo(UPDATED_CLAIM_SERVICELINE_CONTROL_NO);
        assertThat(testPrimaryClaimSubmisionServicelines.getProcedureIdentifier()).isEqualTo(UPDATED_PROCEDURE_IDENTIFIER);
        assertThat(testPrimaryClaimSubmisionServicelines.getInsertedByName()).isEqualTo(UPDATED_INSERTED_BY_NAME);
        assertThat(testPrimaryClaimSubmisionServicelines.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPrimaryClaimSubmisionServicelines.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPrimaryClaimSubmisionServicelines.getOrderingProviderFirstName()).isEqualTo(UPDATED_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testPrimaryClaimSubmisionServicelines.getOrderingProviderLastName()).isEqualTo(UPDATED_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testPrimaryClaimSubmisionServicelines.getOrderingProviderNpi()).isEqualTo(UPDATED_ORDERING_PROVIDER_NPI);
        assertThat(testPrimaryClaimSubmisionServicelines.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testPrimaryClaimSubmisionServicelines.getPrimaryClaimSubmisionServicelinesUuid())
            .isEqualTo(UPDATED_PRIMARY_CLAIM_SUBMISION_SERVICELINES_UUID);
    }

    @Test
    void putNonExistingPrimaryClaimSubmisionServicelines() throws Exception {
        int databaseSizeBeforeUpdate = primaryClaimSubmisionServicelinesRepository.findAll().collectList().block().size();
        primaryClaimSubmisionServicelines.setChangeHealthPrimarySubmisionServicelinesId(count.incrementAndGet());

        // Create the PrimaryClaimSubmisionServicelines
        PrimaryClaimSubmisionServicelinesDTO primaryClaimSubmisionServicelinesDTO = primaryClaimSubmisionServicelinesMapper.toDto(
            primaryClaimSubmisionServicelines
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, primaryClaimSubmisionServicelinesDTO.getChangeHealthPrimarySubmisionServicelinesId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(primaryClaimSubmisionServicelinesDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PrimaryClaimSubmisionServicelines in the database
        List<PrimaryClaimSubmisionServicelines> primaryClaimSubmisionServicelinesList = primaryClaimSubmisionServicelinesRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimSubmisionServicelinesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchPrimaryClaimSubmisionServicelines() throws Exception {
        int databaseSizeBeforeUpdate = primaryClaimSubmisionServicelinesRepository.findAll().collectList().block().size();
        primaryClaimSubmisionServicelines.setChangeHealthPrimarySubmisionServicelinesId(count.incrementAndGet());

        // Create the PrimaryClaimSubmisionServicelines
        PrimaryClaimSubmisionServicelinesDTO primaryClaimSubmisionServicelinesDTO = primaryClaimSubmisionServicelinesMapper.toDto(
            primaryClaimSubmisionServicelines
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(primaryClaimSubmisionServicelinesDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PrimaryClaimSubmisionServicelines in the database
        List<PrimaryClaimSubmisionServicelines> primaryClaimSubmisionServicelinesList = primaryClaimSubmisionServicelinesRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimSubmisionServicelinesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamPrimaryClaimSubmisionServicelines() throws Exception {
        int databaseSizeBeforeUpdate = primaryClaimSubmisionServicelinesRepository.findAll().collectList().block().size();
        primaryClaimSubmisionServicelines.setChangeHealthPrimarySubmisionServicelinesId(count.incrementAndGet());

        // Create the PrimaryClaimSubmisionServicelines
        PrimaryClaimSubmisionServicelinesDTO primaryClaimSubmisionServicelinesDTO = primaryClaimSubmisionServicelinesMapper.toDto(
            primaryClaimSubmisionServicelines
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(primaryClaimSubmisionServicelinesDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PrimaryClaimSubmisionServicelines in the database
        List<PrimaryClaimSubmisionServicelines> primaryClaimSubmisionServicelinesList = primaryClaimSubmisionServicelinesRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimSubmisionServicelinesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdatePrimaryClaimSubmisionServicelinesWithPatch() throws Exception {
        // Initialize the database
        primaryClaimSubmisionServicelinesRepository.save(primaryClaimSubmisionServicelines).block();

        int databaseSizeBeforeUpdate = primaryClaimSubmisionServicelinesRepository.findAll().collectList().block().size();

        // Update the primaryClaimSubmisionServicelines using partial update
        PrimaryClaimSubmisionServicelines partialUpdatedPrimaryClaimSubmisionServicelines = new PrimaryClaimSubmisionServicelines();
        partialUpdatedPrimaryClaimSubmisionServicelines.setChangeHealthPrimarySubmisionServicelinesId(
            primaryClaimSubmisionServicelines.getChangeHealthPrimarySubmisionServicelinesId()
        );

        partialUpdatedPrimaryClaimSubmisionServicelines
            .changeHealthPrimarySubmisionMasterId(UPDATED_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID)
            .modifier3(UPDATED_MODIFIER_3)
            .modifier4(UPDATED_MODIFIER_4)
            .icdPointer(UPDATED_ICD_POINTER)
            .qty(UPDATED_QTY)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .procedureIdentifier(UPDATED_PROCEDURE_IDENTIFIER)
            .status(UPDATED_STATUS)
            .reference(UPDATED_REFERENCE)
            .primaryClaimSubmisionServicelinesUuid(UPDATED_PRIMARY_CLAIM_SUBMISION_SERVICELINES_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPrimaryClaimSubmisionServicelines.getChangeHealthPrimarySubmisionServicelinesId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPrimaryClaimSubmisionServicelines))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PrimaryClaimSubmisionServicelines in the database
        List<PrimaryClaimSubmisionServicelines> primaryClaimSubmisionServicelinesList = primaryClaimSubmisionServicelinesRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimSubmisionServicelinesList).hasSize(databaseSizeBeforeUpdate);
        PrimaryClaimSubmisionServicelines testPrimaryClaimSubmisionServicelines = primaryClaimSubmisionServicelinesList.get(
            primaryClaimSubmisionServicelinesList.size() - 1
        );
        assertThat(testPrimaryClaimSubmisionServicelines.getChangeHealthPrimarySubmisionMasterId())
            .isEqualTo(UPDATED_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID);
        assertThat(testPrimaryClaimSubmisionServicelines.getOriginalDos()).isEqualTo(DEFAULT_ORIGINAL_DOS);
        assertThat(testPrimaryClaimSubmisionServicelines.getDosTo()).isEqualTo(DEFAULT_DOS_TO);
        assertThat(testPrimaryClaimSubmisionServicelines.getProcCode()).isEqualTo(DEFAULT_PROC_CODE);
        assertThat(testPrimaryClaimSubmisionServicelines.getChargeAmt()).isEqualTo(DEFAULT_CHARGE_AMT);
        assertThat(testPrimaryClaimSubmisionServicelines.getItemUom()).isEqualTo(DEFAULT_ITEM_UOM);
        assertThat(testPrimaryClaimSubmisionServicelines.getModifier1()).isEqualTo(DEFAULT_MODIFIER_1);
        assertThat(testPrimaryClaimSubmisionServicelines.getModifier2()).isEqualTo(DEFAULT_MODIFIER_2);
        assertThat(testPrimaryClaimSubmisionServicelines.getModifier3()).isEqualTo(UPDATED_MODIFIER_3);
        assertThat(testPrimaryClaimSubmisionServicelines.getModifier4()).isEqualTo(UPDATED_MODIFIER_4);
        assertThat(testPrimaryClaimSubmisionServicelines.getIcdPointer()).isEqualTo(UPDATED_ICD_POINTER);
        assertThat(testPrimaryClaimSubmisionServicelines.getQty()).isEqualTo(UPDATED_QTY);
        assertThat(testPrimaryClaimSubmisionServicelines.getInsertedById()).isEqualTo(DEFAULT_INSERTED_BY_ID);
        assertThat(testPrimaryClaimSubmisionServicelines.getInsertedDate()).isEqualTo(DEFAULT_INSERTED_DATE);
        assertThat(testPrimaryClaimSubmisionServicelines.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPrimaryClaimSubmisionServicelines.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPrimaryClaimSubmisionServicelines.getClaimServicelineControlNo()).isEqualTo(DEFAULT_CLAIM_SERVICELINE_CONTROL_NO);
        assertThat(testPrimaryClaimSubmisionServicelines.getProcedureIdentifier()).isEqualTo(UPDATED_PROCEDURE_IDENTIFIER);
        assertThat(testPrimaryClaimSubmisionServicelines.getInsertedByName()).isEqualTo(DEFAULT_INSERTED_BY_NAME);
        assertThat(testPrimaryClaimSubmisionServicelines.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testPrimaryClaimSubmisionServicelines.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPrimaryClaimSubmisionServicelines.getOrderingProviderFirstName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testPrimaryClaimSubmisionServicelines.getOrderingProviderLastName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testPrimaryClaimSubmisionServicelines.getOrderingProviderNpi()).isEqualTo(DEFAULT_ORDERING_PROVIDER_NPI);
        assertThat(testPrimaryClaimSubmisionServicelines.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testPrimaryClaimSubmisionServicelines.getPrimaryClaimSubmisionServicelinesUuid())
            .isEqualTo(UPDATED_PRIMARY_CLAIM_SUBMISION_SERVICELINES_UUID);
    }

    @Test
    void fullUpdatePrimaryClaimSubmisionServicelinesWithPatch() throws Exception {
        // Initialize the database
        primaryClaimSubmisionServicelinesRepository.save(primaryClaimSubmisionServicelines).block();

        int databaseSizeBeforeUpdate = primaryClaimSubmisionServicelinesRepository.findAll().collectList().block().size();

        // Update the primaryClaimSubmisionServicelines using partial update
        PrimaryClaimSubmisionServicelines partialUpdatedPrimaryClaimSubmisionServicelines = new PrimaryClaimSubmisionServicelines();
        partialUpdatedPrimaryClaimSubmisionServicelines.setChangeHealthPrimarySubmisionServicelinesId(
            primaryClaimSubmisionServicelines.getChangeHealthPrimarySubmisionServicelinesId()
        );

        partialUpdatedPrimaryClaimSubmisionServicelines
            .changeHealthPrimarySubmisionMasterId(UPDATED_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID)
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
            .primaryClaimSubmisionServicelinesUuid(UPDATED_PRIMARY_CLAIM_SUBMISION_SERVICELINES_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPrimaryClaimSubmisionServicelines.getChangeHealthPrimarySubmisionServicelinesId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPrimaryClaimSubmisionServicelines))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PrimaryClaimSubmisionServicelines in the database
        List<PrimaryClaimSubmisionServicelines> primaryClaimSubmisionServicelinesList = primaryClaimSubmisionServicelinesRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimSubmisionServicelinesList).hasSize(databaseSizeBeforeUpdate);
        PrimaryClaimSubmisionServicelines testPrimaryClaimSubmisionServicelines = primaryClaimSubmisionServicelinesList.get(
            primaryClaimSubmisionServicelinesList.size() - 1
        );
        assertThat(testPrimaryClaimSubmisionServicelines.getChangeHealthPrimarySubmisionMasterId())
            .isEqualTo(UPDATED_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID);
        assertThat(testPrimaryClaimSubmisionServicelines.getOriginalDos()).isEqualTo(UPDATED_ORIGINAL_DOS);
        assertThat(testPrimaryClaimSubmisionServicelines.getDosTo()).isEqualTo(UPDATED_DOS_TO);
        assertThat(testPrimaryClaimSubmisionServicelines.getProcCode()).isEqualTo(UPDATED_PROC_CODE);
        assertThat(testPrimaryClaimSubmisionServicelines.getChargeAmt()).isEqualTo(UPDATED_CHARGE_AMT);
        assertThat(testPrimaryClaimSubmisionServicelines.getItemUom()).isEqualTo(UPDATED_ITEM_UOM);
        assertThat(testPrimaryClaimSubmisionServicelines.getModifier1()).isEqualTo(UPDATED_MODIFIER_1);
        assertThat(testPrimaryClaimSubmisionServicelines.getModifier2()).isEqualTo(UPDATED_MODIFIER_2);
        assertThat(testPrimaryClaimSubmisionServicelines.getModifier3()).isEqualTo(UPDATED_MODIFIER_3);
        assertThat(testPrimaryClaimSubmisionServicelines.getModifier4()).isEqualTo(UPDATED_MODIFIER_4);
        assertThat(testPrimaryClaimSubmisionServicelines.getIcdPointer()).isEqualTo(UPDATED_ICD_POINTER);
        assertThat(testPrimaryClaimSubmisionServicelines.getQty()).isEqualTo(UPDATED_QTY);
        assertThat(testPrimaryClaimSubmisionServicelines.getInsertedById()).isEqualTo(UPDATED_INSERTED_BY_ID);
        assertThat(testPrimaryClaimSubmisionServicelines.getInsertedDate()).isEqualTo(UPDATED_INSERTED_DATE);
        assertThat(testPrimaryClaimSubmisionServicelines.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPrimaryClaimSubmisionServicelines.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPrimaryClaimSubmisionServicelines.getClaimServicelineControlNo()).isEqualTo(UPDATED_CLAIM_SERVICELINE_CONTROL_NO);
        assertThat(testPrimaryClaimSubmisionServicelines.getProcedureIdentifier()).isEqualTo(UPDATED_PROCEDURE_IDENTIFIER);
        assertThat(testPrimaryClaimSubmisionServicelines.getInsertedByName()).isEqualTo(UPDATED_INSERTED_BY_NAME);
        assertThat(testPrimaryClaimSubmisionServicelines.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPrimaryClaimSubmisionServicelines.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPrimaryClaimSubmisionServicelines.getOrderingProviderFirstName()).isEqualTo(UPDATED_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testPrimaryClaimSubmisionServicelines.getOrderingProviderLastName()).isEqualTo(UPDATED_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testPrimaryClaimSubmisionServicelines.getOrderingProviderNpi()).isEqualTo(UPDATED_ORDERING_PROVIDER_NPI);
        assertThat(testPrimaryClaimSubmisionServicelines.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testPrimaryClaimSubmisionServicelines.getPrimaryClaimSubmisionServicelinesUuid())
            .isEqualTo(UPDATED_PRIMARY_CLAIM_SUBMISION_SERVICELINES_UUID);
    }

    @Test
    void patchNonExistingPrimaryClaimSubmisionServicelines() throws Exception {
        int databaseSizeBeforeUpdate = primaryClaimSubmisionServicelinesRepository.findAll().collectList().block().size();
        primaryClaimSubmisionServicelines.setChangeHealthPrimarySubmisionServicelinesId(count.incrementAndGet());

        // Create the PrimaryClaimSubmisionServicelines
        PrimaryClaimSubmisionServicelinesDTO primaryClaimSubmisionServicelinesDTO = primaryClaimSubmisionServicelinesMapper.toDto(
            primaryClaimSubmisionServicelines
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, primaryClaimSubmisionServicelinesDTO.getChangeHealthPrimarySubmisionServicelinesId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(primaryClaimSubmisionServicelinesDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PrimaryClaimSubmisionServicelines in the database
        List<PrimaryClaimSubmisionServicelines> primaryClaimSubmisionServicelinesList = primaryClaimSubmisionServicelinesRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimSubmisionServicelinesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchPrimaryClaimSubmisionServicelines() throws Exception {
        int databaseSizeBeforeUpdate = primaryClaimSubmisionServicelinesRepository.findAll().collectList().block().size();
        primaryClaimSubmisionServicelines.setChangeHealthPrimarySubmisionServicelinesId(count.incrementAndGet());

        // Create the PrimaryClaimSubmisionServicelines
        PrimaryClaimSubmisionServicelinesDTO primaryClaimSubmisionServicelinesDTO = primaryClaimSubmisionServicelinesMapper.toDto(
            primaryClaimSubmisionServicelines
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(primaryClaimSubmisionServicelinesDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PrimaryClaimSubmisionServicelines in the database
        List<PrimaryClaimSubmisionServicelines> primaryClaimSubmisionServicelinesList = primaryClaimSubmisionServicelinesRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimSubmisionServicelinesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamPrimaryClaimSubmisionServicelines() throws Exception {
        int databaseSizeBeforeUpdate = primaryClaimSubmisionServicelinesRepository.findAll().collectList().block().size();
        primaryClaimSubmisionServicelines.setChangeHealthPrimarySubmisionServicelinesId(count.incrementAndGet());

        // Create the PrimaryClaimSubmisionServicelines
        PrimaryClaimSubmisionServicelinesDTO primaryClaimSubmisionServicelinesDTO = primaryClaimSubmisionServicelinesMapper.toDto(
            primaryClaimSubmisionServicelines
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(primaryClaimSubmisionServicelinesDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PrimaryClaimSubmisionServicelines in the database
        List<PrimaryClaimSubmisionServicelines> primaryClaimSubmisionServicelinesList = primaryClaimSubmisionServicelinesRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimSubmisionServicelinesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deletePrimaryClaimSubmisionServicelines() {
        // Initialize the database
        primaryClaimSubmisionServicelinesRepository.save(primaryClaimSubmisionServicelines).block();

        int databaseSizeBeforeDelete = primaryClaimSubmisionServicelinesRepository.findAll().collectList().block().size();

        // Delete the primaryClaimSubmisionServicelines
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, primaryClaimSubmisionServicelines.getChangeHealthPrimarySubmisionServicelinesId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<PrimaryClaimSubmisionServicelines> primaryClaimSubmisionServicelinesList = primaryClaimSubmisionServicelinesRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimSubmisionServicelinesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
