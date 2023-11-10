package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.PatientDetails;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.PatientDetailsRepository;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
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
 * Integration tests for the {@link PatientDetailsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class PatientDetailsResourceIT {

    private static final String DEFAULT_PATIENT_FNAME = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_FNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_LNAME = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_LNAME = "BBBBBBBBBB";

    private static final Instant DEFAULT_DOB = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DOB = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_ZIP = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NO = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_DOCUMENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_MRNO = "AAAAAAAAAA";
    private static final String UPDATED_MRNO = "BBBBBBBBBB";

    private static final String DEFAULT_DATE_TIME = "AAAAAAAAAA";
    private static final String UPDATED_DATE_TIME = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final Boolean DEFAULT_IS_TAGGED = false;
    private static final Boolean UPDATED_IS_TAGGED = true;

    private static final String DEFAULT_DOCUMENT_PATH = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENT_PATH = "BBBBBBBBBB";

    private static final Boolean DEFAULT_QR_CODE_STATUS = false;
    private static final Boolean UPDATED_QR_CODE_STATUS = true;

    private static final String ENTITY_API_URL = "/api/patient-details";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{patientId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PatientDetailsRepository patientDetailsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private PatientDetails patientDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PatientDetails createEntity(EntityManager em) {
        PatientDetails patientDetails = new PatientDetails()
            .patientFname(DEFAULT_PATIENT_FNAME)
            .patientLname(DEFAULT_PATIENT_LNAME)
            .dob(DEFAULT_DOB)
            .address(DEFAULT_ADDRESS)
            .city(DEFAULT_CITY)
            .zip(DEFAULT_ZIP)
            .email(DEFAULT_EMAIL)
            .phoneNo(DEFAULT_PHONE_NO)
            .documentName(DEFAULT_DOCUMENT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .mrno(DEFAULT_MRNO)
            .dateTime(DEFAULT_DATE_TIME)
            .status(DEFAULT_STATUS)
            .isTagged(DEFAULT_IS_TAGGED)
            .documentPath(DEFAULT_DOCUMENT_PATH)
            .qrCodeStatus(DEFAULT_QR_CODE_STATUS);
        return patientDetails;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PatientDetails createUpdatedEntity(EntityManager em) {
        PatientDetails patientDetails = new PatientDetails()
            .patientFname(UPDATED_PATIENT_FNAME)
            .patientLname(UPDATED_PATIENT_LNAME)
            .dob(UPDATED_DOB)
            .address(UPDATED_ADDRESS)
            .city(UPDATED_CITY)
            .zip(UPDATED_ZIP)
            .email(UPDATED_EMAIL)
            .phoneNo(UPDATED_PHONE_NO)
            .documentName(UPDATED_DOCUMENT_NAME)
            .description(UPDATED_DESCRIPTION)
            .mrno(UPDATED_MRNO)
            .dateTime(UPDATED_DATE_TIME)
            .status(UPDATED_STATUS)
            .isTagged(UPDATED_IS_TAGGED)
            .documentPath(UPDATED_DOCUMENT_PATH)
            .qrCodeStatus(UPDATED_QR_CODE_STATUS);
        return patientDetails;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(PatientDetails.class).block();
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
        patientDetails = createEntity(em);
    }

    @Test
    void createPatientDetails() throws Exception {
        int databaseSizeBeforeCreate = patientDetailsRepository.findAll().collectList().block().size();
        // Create the PatientDetails
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDetails))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the PatientDetails in the database
        List<PatientDetails> patientDetailsList = patientDetailsRepository.findAll().collectList().block();
        assertThat(patientDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        PatientDetails testPatientDetails = patientDetailsList.get(patientDetailsList.size() - 1);
        assertThat(testPatientDetails.getPatientFname()).isEqualTo(DEFAULT_PATIENT_FNAME);
        assertThat(testPatientDetails.getPatientLname()).isEqualTo(DEFAULT_PATIENT_LNAME);
        assertThat(testPatientDetails.getDob()).isEqualTo(DEFAULT_DOB);
        assertThat(testPatientDetails.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testPatientDetails.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testPatientDetails.getZip()).isEqualTo(DEFAULT_ZIP);
        assertThat(testPatientDetails.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testPatientDetails.getPhoneNo()).isEqualTo(DEFAULT_PHONE_NO);
        assertThat(testPatientDetails.getDocumentName()).isEqualTo(DEFAULT_DOCUMENT_NAME);
        assertThat(testPatientDetails.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testPatientDetails.getMrno()).isEqualTo(DEFAULT_MRNO);
        assertThat(testPatientDetails.getDateTime()).isEqualTo(DEFAULT_DATE_TIME);
        assertThat(testPatientDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPatientDetails.getIsTagged()).isEqualTo(DEFAULT_IS_TAGGED);
        assertThat(testPatientDetails.getDocumentPath()).isEqualTo(DEFAULT_DOCUMENT_PATH);
        assertThat(testPatientDetails.getQrCodeStatus()).isEqualTo(DEFAULT_QR_CODE_STATUS);
    }

    @Test
    void createPatientDetailsWithExistingId() throws Exception {
        // Create the PatientDetails with an existing ID
        patientDetails.setPatientId(1L);

        int databaseSizeBeforeCreate = patientDetailsRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDetails))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDetails in the database
        List<PatientDetails> patientDetailsList = patientDetailsRepository.findAll().collectList().block();
        assertThat(patientDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkDobIsRequired() throws Exception {
        int databaseSizeBeforeTest = patientDetailsRepository.findAll().collectList().block().size();
        // set the field null
        patientDetails.setDob(null);

        // Create the PatientDetails, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDetails))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<PatientDetails> patientDetailsList = patientDetailsRepository.findAll().collectList().block();
        assertThat(patientDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllPatientDetailsAsStream() {
        // Initialize the database
        patientDetailsRepository.save(patientDetails).block();

        List<PatientDetails> patientDetailsList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(PatientDetails.class)
            .getResponseBody()
            .filter(patientDetails::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(patientDetailsList).isNotNull();
        assertThat(patientDetailsList).hasSize(1);
        PatientDetails testPatientDetails = patientDetailsList.get(0);
        assertThat(testPatientDetails.getPatientFname()).isEqualTo(DEFAULT_PATIENT_FNAME);
        assertThat(testPatientDetails.getPatientLname()).isEqualTo(DEFAULT_PATIENT_LNAME);
        assertThat(testPatientDetails.getDob()).isEqualTo(DEFAULT_DOB);
        assertThat(testPatientDetails.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testPatientDetails.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testPatientDetails.getZip()).isEqualTo(DEFAULT_ZIP);
        assertThat(testPatientDetails.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testPatientDetails.getPhoneNo()).isEqualTo(DEFAULT_PHONE_NO);
        assertThat(testPatientDetails.getDocumentName()).isEqualTo(DEFAULT_DOCUMENT_NAME);
        assertThat(testPatientDetails.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testPatientDetails.getMrno()).isEqualTo(DEFAULT_MRNO);
        assertThat(testPatientDetails.getDateTime()).isEqualTo(DEFAULT_DATE_TIME);
        assertThat(testPatientDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPatientDetails.getIsTagged()).isEqualTo(DEFAULT_IS_TAGGED);
        assertThat(testPatientDetails.getDocumentPath()).isEqualTo(DEFAULT_DOCUMENT_PATH);
        assertThat(testPatientDetails.getQrCodeStatus()).isEqualTo(DEFAULT_QR_CODE_STATUS);
    }

    @Test
    void getAllPatientDetails() {
        // Initialize the database
        patientDetailsRepository.save(patientDetails).block();

        // Get all the patientDetailsList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=patientId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].patientId")
            .value(hasItem(patientDetails.getPatientId().intValue()))
            .jsonPath("$.[*].patientFname")
            .value(hasItem(DEFAULT_PATIENT_FNAME))
            .jsonPath("$.[*].patientLname")
            .value(hasItem(DEFAULT_PATIENT_LNAME))
            .jsonPath("$.[*].dob")
            .value(hasItem(DEFAULT_DOB.toString()))
            .jsonPath("$.[*].address")
            .value(hasItem(DEFAULT_ADDRESS))
            .jsonPath("$.[*].city")
            .value(hasItem(DEFAULT_CITY))
            .jsonPath("$.[*].zip")
            .value(hasItem(DEFAULT_ZIP))
            .jsonPath("$.[*].email")
            .value(hasItem(DEFAULT_EMAIL))
            .jsonPath("$.[*].phoneNo")
            .value(hasItem(DEFAULT_PHONE_NO))
            .jsonPath("$.[*].documentName")
            .value(hasItem(DEFAULT_DOCUMENT_NAME))
            .jsonPath("$.[*].description")
            .value(hasItem(DEFAULT_DESCRIPTION))
            .jsonPath("$.[*].mrno")
            .value(hasItem(DEFAULT_MRNO))
            .jsonPath("$.[*].dateTime")
            .value(hasItem(DEFAULT_DATE_TIME))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS))
            .jsonPath("$.[*].isTagged")
            .value(hasItem(DEFAULT_IS_TAGGED.booleanValue()))
            .jsonPath("$.[*].documentPath")
            .value(hasItem(DEFAULT_DOCUMENT_PATH))
            .jsonPath("$.[*].qrCodeStatus")
            .value(hasItem(DEFAULT_QR_CODE_STATUS.booleanValue()));
    }

    @Test
    void getPatientDetails() {
        // Initialize the database
        patientDetailsRepository.save(patientDetails).block();

        // Get the patientDetails
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, patientDetails.getPatientId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.patientId")
            .value(is(patientDetails.getPatientId().intValue()))
            .jsonPath("$.patientFname")
            .value(is(DEFAULT_PATIENT_FNAME))
            .jsonPath("$.patientLname")
            .value(is(DEFAULT_PATIENT_LNAME))
            .jsonPath("$.dob")
            .value(is(DEFAULT_DOB.toString()))
            .jsonPath("$.address")
            .value(is(DEFAULT_ADDRESS))
            .jsonPath("$.city")
            .value(is(DEFAULT_CITY))
            .jsonPath("$.zip")
            .value(is(DEFAULT_ZIP))
            .jsonPath("$.email")
            .value(is(DEFAULT_EMAIL))
            .jsonPath("$.phoneNo")
            .value(is(DEFAULT_PHONE_NO))
            .jsonPath("$.documentName")
            .value(is(DEFAULT_DOCUMENT_NAME))
            .jsonPath("$.description")
            .value(is(DEFAULT_DESCRIPTION))
            .jsonPath("$.mrno")
            .value(is(DEFAULT_MRNO))
            .jsonPath("$.dateTime")
            .value(is(DEFAULT_DATE_TIME))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS))
            .jsonPath("$.isTagged")
            .value(is(DEFAULT_IS_TAGGED.booleanValue()))
            .jsonPath("$.documentPath")
            .value(is(DEFAULT_DOCUMENT_PATH))
            .jsonPath("$.qrCodeStatus")
            .value(is(DEFAULT_QR_CODE_STATUS.booleanValue()));
    }

    @Test
    void getNonExistingPatientDetails() {
        // Get the patientDetails
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putNewPatientDetails() throws Exception {
        // Initialize the database
        patientDetailsRepository.save(patientDetails).block();

        int databaseSizeBeforeUpdate = patientDetailsRepository.findAll().collectList().block().size();

        // Update the patientDetails
        PatientDetails updatedPatientDetails = patientDetailsRepository.findById(patientDetails.getPatientId()).block();
        updatedPatientDetails
            .patientFname(UPDATED_PATIENT_FNAME)
            .patientLname(UPDATED_PATIENT_LNAME)
            .dob(UPDATED_DOB)
            .address(UPDATED_ADDRESS)
            .city(UPDATED_CITY)
            .zip(UPDATED_ZIP)
            .email(UPDATED_EMAIL)
            .phoneNo(UPDATED_PHONE_NO)
            .documentName(UPDATED_DOCUMENT_NAME)
            .description(UPDATED_DESCRIPTION)
            .mrno(UPDATED_MRNO)
            .dateTime(UPDATED_DATE_TIME)
            .status(UPDATED_STATUS)
            .isTagged(UPDATED_IS_TAGGED)
            .documentPath(UPDATED_DOCUMENT_PATH)
            .qrCodeStatus(UPDATED_QR_CODE_STATUS);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedPatientDetails.getPatientId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedPatientDetails))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientDetails in the database
        List<PatientDetails> patientDetailsList = patientDetailsRepository.findAll().collectList().block();
        assertThat(patientDetailsList).hasSize(databaseSizeBeforeUpdate);
        PatientDetails testPatientDetails = patientDetailsList.get(patientDetailsList.size() - 1);
        assertThat(testPatientDetails.getPatientFname()).isEqualTo(UPDATED_PATIENT_FNAME);
        assertThat(testPatientDetails.getPatientLname()).isEqualTo(UPDATED_PATIENT_LNAME);
        assertThat(testPatientDetails.getDob()).isEqualTo(UPDATED_DOB);
        assertThat(testPatientDetails.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testPatientDetails.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testPatientDetails.getZip()).isEqualTo(UPDATED_ZIP);
        assertThat(testPatientDetails.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testPatientDetails.getPhoneNo()).isEqualTo(UPDATED_PHONE_NO);
        assertThat(testPatientDetails.getDocumentName()).isEqualTo(UPDATED_DOCUMENT_NAME);
        assertThat(testPatientDetails.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testPatientDetails.getMrno()).isEqualTo(UPDATED_MRNO);
        assertThat(testPatientDetails.getDateTime()).isEqualTo(UPDATED_DATE_TIME);
        assertThat(testPatientDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPatientDetails.getIsTagged()).isEqualTo(UPDATED_IS_TAGGED);
        assertThat(testPatientDetails.getDocumentPath()).isEqualTo(UPDATED_DOCUMENT_PATH);
        assertThat(testPatientDetails.getQrCodeStatus()).isEqualTo(UPDATED_QR_CODE_STATUS);
    }

    @Test
    void putNonExistingPatientDetails() throws Exception {
        int databaseSizeBeforeUpdate = patientDetailsRepository.findAll().collectList().block().size();
        patientDetails.setPatientId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, patientDetails.getPatientId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDetails))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDetails in the database
        List<PatientDetails> patientDetailsList = patientDetailsRepository.findAll().collectList().block();
        assertThat(patientDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchPatientDetails() throws Exception {
        int databaseSizeBeforeUpdate = patientDetailsRepository.findAll().collectList().block().size();
        patientDetails.setPatientId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDetails))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDetails in the database
        List<PatientDetails> patientDetailsList = patientDetailsRepository.findAll().collectList().block();
        assertThat(patientDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamPatientDetails() throws Exception {
        int databaseSizeBeforeUpdate = patientDetailsRepository.findAll().collectList().block().size();
        patientDetails.setPatientId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDetails))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PatientDetails in the database
        List<PatientDetails> patientDetailsList = patientDetailsRepository.findAll().collectList().block();
        assertThat(patientDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdatePatientDetailsWithPatch() throws Exception {
        // Initialize the database
        patientDetailsRepository.save(patientDetails).block();

        int databaseSizeBeforeUpdate = patientDetailsRepository.findAll().collectList().block().size();

        // Update the patientDetails using partial update
        PatientDetails partialUpdatedPatientDetails = new PatientDetails();
        partialUpdatedPatientDetails.setPatientId(patientDetails.getPatientId());

        partialUpdatedPatientDetails
            .patientFname(UPDATED_PATIENT_FNAME)
            .dob(UPDATED_DOB)
            .address(UPDATED_ADDRESS)
            .phoneNo(UPDATED_PHONE_NO)
            .documentName(UPDATED_DOCUMENT_NAME)
            .mrno(UPDATED_MRNO)
            .documentPath(UPDATED_DOCUMENT_PATH);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPatientDetails.getPatientId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPatientDetails))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientDetails in the database
        List<PatientDetails> patientDetailsList = patientDetailsRepository.findAll().collectList().block();
        assertThat(patientDetailsList).hasSize(databaseSizeBeforeUpdate);
        PatientDetails testPatientDetails = patientDetailsList.get(patientDetailsList.size() - 1);
        assertThat(testPatientDetails.getPatientFname()).isEqualTo(UPDATED_PATIENT_FNAME);
        assertThat(testPatientDetails.getPatientLname()).isEqualTo(DEFAULT_PATIENT_LNAME);
        assertThat(testPatientDetails.getDob()).isEqualTo(UPDATED_DOB);
        assertThat(testPatientDetails.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testPatientDetails.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testPatientDetails.getZip()).isEqualTo(DEFAULT_ZIP);
        assertThat(testPatientDetails.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testPatientDetails.getPhoneNo()).isEqualTo(UPDATED_PHONE_NO);
        assertThat(testPatientDetails.getDocumentName()).isEqualTo(UPDATED_DOCUMENT_NAME);
        assertThat(testPatientDetails.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testPatientDetails.getMrno()).isEqualTo(UPDATED_MRNO);
        assertThat(testPatientDetails.getDateTime()).isEqualTo(DEFAULT_DATE_TIME);
        assertThat(testPatientDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPatientDetails.getIsTagged()).isEqualTo(DEFAULT_IS_TAGGED);
        assertThat(testPatientDetails.getDocumentPath()).isEqualTo(UPDATED_DOCUMENT_PATH);
        assertThat(testPatientDetails.getQrCodeStatus()).isEqualTo(DEFAULT_QR_CODE_STATUS);
    }

    @Test
    void fullUpdatePatientDetailsWithPatch() throws Exception {
        // Initialize the database
        patientDetailsRepository.save(patientDetails).block();

        int databaseSizeBeforeUpdate = patientDetailsRepository.findAll().collectList().block().size();

        // Update the patientDetails using partial update
        PatientDetails partialUpdatedPatientDetails = new PatientDetails();
        partialUpdatedPatientDetails.setPatientId(patientDetails.getPatientId());

        partialUpdatedPatientDetails
            .patientFname(UPDATED_PATIENT_FNAME)
            .patientLname(UPDATED_PATIENT_LNAME)
            .dob(UPDATED_DOB)
            .address(UPDATED_ADDRESS)
            .city(UPDATED_CITY)
            .zip(UPDATED_ZIP)
            .email(UPDATED_EMAIL)
            .phoneNo(UPDATED_PHONE_NO)
            .documentName(UPDATED_DOCUMENT_NAME)
            .description(UPDATED_DESCRIPTION)
            .mrno(UPDATED_MRNO)
            .dateTime(UPDATED_DATE_TIME)
            .status(UPDATED_STATUS)
            .isTagged(UPDATED_IS_TAGGED)
            .documentPath(UPDATED_DOCUMENT_PATH)
            .qrCodeStatus(UPDATED_QR_CODE_STATUS);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPatientDetails.getPatientId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPatientDetails))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientDetails in the database
        List<PatientDetails> patientDetailsList = patientDetailsRepository.findAll().collectList().block();
        assertThat(patientDetailsList).hasSize(databaseSizeBeforeUpdate);
        PatientDetails testPatientDetails = patientDetailsList.get(patientDetailsList.size() - 1);
        assertThat(testPatientDetails.getPatientFname()).isEqualTo(UPDATED_PATIENT_FNAME);
        assertThat(testPatientDetails.getPatientLname()).isEqualTo(UPDATED_PATIENT_LNAME);
        assertThat(testPatientDetails.getDob()).isEqualTo(UPDATED_DOB);
        assertThat(testPatientDetails.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testPatientDetails.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testPatientDetails.getZip()).isEqualTo(UPDATED_ZIP);
        assertThat(testPatientDetails.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testPatientDetails.getPhoneNo()).isEqualTo(UPDATED_PHONE_NO);
        assertThat(testPatientDetails.getDocumentName()).isEqualTo(UPDATED_DOCUMENT_NAME);
        assertThat(testPatientDetails.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testPatientDetails.getMrno()).isEqualTo(UPDATED_MRNO);
        assertThat(testPatientDetails.getDateTime()).isEqualTo(UPDATED_DATE_TIME);
        assertThat(testPatientDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPatientDetails.getIsTagged()).isEqualTo(UPDATED_IS_TAGGED);
        assertThat(testPatientDetails.getDocumentPath()).isEqualTo(UPDATED_DOCUMENT_PATH);
        assertThat(testPatientDetails.getQrCodeStatus()).isEqualTo(UPDATED_QR_CODE_STATUS);
    }

    @Test
    void patchNonExistingPatientDetails() throws Exception {
        int databaseSizeBeforeUpdate = patientDetailsRepository.findAll().collectList().block().size();
        patientDetails.setPatientId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, patientDetails.getPatientId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDetails))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDetails in the database
        List<PatientDetails> patientDetailsList = patientDetailsRepository.findAll().collectList().block();
        assertThat(patientDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchPatientDetails() throws Exception {
        int databaseSizeBeforeUpdate = patientDetailsRepository.findAll().collectList().block().size();
        patientDetails.setPatientId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDetails))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDetails in the database
        List<PatientDetails> patientDetailsList = patientDetailsRepository.findAll().collectList().block();
        assertThat(patientDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamPatientDetails() throws Exception {
        int databaseSizeBeforeUpdate = patientDetailsRepository.findAll().collectList().block().size();
        patientDetails.setPatientId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDetails))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PatientDetails in the database
        List<PatientDetails> patientDetailsList = patientDetailsRepository.findAll().collectList().block();
        assertThat(patientDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deletePatientDetails() {
        // Initialize the database
        patientDetailsRepository.save(patientDetails).block();

        int databaseSizeBeforeDelete = patientDetailsRepository.findAll().collectList().block().size();

        // Delete the patientDetails
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, patientDetails.getPatientId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<PatientDetails> patientDetailsList = patientDetailsRepository.findAll().collectList().block();
        assertThat(patientDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
