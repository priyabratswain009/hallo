package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.Cmn;
import com.sunknowledge.dme.rcm.repository.CmnRepository;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.service.dto.CmnDTO;
import com.sunknowledge.dme.rcm.service.mapper.CmnMapper;
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
 * Integration tests for the {@link CmnResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class CmnResourceIT {

    private static final String DEFAULT_CMN_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CMN_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_CMN_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_CMN_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CMN_FORM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CMN_FORM_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_PATIENT_ID = 1L;
    private static final Long UPDATED_PATIENT_ID = 2L;

    private static final Long DEFAULT_SALES_ORDER_ID = 1L;
    private static final Long UPDATED_SALES_ORDER_ID = 2L;

    private static final String DEFAULT_SALES_ORDER_NO = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_NO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CMN_CREATE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CMN_CREATE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_CMN_INITIAL_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CMN_INITIAL_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_CMN_REVISION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CMN_REVISION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_CMN_RECERTIFICATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CMN_RECERTIFICATION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_CMN_EXPIRATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CMN_EXPIRATION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_CMN_LOGGED_BY = 1L;
    private static final Long UPDATED_CMN_LOGGED_BY = 2L;

    private static final LocalDate DEFAULT_CMN_LOGGED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CMN_LOGGED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_CMN_APPROVED_BY = 1L;
    private static final Long UPDATED_CMN_APPROVED_BY = 2L;

    private static final LocalDate DEFAULT_CMN_APPROVED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CMN_APPROVED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_CMN_PRINTED_BY = 1L;
    private static final Long UPDATED_CMN_PRINTED_BY = 2L;

    private static final LocalDate DEFAULT_CMN_PRINTED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CMN_PRINTED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_LENGTH_OF_NEED = "AAAAAAAAAA";
    private static final String UPDATED_LENGTH_OF_NEED = "BBBBBBBBBB";

    private static final String DEFAULT_FAX_CMN_OPTION = "AAAAAAAAAA";
    private static final String UPDATED_FAX_CMN_OPTION = "BBBBBBBBBB";

    private static final String DEFAULT_CMN_COVER_LETTER_INCLUSION_OPTION = "AAAAAAAAAA";
    private static final String UPDATED_CMN_COVER_LETTER_INCLUSION_OPTION = "BBBBBBBBBB";

    private static final Long DEFAULT_CMN_FAXED_BY = 1L;
    private static final Long UPDATED_CMN_FAXED_BY = 2L;

    private static final LocalDate DEFAULT_CMN_FAXED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CMN_FAXED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_FAX_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_FAX_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_FAX_STATUS_REASON = "AAAAAAAAAA";
    private static final String UPDATED_FAX_STATUS_REASON = "BBBBBBBBBB";

    private static final String DEFAULT_PRINT_CMN_OPTION = "AAAAAAAAAA";
    private static final String UPDATED_PRINT_CMN_OPTION = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final UUID DEFAULT_CMN_ID_UUID = UUID.randomUUID();
    private static final UUID UPDATED_CMN_ID_UUID = UUID.randomUUID();

    private static final String DEFAULT_PATIENT_PROGNOSIS = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_PROGNOSIS = "BBBBBBBBBB";

    private static final String DEFAULT_CMN_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_CMN_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_REFILL_AUTHORISED = "AAAAAAAAAA";
    private static final String UPDATED_REFILL_AUTHORISED = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/cmns";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{cmnId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CmnRepository cmnRepository;

    @Autowired
    private CmnMapper cmnMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private Cmn cmn;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cmn createEntity(EntityManager em) {
        Cmn cmn = new Cmn()
            .cmnNumber(DEFAULT_CMN_NUMBER)
            .cmnType(DEFAULT_CMN_TYPE)
            .cmnFormName(DEFAULT_CMN_FORM_NAME)
            .patientId(DEFAULT_PATIENT_ID)
            .salesOrderId(DEFAULT_SALES_ORDER_ID)
            .salesOrderNo(DEFAULT_SALES_ORDER_NO)
            .cmnCreateDate(DEFAULT_CMN_CREATE_DATE)
            .cmnInitialDate(DEFAULT_CMN_INITIAL_DATE)
            .cmnRevisionDate(DEFAULT_CMN_REVISION_DATE)
            .cmnRecertificationDate(DEFAULT_CMN_RECERTIFICATION_DATE)
            .cmnExpirationDate(DEFAULT_CMN_EXPIRATION_DATE)
            .cmnLoggedBy(DEFAULT_CMN_LOGGED_BY)
            .cmnLoggedDate(DEFAULT_CMN_LOGGED_DATE)
            .cmnApprovedBy(DEFAULT_CMN_APPROVED_BY)
            .cmnApprovedDate(DEFAULT_CMN_APPROVED_DATE)
            .cmnPrintedBy(DEFAULT_CMN_PRINTED_BY)
            .cmnPrintedDate(DEFAULT_CMN_PRINTED_DATE)
            .lengthOfNeed(DEFAULT_LENGTH_OF_NEED)
            .faxCmnOption(DEFAULT_FAX_CMN_OPTION)
            .cmnCoverLetterInclusionOption(DEFAULT_CMN_COVER_LETTER_INCLUSION_OPTION)
            .cmnFaxedBy(DEFAULT_CMN_FAXED_BY)
            .cmnFaxedDate(DEFAULT_CMN_FAXED_DATE)
            .faxStatus(DEFAULT_FAX_STATUS)
            .faxStatusReason(DEFAULT_FAX_STATUS_REASON)
            .printCmnOption(DEFAULT_PRINT_CMN_OPTION)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .status(DEFAULT_STATUS)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .cmnIdUuid(DEFAULT_CMN_ID_UUID)
            .patientPrognosis(DEFAULT_PATIENT_PROGNOSIS)
            .cmnStatus(DEFAULT_CMN_STATUS)
            .refillAuthorised(DEFAULT_REFILL_AUTHORISED);
        return cmn;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cmn createUpdatedEntity(EntityManager em) {
        Cmn cmn = new Cmn()
            .cmnNumber(UPDATED_CMN_NUMBER)
            .cmnType(UPDATED_CMN_TYPE)
            .cmnFormName(UPDATED_CMN_FORM_NAME)
            .patientId(UPDATED_PATIENT_ID)
            .salesOrderId(UPDATED_SALES_ORDER_ID)
            .salesOrderNo(UPDATED_SALES_ORDER_NO)
            .cmnCreateDate(UPDATED_CMN_CREATE_DATE)
            .cmnInitialDate(UPDATED_CMN_INITIAL_DATE)
            .cmnRevisionDate(UPDATED_CMN_REVISION_DATE)
            .cmnRecertificationDate(UPDATED_CMN_RECERTIFICATION_DATE)
            .cmnExpirationDate(UPDATED_CMN_EXPIRATION_DATE)
            .cmnLoggedBy(UPDATED_CMN_LOGGED_BY)
            .cmnLoggedDate(UPDATED_CMN_LOGGED_DATE)
            .cmnApprovedBy(UPDATED_CMN_APPROVED_BY)
            .cmnApprovedDate(UPDATED_CMN_APPROVED_DATE)
            .cmnPrintedBy(UPDATED_CMN_PRINTED_BY)
            .cmnPrintedDate(UPDATED_CMN_PRINTED_DATE)
            .lengthOfNeed(UPDATED_LENGTH_OF_NEED)
            .faxCmnOption(UPDATED_FAX_CMN_OPTION)
            .cmnCoverLetterInclusionOption(UPDATED_CMN_COVER_LETTER_INCLUSION_OPTION)
            .cmnFaxedBy(UPDATED_CMN_FAXED_BY)
            .cmnFaxedDate(UPDATED_CMN_FAXED_DATE)
            .faxStatus(UPDATED_FAX_STATUS)
            .faxStatusReason(UPDATED_FAX_STATUS_REASON)
            .printCmnOption(UPDATED_PRINT_CMN_OPTION)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .status(UPDATED_STATUS)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .cmnIdUuid(UPDATED_CMN_ID_UUID)
            .patientPrognosis(UPDATED_PATIENT_PROGNOSIS)
            .cmnStatus(UPDATED_CMN_STATUS)
            .refillAuthorised(UPDATED_REFILL_AUTHORISED);
        return cmn;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(Cmn.class).block();
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
        cmn = createEntity(em);
    }

    @Test
    void createCmn() throws Exception {
        int databaseSizeBeforeCreate = cmnRepository.findAll().collectList().block().size();
        // Create the Cmn
        CmnDTO cmnDTO = cmnMapper.toDto(cmn);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(cmnDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the Cmn in the database
        List<Cmn> cmnList = cmnRepository.findAll().collectList().block();
        assertThat(cmnList).hasSize(databaseSizeBeforeCreate + 1);
        Cmn testCmn = cmnList.get(cmnList.size() - 1);
        assertThat(testCmn.getCmnNumber()).isEqualTo(DEFAULT_CMN_NUMBER);
        assertThat(testCmn.getCmnType()).isEqualTo(DEFAULT_CMN_TYPE);
        assertThat(testCmn.getCmnFormName()).isEqualTo(DEFAULT_CMN_FORM_NAME);
        assertThat(testCmn.getPatientId()).isEqualTo(DEFAULT_PATIENT_ID);
        assertThat(testCmn.getSalesOrderId()).isEqualTo(DEFAULT_SALES_ORDER_ID);
        assertThat(testCmn.getSalesOrderNo()).isEqualTo(DEFAULT_SALES_ORDER_NO);
        assertThat(testCmn.getCmnCreateDate()).isEqualTo(DEFAULT_CMN_CREATE_DATE);
        assertThat(testCmn.getCmnInitialDate()).isEqualTo(DEFAULT_CMN_INITIAL_DATE);
        assertThat(testCmn.getCmnRevisionDate()).isEqualTo(DEFAULT_CMN_REVISION_DATE);
        assertThat(testCmn.getCmnRecertificationDate()).isEqualTo(DEFAULT_CMN_RECERTIFICATION_DATE);
        assertThat(testCmn.getCmnExpirationDate()).isEqualTo(DEFAULT_CMN_EXPIRATION_DATE);
        assertThat(testCmn.getCmnLoggedBy()).isEqualTo(DEFAULT_CMN_LOGGED_BY);
        assertThat(testCmn.getCmnLoggedDate()).isEqualTo(DEFAULT_CMN_LOGGED_DATE);
        assertThat(testCmn.getCmnApprovedBy()).isEqualTo(DEFAULT_CMN_APPROVED_BY);
        assertThat(testCmn.getCmnApprovedDate()).isEqualTo(DEFAULT_CMN_APPROVED_DATE);
        assertThat(testCmn.getCmnPrintedBy()).isEqualTo(DEFAULT_CMN_PRINTED_BY);
        assertThat(testCmn.getCmnPrintedDate()).isEqualTo(DEFAULT_CMN_PRINTED_DATE);
        assertThat(testCmn.getLengthOfNeed()).isEqualTo(DEFAULT_LENGTH_OF_NEED);
        assertThat(testCmn.getFaxCmnOption()).isEqualTo(DEFAULT_FAX_CMN_OPTION);
        assertThat(testCmn.getCmnCoverLetterInclusionOption()).isEqualTo(DEFAULT_CMN_COVER_LETTER_INCLUSION_OPTION);
        assertThat(testCmn.getCmnFaxedBy()).isEqualTo(DEFAULT_CMN_FAXED_BY);
        assertThat(testCmn.getCmnFaxedDate()).isEqualTo(DEFAULT_CMN_FAXED_DATE);
        assertThat(testCmn.getFaxStatus()).isEqualTo(DEFAULT_FAX_STATUS);
        assertThat(testCmn.getFaxStatusReason()).isEqualTo(DEFAULT_FAX_STATUS_REASON);
        assertThat(testCmn.getPrintCmnOption()).isEqualTo(DEFAULT_PRINT_CMN_OPTION);
        assertThat(testCmn.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testCmn.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testCmn.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testCmn.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testCmn.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testCmn.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testCmn.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testCmn.getCmnIdUuid()).isEqualTo(DEFAULT_CMN_ID_UUID);
        assertThat(testCmn.getPatientPrognosis()).isEqualTo(DEFAULT_PATIENT_PROGNOSIS);
        assertThat(testCmn.getCmnStatus()).isEqualTo(DEFAULT_CMN_STATUS);
        assertThat(testCmn.getRefillAuthorised()).isEqualTo(DEFAULT_REFILL_AUTHORISED);
    }

    @Test
    void createCmnWithExistingId() throws Exception {
        // Create the Cmn with an existing ID
        cmn.setCmnId(1L);
        CmnDTO cmnDTO = cmnMapper.toDto(cmn);

        int databaseSizeBeforeCreate = cmnRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(cmnDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Cmn in the database
        List<Cmn> cmnList = cmnRepository.findAll().collectList().block();
        assertThat(cmnList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllCmns() {
        // Initialize the database
        cmnRepository.save(cmn).block();

        // Get all the cmnList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=cmnId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].cmnId")
            .value(hasItem(cmn.getCmnId().intValue()))
            .jsonPath("$.[*].cmnNumber")
            .value(hasItem(DEFAULT_CMN_NUMBER))
            .jsonPath("$.[*].cmnType")
            .value(hasItem(DEFAULT_CMN_TYPE))
            .jsonPath("$.[*].cmnFormName")
            .value(hasItem(DEFAULT_CMN_FORM_NAME))
            .jsonPath("$.[*].patientId")
            .value(hasItem(DEFAULT_PATIENT_ID.intValue()))
            .jsonPath("$.[*].salesOrderId")
            .value(hasItem(DEFAULT_SALES_ORDER_ID.intValue()))
            .jsonPath("$.[*].salesOrderNo")
            .value(hasItem(DEFAULT_SALES_ORDER_NO))
            .jsonPath("$.[*].cmnCreateDate")
            .value(hasItem(DEFAULT_CMN_CREATE_DATE.toString()))
            .jsonPath("$.[*].cmnInitialDate")
            .value(hasItem(DEFAULT_CMN_INITIAL_DATE.toString()))
            .jsonPath("$.[*].cmnRevisionDate")
            .value(hasItem(DEFAULT_CMN_REVISION_DATE.toString()))
            .jsonPath("$.[*].cmnRecertificationDate")
            .value(hasItem(DEFAULT_CMN_RECERTIFICATION_DATE.toString()))
            .jsonPath("$.[*].cmnExpirationDate")
            .value(hasItem(DEFAULT_CMN_EXPIRATION_DATE.toString()))
            .jsonPath("$.[*].cmnLoggedBy")
            .value(hasItem(DEFAULT_CMN_LOGGED_BY.intValue()))
            .jsonPath("$.[*].cmnLoggedDate")
            .value(hasItem(DEFAULT_CMN_LOGGED_DATE.toString()))
            .jsonPath("$.[*].cmnApprovedBy")
            .value(hasItem(DEFAULT_CMN_APPROVED_BY.intValue()))
            .jsonPath("$.[*].cmnApprovedDate")
            .value(hasItem(DEFAULT_CMN_APPROVED_DATE.toString()))
            .jsonPath("$.[*].cmnPrintedBy")
            .value(hasItem(DEFAULT_CMN_PRINTED_BY.intValue()))
            .jsonPath("$.[*].cmnPrintedDate")
            .value(hasItem(DEFAULT_CMN_PRINTED_DATE.toString()))
            .jsonPath("$.[*].lengthOfNeed")
            .value(hasItem(DEFAULT_LENGTH_OF_NEED))
            .jsonPath("$.[*].faxCmnOption")
            .value(hasItem(DEFAULT_FAX_CMN_OPTION))
            .jsonPath("$.[*].cmnCoverLetterInclusionOption")
            .value(hasItem(DEFAULT_CMN_COVER_LETTER_INCLUSION_OPTION))
            .jsonPath("$.[*].cmnFaxedBy")
            .value(hasItem(DEFAULT_CMN_FAXED_BY.intValue()))
            .jsonPath("$.[*].cmnFaxedDate")
            .value(hasItem(DEFAULT_CMN_FAXED_DATE.toString()))
            .jsonPath("$.[*].faxStatus")
            .value(hasItem(DEFAULT_FAX_STATUS))
            .jsonPath("$.[*].faxStatusReason")
            .value(hasItem(DEFAULT_FAX_STATUS_REASON))
            .jsonPath("$.[*].printCmnOption")
            .value(hasItem(DEFAULT_PRINT_CMN_OPTION))
            .jsonPath("$.[*].createdById")
            .value(hasItem(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.[*].createdDate")
            .value(hasItem(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS))
            .jsonPath("$.[*].updatedById")
            .value(hasItem(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.[*].updatedDate")
            .value(hasItem(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.[*].createdByName")
            .value(hasItem(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.[*].updatedByName")
            .value(hasItem(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.[*].cmnIdUuid")
            .value(hasItem(DEFAULT_CMN_ID_UUID.toString()))
            .jsonPath("$.[*].patientPrognosis")
            .value(hasItem(DEFAULT_PATIENT_PROGNOSIS))
            .jsonPath("$.[*].cmnStatus")
            .value(hasItem(DEFAULT_CMN_STATUS))
            .jsonPath("$.[*].refillAuthorised")
            .value(hasItem(DEFAULT_REFILL_AUTHORISED));
    }

    @Test
    void getCmn() {
        // Initialize the database
        cmnRepository.save(cmn).block();

        // Get the cmn
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, cmn.getCmnId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.cmnId")
            .value(is(cmn.getCmnId().intValue()))
            .jsonPath("$.cmnNumber")
            .value(is(DEFAULT_CMN_NUMBER))
            .jsonPath("$.cmnType")
            .value(is(DEFAULT_CMN_TYPE))
            .jsonPath("$.cmnFormName")
            .value(is(DEFAULT_CMN_FORM_NAME))
            .jsonPath("$.patientId")
            .value(is(DEFAULT_PATIENT_ID.intValue()))
            .jsonPath("$.salesOrderId")
            .value(is(DEFAULT_SALES_ORDER_ID.intValue()))
            .jsonPath("$.salesOrderNo")
            .value(is(DEFAULT_SALES_ORDER_NO))
            .jsonPath("$.cmnCreateDate")
            .value(is(DEFAULT_CMN_CREATE_DATE.toString()))
            .jsonPath("$.cmnInitialDate")
            .value(is(DEFAULT_CMN_INITIAL_DATE.toString()))
            .jsonPath("$.cmnRevisionDate")
            .value(is(DEFAULT_CMN_REVISION_DATE.toString()))
            .jsonPath("$.cmnRecertificationDate")
            .value(is(DEFAULT_CMN_RECERTIFICATION_DATE.toString()))
            .jsonPath("$.cmnExpirationDate")
            .value(is(DEFAULT_CMN_EXPIRATION_DATE.toString()))
            .jsonPath("$.cmnLoggedBy")
            .value(is(DEFAULT_CMN_LOGGED_BY.intValue()))
            .jsonPath("$.cmnLoggedDate")
            .value(is(DEFAULT_CMN_LOGGED_DATE.toString()))
            .jsonPath("$.cmnApprovedBy")
            .value(is(DEFAULT_CMN_APPROVED_BY.intValue()))
            .jsonPath("$.cmnApprovedDate")
            .value(is(DEFAULT_CMN_APPROVED_DATE.toString()))
            .jsonPath("$.cmnPrintedBy")
            .value(is(DEFAULT_CMN_PRINTED_BY.intValue()))
            .jsonPath("$.cmnPrintedDate")
            .value(is(DEFAULT_CMN_PRINTED_DATE.toString()))
            .jsonPath("$.lengthOfNeed")
            .value(is(DEFAULT_LENGTH_OF_NEED))
            .jsonPath("$.faxCmnOption")
            .value(is(DEFAULT_FAX_CMN_OPTION))
            .jsonPath("$.cmnCoverLetterInclusionOption")
            .value(is(DEFAULT_CMN_COVER_LETTER_INCLUSION_OPTION))
            .jsonPath("$.cmnFaxedBy")
            .value(is(DEFAULT_CMN_FAXED_BY.intValue()))
            .jsonPath("$.cmnFaxedDate")
            .value(is(DEFAULT_CMN_FAXED_DATE.toString()))
            .jsonPath("$.faxStatus")
            .value(is(DEFAULT_FAX_STATUS))
            .jsonPath("$.faxStatusReason")
            .value(is(DEFAULT_FAX_STATUS_REASON))
            .jsonPath("$.printCmnOption")
            .value(is(DEFAULT_PRINT_CMN_OPTION))
            .jsonPath("$.createdById")
            .value(is(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.createdDate")
            .value(is(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS))
            .jsonPath("$.updatedById")
            .value(is(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.updatedDate")
            .value(is(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.createdByName")
            .value(is(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.updatedByName")
            .value(is(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.cmnIdUuid")
            .value(is(DEFAULT_CMN_ID_UUID.toString()))
            .jsonPath("$.patientPrognosis")
            .value(is(DEFAULT_PATIENT_PROGNOSIS))
            .jsonPath("$.cmnStatus")
            .value(is(DEFAULT_CMN_STATUS))
            .jsonPath("$.refillAuthorised")
            .value(is(DEFAULT_REFILL_AUTHORISED));
    }

    @Test
    void getNonExistingCmn() {
        // Get the cmn
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putNewCmn() throws Exception {
        // Initialize the database
        cmnRepository.save(cmn).block();

        int databaseSizeBeforeUpdate = cmnRepository.findAll().collectList().block().size();

        // Update the cmn
        Cmn updatedCmn = cmnRepository.findById(cmn.getCmnId()).block();
        updatedCmn
            .cmnNumber(UPDATED_CMN_NUMBER)
            .cmnType(UPDATED_CMN_TYPE)
            .cmnFormName(UPDATED_CMN_FORM_NAME)
            .patientId(UPDATED_PATIENT_ID)
            .salesOrderId(UPDATED_SALES_ORDER_ID)
            .salesOrderNo(UPDATED_SALES_ORDER_NO)
            .cmnCreateDate(UPDATED_CMN_CREATE_DATE)
            .cmnInitialDate(UPDATED_CMN_INITIAL_DATE)
            .cmnRevisionDate(UPDATED_CMN_REVISION_DATE)
            .cmnRecertificationDate(UPDATED_CMN_RECERTIFICATION_DATE)
            .cmnExpirationDate(UPDATED_CMN_EXPIRATION_DATE)
            .cmnLoggedBy(UPDATED_CMN_LOGGED_BY)
            .cmnLoggedDate(UPDATED_CMN_LOGGED_DATE)
            .cmnApprovedBy(UPDATED_CMN_APPROVED_BY)
            .cmnApprovedDate(UPDATED_CMN_APPROVED_DATE)
            .cmnPrintedBy(UPDATED_CMN_PRINTED_BY)
            .cmnPrintedDate(UPDATED_CMN_PRINTED_DATE)
            .lengthOfNeed(UPDATED_LENGTH_OF_NEED)
            .faxCmnOption(UPDATED_FAX_CMN_OPTION)
            .cmnCoverLetterInclusionOption(UPDATED_CMN_COVER_LETTER_INCLUSION_OPTION)
            .cmnFaxedBy(UPDATED_CMN_FAXED_BY)
            .cmnFaxedDate(UPDATED_CMN_FAXED_DATE)
            .faxStatus(UPDATED_FAX_STATUS)
            .faxStatusReason(UPDATED_FAX_STATUS_REASON)
            .printCmnOption(UPDATED_PRINT_CMN_OPTION)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .status(UPDATED_STATUS)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .cmnIdUuid(UPDATED_CMN_ID_UUID)
            .patientPrognosis(UPDATED_PATIENT_PROGNOSIS)
            .cmnStatus(UPDATED_CMN_STATUS)
            .refillAuthorised(UPDATED_REFILL_AUTHORISED);
        CmnDTO cmnDTO = cmnMapper.toDto(updatedCmn);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, cmnDTO.getCmnId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(cmnDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Cmn in the database
        List<Cmn> cmnList = cmnRepository.findAll().collectList().block();
        assertThat(cmnList).hasSize(databaseSizeBeforeUpdate);
        Cmn testCmn = cmnList.get(cmnList.size() - 1);
        assertThat(testCmn.getCmnNumber()).isEqualTo(UPDATED_CMN_NUMBER);
        assertThat(testCmn.getCmnType()).isEqualTo(UPDATED_CMN_TYPE);
        assertThat(testCmn.getCmnFormName()).isEqualTo(UPDATED_CMN_FORM_NAME);
        assertThat(testCmn.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testCmn.getSalesOrderId()).isEqualTo(UPDATED_SALES_ORDER_ID);
        assertThat(testCmn.getSalesOrderNo()).isEqualTo(UPDATED_SALES_ORDER_NO);
        assertThat(testCmn.getCmnCreateDate()).isEqualTo(UPDATED_CMN_CREATE_DATE);
        assertThat(testCmn.getCmnInitialDate()).isEqualTo(UPDATED_CMN_INITIAL_DATE);
        assertThat(testCmn.getCmnRevisionDate()).isEqualTo(UPDATED_CMN_REVISION_DATE);
        assertThat(testCmn.getCmnRecertificationDate()).isEqualTo(UPDATED_CMN_RECERTIFICATION_DATE);
        assertThat(testCmn.getCmnExpirationDate()).isEqualTo(UPDATED_CMN_EXPIRATION_DATE);
        assertThat(testCmn.getCmnLoggedBy()).isEqualTo(UPDATED_CMN_LOGGED_BY);
        assertThat(testCmn.getCmnLoggedDate()).isEqualTo(UPDATED_CMN_LOGGED_DATE);
        assertThat(testCmn.getCmnApprovedBy()).isEqualTo(UPDATED_CMN_APPROVED_BY);
        assertThat(testCmn.getCmnApprovedDate()).isEqualTo(UPDATED_CMN_APPROVED_DATE);
        assertThat(testCmn.getCmnPrintedBy()).isEqualTo(UPDATED_CMN_PRINTED_BY);
        assertThat(testCmn.getCmnPrintedDate()).isEqualTo(UPDATED_CMN_PRINTED_DATE);
        assertThat(testCmn.getLengthOfNeed()).isEqualTo(UPDATED_LENGTH_OF_NEED);
        assertThat(testCmn.getFaxCmnOption()).isEqualTo(UPDATED_FAX_CMN_OPTION);
        assertThat(testCmn.getCmnCoverLetterInclusionOption()).isEqualTo(UPDATED_CMN_COVER_LETTER_INCLUSION_OPTION);
        assertThat(testCmn.getCmnFaxedBy()).isEqualTo(UPDATED_CMN_FAXED_BY);
        assertThat(testCmn.getCmnFaxedDate()).isEqualTo(UPDATED_CMN_FAXED_DATE);
        assertThat(testCmn.getFaxStatus()).isEqualTo(UPDATED_FAX_STATUS);
        assertThat(testCmn.getFaxStatusReason()).isEqualTo(UPDATED_FAX_STATUS_REASON);
        assertThat(testCmn.getPrintCmnOption()).isEqualTo(UPDATED_PRINT_CMN_OPTION);
        assertThat(testCmn.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testCmn.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCmn.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testCmn.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testCmn.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testCmn.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testCmn.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testCmn.getCmnIdUuid()).isEqualTo(UPDATED_CMN_ID_UUID);
        assertThat(testCmn.getPatientPrognosis()).isEqualTo(UPDATED_PATIENT_PROGNOSIS);
        assertThat(testCmn.getCmnStatus()).isEqualTo(UPDATED_CMN_STATUS);
        assertThat(testCmn.getRefillAuthorised()).isEqualTo(UPDATED_REFILL_AUTHORISED);
    }

    @Test
    void putNonExistingCmn() throws Exception {
        int databaseSizeBeforeUpdate = cmnRepository.findAll().collectList().block().size();
        cmn.setCmnId(count.incrementAndGet());

        // Create the Cmn
        CmnDTO cmnDTO = cmnMapper.toDto(cmn);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, cmnDTO.getCmnId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(cmnDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Cmn in the database
        List<Cmn> cmnList = cmnRepository.findAll().collectList().block();
        assertThat(cmnList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchCmn() throws Exception {
        int databaseSizeBeforeUpdate = cmnRepository.findAll().collectList().block().size();
        cmn.setCmnId(count.incrementAndGet());

        // Create the Cmn
        CmnDTO cmnDTO = cmnMapper.toDto(cmn);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(cmnDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Cmn in the database
        List<Cmn> cmnList = cmnRepository.findAll().collectList().block();
        assertThat(cmnList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamCmn() throws Exception {
        int databaseSizeBeforeUpdate = cmnRepository.findAll().collectList().block().size();
        cmn.setCmnId(count.incrementAndGet());

        // Create the Cmn
        CmnDTO cmnDTO = cmnMapper.toDto(cmn);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(cmnDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Cmn in the database
        List<Cmn> cmnList = cmnRepository.findAll().collectList().block();
        assertThat(cmnList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateCmnWithPatch() throws Exception {
        // Initialize the database
        cmnRepository.save(cmn).block();

        int databaseSizeBeforeUpdate = cmnRepository.findAll().collectList().block().size();

        // Update the cmn using partial update
        Cmn partialUpdatedCmn = new Cmn();
        partialUpdatedCmn.setCmnId(cmn.getCmnId());

        partialUpdatedCmn
            .cmnFormName(UPDATED_CMN_FORM_NAME)
            .salesOrderId(UPDATED_SALES_ORDER_ID)
            .salesOrderNo(UPDATED_SALES_ORDER_NO)
            .cmnInitialDate(UPDATED_CMN_INITIAL_DATE)
            .cmnRecertificationDate(UPDATED_CMN_RECERTIFICATION_DATE)
            .cmnLoggedDate(UPDATED_CMN_LOGGED_DATE)
            .cmnPrintedDate(UPDATED_CMN_PRINTED_DATE)
            .lengthOfNeed(UPDATED_LENGTH_OF_NEED)
            .cmnCoverLetterInclusionOption(UPDATED_CMN_COVER_LETTER_INCLUSION_OPTION)
            .printCmnOption(UPDATED_PRINT_CMN_OPTION)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .cmnIdUuid(UPDATED_CMN_ID_UUID)
            .cmnStatus(UPDATED_CMN_STATUS)
            .refillAuthorised(UPDATED_REFILL_AUTHORISED);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedCmn.getCmnId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCmn))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Cmn in the database
        List<Cmn> cmnList = cmnRepository.findAll().collectList().block();
        assertThat(cmnList).hasSize(databaseSizeBeforeUpdate);
        Cmn testCmn = cmnList.get(cmnList.size() - 1);
        assertThat(testCmn.getCmnNumber()).isEqualTo(DEFAULT_CMN_NUMBER);
        assertThat(testCmn.getCmnType()).isEqualTo(DEFAULT_CMN_TYPE);
        assertThat(testCmn.getCmnFormName()).isEqualTo(UPDATED_CMN_FORM_NAME);
        assertThat(testCmn.getPatientId()).isEqualTo(DEFAULT_PATIENT_ID);
        assertThat(testCmn.getSalesOrderId()).isEqualTo(UPDATED_SALES_ORDER_ID);
        assertThat(testCmn.getSalesOrderNo()).isEqualTo(UPDATED_SALES_ORDER_NO);
        assertThat(testCmn.getCmnCreateDate()).isEqualTo(DEFAULT_CMN_CREATE_DATE);
        assertThat(testCmn.getCmnInitialDate()).isEqualTo(UPDATED_CMN_INITIAL_DATE);
        assertThat(testCmn.getCmnRevisionDate()).isEqualTo(DEFAULT_CMN_REVISION_DATE);
        assertThat(testCmn.getCmnRecertificationDate()).isEqualTo(UPDATED_CMN_RECERTIFICATION_DATE);
        assertThat(testCmn.getCmnExpirationDate()).isEqualTo(DEFAULT_CMN_EXPIRATION_DATE);
        assertThat(testCmn.getCmnLoggedBy()).isEqualTo(DEFAULT_CMN_LOGGED_BY);
        assertThat(testCmn.getCmnLoggedDate()).isEqualTo(UPDATED_CMN_LOGGED_DATE);
        assertThat(testCmn.getCmnApprovedBy()).isEqualTo(DEFAULT_CMN_APPROVED_BY);
        assertThat(testCmn.getCmnApprovedDate()).isEqualTo(DEFAULT_CMN_APPROVED_DATE);
        assertThat(testCmn.getCmnPrintedBy()).isEqualTo(DEFAULT_CMN_PRINTED_BY);
        assertThat(testCmn.getCmnPrintedDate()).isEqualTo(UPDATED_CMN_PRINTED_DATE);
        assertThat(testCmn.getLengthOfNeed()).isEqualTo(UPDATED_LENGTH_OF_NEED);
        assertThat(testCmn.getFaxCmnOption()).isEqualTo(DEFAULT_FAX_CMN_OPTION);
        assertThat(testCmn.getCmnCoverLetterInclusionOption()).isEqualTo(UPDATED_CMN_COVER_LETTER_INCLUSION_OPTION);
        assertThat(testCmn.getCmnFaxedBy()).isEqualTo(DEFAULT_CMN_FAXED_BY);
        assertThat(testCmn.getCmnFaxedDate()).isEqualTo(DEFAULT_CMN_FAXED_DATE);
        assertThat(testCmn.getFaxStatus()).isEqualTo(DEFAULT_FAX_STATUS);
        assertThat(testCmn.getFaxStatusReason()).isEqualTo(DEFAULT_FAX_STATUS_REASON);
        assertThat(testCmn.getPrintCmnOption()).isEqualTo(UPDATED_PRINT_CMN_OPTION);
        assertThat(testCmn.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testCmn.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCmn.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testCmn.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testCmn.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testCmn.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testCmn.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testCmn.getCmnIdUuid()).isEqualTo(UPDATED_CMN_ID_UUID);
        assertThat(testCmn.getPatientPrognosis()).isEqualTo(DEFAULT_PATIENT_PROGNOSIS);
        assertThat(testCmn.getCmnStatus()).isEqualTo(UPDATED_CMN_STATUS);
        assertThat(testCmn.getRefillAuthorised()).isEqualTo(UPDATED_REFILL_AUTHORISED);
    }

    @Test
    void fullUpdateCmnWithPatch() throws Exception {
        // Initialize the database
        cmnRepository.save(cmn).block();

        int databaseSizeBeforeUpdate = cmnRepository.findAll().collectList().block().size();

        // Update the cmn using partial update
        Cmn partialUpdatedCmn = new Cmn();
        partialUpdatedCmn.setCmnId(cmn.getCmnId());

        partialUpdatedCmn
            .cmnNumber(UPDATED_CMN_NUMBER)
            .cmnType(UPDATED_CMN_TYPE)
            .cmnFormName(UPDATED_CMN_FORM_NAME)
            .patientId(UPDATED_PATIENT_ID)
            .salesOrderId(UPDATED_SALES_ORDER_ID)
            .salesOrderNo(UPDATED_SALES_ORDER_NO)
            .cmnCreateDate(UPDATED_CMN_CREATE_DATE)
            .cmnInitialDate(UPDATED_CMN_INITIAL_DATE)
            .cmnRevisionDate(UPDATED_CMN_REVISION_DATE)
            .cmnRecertificationDate(UPDATED_CMN_RECERTIFICATION_DATE)
            .cmnExpirationDate(UPDATED_CMN_EXPIRATION_DATE)
            .cmnLoggedBy(UPDATED_CMN_LOGGED_BY)
            .cmnLoggedDate(UPDATED_CMN_LOGGED_DATE)
            .cmnApprovedBy(UPDATED_CMN_APPROVED_BY)
            .cmnApprovedDate(UPDATED_CMN_APPROVED_DATE)
            .cmnPrintedBy(UPDATED_CMN_PRINTED_BY)
            .cmnPrintedDate(UPDATED_CMN_PRINTED_DATE)
            .lengthOfNeed(UPDATED_LENGTH_OF_NEED)
            .faxCmnOption(UPDATED_FAX_CMN_OPTION)
            .cmnCoverLetterInclusionOption(UPDATED_CMN_COVER_LETTER_INCLUSION_OPTION)
            .cmnFaxedBy(UPDATED_CMN_FAXED_BY)
            .cmnFaxedDate(UPDATED_CMN_FAXED_DATE)
            .faxStatus(UPDATED_FAX_STATUS)
            .faxStatusReason(UPDATED_FAX_STATUS_REASON)
            .printCmnOption(UPDATED_PRINT_CMN_OPTION)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .status(UPDATED_STATUS)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .cmnIdUuid(UPDATED_CMN_ID_UUID)
            .patientPrognosis(UPDATED_PATIENT_PROGNOSIS)
            .cmnStatus(UPDATED_CMN_STATUS)
            .refillAuthorised(UPDATED_REFILL_AUTHORISED);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedCmn.getCmnId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCmn))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Cmn in the database
        List<Cmn> cmnList = cmnRepository.findAll().collectList().block();
        assertThat(cmnList).hasSize(databaseSizeBeforeUpdate);
        Cmn testCmn = cmnList.get(cmnList.size() - 1);
        assertThat(testCmn.getCmnNumber()).isEqualTo(UPDATED_CMN_NUMBER);
        assertThat(testCmn.getCmnType()).isEqualTo(UPDATED_CMN_TYPE);
        assertThat(testCmn.getCmnFormName()).isEqualTo(UPDATED_CMN_FORM_NAME);
        assertThat(testCmn.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testCmn.getSalesOrderId()).isEqualTo(UPDATED_SALES_ORDER_ID);
        assertThat(testCmn.getSalesOrderNo()).isEqualTo(UPDATED_SALES_ORDER_NO);
        assertThat(testCmn.getCmnCreateDate()).isEqualTo(UPDATED_CMN_CREATE_DATE);
        assertThat(testCmn.getCmnInitialDate()).isEqualTo(UPDATED_CMN_INITIAL_DATE);
        assertThat(testCmn.getCmnRevisionDate()).isEqualTo(UPDATED_CMN_REVISION_DATE);
        assertThat(testCmn.getCmnRecertificationDate()).isEqualTo(UPDATED_CMN_RECERTIFICATION_DATE);
        assertThat(testCmn.getCmnExpirationDate()).isEqualTo(UPDATED_CMN_EXPIRATION_DATE);
        assertThat(testCmn.getCmnLoggedBy()).isEqualTo(UPDATED_CMN_LOGGED_BY);
        assertThat(testCmn.getCmnLoggedDate()).isEqualTo(UPDATED_CMN_LOGGED_DATE);
        assertThat(testCmn.getCmnApprovedBy()).isEqualTo(UPDATED_CMN_APPROVED_BY);
        assertThat(testCmn.getCmnApprovedDate()).isEqualTo(UPDATED_CMN_APPROVED_DATE);
        assertThat(testCmn.getCmnPrintedBy()).isEqualTo(UPDATED_CMN_PRINTED_BY);
        assertThat(testCmn.getCmnPrintedDate()).isEqualTo(UPDATED_CMN_PRINTED_DATE);
        assertThat(testCmn.getLengthOfNeed()).isEqualTo(UPDATED_LENGTH_OF_NEED);
        assertThat(testCmn.getFaxCmnOption()).isEqualTo(UPDATED_FAX_CMN_OPTION);
        assertThat(testCmn.getCmnCoverLetterInclusionOption()).isEqualTo(UPDATED_CMN_COVER_LETTER_INCLUSION_OPTION);
        assertThat(testCmn.getCmnFaxedBy()).isEqualTo(UPDATED_CMN_FAXED_BY);
        assertThat(testCmn.getCmnFaxedDate()).isEqualTo(UPDATED_CMN_FAXED_DATE);
        assertThat(testCmn.getFaxStatus()).isEqualTo(UPDATED_FAX_STATUS);
        assertThat(testCmn.getFaxStatusReason()).isEqualTo(UPDATED_FAX_STATUS_REASON);
        assertThat(testCmn.getPrintCmnOption()).isEqualTo(UPDATED_PRINT_CMN_OPTION);
        assertThat(testCmn.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testCmn.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCmn.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testCmn.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testCmn.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testCmn.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testCmn.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testCmn.getCmnIdUuid()).isEqualTo(UPDATED_CMN_ID_UUID);
        assertThat(testCmn.getPatientPrognosis()).isEqualTo(UPDATED_PATIENT_PROGNOSIS);
        assertThat(testCmn.getCmnStatus()).isEqualTo(UPDATED_CMN_STATUS);
        assertThat(testCmn.getRefillAuthorised()).isEqualTo(UPDATED_REFILL_AUTHORISED);
    }

    @Test
    void patchNonExistingCmn() throws Exception {
        int databaseSizeBeforeUpdate = cmnRepository.findAll().collectList().block().size();
        cmn.setCmnId(count.incrementAndGet());

        // Create the Cmn
        CmnDTO cmnDTO = cmnMapper.toDto(cmn);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, cmnDTO.getCmnId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(cmnDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Cmn in the database
        List<Cmn> cmnList = cmnRepository.findAll().collectList().block();
        assertThat(cmnList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchCmn() throws Exception {
        int databaseSizeBeforeUpdate = cmnRepository.findAll().collectList().block().size();
        cmn.setCmnId(count.incrementAndGet());

        // Create the Cmn
        CmnDTO cmnDTO = cmnMapper.toDto(cmn);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(cmnDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Cmn in the database
        List<Cmn> cmnList = cmnRepository.findAll().collectList().block();
        assertThat(cmnList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamCmn() throws Exception {
        int databaseSizeBeforeUpdate = cmnRepository.findAll().collectList().block().size();
        cmn.setCmnId(count.incrementAndGet());

        // Create the Cmn
        CmnDTO cmnDTO = cmnMapper.toDto(cmn);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(cmnDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Cmn in the database
        List<Cmn> cmnList = cmnRepository.findAll().collectList().block();
        assertThat(cmnList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteCmn() {
        // Initialize the database
        cmnRepository.save(cmn).block();

        int databaseSizeBeforeDelete = cmnRepository.findAll().collectList().block().size();

        // Delete the cmn
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, cmn.getCmnId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<Cmn> cmnList = cmnRepository.findAll().collectList().block();
        assertThat(cmnList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
