package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.BranchOffice;
import com.sunknowledge.dme.rcm.repository.BranchOfficeRepository;
import com.sunknowledge.dme.rcm.service.dto.BranchOfficeDTO;
import com.sunknowledge.dme.rcm.service.mapper.BranchOfficeMapper;
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
 * Integration tests for the {@link BranchOfficeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BranchOfficeResourceIT {

    private static final String DEFAULT_BRANCH_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_BRANCH_GROUP_ID = 1L;
    private static final Long UPDATED_BRANCH_GROUP_ID = 2L;

    private static final String DEFAULT_NPI = "AAAAAAAAAA";
    private static final String UPDATED_NPI = "BBBBBBBBBB";

    private static final String DEFAULT_PTAN = "AAAAAAAAAA";
    private static final String UPDATED_PTAN = "BBBBBBBBBB";

    private static final String DEFAULT_TAXONOMY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TAXONOMY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_TAXONOMY_CODE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_TAXONOMY_CODE_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_TAX_ID_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TAX_ID_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_TAX_ID_NO = "AAAAAAAAAA";
    private static final String UPDATED_TAX_ID_NO = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_NO = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_NO = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final UUID DEFAULT_BRANCH_OFFICE_UUID = UUID.randomUUID();
    private static final UUID UPDATED_BRANCH_OFFICE_UUID = UUID.randomUUID();

    private static final String DEFAULT_BILLING_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_CITY = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_STATE = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_ZIP_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_ZIP_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SUBMITTER_CONTACT_PERSON_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SUBMITTER_CONTACT_PERSON_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SUBMITTER_CONTACT_PHONE_NO_1 = "AAAAAAAAAA";
    private static final String UPDATED_SUBMITTER_CONTACT_PHONE_NO_1 = "BBBBBBBBBB";

    private static final String DEFAULT_SUBMITTER_CONTACT_PHONE_NO_2 = "AAAAAAAAAA";
    private static final String UPDATED_SUBMITTER_CONTACT_PHONE_NO_2 = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_FAX = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_ZIP = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_CONTACT_NO_1 = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_CONTACT_NO_1 = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_CONTACT_NO_2 = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_CONTACT_NO_2 = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_COMPANY = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_COMPANY = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_GROUP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_GROUP_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_SIGNATURE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SIGNATURE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_LOCATION_ID = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_LOCATION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_LOCATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_LOCATION_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_BRANCH_COMPANY_ID = 1L;
    private static final Long UPDATED_BRANCH_COMPANY_ID = 2L;

    private static final String DEFAULT_IS_DROPSHIP_ALLOWED = "AAAAAAAAAA";
    private static final String UPDATED_IS_DROPSHIP_ALLOWED = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_TAXONOMY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_TAXONOMY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_NPI = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_NPI = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_ORGANISATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_ORGANISATION_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_TAX_ID_NO = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_TAX_ID_NO = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_BRANCH_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_BRANCH_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/branch-offices";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{branchId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BranchOfficeRepository branchOfficeRepository;

    @Autowired
    private BranchOfficeMapper branchOfficeMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBranchOfficeMockMvc;

    private BranchOffice branchOffice;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BranchOffice createEntity(EntityManager em) {
        BranchOffice branchOffice = new BranchOffice()
            .branchName(DEFAULT_BRANCH_NAME)
            .branchGroupId(DEFAULT_BRANCH_GROUP_ID)
            .npi(DEFAULT_NPI)
            .ptan(DEFAULT_PTAN)
            .taxonomyCode(DEFAULT_TAXONOMY_CODE)
            .taxonomyCodeDescription(DEFAULT_TAXONOMY_CODE_DESCRIPTION)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .taxIdType(DEFAULT_TAX_ID_TYPE)
            .taxIdNo(DEFAULT_TAX_ID_NO)
            .branchNo(DEFAULT_BRANCH_NO)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .branchOfficeUuid(DEFAULT_BRANCH_OFFICE_UUID)
            .billingAddressLine1(DEFAULT_BILLING_ADDRESS_LINE_1)
            .billingAddressLine2(DEFAULT_BILLING_ADDRESS_LINE_2)
            .billingCity(DEFAULT_BILLING_CITY)
            .billingState(DEFAULT_BILLING_STATE)
            .billingZipCode(DEFAULT_BILLING_ZIP_CODE)
            .submitterContactPersonName(DEFAULT_SUBMITTER_CONTACT_PERSON_NAME)
            .submitterContactPhoneNo1(DEFAULT_SUBMITTER_CONTACT_PHONE_NO_1)
            .submitterContactPhoneNo2(DEFAULT_SUBMITTER_CONTACT_PHONE_NO_2)
            .billingFax(DEFAULT_BILLING_FAX)
            .contactEmail(DEFAULT_CONTACT_EMAIL)
            .zip(DEFAULT_ZIP)
            .billingContactNo1(DEFAULT_BILLING_CONTACT_NO_1)
            .billingContactNo2(DEFAULT_BILLING_CONTACT_NO_2)
            .billingEmail(DEFAULT_BILLING_EMAIL)
            .branchCompany(DEFAULT_BRANCH_COMPANY)
            .branchGroupName(DEFAULT_BRANCH_GROUP_NAME)
            .addressLine1(DEFAULT_ADDRESS_LINE_1)
            .addressLine2(DEFAULT_ADDRESS_LINE_2)
            .city(DEFAULT_CITY)
            .state(DEFAULT_STATE)
            .signatureName(DEFAULT_SIGNATURE_NAME)
            .itemLocationId(DEFAULT_ITEM_LOCATION_ID)
            .itemLocationName(DEFAULT_ITEM_LOCATION_NAME)
            .branchCompanyId(DEFAULT_BRANCH_COMPANY_ID)
            .isDropshipAllowed(DEFAULT_IS_DROPSHIP_ALLOWED)
            .billingTaxonomyCode(DEFAULT_BILLING_TAXONOMY_CODE)
            .billingNpi(DEFAULT_BILLING_NPI)
            .billingOrganisationName(DEFAULT_BILLING_ORGANISATION_NAME)
            .billingTaxIdNo(DEFAULT_BILLING_TAX_ID_NO)
            .billingBranchName(DEFAULT_BILLING_BRANCH_NAME)
            .fax(DEFAULT_FAX);
        return branchOffice;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BranchOffice createUpdatedEntity(EntityManager em) {
        BranchOffice branchOffice = new BranchOffice()
            .branchName(UPDATED_BRANCH_NAME)
            .branchGroupId(UPDATED_BRANCH_GROUP_ID)
            .npi(UPDATED_NPI)
            .ptan(UPDATED_PTAN)
            .taxonomyCode(UPDATED_TAXONOMY_CODE)
            .taxonomyCodeDescription(UPDATED_TAXONOMY_CODE_DESCRIPTION)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .taxIdType(UPDATED_TAX_ID_TYPE)
            .taxIdNo(UPDATED_TAX_ID_NO)
            .branchNo(UPDATED_BRANCH_NO)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .branchOfficeUuid(UPDATED_BRANCH_OFFICE_UUID)
            .billingAddressLine1(UPDATED_BILLING_ADDRESS_LINE_1)
            .billingAddressLine2(UPDATED_BILLING_ADDRESS_LINE_2)
            .billingCity(UPDATED_BILLING_CITY)
            .billingState(UPDATED_BILLING_STATE)
            .billingZipCode(UPDATED_BILLING_ZIP_CODE)
            .submitterContactPersonName(UPDATED_SUBMITTER_CONTACT_PERSON_NAME)
            .submitterContactPhoneNo1(UPDATED_SUBMITTER_CONTACT_PHONE_NO_1)
            .submitterContactPhoneNo2(UPDATED_SUBMITTER_CONTACT_PHONE_NO_2)
            .billingFax(UPDATED_BILLING_FAX)
            .contactEmail(UPDATED_CONTACT_EMAIL)
            .zip(UPDATED_ZIP)
            .billingContactNo1(UPDATED_BILLING_CONTACT_NO_1)
            .billingContactNo2(UPDATED_BILLING_CONTACT_NO_2)
            .billingEmail(UPDATED_BILLING_EMAIL)
            .branchCompany(UPDATED_BRANCH_COMPANY)
            .branchGroupName(UPDATED_BRANCH_GROUP_NAME)
            .addressLine1(UPDATED_ADDRESS_LINE_1)
            .addressLine2(UPDATED_ADDRESS_LINE_2)
            .city(UPDATED_CITY)
            .state(UPDATED_STATE)
            .signatureName(UPDATED_SIGNATURE_NAME)
            .itemLocationId(UPDATED_ITEM_LOCATION_ID)
            .itemLocationName(UPDATED_ITEM_LOCATION_NAME)
            .branchCompanyId(UPDATED_BRANCH_COMPANY_ID)
            .isDropshipAllowed(UPDATED_IS_DROPSHIP_ALLOWED)
            .billingTaxonomyCode(UPDATED_BILLING_TAXONOMY_CODE)
            .billingNpi(UPDATED_BILLING_NPI)
            .billingOrganisationName(UPDATED_BILLING_ORGANISATION_NAME)
            .billingTaxIdNo(UPDATED_BILLING_TAX_ID_NO)
            .billingBranchName(UPDATED_BILLING_BRANCH_NAME)
            .fax(UPDATED_FAX);
        return branchOffice;
    }

    @BeforeEach
    public void initTest() {
        branchOffice = createEntity(em);
    }

    @Test
    @Transactional
    void createBranchOffice() throws Exception {
        int databaseSizeBeforeCreate = branchOfficeRepository.findAll().size();
        // Create the BranchOffice
        BranchOfficeDTO branchOfficeDTO = branchOfficeMapper.toDto(branchOffice);
        restBranchOfficeMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchOfficeDTO))
            )
            .andExpect(status().isCreated());

        // Validate the BranchOffice in the database
        List<BranchOffice> branchOfficeList = branchOfficeRepository.findAll();
        assertThat(branchOfficeList).hasSize(databaseSizeBeforeCreate + 1);
        BranchOffice testBranchOffice = branchOfficeList.get(branchOfficeList.size() - 1);
        assertThat(testBranchOffice.getBranchName()).isEqualTo(DEFAULT_BRANCH_NAME);
        assertThat(testBranchOffice.getBranchGroupId()).isEqualTo(DEFAULT_BRANCH_GROUP_ID);
        assertThat(testBranchOffice.getNpi()).isEqualTo(DEFAULT_NPI);
        assertThat(testBranchOffice.getPtan()).isEqualTo(DEFAULT_PTAN);
        assertThat(testBranchOffice.getTaxonomyCode()).isEqualTo(DEFAULT_TAXONOMY_CODE);
        assertThat(testBranchOffice.getTaxonomyCodeDescription()).isEqualTo(DEFAULT_TAXONOMY_CODE_DESCRIPTION);
        assertThat(testBranchOffice.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testBranchOffice.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testBranchOffice.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testBranchOffice.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testBranchOffice.getTaxIdType()).isEqualTo(DEFAULT_TAX_ID_TYPE);
        assertThat(testBranchOffice.getTaxIdNo()).isEqualTo(DEFAULT_TAX_ID_NO);
        assertThat(testBranchOffice.getBranchNo()).isEqualTo(DEFAULT_BRANCH_NO);
        assertThat(testBranchOffice.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testBranchOffice.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testBranchOffice.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testBranchOffice.getBranchOfficeUuid()).isEqualTo(DEFAULT_BRANCH_OFFICE_UUID);
        assertThat(testBranchOffice.getBillingAddressLine1()).isEqualTo(DEFAULT_BILLING_ADDRESS_LINE_1);
        assertThat(testBranchOffice.getBillingAddressLine2()).isEqualTo(DEFAULT_BILLING_ADDRESS_LINE_2);
        assertThat(testBranchOffice.getBillingCity()).isEqualTo(DEFAULT_BILLING_CITY);
        assertThat(testBranchOffice.getBillingState()).isEqualTo(DEFAULT_BILLING_STATE);
        assertThat(testBranchOffice.getBillingZipCode()).isEqualTo(DEFAULT_BILLING_ZIP_CODE);
        assertThat(testBranchOffice.getSubmitterContactPersonName()).isEqualTo(DEFAULT_SUBMITTER_CONTACT_PERSON_NAME);
        assertThat(testBranchOffice.getSubmitterContactPhoneNo1()).isEqualTo(DEFAULT_SUBMITTER_CONTACT_PHONE_NO_1);
        assertThat(testBranchOffice.getSubmitterContactPhoneNo2()).isEqualTo(DEFAULT_SUBMITTER_CONTACT_PHONE_NO_2);
        assertThat(testBranchOffice.getBillingFax()).isEqualTo(DEFAULT_BILLING_FAX);
        assertThat(testBranchOffice.getContactEmail()).isEqualTo(DEFAULT_CONTACT_EMAIL);
        assertThat(testBranchOffice.getZip()).isEqualTo(DEFAULT_ZIP);
        assertThat(testBranchOffice.getBillingContactNo1()).isEqualTo(DEFAULT_BILLING_CONTACT_NO_1);
        assertThat(testBranchOffice.getBillingContactNo2()).isEqualTo(DEFAULT_BILLING_CONTACT_NO_2);
        assertThat(testBranchOffice.getBillingEmail()).isEqualTo(DEFAULT_BILLING_EMAIL);
        assertThat(testBranchOffice.getBranchCompany()).isEqualTo(DEFAULT_BRANCH_COMPANY);
        assertThat(testBranchOffice.getBranchGroupName()).isEqualTo(DEFAULT_BRANCH_GROUP_NAME);
        assertThat(testBranchOffice.getAddressLine1()).isEqualTo(DEFAULT_ADDRESS_LINE_1);
        assertThat(testBranchOffice.getAddressLine2()).isEqualTo(DEFAULT_ADDRESS_LINE_2);
        assertThat(testBranchOffice.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testBranchOffice.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testBranchOffice.getSignatureName()).isEqualTo(DEFAULT_SIGNATURE_NAME);
        assertThat(testBranchOffice.getItemLocationId()).isEqualTo(DEFAULT_ITEM_LOCATION_ID);
        assertThat(testBranchOffice.getItemLocationName()).isEqualTo(DEFAULT_ITEM_LOCATION_NAME);
        assertThat(testBranchOffice.getBranchCompanyId()).isEqualTo(DEFAULT_BRANCH_COMPANY_ID);
        assertThat(testBranchOffice.getIsDropshipAllowed()).isEqualTo(DEFAULT_IS_DROPSHIP_ALLOWED);
        assertThat(testBranchOffice.getBillingTaxonomyCode()).isEqualTo(DEFAULT_BILLING_TAXONOMY_CODE);
        assertThat(testBranchOffice.getBillingNpi()).isEqualTo(DEFAULT_BILLING_NPI);
        assertThat(testBranchOffice.getBillingOrganisationName()).isEqualTo(DEFAULT_BILLING_ORGANISATION_NAME);
        assertThat(testBranchOffice.getBillingTaxIdNo()).isEqualTo(DEFAULT_BILLING_TAX_ID_NO);
        assertThat(testBranchOffice.getBillingBranchName()).isEqualTo(DEFAULT_BILLING_BRANCH_NAME);
        assertThat(testBranchOffice.getFax()).isEqualTo(DEFAULT_FAX);
    }

    @Test
    @Transactional
    void createBranchOfficeWithExistingId() throws Exception {
        // Create the BranchOffice with an existing ID
        branchOffice.setBranchId(1L);
        BranchOfficeDTO branchOfficeDTO = branchOfficeMapper.toDto(branchOffice);

        int databaseSizeBeforeCreate = branchOfficeRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBranchOfficeMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchOfficeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchOffice in the database
        List<BranchOffice> branchOfficeList = branchOfficeRepository.findAll();
        assertThat(branchOfficeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBranchOffices() throws Exception {
        // Initialize the database
        branchOfficeRepository.saveAndFlush(branchOffice);

        // Get all the branchOfficeList
        restBranchOfficeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=branchId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].branchId").value(hasItem(branchOffice.getBranchId().intValue())))
            .andExpect(jsonPath("$.[*].branchName").value(hasItem(DEFAULT_BRANCH_NAME)))
            .andExpect(jsonPath("$.[*].branchGroupId").value(hasItem(DEFAULT_BRANCH_GROUP_ID.intValue())))
            .andExpect(jsonPath("$.[*].npi").value(hasItem(DEFAULT_NPI)))
            .andExpect(jsonPath("$.[*].ptan").value(hasItem(DEFAULT_PTAN)))
            .andExpect(jsonPath("$.[*].taxonomyCode").value(hasItem(DEFAULT_TAXONOMY_CODE)))
            .andExpect(jsonPath("$.[*].taxonomyCodeDescription").value(hasItem(DEFAULT_TAXONOMY_CODE_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].taxIdType").value(hasItem(DEFAULT_TAX_ID_TYPE)))
            .andExpect(jsonPath("$.[*].taxIdNo").value(hasItem(DEFAULT_TAX_ID_NO)))
            .andExpect(jsonPath("$.[*].branchNo").value(hasItem(DEFAULT_BRANCH_NO)))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].branchOfficeUuid").value(hasItem(DEFAULT_BRANCH_OFFICE_UUID.toString())))
            .andExpect(jsonPath("$.[*].billingAddressLine1").value(hasItem(DEFAULT_BILLING_ADDRESS_LINE_1)))
            .andExpect(jsonPath("$.[*].billingAddressLine2").value(hasItem(DEFAULT_BILLING_ADDRESS_LINE_2)))
            .andExpect(jsonPath("$.[*].billingCity").value(hasItem(DEFAULT_BILLING_CITY)))
            .andExpect(jsonPath("$.[*].billingState").value(hasItem(DEFAULT_BILLING_STATE)))
            .andExpect(jsonPath("$.[*].billingZipCode").value(hasItem(DEFAULT_BILLING_ZIP_CODE)))
            .andExpect(jsonPath("$.[*].submitterContactPersonName").value(hasItem(DEFAULT_SUBMITTER_CONTACT_PERSON_NAME)))
            .andExpect(jsonPath("$.[*].submitterContactPhoneNo1").value(hasItem(DEFAULT_SUBMITTER_CONTACT_PHONE_NO_1)))
            .andExpect(jsonPath("$.[*].submitterContactPhoneNo2").value(hasItem(DEFAULT_SUBMITTER_CONTACT_PHONE_NO_2)))
            .andExpect(jsonPath("$.[*].billingFax").value(hasItem(DEFAULT_BILLING_FAX)))
            .andExpect(jsonPath("$.[*].contactEmail").value(hasItem(DEFAULT_CONTACT_EMAIL)))
            .andExpect(jsonPath("$.[*].zip").value(hasItem(DEFAULT_ZIP)))
            .andExpect(jsonPath("$.[*].billingContactNo1").value(hasItem(DEFAULT_BILLING_CONTACT_NO_1)))
            .andExpect(jsonPath("$.[*].billingContactNo2").value(hasItem(DEFAULT_BILLING_CONTACT_NO_2)))
            .andExpect(jsonPath("$.[*].billingEmail").value(hasItem(DEFAULT_BILLING_EMAIL)))
            .andExpect(jsonPath("$.[*].branchCompany").value(hasItem(DEFAULT_BRANCH_COMPANY)))
            .andExpect(jsonPath("$.[*].branchGroupName").value(hasItem(DEFAULT_BRANCH_GROUP_NAME)))
            .andExpect(jsonPath("$.[*].addressLine1").value(hasItem(DEFAULT_ADDRESS_LINE_1)))
            .andExpect(jsonPath("$.[*].addressLine2").value(hasItem(DEFAULT_ADDRESS_LINE_2)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE)))
            .andExpect(jsonPath("$.[*].signatureName").value(hasItem(DEFAULT_SIGNATURE_NAME)))
            .andExpect(jsonPath("$.[*].itemLocationId").value(hasItem(DEFAULT_ITEM_LOCATION_ID)))
            .andExpect(jsonPath("$.[*].itemLocationName").value(hasItem(DEFAULT_ITEM_LOCATION_NAME)))
            .andExpect(jsonPath("$.[*].branchCompanyId").value(hasItem(DEFAULT_BRANCH_COMPANY_ID.intValue())))
            .andExpect(jsonPath("$.[*].isDropshipAllowed").value(hasItem(DEFAULT_IS_DROPSHIP_ALLOWED)))
            .andExpect(jsonPath("$.[*].billingTaxonomyCode").value(hasItem(DEFAULT_BILLING_TAXONOMY_CODE)))
            .andExpect(jsonPath("$.[*].billingNpi").value(hasItem(DEFAULT_BILLING_NPI)))
            .andExpect(jsonPath("$.[*].billingOrganisationName").value(hasItem(DEFAULT_BILLING_ORGANISATION_NAME)))
            .andExpect(jsonPath("$.[*].billingTaxIdNo").value(hasItem(DEFAULT_BILLING_TAX_ID_NO)))
            .andExpect(jsonPath("$.[*].billingBranchName").value(hasItem(DEFAULT_BILLING_BRANCH_NAME)))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX)));
    }

    @Test
    @Transactional
    void getBranchOffice() throws Exception {
        // Initialize the database
        branchOfficeRepository.saveAndFlush(branchOffice);

        // Get the branchOffice
        restBranchOfficeMockMvc
            .perform(get(ENTITY_API_URL_ID, branchOffice.getBranchId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.branchId").value(branchOffice.getBranchId().intValue()))
            .andExpect(jsonPath("$.branchName").value(DEFAULT_BRANCH_NAME))
            .andExpect(jsonPath("$.branchGroupId").value(DEFAULT_BRANCH_GROUP_ID.intValue()))
            .andExpect(jsonPath("$.npi").value(DEFAULT_NPI))
            .andExpect(jsonPath("$.ptan").value(DEFAULT_PTAN))
            .andExpect(jsonPath("$.taxonomyCode").value(DEFAULT_TAXONOMY_CODE))
            .andExpect(jsonPath("$.taxonomyCodeDescription").value(DEFAULT_TAXONOMY_CODE_DESCRIPTION))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.taxIdType").value(DEFAULT_TAX_ID_TYPE))
            .andExpect(jsonPath("$.taxIdNo").value(DEFAULT_TAX_ID_NO))
            .andExpect(jsonPath("$.branchNo").value(DEFAULT_BRANCH_NO))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.branchOfficeUuid").value(DEFAULT_BRANCH_OFFICE_UUID.toString()))
            .andExpect(jsonPath("$.billingAddressLine1").value(DEFAULT_BILLING_ADDRESS_LINE_1))
            .andExpect(jsonPath("$.billingAddressLine2").value(DEFAULT_BILLING_ADDRESS_LINE_2))
            .andExpect(jsonPath("$.billingCity").value(DEFAULT_BILLING_CITY))
            .andExpect(jsonPath("$.billingState").value(DEFAULT_BILLING_STATE))
            .andExpect(jsonPath("$.billingZipCode").value(DEFAULT_BILLING_ZIP_CODE))
            .andExpect(jsonPath("$.submitterContactPersonName").value(DEFAULT_SUBMITTER_CONTACT_PERSON_NAME))
            .andExpect(jsonPath("$.submitterContactPhoneNo1").value(DEFAULT_SUBMITTER_CONTACT_PHONE_NO_1))
            .andExpect(jsonPath("$.submitterContactPhoneNo2").value(DEFAULT_SUBMITTER_CONTACT_PHONE_NO_2))
            .andExpect(jsonPath("$.billingFax").value(DEFAULT_BILLING_FAX))
            .andExpect(jsonPath("$.contactEmail").value(DEFAULT_CONTACT_EMAIL))
            .andExpect(jsonPath("$.zip").value(DEFAULT_ZIP))
            .andExpect(jsonPath("$.billingContactNo1").value(DEFAULT_BILLING_CONTACT_NO_1))
            .andExpect(jsonPath("$.billingContactNo2").value(DEFAULT_BILLING_CONTACT_NO_2))
            .andExpect(jsonPath("$.billingEmail").value(DEFAULT_BILLING_EMAIL))
            .andExpect(jsonPath("$.branchCompany").value(DEFAULT_BRANCH_COMPANY))
            .andExpect(jsonPath("$.branchGroupName").value(DEFAULT_BRANCH_GROUP_NAME))
            .andExpect(jsonPath("$.addressLine1").value(DEFAULT_ADDRESS_LINE_1))
            .andExpect(jsonPath("$.addressLine2").value(DEFAULT_ADDRESS_LINE_2))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE))
            .andExpect(jsonPath("$.signatureName").value(DEFAULT_SIGNATURE_NAME))
            .andExpect(jsonPath("$.itemLocationId").value(DEFAULT_ITEM_LOCATION_ID))
            .andExpect(jsonPath("$.itemLocationName").value(DEFAULT_ITEM_LOCATION_NAME))
            .andExpect(jsonPath("$.branchCompanyId").value(DEFAULT_BRANCH_COMPANY_ID.intValue()))
            .andExpect(jsonPath("$.isDropshipAllowed").value(DEFAULT_IS_DROPSHIP_ALLOWED))
            .andExpect(jsonPath("$.billingTaxonomyCode").value(DEFAULT_BILLING_TAXONOMY_CODE))
            .andExpect(jsonPath("$.billingNpi").value(DEFAULT_BILLING_NPI))
            .andExpect(jsonPath("$.billingOrganisationName").value(DEFAULT_BILLING_ORGANISATION_NAME))
            .andExpect(jsonPath("$.billingTaxIdNo").value(DEFAULT_BILLING_TAX_ID_NO))
            .andExpect(jsonPath("$.billingBranchName").value(DEFAULT_BILLING_BRANCH_NAME))
            .andExpect(jsonPath("$.fax").value(DEFAULT_FAX));
    }

    @Test
    @Transactional
    void getNonExistingBranchOffice() throws Exception {
        // Get the branchOffice
        restBranchOfficeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingBranchOffice() throws Exception {
        // Initialize the database
        branchOfficeRepository.saveAndFlush(branchOffice);

        int databaseSizeBeforeUpdate = branchOfficeRepository.findAll().size();

        // Update the branchOffice
        BranchOffice updatedBranchOffice = branchOfficeRepository.findById(branchOffice.getBranchId()).get();
        // Disconnect from session so that the updates on updatedBranchOffice are not directly saved in db
        em.detach(updatedBranchOffice);
        updatedBranchOffice
            .branchName(UPDATED_BRANCH_NAME)
            .branchGroupId(UPDATED_BRANCH_GROUP_ID)
            .npi(UPDATED_NPI)
            .ptan(UPDATED_PTAN)
            .taxonomyCode(UPDATED_TAXONOMY_CODE)
            .taxonomyCodeDescription(UPDATED_TAXONOMY_CODE_DESCRIPTION)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .taxIdType(UPDATED_TAX_ID_TYPE)
            .taxIdNo(UPDATED_TAX_ID_NO)
            .branchNo(UPDATED_BRANCH_NO)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .branchOfficeUuid(UPDATED_BRANCH_OFFICE_UUID)
            .billingAddressLine1(UPDATED_BILLING_ADDRESS_LINE_1)
            .billingAddressLine2(UPDATED_BILLING_ADDRESS_LINE_2)
            .billingCity(UPDATED_BILLING_CITY)
            .billingState(UPDATED_BILLING_STATE)
            .billingZipCode(UPDATED_BILLING_ZIP_CODE)
            .submitterContactPersonName(UPDATED_SUBMITTER_CONTACT_PERSON_NAME)
            .submitterContactPhoneNo1(UPDATED_SUBMITTER_CONTACT_PHONE_NO_1)
            .submitterContactPhoneNo2(UPDATED_SUBMITTER_CONTACT_PHONE_NO_2)
            .billingFax(UPDATED_BILLING_FAX)
            .contactEmail(UPDATED_CONTACT_EMAIL)
            .zip(UPDATED_ZIP)
            .billingContactNo1(UPDATED_BILLING_CONTACT_NO_1)
            .billingContactNo2(UPDATED_BILLING_CONTACT_NO_2)
            .billingEmail(UPDATED_BILLING_EMAIL)
            .branchCompany(UPDATED_BRANCH_COMPANY)
            .branchGroupName(UPDATED_BRANCH_GROUP_NAME)
            .addressLine1(UPDATED_ADDRESS_LINE_1)
            .addressLine2(UPDATED_ADDRESS_LINE_2)
            .city(UPDATED_CITY)
            .state(UPDATED_STATE)
            .signatureName(UPDATED_SIGNATURE_NAME)
            .itemLocationId(UPDATED_ITEM_LOCATION_ID)
            .itemLocationName(UPDATED_ITEM_LOCATION_NAME)
            .branchCompanyId(UPDATED_BRANCH_COMPANY_ID)
            .isDropshipAllowed(UPDATED_IS_DROPSHIP_ALLOWED)
            .billingTaxonomyCode(UPDATED_BILLING_TAXONOMY_CODE)
            .billingNpi(UPDATED_BILLING_NPI)
            .billingOrganisationName(UPDATED_BILLING_ORGANISATION_NAME)
            .billingTaxIdNo(UPDATED_BILLING_TAX_ID_NO)
            .billingBranchName(UPDATED_BILLING_BRANCH_NAME)
            .fax(UPDATED_FAX);
        BranchOfficeDTO branchOfficeDTO = branchOfficeMapper.toDto(updatedBranchOffice);

        restBranchOfficeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, branchOfficeDTO.getBranchId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchOfficeDTO))
            )
            .andExpect(status().isOk());

        // Validate the BranchOffice in the database
        List<BranchOffice> branchOfficeList = branchOfficeRepository.findAll();
        assertThat(branchOfficeList).hasSize(databaseSizeBeforeUpdate);
        BranchOffice testBranchOffice = branchOfficeList.get(branchOfficeList.size() - 1);
        assertThat(testBranchOffice.getBranchName()).isEqualTo(UPDATED_BRANCH_NAME);
        assertThat(testBranchOffice.getBranchGroupId()).isEqualTo(UPDATED_BRANCH_GROUP_ID);
        assertThat(testBranchOffice.getNpi()).isEqualTo(UPDATED_NPI);
        assertThat(testBranchOffice.getPtan()).isEqualTo(UPDATED_PTAN);
        assertThat(testBranchOffice.getTaxonomyCode()).isEqualTo(UPDATED_TAXONOMY_CODE);
        assertThat(testBranchOffice.getTaxonomyCodeDescription()).isEqualTo(UPDATED_TAXONOMY_CODE_DESCRIPTION);
        assertThat(testBranchOffice.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testBranchOffice.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testBranchOffice.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBranchOffice.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testBranchOffice.getTaxIdType()).isEqualTo(UPDATED_TAX_ID_TYPE);
        assertThat(testBranchOffice.getTaxIdNo()).isEqualTo(UPDATED_TAX_ID_NO);
        assertThat(testBranchOffice.getBranchNo()).isEqualTo(UPDATED_BRANCH_NO);
        assertThat(testBranchOffice.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testBranchOffice.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testBranchOffice.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testBranchOffice.getBranchOfficeUuid()).isEqualTo(UPDATED_BRANCH_OFFICE_UUID);
        assertThat(testBranchOffice.getBillingAddressLine1()).isEqualTo(UPDATED_BILLING_ADDRESS_LINE_1);
        assertThat(testBranchOffice.getBillingAddressLine2()).isEqualTo(UPDATED_BILLING_ADDRESS_LINE_2);
        assertThat(testBranchOffice.getBillingCity()).isEqualTo(UPDATED_BILLING_CITY);
        assertThat(testBranchOffice.getBillingState()).isEqualTo(UPDATED_BILLING_STATE);
        assertThat(testBranchOffice.getBillingZipCode()).isEqualTo(UPDATED_BILLING_ZIP_CODE);
        assertThat(testBranchOffice.getSubmitterContactPersonName()).isEqualTo(UPDATED_SUBMITTER_CONTACT_PERSON_NAME);
        assertThat(testBranchOffice.getSubmitterContactPhoneNo1()).isEqualTo(UPDATED_SUBMITTER_CONTACT_PHONE_NO_1);
        assertThat(testBranchOffice.getSubmitterContactPhoneNo2()).isEqualTo(UPDATED_SUBMITTER_CONTACT_PHONE_NO_2);
        assertThat(testBranchOffice.getBillingFax()).isEqualTo(UPDATED_BILLING_FAX);
        assertThat(testBranchOffice.getContactEmail()).isEqualTo(UPDATED_CONTACT_EMAIL);
        assertThat(testBranchOffice.getZip()).isEqualTo(UPDATED_ZIP);
        assertThat(testBranchOffice.getBillingContactNo1()).isEqualTo(UPDATED_BILLING_CONTACT_NO_1);
        assertThat(testBranchOffice.getBillingContactNo2()).isEqualTo(UPDATED_BILLING_CONTACT_NO_2);
        assertThat(testBranchOffice.getBillingEmail()).isEqualTo(UPDATED_BILLING_EMAIL);
        assertThat(testBranchOffice.getBranchCompany()).isEqualTo(UPDATED_BRANCH_COMPANY);
        assertThat(testBranchOffice.getBranchGroupName()).isEqualTo(UPDATED_BRANCH_GROUP_NAME);
        assertThat(testBranchOffice.getAddressLine1()).isEqualTo(UPDATED_ADDRESS_LINE_1);
        assertThat(testBranchOffice.getAddressLine2()).isEqualTo(UPDATED_ADDRESS_LINE_2);
        assertThat(testBranchOffice.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testBranchOffice.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testBranchOffice.getSignatureName()).isEqualTo(UPDATED_SIGNATURE_NAME);
        assertThat(testBranchOffice.getItemLocationId()).isEqualTo(UPDATED_ITEM_LOCATION_ID);
        assertThat(testBranchOffice.getItemLocationName()).isEqualTo(UPDATED_ITEM_LOCATION_NAME);
        assertThat(testBranchOffice.getBranchCompanyId()).isEqualTo(UPDATED_BRANCH_COMPANY_ID);
        assertThat(testBranchOffice.getIsDropshipAllowed()).isEqualTo(UPDATED_IS_DROPSHIP_ALLOWED);
        assertThat(testBranchOffice.getBillingTaxonomyCode()).isEqualTo(UPDATED_BILLING_TAXONOMY_CODE);
        assertThat(testBranchOffice.getBillingNpi()).isEqualTo(UPDATED_BILLING_NPI);
        assertThat(testBranchOffice.getBillingOrganisationName()).isEqualTo(UPDATED_BILLING_ORGANISATION_NAME);
        assertThat(testBranchOffice.getBillingTaxIdNo()).isEqualTo(UPDATED_BILLING_TAX_ID_NO);
        assertThat(testBranchOffice.getBillingBranchName()).isEqualTo(UPDATED_BILLING_BRANCH_NAME);
        assertThat(testBranchOffice.getFax()).isEqualTo(UPDATED_FAX);
    }

    @Test
    @Transactional
    void putNonExistingBranchOffice() throws Exception {
        int databaseSizeBeforeUpdate = branchOfficeRepository.findAll().size();
        branchOffice.setBranchId(count.incrementAndGet());

        // Create the BranchOffice
        BranchOfficeDTO branchOfficeDTO = branchOfficeMapper.toDto(branchOffice);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBranchOfficeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, branchOfficeDTO.getBranchId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchOfficeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchOffice in the database
        List<BranchOffice> branchOfficeList = branchOfficeRepository.findAll();
        assertThat(branchOfficeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBranchOffice() throws Exception {
        int databaseSizeBeforeUpdate = branchOfficeRepository.findAll().size();
        branchOffice.setBranchId(count.incrementAndGet());

        // Create the BranchOffice
        BranchOfficeDTO branchOfficeDTO = branchOfficeMapper.toDto(branchOffice);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchOfficeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchOfficeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchOffice in the database
        List<BranchOffice> branchOfficeList = branchOfficeRepository.findAll();
        assertThat(branchOfficeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBranchOffice() throws Exception {
        int databaseSizeBeforeUpdate = branchOfficeRepository.findAll().size();
        branchOffice.setBranchId(count.incrementAndGet());

        // Create the BranchOffice
        BranchOfficeDTO branchOfficeDTO = branchOfficeMapper.toDto(branchOffice);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchOfficeMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchOfficeDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BranchOffice in the database
        List<BranchOffice> branchOfficeList = branchOfficeRepository.findAll();
        assertThat(branchOfficeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBranchOfficeWithPatch() throws Exception {
        // Initialize the database
        branchOfficeRepository.saveAndFlush(branchOffice);

        int databaseSizeBeforeUpdate = branchOfficeRepository.findAll().size();

        // Update the branchOffice using partial update
        BranchOffice partialUpdatedBranchOffice = new BranchOffice();
        partialUpdatedBranchOffice.setBranchId(branchOffice.getBranchId());

        partialUpdatedBranchOffice
            .branchName(UPDATED_BRANCH_NAME)
            .ptan(UPDATED_PTAN)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .branchOfficeUuid(UPDATED_BRANCH_OFFICE_UUID)
            .billingAddressLine2(UPDATED_BILLING_ADDRESS_LINE_2)
            .billingCity(UPDATED_BILLING_CITY)
            .billingZipCode(UPDATED_BILLING_ZIP_CODE)
            .submitterContactPhoneNo1(UPDATED_SUBMITTER_CONTACT_PHONE_NO_1)
            .submitterContactPhoneNo2(UPDATED_SUBMITTER_CONTACT_PHONE_NO_2)
            .zip(UPDATED_ZIP)
            .billingContactNo2(UPDATED_BILLING_CONTACT_NO_2)
            .addressLine2(UPDATED_ADDRESS_LINE_2)
            .city(UPDATED_CITY)
            .state(UPDATED_STATE)
            .itemLocationName(UPDATED_ITEM_LOCATION_NAME)
            .branchCompanyId(UPDATED_BRANCH_COMPANY_ID)
            .isDropshipAllowed(UPDATED_IS_DROPSHIP_ALLOWED)
            .billingTaxonomyCode(UPDATED_BILLING_TAXONOMY_CODE)
            .billingTaxIdNo(UPDATED_BILLING_TAX_ID_NO);

        restBranchOfficeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBranchOffice.getBranchId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBranchOffice))
            )
            .andExpect(status().isOk());

        // Validate the BranchOffice in the database
        List<BranchOffice> branchOfficeList = branchOfficeRepository.findAll();
        assertThat(branchOfficeList).hasSize(databaseSizeBeforeUpdate);
        BranchOffice testBranchOffice = branchOfficeList.get(branchOfficeList.size() - 1);
        assertThat(testBranchOffice.getBranchName()).isEqualTo(UPDATED_BRANCH_NAME);
        assertThat(testBranchOffice.getBranchGroupId()).isEqualTo(DEFAULT_BRANCH_GROUP_ID);
        assertThat(testBranchOffice.getNpi()).isEqualTo(DEFAULT_NPI);
        assertThat(testBranchOffice.getPtan()).isEqualTo(UPDATED_PTAN);
        assertThat(testBranchOffice.getTaxonomyCode()).isEqualTo(DEFAULT_TAXONOMY_CODE);
        assertThat(testBranchOffice.getTaxonomyCodeDescription()).isEqualTo(DEFAULT_TAXONOMY_CODE_DESCRIPTION);
        assertThat(testBranchOffice.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testBranchOffice.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testBranchOffice.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBranchOffice.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testBranchOffice.getTaxIdType()).isEqualTo(DEFAULT_TAX_ID_TYPE);
        assertThat(testBranchOffice.getTaxIdNo()).isEqualTo(DEFAULT_TAX_ID_NO);
        assertThat(testBranchOffice.getBranchNo()).isEqualTo(DEFAULT_BRANCH_NO);
        assertThat(testBranchOffice.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testBranchOffice.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testBranchOffice.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testBranchOffice.getBranchOfficeUuid()).isEqualTo(UPDATED_BRANCH_OFFICE_UUID);
        assertThat(testBranchOffice.getBillingAddressLine1()).isEqualTo(DEFAULT_BILLING_ADDRESS_LINE_1);
        assertThat(testBranchOffice.getBillingAddressLine2()).isEqualTo(UPDATED_BILLING_ADDRESS_LINE_2);
        assertThat(testBranchOffice.getBillingCity()).isEqualTo(UPDATED_BILLING_CITY);
        assertThat(testBranchOffice.getBillingState()).isEqualTo(DEFAULT_BILLING_STATE);
        assertThat(testBranchOffice.getBillingZipCode()).isEqualTo(UPDATED_BILLING_ZIP_CODE);
        assertThat(testBranchOffice.getSubmitterContactPersonName()).isEqualTo(DEFAULT_SUBMITTER_CONTACT_PERSON_NAME);
        assertThat(testBranchOffice.getSubmitterContactPhoneNo1()).isEqualTo(UPDATED_SUBMITTER_CONTACT_PHONE_NO_1);
        assertThat(testBranchOffice.getSubmitterContactPhoneNo2()).isEqualTo(UPDATED_SUBMITTER_CONTACT_PHONE_NO_2);
        assertThat(testBranchOffice.getBillingFax()).isEqualTo(DEFAULT_BILLING_FAX);
        assertThat(testBranchOffice.getContactEmail()).isEqualTo(DEFAULT_CONTACT_EMAIL);
        assertThat(testBranchOffice.getZip()).isEqualTo(UPDATED_ZIP);
        assertThat(testBranchOffice.getBillingContactNo1()).isEqualTo(DEFAULT_BILLING_CONTACT_NO_1);
        assertThat(testBranchOffice.getBillingContactNo2()).isEqualTo(UPDATED_BILLING_CONTACT_NO_2);
        assertThat(testBranchOffice.getBillingEmail()).isEqualTo(DEFAULT_BILLING_EMAIL);
        assertThat(testBranchOffice.getBranchCompany()).isEqualTo(DEFAULT_BRANCH_COMPANY);
        assertThat(testBranchOffice.getBranchGroupName()).isEqualTo(DEFAULT_BRANCH_GROUP_NAME);
        assertThat(testBranchOffice.getAddressLine1()).isEqualTo(DEFAULT_ADDRESS_LINE_1);
        assertThat(testBranchOffice.getAddressLine2()).isEqualTo(UPDATED_ADDRESS_LINE_2);
        assertThat(testBranchOffice.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testBranchOffice.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testBranchOffice.getSignatureName()).isEqualTo(DEFAULT_SIGNATURE_NAME);
        assertThat(testBranchOffice.getItemLocationId()).isEqualTo(DEFAULT_ITEM_LOCATION_ID);
        assertThat(testBranchOffice.getItemLocationName()).isEqualTo(UPDATED_ITEM_LOCATION_NAME);
        assertThat(testBranchOffice.getBranchCompanyId()).isEqualTo(UPDATED_BRANCH_COMPANY_ID);
        assertThat(testBranchOffice.getIsDropshipAllowed()).isEqualTo(UPDATED_IS_DROPSHIP_ALLOWED);
        assertThat(testBranchOffice.getBillingTaxonomyCode()).isEqualTo(UPDATED_BILLING_TAXONOMY_CODE);
        assertThat(testBranchOffice.getBillingNpi()).isEqualTo(DEFAULT_BILLING_NPI);
        assertThat(testBranchOffice.getBillingOrganisationName()).isEqualTo(DEFAULT_BILLING_ORGANISATION_NAME);
        assertThat(testBranchOffice.getBillingTaxIdNo()).isEqualTo(UPDATED_BILLING_TAX_ID_NO);
        assertThat(testBranchOffice.getBillingBranchName()).isEqualTo(DEFAULT_BILLING_BRANCH_NAME);
        assertThat(testBranchOffice.getFax()).isEqualTo(DEFAULT_FAX);
    }

    @Test
    @Transactional
    void fullUpdateBranchOfficeWithPatch() throws Exception {
        // Initialize the database
        branchOfficeRepository.saveAndFlush(branchOffice);

        int databaseSizeBeforeUpdate = branchOfficeRepository.findAll().size();

        // Update the branchOffice using partial update
        BranchOffice partialUpdatedBranchOffice = new BranchOffice();
        partialUpdatedBranchOffice.setBranchId(branchOffice.getBranchId());

        partialUpdatedBranchOffice
            .branchName(UPDATED_BRANCH_NAME)
            .branchGroupId(UPDATED_BRANCH_GROUP_ID)
            .npi(UPDATED_NPI)
            .ptan(UPDATED_PTAN)
            .taxonomyCode(UPDATED_TAXONOMY_CODE)
            .taxonomyCodeDescription(UPDATED_TAXONOMY_CODE_DESCRIPTION)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .taxIdType(UPDATED_TAX_ID_TYPE)
            .taxIdNo(UPDATED_TAX_ID_NO)
            .branchNo(UPDATED_BRANCH_NO)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .branchOfficeUuid(UPDATED_BRANCH_OFFICE_UUID)
            .billingAddressLine1(UPDATED_BILLING_ADDRESS_LINE_1)
            .billingAddressLine2(UPDATED_BILLING_ADDRESS_LINE_2)
            .billingCity(UPDATED_BILLING_CITY)
            .billingState(UPDATED_BILLING_STATE)
            .billingZipCode(UPDATED_BILLING_ZIP_CODE)
            .submitterContactPersonName(UPDATED_SUBMITTER_CONTACT_PERSON_NAME)
            .submitterContactPhoneNo1(UPDATED_SUBMITTER_CONTACT_PHONE_NO_1)
            .submitterContactPhoneNo2(UPDATED_SUBMITTER_CONTACT_PHONE_NO_2)
            .billingFax(UPDATED_BILLING_FAX)
            .contactEmail(UPDATED_CONTACT_EMAIL)
            .zip(UPDATED_ZIP)
            .billingContactNo1(UPDATED_BILLING_CONTACT_NO_1)
            .billingContactNo2(UPDATED_BILLING_CONTACT_NO_2)
            .billingEmail(UPDATED_BILLING_EMAIL)
            .branchCompany(UPDATED_BRANCH_COMPANY)
            .branchGroupName(UPDATED_BRANCH_GROUP_NAME)
            .addressLine1(UPDATED_ADDRESS_LINE_1)
            .addressLine2(UPDATED_ADDRESS_LINE_2)
            .city(UPDATED_CITY)
            .state(UPDATED_STATE)
            .signatureName(UPDATED_SIGNATURE_NAME)
            .itemLocationId(UPDATED_ITEM_LOCATION_ID)
            .itemLocationName(UPDATED_ITEM_LOCATION_NAME)
            .branchCompanyId(UPDATED_BRANCH_COMPANY_ID)
            .isDropshipAllowed(UPDATED_IS_DROPSHIP_ALLOWED)
            .billingTaxonomyCode(UPDATED_BILLING_TAXONOMY_CODE)
            .billingNpi(UPDATED_BILLING_NPI)
            .billingOrganisationName(UPDATED_BILLING_ORGANISATION_NAME)
            .billingTaxIdNo(UPDATED_BILLING_TAX_ID_NO)
            .billingBranchName(UPDATED_BILLING_BRANCH_NAME)
            .fax(UPDATED_FAX);

        restBranchOfficeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBranchOffice.getBranchId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBranchOffice))
            )
            .andExpect(status().isOk());

        // Validate the BranchOffice in the database
        List<BranchOffice> branchOfficeList = branchOfficeRepository.findAll();
        assertThat(branchOfficeList).hasSize(databaseSizeBeforeUpdate);
        BranchOffice testBranchOffice = branchOfficeList.get(branchOfficeList.size() - 1);
        assertThat(testBranchOffice.getBranchName()).isEqualTo(UPDATED_BRANCH_NAME);
        assertThat(testBranchOffice.getBranchGroupId()).isEqualTo(UPDATED_BRANCH_GROUP_ID);
        assertThat(testBranchOffice.getNpi()).isEqualTo(UPDATED_NPI);
        assertThat(testBranchOffice.getPtan()).isEqualTo(UPDATED_PTAN);
        assertThat(testBranchOffice.getTaxonomyCode()).isEqualTo(UPDATED_TAXONOMY_CODE);
        assertThat(testBranchOffice.getTaxonomyCodeDescription()).isEqualTo(UPDATED_TAXONOMY_CODE_DESCRIPTION);
        assertThat(testBranchOffice.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testBranchOffice.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testBranchOffice.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBranchOffice.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testBranchOffice.getTaxIdType()).isEqualTo(UPDATED_TAX_ID_TYPE);
        assertThat(testBranchOffice.getTaxIdNo()).isEqualTo(UPDATED_TAX_ID_NO);
        assertThat(testBranchOffice.getBranchNo()).isEqualTo(UPDATED_BRANCH_NO);
        assertThat(testBranchOffice.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testBranchOffice.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testBranchOffice.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testBranchOffice.getBranchOfficeUuid()).isEqualTo(UPDATED_BRANCH_OFFICE_UUID);
        assertThat(testBranchOffice.getBillingAddressLine1()).isEqualTo(UPDATED_BILLING_ADDRESS_LINE_1);
        assertThat(testBranchOffice.getBillingAddressLine2()).isEqualTo(UPDATED_BILLING_ADDRESS_LINE_2);
        assertThat(testBranchOffice.getBillingCity()).isEqualTo(UPDATED_BILLING_CITY);
        assertThat(testBranchOffice.getBillingState()).isEqualTo(UPDATED_BILLING_STATE);
        assertThat(testBranchOffice.getBillingZipCode()).isEqualTo(UPDATED_BILLING_ZIP_CODE);
        assertThat(testBranchOffice.getSubmitterContactPersonName()).isEqualTo(UPDATED_SUBMITTER_CONTACT_PERSON_NAME);
        assertThat(testBranchOffice.getSubmitterContactPhoneNo1()).isEqualTo(UPDATED_SUBMITTER_CONTACT_PHONE_NO_1);
        assertThat(testBranchOffice.getSubmitterContactPhoneNo2()).isEqualTo(UPDATED_SUBMITTER_CONTACT_PHONE_NO_2);
        assertThat(testBranchOffice.getBillingFax()).isEqualTo(UPDATED_BILLING_FAX);
        assertThat(testBranchOffice.getContactEmail()).isEqualTo(UPDATED_CONTACT_EMAIL);
        assertThat(testBranchOffice.getZip()).isEqualTo(UPDATED_ZIP);
        assertThat(testBranchOffice.getBillingContactNo1()).isEqualTo(UPDATED_BILLING_CONTACT_NO_1);
        assertThat(testBranchOffice.getBillingContactNo2()).isEqualTo(UPDATED_BILLING_CONTACT_NO_2);
        assertThat(testBranchOffice.getBillingEmail()).isEqualTo(UPDATED_BILLING_EMAIL);
        assertThat(testBranchOffice.getBranchCompany()).isEqualTo(UPDATED_BRANCH_COMPANY);
        assertThat(testBranchOffice.getBranchGroupName()).isEqualTo(UPDATED_BRANCH_GROUP_NAME);
        assertThat(testBranchOffice.getAddressLine1()).isEqualTo(UPDATED_ADDRESS_LINE_1);
        assertThat(testBranchOffice.getAddressLine2()).isEqualTo(UPDATED_ADDRESS_LINE_2);
        assertThat(testBranchOffice.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testBranchOffice.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testBranchOffice.getSignatureName()).isEqualTo(UPDATED_SIGNATURE_NAME);
        assertThat(testBranchOffice.getItemLocationId()).isEqualTo(UPDATED_ITEM_LOCATION_ID);
        assertThat(testBranchOffice.getItemLocationName()).isEqualTo(UPDATED_ITEM_LOCATION_NAME);
        assertThat(testBranchOffice.getBranchCompanyId()).isEqualTo(UPDATED_BRANCH_COMPANY_ID);
        assertThat(testBranchOffice.getIsDropshipAllowed()).isEqualTo(UPDATED_IS_DROPSHIP_ALLOWED);
        assertThat(testBranchOffice.getBillingTaxonomyCode()).isEqualTo(UPDATED_BILLING_TAXONOMY_CODE);
        assertThat(testBranchOffice.getBillingNpi()).isEqualTo(UPDATED_BILLING_NPI);
        assertThat(testBranchOffice.getBillingOrganisationName()).isEqualTo(UPDATED_BILLING_ORGANISATION_NAME);
        assertThat(testBranchOffice.getBillingTaxIdNo()).isEqualTo(UPDATED_BILLING_TAX_ID_NO);
        assertThat(testBranchOffice.getBillingBranchName()).isEqualTo(UPDATED_BILLING_BRANCH_NAME);
        assertThat(testBranchOffice.getFax()).isEqualTo(UPDATED_FAX);
    }

    @Test
    @Transactional
    void patchNonExistingBranchOffice() throws Exception {
        int databaseSizeBeforeUpdate = branchOfficeRepository.findAll().size();
        branchOffice.setBranchId(count.incrementAndGet());

        // Create the BranchOffice
        BranchOfficeDTO branchOfficeDTO = branchOfficeMapper.toDto(branchOffice);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBranchOfficeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, branchOfficeDTO.getBranchId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(branchOfficeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchOffice in the database
        List<BranchOffice> branchOfficeList = branchOfficeRepository.findAll();
        assertThat(branchOfficeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBranchOffice() throws Exception {
        int databaseSizeBeforeUpdate = branchOfficeRepository.findAll().size();
        branchOffice.setBranchId(count.incrementAndGet());

        // Create the BranchOffice
        BranchOfficeDTO branchOfficeDTO = branchOfficeMapper.toDto(branchOffice);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchOfficeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(branchOfficeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchOffice in the database
        List<BranchOffice> branchOfficeList = branchOfficeRepository.findAll();
        assertThat(branchOfficeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBranchOffice() throws Exception {
        int databaseSizeBeforeUpdate = branchOfficeRepository.findAll().size();
        branchOffice.setBranchId(count.incrementAndGet());

        // Create the BranchOffice
        BranchOfficeDTO branchOfficeDTO = branchOfficeMapper.toDto(branchOffice);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchOfficeMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(branchOfficeDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BranchOffice in the database
        List<BranchOffice> branchOfficeList = branchOfficeRepository.findAll();
        assertThat(branchOfficeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBranchOffice() throws Exception {
        // Initialize the database
        branchOfficeRepository.saveAndFlush(branchOffice);

        int databaseSizeBeforeDelete = branchOfficeRepository.findAll().size();

        // Delete the branchOffice
        restBranchOfficeMockMvc
            .perform(delete(ENTITY_API_URL_ID, branchOffice.getBranchId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BranchOffice> branchOfficeList = branchOfficeRepository.findAll();
        assertThat(branchOfficeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
