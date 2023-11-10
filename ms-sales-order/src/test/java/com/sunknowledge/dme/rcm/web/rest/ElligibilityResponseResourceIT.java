package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ElligibilityResponse;
import com.sunknowledge.dme.rcm.repository.ElligibilityResponseRepository;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.service.dto.ElligibilityResponseDTO;
import com.sunknowledge.dme.rcm.service.mapper.ElligibilityResponseMapper;
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
 * Integration tests for the {@link ElligibilityResponseResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class ElligibilityResponseResourceIT {

    private static final String DEFAULT_ELLIGIBILITY_CONTROL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ELLIGIBILITY_CONTROL_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_TRACEID = "AAAAAAAAAA";
    private static final String UPDATED_TRACEID = "BBBBBBBBBB";

    private static final String DEFAULT_SUBSCRIBER_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIBER_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SUBSCRIBER_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIBER_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SUBSCRIBER_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIBER_GENDER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_SUBSCRIBER_DOB = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SUBSCRIBER_DOB = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SUBSCRIBER_IDENTIFIER = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIBER_IDENTIFIER = "BBBBBBBBBB";

    private static final String DEFAULT_SUBSCRIBER_ENTITYTYPE = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIBER_ENTITYTYPE = "BBBBBBBBBB";

    private static final String DEFAULT_SUBSCRIBER_SSN = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIBER_SSN = "BBBBBBBBBB";

    private static final String DEFAULT_PAYER_IDENTIFIER = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_IDENTIFIER = "BBBBBBBBBB";

    private static final String DEFAULT_PAYER_ENTITYTYPE = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_ENTITYTYPE = "BBBBBBBBBB";

    private static final String DEFAULT_PAYER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PAYER_IDENTIFICATION = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_IDENTIFICATION = "BBBBBBBBBB";

    private static final String DEFAULT_PLAN_SSN = "AAAAAAAAAA";
    private static final String UPDATED_PLAN_SSN = "BBBBBBBBBB";

    private static final String DEFAULT_PLAN_DATE = "AAAAAAAAAA";
    private static final String UPDATED_PLAN_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_PLAN_STATUS_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PLAN_STATUS_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PLAN_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_PLAN_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_PLAN_DETAILS = "AAAAAAAAAA";
    private static final String UPDATED_PLAN_DETAILS = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICE_TYPE_CODES = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_TYPE_CODES = "BBBBBBBBBB";

    private static final UUID DEFAULT_ELLIGIBILITY_RESPONSE_STATUS_UUID = UUID.randomUUID();
    private static final UUID UPDATED_ELLIGIBILITY_RESPONSE_STATUS_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/elligibility-responses";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{elligibilityResponseStatusId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ElligibilityResponseRepository elligibilityResponseRepository;

    @Autowired
    private ElligibilityResponseMapper elligibilityResponseMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private ElligibilityResponse elligibilityResponse;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ElligibilityResponse createEntity(EntityManager em) {
        ElligibilityResponse elligibilityResponse = new ElligibilityResponse()
            .elligibilityControlNumber(DEFAULT_ELLIGIBILITY_CONTROL_NUMBER)
            .traceid(DEFAULT_TRACEID)
            .subscriberFirstName(DEFAULT_SUBSCRIBER_FIRST_NAME)
            .subscriberLastName(DEFAULT_SUBSCRIBER_LAST_NAME)
            .subscriberGender(DEFAULT_SUBSCRIBER_GENDER)
            .subscriberDob(DEFAULT_SUBSCRIBER_DOB)
            .subscriberIdentifier(DEFAULT_SUBSCRIBER_IDENTIFIER)
            .subscriberEntitytype(DEFAULT_SUBSCRIBER_ENTITYTYPE)
            .subscriberSsn(DEFAULT_SUBSCRIBER_SSN)
            .payerIdentifier(DEFAULT_PAYER_IDENTIFIER)
            .payerEntitytype(DEFAULT_PAYER_ENTITYTYPE)
            .payerName(DEFAULT_PAYER_NAME)
            .payerIdentification(DEFAULT_PAYER_IDENTIFICATION)
            .planSsn(DEFAULT_PLAN_SSN)
            .planDate(DEFAULT_PLAN_DATE)
            .planStatusCode(DEFAULT_PLAN_STATUS_CODE)
            .planStatus(DEFAULT_PLAN_STATUS)
            .planDetails(DEFAULT_PLAN_DETAILS)
            .serviceTypeCodes(DEFAULT_SERVICE_TYPE_CODES)
            .elligibilityResponseStatusUuid(DEFAULT_ELLIGIBILITY_RESPONSE_STATUS_UUID);
        return elligibilityResponse;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ElligibilityResponse createUpdatedEntity(EntityManager em) {
        ElligibilityResponse elligibilityResponse = new ElligibilityResponse()
            .elligibilityControlNumber(UPDATED_ELLIGIBILITY_CONTROL_NUMBER)
            .traceid(UPDATED_TRACEID)
            .subscriberFirstName(UPDATED_SUBSCRIBER_FIRST_NAME)
            .subscriberLastName(UPDATED_SUBSCRIBER_LAST_NAME)
            .subscriberGender(UPDATED_SUBSCRIBER_GENDER)
            .subscriberDob(UPDATED_SUBSCRIBER_DOB)
            .subscriberIdentifier(UPDATED_SUBSCRIBER_IDENTIFIER)
            .subscriberEntitytype(UPDATED_SUBSCRIBER_ENTITYTYPE)
            .subscriberSsn(UPDATED_SUBSCRIBER_SSN)
            .payerIdentifier(UPDATED_PAYER_IDENTIFIER)
            .payerEntitytype(UPDATED_PAYER_ENTITYTYPE)
            .payerName(UPDATED_PAYER_NAME)
            .payerIdentification(UPDATED_PAYER_IDENTIFICATION)
            .planSsn(UPDATED_PLAN_SSN)
            .planDate(UPDATED_PLAN_DATE)
            .planStatusCode(UPDATED_PLAN_STATUS_CODE)
            .planStatus(UPDATED_PLAN_STATUS)
            .planDetails(UPDATED_PLAN_DETAILS)
            .serviceTypeCodes(UPDATED_SERVICE_TYPE_CODES)
            .elligibilityResponseStatusUuid(UPDATED_ELLIGIBILITY_RESPONSE_STATUS_UUID);
        return elligibilityResponse;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(ElligibilityResponse.class).block();
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
        elligibilityResponse = createEntity(em);
    }

    @Test
    void createElligibilityResponse() throws Exception {
        int databaseSizeBeforeCreate = elligibilityResponseRepository.findAll().collectList().block().size();
        // Create the ElligibilityResponse
        ElligibilityResponseDTO elligibilityResponseDTO = elligibilityResponseMapper.toDto(elligibilityResponse);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(elligibilityResponseDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the ElligibilityResponse in the database
        List<ElligibilityResponse> elligibilityResponseList = elligibilityResponseRepository.findAll().collectList().block();
        assertThat(elligibilityResponseList).hasSize(databaseSizeBeforeCreate + 1);
        ElligibilityResponse testElligibilityResponse = elligibilityResponseList.get(elligibilityResponseList.size() - 1);
        assertThat(testElligibilityResponse.getElligibilityControlNumber()).isEqualTo(DEFAULT_ELLIGIBILITY_CONTROL_NUMBER);
        assertThat(testElligibilityResponse.getTraceid()).isEqualTo(DEFAULT_TRACEID);
        assertThat(testElligibilityResponse.getSubscriberFirstName()).isEqualTo(DEFAULT_SUBSCRIBER_FIRST_NAME);
        assertThat(testElligibilityResponse.getSubscriberLastName()).isEqualTo(DEFAULT_SUBSCRIBER_LAST_NAME);
        assertThat(testElligibilityResponse.getSubscriberGender()).isEqualTo(DEFAULT_SUBSCRIBER_GENDER);
        assertThat(testElligibilityResponse.getSubscriberDob()).isEqualTo(DEFAULT_SUBSCRIBER_DOB);
        assertThat(testElligibilityResponse.getSubscriberIdentifier()).isEqualTo(DEFAULT_SUBSCRIBER_IDENTIFIER);
        assertThat(testElligibilityResponse.getSubscriberEntitytype()).isEqualTo(DEFAULT_SUBSCRIBER_ENTITYTYPE);
        assertThat(testElligibilityResponse.getSubscriberSsn()).isEqualTo(DEFAULT_SUBSCRIBER_SSN);
        assertThat(testElligibilityResponse.getPayerIdentifier()).isEqualTo(DEFAULT_PAYER_IDENTIFIER);
        assertThat(testElligibilityResponse.getPayerEntitytype()).isEqualTo(DEFAULT_PAYER_ENTITYTYPE);
        assertThat(testElligibilityResponse.getPayerName()).isEqualTo(DEFAULT_PAYER_NAME);
        assertThat(testElligibilityResponse.getPayerIdentification()).isEqualTo(DEFAULT_PAYER_IDENTIFICATION);
        assertThat(testElligibilityResponse.getPlanSsn()).isEqualTo(DEFAULT_PLAN_SSN);
        assertThat(testElligibilityResponse.getPlanDate()).isEqualTo(DEFAULT_PLAN_DATE);
        assertThat(testElligibilityResponse.getPlanStatusCode()).isEqualTo(DEFAULT_PLAN_STATUS_CODE);
        assertThat(testElligibilityResponse.getPlanStatus()).isEqualTo(DEFAULT_PLAN_STATUS);
        assertThat(testElligibilityResponse.getPlanDetails()).isEqualTo(DEFAULT_PLAN_DETAILS);
        assertThat(testElligibilityResponse.getServiceTypeCodes()).isEqualTo(DEFAULT_SERVICE_TYPE_CODES);
        assertThat(testElligibilityResponse.getElligibilityResponseStatusUuid()).isEqualTo(DEFAULT_ELLIGIBILITY_RESPONSE_STATUS_UUID);
    }

    @Test
    void createElligibilityResponseWithExistingId() throws Exception {
        // Create the ElligibilityResponse with an existing ID
        elligibilityResponse.setElligibilityResponseStatusId(1L);
        ElligibilityResponseDTO elligibilityResponseDTO = elligibilityResponseMapper.toDto(elligibilityResponse);

        int databaseSizeBeforeCreate = elligibilityResponseRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(elligibilityResponseDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ElligibilityResponse in the database
        List<ElligibilityResponse> elligibilityResponseList = elligibilityResponseRepository.findAll().collectList().block();
        assertThat(elligibilityResponseList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkElligibilityControlNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = elligibilityResponseRepository.findAll().collectList().block().size();
        // set the field null
        elligibilityResponse.setElligibilityControlNumber(null);

        // Create the ElligibilityResponse, which fails.
        ElligibilityResponseDTO elligibilityResponseDTO = elligibilityResponseMapper.toDto(elligibilityResponse);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(elligibilityResponseDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<ElligibilityResponse> elligibilityResponseList = elligibilityResponseRepository.findAll().collectList().block();
        assertThat(elligibilityResponseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllElligibilityResponses() {
        // Initialize the database
        elligibilityResponseRepository.save(elligibilityResponse).block();

        // Get all the elligibilityResponseList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=elligibilityResponseStatusId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].elligibilityResponseStatusId")
            .value(hasItem(elligibilityResponse.getElligibilityResponseStatusId().intValue()))
            .jsonPath("$.[*].elligibilityControlNumber")
            .value(hasItem(DEFAULT_ELLIGIBILITY_CONTROL_NUMBER))
            .jsonPath("$.[*].traceid")
            .value(hasItem(DEFAULT_TRACEID))
            .jsonPath("$.[*].subscriberFirstName")
            .value(hasItem(DEFAULT_SUBSCRIBER_FIRST_NAME))
            .jsonPath("$.[*].subscriberLastName")
            .value(hasItem(DEFAULT_SUBSCRIBER_LAST_NAME))
            .jsonPath("$.[*].subscriberGender")
            .value(hasItem(DEFAULT_SUBSCRIBER_GENDER))
            .jsonPath("$.[*].subscriberDob")
            .value(hasItem(DEFAULT_SUBSCRIBER_DOB.toString()))
            .jsonPath("$.[*].subscriberIdentifier")
            .value(hasItem(DEFAULT_SUBSCRIBER_IDENTIFIER))
            .jsonPath("$.[*].subscriberEntitytype")
            .value(hasItem(DEFAULT_SUBSCRIBER_ENTITYTYPE))
            .jsonPath("$.[*].subscriberSsn")
            .value(hasItem(DEFAULT_SUBSCRIBER_SSN))
            .jsonPath("$.[*].payerIdentifier")
            .value(hasItem(DEFAULT_PAYER_IDENTIFIER))
            .jsonPath("$.[*].payerEntitytype")
            .value(hasItem(DEFAULT_PAYER_ENTITYTYPE))
            .jsonPath("$.[*].payerName")
            .value(hasItem(DEFAULT_PAYER_NAME))
            .jsonPath("$.[*].payerIdentification")
            .value(hasItem(DEFAULT_PAYER_IDENTIFICATION))
            .jsonPath("$.[*].planSsn")
            .value(hasItem(DEFAULT_PLAN_SSN))
            .jsonPath("$.[*].planDate")
            .value(hasItem(DEFAULT_PLAN_DATE))
            .jsonPath("$.[*].planStatusCode")
            .value(hasItem(DEFAULT_PLAN_STATUS_CODE))
            .jsonPath("$.[*].planStatus")
            .value(hasItem(DEFAULT_PLAN_STATUS))
            .jsonPath("$.[*].planDetails")
            .value(hasItem(DEFAULT_PLAN_DETAILS))
            .jsonPath("$.[*].serviceTypeCodes")
            .value(hasItem(DEFAULT_SERVICE_TYPE_CODES))
            .jsonPath("$.[*].elligibilityResponseStatusUuid")
            .value(hasItem(DEFAULT_ELLIGIBILITY_RESPONSE_STATUS_UUID.toString()));
    }

    @Test
    void getElligibilityResponse() {
        // Initialize the database
        elligibilityResponseRepository.save(elligibilityResponse).block();

        // Get the elligibilityResponse
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, elligibilityResponse.getElligibilityResponseStatusId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.elligibilityResponseStatusId")
            .value(is(elligibilityResponse.getElligibilityResponseStatusId().intValue()))
            .jsonPath("$.elligibilityControlNumber")
            .value(is(DEFAULT_ELLIGIBILITY_CONTROL_NUMBER))
            .jsonPath("$.traceid")
            .value(is(DEFAULT_TRACEID))
            .jsonPath("$.subscriberFirstName")
            .value(is(DEFAULT_SUBSCRIBER_FIRST_NAME))
            .jsonPath("$.subscriberLastName")
            .value(is(DEFAULT_SUBSCRIBER_LAST_NAME))
            .jsonPath("$.subscriberGender")
            .value(is(DEFAULT_SUBSCRIBER_GENDER))
            .jsonPath("$.subscriberDob")
            .value(is(DEFAULT_SUBSCRIBER_DOB.toString()))
            .jsonPath("$.subscriberIdentifier")
            .value(is(DEFAULT_SUBSCRIBER_IDENTIFIER))
            .jsonPath("$.subscriberEntitytype")
            .value(is(DEFAULT_SUBSCRIBER_ENTITYTYPE))
            .jsonPath("$.subscriberSsn")
            .value(is(DEFAULT_SUBSCRIBER_SSN))
            .jsonPath("$.payerIdentifier")
            .value(is(DEFAULT_PAYER_IDENTIFIER))
            .jsonPath("$.payerEntitytype")
            .value(is(DEFAULT_PAYER_ENTITYTYPE))
            .jsonPath("$.payerName")
            .value(is(DEFAULT_PAYER_NAME))
            .jsonPath("$.payerIdentification")
            .value(is(DEFAULT_PAYER_IDENTIFICATION))
            .jsonPath("$.planSsn")
            .value(is(DEFAULT_PLAN_SSN))
            .jsonPath("$.planDate")
            .value(is(DEFAULT_PLAN_DATE))
            .jsonPath("$.planStatusCode")
            .value(is(DEFAULT_PLAN_STATUS_CODE))
            .jsonPath("$.planStatus")
            .value(is(DEFAULT_PLAN_STATUS))
            .jsonPath("$.planDetails")
            .value(is(DEFAULT_PLAN_DETAILS))
            .jsonPath("$.serviceTypeCodes")
            .value(is(DEFAULT_SERVICE_TYPE_CODES))
            .jsonPath("$.elligibilityResponseStatusUuid")
            .value(is(DEFAULT_ELLIGIBILITY_RESPONSE_STATUS_UUID.toString()));
    }

    @Test
    void getNonExistingElligibilityResponse() {
        // Get the elligibilityResponse
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putNewElligibilityResponse() throws Exception {
        // Initialize the database
        elligibilityResponseRepository.save(elligibilityResponse).block();

        int databaseSizeBeforeUpdate = elligibilityResponseRepository.findAll().collectList().block().size();

        // Update the elligibilityResponse
        ElligibilityResponse updatedElligibilityResponse = elligibilityResponseRepository
            .findById(elligibilityResponse.getElligibilityResponseStatusId())
            .block();
        updatedElligibilityResponse
            .elligibilityControlNumber(UPDATED_ELLIGIBILITY_CONTROL_NUMBER)
            .traceid(UPDATED_TRACEID)
            .subscriberFirstName(UPDATED_SUBSCRIBER_FIRST_NAME)
            .subscriberLastName(UPDATED_SUBSCRIBER_LAST_NAME)
            .subscriberGender(UPDATED_SUBSCRIBER_GENDER)
            .subscriberDob(UPDATED_SUBSCRIBER_DOB)
            .subscriberIdentifier(UPDATED_SUBSCRIBER_IDENTIFIER)
            .subscriberEntitytype(UPDATED_SUBSCRIBER_ENTITYTYPE)
            .subscriberSsn(UPDATED_SUBSCRIBER_SSN)
            .payerIdentifier(UPDATED_PAYER_IDENTIFIER)
            .payerEntitytype(UPDATED_PAYER_ENTITYTYPE)
            .payerName(UPDATED_PAYER_NAME)
            .payerIdentification(UPDATED_PAYER_IDENTIFICATION)
            .planSsn(UPDATED_PLAN_SSN)
            .planDate(UPDATED_PLAN_DATE)
            .planStatusCode(UPDATED_PLAN_STATUS_CODE)
            .planStatus(UPDATED_PLAN_STATUS)
            .planDetails(UPDATED_PLAN_DETAILS)
            .serviceTypeCodes(UPDATED_SERVICE_TYPE_CODES)
            .elligibilityResponseStatusUuid(UPDATED_ELLIGIBILITY_RESPONSE_STATUS_UUID);
        ElligibilityResponseDTO elligibilityResponseDTO = elligibilityResponseMapper.toDto(updatedElligibilityResponse);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, elligibilityResponseDTO.getElligibilityResponseStatusId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(elligibilityResponseDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ElligibilityResponse in the database
        List<ElligibilityResponse> elligibilityResponseList = elligibilityResponseRepository.findAll().collectList().block();
        assertThat(elligibilityResponseList).hasSize(databaseSizeBeforeUpdate);
        ElligibilityResponse testElligibilityResponse = elligibilityResponseList.get(elligibilityResponseList.size() - 1);
        assertThat(testElligibilityResponse.getElligibilityControlNumber()).isEqualTo(UPDATED_ELLIGIBILITY_CONTROL_NUMBER);
        assertThat(testElligibilityResponse.getTraceid()).isEqualTo(UPDATED_TRACEID);
        assertThat(testElligibilityResponse.getSubscriberFirstName()).isEqualTo(UPDATED_SUBSCRIBER_FIRST_NAME);
        assertThat(testElligibilityResponse.getSubscriberLastName()).isEqualTo(UPDATED_SUBSCRIBER_LAST_NAME);
        assertThat(testElligibilityResponse.getSubscriberGender()).isEqualTo(UPDATED_SUBSCRIBER_GENDER);
        assertThat(testElligibilityResponse.getSubscriberDob()).isEqualTo(UPDATED_SUBSCRIBER_DOB);
        assertThat(testElligibilityResponse.getSubscriberIdentifier()).isEqualTo(UPDATED_SUBSCRIBER_IDENTIFIER);
        assertThat(testElligibilityResponse.getSubscriberEntitytype()).isEqualTo(UPDATED_SUBSCRIBER_ENTITYTYPE);
        assertThat(testElligibilityResponse.getSubscriberSsn()).isEqualTo(UPDATED_SUBSCRIBER_SSN);
        assertThat(testElligibilityResponse.getPayerIdentifier()).isEqualTo(UPDATED_PAYER_IDENTIFIER);
        assertThat(testElligibilityResponse.getPayerEntitytype()).isEqualTo(UPDATED_PAYER_ENTITYTYPE);
        assertThat(testElligibilityResponse.getPayerName()).isEqualTo(UPDATED_PAYER_NAME);
        assertThat(testElligibilityResponse.getPayerIdentification()).isEqualTo(UPDATED_PAYER_IDENTIFICATION);
        assertThat(testElligibilityResponse.getPlanSsn()).isEqualTo(UPDATED_PLAN_SSN);
        assertThat(testElligibilityResponse.getPlanDate()).isEqualTo(UPDATED_PLAN_DATE);
        assertThat(testElligibilityResponse.getPlanStatusCode()).isEqualTo(UPDATED_PLAN_STATUS_CODE);
        assertThat(testElligibilityResponse.getPlanStatus()).isEqualTo(UPDATED_PLAN_STATUS);
        assertThat(testElligibilityResponse.getPlanDetails()).isEqualTo(UPDATED_PLAN_DETAILS);
        assertThat(testElligibilityResponse.getServiceTypeCodes()).isEqualTo(UPDATED_SERVICE_TYPE_CODES);
        assertThat(testElligibilityResponse.getElligibilityResponseStatusUuid()).isEqualTo(UPDATED_ELLIGIBILITY_RESPONSE_STATUS_UUID);
    }

    @Test
    void putNonExistingElligibilityResponse() throws Exception {
        int databaseSizeBeforeUpdate = elligibilityResponseRepository.findAll().collectList().block().size();
        elligibilityResponse.setElligibilityResponseStatusId(count.incrementAndGet());

        // Create the ElligibilityResponse
        ElligibilityResponseDTO elligibilityResponseDTO = elligibilityResponseMapper.toDto(elligibilityResponse);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, elligibilityResponseDTO.getElligibilityResponseStatusId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(elligibilityResponseDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ElligibilityResponse in the database
        List<ElligibilityResponse> elligibilityResponseList = elligibilityResponseRepository.findAll().collectList().block();
        assertThat(elligibilityResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchElligibilityResponse() throws Exception {
        int databaseSizeBeforeUpdate = elligibilityResponseRepository.findAll().collectList().block().size();
        elligibilityResponse.setElligibilityResponseStatusId(count.incrementAndGet());

        // Create the ElligibilityResponse
        ElligibilityResponseDTO elligibilityResponseDTO = elligibilityResponseMapper.toDto(elligibilityResponse);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(elligibilityResponseDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ElligibilityResponse in the database
        List<ElligibilityResponse> elligibilityResponseList = elligibilityResponseRepository.findAll().collectList().block();
        assertThat(elligibilityResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamElligibilityResponse() throws Exception {
        int databaseSizeBeforeUpdate = elligibilityResponseRepository.findAll().collectList().block().size();
        elligibilityResponse.setElligibilityResponseStatusId(count.incrementAndGet());

        // Create the ElligibilityResponse
        ElligibilityResponseDTO elligibilityResponseDTO = elligibilityResponseMapper.toDto(elligibilityResponse);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(elligibilityResponseDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the ElligibilityResponse in the database
        List<ElligibilityResponse> elligibilityResponseList = elligibilityResponseRepository.findAll().collectList().block();
        assertThat(elligibilityResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateElligibilityResponseWithPatch() throws Exception {
        // Initialize the database
        elligibilityResponseRepository.save(elligibilityResponse).block();

        int databaseSizeBeforeUpdate = elligibilityResponseRepository.findAll().collectList().block().size();

        // Update the elligibilityResponse using partial update
        ElligibilityResponse partialUpdatedElligibilityResponse = new ElligibilityResponse();
        partialUpdatedElligibilityResponse.setElligibilityResponseStatusId(elligibilityResponse.getElligibilityResponseStatusId());

        partialUpdatedElligibilityResponse
            .elligibilityControlNumber(UPDATED_ELLIGIBILITY_CONTROL_NUMBER)
            .subscriberGender(UPDATED_SUBSCRIBER_GENDER)
            .subscriberSsn(UPDATED_SUBSCRIBER_SSN)
            .payerEntitytype(UPDATED_PAYER_ENTITYTYPE)
            .payerIdentification(UPDATED_PAYER_IDENTIFICATION)
            .planDate(UPDATED_PLAN_DATE)
            .planStatusCode(UPDATED_PLAN_STATUS_CODE)
            .planDetails(UPDATED_PLAN_DETAILS)
            .elligibilityResponseStatusUuid(UPDATED_ELLIGIBILITY_RESPONSE_STATUS_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedElligibilityResponse.getElligibilityResponseStatusId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedElligibilityResponse))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ElligibilityResponse in the database
        List<ElligibilityResponse> elligibilityResponseList = elligibilityResponseRepository.findAll().collectList().block();
        assertThat(elligibilityResponseList).hasSize(databaseSizeBeforeUpdate);
        ElligibilityResponse testElligibilityResponse = elligibilityResponseList.get(elligibilityResponseList.size() - 1);
        assertThat(testElligibilityResponse.getElligibilityControlNumber()).isEqualTo(UPDATED_ELLIGIBILITY_CONTROL_NUMBER);
        assertThat(testElligibilityResponse.getTraceid()).isEqualTo(DEFAULT_TRACEID);
        assertThat(testElligibilityResponse.getSubscriberFirstName()).isEqualTo(DEFAULT_SUBSCRIBER_FIRST_NAME);
        assertThat(testElligibilityResponse.getSubscriberLastName()).isEqualTo(DEFAULT_SUBSCRIBER_LAST_NAME);
        assertThat(testElligibilityResponse.getSubscriberGender()).isEqualTo(UPDATED_SUBSCRIBER_GENDER);
        assertThat(testElligibilityResponse.getSubscriberDob()).isEqualTo(DEFAULT_SUBSCRIBER_DOB);
        assertThat(testElligibilityResponse.getSubscriberIdentifier()).isEqualTo(DEFAULT_SUBSCRIBER_IDENTIFIER);
        assertThat(testElligibilityResponse.getSubscriberEntitytype()).isEqualTo(DEFAULT_SUBSCRIBER_ENTITYTYPE);
        assertThat(testElligibilityResponse.getSubscriberSsn()).isEqualTo(UPDATED_SUBSCRIBER_SSN);
        assertThat(testElligibilityResponse.getPayerIdentifier()).isEqualTo(DEFAULT_PAYER_IDENTIFIER);
        assertThat(testElligibilityResponse.getPayerEntitytype()).isEqualTo(UPDATED_PAYER_ENTITYTYPE);
        assertThat(testElligibilityResponse.getPayerName()).isEqualTo(DEFAULT_PAYER_NAME);
        assertThat(testElligibilityResponse.getPayerIdentification()).isEqualTo(UPDATED_PAYER_IDENTIFICATION);
        assertThat(testElligibilityResponse.getPlanSsn()).isEqualTo(DEFAULT_PLAN_SSN);
        assertThat(testElligibilityResponse.getPlanDate()).isEqualTo(UPDATED_PLAN_DATE);
        assertThat(testElligibilityResponse.getPlanStatusCode()).isEqualTo(UPDATED_PLAN_STATUS_CODE);
        assertThat(testElligibilityResponse.getPlanStatus()).isEqualTo(DEFAULT_PLAN_STATUS);
        assertThat(testElligibilityResponse.getPlanDetails()).isEqualTo(UPDATED_PLAN_DETAILS);
        assertThat(testElligibilityResponse.getServiceTypeCodes()).isEqualTo(DEFAULT_SERVICE_TYPE_CODES);
        assertThat(testElligibilityResponse.getElligibilityResponseStatusUuid()).isEqualTo(UPDATED_ELLIGIBILITY_RESPONSE_STATUS_UUID);
    }

    @Test
    void fullUpdateElligibilityResponseWithPatch() throws Exception {
        // Initialize the database
        elligibilityResponseRepository.save(elligibilityResponse).block();

        int databaseSizeBeforeUpdate = elligibilityResponseRepository.findAll().collectList().block().size();

        // Update the elligibilityResponse using partial update
        ElligibilityResponse partialUpdatedElligibilityResponse = new ElligibilityResponse();
        partialUpdatedElligibilityResponse.setElligibilityResponseStatusId(elligibilityResponse.getElligibilityResponseStatusId());

        partialUpdatedElligibilityResponse
            .elligibilityControlNumber(UPDATED_ELLIGIBILITY_CONTROL_NUMBER)
            .traceid(UPDATED_TRACEID)
            .subscriberFirstName(UPDATED_SUBSCRIBER_FIRST_NAME)
            .subscriberLastName(UPDATED_SUBSCRIBER_LAST_NAME)
            .subscriberGender(UPDATED_SUBSCRIBER_GENDER)
            .subscriberDob(UPDATED_SUBSCRIBER_DOB)
            .subscriberIdentifier(UPDATED_SUBSCRIBER_IDENTIFIER)
            .subscriberEntitytype(UPDATED_SUBSCRIBER_ENTITYTYPE)
            .subscriberSsn(UPDATED_SUBSCRIBER_SSN)
            .payerIdentifier(UPDATED_PAYER_IDENTIFIER)
            .payerEntitytype(UPDATED_PAYER_ENTITYTYPE)
            .payerName(UPDATED_PAYER_NAME)
            .payerIdentification(UPDATED_PAYER_IDENTIFICATION)
            .planSsn(UPDATED_PLAN_SSN)
            .planDate(UPDATED_PLAN_DATE)
            .planStatusCode(UPDATED_PLAN_STATUS_CODE)
            .planStatus(UPDATED_PLAN_STATUS)
            .planDetails(UPDATED_PLAN_DETAILS)
            .serviceTypeCodes(UPDATED_SERVICE_TYPE_CODES)
            .elligibilityResponseStatusUuid(UPDATED_ELLIGIBILITY_RESPONSE_STATUS_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedElligibilityResponse.getElligibilityResponseStatusId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedElligibilityResponse))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ElligibilityResponse in the database
        List<ElligibilityResponse> elligibilityResponseList = elligibilityResponseRepository.findAll().collectList().block();
        assertThat(elligibilityResponseList).hasSize(databaseSizeBeforeUpdate);
        ElligibilityResponse testElligibilityResponse = elligibilityResponseList.get(elligibilityResponseList.size() - 1);
        assertThat(testElligibilityResponse.getElligibilityControlNumber()).isEqualTo(UPDATED_ELLIGIBILITY_CONTROL_NUMBER);
        assertThat(testElligibilityResponse.getTraceid()).isEqualTo(UPDATED_TRACEID);
        assertThat(testElligibilityResponse.getSubscriberFirstName()).isEqualTo(UPDATED_SUBSCRIBER_FIRST_NAME);
        assertThat(testElligibilityResponse.getSubscriberLastName()).isEqualTo(UPDATED_SUBSCRIBER_LAST_NAME);
        assertThat(testElligibilityResponse.getSubscriberGender()).isEqualTo(UPDATED_SUBSCRIBER_GENDER);
        assertThat(testElligibilityResponse.getSubscriberDob()).isEqualTo(UPDATED_SUBSCRIBER_DOB);
        assertThat(testElligibilityResponse.getSubscriberIdentifier()).isEqualTo(UPDATED_SUBSCRIBER_IDENTIFIER);
        assertThat(testElligibilityResponse.getSubscriberEntitytype()).isEqualTo(UPDATED_SUBSCRIBER_ENTITYTYPE);
        assertThat(testElligibilityResponse.getSubscriberSsn()).isEqualTo(UPDATED_SUBSCRIBER_SSN);
        assertThat(testElligibilityResponse.getPayerIdentifier()).isEqualTo(UPDATED_PAYER_IDENTIFIER);
        assertThat(testElligibilityResponse.getPayerEntitytype()).isEqualTo(UPDATED_PAYER_ENTITYTYPE);
        assertThat(testElligibilityResponse.getPayerName()).isEqualTo(UPDATED_PAYER_NAME);
        assertThat(testElligibilityResponse.getPayerIdentification()).isEqualTo(UPDATED_PAYER_IDENTIFICATION);
        assertThat(testElligibilityResponse.getPlanSsn()).isEqualTo(UPDATED_PLAN_SSN);
        assertThat(testElligibilityResponse.getPlanDate()).isEqualTo(UPDATED_PLAN_DATE);
        assertThat(testElligibilityResponse.getPlanStatusCode()).isEqualTo(UPDATED_PLAN_STATUS_CODE);
        assertThat(testElligibilityResponse.getPlanStatus()).isEqualTo(UPDATED_PLAN_STATUS);
        assertThat(testElligibilityResponse.getPlanDetails()).isEqualTo(UPDATED_PLAN_DETAILS);
        assertThat(testElligibilityResponse.getServiceTypeCodes()).isEqualTo(UPDATED_SERVICE_TYPE_CODES);
        assertThat(testElligibilityResponse.getElligibilityResponseStatusUuid()).isEqualTo(UPDATED_ELLIGIBILITY_RESPONSE_STATUS_UUID);
    }

    @Test
    void patchNonExistingElligibilityResponse() throws Exception {
        int databaseSizeBeforeUpdate = elligibilityResponseRepository.findAll().collectList().block().size();
        elligibilityResponse.setElligibilityResponseStatusId(count.incrementAndGet());

        // Create the ElligibilityResponse
        ElligibilityResponseDTO elligibilityResponseDTO = elligibilityResponseMapper.toDto(elligibilityResponse);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, elligibilityResponseDTO.getElligibilityResponseStatusId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(elligibilityResponseDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ElligibilityResponse in the database
        List<ElligibilityResponse> elligibilityResponseList = elligibilityResponseRepository.findAll().collectList().block();
        assertThat(elligibilityResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchElligibilityResponse() throws Exception {
        int databaseSizeBeforeUpdate = elligibilityResponseRepository.findAll().collectList().block().size();
        elligibilityResponse.setElligibilityResponseStatusId(count.incrementAndGet());

        // Create the ElligibilityResponse
        ElligibilityResponseDTO elligibilityResponseDTO = elligibilityResponseMapper.toDto(elligibilityResponse);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(elligibilityResponseDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ElligibilityResponse in the database
        List<ElligibilityResponse> elligibilityResponseList = elligibilityResponseRepository.findAll().collectList().block();
        assertThat(elligibilityResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamElligibilityResponse() throws Exception {
        int databaseSizeBeforeUpdate = elligibilityResponseRepository.findAll().collectList().block().size();
        elligibilityResponse.setElligibilityResponseStatusId(count.incrementAndGet());

        // Create the ElligibilityResponse
        ElligibilityResponseDTO elligibilityResponseDTO = elligibilityResponseMapper.toDto(elligibilityResponse);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(elligibilityResponseDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the ElligibilityResponse in the database
        List<ElligibilityResponse> elligibilityResponseList = elligibilityResponseRepository.findAll().collectList().block();
        assertThat(elligibilityResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteElligibilityResponse() {
        // Initialize the database
        elligibilityResponseRepository.save(elligibilityResponse).block();

        int databaseSizeBeforeDelete = elligibilityResponseRepository.findAll().collectList().block().size();

        // Delete the elligibilityResponse
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, elligibilityResponse.getElligibilityResponseStatusId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<ElligibilityResponse> elligibilityResponseList = elligibilityResponseRepository.findAll().collectList().block();
        assertThat(elligibilityResponseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
