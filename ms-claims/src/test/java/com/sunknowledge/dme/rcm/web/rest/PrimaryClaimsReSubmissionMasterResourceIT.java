package com.sunknowledge.dme.rcm.web.rest;

import static com.sunknowledge.dme.rcm.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.PrimaryClaimsReSubmissionMaster;
import com.sunknowledge.dme.rcm.repository.PrimaryClaimsReSubmissionMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.PrimaryClaimsReSubmissionMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.PrimaryClaimsReSubmissionMasterMapper;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link PrimaryClaimsReSubmissionMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PrimaryClaimsReSubmissionMasterResourceIT {

    private static final Long DEFAULT_SALES_ORDER_ID = 1L;
    private static final Long UPDATED_SALES_ORDER_ID = 2L;

    private static final String DEFAULT_CLAIM_CONTROL_NO = "AAAAAAAAAA";
    private static final String UPDATED_CLAIM_CONTROL_NO = "BBBBBBBBBB";

    private static final String DEFAULT_TRADING_PARTNER_SERVICE_ID = "AAAAAAAAAA";
    private static final String UPDATED_TRADING_PARTNER_SERVICE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TRADING_PARTNER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TRADING_PARTNER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SUBMITTER_ORGANIZATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SUBMITTER_ORGANIZATION_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SUBMITTER_CONTACT_PERSON_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SUBMITTER_CONTACT_PERSON_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SUBMITTER_CONTACT_NO = "AAAAAAAAAA";
    private static final String UPDATED_SUBMITTER_CONTACT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_RECEIVER_ORGANIZATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_RECEIVER_ORGANIZATION_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SUBSCRIBER_MEMBER_ID_NO = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIBER_MEMBER_ID_NO = "BBBBBBBBBB";

    private static final String DEFAULT_SUBSCRIBER_PAYMENT_RESPONSIBILITY_LEVEL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIBER_PAYMENT_RESPONSIBILITY_LEVEL_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SUBSCRIBER_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIBER_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SUBSCRIBER_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIBER_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SUBSCRIBER_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIBER_GENDER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_SUBSCRIBER_DOB = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SUBSCRIBER_DOB = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_PRIMARY_INSURER_POLICY_NO = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_INSURER_POLICY_NO = "BBBBBBBBBB";

    private static final String DEFAULT_SUBSCRIBER_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIBER_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_SUBSCRIBER_CITY = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIBER_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_SUBSCRIBER_STATE = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIBER_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_SUBSCRIBER_ZIP_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIBER_ZIP_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_PROVIDER_NPI = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_PROVIDER_NPI = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_PROVIDER_EIN = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_PROVIDER_EIN = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_PROVIDER_ORGANIZATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_PROVIDER_ORGANIZATION_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_PROVIDER_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_PROVIDER_CITY = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_PROVIDER_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_PROVIDER_STATE = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_PROVIDER_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_PROVIDER_ZIP_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_PROVIDER_ZIP_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_PROVIDER_CONTACT_PERSON_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_PROVIDER_CONTACT_PERSON_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_PROVIDER_CONTACT_NO = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_PROVIDER_CONTACT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_CLAIM_FILING_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CLAIM_FILING_CODE = "BBBBBBBBBB";

    private static final Double DEFAULT_CLAIM_CHARGE_AMOUNT = 1D;
    private static final Double UPDATED_CLAIM_CHARGE_AMOUNT = 2D;

    private static final String DEFAULT_POS_CODE = "AAAAAAAAAA";
    private static final String UPDATED_POS_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CLAIM_FREQUENCY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CLAIM_FREQUENCY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SIGNATURE_INDICATOR = "AAAAAAAAAA";
    private static final String UPDATED_SIGNATURE_INDICATOR = "BBBBBBBBBB";

    private static final String DEFAULT_PLAN_PARTICIPATION_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PLAN_PARTICIPATION_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR = "AAAAAAAAAA";
    private static final String UPDATED_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR = "BBBBBBBBBB";

    private static final String DEFAULT_RELEASE_INFORMATION_CODE = "AAAAAAAAAA";
    private static final String UPDATED_RELEASE_INFORMATION_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_DIAGNOSIS = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_DIAGNOSIS = "BBBBBBBBBB";

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

    private static final Long DEFAULT_INSERTED_BY_ID = 1L;
    private static final Long UPDATED_INSERTED_BY_ID = 2L;

    private static final ZonedDateTime DEFAULT_INSERTED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_INSERTED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final ZonedDateTime DEFAULT_UPDATED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_BILLING_PROVIDER_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_PROVIDER_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_INSERTED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INSERTED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_PROVIDER_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_PAYER_CLAIM_CONTROL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_CLAIM_CONTROL_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_INSURED_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INSURED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_INSURED_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INSURED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_INSURED_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_INSURED_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_INSURED_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_INSURED_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_INSURED_CITY = "AAAAAAAAAA";
    private static final String UPDATED_INSURED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_INSURED_STATE = "AAAAAAAAAA";
    private static final String UPDATED_INSURED_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_INSURED_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_INSURED_ZIP = "BBBBBBBBBB";

    private static final String DEFAULT_INSURED_CONTACT_NO = "AAAAAAAAAA";
    private static final String UPDATED_INSURED_CONTACT_NO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_INSURED_DOB = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_INSURED_DOB = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_INSURED_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_INSURED_GENDER = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERING_PROVIDER_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ORDERING_PROVIDER_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERING_PROVIDER_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ORDERING_PROVIDER_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERING_PROVIDER_NPI = "AAAAAAAAAA";
    private static final String UPDATED_ORDERING_PROVIDER_NPI = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_RELATIONSHIP_INSURED = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_RELATIONSHIP_INSURED = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_CONDITION_EMPLOYMENT = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_CONDITION_EMPLOYMENT = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_CONDITION_AUTO_ACCIDENT = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_CONDITION_AUTO_ACCIDENT = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_CONDITION_OTHER_ACCIDENT = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_CONDITION_OTHER_ACCIDENT = "BBBBBBBBBB";

    private static final String DEFAULT_IS_NEXT_LEVEL_INSURER_PRESENT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_IS_NEXT_LEVEL_INSURER_PRESENT_STATUS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_ORIGINAL_DOS = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ORIGINAL_DOS = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_PAR_NO = "AAAAAAAAAA";
    private static final String UPDATED_PAR_NO = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_PROVIDER_TAXONOMY = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_PROVIDER_TAXONOMY = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICE_PROVIDER_NPI = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_PROVIDER_NPI = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICE_PROVIDER_ORGANISATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_PROVIDER_ORGANISATION_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICE_PROVIDER_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_PROVIDER_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICE_PROVIDER_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_PROVIDER_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICE_PROVIDER_CITY = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_PROVIDER_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICE_PROVIDER_STATE = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_PROVIDER_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICE_PROVIDER_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_PROVIDER_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICE_PROVIDER_ZIP_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_PROVIDER_ZIP_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICE_PROVIDER_TAXONOMY = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_PROVIDER_TAXONOMY = "BBBBBBBBBB";

    private static final String DEFAULT_CMS_1500_FORM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CMS_1500_FORM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TRADING_PARTNER_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_TRADING_PARTNER_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_TRADING_PARTNER_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_TRADING_PARTNER_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_TRADING_PARTNER_CITY = "AAAAAAAAAA";
    private static final String UPDATED_TRADING_PARTNER_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_TRADING_PARTNER_STATE = "AAAAAAAAAA";
    private static final String UPDATED_TRADING_PARTNER_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_TRADING_PARTNER_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_TRADING_PARTNER_ZIP = "BBBBBBBBBB";

    private static final String DEFAULT_DIAGNOSIS_CODE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_DIAGNOSIS_CODE_TYPE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/primary-claims-re-submission-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{changeHealthPrimaryResubmisionMasterId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PrimaryClaimsReSubmissionMasterRepository primaryClaimsReSubmissionMasterRepository;

    @Autowired
    private PrimaryClaimsReSubmissionMasterMapper primaryClaimsReSubmissionMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPrimaryClaimsReSubmissionMasterMockMvc;

    private PrimaryClaimsReSubmissionMaster primaryClaimsReSubmissionMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PrimaryClaimsReSubmissionMaster createEntity(EntityManager em) {
        PrimaryClaimsReSubmissionMaster primaryClaimsReSubmissionMaster = new PrimaryClaimsReSubmissionMaster()
            .salesOrderId(DEFAULT_SALES_ORDER_ID)
            .claimControlNo(DEFAULT_CLAIM_CONTROL_NO)
            .tradingPartnerServiceId(DEFAULT_TRADING_PARTNER_SERVICE_ID)
            .tradingPartnerName(DEFAULT_TRADING_PARTNER_NAME)
            .submitterOrganizationName(DEFAULT_SUBMITTER_ORGANIZATION_NAME)
            .submitterContactPersonName(DEFAULT_SUBMITTER_CONTACT_PERSON_NAME)
            .submitterContactNo(DEFAULT_SUBMITTER_CONTACT_NO)
            .receiverOrganizationName(DEFAULT_RECEIVER_ORGANIZATION_NAME)
            .subscriberMemberIdNo(DEFAULT_SUBSCRIBER_MEMBER_ID_NO)
            .subscriberPaymentResponsibilityLevelCode(DEFAULT_SUBSCRIBER_PAYMENT_RESPONSIBILITY_LEVEL_CODE)
            .subscriberFirstName(DEFAULT_SUBSCRIBER_FIRST_NAME)
            .subscriberLastName(DEFAULT_SUBSCRIBER_LAST_NAME)
            .subscriberGender(DEFAULT_SUBSCRIBER_GENDER)
            .subscriberDob(DEFAULT_SUBSCRIBER_DOB)
            .primaryInsurerPolicyNo(DEFAULT_PRIMARY_INSURER_POLICY_NO)
            .subscriberAddressLine1(DEFAULT_SUBSCRIBER_ADDRESS_LINE_1)
            .subscriberCity(DEFAULT_SUBSCRIBER_CITY)
            .subscriberState(DEFAULT_SUBSCRIBER_STATE)
            .subscriberZipCode(DEFAULT_SUBSCRIBER_ZIP_CODE)
            .billingProviderNpi(DEFAULT_BILLING_PROVIDER_NPI)
            .billingProviderEin(DEFAULT_BILLING_PROVIDER_EIN)
            .billingProviderOrganizationName(DEFAULT_BILLING_PROVIDER_ORGANIZATION_NAME)
            .billingProviderAddressLine1(DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_1)
            .billingProviderCity(DEFAULT_BILLING_PROVIDER_CITY)
            .billingProviderState(DEFAULT_BILLING_PROVIDER_STATE)
            .billingProviderZipCode(DEFAULT_BILLING_PROVIDER_ZIP_CODE)
            .billingProviderContactPersonName(DEFAULT_BILLING_PROVIDER_CONTACT_PERSON_NAME)
            .billingProviderContactNo(DEFAULT_BILLING_PROVIDER_CONTACT_NO)
            .claimFilingCode(DEFAULT_CLAIM_FILING_CODE)
            .claimChargeAmount(DEFAULT_CLAIM_CHARGE_AMOUNT)
            .posCode(DEFAULT_POS_CODE)
            .claimFrequencyCode(DEFAULT_CLAIM_FREQUENCY_CODE)
            .signatureIndicator(DEFAULT_SIGNATURE_INDICATOR)
            .planParticipationCode(DEFAULT_PLAN_PARTICIPATION_CODE)
            .benefitsAssignmentCertificationIndicator(DEFAULT_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR)
            .releaseInformationCode(DEFAULT_RELEASE_INFORMATION_CODE)
            .primaryDiagnosis(DEFAULT_PRIMARY_DIAGNOSIS)
            .icd10diagnosisCode1(DEFAULT_ICD_10_DIAGNOSIS_CODE_1)
            .icd10diagnosisCode2(DEFAULT_ICD_10_DIAGNOSIS_CODE_2)
            .icd10diagnosisCode3(DEFAULT_ICD_10_DIAGNOSIS_CODE_3)
            .icd10diagnosisCode4(DEFAULT_ICD_10_DIAGNOSIS_CODE_4)
            .icd10diagnosisCode5(DEFAULT_ICD_10_DIAGNOSIS_CODE_5)
            .icd10diagnosisCode6(DEFAULT_ICD_10_DIAGNOSIS_CODE_6)
            .icd10diagnosisCode7(DEFAULT_ICD_10_DIAGNOSIS_CODE_7)
            .icd10diagnosisCode8(DEFAULT_ICD_10_DIAGNOSIS_CODE_8)
            .icd10diagnosisCode9(DEFAULT_ICD_10_DIAGNOSIS_CODE_9)
            .icd10diagnosisCode10(DEFAULT_ICD_10_DIAGNOSIS_CODE_10)
            .icd10diagnosisCode11(DEFAULT_ICD_10_DIAGNOSIS_CODE_11)
            .icd10diagnosisCode12(DEFAULT_ICD_10_DIAGNOSIS_CODE_12)
            .insertedById(DEFAULT_INSERTED_BY_ID)
            .insertedDate(DEFAULT_INSERTED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .billingProviderType(DEFAULT_BILLING_PROVIDER_TYPE)
            .insertedByName(DEFAULT_INSERTED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .status(DEFAULT_STATUS)
            .billingProviderAddressLine2(DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_2)
            .payerClaimControlNumber(DEFAULT_PAYER_CLAIM_CONTROL_NUMBER)
            .insuredFirstName(DEFAULT_INSURED_FIRST_NAME)
            .insuredLastName(DEFAULT_INSURED_LAST_NAME)
            .insuredAddressLine1(DEFAULT_INSURED_ADDRESS_LINE_1)
            .insuredAddressLine2(DEFAULT_INSURED_ADDRESS_LINE_2)
            .insuredCity(DEFAULT_INSURED_CITY)
            .insuredState(DEFAULT_INSURED_STATE)
            .insuredZip(DEFAULT_INSURED_ZIP)
            .insuredContactNo(DEFAULT_INSURED_CONTACT_NO)
            .insuredDob(DEFAULT_INSURED_DOB)
            .insuredGender(DEFAULT_INSURED_GENDER)
            .orderingProviderFirstName(DEFAULT_ORDERING_PROVIDER_FIRST_NAME)
            .orderingProviderLastName(DEFAULT_ORDERING_PROVIDER_LAST_NAME)
            .orderingProviderNpi(DEFAULT_ORDERING_PROVIDER_NPI)
            .patientRelationshipInsured(DEFAULT_PATIENT_RELATIONSHIP_INSURED)
            .patientConditionEmployment(DEFAULT_PATIENT_CONDITION_EMPLOYMENT)
            .patientConditionAutoAccident(DEFAULT_PATIENT_CONDITION_AUTO_ACCIDENT)
            .patientConditionOtherAccident(DEFAULT_PATIENT_CONDITION_OTHER_ACCIDENT)
            .isNextLevelInsurerPresentStatus(DEFAULT_IS_NEXT_LEVEL_INSURER_PRESENT_STATUS)
            .originalDos(DEFAULT_ORIGINAL_DOS)
            .parNo(DEFAULT_PAR_NO)
            .billingProviderTaxonomy(DEFAULT_BILLING_PROVIDER_TAXONOMY)
            .serviceProviderNpi(DEFAULT_SERVICE_PROVIDER_NPI)
            .serviceProviderOrganisationName(DEFAULT_SERVICE_PROVIDER_ORGANISATION_NAME)
            .serviceProviderAddressLine1(DEFAULT_SERVICE_PROVIDER_ADDRESS_LINE_1)
            .serviceProviderAddressLine2(DEFAULT_SERVICE_PROVIDER_ADDRESS_LINE_2)
            .serviceProviderCity(DEFAULT_SERVICE_PROVIDER_CITY)
            .serviceProviderState(DEFAULT_SERVICE_PROVIDER_STATE)
            .serviceProviderCountry(DEFAULT_SERVICE_PROVIDER_COUNTRY)
            .serviceProviderZipCode(DEFAULT_SERVICE_PROVIDER_ZIP_CODE)
            .serviceProviderTaxonomy(DEFAULT_SERVICE_PROVIDER_TAXONOMY)
            .cms1500FormName(DEFAULT_CMS_1500_FORM_NAME)
            .tradingPartnerAddressLine1(DEFAULT_TRADING_PARTNER_ADDRESS_LINE_1)
            .tradingPartnerAddressLine2(DEFAULT_TRADING_PARTNER_ADDRESS_LINE_2)
            .tradingPartnerCity(DEFAULT_TRADING_PARTNER_CITY)
            .tradingPartnerState(DEFAULT_TRADING_PARTNER_STATE)
            .tradingPartnerZip(DEFAULT_TRADING_PARTNER_ZIP)
            .diagnosisCodeType(DEFAULT_DIAGNOSIS_CODE_TYPE);
        return primaryClaimsReSubmissionMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PrimaryClaimsReSubmissionMaster createUpdatedEntity(EntityManager em) {
        PrimaryClaimsReSubmissionMaster primaryClaimsReSubmissionMaster = new PrimaryClaimsReSubmissionMaster()
            .salesOrderId(UPDATED_SALES_ORDER_ID)
            .claimControlNo(UPDATED_CLAIM_CONTROL_NO)
            .tradingPartnerServiceId(UPDATED_TRADING_PARTNER_SERVICE_ID)
            .tradingPartnerName(UPDATED_TRADING_PARTNER_NAME)
            .submitterOrganizationName(UPDATED_SUBMITTER_ORGANIZATION_NAME)
            .submitterContactPersonName(UPDATED_SUBMITTER_CONTACT_PERSON_NAME)
            .submitterContactNo(UPDATED_SUBMITTER_CONTACT_NO)
            .receiverOrganizationName(UPDATED_RECEIVER_ORGANIZATION_NAME)
            .subscriberMemberIdNo(UPDATED_SUBSCRIBER_MEMBER_ID_NO)
            .subscriberPaymentResponsibilityLevelCode(UPDATED_SUBSCRIBER_PAYMENT_RESPONSIBILITY_LEVEL_CODE)
            .subscriberFirstName(UPDATED_SUBSCRIBER_FIRST_NAME)
            .subscriberLastName(UPDATED_SUBSCRIBER_LAST_NAME)
            .subscriberGender(UPDATED_SUBSCRIBER_GENDER)
            .subscriberDob(UPDATED_SUBSCRIBER_DOB)
            .primaryInsurerPolicyNo(UPDATED_PRIMARY_INSURER_POLICY_NO)
            .subscriberAddressLine1(UPDATED_SUBSCRIBER_ADDRESS_LINE_1)
            .subscriberCity(UPDATED_SUBSCRIBER_CITY)
            .subscriberState(UPDATED_SUBSCRIBER_STATE)
            .subscriberZipCode(UPDATED_SUBSCRIBER_ZIP_CODE)
            .billingProviderNpi(UPDATED_BILLING_PROVIDER_NPI)
            .billingProviderEin(UPDATED_BILLING_PROVIDER_EIN)
            .billingProviderOrganizationName(UPDATED_BILLING_PROVIDER_ORGANIZATION_NAME)
            .billingProviderAddressLine1(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_1)
            .billingProviderCity(UPDATED_BILLING_PROVIDER_CITY)
            .billingProviderState(UPDATED_BILLING_PROVIDER_STATE)
            .billingProviderZipCode(UPDATED_BILLING_PROVIDER_ZIP_CODE)
            .billingProviderContactPersonName(UPDATED_BILLING_PROVIDER_CONTACT_PERSON_NAME)
            .billingProviderContactNo(UPDATED_BILLING_PROVIDER_CONTACT_NO)
            .claimFilingCode(UPDATED_CLAIM_FILING_CODE)
            .claimChargeAmount(UPDATED_CLAIM_CHARGE_AMOUNT)
            .posCode(UPDATED_POS_CODE)
            .claimFrequencyCode(UPDATED_CLAIM_FREQUENCY_CODE)
            .signatureIndicator(UPDATED_SIGNATURE_INDICATOR)
            .planParticipationCode(UPDATED_PLAN_PARTICIPATION_CODE)
            .benefitsAssignmentCertificationIndicator(UPDATED_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR)
            .releaseInformationCode(UPDATED_RELEASE_INFORMATION_CODE)
            .primaryDiagnosis(UPDATED_PRIMARY_DIAGNOSIS)
            .icd10diagnosisCode1(UPDATED_ICD_10_DIAGNOSIS_CODE_1)
            .icd10diagnosisCode2(UPDATED_ICD_10_DIAGNOSIS_CODE_2)
            .icd10diagnosisCode3(UPDATED_ICD_10_DIAGNOSIS_CODE_3)
            .icd10diagnosisCode4(UPDATED_ICD_10_DIAGNOSIS_CODE_4)
            .icd10diagnosisCode5(UPDATED_ICD_10_DIAGNOSIS_CODE_5)
            .icd10diagnosisCode6(UPDATED_ICD_10_DIAGNOSIS_CODE_6)
            .icd10diagnosisCode7(UPDATED_ICD_10_DIAGNOSIS_CODE_7)
            .icd10diagnosisCode8(UPDATED_ICD_10_DIAGNOSIS_CODE_8)
            .icd10diagnosisCode9(UPDATED_ICD_10_DIAGNOSIS_CODE_9)
            .icd10diagnosisCode10(UPDATED_ICD_10_DIAGNOSIS_CODE_10)
            .icd10diagnosisCode11(UPDATED_ICD_10_DIAGNOSIS_CODE_11)
            .icd10diagnosisCode12(UPDATED_ICD_10_DIAGNOSIS_CODE_12)
            .insertedById(UPDATED_INSERTED_BY_ID)
            .insertedDate(UPDATED_INSERTED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .billingProviderType(UPDATED_BILLING_PROVIDER_TYPE)
            .insertedByName(UPDATED_INSERTED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .status(UPDATED_STATUS)
            .billingProviderAddressLine2(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_2)
            .payerClaimControlNumber(UPDATED_PAYER_CLAIM_CONTROL_NUMBER)
            .insuredFirstName(UPDATED_INSURED_FIRST_NAME)
            .insuredLastName(UPDATED_INSURED_LAST_NAME)
            .insuredAddressLine1(UPDATED_INSURED_ADDRESS_LINE_1)
            .insuredAddressLine2(UPDATED_INSURED_ADDRESS_LINE_2)
            .insuredCity(UPDATED_INSURED_CITY)
            .insuredState(UPDATED_INSURED_STATE)
            .insuredZip(UPDATED_INSURED_ZIP)
            .insuredContactNo(UPDATED_INSURED_CONTACT_NO)
            .insuredDob(UPDATED_INSURED_DOB)
            .insuredGender(UPDATED_INSURED_GENDER)
            .orderingProviderFirstName(UPDATED_ORDERING_PROVIDER_FIRST_NAME)
            .orderingProviderLastName(UPDATED_ORDERING_PROVIDER_LAST_NAME)
            .orderingProviderNpi(UPDATED_ORDERING_PROVIDER_NPI)
            .patientRelationshipInsured(UPDATED_PATIENT_RELATIONSHIP_INSURED)
            .patientConditionEmployment(UPDATED_PATIENT_CONDITION_EMPLOYMENT)
            .patientConditionAutoAccident(UPDATED_PATIENT_CONDITION_AUTO_ACCIDENT)
            .patientConditionOtherAccident(UPDATED_PATIENT_CONDITION_OTHER_ACCIDENT)
            .isNextLevelInsurerPresentStatus(UPDATED_IS_NEXT_LEVEL_INSURER_PRESENT_STATUS)
            .originalDos(UPDATED_ORIGINAL_DOS)
            .parNo(UPDATED_PAR_NO)
            .billingProviderTaxonomy(UPDATED_BILLING_PROVIDER_TAXONOMY)
            .serviceProviderNpi(UPDATED_SERVICE_PROVIDER_NPI)
            .serviceProviderOrganisationName(UPDATED_SERVICE_PROVIDER_ORGANISATION_NAME)
            .serviceProviderAddressLine1(UPDATED_SERVICE_PROVIDER_ADDRESS_LINE_1)
            .serviceProviderAddressLine2(UPDATED_SERVICE_PROVIDER_ADDRESS_LINE_2)
            .serviceProviderCity(UPDATED_SERVICE_PROVIDER_CITY)
            .serviceProviderState(UPDATED_SERVICE_PROVIDER_STATE)
            .serviceProviderCountry(UPDATED_SERVICE_PROVIDER_COUNTRY)
            .serviceProviderZipCode(UPDATED_SERVICE_PROVIDER_ZIP_CODE)
            .serviceProviderTaxonomy(UPDATED_SERVICE_PROVIDER_TAXONOMY)
            .cms1500FormName(UPDATED_CMS_1500_FORM_NAME)
            .tradingPartnerAddressLine1(UPDATED_TRADING_PARTNER_ADDRESS_LINE_1)
            .tradingPartnerAddressLine2(UPDATED_TRADING_PARTNER_ADDRESS_LINE_2)
            .tradingPartnerCity(UPDATED_TRADING_PARTNER_CITY)
            .tradingPartnerState(UPDATED_TRADING_PARTNER_STATE)
            .tradingPartnerZip(UPDATED_TRADING_PARTNER_ZIP)
            .diagnosisCodeType(UPDATED_DIAGNOSIS_CODE_TYPE);
        return primaryClaimsReSubmissionMaster;
    }

    @BeforeEach
    public void initTest() {
        primaryClaimsReSubmissionMaster = createEntity(em);
    }

    @Test
    @Transactional
    void createPrimaryClaimsReSubmissionMaster() throws Exception {
        int databaseSizeBeforeCreate = primaryClaimsReSubmissionMasterRepository.findAll().size();
        // Create the PrimaryClaimsReSubmissionMaster
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );
        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the PrimaryClaimsReSubmissionMaster in the database
        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeCreate + 1);
        PrimaryClaimsReSubmissionMaster testPrimaryClaimsReSubmissionMaster = primaryClaimsReSubmissionMasterList.get(
            primaryClaimsReSubmissionMasterList.size() - 1
        );
        assertThat(testPrimaryClaimsReSubmissionMaster.getSalesOrderId()).isEqualTo(DEFAULT_SALES_ORDER_ID);
        assertThat(testPrimaryClaimsReSubmissionMaster.getClaimControlNo()).isEqualTo(DEFAULT_CLAIM_CONTROL_NO);
        assertThat(testPrimaryClaimsReSubmissionMaster.getTradingPartnerServiceId()).isEqualTo(DEFAULT_TRADING_PARTNER_SERVICE_ID);
        assertThat(testPrimaryClaimsReSubmissionMaster.getTradingPartnerName()).isEqualTo(DEFAULT_TRADING_PARTNER_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubmitterOrganizationName()).isEqualTo(DEFAULT_SUBMITTER_ORGANIZATION_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubmitterContactPersonName()).isEqualTo(DEFAULT_SUBMITTER_CONTACT_PERSON_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubmitterContactNo()).isEqualTo(DEFAULT_SUBMITTER_CONTACT_NO);
        assertThat(testPrimaryClaimsReSubmissionMaster.getReceiverOrganizationName()).isEqualTo(DEFAULT_RECEIVER_ORGANIZATION_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubscriberMemberIdNo()).isEqualTo(DEFAULT_SUBSCRIBER_MEMBER_ID_NO);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubscriberPaymentResponsibilityLevelCode())
            .isEqualTo(DEFAULT_SUBSCRIBER_PAYMENT_RESPONSIBILITY_LEVEL_CODE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubscriberFirstName()).isEqualTo(DEFAULT_SUBSCRIBER_FIRST_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubscriberLastName()).isEqualTo(DEFAULT_SUBSCRIBER_LAST_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubscriberGender()).isEqualTo(DEFAULT_SUBSCRIBER_GENDER);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubscriberDob()).isEqualTo(DEFAULT_SUBSCRIBER_DOB);
        assertThat(testPrimaryClaimsReSubmissionMaster.getPrimaryInsurerPolicyNo()).isEqualTo(DEFAULT_PRIMARY_INSURER_POLICY_NO);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubscriberAddressLine1()).isEqualTo(DEFAULT_SUBSCRIBER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubscriberCity()).isEqualTo(DEFAULT_SUBSCRIBER_CITY);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubscriberState()).isEqualTo(DEFAULT_SUBSCRIBER_STATE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubscriberZipCode()).isEqualTo(DEFAULT_SUBSCRIBER_ZIP_CODE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderNpi()).isEqualTo(DEFAULT_BILLING_PROVIDER_NPI);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderEin()).isEqualTo(DEFAULT_BILLING_PROVIDER_EIN);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderOrganizationName())
            .isEqualTo(DEFAULT_BILLING_PROVIDER_ORGANIZATION_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderAddressLine1()).isEqualTo(DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderCity()).isEqualTo(DEFAULT_BILLING_PROVIDER_CITY);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderState()).isEqualTo(DEFAULT_BILLING_PROVIDER_STATE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderZipCode()).isEqualTo(DEFAULT_BILLING_PROVIDER_ZIP_CODE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderContactPersonName())
            .isEqualTo(DEFAULT_BILLING_PROVIDER_CONTACT_PERSON_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderContactNo()).isEqualTo(DEFAULT_BILLING_PROVIDER_CONTACT_NO);
        assertThat(testPrimaryClaimsReSubmissionMaster.getClaimFilingCode()).isEqualTo(DEFAULT_CLAIM_FILING_CODE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getClaimChargeAmount()).isEqualTo(DEFAULT_CLAIM_CHARGE_AMOUNT);
        assertThat(testPrimaryClaimsReSubmissionMaster.getPosCode()).isEqualTo(DEFAULT_POS_CODE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getClaimFrequencyCode()).isEqualTo(DEFAULT_CLAIM_FREQUENCY_CODE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSignatureIndicator()).isEqualTo(DEFAULT_SIGNATURE_INDICATOR);
        assertThat(testPrimaryClaimsReSubmissionMaster.getPlanParticipationCode()).isEqualTo(DEFAULT_PLAN_PARTICIPATION_CODE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBenefitsAssignmentCertificationIndicator())
            .isEqualTo(DEFAULT_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR);
        assertThat(testPrimaryClaimsReSubmissionMaster.getReleaseInformationCode()).isEqualTo(DEFAULT_RELEASE_INFORMATION_CODE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getPrimaryDiagnosis()).isEqualTo(DEFAULT_PRIMARY_DIAGNOSIS);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode1()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_1);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode2()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_2);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode3()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_3);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode4()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_4);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode5()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_5);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode6()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_6);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode7()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_7);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode8()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_8);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode9()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_9);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode10()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_10);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode11()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_11);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode12()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_12);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsertedById()).isEqualTo(DEFAULT_INSERTED_BY_ID);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsertedDate()).isEqualTo(DEFAULT_INSERTED_DATE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testPrimaryClaimsReSubmissionMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderType()).isEqualTo(DEFAULT_BILLING_PROVIDER_TYPE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsertedByName()).isEqualTo(DEFAULT_INSERTED_BY_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderAddressLine2()).isEqualTo(DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimsReSubmissionMaster.getPayerClaimControlNumber()).isEqualTo(DEFAULT_PAYER_CLAIM_CONTROL_NUMBER);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsuredFirstName()).isEqualTo(DEFAULT_INSURED_FIRST_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsuredLastName()).isEqualTo(DEFAULT_INSURED_LAST_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsuredAddressLine1()).isEqualTo(DEFAULT_INSURED_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsuredAddressLine2()).isEqualTo(DEFAULT_INSURED_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsuredCity()).isEqualTo(DEFAULT_INSURED_CITY);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsuredState()).isEqualTo(DEFAULT_INSURED_STATE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsuredZip()).isEqualTo(DEFAULT_INSURED_ZIP);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsuredContactNo()).isEqualTo(DEFAULT_INSURED_CONTACT_NO);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsuredDob()).isEqualTo(DEFAULT_INSURED_DOB);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsuredGender()).isEqualTo(DEFAULT_INSURED_GENDER);
        assertThat(testPrimaryClaimsReSubmissionMaster.getOrderingProviderFirstName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getOrderingProviderLastName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getOrderingProviderNpi()).isEqualTo(DEFAULT_ORDERING_PROVIDER_NPI);
        assertThat(testPrimaryClaimsReSubmissionMaster.getPatientRelationshipInsured()).isEqualTo(DEFAULT_PATIENT_RELATIONSHIP_INSURED);
        assertThat(testPrimaryClaimsReSubmissionMaster.getPatientConditionEmployment()).isEqualTo(DEFAULT_PATIENT_CONDITION_EMPLOYMENT);
        assertThat(testPrimaryClaimsReSubmissionMaster.getPatientConditionAutoAccident())
            .isEqualTo(DEFAULT_PATIENT_CONDITION_AUTO_ACCIDENT);
        assertThat(testPrimaryClaimsReSubmissionMaster.getPatientConditionOtherAccident())
            .isEqualTo(DEFAULT_PATIENT_CONDITION_OTHER_ACCIDENT);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIsNextLevelInsurerPresentStatus())
            .isEqualTo(DEFAULT_IS_NEXT_LEVEL_INSURER_PRESENT_STATUS);
        assertThat(testPrimaryClaimsReSubmissionMaster.getOriginalDos()).isEqualTo(DEFAULT_ORIGINAL_DOS);
        assertThat(testPrimaryClaimsReSubmissionMaster.getParNo()).isEqualTo(DEFAULT_PAR_NO);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderTaxonomy()).isEqualTo(DEFAULT_BILLING_PROVIDER_TAXONOMY);
        assertThat(testPrimaryClaimsReSubmissionMaster.getServiceProviderNpi()).isEqualTo(DEFAULT_SERVICE_PROVIDER_NPI);
        assertThat(testPrimaryClaimsReSubmissionMaster.getServiceProviderOrganisationName())
            .isEqualTo(DEFAULT_SERVICE_PROVIDER_ORGANISATION_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getServiceProviderAddressLine1()).isEqualTo(DEFAULT_SERVICE_PROVIDER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimsReSubmissionMaster.getServiceProviderAddressLine2()).isEqualTo(DEFAULT_SERVICE_PROVIDER_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimsReSubmissionMaster.getServiceProviderCity()).isEqualTo(DEFAULT_SERVICE_PROVIDER_CITY);
        assertThat(testPrimaryClaimsReSubmissionMaster.getServiceProviderState()).isEqualTo(DEFAULT_SERVICE_PROVIDER_STATE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getServiceProviderCountry()).isEqualTo(DEFAULT_SERVICE_PROVIDER_COUNTRY);
        assertThat(testPrimaryClaimsReSubmissionMaster.getServiceProviderZipCode()).isEqualTo(DEFAULT_SERVICE_PROVIDER_ZIP_CODE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getServiceProviderTaxonomy()).isEqualTo(DEFAULT_SERVICE_PROVIDER_TAXONOMY);
        assertThat(testPrimaryClaimsReSubmissionMaster.getCms1500FormName()).isEqualTo(DEFAULT_CMS_1500_FORM_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getTradingPartnerAddressLine1()).isEqualTo(DEFAULT_TRADING_PARTNER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimsReSubmissionMaster.getTradingPartnerAddressLine2()).isEqualTo(DEFAULT_TRADING_PARTNER_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimsReSubmissionMaster.getTradingPartnerCity()).isEqualTo(DEFAULT_TRADING_PARTNER_CITY);
        assertThat(testPrimaryClaimsReSubmissionMaster.getTradingPartnerState()).isEqualTo(DEFAULT_TRADING_PARTNER_STATE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getTradingPartnerZip()).isEqualTo(DEFAULT_TRADING_PARTNER_ZIP);
        assertThat(testPrimaryClaimsReSubmissionMaster.getDiagnosisCodeType()).isEqualTo(DEFAULT_DIAGNOSIS_CODE_TYPE);
    }

    @Test
    @Transactional
    void createPrimaryClaimsReSubmissionMasterWithExistingId() throws Exception {
        // Create the PrimaryClaimsReSubmissionMaster with an existing ID
        primaryClaimsReSubmissionMaster.setChangeHealthPrimaryResubmisionMasterId(1L);
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );

        int databaseSizeBeforeCreate = primaryClaimsReSubmissionMasterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PrimaryClaimsReSubmissionMaster in the database
        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkSalesOrderIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryClaimsReSubmissionMasterRepository.findAll().size();
        // set the field null
        primaryClaimsReSubmissionMaster.setSalesOrderId(null);

        // Create the PrimaryClaimsReSubmissionMaster, which fails.
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );

        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkClaimControlNoIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryClaimsReSubmissionMasterRepository.findAll().size();
        // set the field null
        primaryClaimsReSubmissionMaster.setClaimControlNo(null);

        // Create the PrimaryClaimsReSubmissionMaster, which fails.
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );

        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTradingPartnerServiceIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryClaimsReSubmissionMasterRepository.findAll().size();
        // set the field null
        primaryClaimsReSubmissionMaster.setTradingPartnerServiceId(null);

        // Create the PrimaryClaimsReSubmissionMaster, which fails.
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );

        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTradingPartnerNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryClaimsReSubmissionMasterRepository.findAll().size();
        // set the field null
        primaryClaimsReSubmissionMaster.setTradingPartnerName(null);

        // Create the PrimaryClaimsReSubmissionMaster, which fails.
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );

        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSubmitterOrganizationNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryClaimsReSubmissionMasterRepository.findAll().size();
        // set the field null
        primaryClaimsReSubmissionMaster.setSubmitterOrganizationName(null);

        // Create the PrimaryClaimsReSubmissionMaster, which fails.
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );

        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSubmitterContactPersonNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryClaimsReSubmissionMasterRepository.findAll().size();
        // set the field null
        primaryClaimsReSubmissionMaster.setSubmitterContactPersonName(null);

        // Create the PrimaryClaimsReSubmissionMaster, which fails.
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );

        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSubmitterContactNoIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryClaimsReSubmissionMasterRepository.findAll().size();
        // set the field null
        primaryClaimsReSubmissionMaster.setSubmitterContactNo(null);

        // Create the PrimaryClaimsReSubmissionMaster, which fails.
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );

        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkReceiverOrganizationNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryClaimsReSubmissionMasterRepository.findAll().size();
        // set the field null
        primaryClaimsReSubmissionMaster.setReceiverOrganizationName(null);

        // Create the PrimaryClaimsReSubmissionMaster, which fails.
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );

        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSubscriberMemberIdNoIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryClaimsReSubmissionMasterRepository.findAll().size();
        // set the field null
        primaryClaimsReSubmissionMaster.setSubscriberMemberIdNo(null);

        // Create the PrimaryClaimsReSubmissionMaster, which fails.
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );

        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSubscriberPaymentResponsibilityLevelCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryClaimsReSubmissionMasterRepository.findAll().size();
        // set the field null
        primaryClaimsReSubmissionMaster.setSubscriberPaymentResponsibilityLevelCode(null);

        // Create the PrimaryClaimsReSubmissionMaster, which fails.
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );

        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSubscriberFirstNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryClaimsReSubmissionMasterRepository.findAll().size();
        // set the field null
        primaryClaimsReSubmissionMaster.setSubscriberFirstName(null);

        // Create the PrimaryClaimsReSubmissionMaster, which fails.
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );

        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSubscriberLastNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryClaimsReSubmissionMasterRepository.findAll().size();
        // set the field null
        primaryClaimsReSubmissionMaster.setSubscriberLastName(null);

        // Create the PrimaryClaimsReSubmissionMaster, which fails.
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );

        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSubscriberGenderIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryClaimsReSubmissionMasterRepository.findAll().size();
        // set the field null
        primaryClaimsReSubmissionMaster.setSubscriberGender(null);

        // Create the PrimaryClaimsReSubmissionMaster, which fails.
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );

        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSubscriberDobIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryClaimsReSubmissionMasterRepository.findAll().size();
        // set the field null
        primaryClaimsReSubmissionMaster.setSubscriberDob(null);

        // Create the PrimaryClaimsReSubmissionMaster, which fails.
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );

        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSubscriberCityIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryClaimsReSubmissionMasterRepository.findAll().size();
        // set the field null
        primaryClaimsReSubmissionMaster.setSubscriberCity(null);

        // Create the PrimaryClaimsReSubmissionMaster, which fails.
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );

        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSubscriberStateIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryClaimsReSubmissionMasterRepository.findAll().size();
        // set the field null
        primaryClaimsReSubmissionMaster.setSubscriberState(null);

        // Create the PrimaryClaimsReSubmissionMaster, which fails.
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );

        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSubscriberZipCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryClaimsReSubmissionMasterRepository.findAll().size();
        // set the field null
        primaryClaimsReSubmissionMaster.setSubscriberZipCode(null);

        // Create the PrimaryClaimsReSubmissionMaster, which fails.
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );

        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBillingProviderNpiIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryClaimsReSubmissionMasterRepository.findAll().size();
        // set the field null
        primaryClaimsReSubmissionMaster.setBillingProviderNpi(null);

        // Create the PrimaryClaimsReSubmissionMaster, which fails.
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );

        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBillingProviderEinIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryClaimsReSubmissionMasterRepository.findAll().size();
        // set the field null
        primaryClaimsReSubmissionMaster.setBillingProviderEin(null);

        // Create the PrimaryClaimsReSubmissionMaster, which fails.
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );

        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBillingProviderOrganizationNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryClaimsReSubmissionMasterRepository.findAll().size();
        // set the field null
        primaryClaimsReSubmissionMaster.setBillingProviderOrganizationName(null);

        // Create the PrimaryClaimsReSubmissionMaster, which fails.
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );

        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBillingProviderAddressLine1IsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryClaimsReSubmissionMasterRepository.findAll().size();
        // set the field null
        primaryClaimsReSubmissionMaster.setBillingProviderAddressLine1(null);

        // Create the PrimaryClaimsReSubmissionMaster, which fails.
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );

        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBillingProviderCityIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryClaimsReSubmissionMasterRepository.findAll().size();
        // set the field null
        primaryClaimsReSubmissionMaster.setBillingProviderCity(null);

        // Create the PrimaryClaimsReSubmissionMaster, which fails.
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );

        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBillingProviderStateIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryClaimsReSubmissionMasterRepository.findAll().size();
        // set the field null
        primaryClaimsReSubmissionMaster.setBillingProviderState(null);

        // Create the PrimaryClaimsReSubmissionMaster, which fails.
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );

        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBillingProviderZipCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryClaimsReSubmissionMasterRepository.findAll().size();
        // set the field null
        primaryClaimsReSubmissionMaster.setBillingProviderZipCode(null);

        // Create the PrimaryClaimsReSubmissionMaster, which fails.
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );

        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBillingProviderContactPersonNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryClaimsReSubmissionMasterRepository.findAll().size();
        // set the field null
        primaryClaimsReSubmissionMaster.setBillingProviderContactPersonName(null);

        // Create the PrimaryClaimsReSubmissionMaster, which fails.
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );

        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBillingProviderContactNoIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryClaimsReSubmissionMasterRepository.findAll().size();
        // set the field null
        primaryClaimsReSubmissionMaster.setBillingProviderContactNo(null);

        // Create the PrimaryClaimsReSubmissionMaster, which fails.
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );

        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkClaimFilingCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryClaimsReSubmissionMasterRepository.findAll().size();
        // set the field null
        primaryClaimsReSubmissionMaster.setClaimFilingCode(null);

        // Create the PrimaryClaimsReSubmissionMaster, which fails.
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );

        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkClaimChargeAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryClaimsReSubmissionMasterRepository.findAll().size();
        // set the field null
        primaryClaimsReSubmissionMaster.setClaimChargeAmount(null);

        // Create the PrimaryClaimsReSubmissionMaster, which fails.
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );

        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPosCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryClaimsReSubmissionMasterRepository.findAll().size();
        // set the field null
        primaryClaimsReSubmissionMaster.setPosCode(null);

        // Create the PrimaryClaimsReSubmissionMaster, which fails.
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );

        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkClaimFrequencyCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryClaimsReSubmissionMasterRepository.findAll().size();
        // set the field null
        primaryClaimsReSubmissionMaster.setClaimFrequencyCode(null);

        // Create the PrimaryClaimsReSubmissionMaster, which fails.
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );

        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSignatureIndicatorIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryClaimsReSubmissionMasterRepository.findAll().size();
        // set the field null
        primaryClaimsReSubmissionMaster.setSignatureIndicator(null);

        // Create the PrimaryClaimsReSubmissionMaster, which fails.
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );

        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPlanParticipationCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryClaimsReSubmissionMasterRepository.findAll().size();
        // set the field null
        primaryClaimsReSubmissionMaster.setPlanParticipationCode(null);

        // Create the PrimaryClaimsReSubmissionMaster, which fails.
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );

        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBenefitsAssignmentCertificationIndicatorIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryClaimsReSubmissionMasterRepository.findAll().size();
        // set the field null
        primaryClaimsReSubmissionMaster.setBenefitsAssignmentCertificationIndicator(null);

        // Create the PrimaryClaimsReSubmissionMaster, which fails.
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );

        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkReleaseInformationCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryClaimsReSubmissionMasterRepository.findAll().size();
        // set the field null
        primaryClaimsReSubmissionMaster.setReleaseInformationCode(null);

        // Create the PrimaryClaimsReSubmissionMaster, which fails.
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );

        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBillingProviderTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryClaimsReSubmissionMasterRepository.findAll().size();
        // set the field null
        primaryClaimsReSubmissionMaster.setBillingProviderType(null);

        // Create the PrimaryClaimsReSubmissionMaster, which fails.
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );

        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPayerClaimControlNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryClaimsReSubmissionMasterRepository.findAll().size();
        // set the field null
        primaryClaimsReSubmissionMaster.setPayerClaimControlNumber(null);

        // Create the PrimaryClaimsReSubmissionMaster, which fails.
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );

        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllPrimaryClaimsReSubmissionMasters() throws Exception {
        // Initialize the database
        primaryClaimsReSubmissionMasterRepository.saveAndFlush(primaryClaimsReSubmissionMaster);

        // Get all the primaryClaimsReSubmissionMasterList
        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=changeHealthPrimaryResubmisionMasterId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].changeHealthPrimaryResubmisionMasterId")
                    .value(hasItem(primaryClaimsReSubmissionMaster.getChangeHealthPrimaryResubmisionMasterId().intValue()))
            )
            .andExpect(jsonPath("$.[*].salesOrderId").value(hasItem(DEFAULT_SALES_ORDER_ID.intValue())))
            .andExpect(jsonPath("$.[*].claimControlNo").value(hasItem(DEFAULT_CLAIM_CONTROL_NO)))
            .andExpect(jsonPath("$.[*].tradingPartnerServiceId").value(hasItem(DEFAULT_TRADING_PARTNER_SERVICE_ID)))
            .andExpect(jsonPath("$.[*].tradingPartnerName").value(hasItem(DEFAULT_TRADING_PARTNER_NAME)))
            .andExpect(jsonPath("$.[*].submitterOrganizationName").value(hasItem(DEFAULT_SUBMITTER_ORGANIZATION_NAME)))
            .andExpect(jsonPath("$.[*].submitterContactPersonName").value(hasItem(DEFAULT_SUBMITTER_CONTACT_PERSON_NAME)))
            .andExpect(jsonPath("$.[*].submitterContactNo").value(hasItem(DEFAULT_SUBMITTER_CONTACT_NO)))
            .andExpect(jsonPath("$.[*].receiverOrganizationName").value(hasItem(DEFAULT_RECEIVER_ORGANIZATION_NAME)))
            .andExpect(jsonPath("$.[*].subscriberMemberIdNo").value(hasItem(DEFAULT_SUBSCRIBER_MEMBER_ID_NO)))
            .andExpect(
                jsonPath("$.[*].subscriberPaymentResponsibilityLevelCode")
                    .value(hasItem(DEFAULT_SUBSCRIBER_PAYMENT_RESPONSIBILITY_LEVEL_CODE))
            )
            .andExpect(jsonPath("$.[*].subscriberFirstName").value(hasItem(DEFAULT_SUBSCRIBER_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].subscriberLastName").value(hasItem(DEFAULT_SUBSCRIBER_LAST_NAME)))
            .andExpect(jsonPath("$.[*].subscriberGender").value(hasItem(DEFAULT_SUBSCRIBER_GENDER)))
            .andExpect(jsonPath("$.[*].subscriberDob").value(hasItem(DEFAULT_SUBSCRIBER_DOB.toString())))
            .andExpect(jsonPath("$.[*].primaryInsurerPolicyNo").value(hasItem(DEFAULT_PRIMARY_INSURER_POLICY_NO)))
            .andExpect(jsonPath("$.[*].subscriberAddressLine1").value(hasItem(DEFAULT_SUBSCRIBER_ADDRESS_LINE_1)))
            .andExpect(jsonPath("$.[*].subscriberCity").value(hasItem(DEFAULT_SUBSCRIBER_CITY)))
            .andExpect(jsonPath("$.[*].subscriberState").value(hasItem(DEFAULT_SUBSCRIBER_STATE)))
            .andExpect(jsonPath("$.[*].subscriberZipCode").value(hasItem(DEFAULT_SUBSCRIBER_ZIP_CODE)))
            .andExpect(jsonPath("$.[*].billingProviderNpi").value(hasItem(DEFAULT_BILLING_PROVIDER_NPI)))
            .andExpect(jsonPath("$.[*].billingProviderEin").value(hasItem(DEFAULT_BILLING_PROVIDER_EIN)))
            .andExpect(jsonPath("$.[*].billingProviderOrganizationName").value(hasItem(DEFAULT_BILLING_PROVIDER_ORGANIZATION_NAME)))
            .andExpect(jsonPath("$.[*].billingProviderAddressLine1").value(hasItem(DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_1)))
            .andExpect(jsonPath("$.[*].billingProviderCity").value(hasItem(DEFAULT_BILLING_PROVIDER_CITY)))
            .andExpect(jsonPath("$.[*].billingProviderState").value(hasItem(DEFAULT_BILLING_PROVIDER_STATE)))
            .andExpect(jsonPath("$.[*].billingProviderZipCode").value(hasItem(DEFAULT_BILLING_PROVIDER_ZIP_CODE)))
            .andExpect(jsonPath("$.[*].billingProviderContactPersonName").value(hasItem(DEFAULT_BILLING_PROVIDER_CONTACT_PERSON_NAME)))
            .andExpect(jsonPath("$.[*].billingProviderContactNo").value(hasItem(DEFAULT_BILLING_PROVIDER_CONTACT_NO)))
            .andExpect(jsonPath("$.[*].claimFilingCode").value(hasItem(DEFAULT_CLAIM_FILING_CODE)))
            .andExpect(jsonPath("$.[*].claimChargeAmount").value(hasItem(DEFAULT_CLAIM_CHARGE_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].posCode").value(hasItem(DEFAULT_POS_CODE)))
            .andExpect(jsonPath("$.[*].claimFrequencyCode").value(hasItem(DEFAULT_CLAIM_FREQUENCY_CODE)))
            .andExpect(jsonPath("$.[*].signatureIndicator").value(hasItem(DEFAULT_SIGNATURE_INDICATOR)))
            .andExpect(jsonPath("$.[*].planParticipationCode").value(hasItem(DEFAULT_PLAN_PARTICIPATION_CODE)))
            .andExpect(
                jsonPath("$.[*].benefitsAssignmentCertificationIndicator")
                    .value(hasItem(DEFAULT_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR))
            )
            .andExpect(jsonPath("$.[*].releaseInformationCode").value(hasItem(DEFAULT_RELEASE_INFORMATION_CODE)))
            .andExpect(jsonPath("$.[*].primaryDiagnosis").value(hasItem(DEFAULT_PRIMARY_DIAGNOSIS)))
            .andExpect(jsonPath("$.[*].icd10diagnosisCode1").value(hasItem(DEFAULT_ICD_10_DIAGNOSIS_CODE_1)))
            .andExpect(jsonPath("$.[*].icd10diagnosisCode2").value(hasItem(DEFAULT_ICD_10_DIAGNOSIS_CODE_2)))
            .andExpect(jsonPath("$.[*].icd10diagnosisCode3").value(hasItem(DEFAULT_ICD_10_DIAGNOSIS_CODE_3)))
            .andExpect(jsonPath("$.[*].icd10diagnosisCode4").value(hasItem(DEFAULT_ICD_10_DIAGNOSIS_CODE_4)))
            .andExpect(jsonPath("$.[*].icd10diagnosisCode5").value(hasItem(DEFAULT_ICD_10_DIAGNOSIS_CODE_5)))
            .andExpect(jsonPath("$.[*].icd10diagnosisCode6").value(hasItem(DEFAULT_ICD_10_DIAGNOSIS_CODE_6)))
            .andExpect(jsonPath("$.[*].icd10diagnosisCode7").value(hasItem(DEFAULT_ICD_10_DIAGNOSIS_CODE_7)))
            .andExpect(jsonPath("$.[*].icd10diagnosisCode8").value(hasItem(DEFAULT_ICD_10_DIAGNOSIS_CODE_8)))
            .andExpect(jsonPath("$.[*].icd10diagnosisCode9").value(hasItem(DEFAULT_ICD_10_DIAGNOSIS_CODE_9)))
            .andExpect(jsonPath("$.[*].icd10diagnosisCode10").value(hasItem(DEFAULT_ICD_10_DIAGNOSIS_CODE_10)))
            .andExpect(jsonPath("$.[*].icd10diagnosisCode11").value(hasItem(DEFAULT_ICD_10_DIAGNOSIS_CODE_11)))
            .andExpect(jsonPath("$.[*].icd10diagnosisCode12").value(hasItem(DEFAULT_ICD_10_DIAGNOSIS_CODE_12)))
            .andExpect(jsonPath("$.[*].insertedById").value(hasItem(DEFAULT_INSERTED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].insertedDate").value(hasItem(sameInstant(DEFAULT_INSERTED_DATE))))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(sameInstant(DEFAULT_UPDATED_DATE))))
            .andExpect(jsonPath("$.[*].billingProviderType").value(hasItem(DEFAULT_BILLING_PROVIDER_TYPE)))
            .andExpect(jsonPath("$.[*].insertedByName").value(hasItem(DEFAULT_INSERTED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].billingProviderAddressLine2").value(hasItem(DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_2)))
            .andExpect(jsonPath("$.[*].payerClaimControlNumber").value(hasItem(DEFAULT_PAYER_CLAIM_CONTROL_NUMBER)))
            .andExpect(jsonPath("$.[*].insuredFirstName").value(hasItem(DEFAULT_INSURED_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].insuredLastName").value(hasItem(DEFAULT_INSURED_LAST_NAME)))
            .andExpect(jsonPath("$.[*].insuredAddressLine1").value(hasItem(DEFAULT_INSURED_ADDRESS_LINE_1)))
            .andExpect(jsonPath("$.[*].insuredAddressLine2").value(hasItem(DEFAULT_INSURED_ADDRESS_LINE_2)))
            .andExpect(jsonPath("$.[*].insuredCity").value(hasItem(DEFAULT_INSURED_CITY)))
            .andExpect(jsonPath("$.[*].insuredState").value(hasItem(DEFAULT_INSURED_STATE)))
            .andExpect(jsonPath("$.[*].insuredZip").value(hasItem(DEFAULT_INSURED_ZIP)))
            .andExpect(jsonPath("$.[*].insuredContactNo").value(hasItem(DEFAULT_INSURED_CONTACT_NO)))
            .andExpect(jsonPath("$.[*].insuredDob").value(hasItem(DEFAULT_INSURED_DOB.toString())))
            .andExpect(jsonPath("$.[*].insuredGender").value(hasItem(DEFAULT_INSURED_GENDER)))
            .andExpect(jsonPath("$.[*].orderingProviderFirstName").value(hasItem(DEFAULT_ORDERING_PROVIDER_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].orderingProviderLastName").value(hasItem(DEFAULT_ORDERING_PROVIDER_LAST_NAME)))
            .andExpect(jsonPath("$.[*].orderingProviderNpi").value(hasItem(DEFAULT_ORDERING_PROVIDER_NPI)))
            .andExpect(jsonPath("$.[*].patientRelationshipInsured").value(hasItem(DEFAULT_PATIENT_RELATIONSHIP_INSURED)))
            .andExpect(jsonPath("$.[*].patientConditionEmployment").value(hasItem(DEFAULT_PATIENT_CONDITION_EMPLOYMENT)))
            .andExpect(jsonPath("$.[*].patientConditionAutoAccident").value(hasItem(DEFAULT_PATIENT_CONDITION_AUTO_ACCIDENT)))
            .andExpect(jsonPath("$.[*].patientConditionOtherAccident").value(hasItem(DEFAULT_PATIENT_CONDITION_OTHER_ACCIDENT)))
            .andExpect(jsonPath("$.[*].isNextLevelInsurerPresentStatus").value(hasItem(DEFAULT_IS_NEXT_LEVEL_INSURER_PRESENT_STATUS)))
            .andExpect(jsonPath("$.[*].originalDos").value(hasItem(DEFAULT_ORIGINAL_DOS.toString())))
            .andExpect(jsonPath("$.[*].parNo").value(hasItem(DEFAULT_PAR_NO)))
            .andExpect(jsonPath("$.[*].billingProviderTaxonomy").value(hasItem(DEFAULT_BILLING_PROVIDER_TAXONOMY)))
            .andExpect(jsonPath("$.[*].serviceProviderNpi").value(hasItem(DEFAULT_SERVICE_PROVIDER_NPI)))
            .andExpect(jsonPath("$.[*].serviceProviderOrganisationName").value(hasItem(DEFAULT_SERVICE_PROVIDER_ORGANISATION_NAME)))
            .andExpect(jsonPath("$.[*].serviceProviderAddressLine1").value(hasItem(DEFAULT_SERVICE_PROVIDER_ADDRESS_LINE_1)))
            .andExpect(jsonPath("$.[*].serviceProviderAddressLine2").value(hasItem(DEFAULT_SERVICE_PROVIDER_ADDRESS_LINE_2)))
            .andExpect(jsonPath("$.[*].serviceProviderCity").value(hasItem(DEFAULT_SERVICE_PROVIDER_CITY)))
            .andExpect(jsonPath("$.[*].serviceProviderState").value(hasItem(DEFAULT_SERVICE_PROVIDER_STATE)))
            .andExpect(jsonPath("$.[*].serviceProviderCountry").value(hasItem(DEFAULT_SERVICE_PROVIDER_COUNTRY)))
            .andExpect(jsonPath("$.[*].serviceProviderZipCode").value(hasItem(DEFAULT_SERVICE_PROVIDER_ZIP_CODE)))
            .andExpect(jsonPath("$.[*].serviceProviderTaxonomy").value(hasItem(DEFAULT_SERVICE_PROVIDER_TAXONOMY)))
            .andExpect(jsonPath("$.[*].cms1500FormName").value(hasItem(DEFAULT_CMS_1500_FORM_NAME)))
            .andExpect(jsonPath("$.[*].tradingPartnerAddressLine1").value(hasItem(DEFAULT_TRADING_PARTNER_ADDRESS_LINE_1)))
            .andExpect(jsonPath("$.[*].tradingPartnerAddressLine2").value(hasItem(DEFAULT_TRADING_PARTNER_ADDRESS_LINE_2)))
            .andExpect(jsonPath("$.[*].tradingPartnerCity").value(hasItem(DEFAULT_TRADING_PARTNER_CITY)))
            .andExpect(jsonPath("$.[*].tradingPartnerState").value(hasItem(DEFAULT_TRADING_PARTNER_STATE)))
            .andExpect(jsonPath("$.[*].tradingPartnerZip").value(hasItem(DEFAULT_TRADING_PARTNER_ZIP)))
            .andExpect(jsonPath("$.[*].diagnosisCodeType").value(hasItem(DEFAULT_DIAGNOSIS_CODE_TYPE)));
    }

    @Test
    @Transactional
    void getPrimaryClaimsReSubmissionMaster() throws Exception {
        // Initialize the database
        primaryClaimsReSubmissionMasterRepository.saveAndFlush(primaryClaimsReSubmissionMaster);

        // Get the primaryClaimsReSubmissionMaster
        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(get(ENTITY_API_URL_ID, primaryClaimsReSubmissionMaster.getChangeHealthPrimaryResubmisionMasterId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.changeHealthPrimaryResubmisionMasterId")
                    .value(primaryClaimsReSubmissionMaster.getChangeHealthPrimaryResubmisionMasterId().intValue())
            )
            .andExpect(jsonPath("$.salesOrderId").value(DEFAULT_SALES_ORDER_ID.intValue()))
            .andExpect(jsonPath("$.claimControlNo").value(DEFAULT_CLAIM_CONTROL_NO))
            .andExpect(jsonPath("$.tradingPartnerServiceId").value(DEFAULT_TRADING_PARTNER_SERVICE_ID))
            .andExpect(jsonPath("$.tradingPartnerName").value(DEFAULT_TRADING_PARTNER_NAME))
            .andExpect(jsonPath("$.submitterOrganizationName").value(DEFAULT_SUBMITTER_ORGANIZATION_NAME))
            .andExpect(jsonPath("$.submitterContactPersonName").value(DEFAULT_SUBMITTER_CONTACT_PERSON_NAME))
            .andExpect(jsonPath("$.submitterContactNo").value(DEFAULT_SUBMITTER_CONTACT_NO))
            .andExpect(jsonPath("$.receiverOrganizationName").value(DEFAULT_RECEIVER_ORGANIZATION_NAME))
            .andExpect(jsonPath("$.subscriberMemberIdNo").value(DEFAULT_SUBSCRIBER_MEMBER_ID_NO))
            .andExpect(jsonPath("$.subscriberPaymentResponsibilityLevelCode").value(DEFAULT_SUBSCRIBER_PAYMENT_RESPONSIBILITY_LEVEL_CODE))
            .andExpect(jsonPath("$.subscriberFirstName").value(DEFAULT_SUBSCRIBER_FIRST_NAME))
            .andExpect(jsonPath("$.subscriberLastName").value(DEFAULT_SUBSCRIBER_LAST_NAME))
            .andExpect(jsonPath("$.subscriberGender").value(DEFAULT_SUBSCRIBER_GENDER))
            .andExpect(jsonPath("$.subscriberDob").value(DEFAULT_SUBSCRIBER_DOB.toString()))
            .andExpect(jsonPath("$.primaryInsurerPolicyNo").value(DEFAULT_PRIMARY_INSURER_POLICY_NO))
            .andExpect(jsonPath("$.subscriberAddressLine1").value(DEFAULT_SUBSCRIBER_ADDRESS_LINE_1))
            .andExpect(jsonPath("$.subscriberCity").value(DEFAULT_SUBSCRIBER_CITY))
            .andExpect(jsonPath("$.subscriberState").value(DEFAULT_SUBSCRIBER_STATE))
            .andExpect(jsonPath("$.subscriberZipCode").value(DEFAULT_SUBSCRIBER_ZIP_CODE))
            .andExpect(jsonPath("$.billingProviderNpi").value(DEFAULT_BILLING_PROVIDER_NPI))
            .andExpect(jsonPath("$.billingProviderEin").value(DEFAULT_BILLING_PROVIDER_EIN))
            .andExpect(jsonPath("$.billingProviderOrganizationName").value(DEFAULT_BILLING_PROVIDER_ORGANIZATION_NAME))
            .andExpect(jsonPath("$.billingProviderAddressLine1").value(DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_1))
            .andExpect(jsonPath("$.billingProviderCity").value(DEFAULT_BILLING_PROVIDER_CITY))
            .andExpect(jsonPath("$.billingProviderState").value(DEFAULT_BILLING_PROVIDER_STATE))
            .andExpect(jsonPath("$.billingProviderZipCode").value(DEFAULT_BILLING_PROVIDER_ZIP_CODE))
            .andExpect(jsonPath("$.billingProviderContactPersonName").value(DEFAULT_BILLING_PROVIDER_CONTACT_PERSON_NAME))
            .andExpect(jsonPath("$.billingProviderContactNo").value(DEFAULT_BILLING_PROVIDER_CONTACT_NO))
            .andExpect(jsonPath("$.claimFilingCode").value(DEFAULT_CLAIM_FILING_CODE))
            .andExpect(jsonPath("$.claimChargeAmount").value(DEFAULT_CLAIM_CHARGE_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.posCode").value(DEFAULT_POS_CODE))
            .andExpect(jsonPath("$.claimFrequencyCode").value(DEFAULT_CLAIM_FREQUENCY_CODE))
            .andExpect(jsonPath("$.signatureIndicator").value(DEFAULT_SIGNATURE_INDICATOR))
            .andExpect(jsonPath("$.planParticipationCode").value(DEFAULT_PLAN_PARTICIPATION_CODE))
            .andExpect(jsonPath("$.benefitsAssignmentCertificationIndicator").value(DEFAULT_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR))
            .andExpect(jsonPath("$.releaseInformationCode").value(DEFAULT_RELEASE_INFORMATION_CODE))
            .andExpect(jsonPath("$.primaryDiagnosis").value(DEFAULT_PRIMARY_DIAGNOSIS))
            .andExpect(jsonPath("$.icd10diagnosisCode1").value(DEFAULT_ICD_10_DIAGNOSIS_CODE_1))
            .andExpect(jsonPath("$.icd10diagnosisCode2").value(DEFAULT_ICD_10_DIAGNOSIS_CODE_2))
            .andExpect(jsonPath("$.icd10diagnosisCode3").value(DEFAULT_ICD_10_DIAGNOSIS_CODE_3))
            .andExpect(jsonPath("$.icd10diagnosisCode4").value(DEFAULT_ICD_10_DIAGNOSIS_CODE_4))
            .andExpect(jsonPath("$.icd10diagnosisCode5").value(DEFAULT_ICD_10_DIAGNOSIS_CODE_5))
            .andExpect(jsonPath("$.icd10diagnosisCode6").value(DEFAULT_ICD_10_DIAGNOSIS_CODE_6))
            .andExpect(jsonPath("$.icd10diagnosisCode7").value(DEFAULT_ICD_10_DIAGNOSIS_CODE_7))
            .andExpect(jsonPath("$.icd10diagnosisCode8").value(DEFAULT_ICD_10_DIAGNOSIS_CODE_8))
            .andExpect(jsonPath("$.icd10diagnosisCode9").value(DEFAULT_ICD_10_DIAGNOSIS_CODE_9))
            .andExpect(jsonPath("$.icd10diagnosisCode10").value(DEFAULT_ICD_10_DIAGNOSIS_CODE_10))
            .andExpect(jsonPath("$.icd10diagnosisCode11").value(DEFAULT_ICD_10_DIAGNOSIS_CODE_11))
            .andExpect(jsonPath("$.icd10diagnosisCode12").value(DEFAULT_ICD_10_DIAGNOSIS_CODE_12))
            .andExpect(jsonPath("$.insertedById").value(DEFAULT_INSERTED_BY_ID.intValue()))
            .andExpect(jsonPath("$.insertedDate").value(sameInstant(DEFAULT_INSERTED_DATE)))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedDate").value(sameInstant(DEFAULT_UPDATED_DATE)))
            .andExpect(jsonPath("$.billingProviderType").value(DEFAULT_BILLING_PROVIDER_TYPE))
            .andExpect(jsonPath("$.insertedByName").value(DEFAULT_INSERTED_BY_NAME))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.billingProviderAddressLine2").value(DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_2))
            .andExpect(jsonPath("$.payerClaimControlNumber").value(DEFAULT_PAYER_CLAIM_CONTROL_NUMBER))
            .andExpect(jsonPath("$.insuredFirstName").value(DEFAULT_INSURED_FIRST_NAME))
            .andExpect(jsonPath("$.insuredLastName").value(DEFAULT_INSURED_LAST_NAME))
            .andExpect(jsonPath("$.insuredAddressLine1").value(DEFAULT_INSURED_ADDRESS_LINE_1))
            .andExpect(jsonPath("$.insuredAddressLine2").value(DEFAULT_INSURED_ADDRESS_LINE_2))
            .andExpect(jsonPath("$.insuredCity").value(DEFAULT_INSURED_CITY))
            .andExpect(jsonPath("$.insuredState").value(DEFAULT_INSURED_STATE))
            .andExpect(jsonPath("$.insuredZip").value(DEFAULT_INSURED_ZIP))
            .andExpect(jsonPath("$.insuredContactNo").value(DEFAULT_INSURED_CONTACT_NO))
            .andExpect(jsonPath("$.insuredDob").value(DEFAULT_INSURED_DOB.toString()))
            .andExpect(jsonPath("$.insuredGender").value(DEFAULT_INSURED_GENDER))
            .andExpect(jsonPath("$.orderingProviderFirstName").value(DEFAULT_ORDERING_PROVIDER_FIRST_NAME))
            .andExpect(jsonPath("$.orderingProviderLastName").value(DEFAULT_ORDERING_PROVIDER_LAST_NAME))
            .andExpect(jsonPath("$.orderingProviderNpi").value(DEFAULT_ORDERING_PROVIDER_NPI))
            .andExpect(jsonPath("$.patientRelationshipInsured").value(DEFAULT_PATIENT_RELATIONSHIP_INSURED))
            .andExpect(jsonPath("$.patientConditionEmployment").value(DEFAULT_PATIENT_CONDITION_EMPLOYMENT))
            .andExpect(jsonPath("$.patientConditionAutoAccident").value(DEFAULT_PATIENT_CONDITION_AUTO_ACCIDENT))
            .andExpect(jsonPath("$.patientConditionOtherAccident").value(DEFAULT_PATIENT_CONDITION_OTHER_ACCIDENT))
            .andExpect(jsonPath("$.isNextLevelInsurerPresentStatus").value(DEFAULT_IS_NEXT_LEVEL_INSURER_PRESENT_STATUS))
            .andExpect(jsonPath("$.originalDos").value(DEFAULT_ORIGINAL_DOS.toString()))
            .andExpect(jsonPath("$.parNo").value(DEFAULT_PAR_NO))
            .andExpect(jsonPath("$.billingProviderTaxonomy").value(DEFAULT_BILLING_PROVIDER_TAXONOMY))
            .andExpect(jsonPath("$.serviceProviderNpi").value(DEFAULT_SERVICE_PROVIDER_NPI))
            .andExpect(jsonPath("$.serviceProviderOrganisationName").value(DEFAULT_SERVICE_PROVIDER_ORGANISATION_NAME))
            .andExpect(jsonPath("$.serviceProviderAddressLine1").value(DEFAULT_SERVICE_PROVIDER_ADDRESS_LINE_1))
            .andExpect(jsonPath("$.serviceProviderAddressLine2").value(DEFAULT_SERVICE_PROVIDER_ADDRESS_LINE_2))
            .andExpect(jsonPath("$.serviceProviderCity").value(DEFAULT_SERVICE_PROVIDER_CITY))
            .andExpect(jsonPath("$.serviceProviderState").value(DEFAULT_SERVICE_PROVIDER_STATE))
            .andExpect(jsonPath("$.serviceProviderCountry").value(DEFAULT_SERVICE_PROVIDER_COUNTRY))
            .andExpect(jsonPath("$.serviceProviderZipCode").value(DEFAULT_SERVICE_PROVIDER_ZIP_CODE))
            .andExpect(jsonPath("$.serviceProviderTaxonomy").value(DEFAULT_SERVICE_PROVIDER_TAXONOMY))
            .andExpect(jsonPath("$.cms1500FormName").value(DEFAULT_CMS_1500_FORM_NAME))
            .andExpect(jsonPath("$.tradingPartnerAddressLine1").value(DEFAULT_TRADING_PARTNER_ADDRESS_LINE_1))
            .andExpect(jsonPath("$.tradingPartnerAddressLine2").value(DEFAULT_TRADING_PARTNER_ADDRESS_LINE_2))
            .andExpect(jsonPath("$.tradingPartnerCity").value(DEFAULT_TRADING_PARTNER_CITY))
            .andExpect(jsonPath("$.tradingPartnerState").value(DEFAULT_TRADING_PARTNER_STATE))
            .andExpect(jsonPath("$.tradingPartnerZip").value(DEFAULT_TRADING_PARTNER_ZIP))
            .andExpect(jsonPath("$.diagnosisCodeType").value(DEFAULT_DIAGNOSIS_CODE_TYPE));
    }

    @Test
    @Transactional
    void getNonExistingPrimaryClaimsReSubmissionMaster() throws Exception {
        // Get the primaryClaimsReSubmissionMaster
        restPrimaryClaimsReSubmissionMasterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewPrimaryClaimsReSubmissionMaster() throws Exception {
        // Initialize the database
        primaryClaimsReSubmissionMasterRepository.saveAndFlush(primaryClaimsReSubmissionMaster);

        int databaseSizeBeforeUpdate = primaryClaimsReSubmissionMasterRepository.findAll().size();

        // Update the primaryClaimsReSubmissionMaster
        PrimaryClaimsReSubmissionMaster updatedPrimaryClaimsReSubmissionMaster = primaryClaimsReSubmissionMasterRepository
            .findById(primaryClaimsReSubmissionMaster.getChangeHealthPrimaryResubmisionMasterId())
            .get();
        // Disconnect from session so that the updates on updatedPrimaryClaimsReSubmissionMaster are not directly saved in db
        em.detach(updatedPrimaryClaimsReSubmissionMaster);
        updatedPrimaryClaimsReSubmissionMaster
            .salesOrderId(UPDATED_SALES_ORDER_ID)
            .claimControlNo(UPDATED_CLAIM_CONTROL_NO)
            .tradingPartnerServiceId(UPDATED_TRADING_PARTNER_SERVICE_ID)
            .tradingPartnerName(UPDATED_TRADING_PARTNER_NAME)
            .submitterOrganizationName(UPDATED_SUBMITTER_ORGANIZATION_NAME)
            .submitterContactPersonName(UPDATED_SUBMITTER_CONTACT_PERSON_NAME)
            .submitterContactNo(UPDATED_SUBMITTER_CONTACT_NO)
            .receiverOrganizationName(UPDATED_RECEIVER_ORGANIZATION_NAME)
            .subscriberMemberIdNo(UPDATED_SUBSCRIBER_MEMBER_ID_NO)
            .subscriberPaymentResponsibilityLevelCode(UPDATED_SUBSCRIBER_PAYMENT_RESPONSIBILITY_LEVEL_CODE)
            .subscriberFirstName(UPDATED_SUBSCRIBER_FIRST_NAME)
            .subscriberLastName(UPDATED_SUBSCRIBER_LAST_NAME)
            .subscriberGender(UPDATED_SUBSCRIBER_GENDER)
            .subscriberDob(UPDATED_SUBSCRIBER_DOB)
            .primaryInsurerPolicyNo(UPDATED_PRIMARY_INSURER_POLICY_NO)
            .subscriberAddressLine1(UPDATED_SUBSCRIBER_ADDRESS_LINE_1)
            .subscriberCity(UPDATED_SUBSCRIBER_CITY)
            .subscriberState(UPDATED_SUBSCRIBER_STATE)
            .subscriberZipCode(UPDATED_SUBSCRIBER_ZIP_CODE)
            .billingProviderNpi(UPDATED_BILLING_PROVIDER_NPI)
            .billingProviderEin(UPDATED_BILLING_PROVIDER_EIN)
            .billingProviderOrganizationName(UPDATED_BILLING_PROVIDER_ORGANIZATION_NAME)
            .billingProviderAddressLine1(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_1)
            .billingProviderCity(UPDATED_BILLING_PROVIDER_CITY)
            .billingProviderState(UPDATED_BILLING_PROVIDER_STATE)
            .billingProviderZipCode(UPDATED_BILLING_PROVIDER_ZIP_CODE)
            .billingProviderContactPersonName(UPDATED_BILLING_PROVIDER_CONTACT_PERSON_NAME)
            .billingProviderContactNo(UPDATED_BILLING_PROVIDER_CONTACT_NO)
            .claimFilingCode(UPDATED_CLAIM_FILING_CODE)
            .claimChargeAmount(UPDATED_CLAIM_CHARGE_AMOUNT)
            .posCode(UPDATED_POS_CODE)
            .claimFrequencyCode(UPDATED_CLAIM_FREQUENCY_CODE)
            .signatureIndicator(UPDATED_SIGNATURE_INDICATOR)
            .planParticipationCode(UPDATED_PLAN_PARTICIPATION_CODE)
            .benefitsAssignmentCertificationIndicator(UPDATED_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR)
            .releaseInformationCode(UPDATED_RELEASE_INFORMATION_CODE)
            .primaryDiagnosis(UPDATED_PRIMARY_DIAGNOSIS)
            .icd10diagnosisCode1(UPDATED_ICD_10_DIAGNOSIS_CODE_1)
            .icd10diagnosisCode2(UPDATED_ICD_10_DIAGNOSIS_CODE_2)
            .icd10diagnosisCode3(UPDATED_ICD_10_DIAGNOSIS_CODE_3)
            .icd10diagnosisCode4(UPDATED_ICD_10_DIAGNOSIS_CODE_4)
            .icd10diagnosisCode5(UPDATED_ICD_10_DIAGNOSIS_CODE_5)
            .icd10diagnosisCode6(UPDATED_ICD_10_DIAGNOSIS_CODE_6)
            .icd10diagnosisCode7(UPDATED_ICD_10_DIAGNOSIS_CODE_7)
            .icd10diagnosisCode8(UPDATED_ICD_10_DIAGNOSIS_CODE_8)
            .icd10diagnosisCode9(UPDATED_ICD_10_DIAGNOSIS_CODE_9)
            .icd10diagnosisCode10(UPDATED_ICD_10_DIAGNOSIS_CODE_10)
            .icd10diagnosisCode11(UPDATED_ICD_10_DIAGNOSIS_CODE_11)
            .icd10diagnosisCode12(UPDATED_ICD_10_DIAGNOSIS_CODE_12)
            .insertedById(UPDATED_INSERTED_BY_ID)
            .insertedDate(UPDATED_INSERTED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .billingProviderType(UPDATED_BILLING_PROVIDER_TYPE)
            .insertedByName(UPDATED_INSERTED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .status(UPDATED_STATUS)
            .billingProviderAddressLine2(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_2)
            .payerClaimControlNumber(UPDATED_PAYER_CLAIM_CONTROL_NUMBER)
            .insuredFirstName(UPDATED_INSURED_FIRST_NAME)
            .insuredLastName(UPDATED_INSURED_LAST_NAME)
            .insuredAddressLine1(UPDATED_INSURED_ADDRESS_LINE_1)
            .insuredAddressLine2(UPDATED_INSURED_ADDRESS_LINE_2)
            .insuredCity(UPDATED_INSURED_CITY)
            .insuredState(UPDATED_INSURED_STATE)
            .insuredZip(UPDATED_INSURED_ZIP)
            .insuredContactNo(UPDATED_INSURED_CONTACT_NO)
            .insuredDob(UPDATED_INSURED_DOB)
            .insuredGender(UPDATED_INSURED_GENDER)
            .orderingProviderFirstName(UPDATED_ORDERING_PROVIDER_FIRST_NAME)
            .orderingProviderLastName(UPDATED_ORDERING_PROVIDER_LAST_NAME)
            .orderingProviderNpi(UPDATED_ORDERING_PROVIDER_NPI)
            .patientRelationshipInsured(UPDATED_PATIENT_RELATIONSHIP_INSURED)
            .patientConditionEmployment(UPDATED_PATIENT_CONDITION_EMPLOYMENT)
            .patientConditionAutoAccident(UPDATED_PATIENT_CONDITION_AUTO_ACCIDENT)
            .patientConditionOtherAccident(UPDATED_PATIENT_CONDITION_OTHER_ACCIDENT)
            .isNextLevelInsurerPresentStatus(UPDATED_IS_NEXT_LEVEL_INSURER_PRESENT_STATUS)
            .originalDos(UPDATED_ORIGINAL_DOS)
            .parNo(UPDATED_PAR_NO)
            .billingProviderTaxonomy(UPDATED_BILLING_PROVIDER_TAXONOMY)
            .serviceProviderNpi(UPDATED_SERVICE_PROVIDER_NPI)
            .serviceProviderOrganisationName(UPDATED_SERVICE_PROVIDER_ORGANISATION_NAME)
            .serviceProviderAddressLine1(UPDATED_SERVICE_PROVIDER_ADDRESS_LINE_1)
            .serviceProviderAddressLine2(UPDATED_SERVICE_PROVIDER_ADDRESS_LINE_2)
            .serviceProviderCity(UPDATED_SERVICE_PROVIDER_CITY)
            .serviceProviderState(UPDATED_SERVICE_PROVIDER_STATE)
            .serviceProviderCountry(UPDATED_SERVICE_PROVIDER_COUNTRY)
            .serviceProviderZipCode(UPDATED_SERVICE_PROVIDER_ZIP_CODE)
            .serviceProviderTaxonomy(UPDATED_SERVICE_PROVIDER_TAXONOMY)
            .cms1500FormName(UPDATED_CMS_1500_FORM_NAME)
            .tradingPartnerAddressLine1(UPDATED_TRADING_PARTNER_ADDRESS_LINE_1)
            .tradingPartnerAddressLine2(UPDATED_TRADING_PARTNER_ADDRESS_LINE_2)
            .tradingPartnerCity(UPDATED_TRADING_PARTNER_CITY)
            .tradingPartnerState(UPDATED_TRADING_PARTNER_STATE)
            .tradingPartnerZip(UPDATED_TRADING_PARTNER_ZIP)
            .diagnosisCodeType(UPDATED_DIAGNOSIS_CODE_TYPE);
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            updatedPrimaryClaimsReSubmissionMaster
        );

        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, primaryClaimsReSubmissionMasterDTO.getChangeHealthPrimaryResubmisionMasterId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isOk());

        // Validate the PrimaryClaimsReSubmissionMaster in the database
        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeUpdate);
        PrimaryClaimsReSubmissionMaster testPrimaryClaimsReSubmissionMaster = primaryClaimsReSubmissionMasterList.get(
            primaryClaimsReSubmissionMasterList.size() - 1
        );
        assertThat(testPrimaryClaimsReSubmissionMaster.getSalesOrderId()).isEqualTo(UPDATED_SALES_ORDER_ID);
        assertThat(testPrimaryClaimsReSubmissionMaster.getClaimControlNo()).isEqualTo(UPDATED_CLAIM_CONTROL_NO);
        assertThat(testPrimaryClaimsReSubmissionMaster.getTradingPartnerServiceId()).isEqualTo(UPDATED_TRADING_PARTNER_SERVICE_ID);
        assertThat(testPrimaryClaimsReSubmissionMaster.getTradingPartnerName()).isEqualTo(UPDATED_TRADING_PARTNER_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubmitterOrganizationName()).isEqualTo(UPDATED_SUBMITTER_ORGANIZATION_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubmitterContactPersonName()).isEqualTo(UPDATED_SUBMITTER_CONTACT_PERSON_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubmitterContactNo()).isEqualTo(UPDATED_SUBMITTER_CONTACT_NO);
        assertThat(testPrimaryClaimsReSubmissionMaster.getReceiverOrganizationName()).isEqualTo(UPDATED_RECEIVER_ORGANIZATION_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubscriberMemberIdNo()).isEqualTo(UPDATED_SUBSCRIBER_MEMBER_ID_NO);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubscriberPaymentResponsibilityLevelCode())
            .isEqualTo(UPDATED_SUBSCRIBER_PAYMENT_RESPONSIBILITY_LEVEL_CODE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubscriberFirstName()).isEqualTo(UPDATED_SUBSCRIBER_FIRST_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubscriberLastName()).isEqualTo(UPDATED_SUBSCRIBER_LAST_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubscriberGender()).isEqualTo(UPDATED_SUBSCRIBER_GENDER);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubscriberDob()).isEqualTo(UPDATED_SUBSCRIBER_DOB);
        assertThat(testPrimaryClaimsReSubmissionMaster.getPrimaryInsurerPolicyNo()).isEqualTo(UPDATED_PRIMARY_INSURER_POLICY_NO);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubscriberAddressLine1()).isEqualTo(UPDATED_SUBSCRIBER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubscriberCity()).isEqualTo(UPDATED_SUBSCRIBER_CITY);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubscriberState()).isEqualTo(UPDATED_SUBSCRIBER_STATE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubscriberZipCode()).isEqualTo(UPDATED_SUBSCRIBER_ZIP_CODE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderNpi()).isEqualTo(UPDATED_BILLING_PROVIDER_NPI);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderEin()).isEqualTo(UPDATED_BILLING_PROVIDER_EIN);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderOrganizationName())
            .isEqualTo(UPDATED_BILLING_PROVIDER_ORGANIZATION_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderAddressLine1()).isEqualTo(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderCity()).isEqualTo(UPDATED_BILLING_PROVIDER_CITY);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderState()).isEqualTo(UPDATED_BILLING_PROVIDER_STATE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderZipCode()).isEqualTo(UPDATED_BILLING_PROVIDER_ZIP_CODE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderContactPersonName())
            .isEqualTo(UPDATED_BILLING_PROVIDER_CONTACT_PERSON_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderContactNo()).isEqualTo(UPDATED_BILLING_PROVIDER_CONTACT_NO);
        assertThat(testPrimaryClaimsReSubmissionMaster.getClaimFilingCode()).isEqualTo(UPDATED_CLAIM_FILING_CODE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getClaimChargeAmount()).isEqualTo(UPDATED_CLAIM_CHARGE_AMOUNT);
        assertThat(testPrimaryClaimsReSubmissionMaster.getPosCode()).isEqualTo(UPDATED_POS_CODE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getClaimFrequencyCode()).isEqualTo(UPDATED_CLAIM_FREQUENCY_CODE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSignatureIndicator()).isEqualTo(UPDATED_SIGNATURE_INDICATOR);
        assertThat(testPrimaryClaimsReSubmissionMaster.getPlanParticipationCode()).isEqualTo(UPDATED_PLAN_PARTICIPATION_CODE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBenefitsAssignmentCertificationIndicator())
            .isEqualTo(UPDATED_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR);
        assertThat(testPrimaryClaimsReSubmissionMaster.getReleaseInformationCode()).isEqualTo(UPDATED_RELEASE_INFORMATION_CODE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getPrimaryDiagnosis()).isEqualTo(UPDATED_PRIMARY_DIAGNOSIS);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode1()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_1);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode2()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_2);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode3()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_3);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode4()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_4);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode5()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_5);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode6()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_6);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode7()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_7);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode8()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_8);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode9()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_9);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode10()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_10);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode11()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_11);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode12()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_12);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsertedById()).isEqualTo(UPDATED_INSERTED_BY_ID);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsertedDate()).isEqualTo(UPDATED_INSERTED_DATE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPrimaryClaimsReSubmissionMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderType()).isEqualTo(UPDATED_BILLING_PROVIDER_TYPE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsertedByName()).isEqualTo(UPDATED_INSERTED_BY_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderAddressLine2()).isEqualTo(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimsReSubmissionMaster.getPayerClaimControlNumber()).isEqualTo(UPDATED_PAYER_CLAIM_CONTROL_NUMBER);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsuredFirstName()).isEqualTo(UPDATED_INSURED_FIRST_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsuredLastName()).isEqualTo(UPDATED_INSURED_LAST_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsuredAddressLine1()).isEqualTo(UPDATED_INSURED_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsuredAddressLine2()).isEqualTo(UPDATED_INSURED_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsuredCity()).isEqualTo(UPDATED_INSURED_CITY);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsuredState()).isEqualTo(UPDATED_INSURED_STATE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsuredZip()).isEqualTo(UPDATED_INSURED_ZIP);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsuredContactNo()).isEqualTo(UPDATED_INSURED_CONTACT_NO);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsuredDob()).isEqualTo(UPDATED_INSURED_DOB);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsuredGender()).isEqualTo(UPDATED_INSURED_GENDER);
        assertThat(testPrimaryClaimsReSubmissionMaster.getOrderingProviderFirstName()).isEqualTo(UPDATED_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getOrderingProviderLastName()).isEqualTo(UPDATED_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getOrderingProviderNpi()).isEqualTo(UPDATED_ORDERING_PROVIDER_NPI);
        assertThat(testPrimaryClaimsReSubmissionMaster.getPatientRelationshipInsured()).isEqualTo(UPDATED_PATIENT_RELATIONSHIP_INSURED);
        assertThat(testPrimaryClaimsReSubmissionMaster.getPatientConditionEmployment()).isEqualTo(UPDATED_PATIENT_CONDITION_EMPLOYMENT);
        assertThat(testPrimaryClaimsReSubmissionMaster.getPatientConditionAutoAccident())
            .isEqualTo(UPDATED_PATIENT_CONDITION_AUTO_ACCIDENT);
        assertThat(testPrimaryClaimsReSubmissionMaster.getPatientConditionOtherAccident())
            .isEqualTo(UPDATED_PATIENT_CONDITION_OTHER_ACCIDENT);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIsNextLevelInsurerPresentStatus())
            .isEqualTo(UPDATED_IS_NEXT_LEVEL_INSURER_PRESENT_STATUS);
        assertThat(testPrimaryClaimsReSubmissionMaster.getOriginalDos()).isEqualTo(UPDATED_ORIGINAL_DOS);
        assertThat(testPrimaryClaimsReSubmissionMaster.getParNo()).isEqualTo(UPDATED_PAR_NO);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderTaxonomy()).isEqualTo(UPDATED_BILLING_PROVIDER_TAXONOMY);
        assertThat(testPrimaryClaimsReSubmissionMaster.getServiceProviderNpi()).isEqualTo(UPDATED_SERVICE_PROVIDER_NPI);
        assertThat(testPrimaryClaimsReSubmissionMaster.getServiceProviderOrganisationName())
            .isEqualTo(UPDATED_SERVICE_PROVIDER_ORGANISATION_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getServiceProviderAddressLine1()).isEqualTo(UPDATED_SERVICE_PROVIDER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimsReSubmissionMaster.getServiceProviderAddressLine2()).isEqualTo(UPDATED_SERVICE_PROVIDER_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimsReSubmissionMaster.getServiceProviderCity()).isEqualTo(UPDATED_SERVICE_PROVIDER_CITY);
        assertThat(testPrimaryClaimsReSubmissionMaster.getServiceProviderState()).isEqualTo(UPDATED_SERVICE_PROVIDER_STATE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getServiceProviderCountry()).isEqualTo(UPDATED_SERVICE_PROVIDER_COUNTRY);
        assertThat(testPrimaryClaimsReSubmissionMaster.getServiceProviderZipCode()).isEqualTo(UPDATED_SERVICE_PROVIDER_ZIP_CODE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getServiceProviderTaxonomy()).isEqualTo(UPDATED_SERVICE_PROVIDER_TAXONOMY);
        assertThat(testPrimaryClaimsReSubmissionMaster.getCms1500FormName()).isEqualTo(UPDATED_CMS_1500_FORM_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getTradingPartnerAddressLine1()).isEqualTo(UPDATED_TRADING_PARTNER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimsReSubmissionMaster.getTradingPartnerAddressLine2()).isEqualTo(UPDATED_TRADING_PARTNER_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimsReSubmissionMaster.getTradingPartnerCity()).isEqualTo(UPDATED_TRADING_PARTNER_CITY);
        assertThat(testPrimaryClaimsReSubmissionMaster.getTradingPartnerState()).isEqualTo(UPDATED_TRADING_PARTNER_STATE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getTradingPartnerZip()).isEqualTo(UPDATED_TRADING_PARTNER_ZIP);
        assertThat(testPrimaryClaimsReSubmissionMaster.getDiagnosisCodeType()).isEqualTo(UPDATED_DIAGNOSIS_CODE_TYPE);
    }

    @Test
    @Transactional
    void putNonExistingPrimaryClaimsReSubmissionMaster() throws Exception {
        int databaseSizeBeforeUpdate = primaryClaimsReSubmissionMasterRepository.findAll().size();
        primaryClaimsReSubmissionMaster.setChangeHealthPrimaryResubmisionMasterId(count.incrementAndGet());

        // Create the PrimaryClaimsReSubmissionMaster
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, primaryClaimsReSubmissionMasterDTO.getChangeHealthPrimaryResubmisionMasterId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PrimaryClaimsReSubmissionMaster in the database
        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPrimaryClaimsReSubmissionMaster() throws Exception {
        int databaseSizeBeforeUpdate = primaryClaimsReSubmissionMasterRepository.findAll().size();
        primaryClaimsReSubmissionMaster.setChangeHealthPrimaryResubmisionMasterId(count.incrementAndGet());

        // Create the PrimaryClaimsReSubmissionMaster
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PrimaryClaimsReSubmissionMaster in the database
        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPrimaryClaimsReSubmissionMaster() throws Exception {
        int databaseSizeBeforeUpdate = primaryClaimsReSubmissionMasterRepository.findAll().size();
        primaryClaimsReSubmissionMaster.setChangeHealthPrimaryResubmisionMasterId(count.incrementAndGet());

        // Create the PrimaryClaimsReSubmissionMaster
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PrimaryClaimsReSubmissionMaster in the database
        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePrimaryClaimsReSubmissionMasterWithPatch() throws Exception {
        // Initialize the database
        primaryClaimsReSubmissionMasterRepository.saveAndFlush(primaryClaimsReSubmissionMaster);

        int databaseSizeBeforeUpdate = primaryClaimsReSubmissionMasterRepository.findAll().size();

        // Update the primaryClaimsReSubmissionMaster using partial update
        PrimaryClaimsReSubmissionMaster partialUpdatedPrimaryClaimsReSubmissionMaster = new PrimaryClaimsReSubmissionMaster();
        partialUpdatedPrimaryClaimsReSubmissionMaster.setChangeHealthPrimaryResubmisionMasterId(
            primaryClaimsReSubmissionMaster.getChangeHealthPrimaryResubmisionMasterId()
        );

        partialUpdatedPrimaryClaimsReSubmissionMaster
            .salesOrderId(UPDATED_SALES_ORDER_ID)
            .receiverOrganizationName(UPDATED_RECEIVER_ORGANIZATION_NAME)
            .subscriberLastName(UPDATED_SUBSCRIBER_LAST_NAME)
            .subscriberGender(UPDATED_SUBSCRIBER_GENDER)
            .subscriberDob(UPDATED_SUBSCRIBER_DOB)
            .primaryInsurerPolicyNo(UPDATED_PRIMARY_INSURER_POLICY_NO)
            .subscriberCity(UPDATED_SUBSCRIBER_CITY)
            .subscriberZipCode(UPDATED_SUBSCRIBER_ZIP_CODE)
            .billingProviderNpi(UPDATED_BILLING_PROVIDER_NPI)
            .billingProviderCity(UPDATED_BILLING_PROVIDER_CITY)
            .billingProviderState(UPDATED_BILLING_PROVIDER_STATE)
            .billingProviderContactPersonName(UPDATED_BILLING_PROVIDER_CONTACT_PERSON_NAME)
            .claimFilingCode(UPDATED_CLAIM_FILING_CODE)
            .claimChargeAmount(UPDATED_CLAIM_CHARGE_AMOUNT)
            .posCode(UPDATED_POS_CODE)
            .claimFrequencyCode(UPDATED_CLAIM_FREQUENCY_CODE)
            .signatureIndicator(UPDATED_SIGNATURE_INDICATOR)
            .planParticipationCode(UPDATED_PLAN_PARTICIPATION_CODE)
            .benefitsAssignmentCertificationIndicator(UPDATED_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR)
            .primaryDiagnosis(UPDATED_PRIMARY_DIAGNOSIS)
            .icd10diagnosisCode1(UPDATED_ICD_10_DIAGNOSIS_CODE_1)
            .icd10diagnosisCode2(UPDATED_ICD_10_DIAGNOSIS_CODE_2)
            .icd10diagnosisCode4(UPDATED_ICD_10_DIAGNOSIS_CODE_4)
            .icd10diagnosisCode8(UPDATED_ICD_10_DIAGNOSIS_CODE_8)
            .icd10diagnosisCode9(UPDATED_ICD_10_DIAGNOSIS_CODE_9)
            .icd10diagnosisCode11(UPDATED_ICD_10_DIAGNOSIS_CODE_11)
            .insertedById(UPDATED_INSERTED_BY_ID)
            .insertedDate(UPDATED_INSERTED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .billingProviderType(UPDATED_BILLING_PROVIDER_TYPE)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .billingProviderAddressLine2(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_2)
            .insuredLastName(UPDATED_INSURED_LAST_NAME)
            .insuredAddressLine1(UPDATED_INSURED_ADDRESS_LINE_1)
            .insuredAddressLine2(UPDATED_INSURED_ADDRESS_LINE_2)
            .insuredState(UPDATED_INSURED_STATE)
            .patientConditionEmployment(UPDATED_PATIENT_CONDITION_EMPLOYMENT)
            .patientConditionAutoAccident(UPDATED_PATIENT_CONDITION_AUTO_ACCIDENT)
            .patientConditionOtherAccident(UPDATED_PATIENT_CONDITION_OTHER_ACCIDENT)
            .billingProviderTaxonomy(UPDATED_BILLING_PROVIDER_TAXONOMY)
            .serviceProviderAddressLine1(UPDATED_SERVICE_PROVIDER_ADDRESS_LINE_1)
            .serviceProviderAddressLine2(UPDATED_SERVICE_PROVIDER_ADDRESS_LINE_2)
            .serviceProviderState(UPDATED_SERVICE_PROVIDER_STATE)
            .serviceProviderZipCode(UPDATED_SERVICE_PROVIDER_ZIP_CODE)
            .serviceProviderTaxonomy(UPDATED_SERVICE_PROVIDER_TAXONOMY)
            .cms1500FormName(UPDATED_CMS_1500_FORM_NAME)
            .tradingPartnerAddressLine1(UPDATED_TRADING_PARTNER_ADDRESS_LINE_1)
            .tradingPartnerAddressLine2(UPDATED_TRADING_PARTNER_ADDRESS_LINE_2);

        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPrimaryClaimsReSubmissionMaster.getChangeHealthPrimaryResubmisionMasterId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPrimaryClaimsReSubmissionMaster))
            )
            .andExpect(status().isOk());

        // Validate the PrimaryClaimsReSubmissionMaster in the database
        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeUpdate);
        PrimaryClaimsReSubmissionMaster testPrimaryClaimsReSubmissionMaster = primaryClaimsReSubmissionMasterList.get(
            primaryClaimsReSubmissionMasterList.size() - 1
        );
        assertThat(testPrimaryClaimsReSubmissionMaster.getSalesOrderId()).isEqualTo(UPDATED_SALES_ORDER_ID);
        assertThat(testPrimaryClaimsReSubmissionMaster.getClaimControlNo()).isEqualTo(DEFAULT_CLAIM_CONTROL_NO);
        assertThat(testPrimaryClaimsReSubmissionMaster.getTradingPartnerServiceId()).isEqualTo(DEFAULT_TRADING_PARTNER_SERVICE_ID);
        assertThat(testPrimaryClaimsReSubmissionMaster.getTradingPartnerName()).isEqualTo(DEFAULT_TRADING_PARTNER_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubmitterOrganizationName()).isEqualTo(DEFAULT_SUBMITTER_ORGANIZATION_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubmitterContactPersonName()).isEqualTo(DEFAULT_SUBMITTER_CONTACT_PERSON_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubmitterContactNo()).isEqualTo(DEFAULT_SUBMITTER_CONTACT_NO);
        assertThat(testPrimaryClaimsReSubmissionMaster.getReceiverOrganizationName()).isEqualTo(UPDATED_RECEIVER_ORGANIZATION_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubscriberMemberIdNo()).isEqualTo(DEFAULT_SUBSCRIBER_MEMBER_ID_NO);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubscriberPaymentResponsibilityLevelCode())
            .isEqualTo(DEFAULT_SUBSCRIBER_PAYMENT_RESPONSIBILITY_LEVEL_CODE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubscriberFirstName()).isEqualTo(DEFAULT_SUBSCRIBER_FIRST_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubscriberLastName()).isEqualTo(UPDATED_SUBSCRIBER_LAST_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubscriberGender()).isEqualTo(UPDATED_SUBSCRIBER_GENDER);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubscriberDob()).isEqualTo(UPDATED_SUBSCRIBER_DOB);
        assertThat(testPrimaryClaimsReSubmissionMaster.getPrimaryInsurerPolicyNo()).isEqualTo(UPDATED_PRIMARY_INSURER_POLICY_NO);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubscriberAddressLine1()).isEqualTo(DEFAULT_SUBSCRIBER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubscriberCity()).isEqualTo(UPDATED_SUBSCRIBER_CITY);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubscriberState()).isEqualTo(DEFAULT_SUBSCRIBER_STATE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubscriberZipCode()).isEqualTo(UPDATED_SUBSCRIBER_ZIP_CODE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderNpi()).isEqualTo(UPDATED_BILLING_PROVIDER_NPI);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderEin()).isEqualTo(DEFAULT_BILLING_PROVIDER_EIN);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderOrganizationName())
            .isEqualTo(DEFAULT_BILLING_PROVIDER_ORGANIZATION_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderAddressLine1()).isEqualTo(DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderCity()).isEqualTo(UPDATED_BILLING_PROVIDER_CITY);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderState()).isEqualTo(UPDATED_BILLING_PROVIDER_STATE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderZipCode()).isEqualTo(DEFAULT_BILLING_PROVIDER_ZIP_CODE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderContactPersonName())
            .isEqualTo(UPDATED_BILLING_PROVIDER_CONTACT_PERSON_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderContactNo()).isEqualTo(DEFAULT_BILLING_PROVIDER_CONTACT_NO);
        assertThat(testPrimaryClaimsReSubmissionMaster.getClaimFilingCode()).isEqualTo(UPDATED_CLAIM_FILING_CODE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getClaimChargeAmount()).isEqualTo(UPDATED_CLAIM_CHARGE_AMOUNT);
        assertThat(testPrimaryClaimsReSubmissionMaster.getPosCode()).isEqualTo(UPDATED_POS_CODE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getClaimFrequencyCode()).isEqualTo(UPDATED_CLAIM_FREQUENCY_CODE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSignatureIndicator()).isEqualTo(UPDATED_SIGNATURE_INDICATOR);
        assertThat(testPrimaryClaimsReSubmissionMaster.getPlanParticipationCode()).isEqualTo(UPDATED_PLAN_PARTICIPATION_CODE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBenefitsAssignmentCertificationIndicator())
            .isEqualTo(UPDATED_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR);
        assertThat(testPrimaryClaimsReSubmissionMaster.getReleaseInformationCode()).isEqualTo(DEFAULT_RELEASE_INFORMATION_CODE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getPrimaryDiagnosis()).isEqualTo(UPDATED_PRIMARY_DIAGNOSIS);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode1()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_1);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode2()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_2);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode3()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_3);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode4()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_4);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode5()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_5);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode6()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_6);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode7()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_7);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode8()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_8);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode9()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_9);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode10()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_10);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode11()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_11);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode12()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_12);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsertedById()).isEqualTo(UPDATED_INSERTED_BY_ID);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsertedDate()).isEqualTo(UPDATED_INSERTED_DATE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPrimaryClaimsReSubmissionMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderType()).isEqualTo(UPDATED_BILLING_PROVIDER_TYPE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsertedByName()).isEqualTo(DEFAULT_INSERTED_BY_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderAddressLine2()).isEqualTo(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimsReSubmissionMaster.getPayerClaimControlNumber()).isEqualTo(DEFAULT_PAYER_CLAIM_CONTROL_NUMBER);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsuredFirstName()).isEqualTo(DEFAULT_INSURED_FIRST_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsuredLastName()).isEqualTo(UPDATED_INSURED_LAST_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsuredAddressLine1()).isEqualTo(UPDATED_INSURED_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsuredAddressLine2()).isEqualTo(UPDATED_INSURED_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsuredCity()).isEqualTo(DEFAULT_INSURED_CITY);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsuredState()).isEqualTo(UPDATED_INSURED_STATE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsuredZip()).isEqualTo(DEFAULT_INSURED_ZIP);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsuredContactNo()).isEqualTo(DEFAULT_INSURED_CONTACT_NO);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsuredDob()).isEqualTo(DEFAULT_INSURED_DOB);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsuredGender()).isEqualTo(DEFAULT_INSURED_GENDER);
        assertThat(testPrimaryClaimsReSubmissionMaster.getOrderingProviderFirstName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getOrderingProviderLastName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getOrderingProviderNpi()).isEqualTo(DEFAULT_ORDERING_PROVIDER_NPI);
        assertThat(testPrimaryClaimsReSubmissionMaster.getPatientRelationshipInsured()).isEqualTo(DEFAULT_PATIENT_RELATIONSHIP_INSURED);
        assertThat(testPrimaryClaimsReSubmissionMaster.getPatientConditionEmployment()).isEqualTo(UPDATED_PATIENT_CONDITION_EMPLOYMENT);
        assertThat(testPrimaryClaimsReSubmissionMaster.getPatientConditionAutoAccident())
            .isEqualTo(UPDATED_PATIENT_CONDITION_AUTO_ACCIDENT);
        assertThat(testPrimaryClaimsReSubmissionMaster.getPatientConditionOtherAccident())
            .isEqualTo(UPDATED_PATIENT_CONDITION_OTHER_ACCIDENT);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIsNextLevelInsurerPresentStatus())
            .isEqualTo(DEFAULT_IS_NEXT_LEVEL_INSURER_PRESENT_STATUS);
        assertThat(testPrimaryClaimsReSubmissionMaster.getOriginalDos()).isEqualTo(DEFAULT_ORIGINAL_DOS);
        assertThat(testPrimaryClaimsReSubmissionMaster.getParNo()).isEqualTo(DEFAULT_PAR_NO);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderTaxonomy()).isEqualTo(UPDATED_BILLING_PROVIDER_TAXONOMY);
        assertThat(testPrimaryClaimsReSubmissionMaster.getServiceProviderNpi()).isEqualTo(DEFAULT_SERVICE_PROVIDER_NPI);
        assertThat(testPrimaryClaimsReSubmissionMaster.getServiceProviderOrganisationName())
            .isEqualTo(DEFAULT_SERVICE_PROVIDER_ORGANISATION_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getServiceProviderAddressLine1()).isEqualTo(UPDATED_SERVICE_PROVIDER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimsReSubmissionMaster.getServiceProviderAddressLine2()).isEqualTo(UPDATED_SERVICE_PROVIDER_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimsReSubmissionMaster.getServiceProviderCity()).isEqualTo(DEFAULT_SERVICE_PROVIDER_CITY);
        assertThat(testPrimaryClaimsReSubmissionMaster.getServiceProviderState()).isEqualTo(UPDATED_SERVICE_PROVIDER_STATE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getServiceProviderCountry()).isEqualTo(DEFAULT_SERVICE_PROVIDER_COUNTRY);
        assertThat(testPrimaryClaimsReSubmissionMaster.getServiceProviderZipCode()).isEqualTo(UPDATED_SERVICE_PROVIDER_ZIP_CODE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getServiceProviderTaxonomy()).isEqualTo(UPDATED_SERVICE_PROVIDER_TAXONOMY);
        assertThat(testPrimaryClaimsReSubmissionMaster.getCms1500FormName()).isEqualTo(UPDATED_CMS_1500_FORM_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getTradingPartnerAddressLine1()).isEqualTo(UPDATED_TRADING_PARTNER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimsReSubmissionMaster.getTradingPartnerAddressLine2()).isEqualTo(UPDATED_TRADING_PARTNER_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimsReSubmissionMaster.getTradingPartnerCity()).isEqualTo(DEFAULT_TRADING_PARTNER_CITY);
        assertThat(testPrimaryClaimsReSubmissionMaster.getTradingPartnerState()).isEqualTo(DEFAULT_TRADING_PARTNER_STATE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getTradingPartnerZip()).isEqualTo(DEFAULT_TRADING_PARTNER_ZIP);
        assertThat(testPrimaryClaimsReSubmissionMaster.getDiagnosisCodeType()).isEqualTo(DEFAULT_DIAGNOSIS_CODE_TYPE);
    }

    @Test
    @Transactional
    void fullUpdatePrimaryClaimsReSubmissionMasterWithPatch() throws Exception {
        // Initialize the database
        primaryClaimsReSubmissionMasterRepository.saveAndFlush(primaryClaimsReSubmissionMaster);

        int databaseSizeBeforeUpdate = primaryClaimsReSubmissionMasterRepository.findAll().size();

        // Update the primaryClaimsReSubmissionMaster using partial update
        PrimaryClaimsReSubmissionMaster partialUpdatedPrimaryClaimsReSubmissionMaster = new PrimaryClaimsReSubmissionMaster();
        partialUpdatedPrimaryClaimsReSubmissionMaster.setChangeHealthPrimaryResubmisionMasterId(
            primaryClaimsReSubmissionMaster.getChangeHealthPrimaryResubmisionMasterId()
        );

        partialUpdatedPrimaryClaimsReSubmissionMaster
            .salesOrderId(UPDATED_SALES_ORDER_ID)
            .claimControlNo(UPDATED_CLAIM_CONTROL_NO)
            .tradingPartnerServiceId(UPDATED_TRADING_PARTNER_SERVICE_ID)
            .tradingPartnerName(UPDATED_TRADING_PARTNER_NAME)
            .submitterOrganizationName(UPDATED_SUBMITTER_ORGANIZATION_NAME)
            .submitterContactPersonName(UPDATED_SUBMITTER_CONTACT_PERSON_NAME)
            .submitterContactNo(UPDATED_SUBMITTER_CONTACT_NO)
            .receiverOrganizationName(UPDATED_RECEIVER_ORGANIZATION_NAME)
            .subscriberMemberIdNo(UPDATED_SUBSCRIBER_MEMBER_ID_NO)
            .subscriberPaymentResponsibilityLevelCode(UPDATED_SUBSCRIBER_PAYMENT_RESPONSIBILITY_LEVEL_CODE)
            .subscriberFirstName(UPDATED_SUBSCRIBER_FIRST_NAME)
            .subscriberLastName(UPDATED_SUBSCRIBER_LAST_NAME)
            .subscriberGender(UPDATED_SUBSCRIBER_GENDER)
            .subscriberDob(UPDATED_SUBSCRIBER_DOB)
            .primaryInsurerPolicyNo(UPDATED_PRIMARY_INSURER_POLICY_NO)
            .subscriberAddressLine1(UPDATED_SUBSCRIBER_ADDRESS_LINE_1)
            .subscriberCity(UPDATED_SUBSCRIBER_CITY)
            .subscriberState(UPDATED_SUBSCRIBER_STATE)
            .subscriberZipCode(UPDATED_SUBSCRIBER_ZIP_CODE)
            .billingProviderNpi(UPDATED_BILLING_PROVIDER_NPI)
            .billingProviderEin(UPDATED_BILLING_PROVIDER_EIN)
            .billingProviderOrganizationName(UPDATED_BILLING_PROVIDER_ORGANIZATION_NAME)
            .billingProviderAddressLine1(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_1)
            .billingProviderCity(UPDATED_BILLING_PROVIDER_CITY)
            .billingProviderState(UPDATED_BILLING_PROVIDER_STATE)
            .billingProviderZipCode(UPDATED_BILLING_PROVIDER_ZIP_CODE)
            .billingProviderContactPersonName(UPDATED_BILLING_PROVIDER_CONTACT_PERSON_NAME)
            .billingProviderContactNo(UPDATED_BILLING_PROVIDER_CONTACT_NO)
            .claimFilingCode(UPDATED_CLAIM_FILING_CODE)
            .claimChargeAmount(UPDATED_CLAIM_CHARGE_AMOUNT)
            .posCode(UPDATED_POS_CODE)
            .claimFrequencyCode(UPDATED_CLAIM_FREQUENCY_CODE)
            .signatureIndicator(UPDATED_SIGNATURE_INDICATOR)
            .planParticipationCode(UPDATED_PLAN_PARTICIPATION_CODE)
            .benefitsAssignmentCertificationIndicator(UPDATED_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR)
            .releaseInformationCode(UPDATED_RELEASE_INFORMATION_CODE)
            .primaryDiagnosis(UPDATED_PRIMARY_DIAGNOSIS)
            .icd10diagnosisCode1(UPDATED_ICD_10_DIAGNOSIS_CODE_1)
            .icd10diagnosisCode2(UPDATED_ICD_10_DIAGNOSIS_CODE_2)
            .icd10diagnosisCode3(UPDATED_ICD_10_DIAGNOSIS_CODE_3)
            .icd10diagnosisCode4(UPDATED_ICD_10_DIAGNOSIS_CODE_4)
            .icd10diagnosisCode5(UPDATED_ICD_10_DIAGNOSIS_CODE_5)
            .icd10diagnosisCode6(UPDATED_ICD_10_DIAGNOSIS_CODE_6)
            .icd10diagnosisCode7(UPDATED_ICD_10_DIAGNOSIS_CODE_7)
            .icd10diagnosisCode8(UPDATED_ICD_10_DIAGNOSIS_CODE_8)
            .icd10diagnosisCode9(UPDATED_ICD_10_DIAGNOSIS_CODE_9)
            .icd10diagnosisCode10(UPDATED_ICD_10_DIAGNOSIS_CODE_10)
            .icd10diagnosisCode11(UPDATED_ICD_10_DIAGNOSIS_CODE_11)
            .icd10diagnosisCode12(UPDATED_ICD_10_DIAGNOSIS_CODE_12)
            .insertedById(UPDATED_INSERTED_BY_ID)
            .insertedDate(UPDATED_INSERTED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .billingProviderType(UPDATED_BILLING_PROVIDER_TYPE)
            .insertedByName(UPDATED_INSERTED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .status(UPDATED_STATUS)
            .billingProviderAddressLine2(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_2)
            .payerClaimControlNumber(UPDATED_PAYER_CLAIM_CONTROL_NUMBER)
            .insuredFirstName(UPDATED_INSURED_FIRST_NAME)
            .insuredLastName(UPDATED_INSURED_LAST_NAME)
            .insuredAddressLine1(UPDATED_INSURED_ADDRESS_LINE_1)
            .insuredAddressLine2(UPDATED_INSURED_ADDRESS_LINE_2)
            .insuredCity(UPDATED_INSURED_CITY)
            .insuredState(UPDATED_INSURED_STATE)
            .insuredZip(UPDATED_INSURED_ZIP)
            .insuredContactNo(UPDATED_INSURED_CONTACT_NO)
            .insuredDob(UPDATED_INSURED_DOB)
            .insuredGender(UPDATED_INSURED_GENDER)
            .orderingProviderFirstName(UPDATED_ORDERING_PROVIDER_FIRST_NAME)
            .orderingProviderLastName(UPDATED_ORDERING_PROVIDER_LAST_NAME)
            .orderingProviderNpi(UPDATED_ORDERING_PROVIDER_NPI)
            .patientRelationshipInsured(UPDATED_PATIENT_RELATIONSHIP_INSURED)
            .patientConditionEmployment(UPDATED_PATIENT_CONDITION_EMPLOYMENT)
            .patientConditionAutoAccident(UPDATED_PATIENT_CONDITION_AUTO_ACCIDENT)
            .patientConditionOtherAccident(UPDATED_PATIENT_CONDITION_OTHER_ACCIDENT)
            .isNextLevelInsurerPresentStatus(UPDATED_IS_NEXT_LEVEL_INSURER_PRESENT_STATUS)
            .originalDos(UPDATED_ORIGINAL_DOS)
            .parNo(UPDATED_PAR_NO)
            .billingProviderTaxonomy(UPDATED_BILLING_PROVIDER_TAXONOMY)
            .serviceProviderNpi(UPDATED_SERVICE_PROVIDER_NPI)
            .serviceProviderOrganisationName(UPDATED_SERVICE_PROVIDER_ORGANISATION_NAME)
            .serviceProviderAddressLine1(UPDATED_SERVICE_PROVIDER_ADDRESS_LINE_1)
            .serviceProviderAddressLine2(UPDATED_SERVICE_PROVIDER_ADDRESS_LINE_2)
            .serviceProviderCity(UPDATED_SERVICE_PROVIDER_CITY)
            .serviceProviderState(UPDATED_SERVICE_PROVIDER_STATE)
            .serviceProviderCountry(UPDATED_SERVICE_PROVIDER_COUNTRY)
            .serviceProviderZipCode(UPDATED_SERVICE_PROVIDER_ZIP_CODE)
            .serviceProviderTaxonomy(UPDATED_SERVICE_PROVIDER_TAXONOMY)
            .cms1500FormName(UPDATED_CMS_1500_FORM_NAME)
            .tradingPartnerAddressLine1(UPDATED_TRADING_PARTNER_ADDRESS_LINE_1)
            .tradingPartnerAddressLine2(UPDATED_TRADING_PARTNER_ADDRESS_LINE_2)
            .tradingPartnerCity(UPDATED_TRADING_PARTNER_CITY)
            .tradingPartnerState(UPDATED_TRADING_PARTNER_STATE)
            .tradingPartnerZip(UPDATED_TRADING_PARTNER_ZIP)
            .diagnosisCodeType(UPDATED_DIAGNOSIS_CODE_TYPE);

        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPrimaryClaimsReSubmissionMaster.getChangeHealthPrimaryResubmisionMasterId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPrimaryClaimsReSubmissionMaster))
            )
            .andExpect(status().isOk());

        // Validate the PrimaryClaimsReSubmissionMaster in the database
        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeUpdate);
        PrimaryClaimsReSubmissionMaster testPrimaryClaimsReSubmissionMaster = primaryClaimsReSubmissionMasterList.get(
            primaryClaimsReSubmissionMasterList.size() - 1
        );
        assertThat(testPrimaryClaimsReSubmissionMaster.getSalesOrderId()).isEqualTo(UPDATED_SALES_ORDER_ID);
        assertThat(testPrimaryClaimsReSubmissionMaster.getClaimControlNo()).isEqualTo(UPDATED_CLAIM_CONTROL_NO);
        assertThat(testPrimaryClaimsReSubmissionMaster.getTradingPartnerServiceId()).isEqualTo(UPDATED_TRADING_PARTNER_SERVICE_ID);
        assertThat(testPrimaryClaimsReSubmissionMaster.getTradingPartnerName()).isEqualTo(UPDATED_TRADING_PARTNER_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubmitterOrganizationName()).isEqualTo(UPDATED_SUBMITTER_ORGANIZATION_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubmitterContactPersonName()).isEqualTo(UPDATED_SUBMITTER_CONTACT_PERSON_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubmitterContactNo()).isEqualTo(UPDATED_SUBMITTER_CONTACT_NO);
        assertThat(testPrimaryClaimsReSubmissionMaster.getReceiverOrganizationName()).isEqualTo(UPDATED_RECEIVER_ORGANIZATION_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubscriberMemberIdNo()).isEqualTo(UPDATED_SUBSCRIBER_MEMBER_ID_NO);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubscriberPaymentResponsibilityLevelCode())
            .isEqualTo(UPDATED_SUBSCRIBER_PAYMENT_RESPONSIBILITY_LEVEL_CODE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubscriberFirstName()).isEqualTo(UPDATED_SUBSCRIBER_FIRST_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubscriberLastName()).isEqualTo(UPDATED_SUBSCRIBER_LAST_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubscriberGender()).isEqualTo(UPDATED_SUBSCRIBER_GENDER);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubscriberDob()).isEqualTo(UPDATED_SUBSCRIBER_DOB);
        assertThat(testPrimaryClaimsReSubmissionMaster.getPrimaryInsurerPolicyNo()).isEqualTo(UPDATED_PRIMARY_INSURER_POLICY_NO);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubscriberAddressLine1()).isEqualTo(UPDATED_SUBSCRIBER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubscriberCity()).isEqualTo(UPDATED_SUBSCRIBER_CITY);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubscriberState()).isEqualTo(UPDATED_SUBSCRIBER_STATE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSubscriberZipCode()).isEqualTo(UPDATED_SUBSCRIBER_ZIP_CODE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderNpi()).isEqualTo(UPDATED_BILLING_PROVIDER_NPI);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderEin()).isEqualTo(UPDATED_BILLING_PROVIDER_EIN);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderOrganizationName())
            .isEqualTo(UPDATED_BILLING_PROVIDER_ORGANIZATION_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderAddressLine1()).isEqualTo(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderCity()).isEqualTo(UPDATED_BILLING_PROVIDER_CITY);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderState()).isEqualTo(UPDATED_BILLING_PROVIDER_STATE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderZipCode()).isEqualTo(UPDATED_BILLING_PROVIDER_ZIP_CODE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderContactPersonName())
            .isEqualTo(UPDATED_BILLING_PROVIDER_CONTACT_PERSON_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderContactNo()).isEqualTo(UPDATED_BILLING_PROVIDER_CONTACT_NO);
        assertThat(testPrimaryClaimsReSubmissionMaster.getClaimFilingCode()).isEqualTo(UPDATED_CLAIM_FILING_CODE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getClaimChargeAmount()).isEqualTo(UPDATED_CLAIM_CHARGE_AMOUNT);
        assertThat(testPrimaryClaimsReSubmissionMaster.getPosCode()).isEqualTo(UPDATED_POS_CODE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getClaimFrequencyCode()).isEqualTo(UPDATED_CLAIM_FREQUENCY_CODE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getSignatureIndicator()).isEqualTo(UPDATED_SIGNATURE_INDICATOR);
        assertThat(testPrimaryClaimsReSubmissionMaster.getPlanParticipationCode()).isEqualTo(UPDATED_PLAN_PARTICIPATION_CODE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBenefitsAssignmentCertificationIndicator())
            .isEqualTo(UPDATED_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR);
        assertThat(testPrimaryClaimsReSubmissionMaster.getReleaseInformationCode()).isEqualTo(UPDATED_RELEASE_INFORMATION_CODE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getPrimaryDiagnosis()).isEqualTo(UPDATED_PRIMARY_DIAGNOSIS);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode1()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_1);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode2()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_2);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode3()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_3);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode4()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_4);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode5()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_5);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode6()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_6);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode7()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_7);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode8()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_8);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode9()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_9);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode10()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_10);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode11()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_11);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIcd10diagnosisCode12()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_12);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsertedById()).isEqualTo(UPDATED_INSERTED_BY_ID);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsertedDate()).isEqualTo(UPDATED_INSERTED_DATE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPrimaryClaimsReSubmissionMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderType()).isEqualTo(UPDATED_BILLING_PROVIDER_TYPE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsertedByName()).isEqualTo(UPDATED_INSERTED_BY_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderAddressLine2()).isEqualTo(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimsReSubmissionMaster.getPayerClaimControlNumber()).isEqualTo(UPDATED_PAYER_CLAIM_CONTROL_NUMBER);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsuredFirstName()).isEqualTo(UPDATED_INSURED_FIRST_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsuredLastName()).isEqualTo(UPDATED_INSURED_LAST_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsuredAddressLine1()).isEqualTo(UPDATED_INSURED_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsuredAddressLine2()).isEqualTo(UPDATED_INSURED_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsuredCity()).isEqualTo(UPDATED_INSURED_CITY);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsuredState()).isEqualTo(UPDATED_INSURED_STATE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsuredZip()).isEqualTo(UPDATED_INSURED_ZIP);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsuredContactNo()).isEqualTo(UPDATED_INSURED_CONTACT_NO);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsuredDob()).isEqualTo(UPDATED_INSURED_DOB);
        assertThat(testPrimaryClaimsReSubmissionMaster.getInsuredGender()).isEqualTo(UPDATED_INSURED_GENDER);
        assertThat(testPrimaryClaimsReSubmissionMaster.getOrderingProviderFirstName()).isEqualTo(UPDATED_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getOrderingProviderLastName()).isEqualTo(UPDATED_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getOrderingProviderNpi()).isEqualTo(UPDATED_ORDERING_PROVIDER_NPI);
        assertThat(testPrimaryClaimsReSubmissionMaster.getPatientRelationshipInsured()).isEqualTo(UPDATED_PATIENT_RELATIONSHIP_INSURED);
        assertThat(testPrimaryClaimsReSubmissionMaster.getPatientConditionEmployment()).isEqualTo(UPDATED_PATIENT_CONDITION_EMPLOYMENT);
        assertThat(testPrimaryClaimsReSubmissionMaster.getPatientConditionAutoAccident())
            .isEqualTo(UPDATED_PATIENT_CONDITION_AUTO_ACCIDENT);
        assertThat(testPrimaryClaimsReSubmissionMaster.getPatientConditionOtherAccident())
            .isEqualTo(UPDATED_PATIENT_CONDITION_OTHER_ACCIDENT);
        assertThat(testPrimaryClaimsReSubmissionMaster.getIsNextLevelInsurerPresentStatus())
            .isEqualTo(UPDATED_IS_NEXT_LEVEL_INSURER_PRESENT_STATUS);
        assertThat(testPrimaryClaimsReSubmissionMaster.getOriginalDos()).isEqualTo(UPDATED_ORIGINAL_DOS);
        assertThat(testPrimaryClaimsReSubmissionMaster.getParNo()).isEqualTo(UPDATED_PAR_NO);
        assertThat(testPrimaryClaimsReSubmissionMaster.getBillingProviderTaxonomy()).isEqualTo(UPDATED_BILLING_PROVIDER_TAXONOMY);
        assertThat(testPrimaryClaimsReSubmissionMaster.getServiceProviderNpi()).isEqualTo(UPDATED_SERVICE_PROVIDER_NPI);
        assertThat(testPrimaryClaimsReSubmissionMaster.getServiceProviderOrganisationName())
            .isEqualTo(UPDATED_SERVICE_PROVIDER_ORGANISATION_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getServiceProviderAddressLine1()).isEqualTo(UPDATED_SERVICE_PROVIDER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimsReSubmissionMaster.getServiceProviderAddressLine2()).isEqualTo(UPDATED_SERVICE_PROVIDER_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimsReSubmissionMaster.getServiceProviderCity()).isEqualTo(UPDATED_SERVICE_PROVIDER_CITY);
        assertThat(testPrimaryClaimsReSubmissionMaster.getServiceProviderState()).isEqualTo(UPDATED_SERVICE_PROVIDER_STATE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getServiceProviderCountry()).isEqualTo(UPDATED_SERVICE_PROVIDER_COUNTRY);
        assertThat(testPrimaryClaimsReSubmissionMaster.getServiceProviderZipCode()).isEqualTo(UPDATED_SERVICE_PROVIDER_ZIP_CODE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getServiceProviderTaxonomy()).isEqualTo(UPDATED_SERVICE_PROVIDER_TAXONOMY);
        assertThat(testPrimaryClaimsReSubmissionMaster.getCms1500FormName()).isEqualTo(UPDATED_CMS_1500_FORM_NAME);
        assertThat(testPrimaryClaimsReSubmissionMaster.getTradingPartnerAddressLine1()).isEqualTo(UPDATED_TRADING_PARTNER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimsReSubmissionMaster.getTradingPartnerAddressLine2()).isEqualTo(UPDATED_TRADING_PARTNER_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimsReSubmissionMaster.getTradingPartnerCity()).isEqualTo(UPDATED_TRADING_PARTNER_CITY);
        assertThat(testPrimaryClaimsReSubmissionMaster.getTradingPartnerState()).isEqualTo(UPDATED_TRADING_PARTNER_STATE);
        assertThat(testPrimaryClaimsReSubmissionMaster.getTradingPartnerZip()).isEqualTo(UPDATED_TRADING_PARTNER_ZIP);
        assertThat(testPrimaryClaimsReSubmissionMaster.getDiagnosisCodeType()).isEqualTo(UPDATED_DIAGNOSIS_CODE_TYPE);
    }

    @Test
    @Transactional
    void patchNonExistingPrimaryClaimsReSubmissionMaster() throws Exception {
        int databaseSizeBeforeUpdate = primaryClaimsReSubmissionMasterRepository.findAll().size();
        primaryClaimsReSubmissionMaster.setChangeHealthPrimaryResubmisionMasterId(count.incrementAndGet());

        // Create the PrimaryClaimsReSubmissionMaster
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, primaryClaimsReSubmissionMasterDTO.getChangeHealthPrimaryResubmisionMasterId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PrimaryClaimsReSubmissionMaster in the database
        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPrimaryClaimsReSubmissionMaster() throws Exception {
        int databaseSizeBeforeUpdate = primaryClaimsReSubmissionMasterRepository.findAll().size();
        primaryClaimsReSubmissionMaster.setChangeHealthPrimaryResubmisionMasterId(count.incrementAndGet());

        // Create the PrimaryClaimsReSubmissionMaster
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PrimaryClaimsReSubmissionMaster in the database
        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPrimaryClaimsReSubmissionMaster() throws Exception {
        int databaseSizeBeforeUpdate = primaryClaimsReSubmissionMasterRepository.findAll().size();
        primaryClaimsReSubmissionMaster.setChangeHealthPrimaryResubmisionMasterId(count.incrementAndGet());

        // Create the PrimaryClaimsReSubmissionMaster
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = primaryClaimsReSubmissionMasterMapper.toDto(
            primaryClaimsReSubmissionMaster
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(primaryClaimsReSubmissionMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PrimaryClaimsReSubmissionMaster in the database
        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePrimaryClaimsReSubmissionMaster() throws Exception {
        // Initialize the database
        primaryClaimsReSubmissionMasterRepository.saveAndFlush(primaryClaimsReSubmissionMaster);

        int databaseSizeBeforeDelete = primaryClaimsReSubmissionMasterRepository.findAll().size();

        // Delete the primaryClaimsReSubmissionMaster
        restPrimaryClaimsReSubmissionMasterMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, primaryClaimsReSubmissionMaster.getChangeHealthPrimaryResubmisionMasterId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PrimaryClaimsReSubmissionMaster> primaryClaimsReSubmissionMasterList = primaryClaimsReSubmissionMasterRepository.findAll();
        assertThat(primaryClaimsReSubmissionMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
