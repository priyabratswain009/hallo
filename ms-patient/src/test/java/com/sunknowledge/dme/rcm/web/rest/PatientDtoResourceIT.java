package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.PatientDto;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.PatientDtoRepository;
import com.sunknowledge.dme.rcm.service.dto.PatientDtoDTO;
import com.sunknowledge.dme.rcm.service.mapper.PatientDtoMapper;
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
 * Integration tests for the {@link PatientDtoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class PatientDtoResourceIT {

    private static final Long DEFAULT_PATIENT_ID = 1L;
    private static final Long UPDATED_PATIENT_ID = 2L;

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

    private static final String DEFAULT_TAX_ZONE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TAX_ZONE_NAME = "BBBBBBBBBB";

    private static final Double DEFAULT_TAX_RATE = 1D;
    private static final Double UPDATED_TAX_RATE = 2D;

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

    private static final Long DEFAULT_PRIMARY_INSURANCE_ID = 1L;
    private static final Long UPDATED_PRIMARY_INSURANCE_ID = 2L;

    private static final String DEFAULT_PRIMARY_INSURANCE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_INSURANCE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_INSURANCE_PAYER_ID_NO = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_INSURANCE_PAYER_ID_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_INSURANCE_PAYER_CONTACT_NO = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_INSURANCE_PAYER_CONTACT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_INSURANCE_POLICY_NUM = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_INSURANCE_POLICY_NUM = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_INSURANCE_POLICY_GROUP_NUM = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_INSURANCE_POLICY_GROUP_NUM = "BBBBBBBBBB";

    private static final Long DEFAULT_PRIMARY_INSURANCE_POLICY_GROUP_ID = 1L;
    private static final Long UPDATED_PRIMARY_INSURANCE_POLICY_GROUP_ID = 2L;

    private static final LocalDate DEFAULT_PRIMARY_INSURANCE_POLICY_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PRIMARY_INSURANCE_POLICY_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_PRIMARY_INSURANCE_POLICY_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PRIMARY_INSURANCE_POLICY_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_PRIMARY_INSURANCE_PAY_PERCENTAGE = 1D;
    private static final Double UPDATED_PRIMARY_INSURANCE_PAY_PERCENTAGE = 2D;

    private static final Double DEFAULT_PRIMARY_INSURANCE_DEDUCTABLE_AMT = 1D;
    private static final Double UPDATED_PRIMARY_INSURANCE_DEDUCTABLE_AMT = 2D;

    private static final Long DEFAULT_SECONDARY_INSURANCE_ID = 1L;
    private static final Long UPDATED_SECONDARY_INSURANCE_ID = 2L;

    private static final String DEFAULT_SECONDARY_INSURANCE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY_INSURANCE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SECONDARY_INSURANCE_PAYER_ID_NO = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY_INSURANCE_PAYER_ID_NO = "BBBBBBBBBB";

    private static final String DEFAULT_SECONDARY_INSURANCE_PAYER_CONTACT_NO = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY_INSURANCE_PAYER_CONTACT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_SECONDARY_INSURANCE_POLICY_NUM = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY_INSURANCE_POLICY_NUM = "BBBBBBBBBB";

    private static final String DEFAULT_SECONDARY_INSURANCE_POLICY_GROUP_NUM = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY_INSURANCE_POLICY_GROUP_NUM = "BBBBBBBBBB";

    private static final Long DEFAULT_SECONDARY_INSURANCE_POLICY_GROUP_ID = 1L;
    private static final Long UPDATED_SECONDARY_INSURANCE_POLICY_GROUP_ID = 2L;

    private static final LocalDate DEFAULT_SECONDARY_INSURANCE_POLICY_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SECONDARY_INSURANCE_POLICY_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_SECONDARY_INSURANCE_POLICY_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SECONDARY_INSURANCE_POLICY_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_SECONDARY_INSURANCE_PAY_PERCENTAGE = 1D;
    private static final Double UPDATED_SECONDARY_INSURANCE_PAY_PERCENTAGE = 2D;

    private static final Double DEFAULT_SECONDARY_INSURANCE_DEDUCTABLE_AMT = 1D;
    private static final Double UPDATED_SECONDARY_INSURANCE_DEDUCTABLE_AMT = 2D;

    private static final Long DEFAULT_TERTIARY_INSURANCE_ID = 1L;
    private static final Long UPDATED_TERTIARY_INSURANCE_ID = 2L;

    private static final String DEFAULT_TERTIARY_INSURANCE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TERTIARY_INSURANCE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TERTIARY_INSURANCE_PAYER_ID_NO = "AAAAAAAAAA";
    private static final String UPDATED_TERTIARY_INSURANCE_PAYER_ID_NO = "BBBBBBBBBB";

    private static final String DEFAULT_TERTIARY_INSURANCE_PAYER_CONTACT_NO = "AAAAAAAAAA";
    private static final String UPDATED_TERTIARY_INSURANCE_PAYER_CONTACT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_TERTIARY_INSURANCE_POLICY_NUM = "AAAAAAAAAA";
    private static final String UPDATED_TERTIARY_INSURANCE_POLICY_NUM = "BBBBBBBBBB";

    private static final String DEFAULT_TERTIARY_INSURANCE_POLICY_GROUP_NUM = "AAAAAAAAAA";
    private static final String UPDATED_TERTIARY_INSURANCE_POLICY_GROUP_NUM = "BBBBBBBBBB";

    private static final Long DEFAULT_TERTIARY_INSURANCE_POLICY_GROUP_ID = 1L;
    private static final Long UPDATED_TERTIARY_INSURANCE_POLICY_GROUP_ID = 2L;

    private static final LocalDate DEFAULT_TERTIARY_INSURANCE_POLICY_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TERTIARY_INSURANCE_POLICY_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_TERTIARY_INSURANCE_POLICY_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TERTIARY_INSURANCE_POLICY_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_TERTIARY_INSURANCE_PAY_PERCENTAGE = 1D;
    private static final Double UPDATED_TERTIARY_INSURANCE_PAY_PERCENTAGE = 2D;

    private static final Double DEFAULT_TERTIARY_INSURANCE_DEDUCTABLE_AMT = 1D;
    private static final Double UPDATED_TERTIARY_INSURANCE_DEDUCTABLE_AMT = 2D;

    private static final String DEFAULT_RELATIONSHIP = "AAAAAAAAAA";
    private static final String UPDATED_RELATIONSHIP = "BBBBBBBBBB";

    private static final String DEFAULT_INSURED_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INSURED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_INSURED_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INSURED_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_INSURED_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INSURED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_INSURED_SUFFIX = "AAAAAAAAAA";
    private static final String UPDATED_INSURED_SUFFIX = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_INSURED_DOB = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_INSURED_DOB = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_INSURED_SSN = "AAAAAAAAAA";
    private static final String UPDATED_INSURED_SSN = "BBBBBBBBBB";

    private static final String DEFAULT_INSURED_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_INSURED_GENDER = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_INSURER_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_INSURER_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_INSURER_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_INSURER_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_INSURER_CITY = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_INSURER_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_INSURER_STATE = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_INSURER_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_INSURER_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_INSURER_ZIP = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_INSURER_CONTACT_1 = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_INSURER_CONTACT_1 = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_INSURER_FAX = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_INSURER_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_ALWAYS_CROSSOVER_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_ALWAYS_CROSSOVER_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_INSURANCE_MEMBER_ID = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_INSURANCE_MEMBER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SECONDARY_INSURANCE_MEMBER_ID = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY_INSURANCE_MEMBER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TERTIARY_INSURANCE_MEMBER_ID = "AAAAAAAAAA";
    private static final String UPDATED_TERTIARY_INSURANCE_MEMBER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_RELATIONSHIP_INSURED = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_RELATIONSHIP_INSURED = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_CONDITION_EMPLOYMENT = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_CONDITION_EMPLOYMENT = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_CONDITION_AUTO_ACCIDENT = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_CONDITION_AUTO_ACCIDENT = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_CONDITION_OTHER_ACCIDENT = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_CONDITION_OTHER_ACCIDENT = "BBBBBBBBBB";

    private static final String DEFAULT_INSURED_EMPLOYER = "AAAAAAAAAA";
    private static final String UPDATED_INSURED_EMPLOYER = "BBBBBBBBBB";

    private static final String DEFAULT_WORKERS_COMPENSATION_PAYER_ID_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_WORKERS_COMPENSATION_PAYER_ID_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_WORKERS_COMPENSATION_PLAN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_WORKERS_COMPENSATION_PLAN_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_WORKERS_COMPENSATION_ADDITIONAL_DTLS = "AAAAAAAAAA";
    private static final String UPDATED_WORKERS_COMPENSATION_ADDITIONAL_DTLS = "BBBBBBBBBB";

    private static final String DEFAULT_WORKERS_COMPENSATION_CLAIM_FILLING_CODE = "AAAAAAAAAA";
    private static final String UPDATED_WORKERS_COMPENSATION_CLAIM_FILLING_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_WORKERS_COMPENSATION_TPL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_WORKERS_COMPENSATION_TPL_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_WORKERS_COMPENSATION_TPL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_WORKERS_COMPENSATION_TPL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_WC_PROPERTY_CASUALTY_AGENCY_CLAIM_NO = "AAAAAAAAAA";
    private static final String UPDATED_WC_PROPERTY_CASUALTY_AGENCY_CLAIM_NO = "BBBBBBBBBB";

    private static final String DEFAULT_WC_CARRIER_ID = "AAAAAAAAAA";
    private static final String UPDATED_WC_CARRIER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYER_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYER_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYER_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYER_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYER_CITY = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYER_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYER_STATE = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYER_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYER_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYER_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYER_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYER_ZIP = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYER_CONTACT_NO_1 = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYER_CONTACT_NO_1 = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYER_CONTACT_NO_2 = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYER_CONTACT_NO_2 = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYER_FAX = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYER_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYER_EFAX = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYER_EFAX = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYER_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYER_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYEE_RELATIONSHIP = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYEE_RELATIONSHIP = "BBBBBBBBBB";

    private static final Double DEFAULT_HEIGHT = 1D;
    private static final Double UPDATED_HEIGHT = 2D;

    private static final Double DEFAULT_WEIGHT = 1D;
    private static final Double UPDATED_WEIGHT = 2D;

    private static final String DEFAULT_FUNCTIONAL_ABILITIES = "AAAAAAAAAA";
    private static final String UPDATED_FUNCTIONAL_ABILITIES = "BBBBBBBBBB";

    private static final String DEFAULT_INFECTION_CONDITION_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_INFECTION_CONDITION_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_DIABETES_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_DIABETES_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_DIAGNOSIS_CODE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_DIAGNOSIS_CODE_TYPE = "BBBBBBBBBB";

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

    private static final String DEFAULT_DOCTOR_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DOCTOR_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DOCTOR_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DOCTOR_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DOCTOR_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DOCTOR_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DOCTOR_NAME_SUFFIX = "AAAAAAAAAA";
    private static final String UPDATED_DOCTOR_NAME_SUFFIX = "BBBBBBBBBB";

    private static final String DEFAULT_DOCTOR_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_DOCTOR_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_DOCTOR_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_DOCTOR_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_DOCTOR_ADDRESS_CITY = "AAAAAAAAAA";
    private static final String UPDATED_DOCTOR_ADDRESS_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_DOCTOR_ADDRESS_STATE = "AAAAAAAAAA";
    private static final String UPDATED_DOCTOR_ADDRESS_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_DOCTOR_ADDRESS_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_DOCTOR_ADDRESS_ZIP = "BBBBBBBBBB";

    private static final String DEFAULT_DOCTOR_CONTACT_1 = "AAAAAAAAAA";
    private static final String UPDATED_DOCTOR_CONTACT_1 = "BBBBBBBBBB";

    private static final String DEFAULT_DOCTOR_CONTACT_2 = "AAAAAAAAAA";
    private static final String UPDATED_DOCTOR_CONTACT_2 = "BBBBBBBBBB";

    private static final String DEFAULT_DOCTOR_FAX = "AAAAAAAAAA";
    private static final String UPDATED_DOCTOR_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_DOCTOR_NPI_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_DOCTOR_NPI_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_DOCTOR_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_DOCTOR_GENDER = "BBBBBBBBBB";

    private static final String DEFAULT_DOCTOR_TAXONOMY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_DOCTOR_TAXONOMY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DOCTOR_TAXONOMY_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DOCTOR_TAXONOMY_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_DOCTOR_PRACTICE_STATE = "AAAAAAAAAA";
    private static final String UPDATED_DOCTOR_PRACTICE_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_DOCTOR_LICENSE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_DOCTOR_LICENSE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final UUID DEFAULT_PATIENT_DTO_UUID = UUID.randomUUID();
    private static final UUID UPDATED_PATIENT_DTO_UUID = UUID.randomUUID();

    private static final String DEFAULT_SECONDARY_INSURER_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY_INSURER_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_SECONDARY_INSURER_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY_INSURER_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_SECONDARY_INSURER_CITY = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY_INSURER_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_SECONDARY_INSURER_STATE = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY_INSURER_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_SECONDARY_INSURER_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY_INSURER_ZIP = "BBBBBBBBBB";

    private static final String DEFAULT_SECONDARY_INSURER_CONTACT_1 = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY_INSURER_CONTACT_1 = "BBBBBBBBBB";

    private static final String DEFAULT_SECONDARY_INSURER_FAX = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY_INSURER_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_TERTIARY_INSURER_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_TERTIARY_INSURER_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_TERTIARY_INSURER_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_TERTIARY_INSURER_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_TERTIARY_INSURER_CITY = "AAAAAAAAAA";
    private static final String UPDATED_TERTIARY_INSURER_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_TERTIARY_INSURER_STATE = "AAAAAAAAAA";
    private static final String UPDATED_TERTIARY_INSURER_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_TERTIARY_INSURER_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_TERTIARY_INSURER_ZIP = "BBBBBBBBBB";

    private static final String DEFAULT_TERTIARY_INSURER_CONTACT_1 = "AAAAAAAAAA";
    private static final String UPDATED_TERTIARY_INSURER_CONTACT_1 = "BBBBBBBBBB";

    private static final String DEFAULT_TERTIARY_INSURER_FAX = "AAAAAAAAAA";
    private static final String UPDATED_TERTIARY_INSURER_FAX = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PATIENT_DOD = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PATIENT_DOD = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/patient-dtos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{patientDtoId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PatientDtoRepository patientDtoRepository;

    @Autowired
    private PatientDtoMapper patientDtoMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private PatientDto patientDto;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PatientDto createEntity(EntityManager em) {
        PatientDto patientDto = new PatientDto()
            .patientId(DEFAULT_PATIENT_ID)
            .patientFirstName(DEFAULT_PATIENT_FIRST_NAME)
            .patientMiddleName(DEFAULT_PATIENT_MIDDLE_NAME)
            .patientLastName(DEFAULT_PATIENT_LAST_NAME)
            .dob(DEFAULT_DOB)
            .gender(DEFAULT_GENDER)
            .ssn(DEFAULT_SSN)
            .taxZoneId(DEFAULT_TAX_ZONE_ID)
            .taxZoneName(DEFAULT_TAX_ZONE_NAME)
            .taxRate(DEFAULT_TAX_RATE)
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
            .branchName(DEFAULT_BRANCH_NAME)
            .billingAddressLine1(DEFAULT_BILLING_ADDRESS_LINE_1)
            .billingAddressLine2(DEFAULT_BILLING_ADDRESS_LINE_2)
            .billingAddressCity(DEFAULT_BILLING_ADDRESS_CITY)
            .billingAddressState(DEFAULT_BILLING_ADDRESS_STATE)
            .billingAddressZip(DEFAULT_BILLING_ADDRESS_ZIP)
            .caregiverName(DEFAULT_CAREGIVER_NAME)
            .caregiverContact(DEFAULT_CAREGIVER_CONTACT)
            .caregiverRelatinshipPatient(DEFAULT_CAREGIVER_RELATINSHIP_PATIENT)
            .primaryInsuranceId(DEFAULT_PRIMARY_INSURANCE_ID)
            .primaryInsuranceName(DEFAULT_PRIMARY_INSURANCE_NAME)
            .primaryInsurancePayerIdNo(DEFAULT_PRIMARY_INSURANCE_PAYER_ID_NO)
            .primaryInsurancePayerContactNo(DEFAULT_PRIMARY_INSURANCE_PAYER_CONTACT_NO)
            .primaryInsurancePolicyNum(DEFAULT_PRIMARY_INSURANCE_POLICY_NUM)
            .primaryInsurancePolicyGroupNum(DEFAULT_PRIMARY_INSURANCE_POLICY_GROUP_NUM)
            .primaryInsurancePolicyGroupId(DEFAULT_PRIMARY_INSURANCE_POLICY_GROUP_ID)
            .primaryInsurancePolicyStartDate(DEFAULT_PRIMARY_INSURANCE_POLICY_START_DATE)
            .primaryInsurancePolicyEndDate(DEFAULT_PRIMARY_INSURANCE_POLICY_END_DATE)
            .primaryInsurancePayPercentage(DEFAULT_PRIMARY_INSURANCE_PAY_PERCENTAGE)
            .primaryInsuranceDeductableAmt(DEFAULT_PRIMARY_INSURANCE_DEDUCTABLE_AMT)
            .secondaryInsuranceId(DEFAULT_SECONDARY_INSURANCE_ID)
            .secondaryInsuranceName(DEFAULT_SECONDARY_INSURANCE_NAME)
            .secondaryInsurancePayerIdNo(DEFAULT_SECONDARY_INSURANCE_PAYER_ID_NO)
            .secondaryInsurancePayerContactNo(DEFAULT_SECONDARY_INSURANCE_PAYER_CONTACT_NO)
            .secondaryInsurancePolicyNum(DEFAULT_SECONDARY_INSURANCE_POLICY_NUM)
            .secondaryInsurancePolicyGroupNum(DEFAULT_SECONDARY_INSURANCE_POLICY_GROUP_NUM)
            .secondaryInsurancePolicyGroupId(DEFAULT_SECONDARY_INSURANCE_POLICY_GROUP_ID)
            .secondaryInsurancePolicyStartDate(DEFAULT_SECONDARY_INSURANCE_POLICY_START_DATE)
            .secondaryInsurancePolicyEndDate(DEFAULT_SECONDARY_INSURANCE_POLICY_END_DATE)
            .secondaryInsurancePayPercentage(DEFAULT_SECONDARY_INSURANCE_PAY_PERCENTAGE)
            .secondaryInsuranceDeductableAmt(DEFAULT_SECONDARY_INSURANCE_DEDUCTABLE_AMT)
            .tertiaryInsuranceId(DEFAULT_TERTIARY_INSURANCE_ID)
            .tertiaryInsuranceName(DEFAULT_TERTIARY_INSURANCE_NAME)
            .tertiaryInsurancePayerIdNo(DEFAULT_TERTIARY_INSURANCE_PAYER_ID_NO)
            .tertiaryInsurancePayerContactNo(DEFAULT_TERTIARY_INSURANCE_PAYER_CONTACT_NO)
            .tertiaryInsurancePolicyNum(DEFAULT_TERTIARY_INSURANCE_POLICY_NUM)
            .tertiaryInsurancePolicyGroupNum(DEFAULT_TERTIARY_INSURANCE_POLICY_GROUP_NUM)
            .tertiaryInsurancePolicyGroupId(DEFAULT_TERTIARY_INSURANCE_POLICY_GROUP_ID)
            .tertiaryInsurancePolicyStartDate(DEFAULT_TERTIARY_INSURANCE_POLICY_START_DATE)
            .tertiaryInsurancePolicyEndDate(DEFAULT_TERTIARY_INSURANCE_POLICY_END_DATE)
            .tertiaryInsurancePayPercentage(DEFAULT_TERTIARY_INSURANCE_PAY_PERCENTAGE)
            .tertiaryInsuranceDeductableAmt(DEFAULT_TERTIARY_INSURANCE_DEDUCTABLE_AMT)
            .relationship(DEFAULT_RELATIONSHIP)
            .insuredFirstName(DEFAULT_INSURED_FIRST_NAME)
            .insuredMiddleName(DEFAULT_INSURED_MIDDLE_NAME)
            .insuredLastName(DEFAULT_INSURED_LAST_NAME)
            .insuredSuffix(DEFAULT_INSURED_SUFFIX)
            .insuredDob(DEFAULT_INSURED_DOB)
            .insuredSsn(DEFAULT_INSURED_SSN)
            .insuredGender(DEFAULT_INSURED_GENDER)
            .primaryInsurerAddressLine1(DEFAULT_PRIMARY_INSURER_ADDRESS_LINE_1)
            .primaryInsurerAddressLine2(DEFAULT_PRIMARY_INSURER_ADDRESS_LINE_2)
            .primaryInsurerCity(DEFAULT_PRIMARY_INSURER_CITY)
            .primaryInsurerState(DEFAULT_PRIMARY_INSURER_STATE)
            .primaryInsurerZip(DEFAULT_PRIMARY_INSURER_ZIP)
            .primaryInsurerContact1(DEFAULT_PRIMARY_INSURER_CONTACT_1)
            .primaryInsurerFax(DEFAULT_PRIMARY_INSURER_FAX)
            .alwaysCrossoverStatus(DEFAULT_ALWAYS_CROSSOVER_STATUS)
            .primaryInsuranceMemberId(DEFAULT_PRIMARY_INSURANCE_MEMBER_ID)
            .secondaryInsuranceMemberId(DEFAULT_SECONDARY_INSURANCE_MEMBER_ID)
            .tertiaryInsuranceMemberId(DEFAULT_TERTIARY_INSURANCE_MEMBER_ID)
            .patientRelationshipInsured(DEFAULT_PATIENT_RELATIONSHIP_INSURED)
            .patientConditionEmployment(DEFAULT_PATIENT_CONDITION_EMPLOYMENT)
            .patientConditionAutoAccident(DEFAULT_PATIENT_CONDITION_AUTO_ACCIDENT)
            .patientConditionOtherAccident(DEFAULT_PATIENT_CONDITION_OTHER_ACCIDENT)
            .insuredEmployer(DEFAULT_INSURED_EMPLOYER)
            .workersCompensationPayerIdNumber(DEFAULT_WORKERS_COMPENSATION_PAYER_ID_NUMBER)
            .workersCompensationPlanName(DEFAULT_WORKERS_COMPENSATION_PLAN_NAME)
            .workersCompensationAdditionalDtls(DEFAULT_WORKERS_COMPENSATION_ADDITIONAL_DTLS)
            .workersCompensationClaimFillingCode(DEFAULT_WORKERS_COMPENSATION_CLAIM_FILLING_CODE)
            .workersCompensationTplCode(DEFAULT_WORKERS_COMPENSATION_TPL_CODE)
            .workersCompensationTplName(DEFAULT_WORKERS_COMPENSATION_TPL_NAME)
            .wcPropertyCasualtyAgencyClaimNo(DEFAULT_WC_PROPERTY_CASUALTY_AGENCY_CLAIM_NO)
            .wcCarrierId(DEFAULT_WC_CARRIER_ID)
            .employerAddressLine1(DEFAULT_EMPLOYER_ADDRESS_LINE_1)
            .employerAddressLine2(DEFAULT_EMPLOYER_ADDRESS_LINE_2)
            .employerCity(DEFAULT_EMPLOYER_CITY)
            .employerState(DEFAULT_EMPLOYER_STATE)
            .employerCountry(DEFAULT_EMPLOYER_COUNTRY)
            .employerZip(DEFAULT_EMPLOYER_ZIP)
            .employerContactNo1(DEFAULT_EMPLOYER_CONTACT_NO_1)
            .employerContactNo2(DEFAULT_EMPLOYER_CONTACT_NO_2)
            .employerFax(DEFAULT_EMPLOYER_FAX)
            .employerEfax(DEFAULT_EMPLOYER_EFAX)
            .employerEmail(DEFAULT_EMPLOYER_EMAIL)
            .employeeRelationship(DEFAULT_EMPLOYEE_RELATIONSHIP)
            .height(DEFAULT_HEIGHT)
            .weight(DEFAULT_WEIGHT)
            .functionalAbilities(DEFAULT_FUNCTIONAL_ABILITIES)
            .infectionConditionStatus(DEFAULT_INFECTION_CONDITION_STATUS)
            .diabetesStatus(DEFAULT_DIABETES_STATUS)
            .diagnosisCodeType(DEFAULT_DIAGNOSIS_CODE_TYPE)
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
            .doctorFirstName(DEFAULT_DOCTOR_FIRST_NAME)
            .doctorMiddleName(DEFAULT_DOCTOR_MIDDLE_NAME)
            .doctorLastName(DEFAULT_DOCTOR_LAST_NAME)
            .doctorNameSuffix(DEFAULT_DOCTOR_NAME_SUFFIX)
            .doctorAddressLine1(DEFAULT_DOCTOR_ADDRESS_LINE_1)
            .doctorAddressLine2(DEFAULT_DOCTOR_ADDRESS_LINE_2)
            .doctorAddressCity(DEFAULT_DOCTOR_ADDRESS_CITY)
            .doctorAddressState(DEFAULT_DOCTOR_ADDRESS_STATE)
            .doctorAddressZip(DEFAULT_DOCTOR_ADDRESS_ZIP)
            .doctorContact1(DEFAULT_DOCTOR_CONTACT_1)
            .doctorContact2(DEFAULT_DOCTOR_CONTACT_2)
            .doctorFax(DEFAULT_DOCTOR_FAX)
            .doctorNpiNumber(DEFAULT_DOCTOR_NPI_NUMBER)
            .doctorGender(DEFAULT_DOCTOR_GENDER)
            .doctorTaxonomyCode(DEFAULT_DOCTOR_TAXONOMY_CODE)
            .doctorTaxonomyDescription(DEFAULT_DOCTOR_TAXONOMY_DESCRIPTION)
            .doctorPracticeState(DEFAULT_DOCTOR_PRACTICE_STATE)
            .doctorLicenseNumber(DEFAULT_DOCTOR_LICENSE_NUMBER)
            .status(DEFAULT_STATUS)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .patientDtoUuid(DEFAULT_PATIENT_DTO_UUID)
            .secondaryInsurerAddressLine1(DEFAULT_SECONDARY_INSURER_ADDRESS_LINE_1)
            .secondaryInsurerAddressLine2(DEFAULT_SECONDARY_INSURER_ADDRESS_LINE_2)
            .secondaryInsurerCity(DEFAULT_SECONDARY_INSURER_CITY)
            .secondaryInsurerState(DEFAULT_SECONDARY_INSURER_STATE)
            .secondaryInsurerZip(DEFAULT_SECONDARY_INSURER_ZIP)
            .secondaryInsurerContact1(DEFAULT_SECONDARY_INSURER_CONTACT_1)
            .secondaryInsurerFax(DEFAULT_SECONDARY_INSURER_FAX)
            .tertiaryInsurerAddressLine1(DEFAULT_TERTIARY_INSURER_ADDRESS_LINE_1)
            .tertiaryInsurerAddressLine2(DEFAULT_TERTIARY_INSURER_ADDRESS_LINE_2)
            .tertiaryInsurerCity(DEFAULT_TERTIARY_INSURER_CITY)
            .tertiaryInsurerState(DEFAULT_TERTIARY_INSURER_STATE)
            .tertiaryInsurerZip(DEFAULT_TERTIARY_INSURER_ZIP)
            .tertiaryInsurerContact1(DEFAULT_TERTIARY_INSURER_CONTACT_1)
            .tertiaryInsurerFax(DEFAULT_TERTIARY_INSURER_FAX)
            .patientDod(DEFAULT_PATIENT_DOD);
        return patientDto;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PatientDto createUpdatedEntity(EntityManager em) {
        PatientDto patientDto = new PatientDto()
            .patientId(UPDATED_PATIENT_ID)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientMiddleName(UPDATED_PATIENT_MIDDLE_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .dob(UPDATED_DOB)
            .gender(UPDATED_GENDER)
            .ssn(UPDATED_SSN)
            .taxZoneId(UPDATED_TAX_ZONE_ID)
            .taxZoneName(UPDATED_TAX_ZONE_NAME)
            .taxRate(UPDATED_TAX_RATE)
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
            .branchName(UPDATED_BRANCH_NAME)
            .billingAddressLine1(UPDATED_BILLING_ADDRESS_LINE_1)
            .billingAddressLine2(UPDATED_BILLING_ADDRESS_LINE_2)
            .billingAddressCity(UPDATED_BILLING_ADDRESS_CITY)
            .billingAddressState(UPDATED_BILLING_ADDRESS_STATE)
            .billingAddressZip(UPDATED_BILLING_ADDRESS_ZIP)
            .caregiverName(UPDATED_CAREGIVER_NAME)
            .caregiverContact(UPDATED_CAREGIVER_CONTACT)
            .caregiverRelatinshipPatient(UPDATED_CAREGIVER_RELATINSHIP_PATIENT)
            .primaryInsuranceId(UPDATED_PRIMARY_INSURANCE_ID)
            .primaryInsuranceName(UPDATED_PRIMARY_INSURANCE_NAME)
            .primaryInsurancePayerIdNo(UPDATED_PRIMARY_INSURANCE_PAYER_ID_NO)
            .primaryInsurancePayerContactNo(UPDATED_PRIMARY_INSURANCE_PAYER_CONTACT_NO)
            .primaryInsurancePolicyNum(UPDATED_PRIMARY_INSURANCE_POLICY_NUM)
            .primaryInsurancePolicyGroupNum(UPDATED_PRIMARY_INSURANCE_POLICY_GROUP_NUM)
            .primaryInsurancePolicyGroupId(UPDATED_PRIMARY_INSURANCE_POLICY_GROUP_ID)
            .primaryInsurancePolicyStartDate(UPDATED_PRIMARY_INSURANCE_POLICY_START_DATE)
            .primaryInsurancePolicyEndDate(UPDATED_PRIMARY_INSURANCE_POLICY_END_DATE)
            .primaryInsurancePayPercentage(UPDATED_PRIMARY_INSURANCE_PAY_PERCENTAGE)
            .primaryInsuranceDeductableAmt(UPDATED_PRIMARY_INSURANCE_DEDUCTABLE_AMT)
            .secondaryInsuranceId(UPDATED_SECONDARY_INSURANCE_ID)
            .secondaryInsuranceName(UPDATED_SECONDARY_INSURANCE_NAME)
            .secondaryInsurancePayerIdNo(UPDATED_SECONDARY_INSURANCE_PAYER_ID_NO)
            .secondaryInsurancePayerContactNo(UPDATED_SECONDARY_INSURANCE_PAYER_CONTACT_NO)
            .secondaryInsurancePolicyNum(UPDATED_SECONDARY_INSURANCE_POLICY_NUM)
            .secondaryInsurancePolicyGroupNum(UPDATED_SECONDARY_INSURANCE_POLICY_GROUP_NUM)
            .secondaryInsurancePolicyGroupId(UPDATED_SECONDARY_INSURANCE_POLICY_GROUP_ID)
            .secondaryInsurancePolicyStartDate(UPDATED_SECONDARY_INSURANCE_POLICY_START_DATE)
            .secondaryInsurancePolicyEndDate(UPDATED_SECONDARY_INSURANCE_POLICY_END_DATE)
            .secondaryInsurancePayPercentage(UPDATED_SECONDARY_INSURANCE_PAY_PERCENTAGE)
            .secondaryInsuranceDeductableAmt(UPDATED_SECONDARY_INSURANCE_DEDUCTABLE_AMT)
            .tertiaryInsuranceId(UPDATED_TERTIARY_INSURANCE_ID)
            .tertiaryInsuranceName(UPDATED_TERTIARY_INSURANCE_NAME)
            .tertiaryInsurancePayerIdNo(UPDATED_TERTIARY_INSURANCE_PAYER_ID_NO)
            .tertiaryInsurancePayerContactNo(UPDATED_TERTIARY_INSURANCE_PAYER_CONTACT_NO)
            .tertiaryInsurancePolicyNum(UPDATED_TERTIARY_INSURANCE_POLICY_NUM)
            .tertiaryInsurancePolicyGroupNum(UPDATED_TERTIARY_INSURANCE_POLICY_GROUP_NUM)
            .tertiaryInsurancePolicyGroupId(UPDATED_TERTIARY_INSURANCE_POLICY_GROUP_ID)
            .tertiaryInsurancePolicyStartDate(UPDATED_TERTIARY_INSURANCE_POLICY_START_DATE)
            .tertiaryInsurancePolicyEndDate(UPDATED_TERTIARY_INSURANCE_POLICY_END_DATE)
            .tertiaryInsurancePayPercentage(UPDATED_TERTIARY_INSURANCE_PAY_PERCENTAGE)
            .tertiaryInsuranceDeductableAmt(UPDATED_TERTIARY_INSURANCE_DEDUCTABLE_AMT)
            .relationship(UPDATED_RELATIONSHIP)
            .insuredFirstName(UPDATED_INSURED_FIRST_NAME)
            .insuredMiddleName(UPDATED_INSURED_MIDDLE_NAME)
            .insuredLastName(UPDATED_INSURED_LAST_NAME)
            .insuredSuffix(UPDATED_INSURED_SUFFIX)
            .insuredDob(UPDATED_INSURED_DOB)
            .insuredSsn(UPDATED_INSURED_SSN)
            .insuredGender(UPDATED_INSURED_GENDER)
            .primaryInsurerAddressLine1(UPDATED_PRIMARY_INSURER_ADDRESS_LINE_1)
            .primaryInsurerAddressLine2(UPDATED_PRIMARY_INSURER_ADDRESS_LINE_2)
            .primaryInsurerCity(UPDATED_PRIMARY_INSURER_CITY)
            .primaryInsurerState(UPDATED_PRIMARY_INSURER_STATE)
            .primaryInsurerZip(UPDATED_PRIMARY_INSURER_ZIP)
            .primaryInsurerContact1(UPDATED_PRIMARY_INSURER_CONTACT_1)
            .primaryInsurerFax(UPDATED_PRIMARY_INSURER_FAX)
            .alwaysCrossoverStatus(UPDATED_ALWAYS_CROSSOVER_STATUS)
            .primaryInsuranceMemberId(UPDATED_PRIMARY_INSURANCE_MEMBER_ID)
            .secondaryInsuranceMemberId(UPDATED_SECONDARY_INSURANCE_MEMBER_ID)
            .tertiaryInsuranceMemberId(UPDATED_TERTIARY_INSURANCE_MEMBER_ID)
            .patientRelationshipInsured(UPDATED_PATIENT_RELATIONSHIP_INSURED)
            .patientConditionEmployment(UPDATED_PATIENT_CONDITION_EMPLOYMENT)
            .patientConditionAutoAccident(UPDATED_PATIENT_CONDITION_AUTO_ACCIDENT)
            .patientConditionOtherAccident(UPDATED_PATIENT_CONDITION_OTHER_ACCIDENT)
            .insuredEmployer(UPDATED_INSURED_EMPLOYER)
            .workersCompensationPayerIdNumber(UPDATED_WORKERS_COMPENSATION_PAYER_ID_NUMBER)
            .workersCompensationPlanName(UPDATED_WORKERS_COMPENSATION_PLAN_NAME)
            .workersCompensationAdditionalDtls(UPDATED_WORKERS_COMPENSATION_ADDITIONAL_DTLS)
            .workersCompensationClaimFillingCode(UPDATED_WORKERS_COMPENSATION_CLAIM_FILLING_CODE)
            .workersCompensationTplCode(UPDATED_WORKERS_COMPENSATION_TPL_CODE)
            .workersCompensationTplName(UPDATED_WORKERS_COMPENSATION_TPL_NAME)
            .wcPropertyCasualtyAgencyClaimNo(UPDATED_WC_PROPERTY_CASUALTY_AGENCY_CLAIM_NO)
            .wcCarrierId(UPDATED_WC_CARRIER_ID)
            .employerAddressLine1(UPDATED_EMPLOYER_ADDRESS_LINE_1)
            .employerAddressLine2(UPDATED_EMPLOYER_ADDRESS_LINE_2)
            .employerCity(UPDATED_EMPLOYER_CITY)
            .employerState(UPDATED_EMPLOYER_STATE)
            .employerCountry(UPDATED_EMPLOYER_COUNTRY)
            .employerZip(UPDATED_EMPLOYER_ZIP)
            .employerContactNo1(UPDATED_EMPLOYER_CONTACT_NO_1)
            .employerContactNo2(UPDATED_EMPLOYER_CONTACT_NO_2)
            .employerFax(UPDATED_EMPLOYER_FAX)
            .employerEfax(UPDATED_EMPLOYER_EFAX)
            .employerEmail(UPDATED_EMPLOYER_EMAIL)
            .employeeRelationship(UPDATED_EMPLOYEE_RELATIONSHIP)
            .height(UPDATED_HEIGHT)
            .weight(UPDATED_WEIGHT)
            .functionalAbilities(UPDATED_FUNCTIONAL_ABILITIES)
            .infectionConditionStatus(UPDATED_INFECTION_CONDITION_STATUS)
            .diabetesStatus(UPDATED_DIABETES_STATUS)
            .diagnosisCodeType(UPDATED_DIAGNOSIS_CODE_TYPE)
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
            .doctorFirstName(UPDATED_DOCTOR_FIRST_NAME)
            .doctorMiddleName(UPDATED_DOCTOR_MIDDLE_NAME)
            .doctorLastName(UPDATED_DOCTOR_LAST_NAME)
            .doctorNameSuffix(UPDATED_DOCTOR_NAME_SUFFIX)
            .doctorAddressLine1(UPDATED_DOCTOR_ADDRESS_LINE_1)
            .doctorAddressLine2(UPDATED_DOCTOR_ADDRESS_LINE_2)
            .doctorAddressCity(UPDATED_DOCTOR_ADDRESS_CITY)
            .doctorAddressState(UPDATED_DOCTOR_ADDRESS_STATE)
            .doctorAddressZip(UPDATED_DOCTOR_ADDRESS_ZIP)
            .doctorContact1(UPDATED_DOCTOR_CONTACT_1)
            .doctorContact2(UPDATED_DOCTOR_CONTACT_2)
            .doctorFax(UPDATED_DOCTOR_FAX)
            .doctorNpiNumber(UPDATED_DOCTOR_NPI_NUMBER)
            .doctorGender(UPDATED_DOCTOR_GENDER)
            .doctorTaxonomyCode(UPDATED_DOCTOR_TAXONOMY_CODE)
            .doctorTaxonomyDescription(UPDATED_DOCTOR_TAXONOMY_DESCRIPTION)
            .doctorPracticeState(UPDATED_DOCTOR_PRACTICE_STATE)
            .doctorLicenseNumber(UPDATED_DOCTOR_LICENSE_NUMBER)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .patientDtoUuid(UPDATED_PATIENT_DTO_UUID)
            .secondaryInsurerAddressLine1(UPDATED_SECONDARY_INSURER_ADDRESS_LINE_1)
            .secondaryInsurerAddressLine2(UPDATED_SECONDARY_INSURER_ADDRESS_LINE_2)
            .secondaryInsurerCity(UPDATED_SECONDARY_INSURER_CITY)
            .secondaryInsurerState(UPDATED_SECONDARY_INSURER_STATE)
            .secondaryInsurerZip(UPDATED_SECONDARY_INSURER_ZIP)
            .secondaryInsurerContact1(UPDATED_SECONDARY_INSURER_CONTACT_1)
            .secondaryInsurerFax(UPDATED_SECONDARY_INSURER_FAX)
            .tertiaryInsurerAddressLine1(UPDATED_TERTIARY_INSURER_ADDRESS_LINE_1)
            .tertiaryInsurerAddressLine2(UPDATED_TERTIARY_INSURER_ADDRESS_LINE_2)
            .tertiaryInsurerCity(UPDATED_TERTIARY_INSURER_CITY)
            .tertiaryInsurerState(UPDATED_TERTIARY_INSURER_STATE)
            .tertiaryInsurerZip(UPDATED_TERTIARY_INSURER_ZIP)
            .tertiaryInsurerContact1(UPDATED_TERTIARY_INSURER_CONTACT_1)
            .tertiaryInsurerFax(UPDATED_TERTIARY_INSURER_FAX)
            .patientDod(UPDATED_PATIENT_DOD);
        return patientDto;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(PatientDto.class).block();
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
        patientDto = createEntity(em);
    }

    @Test
    void createPatientDto() throws Exception {
        int databaseSizeBeforeCreate = patientDtoRepository.findAll().collectList().block().size();
        // Create the PatientDto
        PatientDtoDTO patientDtoDTO = patientDtoMapper.toDto(patientDto);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDtoDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the PatientDto in the database
        List<PatientDto> patientDtoList = patientDtoRepository.findAll().collectList().block();
        assertThat(patientDtoList).hasSize(databaseSizeBeforeCreate + 1);
        PatientDto testPatientDto = patientDtoList.get(patientDtoList.size() - 1);
        assertThat(testPatientDto.getPatientId()).isEqualTo(DEFAULT_PATIENT_ID);
        assertThat(testPatientDto.getPatientFirstName()).isEqualTo(DEFAULT_PATIENT_FIRST_NAME);
        assertThat(testPatientDto.getPatientMiddleName()).isEqualTo(DEFAULT_PATIENT_MIDDLE_NAME);
        assertThat(testPatientDto.getPatientLastName()).isEqualTo(DEFAULT_PATIENT_LAST_NAME);
        assertThat(testPatientDto.getDob()).isEqualTo(DEFAULT_DOB);
        assertThat(testPatientDto.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testPatientDto.getSsn()).isEqualTo(DEFAULT_SSN);
        assertThat(testPatientDto.getTaxZoneId()).isEqualTo(DEFAULT_TAX_ZONE_ID);
        assertThat(testPatientDto.getTaxZoneName()).isEqualTo(DEFAULT_TAX_ZONE_NAME);
        assertThat(testPatientDto.getTaxRate()).isEqualTo(DEFAULT_TAX_RATE);
        assertThat(testPatientDto.getPatientIdNumber()).isEqualTo(DEFAULT_PATIENT_ID_NUMBER);
        assertThat(testPatientDto.getAddressLine1()).isEqualTo(DEFAULT_ADDRESS_LINE_1);
        assertThat(testPatientDto.getAddressLine2()).isEqualTo(DEFAULT_ADDRESS_LINE_2);
        assertThat(testPatientDto.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testPatientDto.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testPatientDto.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testPatientDto.getZip()).isEqualTo(DEFAULT_ZIP);
        assertThat(testPatientDto.getContactNo1()).isEqualTo(DEFAULT_CONTACT_NO_1);
        assertThat(testPatientDto.getContactNo2()).isEqualTo(DEFAULT_CONTACT_NO_2);
        assertThat(testPatientDto.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testPatientDto.getEfax()).isEqualTo(DEFAULT_EFAX);
        assertThat(testPatientDto.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testPatientDto.getBranchName()).isEqualTo(DEFAULT_BRANCH_NAME);
        assertThat(testPatientDto.getBillingAddressLine1()).isEqualTo(DEFAULT_BILLING_ADDRESS_LINE_1);
        assertThat(testPatientDto.getBillingAddressLine2()).isEqualTo(DEFAULT_BILLING_ADDRESS_LINE_2);
        assertThat(testPatientDto.getBillingAddressCity()).isEqualTo(DEFAULT_BILLING_ADDRESS_CITY);
        assertThat(testPatientDto.getBillingAddressState()).isEqualTo(DEFAULT_BILLING_ADDRESS_STATE);
        assertThat(testPatientDto.getBillingAddressZip()).isEqualTo(DEFAULT_BILLING_ADDRESS_ZIP);
        assertThat(testPatientDto.getCaregiverName()).isEqualTo(DEFAULT_CAREGIVER_NAME);
        assertThat(testPatientDto.getCaregiverContact()).isEqualTo(DEFAULT_CAREGIVER_CONTACT);
        assertThat(testPatientDto.getCaregiverRelatinshipPatient()).isEqualTo(DEFAULT_CAREGIVER_RELATINSHIP_PATIENT);
        assertThat(testPatientDto.getPrimaryInsuranceId()).isEqualTo(DEFAULT_PRIMARY_INSURANCE_ID);
        assertThat(testPatientDto.getPrimaryInsuranceName()).isEqualTo(DEFAULT_PRIMARY_INSURANCE_NAME);
        assertThat(testPatientDto.getPrimaryInsurancePayerIdNo()).isEqualTo(DEFAULT_PRIMARY_INSURANCE_PAYER_ID_NO);
        assertThat(testPatientDto.getPrimaryInsurancePayerContactNo()).isEqualTo(DEFAULT_PRIMARY_INSURANCE_PAYER_CONTACT_NO);
        assertThat(testPatientDto.getPrimaryInsurancePolicyNum()).isEqualTo(DEFAULT_PRIMARY_INSURANCE_POLICY_NUM);
        assertThat(testPatientDto.getPrimaryInsurancePolicyGroupNum()).isEqualTo(DEFAULT_PRIMARY_INSURANCE_POLICY_GROUP_NUM);
        assertThat(testPatientDto.getPrimaryInsurancePolicyGroupId()).isEqualTo(DEFAULT_PRIMARY_INSURANCE_POLICY_GROUP_ID);
        assertThat(testPatientDto.getPrimaryInsurancePolicyStartDate()).isEqualTo(DEFAULT_PRIMARY_INSURANCE_POLICY_START_DATE);
        assertThat(testPatientDto.getPrimaryInsurancePolicyEndDate()).isEqualTo(DEFAULT_PRIMARY_INSURANCE_POLICY_END_DATE);
        assertThat(testPatientDto.getPrimaryInsurancePayPercentage()).isEqualTo(DEFAULT_PRIMARY_INSURANCE_PAY_PERCENTAGE);
        assertThat(testPatientDto.getPrimaryInsuranceDeductableAmt()).isEqualTo(DEFAULT_PRIMARY_INSURANCE_DEDUCTABLE_AMT);
        assertThat(testPatientDto.getSecondaryInsuranceId()).isEqualTo(DEFAULT_SECONDARY_INSURANCE_ID);
        assertThat(testPatientDto.getSecondaryInsuranceName()).isEqualTo(DEFAULT_SECONDARY_INSURANCE_NAME);
        assertThat(testPatientDto.getSecondaryInsurancePayerIdNo()).isEqualTo(DEFAULT_SECONDARY_INSURANCE_PAYER_ID_NO);
        assertThat(testPatientDto.getSecondaryInsurancePayerContactNo()).isEqualTo(DEFAULT_SECONDARY_INSURANCE_PAYER_CONTACT_NO);
        assertThat(testPatientDto.getSecondaryInsurancePolicyNum()).isEqualTo(DEFAULT_SECONDARY_INSURANCE_POLICY_NUM);
        assertThat(testPatientDto.getSecondaryInsurancePolicyGroupNum()).isEqualTo(DEFAULT_SECONDARY_INSURANCE_POLICY_GROUP_NUM);
        assertThat(testPatientDto.getSecondaryInsurancePolicyGroupId()).isEqualTo(DEFAULT_SECONDARY_INSURANCE_POLICY_GROUP_ID);
        assertThat(testPatientDto.getSecondaryInsurancePolicyStartDate()).isEqualTo(DEFAULT_SECONDARY_INSURANCE_POLICY_START_DATE);
        assertThat(testPatientDto.getSecondaryInsurancePolicyEndDate()).isEqualTo(DEFAULT_SECONDARY_INSURANCE_POLICY_END_DATE);
        assertThat(testPatientDto.getSecondaryInsurancePayPercentage()).isEqualTo(DEFAULT_SECONDARY_INSURANCE_PAY_PERCENTAGE);
        assertThat(testPatientDto.getSecondaryInsuranceDeductableAmt()).isEqualTo(DEFAULT_SECONDARY_INSURANCE_DEDUCTABLE_AMT);
        assertThat(testPatientDto.getTertiaryInsuranceId()).isEqualTo(DEFAULT_TERTIARY_INSURANCE_ID);
        assertThat(testPatientDto.getTertiaryInsuranceName()).isEqualTo(DEFAULT_TERTIARY_INSURANCE_NAME);
        assertThat(testPatientDto.getTertiaryInsurancePayerIdNo()).isEqualTo(DEFAULT_TERTIARY_INSURANCE_PAYER_ID_NO);
        assertThat(testPatientDto.getTertiaryInsurancePayerContactNo()).isEqualTo(DEFAULT_TERTIARY_INSURANCE_PAYER_CONTACT_NO);
        assertThat(testPatientDto.getTertiaryInsurancePolicyNum()).isEqualTo(DEFAULT_TERTIARY_INSURANCE_POLICY_NUM);
        assertThat(testPatientDto.getTertiaryInsurancePolicyGroupNum()).isEqualTo(DEFAULT_TERTIARY_INSURANCE_POLICY_GROUP_NUM);
        assertThat(testPatientDto.getTertiaryInsurancePolicyGroupId()).isEqualTo(DEFAULT_TERTIARY_INSURANCE_POLICY_GROUP_ID);
        assertThat(testPatientDto.getTertiaryInsurancePolicyStartDate()).isEqualTo(DEFAULT_TERTIARY_INSURANCE_POLICY_START_DATE);
        assertThat(testPatientDto.getTertiaryInsurancePolicyEndDate()).isEqualTo(DEFAULT_TERTIARY_INSURANCE_POLICY_END_DATE);
        assertThat(testPatientDto.getTertiaryInsurancePayPercentage()).isEqualTo(DEFAULT_TERTIARY_INSURANCE_PAY_PERCENTAGE);
        assertThat(testPatientDto.getTertiaryInsuranceDeductableAmt()).isEqualTo(DEFAULT_TERTIARY_INSURANCE_DEDUCTABLE_AMT);
        assertThat(testPatientDto.getRelationship()).isEqualTo(DEFAULT_RELATIONSHIP);
        assertThat(testPatientDto.getInsuredFirstName()).isEqualTo(DEFAULT_INSURED_FIRST_NAME);
        assertThat(testPatientDto.getInsuredMiddleName()).isEqualTo(DEFAULT_INSURED_MIDDLE_NAME);
        assertThat(testPatientDto.getInsuredLastName()).isEqualTo(DEFAULT_INSURED_LAST_NAME);
        assertThat(testPatientDto.getInsuredSuffix()).isEqualTo(DEFAULT_INSURED_SUFFIX);
        assertThat(testPatientDto.getInsuredDob()).isEqualTo(DEFAULT_INSURED_DOB);
        assertThat(testPatientDto.getInsuredSsn()).isEqualTo(DEFAULT_INSURED_SSN);
        assertThat(testPatientDto.getInsuredGender()).isEqualTo(DEFAULT_INSURED_GENDER);
        assertThat(testPatientDto.getPrimaryInsurerAddressLine1()).isEqualTo(DEFAULT_PRIMARY_INSURER_ADDRESS_LINE_1);
        assertThat(testPatientDto.getPrimaryInsurerAddressLine2()).isEqualTo(DEFAULT_PRIMARY_INSURER_ADDRESS_LINE_2);
        assertThat(testPatientDto.getPrimaryInsurerCity()).isEqualTo(DEFAULT_PRIMARY_INSURER_CITY);
        assertThat(testPatientDto.getPrimaryInsurerState()).isEqualTo(DEFAULT_PRIMARY_INSURER_STATE);
        assertThat(testPatientDto.getPrimaryInsurerZip()).isEqualTo(DEFAULT_PRIMARY_INSURER_ZIP);
        assertThat(testPatientDto.getPrimaryInsurerContact1()).isEqualTo(DEFAULT_PRIMARY_INSURER_CONTACT_1);
        assertThat(testPatientDto.getPrimaryInsurerFax()).isEqualTo(DEFAULT_PRIMARY_INSURER_FAX);
        assertThat(testPatientDto.getAlwaysCrossoverStatus()).isEqualTo(DEFAULT_ALWAYS_CROSSOVER_STATUS);
        assertThat(testPatientDto.getPrimaryInsuranceMemberId()).isEqualTo(DEFAULT_PRIMARY_INSURANCE_MEMBER_ID);
        assertThat(testPatientDto.getSecondaryInsuranceMemberId()).isEqualTo(DEFAULT_SECONDARY_INSURANCE_MEMBER_ID);
        assertThat(testPatientDto.getTertiaryInsuranceMemberId()).isEqualTo(DEFAULT_TERTIARY_INSURANCE_MEMBER_ID);
        assertThat(testPatientDto.getPatientRelationshipInsured()).isEqualTo(DEFAULT_PATIENT_RELATIONSHIP_INSURED);
        assertThat(testPatientDto.getPatientConditionEmployment()).isEqualTo(DEFAULT_PATIENT_CONDITION_EMPLOYMENT);
        assertThat(testPatientDto.getPatientConditionAutoAccident()).isEqualTo(DEFAULT_PATIENT_CONDITION_AUTO_ACCIDENT);
        assertThat(testPatientDto.getPatientConditionOtherAccident()).isEqualTo(DEFAULT_PATIENT_CONDITION_OTHER_ACCIDENT);
        assertThat(testPatientDto.getInsuredEmployer()).isEqualTo(DEFAULT_INSURED_EMPLOYER);
        assertThat(testPatientDto.getWorkersCompensationPayerIdNumber()).isEqualTo(DEFAULT_WORKERS_COMPENSATION_PAYER_ID_NUMBER);
        assertThat(testPatientDto.getWorkersCompensationPlanName()).isEqualTo(DEFAULT_WORKERS_COMPENSATION_PLAN_NAME);
        assertThat(testPatientDto.getWorkersCompensationAdditionalDtls()).isEqualTo(DEFAULT_WORKERS_COMPENSATION_ADDITIONAL_DTLS);
        assertThat(testPatientDto.getWorkersCompensationClaimFillingCode()).isEqualTo(DEFAULT_WORKERS_COMPENSATION_CLAIM_FILLING_CODE);
        assertThat(testPatientDto.getWorkersCompensationTplCode()).isEqualTo(DEFAULT_WORKERS_COMPENSATION_TPL_CODE);
        assertThat(testPatientDto.getWorkersCompensationTplName()).isEqualTo(DEFAULT_WORKERS_COMPENSATION_TPL_NAME);
        assertThat(testPatientDto.getWcPropertyCasualtyAgencyClaimNo()).isEqualTo(DEFAULT_WC_PROPERTY_CASUALTY_AGENCY_CLAIM_NO);
        assertThat(testPatientDto.getWcCarrierId()).isEqualTo(DEFAULT_WC_CARRIER_ID);
        assertThat(testPatientDto.getEmployerAddressLine1()).isEqualTo(DEFAULT_EMPLOYER_ADDRESS_LINE_1);
        assertThat(testPatientDto.getEmployerAddressLine2()).isEqualTo(DEFAULT_EMPLOYER_ADDRESS_LINE_2);
        assertThat(testPatientDto.getEmployerCity()).isEqualTo(DEFAULT_EMPLOYER_CITY);
        assertThat(testPatientDto.getEmployerState()).isEqualTo(DEFAULT_EMPLOYER_STATE);
        assertThat(testPatientDto.getEmployerCountry()).isEqualTo(DEFAULT_EMPLOYER_COUNTRY);
        assertThat(testPatientDto.getEmployerZip()).isEqualTo(DEFAULT_EMPLOYER_ZIP);
        assertThat(testPatientDto.getEmployerContactNo1()).isEqualTo(DEFAULT_EMPLOYER_CONTACT_NO_1);
        assertThat(testPatientDto.getEmployerContactNo2()).isEqualTo(DEFAULT_EMPLOYER_CONTACT_NO_2);
        assertThat(testPatientDto.getEmployerFax()).isEqualTo(DEFAULT_EMPLOYER_FAX);
        assertThat(testPatientDto.getEmployerEfax()).isEqualTo(DEFAULT_EMPLOYER_EFAX);
        assertThat(testPatientDto.getEmployerEmail()).isEqualTo(DEFAULT_EMPLOYER_EMAIL);
        assertThat(testPatientDto.getEmployeeRelationship()).isEqualTo(DEFAULT_EMPLOYEE_RELATIONSHIP);
        assertThat(testPatientDto.getHeight()).isEqualTo(DEFAULT_HEIGHT);
        assertThat(testPatientDto.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testPatientDto.getFunctionalAbilities()).isEqualTo(DEFAULT_FUNCTIONAL_ABILITIES);
        assertThat(testPatientDto.getInfectionConditionStatus()).isEqualTo(DEFAULT_INFECTION_CONDITION_STATUS);
        assertThat(testPatientDto.getDiabetesStatus()).isEqualTo(DEFAULT_DIABETES_STATUS);
        assertThat(testPatientDto.getDiagnosisCodeType()).isEqualTo(DEFAULT_DIAGNOSIS_CODE_TYPE);
        assertThat(testPatientDto.getIcd10DiagnosisCode1()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_1);
        assertThat(testPatientDto.getIcd10DiagnosisCode2()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_2);
        assertThat(testPatientDto.getIcd10DiagnosisCode3()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_3);
        assertThat(testPatientDto.getIcd10DiagnosisCode4()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_4);
        assertThat(testPatientDto.getIcd10DiagnosisCode5()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_5);
        assertThat(testPatientDto.getIcd10DiagnosisCode6()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_6);
        assertThat(testPatientDto.getIcd10DiagnosisCode7()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_7);
        assertThat(testPatientDto.getIcd10DiagnosisCode8()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_8);
        assertThat(testPatientDto.getIcd10DiagnosisCode9()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_9);
        assertThat(testPatientDto.getIcd10DiagnosisCode10()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_10);
        assertThat(testPatientDto.getIcd10DiagnosisCode11()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_11);
        assertThat(testPatientDto.getIcd10DiagnosisCode12()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_12);
        assertThat(testPatientDto.getDoctorFirstName()).isEqualTo(DEFAULT_DOCTOR_FIRST_NAME);
        assertThat(testPatientDto.getDoctorMiddleName()).isEqualTo(DEFAULT_DOCTOR_MIDDLE_NAME);
        assertThat(testPatientDto.getDoctorLastName()).isEqualTo(DEFAULT_DOCTOR_LAST_NAME);
        assertThat(testPatientDto.getDoctorNameSuffix()).isEqualTo(DEFAULT_DOCTOR_NAME_SUFFIX);
        assertThat(testPatientDto.getDoctorAddressLine1()).isEqualTo(DEFAULT_DOCTOR_ADDRESS_LINE_1);
        assertThat(testPatientDto.getDoctorAddressLine2()).isEqualTo(DEFAULT_DOCTOR_ADDRESS_LINE_2);
        assertThat(testPatientDto.getDoctorAddressCity()).isEqualTo(DEFAULT_DOCTOR_ADDRESS_CITY);
        assertThat(testPatientDto.getDoctorAddressState()).isEqualTo(DEFAULT_DOCTOR_ADDRESS_STATE);
        assertThat(testPatientDto.getDoctorAddressZip()).isEqualTo(DEFAULT_DOCTOR_ADDRESS_ZIP);
        assertThat(testPatientDto.getDoctorContact1()).isEqualTo(DEFAULT_DOCTOR_CONTACT_1);
        assertThat(testPatientDto.getDoctorContact2()).isEqualTo(DEFAULT_DOCTOR_CONTACT_2);
        assertThat(testPatientDto.getDoctorFax()).isEqualTo(DEFAULT_DOCTOR_FAX);
        assertThat(testPatientDto.getDoctorNpiNumber()).isEqualTo(DEFAULT_DOCTOR_NPI_NUMBER);
        assertThat(testPatientDto.getDoctorGender()).isEqualTo(DEFAULT_DOCTOR_GENDER);
        assertThat(testPatientDto.getDoctorTaxonomyCode()).isEqualTo(DEFAULT_DOCTOR_TAXONOMY_CODE);
        assertThat(testPatientDto.getDoctorTaxonomyDescription()).isEqualTo(DEFAULT_DOCTOR_TAXONOMY_DESCRIPTION);
        assertThat(testPatientDto.getDoctorPracticeState()).isEqualTo(DEFAULT_DOCTOR_PRACTICE_STATE);
        assertThat(testPatientDto.getDoctorLicenseNumber()).isEqualTo(DEFAULT_DOCTOR_LICENSE_NUMBER);
        assertThat(testPatientDto.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPatientDto.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testPatientDto.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testPatientDto.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testPatientDto.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testPatientDto.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testPatientDto.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testPatientDto.getPatientDtoUuid()).isEqualTo(DEFAULT_PATIENT_DTO_UUID);
        assertThat(testPatientDto.getSecondaryInsurerAddressLine1()).isEqualTo(DEFAULT_SECONDARY_INSURER_ADDRESS_LINE_1);
        assertThat(testPatientDto.getSecondaryInsurerAddressLine2()).isEqualTo(DEFAULT_SECONDARY_INSURER_ADDRESS_LINE_2);
        assertThat(testPatientDto.getSecondaryInsurerCity()).isEqualTo(DEFAULT_SECONDARY_INSURER_CITY);
        assertThat(testPatientDto.getSecondaryInsurerState()).isEqualTo(DEFAULT_SECONDARY_INSURER_STATE);
        assertThat(testPatientDto.getSecondaryInsurerZip()).isEqualTo(DEFAULT_SECONDARY_INSURER_ZIP);
        assertThat(testPatientDto.getSecondaryInsurerContact1()).isEqualTo(DEFAULT_SECONDARY_INSURER_CONTACT_1);
        assertThat(testPatientDto.getSecondaryInsurerFax()).isEqualTo(DEFAULT_SECONDARY_INSURER_FAX);
        assertThat(testPatientDto.getTertiaryInsurerAddressLine1()).isEqualTo(DEFAULT_TERTIARY_INSURER_ADDRESS_LINE_1);
        assertThat(testPatientDto.getTertiaryInsurerAddressLine2()).isEqualTo(DEFAULT_TERTIARY_INSURER_ADDRESS_LINE_2);
        assertThat(testPatientDto.getTertiaryInsurerCity()).isEqualTo(DEFAULT_TERTIARY_INSURER_CITY);
        assertThat(testPatientDto.getTertiaryInsurerState()).isEqualTo(DEFAULT_TERTIARY_INSURER_STATE);
        assertThat(testPatientDto.getTertiaryInsurerZip()).isEqualTo(DEFAULT_TERTIARY_INSURER_ZIP);
        assertThat(testPatientDto.getTertiaryInsurerContact1()).isEqualTo(DEFAULT_TERTIARY_INSURER_CONTACT_1);
        assertThat(testPatientDto.getTertiaryInsurerFax()).isEqualTo(DEFAULT_TERTIARY_INSURER_FAX);
        assertThat(testPatientDto.getPatientDod()).isEqualTo(DEFAULT_PATIENT_DOD);
    }

    @Test
    void createPatientDtoWithExistingId() throws Exception {
        // Create the PatientDto with an existing ID
        patientDto.setPatientDtoId(1L);
        PatientDtoDTO patientDtoDTO = patientDtoMapper.toDto(patientDto);

        int databaseSizeBeforeCreate = patientDtoRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDtoDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDto in the database
        List<PatientDto> patientDtoList = patientDtoRepository.findAll().collectList().block();
        assertThat(patientDtoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllPatientDtos() {
        // Initialize the database
        patientDtoRepository.save(patientDto).block();

        // Get all the patientDtoList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=patientDtoId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].patientDtoId")
            .value(hasItem(patientDto.getPatientDtoId().intValue()))
            .jsonPath("$.[*].patientId")
            .value(hasItem(DEFAULT_PATIENT_ID.intValue()))
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
            .jsonPath("$.[*].taxZoneName")
            .value(hasItem(DEFAULT_TAX_ZONE_NAME))
            .jsonPath("$.[*].taxRate")
            .value(hasItem(DEFAULT_TAX_RATE.doubleValue()))
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
            .jsonPath("$.[*].primaryInsuranceId")
            .value(hasItem(DEFAULT_PRIMARY_INSURANCE_ID.intValue()))
            .jsonPath("$.[*].primaryInsuranceName")
            .value(hasItem(DEFAULT_PRIMARY_INSURANCE_NAME))
            .jsonPath("$.[*].primaryInsurancePayerIdNo")
            .value(hasItem(DEFAULT_PRIMARY_INSURANCE_PAYER_ID_NO))
            .jsonPath("$.[*].primaryInsurancePayerContactNo")
            .value(hasItem(DEFAULT_PRIMARY_INSURANCE_PAYER_CONTACT_NO))
            .jsonPath("$.[*].primaryInsurancePolicyNum")
            .value(hasItem(DEFAULT_PRIMARY_INSURANCE_POLICY_NUM))
            .jsonPath("$.[*].primaryInsurancePolicyGroupNum")
            .value(hasItem(DEFAULT_PRIMARY_INSURANCE_POLICY_GROUP_NUM))
            .jsonPath("$.[*].primaryInsurancePolicyGroupId")
            .value(hasItem(DEFAULT_PRIMARY_INSURANCE_POLICY_GROUP_ID.intValue()))
            .jsonPath("$.[*].primaryInsurancePolicyStartDate")
            .value(hasItem(DEFAULT_PRIMARY_INSURANCE_POLICY_START_DATE.toString()))
            .jsonPath("$.[*].primaryInsurancePolicyEndDate")
            .value(hasItem(DEFAULT_PRIMARY_INSURANCE_POLICY_END_DATE.toString()))
            .jsonPath("$.[*].primaryInsurancePayPercentage")
            .value(hasItem(DEFAULT_PRIMARY_INSURANCE_PAY_PERCENTAGE.doubleValue()))
            .jsonPath("$.[*].primaryInsuranceDeductableAmt")
            .value(hasItem(DEFAULT_PRIMARY_INSURANCE_DEDUCTABLE_AMT.doubleValue()))
            .jsonPath("$.[*].secondaryInsuranceId")
            .value(hasItem(DEFAULT_SECONDARY_INSURANCE_ID.intValue()))
            .jsonPath("$.[*].secondaryInsuranceName")
            .value(hasItem(DEFAULT_SECONDARY_INSURANCE_NAME))
            .jsonPath("$.[*].secondaryInsurancePayerIdNo")
            .value(hasItem(DEFAULT_SECONDARY_INSURANCE_PAYER_ID_NO))
            .jsonPath("$.[*].secondaryInsurancePayerContactNo")
            .value(hasItem(DEFAULT_SECONDARY_INSURANCE_PAYER_CONTACT_NO))
            .jsonPath("$.[*].secondaryInsurancePolicyNum")
            .value(hasItem(DEFAULT_SECONDARY_INSURANCE_POLICY_NUM))
            .jsonPath("$.[*].secondaryInsurancePolicyGroupNum")
            .value(hasItem(DEFAULT_SECONDARY_INSURANCE_POLICY_GROUP_NUM))
            .jsonPath("$.[*].secondaryInsurancePolicyGroupId")
            .value(hasItem(DEFAULT_SECONDARY_INSURANCE_POLICY_GROUP_ID.intValue()))
            .jsonPath("$.[*].secondaryInsurancePolicyStartDate")
            .value(hasItem(DEFAULT_SECONDARY_INSURANCE_POLICY_START_DATE.toString()))
            .jsonPath("$.[*].secondaryInsurancePolicyEndDate")
            .value(hasItem(DEFAULT_SECONDARY_INSURANCE_POLICY_END_DATE.toString()))
            .jsonPath("$.[*].secondaryInsurancePayPercentage")
            .value(hasItem(DEFAULT_SECONDARY_INSURANCE_PAY_PERCENTAGE.doubleValue()))
            .jsonPath("$.[*].secondaryInsuranceDeductableAmt")
            .value(hasItem(DEFAULT_SECONDARY_INSURANCE_DEDUCTABLE_AMT.doubleValue()))
            .jsonPath("$.[*].tertiaryInsuranceId")
            .value(hasItem(DEFAULT_TERTIARY_INSURANCE_ID.intValue()))
            .jsonPath("$.[*].tertiaryInsuranceName")
            .value(hasItem(DEFAULT_TERTIARY_INSURANCE_NAME))
            .jsonPath("$.[*].tertiaryInsurancePayerIdNo")
            .value(hasItem(DEFAULT_TERTIARY_INSURANCE_PAYER_ID_NO))
            .jsonPath("$.[*].tertiaryInsurancePayerContactNo")
            .value(hasItem(DEFAULT_TERTIARY_INSURANCE_PAYER_CONTACT_NO))
            .jsonPath("$.[*].tertiaryInsurancePolicyNum")
            .value(hasItem(DEFAULT_TERTIARY_INSURANCE_POLICY_NUM))
            .jsonPath("$.[*].tertiaryInsurancePolicyGroupNum")
            .value(hasItem(DEFAULT_TERTIARY_INSURANCE_POLICY_GROUP_NUM))
            .jsonPath("$.[*].tertiaryInsurancePolicyGroupId")
            .value(hasItem(DEFAULT_TERTIARY_INSURANCE_POLICY_GROUP_ID.intValue()))
            .jsonPath("$.[*].tertiaryInsurancePolicyStartDate")
            .value(hasItem(DEFAULT_TERTIARY_INSURANCE_POLICY_START_DATE.toString()))
            .jsonPath("$.[*].tertiaryInsurancePolicyEndDate")
            .value(hasItem(DEFAULT_TERTIARY_INSURANCE_POLICY_END_DATE.toString()))
            .jsonPath("$.[*].tertiaryInsurancePayPercentage")
            .value(hasItem(DEFAULT_TERTIARY_INSURANCE_PAY_PERCENTAGE.doubleValue()))
            .jsonPath("$.[*].tertiaryInsuranceDeductableAmt")
            .value(hasItem(DEFAULT_TERTIARY_INSURANCE_DEDUCTABLE_AMT.doubleValue()))
            .jsonPath("$.[*].relationship")
            .value(hasItem(DEFAULT_RELATIONSHIP))
            .jsonPath("$.[*].insuredFirstName")
            .value(hasItem(DEFAULT_INSURED_FIRST_NAME))
            .jsonPath("$.[*].insuredMiddleName")
            .value(hasItem(DEFAULT_INSURED_MIDDLE_NAME))
            .jsonPath("$.[*].insuredLastName")
            .value(hasItem(DEFAULT_INSURED_LAST_NAME))
            .jsonPath("$.[*].insuredSuffix")
            .value(hasItem(DEFAULT_INSURED_SUFFIX))
            .jsonPath("$.[*].insuredDob")
            .value(hasItem(DEFAULT_INSURED_DOB.toString()))
            .jsonPath("$.[*].insuredSsn")
            .value(hasItem(DEFAULT_INSURED_SSN))
            .jsonPath("$.[*].insuredGender")
            .value(hasItem(DEFAULT_INSURED_GENDER))
            .jsonPath("$.[*].primaryInsurerAddressLine1")
            .value(hasItem(DEFAULT_PRIMARY_INSURER_ADDRESS_LINE_1))
            .jsonPath("$.[*].primaryInsurerAddressLine2")
            .value(hasItem(DEFAULT_PRIMARY_INSURER_ADDRESS_LINE_2))
            .jsonPath("$.[*].primaryInsurerCity")
            .value(hasItem(DEFAULT_PRIMARY_INSURER_CITY))
            .jsonPath("$.[*].primaryInsurerState")
            .value(hasItem(DEFAULT_PRIMARY_INSURER_STATE))
            .jsonPath("$.[*].primaryInsurerZip")
            .value(hasItem(DEFAULT_PRIMARY_INSURER_ZIP))
            .jsonPath("$.[*].primaryInsurerContact1")
            .value(hasItem(DEFAULT_PRIMARY_INSURER_CONTACT_1))
            .jsonPath("$.[*].primaryInsurerFax")
            .value(hasItem(DEFAULT_PRIMARY_INSURER_FAX))
            .jsonPath("$.[*].alwaysCrossoverStatus")
            .value(hasItem(DEFAULT_ALWAYS_CROSSOVER_STATUS))
            .jsonPath("$.[*].primaryInsuranceMemberId")
            .value(hasItem(DEFAULT_PRIMARY_INSURANCE_MEMBER_ID))
            .jsonPath("$.[*].secondaryInsuranceMemberId")
            .value(hasItem(DEFAULT_SECONDARY_INSURANCE_MEMBER_ID))
            .jsonPath("$.[*].tertiaryInsuranceMemberId")
            .value(hasItem(DEFAULT_TERTIARY_INSURANCE_MEMBER_ID))
            .jsonPath("$.[*].patientRelationshipInsured")
            .value(hasItem(DEFAULT_PATIENT_RELATIONSHIP_INSURED))
            .jsonPath("$.[*].patientConditionEmployment")
            .value(hasItem(DEFAULT_PATIENT_CONDITION_EMPLOYMENT))
            .jsonPath("$.[*].patientConditionAutoAccident")
            .value(hasItem(DEFAULT_PATIENT_CONDITION_AUTO_ACCIDENT))
            .jsonPath("$.[*].patientConditionOtherAccident")
            .value(hasItem(DEFAULT_PATIENT_CONDITION_OTHER_ACCIDENT))
            .jsonPath("$.[*].insuredEmployer")
            .value(hasItem(DEFAULT_INSURED_EMPLOYER))
            .jsonPath("$.[*].workersCompensationPayerIdNumber")
            .value(hasItem(DEFAULT_WORKERS_COMPENSATION_PAYER_ID_NUMBER))
            .jsonPath("$.[*].workersCompensationPlanName")
            .value(hasItem(DEFAULT_WORKERS_COMPENSATION_PLAN_NAME))
            .jsonPath("$.[*].workersCompensationAdditionalDtls")
            .value(hasItem(DEFAULT_WORKERS_COMPENSATION_ADDITIONAL_DTLS))
            .jsonPath("$.[*].workersCompensationClaimFillingCode")
            .value(hasItem(DEFAULT_WORKERS_COMPENSATION_CLAIM_FILLING_CODE))
            .jsonPath("$.[*].workersCompensationTplCode")
            .value(hasItem(DEFAULT_WORKERS_COMPENSATION_TPL_CODE))
            .jsonPath("$.[*].workersCompensationTplName")
            .value(hasItem(DEFAULT_WORKERS_COMPENSATION_TPL_NAME))
            .jsonPath("$.[*].wcPropertyCasualtyAgencyClaimNo")
            .value(hasItem(DEFAULT_WC_PROPERTY_CASUALTY_AGENCY_CLAIM_NO))
            .jsonPath("$.[*].wcCarrierId")
            .value(hasItem(DEFAULT_WC_CARRIER_ID))
            .jsonPath("$.[*].employerAddressLine1")
            .value(hasItem(DEFAULT_EMPLOYER_ADDRESS_LINE_1))
            .jsonPath("$.[*].employerAddressLine2")
            .value(hasItem(DEFAULT_EMPLOYER_ADDRESS_LINE_2))
            .jsonPath("$.[*].employerCity")
            .value(hasItem(DEFAULT_EMPLOYER_CITY))
            .jsonPath("$.[*].employerState")
            .value(hasItem(DEFAULT_EMPLOYER_STATE))
            .jsonPath("$.[*].employerCountry")
            .value(hasItem(DEFAULT_EMPLOYER_COUNTRY))
            .jsonPath("$.[*].employerZip")
            .value(hasItem(DEFAULT_EMPLOYER_ZIP))
            .jsonPath("$.[*].employerContactNo1")
            .value(hasItem(DEFAULT_EMPLOYER_CONTACT_NO_1))
            .jsonPath("$.[*].employerContactNo2")
            .value(hasItem(DEFAULT_EMPLOYER_CONTACT_NO_2))
            .jsonPath("$.[*].employerFax")
            .value(hasItem(DEFAULT_EMPLOYER_FAX))
            .jsonPath("$.[*].employerEfax")
            .value(hasItem(DEFAULT_EMPLOYER_EFAX))
            .jsonPath("$.[*].employerEmail")
            .value(hasItem(DEFAULT_EMPLOYER_EMAIL))
            .jsonPath("$.[*].employeeRelationship")
            .value(hasItem(DEFAULT_EMPLOYEE_RELATIONSHIP))
            .jsonPath("$.[*].height")
            .value(hasItem(DEFAULT_HEIGHT.doubleValue()))
            .jsonPath("$.[*].weight")
            .value(hasItem(DEFAULT_WEIGHT.doubleValue()))
            .jsonPath("$.[*].functionalAbilities")
            .value(hasItem(DEFAULT_FUNCTIONAL_ABILITIES))
            .jsonPath("$.[*].infectionConditionStatus")
            .value(hasItem(DEFAULT_INFECTION_CONDITION_STATUS))
            .jsonPath("$.[*].diabetesStatus")
            .value(hasItem(DEFAULT_DIABETES_STATUS))
            .jsonPath("$.[*].diagnosisCodeType")
            .value(hasItem(DEFAULT_DIAGNOSIS_CODE_TYPE))
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
            .jsonPath("$.[*].doctorFirstName")
            .value(hasItem(DEFAULT_DOCTOR_FIRST_NAME))
            .jsonPath("$.[*].doctorMiddleName")
            .value(hasItem(DEFAULT_DOCTOR_MIDDLE_NAME))
            .jsonPath("$.[*].doctorLastName")
            .value(hasItem(DEFAULT_DOCTOR_LAST_NAME))
            .jsonPath("$.[*].doctorNameSuffix")
            .value(hasItem(DEFAULT_DOCTOR_NAME_SUFFIX))
            .jsonPath("$.[*].doctorAddressLine1")
            .value(hasItem(DEFAULT_DOCTOR_ADDRESS_LINE_1))
            .jsonPath("$.[*].doctorAddressLine2")
            .value(hasItem(DEFAULT_DOCTOR_ADDRESS_LINE_2))
            .jsonPath("$.[*].doctorAddressCity")
            .value(hasItem(DEFAULT_DOCTOR_ADDRESS_CITY))
            .jsonPath("$.[*].doctorAddressState")
            .value(hasItem(DEFAULT_DOCTOR_ADDRESS_STATE))
            .jsonPath("$.[*].doctorAddressZip")
            .value(hasItem(DEFAULT_DOCTOR_ADDRESS_ZIP))
            .jsonPath("$.[*].doctorContact1")
            .value(hasItem(DEFAULT_DOCTOR_CONTACT_1))
            .jsonPath("$.[*].doctorContact2")
            .value(hasItem(DEFAULT_DOCTOR_CONTACT_2))
            .jsonPath("$.[*].doctorFax")
            .value(hasItem(DEFAULT_DOCTOR_FAX))
            .jsonPath("$.[*].doctorNpiNumber")
            .value(hasItem(DEFAULT_DOCTOR_NPI_NUMBER))
            .jsonPath("$.[*].doctorGender")
            .value(hasItem(DEFAULT_DOCTOR_GENDER))
            .jsonPath("$.[*].doctorTaxonomyCode")
            .value(hasItem(DEFAULT_DOCTOR_TAXONOMY_CODE))
            .jsonPath("$.[*].doctorTaxonomyDescription")
            .value(hasItem(DEFAULT_DOCTOR_TAXONOMY_DESCRIPTION))
            .jsonPath("$.[*].doctorPracticeState")
            .value(hasItem(DEFAULT_DOCTOR_PRACTICE_STATE))
            .jsonPath("$.[*].doctorLicenseNumber")
            .value(hasItem(DEFAULT_DOCTOR_LICENSE_NUMBER))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS))
            .jsonPath("$.[*].createdDate")
            .value(hasItem(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.[*].createdById")
            .value(hasItem(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.[*].createdByName")
            .value(hasItem(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.[*].updatedDate")
            .value(hasItem(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.[*].updatedById")
            .value(hasItem(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.[*].updatedByName")
            .value(hasItem(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.[*].patientDtoUuid")
            .value(hasItem(DEFAULT_PATIENT_DTO_UUID.toString()))
            .jsonPath("$.[*].secondaryInsurerAddressLine1")
            .value(hasItem(DEFAULT_SECONDARY_INSURER_ADDRESS_LINE_1))
            .jsonPath("$.[*].secondaryInsurerAddressLine2")
            .value(hasItem(DEFAULT_SECONDARY_INSURER_ADDRESS_LINE_2))
            .jsonPath("$.[*].secondaryInsurerCity")
            .value(hasItem(DEFAULT_SECONDARY_INSURER_CITY))
            .jsonPath("$.[*].secondaryInsurerState")
            .value(hasItem(DEFAULT_SECONDARY_INSURER_STATE))
            .jsonPath("$.[*].secondaryInsurerZip")
            .value(hasItem(DEFAULT_SECONDARY_INSURER_ZIP))
            .jsonPath("$.[*].secondaryInsurerContact1")
            .value(hasItem(DEFAULT_SECONDARY_INSURER_CONTACT_1))
            .jsonPath("$.[*].secondaryInsurerFax")
            .value(hasItem(DEFAULT_SECONDARY_INSURER_FAX))
            .jsonPath("$.[*].tertiaryInsurerAddressLine1")
            .value(hasItem(DEFAULT_TERTIARY_INSURER_ADDRESS_LINE_1))
            .jsonPath("$.[*].tertiaryInsurerAddressLine2")
            .value(hasItem(DEFAULT_TERTIARY_INSURER_ADDRESS_LINE_2))
            .jsonPath("$.[*].tertiaryInsurerCity")
            .value(hasItem(DEFAULT_TERTIARY_INSURER_CITY))
            .jsonPath("$.[*].tertiaryInsurerState")
            .value(hasItem(DEFAULT_TERTIARY_INSURER_STATE))
            .jsonPath("$.[*].tertiaryInsurerZip")
            .value(hasItem(DEFAULT_TERTIARY_INSURER_ZIP))
            .jsonPath("$.[*].tertiaryInsurerContact1")
            .value(hasItem(DEFAULT_TERTIARY_INSURER_CONTACT_1))
            .jsonPath("$.[*].tertiaryInsurerFax")
            .value(hasItem(DEFAULT_TERTIARY_INSURER_FAX))
            .jsonPath("$.[*].patientDod")
            .value(hasItem(DEFAULT_PATIENT_DOD.toString()));
    }

    @Test
    void getPatientDto() {
        // Initialize the database
        patientDtoRepository.save(patientDto).block();

        // Get the patientDto
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, patientDto.getPatientDtoId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.patientDtoId")
            .value(is(patientDto.getPatientDtoId().intValue()))
            .jsonPath("$.patientId")
            .value(is(DEFAULT_PATIENT_ID.intValue()))
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
            .jsonPath("$.taxZoneName")
            .value(is(DEFAULT_TAX_ZONE_NAME))
            .jsonPath("$.taxRate")
            .value(is(DEFAULT_TAX_RATE.doubleValue()))
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
            .jsonPath("$.primaryInsuranceId")
            .value(is(DEFAULT_PRIMARY_INSURANCE_ID.intValue()))
            .jsonPath("$.primaryInsuranceName")
            .value(is(DEFAULT_PRIMARY_INSURANCE_NAME))
            .jsonPath("$.primaryInsurancePayerIdNo")
            .value(is(DEFAULT_PRIMARY_INSURANCE_PAYER_ID_NO))
            .jsonPath("$.primaryInsurancePayerContactNo")
            .value(is(DEFAULT_PRIMARY_INSURANCE_PAYER_CONTACT_NO))
            .jsonPath("$.primaryInsurancePolicyNum")
            .value(is(DEFAULT_PRIMARY_INSURANCE_POLICY_NUM))
            .jsonPath("$.primaryInsurancePolicyGroupNum")
            .value(is(DEFAULT_PRIMARY_INSURANCE_POLICY_GROUP_NUM))
            .jsonPath("$.primaryInsurancePolicyGroupId")
            .value(is(DEFAULT_PRIMARY_INSURANCE_POLICY_GROUP_ID.intValue()))
            .jsonPath("$.primaryInsurancePolicyStartDate")
            .value(is(DEFAULT_PRIMARY_INSURANCE_POLICY_START_DATE.toString()))
            .jsonPath("$.primaryInsurancePolicyEndDate")
            .value(is(DEFAULT_PRIMARY_INSURANCE_POLICY_END_DATE.toString()))
            .jsonPath("$.primaryInsurancePayPercentage")
            .value(is(DEFAULT_PRIMARY_INSURANCE_PAY_PERCENTAGE.doubleValue()))
            .jsonPath("$.primaryInsuranceDeductableAmt")
            .value(is(DEFAULT_PRIMARY_INSURANCE_DEDUCTABLE_AMT.doubleValue()))
            .jsonPath("$.secondaryInsuranceId")
            .value(is(DEFAULT_SECONDARY_INSURANCE_ID.intValue()))
            .jsonPath("$.secondaryInsuranceName")
            .value(is(DEFAULT_SECONDARY_INSURANCE_NAME))
            .jsonPath("$.secondaryInsurancePayerIdNo")
            .value(is(DEFAULT_SECONDARY_INSURANCE_PAYER_ID_NO))
            .jsonPath("$.secondaryInsurancePayerContactNo")
            .value(is(DEFAULT_SECONDARY_INSURANCE_PAYER_CONTACT_NO))
            .jsonPath("$.secondaryInsurancePolicyNum")
            .value(is(DEFAULT_SECONDARY_INSURANCE_POLICY_NUM))
            .jsonPath("$.secondaryInsurancePolicyGroupNum")
            .value(is(DEFAULT_SECONDARY_INSURANCE_POLICY_GROUP_NUM))
            .jsonPath("$.secondaryInsurancePolicyGroupId")
            .value(is(DEFAULT_SECONDARY_INSURANCE_POLICY_GROUP_ID.intValue()))
            .jsonPath("$.secondaryInsurancePolicyStartDate")
            .value(is(DEFAULT_SECONDARY_INSURANCE_POLICY_START_DATE.toString()))
            .jsonPath("$.secondaryInsurancePolicyEndDate")
            .value(is(DEFAULT_SECONDARY_INSURANCE_POLICY_END_DATE.toString()))
            .jsonPath("$.secondaryInsurancePayPercentage")
            .value(is(DEFAULT_SECONDARY_INSURANCE_PAY_PERCENTAGE.doubleValue()))
            .jsonPath("$.secondaryInsuranceDeductableAmt")
            .value(is(DEFAULT_SECONDARY_INSURANCE_DEDUCTABLE_AMT.doubleValue()))
            .jsonPath("$.tertiaryInsuranceId")
            .value(is(DEFAULT_TERTIARY_INSURANCE_ID.intValue()))
            .jsonPath("$.tertiaryInsuranceName")
            .value(is(DEFAULT_TERTIARY_INSURANCE_NAME))
            .jsonPath("$.tertiaryInsurancePayerIdNo")
            .value(is(DEFAULT_TERTIARY_INSURANCE_PAYER_ID_NO))
            .jsonPath("$.tertiaryInsurancePayerContactNo")
            .value(is(DEFAULT_TERTIARY_INSURANCE_PAYER_CONTACT_NO))
            .jsonPath("$.tertiaryInsurancePolicyNum")
            .value(is(DEFAULT_TERTIARY_INSURANCE_POLICY_NUM))
            .jsonPath("$.tertiaryInsurancePolicyGroupNum")
            .value(is(DEFAULT_TERTIARY_INSURANCE_POLICY_GROUP_NUM))
            .jsonPath("$.tertiaryInsurancePolicyGroupId")
            .value(is(DEFAULT_TERTIARY_INSURANCE_POLICY_GROUP_ID.intValue()))
            .jsonPath("$.tertiaryInsurancePolicyStartDate")
            .value(is(DEFAULT_TERTIARY_INSURANCE_POLICY_START_DATE.toString()))
            .jsonPath("$.tertiaryInsurancePolicyEndDate")
            .value(is(DEFAULT_TERTIARY_INSURANCE_POLICY_END_DATE.toString()))
            .jsonPath("$.tertiaryInsurancePayPercentage")
            .value(is(DEFAULT_TERTIARY_INSURANCE_PAY_PERCENTAGE.doubleValue()))
            .jsonPath("$.tertiaryInsuranceDeductableAmt")
            .value(is(DEFAULT_TERTIARY_INSURANCE_DEDUCTABLE_AMT.doubleValue()))
            .jsonPath("$.relationship")
            .value(is(DEFAULT_RELATIONSHIP))
            .jsonPath("$.insuredFirstName")
            .value(is(DEFAULT_INSURED_FIRST_NAME))
            .jsonPath("$.insuredMiddleName")
            .value(is(DEFAULT_INSURED_MIDDLE_NAME))
            .jsonPath("$.insuredLastName")
            .value(is(DEFAULT_INSURED_LAST_NAME))
            .jsonPath("$.insuredSuffix")
            .value(is(DEFAULT_INSURED_SUFFIX))
            .jsonPath("$.insuredDob")
            .value(is(DEFAULT_INSURED_DOB.toString()))
            .jsonPath("$.insuredSsn")
            .value(is(DEFAULT_INSURED_SSN))
            .jsonPath("$.insuredGender")
            .value(is(DEFAULT_INSURED_GENDER))
            .jsonPath("$.primaryInsurerAddressLine1")
            .value(is(DEFAULT_PRIMARY_INSURER_ADDRESS_LINE_1))
            .jsonPath("$.primaryInsurerAddressLine2")
            .value(is(DEFAULT_PRIMARY_INSURER_ADDRESS_LINE_2))
            .jsonPath("$.primaryInsurerCity")
            .value(is(DEFAULT_PRIMARY_INSURER_CITY))
            .jsonPath("$.primaryInsurerState")
            .value(is(DEFAULT_PRIMARY_INSURER_STATE))
            .jsonPath("$.primaryInsurerZip")
            .value(is(DEFAULT_PRIMARY_INSURER_ZIP))
            .jsonPath("$.primaryInsurerContact1")
            .value(is(DEFAULT_PRIMARY_INSURER_CONTACT_1))
            .jsonPath("$.primaryInsurerFax")
            .value(is(DEFAULT_PRIMARY_INSURER_FAX))
            .jsonPath("$.alwaysCrossoverStatus")
            .value(is(DEFAULT_ALWAYS_CROSSOVER_STATUS))
            .jsonPath("$.primaryInsuranceMemberId")
            .value(is(DEFAULT_PRIMARY_INSURANCE_MEMBER_ID))
            .jsonPath("$.secondaryInsuranceMemberId")
            .value(is(DEFAULT_SECONDARY_INSURANCE_MEMBER_ID))
            .jsonPath("$.tertiaryInsuranceMemberId")
            .value(is(DEFAULT_TERTIARY_INSURANCE_MEMBER_ID))
            .jsonPath("$.patientRelationshipInsured")
            .value(is(DEFAULT_PATIENT_RELATIONSHIP_INSURED))
            .jsonPath("$.patientConditionEmployment")
            .value(is(DEFAULT_PATIENT_CONDITION_EMPLOYMENT))
            .jsonPath("$.patientConditionAutoAccident")
            .value(is(DEFAULT_PATIENT_CONDITION_AUTO_ACCIDENT))
            .jsonPath("$.patientConditionOtherAccident")
            .value(is(DEFAULT_PATIENT_CONDITION_OTHER_ACCIDENT))
            .jsonPath("$.insuredEmployer")
            .value(is(DEFAULT_INSURED_EMPLOYER))
            .jsonPath("$.workersCompensationPayerIdNumber")
            .value(is(DEFAULT_WORKERS_COMPENSATION_PAYER_ID_NUMBER))
            .jsonPath("$.workersCompensationPlanName")
            .value(is(DEFAULT_WORKERS_COMPENSATION_PLAN_NAME))
            .jsonPath("$.workersCompensationAdditionalDtls")
            .value(is(DEFAULT_WORKERS_COMPENSATION_ADDITIONAL_DTLS))
            .jsonPath("$.workersCompensationClaimFillingCode")
            .value(is(DEFAULT_WORKERS_COMPENSATION_CLAIM_FILLING_CODE))
            .jsonPath("$.workersCompensationTplCode")
            .value(is(DEFAULT_WORKERS_COMPENSATION_TPL_CODE))
            .jsonPath("$.workersCompensationTplName")
            .value(is(DEFAULT_WORKERS_COMPENSATION_TPL_NAME))
            .jsonPath("$.wcPropertyCasualtyAgencyClaimNo")
            .value(is(DEFAULT_WC_PROPERTY_CASUALTY_AGENCY_CLAIM_NO))
            .jsonPath("$.wcCarrierId")
            .value(is(DEFAULT_WC_CARRIER_ID))
            .jsonPath("$.employerAddressLine1")
            .value(is(DEFAULT_EMPLOYER_ADDRESS_LINE_1))
            .jsonPath("$.employerAddressLine2")
            .value(is(DEFAULT_EMPLOYER_ADDRESS_LINE_2))
            .jsonPath("$.employerCity")
            .value(is(DEFAULT_EMPLOYER_CITY))
            .jsonPath("$.employerState")
            .value(is(DEFAULT_EMPLOYER_STATE))
            .jsonPath("$.employerCountry")
            .value(is(DEFAULT_EMPLOYER_COUNTRY))
            .jsonPath("$.employerZip")
            .value(is(DEFAULT_EMPLOYER_ZIP))
            .jsonPath("$.employerContactNo1")
            .value(is(DEFAULT_EMPLOYER_CONTACT_NO_1))
            .jsonPath("$.employerContactNo2")
            .value(is(DEFAULT_EMPLOYER_CONTACT_NO_2))
            .jsonPath("$.employerFax")
            .value(is(DEFAULT_EMPLOYER_FAX))
            .jsonPath("$.employerEfax")
            .value(is(DEFAULT_EMPLOYER_EFAX))
            .jsonPath("$.employerEmail")
            .value(is(DEFAULT_EMPLOYER_EMAIL))
            .jsonPath("$.employeeRelationship")
            .value(is(DEFAULT_EMPLOYEE_RELATIONSHIP))
            .jsonPath("$.height")
            .value(is(DEFAULT_HEIGHT.doubleValue()))
            .jsonPath("$.weight")
            .value(is(DEFAULT_WEIGHT.doubleValue()))
            .jsonPath("$.functionalAbilities")
            .value(is(DEFAULT_FUNCTIONAL_ABILITIES))
            .jsonPath("$.infectionConditionStatus")
            .value(is(DEFAULT_INFECTION_CONDITION_STATUS))
            .jsonPath("$.diabetesStatus")
            .value(is(DEFAULT_DIABETES_STATUS))
            .jsonPath("$.diagnosisCodeType")
            .value(is(DEFAULT_DIAGNOSIS_CODE_TYPE))
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
            .jsonPath("$.doctorFirstName")
            .value(is(DEFAULT_DOCTOR_FIRST_NAME))
            .jsonPath("$.doctorMiddleName")
            .value(is(DEFAULT_DOCTOR_MIDDLE_NAME))
            .jsonPath("$.doctorLastName")
            .value(is(DEFAULT_DOCTOR_LAST_NAME))
            .jsonPath("$.doctorNameSuffix")
            .value(is(DEFAULT_DOCTOR_NAME_SUFFIX))
            .jsonPath("$.doctorAddressLine1")
            .value(is(DEFAULT_DOCTOR_ADDRESS_LINE_1))
            .jsonPath("$.doctorAddressLine2")
            .value(is(DEFAULT_DOCTOR_ADDRESS_LINE_2))
            .jsonPath("$.doctorAddressCity")
            .value(is(DEFAULT_DOCTOR_ADDRESS_CITY))
            .jsonPath("$.doctorAddressState")
            .value(is(DEFAULT_DOCTOR_ADDRESS_STATE))
            .jsonPath("$.doctorAddressZip")
            .value(is(DEFAULT_DOCTOR_ADDRESS_ZIP))
            .jsonPath("$.doctorContact1")
            .value(is(DEFAULT_DOCTOR_CONTACT_1))
            .jsonPath("$.doctorContact2")
            .value(is(DEFAULT_DOCTOR_CONTACT_2))
            .jsonPath("$.doctorFax")
            .value(is(DEFAULT_DOCTOR_FAX))
            .jsonPath("$.doctorNpiNumber")
            .value(is(DEFAULT_DOCTOR_NPI_NUMBER))
            .jsonPath("$.doctorGender")
            .value(is(DEFAULT_DOCTOR_GENDER))
            .jsonPath("$.doctorTaxonomyCode")
            .value(is(DEFAULT_DOCTOR_TAXONOMY_CODE))
            .jsonPath("$.doctorTaxonomyDescription")
            .value(is(DEFAULT_DOCTOR_TAXONOMY_DESCRIPTION))
            .jsonPath("$.doctorPracticeState")
            .value(is(DEFAULT_DOCTOR_PRACTICE_STATE))
            .jsonPath("$.doctorLicenseNumber")
            .value(is(DEFAULT_DOCTOR_LICENSE_NUMBER))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS))
            .jsonPath("$.createdDate")
            .value(is(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.createdById")
            .value(is(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.createdByName")
            .value(is(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.updatedDate")
            .value(is(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.updatedById")
            .value(is(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.updatedByName")
            .value(is(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.patientDtoUuid")
            .value(is(DEFAULT_PATIENT_DTO_UUID.toString()))
            .jsonPath("$.secondaryInsurerAddressLine1")
            .value(is(DEFAULT_SECONDARY_INSURER_ADDRESS_LINE_1))
            .jsonPath("$.secondaryInsurerAddressLine2")
            .value(is(DEFAULT_SECONDARY_INSURER_ADDRESS_LINE_2))
            .jsonPath("$.secondaryInsurerCity")
            .value(is(DEFAULT_SECONDARY_INSURER_CITY))
            .jsonPath("$.secondaryInsurerState")
            .value(is(DEFAULT_SECONDARY_INSURER_STATE))
            .jsonPath("$.secondaryInsurerZip")
            .value(is(DEFAULT_SECONDARY_INSURER_ZIP))
            .jsonPath("$.secondaryInsurerContact1")
            .value(is(DEFAULT_SECONDARY_INSURER_CONTACT_1))
            .jsonPath("$.secondaryInsurerFax")
            .value(is(DEFAULT_SECONDARY_INSURER_FAX))
            .jsonPath("$.tertiaryInsurerAddressLine1")
            .value(is(DEFAULT_TERTIARY_INSURER_ADDRESS_LINE_1))
            .jsonPath("$.tertiaryInsurerAddressLine2")
            .value(is(DEFAULT_TERTIARY_INSURER_ADDRESS_LINE_2))
            .jsonPath("$.tertiaryInsurerCity")
            .value(is(DEFAULT_TERTIARY_INSURER_CITY))
            .jsonPath("$.tertiaryInsurerState")
            .value(is(DEFAULT_TERTIARY_INSURER_STATE))
            .jsonPath("$.tertiaryInsurerZip")
            .value(is(DEFAULT_TERTIARY_INSURER_ZIP))
            .jsonPath("$.tertiaryInsurerContact1")
            .value(is(DEFAULT_TERTIARY_INSURER_CONTACT_1))
            .jsonPath("$.tertiaryInsurerFax")
            .value(is(DEFAULT_TERTIARY_INSURER_FAX))
            .jsonPath("$.patientDod")
            .value(is(DEFAULT_PATIENT_DOD.toString()));
    }

    @Test
    void getNonExistingPatientDto() {
        // Get the patientDto
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingPatientDto() throws Exception {
        // Initialize the database
        patientDtoRepository.save(patientDto).block();

        int databaseSizeBeforeUpdate = patientDtoRepository.findAll().collectList().block().size();

        // Update the patientDto
        PatientDto updatedPatientDto = patientDtoRepository.findById(patientDto.getPatientDtoId()).block();
        updatedPatientDto
            .patientId(UPDATED_PATIENT_ID)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientMiddleName(UPDATED_PATIENT_MIDDLE_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .dob(UPDATED_DOB)
            .gender(UPDATED_GENDER)
            .ssn(UPDATED_SSN)
            .taxZoneId(UPDATED_TAX_ZONE_ID)
            .taxZoneName(UPDATED_TAX_ZONE_NAME)
            .taxRate(UPDATED_TAX_RATE)
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
            .branchName(UPDATED_BRANCH_NAME)
            .billingAddressLine1(UPDATED_BILLING_ADDRESS_LINE_1)
            .billingAddressLine2(UPDATED_BILLING_ADDRESS_LINE_2)
            .billingAddressCity(UPDATED_BILLING_ADDRESS_CITY)
            .billingAddressState(UPDATED_BILLING_ADDRESS_STATE)
            .billingAddressZip(UPDATED_BILLING_ADDRESS_ZIP)
            .caregiverName(UPDATED_CAREGIVER_NAME)
            .caregiverContact(UPDATED_CAREGIVER_CONTACT)
            .caregiverRelatinshipPatient(UPDATED_CAREGIVER_RELATINSHIP_PATIENT)
            .primaryInsuranceId(UPDATED_PRIMARY_INSURANCE_ID)
            .primaryInsuranceName(UPDATED_PRIMARY_INSURANCE_NAME)
            .primaryInsurancePayerIdNo(UPDATED_PRIMARY_INSURANCE_PAYER_ID_NO)
            .primaryInsurancePayerContactNo(UPDATED_PRIMARY_INSURANCE_PAYER_CONTACT_NO)
            .primaryInsurancePolicyNum(UPDATED_PRIMARY_INSURANCE_POLICY_NUM)
            .primaryInsurancePolicyGroupNum(UPDATED_PRIMARY_INSURANCE_POLICY_GROUP_NUM)
            .primaryInsurancePolicyGroupId(UPDATED_PRIMARY_INSURANCE_POLICY_GROUP_ID)
            .primaryInsurancePolicyStartDate(UPDATED_PRIMARY_INSURANCE_POLICY_START_DATE)
            .primaryInsurancePolicyEndDate(UPDATED_PRIMARY_INSURANCE_POLICY_END_DATE)
            .primaryInsurancePayPercentage(UPDATED_PRIMARY_INSURANCE_PAY_PERCENTAGE)
            .primaryInsuranceDeductableAmt(UPDATED_PRIMARY_INSURANCE_DEDUCTABLE_AMT)
            .secondaryInsuranceId(UPDATED_SECONDARY_INSURANCE_ID)
            .secondaryInsuranceName(UPDATED_SECONDARY_INSURANCE_NAME)
            .secondaryInsurancePayerIdNo(UPDATED_SECONDARY_INSURANCE_PAYER_ID_NO)
            .secondaryInsurancePayerContactNo(UPDATED_SECONDARY_INSURANCE_PAYER_CONTACT_NO)
            .secondaryInsurancePolicyNum(UPDATED_SECONDARY_INSURANCE_POLICY_NUM)
            .secondaryInsurancePolicyGroupNum(UPDATED_SECONDARY_INSURANCE_POLICY_GROUP_NUM)
            .secondaryInsurancePolicyGroupId(UPDATED_SECONDARY_INSURANCE_POLICY_GROUP_ID)
            .secondaryInsurancePolicyStartDate(UPDATED_SECONDARY_INSURANCE_POLICY_START_DATE)
            .secondaryInsurancePolicyEndDate(UPDATED_SECONDARY_INSURANCE_POLICY_END_DATE)
            .secondaryInsurancePayPercentage(UPDATED_SECONDARY_INSURANCE_PAY_PERCENTAGE)
            .secondaryInsuranceDeductableAmt(UPDATED_SECONDARY_INSURANCE_DEDUCTABLE_AMT)
            .tertiaryInsuranceId(UPDATED_TERTIARY_INSURANCE_ID)
            .tertiaryInsuranceName(UPDATED_TERTIARY_INSURANCE_NAME)
            .tertiaryInsurancePayerIdNo(UPDATED_TERTIARY_INSURANCE_PAYER_ID_NO)
            .tertiaryInsurancePayerContactNo(UPDATED_TERTIARY_INSURANCE_PAYER_CONTACT_NO)
            .tertiaryInsurancePolicyNum(UPDATED_TERTIARY_INSURANCE_POLICY_NUM)
            .tertiaryInsurancePolicyGroupNum(UPDATED_TERTIARY_INSURANCE_POLICY_GROUP_NUM)
            .tertiaryInsurancePolicyGroupId(UPDATED_TERTIARY_INSURANCE_POLICY_GROUP_ID)
            .tertiaryInsurancePolicyStartDate(UPDATED_TERTIARY_INSURANCE_POLICY_START_DATE)
            .tertiaryInsurancePolicyEndDate(UPDATED_TERTIARY_INSURANCE_POLICY_END_DATE)
            .tertiaryInsurancePayPercentage(UPDATED_TERTIARY_INSURANCE_PAY_PERCENTAGE)
            .tertiaryInsuranceDeductableAmt(UPDATED_TERTIARY_INSURANCE_DEDUCTABLE_AMT)
            .relationship(UPDATED_RELATIONSHIP)
            .insuredFirstName(UPDATED_INSURED_FIRST_NAME)
            .insuredMiddleName(UPDATED_INSURED_MIDDLE_NAME)
            .insuredLastName(UPDATED_INSURED_LAST_NAME)
            .insuredSuffix(UPDATED_INSURED_SUFFIX)
            .insuredDob(UPDATED_INSURED_DOB)
            .insuredSsn(UPDATED_INSURED_SSN)
            .insuredGender(UPDATED_INSURED_GENDER)
            .primaryInsurerAddressLine1(UPDATED_PRIMARY_INSURER_ADDRESS_LINE_1)
            .primaryInsurerAddressLine2(UPDATED_PRIMARY_INSURER_ADDRESS_LINE_2)
            .primaryInsurerCity(UPDATED_PRIMARY_INSURER_CITY)
            .primaryInsurerState(UPDATED_PRIMARY_INSURER_STATE)
            .primaryInsurerZip(UPDATED_PRIMARY_INSURER_ZIP)
            .primaryInsurerContact1(UPDATED_PRIMARY_INSURER_CONTACT_1)
            .primaryInsurerFax(UPDATED_PRIMARY_INSURER_FAX)
            .alwaysCrossoverStatus(UPDATED_ALWAYS_CROSSOVER_STATUS)
            .primaryInsuranceMemberId(UPDATED_PRIMARY_INSURANCE_MEMBER_ID)
            .secondaryInsuranceMemberId(UPDATED_SECONDARY_INSURANCE_MEMBER_ID)
            .tertiaryInsuranceMemberId(UPDATED_TERTIARY_INSURANCE_MEMBER_ID)
            .patientRelationshipInsured(UPDATED_PATIENT_RELATIONSHIP_INSURED)
            .patientConditionEmployment(UPDATED_PATIENT_CONDITION_EMPLOYMENT)
            .patientConditionAutoAccident(UPDATED_PATIENT_CONDITION_AUTO_ACCIDENT)
            .patientConditionOtherAccident(UPDATED_PATIENT_CONDITION_OTHER_ACCIDENT)
            .insuredEmployer(UPDATED_INSURED_EMPLOYER)
            .workersCompensationPayerIdNumber(UPDATED_WORKERS_COMPENSATION_PAYER_ID_NUMBER)
            .workersCompensationPlanName(UPDATED_WORKERS_COMPENSATION_PLAN_NAME)
            .workersCompensationAdditionalDtls(UPDATED_WORKERS_COMPENSATION_ADDITIONAL_DTLS)
            .workersCompensationClaimFillingCode(UPDATED_WORKERS_COMPENSATION_CLAIM_FILLING_CODE)
            .workersCompensationTplCode(UPDATED_WORKERS_COMPENSATION_TPL_CODE)
            .workersCompensationTplName(UPDATED_WORKERS_COMPENSATION_TPL_NAME)
            .wcPropertyCasualtyAgencyClaimNo(UPDATED_WC_PROPERTY_CASUALTY_AGENCY_CLAIM_NO)
            .wcCarrierId(UPDATED_WC_CARRIER_ID)
            .employerAddressLine1(UPDATED_EMPLOYER_ADDRESS_LINE_1)
            .employerAddressLine2(UPDATED_EMPLOYER_ADDRESS_LINE_2)
            .employerCity(UPDATED_EMPLOYER_CITY)
            .employerState(UPDATED_EMPLOYER_STATE)
            .employerCountry(UPDATED_EMPLOYER_COUNTRY)
            .employerZip(UPDATED_EMPLOYER_ZIP)
            .employerContactNo1(UPDATED_EMPLOYER_CONTACT_NO_1)
            .employerContactNo2(UPDATED_EMPLOYER_CONTACT_NO_2)
            .employerFax(UPDATED_EMPLOYER_FAX)
            .employerEfax(UPDATED_EMPLOYER_EFAX)
            .employerEmail(UPDATED_EMPLOYER_EMAIL)
            .employeeRelationship(UPDATED_EMPLOYEE_RELATIONSHIP)
            .height(UPDATED_HEIGHT)
            .weight(UPDATED_WEIGHT)
            .functionalAbilities(UPDATED_FUNCTIONAL_ABILITIES)
            .infectionConditionStatus(UPDATED_INFECTION_CONDITION_STATUS)
            .diabetesStatus(UPDATED_DIABETES_STATUS)
            .diagnosisCodeType(UPDATED_DIAGNOSIS_CODE_TYPE)
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
            .doctorFirstName(UPDATED_DOCTOR_FIRST_NAME)
            .doctorMiddleName(UPDATED_DOCTOR_MIDDLE_NAME)
            .doctorLastName(UPDATED_DOCTOR_LAST_NAME)
            .doctorNameSuffix(UPDATED_DOCTOR_NAME_SUFFIX)
            .doctorAddressLine1(UPDATED_DOCTOR_ADDRESS_LINE_1)
            .doctorAddressLine2(UPDATED_DOCTOR_ADDRESS_LINE_2)
            .doctorAddressCity(UPDATED_DOCTOR_ADDRESS_CITY)
            .doctorAddressState(UPDATED_DOCTOR_ADDRESS_STATE)
            .doctorAddressZip(UPDATED_DOCTOR_ADDRESS_ZIP)
            .doctorContact1(UPDATED_DOCTOR_CONTACT_1)
            .doctorContact2(UPDATED_DOCTOR_CONTACT_2)
            .doctorFax(UPDATED_DOCTOR_FAX)
            .doctorNpiNumber(UPDATED_DOCTOR_NPI_NUMBER)
            .doctorGender(UPDATED_DOCTOR_GENDER)
            .doctorTaxonomyCode(UPDATED_DOCTOR_TAXONOMY_CODE)
            .doctorTaxonomyDescription(UPDATED_DOCTOR_TAXONOMY_DESCRIPTION)
            .doctorPracticeState(UPDATED_DOCTOR_PRACTICE_STATE)
            .doctorLicenseNumber(UPDATED_DOCTOR_LICENSE_NUMBER)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .patientDtoUuid(UPDATED_PATIENT_DTO_UUID)
            .secondaryInsurerAddressLine1(UPDATED_SECONDARY_INSURER_ADDRESS_LINE_1)
            .secondaryInsurerAddressLine2(UPDATED_SECONDARY_INSURER_ADDRESS_LINE_2)
            .secondaryInsurerCity(UPDATED_SECONDARY_INSURER_CITY)
            .secondaryInsurerState(UPDATED_SECONDARY_INSURER_STATE)
            .secondaryInsurerZip(UPDATED_SECONDARY_INSURER_ZIP)
            .secondaryInsurerContact1(UPDATED_SECONDARY_INSURER_CONTACT_1)
            .secondaryInsurerFax(UPDATED_SECONDARY_INSURER_FAX)
            .tertiaryInsurerAddressLine1(UPDATED_TERTIARY_INSURER_ADDRESS_LINE_1)
            .tertiaryInsurerAddressLine2(UPDATED_TERTIARY_INSURER_ADDRESS_LINE_2)
            .tertiaryInsurerCity(UPDATED_TERTIARY_INSURER_CITY)
            .tertiaryInsurerState(UPDATED_TERTIARY_INSURER_STATE)
            .tertiaryInsurerZip(UPDATED_TERTIARY_INSURER_ZIP)
            .tertiaryInsurerContact1(UPDATED_TERTIARY_INSURER_CONTACT_1)
            .tertiaryInsurerFax(UPDATED_TERTIARY_INSURER_FAX)
            .patientDod(UPDATED_PATIENT_DOD);
        PatientDtoDTO patientDtoDTO = patientDtoMapper.toDto(updatedPatientDto);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, patientDtoDTO.getPatientDtoId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDtoDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientDto in the database
        List<PatientDto> patientDtoList = patientDtoRepository.findAll().collectList().block();
        assertThat(patientDtoList).hasSize(databaseSizeBeforeUpdate);
        PatientDto testPatientDto = patientDtoList.get(patientDtoList.size() - 1);
        assertThat(testPatientDto.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testPatientDto.getPatientFirstName()).isEqualTo(UPDATED_PATIENT_FIRST_NAME);
        assertThat(testPatientDto.getPatientMiddleName()).isEqualTo(UPDATED_PATIENT_MIDDLE_NAME);
        assertThat(testPatientDto.getPatientLastName()).isEqualTo(UPDATED_PATIENT_LAST_NAME);
        assertThat(testPatientDto.getDob()).isEqualTo(UPDATED_DOB);
        assertThat(testPatientDto.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testPatientDto.getSsn()).isEqualTo(UPDATED_SSN);
        assertThat(testPatientDto.getTaxZoneId()).isEqualTo(UPDATED_TAX_ZONE_ID);
        assertThat(testPatientDto.getTaxZoneName()).isEqualTo(UPDATED_TAX_ZONE_NAME);
        assertThat(testPatientDto.getTaxRate()).isEqualTo(UPDATED_TAX_RATE);
        assertThat(testPatientDto.getPatientIdNumber()).isEqualTo(UPDATED_PATIENT_ID_NUMBER);
        assertThat(testPatientDto.getAddressLine1()).isEqualTo(UPDATED_ADDRESS_LINE_1);
        assertThat(testPatientDto.getAddressLine2()).isEqualTo(UPDATED_ADDRESS_LINE_2);
        assertThat(testPatientDto.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testPatientDto.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testPatientDto.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testPatientDto.getZip()).isEqualTo(UPDATED_ZIP);
        assertThat(testPatientDto.getContactNo1()).isEqualTo(UPDATED_CONTACT_NO_1);
        assertThat(testPatientDto.getContactNo2()).isEqualTo(UPDATED_CONTACT_NO_2);
        assertThat(testPatientDto.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testPatientDto.getEfax()).isEqualTo(UPDATED_EFAX);
        assertThat(testPatientDto.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testPatientDto.getBranchName()).isEqualTo(UPDATED_BRANCH_NAME);
        assertThat(testPatientDto.getBillingAddressLine1()).isEqualTo(UPDATED_BILLING_ADDRESS_LINE_1);
        assertThat(testPatientDto.getBillingAddressLine2()).isEqualTo(UPDATED_BILLING_ADDRESS_LINE_2);
        assertThat(testPatientDto.getBillingAddressCity()).isEqualTo(UPDATED_BILLING_ADDRESS_CITY);
        assertThat(testPatientDto.getBillingAddressState()).isEqualTo(UPDATED_BILLING_ADDRESS_STATE);
        assertThat(testPatientDto.getBillingAddressZip()).isEqualTo(UPDATED_BILLING_ADDRESS_ZIP);
        assertThat(testPatientDto.getCaregiverName()).isEqualTo(UPDATED_CAREGIVER_NAME);
        assertThat(testPatientDto.getCaregiverContact()).isEqualTo(UPDATED_CAREGIVER_CONTACT);
        assertThat(testPatientDto.getCaregiverRelatinshipPatient()).isEqualTo(UPDATED_CAREGIVER_RELATINSHIP_PATIENT);
        assertThat(testPatientDto.getPrimaryInsuranceId()).isEqualTo(UPDATED_PRIMARY_INSURANCE_ID);
        assertThat(testPatientDto.getPrimaryInsuranceName()).isEqualTo(UPDATED_PRIMARY_INSURANCE_NAME);
        assertThat(testPatientDto.getPrimaryInsurancePayerIdNo()).isEqualTo(UPDATED_PRIMARY_INSURANCE_PAYER_ID_NO);
        assertThat(testPatientDto.getPrimaryInsurancePayerContactNo()).isEqualTo(UPDATED_PRIMARY_INSURANCE_PAYER_CONTACT_NO);
        assertThat(testPatientDto.getPrimaryInsurancePolicyNum()).isEqualTo(UPDATED_PRIMARY_INSURANCE_POLICY_NUM);
        assertThat(testPatientDto.getPrimaryInsurancePolicyGroupNum()).isEqualTo(UPDATED_PRIMARY_INSURANCE_POLICY_GROUP_NUM);
        assertThat(testPatientDto.getPrimaryInsurancePolicyGroupId()).isEqualTo(UPDATED_PRIMARY_INSURANCE_POLICY_GROUP_ID);
        assertThat(testPatientDto.getPrimaryInsurancePolicyStartDate()).isEqualTo(UPDATED_PRIMARY_INSURANCE_POLICY_START_DATE);
        assertThat(testPatientDto.getPrimaryInsurancePolicyEndDate()).isEqualTo(UPDATED_PRIMARY_INSURANCE_POLICY_END_DATE);
        assertThat(testPatientDto.getPrimaryInsurancePayPercentage()).isEqualTo(UPDATED_PRIMARY_INSURANCE_PAY_PERCENTAGE);
        assertThat(testPatientDto.getPrimaryInsuranceDeductableAmt()).isEqualTo(UPDATED_PRIMARY_INSURANCE_DEDUCTABLE_AMT);
        assertThat(testPatientDto.getSecondaryInsuranceId()).isEqualTo(UPDATED_SECONDARY_INSURANCE_ID);
        assertThat(testPatientDto.getSecondaryInsuranceName()).isEqualTo(UPDATED_SECONDARY_INSURANCE_NAME);
        assertThat(testPatientDto.getSecondaryInsurancePayerIdNo()).isEqualTo(UPDATED_SECONDARY_INSURANCE_PAYER_ID_NO);
        assertThat(testPatientDto.getSecondaryInsurancePayerContactNo()).isEqualTo(UPDATED_SECONDARY_INSURANCE_PAYER_CONTACT_NO);
        assertThat(testPatientDto.getSecondaryInsurancePolicyNum()).isEqualTo(UPDATED_SECONDARY_INSURANCE_POLICY_NUM);
        assertThat(testPatientDto.getSecondaryInsurancePolicyGroupNum()).isEqualTo(UPDATED_SECONDARY_INSURANCE_POLICY_GROUP_NUM);
        assertThat(testPatientDto.getSecondaryInsurancePolicyGroupId()).isEqualTo(UPDATED_SECONDARY_INSURANCE_POLICY_GROUP_ID);
        assertThat(testPatientDto.getSecondaryInsurancePolicyStartDate()).isEqualTo(UPDATED_SECONDARY_INSURANCE_POLICY_START_DATE);
        assertThat(testPatientDto.getSecondaryInsurancePolicyEndDate()).isEqualTo(UPDATED_SECONDARY_INSURANCE_POLICY_END_DATE);
        assertThat(testPatientDto.getSecondaryInsurancePayPercentage()).isEqualTo(UPDATED_SECONDARY_INSURANCE_PAY_PERCENTAGE);
        assertThat(testPatientDto.getSecondaryInsuranceDeductableAmt()).isEqualTo(UPDATED_SECONDARY_INSURANCE_DEDUCTABLE_AMT);
        assertThat(testPatientDto.getTertiaryInsuranceId()).isEqualTo(UPDATED_TERTIARY_INSURANCE_ID);
        assertThat(testPatientDto.getTertiaryInsuranceName()).isEqualTo(UPDATED_TERTIARY_INSURANCE_NAME);
        assertThat(testPatientDto.getTertiaryInsurancePayerIdNo()).isEqualTo(UPDATED_TERTIARY_INSURANCE_PAYER_ID_NO);
        assertThat(testPatientDto.getTertiaryInsurancePayerContactNo()).isEqualTo(UPDATED_TERTIARY_INSURANCE_PAYER_CONTACT_NO);
        assertThat(testPatientDto.getTertiaryInsurancePolicyNum()).isEqualTo(UPDATED_TERTIARY_INSURANCE_POLICY_NUM);
        assertThat(testPatientDto.getTertiaryInsurancePolicyGroupNum()).isEqualTo(UPDATED_TERTIARY_INSURANCE_POLICY_GROUP_NUM);
        assertThat(testPatientDto.getTertiaryInsurancePolicyGroupId()).isEqualTo(UPDATED_TERTIARY_INSURANCE_POLICY_GROUP_ID);
        assertThat(testPatientDto.getTertiaryInsurancePolicyStartDate()).isEqualTo(UPDATED_TERTIARY_INSURANCE_POLICY_START_DATE);
        assertThat(testPatientDto.getTertiaryInsurancePolicyEndDate()).isEqualTo(UPDATED_TERTIARY_INSURANCE_POLICY_END_DATE);
        assertThat(testPatientDto.getTertiaryInsurancePayPercentage()).isEqualTo(UPDATED_TERTIARY_INSURANCE_PAY_PERCENTAGE);
        assertThat(testPatientDto.getTertiaryInsuranceDeductableAmt()).isEqualTo(UPDATED_TERTIARY_INSURANCE_DEDUCTABLE_AMT);
        assertThat(testPatientDto.getRelationship()).isEqualTo(UPDATED_RELATIONSHIP);
        assertThat(testPatientDto.getInsuredFirstName()).isEqualTo(UPDATED_INSURED_FIRST_NAME);
        assertThat(testPatientDto.getInsuredMiddleName()).isEqualTo(UPDATED_INSURED_MIDDLE_NAME);
        assertThat(testPatientDto.getInsuredLastName()).isEqualTo(UPDATED_INSURED_LAST_NAME);
        assertThat(testPatientDto.getInsuredSuffix()).isEqualTo(UPDATED_INSURED_SUFFIX);
        assertThat(testPatientDto.getInsuredDob()).isEqualTo(UPDATED_INSURED_DOB);
        assertThat(testPatientDto.getInsuredSsn()).isEqualTo(UPDATED_INSURED_SSN);
        assertThat(testPatientDto.getInsuredGender()).isEqualTo(UPDATED_INSURED_GENDER);
        assertThat(testPatientDto.getPrimaryInsurerAddressLine1()).isEqualTo(UPDATED_PRIMARY_INSURER_ADDRESS_LINE_1);
        assertThat(testPatientDto.getPrimaryInsurerAddressLine2()).isEqualTo(UPDATED_PRIMARY_INSURER_ADDRESS_LINE_2);
        assertThat(testPatientDto.getPrimaryInsurerCity()).isEqualTo(UPDATED_PRIMARY_INSURER_CITY);
        assertThat(testPatientDto.getPrimaryInsurerState()).isEqualTo(UPDATED_PRIMARY_INSURER_STATE);
        assertThat(testPatientDto.getPrimaryInsurerZip()).isEqualTo(UPDATED_PRIMARY_INSURER_ZIP);
        assertThat(testPatientDto.getPrimaryInsurerContact1()).isEqualTo(UPDATED_PRIMARY_INSURER_CONTACT_1);
        assertThat(testPatientDto.getPrimaryInsurerFax()).isEqualTo(UPDATED_PRIMARY_INSURER_FAX);
        assertThat(testPatientDto.getAlwaysCrossoverStatus()).isEqualTo(UPDATED_ALWAYS_CROSSOVER_STATUS);
        assertThat(testPatientDto.getPrimaryInsuranceMemberId()).isEqualTo(UPDATED_PRIMARY_INSURANCE_MEMBER_ID);
        assertThat(testPatientDto.getSecondaryInsuranceMemberId()).isEqualTo(UPDATED_SECONDARY_INSURANCE_MEMBER_ID);
        assertThat(testPatientDto.getTertiaryInsuranceMemberId()).isEqualTo(UPDATED_TERTIARY_INSURANCE_MEMBER_ID);
        assertThat(testPatientDto.getPatientRelationshipInsured()).isEqualTo(UPDATED_PATIENT_RELATIONSHIP_INSURED);
        assertThat(testPatientDto.getPatientConditionEmployment()).isEqualTo(UPDATED_PATIENT_CONDITION_EMPLOYMENT);
        assertThat(testPatientDto.getPatientConditionAutoAccident()).isEqualTo(UPDATED_PATIENT_CONDITION_AUTO_ACCIDENT);
        assertThat(testPatientDto.getPatientConditionOtherAccident()).isEqualTo(UPDATED_PATIENT_CONDITION_OTHER_ACCIDENT);
        assertThat(testPatientDto.getInsuredEmployer()).isEqualTo(UPDATED_INSURED_EMPLOYER);
        assertThat(testPatientDto.getWorkersCompensationPayerIdNumber()).isEqualTo(UPDATED_WORKERS_COMPENSATION_PAYER_ID_NUMBER);
        assertThat(testPatientDto.getWorkersCompensationPlanName()).isEqualTo(UPDATED_WORKERS_COMPENSATION_PLAN_NAME);
        assertThat(testPatientDto.getWorkersCompensationAdditionalDtls()).isEqualTo(UPDATED_WORKERS_COMPENSATION_ADDITIONAL_DTLS);
        assertThat(testPatientDto.getWorkersCompensationClaimFillingCode()).isEqualTo(UPDATED_WORKERS_COMPENSATION_CLAIM_FILLING_CODE);
        assertThat(testPatientDto.getWorkersCompensationTplCode()).isEqualTo(UPDATED_WORKERS_COMPENSATION_TPL_CODE);
        assertThat(testPatientDto.getWorkersCompensationTplName()).isEqualTo(UPDATED_WORKERS_COMPENSATION_TPL_NAME);
        assertThat(testPatientDto.getWcPropertyCasualtyAgencyClaimNo()).isEqualTo(UPDATED_WC_PROPERTY_CASUALTY_AGENCY_CLAIM_NO);
        assertThat(testPatientDto.getWcCarrierId()).isEqualTo(UPDATED_WC_CARRIER_ID);
        assertThat(testPatientDto.getEmployerAddressLine1()).isEqualTo(UPDATED_EMPLOYER_ADDRESS_LINE_1);
        assertThat(testPatientDto.getEmployerAddressLine2()).isEqualTo(UPDATED_EMPLOYER_ADDRESS_LINE_2);
        assertThat(testPatientDto.getEmployerCity()).isEqualTo(UPDATED_EMPLOYER_CITY);
        assertThat(testPatientDto.getEmployerState()).isEqualTo(UPDATED_EMPLOYER_STATE);
        assertThat(testPatientDto.getEmployerCountry()).isEqualTo(UPDATED_EMPLOYER_COUNTRY);
        assertThat(testPatientDto.getEmployerZip()).isEqualTo(UPDATED_EMPLOYER_ZIP);
        assertThat(testPatientDto.getEmployerContactNo1()).isEqualTo(UPDATED_EMPLOYER_CONTACT_NO_1);
        assertThat(testPatientDto.getEmployerContactNo2()).isEqualTo(UPDATED_EMPLOYER_CONTACT_NO_2);
        assertThat(testPatientDto.getEmployerFax()).isEqualTo(UPDATED_EMPLOYER_FAX);
        assertThat(testPatientDto.getEmployerEfax()).isEqualTo(UPDATED_EMPLOYER_EFAX);
        assertThat(testPatientDto.getEmployerEmail()).isEqualTo(UPDATED_EMPLOYER_EMAIL);
        assertThat(testPatientDto.getEmployeeRelationship()).isEqualTo(UPDATED_EMPLOYEE_RELATIONSHIP);
        assertThat(testPatientDto.getHeight()).isEqualTo(UPDATED_HEIGHT);
        assertThat(testPatientDto.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testPatientDto.getFunctionalAbilities()).isEqualTo(UPDATED_FUNCTIONAL_ABILITIES);
        assertThat(testPatientDto.getInfectionConditionStatus()).isEqualTo(UPDATED_INFECTION_CONDITION_STATUS);
        assertThat(testPatientDto.getDiabetesStatus()).isEqualTo(UPDATED_DIABETES_STATUS);
        assertThat(testPatientDto.getDiagnosisCodeType()).isEqualTo(UPDATED_DIAGNOSIS_CODE_TYPE);
        assertThat(testPatientDto.getIcd10DiagnosisCode1()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_1);
        assertThat(testPatientDto.getIcd10DiagnosisCode2()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_2);
        assertThat(testPatientDto.getIcd10DiagnosisCode3()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_3);
        assertThat(testPatientDto.getIcd10DiagnosisCode4()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_4);
        assertThat(testPatientDto.getIcd10DiagnosisCode5()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_5);
        assertThat(testPatientDto.getIcd10DiagnosisCode6()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_6);
        assertThat(testPatientDto.getIcd10DiagnosisCode7()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_7);
        assertThat(testPatientDto.getIcd10DiagnosisCode8()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_8);
        assertThat(testPatientDto.getIcd10DiagnosisCode9()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_9);
        assertThat(testPatientDto.getIcd10DiagnosisCode10()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_10);
        assertThat(testPatientDto.getIcd10DiagnosisCode11()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_11);
        assertThat(testPatientDto.getIcd10DiagnosisCode12()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_12);
        assertThat(testPatientDto.getDoctorFirstName()).isEqualTo(UPDATED_DOCTOR_FIRST_NAME);
        assertThat(testPatientDto.getDoctorMiddleName()).isEqualTo(UPDATED_DOCTOR_MIDDLE_NAME);
        assertThat(testPatientDto.getDoctorLastName()).isEqualTo(UPDATED_DOCTOR_LAST_NAME);
        assertThat(testPatientDto.getDoctorNameSuffix()).isEqualTo(UPDATED_DOCTOR_NAME_SUFFIX);
        assertThat(testPatientDto.getDoctorAddressLine1()).isEqualTo(UPDATED_DOCTOR_ADDRESS_LINE_1);
        assertThat(testPatientDto.getDoctorAddressLine2()).isEqualTo(UPDATED_DOCTOR_ADDRESS_LINE_2);
        assertThat(testPatientDto.getDoctorAddressCity()).isEqualTo(UPDATED_DOCTOR_ADDRESS_CITY);
        assertThat(testPatientDto.getDoctorAddressState()).isEqualTo(UPDATED_DOCTOR_ADDRESS_STATE);
        assertThat(testPatientDto.getDoctorAddressZip()).isEqualTo(UPDATED_DOCTOR_ADDRESS_ZIP);
        assertThat(testPatientDto.getDoctorContact1()).isEqualTo(UPDATED_DOCTOR_CONTACT_1);
        assertThat(testPatientDto.getDoctorContact2()).isEqualTo(UPDATED_DOCTOR_CONTACT_2);
        assertThat(testPatientDto.getDoctorFax()).isEqualTo(UPDATED_DOCTOR_FAX);
        assertThat(testPatientDto.getDoctorNpiNumber()).isEqualTo(UPDATED_DOCTOR_NPI_NUMBER);
        assertThat(testPatientDto.getDoctorGender()).isEqualTo(UPDATED_DOCTOR_GENDER);
        assertThat(testPatientDto.getDoctorTaxonomyCode()).isEqualTo(UPDATED_DOCTOR_TAXONOMY_CODE);
        assertThat(testPatientDto.getDoctorTaxonomyDescription()).isEqualTo(UPDATED_DOCTOR_TAXONOMY_DESCRIPTION);
        assertThat(testPatientDto.getDoctorPracticeState()).isEqualTo(UPDATED_DOCTOR_PRACTICE_STATE);
        assertThat(testPatientDto.getDoctorLicenseNumber()).isEqualTo(UPDATED_DOCTOR_LICENSE_NUMBER);
        assertThat(testPatientDto.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPatientDto.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPatientDto.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPatientDto.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPatientDto.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPatientDto.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPatientDto.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPatientDto.getPatientDtoUuid()).isEqualTo(UPDATED_PATIENT_DTO_UUID);
        assertThat(testPatientDto.getSecondaryInsurerAddressLine1()).isEqualTo(UPDATED_SECONDARY_INSURER_ADDRESS_LINE_1);
        assertThat(testPatientDto.getSecondaryInsurerAddressLine2()).isEqualTo(UPDATED_SECONDARY_INSURER_ADDRESS_LINE_2);
        assertThat(testPatientDto.getSecondaryInsurerCity()).isEqualTo(UPDATED_SECONDARY_INSURER_CITY);
        assertThat(testPatientDto.getSecondaryInsurerState()).isEqualTo(UPDATED_SECONDARY_INSURER_STATE);
        assertThat(testPatientDto.getSecondaryInsurerZip()).isEqualTo(UPDATED_SECONDARY_INSURER_ZIP);
        assertThat(testPatientDto.getSecondaryInsurerContact1()).isEqualTo(UPDATED_SECONDARY_INSURER_CONTACT_1);
        assertThat(testPatientDto.getSecondaryInsurerFax()).isEqualTo(UPDATED_SECONDARY_INSURER_FAX);
        assertThat(testPatientDto.getTertiaryInsurerAddressLine1()).isEqualTo(UPDATED_TERTIARY_INSURER_ADDRESS_LINE_1);
        assertThat(testPatientDto.getTertiaryInsurerAddressLine2()).isEqualTo(UPDATED_TERTIARY_INSURER_ADDRESS_LINE_2);
        assertThat(testPatientDto.getTertiaryInsurerCity()).isEqualTo(UPDATED_TERTIARY_INSURER_CITY);
        assertThat(testPatientDto.getTertiaryInsurerState()).isEqualTo(UPDATED_TERTIARY_INSURER_STATE);
        assertThat(testPatientDto.getTertiaryInsurerZip()).isEqualTo(UPDATED_TERTIARY_INSURER_ZIP);
        assertThat(testPatientDto.getTertiaryInsurerContact1()).isEqualTo(UPDATED_TERTIARY_INSURER_CONTACT_1);
        assertThat(testPatientDto.getTertiaryInsurerFax()).isEqualTo(UPDATED_TERTIARY_INSURER_FAX);
        assertThat(testPatientDto.getPatientDod()).isEqualTo(UPDATED_PATIENT_DOD);
    }

    @Test
    void putNonExistingPatientDto() throws Exception {
        int databaseSizeBeforeUpdate = patientDtoRepository.findAll().collectList().block().size();
        patientDto.setPatientDtoId(count.incrementAndGet());

        // Create the PatientDto
        PatientDtoDTO patientDtoDTO = patientDtoMapper.toDto(patientDto);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, patientDtoDTO.getPatientDtoId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDtoDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDto in the database
        List<PatientDto> patientDtoList = patientDtoRepository.findAll().collectList().block();
        assertThat(patientDtoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchPatientDto() throws Exception {
        int databaseSizeBeforeUpdate = patientDtoRepository.findAll().collectList().block().size();
        patientDto.setPatientDtoId(count.incrementAndGet());

        // Create the PatientDto
        PatientDtoDTO patientDtoDTO = patientDtoMapper.toDto(patientDto);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDtoDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDto in the database
        List<PatientDto> patientDtoList = patientDtoRepository.findAll().collectList().block();
        assertThat(patientDtoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamPatientDto() throws Exception {
        int databaseSizeBeforeUpdate = patientDtoRepository.findAll().collectList().block().size();
        patientDto.setPatientDtoId(count.incrementAndGet());

        // Create the PatientDto
        PatientDtoDTO patientDtoDTO = patientDtoMapper.toDto(patientDto);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDtoDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PatientDto in the database
        List<PatientDto> patientDtoList = patientDtoRepository.findAll().collectList().block();
        assertThat(patientDtoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdatePatientDtoWithPatch() throws Exception {
        // Initialize the database
        patientDtoRepository.save(patientDto).block();

        int databaseSizeBeforeUpdate = patientDtoRepository.findAll().collectList().block().size();

        // Update the patientDto using partial update
        PatientDto partialUpdatedPatientDto = new PatientDto();
        partialUpdatedPatientDto.setPatientDtoId(patientDto.getPatientDtoId());

        partialUpdatedPatientDto
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .dob(UPDATED_DOB)
            .ssn(UPDATED_SSN)
            .taxZoneId(UPDATED_TAX_ZONE_ID)
            .taxZoneName(UPDATED_TAX_ZONE_NAME)
            .state(UPDATED_STATE)
            .zip(UPDATED_ZIP)
            .contactNo1(UPDATED_CONTACT_NO_1)
            .fax(UPDATED_FAX)
            .efax(UPDATED_EFAX)
            .billingAddressLine1(UPDATED_BILLING_ADDRESS_LINE_1)
            .billingAddressState(UPDATED_BILLING_ADDRESS_STATE)
            .caregiverContact(UPDATED_CAREGIVER_CONTACT)
            .primaryInsuranceId(UPDATED_PRIMARY_INSURANCE_ID)
            .primaryInsuranceName(UPDATED_PRIMARY_INSURANCE_NAME)
            .primaryInsurancePolicyGroupNum(UPDATED_PRIMARY_INSURANCE_POLICY_GROUP_NUM)
            .primaryInsurancePolicyGroupId(UPDATED_PRIMARY_INSURANCE_POLICY_GROUP_ID)
            .primaryInsurancePolicyStartDate(UPDATED_PRIMARY_INSURANCE_POLICY_START_DATE)
            .primaryInsuranceDeductableAmt(UPDATED_PRIMARY_INSURANCE_DEDUCTABLE_AMT)
            .secondaryInsuranceId(UPDATED_SECONDARY_INSURANCE_ID)
            .secondaryInsurancePolicyNum(UPDATED_SECONDARY_INSURANCE_POLICY_NUM)
            .secondaryInsurancePolicyGroupId(UPDATED_SECONDARY_INSURANCE_POLICY_GROUP_ID)
            .secondaryInsurancePolicyStartDate(UPDATED_SECONDARY_INSURANCE_POLICY_START_DATE)
            .secondaryInsurancePolicyEndDate(UPDATED_SECONDARY_INSURANCE_POLICY_END_DATE)
            .secondaryInsurancePayPercentage(UPDATED_SECONDARY_INSURANCE_PAY_PERCENTAGE)
            .tertiaryInsurancePayerContactNo(UPDATED_TERTIARY_INSURANCE_PAYER_CONTACT_NO)
            .tertiaryInsurancePolicyGroupId(UPDATED_TERTIARY_INSURANCE_POLICY_GROUP_ID)
            .tertiaryInsurancePolicyEndDate(UPDATED_TERTIARY_INSURANCE_POLICY_END_DATE)
            .tertiaryInsurancePayPercentage(UPDATED_TERTIARY_INSURANCE_PAY_PERCENTAGE)
            .tertiaryInsuranceDeductableAmt(UPDATED_TERTIARY_INSURANCE_DEDUCTABLE_AMT)
            .insuredFirstName(UPDATED_INSURED_FIRST_NAME)
            .insuredMiddleName(UPDATED_INSURED_MIDDLE_NAME)
            .insuredLastName(UPDATED_INSURED_LAST_NAME)
            .primaryInsurerAddressLine1(UPDATED_PRIMARY_INSURER_ADDRESS_LINE_1)
            .primaryInsurerCity(UPDATED_PRIMARY_INSURER_CITY)
            .primaryInsurerContact1(UPDATED_PRIMARY_INSURER_CONTACT_1)
            .primaryInsuranceMemberId(UPDATED_PRIMARY_INSURANCE_MEMBER_ID)
            .secondaryInsuranceMemberId(UPDATED_SECONDARY_INSURANCE_MEMBER_ID)
            .patientRelationshipInsured(UPDATED_PATIENT_RELATIONSHIP_INSURED)
            .patientConditionEmployment(UPDATED_PATIENT_CONDITION_EMPLOYMENT)
            .insuredEmployer(UPDATED_INSURED_EMPLOYER)
            .workersCompensationClaimFillingCode(UPDATED_WORKERS_COMPENSATION_CLAIM_FILLING_CODE)
            .workersCompensationTplCode(UPDATED_WORKERS_COMPENSATION_TPL_CODE)
            .workersCompensationTplName(UPDATED_WORKERS_COMPENSATION_TPL_NAME)
            .wcCarrierId(UPDATED_WC_CARRIER_ID)
            .employerAddressLine1(UPDATED_EMPLOYER_ADDRESS_LINE_1)
            .employerState(UPDATED_EMPLOYER_STATE)
            .employerCountry(UPDATED_EMPLOYER_COUNTRY)
            .employerContactNo1(UPDATED_EMPLOYER_CONTACT_NO_1)
            .employerEfax(UPDATED_EMPLOYER_EFAX)
            .employerEmail(UPDATED_EMPLOYER_EMAIL)
            .functionalAbilities(UPDATED_FUNCTIONAL_ABILITIES)
            .diagnosisCodeType(UPDATED_DIAGNOSIS_CODE_TYPE)
            .icd10DiagnosisCode1(UPDATED_ICD_10_DIAGNOSIS_CODE_1)
            .icd10DiagnosisCode5(UPDATED_ICD_10_DIAGNOSIS_CODE_5)
            .icd10DiagnosisCode9(UPDATED_ICD_10_DIAGNOSIS_CODE_9)
            .icd10DiagnosisCode10(UPDATED_ICD_10_DIAGNOSIS_CODE_10)
            .icd10DiagnosisCode12(UPDATED_ICD_10_DIAGNOSIS_CODE_12)
            .doctorLastName(UPDATED_DOCTOR_LAST_NAME)
            .doctorNameSuffix(UPDATED_DOCTOR_NAME_SUFFIX)
            .doctorLicenseNumber(UPDATED_DOCTOR_LICENSE_NUMBER)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .secondaryInsurerAddressLine1(UPDATED_SECONDARY_INSURER_ADDRESS_LINE_1)
            .secondaryInsurerAddressLine2(UPDATED_SECONDARY_INSURER_ADDRESS_LINE_2)
            .secondaryInsurerState(UPDATED_SECONDARY_INSURER_STATE)
            .secondaryInsurerFax(UPDATED_SECONDARY_INSURER_FAX)
            .tertiaryInsurerAddressLine2(UPDATED_TERTIARY_INSURER_ADDRESS_LINE_2)
            .tertiaryInsurerCity(UPDATED_TERTIARY_INSURER_CITY)
            .tertiaryInsurerState(UPDATED_TERTIARY_INSURER_STATE)
            .tertiaryInsurerFax(UPDATED_TERTIARY_INSURER_FAX)
            .patientDod(UPDATED_PATIENT_DOD);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPatientDto.getPatientDtoId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPatientDto))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientDto in the database
        List<PatientDto> patientDtoList = patientDtoRepository.findAll().collectList().block();
        assertThat(patientDtoList).hasSize(databaseSizeBeforeUpdate);
        PatientDto testPatientDto = patientDtoList.get(patientDtoList.size() - 1);
        assertThat(testPatientDto.getPatientId()).isEqualTo(DEFAULT_PATIENT_ID);
        assertThat(testPatientDto.getPatientFirstName()).isEqualTo(UPDATED_PATIENT_FIRST_NAME);
        assertThat(testPatientDto.getPatientMiddleName()).isEqualTo(DEFAULT_PATIENT_MIDDLE_NAME);
        assertThat(testPatientDto.getPatientLastName()).isEqualTo(UPDATED_PATIENT_LAST_NAME);
        assertThat(testPatientDto.getDob()).isEqualTo(UPDATED_DOB);
        assertThat(testPatientDto.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testPatientDto.getSsn()).isEqualTo(UPDATED_SSN);
        assertThat(testPatientDto.getTaxZoneId()).isEqualTo(UPDATED_TAX_ZONE_ID);
        assertThat(testPatientDto.getTaxZoneName()).isEqualTo(UPDATED_TAX_ZONE_NAME);
        assertThat(testPatientDto.getTaxRate()).isEqualTo(DEFAULT_TAX_RATE);
        assertThat(testPatientDto.getPatientIdNumber()).isEqualTo(DEFAULT_PATIENT_ID_NUMBER);
        assertThat(testPatientDto.getAddressLine1()).isEqualTo(DEFAULT_ADDRESS_LINE_1);
        assertThat(testPatientDto.getAddressLine2()).isEqualTo(DEFAULT_ADDRESS_LINE_2);
        assertThat(testPatientDto.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testPatientDto.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testPatientDto.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testPatientDto.getZip()).isEqualTo(UPDATED_ZIP);
        assertThat(testPatientDto.getContactNo1()).isEqualTo(UPDATED_CONTACT_NO_1);
        assertThat(testPatientDto.getContactNo2()).isEqualTo(DEFAULT_CONTACT_NO_2);
        assertThat(testPatientDto.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testPatientDto.getEfax()).isEqualTo(UPDATED_EFAX);
        assertThat(testPatientDto.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testPatientDto.getBranchName()).isEqualTo(DEFAULT_BRANCH_NAME);
        assertThat(testPatientDto.getBillingAddressLine1()).isEqualTo(UPDATED_BILLING_ADDRESS_LINE_1);
        assertThat(testPatientDto.getBillingAddressLine2()).isEqualTo(DEFAULT_BILLING_ADDRESS_LINE_2);
        assertThat(testPatientDto.getBillingAddressCity()).isEqualTo(DEFAULT_BILLING_ADDRESS_CITY);
        assertThat(testPatientDto.getBillingAddressState()).isEqualTo(UPDATED_BILLING_ADDRESS_STATE);
        assertThat(testPatientDto.getBillingAddressZip()).isEqualTo(DEFAULT_BILLING_ADDRESS_ZIP);
        assertThat(testPatientDto.getCaregiverName()).isEqualTo(DEFAULT_CAREGIVER_NAME);
        assertThat(testPatientDto.getCaregiverContact()).isEqualTo(UPDATED_CAREGIVER_CONTACT);
        assertThat(testPatientDto.getCaregiverRelatinshipPatient()).isEqualTo(DEFAULT_CAREGIVER_RELATINSHIP_PATIENT);
        assertThat(testPatientDto.getPrimaryInsuranceId()).isEqualTo(UPDATED_PRIMARY_INSURANCE_ID);
        assertThat(testPatientDto.getPrimaryInsuranceName()).isEqualTo(UPDATED_PRIMARY_INSURANCE_NAME);
        assertThat(testPatientDto.getPrimaryInsurancePayerIdNo()).isEqualTo(DEFAULT_PRIMARY_INSURANCE_PAYER_ID_NO);
        assertThat(testPatientDto.getPrimaryInsurancePayerContactNo()).isEqualTo(DEFAULT_PRIMARY_INSURANCE_PAYER_CONTACT_NO);
        assertThat(testPatientDto.getPrimaryInsurancePolicyNum()).isEqualTo(DEFAULT_PRIMARY_INSURANCE_POLICY_NUM);
        assertThat(testPatientDto.getPrimaryInsurancePolicyGroupNum()).isEqualTo(UPDATED_PRIMARY_INSURANCE_POLICY_GROUP_NUM);
        assertThat(testPatientDto.getPrimaryInsurancePolicyGroupId()).isEqualTo(UPDATED_PRIMARY_INSURANCE_POLICY_GROUP_ID);
        assertThat(testPatientDto.getPrimaryInsurancePolicyStartDate()).isEqualTo(UPDATED_PRIMARY_INSURANCE_POLICY_START_DATE);
        assertThat(testPatientDto.getPrimaryInsurancePolicyEndDate()).isEqualTo(DEFAULT_PRIMARY_INSURANCE_POLICY_END_DATE);
        assertThat(testPatientDto.getPrimaryInsurancePayPercentage()).isEqualTo(DEFAULT_PRIMARY_INSURANCE_PAY_PERCENTAGE);
        assertThat(testPatientDto.getPrimaryInsuranceDeductableAmt()).isEqualTo(UPDATED_PRIMARY_INSURANCE_DEDUCTABLE_AMT);
        assertThat(testPatientDto.getSecondaryInsuranceId()).isEqualTo(UPDATED_SECONDARY_INSURANCE_ID);
        assertThat(testPatientDto.getSecondaryInsuranceName()).isEqualTo(DEFAULT_SECONDARY_INSURANCE_NAME);
        assertThat(testPatientDto.getSecondaryInsurancePayerIdNo()).isEqualTo(DEFAULT_SECONDARY_INSURANCE_PAYER_ID_NO);
        assertThat(testPatientDto.getSecondaryInsurancePayerContactNo()).isEqualTo(DEFAULT_SECONDARY_INSURANCE_PAYER_CONTACT_NO);
        assertThat(testPatientDto.getSecondaryInsurancePolicyNum()).isEqualTo(UPDATED_SECONDARY_INSURANCE_POLICY_NUM);
        assertThat(testPatientDto.getSecondaryInsurancePolicyGroupNum()).isEqualTo(DEFAULT_SECONDARY_INSURANCE_POLICY_GROUP_NUM);
        assertThat(testPatientDto.getSecondaryInsurancePolicyGroupId()).isEqualTo(UPDATED_SECONDARY_INSURANCE_POLICY_GROUP_ID);
        assertThat(testPatientDto.getSecondaryInsurancePolicyStartDate()).isEqualTo(UPDATED_SECONDARY_INSURANCE_POLICY_START_DATE);
        assertThat(testPatientDto.getSecondaryInsurancePolicyEndDate()).isEqualTo(UPDATED_SECONDARY_INSURANCE_POLICY_END_DATE);
        assertThat(testPatientDto.getSecondaryInsurancePayPercentage()).isEqualTo(UPDATED_SECONDARY_INSURANCE_PAY_PERCENTAGE);
        assertThat(testPatientDto.getSecondaryInsuranceDeductableAmt()).isEqualTo(DEFAULT_SECONDARY_INSURANCE_DEDUCTABLE_AMT);
        assertThat(testPatientDto.getTertiaryInsuranceId()).isEqualTo(DEFAULT_TERTIARY_INSURANCE_ID);
        assertThat(testPatientDto.getTertiaryInsuranceName()).isEqualTo(DEFAULT_TERTIARY_INSURANCE_NAME);
        assertThat(testPatientDto.getTertiaryInsurancePayerIdNo()).isEqualTo(DEFAULT_TERTIARY_INSURANCE_PAYER_ID_NO);
        assertThat(testPatientDto.getTertiaryInsurancePayerContactNo()).isEqualTo(UPDATED_TERTIARY_INSURANCE_PAYER_CONTACT_NO);
        assertThat(testPatientDto.getTertiaryInsurancePolicyNum()).isEqualTo(DEFAULT_TERTIARY_INSURANCE_POLICY_NUM);
        assertThat(testPatientDto.getTertiaryInsurancePolicyGroupNum()).isEqualTo(DEFAULT_TERTIARY_INSURANCE_POLICY_GROUP_NUM);
        assertThat(testPatientDto.getTertiaryInsurancePolicyGroupId()).isEqualTo(UPDATED_TERTIARY_INSURANCE_POLICY_GROUP_ID);
        assertThat(testPatientDto.getTertiaryInsurancePolicyStartDate()).isEqualTo(DEFAULT_TERTIARY_INSURANCE_POLICY_START_DATE);
        assertThat(testPatientDto.getTertiaryInsurancePolicyEndDate()).isEqualTo(UPDATED_TERTIARY_INSURANCE_POLICY_END_DATE);
        assertThat(testPatientDto.getTertiaryInsurancePayPercentage()).isEqualTo(UPDATED_TERTIARY_INSURANCE_PAY_PERCENTAGE);
        assertThat(testPatientDto.getTertiaryInsuranceDeductableAmt()).isEqualTo(UPDATED_TERTIARY_INSURANCE_DEDUCTABLE_AMT);
        assertThat(testPatientDto.getRelationship()).isEqualTo(DEFAULT_RELATIONSHIP);
        assertThat(testPatientDto.getInsuredFirstName()).isEqualTo(UPDATED_INSURED_FIRST_NAME);
        assertThat(testPatientDto.getInsuredMiddleName()).isEqualTo(UPDATED_INSURED_MIDDLE_NAME);
        assertThat(testPatientDto.getInsuredLastName()).isEqualTo(UPDATED_INSURED_LAST_NAME);
        assertThat(testPatientDto.getInsuredSuffix()).isEqualTo(DEFAULT_INSURED_SUFFIX);
        assertThat(testPatientDto.getInsuredDob()).isEqualTo(DEFAULT_INSURED_DOB);
        assertThat(testPatientDto.getInsuredSsn()).isEqualTo(DEFAULT_INSURED_SSN);
        assertThat(testPatientDto.getInsuredGender()).isEqualTo(DEFAULT_INSURED_GENDER);
        assertThat(testPatientDto.getPrimaryInsurerAddressLine1()).isEqualTo(UPDATED_PRIMARY_INSURER_ADDRESS_LINE_1);
        assertThat(testPatientDto.getPrimaryInsurerAddressLine2()).isEqualTo(DEFAULT_PRIMARY_INSURER_ADDRESS_LINE_2);
        assertThat(testPatientDto.getPrimaryInsurerCity()).isEqualTo(UPDATED_PRIMARY_INSURER_CITY);
        assertThat(testPatientDto.getPrimaryInsurerState()).isEqualTo(DEFAULT_PRIMARY_INSURER_STATE);
        assertThat(testPatientDto.getPrimaryInsurerZip()).isEqualTo(DEFAULT_PRIMARY_INSURER_ZIP);
        assertThat(testPatientDto.getPrimaryInsurerContact1()).isEqualTo(UPDATED_PRIMARY_INSURER_CONTACT_1);
        assertThat(testPatientDto.getPrimaryInsurerFax()).isEqualTo(DEFAULT_PRIMARY_INSURER_FAX);
        assertThat(testPatientDto.getAlwaysCrossoverStatus()).isEqualTo(DEFAULT_ALWAYS_CROSSOVER_STATUS);
        assertThat(testPatientDto.getPrimaryInsuranceMemberId()).isEqualTo(UPDATED_PRIMARY_INSURANCE_MEMBER_ID);
        assertThat(testPatientDto.getSecondaryInsuranceMemberId()).isEqualTo(UPDATED_SECONDARY_INSURANCE_MEMBER_ID);
        assertThat(testPatientDto.getTertiaryInsuranceMemberId()).isEqualTo(DEFAULT_TERTIARY_INSURANCE_MEMBER_ID);
        assertThat(testPatientDto.getPatientRelationshipInsured()).isEqualTo(UPDATED_PATIENT_RELATIONSHIP_INSURED);
        assertThat(testPatientDto.getPatientConditionEmployment()).isEqualTo(UPDATED_PATIENT_CONDITION_EMPLOYMENT);
        assertThat(testPatientDto.getPatientConditionAutoAccident()).isEqualTo(DEFAULT_PATIENT_CONDITION_AUTO_ACCIDENT);
        assertThat(testPatientDto.getPatientConditionOtherAccident()).isEqualTo(DEFAULT_PATIENT_CONDITION_OTHER_ACCIDENT);
        assertThat(testPatientDto.getInsuredEmployer()).isEqualTo(UPDATED_INSURED_EMPLOYER);
        assertThat(testPatientDto.getWorkersCompensationPayerIdNumber()).isEqualTo(DEFAULT_WORKERS_COMPENSATION_PAYER_ID_NUMBER);
        assertThat(testPatientDto.getWorkersCompensationPlanName()).isEqualTo(DEFAULT_WORKERS_COMPENSATION_PLAN_NAME);
        assertThat(testPatientDto.getWorkersCompensationAdditionalDtls()).isEqualTo(DEFAULT_WORKERS_COMPENSATION_ADDITIONAL_DTLS);
        assertThat(testPatientDto.getWorkersCompensationClaimFillingCode()).isEqualTo(UPDATED_WORKERS_COMPENSATION_CLAIM_FILLING_CODE);
        assertThat(testPatientDto.getWorkersCompensationTplCode()).isEqualTo(UPDATED_WORKERS_COMPENSATION_TPL_CODE);
        assertThat(testPatientDto.getWorkersCompensationTplName()).isEqualTo(UPDATED_WORKERS_COMPENSATION_TPL_NAME);
        assertThat(testPatientDto.getWcPropertyCasualtyAgencyClaimNo()).isEqualTo(DEFAULT_WC_PROPERTY_CASUALTY_AGENCY_CLAIM_NO);
        assertThat(testPatientDto.getWcCarrierId()).isEqualTo(UPDATED_WC_CARRIER_ID);
        assertThat(testPatientDto.getEmployerAddressLine1()).isEqualTo(UPDATED_EMPLOYER_ADDRESS_LINE_1);
        assertThat(testPatientDto.getEmployerAddressLine2()).isEqualTo(DEFAULT_EMPLOYER_ADDRESS_LINE_2);
        assertThat(testPatientDto.getEmployerCity()).isEqualTo(DEFAULT_EMPLOYER_CITY);
        assertThat(testPatientDto.getEmployerState()).isEqualTo(UPDATED_EMPLOYER_STATE);
        assertThat(testPatientDto.getEmployerCountry()).isEqualTo(UPDATED_EMPLOYER_COUNTRY);
        assertThat(testPatientDto.getEmployerZip()).isEqualTo(DEFAULT_EMPLOYER_ZIP);
        assertThat(testPatientDto.getEmployerContactNo1()).isEqualTo(UPDATED_EMPLOYER_CONTACT_NO_1);
        assertThat(testPatientDto.getEmployerContactNo2()).isEqualTo(DEFAULT_EMPLOYER_CONTACT_NO_2);
        assertThat(testPatientDto.getEmployerFax()).isEqualTo(DEFAULT_EMPLOYER_FAX);
        assertThat(testPatientDto.getEmployerEfax()).isEqualTo(UPDATED_EMPLOYER_EFAX);
        assertThat(testPatientDto.getEmployerEmail()).isEqualTo(UPDATED_EMPLOYER_EMAIL);
        assertThat(testPatientDto.getEmployeeRelationship()).isEqualTo(DEFAULT_EMPLOYEE_RELATIONSHIP);
        assertThat(testPatientDto.getHeight()).isEqualTo(DEFAULT_HEIGHT);
        assertThat(testPatientDto.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testPatientDto.getFunctionalAbilities()).isEqualTo(UPDATED_FUNCTIONAL_ABILITIES);
        assertThat(testPatientDto.getInfectionConditionStatus()).isEqualTo(DEFAULT_INFECTION_CONDITION_STATUS);
        assertThat(testPatientDto.getDiabetesStatus()).isEqualTo(DEFAULT_DIABETES_STATUS);
        assertThat(testPatientDto.getDiagnosisCodeType()).isEqualTo(UPDATED_DIAGNOSIS_CODE_TYPE);
        assertThat(testPatientDto.getIcd10DiagnosisCode1()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_1);
        assertThat(testPatientDto.getIcd10DiagnosisCode2()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_2);
        assertThat(testPatientDto.getIcd10DiagnosisCode3()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_3);
        assertThat(testPatientDto.getIcd10DiagnosisCode4()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_4);
        assertThat(testPatientDto.getIcd10DiagnosisCode5()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_5);
        assertThat(testPatientDto.getIcd10DiagnosisCode6()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_6);
        assertThat(testPatientDto.getIcd10DiagnosisCode7()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_7);
        assertThat(testPatientDto.getIcd10DiagnosisCode8()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_8);
        assertThat(testPatientDto.getIcd10DiagnosisCode9()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_9);
        assertThat(testPatientDto.getIcd10DiagnosisCode10()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_10);
        assertThat(testPatientDto.getIcd10DiagnosisCode11()).isEqualTo(DEFAULT_ICD_10_DIAGNOSIS_CODE_11);
        assertThat(testPatientDto.getIcd10DiagnosisCode12()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_12);
        assertThat(testPatientDto.getDoctorFirstName()).isEqualTo(DEFAULT_DOCTOR_FIRST_NAME);
        assertThat(testPatientDto.getDoctorMiddleName()).isEqualTo(DEFAULT_DOCTOR_MIDDLE_NAME);
        assertThat(testPatientDto.getDoctorLastName()).isEqualTo(UPDATED_DOCTOR_LAST_NAME);
        assertThat(testPatientDto.getDoctorNameSuffix()).isEqualTo(UPDATED_DOCTOR_NAME_SUFFIX);
        assertThat(testPatientDto.getDoctorAddressLine1()).isEqualTo(DEFAULT_DOCTOR_ADDRESS_LINE_1);
        assertThat(testPatientDto.getDoctorAddressLine2()).isEqualTo(DEFAULT_DOCTOR_ADDRESS_LINE_2);
        assertThat(testPatientDto.getDoctorAddressCity()).isEqualTo(DEFAULT_DOCTOR_ADDRESS_CITY);
        assertThat(testPatientDto.getDoctorAddressState()).isEqualTo(DEFAULT_DOCTOR_ADDRESS_STATE);
        assertThat(testPatientDto.getDoctorAddressZip()).isEqualTo(DEFAULT_DOCTOR_ADDRESS_ZIP);
        assertThat(testPatientDto.getDoctorContact1()).isEqualTo(DEFAULT_DOCTOR_CONTACT_1);
        assertThat(testPatientDto.getDoctorContact2()).isEqualTo(DEFAULT_DOCTOR_CONTACT_2);
        assertThat(testPatientDto.getDoctorFax()).isEqualTo(DEFAULT_DOCTOR_FAX);
        assertThat(testPatientDto.getDoctorNpiNumber()).isEqualTo(DEFAULT_DOCTOR_NPI_NUMBER);
        assertThat(testPatientDto.getDoctorGender()).isEqualTo(DEFAULT_DOCTOR_GENDER);
        assertThat(testPatientDto.getDoctorTaxonomyCode()).isEqualTo(DEFAULT_DOCTOR_TAXONOMY_CODE);
        assertThat(testPatientDto.getDoctorTaxonomyDescription()).isEqualTo(DEFAULT_DOCTOR_TAXONOMY_DESCRIPTION);
        assertThat(testPatientDto.getDoctorPracticeState()).isEqualTo(DEFAULT_DOCTOR_PRACTICE_STATE);
        assertThat(testPatientDto.getDoctorLicenseNumber()).isEqualTo(UPDATED_DOCTOR_LICENSE_NUMBER);
        assertThat(testPatientDto.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPatientDto.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPatientDto.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testPatientDto.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testPatientDto.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testPatientDto.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testPatientDto.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPatientDto.getPatientDtoUuid()).isEqualTo(DEFAULT_PATIENT_DTO_UUID);
        assertThat(testPatientDto.getSecondaryInsurerAddressLine1()).isEqualTo(UPDATED_SECONDARY_INSURER_ADDRESS_LINE_1);
        assertThat(testPatientDto.getSecondaryInsurerAddressLine2()).isEqualTo(UPDATED_SECONDARY_INSURER_ADDRESS_LINE_2);
        assertThat(testPatientDto.getSecondaryInsurerCity()).isEqualTo(DEFAULT_SECONDARY_INSURER_CITY);
        assertThat(testPatientDto.getSecondaryInsurerState()).isEqualTo(UPDATED_SECONDARY_INSURER_STATE);
        assertThat(testPatientDto.getSecondaryInsurerZip()).isEqualTo(DEFAULT_SECONDARY_INSURER_ZIP);
        assertThat(testPatientDto.getSecondaryInsurerContact1()).isEqualTo(DEFAULT_SECONDARY_INSURER_CONTACT_1);
        assertThat(testPatientDto.getSecondaryInsurerFax()).isEqualTo(UPDATED_SECONDARY_INSURER_FAX);
        assertThat(testPatientDto.getTertiaryInsurerAddressLine1()).isEqualTo(DEFAULT_TERTIARY_INSURER_ADDRESS_LINE_1);
        assertThat(testPatientDto.getTertiaryInsurerAddressLine2()).isEqualTo(UPDATED_TERTIARY_INSURER_ADDRESS_LINE_2);
        assertThat(testPatientDto.getTertiaryInsurerCity()).isEqualTo(UPDATED_TERTIARY_INSURER_CITY);
        assertThat(testPatientDto.getTertiaryInsurerState()).isEqualTo(UPDATED_TERTIARY_INSURER_STATE);
        assertThat(testPatientDto.getTertiaryInsurerZip()).isEqualTo(DEFAULT_TERTIARY_INSURER_ZIP);
        assertThat(testPatientDto.getTertiaryInsurerContact1()).isEqualTo(DEFAULT_TERTIARY_INSURER_CONTACT_1);
        assertThat(testPatientDto.getTertiaryInsurerFax()).isEqualTo(UPDATED_TERTIARY_INSURER_FAX);
        assertThat(testPatientDto.getPatientDod()).isEqualTo(UPDATED_PATIENT_DOD);
    }

    @Test
    void fullUpdatePatientDtoWithPatch() throws Exception {
        // Initialize the database
        patientDtoRepository.save(patientDto).block();

        int databaseSizeBeforeUpdate = patientDtoRepository.findAll().collectList().block().size();

        // Update the patientDto using partial update
        PatientDto partialUpdatedPatientDto = new PatientDto();
        partialUpdatedPatientDto.setPatientDtoId(patientDto.getPatientDtoId());

        partialUpdatedPatientDto
            .patientId(UPDATED_PATIENT_ID)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientMiddleName(UPDATED_PATIENT_MIDDLE_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .dob(UPDATED_DOB)
            .gender(UPDATED_GENDER)
            .ssn(UPDATED_SSN)
            .taxZoneId(UPDATED_TAX_ZONE_ID)
            .taxZoneName(UPDATED_TAX_ZONE_NAME)
            .taxRate(UPDATED_TAX_RATE)
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
            .branchName(UPDATED_BRANCH_NAME)
            .billingAddressLine1(UPDATED_BILLING_ADDRESS_LINE_1)
            .billingAddressLine2(UPDATED_BILLING_ADDRESS_LINE_2)
            .billingAddressCity(UPDATED_BILLING_ADDRESS_CITY)
            .billingAddressState(UPDATED_BILLING_ADDRESS_STATE)
            .billingAddressZip(UPDATED_BILLING_ADDRESS_ZIP)
            .caregiverName(UPDATED_CAREGIVER_NAME)
            .caregiverContact(UPDATED_CAREGIVER_CONTACT)
            .caregiverRelatinshipPatient(UPDATED_CAREGIVER_RELATINSHIP_PATIENT)
            .primaryInsuranceId(UPDATED_PRIMARY_INSURANCE_ID)
            .primaryInsuranceName(UPDATED_PRIMARY_INSURANCE_NAME)
            .primaryInsurancePayerIdNo(UPDATED_PRIMARY_INSURANCE_PAYER_ID_NO)
            .primaryInsurancePayerContactNo(UPDATED_PRIMARY_INSURANCE_PAYER_CONTACT_NO)
            .primaryInsurancePolicyNum(UPDATED_PRIMARY_INSURANCE_POLICY_NUM)
            .primaryInsurancePolicyGroupNum(UPDATED_PRIMARY_INSURANCE_POLICY_GROUP_NUM)
            .primaryInsurancePolicyGroupId(UPDATED_PRIMARY_INSURANCE_POLICY_GROUP_ID)
            .primaryInsurancePolicyStartDate(UPDATED_PRIMARY_INSURANCE_POLICY_START_DATE)
            .primaryInsurancePolicyEndDate(UPDATED_PRIMARY_INSURANCE_POLICY_END_DATE)
            .primaryInsurancePayPercentage(UPDATED_PRIMARY_INSURANCE_PAY_PERCENTAGE)
            .primaryInsuranceDeductableAmt(UPDATED_PRIMARY_INSURANCE_DEDUCTABLE_AMT)
            .secondaryInsuranceId(UPDATED_SECONDARY_INSURANCE_ID)
            .secondaryInsuranceName(UPDATED_SECONDARY_INSURANCE_NAME)
            .secondaryInsurancePayerIdNo(UPDATED_SECONDARY_INSURANCE_PAYER_ID_NO)
            .secondaryInsurancePayerContactNo(UPDATED_SECONDARY_INSURANCE_PAYER_CONTACT_NO)
            .secondaryInsurancePolicyNum(UPDATED_SECONDARY_INSURANCE_POLICY_NUM)
            .secondaryInsurancePolicyGroupNum(UPDATED_SECONDARY_INSURANCE_POLICY_GROUP_NUM)
            .secondaryInsurancePolicyGroupId(UPDATED_SECONDARY_INSURANCE_POLICY_GROUP_ID)
            .secondaryInsurancePolicyStartDate(UPDATED_SECONDARY_INSURANCE_POLICY_START_DATE)
            .secondaryInsurancePolicyEndDate(UPDATED_SECONDARY_INSURANCE_POLICY_END_DATE)
            .secondaryInsurancePayPercentage(UPDATED_SECONDARY_INSURANCE_PAY_PERCENTAGE)
            .secondaryInsuranceDeductableAmt(UPDATED_SECONDARY_INSURANCE_DEDUCTABLE_AMT)
            .tertiaryInsuranceId(UPDATED_TERTIARY_INSURANCE_ID)
            .tertiaryInsuranceName(UPDATED_TERTIARY_INSURANCE_NAME)
            .tertiaryInsurancePayerIdNo(UPDATED_TERTIARY_INSURANCE_PAYER_ID_NO)
            .tertiaryInsurancePayerContactNo(UPDATED_TERTIARY_INSURANCE_PAYER_CONTACT_NO)
            .tertiaryInsurancePolicyNum(UPDATED_TERTIARY_INSURANCE_POLICY_NUM)
            .tertiaryInsurancePolicyGroupNum(UPDATED_TERTIARY_INSURANCE_POLICY_GROUP_NUM)
            .tertiaryInsurancePolicyGroupId(UPDATED_TERTIARY_INSURANCE_POLICY_GROUP_ID)
            .tertiaryInsurancePolicyStartDate(UPDATED_TERTIARY_INSURANCE_POLICY_START_DATE)
            .tertiaryInsurancePolicyEndDate(UPDATED_TERTIARY_INSURANCE_POLICY_END_DATE)
            .tertiaryInsurancePayPercentage(UPDATED_TERTIARY_INSURANCE_PAY_PERCENTAGE)
            .tertiaryInsuranceDeductableAmt(UPDATED_TERTIARY_INSURANCE_DEDUCTABLE_AMT)
            .relationship(UPDATED_RELATIONSHIP)
            .insuredFirstName(UPDATED_INSURED_FIRST_NAME)
            .insuredMiddleName(UPDATED_INSURED_MIDDLE_NAME)
            .insuredLastName(UPDATED_INSURED_LAST_NAME)
            .insuredSuffix(UPDATED_INSURED_SUFFIX)
            .insuredDob(UPDATED_INSURED_DOB)
            .insuredSsn(UPDATED_INSURED_SSN)
            .insuredGender(UPDATED_INSURED_GENDER)
            .primaryInsurerAddressLine1(UPDATED_PRIMARY_INSURER_ADDRESS_LINE_1)
            .primaryInsurerAddressLine2(UPDATED_PRIMARY_INSURER_ADDRESS_LINE_2)
            .primaryInsurerCity(UPDATED_PRIMARY_INSURER_CITY)
            .primaryInsurerState(UPDATED_PRIMARY_INSURER_STATE)
            .primaryInsurerZip(UPDATED_PRIMARY_INSURER_ZIP)
            .primaryInsurerContact1(UPDATED_PRIMARY_INSURER_CONTACT_1)
            .primaryInsurerFax(UPDATED_PRIMARY_INSURER_FAX)
            .alwaysCrossoverStatus(UPDATED_ALWAYS_CROSSOVER_STATUS)
            .primaryInsuranceMemberId(UPDATED_PRIMARY_INSURANCE_MEMBER_ID)
            .secondaryInsuranceMemberId(UPDATED_SECONDARY_INSURANCE_MEMBER_ID)
            .tertiaryInsuranceMemberId(UPDATED_TERTIARY_INSURANCE_MEMBER_ID)
            .patientRelationshipInsured(UPDATED_PATIENT_RELATIONSHIP_INSURED)
            .patientConditionEmployment(UPDATED_PATIENT_CONDITION_EMPLOYMENT)
            .patientConditionAutoAccident(UPDATED_PATIENT_CONDITION_AUTO_ACCIDENT)
            .patientConditionOtherAccident(UPDATED_PATIENT_CONDITION_OTHER_ACCIDENT)
            .insuredEmployer(UPDATED_INSURED_EMPLOYER)
            .workersCompensationPayerIdNumber(UPDATED_WORKERS_COMPENSATION_PAYER_ID_NUMBER)
            .workersCompensationPlanName(UPDATED_WORKERS_COMPENSATION_PLAN_NAME)
            .workersCompensationAdditionalDtls(UPDATED_WORKERS_COMPENSATION_ADDITIONAL_DTLS)
            .workersCompensationClaimFillingCode(UPDATED_WORKERS_COMPENSATION_CLAIM_FILLING_CODE)
            .workersCompensationTplCode(UPDATED_WORKERS_COMPENSATION_TPL_CODE)
            .workersCompensationTplName(UPDATED_WORKERS_COMPENSATION_TPL_NAME)
            .wcPropertyCasualtyAgencyClaimNo(UPDATED_WC_PROPERTY_CASUALTY_AGENCY_CLAIM_NO)
            .wcCarrierId(UPDATED_WC_CARRIER_ID)
            .employerAddressLine1(UPDATED_EMPLOYER_ADDRESS_LINE_1)
            .employerAddressLine2(UPDATED_EMPLOYER_ADDRESS_LINE_2)
            .employerCity(UPDATED_EMPLOYER_CITY)
            .employerState(UPDATED_EMPLOYER_STATE)
            .employerCountry(UPDATED_EMPLOYER_COUNTRY)
            .employerZip(UPDATED_EMPLOYER_ZIP)
            .employerContactNo1(UPDATED_EMPLOYER_CONTACT_NO_1)
            .employerContactNo2(UPDATED_EMPLOYER_CONTACT_NO_2)
            .employerFax(UPDATED_EMPLOYER_FAX)
            .employerEfax(UPDATED_EMPLOYER_EFAX)
            .employerEmail(UPDATED_EMPLOYER_EMAIL)
            .employeeRelationship(UPDATED_EMPLOYEE_RELATIONSHIP)
            .height(UPDATED_HEIGHT)
            .weight(UPDATED_WEIGHT)
            .functionalAbilities(UPDATED_FUNCTIONAL_ABILITIES)
            .infectionConditionStatus(UPDATED_INFECTION_CONDITION_STATUS)
            .diabetesStatus(UPDATED_DIABETES_STATUS)
            .diagnosisCodeType(UPDATED_DIAGNOSIS_CODE_TYPE)
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
            .doctorFirstName(UPDATED_DOCTOR_FIRST_NAME)
            .doctorMiddleName(UPDATED_DOCTOR_MIDDLE_NAME)
            .doctorLastName(UPDATED_DOCTOR_LAST_NAME)
            .doctorNameSuffix(UPDATED_DOCTOR_NAME_SUFFIX)
            .doctorAddressLine1(UPDATED_DOCTOR_ADDRESS_LINE_1)
            .doctorAddressLine2(UPDATED_DOCTOR_ADDRESS_LINE_2)
            .doctorAddressCity(UPDATED_DOCTOR_ADDRESS_CITY)
            .doctorAddressState(UPDATED_DOCTOR_ADDRESS_STATE)
            .doctorAddressZip(UPDATED_DOCTOR_ADDRESS_ZIP)
            .doctorContact1(UPDATED_DOCTOR_CONTACT_1)
            .doctorContact2(UPDATED_DOCTOR_CONTACT_2)
            .doctorFax(UPDATED_DOCTOR_FAX)
            .doctorNpiNumber(UPDATED_DOCTOR_NPI_NUMBER)
            .doctorGender(UPDATED_DOCTOR_GENDER)
            .doctorTaxonomyCode(UPDATED_DOCTOR_TAXONOMY_CODE)
            .doctorTaxonomyDescription(UPDATED_DOCTOR_TAXONOMY_DESCRIPTION)
            .doctorPracticeState(UPDATED_DOCTOR_PRACTICE_STATE)
            .doctorLicenseNumber(UPDATED_DOCTOR_LICENSE_NUMBER)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .patientDtoUuid(UPDATED_PATIENT_DTO_UUID)
            .secondaryInsurerAddressLine1(UPDATED_SECONDARY_INSURER_ADDRESS_LINE_1)
            .secondaryInsurerAddressLine2(UPDATED_SECONDARY_INSURER_ADDRESS_LINE_2)
            .secondaryInsurerCity(UPDATED_SECONDARY_INSURER_CITY)
            .secondaryInsurerState(UPDATED_SECONDARY_INSURER_STATE)
            .secondaryInsurerZip(UPDATED_SECONDARY_INSURER_ZIP)
            .secondaryInsurerContact1(UPDATED_SECONDARY_INSURER_CONTACT_1)
            .secondaryInsurerFax(UPDATED_SECONDARY_INSURER_FAX)
            .tertiaryInsurerAddressLine1(UPDATED_TERTIARY_INSURER_ADDRESS_LINE_1)
            .tertiaryInsurerAddressLine2(UPDATED_TERTIARY_INSURER_ADDRESS_LINE_2)
            .tertiaryInsurerCity(UPDATED_TERTIARY_INSURER_CITY)
            .tertiaryInsurerState(UPDATED_TERTIARY_INSURER_STATE)
            .tertiaryInsurerZip(UPDATED_TERTIARY_INSURER_ZIP)
            .tertiaryInsurerContact1(UPDATED_TERTIARY_INSURER_CONTACT_1)
            .tertiaryInsurerFax(UPDATED_TERTIARY_INSURER_FAX)
            .patientDod(UPDATED_PATIENT_DOD);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPatientDto.getPatientDtoId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPatientDto))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientDto in the database
        List<PatientDto> patientDtoList = patientDtoRepository.findAll().collectList().block();
        assertThat(patientDtoList).hasSize(databaseSizeBeforeUpdate);
        PatientDto testPatientDto = patientDtoList.get(patientDtoList.size() - 1);
        assertThat(testPatientDto.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testPatientDto.getPatientFirstName()).isEqualTo(UPDATED_PATIENT_FIRST_NAME);
        assertThat(testPatientDto.getPatientMiddleName()).isEqualTo(UPDATED_PATIENT_MIDDLE_NAME);
        assertThat(testPatientDto.getPatientLastName()).isEqualTo(UPDATED_PATIENT_LAST_NAME);
        assertThat(testPatientDto.getDob()).isEqualTo(UPDATED_DOB);
        assertThat(testPatientDto.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testPatientDto.getSsn()).isEqualTo(UPDATED_SSN);
        assertThat(testPatientDto.getTaxZoneId()).isEqualTo(UPDATED_TAX_ZONE_ID);
        assertThat(testPatientDto.getTaxZoneName()).isEqualTo(UPDATED_TAX_ZONE_NAME);
        assertThat(testPatientDto.getTaxRate()).isEqualTo(UPDATED_TAX_RATE);
        assertThat(testPatientDto.getPatientIdNumber()).isEqualTo(UPDATED_PATIENT_ID_NUMBER);
        assertThat(testPatientDto.getAddressLine1()).isEqualTo(UPDATED_ADDRESS_LINE_1);
        assertThat(testPatientDto.getAddressLine2()).isEqualTo(UPDATED_ADDRESS_LINE_2);
        assertThat(testPatientDto.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testPatientDto.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testPatientDto.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testPatientDto.getZip()).isEqualTo(UPDATED_ZIP);
        assertThat(testPatientDto.getContactNo1()).isEqualTo(UPDATED_CONTACT_NO_1);
        assertThat(testPatientDto.getContactNo2()).isEqualTo(UPDATED_CONTACT_NO_2);
        assertThat(testPatientDto.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testPatientDto.getEfax()).isEqualTo(UPDATED_EFAX);
        assertThat(testPatientDto.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testPatientDto.getBranchName()).isEqualTo(UPDATED_BRANCH_NAME);
        assertThat(testPatientDto.getBillingAddressLine1()).isEqualTo(UPDATED_BILLING_ADDRESS_LINE_1);
        assertThat(testPatientDto.getBillingAddressLine2()).isEqualTo(UPDATED_BILLING_ADDRESS_LINE_2);
        assertThat(testPatientDto.getBillingAddressCity()).isEqualTo(UPDATED_BILLING_ADDRESS_CITY);
        assertThat(testPatientDto.getBillingAddressState()).isEqualTo(UPDATED_BILLING_ADDRESS_STATE);
        assertThat(testPatientDto.getBillingAddressZip()).isEqualTo(UPDATED_BILLING_ADDRESS_ZIP);
        assertThat(testPatientDto.getCaregiverName()).isEqualTo(UPDATED_CAREGIVER_NAME);
        assertThat(testPatientDto.getCaregiverContact()).isEqualTo(UPDATED_CAREGIVER_CONTACT);
        assertThat(testPatientDto.getCaregiverRelatinshipPatient()).isEqualTo(UPDATED_CAREGIVER_RELATINSHIP_PATIENT);
        assertThat(testPatientDto.getPrimaryInsuranceId()).isEqualTo(UPDATED_PRIMARY_INSURANCE_ID);
        assertThat(testPatientDto.getPrimaryInsuranceName()).isEqualTo(UPDATED_PRIMARY_INSURANCE_NAME);
        assertThat(testPatientDto.getPrimaryInsurancePayerIdNo()).isEqualTo(UPDATED_PRIMARY_INSURANCE_PAYER_ID_NO);
        assertThat(testPatientDto.getPrimaryInsurancePayerContactNo()).isEqualTo(UPDATED_PRIMARY_INSURANCE_PAYER_CONTACT_NO);
        assertThat(testPatientDto.getPrimaryInsurancePolicyNum()).isEqualTo(UPDATED_PRIMARY_INSURANCE_POLICY_NUM);
        assertThat(testPatientDto.getPrimaryInsurancePolicyGroupNum()).isEqualTo(UPDATED_PRIMARY_INSURANCE_POLICY_GROUP_NUM);
        assertThat(testPatientDto.getPrimaryInsurancePolicyGroupId()).isEqualTo(UPDATED_PRIMARY_INSURANCE_POLICY_GROUP_ID);
        assertThat(testPatientDto.getPrimaryInsurancePolicyStartDate()).isEqualTo(UPDATED_PRIMARY_INSURANCE_POLICY_START_DATE);
        assertThat(testPatientDto.getPrimaryInsurancePolicyEndDate()).isEqualTo(UPDATED_PRIMARY_INSURANCE_POLICY_END_DATE);
        assertThat(testPatientDto.getPrimaryInsurancePayPercentage()).isEqualTo(UPDATED_PRIMARY_INSURANCE_PAY_PERCENTAGE);
        assertThat(testPatientDto.getPrimaryInsuranceDeductableAmt()).isEqualTo(UPDATED_PRIMARY_INSURANCE_DEDUCTABLE_AMT);
        assertThat(testPatientDto.getSecondaryInsuranceId()).isEqualTo(UPDATED_SECONDARY_INSURANCE_ID);
        assertThat(testPatientDto.getSecondaryInsuranceName()).isEqualTo(UPDATED_SECONDARY_INSURANCE_NAME);
        assertThat(testPatientDto.getSecondaryInsurancePayerIdNo()).isEqualTo(UPDATED_SECONDARY_INSURANCE_PAYER_ID_NO);
        assertThat(testPatientDto.getSecondaryInsurancePayerContactNo()).isEqualTo(UPDATED_SECONDARY_INSURANCE_PAYER_CONTACT_NO);
        assertThat(testPatientDto.getSecondaryInsurancePolicyNum()).isEqualTo(UPDATED_SECONDARY_INSURANCE_POLICY_NUM);
        assertThat(testPatientDto.getSecondaryInsurancePolicyGroupNum()).isEqualTo(UPDATED_SECONDARY_INSURANCE_POLICY_GROUP_NUM);
        assertThat(testPatientDto.getSecondaryInsurancePolicyGroupId()).isEqualTo(UPDATED_SECONDARY_INSURANCE_POLICY_GROUP_ID);
        assertThat(testPatientDto.getSecondaryInsurancePolicyStartDate()).isEqualTo(UPDATED_SECONDARY_INSURANCE_POLICY_START_DATE);
        assertThat(testPatientDto.getSecondaryInsurancePolicyEndDate()).isEqualTo(UPDATED_SECONDARY_INSURANCE_POLICY_END_DATE);
        assertThat(testPatientDto.getSecondaryInsurancePayPercentage()).isEqualTo(UPDATED_SECONDARY_INSURANCE_PAY_PERCENTAGE);
        assertThat(testPatientDto.getSecondaryInsuranceDeductableAmt()).isEqualTo(UPDATED_SECONDARY_INSURANCE_DEDUCTABLE_AMT);
        assertThat(testPatientDto.getTertiaryInsuranceId()).isEqualTo(UPDATED_TERTIARY_INSURANCE_ID);
        assertThat(testPatientDto.getTertiaryInsuranceName()).isEqualTo(UPDATED_TERTIARY_INSURANCE_NAME);
        assertThat(testPatientDto.getTertiaryInsurancePayerIdNo()).isEqualTo(UPDATED_TERTIARY_INSURANCE_PAYER_ID_NO);
        assertThat(testPatientDto.getTertiaryInsurancePayerContactNo()).isEqualTo(UPDATED_TERTIARY_INSURANCE_PAYER_CONTACT_NO);
        assertThat(testPatientDto.getTertiaryInsurancePolicyNum()).isEqualTo(UPDATED_TERTIARY_INSURANCE_POLICY_NUM);
        assertThat(testPatientDto.getTertiaryInsurancePolicyGroupNum()).isEqualTo(UPDATED_TERTIARY_INSURANCE_POLICY_GROUP_NUM);
        assertThat(testPatientDto.getTertiaryInsurancePolicyGroupId()).isEqualTo(UPDATED_TERTIARY_INSURANCE_POLICY_GROUP_ID);
        assertThat(testPatientDto.getTertiaryInsurancePolicyStartDate()).isEqualTo(UPDATED_TERTIARY_INSURANCE_POLICY_START_DATE);
        assertThat(testPatientDto.getTertiaryInsurancePolicyEndDate()).isEqualTo(UPDATED_TERTIARY_INSURANCE_POLICY_END_DATE);
        assertThat(testPatientDto.getTertiaryInsurancePayPercentage()).isEqualTo(UPDATED_TERTIARY_INSURANCE_PAY_PERCENTAGE);
        assertThat(testPatientDto.getTertiaryInsuranceDeductableAmt()).isEqualTo(UPDATED_TERTIARY_INSURANCE_DEDUCTABLE_AMT);
        assertThat(testPatientDto.getRelationship()).isEqualTo(UPDATED_RELATIONSHIP);
        assertThat(testPatientDto.getInsuredFirstName()).isEqualTo(UPDATED_INSURED_FIRST_NAME);
        assertThat(testPatientDto.getInsuredMiddleName()).isEqualTo(UPDATED_INSURED_MIDDLE_NAME);
        assertThat(testPatientDto.getInsuredLastName()).isEqualTo(UPDATED_INSURED_LAST_NAME);
        assertThat(testPatientDto.getInsuredSuffix()).isEqualTo(UPDATED_INSURED_SUFFIX);
        assertThat(testPatientDto.getInsuredDob()).isEqualTo(UPDATED_INSURED_DOB);
        assertThat(testPatientDto.getInsuredSsn()).isEqualTo(UPDATED_INSURED_SSN);
        assertThat(testPatientDto.getInsuredGender()).isEqualTo(UPDATED_INSURED_GENDER);
        assertThat(testPatientDto.getPrimaryInsurerAddressLine1()).isEqualTo(UPDATED_PRIMARY_INSURER_ADDRESS_LINE_1);
        assertThat(testPatientDto.getPrimaryInsurerAddressLine2()).isEqualTo(UPDATED_PRIMARY_INSURER_ADDRESS_LINE_2);
        assertThat(testPatientDto.getPrimaryInsurerCity()).isEqualTo(UPDATED_PRIMARY_INSURER_CITY);
        assertThat(testPatientDto.getPrimaryInsurerState()).isEqualTo(UPDATED_PRIMARY_INSURER_STATE);
        assertThat(testPatientDto.getPrimaryInsurerZip()).isEqualTo(UPDATED_PRIMARY_INSURER_ZIP);
        assertThat(testPatientDto.getPrimaryInsurerContact1()).isEqualTo(UPDATED_PRIMARY_INSURER_CONTACT_1);
        assertThat(testPatientDto.getPrimaryInsurerFax()).isEqualTo(UPDATED_PRIMARY_INSURER_FAX);
        assertThat(testPatientDto.getAlwaysCrossoverStatus()).isEqualTo(UPDATED_ALWAYS_CROSSOVER_STATUS);
        assertThat(testPatientDto.getPrimaryInsuranceMemberId()).isEqualTo(UPDATED_PRIMARY_INSURANCE_MEMBER_ID);
        assertThat(testPatientDto.getSecondaryInsuranceMemberId()).isEqualTo(UPDATED_SECONDARY_INSURANCE_MEMBER_ID);
        assertThat(testPatientDto.getTertiaryInsuranceMemberId()).isEqualTo(UPDATED_TERTIARY_INSURANCE_MEMBER_ID);
        assertThat(testPatientDto.getPatientRelationshipInsured()).isEqualTo(UPDATED_PATIENT_RELATIONSHIP_INSURED);
        assertThat(testPatientDto.getPatientConditionEmployment()).isEqualTo(UPDATED_PATIENT_CONDITION_EMPLOYMENT);
        assertThat(testPatientDto.getPatientConditionAutoAccident()).isEqualTo(UPDATED_PATIENT_CONDITION_AUTO_ACCIDENT);
        assertThat(testPatientDto.getPatientConditionOtherAccident()).isEqualTo(UPDATED_PATIENT_CONDITION_OTHER_ACCIDENT);
        assertThat(testPatientDto.getInsuredEmployer()).isEqualTo(UPDATED_INSURED_EMPLOYER);
        assertThat(testPatientDto.getWorkersCompensationPayerIdNumber()).isEqualTo(UPDATED_WORKERS_COMPENSATION_PAYER_ID_NUMBER);
        assertThat(testPatientDto.getWorkersCompensationPlanName()).isEqualTo(UPDATED_WORKERS_COMPENSATION_PLAN_NAME);
        assertThat(testPatientDto.getWorkersCompensationAdditionalDtls()).isEqualTo(UPDATED_WORKERS_COMPENSATION_ADDITIONAL_DTLS);
        assertThat(testPatientDto.getWorkersCompensationClaimFillingCode()).isEqualTo(UPDATED_WORKERS_COMPENSATION_CLAIM_FILLING_CODE);
        assertThat(testPatientDto.getWorkersCompensationTplCode()).isEqualTo(UPDATED_WORKERS_COMPENSATION_TPL_CODE);
        assertThat(testPatientDto.getWorkersCompensationTplName()).isEqualTo(UPDATED_WORKERS_COMPENSATION_TPL_NAME);
        assertThat(testPatientDto.getWcPropertyCasualtyAgencyClaimNo()).isEqualTo(UPDATED_WC_PROPERTY_CASUALTY_AGENCY_CLAIM_NO);
        assertThat(testPatientDto.getWcCarrierId()).isEqualTo(UPDATED_WC_CARRIER_ID);
        assertThat(testPatientDto.getEmployerAddressLine1()).isEqualTo(UPDATED_EMPLOYER_ADDRESS_LINE_1);
        assertThat(testPatientDto.getEmployerAddressLine2()).isEqualTo(UPDATED_EMPLOYER_ADDRESS_LINE_2);
        assertThat(testPatientDto.getEmployerCity()).isEqualTo(UPDATED_EMPLOYER_CITY);
        assertThat(testPatientDto.getEmployerState()).isEqualTo(UPDATED_EMPLOYER_STATE);
        assertThat(testPatientDto.getEmployerCountry()).isEqualTo(UPDATED_EMPLOYER_COUNTRY);
        assertThat(testPatientDto.getEmployerZip()).isEqualTo(UPDATED_EMPLOYER_ZIP);
        assertThat(testPatientDto.getEmployerContactNo1()).isEqualTo(UPDATED_EMPLOYER_CONTACT_NO_1);
        assertThat(testPatientDto.getEmployerContactNo2()).isEqualTo(UPDATED_EMPLOYER_CONTACT_NO_2);
        assertThat(testPatientDto.getEmployerFax()).isEqualTo(UPDATED_EMPLOYER_FAX);
        assertThat(testPatientDto.getEmployerEfax()).isEqualTo(UPDATED_EMPLOYER_EFAX);
        assertThat(testPatientDto.getEmployerEmail()).isEqualTo(UPDATED_EMPLOYER_EMAIL);
        assertThat(testPatientDto.getEmployeeRelationship()).isEqualTo(UPDATED_EMPLOYEE_RELATIONSHIP);
        assertThat(testPatientDto.getHeight()).isEqualTo(UPDATED_HEIGHT);
        assertThat(testPatientDto.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testPatientDto.getFunctionalAbilities()).isEqualTo(UPDATED_FUNCTIONAL_ABILITIES);
        assertThat(testPatientDto.getInfectionConditionStatus()).isEqualTo(UPDATED_INFECTION_CONDITION_STATUS);
        assertThat(testPatientDto.getDiabetesStatus()).isEqualTo(UPDATED_DIABETES_STATUS);
        assertThat(testPatientDto.getDiagnosisCodeType()).isEqualTo(UPDATED_DIAGNOSIS_CODE_TYPE);
        assertThat(testPatientDto.getIcd10DiagnosisCode1()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_1);
        assertThat(testPatientDto.getIcd10DiagnosisCode2()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_2);
        assertThat(testPatientDto.getIcd10DiagnosisCode3()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_3);
        assertThat(testPatientDto.getIcd10DiagnosisCode4()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_4);
        assertThat(testPatientDto.getIcd10DiagnosisCode5()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_5);
        assertThat(testPatientDto.getIcd10DiagnosisCode6()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_6);
        assertThat(testPatientDto.getIcd10DiagnosisCode7()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_7);
        assertThat(testPatientDto.getIcd10DiagnosisCode8()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_8);
        assertThat(testPatientDto.getIcd10DiagnosisCode9()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_9);
        assertThat(testPatientDto.getIcd10DiagnosisCode10()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_10);
        assertThat(testPatientDto.getIcd10DiagnosisCode11()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_11);
        assertThat(testPatientDto.getIcd10DiagnosisCode12()).isEqualTo(UPDATED_ICD_10_DIAGNOSIS_CODE_12);
        assertThat(testPatientDto.getDoctorFirstName()).isEqualTo(UPDATED_DOCTOR_FIRST_NAME);
        assertThat(testPatientDto.getDoctorMiddleName()).isEqualTo(UPDATED_DOCTOR_MIDDLE_NAME);
        assertThat(testPatientDto.getDoctorLastName()).isEqualTo(UPDATED_DOCTOR_LAST_NAME);
        assertThat(testPatientDto.getDoctorNameSuffix()).isEqualTo(UPDATED_DOCTOR_NAME_SUFFIX);
        assertThat(testPatientDto.getDoctorAddressLine1()).isEqualTo(UPDATED_DOCTOR_ADDRESS_LINE_1);
        assertThat(testPatientDto.getDoctorAddressLine2()).isEqualTo(UPDATED_DOCTOR_ADDRESS_LINE_2);
        assertThat(testPatientDto.getDoctorAddressCity()).isEqualTo(UPDATED_DOCTOR_ADDRESS_CITY);
        assertThat(testPatientDto.getDoctorAddressState()).isEqualTo(UPDATED_DOCTOR_ADDRESS_STATE);
        assertThat(testPatientDto.getDoctorAddressZip()).isEqualTo(UPDATED_DOCTOR_ADDRESS_ZIP);
        assertThat(testPatientDto.getDoctorContact1()).isEqualTo(UPDATED_DOCTOR_CONTACT_1);
        assertThat(testPatientDto.getDoctorContact2()).isEqualTo(UPDATED_DOCTOR_CONTACT_2);
        assertThat(testPatientDto.getDoctorFax()).isEqualTo(UPDATED_DOCTOR_FAX);
        assertThat(testPatientDto.getDoctorNpiNumber()).isEqualTo(UPDATED_DOCTOR_NPI_NUMBER);
        assertThat(testPatientDto.getDoctorGender()).isEqualTo(UPDATED_DOCTOR_GENDER);
        assertThat(testPatientDto.getDoctorTaxonomyCode()).isEqualTo(UPDATED_DOCTOR_TAXONOMY_CODE);
        assertThat(testPatientDto.getDoctorTaxonomyDescription()).isEqualTo(UPDATED_DOCTOR_TAXONOMY_DESCRIPTION);
        assertThat(testPatientDto.getDoctorPracticeState()).isEqualTo(UPDATED_DOCTOR_PRACTICE_STATE);
        assertThat(testPatientDto.getDoctorLicenseNumber()).isEqualTo(UPDATED_DOCTOR_LICENSE_NUMBER);
        assertThat(testPatientDto.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPatientDto.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPatientDto.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPatientDto.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPatientDto.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPatientDto.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPatientDto.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPatientDto.getPatientDtoUuid()).isEqualTo(UPDATED_PATIENT_DTO_UUID);
        assertThat(testPatientDto.getSecondaryInsurerAddressLine1()).isEqualTo(UPDATED_SECONDARY_INSURER_ADDRESS_LINE_1);
        assertThat(testPatientDto.getSecondaryInsurerAddressLine2()).isEqualTo(UPDATED_SECONDARY_INSURER_ADDRESS_LINE_2);
        assertThat(testPatientDto.getSecondaryInsurerCity()).isEqualTo(UPDATED_SECONDARY_INSURER_CITY);
        assertThat(testPatientDto.getSecondaryInsurerState()).isEqualTo(UPDATED_SECONDARY_INSURER_STATE);
        assertThat(testPatientDto.getSecondaryInsurerZip()).isEqualTo(UPDATED_SECONDARY_INSURER_ZIP);
        assertThat(testPatientDto.getSecondaryInsurerContact1()).isEqualTo(UPDATED_SECONDARY_INSURER_CONTACT_1);
        assertThat(testPatientDto.getSecondaryInsurerFax()).isEqualTo(UPDATED_SECONDARY_INSURER_FAX);
        assertThat(testPatientDto.getTertiaryInsurerAddressLine1()).isEqualTo(UPDATED_TERTIARY_INSURER_ADDRESS_LINE_1);
        assertThat(testPatientDto.getTertiaryInsurerAddressLine2()).isEqualTo(UPDATED_TERTIARY_INSURER_ADDRESS_LINE_2);
        assertThat(testPatientDto.getTertiaryInsurerCity()).isEqualTo(UPDATED_TERTIARY_INSURER_CITY);
        assertThat(testPatientDto.getTertiaryInsurerState()).isEqualTo(UPDATED_TERTIARY_INSURER_STATE);
        assertThat(testPatientDto.getTertiaryInsurerZip()).isEqualTo(UPDATED_TERTIARY_INSURER_ZIP);
        assertThat(testPatientDto.getTertiaryInsurerContact1()).isEqualTo(UPDATED_TERTIARY_INSURER_CONTACT_1);
        assertThat(testPatientDto.getTertiaryInsurerFax()).isEqualTo(UPDATED_TERTIARY_INSURER_FAX);
        assertThat(testPatientDto.getPatientDod()).isEqualTo(UPDATED_PATIENT_DOD);
    }

    @Test
    void patchNonExistingPatientDto() throws Exception {
        int databaseSizeBeforeUpdate = patientDtoRepository.findAll().collectList().block().size();
        patientDto.setPatientDtoId(count.incrementAndGet());

        // Create the PatientDto
        PatientDtoDTO patientDtoDTO = patientDtoMapper.toDto(patientDto);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, patientDtoDTO.getPatientDtoId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDtoDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDto in the database
        List<PatientDto> patientDtoList = patientDtoRepository.findAll().collectList().block();
        assertThat(patientDtoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchPatientDto() throws Exception {
        int databaseSizeBeforeUpdate = patientDtoRepository.findAll().collectList().block().size();
        patientDto.setPatientDtoId(count.incrementAndGet());

        // Create the PatientDto
        PatientDtoDTO patientDtoDTO = patientDtoMapper.toDto(patientDto);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDtoDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDto in the database
        List<PatientDto> patientDtoList = patientDtoRepository.findAll().collectList().block();
        assertThat(patientDtoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamPatientDto() throws Exception {
        int databaseSizeBeforeUpdate = patientDtoRepository.findAll().collectList().block().size();
        patientDto.setPatientDtoId(count.incrementAndGet());

        // Create the PatientDto
        PatientDtoDTO patientDtoDTO = patientDtoMapper.toDto(patientDto);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDtoDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PatientDto in the database
        List<PatientDto> patientDtoList = patientDtoRepository.findAll().collectList().block();
        assertThat(patientDtoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deletePatientDto() {
        // Initialize the database
        patientDtoRepository.save(patientDto).block();

        int databaseSizeBeforeDelete = patientDtoRepository.findAll().collectList().block().size();

        // Delete the patientDto
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, patientDto.getPatientDtoId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<PatientDto> patientDtoList = patientDtoRepository.findAll().collectList().block();
        assertThat(patientDtoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
