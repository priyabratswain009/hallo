package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ParSoMap;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.ParSoMapRepository;
import com.sunknowledge.dme.rcm.service.dto.ParSoMapDTO;
import com.sunknowledge.dme.rcm.service.mapper.ParSoMapMapper;
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
 * Integration tests for the {@link ParSoMapResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class ParSoMapResourceIT {

    private static final Long DEFAULT_PAR_ID = 1L;
    private static final Long UPDATED_PAR_ID = 2L;

    private static final String DEFAULT_PAR_NO = "AAAAAAAAAA";
    private static final String UPDATED_PAR_NO = "BBBBBBBBBB";

    private static final Long DEFAULT_SO_ID = 1L;
    private static final Long UPDATED_SO_ID = 2L;

    private static final String DEFAULT_SO_NO = "AAAAAAAAAA";
    private static final String UPDATED_SO_NO = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final UUID DEFAULT_PAR_SO_MAP_UUID = UUID.randomUUID();
    private static final UUID UPDATED_PAR_SO_MAP_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/par-so-maps";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{parSoId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ParSoMapRepository parSoMapRepository;

    @Autowired
    private ParSoMapMapper parSoMapMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private ParSoMap parSoMap;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ParSoMap createEntity(EntityManager em) {
        ParSoMap parSoMap = new ParSoMap()
            .parId(DEFAULT_PAR_ID)
            .parNo(DEFAULT_PAR_NO)
            .soId(DEFAULT_SO_ID)
            .soNo(DEFAULT_SO_NO)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .parSoMapUuid(DEFAULT_PAR_SO_MAP_UUID);
        return parSoMap;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ParSoMap createUpdatedEntity(EntityManager em) {
        ParSoMap parSoMap = new ParSoMap()
            .parId(UPDATED_PAR_ID)
            .parNo(UPDATED_PAR_NO)
            .soId(UPDATED_SO_ID)
            .soNo(UPDATED_SO_NO)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .parSoMapUuid(UPDATED_PAR_SO_MAP_UUID);
        return parSoMap;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(ParSoMap.class).block();
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
        parSoMap = createEntity(em);
    }

    @Test
    void createParSoMap() throws Exception {
        int databaseSizeBeforeCreate = parSoMapRepository.findAll().collectList().block().size();
        // Create the ParSoMap
        ParSoMapDTO parSoMapDTO = parSoMapMapper.toDto(parSoMap);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(parSoMapDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the ParSoMap in the database
        List<ParSoMap> parSoMapList = parSoMapRepository.findAll().collectList().block();
        assertThat(parSoMapList).hasSize(databaseSizeBeforeCreate + 1);
        ParSoMap testParSoMap = parSoMapList.get(parSoMapList.size() - 1);
        assertThat(testParSoMap.getParId()).isEqualTo(DEFAULT_PAR_ID);
        assertThat(testParSoMap.getParNo()).isEqualTo(DEFAULT_PAR_NO);
        assertThat(testParSoMap.getSoId()).isEqualTo(DEFAULT_SO_ID);
        assertThat(testParSoMap.getSoNo()).isEqualTo(DEFAULT_SO_NO);
        assertThat(testParSoMap.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testParSoMap.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testParSoMap.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testParSoMap.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testParSoMap.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testParSoMap.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testParSoMap.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testParSoMap.getParSoMapUuid()).isEqualTo(DEFAULT_PAR_SO_MAP_UUID);
    }

    @Test
    void createParSoMapWithExistingId() throws Exception {
        // Create the ParSoMap with an existing ID
        parSoMap.setParSoId(1L);
        ParSoMapDTO parSoMapDTO = parSoMapMapper.toDto(parSoMap);

        int databaseSizeBeforeCreate = parSoMapRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(parSoMapDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ParSoMap in the database
        List<ParSoMap> parSoMapList = parSoMapRepository.findAll().collectList().block();
        assertThat(parSoMapList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllParSoMaps() {
        // Initialize the database
        parSoMapRepository.save(parSoMap).block();

        // Get all the parSoMapList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=parSoId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].parSoId")
            .value(hasItem(parSoMap.getParSoId().intValue()))
            .jsonPath("$.[*].parId")
            .value(hasItem(DEFAULT_PAR_ID.intValue()))
            .jsonPath("$.[*].parNo")
            .value(hasItem(DEFAULT_PAR_NO))
            .jsonPath("$.[*].soId")
            .value(hasItem(DEFAULT_SO_ID.intValue()))
            .jsonPath("$.[*].soNo")
            .value(hasItem(DEFAULT_SO_NO))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS))
            .jsonPath("$.[*].createdById")
            .value(hasItem(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.[*].createdByName")
            .value(hasItem(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.[*].createdDate")
            .value(hasItem(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.[*].updatedById")
            .value(hasItem(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.[*].updatedByName")
            .value(hasItem(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.[*].updatedDate")
            .value(hasItem(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.[*].parSoMapUuid")
            .value(hasItem(DEFAULT_PAR_SO_MAP_UUID.toString()));
    }

    @Test
    void getParSoMap() {
        // Initialize the database
        parSoMapRepository.save(parSoMap).block();

        // Get the parSoMap
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, parSoMap.getParSoId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.parSoId")
            .value(is(parSoMap.getParSoId().intValue()))
            .jsonPath("$.parId")
            .value(is(DEFAULT_PAR_ID.intValue()))
            .jsonPath("$.parNo")
            .value(is(DEFAULT_PAR_NO))
            .jsonPath("$.soId")
            .value(is(DEFAULT_SO_ID.intValue()))
            .jsonPath("$.soNo")
            .value(is(DEFAULT_SO_NO))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS))
            .jsonPath("$.createdById")
            .value(is(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.createdByName")
            .value(is(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.createdDate")
            .value(is(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.updatedById")
            .value(is(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.updatedByName")
            .value(is(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.updatedDate")
            .value(is(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.parSoMapUuid")
            .value(is(DEFAULT_PAR_SO_MAP_UUID.toString()));
    }

    @Test
    void getNonExistingParSoMap() {
        // Get the parSoMap
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingParSoMap() throws Exception {
        // Initialize the database
        parSoMapRepository.save(parSoMap).block();

        int databaseSizeBeforeUpdate = parSoMapRepository.findAll().collectList().block().size();

        // Update the parSoMap
        ParSoMap updatedParSoMap = parSoMapRepository.findById(parSoMap.getParSoId()).block();
        updatedParSoMap
            .parId(UPDATED_PAR_ID)
            .parNo(UPDATED_PAR_NO)
            .soId(UPDATED_SO_ID)
            .soNo(UPDATED_SO_NO)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .parSoMapUuid(UPDATED_PAR_SO_MAP_UUID);
        ParSoMapDTO parSoMapDTO = parSoMapMapper.toDto(updatedParSoMap);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, parSoMapDTO.getParSoId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(parSoMapDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ParSoMap in the database
        List<ParSoMap> parSoMapList = parSoMapRepository.findAll().collectList().block();
        assertThat(parSoMapList).hasSize(databaseSizeBeforeUpdate);
        ParSoMap testParSoMap = parSoMapList.get(parSoMapList.size() - 1);
        assertThat(testParSoMap.getParId()).isEqualTo(UPDATED_PAR_ID);
        assertThat(testParSoMap.getParNo()).isEqualTo(UPDATED_PAR_NO);
        assertThat(testParSoMap.getSoId()).isEqualTo(UPDATED_SO_ID);
        assertThat(testParSoMap.getSoNo()).isEqualTo(UPDATED_SO_NO);
        assertThat(testParSoMap.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testParSoMap.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testParSoMap.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testParSoMap.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testParSoMap.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testParSoMap.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testParSoMap.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testParSoMap.getParSoMapUuid()).isEqualTo(UPDATED_PAR_SO_MAP_UUID);
    }

    @Test
    void putNonExistingParSoMap() throws Exception {
        int databaseSizeBeforeUpdate = parSoMapRepository.findAll().collectList().block().size();
        parSoMap.setParSoId(count.incrementAndGet());

        // Create the ParSoMap
        ParSoMapDTO parSoMapDTO = parSoMapMapper.toDto(parSoMap);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, parSoMapDTO.getParSoId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(parSoMapDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ParSoMap in the database
        List<ParSoMap> parSoMapList = parSoMapRepository.findAll().collectList().block();
        assertThat(parSoMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchParSoMap() throws Exception {
        int databaseSizeBeforeUpdate = parSoMapRepository.findAll().collectList().block().size();
        parSoMap.setParSoId(count.incrementAndGet());

        // Create the ParSoMap
        ParSoMapDTO parSoMapDTO = parSoMapMapper.toDto(parSoMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(parSoMapDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ParSoMap in the database
        List<ParSoMap> parSoMapList = parSoMapRepository.findAll().collectList().block();
        assertThat(parSoMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamParSoMap() throws Exception {
        int databaseSizeBeforeUpdate = parSoMapRepository.findAll().collectList().block().size();
        parSoMap.setParSoId(count.incrementAndGet());

        // Create the ParSoMap
        ParSoMapDTO parSoMapDTO = parSoMapMapper.toDto(parSoMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(parSoMapDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the ParSoMap in the database
        List<ParSoMap> parSoMapList = parSoMapRepository.findAll().collectList().block();
        assertThat(parSoMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateParSoMapWithPatch() throws Exception {
        // Initialize the database
        parSoMapRepository.save(parSoMap).block();

        int databaseSizeBeforeUpdate = parSoMapRepository.findAll().collectList().block().size();

        // Update the parSoMap using partial update
        ParSoMap partialUpdatedParSoMap = new ParSoMap();
        partialUpdatedParSoMap.setParSoId(parSoMap.getParSoId());

        partialUpdatedParSoMap.createdById(UPDATED_CREATED_BY_ID).createdDate(UPDATED_CREATED_DATE).updatedById(UPDATED_UPDATED_BY_ID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedParSoMap.getParSoId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedParSoMap))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ParSoMap in the database
        List<ParSoMap> parSoMapList = parSoMapRepository.findAll().collectList().block();
        assertThat(parSoMapList).hasSize(databaseSizeBeforeUpdate);
        ParSoMap testParSoMap = parSoMapList.get(parSoMapList.size() - 1);
        assertThat(testParSoMap.getParId()).isEqualTo(DEFAULT_PAR_ID);
        assertThat(testParSoMap.getParNo()).isEqualTo(DEFAULT_PAR_NO);
        assertThat(testParSoMap.getSoId()).isEqualTo(DEFAULT_SO_ID);
        assertThat(testParSoMap.getSoNo()).isEqualTo(DEFAULT_SO_NO);
        assertThat(testParSoMap.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testParSoMap.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testParSoMap.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testParSoMap.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testParSoMap.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testParSoMap.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testParSoMap.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testParSoMap.getParSoMapUuid()).isEqualTo(DEFAULT_PAR_SO_MAP_UUID);
    }

    @Test
    void fullUpdateParSoMapWithPatch() throws Exception {
        // Initialize the database
        parSoMapRepository.save(parSoMap).block();

        int databaseSizeBeforeUpdate = parSoMapRepository.findAll().collectList().block().size();

        // Update the parSoMap using partial update
        ParSoMap partialUpdatedParSoMap = new ParSoMap();
        partialUpdatedParSoMap.setParSoId(parSoMap.getParSoId());

        partialUpdatedParSoMap
            .parId(UPDATED_PAR_ID)
            .parNo(UPDATED_PAR_NO)
            .soId(UPDATED_SO_ID)
            .soNo(UPDATED_SO_NO)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .parSoMapUuid(UPDATED_PAR_SO_MAP_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedParSoMap.getParSoId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedParSoMap))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ParSoMap in the database
        List<ParSoMap> parSoMapList = parSoMapRepository.findAll().collectList().block();
        assertThat(parSoMapList).hasSize(databaseSizeBeforeUpdate);
        ParSoMap testParSoMap = parSoMapList.get(parSoMapList.size() - 1);
        assertThat(testParSoMap.getParId()).isEqualTo(UPDATED_PAR_ID);
        assertThat(testParSoMap.getParNo()).isEqualTo(UPDATED_PAR_NO);
        assertThat(testParSoMap.getSoId()).isEqualTo(UPDATED_SO_ID);
        assertThat(testParSoMap.getSoNo()).isEqualTo(UPDATED_SO_NO);
        assertThat(testParSoMap.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testParSoMap.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testParSoMap.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testParSoMap.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testParSoMap.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testParSoMap.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testParSoMap.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testParSoMap.getParSoMapUuid()).isEqualTo(UPDATED_PAR_SO_MAP_UUID);
    }

    @Test
    void patchNonExistingParSoMap() throws Exception {
        int databaseSizeBeforeUpdate = parSoMapRepository.findAll().collectList().block().size();
        parSoMap.setParSoId(count.incrementAndGet());

        // Create the ParSoMap
        ParSoMapDTO parSoMapDTO = parSoMapMapper.toDto(parSoMap);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, parSoMapDTO.getParSoId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(parSoMapDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ParSoMap in the database
        List<ParSoMap> parSoMapList = parSoMapRepository.findAll().collectList().block();
        assertThat(parSoMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchParSoMap() throws Exception {
        int databaseSizeBeforeUpdate = parSoMapRepository.findAll().collectList().block().size();
        parSoMap.setParSoId(count.incrementAndGet());

        // Create the ParSoMap
        ParSoMapDTO parSoMapDTO = parSoMapMapper.toDto(parSoMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(parSoMapDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ParSoMap in the database
        List<ParSoMap> parSoMapList = parSoMapRepository.findAll().collectList().block();
        assertThat(parSoMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamParSoMap() throws Exception {
        int databaseSizeBeforeUpdate = parSoMapRepository.findAll().collectList().block().size();
        parSoMap.setParSoId(count.incrementAndGet());

        // Create the ParSoMap
        ParSoMapDTO parSoMapDTO = parSoMapMapper.toDto(parSoMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(parSoMapDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the ParSoMap in the database
        List<ParSoMap> parSoMapList = parSoMapRepository.findAll().collectList().block();
        assertThat(parSoMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteParSoMap() {
        // Initialize the database
        parSoMapRepository.save(parSoMap).block();

        int databaseSizeBeforeDelete = parSoMapRepository.findAll().collectList().block().size();

        // Delete the parSoMap
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, parSoMap.getParSoId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<ParSoMap> parSoMapList = parSoMapRepository.findAll().collectList().block();
        assertThat(parSoMapList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
