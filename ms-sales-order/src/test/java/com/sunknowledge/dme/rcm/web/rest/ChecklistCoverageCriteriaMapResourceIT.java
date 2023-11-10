package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ChecklistCoverageCriteriaMap;
import com.sunknowledge.dme.rcm.repository.ChecklistCoverageCriteriaMapRepository;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.service.dto.ChecklistCoverageCriteriaMapDTO;
import com.sunknowledge.dme.rcm.service.mapper.ChecklistCoverageCriteriaMapMapper;
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
 * Integration tests for the {@link ChecklistCoverageCriteriaMapResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class ChecklistCoverageCriteriaMapResourceIT {

    private static final Long DEFAULT_CHECKLIST_ID = 1L;
    private static final Long UPDATED_CHECKLIST_ID = 2L;

    private static final String DEFAULT_CHECKLIST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CHECKLIST_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_COVERAGE_CRITERIA_ID = 1L;
    private static final Long UPDATED_COVERAGE_CRITERIA_ID = 2L;

    private static final String DEFAULT_COVERAGE_CRITERIA_DETAILS = "AAAAAAAAAA";
    private static final String UPDATED_COVERAGE_CRITERIA_DETAILS = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_CHECKLIST_COVERAGE_CRITERIA_MAP_UUID = UUID.randomUUID();
    private static final UUID UPDATED_CHECKLIST_COVERAGE_CRITERIA_MAP_UUID = UUID.randomUUID();

    private static final String DEFAULT_COVERAGE_CRITERIA_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COVERAGE_CRITERIA_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/checklist-coverage-criteria-maps";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{checklistCoverageCriteriaId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ChecklistCoverageCriteriaMapRepository checklistCoverageCriteriaMapRepository;

    @Autowired
    private ChecklistCoverageCriteriaMapMapper checklistCoverageCriteriaMapMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private ChecklistCoverageCriteriaMap checklistCoverageCriteriaMap;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ChecklistCoverageCriteriaMap createEntity(EntityManager em) {
        ChecklistCoverageCriteriaMap checklistCoverageCriteriaMap = new ChecklistCoverageCriteriaMap()
            .checklistId(DEFAULT_CHECKLIST_ID)
            .checklistName(DEFAULT_CHECKLIST_NAME)
            .coverageCriteriaId(DEFAULT_COVERAGE_CRITERIA_ID)
            .coverageCriteriaDetails(DEFAULT_COVERAGE_CRITERIA_DETAILS)
            .status(DEFAULT_STATUS)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .checklistCoverageCriteriaMapUuid(DEFAULT_CHECKLIST_COVERAGE_CRITERIA_MAP_UUID)
            .coverageCriteriaName(DEFAULT_COVERAGE_CRITERIA_NAME);
        return checklistCoverageCriteriaMap;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ChecklistCoverageCriteriaMap createUpdatedEntity(EntityManager em) {
        ChecklistCoverageCriteriaMap checklistCoverageCriteriaMap = new ChecklistCoverageCriteriaMap()
            .checklistId(UPDATED_CHECKLIST_ID)
            .checklistName(UPDATED_CHECKLIST_NAME)
            .coverageCriteriaId(UPDATED_COVERAGE_CRITERIA_ID)
            .coverageCriteriaDetails(UPDATED_COVERAGE_CRITERIA_DETAILS)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .checklistCoverageCriteriaMapUuid(UPDATED_CHECKLIST_COVERAGE_CRITERIA_MAP_UUID)
            .coverageCriteriaName(UPDATED_COVERAGE_CRITERIA_NAME);
        return checklistCoverageCriteriaMap;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(ChecklistCoverageCriteriaMap.class).block();
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
        checklistCoverageCriteriaMap = createEntity(em);
    }

    @Test
    void createChecklistCoverageCriteriaMap() throws Exception {
        int databaseSizeBeforeCreate = checklistCoverageCriteriaMapRepository.findAll().collectList().block().size();
        // Create the ChecklistCoverageCriteriaMap
        ChecklistCoverageCriteriaMapDTO checklistCoverageCriteriaMapDTO = checklistCoverageCriteriaMapMapper.toDto(
            checklistCoverageCriteriaMap
        );
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(checklistCoverageCriteriaMapDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the ChecklistCoverageCriteriaMap in the database
        List<ChecklistCoverageCriteriaMap> checklistCoverageCriteriaMapList = checklistCoverageCriteriaMapRepository
            .findAll()
            .collectList()
            .block();
        assertThat(checklistCoverageCriteriaMapList).hasSize(databaseSizeBeforeCreate + 1);
        ChecklistCoverageCriteriaMap testChecklistCoverageCriteriaMap = checklistCoverageCriteriaMapList.get(
            checklistCoverageCriteriaMapList.size() - 1
        );
        assertThat(testChecklistCoverageCriteriaMap.getChecklistId()).isEqualTo(DEFAULT_CHECKLIST_ID);
        assertThat(testChecklistCoverageCriteriaMap.getChecklistName()).isEqualTo(DEFAULT_CHECKLIST_NAME);
        assertThat(testChecklistCoverageCriteriaMap.getCoverageCriteriaId()).isEqualTo(DEFAULT_COVERAGE_CRITERIA_ID);
        assertThat(testChecklistCoverageCriteriaMap.getCoverageCriteriaDetails()).isEqualTo(DEFAULT_COVERAGE_CRITERIA_DETAILS);
        assertThat(testChecklistCoverageCriteriaMap.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testChecklistCoverageCriteriaMap.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testChecklistCoverageCriteriaMap.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testChecklistCoverageCriteriaMap.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testChecklistCoverageCriteriaMap.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testChecklistCoverageCriteriaMap.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testChecklistCoverageCriteriaMap.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testChecklistCoverageCriteriaMap.getChecklistCoverageCriteriaMapUuid())
            .isEqualTo(DEFAULT_CHECKLIST_COVERAGE_CRITERIA_MAP_UUID);
        assertThat(testChecklistCoverageCriteriaMap.getCoverageCriteriaName()).isEqualTo(DEFAULT_COVERAGE_CRITERIA_NAME);
    }

    @Test
    void createChecklistCoverageCriteriaMapWithExistingId() throws Exception {
        // Create the ChecklistCoverageCriteriaMap with an existing ID
        checklistCoverageCriteriaMap.setChecklistCoverageCriteriaId(1L);
        ChecklistCoverageCriteriaMapDTO checklistCoverageCriteriaMapDTO = checklistCoverageCriteriaMapMapper.toDto(
            checklistCoverageCriteriaMap
        );

        int databaseSizeBeforeCreate = checklistCoverageCriteriaMapRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(checklistCoverageCriteriaMapDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ChecklistCoverageCriteriaMap in the database
        List<ChecklistCoverageCriteriaMap> checklistCoverageCriteriaMapList = checklistCoverageCriteriaMapRepository
            .findAll()
            .collectList()
            .block();
        assertThat(checklistCoverageCriteriaMapList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllChecklistCoverageCriteriaMaps() {
        // Initialize the database
        checklistCoverageCriteriaMapRepository.save(checklistCoverageCriteriaMap).block();

        // Get all the checklistCoverageCriteriaMapList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=checklistCoverageCriteriaId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].checklistCoverageCriteriaId")
            .value(hasItem(checklistCoverageCriteriaMap.getChecklistCoverageCriteriaId().intValue()))
            .jsonPath("$.[*].checklistId")
            .value(hasItem(DEFAULT_CHECKLIST_ID.intValue()))
            .jsonPath("$.[*].checklistName")
            .value(hasItem(DEFAULT_CHECKLIST_NAME))
            .jsonPath("$.[*].coverageCriteriaId")
            .value(hasItem(DEFAULT_COVERAGE_CRITERIA_ID.intValue()))
            .jsonPath("$.[*].coverageCriteriaDetails")
            .value(hasItem(DEFAULT_COVERAGE_CRITERIA_DETAILS))
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
            .jsonPath("$.[*].checklistCoverageCriteriaMapUuid")
            .value(hasItem(DEFAULT_CHECKLIST_COVERAGE_CRITERIA_MAP_UUID.toString()))
            .jsonPath("$.[*].coverageCriteriaName")
            .value(hasItem(DEFAULT_COVERAGE_CRITERIA_NAME));
    }

    @Test
    void getChecklistCoverageCriteriaMap() {
        // Initialize the database
        checklistCoverageCriteriaMapRepository.save(checklistCoverageCriteriaMap).block();

        // Get the checklistCoverageCriteriaMap
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, checklistCoverageCriteriaMap.getChecklistCoverageCriteriaId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.checklistCoverageCriteriaId")
            .value(is(checklistCoverageCriteriaMap.getChecklistCoverageCriteriaId().intValue()))
            .jsonPath("$.checklistId")
            .value(is(DEFAULT_CHECKLIST_ID.intValue()))
            .jsonPath("$.checklistName")
            .value(is(DEFAULT_CHECKLIST_NAME))
            .jsonPath("$.coverageCriteriaId")
            .value(is(DEFAULT_COVERAGE_CRITERIA_ID.intValue()))
            .jsonPath("$.coverageCriteriaDetails")
            .value(is(DEFAULT_COVERAGE_CRITERIA_DETAILS))
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
            .jsonPath("$.checklistCoverageCriteriaMapUuid")
            .value(is(DEFAULT_CHECKLIST_COVERAGE_CRITERIA_MAP_UUID.toString()))
            .jsonPath("$.coverageCriteriaName")
            .value(is(DEFAULT_COVERAGE_CRITERIA_NAME));
    }

    @Test
    void getNonExistingChecklistCoverageCriteriaMap() {
        // Get the checklistCoverageCriteriaMap
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putNewChecklistCoverageCriteriaMap() throws Exception {
        // Initialize the database
        checklistCoverageCriteriaMapRepository.save(checklistCoverageCriteriaMap).block();

        int databaseSizeBeforeUpdate = checklistCoverageCriteriaMapRepository.findAll().collectList().block().size();

        // Update the checklistCoverageCriteriaMap
        ChecklistCoverageCriteriaMap updatedChecklistCoverageCriteriaMap = checklistCoverageCriteriaMapRepository
            .findById(checklistCoverageCriteriaMap.getChecklistCoverageCriteriaId())
            .block();
        updatedChecklistCoverageCriteriaMap
            .checklistId(UPDATED_CHECKLIST_ID)
            .checklistName(UPDATED_CHECKLIST_NAME)
            .coverageCriteriaId(UPDATED_COVERAGE_CRITERIA_ID)
            .coverageCriteriaDetails(UPDATED_COVERAGE_CRITERIA_DETAILS)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .checklistCoverageCriteriaMapUuid(UPDATED_CHECKLIST_COVERAGE_CRITERIA_MAP_UUID)
            .coverageCriteriaName(UPDATED_COVERAGE_CRITERIA_NAME);
        ChecklistCoverageCriteriaMapDTO checklistCoverageCriteriaMapDTO = checklistCoverageCriteriaMapMapper.toDto(
            updatedChecklistCoverageCriteriaMap
        );

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, checklistCoverageCriteriaMapDTO.getChecklistCoverageCriteriaId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(checklistCoverageCriteriaMapDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ChecklistCoverageCriteriaMap in the database
        List<ChecklistCoverageCriteriaMap> checklistCoverageCriteriaMapList = checklistCoverageCriteriaMapRepository
            .findAll()
            .collectList()
            .block();
        assertThat(checklistCoverageCriteriaMapList).hasSize(databaseSizeBeforeUpdate);
        ChecklistCoverageCriteriaMap testChecklistCoverageCriteriaMap = checklistCoverageCriteriaMapList.get(
            checklistCoverageCriteriaMapList.size() - 1
        );
        assertThat(testChecklistCoverageCriteriaMap.getChecklistId()).isEqualTo(UPDATED_CHECKLIST_ID);
        assertThat(testChecklistCoverageCriteriaMap.getChecklistName()).isEqualTo(UPDATED_CHECKLIST_NAME);
        assertThat(testChecklistCoverageCriteriaMap.getCoverageCriteriaId()).isEqualTo(UPDATED_COVERAGE_CRITERIA_ID);
        assertThat(testChecklistCoverageCriteriaMap.getCoverageCriteriaDetails()).isEqualTo(UPDATED_COVERAGE_CRITERIA_DETAILS);
        assertThat(testChecklistCoverageCriteriaMap.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testChecklistCoverageCriteriaMap.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testChecklistCoverageCriteriaMap.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testChecklistCoverageCriteriaMap.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testChecklistCoverageCriteriaMap.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testChecklistCoverageCriteriaMap.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testChecklistCoverageCriteriaMap.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testChecklistCoverageCriteriaMap.getChecklistCoverageCriteriaMapUuid())
            .isEqualTo(UPDATED_CHECKLIST_COVERAGE_CRITERIA_MAP_UUID);
        assertThat(testChecklistCoverageCriteriaMap.getCoverageCriteriaName()).isEqualTo(UPDATED_COVERAGE_CRITERIA_NAME);
    }

    @Test
    void putNonExistingChecklistCoverageCriteriaMap() throws Exception {
        int databaseSizeBeforeUpdate = checklistCoverageCriteriaMapRepository.findAll().collectList().block().size();
        checklistCoverageCriteriaMap.setChecklistCoverageCriteriaId(count.incrementAndGet());

        // Create the ChecklistCoverageCriteriaMap
        ChecklistCoverageCriteriaMapDTO checklistCoverageCriteriaMapDTO = checklistCoverageCriteriaMapMapper.toDto(
            checklistCoverageCriteriaMap
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, checklistCoverageCriteriaMapDTO.getChecklistCoverageCriteriaId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(checklistCoverageCriteriaMapDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ChecklistCoverageCriteriaMap in the database
        List<ChecklistCoverageCriteriaMap> checklistCoverageCriteriaMapList = checklistCoverageCriteriaMapRepository
            .findAll()
            .collectList()
            .block();
        assertThat(checklistCoverageCriteriaMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchChecklistCoverageCriteriaMap() throws Exception {
        int databaseSizeBeforeUpdate = checklistCoverageCriteriaMapRepository.findAll().collectList().block().size();
        checklistCoverageCriteriaMap.setChecklistCoverageCriteriaId(count.incrementAndGet());

        // Create the ChecklistCoverageCriteriaMap
        ChecklistCoverageCriteriaMapDTO checklistCoverageCriteriaMapDTO = checklistCoverageCriteriaMapMapper.toDto(
            checklistCoverageCriteriaMap
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(checklistCoverageCriteriaMapDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ChecklistCoverageCriteriaMap in the database
        List<ChecklistCoverageCriteriaMap> checklistCoverageCriteriaMapList = checklistCoverageCriteriaMapRepository
            .findAll()
            .collectList()
            .block();
        assertThat(checklistCoverageCriteriaMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamChecklistCoverageCriteriaMap() throws Exception {
        int databaseSizeBeforeUpdate = checklistCoverageCriteriaMapRepository.findAll().collectList().block().size();
        checklistCoverageCriteriaMap.setChecklistCoverageCriteriaId(count.incrementAndGet());

        // Create the ChecklistCoverageCriteriaMap
        ChecklistCoverageCriteriaMapDTO checklistCoverageCriteriaMapDTO = checklistCoverageCriteriaMapMapper.toDto(
            checklistCoverageCriteriaMap
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(checklistCoverageCriteriaMapDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the ChecklistCoverageCriteriaMap in the database
        List<ChecklistCoverageCriteriaMap> checklistCoverageCriteriaMapList = checklistCoverageCriteriaMapRepository
            .findAll()
            .collectList()
            .block();
        assertThat(checklistCoverageCriteriaMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateChecklistCoverageCriteriaMapWithPatch() throws Exception {
        // Initialize the database
        checklistCoverageCriteriaMapRepository.save(checklistCoverageCriteriaMap).block();

        int databaseSizeBeforeUpdate = checklistCoverageCriteriaMapRepository.findAll().collectList().block().size();

        // Update the checklistCoverageCriteriaMap using partial update
        ChecklistCoverageCriteriaMap partialUpdatedChecklistCoverageCriteriaMap = new ChecklistCoverageCriteriaMap();
        partialUpdatedChecklistCoverageCriteriaMap.setChecklistCoverageCriteriaId(
            checklistCoverageCriteriaMap.getChecklistCoverageCriteriaId()
        );

        partialUpdatedChecklistCoverageCriteriaMap
            .coverageCriteriaDetails(UPDATED_COVERAGE_CRITERIA_DETAILS)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .checklistCoverageCriteriaMapUuid(UPDATED_CHECKLIST_COVERAGE_CRITERIA_MAP_UUID)
            .coverageCriteriaName(UPDATED_COVERAGE_CRITERIA_NAME);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedChecklistCoverageCriteriaMap.getChecklistCoverageCriteriaId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedChecklistCoverageCriteriaMap))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ChecklistCoverageCriteriaMap in the database
        List<ChecklistCoverageCriteriaMap> checklistCoverageCriteriaMapList = checklistCoverageCriteriaMapRepository
            .findAll()
            .collectList()
            .block();
        assertThat(checklistCoverageCriteriaMapList).hasSize(databaseSizeBeforeUpdate);
        ChecklistCoverageCriteriaMap testChecklistCoverageCriteriaMap = checklistCoverageCriteriaMapList.get(
            checklistCoverageCriteriaMapList.size() - 1
        );
        assertThat(testChecklistCoverageCriteriaMap.getChecklistId()).isEqualTo(DEFAULT_CHECKLIST_ID);
        assertThat(testChecklistCoverageCriteriaMap.getChecklistName()).isEqualTo(DEFAULT_CHECKLIST_NAME);
        assertThat(testChecklistCoverageCriteriaMap.getCoverageCriteriaId()).isEqualTo(DEFAULT_COVERAGE_CRITERIA_ID);
        assertThat(testChecklistCoverageCriteriaMap.getCoverageCriteriaDetails()).isEqualTo(UPDATED_COVERAGE_CRITERIA_DETAILS);
        assertThat(testChecklistCoverageCriteriaMap.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testChecklistCoverageCriteriaMap.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testChecklistCoverageCriteriaMap.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testChecklistCoverageCriteriaMap.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testChecklistCoverageCriteriaMap.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testChecklistCoverageCriteriaMap.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testChecklistCoverageCriteriaMap.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testChecklistCoverageCriteriaMap.getChecklistCoverageCriteriaMapUuid())
            .isEqualTo(UPDATED_CHECKLIST_COVERAGE_CRITERIA_MAP_UUID);
        assertThat(testChecklistCoverageCriteriaMap.getCoverageCriteriaName()).isEqualTo(UPDATED_COVERAGE_CRITERIA_NAME);
    }

    @Test
    void fullUpdateChecklistCoverageCriteriaMapWithPatch() throws Exception {
        // Initialize the database
        checklistCoverageCriteriaMapRepository.save(checklistCoverageCriteriaMap).block();

        int databaseSizeBeforeUpdate = checklistCoverageCriteriaMapRepository.findAll().collectList().block().size();

        // Update the checklistCoverageCriteriaMap using partial update
        ChecklistCoverageCriteriaMap partialUpdatedChecklistCoverageCriteriaMap = new ChecklistCoverageCriteriaMap();
        partialUpdatedChecklistCoverageCriteriaMap.setChecklistCoverageCriteriaId(
            checklistCoverageCriteriaMap.getChecklistCoverageCriteriaId()
        );

        partialUpdatedChecklistCoverageCriteriaMap
            .checklistId(UPDATED_CHECKLIST_ID)
            .checklistName(UPDATED_CHECKLIST_NAME)
            .coverageCriteriaId(UPDATED_COVERAGE_CRITERIA_ID)
            .coverageCriteriaDetails(UPDATED_COVERAGE_CRITERIA_DETAILS)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .checklistCoverageCriteriaMapUuid(UPDATED_CHECKLIST_COVERAGE_CRITERIA_MAP_UUID)
            .coverageCriteriaName(UPDATED_COVERAGE_CRITERIA_NAME);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedChecklistCoverageCriteriaMap.getChecklistCoverageCriteriaId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedChecklistCoverageCriteriaMap))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ChecklistCoverageCriteriaMap in the database
        List<ChecklistCoverageCriteriaMap> checklistCoverageCriteriaMapList = checklistCoverageCriteriaMapRepository
            .findAll()
            .collectList()
            .block();
        assertThat(checklistCoverageCriteriaMapList).hasSize(databaseSizeBeforeUpdate);
        ChecklistCoverageCriteriaMap testChecklistCoverageCriteriaMap = checklistCoverageCriteriaMapList.get(
            checklistCoverageCriteriaMapList.size() - 1
        );
        assertThat(testChecklistCoverageCriteriaMap.getChecklistId()).isEqualTo(UPDATED_CHECKLIST_ID);
        assertThat(testChecklistCoverageCriteriaMap.getChecklistName()).isEqualTo(UPDATED_CHECKLIST_NAME);
        assertThat(testChecklistCoverageCriteriaMap.getCoverageCriteriaId()).isEqualTo(UPDATED_COVERAGE_CRITERIA_ID);
        assertThat(testChecklistCoverageCriteriaMap.getCoverageCriteriaDetails()).isEqualTo(UPDATED_COVERAGE_CRITERIA_DETAILS);
        assertThat(testChecklistCoverageCriteriaMap.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testChecklistCoverageCriteriaMap.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testChecklistCoverageCriteriaMap.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testChecklistCoverageCriteriaMap.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testChecklistCoverageCriteriaMap.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testChecklistCoverageCriteriaMap.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testChecklistCoverageCriteriaMap.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testChecklistCoverageCriteriaMap.getChecklistCoverageCriteriaMapUuid())
            .isEqualTo(UPDATED_CHECKLIST_COVERAGE_CRITERIA_MAP_UUID);
        assertThat(testChecklistCoverageCriteriaMap.getCoverageCriteriaName()).isEqualTo(UPDATED_COVERAGE_CRITERIA_NAME);
    }

    @Test
    void patchNonExistingChecklistCoverageCriteriaMap() throws Exception {
        int databaseSizeBeforeUpdate = checklistCoverageCriteriaMapRepository.findAll().collectList().block().size();
        checklistCoverageCriteriaMap.setChecklistCoverageCriteriaId(count.incrementAndGet());

        // Create the ChecklistCoverageCriteriaMap
        ChecklistCoverageCriteriaMapDTO checklistCoverageCriteriaMapDTO = checklistCoverageCriteriaMapMapper.toDto(
            checklistCoverageCriteriaMap
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, checklistCoverageCriteriaMapDTO.getChecklistCoverageCriteriaId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(checklistCoverageCriteriaMapDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ChecklistCoverageCriteriaMap in the database
        List<ChecklistCoverageCriteriaMap> checklistCoverageCriteriaMapList = checklistCoverageCriteriaMapRepository
            .findAll()
            .collectList()
            .block();
        assertThat(checklistCoverageCriteriaMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchChecklistCoverageCriteriaMap() throws Exception {
        int databaseSizeBeforeUpdate = checklistCoverageCriteriaMapRepository.findAll().collectList().block().size();
        checklistCoverageCriteriaMap.setChecklistCoverageCriteriaId(count.incrementAndGet());

        // Create the ChecklistCoverageCriteriaMap
        ChecklistCoverageCriteriaMapDTO checklistCoverageCriteriaMapDTO = checklistCoverageCriteriaMapMapper.toDto(
            checklistCoverageCriteriaMap
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(checklistCoverageCriteriaMapDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ChecklistCoverageCriteriaMap in the database
        List<ChecklistCoverageCriteriaMap> checklistCoverageCriteriaMapList = checklistCoverageCriteriaMapRepository
            .findAll()
            .collectList()
            .block();
        assertThat(checklistCoverageCriteriaMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamChecklistCoverageCriteriaMap() throws Exception {
        int databaseSizeBeforeUpdate = checklistCoverageCriteriaMapRepository.findAll().collectList().block().size();
        checklistCoverageCriteriaMap.setChecklistCoverageCriteriaId(count.incrementAndGet());

        // Create the ChecklistCoverageCriteriaMap
        ChecklistCoverageCriteriaMapDTO checklistCoverageCriteriaMapDTO = checklistCoverageCriteriaMapMapper.toDto(
            checklistCoverageCriteriaMap
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(checklistCoverageCriteriaMapDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the ChecklistCoverageCriteriaMap in the database
        List<ChecklistCoverageCriteriaMap> checklistCoverageCriteriaMapList = checklistCoverageCriteriaMapRepository
            .findAll()
            .collectList()
            .block();
        assertThat(checklistCoverageCriteriaMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteChecklistCoverageCriteriaMap() {
        // Initialize the database
        checklistCoverageCriteriaMapRepository.save(checklistCoverageCriteriaMap).block();

        int databaseSizeBeforeDelete = checklistCoverageCriteriaMapRepository.findAll().collectList().block().size();

        // Delete the checklistCoverageCriteriaMap
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, checklistCoverageCriteriaMap.getChecklistCoverageCriteriaId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<ChecklistCoverageCriteriaMap> checklistCoverageCriteriaMapList = checklistCoverageCriteriaMapRepository
            .findAll()
            .collectList()
            .block();
        assertThat(checklistCoverageCriteriaMapList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
