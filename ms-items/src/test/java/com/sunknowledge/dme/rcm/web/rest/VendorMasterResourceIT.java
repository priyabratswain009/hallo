package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.VendorMaster;
import com.sunknowledge.dme.rcm.repository.VendorMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.VendorMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.VendorMasterMapper;
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
 * Integration tests for the {@link VendorMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class VendorMasterResourceIT {

    private static final String DEFAULT_VENDOR_NAME = "AAAAAAAAAA";
    private static final String UPDATED_VENDOR_NAME = "BBBBBBBBBB";

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

    private static final String DEFAULT_VENDOR_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_VENDOR_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_VENDOR_NO = "AAAAAAAAAA";
    private static final String UPDATED_VENDOR_NO = "BBBBBBBBBB";

    private static final UUID DEFAULT_VENDOR_MASTER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_VENDOR_MASTER_UUID = UUID.randomUUID();

    private static final String DEFAULT_VENDOR_ACCOUNT_NO = "AAAAAAAAAA";
    private static final String UPDATED_VENDOR_ACCOUNT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_DEFAULT_PO_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_DEFAULT_PO_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_DEFAULT_SHOP_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_DEFAULT_SHOP_TYPE = "BBBBBBBBBB";

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

    private static final String DEFAULT_EFAX = "AAAAAAAAAA";
    private static final String UPDATED_EFAX = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_PERSON_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_PERSON_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_NO_1 = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_NO_1 = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_NO_2 = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_NO_2 = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/vendor-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{vendorId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private VendorMasterRepository vendorMasterRepository;

    @Autowired
    private VendorMasterMapper vendorMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVendorMasterMockMvc;

    private VendorMaster vendorMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VendorMaster createEntity(EntityManager em) {
        VendorMaster vendorMaster = new VendorMaster()
            .vendorName(DEFAULT_VENDOR_NAME)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .vendorDescription(DEFAULT_VENDOR_DESCRIPTION)
            .vendorNo(DEFAULT_VENDOR_NO)
            .vendorMasterUuid(DEFAULT_VENDOR_MASTER_UUID)
            .vendorAccountNo(DEFAULT_VENDOR_ACCOUNT_NO)
            .defaultPoType(DEFAULT_DEFAULT_PO_TYPE)
            .defaultShopType(DEFAULT_DEFAULT_SHOP_TYPE)
            .addressLine1(DEFAULT_ADDRESS_LINE_1)
            .addressLine2(DEFAULT_ADDRESS_LINE_2)
            .city(DEFAULT_CITY)
            .state(DEFAULT_STATE)
            .zip(DEFAULT_ZIP)
            .email(DEFAULT_EMAIL)
            .fax(DEFAULT_FAX)
            .efax(DEFAULT_EFAX)
            .contactPersonName(DEFAULT_CONTACT_PERSON_NAME)
            .contactNo1(DEFAULT_CONTACT_NO_1)
            .contactNo2(DEFAULT_CONTACT_NO_2);
        return vendorMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VendorMaster createUpdatedEntity(EntityManager em) {
        VendorMaster vendorMaster = new VendorMaster()
            .vendorName(UPDATED_VENDOR_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .vendorDescription(UPDATED_VENDOR_DESCRIPTION)
            .vendorNo(UPDATED_VENDOR_NO)
            .vendorMasterUuid(UPDATED_VENDOR_MASTER_UUID)
            .vendorAccountNo(UPDATED_VENDOR_ACCOUNT_NO)
            .defaultPoType(UPDATED_DEFAULT_PO_TYPE)
            .defaultShopType(UPDATED_DEFAULT_SHOP_TYPE)
            .addressLine1(UPDATED_ADDRESS_LINE_1)
            .addressLine2(UPDATED_ADDRESS_LINE_2)
            .city(UPDATED_CITY)
            .state(UPDATED_STATE)
            .zip(UPDATED_ZIP)
            .email(UPDATED_EMAIL)
            .fax(UPDATED_FAX)
            .efax(UPDATED_EFAX)
            .contactPersonName(UPDATED_CONTACT_PERSON_NAME)
            .contactNo1(UPDATED_CONTACT_NO_1)
            .contactNo2(UPDATED_CONTACT_NO_2);
        return vendorMaster;
    }

    @BeforeEach
    public void initTest() {
        vendorMaster = createEntity(em);
    }

    @Test
    @Transactional
    void createVendorMaster() throws Exception {
        int databaseSizeBeforeCreate = vendorMasterRepository.findAll().size();
        // Create the VendorMaster
        VendorMasterDTO vendorMasterDTO = vendorMasterMapper.toDto(vendorMaster);
        restVendorMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(vendorMasterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the VendorMaster in the database
        List<VendorMaster> vendorMasterList = vendorMasterRepository.findAll();
        assertThat(vendorMasterList).hasSize(databaseSizeBeforeCreate + 1);
        VendorMaster testVendorMaster = vendorMasterList.get(vendorMasterList.size() - 1);
        assertThat(testVendorMaster.getVendorName()).isEqualTo(DEFAULT_VENDOR_NAME);
        assertThat(testVendorMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testVendorMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testVendorMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testVendorMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testVendorMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testVendorMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testVendorMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testVendorMaster.getVendorDescription()).isEqualTo(DEFAULT_VENDOR_DESCRIPTION);
        assertThat(testVendorMaster.getVendorNo()).isEqualTo(DEFAULT_VENDOR_NO);
        assertThat(testVendorMaster.getVendorMasterUuid()).isEqualTo(DEFAULT_VENDOR_MASTER_UUID);
        assertThat(testVendorMaster.getVendorAccountNo()).isEqualTo(DEFAULT_VENDOR_ACCOUNT_NO);
        assertThat(testVendorMaster.getDefaultPoType()).isEqualTo(DEFAULT_DEFAULT_PO_TYPE);
        assertThat(testVendorMaster.getDefaultShopType()).isEqualTo(DEFAULT_DEFAULT_SHOP_TYPE);
        assertThat(testVendorMaster.getAddressLine1()).isEqualTo(DEFAULT_ADDRESS_LINE_1);
        assertThat(testVendorMaster.getAddressLine2()).isEqualTo(DEFAULT_ADDRESS_LINE_2);
        assertThat(testVendorMaster.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testVendorMaster.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testVendorMaster.getZip()).isEqualTo(DEFAULT_ZIP);
        assertThat(testVendorMaster.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testVendorMaster.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testVendorMaster.getEfax()).isEqualTo(DEFAULT_EFAX);
        assertThat(testVendorMaster.getContactPersonName()).isEqualTo(DEFAULT_CONTACT_PERSON_NAME);
        assertThat(testVendorMaster.getContactNo1()).isEqualTo(DEFAULT_CONTACT_NO_1);
        assertThat(testVendorMaster.getContactNo2()).isEqualTo(DEFAULT_CONTACT_NO_2);
    }

    @Test
    @Transactional
    void createVendorMasterWithExistingId() throws Exception {
        // Create the VendorMaster with an existing ID
        vendorMaster.setVendorId(1L);
        VendorMasterDTO vendorMasterDTO = vendorMasterMapper.toDto(vendorMaster);

        int databaseSizeBeforeCreate = vendorMasterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restVendorMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(vendorMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the VendorMaster in the database
        List<VendorMaster> vendorMasterList = vendorMasterRepository.findAll();
        assertThat(vendorMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllVendorMasters() throws Exception {
        // Initialize the database
        vendorMasterRepository.saveAndFlush(vendorMaster);

        // Get all the vendorMasterList
        restVendorMasterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=vendorId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].vendorId").value(hasItem(vendorMaster.getVendorId().intValue())))
            .andExpect(jsonPath("$.[*].vendorName").value(hasItem(DEFAULT_VENDOR_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].vendorDescription").value(hasItem(DEFAULT_VENDOR_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].vendorNo").value(hasItem(DEFAULT_VENDOR_NO)))
            .andExpect(jsonPath("$.[*].vendorMasterUuid").value(hasItem(DEFAULT_VENDOR_MASTER_UUID.toString())))
            .andExpect(jsonPath("$.[*].vendorAccountNo").value(hasItem(DEFAULT_VENDOR_ACCOUNT_NO)))
            .andExpect(jsonPath("$.[*].defaultPoType").value(hasItem(DEFAULT_DEFAULT_PO_TYPE)))
            .andExpect(jsonPath("$.[*].defaultShopType").value(hasItem(DEFAULT_DEFAULT_SHOP_TYPE)))
            .andExpect(jsonPath("$.[*].addressLine1").value(hasItem(DEFAULT_ADDRESS_LINE_1)))
            .andExpect(jsonPath("$.[*].addressLine2").value(hasItem(DEFAULT_ADDRESS_LINE_2)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE)))
            .andExpect(jsonPath("$.[*].zip").value(hasItem(DEFAULT_ZIP)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX)))
            .andExpect(jsonPath("$.[*].efax").value(hasItem(DEFAULT_EFAX)))
            .andExpect(jsonPath("$.[*].contactPersonName").value(hasItem(DEFAULT_CONTACT_PERSON_NAME)))
            .andExpect(jsonPath("$.[*].contactNo1").value(hasItem(DEFAULT_CONTACT_NO_1)))
            .andExpect(jsonPath("$.[*].contactNo2").value(hasItem(DEFAULT_CONTACT_NO_2)));
    }

    @Test
    @Transactional
    void getVendorMaster() throws Exception {
        // Initialize the database
        vendorMasterRepository.saveAndFlush(vendorMaster);

        // Get the vendorMaster
        restVendorMasterMockMvc
            .perform(get(ENTITY_API_URL_ID, vendorMaster.getVendorId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.vendorId").value(vendorMaster.getVendorId().intValue()))
            .andExpect(jsonPath("$.vendorName").value(DEFAULT_VENDOR_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.vendorDescription").value(DEFAULT_VENDOR_DESCRIPTION))
            .andExpect(jsonPath("$.vendorNo").value(DEFAULT_VENDOR_NO))
            .andExpect(jsonPath("$.vendorMasterUuid").value(DEFAULT_VENDOR_MASTER_UUID.toString()))
            .andExpect(jsonPath("$.vendorAccountNo").value(DEFAULT_VENDOR_ACCOUNT_NO))
            .andExpect(jsonPath("$.defaultPoType").value(DEFAULT_DEFAULT_PO_TYPE))
            .andExpect(jsonPath("$.defaultShopType").value(DEFAULT_DEFAULT_SHOP_TYPE))
            .andExpect(jsonPath("$.addressLine1").value(DEFAULT_ADDRESS_LINE_1))
            .andExpect(jsonPath("$.addressLine2").value(DEFAULT_ADDRESS_LINE_2))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE))
            .andExpect(jsonPath("$.zip").value(DEFAULT_ZIP))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.fax").value(DEFAULT_FAX))
            .andExpect(jsonPath("$.efax").value(DEFAULT_EFAX))
            .andExpect(jsonPath("$.contactPersonName").value(DEFAULT_CONTACT_PERSON_NAME))
            .andExpect(jsonPath("$.contactNo1").value(DEFAULT_CONTACT_NO_1))
            .andExpect(jsonPath("$.contactNo2").value(DEFAULT_CONTACT_NO_2));
    }

    @Test
    @Transactional
    void getNonExistingVendorMaster() throws Exception {
        // Get the vendorMaster
        restVendorMasterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingVendorMaster() throws Exception {
        // Initialize the database
        vendorMasterRepository.saveAndFlush(vendorMaster);

        int databaseSizeBeforeUpdate = vendorMasterRepository.findAll().size();

        // Update the vendorMaster
        VendorMaster updatedVendorMaster = vendorMasterRepository.findById(vendorMaster.getVendorId()).get();
        // Disconnect from session so that the updates on updatedVendorMaster are not directly saved in db
        em.detach(updatedVendorMaster);
        updatedVendorMaster
            .vendorName(UPDATED_VENDOR_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .vendorDescription(UPDATED_VENDOR_DESCRIPTION)
            .vendorNo(UPDATED_VENDOR_NO)
            .vendorMasterUuid(UPDATED_VENDOR_MASTER_UUID)
            .vendorAccountNo(UPDATED_VENDOR_ACCOUNT_NO)
            .defaultPoType(UPDATED_DEFAULT_PO_TYPE)
            .defaultShopType(UPDATED_DEFAULT_SHOP_TYPE)
            .addressLine1(UPDATED_ADDRESS_LINE_1)
            .addressLine2(UPDATED_ADDRESS_LINE_2)
            .city(UPDATED_CITY)
            .state(UPDATED_STATE)
            .zip(UPDATED_ZIP)
            .email(UPDATED_EMAIL)
            .fax(UPDATED_FAX)
            .efax(UPDATED_EFAX)
            .contactPersonName(UPDATED_CONTACT_PERSON_NAME)
            .contactNo1(UPDATED_CONTACT_NO_1)
            .contactNo2(UPDATED_CONTACT_NO_2);
        VendorMasterDTO vendorMasterDTO = vendorMasterMapper.toDto(updatedVendorMaster);

        restVendorMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, vendorMasterDTO.getVendorId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(vendorMasterDTO))
            )
            .andExpect(status().isOk());

        // Validate the VendorMaster in the database
        List<VendorMaster> vendorMasterList = vendorMasterRepository.findAll();
        assertThat(vendorMasterList).hasSize(databaseSizeBeforeUpdate);
        VendorMaster testVendorMaster = vendorMasterList.get(vendorMasterList.size() - 1);
        assertThat(testVendorMaster.getVendorName()).isEqualTo(UPDATED_VENDOR_NAME);
        assertThat(testVendorMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testVendorMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testVendorMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testVendorMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testVendorMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testVendorMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testVendorMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testVendorMaster.getVendorDescription()).isEqualTo(UPDATED_VENDOR_DESCRIPTION);
        assertThat(testVendorMaster.getVendorNo()).isEqualTo(UPDATED_VENDOR_NO);
        assertThat(testVendorMaster.getVendorMasterUuid()).isEqualTo(UPDATED_VENDOR_MASTER_UUID);
        assertThat(testVendorMaster.getVendorAccountNo()).isEqualTo(UPDATED_VENDOR_ACCOUNT_NO);
        assertThat(testVendorMaster.getDefaultPoType()).isEqualTo(UPDATED_DEFAULT_PO_TYPE);
        assertThat(testVendorMaster.getDefaultShopType()).isEqualTo(UPDATED_DEFAULT_SHOP_TYPE);
        assertThat(testVendorMaster.getAddressLine1()).isEqualTo(UPDATED_ADDRESS_LINE_1);
        assertThat(testVendorMaster.getAddressLine2()).isEqualTo(UPDATED_ADDRESS_LINE_2);
        assertThat(testVendorMaster.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testVendorMaster.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testVendorMaster.getZip()).isEqualTo(UPDATED_ZIP);
        assertThat(testVendorMaster.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testVendorMaster.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testVendorMaster.getEfax()).isEqualTo(UPDATED_EFAX);
        assertThat(testVendorMaster.getContactPersonName()).isEqualTo(UPDATED_CONTACT_PERSON_NAME);
        assertThat(testVendorMaster.getContactNo1()).isEqualTo(UPDATED_CONTACT_NO_1);
        assertThat(testVendorMaster.getContactNo2()).isEqualTo(UPDATED_CONTACT_NO_2);
    }

    @Test
    @Transactional
    void putNonExistingVendorMaster() throws Exception {
        int databaseSizeBeforeUpdate = vendorMasterRepository.findAll().size();
        vendorMaster.setVendorId(count.incrementAndGet());

        // Create the VendorMaster
        VendorMasterDTO vendorMasterDTO = vendorMasterMapper.toDto(vendorMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVendorMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, vendorMasterDTO.getVendorId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(vendorMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the VendorMaster in the database
        List<VendorMaster> vendorMasterList = vendorMasterRepository.findAll();
        assertThat(vendorMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchVendorMaster() throws Exception {
        int databaseSizeBeforeUpdate = vendorMasterRepository.findAll().size();
        vendorMaster.setVendorId(count.incrementAndGet());

        // Create the VendorMaster
        VendorMasterDTO vendorMasterDTO = vendorMasterMapper.toDto(vendorMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVendorMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(vendorMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the VendorMaster in the database
        List<VendorMaster> vendorMasterList = vendorMasterRepository.findAll();
        assertThat(vendorMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamVendorMaster() throws Exception {
        int databaseSizeBeforeUpdate = vendorMasterRepository.findAll().size();
        vendorMaster.setVendorId(count.incrementAndGet());

        // Create the VendorMaster
        VendorMasterDTO vendorMasterDTO = vendorMasterMapper.toDto(vendorMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVendorMasterMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(vendorMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the VendorMaster in the database
        List<VendorMaster> vendorMasterList = vendorMasterRepository.findAll();
        assertThat(vendorMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateVendorMasterWithPatch() throws Exception {
        // Initialize the database
        vendorMasterRepository.saveAndFlush(vendorMaster);

        int databaseSizeBeforeUpdate = vendorMasterRepository.findAll().size();

        // Update the vendorMaster using partial update
        VendorMaster partialUpdatedVendorMaster = new VendorMaster();
        partialUpdatedVendorMaster.setVendorId(vendorMaster.getVendorId());

        partialUpdatedVendorMaster
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .vendorDescription(UPDATED_VENDOR_DESCRIPTION)
            .vendorNo(UPDATED_VENDOR_NO)
            .vendorAccountNo(UPDATED_VENDOR_ACCOUNT_NO)
            .defaultPoType(UPDATED_DEFAULT_PO_TYPE)
            .city(UPDATED_CITY)
            .state(UPDATED_STATE)
            .email(UPDATED_EMAIL)
            .efax(UPDATED_EFAX)
            .contactNo1(UPDATED_CONTACT_NO_1);

        restVendorMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVendorMaster.getVendorId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedVendorMaster))
            )
            .andExpect(status().isOk());

        // Validate the VendorMaster in the database
        List<VendorMaster> vendorMasterList = vendorMasterRepository.findAll();
        assertThat(vendorMasterList).hasSize(databaseSizeBeforeUpdate);
        VendorMaster testVendorMaster = vendorMasterList.get(vendorMasterList.size() - 1);
        assertThat(testVendorMaster.getVendorName()).isEqualTo(DEFAULT_VENDOR_NAME);
        assertThat(testVendorMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testVendorMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testVendorMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testVendorMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testVendorMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testVendorMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testVendorMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testVendorMaster.getVendorDescription()).isEqualTo(UPDATED_VENDOR_DESCRIPTION);
        assertThat(testVendorMaster.getVendorNo()).isEqualTo(UPDATED_VENDOR_NO);
        assertThat(testVendorMaster.getVendorMasterUuid()).isEqualTo(DEFAULT_VENDOR_MASTER_UUID);
        assertThat(testVendorMaster.getVendorAccountNo()).isEqualTo(UPDATED_VENDOR_ACCOUNT_NO);
        assertThat(testVendorMaster.getDefaultPoType()).isEqualTo(UPDATED_DEFAULT_PO_TYPE);
        assertThat(testVendorMaster.getDefaultShopType()).isEqualTo(DEFAULT_DEFAULT_SHOP_TYPE);
        assertThat(testVendorMaster.getAddressLine1()).isEqualTo(DEFAULT_ADDRESS_LINE_1);
        assertThat(testVendorMaster.getAddressLine2()).isEqualTo(DEFAULT_ADDRESS_LINE_2);
        assertThat(testVendorMaster.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testVendorMaster.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testVendorMaster.getZip()).isEqualTo(DEFAULT_ZIP);
        assertThat(testVendorMaster.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testVendorMaster.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testVendorMaster.getEfax()).isEqualTo(UPDATED_EFAX);
        assertThat(testVendorMaster.getContactPersonName()).isEqualTo(DEFAULT_CONTACT_PERSON_NAME);
        assertThat(testVendorMaster.getContactNo1()).isEqualTo(UPDATED_CONTACT_NO_1);
        assertThat(testVendorMaster.getContactNo2()).isEqualTo(DEFAULT_CONTACT_NO_2);
    }

    @Test
    @Transactional
    void fullUpdateVendorMasterWithPatch() throws Exception {
        // Initialize the database
        vendorMasterRepository.saveAndFlush(vendorMaster);

        int databaseSizeBeforeUpdate = vendorMasterRepository.findAll().size();

        // Update the vendorMaster using partial update
        VendorMaster partialUpdatedVendorMaster = new VendorMaster();
        partialUpdatedVendorMaster.setVendorId(vendorMaster.getVendorId());

        partialUpdatedVendorMaster
            .vendorName(UPDATED_VENDOR_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .vendorDescription(UPDATED_VENDOR_DESCRIPTION)
            .vendorNo(UPDATED_VENDOR_NO)
            .vendorMasterUuid(UPDATED_VENDOR_MASTER_UUID)
            .vendorAccountNo(UPDATED_VENDOR_ACCOUNT_NO)
            .defaultPoType(UPDATED_DEFAULT_PO_TYPE)
            .defaultShopType(UPDATED_DEFAULT_SHOP_TYPE)
            .addressLine1(UPDATED_ADDRESS_LINE_1)
            .addressLine2(UPDATED_ADDRESS_LINE_2)
            .city(UPDATED_CITY)
            .state(UPDATED_STATE)
            .zip(UPDATED_ZIP)
            .email(UPDATED_EMAIL)
            .fax(UPDATED_FAX)
            .efax(UPDATED_EFAX)
            .contactPersonName(UPDATED_CONTACT_PERSON_NAME)
            .contactNo1(UPDATED_CONTACT_NO_1)
            .contactNo2(UPDATED_CONTACT_NO_2);

        restVendorMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVendorMaster.getVendorId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedVendorMaster))
            )
            .andExpect(status().isOk());

        // Validate the VendorMaster in the database
        List<VendorMaster> vendorMasterList = vendorMasterRepository.findAll();
        assertThat(vendorMasterList).hasSize(databaseSizeBeforeUpdate);
        VendorMaster testVendorMaster = vendorMasterList.get(vendorMasterList.size() - 1);
        assertThat(testVendorMaster.getVendorName()).isEqualTo(UPDATED_VENDOR_NAME);
        assertThat(testVendorMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testVendorMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testVendorMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testVendorMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testVendorMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testVendorMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testVendorMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testVendorMaster.getVendorDescription()).isEqualTo(UPDATED_VENDOR_DESCRIPTION);
        assertThat(testVendorMaster.getVendorNo()).isEqualTo(UPDATED_VENDOR_NO);
        assertThat(testVendorMaster.getVendorMasterUuid()).isEqualTo(UPDATED_VENDOR_MASTER_UUID);
        assertThat(testVendorMaster.getVendorAccountNo()).isEqualTo(UPDATED_VENDOR_ACCOUNT_NO);
        assertThat(testVendorMaster.getDefaultPoType()).isEqualTo(UPDATED_DEFAULT_PO_TYPE);
        assertThat(testVendorMaster.getDefaultShopType()).isEqualTo(UPDATED_DEFAULT_SHOP_TYPE);
        assertThat(testVendorMaster.getAddressLine1()).isEqualTo(UPDATED_ADDRESS_LINE_1);
        assertThat(testVendorMaster.getAddressLine2()).isEqualTo(UPDATED_ADDRESS_LINE_2);
        assertThat(testVendorMaster.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testVendorMaster.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testVendorMaster.getZip()).isEqualTo(UPDATED_ZIP);
        assertThat(testVendorMaster.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testVendorMaster.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testVendorMaster.getEfax()).isEqualTo(UPDATED_EFAX);
        assertThat(testVendorMaster.getContactPersonName()).isEqualTo(UPDATED_CONTACT_PERSON_NAME);
        assertThat(testVendorMaster.getContactNo1()).isEqualTo(UPDATED_CONTACT_NO_1);
        assertThat(testVendorMaster.getContactNo2()).isEqualTo(UPDATED_CONTACT_NO_2);
    }

    @Test
    @Transactional
    void patchNonExistingVendorMaster() throws Exception {
        int databaseSizeBeforeUpdate = vendorMasterRepository.findAll().size();
        vendorMaster.setVendorId(count.incrementAndGet());

        // Create the VendorMaster
        VendorMasterDTO vendorMasterDTO = vendorMasterMapper.toDto(vendorMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVendorMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, vendorMasterDTO.getVendorId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(vendorMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the VendorMaster in the database
        List<VendorMaster> vendorMasterList = vendorMasterRepository.findAll();
        assertThat(vendorMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchVendorMaster() throws Exception {
        int databaseSizeBeforeUpdate = vendorMasterRepository.findAll().size();
        vendorMaster.setVendorId(count.incrementAndGet());

        // Create the VendorMaster
        VendorMasterDTO vendorMasterDTO = vendorMasterMapper.toDto(vendorMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVendorMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(vendorMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the VendorMaster in the database
        List<VendorMaster> vendorMasterList = vendorMasterRepository.findAll();
        assertThat(vendorMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamVendorMaster() throws Exception {
        int databaseSizeBeforeUpdate = vendorMasterRepository.findAll().size();
        vendorMaster.setVendorId(count.incrementAndGet());

        // Create the VendorMaster
        VendorMasterDTO vendorMasterDTO = vendorMasterMapper.toDto(vendorMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVendorMasterMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(vendorMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the VendorMaster in the database
        List<VendorMaster> vendorMasterList = vendorMasterRepository.findAll();
        assertThat(vendorMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteVendorMaster() throws Exception {
        // Initialize the database
        vendorMasterRepository.saveAndFlush(vendorMaster);

        int databaseSizeBeforeDelete = vendorMasterRepository.findAll().size();

        // Delete the vendorMaster
        restVendorMasterMockMvc
            .perform(delete(ENTITY_API_URL_ID, vendorMaster.getVendorId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<VendorMaster> vendorMasterList = vendorMasterRepository.findAll();
        assertThat(vendorMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
