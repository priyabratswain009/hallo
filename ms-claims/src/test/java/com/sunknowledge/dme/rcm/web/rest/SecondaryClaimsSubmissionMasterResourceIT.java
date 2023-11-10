package com.sunknowledge.dme.rcm.web.rest;

import static com.sunknowledge.dme.rcm.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.SecondaryClaimsSubmissionMaster;
import com.sunknowledge.dme.rcm.repository.SecondaryClaimsSubmissionMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.SecondaryClaimsSubmissionMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.SecondaryClaimsSubmissionMasterMapper;
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
 * Integration tests for the {@link SecondaryClaimsSubmissionMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SecondaryClaimsSubmissionMasterResourceIT {

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

    private static final String DEFAULT_PROVIDER_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_PROVIDER_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_PROVIDER_NPI = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_PROVIDER_NPI = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_PROVIDER_EIN = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_PROVIDER_EIN = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_PROVIDER_ORGANIZATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_PROVIDER_ORGANIZATION_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_PROVIDER_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_PROVIDER_ADDRESS_LINE_2 = "BBBBBBBBBB";

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

    private static final String DEFAULT_PATIENT_ACCOUNT_NO = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_ACCOUNT_NO = "BBBBBBBBBB";

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

    private static final String DEFAULT_BILLING_PROVIDER_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_PROVIDER_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_INSERTED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INSERTED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_OTHER_SUBSCRIBER_ADDRESS_1 = "AAAAAAAAAA";
    private static final String UPDATED_OTHER_SUBSCRIBER_ADDRESS_1 = "BBBBBBBBBB";

    private static final String DEFAULT_OTHER_SUBSCRIBER_ADDRESS_2 = "AAAAAAAAAA";
    private static final String UPDATED_OTHER_SUBSCRIBER_ADDRESS_2 = "BBBBBBBBBB";

    private static final String DEFAULT_OTHER_SUBSCRIBER_CITY = "AAAAAAAAAA";
    private static final String UPDATED_OTHER_SUBSCRIBER_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_OTHER_SUBSCRIBER_STATE = "AAAAAAAAAA";
    private static final String UPDATED_OTHER_SUBSCRIBER_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_OTHER_SUBSCRIBER_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_OTHER_SUBSCRIBER_ZIP = "BBBBBBBBBB";

    private static final String DEFAULT_OTHER_INSURED_QUALIFIER = "AAAAAAAAAA";
    private static final String UPDATED_OTHER_INSURED_QUALIFIER = "BBBBBBBBBB";

    private static final String DEFAULT_OTHER_INSURED_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_OTHER_INSURED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_OTHER_INSURED_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_OTHER_INSURED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_OTHER_INSURED_IDENTIFIER_TYPE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_OTHER_INSURED_IDENTIFIER_TYPE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_OTHER_INSURED_IDENTIFIER = "AAAAAAAAAA";
    private static final String UPDATED_OTHER_INSURED_IDENTIFIER = "BBBBBBBBBB";

    private static final String DEFAULT_OTHER_PAYER_ORGANIZATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_OTHER_PAYER_ORGANIZATION_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_OTHER_PAYER_IDENTIFIER_TYPE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_OTHER_PAYER_IDENTIFIER_TYPE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_OTHER_PAYER_IDENTIFIER = "AAAAAAAAAA";
    private static final String UPDATED_OTHER_PAYER_IDENTIFIER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_OTHER_PAYER_ADJUDICATION_OR_PAYMENT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_OTHER_PAYER_ADJUDICATION_OR_PAYMENT_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_OTHER_PAYER_CLAIM_ADJUSTMENT_INDICATOR = "AAAAAAAAAA";
    private static final String UPDATED_OTHER_PAYER_CLAIM_ADJUSTMENT_INDICATOR = "BBBBBBBBBB";

    private static final String DEFAULT_OTHER_PAYER_CLAIM_CONTROL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_OTHER_PAYER_CLAIM_CONTROL_NUMBER = "BBBBBBBBBB";

    private static final Double DEFAULT_PAYER_PAID_AMOUNT = 1D;
    private static final Double UPDATED_PAYER_PAID_AMOUNT = 2D;

    private static final String DEFAULT_PAYMENT_RESPONSIBILITY_LEVEL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_RESPONSIBILITY_LEVEL_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_INDIVIDUAL_RELATIONSHIP_CODE = "AAAAAAAAAA";
    private static final String UPDATED_INDIVIDUAL_RELATIONSHIP_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CLAIM_FILING_INDICATOR_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CLAIM_FILING_INDICATOR_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_OTHER_PAYER_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR = "AAAAAAAAAA";
    private static final String UPDATED_OTHER_PAYER_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR = "BBBBBBBBBB";

    private static final String DEFAULT_RELEASE_OF_INFORMATION_CODE = "AAAAAAAAAA";
    private static final String UPDATED_RELEASE_OF_INFORMATION_CODE = "BBBBBBBBBB";

    private static final Double DEFAULT_REMAINING_PATIENT_LIABILITY = 1D;
    private static final Double UPDATED_REMAINING_PATIENT_LIABILITY = 2D;

    private static final String DEFAULT_PATIENT_SIGNATURE_GENERATED_FOR_PATIENT = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_SIGNATURE_GENERATED_FOR_PATIENT = "BBBBBBBBBB";

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_UPDATED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Long DEFAULT_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID = 1L;
    private static final Long UPDATED_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/secondary-claims-submission-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{changeHealthSecondarySubmisionMasterId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SecondaryClaimsSubmissionMasterRepository secondaryClaimsSubmissionMasterRepository;

    @Autowired
    private SecondaryClaimsSubmissionMasterMapper secondaryClaimsSubmissionMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSecondaryClaimsSubmissionMasterMockMvc;

    private SecondaryClaimsSubmissionMaster secondaryClaimsSubmissionMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SecondaryClaimsSubmissionMaster createEntity(EntityManager em) {
        SecondaryClaimsSubmissionMaster secondaryClaimsSubmissionMaster = new SecondaryClaimsSubmissionMaster()
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
            .providerType(DEFAULT_PROVIDER_TYPE)
            .billingProviderNpi(DEFAULT_BILLING_PROVIDER_NPI)
            .billingProviderEin(DEFAULT_BILLING_PROVIDER_EIN)
            .billingProviderOrganizationName(DEFAULT_BILLING_PROVIDER_ORGANIZATION_NAME)
            .billingProviderAddressLine1(DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_1)
            .billingProviderAddressLine2(DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_2)
            .billingProviderCity(DEFAULT_BILLING_PROVIDER_CITY)
            .billingProviderState(DEFAULT_BILLING_PROVIDER_STATE)
            .billingProviderZipCode(DEFAULT_BILLING_PROVIDER_ZIP_CODE)
            .billingProviderContactPersonName(DEFAULT_BILLING_PROVIDER_CONTACT_PERSON_NAME)
            .billingProviderContactNo(DEFAULT_BILLING_PROVIDER_CONTACT_NO)
            .claimFilingCode(DEFAULT_CLAIM_FILING_CODE)
            .patientAccountNo(DEFAULT_PATIENT_ACCOUNT_NO)
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
            .billingProviderType(DEFAULT_BILLING_PROVIDER_TYPE)
            .insertedByName(DEFAULT_INSERTED_BY_NAME)
            .status(DEFAULT_STATUS)
            .otherSubscriberAddress1(DEFAULT_OTHER_SUBSCRIBER_ADDRESS_1)
            .otherSubscriberAddress2(DEFAULT_OTHER_SUBSCRIBER_ADDRESS_2)
            .otherSubscriberCity(DEFAULT_OTHER_SUBSCRIBER_CITY)
            .otherSubscriberState(DEFAULT_OTHER_SUBSCRIBER_STATE)
            .otherSubscriberZip(DEFAULT_OTHER_SUBSCRIBER_ZIP)
            .otherInsuredQualifier(DEFAULT_OTHER_INSURED_QUALIFIER)
            .otherInsuredLastName(DEFAULT_OTHER_INSURED_LAST_NAME)
            .otherInsuredFirstName(DEFAULT_OTHER_INSURED_FIRST_NAME)
            .otherInsuredIdentifierTypeCode(DEFAULT_OTHER_INSURED_IDENTIFIER_TYPE_CODE)
            .otherInsuredIdentifier(DEFAULT_OTHER_INSURED_IDENTIFIER)
            .otherPayerOrganizationName(DEFAULT_OTHER_PAYER_ORGANIZATION_NAME)
            .otherPayerIdentifierTypeCode(DEFAULT_OTHER_PAYER_IDENTIFIER_TYPE_CODE)
            .otherPayerIdentifier(DEFAULT_OTHER_PAYER_IDENTIFIER)
            .otherPayerAdjudicationOrPaymentDate(DEFAULT_OTHER_PAYER_ADJUDICATION_OR_PAYMENT_DATE)
            .otherPayerClaimAdjustmentIndicator(DEFAULT_OTHER_PAYER_CLAIM_ADJUSTMENT_INDICATOR)
            .otherPayerClaimControlNumber(DEFAULT_OTHER_PAYER_CLAIM_CONTROL_NUMBER)
            .payerPaidAmount(DEFAULT_PAYER_PAID_AMOUNT)
            .paymentResponsibilityLevelCode(DEFAULT_PAYMENT_RESPONSIBILITY_LEVEL_CODE)
            .individualRelationshipCode(DEFAULT_INDIVIDUAL_RELATIONSHIP_CODE)
            .claimFilingIndicatorCode(DEFAULT_CLAIM_FILING_INDICATOR_CODE)
            .otherPayerBenefitsAssignmentCertificationIndicator(DEFAULT_OTHER_PAYER_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR)
            .releaseOfInformationCode(DEFAULT_RELEASE_OF_INFORMATION_CODE)
            .remainingPatientLiability(DEFAULT_REMAINING_PATIENT_LIABILITY)
            .patientSignatureGeneratedForPatient(DEFAULT_PATIENT_SIGNATURE_GENERATED_FOR_PATIENT)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .changeHealthPrimarySubmisionMasterId(DEFAULT_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID)
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
        return secondaryClaimsSubmissionMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SecondaryClaimsSubmissionMaster createUpdatedEntity(EntityManager em) {
        SecondaryClaimsSubmissionMaster secondaryClaimsSubmissionMaster = new SecondaryClaimsSubmissionMaster()
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
            .providerType(UPDATED_PROVIDER_TYPE)
            .billingProviderNpi(UPDATED_BILLING_PROVIDER_NPI)
            .billingProviderEin(UPDATED_BILLING_PROVIDER_EIN)
            .billingProviderOrganizationName(UPDATED_BILLING_PROVIDER_ORGANIZATION_NAME)
            .billingProviderAddressLine1(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_1)
            .billingProviderAddressLine2(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_2)
            .billingProviderCity(UPDATED_BILLING_PROVIDER_CITY)
            .billingProviderState(UPDATED_BILLING_PROVIDER_STATE)
            .billingProviderZipCode(UPDATED_BILLING_PROVIDER_ZIP_CODE)
            .billingProviderContactPersonName(UPDATED_BILLING_PROVIDER_CONTACT_PERSON_NAME)
            .billingProviderContactNo(UPDATED_BILLING_PROVIDER_CONTACT_NO)
            .claimFilingCode(UPDATED_CLAIM_FILING_CODE)
            .patientAccountNo(UPDATED_PATIENT_ACCOUNT_NO)
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
            .billingProviderType(UPDATED_BILLING_PROVIDER_TYPE)
            .insertedByName(UPDATED_INSERTED_BY_NAME)
            .status(UPDATED_STATUS)
            .otherSubscriberAddress1(UPDATED_OTHER_SUBSCRIBER_ADDRESS_1)
            .otherSubscriberAddress2(UPDATED_OTHER_SUBSCRIBER_ADDRESS_2)
            .otherSubscriberCity(UPDATED_OTHER_SUBSCRIBER_CITY)
            .otherSubscriberState(UPDATED_OTHER_SUBSCRIBER_STATE)
            .otherSubscriberZip(UPDATED_OTHER_SUBSCRIBER_ZIP)
            .otherInsuredQualifier(UPDATED_OTHER_INSURED_QUALIFIER)
            .otherInsuredLastName(UPDATED_OTHER_INSURED_LAST_NAME)
            .otherInsuredFirstName(UPDATED_OTHER_INSURED_FIRST_NAME)
            .otherInsuredIdentifierTypeCode(UPDATED_OTHER_INSURED_IDENTIFIER_TYPE_CODE)
            .otherInsuredIdentifier(UPDATED_OTHER_INSURED_IDENTIFIER)
            .otherPayerOrganizationName(UPDATED_OTHER_PAYER_ORGANIZATION_NAME)
            .otherPayerIdentifierTypeCode(UPDATED_OTHER_PAYER_IDENTIFIER_TYPE_CODE)
            .otherPayerIdentifier(UPDATED_OTHER_PAYER_IDENTIFIER)
            .otherPayerAdjudicationOrPaymentDate(UPDATED_OTHER_PAYER_ADJUDICATION_OR_PAYMENT_DATE)
            .otherPayerClaimAdjustmentIndicator(UPDATED_OTHER_PAYER_CLAIM_ADJUSTMENT_INDICATOR)
            .otherPayerClaimControlNumber(UPDATED_OTHER_PAYER_CLAIM_CONTROL_NUMBER)
            .payerPaidAmount(UPDATED_PAYER_PAID_AMOUNT)
            .paymentResponsibilityLevelCode(UPDATED_PAYMENT_RESPONSIBILITY_LEVEL_CODE)
            .individualRelationshipCode(UPDATED_INDIVIDUAL_RELATIONSHIP_CODE)
            .claimFilingIndicatorCode(UPDATED_CLAIM_FILING_INDICATOR_CODE)
            .otherPayerBenefitsAssignmentCertificationIndicator(UPDATED_OTHER_PAYER_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR)
            .releaseOfInformationCode(UPDATED_RELEASE_OF_INFORMATION_CODE)
            .remainingPatientLiability(UPDATED_REMAINING_PATIENT_LIABILITY)
            .patientSignatureGeneratedForPatient(UPDATED_PATIENT_SIGNATURE_GENERATED_FOR_PATIENT)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .changeHealthPrimarySubmisionMasterId(UPDATED_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID)
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
        return secondaryClaimsSubmissionMaster;
    }

    @BeforeEach
    public void initTest() {
        secondaryClaimsSubmissionMaster = createEntity(em);
    }

    @Test
    @Transactional
    void createSecondaryClaimsSubmissionMaster() throws Exception {
        int databaseSizeBeforeCreate = secondaryClaimsSubmissionMasterRepository.findAll().size();
        // Create the SecondaryClaimsSubmissionMaster
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );
        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the SecondaryClaimsSubmissionMaster in the database
        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeCreate + 1);
        SecondaryClaimsSubmissionMaster testSecondaryClaimsSubmissionMaster = secondaryClaimsSubmissionMasterList.get(
            secondaryClaimsSubmissionMasterList.size() - 1
        );
        assertThat(testSecondaryClaimsSubmissionMaster.getSalesOrderId()).isEqualTo(DEFAULT_SALES_ORDER_ID);
        assertThat(testSecondaryClaimsSubmissionMaster.getClaimControlNo()).isEqualTo(DEFAULT_CLAIM_CONTROL_NO);
        assertThat(testSecondaryClaimsSubmissionMaster.getTradingPartnerServiceId()).isEqualTo(DEFAULT_TRADING_PARTNER_SERVICE_ID);
        assertThat(testSecondaryClaimsSubmissionMaster.getTradingPartnerName()).isEqualTo(DEFAULT_TRADING_PARTNER_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubmitterOrganizationName()).isEqualTo(DEFAULT_SUBMITTER_ORGANIZATION_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubmitterContactPersonName()).isEqualTo(DEFAULT_SUBMITTER_CONTACT_PERSON_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubmitterContactNo()).isEqualTo(DEFAULT_SUBMITTER_CONTACT_NO);
        assertThat(testSecondaryClaimsSubmissionMaster.getReceiverOrganizationName()).isEqualTo(DEFAULT_RECEIVER_ORGANIZATION_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubscriberMemberIdNo()).isEqualTo(DEFAULT_SUBSCRIBER_MEMBER_ID_NO);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubscriberPaymentResponsibilityLevelCode())
            .isEqualTo(DEFAULT_SUBSCRIBER_PAYMENT_RESPONSIBILITY_LEVEL_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubscriberFirstName()).isEqualTo(DEFAULT_SUBSCRIBER_FIRST_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubscriberLastName()).isEqualTo(DEFAULT_SUBSCRIBER_LAST_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubscriberGender()).isEqualTo(DEFAULT_SUBSCRIBER_GENDER);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubscriberDob()).isEqualTo(DEFAULT_SUBSCRIBER_DOB);
        assertThat(testSecondaryClaimsSubmissionMaster.getPrimaryInsurerPolicyNo()).isEqualTo(DEFAULT_PRIMARY_INSURER_POLICY_NO);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubscriberAddressLine1()).isEqualTo(DEFAULT_SUBSCRIBER_ADDRESS_LINE_1);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubscriberCity()).isEqualTo(DEFAULT_SUBSCRIBER_CITY);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubscriberState()).isEqualTo(DEFAULT_SUBSCRIBER_STATE);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubscriberZipCode()).isEqualTo(DEFAULT_SUBSCRIBER_ZIP_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getProviderType()).isEqualTo(DEFAULT_PROVIDER_TYPE);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderNpi()).isEqualTo(DEFAULT_BILLING_PROVIDER_NPI);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderEin()).isEqualTo(DEFAULT_BILLING_PROVIDER_EIN);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderOrganizationName())
            .isEqualTo(DEFAULT_BILLING_PROVIDER_ORGANIZATION_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderAddressLine1()).isEqualTo(DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_1);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderAddressLine2()).isEqualTo(DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_2);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderCity()).isEqualTo(DEFAULT_BILLING_PROVIDER_CITY);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderState()).isEqualTo(DEFAULT_BILLING_PROVIDER_STATE);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderZipCode()).isEqualTo(DEFAULT_BILLING_PROVIDER_ZIP_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderContactPersonName())
            .isEqualTo(DEFAULT_BILLING_PROVIDER_CONTACT_PERSON_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderContactNo()).isEqualTo(DEFAULT_BILLING_PROVIDER_CONTACT_NO);
        assertThat(testSecondaryClaimsSubmissionMaster.getClaimFilingCode()).isEqualTo(DEFAULT_CLAIM_FILING_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getPatientAccountNo()).isEqualTo(DEFAULT_PATIENT_ACCOUNT_NO);
        assertThat(testSecondaryClaimsSubmissionMaster.getClaimChargeAmount()).isEqualTo(DEFAULT_CLAIM_CHARGE_AMOUNT);
        assertThat(testSecondaryClaimsSubmissionMaster.getPosCode()).isEqualTo(DEFAULT_POS_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getClaimFrequencyCode()).isEqualTo(DEFAULT_CLAIM_FREQUENCY_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getSignatureIndicator()).isEqualTo(DEFAULT_SIGNATURE_INDICATOR);
        assertThat(testSecondaryClaimsSubmissionMaster.getPlanParticipationCode()).isEqualTo(DEFAULT_PLAN_PARTICIPATION_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getBenefitsAssignmentCertificationIndicator())
            .isEqualTo(DEFAULT_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR);
        assertThat(testSecondaryClaimsSubmissionMaster.getReleaseInformationCode()).isEqualTo(DEFAULT_RELEASE_INFORMATION_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getPrimaryDiagnosis()).isEqualTo(DEFAULT_PRIMARY_DIAGNOSIS);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode1()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_1);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode2()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_2);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode3()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_3);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode4()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_4);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode5()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_5);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode6()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_6);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode7()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_7);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode8()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_8);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode9()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_9);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode10()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_10);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode11()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_11);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode12()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_12);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsertedById()).isEqualTo(DEFAULT_INSERTED_BY_ID);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsertedDate()).isEqualTo(DEFAULT_INSERTED_DATE);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderType()).isEqualTo(DEFAULT_BILLING_PROVIDER_TYPE);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsertedByName()).isEqualTo(DEFAULT_INSERTED_BY_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherSubscriberAddress1()).isEqualTo(DEFAULT_OTHER_SUBSCRIBER_ADDRESS_1);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherSubscriberAddress2()).isEqualTo(DEFAULT_OTHER_SUBSCRIBER_ADDRESS_2);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherSubscriberCity()).isEqualTo(DEFAULT_OTHER_SUBSCRIBER_CITY);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherSubscriberState()).isEqualTo(DEFAULT_OTHER_SUBSCRIBER_STATE);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherSubscriberZip()).isEqualTo(DEFAULT_OTHER_SUBSCRIBER_ZIP);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherInsuredQualifier()).isEqualTo(DEFAULT_OTHER_INSURED_QUALIFIER);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherInsuredLastName()).isEqualTo(DEFAULT_OTHER_INSURED_LAST_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherInsuredFirstName()).isEqualTo(DEFAULT_OTHER_INSURED_FIRST_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherInsuredIdentifierTypeCode())
            .isEqualTo(DEFAULT_OTHER_INSURED_IDENTIFIER_TYPE_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherInsuredIdentifier()).isEqualTo(DEFAULT_OTHER_INSURED_IDENTIFIER);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherPayerOrganizationName()).isEqualTo(DEFAULT_OTHER_PAYER_ORGANIZATION_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherPayerIdentifierTypeCode())
            .isEqualTo(DEFAULT_OTHER_PAYER_IDENTIFIER_TYPE_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherPayerIdentifier()).isEqualTo(DEFAULT_OTHER_PAYER_IDENTIFIER);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherPayerAdjudicationOrPaymentDate())
            .isEqualTo(DEFAULT_OTHER_PAYER_ADJUDICATION_OR_PAYMENT_DATE);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherPayerClaimAdjustmentIndicator())
            .isEqualTo(DEFAULT_OTHER_PAYER_CLAIM_ADJUSTMENT_INDICATOR);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherPayerClaimControlNumber())
            .isEqualTo(DEFAULT_OTHER_PAYER_CLAIM_CONTROL_NUMBER);
        assertThat(testSecondaryClaimsSubmissionMaster.getPayerPaidAmount()).isEqualTo(DEFAULT_PAYER_PAID_AMOUNT);
        assertThat(testSecondaryClaimsSubmissionMaster.getPaymentResponsibilityLevelCode())
            .isEqualTo(DEFAULT_PAYMENT_RESPONSIBILITY_LEVEL_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getIndividualRelationshipCode()).isEqualTo(DEFAULT_INDIVIDUAL_RELATIONSHIP_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getClaimFilingIndicatorCode()).isEqualTo(DEFAULT_CLAIM_FILING_INDICATOR_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherPayerBenefitsAssignmentCertificationIndicator())
            .isEqualTo(DEFAULT_OTHER_PAYER_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR);
        assertThat(testSecondaryClaimsSubmissionMaster.getReleaseOfInformationCode()).isEqualTo(DEFAULT_RELEASE_OF_INFORMATION_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getRemainingPatientLiability()).isEqualTo(DEFAULT_REMAINING_PATIENT_LIABILITY);
        assertThat(testSecondaryClaimsSubmissionMaster.getPatientSignatureGeneratedForPatient())
            .isEqualTo(DEFAULT_PATIENT_SIGNATURE_GENERATED_FOR_PATIENT);
        assertThat(testSecondaryClaimsSubmissionMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testSecondaryClaimsSubmissionMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testSecondaryClaimsSubmissionMaster.getChangeHealthPrimarySubmisionMasterId())
            .isEqualTo(DEFAULT_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsuredFirstName()).isEqualTo(DEFAULT_INSURED_FIRST_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsuredLastName()).isEqualTo(DEFAULT_INSURED_LAST_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsuredAddressLine1()).isEqualTo(DEFAULT_INSURED_ADDRESS_LINE_1);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsuredAddressLine2()).isEqualTo(DEFAULT_INSURED_ADDRESS_LINE_2);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsuredCity()).isEqualTo(DEFAULT_INSURED_CITY);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsuredState()).isEqualTo(DEFAULT_INSURED_STATE);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsuredZip()).isEqualTo(DEFAULT_INSURED_ZIP);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsuredContactNo()).isEqualTo(DEFAULT_INSURED_CONTACT_NO);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsuredDob()).isEqualTo(DEFAULT_INSURED_DOB);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsuredGender()).isEqualTo(DEFAULT_INSURED_GENDER);
        assertThat(testSecondaryClaimsSubmissionMaster.getOrderingProviderFirstName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getOrderingProviderLastName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getOrderingProviderNpi()).isEqualTo(DEFAULT_ORDERING_PROVIDER_NPI);
        assertThat(testSecondaryClaimsSubmissionMaster.getPatientRelationshipInsured()).isEqualTo(DEFAULT_PATIENT_RELATIONSHIP_INSURED);
        assertThat(testSecondaryClaimsSubmissionMaster.getPatientConditionEmployment()).isEqualTo(DEFAULT_PATIENT_CONDITION_EMPLOYMENT);
        assertThat(testSecondaryClaimsSubmissionMaster.getPatientConditionAutoAccident())
            .isEqualTo(DEFAULT_PATIENT_CONDITION_AUTO_ACCIDENT);
        assertThat(testSecondaryClaimsSubmissionMaster.getPatientConditionOtherAccident())
            .isEqualTo(DEFAULT_PATIENT_CONDITION_OTHER_ACCIDENT);
        assertThat(testSecondaryClaimsSubmissionMaster.getIsNextLevelInsurerPresentStatus())
            .isEqualTo(DEFAULT_IS_NEXT_LEVEL_INSURER_PRESENT_STATUS);
        assertThat(testSecondaryClaimsSubmissionMaster.getOriginalDos()).isEqualTo(DEFAULT_ORIGINAL_DOS);
        assertThat(testSecondaryClaimsSubmissionMaster.getParNo()).isEqualTo(DEFAULT_PAR_NO);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderTaxonomy()).isEqualTo(DEFAULT_BILLING_PROVIDER_TAXONOMY);
        assertThat(testSecondaryClaimsSubmissionMaster.getServiceProviderNpi()).isEqualTo(DEFAULT_SERVICE_PROVIDER_NPI);
        assertThat(testSecondaryClaimsSubmissionMaster.getServiceProviderOrganisationName())
            .isEqualTo(DEFAULT_SERVICE_PROVIDER_ORGANISATION_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getServiceProviderAddressLine1()).isEqualTo(DEFAULT_SERVICE_PROVIDER_ADDRESS_LINE_1);
        assertThat(testSecondaryClaimsSubmissionMaster.getServiceProviderAddressLine2()).isEqualTo(DEFAULT_SERVICE_PROVIDER_ADDRESS_LINE_2);
        assertThat(testSecondaryClaimsSubmissionMaster.getServiceProviderCity()).isEqualTo(DEFAULT_SERVICE_PROVIDER_CITY);
        assertThat(testSecondaryClaimsSubmissionMaster.getServiceProviderState()).isEqualTo(DEFAULT_SERVICE_PROVIDER_STATE);
        assertThat(testSecondaryClaimsSubmissionMaster.getServiceProviderCountry()).isEqualTo(DEFAULT_SERVICE_PROVIDER_COUNTRY);
        assertThat(testSecondaryClaimsSubmissionMaster.getServiceProviderZipCode()).isEqualTo(DEFAULT_SERVICE_PROVIDER_ZIP_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getServiceProviderTaxonomy()).isEqualTo(DEFAULT_SERVICE_PROVIDER_TAXONOMY);
        assertThat(testSecondaryClaimsSubmissionMaster.getCms1500FormName()).isEqualTo(DEFAULT_CMS_1500_FORM_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getTradingPartnerAddressLine1()).isEqualTo(DEFAULT_TRADING_PARTNER_ADDRESS_LINE_1);
        assertThat(testSecondaryClaimsSubmissionMaster.getTradingPartnerAddressLine2()).isEqualTo(DEFAULT_TRADING_PARTNER_ADDRESS_LINE_2);
        assertThat(testSecondaryClaimsSubmissionMaster.getTradingPartnerCity()).isEqualTo(DEFAULT_TRADING_PARTNER_CITY);
        assertThat(testSecondaryClaimsSubmissionMaster.getTradingPartnerState()).isEqualTo(DEFAULT_TRADING_PARTNER_STATE);
        assertThat(testSecondaryClaimsSubmissionMaster.getTradingPartnerZip()).isEqualTo(DEFAULT_TRADING_PARTNER_ZIP);
        assertThat(testSecondaryClaimsSubmissionMaster.getDiagnosisCodeType()).isEqualTo(DEFAULT_DIAGNOSIS_CODE_TYPE);
    }

    @Test
    @Transactional
    void createSecondaryClaimsSubmissionMasterWithExistingId() throws Exception {
        // Create the SecondaryClaimsSubmissionMaster with an existing ID
        secondaryClaimsSubmissionMaster.setChangeHealthSecondarySubmisionMasterId(1L);
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        int databaseSizeBeforeCreate = secondaryClaimsSubmissionMasterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SecondaryClaimsSubmissionMaster in the database
        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkSalesOrderIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryClaimsSubmissionMasterRepository.findAll().size();
        // set the field null
        secondaryClaimsSubmissionMaster.setSalesOrderId(null);

        // Create the SecondaryClaimsSubmissionMaster, which fails.
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkClaimControlNoIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryClaimsSubmissionMasterRepository.findAll().size();
        // set the field null
        secondaryClaimsSubmissionMaster.setClaimControlNo(null);

        // Create the SecondaryClaimsSubmissionMaster, which fails.
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTradingPartnerServiceIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryClaimsSubmissionMasterRepository.findAll().size();
        // set the field null
        secondaryClaimsSubmissionMaster.setTradingPartnerServiceId(null);

        // Create the SecondaryClaimsSubmissionMaster, which fails.
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTradingPartnerNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryClaimsSubmissionMasterRepository.findAll().size();
        // set the field null
        secondaryClaimsSubmissionMaster.setTradingPartnerName(null);

        // Create the SecondaryClaimsSubmissionMaster, which fails.
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSubmitterOrganizationNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryClaimsSubmissionMasterRepository.findAll().size();
        // set the field null
        secondaryClaimsSubmissionMaster.setSubmitterOrganizationName(null);

        // Create the SecondaryClaimsSubmissionMaster, which fails.
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSubmitterContactPersonNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryClaimsSubmissionMasterRepository.findAll().size();
        // set the field null
        secondaryClaimsSubmissionMaster.setSubmitterContactPersonName(null);

        // Create the SecondaryClaimsSubmissionMaster, which fails.
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSubmitterContactNoIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryClaimsSubmissionMasterRepository.findAll().size();
        // set the field null
        secondaryClaimsSubmissionMaster.setSubmitterContactNo(null);

        // Create the SecondaryClaimsSubmissionMaster, which fails.
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkReceiverOrganizationNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryClaimsSubmissionMasterRepository.findAll().size();
        // set the field null
        secondaryClaimsSubmissionMaster.setReceiverOrganizationName(null);

        // Create the SecondaryClaimsSubmissionMaster, which fails.
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSubscriberMemberIdNoIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryClaimsSubmissionMasterRepository.findAll().size();
        // set the field null
        secondaryClaimsSubmissionMaster.setSubscriberMemberIdNo(null);

        // Create the SecondaryClaimsSubmissionMaster, which fails.
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSubscriberPaymentResponsibilityLevelCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryClaimsSubmissionMasterRepository.findAll().size();
        // set the field null
        secondaryClaimsSubmissionMaster.setSubscriberPaymentResponsibilityLevelCode(null);

        // Create the SecondaryClaimsSubmissionMaster, which fails.
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSubscriberFirstNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryClaimsSubmissionMasterRepository.findAll().size();
        // set the field null
        secondaryClaimsSubmissionMaster.setSubscriberFirstName(null);

        // Create the SecondaryClaimsSubmissionMaster, which fails.
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSubscriberLastNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryClaimsSubmissionMasterRepository.findAll().size();
        // set the field null
        secondaryClaimsSubmissionMaster.setSubscriberLastName(null);

        // Create the SecondaryClaimsSubmissionMaster, which fails.
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSubscriberGenderIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryClaimsSubmissionMasterRepository.findAll().size();
        // set the field null
        secondaryClaimsSubmissionMaster.setSubscriberGender(null);

        // Create the SecondaryClaimsSubmissionMaster, which fails.
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSubscriberDobIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryClaimsSubmissionMasterRepository.findAll().size();
        // set the field null
        secondaryClaimsSubmissionMaster.setSubscriberDob(null);

        // Create the SecondaryClaimsSubmissionMaster, which fails.
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSubscriberCityIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryClaimsSubmissionMasterRepository.findAll().size();
        // set the field null
        secondaryClaimsSubmissionMaster.setSubscriberCity(null);

        // Create the SecondaryClaimsSubmissionMaster, which fails.
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSubscriberStateIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryClaimsSubmissionMasterRepository.findAll().size();
        // set the field null
        secondaryClaimsSubmissionMaster.setSubscriberState(null);

        // Create the SecondaryClaimsSubmissionMaster, which fails.
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSubscriberZipCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryClaimsSubmissionMasterRepository.findAll().size();
        // set the field null
        secondaryClaimsSubmissionMaster.setSubscriberZipCode(null);

        // Create the SecondaryClaimsSubmissionMaster, which fails.
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkProviderTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryClaimsSubmissionMasterRepository.findAll().size();
        // set the field null
        secondaryClaimsSubmissionMaster.setProviderType(null);

        // Create the SecondaryClaimsSubmissionMaster, which fails.
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBillingProviderNpiIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryClaimsSubmissionMasterRepository.findAll().size();
        // set the field null
        secondaryClaimsSubmissionMaster.setBillingProviderNpi(null);

        // Create the SecondaryClaimsSubmissionMaster, which fails.
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBillingProviderEinIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryClaimsSubmissionMasterRepository.findAll().size();
        // set the field null
        secondaryClaimsSubmissionMaster.setBillingProviderEin(null);

        // Create the SecondaryClaimsSubmissionMaster, which fails.
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBillingProviderOrganizationNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryClaimsSubmissionMasterRepository.findAll().size();
        // set the field null
        secondaryClaimsSubmissionMaster.setBillingProviderOrganizationName(null);

        // Create the SecondaryClaimsSubmissionMaster, which fails.
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBillingProviderAddressLine1IsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryClaimsSubmissionMasterRepository.findAll().size();
        // set the field null
        secondaryClaimsSubmissionMaster.setBillingProviderAddressLine1(null);

        // Create the SecondaryClaimsSubmissionMaster, which fails.
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBillingProviderCityIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryClaimsSubmissionMasterRepository.findAll().size();
        // set the field null
        secondaryClaimsSubmissionMaster.setBillingProviderCity(null);

        // Create the SecondaryClaimsSubmissionMaster, which fails.
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBillingProviderStateIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryClaimsSubmissionMasterRepository.findAll().size();
        // set the field null
        secondaryClaimsSubmissionMaster.setBillingProviderState(null);

        // Create the SecondaryClaimsSubmissionMaster, which fails.
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBillingProviderZipCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryClaimsSubmissionMasterRepository.findAll().size();
        // set the field null
        secondaryClaimsSubmissionMaster.setBillingProviderZipCode(null);

        // Create the SecondaryClaimsSubmissionMaster, which fails.
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBillingProviderContactPersonNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryClaimsSubmissionMasterRepository.findAll().size();
        // set the field null
        secondaryClaimsSubmissionMaster.setBillingProviderContactPersonName(null);

        // Create the SecondaryClaimsSubmissionMaster, which fails.
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBillingProviderContactNoIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryClaimsSubmissionMasterRepository.findAll().size();
        // set the field null
        secondaryClaimsSubmissionMaster.setBillingProviderContactNo(null);

        // Create the SecondaryClaimsSubmissionMaster, which fails.
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkClaimFilingCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryClaimsSubmissionMasterRepository.findAll().size();
        // set the field null
        secondaryClaimsSubmissionMaster.setClaimFilingCode(null);

        // Create the SecondaryClaimsSubmissionMaster, which fails.
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPatientAccountNoIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryClaimsSubmissionMasterRepository.findAll().size();
        // set the field null
        secondaryClaimsSubmissionMaster.setPatientAccountNo(null);

        // Create the SecondaryClaimsSubmissionMaster, which fails.
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkClaimChargeAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryClaimsSubmissionMasterRepository.findAll().size();
        // set the field null
        secondaryClaimsSubmissionMaster.setClaimChargeAmount(null);

        // Create the SecondaryClaimsSubmissionMaster, which fails.
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPosCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryClaimsSubmissionMasterRepository.findAll().size();
        // set the field null
        secondaryClaimsSubmissionMaster.setPosCode(null);

        // Create the SecondaryClaimsSubmissionMaster, which fails.
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkClaimFrequencyCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryClaimsSubmissionMasterRepository.findAll().size();
        // set the field null
        secondaryClaimsSubmissionMaster.setClaimFrequencyCode(null);

        // Create the SecondaryClaimsSubmissionMaster, which fails.
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSignatureIndicatorIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryClaimsSubmissionMasterRepository.findAll().size();
        // set the field null
        secondaryClaimsSubmissionMaster.setSignatureIndicator(null);

        // Create the SecondaryClaimsSubmissionMaster, which fails.
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPlanParticipationCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryClaimsSubmissionMasterRepository.findAll().size();
        // set the field null
        secondaryClaimsSubmissionMaster.setPlanParticipationCode(null);

        // Create the SecondaryClaimsSubmissionMaster, which fails.
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBenefitsAssignmentCertificationIndicatorIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryClaimsSubmissionMasterRepository.findAll().size();
        // set the field null
        secondaryClaimsSubmissionMaster.setBenefitsAssignmentCertificationIndicator(null);

        // Create the SecondaryClaimsSubmissionMaster, which fails.
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkReleaseInformationCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryClaimsSubmissionMasterRepository.findAll().size();
        // set the field null
        secondaryClaimsSubmissionMaster.setReleaseInformationCode(null);

        // Create the SecondaryClaimsSubmissionMaster, which fails.
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBillingProviderTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryClaimsSubmissionMasterRepository.findAll().size();
        // set the field null
        secondaryClaimsSubmissionMaster.setBillingProviderType(null);

        // Create the SecondaryClaimsSubmissionMaster, which fails.
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllSecondaryClaimsSubmissionMasters() throws Exception {
        // Initialize the database
        secondaryClaimsSubmissionMasterRepository.saveAndFlush(secondaryClaimsSubmissionMaster);

        // Get all the secondaryClaimsSubmissionMasterList
        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=changeHealthSecondarySubmisionMasterId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].changeHealthSecondarySubmisionMasterId")
                    .value(hasItem(secondaryClaimsSubmissionMaster.getChangeHealthSecondarySubmisionMasterId().intValue()))
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
            .andExpect(jsonPath("$.[*].providerType").value(hasItem(DEFAULT_PROVIDER_TYPE)))
            .andExpect(jsonPath("$.[*].billingProviderNpi").value(hasItem(DEFAULT_BILLING_PROVIDER_NPI)))
            .andExpect(jsonPath("$.[*].billingProviderEin").value(hasItem(DEFAULT_BILLING_PROVIDER_EIN)))
            .andExpect(jsonPath("$.[*].billingProviderOrganizationName").value(hasItem(DEFAULT_BILLING_PROVIDER_ORGANIZATION_NAME)))
            .andExpect(jsonPath("$.[*].billingProviderAddressLine1").value(hasItem(DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_1)))
            .andExpect(jsonPath("$.[*].billingProviderAddressLine2").value(hasItem(DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_2)))
            .andExpect(jsonPath("$.[*].billingProviderCity").value(hasItem(DEFAULT_BILLING_PROVIDER_CITY)))
            .andExpect(jsonPath("$.[*].billingProviderState").value(hasItem(DEFAULT_BILLING_PROVIDER_STATE)))
            .andExpect(jsonPath("$.[*].billingProviderZipCode").value(hasItem(DEFAULT_BILLING_PROVIDER_ZIP_CODE)))
            .andExpect(jsonPath("$.[*].billingProviderContactPersonName").value(hasItem(DEFAULT_BILLING_PROVIDER_CONTACT_PERSON_NAME)))
            .andExpect(jsonPath("$.[*].billingProviderContactNo").value(hasItem(DEFAULT_BILLING_PROVIDER_CONTACT_NO)))
            .andExpect(jsonPath("$.[*].claimFilingCode").value(hasItem(DEFAULT_CLAIM_FILING_CODE)))
            .andExpect(jsonPath("$.[*].patientAccountNo").value(hasItem(DEFAULT_PATIENT_ACCOUNT_NO)))
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
            .andExpect(jsonPath("$.[*].billingProviderType").value(hasItem(DEFAULT_BILLING_PROVIDER_TYPE)))
            .andExpect(jsonPath("$.[*].insertedByName").value(hasItem(DEFAULT_INSERTED_BY_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].otherSubscriberAddress1").value(hasItem(DEFAULT_OTHER_SUBSCRIBER_ADDRESS_1)))
            .andExpect(jsonPath("$.[*].otherSubscriberAddress2").value(hasItem(DEFAULT_OTHER_SUBSCRIBER_ADDRESS_2)))
            .andExpect(jsonPath("$.[*].otherSubscriberCity").value(hasItem(DEFAULT_OTHER_SUBSCRIBER_CITY)))
            .andExpect(jsonPath("$.[*].otherSubscriberState").value(hasItem(DEFAULT_OTHER_SUBSCRIBER_STATE)))
            .andExpect(jsonPath("$.[*].otherSubscriberZip").value(hasItem(DEFAULT_OTHER_SUBSCRIBER_ZIP)))
            .andExpect(jsonPath("$.[*].otherInsuredQualifier").value(hasItem(DEFAULT_OTHER_INSURED_QUALIFIER)))
            .andExpect(jsonPath("$.[*].otherInsuredLastName").value(hasItem(DEFAULT_OTHER_INSURED_LAST_NAME)))
            .andExpect(jsonPath("$.[*].otherInsuredFirstName").value(hasItem(DEFAULT_OTHER_INSURED_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].otherInsuredIdentifierTypeCode").value(hasItem(DEFAULT_OTHER_INSURED_IDENTIFIER_TYPE_CODE)))
            .andExpect(jsonPath("$.[*].otherInsuredIdentifier").value(hasItem(DEFAULT_OTHER_INSURED_IDENTIFIER)))
            .andExpect(jsonPath("$.[*].otherPayerOrganizationName").value(hasItem(DEFAULT_OTHER_PAYER_ORGANIZATION_NAME)))
            .andExpect(jsonPath("$.[*].otherPayerIdentifierTypeCode").value(hasItem(DEFAULT_OTHER_PAYER_IDENTIFIER_TYPE_CODE)))
            .andExpect(jsonPath("$.[*].otherPayerIdentifier").value(hasItem(DEFAULT_OTHER_PAYER_IDENTIFIER)))
            .andExpect(
                jsonPath("$.[*].otherPayerAdjudicationOrPaymentDate")
                    .value(hasItem(DEFAULT_OTHER_PAYER_ADJUDICATION_OR_PAYMENT_DATE.toString()))
            )
            .andExpect(jsonPath("$.[*].otherPayerClaimAdjustmentIndicator").value(hasItem(DEFAULT_OTHER_PAYER_CLAIM_ADJUSTMENT_INDICATOR)))
            .andExpect(jsonPath("$.[*].otherPayerClaimControlNumber").value(hasItem(DEFAULT_OTHER_PAYER_CLAIM_CONTROL_NUMBER)))
            .andExpect(jsonPath("$.[*].payerPaidAmount").value(hasItem(DEFAULT_PAYER_PAID_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].paymentResponsibilityLevelCode").value(hasItem(DEFAULT_PAYMENT_RESPONSIBILITY_LEVEL_CODE)))
            .andExpect(jsonPath("$.[*].individualRelationshipCode").value(hasItem(DEFAULT_INDIVIDUAL_RELATIONSHIP_CODE)))
            .andExpect(jsonPath("$.[*].claimFilingIndicatorCode").value(hasItem(DEFAULT_CLAIM_FILING_INDICATOR_CODE)))
            .andExpect(
                jsonPath("$.[*].otherPayerBenefitsAssignmentCertificationIndicator")
                    .value(hasItem(DEFAULT_OTHER_PAYER_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR))
            )
            .andExpect(jsonPath("$.[*].releaseOfInformationCode").value(hasItem(DEFAULT_RELEASE_OF_INFORMATION_CODE)))
            .andExpect(jsonPath("$.[*].remainingPatientLiability").value(hasItem(DEFAULT_REMAINING_PATIENT_LIABILITY.doubleValue())))
            .andExpect(
                jsonPath("$.[*].patientSignatureGeneratedForPatient").value(hasItem(DEFAULT_PATIENT_SIGNATURE_GENERATED_FOR_PATIENT))
            )
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(sameInstant(DEFAULT_UPDATED_DATE))))
            .andExpect(
                jsonPath("$.[*].changeHealthPrimarySubmisionMasterId")
                    .value(hasItem(DEFAULT_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID.intValue()))
            )
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
    void getSecondaryClaimsSubmissionMaster() throws Exception {
        // Initialize the database
        secondaryClaimsSubmissionMasterRepository.saveAndFlush(secondaryClaimsSubmissionMaster);

        // Get the secondaryClaimsSubmissionMaster
        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(get(ENTITY_API_URL_ID, secondaryClaimsSubmissionMaster.getChangeHealthSecondarySubmisionMasterId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.changeHealthSecondarySubmisionMasterId")
                    .value(secondaryClaimsSubmissionMaster.getChangeHealthSecondarySubmisionMasterId().intValue())
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
            .andExpect(jsonPath("$.providerType").value(DEFAULT_PROVIDER_TYPE))
            .andExpect(jsonPath("$.billingProviderNpi").value(DEFAULT_BILLING_PROVIDER_NPI))
            .andExpect(jsonPath("$.billingProviderEin").value(DEFAULT_BILLING_PROVIDER_EIN))
            .andExpect(jsonPath("$.billingProviderOrganizationName").value(DEFAULT_BILLING_PROVIDER_ORGANIZATION_NAME))
            .andExpect(jsonPath("$.billingProviderAddressLine1").value(DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_1))
            .andExpect(jsonPath("$.billingProviderAddressLine2").value(DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_2))
            .andExpect(jsonPath("$.billingProviderCity").value(DEFAULT_BILLING_PROVIDER_CITY))
            .andExpect(jsonPath("$.billingProviderState").value(DEFAULT_BILLING_PROVIDER_STATE))
            .andExpect(jsonPath("$.billingProviderZipCode").value(DEFAULT_BILLING_PROVIDER_ZIP_CODE))
            .andExpect(jsonPath("$.billingProviderContactPersonName").value(DEFAULT_BILLING_PROVIDER_CONTACT_PERSON_NAME))
            .andExpect(jsonPath("$.billingProviderContactNo").value(DEFAULT_BILLING_PROVIDER_CONTACT_NO))
            .andExpect(jsonPath("$.claimFilingCode").value(DEFAULT_CLAIM_FILING_CODE))
            .andExpect(jsonPath("$.patientAccountNo").value(DEFAULT_PATIENT_ACCOUNT_NO))
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
            .andExpect(jsonPath("$.billingProviderType").value(DEFAULT_BILLING_PROVIDER_TYPE))
            .andExpect(jsonPath("$.insertedByName").value(DEFAULT_INSERTED_BY_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.otherSubscriberAddress1").value(DEFAULT_OTHER_SUBSCRIBER_ADDRESS_1))
            .andExpect(jsonPath("$.otherSubscriberAddress2").value(DEFAULT_OTHER_SUBSCRIBER_ADDRESS_2))
            .andExpect(jsonPath("$.otherSubscriberCity").value(DEFAULT_OTHER_SUBSCRIBER_CITY))
            .andExpect(jsonPath("$.otherSubscriberState").value(DEFAULT_OTHER_SUBSCRIBER_STATE))
            .andExpect(jsonPath("$.otherSubscriberZip").value(DEFAULT_OTHER_SUBSCRIBER_ZIP))
            .andExpect(jsonPath("$.otherInsuredQualifier").value(DEFAULT_OTHER_INSURED_QUALIFIER))
            .andExpect(jsonPath("$.otherInsuredLastName").value(DEFAULT_OTHER_INSURED_LAST_NAME))
            .andExpect(jsonPath("$.otherInsuredFirstName").value(DEFAULT_OTHER_INSURED_FIRST_NAME))
            .andExpect(jsonPath("$.otherInsuredIdentifierTypeCode").value(DEFAULT_OTHER_INSURED_IDENTIFIER_TYPE_CODE))
            .andExpect(jsonPath("$.otherInsuredIdentifier").value(DEFAULT_OTHER_INSURED_IDENTIFIER))
            .andExpect(jsonPath("$.otherPayerOrganizationName").value(DEFAULT_OTHER_PAYER_ORGANIZATION_NAME))
            .andExpect(jsonPath("$.otherPayerIdentifierTypeCode").value(DEFAULT_OTHER_PAYER_IDENTIFIER_TYPE_CODE))
            .andExpect(jsonPath("$.otherPayerIdentifier").value(DEFAULT_OTHER_PAYER_IDENTIFIER))
            .andExpect(jsonPath("$.otherPayerAdjudicationOrPaymentDate").value(DEFAULT_OTHER_PAYER_ADJUDICATION_OR_PAYMENT_DATE.toString()))
            .andExpect(jsonPath("$.otherPayerClaimAdjustmentIndicator").value(DEFAULT_OTHER_PAYER_CLAIM_ADJUSTMENT_INDICATOR))
            .andExpect(jsonPath("$.otherPayerClaimControlNumber").value(DEFAULT_OTHER_PAYER_CLAIM_CONTROL_NUMBER))
            .andExpect(jsonPath("$.payerPaidAmount").value(DEFAULT_PAYER_PAID_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.paymentResponsibilityLevelCode").value(DEFAULT_PAYMENT_RESPONSIBILITY_LEVEL_CODE))
            .andExpect(jsonPath("$.individualRelationshipCode").value(DEFAULT_INDIVIDUAL_RELATIONSHIP_CODE))
            .andExpect(jsonPath("$.claimFilingIndicatorCode").value(DEFAULT_CLAIM_FILING_INDICATOR_CODE))
            .andExpect(
                jsonPath("$.otherPayerBenefitsAssignmentCertificationIndicator")
                    .value(DEFAULT_OTHER_PAYER_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR)
            )
            .andExpect(jsonPath("$.releaseOfInformationCode").value(DEFAULT_RELEASE_OF_INFORMATION_CODE))
            .andExpect(jsonPath("$.remainingPatientLiability").value(DEFAULT_REMAINING_PATIENT_LIABILITY.doubleValue()))
            .andExpect(jsonPath("$.patientSignatureGeneratedForPatient").value(DEFAULT_PATIENT_SIGNATURE_GENERATED_FOR_PATIENT))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedDate").value(sameInstant(DEFAULT_UPDATED_DATE)))
            .andExpect(
                jsonPath("$.changeHealthPrimarySubmisionMasterId").value(DEFAULT_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID.intValue())
            )
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
    void getNonExistingSecondaryClaimsSubmissionMaster() throws Exception {
        // Get the secondaryClaimsSubmissionMaster
        restSecondaryClaimsSubmissionMasterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewSecondaryClaimsSubmissionMaster() throws Exception {
        // Initialize the database
        secondaryClaimsSubmissionMasterRepository.saveAndFlush(secondaryClaimsSubmissionMaster);

        int databaseSizeBeforeUpdate = secondaryClaimsSubmissionMasterRepository.findAll().size();

        // Update the secondaryClaimsSubmissionMaster
        SecondaryClaimsSubmissionMaster updatedSecondaryClaimsSubmissionMaster = secondaryClaimsSubmissionMasterRepository
            .findById(secondaryClaimsSubmissionMaster.getChangeHealthSecondarySubmisionMasterId())
            .get();
        // Disconnect from session so that the updates on updatedSecondaryClaimsSubmissionMaster are not directly saved in db
        em.detach(updatedSecondaryClaimsSubmissionMaster);
        updatedSecondaryClaimsSubmissionMaster
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
            .providerType(UPDATED_PROVIDER_TYPE)
            .billingProviderNpi(UPDATED_BILLING_PROVIDER_NPI)
            .billingProviderEin(UPDATED_BILLING_PROVIDER_EIN)
            .billingProviderOrganizationName(UPDATED_BILLING_PROVIDER_ORGANIZATION_NAME)
            .billingProviderAddressLine1(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_1)
            .billingProviderAddressLine2(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_2)
            .billingProviderCity(UPDATED_BILLING_PROVIDER_CITY)
            .billingProviderState(UPDATED_BILLING_PROVIDER_STATE)
            .billingProviderZipCode(UPDATED_BILLING_PROVIDER_ZIP_CODE)
            .billingProviderContactPersonName(UPDATED_BILLING_PROVIDER_CONTACT_PERSON_NAME)
            .billingProviderContactNo(UPDATED_BILLING_PROVIDER_CONTACT_NO)
            .claimFilingCode(UPDATED_CLAIM_FILING_CODE)
            .patientAccountNo(UPDATED_PATIENT_ACCOUNT_NO)
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
            .billingProviderType(UPDATED_BILLING_PROVIDER_TYPE)
            .insertedByName(UPDATED_INSERTED_BY_NAME)
            .status(UPDATED_STATUS)
            .otherSubscriberAddress1(UPDATED_OTHER_SUBSCRIBER_ADDRESS_1)
            .otherSubscriberAddress2(UPDATED_OTHER_SUBSCRIBER_ADDRESS_2)
            .otherSubscriberCity(UPDATED_OTHER_SUBSCRIBER_CITY)
            .otherSubscriberState(UPDATED_OTHER_SUBSCRIBER_STATE)
            .otherSubscriberZip(UPDATED_OTHER_SUBSCRIBER_ZIP)
            .otherInsuredQualifier(UPDATED_OTHER_INSURED_QUALIFIER)
            .otherInsuredLastName(UPDATED_OTHER_INSURED_LAST_NAME)
            .otherInsuredFirstName(UPDATED_OTHER_INSURED_FIRST_NAME)
            .otherInsuredIdentifierTypeCode(UPDATED_OTHER_INSURED_IDENTIFIER_TYPE_CODE)
            .otherInsuredIdentifier(UPDATED_OTHER_INSURED_IDENTIFIER)
            .otherPayerOrganizationName(UPDATED_OTHER_PAYER_ORGANIZATION_NAME)
            .otherPayerIdentifierTypeCode(UPDATED_OTHER_PAYER_IDENTIFIER_TYPE_CODE)
            .otherPayerIdentifier(UPDATED_OTHER_PAYER_IDENTIFIER)
            .otherPayerAdjudicationOrPaymentDate(UPDATED_OTHER_PAYER_ADJUDICATION_OR_PAYMENT_DATE)
            .otherPayerClaimAdjustmentIndicator(UPDATED_OTHER_PAYER_CLAIM_ADJUSTMENT_INDICATOR)
            .otherPayerClaimControlNumber(UPDATED_OTHER_PAYER_CLAIM_CONTROL_NUMBER)
            .payerPaidAmount(UPDATED_PAYER_PAID_AMOUNT)
            .paymentResponsibilityLevelCode(UPDATED_PAYMENT_RESPONSIBILITY_LEVEL_CODE)
            .individualRelationshipCode(UPDATED_INDIVIDUAL_RELATIONSHIP_CODE)
            .claimFilingIndicatorCode(UPDATED_CLAIM_FILING_INDICATOR_CODE)
            .otherPayerBenefitsAssignmentCertificationIndicator(UPDATED_OTHER_PAYER_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR)
            .releaseOfInformationCode(UPDATED_RELEASE_OF_INFORMATION_CODE)
            .remainingPatientLiability(UPDATED_REMAINING_PATIENT_LIABILITY)
            .patientSignatureGeneratedForPatient(UPDATED_PATIENT_SIGNATURE_GENERATED_FOR_PATIENT)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .changeHealthPrimarySubmisionMasterId(UPDATED_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID)
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
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            updatedSecondaryClaimsSubmissionMaster
        );

        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, secondaryClaimsSubmissionMasterDTO.getChangeHealthSecondarySubmisionMasterId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isOk());

        // Validate the SecondaryClaimsSubmissionMaster in the database
        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeUpdate);
        SecondaryClaimsSubmissionMaster testSecondaryClaimsSubmissionMaster = secondaryClaimsSubmissionMasterList.get(
            secondaryClaimsSubmissionMasterList.size() - 1
        );
        assertThat(testSecondaryClaimsSubmissionMaster.getSalesOrderId()).isEqualTo(UPDATED_SALES_ORDER_ID);
        assertThat(testSecondaryClaimsSubmissionMaster.getClaimControlNo()).isEqualTo(UPDATED_CLAIM_CONTROL_NO);
        assertThat(testSecondaryClaimsSubmissionMaster.getTradingPartnerServiceId()).isEqualTo(UPDATED_TRADING_PARTNER_SERVICE_ID);
        assertThat(testSecondaryClaimsSubmissionMaster.getTradingPartnerName()).isEqualTo(UPDATED_TRADING_PARTNER_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubmitterOrganizationName()).isEqualTo(UPDATED_SUBMITTER_ORGANIZATION_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubmitterContactPersonName()).isEqualTo(UPDATED_SUBMITTER_CONTACT_PERSON_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubmitterContactNo()).isEqualTo(UPDATED_SUBMITTER_CONTACT_NO);
        assertThat(testSecondaryClaimsSubmissionMaster.getReceiverOrganizationName()).isEqualTo(UPDATED_RECEIVER_ORGANIZATION_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubscriberMemberIdNo()).isEqualTo(UPDATED_SUBSCRIBER_MEMBER_ID_NO);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubscriberPaymentResponsibilityLevelCode())
            .isEqualTo(UPDATED_SUBSCRIBER_PAYMENT_RESPONSIBILITY_LEVEL_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubscriberFirstName()).isEqualTo(UPDATED_SUBSCRIBER_FIRST_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubscriberLastName()).isEqualTo(UPDATED_SUBSCRIBER_LAST_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubscriberGender()).isEqualTo(UPDATED_SUBSCRIBER_GENDER);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubscriberDob()).isEqualTo(UPDATED_SUBSCRIBER_DOB);
        assertThat(testSecondaryClaimsSubmissionMaster.getPrimaryInsurerPolicyNo()).isEqualTo(UPDATED_PRIMARY_INSURER_POLICY_NO);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubscriberAddressLine1()).isEqualTo(UPDATED_SUBSCRIBER_ADDRESS_LINE_1);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubscriberCity()).isEqualTo(UPDATED_SUBSCRIBER_CITY);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubscriberState()).isEqualTo(UPDATED_SUBSCRIBER_STATE);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubscriberZipCode()).isEqualTo(UPDATED_SUBSCRIBER_ZIP_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getProviderType()).isEqualTo(UPDATED_PROVIDER_TYPE);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderNpi()).isEqualTo(UPDATED_BILLING_PROVIDER_NPI);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderEin()).isEqualTo(UPDATED_BILLING_PROVIDER_EIN);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderOrganizationName())
            .isEqualTo(UPDATED_BILLING_PROVIDER_ORGANIZATION_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderAddressLine1()).isEqualTo(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_1);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderAddressLine2()).isEqualTo(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_2);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderCity()).isEqualTo(UPDATED_BILLING_PROVIDER_CITY);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderState()).isEqualTo(UPDATED_BILLING_PROVIDER_STATE);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderZipCode()).isEqualTo(UPDATED_BILLING_PROVIDER_ZIP_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderContactPersonName())
            .isEqualTo(UPDATED_BILLING_PROVIDER_CONTACT_PERSON_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderContactNo()).isEqualTo(UPDATED_BILLING_PROVIDER_CONTACT_NO);
        assertThat(testSecondaryClaimsSubmissionMaster.getClaimFilingCode()).isEqualTo(UPDATED_CLAIM_FILING_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getPatientAccountNo()).isEqualTo(UPDATED_PATIENT_ACCOUNT_NO);
        assertThat(testSecondaryClaimsSubmissionMaster.getClaimChargeAmount()).isEqualTo(UPDATED_CLAIM_CHARGE_AMOUNT);
        assertThat(testSecondaryClaimsSubmissionMaster.getPosCode()).isEqualTo(UPDATED_POS_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getClaimFrequencyCode()).isEqualTo(UPDATED_CLAIM_FREQUENCY_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getSignatureIndicator()).isEqualTo(UPDATED_SIGNATURE_INDICATOR);
        assertThat(testSecondaryClaimsSubmissionMaster.getPlanParticipationCode()).isEqualTo(UPDATED_PLAN_PARTICIPATION_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getBenefitsAssignmentCertificationIndicator())
            .isEqualTo(UPDATED_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR);
        assertThat(testSecondaryClaimsSubmissionMaster.getReleaseInformationCode()).isEqualTo(UPDATED_RELEASE_INFORMATION_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getPrimaryDiagnosis()).isEqualTo(UPDATED_PRIMARY_DIAGNOSIS);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode1()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_1);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode2()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_2);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode3()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_3);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode4()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_4);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode5()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_5);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode6()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_6);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode7()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_7);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode8()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_8);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode9()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_9);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode10()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_10);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode11()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_11);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode12()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_12);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsertedById()).isEqualTo(UPDATED_INSERTED_BY_ID);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsertedDate()).isEqualTo(UPDATED_INSERTED_DATE);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderType()).isEqualTo(UPDATED_BILLING_PROVIDER_TYPE);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsertedByName()).isEqualTo(UPDATED_INSERTED_BY_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherSubscriberAddress1()).isEqualTo(UPDATED_OTHER_SUBSCRIBER_ADDRESS_1);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherSubscriberAddress2()).isEqualTo(UPDATED_OTHER_SUBSCRIBER_ADDRESS_2);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherSubscriberCity()).isEqualTo(UPDATED_OTHER_SUBSCRIBER_CITY);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherSubscriberState()).isEqualTo(UPDATED_OTHER_SUBSCRIBER_STATE);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherSubscriberZip()).isEqualTo(UPDATED_OTHER_SUBSCRIBER_ZIP);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherInsuredQualifier()).isEqualTo(UPDATED_OTHER_INSURED_QUALIFIER);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherInsuredLastName()).isEqualTo(UPDATED_OTHER_INSURED_LAST_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherInsuredFirstName()).isEqualTo(UPDATED_OTHER_INSURED_FIRST_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherInsuredIdentifierTypeCode())
            .isEqualTo(UPDATED_OTHER_INSURED_IDENTIFIER_TYPE_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherInsuredIdentifier()).isEqualTo(UPDATED_OTHER_INSURED_IDENTIFIER);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherPayerOrganizationName()).isEqualTo(UPDATED_OTHER_PAYER_ORGANIZATION_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherPayerIdentifierTypeCode())
            .isEqualTo(UPDATED_OTHER_PAYER_IDENTIFIER_TYPE_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherPayerIdentifier()).isEqualTo(UPDATED_OTHER_PAYER_IDENTIFIER);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherPayerAdjudicationOrPaymentDate())
            .isEqualTo(UPDATED_OTHER_PAYER_ADJUDICATION_OR_PAYMENT_DATE);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherPayerClaimAdjustmentIndicator())
            .isEqualTo(UPDATED_OTHER_PAYER_CLAIM_ADJUSTMENT_INDICATOR);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherPayerClaimControlNumber())
            .isEqualTo(UPDATED_OTHER_PAYER_CLAIM_CONTROL_NUMBER);
        assertThat(testSecondaryClaimsSubmissionMaster.getPayerPaidAmount()).isEqualTo(UPDATED_PAYER_PAID_AMOUNT);
        assertThat(testSecondaryClaimsSubmissionMaster.getPaymentResponsibilityLevelCode())
            .isEqualTo(UPDATED_PAYMENT_RESPONSIBILITY_LEVEL_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getIndividualRelationshipCode()).isEqualTo(UPDATED_INDIVIDUAL_RELATIONSHIP_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getClaimFilingIndicatorCode()).isEqualTo(UPDATED_CLAIM_FILING_INDICATOR_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherPayerBenefitsAssignmentCertificationIndicator())
            .isEqualTo(UPDATED_OTHER_PAYER_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR);
        assertThat(testSecondaryClaimsSubmissionMaster.getReleaseOfInformationCode()).isEqualTo(UPDATED_RELEASE_OF_INFORMATION_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getRemainingPatientLiability()).isEqualTo(UPDATED_REMAINING_PATIENT_LIABILITY);
        assertThat(testSecondaryClaimsSubmissionMaster.getPatientSignatureGeneratedForPatient())
            .isEqualTo(UPDATED_PATIENT_SIGNATURE_GENERATED_FOR_PATIENT);
        assertThat(testSecondaryClaimsSubmissionMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testSecondaryClaimsSubmissionMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testSecondaryClaimsSubmissionMaster.getChangeHealthPrimarySubmisionMasterId())
            .isEqualTo(UPDATED_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsuredFirstName()).isEqualTo(UPDATED_INSURED_FIRST_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsuredLastName()).isEqualTo(UPDATED_INSURED_LAST_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsuredAddressLine1()).isEqualTo(UPDATED_INSURED_ADDRESS_LINE_1);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsuredAddressLine2()).isEqualTo(UPDATED_INSURED_ADDRESS_LINE_2);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsuredCity()).isEqualTo(UPDATED_INSURED_CITY);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsuredState()).isEqualTo(UPDATED_INSURED_STATE);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsuredZip()).isEqualTo(UPDATED_INSURED_ZIP);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsuredContactNo()).isEqualTo(UPDATED_INSURED_CONTACT_NO);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsuredDob()).isEqualTo(UPDATED_INSURED_DOB);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsuredGender()).isEqualTo(UPDATED_INSURED_GENDER);
        assertThat(testSecondaryClaimsSubmissionMaster.getOrderingProviderFirstName()).isEqualTo(UPDATED_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getOrderingProviderLastName()).isEqualTo(UPDATED_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getOrderingProviderNpi()).isEqualTo(UPDATED_ORDERING_PROVIDER_NPI);
        assertThat(testSecondaryClaimsSubmissionMaster.getPatientRelationshipInsured()).isEqualTo(UPDATED_PATIENT_RELATIONSHIP_INSURED);
        assertThat(testSecondaryClaimsSubmissionMaster.getPatientConditionEmployment()).isEqualTo(UPDATED_PATIENT_CONDITION_EMPLOYMENT);
        assertThat(testSecondaryClaimsSubmissionMaster.getPatientConditionAutoAccident())
            .isEqualTo(UPDATED_PATIENT_CONDITION_AUTO_ACCIDENT);
        assertThat(testSecondaryClaimsSubmissionMaster.getPatientConditionOtherAccident())
            .isEqualTo(UPDATED_PATIENT_CONDITION_OTHER_ACCIDENT);
        assertThat(testSecondaryClaimsSubmissionMaster.getIsNextLevelInsurerPresentStatus())
            .isEqualTo(UPDATED_IS_NEXT_LEVEL_INSURER_PRESENT_STATUS);
        assertThat(testSecondaryClaimsSubmissionMaster.getOriginalDos()).isEqualTo(UPDATED_ORIGINAL_DOS);
        assertThat(testSecondaryClaimsSubmissionMaster.getParNo()).isEqualTo(UPDATED_PAR_NO);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderTaxonomy()).isEqualTo(UPDATED_BILLING_PROVIDER_TAXONOMY);
        assertThat(testSecondaryClaimsSubmissionMaster.getServiceProviderNpi()).isEqualTo(UPDATED_SERVICE_PROVIDER_NPI);
        assertThat(testSecondaryClaimsSubmissionMaster.getServiceProviderOrganisationName())
            .isEqualTo(UPDATED_SERVICE_PROVIDER_ORGANISATION_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getServiceProviderAddressLine1()).isEqualTo(UPDATED_SERVICE_PROVIDER_ADDRESS_LINE_1);
        assertThat(testSecondaryClaimsSubmissionMaster.getServiceProviderAddressLine2()).isEqualTo(UPDATED_SERVICE_PROVIDER_ADDRESS_LINE_2);
        assertThat(testSecondaryClaimsSubmissionMaster.getServiceProviderCity()).isEqualTo(UPDATED_SERVICE_PROVIDER_CITY);
        assertThat(testSecondaryClaimsSubmissionMaster.getServiceProviderState()).isEqualTo(UPDATED_SERVICE_PROVIDER_STATE);
        assertThat(testSecondaryClaimsSubmissionMaster.getServiceProviderCountry()).isEqualTo(UPDATED_SERVICE_PROVIDER_COUNTRY);
        assertThat(testSecondaryClaimsSubmissionMaster.getServiceProviderZipCode()).isEqualTo(UPDATED_SERVICE_PROVIDER_ZIP_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getServiceProviderTaxonomy()).isEqualTo(UPDATED_SERVICE_PROVIDER_TAXONOMY);
        assertThat(testSecondaryClaimsSubmissionMaster.getCms1500FormName()).isEqualTo(UPDATED_CMS_1500_FORM_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getTradingPartnerAddressLine1()).isEqualTo(UPDATED_TRADING_PARTNER_ADDRESS_LINE_1);
        assertThat(testSecondaryClaimsSubmissionMaster.getTradingPartnerAddressLine2()).isEqualTo(UPDATED_TRADING_PARTNER_ADDRESS_LINE_2);
        assertThat(testSecondaryClaimsSubmissionMaster.getTradingPartnerCity()).isEqualTo(UPDATED_TRADING_PARTNER_CITY);
        assertThat(testSecondaryClaimsSubmissionMaster.getTradingPartnerState()).isEqualTo(UPDATED_TRADING_PARTNER_STATE);
        assertThat(testSecondaryClaimsSubmissionMaster.getTradingPartnerZip()).isEqualTo(UPDATED_TRADING_PARTNER_ZIP);
        assertThat(testSecondaryClaimsSubmissionMaster.getDiagnosisCodeType()).isEqualTo(UPDATED_DIAGNOSIS_CODE_TYPE);
    }

    @Test
    @Transactional
    void putNonExistingSecondaryClaimsSubmissionMaster() throws Exception {
        int databaseSizeBeforeUpdate = secondaryClaimsSubmissionMasterRepository.findAll().size();
        secondaryClaimsSubmissionMaster.setChangeHealthSecondarySubmisionMasterId(count.incrementAndGet());

        // Create the SecondaryClaimsSubmissionMaster
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, secondaryClaimsSubmissionMasterDTO.getChangeHealthSecondarySubmisionMasterId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SecondaryClaimsSubmissionMaster in the database
        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSecondaryClaimsSubmissionMaster() throws Exception {
        int databaseSizeBeforeUpdate = secondaryClaimsSubmissionMasterRepository.findAll().size();
        secondaryClaimsSubmissionMaster.setChangeHealthSecondarySubmisionMasterId(count.incrementAndGet());

        // Create the SecondaryClaimsSubmissionMaster
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SecondaryClaimsSubmissionMaster in the database
        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSecondaryClaimsSubmissionMaster() throws Exception {
        int databaseSizeBeforeUpdate = secondaryClaimsSubmissionMasterRepository.findAll().size();
        secondaryClaimsSubmissionMaster.setChangeHealthSecondarySubmisionMasterId(count.incrementAndGet());

        // Create the SecondaryClaimsSubmissionMaster
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SecondaryClaimsSubmissionMaster in the database
        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSecondaryClaimsSubmissionMasterWithPatch() throws Exception {
        // Initialize the database
        secondaryClaimsSubmissionMasterRepository.saveAndFlush(secondaryClaimsSubmissionMaster);

        int databaseSizeBeforeUpdate = secondaryClaimsSubmissionMasterRepository.findAll().size();

        // Update the secondaryClaimsSubmissionMaster using partial update
        SecondaryClaimsSubmissionMaster partialUpdatedSecondaryClaimsSubmissionMaster = new SecondaryClaimsSubmissionMaster();
        partialUpdatedSecondaryClaimsSubmissionMaster.setChangeHealthSecondarySubmisionMasterId(
            secondaryClaimsSubmissionMaster.getChangeHealthSecondarySubmisionMasterId()
        );

        partialUpdatedSecondaryClaimsSubmissionMaster
            .salesOrderId(UPDATED_SALES_ORDER_ID)
            .claimControlNo(UPDATED_CLAIM_CONTROL_NO)
            .tradingPartnerName(UPDATED_TRADING_PARTNER_NAME)
            .submitterOrganizationName(UPDATED_SUBMITTER_ORGANIZATION_NAME)
            .submitterContactNo(UPDATED_SUBMITTER_CONTACT_NO)
            .subscriberFirstName(UPDATED_SUBSCRIBER_FIRST_NAME)
            .subscriberLastName(UPDATED_SUBSCRIBER_LAST_NAME)
            .subscriberGender(UPDATED_SUBSCRIBER_GENDER)
            .subscriberAddressLine1(UPDATED_SUBSCRIBER_ADDRESS_LINE_1)
            .subscriberZipCode(UPDATED_SUBSCRIBER_ZIP_CODE)
            .billingProviderEin(UPDATED_BILLING_PROVIDER_EIN)
            .billingProviderAddressLine1(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_1)
            .billingProviderAddressLine2(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_2)
            .billingProviderZipCode(UPDATED_BILLING_PROVIDER_ZIP_CODE)
            .billingProviderContactPersonName(UPDATED_BILLING_PROVIDER_CONTACT_PERSON_NAME)
            .claimFilingCode(UPDATED_CLAIM_FILING_CODE)
            .patientAccountNo(UPDATED_PATIENT_ACCOUNT_NO)
            .claimChargeAmount(UPDATED_CLAIM_CHARGE_AMOUNT)
            .posCode(UPDATED_POS_CODE)
            .claimFrequencyCode(UPDATED_CLAIM_FREQUENCY_CODE)
            .signatureIndicator(UPDATED_SIGNATURE_INDICATOR)
            .primaryDiagnosis(UPDATED_PRIMARY_DIAGNOSIS)
            .icd10diagnosisCode4(UPDATED_ICD_10_DIAGNOSIS_CODE_4)
            .icd10diagnosisCode5(UPDATED_ICD_10_DIAGNOSIS_CODE_5)
            .icd10diagnosisCode6(UPDATED_ICD_10_DIAGNOSIS_CODE_6)
            .icd10diagnosisCode8(UPDATED_ICD_10_DIAGNOSIS_CODE_8)
            .icd10diagnosisCode10(UPDATED_ICD_10_DIAGNOSIS_CODE_10)
            .icd10diagnosisCode11(UPDATED_ICD_10_DIAGNOSIS_CODE_11)
            .billingProviderType(UPDATED_BILLING_PROVIDER_TYPE)
            .insertedByName(UPDATED_INSERTED_BY_NAME)
            .status(UPDATED_STATUS)
            .otherSubscriberAddress1(UPDATED_OTHER_SUBSCRIBER_ADDRESS_1)
            .otherSubscriberState(UPDATED_OTHER_SUBSCRIBER_STATE)
            .otherInsuredQualifier(UPDATED_OTHER_INSURED_QUALIFIER)
            .otherInsuredLastName(UPDATED_OTHER_INSURED_LAST_NAME)
            .otherInsuredIdentifierTypeCode(UPDATED_OTHER_INSURED_IDENTIFIER_TYPE_CODE)
            .otherInsuredIdentifier(UPDATED_OTHER_INSURED_IDENTIFIER)
            .otherPayerOrganizationName(UPDATED_OTHER_PAYER_ORGANIZATION_NAME)
            .otherPayerAdjudicationOrPaymentDate(UPDATED_OTHER_PAYER_ADJUDICATION_OR_PAYMENT_DATE)
            .payerPaidAmount(UPDATED_PAYER_PAID_AMOUNT)
            .paymentResponsibilityLevelCode(UPDATED_PAYMENT_RESPONSIBILITY_LEVEL_CODE)
            .changeHealthPrimarySubmisionMasterId(UPDATED_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID)
            .insuredAddressLine1(UPDATED_INSURED_ADDRESS_LINE_1)
            .insuredAddressLine2(UPDATED_INSURED_ADDRESS_LINE_2)
            .insuredCity(UPDATED_INSURED_CITY)
            .insuredState(UPDATED_INSURED_STATE)
            .insuredContactNo(UPDATED_INSURED_CONTACT_NO)
            .insuredDob(UPDATED_INSURED_DOB)
            .insuredGender(UPDATED_INSURED_GENDER)
            .orderingProviderLastName(UPDATED_ORDERING_PROVIDER_LAST_NAME)
            .patientRelationshipInsured(UPDATED_PATIENT_RELATIONSHIP_INSURED)
            .patientConditionEmployment(UPDATED_PATIENT_CONDITION_EMPLOYMENT)
            .originalDos(UPDATED_ORIGINAL_DOS)
            .serviceProviderNpi(UPDATED_SERVICE_PROVIDER_NPI)
            .serviceProviderAddressLine1(UPDATED_SERVICE_PROVIDER_ADDRESS_LINE_1)
            .serviceProviderCity(UPDATED_SERVICE_PROVIDER_CITY)
            .serviceProviderState(UPDATED_SERVICE_PROVIDER_STATE)
            .tradingPartnerAddressLine1(UPDATED_TRADING_PARTNER_ADDRESS_LINE_1)
            .tradingPartnerAddressLine2(UPDATED_TRADING_PARTNER_ADDRESS_LINE_2);

        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSecondaryClaimsSubmissionMaster.getChangeHealthSecondarySubmisionMasterId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSecondaryClaimsSubmissionMaster))
            )
            .andExpect(status().isOk());

        // Validate the SecondaryClaimsSubmissionMaster in the database
        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeUpdate);
        SecondaryClaimsSubmissionMaster testSecondaryClaimsSubmissionMaster = secondaryClaimsSubmissionMasterList.get(
            secondaryClaimsSubmissionMasterList.size() - 1
        );
        assertThat(testSecondaryClaimsSubmissionMaster.getSalesOrderId()).isEqualTo(UPDATED_SALES_ORDER_ID);
        assertThat(testSecondaryClaimsSubmissionMaster.getClaimControlNo()).isEqualTo(UPDATED_CLAIM_CONTROL_NO);
        assertThat(testSecondaryClaimsSubmissionMaster.getTradingPartnerServiceId()).isEqualTo(DEFAULT_TRADING_PARTNER_SERVICE_ID);
        assertThat(testSecondaryClaimsSubmissionMaster.getTradingPartnerName()).isEqualTo(UPDATED_TRADING_PARTNER_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubmitterOrganizationName()).isEqualTo(UPDATED_SUBMITTER_ORGANIZATION_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubmitterContactPersonName()).isEqualTo(DEFAULT_SUBMITTER_CONTACT_PERSON_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubmitterContactNo()).isEqualTo(UPDATED_SUBMITTER_CONTACT_NO);
        assertThat(testSecondaryClaimsSubmissionMaster.getReceiverOrganizationName()).isEqualTo(DEFAULT_RECEIVER_ORGANIZATION_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubscriberMemberIdNo()).isEqualTo(DEFAULT_SUBSCRIBER_MEMBER_ID_NO);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubscriberPaymentResponsibilityLevelCode())
            .isEqualTo(DEFAULT_SUBSCRIBER_PAYMENT_RESPONSIBILITY_LEVEL_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubscriberFirstName()).isEqualTo(UPDATED_SUBSCRIBER_FIRST_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubscriberLastName()).isEqualTo(UPDATED_SUBSCRIBER_LAST_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubscriberGender()).isEqualTo(UPDATED_SUBSCRIBER_GENDER);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubscriberDob()).isEqualTo(DEFAULT_SUBSCRIBER_DOB);
        assertThat(testSecondaryClaimsSubmissionMaster.getPrimaryInsurerPolicyNo()).isEqualTo(DEFAULT_PRIMARY_INSURER_POLICY_NO);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubscriberAddressLine1()).isEqualTo(UPDATED_SUBSCRIBER_ADDRESS_LINE_1);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubscriberCity()).isEqualTo(DEFAULT_SUBSCRIBER_CITY);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubscriberState()).isEqualTo(DEFAULT_SUBSCRIBER_STATE);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubscriberZipCode()).isEqualTo(UPDATED_SUBSCRIBER_ZIP_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getProviderType()).isEqualTo(DEFAULT_PROVIDER_TYPE);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderNpi()).isEqualTo(DEFAULT_BILLING_PROVIDER_NPI);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderEin()).isEqualTo(UPDATED_BILLING_PROVIDER_EIN);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderOrganizationName())
            .isEqualTo(DEFAULT_BILLING_PROVIDER_ORGANIZATION_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderAddressLine1()).isEqualTo(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_1);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderAddressLine2()).isEqualTo(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_2);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderCity()).isEqualTo(DEFAULT_BILLING_PROVIDER_CITY);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderState()).isEqualTo(DEFAULT_BILLING_PROVIDER_STATE);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderZipCode()).isEqualTo(UPDATED_BILLING_PROVIDER_ZIP_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderContactPersonName())
            .isEqualTo(UPDATED_BILLING_PROVIDER_CONTACT_PERSON_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderContactNo()).isEqualTo(DEFAULT_BILLING_PROVIDER_CONTACT_NO);
        assertThat(testSecondaryClaimsSubmissionMaster.getClaimFilingCode()).isEqualTo(UPDATED_CLAIM_FILING_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getPatientAccountNo()).isEqualTo(UPDATED_PATIENT_ACCOUNT_NO);
        assertThat(testSecondaryClaimsSubmissionMaster.getClaimChargeAmount()).isEqualTo(UPDATED_CLAIM_CHARGE_AMOUNT);
        assertThat(testSecondaryClaimsSubmissionMaster.getPosCode()).isEqualTo(UPDATED_POS_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getClaimFrequencyCode()).isEqualTo(UPDATED_CLAIM_FREQUENCY_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getSignatureIndicator()).isEqualTo(UPDATED_SIGNATURE_INDICATOR);
        assertThat(testSecondaryClaimsSubmissionMaster.getPlanParticipationCode()).isEqualTo(DEFAULT_PLAN_PARTICIPATION_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getBenefitsAssignmentCertificationIndicator())
            .isEqualTo(DEFAULT_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR);
        assertThat(testSecondaryClaimsSubmissionMaster.getReleaseInformationCode()).isEqualTo(DEFAULT_RELEASE_INFORMATION_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getPrimaryDiagnosis()).isEqualTo(UPDATED_PRIMARY_DIAGNOSIS);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode1()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_1);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode2()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_2);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode3()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_3);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode4()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_4);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode5()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_5);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode6()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_6);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode7()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_7);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode8()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_8);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode9()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_9);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode10()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_10);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode11()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_11);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode12()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_12);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsertedById()).isEqualTo(DEFAULT_INSERTED_BY_ID);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsertedDate()).isEqualTo(DEFAULT_INSERTED_DATE);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderType()).isEqualTo(UPDATED_BILLING_PROVIDER_TYPE);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsertedByName()).isEqualTo(UPDATED_INSERTED_BY_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherSubscriberAddress1()).isEqualTo(UPDATED_OTHER_SUBSCRIBER_ADDRESS_1);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherSubscriberAddress2()).isEqualTo(DEFAULT_OTHER_SUBSCRIBER_ADDRESS_2);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherSubscriberCity()).isEqualTo(DEFAULT_OTHER_SUBSCRIBER_CITY);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherSubscriberState()).isEqualTo(UPDATED_OTHER_SUBSCRIBER_STATE);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherSubscriberZip()).isEqualTo(DEFAULT_OTHER_SUBSCRIBER_ZIP);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherInsuredQualifier()).isEqualTo(UPDATED_OTHER_INSURED_QUALIFIER);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherInsuredLastName()).isEqualTo(UPDATED_OTHER_INSURED_LAST_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherInsuredFirstName()).isEqualTo(DEFAULT_OTHER_INSURED_FIRST_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherInsuredIdentifierTypeCode())
            .isEqualTo(UPDATED_OTHER_INSURED_IDENTIFIER_TYPE_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherInsuredIdentifier()).isEqualTo(UPDATED_OTHER_INSURED_IDENTIFIER);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherPayerOrganizationName()).isEqualTo(UPDATED_OTHER_PAYER_ORGANIZATION_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherPayerIdentifierTypeCode())
            .isEqualTo(DEFAULT_OTHER_PAYER_IDENTIFIER_TYPE_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherPayerIdentifier()).isEqualTo(DEFAULT_OTHER_PAYER_IDENTIFIER);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherPayerAdjudicationOrPaymentDate())
            .isEqualTo(UPDATED_OTHER_PAYER_ADJUDICATION_OR_PAYMENT_DATE);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherPayerClaimAdjustmentIndicator())
            .isEqualTo(DEFAULT_OTHER_PAYER_CLAIM_ADJUSTMENT_INDICATOR);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherPayerClaimControlNumber())
            .isEqualTo(DEFAULT_OTHER_PAYER_CLAIM_CONTROL_NUMBER);
        assertThat(testSecondaryClaimsSubmissionMaster.getPayerPaidAmount()).isEqualTo(UPDATED_PAYER_PAID_AMOUNT);
        assertThat(testSecondaryClaimsSubmissionMaster.getPaymentResponsibilityLevelCode())
            .isEqualTo(UPDATED_PAYMENT_RESPONSIBILITY_LEVEL_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getIndividualRelationshipCode()).isEqualTo(DEFAULT_INDIVIDUAL_RELATIONSHIP_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getClaimFilingIndicatorCode()).isEqualTo(DEFAULT_CLAIM_FILING_INDICATOR_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherPayerBenefitsAssignmentCertificationIndicator())
            .isEqualTo(DEFAULT_OTHER_PAYER_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR);
        assertThat(testSecondaryClaimsSubmissionMaster.getReleaseOfInformationCode()).isEqualTo(DEFAULT_RELEASE_OF_INFORMATION_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getRemainingPatientLiability()).isEqualTo(DEFAULT_REMAINING_PATIENT_LIABILITY);
        assertThat(testSecondaryClaimsSubmissionMaster.getPatientSignatureGeneratedForPatient())
            .isEqualTo(DEFAULT_PATIENT_SIGNATURE_GENERATED_FOR_PATIENT);
        assertThat(testSecondaryClaimsSubmissionMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testSecondaryClaimsSubmissionMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testSecondaryClaimsSubmissionMaster.getChangeHealthPrimarySubmisionMasterId())
            .isEqualTo(UPDATED_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsuredFirstName()).isEqualTo(DEFAULT_INSURED_FIRST_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsuredLastName()).isEqualTo(DEFAULT_INSURED_LAST_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsuredAddressLine1()).isEqualTo(UPDATED_INSURED_ADDRESS_LINE_1);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsuredAddressLine2()).isEqualTo(UPDATED_INSURED_ADDRESS_LINE_2);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsuredCity()).isEqualTo(UPDATED_INSURED_CITY);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsuredState()).isEqualTo(UPDATED_INSURED_STATE);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsuredZip()).isEqualTo(DEFAULT_INSURED_ZIP);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsuredContactNo()).isEqualTo(UPDATED_INSURED_CONTACT_NO);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsuredDob()).isEqualTo(UPDATED_INSURED_DOB);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsuredGender()).isEqualTo(UPDATED_INSURED_GENDER);
        assertThat(testSecondaryClaimsSubmissionMaster.getOrderingProviderFirstName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getOrderingProviderLastName()).isEqualTo(UPDATED_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getOrderingProviderNpi()).isEqualTo(DEFAULT_ORDERING_PROVIDER_NPI);
        assertThat(testSecondaryClaimsSubmissionMaster.getPatientRelationshipInsured()).isEqualTo(UPDATED_PATIENT_RELATIONSHIP_INSURED);
        assertThat(testSecondaryClaimsSubmissionMaster.getPatientConditionEmployment()).isEqualTo(UPDATED_PATIENT_CONDITION_EMPLOYMENT);
        assertThat(testSecondaryClaimsSubmissionMaster.getPatientConditionAutoAccident())
            .isEqualTo(DEFAULT_PATIENT_CONDITION_AUTO_ACCIDENT);
        assertThat(testSecondaryClaimsSubmissionMaster.getPatientConditionOtherAccident())
            .isEqualTo(DEFAULT_PATIENT_CONDITION_OTHER_ACCIDENT);
        assertThat(testSecondaryClaimsSubmissionMaster.getIsNextLevelInsurerPresentStatus())
            .isEqualTo(DEFAULT_IS_NEXT_LEVEL_INSURER_PRESENT_STATUS);
        assertThat(testSecondaryClaimsSubmissionMaster.getOriginalDos()).isEqualTo(UPDATED_ORIGINAL_DOS);
        assertThat(testSecondaryClaimsSubmissionMaster.getParNo()).isEqualTo(DEFAULT_PAR_NO);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderTaxonomy()).isEqualTo(DEFAULT_BILLING_PROVIDER_TAXONOMY);
        assertThat(testSecondaryClaimsSubmissionMaster.getServiceProviderNpi()).isEqualTo(UPDATED_SERVICE_PROVIDER_NPI);
        assertThat(testSecondaryClaimsSubmissionMaster.getServiceProviderOrganisationName())
            .isEqualTo(DEFAULT_SERVICE_PROVIDER_ORGANISATION_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getServiceProviderAddressLine1()).isEqualTo(UPDATED_SERVICE_PROVIDER_ADDRESS_LINE_1);
        assertThat(testSecondaryClaimsSubmissionMaster.getServiceProviderAddressLine2()).isEqualTo(DEFAULT_SERVICE_PROVIDER_ADDRESS_LINE_2);
        assertThat(testSecondaryClaimsSubmissionMaster.getServiceProviderCity()).isEqualTo(UPDATED_SERVICE_PROVIDER_CITY);
        assertThat(testSecondaryClaimsSubmissionMaster.getServiceProviderState()).isEqualTo(UPDATED_SERVICE_PROVIDER_STATE);
        assertThat(testSecondaryClaimsSubmissionMaster.getServiceProviderCountry()).isEqualTo(DEFAULT_SERVICE_PROVIDER_COUNTRY);
        assertThat(testSecondaryClaimsSubmissionMaster.getServiceProviderZipCode()).isEqualTo(DEFAULT_SERVICE_PROVIDER_ZIP_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getServiceProviderTaxonomy()).isEqualTo(DEFAULT_SERVICE_PROVIDER_TAXONOMY);
        assertThat(testSecondaryClaimsSubmissionMaster.getCms1500FormName()).isEqualTo(DEFAULT_CMS_1500_FORM_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getTradingPartnerAddressLine1()).isEqualTo(UPDATED_TRADING_PARTNER_ADDRESS_LINE_1);
        assertThat(testSecondaryClaimsSubmissionMaster.getTradingPartnerAddressLine2()).isEqualTo(UPDATED_TRADING_PARTNER_ADDRESS_LINE_2);
        assertThat(testSecondaryClaimsSubmissionMaster.getTradingPartnerCity()).isEqualTo(DEFAULT_TRADING_PARTNER_CITY);
        assertThat(testSecondaryClaimsSubmissionMaster.getTradingPartnerState()).isEqualTo(DEFAULT_TRADING_PARTNER_STATE);
        assertThat(testSecondaryClaimsSubmissionMaster.getTradingPartnerZip()).isEqualTo(DEFAULT_TRADING_PARTNER_ZIP);
        assertThat(testSecondaryClaimsSubmissionMaster.getDiagnosisCodeType()).isEqualTo(DEFAULT_DIAGNOSIS_CODE_TYPE);
    }

    @Test
    @Transactional
    void fullUpdateSecondaryClaimsSubmissionMasterWithPatch() throws Exception {
        // Initialize the database
        secondaryClaimsSubmissionMasterRepository.saveAndFlush(secondaryClaimsSubmissionMaster);

        int databaseSizeBeforeUpdate = secondaryClaimsSubmissionMasterRepository.findAll().size();

        // Update the secondaryClaimsSubmissionMaster using partial update
        SecondaryClaimsSubmissionMaster partialUpdatedSecondaryClaimsSubmissionMaster = new SecondaryClaimsSubmissionMaster();
        partialUpdatedSecondaryClaimsSubmissionMaster.setChangeHealthSecondarySubmisionMasterId(
            secondaryClaimsSubmissionMaster.getChangeHealthSecondarySubmisionMasterId()
        );

        partialUpdatedSecondaryClaimsSubmissionMaster
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
            .providerType(UPDATED_PROVIDER_TYPE)
            .billingProviderNpi(UPDATED_BILLING_PROVIDER_NPI)
            .billingProviderEin(UPDATED_BILLING_PROVIDER_EIN)
            .billingProviderOrganizationName(UPDATED_BILLING_PROVIDER_ORGANIZATION_NAME)
            .billingProviderAddressLine1(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_1)
            .billingProviderAddressLine2(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_2)
            .billingProviderCity(UPDATED_BILLING_PROVIDER_CITY)
            .billingProviderState(UPDATED_BILLING_PROVIDER_STATE)
            .billingProviderZipCode(UPDATED_BILLING_PROVIDER_ZIP_CODE)
            .billingProviderContactPersonName(UPDATED_BILLING_PROVIDER_CONTACT_PERSON_NAME)
            .billingProviderContactNo(UPDATED_BILLING_PROVIDER_CONTACT_NO)
            .claimFilingCode(UPDATED_CLAIM_FILING_CODE)
            .patientAccountNo(UPDATED_PATIENT_ACCOUNT_NO)
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
            .billingProviderType(UPDATED_BILLING_PROVIDER_TYPE)
            .insertedByName(UPDATED_INSERTED_BY_NAME)
            .status(UPDATED_STATUS)
            .otherSubscriberAddress1(UPDATED_OTHER_SUBSCRIBER_ADDRESS_1)
            .otherSubscriberAddress2(UPDATED_OTHER_SUBSCRIBER_ADDRESS_2)
            .otherSubscriberCity(UPDATED_OTHER_SUBSCRIBER_CITY)
            .otherSubscriberState(UPDATED_OTHER_SUBSCRIBER_STATE)
            .otherSubscriberZip(UPDATED_OTHER_SUBSCRIBER_ZIP)
            .otherInsuredQualifier(UPDATED_OTHER_INSURED_QUALIFIER)
            .otherInsuredLastName(UPDATED_OTHER_INSURED_LAST_NAME)
            .otherInsuredFirstName(UPDATED_OTHER_INSURED_FIRST_NAME)
            .otherInsuredIdentifierTypeCode(UPDATED_OTHER_INSURED_IDENTIFIER_TYPE_CODE)
            .otherInsuredIdentifier(UPDATED_OTHER_INSURED_IDENTIFIER)
            .otherPayerOrganizationName(UPDATED_OTHER_PAYER_ORGANIZATION_NAME)
            .otherPayerIdentifierTypeCode(UPDATED_OTHER_PAYER_IDENTIFIER_TYPE_CODE)
            .otherPayerIdentifier(UPDATED_OTHER_PAYER_IDENTIFIER)
            .otherPayerAdjudicationOrPaymentDate(UPDATED_OTHER_PAYER_ADJUDICATION_OR_PAYMENT_DATE)
            .otherPayerClaimAdjustmentIndicator(UPDATED_OTHER_PAYER_CLAIM_ADJUSTMENT_INDICATOR)
            .otherPayerClaimControlNumber(UPDATED_OTHER_PAYER_CLAIM_CONTROL_NUMBER)
            .payerPaidAmount(UPDATED_PAYER_PAID_AMOUNT)
            .paymentResponsibilityLevelCode(UPDATED_PAYMENT_RESPONSIBILITY_LEVEL_CODE)
            .individualRelationshipCode(UPDATED_INDIVIDUAL_RELATIONSHIP_CODE)
            .claimFilingIndicatorCode(UPDATED_CLAIM_FILING_INDICATOR_CODE)
            .otherPayerBenefitsAssignmentCertificationIndicator(UPDATED_OTHER_PAYER_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR)
            .releaseOfInformationCode(UPDATED_RELEASE_OF_INFORMATION_CODE)
            .remainingPatientLiability(UPDATED_REMAINING_PATIENT_LIABILITY)
            .patientSignatureGeneratedForPatient(UPDATED_PATIENT_SIGNATURE_GENERATED_FOR_PATIENT)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .changeHealthPrimarySubmisionMasterId(UPDATED_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID)
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

        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSecondaryClaimsSubmissionMaster.getChangeHealthSecondarySubmisionMasterId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSecondaryClaimsSubmissionMaster))
            )
            .andExpect(status().isOk());

        // Validate the SecondaryClaimsSubmissionMaster in the database
        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeUpdate);
        SecondaryClaimsSubmissionMaster testSecondaryClaimsSubmissionMaster = secondaryClaimsSubmissionMasterList.get(
            secondaryClaimsSubmissionMasterList.size() - 1
        );
        assertThat(testSecondaryClaimsSubmissionMaster.getSalesOrderId()).isEqualTo(UPDATED_SALES_ORDER_ID);
        assertThat(testSecondaryClaimsSubmissionMaster.getClaimControlNo()).isEqualTo(UPDATED_CLAIM_CONTROL_NO);
        assertThat(testSecondaryClaimsSubmissionMaster.getTradingPartnerServiceId()).isEqualTo(UPDATED_TRADING_PARTNER_SERVICE_ID);
        assertThat(testSecondaryClaimsSubmissionMaster.getTradingPartnerName()).isEqualTo(UPDATED_TRADING_PARTNER_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubmitterOrganizationName()).isEqualTo(UPDATED_SUBMITTER_ORGANIZATION_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubmitterContactPersonName()).isEqualTo(UPDATED_SUBMITTER_CONTACT_PERSON_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubmitterContactNo()).isEqualTo(UPDATED_SUBMITTER_CONTACT_NO);
        assertThat(testSecondaryClaimsSubmissionMaster.getReceiverOrganizationName()).isEqualTo(UPDATED_RECEIVER_ORGANIZATION_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubscriberMemberIdNo()).isEqualTo(UPDATED_SUBSCRIBER_MEMBER_ID_NO);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubscriberPaymentResponsibilityLevelCode())
            .isEqualTo(UPDATED_SUBSCRIBER_PAYMENT_RESPONSIBILITY_LEVEL_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubscriberFirstName()).isEqualTo(UPDATED_SUBSCRIBER_FIRST_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubscriberLastName()).isEqualTo(UPDATED_SUBSCRIBER_LAST_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubscriberGender()).isEqualTo(UPDATED_SUBSCRIBER_GENDER);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubscriberDob()).isEqualTo(UPDATED_SUBSCRIBER_DOB);
        assertThat(testSecondaryClaimsSubmissionMaster.getPrimaryInsurerPolicyNo()).isEqualTo(UPDATED_PRIMARY_INSURER_POLICY_NO);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubscriberAddressLine1()).isEqualTo(UPDATED_SUBSCRIBER_ADDRESS_LINE_1);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubscriberCity()).isEqualTo(UPDATED_SUBSCRIBER_CITY);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubscriberState()).isEqualTo(UPDATED_SUBSCRIBER_STATE);
        assertThat(testSecondaryClaimsSubmissionMaster.getSubscriberZipCode()).isEqualTo(UPDATED_SUBSCRIBER_ZIP_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getProviderType()).isEqualTo(UPDATED_PROVIDER_TYPE);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderNpi()).isEqualTo(UPDATED_BILLING_PROVIDER_NPI);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderEin()).isEqualTo(UPDATED_BILLING_PROVIDER_EIN);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderOrganizationName())
            .isEqualTo(UPDATED_BILLING_PROVIDER_ORGANIZATION_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderAddressLine1()).isEqualTo(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_1);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderAddressLine2()).isEqualTo(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_2);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderCity()).isEqualTo(UPDATED_BILLING_PROVIDER_CITY);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderState()).isEqualTo(UPDATED_BILLING_PROVIDER_STATE);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderZipCode()).isEqualTo(UPDATED_BILLING_PROVIDER_ZIP_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderContactPersonName())
            .isEqualTo(UPDATED_BILLING_PROVIDER_CONTACT_PERSON_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderContactNo()).isEqualTo(UPDATED_BILLING_PROVIDER_CONTACT_NO);
        assertThat(testSecondaryClaimsSubmissionMaster.getClaimFilingCode()).isEqualTo(UPDATED_CLAIM_FILING_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getPatientAccountNo()).isEqualTo(UPDATED_PATIENT_ACCOUNT_NO);
        assertThat(testSecondaryClaimsSubmissionMaster.getClaimChargeAmount()).isEqualTo(UPDATED_CLAIM_CHARGE_AMOUNT);
        assertThat(testSecondaryClaimsSubmissionMaster.getPosCode()).isEqualTo(UPDATED_POS_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getClaimFrequencyCode()).isEqualTo(UPDATED_CLAIM_FREQUENCY_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getSignatureIndicator()).isEqualTo(UPDATED_SIGNATURE_INDICATOR);
        assertThat(testSecondaryClaimsSubmissionMaster.getPlanParticipationCode()).isEqualTo(UPDATED_PLAN_PARTICIPATION_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getBenefitsAssignmentCertificationIndicator())
            .isEqualTo(UPDATED_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR);
        assertThat(testSecondaryClaimsSubmissionMaster.getReleaseInformationCode()).isEqualTo(UPDATED_RELEASE_INFORMATION_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getPrimaryDiagnosis()).isEqualTo(UPDATED_PRIMARY_DIAGNOSIS);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode1()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_1);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode2()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_2);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode3()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_3);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode4()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_4);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode5()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_5);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode6()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_6);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode7()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_7);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode8()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_8);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode9()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_9);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode10()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_10);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode11()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_11);
        assertThat(testSecondaryClaimsSubmissionMaster.getIcd10diagnosisCode12()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_12);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsertedById()).isEqualTo(UPDATED_INSERTED_BY_ID);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsertedDate()).isEqualTo(UPDATED_INSERTED_DATE);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderType()).isEqualTo(UPDATED_BILLING_PROVIDER_TYPE);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsertedByName()).isEqualTo(UPDATED_INSERTED_BY_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherSubscriberAddress1()).isEqualTo(UPDATED_OTHER_SUBSCRIBER_ADDRESS_1);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherSubscriberAddress2()).isEqualTo(UPDATED_OTHER_SUBSCRIBER_ADDRESS_2);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherSubscriberCity()).isEqualTo(UPDATED_OTHER_SUBSCRIBER_CITY);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherSubscriberState()).isEqualTo(UPDATED_OTHER_SUBSCRIBER_STATE);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherSubscriberZip()).isEqualTo(UPDATED_OTHER_SUBSCRIBER_ZIP);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherInsuredQualifier()).isEqualTo(UPDATED_OTHER_INSURED_QUALIFIER);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherInsuredLastName()).isEqualTo(UPDATED_OTHER_INSURED_LAST_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherInsuredFirstName()).isEqualTo(UPDATED_OTHER_INSURED_FIRST_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherInsuredIdentifierTypeCode())
            .isEqualTo(UPDATED_OTHER_INSURED_IDENTIFIER_TYPE_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherInsuredIdentifier()).isEqualTo(UPDATED_OTHER_INSURED_IDENTIFIER);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherPayerOrganizationName()).isEqualTo(UPDATED_OTHER_PAYER_ORGANIZATION_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherPayerIdentifierTypeCode())
            .isEqualTo(UPDATED_OTHER_PAYER_IDENTIFIER_TYPE_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherPayerIdentifier()).isEqualTo(UPDATED_OTHER_PAYER_IDENTIFIER);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherPayerAdjudicationOrPaymentDate())
            .isEqualTo(UPDATED_OTHER_PAYER_ADJUDICATION_OR_PAYMENT_DATE);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherPayerClaimAdjustmentIndicator())
            .isEqualTo(UPDATED_OTHER_PAYER_CLAIM_ADJUSTMENT_INDICATOR);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherPayerClaimControlNumber())
            .isEqualTo(UPDATED_OTHER_PAYER_CLAIM_CONTROL_NUMBER);
        assertThat(testSecondaryClaimsSubmissionMaster.getPayerPaidAmount()).isEqualTo(UPDATED_PAYER_PAID_AMOUNT);
        assertThat(testSecondaryClaimsSubmissionMaster.getPaymentResponsibilityLevelCode())
            .isEqualTo(UPDATED_PAYMENT_RESPONSIBILITY_LEVEL_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getIndividualRelationshipCode()).isEqualTo(UPDATED_INDIVIDUAL_RELATIONSHIP_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getClaimFilingIndicatorCode()).isEqualTo(UPDATED_CLAIM_FILING_INDICATOR_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getOtherPayerBenefitsAssignmentCertificationIndicator())
            .isEqualTo(UPDATED_OTHER_PAYER_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR);
        assertThat(testSecondaryClaimsSubmissionMaster.getReleaseOfInformationCode()).isEqualTo(UPDATED_RELEASE_OF_INFORMATION_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getRemainingPatientLiability()).isEqualTo(UPDATED_REMAINING_PATIENT_LIABILITY);
        assertThat(testSecondaryClaimsSubmissionMaster.getPatientSignatureGeneratedForPatient())
            .isEqualTo(UPDATED_PATIENT_SIGNATURE_GENERATED_FOR_PATIENT);
        assertThat(testSecondaryClaimsSubmissionMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testSecondaryClaimsSubmissionMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testSecondaryClaimsSubmissionMaster.getChangeHealthPrimarySubmisionMasterId())
            .isEqualTo(UPDATED_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsuredFirstName()).isEqualTo(UPDATED_INSURED_FIRST_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsuredLastName()).isEqualTo(UPDATED_INSURED_LAST_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsuredAddressLine1()).isEqualTo(UPDATED_INSURED_ADDRESS_LINE_1);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsuredAddressLine2()).isEqualTo(UPDATED_INSURED_ADDRESS_LINE_2);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsuredCity()).isEqualTo(UPDATED_INSURED_CITY);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsuredState()).isEqualTo(UPDATED_INSURED_STATE);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsuredZip()).isEqualTo(UPDATED_INSURED_ZIP);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsuredContactNo()).isEqualTo(UPDATED_INSURED_CONTACT_NO);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsuredDob()).isEqualTo(UPDATED_INSURED_DOB);
        assertThat(testSecondaryClaimsSubmissionMaster.getInsuredGender()).isEqualTo(UPDATED_INSURED_GENDER);
        assertThat(testSecondaryClaimsSubmissionMaster.getOrderingProviderFirstName()).isEqualTo(UPDATED_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getOrderingProviderLastName()).isEqualTo(UPDATED_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getOrderingProviderNpi()).isEqualTo(UPDATED_ORDERING_PROVIDER_NPI);
        assertThat(testSecondaryClaimsSubmissionMaster.getPatientRelationshipInsured()).isEqualTo(UPDATED_PATIENT_RELATIONSHIP_INSURED);
        assertThat(testSecondaryClaimsSubmissionMaster.getPatientConditionEmployment()).isEqualTo(UPDATED_PATIENT_CONDITION_EMPLOYMENT);
        assertThat(testSecondaryClaimsSubmissionMaster.getPatientConditionAutoAccident())
            .isEqualTo(UPDATED_PATIENT_CONDITION_AUTO_ACCIDENT);
        assertThat(testSecondaryClaimsSubmissionMaster.getPatientConditionOtherAccident())
            .isEqualTo(UPDATED_PATIENT_CONDITION_OTHER_ACCIDENT);
        assertThat(testSecondaryClaimsSubmissionMaster.getIsNextLevelInsurerPresentStatus())
            .isEqualTo(UPDATED_IS_NEXT_LEVEL_INSURER_PRESENT_STATUS);
        assertThat(testSecondaryClaimsSubmissionMaster.getOriginalDos()).isEqualTo(UPDATED_ORIGINAL_DOS);
        assertThat(testSecondaryClaimsSubmissionMaster.getParNo()).isEqualTo(UPDATED_PAR_NO);
        assertThat(testSecondaryClaimsSubmissionMaster.getBillingProviderTaxonomy()).isEqualTo(UPDATED_BILLING_PROVIDER_TAXONOMY);
        assertThat(testSecondaryClaimsSubmissionMaster.getServiceProviderNpi()).isEqualTo(UPDATED_SERVICE_PROVIDER_NPI);
        assertThat(testSecondaryClaimsSubmissionMaster.getServiceProviderOrganisationName())
            .isEqualTo(UPDATED_SERVICE_PROVIDER_ORGANISATION_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getServiceProviderAddressLine1()).isEqualTo(UPDATED_SERVICE_PROVIDER_ADDRESS_LINE_1);
        assertThat(testSecondaryClaimsSubmissionMaster.getServiceProviderAddressLine2()).isEqualTo(UPDATED_SERVICE_PROVIDER_ADDRESS_LINE_2);
        assertThat(testSecondaryClaimsSubmissionMaster.getServiceProviderCity()).isEqualTo(UPDATED_SERVICE_PROVIDER_CITY);
        assertThat(testSecondaryClaimsSubmissionMaster.getServiceProviderState()).isEqualTo(UPDATED_SERVICE_PROVIDER_STATE);
        assertThat(testSecondaryClaimsSubmissionMaster.getServiceProviderCountry()).isEqualTo(UPDATED_SERVICE_PROVIDER_COUNTRY);
        assertThat(testSecondaryClaimsSubmissionMaster.getServiceProviderZipCode()).isEqualTo(UPDATED_SERVICE_PROVIDER_ZIP_CODE);
        assertThat(testSecondaryClaimsSubmissionMaster.getServiceProviderTaxonomy()).isEqualTo(UPDATED_SERVICE_PROVIDER_TAXONOMY);
        assertThat(testSecondaryClaimsSubmissionMaster.getCms1500FormName()).isEqualTo(UPDATED_CMS_1500_FORM_NAME);
        assertThat(testSecondaryClaimsSubmissionMaster.getTradingPartnerAddressLine1()).isEqualTo(UPDATED_TRADING_PARTNER_ADDRESS_LINE_1);
        assertThat(testSecondaryClaimsSubmissionMaster.getTradingPartnerAddressLine2()).isEqualTo(UPDATED_TRADING_PARTNER_ADDRESS_LINE_2);
        assertThat(testSecondaryClaimsSubmissionMaster.getTradingPartnerCity()).isEqualTo(UPDATED_TRADING_PARTNER_CITY);
        assertThat(testSecondaryClaimsSubmissionMaster.getTradingPartnerState()).isEqualTo(UPDATED_TRADING_PARTNER_STATE);
        assertThat(testSecondaryClaimsSubmissionMaster.getTradingPartnerZip()).isEqualTo(UPDATED_TRADING_PARTNER_ZIP);
        assertThat(testSecondaryClaimsSubmissionMaster.getDiagnosisCodeType()).isEqualTo(UPDATED_DIAGNOSIS_CODE_TYPE);
    }

    @Test
    @Transactional
    void patchNonExistingSecondaryClaimsSubmissionMaster() throws Exception {
        int databaseSizeBeforeUpdate = secondaryClaimsSubmissionMasterRepository.findAll().size();
        secondaryClaimsSubmissionMaster.setChangeHealthSecondarySubmisionMasterId(count.incrementAndGet());

        // Create the SecondaryClaimsSubmissionMaster
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, secondaryClaimsSubmissionMasterDTO.getChangeHealthSecondarySubmisionMasterId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SecondaryClaimsSubmissionMaster in the database
        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSecondaryClaimsSubmissionMaster() throws Exception {
        int databaseSizeBeforeUpdate = secondaryClaimsSubmissionMasterRepository.findAll().size();
        secondaryClaimsSubmissionMaster.setChangeHealthSecondarySubmisionMasterId(count.incrementAndGet());

        // Create the SecondaryClaimsSubmissionMaster
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SecondaryClaimsSubmissionMaster in the database
        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSecondaryClaimsSubmissionMaster() throws Exception {
        int databaseSizeBeforeUpdate = secondaryClaimsSubmissionMasterRepository.findAll().size();
        secondaryClaimsSubmissionMaster.setChangeHealthSecondarySubmisionMasterId(count.incrementAndGet());

        // Create the SecondaryClaimsSubmissionMaster
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO = secondaryClaimsSubmissionMasterMapper.toDto(
            secondaryClaimsSubmissionMaster
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(secondaryClaimsSubmissionMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SecondaryClaimsSubmissionMaster in the database
        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSecondaryClaimsSubmissionMaster() throws Exception {
        // Initialize the database
        secondaryClaimsSubmissionMasterRepository.saveAndFlush(secondaryClaimsSubmissionMaster);

        int databaseSizeBeforeDelete = secondaryClaimsSubmissionMasterRepository.findAll().size();

        // Delete the secondaryClaimsSubmissionMaster
        restSecondaryClaimsSubmissionMasterMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, secondaryClaimsSubmissionMaster.getChangeHealthSecondarySubmisionMasterId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SecondaryClaimsSubmissionMaster> secondaryClaimsSubmissionMasterList = secondaryClaimsSubmissionMasterRepository.findAll();
        assertThat(secondaryClaimsSubmissionMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
