package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.UspsAddressVerificationResponse;
import com.sunknowledge.dme.rcm.repository.UspsAddressVerificationResponseRepository;
import com.sunknowledge.dme.rcm.service.dto.UspsAddressVerificationResponseDTO;
import com.sunknowledge.dme.rcm.service.mapper.UspsAddressVerificationResponseMapper;
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
 * Integration tests for the {@link UspsAddressVerificationResponseResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class UspsAddressVerificationResponseResourceIT {

    private static final Long DEFAULT_PATIENT_ID = 1L;
    private static final Long UPDATED_PATIENT_ID = 2L;

    private static final String DEFAULT_REQ_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_REQ_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_REQ_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_REQ_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_REQ_CITY = "AAAAAAAAAA";
    private static final String UPDATED_REQ_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_REQ_STATE = "AAAAAAAAAA";
    private static final String UPDATED_REQ_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_REQ_ZIP_4 = "AAAAAAAAAA";
    private static final String UPDATED_REQ_ZIP_4 = "BBBBBBBBBB";

    private static final String DEFAULT_REQ_ZIP_5 = "AAAAAAAAAA";
    private static final String UPDATED_REQ_ZIP_5 = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_REQ_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_REQ_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_RSP_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_RSP_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_RSP_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_RSP_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_RSP_CITY = "AAAAAAAAAA";
    private static final String UPDATED_RSP_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_RSP_STATE = "AAAAAAAAAA";
    private static final String UPDATED_RSP_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_RSP_ZIP_4 = "AAAAAAAAAA";
    private static final String UPDATED_RSP_ZIP_4 = "BBBBBBBBBB";

    private static final String DEFAULT_RSP_ZIP_5 = "AAAAAAAAAA";
    private static final String UPDATED_RSP_ZIP_5 = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_RSP_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_RSP_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DPV_FOOTNOTES_CODE = "AAAAAAAAAA";
    private static final String UPDATED_DPV_FOOTNOTES_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DPV_FOOTNOTES_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DPV_FOOTNOTES_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final UUID DEFAULT_USPS_ADDRESS_VERIFICATION_RESPONSE_UUID = UUID.randomUUID();
    private static final UUID UPDATED_USPS_ADDRESS_VERIFICATION_RESPONSE_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/usps-address-verification-responses";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{uspsAddressVerificationId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private UspsAddressVerificationResponseRepository uspsAddressVerificationResponseRepository;

    @Autowired
    private UspsAddressVerificationResponseMapper uspsAddressVerificationResponseMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUspsAddressVerificationResponseMockMvc;

    private UspsAddressVerificationResponse uspsAddressVerificationResponse;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UspsAddressVerificationResponse createEntity(EntityManager em) {
        UspsAddressVerificationResponse uspsAddressVerificationResponse = new UspsAddressVerificationResponse()
            .patientId(DEFAULT_PATIENT_ID)
            .reqAddressLine1(DEFAULT_REQ_ADDRESS_LINE_1)
            .reqAddressLine2(DEFAULT_REQ_ADDRESS_LINE_2)
            .reqCity(DEFAULT_REQ_CITY)
            .reqState(DEFAULT_REQ_STATE)
            .reqZip4(DEFAULT_REQ_ZIP_4)
            .reqZip5(DEFAULT_REQ_ZIP_5)
            .reqDate(DEFAULT_REQ_DATE)
            .rspAddressLine1(DEFAULT_RSP_ADDRESS_LINE_1)
            .rspAddressLine2(DEFAULT_RSP_ADDRESS_LINE_2)
            .rspCity(DEFAULT_RSP_CITY)
            .rspState(DEFAULT_RSP_STATE)
            .rspZip4(DEFAULT_RSP_ZIP_4)
            .rspZip5(DEFAULT_RSP_ZIP_5)
            .rspDate(DEFAULT_RSP_DATE)
            .dpvFootnotesCode(DEFAULT_DPV_FOOTNOTES_CODE)
            .dpvFootnotesDescription(DEFAULT_DPV_FOOTNOTES_DESCRIPTION)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .uspsAddressVerificationResponseUuid(DEFAULT_USPS_ADDRESS_VERIFICATION_RESPONSE_UUID);
        return uspsAddressVerificationResponse;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UspsAddressVerificationResponse createUpdatedEntity(EntityManager em) {
        UspsAddressVerificationResponse uspsAddressVerificationResponse = new UspsAddressVerificationResponse()
            .patientId(UPDATED_PATIENT_ID)
            .reqAddressLine1(UPDATED_REQ_ADDRESS_LINE_1)
            .reqAddressLine2(UPDATED_REQ_ADDRESS_LINE_2)
            .reqCity(UPDATED_REQ_CITY)
            .reqState(UPDATED_REQ_STATE)
            .reqZip4(UPDATED_REQ_ZIP_4)
            .reqZip5(UPDATED_REQ_ZIP_5)
            .reqDate(UPDATED_REQ_DATE)
            .rspAddressLine1(UPDATED_RSP_ADDRESS_LINE_1)
            .rspAddressLine2(UPDATED_RSP_ADDRESS_LINE_2)
            .rspCity(UPDATED_RSP_CITY)
            .rspState(UPDATED_RSP_STATE)
            .rspZip4(UPDATED_RSP_ZIP_4)
            .rspZip5(UPDATED_RSP_ZIP_5)
            .rspDate(UPDATED_RSP_DATE)
            .dpvFootnotesCode(UPDATED_DPV_FOOTNOTES_CODE)
            .dpvFootnotesDescription(UPDATED_DPV_FOOTNOTES_DESCRIPTION)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .uspsAddressVerificationResponseUuid(UPDATED_USPS_ADDRESS_VERIFICATION_RESPONSE_UUID);
        return uspsAddressVerificationResponse;
    }

    @BeforeEach
    public void initTest() {
        uspsAddressVerificationResponse = createEntity(em);
    }

    @Test
    @Transactional
    void createUspsAddressVerificationResponse() throws Exception {
        int databaseSizeBeforeCreate = uspsAddressVerificationResponseRepository.findAll().size();
        // Create the UspsAddressVerificationResponse
        UspsAddressVerificationResponseDTO uspsAddressVerificationResponseDTO = uspsAddressVerificationResponseMapper.toDto(
            uspsAddressVerificationResponse
        );
        restUspsAddressVerificationResponseMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(uspsAddressVerificationResponseDTO))
            )
            .andExpect(status().isCreated());

        // Validate the UspsAddressVerificationResponse in the database
        List<UspsAddressVerificationResponse> uspsAddressVerificationResponseList = uspsAddressVerificationResponseRepository.findAll();
        assertThat(uspsAddressVerificationResponseList).hasSize(databaseSizeBeforeCreate + 1);
        UspsAddressVerificationResponse testUspsAddressVerificationResponse = uspsAddressVerificationResponseList.get(
            uspsAddressVerificationResponseList.size() - 1
        );
        assertThat(testUspsAddressVerificationResponse.getPatientId()).isEqualTo(DEFAULT_PATIENT_ID);
        assertThat(testUspsAddressVerificationResponse.getReqAddressLine1()).isEqualTo(DEFAULT_REQ_ADDRESS_LINE_1);
        assertThat(testUspsAddressVerificationResponse.getReqAddressLine2()).isEqualTo(DEFAULT_REQ_ADDRESS_LINE_2);
        assertThat(testUspsAddressVerificationResponse.getReqCity()).isEqualTo(DEFAULT_REQ_CITY);
        assertThat(testUspsAddressVerificationResponse.getReqState()).isEqualTo(DEFAULT_REQ_STATE);
        assertThat(testUspsAddressVerificationResponse.getReqZip4()).isEqualTo(DEFAULT_REQ_ZIP_4);
        assertThat(testUspsAddressVerificationResponse.getReqZip5()).isEqualTo(DEFAULT_REQ_ZIP_5);
        assertThat(testUspsAddressVerificationResponse.getReqDate()).isEqualTo(DEFAULT_REQ_DATE);
        assertThat(testUspsAddressVerificationResponse.getRspAddressLine1()).isEqualTo(DEFAULT_RSP_ADDRESS_LINE_1);
        assertThat(testUspsAddressVerificationResponse.getRspAddressLine2()).isEqualTo(DEFAULT_RSP_ADDRESS_LINE_2);
        assertThat(testUspsAddressVerificationResponse.getRspCity()).isEqualTo(DEFAULT_RSP_CITY);
        assertThat(testUspsAddressVerificationResponse.getRspState()).isEqualTo(DEFAULT_RSP_STATE);
        assertThat(testUspsAddressVerificationResponse.getRspZip4()).isEqualTo(DEFAULT_RSP_ZIP_4);
        assertThat(testUspsAddressVerificationResponse.getRspZip5()).isEqualTo(DEFAULT_RSP_ZIP_5);
        assertThat(testUspsAddressVerificationResponse.getRspDate()).isEqualTo(DEFAULT_RSP_DATE);
        assertThat(testUspsAddressVerificationResponse.getDpvFootnotesCode()).isEqualTo(DEFAULT_DPV_FOOTNOTES_CODE);
        assertThat(testUspsAddressVerificationResponse.getDpvFootnotesDescription()).isEqualTo(DEFAULT_DPV_FOOTNOTES_DESCRIPTION);
        assertThat(testUspsAddressVerificationResponse.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testUspsAddressVerificationResponse.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testUspsAddressVerificationResponse.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testUspsAddressVerificationResponse.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testUspsAddressVerificationResponse.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testUspsAddressVerificationResponse.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testUspsAddressVerificationResponse.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testUspsAddressVerificationResponse.getUspsAddressVerificationResponseUuid())
            .isEqualTo(DEFAULT_USPS_ADDRESS_VERIFICATION_RESPONSE_UUID);
    }

    @Test
    @Transactional
    void createUspsAddressVerificationResponseWithExistingId() throws Exception {
        // Create the UspsAddressVerificationResponse with an existing ID
        uspsAddressVerificationResponse.setUspsAddressVerificationId(1L);
        UspsAddressVerificationResponseDTO uspsAddressVerificationResponseDTO = uspsAddressVerificationResponseMapper.toDto(
            uspsAddressVerificationResponse
        );

        int databaseSizeBeforeCreate = uspsAddressVerificationResponseRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restUspsAddressVerificationResponseMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(uspsAddressVerificationResponseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UspsAddressVerificationResponse in the database
        List<UspsAddressVerificationResponse> uspsAddressVerificationResponseList = uspsAddressVerificationResponseRepository.findAll();
        assertThat(uspsAddressVerificationResponseList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllUspsAddressVerificationResponses() throws Exception {
        // Initialize the database
        uspsAddressVerificationResponseRepository.saveAndFlush(uspsAddressVerificationResponse);

        // Get all the uspsAddressVerificationResponseList
        restUspsAddressVerificationResponseMockMvc
            .perform(get(ENTITY_API_URL + "?sort=uspsAddressVerificationId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].uspsAddressVerificationId")
                    .value(hasItem(uspsAddressVerificationResponse.getUspsAddressVerificationId().intValue()))
            )
            .andExpect(jsonPath("$.[*].patientId").value(hasItem(DEFAULT_PATIENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].reqAddressLine1").value(hasItem(DEFAULT_REQ_ADDRESS_LINE_1)))
            .andExpect(jsonPath("$.[*].reqAddressLine2").value(hasItem(DEFAULT_REQ_ADDRESS_LINE_2)))
            .andExpect(jsonPath("$.[*].reqCity").value(hasItem(DEFAULT_REQ_CITY)))
            .andExpect(jsonPath("$.[*].reqState").value(hasItem(DEFAULT_REQ_STATE)))
            .andExpect(jsonPath("$.[*].reqZip4").value(hasItem(DEFAULT_REQ_ZIP_4)))
            .andExpect(jsonPath("$.[*].reqZip5").value(hasItem(DEFAULT_REQ_ZIP_5)))
            .andExpect(jsonPath("$.[*].reqDate").value(hasItem(DEFAULT_REQ_DATE.toString())))
            .andExpect(jsonPath("$.[*].rspAddressLine1").value(hasItem(DEFAULT_RSP_ADDRESS_LINE_1)))
            .andExpect(jsonPath("$.[*].rspAddressLine2").value(hasItem(DEFAULT_RSP_ADDRESS_LINE_2)))
            .andExpect(jsonPath("$.[*].rspCity").value(hasItem(DEFAULT_RSP_CITY)))
            .andExpect(jsonPath("$.[*].rspState").value(hasItem(DEFAULT_RSP_STATE)))
            .andExpect(jsonPath("$.[*].rspZip4").value(hasItem(DEFAULT_RSP_ZIP_4)))
            .andExpect(jsonPath("$.[*].rspZip5").value(hasItem(DEFAULT_RSP_ZIP_5)))
            .andExpect(jsonPath("$.[*].rspDate").value(hasItem(DEFAULT_RSP_DATE.toString())))
            .andExpect(jsonPath("$.[*].dpvFootnotesCode").value(hasItem(DEFAULT_DPV_FOOTNOTES_CODE)))
            .andExpect(jsonPath("$.[*].dpvFootnotesDescription").value(hasItem(DEFAULT_DPV_FOOTNOTES_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(
                jsonPath("$.[*].uspsAddressVerificationResponseUuid")
                    .value(hasItem(DEFAULT_USPS_ADDRESS_VERIFICATION_RESPONSE_UUID.toString()))
            );
    }

    @Test
    @Transactional
    void getUspsAddressVerificationResponse() throws Exception {
        // Initialize the database
        uspsAddressVerificationResponseRepository.saveAndFlush(uspsAddressVerificationResponse);

        // Get the uspsAddressVerificationResponse
        restUspsAddressVerificationResponseMockMvc
            .perform(get(ENTITY_API_URL_ID, uspsAddressVerificationResponse.getUspsAddressVerificationId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.uspsAddressVerificationId").value(uspsAddressVerificationResponse.getUspsAddressVerificationId().intValue())
            )
            .andExpect(jsonPath("$.patientId").value(DEFAULT_PATIENT_ID.intValue()))
            .andExpect(jsonPath("$.reqAddressLine1").value(DEFAULT_REQ_ADDRESS_LINE_1))
            .andExpect(jsonPath("$.reqAddressLine2").value(DEFAULT_REQ_ADDRESS_LINE_2))
            .andExpect(jsonPath("$.reqCity").value(DEFAULT_REQ_CITY))
            .andExpect(jsonPath("$.reqState").value(DEFAULT_REQ_STATE))
            .andExpect(jsonPath("$.reqZip4").value(DEFAULT_REQ_ZIP_4))
            .andExpect(jsonPath("$.reqZip5").value(DEFAULT_REQ_ZIP_5))
            .andExpect(jsonPath("$.reqDate").value(DEFAULT_REQ_DATE.toString()))
            .andExpect(jsonPath("$.rspAddressLine1").value(DEFAULT_RSP_ADDRESS_LINE_1))
            .andExpect(jsonPath("$.rspAddressLine2").value(DEFAULT_RSP_ADDRESS_LINE_2))
            .andExpect(jsonPath("$.rspCity").value(DEFAULT_RSP_CITY))
            .andExpect(jsonPath("$.rspState").value(DEFAULT_RSP_STATE))
            .andExpect(jsonPath("$.rspZip4").value(DEFAULT_RSP_ZIP_4))
            .andExpect(jsonPath("$.rspZip5").value(DEFAULT_RSP_ZIP_5))
            .andExpect(jsonPath("$.rspDate").value(DEFAULT_RSP_DATE.toString()))
            .andExpect(jsonPath("$.dpvFootnotesCode").value(DEFAULT_DPV_FOOTNOTES_CODE))
            .andExpect(jsonPath("$.dpvFootnotesDescription").value(DEFAULT_DPV_FOOTNOTES_DESCRIPTION))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.uspsAddressVerificationResponseUuid").value(DEFAULT_USPS_ADDRESS_VERIFICATION_RESPONSE_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingUspsAddressVerificationResponse() throws Exception {
        // Get the uspsAddressVerificationResponse
        restUspsAddressVerificationResponseMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewUspsAddressVerificationResponse() throws Exception {
        // Initialize the database
        uspsAddressVerificationResponseRepository.saveAndFlush(uspsAddressVerificationResponse);

        int databaseSizeBeforeUpdate = uspsAddressVerificationResponseRepository.findAll().size();

        // Update the uspsAddressVerificationResponse
        UspsAddressVerificationResponse updatedUspsAddressVerificationResponse = uspsAddressVerificationResponseRepository
            .findById(uspsAddressVerificationResponse.getUspsAddressVerificationId())
            .get();
        // Disconnect from session so that the updates on updatedUspsAddressVerificationResponse are not directly saved in db
        em.detach(updatedUspsAddressVerificationResponse);
        updatedUspsAddressVerificationResponse
            .patientId(UPDATED_PATIENT_ID)
            .reqAddressLine1(UPDATED_REQ_ADDRESS_LINE_1)
            .reqAddressLine2(UPDATED_REQ_ADDRESS_LINE_2)
            .reqCity(UPDATED_REQ_CITY)
            .reqState(UPDATED_REQ_STATE)
            .reqZip4(UPDATED_REQ_ZIP_4)
            .reqZip5(UPDATED_REQ_ZIP_5)
            .reqDate(UPDATED_REQ_DATE)
            .rspAddressLine1(UPDATED_RSP_ADDRESS_LINE_1)
            .rspAddressLine2(UPDATED_RSP_ADDRESS_LINE_2)
            .rspCity(UPDATED_RSP_CITY)
            .rspState(UPDATED_RSP_STATE)
            .rspZip4(UPDATED_RSP_ZIP_4)
            .rspZip5(UPDATED_RSP_ZIP_5)
            .rspDate(UPDATED_RSP_DATE)
            .dpvFootnotesCode(UPDATED_DPV_FOOTNOTES_CODE)
            .dpvFootnotesDescription(UPDATED_DPV_FOOTNOTES_DESCRIPTION)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .uspsAddressVerificationResponseUuid(UPDATED_USPS_ADDRESS_VERIFICATION_RESPONSE_UUID);
        UspsAddressVerificationResponseDTO uspsAddressVerificationResponseDTO = uspsAddressVerificationResponseMapper.toDto(
            updatedUspsAddressVerificationResponse
        );

        restUspsAddressVerificationResponseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, uspsAddressVerificationResponseDTO.getUspsAddressVerificationId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(uspsAddressVerificationResponseDTO))
            )
            .andExpect(status().isOk());

        // Validate the UspsAddressVerificationResponse in the database
        List<UspsAddressVerificationResponse> uspsAddressVerificationResponseList = uspsAddressVerificationResponseRepository.findAll();
        assertThat(uspsAddressVerificationResponseList).hasSize(databaseSizeBeforeUpdate);
        UspsAddressVerificationResponse testUspsAddressVerificationResponse = uspsAddressVerificationResponseList.get(
            uspsAddressVerificationResponseList.size() - 1
        );
        assertThat(testUspsAddressVerificationResponse.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testUspsAddressVerificationResponse.getReqAddressLine1()).isEqualTo(UPDATED_REQ_ADDRESS_LINE_1);
        assertThat(testUspsAddressVerificationResponse.getReqAddressLine2()).isEqualTo(UPDATED_REQ_ADDRESS_LINE_2);
        assertThat(testUspsAddressVerificationResponse.getReqCity()).isEqualTo(UPDATED_REQ_CITY);
        assertThat(testUspsAddressVerificationResponse.getReqState()).isEqualTo(UPDATED_REQ_STATE);
        assertThat(testUspsAddressVerificationResponse.getReqZip4()).isEqualTo(UPDATED_REQ_ZIP_4);
        assertThat(testUspsAddressVerificationResponse.getReqZip5()).isEqualTo(UPDATED_REQ_ZIP_5);
        assertThat(testUspsAddressVerificationResponse.getReqDate()).isEqualTo(UPDATED_REQ_DATE);
        assertThat(testUspsAddressVerificationResponse.getRspAddressLine1()).isEqualTo(UPDATED_RSP_ADDRESS_LINE_1);
        assertThat(testUspsAddressVerificationResponse.getRspAddressLine2()).isEqualTo(UPDATED_RSP_ADDRESS_LINE_2);
        assertThat(testUspsAddressVerificationResponse.getRspCity()).isEqualTo(UPDATED_RSP_CITY);
        assertThat(testUspsAddressVerificationResponse.getRspState()).isEqualTo(UPDATED_RSP_STATE);
        assertThat(testUspsAddressVerificationResponse.getRspZip4()).isEqualTo(UPDATED_RSP_ZIP_4);
        assertThat(testUspsAddressVerificationResponse.getRspZip5()).isEqualTo(UPDATED_RSP_ZIP_5);
        assertThat(testUspsAddressVerificationResponse.getRspDate()).isEqualTo(UPDATED_RSP_DATE);
        assertThat(testUspsAddressVerificationResponse.getDpvFootnotesCode()).isEqualTo(UPDATED_DPV_FOOTNOTES_CODE);
        assertThat(testUspsAddressVerificationResponse.getDpvFootnotesDescription()).isEqualTo(UPDATED_DPV_FOOTNOTES_DESCRIPTION);
        assertThat(testUspsAddressVerificationResponse.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testUspsAddressVerificationResponse.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testUspsAddressVerificationResponse.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testUspsAddressVerificationResponse.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testUspsAddressVerificationResponse.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testUspsAddressVerificationResponse.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testUspsAddressVerificationResponse.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testUspsAddressVerificationResponse.getUspsAddressVerificationResponseUuid())
            .isEqualTo(UPDATED_USPS_ADDRESS_VERIFICATION_RESPONSE_UUID);
    }

    @Test
    @Transactional
    void putNonExistingUspsAddressVerificationResponse() throws Exception {
        int databaseSizeBeforeUpdate = uspsAddressVerificationResponseRepository.findAll().size();
        uspsAddressVerificationResponse.setUspsAddressVerificationId(count.incrementAndGet());

        // Create the UspsAddressVerificationResponse
        UspsAddressVerificationResponseDTO uspsAddressVerificationResponseDTO = uspsAddressVerificationResponseMapper.toDto(
            uspsAddressVerificationResponse
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUspsAddressVerificationResponseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, uspsAddressVerificationResponseDTO.getUspsAddressVerificationId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(uspsAddressVerificationResponseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UspsAddressVerificationResponse in the database
        List<UspsAddressVerificationResponse> uspsAddressVerificationResponseList = uspsAddressVerificationResponseRepository.findAll();
        assertThat(uspsAddressVerificationResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchUspsAddressVerificationResponse() throws Exception {
        int databaseSizeBeforeUpdate = uspsAddressVerificationResponseRepository.findAll().size();
        uspsAddressVerificationResponse.setUspsAddressVerificationId(count.incrementAndGet());

        // Create the UspsAddressVerificationResponse
        UspsAddressVerificationResponseDTO uspsAddressVerificationResponseDTO = uspsAddressVerificationResponseMapper.toDto(
            uspsAddressVerificationResponse
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUspsAddressVerificationResponseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(uspsAddressVerificationResponseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UspsAddressVerificationResponse in the database
        List<UspsAddressVerificationResponse> uspsAddressVerificationResponseList = uspsAddressVerificationResponseRepository.findAll();
        assertThat(uspsAddressVerificationResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamUspsAddressVerificationResponse() throws Exception {
        int databaseSizeBeforeUpdate = uspsAddressVerificationResponseRepository.findAll().size();
        uspsAddressVerificationResponse.setUspsAddressVerificationId(count.incrementAndGet());

        // Create the UspsAddressVerificationResponse
        UspsAddressVerificationResponseDTO uspsAddressVerificationResponseDTO = uspsAddressVerificationResponseMapper.toDto(
            uspsAddressVerificationResponse
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUspsAddressVerificationResponseMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(uspsAddressVerificationResponseDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the UspsAddressVerificationResponse in the database
        List<UspsAddressVerificationResponse> uspsAddressVerificationResponseList = uspsAddressVerificationResponseRepository.findAll();
        assertThat(uspsAddressVerificationResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateUspsAddressVerificationResponseWithPatch() throws Exception {
        // Initialize the database
        uspsAddressVerificationResponseRepository.saveAndFlush(uspsAddressVerificationResponse);

        int databaseSizeBeforeUpdate = uspsAddressVerificationResponseRepository.findAll().size();

        // Update the uspsAddressVerificationResponse using partial update
        UspsAddressVerificationResponse partialUpdatedUspsAddressVerificationResponse = new UspsAddressVerificationResponse();
        partialUpdatedUspsAddressVerificationResponse.setUspsAddressVerificationId(
            uspsAddressVerificationResponse.getUspsAddressVerificationId()
        );

        partialUpdatedUspsAddressVerificationResponse
            .reqCity(UPDATED_REQ_CITY)
            .reqZip4(UPDATED_REQ_ZIP_4)
            .reqDate(UPDATED_REQ_DATE)
            .rspAddressLine2(UPDATED_RSP_ADDRESS_LINE_2)
            .rspCity(UPDATED_RSP_CITY)
            .rspZip4(UPDATED_RSP_ZIP_4)
            .createdById(UPDATED_CREATED_BY_ID)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE);

        restUspsAddressVerificationResponseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUspsAddressVerificationResponse.getUspsAddressVerificationId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUspsAddressVerificationResponse))
            )
            .andExpect(status().isOk());

        // Validate the UspsAddressVerificationResponse in the database
        List<UspsAddressVerificationResponse> uspsAddressVerificationResponseList = uspsAddressVerificationResponseRepository.findAll();
        assertThat(uspsAddressVerificationResponseList).hasSize(databaseSizeBeforeUpdate);
        UspsAddressVerificationResponse testUspsAddressVerificationResponse = uspsAddressVerificationResponseList.get(
            uspsAddressVerificationResponseList.size() - 1
        );
        assertThat(testUspsAddressVerificationResponse.getPatientId()).isEqualTo(DEFAULT_PATIENT_ID);
        assertThat(testUspsAddressVerificationResponse.getReqAddressLine1()).isEqualTo(DEFAULT_REQ_ADDRESS_LINE_1);
        assertThat(testUspsAddressVerificationResponse.getReqAddressLine2()).isEqualTo(DEFAULT_REQ_ADDRESS_LINE_2);
        assertThat(testUspsAddressVerificationResponse.getReqCity()).isEqualTo(UPDATED_REQ_CITY);
        assertThat(testUspsAddressVerificationResponse.getReqState()).isEqualTo(DEFAULT_REQ_STATE);
        assertThat(testUspsAddressVerificationResponse.getReqZip4()).isEqualTo(UPDATED_REQ_ZIP_4);
        assertThat(testUspsAddressVerificationResponse.getReqZip5()).isEqualTo(DEFAULT_REQ_ZIP_5);
        assertThat(testUspsAddressVerificationResponse.getReqDate()).isEqualTo(UPDATED_REQ_DATE);
        assertThat(testUspsAddressVerificationResponse.getRspAddressLine1()).isEqualTo(DEFAULT_RSP_ADDRESS_LINE_1);
        assertThat(testUspsAddressVerificationResponse.getRspAddressLine2()).isEqualTo(UPDATED_RSP_ADDRESS_LINE_2);
        assertThat(testUspsAddressVerificationResponse.getRspCity()).isEqualTo(UPDATED_RSP_CITY);
        assertThat(testUspsAddressVerificationResponse.getRspState()).isEqualTo(DEFAULT_RSP_STATE);
        assertThat(testUspsAddressVerificationResponse.getRspZip4()).isEqualTo(UPDATED_RSP_ZIP_4);
        assertThat(testUspsAddressVerificationResponse.getRspZip5()).isEqualTo(DEFAULT_RSP_ZIP_5);
        assertThat(testUspsAddressVerificationResponse.getRspDate()).isEqualTo(DEFAULT_RSP_DATE);
        assertThat(testUspsAddressVerificationResponse.getDpvFootnotesCode()).isEqualTo(DEFAULT_DPV_FOOTNOTES_CODE);
        assertThat(testUspsAddressVerificationResponse.getDpvFootnotesDescription()).isEqualTo(DEFAULT_DPV_FOOTNOTES_DESCRIPTION);
        assertThat(testUspsAddressVerificationResponse.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testUspsAddressVerificationResponse.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testUspsAddressVerificationResponse.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testUspsAddressVerificationResponse.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testUspsAddressVerificationResponse.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testUspsAddressVerificationResponse.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testUspsAddressVerificationResponse.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testUspsAddressVerificationResponse.getUspsAddressVerificationResponseUuid())
            .isEqualTo(DEFAULT_USPS_ADDRESS_VERIFICATION_RESPONSE_UUID);
    }

    @Test
    @Transactional
    void fullUpdateUspsAddressVerificationResponseWithPatch() throws Exception {
        // Initialize the database
        uspsAddressVerificationResponseRepository.saveAndFlush(uspsAddressVerificationResponse);

        int databaseSizeBeforeUpdate = uspsAddressVerificationResponseRepository.findAll().size();

        // Update the uspsAddressVerificationResponse using partial update
        UspsAddressVerificationResponse partialUpdatedUspsAddressVerificationResponse = new UspsAddressVerificationResponse();
        partialUpdatedUspsAddressVerificationResponse.setUspsAddressVerificationId(
            uspsAddressVerificationResponse.getUspsAddressVerificationId()
        );

        partialUpdatedUspsAddressVerificationResponse
            .patientId(UPDATED_PATIENT_ID)
            .reqAddressLine1(UPDATED_REQ_ADDRESS_LINE_1)
            .reqAddressLine2(UPDATED_REQ_ADDRESS_LINE_2)
            .reqCity(UPDATED_REQ_CITY)
            .reqState(UPDATED_REQ_STATE)
            .reqZip4(UPDATED_REQ_ZIP_4)
            .reqZip5(UPDATED_REQ_ZIP_5)
            .reqDate(UPDATED_REQ_DATE)
            .rspAddressLine1(UPDATED_RSP_ADDRESS_LINE_1)
            .rspAddressLine2(UPDATED_RSP_ADDRESS_LINE_2)
            .rspCity(UPDATED_RSP_CITY)
            .rspState(UPDATED_RSP_STATE)
            .rspZip4(UPDATED_RSP_ZIP_4)
            .rspZip5(UPDATED_RSP_ZIP_5)
            .rspDate(UPDATED_RSP_DATE)
            .dpvFootnotesCode(UPDATED_DPV_FOOTNOTES_CODE)
            .dpvFootnotesDescription(UPDATED_DPV_FOOTNOTES_DESCRIPTION)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .uspsAddressVerificationResponseUuid(UPDATED_USPS_ADDRESS_VERIFICATION_RESPONSE_UUID);

        restUspsAddressVerificationResponseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUspsAddressVerificationResponse.getUspsAddressVerificationId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUspsAddressVerificationResponse))
            )
            .andExpect(status().isOk());

        // Validate the UspsAddressVerificationResponse in the database
        List<UspsAddressVerificationResponse> uspsAddressVerificationResponseList = uspsAddressVerificationResponseRepository.findAll();
        assertThat(uspsAddressVerificationResponseList).hasSize(databaseSizeBeforeUpdate);
        UspsAddressVerificationResponse testUspsAddressVerificationResponse = uspsAddressVerificationResponseList.get(
            uspsAddressVerificationResponseList.size() - 1
        );
        assertThat(testUspsAddressVerificationResponse.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testUspsAddressVerificationResponse.getReqAddressLine1()).isEqualTo(UPDATED_REQ_ADDRESS_LINE_1);
        assertThat(testUspsAddressVerificationResponse.getReqAddressLine2()).isEqualTo(UPDATED_REQ_ADDRESS_LINE_2);
        assertThat(testUspsAddressVerificationResponse.getReqCity()).isEqualTo(UPDATED_REQ_CITY);
        assertThat(testUspsAddressVerificationResponse.getReqState()).isEqualTo(UPDATED_REQ_STATE);
        assertThat(testUspsAddressVerificationResponse.getReqZip4()).isEqualTo(UPDATED_REQ_ZIP_4);
        assertThat(testUspsAddressVerificationResponse.getReqZip5()).isEqualTo(UPDATED_REQ_ZIP_5);
        assertThat(testUspsAddressVerificationResponse.getReqDate()).isEqualTo(UPDATED_REQ_DATE);
        assertThat(testUspsAddressVerificationResponse.getRspAddressLine1()).isEqualTo(UPDATED_RSP_ADDRESS_LINE_1);
        assertThat(testUspsAddressVerificationResponse.getRspAddressLine2()).isEqualTo(UPDATED_RSP_ADDRESS_LINE_2);
        assertThat(testUspsAddressVerificationResponse.getRspCity()).isEqualTo(UPDATED_RSP_CITY);
        assertThat(testUspsAddressVerificationResponse.getRspState()).isEqualTo(UPDATED_RSP_STATE);
        assertThat(testUspsAddressVerificationResponse.getRspZip4()).isEqualTo(UPDATED_RSP_ZIP_4);
        assertThat(testUspsAddressVerificationResponse.getRspZip5()).isEqualTo(UPDATED_RSP_ZIP_5);
        assertThat(testUspsAddressVerificationResponse.getRspDate()).isEqualTo(UPDATED_RSP_DATE);
        assertThat(testUspsAddressVerificationResponse.getDpvFootnotesCode()).isEqualTo(UPDATED_DPV_FOOTNOTES_CODE);
        assertThat(testUspsAddressVerificationResponse.getDpvFootnotesDescription()).isEqualTo(UPDATED_DPV_FOOTNOTES_DESCRIPTION);
        assertThat(testUspsAddressVerificationResponse.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testUspsAddressVerificationResponse.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testUspsAddressVerificationResponse.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testUspsAddressVerificationResponse.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testUspsAddressVerificationResponse.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testUspsAddressVerificationResponse.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testUspsAddressVerificationResponse.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testUspsAddressVerificationResponse.getUspsAddressVerificationResponseUuid())
            .isEqualTo(UPDATED_USPS_ADDRESS_VERIFICATION_RESPONSE_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingUspsAddressVerificationResponse() throws Exception {
        int databaseSizeBeforeUpdate = uspsAddressVerificationResponseRepository.findAll().size();
        uspsAddressVerificationResponse.setUspsAddressVerificationId(count.incrementAndGet());

        // Create the UspsAddressVerificationResponse
        UspsAddressVerificationResponseDTO uspsAddressVerificationResponseDTO = uspsAddressVerificationResponseMapper.toDto(
            uspsAddressVerificationResponse
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUspsAddressVerificationResponseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, uspsAddressVerificationResponseDTO.getUspsAddressVerificationId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(uspsAddressVerificationResponseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UspsAddressVerificationResponse in the database
        List<UspsAddressVerificationResponse> uspsAddressVerificationResponseList = uspsAddressVerificationResponseRepository.findAll();
        assertThat(uspsAddressVerificationResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchUspsAddressVerificationResponse() throws Exception {
        int databaseSizeBeforeUpdate = uspsAddressVerificationResponseRepository.findAll().size();
        uspsAddressVerificationResponse.setUspsAddressVerificationId(count.incrementAndGet());

        // Create the UspsAddressVerificationResponse
        UspsAddressVerificationResponseDTO uspsAddressVerificationResponseDTO = uspsAddressVerificationResponseMapper.toDto(
            uspsAddressVerificationResponse
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUspsAddressVerificationResponseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(uspsAddressVerificationResponseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UspsAddressVerificationResponse in the database
        List<UspsAddressVerificationResponse> uspsAddressVerificationResponseList = uspsAddressVerificationResponseRepository.findAll();
        assertThat(uspsAddressVerificationResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamUspsAddressVerificationResponse() throws Exception {
        int databaseSizeBeforeUpdate = uspsAddressVerificationResponseRepository.findAll().size();
        uspsAddressVerificationResponse.setUspsAddressVerificationId(count.incrementAndGet());

        // Create the UspsAddressVerificationResponse
        UspsAddressVerificationResponseDTO uspsAddressVerificationResponseDTO = uspsAddressVerificationResponseMapper.toDto(
            uspsAddressVerificationResponse
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUspsAddressVerificationResponseMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(uspsAddressVerificationResponseDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the UspsAddressVerificationResponse in the database
        List<UspsAddressVerificationResponse> uspsAddressVerificationResponseList = uspsAddressVerificationResponseRepository.findAll();
        assertThat(uspsAddressVerificationResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteUspsAddressVerificationResponse() throws Exception {
        // Initialize the database
        uspsAddressVerificationResponseRepository.saveAndFlush(uspsAddressVerificationResponse);

        int databaseSizeBeforeDelete = uspsAddressVerificationResponseRepository.findAll().size();

        // Delete the uspsAddressVerificationResponse
        restUspsAddressVerificationResponseMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, uspsAddressVerificationResponse.getUspsAddressVerificationId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UspsAddressVerificationResponse> uspsAddressVerificationResponseList = uspsAddressVerificationResponseRepository.findAll();
        assertThat(uspsAddressVerificationResponseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
