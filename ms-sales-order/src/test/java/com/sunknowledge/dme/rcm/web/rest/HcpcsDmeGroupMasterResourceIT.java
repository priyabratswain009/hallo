package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.HcpcsDmeGroupMaster;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.HcpcsDmeGroupMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.HcpcsDmeGroupMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.HcpcsDmeGroupMasterMapper;
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
 * Integration tests for the {@link HcpcsDmeGroupMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class HcpcsDmeGroupMasterResourceIT {

    private static final String DEFAULT_HCPCS_CODE = "AAAAAAAAAA";
    private static final String UPDATED_HCPCS_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DME_GROUP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DME_GROUP_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_DME_GROUP_ID = 1L;
    private static final Long UPDATED_DME_GROUP_ID = 2L;

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

    private static final UUID DEFAULT_HCPCS_DME_GROUP_MASTER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_HCPCS_DME_GROUP_MASTER_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/hcpcs-dme-group-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{hcpcsDmeId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private HcpcsDmeGroupMasterRepository hcpcsDmeGroupMasterRepository;

    @Autowired
    private HcpcsDmeGroupMasterMapper hcpcsDmeGroupMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private HcpcsDmeGroupMaster hcpcsDmeGroupMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HcpcsDmeGroupMaster createEntity(EntityManager em) {
        HcpcsDmeGroupMaster hcpcsDmeGroupMaster = new HcpcsDmeGroupMaster()
            .hcpcsCode(DEFAULT_HCPCS_CODE)
            .dmeGroupName(DEFAULT_DME_GROUP_NAME)
            .dmeGroupId(DEFAULT_DME_GROUP_ID)
            .status(DEFAULT_STATUS)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .hcpcsDmeGroupMasterUuid(DEFAULT_HCPCS_DME_GROUP_MASTER_UUID);
        return hcpcsDmeGroupMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HcpcsDmeGroupMaster createUpdatedEntity(EntityManager em) {
        HcpcsDmeGroupMaster hcpcsDmeGroupMaster = new HcpcsDmeGroupMaster()
            .hcpcsCode(UPDATED_HCPCS_CODE)
            .dmeGroupName(UPDATED_DME_GROUP_NAME)
            .dmeGroupId(UPDATED_DME_GROUP_ID)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .hcpcsDmeGroupMasterUuid(UPDATED_HCPCS_DME_GROUP_MASTER_UUID);
        return hcpcsDmeGroupMaster;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(HcpcsDmeGroupMaster.class).block();
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
        hcpcsDmeGroupMaster = createEntity(em);
    }

    @Test
    void createHcpcsDmeGroupMaster() throws Exception {
        int databaseSizeBeforeCreate = hcpcsDmeGroupMasterRepository.findAll().collectList().block().size();
        // Create the HcpcsDmeGroupMaster
        HcpcsDmeGroupMasterDTO hcpcsDmeGroupMasterDTO = hcpcsDmeGroupMasterMapper.toDto(hcpcsDmeGroupMaster);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(hcpcsDmeGroupMasterDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the HcpcsDmeGroupMaster in the database
        List<HcpcsDmeGroupMaster> hcpcsDmeGroupMasterList = hcpcsDmeGroupMasterRepository.findAll().collectList().block();
        assertThat(hcpcsDmeGroupMasterList).hasSize(databaseSizeBeforeCreate + 1);
        HcpcsDmeGroupMaster testHcpcsDmeGroupMaster = hcpcsDmeGroupMasterList.get(hcpcsDmeGroupMasterList.size() - 1);
        assertThat(testHcpcsDmeGroupMaster.getHcpcsCode()).isEqualTo(DEFAULT_HCPCS_CODE);
        assertThat(testHcpcsDmeGroupMaster.getDmeGroupName()).isEqualTo(DEFAULT_DME_GROUP_NAME);
        assertThat(testHcpcsDmeGroupMaster.getDmeGroupId()).isEqualTo(DEFAULT_DME_GROUP_ID);
        assertThat(testHcpcsDmeGroupMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testHcpcsDmeGroupMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testHcpcsDmeGroupMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testHcpcsDmeGroupMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testHcpcsDmeGroupMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testHcpcsDmeGroupMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testHcpcsDmeGroupMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testHcpcsDmeGroupMaster.getHcpcsDmeGroupMasterUuid()).isEqualTo(DEFAULT_HCPCS_DME_GROUP_MASTER_UUID);
    }

    @Test
    void createHcpcsDmeGroupMasterWithExistingId() throws Exception {
        // Create the HcpcsDmeGroupMaster with an existing ID
        hcpcsDmeGroupMaster.setHcpcsDmeId(1L);
        HcpcsDmeGroupMasterDTO hcpcsDmeGroupMasterDTO = hcpcsDmeGroupMasterMapper.toDto(hcpcsDmeGroupMaster);

        int databaseSizeBeforeCreate = hcpcsDmeGroupMasterRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(hcpcsDmeGroupMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the HcpcsDmeGroupMaster in the database
        List<HcpcsDmeGroupMaster> hcpcsDmeGroupMasterList = hcpcsDmeGroupMasterRepository.findAll().collectList().block();
        assertThat(hcpcsDmeGroupMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllHcpcsDmeGroupMasters() {
        // Initialize the database
        hcpcsDmeGroupMasterRepository.save(hcpcsDmeGroupMaster).block();

        // Get all the hcpcsDmeGroupMasterList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=hcpcsDmeId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].hcpcsDmeId")
            .value(hasItem(hcpcsDmeGroupMaster.getHcpcsDmeId().intValue()))
            .jsonPath("$.[*].hcpcsCode")
            .value(hasItem(DEFAULT_HCPCS_CODE))
            .jsonPath("$.[*].dmeGroupName")
            .value(hasItem(DEFAULT_DME_GROUP_NAME))
            .jsonPath("$.[*].dmeGroupId")
            .value(hasItem(DEFAULT_DME_GROUP_ID.intValue()))
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
            .jsonPath("$.[*].hcpcsDmeGroupMasterUuid")
            .value(hasItem(DEFAULT_HCPCS_DME_GROUP_MASTER_UUID.toString()));
    }

    @Test
    void getHcpcsDmeGroupMaster() {
        // Initialize the database
        hcpcsDmeGroupMasterRepository.save(hcpcsDmeGroupMaster).block();

        // Get the hcpcsDmeGroupMaster
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, hcpcsDmeGroupMaster.getHcpcsDmeId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.hcpcsDmeId")
            .value(is(hcpcsDmeGroupMaster.getHcpcsDmeId().intValue()))
            .jsonPath("$.hcpcsCode")
            .value(is(DEFAULT_HCPCS_CODE))
            .jsonPath("$.dmeGroupName")
            .value(is(DEFAULT_DME_GROUP_NAME))
            .jsonPath("$.dmeGroupId")
            .value(is(DEFAULT_DME_GROUP_ID.intValue()))
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
            .jsonPath("$.hcpcsDmeGroupMasterUuid")
            .value(is(DEFAULT_HCPCS_DME_GROUP_MASTER_UUID.toString()));
    }

    @Test
    void getNonExistingHcpcsDmeGroupMaster() {
        // Get the hcpcsDmeGroupMaster
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putNewHcpcsDmeGroupMaster() throws Exception {
        // Initialize the database
        hcpcsDmeGroupMasterRepository.save(hcpcsDmeGroupMaster).block();

        int databaseSizeBeforeUpdate = hcpcsDmeGroupMasterRepository.findAll().collectList().block().size();

        // Update the hcpcsDmeGroupMaster
        HcpcsDmeGroupMaster updatedHcpcsDmeGroupMaster = hcpcsDmeGroupMasterRepository
            .findById(hcpcsDmeGroupMaster.getHcpcsDmeId())
            .block();
        updatedHcpcsDmeGroupMaster
            .hcpcsCode(UPDATED_HCPCS_CODE)
            .dmeGroupName(UPDATED_DME_GROUP_NAME)
            .dmeGroupId(UPDATED_DME_GROUP_ID)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .hcpcsDmeGroupMasterUuid(UPDATED_HCPCS_DME_GROUP_MASTER_UUID);
        HcpcsDmeGroupMasterDTO hcpcsDmeGroupMasterDTO = hcpcsDmeGroupMasterMapper.toDto(updatedHcpcsDmeGroupMaster);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, hcpcsDmeGroupMasterDTO.getHcpcsDmeId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(hcpcsDmeGroupMasterDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the HcpcsDmeGroupMaster in the database
        List<HcpcsDmeGroupMaster> hcpcsDmeGroupMasterList = hcpcsDmeGroupMasterRepository.findAll().collectList().block();
        assertThat(hcpcsDmeGroupMasterList).hasSize(databaseSizeBeforeUpdate);
        HcpcsDmeGroupMaster testHcpcsDmeGroupMaster = hcpcsDmeGroupMasterList.get(hcpcsDmeGroupMasterList.size() - 1);
        assertThat(testHcpcsDmeGroupMaster.getHcpcsCode()).isEqualTo(UPDATED_HCPCS_CODE);
        assertThat(testHcpcsDmeGroupMaster.getDmeGroupName()).isEqualTo(UPDATED_DME_GROUP_NAME);
        assertThat(testHcpcsDmeGroupMaster.getDmeGroupId()).isEqualTo(UPDATED_DME_GROUP_ID);
        assertThat(testHcpcsDmeGroupMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testHcpcsDmeGroupMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testHcpcsDmeGroupMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testHcpcsDmeGroupMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testHcpcsDmeGroupMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testHcpcsDmeGroupMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testHcpcsDmeGroupMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testHcpcsDmeGroupMaster.getHcpcsDmeGroupMasterUuid()).isEqualTo(UPDATED_HCPCS_DME_GROUP_MASTER_UUID);
    }

    @Test
    void putNonExistingHcpcsDmeGroupMaster() throws Exception {
        int databaseSizeBeforeUpdate = hcpcsDmeGroupMasterRepository.findAll().collectList().block().size();
        hcpcsDmeGroupMaster.setHcpcsDmeId(count.incrementAndGet());

        // Create the HcpcsDmeGroupMaster
        HcpcsDmeGroupMasterDTO hcpcsDmeGroupMasterDTO = hcpcsDmeGroupMasterMapper.toDto(hcpcsDmeGroupMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, hcpcsDmeGroupMasterDTO.getHcpcsDmeId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(hcpcsDmeGroupMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the HcpcsDmeGroupMaster in the database
        List<HcpcsDmeGroupMaster> hcpcsDmeGroupMasterList = hcpcsDmeGroupMasterRepository.findAll().collectList().block();
        assertThat(hcpcsDmeGroupMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchHcpcsDmeGroupMaster() throws Exception {
        int databaseSizeBeforeUpdate = hcpcsDmeGroupMasterRepository.findAll().collectList().block().size();
        hcpcsDmeGroupMaster.setHcpcsDmeId(count.incrementAndGet());

        // Create the HcpcsDmeGroupMaster
        HcpcsDmeGroupMasterDTO hcpcsDmeGroupMasterDTO = hcpcsDmeGroupMasterMapper.toDto(hcpcsDmeGroupMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(hcpcsDmeGroupMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the HcpcsDmeGroupMaster in the database
        List<HcpcsDmeGroupMaster> hcpcsDmeGroupMasterList = hcpcsDmeGroupMasterRepository.findAll().collectList().block();
        assertThat(hcpcsDmeGroupMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamHcpcsDmeGroupMaster() throws Exception {
        int databaseSizeBeforeUpdate = hcpcsDmeGroupMasterRepository.findAll().collectList().block().size();
        hcpcsDmeGroupMaster.setHcpcsDmeId(count.incrementAndGet());

        // Create the HcpcsDmeGroupMaster
        HcpcsDmeGroupMasterDTO hcpcsDmeGroupMasterDTO = hcpcsDmeGroupMasterMapper.toDto(hcpcsDmeGroupMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(hcpcsDmeGroupMasterDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the HcpcsDmeGroupMaster in the database
        List<HcpcsDmeGroupMaster> hcpcsDmeGroupMasterList = hcpcsDmeGroupMasterRepository.findAll().collectList().block();
        assertThat(hcpcsDmeGroupMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateHcpcsDmeGroupMasterWithPatch() throws Exception {
        // Initialize the database
        hcpcsDmeGroupMasterRepository.save(hcpcsDmeGroupMaster).block();

        int databaseSizeBeforeUpdate = hcpcsDmeGroupMasterRepository.findAll().collectList().block().size();

        // Update the hcpcsDmeGroupMaster using partial update
        HcpcsDmeGroupMaster partialUpdatedHcpcsDmeGroupMaster = new HcpcsDmeGroupMaster();
        partialUpdatedHcpcsDmeGroupMaster.setHcpcsDmeId(hcpcsDmeGroupMaster.getHcpcsDmeId());

        partialUpdatedHcpcsDmeGroupMaster
            .hcpcsCode(UPDATED_HCPCS_CODE)
            .dmeGroupName(UPDATED_DME_GROUP_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedHcpcsDmeGroupMaster.getHcpcsDmeId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedHcpcsDmeGroupMaster))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the HcpcsDmeGroupMaster in the database
        List<HcpcsDmeGroupMaster> hcpcsDmeGroupMasterList = hcpcsDmeGroupMasterRepository.findAll().collectList().block();
        assertThat(hcpcsDmeGroupMasterList).hasSize(databaseSizeBeforeUpdate);
        HcpcsDmeGroupMaster testHcpcsDmeGroupMaster = hcpcsDmeGroupMasterList.get(hcpcsDmeGroupMasterList.size() - 1);
        assertThat(testHcpcsDmeGroupMaster.getHcpcsCode()).isEqualTo(UPDATED_HCPCS_CODE);
        assertThat(testHcpcsDmeGroupMaster.getDmeGroupName()).isEqualTo(UPDATED_DME_GROUP_NAME);
        assertThat(testHcpcsDmeGroupMaster.getDmeGroupId()).isEqualTo(DEFAULT_DME_GROUP_ID);
        assertThat(testHcpcsDmeGroupMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testHcpcsDmeGroupMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testHcpcsDmeGroupMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testHcpcsDmeGroupMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testHcpcsDmeGroupMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testHcpcsDmeGroupMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testHcpcsDmeGroupMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testHcpcsDmeGroupMaster.getHcpcsDmeGroupMasterUuid()).isEqualTo(DEFAULT_HCPCS_DME_GROUP_MASTER_UUID);
    }

    @Test
    void fullUpdateHcpcsDmeGroupMasterWithPatch() throws Exception {
        // Initialize the database
        hcpcsDmeGroupMasterRepository.save(hcpcsDmeGroupMaster).block();

        int databaseSizeBeforeUpdate = hcpcsDmeGroupMasterRepository.findAll().collectList().block().size();

        // Update the hcpcsDmeGroupMaster using partial update
        HcpcsDmeGroupMaster partialUpdatedHcpcsDmeGroupMaster = new HcpcsDmeGroupMaster();
        partialUpdatedHcpcsDmeGroupMaster.setHcpcsDmeId(hcpcsDmeGroupMaster.getHcpcsDmeId());

        partialUpdatedHcpcsDmeGroupMaster
            .hcpcsCode(UPDATED_HCPCS_CODE)
            .dmeGroupName(UPDATED_DME_GROUP_NAME)
            .dmeGroupId(UPDATED_DME_GROUP_ID)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .hcpcsDmeGroupMasterUuid(UPDATED_HCPCS_DME_GROUP_MASTER_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedHcpcsDmeGroupMaster.getHcpcsDmeId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedHcpcsDmeGroupMaster))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the HcpcsDmeGroupMaster in the database
        List<HcpcsDmeGroupMaster> hcpcsDmeGroupMasterList = hcpcsDmeGroupMasterRepository.findAll().collectList().block();
        assertThat(hcpcsDmeGroupMasterList).hasSize(databaseSizeBeforeUpdate);
        HcpcsDmeGroupMaster testHcpcsDmeGroupMaster = hcpcsDmeGroupMasterList.get(hcpcsDmeGroupMasterList.size() - 1);
        assertThat(testHcpcsDmeGroupMaster.getHcpcsCode()).isEqualTo(UPDATED_HCPCS_CODE);
        assertThat(testHcpcsDmeGroupMaster.getDmeGroupName()).isEqualTo(UPDATED_DME_GROUP_NAME);
        assertThat(testHcpcsDmeGroupMaster.getDmeGroupId()).isEqualTo(UPDATED_DME_GROUP_ID);
        assertThat(testHcpcsDmeGroupMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testHcpcsDmeGroupMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testHcpcsDmeGroupMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testHcpcsDmeGroupMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testHcpcsDmeGroupMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testHcpcsDmeGroupMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testHcpcsDmeGroupMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testHcpcsDmeGroupMaster.getHcpcsDmeGroupMasterUuid()).isEqualTo(UPDATED_HCPCS_DME_GROUP_MASTER_UUID);
    }

    @Test
    void patchNonExistingHcpcsDmeGroupMaster() throws Exception {
        int databaseSizeBeforeUpdate = hcpcsDmeGroupMasterRepository.findAll().collectList().block().size();
        hcpcsDmeGroupMaster.setHcpcsDmeId(count.incrementAndGet());

        // Create the HcpcsDmeGroupMaster
        HcpcsDmeGroupMasterDTO hcpcsDmeGroupMasterDTO = hcpcsDmeGroupMasterMapper.toDto(hcpcsDmeGroupMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, hcpcsDmeGroupMasterDTO.getHcpcsDmeId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(hcpcsDmeGroupMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the HcpcsDmeGroupMaster in the database
        List<HcpcsDmeGroupMaster> hcpcsDmeGroupMasterList = hcpcsDmeGroupMasterRepository.findAll().collectList().block();
        assertThat(hcpcsDmeGroupMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchHcpcsDmeGroupMaster() throws Exception {
        int databaseSizeBeforeUpdate = hcpcsDmeGroupMasterRepository.findAll().collectList().block().size();
        hcpcsDmeGroupMaster.setHcpcsDmeId(count.incrementAndGet());

        // Create the HcpcsDmeGroupMaster
        HcpcsDmeGroupMasterDTO hcpcsDmeGroupMasterDTO = hcpcsDmeGroupMasterMapper.toDto(hcpcsDmeGroupMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(hcpcsDmeGroupMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the HcpcsDmeGroupMaster in the database
        List<HcpcsDmeGroupMaster> hcpcsDmeGroupMasterList = hcpcsDmeGroupMasterRepository.findAll().collectList().block();
        assertThat(hcpcsDmeGroupMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamHcpcsDmeGroupMaster() throws Exception {
        int databaseSizeBeforeUpdate = hcpcsDmeGroupMasterRepository.findAll().collectList().block().size();
        hcpcsDmeGroupMaster.setHcpcsDmeId(count.incrementAndGet());

        // Create the HcpcsDmeGroupMaster
        HcpcsDmeGroupMasterDTO hcpcsDmeGroupMasterDTO = hcpcsDmeGroupMasterMapper.toDto(hcpcsDmeGroupMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(hcpcsDmeGroupMasterDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the HcpcsDmeGroupMaster in the database
        List<HcpcsDmeGroupMaster> hcpcsDmeGroupMasterList = hcpcsDmeGroupMasterRepository.findAll().collectList().block();
        assertThat(hcpcsDmeGroupMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteHcpcsDmeGroupMaster() {
        // Initialize the database
        hcpcsDmeGroupMasterRepository.save(hcpcsDmeGroupMaster).block();

        int databaseSizeBeforeDelete = hcpcsDmeGroupMasterRepository.findAll().collectList().block().size();

        // Delete the hcpcsDmeGroupMaster
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, hcpcsDmeGroupMaster.getHcpcsDmeId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<HcpcsDmeGroupMaster> hcpcsDmeGroupMasterList = hcpcsDmeGroupMasterRepository.findAll().collectList().block();
        assertThat(hcpcsDmeGroupMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
