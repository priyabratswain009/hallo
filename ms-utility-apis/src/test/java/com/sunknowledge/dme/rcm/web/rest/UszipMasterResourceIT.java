package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.UszipMaster;
import com.sunknowledge.dme.rcm.repository.UszipMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.UszipMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.UszipMasterMapper;
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
 * Integration tests for the {@link UszipMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class UszipMasterResourceIT {

    private static final Long DEFAULT_ZIP_CODE = 1L;
    private static final Long UPDATED_ZIP_CODE = 2L;

    private static final String DEFAULT_CITY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CITY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STATE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_STATE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_STATE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STATE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final UUID DEFAULT_USZIP_MASTER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_USZIP_MASTER_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/uszip-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{uszipMasterId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private UszipMasterRepository uszipMasterRepository;

    @Autowired
    private UszipMasterMapper uszipMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUszipMasterMockMvc;

    private UszipMaster uszipMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UszipMaster createEntity(EntityManager em) {
        UszipMaster uszipMaster = new UszipMaster()
            .zipCode(DEFAULT_ZIP_CODE)
            .cityName(DEFAULT_CITY_NAME)
            .stateCode(DEFAULT_STATE_CODE)
            .stateName(DEFAULT_STATE_NAME)
            .status(DEFAULT_STATUS)
            .uszipMasterUuid(DEFAULT_USZIP_MASTER_UUID);
        return uszipMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UszipMaster createUpdatedEntity(EntityManager em) {
        UszipMaster uszipMaster = new UszipMaster()
            .zipCode(UPDATED_ZIP_CODE)
            .cityName(UPDATED_CITY_NAME)
            .stateCode(UPDATED_STATE_CODE)
            .stateName(UPDATED_STATE_NAME)
            .status(UPDATED_STATUS)
            .uszipMasterUuid(UPDATED_USZIP_MASTER_UUID);
        return uszipMaster;
    }

    @BeforeEach
    public void initTest() {
        uszipMaster = createEntity(em);
    }

    @Test
    @Transactional
    void createUszipMaster() throws Exception {
        int databaseSizeBeforeCreate = uszipMasterRepository.findAll().size();
        // Create the UszipMaster
        UszipMasterDTO uszipMasterDTO = uszipMasterMapper.toDto(uszipMaster);
        restUszipMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(uszipMasterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the UszipMaster in the database
        List<UszipMaster> uszipMasterList = uszipMasterRepository.findAll();
        assertThat(uszipMasterList).hasSize(databaseSizeBeforeCreate + 1);
        UszipMaster testUszipMaster = uszipMasterList.get(uszipMasterList.size() - 1);
        assertThat(testUszipMaster.getZipCode()).isEqualTo(DEFAULT_ZIP_CODE);
        assertThat(testUszipMaster.getCityName()).isEqualTo(DEFAULT_CITY_NAME);
        assertThat(testUszipMaster.getStateCode()).isEqualTo(DEFAULT_STATE_CODE);
        assertThat(testUszipMaster.getStateName()).isEqualTo(DEFAULT_STATE_NAME);
        assertThat(testUszipMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testUszipMaster.getUszipMasterUuid()).isEqualTo(DEFAULT_USZIP_MASTER_UUID);
    }

    @Test
    @Transactional
    void createUszipMasterWithExistingId() throws Exception {
        // Create the UszipMaster with an existing ID
        uszipMaster.setUszipMasterId(1L);
        UszipMasterDTO uszipMasterDTO = uszipMasterMapper.toDto(uszipMaster);

        int databaseSizeBeforeCreate = uszipMasterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restUszipMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(uszipMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UszipMaster in the database
        List<UszipMaster> uszipMasterList = uszipMasterRepository.findAll();
        assertThat(uszipMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllUszipMasters() throws Exception {
        // Initialize the database
        uszipMasterRepository.saveAndFlush(uszipMaster);

        // Get all the uszipMasterList
        restUszipMasterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=uszipMasterId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].uszipMasterId").value(hasItem(uszipMaster.getUszipMasterId().intValue())))
            .andExpect(jsonPath("$.[*].zipCode").value(hasItem(DEFAULT_ZIP_CODE.intValue())))
            .andExpect(jsonPath("$.[*].cityName").value(hasItem(DEFAULT_CITY_NAME)))
            .andExpect(jsonPath("$.[*].stateCode").value(hasItem(DEFAULT_STATE_CODE)))
            .andExpect(jsonPath("$.[*].stateName").value(hasItem(DEFAULT_STATE_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].uszipMasterUuid").value(hasItem(DEFAULT_USZIP_MASTER_UUID.toString())));
    }

    @Test
    @Transactional
    void getUszipMaster() throws Exception {
        // Initialize the database
        uszipMasterRepository.saveAndFlush(uszipMaster);

        // Get the uszipMaster
        restUszipMasterMockMvc
            .perform(get(ENTITY_API_URL_ID, uszipMaster.getUszipMasterId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.uszipMasterId").value(uszipMaster.getUszipMasterId().intValue()))
            .andExpect(jsonPath("$.zipCode").value(DEFAULT_ZIP_CODE.intValue()))
            .andExpect(jsonPath("$.cityName").value(DEFAULT_CITY_NAME))
            .andExpect(jsonPath("$.stateCode").value(DEFAULT_STATE_CODE))
            .andExpect(jsonPath("$.stateName").value(DEFAULT_STATE_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.uszipMasterUuid").value(DEFAULT_USZIP_MASTER_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingUszipMaster() throws Exception {
        // Get the uszipMaster
        restUszipMasterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewUszipMaster() throws Exception {
        // Initialize the database
        uszipMasterRepository.saveAndFlush(uszipMaster);

        int databaseSizeBeforeUpdate = uszipMasterRepository.findAll().size();

        // Update the uszipMaster
        UszipMaster updatedUszipMaster = uszipMasterRepository.findById(uszipMaster.getUszipMasterId()).get();
        // Disconnect from session so that the updates on updatedUszipMaster are not directly saved in db
        em.detach(updatedUszipMaster);
        updatedUszipMaster
            .zipCode(UPDATED_ZIP_CODE)
            .cityName(UPDATED_CITY_NAME)
            .stateCode(UPDATED_STATE_CODE)
            .stateName(UPDATED_STATE_NAME)
            .status(UPDATED_STATUS)
            .uszipMasterUuid(UPDATED_USZIP_MASTER_UUID);
        UszipMasterDTO uszipMasterDTO = uszipMasterMapper.toDto(updatedUszipMaster);

        restUszipMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, uszipMasterDTO.getUszipMasterId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(uszipMasterDTO))
            )
            .andExpect(status().isOk());

        // Validate the UszipMaster in the database
        List<UszipMaster> uszipMasterList = uszipMasterRepository.findAll();
        assertThat(uszipMasterList).hasSize(databaseSizeBeforeUpdate);
        UszipMaster testUszipMaster = uszipMasterList.get(uszipMasterList.size() - 1);
        assertThat(testUszipMaster.getZipCode()).isEqualTo(UPDATED_ZIP_CODE);
        assertThat(testUszipMaster.getCityName()).isEqualTo(UPDATED_CITY_NAME);
        assertThat(testUszipMaster.getStateCode()).isEqualTo(UPDATED_STATE_CODE);
        assertThat(testUszipMaster.getStateName()).isEqualTo(UPDATED_STATE_NAME);
        assertThat(testUszipMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testUszipMaster.getUszipMasterUuid()).isEqualTo(UPDATED_USZIP_MASTER_UUID);
    }

    @Test
    @Transactional
    void putNonExistingUszipMaster() throws Exception {
        int databaseSizeBeforeUpdate = uszipMasterRepository.findAll().size();
        uszipMaster.setUszipMasterId(count.incrementAndGet());

        // Create the UszipMaster
        UszipMasterDTO uszipMasterDTO = uszipMasterMapper.toDto(uszipMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUszipMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, uszipMasterDTO.getUszipMasterId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(uszipMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UszipMaster in the database
        List<UszipMaster> uszipMasterList = uszipMasterRepository.findAll();
        assertThat(uszipMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchUszipMaster() throws Exception {
        int databaseSizeBeforeUpdate = uszipMasterRepository.findAll().size();
        uszipMaster.setUszipMasterId(count.incrementAndGet());

        // Create the UszipMaster
        UszipMasterDTO uszipMasterDTO = uszipMasterMapper.toDto(uszipMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUszipMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(uszipMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UszipMaster in the database
        List<UszipMaster> uszipMasterList = uszipMasterRepository.findAll();
        assertThat(uszipMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamUszipMaster() throws Exception {
        int databaseSizeBeforeUpdate = uszipMasterRepository.findAll().size();
        uszipMaster.setUszipMasterId(count.incrementAndGet());

        // Create the UszipMaster
        UszipMasterDTO uszipMasterDTO = uszipMasterMapper.toDto(uszipMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUszipMasterMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(uszipMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the UszipMaster in the database
        List<UszipMaster> uszipMasterList = uszipMasterRepository.findAll();
        assertThat(uszipMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateUszipMasterWithPatch() throws Exception {
        // Initialize the database
        uszipMasterRepository.saveAndFlush(uszipMaster);

        int databaseSizeBeforeUpdate = uszipMasterRepository.findAll().size();

        // Update the uszipMaster using partial update
        UszipMaster partialUpdatedUszipMaster = new UszipMaster();
        partialUpdatedUszipMaster.setUszipMasterId(uszipMaster.getUszipMasterId());

        partialUpdatedUszipMaster
            .zipCode(UPDATED_ZIP_CODE)
            .cityName(UPDATED_CITY_NAME)
            .stateCode(UPDATED_STATE_CODE)
            .uszipMasterUuid(UPDATED_USZIP_MASTER_UUID);

        restUszipMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUszipMaster.getUszipMasterId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUszipMaster))
            )
            .andExpect(status().isOk());

        // Validate the UszipMaster in the database
        List<UszipMaster> uszipMasterList = uszipMasterRepository.findAll();
        assertThat(uszipMasterList).hasSize(databaseSizeBeforeUpdate);
        UszipMaster testUszipMaster = uszipMasterList.get(uszipMasterList.size() - 1);
        assertThat(testUszipMaster.getZipCode()).isEqualTo(UPDATED_ZIP_CODE);
        assertThat(testUszipMaster.getCityName()).isEqualTo(UPDATED_CITY_NAME);
        assertThat(testUszipMaster.getStateCode()).isEqualTo(UPDATED_STATE_CODE);
        assertThat(testUszipMaster.getStateName()).isEqualTo(DEFAULT_STATE_NAME);
        assertThat(testUszipMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testUszipMaster.getUszipMasterUuid()).isEqualTo(UPDATED_USZIP_MASTER_UUID);
    }

    @Test
    @Transactional
    void fullUpdateUszipMasterWithPatch() throws Exception {
        // Initialize the database
        uszipMasterRepository.saveAndFlush(uszipMaster);

        int databaseSizeBeforeUpdate = uszipMasterRepository.findAll().size();

        // Update the uszipMaster using partial update
        UszipMaster partialUpdatedUszipMaster = new UszipMaster();
        partialUpdatedUszipMaster.setUszipMasterId(uszipMaster.getUszipMasterId());

        partialUpdatedUszipMaster
            .zipCode(UPDATED_ZIP_CODE)
            .cityName(UPDATED_CITY_NAME)
            .stateCode(UPDATED_STATE_CODE)
            .stateName(UPDATED_STATE_NAME)
            .status(UPDATED_STATUS)
            .uszipMasterUuid(UPDATED_USZIP_MASTER_UUID);

        restUszipMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUszipMaster.getUszipMasterId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUszipMaster))
            )
            .andExpect(status().isOk());

        // Validate the UszipMaster in the database
        List<UszipMaster> uszipMasterList = uszipMasterRepository.findAll();
        assertThat(uszipMasterList).hasSize(databaseSizeBeforeUpdate);
        UszipMaster testUszipMaster = uszipMasterList.get(uszipMasterList.size() - 1);
        assertThat(testUszipMaster.getZipCode()).isEqualTo(UPDATED_ZIP_CODE);
        assertThat(testUszipMaster.getCityName()).isEqualTo(UPDATED_CITY_NAME);
        assertThat(testUszipMaster.getStateCode()).isEqualTo(UPDATED_STATE_CODE);
        assertThat(testUszipMaster.getStateName()).isEqualTo(UPDATED_STATE_NAME);
        assertThat(testUszipMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testUszipMaster.getUszipMasterUuid()).isEqualTo(UPDATED_USZIP_MASTER_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingUszipMaster() throws Exception {
        int databaseSizeBeforeUpdate = uszipMasterRepository.findAll().size();
        uszipMaster.setUszipMasterId(count.incrementAndGet());

        // Create the UszipMaster
        UszipMasterDTO uszipMasterDTO = uszipMasterMapper.toDto(uszipMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUszipMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, uszipMasterDTO.getUszipMasterId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(uszipMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UszipMaster in the database
        List<UszipMaster> uszipMasterList = uszipMasterRepository.findAll();
        assertThat(uszipMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchUszipMaster() throws Exception {
        int databaseSizeBeforeUpdate = uszipMasterRepository.findAll().size();
        uszipMaster.setUszipMasterId(count.incrementAndGet());

        // Create the UszipMaster
        UszipMasterDTO uszipMasterDTO = uszipMasterMapper.toDto(uszipMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUszipMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(uszipMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UszipMaster in the database
        List<UszipMaster> uszipMasterList = uszipMasterRepository.findAll();
        assertThat(uszipMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamUszipMaster() throws Exception {
        int databaseSizeBeforeUpdate = uszipMasterRepository.findAll().size();
        uszipMaster.setUszipMasterId(count.incrementAndGet());

        // Create the UszipMaster
        UszipMasterDTO uszipMasterDTO = uszipMasterMapper.toDto(uszipMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUszipMasterMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(uszipMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the UszipMaster in the database
        List<UszipMaster> uszipMasterList = uszipMasterRepository.findAll();
        assertThat(uszipMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteUszipMaster() throws Exception {
        // Initialize the database
        uszipMasterRepository.saveAndFlush(uszipMaster);

        int databaseSizeBeforeDelete = uszipMasterRepository.findAll().size();

        // Delete the uszipMaster
        restUszipMasterMockMvc
            .perform(delete(ENTITY_API_URL_ID, uszipMaster.getUszipMasterId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UszipMaster> uszipMasterList = uszipMasterRepository.findAll();
        assertThat(uszipMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
