package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.DeliveryDocuments;
import com.sunknowledge.dme.rcm.repository.DeliveryDocumentsRepository;
import com.sunknowledge.dme.rcm.service.dto.DeliveryDocumentsDTO;
import com.sunknowledge.dme.rcm.service.mapper.DeliveryDocumentsMapper;
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
 * Integration tests for the {@link DeliveryDocumentsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DeliveryDocumentsResourceIT {

    private static final Long DEFAULT_DELIVERY_TICKET_ID = 1L;
    private static final Long UPDATED_DELIVERY_TICKET_ID = 2L;

    private static final String DEFAULT_DELIVERY_TICKET_NO = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_TICKET_NO = "BBBBBBBBBB";

    private static final Long DEFAULT_SO_ID = 1L;
    private static final Long UPDATED_SO_ID = 2L;

    private static final String DEFAULT_SO_NO = "AAAAAAAAAA";
    private static final String UPDATED_SO_NO = "BBBBBBBBBB";

    private static final Long DEFAULT_HCPCS_DOCTYPE_ID = 1L;
    private static final Long UPDATED_HCPCS_DOCTYPE_ID = 2L;

    private static final String DEFAULT_DOC_PATIENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DOC_PATIENT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DOCUMENT_PATIENT_FILE_PATH = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENT_PATIENT_FILE_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_IS_PATIENT_SIGNED = "AAAAAAAAAA";
    private static final String UPDATED_IS_PATIENT_SIGNED = "BBBBBBBBBB";

    private static final String DEFAULT_IS_CAREGIVER_SIGNED = "AAAAAAAAAA";
    private static final String UPDATED_IS_CAREGIVER_SIGNED = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_SIGNED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SIGNED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_IS_DELIVERY_AGENT_SIGNED = "AAAAAAAAAA";
    private static final String UPDATED_IS_DELIVERY_AGENT_SIGNED = "BBBBBBBBBB";

    private static final String DEFAULT_IS_SCANNED_UPLOADED = "AAAAAAAAAA";
    private static final String UPDATED_IS_SCANNED_UPLOADED = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_DELIVERY_DOCUMENTS_UUID = UUID.randomUUID();
    private static final UUID UPDATED_DELIVERY_DOCUMENTS_UUID = UUID.randomUUID();

    private static final String DEFAULT_RESPONSE_JSON_DATA = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_JSON_DATA = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/delivery-documents";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{deliveryDocId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DeliveryDocumentsRepository deliveryDocumentsRepository;

    @Autowired
    private DeliveryDocumentsMapper deliveryDocumentsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDeliveryDocumentsMockMvc;

    private DeliveryDocuments deliveryDocuments;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DeliveryDocuments createEntity(EntityManager em) {
        DeliveryDocuments deliveryDocuments = new DeliveryDocuments()
            .deliveryTicketId(DEFAULT_DELIVERY_TICKET_ID)
            .deliveryTicketNo(DEFAULT_DELIVERY_TICKET_NO)
            .soId(DEFAULT_SO_ID)
            .soNo(DEFAULT_SO_NO)
            .hcpcsDoctypeId(DEFAULT_HCPCS_DOCTYPE_ID)
            .docPatientName(DEFAULT_DOC_PATIENT_NAME)
            .documentPatientFilePath(DEFAULT_DOCUMENT_PATIENT_FILE_PATH)
            .isPatientSigned(DEFAULT_IS_PATIENT_SIGNED)
            .isCaregiverSigned(DEFAULT_IS_CAREGIVER_SIGNED)
            .signedDate(DEFAULT_SIGNED_DATE)
            .isDeliveryAgentSigned(DEFAULT_IS_DELIVERY_AGENT_SIGNED)
            .isScannedUploaded(DEFAULT_IS_SCANNED_UPLOADED)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .deliveryDocumentsUuid(DEFAULT_DELIVERY_DOCUMENTS_UUID)
            .responseJsonData(DEFAULT_RESPONSE_JSON_DATA);
        return deliveryDocuments;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DeliveryDocuments createUpdatedEntity(EntityManager em) {
        DeliveryDocuments deliveryDocuments = new DeliveryDocuments()
            .deliveryTicketId(UPDATED_DELIVERY_TICKET_ID)
            .deliveryTicketNo(UPDATED_DELIVERY_TICKET_NO)
            .soId(UPDATED_SO_ID)
            .soNo(UPDATED_SO_NO)
            .hcpcsDoctypeId(UPDATED_HCPCS_DOCTYPE_ID)
            .docPatientName(UPDATED_DOC_PATIENT_NAME)
            .documentPatientFilePath(UPDATED_DOCUMENT_PATIENT_FILE_PATH)
            .isPatientSigned(UPDATED_IS_PATIENT_SIGNED)
            .isCaregiverSigned(UPDATED_IS_CAREGIVER_SIGNED)
            .signedDate(UPDATED_SIGNED_DATE)
            .isDeliveryAgentSigned(UPDATED_IS_DELIVERY_AGENT_SIGNED)
            .isScannedUploaded(UPDATED_IS_SCANNED_UPLOADED)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .deliveryDocumentsUuid(UPDATED_DELIVERY_DOCUMENTS_UUID)
            .responseJsonData(UPDATED_RESPONSE_JSON_DATA);
        return deliveryDocuments;
    }

    @BeforeEach
    public void initTest() {
        deliveryDocuments = createEntity(em);
    }

    @Test
    @Transactional
    void createDeliveryDocuments() throws Exception {
        int databaseSizeBeforeCreate = deliveryDocumentsRepository.findAll().size();
        // Create the DeliveryDocuments
        DeliveryDocumentsDTO deliveryDocumentsDTO = deliveryDocumentsMapper.toDto(deliveryDocuments);
        restDeliveryDocumentsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(deliveryDocumentsDTO))
            )
            .andExpect(status().isCreated());

        // Validate the DeliveryDocuments in the database
        List<DeliveryDocuments> deliveryDocumentsList = deliveryDocumentsRepository.findAll();
        assertThat(deliveryDocumentsList).hasSize(databaseSizeBeforeCreate + 1);
        DeliveryDocuments testDeliveryDocuments = deliveryDocumentsList.get(deliveryDocumentsList.size() - 1);
        assertThat(testDeliveryDocuments.getDeliveryTicketId()).isEqualTo(DEFAULT_DELIVERY_TICKET_ID);
        assertThat(testDeliveryDocuments.getDeliveryTicketNo()).isEqualTo(DEFAULT_DELIVERY_TICKET_NO);
        assertThat(testDeliveryDocuments.getSoId()).isEqualTo(DEFAULT_SO_ID);
        assertThat(testDeliveryDocuments.getSoNo()).isEqualTo(DEFAULT_SO_NO);
        assertThat(testDeliveryDocuments.getHcpcsDoctypeId()).isEqualTo(DEFAULT_HCPCS_DOCTYPE_ID);
        assertThat(testDeliveryDocuments.getDocPatientName()).isEqualTo(DEFAULT_DOC_PATIENT_NAME);
        assertThat(testDeliveryDocuments.getDocumentPatientFilePath()).isEqualTo(DEFAULT_DOCUMENT_PATIENT_FILE_PATH);
        assertThat(testDeliveryDocuments.getIsPatientSigned()).isEqualTo(DEFAULT_IS_PATIENT_SIGNED);
        assertThat(testDeliveryDocuments.getIsCaregiverSigned()).isEqualTo(DEFAULT_IS_CAREGIVER_SIGNED);
        assertThat(testDeliveryDocuments.getSignedDate()).isEqualTo(DEFAULT_SIGNED_DATE);
        assertThat(testDeliveryDocuments.getIsDeliveryAgentSigned()).isEqualTo(DEFAULT_IS_DELIVERY_AGENT_SIGNED);
        assertThat(testDeliveryDocuments.getIsScannedUploaded()).isEqualTo(DEFAULT_IS_SCANNED_UPLOADED);
        assertThat(testDeliveryDocuments.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testDeliveryDocuments.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testDeliveryDocuments.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testDeliveryDocuments.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testDeliveryDocuments.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testDeliveryDocuments.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testDeliveryDocuments.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testDeliveryDocuments.getDeliveryDocumentsUuid()).isEqualTo(DEFAULT_DELIVERY_DOCUMENTS_UUID);
        assertThat(testDeliveryDocuments.getResponseJsonData()).isEqualTo(DEFAULT_RESPONSE_JSON_DATA);
    }

    @Test
    @Transactional
    void createDeliveryDocumentsWithExistingId() throws Exception {
        // Create the DeliveryDocuments with an existing ID
        deliveryDocuments.setDeliveryDocId(1L);
        DeliveryDocumentsDTO deliveryDocumentsDTO = deliveryDocumentsMapper.toDto(deliveryDocuments);

        int databaseSizeBeforeCreate = deliveryDocumentsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDeliveryDocumentsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(deliveryDocumentsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeliveryDocuments in the database
        List<DeliveryDocuments> deliveryDocumentsList = deliveryDocumentsRepository.findAll();
        assertThat(deliveryDocumentsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDeliveryDocuments() throws Exception {
        // Initialize the database
        deliveryDocumentsRepository.saveAndFlush(deliveryDocuments);

        // Get all the deliveryDocumentsList
        restDeliveryDocumentsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=deliveryDocId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].deliveryDocId").value(hasItem(deliveryDocuments.getDeliveryDocId().intValue())))
            .andExpect(jsonPath("$.[*].deliveryTicketId").value(hasItem(DEFAULT_DELIVERY_TICKET_ID.intValue())))
            .andExpect(jsonPath("$.[*].deliveryTicketNo").value(hasItem(DEFAULT_DELIVERY_TICKET_NO)))
            .andExpect(jsonPath("$.[*].soId").value(hasItem(DEFAULT_SO_ID.intValue())))
            .andExpect(jsonPath("$.[*].soNo").value(hasItem(DEFAULT_SO_NO)))
            .andExpect(jsonPath("$.[*].hcpcsDoctypeId").value(hasItem(DEFAULT_HCPCS_DOCTYPE_ID.intValue())))
            .andExpect(jsonPath("$.[*].docPatientName").value(hasItem(DEFAULT_DOC_PATIENT_NAME)))
            .andExpect(jsonPath("$.[*].documentPatientFilePath").value(hasItem(DEFAULT_DOCUMENT_PATIENT_FILE_PATH)))
            .andExpect(jsonPath("$.[*].isPatientSigned").value(hasItem(DEFAULT_IS_PATIENT_SIGNED)))
            .andExpect(jsonPath("$.[*].isCaregiverSigned").value(hasItem(DEFAULT_IS_CAREGIVER_SIGNED)))
            .andExpect(jsonPath("$.[*].signedDate").value(hasItem(DEFAULT_SIGNED_DATE.toString())))
            .andExpect(jsonPath("$.[*].isDeliveryAgentSigned").value(hasItem(DEFAULT_IS_DELIVERY_AGENT_SIGNED)))
            .andExpect(jsonPath("$.[*].isScannedUploaded").value(hasItem(DEFAULT_IS_SCANNED_UPLOADED)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].deliveryDocumentsUuid").value(hasItem(DEFAULT_DELIVERY_DOCUMENTS_UUID.toString())))
            .andExpect(jsonPath("$.[*].responseJsonData").value(hasItem(DEFAULT_RESPONSE_JSON_DATA)));
    }

    @Test
    @Transactional
    void getDeliveryDocuments() throws Exception {
        // Initialize the database
        deliveryDocumentsRepository.saveAndFlush(deliveryDocuments);

        // Get the deliveryDocuments
        restDeliveryDocumentsMockMvc
            .perform(get(ENTITY_API_URL_ID, deliveryDocuments.getDeliveryDocId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.deliveryDocId").value(deliveryDocuments.getDeliveryDocId().intValue()))
            .andExpect(jsonPath("$.deliveryTicketId").value(DEFAULT_DELIVERY_TICKET_ID.intValue()))
            .andExpect(jsonPath("$.deliveryTicketNo").value(DEFAULT_DELIVERY_TICKET_NO))
            .andExpect(jsonPath("$.soId").value(DEFAULT_SO_ID.intValue()))
            .andExpect(jsonPath("$.soNo").value(DEFAULT_SO_NO))
            .andExpect(jsonPath("$.hcpcsDoctypeId").value(DEFAULT_HCPCS_DOCTYPE_ID.intValue()))
            .andExpect(jsonPath("$.docPatientName").value(DEFAULT_DOC_PATIENT_NAME))
            .andExpect(jsonPath("$.documentPatientFilePath").value(DEFAULT_DOCUMENT_PATIENT_FILE_PATH))
            .andExpect(jsonPath("$.isPatientSigned").value(DEFAULT_IS_PATIENT_SIGNED))
            .andExpect(jsonPath("$.isCaregiverSigned").value(DEFAULT_IS_CAREGIVER_SIGNED))
            .andExpect(jsonPath("$.signedDate").value(DEFAULT_SIGNED_DATE.toString()))
            .andExpect(jsonPath("$.isDeliveryAgentSigned").value(DEFAULT_IS_DELIVERY_AGENT_SIGNED))
            .andExpect(jsonPath("$.isScannedUploaded").value(DEFAULT_IS_SCANNED_UPLOADED))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.deliveryDocumentsUuid").value(DEFAULT_DELIVERY_DOCUMENTS_UUID.toString()))
            .andExpect(jsonPath("$.responseJsonData").value(DEFAULT_RESPONSE_JSON_DATA));
    }

    @Test
    @Transactional
    void getNonExistingDeliveryDocuments() throws Exception {
        // Get the deliveryDocuments
        restDeliveryDocumentsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewDeliveryDocuments() throws Exception {
        // Initialize the database
        deliveryDocumentsRepository.saveAndFlush(deliveryDocuments);

        int databaseSizeBeforeUpdate = deliveryDocumentsRepository.findAll().size();

        // Update the deliveryDocuments
        DeliveryDocuments updatedDeliveryDocuments = deliveryDocumentsRepository.findById(deliveryDocuments.getDeliveryDocId()).get();
        // Disconnect from session so that the updates on updatedDeliveryDocuments are not directly saved in db
        em.detach(updatedDeliveryDocuments);
        updatedDeliveryDocuments
            .deliveryTicketId(UPDATED_DELIVERY_TICKET_ID)
            .deliveryTicketNo(UPDATED_DELIVERY_TICKET_NO)
            .soId(UPDATED_SO_ID)
            .soNo(UPDATED_SO_NO)
            .hcpcsDoctypeId(UPDATED_HCPCS_DOCTYPE_ID)
            .docPatientName(UPDATED_DOC_PATIENT_NAME)
            .documentPatientFilePath(UPDATED_DOCUMENT_PATIENT_FILE_PATH)
            .isPatientSigned(UPDATED_IS_PATIENT_SIGNED)
            .isCaregiverSigned(UPDATED_IS_CAREGIVER_SIGNED)
            .signedDate(UPDATED_SIGNED_DATE)
            .isDeliveryAgentSigned(UPDATED_IS_DELIVERY_AGENT_SIGNED)
            .isScannedUploaded(UPDATED_IS_SCANNED_UPLOADED)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .deliveryDocumentsUuid(UPDATED_DELIVERY_DOCUMENTS_UUID)
            .responseJsonData(UPDATED_RESPONSE_JSON_DATA);
        DeliveryDocumentsDTO deliveryDocumentsDTO = deliveryDocumentsMapper.toDto(updatedDeliveryDocuments);

        restDeliveryDocumentsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, deliveryDocumentsDTO.getDeliveryDocId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(deliveryDocumentsDTO))
            )
            .andExpect(status().isOk());

        // Validate the DeliveryDocuments in the database
        List<DeliveryDocuments> deliveryDocumentsList = deliveryDocumentsRepository.findAll();
        assertThat(deliveryDocumentsList).hasSize(databaseSizeBeforeUpdate);
        DeliveryDocuments testDeliveryDocuments = deliveryDocumentsList.get(deliveryDocumentsList.size() - 1);
        assertThat(testDeliveryDocuments.getDeliveryTicketId()).isEqualTo(UPDATED_DELIVERY_TICKET_ID);
        assertThat(testDeliveryDocuments.getDeliveryTicketNo()).isEqualTo(UPDATED_DELIVERY_TICKET_NO);
        assertThat(testDeliveryDocuments.getSoId()).isEqualTo(UPDATED_SO_ID);
        assertThat(testDeliveryDocuments.getSoNo()).isEqualTo(UPDATED_SO_NO);
        assertThat(testDeliveryDocuments.getHcpcsDoctypeId()).isEqualTo(UPDATED_HCPCS_DOCTYPE_ID);
        assertThat(testDeliveryDocuments.getDocPatientName()).isEqualTo(UPDATED_DOC_PATIENT_NAME);
        assertThat(testDeliveryDocuments.getDocumentPatientFilePath()).isEqualTo(UPDATED_DOCUMENT_PATIENT_FILE_PATH);
        assertThat(testDeliveryDocuments.getIsPatientSigned()).isEqualTo(UPDATED_IS_PATIENT_SIGNED);
        assertThat(testDeliveryDocuments.getIsCaregiverSigned()).isEqualTo(UPDATED_IS_CAREGIVER_SIGNED);
        assertThat(testDeliveryDocuments.getSignedDate()).isEqualTo(UPDATED_SIGNED_DATE);
        assertThat(testDeliveryDocuments.getIsDeliveryAgentSigned()).isEqualTo(UPDATED_IS_DELIVERY_AGENT_SIGNED);
        assertThat(testDeliveryDocuments.getIsScannedUploaded()).isEqualTo(UPDATED_IS_SCANNED_UPLOADED);
        assertThat(testDeliveryDocuments.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDeliveryDocuments.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testDeliveryDocuments.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testDeliveryDocuments.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testDeliveryDocuments.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testDeliveryDocuments.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testDeliveryDocuments.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testDeliveryDocuments.getDeliveryDocumentsUuid()).isEqualTo(UPDATED_DELIVERY_DOCUMENTS_UUID);
        assertThat(testDeliveryDocuments.getResponseJsonData()).isEqualTo(UPDATED_RESPONSE_JSON_DATA);
    }

    @Test
    @Transactional
    void putNonExistingDeliveryDocuments() throws Exception {
        int databaseSizeBeforeUpdate = deliveryDocumentsRepository.findAll().size();
        deliveryDocuments.setDeliveryDocId(count.incrementAndGet());

        // Create the DeliveryDocuments
        DeliveryDocumentsDTO deliveryDocumentsDTO = deliveryDocumentsMapper.toDto(deliveryDocuments);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDeliveryDocumentsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, deliveryDocumentsDTO.getDeliveryDocId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(deliveryDocumentsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeliveryDocuments in the database
        List<DeliveryDocuments> deliveryDocumentsList = deliveryDocumentsRepository.findAll();
        assertThat(deliveryDocumentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDeliveryDocuments() throws Exception {
        int databaseSizeBeforeUpdate = deliveryDocumentsRepository.findAll().size();
        deliveryDocuments.setDeliveryDocId(count.incrementAndGet());

        // Create the DeliveryDocuments
        DeliveryDocumentsDTO deliveryDocumentsDTO = deliveryDocumentsMapper.toDto(deliveryDocuments);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeliveryDocumentsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(deliveryDocumentsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeliveryDocuments in the database
        List<DeliveryDocuments> deliveryDocumentsList = deliveryDocumentsRepository.findAll();
        assertThat(deliveryDocumentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDeliveryDocuments() throws Exception {
        int databaseSizeBeforeUpdate = deliveryDocumentsRepository.findAll().size();
        deliveryDocuments.setDeliveryDocId(count.incrementAndGet());

        // Create the DeliveryDocuments
        DeliveryDocumentsDTO deliveryDocumentsDTO = deliveryDocumentsMapper.toDto(deliveryDocuments);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeliveryDocumentsMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(deliveryDocumentsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DeliveryDocuments in the database
        List<DeliveryDocuments> deliveryDocumentsList = deliveryDocumentsRepository.findAll();
        assertThat(deliveryDocumentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDeliveryDocumentsWithPatch() throws Exception {
        // Initialize the database
        deliveryDocumentsRepository.saveAndFlush(deliveryDocuments);

        int databaseSizeBeforeUpdate = deliveryDocumentsRepository.findAll().size();

        // Update the deliveryDocuments using partial update
        DeliveryDocuments partialUpdatedDeliveryDocuments = new DeliveryDocuments();
        partialUpdatedDeliveryDocuments.setDeliveryDocId(deliveryDocuments.getDeliveryDocId());

        partialUpdatedDeliveryDocuments
            .soId(UPDATED_SO_ID)
            .documentPatientFilePath(UPDATED_DOCUMENT_PATIENT_FILE_PATH)
            .isCaregiverSigned(UPDATED_IS_CAREGIVER_SIGNED)
            .signedDate(UPDATED_SIGNED_DATE)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE);

        restDeliveryDocumentsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDeliveryDocuments.getDeliveryDocId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDeliveryDocuments))
            )
            .andExpect(status().isOk());

        // Validate the DeliveryDocuments in the database
        List<DeliveryDocuments> deliveryDocumentsList = deliveryDocumentsRepository.findAll();
        assertThat(deliveryDocumentsList).hasSize(databaseSizeBeforeUpdate);
        DeliveryDocuments testDeliveryDocuments = deliveryDocumentsList.get(deliveryDocumentsList.size() - 1);
        assertThat(testDeliveryDocuments.getDeliveryTicketId()).isEqualTo(DEFAULT_DELIVERY_TICKET_ID);
        assertThat(testDeliveryDocuments.getDeliveryTicketNo()).isEqualTo(DEFAULT_DELIVERY_TICKET_NO);
        assertThat(testDeliveryDocuments.getSoId()).isEqualTo(UPDATED_SO_ID);
        assertThat(testDeliveryDocuments.getSoNo()).isEqualTo(DEFAULT_SO_NO);
        assertThat(testDeliveryDocuments.getHcpcsDoctypeId()).isEqualTo(DEFAULT_HCPCS_DOCTYPE_ID);
        assertThat(testDeliveryDocuments.getDocPatientName()).isEqualTo(DEFAULT_DOC_PATIENT_NAME);
        assertThat(testDeliveryDocuments.getDocumentPatientFilePath()).isEqualTo(UPDATED_DOCUMENT_PATIENT_FILE_PATH);
        assertThat(testDeliveryDocuments.getIsPatientSigned()).isEqualTo(DEFAULT_IS_PATIENT_SIGNED);
        assertThat(testDeliveryDocuments.getIsCaregiverSigned()).isEqualTo(UPDATED_IS_CAREGIVER_SIGNED);
        assertThat(testDeliveryDocuments.getSignedDate()).isEqualTo(UPDATED_SIGNED_DATE);
        assertThat(testDeliveryDocuments.getIsDeliveryAgentSigned()).isEqualTo(DEFAULT_IS_DELIVERY_AGENT_SIGNED);
        assertThat(testDeliveryDocuments.getIsScannedUploaded()).isEqualTo(DEFAULT_IS_SCANNED_UPLOADED);
        assertThat(testDeliveryDocuments.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDeliveryDocuments.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testDeliveryDocuments.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testDeliveryDocuments.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testDeliveryDocuments.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testDeliveryDocuments.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testDeliveryDocuments.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testDeliveryDocuments.getDeliveryDocumentsUuid()).isEqualTo(DEFAULT_DELIVERY_DOCUMENTS_UUID);
        assertThat(testDeliveryDocuments.getResponseJsonData()).isEqualTo(DEFAULT_RESPONSE_JSON_DATA);
    }

    @Test
    @Transactional
    void fullUpdateDeliveryDocumentsWithPatch() throws Exception {
        // Initialize the database
        deliveryDocumentsRepository.saveAndFlush(deliveryDocuments);

        int databaseSizeBeforeUpdate = deliveryDocumentsRepository.findAll().size();

        // Update the deliveryDocuments using partial update
        DeliveryDocuments partialUpdatedDeliveryDocuments = new DeliveryDocuments();
        partialUpdatedDeliveryDocuments.setDeliveryDocId(deliveryDocuments.getDeliveryDocId());

        partialUpdatedDeliveryDocuments
            .deliveryTicketId(UPDATED_DELIVERY_TICKET_ID)
            .deliveryTicketNo(UPDATED_DELIVERY_TICKET_NO)
            .soId(UPDATED_SO_ID)
            .soNo(UPDATED_SO_NO)
            .hcpcsDoctypeId(UPDATED_HCPCS_DOCTYPE_ID)
            .docPatientName(UPDATED_DOC_PATIENT_NAME)
            .documentPatientFilePath(UPDATED_DOCUMENT_PATIENT_FILE_PATH)
            .isPatientSigned(UPDATED_IS_PATIENT_SIGNED)
            .isCaregiverSigned(UPDATED_IS_CAREGIVER_SIGNED)
            .signedDate(UPDATED_SIGNED_DATE)
            .isDeliveryAgentSigned(UPDATED_IS_DELIVERY_AGENT_SIGNED)
            .isScannedUploaded(UPDATED_IS_SCANNED_UPLOADED)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .deliveryDocumentsUuid(UPDATED_DELIVERY_DOCUMENTS_UUID)
            .responseJsonData(UPDATED_RESPONSE_JSON_DATA);

        restDeliveryDocumentsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDeliveryDocuments.getDeliveryDocId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDeliveryDocuments))
            )
            .andExpect(status().isOk());

        // Validate the DeliveryDocuments in the database
        List<DeliveryDocuments> deliveryDocumentsList = deliveryDocumentsRepository.findAll();
        assertThat(deliveryDocumentsList).hasSize(databaseSizeBeforeUpdate);
        DeliveryDocuments testDeliveryDocuments = deliveryDocumentsList.get(deliveryDocumentsList.size() - 1);
        assertThat(testDeliveryDocuments.getDeliveryTicketId()).isEqualTo(UPDATED_DELIVERY_TICKET_ID);
        assertThat(testDeliveryDocuments.getDeliveryTicketNo()).isEqualTo(UPDATED_DELIVERY_TICKET_NO);
        assertThat(testDeliveryDocuments.getSoId()).isEqualTo(UPDATED_SO_ID);
        assertThat(testDeliveryDocuments.getSoNo()).isEqualTo(UPDATED_SO_NO);
        assertThat(testDeliveryDocuments.getHcpcsDoctypeId()).isEqualTo(UPDATED_HCPCS_DOCTYPE_ID);
        assertThat(testDeliveryDocuments.getDocPatientName()).isEqualTo(UPDATED_DOC_PATIENT_NAME);
        assertThat(testDeliveryDocuments.getDocumentPatientFilePath()).isEqualTo(UPDATED_DOCUMENT_PATIENT_FILE_PATH);
        assertThat(testDeliveryDocuments.getIsPatientSigned()).isEqualTo(UPDATED_IS_PATIENT_SIGNED);
        assertThat(testDeliveryDocuments.getIsCaregiverSigned()).isEqualTo(UPDATED_IS_CAREGIVER_SIGNED);
        assertThat(testDeliveryDocuments.getSignedDate()).isEqualTo(UPDATED_SIGNED_DATE);
        assertThat(testDeliveryDocuments.getIsDeliveryAgentSigned()).isEqualTo(UPDATED_IS_DELIVERY_AGENT_SIGNED);
        assertThat(testDeliveryDocuments.getIsScannedUploaded()).isEqualTo(UPDATED_IS_SCANNED_UPLOADED);
        assertThat(testDeliveryDocuments.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDeliveryDocuments.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testDeliveryDocuments.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testDeliveryDocuments.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testDeliveryDocuments.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testDeliveryDocuments.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testDeliveryDocuments.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testDeliveryDocuments.getDeliveryDocumentsUuid()).isEqualTo(UPDATED_DELIVERY_DOCUMENTS_UUID);
        assertThat(testDeliveryDocuments.getResponseJsonData()).isEqualTo(UPDATED_RESPONSE_JSON_DATA);
    }

    @Test
    @Transactional
    void patchNonExistingDeliveryDocuments() throws Exception {
        int databaseSizeBeforeUpdate = deliveryDocumentsRepository.findAll().size();
        deliveryDocuments.setDeliveryDocId(count.incrementAndGet());

        // Create the DeliveryDocuments
        DeliveryDocumentsDTO deliveryDocumentsDTO = deliveryDocumentsMapper.toDto(deliveryDocuments);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDeliveryDocumentsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, deliveryDocumentsDTO.getDeliveryDocId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(deliveryDocumentsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeliveryDocuments in the database
        List<DeliveryDocuments> deliveryDocumentsList = deliveryDocumentsRepository.findAll();
        assertThat(deliveryDocumentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDeliveryDocuments() throws Exception {
        int databaseSizeBeforeUpdate = deliveryDocumentsRepository.findAll().size();
        deliveryDocuments.setDeliveryDocId(count.incrementAndGet());

        // Create the DeliveryDocuments
        DeliveryDocumentsDTO deliveryDocumentsDTO = deliveryDocumentsMapper.toDto(deliveryDocuments);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeliveryDocumentsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(deliveryDocumentsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeliveryDocuments in the database
        List<DeliveryDocuments> deliveryDocumentsList = deliveryDocumentsRepository.findAll();
        assertThat(deliveryDocumentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDeliveryDocuments() throws Exception {
        int databaseSizeBeforeUpdate = deliveryDocumentsRepository.findAll().size();
        deliveryDocuments.setDeliveryDocId(count.incrementAndGet());

        // Create the DeliveryDocuments
        DeliveryDocumentsDTO deliveryDocumentsDTO = deliveryDocumentsMapper.toDto(deliveryDocuments);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeliveryDocumentsMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(deliveryDocumentsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DeliveryDocuments in the database
        List<DeliveryDocuments> deliveryDocumentsList = deliveryDocumentsRepository.findAll();
        assertThat(deliveryDocumentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDeliveryDocuments() throws Exception {
        // Initialize the database
        deliveryDocumentsRepository.saveAndFlush(deliveryDocuments);

        int databaseSizeBeforeDelete = deliveryDocumentsRepository.findAll().size();

        // Delete the deliveryDocuments
        restDeliveryDocumentsMockMvc
            .perform(delete(ENTITY_API_URL_ID, deliveryDocuments.getDeliveryDocId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DeliveryDocuments> deliveryDocumentsList = deliveryDocumentsRepository.findAll();
        assertThat(deliveryDocumentsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
