package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.PickupExchange;
import com.sunknowledge.dme.rcm.repository.PickupExchangeRepository;
import com.sunknowledge.dme.rcm.service.dto.PickupExchangeDTO;
import com.sunknowledge.dme.rcm.service.mapper.PickupExchangeMapper;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.UUID;
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
 * Integration tests for the {@link PickupExchangeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
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
    private MockMvc restPickupExchangeMockMvc;

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

    @BeforeEach
    public void initTest() {
        pickupExchange = createEntity(em);
    }

    @Test
    @Transactional
    void createPickupExchange() throws Exception {
        int databaseSizeBeforeCreate = pickupExchangeRepository.findAll().size();
        // Create the PickupExchange
        PickupExchangeDTO pickupExchangeDTO = pickupExchangeMapper.toDto(pickupExchange);
        restPickupExchangeMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pickupExchangeDTO))
            )
            .andExpect(status().isCreated());

        // Validate the PickupExchange in the database
        List<PickupExchange> pickupExchangeList = pickupExchangeRepository.findAll();
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
    @Transactional
    void createPickupExchangeWithExistingId() throws Exception {
        // Create the PickupExchange with an existing ID
        pickupExchange.setPickupExchangeId(1L);
        PickupExchangeDTO pickupExchangeDTO = pickupExchangeMapper.toDto(pickupExchange);

        int databaseSizeBeforeCreate = pickupExchangeRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPickupExchangeMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pickupExchangeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PickupExchange in the database
        List<PickupExchange> pickupExchangeList = pickupExchangeRepository.findAll();
        assertThat(pickupExchangeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPickupExchanges() throws Exception {
        // Initialize the database
        pickupExchangeRepository.saveAndFlush(pickupExchange);

        // Get all the pickupExchangeList
        restPickupExchangeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=pickupExchangeId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].pickupExchangeId").value(hasItem(pickupExchange.getPickupExchangeId().intValue())))
            .andExpect(jsonPath("$.[*].pickupExchangeNo").value(hasItem(DEFAULT_PICKUP_EXCHANGE_NO)))
            .andExpect(jsonPath("$.[*].pickupExchangeType").value(hasItem(DEFAULT_PICKUP_EXCHANGE_TYPE)))
            .andExpect(jsonPath("$.[*].soId").value(hasItem(DEFAULT_SO_ID.intValue())))
            .andExpect(jsonPath("$.[*].soNo").value(hasItem(DEFAULT_SO_NO)))
            .andExpect(jsonPath("$.[*].branchId").value(hasItem(DEFAULT_BRANCH_ID.intValue())))
            .andExpect(jsonPath("$.[*].branchName").value(hasItem(DEFAULT_BRANCH_NAME)))
            .andExpect(jsonPath("$.[*].inventoryLocationId").value(hasItem(DEFAULT_INVENTORY_LOCATION_ID.intValue())))
            .andExpect(jsonPath("$.[*].inventoryLocationName").value(hasItem(DEFAULT_INVENTORY_LOCATION_NAME)))
            .andExpect(jsonPath("$.[*].patientId").value(hasItem(DEFAULT_PATIENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].patientIdNo").value(hasItem(DEFAULT_PATIENT_ID_NO)))
            .andExpect(jsonPath("$.[*].patientFirstName").value(hasItem(DEFAULT_PATIENT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].patientMiddleName").value(hasItem(DEFAULT_PATIENT_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].patientLastName").value(hasItem(DEFAULT_PATIENT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].patientContact1").value(hasItem(DEFAULT_PATIENT_CONTACT_1)))
            .andExpect(jsonPath("$.[*].patientContact2").value(hasItem(DEFAULT_PATIENT_CONTACT_2)))
            .andExpect(jsonPath("$.[*].patientBillingAddressLine1").value(hasItem(DEFAULT_PATIENT_BILLING_ADDRESS_LINE_1)))
            .andExpect(jsonPath("$.[*].patientBillingAddressLine2").value(hasItem(DEFAULT_PATIENT_BILLING_ADDRESS_LINE_2)))
            .andExpect(jsonPath("$.[*].patientBillingAddressState").value(hasItem(DEFAULT_PATIENT_BILLING_ADDRESS_STATE)))
            .andExpect(jsonPath("$.[*].patientBillingAddressCity").value(hasItem(DEFAULT_PATIENT_BILLING_ADDRESS_CITY)))
            .andExpect(jsonPath("$.[*].patientBillingAddressZip").value(hasItem(DEFAULT_PATIENT_BILLING_ADDRESS_ZIP)))
            .andExpect(jsonPath("$.[*].patientDeliveyAddressLine1").value(hasItem(DEFAULT_PATIENT_DELIVEY_ADDRESS_LINE_1)))
            .andExpect(jsonPath("$.[*].patientDeliveyAddressLine2").value(hasItem(DEFAULT_PATIENT_DELIVEY_ADDRESS_LINE_2)))
            .andExpect(jsonPath("$.[*].patientDeliveyAddressState").value(hasItem(DEFAULT_PATIENT_DELIVEY_ADDRESS_STATE)))
            .andExpect(jsonPath("$.[*].patientDeliveyAddressCity").value(hasItem(DEFAULT_PATIENT_DELIVEY_ADDRESS_CITY)))
            .andExpect(jsonPath("$.[*].patientDeliveyAddressZip").value(hasItem(DEFAULT_PATIENT_DELIVEY_ADDRESS_ZIP)))
            .andExpect(
                jsonPath("$.[*].pickupExchangeScheduleDateTime").value(hasItem(DEFAULT_PICKUP_EXCHANGE_SCHEDULE_DATE_TIME.toString()))
            )
            .andExpect(jsonPath("$.[*].pickupExchangeActualDateTime").value(hasItem(DEFAULT_PICKUP_EXCHANGE_ACTUAL_DATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].pickupExchangeReason").value(hasItem(DEFAULT_PICKUP_EXCHANGE_REASON)))
            .andExpect(jsonPath("$.[*].pickupExchangeRequest").value(hasItem(DEFAULT_PICKUP_EXCHANGE_REQUEST)))
            .andExpect(jsonPath("$.[*].pickupExchangeNote").value(hasItem(DEFAULT_PICKUP_EXCHANGE_NOTE)))
            .andExpect(jsonPath("$.[*].pickupExchangeAgentIdNo").value(hasItem(DEFAULT_PICKUP_EXCHANGE_AGENT_ID_NO)))
            .andExpect(jsonPath("$.[*].pickupExchangeAgentName").value(hasItem(DEFAULT_PICKUP_EXCHANGE_AGENT_NAME)))
            .andExpect(jsonPath("$.[*].pickupExchangeDocumentId").value(hasItem(DEFAULT_PICKUP_EXCHANGE_DOCUMENT_ID)))
            .andExpect(jsonPath("$.[*].pickupExchangeDocumentNo").value(hasItem(DEFAULT_PICKUP_EXCHANGE_DOCUMENT_NO)))
            .andExpect(jsonPath("$.[*].pickupExchangeDocumentName").value(hasItem(DEFAULT_PICKUP_EXCHANGE_DOCUMENT_NAME)))
            .andExpect(jsonPath("$.[*].pickupExchangeStatus").value(hasItem(DEFAULT_PICKUP_EXCHANGE_STATUS)))
            .andExpect(jsonPath("$.[*].pickupExchangeComments").value(hasItem(DEFAULT_PICKUP_EXCHANGE_COMMENTS)))
            .andExpect(jsonPath("$.[*].isPatientSigned").value(hasItem(DEFAULT_IS_PATIENT_SIGNED)))
            .andExpect(jsonPath("$.[*].relationshipWithPatient").value(hasItem(DEFAULT_RELATIONSHIP_WITH_PATIENT)))
            .andExpect(jsonPath("$.[*].patientSignedDateTime").value(hasItem(DEFAULT_PATIENT_SIGNED_DATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].isAgentSigned").value(hasItem(DEFAULT_IS_AGENT_SIGNED)))
            .andExpect(jsonPath("$.[*].lastBillingDate").value(hasItem(DEFAULT_LAST_BILLING_DATE.toString())))
            .andExpect(jsonPath("$.[*].dateOfDeath").value(hasItem(DEFAULT_DATE_OF_DEATH.toString())))
            .andExpect(jsonPath("$.[*].pickupExchangeSupportingDocument1").value(hasItem(DEFAULT_PICKUP_EXCHANGE_SUPPORTING_DOCUMENT_1)))
            .andExpect(jsonPath("$.[*].pickupExchangeSupportingDocument2").value(hasItem(DEFAULT_PICKUP_EXCHANGE_SUPPORTING_DOCUMENT_2)))
            .andExpect(jsonPath("$.[*].patientNotsignedReason").value(hasItem(DEFAULT_PATIENT_NOTSIGNED_REASON)))
            .andExpect(jsonPath("$.[*].pickupExchangeJsonData").value(hasItem(DEFAULT_PICKUP_EXCHANGE_JSON_DATA)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].pickupExchangeUuid").value(hasItem(DEFAULT_PICKUP_EXCHANGE_UUID.toString())));
    }

    @Test
    @Transactional
    void getPickupExchange() throws Exception {
        // Initialize the database
        pickupExchangeRepository.saveAndFlush(pickupExchange);

        // Get the pickupExchange
        restPickupExchangeMockMvc
            .perform(get(ENTITY_API_URL_ID, pickupExchange.getPickupExchangeId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.pickupExchangeId").value(pickupExchange.getPickupExchangeId().intValue()))
            .andExpect(jsonPath("$.pickupExchangeNo").value(DEFAULT_PICKUP_EXCHANGE_NO))
            .andExpect(jsonPath("$.pickupExchangeType").value(DEFAULT_PICKUP_EXCHANGE_TYPE))
            .andExpect(jsonPath("$.soId").value(DEFAULT_SO_ID.intValue()))
            .andExpect(jsonPath("$.soNo").value(DEFAULT_SO_NO))
            .andExpect(jsonPath("$.branchId").value(DEFAULT_BRANCH_ID.intValue()))
            .andExpect(jsonPath("$.branchName").value(DEFAULT_BRANCH_NAME))
            .andExpect(jsonPath("$.inventoryLocationId").value(DEFAULT_INVENTORY_LOCATION_ID.intValue()))
            .andExpect(jsonPath("$.inventoryLocationName").value(DEFAULT_INVENTORY_LOCATION_NAME))
            .andExpect(jsonPath("$.patientId").value(DEFAULT_PATIENT_ID.intValue()))
            .andExpect(jsonPath("$.patientIdNo").value(DEFAULT_PATIENT_ID_NO))
            .andExpect(jsonPath("$.patientFirstName").value(DEFAULT_PATIENT_FIRST_NAME))
            .andExpect(jsonPath("$.patientMiddleName").value(DEFAULT_PATIENT_MIDDLE_NAME))
            .andExpect(jsonPath("$.patientLastName").value(DEFAULT_PATIENT_LAST_NAME))
            .andExpect(jsonPath("$.patientContact1").value(DEFAULT_PATIENT_CONTACT_1))
            .andExpect(jsonPath("$.patientContact2").value(DEFAULT_PATIENT_CONTACT_2))
            .andExpect(jsonPath("$.patientBillingAddressLine1").value(DEFAULT_PATIENT_BILLING_ADDRESS_LINE_1))
            .andExpect(jsonPath("$.patientBillingAddressLine2").value(DEFAULT_PATIENT_BILLING_ADDRESS_LINE_2))
            .andExpect(jsonPath("$.patientBillingAddressState").value(DEFAULT_PATIENT_BILLING_ADDRESS_STATE))
            .andExpect(jsonPath("$.patientBillingAddressCity").value(DEFAULT_PATIENT_BILLING_ADDRESS_CITY))
            .andExpect(jsonPath("$.patientBillingAddressZip").value(DEFAULT_PATIENT_BILLING_ADDRESS_ZIP))
            .andExpect(jsonPath("$.patientDeliveyAddressLine1").value(DEFAULT_PATIENT_DELIVEY_ADDRESS_LINE_1))
            .andExpect(jsonPath("$.patientDeliveyAddressLine2").value(DEFAULT_PATIENT_DELIVEY_ADDRESS_LINE_2))
            .andExpect(jsonPath("$.patientDeliveyAddressState").value(DEFAULT_PATIENT_DELIVEY_ADDRESS_STATE))
            .andExpect(jsonPath("$.patientDeliveyAddressCity").value(DEFAULT_PATIENT_DELIVEY_ADDRESS_CITY))
            .andExpect(jsonPath("$.patientDeliveyAddressZip").value(DEFAULT_PATIENT_DELIVEY_ADDRESS_ZIP))
            .andExpect(jsonPath("$.pickupExchangeScheduleDateTime").value(DEFAULT_PICKUP_EXCHANGE_SCHEDULE_DATE_TIME.toString()))
            .andExpect(jsonPath("$.pickupExchangeActualDateTime").value(DEFAULT_PICKUP_EXCHANGE_ACTUAL_DATE_TIME.toString()))
            .andExpect(jsonPath("$.pickupExchangeReason").value(DEFAULT_PICKUP_EXCHANGE_REASON))
            .andExpect(jsonPath("$.pickupExchangeRequest").value(DEFAULT_PICKUP_EXCHANGE_REQUEST))
            .andExpect(jsonPath("$.pickupExchangeNote").value(DEFAULT_PICKUP_EXCHANGE_NOTE))
            .andExpect(jsonPath("$.pickupExchangeAgentIdNo").value(DEFAULT_PICKUP_EXCHANGE_AGENT_ID_NO))
            .andExpect(jsonPath("$.pickupExchangeAgentName").value(DEFAULT_PICKUP_EXCHANGE_AGENT_NAME))
            .andExpect(jsonPath("$.pickupExchangeDocumentId").value(DEFAULT_PICKUP_EXCHANGE_DOCUMENT_ID))
            .andExpect(jsonPath("$.pickupExchangeDocumentNo").value(DEFAULT_PICKUP_EXCHANGE_DOCUMENT_NO))
            .andExpect(jsonPath("$.pickupExchangeDocumentName").value(DEFAULT_PICKUP_EXCHANGE_DOCUMENT_NAME))
            .andExpect(jsonPath("$.pickupExchangeStatus").value(DEFAULT_PICKUP_EXCHANGE_STATUS))
            .andExpect(jsonPath("$.pickupExchangeComments").value(DEFAULT_PICKUP_EXCHANGE_COMMENTS))
            .andExpect(jsonPath("$.isPatientSigned").value(DEFAULT_IS_PATIENT_SIGNED))
            .andExpect(jsonPath("$.relationshipWithPatient").value(DEFAULT_RELATIONSHIP_WITH_PATIENT))
            .andExpect(jsonPath("$.patientSignedDateTime").value(DEFAULT_PATIENT_SIGNED_DATE_TIME.toString()))
            .andExpect(jsonPath("$.isAgentSigned").value(DEFAULT_IS_AGENT_SIGNED))
            .andExpect(jsonPath("$.lastBillingDate").value(DEFAULT_LAST_BILLING_DATE.toString()))
            .andExpect(jsonPath("$.dateOfDeath").value(DEFAULT_DATE_OF_DEATH.toString()))
            .andExpect(jsonPath("$.pickupExchangeSupportingDocument1").value(DEFAULT_PICKUP_EXCHANGE_SUPPORTING_DOCUMENT_1))
            .andExpect(jsonPath("$.pickupExchangeSupportingDocument2").value(DEFAULT_PICKUP_EXCHANGE_SUPPORTING_DOCUMENT_2))
            .andExpect(jsonPath("$.patientNotsignedReason").value(DEFAULT_PATIENT_NOTSIGNED_REASON))
            .andExpect(jsonPath("$.pickupExchangeJsonData").value(DEFAULT_PICKUP_EXCHANGE_JSON_DATA))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.pickupExchangeUuid").value(DEFAULT_PICKUP_EXCHANGE_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingPickupExchange() throws Exception {
        // Get the pickupExchange
        restPickupExchangeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewPickupExchange() throws Exception {
        // Initialize the database
        pickupExchangeRepository.saveAndFlush(pickupExchange);

        int databaseSizeBeforeUpdate = pickupExchangeRepository.findAll().size();

        // Update the pickupExchange
        PickupExchange updatedPickupExchange = pickupExchangeRepository.findById(pickupExchange.getPickupExchangeId()).get();
        // Disconnect from session so that the updates on updatedPickupExchange are not directly saved in db
        em.detach(updatedPickupExchange);
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

        restPickupExchangeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, pickupExchangeDTO.getPickupExchangeId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pickupExchangeDTO))
            )
            .andExpect(status().isOk());

        // Validate the PickupExchange in the database
        List<PickupExchange> pickupExchangeList = pickupExchangeRepository.findAll();
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
    @Transactional
    void putNonExistingPickupExchange() throws Exception {
        int databaseSizeBeforeUpdate = pickupExchangeRepository.findAll().size();
        pickupExchange.setPickupExchangeId(count.incrementAndGet());

        // Create the PickupExchange
        PickupExchangeDTO pickupExchangeDTO = pickupExchangeMapper.toDto(pickupExchange);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPickupExchangeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, pickupExchangeDTO.getPickupExchangeId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pickupExchangeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PickupExchange in the database
        List<PickupExchange> pickupExchangeList = pickupExchangeRepository.findAll();
        assertThat(pickupExchangeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPickupExchange() throws Exception {
        int databaseSizeBeforeUpdate = pickupExchangeRepository.findAll().size();
        pickupExchange.setPickupExchangeId(count.incrementAndGet());

        // Create the PickupExchange
        PickupExchangeDTO pickupExchangeDTO = pickupExchangeMapper.toDto(pickupExchange);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPickupExchangeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pickupExchangeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PickupExchange in the database
        List<PickupExchange> pickupExchangeList = pickupExchangeRepository.findAll();
        assertThat(pickupExchangeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPickupExchange() throws Exception {
        int databaseSizeBeforeUpdate = pickupExchangeRepository.findAll().size();
        pickupExchange.setPickupExchangeId(count.incrementAndGet());

        // Create the PickupExchange
        PickupExchangeDTO pickupExchangeDTO = pickupExchangeMapper.toDto(pickupExchange);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPickupExchangeMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pickupExchangeDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PickupExchange in the database
        List<PickupExchange> pickupExchangeList = pickupExchangeRepository.findAll();
        assertThat(pickupExchangeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePickupExchangeWithPatch() throws Exception {
        // Initialize the database
        pickupExchangeRepository.saveAndFlush(pickupExchange);

        int databaseSizeBeforeUpdate = pickupExchangeRepository.findAll().size();

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

        restPickupExchangeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPickupExchange.getPickupExchangeId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPickupExchange))
            )
            .andExpect(status().isOk());

        // Validate the PickupExchange in the database
        List<PickupExchange> pickupExchangeList = pickupExchangeRepository.findAll();
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
    @Transactional
    void fullUpdatePickupExchangeWithPatch() throws Exception {
        // Initialize the database
        pickupExchangeRepository.saveAndFlush(pickupExchange);

        int databaseSizeBeforeUpdate = pickupExchangeRepository.findAll().size();

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

        restPickupExchangeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPickupExchange.getPickupExchangeId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPickupExchange))
            )
            .andExpect(status().isOk());

        // Validate the PickupExchange in the database
        List<PickupExchange> pickupExchangeList = pickupExchangeRepository.findAll();
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
    @Transactional
    void patchNonExistingPickupExchange() throws Exception {
        int databaseSizeBeforeUpdate = pickupExchangeRepository.findAll().size();
        pickupExchange.setPickupExchangeId(count.incrementAndGet());

        // Create the PickupExchange
        PickupExchangeDTO pickupExchangeDTO = pickupExchangeMapper.toDto(pickupExchange);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPickupExchangeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, pickupExchangeDTO.getPickupExchangeId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pickupExchangeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PickupExchange in the database
        List<PickupExchange> pickupExchangeList = pickupExchangeRepository.findAll();
        assertThat(pickupExchangeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPickupExchange() throws Exception {
        int databaseSizeBeforeUpdate = pickupExchangeRepository.findAll().size();
        pickupExchange.setPickupExchangeId(count.incrementAndGet());

        // Create the PickupExchange
        PickupExchangeDTO pickupExchangeDTO = pickupExchangeMapper.toDto(pickupExchange);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPickupExchangeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pickupExchangeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PickupExchange in the database
        List<PickupExchange> pickupExchangeList = pickupExchangeRepository.findAll();
        assertThat(pickupExchangeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPickupExchange() throws Exception {
        int databaseSizeBeforeUpdate = pickupExchangeRepository.findAll().size();
        pickupExchange.setPickupExchangeId(count.incrementAndGet());

        // Create the PickupExchange
        PickupExchangeDTO pickupExchangeDTO = pickupExchangeMapper.toDto(pickupExchange);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPickupExchangeMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pickupExchangeDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PickupExchange in the database
        List<PickupExchange> pickupExchangeList = pickupExchangeRepository.findAll();
        assertThat(pickupExchangeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePickupExchange() throws Exception {
        // Initialize the database
        pickupExchangeRepository.saveAndFlush(pickupExchange);

        int databaseSizeBeforeDelete = pickupExchangeRepository.findAll().size();

        // Delete the pickupExchange
        restPickupExchangeMockMvc
            .perform(delete(ENTITY_API_URL_ID, pickupExchange.getPickupExchangeId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PickupExchange> pickupExchangeList = pickupExchangeRepository.findAll();
        assertThat(pickupExchangeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
