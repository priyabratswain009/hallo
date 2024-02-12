package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.PosMaster;
import com.sunknowledge.dme.rcm.repository.PosMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.PosMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.PosMasterMapper;
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
 * Integration tests for the {@link PosMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PosMasterResourceIT {

    private static final String DEFAULT_POS_CODE = "AAAAAAAAAA";
    private static final String UPDATED_POS_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_POS_NAME = "AAAAAAAAAA";
    private static final String UPDATED_POS_NAME = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_POS_MASTER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_POS_MASTER_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/pos-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{posId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PosMasterRepository posMasterRepository;

    @Autowired
    private PosMasterMapper posMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPosMasterMockMvc;

    private PosMaster posMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PosMaster createEntity(EntityManager em) {
        PosMaster posMaster = new PosMaster()
            .posCode(DEFAULT_POS_CODE)
            .posName(DEFAULT_POS_NAME)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .posMasterUuid(DEFAULT_POS_MASTER_UUID);
        return posMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PosMaster createUpdatedEntity(EntityManager em) {
        PosMaster posMaster = new PosMaster()
            .posCode(UPDATED_POS_CODE)
            .posName(UPDATED_POS_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .posMasterUuid(UPDATED_POS_MASTER_UUID);
        return posMaster;
    }

    @BeforeEach
    public void initTest() {
        posMaster = createEntity(em);
    }

    @Test
    @Transactional
    void createPosMaster() throws Exception {
        int databaseSizeBeforeCreate = posMasterRepository.findAll().size();
        // Create the PosMaster
        PosMasterDTO posMasterDTO = posMasterMapper.toDto(posMaster);
        restPosMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(posMasterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the PosMaster in the database
        List<PosMaster> posMasterList = posMasterRepository.findAll();
        assertThat(posMasterList).hasSize(databaseSizeBeforeCreate + 1);
        PosMaster testPosMaster = posMasterList.get(posMasterList.size() - 1);
        assertThat(testPosMaster.getPosCode()).isEqualTo(DEFAULT_POS_CODE);
        assertThat(testPosMaster.getPosName()).isEqualTo(DEFAULT_POS_NAME);
        assertThat(testPosMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPosMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testPosMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testPosMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testPosMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testPosMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testPosMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testPosMaster.getPosMasterUuid()).isEqualTo(DEFAULT_POS_MASTER_UUID);
    }

    @Test
    @Transactional
    void createPosMasterWithExistingId() throws Exception {
        // Create the PosMaster with an existing ID
        posMaster.setPosId(1L);
        PosMasterDTO posMasterDTO = posMasterMapper.toDto(posMaster);

        int databaseSizeBeforeCreate = posMasterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPosMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(posMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PosMaster in the database
        List<PosMaster> posMasterList = posMasterRepository.findAll();
        assertThat(posMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPosMasters() throws Exception {
        // Initialize the database
        posMasterRepository.saveAndFlush(posMaster);

        // Get all the posMasterList
        restPosMasterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=posId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].posId").value(hasItem(posMaster.getPosId().intValue())))
            .andExpect(jsonPath("$.[*].posCode").value(hasItem(DEFAULT_POS_CODE)))
            .andExpect(jsonPath("$.[*].posName").value(hasItem(DEFAULT_POS_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].posMasterUuid").value(hasItem(DEFAULT_POS_MASTER_UUID.toString())));
    }

    @Test
    @Transactional
    void getPosMaster() throws Exception {
        // Initialize the database
        posMasterRepository.saveAndFlush(posMaster);

        // Get the posMaster
        restPosMasterMockMvc
            .perform(get(ENTITY_API_URL_ID, posMaster.getPosId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.posId").value(posMaster.getPosId().intValue()))
            .andExpect(jsonPath("$.posCode").value(DEFAULT_POS_CODE))
            .andExpect(jsonPath("$.posName").value(DEFAULT_POS_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.posMasterUuid").value(DEFAULT_POS_MASTER_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingPosMaster() throws Exception {
        // Get the posMaster
        restPosMasterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingPosMaster() throws Exception {
        // Initialize the database
        posMasterRepository.saveAndFlush(posMaster);

        int databaseSizeBeforeUpdate = posMasterRepository.findAll().size();

        // Update the posMaster
        PosMaster updatedPosMaster = posMasterRepository.findById(posMaster.getPosId()).get();
        // Disconnect from session so that the updates on updatedPosMaster are not directly saved in db
        em.detach(updatedPosMaster);
        updatedPosMaster
            .posCode(UPDATED_POS_CODE)
            .posName(UPDATED_POS_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .posMasterUuid(UPDATED_POS_MASTER_UUID);
        PosMasterDTO posMasterDTO = posMasterMapper.toDto(updatedPosMaster);

        restPosMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, posMasterDTO.getPosId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(posMasterDTO))
            )
            .andExpect(status().isOk());

        // Validate the PosMaster in the database
        List<PosMaster> posMasterList = posMasterRepository.findAll();
        assertThat(posMasterList).hasSize(databaseSizeBeforeUpdate);
        PosMaster testPosMaster = posMasterList.get(posMasterList.size() - 1);
        assertThat(testPosMaster.getPosCode()).isEqualTo(UPDATED_POS_CODE);
        assertThat(testPosMaster.getPosName()).isEqualTo(UPDATED_POS_NAME);
        assertThat(testPosMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPosMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPosMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPosMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPosMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPosMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPosMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPosMaster.getPosMasterUuid()).isEqualTo(UPDATED_POS_MASTER_UUID);
    }

    @Test
    @Transactional
    void putNonExistingPosMaster() throws Exception {
        int databaseSizeBeforeUpdate = posMasterRepository.findAll().size();
        posMaster.setPosId(count.incrementAndGet());

        // Create the PosMaster
        PosMasterDTO posMasterDTO = posMasterMapper.toDto(posMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPosMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, posMasterDTO.getPosId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(posMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PosMaster in the database
        List<PosMaster> posMasterList = posMasterRepository.findAll();
        assertThat(posMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPosMaster() throws Exception {
        int databaseSizeBeforeUpdate = posMasterRepository.findAll().size();
        posMaster.setPosId(count.incrementAndGet());

        // Create the PosMaster
        PosMasterDTO posMasterDTO = posMasterMapper.toDto(posMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPosMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(posMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PosMaster in the database
        List<PosMaster> posMasterList = posMasterRepository.findAll();
        assertThat(posMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPosMaster() throws Exception {
        int databaseSizeBeforeUpdate = posMasterRepository.findAll().size();
        posMaster.setPosId(count.incrementAndGet());

        // Create the PosMaster
        PosMasterDTO posMasterDTO = posMasterMapper.toDto(posMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPosMasterMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(posMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PosMaster in the database
        List<PosMaster> posMasterList = posMasterRepository.findAll();
        assertThat(posMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePosMasterWithPatch() throws Exception {
        // Initialize the database
        posMasterRepository.saveAndFlush(posMaster);

        int databaseSizeBeforeUpdate = posMasterRepository.findAll().size();

        // Update the posMaster using partial update
        PosMaster partialUpdatedPosMaster = new PosMaster();
        partialUpdatedPosMaster.setPosId(posMaster.getPosId());

        partialUpdatedPosMaster
            .posCode(UPDATED_POS_CODE)
            .posName(UPDATED_POS_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE);

        restPosMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPosMaster.getPosId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPosMaster))
            )
            .andExpect(status().isOk());

        // Validate the PosMaster in the database
        List<PosMaster> posMasterList = posMasterRepository.findAll();
        assertThat(posMasterList).hasSize(databaseSizeBeforeUpdate);
        PosMaster testPosMaster = posMasterList.get(posMasterList.size() - 1);
        assertThat(testPosMaster.getPosCode()).isEqualTo(UPDATED_POS_CODE);
        assertThat(testPosMaster.getPosName()).isEqualTo(UPDATED_POS_NAME);
        assertThat(testPosMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPosMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPosMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testPosMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPosMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testPosMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testPosMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testPosMaster.getPosMasterUuid()).isEqualTo(DEFAULT_POS_MASTER_UUID);
    }

    @Test
    @Transactional
    void fullUpdatePosMasterWithPatch() throws Exception {
        // Initialize the database
        posMasterRepository.saveAndFlush(posMaster);

        int databaseSizeBeforeUpdate = posMasterRepository.findAll().size();

        // Update the posMaster using partial update
        PosMaster partialUpdatedPosMaster = new PosMaster();
        partialUpdatedPosMaster.setPosId(posMaster.getPosId());

        partialUpdatedPosMaster
            .posCode(UPDATED_POS_CODE)
            .posName(UPDATED_POS_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .posMasterUuid(UPDATED_POS_MASTER_UUID);

        restPosMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPosMaster.getPosId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPosMaster))
            )
            .andExpect(status().isOk());

        // Validate the PosMaster in the database
        List<PosMaster> posMasterList = posMasterRepository.findAll();
        assertThat(posMasterList).hasSize(databaseSizeBeforeUpdate);
        PosMaster testPosMaster = posMasterList.get(posMasterList.size() - 1);
        assertThat(testPosMaster.getPosCode()).isEqualTo(UPDATED_POS_CODE);
        assertThat(testPosMaster.getPosName()).isEqualTo(UPDATED_POS_NAME);
        assertThat(testPosMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPosMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPosMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPosMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPosMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPosMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPosMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPosMaster.getPosMasterUuid()).isEqualTo(UPDATED_POS_MASTER_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingPosMaster() throws Exception {
        int databaseSizeBeforeUpdate = posMasterRepository.findAll().size();
        posMaster.setPosId(count.incrementAndGet());

        // Create the PosMaster
        PosMasterDTO posMasterDTO = posMasterMapper.toDto(posMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPosMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, posMasterDTO.getPosId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(posMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PosMaster in the database
        List<PosMaster> posMasterList = posMasterRepository.findAll();
        assertThat(posMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPosMaster() throws Exception {
        int databaseSizeBeforeUpdate = posMasterRepository.findAll().size();
        posMaster.setPosId(count.incrementAndGet());

        // Create the PosMaster
        PosMasterDTO posMasterDTO = posMasterMapper.toDto(posMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPosMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(posMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PosMaster in the database
        List<PosMaster> posMasterList = posMasterRepository.findAll();
        assertThat(posMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPosMaster() throws Exception {
        int databaseSizeBeforeUpdate = posMasterRepository.findAll().size();
        posMaster.setPosId(count.incrementAndGet());

        // Create the PosMaster
        PosMasterDTO posMasterDTO = posMasterMapper.toDto(posMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPosMasterMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(posMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PosMaster in the database
        List<PosMaster> posMasterList = posMasterRepository.findAll();
        assertThat(posMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePosMaster() throws Exception {
        // Initialize the database
        posMasterRepository.saveAndFlush(posMaster);

        int databaseSizeBeforeDelete = posMasterRepository.findAll().size();

        // Delete the posMaster
        restPosMasterMockMvc
            .perform(delete(ENTITY_API_URL_ID, posMaster.getPosId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PosMaster> posMasterList = posMasterRepository.findAll();
        assertThat(posMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
