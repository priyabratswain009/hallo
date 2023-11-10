package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.DeliveryPatientCommunications;
import com.sunknowledge.dme.rcm.repository.DeliveryPatientCommunicationsRepository;
import com.sunknowledge.dme.rcm.service.dto.DeliveryPatientCommunicationsDTO;
import com.sunknowledge.dme.rcm.service.mapper.DeliveryPatientCommunicationsMapper;
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
 * Integration tests for the {@link DeliveryPatientCommunicationsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DeliveryPatientCommunicationsResourceIT {

    private static final Long DEFAULT_DELIVERY_TICKET_ID = 1L;
    private static final Long UPDATED_DELIVERY_TICKET_ID = 2L;

    private static final String DEFAULT_DELIVERY_TICKET_NO = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_TICKET_NO = "BBBBBBBBBB";

    private static final String DEFAULT_REASON_FOR_COMMUNICATION = "AAAAAAAAAA";
    private static final String UPDATED_REASON_FOR_COMMUNICATION = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_PHONE_NO = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_PHONE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PERSON_SPOKEN_TO_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PERSON_SPOKEN_TO_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PERSON_SPOKEN_TO_RELATION_WITH_PATIENT = "AAAAAAAAAA";
    private static final String UPDATED_PERSON_SPOKEN_TO_RELATION_WITH_PATIENT = "BBBBBBBBBB";

    private static final String DEFAULT_COMMUNICATION_SUMMERY = "AAAAAAAAAA";
    private static final String UPDATED_COMMUNICATION_SUMMERY = "BBBBBBBBBB";

    private static final String DEFAULT_CSR_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CSR_NAME = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_DELIVERY_PATIENT_COMMUNICATIONS_UUID = UUID.randomUUID();
    private static final UUID UPDATED_DELIVERY_PATIENT_COMMUNICATIONS_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/delivery-patient-communications";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{deliveryPatientCommunicationsId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DeliveryPatientCommunicationsRepository deliveryPatientCommunicationsRepository;

    @Autowired
    private DeliveryPatientCommunicationsMapper deliveryPatientCommunicationsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDeliveryPatientCommunicationsMockMvc;

    private DeliveryPatientCommunications deliveryPatientCommunications;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DeliveryPatientCommunications createEntity(EntityManager em) {
        DeliveryPatientCommunications deliveryPatientCommunications = new DeliveryPatientCommunications()
            .deliveryTicketId(DEFAULT_DELIVERY_TICKET_ID)
            .deliveryTicketNo(DEFAULT_DELIVERY_TICKET_NO)
            .reasonForCommunication(DEFAULT_REASON_FOR_COMMUNICATION)
            .patientPhoneNo(DEFAULT_PATIENT_PHONE_NO)
            .personSpokenToName(DEFAULT_PERSON_SPOKEN_TO_NAME)
            .personSpokenToRelationWithPatient(DEFAULT_PERSON_SPOKEN_TO_RELATION_WITH_PATIENT)
            .communicationSummery(DEFAULT_COMMUNICATION_SUMMERY)
            .csrName(DEFAULT_CSR_NAME)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .deliveryPatientCommunicationsUuid(DEFAULT_DELIVERY_PATIENT_COMMUNICATIONS_UUID);
        return deliveryPatientCommunications;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DeliveryPatientCommunications createUpdatedEntity(EntityManager em) {
        DeliveryPatientCommunications deliveryPatientCommunications = new DeliveryPatientCommunications()
            .deliveryTicketId(UPDATED_DELIVERY_TICKET_ID)
            .deliveryTicketNo(UPDATED_DELIVERY_TICKET_NO)
            .reasonForCommunication(UPDATED_REASON_FOR_COMMUNICATION)
            .patientPhoneNo(UPDATED_PATIENT_PHONE_NO)
            .personSpokenToName(UPDATED_PERSON_SPOKEN_TO_NAME)
            .personSpokenToRelationWithPatient(UPDATED_PERSON_SPOKEN_TO_RELATION_WITH_PATIENT)
            .communicationSummery(UPDATED_COMMUNICATION_SUMMERY)
            .csrName(UPDATED_CSR_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .deliveryPatientCommunicationsUuid(UPDATED_DELIVERY_PATIENT_COMMUNICATIONS_UUID);
        return deliveryPatientCommunications;
    }

    @BeforeEach
    public void initTest() {
        deliveryPatientCommunications = createEntity(em);
    }

    @Test
    @Transactional
    void createDeliveryPatientCommunications() throws Exception {
        int databaseSizeBeforeCreate = deliveryPatientCommunicationsRepository.findAll().size();
        // Create the DeliveryPatientCommunications
        DeliveryPatientCommunicationsDTO deliveryPatientCommunicationsDTO = deliveryPatientCommunicationsMapper.toDto(
            deliveryPatientCommunications
        );
        restDeliveryPatientCommunicationsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(deliveryPatientCommunicationsDTO))
            )
            .andExpect(status().isCreated());

        // Validate the DeliveryPatientCommunications in the database
        List<DeliveryPatientCommunications> deliveryPatientCommunicationsList = deliveryPatientCommunicationsRepository.findAll();
        assertThat(deliveryPatientCommunicationsList).hasSize(databaseSizeBeforeCreate + 1);
        DeliveryPatientCommunications testDeliveryPatientCommunications = deliveryPatientCommunicationsList.get(
            deliveryPatientCommunicationsList.size() - 1
        );
        assertThat(testDeliveryPatientCommunications.getDeliveryTicketId()).isEqualTo(DEFAULT_DELIVERY_TICKET_ID);
        assertThat(testDeliveryPatientCommunications.getDeliveryTicketNo()).isEqualTo(DEFAULT_DELIVERY_TICKET_NO);
        assertThat(testDeliveryPatientCommunications.getReasonForCommunication()).isEqualTo(DEFAULT_REASON_FOR_COMMUNICATION);
        assertThat(testDeliveryPatientCommunications.getPatientPhoneNo()).isEqualTo(DEFAULT_PATIENT_PHONE_NO);
        assertThat(testDeliveryPatientCommunications.getPersonSpokenToName()).isEqualTo(DEFAULT_PERSON_SPOKEN_TO_NAME);
        assertThat(testDeliveryPatientCommunications.getPersonSpokenToRelationWithPatient())
            .isEqualTo(DEFAULT_PERSON_SPOKEN_TO_RELATION_WITH_PATIENT);
        assertThat(testDeliveryPatientCommunications.getCommunicationSummery()).isEqualTo(DEFAULT_COMMUNICATION_SUMMERY);
        assertThat(testDeliveryPatientCommunications.getCsrName()).isEqualTo(DEFAULT_CSR_NAME);
        assertThat(testDeliveryPatientCommunications.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testDeliveryPatientCommunications.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testDeliveryPatientCommunications.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testDeliveryPatientCommunications.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testDeliveryPatientCommunications.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testDeliveryPatientCommunications.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testDeliveryPatientCommunications.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testDeliveryPatientCommunications.getDeliveryPatientCommunicationsUuid())
            .isEqualTo(DEFAULT_DELIVERY_PATIENT_COMMUNICATIONS_UUID);
    }

    @Test
    @Transactional
    void createDeliveryPatientCommunicationsWithExistingId() throws Exception {
        // Create the DeliveryPatientCommunications with an existing ID
        deliveryPatientCommunications.setDeliveryPatientCommunicationsId(1L);
        DeliveryPatientCommunicationsDTO deliveryPatientCommunicationsDTO = deliveryPatientCommunicationsMapper.toDto(
            deliveryPatientCommunications
        );

        int databaseSizeBeforeCreate = deliveryPatientCommunicationsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDeliveryPatientCommunicationsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(deliveryPatientCommunicationsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeliveryPatientCommunications in the database
        List<DeliveryPatientCommunications> deliveryPatientCommunicationsList = deliveryPatientCommunicationsRepository.findAll();
        assertThat(deliveryPatientCommunicationsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDeliveryPatientCommunications() throws Exception {
        // Initialize the database
        deliveryPatientCommunicationsRepository.saveAndFlush(deliveryPatientCommunications);

        // Get all the deliveryPatientCommunicationsList
        restDeliveryPatientCommunicationsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=deliveryPatientCommunicationsId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].deliveryPatientCommunicationsId")
                    .value(hasItem(deliveryPatientCommunications.getDeliveryPatientCommunicationsId().intValue()))
            )
            .andExpect(jsonPath("$.[*].deliveryTicketId").value(hasItem(DEFAULT_DELIVERY_TICKET_ID.intValue())))
            .andExpect(jsonPath("$.[*].deliveryTicketNo").value(hasItem(DEFAULT_DELIVERY_TICKET_NO)))
            .andExpect(jsonPath("$.[*].reasonForCommunication").value(hasItem(DEFAULT_REASON_FOR_COMMUNICATION)))
            .andExpect(jsonPath("$.[*].patientPhoneNo").value(hasItem(DEFAULT_PATIENT_PHONE_NO)))
            .andExpect(jsonPath("$.[*].personSpokenToName").value(hasItem(DEFAULT_PERSON_SPOKEN_TO_NAME)))
            .andExpect(jsonPath("$.[*].personSpokenToRelationWithPatient").value(hasItem(DEFAULT_PERSON_SPOKEN_TO_RELATION_WITH_PATIENT)))
            .andExpect(jsonPath("$.[*].communicationSummery").value(hasItem(DEFAULT_COMMUNICATION_SUMMERY)))
            .andExpect(jsonPath("$.[*].csrName").value(hasItem(DEFAULT_CSR_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(
                jsonPath("$.[*].deliveryPatientCommunicationsUuid").value(hasItem(DEFAULT_DELIVERY_PATIENT_COMMUNICATIONS_UUID.toString()))
            );
    }

    @Test
    @Transactional
    void getDeliveryPatientCommunications() throws Exception {
        // Initialize the database
        deliveryPatientCommunicationsRepository.saveAndFlush(deliveryPatientCommunications);

        // Get the deliveryPatientCommunications
        restDeliveryPatientCommunicationsMockMvc
            .perform(get(ENTITY_API_URL_ID, deliveryPatientCommunications.getDeliveryPatientCommunicationsId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.deliveryPatientCommunicationsId")
                    .value(deliveryPatientCommunications.getDeliveryPatientCommunicationsId().intValue())
            )
            .andExpect(jsonPath("$.deliveryTicketId").value(DEFAULT_DELIVERY_TICKET_ID.intValue()))
            .andExpect(jsonPath("$.deliveryTicketNo").value(DEFAULT_DELIVERY_TICKET_NO))
            .andExpect(jsonPath("$.reasonForCommunication").value(DEFAULT_REASON_FOR_COMMUNICATION))
            .andExpect(jsonPath("$.patientPhoneNo").value(DEFAULT_PATIENT_PHONE_NO))
            .andExpect(jsonPath("$.personSpokenToName").value(DEFAULT_PERSON_SPOKEN_TO_NAME))
            .andExpect(jsonPath("$.personSpokenToRelationWithPatient").value(DEFAULT_PERSON_SPOKEN_TO_RELATION_WITH_PATIENT))
            .andExpect(jsonPath("$.communicationSummery").value(DEFAULT_COMMUNICATION_SUMMERY))
            .andExpect(jsonPath("$.csrName").value(DEFAULT_CSR_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.deliveryPatientCommunicationsUuid").value(DEFAULT_DELIVERY_PATIENT_COMMUNICATIONS_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingDeliveryPatientCommunications() throws Exception {
        // Get the deliveryPatientCommunications
        restDeliveryPatientCommunicationsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewDeliveryPatientCommunications() throws Exception {
        // Initialize the database
        deliveryPatientCommunicationsRepository.saveAndFlush(deliveryPatientCommunications);

        int databaseSizeBeforeUpdate = deliveryPatientCommunicationsRepository.findAll().size();

        // Update the deliveryPatientCommunications
        DeliveryPatientCommunications updatedDeliveryPatientCommunications = deliveryPatientCommunicationsRepository
            .findById(deliveryPatientCommunications.getDeliveryPatientCommunicationsId())
            .get();
        // Disconnect from session so that the updates on updatedDeliveryPatientCommunications are not directly saved in db
        em.detach(updatedDeliveryPatientCommunications);
        updatedDeliveryPatientCommunications
            .deliveryTicketId(UPDATED_DELIVERY_TICKET_ID)
            .deliveryTicketNo(UPDATED_DELIVERY_TICKET_NO)
            .reasonForCommunication(UPDATED_REASON_FOR_COMMUNICATION)
            .patientPhoneNo(UPDATED_PATIENT_PHONE_NO)
            .personSpokenToName(UPDATED_PERSON_SPOKEN_TO_NAME)
            .personSpokenToRelationWithPatient(UPDATED_PERSON_SPOKEN_TO_RELATION_WITH_PATIENT)
            .communicationSummery(UPDATED_COMMUNICATION_SUMMERY)
            .csrName(UPDATED_CSR_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .deliveryPatientCommunicationsUuid(UPDATED_DELIVERY_PATIENT_COMMUNICATIONS_UUID);
        DeliveryPatientCommunicationsDTO deliveryPatientCommunicationsDTO = deliveryPatientCommunicationsMapper.toDto(
            updatedDeliveryPatientCommunications
        );

        restDeliveryPatientCommunicationsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, deliveryPatientCommunicationsDTO.getDeliveryPatientCommunicationsId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(deliveryPatientCommunicationsDTO))
            )
            .andExpect(status().isOk());

        // Validate the DeliveryPatientCommunications in the database
        List<DeliveryPatientCommunications> deliveryPatientCommunicationsList = deliveryPatientCommunicationsRepository.findAll();
        assertThat(deliveryPatientCommunicationsList).hasSize(databaseSizeBeforeUpdate);
        DeliveryPatientCommunications testDeliveryPatientCommunications = deliveryPatientCommunicationsList.get(
            deliveryPatientCommunicationsList.size() - 1
        );
        assertThat(testDeliveryPatientCommunications.getDeliveryTicketId()).isEqualTo(UPDATED_DELIVERY_TICKET_ID);
        assertThat(testDeliveryPatientCommunications.getDeliveryTicketNo()).isEqualTo(UPDATED_DELIVERY_TICKET_NO);
        assertThat(testDeliveryPatientCommunications.getReasonForCommunication()).isEqualTo(UPDATED_REASON_FOR_COMMUNICATION);
        assertThat(testDeliveryPatientCommunications.getPatientPhoneNo()).isEqualTo(UPDATED_PATIENT_PHONE_NO);
        assertThat(testDeliveryPatientCommunications.getPersonSpokenToName()).isEqualTo(UPDATED_PERSON_SPOKEN_TO_NAME);
        assertThat(testDeliveryPatientCommunications.getPersonSpokenToRelationWithPatient())
            .isEqualTo(UPDATED_PERSON_SPOKEN_TO_RELATION_WITH_PATIENT);
        assertThat(testDeliveryPatientCommunications.getCommunicationSummery()).isEqualTo(UPDATED_COMMUNICATION_SUMMERY);
        assertThat(testDeliveryPatientCommunications.getCsrName()).isEqualTo(UPDATED_CSR_NAME);
        assertThat(testDeliveryPatientCommunications.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDeliveryPatientCommunications.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testDeliveryPatientCommunications.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testDeliveryPatientCommunications.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testDeliveryPatientCommunications.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testDeliveryPatientCommunications.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testDeliveryPatientCommunications.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testDeliveryPatientCommunications.getDeliveryPatientCommunicationsUuid())
            .isEqualTo(UPDATED_DELIVERY_PATIENT_COMMUNICATIONS_UUID);
    }

    @Test
    @Transactional
    void putNonExistingDeliveryPatientCommunications() throws Exception {
        int databaseSizeBeforeUpdate = deliveryPatientCommunicationsRepository.findAll().size();
        deliveryPatientCommunications.setDeliveryPatientCommunicationsId(count.incrementAndGet());

        // Create the DeliveryPatientCommunications
        DeliveryPatientCommunicationsDTO deliveryPatientCommunicationsDTO = deliveryPatientCommunicationsMapper.toDto(
            deliveryPatientCommunications
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDeliveryPatientCommunicationsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, deliveryPatientCommunicationsDTO.getDeliveryPatientCommunicationsId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(deliveryPatientCommunicationsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeliveryPatientCommunications in the database
        List<DeliveryPatientCommunications> deliveryPatientCommunicationsList = deliveryPatientCommunicationsRepository.findAll();
        assertThat(deliveryPatientCommunicationsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDeliveryPatientCommunications() throws Exception {
        int databaseSizeBeforeUpdate = deliveryPatientCommunicationsRepository.findAll().size();
        deliveryPatientCommunications.setDeliveryPatientCommunicationsId(count.incrementAndGet());

        // Create the DeliveryPatientCommunications
        DeliveryPatientCommunicationsDTO deliveryPatientCommunicationsDTO = deliveryPatientCommunicationsMapper.toDto(
            deliveryPatientCommunications
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeliveryPatientCommunicationsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(deliveryPatientCommunicationsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeliveryPatientCommunications in the database
        List<DeliveryPatientCommunications> deliveryPatientCommunicationsList = deliveryPatientCommunicationsRepository.findAll();
        assertThat(deliveryPatientCommunicationsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDeliveryPatientCommunications() throws Exception {
        int databaseSizeBeforeUpdate = deliveryPatientCommunicationsRepository.findAll().size();
        deliveryPatientCommunications.setDeliveryPatientCommunicationsId(count.incrementAndGet());

        // Create the DeliveryPatientCommunications
        DeliveryPatientCommunicationsDTO deliveryPatientCommunicationsDTO = deliveryPatientCommunicationsMapper.toDto(
            deliveryPatientCommunications
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeliveryPatientCommunicationsMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(deliveryPatientCommunicationsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DeliveryPatientCommunications in the database
        List<DeliveryPatientCommunications> deliveryPatientCommunicationsList = deliveryPatientCommunicationsRepository.findAll();
        assertThat(deliveryPatientCommunicationsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDeliveryPatientCommunicationsWithPatch() throws Exception {
        // Initialize the database
        deliveryPatientCommunicationsRepository.saveAndFlush(deliveryPatientCommunications);

        int databaseSizeBeforeUpdate = deliveryPatientCommunicationsRepository.findAll().size();

        // Update the deliveryPatientCommunications using partial update
        DeliveryPatientCommunications partialUpdatedDeliveryPatientCommunications = new DeliveryPatientCommunications();
        partialUpdatedDeliveryPatientCommunications.setDeliveryPatientCommunicationsId(
            deliveryPatientCommunications.getDeliveryPatientCommunicationsId()
        );

        partialUpdatedDeliveryPatientCommunications
            .deliveryTicketNo(UPDATED_DELIVERY_TICKET_NO)
            .reasonForCommunication(UPDATED_REASON_FOR_COMMUNICATION)
            .communicationSummery(UPDATED_COMMUNICATION_SUMMERY)
            .csrName(UPDATED_CSR_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE);

        restDeliveryPatientCommunicationsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDeliveryPatientCommunications.getDeliveryPatientCommunicationsId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDeliveryPatientCommunications))
            )
            .andExpect(status().isOk());

        // Validate the DeliveryPatientCommunications in the database
        List<DeliveryPatientCommunications> deliveryPatientCommunicationsList = deliveryPatientCommunicationsRepository.findAll();
        assertThat(deliveryPatientCommunicationsList).hasSize(databaseSizeBeforeUpdate);
        DeliveryPatientCommunications testDeliveryPatientCommunications = deliveryPatientCommunicationsList.get(
            deliveryPatientCommunicationsList.size() - 1
        );
        assertThat(testDeliveryPatientCommunications.getDeliveryTicketId()).isEqualTo(DEFAULT_DELIVERY_TICKET_ID);
        assertThat(testDeliveryPatientCommunications.getDeliveryTicketNo()).isEqualTo(UPDATED_DELIVERY_TICKET_NO);
        assertThat(testDeliveryPatientCommunications.getReasonForCommunication()).isEqualTo(UPDATED_REASON_FOR_COMMUNICATION);
        assertThat(testDeliveryPatientCommunications.getPatientPhoneNo()).isEqualTo(DEFAULT_PATIENT_PHONE_NO);
        assertThat(testDeliveryPatientCommunications.getPersonSpokenToName()).isEqualTo(DEFAULT_PERSON_SPOKEN_TO_NAME);
        assertThat(testDeliveryPatientCommunications.getPersonSpokenToRelationWithPatient())
            .isEqualTo(DEFAULT_PERSON_SPOKEN_TO_RELATION_WITH_PATIENT);
        assertThat(testDeliveryPatientCommunications.getCommunicationSummery()).isEqualTo(UPDATED_COMMUNICATION_SUMMERY);
        assertThat(testDeliveryPatientCommunications.getCsrName()).isEqualTo(UPDATED_CSR_NAME);
        assertThat(testDeliveryPatientCommunications.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDeliveryPatientCommunications.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testDeliveryPatientCommunications.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testDeliveryPatientCommunications.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testDeliveryPatientCommunications.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testDeliveryPatientCommunications.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testDeliveryPatientCommunications.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testDeliveryPatientCommunications.getDeliveryPatientCommunicationsUuid())
            .isEqualTo(DEFAULT_DELIVERY_PATIENT_COMMUNICATIONS_UUID);
    }

    @Test
    @Transactional
    void fullUpdateDeliveryPatientCommunicationsWithPatch() throws Exception {
        // Initialize the database
        deliveryPatientCommunicationsRepository.saveAndFlush(deliveryPatientCommunications);

        int databaseSizeBeforeUpdate = deliveryPatientCommunicationsRepository.findAll().size();

        // Update the deliveryPatientCommunications using partial update
        DeliveryPatientCommunications partialUpdatedDeliveryPatientCommunications = new DeliveryPatientCommunications();
        partialUpdatedDeliveryPatientCommunications.setDeliveryPatientCommunicationsId(
            deliveryPatientCommunications.getDeliveryPatientCommunicationsId()
        );

        partialUpdatedDeliveryPatientCommunications
            .deliveryTicketId(UPDATED_DELIVERY_TICKET_ID)
            .deliveryTicketNo(UPDATED_DELIVERY_TICKET_NO)
            .reasonForCommunication(UPDATED_REASON_FOR_COMMUNICATION)
            .patientPhoneNo(UPDATED_PATIENT_PHONE_NO)
            .personSpokenToName(UPDATED_PERSON_SPOKEN_TO_NAME)
            .personSpokenToRelationWithPatient(UPDATED_PERSON_SPOKEN_TO_RELATION_WITH_PATIENT)
            .communicationSummery(UPDATED_COMMUNICATION_SUMMERY)
            .csrName(UPDATED_CSR_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .deliveryPatientCommunicationsUuid(UPDATED_DELIVERY_PATIENT_COMMUNICATIONS_UUID);

        restDeliveryPatientCommunicationsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDeliveryPatientCommunications.getDeliveryPatientCommunicationsId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDeliveryPatientCommunications))
            )
            .andExpect(status().isOk());

        // Validate the DeliveryPatientCommunications in the database
        List<DeliveryPatientCommunications> deliveryPatientCommunicationsList = deliveryPatientCommunicationsRepository.findAll();
        assertThat(deliveryPatientCommunicationsList).hasSize(databaseSizeBeforeUpdate);
        DeliveryPatientCommunications testDeliveryPatientCommunications = deliveryPatientCommunicationsList.get(
            deliveryPatientCommunicationsList.size() - 1
        );
        assertThat(testDeliveryPatientCommunications.getDeliveryTicketId()).isEqualTo(UPDATED_DELIVERY_TICKET_ID);
        assertThat(testDeliveryPatientCommunications.getDeliveryTicketNo()).isEqualTo(UPDATED_DELIVERY_TICKET_NO);
        assertThat(testDeliveryPatientCommunications.getReasonForCommunication()).isEqualTo(UPDATED_REASON_FOR_COMMUNICATION);
        assertThat(testDeliveryPatientCommunications.getPatientPhoneNo()).isEqualTo(UPDATED_PATIENT_PHONE_NO);
        assertThat(testDeliveryPatientCommunications.getPersonSpokenToName()).isEqualTo(UPDATED_PERSON_SPOKEN_TO_NAME);
        assertThat(testDeliveryPatientCommunications.getPersonSpokenToRelationWithPatient())
            .isEqualTo(UPDATED_PERSON_SPOKEN_TO_RELATION_WITH_PATIENT);
        assertThat(testDeliveryPatientCommunications.getCommunicationSummery()).isEqualTo(UPDATED_COMMUNICATION_SUMMERY);
        assertThat(testDeliveryPatientCommunications.getCsrName()).isEqualTo(UPDATED_CSR_NAME);
        assertThat(testDeliveryPatientCommunications.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDeliveryPatientCommunications.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testDeliveryPatientCommunications.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testDeliveryPatientCommunications.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testDeliveryPatientCommunications.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testDeliveryPatientCommunications.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testDeliveryPatientCommunications.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testDeliveryPatientCommunications.getDeliveryPatientCommunicationsUuid())
            .isEqualTo(UPDATED_DELIVERY_PATIENT_COMMUNICATIONS_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingDeliveryPatientCommunications() throws Exception {
        int databaseSizeBeforeUpdate = deliveryPatientCommunicationsRepository.findAll().size();
        deliveryPatientCommunications.setDeliveryPatientCommunicationsId(count.incrementAndGet());

        // Create the DeliveryPatientCommunications
        DeliveryPatientCommunicationsDTO deliveryPatientCommunicationsDTO = deliveryPatientCommunicationsMapper.toDto(
            deliveryPatientCommunications
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDeliveryPatientCommunicationsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, deliveryPatientCommunicationsDTO.getDeliveryPatientCommunicationsId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(deliveryPatientCommunicationsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeliveryPatientCommunications in the database
        List<DeliveryPatientCommunications> deliveryPatientCommunicationsList = deliveryPatientCommunicationsRepository.findAll();
        assertThat(deliveryPatientCommunicationsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDeliveryPatientCommunications() throws Exception {
        int databaseSizeBeforeUpdate = deliveryPatientCommunicationsRepository.findAll().size();
        deliveryPatientCommunications.setDeliveryPatientCommunicationsId(count.incrementAndGet());

        // Create the DeliveryPatientCommunications
        DeliveryPatientCommunicationsDTO deliveryPatientCommunicationsDTO = deliveryPatientCommunicationsMapper.toDto(
            deliveryPatientCommunications
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeliveryPatientCommunicationsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(deliveryPatientCommunicationsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeliveryPatientCommunications in the database
        List<DeliveryPatientCommunications> deliveryPatientCommunicationsList = deliveryPatientCommunicationsRepository.findAll();
        assertThat(deliveryPatientCommunicationsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDeliveryPatientCommunications() throws Exception {
        int databaseSizeBeforeUpdate = deliveryPatientCommunicationsRepository.findAll().size();
        deliveryPatientCommunications.setDeliveryPatientCommunicationsId(count.incrementAndGet());

        // Create the DeliveryPatientCommunications
        DeliveryPatientCommunicationsDTO deliveryPatientCommunicationsDTO = deliveryPatientCommunicationsMapper.toDto(
            deliveryPatientCommunications
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeliveryPatientCommunicationsMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(deliveryPatientCommunicationsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DeliveryPatientCommunications in the database
        List<DeliveryPatientCommunications> deliveryPatientCommunicationsList = deliveryPatientCommunicationsRepository.findAll();
        assertThat(deliveryPatientCommunicationsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDeliveryPatientCommunications() throws Exception {
        // Initialize the database
        deliveryPatientCommunicationsRepository.saveAndFlush(deliveryPatientCommunications);

        int databaseSizeBeforeDelete = deliveryPatientCommunicationsRepository.findAll().size();

        // Delete the deliveryPatientCommunications
        restDeliveryPatientCommunicationsMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, deliveryPatientCommunications.getDeliveryPatientCommunicationsId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DeliveryPatientCommunications> deliveryPatientCommunicationsList = deliveryPatientCommunicationsRepository.findAll();
        assertThat(deliveryPatientCommunicationsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
