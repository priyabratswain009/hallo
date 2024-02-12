package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ItemLocation;
import com.sunknowledge.dme.rcm.repository.ItemLocationRepository;
import com.sunknowledge.dme.rcm.service.dto.ItemLocationDTO;
import com.sunknowledge.dme.rcm.service.mapper.ItemLocationMapper;
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
 * Integration tests for the {@link ItemLocationResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ItemLocationResourceIT {

    private static final String DEFAULT_ITEM_LOCATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_LOCATION_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_FIRST_NAME = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_ITEM_LOCATION_UUID = UUID.randomUUID();
    private static final UUID UPDATED_ITEM_LOCATION_UUID = UUID.randomUUID();

    private static final String DEFAULT_BILLING_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_CITY = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_STATE = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_ZIP = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_NO_1 = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_NO_1 = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_NO_2 = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_NO_2 = "BBBBBBBBBB";

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_BILLLING_ADDRESS_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BILLLING_ADDRESS_COMPANY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_CITY = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_STATE = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_ZIP = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/item-locations";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{itemLocationId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ItemLocationRepository itemLocationRepository;

    @Autowired
    private ItemLocationMapper itemLocationMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restItemLocationMockMvc;

    private ItemLocation itemLocation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemLocation createEntity(EntityManager em) {
        ItemLocation itemLocation = new ItemLocation()
            .itemLocationName(DEFAULT_ITEM_LOCATION_NAME)
            .description(DEFAULT_DESCRIPTION)
            .contactFirstName(DEFAULT_CONTACT_FIRST_NAME)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .itemLocationUuid(DEFAULT_ITEM_LOCATION_UUID)
            .billingAddressLine1(DEFAULT_BILLING_ADDRESS_LINE_1)
            .billingAddressLine2(DEFAULT_BILLING_ADDRESS_LINE_2)
            .billingCity(DEFAULT_BILLING_CITY)
            .billingState(DEFAULT_BILLING_STATE)
            .billingCountry(DEFAULT_BILLING_COUNTRY)
            .billingZip(DEFAULT_BILLING_ZIP)
            .contactMiddleName(DEFAULT_CONTACT_MIDDLE_NAME)
            .contactLastName(DEFAULT_CONTACT_LAST_NAME)
            .contactNo1(DEFAULT_CONTACT_NO_1)
            .contactNo2(DEFAULT_CONTACT_NO_2)
            .fax(DEFAULT_FAX)
            .email(DEFAULT_EMAIL)
            .billlingAddressCompanyName(DEFAULT_BILLLING_ADDRESS_COMPANY_NAME)
            .deliveryAddressLine1(DEFAULT_DELIVERY_ADDRESS_LINE_1)
            .deliveryAddressLine2(DEFAULT_DELIVERY_ADDRESS_LINE_2)
            .deliveryCity(DEFAULT_DELIVERY_CITY)
            .deliveryState(DEFAULT_DELIVERY_STATE)
            .deliveryZip(DEFAULT_DELIVERY_ZIP);
        return itemLocation;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemLocation createUpdatedEntity(EntityManager em) {
        ItemLocation itemLocation = new ItemLocation()
            .itemLocationName(UPDATED_ITEM_LOCATION_NAME)
            .description(UPDATED_DESCRIPTION)
            .contactFirstName(UPDATED_CONTACT_FIRST_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .itemLocationUuid(UPDATED_ITEM_LOCATION_UUID)
            .billingAddressLine1(UPDATED_BILLING_ADDRESS_LINE_1)
            .billingAddressLine2(UPDATED_BILLING_ADDRESS_LINE_2)
            .billingCity(UPDATED_BILLING_CITY)
            .billingState(UPDATED_BILLING_STATE)
            .billingCountry(UPDATED_BILLING_COUNTRY)
            .billingZip(UPDATED_BILLING_ZIP)
            .contactMiddleName(UPDATED_CONTACT_MIDDLE_NAME)
            .contactLastName(UPDATED_CONTACT_LAST_NAME)
            .contactNo1(UPDATED_CONTACT_NO_1)
            .contactNo2(UPDATED_CONTACT_NO_2)
            .fax(UPDATED_FAX)
            .email(UPDATED_EMAIL)
            .billlingAddressCompanyName(UPDATED_BILLLING_ADDRESS_COMPANY_NAME)
            .deliveryAddressLine1(UPDATED_DELIVERY_ADDRESS_LINE_1)
            .deliveryAddressLine2(UPDATED_DELIVERY_ADDRESS_LINE_2)
            .deliveryCity(UPDATED_DELIVERY_CITY)
            .deliveryState(UPDATED_DELIVERY_STATE)
            .deliveryZip(UPDATED_DELIVERY_ZIP);
        return itemLocation;
    }

    @BeforeEach
    public void initTest() {
        itemLocation = createEntity(em);
    }

    @Test
    @Transactional
    void createItemLocation() throws Exception {
        int databaseSizeBeforeCreate = itemLocationRepository.findAll().size();
        // Create the ItemLocation
        ItemLocationDTO itemLocationDTO = itemLocationMapper.toDto(itemLocation);
        restItemLocationMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemLocationDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ItemLocation in the database
        List<ItemLocation> itemLocationList = itemLocationRepository.findAll();
        assertThat(itemLocationList).hasSize(databaseSizeBeforeCreate + 1);
        ItemLocation testItemLocation = itemLocationList.get(itemLocationList.size() - 1);
        assertThat(testItemLocation.getItemLocationName()).isEqualTo(DEFAULT_ITEM_LOCATION_NAME);
        assertThat(testItemLocation.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testItemLocation.getContactFirstName()).isEqualTo(DEFAULT_CONTACT_FIRST_NAME);
        assertThat(testItemLocation.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testItemLocation.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testItemLocation.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testItemLocation.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testItemLocation.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testItemLocation.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testItemLocation.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testItemLocation.getItemLocationUuid()).isEqualTo(DEFAULT_ITEM_LOCATION_UUID);
        assertThat(testItemLocation.getBillingAddressLine1()).isEqualTo(DEFAULT_BILLING_ADDRESS_LINE_1);
        assertThat(testItemLocation.getBillingAddressLine2()).isEqualTo(DEFAULT_BILLING_ADDRESS_LINE_2);
        assertThat(testItemLocation.getBillingCity()).isEqualTo(DEFAULT_BILLING_CITY);
        assertThat(testItemLocation.getBillingState()).isEqualTo(DEFAULT_BILLING_STATE);
        assertThat(testItemLocation.getBillingCountry()).isEqualTo(DEFAULT_BILLING_COUNTRY);
        assertThat(testItemLocation.getBillingZip()).isEqualTo(DEFAULT_BILLING_ZIP);
        assertThat(testItemLocation.getContactMiddleName()).isEqualTo(DEFAULT_CONTACT_MIDDLE_NAME);
        assertThat(testItemLocation.getContactLastName()).isEqualTo(DEFAULT_CONTACT_LAST_NAME);
        assertThat(testItemLocation.getContactNo1()).isEqualTo(DEFAULT_CONTACT_NO_1);
        assertThat(testItemLocation.getContactNo2()).isEqualTo(DEFAULT_CONTACT_NO_2);
        assertThat(testItemLocation.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testItemLocation.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testItemLocation.getBilllingAddressCompanyName()).isEqualTo(DEFAULT_BILLLING_ADDRESS_COMPANY_NAME);
        assertThat(testItemLocation.getDeliveryAddressLine1()).isEqualTo(DEFAULT_DELIVERY_ADDRESS_LINE_1);
        assertThat(testItemLocation.getDeliveryAddressLine2()).isEqualTo(DEFAULT_DELIVERY_ADDRESS_LINE_2);
        assertThat(testItemLocation.getDeliveryCity()).isEqualTo(DEFAULT_DELIVERY_CITY);
        assertThat(testItemLocation.getDeliveryState()).isEqualTo(DEFAULT_DELIVERY_STATE);
        assertThat(testItemLocation.getDeliveryZip()).isEqualTo(DEFAULT_DELIVERY_ZIP);
    }

    @Test
    @Transactional
    void createItemLocationWithExistingId() throws Exception {
        // Create the ItemLocation with an existing ID
        itemLocation.setItemLocationId(1L);
        ItemLocationDTO itemLocationDTO = itemLocationMapper.toDto(itemLocation);

        int databaseSizeBeforeCreate = itemLocationRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemLocationMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemLocationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemLocation in the database
        List<ItemLocation> itemLocationList = itemLocationRepository.findAll();
        assertThat(itemLocationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllItemLocations() throws Exception {
        // Initialize the database
        itemLocationRepository.saveAndFlush(itemLocation);

        // Get all the itemLocationList
        restItemLocationMockMvc
            .perform(get(ENTITY_API_URL + "?sort=itemLocationId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].itemLocationId").value(hasItem(itemLocation.getItemLocationId().intValue())))
            .andExpect(jsonPath("$.[*].itemLocationName").value(hasItem(DEFAULT_ITEM_LOCATION_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].contactFirstName").value(hasItem(DEFAULT_CONTACT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].itemLocationUuid").value(hasItem(DEFAULT_ITEM_LOCATION_UUID.toString())))
            .andExpect(jsonPath("$.[*].billingAddressLine1").value(hasItem(DEFAULT_BILLING_ADDRESS_LINE_1)))
            .andExpect(jsonPath("$.[*].billingAddressLine2").value(hasItem(DEFAULT_BILLING_ADDRESS_LINE_2)))
            .andExpect(jsonPath("$.[*].billingCity").value(hasItem(DEFAULT_BILLING_CITY)))
            .andExpect(jsonPath("$.[*].billingState").value(hasItem(DEFAULT_BILLING_STATE)))
            .andExpect(jsonPath("$.[*].billingCountry").value(hasItem(DEFAULT_BILLING_COUNTRY)))
            .andExpect(jsonPath("$.[*].billingZip").value(hasItem(DEFAULT_BILLING_ZIP)))
            .andExpect(jsonPath("$.[*].contactMiddleName").value(hasItem(DEFAULT_CONTACT_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].contactLastName").value(hasItem(DEFAULT_CONTACT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].contactNo1").value(hasItem(DEFAULT_CONTACT_NO_1)))
            .andExpect(jsonPath("$.[*].contactNo2").value(hasItem(DEFAULT_CONTACT_NO_2)))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].billlingAddressCompanyName").value(hasItem(DEFAULT_BILLLING_ADDRESS_COMPANY_NAME)))
            .andExpect(jsonPath("$.[*].deliveryAddressLine1").value(hasItem(DEFAULT_DELIVERY_ADDRESS_LINE_1)))
            .andExpect(jsonPath("$.[*].deliveryAddressLine2").value(hasItem(DEFAULT_DELIVERY_ADDRESS_LINE_2)))
            .andExpect(jsonPath("$.[*].deliveryCity").value(hasItem(DEFAULT_DELIVERY_CITY)))
            .andExpect(jsonPath("$.[*].deliveryState").value(hasItem(DEFAULT_DELIVERY_STATE)))
            .andExpect(jsonPath("$.[*].deliveryZip").value(hasItem(DEFAULT_DELIVERY_ZIP)));
    }

    @Test
    @Transactional
    void getItemLocation() throws Exception {
        // Initialize the database
        itemLocationRepository.saveAndFlush(itemLocation);

        // Get the itemLocation
        restItemLocationMockMvc
            .perform(get(ENTITY_API_URL_ID, itemLocation.getItemLocationId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.itemLocationId").value(itemLocation.getItemLocationId().intValue()))
            .andExpect(jsonPath("$.itemLocationName").value(DEFAULT_ITEM_LOCATION_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.contactFirstName").value(DEFAULT_CONTACT_FIRST_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.itemLocationUuid").value(DEFAULT_ITEM_LOCATION_UUID.toString()))
            .andExpect(jsonPath("$.billingAddressLine1").value(DEFAULT_BILLING_ADDRESS_LINE_1))
            .andExpect(jsonPath("$.billingAddressLine2").value(DEFAULT_BILLING_ADDRESS_LINE_2))
            .andExpect(jsonPath("$.billingCity").value(DEFAULT_BILLING_CITY))
            .andExpect(jsonPath("$.billingState").value(DEFAULT_BILLING_STATE))
            .andExpect(jsonPath("$.billingCountry").value(DEFAULT_BILLING_COUNTRY))
            .andExpect(jsonPath("$.billingZip").value(DEFAULT_BILLING_ZIP))
            .andExpect(jsonPath("$.contactMiddleName").value(DEFAULT_CONTACT_MIDDLE_NAME))
            .andExpect(jsonPath("$.contactLastName").value(DEFAULT_CONTACT_LAST_NAME))
            .andExpect(jsonPath("$.contactNo1").value(DEFAULT_CONTACT_NO_1))
            .andExpect(jsonPath("$.contactNo2").value(DEFAULT_CONTACT_NO_2))
            .andExpect(jsonPath("$.fax").value(DEFAULT_FAX))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.billlingAddressCompanyName").value(DEFAULT_BILLLING_ADDRESS_COMPANY_NAME))
            .andExpect(jsonPath("$.deliveryAddressLine1").value(DEFAULT_DELIVERY_ADDRESS_LINE_1))
            .andExpect(jsonPath("$.deliveryAddressLine2").value(DEFAULT_DELIVERY_ADDRESS_LINE_2))
            .andExpect(jsonPath("$.deliveryCity").value(DEFAULT_DELIVERY_CITY))
            .andExpect(jsonPath("$.deliveryState").value(DEFAULT_DELIVERY_STATE))
            .andExpect(jsonPath("$.deliveryZip").value(DEFAULT_DELIVERY_ZIP));
    }

    @Test
    @Transactional
    void getNonExistingItemLocation() throws Exception {
        // Get the itemLocation
        restItemLocationMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingItemLocation() throws Exception {
        // Initialize the database
        itemLocationRepository.saveAndFlush(itemLocation);

        int databaseSizeBeforeUpdate = itemLocationRepository.findAll().size();

        // Update the itemLocation
        ItemLocation updatedItemLocation = itemLocationRepository.findById(itemLocation.getItemLocationId()).get();
        // Disconnect from session so that the updates on updatedItemLocation are not directly saved in db
        em.detach(updatedItemLocation);
        updatedItemLocation
            .itemLocationName(UPDATED_ITEM_LOCATION_NAME)
            .description(UPDATED_DESCRIPTION)
            .contactFirstName(UPDATED_CONTACT_FIRST_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .itemLocationUuid(UPDATED_ITEM_LOCATION_UUID)
            .billingAddressLine1(UPDATED_BILLING_ADDRESS_LINE_1)
            .billingAddressLine2(UPDATED_BILLING_ADDRESS_LINE_2)
            .billingCity(UPDATED_BILLING_CITY)
            .billingState(UPDATED_BILLING_STATE)
            .billingCountry(UPDATED_BILLING_COUNTRY)
            .billingZip(UPDATED_BILLING_ZIP)
            .contactMiddleName(UPDATED_CONTACT_MIDDLE_NAME)
            .contactLastName(UPDATED_CONTACT_LAST_NAME)
            .contactNo1(UPDATED_CONTACT_NO_1)
            .contactNo2(UPDATED_CONTACT_NO_2)
            .fax(UPDATED_FAX)
            .email(UPDATED_EMAIL)
            .billlingAddressCompanyName(UPDATED_BILLLING_ADDRESS_COMPANY_NAME)
            .deliveryAddressLine1(UPDATED_DELIVERY_ADDRESS_LINE_1)
            .deliveryAddressLine2(UPDATED_DELIVERY_ADDRESS_LINE_2)
            .deliveryCity(UPDATED_DELIVERY_CITY)
            .deliveryState(UPDATED_DELIVERY_STATE)
            .deliveryZip(UPDATED_DELIVERY_ZIP);
        ItemLocationDTO itemLocationDTO = itemLocationMapper.toDto(updatedItemLocation);

        restItemLocationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, itemLocationDTO.getItemLocationId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemLocationDTO))
            )
            .andExpect(status().isOk());

        // Validate the ItemLocation in the database
        List<ItemLocation> itemLocationList = itemLocationRepository.findAll();
        assertThat(itemLocationList).hasSize(databaseSizeBeforeUpdate);
        ItemLocation testItemLocation = itemLocationList.get(itemLocationList.size() - 1);
        assertThat(testItemLocation.getItemLocationName()).isEqualTo(UPDATED_ITEM_LOCATION_NAME);
        assertThat(testItemLocation.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testItemLocation.getContactFirstName()).isEqualTo(UPDATED_CONTACT_FIRST_NAME);
        assertThat(testItemLocation.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testItemLocation.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testItemLocation.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testItemLocation.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testItemLocation.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testItemLocation.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testItemLocation.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testItemLocation.getItemLocationUuid()).isEqualTo(UPDATED_ITEM_LOCATION_UUID);
        assertThat(testItemLocation.getBillingAddressLine1()).isEqualTo(UPDATED_BILLING_ADDRESS_LINE_1);
        assertThat(testItemLocation.getBillingAddressLine2()).isEqualTo(UPDATED_BILLING_ADDRESS_LINE_2);
        assertThat(testItemLocation.getBillingCity()).isEqualTo(UPDATED_BILLING_CITY);
        assertThat(testItemLocation.getBillingState()).isEqualTo(UPDATED_BILLING_STATE);
        assertThat(testItemLocation.getBillingCountry()).isEqualTo(UPDATED_BILLING_COUNTRY);
        assertThat(testItemLocation.getBillingZip()).isEqualTo(UPDATED_BILLING_ZIP);
        assertThat(testItemLocation.getContactMiddleName()).isEqualTo(UPDATED_CONTACT_MIDDLE_NAME);
        assertThat(testItemLocation.getContactLastName()).isEqualTo(UPDATED_CONTACT_LAST_NAME);
        assertThat(testItemLocation.getContactNo1()).isEqualTo(UPDATED_CONTACT_NO_1);
        assertThat(testItemLocation.getContactNo2()).isEqualTo(UPDATED_CONTACT_NO_2);
        assertThat(testItemLocation.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testItemLocation.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testItemLocation.getBilllingAddressCompanyName()).isEqualTo(UPDATED_BILLLING_ADDRESS_COMPANY_NAME);
        assertThat(testItemLocation.getDeliveryAddressLine1()).isEqualTo(UPDATED_DELIVERY_ADDRESS_LINE_1);
        assertThat(testItemLocation.getDeliveryAddressLine2()).isEqualTo(UPDATED_DELIVERY_ADDRESS_LINE_2);
        assertThat(testItemLocation.getDeliveryCity()).isEqualTo(UPDATED_DELIVERY_CITY);
        assertThat(testItemLocation.getDeliveryState()).isEqualTo(UPDATED_DELIVERY_STATE);
        assertThat(testItemLocation.getDeliveryZip()).isEqualTo(UPDATED_DELIVERY_ZIP);
    }

    @Test
    @Transactional
    void putNonExistingItemLocation() throws Exception {
        int databaseSizeBeforeUpdate = itemLocationRepository.findAll().size();
        itemLocation.setItemLocationId(count.incrementAndGet());

        // Create the ItemLocation
        ItemLocationDTO itemLocationDTO = itemLocationMapper.toDto(itemLocation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemLocationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, itemLocationDTO.getItemLocationId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemLocationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemLocation in the database
        List<ItemLocation> itemLocationList = itemLocationRepository.findAll();
        assertThat(itemLocationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchItemLocation() throws Exception {
        int databaseSizeBeforeUpdate = itemLocationRepository.findAll().size();
        itemLocation.setItemLocationId(count.incrementAndGet());

        // Create the ItemLocation
        ItemLocationDTO itemLocationDTO = itemLocationMapper.toDto(itemLocation);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemLocationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemLocationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemLocation in the database
        List<ItemLocation> itemLocationList = itemLocationRepository.findAll();
        assertThat(itemLocationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamItemLocation() throws Exception {
        int databaseSizeBeforeUpdate = itemLocationRepository.findAll().size();
        itemLocation.setItemLocationId(count.incrementAndGet());

        // Create the ItemLocation
        ItemLocationDTO itemLocationDTO = itemLocationMapper.toDto(itemLocation);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemLocationMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemLocationDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ItemLocation in the database
        List<ItemLocation> itemLocationList = itemLocationRepository.findAll();
        assertThat(itemLocationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateItemLocationWithPatch() throws Exception {
        // Initialize the database
        itemLocationRepository.saveAndFlush(itemLocation);

        int databaseSizeBeforeUpdate = itemLocationRepository.findAll().size();

        // Update the itemLocation using partial update
        ItemLocation partialUpdatedItemLocation = new ItemLocation();
        partialUpdatedItemLocation.setItemLocationId(itemLocation.getItemLocationId());

        partialUpdatedItemLocation
            .itemLocationName(UPDATED_ITEM_LOCATION_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .itemLocationUuid(UPDATED_ITEM_LOCATION_UUID)
            .billingAddressLine2(UPDATED_BILLING_ADDRESS_LINE_2)
            .billingCountry(UPDATED_BILLING_COUNTRY)
            .contactNo1(UPDATED_CONTACT_NO_1)
            .email(UPDATED_EMAIL)
            .billlingAddressCompanyName(UPDATED_BILLLING_ADDRESS_COMPANY_NAME)
            .deliveryAddressLine1(UPDATED_DELIVERY_ADDRESS_LINE_1)
            .deliveryAddressLine2(UPDATED_DELIVERY_ADDRESS_LINE_2)
            .deliveryState(UPDATED_DELIVERY_STATE)
            .deliveryZip(UPDATED_DELIVERY_ZIP);

        restItemLocationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedItemLocation.getItemLocationId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedItemLocation))
            )
            .andExpect(status().isOk());

        // Validate the ItemLocation in the database
        List<ItemLocation> itemLocationList = itemLocationRepository.findAll();
        assertThat(itemLocationList).hasSize(databaseSizeBeforeUpdate);
        ItemLocation testItemLocation = itemLocationList.get(itemLocationList.size() - 1);
        assertThat(testItemLocation.getItemLocationName()).isEqualTo(UPDATED_ITEM_LOCATION_NAME);
        assertThat(testItemLocation.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testItemLocation.getContactFirstName()).isEqualTo(DEFAULT_CONTACT_FIRST_NAME);
        assertThat(testItemLocation.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testItemLocation.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testItemLocation.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testItemLocation.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testItemLocation.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testItemLocation.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testItemLocation.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testItemLocation.getItemLocationUuid()).isEqualTo(UPDATED_ITEM_LOCATION_UUID);
        assertThat(testItemLocation.getBillingAddressLine1()).isEqualTo(DEFAULT_BILLING_ADDRESS_LINE_1);
        assertThat(testItemLocation.getBillingAddressLine2()).isEqualTo(UPDATED_BILLING_ADDRESS_LINE_2);
        assertThat(testItemLocation.getBillingCity()).isEqualTo(DEFAULT_BILLING_CITY);
        assertThat(testItemLocation.getBillingState()).isEqualTo(DEFAULT_BILLING_STATE);
        assertThat(testItemLocation.getBillingCountry()).isEqualTo(UPDATED_BILLING_COUNTRY);
        assertThat(testItemLocation.getBillingZip()).isEqualTo(DEFAULT_BILLING_ZIP);
        assertThat(testItemLocation.getContactMiddleName()).isEqualTo(DEFAULT_CONTACT_MIDDLE_NAME);
        assertThat(testItemLocation.getContactLastName()).isEqualTo(DEFAULT_CONTACT_LAST_NAME);
        assertThat(testItemLocation.getContactNo1()).isEqualTo(UPDATED_CONTACT_NO_1);
        assertThat(testItemLocation.getContactNo2()).isEqualTo(DEFAULT_CONTACT_NO_2);
        assertThat(testItemLocation.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testItemLocation.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testItemLocation.getBilllingAddressCompanyName()).isEqualTo(UPDATED_BILLLING_ADDRESS_COMPANY_NAME);
        assertThat(testItemLocation.getDeliveryAddressLine1()).isEqualTo(UPDATED_DELIVERY_ADDRESS_LINE_1);
        assertThat(testItemLocation.getDeliveryAddressLine2()).isEqualTo(UPDATED_DELIVERY_ADDRESS_LINE_2);
        assertThat(testItemLocation.getDeliveryCity()).isEqualTo(DEFAULT_DELIVERY_CITY);
        assertThat(testItemLocation.getDeliveryState()).isEqualTo(UPDATED_DELIVERY_STATE);
        assertThat(testItemLocation.getDeliveryZip()).isEqualTo(UPDATED_DELIVERY_ZIP);
    }

    @Test
    @Transactional
    void fullUpdateItemLocationWithPatch() throws Exception {
        // Initialize the database
        itemLocationRepository.saveAndFlush(itemLocation);

        int databaseSizeBeforeUpdate = itemLocationRepository.findAll().size();

        // Update the itemLocation using partial update
        ItemLocation partialUpdatedItemLocation = new ItemLocation();
        partialUpdatedItemLocation.setItemLocationId(itemLocation.getItemLocationId());

        partialUpdatedItemLocation
            .itemLocationName(UPDATED_ITEM_LOCATION_NAME)
            .description(UPDATED_DESCRIPTION)
            .contactFirstName(UPDATED_CONTACT_FIRST_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .itemLocationUuid(UPDATED_ITEM_LOCATION_UUID)
            .billingAddressLine1(UPDATED_BILLING_ADDRESS_LINE_1)
            .billingAddressLine2(UPDATED_BILLING_ADDRESS_LINE_2)
            .billingCity(UPDATED_BILLING_CITY)
            .billingState(UPDATED_BILLING_STATE)
            .billingCountry(UPDATED_BILLING_COUNTRY)
            .billingZip(UPDATED_BILLING_ZIP)
            .contactMiddleName(UPDATED_CONTACT_MIDDLE_NAME)
            .contactLastName(UPDATED_CONTACT_LAST_NAME)
            .contactNo1(UPDATED_CONTACT_NO_1)
            .contactNo2(UPDATED_CONTACT_NO_2)
            .fax(UPDATED_FAX)
            .email(UPDATED_EMAIL)
            .billlingAddressCompanyName(UPDATED_BILLLING_ADDRESS_COMPANY_NAME)
            .deliveryAddressLine1(UPDATED_DELIVERY_ADDRESS_LINE_1)
            .deliveryAddressLine2(UPDATED_DELIVERY_ADDRESS_LINE_2)
            .deliveryCity(UPDATED_DELIVERY_CITY)
            .deliveryState(UPDATED_DELIVERY_STATE)
            .deliveryZip(UPDATED_DELIVERY_ZIP);

        restItemLocationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedItemLocation.getItemLocationId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedItemLocation))
            )
            .andExpect(status().isOk());

        // Validate the ItemLocation in the database
        List<ItemLocation> itemLocationList = itemLocationRepository.findAll();
        assertThat(itemLocationList).hasSize(databaseSizeBeforeUpdate);
        ItemLocation testItemLocation = itemLocationList.get(itemLocationList.size() - 1);
        assertThat(testItemLocation.getItemLocationName()).isEqualTo(UPDATED_ITEM_LOCATION_NAME);
        assertThat(testItemLocation.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testItemLocation.getContactFirstName()).isEqualTo(UPDATED_CONTACT_FIRST_NAME);
        assertThat(testItemLocation.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testItemLocation.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testItemLocation.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testItemLocation.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testItemLocation.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testItemLocation.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testItemLocation.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testItemLocation.getItemLocationUuid()).isEqualTo(UPDATED_ITEM_LOCATION_UUID);
        assertThat(testItemLocation.getBillingAddressLine1()).isEqualTo(UPDATED_BILLING_ADDRESS_LINE_1);
        assertThat(testItemLocation.getBillingAddressLine2()).isEqualTo(UPDATED_BILLING_ADDRESS_LINE_2);
        assertThat(testItemLocation.getBillingCity()).isEqualTo(UPDATED_BILLING_CITY);
        assertThat(testItemLocation.getBillingState()).isEqualTo(UPDATED_BILLING_STATE);
        assertThat(testItemLocation.getBillingCountry()).isEqualTo(UPDATED_BILLING_COUNTRY);
        assertThat(testItemLocation.getBillingZip()).isEqualTo(UPDATED_BILLING_ZIP);
        assertThat(testItemLocation.getContactMiddleName()).isEqualTo(UPDATED_CONTACT_MIDDLE_NAME);
        assertThat(testItemLocation.getContactLastName()).isEqualTo(UPDATED_CONTACT_LAST_NAME);
        assertThat(testItemLocation.getContactNo1()).isEqualTo(UPDATED_CONTACT_NO_1);
        assertThat(testItemLocation.getContactNo2()).isEqualTo(UPDATED_CONTACT_NO_2);
        assertThat(testItemLocation.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testItemLocation.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testItemLocation.getBilllingAddressCompanyName()).isEqualTo(UPDATED_BILLLING_ADDRESS_COMPANY_NAME);
        assertThat(testItemLocation.getDeliveryAddressLine1()).isEqualTo(UPDATED_DELIVERY_ADDRESS_LINE_1);
        assertThat(testItemLocation.getDeliveryAddressLine2()).isEqualTo(UPDATED_DELIVERY_ADDRESS_LINE_2);
        assertThat(testItemLocation.getDeliveryCity()).isEqualTo(UPDATED_DELIVERY_CITY);
        assertThat(testItemLocation.getDeliveryState()).isEqualTo(UPDATED_DELIVERY_STATE);
        assertThat(testItemLocation.getDeliveryZip()).isEqualTo(UPDATED_DELIVERY_ZIP);
    }

    @Test
    @Transactional
    void patchNonExistingItemLocation() throws Exception {
        int databaseSizeBeforeUpdate = itemLocationRepository.findAll().size();
        itemLocation.setItemLocationId(count.incrementAndGet());

        // Create the ItemLocation
        ItemLocationDTO itemLocationDTO = itemLocationMapper.toDto(itemLocation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemLocationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, itemLocationDTO.getItemLocationId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemLocationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemLocation in the database
        List<ItemLocation> itemLocationList = itemLocationRepository.findAll();
        assertThat(itemLocationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchItemLocation() throws Exception {
        int databaseSizeBeforeUpdate = itemLocationRepository.findAll().size();
        itemLocation.setItemLocationId(count.incrementAndGet());

        // Create the ItemLocation
        ItemLocationDTO itemLocationDTO = itemLocationMapper.toDto(itemLocation);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemLocationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemLocationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemLocation in the database
        List<ItemLocation> itemLocationList = itemLocationRepository.findAll();
        assertThat(itemLocationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamItemLocation() throws Exception {
        int databaseSizeBeforeUpdate = itemLocationRepository.findAll().size();
        itemLocation.setItemLocationId(count.incrementAndGet());

        // Create the ItemLocation
        ItemLocationDTO itemLocationDTO = itemLocationMapper.toDto(itemLocation);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemLocationMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemLocationDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ItemLocation in the database
        List<ItemLocation> itemLocationList = itemLocationRepository.findAll();
        assertThat(itemLocationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteItemLocation() throws Exception {
        // Initialize the database
        itemLocationRepository.saveAndFlush(itemLocation);

        int databaseSizeBeforeDelete = itemLocationRepository.findAll().size();

        // Delete the itemLocation
        restItemLocationMockMvc
            .perform(delete(ENTITY_API_URL_ID, itemLocation.getItemLocationId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ItemLocation> itemLocationList = itemLocationRepository.findAll();
        assertThat(itemLocationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
