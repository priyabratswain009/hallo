package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.AbnDelivery;
import com.sunknowledge.dme.rcm.repository.AbnDeliveryRepository;
import com.sunknowledge.dme.rcm.service.dto.AbnDeliveryDTO;
import com.sunknowledge.dme.rcm.service.mapper.AbnDeliveryMapper;
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
 * Integration tests for the {@link AbnDeliveryResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AbnDeliveryResourceIT {

    private static final Long DEFAULT_ABN_DELIVERY_DATA_ID = 1L;
    private static final Long UPDATED_ABN_DELIVERY_DATA_ID = 2L;

    private static final String DEFAULT_ABN_DOCUMENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ABN_DOCUMENT_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_SCHEDULE_DELIVERY_DATE_TIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SCHEDULE_DELIVERY_DATE_TIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ACTUAL_DELIVERY_DATE_TIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ACTUAL_DELIVERY_DATE_TIME = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_DELIVERY_AGENT_ID = 1L;
    private static final Long UPDATED_DELIVERY_AGENT_ID = 2L;

    private static final String DEFAULT_DELIVERY_AGENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_AGENT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ABN_DELIVERY_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_ABN_DELIVERY_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_ABN_DELIVERY_DOCUMENT_ACK_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_ABN_DELIVERY_DOCUMENT_ACK_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_ABN_DELIVERY_DOC_PATIENT_REPLY_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_ABN_DELIVERY_DOC_PATIENT_REPLY_STATUS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_ABN_DELIVERY_DOC_PATIENT_REPLY_DATE_TIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ABN_DELIVERY_DOC_PATIENT_REPLY_DATE_TIME = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ABN_RESPONSE_JSON_DATA = "AAAAAAAAAA";
    private static final String UPDATED_ABN_RESPONSE_JSON_DATA = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_ABN_DELIVERY_UUID = UUID.randomUUID();
    private static final UUID UPDATED_ABN_DELIVERY_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/abn-deliveries";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{abnDeliveryId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AbnDeliveryRepository abnDeliveryRepository;

    @Autowired
    private AbnDeliveryMapper abnDeliveryMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAbnDeliveryMockMvc;

    private AbnDelivery abnDelivery;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AbnDelivery createEntity(EntityManager em) {
        AbnDelivery abnDelivery = new AbnDelivery()
            .abnDeliveryDataId(DEFAULT_ABN_DELIVERY_DATA_ID)
            .abnDocumentName(DEFAULT_ABN_DOCUMENT_NAME)
            .scheduleDeliveryDateTime(DEFAULT_SCHEDULE_DELIVERY_DATE_TIME)
            .actualDeliveryDateTime(DEFAULT_ACTUAL_DELIVERY_DATE_TIME)
            .deliveryAgentId(DEFAULT_DELIVERY_AGENT_ID)
            .deliveryAgentName(DEFAULT_DELIVERY_AGENT_NAME)
            .abnDeliveryStatus(DEFAULT_ABN_DELIVERY_STATUS)
            .abnDeliveryDocumentAckStatus(DEFAULT_ABN_DELIVERY_DOCUMENT_ACK_STATUS)
            .abnDeliveryDocPatientReplyStatus(DEFAULT_ABN_DELIVERY_DOC_PATIENT_REPLY_STATUS)
            .abnDeliveryDocPatientReplyDateTime(DEFAULT_ABN_DELIVERY_DOC_PATIENT_REPLY_DATE_TIME)
            .abnResponseJsonData(DEFAULT_ABN_RESPONSE_JSON_DATA)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .abnDeliveryUuid(DEFAULT_ABN_DELIVERY_UUID);
        return abnDelivery;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AbnDelivery createUpdatedEntity(EntityManager em) {
        AbnDelivery abnDelivery = new AbnDelivery()
            .abnDeliveryDataId(UPDATED_ABN_DELIVERY_DATA_ID)
            .abnDocumentName(UPDATED_ABN_DOCUMENT_NAME)
            .scheduleDeliveryDateTime(UPDATED_SCHEDULE_DELIVERY_DATE_TIME)
            .actualDeliveryDateTime(UPDATED_ACTUAL_DELIVERY_DATE_TIME)
            .deliveryAgentId(UPDATED_DELIVERY_AGENT_ID)
            .deliveryAgentName(UPDATED_DELIVERY_AGENT_NAME)
            .abnDeliveryStatus(UPDATED_ABN_DELIVERY_STATUS)
            .abnDeliveryDocumentAckStatus(UPDATED_ABN_DELIVERY_DOCUMENT_ACK_STATUS)
            .abnDeliveryDocPatientReplyStatus(UPDATED_ABN_DELIVERY_DOC_PATIENT_REPLY_STATUS)
            .abnDeliveryDocPatientReplyDateTime(UPDATED_ABN_DELIVERY_DOC_PATIENT_REPLY_DATE_TIME)
            .abnResponseJsonData(UPDATED_ABN_RESPONSE_JSON_DATA)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .abnDeliveryUuid(UPDATED_ABN_DELIVERY_UUID);
        return abnDelivery;
    }

    @BeforeEach
    public void initTest() {
        abnDelivery = createEntity(em);
    }

    @Test
    @Transactional
    void createAbnDelivery() throws Exception {
        int databaseSizeBeforeCreate = abnDeliveryRepository.findAll().size();
        // Create the AbnDelivery
        AbnDeliveryDTO abnDeliveryDTO = abnDeliveryMapper.toDto(abnDelivery);
        restAbnDeliveryMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(abnDeliveryDTO))
            )
            .andExpect(status().isCreated());

        // Validate the AbnDelivery in the database
        List<AbnDelivery> abnDeliveryList = abnDeliveryRepository.findAll();
        assertThat(abnDeliveryList).hasSize(databaseSizeBeforeCreate + 1);
        AbnDelivery testAbnDelivery = abnDeliveryList.get(abnDeliveryList.size() - 1);
        assertThat(testAbnDelivery.getAbnDeliveryDataId()).isEqualTo(DEFAULT_ABN_DELIVERY_DATA_ID);
        assertThat(testAbnDelivery.getAbnDocumentName()).isEqualTo(DEFAULT_ABN_DOCUMENT_NAME);
        assertThat(testAbnDelivery.getScheduleDeliveryDateTime()).isEqualTo(DEFAULT_SCHEDULE_DELIVERY_DATE_TIME);
        assertThat(testAbnDelivery.getActualDeliveryDateTime()).isEqualTo(DEFAULT_ACTUAL_DELIVERY_DATE_TIME);
        assertThat(testAbnDelivery.getDeliveryAgentId()).isEqualTo(DEFAULT_DELIVERY_AGENT_ID);
        assertThat(testAbnDelivery.getDeliveryAgentName()).isEqualTo(DEFAULT_DELIVERY_AGENT_NAME);
        assertThat(testAbnDelivery.getAbnDeliveryStatus()).isEqualTo(DEFAULT_ABN_DELIVERY_STATUS);
        assertThat(testAbnDelivery.getAbnDeliveryDocumentAckStatus()).isEqualTo(DEFAULT_ABN_DELIVERY_DOCUMENT_ACK_STATUS);
        assertThat(testAbnDelivery.getAbnDeliveryDocPatientReplyStatus()).isEqualTo(DEFAULT_ABN_DELIVERY_DOC_PATIENT_REPLY_STATUS);
        assertThat(testAbnDelivery.getAbnDeliveryDocPatientReplyDateTime()).isEqualTo(DEFAULT_ABN_DELIVERY_DOC_PATIENT_REPLY_DATE_TIME);
        assertThat(testAbnDelivery.getAbnResponseJsonData()).isEqualTo(DEFAULT_ABN_RESPONSE_JSON_DATA);
        assertThat(testAbnDelivery.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testAbnDelivery.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testAbnDelivery.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testAbnDelivery.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testAbnDelivery.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testAbnDelivery.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testAbnDelivery.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testAbnDelivery.getAbnDeliveryUuid()).isEqualTo(DEFAULT_ABN_DELIVERY_UUID);
    }

    @Test
    @Transactional
    void createAbnDeliveryWithExistingId() throws Exception {
        // Create the AbnDelivery with an existing ID
        abnDelivery.setAbnDeliveryId(1L);
        AbnDeliveryDTO abnDeliveryDTO = abnDeliveryMapper.toDto(abnDelivery);

        int databaseSizeBeforeCreate = abnDeliveryRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAbnDeliveryMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(abnDeliveryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AbnDelivery in the database
        List<AbnDelivery> abnDeliveryList = abnDeliveryRepository.findAll();
        assertThat(abnDeliveryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAbnDeliveries() throws Exception {
        // Initialize the database
        abnDeliveryRepository.saveAndFlush(abnDelivery);

        // Get all the abnDeliveryList
        restAbnDeliveryMockMvc
            .perform(get(ENTITY_API_URL + "?sort=abnDeliveryId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].abnDeliveryId").value(hasItem(abnDelivery.getAbnDeliveryId().intValue())))
            .andExpect(jsonPath("$.[*].abnDeliveryDataId").value(hasItem(DEFAULT_ABN_DELIVERY_DATA_ID.intValue())))
            .andExpect(jsonPath("$.[*].abnDocumentName").value(hasItem(DEFAULT_ABN_DOCUMENT_NAME)))
            .andExpect(jsonPath("$.[*].scheduleDeliveryDateTime").value(hasItem(DEFAULT_SCHEDULE_DELIVERY_DATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].actualDeliveryDateTime").value(hasItem(DEFAULT_ACTUAL_DELIVERY_DATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].deliveryAgentId").value(hasItem(DEFAULT_DELIVERY_AGENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].deliveryAgentName").value(hasItem(DEFAULT_DELIVERY_AGENT_NAME)))
            .andExpect(jsonPath("$.[*].abnDeliveryStatus").value(hasItem(DEFAULT_ABN_DELIVERY_STATUS)))
            .andExpect(jsonPath("$.[*].abnDeliveryDocumentAckStatus").value(hasItem(DEFAULT_ABN_DELIVERY_DOCUMENT_ACK_STATUS)))
            .andExpect(jsonPath("$.[*].abnDeliveryDocPatientReplyStatus").value(hasItem(DEFAULT_ABN_DELIVERY_DOC_PATIENT_REPLY_STATUS)))
            .andExpect(
                jsonPath("$.[*].abnDeliveryDocPatientReplyDateTime")
                    .value(hasItem(DEFAULT_ABN_DELIVERY_DOC_PATIENT_REPLY_DATE_TIME.toString()))
            )
            .andExpect(jsonPath("$.[*].abnResponseJsonData").value(hasItem(DEFAULT_ABN_RESPONSE_JSON_DATA)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].abnDeliveryUuid").value(hasItem(DEFAULT_ABN_DELIVERY_UUID.toString())));
    }

    @Test
    @Transactional
    void getAbnDelivery() throws Exception {
        // Initialize the database
        abnDeliveryRepository.saveAndFlush(abnDelivery);

        // Get the abnDelivery
        restAbnDeliveryMockMvc
            .perform(get(ENTITY_API_URL_ID, abnDelivery.getAbnDeliveryId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.abnDeliveryId").value(abnDelivery.getAbnDeliveryId().intValue()))
            .andExpect(jsonPath("$.abnDeliveryDataId").value(DEFAULT_ABN_DELIVERY_DATA_ID.intValue()))
            .andExpect(jsonPath("$.abnDocumentName").value(DEFAULT_ABN_DOCUMENT_NAME))
            .andExpect(jsonPath("$.scheduleDeliveryDateTime").value(DEFAULT_SCHEDULE_DELIVERY_DATE_TIME.toString()))
            .andExpect(jsonPath("$.actualDeliveryDateTime").value(DEFAULT_ACTUAL_DELIVERY_DATE_TIME.toString()))
            .andExpect(jsonPath("$.deliveryAgentId").value(DEFAULT_DELIVERY_AGENT_ID.intValue()))
            .andExpect(jsonPath("$.deliveryAgentName").value(DEFAULT_DELIVERY_AGENT_NAME))
            .andExpect(jsonPath("$.abnDeliveryStatus").value(DEFAULT_ABN_DELIVERY_STATUS))
            .andExpect(jsonPath("$.abnDeliveryDocumentAckStatus").value(DEFAULT_ABN_DELIVERY_DOCUMENT_ACK_STATUS))
            .andExpect(jsonPath("$.abnDeliveryDocPatientReplyStatus").value(DEFAULT_ABN_DELIVERY_DOC_PATIENT_REPLY_STATUS))
            .andExpect(jsonPath("$.abnDeliveryDocPatientReplyDateTime").value(DEFAULT_ABN_DELIVERY_DOC_PATIENT_REPLY_DATE_TIME.toString()))
            .andExpect(jsonPath("$.abnResponseJsonData").value(DEFAULT_ABN_RESPONSE_JSON_DATA))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.abnDeliveryUuid").value(DEFAULT_ABN_DELIVERY_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingAbnDelivery() throws Exception {
        // Get the abnDelivery
        restAbnDeliveryMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewAbnDelivery() throws Exception {
        // Initialize the database
        abnDeliveryRepository.saveAndFlush(abnDelivery);

        int databaseSizeBeforeUpdate = abnDeliveryRepository.findAll().size();

        // Update the abnDelivery
        AbnDelivery updatedAbnDelivery = abnDeliveryRepository.findById(abnDelivery.getAbnDeliveryId()).get();
        // Disconnect from session so that the updates on updatedAbnDelivery are not directly saved in db
        em.detach(updatedAbnDelivery);
        updatedAbnDelivery
            .abnDeliveryDataId(UPDATED_ABN_DELIVERY_DATA_ID)
            .abnDocumentName(UPDATED_ABN_DOCUMENT_NAME)
            .scheduleDeliveryDateTime(UPDATED_SCHEDULE_DELIVERY_DATE_TIME)
            .actualDeliveryDateTime(UPDATED_ACTUAL_DELIVERY_DATE_TIME)
            .deliveryAgentId(UPDATED_DELIVERY_AGENT_ID)
            .deliveryAgentName(UPDATED_DELIVERY_AGENT_NAME)
            .abnDeliveryStatus(UPDATED_ABN_DELIVERY_STATUS)
            .abnDeliveryDocumentAckStatus(UPDATED_ABN_DELIVERY_DOCUMENT_ACK_STATUS)
            .abnDeliveryDocPatientReplyStatus(UPDATED_ABN_DELIVERY_DOC_PATIENT_REPLY_STATUS)
            .abnDeliveryDocPatientReplyDateTime(UPDATED_ABN_DELIVERY_DOC_PATIENT_REPLY_DATE_TIME)
            .abnResponseJsonData(UPDATED_ABN_RESPONSE_JSON_DATA)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .abnDeliveryUuid(UPDATED_ABN_DELIVERY_UUID);
        AbnDeliveryDTO abnDeliveryDTO = abnDeliveryMapper.toDto(updatedAbnDelivery);

        restAbnDeliveryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, abnDeliveryDTO.getAbnDeliveryId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(abnDeliveryDTO))
            )
            .andExpect(status().isOk());

        // Validate the AbnDelivery in the database
        List<AbnDelivery> abnDeliveryList = abnDeliveryRepository.findAll();
        assertThat(abnDeliveryList).hasSize(databaseSizeBeforeUpdate);
        AbnDelivery testAbnDelivery = abnDeliveryList.get(abnDeliveryList.size() - 1);
        assertThat(testAbnDelivery.getAbnDeliveryDataId()).isEqualTo(UPDATED_ABN_DELIVERY_DATA_ID);
        assertThat(testAbnDelivery.getAbnDocumentName()).isEqualTo(UPDATED_ABN_DOCUMENT_NAME);
        assertThat(testAbnDelivery.getScheduleDeliveryDateTime()).isEqualTo(UPDATED_SCHEDULE_DELIVERY_DATE_TIME);
        assertThat(testAbnDelivery.getActualDeliveryDateTime()).isEqualTo(UPDATED_ACTUAL_DELIVERY_DATE_TIME);
        assertThat(testAbnDelivery.getDeliveryAgentId()).isEqualTo(UPDATED_DELIVERY_AGENT_ID);
        assertThat(testAbnDelivery.getDeliveryAgentName()).isEqualTo(UPDATED_DELIVERY_AGENT_NAME);
        assertThat(testAbnDelivery.getAbnDeliveryStatus()).isEqualTo(UPDATED_ABN_DELIVERY_STATUS);
        assertThat(testAbnDelivery.getAbnDeliveryDocumentAckStatus()).isEqualTo(UPDATED_ABN_DELIVERY_DOCUMENT_ACK_STATUS);
        assertThat(testAbnDelivery.getAbnDeliveryDocPatientReplyStatus()).isEqualTo(UPDATED_ABN_DELIVERY_DOC_PATIENT_REPLY_STATUS);
        assertThat(testAbnDelivery.getAbnDeliveryDocPatientReplyDateTime()).isEqualTo(UPDATED_ABN_DELIVERY_DOC_PATIENT_REPLY_DATE_TIME);
        assertThat(testAbnDelivery.getAbnResponseJsonData()).isEqualTo(UPDATED_ABN_RESPONSE_JSON_DATA);
        assertThat(testAbnDelivery.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testAbnDelivery.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testAbnDelivery.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testAbnDelivery.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testAbnDelivery.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testAbnDelivery.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testAbnDelivery.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testAbnDelivery.getAbnDeliveryUuid()).isEqualTo(UPDATED_ABN_DELIVERY_UUID);
    }

    @Test
    @Transactional
    void putNonExistingAbnDelivery() throws Exception {
        int databaseSizeBeforeUpdate = abnDeliveryRepository.findAll().size();
        abnDelivery.setAbnDeliveryId(count.incrementAndGet());

        // Create the AbnDelivery
        AbnDeliveryDTO abnDeliveryDTO = abnDeliveryMapper.toDto(abnDelivery);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAbnDeliveryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, abnDeliveryDTO.getAbnDeliveryId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(abnDeliveryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AbnDelivery in the database
        List<AbnDelivery> abnDeliveryList = abnDeliveryRepository.findAll();
        assertThat(abnDeliveryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAbnDelivery() throws Exception {
        int databaseSizeBeforeUpdate = abnDeliveryRepository.findAll().size();
        abnDelivery.setAbnDeliveryId(count.incrementAndGet());

        // Create the AbnDelivery
        AbnDeliveryDTO abnDeliveryDTO = abnDeliveryMapper.toDto(abnDelivery);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAbnDeliveryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(abnDeliveryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AbnDelivery in the database
        List<AbnDelivery> abnDeliveryList = abnDeliveryRepository.findAll();
        assertThat(abnDeliveryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAbnDelivery() throws Exception {
        int databaseSizeBeforeUpdate = abnDeliveryRepository.findAll().size();
        abnDelivery.setAbnDeliveryId(count.incrementAndGet());

        // Create the AbnDelivery
        AbnDeliveryDTO abnDeliveryDTO = abnDeliveryMapper.toDto(abnDelivery);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAbnDeliveryMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(abnDeliveryDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AbnDelivery in the database
        List<AbnDelivery> abnDeliveryList = abnDeliveryRepository.findAll();
        assertThat(abnDeliveryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAbnDeliveryWithPatch() throws Exception {
        // Initialize the database
        abnDeliveryRepository.saveAndFlush(abnDelivery);

        int databaseSizeBeforeUpdate = abnDeliveryRepository.findAll().size();

        // Update the abnDelivery using partial update
        AbnDelivery partialUpdatedAbnDelivery = new AbnDelivery();
        partialUpdatedAbnDelivery.setAbnDeliveryId(abnDelivery.getAbnDeliveryId());

        partialUpdatedAbnDelivery
            .abnDeliveryDataId(UPDATED_ABN_DELIVERY_DATA_ID)
            .abnDeliveryStatus(UPDATED_ABN_DELIVERY_STATUS)
            .abnDeliveryDocumentAckStatus(UPDATED_ABN_DELIVERY_DOCUMENT_ACK_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .abnDeliveryUuid(UPDATED_ABN_DELIVERY_UUID);

        restAbnDeliveryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAbnDelivery.getAbnDeliveryId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAbnDelivery))
            )
            .andExpect(status().isOk());

        // Validate the AbnDelivery in the database
        List<AbnDelivery> abnDeliveryList = abnDeliveryRepository.findAll();
        assertThat(abnDeliveryList).hasSize(databaseSizeBeforeUpdate);
        AbnDelivery testAbnDelivery = abnDeliveryList.get(abnDeliveryList.size() - 1);
        assertThat(testAbnDelivery.getAbnDeliveryDataId()).isEqualTo(UPDATED_ABN_DELIVERY_DATA_ID);
        assertThat(testAbnDelivery.getAbnDocumentName()).isEqualTo(DEFAULT_ABN_DOCUMENT_NAME);
        assertThat(testAbnDelivery.getScheduleDeliveryDateTime()).isEqualTo(DEFAULT_SCHEDULE_DELIVERY_DATE_TIME);
        assertThat(testAbnDelivery.getActualDeliveryDateTime()).isEqualTo(DEFAULT_ACTUAL_DELIVERY_DATE_TIME);
        assertThat(testAbnDelivery.getDeliveryAgentId()).isEqualTo(DEFAULT_DELIVERY_AGENT_ID);
        assertThat(testAbnDelivery.getDeliveryAgentName()).isEqualTo(DEFAULT_DELIVERY_AGENT_NAME);
        assertThat(testAbnDelivery.getAbnDeliveryStatus()).isEqualTo(UPDATED_ABN_DELIVERY_STATUS);
        assertThat(testAbnDelivery.getAbnDeliveryDocumentAckStatus()).isEqualTo(UPDATED_ABN_DELIVERY_DOCUMENT_ACK_STATUS);
        assertThat(testAbnDelivery.getAbnDeliveryDocPatientReplyStatus()).isEqualTo(DEFAULT_ABN_DELIVERY_DOC_PATIENT_REPLY_STATUS);
        assertThat(testAbnDelivery.getAbnDeliveryDocPatientReplyDateTime()).isEqualTo(DEFAULT_ABN_DELIVERY_DOC_PATIENT_REPLY_DATE_TIME);
        assertThat(testAbnDelivery.getAbnResponseJsonData()).isEqualTo(DEFAULT_ABN_RESPONSE_JSON_DATA);
        assertThat(testAbnDelivery.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testAbnDelivery.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testAbnDelivery.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testAbnDelivery.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testAbnDelivery.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testAbnDelivery.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testAbnDelivery.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testAbnDelivery.getAbnDeliveryUuid()).isEqualTo(UPDATED_ABN_DELIVERY_UUID);
    }

    @Test
    @Transactional
    void fullUpdateAbnDeliveryWithPatch() throws Exception {
        // Initialize the database
        abnDeliveryRepository.saveAndFlush(abnDelivery);

        int databaseSizeBeforeUpdate = abnDeliveryRepository.findAll().size();

        // Update the abnDelivery using partial update
        AbnDelivery partialUpdatedAbnDelivery = new AbnDelivery();
        partialUpdatedAbnDelivery.setAbnDeliveryId(abnDelivery.getAbnDeliveryId());

        partialUpdatedAbnDelivery
            .abnDeliveryDataId(UPDATED_ABN_DELIVERY_DATA_ID)
            .abnDocumentName(UPDATED_ABN_DOCUMENT_NAME)
            .scheduleDeliveryDateTime(UPDATED_SCHEDULE_DELIVERY_DATE_TIME)
            .actualDeliveryDateTime(UPDATED_ACTUAL_DELIVERY_DATE_TIME)
            .deliveryAgentId(UPDATED_DELIVERY_AGENT_ID)
            .deliveryAgentName(UPDATED_DELIVERY_AGENT_NAME)
            .abnDeliveryStatus(UPDATED_ABN_DELIVERY_STATUS)
            .abnDeliveryDocumentAckStatus(UPDATED_ABN_DELIVERY_DOCUMENT_ACK_STATUS)
            .abnDeliveryDocPatientReplyStatus(UPDATED_ABN_DELIVERY_DOC_PATIENT_REPLY_STATUS)
            .abnDeliveryDocPatientReplyDateTime(UPDATED_ABN_DELIVERY_DOC_PATIENT_REPLY_DATE_TIME)
            .abnResponseJsonData(UPDATED_ABN_RESPONSE_JSON_DATA)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .abnDeliveryUuid(UPDATED_ABN_DELIVERY_UUID);

        restAbnDeliveryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAbnDelivery.getAbnDeliveryId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAbnDelivery))
            )
            .andExpect(status().isOk());

        // Validate the AbnDelivery in the database
        List<AbnDelivery> abnDeliveryList = abnDeliveryRepository.findAll();
        assertThat(abnDeliveryList).hasSize(databaseSizeBeforeUpdate);
        AbnDelivery testAbnDelivery = abnDeliveryList.get(abnDeliveryList.size() - 1);
        assertThat(testAbnDelivery.getAbnDeliveryDataId()).isEqualTo(UPDATED_ABN_DELIVERY_DATA_ID);
        assertThat(testAbnDelivery.getAbnDocumentName()).isEqualTo(UPDATED_ABN_DOCUMENT_NAME);
        assertThat(testAbnDelivery.getScheduleDeliveryDateTime()).isEqualTo(UPDATED_SCHEDULE_DELIVERY_DATE_TIME);
        assertThat(testAbnDelivery.getActualDeliveryDateTime()).isEqualTo(UPDATED_ACTUAL_DELIVERY_DATE_TIME);
        assertThat(testAbnDelivery.getDeliveryAgentId()).isEqualTo(UPDATED_DELIVERY_AGENT_ID);
        assertThat(testAbnDelivery.getDeliveryAgentName()).isEqualTo(UPDATED_DELIVERY_AGENT_NAME);
        assertThat(testAbnDelivery.getAbnDeliveryStatus()).isEqualTo(UPDATED_ABN_DELIVERY_STATUS);
        assertThat(testAbnDelivery.getAbnDeliveryDocumentAckStatus()).isEqualTo(UPDATED_ABN_DELIVERY_DOCUMENT_ACK_STATUS);
        assertThat(testAbnDelivery.getAbnDeliveryDocPatientReplyStatus()).isEqualTo(UPDATED_ABN_DELIVERY_DOC_PATIENT_REPLY_STATUS);
        assertThat(testAbnDelivery.getAbnDeliveryDocPatientReplyDateTime()).isEqualTo(UPDATED_ABN_DELIVERY_DOC_PATIENT_REPLY_DATE_TIME);
        assertThat(testAbnDelivery.getAbnResponseJsonData()).isEqualTo(UPDATED_ABN_RESPONSE_JSON_DATA);
        assertThat(testAbnDelivery.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testAbnDelivery.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testAbnDelivery.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testAbnDelivery.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testAbnDelivery.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testAbnDelivery.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testAbnDelivery.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testAbnDelivery.getAbnDeliveryUuid()).isEqualTo(UPDATED_ABN_DELIVERY_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingAbnDelivery() throws Exception {
        int databaseSizeBeforeUpdate = abnDeliveryRepository.findAll().size();
        abnDelivery.setAbnDeliveryId(count.incrementAndGet());

        // Create the AbnDelivery
        AbnDeliveryDTO abnDeliveryDTO = abnDeliveryMapper.toDto(abnDelivery);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAbnDeliveryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, abnDeliveryDTO.getAbnDeliveryId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(abnDeliveryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AbnDelivery in the database
        List<AbnDelivery> abnDeliveryList = abnDeliveryRepository.findAll();
        assertThat(abnDeliveryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAbnDelivery() throws Exception {
        int databaseSizeBeforeUpdate = abnDeliveryRepository.findAll().size();
        abnDelivery.setAbnDeliveryId(count.incrementAndGet());

        // Create the AbnDelivery
        AbnDeliveryDTO abnDeliveryDTO = abnDeliveryMapper.toDto(abnDelivery);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAbnDeliveryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(abnDeliveryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AbnDelivery in the database
        List<AbnDelivery> abnDeliveryList = abnDeliveryRepository.findAll();
        assertThat(abnDeliveryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAbnDelivery() throws Exception {
        int databaseSizeBeforeUpdate = abnDeliveryRepository.findAll().size();
        abnDelivery.setAbnDeliveryId(count.incrementAndGet());

        // Create the AbnDelivery
        AbnDeliveryDTO abnDeliveryDTO = abnDeliveryMapper.toDto(abnDelivery);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAbnDeliveryMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(abnDeliveryDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AbnDelivery in the database
        List<AbnDelivery> abnDeliveryList = abnDeliveryRepository.findAll();
        assertThat(abnDeliveryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAbnDelivery() throws Exception {
        // Initialize the database
        abnDeliveryRepository.saveAndFlush(abnDelivery);

        int databaseSizeBeforeDelete = abnDeliveryRepository.findAll().size();

        // Delete the abnDelivery
        restAbnDeliveryMockMvc
            .perform(delete(ENTITY_API_URL_ID, abnDelivery.getAbnDeliveryId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AbnDelivery> abnDeliveryList = abnDeliveryRepository.findAll();
        assertThat(abnDeliveryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
