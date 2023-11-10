package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ParMaster;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.ParMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.ParMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.ParMasterMapper;
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
 * Integration tests for the {@link ParMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class ParMasterResourceIT {

    private static final String DEFAULT_PAR_NO = "AAAAAAAAAA";
    private static final String UPDATED_PAR_NO = "BBBBBBBBBB";

    private static final Long DEFAULT_PATIENT_ID = 1L;
    private static final Long UPDATED_PATIENT_ID = 2L;

    private static final String DEFAULT_PATIENT_ID_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_ID_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_LAST_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PATIENT_DOB = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PATIENT_DOB = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_PATIENT_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_GENDER = "BBBBBBBBBB";

    private static final Long DEFAULT_INSURANCE_ID = 1L;
    private static final Long UPDATED_INSURANCE_ID = 2L;

    private static final String DEFAULT_INSURANCE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INSURANCE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PAYER_ID_NO = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_ID_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PAYER_LEVEL = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_LEVEL = "BBBBBBBBBB";

    private static final String DEFAULT_POLICY_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_POLICY_NUMBER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_POLICY_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_POLICY_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_POLICY_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_POLICY_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_PAYER_CONTACT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_CONTACT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_PAYER_CONTACT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_CONTACT_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_OF_CONTACT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_OF_CONTACT = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_INITIAL_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_INITIAL_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_EFFECTIVE_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_EFFECTIVE_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_EXPIRATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_EXPIRATION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_AUTHORIZED_BY = "AAAAAAAAAA";
    private static final String UPDATED_AUTHORIZED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_ADDL_INFORMATION = "AAAAAAAAAA";
    private static final String UPDATED_ADDL_INFORMATION = "BBBBBBBBBB";

    private static final String DEFAULT_PAR_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_PAR_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTS = "BBBBBBBBBB";

    private static final String DEFAULT_LOG_IN_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_LOG_IN_STATUS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_LOG_IN_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LOG_IN_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_RENEWAL_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_RENEWAL_STATUS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_RENEWAL_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_RENEWAL_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_RENEWAL_AUTHORIZED_BY = "AAAAAAAAAA";
    private static final String UPDATED_RENEWAL_AUTHORIZED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_RENEWAL_REQ_SENT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_RENEWAL_REQ_SENT_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_RENEWAL_REQ_REPLY_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_RENEWAL_REQ_REPLY_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_ORIGINAL_PAR_NO = "AAAAAAAAAA";
    private static final String UPDATED_ORIGINAL_PAR_NO = "BBBBBBBBBB";

    private static final String DEFAULT_EXTENSION_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_EXTENSION_STATUS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_EXTENSION_APPROVAL_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_EXTENSION_APPROVAL_DATE = LocalDate.now(ZoneId.systemDefault());

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

    private static final UUID DEFAULT_PAR_UUID = UUID.randomUUID();
    private static final UUID UPDATED_PAR_UUID = UUID.randomUUID();

    private static final String DEFAULT_PAR_ID_NO = "AAAAAAAAAA";
    private static final String UPDATED_PAR_ID_NO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/par-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{parId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ParMasterRepository parMasterRepository;

    @Autowired
    private ParMasterMapper parMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private ParMaster parMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ParMaster createEntity(EntityManager em) {
        ParMaster parMaster = new ParMaster()
            .parNo(DEFAULT_PAR_NO)
            .patientId(DEFAULT_PATIENT_ID)
            .patientIdNumber(DEFAULT_PATIENT_ID_NUMBER)
            .patientFirstName(DEFAULT_PATIENT_FIRST_NAME)
            .patientLastName(DEFAULT_PATIENT_LAST_NAME)
            .patientDob(DEFAULT_PATIENT_DOB)
            .patientGender(DEFAULT_PATIENT_GENDER)
            .insuranceId(DEFAULT_INSURANCE_ID)
            .insuranceName(DEFAULT_INSURANCE_NAME)
            .payerIdNo(DEFAULT_PAYER_ID_NO)
            .payerLevel(DEFAULT_PAYER_LEVEL)
            .policyNumber(DEFAULT_POLICY_NUMBER)
            .policyStartDate(DEFAULT_POLICY_START_DATE)
            .policyEndDate(DEFAULT_POLICY_END_DATE)
            .payerContactNumber(DEFAULT_PAYER_CONTACT_NUMBER)
            .payerContactName(DEFAULT_PAYER_CONTACT_NAME)
            .dateOfContact(DEFAULT_DATE_OF_CONTACT)
            .description(DEFAULT_DESCRIPTION)
            .initialDate(DEFAULT_INITIAL_DATE)
            .effectiveStartDate(DEFAULT_EFFECTIVE_START_DATE)
            .expirationDate(DEFAULT_EXPIRATION_DATE)
            .authorizedBy(DEFAULT_AUTHORIZED_BY)
            .addlInformation(DEFAULT_ADDL_INFORMATION)
            .parStatus(DEFAULT_PAR_STATUS)
            .comments(DEFAULT_COMMENTS)
            .logInStatus(DEFAULT_LOG_IN_STATUS)
            .logInDate(DEFAULT_LOG_IN_DATE)
            .renewalStatus(DEFAULT_RENEWAL_STATUS)
            .renewalDate(DEFAULT_RENEWAL_DATE)
            .renewalAuthorizedBy(DEFAULT_RENEWAL_AUTHORIZED_BY)
            .renewalReqSentStatus(DEFAULT_RENEWAL_REQ_SENT_STATUS)
            .renewalReqReplyStatus(DEFAULT_RENEWAL_REQ_REPLY_STATUS)
            .originalParNo(DEFAULT_ORIGINAL_PAR_NO)
            .extensionStatus(DEFAULT_EXTENSION_STATUS)
            .extensionApprovalDate(DEFAULT_EXTENSION_APPROVAL_DATE)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .parUuid(DEFAULT_PAR_UUID)
            .parIdNo(DEFAULT_PAR_ID_NO);
        return parMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ParMaster createUpdatedEntity(EntityManager em) {
        ParMaster parMaster = new ParMaster()
            .parNo(UPDATED_PAR_NO)
            .patientId(UPDATED_PATIENT_ID)
            .patientIdNumber(UPDATED_PATIENT_ID_NUMBER)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .patientDob(UPDATED_PATIENT_DOB)
            .patientGender(UPDATED_PATIENT_GENDER)
            .insuranceId(UPDATED_INSURANCE_ID)
            .insuranceName(UPDATED_INSURANCE_NAME)
            .payerIdNo(UPDATED_PAYER_ID_NO)
            .payerLevel(UPDATED_PAYER_LEVEL)
            .policyNumber(UPDATED_POLICY_NUMBER)
            .policyStartDate(UPDATED_POLICY_START_DATE)
            .policyEndDate(UPDATED_POLICY_END_DATE)
            .payerContactNumber(UPDATED_PAYER_CONTACT_NUMBER)
            .payerContactName(UPDATED_PAYER_CONTACT_NAME)
            .dateOfContact(UPDATED_DATE_OF_CONTACT)
            .description(UPDATED_DESCRIPTION)
            .initialDate(UPDATED_INITIAL_DATE)
            .effectiveStartDate(UPDATED_EFFECTIVE_START_DATE)
            .expirationDate(UPDATED_EXPIRATION_DATE)
            .authorizedBy(UPDATED_AUTHORIZED_BY)
            .addlInformation(UPDATED_ADDL_INFORMATION)
            .parStatus(UPDATED_PAR_STATUS)
            .comments(UPDATED_COMMENTS)
            .logInStatus(UPDATED_LOG_IN_STATUS)
            .logInDate(UPDATED_LOG_IN_DATE)
            .renewalStatus(UPDATED_RENEWAL_STATUS)
            .renewalDate(UPDATED_RENEWAL_DATE)
            .renewalAuthorizedBy(UPDATED_RENEWAL_AUTHORIZED_BY)
            .renewalReqSentStatus(UPDATED_RENEWAL_REQ_SENT_STATUS)
            .renewalReqReplyStatus(UPDATED_RENEWAL_REQ_REPLY_STATUS)
            .originalParNo(UPDATED_ORIGINAL_PAR_NO)
            .extensionStatus(UPDATED_EXTENSION_STATUS)
            .extensionApprovalDate(UPDATED_EXTENSION_APPROVAL_DATE)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .parUuid(UPDATED_PAR_UUID)
            .parIdNo(UPDATED_PAR_ID_NO);
        return parMaster;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(ParMaster.class).block();
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
        parMaster = createEntity(em);
    }

    @Test
    void createParMaster() throws Exception {
        int databaseSizeBeforeCreate = parMasterRepository.findAll().collectList().block().size();
        // Create the ParMaster
        ParMasterDTO parMasterDTO = parMasterMapper.toDto(parMaster);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(parMasterDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the ParMaster in the database
        List<ParMaster> parMasterList = parMasterRepository.findAll().collectList().block();
        assertThat(parMasterList).hasSize(databaseSizeBeforeCreate + 1);
        ParMaster testParMaster = parMasterList.get(parMasterList.size() - 1);
        assertThat(testParMaster.getParNo()).isEqualTo(DEFAULT_PAR_NO);
        assertThat(testParMaster.getPatientId()).isEqualTo(DEFAULT_PATIENT_ID);
        assertThat(testParMaster.getPatientIdNumber()).isEqualTo(DEFAULT_PATIENT_ID_NUMBER);
        assertThat(testParMaster.getPatientFirstName()).isEqualTo(DEFAULT_PATIENT_FIRST_NAME);
        assertThat(testParMaster.getPatientLastName()).isEqualTo(DEFAULT_PATIENT_LAST_NAME);
        assertThat(testParMaster.getPatientDob()).isEqualTo(DEFAULT_PATIENT_DOB);
        assertThat(testParMaster.getPatientGender()).isEqualTo(DEFAULT_PATIENT_GENDER);
        assertThat(testParMaster.getInsuranceId()).isEqualTo(DEFAULT_INSURANCE_ID);
        assertThat(testParMaster.getInsuranceName()).isEqualTo(DEFAULT_INSURANCE_NAME);
        assertThat(testParMaster.getPayerIdNo()).isEqualTo(DEFAULT_PAYER_ID_NO);
        assertThat(testParMaster.getPayerLevel()).isEqualTo(DEFAULT_PAYER_LEVEL);
        assertThat(testParMaster.getPolicyNumber()).isEqualTo(DEFAULT_POLICY_NUMBER);
        assertThat(testParMaster.getPolicyStartDate()).isEqualTo(DEFAULT_POLICY_START_DATE);
        assertThat(testParMaster.getPolicyEndDate()).isEqualTo(DEFAULT_POLICY_END_DATE);
        assertThat(testParMaster.getPayerContactNumber()).isEqualTo(DEFAULT_PAYER_CONTACT_NUMBER);
        assertThat(testParMaster.getPayerContactName()).isEqualTo(DEFAULT_PAYER_CONTACT_NAME);
        assertThat(testParMaster.getDateOfContact()).isEqualTo(DEFAULT_DATE_OF_CONTACT);
        assertThat(testParMaster.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testParMaster.getInitialDate()).isEqualTo(DEFAULT_INITIAL_DATE);
        assertThat(testParMaster.getEffectiveStartDate()).isEqualTo(DEFAULT_EFFECTIVE_START_DATE);
        assertThat(testParMaster.getExpirationDate()).isEqualTo(DEFAULT_EXPIRATION_DATE);
        assertThat(testParMaster.getAuthorizedBy()).isEqualTo(DEFAULT_AUTHORIZED_BY);
        assertThat(testParMaster.getAddlInformation()).isEqualTo(DEFAULT_ADDL_INFORMATION);
        assertThat(testParMaster.getParStatus()).isEqualTo(DEFAULT_PAR_STATUS);
        assertThat(testParMaster.getComments()).isEqualTo(DEFAULT_COMMENTS);
        assertThat(testParMaster.getLogInStatus()).isEqualTo(DEFAULT_LOG_IN_STATUS);
        assertThat(testParMaster.getLogInDate()).isEqualTo(DEFAULT_LOG_IN_DATE);
        assertThat(testParMaster.getRenewalStatus()).isEqualTo(DEFAULT_RENEWAL_STATUS);
        assertThat(testParMaster.getRenewalDate()).isEqualTo(DEFAULT_RENEWAL_DATE);
        assertThat(testParMaster.getRenewalAuthorizedBy()).isEqualTo(DEFAULT_RENEWAL_AUTHORIZED_BY);
        assertThat(testParMaster.getRenewalReqSentStatus()).isEqualTo(DEFAULT_RENEWAL_REQ_SENT_STATUS);
        assertThat(testParMaster.getRenewalReqReplyStatus()).isEqualTo(DEFAULT_RENEWAL_REQ_REPLY_STATUS);
        assertThat(testParMaster.getOriginalParNo()).isEqualTo(DEFAULT_ORIGINAL_PAR_NO);
        assertThat(testParMaster.getExtensionStatus()).isEqualTo(DEFAULT_EXTENSION_STATUS);
        assertThat(testParMaster.getExtensionApprovalDate()).isEqualTo(DEFAULT_EXTENSION_APPROVAL_DATE);
        assertThat(testParMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testParMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testParMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testParMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testParMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testParMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testParMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testParMaster.getParUuid()).isEqualTo(DEFAULT_PAR_UUID);
        assertThat(testParMaster.getParIdNo()).isEqualTo(DEFAULT_PAR_ID_NO);
    }

    @Test
    void createParMasterWithExistingId() throws Exception {
        // Create the ParMaster with an existing ID
        parMaster.setParId(1L);
        ParMasterDTO parMasterDTO = parMasterMapper.toDto(parMaster);

        int databaseSizeBeforeCreate = parMasterRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(parMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ParMaster in the database
        List<ParMaster> parMasterList = parMasterRepository.findAll().collectList().block();
        assertThat(parMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllParMasters() {
        // Initialize the database
        parMasterRepository.save(parMaster).block();

        // Get all the parMasterList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=parId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].parId")
            .value(hasItem(parMaster.getParId().intValue()))
            .jsonPath("$.[*].parNo")
            .value(hasItem(DEFAULT_PAR_NO))
            .jsonPath("$.[*].patientId")
            .value(hasItem(DEFAULT_PATIENT_ID.intValue()))
            .jsonPath("$.[*].patientIdNumber")
            .value(hasItem(DEFAULT_PATIENT_ID_NUMBER))
            .jsonPath("$.[*].patientFirstName")
            .value(hasItem(DEFAULT_PATIENT_FIRST_NAME))
            .jsonPath("$.[*].patientLastName")
            .value(hasItem(DEFAULT_PATIENT_LAST_NAME))
            .jsonPath("$.[*].patientDob")
            .value(hasItem(DEFAULT_PATIENT_DOB.toString()))
            .jsonPath("$.[*].patientGender")
            .value(hasItem(DEFAULT_PATIENT_GENDER))
            .jsonPath("$.[*].insuranceId")
            .value(hasItem(DEFAULT_INSURANCE_ID.intValue()))
            .jsonPath("$.[*].insuranceName")
            .value(hasItem(DEFAULT_INSURANCE_NAME))
            .jsonPath("$.[*].payerIdNo")
            .value(hasItem(DEFAULT_PAYER_ID_NO))
            .jsonPath("$.[*].payerLevel")
            .value(hasItem(DEFAULT_PAYER_LEVEL))
            .jsonPath("$.[*].policyNumber")
            .value(hasItem(DEFAULT_POLICY_NUMBER))
            .jsonPath("$.[*].policyStartDate")
            .value(hasItem(DEFAULT_POLICY_START_DATE.toString()))
            .jsonPath("$.[*].policyEndDate")
            .value(hasItem(DEFAULT_POLICY_END_DATE.toString()))
            .jsonPath("$.[*].payerContactNumber")
            .value(hasItem(DEFAULT_PAYER_CONTACT_NUMBER))
            .jsonPath("$.[*].payerContactName")
            .value(hasItem(DEFAULT_PAYER_CONTACT_NAME))
            .jsonPath("$.[*].dateOfContact")
            .value(hasItem(DEFAULT_DATE_OF_CONTACT.toString()))
            .jsonPath("$.[*].description")
            .value(hasItem(DEFAULT_DESCRIPTION))
            .jsonPath("$.[*].initialDate")
            .value(hasItem(DEFAULT_INITIAL_DATE.toString()))
            .jsonPath("$.[*].effectiveStartDate")
            .value(hasItem(DEFAULT_EFFECTIVE_START_DATE.toString()))
            .jsonPath("$.[*].expirationDate")
            .value(hasItem(DEFAULT_EXPIRATION_DATE.toString()))
            .jsonPath("$.[*].authorizedBy")
            .value(hasItem(DEFAULT_AUTHORIZED_BY))
            .jsonPath("$.[*].addlInformation")
            .value(hasItem(DEFAULT_ADDL_INFORMATION))
            .jsonPath("$.[*].parStatus")
            .value(hasItem(DEFAULT_PAR_STATUS))
            .jsonPath("$.[*].comments")
            .value(hasItem(DEFAULT_COMMENTS))
            .jsonPath("$.[*].logInStatus")
            .value(hasItem(DEFAULT_LOG_IN_STATUS))
            .jsonPath("$.[*].logInDate")
            .value(hasItem(DEFAULT_LOG_IN_DATE.toString()))
            .jsonPath("$.[*].renewalStatus")
            .value(hasItem(DEFAULT_RENEWAL_STATUS))
            .jsonPath("$.[*].renewalDate")
            .value(hasItem(DEFAULT_RENEWAL_DATE.toString()))
            .jsonPath("$.[*].renewalAuthorizedBy")
            .value(hasItem(DEFAULT_RENEWAL_AUTHORIZED_BY))
            .jsonPath("$.[*].renewalReqSentStatus")
            .value(hasItem(DEFAULT_RENEWAL_REQ_SENT_STATUS))
            .jsonPath("$.[*].renewalReqReplyStatus")
            .value(hasItem(DEFAULT_RENEWAL_REQ_REPLY_STATUS))
            .jsonPath("$.[*].originalParNo")
            .value(hasItem(DEFAULT_ORIGINAL_PAR_NO))
            .jsonPath("$.[*].extensionStatus")
            .value(hasItem(DEFAULT_EXTENSION_STATUS))
            .jsonPath("$.[*].extensionApprovalDate")
            .value(hasItem(DEFAULT_EXTENSION_APPROVAL_DATE.toString()))
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
            .jsonPath("$.[*].parUuid")
            .value(hasItem(DEFAULT_PAR_UUID.toString()))
            .jsonPath("$.[*].parIdNo")
            .value(hasItem(DEFAULT_PAR_ID_NO));
    }

    @Test
    void getParMaster() {
        // Initialize the database
        parMasterRepository.save(parMaster).block();

        // Get the parMaster
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, parMaster.getParId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.parId")
            .value(is(parMaster.getParId().intValue()))
            .jsonPath("$.parNo")
            .value(is(DEFAULT_PAR_NO))
            .jsonPath("$.patientId")
            .value(is(DEFAULT_PATIENT_ID.intValue()))
            .jsonPath("$.patientIdNumber")
            .value(is(DEFAULT_PATIENT_ID_NUMBER))
            .jsonPath("$.patientFirstName")
            .value(is(DEFAULT_PATIENT_FIRST_NAME))
            .jsonPath("$.patientLastName")
            .value(is(DEFAULT_PATIENT_LAST_NAME))
            .jsonPath("$.patientDob")
            .value(is(DEFAULT_PATIENT_DOB.toString()))
            .jsonPath("$.patientGender")
            .value(is(DEFAULT_PATIENT_GENDER))
            .jsonPath("$.insuranceId")
            .value(is(DEFAULT_INSURANCE_ID.intValue()))
            .jsonPath("$.insuranceName")
            .value(is(DEFAULT_INSURANCE_NAME))
            .jsonPath("$.payerIdNo")
            .value(is(DEFAULT_PAYER_ID_NO))
            .jsonPath("$.payerLevel")
            .value(is(DEFAULT_PAYER_LEVEL))
            .jsonPath("$.policyNumber")
            .value(is(DEFAULT_POLICY_NUMBER))
            .jsonPath("$.policyStartDate")
            .value(is(DEFAULT_POLICY_START_DATE.toString()))
            .jsonPath("$.policyEndDate")
            .value(is(DEFAULT_POLICY_END_DATE.toString()))
            .jsonPath("$.payerContactNumber")
            .value(is(DEFAULT_PAYER_CONTACT_NUMBER))
            .jsonPath("$.payerContactName")
            .value(is(DEFAULT_PAYER_CONTACT_NAME))
            .jsonPath("$.dateOfContact")
            .value(is(DEFAULT_DATE_OF_CONTACT.toString()))
            .jsonPath("$.description")
            .value(is(DEFAULT_DESCRIPTION))
            .jsonPath("$.initialDate")
            .value(is(DEFAULT_INITIAL_DATE.toString()))
            .jsonPath("$.effectiveStartDate")
            .value(is(DEFAULT_EFFECTIVE_START_DATE.toString()))
            .jsonPath("$.expirationDate")
            .value(is(DEFAULT_EXPIRATION_DATE.toString()))
            .jsonPath("$.authorizedBy")
            .value(is(DEFAULT_AUTHORIZED_BY))
            .jsonPath("$.addlInformation")
            .value(is(DEFAULT_ADDL_INFORMATION))
            .jsonPath("$.parStatus")
            .value(is(DEFAULT_PAR_STATUS))
            .jsonPath("$.comments")
            .value(is(DEFAULT_COMMENTS))
            .jsonPath("$.logInStatus")
            .value(is(DEFAULT_LOG_IN_STATUS))
            .jsonPath("$.logInDate")
            .value(is(DEFAULT_LOG_IN_DATE.toString()))
            .jsonPath("$.renewalStatus")
            .value(is(DEFAULT_RENEWAL_STATUS))
            .jsonPath("$.renewalDate")
            .value(is(DEFAULT_RENEWAL_DATE.toString()))
            .jsonPath("$.renewalAuthorizedBy")
            .value(is(DEFAULT_RENEWAL_AUTHORIZED_BY))
            .jsonPath("$.renewalReqSentStatus")
            .value(is(DEFAULT_RENEWAL_REQ_SENT_STATUS))
            .jsonPath("$.renewalReqReplyStatus")
            .value(is(DEFAULT_RENEWAL_REQ_REPLY_STATUS))
            .jsonPath("$.originalParNo")
            .value(is(DEFAULT_ORIGINAL_PAR_NO))
            .jsonPath("$.extensionStatus")
            .value(is(DEFAULT_EXTENSION_STATUS))
            .jsonPath("$.extensionApprovalDate")
            .value(is(DEFAULT_EXTENSION_APPROVAL_DATE.toString()))
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
            .jsonPath("$.parUuid")
            .value(is(DEFAULT_PAR_UUID.toString()))
            .jsonPath("$.parIdNo")
            .value(is(DEFAULT_PAR_ID_NO));
    }

    @Test
    void getNonExistingParMaster() {
        // Get the parMaster
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putNewParMaster() throws Exception {
        // Initialize the database
        parMasterRepository.save(parMaster).block();

        int databaseSizeBeforeUpdate = parMasterRepository.findAll().collectList().block().size();

        // Update the parMaster
        ParMaster updatedParMaster = parMasterRepository.findById(parMaster.getParId()).block();
        updatedParMaster
            .parNo(UPDATED_PAR_NO)
            .patientId(UPDATED_PATIENT_ID)
            .patientIdNumber(UPDATED_PATIENT_ID_NUMBER)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .patientDob(UPDATED_PATIENT_DOB)
            .patientGender(UPDATED_PATIENT_GENDER)
            .insuranceId(UPDATED_INSURANCE_ID)
            .insuranceName(UPDATED_INSURANCE_NAME)
            .payerIdNo(UPDATED_PAYER_ID_NO)
            .payerLevel(UPDATED_PAYER_LEVEL)
            .policyNumber(UPDATED_POLICY_NUMBER)
            .policyStartDate(UPDATED_POLICY_START_DATE)
            .policyEndDate(UPDATED_POLICY_END_DATE)
            .payerContactNumber(UPDATED_PAYER_CONTACT_NUMBER)
            .payerContactName(UPDATED_PAYER_CONTACT_NAME)
            .dateOfContact(UPDATED_DATE_OF_CONTACT)
            .description(UPDATED_DESCRIPTION)
            .initialDate(UPDATED_INITIAL_DATE)
            .effectiveStartDate(UPDATED_EFFECTIVE_START_DATE)
            .expirationDate(UPDATED_EXPIRATION_DATE)
            .authorizedBy(UPDATED_AUTHORIZED_BY)
            .addlInformation(UPDATED_ADDL_INFORMATION)
            .parStatus(UPDATED_PAR_STATUS)
            .comments(UPDATED_COMMENTS)
            .logInStatus(UPDATED_LOG_IN_STATUS)
            .logInDate(UPDATED_LOG_IN_DATE)
            .renewalStatus(UPDATED_RENEWAL_STATUS)
            .renewalDate(UPDATED_RENEWAL_DATE)
            .renewalAuthorizedBy(UPDATED_RENEWAL_AUTHORIZED_BY)
            .renewalReqSentStatus(UPDATED_RENEWAL_REQ_SENT_STATUS)
            .renewalReqReplyStatus(UPDATED_RENEWAL_REQ_REPLY_STATUS)
            .originalParNo(UPDATED_ORIGINAL_PAR_NO)
            .extensionStatus(UPDATED_EXTENSION_STATUS)
            .extensionApprovalDate(UPDATED_EXTENSION_APPROVAL_DATE)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .parUuid(UPDATED_PAR_UUID)
            .parIdNo(UPDATED_PAR_ID_NO);
        ParMasterDTO parMasterDTO = parMasterMapper.toDto(updatedParMaster);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, parMasterDTO.getParId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(parMasterDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ParMaster in the database
        List<ParMaster> parMasterList = parMasterRepository.findAll().collectList().block();
        assertThat(parMasterList).hasSize(databaseSizeBeforeUpdate);
        ParMaster testParMaster = parMasterList.get(parMasterList.size() - 1);
        assertThat(testParMaster.getParNo()).isEqualTo(UPDATED_PAR_NO);
        assertThat(testParMaster.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testParMaster.getPatientIdNumber()).isEqualTo(UPDATED_PATIENT_ID_NUMBER);
        assertThat(testParMaster.getPatientFirstName()).isEqualTo(UPDATED_PATIENT_FIRST_NAME);
        assertThat(testParMaster.getPatientLastName()).isEqualTo(UPDATED_PATIENT_LAST_NAME);
        assertThat(testParMaster.getPatientDob()).isEqualTo(UPDATED_PATIENT_DOB);
        assertThat(testParMaster.getPatientGender()).isEqualTo(UPDATED_PATIENT_GENDER);
        assertThat(testParMaster.getInsuranceId()).isEqualTo(UPDATED_INSURANCE_ID);
        assertThat(testParMaster.getInsuranceName()).isEqualTo(UPDATED_INSURANCE_NAME);
        assertThat(testParMaster.getPayerIdNo()).isEqualTo(UPDATED_PAYER_ID_NO);
        assertThat(testParMaster.getPayerLevel()).isEqualTo(UPDATED_PAYER_LEVEL);
        assertThat(testParMaster.getPolicyNumber()).isEqualTo(UPDATED_POLICY_NUMBER);
        assertThat(testParMaster.getPolicyStartDate()).isEqualTo(UPDATED_POLICY_START_DATE);
        assertThat(testParMaster.getPolicyEndDate()).isEqualTo(UPDATED_POLICY_END_DATE);
        assertThat(testParMaster.getPayerContactNumber()).isEqualTo(UPDATED_PAYER_CONTACT_NUMBER);
        assertThat(testParMaster.getPayerContactName()).isEqualTo(UPDATED_PAYER_CONTACT_NAME);
        assertThat(testParMaster.getDateOfContact()).isEqualTo(UPDATED_DATE_OF_CONTACT);
        assertThat(testParMaster.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testParMaster.getInitialDate()).isEqualTo(UPDATED_INITIAL_DATE);
        assertThat(testParMaster.getEffectiveStartDate()).isEqualTo(UPDATED_EFFECTIVE_START_DATE);
        assertThat(testParMaster.getExpirationDate()).isEqualTo(UPDATED_EXPIRATION_DATE);
        assertThat(testParMaster.getAuthorizedBy()).isEqualTo(UPDATED_AUTHORIZED_BY);
        assertThat(testParMaster.getAddlInformation()).isEqualTo(UPDATED_ADDL_INFORMATION);
        assertThat(testParMaster.getParStatus()).isEqualTo(UPDATED_PAR_STATUS);
        assertThat(testParMaster.getComments()).isEqualTo(UPDATED_COMMENTS);
        assertThat(testParMaster.getLogInStatus()).isEqualTo(UPDATED_LOG_IN_STATUS);
        assertThat(testParMaster.getLogInDate()).isEqualTo(UPDATED_LOG_IN_DATE);
        assertThat(testParMaster.getRenewalStatus()).isEqualTo(UPDATED_RENEWAL_STATUS);
        assertThat(testParMaster.getRenewalDate()).isEqualTo(UPDATED_RENEWAL_DATE);
        assertThat(testParMaster.getRenewalAuthorizedBy()).isEqualTo(UPDATED_RENEWAL_AUTHORIZED_BY);
        assertThat(testParMaster.getRenewalReqSentStatus()).isEqualTo(UPDATED_RENEWAL_REQ_SENT_STATUS);
        assertThat(testParMaster.getRenewalReqReplyStatus()).isEqualTo(UPDATED_RENEWAL_REQ_REPLY_STATUS);
        assertThat(testParMaster.getOriginalParNo()).isEqualTo(UPDATED_ORIGINAL_PAR_NO);
        assertThat(testParMaster.getExtensionStatus()).isEqualTo(UPDATED_EXTENSION_STATUS);
        assertThat(testParMaster.getExtensionApprovalDate()).isEqualTo(UPDATED_EXTENSION_APPROVAL_DATE);
        assertThat(testParMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testParMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testParMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testParMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testParMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testParMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testParMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testParMaster.getParUuid()).isEqualTo(UPDATED_PAR_UUID);
        assertThat(testParMaster.getParIdNo()).isEqualTo(UPDATED_PAR_ID_NO);
    }

    @Test
    void putNonExistingParMaster() throws Exception {
        int databaseSizeBeforeUpdate = parMasterRepository.findAll().collectList().block().size();
        parMaster.setParId(count.incrementAndGet());

        // Create the ParMaster
        ParMasterDTO parMasterDTO = parMasterMapper.toDto(parMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, parMasterDTO.getParId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(parMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ParMaster in the database
        List<ParMaster> parMasterList = parMasterRepository.findAll().collectList().block();
        assertThat(parMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchParMaster() throws Exception {
        int databaseSizeBeforeUpdate = parMasterRepository.findAll().collectList().block().size();
        parMaster.setParId(count.incrementAndGet());

        // Create the ParMaster
        ParMasterDTO parMasterDTO = parMasterMapper.toDto(parMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(parMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ParMaster in the database
        List<ParMaster> parMasterList = parMasterRepository.findAll().collectList().block();
        assertThat(parMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamParMaster() throws Exception {
        int databaseSizeBeforeUpdate = parMasterRepository.findAll().collectList().block().size();
        parMaster.setParId(count.incrementAndGet());

        // Create the ParMaster
        ParMasterDTO parMasterDTO = parMasterMapper.toDto(parMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(parMasterDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the ParMaster in the database
        List<ParMaster> parMasterList = parMasterRepository.findAll().collectList().block();
        assertThat(parMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateParMasterWithPatch() throws Exception {
        // Initialize the database
        parMasterRepository.save(parMaster).block();

        int databaseSizeBeforeUpdate = parMasterRepository.findAll().collectList().block().size();

        // Update the parMaster using partial update
        ParMaster partialUpdatedParMaster = new ParMaster();
        partialUpdatedParMaster.setParId(parMaster.getParId());

        partialUpdatedParMaster
            .parNo(UPDATED_PAR_NO)
            .patientId(UPDATED_PATIENT_ID)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .patientDob(UPDATED_PATIENT_DOB)
            .payerLevel(UPDATED_PAYER_LEVEL)
            .policyNumber(UPDATED_POLICY_NUMBER)
            .policyStartDate(UPDATED_POLICY_START_DATE)
            .payerContactName(UPDATED_PAYER_CONTACT_NAME)
            .description(UPDATED_DESCRIPTION)
            .initialDate(UPDATED_INITIAL_DATE)
            .authorizedBy(UPDATED_AUTHORIZED_BY)
            .addlInformation(UPDATED_ADDL_INFORMATION)
            .comments(UPDATED_COMMENTS)
            .renewalStatus(UPDATED_RENEWAL_STATUS)
            .renewalAuthorizedBy(UPDATED_RENEWAL_AUTHORIZED_BY)
            .extensionStatus(UPDATED_EXTENSION_STATUS)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .parIdNo(UPDATED_PAR_ID_NO);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedParMaster.getParId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedParMaster))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ParMaster in the database
        List<ParMaster> parMasterList = parMasterRepository.findAll().collectList().block();
        assertThat(parMasterList).hasSize(databaseSizeBeforeUpdate);
        ParMaster testParMaster = parMasterList.get(parMasterList.size() - 1);
        assertThat(testParMaster.getParNo()).isEqualTo(UPDATED_PAR_NO);
        assertThat(testParMaster.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testParMaster.getPatientIdNumber()).isEqualTo(DEFAULT_PATIENT_ID_NUMBER);
        assertThat(testParMaster.getPatientFirstName()).isEqualTo(UPDATED_PATIENT_FIRST_NAME);
        assertThat(testParMaster.getPatientLastName()).isEqualTo(UPDATED_PATIENT_LAST_NAME);
        assertThat(testParMaster.getPatientDob()).isEqualTo(UPDATED_PATIENT_DOB);
        assertThat(testParMaster.getPatientGender()).isEqualTo(DEFAULT_PATIENT_GENDER);
        assertThat(testParMaster.getInsuranceId()).isEqualTo(DEFAULT_INSURANCE_ID);
        assertThat(testParMaster.getInsuranceName()).isEqualTo(DEFAULT_INSURANCE_NAME);
        assertThat(testParMaster.getPayerIdNo()).isEqualTo(DEFAULT_PAYER_ID_NO);
        assertThat(testParMaster.getPayerLevel()).isEqualTo(UPDATED_PAYER_LEVEL);
        assertThat(testParMaster.getPolicyNumber()).isEqualTo(UPDATED_POLICY_NUMBER);
        assertThat(testParMaster.getPolicyStartDate()).isEqualTo(UPDATED_POLICY_START_DATE);
        assertThat(testParMaster.getPolicyEndDate()).isEqualTo(DEFAULT_POLICY_END_DATE);
        assertThat(testParMaster.getPayerContactNumber()).isEqualTo(DEFAULT_PAYER_CONTACT_NUMBER);
        assertThat(testParMaster.getPayerContactName()).isEqualTo(UPDATED_PAYER_CONTACT_NAME);
        assertThat(testParMaster.getDateOfContact()).isEqualTo(DEFAULT_DATE_OF_CONTACT);
        assertThat(testParMaster.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testParMaster.getInitialDate()).isEqualTo(UPDATED_INITIAL_DATE);
        assertThat(testParMaster.getEffectiveStartDate()).isEqualTo(DEFAULT_EFFECTIVE_START_DATE);
        assertThat(testParMaster.getExpirationDate()).isEqualTo(DEFAULT_EXPIRATION_DATE);
        assertThat(testParMaster.getAuthorizedBy()).isEqualTo(UPDATED_AUTHORIZED_BY);
        assertThat(testParMaster.getAddlInformation()).isEqualTo(UPDATED_ADDL_INFORMATION);
        assertThat(testParMaster.getParStatus()).isEqualTo(DEFAULT_PAR_STATUS);
        assertThat(testParMaster.getComments()).isEqualTo(UPDATED_COMMENTS);
        assertThat(testParMaster.getLogInStatus()).isEqualTo(DEFAULT_LOG_IN_STATUS);
        assertThat(testParMaster.getLogInDate()).isEqualTo(DEFAULT_LOG_IN_DATE);
        assertThat(testParMaster.getRenewalStatus()).isEqualTo(UPDATED_RENEWAL_STATUS);
        assertThat(testParMaster.getRenewalDate()).isEqualTo(DEFAULT_RENEWAL_DATE);
        assertThat(testParMaster.getRenewalAuthorizedBy()).isEqualTo(UPDATED_RENEWAL_AUTHORIZED_BY);
        assertThat(testParMaster.getRenewalReqSentStatus()).isEqualTo(DEFAULT_RENEWAL_REQ_SENT_STATUS);
        assertThat(testParMaster.getRenewalReqReplyStatus()).isEqualTo(DEFAULT_RENEWAL_REQ_REPLY_STATUS);
        assertThat(testParMaster.getOriginalParNo()).isEqualTo(DEFAULT_ORIGINAL_PAR_NO);
        assertThat(testParMaster.getExtensionStatus()).isEqualTo(UPDATED_EXTENSION_STATUS);
        assertThat(testParMaster.getExtensionApprovalDate()).isEqualTo(DEFAULT_EXTENSION_APPROVAL_DATE);
        assertThat(testParMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testParMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testParMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testParMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testParMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testParMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testParMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testParMaster.getParUuid()).isEqualTo(DEFAULT_PAR_UUID);
        assertThat(testParMaster.getParIdNo()).isEqualTo(UPDATED_PAR_ID_NO);
    }

    @Test
    void fullUpdateParMasterWithPatch() throws Exception {
        // Initialize the database
        parMasterRepository.save(parMaster).block();

        int databaseSizeBeforeUpdate = parMasterRepository.findAll().collectList().block().size();

        // Update the parMaster using partial update
        ParMaster partialUpdatedParMaster = new ParMaster();
        partialUpdatedParMaster.setParId(parMaster.getParId());

        partialUpdatedParMaster
            .parNo(UPDATED_PAR_NO)
            .patientId(UPDATED_PATIENT_ID)
            .patientIdNumber(UPDATED_PATIENT_ID_NUMBER)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .patientDob(UPDATED_PATIENT_DOB)
            .patientGender(UPDATED_PATIENT_GENDER)
            .insuranceId(UPDATED_INSURANCE_ID)
            .insuranceName(UPDATED_INSURANCE_NAME)
            .payerIdNo(UPDATED_PAYER_ID_NO)
            .payerLevel(UPDATED_PAYER_LEVEL)
            .policyNumber(UPDATED_POLICY_NUMBER)
            .policyStartDate(UPDATED_POLICY_START_DATE)
            .policyEndDate(UPDATED_POLICY_END_DATE)
            .payerContactNumber(UPDATED_PAYER_CONTACT_NUMBER)
            .payerContactName(UPDATED_PAYER_CONTACT_NAME)
            .dateOfContact(UPDATED_DATE_OF_CONTACT)
            .description(UPDATED_DESCRIPTION)
            .initialDate(UPDATED_INITIAL_DATE)
            .effectiveStartDate(UPDATED_EFFECTIVE_START_DATE)
            .expirationDate(UPDATED_EXPIRATION_DATE)
            .authorizedBy(UPDATED_AUTHORIZED_BY)
            .addlInformation(UPDATED_ADDL_INFORMATION)
            .parStatus(UPDATED_PAR_STATUS)
            .comments(UPDATED_COMMENTS)
            .logInStatus(UPDATED_LOG_IN_STATUS)
            .logInDate(UPDATED_LOG_IN_DATE)
            .renewalStatus(UPDATED_RENEWAL_STATUS)
            .renewalDate(UPDATED_RENEWAL_DATE)
            .renewalAuthorizedBy(UPDATED_RENEWAL_AUTHORIZED_BY)
            .renewalReqSentStatus(UPDATED_RENEWAL_REQ_SENT_STATUS)
            .renewalReqReplyStatus(UPDATED_RENEWAL_REQ_REPLY_STATUS)
            .originalParNo(UPDATED_ORIGINAL_PAR_NO)
            .extensionStatus(UPDATED_EXTENSION_STATUS)
            .extensionApprovalDate(UPDATED_EXTENSION_APPROVAL_DATE)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .parUuid(UPDATED_PAR_UUID)
            .parIdNo(UPDATED_PAR_ID_NO);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedParMaster.getParId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedParMaster))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ParMaster in the database
        List<ParMaster> parMasterList = parMasterRepository.findAll().collectList().block();
        assertThat(parMasterList).hasSize(databaseSizeBeforeUpdate);
        ParMaster testParMaster = parMasterList.get(parMasterList.size() - 1);
        assertThat(testParMaster.getParNo()).isEqualTo(UPDATED_PAR_NO);
        assertThat(testParMaster.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testParMaster.getPatientIdNumber()).isEqualTo(UPDATED_PATIENT_ID_NUMBER);
        assertThat(testParMaster.getPatientFirstName()).isEqualTo(UPDATED_PATIENT_FIRST_NAME);
        assertThat(testParMaster.getPatientLastName()).isEqualTo(UPDATED_PATIENT_LAST_NAME);
        assertThat(testParMaster.getPatientDob()).isEqualTo(UPDATED_PATIENT_DOB);
        assertThat(testParMaster.getPatientGender()).isEqualTo(UPDATED_PATIENT_GENDER);
        assertThat(testParMaster.getInsuranceId()).isEqualTo(UPDATED_INSURANCE_ID);
        assertThat(testParMaster.getInsuranceName()).isEqualTo(UPDATED_INSURANCE_NAME);
        assertThat(testParMaster.getPayerIdNo()).isEqualTo(UPDATED_PAYER_ID_NO);
        assertThat(testParMaster.getPayerLevel()).isEqualTo(UPDATED_PAYER_LEVEL);
        assertThat(testParMaster.getPolicyNumber()).isEqualTo(UPDATED_POLICY_NUMBER);
        assertThat(testParMaster.getPolicyStartDate()).isEqualTo(UPDATED_POLICY_START_DATE);
        assertThat(testParMaster.getPolicyEndDate()).isEqualTo(UPDATED_POLICY_END_DATE);
        assertThat(testParMaster.getPayerContactNumber()).isEqualTo(UPDATED_PAYER_CONTACT_NUMBER);
        assertThat(testParMaster.getPayerContactName()).isEqualTo(UPDATED_PAYER_CONTACT_NAME);
        assertThat(testParMaster.getDateOfContact()).isEqualTo(UPDATED_DATE_OF_CONTACT);
        assertThat(testParMaster.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testParMaster.getInitialDate()).isEqualTo(UPDATED_INITIAL_DATE);
        assertThat(testParMaster.getEffectiveStartDate()).isEqualTo(UPDATED_EFFECTIVE_START_DATE);
        assertThat(testParMaster.getExpirationDate()).isEqualTo(UPDATED_EXPIRATION_DATE);
        assertThat(testParMaster.getAuthorizedBy()).isEqualTo(UPDATED_AUTHORIZED_BY);
        assertThat(testParMaster.getAddlInformation()).isEqualTo(UPDATED_ADDL_INFORMATION);
        assertThat(testParMaster.getParStatus()).isEqualTo(UPDATED_PAR_STATUS);
        assertThat(testParMaster.getComments()).isEqualTo(UPDATED_COMMENTS);
        assertThat(testParMaster.getLogInStatus()).isEqualTo(UPDATED_LOG_IN_STATUS);
        assertThat(testParMaster.getLogInDate()).isEqualTo(UPDATED_LOG_IN_DATE);
        assertThat(testParMaster.getRenewalStatus()).isEqualTo(UPDATED_RENEWAL_STATUS);
        assertThat(testParMaster.getRenewalDate()).isEqualTo(UPDATED_RENEWAL_DATE);
        assertThat(testParMaster.getRenewalAuthorizedBy()).isEqualTo(UPDATED_RENEWAL_AUTHORIZED_BY);
        assertThat(testParMaster.getRenewalReqSentStatus()).isEqualTo(UPDATED_RENEWAL_REQ_SENT_STATUS);
        assertThat(testParMaster.getRenewalReqReplyStatus()).isEqualTo(UPDATED_RENEWAL_REQ_REPLY_STATUS);
        assertThat(testParMaster.getOriginalParNo()).isEqualTo(UPDATED_ORIGINAL_PAR_NO);
        assertThat(testParMaster.getExtensionStatus()).isEqualTo(UPDATED_EXTENSION_STATUS);
        assertThat(testParMaster.getExtensionApprovalDate()).isEqualTo(UPDATED_EXTENSION_APPROVAL_DATE);
        assertThat(testParMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testParMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testParMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testParMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testParMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testParMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testParMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testParMaster.getParUuid()).isEqualTo(UPDATED_PAR_UUID);
        assertThat(testParMaster.getParIdNo()).isEqualTo(UPDATED_PAR_ID_NO);
    }

    @Test
    void patchNonExistingParMaster() throws Exception {
        int databaseSizeBeforeUpdate = parMasterRepository.findAll().collectList().block().size();
        parMaster.setParId(count.incrementAndGet());

        // Create the ParMaster
        ParMasterDTO parMasterDTO = parMasterMapper.toDto(parMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, parMasterDTO.getParId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(parMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ParMaster in the database
        List<ParMaster> parMasterList = parMasterRepository.findAll().collectList().block();
        assertThat(parMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchParMaster() throws Exception {
        int databaseSizeBeforeUpdate = parMasterRepository.findAll().collectList().block().size();
        parMaster.setParId(count.incrementAndGet());

        // Create the ParMaster
        ParMasterDTO parMasterDTO = parMasterMapper.toDto(parMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(parMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ParMaster in the database
        List<ParMaster> parMasterList = parMasterRepository.findAll().collectList().block();
        assertThat(parMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamParMaster() throws Exception {
        int databaseSizeBeforeUpdate = parMasterRepository.findAll().collectList().block().size();
        parMaster.setParId(count.incrementAndGet());

        // Create the ParMaster
        ParMasterDTO parMasterDTO = parMasterMapper.toDto(parMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(parMasterDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the ParMaster in the database
        List<ParMaster> parMasterList = parMasterRepository.findAll().collectList().block();
        assertThat(parMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteParMaster() {
        // Initialize the database
        parMasterRepository.save(parMaster).block();

        int databaseSizeBeforeDelete = parMasterRepository.findAll().collectList().block().size();

        // Delete the parMaster
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, parMaster.getParId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<ParMaster> parMasterList = parMasterRepository.findAll().collectList().block();
        assertThat(parMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
