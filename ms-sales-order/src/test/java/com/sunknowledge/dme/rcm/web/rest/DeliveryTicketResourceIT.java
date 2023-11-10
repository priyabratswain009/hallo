package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.DeliveryTicket;
import com.sunknowledge.dme.rcm.repository.DeliveryTicketRepository;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.service.dto.DeliveryTicketDTO;
import com.sunknowledge.dme.rcm.service.mapper.DeliveryTicketMapper;
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
 * Integration tests for the {@link DeliveryTicketResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class DeliveryTicketResourceIT {

    private static final String DEFAULT_DELIVERY_TICKET_NO = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_TICKET_NO = "BBBBBBBBBB";

    private static final Long DEFAULT_SO_ID = 1L;
    private static final Long UPDATED_SO_ID = 2L;

    private static final String DEFAULT_SO_NO = "AAAAAAAAAA";
    private static final String UPDATED_SO_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_GENDER = "BBBBBBBBBB";

    private static final Integer DEFAULT_AGE_AS_ON_DATE = 1;
    private static final Integer UPDATED_AGE_AS_ON_DATE = 2;

    private static final String DEFAULT_PHONE_1 = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_2 = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_ADDRESS_1 = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_ADDRESS_1 = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_ADDRESS_2 = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_ADDRESS_2 = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_CITY = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_STATE = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_ZIP = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_STATUS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DELIVERY_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DELIVERY_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DELIVERY_PRIORITY = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_PRIORITY = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_NOTE = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_COMMENT = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_ACCEPTED_BY = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_ACCEPTED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_RELATIONSHIP_WITH_PATIENT = "AAAAAAAAAA";
    private static final String UPDATED_RELATIONSHIP_WITH_PATIENT = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_ACCEPTED_BY_CONTACT_NO = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_ACCEPTED_BY_CONTACT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_INSURER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_INSURER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_INSURER_POLICY_NO = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_INSURER_POLICY_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_INSURER_PATIENT_ID_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_INSURER_PATIENT_ID_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_ID_NO = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_ID_NO = "BBBBBBBBBB";

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

    private static final String DEFAULT_BRANCH_CONTACT_NO_1 = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_CONTACT_NO_1 = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_CONTACT_NO_2 = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_CONTACT_NO_2 = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_NPI = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_NPI = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_EIN = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_EIN = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_FAX = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERING_PROVIDER_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ORDERING_PROVIDER_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERING_PROVIDER_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ORDERING_PROVIDER_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERING_PROVIDER_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ORDERING_PROVIDER_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERING_PROVIDER_NPI = "AAAAAAAAAA";
    private static final String UPDATED_ORDERING_PROVIDER_NPI = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERING_PROVIDER_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_ORDERING_PROVIDER_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERING_PROVIDER_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_ORDERING_PROVIDER_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERING_PROVIDER_CITY = "AAAAAAAAAA";
    private static final String UPDATED_ORDERING_PROVIDER_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERING_PROVIDER_STATE = "AAAAAAAAAA";
    private static final String UPDATED_ORDERING_PROVIDER_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERING_PROVIDER_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_ORDERING_PROVIDER_ZIP = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERING_PROVIDER_PHONE_1 = "AAAAAAAAAA";
    private static final String UPDATED_ORDERING_PROVIDER_PHONE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERING_PROVIDER_PHONE_2 = "AAAAAAAAAA";
    private static final String UPDATED_ORDERING_PROVIDER_PHONE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERING_PROVIDER_FAX = "AAAAAAAAAA";
    private static final String UPDATED_ORDERING_PROVIDER_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERING_PROVIDER_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_ORDERING_PROVIDER_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_PATIENT_BRANCH_ID = 1L;
    private static final Long UPDATED_PATIENT_BRANCH_ID = 2L;

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_UPDATED_BY = 1L;
    private static final Long UPDATED_UPDATED_BY = 2L;

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final UUID DEFAULT_DELIVERY_TICKET_UUID = UUID.randomUUID();
    private static final UUID UPDATED_DELIVERY_TICKET_UUID = UUID.randomUUID();

    private static final String DEFAULT_BILLING_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_CITY = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_STATE = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_ZIP = "BBBBBBBBBB";

    private static final Long DEFAULT_INVENTORY_LOCATION_ID = 1L;
    private static final Long UPDATED_INVENTORY_LOCATION_ID = 2L;

    private static final String DEFAULT_INVENTORY_LOCATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INVENTORY_LOCATION_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_DELIVERY_TICKET_DOCUMENT_ID = 1L;
    private static final Long UPDATED_DELIVERY_TICKET_DOCUMENT_ID = 2L;

    private static final String DEFAULT_DELIVERY_TICKET_DOCUMENT_NO = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_TICKET_DOCUMENT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_TICKET_DOCUMENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_TICKET_DOCUMENT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CARRIER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CARRIER_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_SHIPPING_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SHIPPING_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_TRACKING_NO = "AAAAAAAAAA";
    private static final String UPDATED_TRACKING_NO = "BBBBBBBBBB";

    private static final String DEFAULT_REFERENCE_NO = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PACKAGE_WEIGHT = "AAAAAAAAAA";
    private static final String UPDATED_PACKAGE_WEIGHT = "BBBBBBBBBB";

    private static final String DEFAULT_SETUP_METHOD = "AAAAAAAAAA";
    private static final String UPDATED_SETUP_METHOD = "BBBBBBBBBB";

    private static final String DEFAULT_SETUP_TECHNICIAN_NO = "AAAAAAAAAA";
    private static final String UPDATED_SETUP_TECHNICIAN_NO = "BBBBBBBBBB";

    private static final String DEFAULT_SETUP_TECHNICIAN_CONTACT_NO = "AAAAAAAAAA";
    private static final String UPDATED_SETUP_TECHNICIAN_CONTACT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_SETUP_TECHNICIAN_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SETUP_TECHNICIAN_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SETUP_TECHNICIAN_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SETUP_TECHNICIAN_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SETUP_TECHNICIAN_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SETUP_TECHNICIAN_LAST_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_SETUP_DATE_TIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SETUP_DATE_TIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_SCHEDULE_SETUP_DATE_TIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SCHEDULE_SETUP_DATE_TIME = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SETUP_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_SETUP_COMMENTS = "BBBBBBBBBB";

    private static final String DEFAULT_SETUP_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_SETUP_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_COURIER_PACKAGE_ACCEPTED_BY = "AAAAAAAAAA";
    private static final String UPDATED_COURIER_PACKAGE_ACCEPTED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_THERAPIST_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_THERAPIST_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_THERAPIST_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_THERAPIST_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_THERAPIST_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_THERAPIST_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_THERAPIST_LICENSE_NO = "AAAAAAAAAA";
    private static final String UPDATED_THERAPIST_LICENSE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_THERAPIST_NPI = "AAAAAAAAAA";
    private static final String UPDATED_THERAPIST_NPI = "BBBBBBBBBB";

    private static final String DEFAULT_THERAPIST_TAXONOMY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_THERAPIST_TAXONOMY_CODE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_SCHEDULE_THERAPY_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SCHEDULE_THERAPY_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ACTUAL_THERAPY_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ACTUAL_THERAPY_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_THERAPY_MODE = "AAAAAAAAAA";
    private static final String UPDATED_THERAPY_MODE = "BBBBBBBBBB";

    private static final String DEFAULT_THERAPY_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_THERAPY_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_THERAPY_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_THERAPY_NOTES = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/delivery-tickets";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{deliveryTicketId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DeliveryTicketRepository deliveryTicketRepository;

    @Autowired
    private DeliveryTicketMapper deliveryTicketMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private DeliveryTicket deliveryTicket;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DeliveryTicket createEntity(EntityManager em) {
        DeliveryTicket deliveryTicket = new DeliveryTicket()
            .deliveryTicketNo(DEFAULT_DELIVERY_TICKET_NO)
            .soId(DEFAULT_SO_ID)
            .soNo(DEFAULT_SO_NO)
            .patientFirstName(DEFAULT_PATIENT_FIRST_NAME)
            .patientMiddleName(DEFAULT_PATIENT_MIDDLE_NAME)
            .patientLastName(DEFAULT_PATIENT_LAST_NAME)
            .gender(DEFAULT_GENDER)
            .ageAsOnDate(DEFAULT_AGE_AS_ON_DATE)
            .phone1(DEFAULT_PHONE_1)
            .phone2(DEFAULT_PHONE_2)
            .email(DEFAULT_EMAIL)
            .deliveryAddress1(DEFAULT_DELIVERY_ADDRESS_1)
            .deliveryAddress2(DEFAULT_DELIVERY_ADDRESS_2)
            .deliveryCity(DEFAULT_DELIVERY_CITY)
            .deliveryState(DEFAULT_DELIVERY_STATE)
            .deliveryZip(DEFAULT_DELIVERY_ZIP)
            .deliveryStatus(DEFAULT_DELIVERY_STATUS)
            .deliveryDate(DEFAULT_DELIVERY_DATE)
            .deliveryPriority(DEFAULT_DELIVERY_PRIORITY)
            .deliveryNote(DEFAULT_DELIVERY_NOTE)
            .deliveryComment(DEFAULT_DELIVERY_COMMENT)
            .deliveryAcceptedBy(DEFAULT_DELIVERY_ACCEPTED_BY)
            .relationshipWithPatient(DEFAULT_RELATIONSHIP_WITH_PATIENT)
            .deliveryAcceptedByContactNo(DEFAULT_DELIVERY_ACCEPTED_BY_CONTACT_NO)
            .primaryInsurerName(DEFAULT_PRIMARY_INSURER_NAME)
            .primaryInsurerPolicyNo(DEFAULT_PRIMARY_INSURER_POLICY_NO)
            .primaryInsurerPatientIdNumber(DEFAULT_PRIMARY_INSURER_PATIENT_ID_NUMBER)
            .patientIdNo(DEFAULT_PATIENT_ID_NO)
            .branchAddressLine1(DEFAULT_BRANCH_ADDRESS_LINE_1)
            .branchAddressLine2(DEFAULT_BRANCH_ADDRESS_LINE_2)
            .branchCity(DEFAULT_BRANCH_CITY)
            .branchState(DEFAULT_BRANCH_STATE)
            .branchZipCode(DEFAULT_BRANCH_ZIP_CODE)
            .branchContactNo1(DEFAULT_BRANCH_CONTACT_NO_1)
            .branchContactNo2(DEFAULT_BRANCH_CONTACT_NO_2)
            .branchNpi(DEFAULT_BRANCH_NPI)
            .branchEin(DEFAULT_BRANCH_EIN)
            .branchFax(DEFAULT_BRANCH_FAX)
            .orderingProviderFirstName(DEFAULT_ORDERING_PROVIDER_FIRST_NAME)
            .orderingProviderMiddleName(DEFAULT_ORDERING_PROVIDER_MIDDLE_NAME)
            .orderingProviderLastName(DEFAULT_ORDERING_PROVIDER_LAST_NAME)
            .orderingProviderNpi(DEFAULT_ORDERING_PROVIDER_NPI)
            .orderingProviderAddressLine1(DEFAULT_ORDERING_PROVIDER_ADDRESS_LINE_1)
            .orderingProviderAddressLine2(DEFAULT_ORDERING_PROVIDER_ADDRESS_LINE_2)
            .orderingProviderCity(DEFAULT_ORDERING_PROVIDER_CITY)
            .orderingProviderState(DEFAULT_ORDERING_PROVIDER_STATE)
            .orderingProviderZip(DEFAULT_ORDERING_PROVIDER_ZIP)
            .orderingProviderPhone1(DEFAULT_ORDERING_PROVIDER_PHONE_1)
            .orderingProviderPhone2(DEFAULT_ORDERING_PROVIDER_PHONE_2)
            .orderingProviderFax(DEFAULT_ORDERING_PROVIDER_FAX)
            .orderingProviderEmail(DEFAULT_ORDERING_PROVIDER_EMAIL)
            .branchName(DEFAULT_BRANCH_NAME)
            .patientBranchId(DEFAULT_PATIENT_BRANCH_ID)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedBy(DEFAULT_UPDATED_BY)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .deliveryTicketUuid(DEFAULT_DELIVERY_TICKET_UUID)
            .billingAddressLine1(DEFAULT_BILLING_ADDRESS_LINE_1)
            .billingAddressLine2(DEFAULT_BILLING_ADDRESS_LINE_2)
            .billingCity(DEFAULT_BILLING_CITY)
            .billingState(DEFAULT_BILLING_STATE)
            .billingZip(DEFAULT_BILLING_ZIP)
            .inventoryLocationId(DEFAULT_INVENTORY_LOCATION_ID)
            .inventoryLocationName(DEFAULT_INVENTORY_LOCATION_NAME)
            .deliveryTicketDocumentId(DEFAULT_DELIVERY_TICKET_DOCUMENT_ID)
            .deliveryTicketDocumentNo(DEFAULT_DELIVERY_TICKET_DOCUMENT_NO)
            .deliveryTicketDocumentName(DEFAULT_DELIVERY_TICKET_DOCUMENT_NAME)
            .deliveryType(DEFAULT_DELIVERY_TYPE)
            .carrierName(DEFAULT_CARRIER_NAME)
            .shippingDate(DEFAULT_SHIPPING_DATE)
            .trackingNo(DEFAULT_TRACKING_NO)
            .referenceNo(DEFAULT_REFERENCE_NO)
            .packageWeight(DEFAULT_PACKAGE_WEIGHT)
            .setupMethod(DEFAULT_SETUP_METHOD)
            .setupTechnicianNo(DEFAULT_SETUP_TECHNICIAN_NO)
            .setupTechnicianContactNo(DEFAULT_SETUP_TECHNICIAN_CONTACT_NO)
            .setupTechnicianFirstName(DEFAULT_SETUP_TECHNICIAN_FIRST_NAME)
            .setupTechnicianMiddleName(DEFAULT_SETUP_TECHNICIAN_MIDDLE_NAME)
            .setupTechnicianLastName(DEFAULT_SETUP_TECHNICIAN_LAST_NAME)
            .setupDateTime(DEFAULT_SETUP_DATE_TIME)
            .scheduleSetupDateTime(DEFAULT_SCHEDULE_SETUP_DATE_TIME)
            .setupComments(DEFAULT_SETUP_COMMENTS)
            .setupStatus(DEFAULT_SETUP_STATUS)
            .courierPackageAcceptedBy(DEFAULT_COURIER_PACKAGE_ACCEPTED_BY)
            .therapistFirstName(DEFAULT_THERAPIST_FIRST_NAME)
            .therapistMiddleName(DEFAULT_THERAPIST_MIDDLE_NAME)
            .therapistLastName(DEFAULT_THERAPIST_LAST_NAME)
            .therapistLicenseNo(DEFAULT_THERAPIST_LICENSE_NO)
            .therapistNpi(DEFAULT_THERAPIST_NPI)
            .therapistTaxonomyCode(DEFAULT_THERAPIST_TAXONOMY_CODE)
            .scheduleTherapyDate(DEFAULT_SCHEDULE_THERAPY_DATE)
            .actualTherapyDate(DEFAULT_ACTUAL_THERAPY_DATE)
            .therapyMode(DEFAULT_THERAPY_MODE)
            .therapyStatus(DEFAULT_THERAPY_STATUS)
            .therapyNotes(DEFAULT_THERAPY_NOTES);
        return deliveryTicket;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DeliveryTicket createUpdatedEntity(EntityManager em) {
        DeliveryTicket deliveryTicket = new DeliveryTicket()
            .deliveryTicketNo(UPDATED_DELIVERY_TICKET_NO)
            .soId(UPDATED_SO_ID)
            .soNo(UPDATED_SO_NO)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientMiddleName(UPDATED_PATIENT_MIDDLE_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .gender(UPDATED_GENDER)
            .ageAsOnDate(UPDATED_AGE_AS_ON_DATE)
            .phone1(UPDATED_PHONE_1)
            .phone2(UPDATED_PHONE_2)
            .email(UPDATED_EMAIL)
            .deliveryAddress1(UPDATED_DELIVERY_ADDRESS_1)
            .deliveryAddress2(UPDATED_DELIVERY_ADDRESS_2)
            .deliveryCity(UPDATED_DELIVERY_CITY)
            .deliveryState(UPDATED_DELIVERY_STATE)
            .deliveryZip(UPDATED_DELIVERY_ZIP)
            .deliveryStatus(UPDATED_DELIVERY_STATUS)
            .deliveryDate(UPDATED_DELIVERY_DATE)
            .deliveryPriority(UPDATED_DELIVERY_PRIORITY)
            .deliveryNote(UPDATED_DELIVERY_NOTE)
            .deliveryComment(UPDATED_DELIVERY_COMMENT)
            .deliveryAcceptedBy(UPDATED_DELIVERY_ACCEPTED_BY)
            .relationshipWithPatient(UPDATED_RELATIONSHIP_WITH_PATIENT)
            .deliveryAcceptedByContactNo(UPDATED_DELIVERY_ACCEPTED_BY_CONTACT_NO)
            .primaryInsurerName(UPDATED_PRIMARY_INSURER_NAME)
            .primaryInsurerPolicyNo(UPDATED_PRIMARY_INSURER_POLICY_NO)
            .primaryInsurerPatientIdNumber(UPDATED_PRIMARY_INSURER_PATIENT_ID_NUMBER)
            .patientIdNo(UPDATED_PATIENT_ID_NO)
            .branchAddressLine1(UPDATED_BRANCH_ADDRESS_LINE_1)
            .branchAddressLine2(UPDATED_BRANCH_ADDRESS_LINE_2)
            .branchCity(UPDATED_BRANCH_CITY)
            .branchState(UPDATED_BRANCH_STATE)
            .branchZipCode(UPDATED_BRANCH_ZIP_CODE)
            .branchContactNo1(UPDATED_BRANCH_CONTACT_NO_1)
            .branchContactNo2(UPDATED_BRANCH_CONTACT_NO_2)
            .branchNpi(UPDATED_BRANCH_NPI)
            .branchEin(UPDATED_BRANCH_EIN)
            .branchFax(UPDATED_BRANCH_FAX)
            .orderingProviderFirstName(UPDATED_ORDERING_PROVIDER_FIRST_NAME)
            .orderingProviderMiddleName(UPDATED_ORDERING_PROVIDER_MIDDLE_NAME)
            .orderingProviderLastName(UPDATED_ORDERING_PROVIDER_LAST_NAME)
            .orderingProviderNpi(UPDATED_ORDERING_PROVIDER_NPI)
            .orderingProviderAddressLine1(UPDATED_ORDERING_PROVIDER_ADDRESS_LINE_1)
            .orderingProviderAddressLine2(UPDATED_ORDERING_PROVIDER_ADDRESS_LINE_2)
            .orderingProviderCity(UPDATED_ORDERING_PROVIDER_CITY)
            .orderingProviderState(UPDATED_ORDERING_PROVIDER_STATE)
            .orderingProviderZip(UPDATED_ORDERING_PROVIDER_ZIP)
            .orderingProviderPhone1(UPDATED_ORDERING_PROVIDER_PHONE_1)
            .orderingProviderPhone2(UPDATED_ORDERING_PROVIDER_PHONE_2)
            .orderingProviderFax(UPDATED_ORDERING_PROVIDER_FAX)
            .orderingProviderEmail(UPDATED_ORDERING_PROVIDER_EMAIL)
            .branchName(UPDATED_BRANCH_NAME)
            .patientBranchId(UPDATED_PATIENT_BRANCH_ID)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .deliveryTicketUuid(UPDATED_DELIVERY_TICKET_UUID)
            .billingAddressLine1(UPDATED_BILLING_ADDRESS_LINE_1)
            .billingAddressLine2(UPDATED_BILLING_ADDRESS_LINE_2)
            .billingCity(UPDATED_BILLING_CITY)
            .billingState(UPDATED_BILLING_STATE)
            .billingZip(UPDATED_BILLING_ZIP)
            .inventoryLocationId(UPDATED_INVENTORY_LOCATION_ID)
            .inventoryLocationName(UPDATED_INVENTORY_LOCATION_NAME)
            .deliveryTicketDocumentId(UPDATED_DELIVERY_TICKET_DOCUMENT_ID)
            .deliveryTicketDocumentNo(UPDATED_DELIVERY_TICKET_DOCUMENT_NO)
            .deliveryTicketDocumentName(UPDATED_DELIVERY_TICKET_DOCUMENT_NAME)
            .deliveryType(UPDATED_DELIVERY_TYPE)
            .carrierName(UPDATED_CARRIER_NAME)
            .shippingDate(UPDATED_SHIPPING_DATE)
            .trackingNo(UPDATED_TRACKING_NO)
            .referenceNo(UPDATED_REFERENCE_NO)
            .packageWeight(UPDATED_PACKAGE_WEIGHT)
            .setupMethod(UPDATED_SETUP_METHOD)
            .setupTechnicianNo(UPDATED_SETUP_TECHNICIAN_NO)
            .setupTechnicianContactNo(UPDATED_SETUP_TECHNICIAN_CONTACT_NO)
            .setupTechnicianFirstName(UPDATED_SETUP_TECHNICIAN_FIRST_NAME)
            .setupTechnicianMiddleName(UPDATED_SETUP_TECHNICIAN_MIDDLE_NAME)
            .setupTechnicianLastName(UPDATED_SETUP_TECHNICIAN_LAST_NAME)
            .setupDateTime(UPDATED_SETUP_DATE_TIME)
            .scheduleSetupDateTime(UPDATED_SCHEDULE_SETUP_DATE_TIME)
            .setupComments(UPDATED_SETUP_COMMENTS)
            .setupStatus(UPDATED_SETUP_STATUS)
            .courierPackageAcceptedBy(UPDATED_COURIER_PACKAGE_ACCEPTED_BY)
            .therapistFirstName(UPDATED_THERAPIST_FIRST_NAME)
            .therapistMiddleName(UPDATED_THERAPIST_MIDDLE_NAME)
            .therapistLastName(UPDATED_THERAPIST_LAST_NAME)
            .therapistLicenseNo(UPDATED_THERAPIST_LICENSE_NO)
            .therapistNpi(UPDATED_THERAPIST_NPI)
            .therapistTaxonomyCode(UPDATED_THERAPIST_TAXONOMY_CODE)
            .scheduleTherapyDate(UPDATED_SCHEDULE_THERAPY_DATE)
            .actualTherapyDate(UPDATED_ACTUAL_THERAPY_DATE)
            .therapyMode(UPDATED_THERAPY_MODE)
            .therapyStatus(UPDATED_THERAPY_STATUS)
            .therapyNotes(UPDATED_THERAPY_NOTES);
        return deliveryTicket;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(DeliveryTicket.class).block();
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
        deliveryTicket = createEntity(em);
    }

    @Test
    void createDeliveryTicket() throws Exception {
        int databaseSizeBeforeCreate = deliveryTicketRepository.findAll().collectList().block().size();
        // Create the DeliveryTicket
        DeliveryTicketDTO deliveryTicketDTO = deliveryTicketMapper.toDto(deliveryTicket);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(deliveryTicketDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the DeliveryTicket in the database
        List<DeliveryTicket> deliveryTicketList = deliveryTicketRepository.findAll().collectList().block();
        assertThat(deliveryTicketList).hasSize(databaseSizeBeforeCreate + 1);
        DeliveryTicket testDeliveryTicket = deliveryTicketList.get(deliveryTicketList.size() - 1);
        assertThat(testDeliveryTicket.getDeliveryTicketNo()).isEqualTo(DEFAULT_DELIVERY_TICKET_NO);
        assertThat(testDeliveryTicket.getSoId()).isEqualTo(DEFAULT_SO_ID);
        assertThat(testDeliveryTicket.getSoNo()).isEqualTo(DEFAULT_SO_NO);
        assertThat(testDeliveryTicket.getPatientFirstName()).isEqualTo(DEFAULT_PATIENT_FIRST_NAME);
        assertThat(testDeliveryTicket.getPatientMiddleName()).isEqualTo(DEFAULT_PATIENT_MIDDLE_NAME);
        assertThat(testDeliveryTicket.getPatientLastName()).isEqualTo(DEFAULT_PATIENT_LAST_NAME);
        assertThat(testDeliveryTicket.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testDeliveryTicket.getAgeAsOnDate()).isEqualTo(DEFAULT_AGE_AS_ON_DATE);
        assertThat(testDeliveryTicket.getPhone1()).isEqualTo(DEFAULT_PHONE_1);
        assertThat(testDeliveryTicket.getPhone2()).isEqualTo(DEFAULT_PHONE_2);
        assertThat(testDeliveryTicket.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testDeliveryTicket.getDeliveryAddress1()).isEqualTo(DEFAULT_DELIVERY_ADDRESS_1);
        assertThat(testDeliveryTicket.getDeliveryAddress2()).isEqualTo(DEFAULT_DELIVERY_ADDRESS_2);
        assertThat(testDeliveryTicket.getDeliveryCity()).isEqualTo(DEFAULT_DELIVERY_CITY);
        assertThat(testDeliveryTicket.getDeliveryState()).isEqualTo(DEFAULT_DELIVERY_STATE);
        assertThat(testDeliveryTicket.getDeliveryZip()).isEqualTo(DEFAULT_DELIVERY_ZIP);
        assertThat(testDeliveryTicket.getDeliveryStatus()).isEqualTo(DEFAULT_DELIVERY_STATUS);
        assertThat(testDeliveryTicket.getDeliveryDate()).isEqualTo(DEFAULT_DELIVERY_DATE);
        assertThat(testDeliveryTicket.getDeliveryPriority()).isEqualTo(DEFAULT_DELIVERY_PRIORITY);
        assertThat(testDeliveryTicket.getDeliveryNote()).isEqualTo(DEFAULT_DELIVERY_NOTE);
        assertThat(testDeliveryTicket.getDeliveryComment()).isEqualTo(DEFAULT_DELIVERY_COMMENT);
        assertThat(testDeliveryTicket.getDeliveryAcceptedBy()).isEqualTo(DEFAULT_DELIVERY_ACCEPTED_BY);
        assertThat(testDeliveryTicket.getRelationshipWithPatient()).isEqualTo(DEFAULT_RELATIONSHIP_WITH_PATIENT);
        assertThat(testDeliveryTicket.getDeliveryAcceptedByContactNo()).isEqualTo(DEFAULT_DELIVERY_ACCEPTED_BY_CONTACT_NO);
        assertThat(testDeliveryTicket.getPrimaryInsurerName()).isEqualTo(DEFAULT_PRIMARY_INSURER_NAME);
        assertThat(testDeliveryTicket.getPrimaryInsurerPolicyNo()).isEqualTo(DEFAULT_PRIMARY_INSURER_POLICY_NO);
        assertThat(testDeliveryTicket.getPrimaryInsurerPatientIdNumber()).isEqualTo(DEFAULT_PRIMARY_INSURER_PATIENT_ID_NUMBER);
        assertThat(testDeliveryTicket.getPatientIdNo()).isEqualTo(DEFAULT_PATIENT_ID_NO);
        assertThat(testDeliveryTicket.getBranchAddressLine1()).isEqualTo(DEFAULT_BRANCH_ADDRESS_LINE_1);
        assertThat(testDeliveryTicket.getBranchAddressLine2()).isEqualTo(DEFAULT_BRANCH_ADDRESS_LINE_2);
        assertThat(testDeliveryTicket.getBranchCity()).isEqualTo(DEFAULT_BRANCH_CITY);
        assertThat(testDeliveryTicket.getBranchState()).isEqualTo(DEFAULT_BRANCH_STATE);
        assertThat(testDeliveryTicket.getBranchZipCode()).isEqualTo(DEFAULT_BRANCH_ZIP_CODE);
        assertThat(testDeliveryTicket.getBranchContactNo1()).isEqualTo(DEFAULT_BRANCH_CONTACT_NO_1);
        assertThat(testDeliveryTicket.getBranchContactNo2()).isEqualTo(DEFAULT_BRANCH_CONTACT_NO_2);
        assertThat(testDeliveryTicket.getBranchNpi()).isEqualTo(DEFAULT_BRANCH_NPI);
        assertThat(testDeliveryTicket.getBranchEin()).isEqualTo(DEFAULT_BRANCH_EIN);
        assertThat(testDeliveryTicket.getBranchFax()).isEqualTo(DEFAULT_BRANCH_FAX);
        assertThat(testDeliveryTicket.getOrderingProviderFirstName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testDeliveryTicket.getOrderingProviderMiddleName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_MIDDLE_NAME);
        assertThat(testDeliveryTicket.getOrderingProviderLastName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testDeliveryTicket.getOrderingProviderNpi()).isEqualTo(DEFAULT_ORDERING_PROVIDER_NPI);
        assertThat(testDeliveryTicket.getOrderingProviderAddressLine1()).isEqualTo(DEFAULT_ORDERING_PROVIDER_ADDRESS_LINE_1);
        assertThat(testDeliveryTicket.getOrderingProviderAddressLine2()).isEqualTo(DEFAULT_ORDERING_PROVIDER_ADDRESS_LINE_2);
        assertThat(testDeliveryTicket.getOrderingProviderCity()).isEqualTo(DEFAULT_ORDERING_PROVIDER_CITY);
        assertThat(testDeliveryTicket.getOrderingProviderState()).isEqualTo(DEFAULT_ORDERING_PROVIDER_STATE);
        assertThat(testDeliveryTicket.getOrderingProviderZip()).isEqualTo(DEFAULT_ORDERING_PROVIDER_ZIP);
        assertThat(testDeliveryTicket.getOrderingProviderPhone1()).isEqualTo(DEFAULT_ORDERING_PROVIDER_PHONE_1);
        assertThat(testDeliveryTicket.getOrderingProviderPhone2()).isEqualTo(DEFAULT_ORDERING_PROVIDER_PHONE_2);
        assertThat(testDeliveryTicket.getOrderingProviderFax()).isEqualTo(DEFAULT_ORDERING_PROVIDER_FAX);
        assertThat(testDeliveryTicket.getOrderingProviderEmail()).isEqualTo(DEFAULT_ORDERING_PROVIDER_EMAIL);
        assertThat(testDeliveryTicket.getBranchName()).isEqualTo(DEFAULT_BRANCH_NAME);
        assertThat(testDeliveryTicket.getPatientBranchId()).isEqualTo(DEFAULT_PATIENT_BRANCH_ID);
        assertThat(testDeliveryTicket.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testDeliveryTicket.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testDeliveryTicket.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testDeliveryTicket.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testDeliveryTicket.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testDeliveryTicket.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testDeliveryTicket.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testDeliveryTicket.getDeliveryTicketUuid()).isEqualTo(DEFAULT_DELIVERY_TICKET_UUID);
        assertThat(testDeliveryTicket.getBillingAddressLine1()).isEqualTo(DEFAULT_BILLING_ADDRESS_LINE_1);
        assertThat(testDeliveryTicket.getBillingAddressLine2()).isEqualTo(DEFAULT_BILLING_ADDRESS_LINE_2);
        assertThat(testDeliveryTicket.getBillingCity()).isEqualTo(DEFAULT_BILLING_CITY);
        assertThat(testDeliveryTicket.getBillingState()).isEqualTo(DEFAULT_BILLING_STATE);
        assertThat(testDeliveryTicket.getBillingZip()).isEqualTo(DEFAULT_BILLING_ZIP);
        assertThat(testDeliveryTicket.getInventoryLocationId()).isEqualTo(DEFAULT_INVENTORY_LOCATION_ID);
        assertThat(testDeliveryTicket.getInventoryLocationName()).isEqualTo(DEFAULT_INVENTORY_LOCATION_NAME);
        assertThat(testDeliveryTicket.getDeliveryTicketDocumentId()).isEqualTo(DEFAULT_DELIVERY_TICKET_DOCUMENT_ID);
        assertThat(testDeliveryTicket.getDeliveryTicketDocumentNo()).isEqualTo(DEFAULT_DELIVERY_TICKET_DOCUMENT_NO);
        assertThat(testDeliveryTicket.getDeliveryTicketDocumentName()).isEqualTo(DEFAULT_DELIVERY_TICKET_DOCUMENT_NAME);
        assertThat(testDeliveryTicket.getDeliveryType()).isEqualTo(DEFAULT_DELIVERY_TYPE);
        assertThat(testDeliveryTicket.getCarrierName()).isEqualTo(DEFAULT_CARRIER_NAME);
        assertThat(testDeliveryTicket.getShippingDate()).isEqualTo(DEFAULT_SHIPPING_DATE);
        assertThat(testDeliveryTicket.getTrackingNo()).isEqualTo(DEFAULT_TRACKING_NO);
        assertThat(testDeliveryTicket.getReferenceNo()).isEqualTo(DEFAULT_REFERENCE_NO);
        assertThat(testDeliveryTicket.getPackageWeight()).isEqualTo(DEFAULT_PACKAGE_WEIGHT);
        assertThat(testDeliveryTicket.getSetupMethod()).isEqualTo(DEFAULT_SETUP_METHOD);
        assertThat(testDeliveryTicket.getSetupTechnicianNo()).isEqualTo(DEFAULT_SETUP_TECHNICIAN_NO);
        assertThat(testDeliveryTicket.getSetupTechnicianContactNo()).isEqualTo(DEFAULT_SETUP_TECHNICIAN_CONTACT_NO);
        assertThat(testDeliveryTicket.getSetupTechnicianFirstName()).isEqualTo(DEFAULT_SETUP_TECHNICIAN_FIRST_NAME);
        assertThat(testDeliveryTicket.getSetupTechnicianMiddleName()).isEqualTo(DEFAULT_SETUP_TECHNICIAN_MIDDLE_NAME);
        assertThat(testDeliveryTicket.getSetupTechnicianLastName()).isEqualTo(DEFAULT_SETUP_TECHNICIAN_LAST_NAME);
        assertThat(testDeliveryTicket.getSetupDateTime()).isEqualTo(DEFAULT_SETUP_DATE_TIME);
        assertThat(testDeliveryTicket.getScheduleSetupDateTime()).isEqualTo(DEFAULT_SCHEDULE_SETUP_DATE_TIME);
        assertThat(testDeliveryTicket.getSetupComments()).isEqualTo(DEFAULT_SETUP_COMMENTS);
        assertThat(testDeliveryTicket.getSetupStatus()).isEqualTo(DEFAULT_SETUP_STATUS);
        assertThat(testDeliveryTicket.getCourierPackageAcceptedBy()).isEqualTo(DEFAULT_COURIER_PACKAGE_ACCEPTED_BY);
        assertThat(testDeliveryTicket.getTherapistFirstName()).isEqualTo(DEFAULT_THERAPIST_FIRST_NAME);
        assertThat(testDeliveryTicket.getTherapistMiddleName()).isEqualTo(DEFAULT_THERAPIST_MIDDLE_NAME);
        assertThat(testDeliveryTicket.getTherapistLastName()).isEqualTo(DEFAULT_THERAPIST_LAST_NAME);
        assertThat(testDeliveryTicket.getTherapistLicenseNo()).isEqualTo(DEFAULT_THERAPIST_LICENSE_NO);
        assertThat(testDeliveryTicket.getTherapistNpi()).isEqualTo(DEFAULT_THERAPIST_NPI);
        assertThat(testDeliveryTicket.getTherapistTaxonomyCode()).isEqualTo(DEFAULT_THERAPIST_TAXONOMY_CODE);
        assertThat(testDeliveryTicket.getScheduleTherapyDate()).isEqualTo(DEFAULT_SCHEDULE_THERAPY_DATE);
        assertThat(testDeliveryTicket.getActualTherapyDate()).isEqualTo(DEFAULT_ACTUAL_THERAPY_DATE);
        assertThat(testDeliveryTicket.getTherapyMode()).isEqualTo(DEFAULT_THERAPY_MODE);
        assertThat(testDeliveryTicket.getTherapyStatus()).isEqualTo(DEFAULT_THERAPY_STATUS);
        assertThat(testDeliveryTicket.getTherapyNotes()).isEqualTo(DEFAULT_THERAPY_NOTES);
    }

    @Test
    void createDeliveryTicketWithExistingId() throws Exception {
        // Create the DeliveryTicket with an existing ID
        deliveryTicket.setDeliveryTicketId(1L);
        DeliveryTicketDTO deliveryTicketDTO = deliveryTicketMapper.toDto(deliveryTicket);

        int databaseSizeBeforeCreate = deliveryTicketRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(deliveryTicketDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DeliveryTicket in the database
        List<DeliveryTicket> deliveryTicketList = deliveryTicketRepository.findAll().collectList().block();
        assertThat(deliveryTicketList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllDeliveryTickets() {
        // Initialize the database
        deliveryTicketRepository.save(deliveryTicket).block();

        // Get all the deliveryTicketList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=deliveryTicketId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].deliveryTicketId")
            .value(hasItem(deliveryTicket.getDeliveryTicketId().intValue()))
            .jsonPath("$.[*].deliveryTicketNo")
            .value(hasItem(DEFAULT_DELIVERY_TICKET_NO))
            .jsonPath("$.[*].soId")
            .value(hasItem(DEFAULT_SO_ID.intValue()))
            .jsonPath("$.[*].soNo")
            .value(hasItem(DEFAULT_SO_NO))
            .jsonPath("$.[*].patientFirstName")
            .value(hasItem(DEFAULT_PATIENT_FIRST_NAME))
            .jsonPath("$.[*].patientMiddleName")
            .value(hasItem(DEFAULT_PATIENT_MIDDLE_NAME))
            .jsonPath("$.[*].patientLastName")
            .value(hasItem(DEFAULT_PATIENT_LAST_NAME))
            .jsonPath("$.[*].gender")
            .value(hasItem(DEFAULT_GENDER))
            .jsonPath("$.[*].ageAsOnDate")
            .value(hasItem(DEFAULT_AGE_AS_ON_DATE))
            .jsonPath("$.[*].phone1")
            .value(hasItem(DEFAULT_PHONE_1))
            .jsonPath("$.[*].phone2")
            .value(hasItem(DEFAULT_PHONE_2))
            .jsonPath("$.[*].email")
            .value(hasItem(DEFAULT_EMAIL))
            .jsonPath("$.[*].deliveryAddress1")
            .value(hasItem(DEFAULT_DELIVERY_ADDRESS_1))
            .jsonPath("$.[*].deliveryAddress2")
            .value(hasItem(DEFAULT_DELIVERY_ADDRESS_2))
            .jsonPath("$.[*].deliveryCity")
            .value(hasItem(DEFAULT_DELIVERY_CITY))
            .jsonPath("$.[*].deliveryState")
            .value(hasItem(DEFAULT_DELIVERY_STATE))
            .jsonPath("$.[*].deliveryZip")
            .value(hasItem(DEFAULT_DELIVERY_ZIP))
            .jsonPath("$.[*].deliveryStatus")
            .value(hasItem(DEFAULT_DELIVERY_STATUS))
            .jsonPath("$.[*].deliveryDate")
            .value(hasItem(DEFAULT_DELIVERY_DATE.toString()))
            .jsonPath("$.[*].deliveryPriority")
            .value(hasItem(DEFAULT_DELIVERY_PRIORITY))
            .jsonPath("$.[*].deliveryNote")
            .value(hasItem(DEFAULT_DELIVERY_NOTE))
            .jsonPath("$.[*].deliveryComment")
            .value(hasItem(DEFAULT_DELIVERY_COMMENT))
            .jsonPath("$.[*].deliveryAcceptedBy")
            .value(hasItem(DEFAULT_DELIVERY_ACCEPTED_BY))
            .jsonPath("$.[*].relationshipWithPatient")
            .value(hasItem(DEFAULT_RELATIONSHIP_WITH_PATIENT))
            .jsonPath("$.[*].deliveryAcceptedByContactNo")
            .value(hasItem(DEFAULT_DELIVERY_ACCEPTED_BY_CONTACT_NO))
            .jsonPath("$.[*].primaryInsurerName")
            .value(hasItem(DEFAULT_PRIMARY_INSURER_NAME))
            .jsonPath("$.[*].primaryInsurerPolicyNo")
            .value(hasItem(DEFAULT_PRIMARY_INSURER_POLICY_NO))
            .jsonPath("$.[*].primaryInsurerPatientIdNumber")
            .value(hasItem(DEFAULT_PRIMARY_INSURER_PATIENT_ID_NUMBER))
            .jsonPath("$.[*].patientIdNo")
            .value(hasItem(DEFAULT_PATIENT_ID_NO))
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
            .jsonPath("$.[*].branchContactNo1")
            .value(hasItem(DEFAULT_BRANCH_CONTACT_NO_1))
            .jsonPath("$.[*].branchContactNo2")
            .value(hasItem(DEFAULT_BRANCH_CONTACT_NO_2))
            .jsonPath("$.[*].branchNpi")
            .value(hasItem(DEFAULT_BRANCH_NPI))
            .jsonPath("$.[*].branchEin")
            .value(hasItem(DEFAULT_BRANCH_EIN))
            .jsonPath("$.[*].branchFax")
            .value(hasItem(DEFAULT_BRANCH_FAX))
            .jsonPath("$.[*].orderingProviderFirstName")
            .value(hasItem(DEFAULT_ORDERING_PROVIDER_FIRST_NAME))
            .jsonPath("$.[*].orderingProviderMiddleName")
            .value(hasItem(DEFAULT_ORDERING_PROVIDER_MIDDLE_NAME))
            .jsonPath("$.[*].orderingProviderLastName")
            .value(hasItem(DEFAULT_ORDERING_PROVIDER_LAST_NAME))
            .jsonPath("$.[*].orderingProviderNpi")
            .value(hasItem(DEFAULT_ORDERING_PROVIDER_NPI))
            .jsonPath("$.[*].orderingProviderAddressLine1")
            .value(hasItem(DEFAULT_ORDERING_PROVIDER_ADDRESS_LINE_1))
            .jsonPath("$.[*].orderingProviderAddressLine2")
            .value(hasItem(DEFAULT_ORDERING_PROVIDER_ADDRESS_LINE_2))
            .jsonPath("$.[*].orderingProviderCity")
            .value(hasItem(DEFAULT_ORDERING_PROVIDER_CITY))
            .jsonPath("$.[*].orderingProviderState")
            .value(hasItem(DEFAULT_ORDERING_PROVIDER_STATE))
            .jsonPath("$.[*].orderingProviderZip")
            .value(hasItem(DEFAULT_ORDERING_PROVIDER_ZIP))
            .jsonPath("$.[*].orderingProviderPhone1")
            .value(hasItem(DEFAULT_ORDERING_PROVIDER_PHONE_1))
            .jsonPath("$.[*].orderingProviderPhone2")
            .value(hasItem(DEFAULT_ORDERING_PROVIDER_PHONE_2))
            .jsonPath("$.[*].orderingProviderFax")
            .value(hasItem(DEFAULT_ORDERING_PROVIDER_FAX))
            .jsonPath("$.[*].orderingProviderEmail")
            .value(hasItem(DEFAULT_ORDERING_PROVIDER_EMAIL))
            .jsonPath("$.[*].branchName")
            .value(hasItem(DEFAULT_BRANCH_NAME))
            .jsonPath("$.[*].patientBranchId")
            .value(hasItem(DEFAULT_PATIENT_BRANCH_ID.intValue()))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS))
            .jsonPath("$.[*].createdById")
            .value(hasItem(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.[*].createdByName")
            .value(hasItem(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.[*].createdDate")
            .value(hasItem(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.[*].updatedBy")
            .value(hasItem(DEFAULT_UPDATED_BY.intValue()))
            .jsonPath("$.[*].updatedByName")
            .value(hasItem(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.[*].updatedDate")
            .value(hasItem(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.[*].deliveryTicketUuid")
            .value(hasItem(DEFAULT_DELIVERY_TICKET_UUID.toString()))
            .jsonPath("$.[*].billingAddressLine1")
            .value(hasItem(DEFAULT_BILLING_ADDRESS_LINE_1))
            .jsonPath("$.[*].billingAddressLine2")
            .value(hasItem(DEFAULT_BILLING_ADDRESS_LINE_2))
            .jsonPath("$.[*].billingCity")
            .value(hasItem(DEFAULT_BILLING_CITY))
            .jsonPath("$.[*].billingState")
            .value(hasItem(DEFAULT_BILLING_STATE))
            .jsonPath("$.[*].billingZip")
            .value(hasItem(DEFAULT_BILLING_ZIP))
            .jsonPath("$.[*].inventoryLocationId")
            .value(hasItem(DEFAULT_INVENTORY_LOCATION_ID.intValue()))
            .jsonPath("$.[*].inventoryLocationName")
            .value(hasItem(DEFAULT_INVENTORY_LOCATION_NAME))
            .jsonPath("$.[*].deliveryTicketDocumentId")
            .value(hasItem(DEFAULT_DELIVERY_TICKET_DOCUMENT_ID.intValue()))
            .jsonPath("$.[*].deliveryTicketDocumentNo")
            .value(hasItem(DEFAULT_DELIVERY_TICKET_DOCUMENT_NO))
            .jsonPath("$.[*].deliveryTicketDocumentName")
            .value(hasItem(DEFAULT_DELIVERY_TICKET_DOCUMENT_NAME))
            .jsonPath("$.[*].deliveryType")
            .value(hasItem(DEFAULT_DELIVERY_TYPE))
            .jsonPath("$.[*].carrierName")
            .value(hasItem(DEFAULT_CARRIER_NAME))
            .jsonPath("$.[*].shippingDate")
            .value(hasItem(DEFAULT_SHIPPING_DATE.toString()))
            .jsonPath("$.[*].trackingNo")
            .value(hasItem(DEFAULT_TRACKING_NO))
            .jsonPath("$.[*].referenceNo")
            .value(hasItem(DEFAULT_REFERENCE_NO))
            .jsonPath("$.[*].packageWeight")
            .value(hasItem(DEFAULT_PACKAGE_WEIGHT))
            .jsonPath("$.[*].setupMethod")
            .value(hasItem(DEFAULT_SETUP_METHOD))
            .jsonPath("$.[*].setupTechnicianNo")
            .value(hasItem(DEFAULT_SETUP_TECHNICIAN_NO))
            .jsonPath("$.[*].setupTechnicianContactNo")
            .value(hasItem(DEFAULT_SETUP_TECHNICIAN_CONTACT_NO))
            .jsonPath("$.[*].setupTechnicianFirstName")
            .value(hasItem(DEFAULT_SETUP_TECHNICIAN_FIRST_NAME))
            .jsonPath("$.[*].setupTechnicianMiddleName")
            .value(hasItem(DEFAULT_SETUP_TECHNICIAN_MIDDLE_NAME))
            .jsonPath("$.[*].setupTechnicianLastName")
            .value(hasItem(DEFAULT_SETUP_TECHNICIAN_LAST_NAME))
            .jsonPath("$.[*].setupDateTime")
            .value(hasItem(DEFAULT_SETUP_DATE_TIME.toString()))
            .jsonPath("$.[*].scheduleSetupDateTime")
            .value(hasItem(DEFAULT_SCHEDULE_SETUP_DATE_TIME.toString()))
            .jsonPath("$.[*].setupComments")
            .value(hasItem(DEFAULT_SETUP_COMMENTS))
            .jsonPath("$.[*].setupStatus")
            .value(hasItem(DEFAULT_SETUP_STATUS))
            .jsonPath("$.[*].courierPackageAcceptedBy")
            .value(hasItem(DEFAULT_COURIER_PACKAGE_ACCEPTED_BY))
            .jsonPath("$.[*].therapistFirstName")
            .value(hasItem(DEFAULT_THERAPIST_FIRST_NAME))
            .jsonPath("$.[*].therapistMiddleName")
            .value(hasItem(DEFAULT_THERAPIST_MIDDLE_NAME))
            .jsonPath("$.[*].therapistLastName")
            .value(hasItem(DEFAULT_THERAPIST_LAST_NAME))
            .jsonPath("$.[*].therapistLicenseNo")
            .value(hasItem(DEFAULT_THERAPIST_LICENSE_NO))
            .jsonPath("$.[*].therapistNpi")
            .value(hasItem(DEFAULT_THERAPIST_NPI))
            .jsonPath("$.[*].therapistTaxonomyCode")
            .value(hasItem(DEFAULT_THERAPIST_TAXONOMY_CODE))
            .jsonPath("$.[*].scheduleTherapyDate")
            .value(hasItem(DEFAULT_SCHEDULE_THERAPY_DATE.toString()))
            .jsonPath("$.[*].actualTherapyDate")
            .value(hasItem(DEFAULT_ACTUAL_THERAPY_DATE.toString()))
            .jsonPath("$.[*].therapyMode")
            .value(hasItem(DEFAULT_THERAPY_MODE))
            .jsonPath("$.[*].therapyStatus")
            .value(hasItem(DEFAULT_THERAPY_STATUS))
            .jsonPath("$.[*].therapyNotes")
            .value(hasItem(DEFAULT_THERAPY_NOTES));
    }

    @Test
    void getDeliveryTicket() {
        // Initialize the database
        deliveryTicketRepository.save(deliveryTicket).block();

        // Get the deliveryTicket
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, deliveryTicket.getDeliveryTicketId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.deliveryTicketId")
            .value(is(deliveryTicket.getDeliveryTicketId().intValue()))
            .jsonPath("$.deliveryTicketNo")
            .value(is(DEFAULT_DELIVERY_TICKET_NO))
            .jsonPath("$.soId")
            .value(is(DEFAULT_SO_ID.intValue()))
            .jsonPath("$.soNo")
            .value(is(DEFAULT_SO_NO))
            .jsonPath("$.patientFirstName")
            .value(is(DEFAULT_PATIENT_FIRST_NAME))
            .jsonPath("$.patientMiddleName")
            .value(is(DEFAULT_PATIENT_MIDDLE_NAME))
            .jsonPath("$.patientLastName")
            .value(is(DEFAULT_PATIENT_LAST_NAME))
            .jsonPath("$.gender")
            .value(is(DEFAULT_GENDER))
            .jsonPath("$.ageAsOnDate")
            .value(is(DEFAULT_AGE_AS_ON_DATE))
            .jsonPath("$.phone1")
            .value(is(DEFAULT_PHONE_1))
            .jsonPath("$.phone2")
            .value(is(DEFAULT_PHONE_2))
            .jsonPath("$.email")
            .value(is(DEFAULT_EMAIL))
            .jsonPath("$.deliveryAddress1")
            .value(is(DEFAULT_DELIVERY_ADDRESS_1))
            .jsonPath("$.deliveryAddress2")
            .value(is(DEFAULT_DELIVERY_ADDRESS_2))
            .jsonPath("$.deliveryCity")
            .value(is(DEFAULT_DELIVERY_CITY))
            .jsonPath("$.deliveryState")
            .value(is(DEFAULT_DELIVERY_STATE))
            .jsonPath("$.deliveryZip")
            .value(is(DEFAULT_DELIVERY_ZIP))
            .jsonPath("$.deliveryStatus")
            .value(is(DEFAULT_DELIVERY_STATUS))
            .jsonPath("$.deliveryDate")
            .value(is(DEFAULT_DELIVERY_DATE.toString()))
            .jsonPath("$.deliveryPriority")
            .value(is(DEFAULT_DELIVERY_PRIORITY))
            .jsonPath("$.deliveryNote")
            .value(is(DEFAULT_DELIVERY_NOTE))
            .jsonPath("$.deliveryComment")
            .value(is(DEFAULT_DELIVERY_COMMENT))
            .jsonPath("$.deliveryAcceptedBy")
            .value(is(DEFAULT_DELIVERY_ACCEPTED_BY))
            .jsonPath("$.relationshipWithPatient")
            .value(is(DEFAULT_RELATIONSHIP_WITH_PATIENT))
            .jsonPath("$.deliveryAcceptedByContactNo")
            .value(is(DEFAULT_DELIVERY_ACCEPTED_BY_CONTACT_NO))
            .jsonPath("$.primaryInsurerName")
            .value(is(DEFAULT_PRIMARY_INSURER_NAME))
            .jsonPath("$.primaryInsurerPolicyNo")
            .value(is(DEFAULT_PRIMARY_INSURER_POLICY_NO))
            .jsonPath("$.primaryInsurerPatientIdNumber")
            .value(is(DEFAULT_PRIMARY_INSURER_PATIENT_ID_NUMBER))
            .jsonPath("$.patientIdNo")
            .value(is(DEFAULT_PATIENT_ID_NO))
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
            .jsonPath("$.branchContactNo1")
            .value(is(DEFAULT_BRANCH_CONTACT_NO_1))
            .jsonPath("$.branchContactNo2")
            .value(is(DEFAULT_BRANCH_CONTACT_NO_2))
            .jsonPath("$.branchNpi")
            .value(is(DEFAULT_BRANCH_NPI))
            .jsonPath("$.branchEin")
            .value(is(DEFAULT_BRANCH_EIN))
            .jsonPath("$.branchFax")
            .value(is(DEFAULT_BRANCH_FAX))
            .jsonPath("$.orderingProviderFirstName")
            .value(is(DEFAULT_ORDERING_PROVIDER_FIRST_NAME))
            .jsonPath("$.orderingProviderMiddleName")
            .value(is(DEFAULT_ORDERING_PROVIDER_MIDDLE_NAME))
            .jsonPath("$.orderingProviderLastName")
            .value(is(DEFAULT_ORDERING_PROVIDER_LAST_NAME))
            .jsonPath("$.orderingProviderNpi")
            .value(is(DEFAULT_ORDERING_PROVIDER_NPI))
            .jsonPath("$.orderingProviderAddressLine1")
            .value(is(DEFAULT_ORDERING_PROVIDER_ADDRESS_LINE_1))
            .jsonPath("$.orderingProviderAddressLine2")
            .value(is(DEFAULT_ORDERING_PROVIDER_ADDRESS_LINE_2))
            .jsonPath("$.orderingProviderCity")
            .value(is(DEFAULT_ORDERING_PROVIDER_CITY))
            .jsonPath("$.orderingProviderState")
            .value(is(DEFAULT_ORDERING_PROVIDER_STATE))
            .jsonPath("$.orderingProviderZip")
            .value(is(DEFAULT_ORDERING_PROVIDER_ZIP))
            .jsonPath("$.orderingProviderPhone1")
            .value(is(DEFAULT_ORDERING_PROVIDER_PHONE_1))
            .jsonPath("$.orderingProviderPhone2")
            .value(is(DEFAULT_ORDERING_PROVIDER_PHONE_2))
            .jsonPath("$.orderingProviderFax")
            .value(is(DEFAULT_ORDERING_PROVIDER_FAX))
            .jsonPath("$.orderingProviderEmail")
            .value(is(DEFAULT_ORDERING_PROVIDER_EMAIL))
            .jsonPath("$.branchName")
            .value(is(DEFAULT_BRANCH_NAME))
            .jsonPath("$.patientBranchId")
            .value(is(DEFAULT_PATIENT_BRANCH_ID.intValue()))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS))
            .jsonPath("$.createdById")
            .value(is(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.createdByName")
            .value(is(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.createdDate")
            .value(is(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.updatedBy")
            .value(is(DEFAULT_UPDATED_BY.intValue()))
            .jsonPath("$.updatedByName")
            .value(is(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.updatedDate")
            .value(is(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.deliveryTicketUuid")
            .value(is(DEFAULT_DELIVERY_TICKET_UUID.toString()))
            .jsonPath("$.billingAddressLine1")
            .value(is(DEFAULT_BILLING_ADDRESS_LINE_1))
            .jsonPath("$.billingAddressLine2")
            .value(is(DEFAULT_BILLING_ADDRESS_LINE_2))
            .jsonPath("$.billingCity")
            .value(is(DEFAULT_BILLING_CITY))
            .jsonPath("$.billingState")
            .value(is(DEFAULT_BILLING_STATE))
            .jsonPath("$.billingZip")
            .value(is(DEFAULT_BILLING_ZIP))
            .jsonPath("$.inventoryLocationId")
            .value(is(DEFAULT_INVENTORY_LOCATION_ID.intValue()))
            .jsonPath("$.inventoryLocationName")
            .value(is(DEFAULT_INVENTORY_LOCATION_NAME))
            .jsonPath("$.deliveryTicketDocumentId")
            .value(is(DEFAULT_DELIVERY_TICKET_DOCUMENT_ID.intValue()))
            .jsonPath("$.deliveryTicketDocumentNo")
            .value(is(DEFAULT_DELIVERY_TICKET_DOCUMENT_NO))
            .jsonPath("$.deliveryTicketDocumentName")
            .value(is(DEFAULT_DELIVERY_TICKET_DOCUMENT_NAME))
            .jsonPath("$.deliveryType")
            .value(is(DEFAULT_DELIVERY_TYPE))
            .jsonPath("$.carrierName")
            .value(is(DEFAULT_CARRIER_NAME))
            .jsonPath("$.shippingDate")
            .value(is(DEFAULT_SHIPPING_DATE.toString()))
            .jsonPath("$.trackingNo")
            .value(is(DEFAULT_TRACKING_NO))
            .jsonPath("$.referenceNo")
            .value(is(DEFAULT_REFERENCE_NO))
            .jsonPath("$.packageWeight")
            .value(is(DEFAULT_PACKAGE_WEIGHT))
            .jsonPath("$.setupMethod")
            .value(is(DEFAULT_SETUP_METHOD))
            .jsonPath("$.setupTechnicianNo")
            .value(is(DEFAULT_SETUP_TECHNICIAN_NO))
            .jsonPath("$.setupTechnicianContactNo")
            .value(is(DEFAULT_SETUP_TECHNICIAN_CONTACT_NO))
            .jsonPath("$.setupTechnicianFirstName")
            .value(is(DEFAULT_SETUP_TECHNICIAN_FIRST_NAME))
            .jsonPath("$.setupTechnicianMiddleName")
            .value(is(DEFAULT_SETUP_TECHNICIAN_MIDDLE_NAME))
            .jsonPath("$.setupTechnicianLastName")
            .value(is(DEFAULT_SETUP_TECHNICIAN_LAST_NAME))
            .jsonPath("$.setupDateTime")
            .value(is(DEFAULT_SETUP_DATE_TIME.toString()))
            .jsonPath("$.scheduleSetupDateTime")
            .value(is(DEFAULT_SCHEDULE_SETUP_DATE_TIME.toString()))
            .jsonPath("$.setupComments")
            .value(is(DEFAULT_SETUP_COMMENTS))
            .jsonPath("$.setupStatus")
            .value(is(DEFAULT_SETUP_STATUS))
            .jsonPath("$.courierPackageAcceptedBy")
            .value(is(DEFAULT_COURIER_PACKAGE_ACCEPTED_BY))
            .jsonPath("$.therapistFirstName")
            .value(is(DEFAULT_THERAPIST_FIRST_NAME))
            .jsonPath("$.therapistMiddleName")
            .value(is(DEFAULT_THERAPIST_MIDDLE_NAME))
            .jsonPath("$.therapistLastName")
            .value(is(DEFAULT_THERAPIST_LAST_NAME))
            .jsonPath("$.therapistLicenseNo")
            .value(is(DEFAULT_THERAPIST_LICENSE_NO))
            .jsonPath("$.therapistNpi")
            .value(is(DEFAULT_THERAPIST_NPI))
            .jsonPath("$.therapistTaxonomyCode")
            .value(is(DEFAULT_THERAPIST_TAXONOMY_CODE))
            .jsonPath("$.scheduleTherapyDate")
            .value(is(DEFAULT_SCHEDULE_THERAPY_DATE.toString()))
            .jsonPath("$.actualTherapyDate")
            .value(is(DEFAULT_ACTUAL_THERAPY_DATE.toString()))
            .jsonPath("$.therapyMode")
            .value(is(DEFAULT_THERAPY_MODE))
            .jsonPath("$.therapyStatus")
            .value(is(DEFAULT_THERAPY_STATUS))
            .jsonPath("$.therapyNotes")
            .value(is(DEFAULT_THERAPY_NOTES));
    }

    @Test
    void getNonExistingDeliveryTicket() {
        // Get the deliveryTicket
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putNewDeliveryTicket() throws Exception {
        // Initialize the database
        deliveryTicketRepository.save(deliveryTicket).block();

        int databaseSizeBeforeUpdate = deliveryTicketRepository.findAll().collectList().block().size();

        // Update the deliveryTicket
        DeliveryTicket updatedDeliveryTicket = deliveryTicketRepository.findById(deliveryTicket.getDeliveryTicketId()).block();
        updatedDeliveryTicket
            .deliveryTicketNo(UPDATED_DELIVERY_TICKET_NO)
            .soId(UPDATED_SO_ID)
            .soNo(UPDATED_SO_NO)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientMiddleName(UPDATED_PATIENT_MIDDLE_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .gender(UPDATED_GENDER)
            .ageAsOnDate(UPDATED_AGE_AS_ON_DATE)
            .phone1(UPDATED_PHONE_1)
            .phone2(UPDATED_PHONE_2)
            .email(UPDATED_EMAIL)
            .deliveryAddress1(UPDATED_DELIVERY_ADDRESS_1)
            .deliveryAddress2(UPDATED_DELIVERY_ADDRESS_2)
            .deliveryCity(UPDATED_DELIVERY_CITY)
            .deliveryState(UPDATED_DELIVERY_STATE)
            .deliveryZip(UPDATED_DELIVERY_ZIP)
            .deliveryStatus(UPDATED_DELIVERY_STATUS)
            .deliveryDate(UPDATED_DELIVERY_DATE)
            .deliveryPriority(UPDATED_DELIVERY_PRIORITY)
            .deliveryNote(UPDATED_DELIVERY_NOTE)
            .deliveryComment(UPDATED_DELIVERY_COMMENT)
            .deliveryAcceptedBy(UPDATED_DELIVERY_ACCEPTED_BY)
            .relationshipWithPatient(UPDATED_RELATIONSHIP_WITH_PATIENT)
            .deliveryAcceptedByContactNo(UPDATED_DELIVERY_ACCEPTED_BY_CONTACT_NO)
            .primaryInsurerName(UPDATED_PRIMARY_INSURER_NAME)
            .primaryInsurerPolicyNo(UPDATED_PRIMARY_INSURER_POLICY_NO)
            .primaryInsurerPatientIdNumber(UPDATED_PRIMARY_INSURER_PATIENT_ID_NUMBER)
            .patientIdNo(UPDATED_PATIENT_ID_NO)
            .branchAddressLine1(UPDATED_BRANCH_ADDRESS_LINE_1)
            .branchAddressLine2(UPDATED_BRANCH_ADDRESS_LINE_2)
            .branchCity(UPDATED_BRANCH_CITY)
            .branchState(UPDATED_BRANCH_STATE)
            .branchZipCode(UPDATED_BRANCH_ZIP_CODE)
            .branchContactNo1(UPDATED_BRANCH_CONTACT_NO_1)
            .branchContactNo2(UPDATED_BRANCH_CONTACT_NO_2)
            .branchNpi(UPDATED_BRANCH_NPI)
            .branchEin(UPDATED_BRANCH_EIN)
            .branchFax(UPDATED_BRANCH_FAX)
            .orderingProviderFirstName(UPDATED_ORDERING_PROVIDER_FIRST_NAME)
            .orderingProviderMiddleName(UPDATED_ORDERING_PROVIDER_MIDDLE_NAME)
            .orderingProviderLastName(UPDATED_ORDERING_PROVIDER_LAST_NAME)
            .orderingProviderNpi(UPDATED_ORDERING_PROVIDER_NPI)
            .orderingProviderAddressLine1(UPDATED_ORDERING_PROVIDER_ADDRESS_LINE_1)
            .orderingProviderAddressLine2(UPDATED_ORDERING_PROVIDER_ADDRESS_LINE_2)
            .orderingProviderCity(UPDATED_ORDERING_PROVIDER_CITY)
            .orderingProviderState(UPDATED_ORDERING_PROVIDER_STATE)
            .orderingProviderZip(UPDATED_ORDERING_PROVIDER_ZIP)
            .orderingProviderPhone1(UPDATED_ORDERING_PROVIDER_PHONE_1)
            .orderingProviderPhone2(UPDATED_ORDERING_PROVIDER_PHONE_2)
            .orderingProviderFax(UPDATED_ORDERING_PROVIDER_FAX)
            .orderingProviderEmail(UPDATED_ORDERING_PROVIDER_EMAIL)
            .branchName(UPDATED_BRANCH_NAME)
            .patientBranchId(UPDATED_PATIENT_BRANCH_ID)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .deliveryTicketUuid(UPDATED_DELIVERY_TICKET_UUID)
            .billingAddressLine1(UPDATED_BILLING_ADDRESS_LINE_1)
            .billingAddressLine2(UPDATED_BILLING_ADDRESS_LINE_2)
            .billingCity(UPDATED_BILLING_CITY)
            .billingState(UPDATED_BILLING_STATE)
            .billingZip(UPDATED_BILLING_ZIP)
            .inventoryLocationId(UPDATED_INVENTORY_LOCATION_ID)
            .inventoryLocationName(UPDATED_INVENTORY_LOCATION_NAME)
            .deliveryTicketDocumentId(UPDATED_DELIVERY_TICKET_DOCUMENT_ID)
            .deliveryTicketDocumentNo(UPDATED_DELIVERY_TICKET_DOCUMENT_NO)
            .deliveryTicketDocumentName(UPDATED_DELIVERY_TICKET_DOCUMENT_NAME)
            .deliveryType(UPDATED_DELIVERY_TYPE)
            .carrierName(UPDATED_CARRIER_NAME)
            .shippingDate(UPDATED_SHIPPING_DATE)
            .trackingNo(UPDATED_TRACKING_NO)
            .referenceNo(UPDATED_REFERENCE_NO)
            .packageWeight(UPDATED_PACKAGE_WEIGHT)
            .setupMethod(UPDATED_SETUP_METHOD)
            .setupTechnicianNo(UPDATED_SETUP_TECHNICIAN_NO)
            .setupTechnicianContactNo(UPDATED_SETUP_TECHNICIAN_CONTACT_NO)
            .setupTechnicianFirstName(UPDATED_SETUP_TECHNICIAN_FIRST_NAME)
            .setupTechnicianMiddleName(UPDATED_SETUP_TECHNICIAN_MIDDLE_NAME)
            .setupTechnicianLastName(UPDATED_SETUP_TECHNICIAN_LAST_NAME)
            .setupDateTime(UPDATED_SETUP_DATE_TIME)
            .scheduleSetupDateTime(UPDATED_SCHEDULE_SETUP_DATE_TIME)
            .setupComments(UPDATED_SETUP_COMMENTS)
            .setupStatus(UPDATED_SETUP_STATUS)
            .courierPackageAcceptedBy(UPDATED_COURIER_PACKAGE_ACCEPTED_BY)
            .therapistFirstName(UPDATED_THERAPIST_FIRST_NAME)
            .therapistMiddleName(UPDATED_THERAPIST_MIDDLE_NAME)
            .therapistLastName(UPDATED_THERAPIST_LAST_NAME)
            .therapistLicenseNo(UPDATED_THERAPIST_LICENSE_NO)
            .therapistNpi(UPDATED_THERAPIST_NPI)
            .therapistTaxonomyCode(UPDATED_THERAPIST_TAXONOMY_CODE)
            .scheduleTherapyDate(UPDATED_SCHEDULE_THERAPY_DATE)
            .actualTherapyDate(UPDATED_ACTUAL_THERAPY_DATE)
            .therapyMode(UPDATED_THERAPY_MODE)
            .therapyStatus(UPDATED_THERAPY_STATUS)
            .therapyNotes(UPDATED_THERAPY_NOTES);
        DeliveryTicketDTO deliveryTicketDTO = deliveryTicketMapper.toDto(updatedDeliveryTicket);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, deliveryTicketDTO.getDeliveryTicketId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(deliveryTicketDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the DeliveryTicket in the database
        List<DeliveryTicket> deliveryTicketList = deliveryTicketRepository.findAll().collectList().block();
        assertThat(deliveryTicketList).hasSize(databaseSizeBeforeUpdate);
        DeliveryTicket testDeliveryTicket = deliveryTicketList.get(deliveryTicketList.size() - 1);
        assertThat(testDeliveryTicket.getDeliveryTicketNo()).isEqualTo(UPDATED_DELIVERY_TICKET_NO);
        assertThat(testDeliveryTicket.getSoId()).isEqualTo(UPDATED_SO_ID);
        assertThat(testDeliveryTicket.getSoNo()).isEqualTo(UPDATED_SO_NO);
        assertThat(testDeliveryTicket.getPatientFirstName()).isEqualTo(UPDATED_PATIENT_FIRST_NAME);
        assertThat(testDeliveryTicket.getPatientMiddleName()).isEqualTo(UPDATED_PATIENT_MIDDLE_NAME);
        assertThat(testDeliveryTicket.getPatientLastName()).isEqualTo(UPDATED_PATIENT_LAST_NAME);
        assertThat(testDeliveryTicket.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testDeliveryTicket.getAgeAsOnDate()).isEqualTo(UPDATED_AGE_AS_ON_DATE);
        assertThat(testDeliveryTicket.getPhone1()).isEqualTo(UPDATED_PHONE_1);
        assertThat(testDeliveryTicket.getPhone2()).isEqualTo(UPDATED_PHONE_2);
        assertThat(testDeliveryTicket.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testDeliveryTicket.getDeliveryAddress1()).isEqualTo(UPDATED_DELIVERY_ADDRESS_1);
        assertThat(testDeliveryTicket.getDeliveryAddress2()).isEqualTo(UPDATED_DELIVERY_ADDRESS_2);
        assertThat(testDeliveryTicket.getDeliveryCity()).isEqualTo(UPDATED_DELIVERY_CITY);
        assertThat(testDeliveryTicket.getDeliveryState()).isEqualTo(UPDATED_DELIVERY_STATE);
        assertThat(testDeliveryTicket.getDeliveryZip()).isEqualTo(UPDATED_DELIVERY_ZIP);
        assertThat(testDeliveryTicket.getDeliveryStatus()).isEqualTo(UPDATED_DELIVERY_STATUS);
        assertThat(testDeliveryTicket.getDeliveryDate()).isEqualTo(UPDATED_DELIVERY_DATE);
        assertThat(testDeliveryTicket.getDeliveryPriority()).isEqualTo(UPDATED_DELIVERY_PRIORITY);
        assertThat(testDeliveryTicket.getDeliveryNote()).isEqualTo(UPDATED_DELIVERY_NOTE);
        assertThat(testDeliveryTicket.getDeliveryComment()).isEqualTo(UPDATED_DELIVERY_COMMENT);
        assertThat(testDeliveryTicket.getDeliveryAcceptedBy()).isEqualTo(UPDATED_DELIVERY_ACCEPTED_BY);
        assertThat(testDeliveryTicket.getRelationshipWithPatient()).isEqualTo(UPDATED_RELATIONSHIP_WITH_PATIENT);
        assertThat(testDeliveryTicket.getDeliveryAcceptedByContactNo()).isEqualTo(UPDATED_DELIVERY_ACCEPTED_BY_CONTACT_NO);
        assertThat(testDeliveryTicket.getPrimaryInsurerName()).isEqualTo(UPDATED_PRIMARY_INSURER_NAME);
        assertThat(testDeliveryTicket.getPrimaryInsurerPolicyNo()).isEqualTo(UPDATED_PRIMARY_INSURER_POLICY_NO);
        assertThat(testDeliveryTicket.getPrimaryInsurerPatientIdNumber()).isEqualTo(UPDATED_PRIMARY_INSURER_PATIENT_ID_NUMBER);
        assertThat(testDeliveryTicket.getPatientIdNo()).isEqualTo(UPDATED_PATIENT_ID_NO);
        assertThat(testDeliveryTicket.getBranchAddressLine1()).isEqualTo(UPDATED_BRANCH_ADDRESS_LINE_1);
        assertThat(testDeliveryTicket.getBranchAddressLine2()).isEqualTo(UPDATED_BRANCH_ADDRESS_LINE_2);
        assertThat(testDeliveryTicket.getBranchCity()).isEqualTo(UPDATED_BRANCH_CITY);
        assertThat(testDeliveryTicket.getBranchState()).isEqualTo(UPDATED_BRANCH_STATE);
        assertThat(testDeliveryTicket.getBranchZipCode()).isEqualTo(UPDATED_BRANCH_ZIP_CODE);
        assertThat(testDeliveryTicket.getBranchContactNo1()).isEqualTo(UPDATED_BRANCH_CONTACT_NO_1);
        assertThat(testDeliveryTicket.getBranchContactNo2()).isEqualTo(UPDATED_BRANCH_CONTACT_NO_2);
        assertThat(testDeliveryTicket.getBranchNpi()).isEqualTo(UPDATED_BRANCH_NPI);
        assertThat(testDeliveryTicket.getBranchEin()).isEqualTo(UPDATED_BRANCH_EIN);
        assertThat(testDeliveryTicket.getBranchFax()).isEqualTo(UPDATED_BRANCH_FAX);
        assertThat(testDeliveryTicket.getOrderingProviderFirstName()).isEqualTo(UPDATED_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testDeliveryTicket.getOrderingProviderMiddleName()).isEqualTo(UPDATED_ORDERING_PROVIDER_MIDDLE_NAME);
        assertThat(testDeliveryTicket.getOrderingProviderLastName()).isEqualTo(UPDATED_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testDeliveryTicket.getOrderingProviderNpi()).isEqualTo(UPDATED_ORDERING_PROVIDER_NPI);
        assertThat(testDeliveryTicket.getOrderingProviderAddressLine1()).isEqualTo(UPDATED_ORDERING_PROVIDER_ADDRESS_LINE_1);
        assertThat(testDeliveryTicket.getOrderingProviderAddressLine2()).isEqualTo(UPDATED_ORDERING_PROVIDER_ADDRESS_LINE_2);
        assertThat(testDeliveryTicket.getOrderingProviderCity()).isEqualTo(UPDATED_ORDERING_PROVIDER_CITY);
        assertThat(testDeliveryTicket.getOrderingProviderState()).isEqualTo(UPDATED_ORDERING_PROVIDER_STATE);
        assertThat(testDeliveryTicket.getOrderingProviderZip()).isEqualTo(UPDATED_ORDERING_PROVIDER_ZIP);
        assertThat(testDeliveryTicket.getOrderingProviderPhone1()).isEqualTo(UPDATED_ORDERING_PROVIDER_PHONE_1);
        assertThat(testDeliveryTicket.getOrderingProviderPhone2()).isEqualTo(UPDATED_ORDERING_PROVIDER_PHONE_2);
        assertThat(testDeliveryTicket.getOrderingProviderFax()).isEqualTo(UPDATED_ORDERING_PROVIDER_FAX);
        assertThat(testDeliveryTicket.getOrderingProviderEmail()).isEqualTo(UPDATED_ORDERING_PROVIDER_EMAIL);
        assertThat(testDeliveryTicket.getBranchName()).isEqualTo(UPDATED_BRANCH_NAME);
        assertThat(testDeliveryTicket.getPatientBranchId()).isEqualTo(UPDATED_PATIENT_BRANCH_ID);
        assertThat(testDeliveryTicket.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDeliveryTicket.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testDeliveryTicket.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testDeliveryTicket.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testDeliveryTicket.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testDeliveryTicket.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testDeliveryTicket.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testDeliveryTicket.getDeliveryTicketUuid()).isEqualTo(UPDATED_DELIVERY_TICKET_UUID);
        assertThat(testDeliveryTicket.getBillingAddressLine1()).isEqualTo(UPDATED_BILLING_ADDRESS_LINE_1);
        assertThat(testDeliveryTicket.getBillingAddressLine2()).isEqualTo(UPDATED_BILLING_ADDRESS_LINE_2);
        assertThat(testDeliveryTicket.getBillingCity()).isEqualTo(UPDATED_BILLING_CITY);
        assertThat(testDeliveryTicket.getBillingState()).isEqualTo(UPDATED_BILLING_STATE);
        assertThat(testDeliveryTicket.getBillingZip()).isEqualTo(UPDATED_BILLING_ZIP);
        assertThat(testDeliveryTicket.getInventoryLocationId()).isEqualTo(UPDATED_INVENTORY_LOCATION_ID);
        assertThat(testDeliveryTicket.getInventoryLocationName()).isEqualTo(UPDATED_INVENTORY_LOCATION_NAME);
        assertThat(testDeliveryTicket.getDeliveryTicketDocumentId()).isEqualTo(UPDATED_DELIVERY_TICKET_DOCUMENT_ID);
        assertThat(testDeliveryTicket.getDeliveryTicketDocumentNo()).isEqualTo(UPDATED_DELIVERY_TICKET_DOCUMENT_NO);
        assertThat(testDeliveryTicket.getDeliveryTicketDocumentName()).isEqualTo(UPDATED_DELIVERY_TICKET_DOCUMENT_NAME);
        assertThat(testDeliveryTicket.getDeliveryType()).isEqualTo(UPDATED_DELIVERY_TYPE);
        assertThat(testDeliveryTicket.getCarrierName()).isEqualTo(UPDATED_CARRIER_NAME);
        assertThat(testDeliveryTicket.getShippingDate()).isEqualTo(UPDATED_SHIPPING_DATE);
        assertThat(testDeliveryTicket.getTrackingNo()).isEqualTo(UPDATED_TRACKING_NO);
        assertThat(testDeliveryTicket.getReferenceNo()).isEqualTo(UPDATED_REFERENCE_NO);
        assertThat(testDeliveryTicket.getPackageWeight()).isEqualTo(UPDATED_PACKAGE_WEIGHT);
        assertThat(testDeliveryTicket.getSetupMethod()).isEqualTo(UPDATED_SETUP_METHOD);
        assertThat(testDeliveryTicket.getSetupTechnicianNo()).isEqualTo(UPDATED_SETUP_TECHNICIAN_NO);
        assertThat(testDeliveryTicket.getSetupTechnicianContactNo()).isEqualTo(UPDATED_SETUP_TECHNICIAN_CONTACT_NO);
        assertThat(testDeliveryTicket.getSetupTechnicianFirstName()).isEqualTo(UPDATED_SETUP_TECHNICIAN_FIRST_NAME);
        assertThat(testDeliveryTicket.getSetupTechnicianMiddleName()).isEqualTo(UPDATED_SETUP_TECHNICIAN_MIDDLE_NAME);
        assertThat(testDeliveryTicket.getSetupTechnicianLastName()).isEqualTo(UPDATED_SETUP_TECHNICIAN_LAST_NAME);
        assertThat(testDeliveryTicket.getSetupDateTime()).isEqualTo(UPDATED_SETUP_DATE_TIME);
        assertThat(testDeliveryTicket.getScheduleSetupDateTime()).isEqualTo(UPDATED_SCHEDULE_SETUP_DATE_TIME);
        assertThat(testDeliveryTicket.getSetupComments()).isEqualTo(UPDATED_SETUP_COMMENTS);
        assertThat(testDeliveryTicket.getSetupStatus()).isEqualTo(UPDATED_SETUP_STATUS);
        assertThat(testDeliveryTicket.getCourierPackageAcceptedBy()).isEqualTo(UPDATED_COURIER_PACKAGE_ACCEPTED_BY);
        assertThat(testDeliveryTicket.getTherapistFirstName()).isEqualTo(UPDATED_THERAPIST_FIRST_NAME);
        assertThat(testDeliveryTicket.getTherapistMiddleName()).isEqualTo(UPDATED_THERAPIST_MIDDLE_NAME);
        assertThat(testDeliveryTicket.getTherapistLastName()).isEqualTo(UPDATED_THERAPIST_LAST_NAME);
        assertThat(testDeliveryTicket.getTherapistLicenseNo()).isEqualTo(UPDATED_THERAPIST_LICENSE_NO);
        assertThat(testDeliveryTicket.getTherapistNpi()).isEqualTo(UPDATED_THERAPIST_NPI);
        assertThat(testDeliveryTicket.getTherapistTaxonomyCode()).isEqualTo(UPDATED_THERAPIST_TAXONOMY_CODE);
        assertThat(testDeliveryTicket.getScheduleTherapyDate()).isEqualTo(UPDATED_SCHEDULE_THERAPY_DATE);
        assertThat(testDeliveryTicket.getActualTherapyDate()).isEqualTo(UPDATED_ACTUAL_THERAPY_DATE);
        assertThat(testDeliveryTicket.getTherapyMode()).isEqualTo(UPDATED_THERAPY_MODE);
        assertThat(testDeliveryTicket.getTherapyStatus()).isEqualTo(UPDATED_THERAPY_STATUS);
        assertThat(testDeliveryTicket.getTherapyNotes()).isEqualTo(UPDATED_THERAPY_NOTES);
    }

    @Test
    void putNonExistingDeliveryTicket() throws Exception {
        int databaseSizeBeforeUpdate = deliveryTicketRepository.findAll().collectList().block().size();
        deliveryTicket.setDeliveryTicketId(count.incrementAndGet());

        // Create the DeliveryTicket
        DeliveryTicketDTO deliveryTicketDTO = deliveryTicketMapper.toDto(deliveryTicket);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, deliveryTicketDTO.getDeliveryTicketId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(deliveryTicketDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DeliveryTicket in the database
        List<DeliveryTicket> deliveryTicketList = deliveryTicketRepository.findAll().collectList().block();
        assertThat(deliveryTicketList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchDeliveryTicket() throws Exception {
        int databaseSizeBeforeUpdate = deliveryTicketRepository.findAll().collectList().block().size();
        deliveryTicket.setDeliveryTicketId(count.incrementAndGet());

        // Create the DeliveryTicket
        DeliveryTicketDTO deliveryTicketDTO = deliveryTicketMapper.toDto(deliveryTicket);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(deliveryTicketDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DeliveryTicket in the database
        List<DeliveryTicket> deliveryTicketList = deliveryTicketRepository.findAll().collectList().block();
        assertThat(deliveryTicketList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamDeliveryTicket() throws Exception {
        int databaseSizeBeforeUpdate = deliveryTicketRepository.findAll().collectList().block().size();
        deliveryTicket.setDeliveryTicketId(count.incrementAndGet());

        // Create the DeliveryTicket
        DeliveryTicketDTO deliveryTicketDTO = deliveryTicketMapper.toDto(deliveryTicket);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(deliveryTicketDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the DeliveryTicket in the database
        List<DeliveryTicket> deliveryTicketList = deliveryTicketRepository.findAll().collectList().block();
        assertThat(deliveryTicketList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateDeliveryTicketWithPatch() throws Exception {
        // Initialize the database
        deliveryTicketRepository.save(deliveryTicket).block();

        int databaseSizeBeforeUpdate = deliveryTicketRepository.findAll().collectList().block().size();

        // Update the deliveryTicket using partial update
        DeliveryTicket partialUpdatedDeliveryTicket = new DeliveryTicket();
        partialUpdatedDeliveryTicket.setDeliveryTicketId(deliveryTicket.getDeliveryTicketId());

        partialUpdatedDeliveryTicket
            .deliveryTicketNo(UPDATED_DELIVERY_TICKET_NO)
            .soNo(UPDATED_SO_NO)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .phone1(UPDATED_PHONE_1)
            .phone2(UPDATED_PHONE_2)
            .deliveryAddress1(UPDATED_DELIVERY_ADDRESS_1)
            .deliveryAddress2(UPDATED_DELIVERY_ADDRESS_2)
            .deliveryState(UPDATED_DELIVERY_STATE)
            .deliveryZip(UPDATED_DELIVERY_ZIP)
            .deliveryStatus(UPDATED_DELIVERY_STATUS)
            .deliveryPriority(UPDATED_DELIVERY_PRIORITY)
            .deliveryNote(UPDATED_DELIVERY_NOTE)
            .deliveryComment(UPDATED_DELIVERY_COMMENT)
            .deliveryAcceptedBy(UPDATED_DELIVERY_ACCEPTED_BY)
            .relationshipWithPatient(UPDATED_RELATIONSHIP_WITH_PATIENT)
            .primaryInsurerPatientIdNumber(UPDATED_PRIMARY_INSURER_PATIENT_ID_NUMBER)
            .branchCity(UPDATED_BRANCH_CITY)
            .branchContactNo2(UPDATED_BRANCH_CONTACT_NO_2)
            .branchNpi(UPDATED_BRANCH_NPI)
            .branchFax(UPDATED_BRANCH_FAX)
            .orderingProviderLastName(UPDATED_ORDERING_PROVIDER_LAST_NAME)
            .orderingProviderNpi(UPDATED_ORDERING_PROVIDER_NPI)
            .orderingProviderAddressLine2(UPDATED_ORDERING_PROVIDER_ADDRESS_LINE_2)
            .orderingProviderCity(UPDATED_ORDERING_PROVIDER_CITY)
            .orderingProviderState(UPDATED_ORDERING_PROVIDER_STATE)
            .orderingProviderZip(UPDATED_ORDERING_PROVIDER_ZIP)
            .orderingProviderPhone1(UPDATED_ORDERING_PROVIDER_PHONE_1)
            .orderingProviderPhone2(UPDATED_ORDERING_PROVIDER_PHONE_2)
            .branchName(UPDATED_BRANCH_NAME)
            .patientBranchId(UPDATED_PATIENT_BRANCH_ID)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedDate(UPDATED_UPDATED_DATE)
            .billingAddressLine2(UPDATED_BILLING_ADDRESS_LINE_2)
            .billingCity(UPDATED_BILLING_CITY)
            .inventoryLocationName(UPDATED_INVENTORY_LOCATION_NAME)
            .deliveryTicketDocumentNo(UPDATED_DELIVERY_TICKET_DOCUMENT_NO)
            .deliveryType(UPDATED_DELIVERY_TYPE)
            .carrierName(UPDATED_CARRIER_NAME)
            .referenceNo(UPDATED_REFERENCE_NO)
            .setupMethod(UPDATED_SETUP_METHOD)
            .setupTechnicianNo(UPDATED_SETUP_TECHNICIAN_NO)
            .setupTechnicianContactNo(UPDATED_SETUP_TECHNICIAN_CONTACT_NO)
            .setupTechnicianLastName(UPDATED_SETUP_TECHNICIAN_LAST_NAME)
            .setupDateTime(UPDATED_SETUP_DATE_TIME)
            .scheduleSetupDateTime(UPDATED_SCHEDULE_SETUP_DATE_TIME)
            .setupComments(UPDATED_SETUP_COMMENTS)
            .setupStatus(UPDATED_SETUP_STATUS)
            .therapistMiddleName(UPDATED_THERAPIST_MIDDLE_NAME)
            .therapistLastName(UPDATED_THERAPIST_LAST_NAME);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedDeliveryTicket.getDeliveryTicketId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedDeliveryTicket))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the DeliveryTicket in the database
        List<DeliveryTicket> deliveryTicketList = deliveryTicketRepository.findAll().collectList().block();
        assertThat(deliveryTicketList).hasSize(databaseSizeBeforeUpdate);
        DeliveryTicket testDeliveryTicket = deliveryTicketList.get(deliveryTicketList.size() - 1);
        assertThat(testDeliveryTicket.getDeliveryTicketNo()).isEqualTo(UPDATED_DELIVERY_TICKET_NO);
        assertThat(testDeliveryTicket.getSoId()).isEqualTo(DEFAULT_SO_ID);
        assertThat(testDeliveryTicket.getSoNo()).isEqualTo(UPDATED_SO_NO);
        assertThat(testDeliveryTicket.getPatientFirstName()).isEqualTo(UPDATED_PATIENT_FIRST_NAME);
        assertThat(testDeliveryTicket.getPatientMiddleName()).isEqualTo(DEFAULT_PATIENT_MIDDLE_NAME);
        assertThat(testDeliveryTicket.getPatientLastName()).isEqualTo(UPDATED_PATIENT_LAST_NAME);
        assertThat(testDeliveryTicket.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testDeliveryTicket.getAgeAsOnDate()).isEqualTo(DEFAULT_AGE_AS_ON_DATE);
        assertThat(testDeliveryTicket.getPhone1()).isEqualTo(UPDATED_PHONE_1);
        assertThat(testDeliveryTicket.getPhone2()).isEqualTo(UPDATED_PHONE_2);
        assertThat(testDeliveryTicket.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testDeliveryTicket.getDeliveryAddress1()).isEqualTo(UPDATED_DELIVERY_ADDRESS_1);
        assertThat(testDeliveryTicket.getDeliveryAddress2()).isEqualTo(UPDATED_DELIVERY_ADDRESS_2);
        assertThat(testDeliveryTicket.getDeliveryCity()).isEqualTo(DEFAULT_DELIVERY_CITY);
        assertThat(testDeliveryTicket.getDeliveryState()).isEqualTo(UPDATED_DELIVERY_STATE);
        assertThat(testDeliveryTicket.getDeliveryZip()).isEqualTo(UPDATED_DELIVERY_ZIP);
        assertThat(testDeliveryTicket.getDeliveryStatus()).isEqualTo(UPDATED_DELIVERY_STATUS);
        assertThat(testDeliveryTicket.getDeliveryDate()).isEqualTo(DEFAULT_DELIVERY_DATE);
        assertThat(testDeliveryTicket.getDeliveryPriority()).isEqualTo(UPDATED_DELIVERY_PRIORITY);
        assertThat(testDeliveryTicket.getDeliveryNote()).isEqualTo(UPDATED_DELIVERY_NOTE);
        assertThat(testDeliveryTicket.getDeliveryComment()).isEqualTo(UPDATED_DELIVERY_COMMENT);
        assertThat(testDeliveryTicket.getDeliveryAcceptedBy()).isEqualTo(UPDATED_DELIVERY_ACCEPTED_BY);
        assertThat(testDeliveryTicket.getRelationshipWithPatient()).isEqualTo(UPDATED_RELATIONSHIP_WITH_PATIENT);
        assertThat(testDeliveryTicket.getDeliveryAcceptedByContactNo()).isEqualTo(DEFAULT_DELIVERY_ACCEPTED_BY_CONTACT_NO);
        assertThat(testDeliveryTicket.getPrimaryInsurerName()).isEqualTo(DEFAULT_PRIMARY_INSURER_NAME);
        assertThat(testDeliveryTicket.getPrimaryInsurerPolicyNo()).isEqualTo(DEFAULT_PRIMARY_INSURER_POLICY_NO);
        assertThat(testDeliveryTicket.getPrimaryInsurerPatientIdNumber()).isEqualTo(UPDATED_PRIMARY_INSURER_PATIENT_ID_NUMBER);
        assertThat(testDeliveryTicket.getPatientIdNo()).isEqualTo(DEFAULT_PATIENT_ID_NO);
        assertThat(testDeliveryTicket.getBranchAddressLine1()).isEqualTo(DEFAULT_BRANCH_ADDRESS_LINE_1);
        assertThat(testDeliveryTicket.getBranchAddressLine2()).isEqualTo(DEFAULT_BRANCH_ADDRESS_LINE_2);
        assertThat(testDeliveryTicket.getBranchCity()).isEqualTo(UPDATED_BRANCH_CITY);
        assertThat(testDeliveryTicket.getBranchState()).isEqualTo(DEFAULT_BRANCH_STATE);
        assertThat(testDeliveryTicket.getBranchZipCode()).isEqualTo(DEFAULT_BRANCH_ZIP_CODE);
        assertThat(testDeliveryTicket.getBranchContactNo1()).isEqualTo(DEFAULT_BRANCH_CONTACT_NO_1);
        assertThat(testDeliveryTicket.getBranchContactNo2()).isEqualTo(UPDATED_BRANCH_CONTACT_NO_2);
        assertThat(testDeliveryTicket.getBranchNpi()).isEqualTo(UPDATED_BRANCH_NPI);
        assertThat(testDeliveryTicket.getBranchEin()).isEqualTo(DEFAULT_BRANCH_EIN);
        assertThat(testDeliveryTicket.getBranchFax()).isEqualTo(UPDATED_BRANCH_FAX);
        assertThat(testDeliveryTicket.getOrderingProviderFirstName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testDeliveryTicket.getOrderingProviderMiddleName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_MIDDLE_NAME);
        assertThat(testDeliveryTicket.getOrderingProviderLastName()).isEqualTo(UPDATED_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testDeliveryTicket.getOrderingProviderNpi()).isEqualTo(UPDATED_ORDERING_PROVIDER_NPI);
        assertThat(testDeliveryTicket.getOrderingProviderAddressLine1()).isEqualTo(DEFAULT_ORDERING_PROVIDER_ADDRESS_LINE_1);
        assertThat(testDeliveryTicket.getOrderingProviderAddressLine2()).isEqualTo(UPDATED_ORDERING_PROVIDER_ADDRESS_LINE_2);
        assertThat(testDeliveryTicket.getOrderingProviderCity()).isEqualTo(UPDATED_ORDERING_PROVIDER_CITY);
        assertThat(testDeliveryTicket.getOrderingProviderState()).isEqualTo(UPDATED_ORDERING_PROVIDER_STATE);
        assertThat(testDeliveryTicket.getOrderingProviderZip()).isEqualTo(UPDATED_ORDERING_PROVIDER_ZIP);
        assertThat(testDeliveryTicket.getOrderingProviderPhone1()).isEqualTo(UPDATED_ORDERING_PROVIDER_PHONE_1);
        assertThat(testDeliveryTicket.getOrderingProviderPhone2()).isEqualTo(UPDATED_ORDERING_PROVIDER_PHONE_2);
        assertThat(testDeliveryTicket.getOrderingProviderFax()).isEqualTo(DEFAULT_ORDERING_PROVIDER_FAX);
        assertThat(testDeliveryTicket.getOrderingProviderEmail()).isEqualTo(DEFAULT_ORDERING_PROVIDER_EMAIL);
        assertThat(testDeliveryTicket.getBranchName()).isEqualTo(UPDATED_BRANCH_NAME);
        assertThat(testDeliveryTicket.getPatientBranchId()).isEqualTo(UPDATED_PATIENT_BRANCH_ID);
        assertThat(testDeliveryTicket.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDeliveryTicket.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testDeliveryTicket.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testDeliveryTicket.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testDeliveryTicket.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testDeliveryTicket.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testDeliveryTicket.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testDeliveryTicket.getDeliveryTicketUuid()).isEqualTo(DEFAULT_DELIVERY_TICKET_UUID);
        assertThat(testDeliveryTicket.getBillingAddressLine1()).isEqualTo(DEFAULT_BILLING_ADDRESS_LINE_1);
        assertThat(testDeliveryTicket.getBillingAddressLine2()).isEqualTo(UPDATED_BILLING_ADDRESS_LINE_2);
        assertThat(testDeliveryTicket.getBillingCity()).isEqualTo(UPDATED_BILLING_CITY);
        assertThat(testDeliveryTicket.getBillingState()).isEqualTo(DEFAULT_BILLING_STATE);
        assertThat(testDeliveryTicket.getBillingZip()).isEqualTo(DEFAULT_BILLING_ZIP);
        assertThat(testDeliveryTicket.getInventoryLocationId()).isEqualTo(DEFAULT_INVENTORY_LOCATION_ID);
        assertThat(testDeliveryTicket.getInventoryLocationName()).isEqualTo(UPDATED_INVENTORY_LOCATION_NAME);
        assertThat(testDeliveryTicket.getDeliveryTicketDocumentId()).isEqualTo(DEFAULT_DELIVERY_TICKET_DOCUMENT_ID);
        assertThat(testDeliveryTicket.getDeliveryTicketDocumentNo()).isEqualTo(UPDATED_DELIVERY_TICKET_DOCUMENT_NO);
        assertThat(testDeliveryTicket.getDeliveryTicketDocumentName()).isEqualTo(DEFAULT_DELIVERY_TICKET_DOCUMENT_NAME);
        assertThat(testDeliveryTicket.getDeliveryType()).isEqualTo(UPDATED_DELIVERY_TYPE);
        assertThat(testDeliveryTicket.getCarrierName()).isEqualTo(UPDATED_CARRIER_NAME);
        assertThat(testDeliveryTicket.getShippingDate()).isEqualTo(DEFAULT_SHIPPING_DATE);
        assertThat(testDeliveryTicket.getTrackingNo()).isEqualTo(DEFAULT_TRACKING_NO);
        assertThat(testDeliveryTicket.getReferenceNo()).isEqualTo(UPDATED_REFERENCE_NO);
        assertThat(testDeliveryTicket.getPackageWeight()).isEqualTo(DEFAULT_PACKAGE_WEIGHT);
        assertThat(testDeliveryTicket.getSetupMethod()).isEqualTo(UPDATED_SETUP_METHOD);
        assertThat(testDeliveryTicket.getSetupTechnicianNo()).isEqualTo(UPDATED_SETUP_TECHNICIAN_NO);
        assertThat(testDeliveryTicket.getSetupTechnicianContactNo()).isEqualTo(UPDATED_SETUP_TECHNICIAN_CONTACT_NO);
        assertThat(testDeliveryTicket.getSetupTechnicianFirstName()).isEqualTo(DEFAULT_SETUP_TECHNICIAN_FIRST_NAME);
        assertThat(testDeliveryTicket.getSetupTechnicianMiddleName()).isEqualTo(DEFAULT_SETUP_TECHNICIAN_MIDDLE_NAME);
        assertThat(testDeliveryTicket.getSetupTechnicianLastName()).isEqualTo(UPDATED_SETUP_TECHNICIAN_LAST_NAME);
        assertThat(testDeliveryTicket.getSetupDateTime()).isEqualTo(UPDATED_SETUP_DATE_TIME);
        assertThat(testDeliveryTicket.getScheduleSetupDateTime()).isEqualTo(UPDATED_SCHEDULE_SETUP_DATE_TIME);
        assertThat(testDeliveryTicket.getSetupComments()).isEqualTo(UPDATED_SETUP_COMMENTS);
        assertThat(testDeliveryTicket.getSetupStatus()).isEqualTo(UPDATED_SETUP_STATUS);
        assertThat(testDeliveryTicket.getCourierPackageAcceptedBy()).isEqualTo(DEFAULT_COURIER_PACKAGE_ACCEPTED_BY);
        assertThat(testDeliveryTicket.getTherapistFirstName()).isEqualTo(DEFAULT_THERAPIST_FIRST_NAME);
        assertThat(testDeliveryTicket.getTherapistMiddleName()).isEqualTo(UPDATED_THERAPIST_MIDDLE_NAME);
        assertThat(testDeliveryTicket.getTherapistLastName()).isEqualTo(UPDATED_THERAPIST_LAST_NAME);
        assertThat(testDeliveryTicket.getTherapistLicenseNo()).isEqualTo(DEFAULT_THERAPIST_LICENSE_NO);
        assertThat(testDeliveryTicket.getTherapistNpi()).isEqualTo(DEFAULT_THERAPIST_NPI);
        assertThat(testDeliveryTicket.getTherapistTaxonomyCode()).isEqualTo(DEFAULT_THERAPIST_TAXONOMY_CODE);
        assertThat(testDeliveryTicket.getScheduleTherapyDate()).isEqualTo(DEFAULT_SCHEDULE_THERAPY_DATE);
        assertThat(testDeliveryTicket.getActualTherapyDate()).isEqualTo(DEFAULT_ACTUAL_THERAPY_DATE);
        assertThat(testDeliveryTicket.getTherapyMode()).isEqualTo(DEFAULT_THERAPY_MODE);
        assertThat(testDeliveryTicket.getTherapyStatus()).isEqualTo(DEFAULT_THERAPY_STATUS);
        assertThat(testDeliveryTicket.getTherapyNotes()).isEqualTo(DEFAULT_THERAPY_NOTES);
    }

    @Test
    void fullUpdateDeliveryTicketWithPatch() throws Exception {
        // Initialize the database
        deliveryTicketRepository.save(deliveryTicket).block();

        int databaseSizeBeforeUpdate = deliveryTicketRepository.findAll().collectList().block().size();

        // Update the deliveryTicket using partial update
        DeliveryTicket partialUpdatedDeliveryTicket = new DeliveryTicket();
        partialUpdatedDeliveryTicket.setDeliveryTicketId(deliveryTicket.getDeliveryTicketId());

        partialUpdatedDeliveryTicket
            .deliveryTicketNo(UPDATED_DELIVERY_TICKET_NO)
            .soId(UPDATED_SO_ID)
            .soNo(UPDATED_SO_NO)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientMiddleName(UPDATED_PATIENT_MIDDLE_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .gender(UPDATED_GENDER)
            .ageAsOnDate(UPDATED_AGE_AS_ON_DATE)
            .phone1(UPDATED_PHONE_1)
            .phone2(UPDATED_PHONE_2)
            .email(UPDATED_EMAIL)
            .deliveryAddress1(UPDATED_DELIVERY_ADDRESS_1)
            .deliveryAddress2(UPDATED_DELIVERY_ADDRESS_2)
            .deliveryCity(UPDATED_DELIVERY_CITY)
            .deliveryState(UPDATED_DELIVERY_STATE)
            .deliveryZip(UPDATED_DELIVERY_ZIP)
            .deliveryStatus(UPDATED_DELIVERY_STATUS)
            .deliveryDate(UPDATED_DELIVERY_DATE)
            .deliveryPriority(UPDATED_DELIVERY_PRIORITY)
            .deliveryNote(UPDATED_DELIVERY_NOTE)
            .deliveryComment(UPDATED_DELIVERY_COMMENT)
            .deliveryAcceptedBy(UPDATED_DELIVERY_ACCEPTED_BY)
            .relationshipWithPatient(UPDATED_RELATIONSHIP_WITH_PATIENT)
            .deliveryAcceptedByContactNo(UPDATED_DELIVERY_ACCEPTED_BY_CONTACT_NO)
            .primaryInsurerName(UPDATED_PRIMARY_INSURER_NAME)
            .primaryInsurerPolicyNo(UPDATED_PRIMARY_INSURER_POLICY_NO)
            .primaryInsurerPatientIdNumber(UPDATED_PRIMARY_INSURER_PATIENT_ID_NUMBER)
            .patientIdNo(UPDATED_PATIENT_ID_NO)
            .branchAddressLine1(UPDATED_BRANCH_ADDRESS_LINE_1)
            .branchAddressLine2(UPDATED_BRANCH_ADDRESS_LINE_2)
            .branchCity(UPDATED_BRANCH_CITY)
            .branchState(UPDATED_BRANCH_STATE)
            .branchZipCode(UPDATED_BRANCH_ZIP_CODE)
            .branchContactNo1(UPDATED_BRANCH_CONTACT_NO_1)
            .branchContactNo2(UPDATED_BRANCH_CONTACT_NO_2)
            .branchNpi(UPDATED_BRANCH_NPI)
            .branchEin(UPDATED_BRANCH_EIN)
            .branchFax(UPDATED_BRANCH_FAX)
            .orderingProviderFirstName(UPDATED_ORDERING_PROVIDER_FIRST_NAME)
            .orderingProviderMiddleName(UPDATED_ORDERING_PROVIDER_MIDDLE_NAME)
            .orderingProviderLastName(UPDATED_ORDERING_PROVIDER_LAST_NAME)
            .orderingProviderNpi(UPDATED_ORDERING_PROVIDER_NPI)
            .orderingProviderAddressLine1(UPDATED_ORDERING_PROVIDER_ADDRESS_LINE_1)
            .orderingProviderAddressLine2(UPDATED_ORDERING_PROVIDER_ADDRESS_LINE_2)
            .orderingProviderCity(UPDATED_ORDERING_PROVIDER_CITY)
            .orderingProviderState(UPDATED_ORDERING_PROVIDER_STATE)
            .orderingProviderZip(UPDATED_ORDERING_PROVIDER_ZIP)
            .orderingProviderPhone1(UPDATED_ORDERING_PROVIDER_PHONE_1)
            .orderingProviderPhone2(UPDATED_ORDERING_PROVIDER_PHONE_2)
            .orderingProviderFax(UPDATED_ORDERING_PROVIDER_FAX)
            .orderingProviderEmail(UPDATED_ORDERING_PROVIDER_EMAIL)
            .branchName(UPDATED_BRANCH_NAME)
            .patientBranchId(UPDATED_PATIENT_BRANCH_ID)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .deliveryTicketUuid(UPDATED_DELIVERY_TICKET_UUID)
            .billingAddressLine1(UPDATED_BILLING_ADDRESS_LINE_1)
            .billingAddressLine2(UPDATED_BILLING_ADDRESS_LINE_2)
            .billingCity(UPDATED_BILLING_CITY)
            .billingState(UPDATED_BILLING_STATE)
            .billingZip(UPDATED_BILLING_ZIP)
            .inventoryLocationId(UPDATED_INVENTORY_LOCATION_ID)
            .inventoryLocationName(UPDATED_INVENTORY_LOCATION_NAME)
            .deliveryTicketDocumentId(UPDATED_DELIVERY_TICKET_DOCUMENT_ID)
            .deliveryTicketDocumentNo(UPDATED_DELIVERY_TICKET_DOCUMENT_NO)
            .deliveryTicketDocumentName(UPDATED_DELIVERY_TICKET_DOCUMENT_NAME)
            .deliveryType(UPDATED_DELIVERY_TYPE)
            .carrierName(UPDATED_CARRIER_NAME)
            .shippingDate(UPDATED_SHIPPING_DATE)
            .trackingNo(UPDATED_TRACKING_NO)
            .referenceNo(UPDATED_REFERENCE_NO)
            .packageWeight(UPDATED_PACKAGE_WEIGHT)
            .setupMethod(UPDATED_SETUP_METHOD)
            .setupTechnicianNo(UPDATED_SETUP_TECHNICIAN_NO)
            .setupTechnicianContactNo(UPDATED_SETUP_TECHNICIAN_CONTACT_NO)
            .setupTechnicianFirstName(UPDATED_SETUP_TECHNICIAN_FIRST_NAME)
            .setupTechnicianMiddleName(UPDATED_SETUP_TECHNICIAN_MIDDLE_NAME)
            .setupTechnicianLastName(UPDATED_SETUP_TECHNICIAN_LAST_NAME)
            .setupDateTime(UPDATED_SETUP_DATE_TIME)
            .scheduleSetupDateTime(UPDATED_SCHEDULE_SETUP_DATE_TIME)
            .setupComments(UPDATED_SETUP_COMMENTS)
            .setupStatus(UPDATED_SETUP_STATUS)
            .courierPackageAcceptedBy(UPDATED_COURIER_PACKAGE_ACCEPTED_BY)
            .therapistFirstName(UPDATED_THERAPIST_FIRST_NAME)
            .therapistMiddleName(UPDATED_THERAPIST_MIDDLE_NAME)
            .therapistLastName(UPDATED_THERAPIST_LAST_NAME)
            .therapistLicenseNo(UPDATED_THERAPIST_LICENSE_NO)
            .therapistNpi(UPDATED_THERAPIST_NPI)
            .therapistTaxonomyCode(UPDATED_THERAPIST_TAXONOMY_CODE)
            .scheduleTherapyDate(UPDATED_SCHEDULE_THERAPY_DATE)
            .actualTherapyDate(UPDATED_ACTUAL_THERAPY_DATE)
            .therapyMode(UPDATED_THERAPY_MODE)
            .therapyStatus(UPDATED_THERAPY_STATUS)
            .therapyNotes(UPDATED_THERAPY_NOTES);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedDeliveryTicket.getDeliveryTicketId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedDeliveryTicket))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the DeliveryTicket in the database
        List<DeliveryTicket> deliveryTicketList = deliveryTicketRepository.findAll().collectList().block();
        assertThat(deliveryTicketList).hasSize(databaseSizeBeforeUpdate);
        DeliveryTicket testDeliveryTicket = deliveryTicketList.get(deliveryTicketList.size() - 1);
        assertThat(testDeliveryTicket.getDeliveryTicketNo()).isEqualTo(UPDATED_DELIVERY_TICKET_NO);
        assertThat(testDeliveryTicket.getSoId()).isEqualTo(UPDATED_SO_ID);
        assertThat(testDeliveryTicket.getSoNo()).isEqualTo(UPDATED_SO_NO);
        assertThat(testDeliveryTicket.getPatientFirstName()).isEqualTo(UPDATED_PATIENT_FIRST_NAME);
        assertThat(testDeliveryTicket.getPatientMiddleName()).isEqualTo(UPDATED_PATIENT_MIDDLE_NAME);
        assertThat(testDeliveryTicket.getPatientLastName()).isEqualTo(UPDATED_PATIENT_LAST_NAME);
        assertThat(testDeliveryTicket.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testDeliveryTicket.getAgeAsOnDate()).isEqualTo(UPDATED_AGE_AS_ON_DATE);
        assertThat(testDeliveryTicket.getPhone1()).isEqualTo(UPDATED_PHONE_1);
        assertThat(testDeliveryTicket.getPhone2()).isEqualTo(UPDATED_PHONE_2);
        assertThat(testDeliveryTicket.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testDeliveryTicket.getDeliveryAddress1()).isEqualTo(UPDATED_DELIVERY_ADDRESS_1);
        assertThat(testDeliveryTicket.getDeliveryAddress2()).isEqualTo(UPDATED_DELIVERY_ADDRESS_2);
        assertThat(testDeliveryTicket.getDeliveryCity()).isEqualTo(UPDATED_DELIVERY_CITY);
        assertThat(testDeliveryTicket.getDeliveryState()).isEqualTo(UPDATED_DELIVERY_STATE);
        assertThat(testDeliveryTicket.getDeliveryZip()).isEqualTo(UPDATED_DELIVERY_ZIP);
        assertThat(testDeliveryTicket.getDeliveryStatus()).isEqualTo(UPDATED_DELIVERY_STATUS);
        assertThat(testDeliveryTicket.getDeliveryDate()).isEqualTo(UPDATED_DELIVERY_DATE);
        assertThat(testDeliveryTicket.getDeliveryPriority()).isEqualTo(UPDATED_DELIVERY_PRIORITY);
        assertThat(testDeliveryTicket.getDeliveryNote()).isEqualTo(UPDATED_DELIVERY_NOTE);
        assertThat(testDeliveryTicket.getDeliveryComment()).isEqualTo(UPDATED_DELIVERY_COMMENT);
        assertThat(testDeliveryTicket.getDeliveryAcceptedBy()).isEqualTo(UPDATED_DELIVERY_ACCEPTED_BY);
        assertThat(testDeliveryTicket.getRelationshipWithPatient()).isEqualTo(UPDATED_RELATIONSHIP_WITH_PATIENT);
        assertThat(testDeliveryTicket.getDeliveryAcceptedByContactNo()).isEqualTo(UPDATED_DELIVERY_ACCEPTED_BY_CONTACT_NO);
        assertThat(testDeliveryTicket.getPrimaryInsurerName()).isEqualTo(UPDATED_PRIMARY_INSURER_NAME);
        assertThat(testDeliveryTicket.getPrimaryInsurerPolicyNo()).isEqualTo(UPDATED_PRIMARY_INSURER_POLICY_NO);
        assertThat(testDeliveryTicket.getPrimaryInsurerPatientIdNumber()).isEqualTo(UPDATED_PRIMARY_INSURER_PATIENT_ID_NUMBER);
        assertThat(testDeliveryTicket.getPatientIdNo()).isEqualTo(UPDATED_PATIENT_ID_NO);
        assertThat(testDeliveryTicket.getBranchAddressLine1()).isEqualTo(UPDATED_BRANCH_ADDRESS_LINE_1);
        assertThat(testDeliveryTicket.getBranchAddressLine2()).isEqualTo(UPDATED_BRANCH_ADDRESS_LINE_2);
        assertThat(testDeliveryTicket.getBranchCity()).isEqualTo(UPDATED_BRANCH_CITY);
        assertThat(testDeliveryTicket.getBranchState()).isEqualTo(UPDATED_BRANCH_STATE);
        assertThat(testDeliveryTicket.getBranchZipCode()).isEqualTo(UPDATED_BRANCH_ZIP_CODE);
        assertThat(testDeliveryTicket.getBranchContactNo1()).isEqualTo(UPDATED_BRANCH_CONTACT_NO_1);
        assertThat(testDeliveryTicket.getBranchContactNo2()).isEqualTo(UPDATED_BRANCH_CONTACT_NO_2);
        assertThat(testDeliveryTicket.getBranchNpi()).isEqualTo(UPDATED_BRANCH_NPI);
        assertThat(testDeliveryTicket.getBranchEin()).isEqualTo(UPDATED_BRANCH_EIN);
        assertThat(testDeliveryTicket.getBranchFax()).isEqualTo(UPDATED_BRANCH_FAX);
        assertThat(testDeliveryTicket.getOrderingProviderFirstName()).isEqualTo(UPDATED_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testDeliveryTicket.getOrderingProviderMiddleName()).isEqualTo(UPDATED_ORDERING_PROVIDER_MIDDLE_NAME);
        assertThat(testDeliveryTicket.getOrderingProviderLastName()).isEqualTo(UPDATED_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testDeliveryTicket.getOrderingProviderNpi()).isEqualTo(UPDATED_ORDERING_PROVIDER_NPI);
        assertThat(testDeliveryTicket.getOrderingProviderAddressLine1()).isEqualTo(UPDATED_ORDERING_PROVIDER_ADDRESS_LINE_1);
        assertThat(testDeliveryTicket.getOrderingProviderAddressLine2()).isEqualTo(UPDATED_ORDERING_PROVIDER_ADDRESS_LINE_2);
        assertThat(testDeliveryTicket.getOrderingProviderCity()).isEqualTo(UPDATED_ORDERING_PROVIDER_CITY);
        assertThat(testDeliveryTicket.getOrderingProviderState()).isEqualTo(UPDATED_ORDERING_PROVIDER_STATE);
        assertThat(testDeliveryTicket.getOrderingProviderZip()).isEqualTo(UPDATED_ORDERING_PROVIDER_ZIP);
        assertThat(testDeliveryTicket.getOrderingProviderPhone1()).isEqualTo(UPDATED_ORDERING_PROVIDER_PHONE_1);
        assertThat(testDeliveryTicket.getOrderingProviderPhone2()).isEqualTo(UPDATED_ORDERING_PROVIDER_PHONE_2);
        assertThat(testDeliveryTicket.getOrderingProviderFax()).isEqualTo(UPDATED_ORDERING_PROVIDER_FAX);
        assertThat(testDeliveryTicket.getOrderingProviderEmail()).isEqualTo(UPDATED_ORDERING_PROVIDER_EMAIL);
        assertThat(testDeliveryTicket.getBranchName()).isEqualTo(UPDATED_BRANCH_NAME);
        assertThat(testDeliveryTicket.getPatientBranchId()).isEqualTo(UPDATED_PATIENT_BRANCH_ID);
        assertThat(testDeliveryTicket.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDeliveryTicket.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testDeliveryTicket.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testDeliveryTicket.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testDeliveryTicket.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testDeliveryTicket.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testDeliveryTicket.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testDeliveryTicket.getDeliveryTicketUuid()).isEqualTo(UPDATED_DELIVERY_TICKET_UUID);
        assertThat(testDeliveryTicket.getBillingAddressLine1()).isEqualTo(UPDATED_BILLING_ADDRESS_LINE_1);
        assertThat(testDeliveryTicket.getBillingAddressLine2()).isEqualTo(UPDATED_BILLING_ADDRESS_LINE_2);
        assertThat(testDeliveryTicket.getBillingCity()).isEqualTo(UPDATED_BILLING_CITY);
        assertThat(testDeliveryTicket.getBillingState()).isEqualTo(UPDATED_BILLING_STATE);
        assertThat(testDeliveryTicket.getBillingZip()).isEqualTo(UPDATED_BILLING_ZIP);
        assertThat(testDeliveryTicket.getInventoryLocationId()).isEqualTo(UPDATED_INVENTORY_LOCATION_ID);
        assertThat(testDeliveryTicket.getInventoryLocationName()).isEqualTo(UPDATED_INVENTORY_LOCATION_NAME);
        assertThat(testDeliveryTicket.getDeliveryTicketDocumentId()).isEqualTo(UPDATED_DELIVERY_TICKET_DOCUMENT_ID);
        assertThat(testDeliveryTicket.getDeliveryTicketDocumentNo()).isEqualTo(UPDATED_DELIVERY_TICKET_DOCUMENT_NO);
        assertThat(testDeliveryTicket.getDeliveryTicketDocumentName()).isEqualTo(UPDATED_DELIVERY_TICKET_DOCUMENT_NAME);
        assertThat(testDeliveryTicket.getDeliveryType()).isEqualTo(UPDATED_DELIVERY_TYPE);
        assertThat(testDeliveryTicket.getCarrierName()).isEqualTo(UPDATED_CARRIER_NAME);
        assertThat(testDeliveryTicket.getShippingDate()).isEqualTo(UPDATED_SHIPPING_DATE);
        assertThat(testDeliveryTicket.getTrackingNo()).isEqualTo(UPDATED_TRACKING_NO);
        assertThat(testDeliveryTicket.getReferenceNo()).isEqualTo(UPDATED_REFERENCE_NO);
        assertThat(testDeliveryTicket.getPackageWeight()).isEqualTo(UPDATED_PACKAGE_WEIGHT);
        assertThat(testDeliveryTicket.getSetupMethod()).isEqualTo(UPDATED_SETUP_METHOD);
        assertThat(testDeliveryTicket.getSetupTechnicianNo()).isEqualTo(UPDATED_SETUP_TECHNICIAN_NO);
        assertThat(testDeliveryTicket.getSetupTechnicianContactNo()).isEqualTo(UPDATED_SETUP_TECHNICIAN_CONTACT_NO);
        assertThat(testDeliveryTicket.getSetupTechnicianFirstName()).isEqualTo(UPDATED_SETUP_TECHNICIAN_FIRST_NAME);
        assertThat(testDeliveryTicket.getSetupTechnicianMiddleName()).isEqualTo(UPDATED_SETUP_TECHNICIAN_MIDDLE_NAME);
        assertThat(testDeliveryTicket.getSetupTechnicianLastName()).isEqualTo(UPDATED_SETUP_TECHNICIAN_LAST_NAME);
        assertThat(testDeliveryTicket.getSetupDateTime()).isEqualTo(UPDATED_SETUP_DATE_TIME);
        assertThat(testDeliveryTicket.getScheduleSetupDateTime()).isEqualTo(UPDATED_SCHEDULE_SETUP_DATE_TIME);
        assertThat(testDeliveryTicket.getSetupComments()).isEqualTo(UPDATED_SETUP_COMMENTS);
        assertThat(testDeliveryTicket.getSetupStatus()).isEqualTo(UPDATED_SETUP_STATUS);
        assertThat(testDeliveryTicket.getCourierPackageAcceptedBy()).isEqualTo(UPDATED_COURIER_PACKAGE_ACCEPTED_BY);
        assertThat(testDeliveryTicket.getTherapistFirstName()).isEqualTo(UPDATED_THERAPIST_FIRST_NAME);
        assertThat(testDeliveryTicket.getTherapistMiddleName()).isEqualTo(UPDATED_THERAPIST_MIDDLE_NAME);
        assertThat(testDeliveryTicket.getTherapistLastName()).isEqualTo(UPDATED_THERAPIST_LAST_NAME);
        assertThat(testDeliveryTicket.getTherapistLicenseNo()).isEqualTo(UPDATED_THERAPIST_LICENSE_NO);
        assertThat(testDeliveryTicket.getTherapistNpi()).isEqualTo(UPDATED_THERAPIST_NPI);
        assertThat(testDeliveryTicket.getTherapistTaxonomyCode()).isEqualTo(UPDATED_THERAPIST_TAXONOMY_CODE);
        assertThat(testDeliveryTicket.getScheduleTherapyDate()).isEqualTo(UPDATED_SCHEDULE_THERAPY_DATE);
        assertThat(testDeliveryTicket.getActualTherapyDate()).isEqualTo(UPDATED_ACTUAL_THERAPY_DATE);
        assertThat(testDeliveryTicket.getTherapyMode()).isEqualTo(UPDATED_THERAPY_MODE);
        assertThat(testDeliveryTicket.getTherapyStatus()).isEqualTo(UPDATED_THERAPY_STATUS);
        assertThat(testDeliveryTicket.getTherapyNotes()).isEqualTo(UPDATED_THERAPY_NOTES);
    }

    @Test
    void patchNonExistingDeliveryTicket() throws Exception {
        int databaseSizeBeforeUpdate = deliveryTicketRepository.findAll().collectList().block().size();
        deliveryTicket.setDeliveryTicketId(count.incrementAndGet());

        // Create the DeliveryTicket
        DeliveryTicketDTO deliveryTicketDTO = deliveryTicketMapper.toDto(deliveryTicket);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, deliveryTicketDTO.getDeliveryTicketId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(deliveryTicketDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DeliveryTicket in the database
        List<DeliveryTicket> deliveryTicketList = deliveryTicketRepository.findAll().collectList().block();
        assertThat(deliveryTicketList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchDeliveryTicket() throws Exception {
        int databaseSizeBeforeUpdate = deliveryTicketRepository.findAll().collectList().block().size();
        deliveryTicket.setDeliveryTicketId(count.incrementAndGet());

        // Create the DeliveryTicket
        DeliveryTicketDTO deliveryTicketDTO = deliveryTicketMapper.toDto(deliveryTicket);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(deliveryTicketDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DeliveryTicket in the database
        List<DeliveryTicket> deliveryTicketList = deliveryTicketRepository.findAll().collectList().block();
        assertThat(deliveryTicketList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamDeliveryTicket() throws Exception {
        int databaseSizeBeforeUpdate = deliveryTicketRepository.findAll().collectList().block().size();
        deliveryTicket.setDeliveryTicketId(count.incrementAndGet());

        // Create the DeliveryTicket
        DeliveryTicketDTO deliveryTicketDTO = deliveryTicketMapper.toDto(deliveryTicket);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(deliveryTicketDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the DeliveryTicket in the database
        List<DeliveryTicket> deliveryTicketList = deliveryTicketRepository.findAll().collectList().block();
        assertThat(deliveryTicketList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteDeliveryTicket() {
        // Initialize the database
        deliveryTicketRepository.save(deliveryTicket).block();

        int databaseSizeBeforeDelete = deliveryTicketRepository.findAll().collectList().block().size();

        // Delete the deliveryTicket
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, deliveryTicket.getDeliveryTicketId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<DeliveryTicket> deliveryTicketList = deliveryTicketRepository.findAll().collectList().block();
        assertThat(deliveryTicketList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
