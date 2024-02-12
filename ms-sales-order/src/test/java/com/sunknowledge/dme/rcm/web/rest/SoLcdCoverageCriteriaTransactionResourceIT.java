package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.SoLcdCoverageCriteriaTransaction;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.SoLcdCoverageCriteriaTransactionRepository;
import com.sunknowledge.dme.rcm.service.dto.SoLcdCoverageCriteriaTransactionDTO;
import com.sunknowledge.dme.rcm.service.mapper.SoLcdCoverageCriteriaTransactionMapper;
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
 * Integration tests for the {@link SoLcdCoverageCriteriaTransactionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class SoLcdCoverageCriteriaTransactionResourceIT {

    private static final Long DEFAULT_SO_ID = 1L;
    private static final Long UPDATED_SO_ID = 2L;

    private static final Long DEFAULT_CHECKLIST_ID = 1L;
    private static final Long UPDATED_CHECKLIST_ID = 2L;

    private static final String DEFAULT_CHECKLIST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CHECKLIST_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_COVERAGE_CRITERIA_ID = 1L;
    private static final Long UPDATED_COVERAGE_CRITERIA_ID = 2L;

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

    private static final UUID DEFAULT_SO_LCD_COVERAGE_CRITERIA_TRANSACTION_UUID = UUID.randomUUID();
    private static final UUID UPDATED_SO_LCD_COVERAGE_CRITERIA_TRANSACTION_UUID = UUID.randomUUID();

    private static final Long DEFAULT_ITEM_GROUP_ID = 1L;
    private static final Long UPDATED_ITEM_GROUP_ID = 2L;

    private static final String DEFAULT_ITEM_GROUP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_GROUP_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COVERAGE_CRITERIA_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COVERAGE_CRITERIA_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/so-lcd-coverage-criteria-transactions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{soLcdCoverageCriteriaTransactionId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SoLcdCoverageCriteriaTransactionRepository soLcdCoverageCriteriaTransactionRepository;

    @Autowired
    private SoLcdCoverageCriteriaTransactionMapper soLcdCoverageCriteriaTransactionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private SoLcdCoverageCriteriaTransaction soLcdCoverageCriteriaTransaction;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SoLcdCoverageCriteriaTransaction createEntity(EntityManager em) {
        SoLcdCoverageCriteriaTransaction soLcdCoverageCriteriaTransaction = new SoLcdCoverageCriteriaTransaction()
            .soId(DEFAULT_SO_ID)
            .checklistId(DEFAULT_CHECKLIST_ID)
            .checklistName(DEFAULT_CHECKLIST_NAME)
            .coverageCriteriaId(DEFAULT_COVERAGE_CRITERIA_ID)
            .value(DEFAULT_VALUE)
            .status(DEFAULT_STATUS)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .soLcdCoverageCriteriaTransactionUuid(DEFAULT_SO_LCD_COVERAGE_CRITERIA_TRANSACTION_UUID)
            .itemGroupId(DEFAULT_ITEM_GROUP_ID)
            .itemGroupName(DEFAULT_ITEM_GROUP_NAME)
            .coverageCriteriaName(DEFAULT_COVERAGE_CRITERIA_NAME);
        return soLcdCoverageCriteriaTransaction;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SoLcdCoverageCriteriaTransaction createUpdatedEntity(EntityManager em) {
        SoLcdCoverageCriteriaTransaction soLcdCoverageCriteriaTransaction = new SoLcdCoverageCriteriaTransaction()
            .soId(UPDATED_SO_ID)
            .checklistId(UPDATED_CHECKLIST_ID)
            .checklistName(UPDATED_CHECKLIST_NAME)
            .coverageCriteriaId(UPDATED_COVERAGE_CRITERIA_ID)
            .value(UPDATED_VALUE)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .soLcdCoverageCriteriaTransactionUuid(UPDATED_SO_LCD_COVERAGE_CRITERIA_TRANSACTION_UUID)
            .itemGroupId(UPDATED_ITEM_GROUP_ID)
            .itemGroupName(UPDATED_ITEM_GROUP_NAME)
            .coverageCriteriaName(UPDATED_COVERAGE_CRITERIA_NAME);
        return soLcdCoverageCriteriaTransaction;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(SoLcdCoverageCriteriaTransaction.class).block();
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
        soLcdCoverageCriteriaTransaction = createEntity(em);
    }

    @Test
    void createSoLcdCoverageCriteriaTransaction() throws Exception {
        int databaseSizeBeforeCreate = soLcdCoverageCriteriaTransactionRepository.findAll().collectList().block().size();
        // Create the SoLcdCoverageCriteriaTransaction
        SoLcdCoverageCriteriaTransactionDTO soLcdCoverageCriteriaTransactionDTO = soLcdCoverageCriteriaTransactionMapper.toDto(
            soLcdCoverageCriteriaTransaction
        );
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(soLcdCoverageCriteriaTransactionDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the SoLcdCoverageCriteriaTransaction in the database
        List<SoLcdCoverageCriteriaTransaction> soLcdCoverageCriteriaTransactionList = soLcdCoverageCriteriaTransactionRepository
            .findAll()
            .collectList()
            .block();
        assertThat(soLcdCoverageCriteriaTransactionList).hasSize(databaseSizeBeforeCreate + 1);
        SoLcdCoverageCriteriaTransaction testSoLcdCoverageCriteriaTransaction = soLcdCoverageCriteriaTransactionList.get(
            soLcdCoverageCriteriaTransactionList.size() - 1
        );
        assertThat(testSoLcdCoverageCriteriaTransaction.getSoId()).isEqualTo(DEFAULT_SO_ID);
        assertThat(testSoLcdCoverageCriteriaTransaction.getChecklistId()).isEqualTo(DEFAULT_CHECKLIST_ID);
        assertThat(testSoLcdCoverageCriteriaTransaction.getChecklistName()).isEqualTo(DEFAULT_CHECKLIST_NAME);
        assertThat(testSoLcdCoverageCriteriaTransaction.getCoverageCriteriaId()).isEqualTo(DEFAULT_COVERAGE_CRITERIA_ID);
        assertThat(testSoLcdCoverageCriteriaTransaction.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testSoLcdCoverageCriteriaTransaction.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSoLcdCoverageCriteriaTransaction.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testSoLcdCoverageCriteriaTransaction.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testSoLcdCoverageCriteriaTransaction.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testSoLcdCoverageCriteriaTransaction.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testSoLcdCoverageCriteriaTransaction.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testSoLcdCoverageCriteriaTransaction.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testSoLcdCoverageCriteriaTransaction.getSoLcdCoverageCriteriaTransactionUuid())
            .isEqualTo(DEFAULT_SO_LCD_COVERAGE_CRITERIA_TRANSACTION_UUID);
        assertThat(testSoLcdCoverageCriteriaTransaction.getItemGroupId()).isEqualTo(DEFAULT_ITEM_GROUP_ID);
        assertThat(testSoLcdCoverageCriteriaTransaction.getItemGroupName()).isEqualTo(DEFAULT_ITEM_GROUP_NAME);
        assertThat(testSoLcdCoverageCriteriaTransaction.getCoverageCriteriaName()).isEqualTo(DEFAULT_COVERAGE_CRITERIA_NAME);
    }

    @Test
    void createSoLcdCoverageCriteriaTransactionWithExistingId() throws Exception {
        // Create the SoLcdCoverageCriteriaTransaction with an existing ID
        soLcdCoverageCriteriaTransaction.setSoLcdCoverageCriteriaTransactionId(1L);
        SoLcdCoverageCriteriaTransactionDTO soLcdCoverageCriteriaTransactionDTO = soLcdCoverageCriteriaTransactionMapper.toDto(
            soLcdCoverageCriteriaTransaction
        );

        int databaseSizeBeforeCreate = soLcdCoverageCriteriaTransactionRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(soLcdCoverageCriteriaTransactionDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SoLcdCoverageCriteriaTransaction in the database
        List<SoLcdCoverageCriteriaTransaction> soLcdCoverageCriteriaTransactionList = soLcdCoverageCriteriaTransactionRepository
            .findAll()
            .collectList()
            .block();
        assertThat(soLcdCoverageCriteriaTransactionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllSoLcdCoverageCriteriaTransactions() {
        // Initialize the database
        soLcdCoverageCriteriaTransactionRepository.save(soLcdCoverageCriteriaTransaction).block();

        // Get all the soLcdCoverageCriteriaTransactionList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=soLcdCoverageCriteriaTransactionId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].soLcdCoverageCriteriaTransactionId")
            .value(hasItem(soLcdCoverageCriteriaTransaction.getSoLcdCoverageCriteriaTransactionId().intValue()))
            .jsonPath("$.[*].soId")
            .value(hasItem(DEFAULT_SO_ID.intValue()))
            .jsonPath("$.[*].checklistId")
            .value(hasItem(DEFAULT_CHECKLIST_ID.intValue()))
            .jsonPath("$.[*].checklistName")
            .value(hasItem(DEFAULT_CHECKLIST_NAME))
            .jsonPath("$.[*].coverageCriteriaId")
            .value(hasItem(DEFAULT_COVERAGE_CRITERIA_ID.intValue()))
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
            .jsonPath("$.[*].soLcdCoverageCriteriaTransactionUuid")
            .value(hasItem(DEFAULT_SO_LCD_COVERAGE_CRITERIA_TRANSACTION_UUID.toString()))
            .jsonPath("$.[*].itemGroupId")
            .value(hasItem(DEFAULT_ITEM_GROUP_ID.intValue()))
            .jsonPath("$.[*].itemGroupName")
            .value(hasItem(DEFAULT_ITEM_GROUP_NAME))
            .jsonPath("$.[*].coverageCriteriaName")
            .value(hasItem(DEFAULT_COVERAGE_CRITERIA_NAME));
    }

    @Test
    void getSoLcdCoverageCriteriaTransaction() {
        // Initialize the database
        soLcdCoverageCriteriaTransactionRepository.save(soLcdCoverageCriteriaTransaction).block();

        // Get the soLcdCoverageCriteriaTransaction
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, soLcdCoverageCriteriaTransaction.getSoLcdCoverageCriteriaTransactionId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.soLcdCoverageCriteriaTransactionId")
            .value(is(soLcdCoverageCriteriaTransaction.getSoLcdCoverageCriteriaTransactionId().intValue()))
            .jsonPath("$.soId")
            .value(is(DEFAULT_SO_ID.intValue()))
            .jsonPath("$.checklistId")
            .value(is(DEFAULT_CHECKLIST_ID.intValue()))
            .jsonPath("$.checklistName")
            .value(is(DEFAULT_CHECKLIST_NAME))
            .jsonPath("$.coverageCriteriaId")
            .value(is(DEFAULT_COVERAGE_CRITERIA_ID.intValue()))
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
            .jsonPath("$.soLcdCoverageCriteriaTransactionUuid")
            .value(is(DEFAULT_SO_LCD_COVERAGE_CRITERIA_TRANSACTION_UUID.toString()))
            .jsonPath("$.itemGroupId")
            .value(is(DEFAULT_ITEM_GROUP_ID.intValue()))
            .jsonPath("$.itemGroupName")
            .value(is(DEFAULT_ITEM_GROUP_NAME))
            .jsonPath("$.coverageCriteriaName")
            .value(is(DEFAULT_COVERAGE_CRITERIA_NAME));
    }

    @Test
    void getNonExistingSoLcdCoverageCriteriaTransaction() {
        // Get the soLcdCoverageCriteriaTransaction
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingSoLcdCoverageCriteriaTransaction() throws Exception {
        // Initialize the database
        soLcdCoverageCriteriaTransactionRepository.save(soLcdCoverageCriteriaTransaction).block();

        int databaseSizeBeforeUpdate = soLcdCoverageCriteriaTransactionRepository.findAll().collectList().block().size();

        // Update the soLcdCoverageCriteriaTransaction
        SoLcdCoverageCriteriaTransaction updatedSoLcdCoverageCriteriaTransaction = soLcdCoverageCriteriaTransactionRepository
            .findById(soLcdCoverageCriteriaTransaction.getSoLcdCoverageCriteriaTransactionId())
            .block();
        updatedSoLcdCoverageCriteriaTransaction
            .soId(UPDATED_SO_ID)
            .checklistId(UPDATED_CHECKLIST_ID)
            .checklistName(UPDATED_CHECKLIST_NAME)
            .coverageCriteriaId(UPDATED_COVERAGE_CRITERIA_ID)
            .value(UPDATED_VALUE)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .soLcdCoverageCriteriaTransactionUuid(UPDATED_SO_LCD_COVERAGE_CRITERIA_TRANSACTION_UUID)
            .itemGroupId(UPDATED_ITEM_GROUP_ID)
            .itemGroupName(UPDATED_ITEM_GROUP_NAME)
            .coverageCriteriaName(UPDATED_COVERAGE_CRITERIA_NAME);
        SoLcdCoverageCriteriaTransactionDTO soLcdCoverageCriteriaTransactionDTO = soLcdCoverageCriteriaTransactionMapper.toDto(
            updatedSoLcdCoverageCriteriaTransaction
        );

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, soLcdCoverageCriteriaTransactionDTO.getSoLcdCoverageCriteriaTransactionId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(soLcdCoverageCriteriaTransactionDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SoLcdCoverageCriteriaTransaction in the database
        List<SoLcdCoverageCriteriaTransaction> soLcdCoverageCriteriaTransactionList = soLcdCoverageCriteriaTransactionRepository
            .findAll()
            .collectList()
            .block();
        assertThat(soLcdCoverageCriteriaTransactionList).hasSize(databaseSizeBeforeUpdate);
        SoLcdCoverageCriteriaTransaction testSoLcdCoverageCriteriaTransaction = soLcdCoverageCriteriaTransactionList.get(
            soLcdCoverageCriteriaTransactionList.size() - 1
        );
        assertThat(testSoLcdCoverageCriteriaTransaction.getSoId()).isEqualTo(UPDATED_SO_ID);
        assertThat(testSoLcdCoverageCriteriaTransaction.getChecklistId()).isEqualTo(UPDATED_CHECKLIST_ID);
        assertThat(testSoLcdCoverageCriteriaTransaction.getChecklistName()).isEqualTo(UPDATED_CHECKLIST_NAME);
        assertThat(testSoLcdCoverageCriteriaTransaction.getCoverageCriteriaId()).isEqualTo(UPDATED_COVERAGE_CRITERIA_ID);
        assertThat(testSoLcdCoverageCriteriaTransaction.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testSoLcdCoverageCriteriaTransaction.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSoLcdCoverageCriteriaTransaction.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSoLcdCoverageCriteriaTransaction.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testSoLcdCoverageCriteriaTransaction.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testSoLcdCoverageCriteriaTransaction.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testSoLcdCoverageCriteriaTransaction.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testSoLcdCoverageCriteriaTransaction.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testSoLcdCoverageCriteriaTransaction.getSoLcdCoverageCriteriaTransactionUuid())
            .isEqualTo(UPDATED_SO_LCD_COVERAGE_CRITERIA_TRANSACTION_UUID);
        assertThat(testSoLcdCoverageCriteriaTransaction.getItemGroupId()).isEqualTo(UPDATED_ITEM_GROUP_ID);
        assertThat(testSoLcdCoverageCriteriaTransaction.getItemGroupName()).isEqualTo(UPDATED_ITEM_GROUP_NAME);
        assertThat(testSoLcdCoverageCriteriaTransaction.getCoverageCriteriaName()).isEqualTo(UPDATED_COVERAGE_CRITERIA_NAME);
    }

    @Test
    void putNonExistingSoLcdCoverageCriteriaTransaction() throws Exception {
        int databaseSizeBeforeUpdate = soLcdCoverageCriteriaTransactionRepository.findAll().collectList().block().size();
        soLcdCoverageCriteriaTransaction.setSoLcdCoverageCriteriaTransactionId(count.incrementAndGet());

        // Create the SoLcdCoverageCriteriaTransaction
        SoLcdCoverageCriteriaTransactionDTO soLcdCoverageCriteriaTransactionDTO = soLcdCoverageCriteriaTransactionMapper.toDto(
            soLcdCoverageCriteriaTransaction
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, soLcdCoverageCriteriaTransactionDTO.getSoLcdCoverageCriteriaTransactionId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(soLcdCoverageCriteriaTransactionDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SoLcdCoverageCriteriaTransaction in the database
        List<SoLcdCoverageCriteriaTransaction> soLcdCoverageCriteriaTransactionList = soLcdCoverageCriteriaTransactionRepository
            .findAll()
            .collectList()
            .block();
        assertThat(soLcdCoverageCriteriaTransactionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchSoLcdCoverageCriteriaTransaction() throws Exception {
        int databaseSizeBeforeUpdate = soLcdCoverageCriteriaTransactionRepository.findAll().collectList().block().size();
        soLcdCoverageCriteriaTransaction.setSoLcdCoverageCriteriaTransactionId(count.incrementAndGet());

        // Create the SoLcdCoverageCriteriaTransaction
        SoLcdCoverageCriteriaTransactionDTO soLcdCoverageCriteriaTransactionDTO = soLcdCoverageCriteriaTransactionMapper.toDto(
            soLcdCoverageCriteriaTransaction
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(soLcdCoverageCriteriaTransactionDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SoLcdCoverageCriteriaTransaction in the database
        List<SoLcdCoverageCriteriaTransaction> soLcdCoverageCriteriaTransactionList = soLcdCoverageCriteriaTransactionRepository
            .findAll()
            .collectList()
            .block();
        assertThat(soLcdCoverageCriteriaTransactionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamSoLcdCoverageCriteriaTransaction() throws Exception {
        int databaseSizeBeforeUpdate = soLcdCoverageCriteriaTransactionRepository.findAll().collectList().block().size();
        soLcdCoverageCriteriaTransaction.setSoLcdCoverageCriteriaTransactionId(count.incrementAndGet());

        // Create the SoLcdCoverageCriteriaTransaction
        SoLcdCoverageCriteriaTransactionDTO soLcdCoverageCriteriaTransactionDTO = soLcdCoverageCriteriaTransactionMapper.toDto(
            soLcdCoverageCriteriaTransaction
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(soLcdCoverageCriteriaTransactionDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the SoLcdCoverageCriteriaTransaction in the database
        List<SoLcdCoverageCriteriaTransaction> soLcdCoverageCriteriaTransactionList = soLcdCoverageCriteriaTransactionRepository
            .findAll()
            .collectList()
            .block();
        assertThat(soLcdCoverageCriteriaTransactionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateSoLcdCoverageCriteriaTransactionWithPatch() throws Exception {
        // Initialize the database
        soLcdCoverageCriteriaTransactionRepository.save(soLcdCoverageCriteriaTransaction).block();

        int databaseSizeBeforeUpdate = soLcdCoverageCriteriaTransactionRepository.findAll().collectList().block().size();

        // Update the soLcdCoverageCriteriaTransaction using partial update
        SoLcdCoverageCriteriaTransaction partialUpdatedSoLcdCoverageCriteriaTransaction = new SoLcdCoverageCriteriaTransaction();
        partialUpdatedSoLcdCoverageCriteriaTransaction.setSoLcdCoverageCriteriaTransactionId(
            soLcdCoverageCriteriaTransaction.getSoLcdCoverageCriteriaTransactionId()
        );

        partialUpdatedSoLcdCoverageCriteriaTransaction
            .checklistId(UPDATED_CHECKLIST_ID)
            .checklistName(UPDATED_CHECKLIST_NAME)
            .value(UPDATED_VALUE)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .soLcdCoverageCriteriaTransactionUuid(UPDATED_SO_LCD_COVERAGE_CRITERIA_TRANSACTION_UUID)
            .coverageCriteriaName(UPDATED_COVERAGE_CRITERIA_NAME);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedSoLcdCoverageCriteriaTransaction.getSoLcdCoverageCriteriaTransactionId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedSoLcdCoverageCriteriaTransaction))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SoLcdCoverageCriteriaTransaction in the database
        List<SoLcdCoverageCriteriaTransaction> soLcdCoverageCriteriaTransactionList = soLcdCoverageCriteriaTransactionRepository
            .findAll()
            .collectList()
            .block();
        assertThat(soLcdCoverageCriteriaTransactionList).hasSize(databaseSizeBeforeUpdate);
        SoLcdCoverageCriteriaTransaction testSoLcdCoverageCriteriaTransaction = soLcdCoverageCriteriaTransactionList.get(
            soLcdCoverageCriteriaTransactionList.size() - 1
        );
        assertThat(testSoLcdCoverageCriteriaTransaction.getSoId()).isEqualTo(DEFAULT_SO_ID);
        assertThat(testSoLcdCoverageCriteriaTransaction.getChecklistId()).isEqualTo(UPDATED_CHECKLIST_ID);
        assertThat(testSoLcdCoverageCriteriaTransaction.getChecklistName()).isEqualTo(UPDATED_CHECKLIST_NAME);
        assertThat(testSoLcdCoverageCriteriaTransaction.getCoverageCriteriaId()).isEqualTo(DEFAULT_COVERAGE_CRITERIA_ID);
        assertThat(testSoLcdCoverageCriteriaTransaction.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testSoLcdCoverageCriteriaTransaction.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSoLcdCoverageCriteriaTransaction.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSoLcdCoverageCriteriaTransaction.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testSoLcdCoverageCriteriaTransaction.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testSoLcdCoverageCriteriaTransaction.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testSoLcdCoverageCriteriaTransaction.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testSoLcdCoverageCriteriaTransaction.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testSoLcdCoverageCriteriaTransaction.getSoLcdCoverageCriteriaTransactionUuid())
            .isEqualTo(UPDATED_SO_LCD_COVERAGE_CRITERIA_TRANSACTION_UUID);
        assertThat(testSoLcdCoverageCriteriaTransaction.getItemGroupId()).isEqualTo(DEFAULT_ITEM_GROUP_ID);
        assertThat(testSoLcdCoverageCriteriaTransaction.getItemGroupName()).isEqualTo(DEFAULT_ITEM_GROUP_NAME);
        assertThat(testSoLcdCoverageCriteriaTransaction.getCoverageCriteriaName()).isEqualTo(UPDATED_COVERAGE_CRITERIA_NAME);
    }

    @Test
    void fullUpdateSoLcdCoverageCriteriaTransactionWithPatch() throws Exception {
        // Initialize the database
        soLcdCoverageCriteriaTransactionRepository.save(soLcdCoverageCriteriaTransaction).block();

        int databaseSizeBeforeUpdate = soLcdCoverageCriteriaTransactionRepository.findAll().collectList().block().size();

        // Update the soLcdCoverageCriteriaTransaction using partial update
        SoLcdCoverageCriteriaTransaction partialUpdatedSoLcdCoverageCriteriaTransaction = new SoLcdCoverageCriteriaTransaction();
        partialUpdatedSoLcdCoverageCriteriaTransaction.setSoLcdCoverageCriteriaTransactionId(
            soLcdCoverageCriteriaTransaction.getSoLcdCoverageCriteriaTransactionId()
        );

        partialUpdatedSoLcdCoverageCriteriaTransaction
            .soId(UPDATED_SO_ID)
            .checklistId(UPDATED_CHECKLIST_ID)
            .checklistName(UPDATED_CHECKLIST_NAME)
            .coverageCriteriaId(UPDATED_COVERAGE_CRITERIA_ID)
            .value(UPDATED_VALUE)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .soLcdCoverageCriteriaTransactionUuid(UPDATED_SO_LCD_COVERAGE_CRITERIA_TRANSACTION_UUID)
            .itemGroupId(UPDATED_ITEM_GROUP_ID)
            .itemGroupName(UPDATED_ITEM_GROUP_NAME)
            .coverageCriteriaName(UPDATED_COVERAGE_CRITERIA_NAME);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedSoLcdCoverageCriteriaTransaction.getSoLcdCoverageCriteriaTransactionId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedSoLcdCoverageCriteriaTransaction))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SoLcdCoverageCriteriaTransaction in the database
        List<SoLcdCoverageCriteriaTransaction> soLcdCoverageCriteriaTransactionList = soLcdCoverageCriteriaTransactionRepository
            .findAll()
            .collectList()
            .block();
        assertThat(soLcdCoverageCriteriaTransactionList).hasSize(databaseSizeBeforeUpdate);
        SoLcdCoverageCriteriaTransaction testSoLcdCoverageCriteriaTransaction = soLcdCoverageCriteriaTransactionList.get(
            soLcdCoverageCriteriaTransactionList.size() - 1
        );
        assertThat(testSoLcdCoverageCriteriaTransaction.getSoId()).isEqualTo(UPDATED_SO_ID);
        assertThat(testSoLcdCoverageCriteriaTransaction.getChecklistId()).isEqualTo(UPDATED_CHECKLIST_ID);
        assertThat(testSoLcdCoverageCriteriaTransaction.getChecklistName()).isEqualTo(UPDATED_CHECKLIST_NAME);
        assertThat(testSoLcdCoverageCriteriaTransaction.getCoverageCriteriaId()).isEqualTo(UPDATED_COVERAGE_CRITERIA_ID);
        assertThat(testSoLcdCoverageCriteriaTransaction.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testSoLcdCoverageCriteriaTransaction.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSoLcdCoverageCriteriaTransaction.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSoLcdCoverageCriteriaTransaction.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testSoLcdCoverageCriteriaTransaction.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testSoLcdCoverageCriteriaTransaction.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testSoLcdCoverageCriteriaTransaction.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testSoLcdCoverageCriteriaTransaction.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testSoLcdCoverageCriteriaTransaction.getSoLcdCoverageCriteriaTransactionUuid())
            .isEqualTo(UPDATED_SO_LCD_COVERAGE_CRITERIA_TRANSACTION_UUID);
        assertThat(testSoLcdCoverageCriteriaTransaction.getItemGroupId()).isEqualTo(UPDATED_ITEM_GROUP_ID);
        assertThat(testSoLcdCoverageCriteriaTransaction.getItemGroupName()).isEqualTo(UPDATED_ITEM_GROUP_NAME);
        assertThat(testSoLcdCoverageCriteriaTransaction.getCoverageCriteriaName()).isEqualTo(UPDATED_COVERAGE_CRITERIA_NAME);
    }

    @Test
    void patchNonExistingSoLcdCoverageCriteriaTransaction() throws Exception {
        int databaseSizeBeforeUpdate = soLcdCoverageCriteriaTransactionRepository.findAll().collectList().block().size();
        soLcdCoverageCriteriaTransaction.setSoLcdCoverageCriteriaTransactionId(count.incrementAndGet());

        // Create the SoLcdCoverageCriteriaTransaction
        SoLcdCoverageCriteriaTransactionDTO soLcdCoverageCriteriaTransactionDTO = soLcdCoverageCriteriaTransactionMapper.toDto(
            soLcdCoverageCriteriaTransaction
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, soLcdCoverageCriteriaTransactionDTO.getSoLcdCoverageCriteriaTransactionId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(soLcdCoverageCriteriaTransactionDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SoLcdCoverageCriteriaTransaction in the database
        List<SoLcdCoverageCriteriaTransaction> soLcdCoverageCriteriaTransactionList = soLcdCoverageCriteriaTransactionRepository
            .findAll()
            .collectList()
            .block();
        assertThat(soLcdCoverageCriteriaTransactionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchSoLcdCoverageCriteriaTransaction() throws Exception {
        int databaseSizeBeforeUpdate = soLcdCoverageCriteriaTransactionRepository.findAll().collectList().block().size();
        soLcdCoverageCriteriaTransaction.setSoLcdCoverageCriteriaTransactionId(count.incrementAndGet());

        // Create the SoLcdCoverageCriteriaTransaction
        SoLcdCoverageCriteriaTransactionDTO soLcdCoverageCriteriaTransactionDTO = soLcdCoverageCriteriaTransactionMapper.toDto(
            soLcdCoverageCriteriaTransaction
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(soLcdCoverageCriteriaTransactionDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SoLcdCoverageCriteriaTransaction in the database
        List<SoLcdCoverageCriteriaTransaction> soLcdCoverageCriteriaTransactionList = soLcdCoverageCriteriaTransactionRepository
            .findAll()
            .collectList()
            .block();
        assertThat(soLcdCoverageCriteriaTransactionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamSoLcdCoverageCriteriaTransaction() throws Exception {
        int databaseSizeBeforeUpdate = soLcdCoverageCriteriaTransactionRepository.findAll().collectList().block().size();
        soLcdCoverageCriteriaTransaction.setSoLcdCoverageCriteriaTransactionId(count.incrementAndGet());

        // Create the SoLcdCoverageCriteriaTransaction
        SoLcdCoverageCriteriaTransactionDTO soLcdCoverageCriteriaTransactionDTO = soLcdCoverageCriteriaTransactionMapper.toDto(
            soLcdCoverageCriteriaTransaction
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(soLcdCoverageCriteriaTransactionDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the SoLcdCoverageCriteriaTransaction in the database
        List<SoLcdCoverageCriteriaTransaction> soLcdCoverageCriteriaTransactionList = soLcdCoverageCriteriaTransactionRepository
            .findAll()
            .collectList()
            .block();
        assertThat(soLcdCoverageCriteriaTransactionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteSoLcdCoverageCriteriaTransaction() {
        // Initialize the database
        soLcdCoverageCriteriaTransactionRepository.save(soLcdCoverageCriteriaTransaction).block();

        int databaseSizeBeforeDelete = soLcdCoverageCriteriaTransactionRepository.findAll().collectList().block().size();

        // Delete the soLcdCoverageCriteriaTransaction
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, soLcdCoverageCriteriaTransaction.getSoLcdCoverageCriteriaTransactionId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<SoLcdCoverageCriteriaTransaction> soLcdCoverageCriteriaTransactionList = soLcdCoverageCriteriaTransactionRepository
            .findAll()
            .collectList()
            .block();
        assertThat(soLcdCoverageCriteriaTransactionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
