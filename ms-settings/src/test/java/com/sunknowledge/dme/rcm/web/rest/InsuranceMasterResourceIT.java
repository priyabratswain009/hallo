package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.InsuranceMaster;
import com.sunknowledge.dme.rcm.repository.InsuranceMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.InsuranceMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.InsuranceMasterMapper;
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
 * Integration tests for the {@link InsuranceMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class InsuranceMasterResourceIT {

    private static final String DEFAULT_INSURANCE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INSURANCE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_INSURANCE_PLAN_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_INSURANCE_PLAN_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_TAX_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TAX_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_TAX_INCLUDED_ALLOWABLE_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_TAX_INCLUDED_ALLOWABLE_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_INSURANCE_GROUP_ID = 1L;
    private static final Long UPDATED_INSURANCE_GROUP_ID = 2L;

    private static final Long DEFAULT_PRICE_TABLE_ID = 1L;
    private static final Long UPDATED_PRICE_TABLE_ID = 2L;

    private static final String DEFAULT_CLAIM_TYPE_DME_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_CLAIM_TYPE_DME_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_CLAIM_TYPE_MAJOR_MADICAL_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_CLAIM_TYPE_MAJOR_MADICAL_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_CLAIM_TYPE_PHARMACY_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_CLAIM_TYPE_PHARMACY_STATUS = "BBBBBBBBBB";

    private static final Double DEFAULT_PAY_PERCENTAGE = 1D;
    private static final Double UPDATED_PAY_PERCENTAGE = 2D;

    private static final String DEFAULT_ENABLE_SECONDARY_BILLING_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_ENABLE_SECONDARY_BILLING_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_AMT_PRINT_ON_DELIVERY_TICKET_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_AMT_PRINT_ON_DELIVERY_TICKET_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_MEDIGAP_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_MEDIGAP_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_MEDIGAP_NUM = 1L;
    private static final Long UPDATED_MEDIGAP_NUM = 2L;

    private static final String DEFAULT_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_NOTES = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_INSURANCE_MASTER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_INSURANCE_MASTER_UUID = UUID.randomUUID();

    private static final String DEFAULT_INSURANCE_PAYER_ID_NO = "AAAAAAAAAA";
    private static final String UPDATED_INSURANCE_PAYER_ID_NO = "BBBBBBBBBB";

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

    private static final String DEFAULT_CLAIM_PROGRAM_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CLAIM_PROGRAM_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CLAIM_PROGRAM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CLAIM_PROGRAM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_INSURANCE_ID_NO = "AAAAAAAAAA";
    private static final String UPDATED_INSURANCE_ID_NO = "BBBBBBBBBB";

    private static final String DEFAULT_INSURANCE_GROUP_NO = "AAAAAAAAAA";
    private static final String UPDATED_INSURANCE_GROUP_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PRICE_TABLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PRICE_TABLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CLAIM_PROGRAM_ID = "AAAAAAAAAA";
    private static final String UPDATED_CLAIM_PROGRAM_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CLAIM_FORM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CLAIM_FORM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_INSURANCE_GROUP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INSURANCE_GROUP_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_PERSON_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_PERSON_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_PERSON_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_PERSON_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_PERSON_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_PERSON_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TAX_TNCLUDED_ALLOWABLE_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_TAX_TNCLUDED_ALLOWABLE_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_CMS_CROSSOVER_INSURANCE_ID_NO = "AAAAAAAAAA";
    private static final String UPDATED_CMS_CROSSOVER_INSURANCE_ID_NO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/insurance-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{insuranceId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private InsuranceMasterRepository insuranceMasterRepository;

    @Autowired
    private InsuranceMasterMapper insuranceMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInsuranceMasterMockMvc;

    private InsuranceMaster insuranceMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InsuranceMaster createEntity(EntityManager em) {
        InsuranceMaster insuranceMaster = new InsuranceMaster()
            .insuranceName(DEFAULT_INSURANCE_NAME)
            .insurancePlanType(DEFAULT_INSURANCE_PLAN_TYPE)
            .taxType(DEFAULT_TAX_TYPE)
            .taxIncludedAllowableStatus(DEFAULT_TAX_INCLUDED_ALLOWABLE_STATUS)
            .insuranceGroupId(DEFAULT_INSURANCE_GROUP_ID)
            .priceTableId(DEFAULT_PRICE_TABLE_ID)
            .claimTypeDmeStatus(DEFAULT_CLAIM_TYPE_DME_STATUS)
            .claimTypeMajorMadicalStatus(DEFAULT_CLAIM_TYPE_MAJOR_MADICAL_STATUS)
            .claimTypePharmacyStatus(DEFAULT_CLAIM_TYPE_PHARMACY_STATUS)
            .payPercentage(DEFAULT_PAY_PERCENTAGE)
            .enableSecondaryBillingStatus(DEFAULT_ENABLE_SECONDARY_BILLING_STATUS)
            .amtPrintOnDeliveryTicketStatus(DEFAULT_AMT_PRINT_ON_DELIVERY_TICKET_STATUS)
            .medigapStatus(DEFAULT_MEDIGAP_STATUS)
            .medigapNum(DEFAULT_MEDIGAP_NUM)
            .notes(DEFAULT_NOTES)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .insuranceMasterUuid(DEFAULT_INSURANCE_MASTER_UUID)
            .insurancePayerIdNo(DEFAULT_INSURANCE_PAYER_ID_NO)
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
            .claimProgramCode(DEFAULT_CLAIM_PROGRAM_CODE)
            .claimProgramName(DEFAULT_CLAIM_PROGRAM_NAME)
            .insuranceIdNo(DEFAULT_INSURANCE_ID_NO)
            .insuranceGroupNo(DEFAULT_INSURANCE_GROUP_NO)
            .priceTableName(DEFAULT_PRICE_TABLE_NAME)
            .claimProgramId(DEFAULT_CLAIM_PROGRAM_ID)
            .claimFormName(DEFAULT_CLAIM_FORM_NAME)
            .insuranceGroupName(DEFAULT_INSURANCE_GROUP_NAME)
            .contactPersonFirstName(DEFAULT_CONTACT_PERSON_FIRST_NAME)
            .contactPersonMiddleName(DEFAULT_CONTACT_PERSON_MIDDLE_NAME)
            .contactPersonLastName(DEFAULT_CONTACT_PERSON_LAST_NAME)
            .taxTncludedAllowableStatus(DEFAULT_TAX_TNCLUDED_ALLOWABLE_STATUS)
            .cmsCrossoverInsuranceIdNo(DEFAULT_CMS_CROSSOVER_INSURANCE_ID_NO);
        return insuranceMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InsuranceMaster createUpdatedEntity(EntityManager em) {
        InsuranceMaster insuranceMaster = new InsuranceMaster()
            .insuranceName(UPDATED_INSURANCE_NAME)
            .insurancePlanType(UPDATED_INSURANCE_PLAN_TYPE)
            .taxType(UPDATED_TAX_TYPE)
            .taxIncludedAllowableStatus(UPDATED_TAX_INCLUDED_ALLOWABLE_STATUS)
            .insuranceGroupId(UPDATED_INSURANCE_GROUP_ID)
            .priceTableId(UPDATED_PRICE_TABLE_ID)
            .claimTypeDmeStatus(UPDATED_CLAIM_TYPE_DME_STATUS)
            .claimTypeMajorMadicalStatus(UPDATED_CLAIM_TYPE_MAJOR_MADICAL_STATUS)
            .claimTypePharmacyStatus(UPDATED_CLAIM_TYPE_PHARMACY_STATUS)
            .payPercentage(UPDATED_PAY_PERCENTAGE)
            .enableSecondaryBillingStatus(UPDATED_ENABLE_SECONDARY_BILLING_STATUS)
            .amtPrintOnDeliveryTicketStatus(UPDATED_AMT_PRINT_ON_DELIVERY_TICKET_STATUS)
            .medigapStatus(UPDATED_MEDIGAP_STATUS)
            .medigapNum(UPDATED_MEDIGAP_NUM)
            .notes(UPDATED_NOTES)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .insuranceMasterUuid(UPDATED_INSURANCE_MASTER_UUID)
            .insurancePayerIdNo(UPDATED_INSURANCE_PAYER_ID_NO)
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
            .claimProgramCode(UPDATED_CLAIM_PROGRAM_CODE)
            .claimProgramName(UPDATED_CLAIM_PROGRAM_NAME)
            .insuranceIdNo(UPDATED_INSURANCE_ID_NO)
            .insuranceGroupNo(UPDATED_INSURANCE_GROUP_NO)
            .priceTableName(UPDATED_PRICE_TABLE_NAME)
            .claimProgramId(UPDATED_CLAIM_PROGRAM_ID)
            .claimFormName(UPDATED_CLAIM_FORM_NAME)
            .insuranceGroupName(UPDATED_INSURANCE_GROUP_NAME)
            .contactPersonFirstName(UPDATED_CONTACT_PERSON_FIRST_NAME)
            .contactPersonMiddleName(UPDATED_CONTACT_PERSON_MIDDLE_NAME)
            .contactPersonLastName(UPDATED_CONTACT_PERSON_LAST_NAME)
            .taxTncludedAllowableStatus(UPDATED_TAX_TNCLUDED_ALLOWABLE_STATUS)
            .cmsCrossoverInsuranceIdNo(UPDATED_CMS_CROSSOVER_INSURANCE_ID_NO);
        return insuranceMaster;
    }

    @BeforeEach
    public void initTest() {
        insuranceMaster = createEntity(em);
    }

    @Test
    @Transactional
    void createInsuranceMaster() throws Exception {
        int databaseSizeBeforeCreate = insuranceMasterRepository.findAll().size();
        // Create the InsuranceMaster
        InsuranceMasterDTO insuranceMasterDTO = insuranceMasterMapper.toDto(insuranceMaster);
        restInsuranceMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(insuranceMasterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the InsuranceMaster in the database
        List<InsuranceMaster> insuranceMasterList = insuranceMasterRepository.findAll();
        assertThat(insuranceMasterList).hasSize(databaseSizeBeforeCreate + 1);
        InsuranceMaster testInsuranceMaster = insuranceMasterList.get(insuranceMasterList.size() - 1);
        assertThat(testInsuranceMaster.getInsuranceName()).isEqualTo(DEFAULT_INSURANCE_NAME);
        assertThat(testInsuranceMaster.getInsurancePlanType()).isEqualTo(DEFAULT_INSURANCE_PLAN_TYPE);
        assertThat(testInsuranceMaster.getTaxType()).isEqualTo(DEFAULT_TAX_TYPE);
        assertThat(testInsuranceMaster.getTaxIncludedAllowableStatus()).isEqualTo(DEFAULT_TAX_INCLUDED_ALLOWABLE_STATUS);
        assertThat(testInsuranceMaster.getInsuranceGroupId()).isEqualTo(DEFAULT_INSURANCE_GROUP_ID);
        assertThat(testInsuranceMaster.getPriceTableId()).isEqualTo(DEFAULT_PRICE_TABLE_ID);
        assertThat(testInsuranceMaster.getClaimTypeDmeStatus()).isEqualTo(DEFAULT_CLAIM_TYPE_DME_STATUS);
        assertThat(testInsuranceMaster.getClaimTypeMajorMadicalStatus()).isEqualTo(DEFAULT_CLAIM_TYPE_MAJOR_MADICAL_STATUS);
        assertThat(testInsuranceMaster.getClaimTypePharmacyStatus()).isEqualTo(DEFAULT_CLAIM_TYPE_PHARMACY_STATUS);
        assertThat(testInsuranceMaster.getPayPercentage()).isEqualTo(DEFAULT_PAY_PERCENTAGE);
        assertThat(testInsuranceMaster.getEnableSecondaryBillingStatus()).isEqualTo(DEFAULT_ENABLE_SECONDARY_BILLING_STATUS);
        assertThat(testInsuranceMaster.getAmtPrintOnDeliveryTicketStatus()).isEqualTo(DEFAULT_AMT_PRINT_ON_DELIVERY_TICKET_STATUS);
        assertThat(testInsuranceMaster.getMedigapStatus()).isEqualTo(DEFAULT_MEDIGAP_STATUS);
        assertThat(testInsuranceMaster.getMedigapNum()).isEqualTo(DEFAULT_MEDIGAP_NUM);
        assertThat(testInsuranceMaster.getNotes()).isEqualTo(DEFAULT_NOTES);
        assertThat(testInsuranceMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testInsuranceMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testInsuranceMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testInsuranceMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testInsuranceMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testInsuranceMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testInsuranceMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testInsuranceMaster.getInsuranceMasterUuid()).isEqualTo(DEFAULT_INSURANCE_MASTER_UUID);
        assertThat(testInsuranceMaster.getInsurancePayerIdNo()).isEqualTo(DEFAULT_INSURANCE_PAYER_ID_NO);
        assertThat(testInsuranceMaster.getAddressLine1()).isEqualTo(DEFAULT_ADDRESS_LINE_1);
        assertThat(testInsuranceMaster.getAddressLine2()).isEqualTo(DEFAULT_ADDRESS_LINE_2);
        assertThat(testInsuranceMaster.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testInsuranceMaster.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testInsuranceMaster.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testInsuranceMaster.getZip()).isEqualTo(DEFAULT_ZIP);
        assertThat(testInsuranceMaster.getContactNo1()).isEqualTo(DEFAULT_CONTACT_NO_1);
        assertThat(testInsuranceMaster.getContactNo2()).isEqualTo(DEFAULT_CONTACT_NO_2);
        assertThat(testInsuranceMaster.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testInsuranceMaster.getEfax()).isEqualTo(DEFAULT_EFAX);
        assertThat(testInsuranceMaster.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testInsuranceMaster.getRelationship()).isEqualTo(DEFAULT_RELATIONSHIP);
        assertThat(testInsuranceMaster.getModeOfContact()).isEqualTo(DEFAULT_MODE_OF_CONTACT);
        assertThat(testInsuranceMaster.getClaimProgramCode()).isEqualTo(DEFAULT_CLAIM_PROGRAM_CODE);
        assertThat(testInsuranceMaster.getClaimProgramName()).isEqualTo(DEFAULT_CLAIM_PROGRAM_NAME);
        assertThat(testInsuranceMaster.getInsuranceIdNo()).isEqualTo(DEFAULT_INSURANCE_ID_NO);
        assertThat(testInsuranceMaster.getInsuranceGroupNo()).isEqualTo(DEFAULT_INSURANCE_GROUP_NO);
        assertThat(testInsuranceMaster.getPriceTableName()).isEqualTo(DEFAULT_PRICE_TABLE_NAME);
        assertThat(testInsuranceMaster.getClaimProgramId()).isEqualTo(DEFAULT_CLAIM_PROGRAM_ID);
        assertThat(testInsuranceMaster.getClaimFormName()).isEqualTo(DEFAULT_CLAIM_FORM_NAME);
        assertThat(testInsuranceMaster.getInsuranceGroupName()).isEqualTo(DEFAULT_INSURANCE_GROUP_NAME);
        assertThat(testInsuranceMaster.getContactPersonFirstName()).isEqualTo(DEFAULT_CONTACT_PERSON_FIRST_NAME);
        assertThat(testInsuranceMaster.getContactPersonMiddleName()).isEqualTo(DEFAULT_CONTACT_PERSON_MIDDLE_NAME);
        assertThat(testInsuranceMaster.getContactPersonLastName()).isEqualTo(DEFAULT_CONTACT_PERSON_LAST_NAME);
        assertThat(testInsuranceMaster.getTaxTncludedAllowableStatus()).isEqualTo(DEFAULT_TAX_TNCLUDED_ALLOWABLE_STATUS);
        assertThat(testInsuranceMaster.getCmsCrossoverInsuranceIdNo()).isEqualTo(DEFAULT_CMS_CROSSOVER_INSURANCE_ID_NO);
    }

    @Test
    @Transactional
    void createInsuranceMasterWithExistingId() throws Exception {
        // Create the InsuranceMaster with an existing ID
        insuranceMaster.setInsuranceId(1L);
        InsuranceMasterDTO insuranceMasterDTO = insuranceMasterMapper.toDto(insuranceMaster);

        int databaseSizeBeforeCreate = insuranceMasterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restInsuranceMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(insuranceMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InsuranceMaster in the database
        List<InsuranceMaster> insuranceMasterList = insuranceMasterRepository.findAll();
        assertThat(insuranceMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllInsuranceMasters() throws Exception {
        // Initialize the database
        insuranceMasterRepository.saveAndFlush(insuranceMaster);

        // Get all the insuranceMasterList
        restInsuranceMasterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=insuranceId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].insuranceId").value(hasItem(insuranceMaster.getInsuranceId().intValue())))
            .andExpect(jsonPath("$.[*].insuranceName").value(hasItem(DEFAULT_INSURANCE_NAME)))
            .andExpect(jsonPath("$.[*].insurancePlanType").value(hasItem(DEFAULT_INSURANCE_PLAN_TYPE)))
            .andExpect(jsonPath("$.[*].taxType").value(hasItem(DEFAULT_TAX_TYPE)))
            .andExpect(jsonPath("$.[*].taxIncludedAllowableStatus").value(hasItem(DEFAULT_TAX_INCLUDED_ALLOWABLE_STATUS)))
            .andExpect(jsonPath("$.[*].insuranceGroupId").value(hasItem(DEFAULT_INSURANCE_GROUP_ID.intValue())))
            .andExpect(jsonPath("$.[*].priceTableId").value(hasItem(DEFAULT_PRICE_TABLE_ID.intValue())))
            .andExpect(jsonPath("$.[*].claimTypeDmeStatus").value(hasItem(DEFAULT_CLAIM_TYPE_DME_STATUS)))
            .andExpect(jsonPath("$.[*].claimTypeMajorMadicalStatus").value(hasItem(DEFAULT_CLAIM_TYPE_MAJOR_MADICAL_STATUS)))
            .andExpect(jsonPath("$.[*].claimTypePharmacyStatus").value(hasItem(DEFAULT_CLAIM_TYPE_PHARMACY_STATUS)))
            .andExpect(jsonPath("$.[*].payPercentage").value(hasItem(DEFAULT_PAY_PERCENTAGE.doubleValue())))
            .andExpect(jsonPath("$.[*].enableSecondaryBillingStatus").value(hasItem(DEFAULT_ENABLE_SECONDARY_BILLING_STATUS)))
            .andExpect(jsonPath("$.[*].amtPrintOnDeliveryTicketStatus").value(hasItem(DEFAULT_AMT_PRINT_ON_DELIVERY_TICKET_STATUS)))
            .andExpect(jsonPath("$.[*].medigapStatus").value(hasItem(DEFAULT_MEDIGAP_STATUS)))
            .andExpect(jsonPath("$.[*].medigapNum").value(hasItem(DEFAULT_MEDIGAP_NUM.intValue())))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].insuranceMasterUuid").value(hasItem(DEFAULT_INSURANCE_MASTER_UUID.toString())))
            .andExpect(jsonPath("$.[*].insurancePayerIdNo").value(hasItem(DEFAULT_INSURANCE_PAYER_ID_NO)))
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
            .andExpect(jsonPath("$.[*].claimProgramCode").value(hasItem(DEFAULT_CLAIM_PROGRAM_CODE)))
            .andExpect(jsonPath("$.[*].claimProgramName").value(hasItem(DEFAULT_CLAIM_PROGRAM_NAME)))
            .andExpect(jsonPath("$.[*].insuranceIdNo").value(hasItem(DEFAULT_INSURANCE_ID_NO)))
            .andExpect(jsonPath("$.[*].insuranceGroupNo").value(hasItem(DEFAULT_INSURANCE_GROUP_NO)))
            .andExpect(jsonPath("$.[*].priceTableName").value(hasItem(DEFAULT_PRICE_TABLE_NAME)))
            .andExpect(jsonPath("$.[*].claimProgramId").value(hasItem(DEFAULT_CLAIM_PROGRAM_ID)))
            .andExpect(jsonPath("$.[*].claimFormName").value(hasItem(DEFAULT_CLAIM_FORM_NAME)))
            .andExpect(jsonPath("$.[*].insuranceGroupName").value(hasItem(DEFAULT_INSURANCE_GROUP_NAME)))
            .andExpect(jsonPath("$.[*].contactPersonFirstName").value(hasItem(DEFAULT_CONTACT_PERSON_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].contactPersonMiddleName").value(hasItem(DEFAULT_CONTACT_PERSON_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].contactPersonLastName").value(hasItem(DEFAULT_CONTACT_PERSON_LAST_NAME)))
            .andExpect(jsonPath("$.[*].taxTncludedAllowableStatus").value(hasItem(DEFAULT_TAX_TNCLUDED_ALLOWABLE_STATUS)))
            .andExpect(jsonPath("$.[*].cmsCrossoverInsuranceIdNo").value(hasItem(DEFAULT_CMS_CROSSOVER_INSURANCE_ID_NO)));
    }

    @Test
    @Transactional
    void getInsuranceMaster() throws Exception {
        // Initialize the database
        insuranceMasterRepository.saveAndFlush(insuranceMaster);

        // Get the insuranceMaster
        restInsuranceMasterMockMvc
            .perform(get(ENTITY_API_URL_ID, insuranceMaster.getInsuranceId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.insuranceId").value(insuranceMaster.getInsuranceId().intValue()))
            .andExpect(jsonPath("$.insuranceName").value(DEFAULT_INSURANCE_NAME))
            .andExpect(jsonPath("$.insurancePlanType").value(DEFAULT_INSURANCE_PLAN_TYPE))
            .andExpect(jsonPath("$.taxType").value(DEFAULT_TAX_TYPE))
            .andExpect(jsonPath("$.taxIncludedAllowableStatus").value(DEFAULT_TAX_INCLUDED_ALLOWABLE_STATUS))
            .andExpect(jsonPath("$.insuranceGroupId").value(DEFAULT_INSURANCE_GROUP_ID.intValue()))
            .andExpect(jsonPath("$.priceTableId").value(DEFAULT_PRICE_TABLE_ID.intValue()))
            .andExpect(jsonPath("$.claimTypeDmeStatus").value(DEFAULT_CLAIM_TYPE_DME_STATUS))
            .andExpect(jsonPath("$.claimTypeMajorMadicalStatus").value(DEFAULT_CLAIM_TYPE_MAJOR_MADICAL_STATUS))
            .andExpect(jsonPath("$.claimTypePharmacyStatus").value(DEFAULT_CLAIM_TYPE_PHARMACY_STATUS))
            .andExpect(jsonPath("$.payPercentage").value(DEFAULT_PAY_PERCENTAGE.doubleValue()))
            .andExpect(jsonPath("$.enableSecondaryBillingStatus").value(DEFAULT_ENABLE_SECONDARY_BILLING_STATUS))
            .andExpect(jsonPath("$.amtPrintOnDeliveryTicketStatus").value(DEFAULT_AMT_PRINT_ON_DELIVERY_TICKET_STATUS))
            .andExpect(jsonPath("$.medigapStatus").value(DEFAULT_MEDIGAP_STATUS))
            .andExpect(jsonPath("$.medigapNum").value(DEFAULT_MEDIGAP_NUM.intValue()))
            .andExpect(jsonPath("$.notes").value(DEFAULT_NOTES))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.insuranceMasterUuid").value(DEFAULT_INSURANCE_MASTER_UUID.toString()))
            .andExpect(jsonPath("$.insurancePayerIdNo").value(DEFAULT_INSURANCE_PAYER_ID_NO))
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
            .andExpect(jsonPath("$.claimProgramCode").value(DEFAULT_CLAIM_PROGRAM_CODE))
            .andExpect(jsonPath("$.claimProgramName").value(DEFAULT_CLAIM_PROGRAM_NAME))
            .andExpect(jsonPath("$.insuranceIdNo").value(DEFAULT_INSURANCE_ID_NO))
            .andExpect(jsonPath("$.insuranceGroupNo").value(DEFAULT_INSURANCE_GROUP_NO))
            .andExpect(jsonPath("$.priceTableName").value(DEFAULT_PRICE_TABLE_NAME))
            .andExpect(jsonPath("$.claimProgramId").value(DEFAULT_CLAIM_PROGRAM_ID))
            .andExpect(jsonPath("$.claimFormName").value(DEFAULT_CLAIM_FORM_NAME))
            .andExpect(jsonPath("$.insuranceGroupName").value(DEFAULT_INSURANCE_GROUP_NAME))
            .andExpect(jsonPath("$.contactPersonFirstName").value(DEFAULT_CONTACT_PERSON_FIRST_NAME))
            .andExpect(jsonPath("$.contactPersonMiddleName").value(DEFAULT_CONTACT_PERSON_MIDDLE_NAME))
            .andExpect(jsonPath("$.contactPersonLastName").value(DEFAULT_CONTACT_PERSON_LAST_NAME))
            .andExpect(jsonPath("$.taxTncludedAllowableStatus").value(DEFAULT_TAX_TNCLUDED_ALLOWABLE_STATUS))
            .andExpect(jsonPath("$.cmsCrossoverInsuranceIdNo").value(DEFAULT_CMS_CROSSOVER_INSURANCE_ID_NO));
    }

    @Test
    @Transactional
    void getNonExistingInsuranceMaster() throws Exception {
        // Get the insuranceMaster
        restInsuranceMasterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewInsuranceMaster() throws Exception {
        // Initialize the database
        insuranceMasterRepository.saveAndFlush(insuranceMaster);

        int databaseSizeBeforeUpdate = insuranceMasterRepository.findAll().size();

        // Update the insuranceMaster
        InsuranceMaster updatedInsuranceMaster = insuranceMasterRepository.findById(insuranceMaster.getInsuranceId()).get();
        // Disconnect from session so that the updates on updatedInsuranceMaster are not directly saved in db
        em.detach(updatedInsuranceMaster);
        updatedInsuranceMaster
            .insuranceName(UPDATED_INSURANCE_NAME)
            .insurancePlanType(UPDATED_INSURANCE_PLAN_TYPE)
            .taxType(UPDATED_TAX_TYPE)
            .taxIncludedAllowableStatus(UPDATED_TAX_INCLUDED_ALLOWABLE_STATUS)
            .insuranceGroupId(UPDATED_INSURANCE_GROUP_ID)
            .priceTableId(UPDATED_PRICE_TABLE_ID)
            .claimTypeDmeStatus(UPDATED_CLAIM_TYPE_DME_STATUS)
            .claimTypeMajorMadicalStatus(UPDATED_CLAIM_TYPE_MAJOR_MADICAL_STATUS)
            .claimTypePharmacyStatus(UPDATED_CLAIM_TYPE_PHARMACY_STATUS)
            .payPercentage(UPDATED_PAY_PERCENTAGE)
            .enableSecondaryBillingStatus(UPDATED_ENABLE_SECONDARY_BILLING_STATUS)
            .amtPrintOnDeliveryTicketStatus(UPDATED_AMT_PRINT_ON_DELIVERY_TICKET_STATUS)
            .medigapStatus(UPDATED_MEDIGAP_STATUS)
            .medigapNum(UPDATED_MEDIGAP_NUM)
            .notes(UPDATED_NOTES)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .insuranceMasterUuid(UPDATED_INSURANCE_MASTER_UUID)
            .insurancePayerIdNo(UPDATED_INSURANCE_PAYER_ID_NO)
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
            .claimProgramCode(UPDATED_CLAIM_PROGRAM_CODE)
            .claimProgramName(UPDATED_CLAIM_PROGRAM_NAME)
            .insuranceIdNo(UPDATED_INSURANCE_ID_NO)
            .insuranceGroupNo(UPDATED_INSURANCE_GROUP_NO)
            .priceTableName(UPDATED_PRICE_TABLE_NAME)
            .claimProgramId(UPDATED_CLAIM_PROGRAM_ID)
            .claimFormName(UPDATED_CLAIM_FORM_NAME)
            .insuranceGroupName(UPDATED_INSURANCE_GROUP_NAME)
            .contactPersonFirstName(UPDATED_CONTACT_PERSON_FIRST_NAME)
            .contactPersonMiddleName(UPDATED_CONTACT_PERSON_MIDDLE_NAME)
            .contactPersonLastName(UPDATED_CONTACT_PERSON_LAST_NAME)
            .taxTncludedAllowableStatus(UPDATED_TAX_TNCLUDED_ALLOWABLE_STATUS)
            .cmsCrossoverInsuranceIdNo(UPDATED_CMS_CROSSOVER_INSURANCE_ID_NO);
        InsuranceMasterDTO insuranceMasterDTO = insuranceMasterMapper.toDto(updatedInsuranceMaster);

        restInsuranceMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, insuranceMasterDTO.getInsuranceId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(insuranceMasterDTO))
            )
            .andExpect(status().isOk());

        // Validate the InsuranceMaster in the database
        List<InsuranceMaster> insuranceMasterList = insuranceMasterRepository.findAll();
        assertThat(insuranceMasterList).hasSize(databaseSizeBeforeUpdate);
        InsuranceMaster testInsuranceMaster = insuranceMasterList.get(insuranceMasterList.size() - 1);
        assertThat(testInsuranceMaster.getInsuranceName()).isEqualTo(UPDATED_INSURANCE_NAME);
        assertThat(testInsuranceMaster.getInsurancePlanType()).isEqualTo(UPDATED_INSURANCE_PLAN_TYPE);
        assertThat(testInsuranceMaster.getTaxType()).isEqualTo(UPDATED_TAX_TYPE);
        assertThat(testInsuranceMaster.getTaxIncludedAllowableStatus()).isEqualTo(UPDATED_TAX_INCLUDED_ALLOWABLE_STATUS);
        assertThat(testInsuranceMaster.getInsuranceGroupId()).isEqualTo(UPDATED_INSURANCE_GROUP_ID);
        assertThat(testInsuranceMaster.getPriceTableId()).isEqualTo(UPDATED_PRICE_TABLE_ID);
        assertThat(testInsuranceMaster.getClaimTypeDmeStatus()).isEqualTo(UPDATED_CLAIM_TYPE_DME_STATUS);
        assertThat(testInsuranceMaster.getClaimTypeMajorMadicalStatus()).isEqualTo(UPDATED_CLAIM_TYPE_MAJOR_MADICAL_STATUS);
        assertThat(testInsuranceMaster.getClaimTypePharmacyStatus()).isEqualTo(UPDATED_CLAIM_TYPE_PHARMACY_STATUS);
        assertThat(testInsuranceMaster.getPayPercentage()).isEqualTo(UPDATED_PAY_PERCENTAGE);
        assertThat(testInsuranceMaster.getEnableSecondaryBillingStatus()).isEqualTo(UPDATED_ENABLE_SECONDARY_BILLING_STATUS);
        assertThat(testInsuranceMaster.getAmtPrintOnDeliveryTicketStatus()).isEqualTo(UPDATED_AMT_PRINT_ON_DELIVERY_TICKET_STATUS);
        assertThat(testInsuranceMaster.getMedigapStatus()).isEqualTo(UPDATED_MEDIGAP_STATUS);
        assertThat(testInsuranceMaster.getMedigapNum()).isEqualTo(UPDATED_MEDIGAP_NUM);
        assertThat(testInsuranceMaster.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testInsuranceMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testInsuranceMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testInsuranceMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testInsuranceMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testInsuranceMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testInsuranceMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testInsuranceMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testInsuranceMaster.getInsuranceMasterUuid()).isEqualTo(UPDATED_INSURANCE_MASTER_UUID);
        assertThat(testInsuranceMaster.getInsurancePayerIdNo()).isEqualTo(UPDATED_INSURANCE_PAYER_ID_NO);
        assertThat(testInsuranceMaster.getAddressLine1()).isEqualTo(UPDATED_ADDRESS_LINE_1);
        assertThat(testInsuranceMaster.getAddressLine2()).isEqualTo(UPDATED_ADDRESS_LINE_2);
        assertThat(testInsuranceMaster.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testInsuranceMaster.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testInsuranceMaster.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testInsuranceMaster.getZip()).isEqualTo(UPDATED_ZIP);
        assertThat(testInsuranceMaster.getContactNo1()).isEqualTo(UPDATED_CONTACT_NO_1);
        assertThat(testInsuranceMaster.getContactNo2()).isEqualTo(UPDATED_CONTACT_NO_2);
        assertThat(testInsuranceMaster.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testInsuranceMaster.getEfax()).isEqualTo(UPDATED_EFAX);
        assertThat(testInsuranceMaster.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testInsuranceMaster.getRelationship()).isEqualTo(UPDATED_RELATIONSHIP);
        assertThat(testInsuranceMaster.getModeOfContact()).isEqualTo(UPDATED_MODE_OF_CONTACT);
        assertThat(testInsuranceMaster.getClaimProgramCode()).isEqualTo(UPDATED_CLAIM_PROGRAM_CODE);
        assertThat(testInsuranceMaster.getClaimProgramName()).isEqualTo(UPDATED_CLAIM_PROGRAM_NAME);
        assertThat(testInsuranceMaster.getInsuranceIdNo()).isEqualTo(UPDATED_INSURANCE_ID_NO);
        assertThat(testInsuranceMaster.getInsuranceGroupNo()).isEqualTo(UPDATED_INSURANCE_GROUP_NO);
        assertThat(testInsuranceMaster.getPriceTableName()).isEqualTo(UPDATED_PRICE_TABLE_NAME);
        assertThat(testInsuranceMaster.getClaimProgramId()).isEqualTo(UPDATED_CLAIM_PROGRAM_ID);
        assertThat(testInsuranceMaster.getClaimFormName()).isEqualTo(UPDATED_CLAIM_FORM_NAME);
        assertThat(testInsuranceMaster.getInsuranceGroupName()).isEqualTo(UPDATED_INSURANCE_GROUP_NAME);
        assertThat(testInsuranceMaster.getContactPersonFirstName()).isEqualTo(UPDATED_CONTACT_PERSON_FIRST_NAME);
        assertThat(testInsuranceMaster.getContactPersonMiddleName()).isEqualTo(UPDATED_CONTACT_PERSON_MIDDLE_NAME);
        assertThat(testInsuranceMaster.getContactPersonLastName()).isEqualTo(UPDATED_CONTACT_PERSON_LAST_NAME);
        assertThat(testInsuranceMaster.getTaxTncludedAllowableStatus()).isEqualTo(UPDATED_TAX_TNCLUDED_ALLOWABLE_STATUS);
        assertThat(testInsuranceMaster.getCmsCrossoverInsuranceIdNo()).isEqualTo(UPDATED_CMS_CROSSOVER_INSURANCE_ID_NO);
    }

    @Test
    @Transactional
    void putNonExistingInsuranceMaster() throws Exception {
        int databaseSizeBeforeUpdate = insuranceMasterRepository.findAll().size();
        insuranceMaster.setInsuranceId(count.incrementAndGet());

        // Create the InsuranceMaster
        InsuranceMasterDTO insuranceMasterDTO = insuranceMasterMapper.toDto(insuranceMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInsuranceMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, insuranceMasterDTO.getInsuranceId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(insuranceMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InsuranceMaster in the database
        List<InsuranceMaster> insuranceMasterList = insuranceMasterRepository.findAll();
        assertThat(insuranceMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchInsuranceMaster() throws Exception {
        int databaseSizeBeforeUpdate = insuranceMasterRepository.findAll().size();
        insuranceMaster.setInsuranceId(count.incrementAndGet());

        // Create the InsuranceMaster
        InsuranceMasterDTO insuranceMasterDTO = insuranceMasterMapper.toDto(insuranceMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInsuranceMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(insuranceMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InsuranceMaster in the database
        List<InsuranceMaster> insuranceMasterList = insuranceMasterRepository.findAll();
        assertThat(insuranceMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamInsuranceMaster() throws Exception {
        int databaseSizeBeforeUpdate = insuranceMasterRepository.findAll().size();
        insuranceMaster.setInsuranceId(count.incrementAndGet());

        // Create the InsuranceMaster
        InsuranceMasterDTO insuranceMasterDTO = insuranceMasterMapper.toDto(insuranceMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInsuranceMasterMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(insuranceMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the InsuranceMaster in the database
        List<InsuranceMaster> insuranceMasterList = insuranceMasterRepository.findAll();
        assertThat(insuranceMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateInsuranceMasterWithPatch() throws Exception {
        // Initialize the database
        insuranceMasterRepository.saveAndFlush(insuranceMaster);

        int databaseSizeBeforeUpdate = insuranceMasterRepository.findAll().size();

        // Update the insuranceMaster using partial update
        InsuranceMaster partialUpdatedInsuranceMaster = new InsuranceMaster();
        partialUpdatedInsuranceMaster.setInsuranceId(insuranceMaster.getInsuranceId());

        partialUpdatedInsuranceMaster
            .taxType(UPDATED_TAX_TYPE)
            .insuranceGroupId(UPDATED_INSURANCE_GROUP_ID)
            .claimTypeDmeStatus(UPDATED_CLAIM_TYPE_DME_STATUS)
            .claimTypeMajorMadicalStatus(UPDATED_CLAIM_TYPE_MAJOR_MADICAL_STATUS)
            .enableSecondaryBillingStatus(UPDATED_ENABLE_SECONDARY_BILLING_STATUS)
            .amtPrintOnDeliveryTicketStatus(UPDATED_AMT_PRINT_ON_DELIVERY_TICKET_STATUS)
            .medigapStatus(UPDATED_MEDIGAP_STATUS)
            .notes(UPDATED_NOTES)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .insuranceMasterUuid(UPDATED_INSURANCE_MASTER_UUID)
            .insurancePayerIdNo(UPDATED_INSURANCE_PAYER_ID_NO)
            .addressLine2(UPDATED_ADDRESS_LINE_2)
            .fax(UPDATED_FAX)
            .efax(UPDATED_EFAX)
            .email(UPDATED_EMAIL)
            .insuranceIdNo(UPDATED_INSURANCE_ID_NO)
            .insuranceGroupNo(UPDATED_INSURANCE_GROUP_NO)
            .priceTableName(UPDATED_PRICE_TABLE_NAME)
            .claimFormName(UPDATED_CLAIM_FORM_NAME)
            .contactPersonMiddleName(UPDATED_CONTACT_PERSON_MIDDLE_NAME)
            .taxTncludedAllowableStatus(UPDATED_TAX_TNCLUDED_ALLOWABLE_STATUS);

        restInsuranceMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInsuranceMaster.getInsuranceId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInsuranceMaster))
            )
            .andExpect(status().isOk());

        // Validate the InsuranceMaster in the database
        List<InsuranceMaster> insuranceMasterList = insuranceMasterRepository.findAll();
        assertThat(insuranceMasterList).hasSize(databaseSizeBeforeUpdate);
        InsuranceMaster testInsuranceMaster = insuranceMasterList.get(insuranceMasterList.size() - 1);
        assertThat(testInsuranceMaster.getInsuranceName()).isEqualTo(DEFAULT_INSURANCE_NAME);
        assertThat(testInsuranceMaster.getInsurancePlanType()).isEqualTo(DEFAULT_INSURANCE_PLAN_TYPE);
        assertThat(testInsuranceMaster.getTaxType()).isEqualTo(UPDATED_TAX_TYPE);
        assertThat(testInsuranceMaster.getTaxIncludedAllowableStatus()).isEqualTo(DEFAULT_TAX_INCLUDED_ALLOWABLE_STATUS);
        assertThat(testInsuranceMaster.getInsuranceGroupId()).isEqualTo(UPDATED_INSURANCE_GROUP_ID);
        assertThat(testInsuranceMaster.getPriceTableId()).isEqualTo(DEFAULT_PRICE_TABLE_ID);
        assertThat(testInsuranceMaster.getClaimTypeDmeStatus()).isEqualTo(UPDATED_CLAIM_TYPE_DME_STATUS);
        assertThat(testInsuranceMaster.getClaimTypeMajorMadicalStatus()).isEqualTo(UPDATED_CLAIM_TYPE_MAJOR_MADICAL_STATUS);
        assertThat(testInsuranceMaster.getClaimTypePharmacyStatus()).isEqualTo(DEFAULT_CLAIM_TYPE_PHARMACY_STATUS);
        assertThat(testInsuranceMaster.getPayPercentage()).isEqualTo(DEFAULT_PAY_PERCENTAGE);
        assertThat(testInsuranceMaster.getEnableSecondaryBillingStatus()).isEqualTo(UPDATED_ENABLE_SECONDARY_BILLING_STATUS);
        assertThat(testInsuranceMaster.getAmtPrintOnDeliveryTicketStatus()).isEqualTo(UPDATED_AMT_PRINT_ON_DELIVERY_TICKET_STATUS);
        assertThat(testInsuranceMaster.getMedigapStatus()).isEqualTo(UPDATED_MEDIGAP_STATUS);
        assertThat(testInsuranceMaster.getMedigapNum()).isEqualTo(DEFAULT_MEDIGAP_NUM);
        assertThat(testInsuranceMaster.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testInsuranceMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testInsuranceMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testInsuranceMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testInsuranceMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testInsuranceMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testInsuranceMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testInsuranceMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testInsuranceMaster.getInsuranceMasterUuid()).isEqualTo(UPDATED_INSURANCE_MASTER_UUID);
        assertThat(testInsuranceMaster.getInsurancePayerIdNo()).isEqualTo(UPDATED_INSURANCE_PAYER_ID_NO);
        assertThat(testInsuranceMaster.getAddressLine1()).isEqualTo(DEFAULT_ADDRESS_LINE_1);
        assertThat(testInsuranceMaster.getAddressLine2()).isEqualTo(UPDATED_ADDRESS_LINE_2);
        assertThat(testInsuranceMaster.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testInsuranceMaster.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testInsuranceMaster.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testInsuranceMaster.getZip()).isEqualTo(DEFAULT_ZIP);
        assertThat(testInsuranceMaster.getContactNo1()).isEqualTo(DEFAULT_CONTACT_NO_1);
        assertThat(testInsuranceMaster.getContactNo2()).isEqualTo(DEFAULT_CONTACT_NO_2);
        assertThat(testInsuranceMaster.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testInsuranceMaster.getEfax()).isEqualTo(UPDATED_EFAX);
        assertThat(testInsuranceMaster.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testInsuranceMaster.getRelationship()).isEqualTo(DEFAULT_RELATIONSHIP);
        assertThat(testInsuranceMaster.getModeOfContact()).isEqualTo(DEFAULT_MODE_OF_CONTACT);
        assertThat(testInsuranceMaster.getClaimProgramCode()).isEqualTo(DEFAULT_CLAIM_PROGRAM_CODE);
        assertThat(testInsuranceMaster.getClaimProgramName()).isEqualTo(DEFAULT_CLAIM_PROGRAM_NAME);
        assertThat(testInsuranceMaster.getInsuranceIdNo()).isEqualTo(UPDATED_INSURANCE_ID_NO);
        assertThat(testInsuranceMaster.getInsuranceGroupNo()).isEqualTo(UPDATED_INSURANCE_GROUP_NO);
        assertThat(testInsuranceMaster.getPriceTableName()).isEqualTo(UPDATED_PRICE_TABLE_NAME);
        assertThat(testInsuranceMaster.getClaimProgramId()).isEqualTo(DEFAULT_CLAIM_PROGRAM_ID);
        assertThat(testInsuranceMaster.getClaimFormName()).isEqualTo(UPDATED_CLAIM_FORM_NAME);
        assertThat(testInsuranceMaster.getInsuranceGroupName()).isEqualTo(DEFAULT_INSURANCE_GROUP_NAME);
        assertThat(testInsuranceMaster.getContactPersonFirstName()).isEqualTo(DEFAULT_CONTACT_PERSON_FIRST_NAME);
        assertThat(testInsuranceMaster.getContactPersonMiddleName()).isEqualTo(UPDATED_CONTACT_PERSON_MIDDLE_NAME);
        assertThat(testInsuranceMaster.getContactPersonLastName()).isEqualTo(DEFAULT_CONTACT_PERSON_LAST_NAME);
        assertThat(testInsuranceMaster.getTaxTncludedAllowableStatus()).isEqualTo(UPDATED_TAX_TNCLUDED_ALLOWABLE_STATUS);
        assertThat(testInsuranceMaster.getCmsCrossoverInsuranceIdNo()).isEqualTo(DEFAULT_CMS_CROSSOVER_INSURANCE_ID_NO);
    }

    @Test
    @Transactional
    void fullUpdateInsuranceMasterWithPatch() throws Exception {
        // Initialize the database
        insuranceMasterRepository.saveAndFlush(insuranceMaster);

        int databaseSizeBeforeUpdate = insuranceMasterRepository.findAll().size();

        // Update the insuranceMaster using partial update
        InsuranceMaster partialUpdatedInsuranceMaster = new InsuranceMaster();
        partialUpdatedInsuranceMaster.setInsuranceId(insuranceMaster.getInsuranceId());

        partialUpdatedInsuranceMaster
            .insuranceName(UPDATED_INSURANCE_NAME)
            .insurancePlanType(UPDATED_INSURANCE_PLAN_TYPE)
            .taxType(UPDATED_TAX_TYPE)
            .taxIncludedAllowableStatus(UPDATED_TAX_INCLUDED_ALLOWABLE_STATUS)
            .insuranceGroupId(UPDATED_INSURANCE_GROUP_ID)
            .priceTableId(UPDATED_PRICE_TABLE_ID)
            .claimTypeDmeStatus(UPDATED_CLAIM_TYPE_DME_STATUS)
            .claimTypeMajorMadicalStatus(UPDATED_CLAIM_TYPE_MAJOR_MADICAL_STATUS)
            .claimTypePharmacyStatus(UPDATED_CLAIM_TYPE_PHARMACY_STATUS)
            .payPercentage(UPDATED_PAY_PERCENTAGE)
            .enableSecondaryBillingStatus(UPDATED_ENABLE_SECONDARY_BILLING_STATUS)
            .amtPrintOnDeliveryTicketStatus(UPDATED_AMT_PRINT_ON_DELIVERY_TICKET_STATUS)
            .medigapStatus(UPDATED_MEDIGAP_STATUS)
            .medigapNum(UPDATED_MEDIGAP_NUM)
            .notes(UPDATED_NOTES)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .insuranceMasterUuid(UPDATED_INSURANCE_MASTER_UUID)
            .insurancePayerIdNo(UPDATED_INSURANCE_PAYER_ID_NO)
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
            .claimProgramCode(UPDATED_CLAIM_PROGRAM_CODE)
            .claimProgramName(UPDATED_CLAIM_PROGRAM_NAME)
            .insuranceIdNo(UPDATED_INSURANCE_ID_NO)
            .insuranceGroupNo(UPDATED_INSURANCE_GROUP_NO)
            .priceTableName(UPDATED_PRICE_TABLE_NAME)
            .claimProgramId(UPDATED_CLAIM_PROGRAM_ID)
            .claimFormName(UPDATED_CLAIM_FORM_NAME)
            .insuranceGroupName(UPDATED_INSURANCE_GROUP_NAME)
            .contactPersonFirstName(UPDATED_CONTACT_PERSON_FIRST_NAME)
            .contactPersonMiddleName(UPDATED_CONTACT_PERSON_MIDDLE_NAME)
            .contactPersonLastName(UPDATED_CONTACT_PERSON_LAST_NAME)
            .taxTncludedAllowableStatus(UPDATED_TAX_TNCLUDED_ALLOWABLE_STATUS)
            .cmsCrossoverInsuranceIdNo(UPDATED_CMS_CROSSOVER_INSURANCE_ID_NO);

        restInsuranceMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInsuranceMaster.getInsuranceId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInsuranceMaster))
            )
            .andExpect(status().isOk());

        // Validate the InsuranceMaster in the database
        List<InsuranceMaster> insuranceMasterList = insuranceMasterRepository.findAll();
        assertThat(insuranceMasterList).hasSize(databaseSizeBeforeUpdate);
        InsuranceMaster testInsuranceMaster = insuranceMasterList.get(insuranceMasterList.size() - 1);
        assertThat(testInsuranceMaster.getInsuranceName()).isEqualTo(UPDATED_INSURANCE_NAME);
        assertThat(testInsuranceMaster.getInsurancePlanType()).isEqualTo(UPDATED_INSURANCE_PLAN_TYPE);
        assertThat(testInsuranceMaster.getTaxType()).isEqualTo(UPDATED_TAX_TYPE);
        assertThat(testInsuranceMaster.getTaxIncludedAllowableStatus()).isEqualTo(UPDATED_TAX_INCLUDED_ALLOWABLE_STATUS);
        assertThat(testInsuranceMaster.getInsuranceGroupId()).isEqualTo(UPDATED_INSURANCE_GROUP_ID);
        assertThat(testInsuranceMaster.getPriceTableId()).isEqualTo(UPDATED_PRICE_TABLE_ID);
        assertThat(testInsuranceMaster.getClaimTypeDmeStatus()).isEqualTo(UPDATED_CLAIM_TYPE_DME_STATUS);
        assertThat(testInsuranceMaster.getClaimTypeMajorMadicalStatus()).isEqualTo(UPDATED_CLAIM_TYPE_MAJOR_MADICAL_STATUS);
        assertThat(testInsuranceMaster.getClaimTypePharmacyStatus()).isEqualTo(UPDATED_CLAIM_TYPE_PHARMACY_STATUS);
        assertThat(testInsuranceMaster.getPayPercentage()).isEqualTo(UPDATED_PAY_PERCENTAGE);
        assertThat(testInsuranceMaster.getEnableSecondaryBillingStatus()).isEqualTo(UPDATED_ENABLE_SECONDARY_BILLING_STATUS);
        assertThat(testInsuranceMaster.getAmtPrintOnDeliveryTicketStatus()).isEqualTo(UPDATED_AMT_PRINT_ON_DELIVERY_TICKET_STATUS);
        assertThat(testInsuranceMaster.getMedigapStatus()).isEqualTo(UPDATED_MEDIGAP_STATUS);
        assertThat(testInsuranceMaster.getMedigapNum()).isEqualTo(UPDATED_MEDIGAP_NUM);
        assertThat(testInsuranceMaster.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testInsuranceMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testInsuranceMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testInsuranceMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testInsuranceMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testInsuranceMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testInsuranceMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testInsuranceMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testInsuranceMaster.getInsuranceMasterUuid()).isEqualTo(UPDATED_INSURANCE_MASTER_UUID);
        assertThat(testInsuranceMaster.getInsurancePayerIdNo()).isEqualTo(UPDATED_INSURANCE_PAYER_ID_NO);
        assertThat(testInsuranceMaster.getAddressLine1()).isEqualTo(UPDATED_ADDRESS_LINE_1);
        assertThat(testInsuranceMaster.getAddressLine2()).isEqualTo(UPDATED_ADDRESS_LINE_2);
        assertThat(testInsuranceMaster.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testInsuranceMaster.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testInsuranceMaster.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testInsuranceMaster.getZip()).isEqualTo(UPDATED_ZIP);
        assertThat(testInsuranceMaster.getContactNo1()).isEqualTo(UPDATED_CONTACT_NO_1);
        assertThat(testInsuranceMaster.getContactNo2()).isEqualTo(UPDATED_CONTACT_NO_2);
        assertThat(testInsuranceMaster.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testInsuranceMaster.getEfax()).isEqualTo(UPDATED_EFAX);
        assertThat(testInsuranceMaster.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testInsuranceMaster.getRelationship()).isEqualTo(UPDATED_RELATIONSHIP);
        assertThat(testInsuranceMaster.getModeOfContact()).isEqualTo(UPDATED_MODE_OF_CONTACT);
        assertThat(testInsuranceMaster.getClaimProgramCode()).isEqualTo(UPDATED_CLAIM_PROGRAM_CODE);
        assertThat(testInsuranceMaster.getClaimProgramName()).isEqualTo(UPDATED_CLAIM_PROGRAM_NAME);
        assertThat(testInsuranceMaster.getInsuranceIdNo()).isEqualTo(UPDATED_INSURANCE_ID_NO);
        assertThat(testInsuranceMaster.getInsuranceGroupNo()).isEqualTo(UPDATED_INSURANCE_GROUP_NO);
        assertThat(testInsuranceMaster.getPriceTableName()).isEqualTo(UPDATED_PRICE_TABLE_NAME);
        assertThat(testInsuranceMaster.getClaimProgramId()).isEqualTo(UPDATED_CLAIM_PROGRAM_ID);
        assertThat(testInsuranceMaster.getClaimFormName()).isEqualTo(UPDATED_CLAIM_FORM_NAME);
        assertThat(testInsuranceMaster.getInsuranceGroupName()).isEqualTo(UPDATED_INSURANCE_GROUP_NAME);
        assertThat(testInsuranceMaster.getContactPersonFirstName()).isEqualTo(UPDATED_CONTACT_PERSON_FIRST_NAME);
        assertThat(testInsuranceMaster.getContactPersonMiddleName()).isEqualTo(UPDATED_CONTACT_PERSON_MIDDLE_NAME);
        assertThat(testInsuranceMaster.getContactPersonLastName()).isEqualTo(UPDATED_CONTACT_PERSON_LAST_NAME);
        assertThat(testInsuranceMaster.getTaxTncludedAllowableStatus()).isEqualTo(UPDATED_TAX_TNCLUDED_ALLOWABLE_STATUS);
        assertThat(testInsuranceMaster.getCmsCrossoverInsuranceIdNo()).isEqualTo(UPDATED_CMS_CROSSOVER_INSURANCE_ID_NO);
    }

    @Test
    @Transactional
    void patchNonExistingInsuranceMaster() throws Exception {
        int databaseSizeBeforeUpdate = insuranceMasterRepository.findAll().size();
        insuranceMaster.setInsuranceId(count.incrementAndGet());

        // Create the InsuranceMaster
        InsuranceMasterDTO insuranceMasterDTO = insuranceMasterMapper.toDto(insuranceMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInsuranceMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, insuranceMasterDTO.getInsuranceId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(insuranceMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InsuranceMaster in the database
        List<InsuranceMaster> insuranceMasterList = insuranceMasterRepository.findAll();
        assertThat(insuranceMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchInsuranceMaster() throws Exception {
        int databaseSizeBeforeUpdate = insuranceMasterRepository.findAll().size();
        insuranceMaster.setInsuranceId(count.incrementAndGet());

        // Create the InsuranceMaster
        InsuranceMasterDTO insuranceMasterDTO = insuranceMasterMapper.toDto(insuranceMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInsuranceMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(insuranceMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InsuranceMaster in the database
        List<InsuranceMaster> insuranceMasterList = insuranceMasterRepository.findAll();
        assertThat(insuranceMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamInsuranceMaster() throws Exception {
        int databaseSizeBeforeUpdate = insuranceMasterRepository.findAll().size();
        insuranceMaster.setInsuranceId(count.incrementAndGet());

        // Create the InsuranceMaster
        InsuranceMasterDTO insuranceMasterDTO = insuranceMasterMapper.toDto(insuranceMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInsuranceMasterMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(insuranceMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the InsuranceMaster in the database
        List<InsuranceMaster> insuranceMasterList = insuranceMasterRepository.findAll();
        assertThat(insuranceMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteInsuranceMaster() throws Exception {
        // Initialize the database
        insuranceMasterRepository.saveAndFlush(insuranceMaster);

        int databaseSizeBeforeDelete = insuranceMasterRepository.findAll().size();

        // Delete the insuranceMaster
        restInsuranceMasterMockMvc
            .perform(delete(ENTITY_API_URL_ID, insuranceMaster.getInsuranceId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InsuranceMaster> insuranceMasterList = insuranceMasterRepository.findAll();
        assertThat(insuranceMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
