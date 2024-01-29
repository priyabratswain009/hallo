package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.InsurancePricetableMap;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.InsurancePricetableMapRepository;
import com.sunknowledge.dme.rcm.service.dto.InsurancePricetableMapDTO;
import com.sunknowledge.dme.rcm.service.mapper.InsurancePricetableMapMapper;
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
 * Integration tests for the {@link InsurancePricetableMapResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class InsurancePricetableMapResourceIT {

    private static final String DEFAULT_INSURANCE_ID_NO = "AAAAAAAAAA";
    private static final String UPDATED_INSURANCE_ID_NO = "BBBBBBBBBB";

    private static final String DEFAULT_INSURANCE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INSURANCE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_INSURANCE_PAYER_ID_NO = "AAAAAAAAAA";
    private static final String UPDATED_INSURANCE_PAYER_ID_NO = "BBBBBBBBBB";

    private static final Long DEFAULT_PRICE_TABLE_ID = 1L;
    private static final Long UPDATED_PRICE_TABLE_ID = 2L;

    private static final String DEFAULT_PRICE_TABLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PRICE_TABLE_NAME = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_INSURANCE_PRICETABLE_MAP_UUID = UUID.randomUUID();
    private static final UUID UPDATED_INSURANCE_PRICETABLE_MAP_UUID = UUID.randomUUID();

    private static final Long DEFAULT_INSURANCE_ID = 1L;
    private static final Long UPDATED_INSURANCE_ID = 2L;

    private static final String ENTITY_API_URL = "/api/insurance-pricetable-maps";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{insurancePricetableMapId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private InsurancePricetableMapRepository insurancePricetableMapRepository;

    @Autowired
    private InsurancePricetableMapMapper insurancePricetableMapMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private InsurancePricetableMap insurancePricetableMap;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InsurancePricetableMap createEntity(EntityManager em) {
        InsurancePricetableMap insurancePricetableMap = new InsurancePricetableMap()
            .insuranceIdNo(DEFAULT_INSURANCE_ID_NO)
            .insuranceName(DEFAULT_INSURANCE_NAME)
            .insurancePayerIdNo(DEFAULT_INSURANCE_PAYER_ID_NO)
            .priceTableId(DEFAULT_PRICE_TABLE_ID)
            .priceTableName(DEFAULT_PRICE_TABLE_NAME)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .insurancePricetableMapUuid(DEFAULT_INSURANCE_PRICETABLE_MAP_UUID)
            .insuranceId(DEFAULT_INSURANCE_ID);
        return insurancePricetableMap;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InsurancePricetableMap createUpdatedEntity(EntityManager em) {
        InsurancePricetableMap insurancePricetableMap = new InsurancePricetableMap()
            .insuranceIdNo(UPDATED_INSURANCE_ID_NO)
            .insuranceName(UPDATED_INSURANCE_NAME)
            .insurancePayerIdNo(UPDATED_INSURANCE_PAYER_ID_NO)
            .priceTableId(UPDATED_PRICE_TABLE_ID)
            .priceTableName(UPDATED_PRICE_TABLE_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .insurancePricetableMapUuid(UPDATED_INSURANCE_PRICETABLE_MAP_UUID)
            .insuranceId(UPDATED_INSURANCE_ID);
        return insurancePricetableMap;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(InsurancePricetableMap.class).block();
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
        insurancePricetableMap = createEntity(em);
    }

    @Test
    void createInsurancePricetableMap() throws Exception {
        int databaseSizeBeforeCreate = insurancePricetableMapRepository.findAll().collectList().block().size();
        // Create the InsurancePricetableMap
        InsurancePricetableMapDTO insurancePricetableMapDTO = insurancePricetableMapMapper.toDto(insurancePricetableMap);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(insurancePricetableMapDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the InsurancePricetableMap in the database
        List<InsurancePricetableMap> insurancePricetableMapList = insurancePricetableMapRepository.findAll().collectList().block();
        assertThat(insurancePricetableMapList).hasSize(databaseSizeBeforeCreate + 1);
        InsurancePricetableMap testInsurancePricetableMap = insurancePricetableMapList.get(insurancePricetableMapList.size() - 1);
        assertThat(testInsurancePricetableMap.getInsuranceIdNo()).isEqualTo(DEFAULT_INSURANCE_ID_NO);
        assertThat(testInsurancePricetableMap.getInsuranceName()).isEqualTo(DEFAULT_INSURANCE_NAME);
        assertThat(testInsurancePricetableMap.getInsurancePayerIdNo()).isEqualTo(DEFAULT_INSURANCE_PAYER_ID_NO);
        assertThat(testInsurancePricetableMap.getPriceTableId()).isEqualTo(DEFAULT_PRICE_TABLE_ID);
        assertThat(testInsurancePricetableMap.getPriceTableName()).isEqualTo(DEFAULT_PRICE_TABLE_NAME);
        assertThat(testInsurancePricetableMap.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testInsurancePricetableMap.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testInsurancePricetableMap.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testInsurancePricetableMap.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testInsurancePricetableMap.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testInsurancePricetableMap.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testInsurancePricetableMap.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testInsurancePricetableMap.getInsurancePricetableMapUuid()).isEqualTo(DEFAULT_INSURANCE_PRICETABLE_MAP_UUID);
        assertThat(testInsurancePricetableMap.getInsuranceId()).isEqualTo(DEFAULT_INSURANCE_ID);
    }

    @Test
    void createInsurancePricetableMapWithExistingId() throws Exception {
        // Create the InsurancePricetableMap with an existing ID
        insurancePricetableMap.setInsurancePricetableMapId(1L);
        InsurancePricetableMapDTO insurancePricetableMapDTO = insurancePricetableMapMapper.toDto(insurancePricetableMap);

        int databaseSizeBeforeCreate = insurancePricetableMapRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(insurancePricetableMapDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the InsurancePricetableMap in the database
        List<InsurancePricetableMap> insurancePricetableMapList = insurancePricetableMapRepository.findAll().collectList().block();
        assertThat(insurancePricetableMapList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllInsurancePricetableMaps() {
        // Initialize the database
        insurancePricetableMapRepository.save(insurancePricetableMap).block();

        // Get all the insurancePricetableMapList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=insurancePricetableMapId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].insurancePricetableMapId")
            .value(hasItem(insurancePricetableMap.getInsurancePricetableMapId().intValue()))
            .jsonPath("$.[*].insuranceIdNo")
            .value(hasItem(DEFAULT_INSURANCE_ID_NO))
            .jsonPath("$.[*].insuranceName")
            .value(hasItem(DEFAULT_INSURANCE_NAME))
            .jsonPath("$.[*].insurancePayerIdNo")
            .value(hasItem(DEFAULT_INSURANCE_PAYER_ID_NO))
            .jsonPath("$.[*].priceTableId")
            .value(hasItem(DEFAULT_PRICE_TABLE_ID.intValue()))
            .jsonPath("$.[*].priceTableName")
            .value(hasItem(DEFAULT_PRICE_TABLE_NAME))
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
            .jsonPath("$.[*].insurancePricetableMapUuid")
            .value(hasItem(DEFAULT_INSURANCE_PRICETABLE_MAP_UUID.toString()))
            .jsonPath("$.[*].insuranceId")
            .value(hasItem(DEFAULT_INSURANCE_ID.intValue()));
    }

    @Test
    void getInsurancePricetableMap() {
        // Initialize the database
        insurancePricetableMapRepository.save(insurancePricetableMap).block();

        // Get the insurancePricetableMap
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, insurancePricetableMap.getInsurancePricetableMapId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.insurancePricetableMapId")
            .value(is(insurancePricetableMap.getInsurancePricetableMapId().intValue()))
            .jsonPath("$.insuranceIdNo")
            .value(is(DEFAULT_INSURANCE_ID_NO))
            .jsonPath("$.insuranceName")
            .value(is(DEFAULT_INSURANCE_NAME))
            .jsonPath("$.insurancePayerIdNo")
            .value(is(DEFAULT_INSURANCE_PAYER_ID_NO))
            .jsonPath("$.priceTableId")
            .value(is(DEFAULT_PRICE_TABLE_ID.intValue()))
            .jsonPath("$.priceTableName")
            .value(is(DEFAULT_PRICE_TABLE_NAME))
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
            .jsonPath("$.insurancePricetableMapUuid")
            .value(is(DEFAULT_INSURANCE_PRICETABLE_MAP_UUID.toString()))
            .jsonPath("$.insuranceId")
            .value(is(DEFAULT_INSURANCE_ID.intValue()));
    }

    @Test
    void getNonExistingInsurancePricetableMap() {
        // Get the insurancePricetableMap
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingInsurancePricetableMap() throws Exception {
        // Initialize the database
        insurancePricetableMapRepository.save(insurancePricetableMap).block();

        int databaseSizeBeforeUpdate = insurancePricetableMapRepository.findAll().collectList().block().size();

        // Update the insurancePricetableMap
        InsurancePricetableMap updatedInsurancePricetableMap = insurancePricetableMapRepository
            .findById(insurancePricetableMap.getInsurancePricetableMapId())
            .block();
        updatedInsurancePricetableMap
            .insuranceIdNo(UPDATED_INSURANCE_ID_NO)
            .insuranceName(UPDATED_INSURANCE_NAME)
            .insurancePayerIdNo(UPDATED_INSURANCE_PAYER_ID_NO)
            .priceTableId(UPDATED_PRICE_TABLE_ID)
            .priceTableName(UPDATED_PRICE_TABLE_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .insurancePricetableMapUuid(UPDATED_INSURANCE_PRICETABLE_MAP_UUID)
            .insuranceId(UPDATED_INSURANCE_ID);
        InsurancePricetableMapDTO insurancePricetableMapDTO = insurancePricetableMapMapper.toDto(updatedInsurancePricetableMap);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, insurancePricetableMapDTO.getInsurancePricetableMapId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(insurancePricetableMapDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the InsurancePricetableMap in the database
        List<InsurancePricetableMap> insurancePricetableMapList = insurancePricetableMapRepository.findAll().collectList().block();
        assertThat(insurancePricetableMapList).hasSize(databaseSizeBeforeUpdate);
        InsurancePricetableMap testInsurancePricetableMap = insurancePricetableMapList.get(insurancePricetableMapList.size() - 1);
        assertThat(testInsurancePricetableMap.getInsuranceIdNo()).isEqualTo(UPDATED_INSURANCE_ID_NO);
        assertThat(testInsurancePricetableMap.getInsuranceName()).isEqualTo(UPDATED_INSURANCE_NAME);
        assertThat(testInsurancePricetableMap.getInsurancePayerIdNo()).isEqualTo(UPDATED_INSURANCE_PAYER_ID_NO);
        assertThat(testInsurancePricetableMap.getPriceTableId()).isEqualTo(UPDATED_PRICE_TABLE_ID);
        assertThat(testInsurancePricetableMap.getPriceTableName()).isEqualTo(UPDATED_PRICE_TABLE_NAME);
        assertThat(testInsurancePricetableMap.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testInsurancePricetableMap.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testInsurancePricetableMap.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testInsurancePricetableMap.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testInsurancePricetableMap.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testInsurancePricetableMap.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testInsurancePricetableMap.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testInsurancePricetableMap.getInsurancePricetableMapUuid()).isEqualTo(UPDATED_INSURANCE_PRICETABLE_MAP_UUID);
        assertThat(testInsurancePricetableMap.getInsuranceId()).isEqualTo(UPDATED_INSURANCE_ID);
    }

    @Test
    void putNonExistingInsurancePricetableMap() throws Exception {
        int databaseSizeBeforeUpdate = insurancePricetableMapRepository.findAll().collectList().block().size();
        insurancePricetableMap.setInsurancePricetableMapId(count.incrementAndGet());

        // Create the InsurancePricetableMap
        InsurancePricetableMapDTO insurancePricetableMapDTO = insurancePricetableMapMapper.toDto(insurancePricetableMap);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, insurancePricetableMapDTO.getInsurancePricetableMapId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(insurancePricetableMapDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the InsurancePricetableMap in the database
        List<InsurancePricetableMap> insurancePricetableMapList = insurancePricetableMapRepository.findAll().collectList().block();
        assertThat(insurancePricetableMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchInsurancePricetableMap() throws Exception {
        int databaseSizeBeforeUpdate = insurancePricetableMapRepository.findAll().collectList().block().size();
        insurancePricetableMap.setInsurancePricetableMapId(count.incrementAndGet());

        // Create the InsurancePricetableMap
        InsurancePricetableMapDTO insurancePricetableMapDTO = insurancePricetableMapMapper.toDto(insurancePricetableMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(insurancePricetableMapDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the InsurancePricetableMap in the database
        List<InsurancePricetableMap> insurancePricetableMapList = insurancePricetableMapRepository.findAll().collectList().block();
        assertThat(insurancePricetableMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamInsurancePricetableMap() throws Exception {
        int databaseSizeBeforeUpdate = insurancePricetableMapRepository.findAll().collectList().block().size();
        insurancePricetableMap.setInsurancePricetableMapId(count.incrementAndGet());

        // Create the InsurancePricetableMap
        InsurancePricetableMapDTO insurancePricetableMapDTO = insurancePricetableMapMapper.toDto(insurancePricetableMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(insurancePricetableMapDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the InsurancePricetableMap in the database
        List<InsurancePricetableMap> insurancePricetableMapList = insurancePricetableMapRepository.findAll().collectList().block();
        assertThat(insurancePricetableMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateInsurancePricetableMapWithPatch() throws Exception {
        // Initialize the database
        insurancePricetableMapRepository.save(insurancePricetableMap).block();

        int databaseSizeBeforeUpdate = insurancePricetableMapRepository.findAll().collectList().block().size();

        // Update the insurancePricetableMap using partial update
        InsurancePricetableMap partialUpdatedInsurancePricetableMap = new InsurancePricetableMap();
        partialUpdatedInsurancePricetableMap.setInsurancePricetableMapId(insurancePricetableMap.getInsurancePricetableMapId());

        partialUpdatedInsurancePricetableMap
            .insuranceIdNo(UPDATED_INSURANCE_ID_NO)
            .insuranceName(UPDATED_INSURANCE_NAME)
            .insurancePayerIdNo(UPDATED_INSURANCE_PAYER_ID_NO)
            .priceTableName(UPDATED_PRICE_TABLE_NAME)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .insurancePricetableMapUuid(UPDATED_INSURANCE_PRICETABLE_MAP_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedInsurancePricetableMap.getInsurancePricetableMapId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedInsurancePricetableMap))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the InsurancePricetableMap in the database
        List<InsurancePricetableMap> insurancePricetableMapList = insurancePricetableMapRepository.findAll().collectList().block();
        assertThat(insurancePricetableMapList).hasSize(databaseSizeBeforeUpdate);
        InsurancePricetableMap testInsurancePricetableMap = insurancePricetableMapList.get(insurancePricetableMapList.size() - 1);
        assertThat(testInsurancePricetableMap.getInsuranceIdNo()).isEqualTo(UPDATED_INSURANCE_ID_NO);
        assertThat(testInsurancePricetableMap.getInsuranceName()).isEqualTo(UPDATED_INSURANCE_NAME);
        assertThat(testInsurancePricetableMap.getInsurancePayerIdNo()).isEqualTo(UPDATED_INSURANCE_PAYER_ID_NO);
        assertThat(testInsurancePricetableMap.getPriceTableId()).isEqualTo(DEFAULT_PRICE_TABLE_ID);
        assertThat(testInsurancePricetableMap.getPriceTableName()).isEqualTo(UPDATED_PRICE_TABLE_NAME);
        assertThat(testInsurancePricetableMap.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testInsurancePricetableMap.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testInsurancePricetableMap.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testInsurancePricetableMap.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testInsurancePricetableMap.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testInsurancePricetableMap.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testInsurancePricetableMap.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testInsurancePricetableMap.getInsurancePricetableMapUuid()).isEqualTo(UPDATED_INSURANCE_PRICETABLE_MAP_UUID);
        assertThat(testInsurancePricetableMap.getInsuranceId()).isEqualTo(DEFAULT_INSURANCE_ID);
    }

    @Test
    void fullUpdateInsurancePricetableMapWithPatch() throws Exception {
        // Initialize the database
        insurancePricetableMapRepository.save(insurancePricetableMap).block();

        int databaseSizeBeforeUpdate = insurancePricetableMapRepository.findAll().collectList().block().size();

        // Update the insurancePricetableMap using partial update
        InsurancePricetableMap partialUpdatedInsurancePricetableMap = new InsurancePricetableMap();
        partialUpdatedInsurancePricetableMap.setInsurancePricetableMapId(insurancePricetableMap.getInsurancePricetableMapId());

        partialUpdatedInsurancePricetableMap
            .insuranceIdNo(UPDATED_INSURANCE_ID_NO)
            .insuranceName(UPDATED_INSURANCE_NAME)
            .insurancePayerIdNo(UPDATED_INSURANCE_PAYER_ID_NO)
            .priceTableId(UPDATED_PRICE_TABLE_ID)
            .priceTableName(UPDATED_PRICE_TABLE_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .insurancePricetableMapUuid(UPDATED_INSURANCE_PRICETABLE_MAP_UUID)
            .insuranceId(UPDATED_INSURANCE_ID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedInsurancePricetableMap.getInsurancePricetableMapId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedInsurancePricetableMap))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the InsurancePricetableMap in the database
        List<InsurancePricetableMap> insurancePricetableMapList = insurancePricetableMapRepository.findAll().collectList().block();
        assertThat(insurancePricetableMapList).hasSize(databaseSizeBeforeUpdate);
        InsurancePricetableMap testInsurancePricetableMap = insurancePricetableMapList.get(insurancePricetableMapList.size() - 1);
        assertThat(testInsurancePricetableMap.getInsuranceIdNo()).isEqualTo(UPDATED_INSURANCE_ID_NO);
        assertThat(testInsurancePricetableMap.getInsuranceName()).isEqualTo(UPDATED_INSURANCE_NAME);
        assertThat(testInsurancePricetableMap.getInsurancePayerIdNo()).isEqualTo(UPDATED_INSURANCE_PAYER_ID_NO);
        assertThat(testInsurancePricetableMap.getPriceTableId()).isEqualTo(UPDATED_PRICE_TABLE_ID);
        assertThat(testInsurancePricetableMap.getPriceTableName()).isEqualTo(UPDATED_PRICE_TABLE_NAME);
        assertThat(testInsurancePricetableMap.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testInsurancePricetableMap.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testInsurancePricetableMap.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testInsurancePricetableMap.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testInsurancePricetableMap.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testInsurancePricetableMap.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testInsurancePricetableMap.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testInsurancePricetableMap.getInsurancePricetableMapUuid()).isEqualTo(UPDATED_INSURANCE_PRICETABLE_MAP_UUID);
        assertThat(testInsurancePricetableMap.getInsuranceId()).isEqualTo(UPDATED_INSURANCE_ID);
    }

    @Test
    void patchNonExistingInsurancePricetableMap() throws Exception {
        int databaseSizeBeforeUpdate = insurancePricetableMapRepository.findAll().collectList().block().size();
        insurancePricetableMap.setInsurancePricetableMapId(count.incrementAndGet());

        // Create the InsurancePricetableMap
        InsurancePricetableMapDTO insurancePricetableMapDTO = insurancePricetableMapMapper.toDto(insurancePricetableMap);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, insurancePricetableMapDTO.getInsurancePricetableMapId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(insurancePricetableMapDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the InsurancePricetableMap in the database
        List<InsurancePricetableMap> insurancePricetableMapList = insurancePricetableMapRepository.findAll().collectList().block();
        assertThat(insurancePricetableMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchInsurancePricetableMap() throws Exception {
        int databaseSizeBeforeUpdate = insurancePricetableMapRepository.findAll().collectList().block().size();
        insurancePricetableMap.setInsurancePricetableMapId(count.incrementAndGet());

        // Create the InsurancePricetableMap
        InsurancePricetableMapDTO insurancePricetableMapDTO = insurancePricetableMapMapper.toDto(insurancePricetableMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(insurancePricetableMapDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the InsurancePricetableMap in the database
        List<InsurancePricetableMap> insurancePricetableMapList = insurancePricetableMapRepository.findAll().collectList().block();
        assertThat(insurancePricetableMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamInsurancePricetableMap() throws Exception {
        int databaseSizeBeforeUpdate = insurancePricetableMapRepository.findAll().collectList().block().size();
        insurancePricetableMap.setInsurancePricetableMapId(count.incrementAndGet());

        // Create the InsurancePricetableMap
        InsurancePricetableMapDTO insurancePricetableMapDTO = insurancePricetableMapMapper.toDto(insurancePricetableMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(insurancePricetableMapDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the InsurancePricetableMap in the database
        List<InsurancePricetableMap> insurancePricetableMapList = insurancePricetableMapRepository.findAll().collectList().block();
        assertThat(insurancePricetableMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteInsurancePricetableMap() {
        // Initialize the database
        insurancePricetableMapRepository.save(insurancePricetableMap).block();

        int databaseSizeBeforeDelete = insurancePricetableMapRepository.findAll().collectList().block().size();

        // Delete the insurancePricetableMap
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, insurancePricetableMap.getInsurancePricetableMapId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<InsurancePricetableMap> insurancePricetableMapList = insurancePricetableMapRepository.findAll().collectList().block();
        assertThat(insurancePricetableMapList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
