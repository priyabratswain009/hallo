package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.SoRecurringPurchase;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.SoRecurringPurchaseRepository;
import com.sunknowledge.dme.rcm.service.dto.SoRecurringPurchaseDTO;
import com.sunknowledge.dme.rcm.service.mapper.SoRecurringPurchaseMapper;
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
 * Integration tests for the {@link SoRecurringPurchaseResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class SoRecurringPurchaseResourceIT {

    private static final Long DEFAULT_SO_ID = 1L;
    private static final Long UPDATED_SO_ID = 2L;

    private static final Long DEFAULT_ITEM_ID = 1L;
    private static final Long UPDATED_ITEM_ID = 2L;

    private static final String DEFAULT_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PROC_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PROC_CODE = "BBBBBBBBBB";

    private static final Double DEFAULT_QTY = 1D;
    private static final Double UPDATED_QTY = 2D;

    private static final Double DEFAULT_BILLING_INTERVAL = 1D;
    private static final Double UPDATED_BILLING_INTERVAL = 2D;

    private static final LocalDate DEFAULT_INITIAL_DOS = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_INITIAL_DOS = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_PERIOD = 1D;
    private static final Double UPDATED_PERIOD = 2D;

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final UUID DEFAULT_SO_RECURRING_PURCHASE_UUID = UUID.randomUUID();
    private static final UUID UPDATED_SO_RECURRING_PURCHASE_UUID = UUID.randomUUID();

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/so-recurring-purchases";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{rpId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SoRecurringPurchaseRepository soRecurringPurchaseRepository;

    @Autowired
    private SoRecurringPurchaseMapper soRecurringPurchaseMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private SoRecurringPurchase soRecurringPurchase;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SoRecurringPurchase createEntity(EntityManager em) {
        SoRecurringPurchase soRecurringPurchase = new SoRecurringPurchase()
            .soId(DEFAULT_SO_ID)
            .itemId(DEFAULT_ITEM_ID)
            .itemName(DEFAULT_ITEM_NAME)
            .procCode(DEFAULT_PROC_CODE)
            .qty(DEFAULT_QTY)
            .billingInterval(DEFAULT_BILLING_INTERVAL)
            .initialDos(DEFAULT_INITIAL_DOS)
            .period(DEFAULT_PERIOD)
            .status(DEFAULT_STATUS)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdById(DEFAULT_CREATED_BY_ID)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .soRecurringPurchaseUuid(DEFAULT_SO_RECURRING_PURCHASE_UUID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME);
        return soRecurringPurchase;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SoRecurringPurchase createUpdatedEntity(EntityManager em) {
        SoRecurringPurchase soRecurringPurchase = new SoRecurringPurchase()
            .soId(UPDATED_SO_ID)
            .itemId(UPDATED_ITEM_ID)
            .itemName(UPDATED_ITEM_NAME)
            .procCode(UPDATED_PROC_CODE)
            .qty(UPDATED_QTY)
            .billingInterval(UPDATED_BILLING_INTERVAL)
            .initialDos(UPDATED_INITIAL_DOS)
            .period(UPDATED_PERIOD)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .soRecurringPurchaseUuid(UPDATED_SO_RECURRING_PURCHASE_UUID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME);
        return soRecurringPurchase;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(SoRecurringPurchase.class).block();
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
        soRecurringPurchase = createEntity(em);
    }

    @Test
    void createSoRecurringPurchase() throws Exception {
        int databaseSizeBeforeCreate = soRecurringPurchaseRepository.findAll().collectList().block().size();
        // Create the SoRecurringPurchase
        SoRecurringPurchaseDTO soRecurringPurchaseDTO = soRecurringPurchaseMapper.toDto(soRecurringPurchase);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(soRecurringPurchaseDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the SoRecurringPurchase in the database
        List<SoRecurringPurchase> soRecurringPurchaseList = soRecurringPurchaseRepository.findAll().collectList().block();
        assertThat(soRecurringPurchaseList).hasSize(databaseSizeBeforeCreate + 1);
        SoRecurringPurchase testSoRecurringPurchase = soRecurringPurchaseList.get(soRecurringPurchaseList.size() - 1);
        assertThat(testSoRecurringPurchase.getSoId()).isEqualTo(DEFAULT_SO_ID);
        assertThat(testSoRecurringPurchase.getItemId()).isEqualTo(DEFAULT_ITEM_ID);
        assertThat(testSoRecurringPurchase.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testSoRecurringPurchase.getProcCode()).isEqualTo(DEFAULT_PROC_CODE);
        assertThat(testSoRecurringPurchase.getQty()).isEqualTo(DEFAULT_QTY);
        assertThat(testSoRecurringPurchase.getBillingInterval()).isEqualTo(DEFAULT_BILLING_INTERVAL);
        assertThat(testSoRecurringPurchase.getInitialDos()).isEqualTo(DEFAULT_INITIAL_DOS);
        assertThat(testSoRecurringPurchase.getPeriod()).isEqualTo(DEFAULT_PERIOD);
        assertThat(testSoRecurringPurchase.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSoRecurringPurchase.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testSoRecurringPurchase.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testSoRecurringPurchase.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testSoRecurringPurchase.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testSoRecurringPurchase.getSoRecurringPurchaseUuid()).isEqualTo(DEFAULT_SO_RECURRING_PURCHASE_UUID);
        assertThat(testSoRecurringPurchase.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testSoRecurringPurchase.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
    }

    @Test
    void createSoRecurringPurchaseWithExistingId() throws Exception {
        // Create the SoRecurringPurchase with an existing ID
        soRecurringPurchase.setRpId(1L);
        SoRecurringPurchaseDTO soRecurringPurchaseDTO = soRecurringPurchaseMapper.toDto(soRecurringPurchase);

        int databaseSizeBeforeCreate = soRecurringPurchaseRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(soRecurringPurchaseDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SoRecurringPurchase in the database
        List<SoRecurringPurchase> soRecurringPurchaseList = soRecurringPurchaseRepository.findAll().collectList().block();
        assertThat(soRecurringPurchaseList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllSoRecurringPurchases() {
        // Initialize the database
        soRecurringPurchaseRepository.save(soRecurringPurchase).block();

        // Get all the soRecurringPurchaseList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=rpId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].rpId")
            .value(hasItem(soRecurringPurchase.getRpId().intValue()))
            .jsonPath("$.[*].soId")
            .value(hasItem(DEFAULT_SO_ID.intValue()))
            .jsonPath("$.[*].itemId")
            .value(hasItem(DEFAULT_ITEM_ID.intValue()))
            .jsonPath("$.[*].itemName")
            .value(hasItem(DEFAULT_ITEM_NAME))
            .jsonPath("$.[*].procCode")
            .value(hasItem(DEFAULT_PROC_CODE))
            .jsonPath("$.[*].qty")
            .value(hasItem(DEFAULT_QTY.doubleValue()))
            .jsonPath("$.[*].billingInterval")
            .value(hasItem(DEFAULT_BILLING_INTERVAL.doubleValue()))
            .jsonPath("$.[*].initialDos")
            .value(hasItem(DEFAULT_INITIAL_DOS.toString()))
            .jsonPath("$.[*].period")
            .value(hasItem(DEFAULT_PERIOD.doubleValue()))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS))
            .jsonPath("$.[*].createdDate")
            .value(hasItem(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.[*].createdById")
            .value(hasItem(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.[*].updatedDate")
            .value(hasItem(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.[*].updatedById")
            .value(hasItem(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.[*].soRecurringPurchaseUuid")
            .value(hasItem(DEFAULT_SO_RECURRING_PURCHASE_UUID.toString()))
            .jsonPath("$.[*].createdByName")
            .value(hasItem(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.[*].updatedByName")
            .value(hasItem(DEFAULT_UPDATED_BY_NAME));
    }

    @Test
    void getSoRecurringPurchase() {
        // Initialize the database
        soRecurringPurchaseRepository.save(soRecurringPurchase).block();

        // Get the soRecurringPurchase
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, soRecurringPurchase.getRpId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.rpId")
            .value(is(soRecurringPurchase.getRpId().intValue()))
            .jsonPath("$.soId")
            .value(is(DEFAULT_SO_ID.intValue()))
            .jsonPath("$.itemId")
            .value(is(DEFAULT_ITEM_ID.intValue()))
            .jsonPath("$.itemName")
            .value(is(DEFAULT_ITEM_NAME))
            .jsonPath("$.procCode")
            .value(is(DEFAULT_PROC_CODE))
            .jsonPath("$.qty")
            .value(is(DEFAULT_QTY.doubleValue()))
            .jsonPath("$.billingInterval")
            .value(is(DEFAULT_BILLING_INTERVAL.doubleValue()))
            .jsonPath("$.initialDos")
            .value(is(DEFAULT_INITIAL_DOS.toString()))
            .jsonPath("$.period")
            .value(is(DEFAULT_PERIOD.doubleValue()))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS))
            .jsonPath("$.createdDate")
            .value(is(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.createdById")
            .value(is(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.updatedDate")
            .value(is(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.updatedById")
            .value(is(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.soRecurringPurchaseUuid")
            .value(is(DEFAULT_SO_RECURRING_PURCHASE_UUID.toString()))
            .jsonPath("$.createdByName")
            .value(is(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.updatedByName")
            .value(is(DEFAULT_UPDATED_BY_NAME));
    }

    @Test
    void getNonExistingSoRecurringPurchase() {
        // Get the soRecurringPurchase
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingSoRecurringPurchase() throws Exception {
        // Initialize the database
        soRecurringPurchaseRepository.save(soRecurringPurchase).block();

        int databaseSizeBeforeUpdate = soRecurringPurchaseRepository.findAll().collectList().block().size();

        // Update the soRecurringPurchase
        SoRecurringPurchase updatedSoRecurringPurchase = soRecurringPurchaseRepository.findById(soRecurringPurchase.getRpId()).block();
        updatedSoRecurringPurchase
            .soId(UPDATED_SO_ID)
            .itemId(UPDATED_ITEM_ID)
            .itemName(UPDATED_ITEM_NAME)
            .procCode(UPDATED_PROC_CODE)
            .qty(UPDATED_QTY)
            .billingInterval(UPDATED_BILLING_INTERVAL)
            .initialDos(UPDATED_INITIAL_DOS)
            .period(UPDATED_PERIOD)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .soRecurringPurchaseUuid(UPDATED_SO_RECURRING_PURCHASE_UUID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME);
        SoRecurringPurchaseDTO soRecurringPurchaseDTO = soRecurringPurchaseMapper.toDto(updatedSoRecurringPurchase);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, soRecurringPurchaseDTO.getRpId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(soRecurringPurchaseDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SoRecurringPurchase in the database
        List<SoRecurringPurchase> soRecurringPurchaseList = soRecurringPurchaseRepository.findAll().collectList().block();
        assertThat(soRecurringPurchaseList).hasSize(databaseSizeBeforeUpdate);
        SoRecurringPurchase testSoRecurringPurchase = soRecurringPurchaseList.get(soRecurringPurchaseList.size() - 1);
        assertThat(testSoRecurringPurchase.getSoId()).isEqualTo(UPDATED_SO_ID);
        assertThat(testSoRecurringPurchase.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testSoRecurringPurchase.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testSoRecurringPurchase.getProcCode()).isEqualTo(UPDATED_PROC_CODE);
        assertThat(testSoRecurringPurchase.getQty()).isEqualTo(UPDATED_QTY);
        assertThat(testSoRecurringPurchase.getBillingInterval()).isEqualTo(UPDATED_BILLING_INTERVAL);
        assertThat(testSoRecurringPurchase.getInitialDos()).isEqualTo(UPDATED_INITIAL_DOS);
        assertThat(testSoRecurringPurchase.getPeriod()).isEqualTo(UPDATED_PERIOD);
        assertThat(testSoRecurringPurchase.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSoRecurringPurchase.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSoRecurringPurchase.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testSoRecurringPurchase.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testSoRecurringPurchase.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testSoRecurringPurchase.getSoRecurringPurchaseUuid()).isEqualTo(UPDATED_SO_RECURRING_PURCHASE_UUID);
        assertThat(testSoRecurringPurchase.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testSoRecurringPurchase.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
    }

    @Test
    void putNonExistingSoRecurringPurchase() throws Exception {
        int databaseSizeBeforeUpdate = soRecurringPurchaseRepository.findAll().collectList().block().size();
        soRecurringPurchase.setRpId(count.incrementAndGet());

        // Create the SoRecurringPurchase
        SoRecurringPurchaseDTO soRecurringPurchaseDTO = soRecurringPurchaseMapper.toDto(soRecurringPurchase);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, soRecurringPurchaseDTO.getRpId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(soRecurringPurchaseDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SoRecurringPurchase in the database
        List<SoRecurringPurchase> soRecurringPurchaseList = soRecurringPurchaseRepository.findAll().collectList().block();
        assertThat(soRecurringPurchaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchSoRecurringPurchase() throws Exception {
        int databaseSizeBeforeUpdate = soRecurringPurchaseRepository.findAll().collectList().block().size();
        soRecurringPurchase.setRpId(count.incrementAndGet());

        // Create the SoRecurringPurchase
        SoRecurringPurchaseDTO soRecurringPurchaseDTO = soRecurringPurchaseMapper.toDto(soRecurringPurchase);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(soRecurringPurchaseDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SoRecurringPurchase in the database
        List<SoRecurringPurchase> soRecurringPurchaseList = soRecurringPurchaseRepository.findAll().collectList().block();
        assertThat(soRecurringPurchaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamSoRecurringPurchase() throws Exception {
        int databaseSizeBeforeUpdate = soRecurringPurchaseRepository.findAll().collectList().block().size();
        soRecurringPurchase.setRpId(count.incrementAndGet());

        // Create the SoRecurringPurchase
        SoRecurringPurchaseDTO soRecurringPurchaseDTO = soRecurringPurchaseMapper.toDto(soRecurringPurchase);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(soRecurringPurchaseDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the SoRecurringPurchase in the database
        List<SoRecurringPurchase> soRecurringPurchaseList = soRecurringPurchaseRepository.findAll().collectList().block();
        assertThat(soRecurringPurchaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateSoRecurringPurchaseWithPatch() throws Exception {
        // Initialize the database
        soRecurringPurchaseRepository.save(soRecurringPurchase).block();

        int databaseSizeBeforeUpdate = soRecurringPurchaseRepository.findAll().collectList().block().size();

        // Update the soRecurringPurchase using partial update
        SoRecurringPurchase partialUpdatedSoRecurringPurchase = new SoRecurringPurchase();
        partialUpdatedSoRecurringPurchase.setRpId(soRecurringPurchase.getRpId());

        partialUpdatedSoRecurringPurchase
            .soId(UPDATED_SO_ID)
            .itemName(UPDATED_ITEM_NAME)
            .qty(UPDATED_QTY)
            .initialDos(UPDATED_INITIAL_DOS)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedSoRecurringPurchase.getRpId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedSoRecurringPurchase))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SoRecurringPurchase in the database
        List<SoRecurringPurchase> soRecurringPurchaseList = soRecurringPurchaseRepository.findAll().collectList().block();
        assertThat(soRecurringPurchaseList).hasSize(databaseSizeBeforeUpdate);
        SoRecurringPurchase testSoRecurringPurchase = soRecurringPurchaseList.get(soRecurringPurchaseList.size() - 1);
        assertThat(testSoRecurringPurchase.getSoId()).isEqualTo(UPDATED_SO_ID);
        assertThat(testSoRecurringPurchase.getItemId()).isEqualTo(DEFAULT_ITEM_ID);
        assertThat(testSoRecurringPurchase.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testSoRecurringPurchase.getProcCode()).isEqualTo(DEFAULT_PROC_CODE);
        assertThat(testSoRecurringPurchase.getQty()).isEqualTo(UPDATED_QTY);
        assertThat(testSoRecurringPurchase.getBillingInterval()).isEqualTo(DEFAULT_BILLING_INTERVAL);
        assertThat(testSoRecurringPurchase.getInitialDos()).isEqualTo(UPDATED_INITIAL_DOS);
        assertThat(testSoRecurringPurchase.getPeriod()).isEqualTo(DEFAULT_PERIOD);
        assertThat(testSoRecurringPurchase.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSoRecurringPurchase.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testSoRecurringPurchase.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testSoRecurringPurchase.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testSoRecurringPurchase.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testSoRecurringPurchase.getSoRecurringPurchaseUuid()).isEqualTo(DEFAULT_SO_RECURRING_PURCHASE_UUID);
        assertThat(testSoRecurringPurchase.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testSoRecurringPurchase.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
    }

    @Test
    void fullUpdateSoRecurringPurchaseWithPatch() throws Exception {
        // Initialize the database
        soRecurringPurchaseRepository.save(soRecurringPurchase).block();

        int databaseSizeBeforeUpdate = soRecurringPurchaseRepository.findAll().collectList().block().size();

        // Update the soRecurringPurchase using partial update
        SoRecurringPurchase partialUpdatedSoRecurringPurchase = new SoRecurringPurchase();
        partialUpdatedSoRecurringPurchase.setRpId(soRecurringPurchase.getRpId());

        partialUpdatedSoRecurringPurchase
            .soId(UPDATED_SO_ID)
            .itemId(UPDATED_ITEM_ID)
            .itemName(UPDATED_ITEM_NAME)
            .procCode(UPDATED_PROC_CODE)
            .qty(UPDATED_QTY)
            .billingInterval(UPDATED_BILLING_INTERVAL)
            .initialDos(UPDATED_INITIAL_DOS)
            .period(UPDATED_PERIOD)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .soRecurringPurchaseUuid(UPDATED_SO_RECURRING_PURCHASE_UUID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedSoRecurringPurchase.getRpId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedSoRecurringPurchase))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SoRecurringPurchase in the database
        List<SoRecurringPurchase> soRecurringPurchaseList = soRecurringPurchaseRepository.findAll().collectList().block();
        assertThat(soRecurringPurchaseList).hasSize(databaseSizeBeforeUpdate);
        SoRecurringPurchase testSoRecurringPurchase = soRecurringPurchaseList.get(soRecurringPurchaseList.size() - 1);
        assertThat(testSoRecurringPurchase.getSoId()).isEqualTo(UPDATED_SO_ID);
        assertThat(testSoRecurringPurchase.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testSoRecurringPurchase.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testSoRecurringPurchase.getProcCode()).isEqualTo(UPDATED_PROC_CODE);
        assertThat(testSoRecurringPurchase.getQty()).isEqualTo(UPDATED_QTY);
        assertThat(testSoRecurringPurchase.getBillingInterval()).isEqualTo(UPDATED_BILLING_INTERVAL);
        assertThat(testSoRecurringPurchase.getInitialDos()).isEqualTo(UPDATED_INITIAL_DOS);
        assertThat(testSoRecurringPurchase.getPeriod()).isEqualTo(UPDATED_PERIOD);
        assertThat(testSoRecurringPurchase.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSoRecurringPurchase.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSoRecurringPurchase.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testSoRecurringPurchase.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testSoRecurringPurchase.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testSoRecurringPurchase.getSoRecurringPurchaseUuid()).isEqualTo(UPDATED_SO_RECURRING_PURCHASE_UUID);
        assertThat(testSoRecurringPurchase.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testSoRecurringPurchase.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
    }

    @Test
    void patchNonExistingSoRecurringPurchase() throws Exception {
        int databaseSizeBeforeUpdate = soRecurringPurchaseRepository.findAll().collectList().block().size();
        soRecurringPurchase.setRpId(count.incrementAndGet());

        // Create the SoRecurringPurchase
        SoRecurringPurchaseDTO soRecurringPurchaseDTO = soRecurringPurchaseMapper.toDto(soRecurringPurchase);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, soRecurringPurchaseDTO.getRpId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(soRecurringPurchaseDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SoRecurringPurchase in the database
        List<SoRecurringPurchase> soRecurringPurchaseList = soRecurringPurchaseRepository.findAll().collectList().block();
        assertThat(soRecurringPurchaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchSoRecurringPurchase() throws Exception {
        int databaseSizeBeforeUpdate = soRecurringPurchaseRepository.findAll().collectList().block().size();
        soRecurringPurchase.setRpId(count.incrementAndGet());

        // Create the SoRecurringPurchase
        SoRecurringPurchaseDTO soRecurringPurchaseDTO = soRecurringPurchaseMapper.toDto(soRecurringPurchase);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(soRecurringPurchaseDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SoRecurringPurchase in the database
        List<SoRecurringPurchase> soRecurringPurchaseList = soRecurringPurchaseRepository.findAll().collectList().block();
        assertThat(soRecurringPurchaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamSoRecurringPurchase() throws Exception {
        int databaseSizeBeforeUpdate = soRecurringPurchaseRepository.findAll().collectList().block().size();
        soRecurringPurchase.setRpId(count.incrementAndGet());

        // Create the SoRecurringPurchase
        SoRecurringPurchaseDTO soRecurringPurchaseDTO = soRecurringPurchaseMapper.toDto(soRecurringPurchase);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(soRecurringPurchaseDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the SoRecurringPurchase in the database
        List<SoRecurringPurchase> soRecurringPurchaseList = soRecurringPurchaseRepository.findAll().collectList().block();
        assertThat(soRecurringPurchaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteSoRecurringPurchase() {
        // Initialize the database
        soRecurringPurchaseRepository.save(soRecurringPurchase).block();

        int databaseSizeBeforeDelete = soRecurringPurchaseRepository.findAll().collectList().block().size();

        // Delete the soRecurringPurchase
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, soRecurringPurchase.getRpId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<SoRecurringPurchase> soRecurringPurchaseList = soRecurringPurchaseRepository.findAll().collectList().block();
        assertThat(soRecurringPurchaseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
