package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.PriceMaster;
import com.sunknowledge.dme.rcm.repository.PriceMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.PriceMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.PriceMasterMapper;
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
 * Integration tests for the {@link PriceMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PriceMasterResourceIT {

    private static final String DEFAULT_PRICE_TABLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PRICE_TABLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_PRICE_CODE_GROUP = "AAAAAAAAAA";
    private static final String UPDATED_PRICE_CODE_GROUP = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final UUID DEFAULT_PRICE_MASTER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_PRICE_MASTER_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/price-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{priceTableId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PriceMasterRepository priceMasterRepository;

    @Autowired
    private PriceMasterMapper priceMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPriceMasterMockMvc;

    private PriceMaster priceMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PriceMaster createEntity(EntityManager em) {
        PriceMaster priceMaster = new PriceMaster()
            .priceTableName(DEFAULT_PRICE_TABLE_NAME)
            .description(DEFAULT_DESCRIPTION)
            .type(DEFAULT_TYPE)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .priceCodeGroup(DEFAULT_PRICE_CODE_GROUP)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .priceMasterUuid(DEFAULT_PRICE_MASTER_UUID);
        return priceMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PriceMaster createUpdatedEntity(EntityManager em) {
        PriceMaster priceMaster = new PriceMaster()
            .priceTableName(UPDATED_PRICE_TABLE_NAME)
            .description(UPDATED_DESCRIPTION)
            .type(UPDATED_TYPE)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .priceCodeGroup(UPDATED_PRICE_CODE_GROUP)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .priceMasterUuid(UPDATED_PRICE_MASTER_UUID);
        return priceMaster;
    }

    @BeforeEach
    public void initTest() {
        priceMaster = createEntity(em);
    }

    @Test
    @Transactional
    void createPriceMaster() throws Exception {
        int databaseSizeBeforeCreate = priceMasterRepository.findAll().size();
        // Create the PriceMaster
        PriceMasterDTO priceMasterDTO = priceMasterMapper.toDto(priceMaster);
        restPriceMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(priceMasterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the PriceMaster in the database
        List<PriceMaster> priceMasterList = priceMasterRepository.findAll();
        assertThat(priceMasterList).hasSize(databaseSizeBeforeCreate + 1);
        PriceMaster testPriceMaster = priceMasterList.get(priceMasterList.size() - 1);
        assertThat(testPriceMaster.getPriceTableName()).isEqualTo(DEFAULT_PRICE_TABLE_NAME);
        assertThat(testPriceMaster.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testPriceMaster.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testPriceMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPriceMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testPriceMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testPriceMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testPriceMaster.getPriceCodeGroup()).isEqualTo(DEFAULT_PRICE_CODE_GROUP);
        assertThat(testPriceMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testPriceMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testPriceMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testPriceMaster.getPriceMasterUuid()).isEqualTo(DEFAULT_PRICE_MASTER_UUID);
    }

    @Test
    @Transactional
    void createPriceMasterWithExistingId() throws Exception {
        // Create the PriceMaster with an existing ID
        priceMaster.setPriceTableId(1L);
        PriceMasterDTO priceMasterDTO = priceMasterMapper.toDto(priceMaster);

        int databaseSizeBeforeCreate = priceMasterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPriceMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(priceMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PriceMaster in the database
        List<PriceMaster> priceMasterList = priceMasterRepository.findAll();
        assertThat(priceMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPriceMasters() throws Exception {
        // Initialize the database
        priceMasterRepository.saveAndFlush(priceMaster);

        // Get all the priceMasterList
        restPriceMasterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=priceTableId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].priceTableId").value(hasItem(priceMaster.getPriceTableId().intValue())))
            .andExpect(jsonPath("$.[*].priceTableName").value(hasItem(DEFAULT_PRICE_TABLE_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].priceCodeGroup").value(hasItem(DEFAULT_PRICE_CODE_GROUP)))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].priceMasterUuid").value(hasItem(DEFAULT_PRICE_MASTER_UUID.toString())));
    }

    @Test
    @Transactional
    void getPriceMaster() throws Exception {
        // Initialize the database
        priceMasterRepository.saveAndFlush(priceMaster);

        // Get the priceMaster
        restPriceMasterMockMvc
            .perform(get(ENTITY_API_URL_ID, priceMaster.getPriceTableId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.priceTableId").value(priceMaster.getPriceTableId().intValue()))
            .andExpect(jsonPath("$.priceTableName").value(DEFAULT_PRICE_TABLE_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.priceCodeGroup").value(DEFAULT_PRICE_CODE_GROUP))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.priceMasterUuid").value(DEFAULT_PRICE_MASTER_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingPriceMaster() throws Exception {
        // Get the priceMaster
        restPriceMasterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingPriceMaster() throws Exception {
        // Initialize the database
        priceMasterRepository.saveAndFlush(priceMaster);

        int databaseSizeBeforeUpdate = priceMasterRepository.findAll().size();

        // Update the priceMaster
        PriceMaster updatedPriceMaster = priceMasterRepository.findById(priceMaster.getPriceTableId()).get();
        // Disconnect from session so that the updates on updatedPriceMaster are not directly saved in db
        em.detach(updatedPriceMaster);
        updatedPriceMaster
            .priceTableName(UPDATED_PRICE_TABLE_NAME)
            .description(UPDATED_DESCRIPTION)
            .type(UPDATED_TYPE)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .priceCodeGroup(UPDATED_PRICE_CODE_GROUP)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .priceMasterUuid(UPDATED_PRICE_MASTER_UUID);
        PriceMasterDTO priceMasterDTO = priceMasterMapper.toDto(updatedPriceMaster);

        restPriceMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, priceMasterDTO.getPriceTableId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(priceMasterDTO))
            )
            .andExpect(status().isOk());

        // Validate the PriceMaster in the database
        List<PriceMaster> priceMasterList = priceMasterRepository.findAll();
        assertThat(priceMasterList).hasSize(databaseSizeBeforeUpdate);
        PriceMaster testPriceMaster = priceMasterList.get(priceMasterList.size() - 1);
        assertThat(testPriceMaster.getPriceTableName()).isEqualTo(UPDATED_PRICE_TABLE_NAME);
        assertThat(testPriceMaster.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testPriceMaster.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testPriceMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPriceMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPriceMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPriceMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPriceMaster.getPriceCodeGroup()).isEqualTo(UPDATED_PRICE_CODE_GROUP);
        assertThat(testPriceMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPriceMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPriceMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPriceMaster.getPriceMasterUuid()).isEqualTo(UPDATED_PRICE_MASTER_UUID);
    }

    @Test
    @Transactional
    void putNonExistingPriceMaster() throws Exception {
        int databaseSizeBeforeUpdate = priceMasterRepository.findAll().size();
        priceMaster.setPriceTableId(count.incrementAndGet());

        // Create the PriceMaster
        PriceMasterDTO priceMasterDTO = priceMasterMapper.toDto(priceMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPriceMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, priceMasterDTO.getPriceTableId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(priceMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PriceMaster in the database
        List<PriceMaster> priceMasterList = priceMasterRepository.findAll();
        assertThat(priceMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPriceMaster() throws Exception {
        int databaseSizeBeforeUpdate = priceMasterRepository.findAll().size();
        priceMaster.setPriceTableId(count.incrementAndGet());

        // Create the PriceMaster
        PriceMasterDTO priceMasterDTO = priceMasterMapper.toDto(priceMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPriceMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(priceMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PriceMaster in the database
        List<PriceMaster> priceMasterList = priceMasterRepository.findAll();
        assertThat(priceMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPriceMaster() throws Exception {
        int databaseSizeBeforeUpdate = priceMasterRepository.findAll().size();
        priceMaster.setPriceTableId(count.incrementAndGet());

        // Create the PriceMaster
        PriceMasterDTO priceMasterDTO = priceMasterMapper.toDto(priceMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPriceMasterMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(priceMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PriceMaster in the database
        List<PriceMaster> priceMasterList = priceMasterRepository.findAll();
        assertThat(priceMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePriceMasterWithPatch() throws Exception {
        // Initialize the database
        priceMasterRepository.saveAndFlush(priceMaster);

        int databaseSizeBeforeUpdate = priceMasterRepository.findAll().size();

        // Update the priceMaster using partial update
        PriceMaster partialUpdatedPriceMaster = new PriceMaster();
        partialUpdatedPriceMaster.setPriceTableId(priceMaster.getPriceTableId());

        partialUpdatedPriceMaster
            .type(UPDATED_TYPE)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .priceCodeGroup(UPDATED_PRICE_CODE_GROUP)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .priceMasterUuid(UPDATED_PRICE_MASTER_UUID);

        restPriceMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPriceMaster.getPriceTableId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPriceMaster))
            )
            .andExpect(status().isOk());

        // Validate the PriceMaster in the database
        List<PriceMaster> priceMasterList = priceMasterRepository.findAll();
        assertThat(priceMasterList).hasSize(databaseSizeBeforeUpdate);
        PriceMaster testPriceMaster = priceMasterList.get(priceMasterList.size() - 1);
        assertThat(testPriceMaster.getPriceTableName()).isEqualTo(DEFAULT_PRICE_TABLE_NAME);
        assertThat(testPriceMaster.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testPriceMaster.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testPriceMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPriceMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testPriceMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPriceMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPriceMaster.getPriceCodeGroup()).isEqualTo(UPDATED_PRICE_CODE_GROUP);
        assertThat(testPriceMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testPriceMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPriceMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testPriceMaster.getPriceMasterUuid()).isEqualTo(UPDATED_PRICE_MASTER_UUID);
    }

    @Test
    @Transactional
    void fullUpdatePriceMasterWithPatch() throws Exception {
        // Initialize the database
        priceMasterRepository.saveAndFlush(priceMaster);

        int databaseSizeBeforeUpdate = priceMasterRepository.findAll().size();

        // Update the priceMaster using partial update
        PriceMaster partialUpdatedPriceMaster = new PriceMaster();
        partialUpdatedPriceMaster.setPriceTableId(priceMaster.getPriceTableId());

        partialUpdatedPriceMaster
            .priceTableName(UPDATED_PRICE_TABLE_NAME)
            .description(UPDATED_DESCRIPTION)
            .type(UPDATED_TYPE)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .priceCodeGroup(UPDATED_PRICE_CODE_GROUP)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .priceMasterUuid(UPDATED_PRICE_MASTER_UUID);

        restPriceMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPriceMaster.getPriceTableId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPriceMaster))
            )
            .andExpect(status().isOk());

        // Validate the PriceMaster in the database
        List<PriceMaster> priceMasterList = priceMasterRepository.findAll();
        assertThat(priceMasterList).hasSize(databaseSizeBeforeUpdate);
        PriceMaster testPriceMaster = priceMasterList.get(priceMasterList.size() - 1);
        assertThat(testPriceMaster.getPriceTableName()).isEqualTo(UPDATED_PRICE_TABLE_NAME);
        assertThat(testPriceMaster.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testPriceMaster.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testPriceMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPriceMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPriceMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPriceMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPriceMaster.getPriceCodeGroup()).isEqualTo(UPDATED_PRICE_CODE_GROUP);
        assertThat(testPriceMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPriceMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPriceMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPriceMaster.getPriceMasterUuid()).isEqualTo(UPDATED_PRICE_MASTER_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingPriceMaster() throws Exception {
        int databaseSizeBeforeUpdate = priceMasterRepository.findAll().size();
        priceMaster.setPriceTableId(count.incrementAndGet());

        // Create the PriceMaster
        PriceMasterDTO priceMasterDTO = priceMasterMapper.toDto(priceMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPriceMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, priceMasterDTO.getPriceTableId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(priceMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PriceMaster in the database
        List<PriceMaster> priceMasterList = priceMasterRepository.findAll();
        assertThat(priceMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPriceMaster() throws Exception {
        int databaseSizeBeforeUpdate = priceMasterRepository.findAll().size();
        priceMaster.setPriceTableId(count.incrementAndGet());

        // Create the PriceMaster
        PriceMasterDTO priceMasterDTO = priceMasterMapper.toDto(priceMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPriceMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(priceMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PriceMaster in the database
        List<PriceMaster> priceMasterList = priceMasterRepository.findAll();
        assertThat(priceMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPriceMaster() throws Exception {
        int databaseSizeBeforeUpdate = priceMasterRepository.findAll().size();
        priceMaster.setPriceTableId(count.incrementAndGet());

        // Create the PriceMaster
        PriceMasterDTO priceMasterDTO = priceMasterMapper.toDto(priceMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPriceMasterMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(priceMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PriceMaster in the database
        List<PriceMaster> priceMasterList = priceMasterRepository.findAll();
        assertThat(priceMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePriceMaster() throws Exception {
        // Initialize the database
        priceMasterRepository.saveAndFlush(priceMaster);

        int databaseSizeBeforeDelete = priceMasterRepository.findAll().size();

        // Delete the priceMaster
        restPriceMasterMockMvc
            .perform(delete(ENTITY_API_URL_ID, priceMaster.getPriceTableId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PriceMaster> priceMasterList = priceMasterRepository.findAll();
        assertThat(priceMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
