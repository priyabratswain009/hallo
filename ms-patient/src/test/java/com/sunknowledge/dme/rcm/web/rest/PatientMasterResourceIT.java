package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.PatientMaster;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.PatientMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.PatientMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.PatientMasterMapper;
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
 * Integration tests for the {@link PatientMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class PatientMasterResourceIT {

    private static final String DEFAULT_PATIENT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_LAST_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DOB = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DOB = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_GENDER = "BBBBBBBBBB";

    private static final String DEFAULT_SSN = "AAAAAAAAAA";
    private static final String UPDATED_SSN = "BBBBBBBBBB";

    private static final Long DEFAULT_TAX_ZONE_ID = 1L;
    private static final Long UPDATED_TAX_ZONE_ID = 2L;

    private static final Double DEFAULT_DISCOUNT_PERCENT = 1D;
    private static final Double UPDATED_DISCOUNT_PERCENT = 2D;

    private static final Long DEFAULT_POS_ID = 1L;
    private static final Long UPDATED_POS_ID = 2L;

    private static final String DEFAULT_PRIOR_SYSTEM_KEY = "AAAAAAAAAA";
    private static final String UPDATED_PRIOR_SYSTEM_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_BRANCH_ID = 1L;
    private static final Long UPDATED_BRANCH_ID = 2L;

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final UUID DEFAULT_PATIENT_MASTER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_PATIENT_MASTER_UUID = UUID.randomUUID();

    private static final String DEFAULT_PATIENT_ID_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_ID_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_ZIP = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_NO_1 = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_NO_1 = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_NO_2 = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_NO_2 = "BBBBBBBBBB";

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_EFAX = "AAAAAAAAAA";
    private static final String UPDATED_EFAX = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_MODE_OF_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_MODE_OF_CONTACT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_BRANCH_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_ADDRESS_CITY = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_ADDRESS_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_ADDRESS_STATE = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_ADDRESS_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_ADDRESS_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_ADDRESS_ZIP = "BBBBBBBBBB";

    private static final String DEFAULT_CAREGIVER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CAREGIVER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CAREGIVER_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_CAREGIVER_CONTACT = "BBBBBBBBBB";

    private static final String DEFAULT_CAREGIVER_RELATINSHIP_PATIENT = "AAAAAAAAAA";
    private static final String UPDATED_CAREGIVER_RELATINSHIP_PATIENT = "BBBBBBBBBB";

    private static final String DEFAULT_TAX_ZONE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TAX_ZONE_NAME = "BBBBBBBBBB";

    private static final Double DEFAULT_TAX_RATE = 1D;
    private static final Double UPDATED_TAX_RATE = 2D;

    private static final LocalDate DEFAULT_PATIENT_DOD = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PATIENT_DOD = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/patient-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{patientId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PatientMasterRepository patientMasterRepository;

    @Autowired
    private PatientMasterMapper patientMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private PatientMaster patientMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PatientMaster createEntity(EntityManager em) {
        PatientMaster patientMaster = new PatientMaster()
            .patientFirstName(DEFAULT_PATIENT_FIRST_NAME)
            .patientMiddleName(DEFAULT_PATIENT_MIDDLE_NAME)
            .patientLastName(DEFAULT_PATIENT_LAST_NAME)
            .dob(DEFAULT_DOB)
            .gender(DEFAULT_GENDER)
            .ssn(DEFAULT_SSN)
            .taxZoneId(DEFAULT_TAX_ZONE_ID)
            .discountPercent(DEFAULT_DISCOUNT_PERCENT)
            .posId(DEFAULT_POS_ID)
            .priorSystemKey(DEFAULT_PRIOR_SYSTEM_KEY)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .branchId(DEFAULT_BRANCH_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .patientMasterUUID(DEFAULT_PATIENT_MASTER_UUID)
            .patientIdNumber(DEFAULT_PATIENT_ID_NUMBER)
            .addressLine1(DEFAULT_ADDRESS_LINE_1)
            .addressLine2(DEFAULT_ADDRESS_LINE_2)
            .city(DEFAULT_CITY)
            .state(DEFAULT_STATE)
            .country(DEFAULT_COUNTRY)
            .zip(DEFAULT_ZIP)
            .contactNo1(DEFAULT_CONTACT_NO_1)
            .contactNo2(DEFAULT_CONTACT_NO_2)
            .fax(DEFAULT_FAX)
            .efax(DEFAULT_EFAX)
            .email(DEFAULT_EMAIL)
            .modeOfContact(DEFAULT_MODE_OF_CONTACT)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .branchName(DEFAULT_BRANCH_NAME)
            .billingAddressLine1(DEFAULT_BILLING_ADDRESS_LINE_1)
            .billingAddressLine2(DEFAULT_BILLING_ADDRESS_LINE_2)
            .billingAddressCity(DEFAULT_BILLING_ADDRESS_CITY)
            .billingAddressState(DEFAULT_BILLING_ADDRESS_STATE)
            .billingAddressZip(DEFAULT_BILLING_ADDRESS_ZIP)
            .caregiverName(DEFAULT_CAREGIVER_NAME)
            .caregiverContact(DEFAULT_CAREGIVER_CONTACT)
            .caregiverRelatinshipPatient(DEFAULT_CAREGIVER_RELATINSHIP_PATIENT)
            .taxZoneName(DEFAULT_TAX_ZONE_NAME)
            .taxRate(DEFAULT_TAX_RATE)
            .patientDod(DEFAULT_PATIENT_DOD);
        return patientMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PatientMaster createUpdatedEntity(EntityManager em) {
        PatientMaster patientMaster = new PatientMaster()
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientMiddleName(UPDATED_PATIENT_MIDDLE_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .dob(UPDATED_DOB)
            .gender(UPDATED_GENDER)
            .ssn(UPDATED_SSN)
            .taxZoneId(UPDATED_TAX_ZONE_ID)
            .discountPercent(UPDATED_DISCOUNT_PERCENT)
            .posId(UPDATED_POS_ID)
            .priorSystemKey(UPDATED_PRIOR_SYSTEM_KEY)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .branchId(UPDATED_BRANCH_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .patientMasterUUID(UPDATED_PATIENT_MASTER_UUID)
            .patientIdNumber(UPDATED_PATIENT_ID_NUMBER)
            .addressLine1(UPDATED_ADDRESS_LINE_1)
            .addressLine2(UPDATED_ADDRESS_LINE_2)
            .city(UPDATED_CITY)
            .state(UPDATED_STATE)
            .country(UPDATED_COUNTRY)
            .zip(UPDATED_ZIP)
            .contactNo1(UPDATED_CONTACT_NO_1)
            .contactNo2(UPDATED_CONTACT_NO_2)
            .fax(UPDATED_FAX)
            .efax(UPDATED_EFAX)
            .email(UPDATED_EMAIL)
            .modeOfContact(UPDATED_MODE_OF_CONTACT)
            .updatedDate(UPDATED_UPDATED_DATE)
            .branchName(UPDATED_BRANCH_NAME)
            .billingAddressLine1(UPDATED_BILLING_ADDRESS_LINE_1)
            .billingAddressLine2(UPDATED_BILLING_ADDRESS_LINE_2)
            .billingAddressCity(UPDATED_BILLING_ADDRESS_CITY)
            .billingAddressState(UPDATED_BILLING_ADDRESS_STATE)
            .billingAddressZip(UPDATED_BILLING_ADDRESS_ZIP)
            .caregiverName(UPDATED_CAREGIVER_NAME)
            .caregiverContact(UPDATED_CAREGIVER_CONTACT)
            .caregiverRelatinshipPatient(UPDATED_CAREGIVER_RELATINSHIP_PATIENT)
            .taxZoneName(UPDATED_TAX_ZONE_NAME)
            .taxRate(UPDATED_TAX_RATE)
            .patientDod(UPDATED_PATIENT_DOD);
        return patientMaster;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(PatientMaster.class).block();
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
        patientMaster = createEntity(em);
    }

    @Test
    void createPatientMaster() throws Exception {
        int databaseSizeBeforeCreate = patientMasterRepository.findAll().collectList().block().size();
        // Create the PatientMaster
        PatientMasterDTO patientMasterDTO = patientMasterMapper.toDto(patientMaster);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientMasterDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the PatientMaster in the database
        List<PatientMaster> patientMasterList = patientMasterRepository.findAll().collectList().block();
        assertThat(patientMasterList).hasSize(databaseSizeBeforeCreate + 1);
        PatientMaster testPatientMaster = patientMasterList.get(patientMasterList.size() - 1);
        assertThat(testPatientMaster.getPatientFirstName()).isEqualTo(DEFAULT_PATIENT_FIRST_NAME);
        assertThat(testPatientMaster.getPatientMiddleName()).isEqualTo(DEFAULT_PATIENT_MIDDLE_NAME);
        assertThat(testPatientMaster.getPatientLastName()).isEqualTo(DEFAULT_PATIENT_LAST_NAME);
        assertThat(testPatientMaster.getDob()).isEqualTo(DEFAULT_DOB);
        assertThat(testPatientMaster.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testPatientMaster.getSsn()).isEqualTo(DEFAULT_SSN);
        assertThat(testPatientMaster.getTaxZoneId()).isEqualTo(DEFAULT_TAX_ZONE_ID);
        assertThat(testPatientMaster.getDiscountPercent()).isEqualTo(DEFAULT_DISCOUNT_PERCENT);
        assertThat(testPatientMaster.getPosId()).isEqualTo(DEFAULT_POS_ID);
        assertThat(testPatientMaster.getPriorSystemKey()).isEqualTo(DEFAULT_PRIOR_SYSTEM_KEY);
        assertThat(testPatientMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPatientMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testPatientMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testPatientMaster.getBranchId()).isEqualTo(DEFAULT_BRANCH_ID);
        assertThat(testPatientMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testPatientMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testPatientMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testPatientMaster.getPatientMasterUUID()).isEqualTo(DEFAULT_PATIENT_MASTER_UUID);
        assertThat(testPatientMaster.getPatientIdNumber()).isEqualTo(DEFAULT_PATIENT_ID_NUMBER);
        assertThat(testPatientMaster.getAddressLine1()).isEqualTo(DEFAULT_ADDRESS_LINE_1);
        assertThat(testPatientMaster.getAddressLine2()).isEqualTo(DEFAULT_ADDRESS_LINE_2);
        assertThat(testPatientMaster.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testPatientMaster.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testPatientMaster.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testPatientMaster.getZip()).isEqualTo(DEFAULT_ZIP);
        assertThat(testPatientMaster.getContactNo1()).isEqualTo(DEFAULT_CONTACT_NO_1);
        assertThat(testPatientMaster.getContactNo2()).isEqualTo(DEFAULT_CONTACT_NO_2);
        assertThat(testPatientMaster.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testPatientMaster.getEfax()).isEqualTo(DEFAULT_EFAX);
        assertThat(testPatientMaster.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testPatientMaster.getModeOfContact()).isEqualTo(DEFAULT_MODE_OF_CONTACT);
        assertThat(testPatientMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testPatientMaster.getBranchName()).isEqualTo(DEFAULT_BRANCH_NAME);
        assertThat(testPatientMaster.getBillingAddressLine1()).isEqualTo(DEFAULT_BILLING_ADDRESS_LINE_1);
        assertThat(testPatientMaster.getBillingAddressLine2()).isEqualTo(DEFAULT_BILLING_ADDRESS_LINE_2);
        assertThat(testPatientMaster.getBillingAddressCity()).isEqualTo(DEFAULT_BILLING_ADDRESS_CITY);
        assertThat(testPatientMaster.getBillingAddressState()).isEqualTo(DEFAULT_BILLING_ADDRESS_STATE);
        assertThat(testPatientMaster.getBillingAddressZip()).isEqualTo(DEFAULT_BILLING_ADDRESS_ZIP);
        assertThat(testPatientMaster.getCaregiverName()).isEqualTo(DEFAULT_CAREGIVER_NAME);
        assertThat(testPatientMaster.getCaregiverContact()).isEqualTo(DEFAULT_CAREGIVER_CONTACT);
        assertThat(testPatientMaster.getCaregiverRelatinshipPatient()).isEqualTo(DEFAULT_CAREGIVER_RELATINSHIP_PATIENT);
        assertThat(testPatientMaster.getTaxZoneName()).isEqualTo(DEFAULT_TAX_ZONE_NAME);
        assertThat(testPatientMaster.getTaxRate()).isEqualTo(DEFAULT_TAX_RATE);
        assertThat(testPatientMaster.getPatientDod()).isEqualTo(DEFAULT_PATIENT_DOD);
    }

    @Test
    void createPatientMasterWithExistingId() throws Exception {
        // Create the PatientMaster with an existing ID
        patientMaster.setPatientId(1L);
        PatientMasterDTO patientMasterDTO = patientMasterMapper.toDto(patientMaster);

        int databaseSizeBeforeCreate = patientMasterRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientMaster in the database
        List<PatientMaster> patientMasterList = patientMasterRepository.findAll().collectList().block();
        assertThat(patientMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllPatientMasters() {
        // Initialize the database
        patientMasterRepository.save(patientMaster).block();

        // Get all the patientMasterList
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
            .value(hasItem(patientMaster.getPatientId().intValue()))
            .jsonPath("$.[*].patientFirstName")
            .value(hasItem(DEFAULT_PATIENT_FIRST_NAME))
            .jsonPath("$.[*].patientMiddleName")
            .value(hasItem(DEFAULT_PATIENT_MIDDLE_NAME))
            .jsonPath("$.[*].patientLastName")
            .value(hasItem(DEFAULT_PATIENT_LAST_NAME))
            .jsonPath("$.[*].dob")
            .value(hasItem(DEFAULT_DOB.toString()))
            .jsonPath("$.[*].gender")
            .value(hasItem(DEFAULT_GENDER))
            .jsonPath("$.[*].ssn")
            .value(hasItem(DEFAULT_SSN))
            .jsonPath("$.[*].taxZoneId")
            .value(hasItem(DEFAULT_TAX_ZONE_ID.intValue()))
            .jsonPath("$.[*].discountPercent")
            .value(hasItem(DEFAULT_DISCOUNT_PERCENT.doubleValue()))
            .jsonPath("$.[*].posId")
            .value(hasItem(DEFAULT_POS_ID.intValue()))
            .jsonPath("$.[*].priorSystemKey")
            .value(hasItem(DEFAULT_PRIOR_SYSTEM_KEY))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS))
            .jsonPath("$.[*].createdById")
            .value(hasItem(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.[*].createdDate")
            .value(hasItem(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.[*].branchId")
            .value(hasItem(DEFAULT_BRANCH_ID.intValue()))
            .jsonPath("$.[*].createdByName")
            .value(hasItem(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.[*].updatedByName")
            .value(hasItem(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.[*].updatedById")
            .value(hasItem(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.[*].patientMasterUUID")
            .value(hasItem(DEFAULT_PATIENT_MASTER_UUID.toString()))
            .jsonPath("$.[*].patientIdNumber")
            .value(hasItem(DEFAULT_PATIENT_ID_NUMBER))
            .jsonPath("$.[*].addressLine1")
            .value(hasItem(DEFAULT_ADDRESS_LINE_1))
            .jsonPath("$.[*].addressLine2")
            .value(hasItem(DEFAULT_ADDRESS_LINE_2))
            .jsonPath("$.[*].city")
            .value(hasItem(DEFAULT_CITY))
            .jsonPath("$.[*].state")
            .value(hasItem(DEFAULT_STATE))
            .jsonPath("$.[*].country")
            .value(hasItem(DEFAULT_COUNTRY))
            .jsonPath("$.[*].zip")
            .value(hasItem(DEFAULT_ZIP))
            .jsonPath("$.[*].contactNo1")
            .value(hasItem(DEFAULT_CONTACT_NO_1))
            .jsonPath("$.[*].contactNo2")
            .value(hasItem(DEFAULT_CONTACT_NO_2))
            .jsonPath("$.[*].fax")
            .value(hasItem(DEFAULT_FAX))
            .jsonPath("$.[*].efax")
            .value(hasItem(DEFAULT_EFAX))
            .jsonPath("$.[*].email")
            .value(hasItem(DEFAULT_EMAIL))
            .jsonPath("$.[*].modeOfContact")
            .value(hasItem(DEFAULT_MODE_OF_CONTACT))
            .jsonPath("$.[*].updatedDate")
            .value(hasItem(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.[*].branchName")
            .value(hasItem(DEFAULT_BRANCH_NAME))
            .jsonPath("$.[*].billingAddressLine1")
            .value(hasItem(DEFAULT_BILLING_ADDRESS_LINE_1))
            .jsonPath("$.[*].billingAddressLine2")
            .value(hasItem(DEFAULT_BILLING_ADDRESS_LINE_2))
            .jsonPath("$.[*].billingAddressCity")
            .value(hasItem(DEFAULT_BILLING_ADDRESS_CITY))
            .jsonPath("$.[*].billingAddressState")
            .value(hasItem(DEFAULT_BILLING_ADDRESS_STATE))
            .jsonPath("$.[*].billingAddressZip")
            .value(hasItem(DEFAULT_BILLING_ADDRESS_ZIP))
            .jsonPath("$.[*].caregiverName")
            .value(hasItem(DEFAULT_CAREGIVER_NAME))
            .jsonPath("$.[*].caregiverContact")
            .value(hasItem(DEFAULT_CAREGIVER_CONTACT))
            .jsonPath("$.[*].caregiverRelatinshipPatient")
            .value(hasItem(DEFAULT_CAREGIVER_RELATINSHIP_PATIENT))
            .jsonPath("$.[*].taxZoneName")
            .value(hasItem(DEFAULT_TAX_ZONE_NAME))
            .jsonPath("$.[*].taxRate")
            .value(hasItem(DEFAULT_TAX_RATE.doubleValue()))
            .jsonPath("$.[*].patientDod")
            .value(hasItem(DEFAULT_PATIENT_DOD.toString()));
    }

    @Test
    void getPatientMaster() {
        // Initialize the database
        patientMasterRepository.save(patientMaster).block();

        // Get the patientMaster
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, patientMaster.getPatientId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.patientId")
            .value(is(patientMaster.getPatientId().intValue()))
            .jsonPath("$.patientFirstName")
            .value(is(DEFAULT_PATIENT_FIRST_NAME))
            .jsonPath("$.patientMiddleName")
            .value(is(DEFAULT_PATIENT_MIDDLE_NAME))
            .jsonPath("$.patientLastName")
            .value(is(DEFAULT_PATIENT_LAST_NAME))
            .jsonPath("$.dob")
            .value(is(DEFAULT_DOB.toString()))
            .jsonPath("$.gender")
            .value(is(DEFAULT_GENDER))
            .jsonPath("$.ssn")
            .value(is(DEFAULT_SSN))
            .jsonPath("$.taxZoneId")
            .value(is(DEFAULT_TAX_ZONE_ID.intValue()))
            .jsonPath("$.discountPercent")
            .value(is(DEFAULT_DISCOUNT_PERCENT.doubleValue()))
            .jsonPath("$.posId")
            .value(is(DEFAULT_POS_ID.intValue()))
            .jsonPath("$.priorSystemKey")
            .value(is(DEFAULT_PRIOR_SYSTEM_KEY))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS))
            .jsonPath("$.createdById")
            .value(is(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.createdDate")
            .value(is(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.branchId")
            .value(is(DEFAULT_BRANCH_ID.intValue()))
            .jsonPath("$.createdByName")
            .value(is(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.updatedByName")
            .value(is(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.updatedById")
            .value(is(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.patientMasterUUID")
            .value(is(DEFAULT_PATIENT_MASTER_UUID.toString()))
            .jsonPath("$.patientIdNumber")
            .value(is(DEFAULT_PATIENT_ID_NUMBER))
            .jsonPath("$.addressLine1")
            .value(is(DEFAULT_ADDRESS_LINE_1))
            .jsonPath("$.addressLine2")
            .value(is(DEFAULT_ADDRESS_LINE_2))
            .jsonPath("$.city")
            .value(is(DEFAULT_CITY))
            .jsonPath("$.state")
            .value(is(DEFAULT_STATE))
            .jsonPath("$.country")
            .value(is(DEFAULT_COUNTRY))
            .jsonPath("$.zip")
            .value(is(DEFAULT_ZIP))
            .jsonPath("$.contactNo1")
            .value(is(DEFAULT_CONTACT_NO_1))
            .jsonPath("$.contactNo2")
            .value(is(DEFAULT_CONTACT_NO_2))
            .jsonPath("$.fax")
            .value(is(DEFAULT_FAX))
            .jsonPath("$.efax")
            .value(is(DEFAULT_EFAX))
            .jsonPath("$.email")
            .value(is(DEFAULT_EMAIL))
            .jsonPath("$.modeOfContact")
            .value(is(DEFAULT_MODE_OF_CONTACT))
            .jsonPath("$.updatedDate")
            .value(is(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.branchName")
            .value(is(DEFAULT_BRANCH_NAME))
            .jsonPath("$.billingAddressLine1")
            .value(is(DEFAULT_BILLING_ADDRESS_LINE_1))
            .jsonPath("$.billingAddressLine2")
            .value(is(DEFAULT_BILLING_ADDRESS_LINE_2))
            .jsonPath("$.billingAddressCity")
            .value(is(DEFAULT_BILLING_ADDRESS_CITY))
            .jsonPath("$.billingAddressState")
            .value(is(DEFAULT_BILLING_ADDRESS_STATE))
            .jsonPath("$.billingAddressZip")
            .value(is(DEFAULT_BILLING_ADDRESS_ZIP))
            .jsonPath("$.caregiverName")
            .value(is(DEFAULT_CAREGIVER_NAME))
            .jsonPath("$.caregiverContact")
            .value(is(DEFAULT_CAREGIVER_CONTACT))
            .jsonPath("$.caregiverRelatinshipPatient")
            .value(is(DEFAULT_CAREGIVER_RELATINSHIP_PATIENT))
            .jsonPath("$.taxZoneName")
            .value(is(DEFAULT_TAX_ZONE_NAME))
            .jsonPath("$.taxRate")
            .value(is(DEFAULT_TAX_RATE.doubleValue()))
            .jsonPath("$.patientDod")
            .value(is(DEFAULT_PATIENT_DOD.toString()));
    }

    @Test
    void getNonExistingPatientMaster() {
        // Get the patientMaster
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingPatientMaster() throws Exception {
        // Initialize the database
        patientMasterRepository.save(patientMaster).block();

        int databaseSizeBeforeUpdate = patientMasterRepository.findAll().collectList().block().size();

        // Update the patientMaster
        PatientMaster updatedPatientMaster = patientMasterRepository.findById(patientMaster.getPatientId()).block();
        updatedPatientMaster
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientMiddleName(UPDATED_PATIENT_MIDDLE_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .dob(UPDATED_DOB)
            .gender(UPDATED_GENDER)
            .ssn(UPDATED_SSN)
            .taxZoneId(UPDATED_TAX_ZONE_ID)
            .discountPercent(UPDATED_DISCOUNT_PERCENT)
            .posId(UPDATED_POS_ID)
            .priorSystemKey(UPDATED_PRIOR_SYSTEM_KEY)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .branchId(UPDATED_BRANCH_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .patientMasterUUID(UPDATED_PATIENT_MASTER_UUID)
            .patientIdNumber(UPDATED_PATIENT_ID_NUMBER)
            .addressLine1(UPDATED_ADDRESS_LINE_1)
            .addressLine2(UPDATED_ADDRESS_LINE_2)
            .city(UPDATED_CITY)
            .state(UPDATED_STATE)
            .country(UPDATED_COUNTRY)
            .zip(UPDATED_ZIP)
            .contactNo1(UPDATED_CONTACT_NO_1)
            .contactNo2(UPDATED_CONTACT_NO_2)
            .fax(UPDATED_FAX)
            .efax(UPDATED_EFAX)
            .email(UPDATED_EMAIL)
            .modeOfContact(UPDATED_MODE_OF_CONTACT)
            .updatedDate(UPDATED_UPDATED_DATE)
            .branchName(UPDATED_BRANCH_NAME)
            .billingAddressLine1(UPDATED_BILLING_ADDRESS_LINE_1)
            .billingAddressLine2(UPDATED_BILLING_ADDRESS_LINE_2)
            .billingAddressCity(UPDATED_BILLING_ADDRESS_CITY)
            .billingAddressState(UPDATED_BILLING_ADDRESS_STATE)
            .billingAddressZip(UPDATED_BILLING_ADDRESS_ZIP)
            .caregiverName(UPDATED_CAREGIVER_NAME)
            .caregiverContact(UPDATED_CAREGIVER_CONTACT)
            .caregiverRelatinshipPatient(UPDATED_CAREGIVER_RELATINSHIP_PATIENT)
            .taxZoneName(UPDATED_TAX_ZONE_NAME)
            .taxRate(UPDATED_TAX_RATE)
            .patientDod(UPDATED_PATIENT_DOD);
        PatientMasterDTO patientMasterDTO = patientMasterMapper.toDto(updatedPatientMaster);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, patientMasterDTO.getPatientId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientMasterDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientMaster in the database
        List<PatientMaster> patientMasterList = patientMasterRepository.findAll().collectList().block();
        assertThat(patientMasterList).hasSize(databaseSizeBeforeUpdate);
        PatientMaster testPatientMaster = patientMasterList.get(patientMasterList.size() - 1);
        assertThat(testPatientMaster.getPatientFirstName()).isEqualTo(UPDATED_PATIENT_FIRST_NAME);
        assertThat(testPatientMaster.getPatientMiddleName()).isEqualTo(UPDATED_PATIENT_MIDDLE_NAME);
        assertThat(testPatientMaster.getPatientLastName()).isEqualTo(UPDATED_PATIENT_LAST_NAME);
        assertThat(testPatientMaster.getDob()).isEqualTo(UPDATED_DOB);
        assertThat(testPatientMaster.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testPatientMaster.getSsn()).isEqualTo(UPDATED_SSN);
        assertThat(testPatientMaster.getTaxZoneId()).isEqualTo(UPDATED_TAX_ZONE_ID);
        assertThat(testPatientMaster.getDiscountPercent()).isEqualTo(UPDATED_DISCOUNT_PERCENT);
        assertThat(testPatientMaster.getPosId()).isEqualTo(UPDATED_POS_ID);
        assertThat(testPatientMaster.getPriorSystemKey()).isEqualTo(UPDATED_PRIOR_SYSTEM_KEY);
        assertThat(testPatientMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPatientMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPatientMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPatientMaster.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
        assertThat(testPatientMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPatientMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPatientMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPatientMaster.getPatientMasterUUID()).isEqualTo(UPDATED_PATIENT_MASTER_UUID);
        assertThat(testPatientMaster.getPatientIdNumber()).isEqualTo(UPDATED_PATIENT_ID_NUMBER);
        assertThat(testPatientMaster.getAddressLine1()).isEqualTo(UPDATED_ADDRESS_LINE_1);
        assertThat(testPatientMaster.getAddressLine2()).isEqualTo(UPDATED_ADDRESS_LINE_2);
        assertThat(testPatientMaster.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testPatientMaster.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testPatientMaster.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testPatientMaster.getZip()).isEqualTo(UPDATED_ZIP);
        assertThat(testPatientMaster.getContactNo1()).isEqualTo(UPDATED_CONTACT_NO_1);
        assertThat(testPatientMaster.getContactNo2()).isEqualTo(UPDATED_CONTACT_NO_2);
        assertThat(testPatientMaster.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testPatientMaster.getEfax()).isEqualTo(UPDATED_EFAX);
        assertThat(testPatientMaster.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testPatientMaster.getModeOfContact()).isEqualTo(UPDATED_MODE_OF_CONTACT);
        assertThat(testPatientMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPatientMaster.getBranchName()).isEqualTo(UPDATED_BRANCH_NAME);
        assertThat(testPatientMaster.getBillingAddressLine1()).isEqualTo(UPDATED_BILLING_ADDRESS_LINE_1);
        assertThat(testPatientMaster.getBillingAddressLine2()).isEqualTo(UPDATED_BILLING_ADDRESS_LINE_2);
        assertThat(testPatientMaster.getBillingAddressCity()).isEqualTo(UPDATED_BILLING_ADDRESS_CITY);
        assertThat(testPatientMaster.getBillingAddressState()).isEqualTo(UPDATED_BILLING_ADDRESS_STATE);
        assertThat(testPatientMaster.getBillingAddressZip()).isEqualTo(UPDATED_BILLING_ADDRESS_ZIP);
        assertThat(testPatientMaster.getCaregiverName()).isEqualTo(UPDATED_CAREGIVER_NAME);
        assertThat(testPatientMaster.getCaregiverContact()).isEqualTo(UPDATED_CAREGIVER_CONTACT);
        assertThat(testPatientMaster.getCaregiverRelatinshipPatient()).isEqualTo(UPDATED_CAREGIVER_RELATINSHIP_PATIENT);
        assertThat(testPatientMaster.getTaxZoneName()).isEqualTo(UPDATED_TAX_ZONE_NAME);
        assertThat(testPatientMaster.getTaxRate()).isEqualTo(UPDATED_TAX_RATE);
        assertThat(testPatientMaster.getPatientDod()).isEqualTo(UPDATED_PATIENT_DOD);
    }

    @Test
    void putNonExistingPatientMaster() throws Exception {
        int databaseSizeBeforeUpdate = patientMasterRepository.findAll().collectList().block().size();
        patientMaster.setPatientId(count.incrementAndGet());

        // Create the PatientMaster
        PatientMasterDTO patientMasterDTO = patientMasterMapper.toDto(patientMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, patientMasterDTO.getPatientId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientMaster in the database
        List<PatientMaster> patientMasterList = patientMasterRepository.findAll().collectList().block();
        assertThat(patientMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchPatientMaster() throws Exception {
        int databaseSizeBeforeUpdate = patientMasterRepository.findAll().collectList().block().size();
        patientMaster.setPatientId(count.incrementAndGet());

        // Create the PatientMaster
        PatientMasterDTO patientMasterDTO = patientMasterMapper.toDto(patientMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientMaster in the database
        List<PatientMaster> patientMasterList = patientMasterRepository.findAll().collectList().block();
        assertThat(patientMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamPatientMaster() throws Exception {
        int databaseSizeBeforeUpdate = patientMasterRepository.findAll().collectList().block().size();
        patientMaster.setPatientId(count.incrementAndGet());

        // Create the PatientMaster
        PatientMasterDTO patientMasterDTO = patientMasterMapper.toDto(patientMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientMasterDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PatientMaster in the database
        List<PatientMaster> patientMasterList = patientMasterRepository.findAll().collectList().block();
        assertThat(patientMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdatePatientMasterWithPatch() throws Exception {
        // Initialize the database
        patientMasterRepository.save(patientMaster).block();

        int databaseSizeBeforeUpdate = patientMasterRepository.findAll().collectList().block().size();

        // Update the patientMaster using partial update
        PatientMaster partialUpdatedPatientMaster = new PatientMaster();
        partialUpdatedPatientMaster.setPatientId(patientMaster.getPatientId());

        partialUpdatedPatientMaster
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .ssn(UPDATED_SSN)
            .taxZoneId(UPDATED_TAX_ZONE_ID)
            .discountPercent(UPDATED_DISCOUNT_PERCENT)
            .priorSystemKey(UPDATED_PRIOR_SYSTEM_KEY)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .addressLine1(UPDATED_ADDRESS_LINE_1)
            .addressLine2(UPDATED_ADDRESS_LINE_2)
            .city(UPDATED_CITY)
            .state(UPDATED_STATE)
            .contactNo2(UPDATED_CONTACT_NO_2)
            .branchName(UPDATED_BRANCH_NAME)
            .billingAddressLine1(UPDATED_BILLING_ADDRESS_LINE_1)
            .billingAddressLine2(UPDATED_BILLING_ADDRESS_LINE_2)
            .billingAddressCity(UPDATED_BILLING_ADDRESS_CITY)
            .billingAddressZip(UPDATED_BILLING_ADDRESS_ZIP)
            .caregiverName(UPDATED_CAREGIVER_NAME)
            .caregiverContact(UPDATED_CAREGIVER_CONTACT)
            .taxZoneName(UPDATED_TAX_ZONE_NAME)
            .taxRate(UPDATED_TAX_RATE)
            .patientDod(UPDATED_PATIENT_DOD);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPatientMaster.getPatientId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPatientMaster))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientMaster in the database
        List<PatientMaster> patientMasterList = patientMasterRepository.findAll().collectList().block();
        assertThat(patientMasterList).hasSize(databaseSizeBeforeUpdate);
        PatientMaster testPatientMaster = patientMasterList.get(patientMasterList.size() - 1);
        assertThat(testPatientMaster.getPatientFirstName()).isEqualTo(UPDATED_PATIENT_FIRST_NAME);
        assertThat(testPatientMaster.getPatientMiddleName()).isEqualTo(DEFAULT_PATIENT_MIDDLE_NAME);
        assertThat(testPatientMaster.getPatientLastName()).isEqualTo(DEFAULT_PATIENT_LAST_NAME);
        assertThat(testPatientMaster.getDob()).isEqualTo(DEFAULT_DOB);
        assertThat(testPatientMaster.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testPatientMaster.getSsn()).isEqualTo(UPDATED_SSN);
        assertThat(testPatientMaster.getTaxZoneId()).isEqualTo(UPDATED_TAX_ZONE_ID);
        assertThat(testPatientMaster.getDiscountPercent()).isEqualTo(UPDATED_DISCOUNT_PERCENT);
        assertThat(testPatientMaster.getPosId()).isEqualTo(DEFAULT_POS_ID);
        assertThat(testPatientMaster.getPriorSystemKey()).isEqualTo(UPDATED_PRIOR_SYSTEM_KEY);
        assertThat(testPatientMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPatientMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPatientMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testPatientMaster.getBranchId()).isEqualTo(DEFAULT_BRANCH_ID);
        assertThat(testPatientMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testPatientMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPatientMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPatientMaster.getPatientMasterUUID()).isEqualTo(DEFAULT_PATIENT_MASTER_UUID);
        assertThat(testPatientMaster.getPatientIdNumber()).isEqualTo(DEFAULT_PATIENT_ID_NUMBER);
        assertThat(testPatientMaster.getAddressLine1()).isEqualTo(UPDATED_ADDRESS_LINE_1);
        assertThat(testPatientMaster.getAddressLine2()).isEqualTo(UPDATED_ADDRESS_LINE_2);
        assertThat(testPatientMaster.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testPatientMaster.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testPatientMaster.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testPatientMaster.getZip()).isEqualTo(DEFAULT_ZIP);
        assertThat(testPatientMaster.getContactNo1()).isEqualTo(DEFAULT_CONTACT_NO_1);
        assertThat(testPatientMaster.getContactNo2()).isEqualTo(UPDATED_CONTACT_NO_2);
        assertThat(testPatientMaster.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testPatientMaster.getEfax()).isEqualTo(DEFAULT_EFAX);
        assertThat(testPatientMaster.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testPatientMaster.getModeOfContact()).isEqualTo(DEFAULT_MODE_OF_CONTACT);
        assertThat(testPatientMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testPatientMaster.getBranchName()).isEqualTo(UPDATED_BRANCH_NAME);
        assertThat(testPatientMaster.getBillingAddressLine1()).isEqualTo(UPDATED_BILLING_ADDRESS_LINE_1);
        assertThat(testPatientMaster.getBillingAddressLine2()).isEqualTo(UPDATED_BILLING_ADDRESS_LINE_2);
        assertThat(testPatientMaster.getBillingAddressCity()).isEqualTo(UPDATED_BILLING_ADDRESS_CITY);
        assertThat(testPatientMaster.getBillingAddressState()).isEqualTo(DEFAULT_BILLING_ADDRESS_STATE);
        assertThat(testPatientMaster.getBillingAddressZip()).isEqualTo(UPDATED_BILLING_ADDRESS_ZIP);
        assertThat(testPatientMaster.getCaregiverName()).isEqualTo(UPDATED_CAREGIVER_NAME);
        assertThat(testPatientMaster.getCaregiverContact()).isEqualTo(UPDATED_CAREGIVER_CONTACT);
        assertThat(testPatientMaster.getCaregiverRelatinshipPatient()).isEqualTo(DEFAULT_CAREGIVER_RELATINSHIP_PATIENT);
        assertThat(testPatientMaster.getTaxZoneName()).isEqualTo(UPDATED_TAX_ZONE_NAME);
        assertThat(testPatientMaster.getTaxRate()).isEqualTo(UPDATED_TAX_RATE);
        assertThat(testPatientMaster.getPatientDod()).isEqualTo(UPDATED_PATIENT_DOD);
    }

    @Test
    void fullUpdatePatientMasterWithPatch() throws Exception {
        // Initialize the database
        patientMasterRepository.save(patientMaster).block();

        int databaseSizeBeforeUpdate = patientMasterRepository.findAll().collectList().block().size();

        // Update the patientMaster using partial update
        PatientMaster partialUpdatedPatientMaster = new PatientMaster();
        partialUpdatedPatientMaster.setPatientId(patientMaster.getPatientId());

        partialUpdatedPatientMaster
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientMiddleName(UPDATED_PATIENT_MIDDLE_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .dob(UPDATED_DOB)
            .gender(UPDATED_GENDER)
            .ssn(UPDATED_SSN)
            .taxZoneId(UPDATED_TAX_ZONE_ID)
            .discountPercent(UPDATED_DISCOUNT_PERCENT)
            .posId(UPDATED_POS_ID)
            .priorSystemKey(UPDATED_PRIOR_SYSTEM_KEY)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .branchId(UPDATED_BRANCH_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .patientMasterUUID(UPDATED_PATIENT_MASTER_UUID)
            .patientIdNumber(UPDATED_PATIENT_ID_NUMBER)
            .addressLine1(UPDATED_ADDRESS_LINE_1)
            .addressLine2(UPDATED_ADDRESS_LINE_2)
            .city(UPDATED_CITY)
            .state(UPDATED_STATE)
            .country(UPDATED_COUNTRY)
            .zip(UPDATED_ZIP)
            .contactNo1(UPDATED_CONTACT_NO_1)
            .contactNo2(UPDATED_CONTACT_NO_2)
            .fax(UPDATED_FAX)
            .efax(UPDATED_EFAX)
            .email(UPDATED_EMAIL)
            .modeOfContact(UPDATED_MODE_OF_CONTACT)
            .updatedDate(UPDATED_UPDATED_DATE)
            .branchName(UPDATED_BRANCH_NAME)
            .billingAddressLine1(UPDATED_BILLING_ADDRESS_LINE_1)
            .billingAddressLine2(UPDATED_BILLING_ADDRESS_LINE_2)
            .billingAddressCity(UPDATED_BILLING_ADDRESS_CITY)
            .billingAddressState(UPDATED_BILLING_ADDRESS_STATE)
            .billingAddressZip(UPDATED_BILLING_ADDRESS_ZIP)
            .caregiverName(UPDATED_CAREGIVER_NAME)
            .caregiverContact(UPDATED_CAREGIVER_CONTACT)
            .caregiverRelatinshipPatient(UPDATED_CAREGIVER_RELATINSHIP_PATIENT)
            .taxZoneName(UPDATED_TAX_ZONE_NAME)
            .taxRate(UPDATED_TAX_RATE)
            .patientDod(UPDATED_PATIENT_DOD);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPatientMaster.getPatientId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPatientMaster))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientMaster in the database
        List<PatientMaster> patientMasterList = patientMasterRepository.findAll().collectList().block();
        assertThat(patientMasterList).hasSize(databaseSizeBeforeUpdate);
        PatientMaster testPatientMaster = patientMasterList.get(patientMasterList.size() - 1);
        assertThat(testPatientMaster.getPatientFirstName()).isEqualTo(UPDATED_PATIENT_FIRST_NAME);
        assertThat(testPatientMaster.getPatientMiddleName()).isEqualTo(UPDATED_PATIENT_MIDDLE_NAME);
        assertThat(testPatientMaster.getPatientLastName()).isEqualTo(UPDATED_PATIENT_LAST_NAME);
        assertThat(testPatientMaster.getDob()).isEqualTo(UPDATED_DOB);
        assertThat(testPatientMaster.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testPatientMaster.getSsn()).isEqualTo(UPDATED_SSN);
        assertThat(testPatientMaster.getTaxZoneId()).isEqualTo(UPDATED_TAX_ZONE_ID);
        assertThat(testPatientMaster.getDiscountPercent()).isEqualTo(UPDATED_DISCOUNT_PERCENT);
        assertThat(testPatientMaster.getPosId()).isEqualTo(UPDATED_POS_ID);
        assertThat(testPatientMaster.getPriorSystemKey()).isEqualTo(UPDATED_PRIOR_SYSTEM_KEY);
        assertThat(testPatientMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPatientMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPatientMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPatientMaster.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
        assertThat(testPatientMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPatientMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPatientMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPatientMaster.getPatientMasterUUID()).isEqualTo(UPDATED_PATIENT_MASTER_UUID);
        assertThat(testPatientMaster.getPatientIdNumber()).isEqualTo(UPDATED_PATIENT_ID_NUMBER);
        assertThat(testPatientMaster.getAddressLine1()).isEqualTo(UPDATED_ADDRESS_LINE_1);
        assertThat(testPatientMaster.getAddressLine2()).isEqualTo(UPDATED_ADDRESS_LINE_2);
        assertThat(testPatientMaster.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testPatientMaster.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testPatientMaster.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testPatientMaster.getZip()).isEqualTo(UPDATED_ZIP);
        assertThat(testPatientMaster.getContactNo1()).isEqualTo(UPDATED_CONTACT_NO_1);
        assertThat(testPatientMaster.getContactNo2()).isEqualTo(UPDATED_CONTACT_NO_2);
        assertThat(testPatientMaster.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testPatientMaster.getEfax()).isEqualTo(UPDATED_EFAX);
        assertThat(testPatientMaster.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testPatientMaster.getModeOfContact()).isEqualTo(UPDATED_MODE_OF_CONTACT);
        assertThat(testPatientMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPatientMaster.getBranchName()).isEqualTo(UPDATED_BRANCH_NAME);
        assertThat(testPatientMaster.getBillingAddressLine1()).isEqualTo(UPDATED_BILLING_ADDRESS_LINE_1);
        assertThat(testPatientMaster.getBillingAddressLine2()).isEqualTo(UPDATED_BILLING_ADDRESS_LINE_2);
        assertThat(testPatientMaster.getBillingAddressCity()).isEqualTo(UPDATED_BILLING_ADDRESS_CITY);
        assertThat(testPatientMaster.getBillingAddressState()).isEqualTo(UPDATED_BILLING_ADDRESS_STATE);
        assertThat(testPatientMaster.getBillingAddressZip()).isEqualTo(UPDATED_BILLING_ADDRESS_ZIP);
        assertThat(testPatientMaster.getCaregiverName()).isEqualTo(UPDATED_CAREGIVER_NAME);
        assertThat(testPatientMaster.getCaregiverContact()).isEqualTo(UPDATED_CAREGIVER_CONTACT);
        assertThat(testPatientMaster.getCaregiverRelatinshipPatient()).isEqualTo(UPDATED_CAREGIVER_RELATINSHIP_PATIENT);
        assertThat(testPatientMaster.getTaxZoneName()).isEqualTo(UPDATED_TAX_ZONE_NAME);
        assertThat(testPatientMaster.getTaxRate()).isEqualTo(UPDATED_TAX_RATE);
        assertThat(testPatientMaster.getPatientDod()).isEqualTo(UPDATED_PATIENT_DOD);
    }

    @Test
    void patchNonExistingPatientMaster() throws Exception {
        int databaseSizeBeforeUpdate = patientMasterRepository.findAll().collectList().block().size();
        patientMaster.setPatientId(count.incrementAndGet());

        // Create the PatientMaster
        PatientMasterDTO patientMasterDTO = patientMasterMapper.toDto(patientMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, patientMasterDTO.getPatientId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientMaster in the database
        List<PatientMaster> patientMasterList = patientMasterRepository.findAll().collectList().block();
        assertThat(patientMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchPatientMaster() throws Exception {
        int databaseSizeBeforeUpdate = patientMasterRepository.findAll().collectList().block().size();
        patientMaster.setPatientId(count.incrementAndGet());

        // Create the PatientMaster
        PatientMasterDTO patientMasterDTO = patientMasterMapper.toDto(patientMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientMaster in the database
        List<PatientMaster> patientMasterList = patientMasterRepository.findAll().collectList().block();
        assertThat(patientMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamPatientMaster() throws Exception {
        int databaseSizeBeforeUpdate = patientMasterRepository.findAll().collectList().block().size();
        patientMaster.setPatientId(count.incrementAndGet());

        // Create the PatientMaster
        PatientMasterDTO patientMasterDTO = patientMasterMapper.toDto(patientMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientMasterDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PatientMaster in the database
        List<PatientMaster> patientMasterList = patientMasterRepository.findAll().collectList().block();
        assertThat(patientMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deletePatientMaster() {
        // Initialize the database
        patientMasterRepository.save(patientMaster).block();

        int databaseSizeBeforeDelete = patientMasterRepository.findAll().collectList().block().size();

        // Delete the patientMaster
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, patientMaster.getPatientId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<PatientMaster> patientMasterList = patientMasterRepository.findAll().collectList().block();
        assertThat(patientMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
