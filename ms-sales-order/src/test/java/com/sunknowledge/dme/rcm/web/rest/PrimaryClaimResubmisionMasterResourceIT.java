package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.PrimaryClaimResubmisionMaster;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.PrimaryClaimResubmisionMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.PrimaryClaimResubmisionMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.PrimaryClaimResubmisionMasterMapper;
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
 * Integration tests for the {@link PrimaryClaimResubmisionMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class PrimaryClaimResubmisionMasterResourceIT {

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

    private static final LocalDate DEFAULT_INSERTED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_INSERTED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

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

    private static final UUID DEFAULT_PRIMARY_CLAIM_RESUBMISION_MASTER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_PRIMARY_CLAIM_RESUBMISION_MASTER_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/primary-claim-resubmision-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{changeHealthPrimaryResubmisionMasterId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PrimaryClaimResubmisionMasterRepository primaryClaimResubmisionMasterRepository;

    @Autowired
    private PrimaryClaimResubmisionMasterMapper primaryClaimResubmisionMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private PrimaryClaimResubmisionMaster primaryClaimResubmisionMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PrimaryClaimResubmisionMaster createEntity(EntityManager em) {
        PrimaryClaimResubmisionMaster primaryClaimResubmisionMaster = new PrimaryClaimResubmisionMaster()
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
            .diagnosisCodeType(DEFAULT_DIAGNOSIS_CODE_TYPE)
            .primaryClaimResubmisionMasterUuid(DEFAULT_PRIMARY_CLAIM_RESUBMISION_MASTER_UUID);
        return primaryClaimResubmisionMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PrimaryClaimResubmisionMaster createUpdatedEntity(EntityManager em) {
        PrimaryClaimResubmisionMaster primaryClaimResubmisionMaster = new PrimaryClaimResubmisionMaster()
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
            .diagnosisCodeType(UPDATED_DIAGNOSIS_CODE_TYPE)
            .primaryClaimResubmisionMasterUuid(UPDATED_PRIMARY_CLAIM_RESUBMISION_MASTER_UUID);
        return primaryClaimResubmisionMaster;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(PrimaryClaimResubmisionMaster.class).block();
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
        primaryClaimResubmisionMaster = createEntity(em);
    }

    @Test
    void createPrimaryClaimResubmisionMaster() throws Exception {
        int databaseSizeBeforeCreate = primaryClaimResubmisionMasterRepository.findAll().collectList().block().size();
        // Create the PrimaryClaimResubmisionMaster
        PrimaryClaimResubmisionMasterDTO primaryClaimResubmisionMasterDTO = primaryClaimResubmisionMasterMapper.toDto(
            primaryClaimResubmisionMaster
        );
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(primaryClaimResubmisionMasterDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the PrimaryClaimResubmisionMaster in the database
        List<PrimaryClaimResubmisionMaster> primaryClaimResubmisionMasterList = primaryClaimResubmisionMasterRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimResubmisionMasterList).hasSize(databaseSizeBeforeCreate + 1);
        PrimaryClaimResubmisionMaster testPrimaryClaimResubmisionMaster = primaryClaimResubmisionMasterList.get(
            primaryClaimResubmisionMasterList.size() - 1
        );
        assertThat(testPrimaryClaimResubmisionMaster.getSalesOrderId()).isEqualTo(DEFAULT_SALES_ORDER_ID);
        assertThat(testPrimaryClaimResubmisionMaster.getClaimControlNo()).isEqualTo(DEFAULT_CLAIM_CONTROL_NO);
        assertThat(testPrimaryClaimResubmisionMaster.getTradingPartnerServiceId()).isEqualTo(DEFAULT_TRADING_PARTNER_SERVICE_ID);
        assertThat(testPrimaryClaimResubmisionMaster.getTradingPartnerName()).isEqualTo(DEFAULT_TRADING_PARTNER_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getSubmitterOrganizationName()).isEqualTo(DEFAULT_SUBMITTER_ORGANIZATION_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getSubmitterContactPersonName()).isEqualTo(DEFAULT_SUBMITTER_CONTACT_PERSON_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getSubmitterContactNo()).isEqualTo(DEFAULT_SUBMITTER_CONTACT_NO);
        assertThat(testPrimaryClaimResubmisionMaster.getReceiverOrganizationName()).isEqualTo(DEFAULT_RECEIVER_ORGANIZATION_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getSubscriberMemberIdNo()).isEqualTo(DEFAULT_SUBSCRIBER_MEMBER_ID_NO);
        assertThat(testPrimaryClaimResubmisionMaster.getSubscriberPaymentResponsibilityLevelCode())
            .isEqualTo(DEFAULT_SUBSCRIBER_PAYMENT_RESPONSIBILITY_LEVEL_CODE);
        assertThat(testPrimaryClaimResubmisionMaster.getSubscriberFirstName()).isEqualTo(DEFAULT_SUBSCRIBER_FIRST_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getSubscriberLastName()).isEqualTo(DEFAULT_SUBSCRIBER_LAST_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getSubscriberGender()).isEqualTo(DEFAULT_SUBSCRIBER_GENDER);
        assertThat(testPrimaryClaimResubmisionMaster.getSubscriberDob()).isEqualTo(DEFAULT_SUBSCRIBER_DOB);
        assertThat(testPrimaryClaimResubmisionMaster.getPrimaryInsurerPolicyNo()).isEqualTo(DEFAULT_PRIMARY_INSURER_POLICY_NO);
        assertThat(testPrimaryClaimResubmisionMaster.getSubscriberAddressLine1()).isEqualTo(DEFAULT_SUBSCRIBER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimResubmisionMaster.getSubscriberCity()).isEqualTo(DEFAULT_SUBSCRIBER_CITY);
        assertThat(testPrimaryClaimResubmisionMaster.getSubscriberState()).isEqualTo(DEFAULT_SUBSCRIBER_STATE);
        assertThat(testPrimaryClaimResubmisionMaster.getSubscriberZipCode()).isEqualTo(DEFAULT_SUBSCRIBER_ZIP_CODE);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderNpi()).isEqualTo(DEFAULT_BILLING_PROVIDER_NPI);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderEin()).isEqualTo(DEFAULT_BILLING_PROVIDER_EIN);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderOrganizationName())
            .isEqualTo(DEFAULT_BILLING_PROVIDER_ORGANIZATION_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderAddressLine1()).isEqualTo(DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderCity()).isEqualTo(DEFAULT_BILLING_PROVIDER_CITY);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderState()).isEqualTo(DEFAULT_BILLING_PROVIDER_STATE);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderZipCode()).isEqualTo(DEFAULT_BILLING_PROVIDER_ZIP_CODE);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderContactPersonName())
            .isEqualTo(DEFAULT_BILLING_PROVIDER_CONTACT_PERSON_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderContactNo()).isEqualTo(DEFAULT_BILLING_PROVIDER_CONTACT_NO);
        assertThat(testPrimaryClaimResubmisionMaster.getClaimFilingCode()).isEqualTo(DEFAULT_CLAIM_FILING_CODE);
        assertThat(testPrimaryClaimResubmisionMaster.getClaimChargeAmount()).isEqualTo(DEFAULT_CLAIM_CHARGE_AMOUNT);
        assertThat(testPrimaryClaimResubmisionMaster.getPosCode()).isEqualTo(DEFAULT_POS_CODE);
        assertThat(testPrimaryClaimResubmisionMaster.getClaimFrequencyCode()).isEqualTo(DEFAULT_CLAIM_FREQUENCY_CODE);
        assertThat(testPrimaryClaimResubmisionMaster.getSignatureIndicator()).isEqualTo(DEFAULT_SIGNATURE_INDICATOR);
        assertThat(testPrimaryClaimResubmisionMaster.getPlanParticipationCode()).isEqualTo(DEFAULT_PLAN_PARTICIPATION_CODE);
        assertThat(testPrimaryClaimResubmisionMaster.getBenefitsAssignmentCertificationIndicator())
            .isEqualTo(DEFAULT_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR);
        assertThat(testPrimaryClaimResubmisionMaster.getReleaseInformationCode()).isEqualTo(DEFAULT_RELEASE_INFORMATION_CODE);
        assertThat(testPrimaryClaimResubmisionMaster.getPrimaryDiagnosis()).isEqualTo(DEFAULT_PRIMARY_DIAGNOSIS);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode1()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_1);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode2()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_2);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode3()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_3);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode4()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_4);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode5()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_5);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode6()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_6);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode7()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_7);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode8()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_8);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode9()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_9);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode10()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_10);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode11()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_11);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode12()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_12);
        assertThat(testPrimaryClaimResubmisionMaster.getInsertedById()).isEqualTo(DEFAULT_INSERTED_BY_ID);
        assertThat(testPrimaryClaimResubmisionMaster.getInsertedDate()).isEqualTo(DEFAULT_INSERTED_DATE);
        assertThat(testPrimaryClaimResubmisionMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testPrimaryClaimResubmisionMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderType()).isEqualTo(DEFAULT_BILLING_PROVIDER_TYPE);
        assertThat(testPrimaryClaimResubmisionMaster.getInsertedByName()).isEqualTo(DEFAULT_INSERTED_BY_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderAddressLine2()).isEqualTo(DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimResubmisionMaster.getPayerClaimControlNumber()).isEqualTo(DEFAULT_PAYER_CLAIM_CONTROL_NUMBER);
        assertThat(testPrimaryClaimResubmisionMaster.getInsuredFirstName()).isEqualTo(DEFAULT_INSURED_FIRST_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getInsuredLastName()).isEqualTo(DEFAULT_INSURED_LAST_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getInsuredAddressLine1()).isEqualTo(DEFAULT_INSURED_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimResubmisionMaster.getInsuredAddressLine2()).isEqualTo(DEFAULT_INSURED_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimResubmisionMaster.getInsuredCity()).isEqualTo(DEFAULT_INSURED_CITY);
        assertThat(testPrimaryClaimResubmisionMaster.getInsuredState()).isEqualTo(DEFAULT_INSURED_STATE);
        assertThat(testPrimaryClaimResubmisionMaster.getInsuredZip()).isEqualTo(DEFAULT_INSURED_ZIP);
        assertThat(testPrimaryClaimResubmisionMaster.getInsuredContactNo()).isEqualTo(DEFAULT_INSURED_CONTACT_NO);
        assertThat(testPrimaryClaimResubmisionMaster.getInsuredDob()).isEqualTo(DEFAULT_INSURED_DOB);
        assertThat(testPrimaryClaimResubmisionMaster.getInsuredGender()).isEqualTo(DEFAULT_INSURED_GENDER);
        assertThat(testPrimaryClaimResubmisionMaster.getOrderingProviderFirstName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getOrderingProviderLastName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getOrderingProviderNpi()).isEqualTo(DEFAULT_ORDERING_PROVIDER_NPI);
        assertThat(testPrimaryClaimResubmisionMaster.getPatientRelationshipInsured()).isEqualTo(DEFAULT_PATIENT_RELATIONSHIP_INSURED);
        assertThat(testPrimaryClaimResubmisionMaster.getPatientConditionEmployment()).isEqualTo(DEFAULT_PATIENT_CONDITION_EMPLOYMENT);
        assertThat(testPrimaryClaimResubmisionMaster.getPatientConditionAutoAccident()).isEqualTo(DEFAULT_PATIENT_CONDITION_AUTO_ACCIDENT);
        assertThat(testPrimaryClaimResubmisionMaster.getPatientConditionOtherAccident())
            .isEqualTo(DEFAULT_PATIENT_CONDITION_OTHER_ACCIDENT);
        assertThat(testPrimaryClaimResubmisionMaster.getIsNextLevelInsurerPresentStatus())
            .isEqualTo(DEFAULT_IS_NEXT_LEVEL_INSURER_PRESENT_STATUS);
        assertThat(testPrimaryClaimResubmisionMaster.getOriginalDos()).isEqualTo(DEFAULT_ORIGINAL_DOS);
        assertThat(testPrimaryClaimResubmisionMaster.getParNo()).isEqualTo(DEFAULT_PAR_NO);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderTaxonomy()).isEqualTo(DEFAULT_BILLING_PROVIDER_TAXONOMY);
        assertThat(testPrimaryClaimResubmisionMaster.getServiceProviderNpi()).isEqualTo(DEFAULT_SERVICE_PROVIDER_NPI);
        assertThat(testPrimaryClaimResubmisionMaster.getServiceProviderOrganisationName())
            .isEqualTo(DEFAULT_SERVICE_PROVIDER_ORGANISATION_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getServiceProviderAddressLine1()).isEqualTo(DEFAULT_SERVICE_PROVIDER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimResubmisionMaster.getServiceProviderAddressLine2()).isEqualTo(DEFAULT_SERVICE_PROVIDER_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimResubmisionMaster.getServiceProviderCity()).isEqualTo(DEFAULT_SERVICE_PROVIDER_CITY);
        assertThat(testPrimaryClaimResubmisionMaster.getServiceProviderState()).isEqualTo(DEFAULT_SERVICE_PROVIDER_STATE);
        assertThat(testPrimaryClaimResubmisionMaster.getServiceProviderCountry()).isEqualTo(DEFAULT_SERVICE_PROVIDER_COUNTRY);
        assertThat(testPrimaryClaimResubmisionMaster.getServiceProviderZipCode()).isEqualTo(DEFAULT_SERVICE_PROVIDER_ZIP_CODE);
        assertThat(testPrimaryClaimResubmisionMaster.getServiceProviderTaxonomy()).isEqualTo(DEFAULT_SERVICE_PROVIDER_TAXONOMY);
        assertThat(testPrimaryClaimResubmisionMaster.getCms1500FormName()).isEqualTo(DEFAULT_CMS_1500_FORM_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getTradingPartnerAddressLine1()).isEqualTo(DEFAULT_TRADING_PARTNER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimResubmisionMaster.getTradingPartnerAddressLine2()).isEqualTo(DEFAULT_TRADING_PARTNER_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimResubmisionMaster.getTradingPartnerCity()).isEqualTo(DEFAULT_TRADING_PARTNER_CITY);
        assertThat(testPrimaryClaimResubmisionMaster.getTradingPartnerState()).isEqualTo(DEFAULT_TRADING_PARTNER_STATE);
        assertThat(testPrimaryClaimResubmisionMaster.getTradingPartnerZip()).isEqualTo(DEFAULT_TRADING_PARTNER_ZIP);
        assertThat(testPrimaryClaimResubmisionMaster.getDiagnosisCodeType()).isEqualTo(DEFAULT_DIAGNOSIS_CODE_TYPE);
        assertThat(testPrimaryClaimResubmisionMaster.getPrimaryClaimResubmisionMasterUuid())
            .isEqualTo(DEFAULT_PRIMARY_CLAIM_RESUBMISION_MASTER_UUID);
    }

    @Test
    void createPrimaryClaimResubmisionMasterWithExistingId() throws Exception {
        // Create the PrimaryClaimResubmisionMaster with an existing ID
        primaryClaimResubmisionMaster.setChangeHealthPrimaryResubmisionMasterId(1L);
        PrimaryClaimResubmisionMasterDTO primaryClaimResubmisionMasterDTO = primaryClaimResubmisionMasterMapper.toDto(
            primaryClaimResubmisionMaster
        );

        int databaseSizeBeforeCreate = primaryClaimResubmisionMasterRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(primaryClaimResubmisionMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PrimaryClaimResubmisionMaster in the database
        List<PrimaryClaimResubmisionMaster> primaryClaimResubmisionMasterList = primaryClaimResubmisionMasterRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimResubmisionMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllPrimaryClaimResubmisionMasters() {
        // Initialize the database
        primaryClaimResubmisionMasterRepository.save(primaryClaimResubmisionMaster).block();

        // Get all the primaryClaimResubmisionMasterList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=changeHealthPrimaryResubmisionMasterId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].changeHealthPrimaryResubmisionMasterId")
            .value(hasItem(primaryClaimResubmisionMaster.getChangeHealthPrimaryResubmisionMasterId().intValue()))
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
            .jsonPath("$.[*].primaryInsurerPolicyNo")
            .value(hasItem(DEFAULT_PRIMARY_INSURER_POLICY_NO))
            .jsonPath("$.[*].subscriberAddressLine1")
            .value(hasItem(DEFAULT_SUBSCRIBER_ADDRESS_LINE_1))
            .jsonPath("$.[*].subscriberCity")
            .value(hasItem(DEFAULT_SUBSCRIBER_CITY))
            .jsonPath("$.[*].subscriberState")
            .value(hasItem(DEFAULT_SUBSCRIBER_STATE))
            .jsonPath("$.[*].subscriberZipCode")
            .value(hasItem(DEFAULT_SUBSCRIBER_ZIP_CODE))
            .jsonPath("$.[*].billingProviderNpi")
            .value(hasItem(DEFAULT_BILLING_PROVIDER_NPI))
            .jsonPath("$.[*].billingProviderEin")
            .value(hasItem(DEFAULT_BILLING_PROVIDER_EIN))
            .jsonPath("$.[*].billingProviderOrganizationName")
            .value(hasItem(DEFAULT_BILLING_PROVIDER_ORGANIZATION_NAME))
            .jsonPath("$.[*].billingProviderAddressLine1")
            .value(hasItem(DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_1))
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
            .value(hasItem(DEFAULT_INSERTED_BY_ID.intValue()))
            .jsonPath("$.[*].insertedDate")
            .value(hasItem(DEFAULT_INSERTED_DATE.toString()))
            .jsonPath("$.[*].updatedById")
            .value(hasItem(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.[*].updatedDate")
            .value(hasItem(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.[*].billingProviderType")
            .value(hasItem(DEFAULT_BILLING_PROVIDER_TYPE))
            .jsonPath("$.[*].insertedByName")
            .value(hasItem(DEFAULT_INSERTED_BY_NAME))
            .jsonPath("$.[*].updatedByName")
            .value(hasItem(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS))
            .jsonPath("$.[*].billingProviderAddressLine2")
            .value(hasItem(DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_2))
            .jsonPath("$.[*].payerClaimControlNumber")
            .value(hasItem(DEFAULT_PAYER_CLAIM_CONTROL_NUMBER))
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
            .jsonPath("$.[*].primaryClaimResubmisionMasterUuid")
            .value(hasItem(DEFAULT_PRIMARY_CLAIM_RESUBMISION_MASTER_UUID.toString()));
    }

    @Test
    void getPrimaryClaimResubmisionMaster() {
        // Initialize the database
        primaryClaimResubmisionMasterRepository.save(primaryClaimResubmisionMaster).block();

        // Get the primaryClaimResubmisionMaster
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, primaryClaimResubmisionMaster.getChangeHealthPrimaryResubmisionMasterId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.changeHealthPrimaryResubmisionMasterId")
            .value(is(primaryClaimResubmisionMaster.getChangeHealthPrimaryResubmisionMasterId().intValue()))
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
            .jsonPath("$.primaryInsurerPolicyNo")
            .value(is(DEFAULT_PRIMARY_INSURER_POLICY_NO))
            .jsonPath("$.subscriberAddressLine1")
            .value(is(DEFAULT_SUBSCRIBER_ADDRESS_LINE_1))
            .jsonPath("$.subscriberCity")
            .value(is(DEFAULT_SUBSCRIBER_CITY))
            .jsonPath("$.subscriberState")
            .value(is(DEFAULT_SUBSCRIBER_STATE))
            .jsonPath("$.subscriberZipCode")
            .value(is(DEFAULT_SUBSCRIBER_ZIP_CODE))
            .jsonPath("$.billingProviderNpi")
            .value(is(DEFAULT_BILLING_PROVIDER_NPI))
            .jsonPath("$.billingProviderEin")
            .value(is(DEFAULT_BILLING_PROVIDER_EIN))
            .jsonPath("$.billingProviderOrganizationName")
            .value(is(DEFAULT_BILLING_PROVIDER_ORGANIZATION_NAME))
            .jsonPath("$.billingProviderAddressLine1")
            .value(is(DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_1))
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
            .value(is(DEFAULT_INSERTED_BY_ID.intValue()))
            .jsonPath("$.insertedDate")
            .value(is(DEFAULT_INSERTED_DATE.toString()))
            .jsonPath("$.updatedById")
            .value(is(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.updatedDate")
            .value(is(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.billingProviderType")
            .value(is(DEFAULT_BILLING_PROVIDER_TYPE))
            .jsonPath("$.insertedByName")
            .value(is(DEFAULT_INSERTED_BY_NAME))
            .jsonPath("$.updatedByName")
            .value(is(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS))
            .jsonPath("$.billingProviderAddressLine2")
            .value(is(DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_2))
            .jsonPath("$.payerClaimControlNumber")
            .value(is(DEFAULT_PAYER_CLAIM_CONTROL_NUMBER))
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
            .jsonPath("$.primaryClaimResubmisionMasterUuid")
            .value(is(DEFAULT_PRIMARY_CLAIM_RESUBMISION_MASTER_UUID.toString()));
    }

    @Test
    void getNonExistingPrimaryClaimResubmisionMaster() {
        // Get the primaryClaimResubmisionMaster
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putNewPrimaryClaimResubmisionMaster() throws Exception {
        // Initialize the database
        primaryClaimResubmisionMasterRepository.save(primaryClaimResubmisionMaster).block();

        int databaseSizeBeforeUpdate = primaryClaimResubmisionMasterRepository.findAll().collectList().block().size();

        // Update the primaryClaimResubmisionMaster
        PrimaryClaimResubmisionMaster updatedPrimaryClaimResubmisionMaster = primaryClaimResubmisionMasterRepository
            .findById(primaryClaimResubmisionMaster.getChangeHealthPrimaryResubmisionMasterId())
            .block();
        updatedPrimaryClaimResubmisionMaster
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
            .diagnosisCodeType(UPDATED_DIAGNOSIS_CODE_TYPE)
            .primaryClaimResubmisionMasterUuid(UPDATED_PRIMARY_CLAIM_RESUBMISION_MASTER_UUID);
        PrimaryClaimResubmisionMasterDTO primaryClaimResubmisionMasterDTO = primaryClaimResubmisionMasterMapper.toDto(
            updatedPrimaryClaimResubmisionMaster
        );

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, primaryClaimResubmisionMasterDTO.getChangeHealthPrimaryResubmisionMasterId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(primaryClaimResubmisionMasterDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PrimaryClaimResubmisionMaster in the database
        List<PrimaryClaimResubmisionMaster> primaryClaimResubmisionMasterList = primaryClaimResubmisionMasterRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimResubmisionMasterList).hasSize(databaseSizeBeforeUpdate);
        PrimaryClaimResubmisionMaster testPrimaryClaimResubmisionMaster = primaryClaimResubmisionMasterList.get(
            primaryClaimResubmisionMasterList.size() - 1
        );
        assertThat(testPrimaryClaimResubmisionMaster.getSalesOrderId()).isEqualTo(UPDATED_SALES_ORDER_ID);
        assertThat(testPrimaryClaimResubmisionMaster.getClaimControlNo()).isEqualTo(UPDATED_CLAIM_CONTROL_NO);
        assertThat(testPrimaryClaimResubmisionMaster.getTradingPartnerServiceId()).isEqualTo(UPDATED_TRADING_PARTNER_SERVICE_ID);
        assertThat(testPrimaryClaimResubmisionMaster.getTradingPartnerName()).isEqualTo(UPDATED_TRADING_PARTNER_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getSubmitterOrganizationName()).isEqualTo(UPDATED_SUBMITTER_ORGANIZATION_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getSubmitterContactPersonName()).isEqualTo(UPDATED_SUBMITTER_CONTACT_PERSON_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getSubmitterContactNo()).isEqualTo(UPDATED_SUBMITTER_CONTACT_NO);
        assertThat(testPrimaryClaimResubmisionMaster.getReceiverOrganizationName()).isEqualTo(UPDATED_RECEIVER_ORGANIZATION_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getSubscriberMemberIdNo()).isEqualTo(UPDATED_SUBSCRIBER_MEMBER_ID_NO);
        assertThat(testPrimaryClaimResubmisionMaster.getSubscriberPaymentResponsibilityLevelCode())
            .isEqualTo(UPDATED_SUBSCRIBER_PAYMENT_RESPONSIBILITY_LEVEL_CODE);
        assertThat(testPrimaryClaimResubmisionMaster.getSubscriberFirstName()).isEqualTo(UPDATED_SUBSCRIBER_FIRST_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getSubscriberLastName()).isEqualTo(UPDATED_SUBSCRIBER_LAST_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getSubscriberGender()).isEqualTo(UPDATED_SUBSCRIBER_GENDER);
        assertThat(testPrimaryClaimResubmisionMaster.getSubscriberDob()).isEqualTo(UPDATED_SUBSCRIBER_DOB);
        assertThat(testPrimaryClaimResubmisionMaster.getPrimaryInsurerPolicyNo()).isEqualTo(UPDATED_PRIMARY_INSURER_POLICY_NO);
        assertThat(testPrimaryClaimResubmisionMaster.getSubscriberAddressLine1()).isEqualTo(UPDATED_SUBSCRIBER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimResubmisionMaster.getSubscriberCity()).isEqualTo(UPDATED_SUBSCRIBER_CITY);
        assertThat(testPrimaryClaimResubmisionMaster.getSubscriberState()).isEqualTo(UPDATED_SUBSCRIBER_STATE);
        assertThat(testPrimaryClaimResubmisionMaster.getSubscriberZipCode()).isEqualTo(UPDATED_SUBSCRIBER_ZIP_CODE);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderNpi()).isEqualTo(UPDATED_BILLING_PROVIDER_NPI);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderEin()).isEqualTo(UPDATED_BILLING_PROVIDER_EIN);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderOrganizationName())
            .isEqualTo(UPDATED_BILLING_PROVIDER_ORGANIZATION_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderAddressLine1()).isEqualTo(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderCity()).isEqualTo(UPDATED_BILLING_PROVIDER_CITY);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderState()).isEqualTo(UPDATED_BILLING_PROVIDER_STATE);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderZipCode()).isEqualTo(UPDATED_BILLING_PROVIDER_ZIP_CODE);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderContactPersonName())
            .isEqualTo(UPDATED_BILLING_PROVIDER_CONTACT_PERSON_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderContactNo()).isEqualTo(UPDATED_BILLING_PROVIDER_CONTACT_NO);
        assertThat(testPrimaryClaimResubmisionMaster.getClaimFilingCode()).isEqualTo(UPDATED_CLAIM_FILING_CODE);
        assertThat(testPrimaryClaimResubmisionMaster.getClaimChargeAmount()).isEqualTo(UPDATED_CLAIM_CHARGE_AMOUNT);
        assertThat(testPrimaryClaimResubmisionMaster.getPosCode()).isEqualTo(UPDATED_POS_CODE);
        assertThat(testPrimaryClaimResubmisionMaster.getClaimFrequencyCode()).isEqualTo(UPDATED_CLAIM_FREQUENCY_CODE);
        assertThat(testPrimaryClaimResubmisionMaster.getSignatureIndicator()).isEqualTo(UPDATED_SIGNATURE_INDICATOR);
        assertThat(testPrimaryClaimResubmisionMaster.getPlanParticipationCode()).isEqualTo(UPDATED_PLAN_PARTICIPATION_CODE);
        assertThat(testPrimaryClaimResubmisionMaster.getBenefitsAssignmentCertificationIndicator())
            .isEqualTo(UPDATED_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR);
        assertThat(testPrimaryClaimResubmisionMaster.getReleaseInformationCode()).isEqualTo(UPDATED_RELEASE_INFORMATION_CODE);
        assertThat(testPrimaryClaimResubmisionMaster.getPrimaryDiagnosis()).isEqualTo(UPDATED_PRIMARY_DIAGNOSIS);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode1()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_1);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode2()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_2);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode3()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_3);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode4()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_4);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode5()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_5);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode6()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_6);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode7()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_7);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode8()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_8);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode9()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_9);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode10()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_10);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode11()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_11);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode12()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_12);
        assertThat(testPrimaryClaimResubmisionMaster.getInsertedById()).isEqualTo(UPDATED_INSERTED_BY_ID);
        assertThat(testPrimaryClaimResubmisionMaster.getInsertedDate()).isEqualTo(UPDATED_INSERTED_DATE);
        assertThat(testPrimaryClaimResubmisionMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPrimaryClaimResubmisionMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderType()).isEqualTo(UPDATED_BILLING_PROVIDER_TYPE);
        assertThat(testPrimaryClaimResubmisionMaster.getInsertedByName()).isEqualTo(UPDATED_INSERTED_BY_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderAddressLine2()).isEqualTo(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimResubmisionMaster.getPayerClaimControlNumber()).isEqualTo(UPDATED_PAYER_CLAIM_CONTROL_NUMBER);
        assertThat(testPrimaryClaimResubmisionMaster.getInsuredFirstName()).isEqualTo(UPDATED_INSURED_FIRST_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getInsuredLastName()).isEqualTo(UPDATED_INSURED_LAST_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getInsuredAddressLine1()).isEqualTo(UPDATED_INSURED_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimResubmisionMaster.getInsuredAddressLine2()).isEqualTo(UPDATED_INSURED_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimResubmisionMaster.getInsuredCity()).isEqualTo(UPDATED_INSURED_CITY);
        assertThat(testPrimaryClaimResubmisionMaster.getInsuredState()).isEqualTo(UPDATED_INSURED_STATE);
        assertThat(testPrimaryClaimResubmisionMaster.getInsuredZip()).isEqualTo(UPDATED_INSURED_ZIP);
        assertThat(testPrimaryClaimResubmisionMaster.getInsuredContactNo()).isEqualTo(UPDATED_INSURED_CONTACT_NO);
        assertThat(testPrimaryClaimResubmisionMaster.getInsuredDob()).isEqualTo(UPDATED_INSURED_DOB);
        assertThat(testPrimaryClaimResubmisionMaster.getInsuredGender()).isEqualTo(UPDATED_INSURED_GENDER);
        assertThat(testPrimaryClaimResubmisionMaster.getOrderingProviderFirstName()).isEqualTo(UPDATED_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getOrderingProviderLastName()).isEqualTo(UPDATED_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getOrderingProviderNpi()).isEqualTo(UPDATED_ORDERING_PROVIDER_NPI);
        assertThat(testPrimaryClaimResubmisionMaster.getPatientRelationshipInsured()).isEqualTo(UPDATED_PATIENT_RELATIONSHIP_INSURED);
        assertThat(testPrimaryClaimResubmisionMaster.getPatientConditionEmployment()).isEqualTo(UPDATED_PATIENT_CONDITION_EMPLOYMENT);
        assertThat(testPrimaryClaimResubmisionMaster.getPatientConditionAutoAccident()).isEqualTo(UPDATED_PATIENT_CONDITION_AUTO_ACCIDENT);
        assertThat(testPrimaryClaimResubmisionMaster.getPatientConditionOtherAccident())
            .isEqualTo(UPDATED_PATIENT_CONDITION_OTHER_ACCIDENT);
        assertThat(testPrimaryClaimResubmisionMaster.getIsNextLevelInsurerPresentStatus())
            .isEqualTo(UPDATED_IS_NEXT_LEVEL_INSURER_PRESENT_STATUS);
        assertThat(testPrimaryClaimResubmisionMaster.getOriginalDos()).isEqualTo(UPDATED_ORIGINAL_DOS);
        assertThat(testPrimaryClaimResubmisionMaster.getParNo()).isEqualTo(UPDATED_PAR_NO);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderTaxonomy()).isEqualTo(UPDATED_BILLING_PROVIDER_TAXONOMY);
        assertThat(testPrimaryClaimResubmisionMaster.getServiceProviderNpi()).isEqualTo(UPDATED_SERVICE_PROVIDER_NPI);
        assertThat(testPrimaryClaimResubmisionMaster.getServiceProviderOrganisationName())
            .isEqualTo(UPDATED_SERVICE_PROVIDER_ORGANISATION_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getServiceProviderAddressLine1()).isEqualTo(UPDATED_SERVICE_PROVIDER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimResubmisionMaster.getServiceProviderAddressLine2()).isEqualTo(UPDATED_SERVICE_PROVIDER_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimResubmisionMaster.getServiceProviderCity()).isEqualTo(UPDATED_SERVICE_PROVIDER_CITY);
        assertThat(testPrimaryClaimResubmisionMaster.getServiceProviderState()).isEqualTo(UPDATED_SERVICE_PROVIDER_STATE);
        assertThat(testPrimaryClaimResubmisionMaster.getServiceProviderCountry()).isEqualTo(UPDATED_SERVICE_PROVIDER_COUNTRY);
        assertThat(testPrimaryClaimResubmisionMaster.getServiceProviderZipCode()).isEqualTo(UPDATED_SERVICE_PROVIDER_ZIP_CODE);
        assertThat(testPrimaryClaimResubmisionMaster.getServiceProviderTaxonomy()).isEqualTo(UPDATED_SERVICE_PROVIDER_TAXONOMY);
        assertThat(testPrimaryClaimResubmisionMaster.getCms1500FormName()).isEqualTo(UPDATED_CMS_1500_FORM_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getTradingPartnerAddressLine1()).isEqualTo(UPDATED_TRADING_PARTNER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimResubmisionMaster.getTradingPartnerAddressLine2()).isEqualTo(UPDATED_TRADING_PARTNER_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimResubmisionMaster.getTradingPartnerCity()).isEqualTo(UPDATED_TRADING_PARTNER_CITY);
        assertThat(testPrimaryClaimResubmisionMaster.getTradingPartnerState()).isEqualTo(UPDATED_TRADING_PARTNER_STATE);
        assertThat(testPrimaryClaimResubmisionMaster.getTradingPartnerZip()).isEqualTo(UPDATED_TRADING_PARTNER_ZIP);
        assertThat(testPrimaryClaimResubmisionMaster.getDiagnosisCodeType()).isEqualTo(UPDATED_DIAGNOSIS_CODE_TYPE);
        assertThat(testPrimaryClaimResubmisionMaster.getPrimaryClaimResubmisionMasterUuid())
            .isEqualTo(UPDATED_PRIMARY_CLAIM_RESUBMISION_MASTER_UUID);
    }

    @Test
    void putNonExistingPrimaryClaimResubmisionMaster() throws Exception {
        int databaseSizeBeforeUpdate = primaryClaimResubmisionMasterRepository.findAll().collectList().block().size();
        primaryClaimResubmisionMaster.setChangeHealthPrimaryResubmisionMasterId(count.incrementAndGet());

        // Create the PrimaryClaimResubmisionMaster
        PrimaryClaimResubmisionMasterDTO primaryClaimResubmisionMasterDTO = primaryClaimResubmisionMasterMapper.toDto(
            primaryClaimResubmisionMaster
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, primaryClaimResubmisionMasterDTO.getChangeHealthPrimaryResubmisionMasterId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(primaryClaimResubmisionMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PrimaryClaimResubmisionMaster in the database
        List<PrimaryClaimResubmisionMaster> primaryClaimResubmisionMasterList = primaryClaimResubmisionMasterRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimResubmisionMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchPrimaryClaimResubmisionMaster() throws Exception {
        int databaseSizeBeforeUpdate = primaryClaimResubmisionMasterRepository.findAll().collectList().block().size();
        primaryClaimResubmisionMaster.setChangeHealthPrimaryResubmisionMasterId(count.incrementAndGet());

        // Create the PrimaryClaimResubmisionMaster
        PrimaryClaimResubmisionMasterDTO primaryClaimResubmisionMasterDTO = primaryClaimResubmisionMasterMapper.toDto(
            primaryClaimResubmisionMaster
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(primaryClaimResubmisionMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PrimaryClaimResubmisionMaster in the database
        List<PrimaryClaimResubmisionMaster> primaryClaimResubmisionMasterList = primaryClaimResubmisionMasterRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimResubmisionMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamPrimaryClaimResubmisionMaster() throws Exception {
        int databaseSizeBeforeUpdate = primaryClaimResubmisionMasterRepository.findAll().collectList().block().size();
        primaryClaimResubmisionMaster.setChangeHealthPrimaryResubmisionMasterId(count.incrementAndGet());

        // Create the PrimaryClaimResubmisionMaster
        PrimaryClaimResubmisionMasterDTO primaryClaimResubmisionMasterDTO = primaryClaimResubmisionMasterMapper.toDto(
            primaryClaimResubmisionMaster
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(primaryClaimResubmisionMasterDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PrimaryClaimResubmisionMaster in the database
        List<PrimaryClaimResubmisionMaster> primaryClaimResubmisionMasterList = primaryClaimResubmisionMasterRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimResubmisionMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdatePrimaryClaimResubmisionMasterWithPatch() throws Exception {
        // Initialize the database
        primaryClaimResubmisionMasterRepository.save(primaryClaimResubmisionMaster).block();

        int databaseSizeBeforeUpdate = primaryClaimResubmisionMasterRepository.findAll().collectList().block().size();

        // Update the primaryClaimResubmisionMaster using partial update
        PrimaryClaimResubmisionMaster partialUpdatedPrimaryClaimResubmisionMaster = new PrimaryClaimResubmisionMaster();
        partialUpdatedPrimaryClaimResubmisionMaster.setChangeHealthPrimaryResubmisionMasterId(
            primaryClaimResubmisionMaster.getChangeHealthPrimaryResubmisionMasterId()
        );

        partialUpdatedPrimaryClaimResubmisionMaster
            .claimControlNo(UPDATED_CLAIM_CONTROL_NO)
            .tradingPartnerServiceId(UPDATED_TRADING_PARTNER_SERVICE_ID)
            .submitterContactNo(UPDATED_SUBMITTER_CONTACT_NO)
            .receiverOrganizationName(UPDATED_RECEIVER_ORGANIZATION_NAME)
            .subscriberMemberIdNo(UPDATED_SUBSCRIBER_MEMBER_ID_NO)
            .subscriberPaymentResponsibilityLevelCode(UPDATED_SUBSCRIBER_PAYMENT_RESPONSIBILITY_LEVEL_CODE)
            .subscriberFirstName(UPDATED_SUBSCRIBER_FIRST_NAME)
            .subscriberDob(UPDATED_SUBSCRIBER_DOB)
            .subscriberCity(UPDATED_SUBSCRIBER_CITY)
            .subscriberState(UPDATED_SUBSCRIBER_STATE)
            .subscriberZipCode(UPDATED_SUBSCRIBER_ZIP_CODE)
            .billingProviderAddressLine1(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_1)
            .billingProviderCity(UPDATED_BILLING_PROVIDER_CITY)
            .billingProviderZipCode(UPDATED_BILLING_PROVIDER_ZIP_CODE)
            .claimFilingCode(UPDATED_CLAIM_FILING_CODE)
            .posCode(UPDATED_POS_CODE)
            .claimFrequencyCode(UPDATED_CLAIM_FREQUENCY_CODE)
            .signatureIndicator(UPDATED_SIGNATURE_INDICATOR)
            .planParticipationCode(UPDATED_PLAN_PARTICIPATION_CODE)
            .benefitsAssignmentCertificationIndicator(UPDATED_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR)
            .releaseInformationCode(UPDATED_RELEASE_INFORMATION_CODE)
            .icd10DiagnosisCode1(UPDATED_ICD_10_DIAGNOSIS_CODE_1)
            .icd10DiagnosisCode5(UPDATED_ICD_10_DIAGNOSIS_CODE_5)
            .icd10DiagnosisCode6(UPDATED_ICD_10_DIAGNOSIS_CODE_6)
            .icd10DiagnosisCode7(UPDATED_ICD_10_DIAGNOSIS_CODE_7)
            .icd10DiagnosisCode9(UPDATED_ICD_10_DIAGNOSIS_CODE_9)
            .icd10DiagnosisCode12(UPDATED_ICD_10_DIAGNOSIS_CODE_12)
            .insertedDate(UPDATED_INSERTED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .insertedByName(UPDATED_INSERTED_BY_NAME)
            .billingProviderAddressLine2(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_2)
            .insuredLastName(UPDATED_INSURED_LAST_NAME)
            .insuredAddressLine2(UPDATED_INSURED_ADDRESS_LINE_2)
            .insuredZip(UPDATED_INSURED_ZIP)
            .orderingProviderNpi(UPDATED_ORDERING_PROVIDER_NPI)
            .patientRelationshipInsured(UPDATED_PATIENT_RELATIONSHIP_INSURED)
            .patientConditionOtherAccident(UPDATED_PATIENT_CONDITION_OTHER_ACCIDENT)
            .isNextLevelInsurerPresentStatus(UPDATED_IS_NEXT_LEVEL_INSURER_PRESENT_STATUS)
            .originalDos(UPDATED_ORIGINAL_DOS)
            .parNo(UPDATED_PAR_NO)
            .billingProviderTaxonomy(UPDATED_BILLING_PROVIDER_TAXONOMY)
            .serviceProviderNpi(UPDATED_SERVICE_PROVIDER_NPI)
            .serviceProviderOrganisationName(UPDATED_SERVICE_PROVIDER_ORGANISATION_NAME)
            .serviceProviderAddressLine1(UPDATED_SERVICE_PROVIDER_ADDRESS_LINE_1)
            .serviceProviderAddressLine2(UPDATED_SERVICE_PROVIDER_ADDRESS_LINE_2)
            .serviceProviderState(UPDATED_SERVICE_PROVIDER_STATE)
            .serviceProviderZipCode(UPDATED_SERVICE_PROVIDER_ZIP_CODE)
            .serviceProviderTaxonomy(UPDATED_SERVICE_PROVIDER_TAXONOMY)
            .tradingPartnerAddressLine2(UPDATED_TRADING_PARTNER_ADDRESS_LINE_2)
            .tradingPartnerState(UPDATED_TRADING_PARTNER_STATE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPrimaryClaimResubmisionMaster.getChangeHealthPrimaryResubmisionMasterId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPrimaryClaimResubmisionMaster))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PrimaryClaimResubmisionMaster in the database
        List<PrimaryClaimResubmisionMaster> primaryClaimResubmisionMasterList = primaryClaimResubmisionMasterRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimResubmisionMasterList).hasSize(databaseSizeBeforeUpdate);
        PrimaryClaimResubmisionMaster testPrimaryClaimResubmisionMaster = primaryClaimResubmisionMasterList.get(
            primaryClaimResubmisionMasterList.size() - 1
        );
        assertThat(testPrimaryClaimResubmisionMaster.getSalesOrderId()).isEqualTo(DEFAULT_SALES_ORDER_ID);
        assertThat(testPrimaryClaimResubmisionMaster.getClaimControlNo()).isEqualTo(UPDATED_CLAIM_CONTROL_NO);
        assertThat(testPrimaryClaimResubmisionMaster.getTradingPartnerServiceId()).isEqualTo(UPDATED_TRADING_PARTNER_SERVICE_ID);
        assertThat(testPrimaryClaimResubmisionMaster.getTradingPartnerName()).isEqualTo(DEFAULT_TRADING_PARTNER_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getSubmitterOrganizationName()).isEqualTo(DEFAULT_SUBMITTER_ORGANIZATION_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getSubmitterContactPersonName()).isEqualTo(DEFAULT_SUBMITTER_CONTACT_PERSON_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getSubmitterContactNo()).isEqualTo(UPDATED_SUBMITTER_CONTACT_NO);
        assertThat(testPrimaryClaimResubmisionMaster.getReceiverOrganizationName()).isEqualTo(UPDATED_RECEIVER_ORGANIZATION_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getSubscriberMemberIdNo()).isEqualTo(UPDATED_SUBSCRIBER_MEMBER_ID_NO);
        assertThat(testPrimaryClaimResubmisionMaster.getSubscriberPaymentResponsibilityLevelCode())
            .isEqualTo(UPDATED_SUBSCRIBER_PAYMENT_RESPONSIBILITY_LEVEL_CODE);
        assertThat(testPrimaryClaimResubmisionMaster.getSubscriberFirstName()).isEqualTo(UPDATED_SUBSCRIBER_FIRST_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getSubscriberLastName()).isEqualTo(DEFAULT_SUBSCRIBER_LAST_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getSubscriberGender()).isEqualTo(DEFAULT_SUBSCRIBER_GENDER);
        assertThat(testPrimaryClaimResubmisionMaster.getSubscriberDob()).isEqualTo(UPDATED_SUBSCRIBER_DOB);
        assertThat(testPrimaryClaimResubmisionMaster.getPrimaryInsurerPolicyNo()).isEqualTo(DEFAULT_PRIMARY_INSURER_POLICY_NO);
        assertThat(testPrimaryClaimResubmisionMaster.getSubscriberAddressLine1()).isEqualTo(DEFAULT_SUBSCRIBER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimResubmisionMaster.getSubscriberCity()).isEqualTo(UPDATED_SUBSCRIBER_CITY);
        assertThat(testPrimaryClaimResubmisionMaster.getSubscriberState()).isEqualTo(UPDATED_SUBSCRIBER_STATE);
        assertThat(testPrimaryClaimResubmisionMaster.getSubscriberZipCode()).isEqualTo(UPDATED_SUBSCRIBER_ZIP_CODE);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderNpi()).isEqualTo(DEFAULT_BILLING_PROVIDER_NPI);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderEin()).isEqualTo(DEFAULT_BILLING_PROVIDER_EIN);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderOrganizationName())
            .isEqualTo(DEFAULT_BILLING_PROVIDER_ORGANIZATION_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderAddressLine1()).isEqualTo(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderCity()).isEqualTo(UPDATED_BILLING_PROVIDER_CITY);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderState()).isEqualTo(DEFAULT_BILLING_PROVIDER_STATE);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderZipCode()).isEqualTo(UPDATED_BILLING_PROVIDER_ZIP_CODE);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderContactPersonName())
            .isEqualTo(DEFAULT_BILLING_PROVIDER_CONTACT_PERSON_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderContactNo()).isEqualTo(DEFAULT_BILLING_PROVIDER_CONTACT_NO);
        assertThat(testPrimaryClaimResubmisionMaster.getClaimFilingCode()).isEqualTo(UPDATED_CLAIM_FILING_CODE);
        assertThat(testPrimaryClaimResubmisionMaster.getClaimChargeAmount()).isEqualTo(DEFAULT_CLAIM_CHARGE_AMOUNT);
        assertThat(testPrimaryClaimResubmisionMaster.getPosCode()).isEqualTo(UPDATED_POS_CODE);
        assertThat(testPrimaryClaimResubmisionMaster.getClaimFrequencyCode()).isEqualTo(UPDATED_CLAIM_FREQUENCY_CODE);
        assertThat(testPrimaryClaimResubmisionMaster.getSignatureIndicator()).isEqualTo(UPDATED_SIGNATURE_INDICATOR);
        assertThat(testPrimaryClaimResubmisionMaster.getPlanParticipationCode()).isEqualTo(UPDATED_PLAN_PARTICIPATION_CODE);
        assertThat(testPrimaryClaimResubmisionMaster.getBenefitsAssignmentCertificationIndicator())
            .isEqualTo(UPDATED_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR);
        assertThat(testPrimaryClaimResubmisionMaster.getReleaseInformationCode()).isEqualTo(UPDATED_RELEASE_INFORMATION_CODE);
        assertThat(testPrimaryClaimResubmisionMaster.getPrimaryDiagnosis()).isEqualTo(DEFAULT_PRIMARY_DIAGNOSIS);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode1()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_1);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode2()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_2);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode3()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_3);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode4()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_4);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode5()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_5);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode6()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_6);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode7()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_7);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode8()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_8);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode9()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_9);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode10()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_10);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode11()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_11);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode12()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_12);
        assertThat(testPrimaryClaimResubmisionMaster.getInsertedById()).isEqualTo(DEFAULT_INSERTED_BY_ID);
        assertThat(testPrimaryClaimResubmisionMaster.getInsertedDate()).isEqualTo(UPDATED_INSERTED_DATE);
        assertThat(testPrimaryClaimResubmisionMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPrimaryClaimResubmisionMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderType()).isEqualTo(DEFAULT_BILLING_PROVIDER_TYPE);
        assertThat(testPrimaryClaimResubmisionMaster.getInsertedByName()).isEqualTo(UPDATED_INSERTED_BY_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderAddressLine2()).isEqualTo(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimResubmisionMaster.getPayerClaimControlNumber()).isEqualTo(DEFAULT_PAYER_CLAIM_CONTROL_NUMBER);
        assertThat(testPrimaryClaimResubmisionMaster.getInsuredFirstName()).isEqualTo(DEFAULT_INSURED_FIRST_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getInsuredLastName()).isEqualTo(UPDATED_INSURED_LAST_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getInsuredAddressLine1()).isEqualTo(DEFAULT_INSURED_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimResubmisionMaster.getInsuredAddressLine2()).isEqualTo(UPDATED_INSURED_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimResubmisionMaster.getInsuredCity()).isEqualTo(DEFAULT_INSURED_CITY);
        assertThat(testPrimaryClaimResubmisionMaster.getInsuredState()).isEqualTo(DEFAULT_INSURED_STATE);
        assertThat(testPrimaryClaimResubmisionMaster.getInsuredZip()).isEqualTo(UPDATED_INSURED_ZIP);
        assertThat(testPrimaryClaimResubmisionMaster.getInsuredContactNo()).isEqualTo(DEFAULT_INSURED_CONTACT_NO);
        assertThat(testPrimaryClaimResubmisionMaster.getInsuredDob()).isEqualTo(DEFAULT_INSURED_DOB);
        assertThat(testPrimaryClaimResubmisionMaster.getInsuredGender()).isEqualTo(DEFAULT_INSURED_GENDER);
        assertThat(testPrimaryClaimResubmisionMaster.getOrderingProviderFirstName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getOrderingProviderLastName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getOrderingProviderNpi()).isEqualTo(UPDATED_ORDERING_PROVIDER_NPI);
        assertThat(testPrimaryClaimResubmisionMaster.getPatientRelationshipInsured()).isEqualTo(UPDATED_PATIENT_RELATIONSHIP_INSURED);
        assertThat(testPrimaryClaimResubmisionMaster.getPatientConditionEmployment()).isEqualTo(DEFAULT_PATIENT_CONDITION_EMPLOYMENT);
        assertThat(testPrimaryClaimResubmisionMaster.getPatientConditionAutoAccident()).isEqualTo(DEFAULT_PATIENT_CONDITION_AUTO_ACCIDENT);
        assertThat(testPrimaryClaimResubmisionMaster.getPatientConditionOtherAccident())
            .isEqualTo(UPDATED_PATIENT_CONDITION_OTHER_ACCIDENT);
        assertThat(testPrimaryClaimResubmisionMaster.getIsNextLevelInsurerPresentStatus())
            .isEqualTo(UPDATED_IS_NEXT_LEVEL_INSURER_PRESENT_STATUS);
        assertThat(testPrimaryClaimResubmisionMaster.getOriginalDos()).isEqualTo(UPDATED_ORIGINAL_DOS);
        assertThat(testPrimaryClaimResubmisionMaster.getParNo()).isEqualTo(UPDATED_PAR_NO);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderTaxonomy()).isEqualTo(UPDATED_BILLING_PROVIDER_TAXONOMY);
        assertThat(testPrimaryClaimResubmisionMaster.getServiceProviderNpi()).isEqualTo(UPDATED_SERVICE_PROVIDER_NPI);
        assertThat(testPrimaryClaimResubmisionMaster.getServiceProviderOrganisationName())
            .isEqualTo(UPDATED_SERVICE_PROVIDER_ORGANISATION_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getServiceProviderAddressLine1()).isEqualTo(UPDATED_SERVICE_PROVIDER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimResubmisionMaster.getServiceProviderAddressLine2()).isEqualTo(UPDATED_SERVICE_PROVIDER_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimResubmisionMaster.getServiceProviderCity()).isEqualTo(DEFAULT_SERVICE_PROVIDER_CITY);
        assertThat(testPrimaryClaimResubmisionMaster.getServiceProviderState()).isEqualTo(UPDATED_SERVICE_PROVIDER_STATE);
        assertThat(testPrimaryClaimResubmisionMaster.getServiceProviderCountry()).isEqualTo(DEFAULT_SERVICE_PROVIDER_COUNTRY);
        assertThat(testPrimaryClaimResubmisionMaster.getServiceProviderZipCode()).isEqualTo(UPDATED_SERVICE_PROVIDER_ZIP_CODE);
        assertThat(testPrimaryClaimResubmisionMaster.getServiceProviderTaxonomy()).isEqualTo(UPDATED_SERVICE_PROVIDER_TAXONOMY);
        assertThat(testPrimaryClaimResubmisionMaster.getCms1500FormName()).isEqualTo(DEFAULT_CMS_1500_FORM_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getTradingPartnerAddressLine1()).isEqualTo(DEFAULT_TRADING_PARTNER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimResubmisionMaster.getTradingPartnerAddressLine2()).isEqualTo(UPDATED_TRADING_PARTNER_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimResubmisionMaster.getTradingPartnerCity()).isEqualTo(DEFAULT_TRADING_PARTNER_CITY);
        assertThat(testPrimaryClaimResubmisionMaster.getTradingPartnerState()).isEqualTo(UPDATED_TRADING_PARTNER_STATE);
        assertThat(testPrimaryClaimResubmisionMaster.getTradingPartnerZip()).isEqualTo(DEFAULT_TRADING_PARTNER_ZIP);
        assertThat(testPrimaryClaimResubmisionMaster.getDiagnosisCodeType()).isEqualTo(DEFAULT_DIAGNOSIS_CODE_TYPE);
        assertThat(testPrimaryClaimResubmisionMaster.getPrimaryClaimResubmisionMasterUuid())
            .isEqualTo(DEFAULT_PRIMARY_CLAIM_RESUBMISION_MASTER_UUID);
    }

    @Test
    void fullUpdatePrimaryClaimResubmisionMasterWithPatch() throws Exception {
        // Initialize the database
        primaryClaimResubmisionMasterRepository.save(primaryClaimResubmisionMaster).block();

        int databaseSizeBeforeUpdate = primaryClaimResubmisionMasterRepository.findAll().collectList().block().size();

        // Update the primaryClaimResubmisionMaster using partial update
        PrimaryClaimResubmisionMaster partialUpdatedPrimaryClaimResubmisionMaster = new PrimaryClaimResubmisionMaster();
        partialUpdatedPrimaryClaimResubmisionMaster.setChangeHealthPrimaryResubmisionMasterId(
            primaryClaimResubmisionMaster.getChangeHealthPrimaryResubmisionMasterId()
        );

        partialUpdatedPrimaryClaimResubmisionMaster
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
            .diagnosisCodeType(UPDATED_DIAGNOSIS_CODE_TYPE)
            .primaryClaimResubmisionMasterUuid(UPDATED_PRIMARY_CLAIM_RESUBMISION_MASTER_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPrimaryClaimResubmisionMaster.getChangeHealthPrimaryResubmisionMasterId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPrimaryClaimResubmisionMaster))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PrimaryClaimResubmisionMaster in the database
        List<PrimaryClaimResubmisionMaster> primaryClaimResubmisionMasterList = primaryClaimResubmisionMasterRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimResubmisionMasterList).hasSize(databaseSizeBeforeUpdate);
        PrimaryClaimResubmisionMaster testPrimaryClaimResubmisionMaster = primaryClaimResubmisionMasterList.get(
            primaryClaimResubmisionMasterList.size() - 1
        );
        assertThat(testPrimaryClaimResubmisionMaster.getSalesOrderId()).isEqualTo(UPDATED_SALES_ORDER_ID);
        assertThat(testPrimaryClaimResubmisionMaster.getClaimControlNo()).isEqualTo(UPDATED_CLAIM_CONTROL_NO);
        assertThat(testPrimaryClaimResubmisionMaster.getTradingPartnerServiceId()).isEqualTo(UPDATED_TRADING_PARTNER_SERVICE_ID);
        assertThat(testPrimaryClaimResubmisionMaster.getTradingPartnerName()).isEqualTo(UPDATED_TRADING_PARTNER_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getSubmitterOrganizationName()).isEqualTo(UPDATED_SUBMITTER_ORGANIZATION_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getSubmitterContactPersonName()).isEqualTo(UPDATED_SUBMITTER_CONTACT_PERSON_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getSubmitterContactNo()).isEqualTo(UPDATED_SUBMITTER_CONTACT_NO);
        assertThat(testPrimaryClaimResubmisionMaster.getReceiverOrganizationName()).isEqualTo(UPDATED_RECEIVER_ORGANIZATION_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getSubscriberMemberIdNo()).isEqualTo(UPDATED_SUBSCRIBER_MEMBER_ID_NO);
        assertThat(testPrimaryClaimResubmisionMaster.getSubscriberPaymentResponsibilityLevelCode())
            .isEqualTo(UPDATED_SUBSCRIBER_PAYMENT_RESPONSIBILITY_LEVEL_CODE);
        assertThat(testPrimaryClaimResubmisionMaster.getSubscriberFirstName()).isEqualTo(UPDATED_SUBSCRIBER_FIRST_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getSubscriberLastName()).isEqualTo(UPDATED_SUBSCRIBER_LAST_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getSubscriberGender()).isEqualTo(UPDATED_SUBSCRIBER_GENDER);
        assertThat(testPrimaryClaimResubmisionMaster.getSubscriberDob()).isEqualTo(UPDATED_SUBSCRIBER_DOB);
        assertThat(testPrimaryClaimResubmisionMaster.getPrimaryInsurerPolicyNo()).isEqualTo(UPDATED_PRIMARY_INSURER_POLICY_NO);
        assertThat(testPrimaryClaimResubmisionMaster.getSubscriberAddressLine1()).isEqualTo(UPDATED_SUBSCRIBER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimResubmisionMaster.getSubscriberCity()).isEqualTo(UPDATED_SUBSCRIBER_CITY);
        assertThat(testPrimaryClaimResubmisionMaster.getSubscriberState()).isEqualTo(UPDATED_SUBSCRIBER_STATE);
        assertThat(testPrimaryClaimResubmisionMaster.getSubscriberZipCode()).isEqualTo(UPDATED_SUBSCRIBER_ZIP_CODE);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderNpi()).isEqualTo(UPDATED_BILLING_PROVIDER_NPI);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderEin()).isEqualTo(UPDATED_BILLING_PROVIDER_EIN);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderOrganizationName())
            .isEqualTo(UPDATED_BILLING_PROVIDER_ORGANIZATION_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderAddressLine1()).isEqualTo(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderCity()).isEqualTo(UPDATED_BILLING_PROVIDER_CITY);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderState()).isEqualTo(UPDATED_BILLING_PROVIDER_STATE);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderZipCode()).isEqualTo(UPDATED_BILLING_PROVIDER_ZIP_CODE);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderContactPersonName())
            .isEqualTo(UPDATED_BILLING_PROVIDER_CONTACT_PERSON_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderContactNo()).isEqualTo(UPDATED_BILLING_PROVIDER_CONTACT_NO);
        assertThat(testPrimaryClaimResubmisionMaster.getClaimFilingCode()).isEqualTo(UPDATED_CLAIM_FILING_CODE);
        assertThat(testPrimaryClaimResubmisionMaster.getClaimChargeAmount()).isEqualTo(UPDATED_CLAIM_CHARGE_AMOUNT);
        assertThat(testPrimaryClaimResubmisionMaster.getPosCode()).isEqualTo(UPDATED_POS_CODE);
        assertThat(testPrimaryClaimResubmisionMaster.getClaimFrequencyCode()).isEqualTo(UPDATED_CLAIM_FREQUENCY_CODE);
        assertThat(testPrimaryClaimResubmisionMaster.getSignatureIndicator()).isEqualTo(UPDATED_SIGNATURE_INDICATOR);
        assertThat(testPrimaryClaimResubmisionMaster.getPlanParticipationCode()).isEqualTo(UPDATED_PLAN_PARTICIPATION_CODE);
        assertThat(testPrimaryClaimResubmisionMaster.getBenefitsAssignmentCertificationIndicator())
            .isEqualTo(UPDATED_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR);
        assertThat(testPrimaryClaimResubmisionMaster.getReleaseInformationCode()).isEqualTo(UPDATED_RELEASE_INFORMATION_CODE);
        assertThat(testPrimaryClaimResubmisionMaster.getPrimaryDiagnosis()).isEqualTo(UPDATED_PRIMARY_DIAGNOSIS);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode1()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_1);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode2()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_2);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode3()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_3);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode4()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_4);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode5()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_5);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode6()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_6);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode7()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_7);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode8()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_8);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode9()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_9);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode10()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_10);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode11()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_11);
        assertThat(testPrimaryClaimResubmisionMaster.getIcd10DiagnosisCode12()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_12);
        assertThat(testPrimaryClaimResubmisionMaster.getInsertedById()).isEqualTo(UPDATED_INSERTED_BY_ID);
        assertThat(testPrimaryClaimResubmisionMaster.getInsertedDate()).isEqualTo(UPDATED_INSERTED_DATE);
        assertThat(testPrimaryClaimResubmisionMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPrimaryClaimResubmisionMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderType()).isEqualTo(UPDATED_BILLING_PROVIDER_TYPE);
        assertThat(testPrimaryClaimResubmisionMaster.getInsertedByName()).isEqualTo(UPDATED_INSERTED_BY_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderAddressLine2()).isEqualTo(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimResubmisionMaster.getPayerClaimControlNumber()).isEqualTo(UPDATED_PAYER_CLAIM_CONTROL_NUMBER);
        assertThat(testPrimaryClaimResubmisionMaster.getInsuredFirstName()).isEqualTo(UPDATED_INSURED_FIRST_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getInsuredLastName()).isEqualTo(UPDATED_INSURED_LAST_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getInsuredAddressLine1()).isEqualTo(UPDATED_INSURED_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimResubmisionMaster.getInsuredAddressLine2()).isEqualTo(UPDATED_INSURED_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimResubmisionMaster.getInsuredCity()).isEqualTo(UPDATED_INSURED_CITY);
        assertThat(testPrimaryClaimResubmisionMaster.getInsuredState()).isEqualTo(UPDATED_INSURED_STATE);
        assertThat(testPrimaryClaimResubmisionMaster.getInsuredZip()).isEqualTo(UPDATED_INSURED_ZIP);
        assertThat(testPrimaryClaimResubmisionMaster.getInsuredContactNo()).isEqualTo(UPDATED_INSURED_CONTACT_NO);
        assertThat(testPrimaryClaimResubmisionMaster.getInsuredDob()).isEqualTo(UPDATED_INSURED_DOB);
        assertThat(testPrimaryClaimResubmisionMaster.getInsuredGender()).isEqualTo(UPDATED_INSURED_GENDER);
        assertThat(testPrimaryClaimResubmisionMaster.getOrderingProviderFirstName()).isEqualTo(UPDATED_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getOrderingProviderLastName()).isEqualTo(UPDATED_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getOrderingProviderNpi()).isEqualTo(UPDATED_ORDERING_PROVIDER_NPI);
        assertThat(testPrimaryClaimResubmisionMaster.getPatientRelationshipInsured()).isEqualTo(UPDATED_PATIENT_RELATIONSHIP_INSURED);
        assertThat(testPrimaryClaimResubmisionMaster.getPatientConditionEmployment()).isEqualTo(UPDATED_PATIENT_CONDITION_EMPLOYMENT);
        assertThat(testPrimaryClaimResubmisionMaster.getPatientConditionAutoAccident()).isEqualTo(UPDATED_PATIENT_CONDITION_AUTO_ACCIDENT);
        assertThat(testPrimaryClaimResubmisionMaster.getPatientConditionOtherAccident())
            .isEqualTo(UPDATED_PATIENT_CONDITION_OTHER_ACCIDENT);
        assertThat(testPrimaryClaimResubmisionMaster.getIsNextLevelInsurerPresentStatus())
            .isEqualTo(UPDATED_IS_NEXT_LEVEL_INSURER_PRESENT_STATUS);
        assertThat(testPrimaryClaimResubmisionMaster.getOriginalDos()).isEqualTo(UPDATED_ORIGINAL_DOS);
        assertThat(testPrimaryClaimResubmisionMaster.getParNo()).isEqualTo(UPDATED_PAR_NO);
        assertThat(testPrimaryClaimResubmisionMaster.getBillingProviderTaxonomy()).isEqualTo(UPDATED_BILLING_PROVIDER_TAXONOMY);
        assertThat(testPrimaryClaimResubmisionMaster.getServiceProviderNpi()).isEqualTo(UPDATED_SERVICE_PROVIDER_NPI);
        assertThat(testPrimaryClaimResubmisionMaster.getServiceProviderOrganisationName())
            .isEqualTo(UPDATED_SERVICE_PROVIDER_ORGANISATION_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getServiceProviderAddressLine1()).isEqualTo(UPDATED_SERVICE_PROVIDER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimResubmisionMaster.getServiceProviderAddressLine2()).isEqualTo(UPDATED_SERVICE_PROVIDER_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimResubmisionMaster.getServiceProviderCity()).isEqualTo(UPDATED_SERVICE_PROVIDER_CITY);
        assertThat(testPrimaryClaimResubmisionMaster.getServiceProviderState()).isEqualTo(UPDATED_SERVICE_PROVIDER_STATE);
        assertThat(testPrimaryClaimResubmisionMaster.getServiceProviderCountry()).isEqualTo(UPDATED_SERVICE_PROVIDER_COUNTRY);
        assertThat(testPrimaryClaimResubmisionMaster.getServiceProviderZipCode()).isEqualTo(UPDATED_SERVICE_PROVIDER_ZIP_CODE);
        assertThat(testPrimaryClaimResubmisionMaster.getServiceProviderTaxonomy()).isEqualTo(UPDATED_SERVICE_PROVIDER_TAXONOMY);
        assertThat(testPrimaryClaimResubmisionMaster.getCms1500FormName()).isEqualTo(UPDATED_CMS_1500_FORM_NAME);
        assertThat(testPrimaryClaimResubmisionMaster.getTradingPartnerAddressLine1()).isEqualTo(UPDATED_TRADING_PARTNER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimResubmisionMaster.getTradingPartnerAddressLine2()).isEqualTo(UPDATED_TRADING_PARTNER_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimResubmisionMaster.getTradingPartnerCity()).isEqualTo(UPDATED_TRADING_PARTNER_CITY);
        assertThat(testPrimaryClaimResubmisionMaster.getTradingPartnerState()).isEqualTo(UPDATED_TRADING_PARTNER_STATE);
        assertThat(testPrimaryClaimResubmisionMaster.getTradingPartnerZip()).isEqualTo(UPDATED_TRADING_PARTNER_ZIP);
        assertThat(testPrimaryClaimResubmisionMaster.getDiagnosisCodeType()).isEqualTo(UPDATED_DIAGNOSIS_CODE_TYPE);
        assertThat(testPrimaryClaimResubmisionMaster.getPrimaryClaimResubmisionMasterUuid())
            .isEqualTo(UPDATED_PRIMARY_CLAIM_RESUBMISION_MASTER_UUID);
    }

    @Test
    void patchNonExistingPrimaryClaimResubmisionMaster() throws Exception {
        int databaseSizeBeforeUpdate = primaryClaimResubmisionMasterRepository.findAll().collectList().block().size();
        primaryClaimResubmisionMaster.setChangeHealthPrimaryResubmisionMasterId(count.incrementAndGet());

        // Create the PrimaryClaimResubmisionMaster
        PrimaryClaimResubmisionMasterDTO primaryClaimResubmisionMasterDTO = primaryClaimResubmisionMasterMapper.toDto(
            primaryClaimResubmisionMaster
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, primaryClaimResubmisionMasterDTO.getChangeHealthPrimaryResubmisionMasterId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(primaryClaimResubmisionMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PrimaryClaimResubmisionMaster in the database
        List<PrimaryClaimResubmisionMaster> primaryClaimResubmisionMasterList = primaryClaimResubmisionMasterRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimResubmisionMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchPrimaryClaimResubmisionMaster() throws Exception {
        int databaseSizeBeforeUpdate = primaryClaimResubmisionMasterRepository.findAll().collectList().block().size();
        primaryClaimResubmisionMaster.setChangeHealthPrimaryResubmisionMasterId(count.incrementAndGet());

        // Create the PrimaryClaimResubmisionMaster
        PrimaryClaimResubmisionMasterDTO primaryClaimResubmisionMasterDTO = primaryClaimResubmisionMasterMapper.toDto(
            primaryClaimResubmisionMaster
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(primaryClaimResubmisionMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PrimaryClaimResubmisionMaster in the database
        List<PrimaryClaimResubmisionMaster> primaryClaimResubmisionMasterList = primaryClaimResubmisionMasterRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimResubmisionMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamPrimaryClaimResubmisionMaster() throws Exception {
        int databaseSizeBeforeUpdate = primaryClaimResubmisionMasterRepository.findAll().collectList().block().size();
        primaryClaimResubmisionMaster.setChangeHealthPrimaryResubmisionMasterId(count.incrementAndGet());

        // Create the PrimaryClaimResubmisionMaster
        PrimaryClaimResubmisionMasterDTO primaryClaimResubmisionMasterDTO = primaryClaimResubmisionMasterMapper.toDto(
            primaryClaimResubmisionMaster
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(primaryClaimResubmisionMasterDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PrimaryClaimResubmisionMaster in the database
        List<PrimaryClaimResubmisionMaster> primaryClaimResubmisionMasterList = primaryClaimResubmisionMasterRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimResubmisionMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deletePrimaryClaimResubmisionMaster() {
        // Initialize the database
        primaryClaimResubmisionMasterRepository.save(primaryClaimResubmisionMaster).block();

        int databaseSizeBeforeDelete = primaryClaimResubmisionMasterRepository.findAll().collectList().block().size();

        // Delete the primaryClaimResubmisionMaster
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, primaryClaimResubmisionMaster.getChangeHealthPrimaryResubmisionMasterId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<PrimaryClaimResubmisionMaster> primaryClaimResubmisionMasterList = primaryClaimResubmisionMasterRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimResubmisionMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
