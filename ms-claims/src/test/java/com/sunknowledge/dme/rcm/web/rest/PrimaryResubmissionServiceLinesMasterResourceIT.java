package com.sunknowledge.dme.rcm.web.rest;

import static com.sunknowledge.dme.rcm.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.PrimaryResubmissionServiceLinesMaster;
import com.sunknowledge.dme.rcm.repository.PrimaryResubmissionServiceLinesMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.PrimaryResubmissionServiceLinesMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.PrimaryResubmissionServiceLinesMasterMapper;
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
 * Integration tests for the {@link PrimaryResubmissionServiceLinesMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PrimaryResubmissionServiceLinesMasterResourceIT {

    private static final Long DEFAULT_CHANGE_HEALTH_PRIMARY_RESUBMISION_MASTER_ID = 1L;
    private static final Long UPDATED_CHANGE_HEALTH_PRIMARY_RESUBMISION_MASTER_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/primary-resubmission-service-lines-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{changeHealthPrimaryResubmisionServicelinesId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PrimaryResubmissionServiceLinesMasterRepository primaryResubmissionServiceLinesMasterRepository;

    @Autowired
    private PrimaryResubmissionServiceLinesMasterMapper primaryResubmissionServiceLinesMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPrimaryResubmissionServiceLinesMasterMockMvc;

    private PrimaryResubmissionServiceLinesMaster primaryResubmissionServiceLinesMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PrimaryResubmissionServiceLinesMaster createEntity(EntityManager em) {
        PrimaryResubmissionServiceLinesMaster primaryResubmissionServiceLinesMaster = new PrimaryResubmissionServiceLinesMaster()
            .changeHealthPrimaryResubmisionMasterId(DEFAULT_CHANGE_HEALTH_PRIMARY_RESUBMISION_MASTER_ID)
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
        return primaryResubmissionServiceLinesMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PrimaryResubmissionServiceLinesMaster createUpdatedEntity(EntityManager em) {
        PrimaryResubmissionServiceLinesMaster primaryResubmissionServiceLinesMaster = new PrimaryResubmissionServiceLinesMaster()
            .changeHealthPrimaryResubmisionMasterId(UPDATED_CHANGE_HEALTH_PRIMARY_RESUBMISION_MASTER_ID)
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
        return primaryResubmissionServiceLinesMaster;
    }

    @BeforeEach
    public void initTest() {
        primaryResubmissionServiceLinesMaster = createEntity(em);
    }

    @Test
    @Transactional
    void createPrimaryResubmissionServiceLinesMaster() throws Exception {
        int databaseSizeBeforeCreate = primaryResubmissionServiceLinesMasterRepository.findAll().size();
        // Create the PrimaryResubmissionServiceLinesMaster
        PrimaryResubmissionServiceLinesMasterDTO primaryResubmissionServiceLinesMasterDTO = primaryResubmissionServiceLinesMasterMapper.toDto(
            primaryResubmissionServiceLinesMaster
        );
        restPrimaryResubmissionServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryResubmissionServiceLinesMasterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the PrimaryResubmissionServiceLinesMaster in the database
        List<PrimaryResubmissionServiceLinesMaster> primaryResubmissionServiceLinesMasterList = primaryResubmissionServiceLinesMasterRepository.findAll();
        assertThat(primaryResubmissionServiceLinesMasterList).hasSize(databaseSizeBeforeCreate + 1);
        PrimaryResubmissionServiceLinesMaster testPrimaryResubmissionServiceLinesMaster = primaryResubmissionServiceLinesMasterList.get(
            primaryResubmissionServiceLinesMasterList.size() - 1
        );
        assertThat(testPrimaryResubmissionServiceLinesMaster.getChangeHealthPrimaryResubmisionMasterId())
            .isEqualTo(DEFAULT_CHANGE_HEALTH_PRIMARY_RESUBMISION_MASTER_ID);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getOriginalDos()).isEqualTo(DEFAULT_ORIGINAL_DOS);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getDosTo()).isEqualTo(DEFAULT_DOS_TO);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getProcCode()).isEqualTo(DEFAULT_PROC_CODE);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getChargeAmt()).isEqualTo(DEFAULT_CHARGE_AMT);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getItemUom()).isEqualTo(DEFAULT_ITEM_UOM);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getModifier1()).isEqualTo(DEFAULT_MODIFIER_1);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getModifier2()).isEqualTo(DEFAULT_MODIFIER_2);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getModifier3()).isEqualTo(DEFAULT_MODIFIER_3);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getModifier4()).isEqualTo(DEFAULT_MODIFIER_4);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getIcdPointer()).isEqualTo(DEFAULT_ICD_POINTER);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getQty()).isEqualTo(DEFAULT_QTY);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getInsertedById()).isEqualTo(DEFAULT_INSERTED_BY_ID);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getInsertedDate()).isEqualTo(DEFAULT_INSERTED_DATE);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getClaimServicelineControlNo())
            .isEqualTo(DEFAULT_CLAIM_SERVICELINE_CONTROL_NO);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getProcedureIdentifier()).isEqualTo(DEFAULT_PROCEDURE_IDENTIFIER);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getInsertedByName()).isEqualTo(DEFAULT_INSERTED_BY_NAME);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getOrderingProviderFirstName())
            .isEqualTo(DEFAULT_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getOrderingProviderLastName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getOrderingProviderNpi()).isEqualTo(DEFAULT_ORDERING_PROVIDER_NPI);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getReference()).isEqualTo(DEFAULT_REFERENCE);
    }

    @Test
    @Transactional
    void createPrimaryResubmissionServiceLinesMasterWithExistingId() throws Exception {
        // Create the PrimaryResubmissionServiceLinesMaster with an existing ID
        primaryResubmissionServiceLinesMaster.setChangeHealthPrimaryResubmisionServicelinesId(1L);
        PrimaryResubmissionServiceLinesMasterDTO primaryResubmissionServiceLinesMasterDTO = primaryResubmissionServiceLinesMasterMapper.toDto(
            primaryResubmissionServiceLinesMaster
        );

        int databaseSizeBeforeCreate = primaryResubmissionServiceLinesMasterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPrimaryResubmissionServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryResubmissionServiceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PrimaryResubmissionServiceLinesMaster in the database
        List<PrimaryResubmissionServiceLinesMaster> primaryResubmissionServiceLinesMasterList = primaryResubmissionServiceLinesMasterRepository.findAll();
        assertThat(primaryResubmissionServiceLinesMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkChangeHealthPrimaryResubmisionMasterIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryResubmissionServiceLinesMasterRepository.findAll().size();
        // set the field null
        primaryResubmissionServiceLinesMaster.setChangeHealthPrimaryResubmisionMasterId(null);

        // Create the PrimaryResubmissionServiceLinesMaster, which fails.
        PrimaryResubmissionServiceLinesMasterDTO primaryResubmissionServiceLinesMasterDTO = primaryResubmissionServiceLinesMasterMapper.toDto(
            primaryResubmissionServiceLinesMaster
        );

        restPrimaryResubmissionServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryResubmissionServiceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryResubmissionServiceLinesMaster> primaryResubmissionServiceLinesMasterList = primaryResubmissionServiceLinesMasterRepository.findAll();
        assertThat(primaryResubmissionServiceLinesMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkOriginalDosIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryResubmissionServiceLinesMasterRepository.findAll().size();
        // set the field null
        primaryResubmissionServiceLinesMaster.setOriginalDos(null);

        // Create the PrimaryResubmissionServiceLinesMaster, which fails.
        PrimaryResubmissionServiceLinesMasterDTO primaryResubmissionServiceLinesMasterDTO = primaryResubmissionServiceLinesMasterMapper.toDto(
            primaryResubmissionServiceLinesMaster
        );

        restPrimaryResubmissionServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryResubmissionServiceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryResubmissionServiceLinesMaster> primaryResubmissionServiceLinesMasterList = primaryResubmissionServiceLinesMasterRepository.findAll();
        assertThat(primaryResubmissionServiceLinesMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDosToIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryResubmissionServiceLinesMasterRepository.findAll().size();
        // set the field null
        primaryResubmissionServiceLinesMaster.setDosTo(null);

        // Create the PrimaryResubmissionServiceLinesMaster, which fails.
        PrimaryResubmissionServiceLinesMasterDTO primaryResubmissionServiceLinesMasterDTO = primaryResubmissionServiceLinesMasterMapper.toDto(
            primaryResubmissionServiceLinesMaster
        );

        restPrimaryResubmissionServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryResubmissionServiceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryResubmissionServiceLinesMaster> primaryResubmissionServiceLinesMasterList = primaryResubmissionServiceLinesMasterRepository.findAll();
        assertThat(primaryResubmissionServiceLinesMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkProcCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryResubmissionServiceLinesMasterRepository.findAll().size();
        // set the field null
        primaryResubmissionServiceLinesMaster.setProcCode(null);

        // Create the PrimaryResubmissionServiceLinesMaster, which fails.
        PrimaryResubmissionServiceLinesMasterDTO primaryResubmissionServiceLinesMasterDTO = primaryResubmissionServiceLinesMasterMapper.toDto(
            primaryResubmissionServiceLinesMaster
        );

        restPrimaryResubmissionServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryResubmissionServiceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryResubmissionServiceLinesMaster> primaryResubmissionServiceLinesMasterList = primaryResubmissionServiceLinesMasterRepository.findAll();
        assertThat(primaryResubmissionServiceLinesMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkChargeAmtIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryResubmissionServiceLinesMasterRepository.findAll().size();
        // set the field null
        primaryResubmissionServiceLinesMaster.setChargeAmt(null);

        // Create the PrimaryResubmissionServiceLinesMaster, which fails.
        PrimaryResubmissionServiceLinesMasterDTO primaryResubmissionServiceLinesMasterDTO = primaryResubmissionServiceLinesMasterMapper.toDto(
            primaryResubmissionServiceLinesMaster
        );

        restPrimaryResubmissionServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryResubmissionServiceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryResubmissionServiceLinesMaster> primaryResubmissionServiceLinesMasterList = primaryResubmissionServiceLinesMasterRepository.findAll();
        assertThat(primaryResubmissionServiceLinesMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkItemUomIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryResubmissionServiceLinesMasterRepository.findAll().size();
        // set the field null
        primaryResubmissionServiceLinesMaster.setItemUom(null);

        // Create the PrimaryResubmissionServiceLinesMaster, which fails.
        PrimaryResubmissionServiceLinesMasterDTO primaryResubmissionServiceLinesMasterDTO = primaryResubmissionServiceLinesMasterMapper.toDto(
            primaryResubmissionServiceLinesMaster
        );

        restPrimaryResubmissionServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryResubmissionServiceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryResubmissionServiceLinesMaster> primaryResubmissionServiceLinesMasterList = primaryResubmissionServiceLinesMasterRepository.findAll();
        assertThat(primaryResubmissionServiceLinesMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkModifier1IsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryResubmissionServiceLinesMasterRepository.findAll().size();
        // set the field null
        primaryResubmissionServiceLinesMaster.setModifier1(null);

        // Create the PrimaryResubmissionServiceLinesMaster, which fails.
        PrimaryResubmissionServiceLinesMasterDTO primaryResubmissionServiceLinesMasterDTO = primaryResubmissionServiceLinesMasterMapper.toDto(
            primaryResubmissionServiceLinesMaster
        );

        restPrimaryResubmissionServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryResubmissionServiceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryResubmissionServiceLinesMaster> primaryResubmissionServiceLinesMasterList = primaryResubmissionServiceLinesMasterRepository.findAll();
        assertThat(primaryResubmissionServiceLinesMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkModifier2IsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryResubmissionServiceLinesMasterRepository.findAll().size();
        // set the field null
        primaryResubmissionServiceLinesMaster.setModifier2(null);

        // Create the PrimaryResubmissionServiceLinesMaster, which fails.
        PrimaryResubmissionServiceLinesMasterDTO primaryResubmissionServiceLinesMasterDTO = primaryResubmissionServiceLinesMasterMapper.toDto(
            primaryResubmissionServiceLinesMaster
        );

        restPrimaryResubmissionServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryResubmissionServiceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryResubmissionServiceLinesMaster> primaryResubmissionServiceLinesMasterList = primaryResubmissionServiceLinesMasterRepository.findAll();
        assertThat(primaryResubmissionServiceLinesMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkModifier3IsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryResubmissionServiceLinesMasterRepository.findAll().size();
        // set the field null
        primaryResubmissionServiceLinesMaster.setModifier3(null);

        // Create the PrimaryResubmissionServiceLinesMaster, which fails.
        PrimaryResubmissionServiceLinesMasterDTO primaryResubmissionServiceLinesMasterDTO = primaryResubmissionServiceLinesMasterMapper.toDto(
            primaryResubmissionServiceLinesMaster
        );

        restPrimaryResubmissionServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryResubmissionServiceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryResubmissionServiceLinesMaster> primaryResubmissionServiceLinesMasterList = primaryResubmissionServiceLinesMasterRepository.findAll();
        assertThat(primaryResubmissionServiceLinesMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkModifier4IsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryResubmissionServiceLinesMasterRepository.findAll().size();
        // set the field null
        primaryResubmissionServiceLinesMaster.setModifier4(null);

        // Create the PrimaryResubmissionServiceLinesMaster, which fails.
        PrimaryResubmissionServiceLinesMasterDTO primaryResubmissionServiceLinesMasterDTO = primaryResubmissionServiceLinesMasterMapper.toDto(
            primaryResubmissionServiceLinesMaster
        );

        restPrimaryResubmissionServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryResubmissionServiceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryResubmissionServiceLinesMaster> primaryResubmissionServiceLinesMasterList = primaryResubmissionServiceLinesMasterRepository.findAll();
        assertThat(primaryResubmissionServiceLinesMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIcdPointerIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryResubmissionServiceLinesMasterRepository.findAll().size();
        // set the field null
        primaryResubmissionServiceLinesMaster.setIcdPointer(null);

        // Create the PrimaryResubmissionServiceLinesMaster, which fails.
        PrimaryResubmissionServiceLinesMasterDTO primaryResubmissionServiceLinesMasterDTO = primaryResubmissionServiceLinesMasterMapper.toDto(
            primaryResubmissionServiceLinesMaster
        );

        restPrimaryResubmissionServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryResubmissionServiceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryResubmissionServiceLinesMaster> primaryResubmissionServiceLinesMasterList = primaryResubmissionServiceLinesMasterRepository.findAll();
        assertThat(primaryResubmissionServiceLinesMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkQtyIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryResubmissionServiceLinesMasterRepository.findAll().size();
        // set the field null
        primaryResubmissionServiceLinesMaster.setQty(null);

        // Create the PrimaryResubmissionServiceLinesMaster, which fails.
        PrimaryResubmissionServiceLinesMasterDTO primaryResubmissionServiceLinesMasterDTO = primaryResubmissionServiceLinesMasterMapper.toDto(
            primaryResubmissionServiceLinesMaster
        );

        restPrimaryResubmissionServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryResubmissionServiceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryResubmissionServiceLinesMaster> primaryResubmissionServiceLinesMasterList = primaryResubmissionServiceLinesMasterRepository.findAll();
        assertThat(primaryResubmissionServiceLinesMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkProcedureIdentifierIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryResubmissionServiceLinesMasterRepository.findAll().size();
        // set the field null
        primaryResubmissionServiceLinesMaster.setProcedureIdentifier(null);

        // Create the PrimaryResubmissionServiceLinesMaster, which fails.
        PrimaryResubmissionServiceLinesMasterDTO primaryResubmissionServiceLinesMasterDTO = primaryResubmissionServiceLinesMasterMapper.toDto(
            primaryResubmissionServiceLinesMaster
        );

        restPrimaryResubmissionServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryResubmissionServiceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryResubmissionServiceLinesMaster> primaryResubmissionServiceLinesMasterList = primaryResubmissionServiceLinesMasterRepository.findAll();
        assertThat(primaryResubmissionServiceLinesMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkOrderingProviderFirstNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = primaryResubmissionServiceLinesMasterRepository.findAll().size();
        // set the field null
        primaryResubmissionServiceLinesMaster.setOrderingProviderFirstName(null);

        // Create the PrimaryResubmissionServiceLinesMaster, which fails.
        PrimaryResubmissionServiceLinesMasterDTO primaryResubmissionServiceLinesMasterDTO = primaryResubmissionServiceLinesMasterMapper.toDto(
            primaryResubmissionServiceLinesMaster
        );

        restPrimaryResubmissionServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryResubmissionServiceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<PrimaryResubmissionServiceLinesMaster> primaryResubmissionServiceLinesMasterList = primaryResubmissionServiceLinesMasterRepository.findAll();
        assertThat(primaryResubmissionServiceLinesMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllPrimaryResubmissionServiceLinesMasters() throws Exception {
        // Initialize the database
        primaryResubmissionServiceLinesMasterRepository.saveAndFlush(primaryResubmissionServiceLinesMaster);

        // Get all the primaryResubmissionServiceLinesMasterList
        restPrimaryResubmissionServiceLinesMasterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=changeHealthPrimaryResubmisionServicelinesId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].changeHealthPrimaryResubmisionServicelinesId")
                    .value(hasItem(primaryResubmissionServiceLinesMaster.getChangeHealthPrimaryResubmisionServicelinesId().intValue()))
            )
            .andExpect(
                jsonPath("$.[*].changeHealthPrimaryResubmisionMasterId")
                    .value(hasItem(DEFAULT_CHANGE_HEALTH_PRIMARY_RESUBMISION_MASTER_ID.intValue()))
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
    void getPrimaryResubmissionServiceLinesMaster() throws Exception {
        // Initialize the database
        primaryResubmissionServiceLinesMasterRepository.saveAndFlush(primaryResubmissionServiceLinesMaster);

        // Get the primaryResubmissionServiceLinesMaster
        restPrimaryResubmissionServiceLinesMasterMockMvc
            .perform(get(ENTITY_API_URL_ID, primaryResubmissionServiceLinesMaster.getChangeHealthPrimaryResubmisionServicelinesId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.changeHealthPrimaryResubmisionServicelinesId")
                    .value(primaryResubmissionServiceLinesMaster.getChangeHealthPrimaryResubmisionServicelinesId().intValue())
            )
            .andExpect(
                jsonPath("$.changeHealthPrimaryResubmisionMasterId").value(DEFAULT_CHANGE_HEALTH_PRIMARY_RESUBMISION_MASTER_ID.intValue())
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
    void getNonExistingPrimaryResubmissionServiceLinesMaster() throws Exception {
        // Get the primaryResubmissionServiceLinesMaster
        restPrimaryResubmissionServiceLinesMasterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewPrimaryResubmissionServiceLinesMaster() throws Exception {
        // Initialize the database
        primaryResubmissionServiceLinesMasterRepository.saveAndFlush(primaryResubmissionServiceLinesMaster);

        int databaseSizeBeforeUpdate = primaryResubmissionServiceLinesMasterRepository.findAll().size();

        // Update the primaryResubmissionServiceLinesMaster
        PrimaryResubmissionServiceLinesMaster updatedPrimaryResubmissionServiceLinesMaster = primaryResubmissionServiceLinesMasterRepository
            .findById(primaryResubmissionServiceLinesMaster.getChangeHealthPrimaryResubmisionServicelinesId())
            .get();
        // Disconnect from session so that the updates on updatedPrimaryResubmissionServiceLinesMaster are not directly saved in db
        em.detach(updatedPrimaryResubmissionServiceLinesMaster);
        updatedPrimaryResubmissionServiceLinesMaster
            .changeHealthPrimaryResubmisionMasterId(UPDATED_CHANGE_HEALTH_PRIMARY_RESUBMISION_MASTER_ID)
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
        PrimaryResubmissionServiceLinesMasterDTO primaryResubmissionServiceLinesMasterDTO = primaryResubmissionServiceLinesMasterMapper.toDto(
            updatedPrimaryResubmissionServiceLinesMaster
        );

        restPrimaryResubmissionServiceLinesMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, primaryResubmissionServiceLinesMasterDTO.getChangeHealthPrimaryResubmisionServicelinesId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryResubmissionServiceLinesMasterDTO))
            )
            .andExpect(status().isOk());

        // Validate the PrimaryResubmissionServiceLinesMaster in the database
        List<PrimaryResubmissionServiceLinesMaster> primaryResubmissionServiceLinesMasterList = primaryResubmissionServiceLinesMasterRepository.findAll();
        assertThat(primaryResubmissionServiceLinesMasterList).hasSize(databaseSizeBeforeUpdate);
        PrimaryResubmissionServiceLinesMaster testPrimaryResubmissionServiceLinesMaster = primaryResubmissionServiceLinesMasterList.get(
            primaryResubmissionServiceLinesMasterList.size() - 1
        );
        assertThat(testPrimaryResubmissionServiceLinesMaster.getChangeHealthPrimaryResubmisionMasterId())
            .isEqualTo(UPDATED_CHANGE_HEALTH_PRIMARY_RESUBMISION_MASTER_ID);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getOriginalDos()).isEqualTo(UPDATED_ORIGINAL_DOS);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getDosTo()).isEqualTo(UPDATED_DOS_TO);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getProcCode()).isEqualTo(UPDATED_PROC_CODE);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getChargeAmt()).isEqualTo(UPDATED_CHARGE_AMT);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getItemUom()).isEqualTo(UPDATED_ITEM_UOM);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getModifier1()).isEqualTo(UPDATED_MODIFIER_1);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getModifier2()).isEqualTo(UPDATED_MODIFIER_2);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getModifier3()).isEqualTo(UPDATED_MODIFIER_3);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getModifier4()).isEqualTo(UPDATED_MODIFIER_4);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getIcdPointer()).isEqualTo(UPDATED_ICD_POINTER);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getQty()).isEqualTo(UPDATED_QTY);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getInsertedById()).isEqualTo(UPDATED_INSERTED_BY_ID);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getInsertedDate()).isEqualTo(UPDATED_INSERTED_DATE);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getClaimServicelineControlNo())
            .isEqualTo(UPDATED_CLAIM_SERVICELINE_CONTROL_NO);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getProcedureIdentifier()).isEqualTo(UPDATED_PROCEDURE_IDENTIFIER);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getInsertedByName()).isEqualTo(UPDATED_INSERTED_BY_NAME);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getOrderingProviderFirstName())
            .isEqualTo(UPDATED_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getOrderingProviderLastName()).isEqualTo(UPDATED_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getOrderingProviderNpi()).isEqualTo(UPDATED_ORDERING_PROVIDER_NPI);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getReference()).isEqualTo(UPDATED_REFERENCE);
    }

    @Test
    @Transactional
    void putNonExistingPrimaryResubmissionServiceLinesMaster() throws Exception {
        int databaseSizeBeforeUpdate = primaryResubmissionServiceLinesMasterRepository.findAll().size();
        primaryResubmissionServiceLinesMaster.setChangeHealthPrimaryResubmisionServicelinesId(count.incrementAndGet());

        // Create the PrimaryResubmissionServiceLinesMaster
        PrimaryResubmissionServiceLinesMasterDTO primaryResubmissionServiceLinesMasterDTO = primaryResubmissionServiceLinesMasterMapper.toDto(
            primaryResubmissionServiceLinesMaster
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPrimaryResubmissionServiceLinesMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, primaryResubmissionServiceLinesMasterDTO.getChangeHealthPrimaryResubmisionServicelinesId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryResubmissionServiceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PrimaryResubmissionServiceLinesMaster in the database
        List<PrimaryResubmissionServiceLinesMaster> primaryResubmissionServiceLinesMasterList = primaryResubmissionServiceLinesMasterRepository.findAll();
        assertThat(primaryResubmissionServiceLinesMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPrimaryResubmissionServiceLinesMaster() throws Exception {
        int databaseSizeBeforeUpdate = primaryResubmissionServiceLinesMasterRepository.findAll().size();
        primaryResubmissionServiceLinesMaster.setChangeHealthPrimaryResubmisionServicelinesId(count.incrementAndGet());

        // Create the PrimaryResubmissionServiceLinesMaster
        PrimaryResubmissionServiceLinesMasterDTO primaryResubmissionServiceLinesMasterDTO = primaryResubmissionServiceLinesMasterMapper.toDto(
            primaryResubmissionServiceLinesMaster
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPrimaryResubmissionServiceLinesMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryResubmissionServiceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PrimaryResubmissionServiceLinesMaster in the database
        List<PrimaryResubmissionServiceLinesMaster> primaryResubmissionServiceLinesMasterList = primaryResubmissionServiceLinesMasterRepository.findAll();
        assertThat(primaryResubmissionServiceLinesMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPrimaryResubmissionServiceLinesMaster() throws Exception {
        int databaseSizeBeforeUpdate = primaryResubmissionServiceLinesMasterRepository.findAll().size();
        primaryResubmissionServiceLinesMaster.setChangeHealthPrimaryResubmisionServicelinesId(count.incrementAndGet());

        // Create the PrimaryResubmissionServiceLinesMaster
        PrimaryResubmissionServiceLinesMasterDTO primaryResubmissionServiceLinesMasterDTO = primaryResubmissionServiceLinesMasterMapper.toDto(
            primaryResubmissionServiceLinesMaster
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPrimaryResubmissionServiceLinesMasterMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(primaryResubmissionServiceLinesMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PrimaryResubmissionServiceLinesMaster in the database
        List<PrimaryResubmissionServiceLinesMaster> primaryResubmissionServiceLinesMasterList = primaryResubmissionServiceLinesMasterRepository.findAll();
        assertThat(primaryResubmissionServiceLinesMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePrimaryResubmissionServiceLinesMasterWithPatch() throws Exception {
        // Initialize the database
        primaryResubmissionServiceLinesMasterRepository.saveAndFlush(primaryResubmissionServiceLinesMaster);

        int databaseSizeBeforeUpdate = primaryResubmissionServiceLinesMasterRepository.findAll().size();

        // Update the primaryResubmissionServiceLinesMaster using partial update
        PrimaryResubmissionServiceLinesMaster partialUpdatedPrimaryResubmissionServiceLinesMaster = new PrimaryResubmissionServiceLinesMaster();
        partialUpdatedPrimaryResubmissionServiceLinesMaster.setChangeHealthPrimaryResubmisionServicelinesId(
            primaryResubmissionServiceLinesMaster.getChangeHealthPrimaryResubmisionServicelinesId()
        );

        partialUpdatedPrimaryResubmissionServiceLinesMaster
            .changeHealthPrimaryResubmisionMasterId(UPDATED_CHANGE_HEALTH_PRIMARY_RESUBMISION_MASTER_ID)
            .originalDos(UPDATED_ORIGINAL_DOS)
            .dosTo(UPDATED_DOS_TO)
            .procCode(UPDATED_PROC_CODE)
            .chargeAmt(UPDATED_CHARGE_AMT)
            .modifier1(UPDATED_MODIFIER_1)
            .modifier2(UPDATED_MODIFIER_2)
            .icdPointer(UPDATED_ICD_POINTER)
            .insertedById(UPDATED_INSERTED_BY_ID)
            .claimServicelineControlNo(UPDATED_CLAIM_SERVICELINE_CONTROL_NO)
            .insertedByName(UPDATED_INSERTED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME);

        restPrimaryResubmissionServiceLinesMasterMockMvc
            .perform(
                patch(
                    ENTITY_API_URL_ID,
                    partialUpdatedPrimaryResubmissionServiceLinesMaster.getChangeHealthPrimaryResubmisionServicelinesId()
                )
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPrimaryResubmissionServiceLinesMaster))
            )
            .andExpect(status().isOk());

        // Validate the PrimaryResubmissionServiceLinesMaster in the database
        List<PrimaryResubmissionServiceLinesMaster> primaryResubmissionServiceLinesMasterList = primaryResubmissionServiceLinesMasterRepository.findAll();
        assertThat(primaryResubmissionServiceLinesMasterList).hasSize(databaseSizeBeforeUpdate);
        PrimaryResubmissionServiceLinesMaster testPrimaryResubmissionServiceLinesMaster = primaryResubmissionServiceLinesMasterList.get(
            primaryResubmissionServiceLinesMasterList.size() - 1
        );
        assertThat(testPrimaryResubmissionServiceLinesMaster.getChangeHealthPrimaryResubmisionMasterId())
            .isEqualTo(UPDATED_CHANGE_HEALTH_PRIMARY_RESUBMISION_MASTER_ID);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getOriginalDos()).isEqualTo(UPDATED_ORIGINAL_DOS);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getDosTo()).isEqualTo(UPDATED_DOS_TO);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getProcCode()).isEqualTo(UPDATED_PROC_CODE);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getChargeAmt()).isEqualTo(UPDATED_CHARGE_AMT);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getItemUom()).isEqualTo(DEFAULT_ITEM_UOM);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getModifier1()).isEqualTo(UPDATED_MODIFIER_1);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getModifier2()).isEqualTo(UPDATED_MODIFIER_2);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getModifier3()).isEqualTo(DEFAULT_MODIFIER_3);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getModifier4()).isEqualTo(DEFAULT_MODIFIER_4);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getIcdPointer()).isEqualTo(UPDATED_ICD_POINTER);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getQty()).isEqualTo(DEFAULT_QTY);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getInsertedById()).isEqualTo(UPDATED_INSERTED_BY_ID);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getInsertedDate()).isEqualTo(DEFAULT_INSERTED_DATE);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getClaimServicelineControlNo())
            .isEqualTo(UPDATED_CLAIM_SERVICELINE_CONTROL_NO);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getProcedureIdentifier()).isEqualTo(DEFAULT_PROCEDURE_IDENTIFIER);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getInsertedByName()).isEqualTo(UPDATED_INSERTED_BY_NAME);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getOrderingProviderFirstName())
            .isEqualTo(DEFAULT_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getOrderingProviderLastName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getOrderingProviderNpi()).isEqualTo(DEFAULT_ORDERING_PROVIDER_NPI);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getReference()).isEqualTo(DEFAULT_REFERENCE);
    }

    @Test
    @Transactional
    void fullUpdatePrimaryResubmissionServiceLinesMasterWithPatch() throws Exception {
        // Initialize the database
        primaryResubmissionServiceLinesMasterRepository.saveAndFlush(primaryResubmissionServiceLinesMaster);

        int databaseSizeBeforeUpdate = primaryResubmissionServiceLinesMasterRepository.findAll().size();

        // Update the primaryResubmissionServiceLinesMaster using partial update
        PrimaryResubmissionServiceLinesMaster partialUpdatedPrimaryResubmissionServiceLinesMaster = new PrimaryResubmissionServiceLinesMaster();
        partialUpdatedPrimaryResubmissionServiceLinesMaster.setChangeHealthPrimaryResubmisionServicelinesId(
            primaryResubmissionServiceLinesMaster.getChangeHealthPrimaryResubmisionServicelinesId()
        );

        partialUpdatedPrimaryResubmissionServiceLinesMaster
            .changeHealthPrimaryResubmisionMasterId(UPDATED_CHANGE_HEALTH_PRIMARY_RESUBMISION_MASTER_ID)
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

        restPrimaryResubmissionServiceLinesMasterMockMvc
            .perform(
                patch(
                    ENTITY_API_URL_ID,
                    partialUpdatedPrimaryResubmissionServiceLinesMaster.getChangeHealthPrimaryResubmisionServicelinesId()
                )
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPrimaryResubmissionServiceLinesMaster))
            )
            .andExpect(status().isOk());

        // Validate the PrimaryResubmissionServiceLinesMaster in the database
        List<PrimaryResubmissionServiceLinesMaster> primaryResubmissionServiceLinesMasterList = primaryResubmissionServiceLinesMasterRepository.findAll();
        assertThat(primaryResubmissionServiceLinesMasterList).hasSize(databaseSizeBeforeUpdate);
        PrimaryResubmissionServiceLinesMaster testPrimaryResubmissionServiceLinesMaster = primaryResubmissionServiceLinesMasterList.get(
            primaryResubmissionServiceLinesMasterList.size() - 1
        );
        assertThat(testPrimaryResubmissionServiceLinesMaster.getChangeHealthPrimaryResubmisionMasterId())
            .isEqualTo(UPDATED_CHANGE_HEALTH_PRIMARY_RESUBMISION_MASTER_ID);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getOriginalDos()).isEqualTo(UPDATED_ORIGINAL_DOS);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getDosTo()).isEqualTo(UPDATED_DOS_TO);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getProcCode()).isEqualTo(UPDATED_PROC_CODE);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getChargeAmt()).isEqualTo(UPDATED_CHARGE_AMT);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getItemUom()).isEqualTo(UPDATED_ITEM_UOM);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getModifier1()).isEqualTo(UPDATED_MODIFIER_1);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getModifier2()).isEqualTo(UPDATED_MODIFIER_2);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getModifier3()).isEqualTo(UPDATED_MODIFIER_3);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getModifier4()).isEqualTo(UPDATED_MODIFIER_4);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getIcdPointer()).isEqualTo(UPDATED_ICD_POINTER);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getQty()).isEqualTo(UPDATED_QTY);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getInsertedById()).isEqualTo(UPDATED_INSERTED_BY_ID);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getInsertedDate()).isEqualTo(UPDATED_INSERTED_DATE);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getClaimServicelineControlNo())
            .isEqualTo(UPDATED_CLAIM_SERVICELINE_CONTROL_NO);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getProcedureIdentifier()).isEqualTo(UPDATED_PROCEDURE_IDENTIFIER);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getInsertedByName()).isEqualTo(UPDATED_INSERTED_BY_NAME);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getOrderingProviderFirstName())
            .isEqualTo(UPDATED_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getOrderingProviderLastName()).isEqualTo(UPDATED_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getOrderingProviderNpi()).isEqualTo(UPDATED_ORDERING_PROVIDER_NPI);
        assertThat(testPrimaryResubmissionServiceLinesMaster.getReference()).isEqualTo(UPDATED_REFERENCE);
    }

    @Test
    @Transactional
    void patchNonExistingPrimaryResubmissionServiceLinesMaster() throws Exception {
        int databaseSizeBeforeUpdate = primaryResubmissionServiceLinesMasterRepository.findAll().size();
        primaryResubmissionServiceLinesMaster.setChangeHealthPrimaryResubmisionServicelinesId(count.incrementAndGet());

        // Create the PrimaryResubmissionServiceLinesMaster
        PrimaryResubmissionServiceLinesMasterDTO primaryResubmissionServiceLinesMasterDTO = primaryResubmissionServiceLinesMasterMapper.toDto(
            primaryResubmissionServiceLinesMaster
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPrimaryResubmissionServiceLinesMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, primaryResubmissionServiceLinesMasterDTO.getChangeHealthPrimaryResubmisionServicelinesId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(primaryResubmissionServiceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PrimaryResubmissionServiceLinesMaster in the database
        List<PrimaryResubmissionServiceLinesMaster> primaryResubmissionServiceLinesMasterList = primaryResubmissionServiceLinesMasterRepository.findAll();
        assertThat(primaryResubmissionServiceLinesMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPrimaryResubmissionServiceLinesMaster() throws Exception {
        int databaseSizeBeforeUpdate = primaryResubmissionServiceLinesMasterRepository.findAll().size();
        primaryResubmissionServiceLinesMaster.setChangeHealthPrimaryResubmisionServicelinesId(count.incrementAndGet());

        // Create the PrimaryResubmissionServiceLinesMaster
        PrimaryResubmissionServiceLinesMasterDTO primaryResubmissionServiceLinesMasterDTO = primaryResubmissionServiceLinesMasterMapper.toDto(
            primaryResubmissionServiceLinesMaster
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPrimaryResubmissionServiceLinesMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(primaryResubmissionServiceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PrimaryResubmissionServiceLinesMaster in the database
        List<PrimaryResubmissionServiceLinesMaster> primaryResubmissionServiceLinesMasterList = primaryResubmissionServiceLinesMasterRepository.findAll();
        assertThat(primaryResubmissionServiceLinesMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPrimaryResubmissionServiceLinesMaster() throws Exception {
        int databaseSizeBeforeUpdate = primaryResubmissionServiceLinesMasterRepository.findAll().size();
        primaryResubmissionServiceLinesMaster.setChangeHealthPrimaryResubmisionServicelinesId(count.incrementAndGet());

        // Create the PrimaryResubmissionServiceLinesMaster
        PrimaryResubmissionServiceLinesMasterDTO primaryResubmissionServiceLinesMasterDTO = primaryResubmissionServiceLinesMasterMapper.toDto(
            primaryResubmissionServiceLinesMaster
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPrimaryResubmissionServiceLinesMasterMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(primaryResubmissionServiceLinesMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PrimaryResubmissionServiceLinesMaster in the database
        List<PrimaryResubmissionServiceLinesMaster> primaryResubmissionServiceLinesMasterList = primaryResubmissionServiceLinesMasterRepository.findAll();
        assertThat(primaryResubmissionServiceLinesMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePrimaryResubmissionServiceLinesMaster() throws Exception {
        // Initialize the database
        primaryResubmissionServiceLinesMasterRepository.saveAndFlush(primaryResubmissionServiceLinesMaster);

        int databaseSizeBeforeDelete = primaryResubmissionServiceLinesMasterRepository.findAll().size();

        // Delete the primaryResubmissionServiceLinesMaster
        restPrimaryResubmissionServiceLinesMasterMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, primaryResubmissionServiceLinesMaster.getChangeHealthPrimaryResubmisionServicelinesId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PrimaryResubmissionServiceLinesMaster> primaryResubmissionServiceLinesMasterList = primaryResubmissionServiceLinesMasterRepository.findAll();
        assertThat(primaryResubmissionServiceLinesMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
