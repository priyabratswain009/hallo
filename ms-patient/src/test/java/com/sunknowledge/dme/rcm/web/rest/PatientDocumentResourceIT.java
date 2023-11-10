package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.PatientDocument;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.PatientDocumentRepository;
import com.sunknowledge.dme.rcm.service.dto.PatientDocumentDTO;
import com.sunknowledge.dme.rcm.service.mapper.PatientDocumentMapper;
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
 * Integration tests for the {@link PatientDocumentResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class PatientDocumentResourceIT {

    private static final String DEFAULT_PATIENT_DOCUMENT_NO = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_DOCUMENT_NO = "BBBBBBBBBB";

    private static final Long DEFAULT_PATIENT_ID = 1L;
    private static final Long UPDATED_PATIENT_ID = 2L;

    private static final UUID DEFAULT_PATIENT_UUID = UUID.randomUUID();
    private static final UUID UPDATED_PATIENT_UUID = UUID.randomUUID();

    private static final String DEFAULT_PATIENT_ID_NO = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_ID_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_DOCUMENT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_DOCUMENT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_DOCUMENT_REPOSITORY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_DOCUMENT_REPOSITORY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_DOCUMENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_DOCUMENT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_DOCUMENT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_DOCUMENT_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_DOCUMENT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_DOCUMENT_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_UPLOADED_BY_ID = 1L;
    private static final Long UPDATED_UPLOADED_BY_ID = 2L;

    private static final String DEFAULT_UPLOADED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPLOADED_BY_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_UPLOADED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPLOADED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final UUID DEFAULT_PATIENT_DOCUMENT_UUID = UUID.randomUUID();
    private static final UUID UPDATED_PATIENT_DOCUMENT_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/patient-documents";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{patientDocumentId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PatientDocumentRepository patientDocumentRepository;

    @Autowired
    private PatientDocumentMapper patientDocumentMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private PatientDocument patientDocument;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PatientDocument createEntity(EntityManager em) {
        PatientDocument patientDocument = new PatientDocument()
            .patientDocumentNo(DEFAULT_PATIENT_DOCUMENT_NO)
            .patientId(DEFAULT_PATIENT_ID)
            .patientUuid(DEFAULT_PATIENT_UUID)
            .patientIdNo(DEFAULT_PATIENT_ID_NO)
            .patientDocumentType(DEFAULT_PATIENT_DOCUMENT_TYPE)
            .patientDocumentRepositoryName(DEFAULT_PATIENT_DOCUMENT_REPOSITORY_NAME)
            .patientDocumentName(DEFAULT_PATIENT_DOCUMENT_NAME)
            .patientDocumentDescription(DEFAULT_PATIENT_DOCUMENT_DESCRIPTION)
            .patientDocumentStatus(DEFAULT_PATIENT_DOCUMENT_STATUS)
            .uploadedById(DEFAULT_UPLOADED_BY_ID)
            .uploadedByName(DEFAULT_UPLOADED_BY_NAME)
            .uploadedDate(DEFAULT_UPLOADED_DATE)
            .status(DEFAULT_STATUS)
            .patientDocumentUuid(DEFAULT_PATIENT_DOCUMENT_UUID);
        return patientDocument;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PatientDocument createUpdatedEntity(EntityManager em) {
        PatientDocument patientDocument = new PatientDocument()
            .patientDocumentNo(UPDATED_PATIENT_DOCUMENT_NO)
            .patientId(UPDATED_PATIENT_ID)
            .patientUuid(UPDATED_PATIENT_UUID)
            .patientIdNo(UPDATED_PATIENT_ID_NO)
            .patientDocumentType(UPDATED_PATIENT_DOCUMENT_TYPE)
            .patientDocumentRepositoryName(UPDATED_PATIENT_DOCUMENT_REPOSITORY_NAME)
            .patientDocumentName(UPDATED_PATIENT_DOCUMENT_NAME)
            .patientDocumentDescription(UPDATED_PATIENT_DOCUMENT_DESCRIPTION)
            .patientDocumentStatus(UPDATED_PATIENT_DOCUMENT_STATUS)
            .uploadedById(UPDATED_UPLOADED_BY_ID)
            .uploadedByName(UPDATED_UPLOADED_BY_NAME)
            .uploadedDate(UPDATED_UPLOADED_DATE)
            .status(UPDATED_STATUS)
            .patientDocumentUuid(UPDATED_PATIENT_DOCUMENT_UUID);
        return patientDocument;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(PatientDocument.class).block();
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
        patientDocument = createEntity(em);
    }

    @Test
    void createPatientDocument() throws Exception {
        int databaseSizeBeforeCreate = patientDocumentRepository.findAll().collectList().block().size();
        // Create the PatientDocument
        PatientDocumentDTO patientDocumentDTO = patientDocumentMapper.toDto(patientDocument);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDocumentDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the PatientDocument in the database
        List<PatientDocument> patientDocumentList = patientDocumentRepository.findAll().collectList().block();
        assertThat(patientDocumentList).hasSize(databaseSizeBeforeCreate + 1);
        PatientDocument testPatientDocument = patientDocumentList.get(patientDocumentList.size() - 1);
        assertThat(testPatientDocument.getPatientDocumentNo()).isEqualTo(DEFAULT_PATIENT_DOCUMENT_NO);
        assertThat(testPatientDocument.getPatientId()).isEqualTo(DEFAULT_PATIENT_ID);
        assertThat(testPatientDocument.getPatientUuid()).isEqualTo(DEFAULT_PATIENT_UUID);
        assertThat(testPatientDocument.getPatientIdNo()).isEqualTo(DEFAULT_PATIENT_ID_NO);
        assertThat(testPatientDocument.getPatientDocumentType()).isEqualTo(DEFAULT_PATIENT_DOCUMENT_TYPE);
        assertThat(testPatientDocument.getPatientDocumentRepositoryName()).isEqualTo(DEFAULT_PATIENT_DOCUMENT_REPOSITORY_NAME);
        assertThat(testPatientDocument.getPatientDocumentName()).isEqualTo(DEFAULT_PATIENT_DOCUMENT_NAME);
        assertThat(testPatientDocument.getPatientDocumentDescription()).isEqualTo(DEFAULT_PATIENT_DOCUMENT_DESCRIPTION);
        assertThat(testPatientDocument.getPatientDocumentStatus()).isEqualTo(DEFAULT_PATIENT_DOCUMENT_STATUS);
        assertThat(testPatientDocument.getUploadedById()).isEqualTo(DEFAULT_UPLOADED_BY_ID);
        assertThat(testPatientDocument.getUploadedByName()).isEqualTo(DEFAULT_UPLOADED_BY_NAME);
        assertThat(testPatientDocument.getUploadedDate()).isEqualTo(DEFAULT_UPLOADED_DATE);
        assertThat(testPatientDocument.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPatientDocument.getPatientDocumentUuid()).isEqualTo(DEFAULT_PATIENT_DOCUMENT_UUID);
    }

    @Test
    void createPatientDocumentWithExistingId() throws Exception {
        // Create the PatientDocument with an existing ID
        patientDocument.setPatientDocumentId(1L);
        PatientDocumentDTO patientDocumentDTO = patientDocumentMapper.toDto(patientDocument);

        int databaseSizeBeforeCreate = patientDocumentRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDocumentDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDocument in the database
        List<PatientDocument> patientDocumentList = patientDocumentRepository.findAll().collectList().block();
        assertThat(patientDocumentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllPatientDocuments() {
        // Initialize the database
        patientDocumentRepository.save(patientDocument).block();

        // Get all the patientDocumentList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=patientDocumentId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].patientDocumentId")
            .value(hasItem(patientDocument.getPatientDocumentId().intValue()))
            .jsonPath("$.[*].patientDocumentNo")
            .value(hasItem(DEFAULT_PATIENT_DOCUMENT_NO))
            .jsonPath("$.[*].patientId")
            .value(hasItem(DEFAULT_PATIENT_ID.intValue()))
            .jsonPath("$.[*].patientUuid")
            .value(hasItem(DEFAULT_PATIENT_UUID.toString()))
            .jsonPath("$.[*].patientIdNo")
            .value(hasItem(DEFAULT_PATIENT_ID_NO))
            .jsonPath("$.[*].patientDocumentType")
            .value(hasItem(DEFAULT_PATIENT_DOCUMENT_TYPE))
            .jsonPath("$.[*].patientDocumentRepositoryName")
            .value(hasItem(DEFAULT_PATIENT_DOCUMENT_REPOSITORY_NAME))
            .jsonPath("$.[*].patientDocumentName")
            .value(hasItem(DEFAULT_PATIENT_DOCUMENT_NAME))
            .jsonPath("$.[*].patientDocumentDescription")
            .value(hasItem(DEFAULT_PATIENT_DOCUMENT_DESCRIPTION))
            .jsonPath("$.[*].patientDocumentStatus")
            .value(hasItem(DEFAULT_PATIENT_DOCUMENT_STATUS))
            .jsonPath("$.[*].uploadedById")
            .value(hasItem(DEFAULT_UPLOADED_BY_ID.intValue()))
            .jsonPath("$.[*].uploadedByName")
            .value(hasItem(DEFAULT_UPLOADED_BY_NAME))
            .jsonPath("$.[*].uploadedDate")
            .value(hasItem(DEFAULT_UPLOADED_DATE.toString()))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS))
            .jsonPath("$.[*].patientDocumentUuid")
            .value(hasItem(DEFAULT_PATIENT_DOCUMENT_UUID.toString()));
    }

    @Test
    void getPatientDocument() {
        // Initialize the database
        patientDocumentRepository.save(patientDocument).block();

        // Get the patientDocument
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, patientDocument.getPatientDocumentId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.patientDocumentId")
            .value(is(patientDocument.getPatientDocumentId().intValue()))
            .jsonPath("$.patientDocumentNo")
            .value(is(DEFAULT_PATIENT_DOCUMENT_NO))
            .jsonPath("$.patientId")
            .value(is(DEFAULT_PATIENT_ID.intValue()))
            .jsonPath("$.patientUuid")
            .value(is(DEFAULT_PATIENT_UUID.toString()))
            .jsonPath("$.patientIdNo")
            .value(is(DEFAULT_PATIENT_ID_NO))
            .jsonPath("$.patientDocumentType")
            .value(is(DEFAULT_PATIENT_DOCUMENT_TYPE))
            .jsonPath("$.patientDocumentRepositoryName")
            .value(is(DEFAULT_PATIENT_DOCUMENT_REPOSITORY_NAME))
            .jsonPath("$.patientDocumentName")
            .value(is(DEFAULT_PATIENT_DOCUMENT_NAME))
            .jsonPath("$.patientDocumentDescription")
            .value(is(DEFAULT_PATIENT_DOCUMENT_DESCRIPTION))
            .jsonPath("$.patientDocumentStatus")
            .value(is(DEFAULT_PATIENT_DOCUMENT_STATUS))
            .jsonPath("$.uploadedById")
            .value(is(DEFAULT_UPLOADED_BY_ID.intValue()))
            .jsonPath("$.uploadedByName")
            .value(is(DEFAULT_UPLOADED_BY_NAME))
            .jsonPath("$.uploadedDate")
            .value(is(DEFAULT_UPLOADED_DATE.toString()))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS))
            .jsonPath("$.patientDocumentUuid")
            .value(is(DEFAULT_PATIENT_DOCUMENT_UUID.toString()));
    }

    @Test
    void getNonExistingPatientDocument() {
        // Get the patientDocument
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingPatientDocument() throws Exception {
        // Initialize the database
        patientDocumentRepository.save(patientDocument).block();

        int databaseSizeBeforeUpdate = patientDocumentRepository.findAll().collectList().block().size();

        // Update the patientDocument
        PatientDocument updatedPatientDocument = patientDocumentRepository.findById(patientDocument.getPatientDocumentId()).block();
        updatedPatientDocument
            .patientDocumentNo(UPDATED_PATIENT_DOCUMENT_NO)
            .patientId(UPDATED_PATIENT_ID)
            .patientUuid(UPDATED_PATIENT_UUID)
            .patientIdNo(UPDATED_PATIENT_ID_NO)
            .patientDocumentType(UPDATED_PATIENT_DOCUMENT_TYPE)
            .patientDocumentRepositoryName(UPDATED_PATIENT_DOCUMENT_REPOSITORY_NAME)
            .patientDocumentName(UPDATED_PATIENT_DOCUMENT_NAME)
            .patientDocumentDescription(UPDATED_PATIENT_DOCUMENT_DESCRIPTION)
            .patientDocumentStatus(UPDATED_PATIENT_DOCUMENT_STATUS)
            .uploadedById(UPDATED_UPLOADED_BY_ID)
            .uploadedByName(UPDATED_UPLOADED_BY_NAME)
            .uploadedDate(UPDATED_UPLOADED_DATE)
            .status(UPDATED_STATUS)
            .patientDocumentUuid(UPDATED_PATIENT_DOCUMENT_UUID);
        PatientDocumentDTO patientDocumentDTO = patientDocumentMapper.toDto(updatedPatientDocument);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, patientDocumentDTO.getPatientDocumentId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDocumentDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientDocument in the database
        List<PatientDocument> patientDocumentList = patientDocumentRepository.findAll().collectList().block();
        assertThat(patientDocumentList).hasSize(databaseSizeBeforeUpdate);
        PatientDocument testPatientDocument = patientDocumentList.get(patientDocumentList.size() - 1);
        assertThat(testPatientDocument.getPatientDocumentNo()).isEqualTo(UPDATED_PATIENT_DOCUMENT_NO);
        assertThat(testPatientDocument.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testPatientDocument.getPatientUuid()).isEqualTo(UPDATED_PATIENT_UUID);
        assertThat(testPatientDocument.getPatientIdNo()).isEqualTo(UPDATED_PATIENT_ID_NO);
        assertThat(testPatientDocument.getPatientDocumentType()).isEqualTo(UPDATED_PATIENT_DOCUMENT_TYPE);
        assertThat(testPatientDocument.getPatientDocumentRepositoryName()).isEqualTo(UPDATED_PATIENT_DOCUMENT_REPOSITORY_NAME);
        assertThat(testPatientDocument.getPatientDocumentName()).isEqualTo(UPDATED_PATIENT_DOCUMENT_NAME);
        assertThat(testPatientDocument.getPatientDocumentDescription()).isEqualTo(UPDATED_PATIENT_DOCUMENT_DESCRIPTION);
        assertThat(testPatientDocument.getPatientDocumentStatus()).isEqualTo(UPDATED_PATIENT_DOCUMENT_STATUS);
        assertThat(testPatientDocument.getUploadedById()).isEqualTo(UPDATED_UPLOADED_BY_ID);
        assertThat(testPatientDocument.getUploadedByName()).isEqualTo(UPDATED_UPLOADED_BY_NAME);
        assertThat(testPatientDocument.getUploadedDate()).isEqualTo(UPDATED_UPLOADED_DATE);
        assertThat(testPatientDocument.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPatientDocument.getPatientDocumentUuid()).isEqualTo(UPDATED_PATIENT_DOCUMENT_UUID);
    }

    @Test
    void putNonExistingPatientDocument() throws Exception {
        int databaseSizeBeforeUpdate = patientDocumentRepository.findAll().collectList().block().size();
        patientDocument.setPatientDocumentId(count.incrementAndGet());

        // Create the PatientDocument
        PatientDocumentDTO patientDocumentDTO = patientDocumentMapper.toDto(patientDocument);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, patientDocumentDTO.getPatientDocumentId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDocumentDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDocument in the database
        List<PatientDocument> patientDocumentList = patientDocumentRepository.findAll().collectList().block();
        assertThat(patientDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchPatientDocument() throws Exception {
        int databaseSizeBeforeUpdate = patientDocumentRepository.findAll().collectList().block().size();
        patientDocument.setPatientDocumentId(count.incrementAndGet());

        // Create the PatientDocument
        PatientDocumentDTO patientDocumentDTO = patientDocumentMapper.toDto(patientDocument);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDocumentDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDocument in the database
        List<PatientDocument> patientDocumentList = patientDocumentRepository.findAll().collectList().block();
        assertThat(patientDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamPatientDocument() throws Exception {
        int databaseSizeBeforeUpdate = patientDocumentRepository.findAll().collectList().block().size();
        patientDocument.setPatientDocumentId(count.incrementAndGet());

        // Create the PatientDocument
        PatientDocumentDTO patientDocumentDTO = patientDocumentMapper.toDto(patientDocument);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDocumentDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PatientDocument in the database
        List<PatientDocument> patientDocumentList = patientDocumentRepository.findAll().collectList().block();
        assertThat(patientDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdatePatientDocumentWithPatch() throws Exception {
        // Initialize the database
        patientDocumentRepository.save(patientDocument).block();

        int databaseSizeBeforeUpdate = patientDocumentRepository.findAll().collectList().block().size();

        // Update the patientDocument using partial update
        PatientDocument partialUpdatedPatientDocument = new PatientDocument();
        partialUpdatedPatientDocument.setPatientDocumentId(patientDocument.getPatientDocumentId());

        partialUpdatedPatientDocument
            .patientDocumentNo(UPDATED_PATIENT_DOCUMENT_NO)
            .patientId(UPDATED_PATIENT_ID)
            .patientUuid(UPDATED_PATIENT_UUID)
            .patientDocumentType(UPDATED_PATIENT_DOCUMENT_TYPE)
            .patientDocumentRepositoryName(UPDATED_PATIENT_DOCUMENT_REPOSITORY_NAME)
            .patientDocumentName(UPDATED_PATIENT_DOCUMENT_NAME)
            .patientDocumentStatus(UPDATED_PATIENT_DOCUMENT_STATUS)
            .status(UPDATED_STATUS);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPatientDocument.getPatientDocumentId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPatientDocument))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientDocument in the database
        List<PatientDocument> patientDocumentList = patientDocumentRepository.findAll().collectList().block();
        assertThat(patientDocumentList).hasSize(databaseSizeBeforeUpdate);
        PatientDocument testPatientDocument = patientDocumentList.get(patientDocumentList.size() - 1);
        assertThat(testPatientDocument.getPatientDocumentNo()).isEqualTo(UPDATED_PATIENT_DOCUMENT_NO);
        assertThat(testPatientDocument.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testPatientDocument.getPatientUuid()).isEqualTo(UPDATED_PATIENT_UUID);
        assertThat(testPatientDocument.getPatientIdNo()).isEqualTo(DEFAULT_PATIENT_ID_NO);
        assertThat(testPatientDocument.getPatientDocumentType()).isEqualTo(UPDATED_PATIENT_DOCUMENT_TYPE);
        assertThat(testPatientDocument.getPatientDocumentRepositoryName()).isEqualTo(UPDATED_PATIENT_DOCUMENT_REPOSITORY_NAME);
        assertThat(testPatientDocument.getPatientDocumentName()).isEqualTo(UPDATED_PATIENT_DOCUMENT_NAME);
        assertThat(testPatientDocument.getPatientDocumentDescription()).isEqualTo(DEFAULT_PATIENT_DOCUMENT_DESCRIPTION);
        assertThat(testPatientDocument.getPatientDocumentStatus()).isEqualTo(UPDATED_PATIENT_DOCUMENT_STATUS);
        assertThat(testPatientDocument.getUploadedById()).isEqualTo(DEFAULT_UPLOADED_BY_ID);
        assertThat(testPatientDocument.getUploadedByName()).isEqualTo(DEFAULT_UPLOADED_BY_NAME);
        assertThat(testPatientDocument.getUploadedDate()).isEqualTo(DEFAULT_UPLOADED_DATE);
        assertThat(testPatientDocument.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPatientDocument.getPatientDocumentUuid()).isEqualTo(DEFAULT_PATIENT_DOCUMENT_UUID);
    }

    @Test
    void fullUpdatePatientDocumentWithPatch() throws Exception {
        // Initialize the database
        patientDocumentRepository.save(patientDocument).block();

        int databaseSizeBeforeUpdate = patientDocumentRepository.findAll().collectList().block().size();

        // Update the patientDocument using partial update
        PatientDocument partialUpdatedPatientDocument = new PatientDocument();
        partialUpdatedPatientDocument.setPatientDocumentId(patientDocument.getPatientDocumentId());

        partialUpdatedPatientDocument
            .patientDocumentNo(UPDATED_PATIENT_DOCUMENT_NO)
            .patientId(UPDATED_PATIENT_ID)
            .patientUuid(UPDATED_PATIENT_UUID)
            .patientIdNo(UPDATED_PATIENT_ID_NO)
            .patientDocumentType(UPDATED_PATIENT_DOCUMENT_TYPE)
            .patientDocumentRepositoryName(UPDATED_PATIENT_DOCUMENT_REPOSITORY_NAME)
            .patientDocumentName(UPDATED_PATIENT_DOCUMENT_NAME)
            .patientDocumentDescription(UPDATED_PATIENT_DOCUMENT_DESCRIPTION)
            .patientDocumentStatus(UPDATED_PATIENT_DOCUMENT_STATUS)
            .uploadedById(UPDATED_UPLOADED_BY_ID)
            .uploadedByName(UPDATED_UPLOADED_BY_NAME)
            .uploadedDate(UPDATED_UPLOADED_DATE)
            .status(UPDATED_STATUS)
            .patientDocumentUuid(UPDATED_PATIENT_DOCUMENT_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPatientDocument.getPatientDocumentId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPatientDocument))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientDocument in the database
        List<PatientDocument> patientDocumentList = patientDocumentRepository.findAll().collectList().block();
        assertThat(patientDocumentList).hasSize(databaseSizeBeforeUpdate);
        PatientDocument testPatientDocument = patientDocumentList.get(patientDocumentList.size() - 1);
        assertThat(testPatientDocument.getPatientDocumentNo()).isEqualTo(UPDATED_PATIENT_DOCUMENT_NO);
        assertThat(testPatientDocument.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testPatientDocument.getPatientUuid()).isEqualTo(UPDATED_PATIENT_UUID);
        assertThat(testPatientDocument.getPatientIdNo()).isEqualTo(UPDATED_PATIENT_ID_NO);
        assertThat(testPatientDocument.getPatientDocumentType()).isEqualTo(UPDATED_PATIENT_DOCUMENT_TYPE);
        assertThat(testPatientDocument.getPatientDocumentRepositoryName()).isEqualTo(UPDATED_PATIENT_DOCUMENT_REPOSITORY_NAME);
        assertThat(testPatientDocument.getPatientDocumentName()).isEqualTo(UPDATED_PATIENT_DOCUMENT_NAME);
        assertThat(testPatientDocument.getPatientDocumentDescription()).isEqualTo(UPDATED_PATIENT_DOCUMENT_DESCRIPTION);
        assertThat(testPatientDocument.getPatientDocumentStatus()).isEqualTo(UPDATED_PATIENT_DOCUMENT_STATUS);
        assertThat(testPatientDocument.getUploadedById()).isEqualTo(UPDATED_UPLOADED_BY_ID);
        assertThat(testPatientDocument.getUploadedByName()).isEqualTo(UPDATED_UPLOADED_BY_NAME);
        assertThat(testPatientDocument.getUploadedDate()).isEqualTo(UPDATED_UPLOADED_DATE);
        assertThat(testPatientDocument.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPatientDocument.getPatientDocumentUuid()).isEqualTo(UPDATED_PATIENT_DOCUMENT_UUID);
    }

    @Test
    void patchNonExistingPatientDocument() throws Exception {
        int databaseSizeBeforeUpdate = patientDocumentRepository.findAll().collectList().block().size();
        patientDocument.setPatientDocumentId(count.incrementAndGet());

        // Create the PatientDocument
        PatientDocumentDTO patientDocumentDTO = patientDocumentMapper.toDto(patientDocument);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, patientDocumentDTO.getPatientDocumentId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDocumentDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDocument in the database
        List<PatientDocument> patientDocumentList = patientDocumentRepository.findAll().collectList().block();
        assertThat(patientDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchPatientDocument() throws Exception {
        int databaseSizeBeforeUpdate = patientDocumentRepository.findAll().collectList().block().size();
        patientDocument.setPatientDocumentId(count.incrementAndGet());

        // Create the PatientDocument
        PatientDocumentDTO patientDocumentDTO = patientDocumentMapper.toDto(patientDocument);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDocumentDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDocument in the database
        List<PatientDocument> patientDocumentList = patientDocumentRepository.findAll().collectList().block();
        assertThat(patientDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamPatientDocument() throws Exception {
        int databaseSizeBeforeUpdate = patientDocumentRepository.findAll().collectList().block().size();
        patientDocument.setPatientDocumentId(count.incrementAndGet());

        // Create the PatientDocument
        PatientDocumentDTO patientDocumentDTO = patientDocumentMapper.toDto(patientDocument);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDocumentDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PatientDocument in the database
        List<PatientDocument> patientDocumentList = patientDocumentRepository.findAll().collectList().block();
        assertThat(patientDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deletePatientDocument() {
        // Initialize the database
        patientDocumentRepository.save(patientDocument).block();

        int databaseSizeBeforeDelete = patientDocumentRepository.findAll().collectList().block().size();

        // Delete the patientDocument
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, patientDocument.getPatientDocumentId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<PatientDocument> patientDocumentList = patientDocumentRepository.findAll().collectList().block();
        assertThat(patientDocumentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
