package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.SoLcdDocRefTransaction;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.SoLcdDocRefTransactionRepository;
import com.sunknowledge.dme.rcm.service.dto.SoLcdDocRefTransactionDTO;
import com.sunknowledge.dme.rcm.service.mapper.SoLcdDocRefTransactionMapper;
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
 * Integration tests for the {@link SoLcdDocRefTransactionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class SoLcdDocRefTransactionResourceIT {

    private static final Long DEFAULT_SO_ID = 1L;
    private static final Long UPDATED_SO_ID = 2L;

    private static final Long DEFAULT_CHECKLIST_ID = 1L;
    private static final Long UPDATED_CHECKLIST_ID = 2L;

    private static final String DEFAULT_CHECKLIST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CHECKLIST_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_DOC_REF_ID = 1L;
    private static final Long UPDATED_DOC_REF_ID = 2L;

    private static final String DEFAULT_DOC_REF_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DOC_REF_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final UUID DEFAULT_SO_LCD_DOC_REF_TRANSACTION_UUID = UUID.randomUUID();
    private static final UUID UPDATED_SO_LCD_DOC_REF_TRANSACTION_UUID = UUID.randomUUID();

    private static final Long DEFAULT_ITEM_GROUP_ID = 1L;
    private static final Long UPDATED_ITEM_GROUP_ID = 2L;

    private static final String DEFAULT_ITEM_GROUP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_GROUP_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_COVERAGE_CRITERIA_ID = 1L;
    private static final Long UPDATED_COVERAGE_CRITERIA_ID = 2L;

    private static final String ENTITY_API_URL = "/api/so-lcd-doc-ref-transactions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{soLcdDocRefId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SoLcdDocRefTransactionRepository soLcdDocRefTransactionRepository;

    @Autowired
    private SoLcdDocRefTransactionMapper soLcdDocRefTransactionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private SoLcdDocRefTransaction soLcdDocRefTransaction;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SoLcdDocRefTransaction createEntity(EntityManager em) {
        SoLcdDocRefTransaction soLcdDocRefTransaction = new SoLcdDocRefTransaction()
            .soId(DEFAULT_SO_ID)
            .checklistId(DEFAULT_CHECKLIST_ID)
            .checklistName(DEFAULT_CHECKLIST_NAME)
            .docRefId(DEFAULT_DOC_REF_ID)
            .docRefName(DEFAULT_DOC_REF_NAME)
            .value(DEFAULT_VALUE)
            .status(DEFAULT_STATUS)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .soLcdDocRefTransactionUuid(DEFAULT_SO_LCD_DOC_REF_TRANSACTION_UUID)
            .itemGroupId(DEFAULT_ITEM_GROUP_ID)
            .itemGroupName(DEFAULT_ITEM_GROUP_NAME)
            .coverageCriteriaId(DEFAULT_COVERAGE_CRITERIA_ID);
        return soLcdDocRefTransaction;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SoLcdDocRefTransaction createUpdatedEntity(EntityManager em) {
        SoLcdDocRefTransaction soLcdDocRefTransaction = new SoLcdDocRefTransaction()
            .soId(UPDATED_SO_ID)
            .checklistId(UPDATED_CHECKLIST_ID)
            .checklistName(UPDATED_CHECKLIST_NAME)
            .docRefId(UPDATED_DOC_REF_ID)
            .docRefName(UPDATED_DOC_REF_NAME)
            .value(UPDATED_VALUE)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .soLcdDocRefTransactionUuid(UPDATED_SO_LCD_DOC_REF_TRANSACTION_UUID)
            .itemGroupId(UPDATED_ITEM_GROUP_ID)
            .itemGroupName(UPDATED_ITEM_GROUP_NAME)
            .coverageCriteriaId(UPDATED_COVERAGE_CRITERIA_ID);
        return soLcdDocRefTransaction;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(SoLcdDocRefTransaction.class).block();
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
        soLcdDocRefTransaction = createEntity(em);
    }

    @Test
    void createSoLcdDocRefTransaction() throws Exception {
        int databaseSizeBeforeCreate = soLcdDocRefTransactionRepository.findAll().collectList().block().size();
        // Create the SoLcdDocRefTransaction
        SoLcdDocRefTransactionDTO soLcdDocRefTransactionDTO = soLcdDocRefTransactionMapper.toDto(soLcdDocRefTransaction);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(soLcdDocRefTransactionDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the SoLcdDocRefTransaction in the database
        List<SoLcdDocRefTransaction> soLcdDocRefTransactionList = soLcdDocRefTransactionRepository.findAll().collectList().block();
        assertThat(soLcdDocRefTransactionList).hasSize(databaseSizeBeforeCreate + 1);
        SoLcdDocRefTransaction testSoLcdDocRefTransaction = soLcdDocRefTransactionList.get(soLcdDocRefTransactionList.size() - 1);
        assertThat(testSoLcdDocRefTransaction.getSoId()).isEqualTo(DEFAULT_SO_ID);
        assertThat(testSoLcdDocRefTransaction.getChecklistId()).isEqualTo(DEFAULT_CHECKLIST_ID);
        assertThat(testSoLcdDocRefTransaction.getChecklistName()).isEqualTo(DEFAULT_CHECKLIST_NAME);
        assertThat(testSoLcdDocRefTransaction.getDocRefId()).isEqualTo(DEFAULT_DOC_REF_ID);
        assertThat(testSoLcdDocRefTransaction.getDocRefName()).isEqualTo(DEFAULT_DOC_REF_NAME);
        assertThat(testSoLcdDocRefTransaction.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testSoLcdDocRefTransaction.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSoLcdDocRefTransaction.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testSoLcdDocRefTransaction.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testSoLcdDocRefTransaction.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testSoLcdDocRefTransaction.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testSoLcdDocRefTransaction.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testSoLcdDocRefTransaction.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testSoLcdDocRefTransaction.getSoLcdDocRefTransactionUuid()).isEqualTo(DEFAULT_SO_LCD_DOC_REF_TRANSACTION_UUID);
        assertThat(testSoLcdDocRefTransaction.getItemGroupId()).isEqualTo(DEFAULT_ITEM_GROUP_ID);
        assertThat(testSoLcdDocRefTransaction.getItemGroupName()).isEqualTo(DEFAULT_ITEM_GROUP_NAME);
        assertThat(testSoLcdDocRefTransaction.getCoverageCriteriaId()).isEqualTo(DEFAULT_COVERAGE_CRITERIA_ID);
    }

    @Test
    void createSoLcdDocRefTransactionWithExistingId() throws Exception {
        // Create the SoLcdDocRefTransaction with an existing ID
        soLcdDocRefTransaction.setSoLcdDocRefId(1L);
        SoLcdDocRefTransactionDTO soLcdDocRefTransactionDTO = soLcdDocRefTransactionMapper.toDto(soLcdDocRefTransaction);

        int databaseSizeBeforeCreate = soLcdDocRefTransactionRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(soLcdDocRefTransactionDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SoLcdDocRefTransaction in the database
        List<SoLcdDocRefTransaction> soLcdDocRefTransactionList = soLcdDocRefTransactionRepository.findAll().collectList().block();
        assertThat(soLcdDocRefTransactionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllSoLcdDocRefTransactions() {
        // Initialize the database
        soLcdDocRefTransactionRepository.save(soLcdDocRefTransaction).block();

        // Get all the soLcdDocRefTransactionList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=soLcdDocRefId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].soLcdDocRefId")
            .value(hasItem(soLcdDocRefTransaction.getSoLcdDocRefId().intValue()))
            .jsonPath("$.[*].soId")
            .value(hasItem(DEFAULT_SO_ID.intValue()))
            .jsonPath("$.[*].checklistId")
            .value(hasItem(DEFAULT_CHECKLIST_ID.intValue()))
            .jsonPath("$.[*].checklistName")
            .value(hasItem(DEFAULT_CHECKLIST_NAME))
            .jsonPath("$.[*].docRefId")
            .value(hasItem(DEFAULT_DOC_REF_ID.intValue()))
            .jsonPath("$.[*].docRefName")
            .value(hasItem(DEFAULT_DOC_REF_NAME))
            .jsonPath("$.[*].value")
            .value(hasItem(DEFAULT_VALUE))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS))
            .jsonPath("$.[*].createdDate")
            .value(hasItem(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.[*].createdById")
            .value(hasItem(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.[*].createdByName")
            .value(hasItem(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.[*].updatedDate")
            .value(hasItem(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.[*].updatedById")
            .value(hasItem(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.[*].updatedByName")
            .value(hasItem(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.[*].soLcdDocRefTransactionUuid")
            .value(hasItem(DEFAULT_SO_LCD_DOC_REF_TRANSACTION_UUID.toString()))
            .jsonPath("$.[*].itemGroupId")
            .value(hasItem(DEFAULT_ITEM_GROUP_ID.intValue()))
            .jsonPath("$.[*].itemGroupName")
            .value(hasItem(DEFAULT_ITEM_GROUP_NAME))
            .jsonPath("$.[*].coverageCriteriaId")
            .value(hasItem(DEFAULT_COVERAGE_CRITERIA_ID.intValue()));
    }

    @Test
    void getSoLcdDocRefTransaction() {
        // Initialize the database
        soLcdDocRefTransactionRepository.save(soLcdDocRefTransaction).block();

        // Get the soLcdDocRefTransaction
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, soLcdDocRefTransaction.getSoLcdDocRefId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.soLcdDocRefId")
            .value(is(soLcdDocRefTransaction.getSoLcdDocRefId().intValue()))
            .jsonPath("$.soId")
            .value(is(DEFAULT_SO_ID.intValue()))
            .jsonPath("$.checklistId")
            .value(is(DEFAULT_CHECKLIST_ID.intValue()))
            .jsonPath("$.checklistName")
            .value(is(DEFAULT_CHECKLIST_NAME))
            .jsonPath("$.docRefId")
            .value(is(DEFAULT_DOC_REF_ID.intValue()))
            .jsonPath("$.docRefName")
            .value(is(DEFAULT_DOC_REF_NAME))
            .jsonPath("$.value")
            .value(is(DEFAULT_VALUE))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS))
            .jsonPath("$.createdDate")
            .value(is(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.createdById")
            .value(is(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.createdByName")
            .value(is(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.updatedDate")
            .value(is(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.updatedById")
            .value(is(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.updatedByName")
            .value(is(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.soLcdDocRefTransactionUuid")
            .value(is(DEFAULT_SO_LCD_DOC_REF_TRANSACTION_UUID.toString()))
            .jsonPath("$.itemGroupId")
            .value(is(DEFAULT_ITEM_GROUP_ID.intValue()))
            .jsonPath("$.itemGroupName")
            .value(is(DEFAULT_ITEM_GROUP_NAME))
            .jsonPath("$.coverageCriteriaId")
            .value(is(DEFAULT_COVERAGE_CRITERIA_ID.intValue()));
    }

    @Test
    void getNonExistingSoLcdDocRefTransaction() {
        // Get the soLcdDocRefTransaction
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingSoLcdDocRefTransaction() throws Exception {
        // Initialize the database
        soLcdDocRefTransactionRepository.save(soLcdDocRefTransaction).block();

        int databaseSizeBeforeUpdate = soLcdDocRefTransactionRepository.findAll().collectList().block().size();

        // Update the soLcdDocRefTransaction
        SoLcdDocRefTransaction updatedSoLcdDocRefTransaction = soLcdDocRefTransactionRepository
            .findById(soLcdDocRefTransaction.getSoLcdDocRefId())
            .block();
        updatedSoLcdDocRefTransaction
            .soId(UPDATED_SO_ID)
            .checklistId(UPDATED_CHECKLIST_ID)
            .checklistName(UPDATED_CHECKLIST_NAME)
            .docRefId(UPDATED_DOC_REF_ID)
            .docRefName(UPDATED_DOC_REF_NAME)
            .value(UPDATED_VALUE)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .soLcdDocRefTransactionUuid(UPDATED_SO_LCD_DOC_REF_TRANSACTION_UUID)
            .itemGroupId(UPDATED_ITEM_GROUP_ID)
            .itemGroupName(UPDATED_ITEM_GROUP_NAME)
            .coverageCriteriaId(UPDATED_COVERAGE_CRITERIA_ID);
        SoLcdDocRefTransactionDTO soLcdDocRefTransactionDTO = soLcdDocRefTransactionMapper.toDto(updatedSoLcdDocRefTransaction);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, soLcdDocRefTransactionDTO.getSoLcdDocRefId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(soLcdDocRefTransactionDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SoLcdDocRefTransaction in the database
        List<SoLcdDocRefTransaction> soLcdDocRefTransactionList = soLcdDocRefTransactionRepository.findAll().collectList().block();
        assertThat(soLcdDocRefTransactionList).hasSize(databaseSizeBeforeUpdate);
        SoLcdDocRefTransaction testSoLcdDocRefTransaction = soLcdDocRefTransactionList.get(soLcdDocRefTransactionList.size() - 1);
        assertThat(testSoLcdDocRefTransaction.getSoId()).isEqualTo(UPDATED_SO_ID);
        assertThat(testSoLcdDocRefTransaction.getChecklistId()).isEqualTo(UPDATED_CHECKLIST_ID);
        assertThat(testSoLcdDocRefTransaction.getChecklistName()).isEqualTo(UPDATED_CHECKLIST_NAME);
        assertThat(testSoLcdDocRefTransaction.getDocRefId()).isEqualTo(UPDATED_DOC_REF_ID);
        assertThat(testSoLcdDocRefTransaction.getDocRefName()).isEqualTo(UPDATED_DOC_REF_NAME);
        assertThat(testSoLcdDocRefTransaction.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testSoLcdDocRefTransaction.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSoLcdDocRefTransaction.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSoLcdDocRefTransaction.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testSoLcdDocRefTransaction.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testSoLcdDocRefTransaction.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testSoLcdDocRefTransaction.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testSoLcdDocRefTransaction.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testSoLcdDocRefTransaction.getSoLcdDocRefTransactionUuid()).isEqualTo(UPDATED_SO_LCD_DOC_REF_TRANSACTION_UUID);
        assertThat(testSoLcdDocRefTransaction.getItemGroupId()).isEqualTo(UPDATED_ITEM_GROUP_ID);
        assertThat(testSoLcdDocRefTransaction.getItemGroupName()).isEqualTo(UPDATED_ITEM_GROUP_NAME);
        assertThat(testSoLcdDocRefTransaction.getCoverageCriteriaId()).isEqualTo(UPDATED_COVERAGE_CRITERIA_ID);
    }

    @Test
    void putNonExistingSoLcdDocRefTransaction() throws Exception {
        int databaseSizeBeforeUpdate = soLcdDocRefTransactionRepository.findAll().collectList().block().size();
        soLcdDocRefTransaction.setSoLcdDocRefId(count.incrementAndGet());

        // Create the SoLcdDocRefTransaction
        SoLcdDocRefTransactionDTO soLcdDocRefTransactionDTO = soLcdDocRefTransactionMapper.toDto(soLcdDocRefTransaction);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, soLcdDocRefTransactionDTO.getSoLcdDocRefId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(soLcdDocRefTransactionDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SoLcdDocRefTransaction in the database
        List<SoLcdDocRefTransaction> soLcdDocRefTransactionList = soLcdDocRefTransactionRepository.findAll().collectList().block();
        assertThat(soLcdDocRefTransactionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchSoLcdDocRefTransaction() throws Exception {
        int databaseSizeBeforeUpdate = soLcdDocRefTransactionRepository.findAll().collectList().block().size();
        soLcdDocRefTransaction.setSoLcdDocRefId(count.incrementAndGet());

        // Create the SoLcdDocRefTransaction
        SoLcdDocRefTransactionDTO soLcdDocRefTransactionDTO = soLcdDocRefTransactionMapper.toDto(soLcdDocRefTransaction);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(soLcdDocRefTransactionDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SoLcdDocRefTransaction in the database
        List<SoLcdDocRefTransaction> soLcdDocRefTransactionList = soLcdDocRefTransactionRepository.findAll().collectList().block();
        assertThat(soLcdDocRefTransactionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamSoLcdDocRefTransaction() throws Exception {
        int databaseSizeBeforeUpdate = soLcdDocRefTransactionRepository.findAll().collectList().block().size();
        soLcdDocRefTransaction.setSoLcdDocRefId(count.incrementAndGet());

        // Create the SoLcdDocRefTransaction
        SoLcdDocRefTransactionDTO soLcdDocRefTransactionDTO = soLcdDocRefTransactionMapper.toDto(soLcdDocRefTransaction);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(soLcdDocRefTransactionDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the SoLcdDocRefTransaction in the database
        List<SoLcdDocRefTransaction> soLcdDocRefTransactionList = soLcdDocRefTransactionRepository.findAll().collectList().block();
        assertThat(soLcdDocRefTransactionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateSoLcdDocRefTransactionWithPatch() throws Exception {
        // Initialize the database
        soLcdDocRefTransactionRepository.save(soLcdDocRefTransaction).block();

        int databaseSizeBeforeUpdate = soLcdDocRefTransactionRepository.findAll().collectList().block().size();

        // Update the soLcdDocRefTransaction using partial update
        SoLcdDocRefTransaction partialUpdatedSoLcdDocRefTransaction = new SoLcdDocRefTransaction();
        partialUpdatedSoLcdDocRefTransaction.setSoLcdDocRefId(soLcdDocRefTransaction.getSoLcdDocRefId());

        partialUpdatedSoLcdDocRefTransaction
            .checklistName(UPDATED_CHECKLIST_NAME)
            .docRefId(UPDATED_DOC_REF_ID)
            .value(UPDATED_VALUE)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .soLcdDocRefTransactionUuid(UPDATED_SO_LCD_DOC_REF_TRANSACTION_UUID)
            .itemGroupName(UPDATED_ITEM_GROUP_NAME)
            .coverageCriteriaId(UPDATED_COVERAGE_CRITERIA_ID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedSoLcdDocRefTransaction.getSoLcdDocRefId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedSoLcdDocRefTransaction))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SoLcdDocRefTransaction in the database
        List<SoLcdDocRefTransaction> soLcdDocRefTransactionList = soLcdDocRefTransactionRepository.findAll().collectList().block();
        assertThat(soLcdDocRefTransactionList).hasSize(databaseSizeBeforeUpdate);
        SoLcdDocRefTransaction testSoLcdDocRefTransaction = soLcdDocRefTransactionList.get(soLcdDocRefTransactionList.size() - 1);
        assertThat(testSoLcdDocRefTransaction.getSoId()).isEqualTo(DEFAULT_SO_ID);
        assertThat(testSoLcdDocRefTransaction.getChecklistId()).isEqualTo(DEFAULT_CHECKLIST_ID);
        assertThat(testSoLcdDocRefTransaction.getChecklistName()).isEqualTo(UPDATED_CHECKLIST_NAME);
        assertThat(testSoLcdDocRefTransaction.getDocRefId()).isEqualTo(UPDATED_DOC_REF_ID);
        assertThat(testSoLcdDocRefTransaction.getDocRefName()).isEqualTo(DEFAULT_DOC_REF_NAME);
        assertThat(testSoLcdDocRefTransaction.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testSoLcdDocRefTransaction.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSoLcdDocRefTransaction.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSoLcdDocRefTransaction.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testSoLcdDocRefTransaction.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testSoLcdDocRefTransaction.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testSoLcdDocRefTransaction.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testSoLcdDocRefTransaction.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testSoLcdDocRefTransaction.getSoLcdDocRefTransactionUuid()).isEqualTo(UPDATED_SO_LCD_DOC_REF_TRANSACTION_UUID);
        assertThat(testSoLcdDocRefTransaction.getItemGroupId()).isEqualTo(DEFAULT_ITEM_GROUP_ID);
        assertThat(testSoLcdDocRefTransaction.getItemGroupName()).isEqualTo(UPDATED_ITEM_GROUP_NAME);
        assertThat(testSoLcdDocRefTransaction.getCoverageCriteriaId()).isEqualTo(UPDATED_COVERAGE_CRITERIA_ID);
    }

    @Test
    void fullUpdateSoLcdDocRefTransactionWithPatch() throws Exception {
        // Initialize the database
        soLcdDocRefTransactionRepository.save(soLcdDocRefTransaction).block();

        int databaseSizeBeforeUpdate = soLcdDocRefTransactionRepository.findAll().collectList().block().size();

        // Update the soLcdDocRefTransaction using partial update
        SoLcdDocRefTransaction partialUpdatedSoLcdDocRefTransaction = new SoLcdDocRefTransaction();
        partialUpdatedSoLcdDocRefTransaction.setSoLcdDocRefId(soLcdDocRefTransaction.getSoLcdDocRefId());

        partialUpdatedSoLcdDocRefTransaction
            .soId(UPDATED_SO_ID)
            .checklistId(UPDATED_CHECKLIST_ID)
            .checklistName(UPDATED_CHECKLIST_NAME)
            .docRefId(UPDATED_DOC_REF_ID)
            .docRefName(UPDATED_DOC_REF_NAME)
            .value(UPDATED_VALUE)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .soLcdDocRefTransactionUuid(UPDATED_SO_LCD_DOC_REF_TRANSACTION_UUID)
            .itemGroupId(UPDATED_ITEM_GROUP_ID)
            .itemGroupName(UPDATED_ITEM_GROUP_NAME)
            .coverageCriteriaId(UPDATED_COVERAGE_CRITERIA_ID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedSoLcdDocRefTransaction.getSoLcdDocRefId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedSoLcdDocRefTransaction))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SoLcdDocRefTransaction in the database
        List<SoLcdDocRefTransaction> soLcdDocRefTransactionList = soLcdDocRefTransactionRepository.findAll().collectList().block();
        assertThat(soLcdDocRefTransactionList).hasSize(databaseSizeBeforeUpdate);
        SoLcdDocRefTransaction testSoLcdDocRefTransaction = soLcdDocRefTransactionList.get(soLcdDocRefTransactionList.size() - 1);
        assertThat(testSoLcdDocRefTransaction.getSoId()).isEqualTo(UPDATED_SO_ID);
        assertThat(testSoLcdDocRefTransaction.getChecklistId()).isEqualTo(UPDATED_CHECKLIST_ID);
        assertThat(testSoLcdDocRefTransaction.getChecklistName()).isEqualTo(UPDATED_CHECKLIST_NAME);
        assertThat(testSoLcdDocRefTransaction.getDocRefId()).isEqualTo(UPDATED_DOC_REF_ID);
        assertThat(testSoLcdDocRefTransaction.getDocRefName()).isEqualTo(UPDATED_DOC_REF_NAME);
        assertThat(testSoLcdDocRefTransaction.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testSoLcdDocRefTransaction.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSoLcdDocRefTransaction.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSoLcdDocRefTransaction.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testSoLcdDocRefTransaction.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testSoLcdDocRefTransaction.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testSoLcdDocRefTransaction.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testSoLcdDocRefTransaction.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testSoLcdDocRefTransaction.getSoLcdDocRefTransactionUuid()).isEqualTo(UPDATED_SO_LCD_DOC_REF_TRANSACTION_UUID);
        assertThat(testSoLcdDocRefTransaction.getItemGroupId()).isEqualTo(UPDATED_ITEM_GROUP_ID);
        assertThat(testSoLcdDocRefTransaction.getItemGroupName()).isEqualTo(UPDATED_ITEM_GROUP_NAME);
        assertThat(testSoLcdDocRefTransaction.getCoverageCriteriaId()).isEqualTo(UPDATED_COVERAGE_CRITERIA_ID);
    }

    @Test
    void patchNonExistingSoLcdDocRefTransaction() throws Exception {
        int databaseSizeBeforeUpdate = soLcdDocRefTransactionRepository.findAll().collectList().block().size();
        soLcdDocRefTransaction.setSoLcdDocRefId(count.incrementAndGet());

        // Create the SoLcdDocRefTransaction
        SoLcdDocRefTransactionDTO soLcdDocRefTransactionDTO = soLcdDocRefTransactionMapper.toDto(soLcdDocRefTransaction);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, soLcdDocRefTransactionDTO.getSoLcdDocRefId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(soLcdDocRefTransactionDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SoLcdDocRefTransaction in the database
        List<SoLcdDocRefTransaction> soLcdDocRefTransactionList = soLcdDocRefTransactionRepository.findAll().collectList().block();
        assertThat(soLcdDocRefTransactionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchSoLcdDocRefTransaction() throws Exception {
        int databaseSizeBeforeUpdate = soLcdDocRefTransactionRepository.findAll().collectList().block().size();
        soLcdDocRefTransaction.setSoLcdDocRefId(count.incrementAndGet());

        // Create the SoLcdDocRefTransaction
        SoLcdDocRefTransactionDTO soLcdDocRefTransactionDTO = soLcdDocRefTransactionMapper.toDto(soLcdDocRefTransaction);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(soLcdDocRefTransactionDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SoLcdDocRefTransaction in the database
        List<SoLcdDocRefTransaction> soLcdDocRefTransactionList = soLcdDocRefTransactionRepository.findAll().collectList().block();
        assertThat(soLcdDocRefTransactionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamSoLcdDocRefTransaction() throws Exception {
        int databaseSizeBeforeUpdate = soLcdDocRefTransactionRepository.findAll().collectList().block().size();
        soLcdDocRefTransaction.setSoLcdDocRefId(count.incrementAndGet());

        // Create the SoLcdDocRefTransaction
        SoLcdDocRefTransactionDTO soLcdDocRefTransactionDTO = soLcdDocRefTransactionMapper.toDto(soLcdDocRefTransaction);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(soLcdDocRefTransactionDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the SoLcdDocRefTransaction in the database
        List<SoLcdDocRefTransaction> soLcdDocRefTransactionList = soLcdDocRefTransactionRepository.findAll().collectList().block();
        assertThat(soLcdDocRefTransactionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteSoLcdDocRefTransaction() {
        // Initialize the database
        soLcdDocRefTransactionRepository.save(soLcdDocRefTransaction).block();

        int databaseSizeBeforeDelete = soLcdDocRefTransactionRepository.findAll().collectList().block().size();

        // Delete the soLcdDocRefTransaction
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, soLcdDocRefTransaction.getSoLcdDocRefId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<SoLcdDocRefTransaction> soLcdDocRefTransactionList = soLcdDocRefTransactionRepository.findAll().collectList().block();
        assertThat(soLcdDocRefTransactionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
