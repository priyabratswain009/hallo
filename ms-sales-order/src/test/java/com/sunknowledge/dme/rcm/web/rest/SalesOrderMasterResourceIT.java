package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.SalesOrderMaster;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.SalesOrderMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.SalesOrderMasterMapper;
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
 * Integration tests for the {@link SalesOrderMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class SalesOrderMasterResourceIT {

    private static final String DEFAULT_SALES_ORDER_NO = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_NO = "BBBBBBBBBB";

    private static final Long DEFAULT_PATIENT_ID = 1L;
    private static final Long UPDATED_PATIENT_ID = 2L;

    private static final String DEFAULT_PATIENT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_CONTACT_NO_1 = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_CONTACT_NO_1 = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_CONTACT_NO_2 = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_CONTACT_NO_2 = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PATIENT_DOB = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PATIENT_DOB = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_PATIENT_HEIGHT = 1D;
    private static final Double UPDATED_PATIENT_HEIGHT = 2D;

    private static final Double DEFAULT_PATIENT_WEIGHT = 1D;
    private static final Double UPDATED_PATIENT_WEIGHT = 2D;

    private static final String DEFAULT_PATIENT_SSN = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_SSN = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_GENDER = "BBBBBBBBBB";

    private static final String DEFAULT_HIPAA_ON_FILE_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_HIPAA_ON_FILE_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_PATIENT_BRANCH_ID = 1L;
    private static final Long UPDATED_PATIENT_BRANCH_ID = 2L;

    private static final String DEFAULT_BRANCH_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DELIVERY_SCHEDULE_DATETIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DELIVERY_SCHEDULE_DATETIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DELIVERY_ACTUAL_DATETIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DELIVERY_ACTUAL_DATETIME = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DELIVERY_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_PHONE_NO_1 = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_PHONE_NO_1 = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_PHONE_NO_2 = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_PHONE_NO_2 = "BBBBBBBBBB";

    private static final Long DEFAULT_FACILITY_ID = 1L;
    private static final Long UPDATED_FACILITY_ID = 2L;

    private static final String DEFAULT_FACILITY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FACILITY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FACILITY_NPI = "AAAAAAAAAA";
    private static final String UPDATED_FACILITY_NPI = "BBBBBBBBBB";

    private static final Long DEFAULT_TAX_ZONE_ID = 1L;
    private static final Long UPDATED_TAX_ZONE_ID = 2L;

    private static final Double DEFAULT_TAX_RATE = 1D;
    private static final Double UPDATED_TAX_RATE = 2D;

    private static final String DEFAULT_SALES_ORDER_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_NOTE = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_NOTE = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_TECHNICIAN = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_TECHNICIAN = "BBBBBBBBBB";

    private static final String DEFAULT_SIGNATURE_REQUIRED_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_SIGNATURE_REQUIRED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_POD_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_POD_STATUS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_POD_STATUS_DATETIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_POD_STATUS_DATETIME = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_POD_LAST_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_POD_LAST_MESSAGE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_POD_MESSAGE_DATETIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_POD_MESSAGE_DATETIME = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_MUTUAL_HOLD_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_MUTUAL_HOLD_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_HOLD_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_HOLD_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_HOLD_REASON_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_HOLD_REASON_DESCRIPTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_STOP_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_STOP_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_STOP_REASON_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_STOP_REASON_DESCRIPTION = "BBBBBBBBBB";

    private static final Long DEFAULT_BRANCH_ID = 1L;
    private static final Long UPDATED_BRANCH_ID = 2L;

    private static final String DEFAULT_BILLING_BRANCH_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_BRANCH_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_INVENTORY_LOCATION_ID = 1L;
    private static final Long UPDATED_INVENTORY_LOCATION_ID = 2L;

    private static final String DEFAULT_ORDER_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_ORDER_CLASSIFICATION_ID = 1L;
    private static final Long UPDATED_ORDER_CLASSIFICATION_ID = 2L;

    private static final Long DEFAULT_POS_ID = 1L;
    private static final Long UPDATED_POS_ID = 2L;

    private static final LocalDate DEFAULT_ADMISSION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ADMISSION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DISCHARGE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DISCHARGE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_DISCOUNT_PERCENTAGE = 1L;
    private static final Long UPDATED_DISCOUNT_PERCENTAGE = 2L;

    private static final String DEFAULT_PO_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PO_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_USER_FIELD_1 = "AAAAAAAAAA";
    private static final String UPDATED_USER_FIELD_1 = "BBBBBBBBBB";

    private static final String DEFAULT_USER_FIELD_2 = "AAAAAAAAAA";
    private static final String UPDATED_USER_FIELD_2 = "BBBBBBBBBB";

    private static final String DEFAULT_USER_FIELD_3 = "AAAAAAAAAA";
    private static final String UPDATED_USER_FIELD_3 = "BBBBBBBBBB";

    private static final String DEFAULT_USER_FIELD_4 = "AAAAAAAAAA";
    private static final String UPDATED_USER_FIELD_4 = "BBBBBBBBBB";

    private static final String DEFAULT_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE = "BBBBBBBBBB";

    private static final String DEFAULT_WIP_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_WIP_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_WIP_DAYS_IN_STATE = 1L;
    private static final Long UPDATED_WIP_DAYS_IN_STATE = 2L;

    private static final Long DEFAULT_WIP_ASSIGNED_TO_ID = 1L;
    private static final Long UPDATED_WIP_ASSIGNED_TO_ID = 2L;

    private static final LocalDate DEFAULT_WIP_DATE_NEEDED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_WIP_DATE_NEEDED = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_COMPLETED_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_COMPLETED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CITY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CITY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STATE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STATE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ZIP_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ZIP_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_CITY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_CITY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_STATE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_STATE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_ZIP_CODE = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_ZIP_CODE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PATIENT_DOD = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PATIENT_DOD = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_POS_NAME = "AAAAAAAAAA";
    private static final String UPDATED_POS_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final Long DEFAULT_CONFIRMATION_BY_ID = 1L;
    private static final Long UPDATED_CONFIRMATION_BY_ID = 2L;

    private static final String DEFAULT_CONFIRMATION_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CONFIRMATION_BY_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CONFIRMATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CONFIRMATION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final UUID DEFAULT_SALES_ORDER_MASTER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_SALES_ORDER_MASTER_UUID = UUID.randomUUID();

    private static final String DEFAULT_BRANCH_CONTACT_PERSON_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_CONTACT_PERSON_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_NPI = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_NPI = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_EIN = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_EIN = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_CONTACT_NO_1 = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_CONTACT_NO_1 = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_CONTACT_NO_2 = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_CONTACT_NO_2 = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_ID_NO = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_ID_NO = "BBBBBBBBBB";

    private static final String DEFAULT_POS_CODE = "AAAAAAAAAA";
    private static final String UPDATED_POS_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_ECLAIM_CARRIER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ECLAIM_CARRIER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PLAN_PARTICIPATION_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PLAN_PARTICIPATION_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_MEMBER_ID = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_MEMBER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_PERSON_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_PERSON_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PROVIDER_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_PROVIDER_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_CITY = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_STATE = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_ZIP_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_ZIP_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_DELIVERY_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_DELIVERY_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_DELIVERY_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_DELIVERY_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_DELIVERY_CITY = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_DELIVERY_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_DELIVERY_STATE = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_DELIVERY_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_DELIVERY_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_DELIVERY_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_DELIVERY_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_DELIVERY_ZIP = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_BILLING_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_BILLING_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_BILLING_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_BILLING_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_BILLING_CITY = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_BILLING_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_BILLING_STATE = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_BILLING_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_BILLING_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_BILLING_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_BILLING_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_BILLING_ZIP = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_FAX = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_RELATIONSHIP = "AAAAAAAAAA";
    private static final String UPDATED_RELATIONSHIP = "BBBBBBBBBB";

    private static final String DEFAULT_MODE_OF_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_MODE_OF_CONTACT = "BBBBBBBBBB";

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

    private static final String DEFAULT_INSURED_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_INSURED_GENDER = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_RELATIONSHIP_INSURED = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_RELATIONSHIP_INSURED = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_CONDITION_EMPLOYMENT = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_CONDITION_EMPLOYMENT = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_CONDITION_AUTO_ACCIDENT = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_CONDITION_AUTO_ACCIDENT = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_CONDITION_OTHER_ACCIDENT = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_CONDITION_OTHER_ACCIDENT = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_PROVIDER_TAXONOMY = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_PROVIDER_TAXONOMY = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_PROVIDER_NPI = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_PROVIDER_NPI = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_PROVIDER_ORGANISATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_PROVIDER_ORGANISATION_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_PROVIDER_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_PROVIDER_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_PROVIDER_CITY = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_PROVIDER_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_PROVIDER_STATE = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_PROVIDER_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_PROVIDER_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_PROVIDER_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_PROVIDER_ZIP_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_PROVIDER_ZIP_CODE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_INSURED_DOB = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_INSURED_DOB = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_BRANCH_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_TAXONOMY = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_TAXONOMY = "BBBBBBBBBB";

    private static final Long DEFAULT_PRIMARY_INSURER_PRICE_TABLE_ID = 1L;
    private static final Long UPDATED_PRIMARY_INSURER_PRICE_TABLE_ID = 2L;

    private static final String DEFAULT_PRIMARY_INSURER_PRICE_TABLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_INSURER_PRICE_TABLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_INVENTORY_LOCATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INVENTORY_LOCATION_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SO_CONTROL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_SO_CONTROL_NUMBER = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/sales-order-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{salesOrderId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SalesOrderMasterRepository salesOrderMasterRepository;

    @Autowired
    private SalesOrderMasterMapper salesOrderMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private SalesOrderMaster salesOrderMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesOrderMaster createEntity(EntityManager em) {
        SalesOrderMaster salesOrderMaster = new SalesOrderMaster()
            .salesOrderNo(DEFAULT_SALES_ORDER_NO)
            .patientId(DEFAULT_PATIENT_ID)
            .patientFirstName(DEFAULT_PATIENT_FIRST_NAME)
            .patientMiddleName(DEFAULT_PATIENT_MIDDLE_NAME)
            .patientLastName(DEFAULT_PATIENT_LAST_NAME)
            .patientAddressLine1(DEFAULT_PATIENT_ADDRESS_LINE_1)
            .patientAddressLine2(DEFAULT_PATIENT_ADDRESS_LINE_2)
            .patientContactNo1(DEFAULT_PATIENT_CONTACT_NO_1)
            .patientContactNo2(DEFAULT_PATIENT_CONTACT_NO_2)
            .patientDob(DEFAULT_PATIENT_DOB)
            .patientHeight(DEFAULT_PATIENT_HEIGHT)
            .patientWeight(DEFAULT_PATIENT_WEIGHT)
            .patientSsn(DEFAULT_PATIENT_SSN)
            .patientGender(DEFAULT_PATIENT_GENDER)
            .hipaaOnFileStatus(DEFAULT_HIPAA_ON_FILE_STATUS)
            .patientBranchId(DEFAULT_PATIENT_BRANCH_ID)
            .branchName(DEFAULT_BRANCH_NAME)
            .deliveryScheduleDatetime(DEFAULT_DELIVERY_SCHEDULE_DATETIME)
            .deliveryActualDatetime(DEFAULT_DELIVERY_ACTUAL_DATETIME)
            .deliveryAddressLine1(DEFAULT_DELIVERY_ADDRESS_LINE_1)
            .deliveryAddressLine2(DEFAULT_DELIVERY_ADDRESS_LINE_2)
            .deliveryPhoneNo1(DEFAULT_DELIVERY_PHONE_NO_1)
            .deliveryPhoneNo2(DEFAULT_DELIVERY_PHONE_NO_2)
            .facilityId(DEFAULT_FACILITY_ID)
            .facilityName(DEFAULT_FACILITY_NAME)
            .facilityNpi(DEFAULT_FACILITY_NPI)
            .taxZoneId(DEFAULT_TAX_ZONE_ID)
            .taxRate(DEFAULT_TAX_RATE)
            .salesOrderNote(DEFAULT_SALES_ORDER_NOTE)
            .deliveryNote(DEFAULT_DELIVERY_NOTE)
            .deliveryTechnician(DEFAULT_DELIVERY_TECHNICIAN)
            .signatureRequiredStatus(DEFAULT_SIGNATURE_REQUIRED_STATUS)
            .podStatus(DEFAULT_POD_STATUS)
            .podStatusDatetime(DEFAULT_POD_STATUS_DATETIME)
            .podLastMessage(DEFAULT_POD_LAST_MESSAGE)
            .podMessageDatetime(DEFAULT_POD_MESSAGE_DATETIME)
            .mutualHoldStatus(DEFAULT_MUTUAL_HOLD_STATUS)
            .holdStatus(DEFAULT_HOLD_STATUS)
            .holdReasonDescription(DEFAULT_HOLD_REASON_DESCRIPTION)
            .stopDate(DEFAULT_STOP_DATE)
            .stopReasonDescription(DEFAULT_STOP_REASON_DESCRIPTION)
            .branchId(DEFAULT_BRANCH_ID)
            .billingBranchName(DEFAULT_BILLING_BRANCH_NAME)
            .inventoryLocationId(DEFAULT_INVENTORY_LOCATION_ID)
            .orderStatus(DEFAULT_ORDER_STATUS)
            .orderClassificationId(DEFAULT_ORDER_CLASSIFICATION_ID)
            .posId(DEFAULT_POS_ID)
            .admissionDate(DEFAULT_ADMISSION_DATE)
            .dischargeDate(DEFAULT_DISCHARGE_DATE)
            .discountPercentage(DEFAULT_DISCOUNT_PERCENTAGE)
            .poNumber(DEFAULT_PO_NUMBER)
            .userField1(DEFAULT_USER_FIELD_1)
            .userField2(DEFAULT_USER_FIELD_2)
            .userField3(DEFAULT_USER_FIELD_3)
            .userField4(DEFAULT_USER_FIELD_4)
            .reference(DEFAULT_REFERENCE)
            .wipStatus(DEFAULT_WIP_STATUS)
            .wipDaysInState(DEFAULT_WIP_DAYS_IN_STATE)
            .wipAssignedToId(DEFAULT_WIP_ASSIGNED_TO_ID)
            .wipDateNeeded(DEFAULT_WIP_DATE_NEEDED)
            .completedStatus(DEFAULT_COMPLETED_STATUS)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .cityName(DEFAULT_CITY_NAME)
            .stateName(DEFAULT_STATE_NAME)
            .zipCode(DEFAULT_ZIP_CODE)
            .deliveryCityName(DEFAULT_DELIVERY_CITY_NAME)
            .deliveryStateName(DEFAULT_DELIVERY_STATE_NAME)
            .deliveryZipCode(DEFAULT_DELIVERY_ZIP_CODE)
            .patientDod(DEFAULT_PATIENT_DOD)
            .posName(DEFAULT_POS_NAME)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .confirmationById(DEFAULT_CONFIRMATION_BY_ID)
            .confirmationByName(DEFAULT_CONFIRMATION_BY_NAME)
            .confirmationDate(DEFAULT_CONFIRMATION_DATE)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .salesOrderMasterUuid(DEFAULT_SALES_ORDER_MASTER_UUID)
            .branchContactPersonName(DEFAULT_BRANCH_CONTACT_PERSON_NAME)
            .branchNpi(DEFAULT_BRANCH_NPI)
            .branchEin(DEFAULT_BRANCH_EIN)
            .branchContactNo1(DEFAULT_BRANCH_CONTACT_NO_1)
            .branchContactNo2(DEFAULT_BRANCH_CONTACT_NO_2)
            .patientIdNo(DEFAULT_PATIENT_ID_NO)
            .posCode(DEFAULT_POS_CODE)
            .eclaimCarrierName(DEFAULT_ECLAIM_CARRIER_NAME)
            .planParticipationCode(DEFAULT_PLAN_PARTICIPATION_CODE)
            .patientMemberId(DEFAULT_PATIENT_MEMBER_ID)
            .contactPersonName(DEFAULT_CONTACT_PERSON_NAME)
            .providerType(DEFAULT_PROVIDER_TYPE)
            .branchAddressLine1(DEFAULT_BRANCH_ADDRESS_LINE_1)
            .branchAddressLine2(DEFAULT_BRANCH_ADDRESS_LINE_2)
            .branchCity(DEFAULT_BRANCH_CITY)
            .branchState(DEFAULT_BRANCH_STATE)
            .branchZipCode(DEFAULT_BRANCH_ZIP_CODE)
            .patientDeliveryAddressLine1(DEFAULT_PATIENT_DELIVERY_ADDRESS_LINE_1)
            .patientDeliveryAddressLine2(DEFAULT_PATIENT_DELIVERY_ADDRESS_LINE_2)
            .patientDeliveryCity(DEFAULT_PATIENT_DELIVERY_CITY)
            .patientDeliveryState(DEFAULT_PATIENT_DELIVERY_STATE)
            .patientDeliveryCountry(DEFAULT_PATIENT_DELIVERY_COUNTRY)
            .patientDeliveryZip(DEFAULT_PATIENT_DELIVERY_ZIP)
            .patientBillingAddressLine1(DEFAULT_PATIENT_BILLING_ADDRESS_LINE_1)
            .patientBillingAddressLine2(DEFAULT_PATIENT_BILLING_ADDRESS_LINE_2)
            .patientBillingCity(DEFAULT_PATIENT_BILLING_CITY)
            .patientBillingState(DEFAULT_PATIENT_BILLING_STATE)
            .patientBillingCountry(DEFAULT_PATIENT_BILLING_COUNTRY)
            .patientBillingZip(DEFAULT_PATIENT_BILLING_ZIP)
            .patientFax(DEFAULT_PATIENT_FAX)
            .branchFax(DEFAULT_BRANCH_FAX)
            .patientEmail(DEFAULT_PATIENT_EMAIL)
            .relationship(DEFAULT_RELATIONSHIP)
            .modeOfContact(DEFAULT_MODE_OF_CONTACT)
            .insuredFirstName(DEFAULT_INSURED_FIRST_NAME)
            .insuredLastName(DEFAULT_INSURED_LAST_NAME)
            .insuredAddressLine1(DEFAULT_INSURED_ADDRESS_LINE_1)
            .insuredAddressLine2(DEFAULT_INSURED_ADDRESS_LINE_2)
            .insuredCity(DEFAULT_INSURED_CITY)
            .insuredState(DEFAULT_INSURED_STATE)
            .insuredZip(DEFAULT_INSURED_ZIP)
            .insuredContactNo(DEFAULT_INSURED_CONTACT_NO)
            .insuredGender(DEFAULT_INSURED_GENDER)
            .patientRelationshipInsured(DEFAULT_PATIENT_RELATIONSHIP_INSURED)
            .patientConditionEmployment(DEFAULT_PATIENT_CONDITION_EMPLOYMENT)
            .patientConditionAutoAccident(DEFAULT_PATIENT_CONDITION_AUTO_ACCIDENT)
            .patientConditionOtherAccident(DEFAULT_PATIENT_CONDITION_OTHER_ACCIDENT)
            .billingProviderTaxonomy(DEFAULT_BILLING_PROVIDER_TAXONOMY)
            .billingProviderNpi(DEFAULT_BILLING_PROVIDER_NPI)
            .billingProviderOrganisationName(DEFAULT_BILLING_PROVIDER_ORGANISATION_NAME)
            .billingProviderAddressLine1(DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_1)
            .billingProviderAddressLine2(DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_2)
            .billingProviderCity(DEFAULT_BILLING_PROVIDER_CITY)
            .billingProviderState(DEFAULT_BILLING_PROVIDER_STATE)
            .billingProviderCountry(DEFAULT_BILLING_PROVIDER_COUNTRY)
            .billingProviderZipCode(DEFAULT_BILLING_PROVIDER_ZIP_CODE)
            .insuredDob(DEFAULT_INSURED_DOB)
            .branchCountry(DEFAULT_BRANCH_COUNTRY)
            .branchTaxonomy(DEFAULT_BRANCH_TAXONOMY)
            .primaryInsurerPriceTableId(DEFAULT_PRIMARY_INSURER_PRICE_TABLE_ID)
            .primaryInsurerPriceTableName(DEFAULT_PRIMARY_INSURER_PRICE_TABLE_NAME)
            .inventoryLocationName(DEFAULT_INVENTORY_LOCATION_NAME)
            .soControlNumber(DEFAULT_SO_CONTROL_NUMBER);
        return salesOrderMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesOrderMaster createUpdatedEntity(EntityManager em) {
        SalesOrderMaster salesOrderMaster = new SalesOrderMaster()
            .salesOrderNo(UPDATED_SALES_ORDER_NO)
            .patientId(UPDATED_PATIENT_ID)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientMiddleName(UPDATED_PATIENT_MIDDLE_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .patientAddressLine1(UPDATED_PATIENT_ADDRESS_LINE_1)
            .patientAddressLine2(UPDATED_PATIENT_ADDRESS_LINE_2)
            .patientContactNo1(UPDATED_PATIENT_CONTACT_NO_1)
            .patientContactNo2(UPDATED_PATIENT_CONTACT_NO_2)
            .patientDob(UPDATED_PATIENT_DOB)
            .patientHeight(UPDATED_PATIENT_HEIGHT)
            .patientWeight(UPDATED_PATIENT_WEIGHT)
            .patientSsn(UPDATED_PATIENT_SSN)
            .patientGender(UPDATED_PATIENT_GENDER)
            .hipaaOnFileStatus(UPDATED_HIPAA_ON_FILE_STATUS)
            .patientBranchId(UPDATED_PATIENT_BRANCH_ID)
            .branchName(UPDATED_BRANCH_NAME)
            .deliveryScheduleDatetime(UPDATED_DELIVERY_SCHEDULE_DATETIME)
            .deliveryActualDatetime(UPDATED_DELIVERY_ACTUAL_DATETIME)
            .deliveryAddressLine1(UPDATED_DELIVERY_ADDRESS_LINE_1)
            .deliveryAddressLine2(UPDATED_DELIVERY_ADDRESS_LINE_2)
            .deliveryPhoneNo1(UPDATED_DELIVERY_PHONE_NO_1)
            .deliveryPhoneNo2(UPDATED_DELIVERY_PHONE_NO_2)
            .facilityId(UPDATED_FACILITY_ID)
            .facilityName(UPDATED_FACILITY_NAME)
            .facilityNpi(UPDATED_FACILITY_NPI)
            .taxZoneId(UPDATED_TAX_ZONE_ID)
            .taxRate(UPDATED_TAX_RATE)
            .salesOrderNote(UPDATED_SALES_ORDER_NOTE)
            .deliveryNote(UPDATED_DELIVERY_NOTE)
            .deliveryTechnician(UPDATED_DELIVERY_TECHNICIAN)
            .signatureRequiredStatus(UPDATED_SIGNATURE_REQUIRED_STATUS)
            .podStatus(UPDATED_POD_STATUS)
            .podStatusDatetime(UPDATED_POD_STATUS_DATETIME)
            .podLastMessage(UPDATED_POD_LAST_MESSAGE)
            .podMessageDatetime(UPDATED_POD_MESSAGE_DATETIME)
            .mutualHoldStatus(UPDATED_MUTUAL_HOLD_STATUS)
            .holdStatus(UPDATED_HOLD_STATUS)
            .holdReasonDescription(UPDATED_HOLD_REASON_DESCRIPTION)
            .stopDate(UPDATED_STOP_DATE)
            .stopReasonDescription(UPDATED_STOP_REASON_DESCRIPTION)
            .branchId(UPDATED_BRANCH_ID)
            .billingBranchName(UPDATED_BILLING_BRANCH_NAME)
            .inventoryLocationId(UPDATED_INVENTORY_LOCATION_ID)
            .orderStatus(UPDATED_ORDER_STATUS)
            .orderClassificationId(UPDATED_ORDER_CLASSIFICATION_ID)
            .posId(UPDATED_POS_ID)
            .admissionDate(UPDATED_ADMISSION_DATE)
            .dischargeDate(UPDATED_DISCHARGE_DATE)
            .discountPercentage(UPDATED_DISCOUNT_PERCENTAGE)
            .poNumber(UPDATED_PO_NUMBER)
            .userField1(UPDATED_USER_FIELD_1)
            .userField2(UPDATED_USER_FIELD_2)
            .userField3(UPDATED_USER_FIELD_3)
            .userField4(UPDATED_USER_FIELD_4)
            .reference(UPDATED_REFERENCE)
            .wipStatus(UPDATED_WIP_STATUS)
            .wipDaysInState(UPDATED_WIP_DAYS_IN_STATE)
            .wipAssignedToId(UPDATED_WIP_ASSIGNED_TO_ID)
            .wipDateNeeded(UPDATED_WIP_DATE_NEEDED)
            .completedStatus(UPDATED_COMPLETED_STATUS)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .cityName(UPDATED_CITY_NAME)
            .stateName(UPDATED_STATE_NAME)
            .zipCode(UPDATED_ZIP_CODE)
            .deliveryCityName(UPDATED_DELIVERY_CITY_NAME)
            .deliveryStateName(UPDATED_DELIVERY_STATE_NAME)
            .deliveryZipCode(UPDATED_DELIVERY_ZIP_CODE)
            .patientDod(UPDATED_PATIENT_DOD)
            .posName(UPDATED_POS_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .confirmationById(UPDATED_CONFIRMATION_BY_ID)
            .confirmationByName(UPDATED_CONFIRMATION_BY_NAME)
            .confirmationDate(UPDATED_CONFIRMATION_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .salesOrderMasterUuid(UPDATED_SALES_ORDER_MASTER_UUID)
            .branchContactPersonName(UPDATED_BRANCH_CONTACT_PERSON_NAME)
            .branchNpi(UPDATED_BRANCH_NPI)
            .branchEin(UPDATED_BRANCH_EIN)
            .branchContactNo1(UPDATED_BRANCH_CONTACT_NO_1)
            .branchContactNo2(UPDATED_BRANCH_CONTACT_NO_2)
            .patientIdNo(UPDATED_PATIENT_ID_NO)
            .posCode(UPDATED_POS_CODE)
            .eclaimCarrierName(UPDATED_ECLAIM_CARRIER_NAME)
            .planParticipationCode(UPDATED_PLAN_PARTICIPATION_CODE)
            .patientMemberId(UPDATED_PATIENT_MEMBER_ID)
            .contactPersonName(UPDATED_CONTACT_PERSON_NAME)
            .providerType(UPDATED_PROVIDER_TYPE)
            .branchAddressLine1(UPDATED_BRANCH_ADDRESS_LINE_1)
            .branchAddressLine2(UPDATED_BRANCH_ADDRESS_LINE_2)
            .branchCity(UPDATED_BRANCH_CITY)
            .branchState(UPDATED_BRANCH_STATE)
            .branchZipCode(UPDATED_BRANCH_ZIP_CODE)
            .patientDeliveryAddressLine1(UPDATED_PATIENT_DELIVERY_ADDRESS_LINE_1)
            .patientDeliveryAddressLine2(UPDATED_PATIENT_DELIVERY_ADDRESS_LINE_2)
            .patientDeliveryCity(UPDATED_PATIENT_DELIVERY_CITY)
            .patientDeliveryState(UPDATED_PATIENT_DELIVERY_STATE)
            .patientDeliveryCountry(UPDATED_PATIENT_DELIVERY_COUNTRY)
            .patientDeliveryZip(UPDATED_PATIENT_DELIVERY_ZIP)
            .patientBillingAddressLine1(UPDATED_PATIENT_BILLING_ADDRESS_LINE_1)
            .patientBillingAddressLine2(UPDATED_PATIENT_BILLING_ADDRESS_LINE_2)
            .patientBillingCity(UPDATED_PATIENT_BILLING_CITY)
            .patientBillingState(UPDATED_PATIENT_BILLING_STATE)
            .patientBillingCountry(UPDATED_PATIENT_BILLING_COUNTRY)
            .patientBillingZip(UPDATED_PATIENT_BILLING_ZIP)
            .patientFax(UPDATED_PATIENT_FAX)
            .branchFax(UPDATED_BRANCH_FAX)
            .patientEmail(UPDATED_PATIENT_EMAIL)
            .relationship(UPDATED_RELATIONSHIP)
            .modeOfContact(UPDATED_MODE_OF_CONTACT)
            .insuredFirstName(UPDATED_INSURED_FIRST_NAME)
            .insuredLastName(UPDATED_INSURED_LAST_NAME)
            .insuredAddressLine1(UPDATED_INSURED_ADDRESS_LINE_1)
            .insuredAddressLine2(UPDATED_INSURED_ADDRESS_LINE_2)
            .insuredCity(UPDATED_INSURED_CITY)
            .insuredState(UPDATED_INSURED_STATE)
            .insuredZip(UPDATED_INSURED_ZIP)
            .insuredContactNo(UPDATED_INSURED_CONTACT_NO)
            .insuredGender(UPDATED_INSURED_GENDER)
            .patientRelationshipInsured(UPDATED_PATIENT_RELATIONSHIP_INSURED)
            .patientConditionEmployment(UPDATED_PATIENT_CONDITION_EMPLOYMENT)
            .patientConditionAutoAccident(UPDATED_PATIENT_CONDITION_AUTO_ACCIDENT)
            .patientConditionOtherAccident(UPDATED_PATIENT_CONDITION_OTHER_ACCIDENT)
            .billingProviderTaxonomy(UPDATED_BILLING_PROVIDER_TAXONOMY)
            .billingProviderNpi(UPDATED_BILLING_PROVIDER_NPI)
            .billingProviderOrganisationName(UPDATED_BILLING_PROVIDER_ORGANISATION_NAME)
            .billingProviderAddressLine1(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_1)
            .billingProviderAddressLine2(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_2)
            .billingProviderCity(UPDATED_BILLING_PROVIDER_CITY)
            .billingProviderState(UPDATED_BILLING_PROVIDER_STATE)
            .billingProviderCountry(UPDATED_BILLING_PROVIDER_COUNTRY)
            .billingProviderZipCode(UPDATED_BILLING_PROVIDER_ZIP_CODE)
            .insuredDob(UPDATED_INSURED_DOB)
            .branchCountry(UPDATED_BRANCH_COUNTRY)
            .branchTaxonomy(UPDATED_BRANCH_TAXONOMY)
            .primaryInsurerPriceTableId(UPDATED_PRIMARY_INSURER_PRICE_TABLE_ID)
            .primaryInsurerPriceTableName(UPDATED_PRIMARY_INSURER_PRICE_TABLE_NAME)
            .inventoryLocationName(UPDATED_INVENTORY_LOCATION_NAME)
            .soControlNumber(UPDATED_SO_CONTROL_NUMBER);
        return salesOrderMaster;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(SalesOrderMaster.class).block();
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
        salesOrderMaster = createEntity(em);
    }

    @Test
    void createSalesOrderMaster() throws Exception {
        int databaseSizeBeforeCreate = salesOrderMasterRepository.findAll().collectList().block().size();
        // Create the SalesOrderMaster
        SalesOrderMasterDTO salesOrderMasterDTO = salesOrderMasterMapper.toDto(salesOrderMaster);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderMasterDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the SalesOrderMaster in the database
        List<SalesOrderMaster> salesOrderMasterList = salesOrderMasterRepository.findAll().collectList().block();
        assertThat(salesOrderMasterList).hasSize(databaseSizeBeforeCreate + 1);
        SalesOrderMaster testSalesOrderMaster = salesOrderMasterList.get(salesOrderMasterList.size() - 1);
        assertThat(testSalesOrderMaster.getSalesOrderNo()).isEqualTo(DEFAULT_SALES_ORDER_NO);
        assertThat(testSalesOrderMaster.getPatientId()).isEqualTo(DEFAULT_PATIENT_ID);
        assertThat(testSalesOrderMaster.getPatientFirstName()).isEqualTo(DEFAULT_PATIENT_FIRST_NAME);
        assertThat(testSalesOrderMaster.getPatientMiddleName()).isEqualTo(DEFAULT_PATIENT_MIDDLE_NAME);
        assertThat(testSalesOrderMaster.getPatientLastName()).isEqualTo(DEFAULT_PATIENT_LAST_NAME);
        assertThat(testSalesOrderMaster.getPatientAddressLine1()).isEqualTo(DEFAULT_PATIENT_ADDRESS_LINE_1);
        assertThat(testSalesOrderMaster.getPatientAddressLine2()).isEqualTo(DEFAULT_PATIENT_ADDRESS_LINE_2);
        assertThat(testSalesOrderMaster.getPatientContactNo1()).isEqualTo(DEFAULT_PATIENT_CONTACT_NO_1);
        assertThat(testSalesOrderMaster.getPatientContactNo2()).isEqualTo(DEFAULT_PATIENT_CONTACT_NO_2);
        assertThat(testSalesOrderMaster.getPatientDob()).isEqualTo(DEFAULT_PATIENT_DOB);
        assertThat(testSalesOrderMaster.getPatientHeight()).isEqualTo(DEFAULT_PATIENT_HEIGHT);
        assertThat(testSalesOrderMaster.getPatientWeight()).isEqualTo(DEFAULT_PATIENT_WEIGHT);
        assertThat(testSalesOrderMaster.getPatientSsn()).isEqualTo(DEFAULT_PATIENT_SSN);
        assertThat(testSalesOrderMaster.getPatientGender()).isEqualTo(DEFAULT_PATIENT_GENDER);
        assertThat(testSalesOrderMaster.getHipaaOnFileStatus()).isEqualTo(DEFAULT_HIPAA_ON_FILE_STATUS);
        assertThat(testSalesOrderMaster.getPatientBranchId()).isEqualTo(DEFAULT_PATIENT_BRANCH_ID);
        assertThat(testSalesOrderMaster.getBranchName()).isEqualTo(DEFAULT_BRANCH_NAME);
        assertThat(testSalesOrderMaster.getDeliveryScheduleDatetime()).isEqualTo(DEFAULT_DELIVERY_SCHEDULE_DATETIME);
        assertThat(testSalesOrderMaster.getDeliveryActualDatetime()).isEqualTo(DEFAULT_DELIVERY_ACTUAL_DATETIME);
        assertThat(testSalesOrderMaster.getDeliveryAddressLine1()).isEqualTo(DEFAULT_DELIVERY_ADDRESS_LINE_1);
        assertThat(testSalesOrderMaster.getDeliveryAddressLine2()).isEqualTo(DEFAULT_DELIVERY_ADDRESS_LINE_2);
        assertThat(testSalesOrderMaster.getDeliveryPhoneNo1()).isEqualTo(DEFAULT_DELIVERY_PHONE_NO_1);
        assertThat(testSalesOrderMaster.getDeliveryPhoneNo2()).isEqualTo(DEFAULT_DELIVERY_PHONE_NO_2);
        assertThat(testSalesOrderMaster.getFacilityId()).isEqualTo(DEFAULT_FACILITY_ID);
        assertThat(testSalesOrderMaster.getFacilityName()).isEqualTo(DEFAULT_FACILITY_NAME);
        assertThat(testSalesOrderMaster.getFacilityNpi()).isEqualTo(DEFAULT_FACILITY_NPI);
        assertThat(testSalesOrderMaster.getTaxZoneId()).isEqualTo(DEFAULT_TAX_ZONE_ID);
        assertThat(testSalesOrderMaster.getTaxRate()).isEqualTo(DEFAULT_TAX_RATE);
        assertThat(testSalesOrderMaster.getSalesOrderNote()).isEqualTo(DEFAULT_SALES_ORDER_NOTE);
        assertThat(testSalesOrderMaster.getDeliveryNote()).isEqualTo(DEFAULT_DELIVERY_NOTE);
        assertThat(testSalesOrderMaster.getDeliveryTechnician()).isEqualTo(DEFAULT_DELIVERY_TECHNICIAN);
        assertThat(testSalesOrderMaster.getSignatureRequiredStatus()).isEqualTo(DEFAULT_SIGNATURE_REQUIRED_STATUS);
        assertThat(testSalesOrderMaster.getPodStatus()).isEqualTo(DEFAULT_POD_STATUS);
        assertThat(testSalesOrderMaster.getPodStatusDatetime()).isEqualTo(DEFAULT_POD_STATUS_DATETIME);
        assertThat(testSalesOrderMaster.getPodLastMessage()).isEqualTo(DEFAULT_POD_LAST_MESSAGE);
        assertThat(testSalesOrderMaster.getPodMessageDatetime()).isEqualTo(DEFAULT_POD_MESSAGE_DATETIME);
        assertThat(testSalesOrderMaster.getMutualHoldStatus()).isEqualTo(DEFAULT_MUTUAL_HOLD_STATUS);
        assertThat(testSalesOrderMaster.getHoldStatus()).isEqualTo(DEFAULT_HOLD_STATUS);
        assertThat(testSalesOrderMaster.getHoldReasonDescription()).isEqualTo(DEFAULT_HOLD_REASON_DESCRIPTION);
        assertThat(testSalesOrderMaster.getStopDate()).isEqualTo(DEFAULT_STOP_DATE);
        assertThat(testSalesOrderMaster.getStopReasonDescription()).isEqualTo(DEFAULT_STOP_REASON_DESCRIPTION);
        assertThat(testSalesOrderMaster.getBranchId()).isEqualTo(DEFAULT_BRANCH_ID);
        assertThat(testSalesOrderMaster.getBillingBranchName()).isEqualTo(DEFAULT_BILLING_BRANCH_NAME);
        assertThat(testSalesOrderMaster.getInventoryLocationId()).isEqualTo(DEFAULT_INVENTORY_LOCATION_ID);
        assertThat(testSalesOrderMaster.getOrderStatus()).isEqualTo(DEFAULT_ORDER_STATUS);
        assertThat(testSalesOrderMaster.getOrderClassificationId()).isEqualTo(DEFAULT_ORDER_CLASSIFICATION_ID);
        assertThat(testSalesOrderMaster.getPosId()).isEqualTo(DEFAULT_POS_ID);
        assertThat(testSalesOrderMaster.getAdmissionDate()).isEqualTo(DEFAULT_ADMISSION_DATE);
        assertThat(testSalesOrderMaster.getDischargeDate()).isEqualTo(DEFAULT_DISCHARGE_DATE);
        assertThat(testSalesOrderMaster.getDiscountPercentage()).isEqualTo(DEFAULT_DISCOUNT_PERCENTAGE);
        assertThat(testSalesOrderMaster.getPoNumber()).isEqualTo(DEFAULT_PO_NUMBER);
        assertThat(testSalesOrderMaster.getUserField1()).isEqualTo(DEFAULT_USER_FIELD_1);
        assertThat(testSalesOrderMaster.getUserField2()).isEqualTo(DEFAULT_USER_FIELD_2);
        assertThat(testSalesOrderMaster.getUserField3()).isEqualTo(DEFAULT_USER_FIELD_3);
        assertThat(testSalesOrderMaster.getUserField4()).isEqualTo(DEFAULT_USER_FIELD_4);
        assertThat(testSalesOrderMaster.getReference()).isEqualTo(DEFAULT_REFERENCE);
        assertThat(testSalesOrderMaster.getWipStatus()).isEqualTo(DEFAULT_WIP_STATUS);
        assertThat(testSalesOrderMaster.getWipDaysInState()).isEqualTo(DEFAULT_WIP_DAYS_IN_STATE);
        assertThat(testSalesOrderMaster.getWipAssignedToId()).isEqualTo(DEFAULT_WIP_ASSIGNED_TO_ID);
        assertThat(testSalesOrderMaster.getWipDateNeeded()).isEqualTo(DEFAULT_WIP_DATE_NEEDED);
        assertThat(testSalesOrderMaster.getCompletedStatus()).isEqualTo(DEFAULT_COMPLETED_STATUS);
        assertThat(testSalesOrderMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSalesOrderMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testSalesOrderMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testSalesOrderMaster.getCityName()).isEqualTo(DEFAULT_CITY_NAME);
        assertThat(testSalesOrderMaster.getStateName()).isEqualTo(DEFAULT_STATE_NAME);
        assertThat(testSalesOrderMaster.getZipCode()).isEqualTo(DEFAULT_ZIP_CODE);
        assertThat(testSalesOrderMaster.getDeliveryCityName()).isEqualTo(DEFAULT_DELIVERY_CITY_NAME);
        assertThat(testSalesOrderMaster.getDeliveryStateName()).isEqualTo(DEFAULT_DELIVERY_STATE_NAME);
        assertThat(testSalesOrderMaster.getDeliveryZipCode()).isEqualTo(DEFAULT_DELIVERY_ZIP_CODE);
        assertThat(testSalesOrderMaster.getPatientDod()).isEqualTo(DEFAULT_PATIENT_DOD);
        assertThat(testSalesOrderMaster.getPosName()).isEqualTo(DEFAULT_POS_NAME);
        assertThat(testSalesOrderMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testSalesOrderMaster.getConfirmationById()).isEqualTo(DEFAULT_CONFIRMATION_BY_ID);
        assertThat(testSalesOrderMaster.getConfirmationByName()).isEqualTo(DEFAULT_CONFIRMATION_BY_NAME);
        assertThat(testSalesOrderMaster.getConfirmationDate()).isEqualTo(DEFAULT_CONFIRMATION_DATE);
        assertThat(testSalesOrderMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testSalesOrderMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testSalesOrderMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testSalesOrderMaster.getSalesOrderMasterUuid()).isEqualTo(DEFAULT_SALES_ORDER_MASTER_UUID);
        assertThat(testSalesOrderMaster.getBranchContactPersonName()).isEqualTo(DEFAULT_BRANCH_CONTACT_PERSON_NAME);
        assertThat(testSalesOrderMaster.getBranchNpi()).isEqualTo(DEFAULT_BRANCH_NPI);
        assertThat(testSalesOrderMaster.getBranchEin()).isEqualTo(DEFAULT_BRANCH_EIN);
        assertThat(testSalesOrderMaster.getBranchContactNo1()).isEqualTo(DEFAULT_BRANCH_CONTACT_NO_1);
        assertThat(testSalesOrderMaster.getBranchContactNo2()).isEqualTo(DEFAULT_BRANCH_CONTACT_NO_2);
        assertThat(testSalesOrderMaster.getPatientIdNo()).isEqualTo(DEFAULT_PATIENT_ID_NO);
        assertThat(testSalesOrderMaster.getPosCode()).isEqualTo(DEFAULT_POS_CODE);
        assertThat(testSalesOrderMaster.getEclaimCarrierName()).isEqualTo(DEFAULT_ECLAIM_CARRIER_NAME);
        assertThat(testSalesOrderMaster.getPlanParticipationCode()).isEqualTo(DEFAULT_PLAN_PARTICIPATION_CODE);
        assertThat(testSalesOrderMaster.getPatientMemberId()).isEqualTo(DEFAULT_PATIENT_MEMBER_ID);
        assertThat(testSalesOrderMaster.getContactPersonName()).isEqualTo(DEFAULT_CONTACT_PERSON_NAME);
        assertThat(testSalesOrderMaster.getProviderType()).isEqualTo(DEFAULT_PROVIDER_TYPE);
        assertThat(testSalesOrderMaster.getBranchAddressLine1()).isEqualTo(DEFAULT_BRANCH_ADDRESS_LINE_1);
        assertThat(testSalesOrderMaster.getBranchAddressLine2()).isEqualTo(DEFAULT_BRANCH_ADDRESS_LINE_2);
        assertThat(testSalesOrderMaster.getBranchCity()).isEqualTo(DEFAULT_BRANCH_CITY);
        assertThat(testSalesOrderMaster.getBranchState()).isEqualTo(DEFAULT_BRANCH_STATE);
        assertThat(testSalesOrderMaster.getBranchZipCode()).isEqualTo(DEFAULT_BRANCH_ZIP_CODE);
        assertThat(testSalesOrderMaster.getPatientDeliveryAddressLine1()).isEqualTo(DEFAULT_PATIENT_DELIVERY_ADDRESS_LINE_1);
        assertThat(testSalesOrderMaster.getPatientDeliveryAddressLine2()).isEqualTo(DEFAULT_PATIENT_DELIVERY_ADDRESS_LINE_2);
        assertThat(testSalesOrderMaster.getPatientDeliveryCity()).isEqualTo(DEFAULT_PATIENT_DELIVERY_CITY);
        assertThat(testSalesOrderMaster.getPatientDeliveryState()).isEqualTo(DEFAULT_PATIENT_DELIVERY_STATE);
        assertThat(testSalesOrderMaster.getPatientDeliveryCountry()).isEqualTo(DEFAULT_PATIENT_DELIVERY_COUNTRY);
        assertThat(testSalesOrderMaster.getPatientDeliveryZip()).isEqualTo(DEFAULT_PATIENT_DELIVERY_ZIP);
        assertThat(testSalesOrderMaster.getPatientBillingAddressLine1()).isEqualTo(DEFAULT_PATIENT_BILLING_ADDRESS_LINE_1);
        assertThat(testSalesOrderMaster.getPatientBillingAddressLine2()).isEqualTo(DEFAULT_PATIENT_BILLING_ADDRESS_LINE_2);
        assertThat(testSalesOrderMaster.getPatientBillingCity()).isEqualTo(DEFAULT_PATIENT_BILLING_CITY);
        assertThat(testSalesOrderMaster.getPatientBillingState()).isEqualTo(DEFAULT_PATIENT_BILLING_STATE);
        assertThat(testSalesOrderMaster.getPatientBillingCountry()).isEqualTo(DEFAULT_PATIENT_BILLING_COUNTRY);
        assertThat(testSalesOrderMaster.getPatientBillingZip()).isEqualTo(DEFAULT_PATIENT_BILLING_ZIP);
        assertThat(testSalesOrderMaster.getPatientFax()).isEqualTo(DEFAULT_PATIENT_FAX);
        assertThat(testSalesOrderMaster.getBranchFax()).isEqualTo(DEFAULT_BRANCH_FAX);
        assertThat(testSalesOrderMaster.getPatientEmail()).isEqualTo(DEFAULT_PATIENT_EMAIL);
        assertThat(testSalesOrderMaster.getRelationship()).isEqualTo(DEFAULT_RELATIONSHIP);
        assertThat(testSalesOrderMaster.getModeOfContact()).isEqualTo(DEFAULT_MODE_OF_CONTACT);
        assertThat(testSalesOrderMaster.getInsuredFirstName()).isEqualTo(DEFAULT_INSURED_FIRST_NAME);
        assertThat(testSalesOrderMaster.getInsuredLastName()).isEqualTo(DEFAULT_INSURED_LAST_NAME);
        assertThat(testSalesOrderMaster.getInsuredAddressLine1()).isEqualTo(DEFAULT_INSURED_ADDRESS_LINE_1);
        assertThat(testSalesOrderMaster.getInsuredAddressLine2()).isEqualTo(DEFAULT_INSURED_ADDRESS_LINE_2);
        assertThat(testSalesOrderMaster.getInsuredCity()).isEqualTo(DEFAULT_INSURED_CITY);
        assertThat(testSalesOrderMaster.getInsuredState()).isEqualTo(DEFAULT_INSURED_STATE);
        assertThat(testSalesOrderMaster.getInsuredZip()).isEqualTo(DEFAULT_INSURED_ZIP);
        assertThat(testSalesOrderMaster.getInsuredContactNo()).isEqualTo(DEFAULT_INSURED_CONTACT_NO);
        assertThat(testSalesOrderMaster.getInsuredGender()).isEqualTo(DEFAULT_INSURED_GENDER);
        assertThat(testSalesOrderMaster.getPatientRelationshipInsured()).isEqualTo(DEFAULT_PATIENT_RELATIONSHIP_INSURED);
        assertThat(testSalesOrderMaster.getPatientConditionEmployment()).isEqualTo(DEFAULT_PATIENT_CONDITION_EMPLOYMENT);
        assertThat(testSalesOrderMaster.getPatientConditionAutoAccident()).isEqualTo(DEFAULT_PATIENT_CONDITION_AUTO_ACCIDENT);
        assertThat(testSalesOrderMaster.getPatientConditionOtherAccident()).isEqualTo(DEFAULT_PATIENT_CONDITION_OTHER_ACCIDENT);
        assertThat(testSalesOrderMaster.getBillingProviderTaxonomy()).isEqualTo(DEFAULT_BILLING_PROVIDER_TAXONOMY);
        assertThat(testSalesOrderMaster.getBillingProviderNpi()).isEqualTo(DEFAULT_BILLING_PROVIDER_NPI);
        assertThat(testSalesOrderMaster.getBillingProviderOrganisationName()).isEqualTo(DEFAULT_BILLING_PROVIDER_ORGANISATION_NAME);
        assertThat(testSalesOrderMaster.getBillingProviderAddressLine1()).isEqualTo(DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_1);
        assertThat(testSalesOrderMaster.getBillingProviderAddressLine2()).isEqualTo(DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_2);
        assertThat(testSalesOrderMaster.getBillingProviderCity()).isEqualTo(DEFAULT_BILLING_PROVIDER_CITY);
        assertThat(testSalesOrderMaster.getBillingProviderState()).isEqualTo(DEFAULT_BILLING_PROVIDER_STATE);
        assertThat(testSalesOrderMaster.getBillingProviderCountry()).isEqualTo(DEFAULT_BILLING_PROVIDER_COUNTRY);
        assertThat(testSalesOrderMaster.getBillingProviderZipCode()).isEqualTo(DEFAULT_BILLING_PROVIDER_ZIP_CODE);
        assertThat(testSalesOrderMaster.getInsuredDob()).isEqualTo(DEFAULT_INSURED_DOB);
        assertThat(testSalesOrderMaster.getBranchCountry()).isEqualTo(DEFAULT_BRANCH_COUNTRY);
        assertThat(testSalesOrderMaster.getBranchTaxonomy()).isEqualTo(DEFAULT_BRANCH_TAXONOMY);
        assertThat(testSalesOrderMaster.getPrimaryInsurerPriceTableId()).isEqualTo(DEFAULT_PRIMARY_INSURER_PRICE_TABLE_ID);
        assertThat(testSalesOrderMaster.getPrimaryInsurerPriceTableName()).isEqualTo(DEFAULT_PRIMARY_INSURER_PRICE_TABLE_NAME);
        assertThat(testSalesOrderMaster.getInventoryLocationName()).isEqualTo(DEFAULT_INVENTORY_LOCATION_NAME);
        assertThat(testSalesOrderMaster.getSoControlNumber()).isEqualTo(DEFAULT_SO_CONTROL_NUMBER);
    }

    @Test
    void createSalesOrderMasterWithExistingId() throws Exception {
        // Create the SalesOrderMaster with an existing ID
        salesOrderMaster.setSalesOrderId(1L);
        SalesOrderMasterDTO salesOrderMasterDTO = salesOrderMasterMapper.toDto(salesOrderMaster);

        int databaseSizeBeforeCreate = salesOrderMasterRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderMaster in the database
        List<SalesOrderMaster> salesOrderMasterList = salesOrderMasterRepository.findAll().collectList().block();
        assertThat(salesOrderMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllSalesOrderMasters() {
        // Initialize the database
        salesOrderMasterRepository.save(salesOrderMaster).block();

        // Get all the salesOrderMasterList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=salesOrderId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].salesOrderId")
            .value(hasItem(salesOrderMaster.getSalesOrderId().intValue()))
            .jsonPath("$.[*].salesOrderNo")
            .value(hasItem(DEFAULT_SALES_ORDER_NO))
            .jsonPath("$.[*].patientId")
            .value(hasItem(DEFAULT_PATIENT_ID.intValue()))
            .jsonPath("$.[*].patientFirstName")
            .value(hasItem(DEFAULT_PATIENT_FIRST_NAME))
            .jsonPath("$.[*].patientMiddleName")
            .value(hasItem(DEFAULT_PATIENT_MIDDLE_NAME))
            .jsonPath("$.[*].patientLastName")
            .value(hasItem(DEFAULT_PATIENT_LAST_NAME))
            .jsonPath("$.[*].patientAddressLine1")
            .value(hasItem(DEFAULT_PATIENT_ADDRESS_LINE_1))
            .jsonPath("$.[*].patientAddressLine2")
            .value(hasItem(DEFAULT_PATIENT_ADDRESS_LINE_2))
            .jsonPath("$.[*].patientContactNo1")
            .value(hasItem(DEFAULT_PATIENT_CONTACT_NO_1))
            .jsonPath("$.[*].patientContactNo2")
            .value(hasItem(DEFAULT_PATIENT_CONTACT_NO_2))
            .jsonPath("$.[*].patientDob")
            .value(hasItem(DEFAULT_PATIENT_DOB.toString()))
            .jsonPath("$.[*].patientHeight")
            .value(hasItem(DEFAULT_PATIENT_HEIGHT.doubleValue()))
            .jsonPath("$.[*].patientWeight")
            .value(hasItem(DEFAULT_PATIENT_WEIGHT.doubleValue()))
            .jsonPath("$.[*].patientSsn")
            .value(hasItem(DEFAULT_PATIENT_SSN))
            .jsonPath("$.[*].patientGender")
            .value(hasItem(DEFAULT_PATIENT_GENDER))
            .jsonPath("$.[*].hipaaOnFileStatus")
            .value(hasItem(DEFAULT_HIPAA_ON_FILE_STATUS))
            .jsonPath("$.[*].patientBranchId")
            .value(hasItem(DEFAULT_PATIENT_BRANCH_ID.intValue()))
            .jsonPath("$.[*].branchName")
            .value(hasItem(DEFAULT_BRANCH_NAME))
            .jsonPath("$.[*].deliveryScheduleDatetime")
            .value(hasItem(DEFAULT_DELIVERY_SCHEDULE_DATETIME.toString()))
            .jsonPath("$.[*].deliveryActualDatetime")
            .value(hasItem(DEFAULT_DELIVERY_ACTUAL_DATETIME.toString()))
            .jsonPath("$.[*].deliveryAddressLine1")
            .value(hasItem(DEFAULT_DELIVERY_ADDRESS_LINE_1))
            .jsonPath("$.[*].deliveryAddressLine2")
            .value(hasItem(DEFAULT_DELIVERY_ADDRESS_LINE_2))
            .jsonPath("$.[*].deliveryPhoneNo1")
            .value(hasItem(DEFAULT_DELIVERY_PHONE_NO_1))
            .jsonPath("$.[*].deliveryPhoneNo2")
            .value(hasItem(DEFAULT_DELIVERY_PHONE_NO_2))
            .jsonPath("$.[*].facilityId")
            .value(hasItem(DEFAULT_FACILITY_ID.intValue()))
            .jsonPath("$.[*].facilityName")
            .value(hasItem(DEFAULT_FACILITY_NAME))
            .jsonPath("$.[*].facilityNpi")
            .value(hasItem(DEFAULT_FACILITY_NPI))
            .jsonPath("$.[*].taxZoneId")
            .value(hasItem(DEFAULT_TAX_ZONE_ID.intValue()))
            .jsonPath("$.[*].taxRate")
            .value(hasItem(DEFAULT_TAX_RATE.doubleValue()))
            .jsonPath("$.[*].salesOrderNote")
            .value(hasItem(DEFAULT_SALES_ORDER_NOTE))
            .jsonPath("$.[*].deliveryNote")
            .value(hasItem(DEFAULT_DELIVERY_NOTE))
            .jsonPath("$.[*].deliveryTechnician")
            .value(hasItem(DEFAULT_DELIVERY_TECHNICIAN))
            .jsonPath("$.[*].signatureRequiredStatus")
            .value(hasItem(DEFAULT_SIGNATURE_REQUIRED_STATUS))
            .jsonPath("$.[*].podStatus")
            .value(hasItem(DEFAULT_POD_STATUS))
            .jsonPath("$.[*].podStatusDatetime")
            .value(hasItem(DEFAULT_POD_STATUS_DATETIME.toString()))
            .jsonPath("$.[*].podLastMessage")
            .value(hasItem(DEFAULT_POD_LAST_MESSAGE))
            .jsonPath("$.[*].podMessageDatetime")
            .value(hasItem(DEFAULT_POD_MESSAGE_DATETIME.toString()))
            .jsonPath("$.[*].mutualHoldStatus")
            .value(hasItem(DEFAULT_MUTUAL_HOLD_STATUS))
            .jsonPath("$.[*].holdStatus")
            .value(hasItem(DEFAULT_HOLD_STATUS))
            .jsonPath("$.[*].holdReasonDescription")
            .value(hasItem(DEFAULT_HOLD_REASON_DESCRIPTION))
            .jsonPath("$.[*].stopDate")
            .value(hasItem(DEFAULT_STOP_DATE.toString()))
            .jsonPath("$.[*].stopReasonDescription")
            .value(hasItem(DEFAULT_STOP_REASON_DESCRIPTION))
            .jsonPath("$.[*].branchId")
            .value(hasItem(DEFAULT_BRANCH_ID.intValue()))
            .jsonPath("$.[*].billingBranchName")
            .value(hasItem(DEFAULT_BILLING_BRANCH_NAME))
            .jsonPath("$.[*].inventoryLocationId")
            .value(hasItem(DEFAULT_INVENTORY_LOCATION_ID.intValue()))
            .jsonPath("$.[*].orderStatus")
            .value(hasItem(DEFAULT_ORDER_STATUS))
            .jsonPath("$.[*].orderClassificationId")
            .value(hasItem(DEFAULT_ORDER_CLASSIFICATION_ID.intValue()))
            .jsonPath("$.[*].posId")
            .value(hasItem(DEFAULT_POS_ID.intValue()))
            .jsonPath("$.[*].admissionDate")
            .value(hasItem(DEFAULT_ADMISSION_DATE.toString()))
            .jsonPath("$.[*].dischargeDate")
            .value(hasItem(DEFAULT_DISCHARGE_DATE.toString()))
            .jsonPath("$.[*].discountPercentage")
            .value(hasItem(DEFAULT_DISCOUNT_PERCENTAGE.intValue()))
            .jsonPath("$.[*].poNumber")
            .value(hasItem(DEFAULT_PO_NUMBER))
            .jsonPath("$.[*].userField1")
            .value(hasItem(DEFAULT_USER_FIELD_1))
            .jsonPath("$.[*].userField2")
            .value(hasItem(DEFAULT_USER_FIELD_2))
            .jsonPath("$.[*].userField3")
            .value(hasItem(DEFAULT_USER_FIELD_3))
            .jsonPath("$.[*].userField4")
            .value(hasItem(DEFAULT_USER_FIELD_4))
            .jsonPath("$.[*].reference")
            .value(hasItem(DEFAULT_REFERENCE))
            .jsonPath("$.[*].wipStatus")
            .value(hasItem(DEFAULT_WIP_STATUS))
            .jsonPath("$.[*].wipDaysInState")
            .value(hasItem(DEFAULT_WIP_DAYS_IN_STATE.intValue()))
            .jsonPath("$.[*].wipAssignedToId")
            .value(hasItem(DEFAULT_WIP_ASSIGNED_TO_ID.intValue()))
            .jsonPath("$.[*].wipDateNeeded")
            .value(hasItem(DEFAULT_WIP_DATE_NEEDED.toString()))
            .jsonPath("$.[*].completedStatus")
            .value(hasItem(DEFAULT_COMPLETED_STATUS))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS))
            .jsonPath("$.[*].createdById")
            .value(hasItem(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.[*].createdDate")
            .value(hasItem(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.[*].cityName")
            .value(hasItem(DEFAULT_CITY_NAME))
            .jsonPath("$.[*].stateName")
            .value(hasItem(DEFAULT_STATE_NAME))
            .jsonPath("$.[*].zipCode")
            .value(hasItem(DEFAULT_ZIP_CODE))
            .jsonPath("$.[*].deliveryCityName")
            .value(hasItem(DEFAULT_DELIVERY_CITY_NAME))
            .jsonPath("$.[*].deliveryStateName")
            .value(hasItem(DEFAULT_DELIVERY_STATE_NAME))
            .jsonPath("$.[*].deliveryZipCode")
            .value(hasItem(DEFAULT_DELIVERY_ZIP_CODE))
            .jsonPath("$.[*].patientDod")
            .value(hasItem(DEFAULT_PATIENT_DOD.toString()))
            .jsonPath("$.[*].posName")
            .value(hasItem(DEFAULT_POS_NAME))
            .jsonPath("$.[*].updatedById")
            .value(hasItem(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.[*].confirmationById")
            .value(hasItem(DEFAULT_CONFIRMATION_BY_ID.intValue()))
            .jsonPath("$.[*].confirmationByName")
            .value(hasItem(DEFAULT_CONFIRMATION_BY_NAME))
            .jsonPath("$.[*].confirmationDate")
            .value(hasItem(DEFAULT_CONFIRMATION_DATE.toString()))
            .jsonPath("$.[*].createdByName")
            .value(hasItem(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.[*].updatedByName")
            .value(hasItem(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.[*].updatedDate")
            .value(hasItem(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.[*].salesOrderMasterUuid")
            .value(hasItem(DEFAULT_SALES_ORDER_MASTER_UUID.toString()))
            .jsonPath("$.[*].branchContactPersonName")
            .value(hasItem(DEFAULT_BRANCH_CONTACT_PERSON_NAME))
            .jsonPath("$.[*].branchNpi")
            .value(hasItem(DEFAULT_BRANCH_NPI))
            .jsonPath("$.[*].branchEin")
            .value(hasItem(DEFAULT_BRANCH_EIN))
            .jsonPath("$.[*].branchContactNo1")
            .value(hasItem(DEFAULT_BRANCH_CONTACT_NO_1))
            .jsonPath("$.[*].branchContactNo2")
            .value(hasItem(DEFAULT_BRANCH_CONTACT_NO_2))
            .jsonPath("$.[*].patientIdNo")
            .value(hasItem(DEFAULT_PATIENT_ID_NO))
            .jsonPath("$.[*].posCode")
            .value(hasItem(DEFAULT_POS_CODE))
            .jsonPath("$.[*].eclaimCarrierName")
            .value(hasItem(DEFAULT_ECLAIM_CARRIER_NAME))
            .jsonPath("$.[*].planParticipationCode")
            .value(hasItem(DEFAULT_PLAN_PARTICIPATION_CODE))
            .jsonPath("$.[*].patientMemberId")
            .value(hasItem(DEFAULT_PATIENT_MEMBER_ID))
            .jsonPath("$.[*].contactPersonName")
            .value(hasItem(DEFAULT_CONTACT_PERSON_NAME))
            .jsonPath("$.[*].providerType")
            .value(hasItem(DEFAULT_PROVIDER_TYPE))
            .jsonPath("$.[*].branchAddressLine1")
            .value(hasItem(DEFAULT_BRANCH_ADDRESS_LINE_1))
            .jsonPath("$.[*].branchAddressLine2")
            .value(hasItem(DEFAULT_BRANCH_ADDRESS_LINE_2))
            .jsonPath("$.[*].branchCity")
            .value(hasItem(DEFAULT_BRANCH_CITY))
            .jsonPath("$.[*].branchState")
            .value(hasItem(DEFAULT_BRANCH_STATE))
            .jsonPath("$.[*].branchZipCode")
            .value(hasItem(DEFAULT_BRANCH_ZIP_CODE))
            .jsonPath("$.[*].patientDeliveryAddressLine1")
            .value(hasItem(DEFAULT_PATIENT_DELIVERY_ADDRESS_LINE_1))
            .jsonPath("$.[*].patientDeliveryAddressLine2")
            .value(hasItem(DEFAULT_PATIENT_DELIVERY_ADDRESS_LINE_2))
            .jsonPath("$.[*].patientDeliveryCity")
            .value(hasItem(DEFAULT_PATIENT_DELIVERY_CITY))
            .jsonPath("$.[*].patientDeliveryState")
            .value(hasItem(DEFAULT_PATIENT_DELIVERY_STATE))
            .jsonPath("$.[*].patientDeliveryCountry")
            .value(hasItem(DEFAULT_PATIENT_DELIVERY_COUNTRY))
            .jsonPath("$.[*].patientDeliveryZip")
            .value(hasItem(DEFAULT_PATIENT_DELIVERY_ZIP))
            .jsonPath("$.[*].patientBillingAddressLine1")
            .value(hasItem(DEFAULT_PATIENT_BILLING_ADDRESS_LINE_1))
            .jsonPath("$.[*].patientBillingAddressLine2")
            .value(hasItem(DEFAULT_PATIENT_BILLING_ADDRESS_LINE_2))
            .jsonPath("$.[*].patientBillingCity")
            .value(hasItem(DEFAULT_PATIENT_BILLING_CITY))
            .jsonPath("$.[*].patientBillingState")
            .value(hasItem(DEFAULT_PATIENT_BILLING_STATE))
            .jsonPath("$.[*].patientBillingCountry")
            .value(hasItem(DEFAULT_PATIENT_BILLING_COUNTRY))
            .jsonPath("$.[*].patientBillingZip")
            .value(hasItem(DEFAULT_PATIENT_BILLING_ZIP))
            .jsonPath("$.[*].patientFax")
            .value(hasItem(DEFAULT_PATIENT_FAX))
            .jsonPath("$.[*].branchFax")
            .value(hasItem(DEFAULT_BRANCH_FAX))
            .jsonPath("$.[*].patientEmail")
            .value(hasItem(DEFAULT_PATIENT_EMAIL))
            .jsonPath("$.[*].relationship")
            .value(hasItem(DEFAULT_RELATIONSHIP))
            .jsonPath("$.[*].modeOfContact")
            .value(hasItem(DEFAULT_MODE_OF_CONTACT))
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
            .jsonPath("$.[*].insuredGender")
            .value(hasItem(DEFAULT_INSURED_GENDER))
            .jsonPath("$.[*].patientRelationshipInsured")
            .value(hasItem(DEFAULT_PATIENT_RELATIONSHIP_INSURED))
            .jsonPath("$.[*].patientConditionEmployment")
            .value(hasItem(DEFAULT_PATIENT_CONDITION_EMPLOYMENT))
            .jsonPath("$.[*].patientConditionAutoAccident")
            .value(hasItem(DEFAULT_PATIENT_CONDITION_AUTO_ACCIDENT))
            .jsonPath("$.[*].patientConditionOtherAccident")
            .value(hasItem(DEFAULT_PATIENT_CONDITION_OTHER_ACCIDENT))
            .jsonPath("$.[*].billingProviderTaxonomy")
            .value(hasItem(DEFAULT_BILLING_PROVIDER_TAXONOMY))
            .jsonPath("$.[*].billingProviderNpi")
            .value(hasItem(DEFAULT_BILLING_PROVIDER_NPI))
            .jsonPath("$.[*].billingProviderOrganisationName")
            .value(hasItem(DEFAULT_BILLING_PROVIDER_ORGANISATION_NAME))
            .jsonPath("$.[*].billingProviderAddressLine1")
            .value(hasItem(DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_1))
            .jsonPath("$.[*].billingProviderAddressLine2")
            .value(hasItem(DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_2))
            .jsonPath("$.[*].billingProviderCity")
            .value(hasItem(DEFAULT_BILLING_PROVIDER_CITY))
            .jsonPath("$.[*].billingProviderState")
            .value(hasItem(DEFAULT_BILLING_PROVIDER_STATE))
            .jsonPath("$.[*].billingProviderCountry")
            .value(hasItem(DEFAULT_BILLING_PROVIDER_COUNTRY))
            .jsonPath("$.[*].billingProviderZipCode")
            .value(hasItem(DEFAULT_BILLING_PROVIDER_ZIP_CODE))
            .jsonPath("$.[*].insuredDob")
            .value(hasItem(DEFAULT_INSURED_DOB.toString()))
            .jsonPath("$.[*].branchCountry")
            .value(hasItem(DEFAULT_BRANCH_COUNTRY))
            .jsonPath("$.[*].branchTaxonomy")
            .value(hasItem(DEFAULT_BRANCH_TAXONOMY))
            .jsonPath("$.[*].primaryInsurerPriceTableId")
            .value(hasItem(DEFAULT_PRIMARY_INSURER_PRICE_TABLE_ID.intValue()))
            .jsonPath("$.[*].primaryInsurerPriceTableName")
            .value(hasItem(DEFAULT_PRIMARY_INSURER_PRICE_TABLE_NAME))
            .jsonPath("$.[*].inventoryLocationName")
            .value(hasItem(DEFAULT_INVENTORY_LOCATION_NAME))
            .jsonPath("$.[*].soControlNumber")
            .value(hasItem(DEFAULT_SO_CONTROL_NUMBER));
    }

    @Test
    void getSalesOrderMaster() {
        // Initialize the database
        salesOrderMasterRepository.save(salesOrderMaster).block();

        // Get the salesOrderMaster
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, salesOrderMaster.getSalesOrderId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.salesOrderId")
            .value(is(salesOrderMaster.getSalesOrderId().intValue()))
            .jsonPath("$.salesOrderNo")
            .value(is(DEFAULT_SALES_ORDER_NO))
            .jsonPath("$.patientId")
            .value(is(DEFAULT_PATIENT_ID.intValue()))
            .jsonPath("$.patientFirstName")
            .value(is(DEFAULT_PATIENT_FIRST_NAME))
            .jsonPath("$.patientMiddleName")
            .value(is(DEFAULT_PATIENT_MIDDLE_NAME))
            .jsonPath("$.patientLastName")
            .value(is(DEFAULT_PATIENT_LAST_NAME))
            .jsonPath("$.patientAddressLine1")
            .value(is(DEFAULT_PATIENT_ADDRESS_LINE_1))
            .jsonPath("$.patientAddressLine2")
            .value(is(DEFAULT_PATIENT_ADDRESS_LINE_2))
            .jsonPath("$.patientContactNo1")
            .value(is(DEFAULT_PATIENT_CONTACT_NO_1))
            .jsonPath("$.patientContactNo2")
            .value(is(DEFAULT_PATIENT_CONTACT_NO_2))
            .jsonPath("$.patientDob")
            .value(is(DEFAULT_PATIENT_DOB.toString()))
            .jsonPath("$.patientHeight")
            .value(is(DEFAULT_PATIENT_HEIGHT.doubleValue()))
            .jsonPath("$.patientWeight")
            .value(is(DEFAULT_PATIENT_WEIGHT.doubleValue()))
            .jsonPath("$.patientSsn")
            .value(is(DEFAULT_PATIENT_SSN))
            .jsonPath("$.patientGender")
            .value(is(DEFAULT_PATIENT_GENDER))
            .jsonPath("$.hipaaOnFileStatus")
            .value(is(DEFAULT_HIPAA_ON_FILE_STATUS))
            .jsonPath("$.patientBranchId")
            .value(is(DEFAULT_PATIENT_BRANCH_ID.intValue()))
            .jsonPath("$.branchName")
            .value(is(DEFAULT_BRANCH_NAME))
            .jsonPath("$.deliveryScheduleDatetime")
            .value(is(DEFAULT_DELIVERY_SCHEDULE_DATETIME.toString()))
            .jsonPath("$.deliveryActualDatetime")
            .value(is(DEFAULT_DELIVERY_ACTUAL_DATETIME.toString()))
            .jsonPath("$.deliveryAddressLine1")
            .value(is(DEFAULT_DELIVERY_ADDRESS_LINE_1))
            .jsonPath("$.deliveryAddressLine2")
            .value(is(DEFAULT_DELIVERY_ADDRESS_LINE_2))
            .jsonPath("$.deliveryPhoneNo1")
            .value(is(DEFAULT_DELIVERY_PHONE_NO_1))
            .jsonPath("$.deliveryPhoneNo2")
            .value(is(DEFAULT_DELIVERY_PHONE_NO_2))
            .jsonPath("$.facilityId")
            .value(is(DEFAULT_FACILITY_ID.intValue()))
            .jsonPath("$.facilityName")
            .value(is(DEFAULT_FACILITY_NAME))
            .jsonPath("$.facilityNpi")
            .value(is(DEFAULT_FACILITY_NPI))
            .jsonPath("$.taxZoneId")
            .value(is(DEFAULT_TAX_ZONE_ID.intValue()))
            .jsonPath("$.taxRate")
            .value(is(DEFAULT_TAX_RATE.doubleValue()))
            .jsonPath("$.salesOrderNote")
            .value(is(DEFAULT_SALES_ORDER_NOTE))
            .jsonPath("$.deliveryNote")
            .value(is(DEFAULT_DELIVERY_NOTE))
            .jsonPath("$.deliveryTechnician")
            .value(is(DEFAULT_DELIVERY_TECHNICIAN))
            .jsonPath("$.signatureRequiredStatus")
            .value(is(DEFAULT_SIGNATURE_REQUIRED_STATUS))
            .jsonPath("$.podStatus")
            .value(is(DEFAULT_POD_STATUS))
            .jsonPath("$.podStatusDatetime")
            .value(is(DEFAULT_POD_STATUS_DATETIME.toString()))
            .jsonPath("$.podLastMessage")
            .value(is(DEFAULT_POD_LAST_MESSAGE))
            .jsonPath("$.podMessageDatetime")
            .value(is(DEFAULT_POD_MESSAGE_DATETIME.toString()))
            .jsonPath("$.mutualHoldStatus")
            .value(is(DEFAULT_MUTUAL_HOLD_STATUS))
            .jsonPath("$.holdStatus")
            .value(is(DEFAULT_HOLD_STATUS))
            .jsonPath("$.holdReasonDescription")
            .value(is(DEFAULT_HOLD_REASON_DESCRIPTION))
            .jsonPath("$.stopDate")
            .value(is(DEFAULT_STOP_DATE.toString()))
            .jsonPath("$.stopReasonDescription")
            .value(is(DEFAULT_STOP_REASON_DESCRIPTION))
            .jsonPath("$.branchId")
            .value(is(DEFAULT_BRANCH_ID.intValue()))
            .jsonPath("$.billingBranchName")
            .value(is(DEFAULT_BILLING_BRANCH_NAME))
            .jsonPath("$.inventoryLocationId")
            .value(is(DEFAULT_INVENTORY_LOCATION_ID.intValue()))
            .jsonPath("$.orderStatus")
            .value(is(DEFAULT_ORDER_STATUS))
            .jsonPath("$.orderClassificationId")
            .value(is(DEFAULT_ORDER_CLASSIFICATION_ID.intValue()))
            .jsonPath("$.posId")
            .value(is(DEFAULT_POS_ID.intValue()))
            .jsonPath("$.admissionDate")
            .value(is(DEFAULT_ADMISSION_DATE.toString()))
            .jsonPath("$.dischargeDate")
            .value(is(DEFAULT_DISCHARGE_DATE.toString()))
            .jsonPath("$.discountPercentage")
            .value(is(DEFAULT_DISCOUNT_PERCENTAGE.intValue()))
            .jsonPath("$.poNumber")
            .value(is(DEFAULT_PO_NUMBER))
            .jsonPath("$.userField1")
            .value(is(DEFAULT_USER_FIELD_1))
            .jsonPath("$.userField2")
            .value(is(DEFAULT_USER_FIELD_2))
            .jsonPath("$.userField3")
            .value(is(DEFAULT_USER_FIELD_3))
            .jsonPath("$.userField4")
            .value(is(DEFAULT_USER_FIELD_4))
            .jsonPath("$.reference")
            .value(is(DEFAULT_REFERENCE))
            .jsonPath("$.wipStatus")
            .value(is(DEFAULT_WIP_STATUS))
            .jsonPath("$.wipDaysInState")
            .value(is(DEFAULT_WIP_DAYS_IN_STATE.intValue()))
            .jsonPath("$.wipAssignedToId")
            .value(is(DEFAULT_WIP_ASSIGNED_TO_ID.intValue()))
            .jsonPath("$.wipDateNeeded")
            .value(is(DEFAULT_WIP_DATE_NEEDED.toString()))
            .jsonPath("$.completedStatus")
            .value(is(DEFAULT_COMPLETED_STATUS))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS))
            .jsonPath("$.createdById")
            .value(is(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.createdDate")
            .value(is(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.cityName")
            .value(is(DEFAULT_CITY_NAME))
            .jsonPath("$.stateName")
            .value(is(DEFAULT_STATE_NAME))
            .jsonPath("$.zipCode")
            .value(is(DEFAULT_ZIP_CODE))
            .jsonPath("$.deliveryCityName")
            .value(is(DEFAULT_DELIVERY_CITY_NAME))
            .jsonPath("$.deliveryStateName")
            .value(is(DEFAULT_DELIVERY_STATE_NAME))
            .jsonPath("$.deliveryZipCode")
            .value(is(DEFAULT_DELIVERY_ZIP_CODE))
            .jsonPath("$.patientDod")
            .value(is(DEFAULT_PATIENT_DOD.toString()))
            .jsonPath("$.posName")
            .value(is(DEFAULT_POS_NAME))
            .jsonPath("$.updatedById")
            .value(is(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.confirmationById")
            .value(is(DEFAULT_CONFIRMATION_BY_ID.intValue()))
            .jsonPath("$.confirmationByName")
            .value(is(DEFAULT_CONFIRMATION_BY_NAME))
            .jsonPath("$.confirmationDate")
            .value(is(DEFAULT_CONFIRMATION_DATE.toString()))
            .jsonPath("$.createdByName")
            .value(is(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.updatedByName")
            .value(is(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.updatedDate")
            .value(is(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.salesOrderMasterUuid")
            .value(is(DEFAULT_SALES_ORDER_MASTER_UUID.toString()))
            .jsonPath("$.branchContactPersonName")
            .value(is(DEFAULT_BRANCH_CONTACT_PERSON_NAME))
            .jsonPath("$.branchNpi")
            .value(is(DEFAULT_BRANCH_NPI))
            .jsonPath("$.branchEin")
            .value(is(DEFAULT_BRANCH_EIN))
            .jsonPath("$.branchContactNo1")
            .value(is(DEFAULT_BRANCH_CONTACT_NO_1))
            .jsonPath("$.branchContactNo2")
            .value(is(DEFAULT_BRANCH_CONTACT_NO_2))
            .jsonPath("$.patientIdNo")
            .value(is(DEFAULT_PATIENT_ID_NO))
            .jsonPath("$.posCode")
            .value(is(DEFAULT_POS_CODE))
            .jsonPath("$.eclaimCarrierName")
            .value(is(DEFAULT_ECLAIM_CARRIER_NAME))
            .jsonPath("$.planParticipationCode")
            .value(is(DEFAULT_PLAN_PARTICIPATION_CODE))
            .jsonPath("$.patientMemberId")
            .value(is(DEFAULT_PATIENT_MEMBER_ID))
            .jsonPath("$.contactPersonName")
            .value(is(DEFAULT_CONTACT_PERSON_NAME))
            .jsonPath("$.providerType")
            .value(is(DEFAULT_PROVIDER_TYPE))
            .jsonPath("$.branchAddressLine1")
            .value(is(DEFAULT_BRANCH_ADDRESS_LINE_1))
            .jsonPath("$.branchAddressLine2")
            .value(is(DEFAULT_BRANCH_ADDRESS_LINE_2))
            .jsonPath("$.branchCity")
            .value(is(DEFAULT_BRANCH_CITY))
            .jsonPath("$.branchState")
            .value(is(DEFAULT_BRANCH_STATE))
            .jsonPath("$.branchZipCode")
            .value(is(DEFAULT_BRANCH_ZIP_CODE))
            .jsonPath("$.patientDeliveryAddressLine1")
            .value(is(DEFAULT_PATIENT_DELIVERY_ADDRESS_LINE_1))
            .jsonPath("$.patientDeliveryAddressLine2")
            .value(is(DEFAULT_PATIENT_DELIVERY_ADDRESS_LINE_2))
            .jsonPath("$.patientDeliveryCity")
            .value(is(DEFAULT_PATIENT_DELIVERY_CITY))
            .jsonPath("$.patientDeliveryState")
            .value(is(DEFAULT_PATIENT_DELIVERY_STATE))
            .jsonPath("$.patientDeliveryCountry")
            .value(is(DEFAULT_PATIENT_DELIVERY_COUNTRY))
            .jsonPath("$.patientDeliveryZip")
            .value(is(DEFAULT_PATIENT_DELIVERY_ZIP))
            .jsonPath("$.patientBillingAddressLine1")
            .value(is(DEFAULT_PATIENT_BILLING_ADDRESS_LINE_1))
            .jsonPath("$.patientBillingAddressLine2")
            .value(is(DEFAULT_PATIENT_BILLING_ADDRESS_LINE_2))
            .jsonPath("$.patientBillingCity")
            .value(is(DEFAULT_PATIENT_BILLING_CITY))
            .jsonPath("$.patientBillingState")
            .value(is(DEFAULT_PATIENT_BILLING_STATE))
            .jsonPath("$.patientBillingCountry")
            .value(is(DEFAULT_PATIENT_BILLING_COUNTRY))
            .jsonPath("$.patientBillingZip")
            .value(is(DEFAULT_PATIENT_BILLING_ZIP))
            .jsonPath("$.patientFax")
            .value(is(DEFAULT_PATIENT_FAX))
            .jsonPath("$.branchFax")
            .value(is(DEFAULT_BRANCH_FAX))
            .jsonPath("$.patientEmail")
            .value(is(DEFAULT_PATIENT_EMAIL))
            .jsonPath("$.relationship")
            .value(is(DEFAULT_RELATIONSHIP))
            .jsonPath("$.modeOfContact")
            .value(is(DEFAULT_MODE_OF_CONTACT))
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
            .jsonPath("$.insuredGender")
            .value(is(DEFAULT_INSURED_GENDER))
            .jsonPath("$.patientRelationshipInsured")
            .value(is(DEFAULT_PATIENT_RELATIONSHIP_INSURED))
            .jsonPath("$.patientConditionEmployment")
            .value(is(DEFAULT_PATIENT_CONDITION_EMPLOYMENT))
            .jsonPath("$.patientConditionAutoAccident")
            .value(is(DEFAULT_PATIENT_CONDITION_AUTO_ACCIDENT))
            .jsonPath("$.patientConditionOtherAccident")
            .value(is(DEFAULT_PATIENT_CONDITION_OTHER_ACCIDENT))
            .jsonPath("$.billingProviderTaxonomy")
            .value(is(DEFAULT_BILLING_PROVIDER_TAXONOMY))
            .jsonPath("$.billingProviderNpi")
            .value(is(DEFAULT_BILLING_PROVIDER_NPI))
            .jsonPath("$.billingProviderOrganisationName")
            .value(is(DEFAULT_BILLING_PROVIDER_ORGANISATION_NAME))
            .jsonPath("$.billingProviderAddressLine1")
            .value(is(DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_1))
            .jsonPath("$.billingProviderAddressLine2")
            .value(is(DEFAULT_BILLING_PROVIDER_ADDRESS_LINE_2))
            .jsonPath("$.billingProviderCity")
            .value(is(DEFAULT_BILLING_PROVIDER_CITY))
            .jsonPath("$.billingProviderState")
            .value(is(DEFAULT_BILLING_PROVIDER_STATE))
            .jsonPath("$.billingProviderCountry")
            .value(is(DEFAULT_BILLING_PROVIDER_COUNTRY))
            .jsonPath("$.billingProviderZipCode")
            .value(is(DEFAULT_BILLING_PROVIDER_ZIP_CODE))
            .jsonPath("$.insuredDob")
            .value(is(DEFAULT_INSURED_DOB.toString()))
            .jsonPath("$.branchCountry")
            .value(is(DEFAULT_BRANCH_COUNTRY))
            .jsonPath("$.branchTaxonomy")
            .value(is(DEFAULT_BRANCH_TAXONOMY))
            .jsonPath("$.primaryInsurerPriceTableId")
            .value(is(DEFAULT_PRIMARY_INSURER_PRICE_TABLE_ID.intValue()))
            .jsonPath("$.primaryInsurerPriceTableName")
            .value(is(DEFAULT_PRIMARY_INSURER_PRICE_TABLE_NAME))
            .jsonPath("$.inventoryLocationName")
            .value(is(DEFAULT_INVENTORY_LOCATION_NAME))
            .jsonPath("$.soControlNumber")
            .value(is(DEFAULT_SO_CONTROL_NUMBER));
    }

    @Test
    void getNonExistingSalesOrderMaster() {
        // Get the salesOrderMaster
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putNewSalesOrderMaster() throws Exception {
        // Initialize the database
        salesOrderMasterRepository.save(salesOrderMaster).block();

        int databaseSizeBeforeUpdate = salesOrderMasterRepository.findAll().collectList().block().size();

        // Update the salesOrderMaster
        SalesOrderMaster updatedSalesOrderMaster = salesOrderMasterRepository.findById(salesOrderMaster.getSalesOrderId()).block();
        updatedSalesOrderMaster
            .salesOrderNo(UPDATED_SALES_ORDER_NO)
            .patientId(UPDATED_PATIENT_ID)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientMiddleName(UPDATED_PATIENT_MIDDLE_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .patientAddressLine1(UPDATED_PATIENT_ADDRESS_LINE_1)
            .patientAddressLine2(UPDATED_PATIENT_ADDRESS_LINE_2)
            .patientContactNo1(UPDATED_PATIENT_CONTACT_NO_1)
            .patientContactNo2(UPDATED_PATIENT_CONTACT_NO_2)
            .patientDob(UPDATED_PATIENT_DOB)
            .patientHeight(UPDATED_PATIENT_HEIGHT)
            .patientWeight(UPDATED_PATIENT_WEIGHT)
            .patientSsn(UPDATED_PATIENT_SSN)
            .patientGender(UPDATED_PATIENT_GENDER)
            .hipaaOnFileStatus(UPDATED_HIPAA_ON_FILE_STATUS)
            .patientBranchId(UPDATED_PATIENT_BRANCH_ID)
            .branchName(UPDATED_BRANCH_NAME)
            .deliveryScheduleDatetime(UPDATED_DELIVERY_SCHEDULE_DATETIME)
            .deliveryActualDatetime(UPDATED_DELIVERY_ACTUAL_DATETIME)
            .deliveryAddressLine1(UPDATED_DELIVERY_ADDRESS_LINE_1)
            .deliveryAddressLine2(UPDATED_DELIVERY_ADDRESS_LINE_2)
            .deliveryPhoneNo1(UPDATED_DELIVERY_PHONE_NO_1)
            .deliveryPhoneNo2(UPDATED_DELIVERY_PHONE_NO_2)
            .facilityId(UPDATED_FACILITY_ID)
            .facilityName(UPDATED_FACILITY_NAME)
            .facilityNpi(UPDATED_FACILITY_NPI)
            .taxZoneId(UPDATED_TAX_ZONE_ID)
            .taxRate(UPDATED_TAX_RATE)
            .salesOrderNote(UPDATED_SALES_ORDER_NOTE)
            .deliveryNote(UPDATED_DELIVERY_NOTE)
            .deliveryTechnician(UPDATED_DELIVERY_TECHNICIAN)
            .signatureRequiredStatus(UPDATED_SIGNATURE_REQUIRED_STATUS)
            .podStatus(UPDATED_POD_STATUS)
            .podStatusDatetime(UPDATED_POD_STATUS_DATETIME)
            .podLastMessage(UPDATED_POD_LAST_MESSAGE)
            .podMessageDatetime(UPDATED_POD_MESSAGE_DATETIME)
            .mutualHoldStatus(UPDATED_MUTUAL_HOLD_STATUS)
            .holdStatus(UPDATED_HOLD_STATUS)
            .holdReasonDescription(UPDATED_HOLD_REASON_DESCRIPTION)
            .stopDate(UPDATED_STOP_DATE)
            .stopReasonDescription(UPDATED_STOP_REASON_DESCRIPTION)
            .branchId(UPDATED_BRANCH_ID)
            .billingBranchName(UPDATED_BILLING_BRANCH_NAME)
            .inventoryLocationId(UPDATED_INVENTORY_LOCATION_ID)
            .orderStatus(UPDATED_ORDER_STATUS)
            .orderClassificationId(UPDATED_ORDER_CLASSIFICATION_ID)
            .posId(UPDATED_POS_ID)
            .admissionDate(UPDATED_ADMISSION_DATE)
            .dischargeDate(UPDATED_DISCHARGE_DATE)
            .discountPercentage(UPDATED_DISCOUNT_PERCENTAGE)
            .poNumber(UPDATED_PO_NUMBER)
            .userField1(UPDATED_USER_FIELD_1)
            .userField2(UPDATED_USER_FIELD_2)
            .userField3(UPDATED_USER_FIELD_3)
            .userField4(UPDATED_USER_FIELD_4)
            .reference(UPDATED_REFERENCE)
            .wipStatus(UPDATED_WIP_STATUS)
            .wipDaysInState(UPDATED_WIP_DAYS_IN_STATE)
            .wipAssignedToId(UPDATED_WIP_ASSIGNED_TO_ID)
            .wipDateNeeded(UPDATED_WIP_DATE_NEEDED)
            .completedStatus(UPDATED_COMPLETED_STATUS)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .cityName(UPDATED_CITY_NAME)
            .stateName(UPDATED_STATE_NAME)
            .zipCode(UPDATED_ZIP_CODE)
            .deliveryCityName(UPDATED_DELIVERY_CITY_NAME)
            .deliveryStateName(UPDATED_DELIVERY_STATE_NAME)
            .deliveryZipCode(UPDATED_DELIVERY_ZIP_CODE)
            .patientDod(UPDATED_PATIENT_DOD)
            .posName(UPDATED_POS_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .confirmationById(UPDATED_CONFIRMATION_BY_ID)
            .confirmationByName(UPDATED_CONFIRMATION_BY_NAME)
            .confirmationDate(UPDATED_CONFIRMATION_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .salesOrderMasterUuid(UPDATED_SALES_ORDER_MASTER_UUID)
            .branchContactPersonName(UPDATED_BRANCH_CONTACT_PERSON_NAME)
            .branchNpi(UPDATED_BRANCH_NPI)
            .branchEin(UPDATED_BRANCH_EIN)
            .branchContactNo1(UPDATED_BRANCH_CONTACT_NO_1)
            .branchContactNo2(UPDATED_BRANCH_CONTACT_NO_2)
            .patientIdNo(UPDATED_PATIENT_ID_NO)
            .posCode(UPDATED_POS_CODE)
            .eclaimCarrierName(UPDATED_ECLAIM_CARRIER_NAME)
            .planParticipationCode(UPDATED_PLAN_PARTICIPATION_CODE)
            .patientMemberId(UPDATED_PATIENT_MEMBER_ID)
            .contactPersonName(UPDATED_CONTACT_PERSON_NAME)
            .providerType(UPDATED_PROVIDER_TYPE)
            .branchAddressLine1(UPDATED_BRANCH_ADDRESS_LINE_1)
            .branchAddressLine2(UPDATED_BRANCH_ADDRESS_LINE_2)
            .branchCity(UPDATED_BRANCH_CITY)
            .branchState(UPDATED_BRANCH_STATE)
            .branchZipCode(UPDATED_BRANCH_ZIP_CODE)
            .patientDeliveryAddressLine1(UPDATED_PATIENT_DELIVERY_ADDRESS_LINE_1)
            .patientDeliveryAddressLine2(UPDATED_PATIENT_DELIVERY_ADDRESS_LINE_2)
            .patientDeliveryCity(UPDATED_PATIENT_DELIVERY_CITY)
            .patientDeliveryState(UPDATED_PATIENT_DELIVERY_STATE)
            .patientDeliveryCountry(UPDATED_PATIENT_DELIVERY_COUNTRY)
            .patientDeliveryZip(UPDATED_PATIENT_DELIVERY_ZIP)
            .patientBillingAddressLine1(UPDATED_PATIENT_BILLING_ADDRESS_LINE_1)
            .patientBillingAddressLine2(UPDATED_PATIENT_BILLING_ADDRESS_LINE_2)
            .patientBillingCity(UPDATED_PATIENT_BILLING_CITY)
            .patientBillingState(UPDATED_PATIENT_BILLING_STATE)
            .patientBillingCountry(UPDATED_PATIENT_BILLING_COUNTRY)
            .patientBillingZip(UPDATED_PATIENT_BILLING_ZIP)
            .patientFax(UPDATED_PATIENT_FAX)
            .branchFax(UPDATED_BRANCH_FAX)
            .patientEmail(UPDATED_PATIENT_EMAIL)
            .relationship(UPDATED_RELATIONSHIP)
            .modeOfContact(UPDATED_MODE_OF_CONTACT)
            .insuredFirstName(UPDATED_INSURED_FIRST_NAME)
            .insuredLastName(UPDATED_INSURED_LAST_NAME)
            .insuredAddressLine1(UPDATED_INSURED_ADDRESS_LINE_1)
            .insuredAddressLine2(UPDATED_INSURED_ADDRESS_LINE_2)
            .insuredCity(UPDATED_INSURED_CITY)
            .insuredState(UPDATED_INSURED_STATE)
            .insuredZip(UPDATED_INSURED_ZIP)
            .insuredContactNo(UPDATED_INSURED_CONTACT_NO)
            .insuredGender(UPDATED_INSURED_GENDER)
            .patientRelationshipInsured(UPDATED_PATIENT_RELATIONSHIP_INSURED)
            .patientConditionEmployment(UPDATED_PATIENT_CONDITION_EMPLOYMENT)
            .patientConditionAutoAccident(UPDATED_PATIENT_CONDITION_AUTO_ACCIDENT)
            .patientConditionOtherAccident(UPDATED_PATIENT_CONDITION_OTHER_ACCIDENT)
            .billingProviderTaxonomy(UPDATED_BILLING_PROVIDER_TAXONOMY)
            .billingProviderNpi(UPDATED_BILLING_PROVIDER_NPI)
            .billingProviderOrganisationName(UPDATED_BILLING_PROVIDER_ORGANISATION_NAME)
            .billingProviderAddressLine1(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_1)
            .billingProviderAddressLine2(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_2)
            .billingProviderCity(UPDATED_BILLING_PROVIDER_CITY)
            .billingProviderState(UPDATED_BILLING_PROVIDER_STATE)
            .billingProviderCountry(UPDATED_BILLING_PROVIDER_COUNTRY)
            .billingProviderZipCode(UPDATED_BILLING_PROVIDER_ZIP_CODE)
            .insuredDob(UPDATED_INSURED_DOB)
            .branchCountry(UPDATED_BRANCH_COUNTRY)
            .branchTaxonomy(UPDATED_BRANCH_TAXONOMY)
            .primaryInsurerPriceTableId(UPDATED_PRIMARY_INSURER_PRICE_TABLE_ID)
            .primaryInsurerPriceTableName(UPDATED_PRIMARY_INSURER_PRICE_TABLE_NAME)
            .inventoryLocationName(UPDATED_INVENTORY_LOCATION_NAME)
            .soControlNumber(UPDATED_SO_CONTROL_NUMBER);
        SalesOrderMasterDTO salesOrderMasterDTO = salesOrderMasterMapper.toDto(updatedSalesOrderMaster);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, salesOrderMasterDTO.getSalesOrderId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderMasterDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SalesOrderMaster in the database
        List<SalesOrderMaster> salesOrderMasterList = salesOrderMasterRepository.findAll().collectList().block();
        assertThat(salesOrderMasterList).hasSize(databaseSizeBeforeUpdate);
        SalesOrderMaster testSalesOrderMaster = salesOrderMasterList.get(salesOrderMasterList.size() - 1);
        assertThat(testSalesOrderMaster.getSalesOrderNo()).isEqualTo(UPDATED_SALES_ORDER_NO);
        assertThat(testSalesOrderMaster.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testSalesOrderMaster.getPatientFirstName()).isEqualTo(UPDATED_PATIENT_FIRST_NAME);
        assertThat(testSalesOrderMaster.getPatientMiddleName()).isEqualTo(UPDATED_PATIENT_MIDDLE_NAME);
        assertThat(testSalesOrderMaster.getPatientLastName()).isEqualTo(UPDATED_PATIENT_LAST_NAME);
        assertThat(testSalesOrderMaster.getPatientAddressLine1()).isEqualTo(UPDATED_PATIENT_ADDRESS_LINE_1);
        assertThat(testSalesOrderMaster.getPatientAddressLine2()).isEqualTo(UPDATED_PATIENT_ADDRESS_LINE_2);
        assertThat(testSalesOrderMaster.getPatientContactNo1()).isEqualTo(UPDATED_PATIENT_CONTACT_NO_1);
        assertThat(testSalesOrderMaster.getPatientContactNo2()).isEqualTo(UPDATED_PATIENT_CONTACT_NO_2);
        assertThat(testSalesOrderMaster.getPatientDob()).isEqualTo(UPDATED_PATIENT_DOB);
        assertThat(testSalesOrderMaster.getPatientHeight()).isEqualTo(UPDATED_PATIENT_HEIGHT);
        assertThat(testSalesOrderMaster.getPatientWeight()).isEqualTo(UPDATED_PATIENT_WEIGHT);
        assertThat(testSalesOrderMaster.getPatientSsn()).isEqualTo(UPDATED_PATIENT_SSN);
        assertThat(testSalesOrderMaster.getPatientGender()).isEqualTo(UPDATED_PATIENT_GENDER);
        assertThat(testSalesOrderMaster.getHipaaOnFileStatus()).isEqualTo(UPDATED_HIPAA_ON_FILE_STATUS);
        assertThat(testSalesOrderMaster.getPatientBranchId()).isEqualTo(UPDATED_PATIENT_BRANCH_ID);
        assertThat(testSalesOrderMaster.getBranchName()).isEqualTo(UPDATED_BRANCH_NAME);
        assertThat(testSalesOrderMaster.getDeliveryScheduleDatetime()).isEqualTo(UPDATED_DELIVERY_SCHEDULE_DATETIME);
        assertThat(testSalesOrderMaster.getDeliveryActualDatetime()).isEqualTo(UPDATED_DELIVERY_ACTUAL_DATETIME);
        assertThat(testSalesOrderMaster.getDeliveryAddressLine1()).isEqualTo(UPDATED_DELIVERY_ADDRESS_LINE_1);
        assertThat(testSalesOrderMaster.getDeliveryAddressLine2()).isEqualTo(UPDATED_DELIVERY_ADDRESS_LINE_2);
        assertThat(testSalesOrderMaster.getDeliveryPhoneNo1()).isEqualTo(UPDATED_DELIVERY_PHONE_NO_1);
        assertThat(testSalesOrderMaster.getDeliveryPhoneNo2()).isEqualTo(UPDATED_DELIVERY_PHONE_NO_2);
        assertThat(testSalesOrderMaster.getFacilityId()).isEqualTo(UPDATED_FACILITY_ID);
        assertThat(testSalesOrderMaster.getFacilityName()).isEqualTo(UPDATED_FACILITY_NAME);
        assertThat(testSalesOrderMaster.getFacilityNpi()).isEqualTo(UPDATED_FACILITY_NPI);
        assertThat(testSalesOrderMaster.getTaxZoneId()).isEqualTo(UPDATED_TAX_ZONE_ID);
        assertThat(testSalesOrderMaster.getTaxRate()).isEqualTo(UPDATED_TAX_RATE);
        assertThat(testSalesOrderMaster.getSalesOrderNote()).isEqualTo(UPDATED_SALES_ORDER_NOTE);
        assertThat(testSalesOrderMaster.getDeliveryNote()).isEqualTo(UPDATED_DELIVERY_NOTE);
        assertThat(testSalesOrderMaster.getDeliveryTechnician()).isEqualTo(UPDATED_DELIVERY_TECHNICIAN);
        assertThat(testSalesOrderMaster.getSignatureRequiredStatus()).isEqualTo(UPDATED_SIGNATURE_REQUIRED_STATUS);
        assertThat(testSalesOrderMaster.getPodStatus()).isEqualTo(UPDATED_POD_STATUS);
        assertThat(testSalesOrderMaster.getPodStatusDatetime()).isEqualTo(UPDATED_POD_STATUS_DATETIME);
        assertThat(testSalesOrderMaster.getPodLastMessage()).isEqualTo(UPDATED_POD_LAST_MESSAGE);
        assertThat(testSalesOrderMaster.getPodMessageDatetime()).isEqualTo(UPDATED_POD_MESSAGE_DATETIME);
        assertThat(testSalesOrderMaster.getMutualHoldStatus()).isEqualTo(UPDATED_MUTUAL_HOLD_STATUS);
        assertThat(testSalesOrderMaster.getHoldStatus()).isEqualTo(UPDATED_HOLD_STATUS);
        assertThat(testSalesOrderMaster.getHoldReasonDescription()).isEqualTo(UPDATED_HOLD_REASON_DESCRIPTION);
        assertThat(testSalesOrderMaster.getStopDate()).isEqualTo(UPDATED_STOP_DATE);
        assertThat(testSalesOrderMaster.getStopReasonDescription()).isEqualTo(UPDATED_STOP_REASON_DESCRIPTION);
        assertThat(testSalesOrderMaster.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
        assertThat(testSalesOrderMaster.getBillingBranchName()).isEqualTo(UPDATED_BILLING_BRANCH_NAME);
        assertThat(testSalesOrderMaster.getInventoryLocationId()).isEqualTo(UPDATED_INVENTORY_LOCATION_ID);
        assertThat(testSalesOrderMaster.getOrderStatus()).isEqualTo(UPDATED_ORDER_STATUS);
        assertThat(testSalesOrderMaster.getOrderClassificationId()).isEqualTo(UPDATED_ORDER_CLASSIFICATION_ID);
        assertThat(testSalesOrderMaster.getPosId()).isEqualTo(UPDATED_POS_ID);
        assertThat(testSalesOrderMaster.getAdmissionDate()).isEqualTo(UPDATED_ADMISSION_DATE);
        assertThat(testSalesOrderMaster.getDischargeDate()).isEqualTo(UPDATED_DISCHARGE_DATE);
        assertThat(testSalesOrderMaster.getDiscountPercentage()).isEqualTo(UPDATED_DISCOUNT_PERCENTAGE);
        assertThat(testSalesOrderMaster.getPoNumber()).isEqualTo(UPDATED_PO_NUMBER);
        assertThat(testSalesOrderMaster.getUserField1()).isEqualTo(UPDATED_USER_FIELD_1);
        assertThat(testSalesOrderMaster.getUserField2()).isEqualTo(UPDATED_USER_FIELD_2);
        assertThat(testSalesOrderMaster.getUserField3()).isEqualTo(UPDATED_USER_FIELD_3);
        assertThat(testSalesOrderMaster.getUserField4()).isEqualTo(UPDATED_USER_FIELD_4);
        assertThat(testSalesOrderMaster.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testSalesOrderMaster.getWipStatus()).isEqualTo(UPDATED_WIP_STATUS);
        assertThat(testSalesOrderMaster.getWipDaysInState()).isEqualTo(UPDATED_WIP_DAYS_IN_STATE);
        assertThat(testSalesOrderMaster.getWipAssignedToId()).isEqualTo(UPDATED_WIP_ASSIGNED_TO_ID);
        assertThat(testSalesOrderMaster.getWipDateNeeded()).isEqualTo(UPDATED_WIP_DATE_NEEDED);
        assertThat(testSalesOrderMaster.getCompletedStatus()).isEqualTo(UPDATED_COMPLETED_STATUS);
        assertThat(testSalesOrderMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSalesOrderMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testSalesOrderMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSalesOrderMaster.getCityName()).isEqualTo(UPDATED_CITY_NAME);
        assertThat(testSalesOrderMaster.getStateName()).isEqualTo(UPDATED_STATE_NAME);
        assertThat(testSalesOrderMaster.getZipCode()).isEqualTo(UPDATED_ZIP_CODE);
        assertThat(testSalesOrderMaster.getDeliveryCityName()).isEqualTo(UPDATED_DELIVERY_CITY_NAME);
        assertThat(testSalesOrderMaster.getDeliveryStateName()).isEqualTo(UPDATED_DELIVERY_STATE_NAME);
        assertThat(testSalesOrderMaster.getDeliveryZipCode()).isEqualTo(UPDATED_DELIVERY_ZIP_CODE);
        assertThat(testSalesOrderMaster.getPatientDod()).isEqualTo(UPDATED_PATIENT_DOD);
        assertThat(testSalesOrderMaster.getPosName()).isEqualTo(UPDATED_POS_NAME);
        assertThat(testSalesOrderMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testSalesOrderMaster.getConfirmationById()).isEqualTo(UPDATED_CONFIRMATION_BY_ID);
        assertThat(testSalesOrderMaster.getConfirmationByName()).isEqualTo(UPDATED_CONFIRMATION_BY_NAME);
        assertThat(testSalesOrderMaster.getConfirmationDate()).isEqualTo(UPDATED_CONFIRMATION_DATE);
        assertThat(testSalesOrderMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testSalesOrderMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testSalesOrderMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testSalesOrderMaster.getSalesOrderMasterUuid()).isEqualTo(UPDATED_SALES_ORDER_MASTER_UUID);
        assertThat(testSalesOrderMaster.getBranchContactPersonName()).isEqualTo(UPDATED_BRANCH_CONTACT_PERSON_NAME);
        assertThat(testSalesOrderMaster.getBranchNpi()).isEqualTo(UPDATED_BRANCH_NPI);
        assertThat(testSalesOrderMaster.getBranchEin()).isEqualTo(UPDATED_BRANCH_EIN);
        assertThat(testSalesOrderMaster.getBranchContactNo1()).isEqualTo(UPDATED_BRANCH_CONTACT_NO_1);
        assertThat(testSalesOrderMaster.getBranchContactNo2()).isEqualTo(UPDATED_BRANCH_CONTACT_NO_2);
        assertThat(testSalesOrderMaster.getPatientIdNo()).isEqualTo(UPDATED_PATIENT_ID_NO);
        assertThat(testSalesOrderMaster.getPosCode()).isEqualTo(UPDATED_POS_CODE);
        assertThat(testSalesOrderMaster.getEclaimCarrierName()).isEqualTo(UPDATED_ECLAIM_CARRIER_NAME);
        assertThat(testSalesOrderMaster.getPlanParticipationCode()).isEqualTo(UPDATED_PLAN_PARTICIPATION_CODE);
        assertThat(testSalesOrderMaster.getPatientMemberId()).isEqualTo(UPDATED_PATIENT_MEMBER_ID);
        assertThat(testSalesOrderMaster.getContactPersonName()).isEqualTo(UPDATED_CONTACT_PERSON_NAME);
        assertThat(testSalesOrderMaster.getProviderType()).isEqualTo(UPDATED_PROVIDER_TYPE);
        assertThat(testSalesOrderMaster.getBranchAddressLine1()).isEqualTo(UPDATED_BRANCH_ADDRESS_LINE_1);
        assertThat(testSalesOrderMaster.getBranchAddressLine2()).isEqualTo(UPDATED_BRANCH_ADDRESS_LINE_2);
        assertThat(testSalesOrderMaster.getBranchCity()).isEqualTo(UPDATED_BRANCH_CITY);
        assertThat(testSalesOrderMaster.getBranchState()).isEqualTo(UPDATED_BRANCH_STATE);
        assertThat(testSalesOrderMaster.getBranchZipCode()).isEqualTo(UPDATED_BRANCH_ZIP_CODE);
        assertThat(testSalesOrderMaster.getPatientDeliveryAddressLine1()).isEqualTo(UPDATED_PATIENT_DELIVERY_ADDRESS_LINE_1);
        assertThat(testSalesOrderMaster.getPatientDeliveryAddressLine2()).isEqualTo(UPDATED_PATIENT_DELIVERY_ADDRESS_LINE_2);
        assertThat(testSalesOrderMaster.getPatientDeliveryCity()).isEqualTo(UPDATED_PATIENT_DELIVERY_CITY);
        assertThat(testSalesOrderMaster.getPatientDeliveryState()).isEqualTo(UPDATED_PATIENT_DELIVERY_STATE);
        assertThat(testSalesOrderMaster.getPatientDeliveryCountry()).isEqualTo(UPDATED_PATIENT_DELIVERY_COUNTRY);
        assertThat(testSalesOrderMaster.getPatientDeliveryZip()).isEqualTo(UPDATED_PATIENT_DELIVERY_ZIP);
        assertThat(testSalesOrderMaster.getPatientBillingAddressLine1()).isEqualTo(UPDATED_PATIENT_BILLING_ADDRESS_LINE_1);
        assertThat(testSalesOrderMaster.getPatientBillingAddressLine2()).isEqualTo(UPDATED_PATIENT_BILLING_ADDRESS_LINE_2);
        assertThat(testSalesOrderMaster.getPatientBillingCity()).isEqualTo(UPDATED_PATIENT_BILLING_CITY);
        assertThat(testSalesOrderMaster.getPatientBillingState()).isEqualTo(UPDATED_PATIENT_BILLING_STATE);
        assertThat(testSalesOrderMaster.getPatientBillingCountry()).isEqualTo(UPDATED_PATIENT_BILLING_COUNTRY);
        assertThat(testSalesOrderMaster.getPatientBillingZip()).isEqualTo(UPDATED_PATIENT_BILLING_ZIP);
        assertThat(testSalesOrderMaster.getPatientFax()).isEqualTo(UPDATED_PATIENT_FAX);
        assertThat(testSalesOrderMaster.getBranchFax()).isEqualTo(UPDATED_BRANCH_FAX);
        assertThat(testSalesOrderMaster.getPatientEmail()).isEqualTo(UPDATED_PATIENT_EMAIL);
        assertThat(testSalesOrderMaster.getRelationship()).isEqualTo(UPDATED_RELATIONSHIP);
        assertThat(testSalesOrderMaster.getModeOfContact()).isEqualTo(UPDATED_MODE_OF_CONTACT);
        assertThat(testSalesOrderMaster.getInsuredFirstName()).isEqualTo(UPDATED_INSURED_FIRST_NAME);
        assertThat(testSalesOrderMaster.getInsuredLastName()).isEqualTo(UPDATED_INSURED_LAST_NAME);
        assertThat(testSalesOrderMaster.getInsuredAddressLine1()).isEqualTo(UPDATED_INSURED_ADDRESS_LINE_1);
        assertThat(testSalesOrderMaster.getInsuredAddressLine2()).isEqualTo(UPDATED_INSURED_ADDRESS_LINE_2);
        assertThat(testSalesOrderMaster.getInsuredCity()).isEqualTo(UPDATED_INSURED_CITY);
        assertThat(testSalesOrderMaster.getInsuredState()).isEqualTo(UPDATED_INSURED_STATE);
        assertThat(testSalesOrderMaster.getInsuredZip()).isEqualTo(UPDATED_INSURED_ZIP);
        assertThat(testSalesOrderMaster.getInsuredContactNo()).isEqualTo(UPDATED_INSURED_CONTACT_NO);
        assertThat(testSalesOrderMaster.getInsuredGender()).isEqualTo(UPDATED_INSURED_GENDER);
        assertThat(testSalesOrderMaster.getPatientRelationshipInsured()).isEqualTo(UPDATED_PATIENT_RELATIONSHIP_INSURED);
        assertThat(testSalesOrderMaster.getPatientConditionEmployment()).isEqualTo(UPDATED_PATIENT_CONDITION_EMPLOYMENT);
        assertThat(testSalesOrderMaster.getPatientConditionAutoAccident()).isEqualTo(UPDATED_PATIENT_CONDITION_AUTO_ACCIDENT);
        assertThat(testSalesOrderMaster.getPatientConditionOtherAccident()).isEqualTo(UPDATED_PATIENT_CONDITION_OTHER_ACCIDENT);
        assertThat(testSalesOrderMaster.getBillingProviderTaxonomy()).isEqualTo(UPDATED_BILLING_PROVIDER_TAXONOMY);
        assertThat(testSalesOrderMaster.getBillingProviderNpi()).isEqualTo(UPDATED_BILLING_PROVIDER_NPI);
        assertThat(testSalesOrderMaster.getBillingProviderOrganisationName()).isEqualTo(UPDATED_BILLING_PROVIDER_ORGANISATION_NAME);
        assertThat(testSalesOrderMaster.getBillingProviderAddressLine1()).isEqualTo(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_1);
        assertThat(testSalesOrderMaster.getBillingProviderAddressLine2()).isEqualTo(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_2);
        assertThat(testSalesOrderMaster.getBillingProviderCity()).isEqualTo(UPDATED_BILLING_PROVIDER_CITY);
        assertThat(testSalesOrderMaster.getBillingProviderState()).isEqualTo(UPDATED_BILLING_PROVIDER_STATE);
        assertThat(testSalesOrderMaster.getBillingProviderCountry()).isEqualTo(UPDATED_BILLING_PROVIDER_COUNTRY);
        assertThat(testSalesOrderMaster.getBillingProviderZipCode()).isEqualTo(UPDATED_BILLING_PROVIDER_ZIP_CODE);
        assertThat(testSalesOrderMaster.getInsuredDob()).isEqualTo(UPDATED_INSURED_DOB);
        assertThat(testSalesOrderMaster.getBranchCountry()).isEqualTo(UPDATED_BRANCH_COUNTRY);
        assertThat(testSalesOrderMaster.getBranchTaxonomy()).isEqualTo(UPDATED_BRANCH_TAXONOMY);
        assertThat(testSalesOrderMaster.getPrimaryInsurerPriceTableId()).isEqualTo(UPDATED_PRIMARY_INSURER_PRICE_TABLE_ID);
        assertThat(testSalesOrderMaster.getPrimaryInsurerPriceTableName()).isEqualTo(UPDATED_PRIMARY_INSURER_PRICE_TABLE_NAME);
        assertThat(testSalesOrderMaster.getInventoryLocationName()).isEqualTo(UPDATED_INVENTORY_LOCATION_NAME);
        assertThat(testSalesOrderMaster.getSoControlNumber()).isEqualTo(UPDATED_SO_CONTROL_NUMBER);
    }

    @Test
    void putNonExistingSalesOrderMaster() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderMasterRepository.findAll().collectList().block().size();
        salesOrderMaster.setSalesOrderId(count.incrementAndGet());

        // Create the SalesOrderMaster
        SalesOrderMasterDTO salesOrderMasterDTO = salesOrderMasterMapper.toDto(salesOrderMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, salesOrderMasterDTO.getSalesOrderId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderMaster in the database
        List<SalesOrderMaster> salesOrderMasterList = salesOrderMasterRepository.findAll().collectList().block();
        assertThat(salesOrderMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchSalesOrderMaster() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderMasterRepository.findAll().collectList().block().size();
        salesOrderMaster.setSalesOrderId(count.incrementAndGet());

        // Create the SalesOrderMaster
        SalesOrderMasterDTO salesOrderMasterDTO = salesOrderMasterMapper.toDto(salesOrderMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderMaster in the database
        List<SalesOrderMaster> salesOrderMasterList = salesOrderMasterRepository.findAll().collectList().block();
        assertThat(salesOrderMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamSalesOrderMaster() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderMasterRepository.findAll().collectList().block().size();
        salesOrderMaster.setSalesOrderId(count.incrementAndGet());

        // Create the SalesOrderMaster
        SalesOrderMasterDTO salesOrderMasterDTO = salesOrderMasterMapper.toDto(salesOrderMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderMasterDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the SalesOrderMaster in the database
        List<SalesOrderMaster> salesOrderMasterList = salesOrderMasterRepository.findAll().collectList().block();
        assertThat(salesOrderMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateSalesOrderMasterWithPatch() throws Exception {
        // Initialize the database
        salesOrderMasterRepository.save(salesOrderMaster).block();

        int databaseSizeBeforeUpdate = salesOrderMasterRepository.findAll().collectList().block().size();

        // Update the salesOrderMaster using partial update
        SalesOrderMaster partialUpdatedSalesOrderMaster = new SalesOrderMaster();
        partialUpdatedSalesOrderMaster.setSalesOrderId(salesOrderMaster.getSalesOrderId());

        partialUpdatedSalesOrderMaster
            .patientId(UPDATED_PATIENT_ID)
            .patientAddressLine1(UPDATED_PATIENT_ADDRESS_LINE_1)
            .patientAddressLine2(UPDATED_PATIENT_ADDRESS_LINE_2)
            .patientContactNo1(UPDATED_PATIENT_CONTACT_NO_1)
            .patientHeight(UPDATED_PATIENT_HEIGHT)
            .patientSsn(UPDATED_PATIENT_SSN)
            .patientGender(UPDATED_PATIENT_GENDER)
            .patientBranchId(UPDATED_PATIENT_BRANCH_ID)
            .deliveryScheduleDatetime(UPDATED_DELIVERY_SCHEDULE_DATETIME)
            .deliveryActualDatetime(UPDATED_DELIVERY_ACTUAL_DATETIME)
            .deliveryAddressLine2(UPDATED_DELIVERY_ADDRESS_LINE_2)
            .facilityId(UPDATED_FACILITY_ID)
            .facilityNpi(UPDATED_FACILITY_NPI)
            .salesOrderNote(UPDATED_SALES_ORDER_NOTE)
            .deliveryNote(UPDATED_DELIVERY_NOTE)
            .podStatus(UPDATED_POD_STATUS)
            .podLastMessage(UPDATED_POD_LAST_MESSAGE)
            .podMessageDatetime(UPDATED_POD_MESSAGE_DATETIME)
            .holdReasonDescription(UPDATED_HOLD_REASON_DESCRIPTION)
            .branchId(UPDATED_BRANCH_ID)
            .orderStatus(UPDATED_ORDER_STATUS)
            .admissionDate(UPDATED_ADMISSION_DATE)
            .dischargeDate(UPDATED_DISCHARGE_DATE)
            .discountPercentage(UPDATED_DISCOUNT_PERCENTAGE)
            .userField1(UPDATED_USER_FIELD_1)
            .userField3(UPDATED_USER_FIELD_3)
            .userField4(UPDATED_USER_FIELD_4)
            .reference(UPDATED_REFERENCE)
            .wipStatus(UPDATED_WIP_STATUS)
            .wipDaysInState(UPDATED_WIP_DAYS_IN_STATE)
            .wipAssignedToId(UPDATED_WIP_ASSIGNED_TO_ID)
            .cityName(UPDATED_CITY_NAME)
            .zipCode(UPDATED_ZIP_CODE)
            .posName(UPDATED_POS_NAME)
            .confirmationByName(UPDATED_CONFIRMATION_BY_NAME)
            .confirmationDate(UPDATED_CONFIRMATION_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .branchEin(UPDATED_BRANCH_EIN)
            .branchContactNo2(UPDATED_BRANCH_CONTACT_NO_2)
            .providerType(UPDATED_PROVIDER_TYPE)
            .branchAddressLine2(UPDATED_BRANCH_ADDRESS_LINE_2)
            .branchCity(UPDATED_BRANCH_CITY)
            .branchState(UPDATED_BRANCH_STATE)
            .branchZipCode(UPDATED_BRANCH_ZIP_CODE)
            .patientDeliveryAddressLine2(UPDATED_PATIENT_DELIVERY_ADDRESS_LINE_2)
            .patientDeliveryCity(UPDATED_PATIENT_DELIVERY_CITY)
            .patientDeliveryCountry(UPDATED_PATIENT_DELIVERY_COUNTRY)
            .patientDeliveryZip(UPDATED_PATIENT_DELIVERY_ZIP)
            .patientBillingAddressLine1(UPDATED_PATIENT_BILLING_ADDRESS_LINE_1)
            .patientBillingCity(UPDATED_PATIENT_BILLING_CITY)
            .patientBillingState(UPDATED_PATIENT_BILLING_STATE)
            .patientBillingCountry(UPDATED_PATIENT_BILLING_COUNTRY)
            .patientEmail(UPDATED_PATIENT_EMAIL)
            .insuredLastName(UPDATED_INSURED_LAST_NAME)
            .insuredState(UPDATED_INSURED_STATE)
            .insuredContactNo(UPDATED_INSURED_CONTACT_NO)
            .patientConditionAutoAccident(UPDATED_PATIENT_CONDITION_AUTO_ACCIDENT)
            .billingProviderTaxonomy(UPDATED_BILLING_PROVIDER_TAXONOMY)
            .billingProviderNpi(UPDATED_BILLING_PROVIDER_NPI)
            .billingProviderOrganisationName(UPDATED_BILLING_PROVIDER_ORGANISATION_NAME)
            .billingProviderAddressLine1(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_1)
            .billingProviderAddressLine2(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_2)
            .billingProviderCity(UPDATED_BILLING_PROVIDER_CITY)
            .billingProviderCountry(UPDATED_BILLING_PROVIDER_COUNTRY)
            .billingProviderZipCode(UPDATED_BILLING_PROVIDER_ZIP_CODE)
            .insuredDob(UPDATED_INSURED_DOB)
            .primaryInsurerPriceTableId(UPDATED_PRIMARY_INSURER_PRICE_TABLE_ID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedSalesOrderMaster.getSalesOrderId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedSalesOrderMaster))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SalesOrderMaster in the database
        List<SalesOrderMaster> salesOrderMasterList = salesOrderMasterRepository.findAll().collectList().block();
        assertThat(salesOrderMasterList).hasSize(databaseSizeBeforeUpdate);
        SalesOrderMaster testSalesOrderMaster = salesOrderMasterList.get(salesOrderMasterList.size() - 1);
        assertThat(testSalesOrderMaster.getSalesOrderNo()).isEqualTo(DEFAULT_SALES_ORDER_NO);
        assertThat(testSalesOrderMaster.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testSalesOrderMaster.getPatientFirstName()).isEqualTo(DEFAULT_PATIENT_FIRST_NAME);
        assertThat(testSalesOrderMaster.getPatientMiddleName()).isEqualTo(DEFAULT_PATIENT_MIDDLE_NAME);
        assertThat(testSalesOrderMaster.getPatientLastName()).isEqualTo(DEFAULT_PATIENT_LAST_NAME);
        assertThat(testSalesOrderMaster.getPatientAddressLine1()).isEqualTo(UPDATED_PATIENT_ADDRESS_LINE_1);
        assertThat(testSalesOrderMaster.getPatientAddressLine2()).isEqualTo(UPDATED_PATIENT_ADDRESS_LINE_2);
        assertThat(testSalesOrderMaster.getPatientContactNo1()).isEqualTo(UPDATED_PATIENT_CONTACT_NO_1);
        assertThat(testSalesOrderMaster.getPatientContactNo2()).isEqualTo(DEFAULT_PATIENT_CONTACT_NO_2);
        assertThat(testSalesOrderMaster.getPatientDob()).isEqualTo(DEFAULT_PATIENT_DOB);
        assertThat(testSalesOrderMaster.getPatientHeight()).isEqualTo(UPDATED_PATIENT_HEIGHT);
        assertThat(testSalesOrderMaster.getPatientWeight()).isEqualTo(DEFAULT_PATIENT_WEIGHT);
        assertThat(testSalesOrderMaster.getPatientSsn()).isEqualTo(UPDATED_PATIENT_SSN);
        assertThat(testSalesOrderMaster.getPatientGender()).isEqualTo(UPDATED_PATIENT_GENDER);
        assertThat(testSalesOrderMaster.getHipaaOnFileStatus()).isEqualTo(DEFAULT_HIPAA_ON_FILE_STATUS);
        assertThat(testSalesOrderMaster.getPatientBranchId()).isEqualTo(UPDATED_PATIENT_BRANCH_ID);
        assertThat(testSalesOrderMaster.getBranchName()).isEqualTo(DEFAULT_BRANCH_NAME);
        assertThat(testSalesOrderMaster.getDeliveryScheduleDatetime()).isEqualTo(UPDATED_DELIVERY_SCHEDULE_DATETIME);
        assertThat(testSalesOrderMaster.getDeliveryActualDatetime()).isEqualTo(UPDATED_DELIVERY_ACTUAL_DATETIME);
        assertThat(testSalesOrderMaster.getDeliveryAddressLine1()).isEqualTo(DEFAULT_DELIVERY_ADDRESS_LINE_1);
        assertThat(testSalesOrderMaster.getDeliveryAddressLine2()).isEqualTo(UPDATED_DELIVERY_ADDRESS_LINE_2);
        assertThat(testSalesOrderMaster.getDeliveryPhoneNo1()).isEqualTo(DEFAULT_DELIVERY_PHONE_NO_1);
        assertThat(testSalesOrderMaster.getDeliveryPhoneNo2()).isEqualTo(DEFAULT_DELIVERY_PHONE_NO_2);
        assertThat(testSalesOrderMaster.getFacilityId()).isEqualTo(UPDATED_FACILITY_ID);
        assertThat(testSalesOrderMaster.getFacilityName()).isEqualTo(DEFAULT_FACILITY_NAME);
        assertThat(testSalesOrderMaster.getFacilityNpi()).isEqualTo(UPDATED_FACILITY_NPI);
        assertThat(testSalesOrderMaster.getTaxZoneId()).isEqualTo(DEFAULT_TAX_ZONE_ID);
        assertThat(testSalesOrderMaster.getTaxRate()).isEqualTo(DEFAULT_TAX_RATE);
        assertThat(testSalesOrderMaster.getSalesOrderNote()).isEqualTo(UPDATED_SALES_ORDER_NOTE);
        assertThat(testSalesOrderMaster.getDeliveryNote()).isEqualTo(UPDATED_DELIVERY_NOTE);
        assertThat(testSalesOrderMaster.getDeliveryTechnician()).isEqualTo(DEFAULT_DELIVERY_TECHNICIAN);
        assertThat(testSalesOrderMaster.getSignatureRequiredStatus()).isEqualTo(DEFAULT_SIGNATURE_REQUIRED_STATUS);
        assertThat(testSalesOrderMaster.getPodStatus()).isEqualTo(UPDATED_POD_STATUS);
        assertThat(testSalesOrderMaster.getPodStatusDatetime()).isEqualTo(DEFAULT_POD_STATUS_DATETIME);
        assertThat(testSalesOrderMaster.getPodLastMessage()).isEqualTo(UPDATED_POD_LAST_MESSAGE);
        assertThat(testSalesOrderMaster.getPodMessageDatetime()).isEqualTo(UPDATED_POD_MESSAGE_DATETIME);
        assertThat(testSalesOrderMaster.getMutualHoldStatus()).isEqualTo(DEFAULT_MUTUAL_HOLD_STATUS);
        assertThat(testSalesOrderMaster.getHoldStatus()).isEqualTo(DEFAULT_HOLD_STATUS);
        assertThat(testSalesOrderMaster.getHoldReasonDescription()).isEqualTo(UPDATED_HOLD_REASON_DESCRIPTION);
        assertThat(testSalesOrderMaster.getStopDate()).isEqualTo(DEFAULT_STOP_DATE);
        assertThat(testSalesOrderMaster.getStopReasonDescription()).isEqualTo(DEFAULT_STOP_REASON_DESCRIPTION);
        assertThat(testSalesOrderMaster.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
        assertThat(testSalesOrderMaster.getBillingBranchName()).isEqualTo(DEFAULT_BILLING_BRANCH_NAME);
        assertThat(testSalesOrderMaster.getInventoryLocationId()).isEqualTo(DEFAULT_INVENTORY_LOCATION_ID);
        assertThat(testSalesOrderMaster.getOrderStatus()).isEqualTo(UPDATED_ORDER_STATUS);
        assertThat(testSalesOrderMaster.getOrderClassificationId()).isEqualTo(DEFAULT_ORDER_CLASSIFICATION_ID);
        assertThat(testSalesOrderMaster.getPosId()).isEqualTo(DEFAULT_POS_ID);
        assertThat(testSalesOrderMaster.getAdmissionDate()).isEqualTo(UPDATED_ADMISSION_DATE);
        assertThat(testSalesOrderMaster.getDischargeDate()).isEqualTo(UPDATED_DISCHARGE_DATE);
        assertThat(testSalesOrderMaster.getDiscountPercentage()).isEqualTo(UPDATED_DISCOUNT_PERCENTAGE);
        assertThat(testSalesOrderMaster.getPoNumber()).isEqualTo(DEFAULT_PO_NUMBER);
        assertThat(testSalesOrderMaster.getUserField1()).isEqualTo(UPDATED_USER_FIELD_1);
        assertThat(testSalesOrderMaster.getUserField2()).isEqualTo(DEFAULT_USER_FIELD_2);
        assertThat(testSalesOrderMaster.getUserField3()).isEqualTo(UPDATED_USER_FIELD_3);
        assertThat(testSalesOrderMaster.getUserField4()).isEqualTo(UPDATED_USER_FIELD_4);
        assertThat(testSalesOrderMaster.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testSalesOrderMaster.getWipStatus()).isEqualTo(UPDATED_WIP_STATUS);
        assertThat(testSalesOrderMaster.getWipDaysInState()).isEqualTo(UPDATED_WIP_DAYS_IN_STATE);
        assertThat(testSalesOrderMaster.getWipAssignedToId()).isEqualTo(UPDATED_WIP_ASSIGNED_TO_ID);
        assertThat(testSalesOrderMaster.getWipDateNeeded()).isEqualTo(DEFAULT_WIP_DATE_NEEDED);
        assertThat(testSalesOrderMaster.getCompletedStatus()).isEqualTo(DEFAULT_COMPLETED_STATUS);
        assertThat(testSalesOrderMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSalesOrderMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testSalesOrderMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testSalesOrderMaster.getCityName()).isEqualTo(UPDATED_CITY_NAME);
        assertThat(testSalesOrderMaster.getStateName()).isEqualTo(DEFAULT_STATE_NAME);
        assertThat(testSalesOrderMaster.getZipCode()).isEqualTo(UPDATED_ZIP_CODE);
        assertThat(testSalesOrderMaster.getDeliveryCityName()).isEqualTo(DEFAULT_DELIVERY_CITY_NAME);
        assertThat(testSalesOrderMaster.getDeliveryStateName()).isEqualTo(DEFAULT_DELIVERY_STATE_NAME);
        assertThat(testSalesOrderMaster.getDeliveryZipCode()).isEqualTo(DEFAULT_DELIVERY_ZIP_CODE);
        assertThat(testSalesOrderMaster.getPatientDod()).isEqualTo(DEFAULT_PATIENT_DOD);
        assertThat(testSalesOrderMaster.getPosName()).isEqualTo(UPDATED_POS_NAME);
        assertThat(testSalesOrderMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testSalesOrderMaster.getConfirmationById()).isEqualTo(DEFAULT_CONFIRMATION_BY_ID);
        assertThat(testSalesOrderMaster.getConfirmationByName()).isEqualTo(UPDATED_CONFIRMATION_BY_NAME);
        assertThat(testSalesOrderMaster.getConfirmationDate()).isEqualTo(UPDATED_CONFIRMATION_DATE);
        assertThat(testSalesOrderMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testSalesOrderMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testSalesOrderMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testSalesOrderMaster.getSalesOrderMasterUuid()).isEqualTo(DEFAULT_SALES_ORDER_MASTER_UUID);
        assertThat(testSalesOrderMaster.getBranchContactPersonName()).isEqualTo(DEFAULT_BRANCH_CONTACT_PERSON_NAME);
        assertThat(testSalesOrderMaster.getBranchNpi()).isEqualTo(DEFAULT_BRANCH_NPI);
        assertThat(testSalesOrderMaster.getBranchEin()).isEqualTo(UPDATED_BRANCH_EIN);
        assertThat(testSalesOrderMaster.getBranchContactNo1()).isEqualTo(DEFAULT_BRANCH_CONTACT_NO_1);
        assertThat(testSalesOrderMaster.getBranchContactNo2()).isEqualTo(UPDATED_BRANCH_CONTACT_NO_2);
        assertThat(testSalesOrderMaster.getPatientIdNo()).isEqualTo(DEFAULT_PATIENT_ID_NO);
        assertThat(testSalesOrderMaster.getPosCode()).isEqualTo(DEFAULT_POS_CODE);
        assertThat(testSalesOrderMaster.getEclaimCarrierName()).isEqualTo(DEFAULT_ECLAIM_CARRIER_NAME);
        assertThat(testSalesOrderMaster.getPlanParticipationCode()).isEqualTo(DEFAULT_PLAN_PARTICIPATION_CODE);
        assertThat(testSalesOrderMaster.getPatientMemberId()).isEqualTo(DEFAULT_PATIENT_MEMBER_ID);
        assertThat(testSalesOrderMaster.getContactPersonName()).isEqualTo(DEFAULT_CONTACT_PERSON_NAME);
        assertThat(testSalesOrderMaster.getProviderType()).isEqualTo(UPDATED_PROVIDER_TYPE);
        assertThat(testSalesOrderMaster.getBranchAddressLine1()).isEqualTo(DEFAULT_BRANCH_ADDRESS_LINE_1);
        assertThat(testSalesOrderMaster.getBranchAddressLine2()).isEqualTo(UPDATED_BRANCH_ADDRESS_LINE_2);
        assertThat(testSalesOrderMaster.getBranchCity()).isEqualTo(UPDATED_BRANCH_CITY);
        assertThat(testSalesOrderMaster.getBranchState()).isEqualTo(UPDATED_BRANCH_STATE);
        assertThat(testSalesOrderMaster.getBranchZipCode()).isEqualTo(UPDATED_BRANCH_ZIP_CODE);
        assertThat(testSalesOrderMaster.getPatientDeliveryAddressLine1()).isEqualTo(DEFAULT_PATIENT_DELIVERY_ADDRESS_LINE_1);
        assertThat(testSalesOrderMaster.getPatientDeliveryAddressLine2()).isEqualTo(UPDATED_PATIENT_DELIVERY_ADDRESS_LINE_2);
        assertThat(testSalesOrderMaster.getPatientDeliveryCity()).isEqualTo(UPDATED_PATIENT_DELIVERY_CITY);
        assertThat(testSalesOrderMaster.getPatientDeliveryState()).isEqualTo(DEFAULT_PATIENT_DELIVERY_STATE);
        assertThat(testSalesOrderMaster.getPatientDeliveryCountry()).isEqualTo(UPDATED_PATIENT_DELIVERY_COUNTRY);
        assertThat(testSalesOrderMaster.getPatientDeliveryZip()).isEqualTo(UPDATED_PATIENT_DELIVERY_ZIP);
        assertThat(testSalesOrderMaster.getPatientBillingAddressLine1()).isEqualTo(UPDATED_PATIENT_BILLING_ADDRESS_LINE_1);
        assertThat(testSalesOrderMaster.getPatientBillingAddressLine2()).isEqualTo(DEFAULT_PATIENT_BILLING_ADDRESS_LINE_2);
        assertThat(testSalesOrderMaster.getPatientBillingCity()).isEqualTo(UPDATED_PATIENT_BILLING_CITY);
        assertThat(testSalesOrderMaster.getPatientBillingState()).isEqualTo(UPDATED_PATIENT_BILLING_STATE);
        assertThat(testSalesOrderMaster.getPatientBillingCountry()).isEqualTo(UPDATED_PATIENT_BILLING_COUNTRY);
        assertThat(testSalesOrderMaster.getPatientBillingZip()).isEqualTo(DEFAULT_PATIENT_BILLING_ZIP);
        assertThat(testSalesOrderMaster.getPatientFax()).isEqualTo(DEFAULT_PATIENT_FAX);
        assertThat(testSalesOrderMaster.getBranchFax()).isEqualTo(DEFAULT_BRANCH_FAX);
        assertThat(testSalesOrderMaster.getPatientEmail()).isEqualTo(UPDATED_PATIENT_EMAIL);
        assertThat(testSalesOrderMaster.getRelationship()).isEqualTo(DEFAULT_RELATIONSHIP);
        assertThat(testSalesOrderMaster.getModeOfContact()).isEqualTo(DEFAULT_MODE_OF_CONTACT);
        assertThat(testSalesOrderMaster.getInsuredFirstName()).isEqualTo(DEFAULT_INSURED_FIRST_NAME);
        assertThat(testSalesOrderMaster.getInsuredLastName()).isEqualTo(UPDATED_INSURED_LAST_NAME);
        assertThat(testSalesOrderMaster.getInsuredAddressLine1()).isEqualTo(DEFAULT_INSURED_ADDRESS_LINE_1);
        assertThat(testSalesOrderMaster.getInsuredAddressLine2()).isEqualTo(DEFAULT_INSURED_ADDRESS_LINE_2);
        assertThat(testSalesOrderMaster.getInsuredCity()).isEqualTo(DEFAULT_INSURED_CITY);
        assertThat(testSalesOrderMaster.getInsuredState()).isEqualTo(UPDATED_INSURED_STATE);
        assertThat(testSalesOrderMaster.getInsuredZip()).isEqualTo(DEFAULT_INSURED_ZIP);
        assertThat(testSalesOrderMaster.getInsuredContactNo()).isEqualTo(UPDATED_INSURED_CONTACT_NO);
        assertThat(testSalesOrderMaster.getInsuredGender()).isEqualTo(DEFAULT_INSURED_GENDER);
        assertThat(testSalesOrderMaster.getPatientRelationshipInsured()).isEqualTo(DEFAULT_PATIENT_RELATIONSHIP_INSURED);
        assertThat(testSalesOrderMaster.getPatientConditionEmployment()).isEqualTo(DEFAULT_PATIENT_CONDITION_EMPLOYMENT);
        assertThat(testSalesOrderMaster.getPatientConditionAutoAccident()).isEqualTo(UPDATED_PATIENT_CONDITION_AUTO_ACCIDENT);
        assertThat(testSalesOrderMaster.getPatientConditionOtherAccident()).isEqualTo(DEFAULT_PATIENT_CONDITION_OTHER_ACCIDENT);
        assertThat(testSalesOrderMaster.getBillingProviderTaxonomy()).isEqualTo(UPDATED_BILLING_PROVIDER_TAXONOMY);
        assertThat(testSalesOrderMaster.getBillingProviderNpi()).isEqualTo(UPDATED_BILLING_PROVIDER_NPI);
        assertThat(testSalesOrderMaster.getBillingProviderOrganisationName()).isEqualTo(UPDATED_BILLING_PROVIDER_ORGANISATION_NAME);
        assertThat(testSalesOrderMaster.getBillingProviderAddressLine1()).isEqualTo(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_1);
        assertThat(testSalesOrderMaster.getBillingProviderAddressLine2()).isEqualTo(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_2);
        assertThat(testSalesOrderMaster.getBillingProviderCity()).isEqualTo(UPDATED_BILLING_PROVIDER_CITY);
        assertThat(testSalesOrderMaster.getBillingProviderState()).isEqualTo(DEFAULT_BILLING_PROVIDER_STATE);
        assertThat(testSalesOrderMaster.getBillingProviderCountry()).isEqualTo(UPDATED_BILLING_PROVIDER_COUNTRY);
        assertThat(testSalesOrderMaster.getBillingProviderZipCode()).isEqualTo(UPDATED_BILLING_PROVIDER_ZIP_CODE);
        assertThat(testSalesOrderMaster.getInsuredDob()).isEqualTo(UPDATED_INSURED_DOB);
        assertThat(testSalesOrderMaster.getBranchCountry()).isEqualTo(DEFAULT_BRANCH_COUNTRY);
        assertThat(testSalesOrderMaster.getBranchTaxonomy()).isEqualTo(DEFAULT_BRANCH_TAXONOMY);
        assertThat(testSalesOrderMaster.getPrimaryInsurerPriceTableId()).isEqualTo(UPDATED_PRIMARY_INSURER_PRICE_TABLE_ID);
        assertThat(testSalesOrderMaster.getPrimaryInsurerPriceTableName()).isEqualTo(DEFAULT_PRIMARY_INSURER_PRICE_TABLE_NAME);
        assertThat(testSalesOrderMaster.getInventoryLocationName()).isEqualTo(DEFAULT_INVENTORY_LOCATION_NAME);
        assertThat(testSalesOrderMaster.getSoControlNumber()).isEqualTo(DEFAULT_SO_CONTROL_NUMBER);
    }

    @Test
    void fullUpdateSalesOrderMasterWithPatch() throws Exception {
        // Initialize the database
        salesOrderMasterRepository.save(salesOrderMaster).block();

        int databaseSizeBeforeUpdate = salesOrderMasterRepository.findAll().collectList().block().size();

        // Update the salesOrderMaster using partial update
        SalesOrderMaster partialUpdatedSalesOrderMaster = new SalesOrderMaster();
        partialUpdatedSalesOrderMaster.setSalesOrderId(salesOrderMaster.getSalesOrderId());

        partialUpdatedSalesOrderMaster
            .salesOrderNo(UPDATED_SALES_ORDER_NO)
            .patientId(UPDATED_PATIENT_ID)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientMiddleName(UPDATED_PATIENT_MIDDLE_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .patientAddressLine1(UPDATED_PATIENT_ADDRESS_LINE_1)
            .patientAddressLine2(UPDATED_PATIENT_ADDRESS_LINE_2)
            .patientContactNo1(UPDATED_PATIENT_CONTACT_NO_1)
            .patientContactNo2(UPDATED_PATIENT_CONTACT_NO_2)
            .patientDob(UPDATED_PATIENT_DOB)
            .patientHeight(UPDATED_PATIENT_HEIGHT)
            .patientWeight(UPDATED_PATIENT_WEIGHT)
            .patientSsn(UPDATED_PATIENT_SSN)
            .patientGender(UPDATED_PATIENT_GENDER)
            .hipaaOnFileStatus(UPDATED_HIPAA_ON_FILE_STATUS)
            .patientBranchId(UPDATED_PATIENT_BRANCH_ID)
            .branchName(UPDATED_BRANCH_NAME)
            .deliveryScheduleDatetime(UPDATED_DELIVERY_SCHEDULE_DATETIME)
            .deliveryActualDatetime(UPDATED_DELIVERY_ACTUAL_DATETIME)
            .deliveryAddressLine1(UPDATED_DELIVERY_ADDRESS_LINE_1)
            .deliveryAddressLine2(UPDATED_DELIVERY_ADDRESS_LINE_2)
            .deliveryPhoneNo1(UPDATED_DELIVERY_PHONE_NO_1)
            .deliveryPhoneNo2(UPDATED_DELIVERY_PHONE_NO_2)
            .facilityId(UPDATED_FACILITY_ID)
            .facilityName(UPDATED_FACILITY_NAME)
            .facilityNpi(UPDATED_FACILITY_NPI)
            .taxZoneId(UPDATED_TAX_ZONE_ID)
            .taxRate(UPDATED_TAX_RATE)
            .salesOrderNote(UPDATED_SALES_ORDER_NOTE)
            .deliveryNote(UPDATED_DELIVERY_NOTE)
            .deliveryTechnician(UPDATED_DELIVERY_TECHNICIAN)
            .signatureRequiredStatus(UPDATED_SIGNATURE_REQUIRED_STATUS)
            .podStatus(UPDATED_POD_STATUS)
            .podStatusDatetime(UPDATED_POD_STATUS_DATETIME)
            .podLastMessage(UPDATED_POD_LAST_MESSAGE)
            .podMessageDatetime(UPDATED_POD_MESSAGE_DATETIME)
            .mutualHoldStatus(UPDATED_MUTUAL_HOLD_STATUS)
            .holdStatus(UPDATED_HOLD_STATUS)
            .holdReasonDescription(UPDATED_HOLD_REASON_DESCRIPTION)
            .stopDate(UPDATED_STOP_DATE)
            .stopReasonDescription(UPDATED_STOP_REASON_DESCRIPTION)
            .branchId(UPDATED_BRANCH_ID)
            .billingBranchName(UPDATED_BILLING_BRANCH_NAME)
            .inventoryLocationId(UPDATED_INVENTORY_LOCATION_ID)
            .orderStatus(UPDATED_ORDER_STATUS)
            .orderClassificationId(UPDATED_ORDER_CLASSIFICATION_ID)
            .posId(UPDATED_POS_ID)
            .admissionDate(UPDATED_ADMISSION_DATE)
            .dischargeDate(UPDATED_DISCHARGE_DATE)
            .discountPercentage(UPDATED_DISCOUNT_PERCENTAGE)
            .poNumber(UPDATED_PO_NUMBER)
            .userField1(UPDATED_USER_FIELD_1)
            .userField2(UPDATED_USER_FIELD_2)
            .userField3(UPDATED_USER_FIELD_3)
            .userField4(UPDATED_USER_FIELD_4)
            .reference(UPDATED_REFERENCE)
            .wipStatus(UPDATED_WIP_STATUS)
            .wipDaysInState(UPDATED_WIP_DAYS_IN_STATE)
            .wipAssignedToId(UPDATED_WIP_ASSIGNED_TO_ID)
            .wipDateNeeded(UPDATED_WIP_DATE_NEEDED)
            .completedStatus(UPDATED_COMPLETED_STATUS)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .cityName(UPDATED_CITY_NAME)
            .stateName(UPDATED_STATE_NAME)
            .zipCode(UPDATED_ZIP_CODE)
            .deliveryCityName(UPDATED_DELIVERY_CITY_NAME)
            .deliveryStateName(UPDATED_DELIVERY_STATE_NAME)
            .deliveryZipCode(UPDATED_DELIVERY_ZIP_CODE)
            .patientDod(UPDATED_PATIENT_DOD)
            .posName(UPDATED_POS_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .confirmationById(UPDATED_CONFIRMATION_BY_ID)
            .confirmationByName(UPDATED_CONFIRMATION_BY_NAME)
            .confirmationDate(UPDATED_CONFIRMATION_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .salesOrderMasterUuid(UPDATED_SALES_ORDER_MASTER_UUID)
            .branchContactPersonName(UPDATED_BRANCH_CONTACT_PERSON_NAME)
            .branchNpi(UPDATED_BRANCH_NPI)
            .branchEin(UPDATED_BRANCH_EIN)
            .branchContactNo1(UPDATED_BRANCH_CONTACT_NO_1)
            .branchContactNo2(UPDATED_BRANCH_CONTACT_NO_2)
            .patientIdNo(UPDATED_PATIENT_ID_NO)
            .posCode(UPDATED_POS_CODE)
            .eclaimCarrierName(UPDATED_ECLAIM_CARRIER_NAME)
            .planParticipationCode(UPDATED_PLAN_PARTICIPATION_CODE)
            .patientMemberId(UPDATED_PATIENT_MEMBER_ID)
            .contactPersonName(UPDATED_CONTACT_PERSON_NAME)
            .providerType(UPDATED_PROVIDER_TYPE)
            .branchAddressLine1(UPDATED_BRANCH_ADDRESS_LINE_1)
            .branchAddressLine2(UPDATED_BRANCH_ADDRESS_LINE_2)
            .branchCity(UPDATED_BRANCH_CITY)
            .branchState(UPDATED_BRANCH_STATE)
            .branchZipCode(UPDATED_BRANCH_ZIP_CODE)
            .patientDeliveryAddressLine1(UPDATED_PATIENT_DELIVERY_ADDRESS_LINE_1)
            .patientDeliveryAddressLine2(UPDATED_PATIENT_DELIVERY_ADDRESS_LINE_2)
            .patientDeliveryCity(UPDATED_PATIENT_DELIVERY_CITY)
            .patientDeliveryState(UPDATED_PATIENT_DELIVERY_STATE)
            .patientDeliveryCountry(UPDATED_PATIENT_DELIVERY_COUNTRY)
            .patientDeliveryZip(UPDATED_PATIENT_DELIVERY_ZIP)
            .patientBillingAddressLine1(UPDATED_PATIENT_BILLING_ADDRESS_LINE_1)
            .patientBillingAddressLine2(UPDATED_PATIENT_BILLING_ADDRESS_LINE_2)
            .patientBillingCity(UPDATED_PATIENT_BILLING_CITY)
            .patientBillingState(UPDATED_PATIENT_BILLING_STATE)
            .patientBillingCountry(UPDATED_PATIENT_BILLING_COUNTRY)
            .patientBillingZip(UPDATED_PATIENT_BILLING_ZIP)
            .patientFax(UPDATED_PATIENT_FAX)
            .branchFax(UPDATED_BRANCH_FAX)
            .patientEmail(UPDATED_PATIENT_EMAIL)
            .relationship(UPDATED_RELATIONSHIP)
            .modeOfContact(UPDATED_MODE_OF_CONTACT)
            .insuredFirstName(UPDATED_INSURED_FIRST_NAME)
            .insuredLastName(UPDATED_INSURED_LAST_NAME)
            .insuredAddressLine1(UPDATED_INSURED_ADDRESS_LINE_1)
            .insuredAddressLine2(UPDATED_INSURED_ADDRESS_LINE_2)
            .insuredCity(UPDATED_INSURED_CITY)
            .insuredState(UPDATED_INSURED_STATE)
            .insuredZip(UPDATED_INSURED_ZIP)
            .insuredContactNo(UPDATED_INSURED_CONTACT_NO)
            .insuredGender(UPDATED_INSURED_GENDER)
            .patientRelationshipInsured(UPDATED_PATIENT_RELATIONSHIP_INSURED)
            .patientConditionEmployment(UPDATED_PATIENT_CONDITION_EMPLOYMENT)
            .patientConditionAutoAccident(UPDATED_PATIENT_CONDITION_AUTO_ACCIDENT)
            .patientConditionOtherAccident(UPDATED_PATIENT_CONDITION_OTHER_ACCIDENT)
            .billingProviderTaxonomy(UPDATED_BILLING_PROVIDER_TAXONOMY)
            .billingProviderNpi(UPDATED_BILLING_PROVIDER_NPI)
            .billingProviderOrganisationName(UPDATED_BILLING_PROVIDER_ORGANISATION_NAME)
            .billingProviderAddressLine1(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_1)
            .billingProviderAddressLine2(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_2)
            .billingProviderCity(UPDATED_BILLING_PROVIDER_CITY)
            .billingProviderState(UPDATED_BILLING_PROVIDER_STATE)
            .billingProviderCountry(UPDATED_BILLING_PROVIDER_COUNTRY)
            .billingProviderZipCode(UPDATED_BILLING_PROVIDER_ZIP_CODE)
            .insuredDob(UPDATED_INSURED_DOB)
            .branchCountry(UPDATED_BRANCH_COUNTRY)
            .branchTaxonomy(UPDATED_BRANCH_TAXONOMY)
            .primaryInsurerPriceTableId(UPDATED_PRIMARY_INSURER_PRICE_TABLE_ID)
            .primaryInsurerPriceTableName(UPDATED_PRIMARY_INSURER_PRICE_TABLE_NAME)
            .inventoryLocationName(UPDATED_INVENTORY_LOCATION_NAME)
            .soControlNumber(UPDATED_SO_CONTROL_NUMBER);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedSalesOrderMaster.getSalesOrderId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedSalesOrderMaster))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SalesOrderMaster in the database
        List<SalesOrderMaster> salesOrderMasterList = salesOrderMasterRepository.findAll().collectList().block();
        assertThat(salesOrderMasterList).hasSize(databaseSizeBeforeUpdate);
        SalesOrderMaster testSalesOrderMaster = salesOrderMasterList.get(salesOrderMasterList.size() - 1);
        assertThat(testSalesOrderMaster.getSalesOrderNo()).isEqualTo(UPDATED_SALES_ORDER_NO);
        assertThat(testSalesOrderMaster.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testSalesOrderMaster.getPatientFirstName()).isEqualTo(UPDATED_PATIENT_FIRST_NAME);
        assertThat(testSalesOrderMaster.getPatientMiddleName()).isEqualTo(UPDATED_PATIENT_MIDDLE_NAME);
        assertThat(testSalesOrderMaster.getPatientLastName()).isEqualTo(UPDATED_PATIENT_LAST_NAME);
        assertThat(testSalesOrderMaster.getPatientAddressLine1()).isEqualTo(UPDATED_PATIENT_ADDRESS_LINE_1);
        assertThat(testSalesOrderMaster.getPatientAddressLine2()).isEqualTo(UPDATED_PATIENT_ADDRESS_LINE_2);
        assertThat(testSalesOrderMaster.getPatientContactNo1()).isEqualTo(UPDATED_PATIENT_CONTACT_NO_1);
        assertThat(testSalesOrderMaster.getPatientContactNo2()).isEqualTo(UPDATED_PATIENT_CONTACT_NO_2);
        assertThat(testSalesOrderMaster.getPatientDob()).isEqualTo(UPDATED_PATIENT_DOB);
        assertThat(testSalesOrderMaster.getPatientHeight()).isEqualTo(UPDATED_PATIENT_HEIGHT);
        assertThat(testSalesOrderMaster.getPatientWeight()).isEqualTo(UPDATED_PATIENT_WEIGHT);
        assertThat(testSalesOrderMaster.getPatientSsn()).isEqualTo(UPDATED_PATIENT_SSN);
        assertThat(testSalesOrderMaster.getPatientGender()).isEqualTo(UPDATED_PATIENT_GENDER);
        assertThat(testSalesOrderMaster.getHipaaOnFileStatus()).isEqualTo(UPDATED_HIPAA_ON_FILE_STATUS);
        assertThat(testSalesOrderMaster.getPatientBranchId()).isEqualTo(UPDATED_PATIENT_BRANCH_ID);
        assertThat(testSalesOrderMaster.getBranchName()).isEqualTo(UPDATED_BRANCH_NAME);
        assertThat(testSalesOrderMaster.getDeliveryScheduleDatetime()).isEqualTo(UPDATED_DELIVERY_SCHEDULE_DATETIME);
        assertThat(testSalesOrderMaster.getDeliveryActualDatetime()).isEqualTo(UPDATED_DELIVERY_ACTUAL_DATETIME);
        assertThat(testSalesOrderMaster.getDeliveryAddressLine1()).isEqualTo(UPDATED_DELIVERY_ADDRESS_LINE_1);
        assertThat(testSalesOrderMaster.getDeliveryAddressLine2()).isEqualTo(UPDATED_DELIVERY_ADDRESS_LINE_2);
        assertThat(testSalesOrderMaster.getDeliveryPhoneNo1()).isEqualTo(UPDATED_DELIVERY_PHONE_NO_1);
        assertThat(testSalesOrderMaster.getDeliveryPhoneNo2()).isEqualTo(UPDATED_DELIVERY_PHONE_NO_2);
        assertThat(testSalesOrderMaster.getFacilityId()).isEqualTo(UPDATED_FACILITY_ID);
        assertThat(testSalesOrderMaster.getFacilityName()).isEqualTo(UPDATED_FACILITY_NAME);
        assertThat(testSalesOrderMaster.getFacilityNpi()).isEqualTo(UPDATED_FACILITY_NPI);
        assertThat(testSalesOrderMaster.getTaxZoneId()).isEqualTo(UPDATED_TAX_ZONE_ID);
        assertThat(testSalesOrderMaster.getTaxRate()).isEqualTo(UPDATED_TAX_RATE);
        assertThat(testSalesOrderMaster.getSalesOrderNote()).isEqualTo(UPDATED_SALES_ORDER_NOTE);
        assertThat(testSalesOrderMaster.getDeliveryNote()).isEqualTo(UPDATED_DELIVERY_NOTE);
        assertThat(testSalesOrderMaster.getDeliveryTechnician()).isEqualTo(UPDATED_DELIVERY_TECHNICIAN);
        assertThat(testSalesOrderMaster.getSignatureRequiredStatus()).isEqualTo(UPDATED_SIGNATURE_REQUIRED_STATUS);
        assertThat(testSalesOrderMaster.getPodStatus()).isEqualTo(UPDATED_POD_STATUS);
        assertThat(testSalesOrderMaster.getPodStatusDatetime()).isEqualTo(UPDATED_POD_STATUS_DATETIME);
        assertThat(testSalesOrderMaster.getPodLastMessage()).isEqualTo(UPDATED_POD_LAST_MESSAGE);
        assertThat(testSalesOrderMaster.getPodMessageDatetime()).isEqualTo(UPDATED_POD_MESSAGE_DATETIME);
        assertThat(testSalesOrderMaster.getMutualHoldStatus()).isEqualTo(UPDATED_MUTUAL_HOLD_STATUS);
        assertThat(testSalesOrderMaster.getHoldStatus()).isEqualTo(UPDATED_HOLD_STATUS);
        assertThat(testSalesOrderMaster.getHoldReasonDescription()).isEqualTo(UPDATED_HOLD_REASON_DESCRIPTION);
        assertThat(testSalesOrderMaster.getStopDate()).isEqualTo(UPDATED_STOP_DATE);
        assertThat(testSalesOrderMaster.getStopReasonDescription()).isEqualTo(UPDATED_STOP_REASON_DESCRIPTION);
        assertThat(testSalesOrderMaster.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
        assertThat(testSalesOrderMaster.getBillingBranchName()).isEqualTo(UPDATED_BILLING_BRANCH_NAME);
        assertThat(testSalesOrderMaster.getInventoryLocationId()).isEqualTo(UPDATED_INVENTORY_LOCATION_ID);
        assertThat(testSalesOrderMaster.getOrderStatus()).isEqualTo(UPDATED_ORDER_STATUS);
        assertThat(testSalesOrderMaster.getOrderClassificationId()).isEqualTo(UPDATED_ORDER_CLASSIFICATION_ID);
        assertThat(testSalesOrderMaster.getPosId()).isEqualTo(UPDATED_POS_ID);
        assertThat(testSalesOrderMaster.getAdmissionDate()).isEqualTo(UPDATED_ADMISSION_DATE);
        assertThat(testSalesOrderMaster.getDischargeDate()).isEqualTo(UPDATED_DISCHARGE_DATE);
        assertThat(testSalesOrderMaster.getDiscountPercentage()).isEqualTo(UPDATED_DISCOUNT_PERCENTAGE);
        assertThat(testSalesOrderMaster.getPoNumber()).isEqualTo(UPDATED_PO_NUMBER);
        assertThat(testSalesOrderMaster.getUserField1()).isEqualTo(UPDATED_USER_FIELD_1);
        assertThat(testSalesOrderMaster.getUserField2()).isEqualTo(UPDATED_USER_FIELD_2);
        assertThat(testSalesOrderMaster.getUserField3()).isEqualTo(UPDATED_USER_FIELD_3);
        assertThat(testSalesOrderMaster.getUserField4()).isEqualTo(UPDATED_USER_FIELD_4);
        assertThat(testSalesOrderMaster.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testSalesOrderMaster.getWipStatus()).isEqualTo(UPDATED_WIP_STATUS);
        assertThat(testSalesOrderMaster.getWipDaysInState()).isEqualTo(UPDATED_WIP_DAYS_IN_STATE);
        assertThat(testSalesOrderMaster.getWipAssignedToId()).isEqualTo(UPDATED_WIP_ASSIGNED_TO_ID);
        assertThat(testSalesOrderMaster.getWipDateNeeded()).isEqualTo(UPDATED_WIP_DATE_NEEDED);
        assertThat(testSalesOrderMaster.getCompletedStatus()).isEqualTo(UPDATED_COMPLETED_STATUS);
        assertThat(testSalesOrderMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSalesOrderMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testSalesOrderMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSalesOrderMaster.getCityName()).isEqualTo(UPDATED_CITY_NAME);
        assertThat(testSalesOrderMaster.getStateName()).isEqualTo(UPDATED_STATE_NAME);
        assertThat(testSalesOrderMaster.getZipCode()).isEqualTo(UPDATED_ZIP_CODE);
        assertThat(testSalesOrderMaster.getDeliveryCityName()).isEqualTo(UPDATED_DELIVERY_CITY_NAME);
        assertThat(testSalesOrderMaster.getDeliveryStateName()).isEqualTo(UPDATED_DELIVERY_STATE_NAME);
        assertThat(testSalesOrderMaster.getDeliveryZipCode()).isEqualTo(UPDATED_DELIVERY_ZIP_CODE);
        assertThat(testSalesOrderMaster.getPatientDod()).isEqualTo(UPDATED_PATIENT_DOD);
        assertThat(testSalesOrderMaster.getPosName()).isEqualTo(UPDATED_POS_NAME);
        assertThat(testSalesOrderMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testSalesOrderMaster.getConfirmationById()).isEqualTo(UPDATED_CONFIRMATION_BY_ID);
        assertThat(testSalesOrderMaster.getConfirmationByName()).isEqualTo(UPDATED_CONFIRMATION_BY_NAME);
        assertThat(testSalesOrderMaster.getConfirmationDate()).isEqualTo(UPDATED_CONFIRMATION_DATE);
        assertThat(testSalesOrderMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testSalesOrderMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testSalesOrderMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testSalesOrderMaster.getSalesOrderMasterUuid()).isEqualTo(UPDATED_SALES_ORDER_MASTER_UUID);
        assertThat(testSalesOrderMaster.getBranchContactPersonName()).isEqualTo(UPDATED_BRANCH_CONTACT_PERSON_NAME);
        assertThat(testSalesOrderMaster.getBranchNpi()).isEqualTo(UPDATED_BRANCH_NPI);
        assertThat(testSalesOrderMaster.getBranchEin()).isEqualTo(UPDATED_BRANCH_EIN);
        assertThat(testSalesOrderMaster.getBranchContactNo1()).isEqualTo(UPDATED_BRANCH_CONTACT_NO_1);
        assertThat(testSalesOrderMaster.getBranchContactNo2()).isEqualTo(UPDATED_BRANCH_CONTACT_NO_2);
        assertThat(testSalesOrderMaster.getPatientIdNo()).isEqualTo(UPDATED_PATIENT_ID_NO);
        assertThat(testSalesOrderMaster.getPosCode()).isEqualTo(UPDATED_POS_CODE);
        assertThat(testSalesOrderMaster.getEclaimCarrierName()).isEqualTo(UPDATED_ECLAIM_CARRIER_NAME);
        assertThat(testSalesOrderMaster.getPlanParticipationCode()).isEqualTo(UPDATED_PLAN_PARTICIPATION_CODE);
        assertThat(testSalesOrderMaster.getPatientMemberId()).isEqualTo(UPDATED_PATIENT_MEMBER_ID);
        assertThat(testSalesOrderMaster.getContactPersonName()).isEqualTo(UPDATED_CONTACT_PERSON_NAME);
        assertThat(testSalesOrderMaster.getProviderType()).isEqualTo(UPDATED_PROVIDER_TYPE);
        assertThat(testSalesOrderMaster.getBranchAddressLine1()).isEqualTo(UPDATED_BRANCH_ADDRESS_LINE_1);
        assertThat(testSalesOrderMaster.getBranchAddressLine2()).isEqualTo(UPDATED_BRANCH_ADDRESS_LINE_2);
        assertThat(testSalesOrderMaster.getBranchCity()).isEqualTo(UPDATED_BRANCH_CITY);
        assertThat(testSalesOrderMaster.getBranchState()).isEqualTo(UPDATED_BRANCH_STATE);
        assertThat(testSalesOrderMaster.getBranchZipCode()).isEqualTo(UPDATED_BRANCH_ZIP_CODE);
        assertThat(testSalesOrderMaster.getPatientDeliveryAddressLine1()).isEqualTo(UPDATED_PATIENT_DELIVERY_ADDRESS_LINE_1);
        assertThat(testSalesOrderMaster.getPatientDeliveryAddressLine2()).isEqualTo(UPDATED_PATIENT_DELIVERY_ADDRESS_LINE_2);
        assertThat(testSalesOrderMaster.getPatientDeliveryCity()).isEqualTo(UPDATED_PATIENT_DELIVERY_CITY);
        assertThat(testSalesOrderMaster.getPatientDeliveryState()).isEqualTo(UPDATED_PATIENT_DELIVERY_STATE);
        assertThat(testSalesOrderMaster.getPatientDeliveryCountry()).isEqualTo(UPDATED_PATIENT_DELIVERY_COUNTRY);
        assertThat(testSalesOrderMaster.getPatientDeliveryZip()).isEqualTo(UPDATED_PATIENT_DELIVERY_ZIP);
        assertThat(testSalesOrderMaster.getPatientBillingAddressLine1()).isEqualTo(UPDATED_PATIENT_BILLING_ADDRESS_LINE_1);
        assertThat(testSalesOrderMaster.getPatientBillingAddressLine2()).isEqualTo(UPDATED_PATIENT_BILLING_ADDRESS_LINE_2);
        assertThat(testSalesOrderMaster.getPatientBillingCity()).isEqualTo(UPDATED_PATIENT_BILLING_CITY);
        assertThat(testSalesOrderMaster.getPatientBillingState()).isEqualTo(UPDATED_PATIENT_BILLING_STATE);
        assertThat(testSalesOrderMaster.getPatientBillingCountry()).isEqualTo(UPDATED_PATIENT_BILLING_COUNTRY);
        assertThat(testSalesOrderMaster.getPatientBillingZip()).isEqualTo(UPDATED_PATIENT_BILLING_ZIP);
        assertThat(testSalesOrderMaster.getPatientFax()).isEqualTo(UPDATED_PATIENT_FAX);
        assertThat(testSalesOrderMaster.getBranchFax()).isEqualTo(UPDATED_BRANCH_FAX);
        assertThat(testSalesOrderMaster.getPatientEmail()).isEqualTo(UPDATED_PATIENT_EMAIL);
        assertThat(testSalesOrderMaster.getRelationship()).isEqualTo(UPDATED_RELATIONSHIP);
        assertThat(testSalesOrderMaster.getModeOfContact()).isEqualTo(UPDATED_MODE_OF_CONTACT);
        assertThat(testSalesOrderMaster.getInsuredFirstName()).isEqualTo(UPDATED_INSURED_FIRST_NAME);
        assertThat(testSalesOrderMaster.getInsuredLastName()).isEqualTo(UPDATED_INSURED_LAST_NAME);
        assertThat(testSalesOrderMaster.getInsuredAddressLine1()).isEqualTo(UPDATED_INSURED_ADDRESS_LINE_1);
        assertThat(testSalesOrderMaster.getInsuredAddressLine2()).isEqualTo(UPDATED_INSURED_ADDRESS_LINE_2);
        assertThat(testSalesOrderMaster.getInsuredCity()).isEqualTo(UPDATED_INSURED_CITY);
        assertThat(testSalesOrderMaster.getInsuredState()).isEqualTo(UPDATED_INSURED_STATE);
        assertThat(testSalesOrderMaster.getInsuredZip()).isEqualTo(UPDATED_INSURED_ZIP);
        assertThat(testSalesOrderMaster.getInsuredContactNo()).isEqualTo(UPDATED_INSURED_CONTACT_NO);
        assertThat(testSalesOrderMaster.getInsuredGender()).isEqualTo(UPDATED_INSURED_GENDER);
        assertThat(testSalesOrderMaster.getPatientRelationshipInsured()).isEqualTo(UPDATED_PATIENT_RELATIONSHIP_INSURED);
        assertThat(testSalesOrderMaster.getPatientConditionEmployment()).isEqualTo(UPDATED_PATIENT_CONDITION_EMPLOYMENT);
        assertThat(testSalesOrderMaster.getPatientConditionAutoAccident()).isEqualTo(UPDATED_PATIENT_CONDITION_AUTO_ACCIDENT);
        assertThat(testSalesOrderMaster.getPatientConditionOtherAccident()).isEqualTo(UPDATED_PATIENT_CONDITION_OTHER_ACCIDENT);
        assertThat(testSalesOrderMaster.getBillingProviderTaxonomy()).isEqualTo(UPDATED_BILLING_PROVIDER_TAXONOMY);
        assertThat(testSalesOrderMaster.getBillingProviderNpi()).isEqualTo(UPDATED_BILLING_PROVIDER_NPI);
        assertThat(testSalesOrderMaster.getBillingProviderOrganisationName()).isEqualTo(UPDATED_BILLING_PROVIDER_ORGANISATION_NAME);
        assertThat(testSalesOrderMaster.getBillingProviderAddressLine1()).isEqualTo(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_1);
        assertThat(testSalesOrderMaster.getBillingProviderAddressLine2()).isEqualTo(UPDATED_BILLING_PROVIDER_ADDRESS_LINE_2);
        assertThat(testSalesOrderMaster.getBillingProviderCity()).isEqualTo(UPDATED_BILLING_PROVIDER_CITY);
        assertThat(testSalesOrderMaster.getBillingProviderState()).isEqualTo(UPDATED_BILLING_PROVIDER_STATE);
        assertThat(testSalesOrderMaster.getBillingProviderCountry()).isEqualTo(UPDATED_BILLING_PROVIDER_COUNTRY);
        assertThat(testSalesOrderMaster.getBillingProviderZipCode()).isEqualTo(UPDATED_BILLING_PROVIDER_ZIP_CODE);
        assertThat(testSalesOrderMaster.getInsuredDob()).isEqualTo(UPDATED_INSURED_DOB);
        assertThat(testSalesOrderMaster.getBranchCountry()).isEqualTo(UPDATED_BRANCH_COUNTRY);
        assertThat(testSalesOrderMaster.getBranchTaxonomy()).isEqualTo(UPDATED_BRANCH_TAXONOMY);
        assertThat(testSalesOrderMaster.getPrimaryInsurerPriceTableId()).isEqualTo(UPDATED_PRIMARY_INSURER_PRICE_TABLE_ID);
        assertThat(testSalesOrderMaster.getPrimaryInsurerPriceTableName()).isEqualTo(UPDATED_PRIMARY_INSURER_PRICE_TABLE_NAME);
        assertThat(testSalesOrderMaster.getInventoryLocationName()).isEqualTo(UPDATED_INVENTORY_LOCATION_NAME);
        assertThat(testSalesOrderMaster.getSoControlNumber()).isEqualTo(UPDATED_SO_CONTROL_NUMBER);
    }

    @Test
    void patchNonExistingSalesOrderMaster() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderMasterRepository.findAll().collectList().block().size();
        salesOrderMaster.setSalesOrderId(count.incrementAndGet());

        // Create the SalesOrderMaster
        SalesOrderMasterDTO salesOrderMasterDTO = salesOrderMasterMapper.toDto(salesOrderMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, salesOrderMasterDTO.getSalesOrderId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderMaster in the database
        List<SalesOrderMaster> salesOrderMasterList = salesOrderMasterRepository.findAll().collectList().block();
        assertThat(salesOrderMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchSalesOrderMaster() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderMasterRepository.findAll().collectList().block().size();
        salesOrderMaster.setSalesOrderId(count.incrementAndGet());

        // Create the SalesOrderMaster
        SalesOrderMasterDTO salesOrderMasterDTO = salesOrderMasterMapper.toDto(salesOrderMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderMaster in the database
        List<SalesOrderMaster> salesOrderMasterList = salesOrderMasterRepository.findAll().collectList().block();
        assertThat(salesOrderMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamSalesOrderMaster() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderMasterRepository.findAll().collectList().block().size();
        salesOrderMaster.setSalesOrderId(count.incrementAndGet());

        // Create the SalesOrderMaster
        SalesOrderMasterDTO salesOrderMasterDTO = salesOrderMasterMapper.toDto(salesOrderMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderMasterDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the SalesOrderMaster in the database
        List<SalesOrderMaster> salesOrderMasterList = salesOrderMasterRepository.findAll().collectList().block();
        assertThat(salesOrderMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteSalesOrderMaster() {
        // Initialize the database
        salesOrderMasterRepository.save(salesOrderMaster).block();

        int databaseSizeBeforeDelete = salesOrderMasterRepository.findAll().collectList().block().size();

        // Delete the salesOrderMaster
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, salesOrderMaster.getSalesOrderId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<SalesOrderMaster> salesOrderMasterList = salesOrderMasterRepository.findAll().collectList().block();
        assertThat(salesOrderMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
