package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.DeliveryDocumentsSignature;
import com.sunknowledge.dme.rcm.repository.DeliveryDocumentsSignatureRepository;
import com.sunknowledge.dme.rcm.service.dto.DeliveryDocumentsSignatureDTO;
import com.sunknowledge.dme.rcm.service.mapper.DeliveryDocumentsSignatureMapper;
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
 * Integration tests for the {@link DeliveryDocumentsSignatureResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DeliveryDocumentsSignatureResourceIT {

    private static final Long DEFAULT_DELIVERY_TICKET_ID = 1L;
    private static final Long UPDATED_DELIVERY_TICKET_ID = 2L;

    private static final String DEFAULT_DELIVERY_TICKET_NO = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_TICKET_NO = "BBBBBBBBBB";

    private static final String DEFAULT_CAREGIVER_SIGNATURE_FILE = "AAAAAAAAAA";
    private static final String UPDATED_CAREGIVER_SIGNATURE_FILE = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_SIGNATURE_FILE = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_SIGNATURE_FILE = "BBBBBBBBBB";

    private static final String DEFAULT_DRIVER_AGENT_SIGNATURE_FILE = "AAAAAAAAAA";
    private static final String UPDATED_DRIVER_AGENT_SIGNATURE_FILE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_TIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_TIME = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final UUID DEFAULT_DELIVERY_DOCUMENTS_SIGNATURE_UUID = UUID.randomUUID();
    private static final UUID UPDATED_DELIVERY_DOCUMENTS_SIGNATURE_UUID = UUID.randomUUID();

    private static final String DEFAULT_SIGNATURE_FILE_RESPONSE_JSON_DATA = "AAAAAAAAAA";
    private static final String UPDATED_SIGNATURE_FILE_RESPONSE_JSON_DATA = "BBBBBBBBBB";

    private static final String DEFAULT_CAREGIVER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CAREGIVER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CAREGIVER_RELATIONSHIP = "AAAAAAAAAA";
    private static final String UPDATED_CAREGIVER_RELATIONSHIP = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_REASONNOTSIGNED = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_REASONNOTSIGNED = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/delivery-documents-signatures";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{deliveryDocumentSignatureId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DeliveryDocumentsSignatureRepository deliveryDocumentsSignatureRepository;

    @Autowired
    private DeliveryDocumentsSignatureMapper deliveryDocumentsSignatureMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDeliveryDocumentsSignatureMockMvc;

    private DeliveryDocumentsSignature deliveryDocumentsSignature;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DeliveryDocumentsSignature createEntity(EntityManager em) {
        DeliveryDocumentsSignature deliveryDocumentsSignature = new DeliveryDocumentsSignature()
            .deliveryTicketId(DEFAULT_DELIVERY_TICKET_ID)
            .deliveryTicketNo(DEFAULT_DELIVERY_TICKET_NO)
            .caregiverSignatureFile(DEFAULT_CAREGIVER_SIGNATURE_FILE)
            .patientSignatureFile(DEFAULT_PATIENT_SIGNATURE_FILE)
            .driverAgentSignatureFile(DEFAULT_DRIVER_AGENT_SIGNATURE_FILE)
            .dateTime(DEFAULT_DATE_TIME)
            .status(DEFAULT_STATUS)
            .deliveryDocumentsSignatureUuid(DEFAULT_DELIVERY_DOCUMENTS_SIGNATURE_UUID)
            .signatureFileResponseJsonData(DEFAULT_SIGNATURE_FILE_RESPONSE_JSON_DATA)
            .caregiverName(DEFAULT_CAREGIVER_NAME)
            .caregiverRelationship(DEFAULT_CAREGIVER_RELATIONSHIP)
            .patientReasonnotsigned(DEFAULT_PATIENT_REASONNOTSIGNED);
        return deliveryDocumentsSignature;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DeliveryDocumentsSignature createUpdatedEntity(EntityManager em) {
        DeliveryDocumentsSignature deliveryDocumentsSignature = new DeliveryDocumentsSignature()
            .deliveryTicketId(UPDATED_DELIVERY_TICKET_ID)
            .deliveryTicketNo(UPDATED_DELIVERY_TICKET_NO)
            .caregiverSignatureFile(UPDATED_CAREGIVER_SIGNATURE_FILE)
            .patientSignatureFile(UPDATED_PATIENT_SIGNATURE_FILE)
            .driverAgentSignatureFile(UPDATED_DRIVER_AGENT_SIGNATURE_FILE)
            .dateTime(UPDATED_DATE_TIME)
            .status(UPDATED_STATUS)
            .deliveryDocumentsSignatureUuid(UPDATED_DELIVERY_DOCUMENTS_SIGNATURE_UUID)
            .signatureFileResponseJsonData(UPDATED_SIGNATURE_FILE_RESPONSE_JSON_DATA)
            .caregiverName(UPDATED_CAREGIVER_NAME)
            .caregiverRelationship(UPDATED_CAREGIVER_RELATIONSHIP)
            .patientReasonnotsigned(UPDATED_PATIENT_REASONNOTSIGNED);
        return deliveryDocumentsSignature;
    }

    @BeforeEach
    public void initTest() {
        deliveryDocumentsSignature = createEntity(em);
    }

    @Test
    @Transactional
    void createDeliveryDocumentsSignature() throws Exception {
        int databaseSizeBeforeCreate = deliveryDocumentsSignatureRepository.findAll().size();
        // Create the DeliveryDocumentsSignature
        DeliveryDocumentsSignatureDTO deliveryDocumentsSignatureDTO = deliveryDocumentsSignatureMapper.toDto(deliveryDocumentsSignature);
        restDeliveryDocumentsSignatureMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(deliveryDocumentsSignatureDTO))
            )
            .andExpect(status().isCreated());

        // Validate the DeliveryDocumentsSignature in the database
        List<DeliveryDocumentsSignature> deliveryDocumentsSignatureList = deliveryDocumentsSignatureRepository.findAll();
        assertThat(deliveryDocumentsSignatureList).hasSize(databaseSizeBeforeCreate + 1);
        DeliveryDocumentsSignature testDeliveryDocumentsSignature = deliveryDocumentsSignatureList.get(
            deliveryDocumentsSignatureList.size() - 1
        );
        assertThat(testDeliveryDocumentsSignature.getDeliveryTicketId()).isEqualTo(DEFAULT_DELIVERY_TICKET_ID);
        assertThat(testDeliveryDocumentsSignature.getDeliveryTicketNo()).isEqualTo(DEFAULT_DELIVERY_TICKET_NO);
        assertThat(testDeliveryDocumentsSignature.getCaregiverSignatureFile()).isEqualTo(DEFAULT_CAREGIVER_SIGNATURE_FILE);
        assertThat(testDeliveryDocumentsSignature.getPatientSignatureFile()).isEqualTo(DEFAULT_PATIENT_SIGNATURE_FILE);
        assertThat(testDeliveryDocumentsSignature.getDriverAgentSignatureFile()).isEqualTo(DEFAULT_DRIVER_AGENT_SIGNATURE_FILE);
        assertThat(testDeliveryDocumentsSignature.getDateTime()).isEqualTo(DEFAULT_DATE_TIME);
        assertThat(testDeliveryDocumentsSignature.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testDeliveryDocumentsSignature.getDeliveryDocumentsSignatureUuid()).isEqualTo(DEFAULT_DELIVERY_DOCUMENTS_SIGNATURE_UUID);
        assertThat(testDeliveryDocumentsSignature.getSignatureFileResponseJsonData()).isEqualTo(DEFAULT_SIGNATURE_FILE_RESPONSE_JSON_DATA);
        assertThat(testDeliveryDocumentsSignature.getCaregiverName()).isEqualTo(DEFAULT_CAREGIVER_NAME);
        assertThat(testDeliveryDocumentsSignature.getCaregiverRelationship()).isEqualTo(DEFAULT_CAREGIVER_RELATIONSHIP);
        assertThat(testDeliveryDocumentsSignature.getPatientReasonnotsigned()).isEqualTo(DEFAULT_PATIENT_REASONNOTSIGNED);
    }

    @Test
    @Transactional
    void createDeliveryDocumentsSignatureWithExistingId() throws Exception {
        // Create the DeliveryDocumentsSignature with an existing ID
        deliveryDocumentsSignature.setDeliveryDocumentSignatureId(1L);
        DeliveryDocumentsSignatureDTO deliveryDocumentsSignatureDTO = deliveryDocumentsSignatureMapper.toDto(deliveryDocumentsSignature);

        int databaseSizeBeforeCreate = deliveryDocumentsSignatureRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDeliveryDocumentsSignatureMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(deliveryDocumentsSignatureDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeliveryDocumentsSignature in the database
        List<DeliveryDocumentsSignature> deliveryDocumentsSignatureList = deliveryDocumentsSignatureRepository.findAll();
        assertThat(deliveryDocumentsSignatureList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDeliveryDocumentsSignatures() throws Exception {
        // Initialize the database
        deliveryDocumentsSignatureRepository.saveAndFlush(deliveryDocumentsSignature);

        // Get all the deliveryDocumentsSignatureList
        restDeliveryDocumentsSignatureMockMvc
            .perform(get(ENTITY_API_URL + "?sort=deliveryDocumentSignatureId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].deliveryDocumentSignatureId")
                    .value(hasItem(deliveryDocumentsSignature.getDeliveryDocumentSignatureId().intValue()))
            )
            .andExpect(jsonPath("$.[*].deliveryTicketId").value(hasItem(DEFAULT_DELIVERY_TICKET_ID.intValue())))
            .andExpect(jsonPath("$.[*].deliveryTicketNo").value(hasItem(DEFAULT_DELIVERY_TICKET_NO)))
            .andExpect(jsonPath("$.[*].caregiverSignatureFile").value(hasItem(DEFAULT_CAREGIVER_SIGNATURE_FILE)))
            .andExpect(jsonPath("$.[*].patientSignatureFile").value(hasItem(DEFAULT_PATIENT_SIGNATURE_FILE)))
            .andExpect(jsonPath("$.[*].driverAgentSignatureFile").value(hasItem(DEFAULT_DRIVER_AGENT_SIGNATURE_FILE)))
            .andExpect(jsonPath("$.[*].dateTime").value(hasItem(DEFAULT_DATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(
                jsonPath("$.[*].deliveryDocumentsSignatureUuid").value(hasItem(DEFAULT_DELIVERY_DOCUMENTS_SIGNATURE_UUID.toString()))
            )
            .andExpect(jsonPath("$.[*].signatureFileResponseJsonData").value(hasItem(DEFAULT_SIGNATURE_FILE_RESPONSE_JSON_DATA)))
            .andExpect(jsonPath("$.[*].caregiverName").value(hasItem(DEFAULT_CAREGIVER_NAME)))
            .andExpect(jsonPath("$.[*].caregiverRelationship").value(hasItem(DEFAULT_CAREGIVER_RELATIONSHIP)))
            .andExpect(jsonPath("$.[*].patientReasonnotsigned").value(hasItem(DEFAULT_PATIENT_REASONNOTSIGNED)));
    }

    @Test
    @Transactional
    void getDeliveryDocumentsSignature() throws Exception {
        // Initialize the database
        deliveryDocumentsSignatureRepository.saveAndFlush(deliveryDocumentsSignature);

        // Get the deliveryDocumentsSignature
        restDeliveryDocumentsSignatureMockMvc
            .perform(get(ENTITY_API_URL_ID, deliveryDocumentsSignature.getDeliveryDocumentSignatureId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.deliveryDocumentSignatureId").value(deliveryDocumentsSignature.getDeliveryDocumentSignatureId().intValue())
            )
            .andExpect(jsonPath("$.deliveryTicketId").value(DEFAULT_DELIVERY_TICKET_ID.intValue()))
            .andExpect(jsonPath("$.deliveryTicketNo").value(DEFAULT_DELIVERY_TICKET_NO))
            .andExpect(jsonPath("$.caregiverSignatureFile").value(DEFAULT_CAREGIVER_SIGNATURE_FILE))
            .andExpect(jsonPath("$.patientSignatureFile").value(DEFAULT_PATIENT_SIGNATURE_FILE))
            .andExpect(jsonPath("$.driverAgentSignatureFile").value(DEFAULT_DRIVER_AGENT_SIGNATURE_FILE))
            .andExpect(jsonPath("$.dateTime").value(DEFAULT_DATE_TIME.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.deliveryDocumentsSignatureUuid").value(DEFAULT_DELIVERY_DOCUMENTS_SIGNATURE_UUID.toString()))
            .andExpect(jsonPath("$.signatureFileResponseJsonData").value(DEFAULT_SIGNATURE_FILE_RESPONSE_JSON_DATA))
            .andExpect(jsonPath("$.caregiverName").value(DEFAULT_CAREGIVER_NAME))
            .andExpect(jsonPath("$.caregiverRelationship").value(DEFAULT_CAREGIVER_RELATIONSHIP))
            .andExpect(jsonPath("$.patientReasonnotsigned").value(DEFAULT_PATIENT_REASONNOTSIGNED));
    }

    @Test
    @Transactional
    void getNonExistingDeliveryDocumentsSignature() throws Exception {
        // Get the deliveryDocumentsSignature
        restDeliveryDocumentsSignatureMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewDeliveryDocumentsSignature() throws Exception {
        // Initialize the database
        deliveryDocumentsSignatureRepository.saveAndFlush(deliveryDocumentsSignature);

        int databaseSizeBeforeUpdate = deliveryDocumentsSignatureRepository.findAll().size();

        // Update the deliveryDocumentsSignature
        DeliveryDocumentsSignature updatedDeliveryDocumentsSignature = deliveryDocumentsSignatureRepository
            .findById(deliveryDocumentsSignature.getDeliveryDocumentSignatureId())
            .get();
        // Disconnect from session so that the updates on updatedDeliveryDocumentsSignature are not directly saved in db
        em.detach(updatedDeliveryDocumentsSignature);
        updatedDeliveryDocumentsSignature
            .deliveryTicketId(UPDATED_DELIVERY_TICKET_ID)
            .deliveryTicketNo(UPDATED_DELIVERY_TICKET_NO)
            .caregiverSignatureFile(UPDATED_CAREGIVER_SIGNATURE_FILE)
            .patientSignatureFile(UPDATED_PATIENT_SIGNATURE_FILE)
            .driverAgentSignatureFile(UPDATED_DRIVER_AGENT_SIGNATURE_FILE)
            .dateTime(UPDATED_DATE_TIME)
            .status(UPDATED_STATUS)
            .deliveryDocumentsSignatureUuid(UPDATED_DELIVERY_DOCUMENTS_SIGNATURE_UUID)
            .signatureFileResponseJsonData(UPDATED_SIGNATURE_FILE_RESPONSE_JSON_DATA)
            .caregiverName(UPDATED_CAREGIVER_NAME)
            .caregiverRelationship(UPDATED_CAREGIVER_RELATIONSHIP)
            .patientReasonnotsigned(UPDATED_PATIENT_REASONNOTSIGNED);
        DeliveryDocumentsSignatureDTO deliveryDocumentsSignatureDTO = deliveryDocumentsSignatureMapper.toDto(
            updatedDeliveryDocumentsSignature
        );

        restDeliveryDocumentsSignatureMockMvc
            .perform(
                put(ENTITY_API_URL_ID, deliveryDocumentsSignatureDTO.getDeliveryDocumentSignatureId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(deliveryDocumentsSignatureDTO))
            )
            .andExpect(status().isOk());

        // Validate the DeliveryDocumentsSignature in the database
        List<DeliveryDocumentsSignature> deliveryDocumentsSignatureList = deliveryDocumentsSignatureRepository.findAll();
        assertThat(deliveryDocumentsSignatureList).hasSize(databaseSizeBeforeUpdate);
        DeliveryDocumentsSignature testDeliveryDocumentsSignature = deliveryDocumentsSignatureList.get(
            deliveryDocumentsSignatureList.size() - 1
        );
        assertThat(testDeliveryDocumentsSignature.getDeliveryTicketId()).isEqualTo(UPDATED_DELIVERY_TICKET_ID);
        assertThat(testDeliveryDocumentsSignature.getDeliveryTicketNo()).isEqualTo(UPDATED_DELIVERY_TICKET_NO);
        assertThat(testDeliveryDocumentsSignature.getCaregiverSignatureFile()).isEqualTo(UPDATED_CAREGIVER_SIGNATURE_FILE);
        assertThat(testDeliveryDocumentsSignature.getPatientSignatureFile()).isEqualTo(UPDATED_PATIENT_SIGNATURE_FILE);
        assertThat(testDeliveryDocumentsSignature.getDriverAgentSignatureFile()).isEqualTo(UPDATED_DRIVER_AGENT_SIGNATURE_FILE);
        assertThat(testDeliveryDocumentsSignature.getDateTime()).isEqualTo(UPDATED_DATE_TIME);
        assertThat(testDeliveryDocumentsSignature.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDeliveryDocumentsSignature.getDeliveryDocumentsSignatureUuid()).isEqualTo(UPDATED_DELIVERY_DOCUMENTS_SIGNATURE_UUID);
        assertThat(testDeliveryDocumentsSignature.getSignatureFileResponseJsonData()).isEqualTo(UPDATED_SIGNATURE_FILE_RESPONSE_JSON_DATA);
        assertThat(testDeliveryDocumentsSignature.getCaregiverName()).isEqualTo(UPDATED_CAREGIVER_NAME);
        assertThat(testDeliveryDocumentsSignature.getCaregiverRelationship()).isEqualTo(UPDATED_CAREGIVER_RELATIONSHIP);
        assertThat(testDeliveryDocumentsSignature.getPatientReasonnotsigned()).isEqualTo(UPDATED_PATIENT_REASONNOTSIGNED);
    }

    @Test
    @Transactional
    void putNonExistingDeliveryDocumentsSignature() throws Exception {
        int databaseSizeBeforeUpdate = deliveryDocumentsSignatureRepository.findAll().size();
        deliveryDocumentsSignature.setDeliveryDocumentSignatureId(count.incrementAndGet());

        // Create the DeliveryDocumentsSignature
        DeliveryDocumentsSignatureDTO deliveryDocumentsSignatureDTO = deliveryDocumentsSignatureMapper.toDto(deliveryDocumentsSignature);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDeliveryDocumentsSignatureMockMvc
            .perform(
                put(ENTITY_API_URL_ID, deliveryDocumentsSignatureDTO.getDeliveryDocumentSignatureId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(deliveryDocumentsSignatureDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeliveryDocumentsSignature in the database
        List<DeliveryDocumentsSignature> deliveryDocumentsSignatureList = deliveryDocumentsSignatureRepository.findAll();
        assertThat(deliveryDocumentsSignatureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDeliveryDocumentsSignature() throws Exception {
        int databaseSizeBeforeUpdate = deliveryDocumentsSignatureRepository.findAll().size();
        deliveryDocumentsSignature.setDeliveryDocumentSignatureId(count.incrementAndGet());

        // Create the DeliveryDocumentsSignature
        DeliveryDocumentsSignatureDTO deliveryDocumentsSignatureDTO = deliveryDocumentsSignatureMapper.toDto(deliveryDocumentsSignature);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeliveryDocumentsSignatureMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(deliveryDocumentsSignatureDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeliveryDocumentsSignature in the database
        List<DeliveryDocumentsSignature> deliveryDocumentsSignatureList = deliveryDocumentsSignatureRepository.findAll();
        assertThat(deliveryDocumentsSignatureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDeliveryDocumentsSignature() throws Exception {
        int databaseSizeBeforeUpdate = deliveryDocumentsSignatureRepository.findAll().size();
        deliveryDocumentsSignature.setDeliveryDocumentSignatureId(count.incrementAndGet());

        // Create the DeliveryDocumentsSignature
        DeliveryDocumentsSignatureDTO deliveryDocumentsSignatureDTO = deliveryDocumentsSignatureMapper.toDto(deliveryDocumentsSignature);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeliveryDocumentsSignatureMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(deliveryDocumentsSignatureDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DeliveryDocumentsSignature in the database
        List<DeliveryDocumentsSignature> deliveryDocumentsSignatureList = deliveryDocumentsSignatureRepository.findAll();
        assertThat(deliveryDocumentsSignatureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDeliveryDocumentsSignatureWithPatch() throws Exception {
        // Initialize the database
        deliveryDocumentsSignatureRepository.saveAndFlush(deliveryDocumentsSignature);

        int databaseSizeBeforeUpdate = deliveryDocumentsSignatureRepository.findAll().size();

        // Update the deliveryDocumentsSignature using partial update
        DeliveryDocumentsSignature partialUpdatedDeliveryDocumentsSignature = new DeliveryDocumentsSignature();
        partialUpdatedDeliveryDocumentsSignature.setDeliveryDocumentSignatureId(
            deliveryDocumentsSignature.getDeliveryDocumentSignatureId()
        );

        partialUpdatedDeliveryDocumentsSignature
            .deliveryTicketId(UPDATED_DELIVERY_TICKET_ID)
            .caregiverSignatureFile(UPDATED_CAREGIVER_SIGNATURE_FILE)
            .patientSignatureFile(UPDATED_PATIENT_SIGNATURE_FILE)
            .status(UPDATED_STATUS);

        restDeliveryDocumentsSignatureMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDeliveryDocumentsSignature.getDeliveryDocumentSignatureId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDeliveryDocumentsSignature))
            )
            .andExpect(status().isOk());

        // Validate the DeliveryDocumentsSignature in the database
        List<DeliveryDocumentsSignature> deliveryDocumentsSignatureList = deliveryDocumentsSignatureRepository.findAll();
        assertThat(deliveryDocumentsSignatureList).hasSize(databaseSizeBeforeUpdate);
        DeliveryDocumentsSignature testDeliveryDocumentsSignature = deliveryDocumentsSignatureList.get(
            deliveryDocumentsSignatureList.size() - 1
        );
        assertThat(testDeliveryDocumentsSignature.getDeliveryTicketId()).isEqualTo(UPDATED_DELIVERY_TICKET_ID);
        assertThat(testDeliveryDocumentsSignature.getDeliveryTicketNo()).isEqualTo(DEFAULT_DELIVERY_TICKET_NO);
        assertThat(testDeliveryDocumentsSignature.getCaregiverSignatureFile()).isEqualTo(UPDATED_CAREGIVER_SIGNATURE_FILE);
        assertThat(testDeliveryDocumentsSignature.getPatientSignatureFile()).isEqualTo(UPDATED_PATIENT_SIGNATURE_FILE);
        assertThat(testDeliveryDocumentsSignature.getDriverAgentSignatureFile()).isEqualTo(DEFAULT_DRIVER_AGENT_SIGNATURE_FILE);
        assertThat(testDeliveryDocumentsSignature.getDateTime()).isEqualTo(DEFAULT_DATE_TIME);
        assertThat(testDeliveryDocumentsSignature.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDeliveryDocumentsSignature.getDeliveryDocumentsSignatureUuid()).isEqualTo(DEFAULT_DELIVERY_DOCUMENTS_SIGNATURE_UUID);
        assertThat(testDeliveryDocumentsSignature.getSignatureFileResponseJsonData()).isEqualTo(DEFAULT_SIGNATURE_FILE_RESPONSE_JSON_DATA);
        assertThat(testDeliveryDocumentsSignature.getCaregiverName()).isEqualTo(DEFAULT_CAREGIVER_NAME);
        assertThat(testDeliveryDocumentsSignature.getCaregiverRelationship()).isEqualTo(DEFAULT_CAREGIVER_RELATIONSHIP);
        assertThat(testDeliveryDocumentsSignature.getPatientReasonnotsigned()).isEqualTo(DEFAULT_PATIENT_REASONNOTSIGNED);
    }

    @Test
    @Transactional
    void fullUpdateDeliveryDocumentsSignatureWithPatch() throws Exception {
        // Initialize the database
        deliveryDocumentsSignatureRepository.saveAndFlush(deliveryDocumentsSignature);

        int databaseSizeBeforeUpdate = deliveryDocumentsSignatureRepository.findAll().size();

        // Update the deliveryDocumentsSignature using partial update
        DeliveryDocumentsSignature partialUpdatedDeliveryDocumentsSignature = new DeliveryDocumentsSignature();
        partialUpdatedDeliveryDocumentsSignature.setDeliveryDocumentSignatureId(
            deliveryDocumentsSignature.getDeliveryDocumentSignatureId()
        );

        partialUpdatedDeliveryDocumentsSignature
            .deliveryTicketId(UPDATED_DELIVERY_TICKET_ID)
            .deliveryTicketNo(UPDATED_DELIVERY_TICKET_NO)
            .caregiverSignatureFile(UPDATED_CAREGIVER_SIGNATURE_FILE)
            .patientSignatureFile(UPDATED_PATIENT_SIGNATURE_FILE)
            .driverAgentSignatureFile(UPDATED_DRIVER_AGENT_SIGNATURE_FILE)
            .dateTime(UPDATED_DATE_TIME)
            .status(UPDATED_STATUS)
            .deliveryDocumentsSignatureUuid(UPDATED_DELIVERY_DOCUMENTS_SIGNATURE_UUID)
            .signatureFileResponseJsonData(UPDATED_SIGNATURE_FILE_RESPONSE_JSON_DATA)
            .caregiverName(UPDATED_CAREGIVER_NAME)
            .caregiverRelationship(UPDATED_CAREGIVER_RELATIONSHIP)
            .patientReasonnotsigned(UPDATED_PATIENT_REASONNOTSIGNED);

        restDeliveryDocumentsSignatureMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDeliveryDocumentsSignature.getDeliveryDocumentSignatureId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDeliveryDocumentsSignature))
            )
            .andExpect(status().isOk());

        // Validate the DeliveryDocumentsSignature in the database
        List<DeliveryDocumentsSignature> deliveryDocumentsSignatureList = deliveryDocumentsSignatureRepository.findAll();
        assertThat(deliveryDocumentsSignatureList).hasSize(databaseSizeBeforeUpdate);
        DeliveryDocumentsSignature testDeliveryDocumentsSignature = deliveryDocumentsSignatureList.get(
            deliveryDocumentsSignatureList.size() - 1
        );
        assertThat(testDeliveryDocumentsSignature.getDeliveryTicketId()).isEqualTo(UPDATED_DELIVERY_TICKET_ID);
        assertThat(testDeliveryDocumentsSignature.getDeliveryTicketNo()).isEqualTo(UPDATED_DELIVERY_TICKET_NO);
        assertThat(testDeliveryDocumentsSignature.getCaregiverSignatureFile()).isEqualTo(UPDATED_CAREGIVER_SIGNATURE_FILE);
        assertThat(testDeliveryDocumentsSignature.getPatientSignatureFile()).isEqualTo(UPDATED_PATIENT_SIGNATURE_FILE);
        assertThat(testDeliveryDocumentsSignature.getDriverAgentSignatureFile()).isEqualTo(UPDATED_DRIVER_AGENT_SIGNATURE_FILE);
        assertThat(testDeliveryDocumentsSignature.getDateTime()).isEqualTo(UPDATED_DATE_TIME);
        assertThat(testDeliveryDocumentsSignature.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDeliveryDocumentsSignature.getDeliveryDocumentsSignatureUuid()).isEqualTo(UPDATED_DELIVERY_DOCUMENTS_SIGNATURE_UUID);
        assertThat(testDeliveryDocumentsSignature.getSignatureFileResponseJsonData()).isEqualTo(UPDATED_SIGNATURE_FILE_RESPONSE_JSON_DATA);
        assertThat(testDeliveryDocumentsSignature.getCaregiverName()).isEqualTo(UPDATED_CAREGIVER_NAME);
        assertThat(testDeliveryDocumentsSignature.getCaregiverRelationship()).isEqualTo(UPDATED_CAREGIVER_RELATIONSHIP);
        assertThat(testDeliveryDocumentsSignature.getPatientReasonnotsigned()).isEqualTo(UPDATED_PATIENT_REASONNOTSIGNED);
    }

    @Test
    @Transactional
    void patchNonExistingDeliveryDocumentsSignature() throws Exception {
        int databaseSizeBeforeUpdate = deliveryDocumentsSignatureRepository.findAll().size();
        deliveryDocumentsSignature.setDeliveryDocumentSignatureId(count.incrementAndGet());

        // Create the DeliveryDocumentsSignature
        DeliveryDocumentsSignatureDTO deliveryDocumentsSignatureDTO = deliveryDocumentsSignatureMapper.toDto(deliveryDocumentsSignature);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDeliveryDocumentsSignatureMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, deliveryDocumentsSignatureDTO.getDeliveryDocumentSignatureId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(deliveryDocumentsSignatureDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeliveryDocumentsSignature in the database
        List<DeliveryDocumentsSignature> deliveryDocumentsSignatureList = deliveryDocumentsSignatureRepository.findAll();
        assertThat(deliveryDocumentsSignatureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDeliveryDocumentsSignature() throws Exception {
        int databaseSizeBeforeUpdate = deliveryDocumentsSignatureRepository.findAll().size();
        deliveryDocumentsSignature.setDeliveryDocumentSignatureId(count.incrementAndGet());

        // Create the DeliveryDocumentsSignature
        DeliveryDocumentsSignatureDTO deliveryDocumentsSignatureDTO = deliveryDocumentsSignatureMapper.toDto(deliveryDocumentsSignature);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeliveryDocumentsSignatureMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(deliveryDocumentsSignatureDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeliveryDocumentsSignature in the database
        List<DeliveryDocumentsSignature> deliveryDocumentsSignatureList = deliveryDocumentsSignatureRepository.findAll();
        assertThat(deliveryDocumentsSignatureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDeliveryDocumentsSignature() throws Exception {
        int databaseSizeBeforeUpdate = deliveryDocumentsSignatureRepository.findAll().size();
        deliveryDocumentsSignature.setDeliveryDocumentSignatureId(count.incrementAndGet());

        // Create the DeliveryDocumentsSignature
        DeliveryDocumentsSignatureDTO deliveryDocumentsSignatureDTO = deliveryDocumentsSignatureMapper.toDto(deliveryDocumentsSignature);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeliveryDocumentsSignatureMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(deliveryDocumentsSignatureDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DeliveryDocumentsSignature in the database
        List<DeliveryDocumentsSignature> deliveryDocumentsSignatureList = deliveryDocumentsSignatureRepository.findAll();
        assertThat(deliveryDocumentsSignatureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDeliveryDocumentsSignature() throws Exception {
        // Initialize the database
        deliveryDocumentsSignatureRepository.saveAndFlush(deliveryDocumentsSignature);

        int databaseSizeBeforeDelete = deliveryDocumentsSignatureRepository.findAll().size();

        // Delete the deliveryDocumentsSignature
        restDeliveryDocumentsSignatureMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, deliveryDocumentsSignature.getDeliveryDocumentSignatureId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DeliveryDocumentsSignature> deliveryDocumentsSignatureList = deliveryDocumentsSignatureRepository.findAll();
        assertThat(deliveryDocumentsSignatureList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
