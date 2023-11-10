package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.AddressVerificationResponse;
import com.sunknowledge.dme.rcm.repository.AddressVerificationResponseRepository;
import com.sunknowledge.dme.rcm.service.dto.AddressVerificationResponseDTO;
import com.sunknowledge.dme.rcm.service.mapper.AddressVerificationResponseMapper;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
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
 * Integration tests for the {@link AddressVerificationResponseResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AddressVerificationResponseResourceIT {

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

    private static final String ENTITY_API_URL = "/api/address-verification-responses";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{uspsAddressVerificationId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AddressVerificationResponseRepository addressVerificationResponseRepository;

    @Autowired
    private AddressVerificationResponseMapper addressVerificationResponseMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAddressVerificationResponseMockMvc;

    private AddressVerificationResponse addressVerificationResponse;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AddressVerificationResponse createEntity(EntityManager em) {
        AddressVerificationResponse addressVerificationResponse = new AddressVerificationResponse()
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
            .status(DEFAULT_STATUS);
        return addressVerificationResponse;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AddressVerificationResponse createUpdatedEntity(EntityManager em) {
        AddressVerificationResponse addressVerificationResponse = new AddressVerificationResponse()
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
            .status(UPDATED_STATUS);
        return addressVerificationResponse;
    }

    @BeforeEach
    public void initTest() {
        addressVerificationResponse = createEntity(em);
    }

    @Test
    @Transactional
    void createAddressVerificationResponse() throws Exception {
        int databaseSizeBeforeCreate = addressVerificationResponseRepository.findAll().size();
        // Create the AddressVerificationResponse
        AddressVerificationResponseDTO addressVerificationResponseDTO = addressVerificationResponseMapper.toDto(
            addressVerificationResponse
        );
        restAddressVerificationResponseMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(addressVerificationResponseDTO))
            )
            .andExpect(status().isCreated());

        // Validate the AddressVerificationResponse in the database
        List<AddressVerificationResponse> addressVerificationResponseList = addressVerificationResponseRepository.findAll();
        assertThat(addressVerificationResponseList).hasSize(databaseSizeBeforeCreate + 1);
        AddressVerificationResponse testAddressVerificationResponse = addressVerificationResponseList.get(
            addressVerificationResponseList.size() - 1
        );
        assertThat(testAddressVerificationResponse.getPatientId()).isEqualTo(DEFAULT_PATIENT_ID);
        assertThat(testAddressVerificationResponse.getReqAddressLine1()).isEqualTo(DEFAULT_REQ_ADDRESS_LINE_1);
        assertThat(testAddressVerificationResponse.getReqAddressLine2()).isEqualTo(DEFAULT_REQ_ADDRESS_LINE_2);
        assertThat(testAddressVerificationResponse.getReqCity()).isEqualTo(DEFAULT_REQ_CITY);
        assertThat(testAddressVerificationResponse.getReqState()).isEqualTo(DEFAULT_REQ_STATE);
        assertThat(testAddressVerificationResponse.getReqZip4()).isEqualTo(DEFAULT_REQ_ZIP_4);
        assertThat(testAddressVerificationResponse.getReqZip5()).isEqualTo(DEFAULT_REQ_ZIP_5);
        assertThat(testAddressVerificationResponse.getReqDate()).isEqualTo(DEFAULT_REQ_DATE);
        assertThat(testAddressVerificationResponse.getRspAddressLine1()).isEqualTo(DEFAULT_RSP_ADDRESS_LINE_1);
        assertThat(testAddressVerificationResponse.getRspAddressLine2()).isEqualTo(DEFAULT_RSP_ADDRESS_LINE_2);
        assertThat(testAddressVerificationResponse.getRspCity()).isEqualTo(DEFAULT_RSP_CITY);
        assertThat(testAddressVerificationResponse.getRspState()).isEqualTo(DEFAULT_RSP_STATE);
        assertThat(testAddressVerificationResponse.getRspZip4()).isEqualTo(DEFAULT_RSP_ZIP_4);
        assertThat(testAddressVerificationResponse.getRspZip5()).isEqualTo(DEFAULT_RSP_ZIP_5);
        assertThat(testAddressVerificationResponse.getRspDate()).isEqualTo(DEFAULT_RSP_DATE);
        assertThat(testAddressVerificationResponse.getDpvFootnotesCode()).isEqualTo(DEFAULT_DPV_FOOTNOTES_CODE);
        assertThat(testAddressVerificationResponse.getDpvFootnotesDescription()).isEqualTo(DEFAULT_DPV_FOOTNOTES_DESCRIPTION);
        assertThat(testAddressVerificationResponse.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    void createAddressVerificationResponseWithExistingId() throws Exception {
        // Create the AddressVerificationResponse with an existing ID
        addressVerificationResponse.setUspsAddressVerificationId(1L);
        AddressVerificationResponseDTO addressVerificationResponseDTO = addressVerificationResponseMapper.toDto(
            addressVerificationResponse
        );

        int databaseSizeBeforeCreate = addressVerificationResponseRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAddressVerificationResponseMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(addressVerificationResponseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AddressVerificationResponse in the database
        List<AddressVerificationResponse> addressVerificationResponseList = addressVerificationResponseRepository.findAll();
        assertThat(addressVerificationResponseList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAddressVerificationResponses() throws Exception {
        // Initialize the database
        addressVerificationResponseRepository.saveAndFlush(addressVerificationResponse);

        // Get all the addressVerificationResponseList
        restAddressVerificationResponseMockMvc
            .perform(get(ENTITY_API_URL + "?sort=uspsAddressVerificationId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].uspsAddressVerificationId")
                    .value(hasItem(addressVerificationResponse.getUspsAddressVerificationId().intValue()))
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
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }

    @Test
    @Transactional
    void getAddressVerificationResponse() throws Exception {
        // Initialize the database
        addressVerificationResponseRepository.saveAndFlush(addressVerificationResponse);

        // Get the addressVerificationResponse
        restAddressVerificationResponseMockMvc
            .perform(get(ENTITY_API_URL_ID, addressVerificationResponse.getUspsAddressVerificationId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.uspsAddressVerificationId").value(addressVerificationResponse.getUspsAddressVerificationId().intValue()))
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
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    void getNonExistingAddressVerificationResponse() throws Exception {
        // Get the addressVerificationResponse
        restAddressVerificationResponseMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewAddressVerificationResponse() throws Exception {
        // Initialize the database
        addressVerificationResponseRepository.saveAndFlush(addressVerificationResponse);

        int databaseSizeBeforeUpdate = addressVerificationResponseRepository.findAll().size();

        // Update the addressVerificationResponse
        AddressVerificationResponse updatedAddressVerificationResponse = addressVerificationResponseRepository
            .findById(addressVerificationResponse.getUspsAddressVerificationId())
            .get();
        // Disconnect from session so that the updates on updatedAddressVerificationResponse are not directly saved in db
        em.detach(updatedAddressVerificationResponse);
        updatedAddressVerificationResponse
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
            .status(UPDATED_STATUS);
        AddressVerificationResponseDTO addressVerificationResponseDTO = addressVerificationResponseMapper.toDto(
            updatedAddressVerificationResponse
        );

        restAddressVerificationResponseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, addressVerificationResponseDTO.getUspsAddressVerificationId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(addressVerificationResponseDTO))
            )
            .andExpect(status().isOk());

        // Validate the AddressVerificationResponse in the database
        List<AddressVerificationResponse> addressVerificationResponseList = addressVerificationResponseRepository.findAll();
        assertThat(addressVerificationResponseList).hasSize(databaseSizeBeforeUpdate);
        AddressVerificationResponse testAddressVerificationResponse = addressVerificationResponseList.get(
            addressVerificationResponseList.size() - 1
        );
        assertThat(testAddressVerificationResponse.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testAddressVerificationResponse.getReqAddressLine1()).isEqualTo(UPDATED_REQ_ADDRESS_LINE_1);
        assertThat(testAddressVerificationResponse.getReqAddressLine2()).isEqualTo(UPDATED_REQ_ADDRESS_LINE_2);
        assertThat(testAddressVerificationResponse.getReqCity()).isEqualTo(UPDATED_REQ_CITY);
        assertThat(testAddressVerificationResponse.getReqState()).isEqualTo(UPDATED_REQ_STATE);
        assertThat(testAddressVerificationResponse.getReqZip4()).isEqualTo(UPDATED_REQ_ZIP_4);
        assertThat(testAddressVerificationResponse.getReqZip5()).isEqualTo(UPDATED_REQ_ZIP_5);
        assertThat(testAddressVerificationResponse.getReqDate()).isEqualTo(UPDATED_REQ_DATE);
        assertThat(testAddressVerificationResponse.getRspAddressLine1()).isEqualTo(UPDATED_RSP_ADDRESS_LINE_1);
        assertThat(testAddressVerificationResponse.getRspAddressLine2()).isEqualTo(UPDATED_RSP_ADDRESS_LINE_2);
        assertThat(testAddressVerificationResponse.getRspCity()).isEqualTo(UPDATED_RSP_CITY);
        assertThat(testAddressVerificationResponse.getRspState()).isEqualTo(UPDATED_RSP_STATE);
        assertThat(testAddressVerificationResponse.getRspZip4()).isEqualTo(UPDATED_RSP_ZIP_4);
        assertThat(testAddressVerificationResponse.getRspZip5()).isEqualTo(UPDATED_RSP_ZIP_5);
        assertThat(testAddressVerificationResponse.getRspDate()).isEqualTo(UPDATED_RSP_DATE);
        assertThat(testAddressVerificationResponse.getDpvFootnotesCode()).isEqualTo(UPDATED_DPV_FOOTNOTES_CODE);
        assertThat(testAddressVerificationResponse.getDpvFootnotesDescription()).isEqualTo(UPDATED_DPV_FOOTNOTES_DESCRIPTION);
        assertThat(testAddressVerificationResponse.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    void putNonExistingAddressVerificationResponse() throws Exception {
        int databaseSizeBeforeUpdate = addressVerificationResponseRepository.findAll().size();
        addressVerificationResponse.setUspsAddressVerificationId(count.incrementAndGet());

        // Create the AddressVerificationResponse
        AddressVerificationResponseDTO addressVerificationResponseDTO = addressVerificationResponseMapper.toDto(
            addressVerificationResponse
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAddressVerificationResponseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, addressVerificationResponseDTO.getUspsAddressVerificationId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(addressVerificationResponseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AddressVerificationResponse in the database
        List<AddressVerificationResponse> addressVerificationResponseList = addressVerificationResponseRepository.findAll();
        assertThat(addressVerificationResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAddressVerificationResponse() throws Exception {
        int databaseSizeBeforeUpdate = addressVerificationResponseRepository.findAll().size();
        addressVerificationResponse.setUspsAddressVerificationId(count.incrementAndGet());

        // Create the AddressVerificationResponse
        AddressVerificationResponseDTO addressVerificationResponseDTO = addressVerificationResponseMapper.toDto(
            addressVerificationResponse
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAddressVerificationResponseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(addressVerificationResponseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AddressVerificationResponse in the database
        List<AddressVerificationResponse> addressVerificationResponseList = addressVerificationResponseRepository.findAll();
        assertThat(addressVerificationResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAddressVerificationResponse() throws Exception {
        int databaseSizeBeforeUpdate = addressVerificationResponseRepository.findAll().size();
        addressVerificationResponse.setUspsAddressVerificationId(count.incrementAndGet());

        // Create the AddressVerificationResponse
        AddressVerificationResponseDTO addressVerificationResponseDTO = addressVerificationResponseMapper.toDto(
            addressVerificationResponse
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAddressVerificationResponseMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(addressVerificationResponseDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AddressVerificationResponse in the database
        List<AddressVerificationResponse> addressVerificationResponseList = addressVerificationResponseRepository.findAll();
        assertThat(addressVerificationResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAddressVerificationResponseWithPatch() throws Exception {
        // Initialize the database
        addressVerificationResponseRepository.saveAndFlush(addressVerificationResponse);

        int databaseSizeBeforeUpdate = addressVerificationResponseRepository.findAll().size();

        // Update the addressVerificationResponse using partial update
        AddressVerificationResponse partialUpdatedAddressVerificationResponse = new AddressVerificationResponse();
        partialUpdatedAddressVerificationResponse.setUspsAddressVerificationId(addressVerificationResponse.getUspsAddressVerificationId());

        partialUpdatedAddressVerificationResponse
            .patientId(UPDATED_PATIENT_ID)
            .reqAddressLine2(UPDATED_REQ_ADDRESS_LINE_2)
            .reqCity(UPDATED_REQ_CITY)
            .reqZip5(UPDATED_REQ_ZIP_5)
            .reqDate(UPDATED_REQ_DATE)
            .rspAddressLine2(UPDATED_RSP_ADDRESS_LINE_2)
            .rspCity(UPDATED_RSP_CITY)
            .rspState(UPDATED_RSP_STATE)
            .rspZip5(UPDATED_RSP_ZIP_5)
            .rspDate(UPDATED_RSP_DATE)
            .dpvFootnotesCode(UPDATED_DPV_FOOTNOTES_CODE)
            .status(UPDATED_STATUS);

        restAddressVerificationResponseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAddressVerificationResponse.getUspsAddressVerificationId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAddressVerificationResponse))
            )
            .andExpect(status().isOk());

        // Validate the AddressVerificationResponse in the database
        List<AddressVerificationResponse> addressVerificationResponseList = addressVerificationResponseRepository.findAll();
        assertThat(addressVerificationResponseList).hasSize(databaseSizeBeforeUpdate);
        AddressVerificationResponse testAddressVerificationResponse = addressVerificationResponseList.get(
            addressVerificationResponseList.size() - 1
        );
        assertThat(testAddressVerificationResponse.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testAddressVerificationResponse.getReqAddressLine1()).isEqualTo(DEFAULT_REQ_ADDRESS_LINE_1);
        assertThat(testAddressVerificationResponse.getReqAddressLine2()).isEqualTo(UPDATED_REQ_ADDRESS_LINE_2);
        assertThat(testAddressVerificationResponse.getReqCity()).isEqualTo(UPDATED_REQ_CITY);
        assertThat(testAddressVerificationResponse.getReqState()).isEqualTo(DEFAULT_REQ_STATE);
        assertThat(testAddressVerificationResponse.getReqZip4()).isEqualTo(DEFAULT_REQ_ZIP_4);
        assertThat(testAddressVerificationResponse.getReqZip5()).isEqualTo(UPDATED_REQ_ZIP_5);
        assertThat(testAddressVerificationResponse.getReqDate()).isEqualTo(UPDATED_REQ_DATE);
        assertThat(testAddressVerificationResponse.getRspAddressLine1()).isEqualTo(DEFAULT_RSP_ADDRESS_LINE_1);
        assertThat(testAddressVerificationResponse.getRspAddressLine2()).isEqualTo(UPDATED_RSP_ADDRESS_LINE_2);
        assertThat(testAddressVerificationResponse.getRspCity()).isEqualTo(UPDATED_RSP_CITY);
        assertThat(testAddressVerificationResponse.getRspState()).isEqualTo(UPDATED_RSP_STATE);
        assertThat(testAddressVerificationResponse.getRspZip4()).isEqualTo(DEFAULT_RSP_ZIP_4);
        assertThat(testAddressVerificationResponse.getRspZip5()).isEqualTo(UPDATED_RSP_ZIP_5);
        assertThat(testAddressVerificationResponse.getRspDate()).isEqualTo(UPDATED_RSP_DATE);
        assertThat(testAddressVerificationResponse.getDpvFootnotesCode()).isEqualTo(UPDATED_DPV_FOOTNOTES_CODE);
        assertThat(testAddressVerificationResponse.getDpvFootnotesDescription()).isEqualTo(DEFAULT_DPV_FOOTNOTES_DESCRIPTION);
        assertThat(testAddressVerificationResponse.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    void fullUpdateAddressVerificationResponseWithPatch() throws Exception {
        // Initialize the database
        addressVerificationResponseRepository.saveAndFlush(addressVerificationResponse);

        int databaseSizeBeforeUpdate = addressVerificationResponseRepository.findAll().size();

        // Update the addressVerificationResponse using partial update
        AddressVerificationResponse partialUpdatedAddressVerificationResponse = new AddressVerificationResponse();
        partialUpdatedAddressVerificationResponse.setUspsAddressVerificationId(addressVerificationResponse.getUspsAddressVerificationId());

        partialUpdatedAddressVerificationResponse
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
            .status(UPDATED_STATUS);

        restAddressVerificationResponseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAddressVerificationResponse.getUspsAddressVerificationId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAddressVerificationResponse))
            )
            .andExpect(status().isOk());

        // Validate the AddressVerificationResponse in the database
        List<AddressVerificationResponse> addressVerificationResponseList = addressVerificationResponseRepository.findAll();
        assertThat(addressVerificationResponseList).hasSize(databaseSizeBeforeUpdate);
        AddressVerificationResponse testAddressVerificationResponse = addressVerificationResponseList.get(
            addressVerificationResponseList.size() - 1
        );
        assertThat(testAddressVerificationResponse.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testAddressVerificationResponse.getReqAddressLine1()).isEqualTo(UPDATED_REQ_ADDRESS_LINE_1);
        assertThat(testAddressVerificationResponse.getReqAddressLine2()).isEqualTo(UPDATED_REQ_ADDRESS_LINE_2);
        assertThat(testAddressVerificationResponse.getReqCity()).isEqualTo(UPDATED_REQ_CITY);
        assertThat(testAddressVerificationResponse.getReqState()).isEqualTo(UPDATED_REQ_STATE);
        assertThat(testAddressVerificationResponse.getReqZip4()).isEqualTo(UPDATED_REQ_ZIP_4);
        assertThat(testAddressVerificationResponse.getReqZip5()).isEqualTo(UPDATED_REQ_ZIP_5);
        assertThat(testAddressVerificationResponse.getReqDate()).isEqualTo(UPDATED_REQ_DATE);
        assertThat(testAddressVerificationResponse.getRspAddressLine1()).isEqualTo(UPDATED_RSP_ADDRESS_LINE_1);
        assertThat(testAddressVerificationResponse.getRspAddressLine2()).isEqualTo(UPDATED_RSP_ADDRESS_LINE_2);
        assertThat(testAddressVerificationResponse.getRspCity()).isEqualTo(UPDATED_RSP_CITY);
        assertThat(testAddressVerificationResponse.getRspState()).isEqualTo(UPDATED_RSP_STATE);
        assertThat(testAddressVerificationResponse.getRspZip4()).isEqualTo(UPDATED_RSP_ZIP_4);
        assertThat(testAddressVerificationResponse.getRspZip5()).isEqualTo(UPDATED_RSP_ZIP_5);
        assertThat(testAddressVerificationResponse.getRspDate()).isEqualTo(UPDATED_RSP_DATE);
        assertThat(testAddressVerificationResponse.getDpvFootnotesCode()).isEqualTo(UPDATED_DPV_FOOTNOTES_CODE);
        assertThat(testAddressVerificationResponse.getDpvFootnotesDescription()).isEqualTo(UPDATED_DPV_FOOTNOTES_DESCRIPTION);
        assertThat(testAddressVerificationResponse.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    void patchNonExistingAddressVerificationResponse() throws Exception {
        int databaseSizeBeforeUpdate = addressVerificationResponseRepository.findAll().size();
        addressVerificationResponse.setUspsAddressVerificationId(count.incrementAndGet());

        // Create the AddressVerificationResponse
        AddressVerificationResponseDTO addressVerificationResponseDTO = addressVerificationResponseMapper.toDto(
            addressVerificationResponse
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAddressVerificationResponseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, addressVerificationResponseDTO.getUspsAddressVerificationId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(addressVerificationResponseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AddressVerificationResponse in the database
        List<AddressVerificationResponse> addressVerificationResponseList = addressVerificationResponseRepository.findAll();
        assertThat(addressVerificationResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAddressVerificationResponse() throws Exception {
        int databaseSizeBeforeUpdate = addressVerificationResponseRepository.findAll().size();
        addressVerificationResponse.setUspsAddressVerificationId(count.incrementAndGet());

        // Create the AddressVerificationResponse
        AddressVerificationResponseDTO addressVerificationResponseDTO = addressVerificationResponseMapper.toDto(
            addressVerificationResponse
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAddressVerificationResponseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(addressVerificationResponseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AddressVerificationResponse in the database
        List<AddressVerificationResponse> addressVerificationResponseList = addressVerificationResponseRepository.findAll();
        assertThat(addressVerificationResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAddressVerificationResponse() throws Exception {
        int databaseSizeBeforeUpdate = addressVerificationResponseRepository.findAll().size();
        addressVerificationResponse.setUspsAddressVerificationId(count.incrementAndGet());

        // Create the AddressVerificationResponse
        AddressVerificationResponseDTO addressVerificationResponseDTO = addressVerificationResponseMapper.toDto(
            addressVerificationResponse
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAddressVerificationResponseMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(addressVerificationResponseDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AddressVerificationResponse in the database
        List<AddressVerificationResponse> addressVerificationResponseList = addressVerificationResponseRepository.findAll();
        assertThat(addressVerificationResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAddressVerificationResponse() throws Exception {
        // Initialize the database
        addressVerificationResponseRepository.saveAndFlush(addressVerificationResponse);

        int databaseSizeBeforeDelete = addressVerificationResponseRepository.findAll().size();

        // Delete the addressVerificationResponse
        restAddressVerificationResponseMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, addressVerificationResponse.getUspsAddressVerificationId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AddressVerificationResponse> addressVerificationResponseList = addressVerificationResponseRepository.findAll();
        assertThat(addressVerificationResponseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
