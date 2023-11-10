package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.SalesRep;
import com.sunknowledge.dme.rcm.repository.SalesRepRepository;
import com.sunknowledge.dme.rcm.service.dto.SalesRepDTO;
import com.sunknowledge.dme.rcm.service.mapper.SalesRepMapper;
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
 * Integration tests for the {@link SalesRepResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SalesRepResourceIT {

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SUFFIX = "AAAAAAAAAA";
    private static final String UPDATED_SUFFIX = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final UUID DEFAULT_SALES_REP_UUID = UUID.randomUUID();
    private static final UUID UPDATED_SALES_REP_UUID = UUID.randomUUID();

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

    private static final String DEFAULT_SALES_REP_NO = "AAAAAAAAAA";
    private static final String UPDATED_SALES_REP_NO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/sales-reps";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{salesRepId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SalesRepRepository salesRepRepository;

    @Autowired
    private SalesRepMapper salesRepMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSalesRepMockMvc;

    private SalesRep salesRep;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesRep createEntity(EntityManager em) {
        SalesRep salesRep = new SalesRep()
            .firstName(DEFAULT_FIRST_NAME)
            .middleName(DEFAULT_MIDDLE_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .suffix(DEFAULT_SUFFIX)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .salesRepUuid(DEFAULT_SALES_REP_UUID)
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
            .salesRepNo(DEFAULT_SALES_REP_NO);
        return salesRep;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesRep createUpdatedEntity(EntityManager em) {
        SalesRep salesRep = new SalesRep()
            .firstName(UPDATED_FIRST_NAME)
            .middleName(UPDATED_MIDDLE_NAME)
            .lastName(UPDATED_LAST_NAME)
            .suffix(UPDATED_SUFFIX)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .salesRepUuid(UPDATED_SALES_REP_UUID)
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
            .salesRepNo(UPDATED_SALES_REP_NO);
        return salesRep;
    }

    @BeforeEach
    public void initTest() {
        salesRep = createEntity(em);
    }

    @Test
    @Transactional
    void createSalesRep() throws Exception {
        int databaseSizeBeforeCreate = salesRepRepository.findAll().size();
        // Create the SalesRep
        SalesRepDTO salesRepDTO = salesRepMapper.toDto(salesRep);
        restSalesRepMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(salesRepDTO))
            )
            .andExpect(status().isCreated());

        // Validate the SalesRep in the database
        List<SalesRep> salesRepList = salesRepRepository.findAll();
        assertThat(salesRepList).hasSize(databaseSizeBeforeCreate + 1);
        SalesRep testSalesRep = salesRepList.get(salesRepList.size() - 1);
        assertThat(testSalesRep.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testSalesRep.getMiddleName()).isEqualTo(DEFAULT_MIDDLE_NAME);
        assertThat(testSalesRep.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testSalesRep.getSuffix()).isEqualTo(DEFAULT_SUFFIX);
        assertThat(testSalesRep.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSalesRep.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testSalesRep.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testSalesRep.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testSalesRep.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testSalesRep.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testSalesRep.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testSalesRep.getSalesRepUuid()).isEqualTo(DEFAULT_SALES_REP_UUID);
        assertThat(testSalesRep.getAddressLine1()).isEqualTo(DEFAULT_ADDRESS_LINE_1);
        assertThat(testSalesRep.getAddressLine2()).isEqualTo(DEFAULT_ADDRESS_LINE_2);
        assertThat(testSalesRep.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testSalesRep.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testSalesRep.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testSalesRep.getZip()).isEqualTo(DEFAULT_ZIP);
        assertThat(testSalesRep.getContactNo1()).isEqualTo(DEFAULT_CONTACT_NO_1);
        assertThat(testSalesRep.getContactNo2()).isEqualTo(DEFAULT_CONTACT_NO_2);
        assertThat(testSalesRep.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testSalesRep.getEfax()).isEqualTo(DEFAULT_EFAX);
        assertThat(testSalesRep.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testSalesRep.getRelationship()).isEqualTo(DEFAULT_RELATIONSHIP);
        assertThat(testSalesRep.getModeOfContact()).isEqualTo(DEFAULT_MODE_OF_CONTACT);
        assertThat(testSalesRep.getSalesRepNo()).isEqualTo(DEFAULT_SALES_REP_NO);
    }

    @Test
    @Transactional
    void createSalesRepWithExistingId() throws Exception {
        // Create the SalesRep with an existing ID
        salesRep.setSalesRepId(1L);
        SalesRepDTO salesRepDTO = salesRepMapper.toDto(salesRep);

        int databaseSizeBeforeCreate = salesRepRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSalesRepMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(salesRepDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SalesRep in the database
        List<SalesRep> salesRepList = salesRepRepository.findAll();
        assertThat(salesRepList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSalesReps() throws Exception {
        // Initialize the database
        salesRepRepository.saveAndFlush(salesRep);

        // Get all the salesRepList
        restSalesRepMockMvc
            .perform(get(ENTITY_API_URL + "?sort=salesRepId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].salesRepId").value(hasItem(salesRep.getSalesRepId().intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].middleName").value(hasItem(DEFAULT_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].suffix").value(hasItem(DEFAULT_SUFFIX)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].salesRepUuid").value(hasItem(DEFAULT_SALES_REP_UUID.toString())))
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
            .andExpect(jsonPath("$.[*].salesRepNo").value(hasItem(DEFAULT_SALES_REP_NO)));
    }

    @Test
    @Transactional
    void getSalesRep() throws Exception {
        // Initialize the database
        salesRepRepository.saveAndFlush(salesRep);

        // Get the salesRep
        restSalesRepMockMvc
            .perform(get(ENTITY_API_URL_ID, salesRep.getSalesRepId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.salesRepId").value(salesRep.getSalesRepId().intValue()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.middleName").value(DEFAULT_MIDDLE_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.suffix").value(DEFAULT_SUFFIX))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.salesRepUuid").value(DEFAULT_SALES_REP_UUID.toString()))
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
            .andExpect(jsonPath("$.salesRepNo").value(DEFAULT_SALES_REP_NO));
    }

    @Test
    @Transactional
    void getNonExistingSalesRep() throws Exception {
        // Get the salesRep
        restSalesRepMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewSalesRep() throws Exception {
        // Initialize the database
        salesRepRepository.saveAndFlush(salesRep);

        int databaseSizeBeforeUpdate = salesRepRepository.findAll().size();

        // Update the salesRep
        SalesRep updatedSalesRep = salesRepRepository.findById(salesRep.getSalesRepId()).get();
        // Disconnect from session so that the updates on updatedSalesRep are not directly saved in db
        em.detach(updatedSalesRep);
        updatedSalesRep
            .firstName(UPDATED_FIRST_NAME)
            .middleName(UPDATED_MIDDLE_NAME)
            .lastName(UPDATED_LAST_NAME)
            .suffix(UPDATED_SUFFIX)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .salesRepUuid(UPDATED_SALES_REP_UUID)
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
            .salesRepNo(UPDATED_SALES_REP_NO);
        SalesRepDTO salesRepDTO = salesRepMapper.toDto(updatedSalesRep);

        restSalesRepMockMvc
            .perform(
                put(ENTITY_API_URL_ID, salesRepDTO.getSalesRepId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(salesRepDTO))
            )
            .andExpect(status().isOk());

        // Validate the SalesRep in the database
        List<SalesRep> salesRepList = salesRepRepository.findAll();
        assertThat(salesRepList).hasSize(databaseSizeBeforeUpdate);
        SalesRep testSalesRep = salesRepList.get(salesRepList.size() - 1);
        assertThat(testSalesRep.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testSalesRep.getMiddleName()).isEqualTo(UPDATED_MIDDLE_NAME);
        assertThat(testSalesRep.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testSalesRep.getSuffix()).isEqualTo(UPDATED_SUFFIX);
        assertThat(testSalesRep.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSalesRep.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testSalesRep.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSalesRep.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testSalesRep.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testSalesRep.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testSalesRep.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testSalesRep.getSalesRepUuid()).isEqualTo(UPDATED_SALES_REP_UUID);
        assertThat(testSalesRep.getAddressLine1()).isEqualTo(UPDATED_ADDRESS_LINE_1);
        assertThat(testSalesRep.getAddressLine2()).isEqualTo(UPDATED_ADDRESS_LINE_2);
        assertThat(testSalesRep.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testSalesRep.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testSalesRep.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testSalesRep.getZip()).isEqualTo(UPDATED_ZIP);
        assertThat(testSalesRep.getContactNo1()).isEqualTo(UPDATED_CONTACT_NO_1);
        assertThat(testSalesRep.getContactNo2()).isEqualTo(UPDATED_CONTACT_NO_2);
        assertThat(testSalesRep.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testSalesRep.getEfax()).isEqualTo(UPDATED_EFAX);
        assertThat(testSalesRep.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testSalesRep.getRelationship()).isEqualTo(UPDATED_RELATIONSHIP);
        assertThat(testSalesRep.getModeOfContact()).isEqualTo(UPDATED_MODE_OF_CONTACT);
        assertThat(testSalesRep.getSalesRepNo()).isEqualTo(UPDATED_SALES_REP_NO);
    }

    @Test
    @Transactional
    void putNonExistingSalesRep() throws Exception {
        int databaseSizeBeforeUpdate = salesRepRepository.findAll().size();
        salesRep.setSalesRepId(count.incrementAndGet());

        // Create the SalesRep
        SalesRepDTO salesRepDTO = salesRepMapper.toDto(salesRep);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSalesRepMockMvc
            .perform(
                put(ENTITY_API_URL_ID, salesRepDTO.getSalesRepId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(salesRepDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SalesRep in the database
        List<SalesRep> salesRepList = salesRepRepository.findAll();
        assertThat(salesRepList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSalesRep() throws Exception {
        int databaseSizeBeforeUpdate = salesRepRepository.findAll().size();
        salesRep.setSalesRepId(count.incrementAndGet());

        // Create the SalesRep
        SalesRepDTO salesRepDTO = salesRepMapper.toDto(salesRep);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSalesRepMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(salesRepDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SalesRep in the database
        List<SalesRep> salesRepList = salesRepRepository.findAll();
        assertThat(salesRepList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSalesRep() throws Exception {
        int databaseSizeBeforeUpdate = salesRepRepository.findAll().size();
        salesRep.setSalesRepId(count.incrementAndGet());

        // Create the SalesRep
        SalesRepDTO salesRepDTO = salesRepMapper.toDto(salesRep);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSalesRepMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(salesRepDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SalesRep in the database
        List<SalesRep> salesRepList = salesRepRepository.findAll();
        assertThat(salesRepList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSalesRepWithPatch() throws Exception {
        // Initialize the database
        salesRepRepository.saveAndFlush(salesRep);

        int databaseSizeBeforeUpdate = salesRepRepository.findAll().size();

        // Update the salesRep using partial update
        SalesRep partialUpdatedSalesRep = new SalesRep();
        partialUpdatedSalesRep.setSalesRepId(salesRep.getSalesRepId());

        partialUpdatedSalesRep
            .firstName(UPDATED_FIRST_NAME)
            .middleName(UPDATED_MIDDLE_NAME)
            .suffix(UPDATED_SUFFIX)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .salesRepUuid(UPDATED_SALES_REP_UUID)
            .addressLine1(UPDATED_ADDRESS_LINE_1)
            .addressLine2(UPDATED_ADDRESS_LINE_2)
            .city(UPDATED_CITY)
            .state(UPDATED_STATE)
            .country(UPDATED_COUNTRY)
            .contactNo2(UPDATED_CONTACT_NO_2)
            .email(UPDATED_EMAIL)
            .relationship(UPDATED_RELATIONSHIP)
            .modeOfContact(UPDATED_MODE_OF_CONTACT)
            .salesRepNo(UPDATED_SALES_REP_NO);

        restSalesRepMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSalesRep.getSalesRepId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSalesRep))
            )
            .andExpect(status().isOk());

        // Validate the SalesRep in the database
        List<SalesRep> salesRepList = salesRepRepository.findAll();
        assertThat(salesRepList).hasSize(databaseSizeBeforeUpdate);
        SalesRep testSalesRep = salesRepList.get(salesRepList.size() - 1);
        assertThat(testSalesRep.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testSalesRep.getMiddleName()).isEqualTo(UPDATED_MIDDLE_NAME);
        assertThat(testSalesRep.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testSalesRep.getSuffix()).isEqualTo(UPDATED_SUFFIX);
        assertThat(testSalesRep.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSalesRep.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testSalesRep.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSalesRep.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testSalesRep.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testSalesRep.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testSalesRep.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testSalesRep.getSalesRepUuid()).isEqualTo(UPDATED_SALES_REP_UUID);
        assertThat(testSalesRep.getAddressLine1()).isEqualTo(UPDATED_ADDRESS_LINE_1);
        assertThat(testSalesRep.getAddressLine2()).isEqualTo(UPDATED_ADDRESS_LINE_2);
        assertThat(testSalesRep.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testSalesRep.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testSalesRep.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testSalesRep.getZip()).isEqualTo(DEFAULT_ZIP);
        assertThat(testSalesRep.getContactNo1()).isEqualTo(DEFAULT_CONTACT_NO_1);
        assertThat(testSalesRep.getContactNo2()).isEqualTo(UPDATED_CONTACT_NO_2);
        assertThat(testSalesRep.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testSalesRep.getEfax()).isEqualTo(DEFAULT_EFAX);
        assertThat(testSalesRep.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testSalesRep.getRelationship()).isEqualTo(UPDATED_RELATIONSHIP);
        assertThat(testSalesRep.getModeOfContact()).isEqualTo(UPDATED_MODE_OF_CONTACT);
        assertThat(testSalesRep.getSalesRepNo()).isEqualTo(UPDATED_SALES_REP_NO);
    }

    @Test
    @Transactional
    void fullUpdateSalesRepWithPatch() throws Exception {
        // Initialize the database
        salesRepRepository.saveAndFlush(salesRep);

        int databaseSizeBeforeUpdate = salesRepRepository.findAll().size();

        // Update the salesRep using partial update
        SalesRep partialUpdatedSalesRep = new SalesRep();
        partialUpdatedSalesRep.setSalesRepId(salesRep.getSalesRepId());

        partialUpdatedSalesRep
            .firstName(UPDATED_FIRST_NAME)
            .middleName(UPDATED_MIDDLE_NAME)
            .lastName(UPDATED_LAST_NAME)
            .suffix(UPDATED_SUFFIX)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .salesRepUuid(UPDATED_SALES_REP_UUID)
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
            .salesRepNo(UPDATED_SALES_REP_NO);

        restSalesRepMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSalesRep.getSalesRepId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSalesRep))
            )
            .andExpect(status().isOk());

        // Validate the SalesRep in the database
        List<SalesRep> salesRepList = salesRepRepository.findAll();
        assertThat(salesRepList).hasSize(databaseSizeBeforeUpdate);
        SalesRep testSalesRep = salesRepList.get(salesRepList.size() - 1);
        assertThat(testSalesRep.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testSalesRep.getMiddleName()).isEqualTo(UPDATED_MIDDLE_NAME);
        assertThat(testSalesRep.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testSalesRep.getSuffix()).isEqualTo(UPDATED_SUFFIX);
        assertThat(testSalesRep.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSalesRep.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testSalesRep.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSalesRep.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testSalesRep.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testSalesRep.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testSalesRep.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testSalesRep.getSalesRepUuid()).isEqualTo(UPDATED_SALES_REP_UUID);
        assertThat(testSalesRep.getAddressLine1()).isEqualTo(UPDATED_ADDRESS_LINE_1);
        assertThat(testSalesRep.getAddressLine2()).isEqualTo(UPDATED_ADDRESS_LINE_2);
        assertThat(testSalesRep.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testSalesRep.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testSalesRep.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testSalesRep.getZip()).isEqualTo(UPDATED_ZIP);
        assertThat(testSalesRep.getContactNo1()).isEqualTo(UPDATED_CONTACT_NO_1);
        assertThat(testSalesRep.getContactNo2()).isEqualTo(UPDATED_CONTACT_NO_2);
        assertThat(testSalesRep.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testSalesRep.getEfax()).isEqualTo(UPDATED_EFAX);
        assertThat(testSalesRep.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testSalesRep.getRelationship()).isEqualTo(UPDATED_RELATIONSHIP);
        assertThat(testSalesRep.getModeOfContact()).isEqualTo(UPDATED_MODE_OF_CONTACT);
        assertThat(testSalesRep.getSalesRepNo()).isEqualTo(UPDATED_SALES_REP_NO);
    }

    @Test
    @Transactional
    void patchNonExistingSalesRep() throws Exception {
        int databaseSizeBeforeUpdate = salesRepRepository.findAll().size();
        salesRep.setSalesRepId(count.incrementAndGet());

        // Create the SalesRep
        SalesRepDTO salesRepDTO = salesRepMapper.toDto(salesRep);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSalesRepMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, salesRepDTO.getSalesRepId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(salesRepDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SalesRep in the database
        List<SalesRep> salesRepList = salesRepRepository.findAll();
        assertThat(salesRepList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSalesRep() throws Exception {
        int databaseSizeBeforeUpdate = salesRepRepository.findAll().size();
        salesRep.setSalesRepId(count.incrementAndGet());

        // Create the SalesRep
        SalesRepDTO salesRepDTO = salesRepMapper.toDto(salesRep);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSalesRepMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(salesRepDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SalesRep in the database
        List<SalesRep> salesRepList = salesRepRepository.findAll();
        assertThat(salesRepList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSalesRep() throws Exception {
        int databaseSizeBeforeUpdate = salesRepRepository.findAll().size();
        salesRep.setSalesRepId(count.incrementAndGet());

        // Create the SalesRep
        SalesRepDTO salesRepDTO = salesRepMapper.toDto(salesRep);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSalesRepMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(salesRepDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SalesRep in the database
        List<SalesRep> salesRepList = salesRepRepository.findAll();
        assertThat(salesRepList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSalesRep() throws Exception {
        // Initialize the database
        salesRepRepository.saveAndFlush(salesRep);

        int databaseSizeBeforeDelete = salesRepRepository.findAll().size();

        // Delete the salesRep
        restSalesRepMockMvc
            .perform(delete(ENTITY_API_URL_ID, salesRep.getSalesRepId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SalesRep> salesRepList = salesRepRepository.findAll();
        assertThat(salesRepList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
