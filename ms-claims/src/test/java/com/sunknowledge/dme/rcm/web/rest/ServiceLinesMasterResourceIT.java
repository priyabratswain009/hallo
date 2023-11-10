package com.sunknowledge.dme.rcm.web.rest;

import static com.sunknowledge.dme.rcm.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ServiceLinesMaster;
import com.sunknowledge.dme.rcm.repository.ServiceLinesMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.ServiceLinesMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.ServiceLinesMasterMapper;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
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
 * Integration tests for the {@link ServiceLinesMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ServiceLinesMasterResourceIT {

    private static final Long DEFAULT_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID = 1L;
    private static final Long UPDATED_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID = 2L;

    private static final ZonedDateTime DEFAULT_ORIGINAL_DOS = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ORIGINAL_DOS = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DOS_TO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DOS_TO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_PROC_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PROC_CODE = "BBBBBBBBBB";

    private static final Double DEFAULT_CHARGE_AMT = 1D;
    private static final Double UPDATED_CHARGE_AMT = 2D;

    private static final String DEFAULT_ITEM_UOM = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_UOM = "BBBBBBBBBB";

    private static final String DEFAULT_MODIFIER_1 = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIER_1 = "BBBBBBBBBB";

    private static final String DEFAULT_MODIFIER_2 = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIER_2 = "BBBBBBBBBB";

    private static final String DEFAULT_MODIFIER_3 = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIER_3 = "BBBBBBBBBB";

    private static final String DEFAULT_MODIFIER_4 = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIER_4 = "BBBBBBBBBB";

    private static final String DEFAULT_ICD_POINTER = "AAAAAAAAAA";
    private static final String UPDATED_ICD_POINTER = "BBBBBBBBBB";

    private static final Long DEFAULT_QTY = 1L;
    private static final Long UPDATED_QTY = 2L;

    private static final Long DEFAULT_INSERTED_BY_ID = 1L;
    private static final Long UPDATED_INSERTED_BY_ID = 2L;

    private static final ZonedDateTime DEFAULT_INSERTED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_INSERTED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final ZonedDateTime DEFAULT_UPDATED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CLAIM_SERVICELINE_CONTROL_NO = "AAAAAAAAAA";
    private static final String UPDATED_CLAIM_SERVICELINE_CONTROL_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PROCEDURE_IDENTIFIER = "AAAAAAAAAA";
    private static final String UPDATED_PROCEDURE_IDENTIFIER = "BBBBBBBBBB";

    private static final String DEFAULT_INSERTED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INSERTED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERING_PROVIDER_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ORDERING_PROVIDER_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERING_PROVIDER_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ORDERING_PROVIDER_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERING_PROVIDER_NPI = "AAAAAAAAAA";
    private static final String UPDATED_ORDERING_PROVIDER_NPI = "BBBBBBBBBB";

    private static final String DEFAULT_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/service-lines-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{changeHealthPrimarySubmisionServicelinesId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ServiceLinesMasterRepository serviceLinesMasterRepository;

    @Autowired
    private ServiceLinesMasterMapper serviceLinesMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restServiceLinesMasterMockMvc;

    private ServiceLinesMaster serviceLinesMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ServiceLinesMaster createEntity(EntityManager em) {
        ServiceLinesMaster serviceLinesMaster = new ServiceLinesMaster()
            .changeHealthPrimarySubmisionMasterId(DEFAULT_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID)
            .originalDos(DEFAULT_ORIGINAL_DOS)
            .dosTo(DEFAULT_DOS_TO)
            .procCode(DEFAULT_PROC_CODE)
            .chargeAmt(DEFAULT_CHARGE_AMT)
            .itemUom(DEFAULT_ITEM_UOM)
            .modifier1(DEFAULT_MODIFIER_1)
            .modifier2(DEFAULT_MODIFIER_2)
            .modifier3(DEFAULT_MODIFIER_3)
            .modifier4(DEFAULT_MODIFIER_4)
            .icdPointer(DEFAULT_ICD_POINTER)
            .qty(DEFAULT_QTY)
            .insertedById(DEFAULT_INSERTED_BY_ID)
            .insertedDate(DEFAULT_INSERTED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .claimServicelineControlNo(DEFAULT_CLAIM_SERVICELINE_CONTROL_NO)
            .procedureIdentifier(DEFAULT_PROCEDURE_IDENTIFIER)
            .insertedByName(DEFAULT_INSERTED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .status(DEFAULT_STATUS)
            .orderingProviderFirstName(DEFAULT_ORDERING_PROVIDER_FIRST_NAME)
            .orderingProviderLastName(DEFAULT_ORDERING_PROVIDER_LAST_NAME)
            .orderingProviderNpi(DEFAULT_ORDERING_PROVIDER_NPI)
            .reference(DEFAULT_REFERENCE);
        return serviceLinesMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ServiceLinesMaster createUpdatedEntity(EntityManager em) {
        ServiceLinesMaster serviceLinesMaster = new ServiceLinesMaster()
            .changeHealthPrimarySubmisionMasterId(UPDATED_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID)
            .originalDos(UPDATED_ORIGINAL_DOS)
            .dosTo(UPDATED_DOS_TO)
            .procCode(UPDATED_PROC_CODE)
            .chargeAmt(UPDATED_CHARGE_AMT)
            .itemUom(UPDATED_ITEM_UOM)
            .modifier1(UPDATED_MODIFIER_1)
            .modifier2(UPDATED_MODIFIER_2)
            .modifier3(UPDATED_MODIFIER_3)
            .modifier4(UPDATED_MODIFIER_4)
            .icdPointer(UPDATED_ICD_POINTER)
            .qty(UPDATED_QTY)
            .insertedById(UPDATED_INSERTED_BY_ID)
            .insertedDate(UPDATED_INSERTED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .claimServicelineControlNo(UPDATED_CLAIM_SERVICELINE_CONTROL_NO)
            .procedureIdentifier(UPDATED_PROCEDURE_IDENTIFIER)
            .insertedByName(UPDATED_INSERTED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .status(UPDATED_STATUS)
            .orderingProviderFirstName(UPDATED_ORDERING_PROVIDER_FIRST_NAME)
            .orderingProviderLastName(UPDATED_ORDERING_PROVIDER_LAST_NAME)
            .orderingProviderNpi(UPDATED_ORDERING_PROVIDER_NPI)
            .reference(UPDATED_REFERENCE);
        return serviceLinesMaster;
    }

    @BeforeEach
    public void initTest() {
        serviceLinesMaster = createEntity(em);
    }

    @Test
    @Transactional
    void createServiceLinesMaster() throws Exception {
        int databaseSizeBeforeCreate = serviceLinesMasterRepository.findAll().size();
        // Create the ServiceLinesMaster
        ServiceLinesMasterDTO serviceLinesMasterDTO = serviceLinesMasterMapper.toDto(serviceLinesMaster);
        restServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceLinesMasterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ServiceLinesMaster in the database
        List<ServiceLinesMaster> serviceLinesMasterList = serviceLinesMasterRepository.findAll();
        assertThat(serviceLinesMasterList).hasSize(databaseSizeBeforeCreate + 1);
        ServiceLinesMaster testServiceLinesMaster = serviceLinesMasterList.get(serviceLinesMasterList.size() - 1);
        assertThat(testServiceLinesMaster.getChangeHealthPrimarySubmisionMasterId())
            .isEqualTo(DEFAULT_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID);
        assertThat(testServiceLinesMaster.getOriginalDos()).isEqualTo(DEFAULT_ORIGINAL_DOS);
        assertThat(testServiceLinesMaster.getDosTo()).isEqualTo(DEFAULT_DOS_TO);
        assertThat(testServiceLinesMaster.getProcCode()).isEqualTo(DEFAULT_PROC_CODE);
        assertThat(testServiceLinesMaster.getChargeAmt()).isEqualTo(DEFAULT_CHARGE_AMT);
        assertThat(testServiceLinesMaster.getItemUom()).isEqualTo(DEFAULT_ITEM_UOM);
        assertThat(testServiceLinesMaster.getModifier1()).isEqualTo(DEFAULT_MODIFIER_1);
        assertThat(testServiceLinesMaster.getModifier2()).isEqualTo(DEFAULT_MODIFIER_2);
        assertThat(testServiceLinesMaster.getModifier3()).isEqualTo(DEFAULT_MODIFIER_3);
        assertThat(testServiceLinesMaster.getModifier4()).isEqualTo(DEFAULT_MODIFIER_4);
        assertThat(testServiceLinesMaster.getIcdPointer()).isEqualTo(DEFAULT_ICD_POINTER);
        assertThat(testServiceLinesMaster.getQty()).isEqualTo(DEFAULT_QTY);
        assertThat(testServiceLinesMaster.getInsertedById()).isEqualTo(DEFAULT_INSERTED_BY_ID);
        assertThat(testServiceLinesMaster.getInsertedDate()).isEqualTo(DEFAULT_INSERTED_DATE);
        assertThat(testServiceLinesMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testServiceLinesMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testServiceLinesMaster.getClaimServicelineControlNo()).isEqualTo(DEFAULT_CLAIM_SERVICELINE_CONTROL_NO);
        assertThat(testServiceLinesMaster.getProcedureIdentifier()).isEqualTo(DEFAULT_PROCEDURE_IDENTIFIER);
        assertThat(testServiceLinesMaster.getInsertedByName()).isEqualTo(DEFAULT_INSERTED_BY_NAME);
        assertThat(testServiceLinesMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testServiceLinesMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testServiceLinesMaster.getOrderingProviderFirstName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testServiceLinesMaster.getOrderingProviderLastName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testServiceLinesMaster.getOrderingProviderNpi()).isEqualTo(DEFAULT_ORDERING_PROVIDER_NPI);
        assertThat(testServiceLinesMaster.getReference()).isEqualTo(DEFAULT_REFERENCE);
    }

    @Test
    @Transactional
    void createServiceLinesMasterWithExistingId() throws Exception {
        // Create the ServiceLinesMaster with an existing ID
        serviceLinesMaster.setChangeHealthPrimarySubmisionServicelinesId(1L);
        ServiceLinesMasterDTO serviceLinesMasterDTO = serviceLinesMasterMapper.toDto(serviceLinesMaster);

        int databaseSizeBeforeCreate = serviceLinesMasterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ServiceLinesMaster in the database
        List<ServiceLinesMaster> serviceLinesMasterList = serviceLinesMasterRepository.findAll();
        assertThat(serviceLinesMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkChangeHealthPrimarySubmisionMasterIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = serviceLinesMasterRepository.findAll().size();
        // set the field null
        serviceLinesMaster.setChangeHealthPrimarySubmisionMasterId(null);

        // Create the ServiceLinesMaster, which fails.
        ServiceLinesMasterDTO serviceLinesMasterDTO = serviceLinesMasterMapper.toDto(serviceLinesMaster);

        restServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<ServiceLinesMaster> serviceLinesMasterList = serviceLinesMasterRepository.findAll();
        assertThat(serviceLinesMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkOriginalDosIsRequired() throws Exception {
        int databaseSizeBeforeTest = serviceLinesMasterRepository.findAll().size();
        // set the field null
        serviceLinesMaster.setOriginalDos(null);

        // Create the ServiceLinesMaster, which fails.
        ServiceLinesMasterDTO serviceLinesMasterDTO = serviceLinesMasterMapper.toDto(serviceLinesMaster);

        restServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<ServiceLinesMaster> serviceLinesMasterList = serviceLinesMasterRepository.findAll();
        assertThat(serviceLinesMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDosToIsRequired() throws Exception {
        int databaseSizeBeforeTest = serviceLinesMasterRepository.findAll().size();
        // set the field null
        serviceLinesMaster.setDosTo(null);

        // Create the ServiceLinesMaster, which fails.
        ServiceLinesMasterDTO serviceLinesMasterDTO = serviceLinesMasterMapper.toDto(serviceLinesMaster);

        restServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<ServiceLinesMaster> serviceLinesMasterList = serviceLinesMasterRepository.findAll();
        assertThat(serviceLinesMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkProcCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = serviceLinesMasterRepository.findAll().size();
        // set the field null
        serviceLinesMaster.setProcCode(null);

        // Create the ServiceLinesMaster, which fails.
        ServiceLinesMasterDTO serviceLinesMasterDTO = serviceLinesMasterMapper.toDto(serviceLinesMaster);

        restServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<ServiceLinesMaster> serviceLinesMasterList = serviceLinesMasterRepository.findAll();
        assertThat(serviceLinesMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkChargeAmtIsRequired() throws Exception {
        int databaseSizeBeforeTest = serviceLinesMasterRepository.findAll().size();
        // set the field null
        serviceLinesMaster.setChargeAmt(null);

        // Create the ServiceLinesMaster, which fails.
        ServiceLinesMasterDTO serviceLinesMasterDTO = serviceLinesMasterMapper.toDto(serviceLinesMaster);

        restServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<ServiceLinesMaster> serviceLinesMasterList = serviceLinesMasterRepository.findAll();
        assertThat(serviceLinesMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkItemUomIsRequired() throws Exception {
        int databaseSizeBeforeTest = serviceLinesMasterRepository.findAll().size();
        // set the field null
        serviceLinesMaster.setItemUom(null);

        // Create the ServiceLinesMaster, which fails.
        ServiceLinesMasterDTO serviceLinesMasterDTO = serviceLinesMasterMapper.toDto(serviceLinesMaster);

        restServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<ServiceLinesMaster> serviceLinesMasterList = serviceLinesMasterRepository.findAll();
        assertThat(serviceLinesMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkModifier1IsRequired() throws Exception {
        int databaseSizeBeforeTest = serviceLinesMasterRepository.findAll().size();
        // set the field null
        serviceLinesMaster.setModifier1(null);

        // Create the ServiceLinesMaster, which fails.
        ServiceLinesMasterDTO serviceLinesMasterDTO = serviceLinesMasterMapper.toDto(serviceLinesMaster);

        restServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<ServiceLinesMaster> serviceLinesMasterList = serviceLinesMasterRepository.findAll();
        assertThat(serviceLinesMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkModifier2IsRequired() throws Exception {
        int databaseSizeBeforeTest = serviceLinesMasterRepository.findAll().size();
        // set the field null
        serviceLinesMaster.setModifier2(null);

        // Create the ServiceLinesMaster, which fails.
        ServiceLinesMasterDTO serviceLinesMasterDTO = serviceLinesMasterMapper.toDto(serviceLinesMaster);

        restServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<ServiceLinesMaster> serviceLinesMasterList = serviceLinesMasterRepository.findAll();
        assertThat(serviceLinesMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkModifier3IsRequired() throws Exception {
        int databaseSizeBeforeTest = serviceLinesMasterRepository.findAll().size();
        // set the field null
        serviceLinesMaster.setModifier3(null);

        // Create the ServiceLinesMaster, which fails.
        ServiceLinesMasterDTO serviceLinesMasterDTO = serviceLinesMasterMapper.toDto(serviceLinesMaster);

        restServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<ServiceLinesMaster> serviceLinesMasterList = serviceLinesMasterRepository.findAll();
        assertThat(serviceLinesMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkModifier4IsRequired() throws Exception {
        int databaseSizeBeforeTest = serviceLinesMasterRepository.findAll().size();
        // set the field null
        serviceLinesMaster.setModifier4(null);

        // Create the ServiceLinesMaster, which fails.
        ServiceLinesMasterDTO serviceLinesMasterDTO = serviceLinesMasterMapper.toDto(serviceLinesMaster);

        restServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<ServiceLinesMaster> serviceLinesMasterList = serviceLinesMasterRepository.findAll();
        assertThat(serviceLinesMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIcdPointerIsRequired() throws Exception {
        int databaseSizeBeforeTest = serviceLinesMasterRepository.findAll().size();
        // set the field null
        serviceLinesMaster.setIcdPointer(null);

        // Create the ServiceLinesMaster, which fails.
        ServiceLinesMasterDTO serviceLinesMasterDTO = serviceLinesMasterMapper.toDto(serviceLinesMaster);

        restServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<ServiceLinesMaster> serviceLinesMasterList = serviceLinesMasterRepository.findAll();
        assertThat(serviceLinesMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkQtyIsRequired() throws Exception {
        int databaseSizeBeforeTest = serviceLinesMasterRepository.findAll().size();
        // set the field null
        serviceLinesMaster.setQty(null);

        // Create the ServiceLinesMaster, which fails.
        ServiceLinesMasterDTO serviceLinesMasterDTO = serviceLinesMasterMapper.toDto(serviceLinesMaster);

        restServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<ServiceLinesMaster> serviceLinesMasterList = serviceLinesMasterRepository.findAll();
        assertThat(serviceLinesMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkProcedureIdentifierIsRequired() throws Exception {
        int databaseSizeBeforeTest = serviceLinesMasterRepository.findAll().size();
        // set the field null
        serviceLinesMaster.setProcedureIdentifier(null);

        // Create the ServiceLinesMaster, which fails.
        ServiceLinesMasterDTO serviceLinesMasterDTO = serviceLinesMasterMapper.toDto(serviceLinesMaster);

        restServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<ServiceLinesMaster> serviceLinesMasterList = serviceLinesMasterRepository.findAll();
        assertThat(serviceLinesMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkOrderingProviderFirstNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = serviceLinesMasterRepository.findAll().size();
        // set the field null
        serviceLinesMaster.setOrderingProviderFirstName(null);

        // Create the ServiceLinesMaster, which fails.
        ServiceLinesMasterDTO serviceLinesMasterDTO = serviceLinesMasterMapper.toDto(serviceLinesMaster);

        restServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<ServiceLinesMaster> serviceLinesMasterList = serviceLinesMasterRepository.findAll();
        assertThat(serviceLinesMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllServiceLinesMasters() throws Exception {
        // Initialize the database
        serviceLinesMasterRepository.saveAndFlush(serviceLinesMaster);

        // Get all the serviceLinesMasterList
        restServiceLinesMasterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=changeHealthPrimarySubmisionServicelinesId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].changeHealthPrimarySubmisionServicelinesId")
                    .value(hasItem(serviceLinesMaster.getChangeHealthPrimarySubmisionServicelinesId().intValue()))
            )
            .andExpect(
                jsonPath("$.[*].changeHealthPrimarySubmisionMasterId")
                    .value(hasItem(DEFAULT_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID.intValue()))
            )
            .andExpect(jsonPath("$.[*].originalDos").value(hasItem(sameInstant(DEFAULT_ORIGINAL_DOS))))
            .andExpect(jsonPath("$.[*].dosTo").value(hasItem(sameInstant(DEFAULT_DOS_TO))))
            .andExpect(jsonPath("$.[*].procCode").value(hasItem(DEFAULT_PROC_CODE)))
            .andExpect(jsonPath("$.[*].chargeAmt").value(hasItem(DEFAULT_CHARGE_AMT.doubleValue())))
            .andExpect(jsonPath("$.[*].itemUom").value(hasItem(DEFAULT_ITEM_UOM)))
            .andExpect(jsonPath("$.[*].modifier1").value(hasItem(DEFAULT_MODIFIER_1)))
            .andExpect(jsonPath("$.[*].modifier2").value(hasItem(DEFAULT_MODIFIER_2)))
            .andExpect(jsonPath("$.[*].modifier3").value(hasItem(DEFAULT_MODIFIER_3)))
            .andExpect(jsonPath("$.[*].modifier4").value(hasItem(DEFAULT_MODIFIER_4)))
            .andExpect(jsonPath("$.[*].icdPointer").value(hasItem(DEFAULT_ICD_POINTER)))
            .andExpect(jsonPath("$.[*].qty").value(hasItem(DEFAULT_QTY.intValue())))
            .andExpect(jsonPath("$.[*].insertedById").value(hasItem(DEFAULT_INSERTED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].insertedDate").value(hasItem(sameInstant(DEFAULT_INSERTED_DATE))))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(sameInstant(DEFAULT_UPDATED_DATE))))
            .andExpect(jsonPath("$.[*].claimServicelineControlNo").value(hasItem(DEFAULT_CLAIM_SERVICELINE_CONTROL_NO)))
            .andExpect(jsonPath("$.[*].procedureIdentifier").value(hasItem(DEFAULT_PROCEDURE_IDENTIFIER)))
            .andExpect(jsonPath("$.[*].insertedByName").value(hasItem(DEFAULT_INSERTED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].orderingProviderFirstName").value(hasItem(DEFAULT_ORDERING_PROVIDER_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].orderingProviderLastName").value(hasItem(DEFAULT_ORDERING_PROVIDER_LAST_NAME)))
            .andExpect(jsonPath("$.[*].orderingProviderNpi").value(hasItem(DEFAULT_ORDERING_PROVIDER_NPI)))
            .andExpect(jsonPath("$.[*].reference").value(hasItem(DEFAULT_REFERENCE)));
    }

    @Test
    @Transactional
    void getServiceLinesMaster() throws Exception {
        // Initialize the database
        serviceLinesMasterRepository.saveAndFlush(serviceLinesMaster);

        // Get the serviceLinesMaster
        restServiceLinesMasterMockMvc
            .perform(get(ENTITY_API_URL_ID, serviceLinesMaster.getChangeHealthPrimarySubmisionServicelinesId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.changeHealthPrimarySubmisionServicelinesId")
                    .value(serviceLinesMaster.getChangeHealthPrimarySubmisionServicelinesId().intValue())
            )
            .andExpect(
                jsonPath("$.changeHealthPrimarySubmisionMasterId").value(DEFAULT_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID.intValue())
            )
            .andExpect(jsonPath("$.originalDos").value(sameInstant(DEFAULT_ORIGINAL_DOS)))
            .andExpect(jsonPath("$.dosTo").value(sameInstant(DEFAULT_DOS_TO)))
            .andExpect(jsonPath("$.procCode").value(DEFAULT_PROC_CODE))
            .andExpect(jsonPath("$.chargeAmt").value(DEFAULT_CHARGE_AMT.doubleValue()))
            .andExpect(jsonPath("$.itemUom").value(DEFAULT_ITEM_UOM))
            .andExpect(jsonPath("$.modifier1").value(DEFAULT_MODIFIER_1))
            .andExpect(jsonPath("$.modifier2").value(DEFAULT_MODIFIER_2))
            .andExpect(jsonPath("$.modifier3").value(DEFAULT_MODIFIER_3))
            .andExpect(jsonPath("$.modifier4").value(DEFAULT_MODIFIER_4))
            .andExpect(jsonPath("$.icdPointer").value(DEFAULT_ICD_POINTER))
            .andExpect(jsonPath("$.qty").value(DEFAULT_QTY.intValue()))
            .andExpect(jsonPath("$.insertedById").value(DEFAULT_INSERTED_BY_ID.intValue()))
            .andExpect(jsonPath("$.insertedDate").value(sameInstant(DEFAULT_INSERTED_DATE)))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedDate").value(sameInstant(DEFAULT_UPDATED_DATE)))
            .andExpect(jsonPath("$.claimServicelineControlNo").value(DEFAULT_CLAIM_SERVICELINE_CONTROL_NO))
            .andExpect(jsonPath("$.procedureIdentifier").value(DEFAULT_PROCEDURE_IDENTIFIER))
            .andExpect(jsonPath("$.insertedByName").value(DEFAULT_INSERTED_BY_NAME))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.orderingProviderFirstName").value(DEFAULT_ORDERING_PROVIDER_FIRST_NAME))
            .andExpect(jsonPath("$.orderingProviderLastName").value(DEFAULT_ORDERING_PROVIDER_LAST_NAME))
            .andExpect(jsonPath("$.orderingProviderNpi").value(DEFAULT_ORDERING_PROVIDER_NPI))
            .andExpect(jsonPath("$.reference").value(DEFAULT_REFERENCE));
    }

    @Test
    @Transactional
    void getNonExistingServiceLinesMaster() throws Exception {
        // Get the serviceLinesMaster
        restServiceLinesMasterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewServiceLinesMaster() throws Exception {
        // Initialize the database
        serviceLinesMasterRepository.saveAndFlush(serviceLinesMaster);

        int databaseSizeBeforeUpdate = serviceLinesMasterRepository.findAll().size();

        // Update the serviceLinesMaster
        ServiceLinesMaster updatedServiceLinesMaster = serviceLinesMasterRepository
            .findById(serviceLinesMaster.getChangeHealthPrimarySubmisionServicelinesId())
            .get();
        // Disconnect from session so that the updates on updatedServiceLinesMaster are not directly saved in db
        em.detach(updatedServiceLinesMaster);
        updatedServiceLinesMaster
            .changeHealthPrimarySubmisionMasterId(UPDATED_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID)
            .originalDos(UPDATED_ORIGINAL_DOS)
            .dosTo(UPDATED_DOS_TO)
            .procCode(UPDATED_PROC_CODE)
            .chargeAmt(UPDATED_CHARGE_AMT)
            .itemUom(UPDATED_ITEM_UOM)
            .modifier1(UPDATED_MODIFIER_1)
            .modifier2(UPDATED_MODIFIER_2)
            .modifier3(UPDATED_MODIFIER_3)
            .modifier4(UPDATED_MODIFIER_4)
            .icdPointer(UPDATED_ICD_POINTER)
            .qty(UPDATED_QTY)
            .insertedById(UPDATED_INSERTED_BY_ID)
            .insertedDate(UPDATED_INSERTED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .claimServicelineControlNo(UPDATED_CLAIM_SERVICELINE_CONTROL_NO)
            .procedureIdentifier(UPDATED_PROCEDURE_IDENTIFIER)
            .insertedByName(UPDATED_INSERTED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .status(UPDATED_STATUS)
            .orderingProviderFirstName(UPDATED_ORDERING_PROVIDER_FIRST_NAME)
            .orderingProviderLastName(UPDATED_ORDERING_PROVIDER_LAST_NAME)
            .orderingProviderNpi(UPDATED_ORDERING_PROVIDER_NPI)
            .reference(UPDATED_REFERENCE);
        ServiceLinesMasterDTO serviceLinesMasterDTO = serviceLinesMasterMapper.toDto(updatedServiceLinesMaster);

        restServiceLinesMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, serviceLinesMasterDTO.getChangeHealthPrimarySubmisionServicelinesId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceLinesMasterDTO))
            )
            .andExpect(status().isOk());

        // Validate the ServiceLinesMaster in the database
        List<ServiceLinesMaster> serviceLinesMasterList = serviceLinesMasterRepository.findAll();
        assertThat(serviceLinesMasterList).hasSize(databaseSizeBeforeUpdate);
        ServiceLinesMaster testServiceLinesMaster = serviceLinesMasterList.get(serviceLinesMasterList.size() - 1);
        assertThat(testServiceLinesMaster.getChangeHealthPrimarySubmisionMasterId())
            .isEqualTo(UPDATED_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID);
        assertThat(testServiceLinesMaster.getOriginalDos()).isEqualTo(UPDATED_ORIGINAL_DOS);
        assertThat(testServiceLinesMaster.getDosTo()).isEqualTo(UPDATED_DOS_TO);
        assertThat(testServiceLinesMaster.getProcCode()).isEqualTo(UPDATED_PROC_CODE);
        assertThat(testServiceLinesMaster.getChargeAmt()).isEqualTo(UPDATED_CHARGE_AMT);
        assertThat(testServiceLinesMaster.getItemUom()).isEqualTo(UPDATED_ITEM_UOM);
        assertThat(testServiceLinesMaster.getModifier1()).isEqualTo(UPDATED_MODIFIER_1);
        assertThat(testServiceLinesMaster.getModifier2()).isEqualTo(UPDATED_MODIFIER_2);
        assertThat(testServiceLinesMaster.getModifier3()).isEqualTo(UPDATED_MODIFIER_3);
        assertThat(testServiceLinesMaster.getModifier4()).isEqualTo(UPDATED_MODIFIER_4);
        assertThat(testServiceLinesMaster.getIcdPointer()).isEqualTo(UPDATED_ICD_POINTER);
        assertThat(testServiceLinesMaster.getQty()).isEqualTo(UPDATED_QTY);
        assertThat(testServiceLinesMaster.getInsertedById()).isEqualTo(UPDATED_INSERTED_BY_ID);
        assertThat(testServiceLinesMaster.getInsertedDate()).isEqualTo(UPDATED_INSERTED_DATE);
        assertThat(testServiceLinesMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testServiceLinesMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testServiceLinesMaster.getClaimServicelineControlNo()).isEqualTo(UPDATED_CLAIM_SERVICELINE_CONTROL_NO);
        assertThat(testServiceLinesMaster.getProcedureIdentifier()).isEqualTo(UPDATED_PROCEDURE_IDENTIFIER);
        assertThat(testServiceLinesMaster.getInsertedByName()).isEqualTo(UPDATED_INSERTED_BY_NAME);
        assertThat(testServiceLinesMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testServiceLinesMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testServiceLinesMaster.getOrderingProviderFirstName()).isEqualTo(UPDATED_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testServiceLinesMaster.getOrderingProviderLastName()).isEqualTo(UPDATED_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testServiceLinesMaster.getOrderingProviderNpi()).isEqualTo(UPDATED_ORDERING_PROVIDER_NPI);
        assertThat(testServiceLinesMaster.getReference()).isEqualTo(UPDATED_REFERENCE);
    }

    @Test
    @Transactional
    void putNonExistingServiceLinesMaster() throws Exception {
        int databaseSizeBeforeUpdate = serviceLinesMasterRepository.findAll().size();
        serviceLinesMaster.setChangeHealthPrimarySubmisionServicelinesId(count.incrementAndGet());

        // Create the ServiceLinesMaster
        ServiceLinesMasterDTO serviceLinesMasterDTO = serviceLinesMasterMapper.toDto(serviceLinesMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restServiceLinesMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, serviceLinesMasterDTO.getChangeHealthPrimarySubmisionServicelinesId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ServiceLinesMaster in the database
        List<ServiceLinesMaster> serviceLinesMasterList = serviceLinesMasterRepository.findAll();
        assertThat(serviceLinesMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchServiceLinesMaster() throws Exception {
        int databaseSizeBeforeUpdate = serviceLinesMasterRepository.findAll().size();
        serviceLinesMaster.setChangeHealthPrimarySubmisionServicelinesId(count.incrementAndGet());

        // Create the ServiceLinesMaster
        ServiceLinesMasterDTO serviceLinesMasterDTO = serviceLinesMasterMapper.toDto(serviceLinesMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServiceLinesMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ServiceLinesMaster in the database
        List<ServiceLinesMaster> serviceLinesMasterList = serviceLinesMasterRepository.findAll();
        assertThat(serviceLinesMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamServiceLinesMaster() throws Exception {
        int databaseSizeBeforeUpdate = serviceLinesMasterRepository.findAll().size();
        serviceLinesMaster.setChangeHealthPrimarySubmisionServicelinesId(count.incrementAndGet());

        // Create the ServiceLinesMaster
        ServiceLinesMasterDTO serviceLinesMasterDTO = serviceLinesMasterMapper.toDto(serviceLinesMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServiceLinesMasterMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceLinesMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ServiceLinesMaster in the database
        List<ServiceLinesMaster> serviceLinesMasterList = serviceLinesMasterRepository.findAll();
        assertThat(serviceLinesMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateServiceLinesMasterWithPatch() throws Exception {
        // Initialize the database
        serviceLinesMasterRepository.saveAndFlush(serviceLinesMaster);

        int databaseSizeBeforeUpdate = serviceLinesMasterRepository.findAll().size();

        // Update the serviceLinesMaster using partial update
        ServiceLinesMaster partialUpdatedServiceLinesMaster = new ServiceLinesMaster();
        partialUpdatedServiceLinesMaster.setChangeHealthPrimarySubmisionServicelinesId(
            serviceLinesMaster.getChangeHealthPrimarySubmisionServicelinesId()
        );

        partialUpdatedServiceLinesMaster
            .chargeAmt(UPDATED_CHARGE_AMT)
            .itemUom(UPDATED_ITEM_UOM)
            .modifier1(UPDATED_MODIFIER_1)
            .modifier2(UPDATED_MODIFIER_2)
            .insertedById(UPDATED_INSERTED_BY_ID)
            .insertedDate(UPDATED_INSERTED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .claimServicelineControlNo(UPDATED_CLAIM_SERVICELINE_CONTROL_NO)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .orderingProviderFirstName(UPDATED_ORDERING_PROVIDER_FIRST_NAME)
            .orderingProviderLastName(UPDATED_ORDERING_PROVIDER_LAST_NAME)
            .orderingProviderNpi(UPDATED_ORDERING_PROVIDER_NPI);

        restServiceLinesMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedServiceLinesMaster.getChangeHealthPrimarySubmisionServicelinesId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedServiceLinesMaster))
            )
            .andExpect(status().isOk());

        // Validate the ServiceLinesMaster in the database
        List<ServiceLinesMaster> serviceLinesMasterList = serviceLinesMasterRepository.findAll();
        assertThat(serviceLinesMasterList).hasSize(databaseSizeBeforeUpdate);
        ServiceLinesMaster testServiceLinesMaster = serviceLinesMasterList.get(serviceLinesMasterList.size() - 1);
        assertThat(testServiceLinesMaster.getChangeHealthPrimarySubmisionMasterId())
            .isEqualTo(DEFAULT_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID);
        assertThat(testServiceLinesMaster.getOriginalDos()).isEqualTo(DEFAULT_ORIGINAL_DOS);
        assertThat(testServiceLinesMaster.getDosTo()).isEqualTo(DEFAULT_DOS_TO);
        assertThat(testServiceLinesMaster.getProcCode()).isEqualTo(DEFAULT_PROC_CODE);
        assertThat(testServiceLinesMaster.getChargeAmt()).isEqualTo(UPDATED_CHARGE_AMT);
        assertThat(testServiceLinesMaster.getItemUom()).isEqualTo(UPDATED_ITEM_UOM);
        assertThat(testServiceLinesMaster.getModifier1()).isEqualTo(UPDATED_MODIFIER_1);
        assertThat(testServiceLinesMaster.getModifier2()).isEqualTo(UPDATED_MODIFIER_2);
        assertThat(testServiceLinesMaster.getModifier3()).isEqualTo(DEFAULT_MODIFIER_3);
        assertThat(testServiceLinesMaster.getModifier4()).isEqualTo(DEFAULT_MODIFIER_4);
        assertThat(testServiceLinesMaster.getIcdPointer()).isEqualTo(DEFAULT_ICD_POINTER);
        assertThat(testServiceLinesMaster.getQty()).isEqualTo(DEFAULT_QTY);
        assertThat(testServiceLinesMaster.getInsertedById()).isEqualTo(UPDATED_INSERTED_BY_ID);
        assertThat(testServiceLinesMaster.getInsertedDate()).isEqualTo(UPDATED_INSERTED_DATE);
        assertThat(testServiceLinesMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testServiceLinesMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testServiceLinesMaster.getClaimServicelineControlNo()).isEqualTo(UPDATED_CLAIM_SERVICELINE_CONTROL_NO);
        assertThat(testServiceLinesMaster.getProcedureIdentifier()).isEqualTo(DEFAULT_PROCEDURE_IDENTIFIER);
        assertThat(testServiceLinesMaster.getInsertedByName()).isEqualTo(DEFAULT_INSERTED_BY_NAME);
        assertThat(testServiceLinesMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testServiceLinesMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testServiceLinesMaster.getOrderingProviderFirstName()).isEqualTo(UPDATED_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testServiceLinesMaster.getOrderingProviderLastName()).isEqualTo(UPDATED_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testServiceLinesMaster.getOrderingProviderNpi()).isEqualTo(UPDATED_ORDERING_PROVIDER_NPI);
        assertThat(testServiceLinesMaster.getReference()).isEqualTo(DEFAULT_REFERENCE);
    }

    @Test
    @Transactional
    void fullUpdateServiceLinesMasterWithPatch() throws Exception {
        // Initialize the database
        serviceLinesMasterRepository.saveAndFlush(serviceLinesMaster);

        int databaseSizeBeforeUpdate = serviceLinesMasterRepository.findAll().size();

        // Update the serviceLinesMaster using partial update
        ServiceLinesMaster partialUpdatedServiceLinesMaster = new ServiceLinesMaster();
        partialUpdatedServiceLinesMaster.setChangeHealthPrimarySubmisionServicelinesId(
            serviceLinesMaster.getChangeHealthPrimarySubmisionServicelinesId()
        );

        partialUpdatedServiceLinesMaster
            .changeHealthPrimarySubmisionMasterId(UPDATED_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID)
            .originalDos(UPDATED_ORIGINAL_DOS)
            .dosTo(UPDATED_DOS_TO)
            .procCode(UPDATED_PROC_CODE)
            .chargeAmt(UPDATED_CHARGE_AMT)
            .itemUom(UPDATED_ITEM_UOM)
            .modifier1(UPDATED_MODIFIER_1)
            .modifier2(UPDATED_MODIFIER_2)
            .modifier3(UPDATED_MODIFIER_3)
            .modifier4(UPDATED_MODIFIER_4)
            .icdPointer(UPDATED_ICD_POINTER)
            .qty(UPDATED_QTY)
            .insertedById(UPDATED_INSERTED_BY_ID)
            .insertedDate(UPDATED_INSERTED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .claimServicelineControlNo(UPDATED_CLAIM_SERVICELINE_CONTROL_NO)
            .procedureIdentifier(UPDATED_PROCEDURE_IDENTIFIER)
            .insertedByName(UPDATED_INSERTED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .status(UPDATED_STATUS)
            .orderingProviderFirstName(UPDATED_ORDERING_PROVIDER_FIRST_NAME)
            .orderingProviderLastName(UPDATED_ORDERING_PROVIDER_LAST_NAME)
            .orderingProviderNpi(UPDATED_ORDERING_PROVIDER_NPI)
            .reference(UPDATED_REFERENCE);

        restServiceLinesMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedServiceLinesMaster.getChangeHealthPrimarySubmisionServicelinesId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedServiceLinesMaster))
            )
            .andExpect(status().isOk());

        // Validate the ServiceLinesMaster in the database
        List<ServiceLinesMaster> serviceLinesMasterList = serviceLinesMasterRepository.findAll();
        assertThat(serviceLinesMasterList).hasSize(databaseSizeBeforeUpdate);
        ServiceLinesMaster testServiceLinesMaster = serviceLinesMasterList.get(serviceLinesMasterList.size() - 1);
        assertThat(testServiceLinesMaster.getChangeHealthPrimarySubmisionMasterId())
            .isEqualTo(UPDATED_CHANGE_HEALTH_PRIMARY_SUBMISION_MASTER_ID);
        assertThat(testServiceLinesMaster.getOriginalDos()).isEqualTo(UPDATED_ORIGINAL_DOS);
        assertThat(testServiceLinesMaster.getDosTo()).isEqualTo(UPDATED_DOS_TO);
        assertThat(testServiceLinesMaster.getProcCode()).isEqualTo(UPDATED_PROC_CODE);
        assertThat(testServiceLinesMaster.getChargeAmt()).isEqualTo(UPDATED_CHARGE_AMT);
        assertThat(testServiceLinesMaster.getItemUom()).isEqualTo(UPDATED_ITEM_UOM);
        assertThat(testServiceLinesMaster.getModifier1()).isEqualTo(UPDATED_MODIFIER_1);
        assertThat(testServiceLinesMaster.getModifier2()).isEqualTo(UPDATED_MODIFIER_2);
        assertThat(testServiceLinesMaster.getModifier3()).isEqualTo(UPDATED_MODIFIER_3);
        assertThat(testServiceLinesMaster.getModifier4()).isEqualTo(UPDATED_MODIFIER_4);
        assertThat(testServiceLinesMaster.getIcdPointer()).isEqualTo(UPDATED_ICD_POINTER);
        assertThat(testServiceLinesMaster.getQty()).isEqualTo(UPDATED_QTY);
        assertThat(testServiceLinesMaster.getInsertedById()).isEqualTo(UPDATED_INSERTED_BY_ID);
        assertThat(testServiceLinesMaster.getInsertedDate()).isEqualTo(UPDATED_INSERTED_DATE);
        assertThat(testServiceLinesMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testServiceLinesMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testServiceLinesMaster.getClaimServicelineControlNo()).isEqualTo(UPDATED_CLAIM_SERVICELINE_CONTROL_NO);
        assertThat(testServiceLinesMaster.getProcedureIdentifier()).isEqualTo(UPDATED_PROCEDURE_IDENTIFIER);
        assertThat(testServiceLinesMaster.getInsertedByName()).isEqualTo(UPDATED_INSERTED_BY_NAME);
        assertThat(testServiceLinesMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testServiceLinesMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testServiceLinesMaster.getOrderingProviderFirstName()).isEqualTo(UPDATED_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testServiceLinesMaster.getOrderingProviderLastName()).isEqualTo(UPDATED_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testServiceLinesMaster.getOrderingProviderNpi()).isEqualTo(UPDATED_ORDERING_PROVIDER_NPI);
        assertThat(testServiceLinesMaster.getReference()).isEqualTo(UPDATED_REFERENCE);
    }

    @Test
    @Transactional
    void patchNonExistingServiceLinesMaster() throws Exception {
        int databaseSizeBeforeUpdate = serviceLinesMasterRepository.findAll().size();
        serviceLinesMaster.setChangeHealthPrimarySubmisionServicelinesId(count.incrementAndGet());

        // Create the ServiceLinesMaster
        ServiceLinesMasterDTO serviceLinesMasterDTO = serviceLinesMasterMapper.toDto(serviceLinesMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restServiceLinesMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, serviceLinesMasterDTO.getChangeHealthPrimarySubmisionServicelinesId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(serviceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ServiceLinesMaster in the database
        List<ServiceLinesMaster> serviceLinesMasterList = serviceLinesMasterRepository.findAll();
        assertThat(serviceLinesMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchServiceLinesMaster() throws Exception {
        int databaseSizeBeforeUpdate = serviceLinesMasterRepository.findAll().size();
        serviceLinesMaster.setChangeHealthPrimarySubmisionServicelinesId(count.incrementAndGet());

        // Create the ServiceLinesMaster
        ServiceLinesMasterDTO serviceLinesMasterDTO = serviceLinesMasterMapper.toDto(serviceLinesMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServiceLinesMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(serviceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ServiceLinesMaster in the database
        List<ServiceLinesMaster> serviceLinesMasterList = serviceLinesMasterRepository.findAll();
        assertThat(serviceLinesMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamServiceLinesMaster() throws Exception {
        int databaseSizeBeforeUpdate = serviceLinesMasterRepository.findAll().size();
        serviceLinesMaster.setChangeHealthPrimarySubmisionServicelinesId(count.incrementAndGet());

        // Create the ServiceLinesMaster
        ServiceLinesMasterDTO serviceLinesMasterDTO = serviceLinesMasterMapper.toDto(serviceLinesMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServiceLinesMasterMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(serviceLinesMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ServiceLinesMaster in the database
        List<ServiceLinesMaster> serviceLinesMasterList = serviceLinesMasterRepository.findAll();
        assertThat(serviceLinesMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteServiceLinesMaster() throws Exception {
        // Initialize the database
        serviceLinesMasterRepository.saveAndFlush(serviceLinesMaster);

        int databaseSizeBeforeDelete = serviceLinesMasterRepository.findAll().size();

        // Delete the serviceLinesMaster
        restServiceLinesMasterMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, serviceLinesMaster.getChangeHealthPrimarySubmisionServicelinesId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ServiceLinesMaster> serviceLinesMasterList = serviceLinesMasterRepository.findAll();
        assertThat(serviceLinesMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
