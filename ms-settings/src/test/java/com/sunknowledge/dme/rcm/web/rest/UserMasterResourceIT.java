package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.UserMaster;
import com.sunknowledge.dme.rcm.repository.UserMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.UserMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.UserMasterMapper;
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
 * Integration tests for the {@link UserMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class UserMasterResourceIT {

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DOB = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DOB = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_AGE = 1L;
    private static final Long UPDATED_AGE = 2L;

    private static final String DEFAULT_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_GENDER = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_USER_MASTER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_USER_MASTER_UUID = UUID.randomUUID();

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

    private static final String DEFAULT_JOB_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_JOB_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_MODE_OF_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_MODE_OF_CONTACT = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_DEACTIVATE = false;
    private static final Boolean UPDATED_IS_DEACTIVATE = true;

    private static final String DEFAULT_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_USERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/user-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{userId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private UserMasterRepository userMasterRepository;

    @Autowired
    private UserMasterMapper userMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserMasterMockMvc;

    private UserMaster userMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserMaster createEntity(EntityManager em) {
        UserMaster userMaster = new UserMaster()
            .firstName(DEFAULT_FIRST_NAME)
            .middleName(DEFAULT_MIDDLE_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .dob(DEFAULT_DOB)
            .age(DEFAULT_AGE)
            .gender(DEFAULT_GENDER)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .userMasterUuid(DEFAULT_USER_MASTER_UUID)
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
            .jobTitle(DEFAULT_JOB_TITLE)
            .modeOfContact(DEFAULT_MODE_OF_CONTACT)
            .isDeactivate(DEFAULT_IS_DEACTIVATE)
            .username(DEFAULT_USERNAME)
            .password(DEFAULT_PASSWORD);
        return userMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserMaster createUpdatedEntity(EntityManager em) {
        UserMaster userMaster = new UserMaster()
            .firstName(UPDATED_FIRST_NAME)
            .middleName(UPDATED_MIDDLE_NAME)
            .lastName(UPDATED_LAST_NAME)
            .dob(UPDATED_DOB)
            .age(UPDATED_AGE)
            .gender(UPDATED_GENDER)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .userMasterUuid(UPDATED_USER_MASTER_UUID)
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
            .jobTitle(UPDATED_JOB_TITLE)
            .modeOfContact(UPDATED_MODE_OF_CONTACT)
            .isDeactivate(UPDATED_IS_DEACTIVATE)
            .username(UPDATED_USERNAME)
            .password(UPDATED_PASSWORD);
        return userMaster;
    }

    @BeforeEach
    public void initTest() {
        userMaster = createEntity(em);
    }

    @Test
    @Transactional
    void createUserMaster() throws Exception {
        int databaseSizeBeforeCreate = userMasterRepository.findAll().size();
        // Create the UserMaster
        UserMasterDTO userMasterDTO = userMasterMapper.toDto(userMaster);
        restUserMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userMasterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the UserMaster in the database
        List<UserMaster> userMasterList = userMasterRepository.findAll();
        assertThat(userMasterList).hasSize(databaseSizeBeforeCreate + 1);
        UserMaster testUserMaster = userMasterList.get(userMasterList.size() - 1);
        assertThat(testUserMaster.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testUserMaster.getMiddleName()).isEqualTo(DEFAULT_MIDDLE_NAME);
        assertThat(testUserMaster.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testUserMaster.getDob()).isEqualTo(DEFAULT_DOB);
        assertThat(testUserMaster.getAge()).isEqualTo(DEFAULT_AGE);
        assertThat(testUserMaster.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testUserMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testUserMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testUserMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testUserMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testUserMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testUserMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testUserMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testUserMaster.getUserMasterUuid()).isEqualTo(DEFAULT_USER_MASTER_UUID);
        assertThat(testUserMaster.getAddressLine1()).isEqualTo(DEFAULT_ADDRESS_LINE_1);
        assertThat(testUserMaster.getAddressLine2()).isEqualTo(DEFAULT_ADDRESS_LINE_2);
        assertThat(testUserMaster.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testUserMaster.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testUserMaster.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testUserMaster.getZip()).isEqualTo(DEFAULT_ZIP);
        assertThat(testUserMaster.getContactNo1()).isEqualTo(DEFAULT_CONTACT_NO_1);
        assertThat(testUserMaster.getContactNo2()).isEqualTo(DEFAULT_CONTACT_NO_2);
        assertThat(testUserMaster.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testUserMaster.getEfax()).isEqualTo(DEFAULT_EFAX);
        assertThat(testUserMaster.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testUserMaster.getJobTitle()).isEqualTo(DEFAULT_JOB_TITLE);
        assertThat(testUserMaster.getModeOfContact()).isEqualTo(DEFAULT_MODE_OF_CONTACT);
        assertThat(testUserMaster.getIsDeactivate()).isEqualTo(DEFAULT_IS_DEACTIVATE);
        assertThat(testUserMaster.getUsername()).isEqualTo(DEFAULT_USERNAME);
        assertThat(testUserMaster.getPassword()).isEqualTo(DEFAULT_PASSWORD);
    }

    @Test
    @Transactional
    void createUserMasterWithExistingId() throws Exception {
        // Create the UserMaster with an existing ID
        userMaster.setUserId(1L);
        UserMasterDTO userMasterDTO = userMasterMapper.toDto(userMaster);

        int databaseSizeBeforeCreate = userMasterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserMaster in the database
        List<UserMaster> userMasterList = userMasterRepository.findAll();
        assertThat(userMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllUserMasters() throws Exception {
        // Initialize the database
        userMasterRepository.saveAndFlush(userMaster);

        // Get all the userMasterList
        restUserMasterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=userId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(userMaster.getUserId().intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].middleName").value(hasItem(DEFAULT_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].dob").value(hasItem(DEFAULT_DOB.toString())))
            .andExpect(jsonPath("$.[*].age").value(hasItem(DEFAULT_AGE.intValue())))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].userMasterUuid").value(hasItem(DEFAULT_USER_MASTER_UUID.toString())))
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
            .andExpect(jsonPath("$.[*].jobTitle").value(hasItem(DEFAULT_JOB_TITLE)))
            .andExpect(jsonPath("$.[*].modeOfContact").value(hasItem(DEFAULT_MODE_OF_CONTACT)))
            .andExpect(jsonPath("$.[*].isDeactivate").value(hasItem(DEFAULT_IS_DEACTIVATE.booleanValue())))
            .andExpect(jsonPath("$.[*].username").value(hasItem(DEFAULT_USERNAME)))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD)));
    }

    @Test
    @Transactional
    void getUserMaster() throws Exception {
        // Initialize the database
        userMasterRepository.saveAndFlush(userMaster);

        // Get the userMaster
        restUserMasterMockMvc
            .perform(get(ENTITY_API_URL_ID, userMaster.getUserId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.userId").value(userMaster.getUserId().intValue()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.middleName").value(DEFAULT_MIDDLE_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.dob").value(DEFAULT_DOB.toString()))
            .andExpect(jsonPath("$.age").value(DEFAULT_AGE.intValue()))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.userMasterUuid").value(DEFAULT_USER_MASTER_UUID.toString()))
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
            .andExpect(jsonPath("$.jobTitle").value(DEFAULT_JOB_TITLE))
            .andExpect(jsonPath("$.modeOfContact").value(DEFAULT_MODE_OF_CONTACT))
            .andExpect(jsonPath("$.isDeactivate").value(DEFAULT_IS_DEACTIVATE.booleanValue()))
            .andExpect(jsonPath("$.username").value(DEFAULT_USERNAME))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD));
    }

    @Test
    @Transactional
    void getNonExistingUserMaster() throws Exception {
        // Get the userMaster
        restUserMasterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingUserMaster() throws Exception {
        // Initialize the database
        userMasterRepository.saveAndFlush(userMaster);

        int databaseSizeBeforeUpdate = userMasterRepository.findAll().size();

        // Update the userMaster
        UserMaster updatedUserMaster = userMasterRepository.findById(userMaster.getUserId()).get();
        // Disconnect from session so that the updates on updatedUserMaster are not directly saved in db
        em.detach(updatedUserMaster);
        updatedUserMaster
            .firstName(UPDATED_FIRST_NAME)
            .middleName(UPDATED_MIDDLE_NAME)
            .lastName(UPDATED_LAST_NAME)
            .dob(UPDATED_DOB)
            .age(UPDATED_AGE)
            .gender(UPDATED_GENDER)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .userMasterUuid(UPDATED_USER_MASTER_UUID)
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
            .jobTitle(UPDATED_JOB_TITLE)
            .modeOfContact(UPDATED_MODE_OF_CONTACT)
            .isDeactivate(UPDATED_IS_DEACTIVATE)
            .username(UPDATED_USERNAME)
            .password(UPDATED_PASSWORD);
        UserMasterDTO userMasterDTO = userMasterMapper.toDto(updatedUserMaster);

        restUserMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, userMasterDTO.getUserId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userMasterDTO))
            )
            .andExpect(status().isOk());

        // Validate the UserMaster in the database
        List<UserMaster> userMasterList = userMasterRepository.findAll();
        assertThat(userMasterList).hasSize(databaseSizeBeforeUpdate);
        UserMaster testUserMaster = userMasterList.get(userMasterList.size() - 1);
        assertThat(testUserMaster.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testUserMaster.getMiddleName()).isEqualTo(UPDATED_MIDDLE_NAME);
        assertThat(testUserMaster.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testUserMaster.getDob()).isEqualTo(UPDATED_DOB);
        assertThat(testUserMaster.getAge()).isEqualTo(UPDATED_AGE);
        assertThat(testUserMaster.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testUserMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testUserMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testUserMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testUserMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testUserMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testUserMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testUserMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testUserMaster.getUserMasterUuid()).isEqualTo(UPDATED_USER_MASTER_UUID);
        assertThat(testUserMaster.getAddressLine1()).isEqualTo(UPDATED_ADDRESS_LINE_1);
        assertThat(testUserMaster.getAddressLine2()).isEqualTo(UPDATED_ADDRESS_LINE_2);
        assertThat(testUserMaster.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testUserMaster.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testUserMaster.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testUserMaster.getZip()).isEqualTo(UPDATED_ZIP);
        assertThat(testUserMaster.getContactNo1()).isEqualTo(UPDATED_CONTACT_NO_1);
        assertThat(testUserMaster.getContactNo2()).isEqualTo(UPDATED_CONTACT_NO_2);
        assertThat(testUserMaster.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testUserMaster.getEfax()).isEqualTo(UPDATED_EFAX);
        assertThat(testUserMaster.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testUserMaster.getJobTitle()).isEqualTo(UPDATED_JOB_TITLE);
        assertThat(testUserMaster.getModeOfContact()).isEqualTo(UPDATED_MODE_OF_CONTACT);
        assertThat(testUserMaster.getIsDeactivate()).isEqualTo(UPDATED_IS_DEACTIVATE);
        assertThat(testUserMaster.getUsername()).isEqualTo(UPDATED_USERNAME);
        assertThat(testUserMaster.getPassword()).isEqualTo(UPDATED_PASSWORD);
    }

    @Test
    @Transactional
    void putNonExistingUserMaster() throws Exception {
        int databaseSizeBeforeUpdate = userMasterRepository.findAll().size();
        userMaster.setUserId(count.incrementAndGet());

        // Create the UserMaster
        UserMasterDTO userMasterDTO = userMasterMapper.toDto(userMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, userMasterDTO.getUserId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserMaster in the database
        List<UserMaster> userMasterList = userMasterRepository.findAll();
        assertThat(userMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchUserMaster() throws Exception {
        int databaseSizeBeforeUpdate = userMasterRepository.findAll().size();
        userMaster.setUserId(count.incrementAndGet());

        // Create the UserMaster
        UserMasterDTO userMasterDTO = userMasterMapper.toDto(userMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserMaster in the database
        List<UserMaster> userMasterList = userMasterRepository.findAll();
        assertThat(userMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamUserMaster() throws Exception {
        int databaseSizeBeforeUpdate = userMasterRepository.findAll().size();
        userMaster.setUserId(count.incrementAndGet());

        // Create the UserMaster
        UserMasterDTO userMasterDTO = userMasterMapper.toDto(userMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserMasterMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the UserMaster in the database
        List<UserMaster> userMasterList = userMasterRepository.findAll();
        assertThat(userMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateUserMasterWithPatch() throws Exception {
        // Initialize the database
        userMasterRepository.saveAndFlush(userMaster);

        int databaseSizeBeforeUpdate = userMasterRepository.findAll().size();

        // Update the userMaster using partial update
        UserMaster partialUpdatedUserMaster = new UserMaster();
        partialUpdatedUserMaster.setUserId(userMaster.getUserId());

        partialUpdatedUserMaster
            .firstName(UPDATED_FIRST_NAME)
            .gender(UPDATED_GENDER)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .userMasterUuid(UPDATED_USER_MASTER_UUID)
            .addressLine1(UPDATED_ADDRESS_LINE_1)
            .addressLine2(UPDATED_ADDRESS_LINE_2)
            .city(UPDATED_CITY)
            .state(UPDATED_STATE)
            .efax(UPDATED_EFAX)
            .email(UPDATED_EMAIL)
            .jobTitle(UPDATED_JOB_TITLE)
            .modeOfContact(UPDATED_MODE_OF_CONTACT)
            .isDeactivate(UPDATED_IS_DEACTIVATE);

        restUserMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUserMaster.getUserId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUserMaster))
            )
            .andExpect(status().isOk());

        // Validate the UserMaster in the database
        List<UserMaster> userMasterList = userMasterRepository.findAll();
        assertThat(userMasterList).hasSize(databaseSizeBeforeUpdate);
        UserMaster testUserMaster = userMasterList.get(userMasterList.size() - 1);
        assertThat(testUserMaster.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testUserMaster.getMiddleName()).isEqualTo(DEFAULT_MIDDLE_NAME);
        assertThat(testUserMaster.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testUserMaster.getDob()).isEqualTo(DEFAULT_DOB);
        assertThat(testUserMaster.getAge()).isEqualTo(DEFAULT_AGE);
        assertThat(testUserMaster.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testUserMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testUserMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testUserMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testUserMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testUserMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testUserMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testUserMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testUserMaster.getUserMasterUuid()).isEqualTo(UPDATED_USER_MASTER_UUID);
        assertThat(testUserMaster.getAddressLine1()).isEqualTo(UPDATED_ADDRESS_LINE_1);
        assertThat(testUserMaster.getAddressLine2()).isEqualTo(UPDATED_ADDRESS_LINE_2);
        assertThat(testUserMaster.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testUserMaster.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testUserMaster.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testUserMaster.getZip()).isEqualTo(DEFAULT_ZIP);
        assertThat(testUserMaster.getContactNo1()).isEqualTo(DEFAULT_CONTACT_NO_1);
        assertThat(testUserMaster.getContactNo2()).isEqualTo(DEFAULT_CONTACT_NO_2);
        assertThat(testUserMaster.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testUserMaster.getEfax()).isEqualTo(UPDATED_EFAX);
        assertThat(testUserMaster.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testUserMaster.getJobTitle()).isEqualTo(UPDATED_JOB_TITLE);
        assertThat(testUserMaster.getModeOfContact()).isEqualTo(UPDATED_MODE_OF_CONTACT);
        assertThat(testUserMaster.getIsDeactivate()).isEqualTo(UPDATED_IS_DEACTIVATE);
        assertThat(testUserMaster.getUsername()).isEqualTo(DEFAULT_USERNAME);
        assertThat(testUserMaster.getPassword()).isEqualTo(DEFAULT_PASSWORD);
    }

    @Test
    @Transactional
    void fullUpdateUserMasterWithPatch() throws Exception {
        // Initialize the database
        userMasterRepository.saveAndFlush(userMaster);

        int databaseSizeBeforeUpdate = userMasterRepository.findAll().size();

        // Update the userMaster using partial update
        UserMaster partialUpdatedUserMaster = new UserMaster();
        partialUpdatedUserMaster.setUserId(userMaster.getUserId());

        partialUpdatedUserMaster
            .firstName(UPDATED_FIRST_NAME)
            .middleName(UPDATED_MIDDLE_NAME)
            .lastName(UPDATED_LAST_NAME)
            .dob(UPDATED_DOB)
            .age(UPDATED_AGE)
            .gender(UPDATED_GENDER)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .userMasterUuid(UPDATED_USER_MASTER_UUID)
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
            .jobTitle(UPDATED_JOB_TITLE)
            .modeOfContact(UPDATED_MODE_OF_CONTACT)
            .isDeactivate(UPDATED_IS_DEACTIVATE)
            .username(UPDATED_USERNAME)
            .password(UPDATED_PASSWORD);

        restUserMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUserMaster.getUserId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUserMaster))
            )
            .andExpect(status().isOk());

        // Validate the UserMaster in the database
        List<UserMaster> userMasterList = userMasterRepository.findAll();
        assertThat(userMasterList).hasSize(databaseSizeBeforeUpdate);
        UserMaster testUserMaster = userMasterList.get(userMasterList.size() - 1);
        assertThat(testUserMaster.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testUserMaster.getMiddleName()).isEqualTo(UPDATED_MIDDLE_NAME);
        assertThat(testUserMaster.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testUserMaster.getDob()).isEqualTo(UPDATED_DOB);
        assertThat(testUserMaster.getAge()).isEqualTo(UPDATED_AGE);
        assertThat(testUserMaster.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testUserMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testUserMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testUserMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testUserMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testUserMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testUserMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testUserMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testUserMaster.getUserMasterUuid()).isEqualTo(UPDATED_USER_MASTER_UUID);
        assertThat(testUserMaster.getAddressLine1()).isEqualTo(UPDATED_ADDRESS_LINE_1);
        assertThat(testUserMaster.getAddressLine2()).isEqualTo(UPDATED_ADDRESS_LINE_2);
        assertThat(testUserMaster.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testUserMaster.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testUserMaster.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testUserMaster.getZip()).isEqualTo(UPDATED_ZIP);
        assertThat(testUserMaster.getContactNo1()).isEqualTo(UPDATED_CONTACT_NO_1);
        assertThat(testUserMaster.getContactNo2()).isEqualTo(UPDATED_CONTACT_NO_2);
        assertThat(testUserMaster.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testUserMaster.getEfax()).isEqualTo(UPDATED_EFAX);
        assertThat(testUserMaster.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testUserMaster.getJobTitle()).isEqualTo(UPDATED_JOB_TITLE);
        assertThat(testUserMaster.getModeOfContact()).isEqualTo(UPDATED_MODE_OF_CONTACT);
        assertThat(testUserMaster.getIsDeactivate()).isEqualTo(UPDATED_IS_DEACTIVATE);
        assertThat(testUserMaster.getUsername()).isEqualTo(UPDATED_USERNAME);
        assertThat(testUserMaster.getPassword()).isEqualTo(UPDATED_PASSWORD);
    }

    @Test
    @Transactional
    void patchNonExistingUserMaster() throws Exception {
        int databaseSizeBeforeUpdate = userMasterRepository.findAll().size();
        userMaster.setUserId(count.incrementAndGet());

        // Create the UserMaster
        UserMasterDTO userMasterDTO = userMasterMapper.toDto(userMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, userMasterDTO.getUserId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(userMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserMaster in the database
        List<UserMaster> userMasterList = userMasterRepository.findAll();
        assertThat(userMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchUserMaster() throws Exception {
        int databaseSizeBeforeUpdate = userMasterRepository.findAll().size();
        userMaster.setUserId(count.incrementAndGet());

        // Create the UserMaster
        UserMasterDTO userMasterDTO = userMasterMapper.toDto(userMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(userMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserMaster in the database
        List<UserMaster> userMasterList = userMasterRepository.findAll();
        assertThat(userMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamUserMaster() throws Exception {
        int databaseSizeBeforeUpdate = userMasterRepository.findAll().size();
        userMaster.setUserId(count.incrementAndGet());

        // Create the UserMaster
        UserMasterDTO userMasterDTO = userMasterMapper.toDto(userMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserMasterMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(userMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the UserMaster in the database
        List<UserMaster> userMasterList = userMasterRepository.findAll();
        assertThat(userMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteUserMaster() throws Exception {
        // Initialize the database
        userMasterRepository.saveAndFlush(userMaster);

        int databaseSizeBeforeDelete = userMasterRepository.findAll().size();

        // Delete the userMaster
        restUserMasterMockMvc
            .perform(delete(ENTITY_API_URL_ID, userMaster.getUserId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UserMaster> userMasterList = userMasterRepository.findAll();
        assertThat(userMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
