package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.DoctorMaster;
import com.sunknowledge.dme.rcm.repository.DoctorMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.DoctorMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.DoctorMasterMapper;
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
 * Integration tests for the {@link DoctorMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DoctorMasterResourceIT {

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_SUFFIX = "AAAAAAAAAA";
    private static final String UPDATED_SUFFIX = "BBBBBBBBBB";

    private static final Long DEFAULT_DOCTOR_SPECIALITY_ID = 1L;
    private static final Long UPDATED_DOCTOR_SPECIALITY_ID = 2L;

    private static final String DEFAULT_ADDL_IDENTIFIER = "AAAAAAAAAA";
    private static final String UPDATED_ADDL_IDENTIFIER = "BBBBBBBBBB";

    private static final UUID DEFAULT_DOCTOR_MASTER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_DOCTOR_MASTER_UUID = UUID.randomUUID();

    private static final String DEFAULT_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_ZIP = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_NO_1 = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_NO_1 = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_NO_2 = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_NO_2 = "BBBBBBBBBB";

    private static final String DEFAULT_FAX_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_FAX_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_EFAX = "AAAAAAAAAA";
    private static final String UPDATED_EFAX = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_RELATIONSHIP = "AAAAAAAAAA";
    private static final String UPDATED_RELATIONSHIP = "BBBBBBBBBB";

    private static final String DEFAULT_MODE_OF_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_MODE_OF_CONTACT = "BBBBBBBBBB";

    private static final String DEFAULT_NPI_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_NPI_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_GENDER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_ENUMERATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ENUMERATION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_COUNTRY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_PURPOSE = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_PURPOSE = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_POSTAL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_POSTAL_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_TAXONOMY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TAXONOMY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_TAXONOMY_GROUP = "AAAAAAAAAA";
    private static final String UPDATED_TAXONOMY_GROUP = "BBBBBBBBBB";

    private static final String DEFAULT_TAXONOMY_DESC = "AAAAAAAAAA";
    private static final String UPDATED_TAXONOMY_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_PRACTICE_STATE = "AAAAAAAAAA";
    private static final String UPDATED_PRACTICE_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_LICENCE_NO = "AAAAAAAAAA";
    private static final String UPDATED_LICENCE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/doctor-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{doctorId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DoctorMasterRepository doctorMasterRepository;

    @Autowired
    private DoctorMasterMapper doctorMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDoctorMasterMockMvc;

    private DoctorMaster doctorMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DoctorMaster createEntity(EntityManager em) {
        DoctorMaster doctorMaster = new DoctorMaster()
            .firstName(DEFAULT_FIRST_NAME)
            .middleName(DEFAULT_MIDDLE_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .title(DEFAULT_TITLE)
            .suffix(DEFAULT_SUFFIX)
            .doctorSpecialityId(DEFAULT_DOCTOR_SPECIALITY_ID)
            .addlIdentifier(DEFAULT_ADDL_IDENTIFIER)
            .doctorMasterUuid(DEFAULT_DOCTOR_MASTER_UUID)
            .addressLine1(DEFAULT_ADDRESS_LINE_1)
            .addressLine2(DEFAULT_ADDRESS_LINE_2)
            .city(DEFAULT_CITY)
            .state(DEFAULT_STATE)
            .countryName(DEFAULT_COUNTRY_NAME)
            .zip(DEFAULT_ZIP)
            .contactNo1(DEFAULT_CONTACT_NO_1)
            .contactNo2(DEFAULT_CONTACT_NO_2)
            .faxNumber(DEFAULT_FAX_NUMBER)
            .efax(DEFAULT_EFAX)
            .email(DEFAULT_EMAIL)
            .relationship(DEFAULT_RELATIONSHIP)
            .modeOfContact(DEFAULT_MODE_OF_CONTACT)
            .npiNumber(DEFAULT_NPI_NUMBER)
            .gender(DEFAULT_GENDER)
            .enumerationDate(DEFAULT_ENUMERATION_DATE)
            .countryCode(DEFAULT_COUNTRY_CODE)
            .addressPurpose(DEFAULT_ADDRESS_PURPOSE)
            .addressType(DEFAULT_ADDRESS_TYPE)
            .postalCode(DEFAULT_POSTAL_CODE)
            .taxonomyCode(DEFAULT_TAXONOMY_CODE)
            .taxonomyGroup(DEFAULT_TAXONOMY_GROUP)
            .taxonomyDesc(DEFAULT_TAXONOMY_DESC)
            .practiceState(DEFAULT_PRACTICE_STATE)
            .licenceNo(DEFAULT_LICENCE_NO)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedDate(DEFAULT_UPDATED_DATE);
        return doctorMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DoctorMaster createUpdatedEntity(EntityManager em) {
        DoctorMaster doctorMaster = new DoctorMaster()
            .firstName(UPDATED_FIRST_NAME)
            .middleName(UPDATED_MIDDLE_NAME)
            .lastName(UPDATED_LAST_NAME)
            .title(UPDATED_TITLE)
            .suffix(UPDATED_SUFFIX)
            .doctorSpecialityId(UPDATED_DOCTOR_SPECIALITY_ID)
            .addlIdentifier(UPDATED_ADDL_IDENTIFIER)
            .doctorMasterUuid(UPDATED_DOCTOR_MASTER_UUID)
            .addressLine1(UPDATED_ADDRESS_LINE_1)
            .addressLine2(UPDATED_ADDRESS_LINE_2)
            .city(UPDATED_CITY)
            .state(UPDATED_STATE)
            .countryName(UPDATED_COUNTRY_NAME)
            .zip(UPDATED_ZIP)
            .contactNo1(UPDATED_CONTACT_NO_1)
            .contactNo2(UPDATED_CONTACT_NO_2)
            .faxNumber(UPDATED_FAX_NUMBER)
            .efax(UPDATED_EFAX)
            .email(UPDATED_EMAIL)
            .relationship(UPDATED_RELATIONSHIP)
            .modeOfContact(UPDATED_MODE_OF_CONTACT)
            .npiNumber(UPDATED_NPI_NUMBER)
            .gender(UPDATED_GENDER)
            .enumerationDate(UPDATED_ENUMERATION_DATE)
            .countryCode(UPDATED_COUNTRY_CODE)
            .addressPurpose(UPDATED_ADDRESS_PURPOSE)
            .addressType(UPDATED_ADDRESS_TYPE)
            .postalCode(UPDATED_POSTAL_CODE)
            .taxonomyCode(UPDATED_TAXONOMY_CODE)
            .taxonomyGroup(UPDATED_TAXONOMY_GROUP)
            .taxonomyDesc(UPDATED_TAXONOMY_DESC)
            .practiceState(UPDATED_PRACTICE_STATE)
            .licenceNo(UPDATED_LICENCE_NO)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE);
        return doctorMaster;
    }

    @BeforeEach
    public void initTest() {
        doctorMaster = createEntity(em);
    }

    @Test
    @Transactional
    void createDoctorMaster() throws Exception {
        int databaseSizeBeforeCreate = doctorMasterRepository.findAll().size();
        // Create the DoctorMaster
        DoctorMasterDTO doctorMasterDTO = doctorMasterMapper.toDto(doctorMaster);
        restDoctorMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(doctorMasterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the DoctorMaster in the database
        List<DoctorMaster> doctorMasterList = doctorMasterRepository.findAll();
        assertThat(doctorMasterList).hasSize(databaseSizeBeforeCreate + 1);
        DoctorMaster testDoctorMaster = doctorMasterList.get(doctorMasterList.size() - 1);
        assertThat(testDoctorMaster.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testDoctorMaster.getMiddleName()).isEqualTo(DEFAULT_MIDDLE_NAME);
        assertThat(testDoctorMaster.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testDoctorMaster.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testDoctorMaster.getSuffix()).isEqualTo(DEFAULT_SUFFIX);
        assertThat(testDoctorMaster.getDoctorSpecialityId()).isEqualTo(DEFAULT_DOCTOR_SPECIALITY_ID);
        assertThat(testDoctorMaster.getAddlIdentifier()).isEqualTo(DEFAULT_ADDL_IDENTIFIER);
        assertThat(testDoctorMaster.getDoctorMasterUuid()).isEqualTo(DEFAULT_DOCTOR_MASTER_UUID);
        assertThat(testDoctorMaster.getAddressLine1()).isEqualTo(DEFAULT_ADDRESS_LINE_1);
        assertThat(testDoctorMaster.getAddressLine2()).isEqualTo(DEFAULT_ADDRESS_LINE_2);
        assertThat(testDoctorMaster.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testDoctorMaster.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testDoctorMaster.getCountryName()).isEqualTo(DEFAULT_COUNTRY_NAME);
        assertThat(testDoctorMaster.getZip()).isEqualTo(DEFAULT_ZIP);
        assertThat(testDoctorMaster.getContactNo1()).isEqualTo(DEFAULT_CONTACT_NO_1);
        assertThat(testDoctorMaster.getContactNo2()).isEqualTo(DEFAULT_CONTACT_NO_2);
        assertThat(testDoctorMaster.getFaxNumber()).isEqualTo(DEFAULT_FAX_NUMBER);
        assertThat(testDoctorMaster.getEfax()).isEqualTo(DEFAULT_EFAX);
        assertThat(testDoctorMaster.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testDoctorMaster.getRelationship()).isEqualTo(DEFAULT_RELATIONSHIP);
        assertThat(testDoctorMaster.getModeOfContact()).isEqualTo(DEFAULT_MODE_OF_CONTACT);
        assertThat(testDoctorMaster.getNpiNumber()).isEqualTo(DEFAULT_NPI_NUMBER);
        assertThat(testDoctorMaster.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testDoctorMaster.getEnumerationDate()).isEqualTo(DEFAULT_ENUMERATION_DATE);
        assertThat(testDoctorMaster.getCountryCode()).isEqualTo(DEFAULT_COUNTRY_CODE);
        assertThat(testDoctorMaster.getAddressPurpose()).isEqualTo(DEFAULT_ADDRESS_PURPOSE);
        assertThat(testDoctorMaster.getAddressType()).isEqualTo(DEFAULT_ADDRESS_TYPE);
        assertThat(testDoctorMaster.getPostalCode()).isEqualTo(DEFAULT_POSTAL_CODE);
        assertThat(testDoctorMaster.getTaxonomyCode()).isEqualTo(DEFAULT_TAXONOMY_CODE);
        assertThat(testDoctorMaster.getTaxonomyGroup()).isEqualTo(DEFAULT_TAXONOMY_GROUP);
        assertThat(testDoctorMaster.getTaxonomyDesc()).isEqualTo(DEFAULT_TAXONOMY_DESC);
        assertThat(testDoctorMaster.getPracticeState()).isEqualTo(DEFAULT_PRACTICE_STATE);
        assertThat(testDoctorMaster.getLicenceNo()).isEqualTo(DEFAULT_LICENCE_NO);
        assertThat(testDoctorMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testDoctorMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testDoctorMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testDoctorMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testDoctorMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testDoctorMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testDoctorMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
    }

    @Test
    @Transactional
    void createDoctorMasterWithExistingId() throws Exception {
        // Create the DoctorMaster with an existing ID
        doctorMaster.setDoctorId(1L);
        DoctorMasterDTO doctorMasterDTO = doctorMasterMapper.toDto(doctorMaster);

        int databaseSizeBeforeCreate = doctorMasterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDoctorMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(doctorMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DoctorMaster in the database
        List<DoctorMaster> doctorMasterList = doctorMasterRepository.findAll();
        assertThat(doctorMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDoctorMasters() throws Exception {
        // Initialize the database
        doctorMasterRepository.saveAndFlush(doctorMaster);

        // Get all the doctorMasterList
        restDoctorMasterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=doctorId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].doctorId").value(hasItem(doctorMaster.getDoctorId().intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].middleName").value(hasItem(DEFAULT_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].suffix").value(hasItem(DEFAULT_SUFFIX)))
            .andExpect(jsonPath("$.[*].doctorSpecialityId").value(hasItem(DEFAULT_DOCTOR_SPECIALITY_ID.intValue())))
            .andExpect(jsonPath("$.[*].addlIdentifier").value(hasItem(DEFAULT_ADDL_IDENTIFIER)))
            .andExpect(jsonPath("$.[*].doctorMasterUuid").value(hasItem(DEFAULT_DOCTOR_MASTER_UUID.toString())))
            .andExpect(jsonPath("$.[*].addressLine1").value(hasItem(DEFAULT_ADDRESS_LINE_1)))
            .andExpect(jsonPath("$.[*].addressLine2").value(hasItem(DEFAULT_ADDRESS_LINE_2)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE)))
            .andExpect(jsonPath("$.[*].countryName").value(hasItem(DEFAULT_COUNTRY_NAME)))
            .andExpect(jsonPath("$.[*].zip").value(hasItem(DEFAULT_ZIP)))
            .andExpect(jsonPath("$.[*].contactNo1").value(hasItem(DEFAULT_CONTACT_NO_1)))
            .andExpect(jsonPath("$.[*].contactNo2").value(hasItem(DEFAULT_CONTACT_NO_2)))
            .andExpect(jsonPath("$.[*].faxNumber").value(hasItem(DEFAULT_FAX_NUMBER)))
            .andExpect(jsonPath("$.[*].efax").value(hasItem(DEFAULT_EFAX)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].relationship").value(hasItem(DEFAULT_RELATIONSHIP)))
            .andExpect(jsonPath("$.[*].modeOfContact").value(hasItem(DEFAULT_MODE_OF_CONTACT)))
            .andExpect(jsonPath("$.[*].npiNumber").value(hasItem(DEFAULT_NPI_NUMBER)))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER)))
            .andExpect(jsonPath("$.[*].enumerationDate").value(hasItem(DEFAULT_ENUMERATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].countryCode").value(hasItem(DEFAULT_COUNTRY_CODE)))
            .andExpect(jsonPath("$.[*].addressPurpose").value(hasItem(DEFAULT_ADDRESS_PURPOSE)))
            .andExpect(jsonPath("$.[*].addressType").value(hasItem(DEFAULT_ADDRESS_TYPE)))
            .andExpect(jsonPath("$.[*].postalCode").value(hasItem(DEFAULT_POSTAL_CODE)))
            .andExpect(jsonPath("$.[*].taxonomyCode").value(hasItem(DEFAULT_TAXONOMY_CODE)))
            .andExpect(jsonPath("$.[*].taxonomyGroup").value(hasItem(DEFAULT_TAXONOMY_GROUP)))
            .andExpect(jsonPath("$.[*].taxonomyDesc").value(hasItem(DEFAULT_TAXONOMY_DESC)))
            .andExpect(jsonPath("$.[*].practiceState").value(hasItem(DEFAULT_PRACTICE_STATE)))
            .andExpect(jsonPath("$.[*].licenceNo").value(hasItem(DEFAULT_LICENCE_NO)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())));
    }

    @Test
    @Transactional
    void getDoctorMaster() throws Exception {
        // Initialize the database
        doctorMasterRepository.saveAndFlush(doctorMaster);

        // Get the doctorMaster
        restDoctorMasterMockMvc
            .perform(get(ENTITY_API_URL_ID, doctorMaster.getDoctorId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.doctorId").value(doctorMaster.getDoctorId().intValue()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.middleName").value(DEFAULT_MIDDLE_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.suffix").value(DEFAULT_SUFFIX))
            .andExpect(jsonPath("$.doctorSpecialityId").value(DEFAULT_DOCTOR_SPECIALITY_ID.intValue()))
            .andExpect(jsonPath("$.addlIdentifier").value(DEFAULT_ADDL_IDENTIFIER))
            .andExpect(jsonPath("$.doctorMasterUuid").value(DEFAULT_DOCTOR_MASTER_UUID.toString()))
            .andExpect(jsonPath("$.addressLine1").value(DEFAULT_ADDRESS_LINE_1))
            .andExpect(jsonPath("$.addressLine2").value(DEFAULT_ADDRESS_LINE_2))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE))
            .andExpect(jsonPath("$.countryName").value(DEFAULT_COUNTRY_NAME))
            .andExpect(jsonPath("$.zip").value(DEFAULT_ZIP))
            .andExpect(jsonPath("$.contactNo1").value(DEFAULT_CONTACT_NO_1))
            .andExpect(jsonPath("$.contactNo2").value(DEFAULT_CONTACT_NO_2))
            .andExpect(jsonPath("$.faxNumber").value(DEFAULT_FAX_NUMBER))
            .andExpect(jsonPath("$.efax").value(DEFAULT_EFAX))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.relationship").value(DEFAULT_RELATIONSHIP))
            .andExpect(jsonPath("$.modeOfContact").value(DEFAULT_MODE_OF_CONTACT))
            .andExpect(jsonPath("$.npiNumber").value(DEFAULT_NPI_NUMBER))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER))
            .andExpect(jsonPath("$.enumerationDate").value(DEFAULT_ENUMERATION_DATE.toString()))
            .andExpect(jsonPath("$.countryCode").value(DEFAULT_COUNTRY_CODE))
            .andExpect(jsonPath("$.addressPurpose").value(DEFAULT_ADDRESS_PURPOSE))
            .andExpect(jsonPath("$.addressType").value(DEFAULT_ADDRESS_TYPE))
            .andExpect(jsonPath("$.postalCode").value(DEFAULT_POSTAL_CODE))
            .andExpect(jsonPath("$.taxonomyCode").value(DEFAULT_TAXONOMY_CODE))
            .andExpect(jsonPath("$.taxonomyGroup").value(DEFAULT_TAXONOMY_GROUP))
            .andExpect(jsonPath("$.taxonomyDesc").value(DEFAULT_TAXONOMY_DESC))
            .andExpect(jsonPath("$.practiceState").value(DEFAULT_PRACTICE_STATE))
            .andExpect(jsonPath("$.licenceNo").value(DEFAULT_LICENCE_NO))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingDoctorMaster() throws Exception {
        // Get the doctorMaster
        restDoctorMasterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewDoctorMaster() throws Exception {
        // Initialize the database
        doctorMasterRepository.saveAndFlush(doctorMaster);

        int databaseSizeBeforeUpdate = doctorMasterRepository.findAll().size();

        // Update the doctorMaster
        DoctorMaster updatedDoctorMaster = doctorMasterRepository.findById(doctorMaster.getDoctorId()).get();
        // Disconnect from session so that the updates on updatedDoctorMaster are not directly saved in db
        em.detach(updatedDoctorMaster);
        updatedDoctorMaster
            .firstName(UPDATED_FIRST_NAME)
            .middleName(UPDATED_MIDDLE_NAME)
            .lastName(UPDATED_LAST_NAME)
            .title(UPDATED_TITLE)
            .suffix(UPDATED_SUFFIX)
            .doctorSpecialityId(UPDATED_DOCTOR_SPECIALITY_ID)
            .addlIdentifier(UPDATED_ADDL_IDENTIFIER)
            .doctorMasterUuid(UPDATED_DOCTOR_MASTER_UUID)
            .addressLine1(UPDATED_ADDRESS_LINE_1)
            .addressLine2(UPDATED_ADDRESS_LINE_2)
            .city(UPDATED_CITY)
            .state(UPDATED_STATE)
            .countryName(UPDATED_COUNTRY_NAME)
            .zip(UPDATED_ZIP)
            .contactNo1(UPDATED_CONTACT_NO_1)
            .contactNo2(UPDATED_CONTACT_NO_2)
            .faxNumber(UPDATED_FAX_NUMBER)
            .efax(UPDATED_EFAX)
            .email(UPDATED_EMAIL)
            .relationship(UPDATED_RELATIONSHIP)
            .modeOfContact(UPDATED_MODE_OF_CONTACT)
            .npiNumber(UPDATED_NPI_NUMBER)
            .gender(UPDATED_GENDER)
            .enumerationDate(UPDATED_ENUMERATION_DATE)
            .countryCode(UPDATED_COUNTRY_CODE)
            .addressPurpose(UPDATED_ADDRESS_PURPOSE)
            .addressType(UPDATED_ADDRESS_TYPE)
            .postalCode(UPDATED_POSTAL_CODE)
            .taxonomyCode(UPDATED_TAXONOMY_CODE)
            .taxonomyGroup(UPDATED_TAXONOMY_GROUP)
            .taxonomyDesc(UPDATED_TAXONOMY_DESC)
            .practiceState(UPDATED_PRACTICE_STATE)
            .licenceNo(UPDATED_LICENCE_NO)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE);
        DoctorMasterDTO doctorMasterDTO = doctorMasterMapper.toDto(updatedDoctorMaster);

        restDoctorMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, doctorMasterDTO.getDoctorId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(doctorMasterDTO))
            )
            .andExpect(status().isOk());

        // Validate the DoctorMaster in the database
        List<DoctorMaster> doctorMasterList = doctorMasterRepository.findAll();
        assertThat(doctorMasterList).hasSize(databaseSizeBeforeUpdate);
        DoctorMaster testDoctorMaster = doctorMasterList.get(doctorMasterList.size() - 1);
        assertThat(testDoctorMaster.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testDoctorMaster.getMiddleName()).isEqualTo(UPDATED_MIDDLE_NAME);
        assertThat(testDoctorMaster.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testDoctorMaster.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testDoctorMaster.getSuffix()).isEqualTo(UPDATED_SUFFIX);
        assertThat(testDoctorMaster.getDoctorSpecialityId()).isEqualTo(UPDATED_DOCTOR_SPECIALITY_ID);
        assertThat(testDoctorMaster.getAddlIdentifier()).isEqualTo(UPDATED_ADDL_IDENTIFIER);
        assertThat(testDoctorMaster.getDoctorMasterUuid()).isEqualTo(UPDATED_DOCTOR_MASTER_UUID);
        assertThat(testDoctorMaster.getAddressLine1()).isEqualTo(UPDATED_ADDRESS_LINE_1);
        assertThat(testDoctorMaster.getAddressLine2()).isEqualTo(UPDATED_ADDRESS_LINE_2);
        assertThat(testDoctorMaster.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testDoctorMaster.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testDoctorMaster.getCountryName()).isEqualTo(UPDATED_COUNTRY_NAME);
        assertThat(testDoctorMaster.getZip()).isEqualTo(UPDATED_ZIP);
        assertThat(testDoctorMaster.getContactNo1()).isEqualTo(UPDATED_CONTACT_NO_1);
        assertThat(testDoctorMaster.getContactNo2()).isEqualTo(UPDATED_CONTACT_NO_2);
        assertThat(testDoctorMaster.getFaxNumber()).isEqualTo(UPDATED_FAX_NUMBER);
        assertThat(testDoctorMaster.getEfax()).isEqualTo(UPDATED_EFAX);
        assertThat(testDoctorMaster.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testDoctorMaster.getRelationship()).isEqualTo(UPDATED_RELATIONSHIP);
        assertThat(testDoctorMaster.getModeOfContact()).isEqualTo(UPDATED_MODE_OF_CONTACT);
        assertThat(testDoctorMaster.getNpiNumber()).isEqualTo(UPDATED_NPI_NUMBER);
        assertThat(testDoctorMaster.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testDoctorMaster.getEnumerationDate()).isEqualTo(UPDATED_ENUMERATION_DATE);
        assertThat(testDoctorMaster.getCountryCode()).isEqualTo(UPDATED_COUNTRY_CODE);
        assertThat(testDoctorMaster.getAddressPurpose()).isEqualTo(UPDATED_ADDRESS_PURPOSE);
        assertThat(testDoctorMaster.getAddressType()).isEqualTo(UPDATED_ADDRESS_TYPE);
        assertThat(testDoctorMaster.getPostalCode()).isEqualTo(UPDATED_POSTAL_CODE);
        assertThat(testDoctorMaster.getTaxonomyCode()).isEqualTo(UPDATED_TAXONOMY_CODE);
        assertThat(testDoctorMaster.getTaxonomyGroup()).isEqualTo(UPDATED_TAXONOMY_GROUP);
        assertThat(testDoctorMaster.getTaxonomyDesc()).isEqualTo(UPDATED_TAXONOMY_DESC);
        assertThat(testDoctorMaster.getPracticeState()).isEqualTo(UPDATED_PRACTICE_STATE);
        assertThat(testDoctorMaster.getLicenceNo()).isEqualTo(UPDATED_LICENCE_NO);
        assertThat(testDoctorMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDoctorMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testDoctorMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testDoctorMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testDoctorMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testDoctorMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testDoctorMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
    }

    @Test
    @Transactional
    void putNonExistingDoctorMaster() throws Exception {
        int databaseSizeBeforeUpdate = doctorMasterRepository.findAll().size();
        doctorMaster.setDoctorId(count.incrementAndGet());

        // Create the DoctorMaster
        DoctorMasterDTO doctorMasterDTO = doctorMasterMapper.toDto(doctorMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDoctorMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, doctorMasterDTO.getDoctorId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(doctorMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DoctorMaster in the database
        List<DoctorMaster> doctorMasterList = doctorMasterRepository.findAll();
        assertThat(doctorMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDoctorMaster() throws Exception {
        int databaseSizeBeforeUpdate = doctorMasterRepository.findAll().size();
        doctorMaster.setDoctorId(count.incrementAndGet());

        // Create the DoctorMaster
        DoctorMasterDTO doctorMasterDTO = doctorMasterMapper.toDto(doctorMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDoctorMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(doctorMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DoctorMaster in the database
        List<DoctorMaster> doctorMasterList = doctorMasterRepository.findAll();
        assertThat(doctorMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDoctorMaster() throws Exception {
        int databaseSizeBeforeUpdate = doctorMasterRepository.findAll().size();
        doctorMaster.setDoctorId(count.incrementAndGet());

        // Create the DoctorMaster
        DoctorMasterDTO doctorMasterDTO = doctorMasterMapper.toDto(doctorMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDoctorMasterMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(doctorMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DoctorMaster in the database
        List<DoctorMaster> doctorMasterList = doctorMasterRepository.findAll();
        assertThat(doctorMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDoctorMasterWithPatch() throws Exception {
        // Initialize the database
        doctorMasterRepository.saveAndFlush(doctorMaster);

        int databaseSizeBeforeUpdate = doctorMasterRepository.findAll().size();

        // Update the doctorMaster using partial update
        DoctorMaster partialUpdatedDoctorMaster = new DoctorMaster();
        partialUpdatedDoctorMaster.setDoctorId(doctorMaster.getDoctorId());

        partialUpdatedDoctorMaster
            .addlIdentifier(UPDATED_ADDL_IDENTIFIER)
            .doctorMasterUuid(UPDATED_DOCTOR_MASTER_UUID)
            .contactNo2(UPDATED_CONTACT_NO_2)
            .email(UPDATED_EMAIL)
            .relationship(UPDATED_RELATIONSHIP)
            .modeOfContact(UPDATED_MODE_OF_CONTACT)
            .gender(UPDATED_GENDER)
            .addressPurpose(UPDATED_ADDRESS_PURPOSE)
            .postalCode(UPDATED_POSTAL_CODE)
            .taxonomyCode(UPDATED_TAXONOMY_CODE)
            .taxonomyDesc(UPDATED_TAXONOMY_DESC)
            .licenceNo(UPDATED_LICENCE_NO)
            .status(UPDATED_STATUS)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME);

        restDoctorMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDoctorMaster.getDoctorId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDoctorMaster))
            )
            .andExpect(status().isOk());

        // Validate the DoctorMaster in the database
        List<DoctorMaster> doctorMasterList = doctorMasterRepository.findAll();
        assertThat(doctorMasterList).hasSize(databaseSizeBeforeUpdate);
        DoctorMaster testDoctorMaster = doctorMasterList.get(doctorMasterList.size() - 1);
        assertThat(testDoctorMaster.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testDoctorMaster.getMiddleName()).isEqualTo(DEFAULT_MIDDLE_NAME);
        assertThat(testDoctorMaster.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testDoctorMaster.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testDoctorMaster.getSuffix()).isEqualTo(DEFAULT_SUFFIX);
        assertThat(testDoctorMaster.getDoctorSpecialityId()).isEqualTo(DEFAULT_DOCTOR_SPECIALITY_ID);
        assertThat(testDoctorMaster.getAddlIdentifier()).isEqualTo(UPDATED_ADDL_IDENTIFIER);
        assertThat(testDoctorMaster.getDoctorMasterUuid()).isEqualTo(UPDATED_DOCTOR_MASTER_UUID);
        assertThat(testDoctorMaster.getAddressLine1()).isEqualTo(DEFAULT_ADDRESS_LINE_1);
        assertThat(testDoctorMaster.getAddressLine2()).isEqualTo(DEFAULT_ADDRESS_LINE_2);
        assertThat(testDoctorMaster.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testDoctorMaster.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testDoctorMaster.getCountryName()).isEqualTo(DEFAULT_COUNTRY_NAME);
        assertThat(testDoctorMaster.getZip()).isEqualTo(DEFAULT_ZIP);
        assertThat(testDoctorMaster.getContactNo1()).isEqualTo(DEFAULT_CONTACT_NO_1);
        assertThat(testDoctorMaster.getContactNo2()).isEqualTo(UPDATED_CONTACT_NO_2);
        assertThat(testDoctorMaster.getFaxNumber()).isEqualTo(DEFAULT_FAX_NUMBER);
        assertThat(testDoctorMaster.getEfax()).isEqualTo(DEFAULT_EFAX);
        assertThat(testDoctorMaster.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testDoctorMaster.getRelationship()).isEqualTo(UPDATED_RELATIONSHIP);
        assertThat(testDoctorMaster.getModeOfContact()).isEqualTo(UPDATED_MODE_OF_CONTACT);
        assertThat(testDoctorMaster.getNpiNumber()).isEqualTo(DEFAULT_NPI_NUMBER);
        assertThat(testDoctorMaster.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testDoctorMaster.getEnumerationDate()).isEqualTo(DEFAULT_ENUMERATION_DATE);
        assertThat(testDoctorMaster.getCountryCode()).isEqualTo(DEFAULT_COUNTRY_CODE);
        assertThat(testDoctorMaster.getAddressPurpose()).isEqualTo(UPDATED_ADDRESS_PURPOSE);
        assertThat(testDoctorMaster.getAddressType()).isEqualTo(DEFAULT_ADDRESS_TYPE);
        assertThat(testDoctorMaster.getPostalCode()).isEqualTo(UPDATED_POSTAL_CODE);
        assertThat(testDoctorMaster.getTaxonomyCode()).isEqualTo(UPDATED_TAXONOMY_CODE);
        assertThat(testDoctorMaster.getTaxonomyGroup()).isEqualTo(DEFAULT_TAXONOMY_GROUP);
        assertThat(testDoctorMaster.getTaxonomyDesc()).isEqualTo(UPDATED_TAXONOMY_DESC);
        assertThat(testDoctorMaster.getPracticeState()).isEqualTo(DEFAULT_PRACTICE_STATE);
        assertThat(testDoctorMaster.getLicenceNo()).isEqualTo(UPDATED_LICENCE_NO);
        assertThat(testDoctorMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDoctorMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testDoctorMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testDoctorMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testDoctorMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testDoctorMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testDoctorMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
    }

    @Test
    @Transactional
    void fullUpdateDoctorMasterWithPatch() throws Exception {
        // Initialize the database
        doctorMasterRepository.saveAndFlush(doctorMaster);

        int databaseSizeBeforeUpdate = doctorMasterRepository.findAll().size();

        // Update the doctorMaster using partial update
        DoctorMaster partialUpdatedDoctorMaster = new DoctorMaster();
        partialUpdatedDoctorMaster.setDoctorId(doctorMaster.getDoctorId());

        partialUpdatedDoctorMaster
            .firstName(UPDATED_FIRST_NAME)
            .middleName(UPDATED_MIDDLE_NAME)
            .lastName(UPDATED_LAST_NAME)
            .title(UPDATED_TITLE)
            .suffix(UPDATED_SUFFIX)
            .doctorSpecialityId(UPDATED_DOCTOR_SPECIALITY_ID)
            .addlIdentifier(UPDATED_ADDL_IDENTIFIER)
            .doctorMasterUuid(UPDATED_DOCTOR_MASTER_UUID)
            .addressLine1(UPDATED_ADDRESS_LINE_1)
            .addressLine2(UPDATED_ADDRESS_LINE_2)
            .city(UPDATED_CITY)
            .state(UPDATED_STATE)
            .countryName(UPDATED_COUNTRY_NAME)
            .zip(UPDATED_ZIP)
            .contactNo1(UPDATED_CONTACT_NO_1)
            .contactNo2(UPDATED_CONTACT_NO_2)
            .faxNumber(UPDATED_FAX_NUMBER)
            .efax(UPDATED_EFAX)
            .email(UPDATED_EMAIL)
            .relationship(UPDATED_RELATIONSHIP)
            .modeOfContact(UPDATED_MODE_OF_CONTACT)
            .npiNumber(UPDATED_NPI_NUMBER)
            .gender(UPDATED_GENDER)
            .enumerationDate(UPDATED_ENUMERATION_DATE)
            .countryCode(UPDATED_COUNTRY_CODE)
            .addressPurpose(UPDATED_ADDRESS_PURPOSE)
            .addressType(UPDATED_ADDRESS_TYPE)
            .postalCode(UPDATED_POSTAL_CODE)
            .taxonomyCode(UPDATED_TAXONOMY_CODE)
            .taxonomyGroup(UPDATED_TAXONOMY_GROUP)
            .taxonomyDesc(UPDATED_TAXONOMY_DESC)
            .practiceState(UPDATED_PRACTICE_STATE)
            .licenceNo(UPDATED_LICENCE_NO)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE);

        restDoctorMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDoctorMaster.getDoctorId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDoctorMaster))
            )
            .andExpect(status().isOk());

        // Validate the DoctorMaster in the database
        List<DoctorMaster> doctorMasterList = doctorMasterRepository.findAll();
        assertThat(doctorMasterList).hasSize(databaseSizeBeforeUpdate);
        DoctorMaster testDoctorMaster = doctorMasterList.get(doctorMasterList.size() - 1);
        assertThat(testDoctorMaster.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testDoctorMaster.getMiddleName()).isEqualTo(UPDATED_MIDDLE_NAME);
        assertThat(testDoctorMaster.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testDoctorMaster.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testDoctorMaster.getSuffix()).isEqualTo(UPDATED_SUFFIX);
        assertThat(testDoctorMaster.getDoctorSpecialityId()).isEqualTo(UPDATED_DOCTOR_SPECIALITY_ID);
        assertThat(testDoctorMaster.getAddlIdentifier()).isEqualTo(UPDATED_ADDL_IDENTIFIER);
        assertThat(testDoctorMaster.getDoctorMasterUuid()).isEqualTo(UPDATED_DOCTOR_MASTER_UUID);
        assertThat(testDoctorMaster.getAddressLine1()).isEqualTo(UPDATED_ADDRESS_LINE_1);
        assertThat(testDoctorMaster.getAddressLine2()).isEqualTo(UPDATED_ADDRESS_LINE_2);
        assertThat(testDoctorMaster.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testDoctorMaster.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testDoctorMaster.getCountryName()).isEqualTo(UPDATED_COUNTRY_NAME);
        assertThat(testDoctorMaster.getZip()).isEqualTo(UPDATED_ZIP);
        assertThat(testDoctorMaster.getContactNo1()).isEqualTo(UPDATED_CONTACT_NO_1);
        assertThat(testDoctorMaster.getContactNo2()).isEqualTo(UPDATED_CONTACT_NO_2);
        assertThat(testDoctorMaster.getFaxNumber()).isEqualTo(UPDATED_FAX_NUMBER);
        assertThat(testDoctorMaster.getEfax()).isEqualTo(UPDATED_EFAX);
        assertThat(testDoctorMaster.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testDoctorMaster.getRelationship()).isEqualTo(UPDATED_RELATIONSHIP);
        assertThat(testDoctorMaster.getModeOfContact()).isEqualTo(UPDATED_MODE_OF_CONTACT);
        assertThat(testDoctorMaster.getNpiNumber()).isEqualTo(UPDATED_NPI_NUMBER);
        assertThat(testDoctorMaster.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testDoctorMaster.getEnumerationDate()).isEqualTo(UPDATED_ENUMERATION_DATE);
        assertThat(testDoctorMaster.getCountryCode()).isEqualTo(UPDATED_COUNTRY_CODE);
        assertThat(testDoctorMaster.getAddressPurpose()).isEqualTo(UPDATED_ADDRESS_PURPOSE);
        assertThat(testDoctorMaster.getAddressType()).isEqualTo(UPDATED_ADDRESS_TYPE);
        assertThat(testDoctorMaster.getPostalCode()).isEqualTo(UPDATED_POSTAL_CODE);
        assertThat(testDoctorMaster.getTaxonomyCode()).isEqualTo(UPDATED_TAXONOMY_CODE);
        assertThat(testDoctorMaster.getTaxonomyGroup()).isEqualTo(UPDATED_TAXONOMY_GROUP);
        assertThat(testDoctorMaster.getTaxonomyDesc()).isEqualTo(UPDATED_TAXONOMY_DESC);
        assertThat(testDoctorMaster.getPracticeState()).isEqualTo(UPDATED_PRACTICE_STATE);
        assertThat(testDoctorMaster.getLicenceNo()).isEqualTo(UPDATED_LICENCE_NO);
        assertThat(testDoctorMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDoctorMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testDoctorMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testDoctorMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testDoctorMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testDoctorMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testDoctorMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingDoctorMaster() throws Exception {
        int databaseSizeBeforeUpdate = doctorMasterRepository.findAll().size();
        doctorMaster.setDoctorId(count.incrementAndGet());

        // Create the DoctorMaster
        DoctorMasterDTO doctorMasterDTO = doctorMasterMapper.toDto(doctorMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDoctorMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, doctorMasterDTO.getDoctorId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(doctorMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DoctorMaster in the database
        List<DoctorMaster> doctorMasterList = doctorMasterRepository.findAll();
        assertThat(doctorMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDoctorMaster() throws Exception {
        int databaseSizeBeforeUpdate = doctorMasterRepository.findAll().size();
        doctorMaster.setDoctorId(count.incrementAndGet());

        // Create the DoctorMaster
        DoctorMasterDTO doctorMasterDTO = doctorMasterMapper.toDto(doctorMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDoctorMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(doctorMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DoctorMaster in the database
        List<DoctorMaster> doctorMasterList = doctorMasterRepository.findAll();
        assertThat(doctorMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDoctorMaster() throws Exception {
        int databaseSizeBeforeUpdate = doctorMasterRepository.findAll().size();
        doctorMaster.setDoctorId(count.incrementAndGet());

        // Create the DoctorMaster
        DoctorMasterDTO doctorMasterDTO = doctorMasterMapper.toDto(doctorMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDoctorMasterMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(doctorMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DoctorMaster in the database
        List<DoctorMaster> doctorMasterList = doctorMasterRepository.findAll();
        assertThat(doctorMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDoctorMaster() throws Exception {
        // Initialize the database
        doctorMasterRepository.saveAndFlush(doctorMaster);

        int databaseSizeBeforeDelete = doctorMasterRepository.findAll().size();

        // Delete the doctorMaster
        restDoctorMasterMockMvc
            .perform(delete(ENTITY_API_URL_ID, doctorMaster.getDoctorId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DoctorMaster> doctorMasterList = doctorMasterRepository.findAll();
        assertThat(doctorMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
