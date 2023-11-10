package com.sunknowledge.dme.rcm.web.rest;

import static com.sunknowledge.dme.rcm.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ParRequestDetails;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.ParRequestDetailsRepository;
import com.sunknowledge.dme.rcm.service.dto.ParRequestDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.ParRequestDetailsMapper;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
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
 * Integration tests for the {@link ParRequestDetailsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class ParRequestDetailsResourceIT {

    private static final String DEFAULT_PAR_REQUEST_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_PAR_REQUEST_TYPE = "BBBBBBBBBB";

    private static final Long DEFAULT_PAR_ID = 1L;
    private static final Long UPDATED_PAR_ID = 2L;

    private static final String DEFAULT_PAR_NO = "AAAAAAAAAA";
    private static final String UPDATED_PAR_NO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PAR_INITIATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PAR_INITIATION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_PAR_NO_EFFETIVE_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PAR_NO_EFFETIVE_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_PAR_NO_EFFETIVE_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PAR_NO_EFFETIVE_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_PAR_AUTHORISED_BY = "AAAAAAAAAA";
    private static final String UPDATED_PAR_AUTHORISED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_PAR_REQUEST_DOC_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PAR_REQUEST_DOC_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PAR_REQUEST_DOC_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_PAR_REQUEST_DOC_LOCATION = "BBBBBBBBBB";

    private static final String DEFAULT_PAR_REQUEST_FAX_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PAR_REQUEST_FAX_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_PAR_REQUEST_FAX_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_PAR_REQUEST_FAX_STATUS = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_PAR_REQUEST_FAX_DATETIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_PAR_REQUEST_FAX_DATETIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_FAX_RESPONSE_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_FAX_RESPONSE_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_PAR_REQUEST_RESPONSE_DOC_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PAR_REQUEST_RESPONSE_DOC_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DOC_QR_CODE = "AAAAAAAAAA";
    private static final String UPDATED_DOC_QR_CODE = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_PAR_REQUEST_DETAILS_UUID = UUID.randomUUID();
    private static final UUID UPDATED_PAR_REQUEST_DETAILS_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/par-request-details";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{parRequestDetailsId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ParRequestDetailsRepository parRequestDetailsRepository;

    @Autowired
    private ParRequestDetailsMapper parRequestDetailsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private ParRequestDetails parRequestDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ParRequestDetails createEntity(EntityManager em) {
        ParRequestDetails parRequestDetails = new ParRequestDetails()
            .parRequestType(DEFAULT_PAR_REQUEST_TYPE)
            .parId(DEFAULT_PAR_ID)
            .parNo(DEFAULT_PAR_NO)
            .parInitiationDate(DEFAULT_PAR_INITIATION_DATE)
            .parNoEffetiveStartDate(DEFAULT_PAR_NO_EFFETIVE_START_DATE)
            .parNoEffetiveEndDate(DEFAULT_PAR_NO_EFFETIVE_END_DATE)
            .parAuthorisedBy(DEFAULT_PAR_AUTHORISED_BY)
            .parRequestDocName(DEFAULT_PAR_REQUEST_DOC_NAME)
            .parRequestDocLocation(DEFAULT_PAR_REQUEST_DOC_LOCATION)
            .parRequestFaxNumber(DEFAULT_PAR_REQUEST_FAX_NUMBER)
            .parRequestFaxStatus(DEFAULT_PAR_REQUEST_FAX_STATUS)
            .parRequestFaxDatetime(DEFAULT_PAR_REQUEST_FAX_DATETIME)
            .faxResponseStatus(DEFAULT_FAX_RESPONSE_STATUS)
            .parRequestResponseDocName(DEFAULT_PAR_REQUEST_RESPONSE_DOC_NAME)
            .docQrCode(DEFAULT_DOC_QR_CODE)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .parRequestDetailsUuid(DEFAULT_PAR_REQUEST_DETAILS_UUID);
        return parRequestDetails;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ParRequestDetails createUpdatedEntity(EntityManager em) {
        ParRequestDetails parRequestDetails = new ParRequestDetails()
            .parRequestType(UPDATED_PAR_REQUEST_TYPE)
            .parId(UPDATED_PAR_ID)
            .parNo(UPDATED_PAR_NO)
            .parInitiationDate(UPDATED_PAR_INITIATION_DATE)
            .parNoEffetiveStartDate(UPDATED_PAR_NO_EFFETIVE_START_DATE)
            .parNoEffetiveEndDate(UPDATED_PAR_NO_EFFETIVE_END_DATE)
            .parAuthorisedBy(UPDATED_PAR_AUTHORISED_BY)
            .parRequestDocName(UPDATED_PAR_REQUEST_DOC_NAME)
            .parRequestDocLocation(UPDATED_PAR_REQUEST_DOC_LOCATION)
            .parRequestFaxNumber(UPDATED_PAR_REQUEST_FAX_NUMBER)
            .parRequestFaxStatus(UPDATED_PAR_REQUEST_FAX_STATUS)
            .parRequestFaxDatetime(UPDATED_PAR_REQUEST_FAX_DATETIME)
            .faxResponseStatus(UPDATED_FAX_RESPONSE_STATUS)
            .parRequestResponseDocName(UPDATED_PAR_REQUEST_RESPONSE_DOC_NAME)
            .docQrCode(UPDATED_DOC_QR_CODE)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .parRequestDetailsUuid(UPDATED_PAR_REQUEST_DETAILS_UUID);
        return parRequestDetails;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(ParRequestDetails.class).block();
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
        parRequestDetails = createEntity(em);
    }

    @Test
    void createParRequestDetails() throws Exception {
        int databaseSizeBeforeCreate = parRequestDetailsRepository.findAll().collectList().block().size();
        // Create the ParRequestDetails
        ParRequestDetailsDTO parRequestDetailsDTO = parRequestDetailsMapper.toDto(parRequestDetails);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(parRequestDetailsDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the ParRequestDetails in the database
        List<ParRequestDetails> parRequestDetailsList = parRequestDetailsRepository.findAll().collectList().block();
        assertThat(parRequestDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        ParRequestDetails testParRequestDetails = parRequestDetailsList.get(parRequestDetailsList.size() - 1);
        assertThat(testParRequestDetails.getParRequestType()).isEqualTo(DEFAULT_PAR_REQUEST_TYPE);
        assertThat(testParRequestDetails.getParId()).isEqualTo(DEFAULT_PAR_ID);
        assertThat(testParRequestDetails.getParNo()).isEqualTo(DEFAULT_PAR_NO);
        assertThat(testParRequestDetails.getParInitiationDate()).isEqualTo(DEFAULT_PAR_INITIATION_DATE);
        assertThat(testParRequestDetails.getParNoEffetiveStartDate()).isEqualTo(DEFAULT_PAR_NO_EFFETIVE_START_DATE);
        assertThat(testParRequestDetails.getParNoEffetiveEndDate()).isEqualTo(DEFAULT_PAR_NO_EFFETIVE_END_DATE);
        assertThat(testParRequestDetails.getParAuthorisedBy()).isEqualTo(DEFAULT_PAR_AUTHORISED_BY);
        assertThat(testParRequestDetails.getParRequestDocName()).isEqualTo(DEFAULT_PAR_REQUEST_DOC_NAME);
        assertThat(testParRequestDetails.getParRequestDocLocation()).isEqualTo(DEFAULT_PAR_REQUEST_DOC_LOCATION);
        assertThat(testParRequestDetails.getParRequestFaxNumber()).isEqualTo(DEFAULT_PAR_REQUEST_FAX_NUMBER);
        assertThat(testParRequestDetails.getParRequestFaxStatus()).isEqualTo(DEFAULT_PAR_REQUEST_FAX_STATUS);
        assertThat(testParRequestDetails.getParRequestFaxDatetime()).isEqualTo(DEFAULT_PAR_REQUEST_FAX_DATETIME);
        assertThat(testParRequestDetails.getFaxResponseStatus()).isEqualTo(DEFAULT_FAX_RESPONSE_STATUS);
        assertThat(testParRequestDetails.getParRequestResponseDocName()).isEqualTo(DEFAULT_PAR_REQUEST_RESPONSE_DOC_NAME);
        assertThat(testParRequestDetails.getDocQrCode()).isEqualTo(DEFAULT_DOC_QR_CODE);
        assertThat(testParRequestDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testParRequestDetails.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testParRequestDetails.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testParRequestDetails.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testParRequestDetails.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testParRequestDetails.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testParRequestDetails.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testParRequestDetails.getParRequestDetailsUuid()).isEqualTo(DEFAULT_PAR_REQUEST_DETAILS_UUID);
    }

    @Test
    void createParRequestDetailsWithExistingId() throws Exception {
        // Create the ParRequestDetails with an existing ID
        parRequestDetails.setParRequestDetailsId(1L);
        ParRequestDetailsDTO parRequestDetailsDTO = parRequestDetailsMapper.toDto(parRequestDetails);

        int databaseSizeBeforeCreate = parRequestDetailsRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(parRequestDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ParRequestDetails in the database
        List<ParRequestDetails> parRequestDetailsList = parRequestDetailsRepository.findAll().collectList().block();
        assertThat(parRequestDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllParRequestDetails() {
        // Initialize the database
        parRequestDetailsRepository.save(parRequestDetails).block();

        // Get all the parRequestDetailsList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=parRequestDetailsId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].parRequestDetailsId")
            .value(hasItem(parRequestDetails.getParRequestDetailsId().intValue()))
            .jsonPath("$.[*].parRequestType")
            .value(hasItem(DEFAULT_PAR_REQUEST_TYPE))
            .jsonPath("$.[*].parId")
            .value(hasItem(DEFAULT_PAR_ID.intValue()))
            .jsonPath("$.[*].parNo")
            .value(hasItem(DEFAULT_PAR_NO))
            .jsonPath("$.[*].parInitiationDate")
            .value(hasItem(DEFAULT_PAR_INITIATION_DATE.toString()))
            .jsonPath("$.[*].parNoEffetiveStartDate")
            .value(hasItem(DEFAULT_PAR_NO_EFFETIVE_START_DATE.toString()))
            .jsonPath("$.[*].parNoEffetiveEndDate")
            .value(hasItem(DEFAULT_PAR_NO_EFFETIVE_END_DATE.toString()))
            .jsonPath("$.[*].parAuthorisedBy")
            .value(hasItem(DEFAULT_PAR_AUTHORISED_BY))
            .jsonPath("$.[*].parRequestDocName")
            .value(hasItem(DEFAULT_PAR_REQUEST_DOC_NAME))
            .jsonPath("$.[*].parRequestDocLocation")
            .value(hasItem(DEFAULT_PAR_REQUEST_DOC_LOCATION))
            .jsonPath("$.[*].parRequestFaxNumber")
            .value(hasItem(DEFAULT_PAR_REQUEST_FAX_NUMBER))
            .jsonPath("$.[*].parRequestFaxStatus")
            .value(hasItem(DEFAULT_PAR_REQUEST_FAX_STATUS))
            .jsonPath("$.[*].parRequestFaxDatetime")
            .value(hasItem(sameInstant(DEFAULT_PAR_REQUEST_FAX_DATETIME)))
            .jsonPath("$.[*].faxResponseStatus")
            .value(hasItem(DEFAULT_FAX_RESPONSE_STATUS))
            .jsonPath("$.[*].parRequestResponseDocName")
            .value(hasItem(DEFAULT_PAR_REQUEST_RESPONSE_DOC_NAME))
            .jsonPath("$.[*].docQrCode")
            .value(hasItem(DEFAULT_DOC_QR_CODE))
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
            .jsonPath("$.[*].parRequestDetailsUuid")
            .value(hasItem(DEFAULT_PAR_REQUEST_DETAILS_UUID.toString()));
    }

    @Test
    void getParRequestDetails() {
        // Initialize the database
        parRequestDetailsRepository.save(parRequestDetails).block();

        // Get the parRequestDetails
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, parRequestDetails.getParRequestDetailsId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.parRequestDetailsId")
            .value(is(parRequestDetails.getParRequestDetailsId().intValue()))
            .jsonPath("$.parRequestType")
            .value(is(DEFAULT_PAR_REQUEST_TYPE))
            .jsonPath("$.parId")
            .value(is(DEFAULT_PAR_ID.intValue()))
            .jsonPath("$.parNo")
            .value(is(DEFAULT_PAR_NO))
            .jsonPath("$.parInitiationDate")
            .value(is(DEFAULT_PAR_INITIATION_DATE.toString()))
            .jsonPath("$.parNoEffetiveStartDate")
            .value(is(DEFAULT_PAR_NO_EFFETIVE_START_DATE.toString()))
            .jsonPath("$.parNoEffetiveEndDate")
            .value(is(DEFAULT_PAR_NO_EFFETIVE_END_DATE.toString()))
            .jsonPath("$.parAuthorisedBy")
            .value(is(DEFAULT_PAR_AUTHORISED_BY))
            .jsonPath("$.parRequestDocName")
            .value(is(DEFAULT_PAR_REQUEST_DOC_NAME))
            .jsonPath("$.parRequestDocLocation")
            .value(is(DEFAULT_PAR_REQUEST_DOC_LOCATION))
            .jsonPath("$.parRequestFaxNumber")
            .value(is(DEFAULT_PAR_REQUEST_FAX_NUMBER))
            .jsonPath("$.parRequestFaxStatus")
            .value(is(DEFAULT_PAR_REQUEST_FAX_STATUS))
            .jsonPath("$.parRequestFaxDatetime")
            .value(is(sameInstant(DEFAULT_PAR_REQUEST_FAX_DATETIME)))
            .jsonPath("$.faxResponseStatus")
            .value(is(DEFAULT_FAX_RESPONSE_STATUS))
            .jsonPath("$.parRequestResponseDocName")
            .value(is(DEFAULT_PAR_REQUEST_RESPONSE_DOC_NAME))
            .jsonPath("$.docQrCode")
            .value(is(DEFAULT_DOC_QR_CODE))
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
            .jsonPath("$.parRequestDetailsUuid")
            .value(is(DEFAULT_PAR_REQUEST_DETAILS_UUID.toString()));
    }

    @Test
    void getNonExistingParRequestDetails() {
        // Get the parRequestDetails
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putNewParRequestDetails() throws Exception {
        // Initialize the database
        parRequestDetailsRepository.save(parRequestDetails).block();

        int databaseSizeBeforeUpdate = parRequestDetailsRepository.findAll().collectList().block().size();

        // Update the parRequestDetails
        ParRequestDetails updatedParRequestDetails = parRequestDetailsRepository
            .findById(parRequestDetails.getParRequestDetailsId())
            .block();
        updatedParRequestDetails
            .parRequestType(UPDATED_PAR_REQUEST_TYPE)
            .parId(UPDATED_PAR_ID)
            .parNo(UPDATED_PAR_NO)
            .parInitiationDate(UPDATED_PAR_INITIATION_DATE)
            .parNoEffetiveStartDate(UPDATED_PAR_NO_EFFETIVE_START_DATE)
            .parNoEffetiveEndDate(UPDATED_PAR_NO_EFFETIVE_END_DATE)
            .parAuthorisedBy(UPDATED_PAR_AUTHORISED_BY)
            .parRequestDocName(UPDATED_PAR_REQUEST_DOC_NAME)
            .parRequestDocLocation(UPDATED_PAR_REQUEST_DOC_LOCATION)
            .parRequestFaxNumber(UPDATED_PAR_REQUEST_FAX_NUMBER)
            .parRequestFaxStatus(UPDATED_PAR_REQUEST_FAX_STATUS)
            .parRequestFaxDatetime(UPDATED_PAR_REQUEST_FAX_DATETIME)
            .faxResponseStatus(UPDATED_FAX_RESPONSE_STATUS)
            .parRequestResponseDocName(UPDATED_PAR_REQUEST_RESPONSE_DOC_NAME)
            .docQrCode(UPDATED_DOC_QR_CODE)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .parRequestDetailsUuid(UPDATED_PAR_REQUEST_DETAILS_UUID);
        ParRequestDetailsDTO parRequestDetailsDTO = parRequestDetailsMapper.toDto(updatedParRequestDetails);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, parRequestDetailsDTO.getParRequestDetailsId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(parRequestDetailsDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ParRequestDetails in the database
        List<ParRequestDetails> parRequestDetailsList = parRequestDetailsRepository.findAll().collectList().block();
        assertThat(parRequestDetailsList).hasSize(databaseSizeBeforeUpdate);
        ParRequestDetails testParRequestDetails = parRequestDetailsList.get(parRequestDetailsList.size() - 1);
        assertThat(testParRequestDetails.getParRequestType()).isEqualTo(UPDATED_PAR_REQUEST_TYPE);
        assertThat(testParRequestDetails.getParId()).isEqualTo(UPDATED_PAR_ID);
        assertThat(testParRequestDetails.getParNo()).isEqualTo(UPDATED_PAR_NO);
        assertThat(testParRequestDetails.getParInitiationDate()).isEqualTo(UPDATED_PAR_INITIATION_DATE);
        assertThat(testParRequestDetails.getParNoEffetiveStartDate()).isEqualTo(UPDATED_PAR_NO_EFFETIVE_START_DATE);
        assertThat(testParRequestDetails.getParNoEffetiveEndDate()).isEqualTo(UPDATED_PAR_NO_EFFETIVE_END_DATE);
        assertThat(testParRequestDetails.getParAuthorisedBy()).isEqualTo(UPDATED_PAR_AUTHORISED_BY);
        assertThat(testParRequestDetails.getParRequestDocName()).isEqualTo(UPDATED_PAR_REQUEST_DOC_NAME);
        assertThat(testParRequestDetails.getParRequestDocLocation()).isEqualTo(UPDATED_PAR_REQUEST_DOC_LOCATION);
        assertThat(testParRequestDetails.getParRequestFaxNumber()).isEqualTo(UPDATED_PAR_REQUEST_FAX_NUMBER);
        assertThat(testParRequestDetails.getParRequestFaxStatus()).isEqualTo(UPDATED_PAR_REQUEST_FAX_STATUS);
        assertThat(testParRequestDetails.getParRequestFaxDatetime()).isEqualTo(UPDATED_PAR_REQUEST_FAX_DATETIME);
        assertThat(testParRequestDetails.getFaxResponseStatus()).isEqualTo(UPDATED_FAX_RESPONSE_STATUS);
        assertThat(testParRequestDetails.getParRequestResponseDocName()).isEqualTo(UPDATED_PAR_REQUEST_RESPONSE_DOC_NAME);
        assertThat(testParRequestDetails.getDocQrCode()).isEqualTo(UPDATED_DOC_QR_CODE);
        assertThat(testParRequestDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testParRequestDetails.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testParRequestDetails.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testParRequestDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testParRequestDetails.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testParRequestDetails.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testParRequestDetails.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testParRequestDetails.getParRequestDetailsUuid()).isEqualTo(UPDATED_PAR_REQUEST_DETAILS_UUID);
    }

    @Test
    void putNonExistingParRequestDetails() throws Exception {
        int databaseSizeBeforeUpdate = parRequestDetailsRepository.findAll().collectList().block().size();
        parRequestDetails.setParRequestDetailsId(count.incrementAndGet());

        // Create the ParRequestDetails
        ParRequestDetailsDTO parRequestDetailsDTO = parRequestDetailsMapper.toDto(parRequestDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, parRequestDetailsDTO.getParRequestDetailsId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(parRequestDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ParRequestDetails in the database
        List<ParRequestDetails> parRequestDetailsList = parRequestDetailsRepository.findAll().collectList().block();
        assertThat(parRequestDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchParRequestDetails() throws Exception {
        int databaseSizeBeforeUpdate = parRequestDetailsRepository.findAll().collectList().block().size();
        parRequestDetails.setParRequestDetailsId(count.incrementAndGet());

        // Create the ParRequestDetails
        ParRequestDetailsDTO parRequestDetailsDTO = parRequestDetailsMapper.toDto(parRequestDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(parRequestDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ParRequestDetails in the database
        List<ParRequestDetails> parRequestDetailsList = parRequestDetailsRepository.findAll().collectList().block();
        assertThat(parRequestDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamParRequestDetails() throws Exception {
        int databaseSizeBeforeUpdate = parRequestDetailsRepository.findAll().collectList().block().size();
        parRequestDetails.setParRequestDetailsId(count.incrementAndGet());

        // Create the ParRequestDetails
        ParRequestDetailsDTO parRequestDetailsDTO = parRequestDetailsMapper.toDto(parRequestDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(parRequestDetailsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the ParRequestDetails in the database
        List<ParRequestDetails> parRequestDetailsList = parRequestDetailsRepository.findAll().collectList().block();
        assertThat(parRequestDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateParRequestDetailsWithPatch() throws Exception {
        // Initialize the database
        parRequestDetailsRepository.save(parRequestDetails).block();

        int databaseSizeBeforeUpdate = parRequestDetailsRepository.findAll().collectList().block().size();

        // Update the parRequestDetails using partial update
        ParRequestDetails partialUpdatedParRequestDetails = new ParRequestDetails();
        partialUpdatedParRequestDetails.setParRequestDetailsId(parRequestDetails.getParRequestDetailsId());

        partialUpdatedParRequestDetails
            .parInitiationDate(UPDATED_PAR_INITIATION_DATE)
            .parNoEffetiveEndDate(UPDATED_PAR_NO_EFFETIVE_END_DATE)
            .parAuthorisedBy(UPDATED_PAR_AUTHORISED_BY)
            .parRequestDocName(UPDATED_PAR_REQUEST_DOC_NAME)
            .parRequestDocLocation(UPDATED_PAR_REQUEST_DOC_LOCATION)
            .parRequestFaxStatus(UPDATED_PAR_REQUEST_FAX_STATUS)
            .faxResponseStatus(UPDATED_FAX_RESPONSE_STATUS)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .parRequestDetailsUuid(UPDATED_PAR_REQUEST_DETAILS_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedParRequestDetails.getParRequestDetailsId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedParRequestDetails))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ParRequestDetails in the database
        List<ParRequestDetails> parRequestDetailsList = parRequestDetailsRepository.findAll().collectList().block();
        assertThat(parRequestDetailsList).hasSize(databaseSizeBeforeUpdate);
        ParRequestDetails testParRequestDetails = parRequestDetailsList.get(parRequestDetailsList.size() - 1);
        assertThat(testParRequestDetails.getParRequestType()).isEqualTo(DEFAULT_PAR_REQUEST_TYPE);
        assertThat(testParRequestDetails.getParId()).isEqualTo(DEFAULT_PAR_ID);
        assertThat(testParRequestDetails.getParNo()).isEqualTo(DEFAULT_PAR_NO);
        assertThat(testParRequestDetails.getParInitiationDate()).isEqualTo(UPDATED_PAR_INITIATION_DATE);
        assertThat(testParRequestDetails.getParNoEffetiveStartDate()).isEqualTo(DEFAULT_PAR_NO_EFFETIVE_START_DATE);
        assertThat(testParRequestDetails.getParNoEffetiveEndDate()).isEqualTo(UPDATED_PAR_NO_EFFETIVE_END_DATE);
        assertThat(testParRequestDetails.getParAuthorisedBy()).isEqualTo(UPDATED_PAR_AUTHORISED_BY);
        assertThat(testParRequestDetails.getParRequestDocName()).isEqualTo(UPDATED_PAR_REQUEST_DOC_NAME);
        assertThat(testParRequestDetails.getParRequestDocLocation()).isEqualTo(UPDATED_PAR_REQUEST_DOC_LOCATION);
        assertThat(testParRequestDetails.getParRequestFaxNumber()).isEqualTo(DEFAULT_PAR_REQUEST_FAX_NUMBER);
        assertThat(testParRequestDetails.getParRequestFaxStatus()).isEqualTo(UPDATED_PAR_REQUEST_FAX_STATUS);
        assertThat(testParRequestDetails.getParRequestFaxDatetime()).isEqualTo(DEFAULT_PAR_REQUEST_FAX_DATETIME);
        assertThat(testParRequestDetails.getFaxResponseStatus()).isEqualTo(UPDATED_FAX_RESPONSE_STATUS);
        assertThat(testParRequestDetails.getParRequestResponseDocName()).isEqualTo(DEFAULT_PAR_REQUEST_RESPONSE_DOC_NAME);
        assertThat(testParRequestDetails.getDocQrCode()).isEqualTo(DEFAULT_DOC_QR_CODE);
        assertThat(testParRequestDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testParRequestDetails.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testParRequestDetails.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testParRequestDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testParRequestDetails.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testParRequestDetails.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testParRequestDetails.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testParRequestDetails.getParRequestDetailsUuid()).isEqualTo(UPDATED_PAR_REQUEST_DETAILS_UUID);
    }

    @Test
    void fullUpdateParRequestDetailsWithPatch() throws Exception {
        // Initialize the database
        parRequestDetailsRepository.save(parRequestDetails).block();

        int databaseSizeBeforeUpdate = parRequestDetailsRepository.findAll().collectList().block().size();

        // Update the parRequestDetails using partial update
        ParRequestDetails partialUpdatedParRequestDetails = new ParRequestDetails();
        partialUpdatedParRequestDetails.setParRequestDetailsId(parRequestDetails.getParRequestDetailsId());

        partialUpdatedParRequestDetails
            .parRequestType(UPDATED_PAR_REQUEST_TYPE)
            .parId(UPDATED_PAR_ID)
            .parNo(UPDATED_PAR_NO)
            .parInitiationDate(UPDATED_PAR_INITIATION_DATE)
            .parNoEffetiveStartDate(UPDATED_PAR_NO_EFFETIVE_START_DATE)
            .parNoEffetiveEndDate(UPDATED_PAR_NO_EFFETIVE_END_DATE)
            .parAuthorisedBy(UPDATED_PAR_AUTHORISED_BY)
            .parRequestDocName(UPDATED_PAR_REQUEST_DOC_NAME)
            .parRequestDocLocation(UPDATED_PAR_REQUEST_DOC_LOCATION)
            .parRequestFaxNumber(UPDATED_PAR_REQUEST_FAX_NUMBER)
            .parRequestFaxStatus(UPDATED_PAR_REQUEST_FAX_STATUS)
            .parRequestFaxDatetime(UPDATED_PAR_REQUEST_FAX_DATETIME)
            .faxResponseStatus(UPDATED_FAX_RESPONSE_STATUS)
            .parRequestResponseDocName(UPDATED_PAR_REQUEST_RESPONSE_DOC_NAME)
            .docQrCode(UPDATED_DOC_QR_CODE)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .parRequestDetailsUuid(UPDATED_PAR_REQUEST_DETAILS_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedParRequestDetails.getParRequestDetailsId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedParRequestDetails))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ParRequestDetails in the database
        List<ParRequestDetails> parRequestDetailsList = parRequestDetailsRepository.findAll().collectList().block();
        assertThat(parRequestDetailsList).hasSize(databaseSizeBeforeUpdate);
        ParRequestDetails testParRequestDetails = parRequestDetailsList.get(parRequestDetailsList.size() - 1);
        assertThat(testParRequestDetails.getParRequestType()).isEqualTo(UPDATED_PAR_REQUEST_TYPE);
        assertThat(testParRequestDetails.getParId()).isEqualTo(UPDATED_PAR_ID);
        assertThat(testParRequestDetails.getParNo()).isEqualTo(UPDATED_PAR_NO);
        assertThat(testParRequestDetails.getParInitiationDate()).isEqualTo(UPDATED_PAR_INITIATION_DATE);
        assertThat(testParRequestDetails.getParNoEffetiveStartDate()).isEqualTo(UPDATED_PAR_NO_EFFETIVE_START_DATE);
        assertThat(testParRequestDetails.getParNoEffetiveEndDate()).isEqualTo(UPDATED_PAR_NO_EFFETIVE_END_DATE);
        assertThat(testParRequestDetails.getParAuthorisedBy()).isEqualTo(UPDATED_PAR_AUTHORISED_BY);
        assertThat(testParRequestDetails.getParRequestDocName()).isEqualTo(UPDATED_PAR_REQUEST_DOC_NAME);
        assertThat(testParRequestDetails.getParRequestDocLocation()).isEqualTo(UPDATED_PAR_REQUEST_DOC_LOCATION);
        assertThat(testParRequestDetails.getParRequestFaxNumber()).isEqualTo(UPDATED_PAR_REQUEST_FAX_NUMBER);
        assertThat(testParRequestDetails.getParRequestFaxStatus()).isEqualTo(UPDATED_PAR_REQUEST_FAX_STATUS);
        assertThat(testParRequestDetails.getParRequestFaxDatetime()).isEqualTo(UPDATED_PAR_REQUEST_FAX_DATETIME);
        assertThat(testParRequestDetails.getFaxResponseStatus()).isEqualTo(UPDATED_FAX_RESPONSE_STATUS);
        assertThat(testParRequestDetails.getParRequestResponseDocName()).isEqualTo(UPDATED_PAR_REQUEST_RESPONSE_DOC_NAME);
        assertThat(testParRequestDetails.getDocQrCode()).isEqualTo(UPDATED_DOC_QR_CODE);
        assertThat(testParRequestDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testParRequestDetails.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testParRequestDetails.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testParRequestDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testParRequestDetails.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testParRequestDetails.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testParRequestDetails.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testParRequestDetails.getParRequestDetailsUuid()).isEqualTo(UPDATED_PAR_REQUEST_DETAILS_UUID);
    }

    @Test
    void patchNonExistingParRequestDetails() throws Exception {
        int databaseSizeBeforeUpdate = parRequestDetailsRepository.findAll().collectList().block().size();
        parRequestDetails.setParRequestDetailsId(count.incrementAndGet());

        // Create the ParRequestDetails
        ParRequestDetailsDTO parRequestDetailsDTO = parRequestDetailsMapper.toDto(parRequestDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, parRequestDetailsDTO.getParRequestDetailsId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(parRequestDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ParRequestDetails in the database
        List<ParRequestDetails> parRequestDetailsList = parRequestDetailsRepository.findAll().collectList().block();
        assertThat(parRequestDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchParRequestDetails() throws Exception {
        int databaseSizeBeforeUpdate = parRequestDetailsRepository.findAll().collectList().block().size();
        parRequestDetails.setParRequestDetailsId(count.incrementAndGet());

        // Create the ParRequestDetails
        ParRequestDetailsDTO parRequestDetailsDTO = parRequestDetailsMapper.toDto(parRequestDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(parRequestDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ParRequestDetails in the database
        List<ParRequestDetails> parRequestDetailsList = parRequestDetailsRepository.findAll().collectList().block();
        assertThat(parRequestDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamParRequestDetails() throws Exception {
        int databaseSizeBeforeUpdate = parRequestDetailsRepository.findAll().collectList().block().size();
        parRequestDetails.setParRequestDetailsId(count.incrementAndGet());

        // Create the ParRequestDetails
        ParRequestDetailsDTO parRequestDetailsDTO = parRequestDetailsMapper.toDto(parRequestDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(parRequestDetailsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the ParRequestDetails in the database
        List<ParRequestDetails> parRequestDetailsList = parRequestDetailsRepository.findAll().collectList().block();
        assertThat(parRequestDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteParRequestDetails() {
        // Initialize the database
        parRequestDetailsRepository.save(parRequestDetails).block();

        int databaseSizeBeforeDelete = parRequestDetailsRepository.findAll().collectList().block().size();

        // Delete the parRequestDetails
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, parRequestDetails.getParRequestDetailsId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<ParRequestDetails> parRequestDetailsList = parRequestDetailsRepository.findAll().collectList().block();
        assertThat(parRequestDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
