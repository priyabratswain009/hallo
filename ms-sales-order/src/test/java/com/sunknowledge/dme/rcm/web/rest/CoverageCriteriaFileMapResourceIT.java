package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.CoverageCriteriaFileMap;
import com.sunknowledge.dme.rcm.repository.CoverageCriteriaFileMapRepository;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.service.dto.CoverageCriteriaFileMapDTO;
import com.sunknowledge.dme.rcm.service.mapper.CoverageCriteriaFileMapMapper;
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
 * Integration tests for the {@link CoverageCriteriaFileMapResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class CoverageCriteriaFileMapResourceIT {

    private static final Long DEFAULT_CHECKLIST_ID = 1L;
    private static final Long UPDATED_CHECKLIST_ID = 2L;

    private static final String DEFAULT_CHECKLIST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CHECKLIST_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_DOCUMENT_REFERENCE_ID = 1L;
    private static final Long UPDATED_DOCUMENT_REFERENCE_ID = 2L;

    private static final String DEFAULT_DOCUMENT_REFERENCE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENT_REFERENCE_NAME = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_COVERAGE_CRITERIA_FILE_MAP_UUID = UUID.randomUUID();
    private static final UUID UPDATED_COVERAGE_CRITERIA_FILE_MAP_UUID = UUID.randomUUID();

    private static final String DEFAULT_FILE_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_FILE_REFERENCE = "BBBBBBBBBB";

    private static final String DEFAULT_COVERAGE_CRITERIA_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_COVERAGE_CRITERIA_NOTES = "BBBBBBBBBB";

    private static final Long DEFAULT_SO_ID = 1L;
    private static final Long UPDATED_SO_ID = 2L;

    private static final Long DEFAULT_ITEM_GROUP_ID = 1L;
    private static final Long UPDATED_ITEM_GROUP_ID = 2L;

    private static final String DEFAULT_ITEM_GROUP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_GROUP_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_COVERAGE_CRITERIA_ID = 1L;
    private static final Long UPDATED_COVERAGE_CRITERIA_ID = 2L;

    private static final String ENTITY_API_URL = "/api/coverage-criteria-file-maps";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{coverageCriteriaFileMapId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CoverageCriteriaFileMapRepository coverageCriteriaFileMapRepository;

    @Autowired
    private CoverageCriteriaFileMapMapper coverageCriteriaFileMapMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private CoverageCriteriaFileMap coverageCriteriaFileMap;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CoverageCriteriaFileMap createEntity(EntityManager em) {
        CoverageCriteriaFileMap coverageCriteriaFileMap = new CoverageCriteriaFileMap()
            .checklistId(DEFAULT_CHECKLIST_ID)
            .checklistName(DEFAULT_CHECKLIST_NAME)
            .documentReferenceId(DEFAULT_DOCUMENT_REFERENCE_ID)
            .documentReferenceName(DEFAULT_DOCUMENT_REFERENCE_NAME)
            .fileName(DEFAULT_FILE_NAME)
            .status(DEFAULT_STATUS)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .coverageCriteriaFileMapUuid(DEFAULT_COVERAGE_CRITERIA_FILE_MAP_UUID)
            .fileReference(DEFAULT_FILE_REFERENCE)
            .coverageCriteriaNotes(DEFAULT_COVERAGE_CRITERIA_NOTES)
            .soId(DEFAULT_SO_ID)
            .itemGroupId(DEFAULT_ITEM_GROUP_ID)
            .itemGroupName(DEFAULT_ITEM_GROUP_NAME)
            .coverageCriteriaId(DEFAULT_COVERAGE_CRITERIA_ID);
        return coverageCriteriaFileMap;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CoverageCriteriaFileMap createUpdatedEntity(EntityManager em) {
        CoverageCriteriaFileMap coverageCriteriaFileMap = new CoverageCriteriaFileMap()
            .checklistId(UPDATED_CHECKLIST_ID)
            .checklistName(UPDATED_CHECKLIST_NAME)
            .documentReferenceId(UPDATED_DOCUMENT_REFERENCE_ID)
            .documentReferenceName(UPDATED_DOCUMENT_REFERENCE_NAME)
            .fileName(UPDATED_FILE_NAME)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .coverageCriteriaFileMapUuid(UPDATED_COVERAGE_CRITERIA_FILE_MAP_UUID)
            .fileReference(UPDATED_FILE_REFERENCE)
            .coverageCriteriaNotes(UPDATED_COVERAGE_CRITERIA_NOTES)
            .soId(UPDATED_SO_ID)
            .itemGroupId(UPDATED_ITEM_GROUP_ID)
            .itemGroupName(UPDATED_ITEM_GROUP_NAME)
            .coverageCriteriaId(UPDATED_COVERAGE_CRITERIA_ID);
        return coverageCriteriaFileMap;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(CoverageCriteriaFileMap.class).block();
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
        coverageCriteriaFileMap = createEntity(em);
    }

    @Test
    void createCoverageCriteriaFileMap() throws Exception {
        int databaseSizeBeforeCreate = coverageCriteriaFileMapRepository.findAll().collectList().block().size();
        // Create the CoverageCriteriaFileMap
        CoverageCriteriaFileMapDTO coverageCriteriaFileMapDTO = coverageCriteriaFileMapMapper.toDto(coverageCriteriaFileMap);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(coverageCriteriaFileMapDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the CoverageCriteriaFileMap in the database
        List<CoverageCriteriaFileMap> coverageCriteriaFileMapList = coverageCriteriaFileMapRepository.findAll().collectList().block();
        assertThat(coverageCriteriaFileMapList).hasSize(databaseSizeBeforeCreate + 1);
        CoverageCriteriaFileMap testCoverageCriteriaFileMap = coverageCriteriaFileMapList.get(coverageCriteriaFileMapList.size() - 1);
        assertThat(testCoverageCriteriaFileMap.getChecklistId()).isEqualTo(DEFAULT_CHECKLIST_ID);
        assertThat(testCoverageCriteriaFileMap.getChecklistName()).isEqualTo(DEFAULT_CHECKLIST_NAME);
        assertThat(testCoverageCriteriaFileMap.getDocumentReferenceId()).isEqualTo(DEFAULT_DOCUMENT_REFERENCE_ID);
        assertThat(testCoverageCriteriaFileMap.getDocumentReferenceName()).isEqualTo(DEFAULT_DOCUMENT_REFERENCE_NAME);
        assertThat(testCoverageCriteriaFileMap.getFileName()).isEqualTo(DEFAULT_FILE_NAME);
        assertThat(testCoverageCriteriaFileMap.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testCoverageCriteriaFileMap.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testCoverageCriteriaFileMap.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testCoverageCriteriaFileMap.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testCoverageCriteriaFileMap.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testCoverageCriteriaFileMap.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testCoverageCriteriaFileMap.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testCoverageCriteriaFileMap.getCoverageCriteriaFileMapUuid()).isEqualTo(DEFAULT_COVERAGE_CRITERIA_FILE_MAP_UUID);
        assertThat(testCoverageCriteriaFileMap.getFileReference()).isEqualTo(DEFAULT_FILE_REFERENCE);
        assertThat(testCoverageCriteriaFileMap.getCoverageCriteriaNotes()).isEqualTo(DEFAULT_COVERAGE_CRITERIA_NOTES);
        assertThat(testCoverageCriteriaFileMap.getSoId()).isEqualTo(DEFAULT_SO_ID);
        assertThat(testCoverageCriteriaFileMap.getItemGroupId()).isEqualTo(DEFAULT_ITEM_GROUP_ID);
        assertThat(testCoverageCriteriaFileMap.getItemGroupName()).isEqualTo(DEFAULT_ITEM_GROUP_NAME);
        assertThat(testCoverageCriteriaFileMap.getCoverageCriteriaId()).isEqualTo(DEFAULT_COVERAGE_CRITERIA_ID);
    }

    @Test
    void createCoverageCriteriaFileMapWithExistingId() throws Exception {
        // Create the CoverageCriteriaFileMap with an existing ID
        coverageCriteriaFileMap.setCoverageCriteriaFileMapId(1L);
        CoverageCriteriaFileMapDTO coverageCriteriaFileMapDTO = coverageCriteriaFileMapMapper.toDto(coverageCriteriaFileMap);

        int databaseSizeBeforeCreate = coverageCriteriaFileMapRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(coverageCriteriaFileMapDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CoverageCriteriaFileMap in the database
        List<CoverageCriteriaFileMap> coverageCriteriaFileMapList = coverageCriteriaFileMapRepository.findAll().collectList().block();
        assertThat(coverageCriteriaFileMapList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllCoverageCriteriaFileMaps() {
        // Initialize the database
        coverageCriteriaFileMapRepository.save(coverageCriteriaFileMap).block();

        // Get all the coverageCriteriaFileMapList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=coverageCriteriaFileMapId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].coverageCriteriaFileMapId")
            .value(hasItem(coverageCriteriaFileMap.getCoverageCriteriaFileMapId().intValue()))
            .jsonPath("$.[*].checklistId")
            .value(hasItem(DEFAULT_CHECKLIST_ID.intValue()))
            .jsonPath("$.[*].checklistName")
            .value(hasItem(DEFAULT_CHECKLIST_NAME))
            .jsonPath("$.[*].documentReferenceId")
            .value(hasItem(DEFAULT_DOCUMENT_REFERENCE_ID.intValue()))
            .jsonPath("$.[*].documentReferenceName")
            .value(hasItem(DEFAULT_DOCUMENT_REFERENCE_NAME))
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
            .jsonPath("$.[*].coverageCriteriaFileMapUuid")
            .value(hasItem(DEFAULT_COVERAGE_CRITERIA_FILE_MAP_UUID.toString()))
            .jsonPath("$.[*].fileReference")
            .value(hasItem(DEFAULT_FILE_REFERENCE))
            .jsonPath("$.[*].coverageCriteriaNotes")
            .value(hasItem(DEFAULT_COVERAGE_CRITERIA_NOTES))
            .jsonPath("$.[*].soId")
            .value(hasItem(DEFAULT_SO_ID.intValue()))
            .jsonPath("$.[*].itemGroupId")
            .value(hasItem(DEFAULT_ITEM_GROUP_ID.intValue()))
            .jsonPath("$.[*].itemGroupName")
            .value(hasItem(DEFAULT_ITEM_GROUP_NAME))
            .jsonPath("$.[*].coverageCriteriaId")
            .value(hasItem(DEFAULT_COVERAGE_CRITERIA_ID.intValue()));
    }

    @Test
    void getCoverageCriteriaFileMap() {
        // Initialize the database
        coverageCriteriaFileMapRepository.save(coverageCriteriaFileMap).block();

        // Get the coverageCriteriaFileMap
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, coverageCriteriaFileMap.getCoverageCriteriaFileMapId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.coverageCriteriaFileMapId")
            .value(is(coverageCriteriaFileMap.getCoverageCriteriaFileMapId().intValue()))
            .jsonPath("$.checklistId")
            .value(is(DEFAULT_CHECKLIST_ID.intValue()))
            .jsonPath("$.checklistName")
            .value(is(DEFAULT_CHECKLIST_NAME))
            .jsonPath("$.documentReferenceId")
            .value(is(DEFAULT_DOCUMENT_REFERENCE_ID.intValue()))
            .jsonPath("$.documentReferenceName")
            .value(is(DEFAULT_DOCUMENT_REFERENCE_NAME))
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
            .jsonPath("$.coverageCriteriaFileMapUuid")
            .value(is(DEFAULT_COVERAGE_CRITERIA_FILE_MAP_UUID.toString()))
            .jsonPath("$.fileReference")
            .value(is(DEFAULT_FILE_REFERENCE))
            .jsonPath("$.coverageCriteriaNotes")
            .value(is(DEFAULT_COVERAGE_CRITERIA_NOTES))
            .jsonPath("$.soId")
            .value(is(DEFAULT_SO_ID.intValue()))
            .jsonPath("$.itemGroupId")
            .value(is(DEFAULT_ITEM_GROUP_ID.intValue()))
            .jsonPath("$.itemGroupName")
            .value(is(DEFAULT_ITEM_GROUP_NAME))
            .jsonPath("$.coverageCriteriaId")
            .value(is(DEFAULT_COVERAGE_CRITERIA_ID.intValue()));
    }

    @Test
    void getNonExistingCoverageCriteriaFileMap() {
        // Get the coverageCriteriaFileMap
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingCoverageCriteriaFileMap() throws Exception {
        // Initialize the database
        coverageCriteriaFileMapRepository.save(coverageCriteriaFileMap).block();

        int databaseSizeBeforeUpdate = coverageCriteriaFileMapRepository.findAll().collectList().block().size();

        // Update the coverageCriteriaFileMap
        CoverageCriteriaFileMap updatedCoverageCriteriaFileMap = coverageCriteriaFileMapRepository
            .findById(coverageCriteriaFileMap.getCoverageCriteriaFileMapId())
            .block();
        updatedCoverageCriteriaFileMap
            .checklistId(UPDATED_CHECKLIST_ID)
            .checklistName(UPDATED_CHECKLIST_NAME)
            .documentReferenceId(UPDATED_DOCUMENT_REFERENCE_ID)
            .documentReferenceName(UPDATED_DOCUMENT_REFERENCE_NAME)
            .fileName(UPDATED_FILE_NAME)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .coverageCriteriaFileMapUuid(UPDATED_COVERAGE_CRITERIA_FILE_MAP_UUID)
            .fileReference(UPDATED_FILE_REFERENCE)
            .coverageCriteriaNotes(UPDATED_COVERAGE_CRITERIA_NOTES)
            .soId(UPDATED_SO_ID)
            .itemGroupId(UPDATED_ITEM_GROUP_ID)
            .itemGroupName(UPDATED_ITEM_GROUP_NAME)
            .coverageCriteriaId(UPDATED_COVERAGE_CRITERIA_ID);
        CoverageCriteriaFileMapDTO coverageCriteriaFileMapDTO = coverageCriteriaFileMapMapper.toDto(updatedCoverageCriteriaFileMap);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, coverageCriteriaFileMapDTO.getCoverageCriteriaFileMapId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(coverageCriteriaFileMapDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CoverageCriteriaFileMap in the database
        List<CoverageCriteriaFileMap> coverageCriteriaFileMapList = coverageCriteriaFileMapRepository.findAll().collectList().block();
        assertThat(coverageCriteriaFileMapList).hasSize(databaseSizeBeforeUpdate);
        CoverageCriteriaFileMap testCoverageCriteriaFileMap = coverageCriteriaFileMapList.get(coverageCriteriaFileMapList.size() - 1);
        assertThat(testCoverageCriteriaFileMap.getChecklistId()).isEqualTo(UPDATED_CHECKLIST_ID);
        assertThat(testCoverageCriteriaFileMap.getChecklistName()).isEqualTo(UPDATED_CHECKLIST_NAME);
        assertThat(testCoverageCriteriaFileMap.getDocumentReferenceId()).isEqualTo(UPDATED_DOCUMENT_REFERENCE_ID);
        assertThat(testCoverageCriteriaFileMap.getDocumentReferenceName()).isEqualTo(UPDATED_DOCUMENT_REFERENCE_NAME);
        assertThat(testCoverageCriteriaFileMap.getFileName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testCoverageCriteriaFileMap.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testCoverageCriteriaFileMap.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCoverageCriteriaFileMap.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testCoverageCriteriaFileMap.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testCoverageCriteriaFileMap.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testCoverageCriteriaFileMap.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testCoverageCriteriaFileMap.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testCoverageCriteriaFileMap.getCoverageCriteriaFileMapUuid()).isEqualTo(UPDATED_COVERAGE_CRITERIA_FILE_MAP_UUID);
        assertThat(testCoverageCriteriaFileMap.getFileReference()).isEqualTo(UPDATED_FILE_REFERENCE);
        assertThat(testCoverageCriteriaFileMap.getCoverageCriteriaNotes()).isEqualTo(UPDATED_COVERAGE_CRITERIA_NOTES);
        assertThat(testCoverageCriteriaFileMap.getSoId()).isEqualTo(UPDATED_SO_ID);
        assertThat(testCoverageCriteriaFileMap.getItemGroupId()).isEqualTo(UPDATED_ITEM_GROUP_ID);
        assertThat(testCoverageCriteriaFileMap.getItemGroupName()).isEqualTo(UPDATED_ITEM_GROUP_NAME);
        assertThat(testCoverageCriteriaFileMap.getCoverageCriteriaId()).isEqualTo(UPDATED_COVERAGE_CRITERIA_ID);
    }

    @Test
    void putNonExistingCoverageCriteriaFileMap() throws Exception {
        int databaseSizeBeforeUpdate = coverageCriteriaFileMapRepository.findAll().collectList().block().size();
        coverageCriteriaFileMap.setCoverageCriteriaFileMapId(count.incrementAndGet());

        // Create the CoverageCriteriaFileMap
        CoverageCriteriaFileMapDTO coverageCriteriaFileMapDTO = coverageCriteriaFileMapMapper.toDto(coverageCriteriaFileMap);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, coverageCriteriaFileMapDTO.getCoverageCriteriaFileMapId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(coverageCriteriaFileMapDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CoverageCriteriaFileMap in the database
        List<CoverageCriteriaFileMap> coverageCriteriaFileMapList = coverageCriteriaFileMapRepository.findAll().collectList().block();
        assertThat(coverageCriteriaFileMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchCoverageCriteriaFileMap() throws Exception {
        int databaseSizeBeforeUpdate = coverageCriteriaFileMapRepository.findAll().collectList().block().size();
        coverageCriteriaFileMap.setCoverageCriteriaFileMapId(count.incrementAndGet());

        // Create the CoverageCriteriaFileMap
        CoverageCriteriaFileMapDTO coverageCriteriaFileMapDTO = coverageCriteriaFileMapMapper.toDto(coverageCriteriaFileMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(coverageCriteriaFileMapDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CoverageCriteriaFileMap in the database
        List<CoverageCriteriaFileMap> coverageCriteriaFileMapList = coverageCriteriaFileMapRepository.findAll().collectList().block();
        assertThat(coverageCriteriaFileMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamCoverageCriteriaFileMap() throws Exception {
        int databaseSizeBeforeUpdate = coverageCriteriaFileMapRepository.findAll().collectList().block().size();
        coverageCriteriaFileMap.setCoverageCriteriaFileMapId(count.incrementAndGet());

        // Create the CoverageCriteriaFileMap
        CoverageCriteriaFileMapDTO coverageCriteriaFileMapDTO = coverageCriteriaFileMapMapper.toDto(coverageCriteriaFileMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(coverageCriteriaFileMapDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the CoverageCriteriaFileMap in the database
        List<CoverageCriteriaFileMap> coverageCriteriaFileMapList = coverageCriteriaFileMapRepository.findAll().collectList().block();
        assertThat(coverageCriteriaFileMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateCoverageCriteriaFileMapWithPatch() throws Exception {
        // Initialize the database
        coverageCriteriaFileMapRepository.save(coverageCriteriaFileMap).block();

        int databaseSizeBeforeUpdate = coverageCriteriaFileMapRepository.findAll().collectList().block().size();

        // Update the coverageCriteriaFileMap using partial update
        CoverageCriteriaFileMap partialUpdatedCoverageCriteriaFileMap = new CoverageCriteriaFileMap();
        partialUpdatedCoverageCriteriaFileMap.setCoverageCriteriaFileMapId(coverageCriteriaFileMap.getCoverageCriteriaFileMapId());

        partialUpdatedCoverageCriteriaFileMap
            .checklistName(UPDATED_CHECKLIST_NAME)
            .fileName(UPDATED_FILE_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .coverageCriteriaFileMapUuid(UPDATED_COVERAGE_CRITERIA_FILE_MAP_UUID)
            .fileReference(UPDATED_FILE_REFERENCE)
            .coverageCriteriaNotes(UPDATED_COVERAGE_CRITERIA_NOTES)
            .coverageCriteriaId(UPDATED_COVERAGE_CRITERIA_ID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedCoverageCriteriaFileMap.getCoverageCriteriaFileMapId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCoverageCriteriaFileMap))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CoverageCriteriaFileMap in the database
        List<CoverageCriteriaFileMap> coverageCriteriaFileMapList = coverageCriteriaFileMapRepository.findAll().collectList().block();
        assertThat(coverageCriteriaFileMapList).hasSize(databaseSizeBeforeUpdate);
        CoverageCriteriaFileMap testCoverageCriteriaFileMap = coverageCriteriaFileMapList.get(coverageCriteriaFileMapList.size() - 1);
        assertThat(testCoverageCriteriaFileMap.getChecklistId()).isEqualTo(DEFAULT_CHECKLIST_ID);
        assertThat(testCoverageCriteriaFileMap.getChecklistName()).isEqualTo(UPDATED_CHECKLIST_NAME);
        assertThat(testCoverageCriteriaFileMap.getDocumentReferenceId()).isEqualTo(DEFAULT_DOCUMENT_REFERENCE_ID);
        assertThat(testCoverageCriteriaFileMap.getDocumentReferenceName()).isEqualTo(DEFAULT_DOCUMENT_REFERENCE_NAME);
        assertThat(testCoverageCriteriaFileMap.getFileName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testCoverageCriteriaFileMap.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testCoverageCriteriaFileMap.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCoverageCriteriaFileMap.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testCoverageCriteriaFileMap.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testCoverageCriteriaFileMap.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testCoverageCriteriaFileMap.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testCoverageCriteriaFileMap.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testCoverageCriteriaFileMap.getCoverageCriteriaFileMapUuid()).isEqualTo(UPDATED_COVERAGE_CRITERIA_FILE_MAP_UUID);
        assertThat(testCoverageCriteriaFileMap.getFileReference()).isEqualTo(UPDATED_FILE_REFERENCE);
        assertThat(testCoverageCriteriaFileMap.getCoverageCriteriaNotes()).isEqualTo(UPDATED_COVERAGE_CRITERIA_NOTES);
        assertThat(testCoverageCriteriaFileMap.getSoId()).isEqualTo(DEFAULT_SO_ID);
        assertThat(testCoverageCriteriaFileMap.getItemGroupId()).isEqualTo(DEFAULT_ITEM_GROUP_ID);
        assertThat(testCoverageCriteriaFileMap.getItemGroupName()).isEqualTo(DEFAULT_ITEM_GROUP_NAME);
        assertThat(testCoverageCriteriaFileMap.getCoverageCriteriaId()).isEqualTo(UPDATED_COVERAGE_CRITERIA_ID);
    }

    @Test
    void fullUpdateCoverageCriteriaFileMapWithPatch() throws Exception {
        // Initialize the database
        coverageCriteriaFileMapRepository.save(coverageCriteriaFileMap).block();

        int databaseSizeBeforeUpdate = coverageCriteriaFileMapRepository.findAll().collectList().block().size();

        // Update the coverageCriteriaFileMap using partial update
        CoverageCriteriaFileMap partialUpdatedCoverageCriteriaFileMap = new CoverageCriteriaFileMap();
        partialUpdatedCoverageCriteriaFileMap.setCoverageCriteriaFileMapId(coverageCriteriaFileMap.getCoverageCriteriaFileMapId());

        partialUpdatedCoverageCriteriaFileMap
            .checklistId(UPDATED_CHECKLIST_ID)
            .checklistName(UPDATED_CHECKLIST_NAME)
            .documentReferenceId(UPDATED_DOCUMENT_REFERENCE_ID)
            .documentReferenceName(UPDATED_DOCUMENT_REFERENCE_NAME)
            .fileName(UPDATED_FILE_NAME)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .coverageCriteriaFileMapUuid(UPDATED_COVERAGE_CRITERIA_FILE_MAP_UUID)
            .fileReference(UPDATED_FILE_REFERENCE)
            .coverageCriteriaNotes(UPDATED_COVERAGE_CRITERIA_NOTES)
            .soId(UPDATED_SO_ID)
            .itemGroupId(UPDATED_ITEM_GROUP_ID)
            .itemGroupName(UPDATED_ITEM_GROUP_NAME)
            .coverageCriteriaId(UPDATED_COVERAGE_CRITERIA_ID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedCoverageCriteriaFileMap.getCoverageCriteriaFileMapId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCoverageCriteriaFileMap))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CoverageCriteriaFileMap in the database
        List<CoverageCriteriaFileMap> coverageCriteriaFileMapList = coverageCriteriaFileMapRepository.findAll().collectList().block();
        assertThat(coverageCriteriaFileMapList).hasSize(databaseSizeBeforeUpdate);
        CoverageCriteriaFileMap testCoverageCriteriaFileMap = coverageCriteriaFileMapList.get(coverageCriteriaFileMapList.size() - 1);
        assertThat(testCoverageCriteriaFileMap.getChecklistId()).isEqualTo(UPDATED_CHECKLIST_ID);
        assertThat(testCoverageCriteriaFileMap.getChecklistName()).isEqualTo(UPDATED_CHECKLIST_NAME);
        assertThat(testCoverageCriteriaFileMap.getDocumentReferenceId()).isEqualTo(UPDATED_DOCUMENT_REFERENCE_ID);
        assertThat(testCoverageCriteriaFileMap.getDocumentReferenceName()).isEqualTo(UPDATED_DOCUMENT_REFERENCE_NAME);
        assertThat(testCoverageCriteriaFileMap.getFileName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testCoverageCriteriaFileMap.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testCoverageCriteriaFileMap.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCoverageCriteriaFileMap.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testCoverageCriteriaFileMap.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testCoverageCriteriaFileMap.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testCoverageCriteriaFileMap.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testCoverageCriteriaFileMap.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testCoverageCriteriaFileMap.getCoverageCriteriaFileMapUuid()).isEqualTo(UPDATED_COVERAGE_CRITERIA_FILE_MAP_UUID);
        assertThat(testCoverageCriteriaFileMap.getFileReference()).isEqualTo(UPDATED_FILE_REFERENCE);
        assertThat(testCoverageCriteriaFileMap.getCoverageCriteriaNotes()).isEqualTo(UPDATED_COVERAGE_CRITERIA_NOTES);
        assertThat(testCoverageCriteriaFileMap.getSoId()).isEqualTo(UPDATED_SO_ID);
        assertThat(testCoverageCriteriaFileMap.getItemGroupId()).isEqualTo(UPDATED_ITEM_GROUP_ID);
        assertThat(testCoverageCriteriaFileMap.getItemGroupName()).isEqualTo(UPDATED_ITEM_GROUP_NAME);
        assertThat(testCoverageCriteriaFileMap.getCoverageCriteriaId()).isEqualTo(UPDATED_COVERAGE_CRITERIA_ID);
    }

    @Test
    void patchNonExistingCoverageCriteriaFileMap() throws Exception {
        int databaseSizeBeforeUpdate = coverageCriteriaFileMapRepository.findAll().collectList().block().size();
        coverageCriteriaFileMap.setCoverageCriteriaFileMapId(count.incrementAndGet());

        // Create the CoverageCriteriaFileMap
        CoverageCriteriaFileMapDTO coverageCriteriaFileMapDTO = coverageCriteriaFileMapMapper.toDto(coverageCriteriaFileMap);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, coverageCriteriaFileMapDTO.getCoverageCriteriaFileMapId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(coverageCriteriaFileMapDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CoverageCriteriaFileMap in the database
        List<CoverageCriteriaFileMap> coverageCriteriaFileMapList = coverageCriteriaFileMapRepository.findAll().collectList().block();
        assertThat(coverageCriteriaFileMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchCoverageCriteriaFileMap() throws Exception {
        int databaseSizeBeforeUpdate = coverageCriteriaFileMapRepository.findAll().collectList().block().size();
        coverageCriteriaFileMap.setCoverageCriteriaFileMapId(count.incrementAndGet());

        // Create the CoverageCriteriaFileMap
        CoverageCriteriaFileMapDTO coverageCriteriaFileMapDTO = coverageCriteriaFileMapMapper.toDto(coverageCriteriaFileMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(coverageCriteriaFileMapDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CoverageCriteriaFileMap in the database
        List<CoverageCriteriaFileMap> coverageCriteriaFileMapList = coverageCriteriaFileMapRepository.findAll().collectList().block();
        assertThat(coverageCriteriaFileMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamCoverageCriteriaFileMap() throws Exception {
        int databaseSizeBeforeUpdate = coverageCriteriaFileMapRepository.findAll().collectList().block().size();
        coverageCriteriaFileMap.setCoverageCriteriaFileMapId(count.incrementAndGet());

        // Create the CoverageCriteriaFileMap
        CoverageCriteriaFileMapDTO coverageCriteriaFileMapDTO = coverageCriteriaFileMapMapper.toDto(coverageCriteriaFileMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(coverageCriteriaFileMapDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the CoverageCriteriaFileMap in the database
        List<CoverageCriteriaFileMap> coverageCriteriaFileMapList = coverageCriteriaFileMapRepository.findAll().collectList().block();
        assertThat(coverageCriteriaFileMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteCoverageCriteriaFileMap() {
        // Initialize the database
        coverageCriteriaFileMapRepository.save(coverageCriteriaFileMap).block();

        int databaseSizeBeforeDelete = coverageCriteriaFileMapRepository.findAll().collectList().block().size();

        // Delete the coverageCriteriaFileMap
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, coverageCriteriaFileMap.getCoverageCriteriaFileMapId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<CoverageCriteriaFileMap> coverageCriteriaFileMapList = coverageCriteriaFileMapRepository.findAll().collectList().block();
        assertThat(coverageCriteriaFileMapList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
