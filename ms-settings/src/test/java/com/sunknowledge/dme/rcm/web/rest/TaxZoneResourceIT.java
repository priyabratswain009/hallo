package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.TaxZone;
import com.sunknowledge.dme.rcm.repository.TaxZoneRepository;
import com.sunknowledge.dme.rcm.service.dto.TaxZoneDTO;
import com.sunknowledge.dme.rcm.service.mapper.TaxZoneMapper;
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
 * Integration tests for the {@link TaxZoneResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TaxZoneResourceIT {

    private static final Long DEFAULT_STATE_CODE_ID = 1L;
    private static final Long UPDATED_STATE_CODE_ID = 2L;

    private static final String DEFAULT_STATE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STATE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STATE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_STATE_CODE = "BBBBBBBBBB";

    private static final Double DEFAULT_TAX_RATE = 1D;
    private static final Double UPDATED_TAX_RATE = 2D;

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final UUID DEFAULT_TAX_ZONE_UUID = UUID.randomUUID();
    private static final UUID UPDATED_TAX_ZONE_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/tax-zones";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{taxZoneId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TaxZoneRepository taxZoneRepository;

    @Autowired
    private TaxZoneMapper taxZoneMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTaxZoneMockMvc;

    private TaxZone taxZone;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaxZone createEntity(EntityManager em) {
        TaxZone taxZone = new TaxZone()
            .stateCodeId(DEFAULT_STATE_CODE_ID)
            .stateName(DEFAULT_STATE_NAME)
            .stateCode(DEFAULT_STATE_CODE)
            .taxRate(DEFAULT_TAX_RATE)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .taxZoneUuid(DEFAULT_TAX_ZONE_UUID);
        return taxZone;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaxZone createUpdatedEntity(EntityManager em) {
        TaxZone taxZone = new TaxZone()
            .stateCodeId(UPDATED_STATE_CODE_ID)
            .stateName(UPDATED_STATE_NAME)
            .stateCode(UPDATED_STATE_CODE)
            .taxRate(UPDATED_TAX_RATE)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .taxZoneUuid(UPDATED_TAX_ZONE_UUID);
        return taxZone;
    }

    @BeforeEach
    public void initTest() {
        taxZone = createEntity(em);
    }

    @Test
    @Transactional
    void createTaxZone() throws Exception {
        int databaseSizeBeforeCreate = taxZoneRepository.findAll().size();
        // Create the TaxZone
        TaxZoneDTO taxZoneDTO = taxZoneMapper.toDto(taxZone);
        restTaxZoneMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taxZoneDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TaxZone in the database
        List<TaxZone> taxZoneList = taxZoneRepository.findAll();
        assertThat(taxZoneList).hasSize(databaseSizeBeforeCreate + 1);
        TaxZone testTaxZone = taxZoneList.get(taxZoneList.size() - 1);
        assertThat(testTaxZone.getStateCodeId()).isEqualTo(DEFAULT_STATE_CODE_ID);
        assertThat(testTaxZone.getStateName()).isEqualTo(DEFAULT_STATE_NAME);
        assertThat(testTaxZone.getStateCode()).isEqualTo(DEFAULT_STATE_CODE);
        assertThat(testTaxZone.getTaxRate()).isEqualTo(DEFAULT_TAX_RATE);
        assertThat(testTaxZone.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testTaxZone.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testTaxZone.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testTaxZone.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testTaxZone.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testTaxZone.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testTaxZone.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testTaxZone.getTaxZoneUuid()).isEqualTo(DEFAULT_TAX_ZONE_UUID);
    }

    @Test
    @Transactional
    void createTaxZoneWithExistingId() throws Exception {
        // Create the TaxZone with an existing ID
        taxZone.setTaxZoneId(1L);
        TaxZoneDTO taxZoneDTO = taxZoneMapper.toDto(taxZone);

        int databaseSizeBeforeCreate = taxZoneRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTaxZoneMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taxZoneDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaxZone in the database
        List<TaxZone> taxZoneList = taxZoneRepository.findAll();
        assertThat(taxZoneList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTaxZones() throws Exception {
        // Initialize the database
        taxZoneRepository.saveAndFlush(taxZone);

        // Get all the taxZoneList
        restTaxZoneMockMvc
            .perform(get(ENTITY_API_URL + "?sort=taxZoneId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].taxZoneId").value(hasItem(taxZone.getTaxZoneId().intValue())))
            .andExpect(jsonPath("$.[*].stateCodeId").value(hasItem(DEFAULT_STATE_CODE_ID.intValue())))
            .andExpect(jsonPath("$.[*].stateName").value(hasItem(DEFAULT_STATE_NAME)))
            .andExpect(jsonPath("$.[*].stateCode").value(hasItem(DEFAULT_STATE_CODE)))
            .andExpect(jsonPath("$.[*].taxRate").value(hasItem(DEFAULT_TAX_RATE.doubleValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].taxZoneUuid").value(hasItem(DEFAULT_TAX_ZONE_UUID.toString())));
    }

    @Test
    @Transactional
    void getTaxZone() throws Exception {
        // Initialize the database
        taxZoneRepository.saveAndFlush(taxZone);

        // Get the taxZone
        restTaxZoneMockMvc
            .perform(get(ENTITY_API_URL_ID, taxZone.getTaxZoneId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.taxZoneId").value(taxZone.getTaxZoneId().intValue()))
            .andExpect(jsonPath("$.stateCodeId").value(DEFAULT_STATE_CODE_ID.intValue()))
            .andExpect(jsonPath("$.stateName").value(DEFAULT_STATE_NAME))
            .andExpect(jsonPath("$.stateCode").value(DEFAULT_STATE_CODE))
            .andExpect(jsonPath("$.taxRate").value(DEFAULT_TAX_RATE.doubleValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.taxZoneUuid").value(DEFAULT_TAX_ZONE_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingTaxZone() throws Exception {
        // Get the taxZone
        restTaxZoneMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTaxZone() throws Exception {
        // Initialize the database
        taxZoneRepository.saveAndFlush(taxZone);

        int databaseSizeBeforeUpdate = taxZoneRepository.findAll().size();

        // Update the taxZone
        TaxZone updatedTaxZone = taxZoneRepository.findById(taxZone.getTaxZoneId()).get();
        // Disconnect from session so that the updates on updatedTaxZone are not directly saved in db
        em.detach(updatedTaxZone);
        updatedTaxZone
            .stateCodeId(UPDATED_STATE_CODE_ID)
            .stateName(UPDATED_STATE_NAME)
            .stateCode(UPDATED_STATE_CODE)
            .taxRate(UPDATED_TAX_RATE)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .taxZoneUuid(UPDATED_TAX_ZONE_UUID);
        TaxZoneDTO taxZoneDTO = taxZoneMapper.toDto(updatedTaxZone);

        restTaxZoneMockMvc
            .perform(
                put(ENTITY_API_URL_ID, taxZoneDTO.getTaxZoneId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taxZoneDTO))
            )
            .andExpect(status().isOk());

        // Validate the TaxZone in the database
        List<TaxZone> taxZoneList = taxZoneRepository.findAll();
        assertThat(taxZoneList).hasSize(databaseSizeBeforeUpdate);
        TaxZone testTaxZone = taxZoneList.get(taxZoneList.size() - 1);
        assertThat(testTaxZone.getStateCodeId()).isEqualTo(UPDATED_STATE_CODE_ID);
        assertThat(testTaxZone.getStateName()).isEqualTo(UPDATED_STATE_NAME);
        assertThat(testTaxZone.getStateCode()).isEqualTo(UPDATED_STATE_CODE);
        assertThat(testTaxZone.getTaxRate()).isEqualTo(UPDATED_TAX_RATE);
        assertThat(testTaxZone.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testTaxZone.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testTaxZone.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testTaxZone.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testTaxZone.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testTaxZone.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testTaxZone.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testTaxZone.getTaxZoneUuid()).isEqualTo(UPDATED_TAX_ZONE_UUID);
    }

    @Test
    @Transactional
    void putNonExistingTaxZone() throws Exception {
        int databaseSizeBeforeUpdate = taxZoneRepository.findAll().size();
        taxZone.setTaxZoneId(count.incrementAndGet());

        // Create the TaxZone
        TaxZoneDTO taxZoneDTO = taxZoneMapper.toDto(taxZone);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaxZoneMockMvc
            .perform(
                put(ENTITY_API_URL_ID, taxZoneDTO.getTaxZoneId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taxZoneDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaxZone in the database
        List<TaxZone> taxZoneList = taxZoneRepository.findAll();
        assertThat(taxZoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTaxZone() throws Exception {
        int databaseSizeBeforeUpdate = taxZoneRepository.findAll().size();
        taxZone.setTaxZoneId(count.incrementAndGet());

        // Create the TaxZone
        TaxZoneDTO taxZoneDTO = taxZoneMapper.toDto(taxZone);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaxZoneMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taxZoneDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaxZone in the database
        List<TaxZone> taxZoneList = taxZoneRepository.findAll();
        assertThat(taxZoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTaxZone() throws Exception {
        int databaseSizeBeforeUpdate = taxZoneRepository.findAll().size();
        taxZone.setTaxZoneId(count.incrementAndGet());

        // Create the TaxZone
        TaxZoneDTO taxZoneDTO = taxZoneMapper.toDto(taxZone);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaxZoneMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taxZoneDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TaxZone in the database
        List<TaxZone> taxZoneList = taxZoneRepository.findAll();
        assertThat(taxZoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTaxZoneWithPatch() throws Exception {
        // Initialize the database
        taxZoneRepository.saveAndFlush(taxZone);

        int databaseSizeBeforeUpdate = taxZoneRepository.findAll().size();

        // Update the taxZone using partial update
        TaxZone partialUpdatedTaxZone = new TaxZone();
        partialUpdatedTaxZone.setTaxZoneId(taxZone.getTaxZoneId());

        partialUpdatedTaxZone
            .stateName(UPDATED_STATE_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE);

        restTaxZoneMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaxZone.getTaxZoneId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTaxZone))
            )
            .andExpect(status().isOk());

        // Validate the TaxZone in the database
        List<TaxZone> taxZoneList = taxZoneRepository.findAll();
        assertThat(taxZoneList).hasSize(databaseSizeBeforeUpdate);
        TaxZone testTaxZone = taxZoneList.get(taxZoneList.size() - 1);
        assertThat(testTaxZone.getStateCodeId()).isEqualTo(DEFAULT_STATE_CODE_ID);
        assertThat(testTaxZone.getStateName()).isEqualTo(UPDATED_STATE_NAME);
        assertThat(testTaxZone.getStateCode()).isEqualTo(DEFAULT_STATE_CODE);
        assertThat(testTaxZone.getTaxRate()).isEqualTo(DEFAULT_TAX_RATE);
        assertThat(testTaxZone.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testTaxZone.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testTaxZone.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testTaxZone.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testTaxZone.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testTaxZone.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testTaxZone.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testTaxZone.getTaxZoneUuid()).isEqualTo(DEFAULT_TAX_ZONE_UUID);
    }

    @Test
    @Transactional
    void fullUpdateTaxZoneWithPatch() throws Exception {
        // Initialize the database
        taxZoneRepository.saveAndFlush(taxZone);

        int databaseSizeBeforeUpdate = taxZoneRepository.findAll().size();

        // Update the taxZone using partial update
        TaxZone partialUpdatedTaxZone = new TaxZone();
        partialUpdatedTaxZone.setTaxZoneId(taxZone.getTaxZoneId());

        partialUpdatedTaxZone
            .stateCodeId(UPDATED_STATE_CODE_ID)
            .stateName(UPDATED_STATE_NAME)
            .stateCode(UPDATED_STATE_CODE)
            .taxRate(UPDATED_TAX_RATE)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .taxZoneUuid(UPDATED_TAX_ZONE_UUID);

        restTaxZoneMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaxZone.getTaxZoneId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTaxZone))
            )
            .andExpect(status().isOk());

        // Validate the TaxZone in the database
        List<TaxZone> taxZoneList = taxZoneRepository.findAll();
        assertThat(taxZoneList).hasSize(databaseSizeBeforeUpdate);
        TaxZone testTaxZone = taxZoneList.get(taxZoneList.size() - 1);
        assertThat(testTaxZone.getStateCodeId()).isEqualTo(UPDATED_STATE_CODE_ID);
        assertThat(testTaxZone.getStateName()).isEqualTo(UPDATED_STATE_NAME);
        assertThat(testTaxZone.getStateCode()).isEqualTo(UPDATED_STATE_CODE);
        assertThat(testTaxZone.getTaxRate()).isEqualTo(UPDATED_TAX_RATE);
        assertThat(testTaxZone.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testTaxZone.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testTaxZone.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testTaxZone.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testTaxZone.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testTaxZone.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testTaxZone.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testTaxZone.getTaxZoneUuid()).isEqualTo(UPDATED_TAX_ZONE_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingTaxZone() throws Exception {
        int databaseSizeBeforeUpdate = taxZoneRepository.findAll().size();
        taxZone.setTaxZoneId(count.incrementAndGet());

        // Create the TaxZone
        TaxZoneDTO taxZoneDTO = taxZoneMapper.toDto(taxZone);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaxZoneMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, taxZoneDTO.getTaxZoneId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(taxZoneDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaxZone in the database
        List<TaxZone> taxZoneList = taxZoneRepository.findAll();
        assertThat(taxZoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTaxZone() throws Exception {
        int databaseSizeBeforeUpdate = taxZoneRepository.findAll().size();
        taxZone.setTaxZoneId(count.incrementAndGet());

        // Create the TaxZone
        TaxZoneDTO taxZoneDTO = taxZoneMapper.toDto(taxZone);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaxZoneMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(taxZoneDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaxZone in the database
        List<TaxZone> taxZoneList = taxZoneRepository.findAll();
        assertThat(taxZoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTaxZone() throws Exception {
        int databaseSizeBeforeUpdate = taxZoneRepository.findAll().size();
        taxZone.setTaxZoneId(count.incrementAndGet());

        // Create the TaxZone
        TaxZoneDTO taxZoneDTO = taxZoneMapper.toDto(taxZone);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaxZoneMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(taxZoneDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TaxZone in the database
        List<TaxZone> taxZoneList = taxZoneRepository.findAll();
        assertThat(taxZoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTaxZone() throws Exception {
        // Initialize the database
        taxZoneRepository.saveAndFlush(taxZone);

        int databaseSizeBeforeDelete = taxZoneRepository.findAll().size();

        // Delete the taxZone
        restTaxZoneMockMvc
            .perform(delete(ENTITY_API_URL_ID, taxZone.getTaxZoneId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TaxZone> taxZoneList = taxZoneRepository.findAll();
        assertThat(taxZoneList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
