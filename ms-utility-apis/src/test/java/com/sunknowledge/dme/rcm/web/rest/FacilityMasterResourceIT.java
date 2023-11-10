package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.FacilityMaster;
import com.sunknowledge.dme.rcm.repository.FacilityMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.FacilityMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.FacilityMasterMapper;
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
 * Integration tests for the {@link FacilityMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FacilityMasterResourceIT {

    private static final String DEFAULT_FACILITY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FACILITY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_NPI = "AAAAAAAAAA";
    private static final String UPDATED_NPI = "BBBBBBBBBB";

    private static final Long DEFAULT_SALES_REP_ID = 1L;
    private static final Long UPDATED_SALES_REP_ID = 2L;

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_FACILITY_TYPE_ID = 1L;
    private static final Long UPDATED_FACILITY_TYPE_ID = 2L;

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final UUID DEFAULT_FACILITY_MASTER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_FACILITY_MASTER_UUID = UUID.randomUUID();

    private static final String DEFAULT_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_ZIP = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_NO_1 = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_NO_1 = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_NO_2 = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_NO_2 = "BBBBBBBBBB";

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_EFAX = "AAAAAAAAAA";
    private static final String UPDATED_EFAX = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_RELATIONSHIP = "AAAAAAAAAA";
    private static final String UPDATED_RELATIONSHIP = "BBBBBBBBBB";

    private static final String DEFAULT_MODE_OF_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_MODE_OF_CONTACT = "BBBBBBBBBB";

    private static final String DEFAULT_FACILITY_NO = "AAAAAAAAAA";
    private static final String UPDATED_FACILITY_NO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/facility-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{facilityId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private FacilityMasterRepository facilityMasterRepository;

    @Autowired
    private FacilityMasterMapper facilityMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFacilityMasterMockMvc;

    private FacilityMaster facilityMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FacilityMaster createEntity(EntityManager em) {
        FacilityMaster facilityMaster = new FacilityMaster()
            .facilityName(DEFAULT_FACILITY_NAME)
            .npi(DEFAULT_NPI)
            .salesRepId(DEFAULT_SALES_REP_ID)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .facilityTypeId(DEFAULT_FACILITY_TYPE_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .facilityMasterUuid(DEFAULT_FACILITY_MASTER_UUID)
            .addressLine1(DEFAULT_ADDRESS_LINE_1)
            .addressLine2(DEFAULT_ADDRESS_LINE_2)
            .city(DEFAULT_CITY)
            .state(DEFAULT_STATE)
            .country(DEFAULT_COUNTRY)
            .zip(DEFAULT_ZIP)
            .contactNo1(DEFAULT_CONTACT_NO_1)
            .contactNo2(DEFAULT_CONTACT_NO_2)
            .fax(DEFAULT_FAX)
            .efax(DEFAULT_EFAX)
            .email(DEFAULT_EMAIL)
            .relationship(DEFAULT_RELATIONSHIP)
            .modeOfContact(DEFAULT_MODE_OF_CONTACT)
            .facilityNo(DEFAULT_FACILITY_NO);
        return facilityMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FacilityMaster createUpdatedEntity(EntityManager em) {
        FacilityMaster facilityMaster = new FacilityMaster()
            .facilityName(UPDATED_FACILITY_NAME)
            .npi(UPDATED_NPI)
            .salesRepId(UPDATED_SALES_REP_ID)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .facilityTypeId(UPDATED_FACILITY_TYPE_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .facilityMasterUuid(UPDATED_FACILITY_MASTER_UUID)
            .addressLine1(UPDATED_ADDRESS_LINE_1)
            .addressLine2(UPDATED_ADDRESS_LINE_2)
            .city(UPDATED_CITY)
            .state(UPDATED_STATE)
            .country(UPDATED_COUNTRY)
            .zip(UPDATED_ZIP)
            .contactNo1(UPDATED_CONTACT_NO_1)
            .contactNo2(UPDATED_CONTACT_NO_2)
            .fax(UPDATED_FAX)
            .efax(UPDATED_EFAX)
            .email(UPDATED_EMAIL)
            .relationship(UPDATED_RELATIONSHIP)
            .modeOfContact(UPDATED_MODE_OF_CONTACT)
            .facilityNo(UPDATED_FACILITY_NO);
        return facilityMaster;
    }

    @BeforeEach
    public void initTest() {
        facilityMaster = createEntity(em);
    }

    @Test
    @Transactional
    void createFacilityMaster() throws Exception {
        int databaseSizeBeforeCreate = facilityMasterRepository.findAll().size();
        // Create the FacilityMaster
        FacilityMasterDTO facilityMasterDTO = facilityMasterMapper.toDto(facilityMaster);
        restFacilityMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(facilityMasterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the FacilityMaster in the database
        List<FacilityMaster> facilityMasterList = facilityMasterRepository.findAll();
        assertThat(facilityMasterList).hasSize(databaseSizeBeforeCreate + 1);
        FacilityMaster testFacilityMaster = facilityMasterList.get(facilityMasterList.size() - 1);
        assertThat(testFacilityMaster.getFacilityName()).isEqualTo(DEFAULT_FACILITY_NAME);
        assertThat(testFacilityMaster.getNpi()).isEqualTo(DEFAULT_NPI);
        assertThat(testFacilityMaster.getSalesRepId()).isEqualTo(DEFAULT_SALES_REP_ID);
        assertThat(testFacilityMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testFacilityMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testFacilityMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testFacilityMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testFacilityMaster.getFacilityTypeId()).isEqualTo(DEFAULT_FACILITY_TYPE_ID);
        assertThat(testFacilityMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testFacilityMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testFacilityMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testFacilityMaster.getFacilityMasterUuid()).isEqualTo(DEFAULT_FACILITY_MASTER_UUID);
        assertThat(testFacilityMaster.getAddressLine1()).isEqualTo(DEFAULT_ADDRESS_LINE_1);
        assertThat(testFacilityMaster.getAddressLine2()).isEqualTo(DEFAULT_ADDRESS_LINE_2);
        assertThat(testFacilityMaster.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testFacilityMaster.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testFacilityMaster.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testFacilityMaster.getZip()).isEqualTo(DEFAULT_ZIP);
        assertThat(testFacilityMaster.getContactNo1()).isEqualTo(DEFAULT_CONTACT_NO_1);
        assertThat(testFacilityMaster.getContactNo2()).isEqualTo(DEFAULT_CONTACT_NO_2);
        assertThat(testFacilityMaster.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testFacilityMaster.getEfax()).isEqualTo(DEFAULT_EFAX);
        assertThat(testFacilityMaster.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testFacilityMaster.getRelationship()).isEqualTo(DEFAULT_RELATIONSHIP);
        assertThat(testFacilityMaster.getModeOfContact()).isEqualTo(DEFAULT_MODE_OF_CONTACT);
        assertThat(testFacilityMaster.getFacilityNo()).isEqualTo(DEFAULT_FACILITY_NO);
    }

    @Test
    @Transactional
    void createFacilityMasterWithExistingId() throws Exception {
        // Create the FacilityMaster with an existing ID
        facilityMaster.setFacilityId(1L);
        FacilityMasterDTO facilityMasterDTO = facilityMasterMapper.toDto(facilityMaster);

        int databaseSizeBeforeCreate = facilityMasterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFacilityMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(facilityMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FacilityMaster in the database
        List<FacilityMaster> facilityMasterList = facilityMasterRepository.findAll();
        assertThat(facilityMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllFacilityMasters() throws Exception {
        // Initialize the database
        facilityMasterRepository.saveAndFlush(facilityMaster);

        // Get all the facilityMasterList
        restFacilityMasterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=facilityId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].facilityId").value(hasItem(facilityMaster.getFacilityId().intValue())))
            .andExpect(jsonPath("$.[*].facilityName").value(hasItem(DEFAULT_FACILITY_NAME)))
            .andExpect(jsonPath("$.[*].npi").value(hasItem(DEFAULT_NPI)))
            .andExpect(jsonPath("$.[*].salesRepId").value(hasItem(DEFAULT_SALES_REP_ID.intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].facilityTypeId").value(hasItem(DEFAULT_FACILITY_TYPE_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].facilityMasterUuid").value(hasItem(DEFAULT_FACILITY_MASTER_UUID.toString())))
            .andExpect(jsonPath("$.[*].addressLine1").value(hasItem(DEFAULT_ADDRESS_LINE_1)))
            .andExpect(jsonPath("$.[*].addressLine2").value(hasItem(DEFAULT_ADDRESS_LINE_2)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE)))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)))
            .andExpect(jsonPath("$.[*].zip").value(hasItem(DEFAULT_ZIP)))
            .andExpect(jsonPath("$.[*].contactNo1").value(hasItem(DEFAULT_CONTACT_NO_1)))
            .andExpect(jsonPath("$.[*].contactNo2").value(hasItem(DEFAULT_CONTACT_NO_2)))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX)))
            .andExpect(jsonPath("$.[*].efax").value(hasItem(DEFAULT_EFAX)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].relationship").value(hasItem(DEFAULT_RELATIONSHIP)))
            .andExpect(jsonPath("$.[*].modeOfContact").value(hasItem(DEFAULT_MODE_OF_CONTACT)))
            .andExpect(jsonPath("$.[*].facilityNo").value(hasItem(DEFAULT_FACILITY_NO)));
    }

    @Test
    @Transactional
    void getFacilityMaster() throws Exception {
        // Initialize the database
        facilityMasterRepository.saveAndFlush(facilityMaster);

        // Get the facilityMaster
        restFacilityMasterMockMvc
            .perform(get(ENTITY_API_URL_ID, facilityMaster.getFacilityId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.facilityId").value(facilityMaster.getFacilityId().intValue()))
            .andExpect(jsonPath("$.facilityName").value(DEFAULT_FACILITY_NAME))
            .andExpect(jsonPath("$.npi").value(DEFAULT_NPI))
            .andExpect(jsonPath("$.salesRepId").value(DEFAULT_SALES_REP_ID.intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.facilityTypeId").value(DEFAULT_FACILITY_TYPE_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.facilityMasterUuid").value(DEFAULT_FACILITY_MASTER_UUID.toString()))
            .andExpect(jsonPath("$.addressLine1").value(DEFAULT_ADDRESS_LINE_1))
            .andExpect(jsonPath("$.addressLine2").value(DEFAULT_ADDRESS_LINE_2))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY))
            .andExpect(jsonPath("$.zip").value(DEFAULT_ZIP))
            .andExpect(jsonPath("$.contactNo1").value(DEFAULT_CONTACT_NO_1))
            .andExpect(jsonPath("$.contactNo2").value(DEFAULT_CONTACT_NO_2))
            .andExpect(jsonPath("$.fax").value(DEFAULT_FAX))
            .andExpect(jsonPath("$.efax").value(DEFAULT_EFAX))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.relationship").value(DEFAULT_RELATIONSHIP))
            .andExpect(jsonPath("$.modeOfContact").value(DEFAULT_MODE_OF_CONTACT))
            .andExpect(jsonPath("$.facilityNo").value(DEFAULT_FACILITY_NO));
    }

    @Test
    @Transactional
    void getNonExistingFacilityMaster() throws Exception {
        // Get the facilityMaster
        restFacilityMasterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewFacilityMaster() throws Exception {
        // Initialize the database
        facilityMasterRepository.saveAndFlush(facilityMaster);

        int databaseSizeBeforeUpdate = facilityMasterRepository.findAll().size();

        // Update the facilityMaster
        FacilityMaster updatedFacilityMaster = facilityMasterRepository.findById(facilityMaster.getFacilityId()).get();
        // Disconnect from session so that the updates on updatedFacilityMaster are not directly saved in db
        em.detach(updatedFacilityMaster);
        updatedFacilityMaster
            .facilityName(UPDATED_FACILITY_NAME)
            .npi(UPDATED_NPI)
            .salesRepId(UPDATED_SALES_REP_ID)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .facilityTypeId(UPDATED_FACILITY_TYPE_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .facilityMasterUuid(UPDATED_FACILITY_MASTER_UUID)
            .addressLine1(UPDATED_ADDRESS_LINE_1)
            .addressLine2(UPDATED_ADDRESS_LINE_2)
            .city(UPDATED_CITY)
            .state(UPDATED_STATE)
            .country(UPDATED_COUNTRY)
            .zip(UPDATED_ZIP)
            .contactNo1(UPDATED_CONTACT_NO_1)
            .contactNo2(UPDATED_CONTACT_NO_2)
            .fax(UPDATED_FAX)
            .efax(UPDATED_EFAX)
            .email(UPDATED_EMAIL)
            .relationship(UPDATED_RELATIONSHIP)
            .modeOfContact(UPDATED_MODE_OF_CONTACT)
            .facilityNo(UPDATED_FACILITY_NO);
        FacilityMasterDTO facilityMasterDTO = facilityMasterMapper.toDto(updatedFacilityMaster);

        restFacilityMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, facilityMasterDTO.getFacilityId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(facilityMasterDTO))
            )
            .andExpect(status().isOk());

        // Validate the FacilityMaster in the database
        List<FacilityMaster> facilityMasterList = facilityMasterRepository.findAll();
        assertThat(facilityMasterList).hasSize(databaseSizeBeforeUpdate);
        FacilityMaster testFacilityMaster = facilityMasterList.get(facilityMasterList.size() - 1);
        assertThat(testFacilityMaster.getFacilityName()).isEqualTo(UPDATED_FACILITY_NAME);
        assertThat(testFacilityMaster.getNpi()).isEqualTo(UPDATED_NPI);
        assertThat(testFacilityMaster.getSalesRepId()).isEqualTo(UPDATED_SALES_REP_ID);
        assertThat(testFacilityMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testFacilityMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testFacilityMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testFacilityMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testFacilityMaster.getFacilityTypeId()).isEqualTo(UPDATED_FACILITY_TYPE_ID);
        assertThat(testFacilityMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testFacilityMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testFacilityMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testFacilityMaster.getFacilityMasterUuid()).isEqualTo(UPDATED_FACILITY_MASTER_UUID);
        assertThat(testFacilityMaster.getAddressLine1()).isEqualTo(UPDATED_ADDRESS_LINE_1);
        assertThat(testFacilityMaster.getAddressLine2()).isEqualTo(UPDATED_ADDRESS_LINE_2);
        assertThat(testFacilityMaster.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testFacilityMaster.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testFacilityMaster.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testFacilityMaster.getZip()).isEqualTo(UPDATED_ZIP);
        assertThat(testFacilityMaster.getContactNo1()).isEqualTo(UPDATED_CONTACT_NO_1);
        assertThat(testFacilityMaster.getContactNo2()).isEqualTo(UPDATED_CONTACT_NO_2);
        assertThat(testFacilityMaster.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testFacilityMaster.getEfax()).isEqualTo(UPDATED_EFAX);
        assertThat(testFacilityMaster.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testFacilityMaster.getRelationship()).isEqualTo(UPDATED_RELATIONSHIP);
        assertThat(testFacilityMaster.getModeOfContact()).isEqualTo(UPDATED_MODE_OF_CONTACT);
        assertThat(testFacilityMaster.getFacilityNo()).isEqualTo(UPDATED_FACILITY_NO);
    }

    @Test
    @Transactional
    void putNonExistingFacilityMaster() throws Exception {
        int databaseSizeBeforeUpdate = facilityMasterRepository.findAll().size();
        facilityMaster.setFacilityId(count.incrementAndGet());

        // Create the FacilityMaster
        FacilityMasterDTO facilityMasterDTO = facilityMasterMapper.toDto(facilityMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFacilityMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, facilityMasterDTO.getFacilityId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(facilityMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FacilityMaster in the database
        List<FacilityMaster> facilityMasterList = facilityMasterRepository.findAll();
        assertThat(facilityMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFacilityMaster() throws Exception {
        int databaseSizeBeforeUpdate = facilityMasterRepository.findAll().size();
        facilityMaster.setFacilityId(count.incrementAndGet());

        // Create the FacilityMaster
        FacilityMasterDTO facilityMasterDTO = facilityMasterMapper.toDto(facilityMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFacilityMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(facilityMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FacilityMaster in the database
        List<FacilityMaster> facilityMasterList = facilityMasterRepository.findAll();
        assertThat(facilityMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFacilityMaster() throws Exception {
        int databaseSizeBeforeUpdate = facilityMasterRepository.findAll().size();
        facilityMaster.setFacilityId(count.incrementAndGet());

        // Create the FacilityMaster
        FacilityMasterDTO facilityMasterDTO = facilityMasterMapper.toDto(facilityMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFacilityMasterMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(facilityMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the FacilityMaster in the database
        List<FacilityMaster> facilityMasterList = facilityMasterRepository.findAll();
        assertThat(facilityMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFacilityMasterWithPatch() throws Exception {
        // Initialize the database
        facilityMasterRepository.saveAndFlush(facilityMaster);

        int databaseSizeBeforeUpdate = facilityMasterRepository.findAll().size();

        // Update the facilityMaster using partial update
        FacilityMaster partialUpdatedFacilityMaster = new FacilityMaster();
        partialUpdatedFacilityMaster.setFacilityId(facilityMaster.getFacilityId());

        partialUpdatedFacilityMaster
            .npi(UPDATED_NPI)
            .salesRepId(UPDATED_SALES_REP_ID)
            .facilityTypeId(UPDATED_FACILITY_TYPE_ID)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .facilityMasterUuid(UPDATED_FACILITY_MASTER_UUID)
            .addressLine1(UPDATED_ADDRESS_LINE_1)
            .addressLine2(UPDATED_ADDRESS_LINE_2)
            .state(UPDATED_STATE)
            .country(UPDATED_COUNTRY)
            .zip(UPDATED_ZIP)
            .relationship(UPDATED_RELATIONSHIP);

        restFacilityMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFacilityMaster.getFacilityId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFacilityMaster))
            )
            .andExpect(status().isOk());

        // Validate the FacilityMaster in the database
        List<FacilityMaster> facilityMasterList = facilityMasterRepository.findAll();
        assertThat(facilityMasterList).hasSize(databaseSizeBeforeUpdate);
        FacilityMaster testFacilityMaster = facilityMasterList.get(facilityMasterList.size() - 1);
        assertThat(testFacilityMaster.getFacilityName()).isEqualTo(DEFAULT_FACILITY_NAME);
        assertThat(testFacilityMaster.getNpi()).isEqualTo(UPDATED_NPI);
        assertThat(testFacilityMaster.getSalesRepId()).isEqualTo(UPDATED_SALES_REP_ID);
        assertThat(testFacilityMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testFacilityMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testFacilityMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testFacilityMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testFacilityMaster.getFacilityTypeId()).isEqualTo(UPDATED_FACILITY_TYPE_ID);
        assertThat(testFacilityMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testFacilityMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testFacilityMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testFacilityMaster.getFacilityMasterUuid()).isEqualTo(UPDATED_FACILITY_MASTER_UUID);
        assertThat(testFacilityMaster.getAddressLine1()).isEqualTo(UPDATED_ADDRESS_LINE_1);
        assertThat(testFacilityMaster.getAddressLine2()).isEqualTo(UPDATED_ADDRESS_LINE_2);
        assertThat(testFacilityMaster.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testFacilityMaster.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testFacilityMaster.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testFacilityMaster.getZip()).isEqualTo(UPDATED_ZIP);
        assertThat(testFacilityMaster.getContactNo1()).isEqualTo(DEFAULT_CONTACT_NO_1);
        assertThat(testFacilityMaster.getContactNo2()).isEqualTo(DEFAULT_CONTACT_NO_2);
        assertThat(testFacilityMaster.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testFacilityMaster.getEfax()).isEqualTo(DEFAULT_EFAX);
        assertThat(testFacilityMaster.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testFacilityMaster.getRelationship()).isEqualTo(UPDATED_RELATIONSHIP);
        assertThat(testFacilityMaster.getModeOfContact()).isEqualTo(DEFAULT_MODE_OF_CONTACT);
        assertThat(testFacilityMaster.getFacilityNo()).isEqualTo(DEFAULT_FACILITY_NO);
    }

    @Test
    @Transactional
    void fullUpdateFacilityMasterWithPatch() throws Exception {
        // Initialize the database
        facilityMasterRepository.saveAndFlush(facilityMaster);

        int databaseSizeBeforeUpdate = facilityMasterRepository.findAll().size();

        // Update the facilityMaster using partial update
        FacilityMaster partialUpdatedFacilityMaster = new FacilityMaster();
        partialUpdatedFacilityMaster.setFacilityId(facilityMaster.getFacilityId());

        partialUpdatedFacilityMaster
            .facilityName(UPDATED_FACILITY_NAME)
            .npi(UPDATED_NPI)
            .salesRepId(UPDATED_SALES_REP_ID)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .facilityTypeId(UPDATED_FACILITY_TYPE_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .facilityMasterUuid(UPDATED_FACILITY_MASTER_UUID)
            .addressLine1(UPDATED_ADDRESS_LINE_1)
            .addressLine2(UPDATED_ADDRESS_LINE_2)
            .city(UPDATED_CITY)
            .state(UPDATED_STATE)
            .country(UPDATED_COUNTRY)
            .zip(UPDATED_ZIP)
            .contactNo1(UPDATED_CONTACT_NO_1)
            .contactNo2(UPDATED_CONTACT_NO_2)
            .fax(UPDATED_FAX)
            .efax(UPDATED_EFAX)
            .email(UPDATED_EMAIL)
            .relationship(UPDATED_RELATIONSHIP)
            .modeOfContact(UPDATED_MODE_OF_CONTACT)
            .facilityNo(UPDATED_FACILITY_NO);

        restFacilityMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFacilityMaster.getFacilityId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFacilityMaster))
            )
            .andExpect(status().isOk());

        // Validate the FacilityMaster in the database
        List<FacilityMaster> facilityMasterList = facilityMasterRepository.findAll();
        assertThat(facilityMasterList).hasSize(databaseSizeBeforeUpdate);
        FacilityMaster testFacilityMaster = facilityMasterList.get(facilityMasterList.size() - 1);
        assertThat(testFacilityMaster.getFacilityName()).isEqualTo(UPDATED_FACILITY_NAME);
        assertThat(testFacilityMaster.getNpi()).isEqualTo(UPDATED_NPI);
        assertThat(testFacilityMaster.getSalesRepId()).isEqualTo(UPDATED_SALES_REP_ID);
        assertThat(testFacilityMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testFacilityMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testFacilityMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testFacilityMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testFacilityMaster.getFacilityTypeId()).isEqualTo(UPDATED_FACILITY_TYPE_ID);
        assertThat(testFacilityMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testFacilityMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testFacilityMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testFacilityMaster.getFacilityMasterUuid()).isEqualTo(UPDATED_FACILITY_MASTER_UUID);
        assertThat(testFacilityMaster.getAddressLine1()).isEqualTo(UPDATED_ADDRESS_LINE_1);
        assertThat(testFacilityMaster.getAddressLine2()).isEqualTo(UPDATED_ADDRESS_LINE_2);
        assertThat(testFacilityMaster.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testFacilityMaster.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testFacilityMaster.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testFacilityMaster.getZip()).isEqualTo(UPDATED_ZIP);
        assertThat(testFacilityMaster.getContactNo1()).isEqualTo(UPDATED_CONTACT_NO_1);
        assertThat(testFacilityMaster.getContactNo2()).isEqualTo(UPDATED_CONTACT_NO_2);
        assertThat(testFacilityMaster.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testFacilityMaster.getEfax()).isEqualTo(UPDATED_EFAX);
        assertThat(testFacilityMaster.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testFacilityMaster.getRelationship()).isEqualTo(UPDATED_RELATIONSHIP);
        assertThat(testFacilityMaster.getModeOfContact()).isEqualTo(UPDATED_MODE_OF_CONTACT);
        assertThat(testFacilityMaster.getFacilityNo()).isEqualTo(UPDATED_FACILITY_NO);
    }

    @Test
    @Transactional
    void patchNonExistingFacilityMaster() throws Exception {
        int databaseSizeBeforeUpdate = facilityMasterRepository.findAll().size();
        facilityMaster.setFacilityId(count.incrementAndGet());

        // Create the FacilityMaster
        FacilityMasterDTO facilityMasterDTO = facilityMasterMapper.toDto(facilityMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFacilityMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, facilityMasterDTO.getFacilityId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(facilityMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FacilityMaster in the database
        List<FacilityMaster> facilityMasterList = facilityMasterRepository.findAll();
        assertThat(facilityMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFacilityMaster() throws Exception {
        int databaseSizeBeforeUpdate = facilityMasterRepository.findAll().size();
        facilityMaster.setFacilityId(count.incrementAndGet());

        // Create the FacilityMaster
        FacilityMasterDTO facilityMasterDTO = facilityMasterMapper.toDto(facilityMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFacilityMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(facilityMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FacilityMaster in the database
        List<FacilityMaster> facilityMasterList = facilityMasterRepository.findAll();
        assertThat(facilityMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFacilityMaster() throws Exception {
        int databaseSizeBeforeUpdate = facilityMasterRepository.findAll().size();
        facilityMaster.setFacilityId(count.incrementAndGet());

        // Create the FacilityMaster
        FacilityMasterDTO facilityMasterDTO = facilityMasterMapper.toDto(facilityMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFacilityMasterMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(facilityMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the FacilityMaster in the database
        List<FacilityMaster> facilityMasterList = facilityMasterRepository.findAll();
        assertThat(facilityMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFacilityMaster() throws Exception {
        // Initialize the database
        facilityMasterRepository.saveAndFlush(facilityMaster);

        int databaseSizeBeforeDelete = facilityMasterRepository.findAll().size();

        // Delete the facilityMaster
        restFacilityMasterMockMvc
            .perform(delete(ENTITY_API_URL_ID, facilityMaster.getFacilityId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FacilityMaster> facilityMasterList = facilityMasterRepository.findAll();
        assertThat(facilityMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
