package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.DeliveryAssignment;
import com.sunknowledge.dme.rcm.repository.DeliveryAssignmentRepository;
import com.sunknowledge.dme.rcm.service.dto.DeliveryAssignmentDTO;
import com.sunknowledge.dme.rcm.service.mapper.DeliveryAssignmentMapper;
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
 * Integration tests for the {@link DeliveryAssignmentResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DeliveryAssignmentResourceIT {

    private static final Long DEFAULT_DELIVERY_TICKET_ID = 1L;
    private static final Long UPDATED_DELIVERY_TICKET_ID = 2L;

    private static final String DEFAULT_DELIVERY_NO = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_NO = "BBBBBBBBBB";

    private static final Long DEFAULT_SO_ID = 1L;
    private static final Long UPDATED_SO_ID = 2L;

    private static final String DEFAULT_SO_NO = "AAAAAAAAAA";
    private static final String UPDATED_SO_NO = "BBBBBBBBBB";

    private static final String DEFAULT_AGENT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_AGENT_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_AGENT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_AGENT_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_AGENT_ID_NO = "AAAAAAAAAA";
    private static final String UPDATED_AGENT_ID_NO = "BBBBBBBBBB";

    private static final String DEFAULT_AGENT_AGENCY = "AAAAAAAAAA";
    private static final String UPDATED_AGENT_AGENCY = "BBBBBBBBBB";

    private static final String DEFAULT_AGENT_CONTACT_1 = "AAAAAAAAAA";
    private static final String UPDATED_AGENT_CONTACT_1 = "BBBBBBBBBB";

    private static final String DEFAULT_AGENT_CONTACT_2 = "AAAAAAAAAA";
    private static final String UPDATED_AGENT_CONTACT_2 = "BBBBBBBBBB";

    private static final String DEFAULT_AGENT_VEHICLE_NO = "AAAAAAAAAA";
    private static final String UPDATED_AGENT_VEHICLE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_ASSIGNMENT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_ASSIGNMENT_STATUS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_ASSGNMENT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ASSGNMENT_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_PRIORITY = "AAAAAAAAAA";
    private static final String UPDATED_PRIORITY = "BBBBBBBBBB";

    private static final String DEFAULT_AGENT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_AGENT_COMMENT = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_DELIVERY_ASSIGNMENT_UUID = UUID.randomUUID();
    private static final UUID UPDATED_DELIVERY_ASSIGNMENT_UUID = UUID.randomUUID();

    private static final String DEFAULT_DELIVERY_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_STATUS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_ACTUAL_DELIVERY_DATE_TIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ACTUAL_DELIVERY_DATE_TIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DELIVERY_SCHEDULE_DATE_TIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DELIVERY_SCHEDULE_DATE_TIME = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/delivery-assignments";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{deliveryAssignmentId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DeliveryAssignmentRepository deliveryAssignmentRepository;

    @Autowired
    private DeliveryAssignmentMapper deliveryAssignmentMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDeliveryAssignmentMockMvc;

    private DeliveryAssignment deliveryAssignment;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DeliveryAssignment createEntity(EntityManager em) {
        DeliveryAssignment deliveryAssignment = new DeliveryAssignment()
            .deliveryTicketId(DEFAULT_DELIVERY_TICKET_ID)
            .deliveryNo(DEFAULT_DELIVERY_NO)
            .soId(DEFAULT_SO_ID)
            .soNo(DEFAULT_SO_NO)
            .agentFirstName(DEFAULT_AGENT_FIRST_NAME)
            .agentLastName(DEFAULT_AGENT_LAST_NAME)
            .agentIdNo(DEFAULT_AGENT_ID_NO)
            .agentAgency(DEFAULT_AGENT_AGENCY)
            .agentContact1(DEFAULT_AGENT_CONTACT_1)
            .agentContact2(DEFAULT_AGENT_CONTACT_2)
            .agentVehicleNo(DEFAULT_AGENT_VEHICLE_NO)
            .assignmentStatus(DEFAULT_ASSIGNMENT_STATUS)
            .assgnmentDate(DEFAULT_ASSGNMENT_DATE)
            .priority(DEFAULT_PRIORITY)
            .agentComment(DEFAULT_AGENT_COMMENT)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .deliveryAssignmentUuid(DEFAULT_DELIVERY_ASSIGNMENT_UUID)
            .deliveryStatus(DEFAULT_DELIVERY_STATUS)
            .actualDeliveryDateTime(DEFAULT_ACTUAL_DELIVERY_DATE_TIME)
            .deliveryScheduleDateTime(DEFAULT_DELIVERY_SCHEDULE_DATE_TIME);
        return deliveryAssignment;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DeliveryAssignment createUpdatedEntity(EntityManager em) {
        DeliveryAssignment deliveryAssignment = new DeliveryAssignment()
            .deliveryTicketId(UPDATED_DELIVERY_TICKET_ID)
            .deliveryNo(UPDATED_DELIVERY_NO)
            .soId(UPDATED_SO_ID)
            .soNo(UPDATED_SO_NO)
            .agentFirstName(UPDATED_AGENT_FIRST_NAME)
            .agentLastName(UPDATED_AGENT_LAST_NAME)
            .agentIdNo(UPDATED_AGENT_ID_NO)
            .agentAgency(UPDATED_AGENT_AGENCY)
            .agentContact1(UPDATED_AGENT_CONTACT_1)
            .agentContact2(UPDATED_AGENT_CONTACT_2)
            .agentVehicleNo(UPDATED_AGENT_VEHICLE_NO)
            .assignmentStatus(UPDATED_ASSIGNMENT_STATUS)
            .assgnmentDate(UPDATED_ASSGNMENT_DATE)
            .priority(UPDATED_PRIORITY)
            .agentComment(UPDATED_AGENT_COMMENT)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .deliveryAssignmentUuid(UPDATED_DELIVERY_ASSIGNMENT_UUID)
            .deliveryStatus(UPDATED_DELIVERY_STATUS)
            .actualDeliveryDateTime(UPDATED_ACTUAL_DELIVERY_DATE_TIME)
            .deliveryScheduleDateTime(UPDATED_DELIVERY_SCHEDULE_DATE_TIME);
        return deliveryAssignment;
    }

    @BeforeEach
    public void initTest() {
        deliveryAssignment = createEntity(em);
    }

    @Test
    @Transactional
    void createDeliveryAssignment() throws Exception {
        int databaseSizeBeforeCreate = deliveryAssignmentRepository.findAll().size();
        // Create the DeliveryAssignment
        DeliveryAssignmentDTO deliveryAssignmentDTO = deliveryAssignmentMapper.toDto(deliveryAssignment);
        restDeliveryAssignmentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(deliveryAssignmentDTO))
            )
            .andExpect(status().isCreated());

        // Validate the DeliveryAssignment in the database
        List<DeliveryAssignment> deliveryAssignmentList = deliveryAssignmentRepository.findAll();
        assertThat(deliveryAssignmentList).hasSize(databaseSizeBeforeCreate + 1);
        DeliveryAssignment testDeliveryAssignment = deliveryAssignmentList.get(deliveryAssignmentList.size() - 1);
        assertThat(testDeliveryAssignment.getDeliveryTicketId()).isEqualTo(DEFAULT_DELIVERY_TICKET_ID);
        assertThat(testDeliveryAssignment.getDeliveryNo()).isEqualTo(DEFAULT_DELIVERY_NO);
        assertThat(testDeliveryAssignment.getSoId()).isEqualTo(DEFAULT_SO_ID);
        assertThat(testDeliveryAssignment.getSoNo()).isEqualTo(DEFAULT_SO_NO);
        assertThat(testDeliveryAssignment.getAgentFirstName()).isEqualTo(DEFAULT_AGENT_FIRST_NAME);
        assertThat(testDeliveryAssignment.getAgentLastName()).isEqualTo(DEFAULT_AGENT_LAST_NAME);
        assertThat(testDeliveryAssignment.getAgentIdNo()).isEqualTo(DEFAULT_AGENT_ID_NO);
        assertThat(testDeliveryAssignment.getAgentAgency()).isEqualTo(DEFAULT_AGENT_AGENCY);
        assertThat(testDeliveryAssignment.getAgentContact1()).isEqualTo(DEFAULT_AGENT_CONTACT_1);
        assertThat(testDeliveryAssignment.getAgentContact2()).isEqualTo(DEFAULT_AGENT_CONTACT_2);
        assertThat(testDeliveryAssignment.getAgentVehicleNo()).isEqualTo(DEFAULT_AGENT_VEHICLE_NO);
        assertThat(testDeliveryAssignment.getAssignmentStatus()).isEqualTo(DEFAULT_ASSIGNMENT_STATUS);
        assertThat(testDeliveryAssignment.getAssgnmentDate()).isEqualTo(DEFAULT_ASSGNMENT_DATE);
        assertThat(testDeliveryAssignment.getPriority()).isEqualTo(DEFAULT_PRIORITY);
        assertThat(testDeliveryAssignment.getAgentComment()).isEqualTo(DEFAULT_AGENT_COMMENT);
        assertThat(testDeliveryAssignment.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testDeliveryAssignment.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testDeliveryAssignment.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testDeliveryAssignment.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testDeliveryAssignment.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testDeliveryAssignment.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testDeliveryAssignment.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testDeliveryAssignment.getDeliveryAssignmentUuid()).isEqualTo(DEFAULT_DELIVERY_ASSIGNMENT_UUID);
        assertThat(testDeliveryAssignment.getDeliveryStatus()).isEqualTo(DEFAULT_DELIVERY_STATUS);
        assertThat(testDeliveryAssignment.getActualDeliveryDateTime()).isEqualTo(DEFAULT_ACTUAL_DELIVERY_DATE_TIME);
        assertThat(testDeliveryAssignment.getDeliveryScheduleDateTime()).isEqualTo(DEFAULT_DELIVERY_SCHEDULE_DATE_TIME);
    }

    @Test
    @Transactional
    void createDeliveryAssignmentWithExistingId() throws Exception {
        // Create the DeliveryAssignment with an existing ID
        deliveryAssignment.setDeliveryAssignmentId(1L);
        DeliveryAssignmentDTO deliveryAssignmentDTO = deliveryAssignmentMapper.toDto(deliveryAssignment);

        int databaseSizeBeforeCreate = deliveryAssignmentRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDeliveryAssignmentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(deliveryAssignmentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeliveryAssignment in the database
        List<DeliveryAssignment> deliveryAssignmentList = deliveryAssignmentRepository.findAll();
        assertThat(deliveryAssignmentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDeliveryAssignments() throws Exception {
        // Initialize the database
        deliveryAssignmentRepository.saveAndFlush(deliveryAssignment);

        // Get all the deliveryAssignmentList
        restDeliveryAssignmentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=deliveryAssignmentId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].deliveryAssignmentId").value(hasItem(deliveryAssignment.getDeliveryAssignmentId().intValue())))
            .andExpect(jsonPath("$.[*].deliveryTicketId").value(hasItem(DEFAULT_DELIVERY_TICKET_ID.intValue())))
            .andExpect(jsonPath("$.[*].deliveryNo").value(hasItem(DEFAULT_DELIVERY_NO)))
            .andExpect(jsonPath("$.[*].soId").value(hasItem(DEFAULT_SO_ID.intValue())))
            .andExpect(jsonPath("$.[*].soNo").value(hasItem(DEFAULT_SO_NO)))
            .andExpect(jsonPath("$.[*].agentFirstName").value(hasItem(DEFAULT_AGENT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].agentLastName").value(hasItem(DEFAULT_AGENT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].agentIdNo").value(hasItem(DEFAULT_AGENT_ID_NO)))
            .andExpect(jsonPath("$.[*].agentAgency").value(hasItem(DEFAULT_AGENT_AGENCY)))
            .andExpect(jsonPath("$.[*].agentContact1").value(hasItem(DEFAULT_AGENT_CONTACT_1)))
            .andExpect(jsonPath("$.[*].agentContact2").value(hasItem(DEFAULT_AGENT_CONTACT_2)))
            .andExpect(jsonPath("$.[*].agentVehicleNo").value(hasItem(DEFAULT_AGENT_VEHICLE_NO)))
            .andExpect(jsonPath("$.[*].assignmentStatus").value(hasItem(DEFAULT_ASSIGNMENT_STATUS)))
            .andExpect(jsonPath("$.[*].assgnmentDate").value(hasItem(DEFAULT_ASSGNMENT_DATE.toString())))
            .andExpect(jsonPath("$.[*].priority").value(hasItem(DEFAULT_PRIORITY)))
            .andExpect(jsonPath("$.[*].agentComment").value(hasItem(DEFAULT_AGENT_COMMENT)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].deliveryAssignmentUuid").value(hasItem(DEFAULT_DELIVERY_ASSIGNMENT_UUID.toString())))
            .andExpect(jsonPath("$.[*].deliveryStatus").value(hasItem(DEFAULT_DELIVERY_STATUS)))
            .andExpect(jsonPath("$.[*].actualDeliveryDateTime").value(hasItem(DEFAULT_ACTUAL_DELIVERY_DATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].deliveryScheduleDateTime").value(hasItem(DEFAULT_DELIVERY_SCHEDULE_DATE_TIME.toString())));
    }

    @Test
    @Transactional
    void getDeliveryAssignment() throws Exception {
        // Initialize the database
        deliveryAssignmentRepository.saveAndFlush(deliveryAssignment);

        // Get the deliveryAssignment
        restDeliveryAssignmentMockMvc
            .perform(get(ENTITY_API_URL_ID, deliveryAssignment.getDeliveryAssignmentId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.deliveryAssignmentId").value(deliveryAssignment.getDeliveryAssignmentId().intValue()))
            .andExpect(jsonPath("$.deliveryTicketId").value(DEFAULT_DELIVERY_TICKET_ID.intValue()))
            .andExpect(jsonPath("$.deliveryNo").value(DEFAULT_DELIVERY_NO))
            .andExpect(jsonPath("$.soId").value(DEFAULT_SO_ID.intValue()))
            .andExpect(jsonPath("$.soNo").value(DEFAULT_SO_NO))
            .andExpect(jsonPath("$.agentFirstName").value(DEFAULT_AGENT_FIRST_NAME))
            .andExpect(jsonPath("$.agentLastName").value(DEFAULT_AGENT_LAST_NAME))
            .andExpect(jsonPath("$.agentIdNo").value(DEFAULT_AGENT_ID_NO))
            .andExpect(jsonPath("$.agentAgency").value(DEFAULT_AGENT_AGENCY))
            .andExpect(jsonPath("$.agentContact1").value(DEFAULT_AGENT_CONTACT_1))
            .andExpect(jsonPath("$.agentContact2").value(DEFAULT_AGENT_CONTACT_2))
            .andExpect(jsonPath("$.agentVehicleNo").value(DEFAULT_AGENT_VEHICLE_NO))
            .andExpect(jsonPath("$.assignmentStatus").value(DEFAULT_ASSIGNMENT_STATUS))
            .andExpect(jsonPath("$.assgnmentDate").value(DEFAULT_ASSGNMENT_DATE.toString()))
            .andExpect(jsonPath("$.priority").value(DEFAULT_PRIORITY))
            .andExpect(jsonPath("$.agentComment").value(DEFAULT_AGENT_COMMENT))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.deliveryAssignmentUuid").value(DEFAULT_DELIVERY_ASSIGNMENT_UUID.toString()))
            .andExpect(jsonPath("$.deliveryStatus").value(DEFAULT_DELIVERY_STATUS))
            .andExpect(jsonPath("$.actualDeliveryDateTime").value(DEFAULT_ACTUAL_DELIVERY_DATE_TIME.toString()))
            .andExpect(jsonPath("$.deliveryScheduleDateTime").value(DEFAULT_DELIVERY_SCHEDULE_DATE_TIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingDeliveryAssignment() throws Exception {
        // Get the deliveryAssignment
        restDeliveryAssignmentMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewDeliveryAssignment() throws Exception {
        // Initialize the database
        deliveryAssignmentRepository.saveAndFlush(deliveryAssignment);

        int databaseSizeBeforeUpdate = deliveryAssignmentRepository.findAll().size();

        // Update the deliveryAssignment
        DeliveryAssignment updatedDeliveryAssignment = deliveryAssignmentRepository
            .findById(deliveryAssignment.getDeliveryAssignmentId())
            .get();
        // Disconnect from session so that the updates on updatedDeliveryAssignment are not directly saved in db
        em.detach(updatedDeliveryAssignment);
        updatedDeliveryAssignment
            .deliveryTicketId(UPDATED_DELIVERY_TICKET_ID)
            .deliveryNo(UPDATED_DELIVERY_NO)
            .soId(UPDATED_SO_ID)
            .soNo(UPDATED_SO_NO)
            .agentFirstName(UPDATED_AGENT_FIRST_NAME)
            .agentLastName(UPDATED_AGENT_LAST_NAME)
            .agentIdNo(UPDATED_AGENT_ID_NO)
            .agentAgency(UPDATED_AGENT_AGENCY)
            .agentContact1(UPDATED_AGENT_CONTACT_1)
            .agentContact2(UPDATED_AGENT_CONTACT_2)
            .agentVehicleNo(UPDATED_AGENT_VEHICLE_NO)
            .assignmentStatus(UPDATED_ASSIGNMENT_STATUS)
            .assgnmentDate(UPDATED_ASSGNMENT_DATE)
            .priority(UPDATED_PRIORITY)
            .agentComment(UPDATED_AGENT_COMMENT)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .deliveryAssignmentUuid(UPDATED_DELIVERY_ASSIGNMENT_UUID)
            .deliveryStatus(UPDATED_DELIVERY_STATUS)
            .actualDeliveryDateTime(UPDATED_ACTUAL_DELIVERY_DATE_TIME)
            .deliveryScheduleDateTime(UPDATED_DELIVERY_SCHEDULE_DATE_TIME);
        DeliveryAssignmentDTO deliveryAssignmentDTO = deliveryAssignmentMapper.toDto(updatedDeliveryAssignment);

        restDeliveryAssignmentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, deliveryAssignmentDTO.getDeliveryAssignmentId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(deliveryAssignmentDTO))
            )
            .andExpect(status().isOk());

        // Validate the DeliveryAssignment in the database
        List<DeliveryAssignment> deliveryAssignmentList = deliveryAssignmentRepository.findAll();
        assertThat(deliveryAssignmentList).hasSize(databaseSizeBeforeUpdate);
        DeliveryAssignment testDeliveryAssignment = deliveryAssignmentList.get(deliveryAssignmentList.size() - 1);
        assertThat(testDeliveryAssignment.getDeliveryTicketId()).isEqualTo(UPDATED_DELIVERY_TICKET_ID);
        assertThat(testDeliveryAssignment.getDeliveryNo()).isEqualTo(UPDATED_DELIVERY_NO);
        assertThat(testDeliveryAssignment.getSoId()).isEqualTo(UPDATED_SO_ID);
        assertThat(testDeliveryAssignment.getSoNo()).isEqualTo(UPDATED_SO_NO);
        assertThat(testDeliveryAssignment.getAgentFirstName()).isEqualTo(UPDATED_AGENT_FIRST_NAME);
        assertThat(testDeliveryAssignment.getAgentLastName()).isEqualTo(UPDATED_AGENT_LAST_NAME);
        assertThat(testDeliveryAssignment.getAgentIdNo()).isEqualTo(UPDATED_AGENT_ID_NO);
        assertThat(testDeliveryAssignment.getAgentAgency()).isEqualTo(UPDATED_AGENT_AGENCY);
        assertThat(testDeliveryAssignment.getAgentContact1()).isEqualTo(UPDATED_AGENT_CONTACT_1);
        assertThat(testDeliveryAssignment.getAgentContact2()).isEqualTo(UPDATED_AGENT_CONTACT_2);
        assertThat(testDeliveryAssignment.getAgentVehicleNo()).isEqualTo(UPDATED_AGENT_VEHICLE_NO);
        assertThat(testDeliveryAssignment.getAssignmentStatus()).isEqualTo(UPDATED_ASSIGNMENT_STATUS);
        assertThat(testDeliveryAssignment.getAssgnmentDate()).isEqualTo(UPDATED_ASSGNMENT_DATE);
        assertThat(testDeliveryAssignment.getPriority()).isEqualTo(UPDATED_PRIORITY);
        assertThat(testDeliveryAssignment.getAgentComment()).isEqualTo(UPDATED_AGENT_COMMENT);
        assertThat(testDeliveryAssignment.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDeliveryAssignment.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testDeliveryAssignment.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testDeliveryAssignment.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testDeliveryAssignment.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testDeliveryAssignment.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testDeliveryAssignment.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testDeliveryAssignment.getDeliveryAssignmentUuid()).isEqualTo(UPDATED_DELIVERY_ASSIGNMENT_UUID);
        assertThat(testDeliveryAssignment.getDeliveryStatus()).isEqualTo(UPDATED_DELIVERY_STATUS);
        assertThat(testDeliveryAssignment.getActualDeliveryDateTime()).isEqualTo(UPDATED_ACTUAL_DELIVERY_DATE_TIME);
        assertThat(testDeliveryAssignment.getDeliveryScheduleDateTime()).isEqualTo(UPDATED_DELIVERY_SCHEDULE_DATE_TIME);
    }

    @Test
    @Transactional
    void putNonExistingDeliveryAssignment() throws Exception {
        int databaseSizeBeforeUpdate = deliveryAssignmentRepository.findAll().size();
        deliveryAssignment.setDeliveryAssignmentId(count.incrementAndGet());

        // Create the DeliveryAssignment
        DeliveryAssignmentDTO deliveryAssignmentDTO = deliveryAssignmentMapper.toDto(deliveryAssignment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDeliveryAssignmentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, deliveryAssignmentDTO.getDeliveryAssignmentId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(deliveryAssignmentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeliveryAssignment in the database
        List<DeliveryAssignment> deliveryAssignmentList = deliveryAssignmentRepository.findAll();
        assertThat(deliveryAssignmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDeliveryAssignment() throws Exception {
        int databaseSizeBeforeUpdate = deliveryAssignmentRepository.findAll().size();
        deliveryAssignment.setDeliveryAssignmentId(count.incrementAndGet());

        // Create the DeliveryAssignment
        DeliveryAssignmentDTO deliveryAssignmentDTO = deliveryAssignmentMapper.toDto(deliveryAssignment);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeliveryAssignmentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(deliveryAssignmentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeliveryAssignment in the database
        List<DeliveryAssignment> deliveryAssignmentList = deliveryAssignmentRepository.findAll();
        assertThat(deliveryAssignmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDeliveryAssignment() throws Exception {
        int databaseSizeBeforeUpdate = deliveryAssignmentRepository.findAll().size();
        deliveryAssignment.setDeliveryAssignmentId(count.incrementAndGet());

        // Create the DeliveryAssignment
        DeliveryAssignmentDTO deliveryAssignmentDTO = deliveryAssignmentMapper.toDto(deliveryAssignment);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeliveryAssignmentMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(deliveryAssignmentDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DeliveryAssignment in the database
        List<DeliveryAssignment> deliveryAssignmentList = deliveryAssignmentRepository.findAll();
        assertThat(deliveryAssignmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDeliveryAssignmentWithPatch() throws Exception {
        // Initialize the database
        deliveryAssignmentRepository.saveAndFlush(deliveryAssignment);

        int databaseSizeBeforeUpdate = deliveryAssignmentRepository.findAll().size();

        // Update the deliveryAssignment using partial update
        DeliveryAssignment partialUpdatedDeliveryAssignment = new DeliveryAssignment();
        partialUpdatedDeliveryAssignment.setDeliveryAssignmentId(deliveryAssignment.getDeliveryAssignmentId());

        partialUpdatedDeliveryAssignment
            .deliveryTicketId(UPDATED_DELIVERY_TICKET_ID)
            .deliveryNo(UPDATED_DELIVERY_NO)
            .agentFirstName(UPDATED_AGENT_FIRST_NAME)
            .agentIdNo(UPDATED_AGENT_ID_NO)
            .agentAgency(UPDATED_AGENT_AGENCY)
            .agentContact2(UPDATED_AGENT_CONTACT_2)
            .agentVehicleNo(UPDATED_AGENT_VEHICLE_NO)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .deliveryScheduleDateTime(UPDATED_DELIVERY_SCHEDULE_DATE_TIME);

        restDeliveryAssignmentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDeliveryAssignment.getDeliveryAssignmentId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDeliveryAssignment))
            )
            .andExpect(status().isOk());

        // Validate the DeliveryAssignment in the database
        List<DeliveryAssignment> deliveryAssignmentList = deliveryAssignmentRepository.findAll();
        assertThat(deliveryAssignmentList).hasSize(databaseSizeBeforeUpdate);
        DeliveryAssignment testDeliveryAssignment = deliveryAssignmentList.get(deliveryAssignmentList.size() - 1);
        assertThat(testDeliveryAssignment.getDeliveryTicketId()).isEqualTo(UPDATED_DELIVERY_TICKET_ID);
        assertThat(testDeliveryAssignment.getDeliveryNo()).isEqualTo(UPDATED_DELIVERY_NO);
        assertThat(testDeliveryAssignment.getSoId()).isEqualTo(DEFAULT_SO_ID);
        assertThat(testDeliveryAssignment.getSoNo()).isEqualTo(DEFAULT_SO_NO);
        assertThat(testDeliveryAssignment.getAgentFirstName()).isEqualTo(UPDATED_AGENT_FIRST_NAME);
        assertThat(testDeliveryAssignment.getAgentLastName()).isEqualTo(DEFAULT_AGENT_LAST_NAME);
        assertThat(testDeliveryAssignment.getAgentIdNo()).isEqualTo(UPDATED_AGENT_ID_NO);
        assertThat(testDeliveryAssignment.getAgentAgency()).isEqualTo(UPDATED_AGENT_AGENCY);
        assertThat(testDeliveryAssignment.getAgentContact1()).isEqualTo(DEFAULT_AGENT_CONTACT_1);
        assertThat(testDeliveryAssignment.getAgentContact2()).isEqualTo(UPDATED_AGENT_CONTACT_2);
        assertThat(testDeliveryAssignment.getAgentVehicleNo()).isEqualTo(UPDATED_AGENT_VEHICLE_NO);
        assertThat(testDeliveryAssignment.getAssignmentStatus()).isEqualTo(DEFAULT_ASSIGNMENT_STATUS);
        assertThat(testDeliveryAssignment.getAssgnmentDate()).isEqualTo(DEFAULT_ASSGNMENT_DATE);
        assertThat(testDeliveryAssignment.getPriority()).isEqualTo(DEFAULT_PRIORITY);
        assertThat(testDeliveryAssignment.getAgentComment()).isEqualTo(DEFAULT_AGENT_COMMENT);
        assertThat(testDeliveryAssignment.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDeliveryAssignment.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testDeliveryAssignment.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testDeliveryAssignment.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testDeliveryAssignment.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testDeliveryAssignment.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testDeliveryAssignment.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testDeliveryAssignment.getDeliveryAssignmentUuid()).isEqualTo(DEFAULT_DELIVERY_ASSIGNMENT_UUID);
        assertThat(testDeliveryAssignment.getDeliveryStatus()).isEqualTo(DEFAULT_DELIVERY_STATUS);
        assertThat(testDeliveryAssignment.getActualDeliveryDateTime()).isEqualTo(DEFAULT_ACTUAL_DELIVERY_DATE_TIME);
        assertThat(testDeliveryAssignment.getDeliveryScheduleDateTime()).isEqualTo(UPDATED_DELIVERY_SCHEDULE_DATE_TIME);
    }

    @Test
    @Transactional
    void fullUpdateDeliveryAssignmentWithPatch() throws Exception {
        // Initialize the database
        deliveryAssignmentRepository.saveAndFlush(deliveryAssignment);

        int databaseSizeBeforeUpdate = deliveryAssignmentRepository.findAll().size();

        // Update the deliveryAssignment using partial update
        DeliveryAssignment partialUpdatedDeliveryAssignment = new DeliveryAssignment();
        partialUpdatedDeliveryAssignment.setDeliveryAssignmentId(deliveryAssignment.getDeliveryAssignmentId());

        partialUpdatedDeliveryAssignment
            .deliveryTicketId(UPDATED_DELIVERY_TICKET_ID)
            .deliveryNo(UPDATED_DELIVERY_NO)
            .soId(UPDATED_SO_ID)
            .soNo(UPDATED_SO_NO)
            .agentFirstName(UPDATED_AGENT_FIRST_NAME)
            .agentLastName(UPDATED_AGENT_LAST_NAME)
            .agentIdNo(UPDATED_AGENT_ID_NO)
            .agentAgency(UPDATED_AGENT_AGENCY)
            .agentContact1(UPDATED_AGENT_CONTACT_1)
            .agentContact2(UPDATED_AGENT_CONTACT_2)
            .agentVehicleNo(UPDATED_AGENT_VEHICLE_NO)
            .assignmentStatus(UPDATED_ASSIGNMENT_STATUS)
            .assgnmentDate(UPDATED_ASSGNMENT_DATE)
            .priority(UPDATED_PRIORITY)
            .agentComment(UPDATED_AGENT_COMMENT)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .deliveryAssignmentUuid(UPDATED_DELIVERY_ASSIGNMENT_UUID)
            .deliveryStatus(UPDATED_DELIVERY_STATUS)
            .actualDeliveryDateTime(UPDATED_ACTUAL_DELIVERY_DATE_TIME)
            .deliveryScheduleDateTime(UPDATED_DELIVERY_SCHEDULE_DATE_TIME);

        restDeliveryAssignmentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDeliveryAssignment.getDeliveryAssignmentId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDeliveryAssignment))
            )
            .andExpect(status().isOk());

        // Validate the DeliveryAssignment in the database
        List<DeliveryAssignment> deliveryAssignmentList = deliveryAssignmentRepository.findAll();
        assertThat(deliveryAssignmentList).hasSize(databaseSizeBeforeUpdate);
        DeliveryAssignment testDeliveryAssignment = deliveryAssignmentList.get(deliveryAssignmentList.size() - 1);
        assertThat(testDeliveryAssignment.getDeliveryTicketId()).isEqualTo(UPDATED_DELIVERY_TICKET_ID);
        assertThat(testDeliveryAssignment.getDeliveryNo()).isEqualTo(UPDATED_DELIVERY_NO);
        assertThat(testDeliveryAssignment.getSoId()).isEqualTo(UPDATED_SO_ID);
        assertThat(testDeliveryAssignment.getSoNo()).isEqualTo(UPDATED_SO_NO);
        assertThat(testDeliveryAssignment.getAgentFirstName()).isEqualTo(UPDATED_AGENT_FIRST_NAME);
        assertThat(testDeliveryAssignment.getAgentLastName()).isEqualTo(UPDATED_AGENT_LAST_NAME);
        assertThat(testDeliveryAssignment.getAgentIdNo()).isEqualTo(UPDATED_AGENT_ID_NO);
        assertThat(testDeliveryAssignment.getAgentAgency()).isEqualTo(UPDATED_AGENT_AGENCY);
        assertThat(testDeliveryAssignment.getAgentContact1()).isEqualTo(UPDATED_AGENT_CONTACT_1);
        assertThat(testDeliveryAssignment.getAgentContact2()).isEqualTo(UPDATED_AGENT_CONTACT_2);
        assertThat(testDeliveryAssignment.getAgentVehicleNo()).isEqualTo(UPDATED_AGENT_VEHICLE_NO);
        assertThat(testDeliveryAssignment.getAssignmentStatus()).isEqualTo(UPDATED_ASSIGNMENT_STATUS);
        assertThat(testDeliveryAssignment.getAssgnmentDate()).isEqualTo(UPDATED_ASSGNMENT_DATE);
        assertThat(testDeliveryAssignment.getPriority()).isEqualTo(UPDATED_PRIORITY);
        assertThat(testDeliveryAssignment.getAgentComment()).isEqualTo(UPDATED_AGENT_COMMENT);
        assertThat(testDeliveryAssignment.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDeliveryAssignment.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testDeliveryAssignment.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testDeliveryAssignment.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testDeliveryAssignment.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testDeliveryAssignment.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testDeliveryAssignment.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testDeliveryAssignment.getDeliveryAssignmentUuid()).isEqualTo(UPDATED_DELIVERY_ASSIGNMENT_UUID);
        assertThat(testDeliveryAssignment.getDeliveryStatus()).isEqualTo(UPDATED_DELIVERY_STATUS);
        assertThat(testDeliveryAssignment.getActualDeliveryDateTime()).isEqualTo(UPDATED_ACTUAL_DELIVERY_DATE_TIME);
        assertThat(testDeliveryAssignment.getDeliveryScheduleDateTime()).isEqualTo(UPDATED_DELIVERY_SCHEDULE_DATE_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingDeliveryAssignment() throws Exception {
        int databaseSizeBeforeUpdate = deliveryAssignmentRepository.findAll().size();
        deliveryAssignment.setDeliveryAssignmentId(count.incrementAndGet());

        // Create the DeliveryAssignment
        DeliveryAssignmentDTO deliveryAssignmentDTO = deliveryAssignmentMapper.toDto(deliveryAssignment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDeliveryAssignmentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, deliveryAssignmentDTO.getDeliveryAssignmentId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(deliveryAssignmentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeliveryAssignment in the database
        List<DeliveryAssignment> deliveryAssignmentList = deliveryAssignmentRepository.findAll();
        assertThat(deliveryAssignmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDeliveryAssignment() throws Exception {
        int databaseSizeBeforeUpdate = deliveryAssignmentRepository.findAll().size();
        deliveryAssignment.setDeliveryAssignmentId(count.incrementAndGet());

        // Create the DeliveryAssignment
        DeliveryAssignmentDTO deliveryAssignmentDTO = deliveryAssignmentMapper.toDto(deliveryAssignment);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeliveryAssignmentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(deliveryAssignmentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeliveryAssignment in the database
        List<DeliveryAssignment> deliveryAssignmentList = deliveryAssignmentRepository.findAll();
        assertThat(deliveryAssignmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDeliveryAssignment() throws Exception {
        int databaseSizeBeforeUpdate = deliveryAssignmentRepository.findAll().size();
        deliveryAssignment.setDeliveryAssignmentId(count.incrementAndGet());

        // Create the DeliveryAssignment
        DeliveryAssignmentDTO deliveryAssignmentDTO = deliveryAssignmentMapper.toDto(deliveryAssignment);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeliveryAssignmentMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(deliveryAssignmentDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DeliveryAssignment in the database
        List<DeliveryAssignment> deliveryAssignmentList = deliveryAssignmentRepository.findAll();
        assertThat(deliveryAssignmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDeliveryAssignment() throws Exception {
        // Initialize the database
        deliveryAssignmentRepository.saveAndFlush(deliveryAssignment);

        int databaseSizeBeforeDelete = deliveryAssignmentRepository.findAll().size();

        // Delete the deliveryAssignment
        restDeliveryAssignmentMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, deliveryAssignment.getDeliveryAssignmentId()).with(csrf()).accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DeliveryAssignment> deliveryAssignmentList = deliveryAssignmentRepository.findAll();
        assertThat(deliveryAssignmentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
