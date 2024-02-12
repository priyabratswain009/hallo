package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.PatientDocumentSoMap;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.PatientDocumentSoMapRepository;
import com.sunknowledge.dme.rcm.service.dto.PatientDocumentSoMapDTO;
import com.sunknowledge.dme.rcm.service.mapper.PatientDocumentSoMapMapper;
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
 * Integration tests for the {@link PatientDocumentSoMapResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class PatientDocumentSoMapResourceIT {

    private static final Long DEFAULT_PATIENT_ID = 1L;
    private static final Long UPDATED_PATIENT_ID = 2L;

    private static final String DEFAULT_PATIENT_ID_NO = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_ID_NO = "BBBBBBBBBB";

    private static final Long DEFAULT_PATIENT_DOCUMENT_ID = 1L;
    private static final Long UPDATED_PATIENT_DOCUMENT_ID = 2L;

    private static final String DEFAULT_PATIENT_DOCUMENT_NO = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_DOCUMENT_NO = "BBBBBBBBBB";

    private static final Long DEFAULT_SO_ID = 1L;
    private static final Long UPDATED_SO_ID = 2L;

    private static final String DEFAULT_SO_NO = "AAAAAAAAAA";
    private static final String UPDATED_SO_NO = "BBBBBBBBBB";

    private static final Long DEFAULT_UPLOADED_BY_ID = 1L;
    private static final Long UPDATED_UPLOADED_BY_ID = 2L;

    private static final String DEFAULT_UPLOADED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPLOADED_BY_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_UPLOADED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPLOADED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final UUID DEFAULT_PATIENT_DOCUMENT_SO_MAP_UUID = UUID.randomUUID();
    private static final UUID UPDATED_PATIENT_DOCUMENT_SO_MAP_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/patient-document-so-maps";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{patientDocumentSoMapId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PatientDocumentSoMapRepository patientDocumentSoMapRepository;

    @Autowired
    private PatientDocumentSoMapMapper patientDocumentSoMapMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private PatientDocumentSoMap patientDocumentSoMap;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PatientDocumentSoMap createEntity(EntityManager em) {
        PatientDocumentSoMap patientDocumentSoMap = new PatientDocumentSoMap()
            .patientId(DEFAULT_PATIENT_ID)
            .patientIdNo(DEFAULT_PATIENT_ID_NO)
            .patientDocumentId(DEFAULT_PATIENT_DOCUMENT_ID)
            .patientDocumentNo(DEFAULT_PATIENT_DOCUMENT_NO)
            .soId(DEFAULT_SO_ID)
            .soNo(DEFAULT_SO_NO)
            .uploadedById(DEFAULT_UPLOADED_BY_ID)
            .uploadedByName(DEFAULT_UPLOADED_BY_NAME)
            .uploadedDate(DEFAULT_UPLOADED_DATE)
            .status(DEFAULT_STATUS)
            .patientDocumentSoMapUuid(DEFAULT_PATIENT_DOCUMENT_SO_MAP_UUID);
        return patientDocumentSoMap;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PatientDocumentSoMap createUpdatedEntity(EntityManager em) {
        PatientDocumentSoMap patientDocumentSoMap = new PatientDocumentSoMap()
            .patientId(UPDATED_PATIENT_ID)
            .patientIdNo(UPDATED_PATIENT_ID_NO)
            .patientDocumentId(UPDATED_PATIENT_DOCUMENT_ID)
            .patientDocumentNo(UPDATED_PATIENT_DOCUMENT_NO)
            .soId(UPDATED_SO_ID)
            .soNo(UPDATED_SO_NO)
            .uploadedById(UPDATED_UPLOADED_BY_ID)
            .uploadedByName(UPDATED_UPLOADED_BY_NAME)
            .uploadedDate(UPDATED_UPLOADED_DATE)
            .status(UPDATED_STATUS)
            .patientDocumentSoMapUuid(UPDATED_PATIENT_DOCUMENT_SO_MAP_UUID);
        return patientDocumentSoMap;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(PatientDocumentSoMap.class).block();
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
        patientDocumentSoMap = createEntity(em);
    }

    @Test
    void createPatientDocumentSoMap() throws Exception {
        int databaseSizeBeforeCreate = patientDocumentSoMapRepository.findAll().collectList().block().size();
        // Create the PatientDocumentSoMap
        PatientDocumentSoMapDTO patientDocumentSoMapDTO = patientDocumentSoMapMapper.toDto(patientDocumentSoMap);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDocumentSoMapDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the PatientDocumentSoMap in the database
        List<PatientDocumentSoMap> patientDocumentSoMapList = patientDocumentSoMapRepository.findAll().collectList().block();
        assertThat(patientDocumentSoMapList).hasSize(databaseSizeBeforeCreate + 1);
        PatientDocumentSoMap testPatientDocumentSoMap = patientDocumentSoMapList.get(patientDocumentSoMapList.size() - 1);
        assertThat(testPatientDocumentSoMap.getPatientId()).isEqualTo(DEFAULT_PATIENT_ID);
        assertThat(testPatientDocumentSoMap.getPatientIdNo()).isEqualTo(DEFAULT_PATIENT_ID_NO);
        assertThat(testPatientDocumentSoMap.getPatientDocumentId()).isEqualTo(DEFAULT_PATIENT_DOCUMENT_ID);
        assertThat(testPatientDocumentSoMap.getPatientDocumentNo()).isEqualTo(DEFAULT_PATIENT_DOCUMENT_NO);
        assertThat(testPatientDocumentSoMap.getSoId()).isEqualTo(DEFAULT_SO_ID);
        assertThat(testPatientDocumentSoMap.getSoNo()).isEqualTo(DEFAULT_SO_NO);
        assertThat(testPatientDocumentSoMap.getUploadedById()).isEqualTo(DEFAULT_UPLOADED_BY_ID);
        assertThat(testPatientDocumentSoMap.getUploadedByName()).isEqualTo(DEFAULT_UPLOADED_BY_NAME);
        assertThat(testPatientDocumentSoMap.getUploadedDate()).isEqualTo(DEFAULT_UPLOADED_DATE);
        assertThat(testPatientDocumentSoMap.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPatientDocumentSoMap.getPatientDocumentSoMapUuid()).isEqualTo(DEFAULT_PATIENT_DOCUMENT_SO_MAP_UUID);
    }

    @Test
    void createPatientDocumentSoMapWithExistingId() throws Exception {
        // Create the PatientDocumentSoMap with an existing ID
        patientDocumentSoMap.setPatientDocumentSoMapId(1L);
        PatientDocumentSoMapDTO patientDocumentSoMapDTO = patientDocumentSoMapMapper.toDto(patientDocumentSoMap);

        int databaseSizeBeforeCreate = patientDocumentSoMapRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDocumentSoMapDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDocumentSoMap in the database
        List<PatientDocumentSoMap> patientDocumentSoMapList = patientDocumentSoMapRepository.findAll().collectList().block();
        assertThat(patientDocumentSoMapList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllPatientDocumentSoMaps() {
        // Initialize the database
        patientDocumentSoMapRepository.save(patientDocumentSoMap).block();

        // Get all the patientDocumentSoMapList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=patientDocumentSoMapId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].patientDocumentSoMapId")
            .value(hasItem(patientDocumentSoMap.getPatientDocumentSoMapId().intValue()))
            .jsonPath("$.[*].patientId")
            .value(hasItem(DEFAULT_PATIENT_ID.intValue()))
            .jsonPath("$.[*].patientIdNo")
            .value(hasItem(DEFAULT_PATIENT_ID_NO))
            .jsonPath("$.[*].patientDocumentId")
            .value(hasItem(DEFAULT_PATIENT_DOCUMENT_ID.intValue()))
            .jsonPath("$.[*].patientDocumentNo")
            .value(hasItem(DEFAULT_PATIENT_DOCUMENT_NO))
            .jsonPath("$.[*].soId")
            .value(hasItem(DEFAULT_SO_ID.intValue()))
            .jsonPath("$.[*].soNo")
            .value(hasItem(DEFAULT_SO_NO))
            .jsonPath("$.[*].uploadedById")
            .value(hasItem(DEFAULT_UPLOADED_BY_ID.intValue()))
            .jsonPath("$.[*].uploadedByName")
            .value(hasItem(DEFAULT_UPLOADED_BY_NAME))
            .jsonPath("$.[*].uploadedDate")
            .value(hasItem(DEFAULT_UPLOADED_DATE.toString()))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS))
            .jsonPath("$.[*].patientDocumentSoMapUuid")
            .value(hasItem(DEFAULT_PATIENT_DOCUMENT_SO_MAP_UUID.toString()));
    }

    @Test
    void getPatientDocumentSoMap() {
        // Initialize the database
        patientDocumentSoMapRepository.save(patientDocumentSoMap).block();

        // Get the patientDocumentSoMap
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, patientDocumentSoMap.getPatientDocumentSoMapId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.patientDocumentSoMapId")
            .value(is(patientDocumentSoMap.getPatientDocumentSoMapId().intValue()))
            .jsonPath("$.patientId")
            .value(is(DEFAULT_PATIENT_ID.intValue()))
            .jsonPath("$.patientIdNo")
            .value(is(DEFAULT_PATIENT_ID_NO))
            .jsonPath("$.patientDocumentId")
            .value(is(DEFAULT_PATIENT_DOCUMENT_ID.intValue()))
            .jsonPath("$.patientDocumentNo")
            .value(is(DEFAULT_PATIENT_DOCUMENT_NO))
            .jsonPath("$.soId")
            .value(is(DEFAULT_SO_ID.intValue()))
            .jsonPath("$.soNo")
            .value(is(DEFAULT_SO_NO))
            .jsonPath("$.uploadedById")
            .value(is(DEFAULT_UPLOADED_BY_ID.intValue()))
            .jsonPath("$.uploadedByName")
            .value(is(DEFAULT_UPLOADED_BY_NAME))
            .jsonPath("$.uploadedDate")
            .value(is(DEFAULT_UPLOADED_DATE.toString()))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS))
            .jsonPath("$.patientDocumentSoMapUuid")
            .value(is(DEFAULT_PATIENT_DOCUMENT_SO_MAP_UUID.toString()));
    }

    @Test
    void getNonExistingPatientDocumentSoMap() {
        // Get the patientDocumentSoMap
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingPatientDocumentSoMap() throws Exception {
        // Initialize the database
        patientDocumentSoMapRepository.save(patientDocumentSoMap).block();

        int databaseSizeBeforeUpdate = patientDocumentSoMapRepository.findAll().collectList().block().size();

        // Update the patientDocumentSoMap
        PatientDocumentSoMap updatedPatientDocumentSoMap = patientDocumentSoMapRepository
            .findById(patientDocumentSoMap.getPatientDocumentSoMapId())
            .block();
        updatedPatientDocumentSoMap
            .patientId(UPDATED_PATIENT_ID)
            .patientIdNo(UPDATED_PATIENT_ID_NO)
            .patientDocumentId(UPDATED_PATIENT_DOCUMENT_ID)
            .patientDocumentNo(UPDATED_PATIENT_DOCUMENT_NO)
            .soId(UPDATED_SO_ID)
            .soNo(UPDATED_SO_NO)
            .uploadedById(UPDATED_UPLOADED_BY_ID)
            .uploadedByName(UPDATED_UPLOADED_BY_NAME)
            .uploadedDate(UPDATED_UPLOADED_DATE)
            .status(UPDATED_STATUS)
            .patientDocumentSoMapUuid(UPDATED_PATIENT_DOCUMENT_SO_MAP_UUID);
        PatientDocumentSoMapDTO patientDocumentSoMapDTO = patientDocumentSoMapMapper.toDto(updatedPatientDocumentSoMap);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, patientDocumentSoMapDTO.getPatientDocumentSoMapId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDocumentSoMapDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientDocumentSoMap in the database
        List<PatientDocumentSoMap> patientDocumentSoMapList = patientDocumentSoMapRepository.findAll().collectList().block();
        assertThat(patientDocumentSoMapList).hasSize(databaseSizeBeforeUpdate);
        PatientDocumentSoMap testPatientDocumentSoMap = patientDocumentSoMapList.get(patientDocumentSoMapList.size() - 1);
        assertThat(testPatientDocumentSoMap.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testPatientDocumentSoMap.getPatientIdNo()).isEqualTo(UPDATED_PATIENT_ID_NO);
        assertThat(testPatientDocumentSoMap.getPatientDocumentId()).isEqualTo(UPDATED_PATIENT_DOCUMENT_ID);
        assertThat(testPatientDocumentSoMap.getPatientDocumentNo()).isEqualTo(UPDATED_PATIENT_DOCUMENT_NO);
        assertThat(testPatientDocumentSoMap.getSoId()).isEqualTo(UPDATED_SO_ID);
        assertThat(testPatientDocumentSoMap.getSoNo()).isEqualTo(UPDATED_SO_NO);
        assertThat(testPatientDocumentSoMap.getUploadedById()).isEqualTo(UPDATED_UPLOADED_BY_ID);
        assertThat(testPatientDocumentSoMap.getUploadedByName()).isEqualTo(UPDATED_UPLOADED_BY_NAME);
        assertThat(testPatientDocumentSoMap.getUploadedDate()).isEqualTo(UPDATED_UPLOADED_DATE);
        assertThat(testPatientDocumentSoMap.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPatientDocumentSoMap.getPatientDocumentSoMapUuid()).isEqualTo(UPDATED_PATIENT_DOCUMENT_SO_MAP_UUID);
    }

    @Test
    void putNonExistingPatientDocumentSoMap() throws Exception {
        int databaseSizeBeforeUpdate = patientDocumentSoMapRepository.findAll().collectList().block().size();
        patientDocumentSoMap.setPatientDocumentSoMapId(count.incrementAndGet());

        // Create the PatientDocumentSoMap
        PatientDocumentSoMapDTO patientDocumentSoMapDTO = patientDocumentSoMapMapper.toDto(patientDocumentSoMap);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, patientDocumentSoMapDTO.getPatientDocumentSoMapId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDocumentSoMapDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDocumentSoMap in the database
        List<PatientDocumentSoMap> patientDocumentSoMapList = patientDocumentSoMapRepository.findAll().collectList().block();
        assertThat(patientDocumentSoMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchPatientDocumentSoMap() throws Exception {
        int databaseSizeBeforeUpdate = patientDocumentSoMapRepository.findAll().collectList().block().size();
        patientDocumentSoMap.setPatientDocumentSoMapId(count.incrementAndGet());

        // Create the PatientDocumentSoMap
        PatientDocumentSoMapDTO patientDocumentSoMapDTO = patientDocumentSoMapMapper.toDto(patientDocumentSoMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDocumentSoMapDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDocumentSoMap in the database
        List<PatientDocumentSoMap> patientDocumentSoMapList = patientDocumentSoMapRepository.findAll().collectList().block();
        assertThat(patientDocumentSoMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamPatientDocumentSoMap() throws Exception {
        int databaseSizeBeforeUpdate = patientDocumentSoMapRepository.findAll().collectList().block().size();
        patientDocumentSoMap.setPatientDocumentSoMapId(count.incrementAndGet());

        // Create the PatientDocumentSoMap
        PatientDocumentSoMapDTO patientDocumentSoMapDTO = patientDocumentSoMapMapper.toDto(patientDocumentSoMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDocumentSoMapDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PatientDocumentSoMap in the database
        List<PatientDocumentSoMap> patientDocumentSoMapList = patientDocumentSoMapRepository.findAll().collectList().block();
        assertThat(patientDocumentSoMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdatePatientDocumentSoMapWithPatch() throws Exception {
        // Initialize the database
        patientDocumentSoMapRepository.save(patientDocumentSoMap).block();

        int databaseSizeBeforeUpdate = patientDocumentSoMapRepository.findAll().collectList().block().size();

        // Update the patientDocumentSoMap using partial update
        PatientDocumentSoMap partialUpdatedPatientDocumentSoMap = new PatientDocumentSoMap();
        partialUpdatedPatientDocumentSoMap.setPatientDocumentSoMapId(patientDocumentSoMap.getPatientDocumentSoMapId());

        partialUpdatedPatientDocumentSoMap
            .patientId(UPDATED_PATIENT_ID)
            .patientIdNo(UPDATED_PATIENT_ID_NO)
            .patientDocumentId(UPDATED_PATIENT_DOCUMENT_ID)
            .patientDocumentNo(UPDATED_PATIENT_DOCUMENT_NO)
            .soNo(UPDATED_SO_NO)
            .uploadedById(UPDATED_UPLOADED_BY_ID)
            .uploadedByName(UPDATED_UPLOADED_BY_NAME);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPatientDocumentSoMap.getPatientDocumentSoMapId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPatientDocumentSoMap))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientDocumentSoMap in the database
        List<PatientDocumentSoMap> patientDocumentSoMapList = patientDocumentSoMapRepository.findAll().collectList().block();
        assertThat(patientDocumentSoMapList).hasSize(databaseSizeBeforeUpdate);
        PatientDocumentSoMap testPatientDocumentSoMap = patientDocumentSoMapList.get(patientDocumentSoMapList.size() - 1);
        assertThat(testPatientDocumentSoMap.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testPatientDocumentSoMap.getPatientIdNo()).isEqualTo(UPDATED_PATIENT_ID_NO);
        assertThat(testPatientDocumentSoMap.getPatientDocumentId()).isEqualTo(UPDATED_PATIENT_DOCUMENT_ID);
        assertThat(testPatientDocumentSoMap.getPatientDocumentNo()).isEqualTo(UPDATED_PATIENT_DOCUMENT_NO);
        assertThat(testPatientDocumentSoMap.getSoId()).isEqualTo(DEFAULT_SO_ID);
        assertThat(testPatientDocumentSoMap.getSoNo()).isEqualTo(UPDATED_SO_NO);
        assertThat(testPatientDocumentSoMap.getUploadedById()).isEqualTo(UPDATED_UPLOADED_BY_ID);
        assertThat(testPatientDocumentSoMap.getUploadedByName()).isEqualTo(UPDATED_UPLOADED_BY_NAME);
        assertThat(testPatientDocumentSoMap.getUploadedDate()).isEqualTo(DEFAULT_UPLOADED_DATE);
        assertThat(testPatientDocumentSoMap.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPatientDocumentSoMap.getPatientDocumentSoMapUuid()).isEqualTo(DEFAULT_PATIENT_DOCUMENT_SO_MAP_UUID);
    }

    @Test
    void fullUpdatePatientDocumentSoMapWithPatch() throws Exception {
        // Initialize the database
        patientDocumentSoMapRepository.save(patientDocumentSoMap).block();

        int databaseSizeBeforeUpdate = patientDocumentSoMapRepository.findAll().collectList().block().size();

        // Update the patientDocumentSoMap using partial update
        PatientDocumentSoMap partialUpdatedPatientDocumentSoMap = new PatientDocumentSoMap();
        partialUpdatedPatientDocumentSoMap.setPatientDocumentSoMapId(patientDocumentSoMap.getPatientDocumentSoMapId());

        partialUpdatedPatientDocumentSoMap
            .patientId(UPDATED_PATIENT_ID)
            .patientIdNo(UPDATED_PATIENT_ID_NO)
            .patientDocumentId(UPDATED_PATIENT_DOCUMENT_ID)
            .patientDocumentNo(UPDATED_PATIENT_DOCUMENT_NO)
            .soId(UPDATED_SO_ID)
            .soNo(UPDATED_SO_NO)
            .uploadedById(UPDATED_UPLOADED_BY_ID)
            .uploadedByName(UPDATED_UPLOADED_BY_NAME)
            .uploadedDate(UPDATED_UPLOADED_DATE)
            .status(UPDATED_STATUS)
            .patientDocumentSoMapUuid(UPDATED_PATIENT_DOCUMENT_SO_MAP_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPatientDocumentSoMap.getPatientDocumentSoMapId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPatientDocumentSoMap))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientDocumentSoMap in the database
        List<PatientDocumentSoMap> patientDocumentSoMapList = patientDocumentSoMapRepository.findAll().collectList().block();
        assertThat(patientDocumentSoMapList).hasSize(databaseSizeBeforeUpdate);
        PatientDocumentSoMap testPatientDocumentSoMap = patientDocumentSoMapList.get(patientDocumentSoMapList.size() - 1);
        assertThat(testPatientDocumentSoMap.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testPatientDocumentSoMap.getPatientIdNo()).isEqualTo(UPDATED_PATIENT_ID_NO);
        assertThat(testPatientDocumentSoMap.getPatientDocumentId()).isEqualTo(UPDATED_PATIENT_DOCUMENT_ID);
        assertThat(testPatientDocumentSoMap.getPatientDocumentNo()).isEqualTo(UPDATED_PATIENT_DOCUMENT_NO);
        assertThat(testPatientDocumentSoMap.getSoId()).isEqualTo(UPDATED_SO_ID);
        assertThat(testPatientDocumentSoMap.getSoNo()).isEqualTo(UPDATED_SO_NO);
        assertThat(testPatientDocumentSoMap.getUploadedById()).isEqualTo(UPDATED_UPLOADED_BY_ID);
        assertThat(testPatientDocumentSoMap.getUploadedByName()).isEqualTo(UPDATED_UPLOADED_BY_NAME);
        assertThat(testPatientDocumentSoMap.getUploadedDate()).isEqualTo(UPDATED_UPLOADED_DATE);
        assertThat(testPatientDocumentSoMap.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPatientDocumentSoMap.getPatientDocumentSoMapUuid()).isEqualTo(UPDATED_PATIENT_DOCUMENT_SO_MAP_UUID);
    }

    @Test
    void patchNonExistingPatientDocumentSoMap() throws Exception {
        int databaseSizeBeforeUpdate = patientDocumentSoMapRepository.findAll().collectList().block().size();
        patientDocumentSoMap.setPatientDocumentSoMapId(count.incrementAndGet());

        // Create the PatientDocumentSoMap
        PatientDocumentSoMapDTO patientDocumentSoMapDTO = patientDocumentSoMapMapper.toDto(patientDocumentSoMap);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, patientDocumentSoMapDTO.getPatientDocumentSoMapId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDocumentSoMapDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDocumentSoMap in the database
        List<PatientDocumentSoMap> patientDocumentSoMapList = patientDocumentSoMapRepository.findAll().collectList().block();
        assertThat(patientDocumentSoMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchPatientDocumentSoMap() throws Exception {
        int databaseSizeBeforeUpdate = patientDocumentSoMapRepository.findAll().collectList().block().size();
        patientDocumentSoMap.setPatientDocumentSoMapId(count.incrementAndGet());

        // Create the PatientDocumentSoMap
        PatientDocumentSoMapDTO patientDocumentSoMapDTO = patientDocumentSoMapMapper.toDto(patientDocumentSoMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDocumentSoMapDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDocumentSoMap in the database
        List<PatientDocumentSoMap> patientDocumentSoMapList = patientDocumentSoMapRepository.findAll().collectList().block();
        assertThat(patientDocumentSoMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamPatientDocumentSoMap() throws Exception {
        int databaseSizeBeforeUpdate = patientDocumentSoMapRepository.findAll().collectList().block().size();
        patientDocumentSoMap.setPatientDocumentSoMapId(count.incrementAndGet());

        // Create the PatientDocumentSoMap
        PatientDocumentSoMapDTO patientDocumentSoMapDTO = patientDocumentSoMapMapper.toDto(patientDocumentSoMap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDocumentSoMapDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PatientDocumentSoMap in the database
        List<PatientDocumentSoMap> patientDocumentSoMapList = patientDocumentSoMapRepository.findAll().collectList().block();
        assertThat(patientDocumentSoMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deletePatientDocumentSoMap() {
        // Initialize the database
        patientDocumentSoMapRepository.save(patientDocumentSoMap).block();

        int databaseSizeBeforeDelete = patientDocumentSoMapRepository.findAll().collectList().block().size();

        // Delete the patientDocumentSoMap
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, patientDocumentSoMap.getPatientDocumentSoMapId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<PatientDocumentSoMap> patientDocumentSoMapList = patientDocumentSoMapRepository.findAll().collectList().block();
        assertThat(patientDocumentSoMapList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
