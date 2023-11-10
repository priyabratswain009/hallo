package com.sunknowledge.dme.rcm.web.rest;

import static com.sunknowledge.dme.rcm.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.SecondaryServiceLinesMaster;
import com.sunknowledge.dme.rcm.repository.SecondaryServiceLinesMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.SecondaryServiceLinesMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.SecondaryServiceLinesMasterMapper;
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
 * Integration tests for the {@link SecondaryServiceLinesMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SecondaryServiceLinesMasterResourceIT {

    private static final Long DEFAULT_CHANGE_HEALTH_SECONDARY_SUBMISION_MASTER_ID = 1L;
    private static final Long UPDATED_CHANGE_HEALTH_SECONDARY_SUBMISION_MASTER_ID = 2L;

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

    private static final String DEFAULT_INSERTED_BY_ID = "AAAAAAAAAA";
    private static final String UPDATED_INSERTED_BY_ID = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_INSERTED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_INSERTED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CLAIM_SERVICELINE_CONTROL_NO = "AAAAAAAAAA";
    private static final String UPDATED_CLAIM_SERVICELINE_CONTROL_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PROCEDURE_IDENTIFIER = "AAAAAAAAAA";
    private static final String UPDATED_PROCEDURE_IDENTIFIER = "BBBBBBBBBB";

    private static final String DEFAULT_INSERTED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INSERTED_BY_NAME = "BBBBBBBBBB";

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

    private static final String DEFAULT_PAYOR_CLAIM_CONTROL_NO = "AAAAAAAAAA";
    private static final String UPDATED_PAYOR_CLAIM_CONTROL_NO = "BBBBBBBBBB";

    private static final Double DEFAULT_PROVIDER_PAYMENT_AMOUNT = 1D;
    private static final Double UPDATED_PROVIDER_PAYMENT_AMOUNT = 2D;

    private static final String DEFAULT_LINE_ADJUSTMENT = "AAAAAAAAAA";
    private static final String UPDATED_LINE_ADJUSTMENT = "BBBBBBBBBB";

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final ZonedDateTime DEFAULT_UPDATED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/secondary-service-lines-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{changeHealthSecondarySubmisionServicelinesId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SecondaryServiceLinesMasterRepository secondaryServiceLinesMasterRepository;

    @Autowired
    private SecondaryServiceLinesMasterMapper secondaryServiceLinesMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSecondaryServiceLinesMasterMockMvc;

    private SecondaryServiceLinesMaster secondaryServiceLinesMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SecondaryServiceLinesMaster createEntity(EntityManager em) {
        SecondaryServiceLinesMaster secondaryServiceLinesMaster = new SecondaryServiceLinesMaster()
            .changeHealthSecondarySubmisionMasterId(DEFAULT_CHANGE_HEALTH_SECONDARY_SUBMISION_MASTER_ID)
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
            .claimServicelineControlNo(DEFAULT_CLAIM_SERVICELINE_CONTROL_NO)
            .procedureIdentifier(DEFAULT_PROCEDURE_IDENTIFIER)
            .insertedByName(DEFAULT_INSERTED_BY_NAME)
            .status(DEFAULT_STATUS)
            .orderingProviderFirstName(DEFAULT_ORDERING_PROVIDER_FIRST_NAME)
            .orderingProviderLastName(DEFAULT_ORDERING_PROVIDER_LAST_NAME)
            .orderingProviderNpi(DEFAULT_ORDERING_PROVIDER_NPI)
            .reference(DEFAULT_REFERENCE)
            .payorClaimControlNo(DEFAULT_PAYOR_CLAIM_CONTROL_NO)
            .providerPaymentAmount(DEFAULT_PROVIDER_PAYMENT_AMOUNT)
            .lineAdjustment(DEFAULT_LINE_ADJUSTMENT)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .updatedByName(DEFAULT_UPDATED_BY_NAME);
        return secondaryServiceLinesMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SecondaryServiceLinesMaster createUpdatedEntity(EntityManager em) {
        SecondaryServiceLinesMaster secondaryServiceLinesMaster = new SecondaryServiceLinesMaster()
            .changeHealthSecondarySubmisionMasterId(UPDATED_CHANGE_HEALTH_SECONDARY_SUBMISION_MASTER_ID)
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
            .claimServicelineControlNo(UPDATED_CLAIM_SERVICELINE_CONTROL_NO)
            .procedureIdentifier(UPDATED_PROCEDURE_IDENTIFIER)
            .insertedByName(UPDATED_INSERTED_BY_NAME)
            .status(UPDATED_STATUS)
            .orderingProviderFirstName(UPDATED_ORDERING_PROVIDER_FIRST_NAME)
            .orderingProviderLastName(UPDATED_ORDERING_PROVIDER_LAST_NAME)
            .orderingProviderNpi(UPDATED_ORDERING_PROVIDER_NPI)
            .reference(UPDATED_REFERENCE)
            .payorClaimControlNo(UPDATED_PAYOR_CLAIM_CONTROL_NO)
            .providerPaymentAmount(UPDATED_PROVIDER_PAYMENT_AMOUNT)
            .lineAdjustment(UPDATED_LINE_ADJUSTMENT)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedByName(UPDATED_UPDATED_BY_NAME);
        return secondaryServiceLinesMaster;
    }

    @BeforeEach
    public void initTest() {
        secondaryServiceLinesMaster = createEntity(em);
    }

    @Test
    @Transactional
    void createSecondaryServiceLinesMaster() throws Exception {
        int databaseSizeBeforeCreate = secondaryServiceLinesMasterRepository.findAll().size();
        // Create the SecondaryServiceLinesMaster
        SecondaryServiceLinesMasterDTO secondaryServiceLinesMasterDTO = secondaryServiceLinesMasterMapper.toDto(
            secondaryServiceLinesMaster
        );
        restSecondaryServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryServiceLinesMasterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the SecondaryServiceLinesMaster in the database
        List<SecondaryServiceLinesMaster> secondaryServiceLinesMasterList = secondaryServiceLinesMasterRepository.findAll();
        assertThat(secondaryServiceLinesMasterList).hasSize(databaseSizeBeforeCreate + 1);
        SecondaryServiceLinesMaster testSecondaryServiceLinesMaster = secondaryServiceLinesMasterList.get(
            secondaryServiceLinesMasterList.size() - 1
        );
        assertThat(testSecondaryServiceLinesMaster.getChangeHealthSecondarySubmisionMasterId())
            .isEqualTo(DEFAULT_CHANGE_HEALTH_SECONDARY_SUBMISION_MASTER_ID);
        assertThat(testSecondaryServiceLinesMaster.getOriginalDos()).isEqualTo(DEFAULT_ORIGINAL_DOS);
        assertThat(testSecondaryServiceLinesMaster.getDosTo()).isEqualTo(DEFAULT_DOS_TO);
        assertThat(testSecondaryServiceLinesMaster.getProcCode()).isEqualTo(DEFAULT_PROC_CODE);
        assertThat(testSecondaryServiceLinesMaster.getChargeAmt()).isEqualTo(DEFAULT_CHARGE_AMT);
        assertThat(testSecondaryServiceLinesMaster.getItemUom()).isEqualTo(DEFAULT_ITEM_UOM);
        assertThat(testSecondaryServiceLinesMaster.getModifier1()).isEqualTo(DEFAULT_MODIFIER_1);
        assertThat(testSecondaryServiceLinesMaster.getModifier2()).isEqualTo(DEFAULT_MODIFIER_2);
        assertThat(testSecondaryServiceLinesMaster.getModifier3()).isEqualTo(DEFAULT_MODIFIER_3);
        assertThat(testSecondaryServiceLinesMaster.getModifier4()).isEqualTo(DEFAULT_MODIFIER_4);
        assertThat(testSecondaryServiceLinesMaster.getIcdPointer()).isEqualTo(DEFAULT_ICD_POINTER);
        assertThat(testSecondaryServiceLinesMaster.getQty()).isEqualTo(DEFAULT_QTY);
        assertThat(testSecondaryServiceLinesMaster.getInsertedById()).isEqualTo(DEFAULT_INSERTED_BY_ID);
        assertThat(testSecondaryServiceLinesMaster.getInsertedDate()).isEqualTo(DEFAULT_INSERTED_DATE);
        assertThat(testSecondaryServiceLinesMaster.getClaimServicelineControlNo()).isEqualTo(DEFAULT_CLAIM_SERVICELINE_CONTROL_NO);
        assertThat(testSecondaryServiceLinesMaster.getProcedureIdentifier()).isEqualTo(DEFAULT_PROCEDURE_IDENTIFIER);
        assertThat(testSecondaryServiceLinesMaster.getInsertedByName()).isEqualTo(DEFAULT_INSERTED_BY_NAME);
        assertThat(testSecondaryServiceLinesMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSecondaryServiceLinesMaster.getOrderingProviderFirstName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testSecondaryServiceLinesMaster.getOrderingProviderLastName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testSecondaryServiceLinesMaster.getOrderingProviderNpi()).isEqualTo(DEFAULT_ORDERING_PROVIDER_NPI);
        assertThat(testSecondaryServiceLinesMaster.getReference()).isEqualTo(DEFAULT_REFERENCE);
        assertThat(testSecondaryServiceLinesMaster.getPayorClaimControlNo()).isEqualTo(DEFAULT_PAYOR_CLAIM_CONTROL_NO);
        assertThat(testSecondaryServiceLinesMaster.getProviderPaymentAmount()).isEqualTo(DEFAULT_PROVIDER_PAYMENT_AMOUNT);
        assertThat(testSecondaryServiceLinesMaster.getLineAdjustment()).isEqualTo(DEFAULT_LINE_ADJUSTMENT);
        assertThat(testSecondaryServiceLinesMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testSecondaryServiceLinesMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testSecondaryServiceLinesMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
    }

    @Test
    @Transactional
    void createSecondaryServiceLinesMasterWithExistingId() throws Exception {
        // Create the SecondaryServiceLinesMaster with an existing ID
        secondaryServiceLinesMaster.setChangeHealthSecondarySubmisionServicelinesId(1L);
        SecondaryServiceLinesMasterDTO secondaryServiceLinesMasterDTO = secondaryServiceLinesMasterMapper.toDto(
            secondaryServiceLinesMaster
        );

        int databaseSizeBeforeCreate = secondaryServiceLinesMasterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSecondaryServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryServiceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SecondaryServiceLinesMaster in the database
        List<SecondaryServiceLinesMaster> secondaryServiceLinesMasterList = secondaryServiceLinesMasterRepository.findAll();
        assertThat(secondaryServiceLinesMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkChangeHealthSecondarySubmisionMasterIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryServiceLinesMasterRepository.findAll().size();
        // set the field null
        secondaryServiceLinesMaster.setChangeHealthSecondarySubmisionMasterId(null);

        // Create the SecondaryServiceLinesMaster, which fails.
        SecondaryServiceLinesMasterDTO secondaryServiceLinesMasterDTO = secondaryServiceLinesMasterMapper.toDto(
            secondaryServiceLinesMaster
        );

        restSecondaryServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryServiceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryServiceLinesMaster> secondaryServiceLinesMasterList = secondaryServiceLinesMasterRepository.findAll();
        assertThat(secondaryServiceLinesMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkOriginalDosIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryServiceLinesMasterRepository.findAll().size();
        // set the field null
        secondaryServiceLinesMaster.setOriginalDos(null);

        // Create the SecondaryServiceLinesMaster, which fails.
        SecondaryServiceLinesMasterDTO secondaryServiceLinesMasterDTO = secondaryServiceLinesMasterMapper.toDto(
            secondaryServiceLinesMaster
        );

        restSecondaryServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryServiceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryServiceLinesMaster> secondaryServiceLinesMasterList = secondaryServiceLinesMasterRepository.findAll();
        assertThat(secondaryServiceLinesMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDosToIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryServiceLinesMasterRepository.findAll().size();
        // set the field null
        secondaryServiceLinesMaster.setDosTo(null);

        // Create the SecondaryServiceLinesMaster, which fails.
        SecondaryServiceLinesMasterDTO secondaryServiceLinesMasterDTO = secondaryServiceLinesMasterMapper.toDto(
            secondaryServiceLinesMaster
        );

        restSecondaryServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryServiceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryServiceLinesMaster> secondaryServiceLinesMasterList = secondaryServiceLinesMasterRepository.findAll();
        assertThat(secondaryServiceLinesMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkProcCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryServiceLinesMasterRepository.findAll().size();
        // set the field null
        secondaryServiceLinesMaster.setProcCode(null);

        // Create the SecondaryServiceLinesMaster, which fails.
        SecondaryServiceLinesMasterDTO secondaryServiceLinesMasterDTO = secondaryServiceLinesMasterMapper.toDto(
            secondaryServiceLinesMaster
        );

        restSecondaryServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryServiceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryServiceLinesMaster> secondaryServiceLinesMasterList = secondaryServiceLinesMasterRepository.findAll();
        assertThat(secondaryServiceLinesMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkChargeAmtIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryServiceLinesMasterRepository.findAll().size();
        // set the field null
        secondaryServiceLinesMaster.setChargeAmt(null);

        // Create the SecondaryServiceLinesMaster, which fails.
        SecondaryServiceLinesMasterDTO secondaryServiceLinesMasterDTO = secondaryServiceLinesMasterMapper.toDto(
            secondaryServiceLinesMaster
        );

        restSecondaryServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryServiceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryServiceLinesMaster> secondaryServiceLinesMasterList = secondaryServiceLinesMasterRepository.findAll();
        assertThat(secondaryServiceLinesMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkItemUomIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryServiceLinesMasterRepository.findAll().size();
        // set the field null
        secondaryServiceLinesMaster.setItemUom(null);

        // Create the SecondaryServiceLinesMaster, which fails.
        SecondaryServiceLinesMasterDTO secondaryServiceLinesMasterDTO = secondaryServiceLinesMasterMapper.toDto(
            secondaryServiceLinesMaster
        );

        restSecondaryServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryServiceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryServiceLinesMaster> secondaryServiceLinesMasterList = secondaryServiceLinesMasterRepository.findAll();
        assertThat(secondaryServiceLinesMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkModifier1IsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryServiceLinesMasterRepository.findAll().size();
        // set the field null
        secondaryServiceLinesMaster.setModifier1(null);

        // Create the SecondaryServiceLinesMaster, which fails.
        SecondaryServiceLinesMasterDTO secondaryServiceLinesMasterDTO = secondaryServiceLinesMasterMapper.toDto(
            secondaryServiceLinesMaster
        );

        restSecondaryServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryServiceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryServiceLinesMaster> secondaryServiceLinesMasterList = secondaryServiceLinesMasterRepository.findAll();
        assertThat(secondaryServiceLinesMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkModifier2IsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryServiceLinesMasterRepository.findAll().size();
        // set the field null
        secondaryServiceLinesMaster.setModifier2(null);

        // Create the SecondaryServiceLinesMaster, which fails.
        SecondaryServiceLinesMasterDTO secondaryServiceLinesMasterDTO = secondaryServiceLinesMasterMapper.toDto(
            secondaryServiceLinesMaster
        );

        restSecondaryServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryServiceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryServiceLinesMaster> secondaryServiceLinesMasterList = secondaryServiceLinesMasterRepository.findAll();
        assertThat(secondaryServiceLinesMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkModifier3IsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryServiceLinesMasterRepository.findAll().size();
        // set the field null
        secondaryServiceLinesMaster.setModifier3(null);

        // Create the SecondaryServiceLinesMaster, which fails.
        SecondaryServiceLinesMasterDTO secondaryServiceLinesMasterDTO = secondaryServiceLinesMasterMapper.toDto(
            secondaryServiceLinesMaster
        );

        restSecondaryServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryServiceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryServiceLinesMaster> secondaryServiceLinesMasterList = secondaryServiceLinesMasterRepository.findAll();
        assertThat(secondaryServiceLinesMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkModifier4IsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryServiceLinesMasterRepository.findAll().size();
        // set the field null
        secondaryServiceLinesMaster.setModifier4(null);

        // Create the SecondaryServiceLinesMaster, which fails.
        SecondaryServiceLinesMasterDTO secondaryServiceLinesMasterDTO = secondaryServiceLinesMasterMapper.toDto(
            secondaryServiceLinesMaster
        );

        restSecondaryServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryServiceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryServiceLinesMaster> secondaryServiceLinesMasterList = secondaryServiceLinesMasterRepository.findAll();
        assertThat(secondaryServiceLinesMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIcdPointerIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryServiceLinesMasterRepository.findAll().size();
        // set the field null
        secondaryServiceLinesMaster.setIcdPointer(null);

        // Create the SecondaryServiceLinesMaster, which fails.
        SecondaryServiceLinesMasterDTO secondaryServiceLinesMasterDTO = secondaryServiceLinesMasterMapper.toDto(
            secondaryServiceLinesMaster
        );

        restSecondaryServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryServiceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryServiceLinesMaster> secondaryServiceLinesMasterList = secondaryServiceLinesMasterRepository.findAll();
        assertThat(secondaryServiceLinesMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkQtyIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryServiceLinesMasterRepository.findAll().size();
        // set the field null
        secondaryServiceLinesMaster.setQty(null);

        // Create the SecondaryServiceLinesMaster, which fails.
        SecondaryServiceLinesMasterDTO secondaryServiceLinesMasterDTO = secondaryServiceLinesMasterMapper.toDto(
            secondaryServiceLinesMaster
        );

        restSecondaryServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryServiceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryServiceLinesMaster> secondaryServiceLinesMasterList = secondaryServiceLinesMasterRepository.findAll();
        assertThat(secondaryServiceLinesMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkOrderingProviderFirstNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryServiceLinesMasterRepository.findAll().size();
        // set the field null
        secondaryServiceLinesMaster.setOrderingProviderFirstName(null);

        // Create the SecondaryServiceLinesMaster, which fails.
        SecondaryServiceLinesMasterDTO secondaryServiceLinesMasterDTO = secondaryServiceLinesMasterMapper.toDto(
            secondaryServiceLinesMaster
        );

        restSecondaryServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryServiceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryServiceLinesMaster> secondaryServiceLinesMasterList = secondaryServiceLinesMasterRepository.findAll();
        assertThat(secondaryServiceLinesMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkProviderPaymentAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = secondaryServiceLinesMasterRepository.findAll().size();
        // set the field null
        secondaryServiceLinesMaster.setProviderPaymentAmount(null);

        // Create the SecondaryServiceLinesMaster, which fails.
        SecondaryServiceLinesMasterDTO secondaryServiceLinesMasterDTO = secondaryServiceLinesMasterMapper.toDto(
            secondaryServiceLinesMaster
        );

        restSecondaryServiceLinesMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryServiceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        List<SecondaryServiceLinesMaster> secondaryServiceLinesMasterList = secondaryServiceLinesMasterRepository.findAll();
        assertThat(secondaryServiceLinesMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllSecondaryServiceLinesMasters() throws Exception {
        // Initialize the database
        secondaryServiceLinesMasterRepository.saveAndFlush(secondaryServiceLinesMaster);

        // Get all the secondaryServiceLinesMasterList
        restSecondaryServiceLinesMasterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=changeHealthSecondarySubmisionServicelinesId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].changeHealthSecondarySubmisionServicelinesId")
                    .value(hasItem(secondaryServiceLinesMaster.getChangeHealthSecondarySubmisionServicelinesId().intValue()))
            )
            .andExpect(
                jsonPath("$.[*].changeHealthSecondarySubmisionMasterId")
                    .value(hasItem(DEFAULT_CHANGE_HEALTH_SECONDARY_SUBMISION_MASTER_ID.intValue()))
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
            .andExpect(jsonPath("$.[*].insertedById").value(hasItem(DEFAULT_INSERTED_BY_ID)))
            .andExpect(jsonPath("$.[*].insertedDate").value(hasItem(sameInstant(DEFAULT_INSERTED_DATE))))
            .andExpect(jsonPath("$.[*].claimServicelineControlNo").value(hasItem(DEFAULT_CLAIM_SERVICELINE_CONTROL_NO)))
            .andExpect(jsonPath("$.[*].procedureIdentifier").value(hasItem(DEFAULT_PROCEDURE_IDENTIFIER)))
            .andExpect(jsonPath("$.[*].insertedByName").value(hasItem(DEFAULT_INSERTED_BY_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].orderingProviderFirstName").value(hasItem(DEFAULT_ORDERING_PROVIDER_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].orderingProviderLastName").value(hasItem(DEFAULT_ORDERING_PROVIDER_LAST_NAME)))
            .andExpect(jsonPath("$.[*].orderingProviderNpi").value(hasItem(DEFAULT_ORDERING_PROVIDER_NPI)))
            .andExpect(jsonPath("$.[*].reference").value(hasItem(DEFAULT_REFERENCE)))
            .andExpect(jsonPath("$.[*].payorClaimControlNo").value(hasItem(DEFAULT_PAYOR_CLAIM_CONTROL_NO)))
            .andExpect(jsonPath("$.[*].providerPaymentAmount").value(hasItem(DEFAULT_PROVIDER_PAYMENT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].lineAdjustment").value(hasItem(DEFAULT_LINE_ADJUSTMENT)))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(sameInstant(DEFAULT_UPDATED_DATE))))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)));
    }

    @Test
    @Transactional
    void getSecondaryServiceLinesMaster() throws Exception {
        // Initialize the database
        secondaryServiceLinesMasterRepository.saveAndFlush(secondaryServiceLinesMaster);

        // Get the secondaryServiceLinesMaster
        restSecondaryServiceLinesMasterMockMvc
            .perform(get(ENTITY_API_URL_ID, secondaryServiceLinesMaster.getChangeHealthSecondarySubmisionServicelinesId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.changeHealthSecondarySubmisionServicelinesId")
                    .value(secondaryServiceLinesMaster.getChangeHealthSecondarySubmisionServicelinesId().intValue())
            )
            .andExpect(
                jsonPath("$.changeHealthSecondarySubmisionMasterId").value(DEFAULT_CHANGE_HEALTH_SECONDARY_SUBMISION_MASTER_ID.intValue())
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
            .andExpect(jsonPath("$.insertedById").value(DEFAULT_INSERTED_BY_ID))
            .andExpect(jsonPath("$.insertedDate").value(sameInstant(DEFAULT_INSERTED_DATE)))
            .andExpect(jsonPath("$.claimServicelineControlNo").value(DEFAULT_CLAIM_SERVICELINE_CONTROL_NO))
            .andExpect(jsonPath("$.procedureIdentifier").value(DEFAULT_PROCEDURE_IDENTIFIER))
            .andExpect(jsonPath("$.insertedByName").value(DEFAULT_INSERTED_BY_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.orderingProviderFirstName").value(DEFAULT_ORDERING_PROVIDER_FIRST_NAME))
            .andExpect(jsonPath("$.orderingProviderLastName").value(DEFAULT_ORDERING_PROVIDER_LAST_NAME))
            .andExpect(jsonPath("$.orderingProviderNpi").value(DEFAULT_ORDERING_PROVIDER_NPI))
            .andExpect(jsonPath("$.reference").value(DEFAULT_REFERENCE))
            .andExpect(jsonPath("$.payorClaimControlNo").value(DEFAULT_PAYOR_CLAIM_CONTROL_NO))
            .andExpect(jsonPath("$.providerPaymentAmount").value(DEFAULT_PROVIDER_PAYMENT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.lineAdjustment").value(DEFAULT_LINE_ADJUSTMENT))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedDate").value(sameInstant(DEFAULT_UPDATED_DATE)))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME));
    }

    @Test
    @Transactional
    void getNonExistingSecondaryServiceLinesMaster() throws Exception {
        // Get the secondaryServiceLinesMaster
        restSecondaryServiceLinesMasterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewSecondaryServiceLinesMaster() throws Exception {
        // Initialize the database
        secondaryServiceLinesMasterRepository.saveAndFlush(secondaryServiceLinesMaster);

        int databaseSizeBeforeUpdate = secondaryServiceLinesMasterRepository.findAll().size();

        // Update the secondaryServiceLinesMaster
        SecondaryServiceLinesMaster updatedSecondaryServiceLinesMaster = secondaryServiceLinesMasterRepository
            .findById(secondaryServiceLinesMaster.getChangeHealthSecondarySubmisionServicelinesId())
            .get();
        // Disconnect from session so that the updates on updatedSecondaryServiceLinesMaster are not directly saved in db
        em.detach(updatedSecondaryServiceLinesMaster);
        updatedSecondaryServiceLinesMaster
            .changeHealthSecondarySubmisionMasterId(UPDATED_CHANGE_HEALTH_SECONDARY_SUBMISION_MASTER_ID)
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
            .claimServicelineControlNo(UPDATED_CLAIM_SERVICELINE_CONTROL_NO)
            .procedureIdentifier(UPDATED_PROCEDURE_IDENTIFIER)
            .insertedByName(UPDATED_INSERTED_BY_NAME)
            .status(UPDATED_STATUS)
            .orderingProviderFirstName(UPDATED_ORDERING_PROVIDER_FIRST_NAME)
            .orderingProviderLastName(UPDATED_ORDERING_PROVIDER_LAST_NAME)
            .orderingProviderNpi(UPDATED_ORDERING_PROVIDER_NPI)
            .reference(UPDATED_REFERENCE)
            .payorClaimControlNo(UPDATED_PAYOR_CLAIM_CONTROL_NO)
            .providerPaymentAmount(UPDATED_PROVIDER_PAYMENT_AMOUNT)
            .lineAdjustment(UPDATED_LINE_ADJUSTMENT)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedByName(UPDATED_UPDATED_BY_NAME);
        SecondaryServiceLinesMasterDTO secondaryServiceLinesMasterDTO = secondaryServiceLinesMasterMapper.toDto(
            updatedSecondaryServiceLinesMaster
        );

        restSecondaryServiceLinesMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, secondaryServiceLinesMasterDTO.getChangeHealthSecondarySubmisionServicelinesId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryServiceLinesMasterDTO))
            )
            .andExpect(status().isOk());

        // Validate the SecondaryServiceLinesMaster in the database
        List<SecondaryServiceLinesMaster> secondaryServiceLinesMasterList = secondaryServiceLinesMasterRepository.findAll();
        assertThat(secondaryServiceLinesMasterList).hasSize(databaseSizeBeforeUpdate);
        SecondaryServiceLinesMaster testSecondaryServiceLinesMaster = secondaryServiceLinesMasterList.get(
            secondaryServiceLinesMasterList.size() - 1
        );
        assertThat(testSecondaryServiceLinesMaster.getChangeHealthSecondarySubmisionMasterId())
            .isEqualTo(UPDATED_CHANGE_HEALTH_SECONDARY_SUBMISION_MASTER_ID);
        assertThat(testSecondaryServiceLinesMaster.getOriginalDos()).isEqualTo(UPDATED_ORIGINAL_DOS);
        assertThat(testSecondaryServiceLinesMaster.getDosTo()).isEqualTo(UPDATED_DOS_TO);
        assertThat(testSecondaryServiceLinesMaster.getProcCode()).isEqualTo(UPDATED_PROC_CODE);
        assertThat(testSecondaryServiceLinesMaster.getChargeAmt()).isEqualTo(UPDATED_CHARGE_AMT);
        assertThat(testSecondaryServiceLinesMaster.getItemUom()).isEqualTo(UPDATED_ITEM_UOM);
        assertThat(testSecondaryServiceLinesMaster.getModifier1()).isEqualTo(UPDATED_MODIFIER_1);
        assertThat(testSecondaryServiceLinesMaster.getModifier2()).isEqualTo(UPDATED_MODIFIER_2);
        assertThat(testSecondaryServiceLinesMaster.getModifier3()).isEqualTo(UPDATED_MODIFIER_3);
        assertThat(testSecondaryServiceLinesMaster.getModifier4()).isEqualTo(UPDATED_MODIFIER_4);
        assertThat(testSecondaryServiceLinesMaster.getIcdPointer()).isEqualTo(UPDATED_ICD_POINTER);
        assertThat(testSecondaryServiceLinesMaster.getQty()).isEqualTo(UPDATED_QTY);
        assertThat(testSecondaryServiceLinesMaster.getInsertedById()).isEqualTo(UPDATED_INSERTED_BY_ID);
        assertThat(testSecondaryServiceLinesMaster.getInsertedDate()).isEqualTo(UPDATED_INSERTED_DATE);
        assertThat(testSecondaryServiceLinesMaster.getClaimServicelineControlNo()).isEqualTo(UPDATED_CLAIM_SERVICELINE_CONTROL_NO);
        assertThat(testSecondaryServiceLinesMaster.getProcedureIdentifier()).isEqualTo(UPDATED_PROCEDURE_IDENTIFIER);
        assertThat(testSecondaryServiceLinesMaster.getInsertedByName()).isEqualTo(UPDATED_INSERTED_BY_NAME);
        assertThat(testSecondaryServiceLinesMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSecondaryServiceLinesMaster.getOrderingProviderFirstName()).isEqualTo(UPDATED_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testSecondaryServiceLinesMaster.getOrderingProviderLastName()).isEqualTo(UPDATED_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testSecondaryServiceLinesMaster.getOrderingProviderNpi()).isEqualTo(UPDATED_ORDERING_PROVIDER_NPI);
        assertThat(testSecondaryServiceLinesMaster.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testSecondaryServiceLinesMaster.getPayorClaimControlNo()).isEqualTo(UPDATED_PAYOR_CLAIM_CONTROL_NO);
        assertThat(testSecondaryServiceLinesMaster.getProviderPaymentAmount()).isEqualTo(UPDATED_PROVIDER_PAYMENT_AMOUNT);
        assertThat(testSecondaryServiceLinesMaster.getLineAdjustment()).isEqualTo(UPDATED_LINE_ADJUSTMENT);
        assertThat(testSecondaryServiceLinesMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testSecondaryServiceLinesMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testSecondaryServiceLinesMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
    }

    @Test
    @Transactional
    void putNonExistingSecondaryServiceLinesMaster() throws Exception {
        int databaseSizeBeforeUpdate = secondaryServiceLinesMasterRepository.findAll().size();
        secondaryServiceLinesMaster.setChangeHealthSecondarySubmisionServicelinesId(count.incrementAndGet());

        // Create the SecondaryServiceLinesMaster
        SecondaryServiceLinesMasterDTO secondaryServiceLinesMasterDTO = secondaryServiceLinesMasterMapper.toDto(
            secondaryServiceLinesMaster
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSecondaryServiceLinesMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, secondaryServiceLinesMasterDTO.getChangeHealthSecondarySubmisionServicelinesId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryServiceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SecondaryServiceLinesMaster in the database
        List<SecondaryServiceLinesMaster> secondaryServiceLinesMasterList = secondaryServiceLinesMasterRepository.findAll();
        assertThat(secondaryServiceLinesMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSecondaryServiceLinesMaster() throws Exception {
        int databaseSizeBeforeUpdate = secondaryServiceLinesMasterRepository.findAll().size();
        secondaryServiceLinesMaster.setChangeHealthSecondarySubmisionServicelinesId(count.incrementAndGet());

        // Create the SecondaryServiceLinesMaster
        SecondaryServiceLinesMasterDTO secondaryServiceLinesMasterDTO = secondaryServiceLinesMasterMapper.toDto(
            secondaryServiceLinesMaster
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSecondaryServiceLinesMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryServiceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SecondaryServiceLinesMaster in the database
        List<SecondaryServiceLinesMaster> secondaryServiceLinesMasterList = secondaryServiceLinesMasterRepository.findAll();
        assertThat(secondaryServiceLinesMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSecondaryServiceLinesMaster() throws Exception {
        int databaseSizeBeforeUpdate = secondaryServiceLinesMasterRepository.findAll().size();
        secondaryServiceLinesMaster.setChangeHealthSecondarySubmisionServicelinesId(count.incrementAndGet());

        // Create the SecondaryServiceLinesMaster
        SecondaryServiceLinesMasterDTO secondaryServiceLinesMasterDTO = secondaryServiceLinesMasterMapper.toDto(
            secondaryServiceLinesMaster
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSecondaryServiceLinesMasterMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secondaryServiceLinesMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SecondaryServiceLinesMaster in the database
        List<SecondaryServiceLinesMaster> secondaryServiceLinesMasterList = secondaryServiceLinesMasterRepository.findAll();
        assertThat(secondaryServiceLinesMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSecondaryServiceLinesMasterWithPatch() throws Exception {
        // Initialize the database
        secondaryServiceLinesMasterRepository.saveAndFlush(secondaryServiceLinesMaster);

        int databaseSizeBeforeUpdate = secondaryServiceLinesMasterRepository.findAll().size();

        // Update the secondaryServiceLinesMaster using partial update
        SecondaryServiceLinesMaster partialUpdatedSecondaryServiceLinesMaster = new SecondaryServiceLinesMaster();
        partialUpdatedSecondaryServiceLinesMaster.setChangeHealthSecondarySubmisionServicelinesId(
            secondaryServiceLinesMaster.getChangeHealthSecondarySubmisionServicelinesId()
        );

        partialUpdatedSecondaryServiceLinesMaster
            .chargeAmt(UPDATED_CHARGE_AMT)
            .itemUom(UPDATED_ITEM_UOM)
            .modifier2(UPDATED_MODIFIER_2)
            .claimServicelineControlNo(UPDATED_CLAIM_SERVICELINE_CONTROL_NO)
            .insertedByName(UPDATED_INSERTED_BY_NAME)
            .status(UPDATED_STATUS)
            .orderingProviderFirstName(UPDATED_ORDERING_PROVIDER_FIRST_NAME)
            .payorClaimControlNo(UPDATED_PAYOR_CLAIM_CONTROL_NO)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME);

        restSecondaryServiceLinesMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSecondaryServiceLinesMaster.getChangeHealthSecondarySubmisionServicelinesId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSecondaryServiceLinesMaster))
            )
            .andExpect(status().isOk());

        // Validate the SecondaryServiceLinesMaster in the database
        List<SecondaryServiceLinesMaster> secondaryServiceLinesMasterList = secondaryServiceLinesMasterRepository.findAll();
        assertThat(secondaryServiceLinesMasterList).hasSize(databaseSizeBeforeUpdate);
        SecondaryServiceLinesMaster testSecondaryServiceLinesMaster = secondaryServiceLinesMasterList.get(
            secondaryServiceLinesMasterList.size() - 1
        );
        assertThat(testSecondaryServiceLinesMaster.getChangeHealthSecondarySubmisionMasterId())
            .isEqualTo(DEFAULT_CHANGE_HEALTH_SECONDARY_SUBMISION_MASTER_ID);
        assertThat(testSecondaryServiceLinesMaster.getOriginalDos()).isEqualTo(DEFAULT_ORIGINAL_DOS);
        assertThat(testSecondaryServiceLinesMaster.getDosTo()).isEqualTo(DEFAULT_DOS_TO);
        assertThat(testSecondaryServiceLinesMaster.getProcCode()).isEqualTo(DEFAULT_PROC_CODE);
        assertThat(testSecondaryServiceLinesMaster.getChargeAmt()).isEqualTo(UPDATED_CHARGE_AMT);
        assertThat(testSecondaryServiceLinesMaster.getItemUom()).isEqualTo(UPDATED_ITEM_UOM);
        assertThat(testSecondaryServiceLinesMaster.getModifier1()).isEqualTo(DEFAULT_MODIFIER_1);
        assertThat(testSecondaryServiceLinesMaster.getModifier2()).isEqualTo(UPDATED_MODIFIER_2);
        assertThat(testSecondaryServiceLinesMaster.getModifier3()).isEqualTo(DEFAULT_MODIFIER_3);
        assertThat(testSecondaryServiceLinesMaster.getModifier4()).isEqualTo(DEFAULT_MODIFIER_4);
        assertThat(testSecondaryServiceLinesMaster.getIcdPointer()).isEqualTo(DEFAULT_ICD_POINTER);
        assertThat(testSecondaryServiceLinesMaster.getQty()).isEqualTo(DEFAULT_QTY);
        assertThat(testSecondaryServiceLinesMaster.getInsertedById()).isEqualTo(DEFAULT_INSERTED_BY_ID);
        assertThat(testSecondaryServiceLinesMaster.getInsertedDate()).isEqualTo(DEFAULT_INSERTED_DATE);
        assertThat(testSecondaryServiceLinesMaster.getClaimServicelineControlNo()).isEqualTo(UPDATED_CLAIM_SERVICELINE_CONTROL_NO);
        assertThat(testSecondaryServiceLinesMaster.getProcedureIdentifier()).isEqualTo(DEFAULT_PROCEDURE_IDENTIFIER);
        assertThat(testSecondaryServiceLinesMaster.getInsertedByName()).isEqualTo(UPDATED_INSERTED_BY_NAME);
        assertThat(testSecondaryServiceLinesMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSecondaryServiceLinesMaster.getOrderingProviderFirstName()).isEqualTo(UPDATED_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testSecondaryServiceLinesMaster.getOrderingProviderLastName()).isEqualTo(DEFAULT_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testSecondaryServiceLinesMaster.getOrderingProviderNpi()).isEqualTo(DEFAULT_ORDERING_PROVIDER_NPI);
        assertThat(testSecondaryServiceLinesMaster.getReference()).isEqualTo(DEFAULT_REFERENCE);
        assertThat(testSecondaryServiceLinesMaster.getPayorClaimControlNo()).isEqualTo(UPDATED_PAYOR_CLAIM_CONTROL_NO);
        assertThat(testSecondaryServiceLinesMaster.getProviderPaymentAmount()).isEqualTo(DEFAULT_PROVIDER_PAYMENT_AMOUNT);
        assertThat(testSecondaryServiceLinesMaster.getLineAdjustment()).isEqualTo(DEFAULT_LINE_ADJUSTMENT);
        assertThat(testSecondaryServiceLinesMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testSecondaryServiceLinesMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testSecondaryServiceLinesMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
    }

    @Test
    @Transactional
    void fullUpdateSecondaryServiceLinesMasterWithPatch() throws Exception {
        // Initialize the database
        secondaryServiceLinesMasterRepository.saveAndFlush(secondaryServiceLinesMaster);

        int databaseSizeBeforeUpdate = secondaryServiceLinesMasterRepository.findAll().size();

        // Update the secondaryServiceLinesMaster using partial update
        SecondaryServiceLinesMaster partialUpdatedSecondaryServiceLinesMaster = new SecondaryServiceLinesMaster();
        partialUpdatedSecondaryServiceLinesMaster.setChangeHealthSecondarySubmisionServicelinesId(
            secondaryServiceLinesMaster.getChangeHealthSecondarySubmisionServicelinesId()
        );

        partialUpdatedSecondaryServiceLinesMaster
            .changeHealthSecondarySubmisionMasterId(UPDATED_CHANGE_HEALTH_SECONDARY_SUBMISION_MASTER_ID)
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
            .claimServicelineControlNo(UPDATED_CLAIM_SERVICELINE_CONTROL_NO)
            .procedureIdentifier(UPDATED_PROCEDURE_IDENTIFIER)
            .insertedByName(UPDATED_INSERTED_BY_NAME)
            .status(UPDATED_STATUS)
            .orderingProviderFirstName(UPDATED_ORDERING_PROVIDER_FIRST_NAME)
            .orderingProviderLastName(UPDATED_ORDERING_PROVIDER_LAST_NAME)
            .orderingProviderNpi(UPDATED_ORDERING_PROVIDER_NPI)
            .reference(UPDATED_REFERENCE)
            .payorClaimControlNo(UPDATED_PAYOR_CLAIM_CONTROL_NO)
            .providerPaymentAmount(UPDATED_PROVIDER_PAYMENT_AMOUNT)
            .lineAdjustment(UPDATED_LINE_ADJUSTMENT)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedByName(UPDATED_UPDATED_BY_NAME);

        restSecondaryServiceLinesMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSecondaryServiceLinesMaster.getChangeHealthSecondarySubmisionServicelinesId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSecondaryServiceLinesMaster))
            )
            .andExpect(status().isOk());

        // Validate the SecondaryServiceLinesMaster in the database
        List<SecondaryServiceLinesMaster> secondaryServiceLinesMasterList = secondaryServiceLinesMasterRepository.findAll();
        assertThat(secondaryServiceLinesMasterList).hasSize(databaseSizeBeforeUpdate);
        SecondaryServiceLinesMaster testSecondaryServiceLinesMaster = secondaryServiceLinesMasterList.get(
            secondaryServiceLinesMasterList.size() - 1
        );
        assertThat(testSecondaryServiceLinesMaster.getChangeHealthSecondarySubmisionMasterId())
            .isEqualTo(UPDATED_CHANGE_HEALTH_SECONDARY_SUBMISION_MASTER_ID);
        assertThat(testSecondaryServiceLinesMaster.getOriginalDos()).isEqualTo(UPDATED_ORIGINAL_DOS);
        assertThat(testSecondaryServiceLinesMaster.getDosTo()).isEqualTo(UPDATED_DOS_TO);
        assertThat(testSecondaryServiceLinesMaster.getProcCode()).isEqualTo(UPDATED_PROC_CODE);
        assertThat(testSecondaryServiceLinesMaster.getChargeAmt()).isEqualTo(UPDATED_CHARGE_AMT);
        assertThat(testSecondaryServiceLinesMaster.getItemUom()).isEqualTo(UPDATED_ITEM_UOM);
        assertThat(testSecondaryServiceLinesMaster.getModifier1()).isEqualTo(UPDATED_MODIFIER_1);
        assertThat(testSecondaryServiceLinesMaster.getModifier2()).isEqualTo(UPDATED_MODIFIER_2);
        assertThat(testSecondaryServiceLinesMaster.getModifier3()).isEqualTo(UPDATED_MODIFIER_3);
        assertThat(testSecondaryServiceLinesMaster.getModifier4()).isEqualTo(UPDATED_MODIFIER_4);
        assertThat(testSecondaryServiceLinesMaster.getIcdPointer()).isEqualTo(UPDATED_ICD_POINTER);
        assertThat(testSecondaryServiceLinesMaster.getQty()).isEqualTo(UPDATED_QTY);
        assertThat(testSecondaryServiceLinesMaster.getInsertedById()).isEqualTo(UPDATED_INSERTED_BY_ID);
        assertThat(testSecondaryServiceLinesMaster.getInsertedDate()).isEqualTo(UPDATED_INSERTED_DATE);
        assertThat(testSecondaryServiceLinesMaster.getClaimServicelineControlNo()).isEqualTo(UPDATED_CLAIM_SERVICELINE_CONTROL_NO);
        assertThat(testSecondaryServiceLinesMaster.getProcedureIdentifier()).isEqualTo(UPDATED_PROCEDURE_IDENTIFIER);
        assertThat(testSecondaryServiceLinesMaster.getInsertedByName()).isEqualTo(UPDATED_INSERTED_BY_NAME);
        assertThat(testSecondaryServiceLinesMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSecondaryServiceLinesMaster.getOrderingProviderFirstName()).isEqualTo(UPDATED_ORDERING_PROVIDER_FIRST_NAME);
        assertThat(testSecondaryServiceLinesMaster.getOrderingProviderLastName()).isEqualTo(UPDATED_ORDERING_PROVIDER_LAST_NAME);
        assertThat(testSecondaryServiceLinesMaster.getOrderingProviderNpi()).isEqualTo(UPDATED_ORDERING_PROVIDER_NPI);
        assertThat(testSecondaryServiceLinesMaster.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testSecondaryServiceLinesMaster.getPayorClaimControlNo()).isEqualTo(UPDATED_PAYOR_CLAIM_CONTROL_NO);
        assertThat(testSecondaryServiceLinesMaster.getProviderPaymentAmount()).isEqualTo(UPDATED_PROVIDER_PAYMENT_AMOUNT);
        assertThat(testSecondaryServiceLinesMaster.getLineAdjustment()).isEqualTo(UPDATED_LINE_ADJUSTMENT);
        assertThat(testSecondaryServiceLinesMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testSecondaryServiceLinesMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testSecondaryServiceLinesMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
    }

    @Test
    @Transactional
    void patchNonExistingSecondaryServiceLinesMaster() throws Exception {
        int databaseSizeBeforeUpdate = secondaryServiceLinesMasterRepository.findAll().size();
        secondaryServiceLinesMaster.setChangeHealthSecondarySubmisionServicelinesId(count.incrementAndGet());

        // Create the SecondaryServiceLinesMaster
        SecondaryServiceLinesMasterDTO secondaryServiceLinesMasterDTO = secondaryServiceLinesMasterMapper.toDto(
            secondaryServiceLinesMaster
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSecondaryServiceLinesMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, secondaryServiceLinesMasterDTO.getChangeHealthSecondarySubmisionServicelinesId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(secondaryServiceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SecondaryServiceLinesMaster in the database
        List<SecondaryServiceLinesMaster> secondaryServiceLinesMasterList = secondaryServiceLinesMasterRepository.findAll();
        assertThat(secondaryServiceLinesMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSecondaryServiceLinesMaster() throws Exception {
        int databaseSizeBeforeUpdate = secondaryServiceLinesMasterRepository.findAll().size();
        secondaryServiceLinesMaster.setChangeHealthSecondarySubmisionServicelinesId(count.incrementAndGet());

        // Create the SecondaryServiceLinesMaster
        SecondaryServiceLinesMasterDTO secondaryServiceLinesMasterDTO = secondaryServiceLinesMasterMapper.toDto(
            secondaryServiceLinesMaster
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSecondaryServiceLinesMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(secondaryServiceLinesMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SecondaryServiceLinesMaster in the database
        List<SecondaryServiceLinesMaster> secondaryServiceLinesMasterList = secondaryServiceLinesMasterRepository.findAll();
        assertThat(secondaryServiceLinesMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSecondaryServiceLinesMaster() throws Exception {
        int databaseSizeBeforeUpdate = secondaryServiceLinesMasterRepository.findAll().size();
        secondaryServiceLinesMaster.setChangeHealthSecondarySubmisionServicelinesId(count.incrementAndGet());

        // Create the SecondaryServiceLinesMaster
        SecondaryServiceLinesMasterDTO secondaryServiceLinesMasterDTO = secondaryServiceLinesMasterMapper.toDto(
            secondaryServiceLinesMaster
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSecondaryServiceLinesMasterMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(secondaryServiceLinesMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SecondaryServiceLinesMaster in the database
        List<SecondaryServiceLinesMaster> secondaryServiceLinesMasterList = secondaryServiceLinesMasterRepository.findAll();
        assertThat(secondaryServiceLinesMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSecondaryServiceLinesMaster() throws Exception {
        // Initialize the database
        secondaryServiceLinesMasterRepository.saveAndFlush(secondaryServiceLinesMaster);

        int databaseSizeBeforeDelete = secondaryServiceLinesMasterRepository.findAll().size();

        // Delete the secondaryServiceLinesMaster
        restSecondaryServiceLinesMasterMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, secondaryServiceLinesMaster.getChangeHealthSecondarySubmisionServicelinesId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SecondaryServiceLinesMaster> secondaryServiceLinesMasterList = secondaryServiceLinesMasterRepository.findAll();
        assertThat(secondaryServiceLinesMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
