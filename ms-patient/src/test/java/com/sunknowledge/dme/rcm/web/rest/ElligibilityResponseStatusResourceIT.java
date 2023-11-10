package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ElligibilityResponseStatus;
import com.sunknowledge.dme.rcm.repository.ElligibilityResponseStatusRepository;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.service.dto.ElligibilityResponseStatusDTO;
import com.sunknowledge.dme.rcm.service.mapper.ElligibilityResponseStatusMapper;
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
 * Integration tests for the {@link ElligibilityResponseStatusResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class ElligibilityResponseStatusResourceIT {

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

    private static final LocalDate DEFAULT_PLAN_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PLAN_DATE = LocalDate.now(ZoneId.systemDefault());

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

    private static final String ENTITY_API_URL = "/api/elligibility-response-statuses";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{elligibilityResponseStatusId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ElligibilityResponseStatusRepository elligibilityResponseStatusRepository;

    @Autowired
    private ElligibilityResponseStatusMapper elligibilityResponseStatusMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private ElligibilityResponseStatus elligibilityResponseStatus;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ElligibilityResponseStatus createEntity(EntityManager em) {
        ElligibilityResponseStatus elligibilityResponseStatus = new ElligibilityResponseStatus()
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
        return elligibilityResponseStatus;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ElligibilityResponseStatus createUpdatedEntity(EntityManager em) {
        ElligibilityResponseStatus elligibilityResponseStatus = new ElligibilityResponseStatus()
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
        return elligibilityResponseStatus;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(ElligibilityResponseStatus.class).block();
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
        elligibilityResponseStatus = createEntity(em);
    }

    @Test
    void createElligibilityResponseStatus() throws Exception {
        int databaseSizeBeforeCreate = elligibilityResponseStatusRepository.findAll().collectList().block().size();
        // Create the ElligibilityResponseStatus
        ElligibilityResponseStatusDTO elligibilityResponseStatusDTO = elligibilityResponseStatusMapper.toDto(elligibilityResponseStatus);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(elligibilityResponseStatusDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the ElligibilityResponseStatus in the database
        List<ElligibilityResponseStatus> elligibilityResponseStatusList = elligibilityResponseStatusRepository
            .findAll()
            .collectList()
            .block();
        assertThat(elligibilityResponseStatusList).hasSize(databaseSizeBeforeCreate + 1);
        ElligibilityResponseStatus testElligibilityResponseStatus = elligibilityResponseStatusList.get(
            elligibilityResponseStatusList.size() - 1
        );
        assertThat(testElligibilityResponseStatus.getElligibilityControlNumber()).isEqualTo(DEFAULT_ELLIGIBILITY_CONTROL_NUMBER);
        assertThat(testElligibilityResponseStatus.getTraceid()).isEqualTo(DEFAULT_TRACEID);
        assertThat(testElligibilityResponseStatus.getSubscriberFirstName()).isEqualTo(DEFAULT_SUBSCRIBER_FIRST_NAME);
        assertThat(testElligibilityResponseStatus.getSubscriberLastName()).isEqualTo(DEFAULT_SUBSCRIBER_LAST_NAME);
        assertThat(testElligibilityResponseStatus.getSubscriberGender()).isEqualTo(DEFAULT_SUBSCRIBER_GENDER);
        assertThat(testElligibilityResponseStatus.getSubscriberDob()).isEqualTo(DEFAULT_SUBSCRIBER_DOB);
        assertThat(testElligibilityResponseStatus.getSubscriberIdentifier()).isEqualTo(DEFAULT_SUBSCRIBER_IDENTIFIER);
        assertThat(testElligibilityResponseStatus.getSubscriberEntitytype()).isEqualTo(DEFAULT_SUBSCRIBER_ENTITYTYPE);
        assertThat(testElligibilityResponseStatus.getSubscriberSsn()).isEqualTo(DEFAULT_SUBSCRIBER_SSN);
        assertThat(testElligibilityResponseStatus.getPayerIdentifier()).isEqualTo(DEFAULT_PAYER_IDENTIFIER);
        assertThat(testElligibilityResponseStatus.getPayerEntitytype()).isEqualTo(DEFAULT_PAYER_ENTITYTYPE);
        assertThat(testElligibilityResponseStatus.getPayerName()).isEqualTo(DEFAULT_PAYER_NAME);
        assertThat(testElligibilityResponseStatus.getPayerIdentification()).isEqualTo(DEFAULT_PAYER_IDENTIFICATION);
        assertThat(testElligibilityResponseStatus.getPlanSsn()).isEqualTo(DEFAULT_PLAN_SSN);
        assertThat(testElligibilityResponseStatus.getPlanDate()).isEqualTo(DEFAULT_PLAN_DATE);
        assertThat(testElligibilityResponseStatus.getPlanStatusCode()).isEqualTo(DEFAULT_PLAN_STATUS_CODE);
        assertThat(testElligibilityResponseStatus.getPlanStatus()).isEqualTo(DEFAULT_PLAN_STATUS);
        assertThat(testElligibilityResponseStatus.getPlanDetails()).isEqualTo(DEFAULT_PLAN_DETAILS);
        assertThat(testElligibilityResponseStatus.getServiceTypeCodes()).isEqualTo(DEFAULT_SERVICE_TYPE_CODES);
        assertThat(testElligibilityResponseStatus.getElligibilityResponseStatusUuid()).isEqualTo(DEFAULT_ELLIGIBILITY_RESPONSE_STATUS_UUID);
    }

    @Test
    void createElligibilityResponseStatusWithExistingId() throws Exception {
        // Create the ElligibilityResponseStatus with an existing ID
        elligibilityResponseStatus.setElligibilityResponseStatusId(1L);
        ElligibilityResponseStatusDTO elligibilityResponseStatusDTO = elligibilityResponseStatusMapper.toDto(elligibilityResponseStatus);

        int databaseSizeBeforeCreate = elligibilityResponseStatusRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(elligibilityResponseStatusDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ElligibilityResponseStatus in the database
        List<ElligibilityResponseStatus> elligibilityResponseStatusList = elligibilityResponseStatusRepository
            .findAll()
            .collectList()
            .block();
        assertThat(elligibilityResponseStatusList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllElligibilityResponseStatuses() {
        // Initialize the database
        elligibilityResponseStatusRepository.save(elligibilityResponseStatus).block();

        // Get all the elligibilityResponseStatusList
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
            .value(hasItem(elligibilityResponseStatus.getElligibilityResponseStatusId().intValue()))
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
            .value(hasItem(DEFAULT_PLAN_DATE.toString()))
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
    void getElligibilityResponseStatus() {
        // Initialize the database
        elligibilityResponseStatusRepository.save(elligibilityResponseStatus).block();

        // Get the elligibilityResponseStatus
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, elligibilityResponseStatus.getElligibilityResponseStatusId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.elligibilityResponseStatusId")
            .value(is(elligibilityResponseStatus.getElligibilityResponseStatusId().intValue()))
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
            .value(is(DEFAULT_PLAN_DATE.toString()))
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
    void getNonExistingElligibilityResponseStatus() {
        // Get the elligibilityResponseStatus
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingElligibilityResponseStatus() throws Exception {
        // Initialize the database
        elligibilityResponseStatusRepository.save(elligibilityResponseStatus).block();

        int databaseSizeBeforeUpdate = elligibilityResponseStatusRepository.findAll().collectList().block().size();

        // Update the elligibilityResponseStatus
        ElligibilityResponseStatus updatedElligibilityResponseStatus = elligibilityResponseStatusRepository
            .findById(elligibilityResponseStatus.getElligibilityResponseStatusId())
            .block();
        updatedElligibilityResponseStatus
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
        ElligibilityResponseStatusDTO elligibilityResponseStatusDTO = elligibilityResponseStatusMapper.toDto(
            updatedElligibilityResponseStatus
        );

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, elligibilityResponseStatusDTO.getElligibilityResponseStatusId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(elligibilityResponseStatusDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ElligibilityResponseStatus in the database
        List<ElligibilityResponseStatus> elligibilityResponseStatusList = elligibilityResponseStatusRepository
            .findAll()
            .collectList()
            .block();
        assertThat(elligibilityResponseStatusList).hasSize(databaseSizeBeforeUpdate);
        ElligibilityResponseStatus testElligibilityResponseStatus = elligibilityResponseStatusList.get(
            elligibilityResponseStatusList.size() - 1
        );
        assertThat(testElligibilityResponseStatus.getElligibilityControlNumber()).isEqualTo(UPDATED_ELLIGIBILITY_CONTROL_NUMBER);
        assertThat(testElligibilityResponseStatus.getTraceid()).isEqualTo(UPDATED_TRACEID);
        assertThat(testElligibilityResponseStatus.getSubscriberFirstName()).isEqualTo(UPDATED_SUBSCRIBER_FIRST_NAME);
        assertThat(testElligibilityResponseStatus.getSubscriberLastName()).isEqualTo(UPDATED_SUBSCRIBER_LAST_NAME);
        assertThat(testElligibilityResponseStatus.getSubscriberGender()).isEqualTo(UPDATED_SUBSCRIBER_GENDER);
        assertThat(testElligibilityResponseStatus.getSubscriberDob()).isEqualTo(UPDATED_SUBSCRIBER_DOB);
        assertThat(testElligibilityResponseStatus.getSubscriberIdentifier()).isEqualTo(UPDATED_SUBSCRIBER_IDENTIFIER);
        assertThat(testElligibilityResponseStatus.getSubscriberEntitytype()).isEqualTo(UPDATED_SUBSCRIBER_ENTITYTYPE);
        assertThat(testElligibilityResponseStatus.getSubscriberSsn()).isEqualTo(UPDATED_SUBSCRIBER_SSN);
        assertThat(testElligibilityResponseStatus.getPayerIdentifier()).isEqualTo(UPDATED_PAYER_IDENTIFIER);
        assertThat(testElligibilityResponseStatus.getPayerEntitytype()).isEqualTo(UPDATED_PAYER_ENTITYTYPE);
        assertThat(testElligibilityResponseStatus.getPayerName()).isEqualTo(UPDATED_PAYER_NAME);
        assertThat(testElligibilityResponseStatus.getPayerIdentification()).isEqualTo(UPDATED_PAYER_IDENTIFICATION);
        assertThat(testElligibilityResponseStatus.getPlanSsn()).isEqualTo(UPDATED_PLAN_SSN);
        assertThat(testElligibilityResponseStatus.getPlanDate()).isEqualTo(UPDATED_PLAN_DATE);
        assertThat(testElligibilityResponseStatus.getPlanStatusCode()).isEqualTo(UPDATED_PLAN_STATUS_CODE);
        assertThat(testElligibilityResponseStatus.getPlanStatus()).isEqualTo(UPDATED_PLAN_STATUS);
        assertThat(testElligibilityResponseStatus.getPlanDetails()).isEqualTo(UPDATED_PLAN_DETAILS);
        assertThat(testElligibilityResponseStatus.getServiceTypeCodes()).isEqualTo(UPDATED_SERVICE_TYPE_CODES);
        assertThat(testElligibilityResponseStatus.getElligibilityResponseStatusUuid()).isEqualTo(UPDATED_ELLIGIBILITY_RESPONSE_STATUS_UUID);
    }

    @Test
    void putNonExistingElligibilityResponseStatus() throws Exception {
        int databaseSizeBeforeUpdate = elligibilityResponseStatusRepository.findAll().collectList().block().size();
        elligibilityResponseStatus.setElligibilityResponseStatusId(count.incrementAndGet());

        // Create the ElligibilityResponseStatus
        ElligibilityResponseStatusDTO elligibilityResponseStatusDTO = elligibilityResponseStatusMapper.toDto(elligibilityResponseStatus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, elligibilityResponseStatusDTO.getElligibilityResponseStatusId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(elligibilityResponseStatusDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ElligibilityResponseStatus in the database
        List<ElligibilityResponseStatus> elligibilityResponseStatusList = elligibilityResponseStatusRepository
            .findAll()
            .collectList()
            .block();
        assertThat(elligibilityResponseStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchElligibilityResponseStatus() throws Exception {
        int databaseSizeBeforeUpdate = elligibilityResponseStatusRepository.findAll().collectList().block().size();
        elligibilityResponseStatus.setElligibilityResponseStatusId(count.incrementAndGet());

        // Create the ElligibilityResponseStatus
        ElligibilityResponseStatusDTO elligibilityResponseStatusDTO = elligibilityResponseStatusMapper.toDto(elligibilityResponseStatus);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(elligibilityResponseStatusDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ElligibilityResponseStatus in the database
        List<ElligibilityResponseStatus> elligibilityResponseStatusList = elligibilityResponseStatusRepository
            .findAll()
            .collectList()
            .block();
        assertThat(elligibilityResponseStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamElligibilityResponseStatus() throws Exception {
        int databaseSizeBeforeUpdate = elligibilityResponseStatusRepository.findAll().collectList().block().size();
        elligibilityResponseStatus.setElligibilityResponseStatusId(count.incrementAndGet());

        // Create the ElligibilityResponseStatus
        ElligibilityResponseStatusDTO elligibilityResponseStatusDTO = elligibilityResponseStatusMapper.toDto(elligibilityResponseStatus);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(elligibilityResponseStatusDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the ElligibilityResponseStatus in the database
        List<ElligibilityResponseStatus> elligibilityResponseStatusList = elligibilityResponseStatusRepository
            .findAll()
            .collectList()
            .block();
        assertThat(elligibilityResponseStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateElligibilityResponseStatusWithPatch() throws Exception {
        // Initialize the database
        elligibilityResponseStatusRepository.save(elligibilityResponseStatus).block();

        int databaseSizeBeforeUpdate = elligibilityResponseStatusRepository.findAll().collectList().block().size();

        // Update the elligibilityResponseStatus using partial update
        ElligibilityResponseStatus partialUpdatedElligibilityResponseStatus = new ElligibilityResponseStatus();
        partialUpdatedElligibilityResponseStatus.setElligibilityResponseStatusId(
            elligibilityResponseStatus.getElligibilityResponseStatusId()
        );

        partialUpdatedElligibilityResponseStatus
            .subscriberFirstName(UPDATED_SUBSCRIBER_FIRST_NAME)
            .subscriberGender(UPDATED_SUBSCRIBER_GENDER)
            .subscriberIdentifier(UPDATED_SUBSCRIBER_IDENTIFIER)
            .subscriberSsn(UPDATED_SUBSCRIBER_SSN)
            .payerIdentifier(UPDATED_PAYER_IDENTIFIER)
            .payerEntitytype(UPDATED_PAYER_ENTITYTYPE)
            .payerIdentification(UPDATED_PAYER_IDENTIFICATION)
            .planDate(UPDATED_PLAN_DATE)
            .planStatus(UPDATED_PLAN_STATUS)
            .serviceTypeCodes(UPDATED_SERVICE_TYPE_CODES);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedElligibilityResponseStatus.getElligibilityResponseStatusId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedElligibilityResponseStatus))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ElligibilityResponseStatus in the database
        List<ElligibilityResponseStatus> elligibilityResponseStatusList = elligibilityResponseStatusRepository
            .findAll()
            .collectList()
            .block();
        assertThat(elligibilityResponseStatusList).hasSize(databaseSizeBeforeUpdate);
        ElligibilityResponseStatus testElligibilityResponseStatus = elligibilityResponseStatusList.get(
            elligibilityResponseStatusList.size() - 1
        );
        assertThat(testElligibilityResponseStatus.getElligibilityControlNumber()).isEqualTo(DEFAULT_ELLIGIBILITY_CONTROL_NUMBER);
        assertThat(testElligibilityResponseStatus.getTraceid()).isEqualTo(DEFAULT_TRACEID);
        assertThat(testElligibilityResponseStatus.getSubscriberFirstName()).isEqualTo(UPDATED_SUBSCRIBER_FIRST_NAME);
        assertThat(testElligibilityResponseStatus.getSubscriberLastName()).isEqualTo(DEFAULT_SUBSCRIBER_LAST_NAME);
        assertThat(testElligibilityResponseStatus.getSubscriberGender()).isEqualTo(UPDATED_SUBSCRIBER_GENDER);
        assertThat(testElligibilityResponseStatus.getSubscriberDob()).isEqualTo(DEFAULT_SUBSCRIBER_DOB);
        assertThat(testElligibilityResponseStatus.getSubscriberIdentifier()).isEqualTo(UPDATED_SUBSCRIBER_IDENTIFIER);
        assertThat(testElligibilityResponseStatus.getSubscriberEntitytype()).isEqualTo(DEFAULT_SUBSCRIBER_ENTITYTYPE);
        assertThat(testElligibilityResponseStatus.getSubscriberSsn()).isEqualTo(UPDATED_SUBSCRIBER_SSN);
        assertThat(testElligibilityResponseStatus.getPayerIdentifier()).isEqualTo(UPDATED_PAYER_IDENTIFIER);
        assertThat(testElligibilityResponseStatus.getPayerEntitytype()).isEqualTo(UPDATED_PAYER_ENTITYTYPE);
        assertThat(testElligibilityResponseStatus.getPayerName()).isEqualTo(DEFAULT_PAYER_NAME);
        assertThat(testElligibilityResponseStatus.getPayerIdentification()).isEqualTo(UPDATED_PAYER_IDENTIFICATION);
        assertThat(testElligibilityResponseStatus.getPlanSsn()).isEqualTo(DEFAULT_PLAN_SSN);
        assertThat(testElligibilityResponseStatus.getPlanDate()).isEqualTo(UPDATED_PLAN_DATE);
        assertThat(testElligibilityResponseStatus.getPlanStatusCode()).isEqualTo(DEFAULT_PLAN_STATUS_CODE);
        assertThat(testElligibilityResponseStatus.getPlanStatus()).isEqualTo(UPDATED_PLAN_STATUS);
        assertThat(testElligibilityResponseStatus.getPlanDetails()).isEqualTo(DEFAULT_PLAN_DETAILS);
        assertThat(testElligibilityResponseStatus.getServiceTypeCodes()).isEqualTo(UPDATED_SERVICE_TYPE_CODES);
        assertThat(testElligibilityResponseStatus.getElligibilityResponseStatusUuid()).isEqualTo(DEFAULT_ELLIGIBILITY_RESPONSE_STATUS_UUID);
    }

    @Test
    void fullUpdateElligibilityResponseStatusWithPatch() throws Exception {
        // Initialize the database
        elligibilityResponseStatusRepository.save(elligibilityResponseStatus).block();

        int databaseSizeBeforeUpdate = elligibilityResponseStatusRepository.findAll().collectList().block().size();

        // Update the elligibilityResponseStatus using partial update
        ElligibilityResponseStatus partialUpdatedElligibilityResponseStatus = new ElligibilityResponseStatus();
        partialUpdatedElligibilityResponseStatus.setElligibilityResponseStatusId(
            elligibilityResponseStatus.getElligibilityResponseStatusId()
        );

        partialUpdatedElligibilityResponseStatus
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
            .uri(ENTITY_API_URL_ID, partialUpdatedElligibilityResponseStatus.getElligibilityResponseStatusId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedElligibilityResponseStatus))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ElligibilityResponseStatus in the database
        List<ElligibilityResponseStatus> elligibilityResponseStatusList = elligibilityResponseStatusRepository
            .findAll()
            .collectList()
            .block();
        assertThat(elligibilityResponseStatusList).hasSize(databaseSizeBeforeUpdate);
        ElligibilityResponseStatus testElligibilityResponseStatus = elligibilityResponseStatusList.get(
            elligibilityResponseStatusList.size() - 1
        );
        assertThat(testElligibilityResponseStatus.getElligibilityControlNumber()).isEqualTo(UPDATED_ELLIGIBILITY_CONTROL_NUMBER);
        assertThat(testElligibilityResponseStatus.getTraceid()).isEqualTo(UPDATED_TRACEID);
        assertThat(testElligibilityResponseStatus.getSubscriberFirstName()).isEqualTo(UPDATED_SUBSCRIBER_FIRST_NAME);
        assertThat(testElligibilityResponseStatus.getSubscriberLastName()).isEqualTo(UPDATED_SUBSCRIBER_LAST_NAME);
        assertThat(testElligibilityResponseStatus.getSubscriberGender()).isEqualTo(UPDATED_SUBSCRIBER_GENDER);
        assertThat(testElligibilityResponseStatus.getSubscriberDob()).isEqualTo(UPDATED_SUBSCRIBER_DOB);
        assertThat(testElligibilityResponseStatus.getSubscriberIdentifier()).isEqualTo(UPDATED_SUBSCRIBER_IDENTIFIER);
        assertThat(testElligibilityResponseStatus.getSubscriberEntitytype()).isEqualTo(UPDATED_SUBSCRIBER_ENTITYTYPE);
        assertThat(testElligibilityResponseStatus.getSubscriberSsn()).isEqualTo(UPDATED_SUBSCRIBER_SSN);
        assertThat(testElligibilityResponseStatus.getPayerIdentifier()).isEqualTo(UPDATED_PAYER_IDENTIFIER);
        assertThat(testElligibilityResponseStatus.getPayerEntitytype()).isEqualTo(UPDATED_PAYER_ENTITYTYPE);
        assertThat(testElligibilityResponseStatus.getPayerName()).isEqualTo(UPDATED_PAYER_NAME);
        assertThat(testElligibilityResponseStatus.getPayerIdentification()).isEqualTo(UPDATED_PAYER_IDENTIFICATION);
        assertThat(testElligibilityResponseStatus.getPlanSsn()).isEqualTo(UPDATED_PLAN_SSN);
        assertThat(testElligibilityResponseStatus.getPlanDate()).isEqualTo(UPDATED_PLAN_DATE);
        assertThat(testElligibilityResponseStatus.getPlanStatusCode()).isEqualTo(UPDATED_PLAN_STATUS_CODE);
        assertThat(testElligibilityResponseStatus.getPlanStatus()).isEqualTo(UPDATED_PLAN_STATUS);
        assertThat(testElligibilityResponseStatus.getPlanDetails()).isEqualTo(UPDATED_PLAN_DETAILS);
        assertThat(testElligibilityResponseStatus.getServiceTypeCodes()).isEqualTo(UPDATED_SERVICE_TYPE_CODES);
        assertThat(testElligibilityResponseStatus.getElligibilityResponseStatusUuid()).isEqualTo(UPDATED_ELLIGIBILITY_RESPONSE_STATUS_UUID);
    }

    @Test
    void patchNonExistingElligibilityResponseStatus() throws Exception {
        int databaseSizeBeforeUpdate = elligibilityResponseStatusRepository.findAll().collectList().block().size();
        elligibilityResponseStatus.setElligibilityResponseStatusId(count.incrementAndGet());

        // Create the ElligibilityResponseStatus
        ElligibilityResponseStatusDTO elligibilityResponseStatusDTO = elligibilityResponseStatusMapper.toDto(elligibilityResponseStatus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, elligibilityResponseStatusDTO.getElligibilityResponseStatusId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(elligibilityResponseStatusDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ElligibilityResponseStatus in the database
        List<ElligibilityResponseStatus> elligibilityResponseStatusList = elligibilityResponseStatusRepository
            .findAll()
            .collectList()
            .block();
        assertThat(elligibilityResponseStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchElligibilityResponseStatus() throws Exception {
        int databaseSizeBeforeUpdate = elligibilityResponseStatusRepository.findAll().collectList().block().size();
        elligibilityResponseStatus.setElligibilityResponseStatusId(count.incrementAndGet());

        // Create the ElligibilityResponseStatus
        ElligibilityResponseStatusDTO elligibilityResponseStatusDTO = elligibilityResponseStatusMapper.toDto(elligibilityResponseStatus);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(elligibilityResponseStatusDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ElligibilityResponseStatus in the database
        List<ElligibilityResponseStatus> elligibilityResponseStatusList = elligibilityResponseStatusRepository
            .findAll()
            .collectList()
            .block();
        assertThat(elligibilityResponseStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamElligibilityResponseStatus() throws Exception {
        int databaseSizeBeforeUpdate = elligibilityResponseStatusRepository.findAll().collectList().block().size();
        elligibilityResponseStatus.setElligibilityResponseStatusId(count.incrementAndGet());

        // Create the ElligibilityResponseStatus
        ElligibilityResponseStatusDTO elligibilityResponseStatusDTO = elligibilityResponseStatusMapper.toDto(elligibilityResponseStatus);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(elligibilityResponseStatusDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the ElligibilityResponseStatus in the database
        List<ElligibilityResponseStatus> elligibilityResponseStatusList = elligibilityResponseStatusRepository
            .findAll()
            .collectList()
            .block();
        assertThat(elligibilityResponseStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteElligibilityResponseStatus() {
        // Initialize the database
        elligibilityResponseStatusRepository.save(elligibilityResponseStatus).block();

        int databaseSizeBeforeDelete = elligibilityResponseStatusRepository.findAll().collectList().block().size();

        // Delete the elligibilityResponseStatus
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, elligibilityResponseStatus.getElligibilityResponseStatusId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<ElligibilityResponseStatus> elligibilityResponseStatusList = elligibilityResponseStatusRepository
            .findAll()
            .collectList()
            .block();
        assertThat(elligibilityResponseStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
