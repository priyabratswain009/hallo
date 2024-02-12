package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.DocumentReferenceFileMap;
import com.sunknowledge.dme.rcm.repository.DocumentReferenceFileMapRepository;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.service.dto.DocumentReferenceFileMapDTO;
import com.sunknowledge.dme.rcm.service.mapper.DocumentReferenceFileMapMapper;
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
 * Integration tests for the {@link DocumentReferenceFileMapResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class DocumentReferenceFileMapResourceIT {

    private static final Long DEFAULT_CHECKLIST_ID = 1L;
    private static final Long UPDATED_CHECKLIST_ID = 2L;

    private static final String DEFAULT_CHECKLIST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CHECKLIST_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_COVERAGE_CRITERIA_ID = 1L;
    private static final Long UPDATED_COVERAGE_CRITERIA_ID = 2L;

    private static final String DEFAULT_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FILE_NAME = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_DOCUMENT_REFERENCE_FILE_MAP_UUID = UUID.randomUUID();
    private static final UUID UPDATED_DOCUMENT_REFERENCE_FILE_MAP_UUID = UUID.randomUUID();

    private static final String DEFAULT_FILE_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_FILE_REFERENCE = "BBBBBBBBBB";

    private static final String DEFAULT_DOCUMENT_REFERENCE_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENT_REFERENCE_NOTES = "BBBBBBBBBB";

    private static final Long DEFAULT_SO_ID = 1L;
    private static final Long UPDATED_SO_ID = 2L;

    private static final Long DEFAULT_ITEM_GROUP_ID = 1L;
    private static final Long UPDATED_ITEM_GROUP_ID = 2L;

    private static final String DEFAULT_ITEM_GROUP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_GROUP_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/document-reference-file-maps";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{documentReferenceFileMapId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DocumentReferenceFileMapRepository documentReferenceFileMapRepository;

    @Autowired
    private DocumentReferenceFileMapMapper documentReferenceFileMapMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private DocumentReferenceFileMap documentReferenceFileMap;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DocumentReferenceFileMap createEntity(EntityManager em) {
        DocumentReferenceFileMap documentReferenceFileMap = new DocumentReferenceFileMap()
            .checklistId(DEFAULT_CHECKLIST_ID)
            .checklistName(DEFAULT_CHECKLIST_NAME)
            .coverageCriteriaId(DEFAULT_COVERAGE_CRITERIA_ID)
            .fileName(DEFAULT_FILE_NAME)
            .status(DEFAULT_STATUS)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .documentReferenceFileMapUuid(DEFAULT_DOCUMENT_REFERENCE_FILE_MAP_UUID)
            .fileReference(DEFAULT_FILE_REFERENCE)
            .documentReferenceNotes(DEFAULT_DOCUMENT_REFERENCE_NOTES)
            .soId(DEFAULT_SO_ID)
            .itemGroupId(DEFAULT_ITEM_GROUP_ID)
            .itemGroupName(DEFAULT_ITEM_GROUP_NAME);
        return documentReferenceFileMap;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DocumentReferenceFileMap createUpdatedEntity(EntityManager em) {
        DocumentReferenceFileMap documentReferenceFileMap = new DocumentReferenceFileMap()
            .checklistId(UPDATED_CHECKLIST_ID)
            .checklistName(UPDATED_CHECKLIST_NAME)
            .coverageCriteriaId(UPDATED_COVERAGE_CRITERIA_ID)
            .fileName(UPDATED_FILE_NAME)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .documentReferenceFileMapUuid(UPDATED_DOCUMENT_REFERENCE_FILE_MAP_UUID)
            .fileReference(UPDATED_FILE_REFERENCE)
            .documentReferenceNotes(UPDATED_DOCUMENT_REFERENCE_NOTES)
            .soId(UPDATED_SO_ID)
            .itemGroupId(UPDATED_ITEM_GROUP_ID)
            .itemGroupName(UPDATED_ITEM_GROUP_NAME);
        return documentReferenceFileMap;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(DocumentReferenceFileMap.class).block();
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
        documentReferenceFileMap = createEntity(em);
    }

    @Test
    void createDocumentReferenceFileMap() throws Exception {
        int databaseSizeBeforeCreate = documentReferenceFileMapRepository.findAll().collectList().block().size();
        // Create the DocumentReferenceFileMap
        DocumentReferenceFileMapDTO documentReferenceFileMapDTO = documentReferenceFileMapMapper.toDto(documentReferenceFileMap);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(documentReferenceFileMapDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the DocumentReferenceFileMap in the database
        List<DocumentReferenceFileMap> documentReferenceFileMapList = documentReferenceFileMapRepository.findAll().collectList().block();
        assertThat(documentReferenceFileMapList).hasSize(databaseSizeBeforeCreate + 1);
        DocumentReferenceFileMap testDocumentReferenceFileMap = documentReferenceFileMapList.get(documentReferenceFileMapList.size() - 1);
        assertThat(testDocumentReferenceFileMap.getChecklistId()).isEqualTo(DEFAULT_CHECKLIST_ID);
        assertThat(testDocumentReferenceFileMap.getChecklistName()).isEqualTo(DEFAULT_CHECKLIST_NAME);
        assertThat(testDocumentReferenceFileMap.getCoverageCriteriaId()).isEqualTo(DEFAULT_COVERAGE_CRITERIA_ID);
        assertThat(testDocumentReferenceFileMap.getFileName()).isEqualTo(DEFAULT_FILE_NAME);
        assertThat(testDocumentReferenceFileMap.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testDocumentReferenceFileMap.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testDocumentReferenceFileMap.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testDocumentReferenceFileMap.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testDocumentReferenceFileMap.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testDocumentReferenceFileMap.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testDocumentReferenceFileMap.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testDocumentReferenceFileMap.getDocumentReferenceFileMapUuid()).isEqualTo(DEFAULT_DOCUMENT_REFERENCE_FILE_MAP_UUID);
        assertThat(testDocumentReferenceFileMap.getFileReference()).isEqualTo(DEFAULT_FILE_REFERENCE);
        assertThat(testDocumentReferenceFileMap.getDocumentReferenceNotes()).isEqualTo(DEFAULT_DOCUMENT_REFERENCE_NOTES);
        assertThat(testDocumentReferenceFileMap.getSoId()).isEqualTo(DEFAULT_SO_ID);
        assertThat(testDocumentReferenceFileMap.getItemGroupId()).isEqualTo(DEFAULT_ITEM_GROUP_ID);
        assertThat(testDocumentReferenceFileMap.getItemGroupName()).isEqualTo(DEFAULT_ITEM_GROUP_NAME);
    }

    @Test
    void createDocumentReferenceFileMapWithExistingId() throws Exception {
        // Create the DocumentReferenceFileMap with an existing ID
        documentReferenceFileMap.setDocumentReferenceFileMapId(1L);
        DocumentReferenceFileMapDTO documentReferenceFileMapDTO = documentReferenceFileMapMapper.toDto(documentReferenceFileMap);

        int databaseSizeBeforeCreate = documentReferenceFileMapRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(documentReferenceFileMapDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DocumentReferenceFileMap in the database
        List<DocumentReferenceFileMap> documentReferenceFileMapList = documentReferenceFileMapRepository.findAll().collectList().block();
        assertThat(documentReferenceFileMapList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllDocumentReferenceFileMaps() {
        // Initialize the database
        documentReferenceFileMapRepository.save(documentReferenceFileMap).block();

        // Get all the documentReferenceFileMapList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=documentReferenceFileMapId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].documentReferenceFileMapId")
            .value(hasItem(documentReferenceFileMap.getDocumentReferenceFileMapId().intValue()))
            .jsonPath("$.[*].checklistId")
            .value(hasItem(DEFAULT_CHECKLIST_ID.intValue()))
            .jsonPath("$.[*].checklistName")
            .value(hasItem(DEFAULT_CHECKLIST_NAME))
            .jsonPath("$.[*].coverageCriteriaId")
            .value(hasItem(DEFAULT_COVERAGE_CRITERIA_ID.intValue()))
            .jsonPath("$.[*].fileName")
            .value(hasItem(DEFAULT_FILE_NAME))
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
            .jsonPath("$.[*].documentReferenceFileMapUuid")
            .value(hasItem(DEFAULT_DOCUMENT_REFERENCE_FILE_MAP_UUID.toString()))
            .jsonPath("$.[*].fileReference")
            .value(hasItem(DEFAULT_FILE_REFERENCE))
            .jsonPath("$.[*].documentReferenceNotes")
            .value(hasItem(DEFAULT_DOCUMENT_REFERENCE_NOTES))
            .jsonPath("$.[*].soId")
            .value(hasItem(DEFAULT_SO_ID.intValue()))
            .jsonPath("$.[*].itemGroupId")
            .value(hasItem(DEFAULT_ITEM_GROUP_ID.intValue()))
            .jsonPath("$.[*].itemGroupName")
            .value(hasItem(DEFAULT_ITEM_GROUP_NAME));
    }

    @Test
    void getDocumentReferenceFileMap() {
        // Initialize the database
        documentReferenceFileMapRepository.save(documentReferenceFileMap).block();

        // Get the documentReferenceFileMap
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, documentReferenceFileMap.getDocumentReferenceFileMapId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.documentReferenceFileMapId")
            .value(is(documentReferenceFileMap.getDocumentReferenceFileMapId().intValue()))
            .jsonPath("$.checklistId")
            .value(is(DEFAULT_CHECKLIST_ID.intValue()))
            .jsonPath("$.checklistName")
            .value(is(DEFAULT_CHECKLIST_NAME))
            .jsonPath("$.coverageCriteriaId")
            .value(is(DEFAULT_COVERAGE_CRITERIA_ID.intValue()))
            .jsonPath("$.fileName")
            .value(is(DEFAULT_FILE_NAME))
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
            .jsonPath("$.documentReferenceFileMapUuid")
            .value(is(DEFAULT_DOCUMENT_REFERENCE_FILE_MAP_UUID.toString()))
            .jsonPath("$.fileReference")
            .value(is(DEFAULT_FILE_REFERENCE))
            .jsonPath("$.documentReferenceNotes")
            .value(is(DEFAULT_DOCUMENT_REFERENCE_NOTES))
            .jsonPath("$.soId")
            .value(is(DEFAULT_SO_ID.intValue()))
            .jsonPath("$.itemGroupId")
            .value(is(DEFAULT_ITEM_GROUP_ID.intValue()))
            .jsonPath("$.itemGroupName")
            .value(is(DEFAULT_ITEM_GROUP_NAME));
    }

    @Test
    void getNonExistingDocumentReferenceFileMap() {
        // Get the documentReferenceFileMap
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingDocumentReferenceFileMap() throws Exception {
        // Initialize the database
        documentReferenceFileMapRepository.save(documentReferenceFileMap).block();

        int databaseSizeBeforeUpdate = documentReferenceFileMapRepository.findAll().collectList().block().size();

        // Update the documentReferenceFileMap
        DocumentReferenceFileMap updatedDocumentReferenceFileMap = documentReferenceFileMapRepository
            .findById(documentReferenceFileMap.getDocumentReferenceFileMapId())
            .block();
        updatedDocumentReferenceFileMap
            .checklistId(UPDATED_CHECKLIST_ID)
            .checklistName(UPDATED_CHECKLIST_NAME)
            .coverageCriteriaId(UPDATED_COVERAGE_CRITERIA_ID)
            .fileName(UPDATED_FILE_NAME)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .documentReferenceFileMapUuid(UPDATED_DOCUMENT_REFERENCE_FILE_MAP_UUID)
            .fileReference(UPDATED_FILE_REFERENCE)
            .documentReferenceNotes(UPDATED_DOCUMENT_REFERENCE_NOTES)
            .soId(UPDATED_SO_ID)
            .itemGroupId(UPDATED_ITEM_GROUP_ID)
            .itemGroupName(UPDATED_ITEM_GROUP_NAME);
        DocumentReferenceFileMapDTO documentReferenceFileMapDTO = documentReferenceFileMapMapper.toDto(updatedDocumentReferenceFileMap);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, documentReferenceFileMapDTO.getDocumentReferenceFileMapId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(documentReferenceFileMapDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the DocumentReferenceFileMap in the database
        List<DocumentReferenceFileMap> documentReferenceFileMapList = documentReferenceFileMapRepository.findAll().collectList().block();
        assertThat(documentReferenceFileMapList).hasSize(databaseSizeBeforeUpdate);
        DocumentReferenceFileMap testDocumentReferenceFileMap = documentReferenceFileMapList.get(documentReferenceFileMapList.size() - 1);
        assertThat(testDocumentReferenceFileMap.getChecklistId()).isEqualTo(UPDATED_CHECKLIST_ID);
        assertThat(testDocumentReferenceFileMap.getChecklistName()).isEqualTo(UPDATED_CHECKLIST_NAME);
        assertThat(testDocumentReferenceFileMap.getCoverageCriteriaId()).isEqualTo(UPDATED_COVERAGE_CRITERIA_ID);
        assertThat(testDocumentReferenceFileMap.getFileName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testDocumentReferenceFileMap.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDocumentReferenceFileMap.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testDocumentReferenceFileMap.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testDocumentReferenceFileMap.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testDocumentReferenceFileMap.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testDocumentReferenceFileMap.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testDocumentReferenceFileMap.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testDocumentReferenceFileMap.getDocumentReferenceFileMapUuid()).isEqualTo(UPDATED_DOCUMENT_REFERENCE_FILE_MAP_UUID);
        assertThat(testDocumentReferenceFileMap.getFileReference()).isEqualTo(UPDATED_FILE_REFERENCE);
        assertThat(testDocumentReferenceFileMap.getDocumentReferenceNotes()).isEqualTo(UPDATED_DOCUMENT_REFERENCE_NOTES);
        assertThat(testDocumentReferenceFileMap.getSoId()).isEqualTo(UPDATED_SO_ID);
        assertThat(testDocumentReferenceFileMap.getItemGroupId()).isEqualTo(UPDATED_ITEM_GROUP_ID);
        assertThat(testDocumentReferenceFileMap.getItemGroupName()).isEqualTo(UPDATED_ITEM_GROUP_NAME);
    }

    @Test
    void putNonExistingDocumentReferenceFileMap() throws Exception {
        int databaseSizeBeforeUpdate = documentReferenceFileMapRepository.findAll().collectList().block().size();
        documentReferenceFileMap.setDocumentReferenceFileMapId(count.incrementAndGet());

        // Create the DocumentReferenceFileMap
        DocumentReferenceFileMapDTO documentReferenceFileMapDTO = documentReferenceFileMapMapper.toDto(documentReferenceFileMap);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, documentReferenceFileMapDTO.getDocumentReferenceFileMapId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(documentReferenceFileMapDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DocumentReferenceFileMap in the database
        List<DocumentReferenceFileMap> documentReferenceFileMapList = documentReferenceFileMapRepository.findAll().collectList().block();
        assertThat(documentReferenceFileMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchDocumentReferenceFileMap() throws Exception {
        int databaseSizeBeforeUpdate = documentReferenceFileMapRepository.findAll().collectList().block().size();
        documentReferenceFileMap.setDocumentReferenceFileMapId(count.incrementAndGet());

        // Create the DocumentReferenceFileMap
        DocumentReferenceFileMapDTO documentReferenceFileMapDTO = documentReferenceFileMapMapper.toDto(documentReferenceFileMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(documentReferenceFileMapDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DocumentReferenceFileMap in the database
        List<DocumentReferenceFileMap> documentReferenceFileMapList = documentReferenceFileMapRepository.findAll().collectList().block();
        assertThat(documentReferenceFileMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamDocumentReferenceFileMap() throws Exception {
        int databaseSizeBeforeUpdate = documentReferenceFileMapRepository.findAll().collectList().block().size();
        documentReferenceFileMap.setDocumentReferenceFileMapId(count.incrementAndGet());

        // Create the DocumentReferenceFileMap
        DocumentReferenceFileMapDTO documentReferenceFileMapDTO = documentReferenceFileMapMapper.toDto(documentReferenceFileMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(documentReferenceFileMapDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the DocumentReferenceFileMap in the database
        List<DocumentReferenceFileMap> documentReferenceFileMapList = documentReferenceFileMapRepository.findAll().collectList().block();
        assertThat(documentReferenceFileMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateDocumentReferenceFileMapWithPatch() throws Exception {
        // Initialize the database
        documentReferenceFileMapRepository.save(documentReferenceFileMap).block();

        int databaseSizeBeforeUpdate = documentReferenceFileMapRepository.findAll().collectList().block().size();

        // Update the documentReferenceFileMap using partial update
        DocumentReferenceFileMap partialUpdatedDocumentReferenceFileMap = new DocumentReferenceFileMap();
        partialUpdatedDocumentReferenceFileMap.setDocumentReferenceFileMapId(documentReferenceFileMap.getDocumentReferenceFileMapId());

        partialUpdatedDocumentReferenceFileMap
            .checklistId(UPDATED_CHECKLIST_ID)
            .checklistName(UPDATED_CHECKLIST_NAME)
            .fileName(UPDATED_FILE_NAME)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .documentReferenceFileMapUuid(UPDATED_DOCUMENT_REFERENCE_FILE_MAP_UUID)
            .fileReference(UPDATED_FILE_REFERENCE)
            .soId(UPDATED_SO_ID)
            .itemGroupId(UPDATED_ITEM_GROUP_ID)
            .itemGroupName(UPDATED_ITEM_GROUP_NAME);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedDocumentReferenceFileMap.getDocumentReferenceFileMapId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedDocumentReferenceFileMap))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the DocumentReferenceFileMap in the database
        List<DocumentReferenceFileMap> documentReferenceFileMapList = documentReferenceFileMapRepository.findAll().collectList().block();
        assertThat(documentReferenceFileMapList).hasSize(databaseSizeBeforeUpdate);
        DocumentReferenceFileMap testDocumentReferenceFileMap = documentReferenceFileMapList.get(documentReferenceFileMapList.size() - 1);
        assertThat(testDocumentReferenceFileMap.getChecklistId()).isEqualTo(UPDATED_CHECKLIST_ID);
        assertThat(testDocumentReferenceFileMap.getChecklistName()).isEqualTo(UPDATED_CHECKLIST_NAME);
        assertThat(testDocumentReferenceFileMap.getCoverageCriteriaId()).isEqualTo(DEFAULT_COVERAGE_CRITERIA_ID);
        assertThat(testDocumentReferenceFileMap.getFileName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testDocumentReferenceFileMap.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDocumentReferenceFileMap.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testDocumentReferenceFileMap.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testDocumentReferenceFileMap.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testDocumentReferenceFileMap.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testDocumentReferenceFileMap.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testDocumentReferenceFileMap.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testDocumentReferenceFileMap.getDocumentReferenceFileMapUuid()).isEqualTo(UPDATED_DOCUMENT_REFERENCE_FILE_MAP_UUID);
        assertThat(testDocumentReferenceFileMap.getFileReference()).isEqualTo(UPDATED_FILE_REFERENCE);
        assertThat(testDocumentReferenceFileMap.getDocumentReferenceNotes()).isEqualTo(DEFAULT_DOCUMENT_REFERENCE_NOTES);
        assertThat(testDocumentReferenceFileMap.getSoId()).isEqualTo(UPDATED_SO_ID);
        assertThat(testDocumentReferenceFileMap.getItemGroupId()).isEqualTo(UPDATED_ITEM_GROUP_ID);
        assertThat(testDocumentReferenceFileMap.getItemGroupName()).isEqualTo(UPDATED_ITEM_GROUP_NAME);
    }

    @Test
    void fullUpdateDocumentReferenceFileMapWithPatch() throws Exception {
        // Initialize the database
        documentReferenceFileMapRepository.save(documentReferenceFileMap).block();

        int databaseSizeBeforeUpdate = documentReferenceFileMapRepository.findAll().collectList().block().size();

        // Update the documentReferenceFileMap using partial update
        DocumentReferenceFileMap partialUpdatedDocumentReferenceFileMap = new DocumentReferenceFileMap();
        partialUpdatedDocumentReferenceFileMap.setDocumentReferenceFileMapId(documentReferenceFileMap.getDocumentReferenceFileMapId());

        partialUpdatedDocumentReferenceFileMap
            .checklistId(UPDATED_CHECKLIST_ID)
            .checklistName(UPDATED_CHECKLIST_NAME)
            .coverageCriteriaId(UPDATED_COVERAGE_CRITERIA_ID)
            .fileName(UPDATED_FILE_NAME)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .documentReferenceFileMapUuid(UPDATED_DOCUMENT_REFERENCE_FILE_MAP_UUID)
            .fileReference(UPDATED_FILE_REFERENCE)
            .documentReferenceNotes(UPDATED_DOCUMENT_REFERENCE_NOTES)
            .soId(UPDATED_SO_ID)
            .itemGroupId(UPDATED_ITEM_GROUP_ID)
            .itemGroupName(UPDATED_ITEM_GROUP_NAME);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedDocumentReferenceFileMap.getDocumentReferenceFileMapId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedDocumentReferenceFileMap))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the DocumentReferenceFileMap in the database
        List<DocumentReferenceFileMap> documentReferenceFileMapList = documentReferenceFileMapRepository.findAll().collectList().block();
        assertThat(documentReferenceFileMapList).hasSize(databaseSizeBeforeUpdate);
        DocumentReferenceFileMap testDocumentReferenceFileMap = documentReferenceFileMapList.get(documentReferenceFileMapList.size() - 1);
        assertThat(testDocumentReferenceFileMap.getChecklistId()).isEqualTo(UPDATED_CHECKLIST_ID);
        assertThat(testDocumentReferenceFileMap.getChecklistName()).isEqualTo(UPDATED_CHECKLIST_NAME);
        assertThat(testDocumentReferenceFileMap.getCoverageCriteriaId()).isEqualTo(UPDATED_COVERAGE_CRITERIA_ID);
        assertThat(testDocumentReferenceFileMap.getFileName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testDocumentReferenceFileMap.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDocumentReferenceFileMap.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testDocumentReferenceFileMap.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testDocumentReferenceFileMap.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testDocumentReferenceFileMap.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testDocumentReferenceFileMap.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testDocumentReferenceFileMap.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testDocumentReferenceFileMap.getDocumentReferenceFileMapUuid()).isEqualTo(UPDATED_DOCUMENT_REFERENCE_FILE_MAP_UUID);
        assertThat(testDocumentReferenceFileMap.getFileReference()).isEqualTo(UPDATED_FILE_REFERENCE);
        assertThat(testDocumentReferenceFileMap.getDocumentReferenceNotes()).isEqualTo(UPDATED_DOCUMENT_REFERENCE_NOTES);
        assertThat(testDocumentReferenceFileMap.getSoId()).isEqualTo(UPDATED_SO_ID);
        assertThat(testDocumentReferenceFileMap.getItemGroupId()).isEqualTo(UPDATED_ITEM_GROUP_ID);
        assertThat(testDocumentReferenceFileMap.getItemGroupName()).isEqualTo(UPDATED_ITEM_GROUP_NAME);
    }

    @Test
    void patchNonExistingDocumentReferenceFileMap() throws Exception {
        int databaseSizeBeforeUpdate = documentReferenceFileMapRepository.findAll().collectList().block().size();
        documentReferenceFileMap.setDocumentReferenceFileMapId(count.incrementAndGet());

        // Create the DocumentReferenceFileMap
        DocumentReferenceFileMapDTO documentReferenceFileMapDTO = documentReferenceFileMapMapper.toDto(documentReferenceFileMap);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, documentReferenceFileMapDTO.getDocumentReferenceFileMapId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(documentReferenceFileMapDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DocumentReferenceFileMap in the database
        List<DocumentReferenceFileMap> documentReferenceFileMapList = documentReferenceFileMapRepository.findAll().collectList().block();
        assertThat(documentReferenceFileMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchDocumentReferenceFileMap() throws Exception {
        int databaseSizeBeforeUpdate = documentReferenceFileMapRepository.findAll().collectList().block().size();
        documentReferenceFileMap.setDocumentReferenceFileMapId(count.incrementAndGet());

        // Create the DocumentReferenceFileMap
        DocumentReferenceFileMapDTO documentReferenceFileMapDTO = documentReferenceFileMapMapper.toDto(documentReferenceFileMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(documentReferenceFileMapDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DocumentReferenceFileMap in the database
        List<DocumentReferenceFileMap> documentReferenceFileMapList = documentReferenceFileMapRepository.findAll().collectList().block();
        assertThat(documentReferenceFileMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamDocumentReferenceFileMap() throws Exception {
        int databaseSizeBeforeUpdate = documentReferenceFileMapRepository.findAll().collectList().block().size();
        documentReferenceFileMap.setDocumentReferenceFileMapId(count.incrementAndGet());

        // Create the DocumentReferenceFileMap
        DocumentReferenceFileMapDTO documentReferenceFileMapDTO = documentReferenceFileMapMapper.toDto(documentReferenceFileMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(documentReferenceFileMapDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the DocumentReferenceFileMap in the database
        List<DocumentReferenceFileMap> documentReferenceFileMapList = documentReferenceFileMapRepository.findAll().collectList().block();
        assertThat(documentReferenceFileMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteDocumentReferenceFileMap() {
        // Initialize the database
        documentReferenceFileMapRepository.save(documentReferenceFileMap).block();

        int databaseSizeBeforeDelete = documentReferenceFileMapRepository.findAll().collectList().block().size();

        // Delete the documentReferenceFileMap
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, documentReferenceFileMap.getDocumentReferenceFileMapId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<DocumentReferenceFileMap> documentReferenceFileMapList = documentReferenceFileMapRepository.findAll().collectList().block();
        assertThat(documentReferenceFileMapList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
