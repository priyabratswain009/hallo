package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.SalesOrderInsuranceDetails;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.SalesOrderInsuranceDetailsRepository;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderInsuranceDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.SalesOrderInsuranceDetailsMapper;
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
 * Integration tests for the {@link SalesOrderInsuranceDetailsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class SalesOrderInsuranceDetailsResourceIT {

    private static final Long DEFAULT_SALES_ORDER_ID = 1L;
    private static final Long UPDATED_SALES_ORDER_ID = 2L;

    private static final Long DEFAULT_PATIENT_ID = 1L;
    private static final Long UPDATED_PATIENT_ID = 2L;

    private static final Long DEFAULT_PRIMARY_INSURER_ID = 1L;
    private static final Long UPDATED_PRIMARY_INSURER_ID = 2L;

    private static final String DEFAULT_PRIMARY_INSURER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_INSURER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_INSURER_POLICY_NO = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_INSURER_POLICY_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_INSURER_PATIENT_ID_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_INSURER_PATIENT_ID_NUMBER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PRIMARY_INSURER_EFFECTIVE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PRIMARY_INSURER_EFFECTIVE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_PRIMARY_INSURER_VERIFICATION_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_INSURER_VERIFICATION_STATUS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PRIMARY_INSURER_VERIFICATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PRIMARY_INSURER_VERIFICATION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_PRIMARY_INSURER_PAY_PERCENTAGE = 1L;
    private static final Long UPDATED_PRIMARY_INSURER_PAY_PERCENTAGE = 2L;

    private static final String DEFAULT_PRIMARY_BOX_10_D = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_BOX_10_D = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_BOX_19 = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_BOX_19 = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_BOX_24_IA = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_BOX_24_IA = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_BOX_24_JA = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_BOX_24_JA = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_BOX_24_JB = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_BOX_24_JB = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_INCLUDE_BOX_24_JBSTATUS = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_INCLUDE_BOX_24_JBSTATUS = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_INCLUDE_PAYER_SALES_ORDER_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_INCLUDE_PAYER_SALES_ORDER_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_WAIT_FOR_PREVIOUS_PAYER_BEFORE_BILLING_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_WAIT_FOR_PREVIOUS_PAYER_BEFORE_BILLING_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_PAY_PERCENTAGE_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_PAY_PERCENTAGE_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_SECONDARY_INSURER_ID = 1L;
    private static final Long UPDATED_SECONDARY_INSURER_ID = 2L;

    private static final String DEFAULT_SECONDARY_INSURER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY_INSURER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SECONDARY_INSURER_POLICY_NO = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY_INSURER_POLICY_NO = "BBBBBBBBBB";

    private static final String DEFAULT_SECONDARY_INSURER_PATIENT_ID_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY_INSURER_PATIENT_ID_NUMBER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_SECONDARY_INSURER_EFFECTIVE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SECONDARY_INSURER_EFFECTIVE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SECONDARY_INSURER_VERIFICATION_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY_INSURER_VERIFICATION_STATUS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_SECONDARY_INSURER_VERIFICATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SECONDARY_INSURER_VERIFICATION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_SECONDARY_INSURER_PAY_PERCENTAGE = 1L;
    private static final Long UPDATED_SECONDARY_INSURER_PAY_PERCENTAGE = 2L;

    private static final String DEFAULT_SECONDARY_BOX_10_D = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY_BOX_10_D = "BBBBBBBBBB";

    private static final String DEFAULT_SECONDARY_BOX_19 = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY_BOX_19 = "BBBBBBBBBB";

    private static final String DEFAULT_SECONDARY_BOX_24_IA = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY_BOX_24_IA = "BBBBBBBBBB";

    private static final String DEFAULT_SECONDARY_BOX_24_JA = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY_BOX_24_JA = "BBBBBBBBBB";

    private static final String DEFAULT_SECONDARY_BOX_24_JB = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY_BOX_24_JB = "BBBBBBBBBB";

    private static final String DEFAULT_SECONDARY_INCLUDE_BOX_24_JB_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY_INCLUDE_BOX_24_JB_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_SECONDARY_INCLUDE_PAYER_SALES_ORDER_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY_INCLUDE_PAYER_SALES_ORDER_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_SECONDARY_WAIT_PREVIOUS_PAYER_BEFR_BILLING_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY_WAIT_PREVIOUS_PAYER_BEFR_BILLING_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_SECONDARY_PAY_PERCENTAGE_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY_PAY_PERCENTAGE_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_TERTIARY_INSURER_ID = 1L;
    private static final Long UPDATED_TERTIARY_INSURER_ID = 2L;

    private static final String DEFAULT_TERTIARY_INSURER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TERTIARY_INSURER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TERTIARY_INSURER_POLICYNO = "AAAAAAAAAA";
    private static final String UPDATED_TERTIARY_INSURER_POLICYNO = "BBBBBBBBBB";

    private static final String DEFAULT_TERTIARY_INSURER_PATIENT_ID_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_TERTIARY_INSURER_PATIENT_ID_NUMBER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_TERTIARY_INSURER_EFFECTIVE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TERTIARY_INSURER_EFFECTIVE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_TERTIARY_INSURER_VERIFICATION_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_TERTIARY_INSURER_VERIFICATION_STATUS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_TERTIARY_INSURER_VERIFICATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TERTIARY_INSURER_VERIFICATION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_TERTIARY_INSURER_PAY_PERCENTAGE = 1L;
    private static final Long UPDATED_TERTIARY_INSURER_PAY_PERCENTAGE = 2L;

    private static final String DEFAULT_TERTIARY_BOX_10_D = "AAAAAAAAAA";
    private static final String UPDATED_TERTIARY_BOX_10_D = "BBBBBBBBBB";

    private static final String DEFAULT_TERTIARY_BOX_19 = "AAAAAAAAAA";
    private static final String UPDATED_TERTIARY_BOX_19 = "BBBBBBBBBB";

    private static final String DEFAULT_TERTIARY_BOX_24_IA = "AAAAAAAAAA";
    private static final String UPDATED_TERTIARY_BOX_24_IA = "BBBBBBBBBB";

    private static final String DEFAULT_TERTIARY_BOX_24_JA = "AAAAAAAAAA";
    private static final String UPDATED_TERTIARY_BOX_24_JA = "BBBBBBBBBB";

    private static final String DEFAULT_TERTIARY_BOX_24_JB = "AAAAAAAAAA";
    private static final String UPDATED_TERTIARY_BOX_24_JB = "BBBBBBBBBB";

    private static final String DEFAULT_TERTIARY_INCLUDE_BOX_24_JB_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_TERTIARY_INCLUDE_BOX_24_JB_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_TERTIARY_INCLUDE_PAYER_IN_SALES_ORDER_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_TERTIARY_INCLUDE_PAYER_IN_SALES_ORDER_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_TERTIARY_WAIT_PREVIOUS_PAYER_BEFORE_BILLING_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_TERTIARY_WAIT_PREVIOUS_PAYER_BEFORE_BILLING_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_TERTIARY_PAY_PERCENTAGE_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_TERTIARY_PAY_PERCENTAGE_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_INSURANCE_VERIFICATION_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_INSURANCE_VERIFICATION_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_COVERAGE_VERIFICATION_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_COVERAGE_VERIFICATION_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_EXCLUDE_FROM_ELIGIBILITY_CHECK_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_EXCLUDE_FROM_ELIGIBILITY_CHECK_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_PATIENT_PAY_PERCENTAGE = 1L;
    private static final Long UPDATED_PATIENT_PAY_PERCENTAGE = 2L;

    private static final String DEFAULT_PATIENT_INCLUDE_THIS_PAYOR_IN_SALES_ORDER_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_INCLUDE_THIS_PAYOR_IN_SALES_ORDER_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_WAIT_FOR_PREVIOUS_PAYER_BEFORE_BILLING_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_WAIT_FOR_PREVIOUS_PAYER_BEFORE_BILLING_STATUS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_WORKERS_COMP_DATE_OF_ONSET = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_WORKERS_COMP_DATE_OF_ONSET = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_WORKERS_COMP_INJURY_RELATED_EMPLOYMENT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_WORKERS_COMP_INJURY_RELATED_EMPLOYMENT_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_WORKERS_COMP_INJURY_RELATED_AUTO_ACCIDENT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_WORKERS_COMP_INJURY_RELATED_AUTO_ACCIDENT_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_WORKERS_COMP_AUTO_ACCIDENT_STATE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_WORKERS_COMP_AUTO_ACCIDENT_STATE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_WORKERS_COMP_INJURY_RELATED_TO_OTHER_ACCIDENT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_WORKERS_COMP_INJURY_RELATED_TO_OTHER_ACCIDENT_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_ECLAIMS_ATTACHMENT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_ECLAIMS_ATTACHMENT_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_ATTACHMENT_NUMBER = 1L;
    private static final Long UPDATED_ATTACHMENT_NUMBER = 2L;

    private static final String DEFAULT_TYPE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSACTION_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CLAIMS_NOTE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_CLAIMS_NOTE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CLAIMS_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_CLAIMS_NOTE = "BBBBBBBBBB";

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

    private static final String DEFAULT_SALES_ORDER_NO = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_NO = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_PRIMARY_INSURANCE_GROUP_ID = 1L;
    private static final Long UPDATED_PRIMARY_INSURANCE_GROUP_ID = 2L;

    private static final String DEFAULT_PRIMARY_INSURANCE_GROUP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_INSURANCE_GROUP_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_SECONDARY_INSURANCE_GROUP_ID = 1L;
    private static final Long UPDATED_SECONDARY_INSURANCE_GROUP_ID = 2L;

    private static final String DEFAULT_SECONDARY_INSURANCE_GROUP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY_INSURANCE_GROUP_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_TERTIARY_INSURANCE_GROUP_ID = 1L;
    private static final Long UPDATED_TERTIARY_INSURANCE_GROUP_ID = 2L;

    private static final String DEFAULT_TERTIARY_INSURANCE_GROUP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TERTIARY_INSURANCE_GROUP_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_PRIMARY_INSURANCE_PLAN_ID = 1L;
    private static final Long UPDATED_PRIMARY_INSURANCE_PLAN_ID = 2L;

    private static final String DEFAULT_PRIMARY_INSURANCE_PLAN_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_INSURANCE_PLAN_TYPE = "BBBBBBBBBB";

    private static final Long DEFAULT_SECONDARY_INSURANCE_PLAN_ID = 1L;
    private static final Long UPDATED_SECONDARY_INSURANCE_PLAN_ID = 2L;

    private static final String DEFAULT_SECONDARY_INSURANCE_PLAN_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY_INSURANCE_PLAN_TYPE = "BBBBBBBBBB";

    private static final Long DEFAULT_TERTIARY_INSURANCE_PLAN_ID = 1L;
    private static final Long UPDATED_TERTIARY_INSURANCE_PLAN_ID = 2L;

    private static final String DEFAULT_TERTIARY_INSURANCE_PLAN_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TERTIARY_INSURANCE_PLAN_TYPE = "BBBBBBBBBB";

    private static final UUID DEFAULT_SALES_ORDER_INSURANCE_DETAILS_UUID = UUID.randomUUID();
    private static final UUID UPDATED_SALES_ORDER_INSURANCE_DETAILS_UUID = UUID.randomUUID();

    private static final String DEFAULT_PRIMARY_INSURANCE_PAYER_ID = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_INSURANCE_PAYER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SECONDARY_INSURANCE_PAYER_ID = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY_INSURANCE_PAYER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TERTIARY_INSURANCE_PAYER_ID = "AAAAAAAAAA";
    private static final String UPDATED_TERTIARY_INSURANCE_PAYER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_INSURANCE_INDICATOR_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_INSURANCE_INDICATOR_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SECONDARY_INSURANCE_INDICATOR_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY_INSURANCE_INDICATOR_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_TERTIARY_INSURANCE_INDICATOR_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TERTIARY_INSURANCE_INDICATOR_CODE = "BBBBBBBBBB";

    private static final Long DEFAULT_PRICE_TABLE_ID = 1L;
    private static final Long UPDATED_PRICE_TABLE_ID = 2L;

    private static final String DEFAULT_PRICE_TABLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PRICE_TABLE_NAME = "BBBBBBBBBB";

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

    private static final LocalDate DEFAULT_PRIMARY_INSURER_POLICY_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PRIMARY_INSURER_POLICY_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_PRIMARY_INSURER_CONTACT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_INSURER_CONTACT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_CLAIM_PROGRAM = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_CLAIM_PROGRAM = "BBBBBBBBBB";

    private static final String DEFAULT_SECONDARY_CLAIM_PROGRAM = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY_CLAIM_PROGRAM = "BBBBBBBBBB";

    private static final String DEFAULT_TERTIARY_CLAIM_PROGRAM = "AAAAAAAAAA";
    private static final String UPDATED_TERTIARY_CLAIM_PROGRAM = "BBBBBBBBBB";

    private static final String DEFAULT_WORKERS_COMP_INSURED_EMPLOYER = "AAAAAAAAAA";
    private static final String UPDATED_WORKERS_COMP_INSURED_EMPLOYER = "BBBBBBBBBB";

    private static final String DEFAULT_WORKERS_COMP_PAYER_ID_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_WORKERS_COMP_PAYER_ID_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_WORKERS_COMP_PLAN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_WORKERS_COMP_PLAN_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_WORKERS_COMP_ADDITIONAL_DTLS = "AAAAAAAAAA";
    private static final String UPDATED_WORKERS_COMP_ADDITIONAL_DTLS = "BBBBBBBBBB";

    private static final String DEFAULT_WORKERS_COMP_CLAIM_FILLING_CODE = "AAAAAAAAAA";
    private static final String UPDATED_WORKERS_COMP_CLAIM_FILLING_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_WORKERS_COMP_TPL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_WORKERS_COMP_TPL_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_WORKERS_COMP_TPL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_WORKERS_COMP_TPL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_WORKERS_COMP_PROPERTY_CASUALTY_AGENCY_CLAIM_NO = "AAAAAAAAAA";
    private static final String UPDATED_WORKERS_COMP_PROPERTY_CASUALTY_AGENCY_CLAIM_NO = "BBBBBBBBBB";

    private static final String DEFAULT_WORKERS_COMP_CARRIER_ID = "AAAAAAAAAA";
    private static final String UPDATED_WORKERS_COMP_CARRIER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_WORKERS_COMP_EMPLOYER_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_WORKERS_COMP_EMPLOYER_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_WORKERS_COMP_EMPLOYER_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_WORKERS_COMP_EMPLOYER_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_WORKERS_COMP_EMPLOYER_CITY = "AAAAAAAAAA";
    private static final String UPDATED_WORKERS_COMP_EMPLOYER_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_WORKERS_COMP_EMPLOYER_STATE = "AAAAAAAAAA";
    private static final String UPDATED_WORKERS_COMP_EMPLOYER_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_WORKERS_COMP_EMPLOYER_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_WORKERS_COMP_EMPLOYER_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_WORKERS_COMP_EMPLOYER_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_WORKERS_COMP_EMPLOYER_ZIP = "BBBBBBBBBB";

    private static final String DEFAULT_WORKERS_COMP_EMPLOYER_CONTACT_NO_1 = "AAAAAAAAAA";
    private static final String UPDATED_WORKERS_COMP_EMPLOYER_CONTACT_NO_1 = "BBBBBBBBBB";

    private static final String DEFAULT_WORKERS_COMP_EMPLOYER_CONTACT_NO_2 = "AAAAAAAAAA";
    private static final String UPDATED_WORKERS_COMP_EMPLOYER_CONTACT_NO_2 = "BBBBBBBBBB";

    private static final String DEFAULT_WORKERS_COMP_EMPLOYER_FAX = "AAAAAAAAAA";
    private static final String UPDATED_WORKERS_COMP_EMPLOYER_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_WORKERS_COMP_EMPLOYER_EFAX = "AAAAAAAAAA";
    private static final String UPDATED_WORKERS_COMP_EMPLOYER_EFAX = "BBBBBBBBBB";

    private static final String DEFAULT_WORKERS_COMP_EMPLOYER_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_WORKERS_COMP_EMPLOYER_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_WORKERS_COMP_RELATIONSHIP = "AAAAAAAAAA";
    private static final String UPDATED_WORKERS_COMP_RELATIONSHIP = "BBBBBBBBBB";

    private static final String DEFAULT_WORKERS_COMP_MODE_OF_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_WORKERS_COMP_MODE_OF_CONTACT = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/sales-order-insurance-details";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{salesOrderInsuranceDetailsId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SalesOrderInsuranceDetailsRepository salesOrderInsuranceDetailsRepository;

    @Autowired
    private SalesOrderInsuranceDetailsMapper salesOrderInsuranceDetailsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private SalesOrderInsuranceDetails salesOrderInsuranceDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesOrderInsuranceDetails createEntity(EntityManager em) {
        SalesOrderInsuranceDetails salesOrderInsuranceDetails = new SalesOrderInsuranceDetails()
            .salesOrderId(DEFAULT_SALES_ORDER_ID)
            .patientId(DEFAULT_PATIENT_ID)
            .primaryInsurerId(DEFAULT_PRIMARY_INSURER_ID)
            .primaryInsurerName(DEFAULT_PRIMARY_INSURER_NAME)
            .primaryInsurerPolicyNo(DEFAULT_PRIMARY_INSURER_POLICY_NO)
            .primaryInsurerPatientIdNumber(DEFAULT_PRIMARY_INSURER_PATIENT_ID_NUMBER)
            .primaryInsurerEffectiveDate(DEFAULT_PRIMARY_INSURER_EFFECTIVE_DATE)
            .primaryInsurerVerificationStatus(DEFAULT_PRIMARY_INSURER_VERIFICATION_STATUS)
            .primaryInsurerVerificationDate(DEFAULT_PRIMARY_INSURER_VERIFICATION_DATE)
            .primaryInsurerPayPercentage(DEFAULT_PRIMARY_INSURER_PAY_PERCENTAGE)
            .primaryBox10D(DEFAULT_PRIMARY_BOX_10_D)
            .primaryBox19(DEFAULT_PRIMARY_BOX_19)
            .primaryBox24Ia(DEFAULT_PRIMARY_BOX_24_IA)
            .primaryBox24Ja(DEFAULT_PRIMARY_BOX_24_JA)
            .primaryBox24Jb(DEFAULT_PRIMARY_BOX_24_JB)
            .primaryIncludeBox24Jbstatus(DEFAULT_PRIMARY_INCLUDE_BOX_24_JBSTATUS)
            .primaryIncludePayerSalesOrderStatus(DEFAULT_PRIMARY_INCLUDE_PAYER_SALES_ORDER_STATUS)
            .primaryWaitForPreviousPayerBeforeBillingStatus(DEFAULT_PRIMARY_WAIT_FOR_PREVIOUS_PAYER_BEFORE_BILLING_STATUS)
            .primaryPayPercentageStatus(DEFAULT_PRIMARY_PAY_PERCENTAGE_STATUS)
            .secondaryInsurerId(DEFAULT_SECONDARY_INSURER_ID)
            .secondaryInsurerName(DEFAULT_SECONDARY_INSURER_NAME)
            .secondaryInsurerPolicyNo(DEFAULT_SECONDARY_INSURER_POLICY_NO)
            .secondaryInsurerPatientIdNumber(DEFAULT_SECONDARY_INSURER_PATIENT_ID_NUMBER)
            .secondaryInsurerEffectiveDate(DEFAULT_SECONDARY_INSURER_EFFECTIVE_DATE)
            .secondaryInsurerVerificationStatus(DEFAULT_SECONDARY_INSURER_VERIFICATION_STATUS)
            .secondaryInsurerVerificationDate(DEFAULT_SECONDARY_INSURER_VERIFICATION_DATE)
            .secondaryInsurerPayPercentage(DEFAULT_SECONDARY_INSURER_PAY_PERCENTAGE)
            .secondaryBox10D(DEFAULT_SECONDARY_BOX_10_D)
            .secondaryBox19(DEFAULT_SECONDARY_BOX_19)
            .secondaryBox24Ia(DEFAULT_SECONDARY_BOX_24_IA)
            .secondaryBox24Ja(DEFAULT_SECONDARY_BOX_24_JA)
            .secondaryBox24Jb(DEFAULT_SECONDARY_BOX_24_JB)
            .secondaryIncludeBox24JbStatus(DEFAULT_SECONDARY_INCLUDE_BOX_24_JB_STATUS)
            .secondaryIncludePayerSalesOrderStatus(DEFAULT_SECONDARY_INCLUDE_PAYER_SALES_ORDER_STATUS)
            .secondaryWaitPreviousPayerBefrBillingStatus(DEFAULT_SECONDARY_WAIT_PREVIOUS_PAYER_BEFR_BILLING_STATUS)
            .secondaryPayPercentageStatus(DEFAULT_SECONDARY_PAY_PERCENTAGE_STATUS)
            .tertiaryInsurerId(DEFAULT_TERTIARY_INSURER_ID)
            .tertiaryInsurerName(DEFAULT_TERTIARY_INSURER_NAME)
            .tertiaryInsurerPolicyno(DEFAULT_TERTIARY_INSURER_POLICYNO)
            .tertiaryInsurerPatientIdNumber(DEFAULT_TERTIARY_INSURER_PATIENT_ID_NUMBER)
            .tertiaryInsurerEffectiveDate(DEFAULT_TERTIARY_INSURER_EFFECTIVE_DATE)
            .tertiaryInsurerVerificationStatus(DEFAULT_TERTIARY_INSURER_VERIFICATION_STATUS)
            .tertiaryInsurerVerificationDate(DEFAULT_TERTIARY_INSURER_VERIFICATION_DATE)
            .tertiaryInsurerPayPercentage(DEFAULT_TERTIARY_INSURER_PAY_PERCENTAGE)
            .tertiaryBox10D(DEFAULT_TERTIARY_BOX_10_D)
            .tertiaryBox19(DEFAULT_TERTIARY_BOX_19)
            .tertiaryBox24Ia(DEFAULT_TERTIARY_BOX_24_IA)
            .tertiaryBox24Ja(DEFAULT_TERTIARY_BOX_24_JA)
            .tertiaryBox24Jb(DEFAULT_TERTIARY_BOX_24_JB)
            .tertiaryIncludeBox24JbStatus(DEFAULT_TERTIARY_INCLUDE_BOX_24_JB_STATUS)
            .tertiaryIncludePayerInSalesOrderStatus(DEFAULT_TERTIARY_INCLUDE_PAYER_IN_SALES_ORDER_STATUS)
            .tertiaryWaitPreviousPayerBeforeBillingStatus(DEFAULT_TERTIARY_WAIT_PREVIOUS_PAYER_BEFORE_BILLING_STATUS)
            .tertiaryPayPercentageStatus(DEFAULT_TERTIARY_PAY_PERCENTAGE_STATUS)
            .insuranceVerificationStatus(DEFAULT_INSURANCE_VERIFICATION_STATUS)
            .coverageVerificationStatus(DEFAULT_COVERAGE_VERIFICATION_STATUS)
            .excludeFromEligibilityCheckStatus(DEFAULT_EXCLUDE_FROM_ELIGIBILITY_CHECK_STATUS)
            .patientPayPercentage(DEFAULT_PATIENT_PAY_PERCENTAGE)
            .patientIncludeThisPayorInSalesOrderStatus(DEFAULT_PATIENT_INCLUDE_THIS_PAYOR_IN_SALES_ORDER_STATUS)
            .patientWaitForPreviousPayerBeforeBillingStatus(DEFAULT_PATIENT_WAIT_FOR_PREVIOUS_PAYER_BEFORE_BILLING_STATUS)
            .workersCompDateOfOnset(DEFAULT_WORKERS_COMP_DATE_OF_ONSET)
            .workersCompInjuryRelatedEmploymentStatus(DEFAULT_WORKERS_COMP_INJURY_RELATED_EMPLOYMENT_STATUS)
            .workersCompInjuryRelatedAutoAccidentStatus(DEFAULT_WORKERS_COMP_INJURY_RELATED_AUTO_ACCIDENT_STATUS)
            .workersCompAutoAccidentStateCode(DEFAULT_WORKERS_COMP_AUTO_ACCIDENT_STATE_CODE)
            .workersCompInjuryRelatedToOtherAccidentStatus(DEFAULT_WORKERS_COMP_INJURY_RELATED_TO_OTHER_ACCIDENT_STATUS)
            .eclaimsAttachmentStatus(DEFAULT_ECLAIMS_ATTACHMENT_STATUS)
            .attachmentNumber(DEFAULT_ATTACHMENT_NUMBER)
            .typeCode(DEFAULT_TYPE_CODE)
            .transactionCode(DEFAULT_TRANSACTION_CODE)
            .claimsNoteType(DEFAULT_CLAIMS_NOTE_TYPE)
            .claimsNote(DEFAULT_CLAIMS_NOTE)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .status(DEFAULT_STATUS)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .salesOrderNo(DEFAULT_SALES_ORDER_NO)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .primaryInsuranceGroupId(DEFAULT_PRIMARY_INSURANCE_GROUP_ID)
            .primaryInsuranceGroupName(DEFAULT_PRIMARY_INSURANCE_GROUP_NAME)
            .secondaryInsuranceGroupId(DEFAULT_SECONDARY_INSURANCE_GROUP_ID)
            .secondaryInsuranceGroupName(DEFAULT_SECONDARY_INSURANCE_GROUP_NAME)
            .tertiaryInsuranceGroupId(DEFAULT_TERTIARY_INSURANCE_GROUP_ID)
            .tertiaryInsuranceGroupName(DEFAULT_TERTIARY_INSURANCE_GROUP_NAME)
            .primaryInsurancePlanId(DEFAULT_PRIMARY_INSURANCE_PLAN_ID)
            .primaryInsurancePlanType(DEFAULT_PRIMARY_INSURANCE_PLAN_TYPE)
            .secondaryInsurancePlanId(DEFAULT_SECONDARY_INSURANCE_PLAN_ID)
            .secondaryInsurancePlanType(DEFAULT_SECONDARY_INSURANCE_PLAN_TYPE)
            .tertiaryInsurancePlanId(DEFAULT_TERTIARY_INSURANCE_PLAN_ID)
            .tertiaryInsurancePlanType(DEFAULT_TERTIARY_INSURANCE_PLAN_TYPE)
            .salesOrderInsuranceDetailsUuid(DEFAULT_SALES_ORDER_INSURANCE_DETAILS_UUID)
            .primaryInsurancePayerId(DEFAULT_PRIMARY_INSURANCE_PAYER_ID)
            .secondaryInsurancePayerId(DEFAULT_SECONDARY_INSURANCE_PAYER_ID)
            .tertiaryInsurancePayerId(DEFAULT_TERTIARY_INSURANCE_PAYER_ID)
            .primaryInsuranceIndicatorCode(DEFAULT_PRIMARY_INSURANCE_INDICATOR_CODE)
            .secondaryInsuranceIndicatorCode(DEFAULT_SECONDARY_INSURANCE_INDICATOR_CODE)
            .tertiaryInsuranceIndicatorCode(DEFAULT_TERTIARY_INSURANCE_INDICATOR_CODE)
            .priceTableId(DEFAULT_PRICE_TABLE_ID)
            .priceTableName(DEFAULT_PRICE_TABLE_NAME)
            .primaryInsurerAddressLine1(DEFAULT_PRIMARY_INSURER_ADDRESS_LINE_1)
            .primaryInsurerAddressLine2(DEFAULT_PRIMARY_INSURER_ADDRESS_LINE_2)
            .primaryInsurerCity(DEFAULT_PRIMARY_INSURER_CITY)
            .primaryInsurerState(DEFAULT_PRIMARY_INSURER_STATE)
            .primaryInsurerZip(DEFAULT_PRIMARY_INSURER_ZIP)
            .primaryInsurerContact1(DEFAULT_PRIMARY_INSURER_CONTACT_1)
            .primaryInsurerFax(DEFAULT_PRIMARY_INSURER_FAX)
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
            .primaryInsurerPolicyEndDate(DEFAULT_PRIMARY_INSURER_POLICY_END_DATE)
            .primaryInsurerContactName(DEFAULT_PRIMARY_INSURER_CONTACT_NAME)
            .primaryClaimProgram(DEFAULT_PRIMARY_CLAIM_PROGRAM)
            .secondaryClaimProgram(DEFAULT_SECONDARY_CLAIM_PROGRAM)
            .tertiaryClaimProgram(DEFAULT_TERTIARY_CLAIM_PROGRAM)
            .workersCompInsuredEmployer(DEFAULT_WORKERS_COMP_INSURED_EMPLOYER)
            .workersCompPayerIdNumber(DEFAULT_WORKERS_COMP_PAYER_ID_NUMBER)
            .workersCompPlanName(DEFAULT_WORKERS_COMP_PLAN_NAME)
            .workersCompAdditionalDtls(DEFAULT_WORKERS_COMP_ADDITIONAL_DTLS)
            .workersCompClaimFillingCode(DEFAULT_WORKERS_COMP_CLAIM_FILLING_CODE)
            .workersCompTplCode(DEFAULT_WORKERS_COMP_TPL_CODE)
            .workersCompTplName(DEFAULT_WORKERS_COMP_TPL_NAME)
            .workersCompPropertyCasualtyAgencyClaimNo(DEFAULT_WORKERS_COMP_PROPERTY_CASUALTY_AGENCY_CLAIM_NO)
            .workersCompCarrierId(DEFAULT_WORKERS_COMP_CARRIER_ID)
            .workersCompEmployerAddressLine1(DEFAULT_WORKERS_COMP_EMPLOYER_ADDRESS_LINE_1)
            .workersCompEmployerAddressLine2(DEFAULT_WORKERS_COMP_EMPLOYER_ADDRESS_LINE_2)
            .workersCompEmployerCity(DEFAULT_WORKERS_COMP_EMPLOYER_CITY)
            .workersCompEmployerState(DEFAULT_WORKERS_COMP_EMPLOYER_STATE)
            .workersCompEmployerCountry(DEFAULT_WORKERS_COMP_EMPLOYER_COUNTRY)
            .workersCompEmployerZip(DEFAULT_WORKERS_COMP_EMPLOYER_ZIP)
            .workersCompEmployerContactNo1(DEFAULT_WORKERS_COMP_EMPLOYER_CONTACT_NO_1)
            .workersCompEmployerContactNo2(DEFAULT_WORKERS_COMP_EMPLOYER_CONTACT_NO_2)
            .workersCompEmployerFax(DEFAULT_WORKERS_COMP_EMPLOYER_FAX)
            .workersCompEmployerEfax(DEFAULT_WORKERS_COMP_EMPLOYER_EFAX)
            .workersCompEmployerEmail(DEFAULT_WORKERS_COMP_EMPLOYER_EMAIL)
            .workersCompRelationship(DEFAULT_WORKERS_COMP_RELATIONSHIP)
            .workersCompModeOfContact(DEFAULT_WORKERS_COMP_MODE_OF_CONTACT);
        return salesOrderInsuranceDetails;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesOrderInsuranceDetails createUpdatedEntity(EntityManager em) {
        SalesOrderInsuranceDetails salesOrderInsuranceDetails = new SalesOrderInsuranceDetails()
            .salesOrderId(UPDATED_SALES_ORDER_ID)
            .patientId(UPDATED_PATIENT_ID)
            .primaryInsurerId(UPDATED_PRIMARY_INSURER_ID)
            .primaryInsurerName(UPDATED_PRIMARY_INSURER_NAME)
            .primaryInsurerPolicyNo(UPDATED_PRIMARY_INSURER_POLICY_NO)
            .primaryInsurerPatientIdNumber(UPDATED_PRIMARY_INSURER_PATIENT_ID_NUMBER)
            .primaryInsurerEffectiveDate(UPDATED_PRIMARY_INSURER_EFFECTIVE_DATE)
            .primaryInsurerVerificationStatus(UPDATED_PRIMARY_INSURER_VERIFICATION_STATUS)
            .primaryInsurerVerificationDate(UPDATED_PRIMARY_INSURER_VERIFICATION_DATE)
            .primaryInsurerPayPercentage(UPDATED_PRIMARY_INSURER_PAY_PERCENTAGE)
            .primaryBox10D(UPDATED_PRIMARY_BOX_10_D)
            .primaryBox19(UPDATED_PRIMARY_BOX_19)
            .primaryBox24Ia(UPDATED_PRIMARY_BOX_24_IA)
            .primaryBox24Ja(UPDATED_PRIMARY_BOX_24_JA)
            .primaryBox24Jb(UPDATED_PRIMARY_BOX_24_JB)
            .primaryIncludeBox24Jbstatus(UPDATED_PRIMARY_INCLUDE_BOX_24_JBSTATUS)
            .primaryIncludePayerSalesOrderStatus(UPDATED_PRIMARY_INCLUDE_PAYER_SALES_ORDER_STATUS)
            .primaryWaitForPreviousPayerBeforeBillingStatus(UPDATED_PRIMARY_WAIT_FOR_PREVIOUS_PAYER_BEFORE_BILLING_STATUS)
            .primaryPayPercentageStatus(UPDATED_PRIMARY_PAY_PERCENTAGE_STATUS)
            .secondaryInsurerId(UPDATED_SECONDARY_INSURER_ID)
            .secondaryInsurerName(UPDATED_SECONDARY_INSURER_NAME)
            .secondaryInsurerPolicyNo(UPDATED_SECONDARY_INSURER_POLICY_NO)
            .secondaryInsurerPatientIdNumber(UPDATED_SECONDARY_INSURER_PATIENT_ID_NUMBER)
            .secondaryInsurerEffectiveDate(UPDATED_SECONDARY_INSURER_EFFECTIVE_DATE)
            .secondaryInsurerVerificationStatus(UPDATED_SECONDARY_INSURER_VERIFICATION_STATUS)
            .secondaryInsurerVerificationDate(UPDATED_SECONDARY_INSURER_VERIFICATION_DATE)
            .secondaryInsurerPayPercentage(UPDATED_SECONDARY_INSURER_PAY_PERCENTAGE)
            .secondaryBox10D(UPDATED_SECONDARY_BOX_10_D)
            .secondaryBox19(UPDATED_SECONDARY_BOX_19)
            .secondaryBox24Ia(UPDATED_SECONDARY_BOX_24_IA)
            .secondaryBox24Ja(UPDATED_SECONDARY_BOX_24_JA)
            .secondaryBox24Jb(UPDATED_SECONDARY_BOX_24_JB)
            .secondaryIncludeBox24JbStatus(UPDATED_SECONDARY_INCLUDE_BOX_24_JB_STATUS)
            .secondaryIncludePayerSalesOrderStatus(UPDATED_SECONDARY_INCLUDE_PAYER_SALES_ORDER_STATUS)
            .secondaryWaitPreviousPayerBefrBillingStatus(UPDATED_SECONDARY_WAIT_PREVIOUS_PAYER_BEFR_BILLING_STATUS)
            .secondaryPayPercentageStatus(UPDATED_SECONDARY_PAY_PERCENTAGE_STATUS)
            .tertiaryInsurerId(UPDATED_TERTIARY_INSURER_ID)
            .tertiaryInsurerName(UPDATED_TERTIARY_INSURER_NAME)
            .tertiaryInsurerPolicyno(UPDATED_TERTIARY_INSURER_POLICYNO)
            .tertiaryInsurerPatientIdNumber(UPDATED_TERTIARY_INSURER_PATIENT_ID_NUMBER)
            .tertiaryInsurerEffectiveDate(UPDATED_TERTIARY_INSURER_EFFECTIVE_DATE)
            .tertiaryInsurerVerificationStatus(UPDATED_TERTIARY_INSURER_VERIFICATION_STATUS)
            .tertiaryInsurerVerificationDate(UPDATED_TERTIARY_INSURER_VERIFICATION_DATE)
            .tertiaryInsurerPayPercentage(UPDATED_TERTIARY_INSURER_PAY_PERCENTAGE)
            .tertiaryBox10D(UPDATED_TERTIARY_BOX_10_D)
            .tertiaryBox19(UPDATED_TERTIARY_BOX_19)
            .tertiaryBox24Ia(UPDATED_TERTIARY_BOX_24_IA)
            .tertiaryBox24Ja(UPDATED_TERTIARY_BOX_24_JA)
            .tertiaryBox24Jb(UPDATED_TERTIARY_BOX_24_JB)
            .tertiaryIncludeBox24JbStatus(UPDATED_TERTIARY_INCLUDE_BOX_24_JB_STATUS)
            .tertiaryIncludePayerInSalesOrderStatus(UPDATED_TERTIARY_INCLUDE_PAYER_IN_SALES_ORDER_STATUS)
            .tertiaryWaitPreviousPayerBeforeBillingStatus(UPDATED_TERTIARY_WAIT_PREVIOUS_PAYER_BEFORE_BILLING_STATUS)
            .tertiaryPayPercentageStatus(UPDATED_TERTIARY_PAY_PERCENTAGE_STATUS)
            .insuranceVerificationStatus(UPDATED_INSURANCE_VERIFICATION_STATUS)
            .coverageVerificationStatus(UPDATED_COVERAGE_VERIFICATION_STATUS)
            .excludeFromEligibilityCheckStatus(UPDATED_EXCLUDE_FROM_ELIGIBILITY_CHECK_STATUS)
            .patientPayPercentage(UPDATED_PATIENT_PAY_PERCENTAGE)
            .patientIncludeThisPayorInSalesOrderStatus(UPDATED_PATIENT_INCLUDE_THIS_PAYOR_IN_SALES_ORDER_STATUS)
            .patientWaitForPreviousPayerBeforeBillingStatus(UPDATED_PATIENT_WAIT_FOR_PREVIOUS_PAYER_BEFORE_BILLING_STATUS)
            .workersCompDateOfOnset(UPDATED_WORKERS_COMP_DATE_OF_ONSET)
            .workersCompInjuryRelatedEmploymentStatus(UPDATED_WORKERS_COMP_INJURY_RELATED_EMPLOYMENT_STATUS)
            .workersCompInjuryRelatedAutoAccidentStatus(UPDATED_WORKERS_COMP_INJURY_RELATED_AUTO_ACCIDENT_STATUS)
            .workersCompAutoAccidentStateCode(UPDATED_WORKERS_COMP_AUTO_ACCIDENT_STATE_CODE)
            .workersCompInjuryRelatedToOtherAccidentStatus(UPDATED_WORKERS_COMP_INJURY_RELATED_TO_OTHER_ACCIDENT_STATUS)
            .eclaimsAttachmentStatus(UPDATED_ECLAIMS_ATTACHMENT_STATUS)
            .attachmentNumber(UPDATED_ATTACHMENT_NUMBER)
            .typeCode(UPDATED_TYPE_CODE)
            .transactionCode(UPDATED_TRANSACTION_CODE)
            .claimsNoteType(UPDATED_CLAIMS_NOTE_TYPE)
            .claimsNote(UPDATED_CLAIMS_NOTE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .status(UPDATED_STATUS)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .salesOrderNo(UPDATED_SALES_ORDER_NO)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .primaryInsuranceGroupId(UPDATED_PRIMARY_INSURANCE_GROUP_ID)
            .primaryInsuranceGroupName(UPDATED_PRIMARY_INSURANCE_GROUP_NAME)
            .secondaryInsuranceGroupId(UPDATED_SECONDARY_INSURANCE_GROUP_ID)
            .secondaryInsuranceGroupName(UPDATED_SECONDARY_INSURANCE_GROUP_NAME)
            .tertiaryInsuranceGroupId(UPDATED_TERTIARY_INSURANCE_GROUP_ID)
            .tertiaryInsuranceGroupName(UPDATED_TERTIARY_INSURANCE_GROUP_NAME)
            .primaryInsurancePlanId(UPDATED_PRIMARY_INSURANCE_PLAN_ID)
            .primaryInsurancePlanType(UPDATED_PRIMARY_INSURANCE_PLAN_TYPE)
            .secondaryInsurancePlanId(UPDATED_SECONDARY_INSURANCE_PLAN_ID)
            .secondaryInsurancePlanType(UPDATED_SECONDARY_INSURANCE_PLAN_TYPE)
            .tertiaryInsurancePlanId(UPDATED_TERTIARY_INSURANCE_PLAN_ID)
            .tertiaryInsurancePlanType(UPDATED_TERTIARY_INSURANCE_PLAN_TYPE)
            .salesOrderInsuranceDetailsUuid(UPDATED_SALES_ORDER_INSURANCE_DETAILS_UUID)
            .primaryInsurancePayerId(UPDATED_PRIMARY_INSURANCE_PAYER_ID)
            .secondaryInsurancePayerId(UPDATED_SECONDARY_INSURANCE_PAYER_ID)
            .tertiaryInsurancePayerId(UPDATED_TERTIARY_INSURANCE_PAYER_ID)
            .primaryInsuranceIndicatorCode(UPDATED_PRIMARY_INSURANCE_INDICATOR_CODE)
            .secondaryInsuranceIndicatorCode(UPDATED_SECONDARY_INSURANCE_INDICATOR_CODE)
            .tertiaryInsuranceIndicatorCode(UPDATED_TERTIARY_INSURANCE_INDICATOR_CODE)
            .priceTableId(UPDATED_PRICE_TABLE_ID)
            .priceTableName(UPDATED_PRICE_TABLE_NAME)
            .primaryInsurerAddressLine1(UPDATED_PRIMARY_INSURER_ADDRESS_LINE_1)
            .primaryInsurerAddressLine2(UPDATED_PRIMARY_INSURER_ADDRESS_LINE_2)
            .primaryInsurerCity(UPDATED_PRIMARY_INSURER_CITY)
            .primaryInsurerState(UPDATED_PRIMARY_INSURER_STATE)
            .primaryInsurerZip(UPDATED_PRIMARY_INSURER_ZIP)
            .primaryInsurerContact1(UPDATED_PRIMARY_INSURER_CONTACT_1)
            .primaryInsurerFax(UPDATED_PRIMARY_INSURER_FAX)
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
            .primaryInsurerPolicyEndDate(UPDATED_PRIMARY_INSURER_POLICY_END_DATE)
            .primaryInsurerContactName(UPDATED_PRIMARY_INSURER_CONTACT_NAME)
            .primaryClaimProgram(UPDATED_PRIMARY_CLAIM_PROGRAM)
            .secondaryClaimProgram(UPDATED_SECONDARY_CLAIM_PROGRAM)
            .tertiaryClaimProgram(UPDATED_TERTIARY_CLAIM_PROGRAM)
            .workersCompInsuredEmployer(UPDATED_WORKERS_COMP_INSURED_EMPLOYER)
            .workersCompPayerIdNumber(UPDATED_WORKERS_COMP_PAYER_ID_NUMBER)
            .workersCompPlanName(UPDATED_WORKERS_COMP_PLAN_NAME)
            .workersCompAdditionalDtls(UPDATED_WORKERS_COMP_ADDITIONAL_DTLS)
            .workersCompClaimFillingCode(UPDATED_WORKERS_COMP_CLAIM_FILLING_CODE)
            .workersCompTplCode(UPDATED_WORKERS_COMP_TPL_CODE)
            .workersCompTplName(UPDATED_WORKERS_COMP_TPL_NAME)
            .workersCompPropertyCasualtyAgencyClaimNo(UPDATED_WORKERS_COMP_PROPERTY_CASUALTY_AGENCY_CLAIM_NO)
            .workersCompCarrierId(UPDATED_WORKERS_COMP_CARRIER_ID)
            .workersCompEmployerAddressLine1(UPDATED_WORKERS_COMP_EMPLOYER_ADDRESS_LINE_1)
            .workersCompEmployerAddressLine2(UPDATED_WORKERS_COMP_EMPLOYER_ADDRESS_LINE_2)
            .workersCompEmployerCity(UPDATED_WORKERS_COMP_EMPLOYER_CITY)
            .workersCompEmployerState(UPDATED_WORKERS_COMP_EMPLOYER_STATE)
            .workersCompEmployerCountry(UPDATED_WORKERS_COMP_EMPLOYER_COUNTRY)
            .workersCompEmployerZip(UPDATED_WORKERS_COMP_EMPLOYER_ZIP)
            .workersCompEmployerContactNo1(UPDATED_WORKERS_COMP_EMPLOYER_CONTACT_NO_1)
            .workersCompEmployerContactNo2(UPDATED_WORKERS_COMP_EMPLOYER_CONTACT_NO_2)
            .workersCompEmployerFax(UPDATED_WORKERS_COMP_EMPLOYER_FAX)
            .workersCompEmployerEfax(UPDATED_WORKERS_COMP_EMPLOYER_EFAX)
            .workersCompEmployerEmail(UPDATED_WORKERS_COMP_EMPLOYER_EMAIL)
            .workersCompRelationship(UPDATED_WORKERS_COMP_RELATIONSHIP)
            .workersCompModeOfContact(UPDATED_WORKERS_COMP_MODE_OF_CONTACT);
        return salesOrderInsuranceDetails;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(SalesOrderInsuranceDetails.class).block();
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
        salesOrderInsuranceDetails = createEntity(em);
    }

    @Test
    void createSalesOrderInsuranceDetails() throws Exception {
        int databaseSizeBeforeCreate = salesOrderInsuranceDetailsRepository.findAll().collectList().block().size();
        // Create the SalesOrderInsuranceDetails
        SalesOrderInsuranceDetailsDTO salesOrderInsuranceDetailsDTO = salesOrderInsuranceDetailsMapper.toDto(salesOrderInsuranceDetails);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderInsuranceDetailsDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the SalesOrderInsuranceDetails in the database
        List<SalesOrderInsuranceDetails> salesOrderInsuranceDetailsList = salesOrderInsuranceDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderInsuranceDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        SalesOrderInsuranceDetails testSalesOrderInsuranceDetails = salesOrderInsuranceDetailsList.get(
            salesOrderInsuranceDetailsList.size() - 1
        );
        assertThat(testSalesOrderInsuranceDetails.getSalesOrderId()).isEqualTo(DEFAULT_SALES_ORDER_ID);
        assertThat(testSalesOrderInsuranceDetails.getPatientId()).isEqualTo(DEFAULT_PATIENT_ID);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerId()).isEqualTo(DEFAULT_PRIMARY_INSURER_ID);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerName()).isEqualTo(DEFAULT_PRIMARY_INSURER_NAME);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerPolicyNo()).isEqualTo(DEFAULT_PRIMARY_INSURER_POLICY_NO);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerPatientIdNumber()).isEqualTo(DEFAULT_PRIMARY_INSURER_PATIENT_ID_NUMBER);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerEffectiveDate()).isEqualTo(DEFAULT_PRIMARY_INSURER_EFFECTIVE_DATE);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerVerificationStatus())
            .isEqualTo(DEFAULT_PRIMARY_INSURER_VERIFICATION_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerVerificationDate()).isEqualTo(DEFAULT_PRIMARY_INSURER_VERIFICATION_DATE);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerPayPercentage()).isEqualTo(DEFAULT_PRIMARY_INSURER_PAY_PERCENTAGE);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryBox10D()).isEqualTo(DEFAULT_PRIMARY_BOX_10_D);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryBox19()).isEqualTo(DEFAULT_PRIMARY_BOX_19);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryBox24Ia()).isEqualTo(DEFAULT_PRIMARY_BOX_24_IA);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryBox24Ja()).isEqualTo(DEFAULT_PRIMARY_BOX_24_JA);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryBox24Jb()).isEqualTo(DEFAULT_PRIMARY_BOX_24_JB);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryIncludeBox24Jbstatus()).isEqualTo(DEFAULT_PRIMARY_INCLUDE_BOX_24_JBSTATUS);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryIncludePayerSalesOrderStatus())
            .isEqualTo(DEFAULT_PRIMARY_INCLUDE_PAYER_SALES_ORDER_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryWaitForPreviousPayerBeforeBillingStatus())
            .isEqualTo(DEFAULT_PRIMARY_WAIT_FOR_PREVIOUS_PAYER_BEFORE_BILLING_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryPayPercentageStatus()).isEqualTo(DEFAULT_PRIMARY_PAY_PERCENTAGE_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerId()).isEqualTo(DEFAULT_SECONDARY_INSURER_ID);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerName()).isEqualTo(DEFAULT_SECONDARY_INSURER_NAME);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerPolicyNo()).isEqualTo(DEFAULT_SECONDARY_INSURER_POLICY_NO);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerPatientIdNumber())
            .isEqualTo(DEFAULT_SECONDARY_INSURER_PATIENT_ID_NUMBER);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerEffectiveDate()).isEqualTo(DEFAULT_SECONDARY_INSURER_EFFECTIVE_DATE);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerVerificationStatus())
            .isEqualTo(DEFAULT_SECONDARY_INSURER_VERIFICATION_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerVerificationDate())
            .isEqualTo(DEFAULT_SECONDARY_INSURER_VERIFICATION_DATE);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerPayPercentage()).isEqualTo(DEFAULT_SECONDARY_INSURER_PAY_PERCENTAGE);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryBox10D()).isEqualTo(DEFAULT_SECONDARY_BOX_10_D);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryBox19()).isEqualTo(DEFAULT_SECONDARY_BOX_19);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryBox24Ia()).isEqualTo(DEFAULT_SECONDARY_BOX_24_IA);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryBox24Ja()).isEqualTo(DEFAULT_SECONDARY_BOX_24_JA);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryBox24Jb()).isEqualTo(DEFAULT_SECONDARY_BOX_24_JB);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryIncludeBox24JbStatus()).isEqualTo(DEFAULT_SECONDARY_INCLUDE_BOX_24_JB_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryIncludePayerSalesOrderStatus())
            .isEqualTo(DEFAULT_SECONDARY_INCLUDE_PAYER_SALES_ORDER_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryWaitPreviousPayerBefrBillingStatus())
            .isEqualTo(DEFAULT_SECONDARY_WAIT_PREVIOUS_PAYER_BEFR_BILLING_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryPayPercentageStatus()).isEqualTo(DEFAULT_SECONDARY_PAY_PERCENTAGE_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerId()).isEqualTo(DEFAULT_TERTIARY_INSURER_ID);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerName()).isEqualTo(DEFAULT_TERTIARY_INSURER_NAME);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerPolicyno()).isEqualTo(DEFAULT_TERTIARY_INSURER_POLICYNO);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerPatientIdNumber())
            .isEqualTo(DEFAULT_TERTIARY_INSURER_PATIENT_ID_NUMBER);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerEffectiveDate()).isEqualTo(DEFAULT_TERTIARY_INSURER_EFFECTIVE_DATE);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerVerificationStatus())
            .isEqualTo(DEFAULT_TERTIARY_INSURER_VERIFICATION_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerVerificationDate())
            .isEqualTo(DEFAULT_TERTIARY_INSURER_VERIFICATION_DATE);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerPayPercentage()).isEqualTo(DEFAULT_TERTIARY_INSURER_PAY_PERCENTAGE);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryBox10D()).isEqualTo(DEFAULT_TERTIARY_BOX_10_D);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryBox19()).isEqualTo(DEFAULT_TERTIARY_BOX_19);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryBox24Ia()).isEqualTo(DEFAULT_TERTIARY_BOX_24_IA);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryBox24Ja()).isEqualTo(DEFAULT_TERTIARY_BOX_24_JA);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryBox24Jb()).isEqualTo(DEFAULT_TERTIARY_BOX_24_JB);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryIncludeBox24JbStatus()).isEqualTo(DEFAULT_TERTIARY_INCLUDE_BOX_24_JB_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryIncludePayerInSalesOrderStatus())
            .isEqualTo(DEFAULT_TERTIARY_INCLUDE_PAYER_IN_SALES_ORDER_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryWaitPreviousPayerBeforeBillingStatus())
            .isEqualTo(DEFAULT_TERTIARY_WAIT_PREVIOUS_PAYER_BEFORE_BILLING_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryPayPercentageStatus()).isEqualTo(DEFAULT_TERTIARY_PAY_PERCENTAGE_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getInsuranceVerificationStatus()).isEqualTo(DEFAULT_INSURANCE_VERIFICATION_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getCoverageVerificationStatus()).isEqualTo(DEFAULT_COVERAGE_VERIFICATION_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getExcludeFromEligibilityCheckStatus())
            .isEqualTo(DEFAULT_EXCLUDE_FROM_ELIGIBILITY_CHECK_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getPatientPayPercentage()).isEqualTo(DEFAULT_PATIENT_PAY_PERCENTAGE);
        assertThat(testSalesOrderInsuranceDetails.getPatientIncludeThisPayorInSalesOrderStatus())
            .isEqualTo(DEFAULT_PATIENT_INCLUDE_THIS_PAYOR_IN_SALES_ORDER_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getPatientWaitForPreviousPayerBeforeBillingStatus())
            .isEqualTo(DEFAULT_PATIENT_WAIT_FOR_PREVIOUS_PAYER_BEFORE_BILLING_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompDateOfOnset()).isEqualTo(DEFAULT_WORKERS_COMP_DATE_OF_ONSET);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompInjuryRelatedEmploymentStatus())
            .isEqualTo(DEFAULT_WORKERS_COMP_INJURY_RELATED_EMPLOYMENT_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompInjuryRelatedAutoAccidentStatus())
            .isEqualTo(DEFAULT_WORKERS_COMP_INJURY_RELATED_AUTO_ACCIDENT_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompAutoAccidentStateCode())
            .isEqualTo(DEFAULT_WORKERS_COMP_AUTO_ACCIDENT_STATE_CODE);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompInjuryRelatedToOtherAccidentStatus())
            .isEqualTo(DEFAULT_WORKERS_COMP_INJURY_RELATED_TO_OTHER_ACCIDENT_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getEclaimsAttachmentStatus()).isEqualTo(DEFAULT_ECLAIMS_ATTACHMENT_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getAttachmentNumber()).isEqualTo(DEFAULT_ATTACHMENT_NUMBER);
        assertThat(testSalesOrderInsuranceDetails.getTypeCode()).isEqualTo(DEFAULT_TYPE_CODE);
        assertThat(testSalesOrderInsuranceDetails.getTransactionCode()).isEqualTo(DEFAULT_TRANSACTION_CODE);
        assertThat(testSalesOrderInsuranceDetails.getClaimsNoteType()).isEqualTo(DEFAULT_CLAIMS_NOTE_TYPE);
        assertThat(testSalesOrderInsuranceDetails.getClaimsNote()).isEqualTo(DEFAULT_CLAIMS_NOTE);
        assertThat(testSalesOrderInsuranceDetails.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testSalesOrderInsuranceDetails.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testSalesOrderInsuranceDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testSalesOrderInsuranceDetails.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testSalesOrderInsuranceDetails.getSalesOrderNo()).isEqualTo(DEFAULT_SALES_ORDER_NO);
        assertThat(testSalesOrderInsuranceDetails.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testSalesOrderInsuranceDetails.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsuranceGroupId()).isEqualTo(DEFAULT_PRIMARY_INSURANCE_GROUP_ID);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsuranceGroupName()).isEqualTo(DEFAULT_PRIMARY_INSURANCE_GROUP_NAME);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsuranceGroupId()).isEqualTo(DEFAULT_SECONDARY_INSURANCE_GROUP_ID);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsuranceGroupName()).isEqualTo(DEFAULT_SECONDARY_INSURANCE_GROUP_NAME);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsuranceGroupId()).isEqualTo(DEFAULT_TERTIARY_INSURANCE_GROUP_ID);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsuranceGroupName()).isEqualTo(DEFAULT_TERTIARY_INSURANCE_GROUP_NAME);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurancePlanId()).isEqualTo(DEFAULT_PRIMARY_INSURANCE_PLAN_ID);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurancePlanType()).isEqualTo(DEFAULT_PRIMARY_INSURANCE_PLAN_TYPE);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurancePlanId()).isEqualTo(DEFAULT_SECONDARY_INSURANCE_PLAN_ID);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurancePlanType()).isEqualTo(DEFAULT_SECONDARY_INSURANCE_PLAN_TYPE);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurancePlanId()).isEqualTo(DEFAULT_TERTIARY_INSURANCE_PLAN_ID);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurancePlanType()).isEqualTo(DEFAULT_TERTIARY_INSURANCE_PLAN_TYPE);
        assertThat(testSalesOrderInsuranceDetails.getSalesOrderInsuranceDetailsUuid())
            .isEqualTo(DEFAULT_SALES_ORDER_INSURANCE_DETAILS_UUID);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurancePayerId()).isEqualTo(DEFAULT_PRIMARY_INSURANCE_PAYER_ID);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurancePayerId()).isEqualTo(DEFAULT_SECONDARY_INSURANCE_PAYER_ID);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurancePayerId()).isEqualTo(DEFAULT_TERTIARY_INSURANCE_PAYER_ID);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsuranceIndicatorCode()).isEqualTo(DEFAULT_PRIMARY_INSURANCE_INDICATOR_CODE);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsuranceIndicatorCode())
            .isEqualTo(DEFAULT_SECONDARY_INSURANCE_INDICATOR_CODE);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsuranceIndicatorCode()).isEqualTo(DEFAULT_TERTIARY_INSURANCE_INDICATOR_CODE);
        assertThat(testSalesOrderInsuranceDetails.getPriceTableId()).isEqualTo(DEFAULT_PRICE_TABLE_ID);
        assertThat(testSalesOrderInsuranceDetails.getPriceTableName()).isEqualTo(DEFAULT_PRICE_TABLE_NAME);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerAddressLine1()).isEqualTo(DEFAULT_PRIMARY_INSURER_ADDRESS_LINE_1);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerAddressLine2()).isEqualTo(DEFAULT_PRIMARY_INSURER_ADDRESS_LINE_2);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerCity()).isEqualTo(DEFAULT_PRIMARY_INSURER_CITY);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerState()).isEqualTo(DEFAULT_PRIMARY_INSURER_STATE);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerZip()).isEqualTo(DEFAULT_PRIMARY_INSURER_ZIP);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerContact1()).isEqualTo(DEFAULT_PRIMARY_INSURER_CONTACT_1);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerFax()).isEqualTo(DEFAULT_PRIMARY_INSURER_FAX);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerAddressLine1()).isEqualTo(DEFAULT_SECONDARY_INSURER_ADDRESS_LINE_1);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerAddressLine2()).isEqualTo(DEFAULT_SECONDARY_INSURER_ADDRESS_LINE_2);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerCity()).isEqualTo(DEFAULT_SECONDARY_INSURER_CITY);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerState()).isEqualTo(DEFAULT_SECONDARY_INSURER_STATE);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerZip()).isEqualTo(DEFAULT_SECONDARY_INSURER_ZIP);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerContact1()).isEqualTo(DEFAULT_SECONDARY_INSURER_CONTACT_1);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerFax()).isEqualTo(DEFAULT_SECONDARY_INSURER_FAX);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerAddressLine1()).isEqualTo(DEFAULT_TERTIARY_INSURER_ADDRESS_LINE_1);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerAddressLine2()).isEqualTo(DEFAULT_TERTIARY_INSURER_ADDRESS_LINE_2);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerCity()).isEqualTo(DEFAULT_TERTIARY_INSURER_CITY);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerState()).isEqualTo(DEFAULT_TERTIARY_INSURER_STATE);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerZip()).isEqualTo(DEFAULT_TERTIARY_INSURER_ZIP);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerContact1()).isEqualTo(DEFAULT_TERTIARY_INSURER_CONTACT_1);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerFax()).isEqualTo(DEFAULT_TERTIARY_INSURER_FAX);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerPolicyEndDate()).isEqualTo(DEFAULT_PRIMARY_INSURER_POLICY_END_DATE);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerContactName()).isEqualTo(DEFAULT_PRIMARY_INSURER_CONTACT_NAME);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryClaimProgram()).isEqualTo(DEFAULT_PRIMARY_CLAIM_PROGRAM);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryClaimProgram()).isEqualTo(DEFAULT_SECONDARY_CLAIM_PROGRAM);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryClaimProgram()).isEqualTo(DEFAULT_TERTIARY_CLAIM_PROGRAM);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompInsuredEmployer()).isEqualTo(DEFAULT_WORKERS_COMP_INSURED_EMPLOYER);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompPayerIdNumber()).isEqualTo(DEFAULT_WORKERS_COMP_PAYER_ID_NUMBER);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompPlanName()).isEqualTo(DEFAULT_WORKERS_COMP_PLAN_NAME);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompAdditionalDtls()).isEqualTo(DEFAULT_WORKERS_COMP_ADDITIONAL_DTLS);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompClaimFillingCode()).isEqualTo(DEFAULT_WORKERS_COMP_CLAIM_FILLING_CODE);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompTplCode()).isEqualTo(DEFAULT_WORKERS_COMP_TPL_CODE);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompTplName()).isEqualTo(DEFAULT_WORKERS_COMP_TPL_NAME);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompPropertyCasualtyAgencyClaimNo())
            .isEqualTo(DEFAULT_WORKERS_COMP_PROPERTY_CASUALTY_AGENCY_CLAIM_NO);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompCarrierId()).isEqualTo(DEFAULT_WORKERS_COMP_CARRIER_ID);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerAddressLine1())
            .isEqualTo(DEFAULT_WORKERS_COMP_EMPLOYER_ADDRESS_LINE_1);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerAddressLine2())
            .isEqualTo(DEFAULT_WORKERS_COMP_EMPLOYER_ADDRESS_LINE_2);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerCity()).isEqualTo(DEFAULT_WORKERS_COMP_EMPLOYER_CITY);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerState()).isEqualTo(DEFAULT_WORKERS_COMP_EMPLOYER_STATE);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerCountry()).isEqualTo(DEFAULT_WORKERS_COMP_EMPLOYER_COUNTRY);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerZip()).isEqualTo(DEFAULT_WORKERS_COMP_EMPLOYER_ZIP);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerContactNo1()).isEqualTo(DEFAULT_WORKERS_COMP_EMPLOYER_CONTACT_NO_1);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerContactNo2()).isEqualTo(DEFAULT_WORKERS_COMP_EMPLOYER_CONTACT_NO_2);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerFax()).isEqualTo(DEFAULT_WORKERS_COMP_EMPLOYER_FAX);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerEfax()).isEqualTo(DEFAULT_WORKERS_COMP_EMPLOYER_EFAX);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerEmail()).isEqualTo(DEFAULT_WORKERS_COMP_EMPLOYER_EMAIL);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompRelationship()).isEqualTo(DEFAULT_WORKERS_COMP_RELATIONSHIP);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompModeOfContact()).isEqualTo(DEFAULT_WORKERS_COMP_MODE_OF_CONTACT);
    }

    @Test
    void createSalesOrderInsuranceDetailsWithExistingId() throws Exception {
        // Create the SalesOrderInsuranceDetails with an existing ID
        salesOrderInsuranceDetails.setSalesOrderInsuranceDetailsId(1L);
        SalesOrderInsuranceDetailsDTO salesOrderInsuranceDetailsDTO = salesOrderInsuranceDetailsMapper.toDto(salesOrderInsuranceDetails);

        int databaseSizeBeforeCreate = salesOrderInsuranceDetailsRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderInsuranceDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderInsuranceDetails in the database
        List<SalesOrderInsuranceDetails> salesOrderInsuranceDetailsList = salesOrderInsuranceDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderInsuranceDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllSalesOrderInsuranceDetails() {
        // Initialize the database
        salesOrderInsuranceDetailsRepository.save(salesOrderInsuranceDetails).block();

        // Get all the salesOrderInsuranceDetailsList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=salesOrderInsuranceDetailsId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].salesOrderInsuranceDetailsId")
            .value(hasItem(salesOrderInsuranceDetails.getSalesOrderInsuranceDetailsId().intValue()))
            .jsonPath("$.[*].salesOrderId")
            .value(hasItem(DEFAULT_SALES_ORDER_ID.intValue()))
            .jsonPath("$.[*].patientId")
            .value(hasItem(DEFAULT_PATIENT_ID.intValue()))
            .jsonPath("$.[*].primaryInsurerId")
            .value(hasItem(DEFAULT_PRIMARY_INSURER_ID.intValue()))
            .jsonPath("$.[*].primaryInsurerName")
            .value(hasItem(DEFAULT_PRIMARY_INSURER_NAME))
            .jsonPath("$.[*].primaryInsurerPolicyNo")
            .value(hasItem(DEFAULT_PRIMARY_INSURER_POLICY_NO))
            .jsonPath("$.[*].primaryInsurerPatientIdNumber")
            .value(hasItem(DEFAULT_PRIMARY_INSURER_PATIENT_ID_NUMBER))
            .jsonPath("$.[*].primaryInsurerEffectiveDate")
            .value(hasItem(DEFAULT_PRIMARY_INSURER_EFFECTIVE_DATE.toString()))
            .jsonPath("$.[*].primaryInsurerVerificationStatus")
            .value(hasItem(DEFAULT_PRIMARY_INSURER_VERIFICATION_STATUS))
            .jsonPath("$.[*].primaryInsurerVerificationDate")
            .value(hasItem(DEFAULT_PRIMARY_INSURER_VERIFICATION_DATE.toString()))
            .jsonPath("$.[*].primaryInsurerPayPercentage")
            .value(hasItem(DEFAULT_PRIMARY_INSURER_PAY_PERCENTAGE.intValue()))
            .jsonPath("$.[*].primaryBox10D")
            .value(hasItem(DEFAULT_PRIMARY_BOX_10_D))
            .jsonPath("$.[*].primaryBox19")
            .value(hasItem(DEFAULT_PRIMARY_BOX_19))
            .jsonPath("$.[*].primaryBox24Ia")
            .value(hasItem(DEFAULT_PRIMARY_BOX_24_IA))
            .jsonPath("$.[*].primaryBox24Ja")
            .value(hasItem(DEFAULT_PRIMARY_BOX_24_JA))
            .jsonPath("$.[*].primaryBox24Jb")
            .value(hasItem(DEFAULT_PRIMARY_BOX_24_JB))
            .jsonPath("$.[*].primaryIncludeBox24Jbstatus")
            .value(hasItem(DEFAULT_PRIMARY_INCLUDE_BOX_24_JBSTATUS))
            .jsonPath("$.[*].primaryIncludePayerSalesOrderStatus")
            .value(hasItem(DEFAULT_PRIMARY_INCLUDE_PAYER_SALES_ORDER_STATUS))
            .jsonPath("$.[*].primaryWaitForPreviousPayerBeforeBillingStatus")
            .value(hasItem(DEFAULT_PRIMARY_WAIT_FOR_PREVIOUS_PAYER_BEFORE_BILLING_STATUS))
            .jsonPath("$.[*].primaryPayPercentageStatus")
            .value(hasItem(DEFAULT_PRIMARY_PAY_PERCENTAGE_STATUS))
            .jsonPath("$.[*].secondaryInsurerId")
            .value(hasItem(DEFAULT_SECONDARY_INSURER_ID.intValue()))
            .jsonPath("$.[*].secondaryInsurerName")
            .value(hasItem(DEFAULT_SECONDARY_INSURER_NAME))
            .jsonPath("$.[*].secondaryInsurerPolicyNo")
            .value(hasItem(DEFAULT_SECONDARY_INSURER_POLICY_NO))
            .jsonPath("$.[*].secondaryInsurerPatientIdNumber")
            .value(hasItem(DEFAULT_SECONDARY_INSURER_PATIENT_ID_NUMBER))
            .jsonPath("$.[*].secondaryInsurerEffectiveDate")
            .value(hasItem(DEFAULT_SECONDARY_INSURER_EFFECTIVE_DATE.toString()))
            .jsonPath("$.[*].secondaryInsurerVerificationStatus")
            .value(hasItem(DEFAULT_SECONDARY_INSURER_VERIFICATION_STATUS))
            .jsonPath("$.[*].secondaryInsurerVerificationDate")
            .value(hasItem(DEFAULT_SECONDARY_INSURER_VERIFICATION_DATE.toString()))
            .jsonPath("$.[*].secondaryInsurerPayPercentage")
            .value(hasItem(DEFAULT_SECONDARY_INSURER_PAY_PERCENTAGE.intValue()))
            .jsonPath("$.[*].secondaryBox10D")
            .value(hasItem(DEFAULT_SECONDARY_BOX_10_D))
            .jsonPath("$.[*].secondaryBox19")
            .value(hasItem(DEFAULT_SECONDARY_BOX_19))
            .jsonPath("$.[*].secondaryBox24Ia")
            .value(hasItem(DEFAULT_SECONDARY_BOX_24_IA))
            .jsonPath("$.[*].secondaryBox24Ja")
            .value(hasItem(DEFAULT_SECONDARY_BOX_24_JA))
            .jsonPath("$.[*].secondaryBox24Jb")
            .value(hasItem(DEFAULT_SECONDARY_BOX_24_JB))
            .jsonPath("$.[*].secondaryIncludeBox24JbStatus")
            .value(hasItem(DEFAULT_SECONDARY_INCLUDE_BOX_24_JB_STATUS))
            .jsonPath("$.[*].secondaryIncludePayerSalesOrderStatus")
            .value(hasItem(DEFAULT_SECONDARY_INCLUDE_PAYER_SALES_ORDER_STATUS))
            .jsonPath("$.[*].secondaryWaitPreviousPayerBefrBillingStatus")
            .value(hasItem(DEFAULT_SECONDARY_WAIT_PREVIOUS_PAYER_BEFR_BILLING_STATUS))
            .jsonPath("$.[*].secondaryPayPercentageStatus")
            .value(hasItem(DEFAULT_SECONDARY_PAY_PERCENTAGE_STATUS))
            .jsonPath("$.[*].tertiaryInsurerId")
            .value(hasItem(DEFAULT_TERTIARY_INSURER_ID.intValue()))
            .jsonPath("$.[*].tertiaryInsurerName")
            .value(hasItem(DEFAULT_TERTIARY_INSURER_NAME))
            .jsonPath("$.[*].tertiaryInsurerPolicyno")
            .value(hasItem(DEFAULT_TERTIARY_INSURER_POLICYNO))
            .jsonPath("$.[*].tertiaryInsurerPatientIdNumber")
            .value(hasItem(DEFAULT_TERTIARY_INSURER_PATIENT_ID_NUMBER))
            .jsonPath("$.[*].tertiaryInsurerEffectiveDate")
            .value(hasItem(DEFAULT_TERTIARY_INSURER_EFFECTIVE_DATE.toString()))
            .jsonPath("$.[*].tertiaryInsurerVerificationStatus")
            .value(hasItem(DEFAULT_TERTIARY_INSURER_VERIFICATION_STATUS))
            .jsonPath("$.[*].tertiaryInsurerVerificationDate")
            .value(hasItem(DEFAULT_TERTIARY_INSURER_VERIFICATION_DATE.toString()))
            .jsonPath("$.[*].tertiaryInsurerPayPercentage")
            .value(hasItem(DEFAULT_TERTIARY_INSURER_PAY_PERCENTAGE.intValue()))
            .jsonPath("$.[*].tertiaryBox10D")
            .value(hasItem(DEFAULT_TERTIARY_BOX_10_D))
            .jsonPath("$.[*].tertiaryBox19")
            .value(hasItem(DEFAULT_TERTIARY_BOX_19))
            .jsonPath("$.[*].tertiaryBox24Ia")
            .value(hasItem(DEFAULT_TERTIARY_BOX_24_IA))
            .jsonPath("$.[*].tertiaryBox24Ja")
            .value(hasItem(DEFAULT_TERTIARY_BOX_24_JA))
            .jsonPath("$.[*].tertiaryBox24Jb")
            .value(hasItem(DEFAULT_TERTIARY_BOX_24_JB))
            .jsonPath("$.[*].tertiaryIncludeBox24JbStatus")
            .value(hasItem(DEFAULT_TERTIARY_INCLUDE_BOX_24_JB_STATUS))
            .jsonPath("$.[*].tertiaryIncludePayerInSalesOrderStatus")
            .value(hasItem(DEFAULT_TERTIARY_INCLUDE_PAYER_IN_SALES_ORDER_STATUS))
            .jsonPath("$.[*].tertiaryWaitPreviousPayerBeforeBillingStatus")
            .value(hasItem(DEFAULT_TERTIARY_WAIT_PREVIOUS_PAYER_BEFORE_BILLING_STATUS))
            .jsonPath("$.[*].tertiaryPayPercentageStatus")
            .value(hasItem(DEFAULT_TERTIARY_PAY_PERCENTAGE_STATUS))
            .jsonPath("$.[*].insuranceVerificationStatus")
            .value(hasItem(DEFAULT_INSURANCE_VERIFICATION_STATUS))
            .jsonPath("$.[*].coverageVerificationStatus")
            .value(hasItem(DEFAULT_COVERAGE_VERIFICATION_STATUS))
            .jsonPath("$.[*].excludeFromEligibilityCheckStatus")
            .value(hasItem(DEFAULT_EXCLUDE_FROM_ELIGIBILITY_CHECK_STATUS))
            .jsonPath("$.[*].patientPayPercentage")
            .value(hasItem(DEFAULT_PATIENT_PAY_PERCENTAGE.intValue()))
            .jsonPath("$.[*].patientIncludeThisPayorInSalesOrderStatus")
            .value(hasItem(DEFAULT_PATIENT_INCLUDE_THIS_PAYOR_IN_SALES_ORDER_STATUS))
            .jsonPath("$.[*].patientWaitForPreviousPayerBeforeBillingStatus")
            .value(hasItem(DEFAULT_PATIENT_WAIT_FOR_PREVIOUS_PAYER_BEFORE_BILLING_STATUS))
            .jsonPath("$.[*].workersCompDateOfOnset")
            .value(hasItem(DEFAULT_WORKERS_COMP_DATE_OF_ONSET.toString()))
            .jsonPath("$.[*].workersCompInjuryRelatedEmploymentStatus")
            .value(hasItem(DEFAULT_WORKERS_COMP_INJURY_RELATED_EMPLOYMENT_STATUS))
            .jsonPath("$.[*].workersCompInjuryRelatedAutoAccidentStatus")
            .value(hasItem(DEFAULT_WORKERS_COMP_INJURY_RELATED_AUTO_ACCIDENT_STATUS))
            .jsonPath("$.[*].workersCompAutoAccidentStateCode")
            .value(hasItem(DEFAULT_WORKERS_COMP_AUTO_ACCIDENT_STATE_CODE))
            .jsonPath("$.[*].workersCompInjuryRelatedToOtherAccidentStatus")
            .value(hasItem(DEFAULT_WORKERS_COMP_INJURY_RELATED_TO_OTHER_ACCIDENT_STATUS))
            .jsonPath("$.[*].eclaimsAttachmentStatus")
            .value(hasItem(DEFAULT_ECLAIMS_ATTACHMENT_STATUS))
            .jsonPath("$.[*].attachmentNumber")
            .value(hasItem(DEFAULT_ATTACHMENT_NUMBER.intValue()))
            .jsonPath("$.[*].typeCode")
            .value(hasItem(DEFAULT_TYPE_CODE))
            .jsonPath("$.[*].transactionCode")
            .value(hasItem(DEFAULT_TRANSACTION_CODE))
            .jsonPath("$.[*].claimsNoteType")
            .value(hasItem(DEFAULT_CLAIMS_NOTE_TYPE))
            .jsonPath("$.[*].claimsNote")
            .value(hasItem(DEFAULT_CLAIMS_NOTE))
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
            .jsonPath("$.[*].salesOrderNo")
            .value(hasItem(DEFAULT_SALES_ORDER_NO))
            .jsonPath("$.[*].createdByName")
            .value(hasItem(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.[*].updatedByName")
            .value(hasItem(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.[*].primaryInsuranceGroupId")
            .value(hasItem(DEFAULT_PRIMARY_INSURANCE_GROUP_ID.intValue()))
            .jsonPath("$.[*].primaryInsuranceGroupName")
            .value(hasItem(DEFAULT_PRIMARY_INSURANCE_GROUP_NAME))
            .jsonPath("$.[*].secondaryInsuranceGroupId")
            .value(hasItem(DEFAULT_SECONDARY_INSURANCE_GROUP_ID.intValue()))
            .jsonPath("$.[*].secondaryInsuranceGroupName")
            .value(hasItem(DEFAULT_SECONDARY_INSURANCE_GROUP_NAME))
            .jsonPath("$.[*].tertiaryInsuranceGroupId")
            .value(hasItem(DEFAULT_TERTIARY_INSURANCE_GROUP_ID.intValue()))
            .jsonPath("$.[*].tertiaryInsuranceGroupName")
            .value(hasItem(DEFAULT_TERTIARY_INSURANCE_GROUP_NAME))
            .jsonPath("$.[*].primaryInsurancePlanId")
            .value(hasItem(DEFAULT_PRIMARY_INSURANCE_PLAN_ID.intValue()))
            .jsonPath("$.[*].primaryInsurancePlanType")
            .value(hasItem(DEFAULT_PRIMARY_INSURANCE_PLAN_TYPE))
            .jsonPath("$.[*].secondaryInsurancePlanId")
            .value(hasItem(DEFAULT_SECONDARY_INSURANCE_PLAN_ID.intValue()))
            .jsonPath("$.[*].secondaryInsurancePlanType")
            .value(hasItem(DEFAULT_SECONDARY_INSURANCE_PLAN_TYPE))
            .jsonPath("$.[*].tertiaryInsurancePlanId")
            .value(hasItem(DEFAULT_TERTIARY_INSURANCE_PLAN_ID.intValue()))
            .jsonPath("$.[*].tertiaryInsurancePlanType")
            .value(hasItem(DEFAULT_TERTIARY_INSURANCE_PLAN_TYPE))
            .jsonPath("$.[*].salesOrderInsuranceDetailsUuid")
            .value(hasItem(DEFAULT_SALES_ORDER_INSURANCE_DETAILS_UUID.toString()))
            .jsonPath("$.[*].primaryInsurancePayerId")
            .value(hasItem(DEFAULT_PRIMARY_INSURANCE_PAYER_ID))
            .jsonPath("$.[*].secondaryInsurancePayerId")
            .value(hasItem(DEFAULT_SECONDARY_INSURANCE_PAYER_ID))
            .jsonPath("$.[*].tertiaryInsurancePayerId")
            .value(hasItem(DEFAULT_TERTIARY_INSURANCE_PAYER_ID))
            .jsonPath("$.[*].primaryInsuranceIndicatorCode")
            .value(hasItem(DEFAULT_PRIMARY_INSURANCE_INDICATOR_CODE))
            .jsonPath("$.[*].secondaryInsuranceIndicatorCode")
            .value(hasItem(DEFAULT_SECONDARY_INSURANCE_INDICATOR_CODE))
            .jsonPath("$.[*].tertiaryInsuranceIndicatorCode")
            .value(hasItem(DEFAULT_TERTIARY_INSURANCE_INDICATOR_CODE))
            .jsonPath("$.[*].priceTableId")
            .value(hasItem(DEFAULT_PRICE_TABLE_ID.intValue()))
            .jsonPath("$.[*].priceTableName")
            .value(hasItem(DEFAULT_PRICE_TABLE_NAME))
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
            .jsonPath("$.[*].primaryInsurerPolicyEndDate")
            .value(hasItem(DEFAULT_PRIMARY_INSURER_POLICY_END_DATE.toString()))
            .jsonPath("$.[*].primaryInsurerContactName")
            .value(hasItem(DEFAULT_PRIMARY_INSURER_CONTACT_NAME))
            .jsonPath("$.[*].primaryClaimProgram")
            .value(hasItem(DEFAULT_PRIMARY_CLAIM_PROGRAM))
            .jsonPath("$.[*].secondaryClaimProgram")
            .value(hasItem(DEFAULT_SECONDARY_CLAIM_PROGRAM))
            .jsonPath("$.[*].tertiaryClaimProgram")
            .value(hasItem(DEFAULT_TERTIARY_CLAIM_PROGRAM))
            .jsonPath("$.[*].workersCompInsuredEmployer")
            .value(hasItem(DEFAULT_WORKERS_COMP_INSURED_EMPLOYER))
            .jsonPath("$.[*].workersCompPayerIdNumber")
            .value(hasItem(DEFAULT_WORKERS_COMP_PAYER_ID_NUMBER))
            .jsonPath("$.[*].workersCompPlanName")
            .value(hasItem(DEFAULT_WORKERS_COMP_PLAN_NAME))
            .jsonPath("$.[*].workersCompAdditionalDtls")
            .value(hasItem(DEFAULT_WORKERS_COMP_ADDITIONAL_DTLS))
            .jsonPath("$.[*].workersCompClaimFillingCode")
            .value(hasItem(DEFAULT_WORKERS_COMP_CLAIM_FILLING_CODE))
            .jsonPath("$.[*].workersCompTplCode")
            .value(hasItem(DEFAULT_WORKERS_COMP_TPL_CODE))
            .jsonPath("$.[*].workersCompTplName")
            .value(hasItem(DEFAULT_WORKERS_COMP_TPL_NAME))
            .jsonPath("$.[*].workersCompPropertyCasualtyAgencyClaimNo")
            .value(hasItem(DEFAULT_WORKERS_COMP_PROPERTY_CASUALTY_AGENCY_CLAIM_NO))
            .jsonPath("$.[*].workersCompCarrierId")
            .value(hasItem(DEFAULT_WORKERS_COMP_CARRIER_ID))
            .jsonPath("$.[*].workersCompEmployerAddressLine1")
            .value(hasItem(DEFAULT_WORKERS_COMP_EMPLOYER_ADDRESS_LINE_1))
            .jsonPath("$.[*].workersCompEmployerAddressLine2")
            .value(hasItem(DEFAULT_WORKERS_COMP_EMPLOYER_ADDRESS_LINE_2))
            .jsonPath("$.[*].workersCompEmployerCity")
            .value(hasItem(DEFAULT_WORKERS_COMP_EMPLOYER_CITY))
            .jsonPath("$.[*].workersCompEmployerState")
            .value(hasItem(DEFAULT_WORKERS_COMP_EMPLOYER_STATE))
            .jsonPath("$.[*].workersCompEmployerCountry")
            .value(hasItem(DEFAULT_WORKERS_COMP_EMPLOYER_COUNTRY))
            .jsonPath("$.[*].workersCompEmployerZip")
            .value(hasItem(DEFAULT_WORKERS_COMP_EMPLOYER_ZIP))
            .jsonPath("$.[*].workersCompEmployerContactNo1")
            .value(hasItem(DEFAULT_WORKERS_COMP_EMPLOYER_CONTACT_NO_1))
            .jsonPath("$.[*].workersCompEmployerContactNo2")
            .value(hasItem(DEFAULT_WORKERS_COMP_EMPLOYER_CONTACT_NO_2))
            .jsonPath("$.[*].workersCompEmployerFax")
            .value(hasItem(DEFAULT_WORKERS_COMP_EMPLOYER_FAX))
            .jsonPath("$.[*].workersCompEmployerEfax")
            .value(hasItem(DEFAULT_WORKERS_COMP_EMPLOYER_EFAX))
            .jsonPath("$.[*].workersCompEmployerEmail")
            .value(hasItem(DEFAULT_WORKERS_COMP_EMPLOYER_EMAIL))
            .jsonPath("$.[*].workersCompRelationship")
            .value(hasItem(DEFAULT_WORKERS_COMP_RELATIONSHIP))
            .jsonPath("$.[*].workersCompModeOfContact")
            .value(hasItem(DEFAULT_WORKERS_COMP_MODE_OF_CONTACT));
    }

    @Test
    void getSalesOrderInsuranceDetails() {
        // Initialize the database
        salesOrderInsuranceDetailsRepository.save(salesOrderInsuranceDetails).block();

        // Get the salesOrderInsuranceDetails
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, salesOrderInsuranceDetails.getSalesOrderInsuranceDetailsId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.salesOrderInsuranceDetailsId")
            .value(is(salesOrderInsuranceDetails.getSalesOrderInsuranceDetailsId().intValue()))
            .jsonPath("$.salesOrderId")
            .value(is(DEFAULT_SALES_ORDER_ID.intValue()))
            .jsonPath("$.patientId")
            .value(is(DEFAULT_PATIENT_ID.intValue()))
            .jsonPath("$.primaryInsurerId")
            .value(is(DEFAULT_PRIMARY_INSURER_ID.intValue()))
            .jsonPath("$.primaryInsurerName")
            .value(is(DEFAULT_PRIMARY_INSURER_NAME))
            .jsonPath("$.primaryInsurerPolicyNo")
            .value(is(DEFAULT_PRIMARY_INSURER_POLICY_NO))
            .jsonPath("$.primaryInsurerPatientIdNumber")
            .value(is(DEFAULT_PRIMARY_INSURER_PATIENT_ID_NUMBER))
            .jsonPath("$.primaryInsurerEffectiveDate")
            .value(is(DEFAULT_PRIMARY_INSURER_EFFECTIVE_DATE.toString()))
            .jsonPath("$.primaryInsurerVerificationStatus")
            .value(is(DEFAULT_PRIMARY_INSURER_VERIFICATION_STATUS))
            .jsonPath("$.primaryInsurerVerificationDate")
            .value(is(DEFAULT_PRIMARY_INSURER_VERIFICATION_DATE.toString()))
            .jsonPath("$.primaryInsurerPayPercentage")
            .value(is(DEFAULT_PRIMARY_INSURER_PAY_PERCENTAGE.intValue()))
            .jsonPath("$.primaryBox10D")
            .value(is(DEFAULT_PRIMARY_BOX_10_D))
            .jsonPath("$.primaryBox19")
            .value(is(DEFAULT_PRIMARY_BOX_19))
            .jsonPath("$.primaryBox24Ia")
            .value(is(DEFAULT_PRIMARY_BOX_24_IA))
            .jsonPath("$.primaryBox24Ja")
            .value(is(DEFAULT_PRIMARY_BOX_24_JA))
            .jsonPath("$.primaryBox24Jb")
            .value(is(DEFAULT_PRIMARY_BOX_24_JB))
            .jsonPath("$.primaryIncludeBox24Jbstatus")
            .value(is(DEFAULT_PRIMARY_INCLUDE_BOX_24_JBSTATUS))
            .jsonPath("$.primaryIncludePayerSalesOrderStatus")
            .value(is(DEFAULT_PRIMARY_INCLUDE_PAYER_SALES_ORDER_STATUS))
            .jsonPath("$.primaryWaitForPreviousPayerBeforeBillingStatus")
            .value(is(DEFAULT_PRIMARY_WAIT_FOR_PREVIOUS_PAYER_BEFORE_BILLING_STATUS))
            .jsonPath("$.primaryPayPercentageStatus")
            .value(is(DEFAULT_PRIMARY_PAY_PERCENTAGE_STATUS))
            .jsonPath("$.secondaryInsurerId")
            .value(is(DEFAULT_SECONDARY_INSURER_ID.intValue()))
            .jsonPath("$.secondaryInsurerName")
            .value(is(DEFAULT_SECONDARY_INSURER_NAME))
            .jsonPath("$.secondaryInsurerPolicyNo")
            .value(is(DEFAULT_SECONDARY_INSURER_POLICY_NO))
            .jsonPath("$.secondaryInsurerPatientIdNumber")
            .value(is(DEFAULT_SECONDARY_INSURER_PATIENT_ID_NUMBER))
            .jsonPath("$.secondaryInsurerEffectiveDate")
            .value(is(DEFAULT_SECONDARY_INSURER_EFFECTIVE_DATE.toString()))
            .jsonPath("$.secondaryInsurerVerificationStatus")
            .value(is(DEFAULT_SECONDARY_INSURER_VERIFICATION_STATUS))
            .jsonPath("$.secondaryInsurerVerificationDate")
            .value(is(DEFAULT_SECONDARY_INSURER_VERIFICATION_DATE.toString()))
            .jsonPath("$.secondaryInsurerPayPercentage")
            .value(is(DEFAULT_SECONDARY_INSURER_PAY_PERCENTAGE.intValue()))
            .jsonPath("$.secondaryBox10D")
            .value(is(DEFAULT_SECONDARY_BOX_10_D))
            .jsonPath("$.secondaryBox19")
            .value(is(DEFAULT_SECONDARY_BOX_19))
            .jsonPath("$.secondaryBox24Ia")
            .value(is(DEFAULT_SECONDARY_BOX_24_IA))
            .jsonPath("$.secondaryBox24Ja")
            .value(is(DEFAULT_SECONDARY_BOX_24_JA))
            .jsonPath("$.secondaryBox24Jb")
            .value(is(DEFAULT_SECONDARY_BOX_24_JB))
            .jsonPath("$.secondaryIncludeBox24JbStatus")
            .value(is(DEFAULT_SECONDARY_INCLUDE_BOX_24_JB_STATUS))
            .jsonPath("$.secondaryIncludePayerSalesOrderStatus")
            .value(is(DEFAULT_SECONDARY_INCLUDE_PAYER_SALES_ORDER_STATUS))
            .jsonPath("$.secondaryWaitPreviousPayerBefrBillingStatus")
            .value(is(DEFAULT_SECONDARY_WAIT_PREVIOUS_PAYER_BEFR_BILLING_STATUS))
            .jsonPath("$.secondaryPayPercentageStatus")
            .value(is(DEFAULT_SECONDARY_PAY_PERCENTAGE_STATUS))
            .jsonPath("$.tertiaryInsurerId")
            .value(is(DEFAULT_TERTIARY_INSURER_ID.intValue()))
            .jsonPath("$.tertiaryInsurerName")
            .value(is(DEFAULT_TERTIARY_INSURER_NAME))
            .jsonPath("$.tertiaryInsurerPolicyno")
            .value(is(DEFAULT_TERTIARY_INSURER_POLICYNO))
            .jsonPath("$.tertiaryInsurerPatientIdNumber")
            .value(is(DEFAULT_TERTIARY_INSURER_PATIENT_ID_NUMBER))
            .jsonPath("$.tertiaryInsurerEffectiveDate")
            .value(is(DEFAULT_TERTIARY_INSURER_EFFECTIVE_DATE.toString()))
            .jsonPath("$.tertiaryInsurerVerificationStatus")
            .value(is(DEFAULT_TERTIARY_INSURER_VERIFICATION_STATUS))
            .jsonPath("$.tertiaryInsurerVerificationDate")
            .value(is(DEFAULT_TERTIARY_INSURER_VERIFICATION_DATE.toString()))
            .jsonPath("$.tertiaryInsurerPayPercentage")
            .value(is(DEFAULT_TERTIARY_INSURER_PAY_PERCENTAGE.intValue()))
            .jsonPath("$.tertiaryBox10D")
            .value(is(DEFAULT_TERTIARY_BOX_10_D))
            .jsonPath("$.tertiaryBox19")
            .value(is(DEFAULT_TERTIARY_BOX_19))
            .jsonPath("$.tertiaryBox24Ia")
            .value(is(DEFAULT_TERTIARY_BOX_24_IA))
            .jsonPath("$.tertiaryBox24Ja")
            .value(is(DEFAULT_TERTIARY_BOX_24_JA))
            .jsonPath("$.tertiaryBox24Jb")
            .value(is(DEFAULT_TERTIARY_BOX_24_JB))
            .jsonPath("$.tertiaryIncludeBox24JbStatus")
            .value(is(DEFAULT_TERTIARY_INCLUDE_BOX_24_JB_STATUS))
            .jsonPath("$.tertiaryIncludePayerInSalesOrderStatus")
            .value(is(DEFAULT_TERTIARY_INCLUDE_PAYER_IN_SALES_ORDER_STATUS))
            .jsonPath("$.tertiaryWaitPreviousPayerBeforeBillingStatus")
            .value(is(DEFAULT_TERTIARY_WAIT_PREVIOUS_PAYER_BEFORE_BILLING_STATUS))
            .jsonPath("$.tertiaryPayPercentageStatus")
            .value(is(DEFAULT_TERTIARY_PAY_PERCENTAGE_STATUS))
            .jsonPath("$.insuranceVerificationStatus")
            .value(is(DEFAULT_INSURANCE_VERIFICATION_STATUS))
            .jsonPath("$.coverageVerificationStatus")
            .value(is(DEFAULT_COVERAGE_VERIFICATION_STATUS))
            .jsonPath("$.excludeFromEligibilityCheckStatus")
            .value(is(DEFAULT_EXCLUDE_FROM_ELIGIBILITY_CHECK_STATUS))
            .jsonPath("$.patientPayPercentage")
            .value(is(DEFAULT_PATIENT_PAY_PERCENTAGE.intValue()))
            .jsonPath("$.patientIncludeThisPayorInSalesOrderStatus")
            .value(is(DEFAULT_PATIENT_INCLUDE_THIS_PAYOR_IN_SALES_ORDER_STATUS))
            .jsonPath("$.patientWaitForPreviousPayerBeforeBillingStatus")
            .value(is(DEFAULT_PATIENT_WAIT_FOR_PREVIOUS_PAYER_BEFORE_BILLING_STATUS))
            .jsonPath("$.workersCompDateOfOnset")
            .value(is(DEFAULT_WORKERS_COMP_DATE_OF_ONSET.toString()))
            .jsonPath("$.workersCompInjuryRelatedEmploymentStatus")
            .value(is(DEFAULT_WORKERS_COMP_INJURY_RELATED_EMPLOYMENT_STATUS))
            .jsonPath("$.workersCompInjuryRelatedAutoAccidentStatus")
            .value(is(DEFAULT_WORKERS_COMP_INJURY_RELATED_AUTO_ACCIDENT_STATUS))
            .jsonPath("$.workersCompAutoAccidentStateCode")
            .value(is(DEFAULT_WORKERS_COMP_AUTO_ACCIDENT_STATE_CODE))
            .jsonPath("$.workersCompInjuryRelatedToOtherAccidentStatus")
            .value(is(DEFAULT_WORKERS_COMP_INJURY_RELATED_TO_OTHER_ACCIDENT_STATUS))
            .jsonPath("$.eclaimsAttachmentStatus")
            .value(is(DEFAULT_ECLAIMS_ATTACHMENT_STATUS))
            .jsonPath("$.attachmentNumber")
            .value(is(DEFAULT_ATTACHMENT_NUMBER.intValue()))
            .jsonPath("$.typeCode")
            .value(is(DEFAULT_TYPE_CODE))
            .jsonPath("$.transactionCode")
            .value(is(DEFAULT_TRANSACTION_CODE))
            .jsonPath("$.claimsNoteType")
            .value(is(DEFAULT_CLAIMS_NOTE_TYPE))
            .jsonPath("$.claimsNote")
            .value(is(DEFAULT_CLAIMS_NOTE))
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
            .jsonPath("$.salesOrderNo")
            .value(is(DEFAULT_SALES_ORDER_NO))
            .jsonPath("$.createdByName")
            .value(is(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.updatedByName")
            .value(is(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.primaryInsuranceGroupId")
            .value(is(DEFAULT_PRIMARY_INSURANCE_GROUP_ID.intValue()))
            .jsonPath("$.primaryInsuranceGroupName")
            .value(is(DEFAULT_PRIMARY_INSURANCE_GROUP_NAME))
            .jsonPath("$.secondaryInsuranceGroupId")
            .value(is(DEFAULT_SECONDARY_INSURANCE_GROUP_ID.intValue()))
            .jsonPath("$.secondaryInsuranceGroupName")
            .value(is(DEFAULT_SECONDARY_INSURANCE_GROUP_NAME))
            .jsonPath("$.tertiaryInsuranceGroupId")
            .value(is(DEFAULT_TERTIARY_INSURANCE_GROUP_ID.intValue()))
            .jsonPath("$.tertiaryInsuranceGroupName")
            .value(is(DEFAULT_TERTIARY_INSURANCE_GROUP_NAME))
            .jsonPath("$.primaryInsurancePlanId")
            .value(is(DEFAULT_PRIMARY_INSURANCE_PLAN_ID.intValue()))
            .jsonPath("$.primaryInsurancePlanType")
            .value(is(DEFAULT_PRIMARY_INSURANCE_PLAN_TYPE))
            .jsonPath("$.secondaryInsurancePlanId")
            .value(is(DEFAULT_SECONDARY_INSURANCE_PLAN_ID.intValue()))
            .jsonPath("$.secondaryInsurancePlanType")
            .value(is(DEFAULT_SECONDARY_INSURANCE_PLAN_TYPE))
            .jsonPath("$.tertiaryInsurancePlanId")
            .value(is(DEFAULT_TERTIARY_INSURANCE_PLAN_ID.intValue()))
            .jsonPath("$.tertiaryInsurancePlanType")
            .value(is(DEFAULT_TERTIARY_INSURANCE_PLAN_TYPE))
            .jsonPath("$.salesOrderInsuranceDetailsUuid")
            .value(is(DEFAULT_SALES_ORDER_INSURANCE_DETAILS_UUID.toString()))
            .jsonPath("$.primaryInsurancePayerId")
            .value(is(DEFAULT_PRIMARY_INSURANCE_PAYER_ID))
            .jsonPath("$.secondaryInsurancePayerId")
            .value(is(DEFAULT_SECONDARY_INSURANCE_PAYER_ID))
            .jsonPath("$.tertiaryInsurancePayerId")
            .value(is(DEFAULT_TERTIARY_INSURANCE_PAYER_ID))
            .jsonPath("$.primaryInsuranceIndicatorCode")
            .value(is(DEFAULT_PRIMARY_INSURANCE_INDICATOR_CODE))
            .jsonPath("$.secondaryInsuranceIndicatorCode")
            .value(is(DEFAULT_SECONDARY_INSURANCE_INDICATOR_CODE))
            .jsonPath("$.tertiaryInsuranceIndicatorCode")
            .value(is(DEFAULT_TERTIARY_INSURANCE_INDICATOR_CODE))
            .jsonPath("$.priceTableId")
            .value(is(DEFAULT_PRICE_TABLE_ID.intValue()))
            .jsonPath("$.priceTableName")
            .value(is(DEFAULT_PRICE_TABLE_NAME))
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
            .jsonPath("$.primaryInsurerPolicyEndDate")
            .value(is(DEFAULT_PRIMARY_INSURER_POLICY_END_DATE.toString()))
            .jsonPath("$.primaryInsurerContactName")
            .value(is(DEFAULT_PRIMARY_INSURER_CONTACT_NAME))
            .jsonPath("$.primaryClaimProgram")
            .value(is(DEFAULT_PRIMARY_CLAIM_PROGRAM))
            .jsonPath("$.secondaryClaimProgram")
            .value(is(DEFAULT_SECONDARY_CLAIM_PROGRAM))
            .jsonPath("$.tertiaryClaimProgram")
            .value(is(DEFAULT_TERTIARY_CLAIM_PROGRAM))
            .jsonPath("$.workersCompInsuredEmployer")
            .value(is(DEFAULT_WORKERS_COMP_INSURED_EMPLOYER))
            .jsonPath("$.workersCompPayerIdNumber")
            .value(is(DEFAULT_WORKERS_COMP_PAYER_ID_NUMBER))
            .jsonPath("$.workersCompPlanName")
            .value(is(DEFAULT_WORKERS_COMP_PLAN_NAME))
            .jsonPath("$.workersCompAdditionalDtls")
            .value(is(DEFAULT_WORKERS_COMP_ADDITIONAL_DTLS))
            .jsonPath("$.workersCompClaimFillingCode")
            .value(is(DEFAULT_WORKERS_COMP_CLAIM_FILLING_CODE))
            .jsonPath("$.workersCompTplCode")
            .value(is(DEFAULT_WORKERS_COMP_TPL_CODE))
            .jsonPath("$.workersCompTplName")
            .value(is(DEFAULT_WORKERS_COMP_TPL_NAME))
            .jsonPath("$.workersCompPropertyCasualtyAgencyClaimNo")
            .value(is(DEFAULT_WORKERS_COMP_PROPERTY_CASUALTY_AGENCY_CLAIM_NO))
            .jsonPath("$.workersCompCarrierId")
            .value(is(DEFAULT_WORKERS_COMP_CARRIER_ID))
            .jsonPath("$.workersCompEmployerAddressLine1")
            .value(is(DEFAULT_WORKERS_COMP_EMPLOYER_ADDRESS_LINE_1))
            .jsonPath("$.workersCompEmployerAddressLine2")
            .value(is(DEFAULT_WORKERS_COMP_EMPLOYER_ADDRESS_LINE_2))
            .jsonPath("$.workersCompEmployerCity")
            .value(is(DEFAULT_WORKERS_COMP_EMPLOYER_CITY))
            .jsonPath("$.workersCompEmployerState")
            .value(is(DEFAULT_WORKERS_COMP_EMPLOYER_STATE))
            .jsonPath("$.workersCompEmployerCountry")
            .value(is(DEFAULT_WORKERS_COMP_EMPLOYER_COUNTRY))
            .jsonPath("$.workersCompEmployerZip")
            .value(is(DEFAULT_WORKERS_COMP_EMPLOYER_ZIP))
            .jsonPath("$.workersCompEmployerContactNo1")
            .value(is(DEFAULT_WORKERS_COMP_EMPLOYER_CONTACT_NO_1))
            .jsonPath("$.workersCompEmployerContactNo2")
            .value(is(DEFAULT_WORKERS_COMP_EMPLOYER_CONTACT_NO_2))
            .jsonPath("$.workersCompEmployerFax")
            .value(is(DEFAULT_WORKERS_COMP_EMPLOYER_FAX))
            .jsonPath("$.workersCompEmployerEfax")
            .value(is(DEFAULT_WORKERS_COMP_EMPLOYER_EFAX))
            .jsonPath("$.workersCompEmployerEmail")
            .value(is(DEFAULT_WORKERS_COMP_EMPLOYER_EMAIL))
            .jsonPath("$.workersCompRelationship")
            .value(is(DEFAULT_WORKERS_COMP_RELATIONSHIP))
            .jsonPath("$.workersCompModeOfContact")
            .value(is(DEFAULT_WORKERS_COMP_MODE_OF_CONTACT));
    }

    @Test
    void getNonExistingSalesOrderInsuranceDetails() {
        // Get the salesOrderInsuranceDetails
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingSalesOrderInsuranceDetails() throws Exception {
        // Initialize the database
        salesOrderInsuranceDetailsRepository.save(salesOrderInsuranceDetails).block();

        int databaseSizeBeforeUpdate = salesOrderInsuranceDetailsRepository.findAll().collectList().block().size();

        // Update the salesOrderInsuranceDetails
        SalesOrderInsuranceDetails updatedSalesOrderInsuranceDetails = salesOrderInsuranceDetailsRepository
            .findById(salesOrderInsuranceDetails.getSalesOrderInsuranceDetailsId())
            .block();
        updatedSalesOrderInsuranceDetails
            .salesOrderId(UPDATED_SALES_ORDER_ID)
            .patientId(UPDATED_PATIENT_ID)
            .primaryInsurerId(UPDATED_PRIMARY_INSURER_ID)
            .primaryInsurerName(UPDATED_PRIMARY_INSURER_NAME)
            .primaryInsurerPolicyNo(UPDATED_PRIMARY_INSURER_POLICY_NO)
            .primaryInsurerPatientIdNumber(UPDATED_PRIMARY_INSURER_PATIENT_ID_NUMBER)
            .primaryInsurerEffectiveDate(UPDATED_PRIMARY_INSURER_EFFECTIVE_DATE)
            .primaryInsurerVerificationStatus(UPDATED_PRIMARY_INSURER_VERIFICATION_STATUS)
            .primaryInsurerVerificationDate(UPDATED_PRIMARY_INSURER_VERIFICATION_DATE)
            .primaryInsurerPayPercentage(UPDATED_PRIMARY_INSURER_PAY_PERCENTAGE)
            .primaryBox10D(UPDATED_PRIMARY_BOX_10_D)
            .primaryBox19(UPDATED_PRIMARY_BOX_19)
            .primaryBox24Ia(UPDATED_PRIMARY_BOX_24_IA)
            .primaryBox24Ja(UPDATED_PRIMARY_BOX_24_JA)
            .primaryBox24Jb(UPDATED_PRIMARY_BOX_24_JB)
            .primaryIncludeBox24Jbstatus(UPDATED_PRIMARY_INCLUDE_BOX_24_JBSTATUS)
            .primaryIncludePayerSalesOrderStatus(UPDATED_PRIMARY_INCLUDE_PAYER_SALES_ORDER_STATUS)
            .primaryWaitForPreviousPayerBeforeBillingStatus(UPDATED_PRIMARY_WAIT_FOR_PREVIOUS_PAYER_BEFORE_BILLING_STATUS)
            .primaryPayPercentageStatus(UPDATED_PRIMARY_PAY_PERCENTAGE_STATUS)
            .secondaryInsurerId(UPDATED_SECONDARY_INSURER_ID)
            .secondaryInsurerName(UPDATED_SECONDARY_INSURER_NAME)
            .secondaryInsurerPolicyNo(UPDATED_SECONDARY_INSURER_POLICY_NO)
            .secondaryInsurerPatientIdNumber(UPDATED_SECONDARY_INSURER_PATIENT_ID_NUMBER)
            .secondaryInsurerEffectiveDate(UPDATED_SECONDARY_INSURER_EFFECTIVE_DATE)
            .secondaryInsurerVerificationStatus(UPDATED_SECONDARY_INSURER_VERIFICATION_STATUS)
            .secondaryInsurerVerificationDate(UPDATED_SECONDARY_INSURER_VERIFICATION_DATE)
            .secondaryInsurerPayPercentage(UPDATED_SECONDARY_INSURER_PAY_PERCENTAGE)
            .secondaryBox10D(UPDATED_SECONDARY_BOX_10_D)
            .secondaryBox19(UPDATED_SECONDARY_BOX_19)
            .secondaryBox24Ia(UPDATED_SECONDARY_BOX_24_IA)
            .secondaryBox24Ja(UPDATED_SECONDARY_BOX_24_JA)
            .secondaryBox24Jb(UPDATED_SECONDARY_BOX_24_JB)
            .secondaryIncludeBox24JbStatus(UPDATED_SECONDARY_INCLUDE_BOX_24_JB_STATUS)
            .secondaryIncludePayerSalesOrderStatus(UPDATED_SECONDARY_INCLUDE_PAYER_SALES_ORDER_STATUS)
            .secondaryWaitPreviousPayerBefrBillingStatus(UPDATED_SECONDARY_WAIT_PREVIOUS_PAYER_BEFR_BILLING_STATUS)
            .secondaryPayPercentageStatus(UPDATED_SECONDARY_PAY_PERCENTAGE_STATUS)
            .tertiaryInsurerId(UPDATED_TERTIARY_INSURER_ID)
            .tertiaryInsurerName(UPDATED_TERTIARY_INSURER_NAME)
            .tertiaryInsurerPolicyno(UPDATED_TERTIARY_INSURER_POLICYNO)
            .tertiaryInsurerPatientIdNumber(UPDATED_TERTIARY_INSURER_PATIENT_ID_NUMBER)
            .tertiaryInsurerEffectiveDate(UPDATED_TERTIARY_INSURER_EFFECTIVE_DATE)
            .tertiaryInsurerVerificationStatus(UPDATED_TERTIARY_INSURER_VERIFICATION_STATUS)
            .tertiaryInsurerVerificationDate(UPDATED_TERTIARY_INSURER_VERIFICATION_DATE)
            .tertiaryInsurerPayPercentage(UPDATED_TERTIARY_INSURER_PAY_PERCENTAGE)
            .tertiaryBox10D(UPDATED_TERTIARY_BOX_10_D)
            .tertiaryBox19(UPDATED_TERTIARY_BOX_19)
            .tertiaryBox24Ia(UPDATED_TERTIARY_BOX_24_IA)
            .tertiaryBox24Ja(UPDATED_TERTIARY_BOX_24_JA)
            .tertiaryBox24Jb(UPDATED_TERTIARY_BOX_24_JB)
            .tertiaryIncludeBox24JbStatus(UPDATED_TERTIARY_INCLUDE_BOX_24_JB_STATUS)
            .tertiaryIncludePayerInSalesOrderStatus(UPDATED_TERTIARY_INCLUDE_PAYER_IN_SALES_ORDER_STATUS)
            .tertiaryWaitPreviousPayerBeforeBillingStatus(UPDATED_TERTIARY_WAIT_PREVIOUS_PAYER_BEFORE_BILLING_STATUS)
            .tertiaryPayPercentageStatus(UPDATED_TERTIARY_PAY_PERCENTAGE_STATUS)
            .insuranceVerificationStatus(UPDATED_INSURANCE_VERIFICATION_STATUS)
            .coverageVerificationStatus(UPDATED_COVERAGE_VERIFICATION_STATUS)
            .excludeFromEligibilityCheckStatus(UPDATED_EXCLUDE_FROM_ELIGIBILITY_CHECK_STATUS)
            .patientPayPercentage(UPDATED_PATIENT_PAY_PERCENTAGE)
            .patientIncludeThisPayorInSalesOrderStatus(UPDATED_PATIENT_INCLUDE_THIS_PAYOR_IN_SALES_ORDER_STATUS)
            .patientWaitForPreviousPayerBeforeBillingStatus(UPDATED_PATIENT_WAIT_FOR_PREVIOUS_PAYER_BEFORE_BILLING_STATUS)
            .workersCompDateOfOnset(UPDATED_WORKERS_COMP_DATE_OF_ONSET)
            .workersCompInjuryRelatedEmploymentStatus(UPDATED_WORKERS_COMP_INJURY_RELATED_EMPLOYMENT_STATUS)
            .workersCompInjuryRelatedAutoAccidentStatus(UPDATED_WORKERS_COMP_INJURY_RELATED_AUTO_ACCIDENT_STATUS)
            .workersCompAutoAccidentStateCode(UPDATED_WORKERS_COMP_AUTO_ACCIDENT_STATE_CODE)
            .workersCompInjuryRelatedToOtherAccidentStatus(UPDATED_WORKERS_COMP_INJURY_RELATED_TO_OTHER_ACCIDENT_STATUS)
            .eclaimsAttachmentStatus(UPDATED_ECLAIMS_ATTACHMENT_STATUS)
            .attachmentNumber(UPDATED_ATTACHMENT_NUMBER)
            .typeCode(UPDATED_TYPE_CODE)
            .transactionCode(UPDATED_TRANSACTION_CODE)
            .claimsNoteType(UPDATED_CLAIMS_NOTE_TYPE)
            .claimsNote(UPDATED_CLAIMS_NOTE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .status(UPDATED_STATUS)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .salesOrderNo(UPDATED_SALES_ORDER_NO)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .primaryInsuranceGroupId(UPDATED_PRIMARY_INSURANCE_GROUP_ID)
            .primaryInsuranceGroupName(UPDATED_PRIMARY_INSURANCE_GROUP_NAME)
            .secondaryInsuranceGroupId(UPDATED_SECONDARY_INSURANCE_GROUP_ID)
            .secondaryInsuranceGroupName(UPDATED_SECONDARY_INSURANCE_GROUP_NAME)
            .tertiaryInsuranceGroupId(UPDATED_TERTIARY_INSURANCE_GROUP_ID)
            .tertiaryInsuranceGroupName(UPDATED_TERTIARY_INSURANCE_GROUP_NAME)
            .primaryInsurancePlanId(UPDATED_PRIMARY_INSURANCE_PLAN_ID)
            .primaryInsurancePlanType(UPDATED_PRIMARY_INSURANCE_PLAN_TYPE)
            .secondaryInsurancePlanId(UPDATED_SECONDARY_INSURANCE_PLAN_ID)
            .secondaryInsurancePlanType(UPDATED_SECONDARY_INSURANCE_PLAN_TYPE)
            .tertiaryInsurancePlanId(UPDATED_TERTIARY_INSURANCE_PLAN_ID)
            .tertiaryInsurancePlanType(UPDATED_TERTIARY_INSURANCE_PLAN_TYPE)
            .salesOrderInsuranceDetailsUuid(UPDATED_SALES_ORDER_INSURANCE_DETAILS_UUID)
            .primaryInsurancePayerId(UPDATED_PRIMARY_INSURANCE_PAYER_ID)
            .secondaryInsurancePayerId(UPDATED_SECONDARY_INSURANCE_PAYER_ID)
            .tertiaryInsurancePayerId(UPDATED_TERTIARY_INSURANCE_PAYER_ID)
            .primaryInsuranceIndicatorCode(UPDATED_PRIMARY_INSURANCE_INDICATOR_CODE)
            .secondaryInsuranceIndicatorCode(UPDATED_SECONDARY_INSURANCE_INDICATOR_CODE)
            .tertiaryInsuranceIndicatorCode(UPDATED_TERTIARY_INSURANCE_INDICATOR_CODE)
            .priceTableId(UPDATED_PRICE_TABLE_ID)
            .priceTableName(UPDATED_PRICE_TABLE_NAME)
            .primaryInsurerAddressLine1(UPDATED_PRIMARY_INSURER_ADDRESS_LINE_1)
            .primaryInsurerAddressLine2(UPDATED_PRIMARY_INSURER_ADDRESS_LINE_2)
            .primaryInsurerCity(UPDATED_PRIMARY_INSURER_CITY)
            .primaryInsurerState(UPDATED_PRIMARY_INSURER_STATE)
            .primaryInsurerZip(UPDATED_PRIMARY_INSURER_ZIP)
            .primaryInsurerContact1(UPDATED_PRIMARY_INSURER_CONTACT_1)
            .primaryInsurerFax(UPDATED_PRIMARY_INSURER_FAX)
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
            .primaryInsurerPolicyEndDate(UPDATED_PRIMARY_INSURER_POLICY_END_DATE)
            .primaryInsurerContactName(UPDATED_PRIMARY_INSURER_CONTACT_NAME)
            .primaryClaimProgram(UPDATED_PRIMARY_CLAIM_PROGRAM)
            .secondaryClaimProgram(UPDATED_SECONDARY_CLAIM_PROGRAM)
            .tertiaryClaimProgram(UPDATED_TERTIARY_CLAIM_PROGRAM)
            .workersCompInsuredEmployer(UPDATED_WORKERS_COMP_INSURED_EMPLOYER)
            .workersCompPayerIdNumber(UPDATED_WORKERS_COMP_PAYER_ID_NUMBER)
            .workersCompPlanName(UPDATED_WORKERS_COMP_PLAN_NAME)
            .workersCompAdditionalDtls(UPDATED_WORKERS_COMP_ADDITIONAL_DTLS)
            .workersCompClaimFillingCode(UPDATED_WORKERS_COMP_CLAIM_FILLING_CODE)
            .workersCompTplCode(UPDATED_WORKERS_COMP_TPL_CODE)
            .workersCompTplName(UPDATED_WORKERS_COMP_TPL_NAME)
            .workersCompPropertyCasualtyAgencyClaimNo(UPDATED_WORKERS_COMP_PROPERTY_CASUALTY_AGENCY_CLAIM_NO)
            .workersCompCarrierId(UPDATED_WORKERS_COMP_CARRIER_ID)
            .workersCompEmployerAddressLine1(UPDATED_WORKERS_COMP_EMPLOYER_ADDRESS_LINE_1)
            .workersCompEmployerAddressLine2(UPDATED_WORKERS_COMP_EMPLOYER_ADDRESS_LINE_2)
            .workersCompEmployerCity(UPDATED_WORKERS_COMP_EMPLOYER_CITY)
            .workersCompEmployerState(UPDATED_WORKERS_COMP_EMPLOYER_STATE)
            .workersCompEmployerCountry(UPDATED_WORKERS_COMP_EMPLOYER_COUNTRY)
            .workersCompEmployerZip(UPDATED_WORKERS_COMP_EMPLOYER_ZIP)
            .workersCompEmployerContactNo1(UPDATED_WORKERS_COMP_EMPLOYER_CONTACT_NO_1)
            .workersCompEmployerContactNo2(UPDATED_WORKERS_COMP_EMPLOYER_CONTACT_NO_2)
            .workersCompEmployerFax(UPDATED_WORKERS_COMP_EMPLOYER_FAX)
            .workersCompEmployerEfax(UPDATED_WORKERS_COMP_EMPLOYER_EFAX)
            .workersCompEmployerEmail(UPDATED_WORKERS_COMP_EMPLOYER_EMAIL)
            .workersCompRelationship(UPDATED_WORKERS_COMP_RELATIONSHIP)
            .workersCompModeOfContact(UPDATED_WORKERS_COMP_MODE_OF_CONTACT);
        SalesOrderInsuranceDetailsDTO salesOrderInsuranceDetailsDTO = salesOrderInsuranceDetailsMapper.toDto(
            updatedSalesOrderInsuranceDetails
        );

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, salesOrderInsuranceDetailsDTO.getSalesOrderInsuranceDetailsId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderInsuranceDetailsDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SalesOrderInsuranceDetails in the database
        List<SalesOrderInsuranceDetails> salesOrderInsuranceDetailsList = salesOrderInsuranceDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderInsuranceDetailsList).hasSize(databaseSizeBeforeUpdate);
        SalesOrderInsuranceDetails testSalesOrderInsuranceDetails = salesOrderInsuranceDetailsList.get(
            salesOrderInsuranceDetailsList.size() - 1
        );
        assertThat(testSalesOrderInsuranceDetails.getSalesOrderId()).isEqualTo(UPDATED_SALES_ORDER_ID);
        assertThat(testSalesOrderInsuranceDetails.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerId()).isEqualTo(UPDATED_PRIMARY_INSURER_ID);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerName()).isEqualTo(UPDATED_PRIMARY_INSURER_NAME);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerPolicyNo()).isEqualTo(UPDATED_PRIMARY_INSURER_POLICY_NO);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerPatientIdNumber()).isEqualTo(UPDATED_PRIMARY_INSURER_PATIENT_ID_NUMBER);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerEffectiveDate()).isEqualTo(UPDATED_PRIMARY_INSURER_EFFECTIVE_DATE);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerVerificationStatus())
            .isEqualTo(UPDATED_PRIMARY_INSURER_VERIFICATION_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerVerificationDate()).isEqualTo(UPDATED_PRIMARY_INSURER_VERIFICATION_DATE);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerPayPercentage()).isEqualTo(UPDATED_PRIMARY_INSURER_PAY_PERCENTAGE);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryBox10D()).isEqualTo(UPDATED_PRIMARY_BOX_10_D);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryBox19()).isEqualTo(UPDATED_PRIMARY_BOX_19);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryBox24Ia()).isEqualTo(UPDATED_PRIMARY_BOX_24_IA);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryBox24Ja()).isEqualTo(UPDATED_PRIMARY_BOX_24_JA);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryBox24Jb()).isEqualTo(UPDATED_PRIMARY_BOX_24_JB);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryIncludeBox24Jbstatus()).isEqualTo(UPDATED_PRIMARY_INCLUDE_BOX_24_JBSTATUS);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryIncludePayerSalesOrderStatus())
            .isEqualTo(UPDATED_PRIMARY_INCLUDE_PAYER_SALES_ORDER_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryWaitForPreviousPayerBeforeBillingStatus())
            .isEqualTo(UPDATED_PRIMARY_WAIT_FOR_PREVIOUS_PAYER_BEFORE_BILLING_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryPayPercentageStatus()).isEqualTo(UPDATED_PRIMARY_PAY_PERCENTAGE_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerId()).isEqualTo(UPDATED_SECONDARY_INSURER_ID);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerName()).isEqualTo(UPDATED_SECONDARY_INSURER_NAME);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerPolicyNo()).isEqualTo(UPDATED_SECONDARY_INSURER_POLICY_NO);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerPatientIdNumber())
            .isEqualTo(UPDATED_SECONDARY_INSURER_PATIENT_ID_NUMBER);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerEffectiveDate()).isEqualTo(UPDATED_SECONDARY_INSURER_EFFECTIVE_DATE);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerVerificationStatus())
            .isEqualTo(UPDATED_SECONDARY_INSURER_VERIFICATION_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerVerificationDate())
            .isEqualTo(UPDATED_SECONDARY_INSURER_VERIFICATION_DATE);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerPayPercentage()).isEqualTo(UPDATED_SECONDARY_INSURER_PAY_PERCENTAGE);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryBox10D()).isEqualTo(UPDATED_SECONDARY_BOX_10_D);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryBox19()).isEqualTo(UPDATED_SECONDARY_BOX_19);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryBox24Ia()).isEqualTo(UPDATED_SECONDARY_BOX_24_IA);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryBox24Ja()).isEqualTo(UPDATED_SECONDARY_BOX_24_JA);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryBox24Jb()).isEqualTo(UPDATED_SECONDARY_BOX_24_JB);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryIncludeBox24JbStatus()).isEqualTo(UPDATED_SECONDARY_INCLUDE_BOX_24_JB_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryIncludePayerSalesOrderStatus())
            .isEqualTo(UPDATED_SECONDARY_INCLUDE_PAYER_SALES_ORDER_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryWaitPreviousPayerBefrBillingStatus())
            .isEqualTo(UPDATED_SECONDARY_WAIT_PREVIOUS_PAYER_BEFR_BILLING_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryPayPercentageStatus()).isEqualTo(UPDATED_SECONDARY_PAY_PERCENTAGE_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerId()).isEqualTo(UPDATED_TERTIARY_INSURER_ID);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerName()).isEqualTo(UPDATED_TERTIARY_INSURER_NAME);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerPolicyno()).isEqualTo(UPDATED_TERTIARY_INSURER_POLICYNO);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerPatientIdNumber())
            .isEqualTo(UPDATED_TERTIARY_INSURER_PATIENT_ID_NUMBER);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerEffectiveDate()).isEqualTo(UPDATED_TERTIARY_INSURER_EFFECTIVE_DATE);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerVerificationStatus())
            .isEqualTo(UPDATED_TERTIARY_INSURER_VERIFICATION_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerVerificationDate())
            .isEqualTo(UPDATED_TERTIARY_INSURER_VERIFICATION_DATE);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerPayPercentage()).isEqualTo(UPDATED_TERTIARY_INSURER_PAY_PERCENTAGE);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryBox10D()).isEqualTo(UPDATED_TERTIARY_BOX_10_D);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryBox19()).isEqualTo(UPDATED_TERTIARY_BOX_19);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryBox24Ia()).isEqualTo(UPDATED_TERTIARY_BOX_24_IA);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryBox24Ja()).isEqualTo(UPDATED_TERTIARY_BOX_24_JA);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryBox24Jb()).isEqualTo(UPDATED_TERTIARY_BOX_24_JB);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryIncludeBox24JbStatus()).isEqualTo(UPDATED_TERTIARY_INCLUDE_BOX_24_JB_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryIncludePayerInSalesOrderStatus())
            .isEqualTo(UPDATED_TERTIARY_INCLUDE_PAYER_IN_SALES_ORDER_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryWaitPreviousPayerBeforeBillingStatus())
            .isEqualTo(UPDATED_TERTIARY_WAIT_PREVIOUS_PAYER_BEFORE_BILLING_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryPayPercentageStatus()).isEqualTo(UPDATED_TERTIARY_PAY_PERCENTAGE_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getInsuranceVerificationStatus()).isEqualTo(UPDATED_INSURANCE_VERIFICATION_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getCoverageVerificationStatus()).isEqualTo(UPDATED_COVERAGE_VERIFICATION_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getExcludeFromEligibilityCheckStatus())
            .isEqualTo(UPDATED_EXCLUDE_FROM_ELIGIBILITY_CHECK_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getPatientPayPercentage()).isEqualTo(UPDATED_PATIENT_PAY_PERCENTAGE);
        assertThat(testSalesOrderInsuranceDetails.getPatientIncludeThisPayorInSalesOrderStatus())
            .isEqualTo(UPDATED_PATIENT_INCLUDE_THIS_PAYOR_IN_SALES_ORDER_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getPatientWaitForPreviousPayerBeforeBillingStatus())
            .isEqualTo(UPDATED_PATIENT_WAIT_FOR_PREVIOUS_PAYER_BEFORE_BILLING_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompDateOfOnset()).isEqualTo(UPDATED_WORKERS_COMP_DATE_OF_ONSET);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompInjuryRelatedEmploymentStatus())
            .isEqualTo(UPDATED_WORKERS_COMP_INJURY_RELATED_EMPLOYMENT_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompInjuryRelatedAutoAccidentStatus())
            .isEqualTo(UPDATED_WORKERS_COMP_INJURY_RELATED_AUTO_ACCIDENT_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompAutoAccidentStateCode())
            .isEqualTo(UPDATED_WORKERS_COMP_AUTO_ACCIDENT_STATE_CODE);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompInjuryRelatedToOtherAccidentStatus())
            .isEqualTo(UPDATED_WORKERS_COMP_INJURY_RELATED_TO_OTHER_ACCIDENT_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getEclaimsAttachmentStatus()).isEqualTo(UPDATED_ECLAIMS_ATTACHMENT_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getAttachmentNumber()).isEqualTo(UPDATED_ATTACHMENT_NUMBER);
        assertThat(testSalesOrderInsuranceDetails.getTypeCode()).isEqualTo(UPDATED_TYPE_CODE);
        assertThat(testSalesOrderInsuranceDetails.getTransactionCode()).isEqualTo(UPDATED_TRANSACTION_CODE);
        assertThat(testSalesOrderInsuranceDetails.getClaimsNoteType()).isEqualTo(UPDATED_CLAIMS_NOTE_TYPE);
        assertThat(testSalesOrderInsuranceDetails.getClaimsNote()).isEqualTo(UPDATED_CLAIMS_NOTE);
        assertThat(testSalesOrderInsuranceDetails.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testSalesOrderInsuranceDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSalesOrderInsuranceDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testSalesOrderInsuranceDetails.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testSalesOrderInsuranceDetails.getSalesOrderNo()).isEqualTo(UPDATED_SALES_ORDER_NO);
        assertThat(testSalesOrderInsuranceDetails.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testSalesOrderInsuranceDetails.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsuranceGroupId()).isEqualTo(UPDATED_PRIMARY_INSURANCE_GROUP_ID);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsuranceGroupName()).isEqualTo(UPDATED_PRIMARY_INSURANCE_GROUP_NAME);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsuranceGroupId()).isEqualTo(UPDATED_SECONDARY_INSURANCE_GROUP_ID);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsuranceGroupName()).isEqualTo(UPDATED_SECONDARY_INSURANCE_GROUP_NAME);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsuranceGroupId()).isEqualTo(UPDATED_TERTIARY_INSURANCE_GROUP_ID);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsuranceGroupName()).isEqualTo(UPDATED_TERTIARY_INSURANCE_GROUP_NAME);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurancePlanId()).isEqualTo(UPDATED_PRIMARY_INSURANCE_PLAN_ID);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurancePlanType()).isEqualTo(UPDATED_PRIMARY_INSURANCE_PLAN_TYPE);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurancePlanId()).isEqualTo(UPDATED_SECONDARY_INSURANCE_PLAN_ID);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurancePlanType()).isEqualTo(UPDATED_SECONDARY_INSURANCE_PLAN_TYPE);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurancePlanId()).isEqualTo(UPDATED_TERTIARY_INSURANCE_PLAN_ID);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurancePlanType()).isEqualTo(UPDATED_TERTIARY_INSURANCE_PLAN_TYPE);
        assertThat(testSalesOrderInsuranceDetails.getSalesOrderInsuranceDetailsUuid())
            .isEqualTo(UPDATED_SALES_ORDER_INSURANCE_DETAILS_UUID);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurancePayerId()).isEqualTo(UPDATED_PRIMARY_INSURANCE_PAYER_ID);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurancePayerId()).isEqualTo(UPDATED_SECONDARY_INSURANCE_PAYER_ID);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurancePayerId()).isEqualTo(UPDATED_TERTIARY_INSURANCE_PAYER_ID);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsuranceIndicatorCode()).isEqualTo(UPDATED_PRIMARY_INSURANCE_INDICATOR_CODE);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsuranceIndicatorCode())
            .isEqualTo(UPDATED_SECONDARY_INSURANCE_INDICATOR_CODE);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsuranceIndicatorCode()).isEqualTo(UPDATED_TERTIARY_INSURANCE_INDICATOR_CODE);
        assertThat(testSalesOrderInsuranceDetails.getPriceTableId()).isEqualTo(UPDATED_PRICE_TABLE_ID);
        assertThat(testSalesOrderInsuranceDetails.getPriceTableName()).isEqualTo(UPDATED_PRICE_TABLE_NAME);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerAddressLine1()).isEqualTo(UPDATED_PRIMARY_INSURER_ADDRESS_LINE_1);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerAddressLine2()).isEqualTo(UPDATED_PRIMARY_INSURER_ADDRESS_LINE_2);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerCity()).isEqualTo(UPDATED_PRIMARY_INSURER_CITY);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerState()).isEqualTo(UPDATED_PRIMARY_INSURER_STATE);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerZip()).isEqualTo(UPDATED_PRIMARY_INSURER_ZIP);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerContact1()).isEqualTo(UPDATED_PRIMARY_INSURER_CONTACT_1);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerFax()).isEqualTo(UPDATED_PRIMARY_INSURER_FAX);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerAddressLine1()).isEqualTo(UPDATED_SECONDARY_INSURER_ADDRESS_LINE_1);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerAddressLine2()).isEqualTo(UPDATED_SECONDARY_INSURER_ADDRESS_LINE_2);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerCity()).isEqualTo(UPDATED_SECONDARY_INSURER_CITY);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerState()).isEqualTo(UPDATED_SECONDARY_INSURER_STATE);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerZip()).isEqualTo(UPDATED_SECONDARY_INSURER_ZIP);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerContact1()).isEqualTo(UPDATED_SECONDARY_INSURER_CONTACT_1);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerFax()).isEqualTo(UPDATED_SECONDARY_INSURER_FAX);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerAddressLine1()).isEqualTo(UPDATED_TERTIARY_INSURER_ADDRESS_LINE_1);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerAddressLine2()).isEqualTo(UPDATED_TERTIARY_INSURER_ADDRESS_LINE_2);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerCity()).isEqualTo(UPDATED_TERTIARY_INSURER_CITY);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerState()).isEqualTo(UPDATED_TERTIARY_INSURER_STATE);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerZip()).isEqualTo(UPDATED_TERTIARY_INSURER_ZIP);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerContact1()).isEqualTo(UPDATED_TERTIARY_INSURER_CONTACT_1);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerFax()).isEqualTo(UPDATED_TERTIARY_INSURER_FAX);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerPolicyEndDate()).isEqualTo(UPDATED_PRIMARY_INSURER_POLICY_END_DATE);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerContactName()).isEqualTo(UPDATED_PRIMARY_INSURER_CONTACT_NAME);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryClaimProgram()).isEqualTo(UPDATED_PRIMARY_CLAIM_PROGRAM);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryClaimProgram()).isEqualTo(UPDATED_SECONDARY_CLAIM_PROGRAM);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryClaimProgram()).isEqualTo(UPDATED_TERTIARY_CLAIM_PROGRAM);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompInsuredEmployer()).isEqualTo(UPDATED_WORKERS_COMP_INSURED_EMPLOYER);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompPayerIdNumber()).isEqualTo(UPDATED_WORKERS_COMP_PAYER_ID_NUMBER);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompPlanName()).isEqualTo(UPDATED_WORKERS_COMP_PLAN_NAME);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompAdditionalDtls()).isEqualTo(UPDATED_WORKERS_COMP_ADDITIONAL_DTLS);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompClaimFillingCode()).isEqualTo(UPDATED_WORKERS_COMP_CLAIM_FILLING_CODE);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompTplCode()).isEqualTo(UPDATED_WORKERS_COMP_TPL_CODE);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompTplName()).isEqualTo(UPDATED_WORKERS_COMP_TPL_NAME);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompPropertyCasualtyAgencyClaimNo())
            .isEqualTo(UPDATED_WORKERS_COMP_PROPERTY_CASUALTY_AGENCY_CLAIM_NO);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompCarrierId()).isEqualTo(UPDATED_WORKERS_COMP_CARRIER_ID);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerAddressLine1())
            .isEqualTo(UPDATED_WORKERS_COMP_EMPLOYER_ADDRESS_LINE_1);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerAddressLine2())
            .isEqualTo(UPDATED_WORKERS_COMP_EMPLOYER_ADDRESS_LINE_2);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerCity()).isEqualTo(UPDATED_WORKERS_COMP_EMPLOYER_CITY);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerState()).isEqualTo(UPDATED_WORKERS_COMP_EMPLOYER_STATE);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerCountry()).isEqualTo(UPDATED_WORKERS_COMP_EMPLOYER_COUNTRY);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerZip()).isEqualTo(UPDATED_WORKERS_COMP_EMPLOYER_ZIP);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerContactNo1()).isEqualTo(UPDATED_WORKERS_COMP_EMPLOYER_CONTACT_NO_1);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerContactNo2()).isEqualTo(UPDATED_WORKERS_COMP_EMPLOYER_CONTACT_NO_2);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerFax()).isEqualTo(UPDATED_WORKERS_COMP_EMPLOYER_FAX);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerEfax()).isEqualTo(UPDATED_WORKERS_COMP_EMPLOYER_EFAX);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerEmail()).isEqualTo(UPDATED_WORKERS_COMP_EMPLOYER_EMAIL);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompRelationship()).isEqualTo(UPDATED_WORKERS_COMP_RELATIONSHIP);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompModeOfContact()).isEqualTo(UPDATED_WORKERS_COMP_MODE_OF_CONTACT);
    }

    @Test
    void putNonExistingSalesOrderInsuranceDetails() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderInsuranceDetailsRepository.findAll().collectList().block().size();
        salesOrderInsuranceDetails.setSalesOrderInsuranceDetailsId(count.incrementAndGet());

        // Create the SalesOrderInsuranceDetails
        SalesOrderInsuranceDetailsDTO salesOrderInsuranceDetailsDTO = salesOrderInsuranceDetailsMapper.toDto(salesOrderInsuranceDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, salesOrderInsuranceDetailsDTO.getSalesOrderInsuranceDetailsId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderInsuranceDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderInsuranceDetails in the database
        List<SalesOrderInsuranceDetails> salesOrderInsuranceDetailsList = salesOrderInsuranceDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderInsuranceDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchSalesOrderInsuranceDetails() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderInsuranceDetailsRepository.findAll().collectList().block().size();
        salesOrderInsuranceDetails.setSalesOrderInsuranceDetailsId(count.incrementAndGet());

        // Create the SalesOrderInsuranceDetails
        SalesOrderInsuranceDetailsDTO salesOrderInsuranceDetailsDTO = salesOrderInsuranceDetailsMapper.toDto(salesOrderInsuranceDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderInsuranceDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderInsuranceDetails in the database
        List<SalesOrderInsuranceDetails> salesOrderInsuranceDetailsList = salesOrderInsuranceDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderInsuranceDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamSalesOrderInsuranceDetails() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderInsuranceDetailsRepository.findAll().collectList().block().size();
        salesOrderInsuranceDetails.setSalesOrderInsuranceDetailsId(count.incrementAndGet());

        // Create the SalesOrderInsuranceDetails
        SalesOrderInsuranceDetailsDTO salesOrderInsuranceDetailsDTO = salesOrderInsuranceDetailsMapper.toDto(salesOrderInsuranceDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderInsuranceDetailsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the SalesOrderInsuranceDetails in the database
        List<SalesOrderInsuranceDetails> salesOrderInsuranceDetailsList = salesOrderInsuranceDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderInsuranceDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateSalesOrderInsuranceDetailsWithPatch() throws Exception {
        // Initialize the database
        salesOrderInsuranceDetailsRepository.save(salesOrderInsuranceDetails).block();

        int databaseSizeBeforeUpdate = salesOrderInsuranceDetailsRepository.findAll().collectList().block().size();

        // Update the salesOrderInsuranceDetails using partial update
        SalesOrderInsuranceDetails partialUpdatedSalesOrderInsuranceDetails = new SalesOrderInsuranceDetails();
        partialUpdatedSalesOrderInsuranceDetails.setSalesOrderInsuranceDetailsId(
            salesOrderInsuranceDetails.getSalesOrderInsuranceDetailsId()
        );

        partialUpdatedSalesOrderInsuranceDetails
            .salesOrderId(UPDATED_SALES_ORDER_ID)
            .patientId(UPDATED_PATIENT_ID)
            .primaryInsurerId(UPDATED_PRIMARY_INSURER_ID)
            .primaryInsurerPatientIdNumber(UPDATED_PRIMARY_INSURER_PATIENT_ID_NUMBER)
            .primaryInsurerVerificationStatus(UPDATED_PRIMARY_INSURER_VERIFICATION_STATUS)
            .primaryInsurerPayPercentage(UPDATED_PRIMARY_INSURER_PAY_PERCENTAGE)
            .primaryBox24Ia(UPDATED_PRIMARY_BOX_24_IA)
            .primaryBox24Ja(UPDATED_PRIMARY_BOX_24_JA)
            .primaryWaitForPreviousPayerBeforeBillingStatus(UPDATED_PRIMARY_WAIT_FOR_PREVIOUS_PAYER_BEFORE_BILLING_STATUS)
            .primaryPayPercentageStatus(UPDATED_PRIMARY_PAY_PERCENTAGE_STATUS)
            .secondaryInsurerId(UPDATED_SECONDARY_INSURER_ID)
            .secondaryInsurerName(UPDATED_SECONDARY_INSURER_NAME)
            .secondaryInsurerPatientIdNumber(UPDATED_SECONDARY_INSURER_PATIENT_ID_NUMBER)
            .secondaryInsurerEffectiveDate(UPDATED_SECONDARY_INSURER_EFFECTIVE_DATE)
            .secondaryInsurerVerificationStatus(UPDATED_SECONDARY_INSURER_VERIFICATION_STATUS)
            .secondaryInsurerVerificationDate(UPDATED_SECONDARY_INSURER_VERIFICATION_DATE)
            .secondaryInsurerPayPercentage(UPDATED_SECONDARY_INSURER_PAY_PERCENTAGE)
            .secondaryBox19(UPDATED_SECONDARY_BOX_19)
            .secondaryBox24Ja(UPDATED_SECONDARY_BOX_24_JA)
            .secondaryBox24Jb(UPDATED_SECONDARY_BOX_24_JB)
            .secondaryIncludeBox24JbStatus(UPDATED_SECONDARY_INCLUDE_BOX_24_JB_STATUS)
            .secondaryIncludePayerSalesOrderStatus(UPDATED_SECONDARY_INCLUDE_PAYER_SALES_ORDER_STATUS)
            .tertiaryInsurerId(UPDATED_TERTIARY_INSURER_ID)
            .tertiaryInsurerPolicyno(UPDATED_TERTIARY_INSURER_POLICYNO)
            .tertiaryInsurerPatientIdNumber(UPDATED_TERTIARY_INSURER_PATIENT_ID_NUMBER)
            .tertiaryInsurerEffectiveDate(UPDATED_TERTIARY_INSURER_EFFECTIVE_DATE)
            .tertiaryInsurerVerificationDate(UPDATED_TERTIARY_INSURER_VERIFICATION_DATE)
            .tertiaryBox24Ja(UPDATED_TERTIARY_BOX_24_JA)
            .tertiaryBox24Jb(UPDATED_TERTIARY_BOX_24_JB)
            .tertiaryIncludeBox24JbStatus(UPDATED_TERTIARY_INCLUDE_BOX_24_JB_STATUS)
            .insuranceVerificationStatus(UPDATED_INSURANCE_VERIFICATION_STATUS)
            .coverageVerificationStatus(UPDATED_COVERAGE_VERIFICATION_STATUS)
            .patientPayPercentage(UPDATED_PATIENT_PAY_PERCENTAGE)
            .patientIncludeThisPayorInSalesOrderStatus(UPDATED_PATIENT_INCLUDE_THIS_PAYOR_IN_SALES_ORDER_STATUS)
            .workersCompInjuryRelatedEmploymentStatus(UPDATED_WORKERS_COMP_INJURY_RELATED_EMPLOYMENT_STATUS)
            .workersCompInjuryRelatedAutoAccidentStatus(UPDATED_WORKERS_COMP_INJURY_RELATED_AUTO_ACCIDENT_STATUS)
            .eclaimsAttachmentStatus(UPDATED_ECLAIMS_ATTACHMENT_STATUS)
            .typeCode(UPDATED_TYPE_CODE)
            .claimsNote(UPDATED_CLAIMS_NOTE)
            .status(UPDATED_STATUS)
            .updatedDate(UPDATED_UPDATED_DATE)
            .salesOrderNo(UPDATED_SALES_ORDER_NO)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .primaryInsuranceGroupId(UPDATED_PRIMARY_INSURANCE_GROUP_ID)
            .primaryInsuranceGroupName(UPDATED_PRIMARY_INSURANCE_GROUP_NAME)
            .primaryInsurancePlanId(UPDATED_PRIMARY_INSURANCE_PLAN_ID)
            .secondaryInsurancePlanType(UPDATED_SECONDARY_INSURANCE_PLAN_TYPE)
            .tertiaryInsurancePlanId(UPDATED_TERTIARY_INSURANCE_PLAN_ID)
            .tertiaryInsurancePlanType(UPDATED_TERTIARY_INSURANCE_PLAN_TYPE)
            .salesOrderInsuranceDetailsUuid(UPDATED_SALES_ORDER_INSURANCE_DETAILS_UUID)
            .primaryInsuranceIndicatorCode(UPDATED_PRIMARY_INSURANCE_INDICATOR_CODE)
            .secondaryInsuranceIndicatorCode(UPDATED_SECONDARY_INSURANCE_INDICATOR_CODE)
            .priceTableId(UPDATED_PRICE_TABLE_ID)
            .priceTableName(UPDATED_PRICE_TABLE_NAME)
            .primaryInsurerAddressLine1(UPDATED_PRIMARY_INSURER_ADDRESS_LINE_1)
            .primaryInsurerAddressLine2(UPDATED_PRIMARY_INSURER_ADDRESS_LINE_2)
            .primaryInsurerState(UPDATED_PRIMARY_INSURER_STATE)
            .primaryInsurerFax(UPDATED_PRIMARY_INSURER_FAX)
            .secondaryInsurerState(UPDATED_SECONDARY_INSURER_STATE)
            .tertiaryInsurerAddressLine2(UPDATED_TERTIARY_INSURER_ADDRESS_LINE_2)
            .tertiaryInsurerState(UPDATED_TERTIARY_INSURER_STATE)
            .primaryClaimProgram(UPDATED_PRIMARY_CLAIM_PROGRAM)
            .secondaryClaimProgram(UPDATED_SECONDARY_CLAIM_PROGRAM)
            .tertiaryClaimProgram(UPDATED_TERTIARY_CLAIM_PROGRAM)
            .workersCompInsuredEmployer(UPDATED_WORKERS_COMP_INSURED_EMPLOYER)
            .workersCompPayerIdNumber(UPDATED_WORKERS_COMP_PAYER_ID_NUMBER)
            .workersCompPlanName(UPDATED_WORKERS_COMP_PLAN_NAME)
            .workersCompAdditionalDtls(UPDATED_WORKERS_COMP_ADDITIONAL_DTLS)
            .workersCompTplCode(UPDATED_WORKERS_COMP_TPL_CODE)
            .workersCompPropertyCasualtyAgencyClaimNo(UPDATED_WORKERS_COMP_PROPERTY_CASUALTY_AGENCY_CLAIM_NO)
            .workersCompCarrierId(UPDATED_WORKERS_COMP_CARRIER_ID)
            .workersCompEmployerAddressLine2(UPDATED_WORKERS_COMP_EMPLOYER_ADDRESS_LINE_2)
            .workersCompEmployerCity(UPDATED_WORKERS_COMP_EMPLOYER_CITY)
            .workersCompEmployerCountry(UPDATED_WORKERS_COMP_EMPLOYER_COUNTRY)
            .workersCompEmployerZip(UPDATED_WORKERS_COMP_EMPLOYER_ZIP)
            .workersCompEmployerContactNo1(UPDATED_WORKERS_COMP_EMPLOYER_CONTACT_NO_1)
            .workersCompEmployerContactNo2(UPDATED_WORKERS_COMP_EMPLOYER_CONTACT_NO_2)
            .workersCompModeOfContact(UPDATED_WORKERS_COMP_MODE_OF_CONTACT);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedSalesOrderInsuranceDetails.getSalesOrderInsuranceDetailsId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedSalesOrderInsuranceDetails))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SalesOrderInsuranceDetails in the database
        List<SalesOrderInsuranceDetails> salesOrderInsuranceDetailsList = salesOrderInsuranceDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderInsuranceDetailsList).hasSize(databaseSizeBeforeUpdate);
        SalesOrderInsuranceDetails testSalesOrderInsuranceDetails = salesOrderInsuranceDetailsList.get(
            salesOrderInsuranceDetailsList.size() - 1
        );
        assertThat(testSalesOrderInsuranceDetails.getSalesOrderId()).isEqualTo(UPDATED_SALES_ORDER_ID);
        assertThat(testSalesOrderInsuranceDetails.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerId()).isEqualTo(UPDATED_PRIMARY_INSURER_ID);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerName()).isEqualTo(DEFAULT_PRIMARY_INSURER_NAME);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerPolicyNo()).isEqualTo(DEFAULT_PRIMARY_INSURER_POLICY_NO);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerPatientIdNumber()).isEqualTo(UPDATED_PRIMARY_INSURER_PATIENT_ID_NUMBER);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerEffectiveDate()).isEqualTo(DEFAULT_PRIMARY_INSURER_EFFECTIVE_DATE);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerVerificationStatus())
            .isEqualTo(UPDATED_PRIMARY_INSURER_VERIFICATION_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerVerificationDate()).isEqualTo(DEFAULT_PRIMARY_INSURER_VERIFICATION_DATE);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerPayPercentage()).isEqualTo(UPDATED_PRIMARY_INSURER_PAY_PERCENTAGE);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryBox10D()).isEqualTo(DEFAULT_PRIMARY_BOX_10_D);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryBox19()).isEqualTo(DEFAULT_PRIMARY_BOX_19);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryBox24Ia()).isEqualTo(UPDATED_PRIMARY_BOX_24_IA);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryBox24Ja()).isEqualTo(UPDATED_PRIMARY_BOX_24_JA);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryBox24Jb()).isEqualTo(DEFAULT_PRIMARY_BOX_24_JB);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryIncludeBox24Jbstatus()).isEqualTo(DEFAULT_PRIMARY_INCLUDE_BOX_24_JBSTATUS);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryIncludePayerSalesOrderStatus())
            .isEqualTo(DEFAULT_PRIMARY_INCLUDE_PAYER_SALES_ORDER_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryWaitForPreviousPayerBeforeBillingStatus())
            .isEqualTo(UPDATED_PRIMARY_WAIT_FOR_PREVIOUS_PAYER_BEFORE_BILLING_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryPayPercentageStatus()).isEqualTo(UPDATED_PRIMARY_PAY_PERCENTAGE_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerId()).isEqualTo(UPDATED_SECONDARY_INSURER_ID);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerName()).isEqualTo(UPDATED_SECONDARY_INSURER_NAME);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerPolicyNo()).isEqualTo(DEFAULT_SECONDARY_INSURER_POLICY_NO);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerPatientIdNumber())
            .isEqualTo(UPDATED_SECONDARY_INSURER_PATIENT_ID_NUMBER);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerEffectiveDate()).isEqualTo(UPDATED_SECONDARY_INSURER_EFFECTIVE_DATE);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerVerificationStatus())
            .isEqualTo(UPDATED_SECONDARY_INSURER_VERIFICATION_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerVerificationDate())
            .isEqualTo(UPDATED_SECONDARY_INSURER_VERIFICATION_DATE);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerPayPercentage()).isEqualTo(UPDATED_SECONDARY_INSURER_PAY_PERCENTAGE);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryBox10D()).isEqualTo(DEFAULT_SECONDARY_BOX_10_D);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryBox19()).isEqualTo(UPDATED_SECONDARY_BOX_19);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryBox24Ia()).isEqualTo(DEFAULT_SECONDARY_BOX_24_IA);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryBox24Ja()).isEqualTo(UPDATED_SECONDARY_BOX_24_JA);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryBox24Jb()).isEqualTo(UPDATED_SECONDARY_BOX_24_JB);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryIncludeBox24JbStatus()).isEqualTo(UPDATED_SECONDARY_INCLUDE_BOX_24_JB_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryIncludePayerSalesOrderStatus())
            .isEqualTo(UPDATED_SECONDARY_INCLUDE_PAYER_SALES_ORDER_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryWaitPreviousPayerBefrBillingStatus())
            .isEqualTo(DEFAULT_SECONDARY_WAIT_PREVIOUS_PAYER_BEFR_BILLING_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryPayPercentageStatus()).isEqualTo(DEFAULT_SECONDARY_PAY_PERCENTAGE_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerId()).isEqualTo(UPDATED_TERTIARY_INSURER_ID);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerName()).isEqualTo(DEFAULT_TERTIARY_INSURER_NAME);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerPolicyno()).isEqualTo(UPDATED_TERTIARY_INSURER_POLICYNO);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerPatientIdNumber())
            .isEqualTo(UPDATED_TERTIARY_INSURER_PATIENT_ID_NUMBER);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerEffectiveDate()).isEqualTo(UPDATED_TERTIARY_INSURER_EFFECTIVE_DATE);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerVerificationStatus())
            .isEqualTo(DEFAULT_TERTIARY_INSURER_VERIFICATION_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerVerificationDate())
            .isEqualTo(UPDATED_TERTIARY_INSURER_VERIFICATION_DATE);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerPayPercentage()).isEqualTo(DEFAULT_TERTIARY_INSURER_PAY_PERCENTAGE);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryBox10D()).isEqualTo(DEFAULT_TERTIARY_BOX_10_D);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryBox19()).isEqualTo(DEFAULT_TERTIARY_BOX_19);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryBox24Ia()).isEqualTo(DEFAULT_TERTIARY_BOX_24_IA);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryBox24Ja()).isEqualTo(UPDATED_TERTIARY_BOX_24_JA);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryBox24Jb()).isEqualTo(UPDATED_TERTIARY_BOX_24_JB);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryIncludeBox24JbStatus()).isEqualTo(UPDATED_TERTIARY_INCLUDE_BOX_24_JB_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryIncludePayerInSalesOrderStatus())
            .isEqualTo(DEFAULT_TERTIARY_INCLUDE_PAYER_IN_SALES_ORDER_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryWaitPreviousPayerBeforeBillingStatus())
            .isEqualTo(DEFAULT_TERTIARY_WAIT_PREVIOUS_PAYER_BEFORE_BILLING_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryPayPercentageStatus()).isEqualTo(DEFAULT_TERTIARY_PAY_PERCENTAGE_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getInsuranceVerificationStatus()).isEqualTo(UPDATED_INSURANCE_VERIFICATION_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getCoverageVerificationStatus()).isEqualTo(UPDATED_COVERAGE_VERIFICATION_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getExcludeFromEligibilityCheckStatus())
            .isEqualTo(DEFAULT_EXCLUDE_FROM_ELIGIBILITY_CHECK_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getPatientPayPercentage()).isEqualTo(UPDATED_PATIENT_PAY_PERCENTAGE);
        assertThat(testSalesOrderInsuranceDetails.getPatientIncludeThisPayorInSalesOrderStatus())
            .isEqualTo(UPDATED_PATIENT_INCLUDE_THIS_PAYOR_IN_SALES_ORDER_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getPatientWaitForPreviousPayerBeforeBillingStatus())
            .isEqualTo(DEFAULT_PATIENT_WAIT_FOR_PREVIOUS_PAYER_BEFORE_BILLING_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompDateOfOnset()).isEqualTo(DEFAULT_WORKERS_COMP_DATE_OF_ONSET);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompInjuryRelatedEmploymentStatus())
            .isEqualTo(UPDATED_WORKERS_COMP_INJURY_RELATED_EMPLOYMENT_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompInjuryRelatedAutoAccidentStatus())
            .isEqualTo(UPDATED_WORKERS_COMP_INJURY_RELATED_AUTO_ACCIDENT_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompAutoAccidentStateCode())
            .isEqualTo(DEFAULT_WORKERS_COMP_AUTO_ACCIDENT_STATE_CODE);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompInjuryRelatedToOtherAccidentStatus())
            .isEqualTo(DEFAULT_WORKERS_COMP_INJURY_RELATED_TO_OTHER_ACCIDENT_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getEclaimsAttachmentStatus()).isEqualTo(UPDATED_ECLAIMS_ATTACHMENT_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getAttachmentNumber()).isEqualTo(DEFAULT_ATTACHMENT_NUMBER);
        assertThat(testSalesOrderInsuranceDetails.getTypeCode()).isEqualTo(UPDATED_TYPE_CODE);
        assertThat(testSalesOrderInsuranceDetails.getTransactionCode()).isEqualTo(DEFAULT_TRANSACTION_CODE);
        assertThat(testSalesOrderInsuranceDetails.getClaimsNoteType()).isEqualTo(DEFAULT_CLAIMS_NOTE_TYPE);
        assertThat(testSalesOrderInsuranceDetails.getClaimsNote()).isEqualTo(UPDATED_CLAIMS_NOTE);
        assertThat(testSalesOrderInsuranceDetails.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testSalesOrderInsuranceDetails.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testSalesOrderInsuranceDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testSalesOrderInsuranceDetails.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testSalesOrderInsuranceDetails.getSalesOrderNo()).isEqualTo(UPDATED_SALES_ORDER_NO);
        assertThat(testSalesOrderInsuranceDetails.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testSalesOrderInsuranceDetails.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsuranceGroupId()).isEqualTo(UPDATED_PRIMARY_INSURANCE_GROUP_ID);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsuranceGroupName()).isEqualTo(UPDATED_PRIMARY_INSURANCE_GROUP_NAME);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsuranceGroupId()).isEqualTo(DEFAULT_SECONDARY_INSURANCE_GROUP_ID);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsuranceGroupName()).isEqualTo(DEFAULT_SECONDARY_INSURANCE_GROUP_NAME);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsuranceGroupId()).isEqualTo(DEFAULT_TERTIARY_INSURANCE_GROUP_ID);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsuranceGroupName()).isEqualTo(DEFAULT_TERTIARY_INSURANCE_GROUP_NAME);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurancePlanId()).isEqualTo(UPDATED_PRIMARY_INSURANCE_PLAN_ID);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurancePlanType()).isEqualTo(DEFAULT_PRIMARY_INSURANCE_PLAN_TYPE);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurancePlanId()).isEqualTo(DEFAULT_SECONDARY_INSURANCE_PLAN_ID);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurancePlanType()).isEqualTo(UPDATED_SECONDARY_INSURANCE_PLAN_TYPE);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurancePlanId()).isEqualTo(UPDATED_TERTIARY_INSURANCE_PLAN_ID);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurancePlanType()).isEqualTo(UPDATED_TERTIARY_INSURANCE_PLAN_TYPE);
        assertThat(testSalesOrderInsuranceDetails.getSalesOrderInsuranceDetailsUuid())
            .isEqualTo(UPDATED_SALES_ORDER_INSURANCE_DETAILS_UUID);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurancePayerId()).isEqualTo(DEFAULT_PRIMARY_INSURANCE_PAYER_ID);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurancePayerId()).isEqualTo(DEFAULT_SECONDARY_INSURANCE_PAYER_ID);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurancePayerId()).isEqualTo(DEFAULT_TERTIARY_INSURANCE_PAYER_ID);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsuranceIndicatorCode()).isEqualTo(UPDATED_PRIMARY_INSURANCE_INDICATOR_CODE);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsuranceIndicatorCode())
            .isEqualTo(UPDATED_SECONDARY_INSURANCE_INDICATOR_CODE);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsuranceIndicatorCode()).isEqualTo(DEFAULT_TERTIARY_INSURANCE_INDICATOR_CODE);
        assertThat(testSalesOrderInsuranceDetails.getPriceTableId()).isEqualTo(UPDATED_PRICE_TABLE_ID);
        assertThat(testSalesOrderInsuranceDetails.getPriceTableName()).isEqualTo(UPDATED_PRICE_TABLE_NAME);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerAddressLine1()).isEqualTo(UPDATED_PRIMARY_INSURER_ADDRESS_LINE_1);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerAddressLine2()).isEqualTo(UPDATED_PRIMARY_INSURER_ADDRESS_LINE_2);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerCity()).isEqualTo(DEFAULT_PRIMARY_INSURER_CITY);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerState()).isEqualTo(UPDATED_PRIMARY_INSURER_STATE);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerZip()).isEqualTo(DEFAULT_PRIMARY_INSURER_ZIP);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerContact1()).isEqualTo(DEFAULT_PRIMARY_INSURER_CONTACT_1);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerFax()).isEqualTo(UPDATED_PRIMARY_INSURER_FAX);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerAddressLine1()).isEqualTo(DEFAULT_SECONDARY_INSURER_ADDRESS_LINE_1);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerAddressLine2()).isEqualTo(DEFAULT_SECONDARY_INSURER_ADDRESS_LINE_2);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerCity()).isEqualTo(DEFAULT_SECONDARY_INSURER_CITY);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerState()).isEqualTo(UPDATED_SECONDARY_INSURER_STATE);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerZip()).isEqualTo(DEFAULT_SECONDARY_INSURER_ZIP);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerContact1()).isEqualTo(DEFAULT_SECONDARY_INSURER_CONTACT_1);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerFax()).isEqualTo(DEFAULT_SECONDARY_INSURER_FAX);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerAddressLine1()).isEqualTo(DEFAULT_TERTIARY_INSURER_ADDRESS_LINE_1);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerAddressLine2()).isEqualTo(UPDATED_TERTIARY_INSURER_ADDRESS_LINE_2);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerCity()).isEqualTo(DEFAULT_TERTIARY_INSURER_CITY);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerState()).isEqualTo(UPDATED_TERTIARY_INSURER_STATE);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerZip()).isEqualTo(DEFAULT_TERTIARY_INSURER_ZIP);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerContact1()).isEqualTo(DEFAULT_TERTIARY_INSURER_CONTACT_1);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerFax()).isEqualTo(DEFAULT_TERTIARY_INSURER_FAX);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerPolicyEndDate()).isEqualTo(DEFAULT_PRIMARY_INSURER_POLICY_END_DATE);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerContactName()).isEqualTo(DEFAULT_PRIMARY_INSURER_CONTACT_NAME);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryClaimProgram()).isEqualTo(UPDATED_PRIMARY_CLAIM_PROGRAM);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryClaimProgram()).isEqualTo(UPDATED_SECONDARY_CLAIM_PROGRAM);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryClaimProgram()).isEqualTo(UPDATED_TERTIARY_CLAIM_PROGRAM);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompInsuredEmployer()).isEqualTo(UPDATED_WORKERS_COMP_INSURED_EMPLOYER);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompPayerIdNumber()).isEqualTo(UPDATED_WORKERS_COMP_PAYER_ID_NUMBER);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompPlanName()).isEqualTo(UPDATED_WORKERS_COMP_PLAN_NAME);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompAdditionalDtls()).isEqualTo(UPDATED_WORKERS_COMP_ADDITIONAL_DTLS);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompClaimFillingCode()).isEqualTo(DEFAULT_WORKERS_COMP_CLAIM_FILLING_CODE);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompTplCode()).isEqualTo(UPDATED_WORKERS_COMP_TPL_CODE);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompTplName()).isEqualTo(DEFAULT_WORKERS_COMP_TPL_NAME);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompPropertyCasualtyAgencyClaimNo())
            .isEqualTo(UPDATED_WORKERS_COMP_PROPERTY_CASUALTY_AGENCY_CLAIM_NO);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompCarrierId()).isEqualTo(UPDATED_WORKERS_COMP_CARRIER_ID);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerAddressLine1())
            .isEqualTo(DEFAULT_WORKERS_COMP_EMPLOYER_ADDRESS_LINE_1);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerAddressLine2())
            .isEqualTo(UPDATED_WORKERS_COMP_EMPLOYER_ADDRESS_LINE_2);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerCity()).isEqualTo(UPDATED_WORKERS_COMP_EMPLOYER_CITY);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerState()).isEqualTo(DEFAULT_WORKERS_COMP_EMPLOYER_STATE);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerCountry()).isEqualTo(UPDATED_WORKERS_COMP_EMPLOYER_COUNTRY);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerZip()).isEqualTo(UPDATED_WORKERS_COMP_EMPLOYER_ZIP);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerContactNo1()).isEqualTo(UPDATED_WORKERS_COMP_EMPLOYER_CONTACT_NO_1);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerContactNo2()).isEqualTo(UPDATED_WORKERS_COMP_EMPLOYER_CONTACT_NO_2);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerFax()).isEqualTo(DEFAULT_WORKERS_COMP_EMPLOYER_FAX);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerEfax()).isEqualTo(DEFAULT_WORKERS_COMP_EMPLOYER_EFAX);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerEmail()).isEqualTo(DEFAULT_WORKERS_COMP_EMPLOYER_EMAIL);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompRelationship()).isEqualTo(DEFAULT_WORKERS_COMP_RELATIONSHIP);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompModeOfContact()).isEqualTo(UPDATED_WORKERS_COMP_MODE_OF_CONTACT);
    }

    @Test
    void fullUpdateSalesOrderInsuranceDetailsWithPatch() throws Exception {
        // Initialize the database
        salesOrderInsuranceDetailsRepository.save(salesOrderInsuranceDetails).block();

        int databaseSizeBeforeUpdate = salesOrderInsuranceDetailsRepository.findAll().collectList().block().size();

        // Update the salesOrderInsuranceDetails using partial update
        SalesOrderInsuranceDetails partialUpdatedSalesOrderInsuranceDetails = new SalesOrderInsuranceDetails();
        partialUpdatedSalesOrderInsuranceDetails.setSalesOrderInsuranceDetailsId(
            salesOrderInsuranceDetails.getSalesOrderInsuranceDetailsId()
        );

        partialUpdatedSalesOrderInsuranceDetails
            .salesOrderId(UPDATED_SALES_ORDER_ID)
            .patientId(UPDATED_PATIENT_ID)
            .primaryInsurerId(UPDATED_PRIMARY_INSURER_ID)
            .primaryInsurerName(UPDATED_PRIMARY_INSURER_NAME)
            .primaryInsurerPolicyNo(UPDATED_PRIMARY_INSURER_POLICY_NO)
            .primaryInsurerPatientIdNumber(UPDATED_PRIMARY_INSURER_PATIENT_ID_NUMBER)
            .primaryInsurerEffectiveDate(UPDATED_PRIMARY_INSURER_EFFECTIVE_DATE)
            .primaryInsurerVerificationStatus(UPDATED_PRIMARY_INSURER_VERIFICATION_STATUS)
            .primaryInsurerVerificationDate(UPDATED_PRIMARY_INSURER_VERIFICATION_DATE)
            .primaryInsurerPayPercentage(UPDATED_PRIMARY_INSURER_PAY_PERCENTAGE)
            .primaryBox10D(UPDATED_PRIMARY_BOX_10_D)
            .primaryBox19(UPDATED_PRIMARY_BOX_19)
            .primaryBox24Ia(UPDATED_PRIMARY_BOX_24_IA)
            .primaryBox24Ja(UPDATED_PRIMARY_BOX_24_JA)
            .primaryBox24Jb(UPDATED_PRIMARY_BOX_24_JB)
            .primaryIncludeBox24Jbstatus(UPDATED_PRIMARY_INCLUDE_BOX_24_JBSTATUS)
            .primaryIncludePayerSalesOrderStatus(UPDATED_PRIMARY_INCLUDE_PAYER_SALES_ORDER_STATUS)
            .primaryWaitForPreviousPayerBeforeBillingStatus(UPDATED_PRIMARY_WAIT_FOR_PREVIOUS_PAYER_BEFORE_BILLING_STATUS)
            .primaryPayPercentageStatus(UPDATED_PRIMARY_PAY_PERCENTAGE_STATUS)
            .secondaryInsurerId(UPDATED_SECONDARY_INSURER_ID)
            .secondaryInsurerName(UPDATED_SECONDARY_INSURER_NAME)
            .secondaryInsurerPolicyNo(UPDATED_SECONDARY_INSURER_POLICY_NO)
            .secondaryInsurerPatientIdNumber(UPDATED_SECONDARY_INSURER_PATIENT_ID_NUMBER)
            .secondaryInsurerEffectiveDate(UPDATED_SECONDARY_INSURER_EFFECTIVE_DATE)
            .secondaryInsurerVerificationStatus(UPDATED_SECONDARY_INSURER_VERIFICATION_STATUS)
            .secondaryInsurerVerificationDate(UPDATED_SECONDARY_INSURER_VERIFICATION_DATE)
            .secondaryInsurerPayPercentage(UPDATED_SECONDARY_INSURER_PAY_PERCENTAGE)
            .secondaryBox10D(UPDATED_SECONDARY_BOX_10_D)
            .secondaryBox19(UPDATED_SECONDARY_BOX_19)
            .secondaryBox24Ia(UPDATED_SECONDARY_BOX_24_IA)
            .secondaryBox24Ja(UPDATED_SECONDARY_BOX_24_JA)
            .secondaryBox24Jb(UPDATED_SECONDARY_BOX_24_JB)
            .secondaryIncludeBox24JbStatus(UPDATED_SECONDARY_INCLUDE_BOX_24_JB_STATUS)
            .secondaryIncludePayerSalesOrderStatus(UPDATED_SECONDARY_INCLUDE_PAYER_SALES_ORDER_STATUS)
            .secondaryWaitPreviousPayerBefrBillingStatus(UPDATED_SECONDARY_WAIT_PREVIOUS_PAYER_BEFR_BILLING_STATUS)
            .secondaryPayPercentageStatus(UPDATED_SECONDARY_PAY_PERCENTAGE_STATUS)
            .tertiaryInsurerId(UPDATED_TERTIARY_INSURER_ID)
            .tertiaryInsurerName(UPDATED_TERTIARY_INSURER_NAME)
            .tertiaryInsurerPolicyno(UPDATED_TERTIARY_INSURER_POLICYNO)
            .tertiaryInsurerPatientIdNumber(UPDATED_TERTIARY_INSURER_PATIENT_ID_NUMBER)
            .tertiaryInsurerEffectiveDate(UPDATED_TERTIARY_INSURER_EFFECTIVE_DATE)
            .tertiaryInsurerVerificationStatus(UPDATED_TERTIARY_INSURER_VERIFICATION_STATUS)
            .tertiaryInsurerVerificationDate(UPDATED_TERTIARY_INSURER_VERIFICATION_DATE)
            .tertiaryInsurerPayPercentage(UPDATED_TERTIARY_INSURER_PAY_PERCENTAGE)
            .tertiaryBox10D(UPDATED_TERTIARY_BOX_10_D)
            .tertiaryBox19(UPDATED_TERTIARY_BOX_19)
            .tertiaryBox24Ia(UPDATED_TERTIARY_BOX_24_IA)
            .tertiaryBox24Ja(UPDATED_TERTIARY_BOX_24_JA)
            .tertiaryBox24Jb(UPDATED_TERTIARY_BOX_24_JB)
            .tertiaryIncludeBox24JbStatus(UPDATED_TERTIARY_INCLUDE_BOX_24_JB_STATUS)
            .tertiaryIncludePayerInSalesOrderStatus(UPDATED_TERTIARY_INCLUDE_PAYER_IN_SALES_ORDER_STATUS)
            .tertiaryWaitPreviousPayerBeforeBillingStatus(UPDATED_TERTIARY_WAIT_PREVIOUS_PAYER_BEFORE_BILLING_STATUS)
            .tertiaryPayPercentageStatus(UPDATED_TERTIARY_PAY_PERCENTAGE_STATUS)
            .insuranceVerificationStatus(UPDATED_INSURANCE_VERIFICATION_STATUS)
            .coverageVerificationStatus(UPDATED_COVERAGE_VERIFICATION_STATUS)
            .excludeFromEligibilityCheckStatus(UPDATED_EXCLUDE_FROM_ELIGIBILITY_CHECK_STATUS)
            .patientPayPercentage(UPDATED_PATIENT_PAY_PERCENTAGE)
            .patientIncludeThisPayorInSalesOrderStatus(UPDATED_PATIENT_INCLUDE_THIS_PAYOR_IN_SALES_ORDER_STATUS)
            .patientWaitForPreviousPayerBeforeBillingStatus(UPDATED_PATIENT_WAIT_FOR_PREVIOUS_PAYER_BEFORE_BILLING_STATUS)
            .workersCompDateOfOnset(UPDATED_WORKERS_COMP_DATE_OF_ONSET)
            .workersCompInjuryRelatedEmploymentStatus(UPDATED_WORKERS_COMP_INJURY_RELATED_EMPLOYMENT_STATUS)
            .workersCompInjuryRelatedAutoAccidentStatus(UPDATED_WORKERS_COMP_INJURY_RELATED_AUTO_ACCIDENT_STATUS)
            .workersCompAutoAccidentStateCode(UPDATED_WORKERS_COMP_AUTO_ACCIDENT_STATE_CODE)
            .workersCompInjuryRelatedToOtherAccidentStatus(UPDATED_WORKERS_COMP_INJURY_RELATED_TO_OTHER_ACCIDENT_STATUS)
            .eclaimsAttachmentStatus(UPDATED_ECLAIMS_ATTACHMENT_STATUS)
            .attachmentNumber(UPDATED_ATTACHMENT_NUMBER)
            .typeCode(UPDATED_TYPE_CODE)
            .transactionCode(UPDATED_TRANSACTION_CODE)
            .claimsNoteType(UPDATED_CLAIMS_NOTE_TYPE)
            .claimsNote(UPDATED_CLAIMS_NOTE)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .status(UPDATED_STATUS)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .salesOrderNo(UPDATED_SALES_ORDER_NO)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .primaryInsuranceGroupId(UPDATED_PRIMARY_INSURANCE_GROUP_ID)
            .primaryInsuranceGroupName(UPDATED_PRIMARY_INSURANCE_GROUP_NAME)
            .secondaryInsuranceGroupId(UPDATED_SECONDARY_INSURANCE_GROUP_ID)
            .secondaryInsuranceGroupName(UPDATED_SECONDARY_INSURANCE_GROUP_NAME)
            .tertiaryInsuranceGroupId(UPDATED_TERTIARY_INSURANCE_GROUP_ID)
            .tertiaryInsuranceGroupName(UPDATED_TERTIARY_INSURANCE_GROUP_NAME)
            .primaryInsurancePlanId(UPDATED_PRIMARY_INSURANCE_PLAN_ID)
            .primaryInsurancePlanType(UPDATED_PRIMARY_INSURANCE_PLAN_TYPE)
            .secondaryInsurancePlanId(UPDATED_SECONDARY_INSURANCE_PLAN_ID)
            .secondaryInsurancePlanType(UPDATED_SECONDARY_INSURANCE_PLAN_TYPE)
            .tertiaryInsurancePlanId(UPDATED_TERTIARY_INSURANCE_PLAN_ID)
            .tertiaryInsurancePlanType(UPDATED_TERTIARY_INSURANCE_PLAN_TYPE)
            .salesOrderInsuranceDetailsUuid(UPDATED_SALES_ORDER_INSURANCE_DETAILS_UUID)
            .primaryInsurancePayerId(UPDATED_PRIMARY_INSURANCE_PAYER_ID)
            .secondaryInsurancePayerId(UPDATED_SECONDARY_INSURANCE_PAYER_ID)
            .tertiaryInsurancePayerId(UPDATED_TERTIARY_INSURANCE_PAYER_ID)
            .primaryInsuranceIndicatorCode(UPDATED_PRIMARY_INSURANCE_INDICATOR_CODE)
            .secondaryInsuranceIndicatorCode(UPDATED_SECONDARY_INSURANCE_INDICATOR_CODE)
            .tertiaryInsuranceIndicatorCode(UPDATED_TERTIARY_INSURANCE_INDICATOR_CODE)
            .priceTableId(UPDATED_PRICE_TABLE_ID)
            .priceTableName(UPDATED_PRICE_TABLE_NAME)
            .primaryInsurerAddressLine1(UPDATED_PRIMARY_INSURER_ADDRESS_LINE_1)
            .primaryInsurerAddressLine2(UPDATED_PRIMARY_INSURER_ADDRESS_LINE_2)
            .primaryInsurerCity(UPDATED_PRIMARY_INSURER_CITY)
            .primaryInsurerState(UPDATED_PRIMARY_INSURER_STATE)
            .primaryInsurerZip(UPDATED_PRIMARY_INSURER_ZIP)
            .primaryInsurerContact1(UPDATED_PRIMARY_INSURER_CONTACT_1)
            .primaryInsurerFax(UPDATED_PRIMARY_INSURER_FAX)
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
            .primaryInsurerPolicyEndDate(UPDATED_PRIMARY_INSURER_POLICY_END_DATE)
            .primaryInsurerContactName(UPDATED_PRIMARY_INSURER_CONTACT_NAME)
            .primaryClaimProgram(UPDATED_PRIMARY_CLAIM_PROGRAM)
            .secondaryClaimProgram(UPDATED_SECONDARY_CLAIM_PROGRAM)
            .tertiaryClaimProgram(UPDATED_TERTIARY_CLAIM_PROGRAM)
            .workersCompInsuredEmployer(UPDATED_WORKERS_COMP_INSURED_EMPLOYER)
            .workersCompPayerIdNumber(UPDATED_WORKERS_COMP_PAYER_ID_NUMBER)
            .workersCompPlanName(UPDATED_WORKERS_COMP_PLAN_NAME)
            .workersCompAdditionalDtls(UPDATED_WORKERS_COMP_ADDITIONAL_DTLS)
            .workersCompClaimFillingCode(UPDATED_WORKERS_COMP_CLAIM_FILLING_CODE)
            .workersCompTplCode(UPDATED_WORKERS_COMP_TPL_CODE)
            .workersCompTplName(UPDATED_WORKERS_COMP_TPL_NAME)
            .workersCompPropertyCasualtyAgencyClaimNo(UPDATED_WORKERS_COMP_PROPERTY_CASUALTY_AGENCY_CLAIM_NO)
            .workersCompCarrierId(UPDATED_WORKERS_COMP_CARRIER_ID)
            .workersCompEmployerAddressLine1(UPDATED_WORKERS_COMP_EMPLOYER_ADDRESS_LINE_1)
            .workersCompEmployerAddressLine2(UPDATED_WORKERS_COMP_EMPLOYER_ADDRESS_LINE_2)
            .workersCompEmployerCity(UPDATED_WORKERS_COMP_EMPLOYER_CITY)
            .workersCompEmployerState(UPDATED_WORKERS_COMP_EMPLOYER_STATE)
            .workersCompEmployerCountry(UPDATED_WORKERS_COMP_EMPLOYER_COUNTRY)
            .workersCompEmployerZip(UPDATED_WORKERS_COMP_EMPLOYER_ZIP)
            .workersCompEmployerContactNo1(UPDATED_WORKERS_COMP_EMPLOYER_CONTACT_NO_1)
            .workersCompEmployerContactNo2(UPDATED_WORKERS_COMP_EMPLOYER_CONTACT_NO_2)
            .workersCompEmployerFax(UPDATED_WORKERS_COMP_EMPLOYER_FAX)
            .workersCompEmployerEfax(UPDATED_WORKERS_COMP_EMPLOYER_EFAX)
            .workersCompEmployerEmail(UPDATED_WORKERS_COMP_EMPLOYER_EMAIL)
            .workersCompRelationship(UPDATED_WORKERS_COMP_RELATIONSHIP)
            .workersCompModeOfContact(UPDATED_WORKERS_COMP_MODE_OF_CONTACT);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedSalesOrderInsuranceDetails.getSalesOrderInsuranceDetailsId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedSalesOrderInsuranceDetails))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SalesOrderInsuranceDetails in the database
        List<SalesOrderInsuranceDetails> salesOrderInsuranceDetailsList = salesOrderInsuranceDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderInsuranceDetailsList).hasSize(databaseSizeBeforeUpdate);
        SalesOrderInsuranceDetails testSalesOrderInsuranceDetails = salesOrderInsuranceDetailsList.get(
            salesOrderInsuranceDetailsList.size() - 1
        );
        assertThat(testSalesOrderInsuranceDetails.getSalesOrderId()).isEqualTo(UPDATED_SALES_ORDER_ID);
        assertThat(testSalesOrderInsuranceDetails.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerId()).isEqualTo(UPDATED_PRIMARY_INSURER_ID);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerName()).isEqualTo(UPDATED_PRIMARY_INSURER_NAME);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerPolicyNo()).isEqualTo(UPDATED_PRIMARY_INSURER_POLICY_NO);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerPatientIdNumber()).isEqualTo(UPDATED_PRIMARY_INSURER_PATIENT_ID_NUMBER);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerEffectiveDate()).isEqualTo(UPDATED_PRIMARY_INSURER_EFFECTIVE_DATE);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerVerificationStatus())
            .isEqualTo(UPDATED_PRIMARY_INSURER_VERIFICATION_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerVerificationDate()).isEqualTo(UPDATED_PRIMARY_INSURER_VERIFICATION_DATE);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerPayPercentage()).isEqualTo(UPDATED_PRIMARY_INSURER_PAY_PERCENTAGE);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryBox10D()).isEqualTo(UPDATED_PRIMARY_BOX_10_D);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryBox19()).isEqualTo(UPDATED_PRIMARY_BOX_19);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryBox24Ia()).isEqualTo(UPDATED_PRIMARY_BOX_24_IA);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryBox24Ja()).isEqualTo(UPDATED_PRIMARY_BOX_24_JA);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryBox24Jb()).isEqualTo(UPDATED_PRIMARY_BOX_24_JB);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryIncludeBox24Jbstatus()).isEqualTo(UPDATED_PRIMARY_INCLUDE_BOX_24_JBSTATUS);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryIncludePayerSalesOrderStatus())
            .isEqualTo(UPDATED_PRIMARY_INCLUDE_PAYER_SALES_ORDER_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryWaitForPreviousPayerBeforeBillingStatus())
            .isEqualTo(UPDATED_PRIMARY_WAIT_FOR_PREVIOUS_PAYER_BEFORE_BILLING_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryPayPercentageStatus()).isEqualTo(UPDATED_PRIMARY_PAY_PERCENTAGE_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerId()).isEqualTo(UPDATED_SECONDARY_INSURER_ID);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerName()).isEqualTo(UPDATED_SECONDARY_INSURER_NAME);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerPolicyNo()).isEqualTo(UPDATED_SECONDARY_INSURER_POLICY_NO);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerPatientIdNumber())
            .isEqualTo(UPDATED_SECONDARY_INSURER_PATIENT_ID_NUMBER);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerEffectiveDate()).isEqualTo(UPDATED_SECONDARY_INSURER_EFFECTIVE_DATE);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerVerificationStatus())
            .isEqualTo(UPDATED_SECONDARY_INSURER_VERIFICATION_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerVerificationDate())
            .isEqualTo(UPDATED_SECONDARY_INSURER_VERIFICATION_DATE);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerPayPercentage()).isEqualTo(UPDATED_SECONDARY_INSURER_PAY_PERCENTAGE);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryBox10D()).isEqualTo(UPDATED_SECONDARY_BOX_10_D);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryBox19()).isEqualTo(UPDATED_SECONDARY_BOX_19);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryBox24Ia()).isEqualTo(UPDATED_SECONDARY_BOX_24_IA);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryBox24Ja()).isEqualTo(UPDATED_SECONDARY_BOX_24_JA);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryBox24Jb()).isEqualTo(UPDATED_SECONDARY_BOX_24_JB);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryIncludeBox24JbStatus()).isEqualTo(UPDATED_SECONDARY_INCLUDE_BOX_24_JB_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryIncludePayerSalesOrderStatus())
            .isEqualTo(UPDATED_SECONDARY_INCLUDE_PAYER_SALES_ORDER_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryWaitPreviousPayerBefrBillingStatus())
            .isEqualTo(UPDATED_SECONDARY_WAIT_PREVIOUS_PAYER_BEFR_BILLING_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryPayPercentageStatus()).isEqualTo(UPDATED_SECONDARY_PAY_PERCENTAGE_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerId()).isEqualTo(UPDATED_TERTIARY_INSURER_ID);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerName()).isEqualTo(UPDATED_TERTIARY_INSURER_NAME);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerPolicyno()).isEqualTo(UPDATED_TERTIARY_INSURER_POLICYNO);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerPatientIdNumber())
            .isEqualTo(UPDATED_TERTIARY_INSURER_PATIENT_ID_NUMBER);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerEffectiveDate()).isEqualTo(UPDATED_TERTIARY_INSURER_EFFECTIVE_DATE);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerVerificationStatus())
            .isEqualTo(UPDATED_TERTIARY_INSURER_VERIFICATION_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerVerificationDate())
            .isEqualTo(UPDATED_TERTIARY_INSURER_VERIFICATION_DATE);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerPayPercentage()).isEqualTo(UPDATED_TERTIARY_INSURER_PAY_PERCENTAGE);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryBox10D()).isEqualTo(UPDATED_TERTIARY_BOX_10_D);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryBox19()).isEqualTo(UPDATED_TERTIARY_BOX_19);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryBox24Ia()).isEqualTo(UPDATED_TERTIARY_BOX_24_IA);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryBox24Ja()).isEqualTo(UPDATED_TERTIARY_BOX_24_JA);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryBox24Jb()).isEqualTo(UPDATED_TERTIARY_BOX_24_JB);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryIncludeBox24JbStatus()).isEqualTo(UPDATED_TERTIARY_INCLUDE_BOX_24_JB_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryIncludePayerInSalesOrderStatus())
            .isEqualTo(UPDATED_TERTIARY_INCLUDE_PAYER_IN_SALES_ORDER_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryWaitPreviousPayerBeforeBillingStatus())
            .isEqualTo(UPDATED_TERTIARY_WAIT_PREVIOUS_PAYER_BEFORE_BILLING_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryPayPercentageStatus()).isEqualTo(UPDATED_TERTIARY_PAY_PERCENTAGE_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getInsuranceVerificationStatus()).isEqualTo(UPDATED_INSURANCE_VERIFICATION_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getCoverageVerificationStatus()).isEqualTo(UPDATED_COVERAGE_VERIFICATION_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getExcludeFromEligibilityCheckStatus())
            .isEqualTo(UPDATED_EXCLUDE_FROM_ELIGIBILITY_CHECK_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getPatientPayPercentage()).isEqualTo(UPDATED_PATIENT_PAY_PERCENTAGE);
        assertThat(testSalesOrderInsuranceDetails.getPatientIncludeThisPayorInSalesOrderStatus())
            .isEqualTo(UPDATED_PATIENT_INCLUDE_THIS_PAYOR_IN_SALES_ORDER_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getPatientWaitForPreviousPayerBeforeBillingStatus())
            .isEqualTo(UPDATED_PATIENT_WAIT_FOR_PREVIOUS_PAYER_BEFORE_BILLING_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompDateOfOnset()).isEqualTo(UPDATED_WORKERS_COMP_DATE_OF_ONSET);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompInjuryRelatedEmploymentStatus())
            .isEqualTo(UPDATED_WORKERS_COMP_INJURY_RELATED_EMPLOYMENT_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompInjuryRelatedAutoAccidentStatus())
            .isEqualTo(UPDATED_WORKERS_COMP_INJURY_RELATED_AUTO_ACCIDENT_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompAutoAccidentStateCode())
            .isEqualTo(UPDATED_WORKERS_COMP_AUTO_ACCIDENT_STATE_CODE);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompInjuryRelatedToOtherAccidentStatus())
            .isEqualTo(UPDATED_WORKERS_COMP_INJURY_RELATED_TO_OTHER_ACCIDENT_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getEclaimsAttachmentStatus()).isEqualTo(UPDATED_ECLAIMS_ATTACHMENT_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getAttachmentNumber()).isEqualTo(UPDATED_ATTACHMENT_NUMBER);
        assertThat(testSalesOrderInsuranceDetails.getTypeCode()).isEqualTo(UPDATED_TYPE_CODE);
        assertThat(testSalesOrderInsuranceDetails.getTransactionCode()).isEqualTo(UPDATED_TRANSACTION_CODE);
        assertThat(testSalesOrderInsuranceDetails.getClaimsNoteType()).isEqualTo(UPDATED_CLAIMS_NOTE_TYPE);
        assertThat(testSalesOrderInsuranceDetails.getClaimsNote()).isEqualTo(UPDATED_CLAIMS_NOTE);
        assertThat(testSalesOrderInsuranceDetails.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testSalesOrderInsuranceDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSalesOrderInsuranceDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSalesOrderInsuranceDetails.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testSalesOrderInsuranceDetails.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testSalesOrderInsuranceDetails.getSalesOrderNo()).isEqualTo(UPDATED_SALES_ORDER_NO);
        assertThat(testSalesOrderInsuranceDetails.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testSalesOrderInsuranceDetails.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsuranceGroupId()).isEqualTo(UPDATED_PRIMARY_INSURANCE_GROUP_ID);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsuranceGroupName()).isEqualTo(UPDATED_PRIMARY_INSURANCE_GROUP_NAME);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsuranceGroupId()).isEqualTo(UPDATED_SECONDARY_INSURANCE_GROUP_ID);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsuranceGroupName()).isEqualTo(UPDATED_SECONDARY_INSURANCE_GROUP_NAME);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsuranceGroupId()).isEqualTo(UPDATED_TERTIARY_INSURANCE_GROUP_ID);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsuranceGroupName()).isEqualTo(UPDATED_TERTIARY_INSURANCE_GROUP_NAME);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurancePlanId()).isEqualTo(UPDATED_PRIMARY_INSURANCE_PLAN_ID);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurancePlanType()).isEqualTo(UPDATED_PRIMARY_INSURANCE_PLAN_TYPE);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurancePlanId()).isEqualTo(UPDATED_SECONDARY_INSURANCE_PLAN_ID);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurancePlanType()).isEqualTo(UPDATED_SECONDARY_INSURANCE_PLAN_TYPE);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurancePlanId()).isEqualTo(UPDATED_TERTIARY_INSURANCE_PLAN_ID);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurancePlanType()).isEqualTo(UPDATED_TERTIARY_INSURANCE_PLAN_TYPE);
        assertThat(testSalesOrderInsuranceDetails.getSalesOrderInsuranceDetailsUuid())
            .isEqualTo(UPDATED_SALES_ORDER_INSURANCE_DETAILS_UUID);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurancePayerId()).isEqualTo(UPDATED_PRIMARY_INSURANCE_PAYER_ID);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurancePayerId()).isEqualTo(UPDATED_SECONDARY_INSURANCE_PAYER_ID);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurancePayerId()).isEqualTo(UPDATED_TERTIARY_INSURANCE_PAYER_ID);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsuranceIndicatorCode()).isEqualTo(UPDATED_PRIMARY_INSURANCE_INDICATOR_CODE);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsuranceIndicatorCode())
            .isEqualTo(UPDATED_SECONDARY_INSURANCE_INDICATOR_CODE);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsuranceIndicatorCode()).isEqualTo(UPDATED_TERTIARY_INSURANCE_INDICATOR_CODE);
        assertThat(testSalesOrderInsuranceDetails.getPriceTableId()).isEqualTo(UPDATED_PRICE_TABLE_ID);
        assertThat(testSalesOrderInsuranceDetails.getPriceTableName()).isEqualTo(UPDATED_PRICE_TABLE_NAME);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerAddressLine1()).isEqualTo(UPDATED_PRIMARY_INSURER_ADDRESS_LINE_1);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerAddressLine2()).isEqualTo(UPDATED_PRIMARY_INSURER_ADDRESS_LINE_2);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerCity()).isEqualTo(UPDATED_PRIMARY_INSURER_CITY);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerState()).isEqualTo(UPDATED_PRIMARY_INSURER_STATE);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerZip()).isEqualTo(UPDATED_PRIMARY_INSURER_ZIP);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerContact1()).isEqualTo(UPDATED_PRIMARY_INSURER_CONTACT_1);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerFax()).isEqualTo(UPDATED_PRIMARY_INSURER_FAX);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerAddressLine1()).isEqualTo(UPDATED_SECONDARY_INSURER_ADDRESS_LINE_1);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerAddressLine2()).isEqualTo(UPDATED_SECONDARY_INSURER_ADDRESS_LINE_2);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerCity()).isEqualTo(UPDATED_SECONDARY_INSURER_CITY);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerState()).isEqualTo(UPDATED_SECONDARY_INSURER_STATE);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerZip()).isEqualTo(UPDATED_SECONDARY_INSURER_ZIP);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerContact1()).isEqualTo(UPDATED_SECONDARY_INSURER_CONTACT_1);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryInsurerFax()).isEqualTo(UPDATED_SECONDARY_INSURER_FAX);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerAddressLine1()).isEqualTo(UPDATED_TERTIARY_INSURER_ADDRESS_LINE_1);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerAddressLine2()).isEqualTo(UPDATED_TERTIARY_INSURER_ADDRESS_LINE_2);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerCity()).isEqualTo(UPDATED_TERTIARY_INSURER_CITY);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerState()).isEqualTo(UPDATED_TERTIARY_INSURER_STATE);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerZip()).isEqualTo(UPDATED_TERTIARY_INSURER_ZIP);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerContact1()).isEqualTo(UPDATED_TERTIARY_INSURER_CONTACT_1);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryInsurerFax()).isEqualTo(UPDATED_TERTIARY_INSURER_FAX);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerPolicyEndDate()).isEqualTo(UPDATED_PRIMARY_INSURER_POLICY_END_DATE);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryInsurerContactName()).isEqualTo(UPDATED_PRIMARY_INSURER_CONTACT_NAME);
        assertThat(testSalesOrderInsuranceDetails.getPrimaryClaimProgram()).isEqualTo(UPDATED_PRIMARY_CLAIM_PROGRAM);
        assertThat(testSalesOrderInsuranceDetails.getSecondaryClaimProgram()).isEqualTo(UPDATED_SECONDARY_CLAIM_PROGRAM);
        assertThat(testSalesOrderInsuranceDetails.getTertiaryClaimProgram()).isEqualTo(UPDATED_TERTIARY_CLAIM_PROGRAM);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompInsuredEmployer()).isEqualTo(UPDATED_WORKERS_COMP_INSURED_EMPLOYER);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompPayerIdNumber()).isEqualTo(UPDATED_WORKERS_COMP_PAYER_ID_NUMBER);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompPlanName()).isEqualTo(UPDATED_WORKERS_COMP_PLAN_NAME);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompAdditionalDtls()).isEqualTo(UPDATED_WORKERS_COMP_ADDITIONAL_DTLS);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompClaimFillingCode()).isEqualTo(UPDATED_WORKERS_COMP_CLAIM_FILLING_CODE);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompTplCode()).isEqualTo(UPDATED_WORKERS_COMP_TPL_CODE);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompTplName()).isEqualTo(UPDATED_WORKERS_COMP_TPL_NAME);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompPropertyCasualtyAgencyClaimNo())
            .isEqualTo(UPDATED_WORKERS_COMP_PROPERTY_CASUALTY_AGENCY_CLAIM_NO);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompCarrierId()).isEqualTo(UPDATED_WORKERS_COMP_CARRIER_ID);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerAddressLine1())
            .isEqualTo(UPDATED_WORKERS_COMP_EMPLOYER_ADDRESS_LINE_1);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerAddressLine2())
            .isEqualTo(UPDATED_WORKERS_COMP_EMPLOYER_ADDRESS_LINE_2);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerCity()).isEqualTo(UPDATED_WORKERS_COMP_EMPLOYER_CITY);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerState()).isEqualTo(UPDATED_WORKERS_COMP_EMPLOYER_STATE);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerCountry()).isEqualTo(UPDATED_WORKERS_COMP_EMPLOYER_COUNTRY);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerZip()).isEqualTo(UPDATED_WORKERS_COMP_EMPLOYER_ZIP);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerContactNo1()).isEqualTo(UPDATED_WORKERS_COMP_EMPLOYER_CONTACT_NO_1);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerContactNo2()).isEqualTo(UPDATED_WORKERS_COMP_EMPLOYER_CONTACT_NO_2);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerFax()).isEqualTo(UPDATED_WORKERS_COMP_EMPLOYER_FAX);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerEfax()).isEqualTo(UPDATED_WORKERS_COMP_EMPLOYER_EFAX);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompEmployerEmail()).isEqualTo(UPDATED_WORKERS_COMP_EMPLOYER_EMAIL);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompRelationship()).isEqualTo(UPDATED_WORKERS_COMP_RELATIONSHIP);
        assertThat(testSalesOrderInsuranceDetails.getWorkersCompModeOfContact()).isEqualTo(UPDATED_WORKERS_COMP_MODE_OF_CONTACT);
    }

    @Test
    void patchNonExistingSalesOrderInsuranceDetails() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderInsuranceDetailsRepository.findAll().collectList().block().size();
        salesOrderInsuranceDetails.setSalesOrderInsuranceDetailsId(count.incrementAndGet());

        // Create the SalesOrderInsuranceDetails
        SalesOrderInsuranceDetailsDTO salesOrderInsuranceDetailsDTO = salesOrderInsuranceDetailsMapper.toDto(salesOrderInsuranceDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, salesOrderInsuranceDetailsDTO.getSalesOrderInsuranceDetailsId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderInsuranceDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderInsuranceDetails in the database
        List<SalesOrderInsuranceDetails> salesOrderInsuranceDetailsList = salesOrderInsuranceDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderInsuranceDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchSalesOrderInsuranceDetails() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderInsuranceDetailsRepository.findAll().collectList().block().size();
        salesOrderInsuranceDetails.setSalesOrderInsuranceDetailsId(count.incrementAndGet());

        // Create the SalesOrderInsuranceDetails
        SalesOrderInsuranceDetailsDTO salesOrderInsuranceDetailsDTO = salesOrderInsuranceDetailsMapper.toDto(salesOrderInsuranceDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderInsuranceDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderInsuranceDetails in the database
        List<SalesOrderInsuranceDetails> salesOrderInsuranceDetailsList = salesOrderInsuranceDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderInsuranceDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamSalesOrderInsuranceDetails() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderInsuranceDetailsRepository.findAll().collectList().block().size();
        salesOrderInsuranceDetails.setSalesOrderInsuranceDetailsId(count.incrementAndGet());

        // Create the SalesOrderInsuranceDetails
        SalesOrderInsuranceDetailsDTO salesOrderInsuranceDetailsDTO = salesOrderInsuranceDetailsMapper.toDto(salesOrderInsuranceDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderInsuranceDetailsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the SalesOrderInsuranceDetails in the database
        List<SalesOrderInsuranceDetails> salesOrderInsuranceDetailsList = salesOrderInsuranceDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderInsuranceDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteSalesOrderInsuranceDetails() {
        // Initialize the database
        salesOrderInsuranceDetailsRepository.save(salesOrderInsuranceDetails).block();

        int databaseSizeBeforeDelete = salesOrderInsuranceDetailsRepository.findAll().collectList().block().size();

        // Delete the salesOrderInsuranceDetails
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, salesOrderInsuranceDetails.getSalesOrderInsuranceDetailsId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<SalesOrderInsuranceDetails> salesOrderInsuranceDetailsList = salesOrderInsuranceDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderInsuranceDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
