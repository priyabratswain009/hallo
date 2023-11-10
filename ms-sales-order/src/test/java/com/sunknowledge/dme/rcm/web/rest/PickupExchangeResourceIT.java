package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.PickupExchange;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.PickupExchangeRepository;
import com.sunknowledge.dme.rcm.service.dto.PickupExchangeDTO;
import com.sunknowledge.dme.rcm.service.mapper.PickupExchangeMapper;
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
 * Integration tests for the {@link PickupExchangeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class PickupExchangeResourceIT {

    private static final String DEFAULT_PICKUP_EXCHANGE_NO = "AAAAAAAAAA";
    private static final String UPDATED_PICKUP_EXCHANGE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PICKUP_EXCHANGE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_PICKUP_EXCHANGE_TYPE = "BBBBBBBBBB";

    private static final Long DEFAULT_SO_ID = 1L;
    private static final Long UPDATED_SO_ID = 2L;

    private static final String DEFAULT_SO_NO = "AAAAAAAAAA";
    private static final String UPDATED_SO_NO = "BBBBBBBBBB";

    private static final Long DEFAULT_BRANCH_ID = 1L;
    private static final Long UPDATED_BRANCH_ID = 2L;

    private static final String DEFAULT_BRANCH_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_INVENTORY_LOCATION_ID = 1L;
    private static final Long UPDATED_INVENTORY_LOCATION_ID = 2L;

    private static final String DEFAULT_INVENTORY_LOCATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INVENTORY_LOCATION_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_PATIENT_ID = 1L;
    private static final Long UPDATED_PATIENT_ID = 2L;

    private static final String DEFAULT_PATIENT_ID_NO = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_ID_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_CONTACT_1 = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_CONTACT_1 = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_CONTACT_2 = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_CONTACT_2 = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_BILLING_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_BILLING_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_BILLING_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_BILLING_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_BILLING_ADDRESS_STATE = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_BILLING_ADDRESS_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_BILLING_ADDRESS_CITY = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_BILLING_ADDRESS_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_BILLING_ADDRESS_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_BILLING_ADDRESS_ZIP = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_DELIVEY_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_DELIVEY_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_DELIVEY_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_DELIVEY_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_DELIVEY_ADDRESS_STATE = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_DELIVEY_ADDRESS_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_DELIVEY_ADDRESS_CITY = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_DELIVEY_ADDRESS_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_DELIVEY_ADDRESS_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_DELIVEY_ADDRESS_ZIP = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PICKUP_EXCHANGE_SCHEDULE_DATE_TIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PICKUP_EXCHANGE_SCHEDULE_DATE_TIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_PICKUP_EXCHANGE_ACTUAL_DATE_TIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PICKUP_EXCHANGE_ACTUAL_DATE_TIME = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_PICKUP_EXCHANGE_REASON = "AAAAAAAAAA";
    private static final String UPDATED_PICKUP_EXCHANGE_REASON = "BBBBBBBBBB";

    private static final String DEFAULT_PICKUP_EXCHANGE_REQUEST = "AAAAAAAAAA";
    private static final String UPDATED_PICKUP_EXCHANGE_REQUEST = "BBBBBBBBBB";

    private static final String DEFAULT_PICKUP_EXCHANGE_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_PICKUP_EXCHANGE_NOTE = "BBBBBBBBBB";

    private static final String DEFAULT_PICKUP_EXCHANGE_AGENT_ID_NO = "AAAAAAAAAA";
    private static final String UPDATED_PICKUP_EXCHANGE_AGENT_ID_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PICKUP_EXCHANGE_AGENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PICKUP_EXCHANGE_AGENT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PICKUP_EXCHANGE_DOCUMENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_PICKUP_EXCHANGE_DOCUMENT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PICKUP_EXCHANGE_DOCUMENT_NO = "AAAAAAAAAA";
    private static final String UPDATED_PICKUP_EXCHANGE_DOCUMENT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PICKUP_EXCHANGE_DOCUMENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PICKUP_EXCHANGE_DOCUMENT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PICKUP_EXCHANGE_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_PICKUP_EXCHANGE_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_PICKUP_EXCHANGE_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_PICKUP_EXCHANGE_COMMENTS = "BBBBBBBBBB";

    private static final String DEFAULT_IS_PATIENT_SIGNED = "AAAAAAAAAA";
    private static final String UPDATED_IS_PATIENT_SIGNED = "BBBBBBBBBB";

    private static final String DEFAULT_RELATIONSHIP_WITH_PATIENT = "AAAAAAAAAA";
    private static final String UPDATED_RELATIONSHIP_WITH_PATIENT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PATIENT_SIGNED_DATE_TIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PATIENT_SIGNED_DATE_TIME = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_IS_AGENT_SIGNED = "AAAAAAAAAA";
    private static final String UPDATED_IS_AGENT_SIGNED = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_LAST_BILLING_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LAST_BILLING_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_OF_DEATH = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_OF_DEATH = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_PICKUP_EXCHANGE_SUPPORTING_DOCUMENT_1 = "AAAAAAAAAA";
    private static final String UPDATED_PICKUP_EXCHANGE_SUPPORTING_DOCUMENT_1 = "BBBBBBBBBB";

    private static final String DEFAULT_PICKUP_EXCHANGE_SUPPORTING_DOCUMENT_2 = "AAAAAAAAAA";
    private static final String UPDATED_PICKUP_EXCHANGE_SUPPORTING_DOCUMENT_2 = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_NOTSIGNED_REASON = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_NOTSIGNED_REASON = "BBBBBBBBBB";

    private static final String DEFAULT_PICKUP_EXCHANGE_JSON_DATA = "AAAAAAAAAA";
    private static final String UPDATED_PICKUP_EXCHANGE_JSON_DATA = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final UUID DEFAULT_PICKUP_EXCHANGE_UUID = UUID.randomUUID();
    private static final UUID UPDATED_PICKUP_EXCHANGE_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/pickup-exchanges";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{pickupExchangeId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PickupExchangeRepository pickupExchangeRepository;

    @Autowired
    private PickupExchangeMapper pickupExchangeMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private PickupExchange pickupExchange;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PickupExchange createEntity(EntityManager em) {
        PickupExchange pickupExchange = new PickupExchange()
            .pickupExchangeNo(DEFAULT_PICKUP_EXCHANGE_NO)
            .pickupExchangeType(DEFAULT_PICKUP_EXCHANGE_TYPE)
            .soId(DEFAULT_SO_ID)
            .soNo(DEFAULT_SO_NO)
            .branchId(DEFAULT_BRANCH_ID)
            .branchName(DEFAULT_BRANCH_NAME)
            .inventoryLocationId(DEFAULT_INVENTORY_LOCATION_ID)
            .inventoryLocationName(DEFAULT_INVENTORY_LOCATION_NAME)
            .patientId(DEFAULT_PATIENT_ID)
            .patientIdNo(DEFAULT_PATIENT_ID_NO)
            .patientFirstName(DEFAULT_PATIENT_FIRST_NAME)
            .patientMiddleName(DEFAULT_PATIENT_MIDDLE_NAME)
            .patientLastName(DEFAULT_PATIENT_LAST_NAME)
            .patientContact1(DEFAULT_PATIENT_CONTACT_1)
            .patientContact2(DEFAULT_PATIENT_CONTACT_2)
            .patientBillingAddressLine1(DEFAULT_PATIENT_BILLING_ADDRESS_LINE_1)
            .patientBillingAddressLine2(DEFAULT_PATIENT_BILLING_ADDRESS_LINE_2)
            .patientBillingAddressState(DEFAULT_PATIENT_BILLING_ADDRESS_STATE)
            .patientBillingAddressCity(DEFAULT_PATIENT_BILLING_ADDRESS_CITY)
            .patientBillingAddressZip(DEFAULT_PATIENT_BILLING_ADDRESS_ZIP)
            .patientDeliveyAddressLine1(DEFAULT_PATIENT_DELIVEY_ADDRESS_LINE_1)
            .patientDeliveyAddressLine2(DEFAULT_PATIENT_DELIVEY_ADDRESS_LINE_2)
            .patientDeliveyAddressState(DEFAULT_PATIENT_DELIVEY_ADDRESS_STATE)
            .patientDeliveyAddressCity(DEFAULT_PATIENT_DELIVEY_ADDRESS_CITY)
            .patientDeliveyAddressZip(DEFAULT_PATIENT_DELIVEY_ADDRESS_ZIP)
            .pickupExchangeScheduleDateTime(DEFAULT_PICKUP_EXCHANGE_SCHEDULE_DATE_TIME)
            .pickupExchangeActualDateTime(DEFAULT_PICKUP_EXCHANGE_ACTUAL_DATE_TIME)
            .pickupExchangeReason(DEFAULT_PICKUP_EXCHANGE_REASON)
            .pickupExchangeRequest(DEFAULT_PICKUP_EXCHANGE_REQUEST)
            .pickupExchangeNote(DEFAULT_PICKUP_EXCHANGE_NOTE)
            .pickupExchangeAgentIdNo(DEFAULT_PICKUP_EXCHANGE_AGENT_ID_NO)
            .pickupExchangeAgentName(DEFAULT_PICKUP_EXCHANGE_AGENT_NAME)
            .pickupExchangeDocumentId(DEFAULT_PICKUP_EXCHANGE_DOCUMENT_ID)
            .pickupExchangeDocumentNo(DEFAULT_PICKUP_EXCHANGE_DOCUMENT_NO)
            .pickupExchangeDocumentName(DEFAULT_PICKUP_EXCHANGE_DOCUMENT_NAME)
            .pickupExchangeStatus(DEFAULT_PICKUP_EXCHANGE_STATUS)
            .pickupExchangeComments(DEFAULT_PICKUP_EXCHANGE_COMMENTS)
            .isPatientSigned(DEFAULT_IS_PATIENT_SIGNED)
            .relationshipWithPatient(DEFAULT_RELATIONSHIP_WITH_PATIENT)
            .patientSignedDateTime(DEFAULT_PATIENT_SIGNED_DATE_TIME)
            .isAgentSigned(DEFAULT_IS_AGENT_SIGNED)
            .lastBillingDate(DEFAULT_LAST_BILLING_DATE)
            .dateOfDeath(DEFAULT_DATE_OF_DEATH)
            .pickupExchangeSupportingDocument1(DEFAULT_PICKUP_EXCHANGE_SUPPORTING_DOCUMENT_1)
            .pickupExchangeSupportingDocument2(DEFAULT_PICKUP_EXCHANGE_SUPPORTING_DOCUMENT_2)
            .patientNotsignedReason(DEFAULT_PATIENT_NOTSIGNED_REASON)
            .pickupExchangeJsonData(DEFAULT_PICKUP_EXCHANGE_JSON_DATA)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .pickupExchangeUuid(DEFAULT_PICKUP_EXCHANGE_UUID);
        return pickupExchange;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PickupExchange createUpdatedEntity(EntityManager em) {
        PickupExchange pickupExchange = new PickupExchange()
            .pickupExchangeNo(UPDATED_PICKUP_EXCHANGE_NO)
            .pickupExchangeType(UPDATED_PICKUP_EXCHANGE_TYPE)
            .soId(UPDATED_SO_ID)
            .soNo(UPDATED_SO_NO)
            .branchId(UPDATED_BRANCH_ID)
            .branchName(UPDATED_BRANCH_NAME)
            .inventoryLocationId(UPDATED_INVENTORY_LOCATION_ID)
            .inventoryLocationName(UPDATED_INVENTORY_LOCATION_NAME)
            .patientId(UPDATED_PATIENT_ID)
            .patientIdNo(UPDATED_PATIENT_ID_NO)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientMiddleName(UPDATED_PATIENT_MIDDLE_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .patientContact1(UPDATED_PATIENT_CONTACT_1)
            .patientContact2(UPDATED_PATIENT_CONTACT_2)
            .patientBillingAddressLine1(UPDATED_PATIENT_BILLING_ADDRESS_LINE_1)
            .patientBillingAddressLine2(UPDATED_PATIENT_BILLING_ADDRESS_LINE_2)
            .patientBillingAddressState(UPDATED_PATIENT_BILLING_ADDRESS_STATE)
            .patientBillingAddressCity(UPDATED_PATIENT_BILLING_ADDRESS_CITY)
            .patientBillingAddressZip(UPDATED_PATIENT_BILLING_ADDRESS_ZIP)
            .patientDeliveyAddressLine1(UPDATED_PATIENT_DELIVEY_ADDRESS_LINE_1)
            .patientDeliveyAddressLine2(UPDATED_PATIENT_DELIVEY_ADDRESS_LINE_2)
            .patientDeliveyAddressState(UPDATED_PATIENT_DELIVEY_ADDRESS_STATE)
            .patientDeliveyAddressCity(UPDATED_PATIENT_DELIVEY_ADDRESS_CITY)
            .patientDeliveyAddressZip(UPDATED_PATIENT_DELIVEY_ADDRESS_ZIP)
            .pickupExchangeScheduleDateTime(UPDATED_PICKUP_EXCHANGE_SCHEDULE_DATE_TIME)
            .pickupExchangeActualDateTime(UPDATED_PICKUP_EXCHANGE_ACTUAL_DATE_TIME)
            .pickupExchangeReason(UPDATED_PICKUP_EXCHANGE_REASON)
            .pickupExchangeRequest(UPDATED_PICKUP_EXCHANGE_REQUEST)
            .pickupExchangeNote(UPDATED_PICKUP_EXCHANGE_NOTE)
            .pickupExchangeAgentIdNo(UPDATED_PICKUP_EXCHANGE_AGENT_ID_NO)
            .pickupExchangeAgentName(UPDATED_PICKUP_EXCHANGE_AGENT_NAME)
            .pickupExchangeDocumentId(UPDATED_PICKUP_EXCHANGE_DOCUMENT_ID)
            .pickupExchangeDocumentNo(UPDATED_PICKUP_EXCHANGE_DOCUMENT_NO)
            .pickupExchangeDocumentName(UPDATED_PICKUP_EXCHANGE_DOCUMENT_NAME)
            .pickupExchangeStatus(UPDATED_PICKUP_EXCHANGE_STATUS)
            .pickupExchangeComments(UPDATED_PICKUP_EXCHANGE_COMMENTS)
            .isPatientSigned(UPDATED_IS_PATIENT_SIGNED)
            .relationshipWithPatient(UPDATED_RELATIONSHIP_WITH_PATIENT)
            .patientSignedDateTime(UPDATED_PATIENT_SIGNED_DATE_TIME)
            .isAgentSigned(UPDATED_IS_AGENT_SIGNED)
            .lastBillingDate(UPDATED_LAST_BILLING_DATE)
            .dateOfDeath(UPDATED_DATE_OF_DEATH)
            .pickupExchangeSupportingDocument1(UPDATED_PICKUP_EXCHANGE_SUPPORTING_DOCUMENT_1)
            .pickupExchangeSupportingDocument2(UPDATED_PICKUP_EXCHANGE_SUPPORTING_DOCUMENT_2)
            .patientNotsignedReason(UPDATED_PATIENT_NOTSIGNED_REASON)
            .pickupExchangeJsonData(UPDATED_PICKUP_EXCHANGE_JSON_DATA)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .pickupExchangeUuid(UPDATED_PICKUP_EXCHANGE_UUID);
        return pickupExchange;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(PickupExchange.class).block();
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
        pickupExchange = createEntity(em);
    }

    @Test
    void createPickupExchange() throws Exception {
        int databaseSizeBeforeCreate = pickupExchangeRepository.findAll().collectList().block().size();
        // Create the PickupExchange
        PickupExchangeDTO pickupExchangeDTO = pickupExchangeMapper.toDto(pickupExchange);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(pickupExchangeDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the PickupExchange in the database
        List<PickupExchange> pickupExchangeList = pickupExchangeRepository.findAll().collectList().block();
        assertThat(pickupExchangeList).hasSize(databaseSizeBeforeCreate + 1);
        PickupExchange testPickupExchange = pickupExchangeList.get(pickupExchangeList.size() - 1);
        assertThat(testPickupExchange.getPickupExchangeNo()).isEqualTo(DEFAULT_PICKUP_EXCHANGE_NO);
        assertThat(testPickupExchange.getPickupExchangeType()).isEqualTo(DEFAULT_PICKUP_EXCHANGE_TYPE);
        assertThat(testPickupExchange.getSoId()).isEqualTo(DEFAULT_SO_ID);
        assertThat(testPickupExchange.getSoNo()).isEqualTo(DEFAULT_SO_NO);
        assertThat(testPickupExchange.getBranchId()).isEqualTo(DEFAULT_BRANCH_ID);
        assertThat(testPickupExchange.getBranchName()).isEqualTo(DEFAULT_BRANCH_NAME);
        assertThat(testPickupExchange.getInventoryLocationId()).isEqualTo(DEFAULT_INVENTORY_LOCATION_ID);
        assertThat(testPickupExchange.getInventoryLocationName()).isEqualTo(DEFAULT_INVENTORY_LOCATION_NAME);
        assertThat(testPickupExchange.getPatientId()).isEqualTo(DEFAULT_PATIENT_ID);
        assertThat(testPickupExchange.getPatientIdNo()).isEqualTo(DEFAULT_PATIENT_ID_NO);
        assertThat(testPickupExchange.getPatientFirstName()).isEqualTo(DEFAULT_PATIENT_FIRST_NAME);
        assertThat(testPickupExchange.getPatientMiddleName()).isEqualTo(DEFAULT_PATIENT_MIDDLE_NAME);
        assertThat(testPickupExchange.getPatientLastName()).isEqualTo(DEFAULT_PATIENT_LAST_NAME);
        assertThat(testPickupExchange.getPatientContact1()).isEqualTo(DEFAULT_PATIENT_CONTACT_1);
        assertThat(testPickupExchange.getPatientContact2()).isEqualTo(DEFAULT_PATIENT_CONTACT_2);
        assertThat(testPickupExchange.getPatientBillingAddressLine1()).isEqualTo(DEFAULT_PATIENT_BILLING_ADDRESS_LINE_1);
        assertThat(testPickupExchange.getPatientBillingAddressLine2()).isEqualTo(DEFAULT_PATIENT_BILLING_ADDRESS_LINE_2);
        assertThat(testPickupExchange.getPatientBillingAddressState()).isEqualTo(DEFAULT_PATIENT_BILLING_ADDRESS_STATE);
        assertThat(testPickupExchange.getPatientBillingAddressCity()).isEqualTo(DEFAULT_PATIENT_BILLING_ADDRESS_CITY);
        assertThat(testPickupExchange.getPatientBillingAddressZip()).isEqualTo(DEFAULT_PATIENT_BILLING_ADDRESS_ZIP);
        assertThat(testPickupExchange.getPatientDeliveyAddressLine1()).isEqualTo(DEFAULT_PATIENT_DELIVEY_ADDRESS_LINE_1);
        assertThat(testPickupExchange.getPatientDeliveyAddressLine2()).isEqualTo(DEFAULT_PATIENT_DELIVEY_ADDRESS_LINE_2);
        assertThat(testPickupExchange.getPatientDeliveyAddressState()).isEqualTo(DEFAULT_PATIENT_DELIVEY_ADDRESS_STATE);
        assertThat(testPickupExchange.getPatientDeliveyAddressCity()).isEqualTo(DEFAULT_PATIENT_DELIVEY_ADDRESS_CITY);
        assertThat(testPickupExchange.getPatientDeliveyAddressZip()).isEqualTo(DEFAULT_PATIENT_DELIVEY_ADDRESS_ZIP);
        assertThat(testPickupExchange.getPickupExchangeScheduleDateTime()).isEqualTo(DEFAULT_PICKUP_EXCHANGE_SCHEDULE_DATE_TIME);
        assertThat(testPickupExchange.getPickupExchangeActualDateTime()).isEqualTo(DEFAULT_PICKUP_EXCHANGE_ACTUAL_DATE_TIME);
        assertThat(testPickupExchange.getPickupExchangeReason()).isEqualTo(DEFAULT_PICKUP_EXCHANGE_REASON);
        assertThat(testPickupExchange.getPickupExchangeRequest()).isEqualTo(DEFAULT_PICKUP_EXCHANGE_REQUEST);
        assertThat(testPickupExchange.getPickupExchangeNote()).isEqualTo(DEFAULT_PICKUP_EXCHANGE_NOTE);
        assertThat(testPickupExchange.getPickupExchangeAgentIdNo()).isEqualTo(DEFAULT_PICKUP_EXCHANGE_AGENT_ID_NO);
        assertThat(testPickupExchange.getPickupExchangeAgentName()).isEqualTo(DEFAULT_PICKUP_EXCHANGE_AGENT_NAME);
        assertThat(testPickupExchange.getPickupExchangeDocumentId()).isEqualTo(DEFAULT_PICKUP_EXCHANGE_DOCUMENT_ID);
        assertThat(testPickupExchange.getPickupExchangeDocumentNo()).isEqualTo(DEFAULT_PICKUP_EXCHANGE_DOCUMENT_NO);
        assertThat(testPickupExchange.getPickupExchangeDocumentName()).isEqualTo(DEFAULT_PICKUP_EXCHANGE_DOCUMENT_NAME);
        assertThat(testPickupExchange.getPickupExchangeStatus()).isEqualTo(DEFAULT_PICKUP_EXCHANGE_STATUS);
        assertThat(testPickupExchange.getPickupExchangeComments()).isEqualTo(DEFAULT_PICKUP_EXCHANGE_COMMENTS);
        assertThat(testPickupExchange.getIsPatientSigned()).isEqualTo(DEFAULT_IS_PATIENT_SIGNED);
        assertThat(testPickupExchange.getRelationshipWithPatient()).isEqualTo(DEFAULT_RELATIONSHIP_WITH_PATIENT);
        assertThat(testPickupExchange.getPatientSignedDateTime()).isEqualTo(DEFAULT_PATIENT_SIGNED_DATE_TIME);
        assertThat(testPickupExchange.getIsAgentSigned()).isEqualTo(DEFAULT_IS_AGENT_SIGNED);
        assertThat(testPickupExchange.getLastBillingDate()).isEqualTo(DEFAULT_LAST_BILLING_DATE);
        assertThat(testPickupExchange.getDateOfDeath()).isEqualTo(DEFAULT_DATE_OF_DEATH);
        assertThat(testPickupExchange.getPickupExchangeSupportingDocument1()).isEqualTo(DEFAULT_PICKUP_EXCHANGE_SUPPORTING_DOCUMENT_1);
        assertThat(testPickupExchange.getPickupExchangeSupportingDocument2()).isEqualTo(DEFAULT_PICKUP_EXCHANGE_SUPPORTING_DOCUMENT_2);
        assertThat(testPickupExchange.getPatientNotsignedReason()).isEqualTo(DEFAULT_PATIENT_NOTSIGNED_REASON);
        assertThat(testPickupExchange.getPickupExchangeJsonData()).isEqualTo(DEFAULT_PICKUP_EXCHANGE_JSON_DATA);
        assertThat(testPickupExchange.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPickupExchange.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testPickupExchange.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testPickupExchange.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testPickupExchange.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testPickupExchange.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testPickupExchange.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testPickupExchange.getPickupExchangeUuid()).isEqualTo(DEFAULT_PICKUP_EXCHANGE_UUID);
    }

    @Test
    void createPickupExchangeWithExistingId() throws Exception {
        // Create the PickupExchange with an existing ID
        pickupExchange.setPickupExchangeId(1L);
        PickupExchangeDTO pickupExchangeDTO = pickupExchangeMapper.toDto(pickupExchange);

        int databaseSizeBeforeCreate = pickupExchangeRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(pickupExchangeDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PickupExchange in the database
        List<PickupExchange> pickupExchangeList = pickupExchangeRepository.findAll().collectList().block();
        assertThat(pickupExchangeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllPickupExchanges() {
        // Initialize the database
        pickupExchangeRepository.save(pickupExchange).block();

        // Get all the pickupExchangeList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=pickupExchangeId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].pickupExchangeId")
            .value(hasItem(pickupExchange.getPickupExchangeId().intValue()))
            .jsonPath("$.[*].pickupExchangeNo")
            .value(hasItem(DEFAULT_PICKUP_EXCHANGE_NO))
            .jsonPath("$.[*].pickupExchangeType")
            .value(hasItem(DEFAULT_PICKUP_EXCHANGE_TYPE))
            .jsonPath("$.[*].soId")
            .value(hasItem(DEFAULT_SO_ID.intValue()))
            .jsonPath("$.[*].soNo")
            .value(hasItem(DEFAULT_SO_NO))
            .jsonPath("$.[*].branchId")
            .value(hasItem(DEFAULT_BRANCH_ID.intValue()))
            .jsonPath("$.[*].branchName")
            .value(hasItem(DEFAULT_BRANCH_NAME))
            .jsonPath("$.[*].inventoryLocationId")
            .value(hasItem(DEFAULT_INVENTORY_LOCATION_ID.intValue()))
            .jsonPath("$.[*].inventoryLocationName")
            .value(hasItem(DEFAULT_INVENTORY_LOCATION_NAME))
            .jsonPath("$.[*].patientId")
            .value(hasItem(DEFAULT_PATIENT_ID.intValue()))
            .jsonPath("$.[*].patientIdNo")
            .value(hasItem(DEFAULT_PATIENT_ID_NO))
            .jsonPath("$.[*].patientFirstName")
            .value(hasItem(DEFAULT_PATIENT_FIRST_NAME))
            .jsonPath("$.[*].patientMiddleName")
            .value(hasItem(DEFAULT_PATIENT_MIDDLE_NAME))
            .jsonPath("$.[*].patientLastName")
            .value(hasItem(DEFAULT_PATIENT_LAST_NAME))
            .jsonPath("$.[*].patientContact1")
            .value(hasItem(DEFAULT_PATIENT_CONTACT_1))
            .jsonPath("$.[*].patientContact2")
            .value(hasItem(DEFAULT_PATIENT_CONTACT_2))
            .jsonPath("$.[*].patientBillingAddressLine1")
            .value(hasItem(DEFAULT_PATIENT_BILLING_ADDRESS_LINE_1))
            .jsonPath("$.[*].patientBillingAddressLine2")
            .value(hasItem(DEFAULT_PATIENT_BILLING_ADDRESS_LINE_2))
            .jsonPath("$.[*].patientBillingAddressState")
            .value(hasItem(DEFAULT_PATIENT_BILLING_ADDRESS_STATE))
            .jsonPath("$.[*].patientBillingAddressCity")
            .value(hasItem(DEFAULT_PATIENT_BILLING_ADDRESS_CITY))
            .jsonPath("$.[*].patientBillingAddressZip")
            .value(hasItem(DEFAULT_PATIENT_BILLING_ADDRESS_ZIP))
            .jsonPath("$.[*].patientDeliveyAddressLine1")
            .value(hasItem(DEFAULT_PATIENT_DELIVEY_ADDRESS_LINE_1))
            .jsonPath("$.[*].patientDeliveyAddressLine2")
            .value(hasItem(DEFAULT_PATIENT_DELIVEY_ADDRESS_LINE_2))
            .jsonPath("$.[*].patientDeliveyAddressState")
            .value(hasItem(DEFAULT_PATIENT_DELIVEY_ADDRESS_STATE))
            .jsonPath("$.[*].patientDeliveyAddressCity")
            .value(hasItem(DEFAULT_PATIENT_DELIVEY_ADDRESS_CITY))
            .jsonPath("$.[*].patientDeliveyAddressZip")
            .value(hasItem(DEFAULT_PATIENT_DELIVEY_ADDRESS_ZIP))
            .jsonPath("$.[*].pickupExchangeScheduleDateTime")
            .value(hasItem(DEFAULT_PICKUP_EXCHANGE_SCHEDULE_DATE_TIME.toString()))
            .jsonPath("$.[*].pickupExchangeActualDateTime")
            .value(hasItem(DEFAULT_PICKUP_EXCHANGE_ACTUAL_DATE_TIME.toString()))
            .jsonPath("$.[*].pickupExchangeReason")
            .value(hasItem(DEFAULT_PICKUP_EXCHANGE_REASON))
            .jsonPath("$.[*].pickupExchangeRequest")
            .value(hasItem(DEFAULT_PICKUP_EXCHANGE_REQUEST))
            .jsonPath("$.[*].pickupExchangeNote")
            .value(hasItem(DEFAULT_PICKUP_EXCHANGE_NOTE))
            .jsonPath("$.[*].pickupExchangeAgentIdNo")
            .value(hasItem(DEFAULT_PICKUP_EXCHANGE_AGENT_ID_NO))
            .jsonPath("$.[*].pickupExchangeAgentName")
            .value(hasItem(DEFAULT_PICKUP_EXCHANGE_AGENT_NAME))
            .jsonPath("$.[*].pickupExchangeDocumentId")
            .value(hasItem(DEFAULT_PICKUP_EXCHANGE_DOCUMENT_ID))
            .jsonPath("$.[*].pickupExchangeDocumentNo")
            .value(hasItem(DEFAULT_PICKUP_EXCHANGE_DOCUMENT_NO))
            .jsonPath("$.[*].pickupExchangeDocumentName")
            .value(hasItem(DEFAULT_PICKUP_EXCHANGE_DOCUMENT_NAME))
            .jsonPath("$.[*].pickupExchangeStatus")
            .value(hasItem(DEFAULT_PICKUP_EXCHANGE_STATUS))
            .jsonPath("$.[*].pickupExchangeComments")
            .value(hasItem(DEFAULT_PICKUP_EXCHANGE_COMMENTS))
            .jsonPath("$.[*].isPatientSigned")
            .value(hasItem(DEFAULT_IS_PATIENT_SIGNED))
            .jsonPath("$.[*].relationshipWithPatient")
            .value(hasItem(DEFAULT_RELATIONSHIP_WITH_PATIENT))
            .jsonPath("$.[*].patientSignedDateTime")
            .value(hasItem(DEFAULT_PATIENT_SIGNED_DATE_TIME.toString()))
            .jsonPath("$.[*].isAgentSigned")
            .value(hasItem(DEFAULT_IS_AGENT_SIGNED))
            .jsonPath("$.[*].lastBillingDate")
            .value(hasItem(DEFAULT_LAST_BILLING_DATE.toString()))
            .jsonPath("$.[*].dateOfDeath")
            .value(hasItem(DEFAULT_DATE_OF_DEATH.toString()))
            .jsonPath("$.[*].pickupExchangeSupportingDocument1")
            .value(hasItem(DEFAULT_PICKUP_EXCHANGE_SUPPORTING_DOCUMENT_1))
            .jsonPath("$.[*].pickupExchangeSupportingDocument2")
            .value(hasItem(DEFAULT_PICKUP_EXCHANGE_SUPPORTING_DOCUMENT_2))
            .jsonPath("$.[*].patientNotsignedReason")
            .value(hasItem(DEFAULT_PATIENT_NOTSIGNED_REASON))
            .jsonPath("$.[*].pickupExchangeJsonData")
            .value(hasItem(DEFAULT_PICKUP_EXCHANGE_JSON_DATA))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS))
            .jsonPath("$.[*].createdById")
            .value(hasItem(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.[*].createdByName")
            .value(hasItem(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.[*].createdDate")
            .value(hasItem(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.[*].updatedById")
            .value(hasItem(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.[*].updatedByName")
            .value(hasItem(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.[*].updatedDate")
            .value(hasItem(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.[*].pickupExchangeUuid")
            .value(hasItem(DEFAULT_PICKUP_EXCHANGE_UUID.toString()));
    }

    @Test
    void getPickupExchange() {
        // Initialize the database
        pickupExchangeRepository.save(pickupExchange).block();

        // Get the pickupExchange
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, pickupExchange.getPickupExchangeId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.pickupExchangeId")
            .value(is(pickupExchange.getPickupExchangeId().intValue()))
            .jsonPath("$.pickupExchangeNo")
            .value(is(DEFAULT_PICKUP_EXCHANGE_NO))
            .jsonPath("$.pickupExchangeType")
            .value(is(DEFAULT_PICKUP_EXCHANGE_TYPE))
            .jsonPath("$.soId")
            .value(is(DEFAULT_SO_ID.intValue()))
            .jsonPath("$.soNo")
            .value(is(DEFAULT_SO_NO))
            .jsonPath("$.branchId")
            .value(is(DEFAULT_BRANCH_ID.intValue()))
            .jsonPath("$.branchName")
            .value(is(DEFAULT_BRANCH_NAME))
            .jsonPath("$.inventoryLocationId")
            .value(is(DEFAULT_INVENTORY_LOCATION_ID.intValue()))
            .jsonPath("$.inventoryLocationName")
            .value(is(DEFAULT_INVENTORY_LOCATION_NAME))
            .jsonPath("$.patientId")
            .value(is(DEFAULT_PATIENT_ID.intValue()))
            .jsonPath("$.patientIdNo")
            .value(is(DEFAULT_PATIENT_ID_NO))
            .jsonPath("$.patientFirstName")
            .value(is(DEFAULT_PATIENT_FIRST_NAME))
            .jsonPath("$.patientMiddleName")
            .value(is(DEFAULT_PATIENT_MIDDLE_NAME))
            .jsonPath("$.patientLastName")
            .value(is(DEFAULT_PATIENT_LAST_NAME))
            .jsonPath("$.patientContact1")
            .value(is(DEFAULT_PATIENT_CONTACT_1))
            .jsonPath("$.patientContact2")
            .value(is(DEFAULT_PATIENT_CONTACT_2))
            .jsonPath("$.patientBillingAddressLine1")
            .value(is(DEFAULT_PATIENT_BILLING_ADDRESS_LINE_1))
            .jsonPath("$.patientBillingAddressLine2")
            .value(is(DEFAULT_PATIENT_BILLING_ADDRESS_LINE_2))
            .jsonPath("$.patientBillingAddressState")
            .value(is(DEFAULT_PATIENT_BILLING_ADDRESS_STATE))
            .jsonPath("$.patientBillingAddressCity")
            .value(is(DEFAULT_PATIENT_BILLING_ADDRESS_CITY))
            .jsonPath("$.patientBillingAddressZip")
            .value(is(DEFAULT_PATIENT_BILLING_ADDRESS_ZIP))
            .jsonPath("$.patientDeliveyAddressLine1")
            .value(is(DEFAULT_PATIENT_DELIVEY_ADDRESS_LINE_1))
            .jsonPath("$.patientDeliveyAddressLine2")
            .value(is(DEFAULT_PATIENT_DELIVEY_ADDRESS_LINE_2))
            .jsonPath("$.patientDeliveyAddressState")
            .value(is(DEFAULT_PATIENT_DELIVEY_ADDRESS_STATE))
            .jsonPath("$.patientDeliveyAddressCity")
            .value(is(DEFAULT_PATIENT_DELIVEY_ADDRESS_CITY))
            .jsonPath("$.patientDeliveyAddressZip")
            .value(is(DEFAULT_PATIENT_DELIVEY_ADDRESS_ZIP))
            .jsonPath("$.pickupExchangeScheduleDateTime")
            .value(is(DEFAULT_PICKUP_EXCHANGE_SCHEDULE_DATE_TIME.toString()))
            .jsonPath("$.pickupExchangeActualDateTime")
            .value(is(DEFAULT_PICKUP_EXCHANGE_ACTUAL_DATE_TIME.toString()))
            .jsonPath("$.pickupExchangeReason")
            .value(is(DEFAULT_PICKUP_EXCHANGE_REASON))
            .jsonPath("$.pickupExchangeRequest")
            .value(is(DEFAULT_PICKUP_EXCHANGE_REQUEST))
            .jsonPath("$.pickupExchangeNote")
            .value(is(DEFAULT_PICKUP_EXCHANGE_NOTE))
            .jsonPath("$.pickupExchangeAgentIdNo")
            .value(is(DEFAULT_PICKUP_EXCHANGE_AGENT_ID_NO))
            .jsonPath("$.pickupExchangeAgentName")
            .value(is(DEFAULT_PICKUP_EXCHANGE_AGENT_NAME))
            .jsonPath("$.pickupExchangeDocumentId")
            .value(is(DEFAULT_PICKUP_EXCHANGE_DOCUMENT_ID))
            .jsonPath("$.pickupExchangeDocumentNo")
            .value(is(DEFAULT_PICKUP_EXCHANGE_DOCUMENT_NO))
            .jsonPath("$.pickupExchangeDocumentName")
            .value(is(DEFAULT_PICKUP_EXCHANGE_DOCUMENT_NAME))
            .jsonPath("$.pickupExchangeStatus")
            .value(is(DEFAULT_PICKUP_EXCHANGE_STATUS))
            .jsonPath("$.pickupExchangeComments")
            .value(is(DEFAULT_PICKUP_EXCHANGE_COMMENTS))
            .jsonPath("$.isPatientSigned")
            .value(is(DEFAULT_IS_PATIENT_SIGNED))
            .jsonPath("$.relationshipWithPatient")
            .value(is(DEFAULT_RELATIONSHIP_WITH_PATIENT))
            .jsonPath("$.patientSignedDateTime")
            .value(is(DEFAULT_PATIENT_SIGNED_DATE_TIME.toString()))
            .jsonPath("$.isAgentSigned")
            .value(is(DEFAULT_IS_AGENT_SIGNED))
            .jsonPath("$.lastBillingDate")
            .value(is(DEFAULT_LAST_BILLING_DATE.toString()))
            .jsonPath("$.dateOfDeath")
            .value(is(DEFAULT_DATE_OF_DEATH.toString()))
            .jsonPath("$.pickupExchangeSupportingDocument1")
            .value(is(DEFAULT_PICKUP_EXCHANGE_SUPPORTING_DOCUMENT_1))
            .jsonPath("$.pickupExchangeSupportingDocument2")
            .value(is(DEFAULT_PICKUP_EXCHANGE_SUPPORTING_DOCUMENT_2))
            .jsonPath("$.patientNotsignedReason")
            .value(is(DEFAULT_PATIENT_NOTSIGNED_REASON))
            .jsonPath("$.pickupExchangeJsonData")
            .value(is(DEFAULT_PICKUP_EXCHANGE_JSON_DATA))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS))
            .jsonPath("$.createdById")
            .value(is(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.createdByName")
            .value(is(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.createdDate")
            .value(is(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.updatedById")
            .value(is(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.updatedByName")
            .value(is(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.updatedDate")
            .value(is(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.pickupExchangeUuid")
            .value(is(DEFAULT_PICKUP_EXCHANGE_UUID.toString()));
    }

    @Test
    void getNonExistingPickupExchange() {
        // Get the pickupExchange
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putNewPickupExchange() throws Exception {
        // Initialize the database
        pickupExchangeRepository.save(pickupExchange).block();

        int databaseSizeBeforeUpdate = pickupExchangeRepository.findAll().collectList().block().size();

        // Update the pickupExchange
        PickupExchange updatedPickupExchange = pickupExchangeRepository.findById(pickupExchange.getPickupExchangeId()).block();
        updatedPickupExchange
            .pickupExchangeNo(UPDATED_PICKUP_EXCHANGE_NO)
            .pickupExchangeType(UPDATED_PICKUP_EXCHANGE_TYPE)
            .soId(UPDATED_SO_ID)
            .soNo(UPDATED_SO_NO)
            .branchId(UPDATED_BRANCH_ID)
            .branchName(UPDATED_BRANCH_NAME)
            .inventoryLocationId(UPDATED_INVENTORY_LOCATION_ID)
            .inventoryLocationName(UPDATED_INVENTORY_LOCATION_NAME)
            .patientId(UPDATED_PATIENT_ID)
            .patientIdNo(UPDATED_PATIENT_ID_NO)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientMiddleName(UPDATED_PATIENT_MIDDLE_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .patientContact1(UPDATED_PATIENT_CONTACT_1)
            .patientContact2(UPDATED_PATIENT_CONTACT_2)
            .patientBillingAddressLine1(UPDATED_PATIENT_BILLING_ADDRESS_LINE_1)
            .patientBillingAddressLine2(UPDATED_PATIENT_BILLING_ADDRESS_LINE_2)
            .patientBillingAddressState(UPDATED_PATIENT_BILLING_ADDRESS_STATE)
            .patientBillingAddressCity(UPDATED_PATIENT_BILLING_ADDRESS_CITY)
            .patientBillingAddressZip(UPDATED_PATIENT_BILLING_ADDRESS_ZIP)
            .patientDeliveyAddressLine1(UPDATED_PATIENT_DELIVEY_ADDRESS_LINE_1)
            .patientDeliveyAddressLine2(UPDATED_PATIENT_DELIVEY_ADDRESS_LINE_2)
            .patientDeliveyAddressState(UPDATED_PATIENT_DELIVEY_ADDRESS_STATE)
            .patientDeliveyAddressCity(UPDATED_PATIENT_DELIVEY_ADDRESS_CITY)
            .patientDeliveyAddressZip(UPDATED_PATIENT_DELIVEY_ADDRESS_ZIP)
            .pickupExchangeScheduleDateTime(UPDATED_PICKUP_EXCHANGE_SCHEDULE_DATE_TIME)
            .pickupExchangeActualDateTime(UPDATED_PICKUP_EXCHANGE_ACTUAL_DATE_TIME)
            .pickupExchangeReason(UPDATED_PICKUP_EXCHANGE_REASON)
            .pickupExchangeRequest(UPDATED_PICKUP_EXCHANGE_REQUEST)
            .pickupExchangeNote(UPDATED_PICKUP_EXCHANGE_NOTE)
            .pickupExchangeAgentIdNo(UPDATED_PICKUP_EXCHANGE_AGENT_ID_NO)
            .pickupExchangeAgentName(UPDATED_PICKUP_EXCHANGE_AGENT_NAME)
            .pickupExchangeDocumentId(UPDATED_PICKUP_EXCHANGE_DOCUMENT_ID)
            .pickupExchangeDocumentNo(UPDATED_PICKUP_EXCHANGE_DOCUMENT_NO)
            .pickupExchangeDocumentName(UPDATED_PICKUP_EXCHANGE_DOCUMENT_NAME)
            .pickupExchangeStatus(UPDATED_PICKUP_EXCHANGE_STATUS)
            .pickupExchangeComments(UPDATED_PICKUP_EXCHANGE_COMMENTS)
            .isPatientSigned(UPDATED_IS_PATIENT_SIGNED)
            .relationshipWithPatient(UPDATED_RELATIONSHIP_WITH_PATIENT)
            .patientSignedDateTime(UPDATED_PATIENT_SIGNED_DATE_TIME)
            .isAgentSigned(UPDATED_IS_AGENT_SIGNED)
            .lastBillingDate(UPDATED_LAST_BILLING_DATE)
            .dateOfDeath(UPDATED_DATE_OF_DEATH)
            .pickupExchangeSupportingDocument1(UPDATED_PICKUP_EXCHANGE_SUPPORTING_DOCUMENT_1)
            .pickupExchangeSupportingDocument2(UPDATED_PICKUP_EXCHANGE_SUPPORTING_DOCUMENT_2)
            .patientNotsignedReason(UPDATED_PATIENT_NOTSIGNED_REASON)
            .pickupExchangeJsonData(UPDATED_PICKUP_EXCHANGE_JSON_DATA)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .pickupExchangeUuid(UPDATED_PICKUP_EXCHANGE_UUID);
        PickupExchangeDTO pickupExchangeDTO = pickupExchangeMapper.toDto(updatedPickupExchange);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, pickupExchangeDTO.getPickupExchangeId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(pickupExchangeDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PickupExchange in the database
        List<PickupExchange> pickupExchangeList = pickupExchangeRepository.findAll().collectList().block();
        assertThat(pickupExchangeList).hasSize(databaseSizeBeforeUpdate);
        PickupExchange testPickupExchange = pickupExchangeList.get(pickupExchangeList.size() - 1);
        assertThat(testPickupExchange.getPickupExchangeNo()).isEqualTo(UPDATED_PICKUP_EXCHANGE_NO);
        assertThat(testPickupExchange.getPickupExchangeType()).isEqualTo(UPDATED_PICKUP_EXCHANGE_TYPE);
        assertThat(testPickupExchange.getSoId()).isEqualTo(UPDATED_SO_ID);
        assertThat(testPickupExchange.getSoNo()).isEqualTo(UPDATED_SO_NO);
        assertThat(testPickupExchange.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
        assertThat(testPickupExchange.getBranchName()).isEqualTo(UPDATED_BRANCH_NAME);
        assertThat(testPickupExchange.getInventoryLocationId()).isEqualTo(UPDATED_INVENTORY_LOCATION_ID);
        assertThat(testPickupExchange.getInventoryLocationName()).isEqualTo(UPDATED_INVENTORY_LOCATION_NAME);
        assertThat(testPickupExchange.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testPickupExchange.getPatientIdNo()).isEqualTo(UPDATED_PATIENT_ID_NO);
        assertThat(testPickupExchange.getPatientFirstName()).isEqualTo(UPDATED_PATIENT_FIRST_NAME);
        assertThat(testPickupExchange.getPatientMiddleName()).isEqualTo(UPDATED_PATIENT_MIDDLE_NAME);
        assertThat(testPickupExchange.getPatientLastName()).isEqualTo(UPDATED_PATIENT_LAST_NAME);
        assertThat(testPickupExchange.getPatientContact1()).isEqualTo(UPDATED_PATIENT_CONTACT_1);
        assertThat(testPickupExchange.getPatientContact2()).isEqualTo(UPDATED_PATIENT_CONTACT_2);
        assertThat(testPickupExchange.getPatientBillingAddressLine1()).isEqualTo(UPDATED_PATIENT_BILLING_ADDRESS_LINE_1);
        assertThat(testPickupExchange.getPatientBillingAddressLine2()).isEqualTo(UPDATED_PATIENT_BILLING_ADDRESS_LINE_2);
        assertThat(testPickupExchange.getPatientBillingAddressState()).isEqualTo(UPDATED_PATIENT_BILLING_ADDRESS_STATE);
        assertThat(testPickupExchange.getPatientBillingAddressCity()).isEqualTo(UPDATED_PATIENT_BILLING_ADDRESS_CITY);
        assertThat(testPickupExchange.getPatientBillingAddressZip()).isEqualTo(UPDATED_PATIENT_BILLING_ADDRESS_ZIP);
        assertThat(testPickupExchange.getPatientDeliveyAddressLine1()).isEqualTo(UPDATED_PATIENT_DELIVEY_ADDRESS_LINE_1);
        assertThat(testPickupExchange.getPatientDeliveyAddressLine2()).isEqualTo(UPDATED_PATIENT_DELIVEY_ADDRESS_LINE_2);
        assertThat(testPickupExchange.getPatientDeliveyAddressState()).isEqualTo(UPDATED_PATIENT_DELIVEY_ADDRESS_STATE);
        assertThat(testPickupExchange.getPatientDeliveyAddressCity()).isEqualTo(UPDATED_PATIENT_DELIVEY_ADDRESS_CITY);
        assertThat(testPickupExchange.getPatientDeliveyAddressZip()).isEqualTo(UPDATED_PATIENT_DELIVEY_ADDRESS_ZIP);
        assertThat(testPickupExchange.getPickupExchangeScheduleDateTime()).isEqualTo(UPDATED_PICKUP_EXCHANGE_SCHEDULE_DATE_TIME);
        assertThat(testPickupExchange.getPickupExchangeActualDateTime()).isEqualTo(UPDATED_PICKUP_EXCHANGE_ACTUAL_DATE_TIME);
        assertThat(testPickupExchange.getPickupExchangeReason()).isEqualTo(UPDATED_PICKUP_EXCHANGE_REASON);
        assertThat(testPickupExchange.getPickupExchangeRequest()).isEqualTo(UPDATED_PICKUP_EXCHANGE_REQUEST);
        assertThat(testPickupExchange.getPickupExchangeNote()).isEqualTo(UPDATED_PICKUP_EXCHANGE_NOTE);
        assertThat(testPickupExchange.getPickupExchangeAgentIdNo()).isEqualTo(UPDATED_PICKUP_EXCHANGE_AGENT_ID_NO);
        assertThat(testPickupExchange.getPickupExchangeAgentName()).isEqualTo(UPDATED_PICKUP_EXCHANGE_AGENT_NAME);
        assertThat(testPickupExchange.getPickupExchangeDocumentId()).isEqualTo(UPDATED_PICKUP_EXCHANGE_DOCUMENT_ID);
        assertThat(testPickupExchange.getPickupExchangeDocumentNo()).isEqualTo(UPDATED_PICKUP_EXCHANGE_DOCUMENT_NO);
        assertThat(testPickupExchange.getPickupExchangeDocumentName()).isEqualTo(UPDATED_PICKUP_EXCHANGE_DOCUMENT_NAME);
        assertThat(testPickupExchange.getPickupExchangeStatus()).isEqualTo(UPDATED_PICKUP_EXCHANGE_STATUS);
        assertThat(testPickupExchange.getPickupExchangeComments()).isEqualTo(UPDATED_PICKUP_EXCHANGE_COMMENTS);
        assertThat(testPickupExchange.getIsPatientSigned()).isEqualTo(UPDATED_IS_PATIENT_SIGNED);
        assertThat(testPickupExchange.getRelationshipWithPatient()).isEqualTo(UPDATED_RELATIONSHIP_WITH_PATIENT);
        assertThat(testPickupExchange.getPatientSignedDateTime()).isEqualTo(UPDATED_PATIENT_SIGNED_DATE_TIME);
        assertThat(testPickupExchange.getIsAgentSigned()).isEqualTo(UPDATED_IS_AGENT_SIGNED);
        assertThat(testPickupExchange.getLastBillingDate()).isEqualTo(UPDATED_LAST_BILLING_DATE);
        assertThat(testPickupExchange.getDateOfDeath()).isEqualTo(UPDATED_DATE_OF_DEATH);
        assertThat(testPickupExchange.getPickupExchangeSupportingDocument1()).isEqualTo(UPDATED_PICKUP_EXCHANGE_SUPPORTING_DOCUMENT_1);
        assertThat(testPickupExchange.getPickupExchangeSupportingDocument2()).isEqualTo(UPDATED_PICKUP_EXCHANGE_SUPPORTING_DOCUMENT_2);
        assertThat(testPickupExchange.getPatientNotsignedReason()).isEqualTo(UPDATED_PATIENT_NOTSIGNED_REASON);
        assertThat(testPickupExchange.getPickupExchangeJsonData()).isEqualTo(UPDATED_PICKUP_EXCHANGE_JSON_DATA);
        assertThat(testPickupExchange.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPickupExchange.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPickupExchange.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPickupExchange.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPickupExchange.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPickupExchange.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPickupExchange.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPickupExchange.getPickupExchangeUuid()).isEqualTo(UPDATED_PICKUP_EXCHANGE_UUID);
    }

    @Test
    void putNonExistingPickupExchange() throws Exception {
        int databaseSizeBeforeUpdate = pickupExchangeRepository.findAll().collectList().block().size();
        pickupExchange.setPickupExchangeId(count.incrementAndGet());

        // Create the PickupExchange
        PickupExchangeDTO pickupExchangeDTO = pickupExchangeMapper.toDto(pickupExchange);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, pickupExchangeDTO.getPickupExchangeId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(pickupExchangeDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PickupExchange in the database
        List<PickupExchange> pickupExchangeList = pickupExchangeRepository.findAll().collectList().block();
        assertThat(pickupExchangeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchPickupExchange() throws Exception {
        int databaseSizeBeforeUpdate = pickupExchangeRepository.findAll().collectList().block().size();
        pickupExchange.setPickupExchangeId(count.incrementAndGet());

        // Create the PickupExchange
        PickupExchangeDTO pickupExchangeDTO = pickupExchangeMapper.toDto(pickupExchange);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(pickupExchangeDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PickupExchange in the database
        List<PickupExchange> pickupExchangeList = pickupExchangeRepository.findAll().collectList().block();
        assertThat(pickupExchangeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamPickupExchange() throws Exception {
        int databaseSizeBeforeUpdate = pickupExchangeRepository.findAll().collectList().block().size();
        pickupExchange.setPickupExchangeId(count.incrementAndGet());

        // Create the PickupExchange
        PickupExchangeDTO pickupExchangeDTO = pickupExchangeMapper.toDto(pickupExchange);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(pickupExchangeDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PickupExchange in the database
        List<PickupExchange> pickupExchangeList = pickupExchangeRepository.findAll().collectList().block();
        assertThat(pickupExchangeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdatePickupExchangeWithPatch() throws Exception {
        // Initialize the database
        pickupExchangeRepository.save(pickupExchange).block();

        int databaseSizeBeforeUpdate = pickupExchangeRepository.findAll().collectList().block().size();

        // Update the pickupExchange using partial update
        PickupExchange partialUpdatedPickupExchange = new PickupExchange();
        partialUpdatedPickupExchange.setPickupExchangeId(pickupExchange.getPickupExchangeId());

        partialUpdatedPickupExchange
            .pickupExchangeNo(UPDATED_PICKUP_EXCHANGE_NO)
            .soId(UPDATED_SO_ID)
            .soNo(UPDATED_SO_NO)
            .inventoryLocationId(UPDATED_INVENTORY_LOCATION_ID)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientMiddleName(UPDATED_PATIENT_MIDDLE_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .patientContact1(UPDATED_PATIENT_CONTACT_1)
            .patientContact2(UPDATED_PATIENT_CONTACT_2)
            .patientBillingAddressCity(UPDATED_PATIENT_BILLING_ADDRESS_CITY)
            .patientDeliveyAddressLine1(UPDATED_PATIENT_DELIVEY_ADDRESS_LINE_1)
            .patientDeliveyAddressCity(UPDATED_PATIENT_DELIVEY_ADDRESS_CITY)
            .patientDeliveyAddressZip(UPDATED_PATIENT_DELIVEY_ADDRESS_ZIP)
            .pickupExchangeScheduleDateTime(UPDATED_PICKUP_EXCHANGE_SCHEDULE_DATE_TIME)
            .pickupExchangeActualDateTime(UPDATED_PICKUP_EXCHANGE_ACTUAL_DATE_TIME)
            .pickupExchangeAgentIdNo(UPDATED_PICKUP_EXCHANGE_AGENT_ID_NO)
            .pickupExchangeDocumentNo(UPDATED_PICKUP_EXCHANGE_DOCUMENT_NO)
            .pickupExchangeComments(UPDATED_PICKUP_EXCHANGE_COMMENTS)
            .relationshipWithPatient(UPDATED_RELATIONSHIP_WITH_PATIENT)
            .patientSignedDateTime(UPDATED_PATIENT_SIGNED_DATE_TIME)
            .isAgentSigned(UPDATED_IS_AGENT_SIGNED)
            .lastBillingDate(UPDATED_LAST_BILLING_DATE)
            .patientNotsignedReason(UPDATED_PATIENT_NOTSIGNED_REASON)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPickupExchange.getPickupExchangeId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPickupExchange))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PickupExchange in the database
        List<PickupExchange> pickupExchangeList = pickupExchangeRepository.findAll().collectList().block();
        assertThat(pickupExchangeList).hasSize(databaseSizeBeforeUpdate);
        PickupExchange testPickupExchange = pickupExchangeList.get(pickupExchangeList.size() - 1);
        assertThat(testPickupExchange.getPickupExchangeNo()).isEqualTo(UPDATED_PICKUP_EXCHANGE_NO);
        assertThat(testPickupExchange.getPickupExchangeType()).isEqualTo(DEFAULT_PICKUP_EXCHANGE_TYPE);
        assertThat(testPickupExchange.getSoId()).isEqualTo(UPDATED_SO_ID);
        assertThat(testPickupExchange.getSoNo()).isEqualTo(UPDATED_SO_NO);
        assertThat(testPickupExchange.getBranchId()).isEqualTo(DEFAULT_BRANCH_ID);
        assertThat(testPickupExchange.getBranchName()).isEqualTo(DEFAULT_BRANCH_NAME);
        assertThat(testPickupExchange.getInventoryLocationId()).isEqualTo(UPDATED_INVENTORY_LOCATION_ID);
        assertThat(testPickupExchange.getInventoryLocationName()).isEqualTo(DEFAULT_INVENTORY_LOCATION_NAME);
        assertThat(testPickupExchange.getPatientId()).isEqualTo(DEFAULT_PATIENT_ID);
        assertThat(testPickupExchange.getPatientIdNo()).isEqualTo(DEFAULT_PATIENT_ID_NO);
        assertThat(testPickupExchange.getPatientFirstName()).isEqualTo(UPDATED_PATIENT_FIRST_NAME);
        assertThat(testPickupExchange.getPatientMiddleName()).isEqualTo(UPDATED_PATIENT_MIDDLE_NAME);
        assertThat(testPickupExchange.getPatientLastName()).isEqualTo(UPDATED_PATIENT_LAST_NAME);
        assertThat(testPickupExchange.getPatientContact1()).isEqualTo(UPDATED_PATIENT_CONTACT_1);
        assertThat(testPickupExchange.getPatientContact2()).isEqualTo(UPDATED_PATIENT_CONTACT_2);
        assertThat(testPickupExchange.getPatientBillingAddressLine1()).isEqualTo(DEFAULT_PATIENT_BILLING_ADDRESS_LINE_1);
        assertThat(testPickupExchange.getPatientBillingAddressLine2()).isEqualTo(DEFAULT_PATIENT_BILLING_ADDRESS_LINE_2);
        assertThat(testPickupExchange.getPatientBillingAddressState()).isEqualTo(DEFAULT_PATIENT_BILLING_ADDRESS_STATE);
        assertThat(testPickupExchange.getPatientBillingAddressCity()).isEqualTo(UPDATED_PATIENT_BILLING_ADDRESS_CITY);
        assertThat(testPickupExchange.getPatientBillingAddressZip()).isEqualTo(DEFAULT_PATIENT_BILLING_ADDRESS_ZIP);
        assertThat(testPickupExchange.getPatientDeliveyAddressLine1()).isEqualTo(UPDATED_PATIENT_DELIVEY_ADDRESS_LINE_1);
        assertThat(testPickupExchange.getPatientDeliveyAddressLine2()).isEqualTo(DEFAULT_PATIENT_DELIVEY_ADDRESS_LINE_2);
        assertThat(testPickupExchange.getPatientDeliveyAddressState()).isEqualTo(DEFAULT_PATIENT_DELIVEY_ADDRESS_STATE);
        assertThat(testPickupExchange.getPatientDeliveyAddressCity()).isEqualTo(UPDATED_PATIENT_DELIVEY_ADDRESS_CITY);
        assertThat(testPickupExchange.getPatientDeliveyAddressZip()).isEqualTo(UPDATED_PATIENT_DELIVEY_ADDRESS_ZIP);
        assertThat(testPickupExchange.getPickupExchangeScheduleDateTime()).isEqualTo(UPDATED_PICKUP_EXCHANGE_SCHEDULE_DATE_TIME);
        assertThat(testPickupExchange.getPickupExchangeActualDateTime()).isEqualTo(UPDATED_PICKUP_EXCHANGE_ACTUAL_DATE_TIME);
        assertThat(testPickupExchange.getPickupExchangeReason()).isEqualTo(DEFAULT_PICKUP_EXCHANGE_REASON);
        assertThat(testPickupExchange.getPickupExchangeRequest()).isEqualTo(DEFAULT_PICKUP_EXCHANGE_REQUEST);
        assertThat(testPickupExchange.getPickupExchangeNote()).isEqualTo(DEFAULT_PICKUP_EXCHANGE_NOTE);
        assertThat(testPickupExchange.getPickupExchangeAgentIdNo()).isEqualTo(UPDATED_PICKUP_EXCHANGE_AGENT_ID_NO);
        assertThat(testPickupExchange.getPickupExchangeAgentName()).isEqualTo(DEFAULT_PICKUP_EXCHANGE_AGENT_NAME);
        assertThat(testPickupExchange.getPickupExchangeDocumentId()).isEqualTo(DEFAULT_PICKUP_EXCHANGE_DOCUMENT_ID);
        assertThat(testPickupExchange.getPickupExchangeDocumentNo()).isEqualTo(UPDATED_PICKUP_EXCHANGE_DOCUMENT_NO);
        assertThat(testPickupExchange.getPickupExchangeDocumentName()).isEqualTo(DEFAULT_PICKUP_EXCHANGE_DOCUMENT_NAME);
        assertThat(testPickupExchange.getPickupExchangeStatus()).isEqualTo(DEFAULT_PICKUP_EXCHANGE_STATUS);
        assertThat(testPickupExchange.getPickupExchangeComments()).isEqualTo(UPDATED_PICKUP_EXCHANGE_COMMENTS);
        assertThat(testPickupExchange.getIsPatientSigned()).isEqualTo(DEFAULT_IS_PATIENT_SIGNED);
        assertThat(testPickupExchange.getRelationshipWithPatient()).isEqualTo(UPDATED_RELATIONSHIP_WITH_PATIENT);
        assertThat(testPickupExchange.getPatientSignedDateTime()).isEqualTo(UPDATED_PATIENT_SIGNED_DATE_TIME);
        assertThat(testPickupExchange.getIsAgentSigned()).isEqualTo(UPDATED_IS_AGENT_SIGNED);
        assertThat(testPickupExchange.getLastBillingDate()).isEqualTo(UPDATED_LAST_BILLING_DATE);
        assertThat(testPickupExchange.getDateOfDeath()).isEqualTo(DEFAULT_DATE_OF_DEATH);
        assertThat(testPickupExchange.getPickupExchangeSupportingDocument1()).isEqualTo(DEFAULT_PICKUP_EXCHANGE_SUPPORTING_DOCUMENT_1);
        assertThat(testPickupExchange.getPickupExchangeSupportingDocument2()).isEqualTo(DEFAULT_PICKUP_EXCHANGE_SUPPORTING_DOCUMENT_2);
        assertThat(testPickupExchange.getPatientNotsignedReason()).isEqualTo(UPDATED_PATIENT_NOTSIGNED_REASON);
        assertThat(testPickupExchange.getPickupExchangeJsonData()).isEqualTo(DEFAULT_PICKUP_EXCHANGE_JSON_DATA);
        assertThat(testPickupExchange.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPickupExchange.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testPickupExchange.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testPickupExchange.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testPickupExchange.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPickupExchange.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testPickupExchange.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPickupExchange.getPickupExchangeUuid()).isEqualTo(DEFAULT_PICKUP_EXCHANGE_UUID);
    }

    @Test
    void fullUpdatePickupExchangeWithPatch() throws Exception {
        // Initialize the database
        pickupExchangeRepository.save(pickupExchange).block();

        int databaseSizeBeforeUpdate = pickupExchangeRepository.findAll().collectList().block().size();

        // Update the pickupExchange using partial update
        PickupExchange partialUpdatedPickupExchange = new PickupExchange();
        partialUpdatedPickupExchange.setPickupExchangeId(pickupExchange.getPickupExchangeId());

        partialUpdatedPickupExchange
            .pickupExchangeNo(UPDATED_PICKUP_EXCHANGE_NO)
            .pickupExchangeType(UPDATED_PICKUP_EXCHANGE_TYPE)
            .soId(UPDATED_SO_ID)
            .soNo(UPDATED_SO_NO)
            .branchId(UPDATED_BRANCH_ID)
            .branchName(UPDATED_BRANCH_NAME)
            .inventoryLocationId(UPDATED_INVENTORY_LOCATION_ID)
            .inventoryLocationName(UPDATED_INVENTORY_LOCATION_NAME)
            .patientId(UPDATED_PATIENT_ID)
            .patientIdNo(UPDATED_PATIENT_ID_NO)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientMiddleName(UPDATED_PATIENT_MIDDLE_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .patientContact1(UPDATED_PATIENT_CONTACT_1)
            .patientContact2(UPDATED_PATIENT_CONTACT_2)
            .patientBillingAddressLine1(UPDATED_PATIENT_BILLING_ADDRESS_LINE_1)
            .patientBillingAddressLine2(UPDATED_PATIENT_BILLING_ADDRESS_LINE_2)
            .patientBillingAddressState(UPDATED_PATIENT_BILLING_ADDRESS_STATE)
            .patientBillingAddressCity(UPDATED_PATIENT_BILLING_ADDRESS_CITY)
            .patientBillingAddressZip(UPDATED_PATIENT_BILLING_ADDRESS_ZIP)
            .patientDeliveyAddressLine1(UPDATED_PATIENT_DELIVEY_ADDRESS_LINE_1)
            .patientDeliveyAddressLine2(UPDATED_PATIENT_DELIVEY_ADDRESS_LINE_2)
            .patientDeliveyAddressState(UPDATED_PATIENT_DELIVEY_ADDRESS_STATE)
            .patientDeliveyAddressCity(UPDATED_PATIENT_DELIVEY_ADDRESS_CITY)
            .patientDeliveyAddressZip(UPDATED_PATIENT_DELIVEY_ADDRESS_ZIP)
            .pickupExchangeScheduleDateTime(UPDATED_PICKUP_EXCHANGE_SCHEDULE_DATE_TIME)
            .pickupExchangeActualDateTime(UPDATED_PICKUP_EXCHANGE_ACTUAL_DATE_TIME)
            .pickupExchangeReason(UPDATED_PICKUP_EXCHANGE_REASON)
            .pickupExchangeRequest(UPDATED_PICKUP_EXCHANGE_REQUEST)
            .pickupExchangeNote(UPDATED_PICKUP_EXCHANGE_NOTE)
            .pickupExchangeAgentIdNo(UPDATED_PICKUP_EXCHANGE_AGENT_ID_NO)
            .pickupExchangeAgentName(UPDATED_PICKUP_EXCHANGE_AGENT_NAME)
            .pickupExchangeDocumentId(UPDATED_PICKUP_EXCHANGE_DOCUMENT_ID)
            .pickupExchangeDocumentNo(UPDATED_PICKUP_EXCHANGE_DOCUMENT_NO)
            .pickupExchangeDocumentName(UPDATED_PICKUP_EXCHANGE_DOCUMENT_NAME)
            .pickupExchangeStatus(UPDATED_PICKUP_EXCHANGE_STATUS)
            .pickupExchangeComments(UPDATED_PICKUP_EXCHANGE_COMMENTS)
            .isPatientSigned(UPDATED_IS_PATIENT_SIGNED)
            .relationshipWithPatient(UPDATED_RELATIONSHIP_WITH_PATIENT)
            .patientSignedDateTime(UPDATED_PATIENT_SIGNED_DATE_TIME)
            .isAgentSigned(UPDATED_IS_AGENT_SIGNED)
            .lastBillingDate(UPDATED_LAST_BILLING_DATE)
            .dateOfDeath(UPDATED_DATE_OF_DEATH)
            .pickupExchangeSupportingDocument1(UPDATED_PICKUP_EXCHANGE_SUPPORTING_DOCUMENT_1)
            .pickupExchangeSupportingDocument2(UPDATED_PICKUP_EXCHANGE_SUPPORTING_DOCUMENT_2)
            .patientNotsignedReason(UPDATED_PATIENT_NOTSIGNED_REASON)
            .pickupExchangeJsonData(UPDATED_PICKUP_EXCHANGE_JSON_DATA)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .pickupExchangeUuid(UPDATED_PICKUP_EXCHANGE_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPickupExchange.getPickupExchangeId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPickupExchange))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PickupExchange in the database
        List<PickupExchange> pickupExchangeList = pickupExchangeRepository.findAll().collectList().block();
        assertThat(pickupExchangeList).hasSize(databaseSizeBeforeUpdate);
        PickupExchange testPickupExchange = pickupExchangeList.get(pickupExchangeList.size() - 1);
        assertThat(testPickupExchange.getPickupExchangeNo()).isEqualTo(UPDATED_PICKUP_EXCHANGE_NO);
        assertThat(testPickupExchange.getPickupExchangeType()).isEqualTo(UPDATED_PICKUP_EXCHANGE_TYPE);
        assertThat(testPickupExchange.getSoId()).isEqualTo(UPDATED_SO_ID);
        assertThat(testPickupExchange.getSoNo()).isEqualTo(UPDATED_SO_NO);
        assertThat(testPickupExchange.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
        assertThat(testPickupExchange.getBranchName()).isEqualTo(UPDATED_BRANCH_NAME);
        assertThat(testPickupExchange.getInventoryLocationId()).isEqualTo(UPDATED_INVENTORY_LOCATION_ID);
        assertThat(testPickupExchange.getInventoryLocationName()).isEqualTo(UPDATED_INVENTORY_LOCATION_NAME);
        assertThat(testPickupExchange.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testPickupExchange.getPatientIdNo()).isEqualTo(UPDATED_PATIENT_ID_NO);
        assertThat(testPickupExchange.getPatientFirstName()).isEqualTo(UPDATED_PATIENT_FIRST_NAME);
        assertThat(testPickupExchange.getPatientMiddleName()).isEqualTo(UPDATED_PATIENT_MIDDLE_NAME);
        assertThat(testPickupExchange.getPatientLastName()).isEqualTo(UPDATED_PATIENT_LAST_NAME);
        assertThat(testPickupExchange.getPatientContact1()).isEqualTo(UPDATED_PATIENT_CONTACT_1);
        assertThat(testPickupExchange.getPatientContact2()).isEqualTo(UPDATED_PATIENT_CONTACT_2);
        assertThat(testPickupExchange.getPatientBillingAddressLine1()).isEqualTo(UPDATED_PATIENT_BILLING_ADDRESS_LINE_1);
        assertThat(testPickupExchange.getPatientBillingAddressLine2()).isEqualTo(UPDATED_PATIENT_BILLING_ADDRESS_LINE_2);
        assertThat(testPickupExchange.getPatientBillingAddressState()).isEqualTo(UPDATED_PATIENT_BILLING_ADDRESS_STATE);
        assertThat(testPickupExchange.getPatientBillingAddressCity()).isEqualTo(UPDATED_PATIENT_BILLING_ADDRESS_CITY);
        assertThat(testPickupExchange.getPatientBillingAddressZip()).isEqualTo(UPDATED_PATIENT_BILLING_ADDRESS_ZIP);
        assertThat(testPickupExchange.getPatientDeliveyAddressLine1()).isEqualTo(UPDATED_PATIENT_DELIVEY_ADDRESS_LINE_1);
        assertThat(testPickupExchange.getPatientDeliveyAddressLine2()).isEqualTo(UPDATED_PATIENT_DELIVEY_ADDRESS_LINE_2);
        assertThat(testPickupExchange.getPatientDeliveyAddressState()).isEqualTo(UPDATED_PATIENT_DELIVEY_ADDRESS_STATE);
        assertThat(testPickupExchange.getPatientDeliveyAddressCity()).isEqualTo(UPDATED_PATIENT_DELIVEY_ADDRESS_CITY);
        assertThat(testPickupExchange.getPatientDeliveyAddressZip()).isEqualTo(UPDATED_PATIENT_DELIVEY_ADDRESS_ZIP);
        assertThat(testPickupExchange.getPickupExchangeScheduleDateTime()).isEqualTo(UPDATED_PICKUP_EXCHANGE_SCHEDULE_DATE_TIME);
        assertThat(testPickupExchange.getPickupExchangeActualDateTime()).isEqualTo(UPDATED_PICKUP_EXCHANGE_ACTUAL_DATE_TIME);
        assertThat(testPickupExchange.getPickupExchangeReason()).isEqualTo(UPDATED_PICKUP_EXCHANGE_REASON);
        assertThat(testPickupExchange.getPickupExchangeRequest()).isEqualTo(UPDATED_PICKUP_EXCHANGE_REQUEST);
        assertThat(testPickupExchange.getPickupExchangeNote()).isEqualTo(UPDATED_PICKUP_EXCHANGE_NOTE);
        assertThat(testPickupExchange.getPickupExchangeAgentIdNo()).isEqualTo(UPDATED_PICKUP_EXCHANGE_AGENT_ID_NO);
        assertThat(testPickupExchange.getPickupExchangeAgentName()).isEqualTo(UPDATED_PICKUP_EXCHANGE_AGENT_NAME);
        assertThat(testPickupExchange.getPickupExchangeDocumentId()).isEqualTo(UPDATED_PICKUP_EXCHANGE_DOCUMENT_ID);
        assertThat(testPickupExchange.getPickupExchangeDocumentNo()).isEqualTo(UPDATED_PICKUP_EXCHANGE_DOCUMENT_NO);
        assertThat(testPickupExchange.getPickupExchangeDocumentName()).isEqualTo(UPDATED_PICKUP_EXCHANGE_DOCUMENT_NAME);
        assertThat(testPickupExchange.getPickupExchangeStatus()).isEqualTo(UPDATED_PICKUP_EXCHANGE_STATUS);
        assertThat(testPickupExchange.getPickupExchangeComments()).isEqualTo(UPDATED_PICKUP_EXCHANGE_COMMENTS);
        assertThat(testPickupExchange.getIsPatientSigned()).isEqualTo(UPDATED_IS_PATIENT_SIGNED);
        assertThat(testPickupExchange.getRelationshipWithPatient()).isEqualTo(UPDATED_RELATIONSHIP_WITH_PATIENT);
        assertThat(testPickupExchange.getPatientSignedDateTime()).isEqualTo(UPDATED_PATIENT_SIGNED_DATE_TIME);
        assertThat(testPickupExchange.getIsAgentSigned()).isEqualTo(UPDATED_IS_AGENT_SIGNED);
        assertThat(testPickupExchange.getLastBillingDate()).isEqualTo(UPDATED_LAST_BILLING_DATE);
        assertThat(testPickupExchange.getDateOfDeath()).isEqualTo(UPDATED_DATE_OF_DEATH);
        assertThat(testPickupExchange.getPickupExchangeSupportingDocument1()).isEqualTo(UPDATED_PICKUP_EXCHANGE_SUPPORTING_DOCUMENT_1);
        assertThat(testPickupExchange.getPickupExchangeSupportingDocument2()).isEqualTo(UPDATED_PICKUP_EXCHANGE_SUPPORTING_DOCUMENT_2);
        assertThat(testPickupExchange.getPatientNotsignedReason()).isEqualTo(UPDATED_PATIENT_NOTSIGNED_REASON);
        assertThat(testPickupExchange.getPickupExchangeJsonData()).isEqualTo(UPDATED_PICKUP_EXCHANGE_JSON_DATA);
        assertThat(testPickupExchange.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPickupExchange.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPickupExchange.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPickupExchange.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPickupExchange.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPickupExchange.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPickupExchange.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPickupExchange.getPickupExchangeUuid()).isEqualTo(UPDATED_PICKUP_EXCHANGE_UUID);
    }

    @Test
    void patchNonExistingPickupExchange() throws Exception {
        int databaseSizeBeforeUpdate = pickupExchangeRepository.findAll().collectList().block().size();
        pickupExchange.setPickupExchangeId(count.incrementAndGet());

        // Create the PickupExchange
        PickupExchangeDTO pickupExchangeDTO = pickupExchangeMapper.toDto(pickupExchange);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, pickupExchangeDTO.getPickupExchangeId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(pickupExchangeDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PickupExchange in the database
        List<PickupExchange> pickupExchangeList = pickupExchangeRepository.findAll().collectList().block();
        assertThat(pickupExchangeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchPickupExchange() throws Exception {
        int databaseSizeBeforeUpdate = pickupExchangeRepository.findAll().collectList().block().size();
        pickupExchange.setPickupExchangeId(count.incrementAndGet());

        // Create the PickupExchange
        PickupExchangeDTO pickupExchangeDTO = pickupExchangeMapper.toDto(pickupExchange);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(pickupExchangeDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PickupExchange in the database
        List<PickupExchange> pickupExchangeList = pickupExchangeRepository.findAll().collectList().block();
        assertThat(pickupExchangeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamPickupExchange() throws Exception {
        int databaseSizeBeforeUpdate = pickupExchangeRepository.findAll().collectList().block().size();
        pickupExchange.setPickupExchangeId(count.incrementAndGet());

        // Create the PickupExchange
        PickupExchangeDTO pickupExchangeDTO = pickupExchangeMapper.toDto(pickupExchange);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(pickupExchangeDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PickupExchange in the database
        List<PickupExchange> pickupExchangeList = pickupExchangeRepository.findAll().collectList().block();
        assertThat(pickupExchangeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deletePickupExchange() {
        // Initialize the database
        pickupExchangeRepository.save(pickupExchange).block();

        int databaseSizeBeforeDelete = pickupExchangeRepository.findAll().collectList().block().size();

        // Delete the pickupExchange
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, pickupExchange.getPickupExchangeId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<PickupExchange> pickupExchangeList = pickupExchangeRepository.findAll().collectList().block();
        assertThat(pickupExchangeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
