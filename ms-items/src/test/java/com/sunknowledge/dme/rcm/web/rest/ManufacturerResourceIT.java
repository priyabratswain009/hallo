package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.Manufacturer;
import com.sunknowledge.dme.rcm.repository.ManufacturerRepository;
import com.sunknowledge.dme.rcm.service.dto.ManufacturerDTO;
import com.sunknowledge.dme.rcm.service.mapper.ManufacturerMapper;
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
 * Integration tests for the {@link ManufacturerResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ManufacturerResourceIT {

    private static final String DEFAULT_MANUFACTURER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MANUFACTURER_NAME = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_MANUFACTURER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_MANUFACTURER_UUID = UUID.randomUUID();

    private static final String DEFAULT_CONTACT_PERSON_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_PERSON_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_WEB_URL = "AAAAAAAAAA";
    private static final String UPDATED_WEB_URL = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_ZIP = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_NO_1 = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_NO_1 = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_NO_2 = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_NO_2 = "BBBBBBBBBB";

    private static final String DEFAULT_EFAX = "AAAAAAAAAA";
    private static final String UPDATED_EFAX = "BBBBBBBBBB";

    private static final String DEFAULT_MANUFACTURER_NO = "AAAAAAAAAA";
    private static final String UPDATED_MANUFACTURER_NO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/manufacturers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{manufacturerId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Autowired
    private ManufacturerMapper manufacturerMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restManufacturerMockMvc;

    private Manufacturer manufacturer;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Manufacturer createEntity(EntityManager em) {
        Manufacturer manufacturer = new Manufacturer()
            .manufacturerName(DEFAULT_MANUFACTURER_NAME)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .manufacturerUuid(DEFAULT_MANUFACTURER_UUID)
            .contactPersonName(DEFAULT_CONTACT_PERSON_NAME)
            .webUrl(DEFAULT_WEB_URL)
            .addressLine1(DEFAULT_ADDRESS_LINE_1)
            .addressLine2(DEFAULT_ADDRESS_LINE_2)
            .city(DEFAULT_CITY)
            .state(DEFAULT_STATE)
            .zip(DEFAULT_ZIP)
            .email(DEFAULT_EMAIL)
            .fax(DEFAULT_FAX)
            .contactNo1(DEFAULT_CONTACT_NO_1)
            .contactNo2(DEFAULT_CONTACT_NO_2)
            .efax(DEFAULT_EFAX)
            .manufacturerNo(DEFAULT_MANUFACTURER_NO);
        return manufacturer;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Manufacturer createUpdatedEntity(EntityManager em) {
        Manufacturer manufacturer = new Manufacturer()
            .manufacturerName(UPDATED_MANUFACTURER_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .manufacturerUuid(UPDATED_MANUFACTURER_UUID)
            .contactPersonName(UPDATED_CONTACT_PERSON_NAME)
            .webUrl(UPDATED_WEB_URL)
            .addressLine1(UPDATED_ADDRESS_LINE_1)
            .addressLine2(UPDATED_ADDRESS_LINE_2)
            .city(UPDATED_CITY)
            .state(UPDATED_STATE)
            .zip(UPDATED_ZIP)
            .email(UPDATED_EMAIL)
            .fax(UPDATED_FAX)
            .contactNo1(UPDATED_CONTACT_NO_1)
            .contactNo2(UPDATED_CONTACT_NO_2)
            .efax(UPDATED_EFAX)
            .manufacturerNo(UPDATED_MANUFACTURER_NO);
        return manufacturer;
    }

    @BeforeEach
    public void initTest() {
        manufacturer = createEntity(em);
    }

    @Test
    @Transactional
    void createManufacturer() throws Exception {
        int databaseSizeBeforeCreate = manufacturerRepository.findAll().size();
        // Create the Manufacturer
        ManufacturerDTO manufacturerDTO = manufacturerMapper.toDto(manufacturer);
        restManufacturerMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(manufacturerDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Manufacturer in the database
        List<Manufacturer> manufacturerList = manufacturerRepository.findAll();
        assertThat(manufacturerList).hasSize(databaseSizeBeforeCreate + 1);
        Manufacturer testManufacturer = manufacturerList.get(manufacturerList.size() - 1);
        assertThat(testManufacturer.getManufacturerName()).isEqualTo(DEFAULT_MANUFACTURER_NAME);
        assertThat(testManufacturer.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testManufacturer.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testManufacturer.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testManufacturer.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testManufacturer.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testManufacturer.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testManufacturer.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testManufacturer.getManufacturerUuid()).isEqualTo(DEFAULT_MANUFACTURER_UUID);
        assertThat(testManufacturer.getContactPersonName()).isEqualTo(DEFAULT_CONTACT_PERSON_NAME);
        assertThat(testManufacturer.getWebUrl()).isEqualTo(DEFAULT_WEB_URL);
        assertThat(testManufacturer.getAddressLine1()).isEqualTo(DEFAULT_ADDRESS_LINE_1);
        assertThat(testManufacturer.getAddressLine2()).isEqualTo(DEFAULT_ADDRESS_LINE_2);
        assertThat(testManufacturer.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testManufacturer.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testManufacturer.getZip()).isEqualTo(DEFAULT_ZIP);
        assertThat(testManufacturer.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testManufacturer.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testManufacturer.getContactNo1()).isEqualTo(DEFAULT_CONTACT_NO_1);
        assertThat(testManufacturer.getContactNo2()).isEqualTo(DEFAULT_CONTACT_NO_2);
        assertThat(testManufacturer.getEfax()).isEqualTo(DEFAULT_EFAX);
        assertThat(testManufacturer.getManufacturerNo()).isEqualTo(DEFAULT_MANUFACTURER_NO);
    }

    @Test
    @Transactional
    void createManufacturerWithExistingId() throws Exception {
        // Create the Manufacturer with an existing ID
        manufacturer.setManufacturerId(1L);
        ManufacturerDTO manufacturerDTO = manufacturerMapper.toDto(manufacturer);

        int databaseSizeBeforeCreate = manufacturerRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restManufacturerMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(manufacturerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Manufacturer in the database
        List<Manufacturer> manufacturerList = manufacturerRepository.findAll();
        assertThat(manufacturerList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllManufacturers() throws Exception {
        // Initialize the database
        manufacturerRepository.saveAndFlush(manufacturer);

        // Get all the manufacturerList
        restManufacturerMockMvc
            .perform(get(ENTITY_API_URL + "?sort=manufacturerId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].manufacturerId").value(hasItem(manufacturer.getManufacturerId().intValue())))
            .andExpect(jsonPath("$.[*].manufacturerName").value(hasItem(DEFAULT_MANUFACTURER_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].manufacturerUuid").value(hasItem(DEFAULT_MANUFACTURER_UUID.toString())))
            .andExpect(jsonPath("$.[*].contactPersonName").value(hasItem(DEFAULT_CONTACT_PERSON_NAME)))
            .andExpect(jsonPath("$.[*].webUrl").value(hasItem(DEFAULT_WEB_URL)))
            .andExpect(jsonPath("$.[*].addressLine1").value(hasItem(DEFAULT_ADDRESS_LINE_1)))
            .andExpect(jsonPath("$.[*].addressLine2").value(hasItem(DEFAULT_ADDRESS_LINE_2)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE)))
            .andExpect(jsonPath("$.[*].zip").value(hasItem(DEFAULT_ZIP)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX)))
            .andExpect(jsonPath("$.[*].contactNo1").value(hasItem(DEFAULT_CONTACT_NO_1)))
            .andExpect(jsonPath("$.[*].contactNo2").value(hasItem(DEFAULT_CONTACT_NO_2)))
            .andExpect(jsonPath("$.[*].efax").value(hasItem(DEFAULT_EFAX)))
            .andExpect(jsonPath("$.[*].manufacturerNo").value(hasItem(DEFAULT_MANUFACTURER_NO)));
    }

    @Test
    @Transactional
    void getManufacturer() throws Exception {
        // Initialize the database
        manufacturerRepository.saveAndFlush(manufacturer);

        // Get the manufacturer
        restManufacturerMockMvc
            .perform(get(ENTITY_API_URL_ID, manufacturer.getManufacturerId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.manufacturerId").value(manufacturer.getManufacturerId().intValue()))
            .andExpect(jsonPath("$.manufacturerName").value(DEFAULT_MANUFACTURER_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.manufacturerUuid").value(DEFAULT_MANUFACTURER_UUID.toString()))
            .andExpect(jsonPath("$.contactPersonName").value(DEFAULT_CONTACT_PERSON_NAME))
            .andExpect(jsonPath("$.webUrl").value(DEFAULT_WEB_URL))
            .andExpect(jsonPath("$.addressLine1").value(DEFAULT_ADDRESS_LINE_1))
            .andExpect(jsonPath("$.addressLine2").value(DEFAULT_ADDRESS_LINE_2))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE))
            .andExpect(jsonPath("$.zip").value(DEFAULT_ZIP))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.fax").value(DEFAULT_FAX))
            .andExpect(jsonPath("$.contactNo1").value(DEFAULT_CONTACT_NO_1))
            .andExpect(jsonPath("$.contactNo2").value(DEFAULT_CONTACT_NO_2))
            .andExpect(jsonPath("$.efax").value(DEFAULT_EFAX))
            .andExpect(jsonPath("$.manufacturerNo").value(DEFAULT_MANUFACTURER_NO));
    }

    @Test
    @Transactional
    void getNonExistingManufacturer() throws Exception {
        // Get the manufacturer
        restManufacturerMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewManufacturer() throws Exception {
        // Initialize the database
        manufacturerRepository.saveAndFlush(manufacturer);

        int databaseSizeBeforeUpdate = manufacturerRepository.findAll().size();

        // Update the manufacturer
        Manufacturer updatedManufacturer = manufacturerRepository.findById(manufacturer.getManufacturerId()).get();
        // Disconnect from session so that the updates on updatedManufacturer are not directly saved in db
        em.detach(updatedManufacturer);
        updatedManufacturer
            .manufacturerName(UPDATED_MANUFACTURER_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .manufacturerUuid(UPDATED_MANUFACTURER_UUID)
            .contactPersonName(UPDATED_CONTACT_PERSON_NAME)
            .webUrl(UPDATED_WEB_URL)
            .addressLine1(UPDATED_ADDRESS_LINE_1)
            .addressLine2(UPDATED_ADDRESS_LINE_2)
            .city(UPDATED_CITY)
            .state(UPDATED_STATE)
            .zip(UPDATED_ZIP)
            .email(UPDATED_EMAIL)
            .fax(UPDATED_FAX)
            .contactNo1(UPDATED_CONTACT_NO_1)
            .contactNo2(UPDATED_CONTACT_NO_2)
            .efax(UPDATED_EFAX)
            .manufacturerNo(UPDATED_MANUFACTURER_NO);
        ManufacturerDTO manufacturerDTO = manufacturerMapper.toDto(updatedManufacturer);

        restManufacturerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, manufacturerDTO.getManufacturerId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(manufacturerDTO))
            )
            .andExpect(status().isOk());

        // Validate the Manufacturer in the database
        List<Manufacturer> manufacturerList = manufacturerRepository.findAll();
        assertThat(manufacturerList).hasSize(databaseSizeBeforeUpdate);
        Manufacturer testManufacturer = manufacturerList.get(manufacturerList.size() - 1);
        assertThat(testManufacturer.getManufacturerName()).isEqualTo(UPDATED_MANUFACTURER_NAME);
        assertThat(testManufacturer.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testManufacturer.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testManufacturer.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testManufacturer.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testManufacturer.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testManufacturer.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testManufacturer.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testManufacturer.getManufacturerUuid()).isEqualTo(UPDATED_MANUFACTURER_UUID);
        assertThat(testManufacturer.getContactPersonName()).isEqualTo(UPDATED_CONTACT_PERSON_NAME);
        assertThat(testManufacturer.getWebUrl()).isEqualTo(UPDATED_WEB_URL);
        assertThat(testManufacturer.getAddressLine1()).isEqualTo(UPDATED_ADDRESS_LINE_1);
        assertThat(testManufacturer.getAddressLine2()).isEqualTo(UPDATED_ADDRESS_LINE_2);
        assertThat(testManufacturer.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testManufacturer.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testManufacturer.getZip()).isEqualTo(UPDATED_ZIP);
        assertThat(testManufacturer.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testManufacturer.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testManufacturer.getContactNo1()).isEqualTo(UPDATED_CONTACT_NO_1);
        assertThat(testManufacturer.getContactNo2()).isEqualTo(UPDATED_CONTACT_NO_2);
        assertThat(testManufacturer.getEfax()).isEqualTo(UPDATED_EFAX);
        assertThat(testManufacturer.getManufacturerNo()).isEqualTo(UPDATED_MANUFACTURER_NO);
    }

    @Test
    @Transactional
    void putNonExistingManufacturer() throws Exception {
        int databaseSizeBeforeUpdate = manufacturerRepository.findAll().size();
        manufacturer.setManufacturerId(count.incrementAndGet());

        // Create the Manufacturer
        ManufacturerDTO manufacturerDTO = manufacturerMapper.toDto(manufacturer);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restManufacturerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, manufacturerDTO.getManufacturerId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(manufacturerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Manufacturer in the database
        List<Manufacturer> manufacturerList = manufacturerRepository.findAll();
        assertThat(manufacturerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchManufacturer() throws Exception {
        int databaseSizeBeforeUpdate = manufacturerRepository.findAll().size();
        manufacturer.setManufacturerId(count.incrementAndGet());

        // Create the Manufacturer
        ManufacturerDTO manufacturerDTO = manufacturerMapper.toDto(manufacturer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restManufacturerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(manufacturerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Manufacturer in the database
        List<Manufacturer> manufacturerList = manufacturerRepository.findAll();
        assertThat(manufacturerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamManufacturer() throws Exception {
        int databaseSizeBeforeUpdate = manufacturerRepository.findAll().size();
        manufacturer.setManufacturerId(count.incrementAndGet());

        // Create the Manufacturer
        ManufacturerDTO manufacturerDTO = manufacturerMapper.toDto(manufacturer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restManufacturerMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(manufacturerDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Manufacturer in the database
        List<Manufacturer> manufacturerList = manufacturerRepository.findAll();
        assertThat(manufacturerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateManufacturerWithPatch() throws Exception {
        // Initialize the database
        manufacturerRepository.saveAndFlush(manufacturer);

        int databaseSizeBeforeUpdate = manufacturerRepository.findAll().size();

        // Update the manufacturer using partial update
        Manufacturer partialUpdatedManufacturer = new Manufacturer();
        partialUpdatedManufacturer.setManufacturerId(manufacturer.getManufacturerId());

        partialUpdatedManufacturer
            .manufacturerName(UPDATED_MANUFACTURER_NAME)
            .status(UPDATED_STATUS)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .addressLine1(UPDATED_ADDRESS_LINE_1)
            .city(UPDATED_CITY)
            .email(UPDATED_EMAIL)
            .fax(UPDATED_FAX)
            .contactNo1(UPDATED_CONTACT_NO_1)
            .efax(UPDATED_EFAX);

        restManufacturerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedManufacturer.getManufacturerId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedManufacturer))
            )
            .andExpect(status().isOk());

        // Validate the Manufacturer in the database
        List<Manufacturer> manufacturerList = manufacturerRepository.findAll();
        assertThat(manufacturerList).hasSize(databaseSizeBeforeUpdate);
        Manufacturer testManufacturer = manufacturerList.get(manufacturerList.size() - 1);
        assertThat(testManufacturer.getManufacturerName()).isEqualTo(UPDATED_MANUFACTURER_NAME);
        assertThat(testManufacturer.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testManufacturer.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testManufacturer.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testManufacturer.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testManufacturer.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testManufacturer.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testManufacturer.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testManufacturer.getManufacturerUuid()).isEqualTo(DEFAULT_MANUFACTURER_UUID);
        assertThat(testManufacturer.getContactPersonName()).isEqualTo(DEFAULT_CONTACT_PERSON_NAME);
        assertThat(testManufacturer.getWebUrl()).isEqualTo(DEFAULT_WEB_URL);
        assertThat(testManufacturer.getAddressLine1()).isEqualTo(UPDATED_ADDRESS_LINE_1);
        assertThat(testManufacturer.getAddressLine2()).isEqualTo(DEFAULT_ADDRESS_LINE_2);
        assertThat(testManufacturer.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testManufacturer.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testManufacturer.getZip()).isEqualTo(DEFAULT_ZIP);
        assertThat(testManufacturer.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testManufacturer.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testManufacturer.getContactNo1()).isEqualTo(UPDATED_CONTACT_NO_1);
        assertThat(testManufacturer.getContactNo2()).isEqualTo(DEFAULT_CONTACT_NO_2);
        assertThat(testManufacturer.getEfax()).isEqualTo(UPDATED_EFAX);
        assertThat(testManufacturer.getManufacturerNo()).isEqualTo(DEFAULT_MANUFACTURER_NO);
    }

    @Test
    @Transactional
    void fullUpdateManufacturerWithPatch() throws Exception {
        // Initialize the database
        manufacturerRepository.saveAndFlush(manufacturer);

        int databaseSizeBeforeUpdate = manufacturerRepository.findAll().size();

        // Update the manufacturer using partial update
        Manufacturer partialUpdatedManufacturer = new Manufacturer();
        partialUpdatedManufacturer.setManufacturerId(manufacturer.getManufacturerId());

        partialUpdatedManufacturer
            .manufacturerName(UPDATED_MANUFACTURER_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .manufacturerUuid(UPDATED_MANUFACTURER_UUID)
            .contactPersonName(UPDATED_CONTACT_PERSON_NAME)
            .webUrl(UPDATED_WEB_URL)
            .addressLine1(UPDATED_ADDRESS_LINE_1)
            .addressLine2(UPDATED_ADDRESS_LINE_2)
            .city(UPDATED_CITY)
            .state(UPDATED_STATE)
            .zip(UPDATED_ZIP)
            .email(UPDATED_EMAIL)
            .fax(UPDATED_FAX)
            .contactNo1(UPDATED_CONTACT_NO_1)
            .contactNo2(UPDATED_CONTACT_NO_2)
            .efax(UPDATED_EFAX)
            .manufacturerNo(UPDATED_MANUFACTURER_NO);

        restManufacturerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedManufacturer.getManufacturerId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedManufacturer))
            )
            .andExpect(status().isOk());

        // Validate the Manufacturer in the database
        List<Manufacturer> manufacturerList = manufacturerRepository.findAll();
        assertThat(manufacturerList).hasSize(databaseSizeBeforeUpdate);
        Manufacturer testManufacturer = manufacturerList.get(manufacturerList.size() - 1);
        assertThat(testManufacturer.getManufacturerName()).isEqualTo(UPDATED_MANUFACTURER_NAME);
        assertThat(testManufacturer.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testManufacturer.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testManufacturer.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testManufacturer.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testManufacturer.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testManufacturer.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testManufacturer.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testManufacturer.getManufacturerUuid()).isEqualTo(UPDATED_MANUFACTURER_UUID);
        assertThat(testManufacturer.getContactPersonName()).isEqualTo(UPDATED_CONTACT_PERSON_NAME);
        assertThat(testManufacturer.getWebUrl()).isEqualTo(UPDATED_WEB_URL);
        assertThat(testManufacturer.getAddressLine1()).isEqualTo(UPDATED_ADDRESS_LINE_1);
        assertThat(testManufacturer.getAddressLine2()).isEqualTo(UPDATED_ADDRESS_LINE_2);
        assertThat(testManufacturer.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testManufacturer.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testManufacturer.getZip()).isEqualTo(UPDATED_ZIP);
        assertThat(testManufacturer.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testManufacturer.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testManufacturer.getContactNo1()).isEqualTo(UPDATED_CONTACT_NO_1);
        assertThat(testManufacturer.getContactNo2()).isEqualTo(UPDATED_CONTACT_NO_2);
        assertThat(testManufacturer.getEfax()).isEqualTo(UPDATED_EFAX);
        assertThat(testManufacturer.getManufacturerNo()).isEqualTo(UPDATED_MANUFACTURER_NO);
    }

    @Test
    @Transactional
    void patchNonExistingManufacturer() throws Exception {
        int databaseSizeBeforeUpdate = manufacturerRepository.findAll().size();
        manufacturer.setManufacturerId(count.incrementAndGet());

        // Create the Manufacturer
        ManufacturerDTO manufacturerDTO = manufacturerMapper.toDto(manufacturer);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restManufacturerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, manufacturerDTO.getManufacturerId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(manufacturerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Manufacturer in the database
        List<Manufacturer> manufacturerList = manufacturerRepository.findAll();
        assertThat(manufacturerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchManufacturer() throws Exception {
        int databaseSizeBeforeUpdate = manufacturerRepository.findAll().size();
        manufacturer.setManufacturerId(count.incrementAndGet());

        // Create the Manufacturer
        ManufacturerDTO manufacturerDTO = manufacturerMapper.toDto(manufacturer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restManufacturerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(manufacturerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Manufacturer in the database
        List<Manufacturer> manufacturerList = manufacturerRepository.findAll();
        assertThat(manufacturerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamManufacturer() throws Exception {
        int databaseSizeBeforeUpdate = manufacturerRepository.findAll().size();
        manufacturer.setManufacturerId(count.incrementAndGet());

        // Create the Manufacturer
        ManufacturerDTO manufacturerDTO = manufacturerMapper.toDto(manufacturer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restManufacturerMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(manufacturerDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Manufacturer in the database
        List<Manufacturer> manufacturerList = manufacturerRepository.findAll();
        assertThat(manufacturerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteManufacturer() throws Exception {
        // Initialize the database
        manufacturerRepository.saveAndFlush(manufacturer);

        int databaseSizeBeforeDelete = manufacturerRepository.findAll().size();

        // Delete the manufacturer
        restManufacturerMockMvc
            .perform(delete(ENTITY_API_URL_ID, manufacturer.getManufacturerId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Manufacturer> manufacturerList = manufacturerRepository.findAll();
        assertThat(manufacturerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
