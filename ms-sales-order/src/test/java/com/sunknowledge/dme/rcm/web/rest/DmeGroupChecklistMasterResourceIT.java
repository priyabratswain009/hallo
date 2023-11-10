package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.DmeGroupChecklistMaster;
import com.sunknowledge.dme.rcm.repository.DmeGroupChecklistMasterRepository;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.service.dto.DmeGroupChecklistMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.DmeGroupChecklistMasterMapper;
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
 * Integration tests for the {@link DmeGroupChecklistMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class DmeGroupChecklistMasterResourceIT {

    private static final Long DEFAULT_DME_GROUP_ID = 1L;
    private static final Long UPDATED_DME_GROUP_ID = 2L;

    private static final String DEFAULT_DME_GROUP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DME_GROUP_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_CHECKLIST_ID = 1L;
    private static final Long UPDATED_CHECKLIST_ID = 2L;

    private static final String DEFAULT_CHECKLIST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CHECKLIST_NAME = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_DME_GROUP_CHECKLIST_MASTER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_DME_GROUP_CHECKLIST_MASTER_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/dme-group-checklist-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{dmeGroupChecklistId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DmeGroupChecklistMasterRepository dmeGroupChecklistMasterRepository;

    @Autowired
    private DmeGroupChecklistMasterMapper dmeGroupChecklistMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private DmeGroupChecklistMaster dmeGroupChecklistMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DmeGroupChecklistMaster createEntity(EntityManager em) {
        DmeGroupChecklistMaster dmeGroupChecklistMaster = new DmeGroupChecklistMaster()
            .dmeGroupId(DEFAULT_DME_GROUP_ID)
            .dmeGroupName(DEFAULT_DME_GROUP_NAME)
            .checklistId(DEFAULT_CHECKLIST_ID)
            .checklistName(DEFAULT_CHECKLIST_NAME)
            .status(DEFAULT_STATUS)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .dmeGroupChecklistMasterUuid(DEFAULT_DME_GROUP_CHECKLIST_MASTER_UUID);
        return dmeGroupChecklistMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DmeGroupChecklistMaster createUpdatedEntity(EntityManager em) {
        DmeGroupChecklistMaster dmeGroupChecklistMaster = new DmeGroupChecklistMaster()
            .dmeGroupId(UPDATED_DME_GROUP_ID)
            .dmeGroupName(UPDATED_DME_GROUP_NAME)
            .checklistId(UPDATED_CHECKLIST_ID)
            .checklistName(UPDATED_CHECKLIST_NAME)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .dmeGroupChecklistMasterUuid(UPDATED_DME_GROUP_CHECKLIST_MASTER_UUID);
        return dmeGroupChecklistMaster;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(DmeGroupChecklistMaster.class).block();
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
        dmeGroupChecklistMaster = createEntity(em);
    }

    @Test
    void createDmeGroupChecklistMaster() throws Exception {
        int databaseSizeBeforeCreate = dmeGroupChecklistMasterRepository.findAll().collectList().block().size();
        // Create the DmeGroupChecklistMaster
        DmeGroupChecklistMasterDTO dmeGroupChecklistMasterDTO = dmeGroupChecklistMasterMapper.toDto(dmeGroupChecklistMaster);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dmeGroupChecklistMasterDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the DmeGroupChecklistMaster in the database
        List<DmeGroupChecklistMaster> dmeGroupChecklistMasterList = dmeGroupChecklistMasterRepository.findAll().collectList().block();
        assertThat(dmeGroupChecklistMasterList).hasSize(databaseSizeBeforeCreate + 1);
        DmeGroupChecklistMaster testDmeGroupChecklistMaster = dmeGroupChecklistMasterList.get(dmeGroupChecklistMasterList.size() - 1);
        assertThat(testDmeGroupChecklistMaster.getDmeGroupId()).isEqualTo(DEFAULT_DME_GROUP_ID);
        assertThat(testDmeGroupChecklistMaster.getDmeGroupName()).isEqualTo(DEFAULT_DME_GROUP_NAME);
        assertThat(testDmeGroupChecklistMaster.getChecklistId()).isEqualTo(DEFAULT_CHECKLIST_ID);
        assertThat(testDmeGroupChecklistMaster.getChecklistName()).isEqualTo(DEFAULT_CHECKLIST_NAME);
        assertThat(testDmeGroupChecklistMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testDmeGroupChecklistMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testDmeGroupChecklistMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testDmeGroupChecklistMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testDmeGroupChecklistMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testDmeGroupChecklistMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testDmeGroupChecklistMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testDmeGroupChecklistMaster.getDmeGroupChecklistMasterUuid()).isEqualTo(DEFAULT_DME_GROUP_CHECKLIST_MASTER_UUID);
    }

    @Test
    void createDmeGroupChecklistMasterWithExistingId() throws Exception {
        // Create the DmeGroupChecklistMaster with an existing ID
        dmeGroupChecklistMaster.setDmeGroupChecklistId(1L);
        DmeGroupChecklistMasterDTO dmeGroupChecklistMasterDTO = dmeGroupChecklistMasterMapper.toDto(dmeGroupChecklistMaster);

        int databaseSizeBeforeCreate = dmeGroupChecklistMasterRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dmeGroupChecklistMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DmeGroupChecklistMaster in the database
        List<DmeGroupChecklistMaster> dmeGroupChecklistMasterList = dmeGroupChecklistMasterRepository.findAll().collectList().block();
        assertThat(dmeGroupChecklistMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllDmeGroupChecklistMasters() {
        // Initialize the database
        dmeGroupChecklistMasterRepository.save(dmeGroupChecklistMaster).block();

        // Get all the dmeGroupChecklistMasterList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=dmeGroupChecklistId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].dmeGroupChecklistId")
            .value(hasItem(dmeGroupChecklistMaster.getDmeGroupChecklistId().intValue()))
            .jsonPath("$.[*].dmeGroupId")
            .value(hasItem(DEFAULT_DME_GROUP_ID.intValue()))
            .jsonPath("$.[*].dmeGroupName")
            .value(hasItem(DEFAULT_DME_GROUP_NAME))
            .jsonPath("$.[*].checklistId")
            .value(hasItem(DEFAULT_CHECKLIST_ID.intValue()))
            .jsonPath("$.[*].checklistName")
            .value(hasItem(DEFAULT_CHECKLIST_NAME))
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
            .jsonPath("$.[*].dmeGroupChecklistMasterUuid")
            .value(hasItem(DEFAULT_DME_GROUP_CHECKLIST_MASTER_UUID.toString()));
    }

    @Test
    void getDmeGroupChecklistMaster() {
        // Initialize the database
        dmeGroupChecklistMasterRepository.save(dmeGroupChecklistMaster).block();

        // Get the dmeGroupChecklistMaster
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, dmeGroupChecklistMaster.getDmeGroupChecklistId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.dmeGroupChecklistId")
            .value(is(dmeGroupChecklistMaster.getDmeGroupChecklistId().intValue()))
            .jsonPath("$.dmeGroupId")
            .value(is(DEFAULT_DME_GROUP_ID.intValue()))
            .jsonPath("$.dmeGroupName")
            .value(is(DEFAULT_DME_GROUP_NAME))
            .jsonPath("$.checklistId")
            .value(is(DEFAULT_CHECKLIST_ID.intValue()))
            .jsonPath("$.checklistName")
            .value(is(DEFAULT_CHECKLIST_NAME))
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
            .jsonPath("$.dmeGroupChecklistMasterUuid")
            .value(is(DEFAULT_DME_GROUP_CHECKLIST_MASTER_UUID.toString()));
    }

    @Test
    void getNonExistingDmeGroupChecklistMaster() {
        // Get the dmeGroupChecklistMaster
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putNewDmeGroupChecklistMaster() throws Exception {
        // Initialize the database
        dmeGroupChecklistMasterRepository.save(dmeGroupChecklistMaster).block();

        int databaseSizeBeforeUpdate = dmeGroupChecklistMasterRepository.findAll().collectList().block().size();

        // Update the dmeGroupChecklistMaster
        DmeGroupChecklistMaster updatedDmeGroupChecklistMaster = dmeGroupChecklistMasterRepository
            .findById(dmeGroupChecklistMaster.getDmeGroupChecklistId())
            .block();
        updatedDmeGroupChecklistMaster
            .dmeGroupId(UPDATED_DME_GROUP_ID)
            .dmeGroupName(UPDATED_DME_GROUP_NAME)
            .checklistId(UPDATED_CHECKLIST_ID)
            .checklistName(UPDATED_CHECKLIST_NAME)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .dmeGroupChecklistMasterUuid(UPDATED_DME_GROUP_CHECKLIST_MASTER_UUID);
        DmeGroupChecklistMasterDTO dmeGroupChecklistMasterDTO = dmeGroupChecklistMasterMapper.toDto(updatedDmeGroupChecklistMaster);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, dmeGroupChecklistMasterDTO.getDmeGroupChecklistId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dmeGroupChecklistMasterDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the DmeGroupChecklistMaster in the database
        List<DmeGroupChecklistMaster> dmeGroupChecklistMasterList = dmeGroupChecklistMasterRepository.findAll().collectList().block();
        assertThat(dmeGroupChecklistMasterList).hasSize(databaseSizeBeforeUpdate);
        DmeGroupChecklistMaster testDmeGroupChecklistMaster = dmeGroupChecklistMasterList.get(dmeGroupChecklistMasterList.size() - 1);
        assertThat(testDmeGroupChecklistMaster.getDmeGroupId()).isEqualTo(UPDATED_DME_GROUP_ID);
        assertThat(testDmeGroupChecklistMaster.getDmeGroupName()).isEqualTo(UPDATED_DME_GROUP_NAME);
        assertThat(testDmeGroupChecklistMaster.getChecklistId()).isEqualTo(UPDATED_CHECKLIST_ID);
        assertThat(testDmeGroupChecklistMaster.getChecklistName()).isEqualTo(UPDATED_CHECKLIST_NAME);
        assertThat(testDmeGroupChecklistMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDmeGroupChecklistMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testDmeGroupChecklistMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testDmeGroupChecklistMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testDmeGroupChecklistMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testDmeGroupChecklistMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testDmeGroupChecklistMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testDmeGroupChecklistMaster.getDmeGroupChecklistMasterUuid()).isEqualTo(UPDATED_DME_GROUP_CHECKLIST_MASTER_UUID);
    }

    @Test
    void putNonExistingDmeGroupChecklistMaster() throws Exception {
        int databaseSizeBeforeUpdate = dmeGroupChecklistMasterRepository.findAll().collectList().block().size();
        dmeGroupChecklistMaster.setDmeGroupChecklistId(count.incrementAndGet());

        // Create the DmeGroupChecklistMaster
        DmeGroupChecklistMasterDTO dmeGroupChecklistMasterDTO = dmeGroupChecklistMasterMapper.toDto(dmeGroupChecklistMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, dmeGroupChecklistMasterDTO.getDmeGroupChecklistId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dmeGroupChecklistMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DmeGroupChecklistMaster in the database
        List<DmeGroupChecklistMaster> dmeGroupChecklistMasterList = dmeGroupChecklistMasterRepository.findAll().collectList().block();
        assertThat(dmeGroupChecklistMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchDmeGroupChecklistMaster() throws Exception {
        int databaseSizeBeforeUpdate = dmeGroupChecklistMasterRepository.findAll().collectList().block().size();
        dmeGroupChecklistMaster.setDmeGroupChecklistId(count.incrementAndGet());

        // Create the DmeGroupChecklistMaster
        DmeGroupChecklistMasterDTO dmeGroupChecklistMasterDTO = dmeGroupChecklistMasterMapper.toDto(dmeGroupChecklistMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dmeGroupChecklistMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DmeGroupChecklistMaster in the database
        List<DmeGroupChecklistMaster> dmeGroupChecklistMasterList = dmeGroupChecklistMasterRepository.findAll().collectList().block();
        assertThat(dmeGroupChecklistMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamDmeGroupChecklistMaster() throws Exception {
        int databaseSizeBeforeUpdate = dmeGroupChecklistMasterRepository.findAll().collectList().block().size();
        dmeGroupChecklistMaster.setDmeGroupChecklistId(count.incrementAndGet());

        // Create the DmeGroupChecklistMaster
        DmeGroupChecklistMasterDTO dmeGroupChecklistMasterDTO = dmeGroupChecklistMasterMapper.toDto(dmeGroupChecklistMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dmeGroupChecklistMasterDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the DmeGroupChecklistMaster in the database
        List<DmeGroupChecklistMaster> dmeGroupChecklistMasterList = dmeGroupChecklistMasterRepository.findAll().collectList().block();
        assertThat(dmeGroupChecklistMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateDmeGroupChecklistMasterWithPatch() throws Exception {
        // Initialize the database
        dmeGroupChecklistMasterRepository.save(dmeGroupChecklistMaster).block();

        int databaseSizeBeforeUpdate = dmeGroupChecklistMasterRepository.findAll().collectList().block().size();

        // Update the dmeGroupChecklistMaster using partial update
        DmeGroupChecklistMaster partialUpdatedDmeGroupChecklistMaster = new DmeGroupChecklistMaster();
        partialUpdatedDmeGroupChecklistMaster.setDmeGroupChecklistId(dmeGroupChecklistMaster.getDmeGroupChecklistId());

        partialUpdatedDmeGroupChecklistMaster
            .dmeGroupId(UPDATED_DME_GROUP_ID)
            .checklistId(UPDATED_CHECKLIST_ID)
            .checklistName(UPDATED_CHECKLIST_NAME)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedDmeGroupChecklistMaster.getDmeGroupChecklistId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedDmeGroupChecklistMaster))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the DmeGroupChecklistMaster in the database
        List<DmeGroupChecklistMaster> dmeGroupChecklistMasterList = dmeGroupChecklistMasterRepository.findAll().collectList().block();
        assertThat(dmeGroupChecklistMasterList).hasSize(databaseSizeBeforeUpdate);
        DmeGroupChecklistMaster testDmeGroupChecklistMaster = dmeGroupChecklistMasterList.get(dmeGroupChecklistMasterList.size() - 1);
        assertThat(testDmeGroupChecklistMaster.getDmeGroupId()).isEqualTo(UPDATED_DME_GROUP_ID);
        assertThat(testDmeGroupChecklistMaster.getDmeGroupName()).isEqualTo(DEFAULT_DME_GROUP_NAME);
        assertThat(testDmeGroupChecklistMaster.getChecklistId()).isEqualTo(UPDATED_CHECKLIST_ID);
        assertThat(testDmeGroupChecklistMaster.getChecklistName()).isEqualTo(UPDATED_CHECKLIST_NAME);
        assertThat(testDmeGroupChecklistMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testDmeGroupChecklistMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testDmeGroupChecklistMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testDmeGroupChecklistMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testDmeGroupChecklistMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testDmeGroupChecklistMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testDmeGroupChecklistMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testDmeGroupChecklistMaster.getDmeGroupChecklistMasterUuid()).isEqualTo(DEFAULT_DME_GROUP_CHECKLIST_MASTER_UUID);
    }

    @Test
    void fullUpdateDmeGroupChecklistMasterWithPatch() throws Exception {
        // Initialize the database
        dmeGroupChecklistMasterRepository.save(dmeGroupChecklistMaster).block();

        int databaseSizeBeforeUpdate = dmeGroupChecklistMasterRepository.findAll().collectList().block().size();

        // Update the dmeGroupChecklistMaster using partial update
        DmeGroupChecklistMaster partialUpdatedDmeGroupChecklistMaster = new DmeGroupChecklistMaster();
        partialUpdatedDmeGroupChecklistMaster.setDmeGroupChecklistId(dmeGroupChecklistMaster.getDmeGroupChecklistId());

        partialUpdatedDmeGroupChecklistMaster
            .dmeGroupId(UPDATED_DME_GROUP_ID)
            .dmeGroupName(UPDATED_DME_GROUP_NAME)
            .checklistId(UPDATED_CHECKLIST_ID)
            .checklistName(UPDATED_CHECKLIST_NAME)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .dmeGroupChecklistMasterUuid(UPDATED_DME_GROUP_CHECKLIST_MASTER_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedDmeGroupChecklistMaster.getDmeGroupChecklistId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedDmeGroupChecklistMaster))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the DmeGroupChecklistMaster in the database
        List<DmeGroupChecklistMaster> dmeGroupChecklistMasterList = dmeGroupChecklistMasterRepository.findAll().collectList().block();
        assertThat(dmeGroupChecklistMasterList).hasSize(databaseSizeBeforeUpdate);
        DmeGroupChecklistMaster testDmeGroupChecklistMaster = dmeGroupChecklistMasterList.get(dmeGroupChecklistMasterList.size() - 1);
        assertThat(testDmeGroupChecklistMaster.getDmeGroupId()).isEqualTo(UPDATED_DME_GROUP_ID);
        assertThat(testDmeGroupChecklistMaster.getDmeGroupName()).isEqualTo(UPDATED_DME_GROUP_NAME);
        assertThat(testDmeGroupChecklistMaster.getChecklistId()).isEqualTo(UPDATED_CHECKLIST_ID);
        assertThat(testDmeGroupChecklistMaster.getChecklistName()).isEqualTo(UPDATED_CHECKLIST_NAME);
        assertThat(testDmeGroupChecklistMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDmeGroupChecklistMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testDmeGroupChecklistMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testDmeGroupChecklistMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testDmeGroupChecklistMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testDmeGroupChecklistMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testDmeGroupChecklistMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testDmeGroupChecklistMaster.getDmeGroupChecklistMasterUuid()).isEqualTo(UPDATED_DME_GROUP_CHECKLIST_MASTER_UUID);
    }

    @Test
    void patchNonExistingDmeGroupChecklistMaster() throws Exception {
        int databaseSizeBeforeUpdate = dmeGroupChecklistMasterRepository.findAll().collectList().block().size();
        dmeGroupChecklistMaster.setDmeGroupChecklistId(count.incrementAndGet());

        // Create the DmeGroupChecklistMaster
        DmeGroupChecklistMasterDTO dmeGroupChecklistMasterDTO = dmeGroupChecklistMasterMapper.toDto(dmeGroupChecklistMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, dmeGroupChecklistMasterDTO.getDmeGroupChecklistId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(dmeGroupChecklistMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DmeGroupChecklistMaster in the database
        List<DmeGroupChecklistMaster> dmeGroupChecklistMasterList = dmeGroupChecklistMasterRepository.findAll().collectList().block();
        assertThat(dmeGroupChecklistMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchDmeGroupChecklistMaster() throws Exception {
        int databaseSizeBeforeUpdate = dmeGroupChecklistMasterRepository.findAll().collectList().block().size();
        dmeGroupChecklistMaster.setDmeGroupChecklistId(count.incrementAndGet());

        // Create the DmeGroupChecklistMaster
        DmeGroupChecklistMasterDTO dmeGroupChecklistMasterDTO = dmeGroupChecklistMasterMapper.toDto(dmeGroupChecklistMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(dmeGroupChecklistMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DmeGroupChecklistMaster in the database
        List<DmeGroupChecklistMaster> dmeGroupChecklistMasterList = dmeGroupChecklistMasterRepository.findAll().collectList().block();
        assertThat(dmeGroupChecklistMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamDmeGroupChecklistMaster() throws Exception {
        int databaseSizeBeforeUpdate = dmeGroupChecklistMasterRepository.findAll().collectList().block().size();
        dmeGroupChecklistMaster.setDmeGroupChecklistId(count.incrementAndGet());

        // Create the DmeGroupChecklistMaster
        DmeGroupChecklistMasterDTO dmeGroupChecklistMasterDTO = dmeGroupChecklistMasterMapper.toDto(dmeGroupChecklistMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(dmeGroupChecklistMasterDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the DmeGroupChecklistMaster in the database
        List<DmeGroupChecklistMaster> dmeGroupChecklistMasterList = dmeGroupChecklistMasterRepository.findAll().collectList().block();
        assertThat(dmeGroupChecklistMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteDmeGroupChecklistMaster() {
        // Initialize the database
        dmeGroupChecklistMasterRepository.save(dmeGroupChecklistMaster).block();

        int databaseSizeBeforeDelete = dmeGroupChecklistMasterRepository.findAll().collectList().block().size();

        // Delete the dmeGroupChecklistMaster
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, dmeGroupChecklistMaster.getDmeGroupChecklistId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<DmeGroupChecklistMaster> dmeGroupChecklistMasterList = dmeGroupChecklistMasterRepository.findAll().collectList().block();
        assertThat(dmeGroupChecklistMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
