package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.MarketingReferalTypeMaster;
import com.sunknowledge.dme.rcm.repository.MarketingReferalTypeMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.MarketingReferalTypeMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.MarketingReferalTypeMasterMapper;
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
 * Integration tests for the {@link MarketingReferalTypeMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class MarketingReferalTypeMasterResourceIT {

    private static final String DEFAULT_MARKETING_REFERRAL_TYPE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_MARKETING_REFERRAL_TYPE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_MARKETING_REFERAL_TYPE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_MARKETING_REFERAL_TYPE_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final UUID DEFAULT_MARKETING_REFERAL_TYPE_MASTER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_MARKETING_REFERAL_TYPE_MASTER_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/marketing-referal-type-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{marketingReferralTypeId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private MarketingReferalTypeMasterRepository marketingReferalTypeMasterRepository;

    @Autowired
    private MarketingReferalTypeMasterMapper marketingReferalTypeMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMarketingReferalTypeMasterMockMvc;

    private MarketingReferalTypeMaster marketingReferalTypeMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MarketingReferalTypeMaster createEntity(EntityManager em) {
        MarketingReferalTypeMaster marketingReferalTypeMaster = new MarketingReferalTypeMaster()
            .marketingReferralTypeCode(DEFAULT_MARKETING_REFERRAL_TYPE_CODE)
            .marketingReferalTypeDescription(DEFAULT_MARKETING_REFERAL_TYPE_DESCRIPTION)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .marketingReferalTypeMasterUUID(DEFAULT_MARKETING_REFERAL_TYPE_MASTER_UUID);
        return marketingReferalTypeMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MarketingReferalTypeMaster createUpdatedEntity(EntityManager em) {
        MarketingReferalTypeMaster marketingReferalTypeMaster = new MarketingReferalTypeMaster()
            .marketingReferralTypeCode(UPDATED_MARKETING_REFERRAL_TYPE_CODE)
            .marketingReferalTypeDescription(UPDATED_MARKETING_REFERAL_TYPE_DESCRIPTION)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .marketingReferalTypeMasterUUID(UPDATED_MARKETING_REFERAL_TYPE_MASTER_UUID);
        return marketingReferalTypeMaster;
    }

    @BeforeEach
    public void initTest() {
        marketingReferalTypeMaster = createEntity(em);
    }

    @Test
    @Transactional
    void createMarketingReferalTypeMaster() throws Exception {
        int databaseSizeBeforeCreate = marketingReferalTypeMasterRepository.findAll().size();
        // Create the MarketingReferalTypeMaster
        MarketingReferalTypeMasterDTO marketingReferalTypeMasterDTO = marketingReferalTypeMasterMapper.toDto(marketingReferalTypeMaster);
        restMarketingReferalTypeMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(marketingReferalTypeMasterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the MarketingReferalTypeMaster in the database
        List<MarketingReferalTypeMaster> marketingReferalTypeMasterList = marketingReferalTypeMasterRepository.findAll();
        assertThat(marketingReferalTypeMasterList).hasSize(databaseSizeBeforeCreate + 1);
        MarketingReferalTypeMaster testMarketingReferalTypeMaster = marketingReferalTypeMasterList.get(
            marketingReferalTypeMasterList.size() - 1
        );
        assertThat(testMarketingReferalTypeMaster.getMarketingReferralTypeCode()).isEqualTo(DEFAULT_MARKETING_REFERRAL_TYPE_CODE);
        assertThat(testMarketingReferalTypeMaster.getMarketingReferalTypeDescription())
            .isEqualTo(DEFAULT_MARKETING_REFERAL_TYPE_DESCRIPTION);
        assertThat(testMarketingReferalTypeMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testMarketingReferalTypeMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testMarketingReferalTypeMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testMarketingReferalTypeMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testMarketingReferalTypeMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testMarketingReferalTypeMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testMarketingReferalTypeMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testMarketingReferalTypeMaster.getMarketingReferalTypeMasterUUID())
            .isEqualTo(DEFAULT_MARKETING_REFERAL_TYPE_MASTER_UUID);
    }

    @Test
    @Transactional
    void createMarketingReferalTypeMasterWithExistingId() throws Exception {
        // Create the MarketingReferalTypeMaster with an existing ID
        marketingReferalTypeMaster.setMarketingReferralTypeId(1L);
        MarketingReferalTypeMasterDTO marketingReferalTypeMasterDTO = marketingReferalTypeMasterMapper.toDto(marketingReferalTypeMaster);

        int databaseSizeBeforeCreate = marketingReferalTypeMasterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restMarketingReferalTypeMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(marketingReferalTypeMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the MarketingReferalTypeMaster in the database
        List<MarketingReferalTypeMaster> marketingReferalTypeMasterList = marketingReferalTypeMasterRepository.findAll();
        assertThat(marketingReferalTypeMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllMarketingReferalTypeMasters() throws Exception {
        // Initialize the database
        marketingReferalTypeMasterRepository.saveAndFlush(marketingReferalTypeMaster);

        // Get all the marketingReferalTypeMasterList
        restMarketingReferalTypeMasterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=marketingReferralTypeId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].marketingReferralTypeId").value(hasItem(marketingReferalTypeMaster.getMarketingReferralTypeId().intValue()))
            )
            .andExpect(jsonPath("$.[*].marketingReferralTypeCode").value(hasItem(DEFAULT_MARKETING_REFERRAL_TYPE_CODE)))
            .andExpect(jsonPath("$.[*].marketingReferalTypeDescription").value(hasItem(DEFAULT_MARKETING_REFERAL_TYPE_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(
                jsonPath("$.[*].marketingReferalTypeMasterUUID").value(hasItem(DEFAULT_MARKETING_REFERAL_TYPE_MASTER_UUID.toString()))
            );
    }

    @Test
    @Transactional
    void getMarketingReferalTypeMaster() throws Exception {
        // Initialize the database
        marketingReferalTypeMasterRepository.saveAndFlush(marketingReferalTypeMaster);

        // Get the marketingReferalTypeMaster
        restMarketingReferalTypeMasterMockMvc
            .perform(get(ENTITY_API_URL_ID, marketingReferalTypeMaster.getMarketingReferralTypeId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.marketingReferralTypeId").value(marketingReferalTypeMaster.getMarketingReferralTypeId().intValue()))
            .andExpect(jsonPath("$.marketingReferralTypeCode").value(DEFAULT_MARKETING_REFERRAL_TYPE_CODE))
            .andExpect(jsonPath("$.marketingReferalTypeDescription").value(DEFAULT_MARKETING_REFERAL_TYPE_DESCRIPTION))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.marketingReferalTypeMasterUUID").value(DEFAULT_MARKETING_REFERAL_TYPE_MASTER_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingMarketingReferalTypeMaster() throws Exception {
        // Get the marketingReferalTypeMaster
        restMarketingReferalTypeMasterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewMarketingReferalTypeMaster() throws Exception {
        // Initialize the database
        marketingReferalTypeMasterRepository.saveAndFlush(marketingReferalTypeMaster);

        int databaseSizeBeforeUpdate = marketingReferalTypeMasterRepository.findAll().size();

        // Update the marketingReferalTypeMaster
        MarketingReferalTypeMaster updatedMarketingReferalTypeMaster = marketingReferalTypeMasterRepository
            .findById(marketingReferalTypeMaster.getMarketingReferralTypeId())
            .get();
        // Disconnect from session so that the updates on updatedMarketingReferalTypeMaster are not directly saved in db
        em.detach(updatedMarketingReferalTypeMaster);
        updatedMarketingReferalTypeMaster
            .marketingReferralTypeCode(UPDATED_MARKETING_REFERRAL_TYPE_CODE)
            .marketingReferalTypeDescription(UPDATED_MARKETING_REFERAL_TYPE_DESCRIPTION)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .marketingReferalTypeMasterUUID(UPDATED_MARKETING_REFERAL_TYPE_MASTER_UUID);
        MarketingReferalTypeMasterDTO marketingReferalTypeMasterDTO = marketingReferalTypeMasterMapper.toDto(
            updatedMarketingReferalTypeMaster
        );

        restMarketingReferalTypeMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, marketingReferalTypeMasterDTO.getMarketingReferralTypeId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(marketingReferalTypeMasterDTO))
            )
            .andExpect(status().isOk());

        // Validate the MarketingReferalTypeMaster in the database
        List<MarketingReferalTypeMaster> marketingReferalTypeMasterList = marketingReferalTypeMasterRepository.findAll();
        assertThat(marketingReferalTypeMasterList).hasSize(databaseSizeBeforeUpdate);
        MarketingReferalTypeMaster testMarketingReferalTypeMaster = marketingReferalTypeMasterList.get(
            marketingReferalTypeMasterList.size() - 1
        );
        assertThat(testMarketingReferalTypeMaster.getMarketingReferralTypeCode()).isEqualTo(UPDATED_MARKETING_REFERRAL_TYPE_CODE);
        assertThat(testMarketingReferalTypeMaster.getMarketingReferalTypeDescription())
            .isEqualTo(UPDATED_MARKETING_REFERAL_TYPE_DESCRIPTION);
        assertThat(testMarketingReferalTypeMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testMarketingReferalTypeMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testMarketingReferalTypeMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testMarketingReferalTypeMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testMarketingReferalTypeMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testMarketingReferalTypeMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testMarketingReferalTypeMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testMarketingReferalTypeMaster.getMarketingReferalTypeMasterUUID())
            .isEqualTo(UPDATED_MARKETING_REFERAL_TYPE_MASTER_UUID);
    }

    @Test
    @Transactional
    void putNonExistingMarketingReferalTypeMaster() throws Exception {
        int databaseSizeBeforeUpdate = marketingReferalTypeMasterRepository.findAll().size();
        marketingReferalTypeMaster.setMarketingReferralTypeId(count.incrementAndGet());

        // Create the MarketingReferalTypeMaster
        MarketingReferalTypeMasterDTO marketingReferalTypeMasterDTO = marketingReferalTypeMasterMapper.toDto(marketingReferalTypeMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMarketingReferalTypeMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, marketingReferalTypeMasterDTO.getMarketingReferralTypeId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(marketingReferalTypeMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the MarketingReferalTypeMaster in the database
        List<MarketingReferalTypeMaster> marketingReferalTypeMasterList = marketingReferalTypeMasterRepository.findAll();
        assertThat(marketingReferalTypeMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchMarketingReferalTypeMaster() throws Exception {
        int databaseSizeBeforeUpdate = marketingReferalTypeMasterRepository.findAll().size();
        marketingReferalTypeMaster.setMarketingReferralTypeId(count.incrementAndGet());

        // Create the MarketingReferalTypeMaster
        MarketingReferalTypeMasterDTO marketingReferalTypeMasterDTO = marketingReferalTypeMasterMapper.toDto(marketingReferalTypeMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMarketingReferalTypeMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(marketingReferalTypeMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the MarketingReferalTypeMaster in the database
        List<MarketingReferalTypeMaster> marketingReferalTypeMasterList = marketingReferalTypeMasterRepository.findAll();
        assertThat(marketingReferalTypeMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamMarketingReferalTypeMaster() throws Exception {
        int databaseSizeBeforeUpdate = marketingReferalTypeMasterRepository.findAll().size();
        marketingReferalTypeMaster.setMarketingReferralTypeId(count.incrementAndGet());

        // Create the MarketingReferalTypeMaster
        MarketingReferalTypeMasterDTO marketingReferalTypeMasterDTO = marketingReferalTypeMasterMapper.toDto(marketingReferalTypeMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMarketingReferalTypeMasterMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(marketingReferalTypeMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the MarketingReferalTypeMaster in the database
        List<MarketingReferalTypeMaster> marketingReferalTypeMasterList = marketingReferalTypeMasterRepository.findAll();
        assertThat(marketingReferalTypeMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateMarketingReferalTypeMasterWithPatch() throws Exception {
        // Initialize the database
        marketingReferalTypeMasterRepository.saveAndFlush(marketingReferalTypeMaster);

        int databaseSizeBeforeUpdate = marketingReferalTypeMasterRepository.findAll().size();

        // Update the marketingReferalTypeMaster using partial update
        MarketingReferalTypeMaster partialUpdatedMarketingReferalTypeMaster = new MarketingReferalTypeMaster();
        partialUpdatedMarketingReferalTypeMaster.setMarketingReferralTypeId(marketingReferalTypeMaster.getMarketingReferralTypeId());

        partialUpdatedMarketingReferalTypeMaster
            .marketingReferalTypeDescription(UPDATED_MARKETING_REFERAL_TYPE_DESCRIPTION)
            .updatedByName(UPDATED_UPDATED_BY_NAME);

        restMarketingReferalTypeMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMarketingReferalTypeMaster.getMarketingReferralTypeId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMarketingReferalTypeMaster))
            )
            .andExpect(status().isOk());

        // Validate the MarketingReferalTypeMaster in the database
        List<MarketingReferalTypeMaster> marketingReferalTypeMasterList = marketingReferalTypeMasterRepository.findAll();
        assertThat(marketingReferalTypeMasterList).hasSize(databaseSizeBeforeUpdate);
        MarketingReferalTypeMaster testMarketingReferalTypeMaster = marketingReferalTypeMasterList.get(
            marketingReferalTypeMasterList.size() - 1
        );
        assertThat(testMarketingReferalTypeMaster.getMarketingReferralTypeCode()).isEqualTo(DEFAULT_MARKETING_REFERRAL_TYPE_CODE);
        assertThat(testMarketingReferalTypeMaster.getMarketingReferalTypeDescription())
            .isEqualTo(UPDATED_MARKETING_REFERAL_TYPE_DESCRIPTION);
        assertThat(testMarketingReferalTypeMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testMarketingReferalTypeMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testMarketingReferalTypeMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testMarketingReferalTypeMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testMarketingReferalTypeMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testMarketingReferalTypeMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testMarketingReferalTypeMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testMarketingReferalTypeMaster.getMarketingReferalTypeMasterUUID())
            .isEqualTo(DEFAULT_MARKETING_REFERAL_TYPE_MASTER_UUID);
    }

    @Test
    @Transactional
    void fullUpdateMarketingReferalTypeMasterWithPatch() throws Exception {
        // Initialize the database
        marketingReferalTypeMasterRepository.saveAndFlush(marketingReferalTypeMaster);

        int databaseSizeBeforeUpdate = marketingReferalTypeMasterRepository.findAll().size();

        // Update the marketingReferalTypeMaster using partial update
        MarketingReferalTypeMaster partialUpdatedMarketingReferalTypeMaster = new MarketingReferalTypeMaster();
        partialUpdatedMarketingReferalTypeMaster.setMarketingReferralTypeId(marketingReferalTypeMaster.getMarketingReferralTypeId());

        partialUpdatedMarketingReferalTypeMaster
            .marketingReferralTypeCode(UPDATED_MARKETING_REFERRAL_TYPE_CODE)
            .marketingReferalTypeDescription(UPDATED_MARKETING_REFERAL_TYPE_DESCRIPTION)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .marketingReferalTypeMasterUUID(UPDATED_MARKETING_REFERAL_TYPE_MASTER_UUID);

        restMarketingReferalTypeMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMarketingReferalTypeMaster.getMarketingReferralTypeId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMarketingReferalTypeMaster))
            )
            .andExpect(status().isOk());

        // Validate the MarketingReferalTypeMaster in the database
        List<MarketingReferalTypeMaster> marketingReferalTypeMasterList = marketingReferalTypeMasterRepository.findAll();
        assertThat(marketingReferalTypeMasterList).hasSize(databaseSizeBeforeUpdate);
        MarketingReferalTypeMaster testMarketingReferalTypeMaster = marketingReferalTypeMasterList.get(
            marketingReferalTypeMasterList.size() - 1
        );
        assertThat(testMarketingReferalTypeMaster.getMarketingReferralTypeCode()).isEqualTo(UPDATED_MARKETING_REFERRAL_TYPE_CODE);
        assertThat(testMarketingReferalTypeMaster.getMarketingReferalTypeDescription())
            .isEqualTo(UPDATED_MARKETING_REFERAL_TYPE_DESCRIPTION);
        assertThat(testMarketingReferalTypeMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testMarketingReferalTypeMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testMarketingReferalTypeMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testMarketingReferalTypeMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testMarketingReferalTypeMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testMarketingReferalTypeMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testMarketingReferalTypeMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testMarketingReferalTypeMaster.getMarketingReferalTypeMasterUUID())
            .isEqualTo(UPDATED_MARKETING_REFERAL_TYPE_MASTER_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingMarketingReferalTypeMaster() throws Exception {
        int databaseSizeBeforeUpdate = marketingReferalTypeMasterRepository.findAll().size();
        marketingReferalTypeMaster.setMarketingReferralTypeId(count.incrementAndGet());

        // Create the MarketingReferalTypeMaster
        MarketingReferalTypeMasterDTO marketingReferalTypeMasterDTO = marketingReferalTypeMasterMapper.toDto(marketingReferalTypeMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMarketingReferalTypeMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, marketingReferalTypeMasterDTO.getMarketingReferralTypeId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(marketingReferalTypeMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the MarketingReferalTypeMaster in the database
        List<MarketingReferalTypeMaster> marketingReferalTypeMasterList = marketingReferalTypeMasterRepository.findAll();
        assertThat(marketingReferalTypeMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchMarketingReferalTypeMaster() throws Exception {
        int databaseSizeBeforeUpdate = marketingReferalTypeMasterRepository.findAll().size();
        marketingReferalTypeMaster.setMarketingReferralTypeId(count.incrementAndGet());

        // Create the MarketingReferalTypeMaster
        MarketingReferalTypeMasterDTO marketingReferalTypeMasterDTO = marketingReferalTypeMasterMapper.toDto(marketingReferalTypeMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMarketingReferalTypeMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(marketingReferalTypeMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the MarketingReferalTypeMaster in the database
        List<MarketingReferalTypeMaster> marketingReferalTypeMasterList = marketingReferalTypeMasterRepository.findAll();
        assertThat(marketingReferalTypeMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamMarketingReferalTypeMaster() throws Exception {
        int databaseSizeBeforeUpdate = marketingReferalTypeMasterRepository.findAll().size();
        marketingReferalTypeMaster.setMarketingReferralTypeId(count.incrementAndGet());

        // Create the MarketingReferalTypeMaster
        MarketingReferalTypeMasterDTO marketingReferalTypeMasterDTO = marketingReferalTypeMasterMapper.toDto(marketingReferalTypeMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMarketingReferalTypeMasterMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(marketingReferalTypeMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the MarketingReferalTypeMaster in the database
        List<MarketingReferalTypeMaster> marketingReferalTypeMasterList = marketingReferalTypeMasterRepository.findAll();
        assertThat(marketingReferalTypeMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteMarketingReferalTypeMaster() throws Exception {
        // Initialize the database
        marketingReferalTypeMasterRepository.saveAndFlush(marketingReferalTypeMaster);

        int databaseSizeBeforeDelete = marketingReferalTypeMasterRepository.findAll().size();

        // Delete the marketingReferalTypeMaster
        restMarketingReferalTypeMasterMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, marketingReferalTypeMaster.getMarketingReferralTypeId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MarketingReferalTypeMaster> marketingReferalTypeMasterList = marketingReferalTypeMasterRepository.findAll();
        assertThat(marketingReferalTypeMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
