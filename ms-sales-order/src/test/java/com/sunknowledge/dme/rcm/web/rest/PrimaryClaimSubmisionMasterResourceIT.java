package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.PrimaryClaimSubmisionMaster;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.PrimaryClaimSubmisionMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.PrimaryClaimSubmisionMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.PrimaryClaimSubmisionMasterMapper;
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
 * Integration tests for the {@link PrimaryClaimSubmisionMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class PrimaryClaimSubmisionMasterResourceIT {

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

    private static final Long DEFAULT_IPDATED_BY_ID = 1L;
    private static final Long UPDATED_IPDATED_BY_ID = 2L;

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

    private static final String DEFAULT_TRADING_PATNER_CITY = "AAAAAAAAAA";
    private static final String UPDATED_TRADING_PATNER_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_TRADING_PARTNER_STATE = "AAAAAAAAAA";
    private static final String UPDATED_TRADING_PARTNER_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_TRADING_PARTNER_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_TRADING_PARTNER_ZIP = "BBBBBBBBBB";

    private static final String DEFAULT_DIAGNOSIS_CODE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_DIAGNOSIS_CODE_TYPE = "BBBBBBBBBB";

    private static final UUID DEFAULT_PRIMARY_CLAIM_SUBMISION_MASTER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_PRIMARY_CLAIM_SUBMISION_MASTER_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/primary-claim-submision-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{changeHealthPrimarySubmisionMasterId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PrimaryClaimSubmisionMasterRepository primaryClaimSubmisionMasterRepository;

    @Autowired
    private PrimaryClaimSubmisionMasterMapper primaryClaimSubmisionMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private PrimaryClaimSubmisionMaster primaryClaimSubmisionMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PrimaryClaimSubmisionMaster createEntity(EntityManager em) {
        PrimaryClaimSubmisionMaster primaryClaimSubmisionMaster = new PrimaryClaimSubmisionMaster()
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
            .ipdatedById(DEFAULT_IPDATED_BY_ID)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .billingProviderType(DEFAULT_BILLING_PROVIDER_TYPE)
            .insertedByName(DEFAULT_INSERTED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .status(DEFAULT_STATUS)
            .billingProviderAddressLine2(DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_2)
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
            .tradingPatnerCity(DEFAULT_TRADING_PATNER_CITY)
            .tradingPartnerState(DEFAULT_TRADING_PARTNER_STATE)
            .tradingPartnerZip(DEFAULT_TRADING_PARTNER_ZIP)
            .diagnosisCodeType(DEFAULT_DIAGNOSIS_CODE_TYPE)
            .primaryClaimSubmisionMasterUuid(DEFAULT_PRIMARY_CLAIM_SUBMISION_MASTER_UUID);
        return primaryClaimSubmisionMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PrimaryClaimSubmisionMaster createUpdatedEntity(EntityManager em) {
        PrimaryClaimSubmisionMaster primaryClaimSubmisionMaster = new PrimaryClaimSubmisionMaster()
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
            .ipdatedById(UPDATED_IPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .billingProviderType(UPDATED_BILLING_PROVIDER_TYPE)
            .insertedByName(UPDATED_INSERTED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .status(UPDATED_STATUS)
            .billingProviderAddressLine2(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_2)
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
            .tradingPatnerCity(UPDATED_TRADING_PATNER_CITY)
            .tradingPartnerState(UPDATED_TRADING_PARTNER_STATE)
            .tradingPartnerZip(UPDATED_TRADING_PARTNER_ZIP)
            .diagnosisCodeType(UPDATED_DIAGNOSIS_CODE_TYPE)
            .primaryClaimSubmisionMasterUuid(UPDATED_PRIMARY_CLAIM_SUBMISION_MASTER_UUID);
        return primaryClaimSubmisionMaster;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(PrimaryClaimSubmisionMaster.class).block();
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
        primaryClaimSubmisionMaster = createEntity(em);
    }

    @Test
    void createPrimaryClaimSubmisionMaster() throws Exception {
        int databaseSizeBeforeCreate = primaryClaimSubmisionMasterRepository.findAll().collectList().block().size();
        // Create the PrimaryClaimSubmisionMaster
        PrimaryClaimSubmisionMasterDTO primaryClaimSubmisionMasterDTO = primaryClaimSubmisionMasterMapper.toDto(
            primaryClaimSubmisionMaster
        );
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(primaryClaimSubmisionMasterDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the PrimaryClaimSubmisionMaster in the database
        List<PrimaryClaimSubmisionMaster> primaryClaimSubmisionMasterList = primaryClaimSubmisionMasterRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimSubmisionMasterList).hasSize(databaseSizeBeforeCreate + 1);
        PrimaryClaimSubmisionMaster testPrimaryClaimSubmisionMaster = primaryClaimSubmisionMasterList.get(
            primaryClaimSubmisionMasterList.size() - 1
        );
        assertThat(testPrimaryClaimSubmisionMaster.getSalesOrderId()).isEqualTo(DEFAULT_SALES_ORDER_ID);
        assertThat(testPrimaryClaimSubmisionMaster.getClaimControlNo()).isEqualTo(DEFAULT_CLAIM_CONTROL_NO);
        assertThat(testPrimaryClaimSubmisionMaster.getTradingPartnerServiceId()).isEqualTo(DEFAULT_TRADING_PARTNER_SERVICE_ID);
        assertThat(testPrimaryClaimSubmisionMaster.getTradingPartnerName()).isEqualTo(DEFAULT_TRADING_PARTNER_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getSubmitterOrganizationName()).isEqualTo(DEFAULT_SUBMITTER_ORGANIZATION_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getSubmitterContactPersonName()).isEqualTo(DEFAULT_SUBMITTER_CONTACT_PERSON_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getSubmitterContactNo()).isEqualTo(DEFAULT_SUBMITTER_CONTACT_NO);
        assertThat(testPrimaryClaimSubmisionMaster.getReceiverOrganizationName()).isEqualTo(DEFAULT_RECEIVER_ORGANIZATION_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getSubscriberMemberIdNo()).isEqualTo(DEFAULT_SUBSCRIBER_MEMBER_ID_NO);
        assertThat(testPrimaryClaimSubmisionMaster.getSubscriberPaymentResponsibilityLevelCode())
            .isEqualTo(DEFAULT_SUBSCRIBER_PAYMENT_RESPONSIBILITY_LEVEL_CODE);
        assertThat(testPrimaryClaimSubmisionMaster.getSubscriberFirstName()).isEqualTo(DEFAULT_SUBSCRIBER_FIRST_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getSubscriberLastName()).isEqualTo(DEFAULT_SUBSCRIBER_LAST_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getSubscriberGender()).isEqualTo(DEFAULT_SUBSCRIBER_GENDER);
        assertThat(testPrimaryClaimSubmisionMaster.getSubscriberDob()).isEqualTo(DEFAULT_SUBSCRIBER_DOB);
        assertThat(testPrimaryClaimSubmisionMaster.getPrimaryInsurerPolicyNo()).isEqualTo(DEFAULT_PRIMARY_INSURER_POLICY_NO);
        assertThat(testPrimaryClaimSubmisionMaster.getSubscriberAddressLine1()).isEqualTo(DEFAULT_SUBSCRIBER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimSubmisionMaster.getSubscriberCity()).isEqualTo(DEFAULT_SUBSCRIBER_CITY);
        assertThat(testPrimaryClaimSubmisionMaster.getSubscriberState()).isEqualTo(DEFAULT_SUBSCRIBER_STATE);
        assertThat(testPrimaryClaimSubmisionMaster.getSubscriberZipCode()).isEqualTo(DEFAULT_SUBSCRIBER_ZIP_CODE);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderNpi()).isEqualTo(DEFAULT_BILLING_PROVIDER_NPI);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderEin()).isEqualTo(DEFAULT_BILLING_PROVIDER_EIN);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderOrganizationName())
            .isEqualTo(DEFAULT_BILLING_PROVIDER_ORGANIZATION_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderAddressLine1()).isEqualTo(DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderCity()).isEqualTo(DEFAULT_BILLING_PROVIDER_CITY);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderState()).isEqualTo(DEFAULT_BILLING_PROVIDER_STATE);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderZipCode()).isEqualTo(DEFAULT_BILLING_PROVIDER_ZIP_CODE);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderContactPersonName())
            .isEqualTo(DEFAULT_BILLING_PROVIDER_CONTACT_PERSON_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderContactNo()).isEqualTo(DEFAULT_BILLING_PROVIDER_CONTACT_NO);
        assertThat(testPrimaryClaimSubmisionMaster.getClaimFilingCode()).isEqualTo(DEFAULT_CLAIM_FILING_CODE);
        assertThat(testPrimaryClaimSubmisionMaster.getClaimChargeAmount()).isEqualTo(DEFAULT_CLAIM_CHARGE_AMOUNT);
        assertThat(testPrimaryClaimSubmisionMaster.getPosCode()).isEqualTo(DEFAULT_POS_CODE);
        assertThat(testPrimaryClaimSubmisionMaster.getClaimFrequencyCode()).isEqualTo(DEFAULT_CLAIM_FREQUENCY_CODE);
        assertThat(testPrimaryClaimSubmisionMaster.getSignatureIndicator()).isEqualTo(DEFAULT_SIGNATURE_INDICATOR);
        assertThat(testPrimaryClaimSubmisionMaster.getPlanParticipationCode()).isEqualTo(DEFAULT_PLAN_PARTICIPATION_CODE);
        assertThat(testPrimaryClaimSubmisionMaster.getBenefitsAssignmentCertificationIndicator())
            .isEqualTo(DEFAULT_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR);
        assertThat(testPrimaryClaimSubmisionMaster.getReleaseInformationCode()).isEqualTo(DEFAULT_RELEASE_INFORMATION_CODE);
        assertThat(testPrimaryClaimSubmisionMaster.getPrimaryDiagnosis()).isEqualTo(DEFAULT_PRIMARY_DIAGNOSIS);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode1()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_1);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode2()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_2);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode3()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_3);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode4()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_4);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode5()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_5);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode6()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_6);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode7()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_7);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode8()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_8);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode9()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_9);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode10()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_10);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode11()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_11);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode12()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_12);
        assertThat(testPrimaryClaimSubmisionMaster.getInsertedById()).isEqualTo(DEFAULT_INSERTED_BY_ID);
        assertThat(testPrimaryClaimSubmisionMaster.getInsertedDate()).isEqualTo(DEFAULT_INSERTED_DATE);
        assertThat(testPrimaryClaimSubmisionMaster.getIpdatedById()).isEqualTo(DEFAULT_IPDATED_BY_ID);
        assertThat(testPrimaryClaimSubmisionMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderType()).isEqualTo(DEFAULT_BILLING_PROVIDER_TYPE);
        assertThat(testPrimaryClaimSubmisionMaster.getInsertedByName()).isEqualTo(DEFAULT_INSERTED_BY_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderAddressLine2()).isEqualTo(DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimSubmisionMaster.getInsuredFirstName()).isEqualTo(DEFAULT_INSURED_FIRST_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getInsuredLastName()).isEqualTo(DEFAULT_INSURED_LAST_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getInsuredAddressLine1()).isEqualTo(DEFAULT_INSURED_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimSubmisionMaster.getInsuredAddressLine2()).isEqualTo(DEFAULT_INSURED_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimSubmisionMaster.getInsuredCity()).isEqualTo(DEFAULT_INSURED_CITY);
        assertThat(testPrimaryClaimSubmisionMaster.getInsuredState()).isEqualTo(DEFAULT_INSURED_STATE);
        assertThat(testPrimaryClaimSubmisionMaster.getInsuredZip()).isEqualTo(DEFAULT_INSURED_ZIP);
        assertThat(testPrimaryClaimSubmisionMaster.getInsuredContactNo()).isEqualTo(DEFAULT_INSURED_CONTACT_NO);
        assertThat(testPrimaryClaimSubmisionMaster.getInsuredDob()).isEqualTo(DEFAULT_INSURED_DOB);
        assertThat(testPrimaryClaimSubmisionMaster.getInsuredGender()).isEqualTo(DEFAULT_INSURED_GENDER);
        assertThat(testPrimaryClaimSubmisionMaster.getOrderingProviderFirstName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getOrderingProviderLastName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getOrderingProviderNpi()).isEqualTo(DEFAULT_ORDERING_PROVIDER_NPI);
        assertThat(testPrimaryClaimSubmisionMaster.getPatientRelationshipInsured()).isEqualTo(DEFAULT_PATIENT_RELATIONSHIP_INSURED);
        assertThat(testPrimaryClaimSubmisionMaster.getPatientConditionEmployment()).isEqualTo(DEFAULT_PATIENT_CONDITION_EMPLOYMENT);
        assertThat(testPrimaryClaimSubmisionMaster.getPatientConditionAutoAccident()).isEqualTo(DEFAULT_PATIENT_CONDITION_AUTO_ACCIDENT);
        assertThat(testPrimaryClaimSubmisionMaster.getPatientConditionOtherAccident()).isEqualTo(DEFAULT_PATIENT_CONDITION_OTHER_ACCIDENT);
        assertThat(testPrimaryClaimSubmisionMaster.getIsNextLevelInsurerPresentStatus())
            .isEqualTo(DEFAULT_IS_NEXT_LEVEL_INSURER_PRESENT_STATUS);
        assertThat(testPrimaryClaimSubmisionMaster.getOriginalDos()).isEqualTo(DEFAULT_ORIGINAL_DOS);
        assertThat(testPrimaryClaimSubmisionMaster.getParNo()).isEqualTo(DEFAULT_PAR_NO);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderTaxonomy()).isEqualTo(DEFAULT_BILLING_PROVIDER_TAXONOMY);
        assertThat(testPrimaryClaimSubmisionMaster.getServiceProviderNpi()).isEqualTo(DEFAULT_SERVICE_PROVIDER_NPI);
        assertThat(testPrimaryClaimSubmisionMaster.getServiceProviderOrganisationName())
            .isEqualTo(DEFAULT_SERVICE_PROVIDER_ORGANISATION_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getServiceProviderAddressLine1()).isEqualTo(DEFAULT_SERVICE_PROVIDER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimSubmisionMaster.getServiceProviderAddressLine2()).isEqualTo(DEFAULT_SERVICE_PROVIDER_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimSubmisionMaster.getServiceProviderCity()).isEqualTo(DEFAULT_SERVICE_PROVIDER_CITY);
        assertThat(testPrimaryClaimSubmisionMaster.getServiceProviderState()).isEqualTo(DEFAULT_SERVICE_PROVIDER_STATE);
        assertThat(testPrimaryClaimSubmisionMaster.getServiceProviderCountry()).isEqualTo(DEFAULT_SERVICE_PROVIDER_COUNTRY);
        assertThat(testPrimaryClaimSubmisionMaster.getServiceProviderZipCode()).isEqualTo(DEFAULT_SERVICE_PROVIDER_ZIP_CODE);
        assertThat(testPrimaryClaimSubmisionMaster.getServiceProviderTaxonomy()).isEqualTo(DEFAULT_SERVICE_PROVIDER_TAXONOMY);
        assertThat(testPrimaryClaimSubmisionMaster.getCms1500FormName()).isEqualTo(DEFAULT_CMS_1500_FORM_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getTradingPartnerAddressLine1()).isEqualTo(DEFAULT_TRADING_PARTNER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimSubmisionMaster.getTradingPartnerAddressLine2()).isEqualTo(DEFAULT_TRADING_PARTNER_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimSubmisionMaster.getTradingPatnerCity()).isEqualTo(DEFAULT_TRADING_PATNER_CITY);
        assertThat(testPrimaryClaimSubmisionMaster.getTradingPartnerState()).isEqualTo(DEFAULT_TRADING_PARTNER_STATE);
        assertThat(testPrimaryClaimSubmisionMaster.getTradingPartnerZip()).isEqualTo(DEFAULT_TRADING_PARTNER_ZIP);
        assertThat(testPrimaryClaimSubmisionMaster.getDiagnosisCodeType()).isEqualTo(DEFAULT_DIAGNOSIS_CODE_TYPE);
        assertThat(testPrimaryClaimSubmisionMaster.getPrimaryClaimSubmisionMasterUuid())
            .isEqualTo(DEFAULT_PRIMARY_CLAIM_SUBMISION_MASTER_UUID);
    }

    @Test
    void createPrimaryClaimSubmisionMasterWithExistingId() throws Exception {
        // Create the PrimaryClaimSubmisionMaster with an existing ID
        primaryClaimSubmisionMaster.setChangeHealthPrimarySubmisionMasterId(1L);
        PrimaryClaimSubmisionMasterDTO primaryClaimSubmisionMasterDTO = primaryClaimSubmisionMasterMapper.toDto(
            primaryClaimSubmisionMaster
        );

        int databaseSizeBeforeCreate = primaryClaimSubmisionMasterRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(primaryClaimSubmisionMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PrimaryClaimSubmisionMaster in the database
        List<PrimaryClaimSubmisionMaster> primaryClaimSubmisionMasterList = primaryClaimSubmisionMasterRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimSubmisionMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllPrimaryClaimSubmisionMasters() {
        // Initialize the database
        primaryClaimSubmisionMasterRepository.save(primaryClaimSubmisionMaster).block();

        // Get all the primaryClaimSubmisionMasterList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=changeHealthPrimarySubmisionMasterId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].changeHealthPrimarySubmisionMasterId")
            .value(hasItem(primaryClaimSubmisionMaster.getChangeHealthPrimarySubmisionMasterId().intValue()))
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
            .jsonPath("$.[*].ipdatedById")
            .value(hasItem(DEFAULT_IPDATED_BY_ID.intValue()))
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
            .jsonPath("$.[*].tradingPatnerCity")
            .value(hasItem(DEFAULT_TRADING_PATNER_CITY))
            .jsonPath("$.[*].tradingPartnerState")
            .value(hasItem(DEFAULT_TRADING_PARTNER_STATE))
            .jsonPath("$.[*].tradingPartnerZip")
            .value(hasItem(DEFAULT_TRADING_PARTNER_ZIP))
            .jsonPath("$.[*].diagnosisCodeType")
            .value(hasItem(DEFAULT_DIAGNOSIS_CODE_TYPE))
            .jsonPath("$.[*].primaryClaimSubmisionMasterUuid")
            .value(hasItem(DEFAULT_PRIMARY_CLAIM_SUBMISION_MASTER_UUID.toString()));
    }

    @Test
    void getPrimaryClaimSubmisionMaster() {
        // Initialize the database
        primaryClaimSubmisionMasterRepository.save(primaryClaimSubmisionMaster).block();

        // Get the primaryClaimSubmisionMaster
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, primaryClaimSubmisionMaster.getChangeHealthPrimarySubmisionMasterId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.changeHealthPrimarySubmisionMasterId")
            .value(is(primaryClaimSubmisionMaster.getChangeHealthPrimarySubmisionMasterId().intValue()))
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
            .jsonPath("$.ipdatedById")
            .value(is(DEFAULT_IPDATED_BY_ID.intValue()))
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
            .jsonPath("$.tradingPatnerCity")
            .value(is(DEFAULT_TRADING_PATNER_CITY))
            .jsonPath("$.tradingPartnerState")
            .value(is(DEFAULT_TRADING_PARTNER_STATE))
            .jsonPath("$.tradingPartnerZip")
            .value(is(DEFAULT_TRADING_PARTNER_ZIP))
            .jsonPath("$.diagnosisCodeType")
            .value(is(DEFAULT_DIAGNOSIS_CODE_TYPE))
            .jsonPath("$.primaryClaimSubmisionMasterUuid")
            .value(is(DEFAULT_PRIMARY_CLAIM_SUBMISION_MASTER_UUID.toString()));
    }

    @Test
    void getNonExistingPrimaryClaimSubmisionMaster() {
        // Get the primaryClaimSubmisionMaster
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putNewPrimaryClaimSubmisionMaster() throws Exception {
        // Initialize the database
        primaryClaimSubmisionMasterRepository.save(primaryClaimSubmisionMaster).block();

        int databaseSizeBeforeUpdate = primaryClaimSubmisionMasterRepository.findAll().collectList().block().size();

        // Update the primaryClaimSubmisionMaster
        PrimaryClaimSubmisionMaster updatedPrimaryClaimSubmisionMaster = primaryClaimSubmisionMasterRepository
            .findById(primaryClaimSubmisionMaster.getChangeHealthPrimarySubmisionMasterId())
            .block();
        updatedPrimaryClaimSubmisionMaster
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
            .ipdatedById(UPDATED_IPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .billingProviderType(UPDATED_BILLING_PROVIDER_TYPE)
            .insertedByName(UPDATED_INSERTED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .status(UPDATED_STATUS)
            .billingProviderAddressLine2(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_2)
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
            .tradingPatnerCity(UPDATED_TRADING_PATNER_CITY)
            .tradingPartnerState(UPDATED_TRADING_PARTNER_STATE)
            .tradingPartnerZip(UPDATED_TRADING_PARTNER_ZIP)
            .diagnosisCodeType(UPDATED_DIAGNOSIS_CODE_TYPE)
            .primaryClaimSubmisionMasterUuid(UPDATED_PRIMARY_CLAIM_SUBMISION_MASTER_UUID);
        PrimaryClaimSubmisionMasterDTO primaryClaimSubmisionMasterDTO = primaryClaimSubmisionMasterMapper.toDto(
            updatedPrimaryClaimSubmisionMaster
        );

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, primaryClaimSubmisionMasterDTO.getChangeHealthPrimarySubmisionMasterId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(primaryClaimSubmisionMasterDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PrimaryClaimSubmisionMaster in the database
        List<PrimaryClaimSubmisionMaster> primaryClaimSubmisionMasterList = primaryClaimSubmisionMasterRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimSubmisionMasterList).hasSize(databaseSizeBeforeUpdate);
        PrimaryClaimSubmisionMaster testPrimaryClaimSubmisionMaster = primaryClaimSubmisionMasterList.get(
            primaryClaimSubmisionMasterList.size() - 1
        );
        assertThat(testPrimaryClaimSubmisionMaster.getSalesOrderId()).isEqualTo(UPDATED_SALES_ORDER_ID);
        assertThat(testPrimaryClaimSubmisionMaster.getClaimControlNo()).isEqualTo(UPDATED_CLAIM_CONTROL_NO);
        assertThat(testPrimaryClaimSubmisionMaster.getTradingPartnerServiceId()).isEqualTo(UPDATED_TRADING_PARTNER_SERVICE_ID);
        assertThat(testPrimaryClaimSubmisionMaster.getTradingPartnerName()).isEqualTo(UPDATED_TRADING_PARTNER_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getSubmitterOrganizationName()).isEqualTo(UPDATED_SUBMITTER_ORGANIZATION_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getSubmitterContactPersonName()).isEqualTo(UPDATED_SUBMITTER_CONTACT_PERSON_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getSubmitterContactNo()).isEqualTo(UPDATED_SUBMITTER_CONTACT_NO);
        assertThat(testPrimaryClaimSubmisionMaster.getReceiverOrganizationName()).isEqualTo(UPDATED_RECEIVER_ORGANIZATION_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getSubscriberMemberIdNo()).isEqualTo(UPDATED_SUBSCRIBER_MEMBER_ID_NO);
        assertThat(testPrimaryClaimSubmisionMaster.getSubscriberPaymentResponsibilityLevelCode())
            .isEqualTo(UPDATED_SUBSCRIBER_PAYMENT_RESPONSIBILITY_LEVEL_CODE);
        assertThat(testPrimaryClaimSubmisionMaster.getSubscriberFirstName()).isEqualTo(UPDATED_SUBSCRIBER_FIRST_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getSubscriberLastName()).isEqualTo(UPDATED_SUBSCRIBER_LAST_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getSubscriberGender()).isEqualTo(UPDATED_SUBSCRIBER_GENDER);
        assertThat(testPrimaryClaimSubmisionMaster.getSubscriberDob()).isEqualTo(UPDATED_SUBSCRIBER_DOB);
        assertThat(testPrimaryClaimSubmisionMaster.getPrimaryInsurerPolicyNo()).isEqualTo(UPDATED_PRIMARY_INSURER_POLICY_NO);
        assertThat(testPrimaryClaimSubmisionMaster.getSubscriberAddressLine1()).isEqualTo(UPDATED_SUBSCRIBER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimSubmisionMaster.getSubscriberCity()).isEqualTo(UPDATED_SUBSCRIBER_CITY);
        assertThat(testPrimaryClaimSubmisionMaster.getSubscriberState()).isEqualTo(UPDATED_SUBSCRIBER_STATE);
        assertThat(testPrimaryClaimSubmisionMaster.getSubscriberZipCode()).isEqualTo(UPDATED_SUBSCRIBER_ZIP_CODE);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderNpi()).isEqualTo(UPDATED_BILLING_PROVIDER_NPI);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderEin()).isEqualTo(UPDATED_BILLING_PROVIDER_EIN);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderOrganizationName())
            .isEqualTo(UPDATED_BILLING_PROVIDER_ORGANIZATION_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderAddressLine1()).isEqualTo(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderCity()).isEqualTo(UPDATED_BILLING_PROVIDER_CITY);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderState()).isEqualTo(UPDATED_BILLING_PROVIDER_STATE);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderZipCode()).isEqualTo(UPDATED_BILLING_PROVIDER_ZIP_CODE);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderContactPersonName())
            .isEqualTo(UPDATED_BILLING_PROVIDER_CONTACT_PERSON_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderContactNo()).isEqualTo(UPDATED_BILLING_PROVIDER_CONTACT_NO);
        assertThat(testPrimaryClaimSubmisionMaster.getClaimFilingCode()).isEqualTo(UPDATED_CLAIM_FILING_CODE);
        assertThat(testPrimaryClaimSubmisionMaster.getClaimChargeAmount()).isEqualTo(UPDATED_CLAIM_CHARGE_AMOUNT);
        assertThat(testPrimaryClaimSubmisionMaster.getPosCode()).isEqualTo(UPDATED_POS_CODE);
        assertThat(testPrimaryClaimSubmisionMaster.getClaimFrequencyCode()).isEqualTo(UPDATED_CLAIM_FREQUENCY_CODE);
        assertThat(testPrimaryClaimSubmisionMaster.getSignatureIndicator()).isEqualTo(UPDATED_SIGNATURE_INDICATOR);
        assertThat(testPrimaryClaimSubmisionMaster.getPlanParticipationCode()).isEqualTo(UPDATED_PLAN_PARTICIPATION_CODE);
        assertThat(testPrimaryClaimSubmisionMaster.getBenefitsAssignmentCertificationIndicator())
            .isEqualTo(UPDATED_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR);
        assertThat(testPrimaryClaimSubmisionMaster.getReleaseInformationCode()).isEqualTo(UPDATED_RELEASE_INFORMATION_CODE);
        assertThat(testPrimaryClaimSubmisionMaster.getPrimaryDiagnosis()).isEqualTo(UPDATED_PRIMARY_DIAGNOSIS);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode1()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_1);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode2()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_2);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode3()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_3);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode4()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_4);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode5()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_5);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode6()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_6);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode7()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_7);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode8()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_8);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode9()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_9);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode10()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_10);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode11()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_11);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode12()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_12);
        assertThat(testPrimaryClaimSubmisionMaster.getInsertedById()).isEqualTo(UPDATED_INSERTED_BY_ID);
        assertThat(testPrimaryClaimSubmisionMaster.getInsertedDate()).isEqualTo(UPDATED_INSERTED_DATE);
        assertThat(testPrimaryClaimSubmisionMaster.getIpdatedById()).isEqualTo(UPDATED_IPDATED_BY_ID);
        assertThat(testPrimaryClaimSubmisionMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderType()).isEqualTo(UPDATED_BILLING_PROVIDER_TYPE);
        assertThat(testPrimaryClaimSubmisionMaster.getInsertedByName()).isEqualTo(UPDATED_INSERTED_BY_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderAddressLine2()).isEqualTo(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimSubmisionMaster.getInsuredFirstName()).isEqualTo(UPDATED_INSURED_FIRST_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getInsuredLastName()).isEqualTo(UPDATED_INSURED_LAST_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getInsuredAddressLine1()).isEqualTo(UPDATED_INSURED_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimSubmisionMaster.getInsuredAddressLine2()).isEqualTo(UPDATED_INSURED_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimSubmisionMaster.getInsuredCity()).isEqualTo(UPDATED_INSURED_CITY);
        assertThat(testPrimaryClaimSubmisionMaster.getInsuredState()).isEqualTo(UPDATED_INSURED_STATE);
        assertThat(testPrimaryClaimSubmisionMaster.getInsuredZip()).isEqualTo(UPDATED_INSURED_ZIP);
        assertThat(testPrimaryClaimSubmisionMaster.getInsuredContactNo()).isEqualTo(UPDATED_INSURED_CONTACT_NO);
        assertThat(testPrimaryClaimSubmisionMaster.getInsuredDob()).isEqualTo(UPDATED_INSURED_DOB);
        assertThat(testPrimaryClaimSubmisionMaster.getInsuredGender()).isEqualTo(UPDATED_INSURED_GENDER);
        assertThat(testPrimaryClaimSubmisionMaster.getOrderingProviderFirstName()).isEqualTo(UPDATED_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getOrderingProviderLastName()).isEqualTo(UPDATED_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getOrderingProviderNpi()).isEqualTo(UPDATED_ORDERING_PROVIDER_NPI);
        assertThat(testPrimaryClaimSubmisionMaster.getPatientRelationshipInsured()).isEqualTo(UPDATED_PATIENT_RELATIONSHIP_INSURED);
        assertThat(testPrimaryClaimSubmisionMaster.getPatientConditionEmployment()).isEqualTo(UPDATED_PATIENT_CONDITION_EMPLOYMENT);
        assertThat(testPrimaryClaimSubmisionMaster.getPatientConditionAutoAccident()).isEqualTo(UPDATED_PATIENT_CONDITION_AUTO_ACCIDENT);
        assertThat(testPrimaryClaimSubmisionMaster.getPatientConditionOtherAccident()).isEqualTo(UPDATED_PATIENT_CONDITION_OTHER_ACCIDENT);
        assertThat(testPrimaryClaimSubmisionMaster.getIsNextLevelInsurerPresentStatus())
            .isEqualTo(UPDATED_IS_NEXT_LEVEL_INSURER_PRESENT_STATUS);
        assertThat(testPrimaryClaimSubmisionMaster.getOriginalDos()).isEqualTo(UPDATED_ORIGINAL_DOS);
        assertThat(testPrimaryClaimSubmisionMaster.getParNo()).isEqualTo(UPDATED_PAR_NO);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderTaxonomy()).isEqualTo(UPDATED_BILLING_PROVIDER_TAXONOMY);
        assertThat(testPrimaryClaimSubmisionMaster.getServiceProviderNpi()).isEqualTo(UPDATED_SERVICE_PROVIDER_NPI);
        assertThat(testPrimaryClaimSubmisionMaster.getServiceProviderOrganisationName())
            .isEqualTo(UPDATED_SERVICE_PROVIDER_ORGANISATION_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getServiceProviderAddressLine1()).isEqualTo(UPDATED_SERVICE_PROVIDER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimSubmisionMaster.getServiceProviderAddressLine2()).isEqualTo(UPDATED_SERVICE_PROVIDER_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimSubmisionMaster.getServiceProviderCity()).isEqualTo(UPDATED_SERVICE_PROVIDER_CITY);
        assertThat(testPrimaryClaimSubmisionMaster.getServiceProviderState()).isEqualTo(UPDATED_SERVICE_PROVIDER_STATE);
        assertThat(testPrimaryClaimSubmisionMaster.getServiceProviderCountry()).isEqualTo(UPDATED_SERVICE_PROVIDER_COUNTRY);
        assertThat(testPrimaryClaimSubmisionMaster.getServiceProviderZipCode()).isEqualTo(UPDATED_SERVICE_PROVIDER_ZIP_CODE);
        assertThat(testPrimaryClaimSubmisionMaster.getServiceProviderTaxonomy()).isEqualTo(UPDATED_SERVICE_PROVIDER_TAXONOMY);
        assertThat(testPrimaryClaimSubmisionMaster.getCms1500FormName()).isEqualTo(UPDATED_CMS_1500_FORM_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getTradingPartnerAddressLine1()).isEqualTo(UPDATED_TRADING_PARTNER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimSubmisionMaster.getTradingPartnerAddressLine2()).isEqualTo(UPDATED_TRADING_PARTNER_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimSubmisionMaster.getTradingPatnerCity()).isEqualTo(UPDATED_TRADING_PATNER_CITY);
        assertThat(testPrimaryClaimSubmisionMaster.getTradingPartnerState()).isEqualTo(UPDATED_TRADING_PARTNER_STATE);
        assertThat(testPrimaryClaimSubmisionMaster.getTradingPartnerZip()).isEqualTo(UPDATED_TRADING_PARTNER_ZIP);
        assertThat(testPrimaryClaimSubmisionMaster.getDiagnosisCodeType()).isEqualTo(UPDATED_DIAGNOSIS_CODE_TYPE);
        assertThat(testPrimaryClaimSubmisionMaster.getPrimaryClaimSubmisionMasterUuid())
            .isEqualTo(UPDATED_PRIMARY_CLAIM_SUBMISION_MASTER_UUID);
    }

    @Test
    void putNonExistingPrimaryClaimSubmisionMaster() throws Exception {
        int databaseSizeBeforeUpdate = primaryClaimSubmisionMasterRepository.findAll().collectList().block().size();
        primaryClaimSubmisionMaster.setChangeHealthPrimarySubmisionMasterId(count.incrementAndGet());

        // Create the PrimaryClaimSubmisionMaster
        PrimaryClaimSubmisionMasterDTO primaryClaimSubmisionMasterDTO = primaryClaimSubmisionMasterMapper.toDto(
            primaryClaimSubmisionMaster
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, primaryClaimSubmisionMasterDTO.getChangeHealthPrimarySubmisionMasterId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(primaryClaimSubmisionMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PrimaryClaimSubmisionMaster in the database
        List<PrimaryClaimSubmisionMaster> primaryClaimSubmisionMasterList = primaryClaimSubmisionMasterRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimSubmisionMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchPrimaryClaimSubmisionMaster() throws Exception {
        int databaseSizeBeforeUpdate = primaryClaimSubmisionMasterRepository.findAll().collectList().block().size();
        primaryClaimSubmisionMaster.setChangeHealthPrimarySubmisionMasterId(count.incrementAndGet());

        // Create the PrimaryClaimSubmisionMaster
        PrimaryClaimSubmisionMasterDTO primaryClaimSubmisionMasterDTO = primaryClaimSubmisionMasterMapper.toDto(
            primaryClaimSubmisionMaster
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(primaryClaimSubmisionMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PrimaryClaimSubmisionMaster in the database
        List<PrimaryClaimSubmisionMaster> primaryClaimSubmisionMasterList = primaryClaimSubmisionMasterRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimSubmisionMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamPrimaryClaimSubmisionMaster() throws Exception {
        int databaseSizeBeforeUpdate = primaryClaimSubmisionMasterRepository.findAll().collectList().block().size();
        primaryClaimSubmisionMaster.setChangeHealthPrimarySubmisionMasterId(count.incrementAndGet());

        // Create the PrimaryClaimSubmisionMaster
        PrimaryClaimSubmisionMasterDTO primaryClaimSubmisionMasterDTO = primaryClaimSubmisionMasterMapper.toDto(
            primaryClaimSubmisionMaster
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(primaryClaimSubmisionMasterDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PrimaryClaimSubmisionMaster in the database
        List<PrimaryClaimSubmisionMaster> primaryClaimSubmisionMasterList = primaryClaimSubmisionMasterRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimSubmisionMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdatePrimaryClaimSubmisionMasterWithPatch() throws Exception {
        // Initialize the database
        primaryClaimSubmisionMasterRepository.save(primaryClaimSubmisionMaster).block();

        int databaseSizeBeforeUpdate = primaryClaimSubmisionMasterRepository.findAll().collectList().block().size();

        // Update the primaryClaimSubmisionMaster using partial update
        PrimaryClaimSubmisionMaster partialUpdatedPrimaryClaimSubmisionMaster = new PrimaryClaimSubmisionMaster();
        partialUpdatedPrimaryClaimSubmisionMaster.setChangeHealthPrimarySubmisionMasterId(
            primaryClaimSubmisionMaster.getChangeHealthPrimarySubmisionMasterId()
        );

        partialUpdatedPrimaryClaimSubmisionMaster
            .claimControlNo(UPDATED_CLAIM_CONTROL_NO)
            .tradingPartnerName(UPDATED_TRADING_PARTNER_NAME)
            .subscriberPaymentResponsibilityLevelCode(UPDATED_SUBSCRIBER_PAYMENT_RESPONSIBILITY_LEVEL_CODE)
            .subscriberFirstName(UPDATED_SUBSCRIBER_FIRST_NAME)
            .subscriberCity(UPDATED_SUBSCRIBER_CITY)
            .subscriberState(UPDATED_SUBSCRIBER_STATE)
            .billingProviderNpi(UPDATED_BILLING_PROVIDER_NPI)
            .billingProviderEin(UPDATED_BILLING_PROVIDER_EIN)
            .billingProviderState(UPDATED_BILLING_PROVIDER_STATE)
            .billingProviderZipCode(UPDATED_BILLING_PROVIDER_ZIP_CODE)
            .billingProviderContactPersonName(UPDATED_BILLING_PROVIDER_CONTACT_PERSON_NAME)
            .claimFilingCode(UPDATED_CLAIM_FILING_CODE)
            .posCode(UPDATED_POS_CODE)
            .claimFrequencyCode(UPDATED_CLAIM_FREQUENCY_CODE)
            .benefitsAssignmentCertificationIndicator(UPDATED_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR)
            .releaseInformationCode(UPDATED_RELEASE_INFORMATION_CODE)
            .icd10DiagnosisCode1(UPDATED_ICD_10_DIAGNOSIS_CODE_1)
            .icd10DiagnosisCode4(UPDATED_ICD_10_DIAGNOSIS_CODE_4)
            .icd10DiagnosisCode6(UPDATED_ICD_10_DIAGNOSIS_CODE_6)
            .icd10DiagnosisCode8(UPDATED_ICD_10_DIAGNOSIS_CODE_8)
            .icd10DiagnosisCode9(UPDATED_ICD_10_DIAGNOSIS_CODE_9)
            .icd10DiagnosisCode10(UPDATED_ICD_10_DIAGNOSIS_CODE_10)
            .insertedById(UPDATED_INSERTED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .billingProviderType(UPDATED_BILLING_PROVIDER_TYPE)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .status(UPDATED_STATUS)
            .billingProviderAddressLine2(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_2)
            .insuredLastName(UPDATED_INSURED_LAST_NAME)
            .insuredAddressLine2(UPDATED_INSURED_ADDRESS_LINE_2)
            .insuredCity(UPDATED_INSURED_CITY)
            .insuredState(UPDATED_INSURED_STATE)
            .insuredDob(UPDATED_INSURED_DOB)
            .insuredGender(UPDATED_INSURED_GENDER)
            .orderingProviderFirstName(UPDATED_ORDERING_PROVIDER_FIRST_NAME)
            .orderingProviderLastName(UPDATED_ORDERING_PROVIDER_LAST_NAME)
            .orderingProviderNpi(UPDATED_ORDERING_PROVIDER_NPI)
            .patientConditionOtherAccident(UPDATED_PATIENT_CONDITION_OTHER_ACCIDENT)
            .originalDos(UPDATED_ORIGINAL_DOS)
            .serviceProviderOrganisationName(UPDATED_SERVICE_PROVIDER_ORGANISATION_NAME)
            .serviceProviderCity(UPDATED_SERVICE_PROVIDER_CITY)
            .serviceProviderZipCode(UPDATED_SERVICE_PROVIDER_ZIP_CODE)
            .serviceProviderTaxonomy(UPDATED_SERVICE_PROVIDER_TAXONOMY)
            .cms1500FormName(UPDATED_CMS_1500_FORM_NAME)
            .tradingPartnerAddressLine1(UPDATED_TRADING_PARTNER_ADDRESS_LINE_1)
            .tradingPatnerCity(UPDATED_TRADING_PATNER_CITY)
            .tradingPartnerZip(UPDATED_TRADING_PARTNER_ZIP);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPrimaryClaimSubmisionMaster.getChangeHealthPrimarySubmisionMasterId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPrimaryClaimSubmisionMaster))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PrimaryClaimSubmisionMaster in the database
        List<PrimaryClaimSubmisionMaster> primaryClaimSubmisionMasterList = primaryClaimSubmisionMasterRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimSubmisionMasterList).hasSize(databaseSizeBeforeUpdate);
        PrimaryClaimSubmisionMaster testPrimaryClaimSubmisionMaster = primaryClaimSubmisionMasterList.get(
            primaryClaimSubmisionMasterList.size() - 1
        );
        assertThat(testPrimaryClaimSubmisionMaster.getSalesOrderId()).isEqualTo(DEFAULT_SALES_ORDER_ID);
        assertThat(testPrimaryClaimSubmisionMaster.getClaimControlNo()).isEqualTo(UPDATED_CLAIM_CONTROL_NO);
        assertThat(testPrimaryClaimSubmisionMaster.getTradingPartnerServiceId()).isEqualTo(DEFAULT_TRADING_PARTNER_SERVICE_ID);
        assertThat(testPrimaryClaimSubmisionMaster.getTradingPartnerName()).isEqualTo(UPDATED_TRADING_PARTNER_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getSubmitterOrganizationName()).isEqualTo(DEFAULT_SUBMITTER_ORGANIZATION_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getSubmitterContactPersonName()).isEqualTo(DEFAULT_SUBMITTER_CONTACT_PERSON_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getSubmitterContactNo()).isEqualTo(DEFAULT_SUBMITTER_CONTACT_NO);
        assertThat(testPrimaryClaimSubmisionMaster.getReceiverOrganizationName()).isEqualTo(DEFAULT_RECEIVER_ORGANIZATION_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getSubscriberMemberIdNo()).isEqualTo(DEFAULT_SUBSCRIBER_MEMBER_ID_NO);
        assertThat(testPrimaryClaimSubmisionMaster.getSubscriberPaymentResponsibilityLevelCode())
            .isEqualTo(UPDATED_SUBSCRIBER_PAYMENT_RESPONSIBILITY_LEVEL_CODE);
        assertThat(testPrimaryClaimSubmisionMaster.getSubscriberFirstName()).isEqualTo(UPDATED_SUBSCRIBER_FIRST_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getSubscriberLastName()).isEqualTo(DEFAULT_SUBSCRIBER_LAST_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getSubscriberGender()).isEqualTo(DEFAULT_SUBSCRIBER_GENDER);
        assertThat(testPrimaryClaimSubmisionMaster.getSubscriberDob()).isEqualTo(DEFAULT_SUBSCRIBER_DOB);
        assertThat(testPrimaryClaimSubmisionMaster.getPrimaryInsurerPolicyNo()).isEqualTo(DEFAULT_PRIMARY_INSURER_POLICY_NO);
        assertThat(testPrimaryClaimSubmisionMaster.getSubscriberAddressLine1()).isEqualTo(DEFAULT_SUBSCRIBER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimSubmisionMaster.getSubscriberCity()).isEqualTo(UPDATED_SUBSCRIBER_CITY);
        assertThat(testPrimaryClaimSubmisionMaster.getSubscriberState()).isEqualTo(UPDATED_SUBSCRIBER_STATE);
        assertThat(testPrimaryClaimSubmisionMaster.getSubscriberZipCode()).isEqualTo(DEFAULT_SUBSCRIBER_ZIP_CODE);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderNpi()).isEqualTo(UPDATED_BILLING_PROVIDER_NPI);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderEin()).isEqualTo(UPDATED_BILLING_PROVIDER_EIN);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderOrganizationName())
            .isEqualTo(DEFAULT_BILLING_PROVIDER_ORGANIZATION_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderAddressLine1()).isEqualTo(DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderCity()).isEqualTo(DEFAULT_BILLING_PROVIDER_CITY);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderState()).isEqualTo(UPDATED_BILLING_PROVIDER_STATE);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderZipCode()).isEqualTo(UPDATED_BILLING_PROVIDER_ZIP_CODE);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderContactPersonName())
            .isEqualTo(UPDATED_BILLING_PROVIDER_CONTACT_PERSON_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderContactNo()).isEqualTo(DEFAULT_BILLING_PROVIDER_CONTACT_NO);
        assertThat(testPrimaryClaimSubmisionMaster.getClaimFilingCode()).isEqualTo(UPDATED_CLAIM_FILING_CODE);
        assertThat(testPrimaryClaimSubmisionMaster.getClaimChargeAmount()).isEqualTo(DEFAULT_CLAIM_CHARGE_AMOUNT);
        assertThat(testPrimaryClaimSubmisionMaster.getPosCode()).isEqualTo(UPDATED_POS_CODE);
        assertThat(testPrimaryClaimSubmisionMaster.getClaimFrequencyCode()).isEqualTo(UPDATED_CLAIM_FREQUENCY_CODE);
        assertThat(testPrimaryClaimSubmisionMaster.getSignatureIndicator()).isEqualTo(DEFAULT_SIGNATURE_INDICATOR);
        assertThat(testPrimaryClaimSubmisionMaster.getPlanParticipationCode()).isEqualTo(DEFAULT_PLAN_PARTICIPATION_CODE);
        assertThat(testPrimaryClaimSubmisionMaster.getBenefitsAssignmentCertificationIndicator())
            .isEqualTo(UPDATED_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR);
        assertThat(testPrimaryClaimSubmisionMaster.getReleaseInformationCode()).isEqualTo(UPDATED_RELEASE_INFORMATION_CODE);
        assertThat(testPrimaryClaimSubmisionMaster.getPrimaryDiagnosis()).isEqualTo(DEFAULT_PRIMARY_DIAGNOSIS);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode1()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_1);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode2()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_2);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode3()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_3);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode4()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_4);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode5()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_5);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode6()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_6);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode7()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_7);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode8()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_8);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode9()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_9);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode10()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_10);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode11()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_11);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode12()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_12);
        assertThat(testPrimaryClaimSubmisionMaster.getInsertedById()).isEqualTo(UPDATED_INSERTED_BY_ID);
        assertThat(testPrimaryClaimSubmisionMaster.getInsertedDate()).isEqualTo(DEFAULT_INSERTED_DATE);
        assertThat(testPrimaryClaimSubmisionMaster.getIpdatedById()).isEqualTo(DEFAULT_IPDATED_BY_ID);
        assertThat(testPrimaryClaimSubmisionMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderType()).isEqualTo(UPDATED_BILLING_PROVIDER_TYPE);
        assertThat(testPrimaryClaimSubmisionMaster.getInsertedByName()).isEqualTo(DEFAULT_INSERTED_BY_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderAddressLine2()).isEqualTo(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimSubmisionMaster.getInsuredFirstName()).isEqualTo(DEFAULT_INSURED_FIRST_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getInsuredLastName()).isEqualTo(UPDATED_INSURED_LAST_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getInsuredAddressLine1()).isEqualTo(DEFAULT_INSURED_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimSubmisionMaster.getInsuredAddressLine2()).isEqualTo(UPDATED_INSURED_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimSubmisionMaster.getInsuredCity()).isEqualTo(UPDATED_INSURED_CITY);
        assertThat(testPrimaryClaimSubmisionMaster.getInsuredState()).isEqualTo(UPDATED_INSURED_STATE);
        assertThat(testPrimaryClaimSubmisionMaster.getInsuredZip()).isEqualTo(DEFAULT_INSURED_ZIP);
        assertThat(testPrimaryClaimSubmisionMaster.getInsuredContactNo()).isEqualTo(DEFAULT_INSURED_CONTACT_NO);
        assertThat(testPrimaryClaimSubmisionMaster.getInsuredDob()).isEqualTo(UPDATED_INSURED_DOB);
        assertThat(testPrimaryClaimSubmisionMaster.getInsuredGender()).isEqualTo(UPDATED_INSURED_GENDER);
        assertThat(testPrimaryClaimSubmisionMaster.getOrderingProviderFirstName()).isEqualTo(UPDATED_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getOrderingProviderLastName()).isEqualTo(UPDATED_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getOrderingProviderNpi()).isEqualTo(UPDATED_ORDERING_PROVIDER_NPI);
        assertThat(testPrimaryClaimSubmisionMaster.getPatientRelationshipInsured()).isEqualTo(DEFAULT_PATIENT_RELATIONSHIP_INSURED);
        assertThat(testPrimaryClaimSubmisionMaster.getPatientConditionEmployment()).isEqualTo(DEFAULT_PATIENT_CONDITION_EMPLOYMENT);
        assertThat(testPrimaryClaimSubmisionMaster.getPatientConditionAutoAccident()).isEqualTo(DEFAULT_PATIENT_CONDITION_AUTO_ACCIDENT);
        assertThat(testPrimaryClaimSubmisionMaster.getPatientConditionOtherAccident()).isEqualTo(UPDATED_PATIENT_CONDITION_OTHER_ACCIDENT);
        assertThat(testPrimaryClaimSubmisionMaster.getIsNextLevelInsurerPresentStatus())
            .isEqualTo(DEFAULT_IS_NEXT_LEVEL_INSURER_PRESENT_STATUS);
        assertThat(testPrimaryClaimSubmisionMaster.getOriginalDos()).isEqualTo(UPDATED_ORIGINAL_DOS);
        assertThat(testPrimaryClaimSubmisionMaster.getParNo()).isEqualTo(DEFAULT_PAR_NO);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderTaxonomy()).isEqualTo(DEFAULT_BILLING_PROVIDER_TAXONOMY);
        assertThat(testPrimaryClaimSubmisionMaster.getServiceProviderNpi()).isEqualTo(DEFAULT_SERVICE_PROVIDER_NPI);
        assertThat(testPrimaryClaimSubmisionMaster.getServiceProviderOrganisationName())
            .isEqualTo(UPDATED_SERVICE_PROVIDER_ORGANISATION_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getServiceProviderAddressLine1()).isEqualTo(DEFAULT_SERVICE_PROVIDER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimSubmisionMaster.getServiceProviderAddressLine2()).isEqualTo(DEFAULT_SERVICE_PROVIDER_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimSubmisionMaster.getServiceProviderCity()).isEqualTo(UPDATED_SERVICE_PROVIDER_CITY);
        assertThat(testPrimaryClaimSubmisionMaster.getServiceProviderState()).isEqualTo(DEFAULT_SERVICE_PROVIDER_STATE);
        assertThat(testPrimaryClaimSubmisionMaster.getServiceProviderCountry()).isEqualTo(DEFAULT_SERVICE_PROVIDER_COUNTRY);
        assertThat(testPrimaryClaimSubmisionMaster.getServiceProviderZipCode()).isEqualTo(UPDATED_SERVICE_PROVIDER_ZIP_CODE);
        assertThat(testPrimaryClaimSubmisionMaster.getServiceProviderTaxonomy()).isEqualTo(UPDATED_SERVICE_PROVIDER_TAXONOMY);
        assertThat(testPrimaryClaimSubmisionMaster.getCms1500FormName()).isEqualTo(UPDATED_CMS_1500_FORM_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getTradingPartnerAddressLine1()).isEqualTo(UPDATED_TRADING_PARTNER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimSubmisionMaster.getTradingPartnerAddressLine2()).isEqualTo(DEFAULT_TRADING_PARTNER_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimSubmisionMaster.getTradingPatnerCity()).isEqualTo(UPDATED_TRADING_PATNER_CITY);
        assertThat(testPrimaryClaimSubmisionMaster.getTradingPartnerState()).isEqualTo(DEFAULT_TRADING_PARTNER_STATE);
        assertThat(testPrimaryClaimSubmisionMaster.getTradingPartnerZip()).isEqualTo(UPDATED_TRADING_PARTNER_ZIP);
        assertThat(testPrimaryClaimSubmisionMaster.getDiagnosisCodeType()).isEqualTo(DEFAULT_DIAGNOSIS_CODE_TYPE);
        assertThat(testPrimaryClaimSubmisionMaster.getPrimaryClaimSubmisionMasterUuid())
            .isEqualTo(DEFAULT_PRIMARY_CLAIM_SUBMISION_MASTER_UUID);
    }

    @Test
    void fullUpdatePrimaryClaimSubmisionMasterWithPatch() throws Exception {
        // Initialize the database
        primaryClaimSubmisionMasterRepository.save(primaryClaimSubmisionMaster).block();

        int databaseSizeBeforeUpdate = primaryClaimSubmisionMasterRepository.findAll().collectList().block().size();

        // Update the primaryClaimSubmisionMaster using partial update
        PrimaryClaimSubmisionMaster partialUpdatedPrimaryClaimSubmisionMaster = new PrimaryClaimSubmisionMaster();
        partialUpdatedPrimaryClaimSubmisionMaster.setChangeHealthPrimarySubmisionMasterId(
            primaryClaimSubmisionMaster.getChangeHealthPrimarySubmisionMasterId()
        );

        partialUpdatedPrimaryClaimSubmisionMaster
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
            .ipdatedById(UPDATED_IPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .billingProviderType(UPDATED_BILLING_PROVIDER_TYPE)
            .insertedByName(UPDATED_INSERTED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .status(UPDATED_STATUS)
            .billingProviderAddressLine2(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_2)
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
            .tradingPatnerCity(UPDATED_TRADING_PATNER_CITY)
            .tradingPartnerState(UPDATED_TRADING_PARTNER_STATE)
            .tradingPartnerZip(UPDATED_TRADING_PARTNER_ZIP)
            .diagnosisCodeType(UPDATED_DIAGNOSIS_CODE_TYPE)
            .primaryClaimSubmisionMasterUuid(UPDATED_PRIMARY_CLAIM_SUBMISION_MASTER_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPrimaryClaimSubmisionMaster.getChangeHealthPrimarySubmisionMasterId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPrimaryClaimSubmisionMaster))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PrimaryClaimSubmisionMaster in the database
        List<PrimaryClaimSubmisionMaster> primaryClaimSubmisionMasterList = primaryClaimSubmisionMasterRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimSubmisionMasterList).hasSize(databaseSizeBeforeUpdate);
        PrimaryClaimSubmisionMaster testPrimaryClaimSubmisionMaster = primaryClaimSubmisionMasterList.get(
            primaryClaimSubmisionMasterList.size() - 1
        );
        assertThat(testPrimaryClaimSubmisionMaster.getSalesOrderId()).isEqualTo(UPDATED_SALES_ORDER_ID);
        assertThat(testPrimaryClaimSubmisionMaster.getClaimControlNo()).isEqualTo(UPDATED_CLAIM_CONTROL_NO);
        assertThat(testPrimaryClaimSubmisionMaster.getTradingPartnerServiceId()).isEqualTo(UPDATED_TRADING_PARTNER_SERVICE_ID);
        assertThat(testPrimaryClaimSubmisionMaster.getTradingPartnerName()).isEqualTo(UPDATED_TRADING_PARTNER_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getSubmitterOrganizationName()).isEqualTo(UPDATED_SUBMITTER_ORGANIZATION_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getSubmitterContactPersonName()).isEqualTo(UPDATED_SUBMITTER_CONTACT_PERSON_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getSubmitterContactNo()).isEqualTo(UPDATED_SUBMITTER_CONTACT_NO);
        assertThat(testPrimaryClaimSubmisionMaster.getReceiverOrganizationName()).isEqualTo(UPDATED_RECEIVER_ORGANIZATION_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getSubscriberMemberIdNo()).isEqualTo(UPDATED_SUBSCRIBER_MEMBER_ID_NO);
        assertThat(testPrimaryClaimSubmisionMaster.getSubscriberPaymentResponsibilityLevelCode())
            .isEqualTo(UPDATED_SUBSCRIBER_PAYMENT_RESPONSIBILITY_LEVEL_CODE);
        assertThat(testPrimaryClaimSubmisionMaster.getSubscriberFirstName()).isEqualTo(UPDATED_SUBSCRIBER_FIRST_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getSubscriberLastName()).isEqualTo(UPDATED_SUBSCRIBER_LAST_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getSubscriberGender()).isEqualTo(UPDATED_SUBSCRIBER_GENDER);
        assertThat(testPrimaryClaimSubmisionMaster.getSubscriberDob()).isEqualTo(UPDATED_SUBSCRIBER_DOB);
        assertThat(testPrimaryClaimSubmisionMaster.getPrimaryInsurerPolicyNo()).isEqualTo(UPDATED_PRIMARY_INSURER_POLICY_NO);
        assertThat(testPrimaryClaimSubmisionMaster.getSubscriberAddressLine1()).isEqualTo(UPDATED_SUBSCRIBER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimSubmisionMaster.getSubscriberCity()).isEqualTo(UPDATED_SUBSCRIBER_CITY);
        assertThat(testPrimaryClaimSubmisionMaster.getSubscriberState()).isEqualTo(UPDATED_SUBSCRIBER_STATE);
        assertThat(testPrimaryClaimSubmisionMaster.getSubscriberZipCode()).isEqualTo(UPDATED_SUBSCRIBER_ZIP_CODE);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderNpi()).isEqualTo(UPDATED_BILLING_PROVIDER_NPI);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderEin()).isEqualTo(UPDATED_BILLING_PROVIDER_EIN);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderOrganizationName())
            .isEqualTo(UPDATED_BILLING_PROVIDER_ORGANIZATION_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderAddressLine1()).isEqualTo(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderCity()).isEqualTo(UPDATED_BILLING_PROVIDER_CITY);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderState()).isEqualTo(UPDATED_BILLING_PROVIDER_STATE);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderZipCode()).isEqualTo(UPDATED_BILLING_PROVIDER_ZIP_CODE);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderContactPersonName())
            .isEqualTo(UPDATED_BILLING_PROVIDER_CONTACT_PERSON_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderContactNo()).isEqualTo(UPDATED_BILLING_PROVIDER_CONTACT_NO);
        assertThat(testPrimaryClaimSubmisionMaster.getClaimFilingCode()).isEqualTo(UPDATED_CLAIM_FILING_CODE);
        assertThat(testPrimaryClaimSubmisionMaster.getClaimChargeAmount()).isEqualTo(UPDATED_CLAIM_CHARGE_AMOUNT);
        assertThat(testPrimaryClaimSubmisionMaster.getPosCode()).isEqualTo(UPDATED_POS_CODE);
        assertThat(testPrimaryClaimSubmisionMaster.getClaimFrequencyCode()).isEqualTo(UPDATED_CLAIM_FREQUENCY_CODE);
        assertThat(testPrimaryClaimSubmisionMaster.getSignatureIndicator()).isEqualTo(UPDATED_SIGNATURE_INDICATOR);
        assertThat(testPrimaryClaimSubmisionMaster.getPlanParticipationCode()).isEqualTo(UPDATED_PLAN_PARTICIPATION_CODE);
        assertThat(testPrimaryClaimSubmisionMaster.getBenefitsAssignmentCertificationIndicator())
            .isEqualTo(UPDATED_BENEFITS_ASSIGNMENT_CERTIFICATION_INDICATOR);
        assertThat(testPrimaryClaimSubmisionMaster.getReleaseInformationCode()).isEqualTo(UPDATED_RELEASE_INFORMATION_CODE);
        assertThat(testPrimaryClaimSubmisionMaster.getPrimaryDiagnosis()).isEqualTo(UPDATED_PRIMARY_DIAGNOSIS);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode1()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_1);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode2()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_2);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode3()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_3);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode4()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_4);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode5()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_5);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode6()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_6);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode7()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_7);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode8()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_8);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode9()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_9);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode10()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_10);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode11()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_11);
        assertThat(testPrimaryClaimSubmisionMaster.getIcd10DiagnosisCode12()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_12);
        assertThat(testPrimaryClaimSubmisionMaster.getInsertedById()).isEqualTo(UPDATED_INSERTED_BY_ID);
        assertThat(testPrimaryClaimSubmisionMaster.getInsertedDate()).isEqualTo(UPDATED_INSERTED_DATE);
        assertThat(testPrimaryClaimSubmisionMaster.getIpdatedById()).isEqualTo(UPDATED_IPDATED_BY_ID);
        assertThat(testPrimaryClaimSubmisionMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderType()).isEqualTo(UPDATED_BILLING_PROVIDER_TYPE);
        assertThat(testPrimaryClaimSubmisionMaster.getInsertedByName()).isEqualTo(UPDATED_INSERTED_BY_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderAddressLine2()).isEqualTo(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimSubmisionMaster.getInsuredFirstName()).isEqualTo(UPDATED_INSURED_FIRST_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getInsuredLastName()).isEqualTo(UPDATED_INSURED_LAST_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getInsuredAddressLine1()).isEqualTo(UPDATED_INSURED_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimSubmisionMaster.getInsuredAddressLine2()).isEqualTo(UPDATED_INSURED_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimSubmisionMaster.getInsuredCity()).isEqualTo(UPDATED_INSURED_CITY);
        assertThat(testPrimaryClaimSubmisionMaster.getInsuredState()).isEqualTo(UPDATED_INSURED_STATE);
        assertThat(testPrimaryClaimSubmisionMaster.getInsuredZip()).isEqualTo(UPDATED_INSURED_ZIP);
        assertThat(testPrimaryClaimSubmisionMaster.getInsuredContactNo()).isEqualTo(UPDATED_INSURED_CONTACT_NO);
        assertThat(testPrimaryClaimSubmisionMaster.getInsuredDob()).isEqualTo(UPDATED_INSURED_DOB);
        assertThat(testPrimaryClaimSubmisionMaster.getInsuredGender()).isEqualTo(UPDATED_INSURED_GENDER);
        assertThat(testPrimaryClaimSubmisionMaster.getOrderingProviderFirstName()).isEqualTo(UPDATED_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getOrderingProviderLastName()).isEqualTo(UPDATED_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getOrderingProviderNpi()).isEqualTo(UPDATED_ORDERING_PROVIDER_NPI);
        assertThat(testPrimaryClaimSubmisionMaster.getPatientRelationshipInsured()).isEqualTo(UPDATED_PATIENT_RELATIONSHIP_INSURED);
        assertThat(testPrimaryClaimSubmisionMaster.getPatientConditionEmployment()).isEqualTo(UPDATED_PATIENT_CONDITION_EMPLOYMENT);
        assertThat(testPrimaryClaimSubmisionMaster.getPatientConditionAutoAccident()).isEqualTo(UPDATED_PATIENT_CONDITION_AUTO_ACCIDENT);
        assertThat(testPrimaryClaimSubmisionMaster.getPatientConditionOtherAccident()).isEqualTo(UPDATED_PATIENT_CONDITION_OTHER_ACCIDENT);
        assertThat(testPrimaryClaimSubmisionMaster.getIsNextLevelInsurerPresentStatus())
            .isEqualTo(UPDATED_IS_NEXT_LEVEL_INSURER_PRESENT_STATUS);
        assertThat(testPrimaryClaimSubmisionMaster.getOriginalDos()).isEqualTo(UPDATED_ORIGINAL_DOS);
        assertThat(testPrimaryClaimSubmisionMaster.getParNo()).isEqualTo(UPDATED_PAR_NO);
        assertThat(testPrimaryClaimSubmisionMaster.getBillingProviderTaxonomy()).isEqualTo(UPDATED_BILLING_PROVIDER_TAXONOMY);
        assertThat(testPrimaryClaimSubmisionMaster.getServiceProviderNpi()).isEqualTo(UPDATED_SERVICE_PROVIDER_NPI);
        assertThat(testPrimaryClaimSubmisionMaster.getServiceProviderOrganisationName())
            .isEqualTo(UPDATED_SERVICE_PROVIDER_ORGANISATION_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getServiceProviderAddressLine1()).isEqualTo(UPDATED_SERVICE_PROVIDER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimSubmisionMaster.getServiceProviderAddressLine2()).isEqualTo(UPDATED_SERVICE_PROVIDER_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimSubmisionMaster.getServiceProviderCity()).isEqualTo(UPDATED_SERVICE_PROVIDER_CITY);
        assertThat(testPrimaryClaimSubmisionMaster.getServiceProviderState()).isEqualTo(UPDATED_SERVICE_PROVIDER_STATE);
        assertThat(testPrimaryClaimSubmisionMaster.getServiceProviderCountry()).isEqualTo(UPDATED_SERVICE_PROVIDER_COUNTRY);
        assertThat(testPrimaryClaimSubmisionMaster.getServiceProviderZipCode()).isEqualTo(UPDATED_SERVICE_PROVIDER_ZIP_CODE);
        assertThat(testPrimaryClaimSubmisionMaster.getServiceProviderTaxonomy()).isEqualTo(UPDATED_SERVICE_PROVIDER_TAXONOMY);
        assertThat(testPrimaryClaimSubmisionMaster.getCms1500FormName()).isEqualTo(UPDATED_CMS_1500_FORM_NAME);
        assertThat(testPrimaryClaimSubmisionMaster.getTradingPartnerAddressLine1()).isEqualTo(UPDATED_TRADING_PARTNER_ADDRESS_LINE_1);
        assertThat(testPrimaryClaimSubmisionMaster.getTradingPartnerAddressLine2()).isEqualTo(UPDATED_TRADING_PARTNER_ADDRESS_LINE_2);
        assertThat(testPrimaryClaimSubmisionMaster.getTradingPatnerCity()).isEqualTo(UPDATED_TRADING_PATNER_CITY);
        assertThat(testPrimaryClaimSubmisionMaster.getTradingPartnerState()).isEqualTo(UPDATED_TRADING_PARTNER_STATE);
        assertThat(testPrimaryClaimSubmisionMaster.getTradingPartnerZip()).isEqualTo(UPDATED_TRADING_PARTNER_ZIP);
        assertThat(testPrimaryClaimSubmisionMaster.getDiagnosisCodeType()).isEqualTo(UPDATED_DIAGNOSIS_CODE_TYPE);
        assertThat(testPrimaryClaimSubmisionMaster.getPrimaryClaimSubmisionMasterUuid())
            .isEqualTo(UPDATED_PRIMARY_CLAIM_SUBMISION_MASTER_UUID);
    }

    @Test
    void patchNonExistingPrimaryClaimSubmisionMaster() throws Exception {
        int databaseSizeBeforeUpdate = primaryClaimSubmisionMasterRepository.findAll().collectList().block().size();
        primaryClaimSubmisionMaster.setChangeHealthPrimarySubmisionMasterId(count.incrementAndGet());

        // Create the PrimaryClaimSubmisionMaster
        PrimaryClaimSubmisionMasterDTO primaryClaimSubmisionMasterDTO = primaryClaimSubmisionMasterMapper.toDto(
            primaryClaimSubmisionMaster
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, primaryClaimSubmisionMasterDTO.getChangeHealthPrimarySubmisionMasterId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(primaryClaimSubmisionMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PrimaryClaimSubmisionMaster in the database
        List<PrimaryClaimSubmisionMaster> primaryClaimSubmisionMasterList = primaryClaimSubmisionMasterRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimSubmisionMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchPrimaryClaimSubmisionMaster() throws Exception {
        int databaseSizeBeforeUpdate = primaryClaimSubmisionMasterRepository.findAll().collectList().block().size();
        primaryClaimSubmisionMaster.setChangeHealthPrimarySubmisionMasterId(count.incrementAndGet());

        // Create the PrimaryClaimSubmisionMaster
        PrimaryClaimSubmisionMasterDTO primaryClaimSubmisionMasterDTO = primaryClaimSubmisionMasterMapper.toDto(
            primaryClaimSubmisionMaster
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(primaryClaimSubmisionMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PrimaryClaimSubmisionMaster in the database
        List<PrimaryClaimSubmisionMaster> primaryClaimSubmisionMasterList = primaryClaimSubmisionMasterRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimSubmisionMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamPrimaryClaimSubmisionMaster() throws Exception {
        int databaseSizeBeforeUpdate = primaryClaimSubmisionMasterRepository.findAll().collectList().block().size();
        primaryClaimSubmisionMaster.setChangeHealthPrimarySubmisionMasterId(count.incrementAndGet());

        // Create the PrimaryClaimSubmisionMaster
        PrimaryClaimSubmisionMasterDTO primaryClaimSubmisionMasterDTO = primaryClaimSubmisionMasterMapper.toDto(
            primaryClaimSubmisionMaster
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(primaryClaimSubmisionMasterDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PrimaryClaimSubmisionMaster in the database
        List<PrimaryClaimSubmisionMaster> primaryClaimSubmisionMasterList = primaryClaimSubmisionMasterRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimSubmisionMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deletePrimaryClaimSubmisionMaster() {
        // Initialize the database
        primaryClaimSubmisionMasterRepository.save(primaryClaimSubmisionMaster).block();

        int databaseSizeBeforeDelete = primaryClaimSubmisionMasterRepository.findAll().collectList().block().size();

        // Delete the primaryClaimSubmisionMaster
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, primaryClaimSubmisionMaster.getChangeHealthPrimarySubmisionMasterId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<PrimaryClaimSubmisionMaster> primaryClaimSubmisionMasterList = primaryClaimSubmisionMasterRepository
            .findAll()
            .collectList()
            .block();
        assertThat(primaryClaimSubmisionMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
