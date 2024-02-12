package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.SalesOrderClinicalDetails;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.SalesOrderClinicalDetailsRepository;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderClinicalDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.SalesOrderClinicalDetailsMapper;
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
 * Integration tests for the {@link SalesOrderClinicalDetailsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class SalesOrderClinicalDetailsResourceIT {

    private static final Long DEFAULT_SALES_ORDER_ID = 1L;
    private static final Long UPDATED_SALES_ORDER_ID = 2L;

    private static final Long DEFAULT_PATIENT_ID = 1L;
    private static final Long UPDATED_PATIENT_ID = 2L;

    private static final Double DEFAULT_PATIENT_WEIGHT_IN_KG = 1D;
    private static final Double UPDATED_PATIENT_WEIGHT_IN_KG = 2D;

    private static final Double DEFAULT_PATIENT_WEIGHT_IN_LBS = 1D;
    private static final Double UPDATED_PATIENT_WEIGHT_IN_LBS = 2D;

    private static final Double DEFAULT_HEIGHT_IN_INCHES = 1D;
    private static final Double UPDATED_HEIGHT_IN_INCHES = 2D;

    private static final Double DEFAULT_HEIGHT_IN_CM = 1D;
    private static final Double UPDATED_HEIGHT_IN_CM = 2D;

    private static final Long DEFAULT_SALES_REP_ID = 1L;
    private static final Long UPDATED_SALES_REP_ID = 2L;

    private static final String DEFAULT_SALES_REP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SALES_REP_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_RENDERING_PROVIDER_FACILITY_ID = 1L;
    private static final Long UPDATED_RENDERING_PROVIDER_FACILITY_ID = 2L;

    private static final String DEFAULT_RENDERING_PROVIDER_FACILITY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_RENDERING_PROVIDER_FACILITY_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_RENDERING_PROVIDER_ID = 1L;
    private static final Long UPDATED_RENDERING_PROVIDER_ID = 2L;

    private static final String DEFAULT_RENDERING_PROVIDER_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_RENDERING_PROVIDER_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_RENDERING_PROVIDER_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_RENDERING_PROVIDER_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_RENDERING_PROVIDER_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_RENDERING_PROVIDER_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_RENDERING_PROVIDER_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_RENDERING_PROVIDER_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_RENDERING_PROVIDER_NPI = "AAAAAAAAAA";
    private static final String UPDATED_RENDERING_PROVIDER_NPI = "BBBBBBBBBB";

    private static final String DEFAULT_RENDERING_PROVIDER_DEA = "AAAAAAAAAA";
    private static final String UPDATED_RENDERING_PROVIDER_DEA = "BBBBBBBBBB";

    private static final String DEFAULT_RENDERING_PROVIDER_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_RENDERING_PROVIDER_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_RENDERING_PROVIDER_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_RENDERING_PROVIDER_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_RENDERING_PROVIDER_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_RENDERING_PROVIDER_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_RENDERING_PROVIDER_FAX = "AAAAAAAAAA";
    private static final String UPDATED_RENDERING_PROVIDER_FAX = "BBBBBBBBBB";

    private static final Long DEFAULT_REFERRING_PROVIDER_FACILITY_ID = 1L;
    private static final Long UPDATED_REFERRING_PROVIDER_FACILITY_ID = 2L;

    private static final String DEFAULT_REFERRING_PROVIDER_FACILITY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_REFERRING_PROVIDER_FACILITY_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_REFERRING_PROVIDER_ID = 1L;
    private static final Long UPDATED_REFERRING_PROVIDER_ID = 2L;

    private static final String DEFAULT_REFERRING_PROVIDER_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_REFERRING_PROVIDER_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_REFERRING_PROVIDER_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_REFERRING_PROVIDER_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_REFERRING_PROVIDER_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_REFERRING_PROVIDER_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_REFERRING_PROVIDER_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_REFERRING_PROVIDER_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_REFERRING_PROVIDER_NPI = "AAAAAAAAAA";
    private static final String UPDATED_REFERRING_PROVIDER_NPI = "BBBBBBBBBB";

    private static final String DEFAULT_REFERRING_PROVIDER_DEA = "AAAAAAAAAA";
    private static final String UPDATED_REFERRING_PROVIDER_DEA = "BBBBBBBBBB";

    private static final String DEFAULT_REFERRING_PROVIDER_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_REFERRING_PROVIDER_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_REFERRING_PROVIDER_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_REFERRING_PROVIDER_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_REFERRING_PROVIDER_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_REFERRING_PROVIDER_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_REFERRING_PROVIDER_FAX = "AAAAAAAAAA";
    private static final String UPDATED_REFERRING_PROVIDER_FAX = "BBBBBBBBBB";

    private static final Long DEFAULT_ORDERING_PROVIDER_FACILITY_ID = 1L;
    private static final Long UPDATED_ORDERING_PROVIDER_FACILITY_ID = 2L;

    private static final String DEFAULT_ORDERING_PROVIDER_FACILITY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ORDERING_PROVIDER_FACILITY_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_ORDERING_PROVIDER_ID = 1L;
    private static final Long UPDATED_ORDERING_PROVIDER_ID = 2L;

    private static final String DEFAULT_ORDERING_PROVIDER_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_ORDERING_PROVIDER_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERING_PROVIDER_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ORDERING_PROVIDER_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERING_PROVIDER_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ORDERING_PROVIDER_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERING_PROVIDER_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ORDERING_PROVIDER_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERING_PROVIDER_NPI = "AAAAAAAAAA";
    private static final String UPDATED_ORDERING_PROVIDER_NPI = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERING_PROVIDER_DEA = "AAAAAAAAAA";
    private static final String UPDATED_ORDERING_PROVIDER_DEA = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERING_PROVIDER_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_ORDERING_PROVIDER_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERING_PROVIDER_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_ORDERING_PROVIDER_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERING_PROVIDER_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_ORDERING_PROVIDER_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERING_PROVIDER_FAX = "AAAAAAAAAA";
    private static final String UPDATED_ORDERING_PROVIDER_FAX = "BBBBBBBBBB";

    private static final Long DEFAULT_MARKETING_REFERRAL_TYPE_ID = 1L;
    private static final Long UPDATED_MARKETING_REFERRAL_TYPE_ID = 2L;

    private static final String DEFAULT_MARKETING_REFERRAL_TYPE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_MARKETING_REFERRAL_TYPE_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_ICD_10_DIAGNOSIS_CODE_1 = "AAAAAAAAAA";
    private static final String UPDATED_ICD_10_DIAGNOSIS_CODE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ICD_10_DIAGNOSIS_CODE_2 = "AAAAAAAAAA";
    private static final String UPDATED_ICD_10_DIAGNOSIS_CODE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_ICD_10_DIAGNOSIS_CODE_3 = "AAAAAAAAAA";
    private static final String UPDATED_ICD_10_DIAGNOSIS_CODE_3 = "BBBBBBBBBB";

    private static final String DEFAULT_ICD_10_DIAGNOSIS_CODE_4 = "AAAAAAAAAA";
    private static final String UPDATED_ICD_10_DIAGNOSIS_CODE_4 = "BBBBBBBBBB";

    private static final String DEFAULT_ICD_10_DIAGNOSIS_CODE_5 = "AAAAAAAAAA";
    private static final String UPDATED_ICD_10_DIAGNOSIS_CODE_5 = "BBBBBBBBBB";

    private static final String DEFAULT_ICD_10_DIAGNOSIS_CODE_6 = "AAAAAAAAAA";
    private static final String UPDATED_ICD_10_DIAGNOSIS_CODE_6 = "BBBBBBBBBB";

    private static final String DEFAULT_ICD_10_DIAGNOSIS_CODE_7 = "AAAAAAAAAA";
    private static final String UPDATED_ICD_10_DIAGNOSIS_CODE_7 = "BBBBBBBBBB";

    private static final String DEFAULT_ICD_10_DIAGNOSIS_CODE_8 = "AAAAAAAAAA";
    private static final String UPDATED_ICD_10_DIAGNOSIS_CODE_8 = "BBBBBBBBBB";

    private static final String DEFAULT_ICD_10_DIAGNOSIS_CODE_9 = "AAAAAAAAAA";
    private static final String UPDATED_ICD_10_DIAGNOSIS_CODE_9 = "BBBBBBBBBB";

    private static final String DEFAULT_ICD_10_DIAGNOSIS_CODE_10 = "AAAAAAAAAA";
    private static final String UPDATED_ICD_10_DIAGNOSIS_CODE_10 = "BBBBBBBBBB";

    private static final String DEFAULT_ICD_10_DIAGNOSIS_CODE_11 = "AAAAAAAAAA";
    private static final String UPDATED_ICD_10_DIAGNOSIS_CODE_11 = "BBBBBBBBBB";

    private static final String DEFAULT_ICD_10_DIAGNOSIS_CODE_12 = "AAAAAAAAAA";
    private static final String UPDATED_ICD_10_DIAGNOSIS_CODE_12 = "BBBBBBBBBB";

    private static final String DEFAULT_EPSDT_CERTIFICATION_CONDITION_INDICATOR = "AAAAAAAAAA";
    private static final String UPDATED_EPSDT_CERTIFICATION_CONDITION_INDICATOR = "BBBBBBBBBB";

    private static final String DEFAULT_EPSDT_CERTIFICATION_CODE = "AAAAAAAAAA";
    private static final String UPDATED_EPSDT_CERTIFICATION_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_RENDERING_PROVIDER_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_RENDERING_PROVIDER_ZIP = "BBBBBBBBBB";

    private static final String DEFAULT_REFERRING_PROVIDER_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_REFERRING_PROVIDER_ZIP = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERING_PROVIDER_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_ORDERING_PROVIDER_ZIP = "BBBBBBBBBB";

    private static final Long DEFAULT_MARKETING_REFERRAL_ID = 1L;
    private static final Long UPDATED_MARKETING_REFERRAL_ID = 2L;

    private static final String DEFAULT_MARKETING_REFERRAL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MARKETING_REFERRAL_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final UUID DEFAULT_SALES_ORDER_CLINICAL_DETAILS_UUID = UUID.randomUUID();
    private static final UUID UPDATED_SALES_ORDER_CLINICAL_DETAILS_UUID = UUID.randomUUID();

    private static final String DEFAULT_PRIMARY_DIAGNOSIS = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_DIAGNOSIS = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERING_PROVIDER_CITY = "AAAAAAAAAA";
    private static final String UPDATED_ORDERING_PROVIDER_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERING_PROVIDER_STATE = "AAAAAAAAAA";
    private static final String UPDATED_ORDERING_PROVIDER_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERING_PROVIDER_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_ORDERING_PROVIDER_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERING_PROVIDER_CONTACT_NO_1 = "AAAAAAAAAA";
    private static final String UPDATED_ORDERING_PROVIDER_CONTACT_NO_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERING_PROVIDER_CONTACT_NO_2 = "AAAAAAAAAA";
    private static final String UPDATED_ORDERING_PROVIDER_CONTACT_NO_2 = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERING_PROVIDER_EFAX = "AAAAAAAAAA";
    private static final String UPDATED_ORDERING_PROVIDER_EFAX = "BBBBBBBBBB";

    private static final String DEFAULT_RELATIONSHIP = "AAAAAAAAAA";
    private static final String UPDATED_RELATIONSHIP = "BBBBBBBBBB";

    private static final String DEFAULT_MODE_OF_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_MODE_OF_CONTACT = "BBBBBBBBBB";

    private static final String DEFAULT_REFERRING_PROVIDER_CITY = "AAAAAAAAAA";
    private static final String UPDATED_REFERRING_PROVIDER_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_REFERRING_PROVIDER_STATE = "AAAAAAAAAA";
    private static final String UPDATED_REFERRING_PROVIDER_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_REFERRING_PROVIDER_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_REFERRING_PROVIDER_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_REFERRING_PROVIDER_CONTACT_NO_1 = "AAAAAAAAAA";
    private static final String UPDATED_REFERRING_PROVIDER_CONTACT_NO_1 = "BBBBBBBBBB";

    private static final String DEFAULT_REFERRING_PROVIDER_CONTACT_NO_2 = "AAAAAAAAAA";
    private static final String UPDATED_REFERRING_PROVIDER_CONTACT_NO_2 = "BBBBBBBBBB";

    private static final String DEFAULT_REFERRING_PROVIDER_EFAX = "AAAAAAAAAA";
    private static final String UPDATED_REFERRING_PROVIDER_EFAX = "BBBBBBBBBB";

    private static final String DEFAULT_RENDERING_PROVIDER_CITY = "AAAAAAAAAA";
    private static final String UPDATED_RENDERING_PROVIDER_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_RENDERING_PROVIDER_STATE = "AAAAAAAAAA";
    private static final String UPDATED_RENDERING_PROVIDER_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_RENDERING_PROVIDER_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_RENDERING_PROVIDER_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_RENDERING_PROVIDER_CONTACT_NO_1 = "AAAAAAAAAA";
    private static final String UPDATED_RENDERING_PROVIDER_CONTACT_NO_1 = "BBBBBBBBBB";

    private static final String DEFAULT_RENDERING_PROVIDER_CONTACT_NO_2 = "AAAAAAAAAA";
    private static final String UPDATED_RENDERING_PROVIDER_CONTACT_NO_2 = "BBBBBBBBBB";

    private static final String DEFAULT_RENDERING_PROVIDER_EFAX = "AAAAAAAAAA";
    private static final String UPDATED_RENDERING_PROVIDER_EFAX = "BBBBBBBBBB";

    private static final String DEFAULT_DIAGNOSIS_CODE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_DIAGNOSIS_CODE_TYPE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/sales-order-clinical-details";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{salesOrderClinicalDetailsId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SalesOrderClinicalDetailsRepository salesOrderClinicalDetailsRepository;

    @Autowired
    private SalesOrderClinicalDetailsMapper salesOrderClinicalDetailsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private SalesOrderClinicalDetails salesOrderClinicalDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesOrderClinicalDetails createEntity(EntityManager em) {
        SalesOrderClinicalDetails salesOrderClinicalDetails = new SalesOrderClinicalDetails()
            .salesOrderId(DEFAULT_SALES_ORDER_ID)
            .patientId(DEFAULT_PATIENT_ID)
            .patientWeightInKg(DEFAULT_PATIENT_WEIGHT_IN_KG)
            .patientWeightInLbs(DEFAULT_PATIENT_WEIGHT_IN_LBS)
            .heightInInches(DEFAULT_HEIGHT_IN_INCHES)
            .heightInCm(DEFAULT_HEIGHT_IN_CM)
            .salesRepId(DEFAULT_SALES_REP_ID)
            .salesRepName(DEFAULT_SALES_REP_NAME)
            .renderingProviderFacilityId(DEFAULT_RENDERING_PROVIDER_FACILITY_ID)
            .renderingProviderFacilityName(DEFAULT_RENDERING_PROVIDER_FACILITY_NAME)
            .renderingProviderId(DEFAULT_RENDERING_PROVIDER_ID)
            .renderingProviderType(DEFAULT_RENDERING_PROVIDER_TYPE)
            .renderingProviderFirstName(DEFAULT_RENDERING_PROVIDER_FIRST_NAME)
            .renderingProviderMiddleName(DEFAULT_RENDERING_PROVIDER_MIDDLE_NAME)
            .renderingProviderLastName(DEFAULT_RENDERING_PROVIDER_LAST_NAME)
            .renderingProviderNpi(DEFAULT_RENDERING_PROVIDER_NPI)
            .renderingProviderDea(DEFAULT_RENDERING_PROVIDER_DEA)
            .renderingProviderAddressLine1(DEFAULT_RENDERING_PROVIDER_ADDRESS_LINE_1)
            .renderingProviderAddressLine2(DEFAULT_RENDERING_PROVIDER_ADDRESS_LINE_2)
            .renderingProviderEmail(DEFAULT_RENDERING_PROVIDER_EMAIL)
            .renderingProviderFax(DEFAULT_RENDERING_PROVIDER_FAX)
            .referringProviderFacilityId(DEFAULT_REFERRING_PROVIDER_FACILITY_ID)
            .referringProviderFacilityName(DEFAULT_REFERRING_PROVIDER_FACILITY_NAME)
            .referringProviderId(DEFAULT_REFERRING_PROVIDER_ID)
            .referringProviderType(DEFAULT_REFERRING_PROVIDER_TYPE)
            .referringProviderFirstName(DEFAULT_REFERRING_PROVIDER_FIRST_NAME)
            .referringProviderMiddleName(DEFAULT_REFERRING_PROVIDER_MIDDLE_NAME)
            .referringProviderLastName(DEFAULT_REFERRING_PROVIDER_LAST_NAME)
            .referringProviderNpi(DEFAULT_REFERRING_PROVIDER_NPI)
            .referringProviderDea(DEFAULT_REFERRING_PROVIDER_DEA)
            .referringProviderAddressLine1(DEFAULT_REFERRING_PROVIDER_ADDRESS_LINE_1)
            .referringProviderAddressLine2(DEFAULT_REFERRING_PROVIDER_ADDRESS_LINE_2)
            .referringProviderEmail(DEFAULT_REFERRING_PROVIDER_EMAIL)
            .referringProviderFax(DEFAULT_REFERRING_PROVIDER_FAX)
            .orderingProviderFacilityId(DEFAULT_ORDERING_PROVIDER_FACILITY_ID)
            .orderingProviderFacilityName(DEFAULT_ORDERING_PROVIDER_FACILITY_NAME)
            .orderingProviderId(DEFAULT_ORDERING_PROVIDER_ID)
            .orderingProviderType(DEFAULT_ORDERING_PROVIDER_TYPE)
            .orderingProviderFirstName(DEFAULT_ORDERING_PROVIDER_FIRST_NAME)
            .orderingProviderMiddleName(DEFAULT_ORDERING_PROVIDER_MIDDLE_NAME)
            .orderingProviderLastName(DEFAULT_ORDERING_PROVIDER_LAST_NAME)
            .orderingProviderNpi(DEFAULT_ORDERING_PROVIDER_NPI)
            .orderingProviderDea(DEFAULT_ORDERING_PROVIDER_DEA)
            .orderingProviderAddressLine1(DEFAULT_ORDERING_PROVIDER_ADDRESS_LINE_1)
            .orderingProviderAddressLine2(DEFAULT_ORDERING_PROVIDER_ADDRESS_LINE_2)
            .orderingProviderEmail(DEFAULT_ORDERING_PROVIDER_EMAIL)
            .orderingProviderFax(DEFAULT_ORDERING_PROVIDER_FAX)
            .marketingReferralTypeId(DEFAULT_MARKETING_REFERRAL_TYPE_ID)
            .marketingReferralTypeDescription(DEFAULT_MARKETING_REFERRAL_TYPE_DESCRIPTION)
            .icd10DiagnosisCode1(DEFAULT_ICD_10_DIAGNOSIS_CODE_1)
            .icd10DiagnosisCode2(DEFAULT_ICD_10_DIAGNOSIS_CODE_2)
            .icd10DiagnosisCode3(DEFAULT_ICD_10_DIAGNOSIS_CODE_3)
            .icd10DiagnosisCode4(DEFAULT_ICD_10_DIAGNOSIS_CODE_4)
            .icd10DiagnosisCode5(DEFAULT_ICD_10_DIAGNOSIS_CODE_5)
            .icd10DiagnosisCode6(DEFAULT_ICD_10_DIAGNOSIS_CODE_6)
            .icd10DiagnosisCode7(DEFAULT_ICD_10_DIAGNOSIS_CODE_7)
            .icd10DiagnosisCode8(DEFAULT_ICD_10_DIAGNOSIS_CODE_8)
            .icd10DiagnosisCode9(DEFAULT_ICD_10_DIAGNOSIS_CODE_9)
            .icd10DiagnosisCode10(DEFAULT_ICD_10_DIAGNOSIS_CODE_10)
            .icd10DiagnosisCode11(DEFAULT_ICD_10_DIAGNOSIS_CODE_11)
            .icd10DiagnosisCode12(DEFAULT_ICD_10_DIAGNOSIS_CODE_12)
            .epsdtCertificationConditionIndicator(DEFAULT_EPSDT_CERTIFICATION_CONDITION_INDICATOR)
            .epsdtCertificationCode(DEFAULT_EPSDT_CERTIFICATION_CODE)
            .status(DEFAULT_STATUS)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .renderingProviderZip(DEFAULT_RENDERING_PROVIDER_ZIP)
            .referringProviderZip(DEFAULT_REFERRING_PROVIDER_ZIP)
            .orderingProviderZip(DEFAULT_ORDERING_PROVIDER_ZIP)
            .marketingReferralId(DEFAULT_MARKETING_REFERRAL_ID)
            .marketingReferralName(DEFAULT_MARKETING_REFERRAL_NAME)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .createdById(DEFAULT_CREATED_BY_ID)
            .salesOrderClinicalDetailsUuid(DEFAULT_SALES_ORDER_CLINICAL_DETAILS_UUID)
            .primaryDiagnosis(DEFAULT_PRIMARY_DIAGNOSIS)
            .orderingProviderCity(DEFAULT_ORDERING_PROVIDER_CITY)
            .orderingProviderState(DEFAULT_ORDERING_PROVIDER_STATE)
            .orderingProviderCountry(DEFAULT_ORDERING_PROVIDER_COUNTRY)
            .orderingProviderContactNo1(DEFAULT_ORDERING_PROVIDER_CONTACT_NO_1)
            .orderingProviderContactNo2(DEFAULT_ORDERING_PROVIDER_CONTACT_NO_2)
            .orderingProviderEfax(DEFAULT_ORDERING_PROVIDER_EFAX)
            .relationship(DEFAULT_RELATIONSHIP)
            .modeOfContact(DEFAULT_MODE_OF_CONTACT)
            .referringProviderCity(DEFAULT_REFERRING_PROVIDER_CITY)
            .referringProviderState(DEFAULT_REFERRING_PROVIDER_STATE)
            .referringProviderCountry(DEFAULT_REFERRING_PROVIDER_COUNTRY)
            .referringProviderContactNo1(DEFAULT_REFERRING_PROVIDER_CONTACT_NO_1)
            .referringProviderContactNo2(DEFAULT_REFERRING_PROVIDER_CONTACT_NO_2)
            .referringProviderEfax(DEFAULT_REFERRING_PROVIDER_EFAX)
            .renderingProviderCity(DEFAULT_RENDERING_PROVIDER_CITY)
            .renderingProviderState(DEFAULT_RENDERING_PROVIDER_STATE)
            .renderingProviderCountry(DEFAULT_RENDERING_PROVIDER_COUNTRY)
            .renderingProviderContactNo1(DEFAULT_RENDERING_PROVIDER_CONTACT_NO_1)
            .renderingProviderContactNo2(DEFAULT_RENDERING_PROVIDER_CONTACT_NO_2)
            .renderingProviderEfax(DEFAULT_RENDERING_PROVIDER_EFAX)
            .diagnosisCodeType(DEFAULT_DIAGNOSIS_CODE_TYPE);
        return salesOrderClinicalDetails;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesOrderClinicalDetails createUpdatedEntity(EntityManager em) {
        SalesOrderClinicalDetails salesOrderClinicalDetails = new SalesOrderClinicalDetails()
            .salesOrderId(UPDATED_SALES_ORDER_ID)
            .patientId(UPDATED_PATIENT_ID)
            .patientWeightInKg(UPDATED_PATIENT_WEIGHT_IN_KG)
            .patientWeightInLbs(UPDATED_PATIENT_WEIGHT_IN_LBS)
            .heightInInches(UPDATED_HEIGHT_IN_INCHES)
            .heightInCm(UPDATED_HEIGHT_IN_CM)
            .salesRepId(UPDATED_SALES_REP_ID)
            .salesRepName(UPDATED_SALES_REP_NAME)
            .renderingProviderFacilityId(UPDATED_RENDERING_PROVIDER_FACILITY_ID)
            .renderingProviderFacilityName(UPDATED_RENDERING_PROVIDER_FACILITY_NAME)
            .renderingProviderId(UPDATED_RENDERING_PROVIDER_ID)
            .renderingProviderType(UPDATED_RENDERING_PROVIDER_TYPE)
            .renderingProviderFirstName(UPDATED_RENDERING_PROVIDER_FIRST_NAME)
            .renderingProviderMiddleName(UPDATED_RENDERING_PROVIDER_MIDDLE_NAME)
            .renderingProviderLastName(UPDATED_RENDERING_PROVIDER_LAST_NAME)
            .renderingProviderNpi(UPDATED_RENDERING_PROVIDER_NPI)
            .renderingProviderDea(UPDATED_RENDERING_PROVIDER_DEA)
            .renderingProviderAddressLine1(UPDATED_RENDERING_PROVIDER_ADDRESS_LINE_1)
            .renderingProviderAddressLine2(UPDATED_RENDERING_PROVIDER_ADDRESS_LINE_2)
            .renderingProviderEmail(UPDATED_RENDERING_PROVIDER_EMAIL)
            .renderingProviderFax(UPDATED_RENDERING_PROVIDER_FAX)
            .referringProviderFacilityId(UPDATED_REFERRING_PROVIDER_FACILITY_ID)
            .referringProviderFacilityName(UPDATED_REFERRING_PROVIDER_FACILITY_NAME)
            .referringProviderId(UPDATED_REFERRING_PROVIDER_ID)
            .referringProviderType(UPDATED_REFERRING_PROVIDER_TYPE)
            .referringProviderFirstName(UPDATED_REFERRING_PROVIDER_FIRST_NAME)
            .referringProviderMiddleName(UPDATED_REFERRING_PROVIDER_MIDDLE_NAME)
            .referringProviderLastName(UPDATED_REFERRING_PROVIDER_LAST_NAME)
            .referringProviderNpi(UPDATED_REFERRING_PROVIDER_NPI)
            .referringProviderDea(UPDATED_REFERRING_PROVIDER_DEA)
            .referringProviderAddressLine1(UPDATED_REFERRING_PROVIDER_ADDRESS_LINE_1)
            .referringProviderAddressLine2(UPDATED_REFERRING_PROVIDER_ADDRESS_LINE_2)
            .referringProviderEmail(UPDATED_REFERRING_PROVIDER_EMAIL)
            .referringProviderFax(UPDATED_REFERRING_PROVIDER_FAX)
            .orderingProviderFacilityId(UPDATED_ORDERING_PROVIDER_FACILITY_ID)
            .orderingProviderFacilityName(UPDATED_ORDERING_PROVIDER_FACILITY_NAME)
            .orderingProviderId(UPDATED_ORDERING_PROVIDER_ID)
            .orderingProviderType(UPDATED_ORDERING_PROVIDER_TYPE)
            .orderingProviderFirstName(UPDATED_ORDERING_PROVIDER_FIRST_NAME)
            .orderingProviderMiddleName(UPDATED_ORDERING_PROVIDER_MIDDLE_NAME)
            .orderingProviderLastName(UPDATED_ORDERING_PROVIDER_LAST_NAME)
            .orderingProviderNpi(UPDATED_ORDERING_PROVIDER_NPI)
            .orderingProviderDea(UPDATED_ORDERING_PROVIDER_DEA)
            .orderingProviderAddressLine1(UPDATED_ORDERING_PROVIDER_ADDRESS_LINE_1)
            .orderingProviderAddressLine2(UPDATED_ORDERING_PROVIDER_ADDRESS_LINE_2)
            .orderingProviderEmail(UPDATED_ORDERING_PROVIDER_EMAIL)
            .orderingProviderFax(UPDATED_ORDERING_PROVIDER_FAX)
            .marketingReferralTypeId(UPDATED_MARKETING_REFERRAL_TYPE_ID)
            .marketingReferralTypeDescription(UPDATED_MARKETING_REFERRAL_TYPE_DESCRIPTION)
            .icd10DiagnosisCode1(UPDATED_ICD_10_DIAGNOSIS_CODE_1)
            .icd10DiagnosisCode2(UPDATED_ICD_10_DIAGNOSIS_CODE_2)
            .icd10DiagnosisCode3(UPDATED_ICD_10_DIAGNOSIS_CODE_3)
            .icd10DiagnosisCode4(UPDATED_ICD_10_DIAGNOSIS_CODE_4)
            .icd10DiagnosisCode5(UPDATED_ICD_10_DIAGNOSIS_CODE_5)
            .icd10DiagnosisCode6(UPDATED_ICD_10_DIAGNOSIS_CODE_6)
            .icd10DiagnosisCode7(UPDATED_ICD_10_DIAGNOSIS_CODE_7)
            .icd10DiagnosisCode8(UPDATED_ICD_10_DIAGNOSIS_CODE_8)
            .icd10DiagnosisCode9(UPDATED_ICD_10_DIAGNOSIS_CODE_9)
            .icd10DiagnosisCode10(UPDATED_ICD_10_DIAGNOSIS_CODE_10)
            .icd10DiagnosisCode11(UPDATED_ICD_10_DIAGNOSIS_CODE_11)
            .icd10DiagnosisCode12(UPDATED_ICD_10_DIAGNOSIS_CODE_12)
            .epsdtCertificationConditionIndicator(UPDATED_EPSDT_CERTIFICATION_CONDITION_INDICATOR)
            .epsdtCertificationCode(UPDATED_EPSDT_CERTIFICATION_CODE)
            .status(UPDATED_STATUS)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .renderingProviderZip(UPDATED_RENDERING_PROVIDER_ZIP)
            .referringProviderZip(UPDATED_REFERRING_PROVIDER_ZIP)
            .orderingProviderZip(UPDATED_ORDERING_PROVIDER_ZIP)
            .marketingReferralId(UPDATED_MARKETING_REFERRAL_ID)
            .marketingReferralName(UPDATED_MARKETING_REFERRAL_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .createdById(UPDATED_CREATED_BY_ID)
            .salesOrderClinicalDetailsUuid(UPDATED_SALES_ORDER_CLINICAL_DETAILS_UUID)
            .primaryDiagnosis(UPDATED_PRIMARY_DIAGNOSIS)
            .orderingProviderCity(UPDATED_ORDERING_PROVIDER_CITY)
            .orderingProviderState(UPDATED_ORDERING_PROVIDER_STATE)
            .orderingProviderCountry(UPDATED_ORDERING_PROVIDER_COUNTRY)
            .orderingProviderContactNo1(UPDATED_ORDERING_PROVIDER_CONTACT_NO_1)
            .orderingProviderContactNo2(UPDATED_ORDERING_PROVIDER_CONTACT_NO_2)
            .orderingProviderEfax(UPDATED_ORDERING_PROVIDER_EFAX)
            .relationship(UPDATED_RELATIONSHIP)
            .modeOfContact(UPDATED_MODE_OF_CONTACT)
            .referringProviderCity(UPDATED_REFERRING_PROVIDER_CITY)
            .referringProviderState(UPDATED_REFERRING_PROVIDER_STATE)
            .referringProviderCountry(UPDATED_REFERRING_PROVIDER_COUNTRY)
            .referringProviderContactNo1(UPDATED_REFERRING_PROVIDER_CONTACT_NO_1)
            .referringProviderContactNo2(UPDATED_REFERRING_PROVIDER_CONTACT_NO_2)
            .referringProviderEfax(UPDATED_REFERRING_PROVIDER_EFAX)
            .renderingProviderCity(UPDATED_RENDERING_PROVIDER_CITY)
            .renderingProviderState(UPDATED_RENDERING_PROVIDER_STATE)
            .renderingProviderCountry(UPDATED_RENDERING_PROVIDER_COUNTRY)
            .renderingProviderContactNo1(UPDATED_RENDERING_PROVIDER_CONTACT_NO_1)
            .renderingProviderContactNo2(UPDATED_RENDERING_PROVIDER_CONTACT_NO_2)
            .renderingProviderEfax(UPDATED_RENDERING_PROVIDER_EFAX)
            .diagnosisCodeType(UPDATED_DIAGNOSIS_CODE_TYPE);
        return salesOrderClinicalDetails;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(SalesOrderClinicalDetails.class).block();
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
        salesOrderClinicalDetails = createEntity(em);
    }

    @Test
    void createSalesOrderClinicalDetails() throws Exception {
        int databaseSizeBeforeCreate = salesOrderClinicalDetailsRepository.findAll().collectList().block().size();
        // Create the SalesOrderClinicalDetails
        SalesOrderClinicalDetailsDTO salesOrderClinicalDetailsDTO = salesOrderClinicalDetailsMapper.toDto(salesOrderClinicalDetails);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderClinicalDetailsDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the SalesOrderClinicalDetails in the database
        List<SalesOrderClinicalDetails> salesOrderClinicalDetailsList = salesOrderClinicalDetailsRepository.findAll().collectList().block();
        assertThat(salesOrderClinicalDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        SalesOrderClinicalDetails testSalesOrderClinicalDetails = salesOrderClinicalDetailsList.get(
            salesOrderClinicalDetailsList.size() - 1
        );
        assertThat(testSalesOrderClinicalDetails.getSalesOrderId()).isEqualTo(DEFAULT_SALES_ORDER_ID);
        assertThat(testSalesOrderClinicalDetails.getPatientId()).isEqualTo(DEFAULT_PATIENT_ID);
        assertThat(testSalesOrderClinicalDetails.getPatientWeightInKg()).isEqualTo(DEFAULT_PATIENT_WEIGHT_IN_KG);
        assertThat(testSalesOrderClinicalDetails.getPatientWeightInLbs()).isEqualTo(DEFAULT_PATIENT_WEIGHT_IN_LBS);
        assertThat(testSalesOrderClinicalDetails.getHeightInInches()).isEqualTo(DEFAULT_HEIGHT_IN_INCHES);
        assertThat(testSalesOrderClinicalDetails.getHeightInCm()).isEqualTo(DEFAULT_HEIGHT_IN_CM);
        assertThat(testSalesOrderClinicalDetails.getSalesRepId()).isEqualTo(DEFAULT_SALES_REP_ID);
        assertThat(testSalesOrderClinicalDetails.getSalesRepName()).isEqualTo(DEFAULT_SALES_REP_NAME);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderFacilityId()).isEqualTo(DEFAULT_RENDERING_PROVIDER_FACILITY_ID);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderFacilityName()).isEqualTo(DEFAULT_RENDERING_PROVIDER_FACILITY_NAME);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderId()).isEqualTo(DEFAULT_RENDERING_PROVIDER_ID);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderType()).isEqualTo(DEFAULT_RENDERING_PROVIDER_TYPE);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderFirstName()).isEqualTo(DEFAULT_RENDERING_PROVIDER_FIRST_NAME);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderMiddleName()).isEqualTo(DEFAULT_RENDERING_PROVIDER_MIDDLE_NAME);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderLastName()).isEqualTo(DEFAULT_RENDERING_PROVIDER_LAST_NAME);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderNpi()).isEqualTo(DEFAULT_RENDERING_PROVIDER_NPI);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderDea()).isEqualTo(DEFAULT_RENDERING_PROVIDER_DEA);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderAddressLine1()).isEqualTo(DEFAULT_RENDERING_PROVIDER_ADDRESS_LINE_1);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderAddressLine2()).isEqualTo(DEFAULT_RENDERING_PROVIDER_ADDRESS_LINE_2);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderEmail()).isEqualTo(DEFAULT_RENDERING_PROVIDER_EMAIL);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderFax()).isEqualTo(DEFAULT_RENDERING_PROVIDER_FAX);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderFacilityId()).isEqualTo(DEFAULT_REFERRING_PROVIDER_FACILITY_ID);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderFacilityName()).isEqualTo(DEFAULT_REFERRING_PROVIDER_FACILITY_NAME);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderId()).isEqualTo(DEFAULT_REFERRING_PROVIDER_ID);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderType()).isEqualTo(DEFAULT_REFERRING_PROVIDER_TYPE);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderFirstName()).isEqualTo(DEFAULT_REFERRING_PROVIDER_FIRST_NAME);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderMiddleName()).isEqualTo(DEFAULT_REFERRING_PROVIDER_MIDDLE_NAME);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderLastName()).isEqualTo(DEFAULT_REFERRING_PROVIDER_LAST_NAME);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderNpi()).isEqualTo(DEFAULT_REFERRING_PROVIDER_NPI);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderDea()).isEqualTo(DEFAULT_REFERRING_PROVIDER_DEA);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderAddressLine1()).isEqualTo(DEFAULT_REFERRING_PROVIDER_ADDRESS_LINE_1);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderAddressLine2()).isEqualTo(DEFAULT_REFERRING_PROVIDER_ADDRESS_LINE_2);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderEmail()).isEqualTo(DEFAULT_REFERRING_PROVIDER_EMAIL);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderFax()).isEqualTo(DEFAULT_REFERRING_PROVIDER_FAX);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderFacilityId()).isEqualTo(DEFAULT_ORDERING_PROVIDER_FACILITY_ID);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderFacilityName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_FACILITY_NAME);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderId()).isEqualTo(DEFAULT_ORDERING_PROVIDER_ID);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderType()).isEqualTo(DEFAULT_ORDERING_PROVIDER_TYPE);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderFirstName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderMiddleName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_MIDDLE_NAME);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderLastName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderNpi()).isEqualTo(DEFAULT_ORDERING_PROVIDER_NPI);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderDea()).isEqualTo(DEFAULT_ORDERING_PROVIDER_DEA);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderAddressLine1()).isEqualTo(DEFAULT_ORDERING_PROVIDER_ADDRESS_LINE_1);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderAddressLine2()).isEqualTo(DEFAULT_ORDERING_PROVIDER_ADDRESS_LINE_2);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderEmail()).isEqualTo(DEFAULT_ORDERING_PROVIDER_EMAIL);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderFax()).isEqualTo(DEFAULT_ORDERING_PROVIDER_FAX);
        assertThat(testSalesOrderClinicalDetails.getMarketingReferralTypeId()).isEqualTo(DEFAULT_MARKETING_REFERRAL_TYPE_ID);
        assertThat(testSalesOrderClinicalDetails.getMarketingReferralTypeDescription())
            .isEqualTo(DEFAULT_MARKETING_REFERRAL_TYPE_DESCRIPTION);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode1()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_1);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode2()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_2);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode3()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_3);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode4()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_4);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode5()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_5);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode6()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_6);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode7()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_7);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode8()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_8);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode9()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_9);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode10()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_10);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode11()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_11);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode12()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_12);
        assertThat(testSalesOrderClinicalDetails.getEpsdtCertificationConditionIndicator())
            .isEqualTo(DEFAULT_EPSDT_CERTIFICATION_CONDITION_INDICATOR);
        assertThat(testSalesOrderClinicalDetails.getEpsdtCertificationCode()).isEqualTo(DEFAULT_EPSDT_CERTIFICATION_CODE);
        assertThat(testSalesOrderClinicalDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSalesOrderClinicalDetails.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testSalesOrderClinicalDetails.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testSalesOrderClinicalDetails.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testSalesOrderClinicalDetails.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderZip()).isEqualTo(DEFAULT_RENDERING_PROVIDER_ZIP);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderZip()).isEqualTo(DEFAULT_REFERRING_PROVIDER_ZIP);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderZip()).isEqualTo(DEFAULT_ORDERING_PROVIDER_ZIP);
        assertThat(testSalesOrderClinicalDetails.getMarketingReferralId()).isEqualTo(DEFAULT_MARKETING_REFERRAL_ID);
        assertThat(testSalesOrderClinicalDetails.getMarketingReferralName()).isEqualTo(DEFAULT_MARKETING_REFERRAL_NAME);
        assertThat(testSalesOrderClinicalDetails.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testSalesOrderClinicalDetails.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testSalesOrderClinicalDetails.getSalesOrderClinicalDetailsUuid()).isEqualTo(DEFAULT_SALES_ORDER_CLINICAL_DETAILS_UUID);
        assertThat(testSalesOrderClinicalDetails.getPrimaryDiagnosis()).isEqualTo(DEFAULT_PRIMARY_DIAGNOSIS);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderCity()).isEqualTo(DEFAULT_ORDERING_PROVIDER_CITY);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderState()).isEqualTo(DEFAULT_ORDERING_PROVIDER_STATE);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderCountry()).isEqualTo(DEFAULT_ORDERING_PROVIDER_COUNTRY);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderContactNo1()).isEqualTo(DEFAULT_ORDERING_PROVIDER_CONTACT_NO_1);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderContactNo2()).isEqualTo(DEFAULT_ORDERING_PROVIDER_CONTACT_NO_2);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderEfax()).isEqualTo(DEFAULT_ORDERING_PROVIDER_EFAX);
        assertThat(testSalesOrderClinicalDetails.getRelationship()).isEqualTo(DEFAULT_RELATIONSHIP);
        assertThat(testSalesOrderClinicalDetails.getModeOfContact()).isEqualTo(DEFAULT_MODE_OF_CONTACT);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderCity()).isEqualTo(DEFAULT_REFERRING_PROVIDER_CITY);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderState()).isEqualTo(DEFAULT_REFERRING_PROVIDER_STATE);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderCountry()).isEqualTo(DEFAULT_REFERRING_PROVIDER_COUNTRY);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderContactNo1()).isEqualTo(DEFAULT_REFERRING_PROVIDER_CONTACT_NO_1);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderContactNo2()).isEqualTo(DEFAULT_REFERRING_PROVIDER_CONTACT_NO_2);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderEfax()).isEqualTo(DEFAULT_REFERRING_PROVIDER_EFAX);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderCity()).isEqualTo(DEFAULT_RENDERING_PROVIDER_CITY);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderState()).isEqualTo(DEFAULT_RENDERING_PROVIDER_STATE);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderCountry()).isEqualTo(DEFAULT_RENDERING_PROVIDER_COUNTRY);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderContactNo1()).isEqualTo(DEFAULT_RENDERING_PROVIDER_CONTACT_NO_1);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderContactNo2()).isEqualTo(DEFAULT_RENDERING_PROVIDER_CONTACT_NO_2);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderEfax()).isEqualTo(DEFAULT_RENDERING_PROVIDER_EFAX);
        assertThat(testSalesOrderClinicalDetails.getDiagnosisCodeType()).isEqualTo(DEFAULT_DIAGNOSIS_CODE_TYPE);
    }

    @Test
    void createSalesOrderClinicalDetailsWithExistingId() throws Exception {
        // Create the SalesOrderClinicalDetails with an existing ID
        salesOrderClinicalDetails.setSalesOrderClinicalDetailsId(1L);
        SalesOrderClinicalDetailsDTO salesOrderClinicalDetailsDTO = salesOrderClinicalDetailsMapper.toDto(salesOrderClinicalDetails);

        int databaseSizeBeforeCreate = salesOrderClinicalDetailsRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderClinicalDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderClinicalDetails in the database
        List<SalesOrderClinicalDetails> salesOrderClinicalDetailsList = salesOrderClinicalDetailsRepository.findAll().collectList().block();
        assertThat(salesOrderClinicalDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllSalesOrderClinicalDetails() {
        // Initialize the database
        salesOrderClinicalDetailsRepository.save(salesOrderClinicalDetails).block();

        // Get all the salesOrderClinicalDetailsList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=salesOrderClinicalDetailsId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].salesOrderClinicalDetailsId")
            .value(hasItem(salesOrderClinicalDetails.getSalesOrderClinicalDetailsId().intValue()))
            .jsonPath("$.[*].salesOrderId")
            .value(hasItem(DEFAULT_SALES_ORDER_ID.intValue()))
            .jsonPath("$.[*].patientId")
            .value(hasItem(DEFAULT_PATIENT_ID.intValue()))
            .jsonPath("$.[*].patientWeightInKg")
            .value(hasItem(DEFAULT_PATIENT_WEIGHT_IN_KG.doubleValue()))
            .jsonPath("$.[*].patientWeightInLbs")
            .value(hasItem(DEFAULT_PATIENT_WEIGHT_IN_LBS.doubleValue()))
            .jsonPath("$.[*].heightInInches")
            .value(hasItem(DEFAULT_HEIGHT_IN_INCHES.doubleValue()))
            .jsonPath("$.[*].heightInCm")
            .value(hasItem(DEFAULT_HEIGHT_IN_CM.doubleValue()))
            .jsonPath("$.[*].salesRepId")
            .value(hasItem(DEFAULT_SALES_REP_ID.intValue()))
            .jsonPath("$.[*].salesRepName")
            .value(hasItem(DEFAULT_SALES_REP_NAME))
            .jsonPath("$.[*].renderingProviderFacilityId")
            .value(hasItem(DEFAULT_RENDERING_PROVIDER_FACILITY_ID.intValue()))
            .jsonPath("$.[*].renderingProviderFacilityName")
            .value(hasItem(DEFAULT_RENDERING_PROVIDER_FACILITY_NAME))
            .jsonPath("$.[*].renderingProviderId")
            .value(hasItem(DEFAULT_RENDERING_PROVIDER_ID.intValue()))
            .jsonPath("$.[*].renderingProviderType")
            .value(hasItem(DEFAULT_RENDERING_PROVIDER_TYPE))
            .jsonPath("$.[*].renderingProviderFirstName")
            .value(hasItem(DEFAULT_RENDERING_PROVIDER_FIRST_NAME))
            .jsonPath("$.[*].renderingProviderMiddleName")
            .value(hasItem(DEFAULT_RENDERING_PROVIDER_MIDDLE_NAME))
            .jsonPath("$.[*].renderingProviderLastName")
            .value(hasItem(DEFAULT_RENDERING_PROVIDER_LAST_NAME))
            .jsonPath("$.[*].renderingProviderNpi")
            .value(hasItem(DEFAULT_RENDERING_PROVIDER_NPI))
            .jsonPath("$.[*].renderingProviderDea")
            .value(hasItem(DEFAULT_RENDERING_PROVIDER_DEA))
            .jsonPath("$.[*].renderingProviderAddressLine1")
            .value(hasItem(DEFAULT_RENDERING_PROVIDER_ADDRESS_LINE_1))
            .jsonPath("$.[*].renderingProviderAddressLine2")
            .value(hasItem(DEFAULT_RENDERING_PROVIDER_ADDRESS_LINE_2))
            .jsonPath("$.[*].renderingProviderEmail")
            .value(hasItem(DEFAULT_RENDERING_PROVIDER_EMAIL))
            .jsonPath("$.[*].renderingProviderFax")
            .value(hasItem(DEFAULT_RENDERING_PROVIDER_FAX))
            .jsonPath("$.[*].referringProviderFacilityId")
            .value(hasItem(DEFAULT_REFERRING_PROVIDER_FACILITY_ID.intValue()))
            .jsonPath("$.[*].referringProviderFacilityName")
            .value(hasItem(DEFAULT_REFERRING_PROVIDER_FACILITY_NAME))
            .jsonPath("$.[*].referringProviderId")
            .value(hasItem(DEFAULT_REFERRING_PROVIDER_ID.intValue()))
            .jsonPath("$.[*].referringProviderType")
            .value(hasItem(DEFAULT_REFERRING_PROVIDER_TYPE))
            .jsonPath("$.[*].referringProviderFirstName")
            .value(hasItem(DEFAULT_REFERRING_PROVIDER_FIRST_NAME))
            .jsonPath("$.[*].referringProviderMiddleName")
            .value(hasItem(DEFAULT_REFERRING_PROVIDER_MIDDLE_NAME))
            .jsonPath("$.[*].referringProviderLastName")
            .value(hasItem(DEFAULT_REFERRING_PROVIDER_LAST_NAME))
            .jsonPath("$.[*].referringProviderNpi")
            .value(hasItem(DEFAULT_REFERRING_PROVIDER_NPI))
            .jsonPath("$.[*].referringProviderDea")
            .value(hasItem(DEFAULT_REFERRING_PROVIDER_DEA))
            .jsonPath("$.[*].referringProviderAddressLine1")
            .value(hasItem(DEFAULT_REFERRING_PROVIDER_ADDRESS_LINE_1))
            .jsonPath("$.[*].referringProviderAddressLine2")
            .value(hasItem(DEFAULT_REFERRING_PROVIDER_ADDRESS_LINE_2))
            .jsonPath("$.[*].referringProviderEmail")
            .value(hasItem(DEFAULT_REFERRING_PROVIDER_EMAIL))
            .jsonPath("$.[*].referringProviderFax")
            .value(hasItem(DEFAULT_REFERRING_PROVIDER_FAX))
            .jsonPath("$.[*].orderingProviderFacilityId")
            .value(hasItem(DEFAULT_ORDERING_PROVIDER_FACILITY_ID.intValue()))
            .jsonPath("$.[*].orderingProviderFacilityName")
            .value(hasItem(DEFAULT_ORDERING_PROVIDER_FACILITY_NAME))
            .jsonPath("$.[*].orderingProviderId")
            .value(hasItem(DEFAULT_ORDERING_PROVIDER_ID.intValue()))
            .jsonPath("$.[*].orderingProviderType")
            .value(hasItem(DEFAULT_ORDERING_PROVIDER_TYPE))
            .jsonPath("$.[*].orderingProviderFirstName")
            .value(hasItem(DEFAULT_ORDERING_PROVIDER_FIRST_NAME))
            .jsonPath("$.[*].orderingProviderMiddleName")
            .value(hasItem(DEFAULT_ORDERING_PROVIDER_MIDDLE_NAME))
            .jsonPath("$.[*].orderingProviderLastName")
            .value(hasItem(DEFAULT_ORDERING_PROVIDER_LAST_NAME))
            .jsonPath("$.[*].orderingProviderNpi")
            .value(hasItem(DEFAULT_ORDERING_PROVIDER_NPI))
            .jsonPath("$.[*].orderingProviderDea")
            .value(hasItem(DEFAULT_ORDERING_PROVIDER_DEA))
            .jsonPath("$.[*].orderingProviderAddressLine1")
            .value(hasItem(DEFAULT_ORDERING_PROVIDER_ADDRESS_LINE_1))
            .jsonPath("$.[*].orderingProviderAddressLine2")
            .value(hasItem(DEFAULT_ORDERING_PROVIDER_ADDRESS_LINE_2))
            .jsonPath("$.[*].orderingProviderEmail")
            .value(hasItem(DEFAULT_ORDERING_PROVIDER_EMAIL))
            .jsonPath("$.[*].orderingProviderFax")
            .value(hasItem(DEFAULT_ORDERING_PROVIDER_FAX))
            .jsonPath("$.[*].marketingReferralTypeId")
            .value(hasItem(DEFAULT_MARKETING_REFERRAL_TYPE_ID.intValue()))
            .jsonPath("$.[*].marketingReferralTypeDescription")
            .value(hasItem(DEFAULT_MARKETING_REFERRAL_TYPE_DESCRIPTION))
            .jsonPath("$.[*].icd10DiagnosisCode1")
            .value(hasItem(DEFAULT_ICD_10_DIAGNOSIS_CODE_1))
            .jsonPath("$.[*].icd10DiagnosisCode2")
            .value(hasItem(DEFAULT_ICD_10_DIAGNOSIS_CODE_2))
            .jsonPath("$.[*].icd10DiagnosisCode3")
            .value(hasItem(DEFAULT_ICD_10_DIAGNOSIS_CODE_3))
            .jsonPath("$.[*].icd10DiagnosisCode4")
            .value(hasItem(DEFAULT_ICD_10_DIAGNOSIS_CODE_4))
            .jsonPath("$.[*].icd10DiagnosisCode5")
            .value(hasItem(DEFAULT_ICD_10_DIAGNOSIS_CODE_5))
            .jsonPath("$.[*].icd10DiagnosisCode6")
            .value(hasItem(DEFAULT_ICD_10_DIAGNOSIS_CODE_6))
            .jsonPath("$.[*].icd10DiagnosisCode7")
            .value(hasItem(DEFAULT_ICD_10_DIAGNOSIS_CODE_7))
            .jsonPath("$.[*].icd10DiagnosisCode8")
            .value(hasItem(DEFAULT_ICD_10_DIAGNOSIS_CODE_8))
            .jsonPath("$.[*].icd10DiagnosisCode9")
            .value(hasItem(DEFAULT_ICD_10_DIAGNOSIS_CODE_9))
            .jsonPath("$.[*].icd10DiagnosisCode10")
            .value(hasItem(DEFAULT_ICD_10_DIAGNOSIS_CODE_10))
            .jsonPath("$.[*].icd10DiagnosisCode11")
            .value(hasItem(DEFAULT_ICD_10_DIAGNOSIS_CODE_11))
            .jsonPath("$.[*].icd10DiagnosisCode12")
            .value(hasItem(DEFAULT_ICD_10_DIAGNOSIS_CODE_12))
            .jsonPath("$.[*].epsdtCertificationConditionIndicator")
            .value(hasItem(DEFAULT_EPSDT_CERTIFICATION_CONDITION_INDICATOR))
            .jsonPath("$.[*].epsdtCertificationCode")
            .value(hasItem(DEFAULT_EPSDT_CERTIFICATION_CODE))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS))
            .jsonPath("$.[*].createdByName")
            .value(hasItem(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.[*].createdDate")
            .value(hasItem(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.[*].updatedByName")
            .value(hasItem(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.[*].updatedDate")
            .value(hasItem(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.[*].renderingProviderZip")
            .value(hasItem(DEFAULT_RENDERING_PROVIDER_ZIP))
            .jsonPath("$.[*].referringProviderZip")
            .value(hasItem(DEFAULT_REFERRING_PROVIDER_ZIP))
            .jsonPath("$.[*].orderingProviderZip")
            .value(hasItem(DEFAULT_ORDERING_PROVIDER_ZIP))
            .jsonPath("$.[*].marketingReferralId")
            .value(hasItem(DEFAULT_MARKETING_REFERRAL_ID.intValue()))
            .jsonPath("$.[*].marketingReferralName")
            .value(hasItem(DEFAULT_MARKETING_REFERRAL_NAME))
            .jsonPath("$.[*].updatedById")
            .value(hasItem(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.[*].createdById")
            .value(hasItem(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.[*].salesOrderClinicalDetailsUuid")
            .value(hasItem(DEFAULT_SALES_ORDER_CLINICAL_DETAILS_UUID.toString()))
            .jsonPath("$.[*].primaryDiagnosis")
            .value(hasItem(DEFAULT_PRIMARY_DIAGNOSIS))
            .jsonPath("$.[*].orderingProviderCity")
            .value(hasItem(DEFAULT_ORDERING_PROVIDER_CITY))
            .jsonPath("$.[*].orderingProviderState")
            .value(hasItem(DEFAULT_ORDERING_PROVIDER_STATE))
            .jsonPath("$.[*].orderingProviderCountry")
            .value(hasItem(DEFAULT_ORDERING_PROVIDER_COUNTRY))
            .jsonPath("$.[*].orderingProviderContactNo1")
            .value(hasItem(DEFAULT_ORDERING_PROVIDER_CONTACT_NO_1))
            .jsonPath("$.[*].orderingProviderContactNo2")
            .value(hasItem(DEFAULT_ORDERING_PROVIDER_CONTACT_NO_2))
            .jsonPath("$.[*].orderingProviderEfax")
            .value(hasItem(DEFAULT_ORDERING_PROVIDER_EFAX))
            .jsonPath("$.[*].relationship")
            .value(hasItem(DEFAULT_RELATIONSHIP))
            .jsonPath("$.[*].modeOfContact")
            .value(hasItem(DEFAULT_MODE_OF_CONTACT))
            .jsonPath("$.[*].referringProviderCity")
            .value(hasItem(DEFAULT_REFERRING_PROVIDER_CITY))
            .jsonPath("$.[*].referringProviderState")
            .value(hasItem(DEFAULT_REFERRING_PROVIDER_STATE))
            .jsonPath("$.[*].referringProviderCountry")
            .value(hasItem(DEFAULT_REFERRING_PROVIDER_COUNTRY))
            .jsonPath("$.[*].referringProviderContactNo1")
            .value(hasItem(DEFAULT_REFERRING_PROVIDER_CONTACT_NO_1))
            .jsonPath("$.[*].referringProviderContactNo2")
            .value(hasItem(DEFAULT_REFERRING_PROVIDER_CONTACT_NO_2))
            .jsonPath("$.[*].referringProviderEfax")
            .value(hasItem(DEFAULT_REFERRING_PROVIDER_EFAX))
            .jsonPath("$.[*].renderingProviderCity")
            .value(hasItem(DEFAULT_RENDERING_PROVIDER_CITY))
            .jsonPath("$.[*].renderingProviderState")
            .value(hasItem(DEFAULT_RENDERING_PROVIDER_STATE))
            .jsonPath("$.[*].renderingProviderCountry")
            .value(hasItem(DEFAULT_RENDERING_PROVIDER_COUNTRY))
            .jsonPath("$.[*].renderingProviderContactNo1")
            .value(hasItem(DEFAULT_RENDERING_PROVIDER_CONTACT_NO_1))
            .jsonPath("$.[*].renderingProviderContactNo2")
            .value(hasItem(DEFAULT_RENDERING_PROVIDER_CONTACT_NO_2))
            .jsonPath("$.[*].renderingProviderEfax")
            .value(hasItem(DEFAULT_RENDERING_PROVIDER_EFAX))
            .jsonPath("$.[*].diagnosisCodeType")
            .value(hasItem(DEFAULT_DIAGNOSIS_CODE_TYPE));
    }

    @Test
    void getSalesOrderClinicalDetails() {
        // Initialize the database
        salesOrderClinicalDetailsRepository.save(salesOrderClinicalDetails).block();

        // Get the salesOrderClinicalDetails
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, salesOrderClinicalDetails.getSalesOrderClinicalDetailsId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.salesOrderClinicalDetailsId")
            .value(is(salesOrderClinicalDetails.getSalesOrderClinicalDetailsId().intValue()))
            .jsonPath("$.salesOrderId")
            .value(is(DEFAULT_SALES_ORDER_ID.intValue()))
            .jsonPath("$.patientId")
            .value(is(DEFAULT_PATIENT_ID.intValue()))
            .jsonPath("$.patientWeightInKg")
            .value(is(DEFAULT_PATIENT_WEIGHT_IN_KG.doubleValue()))
            .jsonPath("$.patientWeightInLbs")
            .value(is(DEFAULT_PATIENT_WEIGHT_IN_LBS.doubleValue()))
            .jsonPath("$.heightInInches")
            .value(is(DEFAULT_HEIGHT_IN_INCHES.doubleValue()))
            .jsonPath("$.heightInCm")
            .value(is(DEFAULT_HEIGHT_IN_CM.doubleValue()))
            .jsonPath("$.salesRepId")
            .value(is(DEFAULT_SALES_REP_ID.intValue()))
            .jsonPath("$.salesRepName")
            .value(is(DEFAULT_SALES_REP_NAME))
            .jsonPath("$.renderingProviderFacilityId")
            .value(is(DEFAULT_RENDERING_PROVIDER_FACILITY_ID.intValue()))
            .jsonPath("$.renderingProviderFacilityName")
            .value(is(DEFAULT_RENDERING_PROVIDER_FACILITY_NAME))
            .jsonPath("$.renderingProviderId")
            .value(is(DEFAULT_RENDERING_PROVIDER_ID.intValue()))
            .jsonPath("$.renderingProviderType")
            .value(is(DEFAULT_RENDERING_PROVIDER_TYPE))
            .jsonPath("$.renderingProviderFirstName")
            .value(is(DEFAULT_RENDERING_PROVIDER_FIRST_NAME))
            .jsonPath("$.renderingProviderMiddleName")
            .value(is(DEFAULT_RENDERING_PROVIDER_MIDDLE_NAME))
            .jsonPath("$.renderingProviderLastName")
            .value(is(DEFAULT_RENDERING_PROVIDER_LAST_NAME))
            .jsonPath("$.renderingProviderNpi")
            .value(is(DEFAULT_RENDERING_PROVIDER_NPI))
            .jsonPath("$.renderingProviderDea")
            .value(is(DEFAULT_RENDERING_PROVIDER_DEA))
            .jsonPath("$.renderingProviderAddressLine1")
            .value(is(DEFAULT_RENDERING_PROVIDER_ADDRESS_LINE_1))
            .jsonPath("$.renderingProviderAddressLine2")
            .value(is(DEFAULT_RENDERING_PROVIDER_ADDRESS_LINE_2))
            .jsonPath("$.renderingProviderEmail")
            .value(is(DEFAULT_RENDERING_PROVIDER_EMAIL))
            .jsonPath("$.renderingProviderFax")
            .value(is(DEFAULT_RENDERING_PROVIDER_FAX))
            .jsonPath("$.referringProviderFacilityId")
            .value(is(DEFAULT_REFERRING_PROVIDER_FACILITY_ID.intValue()))
            .jsonPath("$.referringProviderFacilityName")
            .value(is(DEFAULT_REFERRING_PROVIDER_FACILITY_NAME))
            .jsonPath("$.referringProviderId")
            .value(is(DEFAULT_REFERRING_PROVIDER_ID.intValue()))
            .jsonPath("$.referringProviderType")
            .value(is(DEFAULT_REFERRING_PROVIDER_TYPE))
            .jsonPath("$.referringProviderFirstName")
            .value(is(DEFAULT_REFERRING_PROVIDER_FIRST_NAME))
            .jsonPath("$.referringProviderMiddleName")
            .value(is(DEFAULT_REFERRING_PROVIDER_MIDDLE_NAME))
            .jsonPath("$.referringProviderLastName")
            .value(is(DEFAULT_REFERRING_PROVIDER_LAST_NAME))
            .jsonPath("$.referringProviderNpi")
            .value(is(DEFAULT_REFERRING_PROVIDER_NPI))
            .jsonPath("$.referringProviderDea")
            .value(is(DEFAULT_REFERRING_PROVIDER_DEA))
            .jsonPath("$.referringProviderAddressLine1")
            .value(is(DEFAULT_REFERRING_PROVIDER_ADDRESS_LINE_1))
            .jsonPath("$.referringProviderAddressLine2")
            .value(is(DEFAULT_REFERRING_PROVIDER_ADDRESS_LINE_2))
            .jsonPath("$.referringProviderEmail")
            .value(is(DEFAULT_REFERRING_PROVIDER_EMAIL))
            .jsonPath("$.referringProviderFax")
            .value(is(DEFAULT_REFERRING_PROVIDER_FAX))
            .jsonPath("$.orderingProviderFacilityId")
            .value(is(DEFAULT_ORDERING_PROVIDER_FACILITY_ID.intValue()))
            .jsonPath("$.orderingProviderFacilityName")
            .value(is(DEFAULT_ORDERING_PROVIDER_FACILITY_NAME))
            .jsonPath("$.orderingProviderId")
            .value(is(DEFAULT_ORDERING_PROVIDER_ID.intValue()))
            .jsonPath("$.orderingProviderType")
            .value(is(DEFAULT_ORDERING_PROVIDER_TYPE))
            .jsonPath("$.orderingProviderFirstName")
            .value(is(DEFAULT_ORDERING_PROVIDER_FIRST_NAME))
            .jsonPath("$.orderingProviderMiddleName")
            .value(is(DEFAULT_ORDERING_PROVIDER_MIDDLE_NAME))
            .jsonPath("$.orderingProviderLastName")
            .value(is(DEFAULT_ORDERING_PROVIDER_LAST_NAME))
            .jsonPath("$.orderingProviderNpi")
            .value(is(DEFAULT_ORDERING_PROVIDER_NPI))
            .jsonPath("$.orderingProviderDea")
            .value(is(DEFAULT_ORDERING_PROVIDER_DEA))
            .jsonPath("$.orderingProviderAddressLine1")
            .value(is(DEFAULT_ORDERING_PROVIDER_ADDRESS_LINE_1))
            .jsonPath("$.orderingProviderAddressLine2")
            .value(is(DEFAULT_ORDERING_PROVIDER_ADDRESS_LINE_2))
            .jsonPath("$.orderingProviderEmail")
            .value(is(DEFAULT_ORDERING_PROVIDER_EMAIL))
            .jsonPath("$.orderingProviderFax")
            .value(is(DEFAULT_ORDERING_PROVIDER_FAX))
            .jsonPath("$.marketingReferralTypeId")
            .value(is(DEFAULT_MARKETING_REFERRAL_TYPE_ID.intValue()))
            .jsonPath("$.marketingReferralTypeDescription")
            .value(is(DEFAULT_MARKETING_REFERRAL_TYPE_DESCRIPTION))
            .jsonPath("$.icd10DiagnosisCode1")
            .value(is(DEFAULT_ICD_10_DIAGNOSIS_CODE_1))
            .jsonPath("$.icd10DiagnosisCode2")
            .value(is(DEFAULT_ICD_10_DIAGNOSIS_CODE_2))
            .jsonPath("$.icd10DiagnosisCode3")
            .value(is(DEFAULT_ICD_10_DIAGNOSIS_CODE_3))
            .jsonPath("$.icd10DiagnosisCode4")
            .value(is(DEFAULT_ICD_10_DIAGNOSIS_CODE_4))
            .jsonPath("$.icd10DiagnosisCode5")
            .value(is(DEFAULT_ICD_10_DIAGNOSIS_CODE_5))
            .jsonPath("$.icd10DiagnosisCode6")
            .value(is(DEFAULT_ICD_10_DIAGNOSIS_CODE_6))
            .jsonPath("$.icd10DiagnosisCode7")
            .value(is(DEFAULT_ICD_10_DIAGNOSIS_CODE_7))
            .jsonPath("$.icd10DiagnosisCode8")
            .value(is(DEFAULT_ICD_10_DIAGNOSIS_CODE_8))
            .jsonPath("$.icd10DiagnosisCode9")
            .value(is(DEFAULT_ICD_10_DIAGNOSIS_CODE_9))
            .jsonPath("$.icd10DiagnosisCode10")
            .value(is(DEFAULT_ICD_10_DIAGNOSIS_CODE_10))
            .jsonPath("$.icd10DiagnosisCode11")
            .value(is(DEFAULT_ICD_10_DIAGNOSIS_CODE_11))
            .jsonPath("$.icd10DiagnosisCode12")
            .value(is(DEFAULT_ICD_10_DIAGNOSIS_CODE_12))
            .jsonPath("$.epsdtCertificationConditionIndicator")
            .value(is(DEFAULT_EPSDT_CERTIFICATION_CONDITION_INDICATOR))
            .jsonPath("$.epsdtCertificationCode")
            .value(is(DEFAULT_EPSDT_CERTIFICATION_CODE))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS))
            .jsonPath("$.createdByName")
            .value(is(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.createdDate")
            .value(is(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.updatedByName")
            .value(is(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.updatedDate")
            .value(is(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.renderingProviderZip")
            .value(is(DEFAULT_RENDERING_PROVIDER_ZIP))
            .jsonPath("$.referringProviderZip")
            .value(is(DEFAULT_REFERRING_PROVIDER_ZIP))
            .jsonPath("$.orderingProviderZip")
            .value(is(DEFAULT_ORDERING_PROVIDER_ZIP))
            .jsonPath("$.marketingReferralId")
            .value(is(DEFAULT_MARKETING_REFERRAL_ID.intValue()))
            .jsonPath("$.marketingReferralName")
            .value(is(DEFAULT_MARKETING_REFERRAL_NAME))
            .jsonPath("$.updatedById")
            .value(is(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.createdById")
            .value(is(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.salesOrderClinicalDetailsUuid")
            .value(is(DEFAULT_SALES_ORDER_CLINICAL_DETAILS_UUID.toString()))
            .jsonPath("$.primaryDiagnosis")
            .value(is(DEFAULT_PRIMARY_DIAGNOSIS))
            .jsonPath("$.orderingProviderCity")
            .value(is(DEFAULT_ORDERING_PROVIDER_CITY))
            .jsonPath("$.orderingProviderState")
            .value(is(DEFAULT_ORDERING_PROVIDER_STATE))
            .jsonPath("$.orderingProviderCountry")
            .value(is(DEFAULT_ORDERING_PROVIDER_COUNTRY))
            .jsonPath("$.orderingProviderContactNo1")
            .value(is(DEFAULT_ORDERING_PROVIDER_CONTACT_NO_1))
            .jsonPath("$.orderingProviderContactNo2")
            .value(is(DEFAULT_ORDERING_PROVIDER_CONTACT_NO_2))
            .jsonPath("$.orderingProviderEfax")
            .value(is(DEFAULT_ORDERING_PROVIDER_EFAX))
            .jsonPath("$.relationship")
            .value(is(DEFAULT_RELATIONSHIP))
            .jsonPath("$.modeOfContact")
            .value(is(DEFAULT_MODE_OF_CONTACT))
            .jsonPath("$.referringProviderCity")
            .value(is(DEFAULT_REFERRING_PROVIDER_CITY))
            .jsonPath("$.referringProviderState")
            .value(is(DEFAULT_REFERRING_PROVIDER_STATE))
            .jsonPath("$.referringProviderCountry")
            .value(is(DEFAULT_REFERRING_PROVIDER_COUNTRY))
            .jsonPath("$.referringProviderContactNo1")
            .value(is(DEFAULT_REFERRING_PROVIDER_CONTACT_NO_1))
            .jsonPath("$.referringProviderContactNo2")
            .value(is(DEFAULT_REFERRING_PROVIDER_CONTACT_NO_2))
            .jsonPath("$.referringProviderEfax")
            .value(is(DEFAULT_REFERRING_PROVIDER_EFAX))
            .jsonPath("$.renderingProviderCity")
            .value(is(DEFAULT_RENDERING_PROVIDER_CITY))
            .jsonPath("$.renderingProviderState")
            .value(is(DEFAULT_RENDERING_PROVIDER_STATE))
            .jsonPath("$.renderingProviderCountry")
            .value(is(DEFAULT_RENDERING_PROVIDER_COUNTRY))
            .jsonPath("$.renderingProviderContactNo1")
            .value(is(DEFAULT_RENDERING_PROVIDER_CONTACT_NO_1))
            .jsonPath("$.renderingProviderContactNo2")
            .value(is(DEFAULT_RENDERING_PROVIDER_CONTACT_NO_2))
            .jsonPath("$.renderingProviderEfax")
            .value(is(DEFAULT_RENDERING_PROVIDER_EFAX))
            .jsonPath("$.diagnosisCodeType")
            .value(is(DEFAULT_DIAGNOSIS_CODE_TYPE));
    }

    @Test
    void getNonExistingSalesOrderClinicalDetails() {
        // Get the salesOrderClinicalDetails
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingSalesOrderClinicalDetails() throws Exception {
        // Initialize the database
        salesOrderClinicalDetailsRepository.save(salesOrderClinicalDetails).block();

        int databaseSizeBeforeUpdate = salesOrderClinicalDetailsRepository.findAll().collectList().block().size();

        // Update the salesOrderClinicalDetails
        SalesOrderClinicalDetails updatedSalesOrderClinicalDetails = salesOrderClinicalDetailsRepository
            .findById(salesOrderClinicalDetails.getSalesOrderClinicalDetailsId())
            .block();
        updatedSalesOrderClinicalDetails
            .salesOrderId(UPDATED_SALES_ORDER_ID)
            .patientId(UPDATED_PATIENT_ID)
            .patientWeightInKg(UPDATED_PATIENT_WEIGHT_IN_KG)
            .patientWeightInLbs(UPDATED_PATIENT_WEIGHT_IN_LBS)
            .heightInInches(UPDATED_HEIGHT_IN_INCHES)
            .heightInCm(UPDATED_HEIGHT_IN_CM)
            .salesRepId(UPDATED_SALES_REP_ID)
            .salesRepName(UPDATED_SALES_REP_NAME)
            .renderingProviderFacilityId(UPDATED_RENDERING_PROVIDER_FACILITY_ID)
            .renderingProviderFacilityName(UPDATED_RENDERING_PROVIDER_FACILITY_NAME)
            .renderingProviderId(UPDATED_RENDERING_PROVIDER_ID)
            .renderingProviderType(UPDATED_RENDERING_PROVIDER_TYPE)
            .renderingProviderFirstName(UPDATED_RENDERING_PROVIDER_FIRST_NAME)
            .renderingProviderMiddleName(UPDATED_RENDERING_PROVIDER_MIDDLE_NAME)
            .renderingProviderLastName(UPDATED_RENDERING_PROVIDER_LAST_NAME)
            .renderingProviderNpi(UPDATED_RENDERING_PROVIDER_NPI)
            .renderingProviderDea(UPDATED_RENDERING_PROVIDER_DEA)
            .renderingProviderAddressLine1(UPDATED_RENDERING_PROVIDER_ADDRESS_LINE_1)
            .renderingProviderAddressLine2(UPDATED_RENDERING_PROVIDER_ADDRESS_LINE_2)
            .renderingProviderEmail(UPDATED_RENDERING_PROVIDER_EMAIL)
            .renderingProviderFax(UPDATED_RENDERING_PROVIDER_FAX)
            .referringProviderFacilityId(UPDATED_REFERRING_PROVIDER_FACILITY_ID)
            .referringProviderFacilityName(UPDATED_REFERRING_PROVIDER_FACILITY_NAME)
            .referringProviderId(UPDATED_REFERRING_PROVIDER_ID)
            .referringProviderType(UPDATED_REFERRING_PROVIDER_TYPE)
            .referringProviderFirstName(UPDATED_REFERRING_PROVIDER_FIRST_NAME)
            .referringProviderMiddleName(UPDATED_REFERRING_PROVIDER_MIDDLE_NAME)
            .referringProviderLastName(UPDATED_REFERRING_PROVIDER_LAST_NAME)
            .referringProviderNpi(UPDATED_REFERRING_PROVIDER_NPI)
            .referringProviderDea(UPDATED_REFERRING_PROVIDER_DEA)
            .referringProviderAddressLine1(UPDATED_REFERRING_PROVIDER_ADDRESS_LINE_1)
            .referringProviderAddressLine2(UPDATED_REFERRING_PROVIDER_ADDRESS_LINE_2)
            .referringProviderEmail(UPDATED_REFERRING_PROVIDER_EMAIL)
            .referringProviderFax(UPDATED_REFERRING_PROVIDER_FAX)
            .orderingProviderFacilityId(UPDATED_ORDERING_PROVIDER_FACILITY_ID)
            .orderingProviderFacilityName(UPDATED_ORDERING_PROVIDER_FACILITY_NAME)
            .orderingProviderId(UPDATED_ORDERING_PROVIDER_ID)
            .orderingProviderType(UPDATED_ORDERING_PROVIDER_TYPE)
            .orderingProviderFirstName(UPDATED_ORDERING_PROVIDER_FIRST_NAME)
            .orderingProviderMiddleName(UPDATED_ORDERING_PROVIDER_MIDDLE_NAME)
            .orderingProviderLastName(UPDATED_ORDERING_PROVIDER_LAST_NAME)
            .orderingProviderNpi(UPDATED_ORDERING_PROVIDER_NPI)
            .orderingProviderDea(UPDATED_ORDERING_PROVIDER_DEA)
            .orderingProviderAddressLine1(UPDATED_ORDERING_PROVIDER_ADDRESS_LINE_1)
            .orderingProviderAddressLine2(UPDATED_ORDERING_PROVIDER_ADDRESS_LINE_2)
            .orderingProviderEmail(UPDATED_ORDERING_PROVIDER_EMAIL)
            .orderingProviderFax(UPDATED_ORDERING_PROVIDER_FAX)
            .marketingReferralTypeId(UPDATED_MARKETING_REFERRAL_TYPE_ID)
            .marketingReferralTypeDescription(UPDATED_MARKETING_REFERRAL_TYPE_DESCRIPTION)
            .icd10DiagnosisCode1(UPDATED_ICD_10_DIAGNOSIS_CODE_1)
            .icd10DiagnosisCode2(UPDATED_ICD_10_DIAGNOSIS_CODE_2)
            .icd10DiagnosisCode3(UPDATED_ICD_10_DIAGNOSIS_CODE_3)
            .icd10DiagnosisCode4(UPDATED_ICD_10_DIAGNOSIS_CODE_4)
            .icd10DiagnosisCode5(UPDATED_ICD_10_DIAGNOSIS_CODE_5)
            .icd10DiagnosisCode6(UPDATED_ICD_10_DIAGNOSIS_CODE_6)
            .icd10DiagnosisCode7(UPDATED_ICD_10_DIAGNOSIS_CODE_7)
            .icd10DiagnosisCode8(UPDATED_ICD_10_DIAGNOSIS_CODE_8)
            .icd10DiagnosisCode9(UPDATED_ICD_10_DIAGNOSIS_CODE_9)
            .icd10DiagnosisCode10(UPDATED_ICD_10_DIAGNOSIS_CODE_10)
            .icd10DiagnosisCode11(UPDATED_ICD_10_DIAGNOSIS_CODE_11)
            .icd10DiagnosisCode12(UPDATED_ICD_10_DIAGNOSIS_CODE_12)
            .epsdtCertificationConditionIndicator(UPDATED_EPSDT_CERTIFICATION_CONDITION_INDICATOR)
            .epsdtCertificationCode(UPDATED_EPSDT_CERTIFICATION_CODE)
            .status(UPDATED_STATUS)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .renderingProviderZip(UPDATED_RENDERING_PROVIDER_ZIP)
            .referringProviderZip(UPDATED_REFERRING_PROVIDER_ZIP)
            .orderingProviderZip(UPDATED_ORDERING_PROVIDER_ZIP)
            .marketingReferralId(UPDATED_MARKETING_REFERRAL_ID)
            .marketingReferralName(UPDATED_MARKETING_REFERRAL_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .createdById(UPDATED_CREATED_BY_ID)
            .salesOrderClinicalDetailsUuid(UPDATED_SALES_ORDER_CLINICAL_DETAILS_UUID)
            .primaryDiagnosis(UPDATED_PRIMARY_DIAGNOSIS)
            .orderingProviderCity(UPDATED_ORDERING_PROVIDER_CITY)
            .orderingProviderState(UPDATED_ORDERING_PROVIDER_STATE)
            .orderingProviderCountry(UPDATED_ORDERING_PROVIDER_COUNTRY)
            .orderingProviderContactNo1(UPDATED_ORDERING_PROVIDER_CONTACT_NO_1)
            .orderingProviderContactNo2(UPDATED_ORDERING_PROVIDER_CONTACT_NO_2)
            .orderingProviderEfax(UPDATED_ORDERING_PROVIDER_EFAX)
            .relationship(UPDATED_RELATIONSHIP)
            .modeOfContact(UPDATED_MODE_OF_CONTACT)
            .referringProviderCity(UPDATED_REFERRING_PROVIDER_CITY)
            .referringProviderState(UPDATED_REFERRING_PROVIDER_STATE)
            .referringProviderCountry(UPDATED_REFERRING_PROVIDER_COUNTRY)
            .referringProviderContactNo1(UPDATED_REFERRING_PROVIDER_CONTACT_NO_1)
            .referringProviderContactNo2(UPDATED_REFERRING_PROVIDER_CONTACT_NO_2)
            .referringProviderEfax(UPDATED_REFERRING_PROVIDER_EFAX)
            .renderingProviderCity(UPDATED_RENDERING_PROVIDER_CITY)
            .renderingProviderState(UPDATED_RENDERING_PROVIDER_STATE)
            .renderingProviderCountry(UPDATED_RENDERING_PROVIDER_COUNTRY)
            .renderingProviderContactNo1(UPDATED_RENDERING_PROVIDER_CONTACT_NO_1)
            .renderingProviderContactNo2(UPDATED_RENDERING_PROVIDER_CONTACT_NO_2)
            .renderingProviderEfax(UPDATED_RENDERING_PROVIDER_EFAX)
            .diagnosisCodeType(UPDATED_DIAGNOSIS_CODE_TYPE);
        SalesOrderClinicalDetailsDTO salesOrderClinicalDetailsDTO = salesOrderClinicalDetailsMapper.toDto(updatedSalesOrderClinicalDetails);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, salesOrderClinicalDetailsDTO.getSalesOrderClinicalDetailsId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderClinicalDetailsDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SalesOrderClinicalDetails in the database
        List<SalesOrderClinicalDetails> salesOrderClinicalDetailsList = salesOrderClinicalDetailsRepository.findAll().collectList().block();
        assertThat(salesOrderClinicalDetailsList).hasSize(databaseSizeBeforeUpdate);
        SalesOrderClinicalDetails testSalesOrderClinicalDetails = salesOrderClinicalDetailsList.get(
            salesOrderClinicalDetailsList.size() - 1
        );
        assertThat(testSalesOrderClinicalDetails.getSalesOrderId()).isEqualTo(UPDATED_SALES_ORDER_ID);
        assertThat(testSalesOrderClinicalDetails.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testSalesOrderClinicalDetails.getPatientWeightInKg()).isEqualTo(UPDATED_PATIENT_WEIGHT_IN_KG);
        assertThat(testSalesOrderClinicalDetails.getPatientWeightInLbs()).isEqualTo(UPDATED_PATIENT_WEIGHT_IN_LBS);
        assertThat(testSalesOrderClinicalDetails.getHeightInInches()).isEqualTo(UPDATED_HEIGHT_IN_INCHES);
        assertThat(testSalesOrderClinicalDetails.getHeightInCm()).isEqualTo(UPDATED_HEIGHT_IN_CM);
        assertThat(testSalesOrderClinicalDetails.getSalesRepId()).isEqualTo(UPDATED_SALES_REP_ID);
        assertThat(testSalesOrderClinicalDetails.getSalesRepName()).isEqualTo(UPDATED_SALES_REP_NAME);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderFacilityId()).isEqualTo(UPDATED_RENDERING_PROVIDER_FACILITY_ID);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderFacilityName()).isEqualTo(UPDATED_RENDERING_PROVIDER_FACILITY_NAME);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderId()).isEqualTo(UPDATED_RENDERING_PROVIDER_ID);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderType()).isEqualTo(UPDATED_RENDERING_PROVIDER_TYPE);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderFirstName()).isEqualTo(UPDATED_RENDERING_PROVIDER_FIRST_NAME);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderMiddleName()).isEqualTo(UPDATED_RENDERING_PROVIDER_MIDDLE_NAME);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderLastName()).isEqualTo(UPDATED_RENDERING_PROVIDER_LAST_NAME);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderNpi()).isEqualTo(UPDATED_RENDERING_PROVIDER_NPI);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderDea()).isEqualTo(UPDATED_RENDERING_PROVIDER_DEA);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderAddressLine1()).isEqualTo(UPDATED_RENDERING_PROVIDER_ADDRESS_LINE_1);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderAddressLine2()).isEqualTo(UPDATED_RENDERING_PROVIDER_ADDRESS_LINE_2);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderEmail()).isEqualTo(UPDATED_RENDERING_PROVIDER_EMAIL);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderFax()).isEqualTo(UPDATED_RENDERING_PROVIDER_FAX);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderFacilityId()).isEqualTo(UPDATED_REFERRING_PROVIDER_FACILITY_ID);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderFacilityName()).isEqualTo(UPDATED_REFERRING_PROVIDER_FACILITY_NAME);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderId()).isEqualTo(UPDATED_REFERRING_PROVIDER_ID);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderType()).isEqualTo(UPDATED_REFERRING_PROVIDER_TYPE);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderFirstName()).isEqualTo(UPDATED_REFERRING_PROVIDER_FIRST_NAME);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderMiddleName()).isEqualTo(UPDATED_REFERRING_PROVIDER_MIDDLE_NAME);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderLastName()).isEqualTo(UPDATED_REFERRING_PROVIDER_LAST_NAME);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderNpi()).isEqualTo(UPDATED_REFERRING_PROVIDER_NPI);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderDea()).isEqualTo(UPDATED_REFERRING_PROVIDER_DEA);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderAddressLine1()).isEqualTo(UPDATED_REFERRING_PROVIDER_ADDRESS_LINE_1);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderAddressLine2()).isEqualTo(UPDATED_REFERRING_PROVIDER_ADDRESS_LINE_2);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderEmail()).isEqualTo(UPDATED_REFERRING_PROVIDER_EMAIL);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderFax()).isEqualTo(UPDATED_REFERRING_PROVIDER_FAX);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderFacilityId()).isEqualTo(UPDATED_ORDERING_PROVIDER_FACILITY_ID);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderFacilityName()).isEqualTo(UPDATED_ORDERING_PROVIDER_FACILITY_NAME);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderId()).isEqualTo(UPDATED_ORDERING_PROVIDER_ID);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderType()).isEqualTo(UPDATED_ORDERING_PROVIDER_TYPE);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderFirstName()).isEqualTo(UPDATED_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderMiddleName()).isEqualTo(UPDATED_ORDERING_PROVIDER_MIDDLE_NAME);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderLastName()).isEqualTo(UPDATED_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderNpi()).isEqualTo(UPDATED_ORDERING_PROVIDER_NPI);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderDea()).isEqualTo(UPDATED_ORDERING_PROVIDER_DEA);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderAddressLine1()).isEqualTo(UPDATED_ORDERING_PROVIDER_ADDRESS_LINE_1);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderAddressLine2()).isEqualTo(UPDATED_ORDERING_PROVIDER_ADDRESS_LINE_2);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderEmail()).isEqualTo(UPDATED_ORDERING_PROVIDER_EMAIL);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderFax()).isEqualTo(UPDATED_ORDERING_PROVIDER_FAX);
        assertThat(testSalesOrderClinicalDetails.getMarketingReferralTypeId()).isEqualTo(UPDATED_MARKETING_REFERRAL_TYPE_ID);
        assertThat(testSalesOrderClinicalDetails.getMarketingReferralTypeDescription())
            .isEqualTo(UPDATED_MARKETING_REFERRAL_TYPE_DESCRIPTION);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode1()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_1);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode2()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_2);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode3()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_3);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode4()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_4);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode5()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_5);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode6()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_6);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode7()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_7);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode8()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_8);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode9()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_9);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode10()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_10);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode11()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_11);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode12()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_12);
        assertThat(testSalesOrderClinicalDetails.getEpsdtCertificationConditionIndicator())
            .isEqualTo(UPDATED_EPSDT_CERTIFICATION_CONDITION_INDICATOR);
        assertThat(testSalesOrderClinicalDetails.getEpsdtCertificationCode()).isEqualTo(UPDATED_EPSDT_CERTIFICATION_CODE);
        assertThat(testSalesOrderClinicalDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSalesOrderClinicalDetails.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testSalesOrderClinicalDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSalesOrderClinicalDetails.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testSalesOrderClinicalDetails.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderZip()).isEqualTo(UPDATED_RENDERING_PROVIDER_ZIP);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderZip()).isEqualTo(UPDATED_REFERRING_PROVIDER_ZIP);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderZip()).isEqualTo(UPDATED_ORDERING_PROVIDER_ZIP);
        assertThat(testSalesOrderClinicalDetails.getMarketingReferralId()).isEqualTo(UPDATED_MARKETING_REFERRAL_ID);
        assertThat(testSalesOrderClinicalDetails.getMarketingReferralName()).isEqualTo(UPDATED_MARKETING_REFERRAL_NAME);
        assertThat(testSalesOrderClinicalDetails.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testSalesOrderClinicalDetails.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testSalesOrderClinicalDetails.getSalesOrderClinicalDetailsUuid()).isEqualTo(UPDATED_SALES_ORDER_CLINICAL_DETAILS_UUID);
        assertThat(testSalesOrderClinicalDetails.getPrimaryDiagnosis()).isEqualTo(UPDATED_PRIMARY_DIAGNOSIS);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderCity()).isEqualTo(UPDATED_ORDERING_PROVIDER_CITY);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderState()).isEqualTo(UPDATED_ORDERING_PROVIDER_STATE);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderCountry()).isEqualTo(UPDATED_ORDERING_PROVIDER_COUNTRY);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderContactNo1()).isEqualTo(UPDATED_ORDERING_PROVIDER_CONTACT_NO_1);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderContactNo2()).isEqualTo(UPDATED_ORDERING_PROVIDER_CONTACT_NO_2);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderEfax()).isEqualTo(UPDATED_ORDERING_PROVIDER_EFAX);
        assertThat(testSalesOrderClinicalDetails.getRelationship()).isEqualTo(UPDATED_RELATIONSHIP);
        assertThat(testSalesOrderClinicalDetails.getModeOfContact()).isEqualTo(UPDATED_MODE_OF_CONTACT);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderCity()).isEqualTo(UPDATED_REFERRING_PROVIDER_CITY);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderState()).isEqualTo(UPDATED_REFERRING_PROVIDER_STATE);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderCountry()).isEqualTo(UPDATED_REFERRING_PROVIDER_COUNTRY);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderContactNo1()).isEqualTo(UPDATED_REFERRING_PROVIDER_CONTACT_NO_1);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderContactNo2()).isEqualTo(UPDATED_REFERRING_PROVIDER_CONTACT_NO_2);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderEfax()).isEqualTo(UPDATED_REFERRING_PROVIDER_EFAX);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderCity()).isEqualTo(UPDATED_RENDERING_PROVIDER_CITY);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderState()).isEqualTo(UPDATED_RENDERING_PROVIDER_STATE);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderCountry()).isEqualTo(UPDATED_RENDERING_PROVIDER_COUNTRY);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderContactNo1()).isEqualTo(UPDATED_RENDERING_PROVIDER_CONTACT_NO_1);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderContactNo2()).isEqualTo(UPDATED_RENDERING_PROVIDER_CONTACT_NO_2);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderEfax()).isEqualTo(UPDATED_RENDERING_PROVIDER_EFAX);
        assertThat(testSalesOrderClinicalDetails.getDiagnosisCodeType()).isEqualTo(UPDATED_DIAGNOSIS_CODE_TYPE);
    }

    @Test
    void putNonExistingSalesOrderClinicalDetails() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderClinicalDetailsRepository.findAll().collectList().block().size();
        salesOrderClinicalDetails.setSalesOrderClinicalDetailsId(count.incrementAndGet());

        // Create the SalesOrderClinicalDetails
        SalesOrderClinicalDetailsDTO salesOrderClinicalDetailsDTO = salesOrderClinicalDetailsMapper.toDto(salesOrderClinicalDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, salesOrderClinicalDetailsDTO.getSalesOrderClinicalDetailsId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderClinicalDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderClinicalDetails in the database
        List<SalesOrderClinicalDetails> salesOrderClinicalDetailsList = salesOrderClinicalDetailsRepository.findAll().collectList().block();
        assertThat(salesOrderClinicalDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchSalesOrderClinicalDetails() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderClinicalDetailsRepository.findAll().collectList().block().size();
        salesOrderClinicalDetails.setSalesOrderClinicalDetailsId(count.incrementAndGet());

        // Create the SalesOrderClinicalDetails
        SalesOrderClinicalDetailsDTO salesOrderClinicalDetailsDTO = salesOrderClinicalDetailsMapper.toDto(salesOrderClinicalDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderClinicalDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderClinicalDetails in the database
        List<SalesOrderClinicalDetails> salesOrderClinicalDetailsList = salesOrderClinicalDetailsRepository.findAll().collectList().block();
        assertThat(salesOrderClinicalDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamSalesOrderClinicalDetails() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderClinicalDetailsRepository.findAll().collectList().block().size();
        salesOrderClinicalDetails.setSalesOrderClinicalDetailsId(count.incrementAndGet());

        // Create the SalesOrderClinicalDetails
        SalesOrderClinicalDetailsDTO salesOrderClinicalDetailsDTO = salesOrderClinicalDetailsMapper.toDto(salesOrderClinicalDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderClinicalDetailsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the SalesOrderClinicalDetails in the database
        List<SalesOrderClinicalDetails> salesOrderClinicalDetailsList = salesOrderClinicalDetailsRepository.findAll().collectList().block();
        assertThat(salesOrderClinicalDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateSalesOrderClinicalDetailsWithPatch() throws Exception {
        // Initialize the database
        salesOrderClinicalDetailsRepository.save(salesOrderClinicalDetails).block();

        int databaseSizeBeforeUpdate = salesOrderClinicalDetailsRepository.findAll().collectList().block().size();

        // Update the salesOrderClinicalDetails using partial update
        SalesOrderClinicalDetails partialUpdatedSalesOrderClinicalDetails = new SalesOrderClinicalDetails();
        partialUpdatedSalesOrderClinicalDetails.setSalesOrderClinicalDetailsId(salesOrderClinicalDetails.getSalesOrderClinicalDetailsId());

        partialUpdatedSalesOrderClinicalDetails
            .salesOrderId(UPDATED_SALES_ORDER_ID)
            .patientId(UPDATED_PATIENT_ID)
            .patientWeightInLbs(UPDATED_PATIENT_WEIGHT_IN_LBS)
            .heightInInches(UPDATED_HEIGHT_IN_INCHES)
            .heightInCm(UPDATED_HEIGHT_IN_CM)
            .salesRepName(UPDATED_SALES_REP_NAME)
            .renderingProviderFacilityName(UPDATED_RENDERING_PROVIDER_FACILITY_NAME)
            .renderingProviderId(UPDATED_RENDERING_PROVIDER_ID)
            .renderingProviderType(UPDATED_RENDERING_PROVIDER_TYPE)
            .renderingProviderAddressLine2(UPDATED_RENDERING_PROVIDER_ADDRESS_LINE_2)
            .renderingProviderEmail(UPDATED_RENDERING_PROVIDER_EMAIL)
            .renderingProviderFax(UPDATED_RENDERING_PROVIDER_FAX)
            .referringProviderFacilityId(UPDATED_REFERRING_PROVIDER_FACILITY_ID)
            .referringProviderFacilityName(UPDATED_REFERRING_PROVIDER_FACILITY_NAME)
            .referringProviderId(UPDATED_REFERRING_PROVIDER_ID)
            .referringProviderFirstName(UPDATED_REFERRING_PROVIDER_FIRST_NAME)
            .referringProviderNpi(UPDATED_REFERRING_PROVIDER_NPI)
            .referringProviderAddressLine2(UPDATED_REFERRING_PROVIDER_ADDRESS_LINE_2)
            .orderingProviderLastName(UPDATED_ORDERING_PROVIDER_LAST_NAME)
            .orderingProviderDea(UPDATED_ORDERING_PROVIDER_DEA)
            .orderingProviderAddressLine1(UPDATED_ORDERING_PROVIDER_ADDRESS_LINE_1)
            .orderingProviderAddressLine2(UPDATED_ORDERING_PROVIDER_ADDRESS_LINE_2)
            .orderingProviderEmail(UPDATED_ORDERING_PROVIDER_EMAIL)
            .marketingReferralTypeDescription(UPDATED_MARKETING_REFERRAL_TYPE_DESCRIPTION)
            .icd10DiagnosisCode1(UPDATED_ICD_10_DIAGNOSIS_CODE_1)
            .icd10DiagnosisCode4(UPDATED_ICD_10_DIAGNOSIS_CODE_4)
            .icd10DiagnosisCode5(UPDATED_ICD_10_DIAGNOSIS_CODE_5)
            .icd10DiagnosisCode7(UPDATED_ICD_10_DIAGNOSIS_CODE_7)
            .icd10DiagnosisCode8(UPDATED_ICD_10_DIAGNOSIS_CODE_8)
            .icd10DiagnosisCode11(UPDATED_ICD_10_DIAGNOSIS_CODE_11)
            .icd10DiagnosisCode12(UPDATED_ICD_10_DIAGNOSIS_CODE_12)
            .epsdtCertificationConditionIndicator(UPDATED_EPSDT_CERTIFICATION_CONDITION_INDICATOR)
            .status(UPDATED_STATUS)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .orderingProviderZip(UPDATED_ORDERING_PROVIDER_ZIP)
            .marketingReferralId(UPDATED_MARKETING_REFERRAL_ID)
            .marketingReferralName(UPDATED_MARKETING_REFERRAL_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .createdById(UPDATED_CREATED_BY_ID)
            .orderingProviderCity(UPDATED_ORDERING_PROVIDER_CITY)
            .orderingProviderCountry(UPDATED_ORDERING_PROVIDER_COUNTRY)
            .referringProviderState(UPDATED_REFERRING_PROVIDER_STATE)
            .referringProviderCountry(UPDATED_REFERRING_PROVIDER_COUNTRY)
            .referringProviderEfax(UPDATED_REFERRING_PROVIDER_EFAX)
            .renderingProviderCity(UPDATED_RENDERING_PROVIDER_CITY)
            .renderingProviderContactNo1(UPDATED_RENDERING_PROVIDER_CONTACT_NO_1)
            .diagnosisCodeType(UPDATED_DIAGNOSIS_CODE_TYPE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedSalesOrderClinicalDetails.getSalesOrderClinicalDetailsId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedSalesOrderClinicalDetails))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SalesOrderClinicalDetails in the database
        List<SalesOrderClinicalDetails> salesOrderClinicalDetailsList = salesOrderClinicalDetailsRepository.findAll().collectList().block();
        assertThat(salesOrderClinicalDetailsList).hasSize(databaseSizeBeforeUpdate);
        SalesOrderClinicalDetails testSalesOrderClinicalDetails = salesOrderClinicalDetailsList.get(
            salesOrderClinicalDetailsList.size() - 1
        );
        assertThat(testSalesOrderClinicalDetails.getSalesOrderId()).isEqualTo(UPDATED_SALES_ORDER_ID);
        assertThat(testSalesOrderClinicalDetails.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testSalesOrderClinicalDetails.getPatientWeightInKg()).isEqualTo(DEFAULT_PATIENT_WEIGHT_IN_KG);
        assertThat(testSalesOrderClinicalDetails.getPatientWeightInLbs()).isEqualTo(UPDATED_PATIENT_WEIGHT_IN_LBS);
        assertThat(testSalesOrderClinicalDetails.getHeightInInches()).isEqualTo(UPDATED_HEIGHT_IN_INCHES);
        assertThat(testSalesOrderClinicalDetails.getHeightInCm()).isEqualTo(UPDATED_HEIGHT_IN_CM);
        assertThat(testSalesOrderClinicalDetails.getSalesRepId()).isEqualTo(DEFAULT_SALES_REP_ID);
        assertThat(testSalesOrderClinicalDetails.getSalesRepName()).isEqualTo(UPDATED_SALES_REP_NAME);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderFacilityId()).isEqualTo(DEFAULT_RENDERING_PROVIDER_FACILITY_ID);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderFacilityName()).isEqualTo(UPDATED_RENDERING_PROVIDER_FACILITY_NAME);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderId()).isEqualTo(UPDATED_RENDERING_PROVIDER_ID);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderType()).isEqualTo(UPDATED_RENDERING_PROVIDER_TYPE);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderFirstName()).isEqualTo(DEFAULT_RENDERING_PROVIDER_FIRST_NAME);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderMiddleName()).isEqualTo(DEFAULT_RENDERING_PROVIDER_MIDDLE_NAME);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderLastName()).isEqualTo(DEFAULT_RENDERING_PROVIDER_LAST_NAME);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderNpi()).isEqualTo(DEFAULT_RENDERING_PROVIDER_NPI);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderDea()).isEqualTo(DEFAULT_RENDERING_PROVIDER_DEA);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderAddressLine1()).isEqualTo(DEFAULT_RENDERING_PROVIDER_ADDRESS_LINE_1);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderAddressLine2()).isEqualTo(UPDATED_RENDERING_PROVIDER_ADDRESS_LINE_2);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderEmail()).isEqualTo(UPDATED_RENDERING_PROVIDER_EMAIL);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderFax()).isEqualTo(UPDATED_RENDERING_PROVIDER_FAX);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderFacilityId()).isEqualTo(UPDATED_REFERRING_PROVIDER_FACILITY_ID);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderFacilityName()).isEqualTo(UPDATED_REFERRING_PROVIDER_FACILITY_NAME);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderId()).isEqualTo(UPDATED_REFERRING_PROVIDER_ID);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderType()).isEqualTo(DEFAULT_REFERRING_PROVIDER_TYPE);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderFirstName()).isEqualTo(UPDATED_REFERRING_PROVIDER_FIRST_NAME);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderMiddleName()).isEqualTo(DEFAULT_REFERRING_PROVIDER_MIDDLE_NAME);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderLastName()).isEqualTo(DEFAULT_REFERRING_PROVIDER_LAST_NAME);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderNpi()).isEqualTo(UPDATED_REFERRING_PROVIDER_NPI);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderDea()).isEqualTo(DEFAULT_REFERRING_PROVIDER_DEA);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderAddressLine1()).isEqualTo(DEFAULT_REFERRING_PROVIDER_ADDRESS_LINE_1);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderAddressLine2()).isEqualTo(UPDATED_REFERRING_PROVIDER_ADDRESS_LINE_2);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderEmail()).isEqualTo(DEFAULT_REFERRING_PROVIDER_EMAIL);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderFax()).isEqualTo(DEFAULT_REFERRING_PROVIDER_FAX);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderFacilityId()).isEqualTo(DEFAULT_ORDERING_PROVIDER_FACILITY_ID);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderFacilityName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_FACILITY_NAME);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderId()).isEqualTo(DEFAULT_ORDERING_PROVIDER_ID);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderType()).isEqualTo(DEFAULT_ORDERING_PROVIDER_TYPE);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderFirstName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderMiddleName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_MIDDLE_NAME);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderLastName()).isEqualTo(UPDATED_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderNpi()).isEqualTo(DEFAULT_ORDERING_PROVIDER_NPI);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderDea()).isEqualTo(UPDATED_ORDERING_PROVIDER_DEA);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderAddressLine1()).isEqualTo(UPDATED_ORDERING_PROVIDER_ADDRESS_LINE_1);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderAddressLine2()).isEqualTo(UPDATED_ORDERING_PROVIDER_ADDRESS_LINE_2);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderEmail()).isEqualTo(UPDATED_ORDERING_PROVIDER_EMAIL);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderFax()).isEqualTo(DEFAULT_ORDERING_PROVIDER_FAX);
        assertThat(testSalesOrderClinicalDetails.getMarketingReferralTypeId()).isEqualTo(DEFAULT_MARKETING_REFERRAL_TYPE_ID);
        assertThat(testSalesOrderClinicalDetails.getMarketingReferralTypeDescription())
            .isEqualTo(UPDATED_MARKETING_REFERRAL_TYPE_DESCRIPTION);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode1()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_1);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode2()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_2);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode3()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_3);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode4()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_4);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode5()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_5);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode6()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_6);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode7()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_7);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode8()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_8);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode9()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_9);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode10()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_10);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode11()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_11);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode12()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_12);
        assertThat(testSalesOrderClinicalDetails.getEpsdtCertificationConditionIndicator())
            .isEqualTo(UPDATED_EPSDT_CERTIFICATION_CONDITION_INDICATOR);
        assertThat(testSalesOrderClinicalDetails.getEpsdtCertificationCode()).isEqualTo(DEFAULT_EPSDT_CERTIFICATION_CODE);
        assertThat(testSalesOrderClinicalDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSalesOrderClinicalDetails.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testSalesOrderClinicalDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSalesOrderClinicalDetails.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testSalesOrderClinicalDetails.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderZip()).isEqualTo(DEFAULT_RENDERING_PROVIDER_ZIP);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderZip()).isEqualTo(DEFAULT_REFERRING_PROVIDER_ZIP);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderZip()).isEqualTo(UPDATED_ORDERING_PROVIDER_ZIP);
        assertThat(testSalesOrderClinicalDetails.getMarketingReferralId()).isEqualTo(UPDATED_MARKETING_REFERRAL_ID);
        assertThat(testSalesOrderClinicalDetails.getMarketingReferralName()).isEqualTo(UPDATED_MARKETING_REFERRAL_NAME);
        assertThat(testSalesOrderClinicalDetails.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testSalesOrderClinicalDetails.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testSalesOrderClinicalDetails.getSalesOrderClinicalDetailsUuid()).isEqualTo(DEFAULT_SALES_ORDER_CLINICAL_DETAILS_UUID);
        assertThat(testSalesOrderClinicalDetails.getPrimaryDiagnosis()).isEqualTo(DEFAULT_PRIMARY_DIAGNOSIS);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderCity()).isEqualTo(UPDATED_ORDERING_PROVIDER_CITY);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderState()).isEqualTo(DEFAULT_ORDERING_PROVIDER_STATE);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderCountry()).isEqualTo(UPDATED_ORDERING_PROVIDER_COUNTRY);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderContactNo1()).isEqualTo(DEFAULT_ORDERING_PROVIDER_CONTACT_NO_1);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderContactNo2()).isEqualTo(DEFAULT_ORDERING_PROVIDER_CONTACT_NO_2);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderEfax()).isEqualTo(DEFAULT_ORDERING_PROVIDER_EFAX);
        assertThat(testSalesOrderClinicalDetails.getRelationship()).isEqualTo(DEFAULT_RELATIONSHIP);
        assertThat(testSalesOrderClinicalDetails.getModeOfContact()).isEqualTo(DEFAULT_MODE_OF_CONTACT);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderCity()).isEqualTo(DEFAULT_REFERRING_PROVIDER_CITY);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderState()).isEqualTo(UPDATED_REFERRING_PROVIDER_STATE);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderCountry()).isEqualTo(UPDATED_REFERRING_PROVIDER_COUNTRY);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderContactNo1()).isEqualTo(DEFAULT_REFERRING_PROVIDER_CONTACT_NO_1);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderContactNo2()).isEqualTo(DEFAULT_REFERRING_PROVIDER_CONTACT_NO_2);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderEfax()).isEqualTo(UPDATED_REFERRING_PROVIDER_EFAX);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderCity()).isEqualTo(UPDATED_RENDERING_PROVIDER_CITY);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderState()).isEqualTo(DEFAULT_RENDERING_PROVIDER_STATE);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderCountry()).isEqualTo(DEFAULT_RENDERING_PROVIDER_COUNTRY);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderContactNo1()).isEqualTo(UPDATED_RENDERING_PROVIDER_CONTACT_NO_1);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderContactNo2()).isEqualTo(DEFAULT_RENDERING_PROVIDER_CONTACT_NO_2);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderEfax()).isEqualTo(DEFAULT_RENDERING_PROVIDER_EFAX);
        assertThat(testSalesOrderClinicalDetails.getDiagnosisCodeType()).isEqualTo(UPDATED_DIAGNOSIS_CODE_TYPE);
    }

    @Test
    void fullUpdateSalesOrderClinicalDetailsWithPatch() throws Exception {
        // Initialize the database
        salesOrderClinicalDetailsRepository.save(salesOrderClinicalDetails).block();

        int databaseSizeBeforeUpdate = salesOrderClinicalDetailsRepository.findAll().collectList().block().size();

        // Update the salesOrderClinicalDetails using partial update
        SalesOrderClinicalDetails partialUpdatedSalesOrderClinicalDetails = new SalesOrderClinicalDetails();
        partialUpdatedSalesOrderClinicalDetails.setSalesOrderClinicalDetailsId(salesOrderClinicalDetails.getSalesOrderClinicalDetailsId());

        partialUpdatedSalesOrderClinicalDetails
            .salesOrderId(UPDATED_SALES_ORDER_ID)
            .patientId(UPDATED_PATIENT_ID)
            .patientWeightInKg(UPDATED_PATIENT_WEIGHT_IN_KG)
            .patientWeightInLbs(UPDATED_PATIENT_WEIGHT_IN_LBS)
            .heightInInches(UPDATED_HEIGHT_IN_INCHES)
            .heightInCm(UPDATED_HEIGHT_IN_CM)
            .salesRepId(UPDATED_SALES_REP_ID)
            .salesRepName(UPDATED_SALES_REP_NAME)
            .renderingProviderFacilityId(UPDATED_RENDERING_PROVIDER_FACILITY_ID)
            .renderingProviderFacilityName(UPDATED_RENDERING_PROVIDER_FACILITY_NAME)
            .renderingProviderId(UPDATED_RENDERING_PROVIDER_ID)
            .renderingProviderType(UPDATED_RENDERING_PROVIDER_TYPE)
            .renderingProviderFirstName(UPDATED_RENDERING_PROVIDER_FIRST_NAME)
            .renderingProviderMiddleName(UPDATED_RENDERING_PROVIDER_MIDDLE_NAME)
            .renderingProviderLastName(UPDATED_RENDERING_PROVIDER_LAST_NAME)
            .renderingProviderNpi(UPDATED_RENDERING_PROVIDER_NPI)
            .renderingProviderDea(UPDATED_RENDERING_PROVIDER_DEA)
            .renderingProviderAddressLine1(UPDATED_RENDERING_PROVIDER_ADDRESS_LINE_1)
            .renderingProviderAddressLine2(UPDATED_RENDERING_PROVIDER_ADDRESS_LINE_2)
            .renderingProviderEmail(UPDATED_RENDERING_PROVIDER_EMAIL)
            .renderingProviderFax(UPDATED_RENDERING_PROVIDER_FAX)
            .referringProviderFacilityId(UPDATED_REFERRING_PROVIDER_FACILITY_ID)
            .referringProviderFacilityName(UPDATED_REFERRING_PROVIDER_FACILITY_NAME)
            .referringProviderId(UPDATED_REFERRING_PROVIDER_ID)
            .referringProviderType(UPDATED_REFERRING_PROVIDER_TYPE)
            .referringProviderFirstName(UPDATED_REFERRING_PROVIDER_FIRST_NAME)
            .referringProviderMiddleName(UPDATED_REFERRING_PROVIDER_MIDDLE_NAME)
            .referringProviderLastName(UPDATED_REFERRING_PROVIDER_LAST_NAME)
            .referringProviderNpi(UPDATED_REFERRING_PROVIDER_NPI)
            .referringProviderDea(UPDATED_REFERRING_PROVIDER_DEA)
            .referringProviderAddressLine1(UPDATED_REFERRING_PROVIDER_ADDRESS_LINE_1)
            .referringProviderAddressLine2(UPDATED_REFERRING_PROVIDER_ADDRESS_LINE_2)
            .referringProviderEmail(UPDATED_REFERRING_PROVIDER_EMAIL)
            .referringProviderFax(UPDATED_REFERRING_PROVIDER_FAX)
            .orderingProviderFacilityId(UPDATED_ORDERING_PROVIDER_FACILITY_ID)
            .orderingProviderFacilityName(UPDATED_ORDERING_PROVIDER_FACILITY_NAME)
            .orderingProviderId(UPDATED_ORDERING_PROVIDER_ID)
            .orderingProviderType(UPDATED_ORDERING_PROVIDER_TYPE)
            .orderingProviderFirstName(UPDATED_ORDERING_PROVIDER_FIRST_NAME)
            .orderingProviderMiddleName(UPDATED_ORDERING_PROVIDER_MIDDLE_NAME)
            .orderingProviderLastName(UPDATED_ORDERING_PROVIDER_LAST_NAME)
            .orderingProviderNpi(UPDATED_ORDERING_PROVIDER_NPI)
            .orderingProviderDea(UPDATED_ORDERING_PROVIDER_DEA)
            .orderingProviderAddressLine1(UPDATED_ORDERING_PROVIDER_ADDRESS_LINE_1)
            .orderingProviderAddressLine2(UPDATED_ORDERING_PROVIDER_ADDRESS_LINE_2)
            .orderingProviderEmail(UPDATED_ORDERING_PROVIDER_EMAIL)
            .orderingProviderFax(UPDATED_ORDERING_PROVIDER_FAX)
            .marketingReferralTypeId(UPDATED_MARKETING_REFERRAL_TYPE_ID)
            .marketingReferralTypeDescription(UPDATED_MARKETING_REFERRAL_TYPE_DESCRIPTION)
            .icd10DiagnosisCode1(UPDATED_ICD_10_DIAGNOSIS_CODE_1)
            .icd10DiagnosisCode2(UPDATED_ICD_10_DIAGNOSIS_CODE_2)
            .icd10DiagnosisCode3(UPDATED_ICD_10_DIAGNOSIS_CODE_3)
            .icd10DiagnosisCode4(UPDATED_ICD_10_DIAGNOSIS_CODE_4)
            .icd10DiagnosisCode5(UPDATED_ICD_10_DIAGNOSIS_CODE_5)
            .icd10DiagnosisCode6(UPDATED_ICD_10_DIAGNOSIS_CODE_6)
            .icd10DiagnosisCode7(UPDATED_ICD_10_DIAGNOSIS_CODE_7)
            .icd10DiagnosisCode8(UPDATED_ICD_10_DIAGNOSIS_CODE_8)
            .icd10DiagnosisCode9(UPDATED_ICD_10_DIAGNOSIS_CODE_9)
            .icd10DiagnosisCode10(UPDATED_ICD_10_DIAGNOSIS_CODE_10)
            .icd10DiagnosisCode11(UPDATED_ICD_10_DIAGNOSIS_CODE_11)
            .icd10DiagnosisCode12(UPDATED_ICD_10_DIAGNOSIS_CODE_12)
            .epsdtCertificationConditionIndicator(UPDATED_EPSDT_CERTIFICATION_CONDITION_INDICATOR)
            .epsdtCertificationCode(UPDATED_EPSDT_CERTIFICATION_CODE)
            .status(UPDATED_STATUS)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .renderingProviderZip(UPDATED_RENDERING_PROVIDER_ZIP)
            .referringProviderZip(UPDATED_REFERRING_PROVIDER_ZIP)
            .orderingProviderZip(UPDATED_ORDERING_PROVIDER_ZIP)
            .marketingReferralId(UPDATED_MARKETING_REFERRAL_ID)
            .marketingReferralName(UPDATED_MARKETING_REFERRAL_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .createdById(UPDATED_CREATED_BY_ID)
            .salesOrderClinicalDetailsUuid(UPDATED_SALES_ORDER_CLINICAL_DETAILS_UUID)
            .primaryDiagnosis(UPDATED_PRIMARY_DIAGNOSIS)
            .orderingProviderCity(UPDATED_ORDERING_PROVIDER_CITY)
            .orderingProviderState(UPDATED_ORDERING_PROVIDER_STATE)
            .orderingProviderCountry(UPDATED_ORDERING_PROVIDER_COUNTRY)
            .orderingProviderContactNo1(UPDATED_ORDERING_PROVIDER_CONTACT_NO_1)
            .orderingProviderContactNo2(UPDATED_ORDERING_PROVIDER_CONTACT_NO_2)
            .orderingProviderEfax(UPDATED_ORDERING_PROVIDER_EFAX)
            .relationship(UPDATED_RELATIONSHIP)
            .modeOfContact(UPDATED_MODE_OF_CONTACT)
            .referringProviderCity(UPDATED_REFERRING_PROVIDER_CITY)
            .referringProviderState(UPDATED_REFERRING_PROVIDER_STATE)
            .referringProviderCountry(UPDATED_REFERRING_PROVIDER_COUNTRY)
            .referringProviderContactNo1(UPDATED_REFERRING_PROVIDER_CONTACT_NO_1)
            .referringProviderContactNo2(UPDATED_REFERRING_PROVIDER_CONTACT_NO_2)
            .referringProviderEfax(UPDATED_REFERRING_PROVIDER_EFAX)
            .renderingProviderCity(UPDATED_RENDERING_PROVIDER_CITY)
            .renderingProviderState(UPDATED_RENDERING_PROVIDER_STATE)
            .renderingProviderCountry(UPDATED_RENDERING_PROVIDER_COUNTRY)
            .renderingProviderContactNo1(UPDATED_RENDERING_PROVIDER_CONTACT_NO_1)
            .renderingProviderContactNo2(UPDATED_RENDERING_PROVIDER_CONTACT_NO_2)
            .renderingProviderEfax(UPDATED_RENDERING_PROVIDER_EFAX)
            .diagnosisCodeType(UPDATED_DIAGNOSIS_CODE_TYPE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedSalesOrderClinicalDetails.getSalesOrderClinicalDetailsId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedSalesOrderClinicalDetails))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SalesOrderClinicalDetails in the database
        List<SalesOrderClinicalDetails> salesOrderClinicalDetailsList = salesOrderClinicalDetailsRepository.findAll().collectList().block();
        assertThat(salesOrderClinicalDetailsList).hasSize(databaseSizeBeforeUpdate);
        SalesOrderClinicalDetails testSalesOrderClinicalDetails = salesOrderClinicalDetailsList.get(
            salesOrderClinicalDetailsList.size() - 1
        );
        assertThat(testSalesOrderClinicalDetails.getSalesOrderId()).isEqualTo(UPDATED_SALES_ORDER_ID);
        assertThat(testSalesOrderClinicalDetails.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testSalesOrderClinicalDetails.getPatientWeightInKg()).isEqualTo(UPDATED_PATIENT_WEIGHT_IN_KG);
        assertThat(testSalesOrderClinicalDetails.getPatientWeightInLbs()).isEqualTo(UPDATED_PATIENT_WEIGHT_IN_LBS);
        assertThat(testSalesOrderClinicalDetails.getHeightInInches()).isEqualTo(UPDATED_HEIGHT_IN_INCHES);
        assertThat(testSalesOrderClinicalDetails.getHeightInCm()).isEqualTo(UPDATED_HEIGHT_IN_CM);
        assertThat(testSalesOrderClinicalDetails.getSalesRepId()).isEqualTo(UPDATED_SALES_REP_ID);
        assertThat(testSalesOrderClinicalDetails.getSalesRepName()).isEqualTo(UPDATED_SALES_REP_NAME);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderFacilityId()).isEqualTo(UPDATED_RENDERING_PROVIDER_FACILITY_ID);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderFacilityName()).isEqualTo(UPDATED_RENDERING_PROVIDER_FACILITY_NAME);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderId()).isEqualTo(UPDATED_RENDERING_PROVIDER_ID);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderType()).isEqualTo(UPDATED_RENDERING_PROVIDER_TYPE);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderFirstName()).isEqualTo(UPDATED_RENDERING_PROVIDER_FIRST_NAME);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderMiddleName()).isEqualTo(UPDATED_RENDERING_PROVIDER_MIDDLE_NAME);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderLastName()).isEqualTo(UPDATED_RENDERING_PROVIDER_LAST_NAME);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderNpi()).isEqualTo(UPDATED_RENDERING_PROVIDER_NPI);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderDea()).isEqualTo(UPDATED_RENDERING_PROVIDER_DEA);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderAddressLine1()).isEqualTo(UPDATED_RENDERING_PROVIDER_ADDRESS_LINE_1);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderAddressLine2()).isEqualTo(UPDATED_RENDERING_PROVIDER_ADDRESS_LINE_2);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderEmail()).isEqualTo(UPDATED_RENDERING_PROVIDER_EMAIL);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderFax()).isEqualTo(UPDATED_RENDERING_PROVIDER_FAX);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderFacilityId()).isEqualTo(UPDATED_REFERRING_PROVIDER_FACILITY_ID);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderFacilityName()).isEqualTo(UPDATED_REFERRING_PROVIDER_FACILITY_NAME);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderId()).isEqualTo(UPDATED_REFERRING_PROVIDER_ID);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderType()).isEqualTo(UPDATED_REFERRING_PROVIDER_TYPE);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderFirstName()).isEqualTo(UPDATED_REFERRING_PROVIDER_FIRST_NAME);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderMiddleName()).isEqualTo(UPDATED_REFERRING_PROVIDER_MIDDLE_NAME);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderLastName()).isEqualTo(UPDATED_REFERRING_PROVIDER_LAST_NAME);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderNpi()).isEqualTo(UPDATED_REFERRING_PROVIDER_NPI);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderDea()).isEqualTo(UPDATED_REFERRING_PROVIDER_DEA);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderAddressLine1()).isEqualTo(UPDATED_REFERRING_PROVIDER_ADDRESS_LINE_1);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderAddressLine2()).isEqualTo(UPDATED_REFERRING_PROVIDER_ADDRESS_LINE_2);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderEmail()).isEqualTo(UPDATED_REFERRING_PROVIDER_EMAIL);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderFax()).isEqualTo(UPDATED_REFERRING_PROVIDER_FAX);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderFacilityId()).isEqualTo(UPDATED_ORDERING_PROVIDER_FACILITY_ID);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderFacilityName()).isEqualTo(UPDATED_ORDERING_PROVIDER_FACILITY_NAME);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderId()).isEqualTo(UPDATED_ORDERING_PROVIDER_ID);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderType()).isEqualTo(UPDATED_ORDERING_PROVIDER_TYPE);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderFirstName()).isEqualTo(UPDATED_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderMiddleName()).isEqualTo(UPDATED_ORDERING_PROVIDER_MIDDLE_NAME);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderLastName()).isEqualTo(UPDATED_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderNpi()).isEqualTo(UPDATED_ORDERING_PROVIDER_NPI);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderDea()).isEqualTo(UPDATED_ORDERING_PROVIDER_DEA);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderAddressLine1()).isEqualTo(UPDATED_ORDERING_PROVIDER_ADDRESS_LINE_1);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderAddressLine2()).isEqualTo(UPDATED_ORDERING_PROVIDER_ADDRESS_LINE_2);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderEmail()).isEqualTo(UPDATED_ORDERING_PROVIDER_EMAIL);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderFax()).isEqualTo(UPDATED_ORDERING_PROVIDER_FAX);
        assertThat(testSalesOrderClinicalDetails.getMarketingReferralTypeId()).isEqualTo(UPDATED_MARKETING_REFERRAL_TYPE_ID);
        assertThat(testSalesOrderClinicalDetails.getMarketingReferralTypeDescription())
            .isEqualTo(UPDATED_MARKETING_REFERRAL_TYPE_DESCRIPTION);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode1()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_1);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode2()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_2);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode3()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_3);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode4()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_4);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode5()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_5);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode6()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_6);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode7()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_7);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode8()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_8);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode9()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_9);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode10()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_10);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode11()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_11);
        assertThat(testSalesOrderClinicalDetails.getIcd10DiagnosisCode12()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_12);
        assertThat(testSalesOrderClinicalDetails.getEpsdtCertificationConditionIndicator())
            .isEqualTo(UPDATED_EPSDT_CERTIFICATION_CONDITION_INDICATOR);
        assertThat(testSalesOrderClinicalDetails.getEpsdtCertificationCode()).isEqualTo(UPDATED_EPSDT_CERTIFICATION_CODE);
        assertThat(testSalesOrderClinicalDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSalesOrderClinicalDetails.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testSalesOrderClinicalDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSalesOrderClinicalDetails.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testSalesOrderClinicalDetails.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderZip()).isEqualTo(UPDATED_RENDERING_PROVIDER_ZIP);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderZip()).isEqualTo(UPDATED_REFERRING_PROVIDER_ZIP);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderZip()).isEqualTo(UPDATED_ORDERING_PROVIDER_ZIP);
        assertThat(testSalesOrderClinicalDetails.getMarketingReferralId()).isEqualTo(UPDATED_MARKETING_REFERRAL_ID);
        assertThat(testSalesOrderClinicalDetails.getMarketingReferralName()).isEqualTo(UPDATED_MARKETING_REFERRAL_NAME);
        assertThat(testSalesOrderClinicalDetails.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testSalesOrderClinicalDetails.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testSalesOrderClinicalDetails.getSalesOrderClinicalDetailsUuid()).isEqualTo(UPDATED_SALES_ORDER_CLINICAL_DETAILS_UUID);
        assertThat(testSalesOrderClinicalDetails.getPrimaryDiagnosis()).isEqualTo(UPDATED_PRIMARY_DIAGNOSIS);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderCity()).isEqualTo(UPDATED_ORDERING_PROVIDER_CITY);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderState()).isEqualTo(UPDATED_ORDERING_PROVIDER_STATE);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderCountry()).isEqualTo(UPDATED_ORDERING_PROVIDER_COUNTRY);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderContactNo1()).isEqualTo(UPDATED_ORDERING_PROVIDER_CONTACT_NO_1);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderContactNo2()).isEqualTo(UPDATED_ORDERING_PROVIDER_CONTACT_NO_2);
        assertThat(testSalesOrderClinicalDetails.getOrderingProviderEfax()).isEqualTo(UPDATED_ORDERING_PROVIDER_EFAX);
        assertThat(testSalesOrderClinicalDetails.getRelationship()).isEqualTo(UPDATED_RELATIONSHIP);
        assertThat(testSalesOrderClinicalDetails.getModeOfContact()).isEqualTo(UPDATED_MODE_OF_CONTACT);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderCity()).isEqualTo(UPDATED_REFERRING_PROVIDER_CITY);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderState()).isEqualTo(UPDATED_REFERRING_PROVIDER_STATE);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderCountry()).isEqualTo(UPDATED_REFERRING_PROVIDER_COUNTRY);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderContactNo1()).isEqualTo(UPDATED_REFERRING_PROVIDER_CONTACT_NO_1);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderContactNo2()).isEqualTo(UPDATED_REFERRING_PROVIDER_CONTACT_NO_2);
        assertThat(testSalesOrderClinicalDetails.getReferringProviderEfax()).isEqualTo(UPDATED_REFERRING_PROVIDER_EFAX);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderCity()).isEqualTo(UPDATED_RENDERING_PROVIDER_CITY);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderState()).isEqualTo(UPDATED_RENDERING_PROVIDER_STATE);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderCountry()).isEqualTo(UPDATED_RENDERING_PROVIDER_COUNTRY);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderContactNo1()).isEqualTo(UPDATED_RENDERING_PROVIDER_CONTACT_NO_1);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderContactNo2()).isEqualTo(UPDATED_RENDERING_PROVIDER_CONTACT_NO_2);
        assertThat(testSalesOrderClinicalDetails.getRenderingProviderEfax()).isEqualTo(UPDATED_RENDERING_PROVIDER_EFAX);
        assertThat(testSalesOrderClinicalDetails.getDiagnosisCodeType()).isEqualTo(UPDATED_DIAGNOSIS_CODE_TYPE);
    }

    @Test
    void patchNonExistingSalesOrderClinicalDetails() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderClinicalDetailsRepository.findAll().collectList().block().size();
        salesOrderClinicalDetails.setSalesOrderClinicalDetailsId(count.incrementAndGet());

        // Create the SalesOrderClinicalDetails
        SalesOrderClinicalDetailsDTO salesOrderClinicalDetailsDTO = salesOrderClinicalDetailsMapper.toDto(salesOrderClinicalDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, salesOrderClinicalDetailsDTO.getSalesOrderClinicalDetailsId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderClinicalDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderClinicalDetails in the database
        List<SalesOrderClinicalDetails> salesOrderClinicalDetailsList = salesOrderClinicalDetailsRepository.findAll().collectList().block();
        assertThat(salesOrderClinicalDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchSalesOrderClinicalDetails() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderClinicalDetailsRepository.findAll().collectList().block().size();
        salesOrderClinicalDetails.setSalesOrderClinicalDetailsId(count.incrementAndGet());

        // Create the SalesOrderClinicalDetails
        SalesOrderClinicalDetailsDTO salesOrderClinicalDetailsDTO = salesOrderClinicalDetailsMapper.toDto(salesOrderClinicalDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderClinicalDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderClinicalDetails in the database
        List<SalesOrderClinicalDetails> salesOrderClinicalDetailsList = salesOrderClinicalDetailsRepository.findAll().collectList().block();
        assertThat(salesOrderClinicalDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamSalesOrderClinicalDetails() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderClinicalDetailsRepository.findAll().collectList().block().size();
        salesOrderClinicalDetails.setSalesOrderClinicalDetailsId(count.incrementAndGet());

        // Create the SalesOrderClinicalDetails
        SalesOrderClinicalDetailsDTO salesOrderClinicalDetailsDTO = salesOrderClinicalDetailsMapper.toDto(salesOrderClinicalDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderClinicalDetailsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the SalesOrderClinicalDetails in the database
        List<SalesOrderClinicalDetails> salesOrderClinicalDetailsList = salesOrderClinicalDetailsRepository.findAll().collectList().block();
        assertThat(salesOrderClinicalDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteSalesOrderClinicalDetails() {
        // Initialize the database
        salesOrderClinicalDetailsRepository.save(salesOrderClinicalDetails).block();

        int databaseSizeBeforeDelete = salesOrderClinicalDetailsRepository.findAll().collectList().block().size();

        // Delete the salesOrderClinicalDetails
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, salesOrderClinicalDetails.getSalesOrderClinicalDetailsId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<SalesOrderClinicalDetails> salesOrderClinicalDetailsList = salesOrderClinicalDetailsRepository.findAll().collectList().block();
        assertThat(salesOrderClinicalDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
