package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ChecklistDocumentReferenceMap;
import com.sunknowledge.dme.rcm.repository.ChecklistDocumentReferenceMapRepository;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.service.dto.ChecklistDocumentReferenceMapDTO;
import com.sunknowledge.dme.rcm.service.mapper.ChecklistDocumentReferenceMapMapper;
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
 * Integration tests for the {@link ChecklistDocumentReferenceMapResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class ChecklistDocumentReferenceMapResourceIT {

    private static final Long DEFAULT_CHECKLIST_ID = 1L;
    private static final Long UPDATED_CHECKLIST_ID = 2L;

    private static final String DEFAULT_CHECKLIST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CHECKLIST_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_DOCUMENT_REFERENCE_ID = 1L;
    private static final Long UPDATED_DOCUMENT_REFERENCE_ID = 2L;

    private static final String DEFAULT_DOCUMENT_REFERENCE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENT_REFERENCE_NAME = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_CHECKLIST_DOCUMENT_REFERENCE_MAP_UUID = UUID.randomUUID();
    private static final UUID UPDATED_CHECKLIST_DOCUMENT_REFERENCE_MAP_UUID = UUID.randomUUID();

    private static final Long DEFAULT_ITEM_GROUP_ID = 1L;
    private static final Long UPDATED_ITEM_GROUP_ID = 2L;

    private static final String DEFAULT_ITEM_GROUP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_GROUP_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/checklist-document-reference-maps";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{checklistDocumentReferenceId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ChecklistDocumentReferenceMapRepository checklistDocumentReferenceMapRepository;

    @Autowired
    private ChecklistDocumentReferenceMapMapper checklistDocumentReferenceMapMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private ChecklistDocumentReferenceMap checklistDocumentReferenceMap;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ChecklistDocumentReferenceMap createEntity(EntityManager em) {
        ChecklistDocumentReferenceMap checklistDocumentReferenceMap = new ChecklistDocumentReferenceMap()
            .checklistId(DEFAULT_CHECKLIST_ID)
            .checklistName(DEFAULT_CHECKLIST_NAME)
            .documentReferenceId(DEFAULT_DOCUMENT_REFERENCE_ID)
            .documentReferenceName(DEFAULT_DOCUMENT_REFERENCE_NAME)
            .status(DEFAULT_STATUS)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .checklistDocumentReferenceMapUuid(DEFAULT_CHECKLIST_DOCUMENT_REFERENCE_MAP_UUID)
            .itemGroupId(DEFAULT_ITEM_GROUP_ID)
            .itemGroupName(DEFAULT_ITEM_GROUP_NAME);
        return checklistDocumentReferenceMap;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ChecklistDocumentReferenceMap createUpdatedEntity(EntityManager em) {
        ChecklistDocumentReferenceMap checklistDocumentReferenceMap = new ChecklistDocumentReferenceMap()
            .checklistId(UPDATED_CHECKLIST_ID)
            .checklistName(UPDATED_CHECKLIST_NAME)
            .documentReferenceId(UPDATED_DOCUMENT_REFERENCE_ID)
            .documentReferenceName(UPDATED_DOCUMENT_REFERENCE_NAME)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .checklistDocumentReferenceMapUuid(UPDATED_CHECKLIST_DOCUMENT_REFERENCE_MAP_UUID)
            .itemGroupId(UPDATED_ITEM_GROUP_ID)
            .itemGroupName(UPDATED_ITEM_GROUP_NAME);
        return checklistDocumentReferenceMap;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(ChecklistDocumentReferenceMap.class).block();
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
        checklistDocumentReferenceMap = createEntity(em);
    }

    @Test
    void createChecklistDocumentReferenceMap() throws Exception {
        int databaseSizeBeforeCreate = checklistDocumentReferenceMapRepository.findAll().collectList().block().size();
        // Create the ChecklistDocumentReferenceMap
        ChecklistDocumentReferenceMapDTO checklistDocumentReferenceMapDTO = checklistDocumentReferenceMapMapper.toDto(
            checklistDocumentReferenceMap
        );
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(checklistDocumentReferenceMapDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the ChecklistDocumentReferenceMap in the database
        List<ChecklistDocumentReferenceMap> checklistDocumentReferenceMapList = checklistDocumentReferenceMapRepository
            .findAll()
            .collectList()
            .block();
        assertThat(checklistDocumentReferenceMapList).hasSize(databaseSizeBeforeCreate + 1);
        ChecklistDocumentReferenceMap testChecklistDocumentReferenceMap = checklistDocumentReferenceMapList.get(
            checklistDocumentReferenceMapList.size() - 1
        );
        assertThat(testChecklistDocumentReferenceMap.getChecklistId()).isEqualTo(DEFAULT_CHECKLIST_ID);
        assertThat(testChecklistDocumentReferenceMap.getChecklistName()).isEqualTo(DEFAULT_CHECKLIST_NAME);
        assertThat(testChecklistDocumentReferenceMap.getDocumentReferenceId()).isEqualTo(DEFAULT_DOCUMENT_REFERENCE_ID);
        assertThat(testChecklistDocumentReferenceMap.getDocumentReferenceName()).isEqualTo(DEFAULT_DOCUMENT_REFERENCE_NAME);
        assertThat(testChecklistDocumentReferenceMap.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testChecklistDocumentReferenceMap.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testChecklistDocumentReferenceMap.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testChecklistDocumentReferenceMap.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testChecklistDocumentReferenceMap.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testChecklistDocumentReferenceMap.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testChecklistDocumentReferenceMap.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testChecklistDocumentReferenceMap.getChecklistDocumentReferenceMapUuid())
            .isEqualTo(DEFAULT_CHECKLIST_DOCUMENT_REFERENCE_MAP_UUID);
        assertThat(testChecklistDocumentReferenceMap.getItemGroupId()).isEqualTo(DEFAULT_ITEM_GROUP_ID);
        assertThat(testChecklistDocumentReferenceMap.getItemGroupName()).isEqualTo(DEFAULT_ITEM_GROUP_NAME);
    }

    @Test
    void createChecklistDocumentReferenceMapWithExistingId() throws Exception {
        // Create the ChecklistDocumentReferenceMap with an existing ID
        checklistDocumentReferenceMap.setChecklistDocumentReferenceId(1L);
        ChecklistDocumentReferenceMapDTO checklistDocumentReferenceMapDTO = checklistDocumentReferenceMapMapper.toDto(
            checklistDocumentReferenceMap
        );

        int databaseSizeBeforeCreate = checklistDocumentReferenceMapRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(checklistDocumentReferenceMapDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ChecklistDocumentReferenceMap in the database
        List<ChecklistDocumentReferenceMap> checklistDocumentReferenceMapList = checklistDocumentReferenceMapRepository
            .findAll()
            .collectList()
            .block();
        assertThat(checklistDocumentReferenceMapList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllChecklistDocumentReferenceMaps() {
        // Initialize the database
        checklistDocumentReferenceMapRepository.save(checklistDocumentReferenceMap).block();

        // Get all the checklistDocumentReferenceMapList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=checklistDocumentReferenceId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].checklistDocumentReferenceId")
            .value(hasItem(checklistDocumentReferenceMap.getChecklistDocumentReferenceId().intValue()))
            .jsonPath("$.[*].checklistId")
            .value(hasItem(DEFAULT_CHECKLIST_ID.intValue()))
            .jsonPath("$.[*].checklistName")
            .value(hasItem(DEFAULT_CHECKLIST_NAME))
            .jsonPath("$.[*].documentReferenceId")
            .value(hasItem(DEFAULT_DOCUMENT_REFERENCE_ID.intValue()))
            .jsonPath("$.[*].documentReferenceName")
            .value(hasItem(DEFAULT_DOCUMENT_REFERENCE_NAME))
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
            .jsonPath("$.[*].checklistDocumentReferenceMapUuid")
            .value(hasItem(DEFAULT_CHECKLIST_DOCUMENT_REFERENCE_MAP_UUID.toString()))
            .jsonPath("$.[*].itemGroupId")
            .value(hasItem(DEFAULT_ITEM_GROUP_ID.intValue()))
            .jsonPath("$.[*].itemGroupName")
            .value(hasItem(DEFAULT_ITEM_GROUP_NAME));
    }

    @Test
    void getChecklistDocumentReferenceMap() {
        // Initialize the database
        checklistDocumentReferenceMapRepository.save(checklistDocumentReferenceMap).block();

        // Get the checklistDocumentReferenceMap
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, checklistDocumentReferenceMap.getChecklistDocumentReferenceId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.checklistDocumentReferenceId")
            .value(is(checklistDocumentReferenceMap.getChecklistDocumentReferenceId().intValue()))
            .jsonPath("$.checklistId")
            .value(is(DEFAULT_CHECKLIST_ID.intValue()))
            .jsonPath("$.checklistName")
            .value(is(DEFAULT_CHECKLIST_NAME))
            .jsonPath("$.documentReferenceId")
            .value(is(DEFAULT_DOCUMENT_REFERENCE_ID.intValue()))
            .jsonPath("$.documentReferenceName")
            .value(is(DEFAULT_DOCUMENT_REFERENCE_NAME))
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
            .jsonPath("$.checklistDocumentReferenceMapUuid")
            .value(is(DEFAULT_CHECKLIST_DOCUMENT_REFERENCE_MAP_UUID.toString()))
            .jsonPath("$.itemGroupId")
            .value(is(DEFAULT_ITEM_GROUP_ID.intValue()))
            .jsonPath("$.itemGroupName")
            .value(is(DEFAULT_ITEM_GROUP_NAME));
    }

    @Test
    void getNonExistingChecklistDocumentReferenceMap() {
        // Get the checklistDocumentReferenceMap
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingChecklistDocumentReferenceMap() throws Exception {
        // Initialize the database
        checklistDocumentReferenceMapRepository.save(checklistDocumentReferenceMap).block();

        int databaseSizeBeforeUpdate = checklistDocumentReferenceMapRepository.findAll().collectList().block().size();

        // Update the checklistDocumentReferenceMap
        ChecklistDocumentReferenceMap updatedChecklistDocumentReferenceMap = checklistDocumentReferenceMapRepository
            .findById(checklistDocumentReferenceMap.getChecklistDocumentReferenceId())
            .block();
        updatedChecklistDocumentReferenceMap
            .checklistId(UPDATED_CHECKLIST_ID)
            .checklistName(UPDATED_CHECKLIST_NAME)
            .documentReferenceId(UPDATED_DOCUMENT_REFERENCE_ID)
            .documentReferenceName(UPDATED_DOCUMENT_REFERENCE_NAME)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .checklistDocumentReferenceMapUuid(UPDATED_CHECKLIST_DOCUMENT_REFERENCE_MAP_UUID)
            .itemGroupId(UPDATED_ITEM_GROUP_ID)
            .itemGroupName(UPDATED_ITEM_GROUP_NAME);
        ChecklistDocumentReferenceMapDTO checklistDocumentReferenceMapDTO = checklistDocumentReferenceMapMapper.toDto(
            updatedChecklistDocumentReferenceMap
        );

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, checklistDocumentReferenceMapDTO.getChecklistDocumentReferenceId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(checklistDocumentReferenceMapDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ChecklistDocumentReferenceMap in the database
        List<ChecklistDocumentReferenceMap> checklistDocumentReferenceMapList = checklistDocumentReferenceMapRepository
            .findAll()
            .collectList()
            .block();
        assertThat(checklistDocumentReferenceMapList).hasSize(databaseSizeBeforeUpdate);
        ChecklistDocumentReferenceMap testChecklistDocumentReferenceMap = checklistDocumentReferenceMapList.get(
            checklistDocumentReferenceMapList.size() - 1
        );
        assertThat(testChecklistDocumentReferenceMap.getChecklistId()).isEqualTo(UPDATED_CHECKLIST_ID);
        assertThat(testChecklistDocumentReferenceMap.getChecklistName()).isEqualTo(UPDATED_CHECKLIST_NAME);
        assertThat(testChecklistDocumentReferenceMap.getDocumentReferenceId()).isEqualTo(UPDATED_DOCUMENT_REFERENCE_ID);
        assertThat(testChecklistDocumentReferenceMap.getDocumentReferenceName()).isEqualTo(UPDATED_DOCUMENT_REFERENCE_NAME);
        assertThat(testChecklistDocumentReferenceMap.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testChecklistDocumentReferenceMap.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testChecklistDocumentReferenceMap.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testChecklistDocumentReferenceMap.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testChecklistDocumentReferenceMap.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testChecklistDocumentReferenceMap.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testChecklistDocumentReferenceMap.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testChecklistDocumentReferenceMap.getChecklistDocumentReferenceMapUuid())
            .isEqualTo(UPDATED_CHECKLIST_DOCUMENT_REFERENCE_MAP_UUID);
        assertThat(testChecklistDocumentReferenceMap.getItemGroupId()).isEqualTo(UPDATED_ITEM_GROUP_ID);
        assertThat(testChecklistDocumentReferenceMap.getItemGroupName()).isEqualTo(UPDATED_ITEM_GROUP_NAME);
    }

    @Test
    void putNonExistingChecklistDocumentReferenceMap() throws Exception {
        int databaseSizeBeforeUpdate = checklistDocumentReferenceMapRepository.findAll().collectList().block().size();
        checklistDocumentReferenceMap.setChecklistDocumentReferenceId(count.incrementAndGet());

        // Create the ChecklistDocumentReferenceMap
        ChecklistDocumentReferenceMapDTO checklistDocumentReferenceMapDTO = checklistDocumentReferenceMapMapper.toDto(
            checklistDocumentReferenceMap
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, checklistDocumentReferenceMapDTO.getChecklistDocumentReferenceId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(checklistDocumentReferenceMapDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ChecklistDocumentReferenceMap in the database
        List<ChecklistDocumentReferenceMap> checklistDocumentReferenceMapList = checklistDocumentReferenceMapRepository
            .findAll()
            .collectList()
            .block();
        assertThat(checklistDocumentReferenceMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchChecklistDocumentReferenceMap() throws Exception {
        int databaseSizeBeforeUpdate = checklistDocumentReferenceMapRepository.findAll().collectList().block().size();
        checklistDocumentReferenceMap.setChecklistDocumentReferenceId(count.incrementAndGet());

        // Create the ChecklistDocumentReferenceMap
        ChecklistDocumentReferenceMapDTO checklistDocumentReferenceMapDTO = checklistDocumentReferenceMapMapper.toDto(
            checklistDocumentReferenceMap
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(checklistDocumentReferenceMapDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ChecklistDocumentReferenceMap in the database
        List<ChecklistDocumentReferenceMap> checklistDocumentReferenceMapList = checklistDocumentReferenceMapRepository
            .findAll()
            .collectList()
            .block();
        assertThat(checklistDocumentReferenceMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamChecklistDocumentReferenceMap() throws Exception {
        int databaseSizeBeforeUpdate = checklistDocumentReferenceMapRepository.findAll().collectList().block().size();
        checklistDocumentReferenceMap.setChecklistDocumentReferenceId(count.incrementAndGet());

        // Create the ChecklistDocumentReferenceMap
        ChecklistDocumentReferenceMapDTO checklistDocumentReferenceMapDTO = checklistDocumentReferenceMapMapper.toDto(
            checklistDocumentReferenceMap
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(checklistDocumentReferenceMapDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the ChecklistDocumentReferenceMap in the database
        List<ChecklistDocumentReferenceMap> checklistDocumentReferenceMapList = checklistDocumentReferenceMapRepository
            .findAll()
            .collectList()
            .block();
        assertThat(checklistDocumentReferenceMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateChecklistDocumentReferenceMapWithPatch() throws Exception {
        // Initialize the database
        checklistDocumentReferenceMapRepository.save(checklistDocumentReferenceMap).block();

        int databaseSizeBeforeUpdate = checklistDocumentReferenceMapRepository.findAll().collectList().block().size();

        // Update the checklistDocumentReferenceMap using partial update
        ChecklistDocumentReferenceMap partialUpdatedChecklistDocumentReferenceMap = new ChecklistDocumentReferenceMap();
        partialUpdatedChecklistDocumentReferenceMap.setChecklistDocumentReferenceId(
            checklistDocumentReferenceMap.getChecklistDocumentReferenceId()
        );

        partialUpdatedChecklistDocumentReferenceMap
            .checklistId(UPDATED_CHECKLIST_ID)
            .documentReferenceId(UPDATED_DOCUMENT_REFERENCE_ID)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .itemGroupId(UPDATED_ITEM_GROUP_ID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedChecklistDocumentReferenceMap.getChecklistDocumentReferenceId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedChecklistDocumentReferenceMap))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ChecklistDocumentReferenceMap in the database
        List<ChecklistDocumentReferenceMap> checklistDocumentReferenceMapList = checklistDocumentReferenceMapRepository
            .findAll()
            .collectList()
            .block();
        assertThat(checklistDocumentReferenceMapList).hasSize(databaseSizeBeforeUpdate);
        ChecklistDocumentReferenceMap testChecklistDocumentReferenceMap = checklistDocumentReferenceMapList.get(
            checklistDocumentReferenceMapList.size() - 1
        );
        assertThat(testChecklistDocumentReferenceMap.getChecklistId()).isEqualTo(UPDATED_CHECKLIST_ID);
        assertThat(testChecklistDocumentReferenceMap.getChecklistName()).isEqualTo(DEFAULT_CHECKLIST_NAME);
        assertThat(testChecklistDocumentReferenceMap.getDocumentReferenceId()).isEqualTo(UPDATED_DOCUMENT_REFERENCE_ID);
        assertThat(testChecklistDocumentReferenceMap.getDocumentReferenceName()).isEqualTo(DEFAULT_DOCUMENT_REFERENCE_NAME);
        assertThat(testChecklistDocumentReferenceMap.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testChecklistDocumentReferenceMap.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testChecklistDocumentReferenceMap.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testChecklistDocumentReferenceMap.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testChecklistDocumentReferenceMap.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testChecklistDocumentReferenceMap.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testChecklistDocumentReferenceMap.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testChecklistDocumentReferenceMap.getChecklistDocumentReferenceMapUuid())
            .isEqualTo(DEFAULT_CHECKLIST_DOCUMENT_REFERENCE_MAP_UUID);
        assertThat(testChecklistDocumentReferenceMap.getItemGroupId()).isEqualTo(UPDATED_ITEM_GROUP_ID);
        assertThat(testChecklistDocumentReferenceMap.getItemGroupName()).isEqualTo(DEFAULT_ITEM_GROUP_NAME);
    }

    @Test
    void fullUpdateChecklistDocumentReferenceMapWithPatch() throws Exception {
        // Initialize the database
        checklistDocumentReferenceMapRepository.save(checklistDocumentReferenceMap).block();

        int databaseSizeBeforeUpdate = checklistDocumentReferenceMapRepository.findAll().collectList().block().size();

        // Update the checklistDocumentReferenceMap using partial update
        ChecklistDocumentReferenceMap partialUpdatedChecklistDocumentReferenceMap = new ChecklistDocumentReferenceMap();
        partialUpdatedChecklistDocumentReferenceMap.setChecklistDocumentReferenceId(
            checklistDocumentReferenceMap.getChecklistDocumentReferenceId()
        );

        partialUpdatedChecklistDocumentReferenceMap
            .checklistId(UPDATED_CHECKLIST_ID)
            .checklistName(UPDATED_CHECKLIST_NAME)
            .documentReferenceId(UPDATED_DOCUMENT_REFERENCE_ID)
            .documentReferenceName(UPDATED_DOCUMENT_REFERENCE_NAME)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .checklistDocumentReferenceMapUuid(UPDATED_CHECKLIST_DOCUMENT_REFERENCE_MAP_UUID)
            .itemGroupId(UPDATED_ITEM_GROUP_ID)
            .itemGroupName(UPDATED_ITEM_GROUP_NAME);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedChecklistDocumentReferenceMap.getChecklistDocumentReferenceId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedChecklistDocumentReferenceMap))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ChecklistDocumentReferenceMap in the database
        List<ChecklistDocumentReferenceMap> checklistDocumentReferenceMapList = checklistDocumentReferenceMapRepository
            .findAll()
            .collectList()
            .block();
        assertThat(checklistDocumentReferenceMapList).hasSize(databaseSizeBeforeUpdate);
        ChecklistDocumentReferenceMap testChecklistDocumentReferenceMap = checklistDocumentReferenceMapList.get(
            checklistDocumentReferenceMapList.size() - 1
        );
        assertThat(testChecklistDocumentReferenceMap.getChecklistId()).isEqualTo(UPDATED_CHECKLIST_ID);
        assertThat(testChecklistDocumentReferenceMap.getChecklistName()).isEqualTo(UPDATED_CHECKLIST_NAME);
        assertThat(testChecklistDocumentReferenceMap.getDocumentReferenceId()).isEqualTo(UPDATED_DOCUMENT_REFERENCE_ID);
        assertThat(testChecklistDocumentReferenceMap.getDocumentReferenceName()).isEqualTo(UPDATED_DOCUMENT_REFERENCE_NAME);
        assertThat(testChecklistDocumentReferenceMap.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testChecklistDocumentReferenceMap.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testChecklistDocumentReferenceMap.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testChecklistDocumentReferenceMap.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testChecklistDocumentReferenceMap.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testChecklistDocumentReferenceMap.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testChecklistDocumentReferenceMap.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testChecklistDocumentReferenceMap.getChecklistDocumentReferenceMapUuid())
            .isEqualTo(UPDATED_CHECKLIST_DOCUMENT_REFERENCE_MAP_UUID);
        assertThat(testChecklistDocumentReferenceMap.getItemGroupId()).isEqualTo(UPDATED_ITEM_GROUP_ID);
        assertThat(testChecklistDocumentReferenceMap.getItemGroupName()).isEqualTo(UPDATED_ITEM_GROUP_NAME);
    }

    @Test
    void patchNonExistingChecklistDocumentReferenceMap() throws Exception {
        int databaseSizeBeforeUpdate = checklistDocumentReferenceMapRepository.findAll().collectList().block().size();
        checklistDocumentReferenceMap.setChecklistDocumentReferenceId(count.incrementAndGet());

        // Create the ChecklistDocumentReferenceMap
        ChecklistDocumentReferenceMapDTO checklistDocumentReferenceMapDTO = checklistDocumentReferenceMapMapper.toDto(
            checklistDocumentReferenceMap
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, checklistDocumentReferenceMapDTO.getChecklistDocumentReferenceId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(checklistDocumentReferenceMapDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ChecklistDocumentReferenceMap in the database
        List<ChecklistDocumentReferenceMap> checklistDocumentReferenceMapList = checklistDocumentReferenceMapRepository
            .findAll()
            .collectList()
            .block();
        assertThat(checklistDocumentReferenceMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchChecklistDocumentReferenceMap() throws Exception {
        int databaseSizeBeforeUpdate = checklistDocumentReferenceMapRepository.findAll().collectList().block().size();
        checklistDocumentReferenceMap.setChecklistDocumentReferenceId(count.incrementAndGet());

        // Create the ChecklistDocumentReferenceMap
        ChecklistDocumentReferenceMapDTO checklistDocumentReferenceMapDTO = checklistDocumentReferenceMapMapper.toDto(
            checklistDocumentReferenceMap
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(checklistDocumentReferenceMapDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ChecklistDocumentReferenceMap in the database
        List<ChecklistDocumentReferenceMap> checklistDocumentReferenceMapList = checklistDocumentReferenceMapRepository
            .findAll()
            .collectList()
            .block();
        assertThat(checklistDocumentReferenceMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamChecklistDocumentReferenceMap() throws Exception {
        int databaseSizeBeforeUpdate = checklistDocumentReferenceMapRepository.findAll().collectList().block().size();
        checklistDocumentReferenceMap.setChecklistDocumentReferenceId(count.incrementAndGet());

        // Create the ChecklistDocumentReferenceMap
        ChecklistDocumentReferenceMapDTO checklistDocumentReferenceMapDTO = checklistDocumentReferenceMapMapper.toDto(
            checklistDocumentReferenceMap
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(checklistDocumentReferenceMapDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the ChecklistDocumentReferenceMap in the database
        List<ChecklistDocumentReferenceMap> checklistDocumentReferenceMapList = checklistDocumentReferenceMapRepository
            .findAll()
            .collectList()
            .block();
        assertThat(checklistDocumentReferenceMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteChecklistDocumentReferenceMap() {
        // Initialize the database
        checklistDocumentReferenceMapRepository.save(checklistDocumentReferenceMap).block();

        int databaseSizeBeforeDelete = checklistDocumentReferenceMapRepository.findAll().collectList().block().size();

        // Delete the checklistDocumentReferenceMap
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, checklistDocumentReferenceMap.getChecklistDocumentReferenceId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<ChecklistDocumentReferenceMap> checklistDocumentReferenceMapList = checklistDocumentReferenceMapRepository
            .findAll()
            .collectList()
            .block();
        assertThat(checklistDocumentReferenceMapList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
