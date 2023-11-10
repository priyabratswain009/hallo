package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.PatientInsurance;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.PatientInsuranceRepository;
import com.sunknowledge.dme.rcm.service.dto.PatientInsuranceDTO;
import com.sunknowledge.dme.rcm.service.mapper.PatientInsuranceMapper;
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
 * Integration tests for the {@link PatientInsuranceResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class PatientInsuranceResourceIT {

    private static final Long DEFAULT_PATIENT_ID = 1L;
    private static final Long UPDATED_PATIENT_ID = 2L;

    private static final String DEFAULT_PAYER_LEVEL = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_LEVEL = "BBBBBBBBBB";

    private static final String DEFAULT_INSURANCE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INSURANCE_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_INSURANCE_ID = 1L;
    private static final Long UPDATED_INSURANCE_ID = 2L;

    private static final String DEFAULT_COVERAGE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_COVERAGE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_PAYER_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_CONTACT = "BBBBBBBBBB";

    private static final String DEFAULT_POLICY_NUM = "AAAAAAAAAA";
    private static final String UPDATED_POLICY_NUM = "BBBBBBBBBB";

    private static final String DEFAULT_POLICY_GROUP_NUM = "AAAAAAAAAA";
    private static final String UPDATED_POLICY_GROUP_NUM = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_POLICY_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_POLICY_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_POLICY_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_POLICY_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_PAY_PERCENTAGE = 1D;
    private static final Double UPDATED_PAY_PERCENTAGE = 2D;

    private static final Double DEFAULT_DEDUCTABLE_AMT = 1D;
    private static final Double UPDATED_DEDUCTABLE_AMT = 2D;

    private static final String DEFAULT_RELATIONSHIP = "AAAAAAAAAA";
    private static final String UPDATED_RELATIONSHIP = "BBBBBBBBBB";

    private static final String DEFAULT_INSURED_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INSURED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_INSURED_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INSURED_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_INSURED_SUFFIX = "AAAAAAAAAA";
    private static final String UPDATED_INSURED_SUFFIX = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_INSURED_DOB = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_INSURED_DOB = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_INSURED_SSN = "AAAAAAAAAA";
    private static final String UPDATED_INSURED_SSN = "BBBBBBBBBB";

    private static final String DEFAULT_INSURED_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_INSURED_GENDER = "BBBBBBBBBB";

    private static final String DEFAULT_ALWAYS_CROSSOVER_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_ALWAYS_CROSSOVER_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_CLAIM_CODES = "AAAAAAAAAA";
    private static final String UPDATED_CLAIM_CODES = "BBBBBBBBBB";

    private static final String DEFAULT_ADDTNL_CLAIM_INFO = "AAAAAAAAAA";
    private static final String UPDATED_ADDTNL_CLAIM_INFO = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_INSURED_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INSURED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final UUID DEFAULT_PATIENT_INSURANCE_UUID = UUID.randomUUID();
    private static final UUID UPDATED_PATIENT_INSURANCE_UUID = UUID.randomUUID();

    private static final String DEFAULT_MEMBER_ID = "AAAAAAAAAA";
    private static final String UPDATED_MEMBER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_RELATIONSHIP_INSURED = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_RELATIONSHIP_INSURED = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_CONDITION_EMPLOYMENT = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_CONDITION_EMPLOYMENT = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_CONDITION_AUTO_ACCIDENT = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_CONDITION_AUTO_ACCIDENT = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_CONDITION_OTHER_ACCIDENT = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_CONDITION_OTHER_ACCIDENT = "BBBBBBBBBB";

    private static final String DEFAULT_INSURANCE_PAYER_ID_NO = "AAAAAAAAAA";
    private static final String UPDATED_INSURANCE_PAYER_ID_NO = "BBBBBBBBBB";

    private static final String DEFAULT_EXPIRATION_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_EXPIRATION_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_INSURER_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_INSURER_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_INSURER_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_INSURER_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_INSURER_CITY = "AAAAAAAAAA";
    private static final String UPDATED_INSURER_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_INSURER_STATE = "AAAAAAAAAA";
    private static final String UPDATED_INSURER_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_INSURER_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_INSURER_ZIP = "BBBBBBBBBB";

    private static final String DEFAULT_INSURER_CONTACT_1 = "AAAAAAAAAA";
    private static final String UPDATED_INSURER_CONTACT_1 = "BBBBBBBBBB";

    private static final String DEFAULT_INSURER_FAX = "AAAAAAAAAA";
    private static final String UPDATED_INSURER_FAX = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/patient-insurances";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{patientInsuranceId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PatientInsuranceRepository patientInsuranceRepository;

    @Autowired
    private PatientInsuranceMapper patientInsuranceMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private PatientInsurance patientInsurance;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PatientInsurance createEntity(EntityManager em) {
        PatientInsurance patientInsurance = new PatientInsurance()
            .patientId(DEFAULT_PATIENT_ID)
            .payerLevel(DEFAULT_PAYER_LEVEL)
            .insuranceName(DEFAULT_INSURANCE_NAME)
            .insuranceId(DEFAULT_INSURANCE_ID)
            .coverageType(DEFAULT_COVERAGE_TYPE)
            .payerContact(DEFAULT_PAYER_CONTACT)
            .policyNum(DEFAULT_POLICY_NUM)
            .policyGroupNum(DEFAULT_POLICY_GROUP_NUM)
            .policyStartDate(DEFAULT_POLICY_START_DATE)
            .policyEndDate(DEFAULT_POLICY_END_DATE)
            .payPercentage(DEFAULT_PAY_PERCENTAGE)
            .deductableAmt(DEFAULT_DEDUCTABLE_AMT)
            .relationship(DEFAULT_RELATIONSHIP)
            .insuredFirstName(DEFAULT_INSURED_FIRST_NAME)
            .insuredMiddleName(DEFAULT_INSURED_MIDDLE_NAME)
            .insuredSuffix(DEFAULT_INSURED_SUFFIX)
            .insuredDob(DEFAULT_INSURED_DOB)
            .insuredSsn(DEFAULT_INSURED_SSN)
            .insuredGender(DEFAULT_INSURED_GENDER)
            .alwaysCrossoverStatus(DEFAULT_ALWAYS_CROSSOVER_STATUS)
            .claimCodes(DEFAULT_CLAIM_CODES)
            .addtnlClaimInfo(DEFAULT_ADDTNL_CLAIM_INFO)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .insuredLastName(DEFAULT_INSURED_LAST_NAME)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .patientInsuranceUuid(DEFAULT_PATIENT_INSURANCE_UUID)
            .memberId(DEFAULT_MEMBER_ID)
            .patientRelationshipInsured(DEFAULT_PATIENT_RELATIONSHIP_INSURED)
            .patientConditionEmployment(DEFAULT_PATIENT_CONDITION_EMPLOYMENT)
            .patientConditionAutoAccident(DEFAULT_PATIENT_CONDITION_AUTO_ACCIDENT)
            .patientConditionOtherAccident(DEFAULT_PATIENT_CONDITION_OTHER_ACCIDENT)
            .insurancePayerIdNo(DEFAULT_INSURANCE_PAYER_ID_NO)
            .expirationStatus(DEFAULT_EXPIRATION_STATUS)
            .insurerAddressLine1(DEFAULT_INSURER_ADDRESS_LINE_1)
            .insurerAddressLine2(DEFAULT_INSURER_ADDRESS_LINE_2)
            .insurerCity(DEFAULT_INSURER_CITY)
            .insurerState(DEFAULT_INSURER_STATE)
            .insurerZip(DEFAULT_INSURER_ZIP)
            .insurerContact1(DEFAULT_INSURER_CONTACT_1)
            .insurerFax(DEFAULT_INSURER_FAX);
        return patientInsurance;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PatientInsurance createUpdatedEntity(EntityManager em) {
        PatientInsurance patientInsurance = new PatientInsurance()
            .patientId(UPDATED_PATIENT_ID)
            .payerLevel(UPDATED_PAYER_LEVEL)
            .insuranceName(UPDATED_INSURANCE_NAME)
            .insuranceId(UPDATED_INSURANCE_ID)
            .coverageType(UPDATED_COVERAGE_TYPE)
            .payerContact(UPDATED_PAYER_CONTACT)
            .policyNum(UPDATED_POLICY_NUM)
            .policyGroupNum(UPDATED_POLICY_GROUP_NUM)
            .policyStartDate(UPDATED_POLICY_START_DATE)
            .policyEndDate(UPDATED_POLICY_END_DATE)
            .payPercentage(UPDATED_PAY_PERCENTAGE)
            .deductableAmt(UPDATED_DEDUCTABLE_AMT)
            .relationship(UPDATED_RELATIONSHIP)
            .insuredFirstName(UPDATED_INSURED_FIRST_NAME)
            .insuredMiddleName(UPDATED_INSURED_MIDDLE_NAME)
            .insuredSuffix(UPDATED_INSURED_SUFFIX)
            .insuredDob(UPDATED_INSURED_DOB)
            .insuredSsn(UPDATED_INSURED_SSN)
            .insuredGender(UPDATED_INSURED_GENDER)
            .alwaysCrossoverStatus(UPDATED_ALWAYS_CROSSOVER_STATUS)
            .claimCodes(UPDATED_CLAIM_CODES)
            .addtnlClaimInfo(UPDATED_ADDTNL_CLAIM_INFO)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .insuredLastName(UPDATED_INSURED_LAST_NAME)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .patientInsuranceUuid(UPDATED_PATIENT_INSURANCE_UUID)
            .memberId(UPDATED_MEMBER_ID)
            .patientRelationshipInsured(UPDATED_PATIENT_RELATIONSHIP_INSURED)
            .patientConditionEmployment(UPDATED_PATIENT_CONDITION_EMPLOYMENT)
            .patientConditionAutoAccident(UPDATED_PATIENT_CONDITION_AUTO_ACCIDENT)
            .patientConditionOtherAccident(UPDATED_PATIENT_CONDITION_OTHER_ACCIDENT)
            .insurancePayerIdNo(UPDATED_INSURANCE_PAYER_ID_NO)
            .expirationStatus(UPDATED_EXPIRATION_STATUS)
            .insurerAddressLine1(UPDATED_INSURER_ADDRESS_LINE_1)
            .insurerAddressLine2(UPDATED_INSURER_ADDRESS_LINE_2)
            .insurerCity(UPDATED_INSURER_CITY)
            .insurerState(UPDATED_INSURER_STATE)
            .insurerZip(UPDATED_INSURER_ZIP)
            .insurerContact1(UPDATED_INSURER_CONTACT_1)
            .insurerFax(UPDATED_INSURER_FAX);
        return patientInsurance;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(PatientInsurance.class).block();
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
        patientInsurance = createEntity(em);
    }

    @Test
    void createPatientInsurance() throws Exception {
        int databaseSizeBeforeCreate = patientInsuranceRepository.findAll().collectList().block().size();
        // Create the PatientInsurance
        PatientInsuranceDTO patientInsuranceDTO = patientInsuranceMapper.toDto(patientInsurance);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientInsuranceDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the PatientInsurance in the database
        List<PatientInsurance> patientInsuranceList = patientInsuranceRepository.findAll().collectList().block();
        assertThat(patientInsuranceList).hasSize(databaseSizeBeforeCreate + 1);
        PatientInsurance testPatientInsurance = patientInsuranceList.get(patientInsuranceList.size() - 1);
        assertThat(testPatientInsurance.getPatientId()).isEqualTo(DEFAULT_PATIENT_ID);
        assertThat(testPatientInsurance.getPayerLevel()).isEqualTo(DEFAULT_PAYER_LEVEL);
        assertThat(testPatientInsurance.getInsuranceName()).isEqualTo(DEFAULT_INSURANCE_NAME);
        assertThat(testPatientInsurance.getInsuranceId()).isEqualTo(DEFAULT_INSURANCE_ID);
        assertThat(testPatientInsurance.getCoverageType()).isEqualTo(DEFAULT_COVERAGE_TYPE);
        assertThat(testPatientInsurance.getPayerContact()).isEqualTo(DEFAULT_PAYER_CONTACT);
        assertThat(testPatientInsurance.getPolicyNum()).isEqualTo(DEFAULT_POLICY_NUM);
        assertThat(testPatientInsurance.getPolicyGroupNum()).isEqualTo(DEFAULT_POLICY_GROUP_NUM);
        assertThat(testPatientInsurance.getPolicyStartDate()).isEqualTo(DEFAULT_POLICY_START_DATE);
        assertThat(testPatientInsurance.getPolicyEndDate()).isEqualTo(DEFAULT_POLICY_END_DATE);
        assertThat(testPatientInsurance.getPayPercentage()).isEqualTo(DEFAULT_PAY_PERCENTAGE);
        assertThat(testPatientInsurance.getDeductableAmt()).isEqualTo(DEFAULT_DEDUCTABLE_AMT);
        assertThat(testPatientInsurance.getRelationship()).isEqualTo(DEFAULT_RELATIONSHIP);
        assertThat(testPatientInsurance.getInsuredFirstName()).isEqualTo(DEFAULT_INSURED_FIRST_NAME);
        assertThat(testPatientInsurance.getInsuredMiddleName()).isEqualTo(DEFAULT_INSURED_MIDDLE_NAME);
        assertThat(testPatientInsurance.getInsuredSuffix()).isEqualTo(DEFAULT_INSURED_SUFFIX);
        assertThat(testPatientInsurance.getInsuredDob()).isEqualTo(DEFAULT_INSURED_DOB);
        assertThat(testPatientInsurance.getInsuredSsn()).isEqualTo(DEFAULT_INSURED_SSN);
        assertThat(testPatientInsurance.getInsuredGender()).isEqualTo(DEFAULT_INSURED_GENDER);
        assertThat(testPatientInsurance.getAlwaysCrossoverStatus()).isEqualTo(DEFAULT_ALWAYS_CROSSOVER_STATUS);
        assertThat(testPatientInsurance.getClaimCodes()).isEqualTo(DEFAULT_CLAIM_CODES);
        assertThat(testPatientInsurance.getAddtnlClaimInfo()).isEqualTo(DEFAULT_ADDTNL_CLAIM_INFO);
        assertThat(testPatientInsurance.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPatientInsurance.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testPatientInsurance.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testPatientInsurance.getInsuredLastName()).isEqualTo(DEFAULT_INSURED_LAST_NAME);
        assertThat(testPatientInsurance.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testPatientInsurance.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testPatientInsurance.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testPatientInsurance.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testPatientInsurance.getPatientInsuranceUuid()).isEqualTo(DEFAULT_PATIENT_INSURANCE_UUID);
        assertThat(testPatientInsurance.getMemberId()).isEqualTo(DEFAULT_MEMBER_ID);
        assertThat(testPatientInsurance.getPatientRelationshipInsured()).isEqualTo(DEFAULT_PATIENT_RELATIONSHIP_INSURED);
        assertThat(testPatientInsurance.getPatientConditionEmployment()).isEqualTo(DEFAULT_PATIENT_CONDITION_EMPLOYMENT);
        assertThat(testPatientInsurance.getPatientConditionAutoAccident()).isEqualTo(DEFAULT_PATIENT_CONDITION_AUTO_ACCIDENT);
        assertThat(testPatientInsurance.getPatientConditionOtherAccident()).isEqualTo(DEFAULT_PATIENT_CONDITION_OTHER_ACCIDENT);
        assertThat(testPatientInsurance.getInsurancePayerIdNo()).isEqualTo(DEFAULT_INSURANCE_PAYER_ID_NO);
        assertThat(testPatientInsurance.getExpirationStatus()).isEqualTo(DEFAULT_EXPIRATION_STATUS);
        assertThat(testPatientInsurance.getInsurerAddressLine1()).isEqualTo(DEFAULT_INSURER_ADDRESS_LINE_1);
        assertThat(testPatientInsurance.getInsurerAddressLine2()).isEqualTo(DEFAULT_INSURER_ADDRESS_LINE_2);
        assertThat(testPatientInsurance.getInsurerCity()).isEqualTo(DEFAULT_INSURER_CITY);
        assertThat(testPatientInsurance.getInsurerState()).isEqualTo(DEFAULT_INSURER_STATE);
        assertThat(testPatientInsurance.getInsurerZip()).isEqualTo(DEFAULT_INSURER_ZIP);
        assertThat(testPatientInsurance.getInsurerContact1()).isEqualTo(DEFAULT_INSURER_CONTACT_1);
        assertThat(testPatientInsurance.getInsurerFax()).isEqualTo(DEFAULT_INSURER_FAX);
    }

    @Test
    void createPatientInsuranceWithExistingId() throws Exception {
        // Create the PatientInsurance with an existing ID
        patientInsurance.setPatientInsuranceId(1L);
        PatientInsuranceDTO patientInsuranceDTO = patientInsuranceMapper.toDto(patientInsurance);

        int databaseSizeBeforeCreate = patientInsuranceRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientInsuranceDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientInsurance in the database
        List<PatientInsurance> patientInsuranceList = patientInsuranceRepository.findAll().collectList().block();
        assertThat(patientInsuranceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllPatientInsurances() {
        // Initialize the database
        patientInsuranceRepository.save(patientInsurance).block();

        // Get all the patientInsuranceList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=patientInsuranceId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].patientInsuranceId")
            .value(hasItem(patientInsurance.getPatientInsuranceId().intValue()))
            .jsonPath("$.[*].patientId")
            .value(hasItem(DEFAULT_PATIENT_ID.intValue()))
            .jsonPath("$.[*].payerLevel")
            .value(hasItem(DEFAULT_PAYER_LEVEL))
            .jsonPath("$.[*].insuranceName")
            .value(hasItem(DEFAULT_INSURANCE_NAME))
            .jsonPath("$.[*].insuranceId")
            .value(hasItem(DEFAULT_INSURANCE_ID.intValue()))
            .jsonPath("$.[*].coverageType")
            .value(hasItem(DEFAULT_COVERAGE_TYPE))
            .jsonPath("$.[*].payerContact")
            .value(hasItem(DEFAULT_PAYER_CONTACT))
            .jsonPath("$.[*].policyNum")
            .value(hasItem(DEFAULT_POLICY_NUM))
            .jsonPath("$.[*].policyGroupNum")
            .value(hasItem(DEFAULT_POLICY_GROUP_NUM))
            .jsonPath("$.[*].policyStartDate")
            .value(hasItem(DEFAULT_POLICY_START_DATE.toString()))
            .jsonPath("$.[*].policyEndDate")
            .value(hasItem(DEFAULT_POLICY_END_DATE.toString()))
            .jsonPath("$.[*].payPercentage")
            .value(hasItem(DEFAULT_PAY_PERCENTAGE.doubleValue()))
            .jsonPath("$.[*].deductableAmt")
            .value(hasItem(DEFAULT_DEDUCTABLE_AMT.doubleValue()))
            .jsonPath("$.[*].relationship")
            .value(hasItem(DEFAULT_RELATIONSHIP))
            .jsonPath("$.[*].insuredFirstName")
            .value(hasItem(DEFAULT_INSURED_FIRST_NAME))
            .jsonPath("$.[*].insuredMiddleName")
            .value(hasItem(DEFAULT_INSURED_MIDDLE_NAME))
            .jsonPath("$.[*].insuredSuffix")
            .value(hasItem(DEFAULT_INSURED_SUFFIX))
            .jsonPath("$.[*].insuredDob")
            .value(hasItem(DEFAULT_INSURED_DOB.toString()))
            .jsonPath("$.[*].insuredSsn")
            .value(hasItem(DEFAULT_INSURED_SSN))
            .jsonPath("$.[*].insuredGender")
            .value(hasItem(DEFAULT_INSURED_GENDER))
            .jsonPath("$.[*].alwaysCrossoverStatus")
            .value(hasItem(DEFAULT_ALWAYS_CROSSOVER_STATUS))
            .jsonPath("$.[*].claimCodes")
            .value(hasItem(DEFAULT_CLAIM_CODES))
            .jsonPath("$.[*].addtnlClaimInfo")
            .value(hasItem(DEFAULT_ADDTNL_CLAIM_INFO))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS))
            .jsonPath("$.[*].createdById")
            .value(hasItem(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.[*].createdDate")
            .value(hasItem(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.[*].insuredLastName")
            .value(hasItem(DEFAULT_INSURED_LAST_NAME))
            .jsonPath("$.[*].createdByName")
            .value(hasItem(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.[*].updatedByName")
            .value(hasItem(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.[*].updatedById")
            .value(hasItem(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.[*].updatedDate")
            .value(hasItem(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.[*].patientInsuranceUuid")
            .value(hasItem(DEFAULT_PATIENT_INSURANCE_UUID.toString()))
            .jsonPath("$.[*].memberId")
            .value(hasItem(DEFAULT_MEMBER_ID))
            .jsonPath("$.[*].patientRelationshipInsured")
            .value(hasItem(DEFAULT_PATIENT_RELATIONSHIP_INSURED))
            .jsonPath("$.[*].patientConditionEmployment")
            .value(hasItem(DEFAULT_PATIENT_CONDITION_EMPLOYMENT))
            .jsonPath("$.[*].patientConditionAutoAccident")
            .value(hasItem(DEFAULT_PATIENT_CONDITION_AUTO_ACCIDENT))
            .jsonPath("$.[*].patientConditionOtherAccident")
            .value(hasItem(DEFAULT_PATIENT_CONDITION_OTHER_ACCIDENT))
            .jsonPath("$.[*].insurancePayerIdNo")
            .value(hasItem(DEFAULT_INSURANCE_PAYER_ID_NO))
            .jsonPath("$.[*].expirationStatus")
            .value(hasItem(DEFAULT_EXPIRATION_STATUS))
            .jsonPath("$.[*].insurerAddressLine1")
            .value(hasItem(DEFAULT_INSURER_ADDRESS_LINE_1))
            .jsonPath("$.[*].insurerAddressLine2")
            .value(hasItem(DEFAULT_INSURER_ADDRESS_LINE_2))
            .jsonPath("$.[*].insurerCity")
            .value(hasItem(DEFAULT_INSURER_CITY))
            .jsonPath("$.[*].insurerState")
            .value(hasItem(DEFAULT_INSURER_STATE))
            .jsonPath("$.[*].insurerZip")
            .value(hasItem(DEFAULT_INSURER_ZIP))
            .jsonPath("$.[*].insurerContact1")
            .value(hasItem(DEFAULT_INSURER_CONTACT_1))
            .jsonPath("$.[*].insurerFax")
            .value(hasItem(DEFAULT_INSURER_FAX));
    }

    @Test
    void getPatientInsurance() {
        // Initialize the database
        patientInsuranceRepository.save(patientInsurance).block();

        // Get the patientInsurance
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, patientInsurance.getPatientInsuranceId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.patientInsuranceId")
            .value(is(patientInsurance.getPatientInsuranceId().intValue()))
            .jsonPath("$.patientId")
            .value(is(DEFAULT_PATIENT_ID.intValue()))
            .jsonPath("$.payerLevel")
            .value(is(DEFAULT_PAYER_LEVEL))
            .jsonPath("$.insuranceName")
            .value(is(DEFAULT_INSURANCE_NAME))
            .jsonPath("$.insuranceId")
            .value(is(DEFAULT_INSURANCE_ID.intValue()))
            .jsonPath("$.coverageType")
            .value(is(DEFAULT_COVERAGE_TYPE))
            .jsonPath("$.payerContact")
            .value(is(DEFAULT_PAYER_CONTACT))
            .jsonPath("$.policyNum")
            .value(is(DEFAULT_POLICY_NUM))
            .jsonPath("$.policyGroupNum")
            .value(is(DEFAULT_POLICY_GROUP_NUM))
            .jsonPath("$.policyStartDate")
            .value(is(DEFAULT_POLICY_START_DATE.toString()))
            .jsonPath("$.policyEndDate")
            .value(is(DEFAULT_POLICY_END_DATE.toString()))
            .jsonPath("$.payPercentage")
            .value(is(DEFAULT_PAY_PERCENTAGE.doubleValue()))
            .jsonPath("$.deductableAmt")
            .value(is(DEFAULT_DEDUCTABLE_AMT.doubleValue()))
            .jsonPath("$.relationship")
            .value(is(DEFAULT_RELATIONSHIP))
            .jsonPath("$.insuredFirstName")
            .value(is(DEFAULT_INSURED_FIRST_NAME))
            .jsonPath("$.insuredMiddleName")
            .value(is(DEFAULT_INSURED_MIDDLE_NAME))
            .jsonPath("$.insuredSuffix")
            .value(is(DEFAULT_INSURED_SUFFIX))
            .jsonPath("$.insuredDob")
            .value(is(DEFAULT_INSURED_DOB.toString()))
            .jsonPath("$.insuredSsn")
            .value(is(DEFAULT_INSURED_SSN))
            .jsonPath("$.insuredGender")
            .value(is(DEFAULT_INSURED_GENDER))
            .jsonPath("$.alwaysCrossoverStatus")
            .value(is(DEFAULT_ALWAYS_CROSSOVER_STATUS))
            .jsonPath("$.claimCodes")
            .value(is(DEFAULT_CLAIM_CODES))
            .jsonPath("$.addtnlClaimInfo")
            .value(is(DEFAULT_ADDTNL_CLAIM_INFO))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS))
            .jsonPath("$.createdById")
            .value(is(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.createdDate")
            .value(is(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.insuredLastName")
            .value(is(DEFAULT_INSURED_LAST_NAME))
            .jsonPath("$.createdByName")
            .value(is(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.updatedByName")
            .value(is(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.updatedById")
            .value(is(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.updatedDate")
            .value(is(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.patientInsuranceUuid")
            .value(is(DEFAULT_PATIENT_INSURANCE_UUID.toString()))
            .jsonPath("$.memberId")
            .value(is(DEFAULT_MEMBER_ID))
            .jsonPath("$.patientRelationshipInsured")
            .value(is(DEFAULT_PATIENT_RELATIONSHIP_INSURED))
            .jsonPath("$.patientConditionEmployment")
            .value(is(DEFAULT_PATIENT_CONDITION_EMPLOYMENT))
            .jsonPath("$.patientConditionAutoAccident")
            .value(is(DEFAULT_PATIENT_CONDITION_AUTO_ACCIDENT))
            .jsonPath("$.patientConditionOtherAccident")
            .value(is(DEFAULT_PATIENT_CONDITION_OTHER_ACCIDENT))
            .jsonPath("$.insurancePayerIdNo")
            .value(is(DEFAULT_INSURANCE_PAYER_ID_NO))
            .jsonPath("$.expirationStatus")
            .value(is(DEFAULT_EXPIRATION_STATUS))
            .jsonPath("$.insurerAddressLine1")
            .value(is(DEFAULT_INSURER_ADDRESS_LINE_1))
            .jsonPath("$.insurerAddressLine2")
            .value(is(DEFAULT_INSURER_ADDRESS_LINE_2))
            .jsonPath("$.insurerCity")
            .value(is(DEFAULT_INSURER_CITY))
            .jsonPath("$.insurerState")
            .value(is(DEFAULT_INSURER_STATE))
            .jsonPath("$.insurerZip")
            .value(is(DEFAULT_INSURER_ZIP))
            .jsonPath("$.insurerContact1")
            .value(is(DEFAULT_INSURER_CONTACT_1))
            .jsonPath("$.insurerFax")
            .value(is(DEFAULT_INSURER_FAX));
    }

    @Test
    void getNonExistingPatientInsurance() {
        // Get the patientInsurance
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingPatientInsurance() throws Exception {
        // Initialize the database
        patientInsuranceRepository.save(patientInsurance).block();

        int databaseSizeBeforeUpdate = patientInsuranceRepository.findAll().collectList().block().size();

        // Update the patientInsurance
        PatientInsurance updatedPatientInsurance = patientInsuranceRepository.findById(patientInsurance.getPatientInsuranceId()).block();
        updatedPatientInsurance
            .patientId(UPDATED_PATIENT_ID)
            .payerLevel(UPDATED_PAYER_LEVEL)
            .insuranceName(UPDATED_INSURANCE_NAME)
            .insuranceId(UPDATED_INSURANCE_ID)
            .coverageType(UPDATED_COVERAGE_TYPE)
            .payerContact(UPDATED_PAYER_CONTACT)
            .policyNum(UPDATED_POLICY_NUM)
            .policyGroupNum(UPDATED_POLICY_GROUP_NUM)
            .policyStartDate(UPDATED_POLICY_START_DATE)
            .policyEndDate(UPDATED_POLICY_END_DATE)
            .payPercentage(UPDATED_PAY_PERCENTAGE)
            .deductableAmt(UPDATED_DEDUCTABLE_AMT)
            .relationship(UPDATED_RELATIONSHIP)
            .insuredFirstName(UPDATED_INSURED_FIRST_NAME)
            .insuredMiddleName(UPDATED_INSURED_MIDDLE_NAME)
            .insuredSuffix(UPDATED_INSURED_SUFFIX)
            .insuredDob(UPDATED_INSURED_DOB)
            .insuredSsn(UPDATED_INSURED_SSN)
            .insuredGender(UPDATED_INSURED_GENDER)
            .alwaysCrossoverStatus(UPDATED_ALWAYS_CROSSOVER_STATUS)
            .claimCodes(UPDATED_CLAIM_CODES)
            .addtnlClaimInfo(UPDATED_ADDTNL_CLAIM_INFO)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .insuredLastName(UPDATED_INSURED_LAST_NAME)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .patientInsuranceUuid(UPDATED_PATIENT_INSURANCE_UUID)
            .memberId(UPDATED_MEMBER_ID)
            .patientRelationshipInsured(UPDATED_PATIENT_RELATIONSHIP_INSURED)
            .patientConditionEmployment(UPDATED_PATIENT_CONDITION_EMPLOYMENT)
            .patientConditionAutoAccident(UPDATED_PATIENT_CONDITION_AUTO_ACCIDENT)
            .patientConditionOtherAccident(UPDATED_PATIENT_CONDITION_OTHER_ACCIDENT)
            .insurancePayerIdNo(UPDATED_INSURANCE_PAYER_ID_NO)
            .expirationStatus(UPDATED_EXPIRATION_STATUS)
            .insurerAddressLine1(UPDATED_INSURER_ADDRESS_LINE_1)
            .insurerAddressLine2(UPDATED_INSURER_ADDRESS_LINE_2)
            .insurerCity(UPDATED_INSURER_CITY)
            .insurerState(UPDATED_INSURER_STATE)
            .insurerZip(UPDATED_INSURER_ZIP)
            .insurerContact1(UPDATED_INSURER_CONTACT_1)
            .insurerFax(UPDATED_INSURER_FAX);
        PatientInsuranceDTO patientInsuranceDTO = patientInsuranceMapper.toDto(updatedPatientInsurance);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, patientInsuranceDTO.getPatientInsuranceId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientInsuranceDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientInsurance in the database
        List<PatientInsurance> patientInsuranceList = patientInsuranceRepository.findAll().collectList().block();
        assertThat(patientInsuranceList).hasSize(databaseSizeBeforeUpdate);
        PatientInsurance testPatientInsurance = patientInsuranceList.get(patientInsuranceList.size() - 1);
        assertThat(testPatientInsurance.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testPatientInsurance.getPayerLevel()).isEqualTo(UPDATED_PAYER_LEVEL);
        assertThat(testPatientInsurance.getInsuranceName()).isEqualTo(UPDATED_INSURANCE_NAME);
        assertThat(testPatientInsurance.getInsuranceId()).isEqualTo(UPDATED_INSURANCE_ID);
        assertThat(testPatientInsurance.getCoverageType()).isEqualTo(UPDATED_COVERAGE_TYPE);
        assertThat(testPatientInsurance.getPayerContact()).isEqualTo(UPDATED_PAYER_CONTACT);
        assertThat(testPatientInsurance.getPolicyNum()).isEqualTo(UPDATED_POLICY_NUM);
        assertThat(testPatientInsurance.getPolicyGroupNum()).isEqualTo(UPDATED_POLICY_GROUP_NUM);
        assertThat(testPatientInsurance.getPolicyStartDate()).isEqualTo(UPDATED_POLICY_START_DATE);
        assertThat(testPatientInsurance.getPolicyEndDate()).isEqualTo(UPDATED_POLICY_END_DATE);
        assertThat(testPatientInsurance.getPayPercentage()).isEqualTo(UPDATED_PAY_PERCENTAGE);
        assertThat(testPatientInsurance.getDeductableAmt()).isEqualTo(UPDATED_DEDUCTABLE_AMT);
        assertThat(testPatientInsurance.getRelationship()).isEqualTo(UPDATED_RELATIONSHIP);
        assertThat(testPatientInsurance.getInsuredFirstName()).isEqualTo(UPDATED_INSURED_FIRST_NAME);
        assertThat(testPatientInsurance.getInsuredMiddleName()).isEqualTo(UPDATED_INSURED_MIDDLE_NAME);
        assertThat(testPatientInsurance.getInsuredSuffix()).isEqualTo(UPDATED_INSURED_SUFFIX);
        assertThat(testPatientInsurance.getInsuredDob()).isEqualTo(UPDATED_INSURED_DOB);
        assertThat(testPatientInsurance.getInsuredSsn()).isEqualTo(UPDATED_INSURED_SSN);
        assertThat(testPatientInsurance.getInsuredGender()).isEqualTo(UPDATED_INSURED_GENDER);
        assertThat(testPatientInsurance.getAlwaysCrossoverStatus()).isEqualTo(UPDATED_ALWAYS_CROSSOVER_STATUS);
        assertThat(testPatientInsurance.getClaimCodes()).isEqualTo(UPDATED_CLAIM_CODES);
        assertThat(testPatientInsurance.getAddtnlClaimInfo()).isEqualTo(UPDATED_ADDTNL_CLAIM_INFO);
        assertThat(testPatientInsurance.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPatientInsurance.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPatientInsurance.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPatientInsurance.getInsuredLastName()).isEqualTo(UPDATED_INSURED_LAST_NAME);
        assertThat(testPatientInsurance.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPatientInsurance.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPatientInsurance.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPatientInsurance.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPatientInsurance.getPatientInsuranceUuid()).isEqualTo(UPDATED_PATIENT_INSURANCE_UUID);
        assertThat(testPatientInsurance.getMemberId()).isEqualTo(UPDATED_MEMBER_ID);
        assertThat(testPatientInsurance.getPatientRelationshipInsured()).isEqualTo(UPDATED_PATIENT_RELATIONSHIP_INSURED);
        assertThat(testPatientInsurance.getPatientConditionEmployment()).isEqualTo(UPDATED_PATIENT_CONDITION_EMPLOYMENT);
        assertThat(testPatientInsurance.getPatientConditionAutoAccident()).isEqualTo(UPDATED_PATIENT_CONDITION_AUTO_ACCIDENT);
        assertThat(testPatientInsurance.getPatientConditionOtherAccident()).isEqualTo(UPDATED_PATIENT_CONDITION_OTHER_ACCIDENT);
        assertThat(testPatientInsurance.getInsurancePayerIdNo()).isEqualTo(UPDATED_INSURANCE_PAYER_ID_NO);
        assertThat(testPatientInsurance.getExpirationStatus()).isEqualTo(UPDATED_EXPIRATION_STATUS);
        assertThat(testPatientInsurance.getInsurerAddressLine1()).isEqualTo(UPDATED_INSURER_ADDRESS_LINE_1);
        assertThat(testPatientInsurance.getInsurerAddressLine2()).isEqualTo(UPDATED_INSURER_ADDRESS_LINE_2);
        assertThat(testPatientInsurance.getInsurerCity()).isEqualTo(UPDATED_INSURER_CITY);
        assertThat(testPatientInsurance.getInsurerState()).isEqualTo(UPDATED_INSURER_STATE);
        assertThat(testPatientInsurance.getInsurerZip()).isEqualTo(UPDATED_INSURER_ZIP);
        assertThat(testPatientInsurance.getInsurerContact1()).isEqualTo(UPDATED_INSURER_CONTACT_1);
        assertThat(testPatientInsurance.getInsurerFax()).isEqualTo(UPDATED_INSURER_FAX);
    }

    @Test
    void putNonExistingPatientInsurance() throws Exception {
        int databaseSizeBeforeUpdate = patientInsuranceRepository.findAll().collectList().block().size();
        patientInsurance.setPatientInsuranceId(count.incrementAndGet());

        // Create the PatientInsurance
        PatientInsuranceDTO patientInsuranceDTO = patientInsuranceMapper.toDto(patientInsurance);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, patientInsuranceDTO.getPatientInsuranceId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientInsuranceDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientInsurance in the database
        List<PatientInsurance> patientInsuranceList = patientInsuranceRepository.findAll().collectList().block();
        assertThat(patientInsuranceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchPatientInsurance() throws Exception {
        int databaseSizeBeforeUpdate = patientInsuranceRepository.findAll().collectList().block().size();
        patientInsurance.setPatientInsuranceId(count.incrementAndGet());

        // Create the PatientInsurance
        PatientInsuranceDTO patientInsuranceDTO = patientInsuranceMapper.toDto(patientInsurance);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientInsuranceDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientInsurance in the database
        List<PatientInsurance> patientInsuranceList = patientInsuranceRepository.findAll().collectList().block();
        assertThat(patientInsuranceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamPatientInsurance() throws Exception {
        int databaseSizeBeforeUpdate = patientInsuranceRepository.findAll().collectList().block().size();
        patientInsurance.setPatientInsuranceId(count.incrementAndGet());

        // Create the PatientInsurance
        PatientInsuranceDTO patientInsuranceDTO = patientInsuranceMapper.toDto(patientInsurance);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientInsuranceDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PatientInsurance in the database
        List<PatientInsurance> patientInsuranceList = patientInsuranceRepository.findAll().collectList().block();
        assertThat(patientInsuranceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdatePatientInsuranceWithPatch() throws Exception {
        // Initialize the database
        patientInsuranceRepository.save(patientInsurance).block();

        int databaseSizeBeforeUpdate = patientInsuranceRepository.findAll().collectList().block().size();

        // Update the patientInsurance using partial update
        PatientInsurance partialUpdatedPatientInsurance = new PatientInsurance();
        partialUpdatedPatientInsurance.setPatientInsuranceId(patientInsurance.getPatientInsuranceId());

        partialUpdatedPatientInsurance
            .patientId(UPDATED_PATIENT_ID)
            .payerLevel(UPDATED_PAYER_LEVEL)
            .insuranceId(UPDATED_INSURANCE_ID)
            .policyGroupNum(UPDATED_POLICY_GROUP_NUM)
            .policyEndDate(UPDATED_POLICY_END_DATE)
            .payPercentage(UPDATED_PAY_PERCENTAGE)
            .insuredDob(UPDATED_INSURED_DOB)
            .insuredSsn(UPDATED_INSURED_SSN)
            .insuredGender(UPDATED_INSURED_GENDER)
            .addtnlClaimInfo(UPDATED_ADDTNL_CLAIM_INFO)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .patientInsuranceUuid(UPDATED_PATIENT_INSURANCE_UUID)
            .memberId(UPDATED_MEMBER_ID)
            .patientRelationshipInsured(UPDATED_PATIENT_RELATIONSHIP_INSURED)
            .patientConditionEmployment(UPDATED_PATIENT_CONDITION_EMPLOYMENT)
            .patientConditionAutoAccident(UPDATED_PATIENT_CONDITION_AUTO_ACCIDENT)
            .patientConditionOtherAccident(UPDATED_PATIENT_CONDITION_OTHER_ACCIDENT)
            .expirationStatus(UPDATED_EXPIRATION_STATUS)
            .insurerAddressLine2(UPDATED_INSURER_ADDRESS_LINE_2)
            .insurerCity(UPDATED_INSURER_CITY)
            .insurerState(UPDATED_INSURER_STATE)
            .insurerContact1(UPDATED_INSURER_CONTACT_1);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPatientInsurance.getPatientInsuranceId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPatientInsurance))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientInsurance in the database
        List<PatientInsurance> patientInsuranceList = patientInsuranceRepository.findAll().collectList().block();
        assertThat(patientInsuranceList).hasSize(databaseSizeBeforeUpdate);
        PatientInsurance testPatientInsurance = patientInsuranceList.get(patientInsuranceList.size() - 1);
        assertThat(testPatientInsurance.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testPatientInsurance.getPayerLevel()).isEqualTo(UPDATED_PAYER_LEVEL);
        assertThat(testPatientInsurance.getInsuranceName()).isEqualTo(DEFAULT_INSURANCE_NAME);
        assertThat(testPatientInsurance.getInsuranceId()).isEqualTo(UPDATED_INSURANCE_ID);
        assertThat(testPatientInsurance.getCoverageType()).isEqualTo(DEFAULT_COVERAGE_TYPE);
        assertThat(testPatientInsurance.getPayerContact()).isEqualTo(DEFAULT_PAYER_CONTACT);
        assertThat(testPatientInsurance.getPolicyNum()).isEqualTo(DEFAULT_POLICY_NUM);
        assertThat(testPatientInsurance.getPolicyGroupNum()).isEqualTo(UPDATED_POLICY_GROUP_NUM);
        assertThat(testPatientInsurance.getPolicyStartDate()).isEqualTo(DEFAULT_POLICY_START_DATE);
        assertThat(testPatientInsurance.getPolicyEndDate()).isEqualTo(UPDATED_POLICY_END_DATE);
        assertThat(testPatientInsurance.getPayPercentage()).isEqualTo(UPDATED_PAY_PERCENTAGE);
        assertThat(testPatientInsurance.getDeductableAmt()).isEqualTo(DEFAULT_DEDUCTABLE_AMT);
        assertThat(testPatientInsurance.getRelationship()).isEqualTo(DEFAULT_RELATIONSHIP);
        assertThat(testPatientInsurance.getInsuredFirstName()).isEqualTo(DEFAULT_INSURED_FIRST_NAME);
        assertThat(testPatientInsurance.getInsuredMiddleName()).isEqualTo(DEFAULT_INSURED_MIDDLE_NAME);
        assertThat(testPatientInsurance.getInsuredSuffix()).isEqualTo(DEFAULT_INSURED_SUFFIX);
        assertThat(testPatientInsurance.getInsuredDob()).isEqualTo(UPDATED_INSURED_DOB);
        assertThat(testPatientInsurance.getInsuredSsn()).isEqualTo(UPDATED_INSURED_SSN);
        assertThat(testPatientInsurance.getInsuredGender()).isEqualTo(UPDATED_INSURED_GENDER);
        assertThat(testPatientInsurance.getAlwaysCrossoverStatus()).isEqualTo(DEFAULT_ALWAYS_CROSSOVER_STATUS);
        assertThat(testPatientInsurance.getClaimCodes()).isEqualTo(DEFAULT_CLAIM_CODES);
        assertThat(testPatientInsurance.getAddtnlClaimInfo()).isEqualTo(UPDATED_ADDTNL_CLAIM_INFO);
        assertThat(testPatientInsurance.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPatientInsurance.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPatientInsurance.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testPatientInsurance.getInsuredLastName()).isEqualTo(DEFAULT_INSURED_LAST_NAME);
        assertThat(testPatientInsurance.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPatientInsurance.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPatientInsurance.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testPatientInsurance.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testPatientInsurance.getPatientInsuranceUuid()).isEqualTo(UPDATED_PATIENT_INSURANCE_UUID);
        assertThat(testPatientInsurance.getMemberId()).isEqualTo(UPDATED_MEMBER_ID);
        assertThat(testPatientInsurance.getPatientRelationshipInsured()).isEqualTo(UPDATED_PATIENT_RELATIONSHIP_INSURED);
        assertThat(testPatientInsurance.getPatientConditionEmployment()).isEqualTo(UPDATED_PATIENT_CONDITION_EMPLOYMENT);
        assertThat(testPatientInsurance.getPatientConditionAutoAccident()).isEqualTo(UPDATED_PATIENT_CONDITION_AUTO_ACCIDENT);
        assertThat(testPatientInsurance.getPatientConditionOtherAccident()).isEqualTo(UPDATED_PATIENT_CONDITION_OTHER_ACCIDENT);
        assertThat(testPatientInsurance.getInsurancePayerIdNo()).isEqualTo(DEFAULT_INSURANCE_PAYER_ID_NO);
        assertThat(testPatientInsurance.getExpirationStatus()).isEqualTo(UPDATED_EXPIRATION_STATUS);
        assertThat(testPatientInsurance.getInsurerAddressLine1()).isEqualTo(DEFAULT_INSURER_ADDRESS_LINE_1);
        assertThat(testPatientInsurance.getInsurerAddressLine2()).isEqualTo(UPDATED_INSURER_ADDRESS_LINE_2);
        assertThat(testPatientInsurance.getInsurerCity()).isEqualTo(UPDATED_INSURER_CITY);
        assertThat(testPatientInsurance.getInsurerState()).isEqualTo(UPDATED_INSURER_STATE);
        assertThat(testPatientInsurance.getInsurerZip()).isEqualTo(DEFAULT_INSURER_ZIP);
        assertThat(testPatientInsurance.getInsurerContact1()).isEqualTo(UPDATED_INSURER_CONTACT_1);
        assertThat(testPatientInsurance.getInsurerFax()).isEqualTo(DEFAULT_INSURER_FAX);
    }

    @Test
    void fullUpdatePatientInsuranceWithPatch() throws Exception {
        // Initialize the database
        patientInsuranceRepository.save(patientInsurance).block();

        int databaseSizeBeforeUpdate = patientInsuranceRepository.findAll().collectList().block().size();

        // Update the patientInsurance using partial update
        PatientInsurance partialUpdatedPatientInsurance = new PatientInsurance();
        partialUpdatedPatientInsurance.setPatientInsuranceId(patientInsurance.getPatientInsuranceId());

        partialUpdatedPatientInsurance
            .patientId(UPDATED_PATIENT_ID)
            .payerLevel(UPDATED_PAYER_LEVEL)
            .insuranceName(UPDATED_INSURANCE_NAME)
            .insuranceId(UPDATED_INSURANCE_ID)
            .coverageType(UPDATED_COVERAGE_TYPE)
            .payerContact(UPDATED_PAYER_CONTACT)
            .policyNum(UPDATED_POLICY_NUM)
            .policyGroupNum(UPDATED_POLICY_GROUP_NUM)
            .policyStartDate(UPDATED_POLICY_START_DATE)
            .policyEndDate(UPDATED_POLICY_END_DATE)
            .payPercentage(UPDATED_PAY_PERCENTAGE)
            .deductableAmt(UPDATED_DEDUCTABLE_AMT)
            .relationship(UPDATED_RELATIONSHIP)
            .insuredFirstName(UPDATED_INSURED_FIRST_NAME)
            .insuredMiddleName(UPDATED_INSURED_MIDDLE_NAME)
            .insuredSuffix(UPDATED_INSURED_SUFFIX)
            .insuredDob(UPDATED_INSURED_DOB)
            .insuredSsn(UPDATED_INSURED_SSN)
            .insuredGender(UPDATED_INSURED_GENDER)
            .alwaysCrossoverStatus(UPDATED_ALWAYS_CROSSOVER_STATUS)
            .claimCodes(UPDATED_CLAIM_CODES)
            .addtnlClaimInfo(UPDATED_ADDTNL_CLAIM_INFO)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .insuredLastName(UPDATED_INSURED_LAST_NAME)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .patientInsuranceUuid(UPDATED_PATIENT_INSURANCE_UUID)
            .memberId(UPDATED_MEMBER_ID)
            .patientRelationshipInsured(UPDATED_PATIENT_RELATIONSHIP_INSURED)
            .patientConditionEmployment(UPDATED_PATIENT_CONDITION_EMPLOYMENT)
            .patientConditionAutoAccident(UPDATED_PATIENT_CONDITION_AUTO_ACCIDENT)
            .patientConditionOtherAccident(UPDATED_PATIENT_CONDITION_OTHER_ACCIDENT)
            .insurancePayerIdNo(UPDATED_INSURANCE_PAYER_ID_NO)
            .expirationStatus(UPDATED_EXPIRATION_STATUS)
            .insurerAddressLine1(UPDATED_INSURER_ADDRESS_LINE_1)
            .insurerAddressLine2(UPDATED_INSURER_ADDRESS_LINE_2)
            .insurerCity(UPDATED_INSURER_CITY)
            .insurerState(UPDATED_INSURER_STATE)
            .insurerZip(UPDATED_INSURER_ZIP)
            .insurerContact1(UPDATED_INSURER_CONTACT_1)
            .insurerFax(UPDATED_INSURER_FAX);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPatientInsurance.getPatientInsuranceId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPatientInsurance))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientInsurance in the database
        List<PatientInsurance> patientInsuranceList = patientInsuranceRepository.findAll().collectList().block();
        assertThat(patientInsuranceList).hasSize(databaseSizeBeforeUpdate);
        PatientInsurance testPatientInsurance = patientInsuranceList.get(patientInsuranceList.size() - 1);
        assertThat(testPatientInsurance.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testPatientInsurance.getPayerLevel()).isEqualTo(UPDATED_PAYER_LEVEL);
        assertThat(testPatientInsurance.getInsuranceName()).isEqualTo(UPDATED_INSURANCE_NAME);
        assertThat(testPatientInsurance.getInsuranceId()).isEqualTo(UPDATED_INSURANCE_ID);
        assertThat(testPatientInsurance.getCoverageType()).isEqualTo(UPDATED_COVERAGE_TYPE);
        assertThat(testPatientInsurance.getPayerContact()).isEqualTo(UPDATED_PAYER_CONTACT);
        assertThat(testPatientInsurance.getPolicyNum()).isEqualTo(UPDATED_POLICY_NUM);
        assertThat(testPatientInsurance.getPolicyGroupNum()).isEqualTo(UPDATED_POLICY_GROUP_NUM);
        assertThat(testPatientInsurance.getPolicyStartDate()).isEqualTo(UPDATED_POLICY_START_DATE);
        assertThat(testPatientInsurance.getPolicyEndDate()).isEqualTo(UPDATED_POLICY_END_DATE);
        assertThat(testPatientInsurance.getPayPercentage()).isEqualTo(UPDATED_PAY_PERCENTAGE);
        assertThat(testPatientInsurance.getDeductableAmt()).isEqualTo(UPDATED_DEDUCTABLE_AMT);
        assertThat(testPatientInsurance.getRelationship()).isEqualTo(UPDATED_RELATIONSHIP);
        assertThat(testPatientInsurance.getInsuredFirstName()).isEqualTo(UPDATED_INSURED_FIRST_NAME);
        assertThat(testPatientInsurance.getInsuredMiddleName()).isEqualTo(UPDATED_INSURED_MIDDLE_NAME);
        assertThat(testPatientInsurance.getInsuredSuffix()).isEqualTo(UPDATED_INSURED_SUFFIX);
        assertThat(testPatientInsurance.getInsuredDob()).isEqualTo(UPDATED_INSURED_DOB);
        assertThat(testPatientInsurance.getInsuredSsn()).isEqualTo(UPDATED_INSURED_SSN);
        assertThat(testPatientInsurance.getInsuredGender()).isEqualTo(UPDATED_INSURED_GENDER);
        assertThat(testPatientInsurance.getAlwaysCrossoverStatus()).isEqualTo(UPDATED_ALWAYS_CROSSOVER_STATUS);
        assertThat(testPatientInsurance.getClaimCodes()).isEqualTo(UPDATED_CLAIM_CODES);
        assertThat(testPatientInsurance.getAddtnlClaimInfo()).isEqualTo(UPDATED_ADDTNL_CLAIM_INFO);
        assertThat(testPatientInsurance.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPatientInsurance.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPatientInsurance.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPatientInsurance.getInsuredLastName()).isEqualTo(UPDATED_INSURED_LAST_NAME);
        assertThat(testPatientInsurance.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPatientInsurance.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPatientInsurance.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPatientInsurance.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPatientInsurance.getPatientInsuranceUuid()).isEqualTo(UPDATED_PATIENT_INSURANCE_UUID);
        assertThat(testPatientInsurance.getMemberId()).isEqualTo(UPDATED_MEMBER_ID);
        assertThat(testPatientInsurance.getPatientRelationshipInsured()).isEqualTo(UPDATED_PATIENT_RELATIONSHIP_INSURED);
        assertThat(testPatientInsurance.getPatientConditionEmployment()).isEqualTo(UPDATED_PATIENT_CONDITION_EMPLOYMENT);
        assertThat(testPatientInsurance.getPatientConditionAutoAccident()).isEqualTo(UPDATED_PATIENT_CONDITION_AUTO_ACCIDENT);
        assertThat(testPatientInsurance.getPatientConditionOtherAccident()).isEqualTo(UPDATED_PATIENT_CONDITION_OTHER_ACCIDENT);
        assertThat(testPatientInsurance.getInsurancePayerIdNo()).isEqualTo(UPDATED_INSURANCE_PAYER_ID_NO);
        assertThat(testPatientInsurance.getExpirationStatus()).isEqualTo(UPDATED_EXPIRATION_STATUS);
        assertThat(testPatientInsurance.getInsurerAddressLine1()).isEqualTo(UPDATED_INSURER_ADDRESS_LINE_1);
        assertThat(testPatientInsurance.getInsurerAddressLine2()).isEqualTo(UPDATED_INSURER_ADDRESS_LINE_2);
        assertThat(testPatientInsurance.getInsurerCity()).isEqualTo(UPDATED_INSURER_CITY);
        assertThat(testPatientInsurance.getInsurerState()).isEqualTo(UPDATED_INSURER_STATE);
        assertThat(testPatientInsurance.getInsurerZip()).isEqualTo(UPDATED_INSURER_ZIP);
        assertThat(testPatientInsurance.getInsurerContact1()).isEqualTo(UPDATED_INSURER_CONTACT_1);
        assertThat(testPatientInsurance.getInsurerFax()).isEqualTo(UPDATED_INSURER_FAX);
    }

    @Test
    void patchNonExistingPatientInsurance() throws Exception {
        int databaseSizeBeforeUpdate = patientInsuranceRepository.findAll().collectList().block().size();
        patientInsurance.setPatientInsuranceId(count.incrementAndGet());

        // Create the PatientInsurance
        PatientInsuranceDTO patientInsuranceDTO = patientInsuranceMapper.toDto(patientInsurance);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, patientInsuranceDTO.getPatientInsuranceId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientInsuranceDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientInsurance in the database
        List<PatientInsurance> patientInsuranceList = patientInsuranceRepository.findAll().collectList().block();
        assertThat(patientInsuranceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchPatientInsurance() throws Exception {
        int databaseSizeBeforeUpdate = patientInsuranceRepository.findAll().collectList().block().size();
        patientInsurance.setPatientInsuranceId(count.incrementAndGet());

        // Create the PatientInsurance
        PatientInsuranceDTO patientInsuranceDTO = patientInsuranceMapper.toDto(patientInsurance);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientInsuranceDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientInsurance in the database
        List<PatientInsurance> patientInsuranceList = patientInsuranceRepository.findAll().collectList().block();
        assertThat(patientInsuranceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamPatientInsurance() throws Exception {
        int databaseSizeBeforeUpdate = patientInsuranceRepository.findAll().collectList().block().size();
        patientInsurance.setPatientInsuranceId(count.incrementAndGet());

        // Create the PatientInsurance
        PatientInsuranceDTO patientInsuranceDTO = patientInsuranceMapper.toDto(patientInsurance);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientInsuranceDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PatientInsurance in the database
        List<PatientInsurance> patientInsuranceList = patientInsuranceRepository.findAll().collectList().block();
        assertThat(patientInsuranceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deletePatientInsurance() {
        // Initialize the database
        patientInsuranceRepository.save(patientInsurance).block();

        int databaseSizeBeforeDelete = patientInsuranceRepository.findAll().collectList().block().size();

        // Delete the patientInsurance
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, patientInsurance.getPatientInsuranceId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<PatientInsurance> patientInsuranceList = patientInsuranceRepository.findAll().collectList().block();
        assertThat(patientInsuranceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
