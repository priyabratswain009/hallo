package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.EfaxResponse;
import com.sunknowledge.dme.rcm.repository.EfaxResponseRepository;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.service.dto.EfaxResponseDTO;
import com.sunknowledge.dme.rcm.service.mapper.EfaxResponseMapper;
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
 * Integration tests for the {@link EfaxResponseResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class EfaxResponseResourceIT {

    private static final LocalDate DEFAULT_EFAX_RECEIVED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_EFAX_RECEIVED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SENDER_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_SENDER_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL_SUBJECT_LINE = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_SUBJECT_LINE = "BBBBBBBBBB";

    private static final String DEFAULT_ATTACHMENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ATTACHMENT_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_QR_DECODED = false;
    private static final Boolean UPDATED_IS_QR_DECODED = true;

    private static final String DEFAULT_QR_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_QR_VALUE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_CMN = false;
    private static final Boolean UPDATED_IS_CMN = true;

    private static final Boolean DEFAULT_IS_PAR = false;
    private static final Boolean UPDATED_IS_PAR = true;

    private static final String DEFAULT_PATIENT_ID_NO = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_ID_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CMN_ID_NO = "AAAAAAAAAA";
    private static final String UPDATED_CMN_ID_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PAR_ID_NO = "AAAAAAAAAA";
    private static final String UPDATED_PAR_ID_NO = "BBBBBBBBBB";

    private static final Long DEFAULT_SO_ID = 1L;
    private static final Long UPDATED_SO_ID = 2L;

    private static final String DEFAULT_SO_NO = "AAAAAAAAAA";
    private static final String UPDATED_SO_NO = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_MANUALLY_TAGGED = false;
    private static final Boolean UPDATED_IS_MANUALLY_TAGGED = true;

    private static final Boolean DEFAULT_IS_PATIENT_RECORD = false;
    private static final Boolean UPDATED_IS_PATIENT_RECORD = true;

    private static final String DEFAULT_EFAX_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_EFAX_NUMBER = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_PO_ACK = false;
    private static final Boolean UPDATED_IS_PO_ACK = true;

    private static final String DEFAULT_DOCUMENT_RENAME_TO = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENT_RENAME_TO = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final UUID DEFAULT_EFAX_RESPONSE_UUID = UUID.randomUUID();
    private static final UUID UPDATED_EFAX_RESPONSE_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/efax-responses";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{efaxResponseId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private EfaxResponseRepository efaxResponseRepository;

    @Autowired
    private EfaxResponseMapper efaxResponseMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private EfaxResponse efaxResponse;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EfaxResponse createEntity(EntityManager em) {
        EfaxResponse efaxResponse = new EfaxResponse()
            .efaxReceivedDate(DEFAULT_EFAX_RECEIVED_DATE)
            .senderEmail(DEFAULT_SENDER_EMAIL)
            .emailSubjectLine(DEFAULT_EMAIL_SUBJECT_LINE)
            .attachmentName(DEFAULT_ATTACHMENT_NAME)
            .isQrDecoded(DEFAULT_IS_QR_DECODED)
            .qrValue(DEFAULT_QR_VALUE)
            .isCmn(DEFAULT_IS_CMN)
            .isPar(DEFAULT_IS_PAR)
            .patientIdNo(DEFAULT_PATIENT_ID_NO)
            .patientFirstName(DEFAULT_PATIENT_FIRST_NAME)
            .patientLastName(DEFAULT_PATIENT_LAST_NAME)
            .cmnIdNo(DEFAULT_CMN_ID_NO)
            .parIdNo(DEFAULT_PAR_ID_NO)
            .soId(DEFAULT_SO_ID)
            .soNo(DEFAULT_SO_NO)
            .isManuallyTagged(DEFAULT_IS_MANUALLY_TAGGED)
            .isPatientRecord(DEFAULT_IS_PATIENT_RECORD)
            .efaxNumber(DEFAULT_EFAX_NUMBER)
            .isPoAck(DEFAULT_IS_PO_ACK)
            .documentRenameTo(DEFAULT_DOCUMENT_RENAME_TO)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .status(DEFAULT_STATUS)
            .efaxResponseUuid(DEFAULT_EFAX_RESPONSE_UUID);
        return efaxResponse;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EfaxResponse createUpdatedEntity(EntityManager em) {
        EfaxResponse efaxResponse = new EfaxResponse()
            .efaxReceivedDate(UPDATED_EFAX_RECEIVED_DATE)
            .senderEmail(UPDATED_SENDER_EMAIL)
            .emailSubjectLine(UPDATED_EMAIL_SUBJECT_LINE)
            .attachmentName(UPDATED_ATTACHMENT_NAME)
            .isQrDecoded(UPDATED_IS_QR_DECODED)
            .qrValue(UPDATED_QR_VALUE)
            .isCmn(UPDATED_IS_CMN)
            .isPar(UPDATED_IS_PAR)
            .patientIdNo(UPDATED_PATIENT_ID_NO)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .cmnIdNo(UPDATED_CMN_ID_NO)
            .parIdNo(UPDATED_PAR_ID_NO)
            .soId(UPDATED_SO_ID)
            .soNo(UPDATED_SO_NO)
            .isManuallyTagged(UPDATED_IS_MANUALLY_TAGGED)
            .isPatientRecord(UPDATED_IS_PATIENT_RECORD)
            .efaxNumber(UPDATED_EFAX_NUMBER)
            .isPoAck(UPDATED_IS_PO_ACK)
            .documentRenameTo(UPDATED_DOCUMENT_RENAME_TO)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .status(UPDATED_STATUS)
            .efaxResponseUuid(UPDATED_EFAX_RESPONSE_UUID);
        return efaxResponse;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(EfaxResponse.class).block();
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
        efaxResponse = createEntity(em);
    }

    @Test
    void createEfaxResponse() throws Exception {
        int databaseSizeBeforeCreate = efaxResponseRepository.findAll().collectList().block().size();
        // Create the EfaxResponse
        EfaxResponseDTO efaxResponseDTO = efaxResponseMapper.toDto(efaxResponse);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(efaxResponseDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the EfaxResponse in the database
        List<EfaxResponse> efaxResponseList = efaxResponseRepository.findAll().collectList().block();
        assertThat(efaxResponseList).hasSize(databaseSizeBeforeCreate + 1);
        EfaxResponse testEfaxResponse = efaxResponseList.get(efaxResponseList.size() - 1);
        assertThat(testEfaxResponse.getEfaxReceivedDate()).isEqualTo(DEFAULT_EFAX_RECEIVED_DATE);
        assertThat(testEfaxResponse.getSenderEmail()).isEqualTo(DEFAULT_SENDER_EMAIL);
        assertThat(testEfaxResponse.getEmailSubjectLine()).isEqualTo(DEFAULT_EMAIL_SUBJECT_LINE);
        assertThat(testEfaxResponse.getAttachmentName()).isEqualTo(DEFAULT_ATTACHMENT_NAME);
        assertThat(testEfaxResponse.getIsQrDecoded()).isEqualTo(DEFAULT_IS_QR_DECODED);
        assertThat(testEfaxResponse.getQrValue()).isEqualTo(DEFAULT_QR_VALUE);
        assertThat(testEfaxResponse.getIsCmn()).isEqualTo(DEFAULT_IS_CMN);
        assertThat(testEfaxResponse.getIsPar()).isEqualTo(DEFAULT_IS_PAR);
        assertThat(testEfaxResponse.getPatientIdNo()).isEqualTo(DEFAULT_PATIENT_ID_NO);
        assertThat(testEfaxResponse.getPatientFirstName()).isEqualTo(DEFAULT_PATIENT_FIRST_NAME);
        assertThat(testEfaxResponse.getPatientLastName()).isEqualTo(DEFAULT_PATIENT_LAST_NAME);
        assertThat(testEfaxResponse.getCmnIdNo()).isEqualTo(DEFAULT_CMN_ID_NO);
        assertThat(testEfaxResponse.getParIdNo()).isEqualTo(DEFAULT_PAR_ID_NO);
        assertThat(testEfaxResponse.getSoId()).isEqualTo(DEFAULT_SO_ID);
        assertThat(testEfaxResponse.getSoNo()).isEqualTo(DEFAULT_SO_NO);
        assertThat(testEfaxResponse.getIsManuallyTagged()).isEqualTo(DEFAULT_IS_MANUALLY_TAGGED);
        assertThat(testEfaxResponse.getIsPatientRecord()).isEqualTo(DEFAULT_IS_PATIENT_RECORD);
        assertThat(testEfaxResponse.getEfaxNumber()).isEqualTo(DEFAULT_EFAX_NUMBER);
        assertThat(testEfaxResponse.getIsPoAck()).isEqualTo(DEFAULT_IS_PO_ACK);
        assertThat(testEfaxResponse.getDocumentRenameTo()).isEqualTo(DEFAULT_DOCUMENT_RENAME_TO);
        assertThat(testEfaxResponse.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testEfaxResponse.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testEfaxResponse.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testEfaxResponse.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testEfaxResponse.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testEfaxResponse.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testEfaxResponse.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testEfaxResponse.getEfaxResponseUuid()).isEqualTo(DEFAULT_EFAX_RESPONSE_UUID);
    }

    @Test
    void createEfaxResponseWithExistingId() throws Exception {
        // Create the EfaxResponse with an existing ID
        efaxResponse.setEfaxResponseId(1L);
        EfaxResponseDTO efaxResponseDTO = efaxResponseMapper.toDto(efaxResponse);

        int databaseSizeBeforeCreate = efaxResponseRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(efaxResponseDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the EfaxResponse in the database
        List<EfaxResponse> efaxResponseList = efaxResponseRepository.findAll().collectList().block();
        assertThat(efaxResponseList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllEfaxResponses() {
        // Initialize the database
        efaxResponseRepository.save(efaxResponse).block();

        // Get all the efaxResponseList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=efaxResponseId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].efaxResponseId")
            .value(hasItem(efaxResponse.getEfaxResponseId().intValue()))
            .jsonPath("$.[*].efaxReceivedDate")
            .value(hasItem(DEFAULT_EFAX_RECEIVED_DATE.toString()))
            .jsonPath("$.[*].senderEmail")
            .value(hasItem(DEFAULT_SENDER_EMAIL))
            .jsonPath("$.[*].emailSubjectLine")
            .value(hasItem(DEFAULT_EMAIL_SUBJECT_LINE))
            .jsonPath("$.[*].attachmentName")
            .value(hasItem(DEFAULT_ATTACHMENT_NAME))
            .jsonPath("$.[*].isQrDecoded")
            .value(hasItem(DEFAULT_IS_QR_DECODED.booleanValue()))
            .jsonPath("$.[*].qrValue")
            .value(hasItem(DEFAULT_QR_VALUE))
            .jsonPath("$.[*].isCmn")
            .value(hasItem(DEFAULT_IS_CMN.booleanValue()))
            .jsonPath("$.[*].isPar")
            .value(hasItem(DEFAULT_IS_PAR.booleanValue()))
            .jsonPath("$.[*].patientIdNo")
            .value(hasItem(DEFAULT_PATIENT_ID_NO))
            .jsonPath("$.[*].patientFirstName")
            .value(hasItem(DEFAULT_PATIENT_FIRST_NAME))
            .jsonPath("$.[*].patientLastName")
            .value(hasItem(DEFAULT_PATIENT_LAST_NAME))
            .jsonPath("$.[*].cmnIdNo")
            .value(hasItem(DEFAULT_CMN_ID_NO))
            .jsonPath("$.[*].parIdNo")
            .value(hasItem(DEFAULT_PAR_ID_NO))
            .jsonPath("$.[*].soId")
            .value(hasItem(DEFAULT_SO_ID.intValue()))
            .jsonPath("$.[*].soNo")
            .value(hasItem(DEFAULT_SO_NO))
            .jsonPath("$.[*].isManuallyTagged")
            .value(hasItem(DEFAULT_IS_MANUALLY_TAGGED.booleanValue()))
            .jsonPath("$.[*].isPatientRecord")
            .value(hasItem(DEFAULT_IS_PATIENT_RECORD.booleanValue()))
            .jsonPath("$.[*].efaxNumber")
            .value(hasItem(DEFAULT_EFAX_NUMBER))
            .jsonPath("$.[*].isPoAck")
            .value(hasItem(DEFAULT_IS_PO_ACK.booleanValue()))
            .jsonPath("$.[*].documentRenameTo")
            .value(hasItem(DEFAULT_DOCUMENT_RENAME_TO))
            .jsonPath("$.[*].createdById")
            .value(hasItem(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.[*].createdDate")
            .value(hasItem(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.[*].createdByName")
            .value(hasItem(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.[*].updatedById")
            .value(hasItem(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.[*].updatedDate")
            .value(hasItem(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.[*].updatedByName")
            .value(hasItem(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS))
            .jsonPath("$.[*].efaxResponseUuid")
            .value(hasItem(DEFAULT_EFAX_RESPONSE_UUID.toString()));
    }

    @Test
    void getEfaxResponse() {
        // Initialize the database
        efaxResponseRepository.save(efaxResponse).block();

        // Get the efaxResponse
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, efaxResponse.getEfaxResponseId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.efaxResponseId")
            .value(is(efaxResponse.getEfaxResponseId().intValue()))
            .jsonPath("$.efaxReceivedDate")
            .value(is(DEFAULT_EFAX_RECEIVED_DATE.toString()))
            .jsonPath("$.senderEmail")
            .value(is(DEFAULT_SENDER_EMAIL))
            .jsonPath("$.emailSubjectLine")
            .value(is(DEFAULT_EMAIL_SUBJECT_LINE))
            .jsonPath("$.attachmentName")
            .value(is(DEFAULT_ATTACHMENT_NAME))
            .jsonPath("$.isQrDecoded")
            .value(is(DEFAULT_IS_QR_DECODED.booleanValue()))
            .jsonPath("$.qrValue")
            .value(is(DEFAULT_QR_VALUE))
            .jsonPath("$.isCmn")
            .value(is(DEFAULT_IS_CMN.booleanValue()))
            .jsonPath("$.isPar")
            .value(is(DEFAULT_IS_PAR.booleanValue()))
            .jsonPath("$.patientIdNo")
            .value(is(DEFAULT_PATIENT_ID_NO))
            .jsonPath("$.patientFirstName")
            .value(is(DEFAULT_PATIENT_FIRST_NAME))
            .jsonPath("$.patientLastName")
            .value(is(DEFAULT_PATIENT_LAST_NAME))
            .jsonPath("$.cmnIdNo")
            .value(is(DEFAULT_CMN_ID_NO))
            .jsonPath("$.parIdNo")
            .value(is(DEFAULT_PAR_ID_NO))
            .jsonPath("$.soId")
            .value(is(DEFAULT_SO_ID.intValue()))
            .jsonPath("$.soNo")
            .value(is(DEFAULT_SO_NO))
            .jsonPath("$.isManuallyTagged")
            .value(is(DEFAULT_IS_MANUALLY_TAGGED.booleanValue()))
            .jsonPath("$.isPatientRecord")
            .value(is(DEFAULT_IS_PATIENT_RECORD.booleanValue()))
            .jsonPath("$.efaxNumber")
            .value(is(DEFAULT_EFAX_NUMBER))
            .jsonPath("$.isPoAck")
            .value(is(DEFAULT_IS_PO_ACK.booleanValue()))
            .jsonPath("$.documentRenameTo")
            .value(is(DEFAULT_DOCUMENT_RENAME_TO))
            .jsonPath("$.createdById")
            .value(is(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.createdDate")
            .value(is(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.createdByName")
            .value(is(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.updatedById")
            .value(is(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.updatedDate")
            .value(is(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.updatedByName")
            .value(is(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS))
            .jsonPath("$.efaxResponseUuid")
            .value(is(DEFAULT_EFAX_RESPONSE_UUID.toString()));
    }

    @Test
    void getNonExistingEfaxResponse() {
        // Get the efaxResponse
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingEfaxResponse() throws Exception {
        // Initialize the database
        efaxResponseRepository.save(efaxResponse).block();

        int databaseSizeBeforeUpdate = efaxResponseRepository.findAll().collectList().block().size();

        // Update the efaxResponse
        EfaxResponse updatedEfaxResponse = efaxResponseRepository.findById(efaxResponse.getEfaxResponseId()).block();
        updatedEfaxResponse
            .efaxReceivedDate(UPDATED_EFAX_RECEIVED_DATE)
            .senderEmail(UPDATED_SENDER_EMAIL)
            .emailSubjectLine(UPDATED_EMAIL_SUBJECT_LINE)
            .attachmentName(UPDATED_ATTACHMENT_NAME)
            .isQrDecoded(UPDATED_IS_QR_DECODED)
            .qrValue(UPDATED_QR_VALUE)
            .isCmn(UPDATED_IS_CMN)
            .isPar(UPDATED_IS_PAR)
            .patientIdNo(UPDATED_PATIENT_ID_NO)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .cmnIdNo(UPDATED_CMN_ID_NO)
            .parIdNo(UPDATED_PAR_ID_NO)
            .soId(UPDATED_SO_ID)
            .soNo(UPDATED_SO_NO)
            .isManuallyTagged(UPDATED_IS_MANUALLY_TAGGED)
            .isPatientRecord(UPDATED_IS_PATIENT_RECORD)
            .efaxNumber(UPDATED_EFAX_NUMBER)
            .isPoAck(UPDATED_IS_PO_ACK)
            .documentRenameTo(UPDATED_DOCUMENT_RENAME_TO)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .status(UPDATED_STATUS)
            .efaxResponseUuid(UPDATED_EFAX_RESPONSE_UUID);
        EfaxResponseDTO efaxResponseDTO = efaxResponseMapper.toDto(updatedEfaxResponse);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, efaxResponseDTO.getEfaxResponseId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(efaxResponseDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the EfaxResponse in the database
        List<EfaxResponse> efaxResponseList = efaxResponseRepository.findAll().collectList().block();
        assertThat(efaxResponseList).hasSize(databaseSizeBeforeUpdate);
        EfaxResponse testEfaxResponse = efaxResponseList.get(efaxResponseList.size() - 1);
        assertThat(testEfaxResponse.getEfaxReceivedDate()).isEqualTo(UPDATED_EFAX_RECEIVED_DATE);
        assertThat(testEfaxResponse.getSenderEmail()).isEqualTo(UPDATED_SENDER_EMAIL);
        assertThat(testEfaxResponse.getEmailSubjectLine()).isEqualTo(UPDATED_EMAIL_SUBJECT_LINE);
        assertThat(testEfaxResponse.getAttachmentName()).isEqualTo(UPDATED_ATTACHMENT_NAME);
        assertThat(testEfaxResponse.getIsQrDecoded()).isEqualTo(UPDATED_IS_QR_DECODED);
        assertThat(testEfaxResponse.getQrValue()).isEqualTo(UPDATED_QR_VALUE);
        assertThat(testEfaxResponse.getIsCmn()).isEqualTo(UPDATED_IS_CMN);
        assertThat(testEfaxResponse.getIsPar()).isEqualTo(UPDATED_IS_PAR);
        assertThat(testEfaxResponse.getPatientIdNo()).isEqualTo(UPDATED_PATIENT_ID_NO);
        assertThat(testEfaxResponse.getPatientFirstName()).isEqualTo(UPDATED_PATIENT_FIRST_NAME);
        assertThat(testEfaxResponse.getPatientLastName()).isEqualTo(UPDATED_PATIENT_LAST_NAME);
        assertThat(testEfaxResponse.getCmnIdNo()).isEqualTo(UPDATED_CMN_ID_NO);
        assertThat(testEfaxResponse.getParIdNo()).isEqualTo(UPDATED_PAR_ID_NO);
        assertThat(testEfaxResponse.getSoId()).isEqualTo(UPDATED_SO_ID);
        assertThat(testEfaxResponse.getSoNo()).isEqualTo(UPDATED_SO_NO);
        assertThat(testEfaxResponse.getIsManuallyTagged()).isEqualTo(UPDATED_IS_MANUALLY_TAGGED);
        assertThat(testEfaxResponse.getIsPatientRecord()).isEqualTo(UPDATED_IS_PATIENT_RECORD);
        assertThat(testEfaxResponse.getEfaxNumber()).isEqualTo(UPDATED_EFAX_NUMBER);
        assertThat(testEfaxResponse.getIsPoAck()).isEqualTo(UPDATED_IS_PO_ACK);
        assertThat(testEfaxResponse.getDocumentRenameTo()).isEqualTo(UPDATED_DOCUMENT_RENAME_TO);
        assertThat(testEfaxResponse.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testEfaxResponse.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testEfaxResponse.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testEfaxResponse.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testEfaxResponse.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testEfaxResponse.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testEfaxResponse.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testEfaxResponse.getEfaxResponseUuid()).isEqualTo(UPDATED_EFAX_RESPONSE_UUID);
    }

    @Test
    void putNonExistingEfaxResponse() throws Exception {
        int databaseSizeBeforeUpdate = efaxResponseRepository.findAll().collectList().block().size();
        efaxResponse.setEfaxResponseId(count.incrementAndGet());

        // Create the EfaxResponse
        EfaxResponseDTO efaxResponseDTO = efaxResponseMapper.toDto(efaxResponse);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, efaxResponseDTO.getEfaxResponseId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(efaxResponseDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the EfaxResponse in the database
        List<EfaxResponse> efaxResponseList = efaxResponseRepository.findAll().collectList().block();
        assertThat(efaxResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchEfaxResponse() throws Exception {
        int databaseSizeBeforeUpdate = efaxResponseRepository.findAll().collectList().block().size();
        efaxResponse.setEfaxResponseId(count.incrementAndGet());

        // Create the EfaxResponse
        EfaxResponseDTO efaxResponseDTO = efaxResponseMapper.toDto(efaxResponse);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(efaxResponseDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the EfaxResponse in the database
        List<EfaxResponse> efaxResponseList = efaxResponseRepository.findAll().collectList().block();
        assertThat(efaxResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamEfaxResponse() throws Exception {
        int databaseSizeBeforeUpdate = efaxResponseRepository.findAll().collectList().block().size();
        efaxResponse.setEfaxResponseId(count.incrementAndGet());

        // Create the EfaxResponse
        EfaxResponseDTO efaxResponseDTO = efaxResponseMapper.toDto(efaxResponse);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(efaxResponseDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the EfaxResponse in the database
        List<EfaxResponse> efaxResponseList = efaxResponseRepository.findAll().collectList().block();
        assertThat(efaxResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateEfaxResponseWithPatch() throws Exception {
        // Initialize the database
        efaxResponseRepository.save(efaxResponse).block();

        int databaseSizeBeforeUpdate = efaxResponseRepository.findAll().collectList().block().size();

        // Update the efaxResponse using partial update
        EfaxResponse partialUpdatedEfaxResponse = new EfaxResponse();
        partialUpdatedEfaxResponse.setEfaxResponseId(efaxResponse.getEfaxResponseId());

        partialUpdatedEfaxResponse
            .senderEmail(UPDATED_SENDER_EMAIL)
            .attachmentName(UPDATED_ATTACHMENT_NAME)
            .isQrDecoded(UPDATED_IS_QR_DECODED)
            .qrValue(UPDATED_QR_VALUE)
            .isCmn(UPDATED_IS_CMN)
            .cmnIdNo(UPDATED_CMN_ID_NO)
            .isPoAck(UPDATED_IS_PO_ACK)
            .documentRenameTo(UPDATED_DOCUMENT_RENAME_TO)
            .createdById(UPDATED_CREATED_BY_ID)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedEfaxResponse.getEfaxResponseId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedEfaxResponse))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the EfaxResponse in the database
        List<EfaxResponse> efaxResponseList = efaxResponseRepository.findAll().collectList().block();
        assertThat(efaxResponseList).hasSize(databaseSizeBeforeUpdate);
        EfaxResponse testEfaxResponse = efaxResponseList.get(efaxResponseList.size() - 1);
        assertThat(testEfaxResponse.getEfaxReceivedDate()).isEqualTo(DEFAULT_EFAX_RECEIVED_DATE);
        assertThat(testEfaxResponse.getSenderEmail()).isEqualTo(UPDATED_SENDER_EMAIL);
        assertThat(testEfaxResponse.getEmailSubjectLine()).isEqualTo(DEFAULT_EMAIL_SUBJECT_LINE);
        assertThat(testEfaxResponse.getAttachmentName()).isEqualTo(UPDATED_ATTACHMENT_NAME);
        assertThat(testEfaxResponse.getIsQrDecoded()).isEqualTo(UPDATED_IS_QR_DECODED);
        assertThat(testEfaxResponse.getQrValue()).isEqualTo(UPDATED_QR_VALUE);
        assertThat(testEfaxResponse.getIsCmn()).isEqualTo(UPDATED_IS_CMN);
        assertThat(testEfaxResponse.getIsPar()).isEqualTo(DEFAULT_IS_PAR);
        assertThat(testEfaxResponse.getPatientIdNo()).isEqualTo(DEFAULT_PATIENT_ID_NO);
        assertThat(testEfaxResponse.getPatientFirstName()).isEqualTo(DEFAULT_PATIENT_FIRST_NAME);
        assertThat(testEfaxResponse.getPatientLastName()).isEqualTo(DEFAULT_PATIENT_LAST_NAME);
        assertThat(testEfaxResponse.getCmnIdNo()).isEqualTo(UPDATED_CMN_ID_NO);
        assertThat(testEfaxResponse.getParIdNo()).isEqualTo(DEFAULT_PAR_ID_NO);
        assertThat(testEfaxResponse.getSoId()).isEqualTo(DEFAULT_SO_ID);
        assertThat(testEfaxResponse.getSoNo()).isEqualTo(DEFAULT_SO_NO);
        assertThat(testEfaxResponse.getIsManuallyTagged()).isEqualTo(DEFAULT_IS_MANUALLY_TAGGED);
        assertThat(testEfaxResponse.getIsPatientRecord()).isEqualTo(DEFAULT_IS_PATIENT_RECORD);
        assertThat(testEfaxResponse.getEfaxNumber()).isEqualTo(DEFAULT_EFAX_NUMBER);
        assertThat(testEfaxResponse.getIsPoAck()).isEqualTo(UPDATED_IS_PO_ACK);
        assertThat(testEfaxResponse.getDocumentRenameTo()).isEqualTo(UPDATED_DOCUMENT_RENAME_TO);
        assertThat(testEfaxResponse.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testEfaxResponse.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testEfaxResponse.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testEfaxResponse.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testEfaxResponse.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testEfaxResponse.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testEfaxResponse.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testEfaxResponse.getEfaxResponseUuid()).isEqualTo(DEFAULT_EFAX_RESPONSE_UUID);
    }

    @Test
    void fullUpdateEfaxResponseWithPatch() throws Exception {
        // Initialize the database
        efaxResponseRepository.save(efaxResponse).block();

        int databaseSizeBeforeUpdate = efaxResponseRepository.findAll().collectList().block().size();

        // Update the efaxResponse using partial update
        EfaxResponse partialUpdatedEfaxResponse = new EfaxResponse();
        partialUpdatedEfaxResponse.setEfaxResponseId(efaxResponse.getEfaxResponseId());

        partialUpdatedEfaxResponse
            .efaxReceivedDate(UPDATED_EFAX_RECEIVED_DATE)
            .senderEmail(UPDATED_SENDER_EMAIL)
            .emailSubjectLine(UPDATED_EMAIL_SUBJECT_LINE)
            .attachmentName(UPDATED_ATTACHMENT_NAME)
            .isQrDecoded(UPDATED_IS_QR_DECODED)
            .qrValue(UPDATED_QR_VALUE)
            .isCmn(UPDATED_IS_CMN)
            .isPar(UPDATED_IS_PAR)
            .patientIdNo(UPDATED_PATIENT_ID_NO)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .cmnIdNo(UPDATED_CMN_ID_NO)
            .parIdNo(UPDATED_PAR_ID_NO)
            .soId(UPDATED_SO_ID)
            .soNo(UPDATED_SO_NO)
            .isManuallyTagged(UPDATED_IS_MANUALLY_TAGGED)
            .isPatientRecord(UPDATED_IS_PATIENT_RECORD)
            .efaxNumber(UPDATED_EFAX_NUMBER)
            .isPoAck(UPDATED_IS_PO_ACK)
            .documentRenameTo(UPDATED_DOCUMENT_RENAME_TO)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .status(UPDATED_STATUS)
            .efaxResponseUuid(UPDATED_EFAX_RESPONSE_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedEfaxResponse.getEfaxResponseId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedEfaxResponse))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the EfaxResponse in the database
        List<EfaxResponse> efaxResponseList = efaxResponseRepository.findAll().collectList().block();
        assertThat(efaxResponseList).hasSize(databaseSizeBeforeUpdate);
        EfaxResponse testEfaxResponse = efaxResponseList.get(efaxResponseList.size() - 1);
        assertThat(testEfaxResponse.getEfaxReceivedDate()).isEqualTo(UPDATED_EFAX_RECEIVED_DATE);
        assertThat(testEfaxResponse.getSenderEmail()).isEqualTo(UPDATED_SENDER_EMAIL);
        assertThat(testEfaxResponse.getEmailSubjectLine()).isEqualTo(UPDATED_EMAIL_SUBJECT_LINE);
        assertThat(testEfaxResponse.getAttachmentName()).isEqualTo(UPDATED_ATTACHMENT_NAME);
        assertThat(testEfaxResponse.getIsQrDecoded()).isEqualTo(UPDATED_IS_QR_DECODED);
        assertThat(testEfaxResponse.getQrValue()).isEqualTo(UPDATED_QR_VALUE);
        assertThat(testEfaxResponse.getIsCmn()).isEqualTo(UPDATED_IS_CMN);
        assertThat(testEfaxResponse.getIsPar()).isEqualTo(UPDATED_IS_PAR);
        assertThat(testEfaxResponse.getPatientIdNo()).isEqualTo(UPDATED_PATIENT_ID_NO);
        assertThat(testEfaxResponse.getPatientFirstName()).isEqualTo(UPDATED_PATIENT_FIRST_NAME);
        assertThat(testEfaxResponse.getPatientLastName()).isEqualTo(UPDATED_PATIENT_LAST_NAME);
        assertThat(testEfaxResponse.getCmnIdNo()).isEqualTo(UPDATED_CMN_ID_NO);
        assertThat(testEfaxResponse.getParIdNo()).isEqualTo(UPDATED_PAR_ID_NO);
        assertThat(testEfaxResponse.getSoId()).isEqualTo(UPDATED_SO_ID);
        assertThat(testEfaxResponse.getSoNo()).isEqualTo(UPDATED_SO_NO);
        assertThat(testEfaxResponse.getIsManuallyTagged()).isEqualTo(UPDATED_IS_MANUALLY_TAGGED);
        assertThat(testEfaxResponse.getIsPatientRecord()).isEqualTo(UPDATED_IS_PATIENT_RECORD);
        assertThat(testEfaxResponse.getEfaxNumber()).isEqualTo(UPDATED_EFAX_NUMBER);
        assertThat(testEfaxResponse.getIsPoAck()).isEqualTo(UPDATED_IS_PO_ACK);
        assertThat(testEfaxResponse.getDocumentRenameTo()).isEqualTo(UPDATED_DOCUMENT_RENAME_TO);
        assertThat(testEfaxResponse.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testEfaxResponse.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testEfaxResponse.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testEfaxResponse.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testEfaxResponse.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testEfaxResponse.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testEfaxResponse.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testEfaxResponse.getEfaxResponseUuid()).isEqualTo(UPDATED_EFAX_RESPONSE_UUID);
    }

    @Test
    void patchNonExistingEfaxResponse() throws Exception {
        int databaseSizeBeforeUpdate = efaxResponseRepository.findAll().collectList().block().size();
        efaxResponse.setEfaxResponseId(count.incrementAndGet());

        // Create the EfaxResponse
        EfaxResponseDTO efaxResponseDTO = efaxResponseMapper.toDto(efaxResponse);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, efaxResponseDTO.getEfaxResponseId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(efaxResponseDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the EfaxResponse in the database
        List<EfaxResponse> efaxResponseList = efaxResponseRepository.findAll().collectList().block();
        assertThat(efaxResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchEfaxResponse() throws Exception {
        int databaseSizeBeforeUpdate = efaxResponseRepository.findAll().collectList().block().size();
        efaxResponse.setEfaxResponseId(count.incrementAndGet());

        // Create the EfaxResponse
        EfaxResponseDTO efaxResponseDTO = efaxResponseMapper.toDto(efaxResponse);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(efaxResponseDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the EfaxResponse in the database
        List<EfaxResponse> efaxResponseList = efaxResponseRepository.findAll().collectList().block();
        assertThat(efaxResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamEfaxResponse() throws Exception {
        int databaseSizeBeforeUpdate = efaxResponseRepository.findAll().collectList().block().size();
        efaxResponse.setEfaxResponseId(count.incrementAndGet());

        // Create the EfaxResponse
        EfaxResponseDTO efaxResponseDTO = efaxResponseMapper.toDto(efaxResponse);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(efaxResponseDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the EfaxResponse in the database
        List<EfaxResponse> efaxResponseList = efaxResponseRepository.findAll().collectList().block();
        assertThat(efaxResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteEfaxResponse() {
        // Initialize the database
        efaxResponseRepository.save(efaxResponse).block();

        int databaseSizeBeforeDelete = efaxResponseRepository.findAll().collectList().block().size();

        // Delete the efaxResponse
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, efaxResponse.getEfaxResponseId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<EfaxResponse> efaxResponseList = efaxResponseRepository.findAll().collectList().block();
        assertThat(efaxResponseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
