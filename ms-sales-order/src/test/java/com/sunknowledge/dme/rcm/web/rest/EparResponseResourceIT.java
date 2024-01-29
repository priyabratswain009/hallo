package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.EparResponse;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.EparResponseRepository;
import com.sunknowledge.dme.rcm.service.dto.EparResponseDTO;
import com.sunknowledge.dme.rcm.service.mapper.EparResponseMapper;
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
 * Integration tests for the {@link EparResponseResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class EparResponseResourceIT {

    private static final Long DEFAULT_EPAR_REQUEST_ID = 1L;
    private static final Long UPDATED_EPAR_REQUEST_ID = 2L;

    private static final Long DEFAULT_PAR_ID = 1L;
    private static final Long UPDATED_PAR_ID = 2L;

    private static final String DEFAULT_PAR_NO = "AAAAAAAAAA";
    private static final String UPDATED_PAR_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PAYER_ID_NO = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_ID_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PAYER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SUBSCRIBER_RELATIONSHIP = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIBER_RELATIONSHIP = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CERTIFICATION_EFFECTIVE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CERTIFICATION_EFFECTIVE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_CERTIFICATION_EXPIRATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CERTIFICATION_EXPIRATION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_REQUEST_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_PLACE_OF_SERVICE = "AAAAAAAAAA";
    private static final String UPDATED_PLACE_OF_SERVICE = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICE_LEVEL = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_LEVEL = "BBBBBBBBBB";

    private static final String DEFAULT_QUANTITY = "AAAAAAAAAA";
    private static final String UPDATED_QUANTITY = "BBBBBBBBBB";

    private static final String DEFAULT_QUANTITY_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_QUANTITY_TYPE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_RESPONSE_CREATE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_RESPONSE_CREATE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_RESPONSE_RESPONSE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_RESPONSE_RESPONSE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_JSON_DATA = "AAAAAAAAAA";
    private static final String UPDATED_JSON_DATA = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_E_PAR_RESPONSE_UUID = UUID.randomUUID();
    private static final UUID UPDATED_E_PAR_RESPONSE_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/epar-responses";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{eparResponseId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private EparResponseRepository eparResponseRepository;

    @Autowired
    private EparResponseMapper eparResponseMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private EparResponse eparResponse;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EparResponse createEntity(EntityManager em) {
        EparResponse eparResponse = new EparResponse()
            .eparRequestId(DEFAULT_EPAR_REQUEST_ID)
            .parId(DEFAULT_PAR_ID)
            .parNo(DEFAULT_PAR_NO)
            .payerIdNo(DEFAULT_PAYER_ID_NO)
            .payerName(DEFAULT_PAYER_NAME)
            .patientFirstName(DEFAULT_PATIENT_FIRST_NAME)
            .patientLastName(DEFAULT_PATIENT_LAST_NAME)
            .subscriberRelationship(DEFAULT_SUBSCRIBER_RELATIONSHIP)
            .certificationEffectiveDate(DEFAULT_CERTIFICATION_EFFECTIVE_DATE)
            .certificationExpirationDate(DEFAULT_CERTIFICATION_EXPIRATION_DATE)
            .requestType(DEFAULT_REQUEST_TYPE)
            .placeOfService(DEFAULT_PLACE_OF_SERVICE)
            .serviceLevel(DEFAULT_SERVICE_LEVEL)
            .quantity(DEFAULT_QUANTITY)
            .quantityType(DEFAULT_QUANTITY_TYPE)
            .responseCreateDate(DEFAULT_RESPONSE_CREATE_DATE)
            .responseResponseDate(DEFAULT_RESPONSE_RESPONSE_DATE)
            .jsonData(DEFAULT_JSON_DATA)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .eParResponseUuid(DEFAULT_E_PAR_RESPONSE_UUID);
        return eparResponse;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EparResponse createUpdatedEntity(EntityManager em) {
        EparResponse eparResponse = new EparResponse()
            .eparRequestId(UPDATED_EPAR_REQUEST_ID)
            .parId(UPDATED_PAR_ID)
            .parNo(UPDATED_PAR_NO)
            .payerIdNo(UPDATED_PAYER_ID_NO)
            .payerName(UPDATED_PAYER_NAME)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .subscriberRelationship(UPDATED_SUBSCRIBER_RELATIONSHIP)
            .certificationEffectiveDate(UPDATED_CERTIFICATION_EFFECTIVE_DATE)
            .certificationExpirationDate(UPDATED_CERTIFICATION_EXPIRATION_DATE)
            .requestType(UPDATED_REQUEST_TYPE)
            .placeOfService(UPDATED_PLACE_OF_SERVICE)
            .serviceLevel(UPDATED_SERVICE_LEVEL)
            .quantity(UPDATED_QUANTITY)
            .quantityType(UPDATED_QUANTITY_TYPE)
            .responseCreateDate(UPDATED_RESPONSE_CREATE_DATE)
            .responseResponseDate(UPDATED_RESPONSE_RESPONSE_DATE)
            .jsonData(UPDATED_JSON_DATA)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .eParResponseUuid(UPDATED_E_PAR_RESPONSE_UUID);
        return eparResponse;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(EparResponse.class).block();
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
        eparResponse = createEntity(em);
    }

    @Test
    void createEparResponse() throws Exception {
        int databaseSizeBeforeCreate = eparResponseRepository.findAll().collectList().block().size();
        // Create the EparResponse
        EparResponseDTO eparResponseDTO = eparResponseMapper.toDto(eparResponse);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(eparResponseDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the EparResponse in the database
        List<EparResponse> eparResponseList = eparResponseRepository.findAll().collectList().block();
        assertThat(eparResponseList).hasSize(databaseSizeBeforeCreate + 1);
        EparResponse testEparResponse = eparResponseList.get(eparResponseList.size() - 1);
        assertThat(testEparResponse.getEparRequestId()).isEqualTo(DEFAULT_EPAR_REQUEST_ID);
        assertThat(testEparResponse.getParId()).isEqualTo(DEFAULT_PAR_ID);
        assertThat(testEparResponse.getParNo()).isEqualTo(DEFAULT_PAR_NO);
        assertThat(testEparResponse.getPayerIdNo()).isEqualTo(DEFAULT_PAYER_ID_NO);
        assertThat(testEparResponse.getPayerName()).isEqualTo(DEFAULT_PAYER_NAME);
        assertThat(testEparResponse.getPatientFirstName()).isEqualTo(DEFAULT_PATIENT_FIRST_NAME);
        assertThat(testEparResponse.getPatientLastName()).isEqualTo(DEFAULT_PATIENT_LAST_NAME);
        assertThat(testEparResponse.getSubscriberRelationship()).isEqualTo(DEFAULT_SUBSCRIBER_RELATIONSHIP);
        assertThat(testEparResponse.getCertificationEffectiveDate()).isEqualTo(DEFAULT_CERTIFICATION_EFFECTIVE_DATE);
        assertThat(testEparResponse.getCertificationExpirationDate()).isEqualTo(DEFAULT_CERTIFICATION_EXPIRATION_DATE);
        assertThat(testEparResponse.getRequestType()).isEqualTo(DEFAULT_REQUEST_TYPE);
        assertThat(testEparResponse.getPlaceOfService()).isEqualTo(DEFAULT_PLACE_OF_SERVICE);
        assertThat(testEparResponse.getServiceLevel()).isEqualTo(DEFAULT_SERVICE_LEVEL);
        assertThat(testEparResponse.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testEparResponse.getQuantityType()).isEqualTo(DEFAULT_QUANTITY_TYPE);
        assertThat(testEparResponse.getResponseCreateDate()).isEqualTo(DEFAULT_RESPONSE_CREATE_DATE);
        assertThat(testEparResponse.getResponseResponseDate()).isEqualTo(DEFAULT_RESPONSE_RESPONSE_DATE);
        assertThat(testEparResponse.getJsonData()).isEqualTo(DEFAULT_JSON_DATA);
        assertThat(testEparResponse.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testEparResponse.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testEparResponse.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testEparResponse.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testEparResponse.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testEparResponse.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testEparResponse.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testEparResponse.geteParResponseUuid()).isEqualTo(DEFAULT_E_PAR_RESPONSE_UUID);
    }

    @Test
    void createEparResponseWithExistingId() throws Exception {
        // Create the EparResponse with an existing ID
        eparResponse.setEparResponseId(1L);
        EparResponseDTO eparResponseDTO = eparResponseMapper.toDto(eparResponse);

        int databaseSizeBeforeCreate = eparResponseRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(eparResponseDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the EparResponse in the database
        List<EparResponse> eparResponseList = eparResponseRepository.findAll().collectList().block();
        assertThat(eparResponseList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllEparResponses() {
        // Initialize the database
        eparResponseRepository.save(eparResponse).block();

        // Get all the eparResponseList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=eparResponseId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].eparResponseId")
            .value(hasItem(eparResponse.getEparResponseId().intValue()))
            .jsonPath("$.[*].eparRequestId")
            .value(hasItem(DEFAULT_EPAR_REQUEST_ID.intValue()))
            .jsonPath("$.[*].parId")
            .value(hasItem(DEFAULT_PAR_ID.intValue()))
            .jsonPath("$.[*].parNo")
            .value(hasItem(DEFAULT_PAR_NO))
            .jsonPath("$.[*].payerIdNo")
            .value(hasItem(DEFAULT_PAYER_ID_NO))
            .jsonPath("$.[*].payerName")
            .value(hasItem(DEFAULT_PAYER_NAME))
            .jsonPath("$.[*].patientFirstName")
            .value(hasItem(DEFAULT_PATIENT_FIRST_NAME))
            .jsonPath("$.[*].patientLastName")
            .value(hasItem(DEFAULT_PATIENT_LAST_NAME))
            .jsonPath("$.[*].subscriberRelationship")
            .value(hasItem(DEFAULT_SUBSCRIBER_RELATIONSHIP))
            .jsonPath("$.[*].certificationEffectiveDate")
            .value(hasItem(DEFAULT_CERTIFICATION_EFFECTIVE_DATE.toString()))
            .jsonPath("$.[*].certificationExpirationDate")
            .value(hasItem(DEFAULT_CERTIFICATION_EXPIRATION_DATE.toString()))
            .jsonPath("$.[*].requestType")
            .value(hasItem(DEFAULT_REQUEST_TYPE))
            .jsonPath("$.[*].placeOfService")
            .value(hasItem(DEFAULT_PLACE_OF_SERVICE))
            .jsonPath("$.[*].serviceLevel")
            .value(hasItem(DEFAULT_SERVICE_LEVEL))
            .jsonPath("$.[*].quantity")
            .value(hasItem(DEFAULT_QUANTITY))
            .jsonPath("$.[*].quantityType")
            .value(hasItem(DEFAULT_QUANTITY_TYPE))
            .jsonPath("$.[*].responseCreateDate")
            .value(hasItem(DEFAULT_RESPONSE_CREATE_DATE.toString()))
            .jsonPath("$.[*].responseResponseDate")
            .value(hasItem(DEFAULT_RESPONSE_RESPONSE_DATE.toString()))
            .jsonPath("$.[*].jsonData")
            .value(hasItem(DEFAULT_JSON_DATA))
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
            .jsonPath("$.[*].eParResponseUuid")
            .value(hasItem(DEFAULT_E_PAR_RESPONSE_UUID.toString()));
    }

    @Test
    void getEparResponse() {
        // Initialize the database
        eparResponseRepository.save(eparResponse).block();

        // Get the eparResponse
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, eparResponse.getEparResponseId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.eparResponseId")
            .value(is(eparResponse.getEparResponseId().intValue()))
            .jsonPath("$.eparRequestId")
            .value(is(DEFAULT_EPAR_REQUEST_ID.intValue()))
            .jsonPath("$.parId")
            .value(is(DEFAULT_PAR_ID.intValue()))
            .jsonPath("$.parNo")
            .value(is(DEFAULT_PAR_NO))
            .jsonPath("$.payerIdNo")
            .value(is(DEFAULT_PAYER_ID_NO))
            .jsonPath("$.payerName")
            .value(is(DEFAULT_PAYER_NAME))
            .jsonPath("$.patientFirstName")
            .value(is(DEFAULT_PATIENT_FIRST_NAME))
            .jsonPath("$.patientLastName")
            .value(is(DEFAULT_PATIENT_LAST_NAME))
            .jsonPath("$.subscriberRelationship")
            .value(is(DEFAULT_SUBSCRIBER_RELATIONSHIP))
            .jsonPath("$.certificationEffectiveDate")
            .value(is(DEFAULT_CERTIFICATION_EFFECTIVE_DATE.toString()))
            .jsonPath("$.certificationExpirationDate")
            .value(is(DEFAULT_CERTIFICATION_EXPIRATION_DATE.toString()))
            .jsonPath("$.requestType")
            .value(is(DEFAULT_REQUEST_TYPE))
            .jsonPath("$.placeOfService")
            .value(is(DEFAULT_PLACE_OF_SERVICE))
            .jsonPath("$.serviceLevel")
            .value(is(DEFAULT_SERVICE_LEVEL))
            .jsonPath("$.quantity")
            .value(is(DEFAULT_QUANTITY))
            .jsonPath("$.quantityType")
            .value(is(DEFAULT_QUANTITY_TYPE))
            .jsonPath("$.responseCreateDate")
            .value(is(DEFAULT_RESPONSE_CREATE_DATE.toString()))
            .jsonPath("$.responseResponseDate")
            .value(is(DEFAULT_RESPONSE_RESPONSE_DATE.toString()))
            .jsonPath("$.jsonData")
            .value(is(DEFAULT_JSON_DATA))
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
            .jsonPath("$.eParResponseUuid")
            .value(is(DEFAULT_E_PAR_RESPONSE_UUID.toString()));
    }

    @Test
    void getNonExistingEparResponse() {
        // Get the eparResponse
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingEparResponse() throws Exception {
        // Initialize the database
        eparResponseRepository.save(eparResponse).block();

        int databaseSizeBeforeUpdate = eparResponseRepository.findAll().collectList().block().size();

        // Update the eparResponse
        EparResponse updatedEparResponse = eparResponseRepository.findById(eparResponse.getEparResponseId()).block();
        updatedEparResponse
            .eparRequestId(UPDATED_EPAR_REQUEST_ID)
            .parId(UPDATED_PAR_ID)
            .parNo(UPDATED_PAR_NO)
            .payerIdNo(UPDATED_PAYER_ID_NO)
            .payerName(UPDATED_PAYER_NAME)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .subscriberRelationship(UPDATED_SUBSCRIBER_RELATIONSHIP)
            .certificationEffectiveDate(UPDATED_CERTIFICATION_EFFECTIVE_DATE)
            .certificationExpirationDate(UPDATED_CERTIFICATION_EXPIRATION_DATE)
            .requestType(UPDATED_REQUEST_TYPE)
            .placeOfService(UPDATED_PLACE_OF_SERVICE)
            .serviceLevel(UPDATED_SERVICE_LEVEL)
            .quantity(UPDATED_QUANTITY)
            .quantityType(UPDATED_QUANTITY_TYPE)
            .responseCreateDate(UPDATED_RESPONSE_CREATE_DATE)
            .responseResponseDate(UPDATED_RESPONSE_RESPONSE_DATE)
            .jsonData(UPDATED_JSON_DATA)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .eParResponseUuid(UPDATED_E_PAR_RESPONSE_UUID);
        EparResponseDTO eparResponseDTO = eparResponseMapper.toDto(updatedEparResponse);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, eparResponseDTO.getEparResponseId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(eparResponseDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the EparResponse in the database
        List<EparResponse> eparResponseList = eparResponseRepository.findAll().collectList().block();
        assertThat(eparResponseList).hasSize(databaseSizeBeforeUpdate);
        EparResponse testEparResponse = eparResponseList.get(eparResponseList.size() - 1);
        assertThat(testEparResponse.getEparRequestId()).isEqualTo(UPDATED_EPAR_REQUEST_ID);
        assertThat(testEparResponse.getParId()).isEqualTo(UPDATED_PAR_ID);
        assertThat(testEparResponse.getParNo()).isEqualTo(UPDATED_PAR_NO);
        assertThat(testEparResponse.getPayerIdNo()).isEqualTo(UPDATED_PAYER_ID_NO);
        assertThat(testEparResponse.getPayerName()).isEqualTo(UPDATED_PAYER_NAME);
        assertThat(testEparResponse.getPatientFirstName()).isEqualTo(UPDATED_PATIENT_FIRST_NAME);
        assertThat(testEparResponse.getPatientLastName()).isEqualTo(UPDATED_PATIENT_LAST_NAME);
        assertThat(testEparResponse.getSubscriberRelationship()).isEqualTo(UPDATED_SUBSCRIBER_RELATIONSHIP);
        assertThat(testEparResponse.getCertificationEffectiveDate()).isEqualTo(UPDATED_CERTIFICATION_EFFECTIVE_DATE);
        assertThat(testEparResponse.getCertificationExpirationDate()).isEqualTo(UPDATED_CERTIFICATION_EXPIRATION_DATE);
        assertThat(testEparResponse.getRequestType()).isEqualTo(UPDATED_REQUEST_TYPE);
        assertThat(testEparResponse.getPlaceOfService()).isEqualTo(UPDATED_PLACE_OF_SERVICE);
        assertThat(testEparResponse.getServiceLevel()).isEqualTo(UPDATED_SERVICE_LEVEL);
        assertThat(testEparResponse.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testEparResponse.getQuantityType()).isEqualTo(UPDATED_QUANTITY_TYPE);
        assertThat(testEparResponse.getResponseCreateDate()).isEqualTo(UPDATED_RESPONSE_CREATE_DATE);
        assertThat(testEparResponse.getResponseResponseDate()).isEqualTo(UPDATED_RESPONSE_RESPONSE_DATE);
        assertThat(testEparResponse.getJsonData()).isEqualTo(UPDATED_JSON_DATA);
        assertThat(testEparResponse.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testEparResponse.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testEparResponse.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testEparResponse.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testEparResponse.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testEparResponse.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testEparResponse.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testEparResponse.geteParResponseUuid()).isEqualTo(UPDATED_E_PAR_RESPONSE_UUID);
    }

    @Test
    void putNonExistingEparResponse() throws Exception {
        int databaseSizeBeforeUpdate = eparResponseRepository.findAll().collectList().block().size();
        eparResponse.setEparResponseId(count.incrementAndGet());

        // Create the EparResponse
        EparResponseDTO eparResponseDTO = eparResponseMapper.toDto(eparResponse);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, eparResponseDTO.getEparResponseId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(eparResponseDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the EparResponse in the database
        List<EparResponse> eparResponseList = eparResponseRepository.findAll().collectList().block();
        assertThat(eparResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchEparResponse() throws Exception {
        int databaseSizeBeforeUpdate = eparResponseRepository.findAll().collectList().block().size();
        eparResponse.setEparResponseId(count.incrementAndGet());

        // Create the EparResponse
        EparResponseDTO eparResponseDTO = eparResponseMapper.toDto(eparResponse);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(eparResponseDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the EparResponse in the database
        List<EparResponse> eparResponseList = eparResponseRepository.findAll().collectList().block();
        assertThat(eparResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamEparResponse() throws Exception {
        int databaseSizeBeforeUpdate = eparResponseRepository.findAll().collectList().block().size();
        eparResponse.setEparResponseId(count.incrementAndGet());

        // Create the EparResponse
        EparResponseDTO eparResponseDTO = eparResponseMapper.toDto(eparResponse);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(eparResponseDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the EparResponse in the database
        List<EparResponse> eparResponseList = eparResponseRepository.findAll().collectList().block();
        assertThat(eparResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateEparResponseWithPatch() throws Exception {
        // Initialize the database
        eparResponseRepository.save(eparResponse).block();

        int databaseSizeBeforeUpdate = eparResponseRepository.findAll().collectList().block().size();

        // Update the eparResponse using partial update
        EparResponse partialUpdatedEparResponse = new EparResponse();
        partialUpdatedEparResponse.setEparResponseId(eparResponse.getEparResponseId());

        partialUpdatedEparResponse
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .subscriberRelationship(UPDATED_SUBSCRIBER_RELATIONSHIP)
            .certificationExpirationDate(UPDATED_CERTIFICATION_EXPIRATION_DATE)
            .requestType(UPDATED_REQUEST_TYPE)
            .serviceLevel(UPDATED_SERVICE_LEVEL)
            .quantity(UPDATED_QUANTITY)
            .responseResponseDate(UPDATED_RESPONSE_RESPONSE_DATE)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .eParResponseUuid(UPDATED_E_PAR_RESPONSE_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedEparResponse.getEparResponseId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedEparResponse))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the EparResponse in the database
        List<EparResponse> eparResponseList = eparResponseRepository.findAll().collectList().block();
        assertThat(eparResponseList).hasSize(databaseSizeBeforeUpdate);
        EparResponse testEparResponse = eparResponseList.get(eparResponseList.size() - 1);
        assertThat(testEparResponse.getEparRequestId()).isEqualTo(DEFAULT_EPAR_REQUEST_ID);
        assertThat(testEparResponse.getParId()).isEqualTo(DEFAULT_PAR_ID);
        assertThat(testEparResponse.getParNo()).isEqualTo(DEFAULT_PAR_NO);
        assertThat(testEparResponse.getPayerIdNo()).isEqualTo(DEFAULT_PAYER_ID_NO);
        assertThat(testEparResponse.getPayerName()).isEqualTo(DEFAULT_PAYER_NAME);
        assertThat(testEparResponse.getPatientFirstName()).isEqualTo(UPDATED_PATIENT_FIRST_NAME);
        assertThat(testEparResponse.getPatientLastName()).isEqualTo(UPDATED_PATIENT_LAST_NAME);
        assertThat(testEparResponse.getSubscriberRelationship()).isEqualTo(UPDATED_SUBSCRIBER_RELATIONSHIP);
        assertThat(testEparResponse.getCertificationEffectiveDate()).isEqualTo(DEFAULT_CERTIFICATION_EFFECTIVE_DATE);
        assertThat(testEparResponse.getCertificationExpirationDate()).isEqualTo(UPDATED_CERTIFICATION_EXPIRATION_DATE);
        assertThat(testEparResponse.getRequestType()).isEqualTo(UPDATED_REQUEST_TYPE);
        assertThat(testEparResponse.getPlaceOfService()).isEqualTo(DEFAULT_PLACE_OF_SERVICE);
        assertThat(testEparResponse.getServiceLevel()).isEqualTo(UPDATED_SERVICE_LEVEL);
        assertThat(testEparResponse.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testEparResponse.getQuantityType()).isEqualTo(DEFAULT_QUANTITY_TYPE);
        assertThat(testEparResponse.getResponseCreateDate()).isEqualTo(DEFAULT_RESPONSE_CREATE_DATE);
        assertThat(testEparResponse.getResponseResponseDate()).isEqualTo(UPDATED_RESPONSE_RESPONSE_DATE);
        assertThat(testEparResponse.getJsonData()).isEqualTo(DEFAULT_JSON_DATA);
        assertThat(testEparResponse.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testEparResponse.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testEparResponse.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testEparResponse.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testEparResponse.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testEparResponse.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testEparResponse.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testEparResponse.geteParResponseUuid()).isEqualTo(UPDATED_E_PAR_RESPONSE_UUID);
    }

    @Test
    void fullUpdateEparResponseWithPatch() throws Exception {
        // Initialize the database
        eparResponseRepository.save(eparResponse).block();

        int databaseSizeBeforeUpdate = eparResponseRepository.findAll().collectList().block().size();

        // Update the eparResponse using partial update
        EparResponse partialUpdatedEparResponse = new EparResponse();
        partialUpdatedEparResponse.setEparResponseId(eparResponse.getEparResponseId());

        partialUpdatedEparResponse
            .eparRequestId(UPDATED_EPAR_REQUEST_ID)
            .parId(UPDATED_PAR_ID)
            .parNo(UPDATED_PAR_NO)
            .payerIdNo(UPDATED_PAYER_ID_NO)
            .payerName(UPDATED_PAYER_NAME)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .subscriberRelationship(UPDATED_SUBSCRIBER_RELATIONSHIP)
            .certificationEffectiveDate(UPDATED_CERTIFICATION_EFFECTIVE_DATE)
            .certificationExpirationDate(UPDATED_CERTIFICATION_EXPIRATION_DATE)
            .requestType(UPDATED_REQUEST_TYPE)
            .placeOfService(UPDATED_PLACE_OF_SERVICE)
            .serviceLevel(UPDATED_SERVICE_LEVEL)
            .quantity(UPDATED_QUANTITY)
            .quantityType(UPDATED_QUANTITY_TYPE)
            .responseCreateDate(UPDATED_RESPONSE_CREATE_DATE)
            .responseResponseDate(UPDATED_RESPONSE_RESPONSE_DATE)
            .jsonData(UPDATED_JSON_DATA)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .eParResponseUuid(UPDATED_E_PAR_RESPONSE_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedEparResponse.getEparResponseId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedEparResponse))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the EparResponse in the database
        List<EparResponse> eparResponseList = eparResponseRepository.findAll().collectList().block();
        assertThat(eparResponseList).hasSize(databaseSizeBeforeUpdate);
        EparResponse testEparResponse = eparResponseList.get(eparResponseList.size() - 1);
        assertThat(testEparResponse.getEparRequestId()).isEqualTo(UPDATED_EPAR_REQUEST_ID);
        assertThat(testEparResponse.getParId()).isEqualTo(UPDATED_PAR_ID);
        assertThat(testEparResponse.getParNo()).isEqualTo(UPDATED_PAR_NO);
        assertThat(testEparResponse.getPayerIdNo()).isEqualTo(UPDATED_PAYER_ID_NO);
        assertThat(testEparResponse.getPayerName()).isEqualTo(UPDATED_PAYER_NAME);
        assertThat(testEparResponse.getPatientFirstName()).isEqualTo(UPDATED_PATIENT_FIRST_NAME);
        assertThat(testEparResponse.getPatientLastName()).isEqualTo(UPDATED_PATIENT_LAST_NAME);
        assertThat(testEparResponse.getSubscriberRelationship()).isEqualTo(UPDATED_SUBSCRIBER_RELATIONSHIP);
        assertThat(testEparResponse.getCertificationEffectiveDate()).isEqualTo(UPDATED_CERTIFICATION_EFFECTIVE_DATE);
        assertThat(testEparResponse.getCertificationExpirationDate()).isEqualTo(UPDATED_CERTIFICATION_EXPIRATION_DATE);
        assertThat(testEparResponse.getRequestType()).isEqualTo(UPDATED_REQUEST_TYPE);
        assertThat(testEparResponse.getPlaceOfService()).isEqualTo(UPDATED_PLACE_OF_SERVICE);
        assertThat(testEparResponse.getServiceLevel()).isEqualTo(UPDATED_SERVICE_LEVEL);
        assertThat(testEparResponse.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testEparResponse.getQuantityType()).isEqualTo(UPDATED_QUANTITY_TYPE);
        assertThat(testEparResponse.getResponseCreateDate()).isEqualTo(UPDATED_RESPONSE_CREATE_DATE);
        assertThat(testEparResponse.getResponseResponseDate()).isEqualTo(UPDATED_RESPONSE_RESPONSE_DATE);
        assertThat(testEparResponse.getJsonData()).isEqualTo(UPDATED_JSON_DATA);
        assertThat(testEparResponse.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testEparResponse.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testEparResponse.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testEparResponse.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testEparResponse.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testEparResponse.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testEparResponse.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testEparResponse.geteParResponseUuid()).isEqualTo(UPDATED_E_PAR_RESPONSE_UUID);
    }

    @Test
    void patchNonExistingEparResponse() throws Exception {
        int databaseSizeBeforeUpdate = eparResponseRepository.findAll().collectList().block().size();
        eparResponse.setEparResponseId(count.incrementAndGet());

        // Create the EparResponse
        EparResponseDTO eparResponseDTO = eparResponseMapper.toDto(eparResponse);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, eparResponseDTO.getEparResponseId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(eparResponseDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the EparResponse in the database
        List<EparResponse> eparResponseList = eparResponseRepository.findAll().collectList().block();
        assertThat(eparResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchEparResponse() throws Exception {
        int databaseSizeBeforeUpdate = eparResponseRepository.findAll().collectList().block().size();
        eparResponse.setEparResponseId(count.incrementAndGet());

        // Create the EparResponse
        EparResponseDTO eparResponseDTO = eparResponseMapper.toDto(eparResponse);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(eparResponseDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the EparResponse in the database
        List<EparResponse> eparResponseList = eparResponseRepository.findAll().collectList().block();
        assertThat(eparResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamEparResponse() throws Exception {
        int databaseSizeBeforeUpdate = eparResponseRepository.findAll().collectList().block().size();
        eparResponse.setEparResponseId(count.incrementAndGet());

        // Create the EparResponse
        EparResponseDTO eparResponseDTO = eparResponseMapper.toDto(eparResponse);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(eparResponseDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the EparResponse in the database
        List<EparResponse> eparResponseList = eparResponseRepository.findAll().collectList().block();
        assertThat(eparResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteEparResponse() {
        // Initialize the database
        eparResponseRepository.save(eparResponse).block();

        int databaseSizeBeforeDelete = eparResponseRepository.findAll().collectList().block().size();

        // Delete the eparResponse
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, eparResponse.getEparResponseId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<EparResponse> eparResponseList = eparResponseRepository.findAll().collectList().block();
        assertThat(eparResponseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
