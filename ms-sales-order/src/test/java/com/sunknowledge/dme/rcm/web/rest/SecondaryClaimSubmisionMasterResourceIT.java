package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.SecondaryClaimSubmisionMaster;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.SecondaryClaimSubmisionMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.SecondaryClaimSubmisionMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.SecondaryClaimSubmisionMasterMapper;
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
 * Integration tests for the {@link SecondaryClaimSubmisionMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class SecondaryClaimSubmisionMasterResourceIT {

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

    private static final String DEFAULT_SECONDARY_INSURER_POLICY_NO = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY_INSURER_POLICY_NO = "BBBBBBBBBB";

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

    private static final String DEFAULT_INSERTED_BY_ID = "AAAAAAAAAA";
    private static final String UPDATED_INSERTED_BY_ID = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_INSERTED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_INSERTED_DATE = LocalDate.now(ZoneId.systemDefault());

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

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

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

    private static final UUID DEFAULT_SECONDARY_CLAIM_SUBMISION_MASTER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_SECONDARY_CLAIM_SUBMISION_MASTER_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/secondary-claim-submision-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{changeHealthSecondarySubmisionMasterId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SecondaryClaimSubmisionMasterRepository secondaryClaimSubmisionMasterRepository;

    @Autowired
    private SecondaryClaimSubmisionMasterMapper secondaryClaimSubmisionMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private SecondaryClaimSubmisionMaster secondaryClaimSubmisionMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SecondaryClaimSubmisionMaster createEntity(EntityManager em) {
        SecondaryClaimSubmisionMaster secondaryClaimSubmisionMaster = new SecondaryClaimSubmisionMaster()
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
            .secondaryInsurerPolicyNo(DEFAULT_SECONDARY_INSURER_POLICY_NO)
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
            .diagnosisCodeType(DEFAULT_DIAGNOSIS_CODE_TYPE)
            .secondaryClaimSubmisionMasterUuid(DEFAULT_SECONDARY_CLAIM_SUBMISION_MASTER_UUID);
        return secondaryClaimSubmisionMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SecondaryClaimSubmisionMaster createUpdatedEntity(EntityManager em) {
        SecondaryClaimSubmisionMaster secondaryClaimSubmisionMaster = new SecondaryClaimSubmisionMaster()
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
            .secondaryInsurerPolicyNo(UPDATED_SECONDARY_INSURER_POLICY_NO)
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
            .diagnosisCodeType(UPDATED_DIAGNOSIS_CODE_TYPE)
            .secondaryClaimSubmisionMasterUuid(UPDATED_SECONDARY_CLAIM_SUBMISION_MASTER_UUID);
        return secondaryClaimSubmisionMaster;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(SecondaryClaimSubmisionMaster.class).block();
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
        secondaryClaimSubmisionMaster = createEntity(em);
    }

    @Test
    void createSecondaryClaimSubmisionMaster() throws Exception {
        int databaseSizeBeforeCreate = secondaryClaimSubmisionMasterRepository.findAll().collectList().block().size();
        // Create the SecondaryClaimSubmisionMaster
        SecondaryClaimSubmisionMasterDTO secondaryClaimSubmisionMasterDTO = secondaryClaimSubmisionMasterMapper.toDto(
            secondaryClaimSubmisionMaster
        );
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(secondaryClaimSubmisionMasterDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the SecondaryClaimSubmisionMaster in the database
        List<SecondaryClaimSubmisionMaster> secondaryClaimSubmisionMasterList = secondaryClaimSubmisionMasterRepository
            .findAll()
            .collectList()
            .block();
        assertThat(secondaryClaimSubmisionMasterList).hasSize(databaseSizeBeforeCreate + 1);
        SecondaryClaimSubmisionMaster testSecondaryClaimSubmisionMaster = secondaryClaimSubmisionMasterList.get(
            secondaryClaimSubmisionMasterList.size() - 1
        );
        assertThat(testSecondaryClaimSubmisionMaster.getSalesOrderId()).isEqualTo(DEFAULT_SALES_ORDER_ID);
        assertThat(testSecondaryClaimSubmisionMaster.getClaimControlNo()).isEqualTo(DEFAULT_CLAIM_CONTROL_NO);
        assertThat(testSecondaryClaimSubmisionMaster.getTradingPartnerServiceId()).isEqualTo(DEFAULT_TRADING_PARTNER_SERVICE_ID);
        assertThat(testSecondaryClaimSubmisionMaster.getTradingPartnerName()).isEqualTo(DEFAULT_TRADING_PARTNER_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getSubmitterOrganizationName()).isEqualTo(DEFAULT_SUBMITTER_ORGANIZATION_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getSubmitterContactPersonName()).isEqualTo(DEFAULT_SUBMITTER_CONTACT_PERSON_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getSubmitterContactNo()).isEqualTo(DEFAULT_SUBMITTER_CONTACT_NO);
        assertThat(testSecondaryClaimSubmisionMaster.getReceiverOrganizationName()).isEqualTo(DEFAULT_RECEIVER_ORGANIZATION_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getSubscriberMemberIdNo()).isEqualTo(DEFAULT_SUBSCRIBER_MEMBER_ID_NO);
        assertThat(testSecondaryClaimSubmisionMaster.getSubscriberPaymentResponsibilityLevelCode())
            .isEqualTo(DEFAULT_SUBSCRIBER_PAYMENT_RESPONSIBILITY_LEVEL_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getSubscriberFirstName()).isEqualTo(DEFAULT_SUBSCRIBER_FIRST_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getSubscriberLastName()).isEqualTo(DEFAULT_SUBSCRIBER_LAST_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getSubscriberGender()).isEqualTo(DEFAULT_SUBSCRIBER_GENDER);
        assertThat(testSecondaryClaimSubmisionMaster.getSubscriberDob()).isEqualTo(DEFAULT_SUBSCRIBER_DOB);
        assertThat(testSecondaryClaimSubmisionMaster.getSecondaryInsurerPolicyNo()).isEqualTo(DEFAULT_SECONDARY_INSURER_POLICY_NO);
        assertThat(testSecondaryClaimSubmisionMaster.getSubscriberAddressLine1()).isEqualTo(DEFAULT_SUBSCRIBER_ADDRESS_LINE_1);
        assertThat(testSecondaryClaimSubmisionMaster.getSubscriberCity()).isEqualTo(DEFAULT_SUBSCRIBER_CITY);
        assertThat(testSecondaryClaimSubmisionMaster.getSubscriberState()).isEqualTo(DEFAULT_SUBSCRIBER_STATE);
        assertThat(testSecondaryClaimSubmisionMaster.getSubscriberZipCode()).isEqualTo(DEFAULT_SUBSCRIBER_ZIP_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getProviderType()).isEqualTo(DEFAULT_PROVIDER_TYPE);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderNpi()).isEqualTo(DEFAULT_BILLING_PROVIDER_NPI);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderEin()).isEqualTo(DEFAULT_BILLING_PROVIDER_EIN);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderOrganizationName())
            .isEqualTo(DEFAULT_BILLING_PROVIDER_ORGANIZATION_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderAddressLine1()).isEqualTo(DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_1);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderAddressLine2()).isEqualTo(DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_2);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderCity()).isEqualTo(DEFAULT_BILLING_PROVIDER_CITY);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderState()).isEqualTo(DEFAULT_BILLING_PROVIDER_STATE);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderZipCode()).isEqualTo(DEFAULT_BILLING_PROVIDER_ZIP_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderContactPersonName())
            .isEqualTo(DEFAULT_BILLING_PROVIDER_CONTACT_PERSON_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderContactNo()).isEqualTo(DEFAULT_BILLING_PROVIDER_CONTACT_NO);
        assertThat(testSecondaryClaimSubmisionMaster.getClaimFilingCode()).isEqualTo(DEFAULT_CLAIM_FILING_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getPatientAccountNo()).isEqualTo(DEFAULT_PATIENT_ACCOUNT_NO);
        assertThat(testSecondaryClaimSubmisionMaster.getClaimChargeAmount()).isEqualTo(DEFAULT_CLAIM_CHARGE_AMOUNT);
        assertThat(testSecondaryClaimSubmisionMaster.getPosCode()).isEqualTo(DEFAULT_POS_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getClaimFrequencyCode()).isEqualTo(DEFAULT_CLAIM_FREQUENCY_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getSignatureIndicator()).isEqualTo(DEFAULT_SIGNATURE_INDICATOR);
        assertThat(testSecondaryClaimSubmisionMaster.getPlanParticipationCode()).isEqualTo(DEFAULT_PLAN_PARTICIPATION_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getBenefitsAssignmentCertificationIndicator())
            .isEqualTo(DEFAULT_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR);
        assertThat(testSecondaryClaimSubmisionMaster.getReleaseInformationCode()).isEqualTo(DEFAULT_RELEASE_INFORMATION_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getPrimaryDiagnosis()).isEqualTo(DEFAULT_PRIMARY_DIAGNOSIS);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode1()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_1);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode2()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_2);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode3()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_3);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode4()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_4);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode5()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_5);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode6()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_6);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode7()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_7);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode8()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_8);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode9()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_9);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode10()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_10);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode11()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_11);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode12()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_12);
        assertThat(testSecondaryClaimSubmisionMaster.getInsertedById()).isEqualTo(DEFAULT_INSERTED_BY_ID);
        assertThat(testSecondaryClaimSubmisionMaster.getInsertedDate()).isEqualTo(DEFAULT_INSERTED_DATE);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderType()).isEqualTo(DEFAULT_BILLING_PROVIDER_TYPE);
        assertThat(testSecondaryClaimSubmisionMaster.getInsertedByName()).isEqualTo(DEFAULT_INSERTED_BY_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherSubscriberAddress1()).isEqualTo(DEFAULT_OTHER_SUBSCRIBER_ADDRESS_1);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherSubscriberAddress2()).isEqualTo(DEFAULT_OTHER_SUBSCRIBER_ADDRESS_2);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherSubscriberCity()).isEqualTo(DEFAULT_OTHER_SUBSCRIBER_CITY);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherSubscriberState()).isEqualTo(DEFAULT_OTHER_SUBSCRIBER_STATE);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherSubscriberZip()).isEqualTo(DEFAULT_OTHER_SUBSCRIBER_ZIP);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherInsuredQualifier()).isEqualTo(DEFAULT_OTHER_INSURED_QUALIFIER);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherInsuredLastName()).isEqualTo(DEFAULT_OTHER_INSURED_LAST_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherInsuredFirstName()).isEqualTo(DEFAULT_OTHER_INSURED_FIRST_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherInsuredIdentifierTypeCode())
            .isEqualTo(DEFAULT_OTHER_INSURED_IDENTIFIER_TYPE_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherInsuredIdentifier()).isEqualTo(DEFAULT_OTHER_INSURED_IDENTIFIER);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherPayerOrganizationName()).isEqualTo(DEFAULT_OTHER_PAYER_ORGANIZATION_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherPayerIdentifierTypeCode()).isEqualTo(DEFAULT_OTHER_PAYER_IDENTIFIER_TYPE_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherPayerIdentifier()).isEqualTo(DEFAULT_OTHER_PAYER_IDENTIFIER);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherPayerAdjudicationOrPaymentDate())
            .isEqualTo(DEFAULT_OTHER_PAYER_ADJUDICATION_OR_PAYMENT_DATE);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherPayerClaimAdjustmentIndicator())
            .isEqualTo(DEFAULT_OTHER_PAYER_CLAIM_ADJUSTMENT_INDICATOR);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherPayerClaimControlNumber()).isEqualTo(DEFAULT_OTHER_PAYER_CLAIM_CONTROL_NUMBER);
        assertThat(testSecondaryClaimSubmisionMaster.getPayerPaidAmount()).isEqualTo(DEFAULT_PAYER_PAID_AMOUNT);
        assertThat(testSecondaryClaimSubmisionMaster.getPaymentResponsibilityLevelCode())
            .isEqualTo(DEFAULT_PAYMENT_RESPONSIBILITY_LEVEL_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getIndividualRelationshipCode()).isEqualTo(DEFAULT_INDIVIDUAL_RELATIONSHIP_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getClaimFilingIndicatorCode()).isEqualTo(DEFAULT_CLAIM_FILING_INDICATOR_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherPayerBenefitsAssignmentCertificationIndicator())
            .isEqualTo(DEFAULT_OTHER_PAYER_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR);
        assertThat(testSecondaryClaimSubmisionMaster.getReleaseOfInformationCode()).isEqualTo(DEFAULT_RELEASE_OF_INFORMATION_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getRemainingPatientLiability()).isEqualTo(DEFAULT_REMAINING_PATIENT_LIABILITY);
        assertThat(testSecondaryClaimSubmisionMaster.getPatientSignatureGeneratedForPatient())
            .isEqualTo(DEFAULT_PATIENT_SIGNATURE_GENERATED_FOR_PATIENT);
        assertThat(testSecondaryClaimSubmisionMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testSecondaryClaimSubmisionMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testSecondaryClaimSubmisionMaster.getChangeHealthPrimarySubmisionMasterId())
            .isEqualTo(DEFAULT_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID);
        assertThat(testSecondaryClaimSubmisionMaster.getInsuredFirstName()).isEqualTo(DEFAULT_INSURED_FIRST_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getInsuredLastName()).isEqualTo(DEFAULT_INSURED_LAST_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getInsuredAddressLine1()).isEqualTo(DEFAULT_INSURED_ADDRESS_LINE_1);
        assertThat(testSecondaryClaimSubmisionMaster.getInsuredAddressLine2()).isEqualTo(DEFAULT_INSURED_ADDRESS_LINE_2);
        assertThat(testSecondaryClaimSubmisionMaster.getInsuredCity()).isEqualTo(DEFAULT_INSURED_CITY);
        assertThat(testSecondaryClaimSubmisionMaster.getInsuredState()).isEqualTo(DEFAULT_INSURED_STATE);
        assertThat(testSecondaryClaimSubmisionMaster.getInsuredZip()).isEqualTo(DEFAULT_INSURED_ZIP);
        assertThat(testSecondaryClaimSubmisionMaster.getInsuredContactNo()).isEqualTo(DEFAULT_INSURED_CONTACT_NO);
        assertThat(testSecondaryClaimSubmisionMaster.getInsuredDob()).isEqualTo(DEFAULT_INSURED_DOB);
        assertThat(testSecondaryClaimSubmisionMaster.getInsuredGender()).isEqualTo(DEFAULT_INSURED_GENDER);
        assertThat(testSecondaryClaimSubmisionMaster.getOrderingProviderFirstName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getOrderingProviderLastName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getOrderingProviderNpi()).isEqualTo(DEFAULT_ORDERING_PROVIDER_NPI);
        assertThat(testSecondaryClaimSubmisionMaster.getPatientRelationshipInsured()).isEqualTo(DEFAULT_PATIENT_RELATIONSHIP_INSURED);
        assertThat(testSecondaryClaimSubmisionMaster.getPatientConditionEmployment()).isEqualTo(DEFAULT_PATIENT_CONDITION_EMPLOYMENT);
        assertThat(testSecondaryClaimSubmisionMaster.getPatientConditionAutoAccident()).isEqualTo(DEFAULT_PATIENT_CONDITION_AUTO_ACCIDENT);
        assertThat(testSecondaryClaimSubmisionMaster.getPatientConditionOtherAccident())
            .isEqualTo(DEFAULT_PATIENT_CONDITION_OTHER_ACCIDENT);
        assertThat(testSecondaryClaimSubmisionMaster.getIsNextLevelInsurerPresentStatus())
            .isEqualTo(DEFAULT_IS_NEXT_LEVEL_INSURER_PRESENT_STATUS);
        assertThat(testSecondaryClaimSubmisionMaster.getOriginalDos()).isEqualTo(DEFAULT_ORIGINAL_DOS);
        assertThat(testSecondaryClaimSubmisionMaster.getParNo()).isEqualTo(DEFAULT_PAR_NO);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderTaxonomy()).isEqualTo(DEFAULT_BILLING_PROVIDER_TAXONOMY);
        assertThat(testSecondaryClaimSubmisionMaster.getServiceProviderNpi()).isEqualTo(DEFAULT_SERVICE_PROVIDER_NPI);
        assertThat(testSecondaryClaimSubmisionMaster.getServiceProviderOrganisationName())
            .isEqualTo(DEFAULT_SERVICE_PROVIDER_ORGANISATION_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getServiceProviderAddressLine1()).isEqualTo(DEFAULT_SERVICE_PROVIDER_ADDRESS_LINE_1);
        assertThat(testSecondaryClaimSubmisionMaster.getServiceProviderAddressLine2()).isEqualTo(DEFAULT_SERVICE_PROVIDER_ADDRESS_LINE_2);
        assertThat(testSecondaryClaimSubmisionMaster.getServiceProviderCity()).isEqualTo(DEFAULT_SERVICE_PROVIDER_CITY);
        assertThat(testSecondaryClaimSubmisionMaster.getServiceProviderState()).isEqualTo(DEFAULT_SERVICE_PROVIDER_STATE);
        assertThat(testSecondaryClaimSubmisionMaster.getServiceProviderCountry()).isEqualTo(DEFAULT_SERVICE_PROVIDER_COUNTRY);
        assertThat(testSecondaryClaimSubmisionMaster.getServiceProviderZipCode()).isEqualTo(DEFAULT_SERVICE_PROVIDER_ZIP_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getServiceProviderTaxonomy()).isEqualTo(DEFAULT_SERVICE_PROVIDER_TAXONOMY);
        assertThat(testSecondaryClaimSubmisionMaster.getCms1500FormName()).isEqualTo(DEFAULT_CMS_1500_FORM_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getTradingPartnerAddressLine1()).isEqualTo(DEFAULT_TRADING_PARTNER_ADDRESS_LINE_1);
        assertThat(testSecondaryClaimSubmisionMaster.getTradingPartnerAddressLine2()).isEqualTo(DEFAULT_TRADING_PARTNER_ADDRESS_LINE_2);
        assertThat(testSecondaryClaimSubmisionMaster.getTradingPartnerCity()).isEqualTo(DEFAULT_TRADING_PARTNER_CITY);
        assertThat(testSecondaryClaimSubmisionMaster.getTradingPartnerState()).isEqualTo(DEFAULT_TRADING_PARTNER_STATE);
        assertThat(testSecondaryClaimSubmisionMaster.getTradingPartnerZip()).isEqualTo(DEFAULT_TRADING_PARTNER_ZIP);
        assertThat(testSecondaryClaimSubmisionMaster.getDiagnosisCodeType()).isEqualTo(DEFAULT_DIAGNOSIS_CODE_TYPE);
        assertThat(testSecondaryClaimSubmisionMaster.getSecondaryClaimSubmisionMasterUuid())
            .isEqualTo(DEFAULT_SECONDARY_CLAIM_SUBMISION_MASTER_UUID);
    }

    @Test
    void createSecondaryClaimSubmisionMasterWithExistingId() throws Exception {
        // Create the SecondaryClaimSubmisionMaster with an existing ID
        secondaryClaimSubmisionMaster.setChangeHealthSecondarySubmisionMasterId(1L);
        SecondaryClaimSubmisionMasterDTO secondaryClaimSubmisionMasterDTO = secondaryClaimSubmisionMasterMapper.toDto(
            secondaryClaimSubmisionMaster
        );

        int databaseSizeBeforeCreate = secondaryClaimSubmisionMasterRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(secondaryClaimSubmisionMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SecondaryClaimSubmisionMaster in the database
        List<SecondaryClaimSubmisionMaster> secondaryClaimSubmisionMasterList = secondaryClaimSubmisionMasterRepository
            .findAll()
            .collectList()
            .block();
        assertThat(secondaryClaimSubmisionMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllSecondaryClaimSubmisionMasters() {
        // Initialize the database
        secondaryClaimSubmisionMasterRepository.save(secondaryClaimSubmisionMaster).block();

        // Get all the secondaryClaimSubmisionMasterList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=changeHealthSecondarySubmisionMasterId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].changeHealthSecondarySubmisionMasterId")
            .value(hasItem(secondaryClaimSubmisionMaster.getChangeHealthSecondarySubmisionMasterId().intValue()))
            .jsonPath("$.[*].salesOrderId")
            .value(hasItem(DEFAULT_SALES_ORDER_ID.intValue()))
            .jsonPath("$.[*].claimControlNo")
            .value(hasItem(DEFAULT_CLAIM_CONTROL_NO))
            .jsonPath("$.[*].tradingPartnerServiceId")
            .value(hasItem(DEFAULT_TRADING_PARTNER_SERVICE_ID))
            .jsonPath("$.[*].tradingPartnerName")
            .value(hasItem(DEFAULT_TRADING_PARTNER_NAME))
            .jsonPath("$.[*].submitterOrganizationName")
            .value(hasItem(DEFAULT_SUBMITTER_ORGANIZATION_NAME))
            .jsonPath("$.[*].submitterContactPersonName")
            .value(hasItem(DEFAULT_SUBMITTER_CONTACT_PERSON_NAME))
            .jsonPath("$.[*].submitterContactNo")
            .value(hasItem(DEFAULT_SUBMITTER_CONTACT_NO))
            .jsonPath("$.[*].receiverOrganizationName")
            .value(hasItem(DEFAULT_RECEIVER_ORGANIZATION_NAME))
            .jsonPath("$.[*].subscriberMemberIdNo")
            .value(hasItem(DEFAULT_SUBSCRIBER_MEMBER_ID_NO))
            .jsonPath("$.[*].subscriberPaymentResponsibilityLevelCode")
            .value(hasItem(DEFAULT_SUBSCRIBER_PAYMENT_RESPONSIBILITY_LEVEL_CODE))
            .jsonPath("$.[*].subscriberFirstName")
            .value(hasItem(DEFAULT_SUBSCRIBER_FIRST_NAME))
            .jsonPath("$.[*].subscriberLastName")
            .value(hasItem(DEFAULT_SUBSCRIBER_LAST_NAME))
            .jsonPath("$.[*].subscriberGender")
            .value(hasItem(DEFAULT_SUBSCRIBER_GENDER))
            .jsonPath("$.[*].subscriberDob")
            .value(hasItem(DEFAULT_SUBSCRIBER_DOB.toString()))
            .jsonPath("$.[*].secondaryInsurerPolicyNo")
            .value(hasItem(DEFAULT_SECONDARY_INSURER_POLICY_NO))
            .jsonPath("$.[*].subscriberAddressLine1")
            .value(hasItem(DEFAULT_SUBSCRIBER_ADDRESS_LINE_1))
            .jsonPath("$.[*].subscriberCity")
            .value(hasItem(DEFAULT_SUBSCRIBER_CITY))
            .jsonPath("$.[*].subscriberState")
            .value(hasItem(DEFAULT_SUBSCRIBER_STATE))
            .jsonPath("$.[*].subscriberZipCode")
            .value(hasItem(DEFAULT_SUBSCRIBER_ZIP_CODE))
            .jsonPath("$.[*].providerType")
            .value(hasItem(DEFAULT_PROVIDER_TYPE))
            .jsonPath("$.[*].billingProviderNpi")
            .value(hasItem(DEFAULT_BILLING_PROVIDER_NPI))
            .jsonPath("$.[*].billingProviderEin")
            .value(hasItem(DEFAULT_BILLING_PROVIDER_EIN))
            .jsonPath("$.[*].billingProviderOrganizationName")
            .value(hasItem(DEFAULT_BILLING_PROVIDER_ORGANIZATION_NAME))
            .jsonPath("$.[*].billingProviderAddressLine1")
            .value(hasItem(DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_1))
            .jsonPath("$.[*].billingProviderAddressLine2")
            .value(hasItem(DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_2))
            .jsonPath("$.[*].billingProviderCity")
            .value(hasItem(DEFAULT_BILLING_PROVIDER_CITY))
            .jsonPath("$.[*].billingProviderState")
            .value(hasItem(DEFAULT_BILLING_PROVIDER_STATE))
            .jsonPath("$.[*].billingProviderZipCode")
            .value(hasItem(DEFAULT_BILLING_PROVIDER_ZIP_CODE))
            .jsonPath("$.[*].billingProviderContactPersonName")
            .value(hasItem(DEFAULT_BILLING_PROVIDER_CONTACT_PERSON_NAME))
            .jsonPath("$.[*].billingProviderContactNo")
            .value(hasItem(DEFAULT_BILLING_PROVIDER_CONTACT_NO))
            .jsonPath("$.[*].claimFilingCode")
            .value(hasItem(DEFAULT_CLAIM_FILING_CODE))
            .jsonPath("$.[*].patientAccountNo")
            .value(hasItem(DEFAULT_PATIENT_ACCOUNT_NO))
            .jsonPath("$.[*].claimChargeAmount")
            .value(hasItem(DEFAULT_CLAIM_CHARGE_AMOUNT.doubleValue()))
            .jsonPath("$.[*].posCode")
            .value(hasItem(DEFAULT_POS_CODE))
            .jsonPath("$.[*].claimFrequencyCode")
            .value(hasItem(DEFAULT_CLAIM_FREQUENCY_CODE))
            .jsonPath("$.[*].signatureIndicator")
            .value(hasItem(DEFAULT_SIGNATURE_INDICATOR))
            .jsonPath("$.[*].planParticipationCode")
            .value(hasItem(DEFAULT_PLAN_PARTICIPATION_CODE))
            .jsonPath("$.[*].benefitsAssignmentCertificationIndicator")
            .value(hasItem(DEFAULT_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR))
            .jsonPath("$.[*].releaseInformationCode")
            .value(hasItem(DEFAULT_RELEASE_INFORMATION_CODE))
            .jsonPath("$.[*].primaryDiagnosis")
            .value(hasItem(DEFAULT_PRIMARY_DIAGNOSIS))
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
            .jsonPath("$.[*].insertedById")
            .value(hasItem(DEFAULT_INSERTED_BY_ID))
            .jsonPath("$.[*].insertedDate")
            .value(hasItem(DEFAULT_INSERTED_DATE.toString()))
            .jsonPath("$.[*].billingProviderType")
            .value(hasItem(DEFAULT_BILLING_PROVIDER_TYPE))
            .jsonPath("$.[*].insertedByName")
            .value(hasItem(DEFAULT_INSERTED_BY_NAME))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS))
            .jsonPath("$.[*].otherSubscriberAddress1")
            .value(hasItem(DEFAULT_OTHER_SUBSCRIBER_ADDRESS_1))
            .jsonPath("$.[*].otherSubscriberAddress2")
            .value(hasItem(DEFAULT_OTHER_SUBSCRIBER_ADDRESS_2))
            .jsonPath("$.[*].otherSubscriberCity")
            .value(hasItem(DEFAULT_OTHER_SUBSCRIBER_CITY))
            .jsonPath("$.[*].otherSubscriberState")
            .value(hasItem(DEFAULT_OTHER_SUBSCRIBER_STATE))
            .jsonPath("$.[*].otherSubscriberZip")
            .value(hasItem(DEFAULT_OTHER_SUBSCRIBER_ZIP))
            .jsonPath("$.[*].otherInsuredQualifier")
            .value(hasItem(DEFAULT_OTHER_INSURED_QUALIFIER))
            .jsonPath("$.[*].otherInsuredLastName")
            .value(hasItem(DEFAULT_OTHER_INSURED_LAST_NAME))
            .jsonPath("$.[*].otherInsuredFirstName")
            .value(hasItem(DEFAULT_OTHER_INSURED_FIRST_NAME))
            .jsonPath("$.[*].otherInsuredIdentifierTypeCode")
            .value(hasItem(DEFAULT_OTHER_INSURED_IDENTIFIER_TYPE_CODE))
            .jsonPath("$.[*].otherInsuredIdentifier")
            .value(hasItem(DEFAULT_OTHER_INSURED_IDENTIFIER))
            .jsonPath("$.[*].otherPayerOrganizationName")
            .value(hasItem(DEFAULT_OTHER_PAYER_ORGANIZATION_NAME))
            .jsonPath("$.[*].otherPayerIdentifierTypeCode")
            .value(hasItem(DEFAULT_OTHER_PAYER_IDENTIFIER_TYPE_CODE))
            .jsonPath("$.[*].otherPayerIdentifier")
            .value(hasItem(DEFAULT_OTHER_PAYER_IDENTIFIER))
            .jsonPath("$.[*].otherPayerAdjudicationOrPaymentDate")
            .value(hasItem(DEFAULT_OTHER_PAYER_ADJUDICATION_OR_PAYMENT_DATE.toString()))
            .jsonPath("$.[*].otherPayerClaimAdjustmentIndicator")
            .value(hasItem(DEFAULT_OTHER_PAYER_CLAIM_ADJUSTMENT_INDICATOR))
            .jsonPath("$.[*].otherPayerClaimControlNumber")
            .value(hasItem(DEFAULT_OTHER_PAYER_CLAIM_CONTROL_NUMBER))
            .jsonPath("$.[*].payerPaidAmount")
            .value(hasItem(DEFAULT_PAYER_PAID_AMOUNT.doubleValue()))
            .jsonPath("$.[*].paymentResponsibilityLevelCode")
            .value(hasItem(DEFAULT_PAYMENT_RESPONSIBILITY_LEVEL_CODE))
            .jsonPath("$.[*].individualRelationshipCode")
            .value(hasItem(DEFAULT_INDIVIDUAL_RELATIONSHIP_CODE))
            .jsonPath("$.[*].claimFilingIndicatorCode")
            .value(hasItem(DEFAULT_CLAIM_FILING_INDICATOR_CODE))
            .jsonPath("$.[*].otherPayerBenefitsAssignmentCertificationIndicator")
            .value(hasItem(DEFAULT_OTHER_PAYER_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR))
            .jsonPath("$.[*].releaseOfInformationCode")
            .value(hasItem(DEFAULT_RELEASE_OF_INFORMATION_CODE))
            .jsonPath("$.[*].remainingPatientLiability")
            .value(hasItem(DEFAULT_REMAINING_PATIENT_LIABILITY.doubleValue()))
            .jsonPath("$.[*].patientSignatureGeneratedForPatient")
            .value(hasItem(DEFAULT_PATIENT_SIGNATURE_GENERATED_FOR_PATIENT))
            .jsonPath("$.[*].updatedById")
            .value(hasItem(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.[*].updatedByName")
            .value(hasItem(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.[*].updatedDate")
            .value(hasItem(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.[*].changeHealthPrimarySubmisionMasterId")
            .value(hasItem(DEFAULT_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID.intValue()))
            .jsonPath("$.[*].insuredFirstName")
            .value(hasItem(DEFAULT_INSURED_FIRST_NAME))
            .jsonPath("$.[*].insuredLastName")
            .value(hasItem(DEFAULT_INSURED_LAST_NAME))
            .jsonPath("$.[*].insuredAddressLine1")
            .value(hasItem(DEFAULT_INSURED_ADDRESS_LINE_1))
            .jsonPath("$.[*].insuredAddressLine2")
            .value(hasItem(DEFAULT_INSURED_ADDRESS_LINE_2))
            .jsonPath("$.[*].insuredCity")
            .value(hasItem(DEFAULT_INSURED_CITY))
            .jsonPath("$.[*].insuredState")
            .value(hasItem(DEFAULT_INSURED_STATE))
            .jsonPath("$.[*].insuredZip")
            .value(hasItem(DEFAULT_INSURED_ZIP))
            .jsonPath("$.[*].insuredContactNo")
            .value(hasItem(DEFAULT_INSURED_CONTACT_NO))
            .jsonPath("$.[*].insuredDob")
            .value(hasItem(DEFAULT_INSURED_DOB.toString()))
            .jsonPath("$.[*].insuredGender")
            .value(hasItem(DEFAULT_INSURED_GENDER))
            .jsonPath("$.[*].orderingProviderFirstName")
            .value(hasItem(DEFAULT_ORDERING_PROVIDER_FIRST_NAME))
            .jsonPath("$.[*].orderingProviderLastName")
            .value(hasItem(DEFAULT_ORDERING_PROVIDER_LAST_NAME))
            .jsonPath("$.[*].orderingProviderNpi")
            .value(hasItem(DEFAULT_ORDERING_PROVIDER_NPI))
            .jsonPath("$.[*].patientRelationshipInsured")
            .value(hasItem(DEFAULT_PATIENT_RELATIONSHIP_INSURED))
            .jsonPath("$.[*].patientConditionEmployment")
            .value(hasItem(DEFAULT_PATIENT_CONDITION_EMPLOYMENT))
            .jsonPath("$.[*].patientConditionAutoAccident")
            .value(hasItem(DEFAULT_PATIENT_CONDITION_AUTO_ACCIDENT))
            .jsonPath("$.[*].patientConditionOtherAccident")
            .value(hasItem(DEFAULT_PATIENT_CONDITION_OTHER_ACCIDENT))
            .jsonPath("$.[*].isNextLevelInsurerPresentStatus")
            .value(hasItem(DEFAULT_IS_NEXT_LEVEL_INSURER_PRESENT_STATUS))
            .jsonPath("$.[*].originalDos")
            .value(hasItem(DEFAULT_ORIGINAL_DOS.toString()))
            .jsonPath("$.[*].parNo")
            .value(hasItem(DEFAULT_PAR_NO))
            .jsonPath("$.[*].billingProviderTaxonomy")
            .value(hasItem(DEFAULT_BILLING_PROVIDER_TAXONOMY))
            .jsonPath("$.[*].serviceProviderNpi")
            .value(hasItem(DEFAULT_SERVICE_PROVIDER_NPI))
            .jsonPath("$.[*].serviceProviderOrganisationName")
            .value(hasItem(DEFAULT_SERVICE_PROVIDER_ORGANISATION_NAME))
            .jsonPath("$.[*].serviceProviderAddressLine1")
            .value(hasItem(DEFAULT_SERVICE_PROVIDER_ADDRESS_LINE_1))
            .jsonPath("$.[*].serviceProviderAddressLine2")
            .value(hasItem(DEFAULT_SERVICE_PROVIDER_ADDRESS_LINE_2))
            .jsonPath("$.[*].serviceProviderCity")
            .value(hasItem(DEFAULT_SERVICE_PROVIDER_CITY))
            .jsonPath("$.[*].serviceProviderState")
            .value(hasItem(DEFAULT_SERVICE_PROVIDER_STATE))
            .jsonPath("$.[*].serviceProviderCountry")
            .value(hasItem(DEFAULT_SERVICE_PROVIDER_COUNTRY))
            .jsonPath("$.[*].serviceProviderZipCode")
            .value(hasItem(DEFAULT_SERVICE_PROVIDER_ZIP_CODE))
            .jsonPath("$.[*].serviceProviderTaxonomy")
            .value(hasItem(DEFAULT_SERVICE_PROVIDER_TAXONOMY))
            .jsonPath("$.[*].cms1500FormName")
            .value(hasItem(DEFAULT_CMS_1500_FORM_NAME))
            .jsonPath("$.[*].tradingPartnerAddressLine1")
            .value(hasItem(DEFAULT_TRADING_PARTNER_ADDRESS_LINE_1))
            .jsonPath("$.[*].tradingPartnerAddressLine2")
            .value(hasItem(DEFAULT_TRADING_PARTNER_ADDRESS_LINE_2))
            .jsonPath("$.[*].tradingPartnerCity")
            .value(hasItem(DEFAULT_TRADING_PARTNER_CITY))
            .jsonPath("$.[*].tradingPartnerState")
            .value(hasItem(DEFAULT_TRADING_PARTNER_STATE))
            .jsonPath("$.[*].tradingPartnerZip")
            .value(hasItem(DEFAULT_TRADING_PARTNER_ZIP))
            .jsonPath("$.[*].diagnosisCodeType")
            .value(hasItem(DEFAULT_DIAGNOSIS_CODE_TYPE))
            .jsonPath("$.[*].secondaryClaimSubmisionMasterUuid")
            .value(hasItem(DEFAULT_SECONDARY_CLAIM_SUBMISION_MASTER_UUID.toString()));
    }

    @Test
    void getSecondaryClaimSubmisionMaster() {
        // Initialize the database
        secondaryClaimSubmisionMasterRepository.save(secondaryClaimSubmisionMaster).block();

        // Get the secondaryClaimSubmisionMaster
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, secondaryClaimSubmisionMaster.getChangeHealthSecondarySubmisionMasterId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.changeHealthSecondarySubmisionMasterId")
            .value(is(secondaryClaimSubmisionMaster.getChangeHealthSecondarySubmisionMasterId().intValue()))
            .jsonPath("$.salesOrderId")
            .value(is(DEFAULT_SALES_ORDER_ID.intValue()))
            .jsonPath("$.claimControlNo")
            .value(is(DEFAULT_CLAIM_CONTROL_NO))
            .jsonPath("$.tradingPartnerServiceId")
            .value(is(DEFAULT_TRADING_PARTNER_SERVICE_ID))
            .jsonPath("$.tradingPartnerName")
            .value(is(DEFAULT_TRADING_PARTNER_NAME))
            .jsonPath("$.submitterOrganizationName")
            .value(is(DEFAULT_SUBMITTER_ORGANIZATION_NAME))
            .jsonPath("$.submitterContactPersonName")
            .value(is(DEFAULT_SUBMITTER_CONTACT_PERSON_NAME))
            .jsonPath("$.submitterContactNo")
            .value(is(DEFAULT_SUBMITTER_CONTACT_NO))
            .jsonPath("$.receiverOrganizationName")
            .value(is(DEFAULT_RECEIVER_ORGANIZATION_NAME))
            .jsonPath("$.subscriberMemberIdNo")
            .value(is(DEFAULT_SUBSCRIBER_MEMBER_ID_NO))
            .jsonPath("$.subscriberPaymentResponsibilityLevelCode")
            .value(is(DEFAULT_SUBSCRIBER_PAYMENT_RESPONSIBILITY_LEVEL_CODE))
            .jsonPath("$.subscriberFirstName")
            .value(is(DEFAULT_SUBSCRIBER_FIRST_NAME))
            .jsonPath("$.subscriberLastName")
            .value(is(DEFAULT_SUBSCRIBER_LAST_NAME))
            .jsonPath("$.subscriberGender")
            .value(is(DEFAULT_SUBSCRIBER_GENDER))
            .jsonPath("$.subscriberDob")
            .value(is(DEFAULT_SUBSCRIBER_DOB.toString()))
            .jsonPath("$.secondaryInsurerPolicyNo")
            .value(is(DEFAULT_SECONDARY_INSURER_POLICY_NO))
            .jsonPath("$.subscriberAddressLine1")
            .value(is(DEFAULT_SUBSCRIBER_ADDRESS_LINE_1))
            .jsonPath("$.subscriberCity")
            .value(is(DEFAULT_SUBSCRIBER_CITY))
            .jsonPath("$.subscriberState")
            .value(is(DEFAULT_SUBSCRIBER_STATE))
            .jsonPath("$.subscriberZipCode")
            .value(is(DEFAULT_SUBSCRIBER_ZIP_CODE))
            .jsonPath("$.providerType")
            .value(is(DEFAULT_PROVIDER_TYPE))
            .jsonPath("$.billingProviderNpi")
            .value(is(DEFAULT_BILLING_PROVIDER_NPI))
            .jsonPath("$.billingProviderEin")
            .value(is(DEFAULT_BILLING_PROVIDER_EIN))
            .jsonPath("$.billingProviderOrganizationName")
            .value(is(DEFAULT_BILLING_PROVIDER_ORGANIZATION_NAME))
            .jsonPath("$.billingProviderAddressLine1")
            .value(is(DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_1))
            .jsonPath("$.billingProviderAddressLine2")
            .value(is(DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_2))
            .jsonPath("$.billingProviderCity")
            .value(is(DEFAULT_BILLING_PROVIDER_CITY))
            .jsonPath("$.billingProviderState")
            .value(is(DEFAULT_BILLING_PROVIDER_STATE))
            .jsonPath("$.billingProviderZipCode")
            .value(is(DEFAULT_BILLING_PROVIDER_ZIP_CODE))
            .jsonPath("$.billingProviderContactPersonName")
            .value(is(DEFAULT_BILLING_PROVIDER_CONTACT_PERSON_NAME))
            .jsonPath("$.billingProviderContactNo")
            .value(is(DEFAULT_BILLING_PROVIDER_CONTACT_NO))
            .jsonPath("$.claimFilingCode")
            .value(is(DEFAULT_CLAIM_FILING_CODE))
            .jsonPath("$.patientAccountNo")
            .value(is(DEFAULT_PATIENT_ACCOUNT_NO))
            .jsonPath("$.claimChargeAmount")
            .value(is(DEFAULT_CLAIM_CHARGE_AMOUNT.doubleValue()))
            .jsonPath("$.posCode")
            .value(is(DEFAULT_POS_CODE))
            .jsonPath("$.claimFrequencyCode")
            .value(is(DEFAULT_CLAIM_FREQUENCY_CODE))
            .jsonPath("$.signatureIndicator")
            .value(is(DEFAULT_SIGNATURE_INDICATOR))
            .jsonPath("$.planParticipationCode")
            .value(is(DEFAULT_PLAN_PARTICIPATION_CODE))
            .jsonPath("$.benefitsAssignmentCertificationIndicator")
            .value(is(DEFAULT_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR))
            .jsonPath("$.releaseInformationCode")
            .value(is(DEFAULT_RELEASE_INFORMATION_CODE))
            .jsonPath("$.primaryDiagnosis")
            .value(is(DEFAULT_PRIMARY_DIAGNOSIS))
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
            .jsonPath("$.insertedById")
            .value(is(DEFAULT_INSERTED_BY_ID))
            .jsonPath("$.insertedDate")
            .value(is(DEFAULT_INSERTED_DATE.toString()))
            .jsonPath("$.billingProviderType")
            .value(is(DEFAULT_BILLING_PROVIDER_TYPE))
            .jsonPath("$.insertedByName")
            .value(is(DEFAULT_INSERTED_BY_NAME))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS))
            .jsonPath("$.otherSubscriberAddress1")
            .value(is(DEFAULT_OTHER_SUBSCRIBER_ADDRESS_1))
            .jsonPath("$.otherSubscriberAddress2")
            .value(is(DEFAULT_OTHER_SUBSCRIBER_ADDRESS_2))
            .jsonPath("$.otherSubscriberCity")
            .value(is(DEFAULT_OTHER_SUBSCRIBER_CITY))
            .jsonPath("$.otherSubscriberState")
            .value(is(DEFAULT_OTHER_SUBSCRIBER_STATE))
            .jsonPath("$.otherSubscriberZip")
            .value(is(DEFAULT_OTHER_SUBSCRIBER_ZIP))
            .jsonPath("$.otherInsuredQualifier")
            .value(is(DEFAULT_OTHER_INSURED_QUALIFIER))
            .jsonPath("$.otherInsuredLastName")
            .value(is(DEFAULT_OTHER_INSURED_LAST_NAME))
            .jsonPath("$.otherInsuredFirstName")
            .value(is(DEFAULT_OTHER_INSURED_FIRST_NAME))
            .jsonPath("$.otherInsuredIdentifierTypeCode")
            .value(is(DEFAULT_OTHER_INSURED_IDENTIFIER_TYPE_CODE))
            .jsonPath("$.otherInsuredIdentifier")
            .value(is(DEFAULT_OTHER_INSURED_IDENTIFIER))
            .jsonPath("$.otherPayerOrganizationName")
            .value(is(DEFAULT_OTHER_PAYER_ORGANIZATION_NAME))
            .jsonPath("$.otherPayerIdentifierTypeCode")
            .value(is(DEFAULT_OTHER_PAYER_IDENTIFIER_TYPE_CODE))
            .jsonPath("$.otherPayerIdentifier")
            .value(is(DEFAULT_OTHER_PAYER_IDENTIFIER))
            .jsonPath("$.otherPayerAdjudicationOrPaymentDate")
            .value(is(DEFAULT_OTHER_PAYER_ADJUDICATION_OR_PAYMENT_DATE.toString()))
            .jsonPath("$.otherPayerClaimAdjustmentIndicator")
            .value(is(DEFAULT_OTHER_PAYER_CLAIM_ADJUSTMENT_INDICATOR))
            .jsonPath("$.otherPayerClaimControlNumber")
            .value(is(DEFAULT_OTHER_PAYER_CLAIM_CONTROL_NUMBER))
            .jsonPath("$.payerPaidAmount")
            .value(is(DEFAULT_PAYER_PAID_AMOUNT.doubleValue()))
            .jsonPath("$.paymentResponsibilityLevelCode")
            .value(is(DEFAULT_PAYMENT_RESPONSIBILITY_LEVEL_CODE))
            .jsonPath("$.individualRelationshipCode")
            .value(is(DEFAULT_INDIVIDUAL_RELATIONSHIP_CODE))
            .jsonPath("$.claimFilingIndicatorCode")
            .value(is(DEFAULT_CLAIM_FILING_INDICATOR_CODE))
            .jsonPath("$.otherPayerBenefitsAssignmentCertificationIndicator")
            .value(is(DEFAULT_OTHER_PAYER_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR))
            .jsonPath("$.releaseOfInformationCode")
            .value(is(DEFAULT_RELEASE_OF_INFORMATION_CODE))
            .jsonPath("$.remainingPatientLiability")
            .value(is(DEFAULT_REMAINING_PATIENT_LIABILITY.doubleValue()))
            .jsonPath("$.patientSignatureGeneratedForPatient")
            .value(is(DEFAULT_PATIENT_SIGNATURE_GENERATED_FOR_PATIENT))
            .jsonPath("$.updatedById")
            .value(is(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.updatedByName")
            .value(is(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.updatedDate")
            .value(is(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.changeHealthPrimarySubmisionMasterId")
            .value(is(DEFAULT_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID.intValue()))
            .jsonPath("$.insuredFirstName")
            .value(is(DEFAULT_INSURED_FIRST_NAME))
            .jsonPath("$.insuredLastName")
            .value(is(DEFAULT_INSURED_LAST_NAME))
            .jsonPath("$.insuredAddressLine1")
            .value(is(DEFAULT_INSURED_ADDRESS_LINE_1))
            .jsonPath("$.insuredAddressLine2")
            .value(is(DEFAULT_INSURED_ADDRESS_LINE_2))
            .jsonPath("$.insuredCity")
            .value(is(DEFAULT_INSURED_CITY))
            .jsonPath("$.insuredState")
            .value(is(DEFAULT_INSURED_STATE))
            .jsonPath("$.insuredZip")
            .value(is(DEFAULT_INSURED_ZIP))
            .jsonPath("$.insuredContactNo")
            .value(is(DEFAULT_INSURED_CONTACT_NO))
            .jsonPath("$.insuredDob")
            .value(is(DEFAULT_INSURED_DOB.toString()))
            .jsonPath("$.insuredGender")
            .value(is(DEFAULT_INSURED_GENDER))
            .jsonPath("$.orderingProviderFirstName")
            .value(is(DEFAULT_ORDERING_PROVIDER_FIRST_NAME))
            .jsonPath("$.orderingProviderLastName")
            .value(is(DEFAULT_ORDERING_PROVIDER_LAST_NAME))
            .jsonPath("$.orderingProviderNpi")
            .value(is(DEFAULT_ORDERING_PROVIDER_NPI))
            .jsonPath("$.patientRelationshipInsured")
            .value(is(DEFAULT_PATIENT_RELATIONSHIP_INSURED))
            .jsonPath("$.patientConditionEmployment")
            .value(is(DEFAULT_PATIENT_CONDITION_EMPLOYMENT))
            .jsonPath("$.patientConditionAutoAccident")
            .value(is(DEFAULT_PATIENT_CONDITION_AUTO_ACCIDENT))
            .jsonPath("$.patientConditionOtherAccident")
            .value(is(DEFAULT_PATIENT_CONDITION_OTHER_ACCIDENT))
            .jsonPath("$.isNextLevelInsurerPresentStatus")
            .value(is(DEFAULT_IS_NEXT_LEVEL_INSURER_PRESENT_STATUS))
            .jsonPath("$.originalDos")
            .value(is(DEFAULT_ORIGINAL_DOS.toString()))
            .jsonPath("$.parNo")
            .value(is(DEFAULT_PAR_NO))
            .jsonPath("$.billingProviderTaxonomy")
            .value(is(DEFAULT_BILLING_PROVIDER_TAXONOMY))
            .jsonPath("$.serviceProviderNpi")
            .value(is(DEFAULT_SERVICE_PROVIDER_NPI))
            .jsonPath("$.serviceProviderOrganisationName")
            .value(is(DEFAULT_SERVICE_PROVIDER_ORGANISATION_NAME))
            .jsonPath("$.serviceProviderAddressLine1")
            .value(is(DEFAULT_SERVICE_PROVIDER_ADDRESS_LINE_1))
            .jsonPath("$.serviceProviderAddressLine2")
            .value(is(DEFAULT_SERVICE_PROVIDER_ADDRESS_LINE_2))
            .jsonPath("$.serviceProviderCity")
            .value(is(DEFAULT_SERVICE_PROVIDER_CITY))
            .jsonPath("$.serviceProviderState")
            .value(is(DEFAULT_SERVICE_PROVIDER_STATE))
            .jsonPath("$.serviceProviderCountry")
            .value(is(DEFAULT_SERVICE_PROVIDER_COUNTRY))
            .jsonPath("$.serviceProviderZipCode")
            .value(is(DEFAULT_SERVICE_PROVIDER_ZIP_CODE))
            .jsonPath("$.serviceProviderTaxonomy")
            .value(is(DEFAULT_SERVICE_PROVIDER_TAXONOMY))
            .jsonPath("$.cms1500FormName")
            .value(is(DEFAULT_CMS_1500_FORM_NAME))
            .jsonPath("$.tradingPartnerAddressLine1")
            .value(is(DEFAULT_TRADING_PARTNER_ADDRESS_LINE_1))
            .jsonPath("$.tradingPartnerAddressLine2")
            .value(is(DEFAULT_TRADING_PARTNER_ADDRESS_LINE_2))
            .jsonPath("$.tradingPartnerCity")
            .value(is(DEFAULT_TRADING_PARTNER_CITY))
            .jsonPath("$.tradingPartnerState")
            .value(is(DEFAULT_TRADING_PARTNER_STATE))
            .jsonPath("$.tradingPartnerZip")
            .value(is(DEFAULT_TRADING_PARTNER_ZIP))
            .jsonPath("$.diagnosisCodeType")
            .value(is(DEFAULT_DIAGNOSIS_CODE_TYPE))
            .jsonPath("$.secondaryClaimSubmisionMasterUuid")
            .value(is(DEFAULT_SECONDARY_CLAIM_SUBMISION_MASTER_UUID.toString()));
    }

    @Test
    void getNonExistingSecondaryClaimSubmisionMaster() {
        // Get the secondaryClaimSubmisionMaster
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putNewSecondaryClaimSubmisionMaster() throws Exception {
        // Initialize the database
        secondaryClaimSubmisionMasterRepository.save(secondaryClaimSubmisionMaster).block();

        int databaseSizeBeforeUpdate = secondaryClaimSubmisionMasterRepository.findAll().collectList().block().size();

        // Update the secondaryClaimSubmisionMaster
        SecondaryClaimSubmisionMaster updatedSecondaryClaimSubmisionMaster = secondaryClaimSubmisionMasterRepository
            .findById(secondaryClaimSubmisionMaster.getChangeHealthSecondarySubmisionMasterId())
            .block();
        updatedSecondaryClaimSubmisionMaster
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
            .secondaryInsurerPolicyNo(UPDATED_SECONDARY_INSURER_POLICY_NO)
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
            .diagnosisCodeType(UPDATED_DIAGNOSIS_CODE_TYPE)
            .secondaryClaimSubmisionMasterUuid(UPDATED_SECONDARY_CLAIM_SUBMISION_MASTER_UUID);
        SecondaryClaimSubmisionMasterDTO secondaryClaimSubmisionMasterDTO = secondaryClaimSubmisionMasterMapper.toDto(
            updatedSecondaryClaimSubmisionMaster
        );

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, secondaryClaimSubmisionMasterDTO.getChangeHealthSecondarySubmisionMasterId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(secondaryClaimSubmisionMasterDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SecondaryClaimSubmisionMaster in the database
        List<SecondaryClaimSubmisionMaster> secondaryClaimSubmisionMasterList = secondaryClaimSubmisionMasterRepository
            .findAll()
            .collectList()
            .block();
        assertThat(secondaryClaimSubmisionMasterList).hasSize(databaseSizeBeforeUpdate);
        SecondaryClaimSubmisionMaster testSecondaryClaimSubmisionMaster = secondaryClaimSubmisionMasterList.get(
            secondaryClaimSubmisionMasterList.size() - 1
        );
        assertThat(testSecondaryClaimSubmisionMaster.getSalesOrderId()).isEqualTo(UPDATED_SALES_ORDER_ID);
        assertThat(testSecondaryClaimSubmisionMaster.getClaimControlNo()).isEqualTo(UPDATED_CLAIM_CONTROL_NO);
        assertThat(testSecondaryClaimSubmisionMaster.getTradingPartnerServiceId()).isEqualTo(UPDATED_TRADING_PARTNER_SERVICE_ID);
        assertThat(testSecondaryClaimSubmisionMaster.getTradingPartnerName()).isEqualTo(UPDATED_TRADING_PARTNER_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getSubmitterOrganizationName()).isEqualTo(UPDATED_SUBMITTER_ORGANIZATION_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getSubmitterContactPersonName()).isEqualTo(UPDATED_SUBMITTER_CONTACT_PERSON_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getSubmitterContactNo()).isEqualTo(UPDATED_SUBMITTER_CONTACT_NO);
        assertThat(testSecondaryClaimSubmisionMaster.getReceiverOrganizationName()).isEqualTo(UPDATED_RECEIVER_ORGANIZATION_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getSubscriberMemberIdNo()).isEqualTo(UPDATED_SUBSCRIBER_MEMBER_ID_NO);
        assertThat(testSecondaryClaimSubmisionMaster.getSubscriberPaymentResponsibilityLevelCode())
            .isEqualTo(UPDATED_SUBSCRIBER_PAYMENT_RESPONSIBILITY_LEVEL_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getSubscriberFirstName()).isEqualTo(UPDATED_SUBSCRIBER_FIRST_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getSubscriberLastName()).isEqualTo(UPDATED_SUBSCRIBER_LAST_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getSubscriberGender()).isEqualTo(UPDATED_SUBSCRIBER_GENDER);
        assertThat(testSecondaryClaimSubmisionMaster.getSubscriberDob()).isEqualTo(UPDATED_SUBSCRIBER_DOB);
        assertThat(testSecondaryClaimSubmisionMaster.getSecondaryInsurerPolicyNo()).isEqualTo(UPDATED_SECONDARY_INSURER_POLICY_NO);
        assertThat(testSecondaryClaimSubmisionMaster.getSubscriberAddressLine1()).isEqualTo(UPDATED_SUBSCRIBER_ADDRESS_LINE_1);
        assertThat(testSecondaryClaimSubmisionMaster.getSubscriberCity()).isEqualTo(UPDATED_SUBSCRIBER_CITY);
        assertThat(testSecondaryClaimSubmisionMaster.getSubscriberState()).isEqualTo(UPDATED_SUBSCRIBER_STATE);
        assertThat(testSecondaryClaimSubmisionMaster.getSubscriberZipCode()).isEqualTo(UPDATED_SUBSCRIBER_ZIP_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getProviderType()).isEqualTo(UPDATED_PROVIDER_TYPE);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderNpi()).isEqualTo(UPDATED_BILLING_PROVIDER_NPI);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderEin()).isEqualTo(UPDATED_BILLING_PROVIDER_EIN);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderOrganizationName())
            .isEqualTo(UPDATED_BILLING_PROVIDER_ORGANIZATION_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderAddressLine1()).isEqualTo(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_1);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderAddressLine2()).isEqualTo(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_2);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderCity()).isEqualTo(UPDATED_BILLING_PROVIDER_CITY);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderState()).isEqualTo(UPDATED_BILLING_PROVIDER_STATE);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderZipCode()).isEqualTo(UPDATED_BILLING_PROVIDER_ZIP_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderContactPersonName())
            .isEqualTo(UPDATED_BILLING_PROVIDER_CONTACT_PERSON_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderContactNo()).isEqualTo(UPDATED_BILLING_PROVIDER_CONTACT_NO);
        assertThat(testSecondaryClaimSubmisionMaster.getClaimFilingCode()).isEqualTo(UPDATED_CLAIM_FILING_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getPatientAccountNo()).isEqualTo(UPDATED_PATIENT_ACCOUNT_NO);
        assertThat(testSecondaryClaimSubmisionMaster.getClaimChargeAmount()).isEqualTo(UPDATED_CLAIM_CHARGE_AMOUNT);
        assertThat(testSecondaryClaimSubmisionMaster.getPosCode()).isEqualTo(UPDATED_POS_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getClaimFrequencyCode()).isEqualTo(UPDATED_CLAIM_FREQUENCY_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getSignatureIndicator()).isEqualTo(UPDATED_SIGNATURE_INDICATOR);
        assertThat(testSecondaryClaimSubmisionMaster.getPlanParticipationCode()).isEqualTo(UPDATED_PLAN_PARTICIPATION_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getBenefitsAssignmentCertificationIndicator())
            .isEqualTo(UPDATED_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR);
        assertThat(testSecondaryClaimSubmisionMaster.getReleaseInformationCode()).isEqualTo(UPDATED_RELEASE_INFORMATION_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getPrimaryDiagnosis()).isEqualTo(UPDATED_PRIMARY_DIAGNOSIS);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode1()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_1);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode2()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_2);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode3()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_3);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode4()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_4);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode5()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_5);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode6()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_6);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode7()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_7);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode8()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_8);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode9()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_9);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode10()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_10);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode11()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_11);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode12()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_12);
        assertThat(testSecondaryClaimSubmisionMaster.getInsertedById()).isEqualTo(UPDATED_INSERTED_BY_ID);
        assertThat(testSecondaryClaimSubmisionMaster.getInsertedDate()).isEqualTo(UPDATED_INSERTED_DATE);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderType()).isEqualTo(UPDATED_BILLING_PROVIDER_TYPE);
        assertThat(testSecondaryClaimSubmisionMaster.getInsertedByName()).isEqualTo(UPDATED_INSERTED_BY_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherSubscriberAddress1()).isEqualTo(UPDATED_OTHER_SUBSCRIBER_ADDRESS_1);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherSubscriberAddress2()).isEqualTo(UPDATED_OTHER_SUBSCRIBER_ADDRESS_2);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherSubscriberCity()).isEqualTo(UPDATED_OTHER_SUBSCRIBER_CITY);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherSubscriberState()).isEqualTo(UPDATED_OTHER_SUBSCRIBER_STATE);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherSubscriberZip()).isEqualTo(UPDATED_OTHER_SUBSCRIBER_ZIP);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherInsuredQualifier()).isEqualTo(UPDATED_OTHER_INSURED_QUALIFIER);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherInsuredLastName()).isEqualTo(UPDATED_OTHER_INSURED_LAST_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherInsuredFirstName()).isEqualTo(UPDATED_OTHER_INSURED_FIRST_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherInsuredIdentifierTypeCode())
            .isEqualTo(UPDATED_OTHER_INSURED_IDENTIFIER_TYPE_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherInsuredIdentifier()).isEqualTo(UPDATED_OTHER_INSURED_IDENTIFIER);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherPayerOrganizationName()).isEqualTo(UPDATED_OTHER_PAYER_ORGANIZATION_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherPayerIdentifierTypeCode()).isEqualTo(UPDATED_OTHER_PAYER_IDENTIFIER_TYPE_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherPayerIdentifier()).isEqualTo(UPDATED_OTHER_PAYER_IDENTIFIER);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherPayerAdjudicationOrPaymentDate())
            .isEqualTo(UPDATED_OTHER_PAYER_ADJUDICATION_OR_PAYMENT_DATE);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherPayerClaimAdjustmentIndicator())
            .isEqualTo(UPDATED_OTHER_PAYER_CLAIM_ADJUSTMENT_INDICATOR);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherPayerClaimControlNumber()).isEqualTo(UPDATED_OTHER_PAYER_CLAIM_CONTROL_NUMBER);
        assertThat(testSecondaryClaimSubmisionMaster.getPayerPaidAmount()).isEqualTo(UPDATED_PAYER_PAID_AMOUNT);
        assertThat(testSecondaryClaimSubmisionMaster.getPaymentResponsibilityLevelCode())
            .isEqualTo(UPDATED_PAYMENT_RESPONSIBILITY_LEVEL_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getIndividualRelationshipCode()).isEqualTo(UPDATED_INDIVIDUAL_RELATIONSHIP_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getClaimFilingIndicatorCode()).isEqualTo(UPDATED_CLAIM_FILING_INDICATOR_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherPayerBenefitsAssignmentCertificationIndicator())
            .isEqualTo(UPDATED_OTHER_PAYER_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR);
        assertThat(testSecondaryClaimSubmisionMaster.getReleaseOfInformationCode()).isEqualTo(UPDATED_RELEASE_OF_INFORMATION_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getRemainingPatientLiability()).isEqualTo(UPDATED_REMAINING_PATIENT_LIABILITY);
        assertThat(testSecondaryClaimSubmisionMaster.getPatientSignatureGeneratedForPatient())
            .isEqualTo(UPDATED_PATIENT_SIGNATURE_GENERATED_FOR_PATIENT);
        assertThat(testSecondaryClaimSubmisionMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testSecondaryClaimSubmisionMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testSecondaryClaimSubmisionMaster.getChangeHealthPrimarySubmisionMasterId())
            .isEqualTo(UPDATED_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID);
        assertThat(testSecondaryClaimSubmisionMaster.getInsuredFirstName()).isEqualTo(UPDATED_INSURED_FIRST_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getInsuredLastName()).isEqualTo(UPDATED_INSURED_LAST_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getInsuredAddressLine1()).isEqualTo(UPDATED_INSURED_ADDRESS_LINE_1);
        assertThat(testSecondaryClaimSubmisionMaster.getInsuredAddressLine2()).isEqualTo(UPDATED_INSURED_ADDRESS_LINE_2);
        assertThat(testSecondaryClaimSubmisionMaster.getInsuredCity()).isEqualTo(UPDATED_INSURED_CITY);
        assertThat(testSecondaryClaimSubmisionMaster.getInsuredState()).isEqualTo(UPDATED_INSURED_STATE);
        assertThat(testSecondaryClaimSubmisionMaster.getInsuredZip()).isEqualTo(UPDATED_INSURED_ZIP);
        assertThat(testSecondaryClaimSubmisionMaster.getInsuredContactNo()).isEqualTo(UPDATED_INSURED_CONTACT_NO);
        assertThat(testSecondaryClaimSubmisionMaster.getInsuredDob()).isEqualTo(UPDATED_INSURED_DOB);
        assertThat(testSecondaryClaimSubmisionMaster.getInsuredGender()).isEqualTo(UPDATED_INSURED_GENDER);
        assertThat(testSecondaryClaimSubmisionMaster.getOrderingProviderFirstName()).isEqualTo(UPDATED_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getOrderingProviderLastName()).isEqualTo(UPDATED_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getOrderingProviderNpi()).isEqualTo(UPDATED_ORDERING_PROVIDER_NPI);
        assertThat(testSecondaryClaimSubmisionMaster.getPatientRelationshipInsured()).isEqualTo(UPDATED_PATIENT_RELATIONSHIP_INSURED);
        assertThat(testSecondaryClaimSubmisionMaster.getPatientConditionEmployment()).isEqualTo(UPDATED_PATIENT_CONDITION_EMPLOYMENT);
        assertThat(testSecondaryClaimSubmisionMaster.getPatientConditionAutoAccident()).isEqualTo(UPDATED_PATIENT_CONDITION_AUTO_ACCIDENT);
        assertThat(testSecondaryClaimSubmisionMaster.getPatientConditionOtherAccident())
            .isEqualTo(UPDATED_PATIENT_CONDITION_OTHER_ACCIDENT);
        assertThat(testSecondaryClaimSubmisionMaster.getIsNextLevelInsurerPresentStatus())
            .isEqualTo(UPDATED_IS_NEXT_LEVEL_INSURER_PRESENT_STATUS);
        assertThat(testSecondaryClaimSubmisionMaster.getOriginalDos()).isEqualTo(UPDATED_ORIGINAL_DOS);
        assertThat(testSecondaryClaimSubmisionMaster.getParNo()).isEqualTo(UPDATED_PAR_NO);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderTaxonomy()).isEqualTo(UPDATED_BILLING_PROVIDER_TAXONOMY);
        assertThat(testSecondaryClaimSubmisionMaster.getServiceProviderNpi()).isEqualTo(UPDATED_SERVICE_PROVIDER_NPI);
        assertThat(testSecondaryClaimSubmisionMaster.getServiceProviderOrganisationName())
            .isEqualTo(UPDATED_SERVICE_PROVIDER_ORGANISATION_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getServiceProviderAddressLine1()).isEqualTo(UPDATED_SERVICE_PROVIDER_ADDRESS_LINE_1);
        assertThat(testSecondaryClaimSubmisionMaster.getServiceProviderAddressLine2()).isEqualTo(UPDATED_SERVICE_PROVIDER_ADDRESS_LINE_2);
        assertThat(testSecondaryClaimSubmisionMaster.getServiceProviderCity()).isEqualTo(UPDATED_SERVICE_PROVIDER_CITY);
        assertThat(testSecondaryClaimSubmisionMaster.getServiceProviderState()).isEqualTo(UPDATED_SERVICE_PROVIDER_STATE);
        assertThat(testSecondaryClaimSubmisionMaster.getServiceProviderCountry()).isEqualTo(UPDATED_SERVICE_PROVIDER_COUNTRY);
        assertThat(testSecondaryClaimSubmisionMaster.getServiceProviderZipCode()).isEqualTo(UPDATED_SERVICE_PROVIDER_ZIP_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getServiceProviderTaxonomy()).isEqualTo(UPDATED_SERVICE_PROVIDER_TAXONOMY);
        assertThat(testSecondaryClaimSubmisionMaster.getCms1500FormName()).isEqualTo(UPDATED_CMS_1500_FORM_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getTradingPartnerAddressLine1()).isEqualTo(UPDATED_TRADING_PARTNER_ADDRESS_LINE_1);
        assertThat(testSecondaryClaimSubmisionMaster.getTradingPartnerAddressLine2()).isEqualTo(UPDATED_TRADING_PARTNER_ADDRESS_LINE_2);
        assertThat(testSecondaryClaimSubmisionMaster.getTradingPartnerCity()).isEqualTo(UPDATED_TRADING_PARTNER_CITY);
        assertThat(testSecondaryClaimSubmisionMaster.getTradingPartnerState()).isEqualTo(UPDATED_TRADING_PARTNER_STATE);
        assertThat(testSecondaryClaimSubmisionMaster.getTradingPartnerZip()).isEqualTo(UPDATED_TRADING_PARTNER_ZIP);
        assertThat(testSecondaryClaimSubmisionMaster.getDiagnosisCodeType()).isEqualTo(UPDATED_DIAGNOSIS_CODE_TYPE);
        assertThat(testSecondaryClaimSubmisionMaster.getSecondaryClaimSubmisionMasterUuid())
            .isEqualTo(UPDATED_SECONDARY_CLAIM_SUBMISION_MASTER_UUID);
    }

    @Test
    void putNonExistingSecondaryClaimSubmisionMaster() throws Exception {
        int databaseSizeBeforeUpdate = secondaryClaimSubmisionMasterRepository.findAll().collectList().block().size();
        secondaryClaimSubmisionMaster.setChangeHealthSecondarySubmisionMasterId(count.incrementAndGet());

        // Create the SecondaryClaimSubmisionMaster
        SecondaryClaimSubmisionMasterDTO secondaryClaimSubmisionMasterDTO = secondaryClaimSubmisionMasterMapper.toDto(
            secondaryClaimSubmisionMaster
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, secondaryClaimSubmisionMasterDTO.getChangeHealthSecondarySubmisionMasterId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(secondaryClaimSubmisionMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SecondaryClaimSubmisionMaster in the database
        List<SecondaryClaimSubmisionMaster> secondaryClaimSubmisionMasterList = secondaryClaimSubmisionMasterRepository
            .findAll()
            .collectList()
            .block();
        assertThat(secondaryClaimSubmisionMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchSecondaryClaimSubmisionMaster() throws Exception {
        int databaseSizeBeforeUpdate = secondaryClaimSubmisionMasterRepository.findAll().collectList().block().size();
        secondaryClaimSubmisionMaster.setChangeHealthSecondarySubmisionMasterId(count.incrementAndGet());

        // Create the SecondaryClaimSubmisionMaster
        SecondaryClaimSubmisionMasterDTO secondaryClaimSubmisionMasterDTO = secondaryClaimSubmisionMasterMapper.toDto(
            secondaryClaimSubmisionMaster
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(secondaryClaimSubmisionMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SecondaryClaimSubmisionMaster in the database
        List<SecondaryClaimSubmisionMaster> secondaryClaimSubmisionMasterList = secondaryClaimSubmisionMasterRepository
            .findAll()
            .collectList()
            .block();
        assertThat(secondaryClaimSubmisionMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamSecondaryClaimSubmisionMaster() throws Exception {
        int databaseSizeBeforeUpdate = secondaryClaimSubmisionMasterRepository.findAll().collectList().block().size();
        secondaryClaimSubmisionMaster.setChangeHealthSecondarySubmisionMasterId(count.incrementAndGet());

        // Create the SecondaryClaimSubmisionMaster
        SecondaryClaimSubmisionMasterDTO secondaryClaimSubmisionMasterDTO = secondaryClaimSubmisionMasterMapper.toDto(
            secondaryClaimSubmisionMaster
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(secondaryClaimSubmisionMasterDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the SecondaryClaimSubmisionMaster in the database
        List<SecondaryClaimSubmisionMaster> secondaryClaimSubmisionMasterList = secondaryClaimSubmisionMasterRepository
            .findAll()
            .collectList()
            .block();
        assertThat(secondaryClaimSubmisionMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateSecondaryClaimSubmisionMasterWithPatch() throws Exception {
        // Initialize the database
        secondaryClaimSubmisionMasterRepository.save(secondaryClaimSubmisionMaster).block();

        int databaseSizeBeforeUpdate = secondaryClaimSubmisionMasterRepository.findAll().collectList().block().size();

        // Update the secondaryClaimSubmisionMaster using partial update
        SecondaryClaimSubmisionMaster partialUpdatedSecondaryClaimSubmisionMaster = new SecondaryClaimSubmisionMaster();
        partialUpdatedSecondaryClaimSubmisionMaster.setChangeHealthSecondarySubmisionMasterId(
            secondaryClaimSubmisionMaster.getChangeHealthSecondarySubmisionMasterId()
        );

        partialUpdatedSecondaryClaimSubmisionMaster
            .salesOrderId(UPDATED_SALES_ORDER_ID)
            .claimControlNo(UPDATED_CLAIM_CONTROL_NO)
            .tradingPartnerServiceId(UPDATED_TRADING_PARTNER_SERVICE_ID)
            .tradingPartnerName(UPDATED_TRADING_PARTNER_NAME)
            .submitterContactNo(UPDATED_SUBMITTER_CONTACT_NO)
            .receiverOrganizationName(UPDATED_RECEIVER_ORGANIZATION_NAME)
            .subscriberFirstName(UPDATED_SUBSCRIBER_FIRST_NAME)
            .subscriberDob(UPDATED_SUBSCRIBER_DOB)
            .secondaryInsurerPolicyNo(UPDATED_SECONDARY_INSURER_POLICY_NO)
            .subscriberZipCode(UPDATED_SUBSCRIBER_ZIP_CODE)
            .providerType(UPDATED_PROVIDER_TYPE)
            .billingProviderEin(UPDATED_BILLING_PROVIDER_EIN)
            .billingProviderAddressLine1(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_1)
            .billingProviderAddressLine2(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_2)
            .billingProviderCity(UPDATED_BILLING_PROVIDER_CITY)
            .billingProviderZipCode(UPDATED_BILLING_PROVIDER_ZIP_CODE)
            .billingProviderContactNo(UPDATED_BILLING_PROVIDER_CONTACT_NO)
            .claimFilingCode(UPDATED_CLAIM_FILING_CODE)
            .claimChargeAmount(UPDATED_CLAIM_CHARGE_AMOUNT)
            .posCode(UPDATED_POS_CODE)
            .claimFrequencyCode(UPDATED_CLAIM_FREQUENCY_CODE)
            .signatureIndicator(UPDATED_SIGNATURE_INDICATOR)
            .primaryDiagnosis(UPDATED_PRIMARY_DIAGNOSIS)
            .icd10DiagnosisCode3(UPDATED_ICD_10_DIAGNOSIS_CODE_3)
            .icd10DiagnosisCode4(UPDATED_ICD_10_DIAGNOSIS_CODE_4)
            .icd10DiagnosisCode5(UPDATED_ICD_10_DIAGNOSIS_CODE_5)
            .icd10DiagnosisCode6(UPDATED_ICD_10_DIAGNOSIS_CODE_6)
            .icd10DiagnosisCode9(UPDATED_ICD_10_DIAGNOSIS_CODE_9)
            .icd10DiagnosisCode10(UPDATED_ICD_10_DIAGNOSIS_CODE_10)
            .icd10DiagnosisCode11(UPDATED_ICD_10_DIAGNOSIS_CODE_11)
            .insertedById(UPDATED_INSERTED_BY_ID)
            .insertedDate(UPDATED_INSERTED_DATE)
            .insertedByName(UPDATED_INSERTED_BY_NAME)
            .otherSubscriberCity(UPDATED_OTHER_SUBSCRIBER_CITY)
            .otherSubscriberState(UPDATED_OTHER_SUBSCRIBER_STATE)
            .otherInsuredLastName(UPDATED_OTHER_INSURED_LAST_NAME)
            .otherInsuredFirstName(UPDATED_OTHER_INSURED_FIRST_NAME)
            .otherInsuredIdentifierTypeCode(UPDATED_OTHER_INSURED_IDENTIFIER_TYPE_CODE)
            .otherInsuredIdentifier(UPDATED_OTHER_INSURED_IDENTIFIER)
            .otherPayerIdentifierTypeCode(UPDATED_OTHER_PAYER_IDENTIFIER_TYPE_CODE)
            .otherPayerAdjudicationOrPaymentDate(UPDATED_OTHER_PAYER_ADJUDICATION_OR_PAYMENT_DATE)
            .payerPaidAmount(UPDATED_PAYER_PAID_AMOUNT)
            .claimFilingIndicatorCode(UPDATED_CLAIM_FILING_INDICATOR_CODE)
            .otherPayerBenefitsAssignmentCertificationIndicator(UPDATED_OTHER_PAYER_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR)
            .remainingPatientLiability(UPDATED_REMAINING_PATIENT_LIABILITY)
            .patientSignatureGeneratedForPatient(UPDATED_PATIENT_SIGNATURE_GENERATED_FOR_PATIENT)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .insuredFirstName(UPDATED_INSURED_FIRST_NAME)
            .insuredAddressLine1(UPDATED_INSURED_ADDRESS_LINE_1)
            .insuredCity(UPDATED_INSURED_CITY)
            .insuredState(UPDATED_INSURED_STATE)
            .insuredContactNo(UPDATED_INSURED_CONTACT_NO)
            .insuredDob(UPDATED_INSURED_DOB)
            .insuredGender(UPDATED_INSURED_GENDER)
            .orderingProviderLastName(UPDATED_ORDERING_PROVIDER_LAST_NAME)
            .orderingProviderNpi(UPDATED_ORDERING_PROVIDER_NPI)
            .isNextLevelInsurerPresentStatus(UPDATED_IS_NEXT_LEVEL_INSURER_PRESENT_STATUS)
            .originalDos(UPDATED_ORIGINAL_DOS)
            .billingProviderTaxonomy(UPDATED_BILLING_PROVIDER_TAXONOMY)
            .serviceProviderAddressLine1(UPDATED_SERVICE_PROVIDER_ADDRESS_LINE_1)
            .serviceProviderAddressLine2(UPDATED_SERVICE_PROVIDER_ADDRESS_LINE_2)
            .serviceProviderCountry(UPDATED_SERVICE_PROVIDER_COUNTRY)
            .serviceProviderZipCode(UPDATED_SERVICE_PROVIDER_ZIP_CODE)
            .serviceProviderTaxonomy(UPDATED_SERVICE_PROVIDER_TAXONOMY)
            .cms1500FormName(UPDATED_CMS_1500_FORM_NAME)
            .tradingPartnerAddressLine1(UPDATED_TRADING_PARTNER_ADDRESS_LINE_1)
            .tradingPartnerCity(UPDATED_TRADING_PARTNER_CITY)
            .secondaryClaimSubmisionMasterUuid(UPDATED_SECONDARY_CLAIM_SUBMISION_MASTER_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedSecondaryClaimSubmisionMaster.getChangeHealthSecondarySubmisionMasterId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedSecondaryClaimSubmisionMaster))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SecondaryClaimSubmisionMaster in the database
        List<SecondaryClaimSubmisionMaster> secondaryClaimSubmisionMasterList = secondaryClaimSubmisionMasterRepository
            .findAll()
            .collectList()
            .block();
        assertThat(secondaryClaimSubmisionMasterList).hasSize(databaseSizeBeforeUpdate);
        SecondaryClaimSubmisionMaster testSecondaryClaimSubmisionMaster = secondaryClaimSubmisionMasterList.get(
            secondaryClaimSubmisionMasterList.size() - 1
        );
        assertThat(testSecondaryClaimSubmisionMaster.getSalesOrderId()).isEqualTo(UPDATED_SALES_ORDER_ID);
        assertThat(testSecondaryClaimSubmisionMaster.getClaimControlNo()).isEqualTo(UPDATED_CLAIM_CONTROL_NO);
        assertThat(testSecondaryClaimSubmisionMaster.getTradingPartnerServiceId()).isEqualTo(UPDATED_TRADING_PARTNER_SERVICE_ID);
        assertThat(testSecondaryClaimSubmisionMaster.getTradingPartnerName()).isEqualTo(UPDATED_TRADING_PARTNER_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getSubmitterOrganizationName()).isEqualTo(DEFAULT_SUBMITTER_ORGANIZATION_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getSubmitterContactPersonName()).isEqualTo(DEFAULT_SUBMITTER_CONTACT_PERSON_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getSubmitterContactNo()).isEqualTo(UPDATED_SUBMITTER_CONTACT_NO);
        assertThat(testSecondaryClaimSubmisionMaster.getReceiverOrganizationName()).isEqualTo(UPDATED_RECEIVER_ORGANIZATION_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getSubscriberMemberIdNo()).isEqualTo(DEFAULT_SUBSCRIBER_MEMBER_ID_NO);
        assertThat(testSecondaryClaimSubmisionMaster.getSubscriberPaymentResponsibilityLevelCode())
            .isEqualTo(DEFAULT_SUBSCRIBER_PAYMENT_RESPONSIBILITY_LEVEL_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getSubscriberFirstName()).isEqualTo(UPDATED_SUBSCRIBER_FIRST_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getSubscriberLastName()).isEqualTo(DEFAULT_SUBSCRIBER_LAST_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getSubscriberGender()).isEqualTo(DEFAULT_SUBSCRIBER_GENDER);
        assertThat(testSecondaryClaimSubmisionMaster.getSubscriberDob()).isEqualTo(UPDATED_SUBSCRIBER_DOB);
        assertThat(testSecondaryClaimSubmisionMaster.getSecondaryInsurerPolicyNo()).isEqualTo(UPDATED_SECONDARY_INSURER_POLICY_NO);
        assertThat(testSecondaryClaimSubmisionMaster.getSubscriberAddressLine1()).isEqualTo(DEFAULT_SUBSCRIBER_ADDRESS_LINE_1);
        assertThat(testSecondaryClaimSubmisionMaster.getSubscriberCity()).isEqualTo(DEFAULT_SUBSCRIBER_CITY);
        assertThat(testSecondaryClaimSubmisionMaster.getSubscriberState()).isEqualTo(DEFAULT_SUBSCRIBER_STATE);
        assertThat(testSecondaryClaimSubmisionMaster.getSubscriberZipCode()).isEqualTo(UPDATED_SUBSCRIBER_ZIP_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getProviderType()).isEqualTo(UPDATED_PROVIDER_TYPE);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderNpi()).isEqualTo(DEFAULT_BILLING_PROVIDER_NPI);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderEin()).isEqualTo(UPDATED_BILLING_PROVIDER_EIN);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderOrganizationName())
            .isEqualTo(DEFAULT_BILLING_PROVIDER_ORGANIZATION_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderAddressLine1()).isEqualTo(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_1);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderAddressLine2()).isEqualTo(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_2);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderCity()).isEqualTo(UPDATED_BILLING_PROVIDER_CITY);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderState()).isEqualTo(DEFAULT_BILLING_PROVIDER_STATE);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderZipCode()).isEqualTo(UPDATED_BILLING_PROVIDER_ZIP_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderContactPersonName())
            .isEqualTo(DEFAULT_BILLING_PROVIDER_CONTACT_PERSON_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderContactNo()).isEqualTo(UPDATED_BILLING_PROVIDER_CONTACT_NO);
        assertThat(testSecondaryClaimSubmisionMaster.getClaimFilingCode()).isEqualTo(UPDATED_CLAIM_FILING_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getPatientAccountNo()).isEqualTo(DEFAULT_PATIENT_ACCOUNT_NO);
        assertThat(testSecondaryClaimSubmisionMaster.getClaimChargeAmount()).isEqualTo(UPDATED_CLAIM_CHARGE_AMOUNT);
        assertThat(testSecondaryClaimSubmisionMaster.getPosCode()).isEqualTo(UPDATED_POS_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getClaimFrequencyCode()).isEqualTo(UPDATED_CLAIM_FREQUENCY_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getSignatureIndicator()).isEqualTo(UPDATED_SIGNATURE_INDICATOR);
        assertThat(testSecondaryClaimSubmisionMaster.getPlanParticipationCode()).isEqualTo(DEFAULT_PLAN_PARTICIPATION_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getBenefitsAssignmentCertificationIndicator())
            .isEqualTo(DEFAULT_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR);
        assertThat(testSecondaryClaimSubmisionMaster.getReleaseInformationCode()).isEqualTo(DEFAULT_RELEASE_INFORMATION_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getPrimaryDiagnosis()).isEqualTo(UPDATED_PRIMARY_DIAGNOSIS);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode1()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_1);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode2()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_2);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode3()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_3);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode4()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_4);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode5()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_5);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode6()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_6);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode7()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_7);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode8()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_8);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode9()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_9);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode10()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_10);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode11()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_11);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode12()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_12);
        assertThat(testSecondaryClaimSubmisionMaster.getInsertedById()).isEqualTo(UPDATED_INSERTED_BY_ID);
        assertThat(testSecondaryClaimSubmisionMaster.getInsertedDate()).isEqualTo(UPDATED_INSERTED_DATE);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderType()).isEqualTo(DEFAULT_BILLING_PROVIDER_TYPE);
        assertThat(testSecondaryClaimSubmisionMaster.getInsertedByName()).isEqualTo(UPDATED_INSERTED_BY_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherSubscriberAddress1()).isEqualTo(DEFAULT_OTHER_SUBSCRIBER_ADDRESS_1);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherSubscriberAddress2()).isEqualTo(DEFAULT_OTHER_SUBSCRIBER_ADDRESS_2);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherSubscriberCity()).isEqualTo(UPDATED_OTHER_SUBSCRIBER_CITY);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherSubscriberState()).isEqualTo(UPDATED_OTHER_SUBSCRIBER_STATE);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherSubscriberZip()).isEqualTo(DEFAULT_OTHER_SUBSCRIBER_ZIP);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherInsuredQualifier()).isEqualTo(DEFAULT_OTHER_INSURED_QUALIFIER);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherInsuredLastName()).isEqualTo(UPDATED_OTHER_INSURED_LAST_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherInsuredFirstName()).isEqualTo(UPDATED_OTHER_INSURED_FIRST_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherInsuredIdentifierTypeCode())
            .isEqualTo(UPDATED_OTHER_INSURED_IDENTIFIER_TYPE_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherInsuredIdentifier()).isEqualTo(UPDATED_OTHER_INSURED_IDENTIFIER);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherPayerOrganizationName()).isEqualTo(DEFAULT_OTHER_PAYER_ORGANIZATION_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherPayerIdentifierTypeCode()).isEqualTo(UPDATED_OTHER_PAYER_IDENTIFIER_TYPE_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherPayerIdentifier()).isEqualTo(DEFAULT_OTHER_PAYER_IDENTIFIER);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherPayerAdjudicationOrPaymentDate())
            .isEqualTo(UPDATED_OTHER_PAYER_ADJUDICATION_OR_PAYMENT_DATE);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherPayerClaimAdjustmentIndicator())
            .isEqualTo(DEFAULT_OTHER_PAYER_CLAIM_ADJUSTMENT_INDICATOR);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherPayerClaimControlNumber()).isEqualTo(DEFAULT_OTHER_PAYER_CLAIM_CONTROL_NUMBER);
        assertThat(testSecondaryClaimSubmisionMaster.getPayerPaidAmount()).isEqualTo(UPDATED_PAYER_PAID_AMOUNT);
        assertThat(testSecondaryClaimSubmisionMaster.getPaymentResponsibilityLevelCode())
            .isEqualTo(DEFAULT_PAYMENT_RESPONSIBILITY_LEVEL_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getIndividualRelationshipCode()).isEqualTo(DEFAULT_INDIVIDUAL_RELATIONSHIP_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getClaimFilingIndicatorCode()).isEqualTo(UPDATED_CLAIM_FILING_INDICATOR_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherPayerBenefitsAssignmentCertificationIndicator())
            .isEqualTo(UPDATED_OTHER_PAYER_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR);
        assertThat(testSecondaryClaimSubmisionMaster.getReleaseOfInformationCode()).isEqualTo(DEFAULT_RELEASE_OF_INFORMATION_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getRemainingPatientLiability()).isEqualTo(UPDATED_REMAINING_PATIENT_LIABILITY);
        assertThat(testSecondaryClaimSubmisionMaster.getPatientSignatureGeneratedForPatient())
            .isEqualTo(UPDATED_PATIENT_SIGNATURE_GENERATED_FOR_PATIENT);
        assertThat(testSecondaryClaimSubmisionMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testSecondaryClaimSubmisionMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testSecondaryClaimSubmisionMaster.getChangeHealthPrimarySubmisionMasterId())
            .isEqualTo(DEFAULT_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID);
        assertThat(testSecondaryClaimSubmisionMaster.getInsuredFirstName()).isEqualTo(UPDATED_INSURED_FIRST_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getInsuredLastName()).isEqualTo(DEFAULT_INSURED_LAST_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getInsuredAddressLine1()).isEqualTo(UPDATED_INSURED_ADDRESS_LINE_1);
        assertThat(testSecondaryClaimSubmisionMaster.getInsuredAddressLine2()).isEqualTo(DEFAULT_INSURED_ADDRESS_LINE_2);
        assertThat(testSecondaryClaimSubmisionMaster.getInsuredCity()).isEqualTo(UPDATED_INSURED_CITY);
        assertThat(testSecondaryClaimSubmisionMaster.getInsuredState()).isEqualTo(UPDATED_INSURED_STATE);
        assertThat(testSecondaryClaimSubmisionMaster.getInsuredZip()).isEqualTo(DEFAULT_INSURED_ZIP);
        assertThat(testSecondaryClaimSubmisionMaster.getInsuredContactNo()).isEqualTo(UPDATED_INSURED_CONTACT_NO);
        assertThat(testSecondaryClaimSubmisionMaster.getInsuredDob()).isEqualTo(UPDATED_INSURED_DOB);
        assertThat(testSecondaryClaimSubmisionMaster.getInsuredGender()).isEqualTo(UPDATED_INSURED_GENDER);
        assertThat(testSecondaryClaimSubmisionMaster.getOrderingProviderFirstName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getOrderingProviderLastName()).isEqualTo(UPDATED_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getOrderingProviderNpi()).isEqualTo(UPDATED_ORDERING_PROVIDER_NPI);
        assertThat(testSecondaryClaimSubmisionMaster.getPatientRelationshipInsured()).isEqualTo(DEFAULT_PATIENT_RELATIONSHIP_INSURED);
        assertThat(testSecondaryClaimSubmisionMaster.getPatientConditionEmployment()).isEqualTo(DEFAULT_PATIENT_CONDITION_EMPLOYMENT);
        assertThat(testSecondaryClaimSubmisionMaster.getPatientConditionAutoAccident()).isEqualTo(DEFAULT_PATIENT_CONDITION_AUTO_ACCIDENT);
        assertThat(testSecondaryClaimSubmisionMaster.getPatientConditionOtherAccident())
            .isEqualTo(DEFAULT_PATIENT_CONDITION_OTHER_ACCIDENT);
        assertThat(testSecondaryClaimSubmisionMaster.getIsNextLevelInsurerPresentStatus())
            .isEqualTo(UPDATED_IS_NEXT_LEVEL_INSURER_PRESENT_STATUS);
        assertThat(testSecondaryClaimSubmisionMaster.getOriginalDos()).isEqualTo(UPDATED_ORIGINAL_DOS);
        assertThat(testSecondaryClaimSubmisionMaster.getParNo()).isEqualTo(DEFAULT_PAR_NO);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderTaxonomy()).isEqualTo(UPDATED_BILLING_PROVIDER_TAXONOMY);
        assertThat(testSecondaryClaimSubmisionMaster.getServiceProviderNpi()).isEqualTo(DEFAULT_SERVICE_PROVIDER_NPI);
        assertThat(testSecondaryClaimSubmisionMaster.getServiceProviderOrganisationName())
            .isEqualTo(DEFAULT_SERVICE_PROVIDER_ORGANISATION_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getServiceProviderAddressLine1()).isEqualTo(UPDATED_SERVICE_PROVIDER_ADDRESS_LINE_1);
        assertThat(testSecondaryClaimSubmisionMaster.getServiceProviderAddressLine2()).isEqualTo(UPDATED_SERVICE_PROVIDER_ADDRESS_LINE_2);
        assertThat(testSecondaryClaimSubmisionMaster.getServiceProviderCity()).isEqualTo(DEFAULT_SERVICE_PROVIDER_CITY);
        assertThat(testSecondaryClaimSubmisionMaster.getServiceProviderState()).isEqualTo(DEFAULT_SERVICE_PROVIDER_STATE);
        assertThat(testSecondaryClaimSubmisionMaster.getServiceProviderCountry()).isEqualTo(UPDATED_SERVICE_PROVIDER_COUNTRY);
        assertThat(testSecondaryClaimSubmisionMaster.getServiceProviderZipCode()).isEqualTo(UPDATED_SERVICE_PROVIDER_ZIP_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getServiceProviderTaxonomy()).isEqualTo(UPDATED_SERVICE_PROVIDER_TAXONOMY);
        assertThat(testSecondaryClaimSubmisionMaster.getCms1500FormName()).isEqualTo(UPDATED_CMS_1500_FORM_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getTradingPartnerAddressLine1()).isEqualTo(UPDATED_TRADING_PARTNER_ADDRESS_LINE_1);
        assertThat(testSecondaryClaimSubmisionMaster.getTradingPartnerAddressLine2()).isEqualTo(DEFAULT_TRADING_PARTNER_ADDRESS_LINE_2);
        assertThat(testSecondaryClaimSubmisionMaster.getTradingPartnerCity()).isEqualTo(UPDATED_TRADING_PARTNER_CITY);
        assertThat(testSecondaryClaimSubmisionMaster.getTradingPartnerState()).isEqualTo(DEFAULT_TRADING_PARTNER_STATE);
        assertThat(testSecondaryClaimSubmisionMaster.getTradingPartnerZip()).isEqualTo(DEFAULT_TRADING_PARTNER_ZIP);
        assertThat(testSecondaryClaimSubmisionMaster.getDiagnosisCodeType()).isEqualTo(DEFAULT_DIAGNOSIS_CODE_TYPE);
        assertThat(testSecondaryClaimSubmisionMaster.getSecondaryClaimSubmisionMasterUuid())
            .isEqualTo(UPDATED_SECONDARY_CLAIM_SUBMISION_MASTER_UUID);
    }

    @Test
    void fullUpdateSecondaryClaimSubmisionMasterWithPatch() throws Exception {
        // Initialize the database
        secondaryClaimSubmisionMasterRepository.save(secondaryClaimSubmisionMaster).block();

        int databaseSizeBeforeUpdate = secondaryClaimSubmisionMasterRepository.findAll().collectList().block().size();

        // Update the secondaryClaimSubmisionMaster using partial update
        SecondaryClaimSubmisionMaster partialUpdatedSecondaryClaimSubmisionMaster = new SecondaryClaimSubmisionMaster();
        partialUpdatedSecondaryClaimSubmisionMaster.setChangeHealthSecondarySubmisionMasterId(
            secondaryClaimSubmisionMaster.getChangeHealthSecondarySubmisionMasterId()
        );

        partialUpdatedSecondaryClaimSubmisionMaster
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
            .secondaryInsurerPolicyNo(UPDATED_SECONDARY_INSURER_POLICY_NO)
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
            .diagnosisCodeType(UPDATED_DIAGNOSIS_CODE_TYPE)
            .secondaryClaimSubmisionMasterUuid(UPDATED_SECONDARY_CLAIM_SUBMISION_MASTER_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedSecondaryClaimSubmisionMaster.getChangeHealthSecondarySubmisionMasterId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedSecondaryClaimSubmisionMaster))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SecondaryClaimSubmisionMaster in the database
        List<SecondaryClaimSubmisionMaster> secondaryClaimSubmisionMasterList = secondaryClaimSubmisionMasterRepository
            .findAll()
            .collectList()
            .block();
        assertThat(secondaryClaimSubmisionMasterList).hasSize(databaseSizeBeforeUpdate);
        SecondaryClaimSubmisionMaster testSecondaryClaimSubmisionMaster = secondaryClaimSubmisionMasterList.get(
            secondaryClaimSubmisionMasterList.size() - 1
        );
        assertThat(testSecondaryClaimSubmisionMaster.getSalesOrderId()).isEqualTo(UPDATED_SALES_ORDER_ID);
        assertThat(testSecondaryClaimSubmisionMaster.getClaimControlNo()).isEqualTo(UPDATED_CLAIM_CONTROL_NO);
        assertThat(testSecondaryClaimSubmisionMaster.getTradingPartnerServiceId()).isEqualTo(UPDATED_TRADING_PARTNER_SERVICE_ID);
        assertThat(testSecondaryClaimSubmisionMaster.getTradingPartnerName()).isEqualTo(UPDATED_TRADING_PARTNER_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getSubmitterOrganizationName()).isEqualTo(UPDATED_SUBMITTER_ORGANIZATION_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getSubmitterContactPersonName()).isEqualTo(UPDATED_SUBMITTER_CONTACT_PERSON_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getSubmitterContactNo()).isEqualTo(UPDATED_SUBMITTER_CONTACT_NO);
        assertThat(testSecondaryClaimSubmisionMaster.getReceiverOrganizationName()).isEqualTo(UPDATED_RECEIVER_ORGANIZATION_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getSubscriberMemberIdNo()).isEqualTo(UPDATED_SUBSCRIBER_MEMBER_ID_NO);
        assertThat(testSecondaryClaimSubmisionMaster.getSubscriberPaymentResponsibilityLevelCode())
            .isEqualTo(UPDATED_SUBSCRIBER_PAYMENT_RESPONSIBILITY_LEVEL_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getSubscriberFirstName()).isEqualTo(UPDATED_SUBSCRIBER_FIRST_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getSubscriberLastName()).isEqualTo(UPDATED_SUBSCRIBER_LAST_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getSubscriberGender()).isEqualTo(UPDATED_SUBSCRIBER_GENDER);
        assertThat(testSecondaryClaimSubmisionMaster.getSubscriberDob()).isEqualTo(UPDATED_SUBSCRIBER_DOB);
        assertThat(testSecondaryClaimSubmisionMaster.getSecondaryInsurerPolicyNo()).isEqualTo(UPDATED_SECONDARY_INSURER_POLICY_NO);
        assertThat(testSecondaryClaimSubmisionMaster.getSubscriberAddressLine1()).isEqualTo(UPDATED_SUBSCRIBER_ADDRESS_LINE_1);
        assertThat(testSecondaryClaimSubmisionMaster.getSubscriberCity()).isEqualTo(UPDATED_SUBSCRIBER_CITY);
        assertThat(testSecondaryClaimSubmisionMaster.getSubscriberState()).isEqualTo(UPDATED_SUBSCRIBER_STATE);
        assertThat(testSecondaryClaimSubmisionMaster.getSubscriberZipCode()).isEqualTo(UPDATED_SUBSCRIBER_ZIP_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getProviderType()).isEqualTo(UPDATED_PROVIDER_TYPE);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderNpi()).isEqualTo(UPDATED_BILLING_PROVIDER_NPI);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderEin()).isEqualTo(UPDATED_BILLING_PROVIDER_EIN);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderOrganizationName())
            .isEqualTo(UPDATED_BILLING_PROVIDER_ORGANIZATION_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderAddressLine1()).isEqualTo(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_1);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderAddressLine2()).isEqualTo(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_2);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderCity()).isEqualTo(UPDATED_BILLING_PROVIDER_CITY);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderState()).isEqualTo(UPDATED_BILLING_PROVIDER_STATE);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderZipCode()).isEqualTo(UPDATED_BILLING_PROVIDER_ZIP_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderContactPersonName())
            .isEqualTo(UPDATED_BILLING_PROVIDER_CONTACT_PERSON_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderContactNo()).isEqualTo(UPDATED_BILLING_PROVIDER_CONTACT_NO);
        assertThat(testSecondaryClaimSubmisionMaster.getClaimFilingCode()).isEqualTo(UPDATED_CLAIM_FILING_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getPatientAccountNo()).isEqualTo(UPDATED_PATIENT_ACCOUNT_NO);
        assertThat(testSecondaryClaimSubmisionMaster.getClaimChargeAmount()).isEqualTo(UPDATED_CLAIM_CHARGE_AMOUNT);
        assertThat(testSecondaryClaimSubmisionMaster.getPosCode()).isEqualTo(UPDATED_POS_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getClaimFrequencyCode()).isEqualTo(UPDATED_CLAIM_FREQUENCY_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getSignatureIndicator()).isEqualTo(UPDATED_SIGNATURE_INDICATOR);
        assertThat(testSecondaryClaimSubmisionMaster.getPlanParticipationCode()).isEqualTo(UPDATED_PLAN_PARTICIPATION_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getBenefitsAssignmentCertificationIndicator())
            .isEqualTo(UPDATED_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR);
        assertThat(testSecondaryClaimSubmisionMaster.getReleaseInformationCode()).isEqualTo(UPDATED_RELEASE_INFORMATION_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getPrimaryDiagnosis()).isEqualTo(UPDATED_PRIMARY_DIAGNOSIS);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode1()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_1);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode2()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_2);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode3()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_3);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode4()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_4);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode5()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_5);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode6()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_6);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode7()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_7);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode8()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_8);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode9()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_9);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode10()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_10);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode11()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_11);
        assertThat(testSecondaryClaimSubmisionMaster.getIcd10DiagnosisCode12()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_12);
        assertThat(testSecondaryClaimSubmisionMaster.getInsertedById()).isEqualTo(UPDATED_INSERTED_BY_ID);
        assertThat(testSecondaryClaimSubmisionMaster.getInsertedDate()).isEqualTo(UPDATED_INSERTED_DATE);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderType()).isEqualTo(UPDATED_BILLING_PROVIDER_TYPE);
        assertThat(testSecondaryClaimSubmisionMaster.getInsertedByName()).isEqualTo(UPDATED_INSERTED_BY_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherSubscriberAddress1()).isEqualTo(UPDATED_OTHER_SUBSCRIBER_ADDRESS_1);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherSubscriberAddress2()).isEqualTo(UPDATED_OTHER_SUBSCRIBER_ADDRESS_2);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherSubscriberCity()).isEqualTo(UPDATED_OTHER_SUBSCRIBER_CITY);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherSubscriberState()).isEqualTo(UPDATED_OTHER_SUBSCRIBER_STATE);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherSubscriberZip()).isEqualTo(UPDATED_OTHER_SUBSCRIBER_ZIP);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherInsuredQualifier()).isEqualTo(UPDATED_OTHER_INSURED_QUALIFIER);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherInsuredLastName()).isEqualTo(UPDATED_OTHER_INSURED_LAST_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherInsuredFirstName()).isEqualTo(UPDATED_OTHER_INSURED_FIRST_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherInsuredIdentifierTypeCode())
            .isEqualTo(UPDATED_OTHER_INSURED_IDENTIFIER_TYPE_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherInsuredIdentifier()).isEqualTo(UPDATED_OTHER_INSURED_IDENTIFIER);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherPayerOrganizationName()).isEqualTo(UPDATED_OTHER_PAYER_ORGANIZATION_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherPayerIdentifierTypeCode()).isEqualTo(UPDATED_OTHER_PAYER_IDENTIFIER_TYPE_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherPayerIdentifier()).isEqualTo(UPDATED_OTHER_PAYER_IDENTIFIER);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherPayerAdjudicationOrPaymentDate())
            .isEqualTo(UPDATED_OTHER_PAYER_ADJUDICATION_OR_PAYMENT_DATE);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherPayerClaimAdjustmentIndicator())
            .isEqualTo(UPDATED_OTHER_PAYER_CLAIM_ADJUSTMENT_INDICATOR);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherPayerClaimControlNumber()).isEqualTo(UPDATED_OTHER_PAYER_CLAIM_CONTROL_NUMBER);
        assertThat(testSecondaryClaimSubmisionMaster.getPayerPaidAmount()).isEqualTo(UPDATED_PAYER_PAID_AMOUNT);
        assertThat(testSecondaryClaimSubmisionMaster.getPaymentResponsibilityLevelCode())
            .isEqualTo(UPDATED_PAYMENT_RESPONSIBILITY_LEVEL_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getIndividualRelationshipCode()).isEqualTo(UPDATED_INDIVIDUAL_RELATIONSHIP_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getClaimFilingIndicatorCode()).isEqualTo(UPDATED_CLAIM_FILING_INDICATOR_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getOtherPayerBenefitsAssignmentCertificationIndicator())
            .isEqualTo(UPDATED_OTHER_PAYER_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR);
        assertThat(testSecondaryClaimSubmisionMaster.getReleaseOfInformationCode()).isEqualTo(UPDATED_RELEASE_OF_INFORMATION_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getRemainingPatientLiability()).isEqualTo(UPDATED_REMAINING_PATIENT_LIABILITY);
        assertThat(testSecondaryClaimSubmisionMaster.getPatientSignatureGeneratedForPatient())
            .isEqualTo(UPDATED_PATIENT_SIGNATURE_GENERATED_FOR_PATIENT);
        assertThat(testSecondaryClaimSubmisionMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testSecondaryClaimSubmisionMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testSecondaryClaimSubmisionMaster.getChangeHealthPrimarySubmisionMasterId())
            .isEqualTo(UPDATED_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID);
        assertThat(testSecondaryClaimSubmisionMaster.getInsuredFirstName()).isEqualTo(UPDATED_INSURED_FIRST_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getInsuredLastName()).isEqualTo(UPDATED_INSURED_LAST_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getInsuredAddressLine1()).isEqualTo(UPDATED_INSURED_ADDRESS_LINE_1);
        assertThat(testSecondaryClaimSubmisionMaster.getInsuredAddressLine2()).isEqualTo(UPDATED_INSURED_ADDRESS_LINE_2);
        assertThat(testSecondaryClaimSubmisionMaster.getInsuredCity()).isEqualTo(UPDATED_INSURED_CITY);
        assertThat(testSecondaryClaimSubmisionMaster.getInsuredState()).isEqualTo(UPDATED_INSURED_STATE);
        assertThat(testSecondaryClaimSubmisionMaster.getInsuredZip()).isEqualTo(UPDATED_INSURED_ZIP);
        assertThat(testSecondaryClaimSubmisionMaster.getInsuredContactNo()).isEqualTo(UPDATED_INSURED_CONTACT_NO);
        assertThat(testSecondaryClaimSubmisionMaster.getInsuredDob()).isEqualTo(UPDATED_INSURED_DOB);
        assertThat(testSecondaryClaimSubmisionMaster.getInsuredGender()).isEqualTo(UPDATED_INSURED_GENDER);
        assertThat(testSecondaryClaimSubmisionMaster.getOrderingProviderFirstName()).isEqualTo(UPDATED_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getOrderingProviderLastName()).isEqualTo(UPDATED_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getOrderingProviderNpi()).isEqualTo(UPDATED_ORDERING_PROVIDER_NPI);
        assertThat(testSecondaryClaimSubmisionMaster.getPatientRelationshipInsured()).isEqualTo(UPDATED_PATIENT_RELATIONSHIP_INSURED);
        assertThat(testSecondaryClaimSubmisionMaster.getPatientConditionEmployment()).isEqualTo(UPDATED_PATIENT_CONDITION_EMPLOYMENT);
        assertThat(testSecondaryClaimSubmisionMaster.getPatientConditionAutoAccident()).isEqualTo(UPDATED_PATIENT_CONDITION_AUTO_ACCIDENT);
        assertThat(testSecondaryClaimSubmisionMaster.getPatientConditionOtherAccident())
            .isEqualTo(UPDATED_PATIENT_CONDITION_OTHER_ACCIDENT);
        assertThat(testSecondaryClaimSubmisionMaster.getIsNextLevelInsurerPresentStatus())
            .isEqualTo(UPDATED_IS_NEXT_LEVEL_INSURER_PRESENT_STATUS);
        assertThat(testSecondaryClaimSubmisionMaster.getOriginalDos()).isEqualTo(UPDATED_ORIGINAL_DOS);
        assertThat(testSecondaryClaimSubmisionMaster.getParNo()).isEqualTo(UPDATED_PAR_NO);
        assertThat(testSecondaryClaimSubmisionMaster.getBillingProviderTaxonomy()).isEqualTo(UPDATED_BILLING_PROVIDER_TAXONOMY);
        assertThat(testSecondaryClaimSubmisionMaster.getServiceProviderNpi()).isEqualTo(UPDATED_SERVICE_PROVIDER_NPI);
        assertThat(testSecondaryClaimSubmisionMaster.getServiceProviderOrganisationName())
            .isEqualTo(UPDATED_SERVICE_PROVIDER_ORGANISATION_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getServiceProviderAddressLine1()).isEqualTo(UPDATED_SERVICE_PROVIDER_ADDRESS_LINE_1);
        assertThat(testSecondaryClaimSubmisionMaster.getServiceProviderAddressLine2()).isEqualTo(UPDATED_SERVICE_PROVIDER_ADDRESS_LINE_2);
        assertThat(testSecondaryClaimSubmisionMaster.getServiceProviderCity()).isEqualTo(UPDATED_SERVICE_PROVIDER_CITY);
        assertThat(testSecondaryClaimSubmisionMaster.getServiceProviderState()).isEqualTo(UPDATED_SERVICE_PROVIDER_STATE);
        assertThat(testSecondaryClaimSubmisionMaster.getServiceProviderCountry()).isEqualTo(UPDATED_SERVICE_PROVIDER_COUNTRY);
        assertThat(testSecondaryClaimSubmisionMaster.getServiceProviderZipCode()).isEqualTo(UPDATED_SERVICE_PROVIDER_ZIP_CODE);
        assertThat(testSecondaryClaimSubmisionMaster.getServiceProviderTaxonomy()).isEqualTo(UPDATED_SERVICE_PROVIDER_TAXONOMY);
        assertThat(testSecondaryClaimSubmisionMaster.getCms1500FormName()).isEqualTo(UPDATED_CMS_1500_FORM_NAME);
        assertThat(testSecondaryClaimSubmisionMaster.getTradingPartnerAddressLine1()).isEqualTo(UPDATED_TRADING_PARTNER_ADDRESS_LINE_1);
        assertThat(testSecondaryClaimSubmisionMaster.getTradingPartnerAddressLine2()).isEqualTo(UPDATED_TRADING_PARTNER_ADDRESS_LINE_2);
        assertThat(testSecondaryClaimSubmisionMaster.getTradingPartnerCity()).isEqualTo(UPDATED_TRADING_PARTNER_CITY);
        assertThat(testSecondaryClaimSubmisionMaster.getTradingPartnerState()).isEqualTo(UPDATED_TRADING_PARTNER_STATE);
        assertThat(testSecondaryClaimSubmisionMaster.getTradingPartnerZip()).isEqualTo(UPDATED_TRADING_PARTNER_ZIP);
        assertThat(testSecondaryClaimSubmisionMaster.getDiagnosisCodeType()).isEqualTo(UPDATED_DIAGNOSIS_CODE_TYPE);
        assertThat(testSecondaryClaimSubmisionMaster.getSecondaryClaimSubmisionMasterUuid())
            .isEqualTo(UPDATED_SECONDARY_CLAIM_SUBMISION_MASTER_UUID);
    }

    @Test
    void patchNonExistingSecondaryClaimSubmisionMaster() throws Exception {
        int databaseSizeBeforeUpdate = secondaryClaimSubmisionMasterRepository.findAll().collectList().block().size();
        secondaryClaimSubmisionMaster.setChangeHealthSecondarySubmisionMasterId(count.incrementAndGet());

        // Create the SecondaryClaimSubmisionMaster
        SecondaryClaimSubmisionMasterDTO secondaryClaimSubmisionMasterDTO = secondaryClaimSubmisionMasterMapper.toDto(
            secondaryClaimSubmisionMaster
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, secondaryClaimSubmisionMasterDTO.getChangeHealthSecondarySubmisionMasterId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(secondaryClaimSubmisionMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SecondaryClaimSubmisionMaster in the database
        List<SecondaryClaimSubmisionMaster> secondaryClaimSubmisionMasterList = secondaryClaimSubmisionMasterRepository
            .findAll()
            .collectList()
            .block();
        assertThat(secondaryClaimSubmisionMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchSecondaryClaimSubmisionMaster() throws Exception {
        int databaseSizeBeforeUpdate = secondaryClaimSubmisionMasterRepository.findAll().collectList().block().size();
        secondaryClaimSubmisionMaster.setChangeHealthSecondarySubmisionMasterId(count.incrementAndGet());

        // Create the SecondaryClaimSubmisionMaster
        SecondaryClaimSubmisionMasterDTO secondaryClaimSubmisionMasterDTO = secondaryClaimSubmisionMasterMapper.toDto(
            secondaryClaimSubmisionMaster
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(secondaryClaimSubmisionMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SecondaryClaimSubmisionMaster in the database
        List<SecondaryClaimSubmisionMaster> secondaryClaimSubmisionMasterList = secondaryClaimSubmisionMasterRepository
            .findAll()
            .collectList()
            .block();
        assertThat(secondaryClaimSubmisionMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamSecondaryClaimSubmisionMaster() throws Exception {
        int databaseSizeBeforeUpdate = secondaryClaimSubmisionMasterRepository.findAll().collectList().block().size();
        secondaryClaimSubmisionMaster.setChangeHealthSecondarySubmisionMasterId(count.incrementAndGet());

        // Create the SecondaryClaimSubmisionMaster
        SecondaryClaimSubmisionMasterDTO secondaryClaimSubmisionMasterDTO = secondaryClaimSubmisionMasterMapper.toDto(
            secondaryClaimSubmisionMaster
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(secondaryClaimSubmisionMasterDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the SecondaryClaimSubmisionMaster in the database
        List<SecondaryClaimSubmisionMaster> secondaryClaimSubmisionMasterList = secondaryClaimSubmisionMasterRepository
            .findAll()
            .collectList()
            .block();
        assertThat(secondaryClaimSubmisionMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteSecondaryClaimSubmisionMaster() {
        // Initialize the database
        secondaryClaimSubmisionMasterRepository.save(secondaryClaimSubmisionMaster).block();

        int databaseSizeBeforeDelete = secondaryClaimSubmisionMasterRepository.findAll().collectList().block().size();

        // Delete the secondaryClaimSubmisionMaster
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, secondaryClaimSubmisionMaster.getChangeHealthSecondarySubmisionMasterId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<SecondaryClaimSubmisionMaster> secondaryClaimSubmisionMasterList = secondaryClaimSubmisionMasterRepository
            .findAll()
            .collectList()
            .block();
        assertThat(secondaryClaimSubmisionMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
